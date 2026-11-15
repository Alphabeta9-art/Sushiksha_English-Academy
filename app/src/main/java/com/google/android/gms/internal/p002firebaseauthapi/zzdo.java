package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzdo extends zzcu {
    private final zzdv zza;
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
        private zzdv zza;

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

        public final zza zza(zzdv zzdvVar) {
            this.zza = zzdvVar;
            return this;
        }

        public final zzdo zza() throws GeneralSecurityException {
            zzzc zzzcVarZzb;
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
            if (this.zza.zzf() == zzdv.zzb.zzc) {
                zzzcVarZzb = zzog.zza;
            } else if (this.zza.zzf() == zzdv.zzb.zzb) {
                zzzcVarZzb = zzog.zza(this.zzc.intValue());
            } else if (this.zza.zzf() == zzdv.zzb.zza) {
                zzzcVarZzb = zzog.zzb(this.zzc.intValue());
            } else {
                throw new IllegalStateException("Unknown AesGcmParameters.Variant: " + String.valueOf(this.zza.zzf()));
            }
            return new zzdo(this.zza, this.zzb, zzzcVarZzb, this.zzc);
        }

        private zza() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
        }
    }

    public final zzdv zzc() {
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

    private zzdo(zzdv zzdvVar, zzze zzzeVar, zzzc zzzcVar, @Nullable Integer num) {
        this.zza = zzdvVar;
        this.zzb = zzzeVar;
        this.zzc = zzzcVar;
        this.zzd = num;
    }
}
