package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzaft implements zzafp {
    private String zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzafp
    public final /* synthetic */ zzafp zza(String str) {
        this.zza = str;
        return this;
    }

    public static zzaft zza(String str, String str2, String str3, String str4, String str5) {
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotEmpty(str2);
        return new zzaft("phone", str, str2, str3, str4, str5);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadq
    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("idToken", this.zza);
        jSONObject.put("mfaProvider", 1);
        if (this.zzb != null) {
            jSONObject.put("displayName", this.zzb);
        }
        JSONObject jSONObject2 = new JSONObject();
        if (this.zzc != null) {
            jSONObject2.put("sessionInfo", this.zzc);
        }
        if (this.zzd != null) {
            jSONObject2.put("code", this.zzd);
        }
        jSONObject.put("phoneVerificationInfo", jSONObject2);
        if (this.zze != null) {
            jSONObject.put("tenantId", this.zze);
        }
        return jSONObject.toString();
    }

    private zzaft(String str, String str2, String str3, String str4, String str5, String str6) {
        Preconditions.checkNotEmpty(str);
        this.zza = Preconditions.checkNotEmpty(str2);
        this.zzc = str3;
        this.zzd = str4;
        this.zzb = str5;
        this.zze = str6;
    }
}
