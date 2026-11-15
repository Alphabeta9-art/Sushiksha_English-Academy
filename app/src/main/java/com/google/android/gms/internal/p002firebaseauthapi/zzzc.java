package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzzc {
    private final byte[] zza;

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    public final int zza() {
        return this.zza.length;
    }

    public static zzzc zza(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("data must be non-null");
        }
        int length = bArr.length;
        if (bArr == null) {
            throw new NullPointerException("data must be non-null");
        }
        if (length > bArr.length) {
            length = bArr.length;
        }
        return new zzzc(bArr, 0, length);
    }

    public final String toString() {
        return "Bytes(" + zzyt.zza(this.zza) + ")";
    }

    private zzzc(byte[] bArr, int i, int i2) {
        this.zza = new byte[i2];
        System.arraycopy(bArr, 0, this.zza, 0, i2);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzzc)) {
            return false;
        }
        return Arrays.equals(((zzzc) obj).zza, this.zza);
    }

    public final byte[] zzb() {
        byte[] bArr = new byte[this.zza.length];
        System.arraycopy(this.zza, 0, bArr, 0, this.zza.length);
        return bArr;
    }
}
