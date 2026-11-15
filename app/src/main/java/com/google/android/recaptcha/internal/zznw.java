package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zznw extends zzks implements zzly {
    private static final zznw zzb;
    private int zzd;
    private zzka zze;
    private int zzf;

    static {
        zznw zznwVar = new zznw();
        zzb = zznwVar;
        zzks.zzF(zznw.class, zznwVar);
    }

    private zznw() {
    }

    @Override // com.google.android.recaptcha.internal.zzks
    protected final Object zzh(int i, Object obj, Object obj2) {
        zznu zznuVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzC(zzb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u0004", new Object[]{"zzd", "zze", "zzf"});
            case 3:
                return new zznw();
            case 4:
                return new zznv(zznuVar);
            case 5:
                return zzb;
        }
    }
}
