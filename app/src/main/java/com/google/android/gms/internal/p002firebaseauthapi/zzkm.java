package com.google.android.gms.internal.p002firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzkm {
    private static final byte[] zzb = new byte[0];
    public static final zzvu zza = zza(zzup.NIST_P256, zzur.SHA256, zztu.UNCOMPRESSED, zzct.zza, zzws.TINK, zzb);

    @Deprecated
    private static zzvu zza(zzup zzupVar, zzur zzurVar, zztu zztuVar, zzvu zzvuVar, zzws zzwsVar, byte[] bArr) {
        zzua.zza zzaVarZza = zzua.zza();
        zzum zzumVar = (zzum) ((zzajy) zzum.zza().zza(zzupVar).zza(zzurVar).zza(zzaip.zza(bArr)).zze());
        return (zzvu) ((zzajy) zzvu.zza().zza(zzjl.zza()).zza(zzwsVar).zza(((zzua) ((zzajy) zzaVarZza.zza((zzud) ((zzajy) zzud.zzc().zza(zzumVar).zza((zztx) ((zzajy) zztx.zza().zza(zzvuVar).zze())).zza(zztuVar).zze())).zze())).a_()).zze());
    }

    static {
        zza(zzup.NIST_P256, zzur.SHA256, zztu.COMPRESSED, zzct.zza, zzws.RAW, zzb);
        zza(zzup.NIST_P256, zzur.SHA256, zztu.UNCOMPRESSED, zzct.zzb, zzws.TINK, zzb);
    }
}
