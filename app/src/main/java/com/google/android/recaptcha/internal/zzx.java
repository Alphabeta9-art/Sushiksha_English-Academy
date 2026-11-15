package com.google.android.recaptcha.internal;

import android.content.Context;
import com.google.android.play.core.integrity.IntegrityManagerFactory;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzx extends zze {
    private final zzdc zza;
    private final List zzb;
    private zziv zzc;
    private final Map zzd;

    public /* synthetic */ zzx(zzbl zzblVar, Context context, zzdc zzdcVar, zzbo zzboVar, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        zzdc zzdcVarZza = zzdcVar.zza();
        CoroutineScope coroutineScopeZzc = zzboVar.zzc();
        zzap zzapVar = new zzap(context, coroutineScopeZzc, zzdcVarZza, IntegrityManagerFactory.createStandard(context), 28800000L);
        int i2 = zzbz.zza;
        List listListOf = CollectionsKt.listOf((Object[]) new zzaa[]{new zzad(zzdcVar.zza()), new zzp(zzdcVar.zza(), zzblVar), new zzm(zzdcVar.zza(), context.getContentResolver()), new zzn(zzdcVar.zza()), new zzag(zzdcVarZza, context, coroutineScopeZzc, zzapVar, zzbz.zza(context))});
        this.zza = zzdcVar;
        this.zzb = listListOf;
        this.zzd = new LinkedHashMap();
    }

    public static final /* synthetic */ String zzo(zzx zzxVar, zzri zzriVar) {
        zziv zzivVar = zzxVar.zzc;
        if (zzivVar == null) {
            zzivVar = null;
        }
        byte[] bArrZzd = zzriVar.zzd();
        return zzca.zza(zzhz.zzh().zzi(bArrZzd, 0, bArrZzd.length), zzivVar);
    }

    public static final /* synthetic */ String zzp(zzx zzxVar, zzrm zzrmVar) {
        zziv zzivVar = zzxVar.zzc;
        if (zzivVar == null) {
            zzivVar = null;
        }
        byte[] bArrZzd = zzrmVar.zzd();
        return zzca.zza(zzhz.zzh().zzi(bArrZzd, 0, bArrZzd.length), zzivVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final zzpw zzt(String str) {
        zzqm zzqmVar = (zzqm) this.zzd.remove(str);
        zzpv zzpvVarZzf = zzpw.zzf();
        zzpvVarZzf.zzd(str);
        if (zzqmVar != null) {
            byte[] bArrZzd = ((zzqn) zzqmVar.zzj()).zzd();
            String strZzi = zzhz.zzh().zzi(bArrZzd, 0, bArrZzd.length);
            zzpt zzptVarZzf = zzpu.zzf();
            zzptVarZzf.zzd(strZzi);
            zzpvVarZzf.zze(zzptVarZzf);
        }
        return (zzpw) zzpvVarZzf.zzj();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object zzu(String str, zzaa zzaaVar, Continuation continuation) {
        zzq zzqVar;
        if (continuation instanceof zzq) {
            zzqVar = (zzq) continuation;
            int i = zzqVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzqVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzqVar = new zzq(this, continuation);
            }
        } else {
            zzqVar = new zzq(this, continuation);
        }
        Object objCoroutineScope = zzqVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (zzqVar.zzc) {
            case 0:
                ResultKt.throwOnFailure(objCoroutineScope);
                zzr zzrVar = new zzr(zzaaVar, str, this, null);
                zzqVar.zzc = 1;
                objCoroutineScope = CoroutineScopeKt.coroutineScope(zzrVar, zzqVar);
                return objCoroutineScope == coroutine_suspended ? coroutine_suspended : objCoroutineScope;
            case 1:
                ResultKt.throwOnFailure(objCoroutineScope);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // com.google.android.recaptcha.internal.zze
    protected final zzdf zza(String str) {
        zzdc zzdcVar = this.zza;
        zzdcVar.zzc(str);
        return zzdcVar.zzf(35);
    }

    @Override // com.google.android.recaptcha.internal.zze
    protected final zzdf zzb() {
        zzdc zzdcVar = this.zza;
        zzdcVar.zzc(zzdcVar.zzd());
        return zzdcVar.zzf(34);
    }

    @Override // com.google.android.recaptcha.internal.zze
    protected final Object zzd(String str, Continuation continuation) {
        return zzt(str);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // com.google.android.recaptcha.internal.zze
    protected final Object zzf(String str, Continuation continuation) {
        zzs zzsVar;
        if (continuation instanceof zzs) {
            zzsVar = (zzs) continuation;
            int i = zzsVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzsVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzsVar = new zzs(this, continuation);
            }
        } else {
            zzsVar = new zzs(this, continuation);
        }
        Object objCoroutineScope = zzsVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (zzsVar.zzc) {
            case 0:
                ResultKt.throwOnFailure(objCoroutineScope);
                zzu zzuVar = new zzu(str, this, null);
                zzsVar.zzc = 1;
                objCoroutineScope = CoroutineScopeKt.coroutineScope(zzuVar, zzsVar);
                if (objCoroutineScope == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure(objCoroutineScope);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return ((Result) objCoroutineScope).getValue();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // com.google.android.recaptcha.internal.zze
    protected final Object zzh(zzpq zzpqVar, Continuation continuation) {
        zzv zzvVar;
        if (continuation instanceof zzv) {
            zzvVar = (zzv) continuation;
            int i = zzvVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzvVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzvVar = new zzv(this, continuation);
            }
        } else {
            zzvVar = new zzv(this, continuation);
        }
        Object objCoroutineScope = zzvVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (zzvVar.zzc) {
            case 0:
                ResultKt.throwOnFailure(objCoroutineScope);
                zzw zzwVar = new zzw(zzpqVar, this, null);
                zzvVar.zzc = 1;
                objCoroutineScope = CoroutineScopeKt.coroutineScope(zzwVar, zzvVar);
                if (objCoroutineScope == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure(objCoroutineScope);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return ((Result) objCoroutineScope).getValue();
    }

    @Override // com.google.android.recaptcha.internal.zze
    protected final void zzk(zzqf zzqfVar) {
        Iterator it = this.zzb.iterator();
        while (it.hasNext()) {
            ((zzaa) it.next()).zze(zzqfVar);
        }
    }

    public final Map zzr() {
        return this.zzd;
    }
}
