package com.google.firebase.auth.internal;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.google.android.gms.internal.p002firebaseauthapi.zzkh;
import com.google.android.gms.internal.p002firebaseauthapi.zzkm;
import com.google.android.gms.internal.p002firebaseauthapi.zzma;
import com.google.android.gms.internal.p002firebaseauthapi.zzpg;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzs {
    private static zzs zza;
    private final String zzb;
    private final zzma zzc;

    public static zzs zza(Context context, String str) {
        if (zza == null || !com.google.android.gms.internal.p002firebaseauthapi.zzr.zza(zza.zzb, str)) {
            zza = new zzs(context, str, true);
        }
        return zza;
    }

    public final String zza(String str) {
        String str2;
        if (this.zzc == null) {
            Log.e("FirebearCryptoHelper", "KeysetManager failed to initialize - unable to decrypt payload");
            return null;
        }
        try {
            synchronized (this.zzc) {
                str2 = new String(((com.google.android.gms.internal.p002firebaseauthapi.zzbo) this.zzc.zza().zza(zzpg.zza(), com.google.android.gms.internal.p002firebaseauthapi.zzbo.class)).zza(Base64.decode(str, 8), null), Key.STRING_CHARSET_NAME);
            }
            return str2;
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            Log.e("FirebearCryptoHelper", "Exception encountered while decrypting bytes:\n" + e.getMessage());
            return null;
        }
    }

    public final String zza() {
        if (this.zzc == null) {
            Log.e("FirebearCryptoHelper", "KeysetManager failed to initialize - unable to get Public key");
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        com.google.android.gms.internal.p002firebaseauthapi.zzce zzceVarZza = com.google.android.gms.internal.p002firebaseauthapi.zzbi.zza(byteArrayOutputStream);
        try {
            synchronized (this.zzc) {
                this.zzc.zza().zza().zza(zzceVarZza);
            }
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 8);
        } catch (IOException | GeneralSecurityException e) {
            Log.e("FirebearCryptoHelper", "Exception encountered when attempting to get Public Key:\n" + e.getMessage());
            return null;
        }
    }

    private zzs(Context context, String str, boolean z) {
        zzma zzmaVarZza;
        this.zzb = str;
        try {
            zzkh.zza();
            zzma.zza zzaVarZza = new zzma.zza().zza(context, "GenericIdpKeyset", String.format("com.google.firebase.auth.api.crypto.%s", str)).zza(zzkm.zza);
            zzaVarZza.zza(String.format("android-keystore://firebear_master_key_id.%s", str));
            zzmaVarZza = zzaVarZza.zza();
        } catch (IOException | GeneralSecurityException e) {
            Log.e("FirebearCryptoHelper", "Exception encountered during crypto setup:\n" + e.getMessage());
            zzmaVarZza = null;
        }
        this.zzc = zzmaVarZza;
    }
}
