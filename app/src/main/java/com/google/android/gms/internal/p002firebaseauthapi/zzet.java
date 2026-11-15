package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzet {
    private static final zzzc zza = zzpr.zzb("type.googleapis.com/google.crypto.tink.KmsAeadKey");
    private static final zzon<zzer, zzpf> zzb = zzon.zza(new zzop() { // from class: com.google.android.gms.internal.firebase-auth-api.zzev
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzop
        public final zzpj zza(zzch zzchVar) {
            zzer zzerVar = (zzer) zzchVar;
            return zzpf.zzb((zzvu) ((zzajy) zzvu.zza().zza("type.googleapis.com/google.crypto.tink.KmsAeadKey").zza(((zzwj) ((zzajy) zzwj.zza().zza(zzerVar.zzc()).zze())).a_()).zza(zzet.zza(zzerVar.zzb())).zze()));
        }
    }, zzer.class, zzpf.class);
    private static final zzoj<zzpf> zzc = zzoj.zza(new zzol() { // from class: com.google.android.gms.internal.firebase-auth-api.zzeu
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzol
        public final zzch zza(zzpj zzpjVar) {
            return zzet.zzb((zzpf) zzpjVar);
        }
    }, zza, zzpf.class);
    private static final zzmw<zzes, zzpc> zzd = zzmw.zza(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzex
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzmy
        public final zzpj zza(zzbp zzbpVar, zzcn zzcnVar) {
            zzes zzesVar = (zzes) zzbpVar;
            return zzpc.zza("type.googleapis.com/google.crypto.tink.KmsAeadKey", ((zzwg) ((zzajy) zzwg.zzb().zza((zzwj) ((zzajy) zzwj.zza().zza(zzesVar.zzb().zzc()).zze())).zze())).a_(), zzvq.zzb.REMOTE, zzet.zza(zzesVar.zzb().zzb()), zzesVar.zza());
        }
    }, zzes.class, zzpc.class);
    private static final zzms<zzpc> zze = zzms.zza(new zzmu() { // from class: com.google.android.gms.internal.firebase-auth-api.zzew
        @Override // com.google.android.gms.internal.p002firebaseauthapi.zzmu
        public final zzbp zza(zzpj zzpjVar, zzcn zzcnVar) {
            return zzet.zzb((zzpc) zzpjVar, zzcnVar);
        }
    }, zza, zzpc.class);

    /* JADX INFO: Access modifiers changed from: private */
    public static zzes zzb(zzpc zzpcVar, @Nullable zzcn zzcnVar) throws GeneralSecurityException {
        if (!zzpcVar.zzf().equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsAeadProtoSerialization.parseKey");
        }
        try {
            zzwg zzwgVarZza = zzwg.zza(zzpcVar.zzd(), zzajk.zza());
            if (zzwgVarZza.zza() == 0) {
                return zzes.zza(zzer.zza(zzwgVarZza.zzd().zzd(), zza(zzpcVar.zzb())), zzpcVar.zze());
            }
            throw new GeneralSecurityException("KmsAeadKey are only accepted with version 0, got " + String.valueOf(zzwgVarZza));
        } catch (zzakf e) {
            throw new GeneralSecurityException("Parsing KmsAeadKey failed: ", e);
        }
    }

    private static zzer.zza zza(zzws zzwsVar) throws GeneralSecurityException {
        switch (zzwsVar) {
            case TINK:
                return zzer.zza.zza;
            case RAW:
                return zzer.zza.zzb;
            default:
                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzwsVar.zza());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzer zzb(zzpf zzpfVar) throws GeneralSecurityException {
        if (!zzpfVar.zza().zzf().equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsAeadProtoSerialization.parseParameters: " + zzpfVar.zza().zzf());
        }
        try {
            return zzer.zza(zzwj.zza(zzpfVar.zza().zze(), zzajk.zza()).zzd(), zza(zzpfVar.zza().zzd()));
        } catch (zzakf e) {
            throw new GeneralSecurityException("Parsing KmsAeadKeyFormat failed: ", e);
        }
    }

    private static zzws zza(zzer.zza zzaVar) throws GeneralSecurityException {
        if (zzer.zza.zza.equals(zzaVar)) {
            return zzws.TINK;
        }
        if (zzer.zza.zzb.equals(zzaVar)) {
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
