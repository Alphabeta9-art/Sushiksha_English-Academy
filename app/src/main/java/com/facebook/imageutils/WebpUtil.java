package com.facebook.imageutils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.UShort;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WebpUtil.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\nH\u0002J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001e\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u001e\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001e\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001c\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\f\u0010\u0019\u001a\u00020\r*\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/facebook/imageutils/WebpUtil;", "", "()V", "VP8L_HEADER", "", "VP8X_HEADER", "VP8_HEADER", "compare", "", "what", "", "with", "get2BytesAsInt", "", "stream", "Ljava/io/InputStream;", "getHeader", "header", "getInt", "getSize", "Lkotlin/Pair;", "getVP8Dimension", "getVP8LDimension", "getVP8XDimension", "read3Bytes", "getNextByteAsInt", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WebpUtil {
    public static final WebpUtil INSTANCE = new WebpUtil();
    private static final String VP8L_HEADER = "VP8L";
    private static final String VP8X_HEADER = "VP8X";
    private static final String VP8_HEADER = "VP8 ";

    private WebpUtil() {
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @JvmStatic
    public static final Pair<Integer, Integer> getSize(InputStream stream) {
        Intrinsics.checkNotNullParameter(stream, "stream");
        byte[] headerBuffer = new byte[4];
        try {
            try {
                try {
                    stream.read(headerBuffer);
                    if (!INSTANCE.compare(headerBuffer, "RIFF")) {
                        try {
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                    INSTANCE.getInt(stream);
                    stream.read(headerBuffer);
                    if (!INSTANCE.compare(headerBuffer, "WEBP")) {
                        try {
                            stream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        return null;
                    }
                    stream.read(headerBuffer);
                    String header = INSTANCE.getHeader(headerBuffer);
                    switch (header.hashCode()) {
                        case 2640674:
                            if (header.equals(VP8_HEADER)) {
                                Pair<Integer, Integer> vP8Dimension = INSTANCE.getVP8Dimension(stream);
                                try {
                                    stream.close();
                                    break;
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                                return vP8Dimension;
                            }
                            break;
                        case 2640718:
                            if (header.equals(VP8L_HEADER)) {
                                Pair<Integer, Integer> vP8LDimension = INSTANCE.getVP8LDimension(stream);
                                try {
                                    stream.close();
                                    break;
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                                return vP8LDimension;
                            }
                            break;
                        case 2640730:
                            if (header.equals(VP8X_HEADER)) {
                                Pair<Integer, Integer> vP8XDimension = INSTANCE.getVP8XDimension(stream);
                                try {
                                    stream.close();
                                    break;
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                                return vP8XDimension;
                            }
                            break;
                    }
                    stream.close();
                    return null;
                } catch (IOException e6) {
                    e6.printStackTrace();
                    stream.close();
                }
            } catch (IOException e7) {
                e7.printStackTrace();
            }
        } catch (Throwable th) {
            try {
                stream.close();
            } catch (IOException e8) {
                e8.printStackTrace();
            }
            throw th;
        }
    }

    private final Pair<Integer, Integer> getVP8Dimension(InputStream stream) throws IOException {
        stream.skip(7L);
        int sign1 = getNextByteAsInt(stream);
        int sign2 = getNextByteAsInt(stream);
        int sign3 = getNextByteAsInt(stream);
        if (sign1 != 157 || sign2 != 1 || sign3 != 42) {
            return null;
        }
        return new Pair<>(Integer.valueOf(get2BytesAsInt(stream)), Integer.valueOf(get2BytesAsInt(stream)));
    }

    private final Pair<Integer, Integer> getVP8LDimension(InputStream stream) throws IOException {
        getInt(stream);
        int check = getNextByteAsInt(stream);
        if (check != 47) {
            return null;
        }
        int data1 = stream.read() & 255;
        int data2 = stream.read() & 255;
        int data3 = stream.read() & 255;
        int data4 = stream.read() & 255;
        int width = (((data2 & 63) << 8) | data1) + 1;
        int height = (((data4 & 15) << 10) | (data3 << 2) | ((data2 & 192) >> 6)) + 1;
        return new Pair<>(Integer.valueOf(width), Integer.valueOf(height));
    }

    private final Pair<Integer, Integer> getVP8XDimension(InputStream stream) throws IOException {
        stream.skip(8L);
        return new Pair<>(Integer.valueOf(read3Bytes(stream) + 1), Integer.valueOf(read3Bytes(stream) + 1));
    }

    private final boolean compare(byte[] what, String with) {
        if (what.length != with.length()) {
            return false;
        }
        Iterable $this$none$iv = ArraysKt.getIndices(what);
        if (($this$none$iv instanceof Collection) && ((Collection) $this$none$iv).isEmpty()) {
            return true;
        }
        Iterator it = $this$none$iv.iterator();
        while (it.hasNext()) {
            int element$iv = ((IntIterator) it).nextInt();
            int i = ((byte) with.charAt(element$iv)) != what[element$iv] ? 1 : 0;
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    private final String getHeader(byte[] header) {
        StringBuilder str = new StringBuilder();
        for (byte b : header) {
            str.append((char) (UShort.m9369constructorimpl(b) & UShort.MAX_VALUE));
        }
        String string = str.toString();
        Intrinsics.checkNotNullExpressionValue(string, "str.toString()");
        return string;
    }

    private final int getInt(InputStream stream) throws IOException {
        int byte1 = getNextByteAsInt(stream);
        int byte2 = getNextByteAsInt(stream);
        int byte3 = getNextByteAsInt(stream);
        int byte4 = getNextByteAsInt(stream);
        return (byte4 << 24) | (byte3 << 16) | (byte2 << 8) | byte1;
    }

    @JvmStatic
    public static final int get2BytesAsInt(InputStream stream) throws IOException {
        Intrinsics.checkNotNullParameter(stream, "stream");
        int byte1 = INSTANCE.getNextByteAsInt(stream);
        int byte2 = INSTANCE.getNextByteAsInt(stream);
        return (byte2 << 8) | byte1;
    }

    private final int read3Bytes(InputStream stream) throws IOException {
        int byte1 = getNextByteAsInt(stream);
        int byte2 = getNextByteAsInt(stream);
        int byte3 = getNextByteAsInt(stream);
        return (byte3 << 16) | (byte2 << 8) | byte1;
    }

    private final int getNextByteAsInt(InputStream $this$getNextByteAsInt) throws IOException {
        return $this$getNextByteAsInt.read() & 255;
    }
}
