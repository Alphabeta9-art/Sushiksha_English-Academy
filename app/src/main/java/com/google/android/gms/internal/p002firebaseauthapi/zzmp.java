package com.google.android.gms.internal.p002firebaseauthapi;

import java.lang.Enum;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzmp<E extends Enum<E>, O> {
    private Map<E, O> zza;
    private Map<O, E> zzb;

    public final zzmp<E, O> zza(E e, O o) {
        this.zza.put(e, o);
        this.zzb.put(o, e);
        return this;
    }

    public final zzmm<E, O> zza() {
        return new zzmm<>(Collections.unmodifiableMap(this.zza), Collections.unmodifiableMap(this.zzb));
    }

    private zzmp() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
    }
}
