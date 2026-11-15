package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzio extends zzix {
    private final zziv zza;
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
        private zziv zza;

        @Nullable
        private zzze zzb;

        @Nullable
        private Integer zzc;

        public final zza zza(@Nullable Integer num) {
            this.zzc = num;
            return this;
        }

        public final zza zza(zzze zzzeVar) {
            this.zzb = zzzeVar;
            return this;
        }

        public final zza zza(zziv zzivVar) {
            this.zza = zzivVar;
            return this;
        }

        public final zzio zza() throws GeneralSecurityException {
            zzzc zzzcVarZzb;
            if (this.zza == null || this.zzb == null) {
                throw new IllegalArgumentException("Cannot build without parameters and/or key material");
            }
            if (this.zza.zzb() != this.zzb.zza()) {
                throw new GeneralSecurityException("Key size mismatch");
            }
            if (this.zza.zza() && this.zzc == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            }
            if (!this.zza.zza() && this.zzc != null) {
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            if (this.zza.zzd() == zziv.zzb.zzc) {
                zzzcVarZzb = zzog.zza;
            } else if (this.zza.zzd() == zziv.zzb.zzb) {
                zzzcVarZzb = zzog.zza(this.zzc.intValue());
            } else if (this.zza.zzd() == zziv.zzb.zza) {
                zzzcVarZzb = zzog.zzb(this.zzc.intValue());
            } else {
                throw new IllegalStateException("Unknown AesSivParameters.Variant: " + String.valueOf(this.zza.zzd()));
            }
            return new zzio(this.zza, this.zzb, zzzcVarZzb, this.zzc);
        }

        private zza() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
        }
    }

    public final zziv zzc() {
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

    private zzio(zziv zzivVar, zzze zzzeVar, zzzc zzzcVar, @Nullable Integer num) {
        this.zza = zzivVar;
        this.zzb = zzzeVar;
        this.zzc = zzzcVar;
        this.zzd = num;
    }
}
