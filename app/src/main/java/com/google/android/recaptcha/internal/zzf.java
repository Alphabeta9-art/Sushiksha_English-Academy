package com.google.android.recaptcha.internal;

import kotlinx.coroutines.TimeoutCancellationException;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzf {
    public static final zzbj zza(Exception exc, zzbj zzbjVar) {
        if (exc instanceof TimeoutCancellationException) {
            return new zzbj(zzbh.zzb, zzbg.zzb, null);
        }
        return exc instanceof zzbj ? (zzbj) exc : zzbjVar;
    }
}
