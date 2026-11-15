package com.google.android.gms.internal.p002firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final /* synthetic */ class zzgf {
    static final /* synthetic */ int[] zza;
    static final /* synthetic */ int[] zzb = new int[zzur.values().length];

    static {
        try {
            zzb[zzur.SHA1.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            zzb[zzur.SHA224.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            zzb[zzur.SHA256.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            zzb[zzur.SHA384.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            zzb[zzur.SHA512.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        zza = new int[zzws.values().length];
        try {
            zza[zzws.TINK.ordinal()] = 1;
        } catch (NoSuchFieldError e6) {
        }
        try {
            zza[zzws.CRUNCHY.ordinal()] = 2;
        } catch (NoSuchFieldError e7) {
        }
        try {
            zza[zzws.LEGACY.ordinal()] = 3;
        } catch (NoSuchFieldError e8) {
        }
        try {
            zza[zzws.RAW.ordinal()] = 4;
        } catch (NoSuchFieldError e9) {
        }
    }
}
