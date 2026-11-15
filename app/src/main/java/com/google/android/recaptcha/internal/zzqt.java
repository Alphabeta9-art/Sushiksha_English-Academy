package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzqt extends zzks implements zzly {
    private static final zzqt zzb;
    private int zzg;
    private String zzd = "";
    private String zze = "";
    private String zzf = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";

    static {
        zzqt zzqtVar = new zzqt();
        zzb = zzqtVar;
        zzks.zzF(zzqt.class, zzqtVar);
    }

    private zzqt() {
    }

    static /* synthetic */ void zzM(zzqt zzqtVar, String str) {
        str.getClass();
        zzqtVar.zzd = str;
    }

    static /* synthetic */ void zzN(zzqt zzqtVar, String str) {
        str.getClass();
        zzqtVar.zzh = str;
    }

    static /* synthetic */ void zzO(zzqt zzqtVar, String str) {
        str.getClass();
        zzqtVar.zzi = str;
    }

    static /* synthetic */ void zzQ(zzqt zzqtVar, String str) {
        str.getClass();
        zzqtVar.zze = str;
    }

    public static zzqs zzf() {
        return (zzqs) zzb.zzp();
    }

    public final String zzJ() {
        return this.zzf;
    }

    public final String zzK() {
        return this.zzh;
    }

    public final String zzL() {
        return this.zzd;
    }

    public final int zzS() {
        int i;
        switch (this.zzg) {
            case 0:
                i = 2;
                break;
            case 1:
                i = 3;
                break;
            case 2:
                i = 4;
                break;
            case 3:
                i = 5;
                break;
            default:
                i = 0;
                break;
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }

    public final String zzi() {
        return this.zzj;
    }

    public final String zzj() {
        return this.zzi;
    }

    public final String zzk() {
        return this.zze;
    }

    @Override // com.google.android.recaptcha.internal.zzks
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzqr zzqrVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzC(zzb, "\u0000\u0007\u0000\u0000\u0001\b\u0007\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004\f\u0006Ȉ\u0007Ȉ\bȈ", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
            case 3:
                return new zzqt();
            case 4:
                return new zzqs(zzqrVar);
            case 5:
                return zzb;
        }
    }
}
