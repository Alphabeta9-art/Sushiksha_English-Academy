package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imageutils.JfifUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class LocalContentUriThumbnailFetchProducer extends LocalFetchProducer implements ThumbnailProducer<EncodedImage> {
    private static final int NO_THUMBNAIL = 0;
    public static final String PRODUCER_NAME = "LocalContentUriThumbnailFetchProducer";
    private final ContentResolver mContentResolver;
    private static final Class<?> TAG = LocalContentUriThumbnailFetchProducer.class;
    private static final String[] PROJECTION = {"_id", "_data"};
    private static final String[] THUMBNAIL_PROJECTION = {"_data"};
    private static final Rect MINI_THUMBNAIL_DIMENSIONS = new Rect(0, 0, 512, 384);
    private static final Rect MICRO_THUMBNAIL_DIMENSIONS = new Rect(0, 0, 96, 96);

    public LocalContentUriThumbnailFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver) {
        super(executor, pooledByteBufferFactory);
        this.mContentResolver = contentResolver;
    }

    @Override // com.facebook.imagepipeline.producers.ThumbnailProducer
    public boolean canProvideImageForSize(@Nullable ResizeOptions resizeOptions) {
        return ThumbnailSizeChecker.isImageBigEnough(MINI_THUMBNAIL_DIMENSIONS.width(), MINI_THUMBNAIL_DIMENSIONS.height(), resizeOptions);
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    @Nullable
    protected EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException {
        Uri uri = imageRequest.getSourceUri();
        if (UriUtil.isLocalCameraUri(uri)) {
            return getCameraImage(uri, imageRequest.getResizeOptions());
        }
        return null;
    }

    @Nullable
    private EncodedImage getCameraImage(Uri uri, @Nullable ResizeOptions resizeOptions) throws IOException {
        Cursor cursor;
        if (resizeOptions == null || (cursor = this.mContentResolver.query(uri, PROJECTION, null, null, null)) == null) {
            return null;
        }
        try {
            if (cursor.moveToFirst()) {
                int imageIdColumnIndex = cursor.getColumnIndex("_id");
                EncodedImage thumbnail = getThumbnail(resizeOptions, cursor.getLong(imageIdColumnIndex));
                if (thumbnail != null) {
                    int imageDataColumnIndex = cursor.getColumnIndex("_data");
                    if (imageDataColumnIndex >= 0) {
                        String pathname = cursor.getString(imageDataColumnIndex);
                        thumbnail.setRotationAngle(getRotationAngle(pathname));
                    }
                    return thumbnail;
                }
            }
            return null;
        } finally {
            cursor.close();
        }
    }

    @Nullable
    private EncodedImage getThumbnail(ResizeOptions resizeOptions, long imageId) throws IOException {
        Cursor thumbnailCursor;
        int thumbnailDataColumnIndex;
        int thumbnailKind = getThumbnailKind(resizeOptions);
        if (thumbnailKind == 0 || (thumbnailCursor = MediaStore.Images.Thumbnails.queryMiniThumbnail(this.mContentResolver, imageId, thumbnailKind, THUMBNAIL_PROJECTION)) == null) {
            return null;
        }
        try {
            if (thumbnailCursor.moveToFirst() && (thumbnailDataColumnIndex = thumbnailCursor.getColumnIndex("_data")) >= 0) {
                String thumbnailUri = (String) Preconditions.checkNotNull(thumbnailCursor.getString(thumbnailDataColumnIndex));
                if (new File(thumbnailUri).exists()) {
                    return getEncodedImage(new FileInputStream(thumbnailUri), getLength(thumbnailUri));
                }
            }
            return null;
        } finally {
            thumbnailCursor.close();
        }
    }

    private static int getThumbnailKind(ResizeOptions resizeOptions) {
        if (ThumbnailSizeChecker.isImageBigEnough(MICRO_THUMBNAIL_DIMENSIONS.width(), MICRO_THUMBNAIL_DIMENSIONS.height(), resizeOptions)) {
            return 3;
        }
        if (ThumbnailSizeChecker.isImageBigEnough(MINI_THUMBNAIL_DIMENSIONS.width(), MINI_THUMBNAIL_DIMENSIONS.height(), resizeOptions)) {
            return 1;
        }
        return 0;
    }

    private static int getLength(String pathname) {
        if (pathname == null) {
            return -1;
        }
        return (int) new File(pathname).length();
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    protected String getProducerName() {
        return PRODUCER_NAME;
    }

    private static int getRotationAngle(@Nullable String pathname) {
        if (pathname != null) {
            try {
                ExifInterface exif = new ExifInterface(pathname);
                return JfifUtil.getAutoRotateAngleFromOrientation(exif.getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1));
            } catch (IOException ioe) {
                FLog.e(TAG, ioe, "Unable to retrieve thumbnail rotation for %s", pathname);
                return 0;
            }
        }
        return 0;
    }
}
