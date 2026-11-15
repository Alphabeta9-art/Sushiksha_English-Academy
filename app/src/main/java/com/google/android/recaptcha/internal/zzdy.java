package com.google.android.recaptcha.internal;

import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzdy extends SuspendLambda implements Function2 {
    final /* synthetic */ Exception zza;
    final /* synthetic */ zzek zzb;
    final /* synthetic */ zzea zzc;
    private /* synthetic */ Object zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdy(Exception exc, zzek zzekVar, zzea zzeaVar, Continuation continuation) {
        super(2, continuation);
        this.zza = exc;
        this.zzb = zzekVar;
        this.zzc = zzeaVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        zzdy zzdyVar = new zzdy(this.zza, this.zzb, this.zzc, continuation);
        zzdyVar.zzd = obj;
        return zzdyVar;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzdy) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        zzrh zzrhVarZza;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.zzd;
        Exception exc = this.zza;
        if (exc instanceof zzby) {
            zzrhVarZza = ((zzby) exc).zza();
            zzrhVarZza.zzd(this.zzb.zza());
        } else {
            zzek zzekVar = this.zzb;
            zzrh zzrhVarZzf = zzri.zzf();
            zzrhVarZzf.zzd(zzekVar.zza());
            zzrhVarZzf.zzq(2);
            zzrhVarZzf.zzp(2);
            zzrhVarZza = zzrhVarZzf;
        }
        zzri zzriVar = (zzri) zzrhVarZza.zzj();
        zzriVar.zzJ();
        zzriVar.zzk();
        Reflection.getOrCreateKotlinClass(this.zza.getClass()).getSimpleName();
        this.zza.getMessage();
        zzek zzekVar2 = this.zzb;
        zzbt zzbtVarZzb = zzekVar2.zzb();
        zzbt zzbtVar = zzekVar2.zza;
        if (zzbtVar == null) {
            zzbtVar = null;
        }
        zzoy zzoyVarZza = zzdn.zza(zzbtVarZzb, zzbtVar);
        String strZzd = this.zzb.zzd();
        if (strZzd.length() == 0) {
            strZzd = "recaptcha.m.Main.rge";
        }
        if (CoroutineScopeKt.isActive(coroutineScope)) {
            zzea zzeaVar = this.zzc;
            zzhz zzhzVarZzh = zzhz.zzh();
            byte[] bArrZzd = zzriVar.zzd();
            zzhz zzhzVarZzh2 = zzhz.zzh();
            byte[] bArrZzd2 = zzoyVarZza.zzd();
            zzeaVar.zzb.zzd().zzb(strZzd, (String[]) Arrays.copyOf(new String[]{zzhzVarZzh.zzi(bArrZzd, 0, bArrZzd.length), zzhzVarZzh2.zzi(bArrZzd2, 0, bArrZzd2.length)}, 2));
        }
        return Unit.INSTANCE;
    }
}
