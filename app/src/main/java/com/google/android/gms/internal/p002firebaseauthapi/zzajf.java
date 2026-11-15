package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzajf implements zzals {
    private final zzajb zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final double zza() throws IOException {
        zzb(1);
        return this.zza.zza();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final float zzb() throws IOException {
        zzb(5);
        return this.zza.zzb();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final int zzc() throws IOException {
        if (this.zzd != 0) {
            this.zzb = this.zzd;
            this.zzd = 0;
        } else {
            this.zzb = this.zza.zzi();
        }
        if (this.zzb == 0 || this.zzb == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return this.zzb >>> 3;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final int zzd() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final int zze() throws IOException {
        zzb(0);
        return this.zza.zzd();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final int zzf() throws IOException {
        zzb(5);
        return this.zza.zze();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final int zzg() throws IOException {
        zzb(0);
        return this.zza.zzf();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final int zzh() throws IOException {
        zzb(5);
        return this.zza.zzg();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final int zzi() throws IOException {
        zzb(0);
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final int zzj() throws IOException {
        zzb(0);
        return this.zza.zzj();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final long zzk() throws IOException {
        zzb(1);
        return this.zza.zzk();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final long zzl() throws IOException {
        zzb(0);
        return this.zza.zzl();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final long zzm() throws IOException {
        zzb(1);
        return this.zza.zzn();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final long zzn() throws IOException {
        zzb(0);
        return this.zza.zzo();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final long zzo() throws IOException {
        zzb(0);
        return this.zza.zzp();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final zzaip zzp() throws IOException {
        zzb(2);
        return this.zza.zzq();
    }

    public static zzajf zza(zzajb zzajbVar) {
        if (zzajbVar.zzd != null) {
            return zzajbVar.zzd;
        }
        return new zzajf(zzajbVar);
    }

    private final Object zza(zzamw zzamwVar, Class<?> cls, zzajk zzajkVar) throws IOException {
        switch (zzaje.zza[zzamwVar.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzs());
            case 2:
                return zzp();
            case 3:
                return Double.valueOf(zza());
            case 4:
                return Integer.valueOf(zze());
            case 5:
                return Integer.valueOf(zzf());
            case 6:
                return Long.valueOf(zzk());
            case 7:
                return Float.valueOf(zzb());
            case 8:
                return Integer.valueOf(zzg());
            case 9:
                return Long.valueOf(zzl());
            case 10:
                zzb(2);
                return zzb(zzalr.zza().zza((Class) cls), zzajkVar);
            case 11:
                return Integer.valueOf(zzh());
            case 12:
                return Long.valueOf(zzm());
            case 13:
                return Integer.valueOf(zzi());
            case 14:
                return Long.valueOf(zzn());
            case 15:
                return zzr();
            case 16:
                return Integer.valueOf(zzj());
            case 17:
                return Long.valueOf(zzo());
            default:
                throw new IllegalArgumentException("unsupported field type.");
        }
    }

    private final <T> T zza(zzalv<T> zzalvVar, zzajk zzajkVar) throws IOException {
        T tZza = zzalvVar.zza();
        zzc(tZza, zzalvVar, zzajkVar);
        zzalvVar.zzd(tZza);
        return tZza;
    }

    private final <T> T zzb(zzalv<T> zzalvVar, zzajk zzajkVar) throws IOException {
        T tZza = zzalvVar.zza();
        zzd(tZza, zzalvVar, zzajkVar);
        zzalvVar.zzd(tZza);
        return tZza;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final String zzq() throws IOException {
        zzb(2);
        return this.zza.zzr();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final String zzr() throws IOException {
        zzb(2);
        return this.zza.zzs();
    }

    private zzajf(zzajb zzajbVar) {
        this.zza = (zzajb) zzakb.zza(zzajbVar, "input");
        this.zza.zzd = this;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final <T> void zza(T t, zzalv<T> zzalvVar, zzajk zzajkVar) throws IOException {
        zzb(3);
        zzc(t, zzalvVar, zzajkVar);
    }

    private final <T> void zzc(T t, zzalv<T> zzalvVar, zzajk zzajkVar) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            zzalvVar.zza(t, this, zzajkVar);
            if (this.zzb != this.zzc) {
                throw zzakf.zzg();
            }
            this.zzc = i;
        } catch (Throwable th) {
            this.zzc = i;
            throw th;
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final <T> void zzb(T t, zzalv<T> zzalvVar, zzajk zzajkVar) throws IOException {
        zzb(2);
        zzd(t, zzalvVar, zzajkVar);
    }

    private final <T> void zzd(T t, zzalv<T> zzalvVar, zzajk zzajkVar) throws IOException {
        int iZzj = this.zza.zzj();
        if (this.zza.zza >= this.zza.zzb) {
            throw zzakf.zzh();
        }
        int iZza = this.zza.zza(iZzj);
        this.zza.zza++;
        zzalvVar.zza(t, this, zzajkVar);
        this.zza.zzb(0);
        this.zza.zza--;
        this.zza.zzc(iZza);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zza(List<Boolean> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzain) {
            zzain zzainVar = (zzain) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 1:
                default:
                    throw zzakf.zza();
                case 2:
                    int iZzc = this.zza.zzc() + this.zza.zzj();
                    do {
                        zzainVar.zza(this.zza.zzu());
                    } while (this.zza.zzc() < iZzc);
                    zza(iZzc);
                    return;
            }
            do {
                zzainVar.zza(this.zza.zzu());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 1:
            default:
                throw zzakf.zza();
            case 2:
                int iZzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Boolean.valueOf(this.zza.zzu()));
                } while (this.zza.zzc() < iZzc2);
                zza(iZzc2);
                return;
        }
        do {
            list.add(Boolean.valueOf(this.zza.zzu()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zzb(List<zzaip> list) throws IOException {
        int iZzi;
        if ((this.zzb & 7) != 2) {
            throw zzakf.zza();
        }
        do {
            list.add(zzp());
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zzc(List<Double> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzaji) {
            zzaji zzajiVar = (zzaji) list;
            switch (this.zzb & 7) {
                case 1:
                    break;
                case 2:
                    int iZzj = this.zza.zzj();
                    zzd(iZzj);
                    int iZzc = this.zza.zzc() + iZzj;
                    do {
                        zzajiVar.zza(this.zza.zza());
                    } while (this.zza.zzc() < iZzc);
                    return;
                default:
                    throw zzakf.zza();
            }
            do {
                zzajiVar.zza(this.zza.zza());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 1:
                break;
            case 2:
                int iZzj2 = this.zza.zzj();
                zzd(iZzj2);
                int iZzc2 = this.zza.zzc() + iZzj2;
                do {
                    list.add(Double.valueOf(this.zza.zza()));
                } while (this.zza.zzc() < iZzc2);
                return;
            default:
                throw zzakf.zza();
        }
        do {
            list.add(Double.valueOf(this.zza.zza()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zzd(List<Integer> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajz) {
            zzajz zzajzVar = (zzajz) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 1:
                default:
                    throw zzakf.zza();
                case 2:
                    int iZzc = this.zza.zzc() + this.zza.zzj();
                    do {
                        zzajzVar.zzc(this.zza.zzd());
                    } while (this.zza.zzc() < iZzc);
                    zza(iZzc);
                    return;
            }
            do {
                zzajzVar.zzc(this.zza.zzd());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 1:
            default:
                throw zzakf.zza();
            case 2:
                int iZzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Integer.valueOf(this.zza.zzd()));
                } while (this.zza.zzc() < iZzc2);
                zza(iZzc2);
                return;
        }
        do {
            list.add(Integer.valueOf(this.zza.zzd()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zze(List<Integer> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajz) {
            zzajz zzajzVar = (zzajz) list;
            switch (this.zzb & 7) {
                case 2:
                    int iZzj = this.zza.zzj();
                    zzc(iZzj);
                    int iZzc = this.zza.zzc() + iZzj;
                    do {
                        zzajzVar.zzc(this.zza.zze());
                    } while (this.zza.zzc() < iZzc);
                    return;
                case 5:
                    break;
                default:
                    throw zzakf.zza();
            }
            do {
                zzajzVar.zzc(this.zza.zze());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 2:
                int iZzj2 = this.zza.zzj();
                zzc(iZzj2);
                int iZzc2 = this.zza.zzc() + iZzj2;
                do {
                    list.add(Integer.valueOf(this.zza.zze()));
                } while (this.zza.zzc() < iZzc2);
                return;
            case 5:
                break;
            default:
                throw zzakf.zza();
        }
        do {
            list.add(Integer.valueOf(this.zza.zze()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zzf(List<Long> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzakr) {
            zzakr zzakrVar = (zzakr) list;
            switch (this.zzb & 7) {
                case 1:
                    break;
                case 2:
                    int iZzj = this.zza.zzj();
                    zzd(iZzj);
                    int iZzc = this.zza.zzc() + iZzj;
                    do {
                        zzakrVar.zza(this.zza.zzk());
                    } while (this.zza.zzc() < iZzc);
                    return;
                default:
                    throw zzakf.zza();
            }
            do {
                zzakrVar.zza(this.zza.zzk());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 1:
                break;
            case 2:
                int iZzj2 = this.zza.zzj();
                zzd(iZzj2);
                int iZzc2 = this.zza.zzc() + iZzj2;
                do {
                    list.add(Long.valueOf(this.zza.zzk()));
                } while (this.zza.zzc() < iZzc2);
                return;
            default:
                throw zzakf.zza();
        }
        do {
            list.add(Long.valueOf(this.zza.zzk()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zzg(List<Float> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajx) {
            zzajx zzajxVar = (zzajx) list;
            switch (this.zzb & 7) {
                case 2:
                    int iZzj = this.zza.zzj();
                    zzc(iZzj);
                    int iZzc = this.zza.zzc() + iZzj;
                    do {
                        zzajxVar.zza(this.zza.zzb());
                    } while (this.zza.zzc() < iZzc);
                    return;
                case 5:
                    break;
                default:
                    throw zzakf.zza();
            }
            do {
                zzajxVar.zza(this.zza.zzb());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 2:
                int iZzj2 = this.zza.zzj();
                zzc(iZzj2);
                int iZzc2 = this.zza.zzc() + iZzj2;
                do {
                    list.add(Float.valueOf(this.zza.zzb()));
                } while (this.zza.zzc() < iZzc2);
                return;
            case 5:
                break;
            default:
                throw zzakf.zza();
        }
        do {
            list.add(Float.valueOf(this.zza.zzb()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    @Deprecated
    public final <T> void zza(List<T> list, zzalv<T> zzalvVar, zzajk zzajkVar) throws IOException {
        int iZzi;
        if ((this.zzb & 7) != 3) {
            throw zzakf.zza();
        }
        int i = this.zzb;
        do {
            list.add(zza(zzalvVar, zzajkVar));
            if (this.zza.zzt() || this.zzd != 0) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == i);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zzh(List<Integer> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajz) {
            zzajz zzajzVar = (zzajz) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 1:
                default:
                    throw zzakf.zza();
                case 2:
                    int iZzc = this.zza.zzc() + this.zza.zzj();
                    do {
                        zzajzVar.zzc(this.zza.zzf());
                    } while (this.zza.zzc() < iZzc);
                    zza(iZzc);
                    return;
            }
            do {
                zzajzVar.zzc(this.zza.zzf());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 1:
            default:
                throw zzakf.zza();
            case 2:
                int iZzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Integer.valueOf(this.zza.zzf()));
                } while (this.zza.zzc() < iZzc2);
                zza(iZzc2);
                return;
        }
        do {
            list.add(Integer.valueOf(this.zza.zzf()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zzi(List<Long> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzakr) {
            zzakr zzakrVar = (zzakr) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 1:
                default:
                    throw zzakf.zza();
                case 2:
                    int iZzc = this.zza.zzc() + this.zza.zzj();
                    do {
                        zzakrVar.zza(this.zza.zzl());
                    } while (this.zza.zzc() < iZzc);
                    zza(iZzc);
                    return;
            }
            do {
                zzakrVar.zza(this.zza.zzl());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 1:
            default:
                throw zzakf.zza();
            case 2:
                int iZzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Long.valueOf(this.zza.zzl()));
                } while (this.zza.zzc() < iZzc2);
                zza(iZzc2);
                return;
        }
        do {
            list.add(Long.valueOf(this.zza.zzl()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final <K, V> void zza(Map<K, V> map, zzakx<K, V> zzakxVar, zzajk zzajkVar) throws IOException {
        zzb(2);
        int iZza = this.zza.zza(this.zza.zzj());
        Object objZza = zzakxVar.zzb;
        Object objZza2 = zzakxVar.zzd;
        while (true) {
            try {
                int iZzc = zzc();
                if (iZzc != Integer.MAX_VALUE && !this.zza.zzt()) {
                    switch (iZzc) {
                        case 1:
                            objZza = zza(zzakxVar.zza, (Class<?>) null, (zzajk) null);
                            continue;
                        case 2:
                            objZza2 = zza(zzakxVar.zzc, zzakxVar.zzd.getClass(), zzajkVar);
                            continue;
                        default:
                            try {
                                if (!zzt()) {
                                    throw new zzakf("Unable to parse map entry.");
                                }
                                continue;
                            } catch (zzake e) {
                                if (!zzt()) {
                                    throw new zzakf("Unable to parse map entry.");
                                }
                            }
                    }
                    this.zza.zzc(iZza);
                    throw th;
                }
            } catch (Throwable th) {
                this.zza.zzc(iZza);
                throw th;
            }
        }
        map.put(objZza, objZza2);
        this.zza.zzc(iZza);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final <T> void zzb(List<T> list, zzalv<T> zzalvVar, zzajk zzajkVar) throws IOException {
        int iZzi;
        if ((this.zzb & 7) != 2) {
            throw zzakf.zza();
        }
        int i = this.zzb;
        do {
            list.add(zzb(zzalvVar, zzajkVar));
            if (this.zza.zzt() || this.zzd != 0) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == i);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zzj(List<Integer> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajz) {
            zzajz zzajzVar = (zzajz) list;
            switch (this.zzb & 7) {
                case 2:
                    int iZzj = this.zza.zzj();
                    zzc(iZzj);
                    int iZzc = this.zza.zzc() + iZzj;
                    do {
                        zzajzVar.zzc(this.zza.zzg());
                    } while (this.zza.zzc() < iZzc);
                    return;
                case 5:
                    break;
                default:
                    throw zzakf.zza();
            }
            do {
                zzajzVar.zzc(this.zza.zzg());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 2:
                int iZzj2 = this.zza.zzj();
                zzc(iZzj2);
                int iZzc2 = this.zza.zzc() + iZzj2;
                do {
                    list.add(Integer.valueOf(this.zza.zzg()));
                } while (this.zza.zzc() < iZzc2);
                return;
            case 5:
                break;
            default:
                throw zzakf.zza();
        }
        do {
            list.add(Integer.valueOf(this.zza.zzg()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zzk(List<Long> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzakr) {
            zzakr zzakrVar = (zzakr) list;
            switch (this.zzb & 7) {
                case 1:
                    break;
                case 2:
                    int iZzj = this.zza.zzj();
                    zzd(iZzj);
                    int iZzc = this.zza.zzc() + iZzj;
                    do {
                        zzakrVar.zza(this.zza.zzn());
                    } while (this.zza.zzc() < iZzc);
                    return;
                default:
                    throw zzakf.zza();
            }
            do {
                zzakrVar.zza(this.zza.zzn());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 1:
                break;
            case 2:
                int iZzj2 = this.zza.zzj();
                zzd(iZzj2);
                int iZzc2 = this.zza.zzc() + iZzj2;
                do {
                    list.add(Long.valueOf(this.zza.zzn()));
                } while (this.zza.zzc() < iZzc2);
                return;
            default:
                throw zzakf.zza();
        }
        do {
            list.add(Long.valueOf(this.zza.zzn()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zzl(List<Integer> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajz) {
            zzajz zzajzVar = (zzajz) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 1:
                default:
                    throw zzakf.zza();
                case 2:
                    int iZzc = this.zza.zzc() + this.zza.zzj();
                    do {
                        zzajzVar.zzc(this.zza.zzh());
                    } while (this.zza.zzc() < iZzc);
                    zza(iZzc);
                    return;
            }
            do {
                zzajzVar.zzc(this.zza.zzh());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 1:
            default:
                throw zzakf.zza();
            case 2:
                int iZzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Integer.valueOf(this.zza.zzh()));
                } while (this.zza.zzc() < iZzc2);
                zza(iZzc2);
                return;
        }
        do {
            list.add(Integer.valueOf(this.zza.zzh()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zzm(List<Long> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzakr) {
            zzakr zzakrVar = (zzakr) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 1:
                default:
                    throw zzakf.zza();
                case 2:
                    int iZzc = this.zza.zzc() + this.zza.zzj();
                    do {
                        zzakrVar.zza(this.zza.zzo());
                    } while (this.zza.zzc() < iZzc);
                    zza(iZzc);
                    return;
            }
            do {
                zzakrVar.zza(this.zza.zzo());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 1:
            default:
                throw zzakf.zza();
            case 2:
                int iZzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Long.valueOf(this.zza.zzo()));
                } while (this.zza.zzc() < iZzc2);
                zza(iZzc2);
                return;
        }
        do {
            list.add(Long.valueOf(this.zza.zzo()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zzn(List<String> list) throws IOException {
        zza(list, false);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int iZzi;
        int iZzi2;
        if ((this.zzb & 7) != 2) {
            throw zzakf.zza();
        }
        if ((list instanceof zzakn) && !z) {
            zzakn zzaknVar = (zzakn) list;
            do {
                zzaknVar.zza(zzp());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        do {
            list.add(z ? zzr() : zzq());
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zzo(List<String> list) throws IOException {
        zza(list, true);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zzp(List<Integer> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzajz) {
            zzajz zzajzVar = (zzajz) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 1:
                default:
                    throw zzakf.zza();
                case 2:
                    int iZzc = this.zza.zzc() + this.zza.zzj();
                    do {
                        zzajzVar.zzc(this.zza.zzj());
                    } while (this.zza.zzc() < iZzc);
                    zza(iZzc);
                    return;
            }
            do {
                zzajzVar.zzc(this.zza.zzj());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 1:
            default:
                throw zzakf.zza();
            case 2:
                int iZzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Integer.valueOf(this.zza.zzj()));
                } while (this.zza.zzc() < iZzc2);
                zza(iZzc2);
                return;
        }
        do {
            list.add(Integer.valueOf(this.zza.zzj()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final void zzq(List<Long> list) throws IOException {
        int iZzi;
        int iZzi2;
        if (list instanceof zzakr) {
            zzakr zzakrVar = (zzakr) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 1:
                default:
                    throw zzakf.zza();
                case 2:
                    int iZzc = this.zza.zzc() + this.zza.zzj();
                    do {
                        zzakrVar.zza(this.zza.zzp());
                    } while (this.zza.zzc() < iZzc);
                    zza(iZzc);
                    return;
            }
            do {
                zzakrVar.zza(this.zza.zzp());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZzi2 = this.zza.zzi();
                }
            } while (iZzi2 == this.zzb);
            this.zzd = iZzi2;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 1:
            default:
                throw zzakf.zza();
            case 2:
                int iZzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Long.valueOf(this.zza.zzp()));
                } while (this.zza.zzc() < iZzc2);
                zza(iZzc2);
                return;
        }
        do {
            list.add(Long.valueOf(this.zza.zzp()));
            if (this.zza.zzt()) {
                return;
            } else {
                iZzi = this.zza.zzi();
            }
        } while (iZzi == this.zzb);
        this.zzd = iZzi;
    }

    private final void zza(int i) throws IOException {
        if (this.zza.zzc() != i) {
            throw zzakf.zzj();
        }
    }

    private final void zzb(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw zzakf.zza();
        }
    }

    private static void zzc(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzakf.zzg();
        }
    }

    private static void zzd(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzakf.zzg();
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final boolean zzs() throws IOException {
        zzb(0);
        return this.zza.zzu();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzals
    public final boolean zzt() throws IOException {
        if (this.zza.zzt() || this.zzb == this.zzc) {
            return false;
        }
        return this.zza.zzd(this.zzb);
    }
}
