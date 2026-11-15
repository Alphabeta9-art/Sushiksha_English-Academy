package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzaq;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzaav implements zzael<zzagl> {
    private final /* synthetic */ zzade zza;
    private final /* synthetic */ zzzk zzb;

    zzaav(zzzk zzzkVar, zzade zzadeVar) {
        this.zza = zzadeVar;
        this.zzb = zzzkVar;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaem
    public final void zza(String str) {
        this.zza.zza(zzaq.zza(str));
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzael
    public final /* synthetic */ void zza(zzagl zzaglVar) {
        zzagl zzaglVar2 = zzaglVar;
        this.zzb.zza.zza(new zzagc(zzaglVar2.zzc()), new zzaau(this, this, this.zza, zzaglVar2));
    }
}
