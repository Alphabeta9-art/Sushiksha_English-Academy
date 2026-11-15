package com.google.android.recaptcha.internal;

import java.math.BigInteger;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzgc {
    private static final zzgb zza;
    private final zzgb zzb;
    private long zzc;

    static {
        long jPow = (long) Math.pow(2.0d, 32.0d);
        zza = new zzgb(11L, 20919936621L ^ jPow, (long) Math.pow(2.0d, 48.0d));
    }

    public zzgc(long j, long j2, zzgb zzgbVar) {
        this.zzb = zzgbVar;
        this.zzc = Math.abs(j);
    }

    public final long zza() {
        zzgb zzgbVar = this.zzb;
        long jLongValue = (BigInteger.valueOf(zzgbVar.zzb()).multiply(BigInteger.valueOf(this.zzc)).mod(BigInteger.valueOf(zzgbVar.zza())).longValue() + 11) % this.zzb.zza();
        this.zzc = jLongValue;
        return jLongValue % 255;
    }
}
