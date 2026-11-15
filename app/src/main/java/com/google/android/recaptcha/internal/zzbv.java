package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzbv {
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;

    public zzbv() {
        this("https://www.recaptcha.net/recaptcha/api3");
    }

    public zzbv(String str) {
        this.zza = "https://www.recaptcha.net/recaptcha/api3";
        String str2 = this.zza;
        this.zzb = str2.concat("/mri");
        this.zzc = str2.concat("/mlg");
        this.zzd = str2.concat("/mrr");
    }

    public final String zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zzd;
    }
}
