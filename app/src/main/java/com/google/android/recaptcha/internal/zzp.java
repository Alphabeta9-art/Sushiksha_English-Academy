package com.google.android.recaptcha.internal;

import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzp implements zzaa {
    private final zzdc zza;
    private final zzbl zzb;
    private boolean zzc = true;

    public zzp(zzdc zzdcVar, zzbl zzblVar) {
        this.zza = zzdcVar;
        this.zzb = zzblVar;
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final int zza() {
        return 25;
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final zzdc zzb() {
        return this.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final Object zzc(String str, Continuation continuation) {
        zzbl zzblVar = this.zzb;
        zzdf zzdfVarZzb = zzab.zzb(this, str);
        String strZza = zzblVar.zza();
        zzdfVarZzb.zza();
        zzrj zzrjVarZzf = zzrm.zzf();
        zzrk zzrkVarZzf = zzrl.zzf();
        zzrkVarZzf.zzv(strZza);
        zzrjVarZzf.zzd(CollectionsKt.listOf(zzrkVarZzf.zzj()));
        return zzab.zza(this, (zzrm) zzrjVarZzf.zzj());
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final Object zzd(zzps zzpsVar, Continuation continuation) {
        zzdf zzdfVarZzc = zzab.zzc(this);
        if (zzpsVar.zzJ().length() == 0) {
            this.zzc = false;
            zzdfVarZzc.zzb(new zzbj(zzbh.zzb, zzbg.zzac, null));
            return Unit.INSTANCE;
        }
        this.zzb.zzb(MapsKt.mapOf(TuplesKt.to("_GRECAPTCHA_KC", zzpsVar.zzJ())));
        zzdfVarZzc.zza();
        return Unit.INSTANCE;
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final void zze(zzqf zzqfVar) {
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final boolean zzf() {
        return this.zzc;
    }
}
