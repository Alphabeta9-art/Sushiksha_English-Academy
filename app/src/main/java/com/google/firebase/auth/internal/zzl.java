package com.google.firebase.auth.internal;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes12.dex */
final class zzl extends zzh {
    private final String zza;
    private final String zzb;
    private final String zzc;

    public final int hashCode() {
        return (((((this.zza == null ? 0 : this.zza.hashCode()) ^ 1000003) * 1000003) ^ (this.zzb == null ? 0 : this.zzb.hashCode())) * 1000003) ^ (this.zzc != null ? this.zzc.hashCode() : 0);
    }

    @Override // com.google.firebase.auth.internal.zzh
    public final String zzb() {
        return this.zzb;
    }

    @Override // com.google.firebase.auth.internal.zzh
    public final String zzc() {
        return this.zzc;
    }

    @Override // com.google.firebase.auth.internal.zzh
    public final String zzd() {
        return this.zza;
    }

    public final String toString() {
        return "AttestationResult{recaptchaV2Token=" + this.zza + ", playIntegrityToken=" + this.zzb + ", recaptchaEnterpriseToken=" + this.zzc + "}";
    }

    private zzl(String str, String str2, String str3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzh)) {
            return false;
        }
        zzh zzhVar = (zzh) obj;
        if (this.zza != null ? this.zza.equals(zzhVar.zzd()) : zzhVar.zzd() == null) {
            if (this.zzb != null ? this.zzb.equals(zzhVar.zzb()) : zzhVar.zzb() == null) {
                if (this.zzc != null ? this.zzc.equals(zzhVar.zzc()) : zzhVar.zzc() == null) {
                    return true;
                }
            }
        }
        return false;
    }
}
