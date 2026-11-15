package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzhr implements zzbg {
    private final zzbg zza;
    private final byte[] zzb;

    public static zzbg zza(zznc zzncVar) throws GeneralSecurityException {
        byte[] bArrZzb;
        zzpc zzpcVarZza = zzncVar.zza(zzbq.zza());
        zzvq zzvqVar = (zzvq) ((zzajy) zzvq.zza().zza(zzpcVarZza.zzf()).zza(zzpcVarZza.zzd()).zza(zzpcVarZza.zza()).zze());
        zzpg.zza();
        zzbg zzbgVar = (zzbg) zzpg.zza(zzvqVar, zzbg.class);
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
        return new zzhr(zzbgVar, bArrZzb);
    }

    public static zzbg zza(zzbg zzbgVar, zzzc zzzcVar) {
        return new zzhr(zzbgVar, zzzcVar.zzb());
    }

    private zzhr(zzbg zzbgVar, byte[] bArr) {
        this.zza = zzbgVar;
        if (bArr.length != 0 && bArr.length != 5) {
            throw new IllegalArgumentException("identifier has an invalid length");
        }
        this.zzb = bArr;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbg
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (this.zzb.length == 0) {
            return this.zza.zza(bArr, bArr2);
        }
        if (!zzpr.zza(this.zzb, bArr)) {
            throw new GeneralSecurityException("wrong prefix");
        }
        return this.zza.zza(Arrays.copyOfRange(bArr, 5, bArr.length), bArr2);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbg
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (this.zzb.length == 0) {
            return this.zza.zzb(bArr, bArr2);
        }
        return zzxv.zza(this.zzb, this.zza.zzb(bArr, bArr2));
    }
}
