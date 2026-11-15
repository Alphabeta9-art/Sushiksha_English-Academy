package com.google.android.gms.common.util;

import androidx.collection.ScatterMapKt;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
/* JADX INFO: loaded from: classes11.dex */
public class MurmurHash3 {
    private MurmurHash3() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int murmurhash3_x86_32(byte[] data, int offset, int len, int seed) {
        int i;
        int i2 = offset;
        while (true) {
            i = (len & (-4)) + offset;
            if (i2 >= i) {
                break;
            }
            int i3 = data[i2] & 255;
            int i4 = (data[i2 + 1] & 255) << 8;
            int i5 = (i3 | i4 | ((data[i2 + 2] & 255) << 16) | (data[i2 + 3] << 24)) * ScatterMapKt.MurmurHashC1;
            int i6 = seed ^ (((i5 << 15) | (i5 >>> 17)) * 461845907);
            seed = (-430675100) + (((i6 << 13) | (i6 >>> 19)) * 5);
            i2 += 4;
        }
        int i7 = 0;
        switch (len & 3) {
            case 1:
                int i8 = (i7 | (data[i] & 255)) * ScatterMapKt.MurmurHashC1;
                seed ^= ((i8 << 15) | (i8 >>> 17)) * 461845907;
                break;
            case 2:
                i7 |= (data[i + 1] & 255) << 8;
                int i9 = (i7 | (data[i] & 255)) * ScatterMapKt.MurmurHashC1;
                seed ^= ((i9 << 15) | (i9 >>> 17)) * 461845907;
                break;
            case 3:
                i7 = (data[i + 2] & 255) << 16;
                i7 |= (data[i + 1] & 255) << 8;
                int i10 = (i7 | (data[i] & 255)) * ScatterMapKt.MurmurHashC1;
                seed ^= ((i10 << 15) | (i10 >>> 17)) * 461845907;
                break;
        }
        int i11 = seed ^ len;
        int i12 = (i11 ^ (i11 >>> 16)) * (-2048144789);
        int i13 = (i12 ^ (i12 >>> 13)) * (-1028477387);
        return i13 ^ (i13 >>> 16);
    }
}
