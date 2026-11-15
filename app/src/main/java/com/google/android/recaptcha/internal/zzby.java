package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzby extends Exception {
    private final Throwable zza;
    private final zzrh zzb;
    private final int zzc;
    private final int zzd;

    public zzby(int i, int i2, Throwable th) {
        this.zzc = i;
        this.zzd = i2;
        this.zza = th;
        zzrh zzrhVarZzf = zzri.zzf();
        zzrhVarZzf.zzp(this.zzd);
        zzrhVarZzf.zzq(this.zzc);
        this.zzb = zzrhVarZzf;
    }

    @Override // java.lang.Throwable
    public final Throwable getCause() {
        return this.zza;
    }

    public final zzrh zza() {
        return this.zzb;
    }

    public final int zzb() {
        return this.zzd;
    }
}
