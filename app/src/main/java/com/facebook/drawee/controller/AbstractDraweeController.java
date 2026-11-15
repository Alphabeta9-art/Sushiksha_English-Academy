package com.facebook.drawee.controller;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.MotionEvent;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.components.DraweeEventTracker;
import com.facebook.drawee.components.RetryManager;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.interfaces.SettableDraweeHierarchy;
import com.facebook.fresco.middleware.MiddlewareUtils;
import com.facebook.fresco.ui.common.ControllerListener2;
import com.facebook.fresco.ui.common.ForwardingControllerListener2;
import com.facebook.fresco.ui.common.LoggingListener;
import com.facebook.fresco.ui.common.OnFadeListener;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public abstract class AbstractDraweeController<T, INFO> implements DraweeController, DeferredReleaser.Releasable, GestureDetector.ClickListener {
    private static final Map<String, Object> COMPONENT_EXTRAS = ImmutableMap.of("component_tag", "drawee");
    private static final Map<String, Object> SHORTCUT_EXTRAS = ImmutableMap.of("origin", "memory_bitmap", "origin_sub", "shortcut");
    private static final Class<?> TAG = AbstractDraweeController.class;
    private Object mCallerContext;

    @Nullable
    private String mContentDescription;

    @Nullable
    protected ControllerListener<INFO> mControllerListener;

    @Nullable
    private Drawable mControllerOverlay;

    @Nullable
    private ControllerViewportVisibilityListener mControllerViewportVisibilityListener;

    @Nullable
    private DataSource<T> mDataSource;
    private final DeferredReleaser mDeferredReleaser;

    @Nullable
    protected Drawable mDrawable;

    @Nullable
    private T mFetchedImage;

    @Nullable
    private GestureDetector mGestureDetector;
    private boolean mHasFetchFailed;
    private String mId;
    private boolean mIsAttached;
    private boolean mIsRequestSubmitted;
    private boolean mIsVisibleInViewportHint;

    @Nullable
    protected LoggingListener mLoggingListener;
    private boolean mRetainImageOnFailure;

    @Nullable
    private RetryManager mRetryManager;

    @Nullable
    private SettableDraweeHierarchy mSettableDraweeHierarchy;
    private final Executor mUiThreadImmediateExecutor;
    private final DraweeEventTracker mEventTracker = DraweeEventTracker.newInstance();
    protected ForwardingControllerListener2<INFO> mControllerListener2 = new ForwardingControllerListener2<>();
    private boolean mJustConstructed = true;
    private boolean mLogWithHighSamplingRate = false;

    protected abstract Drawable createDrawable(T t);

    protected abstract DataSource<T> getDataSource();

    @Nullable
    protected abstract INFO getImageInfo(T t);

    @Nullable
    public abstract Map<String, Object> obtainExtrasFromImage(INFO info);

    protected abstract void releaseDrawable(@Nullable Drawable drawable);

    protected abstract void releaseImage(@Nullable T t);

    private static class InternalForwardingListener<INFO> extends ForwardingControllerListener<INFO> {
        private InternalForwardingListener() {
        }

        public static <INFO> InternalForwardingListener<INFO> createInternal(ControllerListener<? super INFO> listener1, ControllerListener<? super INFO> listener2) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("AbstractDraweeController#createInternal");
            }
            InternalForwardingListener<INFO> forwarder = new InternalForwardingListener<>();
            forwarder.addListener(listener1);
            forwarder.addListener(listener2);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            return forwarder;
        }
    }

    public AbstractDraweeController(DeferredReleaser deferredReleaser, Executor uiThreadImmediateExecutor, String id, Object callerContext) {
        this.mDeferredReleaser = deferredReleaser;
        this.mUiThreadImmediateExecutor = uiThreadImmediateExecutor;
        init(id, callerContext);
    }

    protected void initialize(String id, Object callerContext) {
        init(id, callerContext);
        this.mJustConstructed = false;
        this.mLogWithHighSamplingRate = false;
    }

    private synchronized void init(String id, Object callerContext) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("AbstractDraweeController#init");
        }
        this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_INIT_CONTROLLER);
        if (!this.mJustConstructed && this.mDeferredReleaser != null) {
            this.mDeferredReleaser.cancelDeferredRelease(this);
        }
        this.mIsAttached = false;
        this.mIsVisibleInViewportHint = false;
        releaseFetch();
        this.mRetainImageOnFailure = false;
        if (this.mRetryManager != null) {
            this.mRetryManager.init();
        }
        if (this.mGestureDetector != null) {
            this.mGestureDetector.init();
            this.mGestureDetector.setClickListener(this);
        }
        if (this.mControllerListener instanceof InternalForwardingListener) {
            ((InternalForwardingListener) this.mControllerListener).clearListeners();
        } else {
            this.mControllerListener = null;
        }
        this.mControllerViewportVisibilityListener = null;
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.reset();
            this.mSettableDraweeHierarchy.setControllerOverlay(null);
            this.mSettableDraweeHierarchy = null;
        }
        this.mControllerOverlay = null;
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s -> %s: initialize", Integer.valueOf(System.identityHashCode(this)), this.mId, id);
        }
        this.mId = id;
        this.mCallerContext = callerContext;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        if (this.mLoggingListener != null) {
            setUpLoggingListener();
        }
    }

    @Override // com.facebook.drawee.components.DeferredReleaser.Releasable
    public void release() {
        this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_RELEASE_CONTROLLER);
        if (this.mRetryManager != null) {
            this.mRetryManager.reset();
        }
        if (this.mGestureDetector != null) {
            this.mGestureDetector.reset();
        }
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.reset();
        }
        releaseFetch();
    }

    private void releaseFetch() {
        boolean wasRequestSubmitted = this.mIsRequestSubmitted;
        this.mIsRequestSubmitted = false;
        this.mHasFetchFailed = false;
        Map<String, Object> datasourceExtras = null;
        Map<String, Object> imageExtras = null;
        if (this.mDataSource != null) {
            datasourceExtras = this.mDataSource.getExtras();
            this.mDataSource.close();
            this.mDataSource = null;
        }
        if (this.mDrawable != null) {
            releaseDrawable(this.mDrawable);
        }
        if (this.mContentDescription != null) {
            this.mContentDescription = null;
        }
        this.mDrawable = null;
        if (this.mFetchedImage != null) {
            imageExtras = obtainExtrasFromImage(getImageInfo(this.mFetchedImage));
            logMessageAndImage("release", this.mFetchedImage);
            releaseImage(this.mFetchedImage);
            this.mFetchedImage = null;
        }
        if (wasRequestSubmitted) {
            reportRelease(datasourceExtras, imageExtras);
        }
    }

    public String getId() {
        return this.mId;
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }

    protected RetryManager getRetryManager() {
        if (this.mRetryManager == null) {
            this.mRetryManager = new RetryManager();
        }
        return this.mRetryManager;
    }

    @Nullable
    protected GestureDetector getGestureDetector() {
        return this.mGestureDetector;
    }

    protected void setGestureDetector(@Nullable GestureDetector gestureDetector) {
        this.mGestureDetector = gestureDetector;
        if (this.mGestureDetector != null) {
            this.mGestureDetector.setClickListener(this);
        }
    }

    protected void setRetainImageOnFailure(boolean enabled) {
        this.mRetainImageOnFailure = enabled;
    }

    protected boolean isLogWithHighSamplingRate() {
        return this.mLogWithHighSamplingRate;
    }

    protected void setLogWithHighSamplingRate(boolean logWithHighSamplingRate) {
        this.mLogWithHighSamplingRate = logWithHighSamplingRate;
    }

    @Override // com.facebook.drawee.interfaces.DraweeController
    @Nullable
    public String getContentDescription() {
        return this.mContentDescription;
    }

    @Override // com.facebook.drawee.interfaces.DraweeController
    public void setContentDescription(@Nullable String contentDescription) {
        this.mContentDescription = contentDescription;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addControllerListener(ControllerListener<? super INFO> controllerListener) {
        Preconditions.checkNotNull(controllerListener);
        if (this.mControllerListener instanceof InternalForwardingListener) {
            ((InternalForwardingListener) this.mControllerListener).addListener(controllerListener);
        } else if (this.mControllerListener != null) {
            this.mControllerListener = InternalForwardingListener.createInternal(this.mControllerListener, controllerListener);
        } else {
            this.mControllerListener = controllerListener;
        }
    }

    public void addControllerListener2(ControllerListener2<INFO> controllerListener2) {
        this.mControllerListener2.addListener(controllerListener2);
    }

    public void removeControllerListener2(ControllerListener2<INFO> controllerListener2) {
        this.mControllerListener2.removeListener(controllerListener2);
    }

    public void setLoggingListener(LoggingListener loggingListener) {
        this.mLoggingListener = loggingListener;
    }

    @Nullable
    protected LoggingListener getLoggingListener() {
        return this.mLoggingListener;
    }

    public void removeControllerListener(ControllerListener<? super INFO> controllerListener) {
        Preconditions.checkNotNull(controllerListener);
        if (this.mControllerListener instanceof InternalForwardingListener) {
            ((InternalForwardingListener) this.mControllerListener).removeListener(controllerListener);
        } else if (this.mControllerListener == controllerListener) {
            this.mControllerListener = null;
        }
    }

    protected ControllerListener<INFO> getControllerListener() {
        if (this.mControllerListener == null) {
            return BaseControllerListener.getNoOpListener();
        }
        return this.mControllerListener;
    }

    protected ControllerListener2<INFO> getControllerListener2() {
        return this.mControllerListener2;
    }

    public void setControllerViewportVisibilityListener(@Nullable ControllerViewportVisibilityListener controllerViewportVisibilityListener) {
        this.mControllerViewportVisibilityListener = controllerViewportVisibilityListener;
    }

    @Override // com.facebook.drawee.interfaces.DraweeController
    @Nullable
    public DraweeHierarchy getHierarchy() {
        return this.mSettableDraweeHierarchy;
    }

    @Override // com.facebook.drawee.interfaces.DraweeController
    public void setHierarchy(@Nullable DraweeHierarchy hierarchy) {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: setHierarchy: %s", Integer.valueOf(System.identityHashCode(this)), this.mId, hierarchy);
        }
        this.mEventTracker.recordEvent(hierarchy != null ? DraweeEventTracker.Event.ON_SET_HIERARCHY : DraweeEventTracker.Event.ON_CLEAR_HIERARCHY);
        if (this.mIsRequestSubmitted) {
            this.mDeferredReleaser.cancelDeferredRelease(this);
            release();
        }
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.setControllerOverlay(null);
            this.mSettableDraweeHierarchy = null;
        }
        if (hierarchy != null) {
            Preconditions.checkArgument(Boolean.valueOf(hierarchy instanceof SettableDraweeHierarchy));
            this.mSettableDraweeHierarchy = (SettableDraweeHierarchy) hierarchy;
            this.mSettableDraweeHierarchy.setControllerOverlay(this.mControllerOverlay);
        }
        if (this.mLoggingListener != null) {
            setUpLoggingListener();
        }
    }

    private void setUpLoggingListener() {
        if (this.mSettableDraweeHierarchy instanceof GenericDraweeHierarchy) {
            ((GenericDraweeHierarchy) this.mSettableDraweeHierarchy).setOnFadeListener(new OnFadeListener() { // from class: com.facebook.drawee.controller.AbstractDraweeController.1
                @Override // com.facebook.fresco.ui.common.OnFadeListener
                public void onFadeFinished() {
                    if (AbstractDraweeController.this.mLoggingListener != null) {
                        AbstractDraweeController.this.mLoggingListener.onFadeFinished(AbstractDraweeController.this.mId);
                    }
                }

                @Override // com.facebook.fresco.ui.common.OnFadeListener
                public void onShownImmediately() {
                }

                @Override // com.facebook.fresco.ui.common.OnFadeListener
                public void onFadeStarted() {
                    if (AbstractDraweeController.this.mLoggingListener != null) {
                        AbstractDraweeController.this.mLoggingListener.onFadeStarted(AbstractDraweeController.this.mId);
                    }
                }
            });
        }
    }

    protected void setControllerOverlay(@Nullable Drawable controllerOverlay) {
        this.mControllerOverlay = controllerOverlay;
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.setControllerOverlay(this.mControllerOverlay);
        }
    }

    @Nullable
    protected Drawable getControllerOverlay() {
        return this.mControllerOverlay;
    }

    @Override // com.facebook.drawee.interfaces.DraweeController
    public void onAttach() throws Exception {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("AbstractDraweeController#onAttach");
        }
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: onAttach: %s", Integer.valueOf(System.identityHashCode(this)), this.mId, this.mIsRequestSubmitted ? "request already submitted" : "request needs submit");
        }
        this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_ATTACH_CONTROLLER);
        Preconditions.checkNotNull(this.mSettableDraweeHierarchy);
        this.mDeferredReleaser.cancelDeferredRelease(this);
        this.mIsAttached = true;
        if (!this.mIsRequestSubmitted) {
            submitRequest();
        }
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    @Override // com.facebook.drawee.interfaces.DraweeController
    public void onDetach() {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("AbstractDraweeController#onDetach");
        }
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: onDetach", Integer.valueOf(System.identityHashCode(this)), this.mId);
        }
        this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_DETACH_CONTROLLER);
        this.mIsAttached = false;
        this.mDeferredReleaser.scheduleDeferredRelease(this);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    @Override // com.facebook.drawee.interfaces.DraweeController
    public void onViewportVisibilityHint(boolean isVisibleInViewportHint) {
        ControllerViewportVisibilityListener listener = this.mControllerViewportVisibilityListener;
        if (listener != null) {
            if (isVisibleInViewportHint && !this.mIsVisibleInViewportHint) {
                listener.onDraweeViewportEntry(this.mId);
            } else if (!isVisibleInViewportHint && this.mIsVisibleInViewportHint) {
                listener.onDraweeViewportExit(this.mId);
            }
        }
        this.mIsVisibleInViewportHint = isVisibleInViewportHint;
    }

    @Override // com.facebook.drawee.interfaces.DraweeController
    public boolean onTouchEvent(MotionEvent event) {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: onTouchEvent %s", Integer.valueOf(System.identityHashCode(this)), this.mId, event);
        }
        if (this.mGestureDetector == null) {
            return false;
        }
        if (!this.mGestureDetector.isCapturingGesture() && !shouldHandleGesture()) {
            return false;
        }
        this.mGestureDetector.onTouchEvent(event);
        return true;
    }

    protected boolean shouldHandleGesture() {
        return shouldRetryOnTap();
    }

    private boolean shouldRetryOnTap() {
        return this.mHasFetchFailed && this.mRetryManager != null && this.mRetryManager.shouldRetryOnTap();
    }

    @Override // com.facebook.drawee.gestures.GestureDetector.ClickListener
    public boolean onClick() throws Exception {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: onClick", Integer.valueOf(System.identityHashCode(this)), this.mId);
        }
        if (shouldRetryOnTap()) {
            this.mRetryManager.notifyTapToRetry();
            this.mSettableDraweeHierarchy.reset();
            submitRequest();
            return true;
        }
        return false;
    }

    protected void submitRequest() throws Exception {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("AbstractDraweeController#submitRequest");
        }
        T closeableImage = getCachedImage();
        if (closeableImage != null) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("AbstractDraweeController#submitRequest->cache");
            }
            this.mDataSource = null;
            this.mIsRequestSubmitted = true;
            this.mHasFetchFailed = false;
            this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_SUBMIT_CACHE_HIT);
            reportSubmit(this.mDataSource, getImageInfo(closeableImage));
            onImageLoadedFromCacheImmediately(this.mId, closeableImage);
            onNewResultInternal(this.mId, this.mDataSource, closeableImage, 1.0f, true, true, true);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
                return;
            }
            return;
        }
        this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_DATASOURCE_SUBMIT);
        this.mSettableDraweeHierarchy.setProgress(0.0f, true);
        this.mIsRequestSubmitted = true;
        this.mHasFetchFailed = false;
        this.mDataSource = getDataSource();
        reportSubmit(this.mDataSource, null);
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: submitRequest: dataSource: %x", Integer.valueOf(System.identityHashCode(this)), this.mId, Integer.valueOf(System.identityHashCode(this.mDataSource)));
        }
        final String id = this.mId;
        final boolean wasImmediate = this.mDataSource.hasResult();
        DataSubscriber<T> dataSubscriber = new BaseDataSubscriber<T>() { // from class: com.facebook.drawee.controller.AbstractDraweeController.2
            @Override // com.facebook.datasource.BaseDataSubscriber
            public void onNewResultImpl(DataSource<T> dataSource) throws Exception {
                boolean isFinished = dataSource.isFinished();
                boolean hasMultipleResults = dataSource.hasMultipleResults();
                float progress = dataSource.getProgress();
                T image = dataSource.getResult();
                if (image != null) {
                    AbstractDraweeController.this.onNewResultInternal(id, dataSource, image, progress, isFinished, wasImmediate, hasMultipleResults);
                } else if (isFinished) {
                    AbstractDraweeController.this.onFailureInternal(id, dataSource, new NullPointerException(), true);
                }
            }

            @Override // com.facebook.datasource.BaseDataSubscriber
            public void onFailureImpl(DataSource<T> dataSource) {
                AbstractDraweeController.this.onFailureInternal(id, dataSource, dataSource.getFailureCause(), true);
            }

            @Override // com.facebook.datasource.BaseDataSubscriber, com.facebook.datasource.DataSubscriber
            public void onProgressUpdate(DataSource<T> dataSource) {
                boolean isFinished = dataSource.isFinished();
                float progress = dataSource.getProgress();
                AbstractDraweeController.this.onProgressUpdateInternal(id, dataSource, progress, isFinished);
            }
        };
        this.mDataSource.subscribe(dataSubscriber, this.mUiThreadImmediateExecutor);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNewResultInternal(String id, DataSource<T> dataSource, @Nullable T image, float progress, boolean isFinished, boolean wasImmediate, boolean deliverTempResult) throws Exception {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("AbstractDraweeController#onNewResultInternal");
            }
            if (!isExpectedDataSource(id, dataSource)) {
                logMessageAndImage("ignore_old_datasource @ onNewResult", image);
                releaseImage(image);
                dataSource.close();
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                    return;
                }
                return;
            }
            this.mEventTracker.recordEvent(isFinished ? DraweeEventTracker.Event.ON_DATASOURCE_RESULT : DraweeEventTracker.Event.ON_DATASOURCE_RESULT_INT);
            try {
                Drawable drawable = createDrawable(image);
                T previousImage = this.mFetchedImage;
                Drawable previousDrawable = this.mDrawable;
                this.mFetchedImage = image;
                this.mDrawable = drawable;
                try {
                    if (isFinished) {
                        logMessageAndImage("set_final_result @ onNewResult", image);
                        this.mDataSource = null;
                        getSettableDraweeHierarchy().setImage(drawable, 1.0f, wasImmediate);
                        reportSuccess(id, image, dataSource);
                    } else if (deliverTempResult) {
                        logMessageAndImage("set_temporary_result @ onNewResult", image);
                        getSettableDraweeHierarchy().setImage(drawable, 1.0f, wasImmediate);
                        reportSuccess(id, image, dataSource);
                    } else {
                        logMessageAndImage("set_intermediate_result @ onNewResult", image);
                        getSettableDraweeHierarchy().setImage(drawable, progress, wasImmediate);
                        reportIntermediateSet(id, image);
                    }
                    if (previousDrawable != null && previousDrawable != drawable) {
                        releaseDrawable(previousDrawable);
                    }
                    if (previousImage != null && previousImage != image) {
                        logMessageAndImage("release_previous_result @ onNewResult", previousImage);
                        releaseImage(previousImage);
                    }
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                } catch (Throwable th) {
                    if (previousDrawable != null && previousDrawable != drawable) {
                        releaseDrawable(previousDrawable);
                    }
                    if (previousImage != null && previousImage != image) {
                        logMessageAndImage("release_previous_result @ onNewResult", previousImage);
                        releaseImage(previousImage);
                    }
                    throw th;
                }
            } catch (Exception exception) {
                logMessageAndImage("drawable_failed @ onNewResult", image);
                releaseImage(image);
                onFailureInternal(id, dataSource, exception, isFinished);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            }
        } catch (Throwable exception2) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw exception2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFailureInternal(String id, DataSource<T> dataSource, Throwable throwable, boolean isFinished) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("AbstractDraweeController#onFailureInternal");
        }
        if (!isExpectedDataSource(id, dataSource)) {
            logMessageAndFailure("ignore_old_datasource @ onFailure", throwable);
            dataSource.close();
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
                return;
            }
            return;
        }
        this.mEventTracker.recordEvent(isFinished ? DraweeEventTracker.Event.ON_DATASOURCE_FAILURE : DraweeEventTracker.Event.ON_DATASOURCE_FAILURE_INT);
        if (isFinished) {
            logMessageAndFailure("final_failed @ onFailure", throwable);
            this.mDataSource = null;
            this.mHasFetchFailed = true;
            SettableDraweeHierarchy hierarchy = this.mSettableDraweeHierarchy;
            if (hierarchy != null) {
                if (this.mRetainImageOnFailure && this.mDrawable != null) {
                    hierarchy.setImage(this.mDrawable, 1.0f, true);
                } else if (shouldRetryOnTap()) {
                    hierarchy.setRetry(throwable);
                } else {
                    hierarchy.setFailure(throwable);
                }
            }
            reportFailure(throwable, dataSource);
        } else {
            logMessageAndFailure("intermediate_failed @ onFailure", throwable);
            reportIntermediateFailure(throwable);
        }
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProgressUpdateInternal(String id, DataSource<T> dataSource, float progress, boolean isFinished) {
        if (!isExpectedDataSource(id, dataSource)) {
            logMessageAndFailure("ignore_old_datasource @ onProgress", null);
            dataSource.close();
        } else if (!isFinished) {
            this.mSettableDraweeHierarchy.setProgress(progress, false);
        }
    }

    private boolean isExpectedDataSource(String id, DataSource<T> dataSource) {
        if (dataSource == null && this.mDataSource == null) {
            return true;
        }
        return id.equals(this.mId) && dataSource == this.mDataSource && this.mIsRequestSubmitted;
    }

    private void logMessageAndImage(String messageAndMethod, T image) {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: %s: image: %s %x", Integer.valueOf(System.identityHashCode(this)), this.mId, messageAndMethod, getImageClass(image), Integer.valueOf(getImageHash(image)));
        }
    }

    private void logMessageAndFailure(String messageAndMethod, Throwable throwable) {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: %s: failure: %s", Integer.valueOf(System.identityHashCode(this)), this.mId, messageAndMethod, throwable);
        }
    }

    @Override // com.facebook.drawee.interfaces.DraweeController
    @Nullable
    public Animatable getAnimatable() {
        if (this.mDrawable instanceof Animatable) {
            return (Animatable) this.mDrawable;
        }
        return null;
    }

    protected String getImageClass(@Nullable T image) {
        return image != null ? image.getClass().getSimpleName() : "<null>";
    }

    protected int getImageHash(@Nullable T image) {
        return System.identityHashCode(image);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("isAttached", this.mIsAttached).add("isRequestSubmitted", this.mIsRequestSubmitted).add("hasFetchFailed", this.mHasFetchFailed).add("fetchedImage", getImageHash(this.mFetchedImage)).add("events", this.mEventTracker.toString()).toString();
    }

    @Nullable
    protected T getCachedImage() {
        return null;
    }

    protected void onImageLoadedFromCacheImmediately(String id, T cachedImage) {
    }

    protected void reportSubmit(DataSource<T> dataSource, @Nullable INFO info) {
        getControllerListener().onSubmit(this.mId, this.mCallerContext);
        getControllerListener2().onSubmit(this.mId, this.mCallerContext, obtainExtras(dataSource, info, getMainUri()));
    }

    private void reportIntermediateSet(String id, @Nullable T image) {
        INFO info = getImageInfo(image);
        getControllerListener().onIntermediateImageSet(id, info);
        getControllerListener2().onIntermediateImageSet(id, info);
    }

    private void reportIntermediateFailure(Throwable throwable) {
        getControllerListener().onIntermediateImageFailed(this.mId, throwable);
        getControllerListener2().onIntermediateImageFailed(this.mId);
    }

    private void reportSuccess(String id, @Nullable T image, @Nullable DataSource<T> dataSource) {
        INFO info = getImageInfo(image);
        getControllerListener().onFinalImageSet(id, info, getAnimatable());
        getControllerListener2().onFinalImageSet(id, info, obtainExtras(dataSource, info, (Uri) null));
    }

    private void reportFailure(Throwable throwable, @Nullable DataSource<T> dataSource) {
        ControllerListener2.Extras extras = obtainExtras(dataSource, (Object) null, (Uri) null);
        getControllerListener().onFailure(this.mId, throwable);
        getControllerListener2().onFailure(this.mId, throwable, extras);
    }

    private void reportRelease(@Nullable Map<String, Object> datasourceExtras, @Nullable Map<String, Object> imageExtras) {
        getControllerListener().onRelease(this.mId);
        getControllerListener2().onRelease(this.mId, obtainExtras(datasourceExtras, imageExtras, (Uri) null));
    }

    private ControllerListener2.Extras obtainExtras(@Nullable Map<String, Object> datasourceExtras, @Nullable Map<String, Object> imageExtras, @Nullable Uri mainUri) {
        String scaleType;
        PointF focusPoint;
        SettableDraweeHierarchy hierarchy = this.mSettableDraweeHierarchy;
        if (!(hierarchy instanceof GenericDraweeHierarchy)) {
            scaleType = null;
            focusPoint = null;
        } else {
            String scaleType2 = String.valueOf(((GenericDraweeHierarchy) hierarchy).getActualImageScaleType());
            PointF focusPoint2 = ((GenericDraweeHierarchy) hierarchy).getActualImageFocusPoint();
            scaleType = scaleType2;
            focusPoint = focusPoint2;
        }
        return MiddlewareUtils.obtainExtras(COMPONENT_EXTRAS, SHORTCUT_EXTRAS, datasourceExtras, null, getDimensions(), scaleType, focusPoint, imageExtras, getCallerContext(), isLogWithHighSamplingRate(), mainUri);
    }

    @Nullable
    protected Uri getMainUri() {
        return null;
    }

    private ControllerListener2.Extras obtainExtras(@Nullable DataSource<T> datasource, @Nullable INFO info, @Nullable Uri mainUri) {
        return obtainExtras(datasource == null ? null : datasource.getExtras(), obtainExtrasFromImage(info), mainUri);
    }

    @Nullable
    private Rect getDimensions() {
        if (this.mSettableDraweeHierarchy == null) {
            return null;
        }
        return this.mSettableDraweeHierarchy.getBounds();
    }

    private SettableDraweeHierarchy getSettableDraweeHierarchy() {
        SettableDraweeHierarchy hierarchy = this.mSettableDraweeHierarchy;
        if (hierarchy == null) {
            throw new IllegalStateException("mSettableDraweeHierarchy is null; Caller context: " + this.mCallerContext);
        }
        return hierarchy;
    }
}
