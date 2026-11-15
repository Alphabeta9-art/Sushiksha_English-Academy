package com.linkedin.android.litr.utils;

/* JADX INFO: loaded from: classes13.dex */
class TimeUtils {
    TimeUtils() {
    }

    public static float microsToSeconds(long timeUs) {
        return timeUs / 1000000.0f;
    }

    public static float millisToSeconds(long timeMs) {
        return timeMs / 1000.0f;
    }
}
