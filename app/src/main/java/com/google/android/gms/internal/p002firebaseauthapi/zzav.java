package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzav<K, V> extends zzau<Map.Entry<K, V>> {
    private final transient zzap<K, V> zza;
    private final transient Object[] zzb;
    private final transient int zzc;

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzak
    final int zza(Object[] objArr, int i) {
        return zzc().zza(objArr, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzau
    final zzal<Map.Entry<K, V>> zzg() {
        return new zzay(this);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzak
    /* JADX INFO: renamed from: zzd */
    public final zzbc<Map.Entry<K, V>> iterator() {
        return (zzbc) zzc().iterator();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzau, com.google.android.gms.internal.p002firebaseauthapi.zzak, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    zzav(zzap<K, V> zzapVar, Object[] objArr, int i, int i2) {
        this.zza = zzapVar;
        this.zzb = objArr;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzak, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        return value != null && value.equals(this.zza.get(key));
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzak
    final boolean zze() {
        return true;
    }
}
