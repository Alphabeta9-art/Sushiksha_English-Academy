package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.TotpMultiFactorGenerator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzafy implements zzafr {
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;

    public static zzafy zza(String str, String str2, String str3, String str4) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str4);
        return new zzafy(TotpMultiFactorGenerator.FACTOR_ID, str, str2, str3, str4);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadq
    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (this.zzb != null) {
            jSONObject2.put("verificationCode", this.zzb);
        }
        jSONObject.put("totpVerificationInfo", jSONObject2);
        jSONObject.put("mfaPendingCredential", this.zza);
        if (this.zzc != null) {
            jSONObject.put("tenantId", this.zzc);
        }
        if (this.zzd != null) {
            jSONObject.put("mfaEnrollmentId", this.zzd);
        }
        return jSONObject.toString();
    }

    private zzafy(String str, String str2, String str3, String str4, String str5) {
        Preconditions.checkNotEmpty(str);
        this.zza = Preconditions.checkNotEmpty(str2);
        this.zzb = str3;
        this.zzc = str4;
        this.zzd = str5;
    }
}
