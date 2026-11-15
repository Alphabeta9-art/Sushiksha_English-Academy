package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzmz extends zzks implements zzly {
    private static final zzmz zzb;
    private long zzd;
    private int zze;

    static {
        zzmz zzmzVar = new zzmz();
        zzb = zzmzVar;
        zzks.zzF(zzmz.class, zzmzVar);
    }

    private zzmz() {
    }

    public static zzmy zzi() {
        return (zzmy) zzb.zzp();
    }

    public final int zzf() {
        return this.zze;
    }

    public final long zzg() {
        return this.zzd;
    }

    @Override // com.google.android.recaptcha.internal.zzks
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzmx zzmxVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return new zzmi(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"zzd", "zze"});
            case 3:
                return new zzmz();
            case 4:
                return new zzmy(zzmxVar);
            case 5:
                return zzb;
        }
    }
}
