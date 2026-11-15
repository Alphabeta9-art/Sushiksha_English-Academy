package com.linkedin.android.litr.filter;

import android.graphics.PointF;

/* JADX INFO: loaded from: classes13.dex */
public class Transform {
    public final PointF position;
    public final float rotation;
    public final PointF size;

    public Transform(PointF size, PointF position, float rotation) {
        this.size = size;
        this.position = position;
        this.rotation = rotation;
    }
}
