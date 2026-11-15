package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: Add missing generic type declarations: [E] */
/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzaq<E> extends zzal<E> {
    private final transient int zza;
    private final transient int zzb;
    private final /* synthetic */ zzal zzc;

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzak
    final int zza() {
        return this.zzc.zzb() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzak
    final int zzb() {
        return this.zzc.zzb() + this.zza;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzal
    /* JADX INFO: renamed from: zza */
    public final zzal<E> subList(int i, int i2) {
        zzy.zza(i, i2, this.zzb);
        return (zzal) this.zzc.subList(i + this.zza, i2 + this.zza);
    }

    @Override // java.util.List
    public final E get(int i) {
        zzy.zza(i, this.zzb);
        return this.zzc.get(i + this.zza);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzal, java.util.List
    public final /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    zzaq(zzal zzalVar, int i, int i2) {
        this.zzc = zzalVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzak
    final boolean zze() {
        return true;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzak
    @CheckForNull
    final Object[] zzf() {
        return this.zzc.zzf();
    }
}
