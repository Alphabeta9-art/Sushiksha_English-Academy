package com.facebook.drawee.backends.pipeline.info.internal;

import android.graphics.drawable.Animatable;
import com.facebook.common.time.MonotonicClock;
import com.facebook.drawee.backends.pipeline.info.ImagePerfMonitor;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.fresco.ui.common.DimensionsInfo;
import com.facebook.fresco.ui.common.ImageLoadStatus;
import com.facebook.fresco.ui.common.ImagePerfState;
import com.facebook.fresco.ui.common.OnDrawControllerListener;
import com.facebook.fresco.ui.common.VisibilityState;
import com.facebook.imagepipeline.image.ImageInfo;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class ImagePerfControllerListener extends BaseControllerListener<ImageInfo> implements OnDrawControllerListener<ImageInfo> {
    private final MonotonicClock mClock;
    private final ImagePerfMonitor mImagePerfMonitor;
    private final ImagePerfState mImagePerfState;

    public ImagePerfControllerListener(MonotonicClock clock, ImagePerfState imagePerfState, ImagePerfMonitor imagePerfMonitor) {
        this.mClock = clock;
        this.mImagePerfState = imagePerfState;
        this.mImagePerfMonitor = imagePerfMonitor;
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public void onSubmit(String id, Object callerContext) {
        long now = this.mClock.now();
        this.mImagePerfState.resetPointsTimestamps();
        this.mImagePerfState.setControllerSubmitTimeMs(now);
        this.mImagePerfState.setControllerId(id);
        this.mImagePerfState.setCallerContext(callerContext);
        this.mImagePerfMonitor.notifyStatusUpdated(this.mImagePerfState, ImageLoadStatus.REQUESTED);
        reportViewVisible(now);
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
        long now = this.mClock.now();
        this.mImagePerfState.setControllerIntermediateImageSetTimeMs(now);
        this.mImagePerfState.setControllerId(id);
        this.mImagePerfState.setImageInfo(imageInfo);
        this.mImagePerfMonitor.notifyStatusUpdated(this.mImagePerfState, ImageLoadStatus.INTERMEDIATE_AVAILABLE);
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
        long now = this.mClock.now();
        this.mImagePerfState.setControllerFinalImageSetTimeMs(now);
        this.mImagePerfState.setImageRequestEndTimeMs(now);
        this.mImagePerfState.setControllerId(id);
        this.mImagePerfState.setImageInfo(imageInfo);
        this.mImagePerfMonitor.notifyStatusUpdated(this.mImagePerfState, ImageLoadStatus.SUCCESS);
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public void onFailure(String id, Throwable throwable) {
        long now = this.mClock.now();
        this.mImagePerfState.setControllerFailureTimeMs(now);
        this.mImagePerfState.setControllerId(id);
        this.mImagePerfState.setErrorThrowable(throwable);
        this.mImagePerfMonitor.notifyStatusUpdated(this.mImagePerfState, ImageLoadStatus.ERROR);
        reportViewInvisible(now);
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public void onRelease(String id) {
        super.onRelease(id);
        long now = this.mClock.now();
        ImageLoadStatus lastImageLoadStatus = this.mImagePerfState.getImageLoadStatus();
        if (lastImageLoadStatus != ImageLoadStatus.SUCCESS && lastImageLoadStatus != ImageLoadStatus.ERROR && lastImageLoadStatus != ImageLoadStatus.DRAW) {
            this.mImagePerfState.setControllerCancelTimeMs(now);
            this.mImagePerfState.setControllerId(id);
            this.mImagePerfMonitor.notifyStatusUpdated(this.mImagePerfState, ImageLoadStatus.CANCELED);
        }
        reportViewInvisible(now);
    }

    @Override // com.facebook.fresco.ui.common.OnDrawControllerListener
    public void onImageDrawn(String id, ImageInfo info, DimensionsInfo dimensionsInfo) {
        this.mImagePerfState.setImageDrawTimeMs(this.mClock.now());
        this.mImagePerfState.setDimensionsInfo(dimensionsInfo);
        this.mImagePerfMonitor.notifyStatusUpdated(this.mImagePerfState, ImageLoadStatus.DRAW);
    }

    public void reportViewVisible(long now) {
        this.mImagePerfState.setVisible(true);
        this.mImagePerfState.setVisibilityEventTimeMs(now);
        this.mImagePerfMonitor.notifyListenersOfVisibilityStateUpdate(this.mImagePerfState, VisibilityState.VISIBLE);
    }

    private void reportViewInvisible(long time) {
        this.mImagePerfState.setVisible(false);
        this.mImagePerfState.setInvisibilityEventTimeMs(time);
        this.mImagePerfMonitor.notifyListenersOfVisibilityStateUpdate(this.mImagePerfState, VisibilityState.INVISIBLE);
    }
}
