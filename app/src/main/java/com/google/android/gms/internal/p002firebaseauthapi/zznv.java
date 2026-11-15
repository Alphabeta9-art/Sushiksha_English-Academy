package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zznv {
    private static final zznx<zzne> zza = new zznx() { // from class: com.google.android.gms.internal.firebase-auth-api.zznu
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zznx
        public final zzbp zza(zzch zzchVar, Integer num) {
            return zznv.zza((zzne) zzchVar, num);
        }
    };
    private static final zznv zzb = zzb();
    private final Map<Class<? extends zzch>, zznx<? extends zzch>> zzc = new HashMap();

    public final zzbp zza(zzch zzchVar, @Nullable Integer num) throws GeneralSecurityException {
        return zzb(zzchVar, num);
    }

    private final synchronized <ParametersT extends zzch> zzbp zzb(ParametersT parameterst, @Nullable Integer num) throws GeneralSecurityException {
        zznx<? extends zzch> zznxVar;
        zznxVar = this.zzc.get(parameterst.getClass());
        if (zznxVar == null) {
            throw new GeneralSecurityException("Cannot create a new key for parameters " + String.valueOf(parameterst) + ": no key creator for this class was registered.");
        }
        return zznxVar.zza(parameterst, num);
    }

    public static /* synthetic */ zznc zza(zzne zzneVar, Integer num) throws GeneralSecurityException {
        zzvu zzvuVarZza = zzneVar.zzb().zza();
        zzbs<?> zzbsVarZza = zzmt.zza().zza(zzvuVarZza.zzf());
        if (!zzmt.zza().zzb(zzvuVarZza.zzf())) {
            throw new GeneralSecurityException("Creating new keys is not allowed.");
        }
        zzvq zzvqVarZza = zzbsVarZza.zza(zzvuVarZza.zze());
        return new zznc(zzpc.zza(zzvqVarZza.zzf(), zzvqVarZza.zze(), zzvqVarZza.zzb(), zzvuVarZza.zzd(), num), zzbq.zza());
    }

    public static zznv zza() {
        return zzb;
    }

    private static zznv zzb() {
        zznv zznvVar = new zznv();
        try {
            zznvVar.zza(zza, zzne.class);
            return zznvVar;
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("unexpected error.", e);
        }
    }

    public final synchronized <ParametersT extends zzch> void zza(zznx<ParametersT> zznxVar, Class<ParametersT> cls) throws GeneralSecurityException {
        zznx<? extends zzch> zznxVar2 = this.zzc.get(cls);
        if (zznxVar2 != null && !zznxVar2.equals(zznxVar)) {
            throw new GeneralSecurityException("Different key creator for parameters class " + String.valueOf(cls) + " already inserted");
        }
        this.zzc.put(cls, zznxVar);
    }
}
