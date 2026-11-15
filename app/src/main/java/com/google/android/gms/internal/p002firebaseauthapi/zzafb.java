package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.collection.ArrayMap;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.google.firebase.FirebaseApp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzafb {
    private static final Map<String, zzafe> zza = new ArrayMap();
    private static final Map<String, List<WeakReference<zzafd>>> zzb = new ArrayMap();

    private static String zza(String str, int i, boolean z) {
        return z ? "http://[" + str + "]:" + i + DomExceptionUtils.SEPARATOR : "http://" + str + ":" + i + DomExceptionUtils.SEPARATOR;
    }

    public static String zza(String str) {
        zzafe zzafeVar;
        synchronized (zza) {
            zzafeVar = zza.get(str);
        }
        if (zzafeVar == null) {
            throw new IllegalStateException("Tried to get the emulator widget endpoint, but no emulator endpoint overrides found.");
        }
        return zza(zzafeVar.zzb(), zzafeVar.zza(), zzafeVar.zzb().contains(":")) + "emulator/auth/handler";
    }

    public static String zzb(String str) {
        zzafe zzafeVar;
        String str2;
        synchronized (zza) {
            zzafeVar = zza.get(str);
        }
        if (zzafeVar == null) {
            str2 = "https://";
        } else {
            str2 = "" + zza(zzafeVar.zzb(), zzafeVar.zza(), zzafeVar.zzb().contains(":"));
        }
        return str2 + "www.googleapis.com/identitytoolkit/v3/relyingparty";
    }

    public static String zzc(String str) {
        zzafe zzafeVar;
        String str2;
        synchronized (zza) {
            zzafeVar = zza.get(str);
        }
        if (zzafeVar == null) {
            str2 = "https://";
        } else {
            str2 = "" + zza(zzafeVar.zzb(), zzafeVar.zza(), zzafeVar.zzb().contains(":"));
        }
        return str2 + "identitytoolkit.googleapis.com/v2";
    }

    public static String zzd(String str) {
        zzafe zzafeVar;
        String str2;
        synchronized (zza) {
            zzafeVar = zza.get(str);
        }
        if (zzafeVar == null) {
            str2 = "https://";
        } else {
            str2 = "" + zza(zzafeVar.zzb(), zzafeVar.zza(), zzafeVar.zzb().contains(":"));
        }
        return str2 + "securetoken.googleapis.com/v1";
    }

    public static void zza(String str, zzafd zzafdVar) {
        synchronized (zzb) {
            if (zzb.containsKey(str)) {
                zzb.get(str).add(new WeakReference<>(zzafdVar));
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new WeakReference(zzafdVar));
                zzb.put(str, arrayList);
            }
        }
    }

    public static void zza(FirebaseApp firebaseApp, String str, int i) {
        String apiKey = firebaseApp.getOptions().getApiKey();
        synchronized (zza) {
            zza.put(apiKey, new zzafe(str, i));
        }
        synchronized (zzb) {
            if (zzb.containsKey(apiKey)) {
                Iterator<WeakReference<zzafd>> it = zzb.get(apiKey).iterator();
                boolean z = false;
                while (it.hasNext()) {
                    zzafd zzafdVar = it.next().get();
                    if (zzafdVar != null) {
                        zzafdVar.zza();
                        z = true;
                    }
                }
                if (!z) {
                    zza.remove(apiKey);
                }
            }
        }
    }

    public static boolean zza(FirebaseApp firebaseApp) {
        return zza.containsKey(firebaseApp.getOptions().getApiKey());
    }
}
