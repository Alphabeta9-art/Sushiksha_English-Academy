package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzrm extends zzks implements zzly {
    private static final zzrm zzb;
    private zzkz zzd = zzz();
    private int zze;

    static {
        zzrm zzrmVar = new zzrm();
        zzb = zzrmVar;
        zzks.zzF(zzrm.class, zzrmVar);
    }

    private zzrm() {
    }

    private final void zzJ() {
        zzkz zzkzVar = this.zzd;
        if (zzkzVar.zzc()) {
            return;
        }
        this.zzd = zzks.zzA(zzkzVar);
    }

    public static zzrj zzf() {
        return (zzrj) zzb.zzp();
    }

    static /* synthetic */ void zzi(zzrm zzrmVar, zzrl zzrlVar) {
        zzrlVar.getClass();
        zzrmVar.zzJ();
        zzrmVar.zzd.add(zzrlVar);
    }

    static /* synthetic */ void zzj(zzrm zzrmVar, Iterable iterable) {
        zzrmVar.zzJ();
        zzig.zzc(iterable, zzrmVar.zzd);
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
                return zzC(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\u000b", new Object[]{"zzd", zzrl.class, "zze"});
            case 3:
                return new zzrm();
            case 4:
                return new zzrj(zzqrVar);
            case 5:
                return zzb;
        }
    }
}
