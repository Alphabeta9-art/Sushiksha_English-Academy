package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzef extends zzee {
    private final Function2 zza;
    private final String zzb;

    public zzef(Function2 function2, String str, Object obj) {
        super(obj);
        this.zza = function2;
        this.zzb = str;
    }

    @Override // com.google.android.recaptcha.internal.zzee
    public final boolean zza(Object obj, Method method, Object[] objArr) {
        Collection collectionEmptyList;
        if (!Intrinsics.areEqual(method.getName(), this.zzb)) {
            return false;
        }
        zzrj zzrjVarZzf = zzrm.zzf();
        if (objArr != null) {
            collectionEmptyList = new ArrayList(objArr.length);
            for (Object obj2 : objArr) {
                zzrk zzrkVarZzf = zzrl.zzf();
                zzrkVarZzf.zzv(obj2.toString());
                collectionEmptyList.add((zzrl) zzrkVarZzf.zzj());
            }
        } else {
            collectionEmptyList = CollectionsKt.emptyList();
        }
        zzrjVarZzf.zzd(collectionEmptyList);
        zzrm zzrmVar = (zzrm) zzrjVarZzf.zzj();
        Function2 function2 = this.zza;
        byte[] bArrZzd = zzrmVar.zzd();
        function2.invoke(objArr, zzhz.zzh().zzi(bArrZzd, 0, bArrZzd.length));
        return true;
    }
}
