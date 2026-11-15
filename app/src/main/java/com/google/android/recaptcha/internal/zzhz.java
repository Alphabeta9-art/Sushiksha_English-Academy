package com.google.android.recaptcha.internal;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public abstract class zzhz {
    private static final zzhz zza = new zzhw("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    private static final zzhz zzb = new zzhw("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');

    static {
        new zzhy("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
        new zzhy("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
        zzhv zzhvVar = new zzhv("base16()", "0123456789ABCDEF".toCharArray());
        new zzhy(zzhvVar, null);
        char[] cArr = new char[512];
        zzgx.zza(zzhvVar.zzf.length == 16);
        for (int i = 0; i < 256; i++) {
            cArr[i] = zzhvVar.zza(i >>> 4);
            cArr[i | 256] = zzhvVar.zza(i & 15);
        }
    }

    zzhz() {
    }

    public static zzhz zzg() {
        return zza;
    }

    public static zzhz zzh() {
        return zzb;
    }

    abstract int zza(byte[] bArr, CharSequence charSequence) throws zzhx;

    abstract void zzb(Appendable appendable, byte[] bArr, int i, int i2) throws IOException;

    abstract int zzc(int i);

    abstract int zzd(int i);

    CharSequence zze(CharSequence charSequence) {
        throw null;
    }

    public final String zzi(byte[] bArr, int i, int i2) {
        zzgx.zzd(0, i2, bArr.length);
        StringBuilder sb = new StringBuilder(zzd(i2));
        try {
            zzb(sb, bArr, 0, i2);
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final byte[] zzj(CharSequence charSequence) {
        try {
            CharSequence charSequenceZze = zze(charSequence);
            int iZzc = zzc(charSequenceZze.length());
            byte[] bArr = new byte[iZzc];
            int iZza = zza(bArr, charSequenceZze);
            if (iZza == iZzc) {
                return bArr;
            }
            byte[] bArr2 = new byte[iZza];
            System.arraycopy(bArr, 0, bArr2, 0, iZza);
            return bArr2;
        } catch (zzhx e) {
            throw new IllegalArgumentException(e);
        }
    }
}
