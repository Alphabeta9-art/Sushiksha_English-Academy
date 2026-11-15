package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzeh extends zzee {
    private final zzeg zza;
    private final String zzb;

    public zzeh(zzeg zzegVar, String str, Object obj) {
        super(obj);
        this.zza = zzegVar;
        this.zzb = str;
    }

    @Override // com.google.android.recaptcha.internal.zzee
    public final boolean zza(Object obj, Method method, Object[] objArr) {
        List listEmptyList;
        if (!Intrinsics.areEqual(method.getName(), this.zzb)) {
            return false;
        }
        zzeg zzegVar = this.zza;
        if (objArr == null || (listEmptyList = ArraysKt.asList(objArr)) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        zzegVar.zzb(listEmptyList);
        return true;
    }
}
