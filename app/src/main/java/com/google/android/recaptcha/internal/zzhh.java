package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzhh extends zzhc {
    final /* synthetic */ Iterable zza;
    final /* synthetic */ int zzb;

    zzhh(Iterable iterable, int i) {
        this.zza = iterable;
        this.zzb = i;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        Iterable iterable = this.zza;
        if (iterable instanceof List) {
            List list = (List) iterable;
            return list.subList(Math.min(list.size(), this.zzb), list.size()).iterator();
        }
        int i = this.zzb;
        Iterator it = iterable.iterator();
        if (it == null) {
            throw null;
        }
        zzgx.zzb(i >= 0, "numberToAdvance must be nonnegative");
        for (int i2 = 0; i2 < i && it.hasNext(); i2++) {
            it.next();
        }
        return new zzhg(this, it);
    }
}
