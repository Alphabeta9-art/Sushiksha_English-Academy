package com.facebook.soloader;

import android.os.StrictMode;
import java.io.File;

/* JADX INFO: loaded from: classes11.dex */
public class NoopSoSource extends SoSource {
    @Override // com.facebook.soloader.SoSource
    public int loadLibrary(String soName, int loadFlags, StrictMode.ThreadPolicy threadPolicy) {
        return 1;
    }

    @Override // com.facebook.soloader.SoSource
    public File unpackLibrary(String soName) {
        throw new UnsupportedOperationException("unpacking not supported in test mode");
    }
}
