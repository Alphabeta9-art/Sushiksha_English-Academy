package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzlx implements zzlk {
    private final zzya zza;
    private final zzle zzb;

    static zzlx zza(zzya zzyaVar) throws GeneralSecurityException {
        switch (zzyaVar) {
            case NIST_P256:
                return new zzlx(new zzle("HmacSha256"), zzya.NIST_P256);
            case NIST_P384:
                return new zzlx(new zzle("HmacSha384"), zzya.NIST_P384);
            case NIST_P521:
                return new zzlx(new zzle("HmacSha512"), zzya.NIST_P521);
            default:
                throw new GeneralSecurityException("invalid curve type: " + String.valueOf(zzyaVar));
        }
    }

    private zzlx(zzle zzleVar, zzya zzyaVar) {
        this.zzb = zzleVar;
        this.zza = zzyaVar;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzlk
    public final byte[] zza(byte[] bArr, zzln zzlnVar) throws GeneralSecurityException {
        return this.zzb.zza(null, zzyb.zza(zzyb.zza(this.zza, zzlnVar.zza().zzb()), zzyb.zza(this.zza, zzyd.UNCOMPRESSED, bArr)), "eae_prk", zzxv.zza(bArr, zzlnVar.zzb().zzb()), "shared_secret", zzlq.zza(zza()), this.zzb.zza());
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzlk
    public final byte[] zza() throws GeneralSecurityException {
        switch (this.zza) {
            case NIST_P256:
                return zzlq.zzc;
            case NIST_P384:
                return zzlq.zzd;
            case NIST_P521:
                return zzlq.zze;
            default:
                throw new GeneralSecurityException("Could not determine HPKE KEM ID");
        }
    }
}
