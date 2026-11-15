package com.google.android.gms.internal.p002firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzalt implements zzala {
    private final zzalc zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzala
    public final zzalc zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzala
    public final zzalo zzb() {
        if ((this.zzd & 1) != 0) {
            return zzalo.PROTO2;
        }
        return (this.zzd & 4) == 4 ? zzalo.EDITIONS : zzalo.PROTO3;
    }

    final String zzd() {
        return this.zzb;
    }

    zzalt(zzalc zzalcVar, String str, Object[] objArr) {
        this.zza = zzalcVar;
        this.zzb = str;
        this.zzc = objArr;
        char cCharAt = str.charAt(0);
        if (cCharAt < 55296) {
            this.zzd = cCharAt;
            return;
        }
        int i = cCharAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char cCharAt2 = str.charAt(i3);
            if (cCharAt2 >= 55296) {
                i |= (cCharAt2 & 8191) << i2;
                i2 += 13;
                i3 = i4;
            } else {
                this.zzd = i | (cCharAt2 << i2);
                return;
            }
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzala
    public final boolean zzc() {
        return (this.zzd & 2) == 2;
    }

    final Object[] zze() {
        return this.zzc;
    }
}
