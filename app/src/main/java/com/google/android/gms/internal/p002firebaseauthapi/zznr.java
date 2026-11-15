package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zznr {
    public static final zznk zza = new zznt();

    public static <P> zznn zza(zzoz<P> zzozVar) {
        zzbr zzbrVar;
        zznm zznmVar = new zznm();
        zznmVar.zza(zzozVar.zza());
        Iterator<List<zzpb<P>>> it = zzozVar.zzd().iterator();
        while (it.hasNext()) {
            for (zzpb<P> zzpbVar : it.next()) {
                switch (zznq.zza[zzpbVar.zzb().ordinal()]) {
                    case 1:
                        zzbrVar = zzbr.zza;
                        break;
                    case 2:
                        zzbrVar = zzbr.zzb;
                        break;
                    case 3:
                        zzbrVar = zzbr.zzc;
                        break;
                    default:
                        throw new IllegalStateException("Unknown key status");
                }
                int iZza = zzpbVar.zza();
                String strZze = zzpbVar.zze();
                if (strZze.startsWith("type.googleapis.com/google.crypto.")) {
                    strZze = strZze.substring(34);
                }
                zznmVar.zza(zzbrVar, iZza, strZze, zzpbVar.zzc().name());
            }
        }
        if (zzozVar.zzb() != null) {
            zznmVar.zza(zzozVar.zzb().zza());
        }
        try {
            return zznmVar.zza();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}
