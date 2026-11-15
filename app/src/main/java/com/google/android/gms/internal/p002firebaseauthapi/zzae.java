package com.google.android.gms.internal.p002firebaseauthapi;

import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
abstract class zzae extends zzf<String> {
    final CharSequence zza;
    private final zzh zzb;
    private int zze;
    private int zzd = 0;
    private final boolean zzc = false;

    abstract int zza(int i);

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzf
    @CheckForNull
    protected final /* synthetic */ String zza() {
        int i = this.zzd;
        while (this.zzd != -1) {
            int iZzb = zzb(this.zzd);
            if (iZzb == -1) {
                iZzb = this.zza.length();
                this.zzd = -1;
            } else {
                this.zzd = zza(iZzb);
            }
            if (this.zzd != i) {
                while (i < iZzb && this.zzb.zza(this.zza.charAt(i))) {
                    i++;
                }
                while (iZzb > i && this.zzb.zza(this.zza.charAt(iZzb - 1))) {
                    iZzb--;
                }
                if (this.zze == 1) {
                    iZzb = this.zza.length();
                    this.zzd = -1;
                    while (iZzb > i && this.zzb.zza(this.zza.charAt(iZzb - 1))) {
                        iZzb--;
                    }
                } else {
                    this.zze--;
                }
                return this.zza.subSequence(i, iZzb).toString();
            }
            this.zzd++;
            if (this.zzd > this.zza.length()) {
                this.zzd = -1;
            }
        }
        zzb();
        return null;
    }

    abstract int zzb(int i);

    protected zzae(zzx zzxVar, CharSequence charSequence) {
        this.zzb = zzxVar.zza;
        this.zze = zzxVar.zzc;
        this.zza = charSequence;
    }
}
