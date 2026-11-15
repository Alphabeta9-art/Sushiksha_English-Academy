package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzme {
    private static final zzmd zza;
    private static final zzmd zzb;

    static {
        zzmd zzmdVar;
        try {
            zzmdVar = (zzmd) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            zzmdVar = null;
        }
        zza = zzmdVar;
        zzb = new zzmd();
    }

    static zzmd zza() {
        return zza;
    }

    static zzmd zzb() {
        return zzb;
    }
}
