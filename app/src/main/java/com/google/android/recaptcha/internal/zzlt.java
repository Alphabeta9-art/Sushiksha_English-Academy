package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzlt {
    private static final zzls zza;
    private static final zzls zzb;

    static {
        zzls zzlsVar;
        try {
            zzlsVar = (zzls) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            zzlsVar = null;
        }
        zza = zzlsVar;
        zzb = new zzls();
    }

    static zzls zza() {
        return zza;
    }

    static zzls zzb() {
        return zzb;
    }
}
