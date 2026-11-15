package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzaef<ResultT, CallbackT> implements zzadw<ResultT> {
    private final zzady<ResultT, CallbackT> zza;
    private final TaskCompletionSource<ResultT> zzb;

    public zzaef(zzady<ResultT, CallbackT> zzadyVar, TaskCompletionSource<ResultT> taskCompletionSource) {
        this.zza = zzadyVar;
        this.zzb = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadw
    public final void zza(ResultT resultt, Status status) {
        FirebaseUser firebaseUser;
        Preconditions.checkNotNull(this.zzb, "completion source cannot be null");
        if (status != null) {
            if (this.zza.zzq != null) {
                TaskCompletionSource<ResultT> taskCompletionSource = this.zzb;
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(this.zza.zzc);
                zzzl zzzlVar = this.zza.zzq;
                if ("reauthenticateWithCredential".equals(this.zza.zza()) || "reauthenticateWithCredentialWithData".equals(this.zza.zza())) {
                    firebaseUser = this.zza.zzd;
                } else {
                    firebaseUser = null;
                }
                taskCompletionSource.setException(zzadg.zza(firebaseAuth, zzzlVar, firebaseUser));
                return;
            }
            if (this.zza.zzn != null) {
                this.zzb.setException(zzadg.zza(status, this.zza.zzn, this.zza.zzo, this.zza.zzp));
                return;
            } else {
                this.zzb.setException(zzadg.zza(status));
                return;
            }
        }
        this.zzb.setResult(resultt);
    }
}
