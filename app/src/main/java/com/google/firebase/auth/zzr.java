package com.google.firebase.auth;

import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes12.dex */
final class zzr implements com.google.firebase.auth.internal.zzat {
    private final /* synthetic */ FirebaseUser zza;
    private final /* synthetic */ FirebaseAuth zzb;

    zzr(FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        this.zza = firebaseUser;
        this.zzb = firebaseAuth;
    }

    @Override // com.google.firebase.auth.internal.zzat
    public final void zza() {
        if (this.zzb.zzf == null || !this.zzb.zzf.getUid().equalsIgnoreCase(this.zza.getUid())) {
            return;
        }
        this.zzb.zzh();
    }

    @Override // com.google.firebase.auth.internal.zzaw
    public final void zza(Status status) {
        if (status.getStatusCode() == 17011 || status.getStatusCode() == 17021 || status.getStatusCode() == 17005) {
            this.zzb.signOut();
        }
    }
}
