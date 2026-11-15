package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzakw<K, V> extends LinkedHashMap<K, V> {
    private static final zzakw<?, ?> zza;
    private boolean zzb;

    private static int zza(Object obj) {
        if (obj instanceof byte[]) {
            return zzakb.zza((byte[]) obj);
        }
        if (obj instanceof zzaka) {
            throw new UnsupportedOperationException();
        }
        return obj.hashCode();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int iZza = 0;
        for (Map.Entry<K, V> entry : entrySet()) {
            iZza += zza(entry.getKey()) ^ zza(entry.getValue());
        }
        return iZza;
    }

    public static <K, V> zzakw<K, V> zza() {
        return (zzakw<K, V>) zza;
    }

    public final zzakw<K, V> zzb() {
        return isEmpty() ? new zzakw<>() : new zzakw<>(this);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V put(K k, V v) {
        zze();
        zzakb.zza(k);
        zzakb.zza(v);
        return (V) super.put(k, v);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        zze();
        return (V) super.remove(obj);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    static {
        zzakw<?, ?> zzakwVar = new zzakw<>();
        zza = zzakwVar;
        ((zzakw) zzakwVar).zzb = false;
    }

    private zzakw() {
        this.zzb = true;
    }

    private zzakw(Map<K, V> map) {
        super(map);
        this.zzb = true;
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zze();
        super.clear();
    }

    private final void zze() {
        if (!this.zzb) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzc() {
        this.zzb = false;
    }

    public final void zza(zzakw<K, V> zzakwVar) {
        zze();
        if (!zzakwVar.isEmpty()) {
            putAll(zzakwVar);
        }
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        zze();
        for (K k : map.keySet()) {
            zzakb.zza(k);
            zzakb.zza(map.get(k));
        }
        super.putAll(map);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        boolean z;
        boolean zEquals;
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (this != map) {
                if (size() != map.size()) {
                    z = false;
                } else {
                    for (Map.Entry<K, V> entry : entrySet()) {
                        if (!map.containsKey(entry.getKey())) {
                            z = false;
                        } else {
                            V value = entry.getValue();
                            Object obj2 = map.get(entry.getKey());
                            if ((value instanceof byte[]) && (obj2 instanceof byte[])) {
                                zEquals = Arrays.equals((byte[]) value, (byte[]) obj2);
                            } else {
                                zEquals = value.equals(obj2);
                            }
                            if (!zEquals) {
                                z = false;
                            }
                        }
                    }
                    z = true;
                }
            } else {
                z = true;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final boolean zzd() {
        return this.zzb;
    }
}
