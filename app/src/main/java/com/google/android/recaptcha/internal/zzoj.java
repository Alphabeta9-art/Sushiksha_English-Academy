package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzoj extends zzks implements zzly {
    private static final zzoj zzb;
    private String zzd = "";
    private String zze = "";

    static {
        zzoj zzojVar = new zzoj();
        zzb = zzojVar;
        zzks.zzF(zzoj.class, zzojVar);
    }

    private zzoj() {
    }

    @Override // com.google.android.recaptcha.internal.zzks
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzoh zzohVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzC(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"zzd", "zze"});
            case 3:
                return new zzoj();
            case 4:
                return new zzoi(zzohVar);
            case 5:
                return zzb;
        }
    }
}
