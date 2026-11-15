package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public class zzagm implements zzadt<zzagm> {
    private static final String zza = zzagm.class.getSimpleName();
    private String zzb;
    private zzal<zzagt> zzc;

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadt
    /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public final zzagm zza(String str) throws zzabg {
        zzal<zzagt> zzalVarZza;
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = Strings.emptyToNull(jSONObject.optString("recaptchaKey"));
            if (jSONObject.has("recaptchaEnforcementState")) {
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("recaptchaEnforcementState");
                if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
                    zzalVarZza = zzal.zza(new ArrayList());
                } else {
                    zzao zzaoVarZzg = zzal.zzg();
                    for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                        zzaoVarZzg.zza(jSONObject2 == null ? zzagt.zza(null, null) : zzagt.zza(Strings.emptyToNull(jSONObject2.optString("provider")), Strings.emptyToNull(jSONObject2.optString("enforcementState"))));
                    }
                    zzalVarZza = zzaoVarZzg.zza();
                }
                this.zzc = zzalVarZza;
            }
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzaid.zza(e, zza, str);
        }
    }

    public final String zzb(String str) {
        Preconditions.checkNotEmpty(str);
        if (this.zzc == null || this.zzc.isEmpty()) {
            return null;
        }
        zzal<zzagt> zzalVar = this.zzc;
        int size = zzalVar.size();
        int i = 0;
        while (i < size) {
            zzagt zzagtVar = zzalVar.get(i);
            i++;
            zzagt zzagtVar2 = zzagtVar;
            String strZza = zzagtVar2.zza();
            String strZzb = zzagtVar2.zzb();
            if (strZza != null && strZzb != null && strZzb.equals(str)) {
                return zzagtVar2.zza();
            }
        }
        return null;
    }

    public final String zza() {
        return this.zzb;
    }

    public final boolean zzc(String str) {
        String strZzb = zzb(str);
        if (strZzb == null) {
            return false;
        }
        if (!strZzb.equals("ENFORCE") && !strZzb.equals("AUDIT")) {
            return false;
        }
        return true;
    }
}
