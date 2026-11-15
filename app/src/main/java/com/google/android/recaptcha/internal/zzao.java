package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzao extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzap zzc;
    final /* synthetic */ zzdf zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzao(zzap zzapVar, zzdf zzdfVar, Continuation continuation) {
        super(2, continuation);
        this.zzc = zzapVar;
        this.zzd = zzdfVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zzao(this.zzc, this.zzd, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzao) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0045  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.lang.Object, kotlin.jvm.internal.Ref$ObjectRef] */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v4 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ?? objectRef;
        Throwable th;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.zzb) {
            case 0:
                ResultKt.throwOnFailure(obj);
                objectRef = new Ref.ObjectRef();
                try {
                    zzan zzanVar = new zzan(this.zzc, this.zzd, objectRef, null);
                    this.zza = objectRef;
                    this.zzb = 1;
                    if (TimeoutKt.withTimeout(60000L, zzanVar, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } catch (Exception e) {
                    e = e;
                    CompletableDeferred completableDeferredZzf = this.zzc.zzf();
                    th = (Throwable) ((Ref.ObjectRef) objectRef).element;
                    if (th == null) {
                        th = e;
                    }
                    completableDeferredZzf.completeExceptionally(th);
                    this.zzc.zze = zzat.zza;
                    this.zzd.zzb(new zzbj(zzbh.zzb, zzbg.zza, e.getMessage()));
                }
                return Unit.INSTANCE;
            default:
                Object obj2 = this.zza;
                try {
                    ResultKt.throwOnFailure(obj);
                    break;
                } catch (Exception e2) {
                    e = e2;
                    objectRef = obj2;
                    CompletableDeferred completableDeferredZzf2 = this.zzc.zzf();
                    th = (Throwable) ((Ref.ObjectRef) objectRef).element;
                    if (th == null) {
                        th = e;
                    }
                    completableDeferredZzf2.completeExceptionally(th);
                    this.zzc.zze = zzat.zza;
                    this.zzd.zzb(new zzbj(zzbh.zzb, zzbg.zza, e.getMessage()));
                }
                return Unit.INSTANCE;
        }
    }
}
