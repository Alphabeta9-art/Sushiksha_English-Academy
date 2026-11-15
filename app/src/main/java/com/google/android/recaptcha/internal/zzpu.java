package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzpu extends zzks implements zzly {
    private static final zzpu zzb;
    private int zzd;
    private String zze = "";

    static {
        zzpu zzpuVar = new zzpu();
        zzb = zzpuVar;
        zzks.zzF(zzpu.class, zzpuVar);
    }

    private zzpu() {
    }

    public static zzpt zzf() {
        return (zzpt) zzb.zzp();
    }

    static /* synthetic */ void zzi(zzpu zzpuVar, String str) {
        zzpuVar.zzd |= 1;
        zzpuVar.zze = str;
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
                return new zzpu();
            case 4:
                return new zzpt(zzpkVar);
            case 5:
                return zzb;
        }
    }
}
