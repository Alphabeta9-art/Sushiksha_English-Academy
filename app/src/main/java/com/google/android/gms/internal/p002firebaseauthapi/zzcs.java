package com.google.android.gms.internal.p002firebaseauthapi;

import com.bumptech.glide.load.Key;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzcs {
    public static zzwd zza(zzwa zzwaVar) {
        zzwd.zza zzaVarZza = zzwd.zza().zza(zzwaVar.zzb());
        for (zzwa.zzb zzbVar : zzwaVar.zze()) {
            zzaVarZza.zza((zzwd.zzb) ((zzajy) zzwd.zzb.zza().zza(zzbVar.zzb().zzf()).zza(zzbVar.zzc()).zza(zzbVar.zzf()).zza(zzbVar.zza()).zze()));
        }
        return (zzwd) ((zzajy) zzaVarZza.zze());
    }

    static {
        Charset.forName(Key.STRING_CHARSET_NAME);
    }

    public static void zzb(zzwa zzwaVar) throws GeneralSecurityException {
        int iZzb = zzwaVar.zzb();
        int i = 0;
        boolean z = false;
        boolean z2 = true;
        for (zzwa.zzb zzbVar : zzwaVar.zze()) {
            if (zzbVar.zzc() == zzvv.ENABLED) {
                if (!zzbVar.e_()) {
                    throw new GeneralSecurityException(String.format("key %d has no key data", Integer.valueOf(zzbVar.zza())));
                }
                if (zzbVar.zzf() == zzws.UNKNOWN_PREFIX) {
                    throw new GeneralSecurityException(String.format("key %d has unknown prefix", Integer.valueOf(zzbVar.zza())));
                }
                if (zzbVar.zzc() == zzvv.UNKNOWN_STATUS) {
                    throw new GeneralSecurityException(String.format("key %d has unknown status", Integer.valueOf(zzbVar.zza())));
                }
                if (zzbVar.zza() == iZzb) {
                    if (z) {
                        throw new GeneralSecurityException("keyset contains multiple primary keys");
                    }
                    z = true;
                }
                if (zzbVar.zzb().zzb() != zzvq.zzb.ASYMMETRIC_PUBLIC) {
                    z2 = false;
                }
                i++;
            }
        }
        if (i == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        }
        if (!z && !z2) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}
