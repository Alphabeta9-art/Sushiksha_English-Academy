package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzhu extends zzhk implements Serializable {
    private final MessageDigest zza = zzc("SHA-256");
    private final int zzb = this.zza.getDigestLength();
    private final String zzd = "Hashing.sha256()";
    private final boolean zzc = zzd(this.zza);

    zzhu(String str, String str2) {
    }

    private static MessageDigest zzc(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    private static boolean zzd(MessageDigest messageDigest) {
        try {
            messageDigest.clone();
            return true;
        } catch (CloneNotSupportedException e) {
            return false;
        }
    }

    public final String toString() {
        return this.zzd;
    }

    @Override // com.google.android.recaptcha.internal.zzho
    public final zzhp zzb() {
        zzhs zzhsVar = null;
        if (this.zzc) {
            try {
                return new zzht((MessageDigest) this.zza.clone(), this.zzb, zzhsVar);
            } catch (CloneNotSupportedException e) {
            }
        }
        return new zzht(zzc(this.zza.getAlgorithm()), this.zzb, zzhsVar);
    }
}
