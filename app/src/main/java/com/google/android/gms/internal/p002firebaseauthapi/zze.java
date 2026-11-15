package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
final class zze extends zzb {
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzb
    public final URLConnection zza(URL url, String str) throws IOException {
        return url.openConnection();
    }

    private zze() {
    }
}
