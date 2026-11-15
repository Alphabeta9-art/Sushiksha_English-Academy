package com.google.android.recaptcha.internal;

import java.util.Collection;
import java.util.Queue;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public abstract class zzhf extends zzhd implements Queue {
    protected zzhf() {
    }

    @Override // java.util.Queue
    public final Object element() {
        return zzd().element();
    }

    public boolean offer(Object obj) {
        return zzd().offer(obj);
    }

    @Override // java.util.Queue
    @CheckForNull
    public final Object peek() {
        return zzd().peek();
    }

    @Override // java.util.Queue
    @CheckForNull
    public final Object poll() {
        return zzd().poll();
    }

    @Override // java.util.Queue
    public final Object remove() {
        return zzd().remove();
    }

    @Override // com.google.android.recaptcha.internal.zzhd
    protected /* bridge */ /* synthetic */ Collection zzc() {
        throw null;
    }

    protected abstract Queue zzd();
}
