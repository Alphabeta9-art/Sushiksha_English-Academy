package com.google.android.gms.internal.p002firebaseauthapi;

import com.bumptech.glide.load.Key;
import com.cloudinary.android.BackgroundRequestStrategy;
import com.google.android.gms.common.internal.Preconditions;
import com.google.common.net.HttpHeaders;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import org.json.JSONException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzaeo {
    private static void zza(HttpURLConnection httpURLConnection, zzael<?> zzaelVar, Type type) {
        try {
            try {
                int responseCode = httpURLConnection.getResponseCode();
                InputStream inputStream = zza(responseCode) ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream();
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Key.STRING_CHARSET_NAME));
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        } else {
                            sb.append(line);
                        }
                    } catch (Throwable th) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
                bufferedReader.close();
                String string = sb.toString();
                if (zza(responseCode)) {
                    zzaelVar.zza((zzadt) zzadr.zza(string, type));
                } else {
                    zzaelVar.zza((String) zzadr.zza(string, String.class));
                }
                httpURLConnection.disconnect();
            } catch (zzabg e) {
                e = e;
                zzaelVar.zza(e.getMessage());
                httpURLConnection.disconnect();
            } catch (SocketTimeoutException e2) {
                zzaelVar.zza("TIMEOUT");
                httpURLConnection.disconnect();
            } catch (IOException e3) {
                e = e3;
                zzaelVar.zza(e.getMessage());
                httpURLConnection.disconnect();
            }
        } catch (Throwable th3) {
            httpURLConnection.disconnect();
            throw th3;
        }
    }

    public static void zza(String str, zzael<?> zzaelVar, Type type, zzadu zzaduVar) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(BackgroundRequestStrategy.IMMEDIATE_THRESHOLD);
            zzaduVar.zza(httpURLConnection);
            zza(httpURLConnection, zzaelVar, type);
        } catch (SocketTimeoutException e) {
            zzaelVar.zza("TIMEOUT");
        } catch (UnknownHostException e2) {
            zzaelVar.zza("<<Network Error>>");
        } catch (IOException e3) {
            zzaelVar.zza(e3.getMessage());
        }
    }

    public static void zza(String str, zzadq zzadqVar, zzael<?> zzaelVar, Type type, zzadu zzaduVar) {
        try {
            Preconditions.checkNotNull(zzadqVar);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            byte[] bytes = zzadqVar.zza().getBytes(Charset.defaultCharset());
            httpURLConnection.setFixedLengthStreamingMode(bytes.length);
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");
            httpURLConnection.setConnectTimeout(BackgroundRequestStrategy.IMMEDIATE_THRESHOLD);
            zzaduVar.zza(httpURLConnection);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream(), bytes.length);
            try {
                bufferedOutputStream.write(bytes, 0, bytes.length);
                bufferedOutputStream.close();
                zza(httpURLConnection, zzaelVar, type);
            } catch (Throwable th) {
                try {
                    bufferedOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (NullPointerException e) {
            e = e;
            zzaelVar.zza(e.getMessage());
        } catch (SocketTimeoutException e2) {
            zzaelVar.zza("TIMEOUT");
        } catch (UnknownHostException e3) {
            zzaelVar.zza("<<Network Error>>");
        } catch (IOException e4) {
            e = e4;
            zzaelVar.zza(e.getMessage());
        } catch (JSONException e5) {
            e = e5;
            zzaelVar.zza(e.getMessage());
        }
    }

    private static final boolean zza(int i) {
        return i >= 200 && i < 300;
    }
}
