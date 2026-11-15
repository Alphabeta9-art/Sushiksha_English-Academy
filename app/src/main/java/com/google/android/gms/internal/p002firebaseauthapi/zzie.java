package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzie {
    private static final zzzc zza = zzpr.zzb("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
    private static final zzon<zzga, zzpf> zzb = zzon.zza(new zzop() { // from class: com.google.android.gms.internal.firebase-auth-api.zzid
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzop
        public final zzpj zza(zzch zzchVar) {
            return zzpf.zzb((zzvu) ((zzajy) zzvu.zza().zza("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key").zza(zzxk.zzc().a_()).zza(zzie.zza(((zzga) zzchVar).zzb())).zze()));
        }
    }, zzga.class, zzpf.class);
    private static final zzoj<zzpf> zzc = zzoj.zza(new zzol() { // from class: com.google.android.gms.internal.firebase-auth-api.zzig
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzol
        public final zzch zza(zzpj zzpjVar) {
            return zzie.zzb((zzpf) zzpjVar);
        }
    }, zza, zzpf.class);
    private static final zzmw<zzfv, zzpc> zzd = zzmw.zza(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzif
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzmy
        public final zzpj zza(zzbp zzbpVar, zzcn zzcnVar) {
            zzfv zzfvVar = (zzfv) zzbpVar;
            return zzpc.zza("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key", ((zzxh) ((zzajy) zzxh.zzb().zza(zzaip.zza(zzfvVar.zzd().zza(zzcn.zza(zzcnVar)))).zze())).a_(), zzvq.zzb.SYMMETRIC, zzie.zza(zzfvVar.zzb().zzb()), zzfvVar.zza());
        }
    }, zzfv.class, zzpc.class);
    private static final zzms<zzpc> zze = zzms.zza(new zzmu() { // from class: com.google.android.gms.internal.firebase-auth-api.zzii
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzmu
        public final zzbp zza(zzpj zzpjVar, zzcn zzcnVar) {
            return zzie.zzb((zzpc) zzpjVar, zzcnVar);
        }
    }, zza, zzpc.class);

    /* JADX INFO: Access modifiers changed from: private */
    public static zzfv zzb(zzpc zzpcVar, @Nullable zzcn zzcnVar) throws GeneralSecurityException {
        if (!zzpcVar.zzf().equals("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key")) {
            throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305ProtoSerialization.parseKey");
        }
        try {
            zzxh zzxhVarZza = zzxh.zza(zzpcVar.zzd(), zzajk.zza());
            if (zzxhVarZza.zza() == 0) {
                return zzfv.zza(zza(zzpcVar.zzb()), zzze.zza(zzxhVarZza.zzd().zzd(), zzcn.zza(zzcnVar)), zzpcVar.zze());
            }
            throw new GeneralSecurityException("Only version 0 keys are accepted");
        } catch (zzakf e) {
            throw new GeneralSecurityException("Parsing XChaCha20Poly1305Key failed");
        }
    }

    private static zzga.zza zza(zzws zzwsVar) throws GeneralSecurityException {
        switch (zzwsVar) {
            case TINK:
                return zzga.zza.zza;
            case CRUNCHY:
            case LEGACY:
                return zzga.zza.zzb;
            case RAW:
                return zzga.zza.zzc;
            default:
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzwsVar.zza());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzga zzb(zzpf zzpfVar) throws GeneralSecurityException {
        if (!zzpfVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key")) {
            throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305ProtoSerialization.parseParameters: " + zzpfVar.zza().zzf());
        }
        try {
            if (zzxk.zza(zzpfVar.zza().zze(), zzajk.zza()).zza() != 0) {
                throw new GeneralSecurityException("Only version 0 parameters are accepted");
            }
            return zzga.zza(zza(zzpfVar.zza().zzd()));
        } catch (zzakf e) {
            throw new GeneralSecurityException("Parsing XChaCha20Poly1305Parameters failed: ", e);
        }
    }

    private static zzws zza(zzga.zza zzaVar) throws GeneralSecurityException {
        if (zzga.zza.zza.equals(zzaVar)) {
            return zzws.TINK;
        }
        if (zzga.zza.zzb.equals(zzaVar)) {
            return zzws.CRUNCHY;
        }
        if (zzga.zza.zzc.equals(zzaVar)) {
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
