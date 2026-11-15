package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzalq<E> extends zzaij<E> implements RandomAccess {
    private static final Object[] zza = new Object[0];
    private static final zzalq<Object> zzb = new zzalq<>(zza, 0, false);
    private E[] zzc;
    private int zzd;

    private static int zzb(int i) {
        return Math.max(((i * 3) / 2) + 1, 10);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzakc
    public final /* synthetic */ zzakc zza(int i) {
        if (i < this.zzd) {
            throw new IllegalArgumentException();
        }
        return new zzalq(i == 0 ? zza : Arrays.copyOf(this.zzc, i), this.zzd, true);
    }

    public static <E> zzalq<E> zzd() {
        return (zzalq<E>) zzb;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E get(int i) {
        zzd(i);
        return this.zzc[i];
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.List
    public final E remove(int i) {
        zza();
        zzd(i);
        E e = this.zzc[i];
        if (i < this.zzd - 1) {
            System.arraycopy(this.zzc, i + 1, this.zzc, i, (this.zzd - i) - 1);
        }
        this.zzd--;
        this.modCount++;
        return e;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.List
    public final E set(int i, E e) {
        zza();
        zzd(i);
        E e2 = this.zzc[i];
        this.zzc[i] = e;
        this.modCount++;
        return e2;
    }

    private final String zzc(int i) {
        return "Index:" + i + ", Size:" + this.zzd;
    }

    zzalq() {
        this(zza, 0, true);
    }

    private zzalq(E[] eArr, int i, boolean z) {
        super(z);
        this.zzc = eArr;
        this.zzd = i;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.List
    public final void add(int i, E e) {
        zza();
        if (i < 0 || i > this.zzd) {
            throw new IndexOutOfBoundsException(zzc(i));
        }
        if (this.zzd < this.zzc.length) {
            System.arraycopy(this.zzc, i, this.zzc, i + 1, this.zzd - i);
        } else {
            E[] eArr = (E[]) new Object[zzb(this.zzc.length)];
            System.arraycopy(this.zzc, 0, eArr, 0, i);
            System.arraycopy(this.zzc, i, eArr, i + 1, this.zzd - i);
            this.zzc = eArr;
        }
        this.zzc[i] = e;
        this.zzd++;
        this.modCount++;
    }

    private final void zzd(int i) {
        if (i < 0 || i >= this.zzd) {
            throw new IndexOutOfBoundsException(zzc(i));
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(E e) {
        zza();
        if (this.zzd == this.zzc.length) {
            this.zzc = (E[]) Arrays.copyOf(this.zzc, zzb(this.zzc.length));
        }
        E[] eArr = this.zzc;
        int i = this.zzd;
        this.zzd = i + 1;
        eArr[i] = e;
        this.modCount++;
        return true;
    }
}
