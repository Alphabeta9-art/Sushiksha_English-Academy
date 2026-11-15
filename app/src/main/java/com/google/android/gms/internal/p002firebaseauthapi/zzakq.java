package com.google.android.gms.internal.p002firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzakq implements zzalu {
    private static final zzald zza = new zzakt();
    private final zzald zzb;

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalu
    public final <T> zzalv<T> zza(Class<T> cls) {
        boolean z;
        zzalx.zza((Class<?>) cls);
        zzala zzalaVarZza = this.zzb.zza(cls);
        if (zzalaVarZza.zzc()) {
            return zzali.zza(zzalx.zza(), zzajo.zza(), zzalaVarZza.zza());
        }
        zzalk zzalkVarZza = zzalm.zza();
        zzakm zzakmVarZza = zzako.zza();
        zzamo<?, ?> zzamoVarZza = zzalx.zza();
        switch (zzalaVarZza.zzb()) {
            case PROTO3:
                z = false;
                break;
            default:
                z = true;
                break;
        }
        return zzalg.zza(cls, zzalaVarZza, zzalkVarZza, zzakmVarZza, zzamoVarZza, z ? zzajo.zza() : null, zzalb.zza());
    }

    public zzakq() {
        this(new zzakv(zzajw.zza(), zza));
    }

    private zzakq(zzald zzaldVar) {
        this.zzb = (zzald) zzakb.zza(zzaldVar, "messageInfoFactory");
    }
}
