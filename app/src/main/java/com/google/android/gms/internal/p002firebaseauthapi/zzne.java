package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Objects;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzne extends zzch {
    private final zzpf zza;

    public final int hashCode() {
        return Objects.hash(this.zza.zza(), this.zza.zzc());
    }

    public final zzpf zzb() {
        return this.zza;
    }

    public final String toString() {
        String str;
        String strZzf = this.zza.zza().zzf();
        switch (this.zza.zza().zzd()) {
            case TINK:
                str = "TINK";
                break;
            case LEGACY:
                str = "LEGACY";
                break;
            case RAW:
                str = "RAW";
                break;
            case CRUNCHY:
                str = "CRUNCHY";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        return String.format("(typeUrl=%s, outputPrefixType=%s)", strZzf, str);
    }

    public zzne(zzpf zzpfVar) {
        this.zza = zzpfVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzne)) {
            return false;
        }
        zzpf zzpfVar = ((zzne) obj).zza;
        return this.zza.zza().zzd().equals(zzpfVar.zza().zzd()) && this.zza.zza().zzf().equals(zzpfVar.zza().zzf()) && this.zza.zza().zze().equals(zzpfVar.zza().zze());
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzch
    public final boolean zza() {
        return this.zza.zza().zzd() != zzws.RAW;
    }
}
