package com.cloudinary.android.uploadwidget.ui.imageview.gestures;

import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes11.dex */
abstract class CropOverlayGestureHandler implements CropGestureHandler {
    private static final float GESTURE_REGION = 0.25f;
    private static final int MIN_GESTURE_REGION = 30;
    private boolean isStartedGesture;
    private CropGestureHandler nextHandler;
    protected final Rect overlay;
    protected final Rect bounds = new Rect();
    protected final PointF prevTouchEventPoint = new PointF();

    protected abstract void handleGesture(MotionEvent motionEvent, boolean z);

    public CropOverlayGestureHandler(Rect overlay) {
        this.overlay = overlay;
    }

    @Override // com.cloudinary.android.uploadwidget.ui.imageview.gestures.CropGestureHandler
    public void setNext(CropGestureHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override // com.cloudinary.android.uploadwidget.ui.imageview.gestures.CropGestureHandler
    public void handleTouchEvent(MotionEvent event, boolean isAspectRatioLocked) {
        switch (event.getAction()) {
            case 0:
                if (this.bounds.contains((int) event.getX(), (int) event.getY())) {
                    this.isStartedGesture = true;
                    this.prevTouchEventPoint.set(event.getX(), event.getY());
                    handleGesture(event, isAspectRatioLocked);
                } else {
                    this.isStartedGesture = false;
                    if (this.nextHandler != null) {
                        this.nextHandler.handleTouchEvent(event, isAspectRatioLocked);
                    }
                }
                break;
            case 1:
                this.isStartedGesture = false;
                break;
            case 2:
                if (this.isStartedGesture) {
                    handleGesture(event, isAspectRatioLocked);
                    this.prevTouchEventPoint.set(event.getX(), event.getY());
                } else if (this.nextHandler != null) {
                    this.nextHandler.handleTouchEvent(event, isAspectRatioLocked);
                }
                break;
        }
    }

    protected int getGestureRegionWidth() {
        return Math.max((int) (this.overlay.width() * GESTURE_REGION), 30);
    }

    protected int getGestureRegionHeight() {
        return Math.max((int) (this.overlay.height() * GESTURE_REGION), 30);
    }
}
