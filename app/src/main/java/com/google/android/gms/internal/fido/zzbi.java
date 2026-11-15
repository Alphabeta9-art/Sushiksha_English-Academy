package com.google.android.gms.internal.fido;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public abstract class zzbi extends zzbj implements NavigableSet, zzbz {
    final transient Comparator zza;

    @CheckForNull
    transient zzbi zzb;

    zzbi(Comparator comparator) {
        this.zza = comparator;
    }

    static zzbu zzq(Comparator comparator) {
        if (zzbp.zza.equals(comparator)) {
            return zzbu.zzc;
        }
        int i = zzaz.zzd;
        return new zzbu(zzbs.zza, comparator);
    }

    @Override // java.util.SortedSet, com.google.android.gms.internal.fido.zzbz
    public final Comparator comparator() {
        return this.zza;
    }

    @Override // java.util.SortedSet
    public Object first() {
        return iterator().next();
    }

    @Override // java.util.SortedSet
    public Object last() {
        return descendingIterator().next();
    }

    @Override // java.util.NavigableSet
    @CheckForNull
    @Deprecated
    public final Object pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet
    @CheckForNull
    @Deprecated
    public final Object pollLast() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final /* bridge */ /* synthetic */ SortedSet subSet(Object obj, Object obj2) {
        return subSet(obj, true, obj2, false);
    }

    @Override // com.google.android.gms.internal.fido.zzbc, com.google.android.gms.internal.fido.zzav, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* JADX INFO: renamed from: zzd */
    public abstract zzcb iterator();

    abstract zzbi zzf();

    @Override // java.util.NavigableSet
    /* JADX INFO: renamed from: zzl, reason: merged with bridge method [inline-methods] */
    public final zzbi descendingSet() {
        zzbi zzbiVar = this.zzb;
        if (zzbiVar != null) {
            return zzbiVar;
        }
        zzbi zzbiVarZzf = zzf();
        this.zzb = zzbiVarZzf;
        zzbiVarZzf.zzb = this;
        return zzbiVarZzf;
    }

    abstract zzbi zzm(Object obj, boolean z);

    @Override // java.util.NavigableSet
    /* JADX INFO: renamed from: zzn, reason: merged with bridge method [inline-methods] */
    public final zzbi subSet(Object obj, boolean z, Object obj2, boolean z2) {
        if (obj == null || obj2 == null) {
            throw null;
        }
        zzap.zzc(this.zza.compare(obj, obj2) <= 0);
        return zzo(obj, z, obj2, z2);
    }

    abstract zzbi zzo(Object obj, boolean z, Object obj2, boolean z2);

    abstract zzbi zzp(Object obj, boolean z);

    @Override // java.util.NavigableSet
    /* JADX INFO: renamed from: zzr, reason: merged with bridge method [inline-methods] */
    public abstract zzcb descendingIterator();

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final /* synthetic */ SortedSet headSet(Object obj) {
        if (obj != null) {
            return zzm(obj, false);
        }
        throw null;
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final /* synthetic */ SortedSet tailSet(Object obj) {
        if (obj != null) {
            return zzp(obj, true);
        }
        throw null;
    }

    @Override // java.util.NavigableSet
    @CheckForNull
    public Object ceiling(Object obj) {
        if (obj != null) {
            return zzbk.zza(zzp(obj, true), null);
        }
        throw null;
    }

    @Override // java.util.NavigableSet
    @CheckForNull
    public Object floor(Object obj) {
        if (obj != null) {
            return zzbm.zza(zzm(obj, true).descendingIterator(), null);
        }
        throw null;
    }

    @Override // java.util.NavigableSet
    @CheckForNull
    public Object higher(Object obj) {
        if (obj != null) {
            return zzbk.zza(zzp(obj, false), null);
        }
        throw null;
    }

    @Override // java.util.NavigableSet
    @CheckForNull
    public Object lower(Object obj) {
        if (obj != null) {
            return zzbm.zza(zzm(obj, false).descendingIterator(), null);
        }
        throw null;
    }

    @Override // java.util.NavigableSet
    public final /* synthetic */ NavigableSet headSet(Object obj, boolean z) {
        if (obj != null) {
            return zzm(obj, z);
        }
        throw null;
    }

    @Override // java.util.NavigableSet
    public final /* synthetic */ NavigableSet tailSet(Object obj, boolean z) {
        if (obj != null) {
            return zzp(obj, z);
        }
        throw null;
    }
}
