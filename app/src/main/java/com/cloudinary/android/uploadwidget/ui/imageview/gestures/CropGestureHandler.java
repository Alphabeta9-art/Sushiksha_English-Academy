package com.cloudinary.android.uploadwidget.ui.imageview.gestures;

import android.view.MotionEvent;

/* JADX INFO: loaded from: classes11.dex */
interface CropGestureHandler {
    void handleTouchEvent(MotionEvent motionEvent, boolean z);

    void setNext(CropGestureHandler cropGestureHandler);
}
