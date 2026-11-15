package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzil {
    private static final Logger zza = Logger.getLogger(zzil.class.getName());
    private static final AtomicBoolean zzb = new AtomicBoolean(false);

    static Boolean zza() {
        try {
            return (Boolean) Class.forName("org.conscrypt.Conscrypt").getMethod("isBoringSslFIPSBuild", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            zza.logp(Level.INFO, "com.google.crypto.tink.config.internal.TinkFipsUtil", "checkConscryptIsAvailableAndUsesFipsBoringSsl", "Conscrypt is not available or does not support checking for FIPS build.");
            return false;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static abstract class zza {
        public static final zza zza;
        public static final zza zzb;
        private static final /* synthetic */ zza[] zzc;

        static {
            zzip zzipVar = null;
            zza = new zzin("ALGORITHM_NOT_FIPS");
            zzb = new zzim("ALGORITHM_REQUIRES_BORINGCRYPTO");
            zzc = new zza[]{zza, zzb};
        }

        public abstract boolean zza();

        private zza(String str, int i) {
            super(str, i);
        }

        public static zza[] values() {
            return (zza[]) zzc.clone();
        }
    }

    private zzil() {
    }

    public static boolean zzb() {
        return zzb.get();
    }
}
