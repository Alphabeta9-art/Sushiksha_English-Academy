package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzbu {

    @Nullable
    private final zzch zza;

    public static zzbu zza(zzch zzchVar) throws GeneralSecurityException {
        return new zzbu(zzchVar);
    }

    public final zzch zza() throws GeneralSecurityException {
        return this.zza != null ? this.zza : zzcp.zza(zzb().zzk());
    }

    private final zzvu zzb() {
        try {
            if (this.zza instanceof zzne) {
                return ((zzne) this.zza).zzb().zza();
            }
            return ((zzpf) zzof.zza().zza(this.zza, zzpf.class)).zza();
        } catch (GeneralSecurityException e) {
            throw new zzpp("Parsing parameters failed in getProto(). You probably want to call some Tink register function for " + String.valueOf(this.zza), e);
        }
    }

    private zzbu(zzch zzchVar) {
        this.zza = zzchVar;
    }
}
