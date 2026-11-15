package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzpo extends zzks implements zzly {
    private static final zzpo zzb;
    private zziv zzd = zziv.zzb;

    static {
        zzpo zzpoVar = new zzpo();
        zzb = zzpoVar;
        zzks.zzF(zzpo.class, zzpoVar);
    }

    private zzpo() {
    }

    @Override // com.google.android.recaptcha.internal.zzks
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzpk zzpkVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzC(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\n", new Object[]{"zzd"});
            case 3:
                return new zzpo();
            case 4:
                return new zzpn(zzpkVar);
            case 5:
                return zzb;
        }
    }
}
