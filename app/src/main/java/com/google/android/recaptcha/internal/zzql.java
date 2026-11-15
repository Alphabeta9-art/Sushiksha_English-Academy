package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzql extends zzks implements zzly {
    private static final zzql zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private zzkx zzg = zzw();
    private int zzh;
    private int zzi;

    static {
        zzql zzqlVar = new zzql();
        zzb = zzqlVar;
        zzks.zzF(zzql.class, zzqlVar);
    }

    private zzql() {
    }

    public static zzqj zzf() {
        return (zzqj) zzb.zzp();
    }

    static /* synthetic */ void zzi(zzql zzqlVar, String str) {
        str.getClass();
        zzqlVar.zzd |= 1;
        zzqlVar.zze = str;
    }

    static /* synthetic */ void zzj(zzql zzqlVar, String str) {
        str.getClass();
        zzqlVar.zzd |= 2;
        zzqlVar.zzf = str;
    }

    static /* synthetic */ void zzk(zzql zzqlVar, int i) {
        zzqlVar.zzh = 2;
        zzqlVar.zzd |= 4;
    }

    @Override // com.google.android.recaptcha.internal.zzks
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzqi zzqiVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzC(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003'\u0004᠌\u0002\u0005င\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", zzqk.zza, "zzi"});
            case 3:
                return new zzql();
            case 4:
                return new zzqj(zzqiVar);
            case 5:
                return zzb;
        }
    }
}
