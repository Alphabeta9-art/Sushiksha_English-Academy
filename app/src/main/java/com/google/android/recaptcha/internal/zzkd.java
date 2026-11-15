package com.google.android.recaptcha.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzkd {
    public static final /* synthetic */ int zzb = 0;
    private final Map zzd;
    private static volatile boolean zzc = false;
    static final zzkd zza = new zzkd(true);

    zzkd() {
        this.zzd = new HashMap();
    }

    public final zzkq zza(zzlx zzlxVar, int i) {
        return (zzkq) this.zzd.get(new zzkc(zzlxVar, i));
    }

    zzkd(boolean z) {
        this.zzd = Collections.emptyMap();
    }
}
