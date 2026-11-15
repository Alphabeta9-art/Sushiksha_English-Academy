package com.google.android.recaptcha.internal;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
class zziu extends zzit {
    protected final byte[] zza;

    zziu(byte[] bArr) {
        super(null);
        if (bArr == null) {
            throw null;
        }
        this.zza = bArr;
    }

    @Override // com.google.android.recaptcha.internal.zziv
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (!(obj instanceof zziv) || zzd() != ((zziv) obj).zzd()) {
                return false;
            }
            if (zzd() != 0) {
                if (!(obj instanceof zziu)) {
                    return obj.equals(this);
                }
                zziu zziuVar = (zziu) obj;
                int iZzj = zzj();
                int iZzj2 = zziuVar.zzj();
                if (iZzj != 0 && iZzj2 != 0 && iZzj != iZzj2) {
                    return false;
                }
                int iZzd = zzd();
                if (iZzd > zziuVar.zzd()) {
                    throw new IllegalArgumentException("Length too large: " + iZzd + zzd());
                }
                if (iZzd > zziuVar.zzd()) {
                    throw new IllegalArgumentException("Ran off end of other: 0, " + iZzd + ", " + zziuVar.zzd());
                }
                if (!(zziuVar instanceof zziu)) {
                    return zziuVar.zzg(0, iZzd).equals(zzg(0, iZzd));
                }
                byte[] bArr = this.zza;
                byte[] bArr2 = zziuVar.zza;
                zziuVar.zzc();
                int i = 0;
                int i2 = 0;
                while (i < iZzd) {
                    if (bArr[i] != bArr2[i2]) {
                        return false;
                    }
                    i++;
                    i2++;
                }
                return true;
            }
        }
        return true;
    }

    @Override // com.google.android.recaptcha.internal.zziv
    public byte zza(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.recaptcha.internal.zziv
    byte zzb(int i) {
        return this.zza[i];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.recaptcha.internal.zziv
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.recaptcha.internal.zziv
    protected void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, 0, bArr, 0, i3);
    }

    @Override // com.google.android.recaptcha.internal.zziv
    protected final int zzf(int i, int i2, int i3) {
        return zzla.zzb(i, this.zza, 0, i3);
    }

    @Override // com.google.android.recaptcha.internal.zziv
    public final zziv zzg(int i, int i2) {
        int iZzi = zzi(0, i2, zzd());
        return iZzi == 0 ? zziv.zzb : new zziq(this.zza, 0, iZzi);
    }

    @Override // com.google.android.recaptcha.internal.zziv
    final void zzh(zzin zzinVar) throws IOException {
        ((zzjd) zzinVar).zzc(this.zza, 0, zzd());
    }
}
