package com.google.android.recaptcha.internal;

import android.app.Application;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.android.recaptcha.RecaptchaClient;
import com.google.android.recaptcha.RecaptchaTasksClient;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzcx implements RecaptchaClient, RecaptchaTasksClient {
    private static final Regex zza = new Regex("^[a-zA-Z0-9/_]{0,100}$");
    private final Application zzb;
    private final zzl zzc;
    private final String zzd;
    private final zzbv zze;
    private final zzpq zzf;
    private final zzdc zzg;
    private final zzbl zzh;
    private final zzbo zzi;

    public zzcx(Application application, zzl zzlVar, String str, zzbo zzboVar, zzbv zzbvVar, zzpq zzpqVar, zzdc zzdcVar, zzbl zzblVar, zzds zzdsVar) {
        this.zzb = application;
        this.zzc = zzlVar;
        this.zzd = str;
        this.zzi = zzboVar;
        this.zze = zzbvVar;
        this.zzf = zzpqVar;
        this.zzg = zzdcVar;
        this.zzh = zzblVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:28:0x0058  */
    /* JADX WARN: Code duplicated, block: B:29:0x005b  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object zzf(long j, String str, zzdc zzdcVar, Continuation continuation) throws zzbj {
        zzcp zzcpVar;
        Exception e;
        zzdf zzdfVar;
        zzbj zzbjVar;
        if (continuation instanceof zzcp) {
            zzcpVar = (zzcp) continuation;
            int i = zzcpVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzcpVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzcpVar = new zzcp(this, continuation);
            }
        } else {
            zzcpVar = new zzcp(this, continuation);
        }
        Object objZzb = zzcpVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (zzcpVar.zzc) {
            case 0:
                ResultKt.throwOnFailure(objZzb);
                zzdf zzdfVarZzf = zzdcVar.zzf(27);
                try {
                    zzl zzlVar = this.zzc;
                    zzcpVar.zzd = zzdfVarZzf;
                    zzcpVar.zzc = 1;
                    objZzb = zzlVar.zzb(str, j, zzcpVar);
                    if (objZzb == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    zzdfVar = zzdfVarZzf;
                    zzpw zzpwVar = (zzpw) objZzb;
                    zzdfVar.zza();
                    return zzpwVar;
                } catch (Exception e2) {
                    e = e2;
                    zzdfVar = zzdfVarZzf;
                    if (e instanceof zzbj) {
                        zzbjVar = (zzbj) e;
                    } else {
                        zzbjVar = new zzbj(zzbh.zzb, zzbg.zzaa, null);
                    }
                    zzdfVar.zzb(zzbjVar);
                    throw zzbjVar;
                }
            case 1:
                zzdfVar = zzcpVar.zzd;
                try {
                    ResultKt.throwOnFailure(objZzb);
                    zzpw zzpwVar2 = (zzpw) objZzb;
                    zzdfVar.zza();
                    return zzpwVar2;
                } catch (Exception e3) {
                    e = e3;
                    if (e instanceof zzbj) {
                        zzbjVar = (zzbj) e;
                    } else {
                        zzbjVar = new zzbj(zzbh.zzb, zzbg.zzaa, null);
                    }
                    zzdfVar.zzb(zzbjVar);
                    throw zzbjVar;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:38:0x0102  */
    /* JADX WARN: Code duplicated, block: B:55:0x0131  */
    /* JADX WARN: Code duplicated, block: B:57:0x013f  */
    /* JADX WARN: Code duplicated, block: B:60:0x0151  */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    public final Object zzg(RecaptchaAction recaptchaAction, long j, Continuation continuation) {
        zzcs zzcsVar;
        zzcx zzcxVar;
        zzdf zzdfVar;
        zzcx zzcxVar2;
        RecaptchaAction recaptchaAction2;
        zzdf zzdfVar2;
        double d;
        zzdc zzdcVar;
        zzcx zzcxVar3;
        zzdc zzdcVar2;
        zzbj zzbjVar;
        if (continuation instanceof zzcs) {
            zzcs zzcsVar2 = (zzcs) continuation;
            int i = zzcsVar2.zzf;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzcsVar2.zzf = i - Integer.MIN_VALUE;
                zzcsVar = zzcsVar2;
            } else {
                zzcsVar = new zzcs(this, continuation);
            }
        } else {
            zzcsVar = new zzcs(this, continuation);
        }
        Object objWithContext = zzcsVar.zzd;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (zzcsVar.zzf) {
            case 0:
                ResultKt.throwOnFailure(objWithContext);
                String string = UUID.randomUUID().toString();
                zzdc zzdcVarZza = this.zzg.zza();
                zzdcVarZza.zzc(string);
                zzdf zzdfVarZzf = zzdcVarZza.zzf(9);
                double d2 = j;
                try {
                    zzdf zzdfVarZzf2 = zzdcVarZza.zzf(7);
                    zzbj zzbjVar2 = !zza.matches(recaptchaAction.getAction()) ? new zzbj(zzbh.zzg, zzbg.zzh, null) : null;
                    if (j < 5000) {
                        zzbjVar2 = new zzbj(zzbh.zzb, zzbg.zzI, null);
                    }
                    if (zzbjVar2 != null) {
                        zzdfVarZzf2.zzb(zzbjVar2);
                        throw zzbjVar2;
                    }
                    zzdfVarZzf2.zza();
                    double d3 = 0.45d * d2;
                    zzcsVar.zzg = this;
                    zzcsVar.zza = recaptchaAction;
                    zzcsVar.zzb = zzdcVarZza;
                    zzcsVar.zzh = zzdfVarZzf;
                    double d4 = d2 * 0.55d;
                    zzcsVar.zzc = d4;
                    zzcsVar.zzf = 1;
                    Object objZzf = zzf((long) d3, string, zzdcVarZza, zzcsVar);
                    if (objZzf != coroutine_suspended) {
                        zzcxVar2 = this;
                        recaptchaAction2 = recaptchaAction;
                        objWithContext = objZzf;
                        zzdfVar2 = zzdfVarZzf;
                        d = d4;
                        zzdcVar = zzdcVarZza;
                        try {
                            zzpw zzpwVar = (zzpw) objWithContext;
                            long j2 = (long) d;
                            zzcsVar.zzg = zzcxVar2;
                            zzcsVar.zza = zzdcVar;
                            zzcsVar.zzb = zzdfVar2;
                            zzcsVar.zzh = null;
                            zzcsVar.zzf = 2;
                            zzcxVar3 = zzcxVar2;
                            try {
                                objWithContext = BuildersKt.withContext(zzcxVar2.zzi.zza().getCoroutineContext(), new zzcv(j2, zzdcVar, zzcxVar3, recaptchaAction2, zzpwVar, null), zzcsVar);
                                if (objWithContext != coroutine_suspended) {
                                    zzdfVar = zzdfVar2;
                                    zzdcVar2 = zzdcVar;
                                    zzcxVar = zzcxVar3;
                                    try {
                                        zzqf zzqfVar = (zzqf) objWithContext;
                                        zzcxVar.zzh(zzqfVar, zzdcVar2);
                                        zzdfVar.zza();
                                        Result.Companion companion = Result.INSTANCE;
                                        return Result.m9088constructorimpl(zzqfVar.zzj());
                                    } catch (Exception e) {
                                        e = e;
                                        if (e instanceof zzbj) {
                                            zzbjVar = (zzbj) e;
                                            if (Intrinsics.areEqual(zzbjVar.zzb(), zzbh.zzc)) {
                                                zzbjVar = new zzbj(zzbh.zzc, zzbg.zzap, null);
                                            }
                                        } else {
                                            zzbjVar = new zzbj(zzbh.zzb, zzbg.zzX, e.getClass().getSimpleName());
                                        }
                                        zzdfVar.zzb(zzbjVar);
                                        Result.Companion companion2 = Result.INSTANCE;
                                        return Result.m9088constructorimpl(ResultKt.createFailure(zzbjVar.zzc()));
                                    }
                                }
                            } catch (Exception e2) {
                                e = e2;
                                zzdfVar = zzdfVar2;
                                zzcxVar = zzcxVar3;
                                if (e instanceof zzbj) {
                                    zzbjVar = (zzbj) e;
                                    if (Intrinsics.areEqual(zzbjVar.zzb(), zzbh.zzc)) {
                                        zzbjVar = new zzbj(zzbh.zzc, zzbg.zzap, null);
                                    }
                                } else {
                                    zzbjVar = new zzbj(zzbh.zzb, zzbg.zzX, e.getClass().getSimpleName());
                                }
                                zzdfVar.zzb(zzbjVar);
                                Result.Companion companion3 = Result.INSTANCE;
                                return Result.m9088constructorimpl(ResultKt.createFailure(zzbjVar.zzc()));
                            }
                        } catch (Exception e3) {
                            e = e3;
                            zzcxVar3 = zzcxVar2;
                        }
                    }
                    return coroutine_suspended;
                } catch (Exception e4) {
                    e = e4;
                    zzcxVar = this;
                    zzdfVar = zzdfVarZzf;
                    if (e instanceof zzbj) {
                        zzbjVar = (zzbj) e;
                        if (Intrinsics.areEqual(zzbjVar.zzb(), zzbh.zzc)) {
                            zzbjVar = new zzbj(zzbh.zzc, zzbg.zzap, null);
                        }
                    } else {
                        zzbjVar = new zzbj(zzbh.zzb, zzbg.zzX, e.getClass().getSimpleName());
                    }
                    zzdfVar.zzb(zzbjVar);
                    Result.Companion companion4 = Result.INSTANCE;
                    return Result.m9088constructorimpl(ResultKt.createFailure(zzbjVar.zzc()));
                }
            case 1:
                d = zzcsVar.zzc;
                zzdfVar2 = zzcsVar.zzh;
                zzdc zzdcVar3 = (zzdc) zzcsVar.zzb;
                RecaptchaAction recaptchaAction3 = (RecaptchaAction) zzcsVar.zza;
                zzcx zzcxVar4 = zzcsVar.zzg;
                try {
                    ResultKt.throwOnFailure(objWithContext);
                    recaptchaAction2 = recaptchaAction3;
                    zzcxVar2 = zzcxVar4;
                    zzdcVar = zzdcVar3;
                    zzpw zzpwVar2 = (zzpw) objWithContext;
                    long j3 = (long) d;
                    zzcsVar.zzg = zzcxVar2;
                    zzcsVar.zza = zzdcVar;
                    zzcsVar.zzb = zzdfVar2;
                    zzcsVar.zzh = null;
                    zzcsVar.zzf = 2;
                    zzcxVar3 = zzcxVar2;
                    objWithContext = BuildersKt.withContext(zzcxVar2.zzi.zza().getCoroutineContext(), new zzcv(j3, zzdcVar, zzcxVar3, recaptchaAction2, zzpwVar2, null), zzcsVar);
                    if (objWithContext != coroutine_suspended) {
                        zzdfVar = zzdfVar2;
                        zzdcVar2 = zzdcVar;
                        zzcxVar = zzcxVar3;
                        zzqf zzqfVar2 = (zzqf) objWithContext;
                        zzcxVar.zzh(zzqfVar2, zzdcVar2);
                        zzdfVar.zza();
                        Result.Companion companion5 = Result.INSTANCE;
                        return Result.m9088constructorimpl(zzqfVar2.zzj());
                    }
                    return coroutine_suspended;
                } catch (Exception e5) {
                    e = e5;
                    zzdfVar = zzdfVar2;
                    zzcxVar = zzcxVar4;
                    if (e instanceof zzbj) {
                        zzbjVar = (zzbj) e;
                        if (Intrinsics.areEqual(zzbjVar.zzb(), zzbh.zzc) && !zzcxVar.zzi(zzcxVar.zzb)) {
                            zzbjVar = new zzbj(zzbh.zzc, zzbg.zzap, null);
                        }
                    } else {
                        zzbjVar = new zzbj(zzbh.zzb, zzbg.zzX, e.getClass().getSimpleName());
                    }
                    zzdfVar.zzb(zzbjVar);
                    Result.Companion companion6 = Result.INSTANCE;
                    return Result.m9088constructorimpl(ResultKt.createFailure(zzbjVar.zzc()));
                }
            case 2:
                zzdfVar = (zzdf) zzcsVar.zzb;
                zzdcVar2 = (zzdc) zzcsVar.zza;
                zzcx zzcxVar5 = zzcsVar.zzg;
                try {
                    ResultKt.throwOnFailure(objWithContext);
                    zzcxVar = zzcxVar5;
                    zzqf zzqfVar3 = (zzqf) objWithContext;
                    zzcxVar.zzh(zzqfVar3, zzdcVar2);
                    zzdfVar.zza();
                    Result.Companion companion7 = Result.INSTANCE;
                    return Result.m9088constructorimpl(zzqfVar3.zzj());
                } catch (Exception e6) {
                    e = e6;
                    zzcxVar = zzcxVar5;
                    if (e instanceof zzbj) {
                        zzbjVar = (zzbj) e;
                        if (Intrinsics.areEqual(zzbjVar.zzb(), zzbh.zzc)) {
                            zzbjVar = new zzbj(zzbh.zzc, zzbg.zzap, null);
                        }
                    } else {
                        zzbjVar = new zzbj(zzbh.zzb, zzbg.zzX, e.getClass().getSimpleName());
                    }
                    zzdfVar.zzb(zzbjVar);
                    Result.Companion companion8 = Result.INSTANCE;
                    return Result.m9088constructorimpl(ResultKt.createFailure(zzbjVar.zzc()));
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private final void zzh(zzqf zzqfVar, zzdc zzdcVar) throws zzbj {
        zzdf zzdfVarZzf = zzdcVar.zzf(29);
        try {
            List<zzqh> listZzk = zzqfVar.zzk();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listZzk, 10)), 16));
            for (zzqh zzqhVar : listZzk) {
                Pair pair = TuplesKt.to(zzqhVar.zzg(), zzqhVar.zzi());
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            this.zzh.zzb(linkedHashMap);
            this.zzc.zzf(zzqfVar);
            zzdfVarZzf.zza();
        } catch (Exception e) {
            zzbj zzbjVar = e instanceof zzbj ? (zzbj) e : new zzbj(zzbh.zzb, zzbg.zzaa, null);
            zzdfVarZzf.zzb(zzbjVar);
            throw zzbjVar;
        }
    }

    private final boolean zzi(Application application) {
        int i = zzbb.zza;
        return zzbk.zzb(application);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // com.google.android.recaptcha.RecaptchaClient
    /* JADX INFO: renamed from: execute-0E7RQCE */
    public final Object mo8890execute0E7RQCE(RecaptchaAction recaptchaAction, long j, Continuation<? super Result<String>> continuation) {
        zzcq zzcqVar;
        if (continuation instanceof zzcq) {
            zzcqVar = (zzcq) continuation;
            int i = zzcqVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzcqVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzcqVar = new zzcq(this, continuation);
            }
        } else {
            zzcqVar = new zzcq(this, continuation);
        }
        Object obj = zzcqVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (zzcqVar.zzc) {
            case 0:
                ResultKt.throwOnFailure(obj);
                zzcqVar.zzc = 1;
                Object objZzg = zzg(recaptchaAction, j, zzcqVar);
                return objZzg == coroutine_suspended ? coroutine_suspended : objZzg;
            case 1:
                ResultKt.throwOnFailure(obj);
                return ((Result) obj).getValue();
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // com.google.android.recaptcha.RecaptchaClient
    /* JADX INFO: renamed from: execute-gIAlu-s */
    public final Object mo8891executegIAlus(RecaptchaAction recaptchaAction, Continuation<? super Result<String>> continuation) {
        zzcr zzcrVar;
        if (continuation instanceof zzcr) {
            zzcrVar = (zzcr) continuation;
            int i = zzcrVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzcrVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzcrVar = new zzcr(this, continuation);
            }
        } else {
            zzcrVar = new zzcr(this, continuation);
        }
        Object obj = zzcrVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (zzcrVar.zzc) {
            case 0:
                ResultKt.throwOnFailure(obj);
                zzcrVar.zzc = 1;
                Object objMo8890execute0E7RQCE = mo8890execute0E7RQCE(recaptchaAction, 10000L, zzcrVar);
                return objMo8890execute0E7RQCE == coroutine_suspended ? coroutine_suspended : objMo8890execute0E7RQCE;
            case 1:
                ResultKt.throwOnFailure(obj);
                return ((Result) obj).getValue();
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // com.google.android.recaptcha.RecaptchaTasksClient
    public final Task<String> executeTask(RecaptchaAction recaptchaAction) {
        return zzax.zza(BuildersKt__Builders_commonKt.async$default(this.zzi.zzb(), null, null, new zzct(this, recaptchaAction, 10000L, null), 3, null));
    }

    public final String zze() {
        return this.zzd;
    }

    @Override // com.google.android.recaptcha.RecaptchaTasksClient
    public final Task<String> executeTask(RecaptchaAction recaptchaAction, long j) {
        return zzax.zza(BuildersKt__Builders_commonKt.async$default(this.zzi.zzb(), null, null, new zzct(this, recaptchaAction, j, null), 3, null));
    }
}
