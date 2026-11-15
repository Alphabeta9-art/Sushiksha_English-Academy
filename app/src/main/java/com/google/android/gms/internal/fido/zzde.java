package com.google.android.gms.internal.fido;

import com.bumptech.glide.load.Key;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzde {
    static final Charset zza = Charset.forName("US-ASCII");
    static final Charset zzb = Charset.forName(Key.STRING_CHARSET_NAME);
    static final Charset zzc = Charset.forName("ISO-8859-1");
    public static final byte[] zzd = new byte[0];
    public static final ByteBuffer zze = ByteBuffer.wrap(zzd);
    public static final zzdd zzf;

    static {
        byte[] bArr = zzd;
        int i = zzdd.zza;
        int length = bArr.length;
        zzdb zzdbVar = new zzdb(bArr, 0, 0, false, null);
        try {
            zzdbVar.zza(0);
            zzf = zzdbVar;
        } catch (zzdf e) {
            throw new IllegalArgumentException(e);
        }
    }
}
