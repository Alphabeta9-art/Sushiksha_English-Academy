package com.cloudinary.android.uploadwidget.ui.imageview.gestures;

import android.graphics.Rect;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes11.dex */
class CropDraggingHandler extends CropOverlayGestureHandler {
    private final CropOverlayGestureCallback listener;

    CropDraggingHandler(Rect overlay, CropOverlayGestureCallback listener) {
        super(overlay);
        this.listener = listener;
    }

    @Override // com.cloudinary.android.uploadwidget.ui.imageview.gestures.CropOverlayGestureHandler, com.cloudinary.android.uploadwidget.ui.imageview.gestures.CropGestureHandler
    public void handleTouchEvent(MotionEvent event, boolean isAspectRatioLocked) {
        this.bounds.set(this.overlay);
        this.bounds.inset(getGestureRegionWidth(), getGestureRegionHeight());
        super.handleTouchEvent(event, isAspectRatioLocked);
    }

    @Override // com.cloudinary.android.uploadwidget.ui.imageview.gestures.CropOverlayGestureHandler
    public void handleGesture(MotionEvent event, boolean isAspectRatioLocked) {
        float distanceX = event.getX() - this.prevTouchEventPoint.x;
        float distanceY = event.getY() - this.prevTouchEventPoint.y;
        if (this.listener != null) {
            this.listener.onOverlayDragged((int) distanceX, (int) distanceY);
        }
    }
}
