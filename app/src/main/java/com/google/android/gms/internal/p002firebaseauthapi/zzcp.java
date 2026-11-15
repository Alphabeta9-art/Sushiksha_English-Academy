package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzcp {
    public static zzch zza(byte[] bArr) throws GeneralSecurityException {
        try {
            zzvu zzvuVarZza = zzvu.zza(bArr, zzajk.zza());
            zzof zzofVarZza = zzof.zza();
            zzpf zzpfVarZza = zzpf.zza(zzvuVarZza);
            return !zzofVarZza.zzc(zzpfVarZza) ? new zzne(zzpfVarZza) : zzofVarZza.zza(zzpfVarZza);
        } catch (IOException e) {
            throw new GeneralSecurityException("Failed to parse proto", e);
        }
    }

    public static byte[] zza(zzch zzchVar) throws GeneralSecurityException {
        if (zzchVar instanceof zzne) {
            return ((zzne) zzchVar).zzb().zza().zzk();
        }
        return ((zzpf) zzof.zza().zza(zzchVar, zzpf.class)).zza().zzk();
    }
}
