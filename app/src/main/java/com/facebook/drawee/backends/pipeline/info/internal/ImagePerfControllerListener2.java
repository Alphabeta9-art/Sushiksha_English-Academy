package com.facebook.drawee.backends.pipeline.info.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.time.MonotonicClock;
import com.facebook.drawee.drawable.VisibilityCallback;
import com.facebook.fresco.ui.common.BaseControllerListener2;
import com.facebook.fresco.ui.common.ControllerListener2;
import com.facebook.fresco.ui.common.DimensionsInfo;
import com.facebook.fresco.ui.common.ImageLoadStatus;
import com.facebook.fresco.ui.common.ImagePerfNotifier;
import com.facebook.fresco.ui.common.ImagePerfNotifierHolder;
import com.facebook.fresco.ui.common.ImagePerfState;
import com.facebook.fresco.ui.common.OnDrawControllerListener;
import com.facebook.fresco.ui.common.VisibilityState;
import com.facebook.imagepipeline.image.ImageInfo;
import java.io.Closeable;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class ImagePerfControllerListener2 extends BaseControllerListener2<ImageInfo> implements ImagePerfNotifierHolder, OnDrawControllerListener<ImageInfo>, Closeable, VisibilityCallback {
    private static final int WHAT_STATUS = 1;
    private static final int WHAT_VISIBILITY = 2;

    @Nullable
    private static LogHandler sHandler;
    private final Supplier<Boolean> mAsyncLogging;
    private final MonotonicClock mClock;
    private final ImagePerfNotifier mImagePerfNotifier;
    private final ImagePerfState mImagePerfState;

    @Nullable
    private ImagePerfNotifier mLocalImagePerfNotifier;
    private final boolean mReportVisibleOnSubmitAndRelease;

    static class LogHandler extends Handler implements ImagePerfNotifierHolder {

        @Nullable
        private ImagePerfNotifier mLocalNotifier;
        private final ImagePerfNotifier mNotifier;

        public LogHandler(Looper looper, ImagePerfNotifier notifier, @Nullable ImagePerfNotifier localNotifier) {
            super(looper);
            this.mNotifier = notifier;
            this.mLocalNotifier = localNotifier;
        }

        @Override // com.facebook.fresco.ui.common.ImagePerfNotifierHolder
        public void setImagePerfNotifier(@Nullable ImagePerfNotifier imagePerfNotifier) {
            this.mLocalNotifier = imagePerfNotifier;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            ImagePerfState state = (ImagePerfState) Preconditions.checkNotNull(msg.obj);
            ImagePerfNotifier localNotifier = this.mLocalNotifier;
            switch (msg.what) {
                case 1:
                    ImageLoadStatus imageLoadStatusFromVal = ImageLoadStatus.INSTANCE.fromInt(msg.arg1);
                    if (imageLoadStatusFromVal == null) {
                        throw new IllegalArgumentException("Invalid ImageLoadStatus value: " + msg.arg1);
                    }
                    this.mNotifier.notifyStatusUpdated(state, imageLoadStatusFromVal);
                    if (localNotifier != null) {
                        localNotifier.notifyStatusUpdated(state, imageLoadStatusFromVal);
                        return;
                    }
                    return;
                case 2:
                    VisibilityState visibilityStateFromVal = VisibilityState.INSTANCE.fromInt(msg.arg1);
                    if (visibilityStateFromVal == null) {
                        throw new IllegalArgumentException("Invalid VisibilityState value: " + msg.arg1);
                    }
                    this.mNotifier.notifyListenersOfVisibilityStateUpdate(state, visibilityStateFromVal);
                    if (localNotifier != null) {
                        localNotifier.notifyListenersOfVisibilityStateUpdate(state, visibilityStateFromVal);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public ImagePerfControllerListener2(MonotonicClock clock, ImagePerfState imagePerfState, ImagePerfNotifier globalImagePerfNotifier, Supplier<Boolean> asyncLogging) {
        this(clock, imagePerfState, globalImagePerfNotifier, asyncLogging, true);
    }

    public ImagePerfControllerListener2(MonotonicClock clock, ImagePerfState imagePerfState, ImagePerfNotifier globalImagePerfNotifier, Supplier<Boolean> asyncLogging, boolean reportVisibleOnSubmitAndRelease) {
        this.mLocalImagePerfNotifier = null;
        this.mClock = clock;
        this.mImagePerfState = imagePerfState;
        this.mImagePerfNotifier = globalImagePerfNotifier;
        this.mAsyncLogging = asyncLogging;
        this.mReportVisibleOnSubmitAndRelease = reportVisibleOnSubmitAndRelease;
    }

    @Override // com.facebook.fresco.ui.common.ImagePerfNotifierHolder
    public void setImagePerfNotifier(@Nullable ImagePerfNotifier imagePerfNotifier) {
        this.mLocalImagePerfNotifier = imagePerfNotifier;
        if (sHandler != null) {
            sHandler.setImagePerfNotifier(imagePerfNotifier);
        }
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onSubmit(String id, @Nullable Object callerContext, @Nullable ControllerListener2.Extras extraData) {
        long now = this.mClock.now();
        ImagePerfState state = this.mImagePerfState;
        state.resetPointsTimestamps();
        state.setControllerSubmitTimeMs(now);
        state.setControllerId(id);
        state.setCallerContext(callerContext);
        state.setExtraData(extraData);
        updateStatus(state, ImageLoadStatus.REQUESTED);
        if (this.mReportVisibleOnSubmitAndRelease) {
            reportViewVisible(state, now);
        }
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
        long now = this.mClock.now();
        ImagePerfState state = this.mImagePerfState;
        state.setControllerIntermediateImageSetTimeMs(now);
        state.setControllerId(id);
        state.setImageInfo(imageInfo);
        updateStatus(state, ImageLoadStatus.INTERMEDIATE_AVAILABLE);
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable ControllerListener2.Extras extraData) {
        long now = this.mClock.now();
        ImagePerfState state = this.mImagePerfState;
        state.setExtraData(extraData);
        state.setControllerFinalImageSetTimeMs(now);
        state.setImageRequestEndTimeMs(now);
        state.setControllerId(id);
        state.setImageInfo(imageInfo);
        updateStatus(state, ImageLoadStatus.SUCCESS);
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onFailure(String id, @Nullable Throwable throwable, @Nullable ControllerListener2.Extras extras) {
        long now = this.mClock.now();
        ImagePerfState state = this.mImagePerfState;
        state.setExtraData(extras);
        state.setControllerFailureTimeMs(now);
        state.setControllerId(id);
        state.setErrorThrowable(throwable);
        updateStatus(state, ImageLoadStatus.ERROR);
        reportViewInvisible(state, now);
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onRelease(String id, @Nullable ControllerListener2.Extras extras) {
        long now = this.mClock.now();
        ImagePerfState state = this.mImagePerfState;
        state.setExtraData(extras);
        state.setControllerId(id);
        ImageLoadStatus lastImageLoadStatus = state.getImageLoadStatus();
        if (lastImageLoadStatus != ImageLoadStatus.SUCCESS && lastImageLoadStatus != ImageLoadStatus.ERROR && lastImageLoadStatus != ImageLoadStatus.DRAW) {
            state.setControllerCancelTimeMs(now);
            updateStatus(state, ImageLoadStatus.CANCELED);
        }
        updateStatus(state, ImageLoadStatus.RELEASED);
        if (this.mReportVisibleOnSubmitAndRelease) {
            reportViewInvisible(state, now);
        }
    }

    @Override // com.facebook.fresco.ui.common.OnDrawControllerListener
    public void onImageDrawn(String id, ImageInfo info, DimensionsInfo dimensionsInfo) {
        ImagePerfState state = this.mImagePerfState;
        state.setControllerId(id);
        state.setImageDrawTimeMs(this.mClock.now());
        state.setDimensionsInfo(dimensionsInfo);
        updateStatus(state, ImageLoadStatus.DRAW);
    }

    public void reportViewVisible(ImagePerfState state, long now) {
        state.setVisible(true);
        state.setVisibilityEventTimeMs(now);
        updateVisibility(state, VisibilityState.VISIBLE);
    }

    public void resetState() {
        this.mImagePerfState.reset();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        resetState();
    }

    private void reportViewInvisible(ImagePerfState state, long time) {
        state.setVisible(false);
        state.setInvisibilityEventTimeMs(time);
        updateVisibility(state, VisibilityState.INVISIBLE);
    }

    private void updateStatus(ImagePerfState state, ImageLoadStatus imageLoadStatus) {
        state.setImageLoadStatus(imageLoadStatus);
        if (shouldDispatchAsync()) {
            Message msg = ((LogHandler) Preconditions.checkNotNull(sHandler)).obtainMessage();
            msg.what = 1;
            msg.arg1 = imageLoadStatus.getValue();
            msg.obj = state;
            sHandler.sendMessage(msg);
            return;
        }
        this.mImagePerfNotifier.notifyStatusUpdated(state, imageLoadStatus);
        ImagePerfNotifier localImagePerfNotifier = this.mLocalImagePerfNotifier;
        if (localImagePerfNotifier != null) {
            localImagePerfNotifier.notifyStatusUpdated(state, imageLoadStatus);
        }
    }

    private void updateVisibility(ImagePerfState state, VisibilityState visibilityState) {
        if (shouldDispatchAsync()) {
            Message msg = ((LogHandler) Preconditions.checkNotNull(sHandler)).obtainMessage();
            msg.what = 2;
            msg.arg1 = visibilityState.getValue();
            msg.obj = state;
            sHandler.sendMessage(msg);
            return;
        }
        this.mImagePerfNotifier.notifyListenersOfVisibilityStateUpdate(state, visibilityState);
        ImagePerfNotifier localImagePerfNotifier = this.mLocalImagePerfNotifier;
        if (localImagePerfNotifier != null) {
            localImagePerfNotifier.notifyListenersOfVisibilityStateUpdate(state, visibilityState);
        }
    }

    private synchronized void initHandler() {
        if (sHandler != null) {
            return;
        }
        HandlerThread handlerThread = new HandlerThread("ImagePerfControllerListener2Thread");
        handlerThread.start();
        Looper looper = (Looper) Preconditions.checkNotNull(handlerThread.getLooper());
        sHandler = new LogHandler(looper, this.mImagePerfNotifier, this.mLocalImagePerfNotifier);
    }

    private boolean shouldDispatchAsync() {
        boolean enabled = this.mAsyncLogging.get().booleanValue();
        if (enabled && sHandler == null) {
            initHandler();
        }
        return enabled;
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onEmptyEvent(Object callerContext) {
        ImagePerfState state = this.mImagePerfState;
        state.setImageLoadStatus(ImageLoadStatus.EMPTY_EVENT);
        this.mImagePerfNotifier.notifyStatusUpdated(state, ImageLoadStatus.EMPTY_EVENT);
        ImagePerfNotifier localImagePerfNotifier = this.mLocalImagePerfNotifier;
        if (localImagePerfNotifier != null) {
            localImagePerfNotifier.notifyStatusUpdated(state, ImageLoadStatus.EMPTY_EVENT);
        }
    }

    @Override // com.facebook.drawee.drawable.VisibilityCallback
    public void onVisibilityChange(boolean visible) {
        if (visible) {
            reportViewVisible(this.mImagePerfState, this.mClock.now());
        } else {
            reportViewInvisible(this.mImagePerfState, this.mClock.now());
        }
    }

    @Override // com.facebook.drawee.drawable.VisibilityCallback
    public void onDraw() {
    }
}
