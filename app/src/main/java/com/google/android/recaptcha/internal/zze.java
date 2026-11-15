package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public abstract class zze {
    private boolean zza;

    protected zzdf zza(String str) {
        throw null;
    }

    protected zzdf zzb() {
        throw null;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x007d A[Catch: Exception -> 0x0085, TryCatch #2 {Exception -> 0x0085, blocks: (B:23:0x0070, B:25:0x007d, B:26:0x0080), top: B:49:0x0070 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:38:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:41:0x00d1 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:44:0x00d7 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object zzc(String str, long j, Continuation continuation) throws zzbj {
        zza zzaVar;
        String str2;
        long j2;
        zzdf zzdfVar;
        Exception exc;
        Object obj;
        Object obj2;
        String str3;
        zzbj zzbjVarZza;
        String str4;
        if (continuation instanceof zza) {
            zza zzaVar2 = (zza) continuation;
            int i = zzaVar2.zze;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzaVar2.zze = i - Integer.MIN_VALUE;
                zzaVar = zzaVar2;
            } else {
                zzaVar = new zza(this, continuation);
            }
        } else {
            zzaVar = new zza(this, continuation);
        }
        Object objZzd = zzaVar.zzc;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (zzaVar.zze) {
            case 0:
                ResultKt.throwOnFailure(objZzd);
                zzdf zzdfVarZza = zza(str);
                try {
                    zzb zzbVar = new zzb(this, str, null);
                    zzaVar.zza = this;
                    zzaVar.zzf = str;
                    zzaVar.zzg = zzdfVarZza;
                    zzaVar.zzb = j;
                    zzaVar.zze = 1;
                    Object objWithTimeout = TimeoutKt.withTimeout(j, zzbVar, zzaVar);
                    if (objWithTimeout == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj2 = this;
                    str3 = str;
                    zzdfVar = zzdfVarZza;
                    objZzd = objWithTimeout;
                    try {
                        Object value = ((Result) objZzd).getValue();
                        ResultKt.throwOnFailure(value);
                        zzpw zzpwVar = (zzpw) value;
                        if (zzdfVar != null) {
                            zzdfVar.zza();
                        }
                        return Result.m9088constructorimpl(zzpwVar);
                    } catch (Exception e) {
                        j2 = j;
                        exc = e;
                        obj = obj2;
                        str2 = str3;
                        zzbjVarZza = zzf.zza(exc, new zzbj(zzbh.zzb, zzbg.zzaq, exc.getClass().getSimpleName()));
                        if (zzdfVar != null) {
                            zzdfVar.zzb(zzbjVarZza);
                        }
                        zzaVar.zza = obj;
                        zzaVar.zzf = str2;
                        zzaVar.zzg = null;
                        zzaVar.zze = 2;
                        if (((zze) obj).zzi(str2, j2, exc, zzaVar) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        str4 = str2;
                        Result.Companion companion = Result.INSTANCE;
                        zzaVar.zza = null;
                        zzaVar.zzf = null;
                        zzaVar.zze = 3;
                        objZzd = ((zze) obj).zzd(str4, zzaVar);
                        if (objZzd == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Result.m9088constructorimpl(objZzd);
                    }
                } catch (Exception e2) {
                    str2 = str;
                    j2 = j;
                    zzdfVar = zzdfVarZza;
                    exc = e2;
                    obj = this;
                    zzbjVarZza = zzf.zza(exc, new zzbj(zzbh.zzb, zzbg.zzaq, exc.getClass().getSimpleName()));
                    if (zzdfVar != null) {
                        zzdfVar.zzb(zzbjVarZza);
                    }
                    zzaVar.zza = obj;
                    zzaVar.zzf = str2;
                    zzaVar.zzg = null;
                    zzaVar.zze = 2;
                    if (((zze) obj).zzi(str2, j2, exc, zzaVar) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    str4 = str2;
                    Result.Companion companion2 = Result.INSTANCE;
                    zzaVar.zza = null;
                    zzaVar.zzf = null;
                    zzaVar.zze = 3;
                    objZzd = ((zze) obj).zzd(str4, zzaVar);
                    if (objZzd == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Result.m9088constructorimpl(objZzd);
                }
            case 1:
                j = zzaVar.zzb;
                zzdfVar = zzaVar.zzg;
                str3 = zzaVar.zzf;
                obj2 = zzaVar.zza;
                try {
                    ResultKt.throwOnFailure(objZzd);
                    Object value2 = ((Result) objZzd).getValue();
                    ResultKt.throwOnFailure(value2);
                    zzpw zzpwVar2 = (zzpw) value2;
                    if (zzdfVar != null) {
                        zzdfVar.zza();
                    }
                    return Result.m9088constructorimpl(zzpwVar2);
                } catch (Exception e3) {
                    j2 = j;
                    exc = e3;
                    obj = obj2;
                    str2 = str3;
                    zzbjVarZza = zzf.zza(exc, new zzbj(zzbh.zzb, zzbg.zzaq, exc.getClass().getSimpleName()));
                    if (zzdfVar != null) {
                        zzdfVar.zzb(zzbjVarZza);
                    }
                    zzaVar.zza = obj;
                    zzaVar.zzf = str2;
                    zzaVar.zzg = null;
                    zzaVar.zze = 2;
                    if (((zze) obj).zzi(str2, j2, exc, zzaVar) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    str4 = str2;
                    Result.Companion companion3 = Result.INSTANCE;
                    zzaVar.zza = null;
                    zzaVar.zzf = null;
                    zzaVar.zze = 3;
                    objZzd = ((zze) obj).zzd(str4, zzaVar);
                    if (objZzd == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Result.m9088constructorimpl(objZzd);
                }
            case 2:
                str4 = zzaVar.zzf;
                obj = zzaVar.zza;
                ResultKt.throwOnFailure(objZzd);
                Result.Companion companion4 = Result.INSTANCE;
                zzaVar.zza = null;
                zzaVar.zzf = null;
                zzaVar.zze = 3;
                objZzd = ((zze) obj).zzd(str4, zzaVar);
                if (objZzd == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Result.m9088constructorimpl(objZzd);
            case 3:
                ResultKt.throwOnFailure(objZzd);
                return Result.m9088constructorimpl(objZzd);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    protected abstract Object zzd(String str, Continuation continuation);

    /* JADX WARN: Code duplicated, block: B:26:0x0085 A[Catch: Exception -> 0x008d, TryCatch #1 {Exception -> 0x008d, blocks: (B:15:0x0046, B:24:0x0076, B:26:0x0085, B:27:0x0088), top: B:46:0x0046 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x00a4 A[PHI: r6 r7 r9
  0x00a4: PHI (r6v13 com.google.android.recaptcha.internal.zzdf) = (r6v8 com.google.android.recaptcha.internal.zzdf), (r6v17 com.google.android.recaptcha.internal.zzdf) binds: [B:34:0x00a2, B:13:0x0036] A[DONT_GENERATE, DONT_INLINE]
  0x00a4: PHI (r7v8 com.google.android.recaptcha.internal.zze) = (r7v3 com.google.android.recaptcha.internal.zze), (r7v10 com.google.android.recaptcha.internal.zze) binds: [B:34:0x00a2, B:13:0x0036] A[DONT_GENERATE, DONT_INLINE]
  0x00a4: PHI (r9v9 java.lang.Object) = (r9v6 java.lang.Object), (r9v1 java.lang.Object) binds: [B:34:0x00a2, B:13:0x0036] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:37:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:40:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object zze(long j, zzpq zzpqVar, Continuation continuation) throws zzbj {
        zzc zzcVar;
        Exception e;
        zze zzeVar;
        zzdf zzdfVar;
        zzbj zzbjVar;
        zzbj zzbjVar2;
        if (continuation instanceof zzc) {
            zzcVar = (zzc) continuation;
            int i = zzcVar.zzd;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzcVar.zzd = i - Integer.MIN_VALUE;
            } else {
                zzcVar = new zzc(this, continuation);
            }
        } else {
            zzcVar = new zzc(this, continuation);
        }
        Object objZzj = zzcVar.zzb;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (zzcVar.zzd) {
            case 0:
                ResultKt.throwOnFailure(objZzj);
                zzdf zzdfVarZzb = zzb();
                if (this.zza) {
                    zzdfVarZzb.zza();
                    Result.Companion companion = Result.INSTANCE;
                    return Result.m9088constructorimpl(Unit.INSTANCE);
                }
                try {
                    zzd zzdVar = new zzd(this, zzpqVar, null);
                    zzcVar.zza = this;
                    zzcVar.zze = zzdfVarZzb;
                    zzcVar.zzd = 1;
                    Object objWithTimeout = TimeoutKt.withTimeout(j, zzdVar, zzcVar);
                    if (objWithTimeout != coroutine_suspended) {
                        objZzj = objWithTimeout;
                        zzdfVar = zzdfVarZzb;
                        zzeVar = this;
                        ResultKt.throwOnFailure(((Result) objZzj).getValue());
                        Unit unit = Unit.INSTANCE;
                        zzeVar.zza = true;
                        if (zzdfVar != null) {
                            zzdfVar.zza();
                        }
                        return Result.m9088constructorimpl(unit);
                    }
                } catch (Exception e2) {
                    e = e2;
                    zzeVar = this;
                    zzdfVar = zzdfVarZzb;
                    zzeVar.zza = false;
                    zzcVar.zza = zzeVar;
                    zzcVar.zze = zzdfVar;
                    zzcVar.zzd = 2;
                    objZzj = zzeVar.zzj(e, zzcVar);
                    if (objZzj != coroutine_suspended) {
                        zzbjVar = (zzbj) objZzj;
                        if (zzdfVar != null) {
                            zzdfVar.zzb(zzbjVar);
                        }
                        zzcVar.zza = zzbjVar;
                        zzcVar.zze = null;
                        zzcVar.zzd = 3;
                        if (zzeVar.zzg(zzbjVar, zzcVar) != coroutine_suspended) {
                            zzbjVar2 = zzbjVar;
                            Result.Companion companion2 = Result.INSTANCE;
                            return Result.m9088constructorimpl(ResultKt.createFailure(zzbjVar2));
                        }
                    }
                }
                return coroutine_suspended;
            case 1:
                zzdfVar = zzcVar.zze;
                zzeVar = (zze) zzcVar.zza;
                try {
                    ResultKt.throwOnFailure(objZzj);
                    ResultKt.throwOnFailure(((Result) objZzj).getValue());
                    Unit unit2 = Unit.INSTANCE;
                    zzeVar.zza = true;
                    if (zzdfVar != null) {
                        zzdfVar.zza();
                    }
                    return Result.m9088constructorimpl(unit2);
                } catch (Exception e3) {
                    e = e3;
                    zzeVar.zza = false;
                    zzcVar.zza = zzeVar;
                    zzcVar.zze = zzdfVar;
                    zzcVar.zzd = 2;
                    objZzj = zzeVar.zzj(e, zzcVar);
                    if (objZzj != coroutine_suspended) {
                        zzbjVar = (zzbj) objZzj;
                        if (zzdfVar != null) {
                            zzdfVar.zzb(zzbjVar);
                        }
                        zzcVar.zza = zzbjVar;
                        zzcVar.zze = null;
                        zzcVar.zzd = 3;
                        if (zzeVar.zzg(zzbjVar, zzcVar) != coroutine_suspended) {
                            zzbjVar2 = zzbjVar;
                            Result.Companion companion3 = Result.INSTANCE;
                            return Result.m9088constructorimpl(ResultKt.createFailure(zzbjVar2));
                        }
                    }
                    return coroutine_suspended;
                }
            case 2:
                zzdfVar = zzcVar.zze;
                zzeVar = (zze) zzcVar.zza;
                ResultKt.throwOnFailure(objZzj);
                zzbjVar = (zzbj) objZzj;
                if (zzdfVar != null) {
                    zzdfVar.zzb(zzbjVar);
                }
                zzcVar.zza = zzbjVar;
                zzcVar.zze = null;
                zzcVar.zzd = 3;
                if (zzeVar.zzg(zzbjVar, zzcVar) != coroutine_suspended) {
                    zzbjVar2 = zzbjVar;
                    Result.Companion companion4 = Result.INSTANCE;
                    return Result.m9088constructorimpl(ResultKt.createFailure(zzbjVar2));
                }
                return coroutine_suspended;
            case 3:
                zzbjVar2 = (zzbj) zzcVar.zza;
                ResultKt.throwOnFailure(objZzj);
                Result.Companion companion5 = Result.INSTANCE;
                return Result.m9088constructorimpl(ResultKt.createFailure(zzbjVar2));
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    protected abstract Object zzf(String str, Continuation continuation) throws zzbj;

    protected Object zzg(zzbj zzbjVar, Continuation continuation) {
        return Unit.INSTANCE;
    }

    protected abstract Object zzh(zzpq zzpqVar, Continuation continuation) throws zzbj;

    protected Object zzi(String str, long j, Exception exc, Continuation continuation) {
        return Unit.INSTANCE;
    }

    protected Object zzj(Exception exc, Continuation continuation) {
        return zzf.zza(exc, new zzbj(zzbh.zzb, zzbg.zzaq, exc.getClass().getSimpleName()));
    }

    protected void zzk(zzqf zzqfVar) {
    }

    public final boolean zzl() {
        return this.zza;
    }
}
