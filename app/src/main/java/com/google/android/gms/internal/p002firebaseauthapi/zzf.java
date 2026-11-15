package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
abstract class zzf<T> implements Iterator<T> {
    private int zza = 2;

    @CheckForNull
    private T zzb;

    @CheckForNull
    protected abstract T zza();

    @CheckForNull
    protected final T zzb() {
        this.zza = 3;
        return null;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.zza = 2;
        T t = this.zzb;
        this.zzb = null;
        return t;
    }

    protected zzf() {
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (!(this.zza != 4)) {
            throw new IllegalStateException();
        }
        switch (this.zza - 1) {
            case 0:
                return true;
            case 1:
            default:
                this.zza = 4;
                this.zzb = zza();
                if (this.zza == 3) {
                    return false;
                }
                this.zza = 1;
                return true;
            case 2:
                return false;
        }
    }
}
