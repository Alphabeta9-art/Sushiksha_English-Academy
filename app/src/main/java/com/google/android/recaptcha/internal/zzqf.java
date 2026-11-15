package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzqf extends zzks implements zzly {
    private static final zzqf zzb;
    private int zzd;
    private long zzf;
    private int zzg;
    private String zze = "";
    private zzkz zzh = zzz();
    private zziv zzi = zziv.zzb;
    private String zzj = "";
    private String zzk = "";

    static {
        zzqf zzqfVar = new zzqf();
        zzb = zzqfVar;
        zzks.zzF(zzqf.class, zzqfVar);
    }

    private zzqf() {
    }

    public static zzqf zzi(InputStream inputStream) throws IOException {
        return (zzqf) zzks.zzu(zzb, inputStream);
    }

    public final zziv zzf() {
        return this.zzi;
    }

    public final String zzj() {
        return this.zze;
    }

    public final List zzk() {
        return this.zzh;
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
                return zzC(zzb, "\u0000\u0007\u0000\u0001\u0001\b\u0007\u0000\u0001\u0000\u0001ለ\u0000\u0002ဂ\u0001\u0004ဌ\u0002\u0005\u001b\u0006ည\u0003\u0007ለ\u0004\bለ\u0005", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", zzqh.class, "zzi", "zzj", "zzk"});
            case 3:
                return new zzqf();
            case 4:
                return new zzqe(zzqbVar);
            case 5:
                return zzb;
        }
    }
}
