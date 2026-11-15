package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzxt implements zzbl {
    private static final zzil.zza zza = zzil.zza.zza;
    private static final Collection<Integer> zzb = Arrays.asList(64);
    private static final byte[] zzc = new byte[16];
    private static final ThreadLocal<Cipher> zzd = new zzxs();
    private final zzyv zze;
    private final byte[] zzf;
    private final byte[] zzg;

    public static zzbl zza(zzio zzioVar) throws GeneralSecurityException {
        return new zzxt(zzioVar.zze().zza(zzbq.zza()), zzioVar.zzd());
    }

    private zzxt(byte[] bArr, zzzc zzzcVar) throws GeneralSecurityException {
        if (!zza.zza()) {
            throw new GeneralSecurityException("Can not use AES-SIV in FIPS-mode.");
        }
        if (!zzb.contains(Integer.valueOf(bArr.length))) {
            throw new InvalidKeyException("invalid key size: " + bArr.length + " bytes; key must have 64 bytes");
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length / 2);
        this.zzf = Arrays.copyOfRange(bArr, bArr.length / 2, bArr.length);
        this.zze = new zzyv(bArrCopyOfRange);
        this.zzg = zzzcVar.zzb();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbl
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArrZza;
        byte[] bArr3;
        if (bArr.length < this.zzg.length + 16) {
            throw new GeneralSecurityException("Ciphertext too short.");
        }
        if (!zzpr.zza(this.zzg, bArr)) {
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
        Cipher cipher = zzd.get();
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, this.zzg.length, this.zzg.length + 16);
        byte[] bArr4 = (byte[]) bArrCopyOfRange.clone();
        bArr4[8] = (byte) (bArr4[8] & 127);
        bArr4[12] = (byte) (bArr4[12] & 127);
        cipher.init(2, new SecretKeySpec(this.zzf, "AES"), new IvParameterSpec(bArr4));
        int length = this.zzg.length + 16;
        int length2 = bArr.length - length;
        byte[] bArrDoFinal = cipher.doFinal(bArr, length, length2);
        if (length2 == 0 && bArrDoFinal == null && zzyy.zza()) {
            bArrDoFinal = new byte[0];
        }
        byte[][] bArr5 = {bArr2, bArrDoFinal};
        byte[] bArrZza2 = this.zze.zza(zzc, 16);
        for (int i = 0; i <= 0; i++) {
            if (bArr5[i] == null) {
                bArr3 = new byte[0];
            } else {
                bArr3 = bArr5[i];
            }
            bArrZza2 = zzxv.zza(zzrg.zzb(bArrZza2), this.zze.zza(bArr3, 16));
        }
        byte[] bArr6 = bArr5[1];
        if (bArr6.length >= 16) {
            if (bArr6.length < bArrZza2.length) {
                throw new IllegalArgumentException("xorEnd requires a.length >= b.length");
            }
            int length3 = bArr6.length - bArrZza2.length;
            bArrZza = Arrays.copyOf(bArr6, bArr6.length);
            for (int i2 = 0; i2 < bArrZza2.length; i2++) {
                int i3 = length3 + i2;
                bArrZza[i3] = (byte) (bArrZza[i3] ^ bArrZza2[i2]);
            }
        } else {
            bArrZza = zzxv.zza(zzrg.zza(bArr6), zzrg.zzb(bArrZza2));
        }
        if (MessageDigest.isEqual(bArrCopyOfRange, this.zze.zza(bArrZza, 16))) {
            return bArrDoFinal;
        }
        throw new AEADBadTagException("Integrity check failed.");
    }
}
