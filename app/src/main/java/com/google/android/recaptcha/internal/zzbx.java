package com.google.android.recaptcha.internal;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import kotlin.io.FilesKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzbx {
    public static final byte[] zza(File file) throws GeneralSecurityException, IOException {
        return FilesKt.readBytes(file);
    }

    public static final void zzb(File file, byte[] bArr) throws GeneralSecurityException, IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException("Unable to delete existing encrypted file");
        }
        FilesKt.writeBytes(file, bArr);
    }
}
