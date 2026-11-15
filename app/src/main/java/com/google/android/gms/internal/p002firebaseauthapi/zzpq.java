package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzpq extends zzqo {
    private final zzpx zza;
    private final zzze zzb;
    private final zzzc zzc;

    @Nullable
    private final Integer zzd;

    public static zza zzb() {
        return new zza();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static class zza {

        @Nullable
        private zzpx zza;

        @Nullable
        private zzze zzb;

        @Nullable
        private Integer zzc;

        public final zza zza(zzze zzzeVar) throws GeneralSecurityException {
            this.zzb = zzzeVar;
            return this;
        }

        public final zza zza(@Nullable Integer num) {
            this.zzc = num;
            return this;
        }

        public final zza zza(zzpx zzpxVar) {
            this.zza = zzpxVar;
            return this;
        }

        public final zzpq zza() throws GeneralSecurityException {
            zzzc zzzcVarZza;
            if (this.zza == null || this.zzb == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            }
            if (this.zza.zzc() != this.zzb.zza()) {
                throw new GeneralSecurityException("Key size mismatch");
            }
            if (this.zza.zza() && this.zzc == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            }
            if (!this.zza.zza() && this.zzc != null) {
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            if (this.zza.zze() == zzpx.zzb.zzd) {
                zzzcVarZza = zzog.zza;
            } else if (this.zza.zze() == zzpx.zzb.zzc || this.zza.zze() == zzpx.zzb.zzb) {
                zzzcVarZza = zzog.zza(this.zzc.intValue());
            } else if (this.zza.zze() == zzpx.zzb.zza) {
                zzzcVarZza = zzog.zzb(this.zzc.intValue());
            } else {
                throw new IllegalStateException("Unknown AesCmacParametersParameters.Variant: " + String.valueOf(this.zza.zze()));
            }
            return new zzpq(this.zza, this.zzb, zzzcVarZza, this.zzc);
        }

        private zza() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
        }
    }

    public final zzpx zzc() {
        return this.zza;
    }

    public final zzzc zzd() {
        return this.zzc;
    }

    public final zzze zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbp
    @Nullable
    public final Integer zza() {
        return this.zzd;
    }

    private zzpq(zzpx zzpxVar, zzze zzzeVar, zzzc zzzcVar, @Nullable Integer num) {
        this.zza = zzpxVar;
        this.zzb = zzzeVar;
        this.zzc = zzzcVar;
        this.zzd = num;
    }
}
