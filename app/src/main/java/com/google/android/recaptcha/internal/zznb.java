package com.google.android.recaptcha.internal;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
abstract class zznb {
    zznb() {
    }

    abstract int zza(Object obj);

    abstract int zzb(Object obj);

    abstract Object zzc(Object obj);

    abstract Object zzd(Object obj);

    abstract Object zze(Object obj, Object obj2);

    abstract Object zzf();

    abstract Object zzg(Object obj);

    abstract void zzh(Object obj, int i, int i2);

    abstract void zzi(Object obj, int i, long j);

    abstract void zzj(Object obj, int i, Object obj2);

    abstract void zzk(Object obj, int i, zziv zzivVar);

    abstract void zzl(Object obj, int i, long j);

    abstract void zzm(Object obj);

    abstract void zzn(Object obj, Object obj2);

    abstract void zzo(Object obj, Object obj2);

    abstract void zzp(Object obj, zzno zznoVar) throws IOException;

    abstract void zzq(Object obj, zzno zznoVar) throws IOException;

    abstract boolean zzs(zzmj zzmjVar);

    final boolean zzr(Object obj, zzmj zzmjVar) throws IOException {
        int iZzd = zzmjVar.zzd();
        int i = iZzd >>> 3;
        switch (iZzd & 7) {
            case 0:
                zzl(obj, i, zzmjVar.zzl());
                return true;
            case 1:
                zzi(obj, i, zzmjVar.zzk());
                return true;
            case 2:
                zzk(obj, i, zzmjVar.zzp());
                return true;
            case 3:
                Object objZzf = zzf();
                int i2 = i << 3;
                while (zzmjVar.zzc() != Integer.MAX_VALUE && zzr(objZzf, zzmjVar)) {
                }
                if ((i2 | 4) != zzmjVar.zzd()) {
                    throw zzlc.zzb();
                }
                zzg(objZzf);
                zzj(obj, i, objZzf);
                return true;
            case 4:
                return false;
            case 5:
                zzh(obj, i, zzmjVar.zzf());
                return true;
            default:
                throw zzlc.zza();
        }
    }
}
