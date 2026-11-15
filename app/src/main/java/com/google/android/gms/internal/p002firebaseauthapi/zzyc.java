package com.google.android.gms.internal.p002firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzyc implements zzbg {
    private final zzys zza;
    private final zzci zzb;
    private final int zzc;
    private final byte[] zzd;

    public static zzbg zza(zzcz zzczVar) throws GeneralSecurityException {
        return new zzyc(new zzxn(zzczVar.zze().zza(zzbq.zza()), zzczVar.zzc().zzd()), new zzyz(new zzyx("HMAC" + String.valueOf(zzczVar.zzc().zzg()), new SecretKeySpec(zzczVar.zzf().zza(zzbq.zza()), "HMAC")), zzczVar.zzc().zze()), zzczVar.zzc().zze(), zzczVar.zzd().zzb());
    }

    private zzyc(zzys zzysVar, zzci zzciVar, int i, byte[] bArr) {
        this.zza = zzysVar;
        this.zzb = zzciVar;
        this.zzc = i;
        this.zzd = bArr;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbg
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length < this.zzc + this.zzd.length) {
            throw new GeneralSecurityException("Decryption failed (ciphertext too short).");
        }
        if (!zzpr.zza(this.zzd, bArr)) {
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, this.zzd.length, bArr.length - this.zzc);
        byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr, bArr.length - this.zzc, bArr.length);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        this.zzb.zza(bArrCopyOfRange2, zzxv.zza(bArr2, bArrCopyOfRange, Arrays.copyOf(ByteBuffer.allocate(8).putLong(((long) bArr2.length) * 8).array(), 8)));
        return this.zza.zza(bArrCopyOfRange);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbg
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArrZzb = this.zza.zzb(bArr);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        return zzxv.zza(this.zzd, bArrZzb, this.zzb.zza(zzxv.zza(bArr2, bArrZzb, Arrays.copyOf(ByteBuffer.allocate(8).putLong(((long) bArr2.length) * 8).array(), 8))));
    }
}
