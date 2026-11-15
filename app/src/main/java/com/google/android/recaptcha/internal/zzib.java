package com.google.android.recaptcha.internal;

import java.math.RoundingMode;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzib {
    /* JADX WARN: Code duplicated, block: B:27:0x004a  */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004b, code lost:
    
        if (r3 != false) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int zza(int r5, int r6, java.math.RoundingMode r7) {
        /*
            if (r7 == 0) goto L5b
            if (r6 == 0) goto L53
            int r0 = r5 / r6
            int r1 = r6 * r0
            int r1 = r5 - r1
            if (r1 == 0) goto L52
            r5 = r5 ^ r6
            int[] r2 = com.google.android.recaptcha.internal.zzia.zza
            int r3 = r7.ordinal()
            r2 = r2[r3]
            int r5 = r5 >> 31
            r3 = 1
            r5 = r5 | r3
            r4 = 0
            switch(r2) {
                case 1: goto L4f;
                case 2: goto L52;
                case 3: goto L47;
                case 4: goto L4d;
                case 5: goto L43;
                case 6: goto L23;
                case 7: goto L23;
                case 8: goto L23;
                default: goto L1d;
            }
        L1d:
            java.lang.AssertionError r5 = new java.lang.AssertionError
            r5.<init>()
            throw r5
        L23:
            int r1 = java.lang.Math.abs(r1)
            int r6 = java.lang.Math.abs(r6)
            int r6 = r6 - r1
            int r1 = r1 - r6
            if (r1 != 0) goto L40
            java.math.RoundingMode r6 = java.math.RoundingMode.HALF_UP
            if (r7 == r6) goto L49
            java.math.RoundingMode r6 = java.math.RoundingMode.HALF_EVEN
            if (r7 != r6) goto L39
            r6 = r3
            goto L3a
        L39:
            r6 = r4
        L3a:
            r7 = r0 & 1
            r6 = r6 & r7
            if (r6 == 0) goto L4a
            goto L49
        L40:
            if (r1 <= 0) goto L4a
            goto L49
        L43:
            if (r5 > 0) goto L49
            r3 = r4
            goto L4b
        L47:
            if (r5 >= 0) goto L4a
        L49:
            goto L4b
        L4a:
            r3 = r4
        L4b:
            if (r3 == 0) goto L52
        L4d:
            int r0 = r0 + r5
            return r0
        L4f:
            com.google.android.recaptcha.internal.zzid.zzb(r4)
        L52:
            return r0
        L53:
            java.lang.ArithmeticException r5 = new java.lang.ArithmeticException
            java.lang.String r6 = "/ by zero"
            r5.<init>(r6)
            throw r5
        L5b:
            r5 = 0
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzib.zza(int, int, java.math.RoundingMode):int");
    }

    public static int zzb(int i, RoundingMode roundingMode) {
        if (i <= 0) {
            throw new IllegalArgumentException("x (0) must be > 0");
        }
        switch (zzia.zza[roundingMode.ordinal()]) {
            case 1:
                zzid.zzb(((i + (-1)) & i) == 0);
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 32 - Integer.numberOfLeadingZeros(i - 1);
            case 6:
            case 7:
            case 8:
                int iNumberOfLeadingZeros = Integer.numberOfLeadingZeros(i);
                return (31 - iNumberOfLeadingZeros) + ((((-1257966797) >>> iNumberOfLeadingZeros) - i) >>> 31);
            default:
                throw new AssertionError();
        }
        return 31 - Integer.numberOfLeadingZeros(i);
    }
}
