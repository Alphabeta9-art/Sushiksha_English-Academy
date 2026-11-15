package com.google.android.recaptcha.internal;

import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.UInt;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzgg implements zzge {
    private final zzgf zza;

    public zzgg(zzgf zzgfVar, zzgd zzgdVar) {
        this.zza = zzgfVar;
    }

    private final zzrg zzb(String str, List list) throws zzby {
        if (str.length() == 0) {
            throw new zzby(3, 17, null);
        }
        try {
            zzgc zzgcVar = new zzgc(this.zza.zza(CollectionsKt.toLongArray(list)), 255L, zzgc.zza);
            StringBuilder sb = new StringBuilder(str.length());
            for (int i = 0; i < str.length(); i++) {
                sb.append((char) UInt.m9183constructorimpl(UInt.m9183constructorimpl(str.charAt(i)) ^ UInt.m9183constructorimpl((int) zzgcVar.zza())));
            }
            return zzrg.zzg(zzhz.zzh().zzj(sb.toString()));
        } catch (Exception e) {
            throw new zzby(3, 18, e);
        }
    }

    @Override // com.google.android.recaptcha.internal.zzge
    public final zzrg zza(zzro zzroVar) throws zzby {
        zzgz zzgzVarZzb = zzgz.zzb();
        zzrg zzrgVarZzb = zzb(zzroVar.zzj(), zzroVar.zzk());
        zzgzVarZzb.zzf();
        long jZza = zzgzVarZzb.zza(TimeUnit.MICROSECONDS);
        int i = zzbq.zza;
        zzbq.zza(zzbr.zzh.zza(), jZza);
        return zzrgVarZzb;
    }
}
