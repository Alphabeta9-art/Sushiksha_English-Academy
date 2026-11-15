package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzoy extends zzks implements zzly {
    private static final zzoy zzb;
    private int zzd;
    private zzka zze;
    private zzmz zzf;
    private zzka zzg;
    private zzmz zzh;

    static {
        zzoy zzoyVar = new zzoy();
        zzb = zzoyVar;
        zzks.zzF(zzoy.class, zzoyVar);
    }

    private zzoy() {
    }

    static /* synthetic */ void zzJ(zzoy zzoyVar, zzka zzkaVar) {
        zzkaVar.getClass();
        zzoyVar.zzg = zzkaVar;
        zzoyVar.zzd |= 4;
    }

    public static zzox zzf() {
        return (zzox) zzb.zzp();
    }

    static /* synthetic */ void zzi(zzoy zzoyVar, zzka zzkaVar) {
        zzkaVar.getClass();
        zzoyVar.zze = zzkaVar;
        zzoyVar.zzd |= 1;
    }

    static /* synthetic */ void zzj(zzoy zzoyVar, zzmz zzmzVar) {
        zzmzVar.getClass();
        zzoyVar.zzh = zzmzVar;
        zzoyVar.zzd |= 8;
    }

    static /* synthetic */ void zzk(zzoy zzoyVar, zzmz zzmzVar) {
        zzmzVar.getClass();
        zzoyVar.zzf = zzmzVar;
        zzoyVar.zzd |= 2;
    }

    @Override // com.google.android.recaptcha.internal.zzks
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzow zzowVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzC(zzb, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
            case 3:
                return new zzoy();
            case 4:
                return new zzox(zzowVar);
            case 5:
                return zzb;
        }
    }
}
