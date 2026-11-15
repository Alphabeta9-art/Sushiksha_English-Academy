package com.facebook.soloader;

import com.facebook.soloader.nativeloader.NativeLoaderDelegate;
import java.io.IOException;

/* JADX INFO: loaded from: classes11.dex */
public class NativeLoaderToSoLoaderDelegate implements NativeLoaderDelegate {
    @Override // com.facebook.soloader.nativeloader.NativeLoaderDelegate
    public boolean loadLibrary(String shortName, int flags) {
        int soLoaderFlags = 0 | ((flags & 1) != 0 ? 16 : 0);
        return SoLoader.loadLibrary(shortName, soLoaderFlags);
    }

    @Override // com.facebook.soloader.nativeloader.NativeLoaderDelegate
    public String getLibraryPath(String libName) throws IOException {
        return SoLoader.getLibraryPath(libName);
    }

    @Override // com.facebook.soloader.nativeloader.NativeLoaderDelegate
    public int getSoSourcesVersion() {
        return SoLoader.getSoSourcesVersion();
    }
}
