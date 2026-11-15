package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzbj {
    public static final byte[] zza = new byte[0];

    public static byte[] zza(zzwa.zzb zzbVar) throws GeneralSecurityException {
        switch (zzbm.zza[zzbVar.zzf().ordinal()]) {
            case 1:
            case 2:
                return zzog.zza(zzbVar.zza()).zzb();
            case 3:
                return zzog.zzb(zzbVar.zza()).zzb();
            case 4:
                return zza;
            default:
                throw new GeneralSecurityException("unknown output prefix type");
        }
    }
}
