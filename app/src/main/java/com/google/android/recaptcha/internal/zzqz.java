package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzqz extends zzks implements zzly {
    private static final zzqz zzb;
    private int zzd;

    static {
        zzqz zzqzVar = new zzqz();
        zzb = zzqzVar;
        zzks.zzF(zzqz.class, zzqzVar);
    }

    private zzqz() {
    }

    public static zzqz zzg(byte[] bArr) throws zzlc {
        return (zzqz) zzks.zzv(zzb, bArr);
    }

    public final zzrc zzi() {
        zzrc zzrcVarZzb = zzrc.zzb(this.zzd);
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
                return zzC(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"zzd"});
            case 3:
                return new zzqz();
            case 4:
                return new zzqy(zzqrVar);
            case 5:
                return zzb;
        }
    }
}
