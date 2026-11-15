package com.google.android.recaptcha.internal;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzdn {
    public static final zzoy zza(zzbt zzbtVar, zzbt zzbtVar2) {
        zzox zzoxVarZzf = zzoy.zzf();
        zzoxVarZzf.zzp(zznr.zzb(zzbtVar.zzb()));
        zzoxVarZzf.zzq(zznp.zza(zzbtVar.zza(TimeUnit.NANOSECONDS)));
        zzoxVarZzf.zzd(zznr.zzb(zzbtVar2.zzb()));
        zzoxVarZzf.zze(zznp.zza(zzbtVar2.zza(TimeUnit.NANOSECONDS)));
        return (zzoy) zzoxVarZzf.zzj();
    }
}
