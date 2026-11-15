package io.grpc.okhttp;

import com.android.tools.r8.annotations.LambdaMethod;
import com.android.tools.r8.annotations.SynthesizedClassV2;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes13.dex */
@LambdaMethod(holder = "Lio/grpc/okhttp/OkHttpServerTransport;", method = "triggerForcefulClose", proto = "()V")
@SynthesizedClassV2(apiLevel = -2, kind = 28, versionHash = "f3a348d7d571cfc3f89005b557595f12bc85b7451dde524d9d228b1d59adcb6b")
public final /* synthetic */ class OkHttpServerTransport$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ OkHttpServerTransport f$0;

    public /* synthetic */ OkHttpServerTransport$$ExternalSyntheticLambda0(OkHttpServerTransport okHttpServerTransport) {
        this.f$0 = okHttpServerTransport;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.triggerForcefulClose();
    }
}
