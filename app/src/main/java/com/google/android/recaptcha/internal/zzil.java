package com.google.android.recaptcha.internal;

import com.google.common.base.Ascii;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzil {
    static int zza(byte[] bArr, int i, zzik zzikVar) throws zzlc {
        int iZzi = zzi(bArr, i, zzikVar);
        int i2 = zzikVar.zza;
        if (i2 < 0) {
            throw zzlc.zzf();
        }
        if (i2 > bArr.length - iZzi) {
            throw zzlc.zzi();
        }
        if (i2 == 0) {
            zzikVar.zzc = zziv.zzb;
            return iZzi;
        }
        zzikVar.zzc = zziv.zzk(bArr, iZzi, i2);
        return iZzi + i2;
    }

    static int zzb(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24);
    }

    static int zzc(zzmk zzmkVar, byte[] bArr, int i, int i2, int i3, zzik zzikVar) throws IOException {
        Object objZze = zzmkVar.zze();
        int iZzm = zzm(objZze, zzmkVar, bArr, i, i2, i3, zzikVar);
        zzmkVar.zzf(objZze);
        zzikVar.zzc = objZze;
        return iZzm;
    }

    static int zzd(zzmk zzmkVar, byte[] bArr, int i, int i2, zzik zzikVar) throws IOException {
        Object objZze = zzmkVar.zze();
        int iZzn = zzn(objZze, zzmkVar, bArr, i, i2, zzikVar);
        zzmkVar.zzf(objZze);
        zzikVar.zzc = objZze;
        return iZzn;
    }

    static int zze(zzmk zzmkVar, int i, byte[] bArr, int i2, int i3, zzkz zzkzVar, zzik zzikVar) throws IOException {
        int iZzd = zzd(zzmkVar, bArr, i2, i3, zzikVar);
        zzkzVar.add(zzikVar.zzc);
        while (iZzd < i3) {
            int iZzi = zzi(bArr, iZzd, zzikVar);
            if (i != zzikVar.zza) {
                break;
            }
            iZzd = zzd(zzmkVar, bArr, iZzi, i3, zzikVar);
            zzkzVar.add(zzikVar.zzc);
        }
        return iZzd;
    }

    static int zzf(byte[] bArr, int i, zzkz zzkzVar, zzik zzikVar) throws IOException {
        zzkt zzktVar = (zzkt) zzkzVar;
        int iZzi = zzi(bArr, i, zzikVar);
        int i2 = zzikVar.zza + iZzi;
        while (iZzi < i2) {
            iZzi = zzi(bArr, iZzi, zzikVar);
            zzktVar.zzh(zzikVar.zza);
        }
        if (iZzi == i2) {
            return iZzi;
        }
        throw zzlc.zzi();
    }

    static int zzg(byte[] bArr, int i, zzik zzikVar) throws zzlc {
        int iZzi = zzi(bArr, i, zzikVar);
        int i2 = zzikVar.zza;
        if (i2 < 0) {
            throw zzlc.zzf();
        }
        if (i2 == 0) {
            zzikVar.zzc = "";
            return iZzi;
        }
        zzikVar.zzc = new String(bArr, iZzi, i2, zzla.zza);
        return iZzi + i2;
    }

    static int zzi(byte[] bArr, int i, zzik zzikVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzj(b, bArr, i2, zzikVar);
        }
        zzikVar.zza = b;
        return i2;
    }

    static int zzj(int i, byte[] bArr, int i2, zzik zzikVar) {
        byte b = bArr[i2];
        int i3 = i2 + 1;
        int i4 = i & 127;
        if (b >= 0) {
            zzikVar.zza = i4 | (b << 7);
            return i3;
        }
        int i5 = i4 | ((b & 127) << 7);
        int i6 = i3 + 1;
        byte b2 = bArr[i3];
        if (b2 >= 0) {
            zzikVar.zza = i5 | (b2 << Ascii.SO);
            return i6;
        }
        int i7 = i5 | ((b2 & 127) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzikVar.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & 127) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzikVar.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & 127) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzikVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zzk(int i, byte[] bArr, int i2, int i3, zzkz zzkzVar, zzik zzikVar) {
        zzkt zzktVar = (zzkt) zzkzVar;
        int iZzi = zzi(bArr, i2, zzikVar);
        zzktVar.zzh(zzikVar.zza);
        while (iZzi < i3) {
            int iZzi2 = zzi(bArr, iZzi, zzikVar);
            if (i != zzikVar.zza) {
                break;
            }
            iZzi = zzi(bArr, iZzi2, zzikVar);
            zzktVar.zzh(zzikVar.zza);
        }
        return iZzi;
    }

    static int zzl(byte[] bArr, int i, zzik zzikVar) {
        long j = bArr[i];
        int i2 = i + 1;
        if (j >= 0) {
            zzikVar.zzb = j;
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
            i3 = i5;
            b = b2;
        }
        zzikVar.zzb = j2;
        return i3;
    }

    static int zzm(Object obj, zzmk zzmkVar, byte[] bArr, int i, int i2, int i3, zzik zzikVar) throws IOException {
        int iZzc = ((zzma) zzmkVar).zzc(obj, bArr, i, i2, i3, zzikVar);
        zzikVar.zzc = obj;
        return iZzc;
    }

    static int zzn(Object obj, zzmk zzmkVar, byte[] bArr, int i, int i2, zzik zzikVar) throws IOException {
        int i3;
        int i4 = i + 1;
        int i5 = bArr[i];
        if (i5 < 0) {
            int iZzj = zzj(i5, bArr, i4, zzikVar);
            i5 = zzikVar.zza;
            i3 = iZzj;
        } else {
            i3 = i4;
        }
        if (i5 < 0 || i5 > i2 - i3) {
            throw zzlc.zzi();
        }
        int i6 = i3 + i5;
        zzmkVar.zzi(obj, bArr, i3, i6, zzikVar);
        zzikVar.zzc = obj;
        return i6;
    }

    static int zzo(int i, byte[] bArr, int i2, int i3, zzik zzikVar) throws zzlc {
        if ((i >>> 3) == 0) {
            throw zzlc.zzc();
        }
        switch (i & 7) {
            case 0:
                return zzl(bArr, i2, zzikVar);
            case 1:
                return i2 + 8;
            case 2:
                return zzi(bArr, i2, zzikVar) + zzikVar.zza;
            case 3:
                int i4 = (i & (-8)) | 4;
                int i5 = 0;
                while (i2 < i3) {
                    i2 = zzi(bArr, i2, zzikVar);
                    i5 = zzikVar.zza;
                    if (i5 == i4) {
                        if (i2 <= i3 || i5 != i4) {
                            throw zzlc.zzg();
                        }
                        return i2;
                    }
                    i2 = zzo(i5, bArr, i2, i3, zzikVar);
                }
                if (i2 <= i3) {
                }
                throw zzlc.zzg();
            case 4:
            default:
                throw zzlc.zzc();
            case 5:
                return i2 + 4;
        }
    }

    static long zzp(byte[] bArr, int i) {
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    static int zzh(int i, byte[] bArr, int i2, int i3, zznc zzncVar, zzik zzikVar) throws zzlc {
        if ((i >>> 3) == 0) {
            throw zzlc.zzc();
        }
        switch (i & 7) {
            case 0:
                int iZzl = zzl(bArr, i2, zzikVar);
                zzncVar.zzj(i, Long.valueOf(zzikVar.zzb));
                return iZzl;
            case 1:
                zzncVar.zzj(i, Long.valueOf(zzp(bArr, i2)));
                return i2 + 8;
            case 2:
                int iZzi = zzi(bArr, i2, zzikVar);
                int i4 = zzikVar.zza;
                if (i4 < 0) {
                    throw zzlc.zzf();
                }
                if (i4 > bArr.length - iZzi) {
                    throw zzlc.zzi();
                }
                if (i4 == 0) {
                    zzncVar.zzj(i, zziv.zzb);
                } else {
                    zzncVar.zzj(i, zziv.zzk(bArr, iZzi, i4));
                }
                return iZzi + i4;
            case 3:
                int i5 = (i & (-8)) | 4;
                zznc zzncVarZzf = zznc.zzf();
                int i6 = 0;
                while (i2 < i3) {
                    int iZzi2 = zzi(bArr, i2, zzikVar);
                    i6 = zzikVar.zza;
                    if (i6 == i5) {
                        i2 = iZzi2;
                        if (i2 <= i3 || i6 != i5) {
                            throw zzlc.zzg();
                        }
                        zzncVar.zzj(i, zzncVarZzf);
                        return i2;
                    }
                    i2 = zzh(i6, bArr, iZzi2, i3, zzncVarZzf, zzikVar);
                }
                if (i2 <= i3) {
                }
                throw zzlc.zzg();
            case 4:
            default:
                throw zzlc.zzc();
            case 5:
                zzncVar.zzj(i, Integer.valueOf(zzb(bArr, i2)));
                return i2 + 4;
        }
    }
}
