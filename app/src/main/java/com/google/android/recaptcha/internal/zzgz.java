package com.google.android.recaptcha.internal;

import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import com.cloudinary.metadata.MetadataValidation;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzgz {
    private boolean zza;
    private long zzb;
    private long zzc;

    zzgz() {
    }

    public static zzgz zzb() {
        zzgz zzgzVar = new zzgz();
        zzgzVar.zze();
        return zzgzVar;
    }

    public static zzgz zzc() {
        return new zzgz();
    }

    private final long zzg() {
        return this.zza ? (System.nanoTime() - this.zzc) + this.zzb : this.zzb;
    }

    public final String toString() {
        TimeUnit timeUnit;
        String str;
        long jZzg = zzg();
        if (TimeUnit.DAYS.convert(jZzg, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.DAYS;
        } else if (TimeUnit.HOURS.convert(jZzg, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.HOURS;
        } else if (TimeUnit.MINUTES.convert(jZzg, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.MINUTES;
        } else if (TimeUnit.SECONDS.convert(jZzg, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.SECONDS;
        } else if (TimeUnit.MILLISECONDS.convert(jZzg, TimeUnit.NANOSECONDS) > 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        } else {
            timeUnit = TimeUnit.MICROSECONDS.convert(jZzg, TimeUnit.NANOSECONDS) > 0 ? TimeUnit.MICROSECONDS : TimeUnit.NANOSECONDS;
        }
        String str2 = String.format(Locale.ROOT, "%.4g", Double.valueOf(jZzg / TimeUnit.NANOSECONDS.convert(1L, timeUnit)));
        switch (zzgy.zza[timeUnit.ordinal()]) {
            case 1:
                str = "ns";
                break;
            case 2:
                str = "μs";
                break;
            case 3:
                str = "ms";
                break;
            case 4:
                str = CmcdHeadersFactory.STREAMING_FORMAT_SS;
                break;
            case 5:
                str = MetadataValidation.MIN;
                break;
            case 6:
                str = CmcdHeadersFactory.STREAMING_FORMAT_HLS;
                break;
            case 7:
                str = "d";
                break;
            default:
                throw new AssertionError();
        }
        return str2 + " " + str;
    }

    public final long zza(TimeUnit timeUnit) {
        return timeUnit.convert(zzg(), TimeUnit.NANOSECONDS);
    }

    public final zzgz zzd() {
        this.zzb = 0L;
        this.zza = false;
        return this;
    }

    public final zzgz zze() {
        zzgx.zze(!this.zza, "This stopwatch is already running.");
        this.zza = true;
        this.zzc = System.nanoTime();
        return this;
    }

    public final zzgz zzf() {
        long jNanoTime = System.nanoTime();
        zzgx.zze(this.zza, "This stopwatch is already stopped.");
        this.zza = false;
        this.zzb += jNanoTime - this.zzc;
        return this;
    }
}
