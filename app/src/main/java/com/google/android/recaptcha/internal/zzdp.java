package com.google.android.recaptcha.internal;

import com.bumptech.glide.load.Key;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzdp extends SuspendLambda implements Function2 {
    final /* synthetic */ zzdq zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzqt zzc;
    final /* synthetic */ zzdc zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdp(zzdq zzdqVar, String str, zzqt zzqtVar, zzdc zzdcVar, Continuation continuation) {
        super(2, continuation);
        this.zza = zzdqVar;
        this.zzb = str;
        this.zzc = zzqtVar;
        this.zzd = zzdcVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zzdp(this.zza, this.zzb, this.zzc, this.zzd, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzdp) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws zzbj {
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        zzdf zzdfVarZzf = this.zzd.zzf(22);
        zzqt zzqtVar = this.zzc;
        String str2 = this.zzb;
        try {
            int i = zzdt.zza;
            if (!zzdt.zzb(str2)) {
                throw new zzbj(zzbh.zzc, zzbg.zzQ, null);
            }
            try {
                URLConnection uRLConnectionOpenConnection = new URL(str2).openConnection();
                Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
                HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT, "application/x-protobuffer");
                String strEncode = URLEncoder.encode(zzqtVar.zzL(), Key.STRING_CHARSET_NAME);
                String strEncode2 = URLEncoder.encode(zzqtVar.zzk(), Key.STRING_CHARSET_NAME);
                switch (zzqtVar.zzS()) {
                    case 2:
                        str = "UNSPECIFIED";
                        break;
                    case 3:
                        str = "ANDROID_ONPLAY";
                        break;
                    case 4:
                        str = "ANDROID_OFFPLAY";
                        break;
                    case 5:
                        str = "IOS";
                        break;
                    default:
                        str = "UNRECOGNIZED";
                        break;
                }
                byte[] bytes = ("k=" + strEncode + "&pk=" + strEncode2 + "&mst=" + URLEncoder.encode(str, Key.STRING_CHARSET_NAME) + "&msv=" + URLEncoder.encode(zzqtVar.zzJ(), Key.STRING_CHARSET_NAME) + "&msi=" + URLEncoder.encode(zzqtVar.zzK(), Key.STRING_CHARSET_NAME) + "&mov=" + zzqtVar.zzj() + "&mkc=" + zzqtVar.zzi()).getBytes(Charset.forName(Key.STRING_CHARSET_NAME));
                try {
                    try {
                        zzdr.zza(httpURLConnection);
                        try {
                            zzdr.zzd(httpURLConnection).write(bytes);
                            if (httpURLConnection.getResponseCode() == 200) {
                                try {
                                    zzpq zzpqVarZzi = zzpq.zzi(zzdr.zzc(httpURLConnection));
                                    httpURLConnection.disconnect();
                                    zzdfVarZzf.zza();
                                    return zzpqVarZzi;
                                } catch (Exception e) {
                                    throw new zzbj(zzbh.zzb, zzbg.zzG, null);
                                }
                            }
                            try {
                                int responseCode = httpURLConnection.getResponseCode();
                                byte[] bytes2 = ByteStreamsKt.readBytes(httpURLConnection.getErrorStream());
                                if (responseCode != 400) {
                                    throw zzdr.zzb(responseCode);
                                }
                                zzrb zzrbVarZzg = zzrb.zzg(bytes2);
                                int i2 = zzbj.zza;
                                throw zzbi.zza(zzrbVarZzg.zzi());
                            } catch (Exception e2) {
                                throw new zzbj(zzbh.zzc, zzbg.zzai, null);
                            }
                        } catch (IOException e3) {
                            throw new zzbj(zzbh.zzc, zzbg.zzah, e3.getMessage());
                        } catch (Exception e4) {
                            throw new zzbj(zzbh.zzc, zzbg.zzam, e4.getMessage());
                        }
                    } catch (Exception e5) {
                        if (e5 instanceof zzbj) {
                            throw ((zzbj) e5);
                        }
                        throw new zzbj(zzbh.zzc, zzbg.zzF, e5.getMessage());
                    }
                } catch (Throwable th) {
                    httpURLConnection.disconnect();
                    throw th;
                }
            } catch (Exception e6) {
                throw new zzbj(zzbh.zzc, zzbg.zzaj, e6.getMessage());
            }
        } catch (zzbj e7) {
            zzdfVarZzf.zzb(e7);
            throw e7;
        } catch (Exception e8) {
            zzbj zzbjVar = new zzbj(zzbh.zzb, zzbg.zza, e8.getMessage());
            zzdfVarZzf.zzb(zzbjVar);
            throw zzbjVar;
        }
    }
}
