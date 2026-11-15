package com.google.android.gms.internal.p002firebaseauthapi;

import android.app.Activity;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.util.DefaultClock;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzaer {
    private static final Map<String, zzaet> zza = new ArrayMap();

    public static PhoneAuthProvider.OnVerificationStateChangedCallbacks zza(String str, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, zzady zzadyVar) {
        zza(str, zzadyVar);
        return new zzaeu(onVerificationStateChangedCallbacks, str);
    }

    public static void zza() {
        zza.clear();
    }

    private static void zza(String str, zzady zzadyVar) {
        zza.put(str, new zzaet(zzadyVar, DefaultClock.getInstance().currentTimeMillis()));
    }

    public static boolean zza(String str, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor) {
        if (zza.containsKey(str)) {
            zzaet zzaetVar = zza.get(str);
            if (DefaultClock.getInstance().currentTimeMillis() - zzaetVar.zzb < 120000) {
                if (zzaetVar.zza != null) {
                    zzaetVar.zza.zza(onVerificationStateChangedCallbacks, activity, executor, str);
                    return true;
                }
                return true;
            }
            zza(str, null);
            return false;
        }
        zza(str, null);
        return false;
    }
}
