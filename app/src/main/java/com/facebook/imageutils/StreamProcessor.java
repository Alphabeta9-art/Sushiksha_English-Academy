package com.facebook.imageutils;

import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StreamProcessor.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lcom/facebook/imageutils/StreamProcessor;", "", "()V", "readPackedInt", "", "stream", "Ljava/io/InputStream;", "numBytes", "isLittleEndian", "", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class StreamProcessor {
    public static final StreamProcessor INSTANCE = new StreamProcessor();

    private StreamProcessor() {
    }

    @JvmStatic
    public static final int readPackedInt(InputStream stream, int numBytes, boolean isLittleEndian) throws IOException {
        int i;
        Intrinsics.checkNotNullParameter(stream, "stream");
        int value = 0;
        for (int i2 = 0; i2 < numBytes; i2++) {
            int b = stream.read();
            if (b == -1) {
                throw new IOException("no more bytes");
            }
            if (isLittleEndian) {
                i = ((b & 255) << (i2 * 8)) | value;
            } else {
                i = (value << 8) | (b & 255);
            }
            value = i;
        }
        return value;
    }
}
