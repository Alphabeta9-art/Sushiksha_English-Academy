package com.facebook.soloader.nativeloader;

import java.io.IOException;

/* JADX INFO: loaded from: classes11.dex */
public class NativeLoader {
    private static NativeLoaderDelegate sDelegate;

    private NativeLoader() {
    }

    public static boolean loadLibrary(String shortName) {
        return loadLibrary(shortName, 0);
    }

    public static boolean loadLibrary(String shortName, int flags) {
        synchronized (NativeLoader.class) {
            if (sDelegate == null) {
                throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
            }
        }
        return sDelegate.loadLibrary(shortName, flags);
    }

    public static String getLibraryPath(String shortName) throws IOException {
        synchronized (NativeLoader.class) {
            if (sDelegate == null) {
                throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
            }
        }
        return sDelegate.getLibraryPath(shortName);
    }

    public static int getSoSourcesVersion() {
        synchronized (NativeLoader.class) {
            if (sDelegate == null) {
                throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
            }
        }
        return sDelegate.getSoSourcesVersion();
    }

    public static void init(NativeLoaderDelegate delegate) {
        synchronized (NativeLoader.class) {
            if (sDelegate != null) {
                throw new IllegalStateException("Cannot re-initialize NativeLoader.");
            }
            sDelegate = delegate;
        }
    }

    public static boolean isInitialized() {
        boolean z;
        synchronized (NativeLoader.class) {
            z = sDelegate != null;
        }
        return z;
    }

    public static void initIfUninitialized(NativeLoaderDelegate delegate) {
        if (!isInitialized()) {
            init(delegate);
        }
    }
}
