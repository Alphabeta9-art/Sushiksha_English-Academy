package com.facebook.imagepipeline.cache;

import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;

/* JADX INFO: loaded from: classes11.dex */
public class NativeMemoryCacheTrimStrategy implements MemoryCache.CacheTrimStrategy {
    private static final String TAG = "NativeMemoryCacheTrimStrategy";

    @Override // com.facebook.imagepipeline.cache.MemoryCache.CacheTrimStrategy
    public double getTrimRatio(MemoryTrimType trimType) {
        switch (trimType) {
            case OnCloseToDalvikHeapLimit:
                return 0.0d;
            case OnAppBackgrounded:
            case OnSystemMemoryCriticallyLowWhileAppInForeground:
            case OnSystemLowMemoryWhileAppInForeground:
            case OnSystemLowMemoryWhileAppInBackgroundLowSeverity:
                return 1.0d;
            default:
                FLog.wtf(TAG, "unknown trim type: %s", trimType);
                return 0.0d;
        }
    }
}
