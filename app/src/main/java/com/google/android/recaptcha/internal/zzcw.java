package com.google.android.recaptcha.internal;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzcw extends Lambda implements Function0 {
    public static final zzcw zza = new zzcw();

    public zzcw() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        int i = zzbb.zza;
        Object objZzb = zzba.zza().zzb(zzbk.class.getName().hashCode());
        if (objZzb != null) {
            return (zzbk) objZzb;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.google.android.libraries.abuse.recaptcha.common.NetworkStatus");
    }
}
