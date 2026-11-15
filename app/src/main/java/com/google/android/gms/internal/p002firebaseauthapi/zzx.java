package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzx {
    private final zzh zza;
    private final zzad zzb;
    private final int zzc;

    public static zzx zza(char c) {
        zzj zzjVar = new zzj(c);
        zzy.zza(zzjVar);
        return new zzx(new zzaa(zzjVar));
    }

    public static zzx zza(String str) {
        zzn zznVarZza = zzt.zza(str);
        if (zznVarZza.zza("").zzc()) {
            throw new IllegalArgumentException(zzag.zza("The pattern may not match the empty string: %s", zznVarZza));
        }
        return new zzx(new zzac(zznVarZza));
    }

    public final List<String> zza(CharSequence charSequence) {
        zzy.zza(charSequence);
        Iterator<String> itZza = this.zzb.zza(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (itZza.hasNext()) {
            arrayList.add(itZza.next());
        }
        return Collections.unmodifiableList(arrayList);
    }

    private zzx(zzad zzadVar) {
        this(zzadVar, false, zzl.zza, Integer.MAX_VALUE);
    }

    private zzx(zzad zzadVar, boolean z, zzh zzhVar, int i) {
        this.zzb = zzadVar;
        this.zza = zzhVar;
        this.zzc = Integer.MAX_VALUE;
    }
}
