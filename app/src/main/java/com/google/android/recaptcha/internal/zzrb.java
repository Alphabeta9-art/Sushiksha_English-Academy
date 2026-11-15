package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzrb extends zzks implements zzly {
    private static final zzrb zzb;
    private int zzd;
    private int zze;
    private int zzf;

    static {
        zzrb zzrbVar = new zzrb();
        zzb = zzrbVar;
        zzks.zzF(zzrb.class, zzrbVar);
    }

    private zzrb() {
    }

    public static zzrb zzg(byte[] bArr) throws zzlc {
        return (zzrb) zzks.zzv(zzb, bArr);
    }

    public final zzrc zzi() {
        zzrc zzrcVarZzb = zzrc.zzb(this.zzf);
        return zzrcVarZzb == null ? zzrc.UNRECOGNIZED : zzrcVarZzb;
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
                return zzC(zzb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001", new Object[]{"zzd", "zze", "zzf"});
            case 3:
                return new zzrb();
            case 4:
                return new zzra(zzqrVar);
            case 5:
                return zzb;
        }
    }
}
