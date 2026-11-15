package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzjk extends zzko implements zzly {
    private static final zzjk zzd;
    private int zze;
    private boolean zzf;
    private zzjs zzg;
    private boolean zzh;
    private byte zzj = 2;
    private zzkz zzi = zzmh.zze();

    static {
        zzjk zzjkVar = new zzjk();
        zzd = zzjkVar;
        zzks.zzF(zzjk.class, zzjkVar);
    }

    private zzjk() {
    }

    public static zzjk zzg() {
        return zzd;
    }

    @Override // com.google.android.recaptcha.internal.zzks
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzji zzjiVar = null;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzj);
            case 1:
            default:
                this.zzj = obj == null ? (byte) 0 : (byte) 1;
                return null;
            case 2:
                return new zzmi(zzd, "\u0001\u0004\u0000\u0001\u0001ϧ\u0004\u0000\u0001\u0002\u0001ဇ\u0000\u0002ᐉ\u0001\u0003ဇ\u0002ϧЛ", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", zzjw.class});
            case 3:
                return new zzjk();
            case 4:
                return new zzjj(zzjiVar);
            case 5:
                return zzd;
        }
    }
}
