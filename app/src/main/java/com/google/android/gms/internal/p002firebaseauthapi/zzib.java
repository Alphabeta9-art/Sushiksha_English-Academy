package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzib implements zzbg {
    private static final zzil.zza zza = zzil.zza.zza;
    private final byte[] zzb;
    private final byte[] zzc;

    public static zzbg zza(zzfv zzfvVar) throws GeneralSecurityException {
        return new zzib(zzfvVar.zzd().zza(zzbq.zza()), zzfvVar.zzc().zzb());
    }

    private zzib(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (!zza.zza()) {
            throw new GeneralSecurityException("Can not use ChaCha20Poly1305 in FIPS-mode.");
        }
        if (!zza()) {
            throw new GeneralSecurityException("JCE does not support algorithm: ChaCha20-Poly1305");
        }
        if (bArr.length != 32) {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
        this.zzb = bArr;
        this.zzc = bArr2;
    }

    public static boolean zza() {
        return zzhc.zza() != null;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbg
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr == null) {
            throw new NullPointerException("ciphertext is null");
        }
        if (bArr.length < this.zzc.length + 24 + 16) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        if (!zzpr.zza(this.zzc, bArr)) {
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
        byte[] bArr3 = new byte[24];
        System.arraycopy(bArr, this.zzc.length, bArr3, 0, 24);
        SecretKeySpec secretKeySpec = new SecretKeySpec(zzhk.zza(this.zzb, bArr3), "ChaCha20");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(zza(bArr3));
        Cipher cipherZza = zzhc.zza();
        cipherZza.init(2, secretKeySpec, ivParameterSpec);
        if (bArr2 != null && bArr2.length != 0) {
            cipherZza.updateAAD(bArr2);
        }
        return cipherZza.doFinal(bArr, this.zzc.length + 24, (bArr.length - this.zzc.length) - 24);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbg
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr == null) {
            throw new NullPointerException("plaintext is null");
        }
        byte[] bArrZza = zzpe.zza(24);
        SecretKeySpec secretKeySpec = new SecretKeySpec(zzhk.zza(this.zzb, bArrZza), "ChaCha20");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(zza(bArrZza));
        Cipher cipherZza = zzhc.zza();
        cipherZza.init(1, secretKeySpec, ivParameterSpec);
        if (bArr2 != null && bArr2.length != 0) {
            cipherZza.updateAAD(bArr2);
        }
        int outputSize = cipherZza.getOutputSize(bArr.length);
        if (outputSize <= (Integer.MAX_VALUE - this.zzc.length) - 24) {
            byte[] bArrCopyOf = Arrays.copyOf(this.zzc, this.zzc.length + 24 + outputSize);
            System.arraycopy(bArrZza, 0, bArrCopyOf, this.zzc.length, 24);
            if (cipherZza.doFinal(bArr, 0, bArr.length, bArrCopyOf, this.zzc.length + 24) != outputSize) {
                throw new GeneralSecurityException("not enough data written");
            }
            return bArrCopyOf;
        }
        throw new GeneralSecurityException("plaintext too long");
    }

    private static byte[] zza(byte[] bArr) {
        byte[] bArr2 = new byte[12];
        System.arraycopy(bArr, 16, bArr2, 4, 8);
        return bArr2;
    }
}
