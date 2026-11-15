package com.google.android.gms.internal.p002firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.util.Arrays;
import javax.crypto.KeyAgreement;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzyb {
    public static int zza(EllipticCurve ellipticCurve) throws GeneralSecurityException {
        return (zzmk.zza(ellipticCurve).subtract(BigInteger.ONE).bitLength() + 7) / 8;
    }

    private static BigInteger zza(BigInteger bigInteger, boolean z, EllipticCurve ellipticCurve) throws GeneralSecurityException {
        BigInteger bigIntegerModPow;
        BigInteger bigInteger2;
        BigInteger bigIntegerZza = zzmk.zza(ellipticCurve);
        BigInteger bigIntegerMod = bigInteger.multiply(bigInteger).add(ellipticCurve.getA()).multiply(bigInteger).add(ellipticCurve.getB()).mod(bigIntegerZza);
        if (bigIntegerZza.signum() != 1) {
            throw new InvalidAlgorithmParameterException("p must be positive");
        }
        BigInteger bigIntegerMod2 = bigIntegerMod.mod(bigIntegerZza);
        if (bigIntegerMod2.equals(BigInteger.ZERO)) {
            bigInteger2 = BigInteger.ZERO;
        } else {
            if (bigIntegerZza.testBit(0) && bigIntegerZza.testBit(1)) {
                bigIntegerModPow = bigIntegerMod2.modPow(bigIntegerZza.add(BigInteger.ONE).shiftRight(2), bigIntegerZza);
            } else if (bigIntegerZza.testBit(0) && !bigIntegerZza.testBit(1)) {
                BigInteger bigIntegerAdd = BigInteger.ONE;
                BigInteger bigIntegerShiftRight = bigIntegerZza.subtract(BigInteger.ONE).shiftRight(1);
                int i = 0;
                while (true) {
                    BigInteger bigIntegerMod3 = bigIntegerAdd.multiply(bigIntegerAdd).subtract(bigIntegerMod2).mod(bigIntegerZza);
                    if (bigIntegerMod3.equals(BigInteger.ZERO)) {
                        bigInteger2 = bigIntegerAdd;
                    } else {
                        BigInteger bigIntegerModPow2 = bigIntegerMod3.modPow(bigIntegerShiftRight, bigIntegerZza);
                        if (!bigIntegerModPow2.add(BigInteger.ONE).equals(bigIntegerZza)) {
                            if (!bigIntegerModPow2.equals(BigInteger.ONE)) {
                                throw new InvalidAlgorithmParameterException("p is not prime");
                            }
                            bigIntegerAdd = bigIntegerAdd.add(BigInteger.ONE);
                            i++;
                            if (i == 128 && !bigIntegerZza.isProbablePrime(80)) {
                                throw new InvalidAlgorithmParameterException("p is not prime");
                            }
                        } else {
                            BigInteger bigIntegerShiftRight2 = bigIntegerZza.add(BigInteger.ONE).shiftRight(1);
                            BigInteger bigIntegerMod4 = BigInteger.ONE;
                            BigInteger bigIntegerMod5 = bigIntegerAdd;
                            for (int iBitLength = bigIntegerShiftRight2.bitLength() - 2; iBitLength >= 0; iBitLength--) {
                                BigInteger bigIntegerMultiply = bigIntegerMod5.multiply(bigIntegerMod4);
                                bigIntegerMod5 = bigIntegerMod5.multiply(bigIntegerMod5).add(bigIntegerMod4.multiply(bigIntegerMod4).mod(bigIntegerZza).multiply(bigIntegerMod3)).mod(bigIntegerZza);
                                bigIntegerMod4 = bigIntegerMultiply.add(bigIntegerMultiply).mod(bigIntegerZza);
                                if (bigIntegerShiftRight2.testBit(iBitLength)) {
                                    BigInteger bigIntegerMod6 = bigIntegerMod5.multiply(bigIntegerAdd).add(bigIntegerMod4.multiply(bigIntegerMod3)).mod(bigIntegerZza);
                                    bigIntegerMod4 = bigIntegerAdd.multiply(bigIntegerMod4).add(bigIntegerMod5).mod(bigIntegerZza);
                                    bigIntegerMod5 = bigIntegerMod6;
                                }
                            }
                            bigIntegerModPow = bigIntegerMod5;
                        }
                    }
                }
            } else {
                bigIntegerModPow = null;
            }
            if (bigIntegerModPow != null && bigIntegerModPow.multiply(bigIntegerModPow).mod(bigIntegerZza).compareTo(bigIntegerMod2) != 0) {
                throw new GeneralSecurityException("Could not find a modular square root");
            }
            bigInteger2 = bigIntegerModPow;
        }
        if (z != bigInteger2.testBit(0)) {
            return bigIntegerZza.subtract(bigInteger2).mod(bigIntegerZza);
        }
        return bigInteger2;
    }

    public static KeyPair zza(ECParameterSpec eCParameterSpec) throws GeneralSecurityException {
        KeyPairGenerator keyPairGeneratorZza = zzyf.zzd.zza("EC");
        keyPairGeneratorZza.initialize(eCParameterSpec);
        return keyPairGeneratorZza.generateKeyPair();
    }

    public static ECPrivateKey zza(zzya zzyaVar, byte[] bArr) throws GeneralSecurityException {
        return (ECPrivateKey) zzyf.zze.zza("EC").generatePrivate(new ECPrivateKeySpec(zzmj.zza(bArr), zza(zzyaVar)));
    }

    public static ECPublicKey zza(zzya zzyaVar, zzyd zzydVar, byte[] bArr) throws GeneralSecurityException {
        return zza(zza(zzyaVar), zzydVar, bArr);
    }

    public static ECPublicKey zza(ECParameterSpec eCParameterSpec, zzyd zzydVar, byte[] bArr) throws GeneralSecurityException {
        return (ECPublicKey) zzyf.zze.zza("EC").generatePublic(new ECPublicKeySpec(zza(eCParameterSpec.getCurve(), zzydVar, bArr), eCParameterSpec));
    }

    public static ECParameterSpec zza(zzya zzyaVar) throws NoSuchAlgorithmException {
        switch (zzyaVar) {
            case NIST_P256:
                return zzmk.zza;
            case NIST_P384:
                return zzmk.zzb;
            case NIST_P521:
                return zzmk.zzc;
            default:
                throw new NoSuchAlgorithmException("curve not implemented:" + String.valueOf(zzyaVar));
        }
    }

    public static ECPoint zza(EllipticCurve ellipticCurve, zzyd zzydVar, byte[] bArr) throws GeneralSecurityException {
        int iZza = zza(ellipticCurve);
        boolean z = false;
        switch (zzydVar) {
            case UNCOMPRESSED:
                if (bArr.length != (iZza * 2) + 1) {
                    throw new GeneralSecurityException("invalid point size");
                }
                if (bArr[0] != 4) {
                    throw new GeneralSecurityException("invalid point format");
                }
                int i = iZza + 1;
                ECPoint eCPoint = new ECPoint(new BigInteger(1, Arrays.copyOfRange(bArr, 1, i)), new BigInteger(1, Arrays.copyOfRange(bArr, i, bArr.length)));
                zzmk.zza(eCPoint, ellipticCurve);
                return eCPoint;
            case COMPRESSED:
                BigInteger bigIntegerZza = zzmk.zza(ellipticCurve);
                if (bArr.length != iZza + 1) {
                    throw new GeneralSecurityException("compressed point has wrong length");
                }
                if (bArr[0] != 2) {
                    if (bArr[0] == 3) {
                        z = true;
                    } else {
                        throw new GeneralSecurityException("invalid format");
                    }
                }
                BigInteger bigInteger = new BigInteger(1, Arrays.copyOfRange(bArr, 1, bArr.length));
                if (bigInteger.signum() == -1 || bigInteger.compareTo(bigIntegerZza) >= 0) {
                    throw new GeneralSecurityException("x is out of range");
                }
                return new ECPoint(bigInteger, zza(bigInteger, z, ellipticCurve));
            case DO_NOT_USE_CRUNCHY_UNCOMPRESSED:
                if (bArr.length != iZza * 2) {
                    throw new GeneralSecurityException("invalid point size");
                }
                ECPoint eCPoint2 = new ECPoint(new BigInteger(1, Arrays.copyOf(bArr, iZza)), new BigInteger(1, Arrays.copyOfRange(bArr, iZza, bArr.length)));
                zzmk.zza(eCPoint2, ellipticCurve);
                return eCPoint2;
            default:
                throw new GeneralSecurityException("invalid format:" + String.valueOf(zzydVar));
        }
    }

    public static void zza(ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey) throws GeneralSecurityException {
        zzb(eCPublicKey, eCPrivateKey);
        zzmk.zza(eCPublicKey.getW(), eCPrivateKey.getParams().getCurve());
    }

    private static void zzb(ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey) throws GeneralSecurityException {
        try {
            if (!zzmk.zza(eCPublicKey.getParams(), eCPrivateKey.getParams())) {
                throw new GeneralSecurityException("invalid public key spec");
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new GeneralSecurityException(e);
        }
    }

    public static byte[] zza(ECPrivateKey eCPrivateKey, ECPublicKey eCPublicKey) throws GeneralSecurityException {
        zzb(eCPublicKey, eCPrivateKey);
        return zza(eCPrivateKey, eCPublicKey.getW());
    }

    private static byte[] zza(ECPrivateKey eCPrivateKey, ECPoint eCPoint) throws GeneralSecurityException {
        zzmk.zza(eCPoint, eCPrivateKey.getParams().getCurve());
        PublicKey publicKeyGeneratePublic = zzyf.zze.zza("EC").generatePublic(new ECPublicKeySpec(eCPoint, eCPrivateKey.getParams()));
        KeyAgreement keyAgreementZza = zzyf.zzc.zza("ECDH");
        keyAgreementZza.init(eCPrivateKey);
        try {
            keyAgreementZza.doPhase(publicKeyGeneratePublic, true);
            byte[] bArrGenerateSecret = keyAgreementZza.generateSecret();
            EllipticCurve curve = eCPrivateKey.getParams().getCurve();
            BigInteger bigInteger = new BigInteger(1, bArrGenerateSecret);
            if (bigInteger.signum() == -1 || bigInteger.compareTo(zzmk.zza(curve)) >= 0) {
                throw new GeneralSecurityException("shared secret is out of range");
            }
            zza(bigInteger, true, curve);
            return bArrGenerateSecret;
        } catch (IllegalStateException e) {
            throw new GeneralSecurityException(e);
        }
    }
}
