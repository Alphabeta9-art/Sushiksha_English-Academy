package com.google.android.recaptcha.internal;

import android.content.Context;
import android.webkit.WebView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.TimeoutCancellationException;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzgv extends zze {
    public CompletableDeferred zza;
    public zzdv zzb;
    private final WebView zzc;
    private final Context zzd;
    private final zzbv zze;
    private final zzdc zzf;
    private final zzdq zzg;
    private final Map zzh = zzgw.zza();
    private final Map zzi = new LinkedHashMap();
    private final zzgz zzj = zzgz.zzc();
    private final zzgo zzk = new zzgo(this);
    private final zzdc zzl;
    private zzdf zzm;
    private final zzbo zzn;

    public zzgv(WebView webView, Context context, zzbv zzbvVar, zzdc zzdcVar, zzbo zzboVar, zzdq zzdqVar) {
        this.zzc = webView;
        this.zzd = context;
        this.zze = zzbvVar;
        this.zzf = zzdcVar;
        this.zzn = zzboVar;
        this.zzg = zzdqVar;
        zzdc zzdcVar2 = this.zzf;
        zzdc zzdcVarZza = zzdcVar2.zza();
        zzdcVarZza.zzc(zzdcVar2.zzd());
        this.zzl = zzdcVarZza;
        this.zzc.getSettings().setJavaScriptEnabled(true);
        this.zzc.addJavascriptInterface(this.zzk, "RN");
        this.zzc.setWebViewClient(new zzgp(this));
    }

    public static final /* synthetic */ void zzv(zzgv zzgvVar, String str) {
        zzgvVar.zzm = zzgvVar.zzl.zzf(26);
        try {
            zzgvVar.zzc.loadDataWithBaseURL(zzgvVar.zze.zza(), str, "text/html", "utf-8", null);
        } catch (Exception e) {
            zzbj zzbjVar = new zzbj(zzbh.zzb, zzbg.zzU, null);
            zzdf zzdfVar = zzgvVar.zzm;
            if (zzdfVar != null) {
                zzdfVar.zzb(zzbjVar);
            }
            zzgvVar.zzm = null;
            zzgvVar.zzu().completeExceptionally(zzbjVar);
        }
    }

    @Override // com.google.android.recaptcha.internal.zze
    protected final zzdf zza(String str) {
        zzdc zzdcVar = this.zzf;
        zzdcVar.zzc(str);
        return zzdcVar.zzf(33);
    }

    @Override // com.google.android.recaptcha.internal.zze
    protected final zzdf zzb() {
        zzdc zzdcVar = this.zzf;
        zzdcVar.zzc(zzdcVar.zzd());
        return zzdcVar.zzf(32);
    }

    @Override // com.google.android.recaptcha.internal.zze
    protected final Object zzd(String str, Continuation continuation) {
        zzpv zzpvVarZzf = zzpw.zzf();
        zzpvVarZzf.zzd(str);
        return zzpvVarZzf.zzj();
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00db  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // com.google.android.recaptcha.internal.zze
    protected final Object zzf(String str, Continuation continuation) {
        zzgs zzgsVar;
        Exception exc;
        zzgv zzgvVar;
        zzbj zzbjVarZza;
        CompletableDeferred completableDeferred;
        if (continuation instanceof zzgs) {
            zzgsVar = (zzgs) continuation;
            int i = zzgsVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzgsVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzgsVar = new zzgs(this, continuation);
            }
        } else {
            zzgsVar = new zzgs(this, continuation);
        }
        Object objAwait = zzgsVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (zzgsVar.zzc) {
            case 0:
                ResultKt.throwOnFailure(objAwait);
                try {
                    CompletableDeferred completableDeferredCompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
                    this.zzi.put(str, completableDeferredCompletableDeferred$default);
                    zzqw zzqwVarZzf = zzqx.zzf();
                    zzqwVarZzf.zzd(str);
                    byte[] bArrZzd = ((zzqx) zzqwVarZzf.zzj()).zzd();
                    BuildersKt__Builders_commonKt.launch$default(this.zzn.zzb(), null, null, new zzgt(this, zzhz.zzh().zzi(bArrZzd, 0, bArrZzd.length), null), 3, null);
                    zzgsVar.zzd = this;
                    zzgsVar.zze = str;
                    zzgsVar.zzc = 1;
                    objAwait = completableDeferredCompletableDeferred$default.await(zzgsVar);
                    if (objAwait == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    zzgvVar = this;
                    try {
                        zzpw zzpwVar = (zzpw) objAwait;
                        zzpv zzpvVar = (zzpv) zzpwVar.zzq();
                        zzpz zzpzVarZzf = zzqa.zzf();
                        zzpzVarZzf.zzd(zzpwVar.zzJ());
                        zzpvVar.zzp(zzpzVarZzf);
                        zzpx zzpxVarZzf = zzpy.zzf();
                        zzpxVarZzf.zzd(zzpwVar.zzj());
                        zzpxVarZzf.zze(zzpwVar.zzK());
                        zzpvVar.zzq(zzpxVarZzf);
                        Result.Companion companion = Result.INSTANCE;
                        return Result.m9088constructorimpl(zzpvVar.zzj());
                    } catch (Exception e) {
                        exc = e;
                        zzbjVarZza = zzf.zza(exc, new zzbj(zzbh.zzb, zzbg.zzW, exc.getClass().getSimpleName()));
                        completableDeferred = (CompletableDeferred) zzgvVar.zzi.remove(str);
                        if (completableDeferred != null) {
                            Boxing.boxBoolean(completableDeferred.completeExceptionally(zzbjVarZza));
                        }
                        Result.Companion companion2 = Result.INSTANCE;
                        return Result.m9088constructorimpl(ResultKt.createFailure(zzbjVarZza));
                    }
                } catch (Exception e2) {
                    exc = e2;
                    zzgvVar = this;
                    zzbjVarZza = zzf.zza(exc, new zzbj(zzbh.zzb, zzbg.zzW, exc.getClass().getSimpleName()));
                    completableDeferred = (CompletableDeferred) zzgvVar.zzi.remove(str);
                    if (completableDeferred != null) {
                        Boxing.boxBoolean(completableDeferred.completeExceptionally(zzbjVarZza));
                    }
                    Result.Companion companion3 = Result.INSTANCE;
                    return Result.m9088constructorimpl(ResultKt.createFailure(zzbjVarZza));
                }
            case 1:
                str = zzgsVar.zze;
                zzgvVar = zzgsVar.zzd;
                try {
                    ResultKt.throwOnFailure(objAwait);
                    zzpw zzpwVar2 = (zzpw) objAwait;
                    zzpv zzpvVar2 = (zzpv) zzpwVar2.zzq();
                    zzpz zzpzVarZzf2 = zzqa.zzf();
                    zzpzVarZzf2.zzd(zzpwVar2.zzJ());
                    zzpvVar2.zzp(zzpzVarZzf2);
                    zzpx zzpxVarZzf2 = zzpy.zzf();
                    zzpxVarZzf2.zzd(zzpwVar2.zzj());
                    zzpxVarZzf2.zze(zzpwVar2.zzK());
                    zzpvVar2.zzq(zzpxVarZzf2);
                    Result.Companion companion4 = Result.INSTANCE;
                    return Result.m9088constructorimpl(zzpvVar2.zzj());
                } catch (Exception e3) {
                    exc = e3;
                    zzbjVarZza = zzf.zza(exc, new zzbj(zzbh.zzb, zzbg.zzW, exc.getClass().getSimpleName()));
                    completableDeferred = (CompletableDeferred) zzgvVar.zzi.remove(str);
                    if (completableDeferred != null) {
                        Boxing.boxBoolean(completableDeferred.completeExceptionally(zzbjVarZza));
                    }
                    Result.Companion companion5 = Result.INSTANCE;
                    return Result.m9088constructorimpl(ResultKt.createFailure(zzbjVarZza));
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // com.google.android.recaptcha.internal.zze
    protected final Object zzg(zzbj zzbjVar, Continuation continuation) {
        if (Intrinsics.areEqual(zzbjVar.zza(), zzbg.zzb)) {
            zzdf zzdfVar = this.zzm;
            if (zzdfVar != null) {
                zzdfVar.zzb(zzbjVar);
            }
            this.zzm = null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // com.google.android.recaptcha.internal.zze
    protected final Object zzh(zzpq zzpqVar, Continuation continuation) {
        zzgu zzguVar;
        if (continuation instanceof zzgu) {
            zzguVar = (zzgu) continuation;
            int i = zzguVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzguVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzguVar = new zzgu(this, continuation);
            }
        } else {
            zzguVar = new zzgu(this, continuation);
        }
        Object obj = zzguVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (zzguVar.zzc) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.zzb = zzy(zzpqVar, new zzcb(zzpqVar.zzf()));
                    this.zza = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
                    Boxing.boxInt(zzu().hashCode());
                    this.zzj.zzd();
                    this.zzj.zze();
                    BuildersKt__Builders_commonKt.launch$default(this.zzn.zza(), null, null, new zzgr(this, zzpqVar, null), 3, null);
                    Boxing.boxInt(zzu().hashCode());
                    CompletableDeferred completableDeferredZzu = zzu();
                    zzguVar.zzc = 1;
                    if (completableDeferredZzu.await(zzguVar) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure(obj);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Unit unit = Unit.INSTANCE;
            Result.Companion companion = Result.INSTANCE;
            return Result.m9088constructorimpl(unit);
        } catch (Exception e) {
            e.getMessage();
            zzbj zzbjVarZza = zzf.zza(e, new zzbj(zzbh.zzb, zzbg.zzV, e.getClass().getSimpleName()));
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m9088constructorimpl(ResultKt.createFailure(zzbjVarZza));
        }
    }

    @Override // com.google.android.recaptcha.internal.zze
    protected final Object zzi(String str, long j, Exception exc, Continuation continuation) {
        CompletableDeferred completableDeferred = (CompletableDeferred) this.zzi.remove(str);
        if (completableDeferred != null) {
            Boxing.boxBoolean(completableDeferred.completeExceptionally(exc));
        }
        return Unit.INSTANCE;
    }

    @Override // com.google.android.recaptcha.internal.zze
    protected final Object zzj(Exception exc, Continuation continuation) {
        Long lZza = this.zzk.zza();
        if ((exc instanceof TimeoutCancellationException) && lZza == null) {
            return new zzbj(zzbh.zzc, zzbg.zzH, null);
        }
        return zzf.zza(exc, new zzbj(zzbh.zzb, zzbg.zzV, exc.getClass().getSimpleName()));
    }

    public final WebView zzm() {
        return this.zzc;
    }

    public final zzdq zzp() {
        return this.zzg;
    }

    public final zzgo zzq() {
        return this.zzk;
    }

    public final CompletableDeferred zzu() {
        CompletableDeferred completableDeferred = this.zza;
        if (completableDeferred != null) {
            return completableDeferred;
        }
        return null;
    }

    public final zzea zzy(zzpq zzpqVar, zzcb zzcbVar) {
        zzed zzedVar = new zzed(this.zzc, this.zzn.zzb());
        zzgf zzgfVar = new zzgf();
        zzgfVar.zzb(CollectionsKt.toLongArray(zzpqVar.zzN()));
        zzem zzemVar = new zzem(zzedVar, zzcbVar, new zzbu());
        zzgg zzggVar = new zzgg(zzgfVar, new zzgd());
        zzemVar.zze(3, this.zzd);
        zzemVar.zze(5, zzgm.class.getMethod("cs", new Object[0].getClass()));
        zzemVar.zze(6, new zzgh(this.zzd));
        zzemVar.zze(7, new zzgj());
        zzemVar.zze(8, new zzgn(this.zzd));
        zzemVar.zze(9, new zzgk(this.zzd));
        zzemVar.zze(10, new zzgi(this.zzd));
        return new zzea(this.zzn.zzd(), zzemVar, zzggVar, zzdu.zza());
    }
}
