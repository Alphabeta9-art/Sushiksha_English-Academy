package com.facebook.cache.common;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class NoOpCacheErrorLogger implements CacheErrorLogger {

    @Nullable
    private static NoOpCacheErrorLogger sInstance = null;

    private NoOpCacheErrorLogger() {
    }

    public static synchronized NoOpCacheErrorLogger getInstance() {
        if (sInstance == null) {
            sInstance = new NoOpCacheErrorLogger();
        }
        return sInstance;
    }

    @Override // com.facebook.cache.common.CacheErrorLogger
    public void logError(CacheErrorLogger.CacheErrorCategory category, Class<?> clazz, String message, @Nullable Throwable throwable) {
    }
}
