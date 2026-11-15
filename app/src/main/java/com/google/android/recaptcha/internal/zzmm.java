package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzmm {
    public static final /* synthetic */ int zza = 0;
    private static final Class zzb;
    private static final zznb zzc;
    private static final zznb zzd;

    static {
        Class<?> cls;
        Class<?> cls2;
        zznb zznbVar = null;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            cls = null;
        }
        zzb = cls;
        try {
            cls2 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th2) {
            cls2 = null;
        }
        if (cls2 != null) {
            try {
                zznbVar = (zznb) cls2.getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable th3) {
            }
        }
        zzc = zznbVar;
        zzd = new zznd();
    }

    public static void zzA(int i, List list, zzno zznoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznoVar.zzu(i, list, z);
    }

    public static void zzB(int i, List list, zzno zznoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznoVar.zzy(i, list, z);
    }

    public static void zzC(int i, List list, zzno zznoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznoVar.zzA(i, list, z);
    }

    public static void zzD(int i, List list, zzno zznoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznoVar.zzC(i, list, z);
    }

    public static void zzE(int i, List list, zzno zznoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznoVar.zzE(i, list, z);
    }

    public static void zzF(int i, List list, zzno zznoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznoVar.zzJ(i, list, z);
    }

    public static void zzG(int i, List list, zzno zznoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznoVar.zzL(i, list, z);
    }

    static boolean zzH(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static int zza(List list) {
        int iZzy;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkt) {
            zzkt zzktVar = (zzkt) list;
            iZzy = 0;
            while (i < size) {
                iZzy += zzjg.zzy(zzktVar.zze(i));
                i++;
            }
        } else {
            iZzy = 0;
            while (i < size) {
                iZzy += zzjg.zzy(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzy;
    }

    static int zzb(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzjg.zzx(i << 3) + 4);
    }

    static int zzc(List list) {
        return list.size() * 4;
    }

    static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzjg.zzx(i << 3) + 8);
    }

    static int zze(List list) {
        return list.size() * 8;
    }

    static int zzf(List list) {
        int iZzy;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkt) {
            zzkt zzktVar = (zzkt) list;
            iZzy = 0;
            while (i < size) {
                iZzy += zzjg.zzy(zzktVar.zze(i));
                i++;
            }
        } else {
            iZzy = 0;
            while (i < size) {
                iZzy += zzjg.zzy(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzy;
    }

    static int zzg(List list) {
        int iZzy;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzlm) {
            zzlm zzlmVar = (zzlm) list;
            iZzy = 0;
            while (i < size) {
                iZzy += zzjg.zzy(zzlmVar.zze(i));
                i++;
            }
        } else {
            iZzy = 0;
            while (i < size) {
                iZzy += zzjg.zzy(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iZzy;
    }

    static int zzh(int i, Object obj, zzmk zzmkVar) {
        int i2 = i << 3;
        if (!(obj instanceof zzli)) {
            return zzjg.zzx(i2) + zzjg.zzv((zzlx) obj, zzmkVar);
        }
        int iZzx = zzjg.zzx(i2);
        int iZza = ((zzli) obj).zza();
        return iZzx + zzjg.zzx(iZza) + iZza;
    }

    static int zzi(List list) {
        int iZzx;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkt) {
            zzkt zzktVar = (zzkt) list;
            iZzx = 0;
            while (i < size) {
                int iZze = zzktVar.zze(i);
                iZzx += zzjg.zzx((iZze + iZze) ^ (iZze >> 31));
                i++;
            }
        } else {
            iZzx = 0;
            while (i < size) {
                int iIntValue = ((Integer) list.get(i)).intValue();
                iZzx += zzjg.zzx((iIntValue + iIntValue) ^ (iIntValue >> 31));
                i++;
            }
        }
        return iZzx;
    }

    static int zzj(List list) {
        int iZzy;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzlm) {
            zzlm zzlmVar = (zzlm) list;
            iZzy = 0;
            while (i < size) {
                long jZze = zzlmVar.zze(i);
                iZzy += zzjg.zzy((jZze + jZze) ^ (jZze >> 63));
                i++;
            }
        } else {
            iZzy = 0;
            while (i < size) {
                long jLongValue = ((Long) list.get(i)).longValue();
                iZzy += zzjg.zzy((jLongValue + jLongValue) ^ (jLongValue >> 63));
                i++;
            }
        }
        return iZzy;
    }

    static int zzk(List list) {
        int iZzx;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkt) {
            zzkt zzktVar = (zzkt) list;
            iZzx = 0;
            while (i < size) {
                iZzx += zzjg.zzx(zzktVar.zze(i));
                i++;
            }
        } else {
            iZzx = 0;
            while (i < size) {
                iZzx += zzjg.zzx(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzx;
    }

    static int zzl(List list) {
        int iZzy;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzlm) {
            zzlm zzlmVar = (zzlm) list;
            iZzy = 0;
            while (i < size) {
                iZzy += zzjg.zzy(zzlmVar.zze(i));
                i++;
            }
        } else {
            iZzy = 0;
            while (i < size) {
                iZzy += zzjg.zzy(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iZzy;
    }

    public static zznb zzm() {
        return zzc;
    }

    public static zznb zzn() {
        return zzd;
    }

    static Object zzo(Object obj, int i, List list, zzkw zzkwVar, Object obj2, zznb zznbVar) {
        if (zzkwVar == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = ((Integer) list.get(i3)).intValue();
                if (zzkwVar.zza(iIntValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    obj2 = zzp(obj, i, iIntValue, obj2, zznbVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return obj2;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = ((Integer) it.next()).intValue();
                if (!zzkwVar.zza(iIntValue2)) {
                    obj2 = zzp(obj, i, iIntValue2, obj2, zznbVar);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    static Object zzp(Object obj, int i, int i2, Object obj2, zznb zznbVar) {
        if (obj2 == null) {
            obj2 = zznbVar.zzc(obj);
        }
        zznbVar.zzl(obj2, i, i2);
        return obj2;
    }

    static void zzq(zzke zzkeVar, Object obj, Object obj2) {
        zzki zzkiVarZzb = zzkeVar.zzb(obj2);
        if (zzkiVarZzb.zza.isEmpty()) {
            return;
        }
        zzkeVar.zzc(obj).zzh(zzkiVarZzb);
    }

    static void zzr(zznb zznbVar, Object obj, Object obj2) {
        zznbVar.zzo(obj, zznbVar.zze(zznbVar.zzd(obj), zznbVar.zzd(obj2)));
    }

    public static void zzs(Class cls) {
        Class cls2;
        if (!zzks.class.isAssignableFrom(cls) && (cls2 = zzb) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzt(int i, List list, zzno zznoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznoVar.zzc(i, list, z);
    }

    public static void zzu(int i, List list, zzno zznoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznoVar.zzg(i, list, z);
    }

    public static void zzv(int i, List list, zzno zznoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznoVar.zzj(i, list, z);
    }

    public static void zzw(int i, List list, zzno zznoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznoVar.zzl(i, list, z);
    }

    public static void zzx(int i, List list, zzno zznoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznoVar.zzn(i, list, z);
    }

    public static void zzy(int i, List list, zzno zznoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznoVar.zzp(i, list, z);
    }

    public static void zzz(int i, List list, zzno zznoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznoVar.zzs(i, list, z);
    }
}
