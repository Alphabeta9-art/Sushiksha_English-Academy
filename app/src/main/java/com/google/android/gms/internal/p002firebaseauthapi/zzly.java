package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzly implements zzlk {
    private final zzle zza;

    zzly(zzle zzleVar) {
        this.zza = zzleVar;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzlk
    public final byte[] zza(byte[] bArr, zzln zzlnVar) throws GeneralSecurityException {
        return this.zza.zza(null, zzza.zza(zzlnVar.zza().zzb(), bArr), "eae_prk", zzxv.zza(bArr, zzlnVar.zzb().zzb()), "shared_secret", zzlq.zza(zzlq.zzb), this.zza.zza());
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzlk
    public final byte[] zza() throws GeneralSecurityException {
        if (Arrays.equals(this.zza.zzb(), zzlq.zzf)) {
            return zzlq.zzb;
        }
        throw new GeneralSecurityException("Could not determine HPKE KEM ID");
    }
}
