package com.facebook.imagepipeline.filter;

import android.graphics.Bitmap;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: IterativeBoxBlurFilter.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0007J \u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J8\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0013H\u0002J@\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/facebook/imagepipeline/filter/IterativeBoxBlurFilter;", "", "()V", "TAG", "", "bound", "", "x", CmcdHeadersFactory.STREAM_TYPE_LIVE, CmcdHeadersFactory.STREAMING_FORMAT_HLS, "boxBlurBitmapInPlace", "", "bitmap", "Landroid/graphics/Bitmap;", "iterations", "radius", "fastBoxBlur", "internalHorizontalBlur", "pixels", "", "outRow", "w", "row", "diameter", TtmlNode.TAG_DIV, "internalVerticalBlur", "outCol", "col", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class IterativeBoxBlurFilter {
    public static final IterativeBoxBlurFilter INSTANCE = new IterativeBoxBlurFilter();
    private static final String TAG = "IterativeBoxBlurFilter";

    private IterativeBoxBlurFilter() {
    }

    @JvmStatic
    public static final void boxBlurBitmapInPlace(Bitmap bitmap, int iterations, int radius) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Preconditions.checkArgument(Boolean.valueOf(bitmap.isMutable()));
        Preconditions.checkArgument(Boolean.valueOf(((float) bitmap.getHeight()) <= 2048.0f));
        Preconditions.checkArgument(Boolean.valueOf(((float) bitmap.getWidth()) <= 2048.0f));
        Preconditions.checkArgument(Boolean.valueOf(radius > 0 && radius <= 25));
        Preconditions.checkArgument(Boolean.valueOf(iterations > 0));
        try {
            INSTANCE.fastBoxBlur(bitmap, iterations, radius);
        } catch (OutOfMemoryError oom) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format(null, "OOM: %d iterations on %dx%d with %d radius", Arrays.copyOf(new Object[]{Integer.valueOf(iterations), Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), Integer.valueOf(radius)}, 4));
            Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
            FLog.e(TAG, str);
            throw oom;
        }
    }

    private final void fastBoxBlur(Bitmap bitmap, int iterations, int radius) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int[] pixels = new int[w * h];
        bitmap.getPixels(pixels, 0, w, 0, 0, w, h);
        int col = radius + 1 + radius;
        int[] div = new int[col * 256];
        int ptr = radius + 1;
        int ptr2 = ptr;
        for (int b = 1; b < 256; b++) {
            for (int d = 0; d < col; d++) {
                div[ptr2] = b;
                ptr2++;
            }
        }
        int[] tempRowOrColumn = new int[Math.max(w, h)];
        int i = 0;
        while (i < iterations) {
            for (int row = 0; row < h; row++) {
                internalHorizontalBlur(pixels, tempRowOrColumn, w, row, col, div);
                System.arraycopy(tempRowOrColumn, 0, pixels, row * w, w);
            }
            int pos = 0;
            while (pos < w) {
                int h2 = h;
                int[] div2 = div;
                int diameter = col;
                int diameter2 = pos;
                internalVerticalBlur(pixels, tempRowOrColumn, w, h2, diameter2, diameter, div2);
                int[] tempRowOrColumn2 = tempRowOrColumn;
                h = h2;
                int pos2 = diameter2;
                for (int row2 = 0; row2 < h; row2++) {
                    pixels[pos2] = tempRowOrColumn2[row2];
                    pos2 += w;
                }
                pos = diameter2 + 1;
                col = diameter;
                div = div2;
                tempRowOrColumn = tempRowOrColumn2;
            }
            i++;
            col = col;
        }
        bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
    }

    private final void internalHorizontalBlur(int[] pixels, int[] outRow, int w, int row, int diameter, int[] div) {
        int firstInByte = w * row;
        int lastInByte = ((row + 1) * w) - 1;
        int radius = diameter >> 1;
        int a = 0;
        int r = 0;
        int g = 0;
        int b = 0;
        int i = w + radius;
        for (int i2 = -radius; i2 < i; i2++) {
            int ii = bound(firstInByte + i2, firstInByte, lastInByte);
            int pixel = pixels[ii];
            r += (pixel >> 16) & 255;
            g += (pixel >> 8) & 255;
            b += pixel & 255;
            a += pixel >>> 24;
            if (i2 >= radius) {
                int outOffset = i2 - radius;
                outRow[outOffset] = (div[a] << 24) | (div[r] << 16) | (div[g] << 8) | div[b];
                int j = i2 - (diameter - 1);
                int jj = bound(firstInByte + j, firstInByte, lastInByte);
                int pixel2 = pixels[jj];
                r -= (pixel2 >> 16) & 255;
                g -= (pixel2 >> 8) & 255;
                b -= pixel2 & 255;
                a -= pixel2 >>> 24;
            }
        }
    }

    private final void internalVerticalBlur(int[] pixels, int[] outCol, int w, int h, int col, int diameter, int[] div) {
        int lastInByte = ((h - 1) * w) + col;
        int radiusTimesW = (diameter >> 1) * w;
        int diameterMinusOneTimesW = (diameter - 1) * w;
        int a = 0;
        int r = 0;
        int g = 0;
        int b = 0;
        int outColPos = 0;
        int i = col - radiusTimesW;
        while (i <= lastInByte + radiusTimesW) {
            int ii = bound(i, col, lastInByte);
            int pixel = pixels[ii];
            r += (pixel >> 16) & 255;
            g += (pixel >> 8) & 255;
            b += pixel & 255;
            a += pixel >>> 24;
            int outPos = i - radiusTimesW;
            if (outPos >= col) {
                outCol[outColPos] = (div[a] << 24) | (div[r] << 16) | (div[g] << 8) | div[b];
                outColPos++;
                int j = i - diameterMinusOneTimesW;
                int jj = bound(j, col, lastInByte);
                int pixel2 = pixels[jj];
                r -= (pixel2 >> 16) & 255;
                g -= (pixel2 >> 8) & 255;
                b -= pixel2 & 255;
                a -= pixel2 >>> 24;
            }
            i += w;
        }
    }

    private final int bound(int x, int l, int h) {
        if (x < l) {
            return l;
        }
        return x > h ? h : x;
    }
}
