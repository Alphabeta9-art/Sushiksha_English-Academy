package com.facebook.imagepipeline.filter;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InPlaceRoundFilter.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/facebook/imagepipeline/filter/InPlaceRoundFilter;", "", "()V", "roundBitmapInPlace", "", "bitmap", "Landroid/graphics/Bitmap;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class InPlaceRoundFilter {
    public static final InPlaceRoundFilter INSTANCE = new InPlaceRoundFilter();

    private InPlaceRoundFilter() {
    }

    @JvmStatic
    public static final void roundBitmapInPlace(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int radius = Math.min(w, h) / 2;
        int centerX = w / 2;
        int centerY = h / 2;
        if (radius == 0) {
            return;
        }
        Preconditions.checkArgument(Boolean.valueOf(radius >= 1));
        Preconditions.checkArgument(Boolean.valueOf(w > 0 && ((float) w) <= 2048.0f));
        Preconditions.checkArgument(Boolean.valueOf(h > 0 && ((float) h) <= 2048.0f));
        Preconditions.checkArgument(Boolean.valueOf(centerX > 0 && centerX < w));
        Preconditions.checkArgument(Boolean.valueOf(centerY > 0 && centerY < h));
        int[] pixels = new int[w * h];
        bitmap.getPixels(pixels, 0, w, 0, 0, w, h);
        int x = radius - 1;
        int y = 0;
        int maxX = centerX + x;
        int maxY = centerY + x;
        int minX = centerX - x;
        int minY = centerY - x;
        Preconditions.checkArgument(Boolean.valueOf(minX >= 0 && minY >= 0 && maxX < w && maxY < h));
        int rInc = (-radius) * 2;
        int[] transparentColor = new int[w];
        int err = 1 + rInc;
        int dy = 1;
        int err2 = err;
        int err3 = 1;
        while (x >= y) {
            int cXpX = centerX + x;
            int cXmX = centerX - x;
            int cXpY = centerX + y;
            int cXmY = centerX - y;
            int cYpX = centerY + x;
            int cYmX = centerY - x;
            int cYpY = centerY + y;
            int cYmY = centerY - y;
            Preconditions.checkArgument(Boolean.valueOf(x >= 0 && cXpY < w && cXmY >= 0 && cYpY < h && cYmY >= 0));
            radius = radius;
            int radius2 = w * cYpY;
            int x2 = x;
            int x3 = w * cYmY;
            int y2 = y;
            int y3 = w * cYpX;
            int cXpY2 = w * cYmX;
            centerX = centerX;
            System.arraycopy(transparentColor, 0, pixels, radius2, cXmX);
            System.arraycopy(transparentColor, 0, pixels, x3, cXmX);
            System.arraycopy(transparentColor, 0, pixels, y3, cXmY);
            System.arraycopy(transparentColor, 0, pixels, cXpY2, cXmY);
            int offA = radius2 + cXpX;
            int offB = w - cXpX;
            System.arraycopy(transparentColor, 0, pixels, offA, offB);
            System.arraycopy(transparentColor, 0, pixels, x3 + cXpX, w - cXpX);
            System.arraycopy(transparentColor, 0, pixels, y3 + cXpY, w - cXpY);
            System.arraycopy(transparentColor, 0, pixels, cXpY2 + cXpY, w - cXpY);
            if (err2 > 0) {
                y = y2;
            } else {
                y = y2 + 1;
                dy += 2;
                err2 += dy;
            }
            if (err2 > 0) {
                x = x2 - 1;
                err3 += 2;
                err2 += err3 + rInc;
            } else {
                x = x2;
            }
        }
        int radius3 = radius;
        for (int i = centerY - radius3; -1 < i; i--) {
            System.arraycopy(transparentColor, 0, pixels, i * w, w);
        }
        for (int i2 = centerY + radius3; i2 < h; i2++) {
            System.arraycopy(transparentColor, 0, pixels, i2 * w, w);
        }
        bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
    }
}
