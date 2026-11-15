package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzay<K, V> extends zzal<Map.Entry<K, V>> {
    private final /* synthetic */ zzav zza;

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzc;
    }

    @Override // java.util.List
    public final /* synthetic */ Object get(int i) {
        zzy.zza(i, this.zza.zzc);
        int i2 = i * 2;
        return new AbstractMap.SimpleImmutableEntry(Objects.requireNonNull(this.zza.zzb[i2]), Objects.requireNonNull(this.zza.zzb[i2 + 1]));
    }

    zzay(zzav zzavVar) {
        this.zza = zzavVar;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzak
    public final boolean zze() {
        return true;
    }
}
