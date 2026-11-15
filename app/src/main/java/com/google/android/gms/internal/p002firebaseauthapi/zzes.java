package com.google.android.gms.internal.p002firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public class zzes extends zzcu {
    private final zzer zza;
    private final zzzc zzb;

    @Nullable
    private final Integer zzc;

    public static zzes zza(zzer zzerVar, @Nullable Integer num) throws GeneralSecurityException {
        zzzc zzzcVarZza;
        if (zzerVar.zzb() == zzer.zza.zza) {
            if (num == null) {
                throw new GeneralSecurityException("For given Variant TINK the value of idRequirement must be non-null");
            }
            zzzcVarZza = zzzc.zza(ByteBuffer.allocate(5).put((byte) 1).putInt(num.intValue()).array());
        } else {
            if (zzerVar.zzb() != zzer.zza.zzb) {
                throw new GeneralSecurityException("Unknown Variant: " + String.valueOf(zzerVar.zzb()));
            }
            if (num != null) {
                throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
            }
            zzzcVarZza = zzzc.zza(new byte[0]);
        }
        return new zzes(zzerVar, zzzcVarZza, num);
    }

    public final zzer zzb() {
        return this.zza;
    }

    public final zzzc zzc() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbp
    public final Integer zza() {
        return this.zzc;
    }

    private zzes(zzer zzerVar, zzzc zzzcVar, @Nullable Integer num) {
        this.zza = zzerVar;
        this.zzb = zzzcVar;
        this.zzc = num;
    }
}
