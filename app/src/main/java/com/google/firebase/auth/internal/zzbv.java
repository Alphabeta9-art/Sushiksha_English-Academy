package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzagm;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.android.recaptcha.RecaptchaTasksClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzbv {
    FirebaseApp zza;
    zzbu zzb;
    private final Object zzc;
    private final Map<String, Task<RecaptchaTasksClient>> zzd;
    private zzagm zze;
    private FirebaseAuth zzf;

    private final Task<RecaptchaTasksClient> zzc(String str) {
        Task<RecaptchaTasksClient> task;
        synchronized (this.zzc) {
            task = this.zzd.get(str);
        }
        return task;
    }

    public final Task<String> zza(String str, Boolean bool, RecaptchaAction recaptchaAction) {
        String strZzd = zzd(str);
        Task<RecaptchaTasksClient> taskZzc = zzc(strZzd);
        if (bool.booleanValue() || taskZzc == null) {
            taskZzc = zza(strZzd, bool);
        }
        return taskZzc.continueWithTask(new zzbx(this, recaptchaAction));
    }

    public final Task<RecaptchaTasksClient> zza(String str, Boolean bool) {
        Task<RecaptchaTasksClient> taskZzc;
        String strZzd = zzd(str);
        if (!bool.booleanValue() && (taskZzc = zzc(strZzd)) != null) {
            return taskZzc;
        }
        return this.zzf.zza("RECAPTCHA_ENTERPRISE").continueWithTask(new zzby(this, strZzd));
    }

    private static String zzd(String str) {
        if (com.google.android.gms.internal.p002firebaseauthapi.zzag.zzc(str)) {
            return "*";
        }
        return str;
    }

    static /* synthetic */ void zza(zzbv zzbvVar, zzagm zzagmVar, Task task, String str) {
        synchronized (zzbvVar.zzc) {
            zzbvVar.zze = zzagmVar;
            zzbvVar.zzd.put(str, task);
        }
    }

    public zzbv(FirebaseApp firebaseApp, FirebaseAuth firebaseAuth) {
        this(firebaseApp, firebaseAuth, new zzbt());
    }

    private zzbv(FirebaseApp firebaseApp, FirebaseAuth firebaseAuth, zzbu zzbuVar) {
        this.zzc = new Object();
        this.zzd = new HashMap();
        this.zza = firebaseApp;
        this.zzf = firebaseAuth;
        this.zzb = zzbuVar;
    }

    public final boolean zza(String str) {
        String strZzb;
        Preconditions.checkNotNull(str);
        if (this.zze == null || (strZzb = this.zze.zzb(str)) == null) {
            return false;
        }
        return strZzb.equals("AUDIT");
    }

    public final boolean zzb(String str) {
        boolean z;
        synchronized (this.zzc) {
            z = this.zze != null && this.zze.zzc(str);
        }
        return z;
    }
}
