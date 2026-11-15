package com.facebook.imagepipeline.image;

import android.graphics.ColorSpace;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.SharedReference;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imageutils.BitmapUtil;
import com.facebook.imageutils.HeifExifUtil;
import com.facebook.imageutils.ImageMetaData;
import com.facebook.imageutils.JfifUtil;
import com.facebook.imageutils.WebpUtil;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;
import kotlin.Pair;

/* JADX INFO: loaded from: classes11.dex */
public class EncodedImage implements Closeable {
    public static final int DEFAULT_SAMPLE_SIZE = 1;
    public static final int UNKNOWN_HEIGHT = -1;
    public static final int UNKNOWN_ROTATION_ANGLE = -1;
    public static final int UNKNOWN_STREAM_SIZE = -1;
    public static final int UNKNOWN_WIDTH = -1;
    private static boolean sUseCachedMetadata;

    @Nullable
    private BytesRange mBytesRange;

    @Nullable
    private ColorSpace mColorSpace;
    private int mExifOrientation;
    private boolean mHasParsedMetadata;
    private int mHeight;
    private ImageFormat mImageFormat;

    @Nullable
    private final Supplier<FileInputStream> mInputStreamSupplier;

    @Nullable
    private final CloseableReference<PooledByteBuffer> mPooledByteBufferRef;
    private int mRotationAngle;
    private int mSampleSize;

    @Nullable
    private String mSource;
    private int mStreamSize;
    private int mWidth;

    public EncodedImage(CloseableReference<PooledByteBuffer> pooledByteBufferRef) {
        this.mImageFormat = ImageFormat.UNKNOWN;
        this.mRotationAngle = -1;
        this.mExifOrientation = 0;
        this.mWidth = -1;
        this.mHeight = -1;
        this.mSampleSize = 1;
        this.mStreamSize = -1;
        Preconditions.checkArgument(Boolean.valueOf(CloseableReference.isValid(pooledByteBufferRef)));
        this.mPooledByteBufferRef = pooledByteBufferRef.mo8779clone();
        this.mInputStreamSupplier = null;
    }

    public EncodedImage(Supplier<FileInputStream> inputStreamSupplier) {
        this.mImageFormat = ImageFormat.UNKNOWN;
        this.mRotationAngle = -1;
        this.mExifOrientation = 0;
        this.mWidth = -1;
        this.mHeight = -1;
        this.mSampleSize = 1;
        this.mStreamSize = -1;
        Preconditions.checkNotNull(inputStreamSupplier);
        this.mPooledByteBufferRef = null;
        this.mInputStreamSupplier = inputStreamSupplier;
    }

    public EncodedImage(Supplier<FileInputStream> inputStreamSupplier, int streamSize) {
        this(inputStreamSupplier);
        this.mStreamSize = streamSize;
    }

    @Nullable
    public static EncodedImage cloneOrNull(@Nullable EncodedImage encodedImage) {
        if (encodedImage != null) {
            return encodedImage.cloneOrNull();
        }
        return null;
    }

    @Nullable
    public EncodedImage cloneOrNull() {
        EncodedImage encodedImage;
        EncodedImage encodedImage2;
        if (this.mInputStreamSupplier != null) {
            encodedImage2 = new EncodedImage(this.mInputStreamSupplier, this.mStreamSize);
        } else {
            CloseableReference<PooledByteBuffer> pooledByteBufferRef = CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
            if (pooledByteBufferRef == null) {
                encodedImage = null;
            } else {
                try {
                    encodedImage = new EncodedImage(pooledByteBufferRef);
                } catch (Throwable th) {
                    CloseableReference.closeSafely(pooledByteBufferRef);
                    throw th;
                }
            }
            CloseableReference.closeSafely(pooledByteBufferRef);
            encodedImage2 = encodedImage;
        }
        if (encodedImage2 != null) {
            encodedImage2.copyMetaDataFrom(this);
        }
        return encodedImage2;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        CloseableReference.closeSafely(this.mPooledByteBufferRef);
    }

    public synchronized boolean isValid() {
        return CloseableReference.isValid(this.mPooledByteBufferRef) || this.mInputStreamSupplier != null;
    }

    public CloseableReference<PooledByteBuffer> getByteBufferRef() {
        return CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
    }

