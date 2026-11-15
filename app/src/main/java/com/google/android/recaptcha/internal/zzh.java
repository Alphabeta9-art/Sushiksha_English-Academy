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
import kotlinx.coroutines.Deferred;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzh extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzl zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ long zzd;
    private /* synthetic */ Object zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzh(zzl zzlVar, String str, long j, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzlVar;
        this.zzc = str;
        this.zzd = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        zzh zzhVar = new zzh(this.zzb, this.zzc, this.zzd, continuation);
        zzhVar.zze = obj;
        return zzhVar;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzh) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        zzdf zzdfVarZzf;
        Object objAwaitAll;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.zza) {
            case 0:
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.zze;
                zzl zzlVar = this.zzb;
                String str = this.zzc;
                zzdc zzdcVarZza = zzlVar.zza();
                zzdcVarZza.zzc(str);
                zzdfVarZzf = zzdcVarZza.zzf(31);
                ArrayList arrayList = new ArrayList();
                for (zze zzeVar : this.zzb.zzd()) {
                    if (zzeVar.zzl()) {
                        arrayList.add(BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new zzg(zzeVar, this.zzc, this.zzd, null), 3, null));
                    }
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
        String str2 = this.zzc;
        zzpv zzpvVarZzf = zzpw.zzf();
        zzpvVarZzf.zzd(str2);
        Iterator it = ((List) objAwaitAll).iterator();
        while (it.hasNext()) {
            Object value = ((Result) it.next()).getValue();
            if (Result.m9095isSuccessimpl(value)) {
                zzpvVarZzf.zzg((zzpw) value);
            }
        }
        zzpw zzpwVar = (zzpw) zzpvVarZzf.zzj();
        zzdfVarZzf.zza();
        return zzpwVar;
    }
}
