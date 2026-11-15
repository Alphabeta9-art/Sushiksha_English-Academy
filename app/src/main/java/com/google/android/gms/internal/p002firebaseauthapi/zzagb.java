package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.autofill.HintConstants;
import com.cloudinary.provisioning.Account;
import com.google.android.gms.common.util.Strings;
import com.google.firebase.auth.zzan;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public class zzagb implements zzadt<zzagb> {
    private static final String zza = zzagb.class.getSimpleName();
    private zzagd zzb;

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadt
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzagb zza(String str) throws zzabg {
        zzagd zzagdVar;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(Account.USERS)) {
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(Account.USERS);
                if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
                    zzagdVar = new zzagd(new ArrayList());
                } else {
                    ArrayList arrayList = new ArrayList(jSONArrayOptJSONArray.length());
                    boolean z = false;
                    int i = 0;
                    while (i < jSONArrayOptJSONArray.length()) {
                        JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                        arrayList.add(jSONObject2 == null ? new zzage() : new zzage(Strings.emptyToNull(jSONObject2.optString("localId", null)), Strings.emptyToNull(jSONObject2.optString("email", null)), jSONObject2.optBoolean("emailVerified", z), Strings.emptyToNull(jSONObject2.optString("displayName", null)), Strings.emptyToNull(jSONObject2.optString("photoUrl", null)), zzagu.zza(jSONObject2.optJSONArray("providerUserInfo")), Strings.emptyToNull(jSONObject2.optString("rawPassword", null)), Strings.emptyToNull(jSONObject2.optString(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, null)), jSONObject2.optLong("createdAt", 0L), jSONObject2.optLong("lastLoginAt", 0L), false, null, zzags.zza(jSONObject2.optJSONArray("mfaInfo")), zzan.zza(jSONObject2.optJSONArray("passkeyInfo"))));
                        i++;
                        z = false;
                    }
                    zzagdVar = new zzagd(arrayList);
                }
            } else {
                zzagdVar = new zzagd();
            }
            this.zzb = zzagdVar;
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzaid.zza(e, zza, str);
        }
    }

    public final List<zzage> zza() {
        return this.zzb.zza();
    }
}
