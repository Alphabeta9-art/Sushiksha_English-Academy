package com.google.android.gms.internal.p002firebaseauthapi;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzl extends zzm {
    static final zzh zza = new zzl();

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzh
    public final int zza(CharSequence charSequence, int i) {
        zzy.zza(i, charSequence.length(), "index");
        return -1;
    }

    private zzl() {
        super("CharMatcher.none()");
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzh
    public final boolean zza(char c) {
        return false;
    }
}
