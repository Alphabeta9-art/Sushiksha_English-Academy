package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zznc extends zzbp {
    private final zzpc zza;

    public final zzpc zza(@Nullable zzcn zzcnVar) throws GeneralSecurityException {
        zza(this.zza, zzcnVar);
        return this.zza;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbp
    @Nullable
    public final Integer zza() {
        return this.zza.zze();
    }

    public zznc(zzpc zzpcVar, @Nullable zzcn zzcnVar) throws GeneralSecurityException {
        zza(zzpcVar, zzcnVar);
        this.zza = zzpcVar;
    }

    private static void zza(zzpc zzpcVar, @Nullable zzcn zzcnVar) throws GeneralSecurityException {
        switch (zzpcVar.zza()) {
            case SYMMETRIC:
            case ASYMMETRIC_PRIVATE:
                zzcn.zza(zzcnVar);
                break;
        }
    }
}
