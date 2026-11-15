package com.google.android.gms.internal.p002firebaseauthapi;

import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzby {
    private boolean zza;
    private zzbr zzb;

    @Nullable
    private final zzch zzc;
    private zzbx zzd;

    @Nullable
    private zzbv zze;

    public final zzby zza() {
        if (this.zze != null) {
            this.zze.zzb();
        }
        this.zza = true;
        return this;
    }

    public final zzby zzb() {
        this.zzd = zzbx.zza;
        return this;
    }

    private zzby(zzch zzchVar) {
        this.zzb = zzbr.zza;
        this.zzd = null;
        this.zze = null;
        this.zzc = zzchVar;
    }
}
