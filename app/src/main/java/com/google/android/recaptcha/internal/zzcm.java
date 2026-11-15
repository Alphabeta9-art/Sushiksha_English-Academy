package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzcm extends ContinuationImpl {
    Object zza;
    /* synthetic */ Object zzb;
    final /* synthetic */ zzco zzc;
    int zzd;
    zzco zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcm(zzco zzcoVar, Continuation continuation) {
        super(continuation);
        this.zzc = zzcoVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.zzb = obj;
        this.zzd |= Integer.MIN_VALUE;
        return this.zzc.zzm(null, null, 0L, null, null, null, null, null, this);
    }
}
