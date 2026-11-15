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
final class zzd extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zze zzb;
    final /* synthetic */ zzpq zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzd(zze zzeVar, zzpq zzpqVar, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzeVar;
        this.zzc = zzpqVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zzd(this.zzb, this.zzc, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzd) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws zzbj {
        Object objZzh;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        switch (i) {
            case 0:
                zze zzeVar = this.zzb;
                zzpq zzpqVar = this.zzc;
                this.zza = 1;
                objZzh = zzeVar.zzh(zzpqVar, this);
                if (objZzh == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            default:
                objZzh = ((Result) obj).getValue();
                break;
        }
        return Result.m9087boximpl(objZzh);
    }
}
