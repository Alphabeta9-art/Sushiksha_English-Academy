package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zznc {
    private static final zznc zza = new zznc(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zznc() {
        this(0, new int[8], new Object[8], true);
    }

    private zznc(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zznc zzc() {
        return zza;
    }

    static zznc zze(zznc zzncVar, zznc zzncVar2) {
        int i = zzncVar.zzb + zzncVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzncVar.zzc, i);
        System.arraycopy(zzncVar2.zzc, 0, iArrCopyOf, zzncVar.zzb, zzncVar2.zzb);
        Object[] objArrCopyOf = Arrays.copyOf(zzncVar.zzd, i);
        System.arraycopy(zzncVar2.zzd, 0, objArrCopyOf, zzncVar.zzb, zzncVar2.zzb);
        return new zznc(i, iArrCopyOf, objArrCopyOf, true);
    }

    static zznc zzf() {
        return new zznc(0, new int[8], new Object[8], true);
    }

    private final void zzm(int i) {
        int[] iArr = this.zzc;
        if (i > iArr.length) {
            int i2 = this.zzb;
            int i3 = i2 + (i2 / 2);
            if (i3 >= i) {
                i = i3;
            }
            if (i < 8) {
                i = 8;
            }
            this.zzc = Arrays.copyOf(iArr, i);
            this.zzd = Arrays.copyOf(this.zzd, i);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zznc)) {
            return false;
        }
        zznc zzncVar = (zznc) obj;
        int i = this.zzb;
        if (i == zzncVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzncVar.zzc;
            for (int i2 = 0; i2 < i; i2++) {
                if (iArr[i2] == iArr2[i2]) {
                }
            }
            Object[] objArr = this.zzd;
            Object[] objArr2 = zzncVar.zzd;
            int i3 = this.zzb;
            for (int i4 = 0; i4 < i3; i4++) {
                if (objArr[i4].equals(objArr2[i4])) {
                }
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = i + 527;
        int[] iArr = this.zzc;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = ((i2 * 31) + i3) * 31;
        Object[] objArr = this.zzd;
        int i6 = this.zzb;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    public final int zza() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzx = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            int i3 = this.zzc[i2];
            int i4 = i3 >>> 3;
            switch (i3 & 7) {
                case 0:
                    iZzx += zzjg.zzx(i4 << 3) + zzjg.zzy(((Long) this.zzd[i2]).longValue());
                    break;
                case 1:
                    ((Long) this.zzd[i2]).longValue();
                    iZzx += zzjg.zzx(i4 << 3) + 8;
                    break;
                case 2:
                    zziv zzivVar = (zziv) this.zzd[i2];
                    int iZzx2 = zzjg.zzx(i4 << 3);
                    int iZzd = zzivVar.zzd();
                    iZzx += iZzx2 + zzjg.zzx(iZzd) + iZzd;
                    break;
                case 3:
                    int iZzx3 = zzjg.zzx(i4 << 3);
                    iZzx += iZzx3 + iZzx3 + ((zznc) this.zzd[i2]).zza();
                    break;
                case 4:
                default:
                    throw new IllegalStateException(zzlc.zza());
                case 5:
                    ((Integer) this.zzd[i2]).intValue();
                    iZzx += zzjg.zzx(i4 << 3) + 4;
                    break;
            }
        }
        this.zze = iZzx;
        return iZzx;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzx = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            int i3 = this.zzc[i2] >>> 3;
            zziv zzivVar = (zziv) this.zzd[i2];
            int iZzx2 = zzjg.zzx(8);
            int iZzx3 = zzjg.zzx(16) + zzjg.zzx(i3);
            int iZzx4 = zzjg.zzx(24);
            int iZzd = zzivVar.zzd();
            iZzx += iZzx2 + iZzx2 + iZzx3 + iZzx4 + zzjg.zzx(iZzd) + iZzd;
        }
        this.zze = iZzx;
        return iZzx;
    }

    final zznc zzd(zznc zzncVar) {
        if (zzncVar.equals(zza)) {
            return this;
        }
        zzg();
        int i = this.zzb + zzncVar.zzb;
        zzm(i);
        System.arraycopy(zzncVar.zzc, 0, this.zzc, this.zzb, zzncVar.zzb);
        System.arraycopy(zzncVar.zzd, 0, this.zzd, this.zzb, zzncVar.zzb);
        this.zzb = i;
        return this;
    }

    final void zzg() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzh() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    final void zzi(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzlz.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    final void zzj(int i, Object obj) {
        zzg();
        zzm(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }

    final void zzk(zzno zznoVar) throws IOException {
        for (int i = 0; i < this.zzb; i++) {
            zznoVar.zzw(this.zzc[i] >>> 3, this.zzd[i]);
        }
    }

    public final void zzl(zzno zznoVar) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 & 7;
                int i4 = i2 >>> 3;
                switch (i3) {
                    case 0:
                        zznoVar.zzt(i4, ((Long) obj).longValue());
                        break;
                    case 1:
                        zznoVar.zzm(i4, ((Long) obj).longValue());
                        break;
                    case 2:
                        zznoVar.zzd(i4, (zziv) obj);
                        break;
                    case 3:
                        zznoVar.zzF(i4);
                        ((zznc) obj).zzl(zznoVar);
                        zznoVar.zzh(i4);
                        break;
                    case 4:
                    default:
                        throw new RuntimeException(zzlc.zza());
                    case 5:
                        zznoVar.zzk(i4, ((Integer) obj).intValue());
                        break;
                }
            }
        }
    }
}
