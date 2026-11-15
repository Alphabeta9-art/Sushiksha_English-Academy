package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import okio.Utf8;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzlm {
    private static final zzor<zzjw, zzbo> zza = zzor.zza(new zzot() { // from class: com.google.android.gms.internal.firebase-auth-api.zzlp
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzot
        public final Object zza(zzbp zzbpVar) {
            return zzlj.zza((zzjw) zzbpVar);
        }
    }, zzjw.class, zzbo.class);
    private static final zzor<zzke, zzbn> zzb = zzor.zza(new zzot() { // from class: com.google.android.gms.internal.firebase-auth-api.zzlo
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzot
        public final Object zza(zzbp zzbpVar) {
            return zzli.zza((zzke) zzbpVar);
        }
    }, zzke.class, zzbn.class);
    private static final zzcj<zzbo> zzc = zzna.zza("type.googleapis.com/google.crypto.tink.HpkePrivateKey", zzbo.class, zzvk.zzf());
    private static final zzbs<zzbn> zzd = zzna.zza("type.googleapis.com/google.crypto.tink.HpkePublicKey", zzbn.class, zzvq.zzb.ASYMMETRIC_PUBLIC, zzvn.d_());
    private static final zznx<zzju> zze = new zznx() { // from class: com.google.android.gms.internal.firebase-auth-api.zzlr
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zznx
        public final zzbp zza(zzch zzchVar, Integer num) {
            return zzlm.zza((zzju) zzchVar, num);
        }
    };

    public static /* synthetic */ zzjw zza(zzju zzjuVar, Integer num) throws GeneralSecurityException {
        byte[] bArr;
        zzzc zzzcVarZza;
        zzze zzzeVarZza;
        if (zzjuVar.zze().equals(zzju.zzf.zzd)) {
            byte[] bArrZza = zzpe.zza(32);
            bArrZza[0] = (byte) (bArrZza[0] | 7);
            bArrZza[31] = (byte) (bArrZza[31] & Utf8.REPLACEMENT_BYTE);
            bArrZza[31] = (byte) (bArrZza[31] | 128);
            zzzeVarZza = zzze.zza(bArrZza, zzbq.zza());
            zzzcVarZza = zzzc.zza(zzza.zza(bArrZza));
        } else {
            if (!zzjuVar.zze().equals(zzju.zzf.zza) && !zzjuVar.zze().equals(zzju.zzf.zzb) && !zzjuVar.zze().equals(zzju.zzf.zzc)) {
                throw new GeneralSecurityException("Unknown KEM ID");
            }
            zzya zzyaVarZzc = zzlq.zzc(zzjuVar.zze());
            KeyPair keyPairZza = zzyb.zza(zzyb.zza(zzyaVarZzc));
            zzyd zzydVar = zzyd.UNCOMPRESSED;
            ECPoint w = ((ECPublicKey) keyPairZza.getPublic()).getW();
            EllipticCurve curve = zzyb.zza(zzyaVarZzc).getCurve();
            zzmk.zza(w, curve);
            int iZza = zzyb.zza(curve);
            switch (zzydVar) {
                case UNCOMPRESSED:
                    int i = (iZza * 2) + 1;
                    bArr = new byte[i];
                    byte[] bArrZza2 = zzmj.zza(w.getAffineX());
                    byte[] bArrZza3 = zzmj.zza(w.getAffineY());
                    System.arraycopy(bArrZza3, 0, bArr, i - bArrZza3.length, bArrZza3.length);
                    System.arraycopy(bArrZza2, 0, bArr, (iZza + 1) - bArrZza2.length, bArrZza2.length);
                    bArr[0] = 4;
                    break;
                case COMPRESSED:
                    int i2 = iZza + 1;
                    bArr = new byte[i2];
                    byte[] bArrZza4 = zzmj.zza(w.getAffineX());
                    System.arraycopy(bArrZza4, 0, bArr, i2 - bArrZza4.length, bArrZza4.length);
                    bArr[0] = (byte) (w.getAffineY().testBit(0) ? 3 : 2);
                    break;
                case DO_NOT_USE_CRUNCHY_UNCOMPRESSED:
                    int i3 = iZza * 2;
                    bArr = new byte[i3];
                    byte[] bArrZza5 = zzmj.zza(w.getAffineX());
                    if (bArrZza5.length > iZza) {
                        bArrZza5 = Arrays.copyOfRange(bArrZza5, bArrZza5.length - iZza, bArrZza5.length);
                    }
                    byte[] bArrZza6 = zzmj.zza(w.getAffineY());
                    if (bArrZza6.length > iZza) {
                        bArrZza6 = Arrays.copyOfRange(bArrZza6, bArrZza6.length - iZza, bArrZza6.length);
                    }
                    System.arraycopy(bArrZza6, 0, bArr, i3 - bArrZza6.length, bArrZza6.length);
                    System.arraycopy(bArrZza5, 0, bArr, iZza - bArrZza5.length, bArrZza5.length);
                    break;
                default:
                    throw new GeneralSecurityException("invalid format:" + String.valueOf(zzydVar));
            }
            zzzcVarZza = zzzc.zza(bArr);
            zzzeVarZza = zzze.zza(zzmj.zza(((ECPrivateKey) keyPairZza.getPrivate()).getS(), zzlq.zza(zzjuVar.zze())), zzbq.zza());
        }
        return zzjw.zza(zzke.zza(zzjuVar, zzzcVarZza, num), zzzeVarZza);
    }

    public static void zza(boolean z) throws GeneralSecurityException {
        if (!zzil.zza.zza.zza()) {
            throw new GeneralSecurityException("Registering HPKE Hybrid Encryption is not supported in FIPS mode");
        }
        zzjz.zza();
        zzod zzodVarZza = zzod.zza();
        HashMap map = new HashMap();
        map.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zzd).zza(zzju.zzc.zza).zza(zzju.zzb.zza).zza());
        map.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zzd).zza(zzju.zzc.zza).zza(zzju.zzb.zza).zza());
        map.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zzd).zza(zzju.zzc.zza).zza(zzju.zzb.zzb).zza());
        map.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zzd).zza(zzju.zzc.zza).zza(zzju.zzb.zzb).zza());
        map.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zzd).zza(zzju.zzc.zza).zza(zzju.zzb.zzc).zza());
        map.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zzd).zza(zzju.zzc.zza).zza(zzju.zzb.zzc).zza());
        map.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zza).zza(zzju.zzc.zza).zza(zzju.zzb.zza).zza());
        map.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zza).zza(zzju.zzc.zza).zza(zzju.zzb.zza).zza());
        map.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zza).zza(zzju.zzc.zza).zza(zzju.zzb.zzb).zza());
        map.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zza).zza(zzju.zzc.zza).zza(zzju.zzb.zzb).zza());
        map.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zzb).zza(zzju.zzc.zzb).zza(zzju.zzb.zza).zza());
        map.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zzb).zza(zzju.zzc.zzb).zza(zzju.zzb.zza).zza());
        map.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zzb).zza(zzju.zzc.zzb).zza(zzju.zzb.zzb).zza());
        map.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zzb).zza(zzju.zzc.zzb).zza(zzju.zzb.zzb).zza());
        map.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zzc).zza(zzju.zzc.zzc).zza(zzju.zzb.zza).zza());
        map.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zzc).zza(zzju.zzc.zzc).zza(zzju.zzb.zza).zza());
        map.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zzc).zza(zzju.zzc.zzc).zza(zzju.zzb.zzb).zza());
        map.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zzc).zza(zzju.zzc.zzc).zza(zzju.zzb.zzb).zza());
        zzodVarZza.zza(Collections.unmodifiableMap(map));
        zzoc.zza().zza(zza);
        zzoc.zza().zza(zzb);
        zznv.zza().zza(zze, zzju.class);
        zzmt.zza().zza((zzbs) zzc, true);
        zzmt.zza().zza((zzbs) zzd, false);
    }
}
