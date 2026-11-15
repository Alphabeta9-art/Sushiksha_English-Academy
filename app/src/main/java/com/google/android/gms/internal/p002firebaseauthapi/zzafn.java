package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.auth.ActionCodeUrl;
import com.google.firebase.auth.EmailAuthCredential;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public class zzafn implements zzadq {
    private static final String zza = zzafn.class.getSimpleName();
    private final String zzb;
    private final String zzc;
    private final EmailAuthCredential zzd;
    private final String zze;
    private final String zzf;

    public final EmailAuthCredential zzb() {
        return this.zzd;
    }

    public final String zzc() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadq
    public final String zza() throws JSONException {
        ActionCodeUrl link = ActionCodeUrl.parseLink(this.zzc);
        String code = link != null ? link.getCode() : null;
        String strZza = link != null ? link.zza() : null;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("email", this.zzb);
        if (code != null) {
            jSONObject.put("oobCode", code);
        }
        if (strZza != null) {
            jSONObject.put("tenantId", strZza);
        }
        if (this.zze != null) {
            jSONObject.put("idToken", this.zze);
        }
        if (this.zzf != null) {
            zzaid.zza(jSONObject, "captchaResp", this.zzf);
        } else {
            zzaid.zza(jSONObject);
        }
        return jSONObject.toString();
    }

    static {
        new Logger(zza, new String[0]);
    }

    public zzafn(EmailAuthCredential emailAuthCredential, String str, String str2) {
        this.zzd = (EmailAuthCredential) Preconditions.checkNotNull(emailAuthCredential);
        this.zzb = Preconditions.checkNotEmpty(emailAuthCredential.zzc());
        this.zzc = Preconditions.checkNotEmpty(emailAuthCredential.zze());
        this.zze = str;
        this.zzf = str2;
    }
}
