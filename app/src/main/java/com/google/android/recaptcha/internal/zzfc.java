package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzfc implements zzfe {
    public static final zzfc zza = new zzfc();

    private zzfc() {
    }

    @Override // com.google.android.recaptcha.internal.zzfe
    public final void zza(int i, zzek zzekVar, zzrr... zzrrVarArr) throws zzby {
        Object objValueOf;
        if (zzrrVarArr.length != 2) {
            throw new zzby(4, 3, null);
        }
        Object objZza = zzekVar.zzc().zza(zzrrVarArr[0]);
        if (true != (objZza instanceof Object)) {
            objZza = null;
        }
        if (objZza == null) {
            throw new zzby(4, 5, null);
        }
        Object objZza2 = zzekVar.zzc().zza(zzrrVarArr[1]);
        if (true != (objZza2 instanceof Integer)) {
            objZza2 = null;
        }
        Integer num = (Integer) objZza2;
        if (num == null) {
            throw new zzby(4, 5, null);
        }
        int iIntValue = num.intValue();
        try {
            if (objZza instanceof Integer) {
                objValueOf = Integer.valueOf(((Number) objZza).intValue() / iIntValue);
            } else {
                if (!(objZza instanceof int[])) {
                    throw new zzby(4, 5, null);
                }
                int[] iArr = (int[]) objZza;
                ArrayList arrayList = new ArrayList(iArr.length);
                for (int i2 : iArr) {
                    arrayList.add(Integer.valueOf(i2 / iIntValue));
                }
                objValueOf = (Serializable) arrayList.toArray(new Integer[0]);
            }
            zzekVar.zzc().zze(i, objValueOf);
        } catch (ArithmeticException e) {
            throw new zzby(4, 6, e);
        }
    }
}
