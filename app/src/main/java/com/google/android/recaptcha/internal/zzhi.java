package com.google.android.recaptcha.internal;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzhi {
    public static boolean zza(Collection collection, Iterator it) {
        if (it == null) {
            throw null;
        }
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= collection.add(it.next());
        }
        return zAdd;
    }
}
