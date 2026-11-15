package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.internal.zzap;
import com.google.firebase.auth.internal.zzj;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzabp extends zzady<SignInMethodQueryResult, zzj> {
    private final String zzu;
    private final String zzv;

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaek
    public final String zza() {
        return "fetchSignInMethodsForEmail";
    }

    public zzabp(String str, String str2) {
        super(3);
        Preconditions.checkNotEmpty(str, "email cannot be null or empty");
        this.zzu = str;
        this.zzv = str2;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzady
    public final void zzb() {
        List listZzh;
        if (this.zzl.zza() == null) {
            listZzh = zzal.zzh();
        } else {
            listZzh = (List) Preconditions.checkNotNull(this.zzl.zza());
        }
        zzb(new zzap(listZzh));
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaek
    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadhVar) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadhVar.zze(this.zzu, this.zzv, this.zzb);
    }
}
