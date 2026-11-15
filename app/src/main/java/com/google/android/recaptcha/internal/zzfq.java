package com.google.android.recaptcha.internal;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import kotlin.collections.ArraysKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzfq implements zzfe {
    public static final zzfq zza = new zzfq();

    private zzfq() {
    }

    @Override // com.google.android.recaptcha.internal.zzfe
    public final void zza(int i, zzek zzekVar, zzrr... zzrrVarArr) throws zzby {
        int length = zzrrVarArr.length;
        if (length == 0) {
            throw new zzby(4, 3, null);
        }
        Constructor<?> constructorZza = zzekVar.zzc().zza(zzrrVarArr[0]);
        if (true != (constructorZza instanceof Object)) {
            constructorZza = null;
        }
        if (constructorZza == null) {
            throw new zzby(4, 5, null);
        }
        Constructor<?> constructor = constructorZza instanceof Constructor ? constructorZza : constructorZza.getClass().getConstructor(new Class[0]);
        Object[] objArrZzg = zzekVar.zzc().zzg(ArraysKt.toList(zzrrVarArr).subList(1, length));
        try {
            zzekVar.zzc().zze(i, constructor.newInstance(Arrays.copyOf(objArrZzg, objArrZzg.length)));
        } catch (Exception e) {
            throw new zzby(6, 14, e);
        }
    }
}
