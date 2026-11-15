package com.google.android.gms.internal.fido;

import java.util.Comparator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzcn {
    static final String zza = String.valueOf(zzcn.class.getName()).concat("$UnsafeComparator");
    static final Comparator zzb;

    static {
        Comparator comparator;
        try {
            Object[] enumConstants = Class.forName(zza).getEnumConstants();
            enumConstants.getClass();
            comparator = (Comparator) enumConstants[0];
        } catch (Throwable th) {
            comparator = zzcm.INSTANCE;
        }
        zzb = comparator;
    }

    zzcn() {
    }
}
