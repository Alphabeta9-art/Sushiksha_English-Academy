package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.os.Build;
import androidx.core.util.Pools;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.DecodeBufferHelper;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.streams.TailAppendingInputStream;
import com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imagepipeline.memory.DummyBitmapPool;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public abstract class DefaultDecoder implements PlatformDecoder {
    private boolean mAvoidPoolGet;
    private boolean mAvoidPoolRelease;
    private final BitmapPool mBitmapPool;
    final Pools.Pool<ByteBuffer> mDecodeBuffers;

    @Nullable
    private final PreverificationHelper mPreverificationHelper;
    private static final Class<?> TAG = DefaultDecoder.class;
    private static final byte[] EOI_TAIL = {-1, -39};

    public abstract int getBitmapSize(int i, int i2, BitmapFactory.Options options);

    public DefaultDecoder(BitmapPool bitmapPool, Pools.Pool<ByteBuffer> decodeBuffers, PlatformDecoderOptions platformDecoderOptions) {
        this.mPreverificationHelper = Build.VERSION.SDK_INT >= 26 ? new PreverificationHelper() : null;
        this.mBitmapPool = bitmapPool;
        if (bitmapPool instanceof DummyBitmapPool) {
            this.mAvoidPoolGet = platformDecoderOptions.getAvoidPoolGet();
            this.mAvoidPoolRelease = platformDecoderOptions.getAvoidPoolRelease();
        }
        this.mDecodeBuffers = decodeBuffers;
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    @Nullable
    public CloseableReference<Bitmap> decodeFromEncodedImage(EncodedImage encodedImage, Bitmap.Config bitmapConfig, @Nullable Rect regionToDecode) {
        return decodeFromEncodedImageWithColorSpace(encodedImage, bitmapConfig, regionToDecode, null);
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    @Nullable
    public CloseableReference<Bitmap> decodeJPEGFromEncodedImage(EncodedImage encodedImage, Bitmap.Config bitmapConfig, @Nullable Rect regionToDecode, int length) {
        return decodeJPEGFromEncodedImageWithColorSpace(encodedImage, bitmapConfig, regionToDecode, length, null);
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    @Nullable
    public CloseableReference<Bitmap> decodeFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config bitmapConfig, @Nullable Rect regionToDecode, @Nullable ColorSpace colorSpace) {
        BitmapFactory.Options options = getDecodeOptionsForStream(encodedImage, bitmapConfig, this.mAvoidPoolGet);
        boolean retryOnFail = options.inPreferredConfig != Bitmap.Config.ARGB_8888;
        try {
            InputStream s = (InputStream) Preconditions.checkNotNull(encodedImage.getInputStream());
            return decodeFromStream(s, options, regionToDecode, colorSpace);
        } catch (RuntimeException re) {
            if (retryOnFail) {
                return decodeFromEncodedImageWithColorSpace(encodedImage, Bitmap.Config.ARGB_8888, regionToDecode, colorSpace);
            }
            throw re;
        }
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    @Nullable
    public CloseableReference<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config bitmapConfig, @Nullable Rect regionToDecode, int length, @Nullable ColorSpace colorSpace) throws IOException {
        InputStream jpegDataStream;
        boolean isJpegComplete = encodedImage.isCompleteAt(length);
        BitmapFactory.Options options = getDecodeOptionsForStream(encodedImage, bitmapConfig, this.mAvoidPoolGet);
        InputStream jpegDataStream2 = encodedImage.getInputStream();
        Preconditions.checkNotNull(jpegDataStream2);
        if (encodedImage.getSize() > length) {
            jpegDataStream2 = new LimitedInputStream(jpegDataStream2, length);
        }
        if (isJpegComplete) {
            jpegDataStream = jpegDataStream2;
        } else {
            jpegDataStream = new TailAppendingInputStream(jpegDataStream2, EOI_TAIL);
        }
        boolean retryOnFail = options.inPreferredConfig != Bitmap.Config.ARGB_8888;
        try {
            try {
                CloseableReference<Bitmap> closeableReferenceDecodeFromStream = decodeFromStream(jpegDataStream, options, regionToDecode, colorSpace);
                try {
                    jpegDataStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return closeableReferenceDecodeFromStream;
            } catch (Throwable e2) {
                try {
                    jpegDataStream.close();
                    throw e2;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    throw e2;
                }
            }
        } catch (RuntimeException re) {
            if (!retryOnFail) {
                throw re;
            }
            CloseableReference<Bitmap> closeableReferenceDecodeJPEGFromEncodedImageWithColorSpace = decodeJPEGFromEncodedImageWithColorSpace(encodedImage, Bitmap.Config.ARGB_8888, regionToDecode, length, colorSpace);
            try {
                jpegDataStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            return closeableReferenceDecodeJPEGFromEncodedImageWithColorSpace;
        }
    }

    @Nullable
    protected CloseableReference<Bitmap> decodeStaticImageFromStream(InputStream inputStream, BitmapFactory.Options options, @Nullable Rect regionToDecode) {
        return decodeFromStream(inputStream, options, regionToDecode, null);
    }

    @Nullable
    private CloseableReference<Bitmap> decodeFromStream(InputStream inputStream, BitmapFactory.Options options, @Nullable Rect regionToDecode, @Nullable ColorSpace colorSpace) {
        Preconditions.checkNotNull(inputStream);
        int targetWidth = options.outWidth;
        int targetHeight = options.outHeight;
        if (regionToDecode != null) {
            targetWidth = regionToDecode.width() / options.inSampleSize;
            targetHeight = regionToDecode.height() / options.inSampleSize;
        }
        Bitmap bitmapToReuse = null;
        boolean shouldUseHardwareBitmapConfig = false;
        if (Build.VERSION.SDK_INT >= 26) {
            shouldUseHardwareBitmapConfig = this.mPreverificationHelper != null && this.mPreverificationHelper.shouldUseHardwareBitmapConfig(options.inPreferredConfig);
        }
        if (regionToDecode == null && shouldUseHardwareBitmapConfig) {
            options.inMutable = false;
        } else {
            if (regionToDecode != null && shouldUseHardwareBitmapConfig) {
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            }
            if (!this.mAvoidPoolGet) {
                int sizeInBytes = getBitmapSize(targetWidth, targetHeight, options);
                bitmapToReuse = this.mBitmapPool.get(sizeInBytes);
                if (bitmapToReuse == null) {
                    throw new NullPointerException("BitmapPool.get returned null");
                }
            }
        }
        options.inBitmap = bitmapToReuse;
        if (Build.VERSION.SDK_INT >= 26) {
            options.inPreferredColorSpace = colorSpace == null ? ColorSpace.get(ColorSpace.Named.SRGB) : colorSpace;
        }
        Bitmap decodedBitmap = null;
        ByteBuffer byteBuffer = this.mDecodeBuffers.acquire();
        if (byteBuffer == null) {
            byteBuffer = ByteBuffer.allocate(DecodeBufferHelper.getRecommendedDecodeBufferSize());
        }
        try {
            try {
                options.inTempStorage = byteBuffer.array();
                int i = Build.VERSION.SDK_INT;
                if (regionToDecode != null && bitmapToReuse != null && options.inPreferredConfig != null) {
                    BitmapRegionDecoder bitmapRegionDecoder = null;
                    try {
                        try {
                            bitmapToReuse.reconfigure(targetWidth, targetHeight, options.inPreferredConfig);
                            bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, true);
                            if (bitmapRegionDecoder != null) {
                                decodedBitmap = bitmapRegionDecoder.decodeRegion(regionToDecode, options);
                            }
                            if (bitmapRegionDecoder != null) {
                                bitmapRegionDecoder.recycle();
                            }
                        } catch (IOException e) {
                            FLog.e(TAG, "Could not decode region %s, decoding full bitmap instead.", regionToDecode);
                            if (bitmapRegionDecoder != null) {
                            }
                        }
                    } catch (Throwable th) {
                        if (bitmapRegionDecoder != null) {
                            bitmapRegionDecoder.recycle();
                        }
                        throw th;
                    }
                }
                if (decodedBitmap == null) {
                    decodedBitmap = BitmapFactory.decodeStream(inputStream, null, options);
                }
                this.mDecodeBuffers.release(byteBuffer);
                if (bitmapToReuse != null && bitmapToReuse != decodedBitmap) {
                    this.mBitmapPool.release(bitmapToReuse);
                    if (decodedBitmap != null) {
                        decodedBitmap.recycle();
                    }
                    throw new IllegalStateException();
                }
                if (this.mAvoidPoolRelease) {
                    return CloseableReference.of(decodedBitmap, NoOpResourceReleaser.INSTANCE);
                }
                return CloseableReference.of(decodedBitmap, this.mBitmapPool);
            } catch (IllegalArgumentException e2) {
                if (bitmapToReuse != null) {
                    this.mBitmapPool.release(bitmapToReuse);
                }
                try {
                    inputStream.reset();
                    Bitmap naiveDecodedBitmap = BitmapFactory.decodeStream(inputStream);
                    if (naiveDecodedBitmap == null) {
                        throw e2;
                    }
                    CloseableReference<Bitmap> closeableReferenceOf = CloseableReference.of(naiveDecodedBitmap, SimpleBitmapReleaser.getInstance());
                    this.mDecodeBuffers.release(byteBuffer);
                    return closeableReferenceOf;
                } catch (IOException e3) {
                    throw e2;
                }
            } catch (RuntimeException re) {
                if (bitmapToReuse != null) {
                    this.mBitmapPool.release(bitmapToReuse);
                }
                throw re;
            }
        } catch (Throwable e4) {
            this.mDecodeBuffers.release(byteBuffer);
            throw e4;
        }
    }

    private static BitmapFactory.Options getDecodeOptionsForStream(EncodedImage encodedImage, Bitmap.Config bitmapConfig, boolean skipDecoding) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = encodedImage.getSampleSize();
        options.inJustDecodeBounds = true;
        options.inDither = true;
        boolean isHardwareBitmap = Build.VERSION.SDK_INT >= 26 && bitmapConfig == Bitmap.Config.HARDWARE;
        if (!isHardwareBitmap) {
            options.inPreferredConfig = bitmapConfig;
        }
        options.inMutable = true;
        if (!skipDecoding) {
            BitmapFactory.decodeStream(encodedImage.getInputStream(), null, options);
            if (options.outWidth == -1 || options.outHeight == -1) {
                throw new IllegalArgumentException();
            }
        }
        if (isHardwareBitmap) {
            options.inPreferredConfig = bitmapConfig;
        }
        options.inJustDecodeBounds = false;
        return options;
    }

    private static final class NoOpResourceReleaser implements ResourceReleaser<Bitmap> {
        private static final NoOpResourceReleaser INSTANCE = new NoOpResourceReleaser();

        private NoOpResourceReleaser() {
        }

        @Override // com.facebook.common.references.ResourceReleaser
        public void release(Bitmap value) {
        }
    }
}