    @Nullable
    public InputStream getInputStream() {
        if (this.mInputStreamSupplier != null) {
            return this.mInputStreamSupplier.get();
        }
        CloseableReference<PooledByteBuffer> pooledByteBufferRef = CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
        if (pooledByteBufferRef != null) {
            try {
                return new PooledByteBufferInputStream(pooledByteBufferRef.get());
            } finally {
                CloseableReference.closeSafely(pooledByteBufferRef);
            }
        }
        return null;
    }

    public InputStream getInputStreamOrThrow() {
        return (InputStream) Preconditions.checkNotNull(getInputStream());
    }

    public void setImageFormat(ImageFormat imageFormat) {
        this.mImageFormat = imageFormat;
    }

    public void setHeight(int height) {
        this.mHeight = height;
    }

    public void setWidth(int width) {
        this.mWidth = width;
    }

    public void setRotationAngle(int rotationAngle) {
        this.mRotationAngle = rotationAngle;
    }

    public void setExifOrientation(int exifOrientation) {
        this.mExifOrientation = exifOrientation;
    }

    public void setSampleSize(int sampleSize) {
        this.mSampleSize = sampleSize;
    }

    public void setStreamSize(int streamSize) {
        this.mStreamSize = streamSize;
    }

    public void setBytesRange(@Nullable BytesRange bytesRange) {
        this.mBytesRange = bytesRange;
    }

    public void setSource(@Nullable String source) {
        this.mSource = source;
    }

    @Nullable
    public String getSource() {
        return this.mSource;
    }

    public ImageFormat getImageFormat() {
        parseMetadataIfNeeded();
        return this.mImageFormat;
    }

    public int getRotationAngle() {
        parseMetadataIfNeeded();
        return this.mRotationAngle;
    }

    public int getExifOrientation() {
        parseMetadataIfNeeded();
        return this.mExifOrientation;
    }

    public int getWidth() {
        parseMetadataIfNeeded();
        return this.mWidth;
    }

    public int getHeight() {
        parseMetadataIfNeeded();
        return this.mHeight;
    }

    @Nullable
    public ColorSpace getColorSpace() {
        parseMetadataIfNeeded();
        return this.mColorSpace;
    }

    public int getSampleSize() {
        return this.mSampleSize;
    }

    @Nullable
    public BytesRange getBytesRange() {
        return this.mBytesRange;
    }

    public boolean isCompleteAt(int length) {
        if ((this.mImageFormat != DefaultImageFormats.JPEG && this.mImageFormat != DefaultImageFormats.DNG) || this.mInputStreamSupplier != null) {
            return true;
        }
        Preconditions.checkNotNull(this.mPooledByteBufferRef);
        PooledByteBuffer buf = this.mPooledByteBufferRef.get();
        return buf.read(length + (-2)) == -1 && buf.read(length + (-1)) == -39;
    }

    public int getSize() {
        if (this.mPooledByteBufferRef != null && this.mPooledByteBufferRef.get() != null) {
            return this.mPooledByteBufferRef.get().size();
        }
        return this.mStreamSize;
    }

    public String getFirstBytesAsHexString(int length) {
        CloseableReference<PooledByteBuffer> imageBuffer = getByteBufferRef();
        if (imageBuffer == null) {
            return "";
        }
        int imageSize = getSize();
        int resultSampleSize = Math.min(imageSize, length);
        byte[] bytesBuffer = new byte[resultSampleSize];
        try {
            PooledByteBuffer pooledByteBuffer = imageBuffer.get();
            if (pooledByteBuffer != null) {
                pooledByteBuffer.read(0, bytesBuffer, 0, resultSampleSize);
                imageBuffer.close();
                StringBuilder stringBuilder = new StringBuilder(bytesBuffer.length * 2);
                for (byte b : bytesBuffer) {
                    stringBuilder.append(String.format("%02X", Byte.valueOf(b)));
                }
                return stringBuilder.toString();
            }
            imageBuffer.close();
            return "";
        } catch (Throwable th) {
            imageBuffer.close();
            throw th;
        }
    }

    private void parseMetadataIfNeeded() {
        if (this.mWidth < 0 || this.mHeight < 0) {
            parseMetaData();
        }
    }

