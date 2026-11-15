package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
abstract class zzamo<T, B> {
    private static volatile int zza = 100;

    abstract int zza(T t);

    abstract B zza();

    abstract T zza(T t, T t2);

    abstract void zza(B b, int i, int i2);

    abstract void zza(B b, int i, long j);

    abstract void zza(B b, int i, zzaip zzaipVar);

    abstract void zza(B b, int i, T t);

    abstract void zza(T t, zzanf zzanfVar) throws IOException;

    abstract boolean zza(zzals zzalsVar);

    abstract int zzb(T t);

    abstract void zzb(B b, int i, long j);

    abstract void zzb(T t, zzanf zzanfVar) throws IOException;

    abstract void zzb(Object obj, B b);

    abstract B zzc(Object obj);

    abstract void zzc(Object obj, T t);

    abstract T zzd(Object obj);

    abstract T zze(B b);

    abstract void zzf(Object obj);

    zzamo() {
    }

    final boolean zza(B b, zzals zzalsVar, int i) throws IOException {
        int iZzd = zzalsVar.zzd();
        int i2 = iZzd >>> 3;
        switch (iZzd & 7) {
            case 0:
                zzb(b, i2, zzalsVar.zzl());
                return true;
            case 1:
                zza(b, i2, zzalsVar.zzk());
                return true;
            case 2:
                zza((Object) b, i2, zzalsVar.zzp());
                return true;
            case 3:
                B bZza = zza();
                int i3 = (i2 << 3) | 4;
                int i4 = i + 1;
                if (i4 >= zza) {
                    throw zzakf.zzh();
                }
                while (zzalsVar.zzc() != Integer.MAX_VALUE && zza(bZza, zzalsVar, i4)) {
                }
                if (i3 != zzalsVar.zzd()) {
                    throw zzakf.zzb();
                }
                zza(b, i2, zze(bZza));
                return true;
            case 4:
                return false;
            case 5:
                zza((Object) b, i2, zzalsVar.zzf());
                return true;
            default:
                throw zzakf.zza();
        }
    }
}
