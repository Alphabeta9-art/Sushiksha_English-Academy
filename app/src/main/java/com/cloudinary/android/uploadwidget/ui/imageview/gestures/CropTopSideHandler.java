package com.cloudinary.android.uploadwidget.ui.imageview.gestures;

import android.graphics.Rect;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes11.dex */
class CropTopSideHandler extends CropOverlayGestureHandler {
    private final CropOverlayGestureCallback listener;

    CropTopSideHandler(Rect overlay, CropOverlayGestureCallback listener) {
        super(overlay);
        this.listener = listener;
    }

    @Override // com.cloudinary.android.uploadwidget.ui.imageview.gestures.CropOverlayGestureHandler, com.cloudinary.android.uploadwidget.ui.imageview.gestures.CropGestureHandler
    public void handleTouchEvent(MotionEvent event, boolean isAspectRatioLocked) {
        this.bounds.set(this.overlay.left + getGestureRegionWidth(), this.overlay.top - getGestureRegionHeight(), this.overlay.right - getGestureRegionWidth(), this.overlay.top + getGestureRegionHeight());
        super.handleTouchEvent(event, isAspectRatioLocked);
    }

    @Override // com.cloudinary.android.uploadwidget.ui.imageview.gestures.CropOverlayGestureHandler
    public void handleGesture(MotionEvent event, boolean isAspectRatioLocked) {
        int left = this.overlay.left;
        int top = this.overlay.top + ((int) (event.getY() - this.prevTouchEventPoint.y));
        int right = this.overlay.right;
        int bottom = this.overlay.bottom;
        if (isAspectRatioLocked) {
            left = (int) (left + ((top - this.overlay.top) / 2.0f));
            right = (int) (right - ((top - this.overlay.top) / 2.0f));
        }
        if (this.listener != null) {
            this.listener.onOverlaySizeChanged(left, top, right, bottom);
        }
    }
}
