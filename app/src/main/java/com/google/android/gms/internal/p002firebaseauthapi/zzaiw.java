package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
class zzaiw extends zzaix {
    protected final byte[] zzb;

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaip
    public byte zza(int i) {
        return this.zzb[i];
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaip
    byte zzb(int i) {
        return this.zzb[i];
    }

    protected int zze() {
        return 0;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaip
    protected final int zzb(int i, int i2, int i3) {
        return zzakb.zza(i, this.zzb, zze(), i3);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaip
    public int zzb() {
        return this.zzb.length;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaip
    public final zzaip zza(int i, int i2) {
        int iZza = zza(0, i2, zzb());
        if (iZza == 0) {
            return zzaip.zza;
        }
        return new zzait(this.zzb, zze(), iZza);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaip
    public final zzajb zzc() {
        return zzajb.zza(this.zzb, zze(), zzb(), true);
    }

    zzaiw(byte[] bArr) {
        super();
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.zzb = bArr;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaip
    protected void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzb, 0, bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaip
    final void zza(zzaim zzaimVar) throws IOException {
        zzaimVar.zza(this.zzb, zze(), zzb());
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaip
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzaip) || zzb() != ((zzaip) obj).zzb()) {
            return false;
        }
        if (zzb() == 0) {
            return true;
        }
        if (obj instanceof zzaiw) {
            zzaiw zzaiwVar = (zzaiw) obj;
            int iZza = zza();
            int iZza2 = zzaiwVar.zza();
            if (iZza == 0 || iZza2 == 0 || iZza == iZza2) {
                return zza(zzaiwVar, 0, zzb());
            }
            return false;
        }
        return obj.equals(this);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaix
    final boolean zza(zzaip zzaipVar, int i, int i2) {
        if (i2 > zzaipVar.zzb()) {
            throw new IllegalArgumentException("Length too large: " + i2 + zzb());
        }
        if (i2 > zzaipVar.zzb()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + i2 + ", " + zzaipVar.zzb());
        }
        if (zzaipVar instanceof zzaiw) {
            zzaiw zzaiwVar = (zzaiw) zzaipVar;
            byte[] bArr = this.zzb;
            byte[] bArr2 = zzaiwVar.zzb;
            int iZze = zze() + i2;
            int iZze2 = zze();
            int iZze3 = zzaiwVar.zze();
            while (iZze2 < iZze) {
                if (bArr[iZze2] != bArr2[iZze3]) {
                    return false;
                }
                iZze2++;
                iZze3++;
            }
            return true;
        }
        return zzaipVar.zza(0, i2).equals(zza(0, i2));
    }
}
