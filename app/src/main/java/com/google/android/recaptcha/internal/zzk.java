package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzk extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzl zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ zzpq zzd;
    private /* synthetic */ Object zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzk(zzl zzlVar, long j, zzpq zzpqVar, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzlVar;
        this.zzc = j;
        this.zzd = zzpqVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        zzk zzkVar = new zzk(this.zzb, this.zzc, this.zzd, continuation);
        zzkVar.zze = obj;
        return zzkVar;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzk) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        zzdf zzdfVarZzf;
        Object objAwaitAll;
        Object objM9088constructorimpl;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.zza) {
            case 0:
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.zze;
                zzl zzlVar = this.zzb;
                zzdc zzdcVarZza = zzlVar.zza();
                zzdcVarZza.zzc(zzlVar.zza().zzd());
                zzdfVarZzf = zzdcVarZza.zzf(30);
                ArrayList arrayList = new ArrayList();
                Iterator it = this.zzb.zzd().iterator();
                while (it.hasNext()) {
                    arrayList.add(BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new zzj((zze) it.next(), this.zzc, this.zzd, null), 3, null));
                }
                Deferred[] deferredArr = (Deferred[]) arrayList.toArray(new Deferred[0]);
                Deferred[] deferredArr2 = (Deferred[]) Arrays.copyOf(deferredArr, deferredArr.length);
                this.zze = zzdfVarZzf;
                this.zza = 1;
                objAwaitAll = AwaitKt.awaitAll(deferredArr2, this);
                if (objAwaitAll == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            default:
                zzdfVarZzf = (zzdf) this.zze;
                ResultKt.throwOnFailure(obj);
                objAwaitAll = obj;
                break;
        }
        List list = (List) objAwaitAll;
        if ((list instanceof Collection) && list.isEmpty()) {
            zzbj zzbjVar = new zzbj(zzbh.zzb, zzbg.zzY, null);
            zzdfVarZzf.zzb(zzbjVar);
            Result.Companion companion = Result.INSTANCE;
            objM9088constructorimpl = Result.m9088constructorimpl(ResultKt.createFailure(zzbjVar));
        } else {
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                if (!Result.m9094isFailureimpl(((Result) it2.next()).getValue())) {
                    zzdfVarZzf.zza();
                    objM9088constructorimpl = Result.m9088constructorimpl(Unit.INSTANCE);
                }
            }
            zzbj zzbjVar2 = new zzbj(zzbh.zzb, zzbg.zzY, null);
            zzdfVarZzf.zzb(zzbjVar2);
            Result.Companion companion2 = Result.INSTANCE;
            objM9088constructorimpl = Result.m9088constructorimpl(ResultKt.createFailure(zzbjVar2));
        }
        return Result.m9087boximpl(objM9088constructorimpl);
    }
}
