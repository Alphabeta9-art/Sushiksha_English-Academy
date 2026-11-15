package com.google.android.gms.internal.p002firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzafi extends zzagy {
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final zzaga zzd;
    private final String zze;

    public final int hashCode() {
        return ((((((((this.zza.hashCode() ^ 1000003) * 1000003) ^ (this.zzb == null ? 0 : this.zzb.hashCode())) * 1000003) ^ this.zzc.hashCode()) * 1000003) ^ this.zzd.hashCode()) * 1000003) ^ this.zze.hashCode();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzagy
    public final zzaga zzb() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzagy
    public final String zzc() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzagy
    public final String zzd() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzagy
    public final String zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzagy
    public final String zzf() {
        return this.zzc;
    }

    public final String toString() {
        return "RevokeTokenRequest{providerId=" + this.zza + ", tenantId=" + this.zzb + ", token=" + this.zzc + ", tokenType=" + String.valueOf(this.zzd) + ", idToken=" + this.zze + "}";
    }

    private zzafi(String str, String str2, String str3, zzaga zzagaVar, String str4) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzagaVar;
        this.zze = str4;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzagy)) {
            return false;
        }
        zzagy zzagyVar = (zzagy) obj;
        return this.zza.equals(zzagyVar.zzd()) && (this.zzb != null ? this.zzb.equals(zzagyVar.zze()) : zzagyVar.zze() == null) && this.zzc.equals(zzagyVar.zzf()) && this.zzd.equals(zzagyVar.zzb()) && this.zze.equals(zzagyVar.zzc());
    }
}
