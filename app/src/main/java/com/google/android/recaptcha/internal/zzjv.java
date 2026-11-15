package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzjv extends zzks implements zzly {
    private static final zzjv zzb;
    private int zzd;
    private boolean zzf;
    private byte zzg = 2;
    private String zze = "";

    static {
        zzjv zzjvVar = new zzjv();
        zzb = zzjvVar;
        zzks.zzF(zzjv.class, zzjvVar);
    }

    private zzjv() {
    }

    @Override // com.google.android.recaptcha.internal.zzks
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzji zzjiVar = null;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzg);
            case 1:
            default:
                this.zzg = obj == null ? (byte) 0 : (byte) 1;
                return null;
            case 2:
                return new zzmi(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔈ\u0000\u0002ᔇ\u0001", new Object[]{"zzd", "zze", "zzf"});
            case 3:
                return new zzjv();
            case 4:
                return new zzju(zzjiVar);
            case 5:
                return zzb;
        }
    }
}
