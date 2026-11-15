package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzcr extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzcx zzb;
    int zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcr(zzcx zzcxVar, Continuation continuation) {
        super(continuation);
        this.zzb = zzcxVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.zza = obj;
        this.zzc |= Integer.MIN_VALUE;
        Object objMo8891executegIAlus = this.zzb.mo8891executegIAlus(null, this);
        return objMo8891executegIAlus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo8891executegIAlus : Result.m9087boximpl(objMo8891executegIAlus);
    }
}
