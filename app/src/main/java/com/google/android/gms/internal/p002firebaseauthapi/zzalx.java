package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzalx {
    private static final zzamo<?, ?> zza = new zzamq();

    static int zza(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzajg.zza(i, true);
    }

    static int zza(List<?> list) {
        return list.size();
    }

    static int zza(int i, List<zzaip> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzi = size * zzajg.zzi(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            iZzi += zzajg.zza(list.get(i2));
        }
        return iZzi;
    }

    static int zzb(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzajg.zzi(i));
    }

    static int zzb(List<Integer> list) {
        int iZzc;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzajz) {
            zzajz zzajzVar = (zzajz) list;
            iZzc = 0;
            while (i < size) {
                iZzc += zzajg.zzc(zzajzVar.zzb(i));
                i++;
            }
        } else {
            iZzc = 0;
            while (i < size) {
                iZzc += zzajg.zzc(list.get(i).intValue());
                i++;
            }
        }
        return iZzc;
    }

    static int zzc(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzajg.zzc(i, 0);
    }

    static int zzc(List<?> list) {
        return list.size() << 2;
    }

    static int zzd(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzajg.zza(i, 0L);
    }

    static int zzd(List<?> list) {
        return list.size() << 3;
    }

    static int zza(int i, List<zzalc> list, zzalv<?> zzalvVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZza = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iZza += zzajg.zza(i, list.get(i2), zzalvVar);
        }
        return iZza;
    }

    static int zze(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzajg.zzi(i));
    }

    static int zze(List<Integer> list) {
        int iZze;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzajz) {
            zzajz zzajzVar = (zzajz) list;
            iZze = 0;
            while (i < size) {
                iZze += zzajg.zze(zzajzVar.zzb(i));
                i++;
            }
        } else {
            iZze = 0;
            while (i < size) {
                iZze += zzajg.zze(list.get(i).intValue());
                i++;
            }
        }
        return iZze;
    }

    static int zzf(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzf(list) + (list.size() * zzajg.zzi(i));
    }

    static int zzf(List<Long> list) {
        int iZzd;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzakr) {
            zzakr zzakrVar = (zzakr) list;
            iZzd = 0;
            while (i < size) {
                iZzd += zzajg.zzd(zzakrVar.zzb(i));
                i++;
            }
        } else {
            iZzd = 0;
            while (i < size) {
                iZzd += zzajg.zzd(list.get(i).longValue());
                i++;
            }
        }
        return iZzd;
    }

    static int zza(int i, Object obj, zzalv<?> zzalvVar) {
        if (obj instanceof zzakk) {
            return zzajg.zzb(i, (zzakk) obj);
        }
        return zzajg.zzb(i, (zzalc) obj, zzalvVar);
    }

    static int zzb(int i, List<?> list, zzalv<?> zzalvVar) {
        int iZza;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzi = zzajg.zzi(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzakk) {
                iZza = zzajg.zza((zzakk) obj);
            } else {
                iZza = zzajg.zza((zzalc) obj, zzalvVar);
            }
            iZzi += iZza;
        }
        return iZzi;
    }

    static int zzg(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzajg.zzi(i));
    }

    static int zzg(List<Integer> list) {
        int iZzh;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzajz) {
            zzajz zzajzVar = (zzajz) list;
            iZzh = 0;
            while (i < size) {
                iZzh += zzajg.zzh(zzajzVar.zzb(i));
                i++;
            }
        } else {
            iZzh = 0;
            while (i < size) {
                iZzh += zzajg.zzh(list.get(i).intValue());
                i++;
            }
        }
        return iZzh;
    }

    static int zzh(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzh(list) + (size * zzajg.zzi(i));
    }

    static int zzh(List<Long> list) {
        int iZzf;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzakr) {
            zzakr zzakrVar = (zzakr) list;
            iZzf = 0;
            while (i < size) {
                iZzf += zzajg.zzf(zzakrVar.zzb(i));
                i++;
            }
        } else {
            iZzf = 0;
            while (i < size) {
                iZzf += zzajg.zzf(list.get(i).longValue());
                i++;
            }
        }
        return iZzf;
    }

    static int zzb(int i, List<?> list) {
        int iZza;
        int iZza2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iZzi = zzajg.zzi(i) * size;
        if (list instanceof zzakn) {
            zzakn zzaknVar = (zzakn) list;
            while (i2 < size) {
                Object objZza = zzaknVar.zza(i2);
                if (objZza instanceof zzaip) {
                    iZza2 = zzajg.zza((zzaip) objZza);
                } else {
                    iZza2 = zzajg.zza((String) objZza);
                }
                iZzi += iZza2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzaip) {
                    iZza = zzajg.zza((zzaip) obj);
                } else {
                    iZza = zzajg.zza((String) obj);
                }
                iZzi += iZza;
                i2++;
            }
        }
        return iZzi;
    }

    static int zzi(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzi(list) + (size * zzajg.zzi(i));
    }

    static int zzi(List<Integer> list) {
        int iZzj;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzajz) {
            zzajz zzajzVar = (zzajz) list;
            iZzj = 0;
            while (i < size) {
                iZzj += zzajg.zzj(zzajzVar.zzb(i));
                i++;
            }
        } else {
            iZzj = 0;
            while (i < size) {
                iZzj += zzajg.zzj(list.get(i).intValue());
                i++;
            }
        }
        return iZzj;
    }

    static int zzj(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzj(list) + (size * zzajg.zzi(i));
    }

    static int zzj(List<Long> list) {
        int iZzg;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzakr) {
            zzakr zzakrVar = (zzakr) list;
            iZzg = 0;
            while (i < size) {
                iZzg += zzajg.zzg(zzakrVar.zzb(i));
                i++;
            }
        } else {
            iZzg = 0;
            while (i < size) {
                iZzg += zzajg.zzg(list.get(i).longValue());
                i++;
            }
        }
        return iZzg;
    }

    public static zzamo<?, ?> zza() {
        return zza;
    }

    static <UT, UB> UB zza(Object obj, int i, List<Integer> list, zzakd zzakdVar, UB ub, zzamo<UT, UB> zzamoVar) {
        if (zzakdVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = list.get(i3).intValue();
                if (zzakdVar.zza(iIntValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    ub = (UB) zza(obj, i, iIntValue, ub, zzamoVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = it.next().intValue();
                if (!zzakdVar.zza(iIntValue2)) {
                    ub = (UB) zza(obj, i, iIntValue2, ub, zzamoVar);
                    it.remove();
                }
            }
        }
        return ub;
    }

    static <UT, UB> UB zza(Object obj, int i, int i2, UB ub, zzamo<UT, UB> zzamoVar) {
        if (ub == null) {
            ub = zzamoVar.zzc(obj);
        }
        zzamoVar.zzb(ub, i, i2);
        return ub;
    }

    static <T, FT extends zzajt<FT>> void zza(zzajm<FT> zzajmVar, T t, T t2) {
        zzajr<T> zzajrVarZza = zzajmVar.zza(t2);
        if (!zzajrVarZza.zza.isEmpty()) {
            zzajmVar.zzb(t).zza((zzajr) zzajrVarZza);
        }
    }

    static <T> void zza(zzakz zzakzVar, T t, T t2, long j) {
        zzamp.zza(t, j, zzakzVar.zza(zzamp.zze(t, j), zzamp.zze(t2, j)));
    }

    static <T, UT, UB> void zza(zzamo<UT, UB> zzamoVar, T t, T t2) {
        zzamoVar.zzc(t, zzamoVar.zza(zzamoVar.zzd(t), zzamoVar.zzd(t2)));
    }

    public static void zza(Class<?> cls) {
        zzajy.class.isAssignableFrom(cls);
    }

    public static void zza(int i, List<Boolean> list, zzanf zzanfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zza(i, list, z);
        }
    }

    public static void zza(int i, List<zzaip> list, zzanf zzanfVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zza(i, list);
        }
    }

    public static void zzb(int i, List<Double> list, zzanf zzanfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zzb(i, list, z);
        }
    }

    public static void zzc(int i, List<Integer> list, zzanf zzanfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Integer> list, zzanf zzanfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzanf zzanfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zze(i, list, z);
        }
    }

    public static void zzf(int i, List<Float> list, zzanf zzanfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zzf(i, list, z);
        }
    }

    public static void zza(int i, List<?> list, zzanf zzanfVar, zzalv<?> zzalvVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zza(i, list, (zzalv) zzalvVar);
        }
    }

    public static void zzg(int i, List<Integer> list, zzanf zzanfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zzg(i, list, z);
        }
    }

    public static void zzh(int i, List<Long> list, zzanf zzanfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zzh(i, list, z);
        }
    }

    public static void zzb(int i, List<?> list, zzanf zzanfVar, zzalv<?> zzalvVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zzb(i, list, (zzalv) zzalvVar);
        }
    }

    public static void zzi(int i, List<Integer> list, zzanf zzanfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zzi(i, list, z);
        }
    }

    public static void zzj(int i, List<Long> list, zzanf zzanfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zzj(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzanf zzanfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zzk(i, list, z);
        }
    }

    public static void zzl(int i, List<Long> list, zzanf zzanfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zzl(i, list, z);
        }
    }

    public static void zzb(int i, List<String> list, zzanf zzanfVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zzb(i, list);
        }
    }

    public static void zzm(int i, List<Integer> list, zzanf zzanfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zzm(i, list, z);
        }
    }

    public static void zzn(int i, List<Long> list, zzanf zzanfVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanfVar.zzn(i, list, z);
        }
    }

    static boolean zza(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }
}
