package com.facebook.drawee.debug.listener;

import android.graphics.drawable.Animatable;
import com.facebook.drawee.controller.BaseControllerListener;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class ImageLoadingTimeControllerListener extends BaseControllerListener {

    @Nullable
    private ImageLoadingTimeListener mImageLoadingTimeListener;
    private long mRequestSubmitTimeMs = -1;
    private long mFinalImageSetTimeMs = -1;

    public ImageLoadingTimeControllerListener(@Nullable ImageLoadingTimeListener imageLoadingTimeListener) {
        this.mImageLoadingTimeListener = imageLoadingTimeListener;
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public void onSubmit(String id, Object callerContext) {
        this.mRequestSubmitTimeMs = System.currentTimeMillis();
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public void onFinalImageSet(String id, @Nullable Object imageInfo, @Nullable Animatable animatable) {
        this.mFinalImageSetTimeMs = System.currentTimeMillis();
        if (this.mImageLoadingTimeListener != null) {
            this.mImageLoadingTimeListener.onFinalImageSet(this.mFinalImageSetTimeMs - this.mRequestSubmitTimeMs);
        }
    }
}
