package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzlv implements zzbn {
    public static zzbn zza(zznc zzncVar) throws GeneralSecurityException {
        byte[] bArrZzb;
        zzpc zzpcVarZza = zzncVar.zza(zzbq.zza());
        zzbn zzbnVar = (zzbn) zzco.zza((zzvq) ((zzajy) zzvq.zza().zza(zzpcVarZza.zzf()).zza(zzpcVarZza.zzd()).zza(zzpcVarZza.zza()).zze()), zzbn.class);
        zzws zzwsVarZzb = zzpcVarZza.zzb();
        switch (zzwsVarZzb) {
            case RAW:
                bArrZzb = zzog.zza.zzb();
                break;
            case LEGACY:
            case CRUNCHY:
                bArrZzb = zzog.zza(zzncVar.zza().intValue()).zzb();
                break;
            case TINK:
                bArrZzb = zzog.zzb(zzncVar.zza().intValue()).zzb();
                break;
            default:
                throw new GeneralSecurityException("unknown output prefix type " + String.valueOf(zzwsVarZzb));
        }
        return new zzlv(zzbnVar, bArrZzb);
    }

    private zzlv(zzbn zzbnVar, byte[] bArr) {
    }
}
