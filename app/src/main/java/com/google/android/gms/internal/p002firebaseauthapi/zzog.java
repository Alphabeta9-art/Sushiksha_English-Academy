package com.google.android.gms.internal.p002firebaseauthapi;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzog {
    public static final zzzc zza = zzzc.zza(new byte[0]);

    public static final zzzc zza(int i) {
        return zzzc.zza(ByteBuffer.allocate(5).put((byte) 0).putInt(i).array());
    }

    public static final zzzc zzb(int i) {
        return zzzc.zza(ByteBuffer.allocate(5).put((byte) 1).putInt(i).array());
    }
}
