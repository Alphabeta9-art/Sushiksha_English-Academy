package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;

/* JADX INFO: loaded from: classes11.dex */
public interface CloseableStaticBitmap extends CloseableBitmap {
    CloseableReference<Bitmap> cloneUnderlyingBitmapReference();

    CloseableReference<Bitmap> convertToBitmapReference();

    int getExifOrientation();

    int getRotationAngle();

    static CloseableStaticBitmap of(Bitmap bitmap, ResourceReleaser<Bitmap> resourceReleaser, QualityInfo qualityInfo, int rotationAngle) {
        return of(bitmap, resourceReleaser, qualityInfo, rotationAngle, 0);
    }

    static CloseableStaticBitmap of(CloseableReference<Bitmap> bitmapReference, QualityInfo qualityInfo, int rotationAngle) {
        return of(bitmapReference, qualityInfo, rotationAngle, 0);
    }

    static CloseableStaticBitmap of(Bitmap bitmap, ResourceReleaser<Bitmap> resourceReleaser, QualityInfo qualityInfo, int rotationAngle, int orientation) {
        if (BaseCloseableStaticBitmap.shouldUseSimpleCloseableStaticBitmap()) {
            return new BaseCloseableStaticBitmap(bitmap, resourceReleaser, qualityInfo, rotationAngle, orientation);
        }
        return new DefaultCloseableStaticBitmap(bitmap, resourceReleaser, qualityInfo, rotationAngle, orientation);
    }

    static CloseableStaticBitmap of(CloseableReference<Bitmap> bitmapReference, QualityInfo qualityInfo, int rotationAngle, int exifOrientation) {
        if (BaseCloseableStaticBitmap.shouldUseSimpleCloseableStaticBitmap()) {
            return new BaseCloseableStaticBitmap(bitmapReference, qualityInfo, rotationAngle, exifOrientation);
        }
        return new DefaultCloseableStaticBitmap(bitmapReference, qualityInfo, rotationAngle, exifOrientation);
    }
}
