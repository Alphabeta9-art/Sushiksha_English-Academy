package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzajk {
    private final Map<zzajn, zzajy.zzd<?, ?>> zzc;
    private static volatile boolean zzb = false;
    static final zzajk zza = new zzajk(true);

    public static zzajk zza() {
        return zza;
    }

    public final <ContainingType extends zzalc> zzajy.zzd<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return (zzajy.zzd) this.zzc.get(new zzajn(containingtype, i));
    }

    zzajk() {
        this.zzc = new HashMap();
    }

    private zzajk(boolean z) {
        this.zzc = Collections.emptyMap();
    }
}
