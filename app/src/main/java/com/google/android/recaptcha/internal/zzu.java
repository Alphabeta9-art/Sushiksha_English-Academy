package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Arrays;
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
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzu extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzx zzc;
    private /* synthetic */ Object zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzu(String str, zzx zzxVar, Continuation continuation) {
        super(2, continuation);
        this.zzb = str;
        this.zzc = zzxVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        zzu zzuVar = new zzu(this.zzb, this.zzc, continuation);
        zzuVar.zzd = obj;
        return zzuVar;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzu) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        switch (i) {
            case 0:
                CoroutineScope coroutineScope = (CoroutineScope) this.zzd;
                String str = this.zzb;
                zzqm zzqmVarZzf = zzqn.zzf();
                zzqmVarZzf.zze(str);
                zzx zzxVar = this.zzc;
                zzxVar.zzr().put(this.zzb, zzqmVarZzf);
                ArrayList arrayList = new ArrayList();
                List list = this.zzc.zzb;
                ArrayList arrayList2 = new ArrayList();
                for (Object obj2 : list) {
                    if (((zzaa) obj2).zzf()) {
                        arrayList2.add(obj2);
                    }
                }
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    arrayList.add(BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new zzt(this.zzc, this.zzb, (zzaa) it.next(), zzqmVarZzf, null), 3, null));
                    zzqmVarZzf = zzqmVarZzf;
                }
                Job[] jobArr = (Job[]) arrayList.toArray(new Job[0]);
                Job[] jobArr2 = (Job[]) Arrays.copyOf(jobArr, jobArr.length);
                this.zza = 1;
                if (AwaitKt.joinAll(jobArr2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
        }
        Result.Companion companion = Result.INSTANCE;
        return Result.m9087boximpl(Result.m9088constructorimpl(this.zzc.zzt(this.zzb)));
    }
}
