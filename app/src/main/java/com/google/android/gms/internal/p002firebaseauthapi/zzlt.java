package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzlt implements zzbo {
    private final zzbo zza;
    private final byte[] zzb;

    public static zzbo zza(zznc zzncVar) throws GeneralSecurityException {
        byte[] bArrZzb;
        zzpc zzpcVarZza = zzncVar.zza(zzbq.zza());
        zzbo zzboVar = (zzbo) zzco.zza((zzvq) ((zzajy) zzvq.zza().zza(zzpcVarZza.zzf()).zza(zzpcVarZza.zzd()).zza(zzpcVarZza.zza()).zze()), zzbo.class);
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
        return new zzlt(zzboVar, bArrZzb);
    }

    private zzlt(zzbo zzboVar, byte[] bArr) {
        this.zza = zzboVar;
        this.zzb = bArr;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbo
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (this.zzb.length == 0) {
            return this.zza.zza(bArr, bArr2);
        }
        if (!zzpr.zza(this.zzb, bArr)) {
            throw new GeneralSecurityException("Invalid ciphertext (output prefix mismatch)");
        }
        return this.zza.zza(Arrays.copyOfRange(bArr, this.zzb.length, bArr.length), bArr2);
    }
}
