package com.google.android.recaptcha.internal;

import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzfj implements zzfe {
    public static final zzfj zza = new zzfj();

    private zzfj() {
    }

    @Override // com.google.android.recaptcha.internal.zzfe
    public final void zza(int i, zzek zzekVar, zzrr... zzrrVarArr) throws zzby {
        int length = zzrrVarArr.length;
        if (length < 2) {
            throw new zzby(4, 3, null);
        }
        Class<?> clsZza = zzekVar.zzc().zza(zzrrVarArr[0]);
        if (true != (clsZza instanceof Object)) {
            clsZza = null;
        }
        if (clsZza == null) {
            throw new zzby(4, 5, null);
        }
        Class<?> cls = clsZza instanceof Class ? clsZza : clsZza.getClass();
        Object objZza = zzekVar.zzc().zza(zzrrVarArr[1]);
        if (true != (objZza instanceof String)) {
            objZza = null;
        }
        String str = (String) objZza;
        if (str == null) {
            throw new zzby(4, 5, null);
        }
        String strZza = zzekVar.zzh().zza(str);
        if (Intrinsics.areEqual(strZza, "forName")) {
            throw new zzby(6, 48, null);
        }
        Class[] clsArrZzf = zzekVar.zzc().zzf(ArraysKt.toList(zzrrVarArr).subList(2, length));
        try {
            zzekVar.zzc().zze(i, cls.getMethod(strZza, (Class[]) Arrays.copyOf(clsArrZzf, clsArrZzf.length)));
        } catch (Exception e) {
            throw new zzby(6, 13, e);
        }
    }
}
