package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Objects;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzba extends zzal<Object> {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc;

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzy.zza(i, this.zzc);
        return Objects.requireNonNull(this.zza[(i * 2) + this.zzb]);
    }

    zzba(Object[] objArr, int i, int i2) {
        this.zza = objArr;
        this.zzb = i;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzak
    final boolean zze() {
        return true;
    }
}
