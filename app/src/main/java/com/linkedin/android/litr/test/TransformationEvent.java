package com.linkedin.android.litr.test;

/* JADX INFO: loaded from: classes13.dex */
public class TransformationEvent {
    public static final int TYPE_CANCELLED = 4;
    public static final int TYPE_COMPLETED = 2;
    public static final int TYPE_ERROR = 3;
    public static final int TYPE_PROGRESS = 1;
    public static final int TYPE_START = 0;
    public final Throwable cause;
    public final String id;
    public final float progress;
    public final int type;

    public TransformationEvent(String id, int type, float progress, Throwable cause) {
        this.id = id;
        this.type = type;
        this.progress = progress;
        this.cause = cause;
    }
}
