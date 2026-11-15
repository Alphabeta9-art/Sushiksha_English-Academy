package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzw extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    int zzc;
    final /* synthetic */ zzpq zzd;
    final /* synthetic */ zzx zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzw(zzpq zzpqVar, zzx zzxVar, Continuation continuation) {
        super(2, continuation);
        this.zzd = zzpqVar;
        this.zze = zzxVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zzw(this.zzd, this.zze, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzw) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Object, java.util.Iterator] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        ?? it;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.zzc) {
            case 0:
                ResultKt.throwOnFailure(obj);
                if (!this.zzd.zzO()) {
                    Result.Companion companion = Result.INSTANCE;
                    return Result.m9087boximpl(Result.m9088constructorimpl(ResultKt.createFailure(new zzbj(zzbh.zzb, zzbg.zzac, null))));
                }
                zzpq zzpqVar = this.zzd;
                zzx zzxVar = this.zze;
                zzps zzpsVarZzj = zzpqVar.zzj();
                zzxVar.zzc = zzpsVarZzj.zzi();
                it = this.zze.zzb.iterator();
                obj2 = zzpsVarZzj;
                break;
                break;
            default:
                Object obj3 = this.zzb;
                Object obj4 = this.zza;
                ResultKt.throwOnFailure(obj);
                obj2 = obj4;
                it = obj3;
                break;
        }
        while (it.hasNext()) {
            zzaa zzaaVar = (zzaa) it.next();
            this.zza = obj2;
            this.zzb = it;
            this.zzc = 1;
            if (zzaaVar.zzd((zzps) obj2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        Result.Companion companion2 = Result.INSTANCE;
        return Result.m9087boximpl(Result.m9088constructorimpl(Unit.INSTANCE));
    }
}
