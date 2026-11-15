package com.google.android.recaptcha.internal;

import android.app.Application;
import android.webkit.WebView;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.RecaptchaException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import kotlin.LazyKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzco {
    private static zzcx zzb;
    public static final zzco zza = new zzco();
    private static final String zzc = UUID.randomUUID().toString();
    private static final Mutex zzd = MutexKt.Mutex$default(false, 1, null);
    private static final zzbo zzf = new zzbo();
    private static final zzl zze = new zzl(null, 1, 0 == true ? 1 : 0);

    static {
        int i = zzbb.zza;
        zzba.zzb(new zzbc(zzbf.class.getName().hashCode(), new zzbf(null, 1, null)), new zzbc(zzeb.class.getName().hashCode(), new zzeb()), new zzbc(zzbk.class.getName().hashCode(), new zzbk()));
    }

    private zzco() {
    }

    public static final /* synthetic */ zzcx zza(zzco zzcoVar, String str) throws zzbj {
        zzcx zzcxVar = zzb;
        if (zzcxVar == null) {
            return null;
        }
        if (Intrinsics.areEqual(zzcxVar.zze(), str)) {
            return zzcxVar;
        }
        throw new zzbj(zzbh.zzd, zzbg.zzan, null);
    }

    public static final Object zzg(Application application, String str, long j, zzdq zzdqVar, Continuation continuation) throws RecaptchaException, TimeoutCancellationException, ApiException {
        return BuildersKt.withContext(zzf.zzb().getCoroutineContext(), new zzcc(application, str, j, null, null), continuation);
    }

    public static final Task zzh(Application application, String str, long j) throws RecaptchaException, TimeoutCancellationException, ApiException {
        return zzax.zza(BuildersKt__Builders_commonKt.async$default(zzf.zza(), null, null, new zzch(application, str, j, null), 3, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object zzi(String str, Application application, Function2 function2, Continuation continuation) throws RecaptchaException {
        zzcn zzcnVar;
        zzdf zzdfVarZzf;
        Exception exc;
        zzbj zzbjVar;
        if (continuation instanceof zzcn) {
            zzcnVar = (zzcn) continuation;
            int i = zzcnVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzcnVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzcnVar = new zzcn(this, continuation);
            }
        } else {
            zzcnVar = new zzcn(this, continuation);
        }
        Object objInvoke = zzcnVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (zzcnVar.zzc) {
            case 0:
                ResultKt.throwOnFailure(objInvoke);
                zzbv zzbvVar = new zzbv("https://www.recaptcha.net/recaptcha/api3");
                zzbo zzboVar = zzf;
                String string = UUID.randomUUID().toString();
                zzdc zzdcVar = new zzdc(str, zzc, string, application, new zzdk(application, new zzdm(zzbvVar.zzc()), zzboVar.zza()), null);
                zzdcVar.zzc(string);
                zzdfVarZzf = zzdcVar.zzf(6);
                try {
                    zzcnVar.zzd = zzdfVarZzf;
                    zzcnVar.zzc = 1;
                    objInvoke = function2.invoke(zzdcVar, zzcnVar);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    zzdfVarZzf.zza();
                    return objInvoke;
                } catch (zzbj e) {
                    zzbjVar = e;
                    zzdfVarZzf.zzb(zzbjVar);
                    throw zzbjVar.zzc();
                } catch (Exception e2) {
                    exc = e2;
                    zzbj zzbjVar2 = new zzbj(zzbh.zzb, zzbg.zza, exc.getMessage());
                    zzdfVarZzf.zzb(zzbjVar2);
                    throw zzbjVar2.zzc();
                }
            case 1:
                zzdfVarZzf = zzcnVar.zzd;
                try {
                    ResultKt.throwOnFailure(objInvoke);
                    zzdfVarZzf.zza();
                    return objInvoke;
                } catch (zzbj e3) {
                    zzbjVar = e3;
                    zzdfVarZzf.zzb(zzbjVar);
                    throw zzbjVar.zzc();
                } catch (Exception e4) {
                    exc = e4;
                    zzbj zzbjVar3 = new zzbj(zzbh.zzb, zzbg.zza, exc.getMessage());
                    zzdfVarZzf.zzb(zzbjVar3);
                    throw zzbjVar3.zzc();
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private final void zzj(String str) throws zzbj {
        try {
            zzpj zzpjVarZzj = zzpj.zzj(zzhz.zzh().zzj(str));
            int i = zzbb.zza;
            ((zzeb) LazyKt.lazy(zzci.zza).getValue()).zza(zzpjVarZzj);
        } catch (Exception e) {
            throw new zzbj(zzbh.zzl, zzbg.zzao, e.getMessage());
        }
    }

    private final boolean zzk(Application application) {
        int i = zzbb.zza;
        return zzbk.zzb(application);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:21:0x0171  */
    /* JADX WARN: Code duplicated, block: B:24:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:27:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:29:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:33:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    public final Object zzl(Application application, String str, long j, zzdc zzdcVar, zzbv zzbvVar, WebView webView, zzdq zzdqVar, zzbo zzboVar, Continuation continuation) throws Throwable {
        zzcf zzcfVar;
        zzbl zzblVar;
        String str2;
        zzdc zzdcVar2;
        zzbv zzbvVar2;
        zzdq zzdqVar2;
        zzbo zzboVar2;
        double d;
        WebView webView2;
        zzco zzcoVar;
        Application application2;
        zzpq zzpqVar;
        zzbo zzboVar3;
        zzbv zzbvVar3;
        Application application3;
        Object objWithContext;
        double d2;
        zzbl zzblVar2;
        zzdc zzdcVar3;
        String str3;
        zzbv zzbvVar4;
        zzbo zzboVar4;
        zzpq zzpqVar2;
        Application application4;
        zzbo zzboVar5;
        Object objZzc;
        zzbo zzboVar6;
        zzbv zzbvVar5;
        Application application5;
        zzpq zzpqVar3;
        zzdc zzdcVar4;
        String str4;
        Throwable thM9091exceptionOrNullimpl;
        List list;
        if (continuation instanceof zzcf) {
            zzcfVar = (zzcf) continuation;
            int i = zzcfVar.zzk;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzcfVar.zzk = i - Integer.MIN_VALUE;
            } else {
                zzcfVar = new zzcf(this, continuation);
            }
        } else {
            zzcfVar = new zzcf(this, continuation);
        }
        zzcf zzcfVar2 = zzcfVar;
        Object objWithContext2 = zzcfVar2.zzi;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (zzcfVar2.zzk) {
            case 0:
                ResultKt.throwOnFailure(objWithContext2);
                double d3 = j;
                zzblVar = new zzbl(application);
                zzcfVar2.zza = this;
                zzcfVar2.zzb = application;
                zzcfVar2.zzc = str;
                zzcfVar2.zzd = zzdcVar;
                zzcfVar2.zze = zzbvVar;
                zzcfVar2.zzf = null;
                zzcfVar2.zzg = zzdqVar;
                zzcfVar2.zzm = zzboVar;
                zzcfVar2.zzl = zzblVar;
                double d4 = d3 * 0.4d;
                zzcfVar2.zzh = d4;
                zzcfVar2.zzk = 1;
                objWithContext2 = BuildersKt.withContext(zzboVar.zza().getCoroutineContext(), new zzck((long) (d3 * 0.6d), application, str, zzdcVar, zzblVar, zzdqVar, zzbvVar, null), zzcfVar2);
                if (objWithContext2 != coroutine_suspended) {
                    str2 = str;
                    zzdcVar2 = zzdcVar;
                    zzbvVar2 = zzbvVar;
                    zzdqVar2 = zzdqVar;
                    zzboVar2 = zzboVar;
                    d = d4;
                    webView2 = null;
                    zzcoVar = this;
                    application2 = application;
                    zzpqVar = (zzpq) objWithContext2;
                    zzcoVar.zzj(zzpqVar.zzM());
                    zzdc zzdcVarZza = zzdcVar2.zza();
                    zzcfVar2.zza = application2;
                    zzcfVar2.zzb = str2;
                    zzcfVar2.zzc = zzdcVar2;
                    zzcfVar2.zzd = zzbvVar2;
                    zzcfVar2.zze = zzboVar2;
                    zzcfVar2.zzf = zzblVar;
                    zzcfVar2.zzg = zzpqVar;
                    zzcfVar2.zzm = null;
                    zzcfVar2.zzl = null;
                    zzcfVar2.zzh = d;
                    zzcfVar2.zzk = 2;
                    zzboVar3 = zzboVar2;
                    zzbvVar3 = zzbvVar2;
                    application3 = application2;
                    objWithContext = BuildersKt.withContext(zzboVar2.zzb().getCoroutineContext(), new zzcg(webView2, application3, zzbvVar3, zzdcVarZza, zzboVar3, zzdqVar2, null), zzcfVar2);
                    if (objWithContext != coroutine_suspended) {
                        d2 = d;
                        zzblVar2 = zzblVar;
                        zzdcVar3 = zzdcVar2;
                        str3 = str2;
                        zzbvVar4 = zzbvVar3;
                        zzboVar4 = zzboVar3;
                        zzpqVar2 = zzpqVar;
                        objWithContext2 = objWithContext;
                        zzx zzxVar = new zzx(zzblVar2, application3, zzdcVar3.zza(), zzboVar4, null, 16, null);
                        application4 = application3;
                        zzboVar5 = zzboVar4;
                        zzl zzlVar = zze;
                        zzlVar.zze(zzxVar, (zzgv) objWithContext2);
                        zzlVar.zza = zzdcVar3.zza();
                        zzl zzlVar2 = zze;
                        zzcfVar2.zza = application4;
                        zzcfVar2.zzb = str3;
                        zzcfVar2.zzc = zzdcVar3;
                        zzcfVar2.zzd = zzbvVar4;
                        zzcfVar2.zze = zzboVar5;
                        zzcfVar2.zzf = zzpqVar2;
                        zzcfVar2.zzg = null;
                        zzcfVar2.zzk = 3;
                        objZzc = zzlVar2.zzc((long) d2, zzpqVar2, zzcfVar2);
                        if (objZzc != coroutine_suspended) {
                            zzboVar6 = zzboVar5;
                            zzbvVar5 = zzbvVar4;
                            application5 = application4;
                            zzpqVar3 = zzpqVar2;
                            zzdcVar4 = zzdcVar3;
                            str4 = str3;
                            thM9091exceptionOrNullimpl = Result.m9091exceptionOrNullimpl(objZzc);
                            if (thM9091exceptionOrNullimpl == null) {
                                return new zzcx(application5, zze, str4, zzboVar6, zzbvVar5, zzpqVar3, zzdcVar4, new zzbl(application5), new zzds());
                            }
                            JobKt__JobKt.cancelChildren$default(zzboVar6.zzd().getCoroutineContext(), (CancellationException) null, 1, (Object) null);
                            list = SequencesKt.toList(JobKt.getJob(zzboVar6.zzd().getCoroutineContext()).getChildren());
                            zzcfVar2.zza = thM9091exceptionOrNullimpl;
                            zzcfVar2.zzb = null;
                            zzcfVar2.zzc = null;
                            zzcfVar2.zzd = null;
                            zzcfVar2.zze = null;
                            zzcfVar2.zzf = null;
                            zzcfVar2.zzk = 4;
                            if (AwaitKt.joinAll(list, zzcfVar2) != coroutine_suspended) {
                                throw thM9091exceptionOrNullimpl;
                            }
                        }
                    }
                }
                return coroutine_suspended;
            case 1:
                d = zzcfVar2.zzh;
                zzbl zzblVar3 = zzcfVar2.zzl;
                zzboVar2 = zzcfVar2.zzm;
                zzdq zzdqVar3 = (zzdq) zzcfVar2.zzg;
                WebView webView3 = (WebView) zzcfVar2.zzf;
                zzbvVar2 = (zzbv) zzcfVar2.zze;
                zzdcVar2 = (zzdc) zzcfVar2.zzd;
                str2 = (String) zzcfVar2.zzc;
                application2 = (Application) zzcfVar2.zzb;
                zzcoVar = (zzco) zzcfVar2.zza;
                ResultKt.throwOnFailure(objWithContext2);
                zzdqVar2 = zzdqVar3;
                webView2 = webView3;
                zzblVar = zzblVar3;
                zzpqVar = (zzpq) objWithContext2;
                zzcoVar.zzj(zzpqVar.zzM());
                zzdc zzdcVarZza2 = zzdcVar2.zza();
                zzcfVar2.zza = application2;
                zzcfVar2.zzb = str2;
                zzcfVar2.zzc = zzdcVar2;
                zzcfVar2.zzd = zzbvVar2;
                zzcfVar2.zze = zzboVar2;
                zzcfVar2.zzf = zzblVar;
                zzcfVar2.zzg = zzpqVar;
                zzcfVar2.zzm = null;
                zzcfVar2.zzl = null;
                zzcfVar2.zzh = d;
                zzcfVar2.zzk = 2;
                zzboVar3 = zzboVar2;
                zzbvVar3 = zzbvVar2;
                application3 = application2;
                objWithContext = BuildersKt.withContext(zzboVar2.zzb().getCoroutineContext(), new zzcg(webView2, application3, zzbvVar3, zzdcVarZza2, zzboVar3, zzdqVar2, null), zzcfVar2);
                if (objWithContext != coroutine_suspended) {
                    d2 = d;
                    zzblVar2 = zzblVar;
                    zzdcVar3 = zzdcVar2;
                    str3 = str2;
                    zzbvVar4 = zzbvVar3;
                    zzboVar4 = zzboVar3;
                    zzpqVar2 = zzpqVar;
                    objWithContext2 = objWithContext;
                    zzx zzxVar2 = new zzx(zzblVar2, application3, zzdcVar3.zza(), zzboVar4, null, 16, null);
                    application4 = application3;
                    zzboVar5 = zzboVar4;
                    zzl zzlVar3 = zze;
                    zzlVar3.zze(zzxVar2, (zzgv) objWithContext2);
                    zzlVar3.zza = zzdcVar3.zza();
                    zzl zzlVar4 = zze;
                    zzcfVar2.zza = application4;
                    zzcfVar2.zzb = str3;
                    zzcfVar2.zzc = zzdcVar3;
                    zzcfVar2.zzd = zzbvVar4;
                    zzcfVar2.zze = zzboVar5;
                    zzcfVar2.zzf = zzpqVar2;
                    zzcfVar2.zzg = null;
                    zzcfVar2.zzk = 3;
                    objZzc = zzlVar4.zzc((long) d2, zzpqVar2, zzcfVar2);
                    if (objZzc != coroutine_suspended) {
                        zzboVar6 = zzboVar5;
                        zzbvVar5 = zzbvVar4;
                        application5 = application4;
                        zzpqVar3 = zzpqVar2;
                        zzdcVar4 = zzdcVar3;
                        str4 = str3;
                        thM9091exceptionOrNullimpl = Result.m9091exceptionOrNullimpl(objZzc);
                        if (thM9091exceptionOrNullimpl == null) {
                            return new zzcx(application5, zze, str4, zzboVar6, zzbvVar5, zzpqVar3, zzdcVar4, new zzbl(application5), new zzds());
                        }
                        JobKt__JobKt.cancelChildren$default(zzboVar6.zzd().getCoroutineContext(), (CancellationException) null, 1, (Object) null);
                        list = SequencesKt.toList(JobKt.getJob(zzboVar6.zzd().getCoroutineContext()).getChildren());
                        zzcfVar2.zza = thM9091exceptionOrNullimpl;
                        zzcfVar2.zzb = null;
                        zzcfVar2.zzc = null;
                        zzcfVar2.zzd = null;
                        zzcfVar2.zze = null;
                        zzcfVar2.zzf = null;
                        zzcfVar2.zzk = 4;
                        if (AwaitKt.joinAll(list, zzcfVar2) != coroutine_suspended) {
                            throw thM9091exceptionOrNullimpl;
                        }
                    }
                }
                return coroutine_suspended;
            case 2:
                double d5 = zzcfVar2.zzh;
                zzpq zzpqVar4 = (zzpq) zzcfVar2.zzg;
                zzbl zzblVar4 = (zzbl) zzcfVar2.zzf;
                zzbo zzboVar7 = (zzbo) zzcfVar2.zze;
                zzbvVar4 = (zzbv) zzcfVar2.zzd;
                zzdc zzdcVar5 = (zzdc) zzcfVar2.zzc;
                String str5 = (String) zzcfVar2.zzb;
                Application application6 = (Application) zzcfVar2.zza;
                ResultKt.throwOnFailure(objWithContext2);
                zzpqVar2 = zzpqVar4;
                d2 = d5;
                zzblVar2 = zzblVar4;
                zzboVar4 = zzboVar7;
                zzdcVar3 = zzdcVar5;
                str3 = str5;
                application3 = application6;
                zzx zzxVar3 = new zzx(zzblVar2, application3, zzdcVar3.zza(), zzboVar4, null, 16, null);
                application4 = application3;
                zzboVar5 = zzboVar4;
                zzl zzlVar5 = zze;
                zzlVar5.zze(zzxVar3, (zzgv) objWithContext2);
                zzlVar5.zza = zzdcVar3.zza();
                zzl zzlVar6 = zze;
                zzcfVar2.zza = application4;
                zzcfVar2.zzb = str3;
                zzcfVar2.zzc = zzdcVar3;
                zzcfVar2.zzd = zzbvVar4;
                zzcfVar2.zze = zzboVar5;
                zzcfVar2.zzf = zzpqVar2;
                zzcfVar2.zzg = null;
                zzcfVar2.zzk = 3;
                objZzc = zzlVar6.zzc((long) d2, zzpqVar2, zzcfVar2);
                if (objZzc != coroutine_suspended) {
                    zzboVar6 = zzboVar5;
                    zzbvVar5 = zzbvVar4;
                    application5 = application4;
                    zzpqVar3 = zzpqVar2;
                    zzdcVar4 = zzdcVar3;
                    str4 = str3;
                    thM9091exceptionOrNullimpl = Result.m9091exceptionOrNullimpl(objZzc);
                    if (thM9091exceptionOrNullimpl == null) {
                        return new zzcx(application5, zze, str4, zzboVar6, zzbvVar5, zzpqVar3, zzdcVar4, new zzbl(application5), new zzds());
                    }
                    JobKt__JobKt.cancelChildren$default(zzboVar6.zzd().getCoroutineContext(), (CancellationException) null, 1, (Object) null);
                    list = SequencesKt.toList(JobKt.getJob(zzboVar6.zzd().getCoroutineContext()).getChildren());
                    zzcfVar2.zza = thM9091exceptionOrNullimpl;
                    zzcfVar2.zzb = null;
                    zzcfVar2.zzc = null;
                    zzcfVar2.zzd = null;
                    zzcfVar2.zze = null;
                    zzcfVar2.zzf = null;
                    zzcfVar2.zzk = 4;
                    if (AwaitKt.joinAll(list, zzcfVar2) != coroutine_suspended) {
                        throw thM9091exceptionOrNullimpl;
                    }
                }
                return coroutine_suspended;
            case 3:
                zzpq zzpqVar5 = (zzpq) zzcfVar2.zzf;
                zzbo zzboVar8 = (zzbo) zzcfVar2.zze;
                zzbv zzbvVar6 = (zzbv) zzcfVar2.zzd;
                zzdc zzdcVar6 = (zzdc) zzcfVar2.zzc;
                String str6 = (String) zzcfVar2.zzb;
                application5 = (Application) zzcfVar2.zza;
                ResultKt.throwOnFailure(objWithContext2);
                objZzc = ((Result) objWithContext2).getValue();
                zzboVar6 = zzboVar8;
                zzbvVar5 = zzbvVar6;
                zzpqVar3 = zzpqVar5;
                zzdcVar4 = zzdcVar6;
                str4 = str6;
                thM9091exceptionOrNullimpl = Result.m9091exceptionOrNullimpl(objZzc);
                if (thM9091exceptionOrNullimpl == null) {
                    return new zzcx(application5, zze, str4, zzboVar6, zzbvVar5, zzpqVar3, zzdcVar4, new zzbl(application5), new zzds());
                }
                JobKt__JobKt.cancelChildren$default(zzboVar6.zzd().getCoroutineContext(), (CancellationException) null, 1, (Object) null);
                list = SequencesKt.toList(JobKt.getJob(zzboVar6.zzd().getCoroutineContext()).getChildren());
                zzcfVar2.zza = thM9091exceptionOrNullimpl;
                zzcfVar2.zzb = null;
                zzcfVar2.zzc = null;
                zzcfVar2.zzd = null;
                zzcfVar2.zze = null;
                zzcfVar2.zzf = null;
                zzcfVar2.zzk = 4;
                if (AwaitKt.joinAll(list, zzcfVar2) != coroutine_suspended) {
                    throw thM9091exceptionOrNullimpl;
                }
                return coroutine_suspended;
            case 4:
                Throwable th = (Throwable) zzcfVar2.zza;
                ResultKt.throwOnFailure(objWithContext2);
                throw th;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:36:0x008f  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0, types: [android.app.Application, android.content.Context, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9, types: [java.lang.Object] */
    public final Object zzm(Application application, String str, long j, zzbv zzbvVar, WebView webView, zzdq zzdqVar, zzbo zzboVar, zzdc zzdcVar, Continuation continuation) throws Throwable {
        zzcm zzcmVar;
        zzco zzcoVar;
        if (continuation instanceof zzcm) {
            zzcm zzcmVar2 = (zzcm) continuation;
            int i = zzcmVar2.zzd;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzcmVar2.zzd = i - Integer.MIN_VALUE;
                zzcmVar = zzcmVar2;
            } else {
                zzcmVar = new zzcm(this, continuation);
            }
        } else {
            zzcmVar = new zzcm(this, continuation);
        }
        Object objZzl = zzcmVar.zzb;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (zzcmVar.zzd) {
                case 0:
                    ResultKt.throwOnFailure(objZzl);
                    try {
                        zzdq zzdqVar2 = new zzdq(new zzbs(application), new zzdo());
                        zzcmVar.zze = this;
                        zzcmVar.zza = application;
                        zzcmVar.zzd = 1;
                        objZzl = zzl(application, str, j, zzdcVar, zzbvVar, null, zzdqVar2, zzboVar, zzcmVar);
                        if (objZzl == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        zzcoVar = this;
                        application = application;
                        try {
                            zzcx zzcxVar = (zzcx) objZzl;
                            zzb = zzcxVar;
                            return zzcxVar;
                        } catch (zzbj e) {
                            e = e;
                            if (Intrinsics.areEqual(e.zzb(), zzbh.zzc)) {
                                break;
                            }
                            throw e;
                        }
                    } catch (zzbj e2) {
                        e = e2;
                        zzcoVar = this;
                        if (Intrinsics.areEqual(e.zzb(), zzbh.zzc)) {
                            break;
                        }
                        throw e;
                    }
                case 1:
                    application = zzcmVar.zza;
                    zzcoVar = zzcmVar.zze;
                    try {
                        ResultKt.throwOnFailure(objZzl);
                        application = application;
                        zzcx zzcxVar2 = (zzcx) objZzl;
                        zzb = zzcxVar2;
                        return zzcxVar2;
                    } catch (zzbj e3) {
                        e = e3;
                        if (Intrinsics.areEqual(e.zzb(), zzbh.zzc) || zzcoVar.zzk((Application) application)) {
                            throw e;
                        }
                        throw new zzbj(zzbh.zzc, zzbg.zzap, null);
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (RecaptchaException e4) {
            throw e4;
        } catch (Exception e5) {
            throw new zzbj(zzbh.zzb, zzbg.zza, e5.getMessage());
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r1v9, types: [kotlinx.coroutines.sync.Mutex] */
    public final Object zze(Application application, String str, long j, zzbv zzbvVar, WebView webView, zzdq zzdqVar, zzbo zzboVar, Continuation continuation) throws Throwable {
        zzcd zzcdVar;
        Application application2;
        String str2;
        zzbv zzbvVar2;
        long j2;
        Object obj;
        zzbo zzboVar2;
        ?? r1;
        if (continuation instanceof zzcd) {
            zzcdVar = (zzcd) continuation;
            int i = zzcdVar.zzf;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzcdVar.zzf = i - Integer.MIN_VALUE;
            } else {
                zzcdVar = new zzcd(this, continuation);
            }
        } else {
            zzcdVar = new zzcd(this, continuation);
        }
        Object objZzi = zzcdVar.zzd;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (zzcdVar.zzf) {
            case 0:
                ResultKt.throwOnFailure(objZzi);
                Mutex mutex = zzd;
                zzcdVar.zza = application;
                zzcdVar.zzg = str;
                zzcdVar.zzh = zzbvVar;
                zzcdVar.zzi = zzboVar;
                zzcdVar.zzb = mutex;
                zzcdVar.zzc = j;
                zzcdVar.zzf = 1;
                if (mutex.lock(null, zzcdVar) != coroutine_suspended) {
                    application2 = application;
                    str2 = str;
                    zzbvVar2 = zzbvVar;
                    j2 = j;
                    obj = mutex;
                    zzboVar2 = zzboVar;
                    try {
                        zzco zzcoVar = zza;
                        zzce zzceVar = new zzce(str2, application2, j2, zzbvVar2, null, null, zzboVar2, null);
                        zzcdVar.zza = obj;
                        zzcdVar.zzg = null;
                        zzcdVar.zzh = null;
                        zzcdVar.zzi = null;
                        zzcdVar.zzb = null;
                        zzcdVar.zzf = 2;
                        objZzi = zzcoVar.zzi(str2, application2, zzceVar, zzcdVar);
                        if (objZzi != coroutine_suspended) {
                            r1 = obj;
                            zzcx zzcxVar = (zzcx) objZzi;
                            r1.unlock(null);
                            return zzcxVar;
                        }
                    } catch (Throwable th) {
                        th = th;
                        r1 = obj;
                        r1.unlock(null);
                        throw th;
                    }
                }
                return coroutine_suspended;
            case 1:
                long j3 = zzcdVar.zzc;
                obj = zzcdVar.zzb;
                zzbo zzboVar3 = zzcdVar.zzi;
                zzbv zzbvVar3 = zzcdVar.zzh;
                String str3 = zzcdVar.zzg;
                Application application3 = (Application) zzcdVar.zza;
                ResultKt.throwOnFailure(objZzi);
                j2 = j3;
                zzbvVar2 = zzbvVar3;
                str2 = str3;
                application2 = application3;
                zzboVar2 = zzboVar3;
                zzco zzcoVar2 = zza;
                zzce zzceVar2 = new zzce(str2, application2, j2, zzbvVar2, null, null, zzboVar2, null);
                zzcdVar.zza = obj;
                zzcdVar.zzg = null;
                zzcdVar.zzh = null;
                zzcdVar.zzi = null;
                zzcdVar.zzb = null;
                zzcdVar.zzf = 2;
                objZzi = zzcoVar2.zzi(str2, application2, zzceVar2, zzcdVar);
                if (objZzi != coroutine_suspended) {
                    r1 = obj;
                    zzcx zzcxVar2 = (zzcx) objZzi;
                    r1.unlock(null);
                    return zzcxVar2;
                }
                return coroutine_suspended;
            case 2:
                r1 = (Mutex) zzcdVar.zza;
                try {
                    ResultKt.throwOnFailure(objZzi);
                    r1 = r1;
                    zzcx zzcxVar3 = (zzcx) objZzi;
                    r1.unlock(null);
                    return zzcxVar3;
                } catch (Throwable th2) {
                    th = th2;
                    r1.unlock(null);
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
