package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.common.base.Ascii;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzail {
    private static volatile int zza = 100;

    static double zza(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzd(bArr, i));
    }

    static float zzb(byte[] bArr, int i) {
        return Float.intBitsToFloat(zzc(bArr, i));
    }

    static int zza(byte[] bArr, int i, zzaik zzaikVar) throws zzakf {
        int iZzc = zzc(bArr, i, zzaikVar);
        int i2 = zzaikVar.zza;
        if (i2 < 0) {
            throw zzakf.zzf();
        }
        if (i2 > bArr.length - iZzc) {
            throw zzakf.zzj();
        }
        if (i2 == 0) {
            zzaikVar.zzc = zzaip.zza;
            return iZzc;
        }
        zzaikVar.zzc = zzaip.zza(bArr, iZzc, i2);
        return iZzc + i2;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, Object obj, zzalc zzalcVar, zzamo<zzamn, zzamn> zzamoVar, zzaik zzaikVar) throws IOException {
        if (zzaikVar.zzd.zza(zzalcVar, i >>> 3) == null) {
            return zza(i, bArr, i2, i3, zzalg.zzc(obj), zzaikVar);
        }
        zzajy.zzb zzbVar = (zzajy.zzb) obj;
        zzbVar.zza();
        zzajr<zzajy.zze> zzajrVar = zzbVar.zzc;
        throw new NoSuchMethodError();
    }

    static int zzc(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24);
    }

    private static int zza(zzalv zzalvVar, byte[] bArr, int i, int i2, int i3, zzaik zzaikVar) throws IOException {
        Object objZza = zzalvVar.zza();
        int iZza = zza(objZza, zzalvVar, bArr, i, i2, i3, zzaikVar);
        zzalvVar.zzd(objZza);
        zzaikVar.zzc = objZza;
        return iZza;
    }

    static int zza(zzalv zzalvVar, int i, byte[] bArr, int i2, int i3, zzakc<?> zzakcVar, zzaik zzaikVar) throws IOException {
        int i4 = (i & (-8)) | 4;
        int iZza = zza(zzalvVar, bArr, i2, i3, i4, zzaikVar);
        zzakcVar.add(zzaikVar.zzc);
        while (iZza < i3) {
            int iZzc = zzc(bArr, iZza, zzaikVar);
            if (i != zzaikVar.zza) {
                break;
            }
            iZza = zza(zzalvVar, bArr, iZzc, i3, i4, zzaikVar);
            zzakcVar.add(zzaikVar.zzc);
        }
        return iZza;
    }

    static int zza(zzalv zzalvVar, byte[] bArr, int i, int i2, zzaik zzaikVar) throws IOException {
        Object objZza = zzalvVar.zza();
        int iZza = zza(objZza, zzalvVar, bArr, i, i2, zzaikVar);
        zzalvVar.zzd(objZza);
        zzaikVar.zzc = objZza;
        return iZza;
    }

    static int zzb(zzalv<?> zzalvVar, int i, byte[] bArr, int i2, int i3, zzakc<?> zzakcVar, zzaik zzaikVar) throws IOException {
        int iZza = zza(zzalvVar, bArr, i2, i3, zzaikVar);
        zzakcVar.add(zzaikVar.zzc);
        while (iZza < i3) {
            int iZzc = zzc(bArr, iZza, zzaikVar);
            if (i != zzaikVar.zza) {
                break;
            }
            iZza = zza(zzalvVar, bArr, iZzc, i3, zzaikVar);
            zzakcVar.add(zzaikVar.zzc);
        }
        return iZza;
    }

    static int zza(byte[] bArr, int i, zzakc<?> zzakcVar, zzaik zzaikVar) throws IOException {
        zzajz zzajzVar = (zzajz) zzakcVar;
        int iZzc = zzc(bArr, i, zzaikVar);
        int i2 = zzaikVar.zza + iZzc;
        while (iZzc < i2) {
            iZzc = zzc(bArr, iZzc, zzaikVar);
            zzajzVar.zzc(zzaikVar.zza);
        }
        if (iZzc != i2) {
            throw zzakf.zzj();
        }
        return iZzc;
    }

    static int zzb(byte[] bArr, int i, zzaik zzaikVar) throws zzakf {
        int iZzc = zzc(bArr, i, zzaikVar);
        int i2 = zzaikVar.zza;
        if (i2 < 0) {
            throw zzakf.zzf();
        }
        if (i2 == 0) {
            zzaikVar.zzc = "";
            return iZzc;
        }
        zzaikVar.zzc = zzamt.zzb(bArr, iZzc, i2);
        return iZzc + i2;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzamn zzamnVar, zzaik zzaikVar) throws zzakf {
        if ((i >>> 3) == 0) {
            throw zzakf.zzc();
        }
        switch (i & 7) {
            case 0:
                int iZzd = zzd(bArr, i2, zzaikVar);
                zzamnVar.zza(i, Long.valueOf(zzaikVar.zzb));
                return iZzd;
            case 1:
                zzamnVar.zza(i, Long.valueOf(zzd(bArr, i2)));
                return i2 + 8;
            case 2:
                int iZzc = zzc(bArr, i2, zzaikVar);
                int i4 = zzaikVar.zza;
                if (i4 < 0) {
                    throw zzakf.zzf();
                }
                if (i4 > bArr.length - iZzc) {
                    throw zzakf.zzj();
                }
                if (i4 == 0) {
                    zzamnVar.zza(i, zzaip.zza);
                } else {
                    zzamnVar.zza(i, zzaip.zza(bArr, iZzc, i4));
                }
                return iZzc + i4;
            case 3:
                zzamn zzamnVarZzd = zzamn.zzd();
                int i5 = (i & (-8)) | 4;
                zzaikVar.zze++;
                zza(zzaikVar.zze);
                int i6 = 0;
                while (i2 < i3) {
                    int iZzc2 = zzc(bArr, i2, zzaikVar);
                    i6 = zzaikVar.zza;
                    if (i6 != i5) {
                        i2 = zza(i6, bArr, iZzc2, i3, zzamnVarZzd, zzaikVar);
                    } else {
                        i2 = iZzc2;
                        zzaikVar.zze--;
                        if (i2 <= i3 || i6 != i5) {
                            throw zzakf.zzg();
                        }
                        zzamnVar.zza(i, zzamnVarZzd);
                        return i2;
                    }
                }
                zzaikVar.zze--;
                if (i2 <= i3) {
                }
                throw zzakf.zzg();
            case 4:
            default:
                throw zzakf.zzc();
            case 5:
                zzamnVar.zza(i, Integer.valueOf(zzc(bArr, i2)));
                return i2 + 4;
        }
    }

    static int zzc(byte[] bArr, int i, zzaik zzaikVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= 0) {
            zzaikVar.zza = b;
            return i2;
        }
        return zza(b, bArr, i2, zzaikVar);
    }

    static int zza(int i, byte[] bArr, int i2, zzaik zzaikVar) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzaikVar.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & 127) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzaikVar.zza = i5 | (b2 << Ascii.SO);
            return i6;
        }
        int i7 = i5 | ((b2 & 127) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzaikVar.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & 127) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzaikVar.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & 127) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] < 0) {
                i10 = i12;
            } else {
                zzaikVar.zza = i11;
                return i12;
            }
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzakc<?> zzakcVar, zzaik zzaikVar) {
        zzajz zzajzVar = (zzajz) zzakcVar;
        int iZzc = zzc(bArr, i2, zzaikVar);
        zzajzVar.zzc(zzaikVar.zza);
        while (iZzc < i3) {
            int iZzc2 = zzc(bArr, iZzc, zzaikVar);
            if (i != zzaikVar.zza) {
                break;
            }
            iZzc = zzc(bArr, iZzc2, zzaikVar);
            zzajzVar.zzc(zzaikVar.zza);
        }
        return iZzc;
    }

    static int zzd(byte[] bArr, int i, zzaik zzaikVar) {
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            zzaikVar.zzb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & 127)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & 127)) << i4;
            b = b2;
            i3 = i5;
        }
        zzaikVar.zzb = j2;
        return i3;
    }

    static int zza(Object obj, zzalv zzalvVar, byte[] bArr, int i, int i2, int i3, zzaik zzaikVar) throws IOException {
        zzaikVar.zze++;
        zza(zzaikVar.zze);
        int iZza = ((zzalg) zzalvVar).zza(obj, bArr, i, i2, i3, zzaikVar);
        zzaikVar.zze--;
        zzaikVar.zzc = obj;
        return iZza;
    }

    static int zza(Object obj, zzalv zzalvVar, byte[] bArr, int i, int i2, zzaik zzaikVar) throws IOException {
        int i3;
        int i4 = i + 1;
        int i5 = bArr[i];
        if (i5 >= 0) {
            i3 = i4;
        } else {
            int iZza = zza(i5, bArr, i4, zzaikVar);
            i5 = zzaikVar.zza;
            i3 = iZza;
        }
        if (i5 < 0 || i5 > i2 - i3) {
            throw zzakf.zzj();
        }
        zzaikVar.zze++;
        zza(zzaikVar.zze);
        int i6 = i3 + i5;
        zzalvVar.zza(obj, bArr, i3, i6, zzaikVar);
        zzaikVar.zze--;
        zzaikVar.zzc = obj;
        return i6;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzaik zzaikVar) throws zzakf {
        if ((i >>> 3) == 0) {
            throw zzakf.zzc();
        }
        switch (i & 7) {
            case 0:
                return zzd(bArr, i2, zzaikVar);
            case 1:
                return i2 + 8;
            case 2:
                return zzc(bArr, i2, zzaikVar) + zzaikVar.zza;
            case 3:
                int i4 = (i & (-8)) | 4;
                int i5 = 0;
                while (i2 < i3) {
                    i2 = zzc(bArr, i2, zzaikVar);
                    i5 = zzaikVar.zza;
                    if (i5 != i4) {
                        i2 = zza(i5, bArr, i2, i3, zzaikVar);
                    } else {
                        if (i2 <= i3 || i5 != i4) {
                            throw zzakf.zzg();
                        }
                        return i2;
                    }
                }
                if (i2 <= i3) {
                }
                throw zzakf.zzg();
            case 4:
            default:
                throw zzakf.zzc();
            case 5:
                return i2 + 4;
        }
    }

    static long zzd(byte[] bArr, int i) {
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    private static void zza(int i) throws zzakf {
        if (i >= zza) {
            throw zzakf.zzh();
        }
    }
}
