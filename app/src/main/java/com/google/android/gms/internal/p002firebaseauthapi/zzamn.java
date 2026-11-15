package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzamn {
    private static final zzamn zza = new zzamn(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    public final int zza() {
        int iZze;
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int i4 = this.zzc[i3];
            int i5 = i4 >>> 3;
            switch (i4 & 7) {
                case 0:
                    iZze = zzajg.zze(i5, ((Long) this.zzd[i3]).longValue());
                    break;
                case 1:
                    iZze = zzajg.zza(i5, ((Long) this.zzd[i3]).longValue());
                    break;
                case 2:
                    iZze = zzajg.zza(i5, (zzaip) this.zzd[i3]);
                    break;
                case 3:
                    iZze = (zzajg.zzi(i5) << 1) + ((zzamn) this.zzd[i3]).zza();
                    break;
                case 4:
                default:
                    throw new IllegalStateException(zzakf.zza());
                case 5:
                    iZze = zzajg.zzc(i5, ((Integer) this.zzd[i3]).intValue());
                    break;
            }
            i2 += iZze;
        }
        this.zze = i2;
        return i2;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzb = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            iZzb += zzajg.zzb(this.zzc[i2] >>> 3, (zzaip) this.zzd[i2]);
        }
        this.zze = iZzb;
        return iZzb;
    }

    public final int hashCode() {
        int i = (this.zzb + 527) * 31;
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i + i3) * 31;
        Object[] objArr = this.zzd;
        int i6 = this.zzb;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    public static zzamn zzc() {
        return zza;
    }

    final zzamn zza(zzamn zzamnVar) {
        if (zzamnVar.equals(zza)) {
            return this;
        }
        zzf();
        int i = this.zzb + zzamnVar.zzb;
        zza(i);
        System.arraycopy(zzamnVar.zzc, 0, this.zzc, this.zzb, zzamnVar.zzb);
        System.arraycopy(zzamnVar.zzd, 0, this.zzd, this.zzb, zzamnVar.zzb);
        this.zzb = i;
        return this;
    }

    static zzamn zza(zzamn zzamnVar, zzamn zzamnVar2) {
        int i = zzamnVar.zzb + zzamnVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzamnVar.zzc, i);
        System.arraycopy(zzamnVar2.zzc, 0, iArrCopyOf, zzamnVar.zzb, zzamnVar2.zzb);
        Object[] objArrCopyOf = Arrays.copyOf(zzamnVar.zzd, i);
        System.arraycopy(zzamnVar2.zzd, 0, objArrCopyOf, zzamnVar.zzb, zzamnVar2.zzb);
        return new zzamn(i, iArrCopyOf, objArrCopyOf, true);
    }

    static zzamn zzd() {
        return new zzamn();
    }

    private zzamn() {
        this(0, new int[8], new Object[8], true);
    }

    private zzamn(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    private final void zzf() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    private final void zza(int i) {
        if (i > this.zzc.length) {
            int i2 = this.zzb + (this.zzb / 2);
            if (i2 >= i) {
                i = i2;
            }
            if (i < 8) {
                i = 8;
            }
            this.zzc = Arrays.copyOf(this.zzc, i);
            this.zzd = Arrays.copyOf(this.zzd, i);
        }
    }

    public final void zze() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzalh.zza(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    final void zza(int i, Object obj) {
        zzf();
        zza(this.zzb + 1);
        this.zzc[this.zzb] = i;
        this.zzd[this.zzb] = obj;
        this.zzb++;
    }

    final void zza(zzanf zzanfVar) throws IOException {
        if (zzanfVar.zza() == 2) {
            for (int i = this.zzb - 1; i >= 0; i--) {
                zzanfVar.zza(this.zzc[i] >>> 3, this.zzd[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzanfVar.zza(this.zzc[i2] >>> 3, this.zzd[i2]);
        }
    }

    private static void zza(int i, Object obj, zzanf zzanfVar) throws IOException {
        int i2 = i >>> 3;
        switch (i & 7) {
            case 0:
                zzanfVar.zzb(i2, ((Long) obj).longValue());
                return;
            case 1:
                zzanfVar.zza(i2, ((Long) obj).longValue());
                return;
            case 2:
                zzanfVar.zza(i2, (zzaip) obj);
                return;
            case 3:
                if (zzanfVar.zza() == 1) {
                    zzanfVar.zzb(i2);
                    ((zzamn) obj).zzb(zzanfVar);
                    zzanfVar.zza(i2);
                    return;
                } else {
                    zzanfVar.zza(i2);
                    ((zzamn) obj).zzb(zzanfVar);
                    zzanfVar.zzb(i2);
                    return;
                }
            case 4:
            default:
                throw new RuntimeException(zzakf.zza());
            case 5:
                zzanfVar.zzb(i2, ((Integer) obj).intValue());
                return;
        }
    }

    public final void zzb(zzanf zzanfVar) throws IOException {
        if (this.zzb == 0) {
            return;
        }
        if (zzanfVar.zza() == 1) {
            for (int i = 0; i < this.zzb; i++) {
                zza(this.zzc[i], this.zzd[i], zzanfVar);
            }
            return;
        }
        for (int i2 = this.zzb - 1; i2 >= 0; i2--) {
            zza(this.zzc[i2], this.zzd[i2], zzanfVar);
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x004a A[RETURN] */
    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzamn)) {
            return false;
        }
        zzamn zzamnVar = (zzamn) obj;
        if (this.zzb == zzamnVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzamnVar.zzc;
            int i = this.zzb;
            int i2 = 0;
            while (true) {
                if (i2 < i) {
                    if (iArr[i2] != iArr2[i2]) {
                        z = false;
                        break;
                    }
                    i2++;
                } else {
                    z = true;
                    break;
                }
            }
            if (z) {
                Object[] objArr = this.zzd;
                Object[] objArr2 = zzamnVar.zzd;
                int i3 = this.zzb;
                for (int i4 = 0; i4 < i3; i4++) {
                    if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        if (!z2) {
                            return true;
                        }
                    }
                }
                z2 = true;
                if (!z2) {
                    return true;
                }
            }
        }
        return false;
    }
}
