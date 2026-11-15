package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.util.Strings;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public class zzagv implements zzadt<zzagv> {
    private static final String zza = zzagv.class.getSimpleName();
    private String zzb;
    private String zzc;
    private String zzd;
    private zzags zze;

    public final zzags zza() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:37:0x008d  */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadt
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzagv zza(String str) throws zzabg {
        String str2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = Strings.emptyToNull(jSONObject.optString("email"));
            this.zzc = Strings.emptyToNull(jSONObject.optString("newEmail"));
            String str3 = null;
            switch (jSONObject.optInt("reqType")) {
                case 1:
                    str2 = "PASSWORD_RESET";
                    break;
                case 2:
                case 3:
                default:
                    str2 = null;
                    break;
                case 4:
                    str2 = "VERIFY_EMAIL";
                    break;
                case 5:
                    str2 = "RECOVER_EMAIL";
                    break;
                case 6:
                    str2 = "EMAIL_SIGNIN";
                    break;
                case 7:
                    str2 = "VERIFY_AND_CHANGE_EMAIL";
                    break;
                case 8:
                    str2 = "REVERT_SECOND_FACTOR_ADDITION";
                    break;
            }
            this.zzd = str2;
            if (TextUtils.isEmpty(this.zzd)) {
                String strOptString = jSONObject.optString("requestType");
                switch (strOptString) {
                    case "VERIFY_EMAIL":
                    case "PASSWORD_RESET":
                    case "EMAIL_SIGNIN":
                    case "VERIFY_AND_CHANGE_EMAIL":
                    case "RECOVER_EMAIL":
                    case "REVERT_SECOND_FACTOR_ADDITION":
                        str3 = strOptString;
                        break;
                }
                this.zzd = str3;
            }
            if (jSONObject.has("mfaInfo")) {
                this.zze = zzags.zza(jSONObject.optJSONObject("mfaInfo"));
            }
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzaid.zza(e, zza, str);
        }
    }

    public final String zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zzd;
    }

    public final boolean zze() {
        return this.zzb != null;
    }

    public final boolean zzf() {
        return this.zze != null;
    }

    public final boolean zzg() {
        return this.zzc != null;
    }

    public final boolean zzh() {
        return this.zzd != null;
    }
}
