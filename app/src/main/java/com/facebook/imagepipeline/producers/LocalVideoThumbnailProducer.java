package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import androidx.media3.common.MimeTypes;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class LocalVideoThumbnailProducer implements Producer<CloseableReference<CloseableImage>> {
    static final String CREATED_THUMBNAIL = "createdThumbnail";
    public static final String PRODUCER_NAME = "VideoThumbnailProducer";
    private final ContentResolver mContentResolver;
    private final Executor mExecutor;

    public LocalVideoThumbnailProducer(Executor executor, ContentResolver contentResolver) {
        this.mExecutor = executor;
        this.mContentResolver = contentResolver;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, final ProducerContext producerContext) {
        final ProducerListener2 listener = producerContext.getProducerListener();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        producerContext.putOriginExtra(ImagesContract.LOCAL, MimeTypes.BASE_TYPE_VIDEO);
        final StatefulProducerRunnable<CloseableReference<CloseableImage>> cancellableProducerRunnable = new StatefulProducerRunnable<CloseableReference<CloseableImage>>(consumer, listener, producerContext, PRODUCER_NAME) { // from class: com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
            public void onSuccess(@Nullable CloseableReference<CloseableImage> result) {
                super.onSuccess(result);
                listener.onUltimateProducerReached(producerContext, LocalVideoThumbnailProducer.PRODUCER_NAME, result != null);
                producerContext.putOriginExtra(ImagesContract.LOCAL, MimeTypes.BASE_TYPE_VIDEO);
            }

            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
            protected void onFailure(Exception e) {
                super.onFailure(e);
                listener.onUltimateProducerReached(producerContext, LocalVideoThumbnailProducer.PRODUCER_NAME, false);
                producerContext.putOriginExtra(ImagesContract.LOCAL, MimeTypes.BASE_TYPE_VIDEO);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.common.executors.StatefulRunnable
            @Nullable
            public CloseableReference<CloseableImage> getResult() throws Exception {
                String path;
                Bitmap thumbnailBitmap = null;
                try {
                    path = LocalVideoThumbnailProducer.this.getLocalFilePath(imageRequest);
                } catch (IllegalArgumentException e) {
                    path = null;
                }
                if (path != null) {
                    thumbnailBitmap = ThumbnailUtils.createVideoThumbnail(path, LocalVideoThumbnailProducer.calculateKind(imageRequest));
                }
                if (thumbnailBitmap == null) {
                    thumbnailBitmap = LocalVideoThumbnailProducer.createThumbnailFromContentProvider(LocalVideoThumbnailProducer.this.mContentResolver, imageRequest.getSourceUri());
                }
                if (thumbnailBitmap == null) {
                    return null;
                }
                CloseableStaticBitmap closeableStaticBitmap = CloseableStaticBitmap.of(thumbnailBitmap, SimpleBitmapReleaser.getInstance(), ImmutableQualityInfo.FULL_QUALITY, 0);
                producerContext.putExtra("image_format", "thumbnail");
                closeableStaticBitmap.putExtras(producerContext.getExtras());
                return CloseableReference.of(closeableStaticBitmap);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            public Map<String, String> getExtraMapOnSuccess(@Nullable CloseableReference<CloseableImage> result) {
                return ImmutableMap.of(LocalVideoThumbnailProducer.CREATED_THUMBNAIL, String.valueOf(result != null));
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
            public void disposeResult(@Nullable CloseableReference<CloseableImage> result) {
                CloseableReference.closeSafely(result);
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer.2
            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                cancellableProducerRunnable.cancel();
            }
        });
        this.mExecutor.execute(cancellableProducerRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int calculateKind(ImageRequest imageRequest) {
        if (imageRequest.getPreferredWidth() > 96 || imageRequest.getPreferredHeight() > 96) {
            return 1;
        }
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public String getLocalFilePath(ImageRequest imageRequest) {
        Uri uri = imageRequest.getSourceUri();
        return UriUtil.getRealPathFromUri(this.mContentResolver, uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static Bitmap createThumbnailFromContentProvider(ContentResolver contentResolver, Uri uri) {
        int i = Build.VERSION.SDK_INT;
        try {
            ParcelFileDescriptor videoFile = contentResolver.openFileDescriptor(uri, "r");
            Preconditions.checkNotNull(videoFile);
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(videoFile.getFileDescriptor());
            return mediaMetadataRetriever.getFrameAtTime(-1L);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
