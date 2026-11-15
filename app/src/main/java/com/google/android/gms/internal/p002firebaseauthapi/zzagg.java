package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.ActionCodeSettings;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzagg implements zzadq {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private ActionCodeSettings zze;
    private String zzf;
    private String zzg;

    public final ActionCodeSettings zzb() {
        return this.zze;
    }

    public static zzagg zza(ActionCodeSettings actionCodeSettings, String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(actionCodeSettings);
        return new zzagg(7, actionCodeSettings, null, str2, str, null, null);
    }

    public final zzagg zza(ActionCodeSettings actionCodeSettings) {
        this.zze = (ActionCodeSettings) Preconditions.checkNotNull(actionCodeSettings);
        return this;
    }

    public final zzagg zza(String str) {
        this.zzg = str;
        return this;
    }

    public final zzagg zzb(String str) {
        this.zzb = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzagg zzc(String str) {
        this.zzf = str;
        return this;
    }

    public final zzagg zzd(String str) {
        this.zzd = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final String zzc() {
        return this.zzb;
    }

    public final String zzd() {
        return this.zzd;
    }

    private static String zza(int i) {
        switch (i) {
            case 1:
                return "PASSWORD_RESET";
            case 2:
            case 3:
            case 5:
            default:
                return "REQUEST_TYPE_UNSET_ENUM_VALUE";
            case 4:
                return "VERIFY_EMAIL";
            case 6:
                return "EMAIL_SIGNIN";
            case 7:
                return "VERIFY_AND_CHANGE_EMAIL";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:17:0x003a  */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadq
    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        int i = 0;
        switch (this.zza) {
            case "PASSWORD_RESET":
                i = 1;
                break;
            case "VERIFY_EMAIL":
                i = 4;
                break;
            case "EMAIL_SIGNIN":
                i = 6;
                break;
            case "VERIFY_AND_CHANGE_EMAIL":
                i = 7;
                break;
        }
        jSONObject.put("requestType", i);
        if (this.zzb != null) {
            jSONObject.put("email", this.zzb);
        }
        if (this.zzc != null) {
            jSONObject.put("newEmail", this.zzc);
        }
        if (this.zzd != null) {
            jSONObject.put("idToken", this.zzd);
        }
        if (this.zze != null) {
            jSONObject.put("androidInstallApp", this.zze.getAndroidInstallApp());
            jSONObject.put("canHandleCodeInApp", this.zze.canHandleCodeInApp());
            if (this.zze.getUrl() != null) {
                jSONObject.put("continueUrl", this.zze.getUrl());
            }
            if (this.zze.getIOSBundle() != null) {
                jSONObject.put("iosBundleId", this.zze.getIOSBundle());
            }
            if (this.zze.zzd() != null) {
                jSONObject.put("iosAppStoreId", this.zze.zzd());
            }
            if (this.zze.getAndroidPackageName() != null) {
                jSONObject.put("androidPackageName", this.zze.getAndroidPackageName());
            }
            if (this.zze.getAndroidMinimumVersion() != null) {
                jSONObject.put("androidMinimumVersion", this.zze.getAndroidMinimumVersion());
            }
            if (this.zze.zzc() != null) {
                jSONObject.put("dynamicLinkDomain", this.zze.zzc());
            }
            if (this.zze.zze() != null) {
                jSONObject.put("linkDomain", this.zze.zze());
            }
        }
        if (this.zzf != null) {
            jSONObject.put("tenantId", this.zzf);
        }
        if (this.zzg != null) {
            zzaid.zza(jSONObject, "captchaResp", this.zzg);
        } else {
            zzaid.zza(jSONObject);
        }
        return jSONObject.toString();
    }

    public zzagg(int i) {
        this.zza = zza(i);
    }

    private zzagg(int i, ActionCodeSettings actionCodeSettings, String str, String str2, String str3, String str4, String str5) {
        this.zza = zza(7);
        this.zze = (ActionCodeSettings) Preconditions.checkNotNull(actionCodeSettings);
        this.zzb = null;
        this.zzc = str2;
        this.zzd = str3;
        this.zzf = null;
        this.zzg = null;
    }
}
