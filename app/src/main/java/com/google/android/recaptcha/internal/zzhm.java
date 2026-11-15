package com.google.android.recaptcha.internal;

import java.io.Serializable;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzhm extends zzhn implements Serializable {
    final byte[] zza;

    zzhm(byte[] bArr) {
        if (bArr == null) {
            throw null;
        }
        this.zza = bArr;
    }

    @Override // com.google.android.recaptcha.internal.zzhn
    public final int zzb() {
        return this.zza.length * 8;
    }

    @Override // com.google.android.recaptcha.internal.zzhn
    final boolean zzc(zzhn zzhnVar) {
        if (this.zza.length != zzhnVar.zze().length) {
            return false;
        }
        boolean z = true;
        int i = 0;
        while (true) {
            byte[] bArr = this.zza;
            if (i >= bArr.length) {
                return z;
            }
            z &= bArr[i] == zzhnVar.zze()[i];
            i++;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzhn
    public final byte[] zzd() {
        return (byte[]) this.zza.clone();
    }

    @Override // com.google.android.recaptcha.internal.zzhn
    final byte[] zze() {
        return this.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzhn
    public final int zza() {
        byte[] bArr = this.zza;
        int length = bArr.length;
        if (length < 4) {
            throw new IllegalStateException(zzha.zza("HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", Integer.valueOf(length)));
        }
        return (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16) | ((bArr[3] & 255) << 24);
    }
}
