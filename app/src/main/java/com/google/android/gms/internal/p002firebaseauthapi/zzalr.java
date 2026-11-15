package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzalr {
    private static final zzalr zza = new zzalr();
    private final ConcurrentMap<Class<?>, zzalv<?>> zzc = new ConcurrentHashMap();
    private final zzalu zzb = new zzakq();

    public static zzalr zza() {
        return zza;
    }

    public final <T> zzalv<T> zza(Class<T> cls) {
        zzakb.zza(cls, "messageType");
        zzalv<T> zzalvVar = (zzalv) this.zzc.get(cls);
        if (zzalvVar != null) {
            return zzalvVar;
        }
        zzalv<T> zzalvVarZza = this.zzb.zza(cls);
        zzakb.zza(cls, "messageType");
        zzakb.zza(zzalvVarZza, "schema");
        zzalv<T> zzalvVar2 = (zzalv) this.zzc.putIfAbsent(cls, zzalvVarZza);
        return zzalvVar2 != null ? zzalvVar2 : zzalvVarZza;
    }

    public final <T> zzalv<T> zza(T t) {
        return zza((Class) t.getClass());
    }

    private zzalr() {
    }
}
