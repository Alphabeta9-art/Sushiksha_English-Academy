package com.facebook.imagepipeline.transcoder;

import android.graphics.Matrix;
import com.facebook.common.internal.ImmutableList;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JpegTranscoderUtils.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0007J\"\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0007J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u0018\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J*\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 H\u0007J\u001a\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0012\u0010#\u001a\u0004\u0018\u00010\"2\u0006\u0010$\u001a\u00020\u0004H\u0002J\u0010\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020\u0004H\u0007J\u0010\u0010'\u001a\u00020 2\u0006\u0010(\u001a\u00020\u0004H\u0007J\u0018\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u0012H\u0007R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\u00040\u00040\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/facebook/imagepipeline/transcoder/JpegTranscoderUtils;", "", "()V", "DEFAULT_JPEG_QUALITY", "", "getDEFAULT_JPEG_QUALITY$annotations", "FULL_ROUND", "INVERTED_EXIF_ORIENTATIONS", "Lcom/facebook/common/internal/ImmutableList;", "kotlin.jvm.PlatformType", "MAX_QUALITY", "MAX_SCALE_NUMERATOR", "MIN_QUALITY", "MIN_SCALE_NUMERATOR", "SCALE_DENOMINATOR", "calculateDownsampleNumerator", "downsampleRatio", "determineResizeRatio", "", "resizeOptions", "Lcom/facebook/imagepipeline/common/ResizeOptions;", "width", "height", "extractOrientationFromMetadata", "encodedImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "getForceRotatedInvertedExifOrientation", "rotationOptions", "Lcom/facebook/imagepipeline/common/RotationOptions;", "getRotationAngle", "getSoftwareNumerator", "resizingEnabled", "", "getTransformationMatrix", "Landroid/graphics/Matrix;", "getTransformationMatrixFromInvertedExif", "orientation", "isExifOrientationAllowed", "exifOrientation", "isRotationAngleAllowed", "degrees", "roundNumerator", "maxRatio", "roundUpFraction", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class JpegTranscoderUtils {
    public static final int DEFAULT_JPEG_QUALITY = 85;
    private static final int FULL_ROUND = 360;
    public static final JpegTranscoderUtils INSTANCE = new JpegTranscoderUtils();
    public static final ImmutableList<Integer> INVERTED_EXIF_ORIENTATIONS;
    public static final int MAX_QUALITY = 100;
    public static final int MAX_SCALE_NUMERATOR = 16;
    public static final int MIN_QUALITY = 0;
    public static final int MIN_SCALE_NUMERATOR = 1;
    public static final int SCALE_DENOMINATOR = 8;

    public static /* synthetic */ void getDEFAULT_JPEG_QUALITY$annotations() {
    }

    private JpegTranscoderUtils() {
    }

    static {
        ImmutableList<Integer> immutableListOf = ImmutableList.of((Object[]) new Integer[]{2, 7, 4, 5});
        Intrinsics.checkNotNullExpressionValue(immutableListOf, "of(\n          ExifInterf…ce.ORIENTATION_TRANSPOSE)");
        INVERTED_EXIF_ORIENTATIONS = immutableListOf;
    }

    @JvmStatic
    public static final boolean isRotationAngleAllowed(int degrees) {
        return degrees >= 0 && degrees <= 270 && degrees % 90 == 0;
    }

    @JvmStatic
    public static final boolean isExifOrientationAllowed(int exifOrientation) {
        switch (exifOrientation) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                return false;
        }
    }

    @JvmStatic
    public static final int getSoftwareNumerator(RotationOptions rotationOptions, ResizeOptions resizeOptions, EncodedImage encodedImage, boolean resizingEnabled) {
        Intrinsics.checkNotNullParameter(rotationOptions, "rotationOptions");
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        if (!resizingEnabled || resizeOptions == null) {
            return 8;
        }
        JpegTranscoderUtils jpegTranscoderUtils = INSTANCE;
        int rotationAngle = getRotationAngle(rotationOptions, encodedImage);
        int exifOrientation = 0;
        if (INVERTED_EXIF_ORIENTATIONS.contains(Integer.valueOf(encodedImage.getExifOrientation()))) {
            JpegTranscoderUtils jpegTranscoderUtils2 = INSTANCE;
            exifOrientation = getForceRotatedInvertedExifOrientation(rotationOptions, encodedImage);
        }
        boolean swapDimensions = rotationAngle == 90 || rotationAngle == 270 || exifOrientation == 5 || exifOrientation == 7;
        int widthAfterRotation = swapDimensions ? encodedImage.getHeight() : encodedImage.getWidth();
        int heightAfterRotation = swapDimensions ? encodedImage.getWidth() : encodedImage.getHeight();
        JpegTranscoderUtils jpegTranscoderUtils3 = INSTANCE;
        float ratio = determineResizeRatio(resizeOptions, widthAfterRotation, heightAfterRotation);
        JpegTranscoderUtils jpegTranscoderUtils4 = INSTANCE;
        int numerator = roundNumerator(ratio, resizeOptions.roundUpFraction);
        if (numerator > 8) {
            return 8;
        }
        if (numerator >= 1) {
            return numerator;
        }
        return 1;
    }

    @JvmStatic
    public static final int getRotationAngle(RotationOptions rotationOptions, EncodedImage encodedImage) {
        Intrinsics.checkNotNullParameter(rotationOptions, "rotationOptions");
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        if (!rotationOptions.rotationEnabled()) {
            return 0;
        }
        int rotationFromMetadata = INSTANCE.extractOrientationFromMetadata(encodedImage);
        if (rotationOptions.useImageMetadata()) {
            return rotationFromMetadata;
        }
        return (rotationOptions.getForcedAngle() + rotationFromMetadata) % FULL_ROUND;
    }

    @JvmStatic
    public static final int getForceRotatedInvertedExifOrientation(RotationOptions rotationOptions, EncodedImage encodedImage) {
        Intrinsics.checkNotNullParameter(rotationOptions, "rotationOptions");
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        int exifOrientation = encodedImage.getExifOrientation();
        int index = INVERTED_EXIF_ORIENTATIONS.indexOf(Integer.valueOf(exifOrientation));
        if (!(index >= 0)) {
            throw new IllegalArgumentException("Only accepts inverted exif orientations".toString());
        }
        int forcedAngle = 0;
        if (!rotationOptions.useImageMetadata()) {
            forcedAngle = rotationOptions.getForcedAngle();
        }
        int timesToRotate = forcedAngle / 90;
        Integer num = INVERTED_EXIF_ORIENTATIONS.get((index + timesToRotate) % INVERTED_EXIF_ORIENTATIONS.size());
        Intrinsics.checkNotNullExpressionValue(num, "INVERTED_EXIF_ORIENTATIO…D_EXIF_ORIENTATIONS.size]");
        return num.intValue();
    }

    @JvmStatic
    public static final float determineResizeRatio(ResizeOptions resizeOptions, int width, int height) {
        if (resizeOptions == null) {
            return 1.0f;
        }
        float widthRatio = resizeOptions.width / width;
        float heightRatio = resizeOptions.height / height;
        float ratio = Math.max(widthRatio, heightRatio);
        if (width * ratio > resizeOptions.maxBitmapSize) {
            ratio = resizeOptions.maxBitmapSize / width;
        }
        if (height * ratio > resizeOptions.maxBitmapSize) {
            return resizeOptions.maxBitmapSize / height;
        }
        return ratio;
    }

    @JvmStatic
    public static final int roundNumerator(float maxRatio, float roundUpFraction) {
        return (int) ((8.0f * maxRatio) + roundUpFraction);
    }

    @JvmStatic
    public static final int calculateDownsampleNumerator(int downsampleRatio) {
        return Math.max(1, 8 / downsampleRatio);
    }

    @JvmStatic
    public static final Matrix getTransformationMatrix(EncodedImage encodedImage, RotationOptions rotationOptions) {
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        Intrinsics.checkNotNullParameter(rotationOptions, "rotationOptions");
        if (INVERTED_EXIF_ORIENTATIONS.contains(Integer.valueOf(encodedImage.getExifOrientation()))) {
            JpegTranscoderUtils jpegTranscoderUtils = INSTANCE;
            int exifOrientation = getForceRotatedInvertedExifOrientation(rotationOptions, encodedImage);
            return INSTANCE.getTransformationMatrixFromInvertedExif(exifOrientation);
        }
        JpegTranscoderUtils jpegTranscoderUtils2 = INSTANCE;
        int rotationAngle = getRotationAngle(rotationOptions, encodedImage);
        if (rotationAngle == 0) {
            return null;
        }
        Matrix transformationMatrix = new Matrix();
        transformationMatrix.setRotate(rotationAngle);
        return transformationMatrix;
    }

    private final Matrix getTransformationMatrixFromInvertedExif(int orientation) {
        Matrix matrix = new Matrix();
        switch (orientation) {
            case 2:
                matrix.setScale(-1.0f, 1.0f);
                return matrix;
            case 3:
            case 6:
            default:
                return null;
            case 4:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                return matrix;
            case 5:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                return matrix;
            case 7:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                return matrix;
        }
    }

    private final int extractOrientationFromMetadata(EncodedImage encodedImage) {
        switch (encodedImage.getRotationAngle()) {
            case RotationOptions.ROTATE_90 /* 90 */:
            case RotationOptions.ROTATE_180 /* 180 */:
            case RotationOptions.ROTATE_270 /* 270 */:
                return encodedImage.getRotationAngle();
            default:
                return 0;
        }
    }
}
