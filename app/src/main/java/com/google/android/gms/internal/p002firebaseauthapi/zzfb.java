package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzfb {
    private static final zzzc zza = zzpr.zzb("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey");
    private static final zzon<zzfa, zzpf> zzb = zzon.zza(new zzop() { // from class: com.google.android.gms.internal.firebase-auth-api.zzfe
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzop
        public final zzpj zza(zzch zzchVar) {
            zzfa zzfaVar = (zzfa) zzchVar;
            return zzpf.zzb((zzvu) ((zzajy) zzvu.zza().zza("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey").zza(zzfb.zzb(zzfaVar).a_()).zza(zzfb.zza(zzfaVar.zzc())).zze()));
        }
    }, zzfa.class, zzpf.class);
    private static final zzoj<zzpf> zzc = zzoj.zza(new zzol() { // from class: com.google.android.gms.internal.firebase-auth-api.zzfd
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzol
        public final zzch zza(zzpj zzpjVar) {
            return zzfb.zzb((zzpf) zzpjVar);
        }
    }, zza, zzpf.class);
    private static final zzmw<zzey, zzpc> zzd = zzmw.zza(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzfg
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzmy
        public final zzpj zza(zzbp zzbpVar, zzcn zzcnVar) {
            zzey zzeyVar = (zzey) zzbpVar;
            return zzpc.zza("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey", ((zzwm) ((zzajy) zzwm.zzb().zza(zzfb.zzb(zzeyVar.zzb())).zze())).a_(), zzvq.zzb.REMOTE, zzfb.zza(zzeyVar.zzb().zzc()), zzeyVar.zza());
        }
    }, zzey.class, zzpc.class);
    private static final zzms<zzpc> zze = zzms.zza(new zzmu() { // from class: com.google.android.gms.internal.firebase-auth-api.zzff
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzmu
        public final zzbp zza(zzpj zzpjVar, zzcn zzcnVar) {
            return zzfb.zzb((zzpc) zzpjVar, zzcnVar);
        }
    }, zza, zzpc.class);

    /* JADX INFO: Access modifiers changed from: private */
    public static zzey zzb(zzpc zzpcVar, @Nullable zzcn zzcnVar) throws GeneralSecurityException {
        if (!zzpcVar.zzf().equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsEnvelopeAeadProtoSerialization.parseKey");
        }
        try {
            zzwm zzwmVarZza = zzwm.zza(zzpcVar.zzd(), zzajk.zza());
            if (zzwmVarZza.zza() == 0) {
                return zzey.zza(zza(zzwmVarZza.zzd(), zzpcVar.zzb()), zzpcVar.zze());
            }
            throw new GeneralSecurityException("KmsEnvelopeAeadKeys are only accepted with version 0, got " + String.valueOf(zzwmVarZza));
        } catch (zzakf e) {
            throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKey failed: ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzfa zzb(zzpf zzpfVar) throws GeneralSecurityException {
        if (!zzpfVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsEnvelopeAeadProtoSerialization.parseParameters: " + zzpfVar.zza().zzf());
        }
        try {
            return zza(zzwp.zza(zzpfVar.zza().zze(), zzajk.zza()), zzpfVar.zza().zzd());
        } catch (zzakf e) {
            throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKeyFormat failed: ", e);
        }
    }

    private static zzfa zza(zzwp zzwpVar, zzws zzwsVar) throws GeneralSecurityException {
        zzfa.zzc zzcVar;
        zzfa.zzb zzbVar;
        zzch zzchVarZza = zzcp.zza(((zzvu) ((zzajy) zzvu.zza().zza(zzwpVar.zza().zzf()).zza(zzwpVar.zza().zze()).zza(zzws.RAW).zze())).zzk());
        if (zzchVarZza instanceof zzdv) {
            zzcVar = zzfa.zzc.zza;
        } else if (zzchVarZza instanceof zzei) {
            zzcVar = zzfa.zzc.zzc;
        } else if (zzchVarZza instanceof zzga) {
            zzcVar = zzfa.zzc.zzb;
        } else if (zzchVarZza instanceof zzdg) {
            zzcVar = zzfa.zzc.zzd;
        } else if (zzchVarZza instanceof zzdm) {
            zzcVar = zzfa.zzc.zze;
        } else if (zzchVarZza instanceof zzec) {
            zzcVar = zzfa.zzc.zzf;
        } else {
            throw new GeneralSecurityException("Unsupported DEK parameters when parsing " + String.valueOf(zzchVarZza));
        }
        zzfa.zza zzaVar = new zzfa.zza();
        switch (zzwsVar) {
            case TINK:
                zzbVar = zzfa.zzb.zza;
                break;
            case RAW:
                zzbVar = zzfa.zzb.zzb;
                break;
            default:
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzwsVar.zza());
        }
        return zzaVar.zza(zzbVar).zza(zzwpVar.zze()).zza((zzcw) zzchVarZza).zza(zzcVar).zza();
    }

    private static zzwp zzb(zzfa zzfaVar) throws GeneralSecurityException {
        try {
            return (zzwp) ((zzajy) zzwp.zzb().zza(zzfaVar.zzd()).zza(zzvu.zza(zzcp.zza(zzfaVar.zzb()), zzajk.zza())).zze());
        } catch (zzakf e) {
            throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKeyFormat failed: ", e);
        }
    }

    private static zzws zza(zzfa.zzb zzbVar) throws GeneralSecurityException {
        if (zzfa.zzb.zza.equals(zzbVar)) {
            return zzws.TINK;
        }
        if (zzfa.zzb.zzb.equals(zzbVar)) {
            return zzws.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zzbVar));
    }

    public static void zza() throws GeneralSecurityException {
        zzof zzofVarZza = zzof.zza();
        zzofVarZza.zza(zzb);
        zzofVarZza.zza(zzc);
        zzofVarZza.zza(zzd);
        zzofVarZza.zza(zze);
    }
}
