package com.google.android.recaptcha.internal;

import android.content.ContentValues;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzdj extends SuspendLambda implements Function2 {
    final /* synthetic */ zzdk zza;
    final /* synthetic */ zzre zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdj(zzdk zzdkVar, zzre zzreVar, Continuation continuation) {
        super(2, continuation);
        this.zza = zzdkVar;
        this.zzb = zzreVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zzdj(this.zza, this.zzb, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzdj) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        zzre zzreVar = this.zzb;
        zzdk zzdkVar = this.zza;
        synchronized (zzdg.class) {
            if (zzdkVar.zze != null) {
                byte[] bArrZzd = zzreVar.zzd();
                zzdb zzdbVar = new zzdb(zzhz.zzg().zzi(bArrZzd, 0, bArrZzd.length), System.currentTimeMillis(), 0);
                zzda zzdaVar = zzdkVar.zze;
                ContentValues contentValues = new ContentValues();
                contentValues.put("ss", zzdbVar.zzc());
                contentValues.put("ts", Long.valueOf(zzdbVar.zzb()));
                zzdaVar.getWritableDatabase().insert("ce", null, contentValues);
                int iZzb = zzdkVar.zze.zzb() - 500;
                if (iZzb > 0) {
                    zzdkVar.zze.zza(CollectionsKt.take(zzdkVar.zze.zzd(), iZzb));
                }
                if (zzdkVar.zze.zzb() >= 20) {
                    zzdkVar.zzg();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
