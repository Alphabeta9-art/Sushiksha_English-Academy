package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzqa extends zzks implements zzly {
    private static final zzqa zzb;
    private int zzd;
    private String zze = "";

    static {
        zzqa zzqaVar = new zzqa();
        zzb = zzqaVar;
        zzks.zzF(zzqa.class, zzqaVar);
    }

    private zzqa() {
    }

    public static zzpz zzf() {
        return (zzpz) zzb.zzp();
    }

    static /* synthetic */ void zzi(zzqa zzqaVar, String str) {
        str.getClass();
        zzqaVar.zzd |= 1;
        zzqaVar.zze = str;
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
                return new zzqa();
            case 4:
                return new zzpz(zzpkVar);
            case 5:
                return zzb;
        }
    }
}
