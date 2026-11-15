package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzjj implements zzbl {
    private final zzbl zza;
    private final zzws zzb;
    private final byte[] zzc;

    public static zzbl zza(zznc zzncVar) throws GeneralSecurityException {
        byte[] bArrZzb;
        zzpc zzpcVarZza = zzncVar.zza(zzbq.zza());
        zzvq zzvqVar = (zzvq) ((zzajy) zzvq.zza().zza(zzpcVarZza.zzf()).zza(zzpcVarZza.zzd()).zza(zzpcVarZza.zza()).zze());
        zzpg.zza();
        zzbl zzblVar = (zzbl) zzpg.zza(zzvqVar, zzbl.class);
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
                throw new GeneralSecurityException("unknown output prefix type " + zzwsVarZzb.zza());
        }
        return new zzjj(zzblVar, zzwsVarZzb, bArrZzb);
    }

    private zzjj(zzbl zzblVar, zzws zzwsVar, byte[] bArr) {
        this.zza = zzblVar;
        this.zzb = zzwsVar;
        this.zzc = bArr;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbl
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (this.zzb == zzws.RAW) {
            return this.zza.zza(bArr, bArr2);
        }
        if (!zzpr.zza(this.zzc, bArr)) {
            throw new GeneralSecurityException("wrong prefix");
        }
        return this.zza.zza(Arrays.copyOfRange(bArr, 5, bArr.length), bArr2);
    }
}
