package com.google.android.gms.internal.p002firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzaff extends zzagt {
    private final String zza;
    private final String zzb;

    public final int hashCode() {
        return (((this.zza == null ? 0 : this.zza.hashCode()) ^ 1000003) * 1000003) ^ (this.zzb != null ? this.zzb.hashCode() : 0);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzagt
    final String zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzagt
    final String zzb() {
        return this.zza;
    }

    public final String toString() {
        return "RecaptchaEnforcementState{provider=" + this.zza + ", enforcementState=" + this.zzb + "}";
    }

    zzaff(String str, String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzagt)) {
            return false;
        }
        zzagt zzagtVar = (zzagt) obj;
        if (this.zza != null ? this.zza.equals(zzagtVar.zzb()) : zzagtVar.zzb() == null) {
            if (this.zzb != null ? this.zzb.equals(zzagtVar.zza()) : zzagtVar.zza() == null) {
                return true;
            }
        }
        return false;
    }
}
