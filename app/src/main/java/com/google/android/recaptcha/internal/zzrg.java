package com.google.android.recaptcha.internal;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzrg extends zzks implements zzly {
    private static final zzrg zzb;
    private zzkz zzd = zzz();

    static {
        zzrg zzrgVar = new zzrg();
        zzb = zzrgVar;
        zzks.zzF(zzrg.class, zzrgVar);
    }

    private zzrg() {
    }

    public static zzrg zzg(byte[] bArr) throws zzlc {
        return (zzrg) zzks.zzv(zzb, bArr);
    }

    public final List zzi() {
        return this.zzd;
    }

    @Override // com.google.android.recaptcha.internal.zzks
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzqr zzqrVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzC(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzd", zzrs.class});
            case 3:
                return new zzrg();
            case 4:
                return new zzrf(zzqrVar);
            case 5:
                return zzb;
        }
    }
}
