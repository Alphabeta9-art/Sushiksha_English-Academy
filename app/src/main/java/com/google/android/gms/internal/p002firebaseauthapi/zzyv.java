package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzyv implements zzrq {
    private static final zzil.zza zza = zzil.zza.zza;
    private static final ThreadLocal<Cipher> zzb = new zzyu();
    private final SecretKey zzc;
    private byte[] zzd;
    private byte[] zze;

    private static Cipher zza() throws GeneralSecurityException {
        if (zza.zza()) {
            return zzb.get();
        }
        throw new GeneralSecurityException("Can not use AES-CMAC in FIPS-mode.");
    }

    public zzyv(byte[] bArr) throws GeneralSecurityException {
        zzzb.zza(bArr.length);
        this.zzc = new SecretKeySpec(bArr, "AES");
        Cipher cipherZza = zza();
        cipherZza.init(1, this.zzc);
        this.zzd = zzrg.zzb(cipherZza.doFinal(new byte[16]));
        this.zze = zzrg.zzb(this.zzd);
    }

    private static void zza(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        for (int i2 = 0; i2 < 16; i2++) {
            bArr3[i2] = (byte) (bArr[i2] ^ bArr2[i2 + i]);
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzrq
    public final byte[] zza(byte[] bArr, int i) throws GeneralSecurityException {
        int i2;
        byte[] bArrZza;
        if (i > 16) {
            throw new InvalidAlgorithmParameterException("outputLength too large, max is 16 bytes");
        }
        Cipher cipherZza = zza();
        cipherZza.init(1, this.zzc);
        int length = bArr.length;
        if (length != 0) {
            i2 = ((length - 1) / 16) + 1;
        } else {
            i2 = 1;
        }
        if ((i2 << 4) == bArr.length) {
            bArrZza = zzxv.zza(bArr, (i2 - 1) << 4, this.zzd, 0, 16);
        } else {
            bArrZza = zzxv.zza(zzrg.zza(Arrays.copyOfRange(bArr, (i2 - 1) << 4, bArr.length)), this.zze);
        }
        byte[] bArr2 = new byte[16];
        byte[] bArr3 = new byte[16];
        for (int i3 = 0; i3 < i2 - 1; i3++) {
            zza(bArr2, bArr, i3 << 4, bArr3);
            if (cipherZza.doFinal(bArr3, 0, 16, bArr2) != 16) {
                throw new IllegalStateException("Cipher didn't write full block");
            }
        }
        zza(bArr2, bArrZza, 0, bArr3);
        if (cipherZza.doFinal(bArr3, 0, 16, bArr2) != 16) {
            throw new IllegalStateException("Cipher didn't write full block");
        }
        if (16 == i) {
            return bArr2;
        }
        return Arrays.copyOf(bArr2, i);
    }
}
