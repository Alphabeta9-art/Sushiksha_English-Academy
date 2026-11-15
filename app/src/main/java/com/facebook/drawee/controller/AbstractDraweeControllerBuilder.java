package com.facebook.drawee.controller;

import android.content.Context;
import android.graphics.drawable.Animatable;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.datasource.FirstAvailableDataSourceSupplier;
import com.facebook.datasource.IncreasingQualityDataSourceSupplier;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import com.facebook.fresco.ui.common.ControllerListener2;
import com.facebook.fresco.ui.common.LoggingListener;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public abstract class AbstractDraweeControllerBuilder<BUILDER extends AbstractDraweeControllerBuilder<BUILDER, REQUEST, IMAGE, INFO>, REQUEST, IMAGE, INFO> implements SimpleDraweeControllerBuilder {
    private boolean mAutoPlayAnimations;
    private final Set<ControllerListener> mBoundControllerListeners;
    private final Set<ControllerListener2> mBoundControllerListeners2;

    @Nullable
    private Object mCallerContext;

    @Nullable
    private String mContentDescription;
    private final Context mContext;

    @Nullable
    private ControllerListener<? super INFO> mControllerListener;

    @Nullable
    private ControllerViewportVisibilityListener mControllerViewportVisibilityListener;

    @Nullable
    private Supplier<DataSource<IMAGE>> mDataSourceSupplier;

    @Nullable
    private REQUEST mImageRequest;
    private boolean mLogWithHighSamplingRate = false;

    @Nullable
    private LoggingListener mLoggingListener;

    @Nullable
    private REQUEST mLowResImageRequest;

    @Nullable
    private REQUEST[] mMultiImageRequests;

    @Nullable
    private DraweeController mOldController;
    private boolean mRetainImageOnFailure;
    private boolean mTapToRetryEnabled;
    private boolean mTryCacheOnlyFirst;
    private static final ControllerListener<Object> sAutoPlayAnimationsListener = new BaseControllerListener<Object>() { // from class: com.facebook.drawee.controller.AbstractDraweeControllerBuilder.1
        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onFinalImageSet(String id, @Nullable Object info, @Nullable Animatable anim) {
            if (anim != null) {
                anim.start();
            }
        }
    };
    private static final NullPointerException NO_REQUEST_EXCEPTION = new NullPointerException("No image request was specified!");
    private static final AtomicLong sIdCounter = new AtomicLong();

    public enum CacheLevel {
        FULL_FETCH,
        DISK_CACHE,
        BITMAP_MEMORY_CACHE
    }

    protected abstract DataSource<IMAGE> getDataSourceForRequest(DraweeController draweeController, String str, REQUEST request, Object obj, CacheLevel cacheLevel);

    protected abstract AbstractDraweeController obtainController();

    protected AbstractDraweeControllerBuilder(Context context, Set<ControllerListener> boundControllerListeners, Set<ControllerListener2> boundControllerListeners2) {
        this.mContext = context;
        this.mBoundControllerListeners = boundControllerListeners;
        this.mBoundControllerListeners2 = boundControllerListeners2;
        init();
    }

    private void init() {
        this.mCallerContext = null;
        this.mImageRequest = null;
        this.mLowResImageRequest = null;
        this.mMultiImageRequests = null;
        this.mTryCacheOnlyFirst = true;
        this.mControllerListener = null;
        this.mLoggingListener = null;
        this.mControllerViewportVisibilityListener = null;
        this.mTapToRetryEnabled = false;
        this.mAutoPlayAnimations = false;
        this.mLogWithHighSamplingRate = false;
        this.mOldController = null;
        this.mContentDescription = null;
    }

    public BUILDER reset() {
        init();
        return (BUILDER) getThis();
    }

    @Override // com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder
    public BUILDER setCallerContext(Object obj) {
        this.mCallerContext = obj;
        return (BUILDER) getThis();
    }

    @Nullable
    public Object getCallerContext() {
        return this.mCallerContext;
    }

    public BUILDER setImageRequest(@Nullable REQUEST request) {
        this.mImageRequest = request;
        return (BUILDER) getThis();
    }

    @Nullable
    public REQUEST getImageRequest() {
        return this.mImageRequest;
    }

    public BUILDER setLowResImageRequest(@Nullable REQUEST request) {
        this.mLowResImageRequest = request;
        return (BUILDER) getThis();
    }

    @Nullable
    public REQUEST getLowResImageRequest() {
        return this.mLowResImageRequest;
    }

    public BUILDER setFirstAvailableImageRequests(REQUEST[] requestArr) {
        return (BUILDER) setFirstAvailableImageRequests(requestArr, true);
    }

    public BUILDER setFirstAvailableImageRequests(REQUEST[] requestArr, boolean z) {
        Preconditions.checkArgument(requestArr == null || requestArr.length > 0, "No requests specified!");
        this.mMultiImageRequests = requestArr;
        this.mTryCacheOnlyFirst = z;
        return (BUILDER) getThis();
    }

    @Nullable
    public REQUEST[] getFirstAvailableImageRequests() {
        return this.mMultiImageRequests;
    }

    public BUILDER setDataSourceSupplier(@Nullable Supplier<DataSource<IMAGE>> supplier) {
        this.mDataSourceSupplier = supplier;
        return (BUILDER) getThis();
    }

    @Nullable
    public Supplier<DataSource<IMAGE>> getDataSourceSupplier() {
        return this.mDataSourceSupplier;
    }

    public BUILDER setTapToRetryEnabled(boolean z) {
        this.mTapToRetryEnabled = z;
        return (BUILDER) getThis();
    }

    public boolean getTapToRetryEnabled() {
        return this.mTapToRetryEnabled;
    }

    public BUILDER setRetainImageOnFailure(boolean z) {
        this.mRetainImageOnFailure = z;
        return (BUILDER) getThis();
    }

    public boolean getRetainImageOnFailure() {
        return this.mRetainImageOnFailure;
    }

    public BUILDER setAutoPlayAnimations(boolean z) {
        this.mAutoPlayAnimations = z;
        return (BUILDER) getThis();
    }

    public boolean getAutoPlayAnimations() {
        return this.mAutoPlayAnimations;
    }

    public boolean isLogWithHighSamplingRate() {
        return this.mLogWithHighSamplingRate;
    }

    public BUILDER setLogWithHighSamplingRate(boolean z) {
        this.mLogWithHighSamplingRate = z;
        return (BUILDER) getThis();
    }

    public BUILDER setControllerListener(@Nullable ControllerListener<? super INFO> controllerListener) {
        this.mControllerListener = controllerListener;
        return (BUILDER) getThis();
    }

    public BUILDER setLoggingListener(@Nullable LoggingListener loggingListener) {
        this.mLoggingListener = loggingListener;
        return (BUILDER) getThis();
    }

    @Nullable
    public LoggingListener getLoggingListener() {
        return this.mLoggingListener;
    }

    @Nullable
    public ControllerListener<? super INFO> getControllerListener() {
        return this.mControllerListener;
    }

    public BUILDER setControllerViewportVisibilityListener(@Nullable ControllerViewportVisibilityListener controllerViewportVisibilityListener) {
        this.mControllerViewportVisibilityListener = controllerViewportVisibilityListener;
        return (BUILDER) getThis();
    }

    @Nullable
    public ControllerViewportVisibilityListener getControllerViewportVisibilityListener() {
        return this.mControllerViewportVisibilityListener;
    }

    public BUILDER setContentDescription(String str) {
        this.mContentDescription = str;
        return (BUILDER) getThis();
    }

    @Nullable
    public String getContentDescription() {
        return this.mContentDescription;
    }

    @Override // com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder
    public BUILDER setOldController(@Nullable DraweeController draweeController) {
        this.mOldController = draweeController;
        return (BUILDER) getThis();
    }

    @Nullable
    public DraweeController getOldController() {
        return this.mOldController;
    }

    @Override // com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder
    public AbstractDraweeController build() {
        validate();
        if (this.mImageRequest == null && this.mMultiImageRequests == null && this.mLowResImageRequest != null) {
            this.mImageRequest = this.mLowResImageRequest;
            this.mLowResImageRequest = null;
        }
        return buildController();
    }

    protected void validate() {
        boolean z = false;
        Preconditions.checkState(this.mMultiImageRequests == null || this.mImageRequest == null, "Cannot specify both ImageRequest and FirstAvailableImageRequests!");
        if (this.mDataSourceSupplier == null || (this.mMultiImageRequests == null && this.mImageRequest == null && this.mLowResImageRequest == null)) {
            z = true;
        }
        Preconditions.checkState(z, "Cannot specify DataSourceSupplier with other ImageRequests! Use one or the other.");
    }

    protected AbstractDraweeController buildController() {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("AbstractDraweeControllerBuilder#buildController");
        }
        AbstractDraweeController controller = obtainController();
        controller.setLogWithHighSamplingRate(isLogWithHighSamplingRate());
        controller.setRetainImageOnFailure(getRetainImageOnFailure());
        controller.setContentDescription(getContentDescription());
        controller.setControllerViewportVisibilityListener(getControllerViewportVisibilityListener());
        maybeBuildAndSetRetryManager(controller);
        maybeAttachListeners(controller);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        return controller;
    }

    protected static String generateUniqueControllerId() {
        return String.valueOf(sIdCounter.getAndIncrement());
    }

    protected Supplier<DataSource<IMAGE>> obtainDataSourceSupplier(DraweeController controller, String controllerId) {
        if (this.mDataSourceSupplier != null) {
            return this.mDataSourceSupplier;
        }
        Supplier<DataSource<IMAGE>> supplier = null;
        if (this.mImageRequest != null) {
            supplier = getDataSourceSupplierForRequest(controller, controllerId, this.mImageRequest);
        } else if (this.mMultiImageRequests != null) {
            supplier = getFirstAvailableDataSourceSupplier(controller, controllerId, this.mMultiImageRequests, this.mTryCacheOnlyFirst);
        }
        if (supplier != null && this.mLowResImageRequest != null) {
            List<Supplier<DataSource<IMAGE>>> suppliers = new ArrayList<>(2);
            suppliers.add(supplier);
            suppliers.add(getDataSourceSupplierForRequest(controller, controllerId, this.mLowResImageRequest));
            supplier = IncreasingQualityDataSourceSupplier.create(suppliers, false);
        }
        if (supplier == null) {
            Supplier<DataSource<IMAGE>> supplier2 = DataSources.getFailedDataSourceSupplier(NO_REQUEST_EXCEPTION);
            return supplier2;
        }
        return supplier;
    }

    protected Supplier<DataSource<IMAGE>> getFirstAvailableDataSourceSupplier(DraweeController controller, String controllerId, REQUEST[] imageRequests, boolean tryBitmapCacheOnlyFirst) {
        List<Supplier<DataSource<IMAGE>>> suppliers = new ArrayList<>(imageRequests.length * 2);
        if (tryBitmapCacheOnlyFirst) {
            for (REQUEST request : imageRequests) {
                suppliers.add(getDataSourceSupplierForRequest(controller, controllerId, request, CacheLevel.BITMAP_MEMORY_CACHE));
            }
        }
        for (REQUEST request2 : imageRequests) {
            suppliers.add(getDataSourceSupplierForRequest(controller, controllerId, request2));
        }
        return FirstAvailableDataSourceSupplier.create(suppliers);
    }

    protected Supplier<DataSource<IMAGE>> getDataSourceSupplierForRequest(DraweeController controller, String controllerId, REQUEST imageRequest) {
        return getDataSourceSupplierForRequest(controller, controllerId, imageRequest, CacheLevel.FULL_FETCH);
    }

    protected Supplier<DataSource<IMAGE>> getDataSourceSupplierForRequest(final DraweeController controller, final String controllerId, final REQUEST imageRequest, final CacheLevel cacheLevel) {
        final Object callerContext = getCallerContext();
        return new Supplier<DataSource<IMAGE>>() { // from class: com.facebook.drawee.controller.AbstractDraweeControllerBuilder.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<IMAGE> get() {
                return AbstractDraweeControllerBuilder.this.getDataSourceForRequest(controller, controllerId, imageRequest, callerContext, cacheLevel);
            }

            public String toString() {
                return Objects.toStringHelper(this).add("request", imageRequest.toString()).toString();
            }
        };
    }

    protected void maybeAttachListeners(AbstractDraweeController controller) {
        if (this.mBoundControllerListeners != null) {
            for (ControllerListener listener : this.mBoundControllerListeners) {
                controller.addControllerListener(listener);
            }
        }
        if (this.mBoundControllerListeners2 != null) {
            for (ControllerListener2 listener2 : this.mBoundControllerListeners2) {
                controller.addControllerListener2(listener2);
            }
        }
        if (this.mControllerListener != null) {
            controller.addControllerListener(this.mControllerListener);
        }
        if (this.mAutoPlayAnimations) {
            controller.addControllerListener(sAutoPlayAnimationsListener);
        }
    }

    protected void maybeBuildAndSetRetryManager(AbstractDraweeController controller) {
        if (!this.mTapToRetryEnabled) {
            return;
        }
        controller.getRetryManager().setTapToRetryEnabled(this.mTapToRetryEnabled);
        maybeBuildAndSetGestureDetector(controller);
    }

    protected void maybeBuildAndSetGestureDetector(AbstractDraweeController controller) {
        GestureDetector gestureDetector = controller.getGestureDetector();
        if (gestureDetector == null) {
            GestureDetector gestureDetector2 = GestureDetector.newInstance(this.mContext);
            controller.setGestureDetector(gestureDetector2);
        }
    }

    protected Context getContext() {
        return this.mContext;
    }

    protected final BUILDER getThis() {
        return this;
    }
}
