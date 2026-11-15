package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzajj implements zzanf {
    private final zzajg zza;

    public static zzajj zza(zzajg zzajgVar) {
        return zzajgVar.zze != null ? zzajgVar.zze : new zzajj(zzajgVar);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final int zza() {
        return 1;
    }

    private zzajj(zzajg zzajgVar) {
        this.zza = (zzajg) zzakb.zza(zzajgVar, "output");
        this.zza.zze = this;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zza(int i, boolean z) throws IOException {
        this.zza.zzb(i, z);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zza(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzain) {
            zzain zzainVar = (zzain) list;
            if (z) {
                this.zza.zzk(i, 2);
                int iZza = 0;
                for (int i3 = 0; i3 < zzainVar.size(); i3++) {
                    iZza += zzajg.zza(zzainVar.zzb(i3));
                }
                this.zza.zzn(iZza);
                while (i2 < zzainVar.size()) {
                    this.zza.zzb(zzainVar.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzainVar.size()) {
                this.zza.zzb(i, zzainVar.zzb(i2));
                i2++;
            }
            return;
        }
        if (z) {
            this.zza.zzk(i, 2);
            int iZza2 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                iZza2 += zzajg.zza(list.get(i4).booleanValue());
            }
            this.zza.zzn(iZza2);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzb(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zza(int i, zzaip zzaipVar) throws IOException {
        this.zza.zzc(i, zzaipVar);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zza(int i, List<zzaip> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zzc(i, list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zza(int i, double d) throws IOException {
        this.zza.zzb(i, d);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzb(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzaji) {
            zzaji zzajiVar = (zzaji) list;
            if (z) {
                this.zza.zzk(i, 2);
                int iZza = 0;
                for (int i3 = 0; i3 < zzajiVar.size(); i3++) {
                    iZza += zzajg.zza(zzajiVar.zzb(i3));
                }
                this.zza.zzn(iZza);
                while (i2 < zzajiVar.size()) {
                    this.zza.zzb(zzajiVar.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzajiVar.size()) {
                this.zza.zzb(i, zzajiVar.zzb(i2));
                i2++;
            }
            return;
        }
        if (z) {
            this.zza.zzk(i, 2);
            int iZza2 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                iZza2 += zzajg.zza(list.get(i4).doubleValue());
            }
            this.zza.zzn(iZza2);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzb(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    @Deprecated
    public final void zza(int i) throws IOException {
        this.zza.zzk(i, 4);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zza(int i, int i2) throws IOException {
        this.zza.zzi(i, i2);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzc(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzajz) {
            zzajz zzajzVar = (zzajz) list;
            if (z) {
                this.zza.zzk(i, 2);
                int iZzc = 0;
                for (int i3 = 0; i3 < zzajzVar.size(); i3++) {
                    iZzc += zzajg.zzc(zzajzVar.zzb(i3));
                }
                this.zza.zzn(iZzc);
                while (i2 < zzajzVar.size()) {
                    this.zza.zzl(zzajzVar.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzajzVar.size()) {
                this.zza.zzi(i, zzajzVar.zzb(i2));
                i2++;
            }
            return;
        }
        if (z) {
            this.zza.zzk(i, 2);
            int iZzc2 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                iZzc2 += zzajg.zzc(list.get(i4).intValue());
            }
            this.zza.zzn(iZzc2);
            while (i2 < list.size()) {
                this.zza.zzl(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzi(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzb(int i, int i2) throws IOException {
        this.zza.zzh(i, i2);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzd(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzajz) {
            zzajz zzajzVar = (zzajz) list;
            if (z) {
                this.zza.zzk(i, 2);
                int iZzd = 0;
                for (int i3 = 0; i3 < zzajzVar.size(); i3++) {
                    iZzd += zzajg.zzd(zzajzVar.zzb(i3));
                }
                this.zza.zzn(iZzd);
                while (i2 < zzajzVar.size()) {
                    this.zza.zzk(zzajzVar.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzajzVar.size()) {
                this.zza.zzh(i, zzajzVar.zzb(i2));
                i2++;
            }
            return;
        }
        if (z) {
            this.zza.zzk(i, 2);
            int iZzd2 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                iZzd2 += zzajg.zzd(list.get(i4).intValue());
            }
            this.zza.zzn(iZzd2);
            while (i2 < list.size()) {
                this.zza.zzk(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzh(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zza(int i, long j) throws IOException {
        this.zza.zzf(i, j);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzakr) {
            zzakr zzakrVar = (zzakr) list;
            if (z) {
                this.zza.zzk(i, 2);
                int iZzc = 0;
                for (int i3 = 0; i3 < zzakrVar.size(); i3++) {
                    iZzc += zzajg.zzc(zzakrVar.zzb(i3));
                }
                this.zza.zzn(iZzc);
                while (i2 < zzakrVar.size()) {
                    this.zza.zzh(zzakrVar.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzakrVar.size()) {
                this.zza.zzf(i, zzakrVar.zzb(i2));
                i2++;
            }
            return;
        }
        if (z) {
            this.zza.zzk(i, 2);
            int iZzc2 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                iZzc2 += zzajg.zzc(list.get(i4).longValue());
            }
            this.zza.zzn(iZzc2);
            while (i2 < list.size()) {
                this.zza.zzh(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzf(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zza(int i, float f) throws IOException {
        this.zza.zzb(i, f);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzajx) {
            zzajx zzajxVar = (zzajx) list;
            if (z) {
                this.zza.zzk(i, 2);
                int iZza = 0;
                for (int i3 = 0; i3 < zzajxVar.size(); i3++) {
                    iZza += zzajg.zza(zzajxVar.zzb(i3));
                }
                this.zza.zzn(iZza);
                while (i2 < zzajxVar.size()) {
                    this.zza.zzb(zzajxVar.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzajxVar.size()) {
                this.zza.zzb(i, zzajxVar.zzb(i2));
                i2++;
            }
            return;
        }
        if (z) {
            this.zza.zzk(i, 2);
            int iZza2 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                iZza2 += zzajg.zza(list.get(i4).floatValue());
            }
            this.zza.zzn(iZza2);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzb(i, list.get(i2).floatValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zza(int i, Object obj, zzalv zzalvVar) throws IOException {
        zzajg zzajgVar = this.zza;
        zzajgVar.zzk(i, 3);
        zzalvVar.zza((zzalc) obj, zzajgVar.zze);
        zzajgVar.zzk(i, 4);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zza(int i, List<?> list, zzalv zzalvVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzalvVar);
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzc(int i, int i2) throws IOException {
        this.zza.zzi(i, i2);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzg(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzajz) {
            zzajz zzajzVar = (zzajz) list;
            if (z) {
                this.zza.zzk(i, 2);
                int iZze = 0;
                for (int i3 = 0; i3 < zzajzVar.size(); i3++) {
                    iZze += zzajg.zze(zzajzVar.zzb(i3));
                }
                this.zza.zzn(iZze);
                while (i2 < zzajzVar.size()) {
                    this.zza.zzl(zzajzVar.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzajzVar.size()) {
                this.zza.zzi(i, zzajzVar.zzb(i2));
                i2++;
            }
            return;
        }
        if (z) {
            this.zza.zzk(i, 2);
            int iZze2 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                iZze2 += zzajg.zze(list.get(i4).intValue());
            }
            this.zza.zzn(iZze2);
            while (i2 < list.size()) {
                this.zza.zzl(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzi(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzb(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzh(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzakr) {
            zzakr zzakrVar = (zzakr) list;
            if (z) {
                this.zza.zzk(i, 2);
                int iZzd = 0;
                for (int i3 = 0; i3 < zzakrVar.size(); i3++) {
                    iZzd += zzajg.zzd(zzakrVar.zzb(i3));
                }
                this.zza.zzn(iZzd);
                while (i2 < zzakrVar.size()) {
                    this.zza.zzj(zzakrVar.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzakrVar.size()) {
                this.zza.zzh(i, zzakrVar.zzb(i2));
                i2++;
            }
            return;
        }
        if (z) {
            this.zza.zzk(i, 2);
            int iZzd2 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                iZzd2 += zzajg.zzd(list.get(i4).longValue());
            }
            this.zza.zzn(iZzd2);
            while (i2 < list.size()) {
                this.zza.zzj(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzh(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final <K, V> void zza(int i, zzakx<K, V> zzakxVar, Map<K, V> map) throws IOException {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.zza.zzk(i, 2);
            this.zza.zzn(zzaku.zza(zzakxVar, entry.getKey(), entry.getValue()));
            zzaku.zza(this.zza, zzakxVar, entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzb(int i, Object obj, zzalv zzalvVar) throws IOException {
        this.zza.zzc(i, (zzalc) obj, zzalvVar);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzb(int i, List<?> list, zzalv zzalvVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzalvVar);
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzaip) {
            this.zza.zzd(i, (zzaip) obj);
        } else {
            this.zza.zzb(i, (zzalc) obj);
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzd(int i, int i2) throws IOException {
        this.zza.zzh(i, i2);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzi(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzajz) {
            zzajz zzajzVar = (zzajz) list;
            if (z) {
                this.zza.zzk(i, 2);
                int iZzg = 0;
                for (int i3 = 0; i3 < zzajzVar.size(); i3++) {
                    iZzg += zzajg.zzg(zzajzVar.zzb(i3));
                }
                this.zza.zzn(iZzg);
                while (i2 < zzajzVar.size()) {
                    this.zza.zzk(zzajzVar.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzajzVar.size()) {
                this.zza.zzh(i, zzajzVar.zzb(i2));
                i2++;
            }
            return;
        }
        if (z) {
            this.zza.zzk(i, 2);
            int iZzg2 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                iZzg2 += zzajg.zzg(list.get(i4).intValue());
            }
            this.zza.zzn(iZzg2);
            while (i2 < list.size()) {
                this.zza.zzk(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzh(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzc(int i, long j) throws IOException {
        this.zza.zzf(i, j);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzj(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzakr) {
            zzakr zzakrVar = (zzakr) list;
            if (z) {
                this.zza.zzk(i, 2);
                int iZze = 0;
                for (int i3 = 0; i3 < zzakrVar.size(); i3++) {
                    iZze += zzajg.zze(zzakrVar.zzb(i3));
                }
                this.zza.zzn(iZze);
                while (i2 < zzakrVar.size()) {
                    this.zza.zzh(zzakrVar.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzakrVar.size()) {
                this.zza.zzf(i, zzakrVar.zzb(i2));
                i2++;
            }
            return;
        }
        if (z) {
            this.zza.zzk(i, 2);
            int iZze2 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                iZze2 += zzajg.zze(list.get(i4).longValue());
            }
            this.zza.zzn(iZze2);
            while (i2 < list.size()) {
                this.zza.zzh(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzf(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zze(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzajz) {
            zzajz zzajzVar = (zzajz) list;
            if (z) {
                this.zza.zzk(i, 2);
                int iZzh = 0;
                for (int i3 = 0; i3 < zzajzVar.size(); i3++) {
                    iZzh += zzajg.zzh(zzajzVar.zzb(i3));
                }
                this.zza.zzn(iZzh);
                while (i2 < zzajzVar.size()) {
                    this.zza.zzm(zzajzVar.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzajzVar.size()) {
                this.zza.zzj(i, zzajzVar.zzb(i2));
                i2++;
            }
            return;
        }
        if (z) {
            this.zza.zzk(i, 2);
            int iZzh2 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                iZzh2 += zzajg.zzh(list.get(i4).intValue());
            }
            this.zza.zzn(iZzh2);
            while (i2 < list.size()) {
                this.zza.zzm(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzj(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzd(int i, long j) throws IOException {
        this.zza.zzg(i, j);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzakr) {
            zzakr zzakrVar = (zzakr) list;
            if (z) {
                this.zza.zzk(i, 2);
                int iZzf = 0;
                for (int i3 = 0; i3 < zzakrVar.size(); i3++) {
                    iZzf += zzajg.zzf(zzakrVar.zzb(i3));
                }
                this.zza.zzn(iZzf);
                while (i2 < zzakrVar.size()) {
                    this.zza.zzi(zzakrVar.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzakrVar.size()) {
                this.zza.zzg(i, zzakrVar.zzb(i2));
                i2++;
            }
            return;
        }
        if (z) {
            this.zza.zzk(i, 2);
            int iZzf2 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                iZzf2 += zzajg.zzf(list.get(i4).longValue());
            }
            this.zza.zzn(iZzf2);
            while (i2 < list.size()) {
                this.zza.zzi(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzg(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    @Deprecated
    public final void zzb(int i) throws IOException {
        this.zza.zzk(i, 3);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zza(int i, String str) throws IOException {
        this.zza.zzb(i, str);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzb(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzakn) {
            zzakn zzaknVar = (zzakn) list;
            while (i2 < list.size()) {
                Object objZza = zzaknVar.zza(i2);
                if (objZza instanceof String) {
                    this.zza.zzb(i, (String) objZza);
                } else {
                    this.zza.zzc(i, (zzaip) objZza);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzb(i, list.get(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzf(int i, int i2) throws IOException {
        this.zza.zzl(i, i2);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzajz) {
            zzajz zzajzVar = (zzajz) list;
            if (z) {
                this.zza.zzk(i, 2);
                int iZzj = 0;
                for (int i3 = 0; i3 < zzajzVar.size(); i3++) {
                    iZzj += zzajg.zzj(zzajzVar.zzb(i3));
                }
                this.zza.zzn(iZzj);
                while (i2 < zzajzVar.size()) {
                    this.zza.zzn(zzajzVar.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzajzVar.size()) {
                this.zza.zzl(i, zzajzVar.zzb(i2));
                i2++;
            }
            return;
        }
        if (z) {
            this.zza.zzk(i, 2);
            int iZzj2 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                iZzj2 += zzajg.zzj(list.get(i4).intValue());
            }
            this.zza.zzn(iZzj2);
            while (i2 < list.size()) {
                this.zza.zzn(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzl(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zze(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanf
    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzakr) {
            zzakr zzakrVar = (zzakr) list;
            if (z) {
                this.zza.zzk(i, 2);
                int iZzg = 0;
                for (int i3 = 0; i3 < zzakrVar.size(); i3++) {
                    iZzg += zzajg.zzg(zzakrVar.zzb(i3));
                }
                this.zza.zzn(iZzg);
                while (i2 < zzakrVar.size()) {
                    this.zza.zzj(zzakrVar.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzakrVar.size()) {
                this.zza.zzh(i, zzakrVar.zzb(i2));
                i2++;
            }
            return;
        }
        if (z) {
            this.zza.zzk(i, 2);
            int iZzg2 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                iZzg2 += zzajg.zzg(list.get(i4).longValue());
            }
            this.zza.zzn(iZzg2);
            while (i2 < list.size()) {
                this.zza.zzj(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzh(i, list.get(i2).longValue());
            i2++;
        }
    }
}
