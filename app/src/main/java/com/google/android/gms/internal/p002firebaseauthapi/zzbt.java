package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzbt {
    private final zzwa zza;
    private final List<zzca> zzb;
    private final zzng zzc;

    private static zzbp zza(zzwa.zzb zzbVar) throws GeneralSecurityException {
        zzpc zzpcVarZza = zzpc.zza(zzbVar.zzb().zzf(), zzbVar.zzb().zze(), zzbVar.zzb().zzb(), zzbVar.zzf(), zzbVar.zzf() == zzws.RAW ? null : Integer.valueOf(zzbVar.zza()));
        zzof zzofVarZza = zzof.zza();
        zzcn zzcnVarZza = zzcn.zza();
        return !zzofVarZza.zzb(zzpcVarZza) ? new zznc(zzpcVarZza, zzcnVarZza) : zzofVarZza.zza(zzpcVarZza, zzcnVarZza);
    }

    private static zzbr zza(zzvv zzvvVar) throws GeneralSecurityException {
        switch (zzbw.zza[zzvvVar.ordinal()]) {
            case 1:
                return zzbr.zza;
            case 2:
                return zzbr.zzb;
            case 3:
                return zzbr.zzc;
            default:
                throw new GeneralSecurityException("Unknown key status");
        }
    }

    static final zzbt zza(zzwa zzwaVar) throws GeneralSecurityException {
        zzd(zzwaVar);
        return new zzbt(zzwaVar, zzc(zzwaVar));
    }

    public static final zzbt zza(zzbu zzbuVar) throws GeneralSecurityException {
        return new zzbv().zza(new zzby(zzbuVar.zza()).zzb().zza()).zza();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ProcessVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Method arg registers not loaded: com.google.android.gms.internal.firebase-auth-api.zzca.<init>(com.google.android.gms.internal.firebase-auth-api.zzbp, com.google.android.gms.internal.firebase-auth-api.zzbr, int, boolean, com.google.android.gms.internal.firebase-auth-api.zzbz):void, class status: GENERATED_AND_UNLOADED
        	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:309)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isArgUnused(ProcessVariables.java:146)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.lambda$isVarUnused$0(ProcessVariables.java:131)
        	at jadx.core.utils.ListUtils.allMatch(ListUtils.java:224)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isVarUnused(ProcessVariables.java:131)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.processBlock(ProcessVariables.java:82)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:93)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.removeUnusedResults(ProcessVariables.java:73)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.visit(ProcessVariables.java:48)
        */
    public final com.google.android.gms.internal.p002firebaseauthapi.zzbt zza() throws java.security.GeneralSecurityException {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzbt.zza():com.google.android.gms.internal.firebase-auth-api.zzbt");
    }

    public static final zzbt zza(zzcb zzcbVar, zzbg zzbgVar, byte[] bArr) throws GeneralSecurityException, IOException {
        zzuo zzuoVarZza = zzcbVar.zza();
        if (zzuoVarZza == null || zzuoVarZza.zzc().zzb() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        return zza(zza(zzuoVarZza, zzbgVar, bArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzwa.zzb zzb(zzbp zzbpVar, zzbr zzbrVar, int i) throws GeneralSecurityException {
        zzvv zzvvVar;
        zzpc zzpcVar = (zzpc) zzof.zza().zza(zzbpVar, zzpc.class, zzcn.zza());
        Integer numZze = zzpcVar.zze();
        if (numZze != null && numZze.intValue() != i) {
            throw new GeneralSecurityException("Wrong ID set for key with ID requirement");
        }
        if (zzbr.zza.equals(zzbrVar)) {
            zzvvVar = zzvv.ENABLED;
        } else if (zzbr.zzb.equals(zzbrVar)) {
            zzvvVar = zzvv.DISABLED;
        } else if (zzbr.zzc.equals(zzbrVar)) {
            zzvvVar = zzvv.DESTROYED;
        } else {
            throw new IllegalStateException("Unknown key status");
        }
        return (zzwa.zzb) ((zzajy) zzwa.zzb.zzd().zza(zzvq.zza().zza(zzpcVar.zzf()).zza(zzpcVar.zzd()).zza(zzpcVar.zza())).zza(zzvvVar).zza(i).zza(zzpcVar.zzb()).zze());
    }

    private static zzwa zza(zzuo zzuoVar, zzbg zzbgVar, byte[] bArr) throws GeneralSecurityException {
        try {
            zzwa zzwaVarZza = zzwa.zza(zzbgVar.zza(zzuoVar.zzc().zzd(), bArr), zzajk.zza());
            zzd(zzwaVarZza);
            return zzwaVarZza;
        } catch (zzakf e) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    final zzwa zzb() {
        return this.zza;
    }

    public final <P> P zza(zzbk zzbkVar, Class<P> cls) throws GeneralSecurityException {
        if (!(zzbkVar instanceof zzmq)) {
            throw new GeneralSecurityException("Currently only subclasses of InternalConfiguration are accepted");
        }
        zzmq zzmqVar = (zzmq) zzbkVar;
        Class<?> clsZza = zzmqVar.zza(cls);
        if (clsZza == null) {
            throw new GeneralSecurityException("No wrapper found for " + cls.getName());
        }
        return (P) zza(zzmqVar, cls, clsZza);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <B, P> P zza(zzmq zzmqVar, Class<P> cls, Class<B> cls2) throws GeneralSecurityException {
        zzcs.zzb(this.zza);
        zzoy zzoyVarZza = zzoz.zza(cls2);
        zzoyVarZza.zza(this.zzc);
        for (int i = 0; i < this.zzb.size(); i++) {
            zzwa.zzb zzbVarZza = this.zza.zza(i);
            if (zzbVarZza.zzc().equals(zzvv.ENABLED)) {
                zzca zzcaVar = this.zzb.get(i);
                if (zzcaVar == null) {
                    throw new GeneralSecurityException("Key parsing of key with index " + i + " and type_url " + zzbVarZza.zzb().zzf() + " failed, unable to get primitive");
                }
                zzbp zzbpVarZzb = zzcaVar.zzb();
                try {
                    Object objZza = zzmqVar.zza(zzbpVarZzb, cls2);
                    if (zzbVarZza.zza() == this.zza.zzb()) {
                        zzoyVarZza.zzb(objZza, zzbpVarZzb, zzbVarZza);
                    } else {
                        zzoyVarZza.zza(objZza, zzbpVarZzb, zzbVarZza);
                    }
                } catch (GeneralSecurityException e) {
                    throw new GeneralSecurityException("Unable to get primitive " + String.valueOf(cls2) + " for key of type " + zzbVarZza.zzb().zzf() + ", see https://developers.google.com/tink/faq/registration_errors", e);
                }
            }
        }
        return (P) zzmqVar.zza(zzoyVarZza.zza(), cls);
    }

    public final String toString() {
        return zzcs.zza(this.zza).toString();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ProcessVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Method arg registers not loaded: com.google.android.gms.internal.firebase-auth-api.zzca.<init>(com.google.android.gms.internal.firebase-auth-api.zzbp, com.google.android.gms.internal.firebase-auth-api.zzbr, int, boolean, com.google.android.gms.internal.firebase-auth-api.zzbz):void, class status: GENERATED_AND_UNLOADED
        	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:309)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isArgUnused(ProcessVariables.java:146)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.lambda$isVarUnused$0(ProcessVariables.java:131)
        	at jadx.core.utils.ListUtils.allMatch(ListUtils.java:224)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isVarUnused(ProcessVariables.java:131)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.processBlock(ProcessVariables.java:82)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:93)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.removeUnusedResults(ProcessVariables.java:73)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.visit(ProcessVariables.java:48)
        */
    private static java.util.List<com.google.android.gms.internal.p002firebaseauthapi.zzca> zzc(com.google.android.gms.internal.p002firebaseauthapi.zzwa r9) {
        /*
            java.util.ArrayList r1 = new java.util.ArrayList
            int r0 = r9.zza()
            r1.<init>(r0)
            java.util.List r0 = r9.zze()
            java.util.Iterator r2 = r0.iterator()
        L11:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L47
            java.lang.Object r0 = r2.next()
            com.google.android.gms.internal.firebase-auth-api.zzwa$zzb r0 = (com.google.android.gms.internal.firebase-auth-api.zzwa.zzb) r0
            int r6 = r0.zza()
            com.google.android.gms.internal.firebase-auth-api.zzbp r4 = zza(r0)     // Catch: java.security.GeneralSecurityException -> L41
            com.google.android.gms.internal.firebase-auth-api.zzca r3 = new com.google.android.gms.internal.firebase-auth-api.zzca     // Catch: java.security.GeneralSecurityException -> L41
            com.google.android.gms.internal.firebase-auth-api.zzvv r0 = r0.zzc()     // Catch: java.security.GeneralSecurityException -> L41
            com.google.android.gms.internal.firebase-auth-api.zzbr r5 = zza(r0)     // Catch: java.security.GeneralSecurityException -> L41
            int r0 = r9.zzb()     // Catch: java.security.GeneralSecurityException -> L41
            if (r6 != r0) goto L37
            r0 = 1
            goto L38
        L37:
            r0 = 0
        L38:
            r7 = r0
            r8 = 0
            r3.<init>(r4, r5, r6, r7)     // Catch: java.security.GeneralSecurityException -> L41
            r1.add(r3)     // Catch: java.security.GeneralSecurityException -> L41
            goto L11
        L41:
            r0 = move-exception
            r0 = 0
            r1.add(r0)
            goto L11
        L47:
            java.util.List r9 = java.util.Collections.unmodifiableList(r1)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzbt.zzc(com.google.android.gms.internal.firebase-auth-api.zzwa):java.util.List");
    }

    private zzbt(zzwa zzwaVar, List<zzca> list) {
        this.zza = zzwaVar;
        this.zzb = list;
        this.zzc = zzng.zza;
    }

    private zzbt(zzwa zzwaVar, List<zzca> list, zzng zzngVar) {
        this.zza = zzwaVar;
        this.zzb = list;
        this.zzc = zzngVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzd(zzwa zzwaVar) throws GeneralSecurityException {
        if (zzwaVar == null || zzwaVar.zza() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    public final void zza(zzce zzceVar) throws GeneralSecurityException, IOException {
        for (zzwa.zzb zzbVar : this.zza.zze()) {
            if (zzbVar.zzb().zzb() == zzvq.zzb.UNKNOWN_KEYMATERIAL || zzbVar.zzb().zzb() == zzvq.zzb.SYMMETRIC || zzbVar.zzb().zzb() == zzvq.zzb.ASYMMETRIC_PRIVATE) {
                throw new GeneralSecurityException(String.format("keyset contains key material of type %s for type url %s", zzbVar.zzb().zzb().name(), zzbVar.zzb().zzf()));
            }
        }
        zzceVar.zza(this.zza);
    }

    public final void zza(zzce zzceVar, zzbg zzbgVar, byte[] bArr) throws GeneralSecurityException, IOException {
        zzwa zzwaVar = this.zza;
        zzceVar.zza((zzuo) ((zzajy) zzuo.zza().zza(zzaip.zza(zzbgVar.zzb(zzwaVar.zzk(), bArr))).zza(zzcs.zza(zzwaVar)).zze()));
    }
}
