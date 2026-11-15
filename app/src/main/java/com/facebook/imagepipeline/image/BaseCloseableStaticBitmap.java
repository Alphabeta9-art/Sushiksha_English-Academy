package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imageutils.BitmapUtil;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class BaseCloseableStaticBitmap extends BaseCloseableImage implements CloseableStaticBitmap {
    private static boolean sUseSimpleCloseableStaticBitmap = false;

    @Nullable
    private volatile Bitmap mBitmap;

    @Nullable
    private CloseableReference<Bitmap> mBitmapReference;
    private final int mExifOrientation;
    private final QualityInfo mQualityInfo;
    private final int mRotationAngle;

    protected BaseCloseableStaticBitmap(Bitmap bitmap, ResourceReleaser<Bitmap> resourceReleaser, QualityInfo qualityInfo, int rotationAngle, int exifOrientation) {
        this.mBitmap = (Bitmap) Preconditions.checkNotNull(bitmap);
        this.mBitmapReference = CloseableReference.of(this.mBitmap, (ResourceReleaser<Bitmap>) Preconditions.checkNotNull(resourceReleaser));
        this.mQualityInfo = qualityInfo;
        this.mRotationAngle = rotationAngle;
        this.mExifOrientation = exifOrientation;
    }

    protected BaseCloseableStaticBitmap(CloseableReference<Bitmap> bitmapReference, QualityInfo qualityInfo, int rotationAngle) {
        this(bitmapReference, qualityInfo, rotationAngle, 0);
    }

    protected BaseCloseableStaticBitmap(CloseableReference<Bitmap> bitmapReference, QualityInfo qualityInfo, int rotationAngle, int exifOrientation) {
        this.mBitmapReference = (CloseableReference) Preconditions.checkNotNull(bitmapReference.cloneOrNull());
        this.mBitmap = this.mBitmapReference.get();
        this.mQualityInfo = qualityInfo;
        this.mRotationAngle = rotationAngle;
        this.mExifOrientation = exifOrientation;
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        CloseableReference<Bitmap> reference = detachBitmapReference();
        if (reference != null) {
            reference.close();
        }
    }

    private synchronized CloseableReference<Bitmap> detachBitmapReference() {
        CloseableReference<Bitmap> reference;
        reference = this.mBitmapReference;
        this.mBitmapReference = null;
        this.mBitmap = null;
        return reference;
    }

    @Override // com.facebook.imagepipeline.image.CloseableStaticBitmap
    public synchronized CloseableReference<Bitmap> convertToBitmapReference() {
        Preconditions.checkNotNull(this.mBitmapReference, "Cannot convert a closed static bitmap");
        return detachBitmapReference();
    }

    @Override // com.facebook.imagepipeline.image.CloseableStaticBitmap
    @Nullable
    public synchronized CloseableReference<Bitmap> cloneUnderlyingBitmapReference() {
        return CloseableReference.cloneOrNull(this.mBitmapReference);
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage
    public synchronized boolean isClosed() {
        return this.mBitmapReference == null;
    }

    @Override // com.facebook.imagepipeline.image.CloseableBitmap
    public Bitmap getUnderlyingBitmap() {
        return this.mBitmap;
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage, com.facebook.imagepipeline.image.ImageInfo
    public int getSizeInBytes() {
        return BitmapUtil.getSizeInBytes(this.mBitmap);
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage, com.facebook.imagepipeline.image.ImageInfo
    public int getWidth() {
        if (this.mRotationAngle % RotationOptions.ROTATE_180 != 0 || this.mExifOrientation == 5 || this.mExifOrientation == 7) {
            return getBitmapHeight(this.mBitmap);
        }
        return getBitmapWidth(this.mBitmap);
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage, com.facebook.imagepipeline.image.ImageInfo
    public int getHeight() {
        if (this.mRotationAngle % RotationOptions.ROTATE_180 != 0 || this.mExifOrientation == 5 || this.mExifOrientation == 7) {
            return getBitmapWidth(this.mBitmap);
        }
        return getBitmapHeight(this.mBitmap);
    }

    private static int getBitmapWidth(@Nullable Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        return bitmap.getWidth();
    }

    private static int getBitmapHeight(@Nullable Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        return bitmap.getHeight();
    }

    @Override // com.facebook.imagepipeline.image.CloseableStaticBitmap
    public int getRotationAngle() {
        return this.mRotationAngle;
    }

    @Override // com.facebook.imagepipeline.image.CloseableStaticBitmap
    public int getExifOrientation() {
        return this.mExifOrientation;
    }

    @Override // com.facebook.imagepipeline.image.BaseCloseableImage, com.facebook.imagepipeline.image.CloseableImage, com.facebook.imagepipeline.image.ImageInfo
    public QualityInfo getQualityInfo() {
        return this.mQualityInfo;
    }

    public static void setUseSimpleCloseableStaticBitmap(boolean useSimpleCloseableStaticBitmap) {
        sUseSimpleCloseableStaticBitmap = useSimpleCloseableStaticBitmap;
    }

    public static boolean shouldUseSimpleCloseableStaticBitmap() {
        return sUseSimpleCloseableStaticBitmap;
    }
}
