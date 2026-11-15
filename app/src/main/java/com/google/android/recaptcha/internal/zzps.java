package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzps extends zzks implements zzly {
    private static final zzps zzb;
    private long zzf;
    private zziv zzd = zziv.zzb;
    private String zze = "";
    private zziv zzg = zziv.zzb;
    private String zzh = "";
    private String zzi = "";

    static {
        zzps zzpsVar = new zzps();
        zzb = zzpsVar;
        zzks.zzF(zzps.class, zzpsVar);
    }

    private zzps() {
    }

    public static zzps zzk() {
        return zzb;
    }

    public final String zzJ() {
        return this.zze;
    }

    public final long zzf() {
        return this.zzf;
    }

    public final zziv zzg() {
        return this.zzg;
    }

    public final zziv zzi() {
        return this.zzd;
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
                return zzC(zzb, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0000\u0000\u0001\n\u0002Ȉ\u0003\u0002\u0004\n\u0005Ȉ\u0006Ȉ", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
            case 3:
                return new zzps();
            case 4:
                return new zzpr(zzpkVar);
            case 5:
                return zzb;
        }
    }
}
