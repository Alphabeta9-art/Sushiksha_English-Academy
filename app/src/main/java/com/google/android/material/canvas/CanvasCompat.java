package com.google.android.material.canvas;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Build;

/* JADX INFO: loaded from: classes12.dex */
public class CanvasCompat {
    private CanvasCompat() {
    }

    public static int saveLayerAlpha(Canvas canvas, RectF bounds, int alpha) {
        int i = Build.VERSION.SDK_INT;
        return canvas.saveLayerAlpha(bounds, alpha);
    }

    public static int saveLayerAlpha(Canvas canvas, float left, float top, float right, float bottom, int alpha) {
        int i = Build.VERSION.SDK_INT;
        return canvas.saveLayerAlpha(left, top, right, bottom, alpha);
    }
}
