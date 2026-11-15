package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzjg {
    private static final zzzc zza = zzpr.zzb("type.googleapis.com/google.crypto.tink.AesSivKey");
    private static final zzon<zziv, zzpf> zzb = zzon.zza(new zzop() { // from class: com.google.android.gms.internal.firebase-auth-api.zzjf
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzop
        public final zzpj zza(zzch zzchVar) {
            zziv zzivVar = (zziv) zzchVar;
            return zzpf.zzb((zzvu) ((zzajy) zzvu.zza().zza("type.googleapis.com/google.crypto.tink.AesSivKey").zza(((zztl) ((zzajy) zztl.zzc().zza(zzivVar.zzb()).zze())).a_()).zza(zzjg.zza(zzivVar.zzd())).zze()));
        }
    }, zziv.class, zzpf.class);
    private static final zzoj<zzpf> zzc = zzoj.zza(new zzol() { // from class: com.google.android.gms.internal.firebase-auth-api.zzji
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzol
        public final zzch zza(zzpj zzpjVar) {
            return zzjg.zzb((zzpf) zzpjVar);
        }
    }, zza, zzpf.class);
    private static final zzmw<zzio, zzpc> zzd = zzmw.zza(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzjh
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzmy
        public final zzpj zza(zzbp zzbpVar, zzcn zzcnVar) {
            zzio zzioVar = (zzio) zzbpVar;
            return zzpc.zza("type.googleapis.com/google.crypto.tink.AesSivKey", ((zzti) ((zzajy) zzti.zzb().zza(zzaip.zza(zzioVar.zze().zza(zzcn.zza(zzcnVar)))).zze())).a_(), zzvq.zzb.SYMMETRIC, zzjg.zza(zzioVar.zzc().zzd()), zzioVar.zza());
        }
    }, zzio.class, zzpc.class);
    private static final zzms<zzpc> zze = zzms.zza(new zzmu() { // from class: com.google.android.gms.internal.firebase-auth-api.zzjk
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzmu
        public final zzbp zza(zzpj zzpjVar, zzcn zzcnVar) {
            return zzjg.zzb((zzpc) zzpjVar, zzcnVar);
        }
    }, zza, zzpc.class);
    private static final Map<zziv.zzb, zzws> zzf;
    private static final Map<zzws, zziv.zzb> zzg;

    /* JADX INFO: Access modifiers changed from: private */
    public static zzio zzb(zzpc zzpcVar, @Nullable zzcn zzcnVar) throws GeneralSecurityException {
        if (!zzpcVar.zzf().equals("type.googleapis.com/google.crypto.tink.AesSivKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesSivParameters.parseParameters");
        }
        try {
            zzti zztiVarZza = zzti.zza(zzpcVar.zzd(), zzajk.zza());
            if (zztiVarZza.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            return zzio.zzb().zza(zziv.zzc().zza(zztiVarZza.zzd().zzb()).zza(zza(zzpcVar.zzb())).zza()).zza(zzze.zza(zztiVarZza.zzd().zzd(), zzcn.zza(zzcnVar))).zza(zzpcVar.zze()).zza();
        } catch (zzakf e) {
            throw new GeneralSecurityException("Parsing AesSivKey failed");
        }
    }

    private static zziv.zzb zza(zzws zzwsVar) throws GeneralSecurityException {
        if (zzg.containsKey(zzwsVar)) {
            return zzg.get(zzwsVar);
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzwsVar.zza());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zziv zzb(zzpf zzpfVar) throws GeneralSecurityException {
        if (!zzpfVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.AesSivKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesSivParameters.parseParameters: " + zzpfVar.zza().zzf());
        }
        try {
            zztl zztlVarZza = zztl.zza(zzpfVar.zza().zze(), zzajk.zza());
            if (zztlVarZza.zzb() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            return zziv.zzc().zza(zztlVarZza.zza()).zza(zza(zzpfVar.zza().zzd())).zza();
        } catch (zzakf e) {
            throw new GeneralSecurityException("Parsing AesSivParameters failed: ", e);
        }
    }

    private static zzws zza(zziv.zzb zzbVar) throws GeneralSecurityException {
        if (zzf.containsKey(zzbVar)) {
            return zzf.get(zzbVar);
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zzbVar));
    }

    static {
        HashMap map = new HashMap();
        map.put(zziv.zzb.zzc, zzws.RAW);
        map.put(zziv.zzb.zza, zzws.TINK);
        map.put(zziv.zzb.zzb, zzws.CRUNCHY);
        zzf = Collections.unmodifiableMap(map);
        EnumMap enumMap = new EnumMap(zzws.class);
        enumMap.put(zzws.RAW, zziv.zzb.zzc);
        enumMap.put(zzws.TINK, zziv.zzb.zza);
        enumMap.put(zzws.CRUNCHY, zziv.zzb.zzb);
        enumMap.put(zzws.LEGACY, zziv.zzb.zzb);
        zzg = Collections.unmodifiableMap(enumMap);
    }

    public static void zza() throws GeneralSecurityException {
        zzof zzofVarZza = zzof.zza();
        zzofVarZza.zza(zzb);
        zzofVarZza.zza(zzc);
        zzofVarZza.zza(zzd);
        zzofVarZza.zza(zze);
    }
}
