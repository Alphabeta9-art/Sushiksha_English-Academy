package com.google.android.recaptcha.internal;

import android.webkit.JavascriptInterface;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlinx.coroutines.CompletableDeferred;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzgo {
    final /* synthetic */ zzgv zza;
    private Long zzb;
    private final zzgz zzc = zzgz.zzb();

    public zzgo(zzgv zzgvVar) {
        this.zza = zzgvVar;
    }

    private final void zzb() {
        if (this.zzb == null) {
            this.zzc.zzf();
            this.zzb = Long.valueOf(this.zzc.zza(TimeUnit.MILLISECONDS));
        }
    }

    public final Long zza() {
        return this.zzb;
    }

    @JavascriptInterface
    public final void zzlce(String input) throws zzlc {
        zzgv zzgvVar = this.zza;
        if (zzgvVar.zzq().zzb == null) {
            zzdf zzdfVar = zzgvVar.zzm;
            if (zzdfVar != null) {
                zzdfVar.zza();
            }
            this.zza.zzm = null;
        }
        zzb();
        zzop zzopVarZzJ = zzop.zzJ(zzhz.zzh().zzj(input));
        zzrd zzrdVarZzi = zzre.zzi();
        zzrdVarZzi.zze(zzopVarZzJ);
        this.zza.zzl.zze((zzre) zzrdVarZzi.zzj());
    }

    @JavascriptInterface
    public final void zzlsm(String input) {
        zzb();
        zzrd zzrdVarZzi = zzre.zzi();
        zzrdVarZzi.zzp(zzpe.zzi(zzhz.zzh().zzj(input)));
        this.zza.zzl.zze((zzre) zzrdVarZzi.zzj());
    }

    @JavascriptInterface
    public final void zzoid(String response) throws zzlc {
        zzb();
        zzqz zzqzVarZzg = zzqz.zzg(zzhz.zzh().zzj(response));
        zzqzVarZzg.zzi().name();
        if (zzqzVarZzg.zzi() == zzrc.JS_CODE_SUCCESS) {
            this.zza.zzu().hashCode();
            if (this.zza.zzu().complete(Unit.INSTANCE)) {
                return;
            }
            this.zza.zzu().hashCode();
            return;
        }
        zzqzVarZzg.zzi().name();
        int i = zzbj.zza;
        zzbj zzbjVarZza = zzbi.zza(zzqzVarZzg.zzi());
        this.zza.zzu().hashCode();
        this.zza.zzu().completeExceptionally(zzbjVarZza);
    }

    @JavascriptInterface
    public final void zzrp(String input) {
        zzb();
        zzdv zzdvVar = this.zza.zzb;
        if (zzdvVar == null) {
            zzdvVar = null;
        }
        zzdvVar.zza(input);
    }

    @JavascriptInterface
    public final void zzscd(String input) throws zzlc {
        zzb();
        zzpw zzpwVarZzi = zzpw.zzi(zzhz.zzh().zzj(input));
        zzpwVarZzi.toString();
        CompletableDeferred completableDeferred = (CompletableDeferred) this.zza.zzi.remove(zzpwVarZzi.zzk());
        if (completableDeferred != null) {
            completableDeferred.complete(zzpwVarZzi);
        }
    }
}
