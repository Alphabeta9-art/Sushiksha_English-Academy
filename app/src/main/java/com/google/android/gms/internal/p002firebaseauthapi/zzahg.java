package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzahg implements zzadq {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadq
    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.zza != null) {
            jSONObject.put("email", this.zza);
        }
        if (this.zzb != null) {
            jSONObject.put("password", this.zzb);
        }
        if (this.zzc != null) {
            jSONObject.put("tenantId", this.zzc);
        }
        if (this.zzd != null) {
            zzaid.zza(jSONObject, "captchaResponse", this.zzd);
        } else {
            zzaid.zza(jSONObject);
        }
        if (this.zze != null) {
            jSONObject.put("idToken", this.zze);
        }
        return jSONObject.toString();
    }

    public zzahg(String str) {
        this.zzc = str;
    }

    public zzahg(String str, String str2, String str3, String str4, String str5, String str6) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = Preconditions.checkNotEmpty(str2);
        this.zzc = str4;
        this.zzd = str5;
        this.zze = str6;
    }
}
