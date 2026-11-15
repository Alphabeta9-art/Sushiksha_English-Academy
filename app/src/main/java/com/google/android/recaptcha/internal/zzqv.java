package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzqv extends zzks implements zzly {
    private static final zzqv zzb;
    private String zzd = "";
    private String zze = "";
    private String zzf = "";

    static {
        zzqv zzqvVar = new zzqv();
        zzb = zzqvVar;
        zzks.zzF(zzqv.class, zzqvVar);
    }

    private zzqv() {
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
                return zzC(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ", new Object[]{"zzd", "zze", "zzf"});
            case 3:
                return new zzqv();
            case 4:
                return new zzqu(zzqrVar);
            case 5:
                return zzb;
        }
    }
}
