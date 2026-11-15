package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzajx extends zzaij<Float> implements zzakc<Float>, RandomAccess {
    private static final float[] zza = new float[0];
    private float[] zzb;
    private int zzc;

    public final float zzb(int i) {
        zzf(i);
        return this.zzb[i];
    }

    private static int zzd(int i) {
        return Math.max(((i * 3) / 2) + 1, 10);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iFloatToIntBits = 1;
        for (int i = 0; i < this.zzc; i++) {
            iFloatToIntBits = (iFloatToIntBits * 31) + Float.floatToIntBits(this.zzb[i]);
        }
        return iFloatToIntBits;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        float fFloatValue = ((Float) obj).floatValue();
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.zzb[i] == fFloatValue) {
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
    public final /* synthetic */ zzakc<Float> zza(int i) {
        if (i < this.zzc) {
            throw new IllegalArgumentException();
        }
        return new zzajx(i == 0 ? zza : Arrays.copyOf(this.zzb, i), this.zzc, true);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return Float.valueOf(zzb(i));
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zza();
        zzf(i);
        float f = this.zzb[i];
        if (i < this.zzc - 1) {
            System.arraycopy(this.zzb, i + 1, this.zzb, i, (this.zzc - i) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Float.valueOf(f);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        float fFloatValue = ((Float) obj).floatValue();
        zza();
        zzf(i);
        float f = this.zzb[i];
        this.zzb[i] = fFloatValue;
        return Float.valueOf(f);
    }

    private final String zze(int i) {
        return "Index:" + i + ", Size:" + this.zzc;
    }

    static {
        new zzajx(zza, 0, false);
    }

    zzajx() {
        this(zza, 0, true);
    }

    private zzajx(float[] fArr, int i, boolean z) {
        super(z);
        this.zzb = fArr;
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        float fFloatValue = ((Float) obj).floatValue();
        zza();
        if (i < 0 || i > this.zzc) {
            throw new IndexOutOfBoundsException(zze(i));
        }
        if (this.zzc < this.zzb.length) {
            System.arraycopy(this.zzb, i, this.zzb, i + 1, this.zzc - i);
        } else {
            float[] fArr = new float[zzd(this.zzb.length)];
            System.arraycopy(this.zzb, 0, fArr, 0, i);
            System.arraycopy(this.zzb, i, fArr, i + 1, this.zzc - i);
            this.zzb = fArr;
        }
        this.zzb[i] = fFloatValue;
        this.zzc++;
        this.modCount++;
    }

    public final void zza(float f) {
        zza();
        if (this.zzc == this.zzb.length) {
            float[] fArr = new float[zzd(this.zzb.length)];
            System.arraycopy(this.zzb, 0, fArr, 0, this.zzc);
            this.zzb = fArr;
        }
        float[] fArr2 = this.zzb;
        int i = this.zzc;
        this.zzc = i + 1;
        fArr2[i] = f;
    }

    final void zzc(int i) {
        if (i <= this.zzb.length) {
            return;
        }
        if (this.zzb.length == 0) {
            this.zzb = new float[Math.max(i, 10)];
            return;
        }
        int length = this.zzb.length;
        while (length < i) {
            length = zzd(length);
        }
        this.zzb = Arrays.copyOf(this.zzb, length);
    }

    private final void zzf(int i) {
        if (i < 0 || i >= this.zzc) {
            throw new IndexOutOfBoundsException(zze(i));
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
        zza(((Float) obj).floatValue());
        return true;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Float> collection) {
        zza();
        zzakb.zza(collection);
        if (!(collection instanceof zzajx)) {
            return super.addAll(collection);
        }
        zzajx zzajxVar = (zzajx) collection;
        if (zzajxVar.zzc == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.zzc < zzajxVar.zzc) {
            throw new OutOfMemoryError();
        }
        int i = this.zzc + zzajxVar.zzc;
        if (i > this.zzb.length) {
            this.zzb = Arrays.copyOf(this.zzb, i);
        }
        System.arraycopy(zzajxVar.zzb, 0, this.zzb, this.zzc, zzajxVar.zzc);
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
        if (!(obj instanceof zzajx)) {
            return super.equals(obj);
        }
        zzajx zzajxVar = (zzajx) obj;
        if (this.zzc != zzajxVar.zzc) {
            return false;
        }
        float[] fArr = zzajxVar.zzb;
        for (int i = 0; i < this.zzc; i++) {
            if (Float.floatToIntBits(this.zzb[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }
}
