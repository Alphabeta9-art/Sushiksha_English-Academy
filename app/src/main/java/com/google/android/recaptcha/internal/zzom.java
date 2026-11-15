package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzom extends zzks implements zzly {
    private static final zzom zzb;
    private int zzd;
    private int zzf;
    private int zzh;
    private int zzj;
    private String zze = "";
    private String zzg = "";
    private String zzi = "";

    static {
        zzom zzomVar = new zzom();
        zzb = zzomVar;
        zzks.zzF(zzom.class, zzomVar);
    }

    private zzom() {
    }

    @Override // com.google.android.recaptcha.internal.zzks
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzok zzokVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzC(zzb, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001\u0004\u0002Ȉ\u0003\u0004\u0004Ȉ\u0005\u0004\u0006Ȉ\u0007\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
            case 3:
                return new zzom();
            case 4:
                return new zzol(zzokVar);
            case 5:
                return zzb;
        }
    }
}
