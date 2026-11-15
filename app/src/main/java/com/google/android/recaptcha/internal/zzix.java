package com.google.android.recaptcha.internal;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzix extends zzjb {
    private final byte[] zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;

    /* synthetic */ zzix(byte[] bArr, int i, int i2, boolean z, zziw zziwVar) {
        super(null);
        this.zzj = Integer.MAX_VALUE;
        this.zze = bArr;
        this.zzf = 0;
        this.zzh = 0;
    }

    private final void zzI() {
        int i = this.zzf + this.zzg;
        this.zzf = i;
        int i2 = this.zzj;
        if (i <= i2) {
            this.zzg = 0;
            return;
        }
        int i3 = i - i2;
        this.zzg = i3;
        this.zzf = i - i3;
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final void zzA(int i) {
        this.zzj = i;
        zzI();
    }

    public final void zzB(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.zzf;
            int i3 = this.zzh;
            if (i <= i2 - i3) {
                this.zzh = i3 + i;
                return;
            }
        }
        if (i >= 0) {
            throw zzlc.zzi();
        }
        throw zzlc.zzf();
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final boolean zzC() throws IOException {
        return this.zzh == this.zzf;
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final boolean zzD() throws IOException {
        return zzr() != 0;
    }

    public final byte zza() throws IOException {
        int i = this.zzh;
        if (i == this.zzf) {
            throw zzlc.zzi();
        }
        byte[] bArr = this.zze;
        this.zzh = i + 1;
        return bArr[i];
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final double zzb() throws IOException {
        return Double.longBitsToDouble(zzq());
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final float zzc() throws IOException {
        return Float.intBitsToFloat(zzi());
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final int zzd() {
        return this.zzh;
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final int zze(int i) throws zzlc {
        if (i < 0) {
            throw zzlc.zzf();
        }
        int i2 = i + this.zzh;
        if (i2 < 0) {
            throw zzlc.zzg();
        }
        int i3 = this.zzj;
        if (i2 > i3) {
            throw zzlc.zzi();
        }
        this.zzj = i2;
        zzI();
        return i3;
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final int zzf() throws IOException {
        return zzj();
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final int zzg() throws IOException {
        return zzi();
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final int zzh() throws IOException {
        return zzj();
    }

    public final int zzi() throws IOException {
        int i = this.zzh;
        if (this.zzf - i < 4) {
            throw zzlc.zzi();
        }
        byte[] bArr = this.zze;
        this.zzh = i + 4;
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24);
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final int zzk() throws IOException {
        return zzi();
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final int zzl() throws IOException {
        return zzF(zzj());
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final int zzm() throws IOException {
        if (zzC()) {
            this.zzi = 0;
            return 0;
        }
        int iZzj = zzj();
        this.zzi = iZzj;
        if ((iZzj >>> 3) != 0) {
            return iZzj;
        }
        throw zzlc.zzc();
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final int zzn() throws IOException {
        return zzj();
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final long zzo() throws IOException {
        return zzq();
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final long zzp() throws IOException {
        return zzr();
    }

    public final long zzq() throws IOException {
        int i = this.zzh;
        if (this.zzf - i < 8) {
            throw zzlc.zzi();
        }
        byte[] bArr = this.zze;
        this.zzh = i + 8;
        return (bArr[i] & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    final long zzs() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte bZza = zza();
            j |= ((long) (bZza & 127)) << i;
            if ((bZza & 128) == 0) {
                return j;
            }
        }
        throw zzlc.zze();
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final long zzt() throws IOException {
        return zzq();
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final long zzu() throws IOException {
        return zzG(zzr());
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final long zzv() throws IOException {
        return zzr();
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final zziv zzw() throws IOException {
        int iZzj = zzj();
        if (iZzj > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (iZzj <= i - i2) {
                zziv zzivVarZzk = zziv.zzk(this.zze, i2, iZzj);
                this.zzh += iZzj;
                return zzivVarZzk;
            }
        }
        if (iZzj == 0) {
            return zziv.zzb;
        }
        if (iZzj > 0) {
            int i3 = this.zzf;
            int i4 = this.zzh;
            if (iZzj <= i3 - i4) {
                int i5 = iZzj + i4;
                this.zzh = i5;
                return new zziu(Arrays.copyOfRange(this.zze, i4, i5));
            }
        }
        if (iZzj <= 0) {
            throw zzlc.zzf();
        }
        throw zzlc.zzi();
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final String zzx() throws IOException {
        int iZzj = zzj();
        if (iZzj > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (iZzj <= i - i2) {
                String str = new String(this.zze, i2, iZzj, zzla.zza);
                this.zzh += iZzj;
                return str;
            }
        }
        if (iZzj == 0) {
            return "";
        }
        if (iZzj < 0) {
            throw zzlc.zzf();
        }
        throw zzlc.zzi();
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final String zzy() throws IOException {
        int iZzj = zzj();
        if (iZzj > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (iZzj <= i - i2) {
                String strZzd = zznl.zzd(this.zze, i2, iZzj);
                this.zzh += iZzj;
                return strZzd;
            }
        }
        if (iZzj == 0) {
            return "";
        }
        if (iZzj <= 0) {
            throw zzlc.zzf();
        }
        throw zzlc.zzi();
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final void zzz(int i) throws zzlc {
        if (this.zzi != i) {
            throw zzlc.zzb();
        }
    }

    @Override // com.google.android.recaptcha.internal.zzjb
    public final boolean zzE(int i) throws IOException {
        int iZzm;
        int i2 = 0;
        switch (i & 7) {
            case 0:
                if (this.zzf - this.zzh < 10) {
                    while (i2 < 10) {
                        if (zza() < 0) {
                            i2++;
                        }
                    }
                    throw zzlc.zze();
                }
                while (i2 < 10) {
                    byte[] bArr = this.zze;
                    int i3 = this.zzh;
                    this.zzh = i3 + 1;
                    if (bArr[i3] < 0) {
                        i2++;
                    }
                }
                throw zzlc.zze();
                return true;
            case 1:
                zzB(8);
                return true;
            case 2:
                zzB(zzj());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzB(4);
                return true;
            default:
                throw zzlc.zza();
        }
        do {
            iZzm = zzm();
            if (iZzm != 0) {
            }
            zzz(((i >>> 3) << 3) | 4);
            return true;
        } while (zzE(iZzm));
        zzz(((i >>> 3) << 3) | 4);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0069 A[PHI: r3
  0x0069: PHI (r3v7 int) = (r3v6 int), (r3v9 int), (r3v11 int) binds: [B:20:0x0048, B:24:0x0054, B:28:0x0060] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0066, code lost:
    
        if (r2[r3] < 0) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zzj() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.zzh
            int r1 = r5.zzf
            if (r1 == r0) goto L6d
            byte[] r2 = r5.zze
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L11
            r5.zzh = r3
            return r0
        L11:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L6d
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L22
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            goto L6a
        L22:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L2f
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            r1 = r3
            goto L6a
        L2f:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L3d
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L6a
        L3d:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L69
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L6a
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L69
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L6a
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L69
            int r1 = r3 + 1
            r2 = r2[r3]
            if (r2 >= 0) goto L6a
            goto L6d
        L69:
            r1 = r3
        L6a:
            r5.zzh = r1
            return r0
        L6d:
            long r0 = r5.zzs()
            int r0 = (int) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzix.zzj():int");
    }

    public final long zzr() throws IOException {
        long j;
        int i = this.zzh;
        int i2 = this.zzf;
        if (i2 != i) {
            byte[] bArr = this.zze;
            int i3 = i + 1;
            byte b = bArr[i];
            if (b >= 0) {
                this.zzh = i3;
                return b;
            }
            if (i2 - i3 >= 9) {
                int i4 = i3 + 1;
                int i5 = b ^ (bArr[i3] << 7);
                if (i5 < 0) {
                    j = i5 ^ (-128);
                } else {
                    int i6 = i4 + 1;
                    int i7 = i5 ^ (bArr[i4] << Ascii.SO);
                    if (i7 >= 0) {
                        i4 = i6;
                        j = i7 ^ 16256;
                    } else {
                        i4 = i6 + 1;
                        int i8 = i7 ^ (bArr[i6] << Ascii.NAK);
                        if (i8 < 0) {
                            j = i8 ^ (-2080896);
                        } else {
                            int i9 = i4 + 1;
                            long j2 = ((long) i8) ^ (((long) bArr[i4]) << 28);
                            if (j2 >= 0) {
                                i4 = i9;
                                j = j2 ^ 266354560;
                            } else {
                                int i10 = i9 + 1;
                                long j3 = j2 ^ (((long) bArr[i9]) << 35);
                                if (j3 < 0) {
                                    j = (-34093383808L) ^ j3;
                                    i4 = i10;
                                } else {
                                    int i11 = i10 + 1;
                                    long j4 = j3 ^ (((long) bArr[i10]) << 42);
                                    if (j4 >= 0) {
                                        i4 = i11;
                                        j = j4 ^ 4363953127296L;
                                    } else {
                                        int i12 = i11 + 1;
                                        long j5 = j4 ^ (((long) bArr[i11]) << 49);
                                        if (j5 < 0) {
                                            j = (-558586000294016L) ^ j5;
                                            i4 = i12;
                                        } else {
                                            int i13 = i12 + 1;
                                            long j6 = (j5 ^ (((long) bArr[i12]) << 56)) ^ 71499008037633920L;
                                            if (j6 < 0) {
                                                int i14 = i13 + 1;
                                                if (bArr[i13] >= 0) {
                                                    j = j6;
                                                    i4 = i14;
                                                }
                                            } else {
                                                i4 = i13;
                                                j = j6;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                this.zzh = i4;
                return j;
            }
        }
        return zzs();
    }
}
