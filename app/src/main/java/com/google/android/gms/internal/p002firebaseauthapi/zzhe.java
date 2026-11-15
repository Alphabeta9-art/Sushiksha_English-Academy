package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzhe {
    private static final zzzc zza = zzpr.zzb("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
    private static final zzon<zzei, zzpf> zzb = zzon.zza(new zzop() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhd
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzop
        public final zzpj zza(zzch zzchVar) {
            return zzpf.zzb((zzvu) ((zzajy) zzvu.zza().zza("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key").zza(zztr.zzb().a_()).zza(zzhe.zza(((zzei) zzchVar).zzb())).zze()));
        }
    }, zzei.class, zzpf.class);
    private static final zzoj<zzpf> zzc = zzoj.zza(new zzol() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhg
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzol
        public final zzch zza(zzpj zzpjVar) {
            return zzhe.zzb((zzpf) zzpjVar);
        }
    }, zza, zzpf.class);
    private static final zzmw<zzee, zzpc> zzd = zzmw.zza(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhf
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzmy
        public final zzpj zza(zzbp zzbpVar, zzcn zzcnVar) {
            zzee zzeeVar = (zzee) zzbpVar;
            return zzpc.zza("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key", ((zzto) ((zzajy) zzto.zzb().zza(zzaip.zza(zzeeVar.zzd().zza(zzcn.zza(zzcnVar)))).zze())).a_(), zzvq.zzb.SYMMETRIC, zzhe.zza(zzeeVar.zzb().zzb()), zzeeVar.zza());
        }
    }, zzee.class, zzpc.class);
    private static final zzms<zzpc> zze = zzms.zza(new zzmu() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhi
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzmu
        public final zzbp zza(zzpj zzpjVar, zzcn zzcnVar) {
            return zzhe.zzb((zzpc) zzpjVar, zzcnVar);
        }
    }, zza, zzpc.class);

    /* JADX INFO: Access modifiers changed from: private */
    public static zzee zzb(zzpc zzpcVar, @Nullable zzcn zzcnVar) throws GeneralSecurityException {
        if (!zzpcVar.zzf().equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
            throw new IllegalArgumentException("Wrong type URL in call to ChaCha20Poly1305ProtoSerialization.parseKey");
        }
        try {
            zzto zztoVarZza = zzto.zza(zzpcVar.zzd(), zzajk.zza());
            if (zztoVarZza.zza() == 0) {
                return zzee.zza(zza(zzpcVar.zzb()), zzze.zza(zztoVarZza.zzd().zzd(), zzcn.zza(zzcnVar)), zzpcVar.zze());
            }
            throw new GeneralSecurityException("Only version 0 keys are accepted");
        } catch (zzakf e) {
            throw new GeneralSecurityException("Parsing ChaCha20Poly1305Key failed");
        }
    }

    private static zzei.zza zza(zzws zzwsVar) throws GeneralSecurityException {
        switch (zzwsVar) {
            case TINK:
                return zzei.zza.zza;
            case CRUNCHY:
            case LEGACY:
                return zzei.zza.zzb;
            case RAW:
                return zzei.zza.zzc;
            default:
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzwsVar.zza());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzei zzb(zzpf zzpfVar) throws GeneralSecurityException {
        if (!zzpfVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
            throw new IllegalArgumentException("Wrong type URL in call to ChaCha20Poly1305ProtoSerialization.parseParameters: " + zzpfVar.zza().zzf());
        }
        try {
            zztr.zza(zzpfVar.zza().zze(), zzajk.zza());
            return zzei.zza(zza(zzpfVar.zza().zzd()));
        } catch (zzakf e) {
            throw new GeneralSecurityException("Parsing ChaCha20Poly1305Parameters failed: ", e);
        }
    }

    private static zzws zza(zzei.zza zzaVar) throws GeneralSecurityException {
        if (zzei.zza.zza.equals(zzaVar)) {
            return zzws.TINK;
        }
        if (zzei.zza.zzb.equals(zzaVar)) {
            return zzws.CRUNCHY;
        }
        if (zzei.zza.zzc.equals(zzaVar)) {
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
