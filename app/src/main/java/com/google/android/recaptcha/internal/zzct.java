package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaAction;
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
final class zzct extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzcx zzb;
    final /* synthetic */ RecaptchaAction zzc;
    final /* synthetic */ long zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzct(zzcx zzcxVar, RecaptchaAction recaptchaAction, long j, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzcxVar;
        this.zzc = recaptchaAction;
        this.zzd = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zzct(this.zzb, this.zzc, this.zzd, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzct) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objZzg;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        switch (i) {
            case 0:
                zzcx zzcxVar = this.zzb;
                RecaptchaAction recaptchaAction = this.zzc;
                long j = this.zzd;
                this.zza = 1;
                objZzg = zzcxVar.zzg(recaptchaAction, j, this);
                if (objZzg == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            default:
                objZzg = ((Result) obj).getValue();
                break;
        }
        ResultKt.throwOnFailure(objZzg);
        return objZzg;
    }
}
