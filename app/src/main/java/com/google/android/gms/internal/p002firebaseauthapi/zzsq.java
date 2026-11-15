package com.google.android.gms.internal.p002firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzsq extends zzajy<zzsq, zza> implements zzale {
    private static final zzsq zzc;
    private static volatile zzalp<zzsq> zzd;
    private int zze;
    private zzst zzf;
    private int zzg;

    public final int zza() {
        return this.zzg;
    }

    public static zza zzb() {
        return zzc.zzm();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzsq, zza> implements zzale {
        public final zza zza(int i) {
            zzh();
            ((zzsq) this.zza).zzg = i;
            return this;
        }

        public final zza zza(zzst zzstVar) {
            zzh();
            zzsq.zza((zzsq) this.zza, zzstVar);
            return this;
        }

        private zza() {
            super(zzsq.zzc);
        }
    }

    public static zzsq zza(zzaip zzaipVar, zzajk zzajkVar) throws zzakf {
        return (zzsq) zzajy.zza(zzc, zzaipVar, zzajkVar);
    }

    public final zzst zzd() {
        return this.zzf == null ? zzst.zzd() : this.zzf;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzajy
    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzss.zza[i - 1]) {
            case 1:
                return new zzsq();
            case 2:
                return new zza();
            case 3:
                return zza(zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u000b", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzsq> zzcVar = zzd;
                if (zzcVar == null) {
                    synchronized (zzsq.class) {
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

    static /* synthetic */ void zza(zzsq zzsqVar, zzst zzstVar) {
        zzstVar.getClass();
        zzsqVar.zzf = zzstVar;
        zzsqVar.zze |= 1;
    }

    static {
        zzsq zzsqVar = new zzsq();
        zzc = zzsqVar;
        zzajy.zza((Class<zzsq>) zzsq.class, zzsqVar);
    }

    private zzsq() {
    }
}
