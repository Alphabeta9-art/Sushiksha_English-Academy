package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.cloudinary.android.BackgroundRequestStrategy;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.zzaq;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzadj extends AsyncTask<Void, Void, zzadi> {
    private static final Logger zza = new Logger("FirebaseAuth", "GetAuthDomainTask");
    private final String zzb;
    private final String zzc;
    private final WeakReference<zzadl> zzd;
    private final Uri.Builder zze;
    private final String zzf;
    private final FirebaseApp zzg;

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final zzadi doInBackground(Void... voidArr) {
        try {
            URL url = new URL(this.zzc);
            zzadl zzadlVar = this.zzd.get();
            HttpURLConnection httpURLConnectionZza = zzadlVar.zza(url);
            httpURLConnectionZza.addRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
            httpURLConnectionZza.setConnectTimeout(BackgroundRequestStrategy.IMMEDIATE_THRESHOLD);
            new zzadu(zzadlVar.zza(), this.zzg, zzads.zza().zzb()).zza(httpURLConnectionZza);
            int responseCode = httpURLConnectionZza.getResponseCode();
            if (responseCode != 200) {
                String strZza = zza(httpURLConnectionZza);
                zza.e(String.format("Error getting project config. Failed with %s %s", strZza, Integer.valueOf(responseCode)), new Object[0]);
                return zzadi.zzb(strZza);
            }
            zzagk zzagkVar = new zzagk();
            zzagkVar.zza(new String(zza(httpURLConnectionZza.getInputStream(), 128)));
            if (!TextUtils.isEmpty(this.zzf)) {
                return !zzagkVar.zza().contains(this.zzf) ? zzadi.zzb("UNAUTHORIZED_DOMAIN") : zzadi.zza(this.zzf);
            }
            for (String str : zzagkVar.zza()) {
                if (zza(str)) {
                    return zzadi.zza(str);
                }
            }
            return null;
        } catch (zzabg e) {
            zza.e("ConversionException encountered: " + e.getMessage(), new Object[0]);
            return null;
        } catch (IOException e2) {
            zza.e("IOException occurred: " + e2.getMessage(), new Object[0]);
            return null;
        } catch (NullPointerException e3) {
            zza.e("Null pointer encountered: " + e3.getMessage(), new Object[0]);
            return null;
        }
    }

    private static String zza(HttpURLConnection httpURLConnection) throws zzabg {
        try {
            if (httpURLConnection.getResponseCode() >= 400) {
                InputStream errorStream = httpURLConnection.getErrorStream();
                if (errorStream == null) {
                    return "WEB_INTERNAL_ERROR:Could not retrieve the authDomain for this project but did not receive an error response from the network request. Please try again.";
                }
                return (String) zzadr.zza(new String(zza(errorStream, 128)), String.class);
            }
            return null;
        } catch (IOException e) {
            zza.w("Error parsing error message from response body in getErrorMessageFromBody. " + String.valueOf(e), new Object[0]);
            return null;
        }
    }

    public zzadj(String str, String str2, Intent intent, FirebaseApp firebaseApp, zzadl zzadlVar) {
        this.zzb = Preconditions.checkNotEmpty(str);
        this.zzg = (FirebaseApp) Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(intent);
        String strCheckNotEmpty = Preconditions.checkNotEmpty(intent.getStringExtra("com.google.firebase.auth.KEY_API_KEY"));
        Uri.Builder builderBuildUpon = Uri.parse(zzadlVar.zza(strCheckNotEmpty)).buildUpon();
        builderBuildUpon.appendPath("getProjectConfig").appendQueryParameter("key", strCheckNotEmpty).appendQueryParameter("androidPackageName", str).appendQueryParameter("sha1Cert", (String) Preconditions.checkNotNull(str2));
        this.zzc = builderBuildUpon.build().toString();
        this.zzd = new WeakReference<>(zzadlVar);
        this.zze = zzadlVar.zza(intent, str, str2);
        this.zzf = intent.getStringExtra("com.google.firebase.auth.KEY_CUSTOM_AUTH_DOMAIN");
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onCancelled(zzadi zzadiVar) {
        onPostExecute((zzadi) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final void onPostExecute(zzadi zzadiVar) {
        String strZza;
        String strZzb;
        zzadl zzadlVar = this.zzd.get();
        if (zzadiVar == null) {
            strZza = null;
            strZzb = null;
        } else {
            strZza = zzadiVar.zza();
            strZzb = zzadiVar.zzb();
        }
        if (zzadlVar == null) {
            zza.e("An error has occurred: the handler reference has returned null.", new Object[0]);
            return;
        }
        if (!TextUtils.isEmpty(strZza) && this.zze != null) {
            this.zze.authority(strZza);
            zzadlVar.zza(this.zze.build(), this.zzb, FirebaseAuth.getInstance(this.zzg).zzc());
        } else {
            zzadlVar.zza(this.zzb, zzaq.zza(strZzb));
        }
    }

    private static boolean zza(String str) {
        try {
            String host = new URI("https://" + str).getHost();
            if (host == null || (!host.endsWith("firebaseapp.com") && !host.endsWith("web.app"))) {
                return false;
            }
            return true;
        } catch (URISyntaxException e) {
            zza.e("Error parsing URL for auth domain check: " + str + ". " + e.getMessage(), new Object[0]);
        }
    }

    private static byte[] zza(InputStream inputStream, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[128];
            while (true) {
                int i2 = inputStream.read(bArr);
                if (i2 != -1) {
                    byteArrayOutputStream.write(bArr, 0, i2);
                } else {
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } finally {
            byteArrayOutputStream.close();
        }
    }
}
