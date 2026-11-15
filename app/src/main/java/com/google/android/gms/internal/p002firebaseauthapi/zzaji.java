package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzaji extends zzaij<Double> implements zzakc<Double>, RandomAccess {
    private static final double[] zza = new double[0];
    private double[] zzb;
    private int zzc;

    public final double zzb(int i) {
        zzf(i);
        return this.zzb[i];
    }

    private static int zzd(int i) {
        return Math.max(((i * 3) / 2) + 1, 10);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iZza = 1;
        for (int i = 0; i < this.zzc; i++) {
            iZza = (iZza * 31) + zzakb.zza(Double.doubleToLongBits(this.zzb[i]));
        }
        return iZza;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.zzb[i] == dDoubleValue) {
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
    public final /* synthetic */ zzakc<Double> zza(int i) {
        if (i < this.zzc) {
            throw new IllegalArgumentException();
        }
        return new zzaji(i == 0 ? zza : Arrays.copyOf(this.zzb, i), this.zzc, true);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return Double.valueOf(zzb(i));
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zza();
        zzf(i);
        double d = this.zzb[i];
        if (i < this.zzc - 1) {
            System.arraycopy(this.zzb, i + 1, this.zzb, i, (this.zzc - i) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Double.valueOf(d);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        double dDoubleValue = ((Double) obj).doubleValue();
        zza();
        zzf(i);
        double d = this.zzb[i];
        this.zzb[i] = dDoubleValue;
        return Double.valueOf(d);
    }

    private final String zze(int i) {
        return "Index:" + i + ", Size:" + this.zzc;
    }

    static {
        new zzaji(zza, 0, false);
    }

    zzaji() {
        this(zza, 0, true);
    }

    private zzaji(double[] dArr, int i, boolean z) {
        super(z);
        this.zzb = dArr;
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        double dDoubleValue = ((Double) obj).doubleValue();
        zza();
        if (i < 0 || i > this.zzc) {
            throw new IndexOutOfBoundsException(zze(i));
        }
        if (this.zzc < this.zzb.length) {
            System.arraycopy(this.zzb, i, this.zzb, i + 1, this.zzc - i);
        } else {
            double[] dArr = new double[zzd(this.zzb.length)];
            System.arraycopy(this.zzb, 0, dArr, 0, i);
            System.arraycopy(this.zzb, i, dArr, i + 1, this.zzc - i);
            this.zzb = dArr;
        }
        this.zzb[i] = dDoubleValue;
        this.zzc++;
        this.modCount++;
    }

    public final void zza(double d) {
        zza();
        if (this.zzc == this.zzb.length) {
            double[] dArr = new double[zzd(this.zzb.length)];
            System.arraycopy(this.zzb, 0, dArr, 0, this.zzc);
            this.zzb = dArr;
        }
        double[] dArr2 = this.zzb;
        int i = this.zzc;
        this.zzc = i + 1;
        dArr2[i] = d;
    }

    final void zzc(int i) {
        if (i <= this.zzb.length) {
            return;
        }
        if (this.zzb.length == 0) {
            this.zzb = new double[Math.max(i, 10)];
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
        zza(((Double) obj).doubleValue());
        return true;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaij, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Double> collection) {
        zza();
        zzakb.zza(collection);
        if (!(collection instanceof zzaji)) {
            return super.addAll(collection);
        }
        zzaji zzajiVar = (zzaji) collection;
        if (zzajiVar.zzc == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.zzc < zzajiVar.zzc) {
            throw new OutOfMemoryError();
        }
        int i = this.zzc + zzajiVar.zzc;
        if (i > this.zzb.length) {
            this.zzb = Arrays.copyOf(this.zzb, i);
        }
        System.arraycopy(zzajiVar.zzb, 0, this.zzb, this.zzc, zzajiVar.zzc);
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
        if (!(obj instanceof zzaji)) {
            return super.equals(obj);
        }
        zzaji zzajiVar = (zzaji) obj;
        if (this.zzc != zzajiVar.zzc) {
            return false;
        }
        double[] dArr = zzajiVar.zzb;
        for (int i = 0; i < this.zzc; i++) {
            if (Double.doubleToLongBits(this.zzb[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }
}
