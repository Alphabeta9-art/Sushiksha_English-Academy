package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzek {
    public zzbt zza;
    private final zzem zzb;
    private String zzc = "recaptcha.m.Main.rge";
    private int zzd;
    private final zzel zze;
    private final zzbt zzf;
    private final zzed zzg;
    private final zzcb zzh;

    public zzek(zzem zzemVar) {
        this.zzb = zzemVar;
        zzem zzemVar2 = this.zzb;
        this.zze = zzemVar2.zza();
        this.zzg = zzemVar2.zzd();
        this.zzf = new zzbt();
        this.zzh = this.zzb.zzc();
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzbt zzb() {
        return this.zzf;
    }

    public final zzel zzc() {
        return this.zze;
    }

    public final String zzd() {
        return this.zzc;
    }

    public final void zze() {
        this.zzb.zzb();
    }

    public final void zzf(String str) {
        this.zzc = str;
    }

    public final void zzg(int i) {
        this.zzd = i;
    }

    public final zzcb zzh() {
        return this.zzh;
    }

    public final zzed zzi() {
        return this.zzg;
    }
}
