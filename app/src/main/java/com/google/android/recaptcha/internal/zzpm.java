package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzpm extends zzks implements zzly {
    private static final zzpm zzb;
    private int zzd;
    private String zze = "";

    static {
        zzpm zzpmVar = new zzpm();
        zzb = zzpmVar;
        zzks.zzF(zzpm.class, zzpmVar);
    }

    private zzpm() {
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
                return zzC(zzb, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ለ\u0000", new Object[]{"zzd", "zze"});
            case 3:
                return new zzpm();
            case 4:
                return new zzpl(zzpkVar);
            case 5:
                return zzb;
        }
    }
}
