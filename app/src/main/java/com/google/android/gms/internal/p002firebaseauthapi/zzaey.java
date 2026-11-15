package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.tasks.OnFailureListener;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zzaey implements OnFailureListener {
    zzaey(zzaew zzaewVar) {
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        zzaew.zza.e("SmsRetrieverClient failed to start: " + exc.getMessage(), new Object[0]);
    }
}
