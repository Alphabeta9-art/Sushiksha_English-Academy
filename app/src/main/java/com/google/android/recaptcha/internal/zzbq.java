package com.google.android.recaptcha.internal;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzbq {
    public static final /* synthetic */ int zza = 0;
    private static final ConcurrentHashMap zzb = new ConcurrentHashMap();

    public static final void zza(int i, long j) {
        ConcurrentHashMap concurrentHashMap = zzb;
        Integer numValueOf = Integer.valueOf(i);
        Object zzbpVar = concurrentHashMap.get(numValueOf);
        if (zzbpVar == null) {
            zzbpVar = new zzbp();
        }
        zzbp zzbpVar2 = (zzbp) zzbpVar;
        zzbpVar2.zzg(zzbpVar2.zzb() + 1);
        zzbpVar2.zzf(zzbpVar2.zzd() + j);
        zzbpVar2.zze(Math.max(j, zzbpVar2.zzc()));
        zzb.put(numValueOf, zzbpVar2);
    }
}
