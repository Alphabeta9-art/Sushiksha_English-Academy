package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaAction;
import com.google.common.net.HttpHeaders;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzcu extends SuspendLambda implements Function2 {
    final /* synthetic */ zzdc zza;
    final /* synthetic */ zzcx zzb;
    final /* synthetic */ RecaptchaAction zzc;
    final /* synthetic */ zzpw zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcu(zzdc zzdcVar, zzcx zzcxVar, RecaptchaAction recaptchaAction, zzpw zzpwVar, Continuation continuation) {
        super(2, continuation);
        this.zza = zzdcVar;
        this.zzb = zzcxVar;
        this.zzc = recaptchaAction;
        this.zzd = zzpwVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zzcu(this.zza, this.zzb, this.zzc, this.zzd, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzcu) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws zzbj {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        zzdf zzdfVarZzf = this.zza.zzf(28);
        zzqc zzqcVarZzf = zzqd.zzf();
        zzcx zzcxVar = this.zzb;
        zzqcVarZzf.zzu(zzcxVar.zze());
        zzqcVarZzf.zzd(this.zzc.getAction());
        zzqcVarZzf.zzp(zzcxVar.zzf.zzL());
        zzqcVarZzf.zzq(zzcxVar.zzf.zzK());
        zzpw zzpwVar = this.zzd;
        zzqcVarZzf.zzs(zzpwVar.zzJ());
        zzqcVarZzf.zze(zzpwVar.zzj());
        zzqcVarZzf.zzr(zzpwVar.zzK());
        zzqcVarZzf.zzt(zzpwVar);
        zzqd zzqdVar = (zzqd) zzqcVarZzf.zzj();
        try {
            zzbv zzbvVar = this.zzb.zze;
            try {
                int i = zzdt.zza;
                if (!zzdt.zzb(zzbvVar.zzd())) {
                    throw new zzbj(zzbh.zzc, zzbg.zzQ, null);
                }
                URLConnection uRLConnectionOpenConnection = new URL(zzbvVar.zzd()).openConnection();
                Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
                HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/x-protobuffer");
                httpURLConnection.connect();
                httpURLConnection.getOutputStream().write(zzqdVar.zzd());
                if (httpURLConnection.getResponseCode() != 200) {
                    throw zzdr.zzb(httpURLConnection.getResponseCode());
                }
                try {
                    zzqf zzqfVarZzi = zzqf.zzi(httpURLConnection.getInputStream());
                    zzdfVarZzf.zza();
                    return zzqfVarZzi;
                } catch (Exception e) {
                    throw new zzbj(zzbh.zzb, zzbg.zzG, null);
                }
            } catch (Exception e2) {
                if (e2 instanceof zzbj) {
                    throw ((zzbj) e2);
                }
                throw new zzbj(zzbh.zzc, zzbg.zzF, null);
            }
        } catch (Exception e3) {
            zzbj zzbjVar = e3 instanceof zzbj ? (zzbj) e3 : new zzbj(zzbh.zzb, zzbg.zzZ, null);
            zzdfVarZzf.zzb(zzbjVar);
            throw zzbjVar;
        }
    }
}
