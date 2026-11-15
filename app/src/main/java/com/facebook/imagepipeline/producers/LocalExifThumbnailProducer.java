package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.util.Pair;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imageutils.BitmapUtil;
import com.facebook.imageutils.JfifUtil;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class LocalExifThumbnailProducer implements ThumbnailProducer<EncodedImage> {
    private static final int COMMON_EXIF_THUMBNAIL_MAX_DIMENSION = 512;
    static final String CREATED_THUMBNAIL = "createdThumbnail";
    public static final String PRODUCER_NAME = "LocalExifThumbnailProducer";
    private final ContentResolver mContentResolver;
    private final Executor mExecutor;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    public LocalExifThumbnailProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver) {
        this.mExecutor = executor;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mContentResolver = contentResolver;
    }

    @Override // com.facebook.imagepipeline.producers.ThumbnailProducer
    public boolean canProvideImageForSize(@Nullable ResizeOptions resizeOptions) {
        return ThumbnailSizeChecker.isImageBigEnough(512, 512, resizeOptions);
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        ProducerListener2 listener = producerContext.getProducerListener();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        producerContext.putOriginExtra(ImagesContract.LOCAL, "exif");
        final StatefulProducerRunnable<EncodedImage> cancellableProducerRunnable = new StatefulProducerRunnable<EncodedImage>(consumer, listener, producerContext, PRODUCER_NAME) { // from class: com.facebook.imagepipeline.producers.LocalExifThumbnailProducer.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.common.executors.StatefulRunnable
            @Nullable
            public EncodedImage getResult() throws Exception {
                Uri sourceUri = imageRequest.getSourceUri();
                ExifInterface exifInterface = LocalExifThumbnailProducer.this.getExifInterface(sourceUri);
                if (exifInterface == null || !exifInterface.hasThumbnail()) {
                    return null;
                }
                byte[] bytes = (byte[]) Preconditions.checkNotNull(exifInterface.getThumbnail());
                PooledByteBuffer pooledByteBuffer = LocalExifThumbnailProducer.this.mPooledByteBufferFactory.newByteBuffer(bytes);
                return LocalExifThumbnailProducer.this.buildEncodedImage(pooledByteBuffer, exifInterface);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
            public void disposeResult(@Nullable EncodedImage result) {
                EncodedImage.closeSafely(result);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            public Map<String, String> getExtraMapOnSuccess(@Nullable EncodedImage result) {
                return ImmutableMap.of(LocalExifThumbnailProducer.CREATED_THUMBNAIL, Boolean.toString(result != null));
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.LocalExifThumbnailProducer.2
            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                cancellableProducerRunnable.cancel();
            }
        });
        this.mExecutor.execute(cancellableProducerRunnable);
    }

    @Nullable
    ExifInterface getExifInterface(Uri uri) {
        String realPath = UriUtil.getRealPathFromUri(this.mContentResolver, uri);
        if (realPath == null) {
            return null;
        }
        try {
            if (canReadAsFile(realPath)) {
                return new ExifInterface(realPath);
            }
            AssetFileDescriptor assetFileDescriptor = UriUtil.getAssetFileDescriptor(this.mContentResolver, uri);
            if (assetFileDescriptor != null) {
                int i = Build.VERSION.SDK_INT;
                ExifInterface exifInterface = new Api24Utils().getExifInterface(assetFileDescriptor.getFileDescriptor());
                assetFileDescriptor.close();
                return exifInterface;
            }
            return null;
        } catch (IOException e) {
        } catch (StackOverflowError e2) {
            FLog.e((Class<?>) LocalExifThumbnailProducer.class, "StackOverflowError in ExifInterface constructor");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EncodedImage buildEncodedImage(PooledByteBuffer imageBytes, ExifInterface exifInterface) {
        Pair<Integer, Integer> dimensions = BitmapUtil.decodeDimensions(new PooledByteBufferInputStream(imageBytes));
        int rotationAngle = getRotationAngle(exifInterface);
        int width = dimensions != null ? ((Integer) dimensions.first).intValue() : -1;
        int height = dimensions != null ? ((Integer) dimensions.second).intValue() : -1;
        CloseableReference<PooledByteBuffer> closeableByteBuffer = CloseableReference.of(imageBytes);
        try {
            EncodedImage encodedImage = new EncodedImage(closeableByteBuffer);
            CloseableReference.closeSafely(closeableByteBuffer);
            encodedImage.setImageFormat(DefaultImageFormats.JPEG);
            encodedImage.setRotationAngle(rotationAngle);
            encodedImage.setWidth(width);
            encodedImage.setHeight(height);
            return encodedImage;
        } catch (Throwable th) {
            CloseableReference.closeSafely(closeableByteBuffer);
            throw th;
        }
    }

    private int getRotationAngle(ExifInterface exifInterface) {
        String s = (String) Preconditions.checkNotNull(exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION));
        return JfifUtil.getAutoRotateAngleFromOrientation(Integer.parseInt(s));
    }

    boolean canReadAsFile(String realPath) throws IOException {
        if (realPath == null) {
            return false;
        }
        File file = new File(realPath);
        return file.exists() && file.canRead();
    }

    private class Api24Utils {
        private Api24Utils() {
        }

        @Nullable
        ExifInterface getExifInterface(FileDescriptor fileDescriptor) throws IOException {
            int i = Build.VERSION.SDK_INT;
            return new ExifInterface(fileDescriptor);
        }
    }
}
