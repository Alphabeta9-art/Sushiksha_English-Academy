package com.google.firebase.auth.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.MultiFactorSession;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes12.dex */
final class zzak implements Continuation<GetTokenResult, Task<MultiFactorSession>> {
    private final /* synthetic */ zzah zza;

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Task<MultiFactorSession> then(Task<GetTokenResult> task) throws Exception {
        return !task.isSuccessful() ? Tasks.forException(task.getException()) : Tasks.forResult(zzao.zza(task.getResult().getToken(), this.zza.zza));
    }

    zzak(zzah zzahVar) {
        this.zza = zzahVar;
    }
}
