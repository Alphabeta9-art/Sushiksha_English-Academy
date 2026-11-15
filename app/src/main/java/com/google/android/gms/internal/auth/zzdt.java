package com.google.android.gms.internal.auth;

import com.google.common.base.Ascii;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* JADX INFO: loaded from: classes11.dex */
final class zzdt {
    static int zza(byte[] bArr, int i, zzds zzdsVar) throws zzfa {
        int iZzj = zzj(bArr, i, zzdsVar);
        int i2 = zzdsVar.zza;
        if (i2 < 0) {
            throw zzfa.zzc();
        }
        if (i2 > bArr.length - iZzj) {
            throw zzfa.zzf();
        }
        if (i2 == 0) {
            zzdsVar.zzc = zzee.zzb;
            return iZzj;
        }
        zzdsVar.zzc = zzee.zzk(bArr, iZzj, i2);
        return iZzj + i2;
    }

    static int zzb(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24);
    }

    static int zzc(zzgh zzghVar, byte[] bArr, int i, int i2, int i3, zzds zzdsVar) throws IOException {
        zzfz zzfzVar = (zzfz) zzghVar;
        Object objZzd = zzfzVar.zzd();
        int iZzb = zzfzVar.zzb(objZzd, bArr, i, i2, i3, zzdsVar);
        zzfzVar.zze(objZzd);
        zzdsVar.zzc = objZzd;
        return iZzb;
    }

    static int zzd(zzgh zzghVar, byte[] bArr, int i, int i2, zzds zzdsVar) throws IOException {
        int i3;
        int i4 = i + 1;
        int i5 = bArr[i];
        if (i5 < 0) {
            int iZzk = zzk(i5, bArr, i4, zzdsVar);
            i5 = zzdsVar.zza;
            i3 = iZzk;
        } else {
            i3 = i4;
        }
        if (i5 < 0 || i5 > i2 - i3) {
            throw zzfa.zzf();
        }
        Object objZzd = zzghVar.zzd();
        int i6 = i3 + i5;
        zzghVar.zzg(objZzd, bArr, i3, i6, zzdsVar);
        zzghVar.zze(objZzd);
        zzdsVar.zzc = objZzd;
        return i6;
    }

    static int zze(zzgh zzghVar, int i, byte[] bArr, int i2, int i3, zzey zzeyVar, zzds zzdsVar) throws IOException {
        int iZzd = zzd(zzghVar, bArr, i2, i3, zzdsVar);
        zzeyVar.add(zzdsVar.zzc);
        while (iZzd < i3) {
            int iZzj = zzj(bArr, iZzd, zzdsVar);
            if (i != zzdsVar.zza) {
                break;
            }
            iZzd = zzd(zzghVar, bArr, iZzj, i3, zzdsVar);
            zzeyVar.add(zzdsVar.zzc);
        }
        return iZzd;
    }

    static int zzf(byte[] bArr, int i, zzey zzeyVar, zzds zzdsVar) throws IOException {
        zzev zzevVar = (zzev) zzeyVar;
        int iZzj = zzj(bArr, i, zzdsVar);
        int i2 = zzdsVar.zza + iZzj;
        while (iZzj < i2) {
            iZzj = zzj(bArr, iZzj, zzdsVar);
            zzevVar.zze(zzdsVar.zza);
        }
        if (iZzj == i2) {
            return iZzj;
        }
        throw zzfa.zzf();
    }

    static int zzg(byte[] bArr, int i, zzds zzdsVar) throws zzfa {
        int iZzj = zzj(bArr, i, zzdsVar);
        int i2 = zzdsVar.zza;
        if (i2 < 0) {
            throw zzfa.zzc();
        }
        if (i2 == 0) {
            zzdsVar.zzc = "";
            return iZzj;
        }
        zzdsVar.zzc = new String(bArr, iZzj, i2, zzez.zzb);
        return iZzj + i2;
    }

    static int zzh(byte[] bArr, int i, zzds zzdsVar) throws zzfa {
        int iZzj = zzj(bArr, i, zzdsVar);
        int i2 = zzdsVar.zza;
        if (i2 < 0) {
            throw zzfa.zzc();
        }
        if (i2 == 0) {
            zzdsVar.zzc = "";
            return iZzj;
        }
        zzdsVar.zzc = zzhm.zzb(bArr, iZzj, i2);
        return iZzj + i2;
    }

    static int zzj(byte[] bArr, int i, zzds zzdsVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzk(b, bArr, i2, zzdsVar);
        }
        zzdsVar.zza = b;
        return i2;
    }

    static int zzk(int i, byte[] bArr, int i2, zzds zzdsVar) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzdsVar.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & 127) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzdsVar.zza = i5 | (b2 << Ascii.SO);
            return i6;
        }
        int i7 = i5 | ((b2 & 127) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzdsVar.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & 127) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzdsVar.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & 127) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzdsVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zzl(int i, byte[] bArr, int i2, int i3, zzey zzeyVar, zzds zzdsVar) {
        zzev zzevVar = (zzev) zzeyVar;
        int iZzj = zzj(bArr, i2, zzdsVar);
        zzevVar.zze(zzdsVar.zza);
        while (iZzj < i3) {
            int iZzj2 = zzj(bArr, iZzj, zzdsVar);
            if (i != zzdsVar.zza) {
                break;
            }
            iZzj = zzj(bArr, iZzj2, zzdsVar);
            zzevVar.zze(zzdsVar.zza);
        }
        return iZzj;
    }

    static int zzm(byte[] bArr, int i, zzds zzdsVar) {
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            zzdsVar.zzb = j;
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
        zzdsVar.zzb = j2;
        return i3;
    }

    static long zzn(byte[] bArr, int i) {
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    static int zzi(int i, byte[] bArr, int i2, int i3, zzgz zzgzVar, zzds zzdsVar) throws zzfa {
        if ((i >>> 3) == 0) {
            throw zzfa.zza();
        }
        switch (i & 7) {
            case 0:
                int iZzm = zzm(bArr, i2, zzdsVar);
                zzgzVar.zzf(i, Long.valueOf(zzdsVar.zzb));
                return iZzm;
            case 1:
                zzgzVar.zzf(i, Long.valueOf(zzn(bArr, i2)));
                return i2 + 8;
            case 2:
                int iZzj = zzj(bArr, i2, zzdsVar);
                int i4 = zzdsVar.zza;
                if (i4 < 0) {
                    throw zzfa.zzc();
                }
                if (i4 > bArr.length - iZzj) {
                    throw zzfa.zzf();
                }
                if (i4 == 0) {
                    zzgzVar.zzf(i, zzee.zzb);
                } else {
                    zzgzVar.zzf(i, zzee.zzk(bArr, iZzj, i4));
                }
                return iZzj + i4;
            case 3:
                int i5 = (i & (-8)) | 4;
                zzgz zzgzVarZzc = zzgz.zzc();
                int i6 = 0;
                while (i2 < i3) {
                    int iZzj2 = zzj(bArr, i2, zzdsVar);
                    i6 = zzdsVar.zza;
                    if (i6 == i5) {
                        i2 = iZzj2;
                        if (i2 <= i3 || i6 != i5) {
                            throw zzfa.zzd();
                        }
                        zzgzVar.zzf(i, zzgzVarZzc);
                        return i2;
                    }
                    i2 = zzi(i6, bArr, iZzj2, i3, zzgzVarZzc, zzdsVar);
                }
                if (i2 <= i3) {
                }
                throw zzfa.zzd();
            case 4:
            default:
                throw zzfa.zza();
            case 5:
                zzgzVar.zzf(i, Integer.valueOf(zzb(bArr, i2)));
                return i2 + 4;
        }
    }
}
