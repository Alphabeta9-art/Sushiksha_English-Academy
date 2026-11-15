package com.google.android.gms.internal.p002firebaseauthapi;

import com.bumptech.glide.load.Key;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import java.io.UnsupportedEncodingException;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzagn {
    public static long zza(String str) {
        zzagq zzagqVarZzb = zzb(str);
        return zzagqVarZzb.zza().longValue() - zzagqVarZzb.zzb().longValue();
    }

    private static zzagq zzb(String str) {
        Preconditions.checkNotEmpty(str);
        List<String> listZza = zzx.zza('.').zza((CharSequence) str);
        if (listZza.size() < 2) {
            throw new RuntimeException("Invalid idToken " + str);
        }
        try {
            return zzagq.zza(new String(Base64Utils.decodeUrlSafeNoPadding(listZza.get(1)), Key.STRING_CHARSET_NAME));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unable to decode token", e);
        }
    }
}
