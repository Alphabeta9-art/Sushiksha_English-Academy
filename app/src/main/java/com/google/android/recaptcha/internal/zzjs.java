package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzjs extends zzko implements zzly {
    private static final zzjs zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private byte zzl = 2;

    static {
        zzjs zzjsVar = new zzjs();
        zzd = zzjsVar;
        zzks.zzF(zzjs.class, zzjsVar);
    }

    private zzjs() {
    }

    @Override // com.google.android.recaptcha.internal.zzks
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzji zzjiVar = null;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzl);
            case 1:
            default:
                this.zzl = obj == null ? (byte) 0 : (byte) 1;
                return null;
            case 2:
                return new zzmi(zzd, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006᠌\u0005", new Object[]{"zze", "zzf", zzjn.zza, "zzg", zzjm.zza, "zzh", zzjq.zza, "zzi", zzjr.zza, "zzj", zzjp.zza, "zzk", zzjo.zza});
            case 3:
                return new zzjs();
            case 4:
                return new zzjl(zzjiVar);
            case 5:
                return zzd;
        }
    }
}
