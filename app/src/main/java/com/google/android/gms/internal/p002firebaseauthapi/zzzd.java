package com.google.android.gms.internal.p002firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzzd implements zzbg {
    private final zzhs zza;
    private final byte[] zzb;

    public static zzbg zza(zzfv zzfvVar) throws GeneralSecurityException {
        return new zzzd(zzfvVar.zzd().zza(zzbq.zza()), zzfvVar.zzc().zzb());
    }

    private zzzd(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        this.zza = new zzhs(bArr);
        this.zzb = bArr2;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbg
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (this.zzb.length == 0) {
            return zzc(bArr, bArr2);
        }
        if (!zzpr.zza(this.zzb, bArr)) {
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
        return zzc(Arrays.copyOfRange(bArr, this.zzb.length, bArr.length), bArr2);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbg
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr.length + 24 + 16);
        byte[] bArrZza = zzpe.zza(24);
        byteBufferAllocate.put(bArrZza);
        this.zza.zza(byteBufferAllocate, bArrZza, bArr, bArr2);
        byte[] bArrArray = byteBufferAllocate.array();
        if (this.zzb.length == 0) {
            return bArrArray;
        }
        return zzxv.zza(this.zzb, bArrArray);
    }

    private final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length < 40) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr, 24);
        return this.zza.zza(ByteBuffer.wrap(bArr, 24, bArr.length - 24), bArrCopyOf, bArr2);
    }
}
