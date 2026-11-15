package com.facebook.imagepipeline.decoder;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import com.facebook.imagepipeline.transformation.CircularTransformation;
import com.facebook.imagepipeline.transformation.TransformationUtils;
import java.io.InputStream;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class DefaultImageDecoder implements ImageDecoder {

    @Nullable
    private final ImageDecoder mAnimatedGifDecoder;

    @Nullable
    private final ImageDecoder mAnimatedWebPDecoder;

    @Nullable
    private final Map<ImageFormat, ImageDecoder> mCustomDecoders;
    private final ImageDecoder mDefaultDecoder;
    private final Supplier<Boolean> mEnableEncodedImageColorSpaceUsage;
    private final PlatformDecoder mPlatformDecoder;

    public DefaultImageDecoder(@Nullable ImageDecoder animatedGifDecoder, @Nullable ImageDecoder animatedWebPDecoder, PlatformDecoder platformDecoder) {
        this(animatedGifDecoder, animatedWebPDecoder, platformDecoder, null);
    }

    public DefaultImageDecoder(@Nullable ImageDecoder animatedGifDecoder, @Nullable ImageDecoder animatedWebPDecoder, PlatformDecoder platformDecoder, @Nullable Map<ImageFormat, ImageDecoder> customDecoders) {
        this.mDefaultDecoder = new ImageDecoder() { // from class: com.facebook.imagepipeline.decoder.DefaultImageDecoder.1
            @Override // com.facebook.imagepipeline.decoder.ImageDecoder
            @Nullable
            public CloseableImage decode(EncodedImage encodedImage, int length, QualityInfo qualityInfo, ImageDecodeOptions options) {
                ColorSpace colorSpace;
                ImageFormat imageFormat = encodedImage.getImageFormat();
                if (((Boolean) DefaultImageDecoder.this.mEnableEncodedImageColorSpaceUsage.get()).booleanValue()) {
                    colorSpace = options.colorSpace == null ? encodedImage.getColorSpace() : options.colorSpace;
                } else {
                    ColorSpace colorSpace2 = options.colorSpace;
                    colorSpace = colorSpace2;
                }
                if (imageFormat == DefaultImageFormats.JPEG) {
                    return DefaultImageDecoder.this.decodeJpeg(encodedImage, length, qualityInfo, options, colorSpace);
                }
                if (imageFormat == DefaultImageFormats.GIF) {
                    return DefaultImageDecoder.this.decodeGif(encodedImage, length, qualityInfo, options);
                }
                if (imageFormat == DefaultImageFormats.WEBP_ANIMATED) {
                    return DefaultImageDecoder.this.decodeAnimatedWebp(encodedImage, length, qualityInfo, options);
                }
                if (imageFormat != ImageFormat.UNKNOWN) {
                    return DefaultImageDecoder.this.decodeStaticImage(encodedImage, options);
                }
                throw new DecodeException("unknown image format", encodedImage);
            }
        };
        this.mAnimatedGifDecoder = animatedGifDecoder;
        this.mAnimatedWebPDecoder = animatedWebPDecoder;
        this.mPlatformDecoder = platformDecoder;
        this.mCustomDecoders = customDecoders;
        this.mEnableEncodedImageColorSpaceUsage = Suppliers.BOOLEAN_FALSE;
    }

    public DefaultImageDecoder(@Nullable ImageDecoder animatedGifDecoder, @Nullable ImageDecoder animatedWebPDecoder, PlatformDecoder platformDecoder, @Nullable Map<ImageFormat, ImageDecoder> customDecoders, Supplier<Boolean> enableEncodedImageColorSpaceUsage) {
        this.mDefaultDecoder = new ImageDecoder() { // from class: com.facebook.imagepipeline.decoder.DefaultImageDecoder.1
            @Override // com.facebook.imagepipeline.decoder.ImageDecoder
            @Nullable
            public CloseableImage decode(EncodedImage encodedImage, int length, QualityInfo qualityInfo, ImageDecodeOptions options) {
                ColorSpace colorSpace;
                ImageFormat imageFormat = encodedImage.getImageFormat();
                if (((Boolean) DefaultImageDecoder.this.mEnableEncodedImageColorSpaceUsage.get()).booleanValue()) {
                    colorSpace = options.colorSpace == null ? encodedImage.getColorSpace() : options.colorSpace;
                } else {
                    ColorSpace colorSpace2 = options.colorSpace;
                    colorSpace = colorSpace2;
                }
                if (imageFormat == DefaultImageFormats.JPEG) {
                    return DefaultImageDecoder.this.decodeJpeg(encodedImage, length, qualityInfo, options, colorSpace);
                }
                if (imageFormat == DefaultImageFormats.GIF) {
                    return DefaultImageDecoder.this.decodeGif(encodedImage, length, qualityInfo, options);
                }
                if (imageFormat == DefaultImageFormats.WEBP_ANIMATED) {
                    return DefaultImageDecoder.this.decodeAnimatedWebp(encodedImage, length, qualityInfo, options);
                }
                if (imageFormat != ImageFormat.UNKNOWN) {
                    return DefaultImageDecoder.this.decodeStaticImage(encodedImage, options);
                }
                throw new DecodeException("unknown image format", encodedImage);
            }
        };
        this.mAnimatedGifDecoder = animatedGifDecoder;
        this.mAnimatedWebPDecoder = animatedWebPDecoder;
        this.mPlatformDecoder = platformDecoder;
        this.mCustomDecoders = customDecoders;
        this.mEnableEncodedImageColorSpaceUsage = enableEncodedImageColorSpaceUsage;
    }

    @Override // com.facebook.imagepipeline.decoder.ImageDecoder
    @Nullable
    public CloseableImage decode(EncodedImage encodedImage, int length, QualityInfo qualityInfo, ImageDecodeOptions options) {
        InputStream inputStream;
        ImageDecoder decoder;
        if (options.customImageDecoder != null) {
            return options.customImageDecoder.decode(encodedImage, length, qualityInfo, options);
        }
        ImageFormat imageFormat = encodedImage.getImageFormat();
        if ((imageFormat == null || imageFormat == ImageFormat.UNKNOWN) && (inputStream = encodedImage.getInputStream()) != null) {
            imageFormat = ImageFormatChecker.getImageFormat_WrapIOException(inputStream);
            encodedImage.setImageFormat(imageFormat);
        }
        if (this.mCustomDecoders != null && (decoder = this.mCustomDecoders.get(imageFormat)) != null) {
            return decoder.decode(encodedImage, length, qualityInfo, options);
        }
        return this.mDefaultDecoder.decode(encodedImage, length, qualityInfo, options);
    }

    @Nullable
    public CloseableImage decodeGif(EncodedImage encodedImage, int length, QualityInfo qualityInfo, ImageDecodeOptions options) {
        if (encodedImage.getWidth() == -1 || encodedImage.getHeight() == -1) {
            throw new DecodeException("image width or height is incorrect", encodedImage);
        }
        if (!options.forceStaticImage && this.mAnimatedGifDecoder != null) {
            return this.mAnimatedGifDecoder.decode(encodedImage, length, qualityInfo, options);
        }
        return decodeStaticImage(encodedImage, options);
    }

    public CloseableStaticBitmap decodeStaticImage(EncodedImage encodedImage, ImageDecodeOptions options) {
        CloseableReference<Bitmap> bitmapReference = this.mPlatformDecoder.decodeFromEncodedImageWithColorSpace(encodedImage, options.bitmapConfig, null, options.colorSpace);
        try {
            boolean didApplyTransformation = TransformationUtils.maybeApplyTransformation(options.bitmapTransformation, bitmapReference);
            Preconditions.checkNotNull(bitmapReference);
            CloseableStaticBitmap closeableStaticBitmap = CloseableStaticBitmap.of(bitmapReference, ImmutableQualityInfo.FULL_QUALITY, encodedImage.getRotationAngle(), encodedImage.getExifOrientation());
            closeableStaticBitmap.putExtra("is_rounded", Boolean.valueOf(didApplyTransformation && (options.bitmapTransformation instanceof CircularTransformation)));
            return closeableStaticBitmap;
        } finally {
            CloseableReference.closeSafely(bitmapReference);
        }
    }

    public CloseableStaticBitmap decodeJpeg(EncodedImage encodedImage, int length, QualityInfo qualityInfo, ImageDecodeOptions options, @Nullable ColorSpace colorSpace) {
        CloseableReference<Bitmap> bitmapReference = this.mPlatformDecoder.decodeJPEGFromEncodedImageWithColorSpace(encodedImage, options.bitmapConfig, null, length, colorSpace);
        try {
            boolean didApplyTransformation = TransformationUtils.maybeApplyTransformation(options.bitmapTransformation, bitmapReference);
            Preconditions.checkNotNull(bitmapReference);
            CloseableStaticBitmap closeableStaticBitmap = CloseableStaticBitmap.of(bitmapReference, qualityInfo, encodedImage.getRotationAngle(), encodedImage.getExifOrientation());
            closeableStaticBitmap.putExtra("is_rounded", Boolean.valueOf(didApplyTransformation && (options.bitmapTransformation instanceof CircularTransformation)));
            return closeableStaticBitmap;
        } finally {
            CloseableReference.closeSafely(bitmapReference);
        }
    }

    @Nullable
    public CloseableImage decodeAnimatedWebp(EncodedImage encodedImage, int length, QualityInfo qualityInfo, ImageDecodeOptions options) {
        if (!options.forceStaticImage && this.mAnimatedWebPDecoder != null) {
            return this.mAnimatedWebPDecoder.decode(encodedImage, length, qualityInfo, options);
        }
        return decodeStaticImage(encodedImage, options);
    }
}
