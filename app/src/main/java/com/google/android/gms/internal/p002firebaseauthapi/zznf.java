package com.google.android.gms.internal.p002firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final /* synthetic */ class zznf {
    static final /* synthetic */ int[] zza = new int[zzvq.zzb.values().length];
    private static final /* synthetic */ int[] zzb;

    static {
        try {
            zza[zzvq.zzb.SYMMETRIC.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            zza[zzvq.zzb.ASYMMETRIC_PRIVATE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        zzb = new int[zzws.values().length];
        try {
            zzb[zzws.TINK.ordinal()] = 1;
        } catch (NoSuchFieldError e3) {
        }
        try {
            zzb[zzws.LEGACY.ordinal()] = 2;
        } catch (NoSuchFieldError e4) {
        }
        try {
            zzb[zzws.RAW.ordinal()] = 3;
        } catch (NoSuchFieldError e5) {
        }
        try {
            zzb[zzws.CRUNCHY.ordinal()] = 4;
        } catch (NoSuchFieldError e6) {
        }
    }
}
