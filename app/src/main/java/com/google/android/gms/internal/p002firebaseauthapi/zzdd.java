package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzdd {
    private static final zzor<zzcz, zzbg> zza = zzor.zza(new zzot() { // from class: com.google.android.gms.internal.firebase-auth-api.zzdc
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzot
        public final Object zza(zzbp zzbpVar) {
            return zzyc.zza((zzcz) zzbpVar);
        }
    }, zzcz.class, zzbg.class);
    private static final zzbs<zzbg> zzb = zzna.zza("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey", zzbg.class, zzvq.zzb.SYMMETRIC, zzry.zzf());
    private static final zznz<zzdg> zzc = new zznz() { // from class: com.google.android.gms.internal.firebase-auth-api.zzdf
    };
    private static final zznx<zzdg> zzd = new zznx() { // from class: com.google.android.gms.internal.firebase-auth-api.zzde
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zznx
        public final zzbp zza(zzch zzchVar, Integer num) {
            return zzdd.zza((zzdg) zzchVar, num);
        }
    };
    private static final zzil.zza zze = zzil.zza.zzb;

    static zzcz zza(zzdg zzdgVar, @Nullable Integer num) throws GeneralSecurityException {
        if (zzdgVar.zzb() == 16 || zzdgVar.zzb() == 32) {
            return zzcz.zzb().zza(zzdgVar).zza(num).zza(zzze.zza(zzdgVar.zzb())).zzb(zzze.zza(zzdgVar.zzc())).zza();
        }
        throw new GeneralSecurityException("AES key size must be 16 or 32 bytes");
    }

    static String zza() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    public static void zza(boolean z) throws GeneralSecurityException {
        if (!zze.zza()) {
            throw new GeneralSecurityException("Can not use AES-CTR-HMAC in FIPS-mode, as BoringCrypto module is not available.");
        }
        zzgc.zza();
        zzoc.zza().zza(zza);
        zzod zzodVarZza = zzod.zza();
        HashMap map = new HashMap();
        map.put("AES128_CTR_HMAC_SHA256", zzfh.zze);
        map.put("AES128_CTR_HMAC_SHA256_RAW", zzdg.zzf().zza(16).zzb(32).zzd(16).zzc(16).zza(zzdg.zzc.zzc).zza(zzdg.zzb.zzc).zza());
        map.put("AES256_CTR_HMAC_SHA256", zzfh.zzf);
        map.put("AES256_CTR_HMAC_SHA256_RAW", zzdg.zzf().zza(32).zzb(32).zzd(32).zzc(16).zza(zzdg.zzc.zzc).zza(zzdg.zzb.zzc).zza());
        zzodVarZza.zza(Collections.unmodifiableMap(map));
        zznw.zza().zza(zzc, zzdg.class);
        zznv.zza().zza(zzd, zzdg.class);
        zzmt.zza().zza((zzbs) zzb, zze, true);
    }
}
