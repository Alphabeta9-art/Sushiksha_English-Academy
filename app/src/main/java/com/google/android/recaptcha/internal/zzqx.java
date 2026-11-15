package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzqx extends zzks implements zzly {
    private static final zzqx zzb;
    private String zzd = "";
    private String zze = "";

    static {
        zzqx zzqxVar = new zzqx();
        zzb = zzqxVar;
        zzks.zzF(zzqx.class, zzqxVar);
    }

    private zzqx() {
    }

    public static zzqw zzf() {
        return (zzqw) zzb.zzp();
    }

    static /* synthetic */ void zzi(zzqx zzqxVar, String str) {
        str.getClass();
        zzqxVar.zzd = str;
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
                return zzC(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"zzd", "zze"});
            case 3:
                return new zzqx();
            case 4:
                return new zzqw(zzqrVar);
            case 5:
                return zzb;
        }
    }
}
