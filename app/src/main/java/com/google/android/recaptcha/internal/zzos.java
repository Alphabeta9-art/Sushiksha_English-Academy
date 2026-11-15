package com.google.android.recaptcha.internal;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzos extends zzks implements zzly {
    private static final zzos zzb;
    private zzkz zzd = zzz();
    private zzkz zze = zzz();

    static {
        zzos zzosVar = new zzos();
        zzb = zzosVar;
        zzks.zzF(zzos.class, zzosVar);
    }

    private zzos() {
    }

    static /* synthetic */ void zzK(zzos zzosVar, zzop zzopVar) {
        zzopVar.getClass();
        zzkz zzkzVar = zzosVar.zzd;
        if (!zzkzVar.zzc()) {
            zzosVar.zzd = zzks.zzA(zzkzVar);
        }
        zzosVar.zzd.add(zzopVar);
    }

    static /* synthetic */ void zzL(zzos zzosVar, zzpe zzpeVar) {
        zzpeVar.getClass();
        zzkz zzkzVar = zzosVar.zze;
        if (!zzkzVar.zzc()) {
            zzosVar.zze = zzks.zzA(zzkzVar);
        }
        zzosVar.zze.add(zzpeVar);
    }

    public static zzor zzi() {
        return (zzor) zzb.zzp();
    }

    public static zzos zzk(byte[] bArr) throws zzlc {
        return (zzos) zzks.zzv(zzb, bArr);
    }

    public final List zzJ() {
        return this.zzd;
    }

    public final int zzf() {
        return this.zzd.size();
    }

    public final int zzg() {
        return this.zze.size();
    }

    @Override // com.google.android.recaptcha.internal.zzks
    protected final Object zzh(int i, Object obj, Object obj2) {
        zzoq zzoqVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzC(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001b\u0002\u001b", new Object[]{"zzd", zzop.class, "zze", zzpe.class});
            case 3:
                return new zzos();
            case 4:
                return new zzor(zzoqVar);
            case 5:
                return zzb;
        }
    }
}
