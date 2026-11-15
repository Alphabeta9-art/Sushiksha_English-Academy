package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzajz extends zzaij<Integer> implements zzakc<Integer>, RandomAccess {
    private static final int[] zza = new int[0];
    private int[] zzb;
    private int zzc;

    public final int zzb(int i) {
        zzg(i);
        return this.zzb[i];
    }

    private static int zze(int i) {
        return Math.max(((i * 3) / 2) + 1, 10);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.zzc; i2++) {
            i = (i * 31) + this.zzb[i2];
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int iIntValue = ((Integer) obj).intValue();
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.zzb[i] == iIntValue) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzakc
    public final /* synthetic */ zzakc<Integer> zza(int i) {
        if (i < this.zzc) {
            throw new IllegalArgumentException();
        }
        return new zzajz(i == 0 ? zza : Arrays.copyOf(this.zzb, i), this.zzc, true);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(zzb(i));
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zza();
        zzg(i);
        int i2 = this.zzb[i];
        if (i < this.zzc - 1) {
            System.arraycopy(this.zzb, i + 1, this.zzb, i, (this.zzc - i) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        int iIntValue = ((Integer) obj).intValue();
        zza();
        zzg(i);
        int i2 = this.zzb[i];
        this.zzb[i] = iIntValue;
        return Integer.valueOf(i2);
    }

    private final String zzf(int i) {
        return "Index:" + i + ", Size:" + this.zzc;
    }

    static {
        new zzajz(zza, 0, false);
    }

    zzajz() {
        this(zza, 0, true);
    }

    private zzajz(int[] iArr, int i, boolean z) {
        super(z);
        this.zzb = iArr;
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        int iIntValue = ((Integer) obj).intValue();
        zza();
        if (i < 0 || i > this.zzc) {
            throw new IndexOutOfBoundsException(zzf(i));
        }
        if (this.zzc < this.zzb.length) {
            System.arraycopy(this.zzb, i, this.zzb, i + 1, this.zzc - i);
        } else {
            int[] iArr = new int[zze(this.zzb.length)];
            System.arraycopy(this.zzb, 0, iArr, 0, i);
            System.arraycopy(this.zzb, i, iArr, i + 1, this.zzc - i);
            this.zzb = iArr;
        }
        this.zzb[i] = iIntValue;
        this.zzc++;
        this.modCount++;
    }

    public final void zzc(int i) {
        zza();
        if (this.zzc == this.zzb.length) {
            int[] iArr = new int[zze(this.zzb.length)];
            System.arraycopy(this.zzb, 0, iArr, 0, this.zzc);
            this.zzb = iArr;
        }
        int[] iArr2 = this.zzb;
        int i2 = this.zzc;
        this.zzc = i2 + 1;
        iArr2[i2] = i;
    }

    final void zzd(int i) {
        if (i <= this.zzb.length) {
            return;
        }
        if (this.zzb.length == 0) {
            this.zzb = new int[Math.max(i, 10)];
            return;
        }
        int length = this.zzb.length;
        while (length < i) {
            length = zze(length);
        }
        this.zzb = Arrays.copyOf(this.zzb, length);
    }

    private final void zzg(int i) {
        if (i < 0 || i >= this.zzc) {
            throw new IndexOutOfBoundsException(zzf(i));
        }
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        zza();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzb, i2, this.zzb, i, this.zzc - i2);
        this.zzc -= i2 - i;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        zzc(((Integer) obj).intValue());
        return true;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Integer> collection) {
        zza();
        zzakb.zza(collection);
        if (!(collection instanceof zzajz)) {
            return super.addAll(collection);
        }
        zzajz zzajzVar = (zzajz) collection;
        if (zzajzVar.zzc == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.zzc < zzajzVar.zzc) {
            throw new OutOfMemoryError();
        }
        int i = this.zzc + zzajzVar.zzc;
        if (i > this.zzb.length) {
            this.zzb = Arrays.copyOf(this.zzb, i);
        }
        System.arraycopy(zzajzVar.zzb, 0, this.zzb, this.zzc, zzajzVar.zzc);
        this.zzc = i;
        this.modCount++;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzajz)) {
            return super.equals(obj);
        }
        zzajz zzajzVar = (zzajz) obj;
        if (this.zzc != zzajzVar.zzc) {
            return false;
        }
        int[] iArr = zzajzVar.zzb;
        for (int i = 0; i < this.zzc; i++) {
            if (this.zzb[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }
}
