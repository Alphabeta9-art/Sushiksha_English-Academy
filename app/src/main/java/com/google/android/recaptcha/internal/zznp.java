package com.google.android.recaptcha.internal;

import androidx.media3.common.C;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zznp {
    static {
        zzjz zzjzVarZzi = zzka.zzi();
        zzjzVarZzi.zze(-315576000000L);
        zzjzVarZzi.zzd(-999999999);
        zzjz zzjzVarZzi2 = zzka.zzi();
        zzjzVarZzi2.zze(315576000000L);
        zzjzVarZzi2.zzd(999999999);
        zzjz zzjzVarZzi3 = zzka.zzi();
        zzjzVarZzi3.zze(0L);
        zzjzVarZzi3.zzd(0);
    }

    public static zzka zza(long j) {
        int i = (int) (j % C.NANOS_PER_SECOND);
        long jZza = j / C.NANOS_PER_SECOND;
        if (i <= -1000000000 || i >= 1000000000) {
            jZza = zzic.zza(jZza, i / 1000000000);
            i %= 1000000000;
        }
        if (jZza > 0 && i < 0) {
            i += 1000000000;
            jZza--;
        }
        if (jZza < 0 && i > 0) {
            i -= 1000000000;
            jZza++;
        }
        zzjz zzjzVarZzi = zzka.zzi();
        zzjzVarZzi.zze(jZza);
        zzjzVarZzi.zzd(i);
        zzka zzkaVar = (zzka) zzjzVarZzi.zzj();
        long jZzg = zzkaVar.zzg();
        int iZzf = zzkaVar.zzf();
        if (jZzg < -315576000000L || jZzg > 315576000000L || iZzf < -999999999 || iZzf >= 1000000000 || ((jZzg < 0 || iZzf < 0) && (jZzg > 0 || iZzf > 0))) {
            throw new IllegalArgumentException(String.format("Duration is not valid. See proto definition for valid values. Seconds (%s) must be in range [-315,576,000,000, +315,576,000,000]. Nanos (%s) must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds", Long.valueOf(jZzg), Integer.valueOf(iZzf)));
        }
        return zzkaVar;
    }
}
