package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.autofill.HintConstants;
import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzagz implements zzadq {
    private final String zza;
    private final long zzb;
    private final boolean zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;
    private final boolean zzi;
    private zzafg zzj;

    public final long zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzd;
    }

    public final String zzd() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadq
    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, this.zza);
        if (this.zze != null) {
            jSONObject.put("tenantId", this.zze);
        }
        if (this.zzf != null) {
            jSONObject.put("recaptchaToken", this.zzf);
        }
        if (this.zzj != null) {
            jSONObject.put("autoRetrievalInfo", this.zzj.zza());
        }
        if (this.zzg != null) {
            jSONObject.put("playIntegrityToken", this.zzg);
        }
        if (this.zzh != null) {
            zzaid.zza(jSONObject, "captchaResponse", this.zzh);
        } else {
            zzaid.zza(jSONObject);
        }
        return jSONObject.toString();
    }

    public zzagz(String str, long j, boolean z, String str2, String str3, String str4, String str5, String str6, boolean z2) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = j;
        this.zzc = z;
        this.zzd = str2;
        this.zze = str3;
        this.zzf = str4;
        this.zzg = str5;
        this.zzh = str6;
        this.zzi = z2;
    }

    public final void zza(zzafg zzafgVar) {
        this.zzj = zzafgVar;
    }

    public final boolean zze() {
        return this.zzc;
    }

    public final boolean zzf() {
        return this.zzi;
    }
}
