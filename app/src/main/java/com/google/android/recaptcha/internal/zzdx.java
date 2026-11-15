package com.google.android.recaptcha.internal;

import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzdx extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzek zzb;
    final /* synthetic */ List zzc;
    final /* synthetic */ zzea zzd;
    private /* synthetic */ Object zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdx(zzek zzekVar, List list, zzea zzeaVar, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzekVar;
        this.zzc = list;
        this.zzd = zzeaVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        zzdx zzdxVar = new zzdx(this.zzb, this.zzc, this.zzd, continuation);
        zzdxVar.zze = obj;
        return zzdxVar;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzdx) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        switch (i) {
            case 0:
                CoroutineScope coroutineScope = (CoroutineScope) this.zze;
                while (true) {
                    zzek zzekVar = this.zzb;
                    if (zzekVar.zza() >= 0) {
                        if (zzekVar.zza() < this.zzc.size() && CoroutineScopeKt.isActive(coroutineScope)) {
                            zzrs zzrsVar = (zzrs) this.zzc.get(this.zzb.zza());
                            try {
                                zzea.zzf(this.zzd, zzrsVar, this.zzb);
                            } catch (Exception e) {
                                zzrsVar.zzk();
                                Boxing.boxInt(zzrsVar.zzg());
                                CollectionsKt.joinToString$default(zzrsVar.zzj(), null, null, null, 0, null, new zzdw(this.zzd), 31, null);
                                zzea zzeaVar = this.zzd;
                                zzek zzekVar2 = this.zzb;
                                this.zza = 1;
                                if (zzeaVar.zzh(e, zzekVar2, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                        }
                    }
                }
                return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
