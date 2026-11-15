package com.google.android.recaptcha.internal;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzro extends zzks implements zzly {
    private static final zzro zzb;
    private int zzd;
    private String zze = "";
    private zzkx zzf = zzw();
    private zzky zzg = zzy();
    private zzqq zzh;

    static {
        zzro zzroVar = new zzro();
        zzb = zzroVar;
        zzks.zzF(zzro.class, zzroVar);
    }

    private zzro() {
    }

    public static zzro zzi(byte[] bArr) throws zzlc {
        return (zzro) zzks.zzv(zzb, bArr);
    }

    public final zzqq zzf() {
        zzqq zzqqVar = this.zzh;
        return zzqqVar == null ? zzqq.zzg() : zzqqVar;
    }

    public final String zzj() {
        return this.zze;
    }

    public final List zzk() {
        return this.zzg;
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
                return zzC(zzb, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001Ȉ\u0002'\u0003%\u0004ဉ\u0000", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
            case 3:
                return new zzro();
            case 4:
                return new zzrn(zzqrVar);
            case 5:
                return zzb;
        }
    }
}
