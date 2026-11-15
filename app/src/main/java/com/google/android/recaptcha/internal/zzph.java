package com.google.android.recaptcha.internal;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzph extends zzks implements zzly {
    private static final zzph zzb;
    private zzkz zzd = zzks.zzz();

    static {
        zzph zzphVar = new zzph();
        zzb = zzphVar;
        zzks.zzF(zzph.class, zzphVar);
    }

    private zzph() {
    }

    public static zzph zzg() {
        return zzb;
    }

    public final List zzi() {
        return this.zzd;
    }

    @Override // com.google.android.recaptcha.internal.zzks
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzpf zzpfVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzC(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001Ț", new Object[]{"zzd"});
            case 3:
                return new zzph();
            case 4:
                return new zzpg(zzpfVar);
            case 5:
                return zzb;
        }
    }
}
