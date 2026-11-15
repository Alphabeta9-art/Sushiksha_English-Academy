package com.facebook.common.internal;

import androidx.collection.SieveCacheKt;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes11.dex */
public class Files {
    private Files() {
    }

    static byte[] readFile(InputStream in, long expectedSize) throws IOException {
        if (expectedSize > SieveCacheKt.NodeLinkMask) {
            throw new OutOfMemoryError("file is too large to fit in a byte array: " + expectedSize + " bytes");
        }
        if (expectedSize == 0) {
            return ByteStreams.toByteArray(in);
        }
        return ByteStreams.toByteArray(in, (int) expectedSize);
    }

    public static byte[] toByteArray(File file) throws IOException {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] file2 = readFile(in, in.getChannel().size());
            in.close();
            return file2;
        } catch (Throwable th) {
            if (in != null) {
                in.close();
            }
            throw th;
        }
    }
}
