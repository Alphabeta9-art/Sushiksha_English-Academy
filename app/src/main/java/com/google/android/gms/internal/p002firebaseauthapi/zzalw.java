package com.google.android.gms.internal.p002firebaseauthapi;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
class zzalw<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private Object[] zza;
    private int zzb;
    private Map<K, V> zzc;
    private boolean zzd;
    private volatile zzamc zze;
    private Map<K, V> zzf;

    private final int zza(K k) {
        int i = this.zzb - 1;
        if (i >= 0) {
            int iCompareTo = k.compareTo((Comparable) ((zzama) this.zza[i]).getKey());
            if (iCompareTo > 0) {
                return -(i + 2);
            }
            if (iCompareTo == 0) {
                return i;
            }
        }
        int i2 = 0;
        while (i2 <= i) {
            int i3 = (i2 + i) / 2;
            int iCompareTo2 = k.compareTo((Comparable) ((zzama) this.zza[i3]).getKey());
            if (iCompareTo2 < 0) {
                i = i3 - 1;
            } else {
                if (iCompareTo2 <= 0) {
                    return i3;
                }
                i2 = i3 + 1;
            }
        }
        return -(i2 + 1);
    }

    public final int zza() {
        return this.zzb;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int i = this.zzb;
        int iHashCode = 0;
        for (int i2 = 0; i2 < i; i2++) {
            iHashCode += this.zza[i2].hashCode();
        }
        if (this.zzc.size() > 0) {
            return iHashCode + this.zzc.hashCode();
        }
        return iHashCode;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.zzb + this.zzc.size();
    }

    public final Iterable<Map.Entry<K, V>> zzb() {
        if (this.zzc.isEmpty()) {
            return Collections.emptySet();
        }
        return this.zzc.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iZza = zza(comparable);
        if (iZza >= 0) {
            return (V) ((zzama) this.zza[iZza]).getValue();
        }
        return this.zzc.get(comparable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final V put(K k, V v) {
        zzg();
        int iZza = zza(k);
        if (iZza >= 0) {
            return (V) ((zzama) this.zza[iZza]).setValue(v);
        }
        zzg();
        if (this.zza == null) {
            this.zza = new Object[16];
        }
        int i = -(iZza + 1);
        if (i >= 16) {
            return zzf().put(k, v);
        }
        if (this.zzb == 16) {
            zzama zzamaVar = (zzama) this.zza[15];
            this.zzb--;
            zzf().put((Comparable) zzamaVar.getKey(), zzamaVar.getValue());
        }
        System.arraycopy(this.zza, i, this.zza, i + 1, (this.zza.length - i) - 1);
        this.zza[i] = new zzama(this, k, v);
        this.zzb++;
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        zzg();
        Comparable comparable = (Comparable) obj;
        int iZza = zza(comparable);
        if (iZza >= 0) {
            return zzb(iZza);
        }
        if (this.zzc.isEmpty()) {
            return null;
        }
        return this.zzc.remove(comparable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final V zzb(int i) {
        zzg();
        V v = (V) ((zzama) this.zza[i]).getValue();
        System.arraycopy(this.zza, i + 1, this.zza, i, (this.zzb - i) - 1);
        this.zzb--;
        if (!this.zzc.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = zzf().entrySet().iterator();
            this.zza[this.zzb] = new zzama(this, it.next());
            this.zzb++;
            it.remove();
        }
        return v;
    }

    public final Map.Entry<K, V> zza(int i) {
        if (i >= this.zzb) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        return (zzama) this.zza[i];
    }

    final Set<Map.Entry<K, V>> zzc() {
        return new zzamb(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zze == null) {
            this.zze = new zzamc(this);
        }
        return this.zze;
    }

    private final SortedMap<K, V> zzf() {
        zzg();
        if (this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            this.zzc = new TreeMap();
            this.zzf = ((TreeMap) this.zzc).descendingMap();
        }
        return (SortedMap) this.zzc;
    }

    private zzalw() {
        this.zzc = Collections.emptyMap();
        this.zzf = Collections.emptyMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzg() {
        if (this.zzd) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        zzg();
        if (this.zzb != 0) {
            this.zza = null;
            this.zzb = 0;
        }
        if (!this.zzc.isEmpty()) {
            this.zzc.clear();
        }
    }

    public void zzd() {
        Map<K, V> mapUnmodifiableMap;
        Map<K, V> mapUnmodifiableMap2;
        if (!this.zzd) {
            if (this.zzc.isEmpty()) {
                mapUnmodifiableMap = Collections.emptyMap();
            } else {
                mapUnmodifiableMap = Collections.unmodifiableMap(this.zzc);
            }
            this.zzc = mapUnmodifiableMap;
            if (this.zzf.isEmpty()) {
                mapUnmodifiableMap2 = Collections.emptyMap();
            } else {
                mapUnmodifiableMap2 = Collections.unmodifiableMap(this.zzf);
            }
            this.zzf = mapUnmodifiableMap2;
            this.zzd = true;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzc.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzalw)) {
            return super.equals(obj);
        }
        zzalw zzalwVar = (zzalw) obj;
        int size = size();
        if (size != zzalwVar.size()) {
            return false;
        }
        int i = this.zzb;
        if (i != zzalwVar.zzb) {
            return entrySet().equals(zzalwVar.entrySet());
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (!zza(i2).equals(zzalwVar.zza(i2))) {
                return false;
            }
        }
        if (i == size) {
            return true;
        }
        return this.zzc.equals(zzalwVar.zzc);
    }

    public final boolean zze() {
        return this.zzd;
    }
}
