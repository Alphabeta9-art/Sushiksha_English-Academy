package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;

/* JADX INFO: loaded from: classes11.dex */
class DefaultCloseableStaticBitmap extends BaseCloseableStaticBitmap {
    private static final String TAG = "DefaultCloseableStaticBitmap";

    protected DefaultCloseableStaticBitmap(CloseableReference<Bitmap> bitmapReference, QualityInfo qualityInfo, int rotationAngle, int exifOrientation) {
        super(bitmapReference, qualityInfo, rotationAngle, exifOrientation);
    }

    protected DefaultCloseableStaticBitmap(Bitmap bitmap, ResourceReleaser<Bitmap> resourceReleaser, QualityInfo qualityInfo, int rotationAngle, int exifOrientation) {
        super(bitmap, resourceReleaser, qualityInfo, rotationAngle, exifOrientation);
    }

    protected void finalize() throws Throwable {
        if (isClosed()) {
            return;
        }
        FLog.w(TAG, "finalize: %s %x still open.", getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(this)));
        try {
            close();
        } finally {
            super.finalize();
        }
    }
}
