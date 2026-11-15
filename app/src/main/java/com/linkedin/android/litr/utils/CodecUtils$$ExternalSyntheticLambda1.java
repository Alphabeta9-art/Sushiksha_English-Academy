package com.linkedin.android.litr.utils;

import com.android.tools.r8.annotations.LambdaMethod;
import com.android.tools.r8.annotations.SynthesizedClassV2;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes13.dex */
@LambdaMethod(holder = "Lcom/linkedin/android/litr/utils/CodecUtils;", method = "lambda$findCodecForFormatOrType$1", proto = "(ZLjava/lang/String;)Landroid/media/MediaCodec;")
@SynthesizedClassV2(apiLevel = -2, kind = 28, versionHash = "f3a348d7d571cfc3f89005b557595f12bc85b7451dde524d9d228b1d59adcb6b")
public final /* synthetic */ class CodecUtils$$ExternalSyntheticLambda1 implements Callable {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ CodecUtils$$ExternalSyntheticLambda1(boolean z, String str) {
        this.f$0 = z;
        this.f$1 = str;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return CodecUtils.lambda$findCodecForFormatOrType$1(this.f$0, this.f$1);
    }
}
