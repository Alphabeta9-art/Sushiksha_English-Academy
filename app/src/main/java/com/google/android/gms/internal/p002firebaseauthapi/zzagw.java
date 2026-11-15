package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.autofill.HintConstants;
import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzagw implements zzadq {
    private final String zza;
    private final String zzb;
    private final String zzc;

    public final String zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadq
    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("oobCode", this.zza);
        if (this.zzb != null) {
            jSONObject.put(HintConstants.AUTOFILL_HINT_NEW_PASSWORD, this.zzb);
        }
        if (this.zzc != null) {
            jSONObject.put("tenantId", this.zzc);
        }
        return jSONObject.toString();
    }

    public zzagw(String str, String str2, String str3) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = str2;
        this.zzc = str3;
    }
}
