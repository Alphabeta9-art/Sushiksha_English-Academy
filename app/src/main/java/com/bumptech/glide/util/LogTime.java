package com.bumptech.glide.util;

import android.os.Build;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes11.dex */
public final class LogTime {
    private static final double MILLIS_MULTIPLIER;

    static {
        int i = Build.VERSION.SDK_INT;
        MILLIS_MULTIPLIER = 1.0d / Math.pow(10.0d, 6.0d);
    }

    private LogTime() {
    }

    public static long getLogTime() {
        int i = Build.VERSION.SDK_INT;
        return SystemClock.elapsedRealtimeNanos();
    }

    public static double getElapsedMillis(long logTime) {
        return (getLogTime() - logTime) * MILLIS_MULTIPLIER;
    }
}
