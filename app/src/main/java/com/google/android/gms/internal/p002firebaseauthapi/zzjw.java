package com.google.android.gms.internal.p002firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECParameterSpec;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzjw extends zzko {
    private final zzke zza;
    private final zzze zzb;

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzko, com.google.android.gms.internal.p002firebaseauthapi.zzck
    public final /* synthetic */ zzbp zzb() {
        return (zzke) zzb();
    }

    public final zzju zzc() {
        return this.zza.zzb();
    }

    public static zzjw zza(zzke zzkeVar, zzze zzzeVar) throws GeneralSecurityException {
        ECParameterSpec eCParameterSpec;
        if (zzkeVar == null) {
            throw new GeneralSecurityException("HPKE private key cannot be constructed without an HPKE public key");
        }
        if (zzzeVar == null) {
            throw new GeneralSecurityException("HPKE private key cannot be constructed without secret");
        }
        zzju.zzf zzfVarZze = zzkeVar.zzb().zze();
        int iZza = zzzeVar.zza();
        String str = "Encoded private key byte length for " + String.valueOf(zzfVarZze) + " must be %d, not " + iZza;
        if (zzfVarZze == zzju.zzf.zza) {
            if (iZza != 32) {
                throw new GeneralSecurityException(String.format(str, 32));
            }
        } else if (zzfVarZze == zzju.zzf.zzb) {
            if (iZza != 48) {
                throw new GeneralSecurityException(String.format(str, 48));
            }
        } else if (zzfVarZze == zzju.zzf.zzc) {
            if (iZza != 66) {
                throw new GeneralSecurityException(String.format(str, 66));
            }
        } else if (zzfVarZze == zzju.zzf.zzd) {
            if (iZza != 32) {
                throw new GeneralSecurityException(String.format(str, 32));
            }
        } else {
            throw new GeneralSecurityException("Unable to validate private key length for " + String.valueOf(zzfVarZze));
        }
        zzju.zzf zzfVarZze2 = zzkeVar.zzb().zze();
        byte[] bArrZzb = zzkeVar.zzd().zzb();
        byte[] bArrZza = zzzeVar.zza(zzbq.zza());
        if (zzfVarZze2 == zzju.zzf.zza || zzfVarZze2 == zzju.zzf.zzb || zzfVarZze2 == zzju.zzf.zzc) {
            if (zzfVarZze2 == zzju.zzf.zza) {
                eCParameterSpec = zzmk.zza;
            } else if (zzfVarZze2 == zzju.zzf.zzb) {
                eCParameterSpec = zzmk.zzb;
            } else if (zzfVarZze2 == zzju.zzf.zzc) {
                eCParameterSpec = zzmk.zzc;
            } else {
                throw new IllegalArgumentException("Unable to determine NIST curve params for " + String.valueOf(zzfVarZze2));
            }
            BigInteger order = eCParameterSpec.getOrder();
            BigInteger bigIntegerZza = zzmj.zza(bArrZza);
            if (bigIntegerZza.signum() <= 0 || bigIntegerZza.compareTo(order) >= 0) {
                throw new GeneralSecurityException("Invalid private key.");
            }
            if (!zzmk.zza(bigIntegerZza, eCParameterSpec).equals(zzyb.zza(eCParameterSpec.getCurve(), zzyd.UNCOMPRESSED, bArrZzb))) {
                throw new GeneralSecurityException("Invalid private key for public key.");
            }
        } else if (zzfVarZze2 == zzju.zzf.zzd) {
            if (!Arrays.equals(zzza.zza(bArrZza), bArrZzb)) {
                throw new GeneralSecurityException("Invalid private key for public key.");
            }
        } else {
            throw new IllegalArgumentException("Unable to validate key pair for " + String.valueOf(zzfVarZze2));
        }
        return new zzjw(zzkeVar, zzzeVar);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzko
    /* JADX INFO: renamed from: zzd */
    public final /* synthetic */ zzkr zzb() {
        return this.zza;
    }

    public final zzze zze() {
        return this.zzb;
    }

    private zzjw(zzke zzkeVar, zzze zzzeVar) {
        this.zza = zzkeVar;
        this.zzb = zzzeVar;
    }
}
