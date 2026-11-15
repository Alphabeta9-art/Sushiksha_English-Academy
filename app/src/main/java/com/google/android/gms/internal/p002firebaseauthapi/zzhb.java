package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
import javax.crypto.Cipher;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzhb extends ThreadLocal<Cipher> {
    @Override // java.lang.ThreadLocal
    @Nullable
    protected final /* synthetic */ Cipher initialValue() {
        return zza();
    }

    @Nullable
    private static Cipher zza() {
        try {
            Cipher cipherZza = zzyf.zza.zza("ChaCha20-Poly1305");
            if (zzhc.zzb(cipherZza)) {
                return cipherZza;
            }
            return null;
        } catch (GeneralSecurityException e) {
            return null;
        }
    }

    zzhb() {
    }
}
