package com.facebook.imagepipeline.transcoder;

import com.facebook.common.logging.FLog;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: DownsampleUtil.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J*\u0010\u000e\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0004H\u0007J \u0010\u0010\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004H\u0007J\u0018\u0010\u0013\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0006H\u0007J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0006H\u0007J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/facebook/imagepipeline/transcoder/DownsampleUtil;", "", "()V", "DEFAULT_SAMPLE_SIZE", "", "INTERVAL_ROUNDING", "", "determineDownsampleRatio", "rotationOptions", "Lcom/facebook/imagepipeline/common/RotationOptions;", "resizeOptions", "Lcom/facebook/imagepipeline/common/ResizeOptions;", "encodedImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "determineSampleSize", "maxBitmapSize", "determineSampleSizeJPEG", "pixelSize", "maxBitmapSizeInBytes", "getRotationAngle", "ratioToSampleSize", "ratio", "ratioToSampleSizeJPEG", "roundToPowerOfTwo", "sampleSize", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DownsampleUtil {
    public static final int DEFAULT_SAMPLE_SIZE = 1;
    public static final DownsampleUtil INSTANCE = new DownsampleUtil();
    private static final float INTERVAL_ROUNDING = 0.33333334f;

    private DownsampleUtil() {
    }

    @JvmStatic
    public static final int determineSampleSize(RotationOptions rotationOptions, ResizeOptions resizeOptions, EncodedImage encodedImage, int maxBitmapSize) {
        int sampleSize;
        Intrinsics.checkNotNullParameter(rotationOptions, "rotationOptions");
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        if (!EncodedImage.isMetaDataAvailable(encodedImage)) {
            return 1;
        }
        DownsampleUtil downsampleUtil = INSTANCE;
        float ratio = determineDownsampleRatio(rotationOptions, resizeOptions, encodedImage);
        if (encodedImage.getImageFormat() == DefaultImageFormats.JPEG) {
            DownsampleUtil downsampleUtil2 = INSTANCE;
            sampleSize = ratioToSampleSizeJPEG(ratio);
        } else {
            DownsampleUtil downsampleUtil3 = INSTANCE;
            sampleSize = ratioToSampleSize(ratio);
        }
        int maxDimension = Math.max(encodedImage.getHeight(), encodedImage.getWidth());
        float computedMaxBitmapSize = resizeOptions != null ? resizeOptions.maxBitmapSize : maxBitmapSize;
        while (maxDimension / sampleSize > computedMaxBitmapSize) {
            if (encodedImage.getImageFormat() == DefaultImageFormats.JPEG) {
                sampleSize *= 2;
            } else {
                sampleSize++;
            }
        }
        return sampleSize;
    }

    @JvmStatic
    public static final int determineSampleSizeJPEG(EncodedImage encodedImage, int pixelSize, int maxBitmapSizeInBytes) {
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        int sampleSize = encodedImage.getSampleSize();
        int base = encodedImage.getWidth() * encodedImage.getHeight() * pixelSize;
        while ((base / sampleSize) / sampleSize > maxBitmapSizeInBytes) {
            sampleSize *= 2;
        }
        return sampleSize;
    }

    @JvmStatic
    public static final float determineDownsampleRatio(RotationOptions rotationOptions, ResizeOptions resizeOptions, EncodedImage encodedImage) {
        Intrinsics.checkNotNullParameter(rotationOptions, "rotationOptions");
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        if (!EncodedImage.isMetaDataAvailable(encodedImage)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (resizeOptions == null || resizeOptions.height <= 0 || resizeOptions.width <= 0 || encodedImage.getWidth() == 0 || encodedImage.getHeight() == 0) {
            return 1.0f;
        }
        int rotationAngle = INSTANCE.getRotationAngle(rotationOptions, encodedImage);
        boolean swapDimensions = rotationAngle == 90 || rotationAngle == 270;
        int widthAfterRotation = swapDimensions ? encodedImage.getHeight() : encodedImage.getWidth();
        int heightAfterRotation = swapDimensions ? encodedImage.getWidth() : encodedImage.getHeight();
        float widthRatio = resizeOptions.width / widthAfterRotation;
        float heightRatio = resizeOptions.height / heightAfterRotation;
        float ratio = RangesKt.coerceAtLeast(widthRatio, heightRatio);
        FLog.v("DownsampleUtil", "Downsample - Specified size: %dx%d, image size: %dx%d ratio: %.1f x %.1f, ratio: %.3f", Integer.valueOf(resizeOptions.width), Integer.valueOf(resizeOptions.height), Integer.valueOf(widthAfterRotation), Integer.valueOf(heightAfterRotation), Float.valueOf(widthRatio), Float.valueOf(heightRatio), Float.valueOf(ratio));
        return ratio;
    }

    @JvmStatic
    public static final int ratioToSampleSize(float ratio) {
        if (ratio > 0.6666667f) {
            return 1;
        }
        int sampleSize = 2;
        while (true) {
            double intervalLength = 1.0d / (Math.pow(sampleSize, 2.0d) - ((double) sampleSize));
            double compare = (1.0d / ((double) sampleSize)) + (0.3333333432674408d * intervalLength);
            if (compare <= ratio) {
                return sampleSize - 1;
            }
            sampleSize++;
        }
    }

    @JvmStatic
    public static final int ratioToSampleSizeJPEG(float ratio) {
        if (ratio > 0.6666667f) {
            return 1;
        }
        int sampleSize = 2;
        while (true) {
            double intervalLength = 1.0d / ((double) (sampleSize * 2));
            double compare = (1.0d / ((double) (sampleSize * 2))) + (0.3333333432674408d * intervalLength);
            if (compare <= ratio) {
                return sampleSize;
            }
            sampleSize *= 2;
        }
    }

    private final int getRotationAngle(RotationOptions rotationOptions, EncodedImage encodedImage) {
        if (!rotationOptions.useImageMetadata()) {
            return 0;
        }
        int rotationAngle = encodedImage.getRotationAngle();
        if (!(rotationAngle == 0 || rotationAngle == 90 || rotationAngle == 180 || rotationAngle == 270)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        return rotationAngle;
    }

    @JvmStatic
    public static final int roundToPowerOfTwo(int sampleSize) {
        int compare = 1;
        while (compare < sampleSize) {
            compare *= 2;
        }
        return compare;
    }
}
