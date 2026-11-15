package com.google.android.gms.internal.p002firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzsk extends zzajy<zzsk, zza> implements zzale {
    private static final zzsk zzc;
    private static volatile zzalp<zzsk> zzd;
    private int zze;

    public final int zza() {
        return this.zze;
    }

    public static zza zzb() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzsk, zza> implements zzale {
        public final zza zza(int i) {
            zzh();
            ((zzsk) this.zza).zze = i;
            return this;
        }

        private zza() {
            super(zzsk.zzc);
        }
    }

    public static zzsk zzd() {
        return zzc;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajy
    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzsm.zza[i - 1]) {
            case 1:
                return new zzsk();
            case 2:
                return new zza();
            case 3:
                return zza(zzc, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zze"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzsk> zzcVar = zzd;
                if (zzcVar == null) {
                    synchronized (zzsk.class) {
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
        zzsk zzskVar = new zzsk();
        zzc = zzskVar;
        zzajy.zza((Class<zzsk>) zzsk.class, zzskVar);
    }

    private zzsk() {
    }
}
