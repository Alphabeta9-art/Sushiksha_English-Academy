package com.google.android.gms.internal.p002firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
@Deprecated
public final class zzwv extends zzajy<zzwv, zza> implements zzale {
    private static final zzwv zzc;
    private static volatile zzalp<zzwv> zzd;
    private String zze = "";
    private zzakc<zzvx> zzf = zzp();

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzwv, zza> implements zzale {
        private zza() {
            super(zzwv.zzc);
        }
    }

    public static zzwv zzb() {
        return zzc;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajy
    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzwu.zza[i - 1]) {
            case 1:
                return new zzwv();
            case 2:
                return new zza();
            case 3:
                return zza(zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"zze", "zzf", zzvx.class});
            case 4:
                return zzc;
            case 5:
                zzalp<zzwv> zzcVar = zzd;
                if (zzcVar == null) {
                    synchronized (zzwv.class) {
                        zzcVar = zzd;
                        if (zzcVar == null) {
                            zzcVar = new zzajy.zzc<>(zzc);
                            zzd = zzcVar;
                        }
                        break;
                    }
                }
                return zzcVar;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        zzwv zzwvVar = new zzwv();
        zzc = zzwvVar;
        zzajy.zza((Class<zzwv>) zzwv.class, zzwvVar);
    }

    private zzwv() {
    }
}
