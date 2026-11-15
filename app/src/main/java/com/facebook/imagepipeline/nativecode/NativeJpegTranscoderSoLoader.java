package com.facebook.imagepipeline.nativecode;

import android.os.Build;
import com.facebook.soloader.nativeloader.NativeLoader;

/* JADX INFO: loaded from: classes11.dex */
public class NativeJpegTranscoderSoLoader {
    private static boolean sInitialized;

    public static synchronized void ensure() {
        if (!sInitialized) {
            int i = Build.VERSION.SDK_INT;
            NativeLoader.loadLibrary("native-imagetranscoder");
            sInitialized = true;
        }
    }
}
