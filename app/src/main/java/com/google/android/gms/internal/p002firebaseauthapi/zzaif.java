package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaie;
import com.google.android.gms.internal.p002firebaseauthapi.zzaif;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public abstract class zzaif<MessageType extends zzaif<MessageType, BuilderType>, BuilderType extends zzaie<MessageType, BuilderType>> implements zzalc {
    protected int zza = 0;

    int zzi() {
        throw new UnsupportedOperationException();
    }

    int zza(zzalv zzalvVar) {
        int iZzi = zzi();
        if (iZzi != -1) {
            return iZzi;
        }
        int iZza = zzalvVar.zza(this);
        zzb(iZza);
        return iZza;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzalc
    public final zzaip a_() {
        try {
            zzaiu zzaiuVarZzc = zzaip.zzc(zzl());
            zza(zzaiuVarZzc.zzb());
            return zzaiuVarZzc.zza();
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    void zzb(int i) {
        throw new UnsupportedOperationException();
    }

    public final void zza(OutputStream outputStream) throws IOException {
        zzajg zzajgVarZza = zzajg.zza(outputStream, zzajg.zzf(zzl()));
        zza(zzajgVarZza);
        zzajgVarZza.zzc();
    }

    public final byte[] zzk() {
        try {
            byte[] bArr = new byte[zzl()];
            zzajg zzajgVarZzb = zzajg.zzb(bArr);
            zza(zzajgVarZzb);
            zzajgVarZzb.zzb();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
