package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.collections.ArraysKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzfm implements zzfe {
    public static final zzfm zza = new zzfm();

    private zzfm() {
    }

    @Override // com.google.android.recaptcha.internal.zzfe
    public final void zza(int i, zzek zzekVar, zzrr... zzrrVarArr) throws zzby {
        int length = zzrrVarArr.length;
        if (length < 2) {
            throw new zzby(4, 3, null);
        }
        Object objZza = zzekVar.zzc().zza(zzrrVarArr[0]);
        if (true != (objZza instanceof Method)) {
            objZza = null;
        }
        Method method = (Method) objZza;
        if (method == null) {
            throw new zzby(4, 5, null);
        }
        Object objZza2 = zzekVar.zzc().zza(zzrrVarArr[1]);
        Object[] objArrZzg = zzekVar.zzc().zzg(ArraysKt.toList(zzrrVarArr).subList(2, length));
        try {
            zzekVar.zzc().zze(i, method.invoke(objZza2, Arrays.copyOf(objArrZzg, objArrZzg.length)));
        } catch (Exception e) {
            throw new zzby(6, 15, e);
        }
    }
}
