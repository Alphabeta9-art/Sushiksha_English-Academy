package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.SecureRandom;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzpe {
    private static final ThreadLocal<SecureRandom> zza = new zzph();

    static /* synthetic */ SecureRandom zza() {
        SecureRandom secureRandomZzb = zzb();
        secureRandomZzb.nextLong();
        return secureRandomZzb;
    }

    private static SecureRandom zzb() {
        Provider providerZza = zzmi.zza();
        if (providerZza != null) {
            try {
                return SecureRandom.getInstance("SHA1PRNG", providerZza);
            } catch (GeneralSecurityException e) {
            }
        }
        Provider providerZzb = zzmi.zzb();
        if (providerZzb != null) {
            try {
                return SecureRandom.getInstance("SHA1PRNG", providerZzb);
            } catch (GeneralSecurityException e2) {
            }
        }
        return new SecureRandom();
    }

    public static byte[] zza(int i) {
        byte[] bArr = new byte[i];
        zza.get().nextBytes(bArr);
        return bArr;
    }
}
