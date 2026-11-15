package com.google.android.recaptcha.internal;

import com.google.android.play.core.integrity.StandardIntegrityException;
import com.google.android.play.core.integrity.model.StandardIntegrityErrorCode;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzae extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzag zzc;
    final /* synthetic */ String zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzae(zzag zzagVar, String str, Continuation continuation) {
        super(2, continuation);
        this.zzc = zzagVar;
        this.zzd = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zzae(this.zzc, this.zzd, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzae) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0081  */
    /* JADX WARN: Code duplicated, block: B:22:0x008b  */
    /* JADX WARN: Code duplicated, block: B:23:0x008e  */
    /* JADX WARN: Code duplicated, block: B:24:0x0091  */
    /* JADX WARN: Code duplicated, block: B:25:0x0094  */
    /* JADX WARN: Code duplicated, block: B:26:0x0097  */
    /* JADX WARN: Code duplicated, block: B:27:0x009a  */
    /* JADX WARN: Code duplicated, block: B:28:0x009d  */
    /* JADX WARN: Code duplicated, block: B:29:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:30:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:31:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:32:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:33:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:34:0x00af  */
    /* JADX WARN: Code duplicated, block: B:35:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:36:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:37:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:38:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:41:0x00cf A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        zzdf zzdfVarZzb;
        zzaa zzaaVar;
        int i;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.zzb) {
            case 0:
                ResultKt.throwOnFailure(obj);
                zzdfVarZzb = zzab.zzb(this.zzc, this.zzd);
                try {
                    zzag zzagVar = this.zzc;
                    zzap zzapVar = zzagVar.zzb;
                    String str = zzagVar.zzd;
                    this.zza = zzdfVarZzb;
                    this.zzb = 1;
                    obj = zzapVar.zzc(str, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    zzdfVarZzb.zza();
                    zzag zzagVar2 = this.zzc;
                    zzrj zzrjVarZzf = zzrm.zzf();
                    zzrk zzrkVarZzf = zzrl.zzf();
                    zzrkVarZzf.zzv((String) obj);
                    zzrjVarZzf.zze((zzrl) zzrkVarZzf.zzj());
                    return zzab.zza(zzagVar2, (zzrm) zzrjVarZzf.zzj());
                } catch (Exception e) {
                    e = e;
                    zzdfVarZzb.zzb(new zzbj(zzbh.zzb, zzbg.zzab, e.getMessage()));
                    zzaaVar = this.zzc;
                    this.zza = zzaaVar;
                    i = 2;
                    this.zzb = 2;
                    if (e instanceof StandardIntegrityException) {
                        switch (((StandardIntegrityException) e).getErrorCode()) {
                            case -100:
                                i = 44;
                                break;
                            case StandardIntegrityErrorCode.INTEGRITY_TOKEN_PROVIDER_INVALID /* -19 */:
                                i = 54;
                                break;
                            case StandardIntegrityErrorCode.CLIENT_TRANSIENT_ERROR /* -18 */:
                                i = 53;
                                break;
                            case -17:
                                i = 52;
                                break;
                            case -16:
                                i = 43;
                                break;
                            case -15:
                                i = 42;
                                break;
                            case -14:
                                i = 41;
                                break;
                            case -12:
                                i = 39;
                                break;
                            case -9:
                                i = 36;
                                break;
                            case -8:
                                i = 35;
                                break;
                            case -7:
                                i = 34;
                                break;
                            case -6:
                                i = 33;
                                break;
                            case -5:
                                i = 32;
                                break;
                            case -3:
                                i = 30;
                                break;
                            case -2:
                                i = 29;
                                break;
                            case -1:
                                i = 28;
                                break;
                        }
                    } else {
                        i = 45;
                    }
                    zzrh zzrhVarZzf = zzri.zzf();
                    zzrhVarZzf.zzp(i);
                    zzrhVarZzf.zzq(15);
                    obj = zzrhVarZzf.zzj();
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    int iZza = zzaaVar.zza();
                    zzrh zzrhVar = (zzrh) ((zzri) obj).zzq();
                    zzrhVar.zze(zzaaVar.zza());
                    return new zzy(iZza, (zzri) zzrhVar.zzj());
                }
            case 1:
                zzdfVarZzb = (zzdf) this.zza;
                try {
                    ResultKt.throwOnFailure(obj);
                    zzdfVarZzb.zza();
                    zzag zzagVar3 = this.zzc;
                    zzrj zzrjVarZzf2 = zzrm.zzf();
                    zzrk zzrkVarZzf2 = zzrl.zzf();
                    zzrkVarZzf2.zzv((String) obj);
                    zzrjVarZzf2.zze((zzrl) zzrkVarZzf2.zzj());
                    return zzab.zza(zzagVar3, (zzrm) zzrjVarZzf2.zzj());
                } catch (Exception e2) {
                    e = e2;
                    zzdfVarZzb.zzb(new zzbj(zzbh.zzb, zzbg.zzab, e.getMessage()));
                    zzaaVar = this.zzc;
                    this.zza = zzaaVar;
                    i = 2;
                    this.zzb = 2;
                    if (e instanceof StandardIntegrityException) {
                        switch (((StandardIntegrityException) e).getErrorCode()) {
                            case -100:
                                i = 44;
                                break;
                            case StandardIntegrityErrorCode.INTEGRITY_TOKEN_PROVIDER_INVALID /* -19 */:
                                i = 54;
                                break;
                            case StandardIntegrityErrorCode.CLIENT_TRANSIENT_ERROR /* -18 */:
                                i = 53;
                                break;
                            case -17:
                                i = 52;
                                break;
                            case -16:
                                i = 43;
                                break;
                            case -15:
                                i = 42;
                                break;
                            case -14:
                                i = 41;
                                break;
                            case -12:
                                i = 39;
                                break;
                            case -9:
                                i = 36;
                                break;
                            case -8:
                                i = 35;
                                break;
                            case -7:
                                i = 34;
                                break;
                            case -6:
                                i = 33;
                                break;
                            case -5:
                                i = 32;
                                break;
                            case -3:
                                i = 30;
                                break;
                            case -2:
                                i = 29;
                                break;
                            case -1:
                                i = 28;
                                break;
                        }
                    } else {
                        i = 45;
                    }
                    zzrh zzrhVarZzf2 = zzri.zzf();
                    zzrhVarZzf2.zzp(i);
                    zzrhVarZzf2.zzq(15);
                    obj = zzrhVarZzf2.zzj();
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    int iZza2 = zzaaVar.zza();
                    zzrh zzrhVar2 = (zzrh) ((zzri) obj).zzq();
                    zzrhVar2.zze(zzaaVar.zza());
                    return new zzy(iZza2, (zzri) zzrhVar2.zzj());
                }
            default:
                zzaaVar = (zzaa) this.zza;
                ResultKt.throwOnFailure(obj);
                int iZza3 = zzaaVar.zza();
                zzrh zzrhVar3 = (zzrh) ((zzri) obj).zzq();
                zzrhVar3.zze(zzaaVar.zza());
                return new zzy(iZza3, (zzri) zzrhVar3.zzj());
        }
    }
}
