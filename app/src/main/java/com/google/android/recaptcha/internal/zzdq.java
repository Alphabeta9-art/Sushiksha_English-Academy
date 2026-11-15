package com.google.android.recaptcha.internal;

import androidx.browser.trusted.sharing.ShareTarget;
import com.google.android.recaptcha.RecaptchaException;
import com.google.common.net.HttpHeaders;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;
import kotlin.coroutines.Continuation;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzdq {
    private final zzav zza;

    public zzdq(zzav zzavVar, zzdo zzdoVar) {
        this.zza = zzavVar;
    }

    public final Object zza(String str, zzqt zzqtVar, zzdc zzdcVar, Continuation continuation) throws RecaptchaException {
        return CoroutineScopeKt.coroutineScope(new zzdp(this, str, zzqtVar, zzdcVar, null), continuation);
    }

    public final String zzb(zzpq zzpqVar, zzdc zzdcVar) throws Exception {
        String strZza;
        String str;
        zzdf zzdfVarZzf;
        try {
            String strZzJ = zzpqVar.zzJ();
            String strZzK = zzpqVar.zzK();
            if (this.zza.zzd(strZzK)) {
                zzdfVarZzf = zzdcVar.zzf(25);
                try {
                    strZza = this.zza.zza(strZzK);
                    if (strZza != null) {
                        zzdfVarZzf.zza();
                    }
                } catch (Exception e) {
                    zzdfVarZzf.zzb(new zzbj(zzbh.zzk, zzbg.zzR, null));
                }
                zzdfVarZzf.zzb(new zzbj(zzbh.zzk, zzbg.zzS, null));
                strZza = null;
            } else {
                strZza = null;
            }
            if (strZza == null) {
                try {
                    this.zza.zzb();
                    zzdf zzdfVarZzf2 = zzdcVar.zzf(23);
                    try {
                        try {
                            URLConnection uRLConnectionOpenConnection = new URL(strZzJ).openConnection();
                            Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
                            HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
                            httpURLConnection.setRequestMethod(ShareTarget.METHOD_GET);
                            httpURLConnection.setDoInput(true);
                            httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT, "application/x-protobuffer");
                            httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "gzip");
                            httpURLConnection.connect();
                            if (httpURLConnection.getResponseCode() != 200) {
                                throw new zzbj(zzbh.zzc, new zzbg(httpURLConnection.getResponseCode()), null);
                            }
                            try {
                                String text = TextStreamsKt.readText(Intrinsics.areEqual("gzip", httpURLConnection.getContentEncoding()) ? new InputStreamReader(new GZIPInputStream(httpURLConnection.getInputStream())) : new InputStreamReader(httpURLConnection.getInputStream()));
                                zzdfVarZzf2.zza();
                                zzdf zzdfVarZzf3 = zzdcVar.zzf(24);
                                try {
                                    this.zza.zzc(strZzK, text);
                                    zzdfVarZzf3.zza();
                                } catch (Exception e2) {
                                    zzdfVarZzf3.zzb(new zzbj(zzbh.zzk, zzbg.zzT, null));
                                }
                                str = text;
                            } catch (Exception e3) {
                                throw new zzbj(zzbh.zzc, zzbg.zzP, null);
                            }
                        } catch (Exception e4) {
                            throw new zzbj(zzbh.zzc, zzbg.zzO, null);
                        }
                    } catch (Exception e5) {
                        throw new zzbj(zzbh.zzb, zzbg.zzN, null);
                    }
                } catch (zzbj e6) {
                    zzdfVarZzf.zzb(e6);
                    throw e6;
                }
            } else {
                str = strZza;
            }
            return StringsKt.replace$default(zzpqVar.zzk(), "JAVASCRIPT_TAG", str, false, 4, (Object) null);
        } catch (Exception e7) {
            if (e7 instanceof zzbj) {
                throw e7;
            }
            throw new zzbj(zzbh.zzb, zzbg.zzL, null);
        }
    }
}
