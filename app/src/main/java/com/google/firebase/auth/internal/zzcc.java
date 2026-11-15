package com.google.firebase.auth.internal;

import com.google.android.gms.common.api.internal.BackgroundDetector;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes12.dex */
final class zzcc implements BackgroundDetector.BackgroundStateChangeListener {
    private final /* synthetic */ zzbz zza;

    zzcc(zzbz zzbzVar) {
        this.zza = zzbzVar;
    }

    @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
    public final void onBackgroundStateChanged(boolean z) {
        if (z) {
            this.zza.zzc = true;
            this.zza.zza();
        } else {
            this.zza.zzc = false;
            if (this.zza.zzb()) {
                this.zza.zzb.zzc();
            }
        }
    }
}
