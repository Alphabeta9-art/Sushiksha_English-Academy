package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzqs implements zzci {
    private final zzoz<zzci> zza;
    private final zznk zzb;
    private final zznk zzc;

    private zzqs(zzoz<zzci> zzozVar) {
        this.zza = zzozVar;
        if (!zzozVar.zzf()) {
            this.zzb = zznr.zza;
            this.zzc = zznr.zza;
        } else {
            zznl zznlVarZza = zzny.zzb().zza();
            zznn zznnVarZza = zznr.zza(zzozVar);
            this.zzb = zznlVarZza.zza(zznnVarZza, "mac", "compute");
            this.zzc = zznlVarZza.zza(zznnVarZza, "mac", "verify");
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzci
    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length <= 5) {
            this.zzc.zza();
            throw new GeneralSecurityException("tag too short");
        }
        for (zzpb<zzci> zzpbVar : this.zza.zza(Arrays.copyOf(bArr, 5))) {
            try {
                zzpbVar.zzd().zza(bArr, bArr2);
                this.zzc.zza(zzpbVar.zza(), bArr2.length);
                return;
            } catch (GeneralSecurityException e) {
            }
        }
        for (zzpb<zzci> zzpbVar2 : this.zza.zze()) {
            try {
                zzpbVar2.zzd().zza(bArr, bArr2);
                this.zzc.zza(zzpbVar2.zza(), bArr2.length);
                return;
            } catch (GeneralSecurityException e2) {
            }
        }
        this.zzc.zza();
        throw new GeneralSecurityException("invalid MAC");
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzci
    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        try {
            byte[] bArrZza = this.zza.zzb().zzd().zza(bArr);
            this.zzb.zza(this.zza.zzb().zza(), bArr.length);
            return bArrZza;
        } catch (GeneralSecurityException e) {
            this.zzb.zza();
            throw e;
        }
    }
}
