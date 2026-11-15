package com.google.android.gms.internal.p002firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzrv extends zzajy<zzrv, zza> implements zzale {
    private static final zzrv zzc;
    private static volatile zzalp<zzrv> zzd;
    private int zze;

    public final int zza() {
        return this.zze;
    }

    public static zza zzb() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzrv, zza> implements zzale {
        public final zza zza(int i) {
            zzh();
            ((zzrv) this.zza).zze = i;
            return this;
        }

        private zza() {
            super(zzrv.zzc);
        }
    }

    public static zzrv zzd() {
        return zzc;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajy
    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzrx.zza[i - 1]) {
            case 1:
                return new zzrv();
            case 2:
                return new zza();
            case 3:
                return zza(zzc, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zze"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzrv> zzcVar = zzd;
                if (zzcVar == null) {
                    synchronized (zzrv.class) {
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
        zzrv zzrvVar = new zzrv();
        zzc = zzrvVar;
        zzajy.zza((Class<zzrv>) zzrv.class, zzrvVar);
    }

    private zzrv() {
    }
}
