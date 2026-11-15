package com.google.android.recaptcha.internal;

import android.net.Uri;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.bumptech.glide.load.Key;
import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzgp extends WebViewClient {
    final /* synthetic */ zzgv zza;

    zzgp(zzgv zzgvVar) {
        this.zza = zzgvVar;
    }

    @Override // android.webkit.WebViewClient
    public final void onLoadResource(WebView webView, String str) {
        System.currentTimeMillis();
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        long jZza = this.zza.zzj.zza(TimeUnit.MICROSECONDS);
        int i = zzbq.zza;
        zzbq.zza(zzbr.zzg.zza(), jZza);
    }

    @Override // android.webkit.WebViewClient
    @Deprecated(message = "Use onReceivedError(WebView,request,error) instead")
    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        zzbh zzbhVar = zzbh.zzc;
        zzbg zzbgVar = (zzbg) this.zza.zzh.get(Integer.valueOf(i));
        if (zzbgVar == null) {
            zzbgVar = zzbg.zzM;
        }
        zzbj zzbjVar = new zzbj(zzbhVar, zzbgVar, null);
        this.zza.zzu().hashCode();
        zzbjVar.getMessage();
        this.zza.zzu().completeExceptionally(zzbjVar);
    }

    @Override // android.webkit.WebViewClient
    @Deprecated(message = "Use shouldInterceptRequest(WebView,WebResourceRequest) instead")
    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        Uri uri = Uri.parse(str);
        int i = zzdt.zza;
        Intrinsics.checkNotNull(uri);
        if (!zzdt.zzc(uri) || zzdt.zza(uri)) {
            return super.shouldInterceptRequest(webView, str);
        }
        zzbj zzbjVar = new zzbj(zzbh.zzb, zzbg.zzQ, null);
        this.zza.zzu().hashCode();
        uri.toString();
        this.zza.zzu().completeExceptionally(zzbjVar);
        return new WebResourceResponse("text/plain", Key.STRING_CHARSET_NAME, new ByteArrayInputStream(new byte[0]));
    }
}
