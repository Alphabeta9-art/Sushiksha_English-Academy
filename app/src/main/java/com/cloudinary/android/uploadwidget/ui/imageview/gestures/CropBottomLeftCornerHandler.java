package com.cloudinary.android.uploadwidget.ui.imageview.gestures;

import android.graphics.Rect;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes11.dex */
class CropBottomLeftCornerHandler extends CropOverlayGestureHandler {
    private final CropOverlayGestureCallback listener;

    public CropBottomLeftCornerHandler(Rect overlay, CropOverlayGestureCallback listener) {
        super(overlay);
        this.listener = listener;
    }

    @Override // com.cloudinary.android.uploadwidget.ui.imageview.gestures.CropOverlayGestureHandler, com.cloudinary.android.uploadwidget.ui.imageview.gestures.CropGestureHandler
    public void handleTouchEvent(MotionEvent event, boolean isAspectRatioLocked) {
        this.bounds.set(this.overlay.left - getGestureRegionWidth(), this.overlay.bottom - getGestureRegionHeight(), this.overlay.left + getGestureRegionWidth(), this.overlay.bottom + getGestureRegionHeight());
        super.handleTouchEvent(event, isAspectRatioLocked);
    }

    @Override // com.cloudinary.android.uploadwidget.ui.imageview.gestures.CropOverlayGestureHandler
    public void handleGesture(MotionEvent event, boolean isAspectRatioLocked) {
        int left = this.overlay.left + ((int) (event.getX() - this.prevTouchEventPoint.x));
        int top = this.overlay.top;
        int right = this.overlay.right;
        int bottom = this.overlay.bottom + ((int) (event.getY() - this.prevTouchEventPoint.y));
        if (isAspectRatioLocked) {
            top += left - this.overlay.left;
            right += bottom - this.overlay.bottom;
        }
        if (this.listener != null) {
            this.listener.onOverlaySizeChanged(left, top, right, bottom);
        }
    }
}
