package androidx.credentials;

import com.android.tools.r8.annotations.LambdaMethod;
import com.android.tools.r8.annotations.SynthesizedClassV2;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes10.dex */
@LambdaMethod(holder = "Ljava/lang/Runnable;", method = "run", proto = "()V")
@SynthesizedClassV2(apiLevel = -2, kind = 28, versionHash = "f3a348d7d571cfc3f89005b557595f12bc85b7451dde524d9d228b1d59adcb6b")
public final /* synthetic */ class CredentialManager$$ExternalSyntheticLambda0 implements Executor {
    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
