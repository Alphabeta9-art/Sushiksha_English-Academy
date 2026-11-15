package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzaja extends zzajb {
    private final byte[] zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;

    private final byte zzw() throws IOException {
        if (this.zzh == this.zzf) {
            throw zzakf.zzj();
        }
        byte[] bArr = this.zze;
        int i = this.zzh;
        this.zzh = i + 1;
        return bArr[i];
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final double zza() throws IOException {
        return Double.longBitsToDouble(zzz());
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final float zzb() throws IOException {
        return Float.intBitsToFloat(zzx());
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final int zzc() {
        return this.zzh - this.zzi;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final int zza(int i) throws zzakf {
        if (i < 0) {
            throw zzakf.zzf();
        }
        int iZzc = i + zzc();
        if (iZzc < 0) {
            throw zzakf.zzg();
        }
        int i2 = this.zzk;
        if (iZzc > i2) {
            throw zzakf.zzj();
        }
        this.zzk = iZzc;
        zzab();
        return i2;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final int zzd() throws IOException {
        return zzy();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final int zze() throws IOException {
        return zzx();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final int zzf() throws IOException {
        return zzy();
    }

    private final int zzx() throws IOException {
        int i = this.zzh;
        if (this.zzf - i < 4) {
            throw zzakf.zzj();
        }
        byte[] bArr = this.zze;
        this.zzh = i + 4;
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x006b A[PHI: r2
  0x006b: PHI (r2v7 int) = (r2v6 int), (r2v9 int), (r2v11 int) binds: [B:20:0x004a, B:24:0x0056, B:28:0x0062] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0068, code lost:
    
        if (r1[r2] < 0) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int zzy() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.zzh
            int r1 = r5.zzf
            if (r1 == r0) goto L6f
            byte[] r1 = r5.zze
            int r2 = r0 + 1
            r0 = r1[r0]
            if (r0 < 0) goto L11
            r5.zzh = r2
            return r0
        L11:
            int r3 = r5.zzf
            int r3 = r3 - r2
            r4 = 9
            if (r3 < r4) goto L6f
            int r3 = r2 + 1
            r2 = r1[r2]
            int r2 = r2 << 7
            r0 = r0 ^ r2
            if (r0 >= 0) goto L24
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            goto L6c
        L24:
            int r2 = r3 + 1
            r3 = r1[r3]
            int r3 = r3 << 14
            r0 = r0 ^ r3
            if (r0 < 0) goto L31
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            r3 = r2
            goto L6c
        L31:
            int r3 = r2 + 1
            r2 = r1[r2]
            int r2 = r2 << 21
            r0 = r0 ^ r2
            if (r0 >= 0) goto L3f
            r1 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r1
            goto L6c
        L3f:
            int r2 = r3 + 1
            r3 = r1[r3]
            int r4 = r3 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r3 >= 0) goto L6b
            int r3 = r2 + 1
            r2 = r1[r2]
            if (r2 >= 0) goto L6c
            int r2 = r3 + 1
            r3 = r1[r3]
            if (r3 >= 0) goto L6b
            int r3 = r2 + 1
            r2 = r1[r2]
            if (r2 >= 0) goto L6c
            int r2 = r3 + 1
            r3 = r1[r3]
            if (r3 >= 0) goto L6b
            int r3 = r2 + 1
            r1 = r1[r2]
            if (r1 >= 0) goto L6c
            goto L6f
        L6b:
            r3 = r2
        L6c:
            r5.zzh = r3
            return r0
        L6f:
            long r0 = r5.zzm()
            int r0 = (int) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzaja.zzy():int");
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final int zzg() throws IOException {
        return zzx();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final int zzh() throws IOException {
        return zze(zzy());
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final int zzi() throws IOException {
        if (zzt()) {
            this.zzj = 0;
            return 0;
        }
        this.zzj = zzy();
        if ((this.zzj >>> 3) == 0) {
            throw zzakf.zzc();
        }
        return this.zzj;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final int zzj() throws IOException {
        return zzy();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final long zzk() throws IOException {
        return zzz();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final long zzl() throws IOException {
        return zzaa();
    }

    private final long zzz() throws IOException {
        int i = this.zzh;
        if (this.zzf - i < 8) {
            throw zzakf.zzj();
        }
        byte[] bArr = this.zze;
        this.zzh = i + 8;
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    private final long zzaa() throws IOException {
        long j;
        int i = this.zzh;
        if (this.zzf != i) {
            byte[] bArr = this.zze;
            int i2 = i + 1;
            byte b = bArr[i];
            if (b >= 0) {
                this.zzh = i2;
                return b;
            }
            if (this.zzf - i2 >= 9) {
                int i3 = i2 + 1;
                int i4 = b ^ (bArr[i2] << 7);
                if (i4 < 0) {
                    j = i4 ^ (-128);
                } else {
                    int i5 = i3 + 1;
                    int i6 = i4 ^ (bArr[i3] << Ascii.SO);
                    if (i6 >= 0) {
                        j = i6 ^ 16256;
                        i3 = i5;
                    } else {
                        i3 = i5 + 1;
                        int i7 = i6 ^ (bArr[i5] << Ascii.NAK);
                        if (i7 < 0) {
                            j = i7 ^ (-2080896);
                        } else {
                            long j2 = i7;
                            int i8 = i3 + 1;
                            long j3 = j2 ^ (((long) bArr[i3]) << 28);
                            if (j3 >= 0) {
                                i3 = i8;
                                j = 266354560 ^ j3;
                            } else {
                                int i9 = i8 + 1;
                                long j4 = j3 ^ (((long) bArr[i8]) << 35);
                                if (j4 < 0) {
                                    j = (-34093383808L) ^ j4;
                                    i3 = i9;
                                } else {
                                    int i10 = i9 + 1;
                                    long j5 = j4 ^ (((long) bArr[i9]) << 42);
                                    if (j5 >= 0) {
                                        i3 = i10;
                                        j = 4363953127296L ^ j5;
                                    } else {
                                        int i11 = i10 + 1;
                                        long j6 = j5 ^ (((long) bArr[i10]) << 49);
                                        if (j6 < 0) {
                                            j = (-558586000294016L) ^ j6;
                                            i3 = i11;
                                        } else {
                                            int i12 = i11 + 1;
                                            long j7 = (j6 ^ (((long) bArr[i11]) << 56)) ^ 71499008037633920L;
                                            if (j7 >= 0) {
                                                i3 = i12;
                                                j = j7;
                                            } else {
                                                int i13 = i12 + 1;
                                                if (bArr[i12] >= 0) {
                                                    j = j7;
                                                    i3 = i13;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                this.zzh = i3;
                return j;
            }
        }
        return zzm();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    final long zzm() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte bZzw = zzw();
            j |= ((long) (bZzw & 127)) << i;
            if ((bZzw & 128) == 0) {
                return j;
            }
        }
        throw zzakf.zze();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final long zzn() throws IOException {
        return zzz();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final long zzo() throws IOException {
        return zza(zzaa());
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final long zzp() throws IOException {
        return zzaa();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final zzaip zzq() throws IOException {
        byte[] bArrCopyOfRange;
        int iZzy = zzy();
        if (iZzy > 0 && iZzy <= this.zzf - this.zzh) {
            zzaip zzaipVarZza = zzaip.zza(this.zze, this.zzh, iZzy);
            this.zzh += iZzy;
            return zzaipVarZza;
        }
        if (iZzy == 0) {
            return zzaip.zza;
        }
        if (iZzy > 0 && iZzy <= this.zzf - this.zzh) {
            int i = this.zzh;
            this.zzh += iZzy;
            bArrCopyOfRange = Arrays.copyOfRange(this.zze, i, this.zzh);
        } else if (iZzy <= 0) {
            if (iZzy == 0) {
                bArrCopyOfRange = zzakb.zzb;
            } else {
                throw zzakf.zzf();
            }
        } else {
            throw zzakf.zzj();
        }
        return zzaip.zzb(bArrCopyOfRange);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final String zzr() throws IOException {
        int iZzy = zzy();
        if (iZzy > 0 && iZzy <= this.zzf - this.zzh) {
            String str = new String(this.zze, this.zzh, iZzy, zzakb.zza);
            this.zzh += iZzy;
            return str;
        }
        if (iZzy == 0) {
            return "";
        }
        if (iZzy < 0) {
            throw zzakf.zzf();
        }
        throw zzakf.zzj();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final String zzs() throws IOException {
        int iZzy = zzy();
        if (iZzy > 0 && iZzy <= this.zzf - this.zzh) {
            String strZzb = zzamt.zzb(this.zze, this.zzh, iZzy);
            this.zzh += iZzy;
            return strZzb;
        }
        if (iZzy == 0) {
            return "";
        }
        if (iZzy <= 0) {
            throw zzakf.zzf();
        }
        throw zzakf.zzj();
    }

    private zzaja(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzk = Integer.MAX_VALUE;
        this.zze = bArr;
        this.zzf = i2 + i;
        this.zzh = i;
        this.zzi = this.zzh;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final void zzb(int i) throws zzakf {
        if (this.zzj != i) {
            throw zzakf.zzb();
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final void zzc(int i) {
        this.zzk = i;
        zzab();
    }

    private final void zzab() {
        this.zzf += this.zzg;
        int i = this.zzf - this.zzi;
        if (i > this.zzk) {
            this.zzg = i - this.zzk;
            this.zzf -= this.zzg;
        } else {
            this.zzg = 0;
        }
    }

    private final void zzf(int i) throws IOException {
        if (i >= 0 && i <= this.zzf - this.zzh) {
            this.zzh += i;
        } else {
            if (i < 0) {
                throw zzakf.zzf();
            }
            throw zzakf.zzj();
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final boolean zzt() throws IOException {
        return this.zzh == this.zzf;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final boolean zzu() throws IOException {
        return zzaa() != 0;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajb
    public final boolean zzd(int i) throws IOException {
        int i2 = 0;
        switch (i & 7) {
            case 0:
                if (this.zzf - this.zzh >= 10) {
                    while (i2 < 10) {
                        byte[] bArr = this.zze;
                        int i3 = this.zzh;
                        this.zzh = i3 + 1;
                        if (bArr[i3] < 0) {
                            i2++;
                        }
                    }
                    throw zzakf.zze();
                }
                while (i2 < 10) {
                    if (zzw() < 0) {
                        i2++;
                    }
                }
                throw zzakf.zze();
                return true;
            case 1:
                zzf(8);
                return true;
            case 2:
                zzf(zzy());
                return true;
            case 3:
                zzv();
                zzb(((i >>> 3) << 3) | 4);
                return true;
            case 4:
                return false;
            case 5:
                zzf(4);
                return true;
            default:
                throw zzakf.zza();
        }
    }
}
