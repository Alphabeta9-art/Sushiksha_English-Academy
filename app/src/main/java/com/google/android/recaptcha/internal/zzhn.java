package com.google.android.recaptcha.internal;

import com.google.common.base.Ascii;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public abstract class zzhn {
    private static final char[] zza = "0123456789abcdef".toCharArray();
    public static final /* synthetic */ int zzb = 0;

    zzhn() {
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzhn) {
            zzhn zzhnVar = (zzhn) obj;
            if (zzb() == zzhnVar.zzb() && zzc(zzhnVar)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        if (zzb() >= 32) {
            return zza();
        }
        byte[] bArrZze = zze();
        int i = bArrZze[0] & 255;
        for (int i2 = 1; i2 < bArrZze.length; i2++) {
            i |= (bArrZze[i2] & 255) << (i2 * 8);
        }
        return i;
    }

    public final String toString() {
        byte[] bArrZze = zze();
        int length = bArrZze.length;
        StringBuilder sb = new StringBuilder(length + length);
        for (byte b : bArrZze) {
            sb.append(zza[(b >> 4) & 15]);
            sb.append(zza[b & Ascii.SI]);
        }
        return sb.toString();
    }

    public abstract int zza();

    public abstract int zzb();

    abstract boolean zzc(zzhn zzhnVar);

    public abstract byte[] zzd();

    byte[] zze() {
        throw null;
    }
}
