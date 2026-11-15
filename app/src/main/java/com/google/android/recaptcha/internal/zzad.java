package com.google.android.recaptcha.internal;

import android.os.Build;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzad implements zzaa {
    private final zzdc zza;

    public zzad(zzdc zzdcVar) {
        this.zza = zzdcVar;
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final int zza() {
        return 10;
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final zzdc zzb() {
        return this.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final Object zzc(String str, Continuation continuation) {
        zzdf zzdfVarZzb = zzab.zzb(this, str);
        int i = Build.VERSION.SDK_INT;
        zzdfVarZzb.zza();
        zzrj zzrjVarZzf = zzrm.zzf();
        zzrk zzrkVarZzf = zzrl.zzf();
        zzrkVarZzf.zzv(String.valueOf(i));
        zzrjVarZzf.zzd(CollectionsKt.listOf(zzrkVarZzf.zzj()));
        return zzab.zza(this, (zzrm) zzrjVarZzf.zzj());
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final Object zzd(zzps zzpsVar, Continuation continuation) {
        zzab.zzc(this).zza();
        return Unit.INSTANCE;
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final void zze(zzqf zzqfVar) {
    }

    @Override // com.google.android.recaptcha.internal.zzaa
    public final boolean zzf() {
        return true;
    }
}
