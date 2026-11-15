package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzpq extends zzks implements zzly {
    private static final zzpq zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private zzky zzk = zzy();
    private zziv zzl = zziv.zzb;
    private String zzm = "";
    private zzps zzn;
    private zzpo zzo;

    static {
        zzpq zzpqVar = new zzpq();
        zzb = zzpqVar;
        zzks.zzF(zzpq.class, zzpqVar);
    }

    private zzpq() {
    }

    public static zzpq zzi(InputStream inputStream) throws IOException {
        return (zzpq) zzks.zzu(zzb, inputStream);
    }

    public final String zzJ() {
        return this.zzh;
    }

    public final String zzK() {
        return this.zzg;
    }

    public final String zzL() {
        return this.zzi;
    }

    public final String zzM() {
        return this.zzj;
    }

    public final List zzN() {
        return this.zzk;
    }

    public final boolean zzO() {
        return (this.zzd & 256) != 0;
    }

    public final zziv zzf() {
        return this.zzl;
    }

    public final zzps zzj() {
        zzps zzpsVar = this.zzn;
        return zzpsVar == null ? zzps.zzk() : zzpsVar;
    }

    public final String zzk() {
        return this.zzf;
    }

    @Override // com.google.android.recaptcha.internal.zzks
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzpk zzpkVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzC(zzb, "\u0000\u000b\u0000\u0001\u0001\f\u000b\u0000\u0001\u0000\u0001ለ\u0000\u0002ለ\u0001\u0003ለ\u0002\u0004ለ\u0003\u0005ለ\u0004\u0006ለ\u0005\u0007%\bည\u0006\tለ\u0007\u000bဉ\b\fဉ\t", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo"});
            case 3:
                return new zzpq();
            case 4:
                return new zzpp(zzpkVar);
            case 5:
                return zzb;
        }
    }
}
