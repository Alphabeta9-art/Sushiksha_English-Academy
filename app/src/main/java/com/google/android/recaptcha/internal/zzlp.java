package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzlp implements zzml {
    private static final zzlv zza = new zzln();
    private final zzlv zzb;

    public zzlp() {
        zzlv zzlvVar;
        zzlv[] zzlvVarArr = new zzlv[2];
        zzlvVarArr[0] = zzkl.zza();
        try {
            zzlvVar = (zzlv) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            zzlvVar = zza;
        }
        zzlvVarArr[1] = zzlvVar;
        zzlo zzloVar = new zzlo(zzlvVarArr);
        byte[] bArr = zzla.zzb;
        this.zzb = zzloVar;
    }

    private static boolean zzb(zzlu zzluVar) {
        switch (zzluVar.zzc() - 1) {
            case 1:
                return false;
            default:
                return true;
        }
    }

    @Override // com.google.android.recaptcha.internal.zzml
    public final zzmk zza(Class cls) {
        zzmm.zzs(cls);
        zzlu zzluVarZzb = this.zzb.zzb(cls);
        if (zzluVarZzb.zzb()) {
            return zzks.class.isAssignableFrom(cls) ? zzmb.zzc(zzmm.zzn(), zzkg.zzb(), zzluVarZzb.zza()) : zzmb.zzc(zzmm.zzm(), zzkg.zza(), zzluVarZzb.zza());
        }
        if (zzks.class.isAssignableFrom(cls)) {
            return zzma.zzm(cls, zzluVarZzb, zzme.zzb(), zzll.zzb(), zzmm.zzn(), zzb(zzluVarZzb) ? zzkg.zzb() : null, zzlt.zzb());
        }
        return zzma.zzm(cls, zzluVarZzb, zzme.zza(), zzll.zza(), zzmm.zzm(), zzb(zzluVarZzb) ? zzkg.zza() : null, zzlt.zza());
    }
}
