package com.google.android.gms.internal.p002firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzxw implements zzbn {
    static final zzmm<zzya, zzjp.zzc> zza = zzmm.zza().zza(zzya.NIST_P256, zzjp.zzc.zza).zza(zzya.NIST_P384, zzjp.zzc.zzb).zza(zzya.NIST_P521, zzjp.zzc.zzc).zza();
    static final zzmm<zzyd, zzjp.zze> zzb = zzmm.zza().zza(zzyd.UNCOMPRESSED, zzjp.zze.zzb).zza(zzyd.COMPRESSED, zzjp.zze.zza).zza(zzyd.DO_NOT_USE_CRUNCHY_UNCOMPRESSED, zzjp.zze.zzc).zza();

    public static zzbn zza(zzjv zzjvVar) throws GeneralSecurityException {
        zzya zzyaVar = (zzya) zza.zza(zzjvVar.zzb().zzd());
        byte[] byteArray = zzjvVar.zze().getAffineX().toByteArray();
        byte[] byteArray2 = zzjvVar.zze().getAffineY().toByteArray();
        ECParameterSpec eCParameterSpecZza = zzyb.zza(zzyaVar);
        ECPoint eCPoint = new ECPoint(new BigInteger(1, byteArray), new BigInteger(1, byteArray2));
        zzmk.zza(eCPoint, eCParameterSpecZza.getCurve());
        return new zzxw((ECPublicKey) zzyf.zze.zza("EC").generatePublic(new ECPublicKeySpec(eCPoint, eCParameterSpecZza)), zzjvVar.zzb().zzh() != null ? zzjvVar.zzb().zzh().zzb() : new byte[0], zza(zzjvVar.zzb().zze()), (zzyd) zzb.zza(zzjvVar.zzb().zzf()), zzks.zza(zzjvVar.zzb()), zzjvVar.zzc().zzb());
    }

    static final String zza(zzjp.zzb zzbVar) throws GeneralSecurityException {
        if (zzbVar.equals(zzjp.zzb.zza)) {
            return "HmacSha1";
        }
        if (zzbVar.equals(zzjp.zzb.zzb)) {
            return "HmacSha224";
        }
        if (zzbVar.equals(zzjp.zzb.zzc)) {
            return "HmacSha256";
        }
        if (zzbVar.equals(zzjp.zzb.zzd)) {
            return "HmacSha384";
        }
        if (zzbVar.equals(zzjp.zzb.zze)) {
            return "HmacSha512";
        }
        throw new GeneralSecurityException("hash unsupported for EciesAeadHkdf: " + String.valueOf(zzbVar));
    }

    private zzxw(ECPublicKey eCPublicKey, byte[] bArr, String str, zzyd zzydVar, zzkw zzkwVar, byte[] bArr2) throws GeneralSecurityException {
        zzmk.zza(eCPublicKey.getW(), eCPublicKey.getParams().getCurve());
        new zzxy(eCPublicKey);
    }
}
