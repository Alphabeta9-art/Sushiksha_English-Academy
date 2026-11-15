package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzhv {
    private static final zzzc zza = zzpr.zzb("type.googleapis.com/google.crypto.tink.XAesGcmKey");
    private static final zzon<zzft, zzpf> zzb = zzon.zza(new zzop() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhy
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzop
        public final zzpj zza(zzch zzchVar) {
            zzft zzftVar = (zzft) zzchVar;
            return zzpf.zzb((zzvu) ((zzajy) zzvu.zza().zza("type.googleapis.com/google.crypto.tink.XAesGcmKey").zza(((zzxb) ((zzajy) zzxb.zzb().zza((zzxe) ((zzajy) zzxe.zzb().zza(zzftVar.zzb()).zze())).zze())).a_()).zza(zzhv.zza(zzftVar.zzc())).zze()));
        }
    }, zzft.class, zzpf.class);
    private static final zzoj<zzpf> zzc = zzoj.zza(new zzol() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhx
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzol
        public final zzch zza(zzpj zzpjVar) {
            return zzhv.zzb((zzpf) zzpjVar);
        }
    }, zza, zzpf.class);
    private static final zzmw<zzfp, zzpc> zzd = zzmw.zza(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzia
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzmy
        public final zzpj zza(zzbp zzbpVar, zzcn zzcnVar) {
            zzfp zzfpVar = (zzfp) zzbpVar;
            return zzpc.zza("type.googleapis.com/google.crypto.tink.XAesGcmKey", ((zzwy) ((zzajy) zzwy.zzb().zza(zzaip.zza(zzfpVar.zzd().zza(zzcn.zza(zzcnVar)))).zza((zzxe) ((zzajy) zzxe.zzb().zza(zzfpVar.zzb().zzb()).zze())).zze())).a_(), zzvq.zzb.SYMMETRIC, zzhv.zza(zzfpVar.zzb().zzc()), zzfpVar.zza());
        }
    }, zzfp.class, zzpc.class);
    private static final zzms<zzpc> zze = zzms.zza(new zzmu() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhz
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzmu
        public final zzbp zza(zzpj zzpjVar, zzcn zzcnVar) {
            return zzhv.zzb((zzpc) zzpjVar, zzcnVar);
        }
    }, zza, zzpc.class);

    /* JADX INFO: Access modifiers changed from: private */
    public static zzfp zzb(zzpc zzpcVar, @Nullable zzcn zzcnVar) throws GeneralSecurityException {
        if (!zzpcVar.zzf().equals("type.googleapis.com/google.crypto.tink.XAesGcmKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to XAesGcmProtoSerialization.parseKey");
        }
        try {
            zzwy zzwyVarZza = zzwy.zza(zzpcVar.zzd(), zzajk.zza());
            if (zzwyVarZza.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            if (zzwyVarZza.zze().zzb() == 32) {
                return zzfp.zza(zzft.zza(zza(zzpcVar.zzb()), zzwyVarZza.zzd().zza()), zzze.zza(zzwyVarZza.zze().zzd(), zzcn.zza(zzcnVar)), zzpcVar.zze());
            }
            throw new GeneralSecurityException("Only 32 byte key size is accepted");
        } catch (zzakf e) {
            throw new GeneralSecurityException("Parsing XAesGcmKey failed");
        }
    }

    private static zzft.zza zza(zzws zzwsVar) throws GeneralSecurityException {
        switch (zzwsVar) {
            case TINK:
                return zzft.zza.zza;
            case RAW:
                return zzft.zza.zzb;
            default:
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzwsVar.zza());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzft zzb(zzpf zzpfVar) throws GeneralSecurityException {
        if (!zzpfVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.XAesGcmKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to XAesGcmProtoSerialization.parseParameters: " + zzpfVar.zza().zzf());
        }
        try {
            zzxb zzxbVarZza = zzxb.zza(zzpfVar.zza().zze(), zzajk.zza());
            if (zzxbVarZza.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 parameters are accepted");
            }
            return zzft.zza(zza(zzpfVar.zza().zzd()), zzxbVarZza.zzd().zza());
        } catch (zzakf e) {
            throw new GeneralSecurityException("Parsing XAesGcmParameters failed: ", e);
        }
    }

    private static zzws zza(zzft.zza zzaVar) throws GeneralSecurityException {
        if (Objects.equals(zzaVar, zzft.zza.zza)) {
            return zzws.TINK;
        }
        if (Objects.equals(zzaVar, zzft.zza.zzb)) {
            return zzws.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zzaVar));
    }

    public static void zza() throws GeneralSecurityException {
        zzof zzofVarZza = zzof.zza();
        zzofVarZza.zza(zzb);
        zzofVarZza.zza(zzc);
        zzofVarZza.zza(zzd);
        zzofVarZza.zza(zze);
    }
}
