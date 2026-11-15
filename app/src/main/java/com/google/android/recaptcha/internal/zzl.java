package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzl {
    public zzdc zza;
    private final List zzb;

    public zzl() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ zzl(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        List listEmptyList = CollectionsKt.emptyList();
        this.zzb = new ArrayList();
        zze[] zzeVarArr = (zze[]) listEmptyList.toArray(new zze[0]);
        zzg((zze[]) Arrays.copyOf(zzeVarArr, zzeVarArr.length));
    }

    private final void zzg(zze... zzeVarArr) {
        CollectionsKt.addAll(this.zzb, zzeVarArr);
    }

    public final zzdc zza() {
        zzdc zzdcVar = this.zza;
        if (zzdcVar != null) {
            return zzdcVar;
        }
        return null;
    }

    public final Object zzb(String str, long j, Continuation continuation) {
        return CoroutineScopeKt.coroutineScope(new zzh(this, str, j, null), continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object zzc(long j, zzpq zzpqVar, Continuation continuation) {
        zzi zziVar;
        if (continuation instanceof zzi) {
            zziVar = (zzi) continuation;
            int i = zziVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zziVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zziVar = new zzi(this, continuation);
            }
        } else {
            zziVar = new zzi(this, continuation);
        }
        Object objCoroutineScope = zziVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (zziVar.zzc) {
            case 0:
                ResultKt.throwOnFailure(objCoroutineScope);
                zzk zzkVar = new zzk(this, j, zzpqVar, null);
                zziVar.zzc = 1;
                objCoroutineScope = CoroutineScopeKt.coroutineScope(zzkVar, zziVar);
                if (objCoroutineScope == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure(objCoroutineScope);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return ((Result) objCoroutineScope).getValue();
    }

    public final List zzd() {
        return this.zzb;
    }

    public final void zze(zze... zzeVarArr) {
        zzg((zze[]) Arrays.copyOf(zzeVarArr, 2));
    }

    public final void zzf(zzqf zzqfVar) {
        Iterator it = this.zzb.iterator();
        while (it.hasNext()) {
            ((zze) it.next()).zzk(zzqfVar);
        }
    }
}
