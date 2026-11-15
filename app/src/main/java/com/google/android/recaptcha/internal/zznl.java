package com.google.android.recaptcha.internal;

import androidx.media3.extractor.ts.PsExtractor;
import androidx.window.core.layout.WindowSizeClass;
import com.google.common.base.Ascii;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zznl {
    static {
        if (zzni.zzx() && zzni.zzy()) {
            int i = zzij.zza;
        }
    }

    static /* bridge */ /* synthetic */ int zza(byte[] bArr, int i, int i2) {
        int i3 = i2 - i;
        byte b = bArr[i - 1];
        switch (i3) {
            case 0:
                if (b <= -12) {
                    return b;
                }
                return -1;
            case 1:
                byte b2 = bArr[i];
                if (b > -12 || b2 > -65) {
                    return -1;
                }
                return b ^ (b2 << 8);
            case 2:
                byte b3 = bArr[i];
                byte b4 = bArr[i + 1];
                if (b > -12 || b3 > -65 || b4 > -65) {
                    return -1;
                }
                return (b ^ (b3 << 8)) ^ (b4 << Ascii.DLE);
            default:
                throw new AssertionError();
        }
    }

    static int zzb(String str, byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        char cCharAt;
        int length = str.length();
        int i6 = 0;
        while (true) {
            i3 = i + i2;
            if (i6 >= length || (i5 = i6 + i) >= i3 || (cCharAt = str.charAt(i6)) >= 128) {
                break;
            }
            bArr[i5] = (byte) cCharAt;
            i6++;
        }
        if (i6 == length) {
            return i + length;
        }
        int i7 = i + i6;
        while (i6 < length) {
            char cCharAt2 = str.charAt(i6);
            if (cCharAt2 < 128 && i7 < i3) {
                bArr[i7] = (byte) cCharAt2;
                i7++;
            } else if (cCharAt2 < 2048 && i7 <= i3 - 2) {
                int i8 = i7 + 1;
                bArr[i7] = (byte) ((cCharAt2 >>> 6) | 960);
                bArr[i8] = (byte) ((cCharAt2 & '?') | 128);
                i7 = i8 + 1;
            } else {
                if ((cCharAt2 >= 55296 && cCharAt2 <= 57343) || i7 > i3 - 3) {
                    if (i7 > i3 - 4) {
                        if (cCharAt2 >= 55296 && cCharAt2 <= 57343 && ((i4 = i6 + 1) == str.length() || !Character.isSurrogatePair(cCharAt2, str.charAt(i4)))) {
                            throw new zznk(i6, length);
                        }
                        throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + i7);
                    }
                    int i9 = i6 + 1;
                    if (i9 != str.length()) {
                        char cCharAt3 = str.charAt(i9);
                        if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                            int i10 = i7 + 1;
                            int i11 = i10 + 1;
                            int i12 = i11 + 1;
                            int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                            bArr[i7] = (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK);
                            bArr[i10] = (byte) (((codePoint >>> 12) & 63) | 128);
                            bArr[i11] = (byte) (((codePoint >>> 6) & 63) | 128);
                            i7 = i12 + 1;
                            bArr[i12] = (byte) ((codePoint & 63) | 128);
                            i6 = i9;
                        } else {
                            i6 = i9;
                        }
                    }
                    throw new zznk(i6 - 1, length);
                }
                int i13 = i7 + 1;
                int i14 = i13 + 1;
                bArr[i7] = (byte) ((cCharAt2 >>> '\f') | WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND);
                bArr[i13] = (byte) (((cCharAt2 >>> 6) & 63) | 128);
                bArr[i14] = (byte) ((cCharAt2 & '?') | 128);
                i7 = i14 + 1;
            }
            i6++;
        }
        return i7;
    }

    static int zzc(String str) {
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && str.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (i2 < length) {
            char cCharAt = str.charAt(i2);
            if (cCharAt >= 2048) {
                int length2 = str.length();
                while (i2 < length2) {
                    char cCharAt2 = str.charAt(i2);
                    if (cCharAt2 < 2048) {
                        i += (127 - cCharAt2) >>> 31;
                    } else {
                        i += 2;
                        if (cCharAt2 >= 55296 && cCharAt2 <= 57343) {
                            if (Character.codePointAt(str, i2) < 65536) {
                                throw new zznk(i2, length2);
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i3 += i;
                break;
            }
            i3 += (127 - cCharAt) >>> 31;
            i2++;
        }
        if (i3 >= length) {
            return i3;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i3) + 4294967296L));
    }

    static String zzd(byte[] bArr, int i, int i2) throws zzlc {
        int length = bArr.length;
        if ((i | i2 | ((length - i) - i2)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(length), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        int i3 = i + i2;
        char[] cArr = new char[i2];
        int i4 = 0;
        while (i < i3) {
            byte b = bArr[i];
            if (!zznj.zzd(b)) {
                break;
            }
            i++;
            cArr[i4] = (char) b;
            i4++;
        }
        int i5 = i4;
        while (i < i3) {
            int i6 = i + 1;
            byte b2 = bArr[i];
            if (zznj.zzd(b2)) {
                cArr[i5] = (char) b2;
                i = i6;
                i5++;
                while (i < i3) {
                    byte b3 = bArr[i];
                    if (!zznj.zzd(b3)) {
                        break;
                    }
                    i++;
                    cArr[i5] = (char) b3;
                    i5++;
                }
            } else if (b2 < -32) {
                if (i6 >= i3) {
                    throw zzlc.zzd();
                }
                zznj.zzc(b2, bArr[i6], cArr, i5);
                i5++;
                i = i6 + 1;
            } else if (b2 < -16) {
                if (i6 >= i3 - 1) {
                    throw zzlc.zzd();
                }
                int i7 = i6 + 1;
                zznj.zzb(b2, bArr[i6], bArr[i7], cArr, i5);
                i5++;
                i = i7 + 1;
            } else {
                if (i6 >= i3 - 2) {
                    throw zzlc.zzd();
                }
                int i8 = i6 + 1;
                int i9 = i8 + 1;
                zznj.zza(b2, bArr[i6], bArr[i8], bArr[i9], cArr, i5);
                i5 += 2;
                i = i9 + 1;
            }
        }
        return new String(cArr, 0, i5);
    }

    /* JADX WARN: Code duplicated, block: B:47:0x0073 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:87:? A[RETURN, SYNTHETIC] */
    static boolean zze(byte[] bArr, int i, int i2) {
        while (i < i2 && bArr[i] >= 0) {
            i++;
        }
        if (i >= i2) {
            return true;
        }
        while (i < i2) {
            int i3 = i + 1;
            int iZza = bArr[i];
            if (iZza < 0) {
                if (iZza >= -32) {
                    if (iZza < -16) {
                        if (i3 >= i2 - 1) {
                            iZza = zza(bArr, i3, i2);
                        } else {
                            int i4 = i3 + 1;
                            byte b = bArr[i3];
                            if (b > -65) {
                                return false;
                            }
                            if (iZza == -32 && b < -96) {
                                return false;
                            }
                            if (iZza == -19 && b >= -96) {
                                return false;
                            }
                            i = i4 + 1;
                            if (bArr[i4] > -65) {
                                return false;
                            }
                        }
                    } else if (i3 >= i2 - 2) {
                        iZza = zza(bArr, i3, i2);
                    } else {
                        int i5 = i3 + 1;
                        byte b2 = bArr[i3];
                        if (b2 > -65 || (((iZza << 28) + (b2 + 112)) >> 30) != 0) {
                            return false;
                        }
                        int i6 = i5 + 1;
                        if (bArr[i5] > -65) {
                            return false;
                        }
                        i3 = i6 + 1;
                        if (bArr[i6] > -65) {
                            return false;
                        }
                    }
                    if (iZza != 0) {
                        return false;
                    }
                    return true;
                }
                if (i3 >= i2) {
                    if (iZza != 0) {
                        return false;
                    }
                    return true;
                }
                if (iZza < -62) {
                    return false;
                }
                i = i3 + 1;
                if (bArr[i3] > -65) {
                    return false;
                }
            }
            i = i3;
        }
        return true;
    }
}
