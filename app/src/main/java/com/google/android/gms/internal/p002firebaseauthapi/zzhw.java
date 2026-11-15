package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzhw implements zzbg {
    private final byte[] zza;
    private final int zzb;
    private final zzrq zzc;

    public static zzbg zza(zzfp zzfpVar) throws GeneralSecurityException {
        if (zzfpVar.zzb().zzb() < 8 || zzfpVar.zzb().zzb() > 12) {
            throw new GeneralSecurityException("invalid salt size");
        }
        return new zzhw(zzfpVar.zzd().zza(zzbq.zza()), zzfpVar.zzc(), zzfpVar.zzb().zzb());
    }

    private zzhw(byte[] bArr, zzzc zzzcVar, int i) throws GeneralSecurityException {
        this.zzc = new zzyv(bArr);
        this.zza = zzzcVar.zzb();
        this.zzb = i;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbg
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr == null) {
            throw new NullPointerException("ciphertext is null");
        }
        if (bArr.length < this.zza.length + this.zzb + 12 + 16) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        if (!zzpr.zza(this.zza, bArr)) {
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
        int length = this.zza.length + this.zzb;
        int i = length + 12;
        return new zzhj(zza(Arrays.copyOfRange(bArr, this.zza.length, length))).zza(Arrays.copyOfRange(bArr, length, i), bArr, i, bArr2);
    }

    private final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        byte[] bArr2 = {0, 1, 88, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        byte[] bArr3 = {0, 2, 88, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        if (bArr.length > 12 || bArr.length < 8) {
            throw new GeneralSecurityException("invalid salt size");
        }
        System.arraycopy(bArr, 0, bArr2, 4, bArr.length);
        System.arraycopy(bArr, 0, bArr3, 4, bArr.length);
        byte[] bArr4 = new byte[32];
        System.arraycopy(this.zzc.zza(bArr2, 16), 0, bArr4, 0, 16);
        System.arraycopy(this.zzc.zza(bArr3, 16), 0, bArr4, 16, 16);
        return bArr4;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbg
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr == null) {
            throw new NullPointerException("plaintext is null");
        }
        byte[] bArrZza = zzpe.zza(this.zzb + 12);
        byte[] bArrCopyOf = Arrays.copyOf(bArrZza, this.zzb);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArrZza, this.zzb, this.zzb + 12);
        byte[] bArrZzb = new zzhj(zza(bArrCopyOf)).zzb(bArrCopyOfRange, bArr, this.zza.length + this.zzb + bArrCopyOfRange.length, bArr2);
        System.arraycopy(this.zza, 0, bArrZzb, 0, this.zza.length);
        System.arraycopy(bArrZza, 0, bArrZzb, this.zza.length, bArrZza.length);
        return bArrZzb;
    }
}
