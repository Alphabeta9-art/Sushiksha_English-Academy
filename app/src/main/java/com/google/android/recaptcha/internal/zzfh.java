package com.google.android.recaptcha.internal;

import java.util.Arrays;
import kotlin.collections.ArraysKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzfh implements zzfe {
    public static final zzfh zza = new zzfh();

    private zzfh() {
    }

    @Override // com.google.android.recaptcha.internal.zzfe
    public final void zza(int i, zzek zzekVar, zzrr... zzrrVarArr) throws zzby {
        int length = zzrrVarArr.length;
        if (length == 0) {
            throw new zzby(4, 3, null);
        }
        Object objZza = zzekVar.zzc().zza(zzrrVarArr[0]);
        if (true != (objZza instanceof Class)) {
            objZza = null;
        }
        Class cls = (Class) objZza;
        if (cls == null) {
            throw new zzby(4, 5, null);
        }
        Class[] clsArrZzf = zzekVar.zzc().zzf(ArraysKt.toList(zzrrVarArr).subList(1, length));
        try {
            zzekVar.zzc().zze(i, cls.getConstructor((Class[]) Arrays.copyOf(clsArrZzf, clsArrZzf.length)));
        } catch (Exception e) {
            throw new zzby(6, 9, e);
        }
    }
}
