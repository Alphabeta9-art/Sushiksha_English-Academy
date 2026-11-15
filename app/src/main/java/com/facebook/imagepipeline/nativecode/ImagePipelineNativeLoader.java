package com.facebook.imagepipeline.nativecode;

import com.facebook.soloader.nativeloader.NativeLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public class ImagePipelineNativeLoader {
    public static final List<String> DEPENDENCIES;
    public static final String DSO_NAME = "imagepipeline";

    static {
        List<String> dependencies = new ArrayList<>();
        DEPENDENCIES = Collections.unmodifiableList(dependencies);
    }

    public static void load() {
        NativeLoader.loadLibrary(DSO_NAME);
    }
}
