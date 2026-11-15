package com.facebook.common.webp;

import android.os.Build;
import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class WebpSupportStatus {
    private static final int EXTENDED_WEBP_HEADER_LENGTH = 21;
    private static final int SIMPLE_WEBP_HEADER_LENGTH = 20;
    private static final String VP8X_WEBP_BASE64 = "UklGRkoAAABXRUJQVlA4WAoAAAAQAAAAAAAAAAAAQUxQSAwAAAARBxAR/Q9ERP8DAABWUDggGAAAABQBAJ0BKgEAAQAAAP4AAA3AAP7mtQAAAA==";
    private static final byte[] WEBP_NAME_BYTES;
    private static final byte[] WEBP_RIFF_BYTES;
    private static final byte[] WEBP_VP8L_BYTES;
    private static final byte[] WEBP_VP8X_BYTES;
    private static final byte[] WEBP_VP8_BYTES;
    public static final boolean sIsExtendedWebpSupported;
    public static final boolean sIsSimpleWebpSupported;

    @Nullable
    public static WebpBitmapFactory sWebpBitmapFactory;
    private static boolean sWebpLibraryChecked;

    static {
        int i = Build.VERSION.SDK_INT;
        sIsSimpleWebpSupported = true;
        sIsExtendedWebpSupported = isExtendedWebpSupported();
        sWebpBitmapFactory = null;
        sWebpLibraryChecked = false;
        WEBP_RIFF_BYTES = asciiBytes("RIFF");
        WEBP_NAME_BYTES = asciiBytes("WEBP");
        WEBP_VP8_BYTES = asciiBytes("VP8 ");
        WEBP_VP8L_BYTES = asciiBytes("VP8L");
        WEBP_VP8X_BYTES = asciiBytes("VP8X");
    }

    @Nullable
    public static WebpBitmapFactory loadWebpBitmapFactoryIfExists() {
        if (sWebpLibraryChecked) {
            return sWebpBitmapFactory;
        }
        WebpBitmapFactory loadedWebpBitmapFactory = null;
        try {
            loadedWebpBitmapFactory = (WebpBitmapFactory) Class.forName("com.facebook.webpsupport.WebpBitmapFactoryImpl").newInstance();
        } catch (Throwable th) {
        }
        sWebpLibraryChecked = true;
        return loadedWebpBitmapFactory;
    }

    private static byte[] asciiBytes(String value) {
        try {
            return value.getBytes("ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("ASCII not found!", uee);
        }
    }

    private static boolean isExtendedWebpSupported() {
        int i = Build.VERSION.SDK_INT;
        int i2 = Build.VERSION.SDK_INT;
        return true;
    }

    public static boolean isWebpSupportedByPlatform(byte[] imageHeaderBytes, int offset, int headerSize) {
        if (isSimpleWebpHeader(imageHeaderBytes, offset)) {
            return sIsSimpleWebpSupported;
        }
        if (isLosslessWebpHeader(imageHeaderBytes, offset)) {
            return sIsExtendedWebpSupported;
        }
        if (!isExtendedWebpHeader(imageHeaderBytes, offset, headerSize) || isAnimatedWebpHeader(imageHeaderBytes, offset)) {
            return false;
        }
        return sIsExtendedWebpSupported;
    }

    public static boolean isAnimatedWebpHeader(byte[] imageHeaderBytes, int offset) {
        boolean isVp8x = matchBytePattern(imageHeaderBytes, offset + 12, WEBP_VP8X_BYTES);
        boolean hasAnimationBit = (imageHeaderBytes[offset + 20] & 2) == 2;
        return isVp8x && hasAnimationBit;
    }

    public static boolean isSimpleWebpHeader(byte[] imageHeaderBytes, int offset) {
        return matchBytePattern(imageHeaderBytes, offset + 12, WEBP_VP8_BYTES);
    }

    public static boolean isLosslessWebpHeader(byte[] imageHeaderBytes, int offset) {
        return matchBytePattern(imageHeaderBytes, offset + 12, WEBP_VP8L_BYTES);
    }

    public static boolean isExtendedWebpHeader(byte[] imageHeaderBytes, int offset, int headerSize) {
        return headerSize >= 21 && matchBytePattern(imageHeaderBytes, offset + 12, WEBP_VP8X_BYTES);
    }

    public static boolean isExtendedWebpHeaderWithAlpha(byte[] imageHeaderBytes, int offset) {
        boolean isVp8x = matchBytePattern(imageHeaderBytes, offset + 12, WEBP_VP8X_BYTES);
        boolean hasAlphaBit = (imageHeaderBytes[offset + 20] & Ascii.DLE) == 16;
        return isVp8x && hasAlphaBit;
    }

    public static boolean isWebpHeader(byte[] imageHeaderBytes, int offset, int headerSize) {
        return headerSize >= 20 && matchBytePattern(imageHeaderBytes, offset, WEBP_RIFF_BYTES) && matchBytePattern(imageHeaderBytes, offset + 8, WEBP_NAME_BYTES);
    }

    private static boolean matchBytePattern(byte[] byteArray, int offset, byte[] pattern) {
        if (pattern == null || byteArray == null || pattern.length + offset > byteArray.length) {
            return false;
        }
        for (int i = 0; i < pattern.length; i++) {
            if (byteArray[i + offset] != pattern[i]) {
                return false;
            }
        }
        return true;
    }
}
