package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public class zzey extends zzcu {
    private final zzfa zza;
    private final zzzc zzb;

    @Nullable
    private final Integer zzc;

    public static zzey zza(zzfa zzfaVar, @Nullable Integer num) throws GeneralSecurityException {
        zzzc zzzcVarZzb;
        if (zzfaVar.zzc() == zzfa.zzb.zzb) {
            if (num != null) {
                throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
            }
            zzzcVarZzb = zzog.zza;
        } else {
            if (zzfaVar.zzc() != zzfa.zzb.zza) {
                throw new GeneralSecurityException("Unknown Variant: " + String.valueOf(zzfaVar.zzc()));
            }
            if (num == null) {
                throw new GeneralSecurityException("For given Variant TINK the value of idRequirement must be non-null");
            }
            zzzcVarZzb = zzog.zzb(num.intValue());
        }
        return new zzey(zzfaVar, zzzcVarZzb, num);
    }

    public final zzfa zzb() {
        return this.zza;
    }

    public final zzzc zzc() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbp
    public final Integer zza() {
        return this.zzc;
    }

    private zzey(zzfa zzfaVar, zzzc zzzcVar, @Nullable Integer num) {
        this.zza = zzfaVar;
        this.zzb = zzzcVar;
        this.zzc = num;
    }
}
