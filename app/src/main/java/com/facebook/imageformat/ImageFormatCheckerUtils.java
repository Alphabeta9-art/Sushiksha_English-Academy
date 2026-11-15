package com.facebook.imageformat;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageFormatCheckerUtils.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0007J(\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\fH\u0007J\u0018\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0007¨\u0006\u0011"}, d2 = {"Lcom/facebook/imageformat/ImageFormatCheckerUtils;", "", "()V", "asciiBytes", "", "value", "", "hasPatternAt", "", "byteArray", "pattern", TypedValues.CycleType.S_WAVE_OFFSET, "", "indexOfPattern", "byteArrayLen", "patternLen", "startsWithPattern", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ImageFormatCheckerUtils {
    public static final ImageFormatCheckerUtils INSTANCE = new ImageFormatCheckerUtils();

    private ImageFormatCheckerUtils() {
    }

    @JvmStatic
    public static final byte[] asciiBytes(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            Charset charsetForName = Charset.forName("ASCII");
            Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(charsetName)");
            byte[] bytes = value.getBytes(charsetForName);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            return bytes;
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("ASCII not found!", uee);
        }
    }

    @JvmStatic
    public static final boolean startsWithPattern(byte[] byteArray, byte[] pattern) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        ImageFormatCheckerUtils imageFormatCheckerUtils = INSTANCE;
        return hasPatternAt(byteArray, pattern, 0);
    }

    @JvmStatic
    public static final boolean hasPatternAt(byte[] byteArray, byte[] pattern, int offset) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        if (pattern.length + offset > byteArray.length) {
            return false;
        }
        Iterable $this$none$iv = ArraysKt.getIndices(pattern);
        if (($this$none$iv instanceof Collection) && ((Collection) $this$none$iv).isEmpty()) {
            return true;
        }
        Iterator it = $this$none$iv.iterator();
        while (it.hasNext()) {
            int element$iv = ((IntIterator) it).nextInt();
            int i = byteArray[offset + element$iv] != pattern[element$iv] ? 1 : 0;
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    @JvmStatic
    public static final int indexOfPattern(byte[] byteArray, int byteArrayLen, byte[] pattern, int patternLen) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        if (patternLen > byteArrayLen) {
            return -1;
        }
        byte first = pattern[0];
        int max = byteArrayLen - patternLen;
        int i = 0;
        while (i <= max) {
            if (byteArray[i] != first) {
                do {
                    i++;
                    if (i > max) {
                        break;
                    }
                } while (byteArray[i] != first);
            }
            if (i <= max) {
                int j = i + 1;
                int end = (j + patternLen) - 1;
                for (int k = 1; j < end && byteArray[j] == pattern[k]; k++) {
                    j++;
                }
                if (j == end) {
                    return i;
                }
            }
            i++;
        }
        return -1;
    }
}
