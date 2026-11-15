package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzqh extends zzks implements zzly {
    private static final zzqh zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzqh zzqhVar = new zzqh();
        zzb = zzqhVar;
        zzks.zzF(zzqh.class, zzqhVar);
    }

    private zzqh() {
    }

    public final String zzg() {
        return this.zze;
    }

    public final String zzi() {
        return this.zzf;
    }

    @Override // com.google.android.recaptcha.internal.zzks
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzqb zzqbVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzC(zzb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ለ\u0000\u0002ለ\u0001", new Object[]{"zzd", "zze", "zzf"});
            case 3:
                return new zzqh();
            case 4:
                return new zzqg(zzqbVar);
            case 5:
                return zzb;
        }
    }
}
