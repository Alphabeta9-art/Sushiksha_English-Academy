package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzpy extends zzks implements zzly {
    private static final zzpy zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzpy zzpyVar = new zzpy();
        zzb = zzpyVar;
        zzks.zzF(zzpy.class, zzpyVar);
    }

    private zzpy() {
    }

    public static zzpx zzf() {
        return (zzpx) zzb.zzp();
    }

    static /* synthetic */ void zzi(zzpy zzpyVar, String str) {
        str.getClass();
        zzpyVar.zzd |= 1;
        zzpyVar.zze = str;
    }

    static /* synthetic */ void zzj(zzpy zzpyVar, String str) {
        str.getClass();
        zzpyVar.zzd |= 2;
        zzpyVar.zzf = str;
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
                return zzC(zzb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ለ\u0000\u0002ለ\u0001", new Object[]{"zzd", "zze", "zzf"});
            case 3:
                return new zzpy();
            case 4:
                return new zzpx(zzpkVar);
            case 5:
                return zzb;
        }
    }
}