    public void parseMetaData() {
        if (!sUseCachedMetadata) {
            internalParseMetaData();
        } else {
            if (this.mHasParsedMetadata) {
                return;
            }
            internalParseMetaData();
            this.mHasParsedMetadata = true;
        }
    }

    private void internalParseMetaData() {
        Pair<Integer, Integer> dimensions;
        ImageFormat imageFormat = ImageFormatChecker.getImageFormat_WrapIOException(getInputStream());
        this.mImageFormat = imageFormat;
        if (DefaultImageFormats.isWebpFormat(imageFormat)) {
            dimensions = readWebPImageSize();
        } else {
            dimensions = readImageMetaData().getDimensions();
        }
        if (imageFormat == DefaultImageFormats.JPEG && this.mRotationAngle == -1) {
            if (dimensions != null) {
                this.mExifOrientation = JfifUtil.getOrientation(getInputStream());
                this.mRotationAngle = JfifUtil.getAutoRotateAngleFromOrientation(this.mExifOrientation);
                return;
            }
            return;
        }
        if (imageFormat == DefaultImageFormats.HEIF && this.mRotationAngle == -1) {
            this.mExifOrientation = HeifExifUtil.getOrientation(getInputStream());
            this.mRotationAngle = JfifUtil.getAutoRotateAngleFromOrientation(this.mExifOrientation);
        } else if (this.mRotationAngle == -1) {
            this.mRotationAngle = 0;
        }
    }

    @Nullable
    private Pair<Integer, Integer> readWebPImageSize() {
        InputStream stream = getInputStream();
        if (stream == null) {
            return null;
        }
        Pair<Integer, Integer> dimensions = WebpUtil.getSize(stream);
        if (dimensions != null) {
            this.mWidth = dimensions.component1().intValue();
            this.mHeight = dimensions.component2().intValue();
        }
        return dimensions;
    }

    private ImageMetaData readImageMetaData() {
        InputStream inputStream = null;
        try {
            inputStream = getInputStream();
            ImageMetaData metaData = BitmapUtil.decodeDimensionsAndColorSpace(inputStream);
            this.mColorSpace = metaData.getColorSpace();
            Pair<Integer, Integer> dimensions = metaData.getDimensions();
            if (dimensions != null) {
                this.mWidth = dimensions.component1().intValue();
                this.mHeight = dimensions.component2().intValue();
            }
            return metaData;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public void copyMetaDataFrom(EncodedImage encodedImage) {
        this.mImageFormat = encodedImage.getImageFormat();
        this.mWidth = encodedImage.getWidth();
        this.mHeight = encodedImage.getHeight();
        this.mRotationAngle = encodedImage.getRotationAngle();
        this.mExifOrientation = encodedImage.getExifOrientation();
        this.mSampleSize = encodedImage.getSampleSize();
        this.mStreamSize = encodedImage.getSize();
        this.mBytesRange = encodedImage.getBytesRange();
        this.mColorSpace = encodedImage.getColorSpace();
        this.mHasParsedMetadata = encodedImage.hasParsedMetaData();
    }

    public static boolean isMetaDataAvailable(EncodedImage encodedImage) {
        return encodedImage.mRotationAngle >= 0 && encodedImage.mWidth >= 0 && encodedImage.mHeight >= 0;
    }

    public static void closeSafely(@Nullable EncodedImage encodedImage) {
        if (encodedImage != null) {
            encodedImage.close();
        }
    }

    public static boolean isValid(@Nullable EncodedImage encodedImage) {
        return encodedImage != null && encodedImage.isValid();
    }

    @Nullable
    public synchronized SharedReference<PooledByteBuffer> getUnderlyingReferenceTestOnly() {
        SharedReference<PooledByteBuffer> underlyingReferenceTestOnly;
        if (this.mPooledByteBufferRef != null) {
            underlyingReferenceTestOnly = this.mPooledByteBufferRef.getUnderlyingReferenceTestOnly();
        } else {
            underlyingReferenceTestOnly = null;
        }
        return underlyingReferenceTestOnly;
    }

    public static void setUseCachedMetadata(boolean useCachedMetadata) {
        sUseCachedMetadata = useCachedMetadata;
    }

    protected boolean hasParsedMetaData() {
        return this.mHasParsedMetadata;
    }
}
