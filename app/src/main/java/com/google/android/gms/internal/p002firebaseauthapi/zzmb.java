package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzmb implements zzln {
    private final zzzc zza;
    private final zzzc zzb;

    static zzmb zza(byte[] bArr) throws GeneralSecurityException {
        return new zzmb(bArr, zzza.zza(bArr));
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzln
    public final zzzc zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzln
    public final zzzc zzb() {
        return this.zzb;
    }

    private zzmb(byte[] bArr, byte[] bArr2) {
        this.zza = zzzc.zza(bArr);
        this.zzb = zzzc.zza(bArr2);
    }
}
