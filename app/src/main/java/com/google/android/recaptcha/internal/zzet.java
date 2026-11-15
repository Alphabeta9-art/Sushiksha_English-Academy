package com.google.android.recaptcha.internal;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzet implements zzfe {
    public static final zzet zza = new zzet();

    private zzet() {
    }

    @Override // com.google.android.recaptcha.internal.zzfe
    public final void zza(int i, zzek zzekVar, zzrr... zzrrVarArr) throws zzby {
        boolean z = true;
        if (zzrrVarArr.length != 1) {
            throw new zzby(4, 3, null);
        }
        Object objZza = zzekVar.zzc().zza(zzrrVarArr[0]);
        if (true != (objZza instanceof Object)) {
            objZza = null;
        }
        if (objZza == null) {
            throw new zzby(4, 5, null);
        }
        try {
            try {
                if (objZza instanceof String) {
                    objZza = zzekVar.zzh().zza((String) objZza);
                }
                zzel zzelVarZzc = zzekVar.zzc();
                try {
                    zzej.zza(objZza);
                } catch (zzby e) {
                    if (e.zzb() == 8 || e.zzb() == 6) {
                        z = false;
                    } else if (e.zzb() != 47) {
                        throw e;
                    }
                }
                zzelVarZzc.zze(i, Boolean.valueOf(z));
            } catch (Exception e2) {
                throw new zzby(6, 8, e2);
            }
        } catch (zzby e3) {
            throw e3;
        }
    }
}
