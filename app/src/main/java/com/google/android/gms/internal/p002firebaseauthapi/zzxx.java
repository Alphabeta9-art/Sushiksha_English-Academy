package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.EllipticCurve;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzxx implements zzbo {
    private final ECPrivateKey zza;
    private final zzxz zzb;
    private final String zzc;
    private final byte[] zzd;
    private final zzyd zze;
    private final zzkw zzf;
    private final byte[] zzg;

    public static zzbo zza(zzjs zzjsVar) throws GeneralSecurityException {
        return new zzxx(zzyb.zza((zzya) zzxw.zza.zza(zzjsVar.zzc().zzd()), zzmj.zza(zzjsVar.zze().zza(zzbq.zza()))), zzjsVar.zzc().zzh() != null ? zzjsVar.zzc().zzh().zzb() : new byte[0], zzxw.zza(zzjsVar.zzc().zze()), (zzyd) zzxw.zzb.zza(zzjsVar.zzc().zzf()), zzks.zza(zzjsVar.zzc()), zzjsVar.zzg().zzb());
    }

    private zzxx(ECPrivateKey eCPrivateKey, byte[] bArr, String str, zzyd zzydVar, zzkw zzkwVar, byte[] bArr2) {
        this.zza = eCPrivateKey;
        this.zzb = new zzxz(eCPrivateKey);
        this.zzd = bArr;
        this.zzc = str;
        this.zze = zzydVar;
        this.zzf = zzkwVar;
        this.zzg = bArr2;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbo
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int i;
        if (!zzpr.zza(this.zzg, bArr)) {
            throw new GeneralSecurityException("Invalid ciphertext (output prefix mismatch)");
        }
        int length = this.zzg.length;
        EllipticCurve curve = this.zza.getParams().getCurve();
        zzyd zzydVar = this.zze;
        int iZza = zzyb.zza(curve);
        switch (zzydVar) {
            case UNCOMPRESSED:
                i = (iZza * 2) + 1;
                break;
            case COMPRESSED:
                i = iZza + 1;
                break;
            case DO_NOT_USE_CRUNCHY_UNCOMPRESSED:
                i = iZza * 2;
                break;
            default:
                throw new GeneralSecurityException("unknown EC point format");
        }
        int i2 = i + length;
        if (bArr.length < i2) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        return this.zzf.zza(this.zzb.zza(Arrays.copyOfRange(bArr, length, i2), this.zzc, this.zzd, bArr2, this.zzf.zza(), this.zze), bArr, i2);
    }
}
