package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzxq implements zzbg {
    private static final zzil.zza zza = zzil.zza.zzb;
    private final SecretKey zzb;
    private final byte[] zzc;

    public static zzbg zza(zzdo zzdoVar) throws GeneralSecurityException {
        if (zzdoVar.zzc().zzb() != 12) {
            throw new GeneralSecurityException("Expected IV Size 12, got " + zzdoVar.zzc().zzb());
        }
        if (zzdoVar.zzc().zzd() == 16) {
            return new zzxq(zzdoVar.zze().zza(zzbq.zza()), zzdoVar.zzd());
        }
        throw new GeneralSecurityException("Expected tag Size 16, got " + zzdoVar.zzc().zzd());
    }

    private zzxq(byte[] bArr, zzzc zzzcVar) throws GeneralSecurityException {
        if (!zza.zza()) {
            throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
        }
        this.zzb = zzgo.zzb(bArr);
        this.zzc = zzzcVar.zzb();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbg
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr == null) {
            throw new NullPointerException("ciphertext is null");
        }
        if (bArr.length < this.zzc.length + 12 + 16) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        if (!zzpr.zza(this.zzc, bArr)) {
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
        AlgorithmParameterSpec algorithmParameterSpecZza = zzgo.zza(bArr, this.zzc.length, 12);
        Cipher cipherZza = zzgo.zza();
        cipherZza.init(2, this.zzb, algorithmParameterSpecZza);
        if (bArr2 != null && bArr2.length != 0) {
            cipherZza.updateAAD(bArr2);
        }
        return cipherZza.doFinal(bArr, this.zzc.length + 12, (bArr.length - this.zzc.length) - 12);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbg
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr == null) {
            throw new NullPointerException("plaintext is null");
        }
        byte[] bArrZza = zzpe.zza(12);
        AlgorithmParameterSpec algorithmParameterSpecZza = zzgo.zza(bArrZza);
        Cipher cipherZza = zzgo.zza();
        cipherZza.init(1, this.zzb, algorithmParameterSpecZza);
        if (bArr2 != null && bArr2.length != 0) {
            cipherZza.updateAAD(bArr2);
        }
        int outputSize = cipherZza.getOutputSize(bArr.length);
        if (outputSize <= (Integer.MAX_VALUE - this.zzc.length) - 12) {
            byte[] bArrCopyOf = Arrays.copyOf(this.zzc, this.zzc.length + 12 + outputSize);
            System.arraycopy(bArrZza, 0, bArrCopyOf, this.zzc.length, 12);
            if (cipherZza.doFinal(bArr, 0, bArr.length, bArrCopyOf, this.zzc.length + 12) != outputSize) {
                throw new GeneralSecurityException("not enough data written");
            }
            return bArrCopyOf;
        }
        throw new GeneralSecurityException("plaintext too long");
    }
}
