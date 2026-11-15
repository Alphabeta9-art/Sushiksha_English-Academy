package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.media3.extractor.ts.TsExtractor;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.AEADBadTagException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzxp implements zzbg {
    private static final zzil.zza zza = zzil.zza.zza;
    private static final ThreadLocal<Cipher> zzb = new zzxo();
    private static final ThreadLocal<Cipher> zzc = new zzxr();
    private final byte[] zzd;
    private final byte[] zze;
    private final byte[] zzf;
    private final SecretKeySpec zzg;
    private final int zzh;

    public static zzbg zza(zzdh zzdhVar) throws GeneralSecurityException {
        if (!zza.zza()) {
            throw new GeneralSecurityException("Can not use AES-EAX in FIPS-mode.");
        }
        if (zzdhVar.zzc().zzd() == 16) {
            return new zzxp(zzdhVar.zze().zza(zzbq.zza()), zzdhVar.zzc().zzb(), zzdhVar.zzd().zzb());
        }
        throw new GeneralSecurityException("AesEaxJce only supports 16 byte tag size, not " + zzdhVar.zzc().zzd());
    }

    private zzxp(byte[] bArr, int i, byte[] bArr2) throws GeneralSecurityException {
        if (!zza.zza()) {
            throw new GeneralSecurityException("Can not use AES-EAX in FIPS-mode.");
        }
        if (i != 12 && i != 16) {
            throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
        }
        this.zzh = i;
        zzzb.zza(bArr.length);
        this.zzg = new SecretKeySpec(bArr, "AES");
        Cipher cipher = zzb.get();
        cipher.init(1, this.zzg);
        this.zzd = zza(cipher.doFinal(new byte[16]));
        this.zze = zza(this.zzd);
        this.zzf = bArr2;
    }

    private static void zzc(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbg
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3;
        int length = ((bArr.length - this.zzf.length) - this.zzh) - 16;
        if (length < 0) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        if (!zzpr.zza(this.zzf, bArr)) {
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
        Cipher cipher = zzb.get();
        cipher.init(1, this.zzg);
        byte[] bArrZza = zza(cipher, 0, bArr, this.zzf.length, this.zzh);
        if (bArr2 != null) {
            bArr3 = bArr2;
        } else {
            bArr3 = new byte[0];
        }
        byte[] bArrZza2 = zza(cipher, 1, bArr3, 0, bArr3.length);
        byte[] bArrZza3 = zza(cipher, 2, bArr, this.zzf.length + this.zzh, length);
        int length2 = bArr.length - 16;
        byte b = 0;
        for (int i = 0; i < 16; i++) {
            b = (byte) (b | (((bArr[length2 + i] ^ bArrZza2[i]) ^ bArrZza[i]) ^ bArrZza3[i]));
        }
        if (b != 0) {
            throw new AEADBadTagException("tag mismatch");
        }
        Cipher cipher2 = zzc.get();
        cipher2.init(1, this.zzg, new IvParameterSpec(bArrZza));
        return cipher2.doFinal(bArr, this.zzf.length + this.zzh, length);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbg
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3;
        if (bArr.length > ((Integer.MAX_VALUE - this.zzf.length) - this.zzh) - 16) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] bArrCopyOf = Arrays.copyOf(this.zzf, this.zzf.length + this.zzh + bArr.length + 16);
        byte[] bArrZza = zzpe.zza(this.zzh);
        System.arraycopy(bArrZza, 0, bArrCopyOf, this.zzf.length, this.zzh);
        Cipher cipher = zzb.get();
        cipher.init(1, this.zzg);
        byte[] bArrZza2 = zza(cipher, 0, bArrZza, 0, bArrZza.length);
        if (bArr2 != null) {
            bArr3 = bArr2;
        } else {
            bArr3 = new byte[0];
        }
        byte[] bArrZza3 = zza(cipher, 1, bArr3, 0, bArr3.length);
        Cipher cipher2 = zzc.get();
        cipher2.init(1, this.zzg, new IvParameterSpec(bArrZza2));
        cipher2.doFinal(bArr, 0, bArr.length, bArrCopyOf, this.zzf.length + this.zzh);
        byte[] bArrZza4 = zza(cipher, 2, bArrCopyOf, this.zzf.length + this.zzh, bArr.length);
        int length = this.zzf.length + bArr.length + this.zzh;
        for (int i = 0; i < 16; i++) {
            bArrCopyOf[length + i] = (byte) ((bArrZza3[i] ^ bArrZza2[i]) ^ bArrZza4[i]);
        }
        return bArrCopyOf;
    }

    private static byte[] zza(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int i = 0;
        while (i < 15) {
            int i2 = i + 1;
            bArr2[i] = (byte) ((bArr[i] << 1) ^ ((bArr[i2] & 255) >>> 7));
            i = i2;
        }
        bArr2[15] = (byte) ((bArr[15] << 1) ^ ((bArr[0] >> 7) & TsExtractor.TS_STREAM_TYPE_E_AC3));
        return bArr2;
    }

    private final byte[] zza(Cipher cipher, int i, byte[] bArr, int i2, int i3) throws BadPaddingException, IllegalBlockSizeException, ShortBufferException {
        byte[] bArr2 = new byte[16];
        bArr2[15] = (byte) i;
        if (i3 == 0) {
            zzc(bArr2, this.zzd);
            return cipher.doFinal(bArr2);
        }
        byte[] bArr3 = new byte[16];
        cipher.doFinal(bArr2, 0, 16, bArr3);
        byte[] bArr4 = bArr3;
        byte[] bArr5 = bArr2;
        int i4 = 0;
        while (i3 - i4 > 16) {
            for (int i5 = 0; i5 < 16; i5++) {
                bArr4[i5] = (byte) (bArr4[i5] ^ bArr[(i2 + i4) + i5]);
            }
            cipher.doFinal(bArr4, 0, 16, bArr5);
            i4 += 16;
            byte[] bArr6 = bArr4;
            bArr4 = bArr5;
            bArr5 = bArr6;
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i4 + i2, i2 + i3);
        if (bArrCopyOfRange.length != 16) {
            byte[] bArrCopyOf = Arrays.copyOf(this.zze, 16);
            for (int i6 = 0; i6 < bArrCopyOfRange.length; i6++) {
                bArrCopyOf[i6] = (byte) (bArrCopyOf[i6] ^ bArrCopyOfRange[i6]);
            }
            bArrCopyOf[bArrCopyOfRange.length] = (byte) (bArrCopyOf[bArrCopyOfRange.length] ^ 128);
            bArrCopyOfRange = bArrCopyOf;
        } else {
            zzc(bArrCopyOfRange, this.zzd);
        }
        zzc(bArr4, bArrCopyOfRange);
        cipher.doFinal(bArr4, 0, 16, bArr5);
        return bArr5;
    }
}
