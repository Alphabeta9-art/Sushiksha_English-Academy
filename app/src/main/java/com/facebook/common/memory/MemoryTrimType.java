package com.facebook.common.memory;

/* JADX INFO: loaded from: classes11.dex */
public enum MemoryTrimType {
    OnCloseToDalvikHeapLimit(0.5d),
    OnSystemMemoryCriticallyLowWhileAppInForeground(1.0d),
    OnSystemLowMemoryWhileAppInForeground(0.5d),
    OnSystemLowMemoryWhileAppInBackgroundLowSeverity(1.0d),
    OnAppBackgrounded(1.0d),
    OnJavaMemoryRed(1.0d),
    OnJavaMemoryYellow(0.5d),
    OnSystemMemoryRed(1.0d),
    OnSystemMemoryYellow(0.5d);

    private double mSuggestedTrimRatio;

    MemoryTrimType(double suggestedTrimRatio) {
        this.mSuggestedTrimRatio = suggestedTrimRatio;
    }

    public double getSuggestedTrimRatio() {
        return this.mSuggestedTrimRatio;
    }
}
