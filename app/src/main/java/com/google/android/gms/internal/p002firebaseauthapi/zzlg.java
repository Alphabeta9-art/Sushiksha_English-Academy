package com.google.android.gms.internal.p002firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzlg {
    private static final byte[] zza = new byte[0];
    private final zzlh zzb;
    private final BigInteger zzc;
    private final byte[] zzd;
    private final byte[] zze;
    private BigInteger zzf = BigInteger.ZERO;

    public static zzlg zza(byte[] bArr, zzln zzlnVar, zzlk zzlkVar, zzll zzllVar, zzlh zzlhVar, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArrZza = zzlkVar.zza(bArr, zzlnVar);
        byte[] bArr3 = zzlq.zza;
        byte[] bArrZza2 = zzlq.zza(zzlkVar.zza(), zzllVar.zzb(), zzlhVar.zzc());
        byte[] bArrZza3 = zzxv.zza(bArr3, zzllVar.zza(zzlq.zzl, zza, "psk_id_hash", bArrZza2), zzllVar.zza(zzlq.zzl, bArr2, "info_hash", bArrZza2));
        byte[] bArrZza4 = zzllVar.zza(bArrZza, zza, "secret", bArrZza2);
        byte[] bArrZza5 = zzllVar.zza(bArrZza4, bArrZza3, "key", bArrZza2, zzlhVar.zza());
        byte[] bArrZza6 = zzllVar.zza(bArrZza4, bArrZza3, "base_nonce", bArrZza2, zzlhVar.zzb());
        zzlhVar.zzb();
        return new zzlg(bArr, bArrZza5, bArrZza6, BigInteger.ONE.shiftLeft(96).subtract(BigInteger.ONE), zzlhVar);
    }

    private zzlg(byte[] bArr, byte[] bArr2, byte[] bArr3, BigInteger bigInteger, zzlh zzlhVar) {
        this.zzd = bArr2;
        this.zze = bArr3;
        this.zzc = bigInteger;
        this.zzb = zzlhVar;
    }

    private final synchronized byte[] zza() throws GeneralSecurityException {
        byte[] bArrZza;
        bArrZza = zzxv.zza(this.zze, zzmj.zza(this.zzf, this.zzb.zzb()));
        if (this.zzf.compareTo(this.zzc) >= 0) {
            throw new GeneralSecurityException("message limit reached");
        }
        this.zzf = this.zzf.add(BigInteger.ONE);
        return bArrZza;
    }

    final byte[] zza(byte[] bArr, int i, byte[] bArr2) throws GeneralSecurityException {
        return this.zzb.zza(this.zzd, zza(), bArr, i, bArr2);
    }
}
