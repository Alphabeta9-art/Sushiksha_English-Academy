package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Size;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.media.MediaUtils;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class LocalThumbnailBitmapSdk29Producer implements Producer<CloseableReference<CloseableImage>> {
    static final String CREATED_THUMBNAIL = "createdThumbnail";
    public static final String PRODUCER_NAME = "LocalThumbnailBitmapSdk29Producer";
    private final ContentResolver mContentResolver;
    private final Executor mExecutor;

    public LocalThumbnailBitmapSdk29Producer(Executor executor, ContentResolver contentResolver) {
        this.mExecutor = executor;
        this.mContentResolver = contentResolver;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, final ProducerContext context) {
        final ProducerListener2 listener = context.getProducerListener();
        final ImageRequest imageRequest = context.getImageRequest();
        context.putOriginExtra(ImagesContract.LOCAL, "thumbnail_bitmap");
        final CancellationSignal cancellationSignal = new CancellationSignal();
        final StatefulProducerRunnable<CloseableReference<CloseableImage>> cancellableProducerRunnable = new StatefulProducerRunnable<CloseableReference<CloseableImage>>(consumer, listener, context, PRODUCER_NAME) { // from class: com.facebook.imagepipeline.producers.LocalThumbnailBitmapSdk29Producer.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
            public void onSuccess(@Nullable CloseableReference<CloseableImage> result) {
                super.onSuccess(result);
                listener.onUltimateProducerReached(context, LocalThumbnailBitmapSdk29Producer.PRODUCER_NAME, result != null);
                context.putOriginExtra(ImagesContract.LOCAL, "thumbnail_bitmap");
            }

            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
            protected void onFailure(Exception e) {
                super.onFailure(e);
                listener.onUltimateProducerReached(context, LocalThumbnailBitmapSdk29Producer.PRODUCER_NAME, false);
                context.putOriginExtra(ImagesContract.LOCAL, "thumbnail_bitmap");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.common.executors.StatefulRunnable
            @Nullable
            public CloseableReference<CloseableImage> getResult() throws IOException {
                String path;
                Bitmap bitmapCreateImageThumbnail;
                Bitmap thumbnailBitmap = null;
                Size size = new Size(imageRequest.getPreferredWidth(), imageRequest.getPreferredHeight());
                try {
                    path = LocalThumbnailBitmapSdk29Producer.this.getLocalFilePath(imageRequest);
                } catch (IllegalArgumentException e) {
                    path = null;
                }
                if (path != null) {
                    if (MediaUtils.isVideo(MediaUtils.extractMime(path))) {
                        bitmapCreateImageThumbnail = ThumbnailUtils.createVideoThumbnail(new File(path), size, cancellationSignal);
                    } else {
                        bitmapCreateImageThumbnail = ThumbnailUtils.createImageThumbnail(new File(path), size, cancellationSignal);
                    }
                    thumbnailBitmap = bitmapCreateImageThumbnail;
                }
                if (thumbnailBitmap == null) {
                    thumbnailBitmap = LocalThumbnailBitmapSdk29Producer.this.mContentResolver.loadThumbnail(imageRequest.getSourceUri(), size, cancellationSignal);
                }
                if (thumbnailBitmap == null) {
                    return null;
                }
                CloseableStaticBitmap closeableStaticBitmap = CloseableStaticBitmap.of(thumbnailBitmap, SimpleBitmapReleaser.getInstance(), ImmutableQualityInfo.FULL_QUALITY, 0);
                context.putExtra("image_format", "thumbnail");
                closeableStaticBitmap.putExtras(context.getExtras());
                return CloseableReference.of(closeableStaticBitmap);
            }

            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
            protected void onCancellation() {
                super.onCancellation();
                cancellationSignal.cancel();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            public Map<String, String> getExtraMapOnSuccess(@Nullable CloseableReference<CloseableImage> result) {
                return ImmutableMap.of(LocalThumbnailBitmapSdk29Producer.CREATED_THUMBNAIL, String.valueOf(result != null));
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
            public void disposeResult(@Nullable CloseableReference<CloseableImage> result) {
                CloseableReference.closeSafely(result);
            }
        };
        context.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.LocalThumbnailBitmapSdk29Producer.2
            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                cancellableProducerRunnable.cancel();
            }
        });
        this.mExecutor.execute(cancellableProducerRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public String getLocalFilePath(ImageRequest imageRequest) {
        Uri uri = imageRequest.getSourceUri();
        return UriUtil.getRealPathFromUri(this.mContentResolver, uri);
    }
}
