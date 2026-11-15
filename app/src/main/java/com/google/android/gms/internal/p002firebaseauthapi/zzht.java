package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.common.base.Ascii;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzht {
    private static long zza(byte[] bArr, int i, int i2) {
        return (zza(bArr, i) >> i2) & 67108863;
    }

    private static long zza(byte[] bArr, int i) {
        return ((long) ((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24))) & 4294967295L;
    }

    private static void zza(byte[] bArr, long j, int i) {
        int i2 = 0;
        while (i2 < 4) {
            bArr[i + i2] = (byte) (255 & j);
            i2++;
            j >>= 8;
        }
    }

    public static byte[] zza(byte[] bArr, byte[] bArr2) {
        if (bArr.length == 32) {
            long jZza = zza(bArr, 0, 0) & 67108863;
            int i = 3;
            int i2 = 2;
            long jZza2 = zza(bArr, 3, 2) & 67108611;
            long jZza3 = zza(bArr, 6, 4) & 67092735;
            long jZza4 = zza(bArr, 9, 6) & 66076671;
            long jZza5 = zza(bArr, 12, 8) & 1048575;
            long j = jZza2 * 5;
            long j2 = jZza3 * 5;
            long j3 = jZza4 * 5;
            long j4 = jZza5 * 5;
            int i3 = 17;
            byte[] bArr3 = new byte[17];
            long j5 = 0;
            int i4 = 0;
            long j6 = 0;
            long j7 = 0;
            long j8 = 0;
            long j9 = 0;
            while (i4 < bArr2.length) {
                int iMin = Math.min(16, bArr2.length - i4);
                System.arraycopy(bArr2, i4, bArr3, 0, iMin);
                bArr3[iMin] = 1;
                if (iMin != 16) {
                    Arrays.fill(bArr3, iMin + 1, i3, (byte) 0);
                }
                long jZza6 = j9 + zza(bArr3, 0, 0);
                long jZza7 = j5 + zza(bArr3, i, i2);
                long jZza8 = j6 + zza(bArr3, 6, 4);
                long jZza9 = j7 + zza(bArr3, 9, 6);
                long j10 = jZza2;
                long jZza10 = j8 + (zza(bArr3, 12, 8) | ((long) (bArr3[16] << Ascii.CAN)));
                long j11 = (jZza6 * jZza) + (jZza7 * j4) + (jZza8 * j3) + (jZza9 * j2) + (jZza10 * j);
                long j12 = (jZza6 * j10) + (jZza7 * jZza) + (jZza8 * j4) + (jZza9 * j3) + (jZza10 * j2);
                long j13 = (jZza6 * jZza3) + (jZza7 * j10) + (jZza8 * jZza) + (jZza9 * j4) + (jZza10 * j3);
                long j14 = (jZza6 * jZza4) + (jZza7 * jZza3) + (jZza8 * j10) + (jZza9 * jZza) + (jZza10 * j4);
                long j15 = (jZza6 * jZza5) + (jZza7 * jZza4) + (jZza8 * jZza3) + (jZza9 * j10) + (jZza10 * jZza);
                long j16 = j12 + (j11 >> 26);
                long j17 = j13 + (j16 >> 26);
                j6 = j17 & 67108863;
                long j18 = j14 + (j17 >> 26);
                j7 = j18 & 67108863;
                long j19 = j15 + (j18 >> 26);
                j8 = j19 & 67108863;
                long j20 = (j11 & 67108863) + ((j19 >> 26) * 5);
                j9 = j20 & 67108863;
                j5 = (j16 & 67108863) + (j20 >> 26);
                i4 += 16;
                jZza2 = j10;
                i3 = 17;
                i = 3;
                i2 = 2;
            }
            long j21 = j6 + (j5 >> 26);
            long j22 = j21 >> 26;
            long j23 = j21 & 67108863;
            long j24 = j7 + j22;
            long j25 = j24 >> 26;
            long j26 = j24 & 67108863;
            long j27 = j8 + j25;
            long j28 = j27 >> 26;
            long j29 = j27 & 67108863;
            long j30 = j9 + (j28 * 5);
            long j31 = j30 >> 26;
            long j32 = j30 & 67108863;
            long j33 = (j5 & 67108863) + j31;
            long j34 = j32 + 5;
            long j35 = j33 + (j34 >> 26);
            long j36 = j23 + (j35 >> 26);
            long j37 = j26 + (j36 >> 26);
            long j38 = (j29 + (j37 >> 26)) - 67108864;
            long j39 = j38 >> 63;
            long j40 = j32 & j39;
            long j41 = j33 & j39;
            long j42 = j23 & j39;
            long j43 = j26 & j39;
            long j44 = j29 & j39;
            long j45 = ~j39;
            long j46 = j41 | (j35 & 67108863 & j45);
            long j47 = j42 | (j36 & 67108863 & j45);
            long j48 = j43 | (j37 & 67108863 & j45);
            long j49 = (j40 | (j34 & 67108863 & j45) | (j46 << 26)) & 4294967295L;
            long j50 = ((j46 >> 6) | (j47 << 20)) & 4294967295L;
            long j51 = ((j47 >> 12) | (j48 << 14)) & 4294967295L;
            long jZza11 = j49 + zza(bArr, 16);
            long jZza12 = j50 + zza(bArr, 20) + (jZza11 >> 32);
            long jZza13 = j51 + zza(bArr, 24) + (jZza12 >> 32);
            long jZza14 = ((((j48 >> 18) | ((j44 | (j38 & j45)) << 8)) & 4294967295L) + zza(bArr, 28) + (jZza13 >> 32)) & 4294967295L;
            byte[] bArr4 = new byte[16];
            zza(bArr4, jZza11 & 4294967295L, 0);
            zza(bArr4, jZza12 & 4294967295L, 4);
            zza(bArr4, jZza13 & 4294967295L, 8);
            zza(bArr4, jZza14, 12);
            return bArr4;
        }
        throw new IllegalArgumentException("The key length in bytes must be 32.");
    }
}
