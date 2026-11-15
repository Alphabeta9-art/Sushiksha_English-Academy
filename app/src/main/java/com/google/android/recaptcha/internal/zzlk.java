package com.google.android.recaptcha.internal;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
final class zzlk {
    zzlk() {
    }

    public static final List zza(Object obj, long j) {
        zzkz zzkzVar = (zzkz) zzni.zzf(obj, j);
        if (zzkzVar.zzc()) {
            return zzkzVar;
        }
        int size = zzkzVar.size();
        zzkz zzkzVarZzd = zzkzVar.zzd(size == 0 ? 10 : size + size);
        zzni.zzs(obj, j, zzkzVarZzd);
        return zzkzVarZzd;
    }
}
