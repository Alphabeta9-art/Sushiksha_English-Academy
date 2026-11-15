package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzoy<P> {
    private final Class<P> zza;
    private Map<zzzc, List<zzpb<P>>> zzb;
    private final List<zzpb<P>> zzc;
    private zzpb<P> zzd;
    private zzng zze;

    private final zzoy<P> zza(P p, zzbp zzbpVar, zzwa.zzb zzbVar, boolean z) throws GeneralSecurityException {
        if (this.zzb == null) {
            throw new IllegalStateException("addEntry cannot be called after build");
        }
        if (p == null) {
            throw new NullPointerException("`fullPrimitive` must not be null");
        }
        if (zzbVar.zzc() != zzvv.ENABLED) {
            throw new GeneralSecurityException("only ENABLED key is allowed");
        }
        zzpb<P> zzpbVar = new zzpb<>(p, zzzc.zza(zzbj.zza(zzbVar)), zzbVar.zzc(), zzbVar.zzf(), zzbVar.zza(), zzbVar.zzb().zzf(), zzbpVar);
        Map<zzzc, List<zzpb<P>>> map = this.zzb;
        List<zzpb<P>> list = this.zzc;
        ArrayList arrayList = new ArrayList();
        arrayList.add(zzpbVar);
        List<zzpb<P>> listPut = map.put(((zzpb) zzpbVar).zzb, Collections.unmodifiableList(arrayList));
        if (listPut != null) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(listPut);
            arrayList2.add(zzpbVar);
            map.put(((zzpb) zzpbVar).zzb, Collections.unmodifiableList(arrayList2));
        }
        list.add(zzpbVar);
        if (z) {
            if (this.zzd != null) {
                throw new IllegalStateException("you cannot set two primary primitives");
            }
            this.zzd = zzpbVar;
        }
        return this;
    }

    public final zzoy<P> zza(P p, zzbp zzbpVar, zzwa.zzb zzbVar) throws GeneralSecurityException {
        return zza(p, zzbpVar, zzbVar, false);
    }

    public final zzoy<P> zzb(P p, zzbp zzbpVar, zzwa.zzb zzbVar) throws GeneralSecurityException {
        return zza(p, zzbpVar, zzbVar, true);
    }

    public final zzoy<P> zza(zzng zzngVar) {
        if (this.zzb == null) {
            throw new IllegalStateException("setAnnotations cannot be called after build");
        }
        this.zze = zzngVar;
        return this;
    }

    public final zzoz<P> zza() throws GeneralSecurityException {
        if (this.zzb == null) {
            throw new IllegalStateException("build cannot be called twice");
        }
        zzoz<P> zzozVar = new zzoz<>(this.zzb, this.zzc, this.zzd, this.zze, this.zza);
        this.zzb = null;
        return zzozVar;
    }

    private zzoy(Class<P> cls) {
        this.zzb = new HashMap();
        this.zzc = new ArrayList();
        this.zza = cls;
        this.zze = zzng.zza;
    }
}
