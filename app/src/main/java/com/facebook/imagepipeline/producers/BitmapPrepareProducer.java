package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class BitmapPrepareProducer implements Producer<CloseableReference<CloseableImage>> {
    public static final String PRODUCER_NAME = "BitmapPrepareProducer";
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;
    private final int mMaxBitmapSizeBytes;
    private final int mMinBitmapSizeBytes;
    private final boolean mPreparePrefetch;

    public BitmapPrepareProducer(Producer<CloseableReference<CloseableImage>> inputProducer, int minBitmapSizeBytes, int maxBitmapSizeBytes, boolean preparePrefetch) {
        Preconditions.checkArgument(Boolean.valueOf(minBitmapSizeBytes <= maxBitmapSizeBytes));
        this.mInputProducer = (Producer) Preconditions.checkNotNull(inputProducer);
        this.mMinBitmapSizeBytes = minBitmapSizeBytes;
        this.mMaxBitmapSizeBytes = maxBitmapSizeBytes;
        this.mPreparePrefetch = preparePrefetch;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        if (producerContext.isPrefetch() && !this.mPreparePrefetch) {
            this.mInputProducer.produceResults(consumer, producerContext);
        } else {
            this.mInputProducer.produceResults(new BitmapPrepareConsumer(consumer, this.mMinBitmapSizeBytes, this.mMaxBitmapSizeBytes), producerContext);
        }
    }

    private static class BitmapPrepareConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        private final int mMaxBitmapSizeBytes;
        private final int mMinBitmapSizeBytes;

        BitmapPrepareConsumer(Consumer<CloseableReference<CloseableImage>> consumer, int minBitmapSizeBytes, int maxBitmapSizeBytes) {
            super(consumer);
            this.mMinBitmapSizeBytes = minBitmapSizeBytes;
            this.mMaxBitmapSizeBytes = maxBitmapSizeBytes;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable CloseableReference<CloseableImage> newResult, int status) {
            internalPrepareBitmap(newResult);
            getConsumer().onNewResult(newResult, status);
        }

        private void internalPrepareBitmap(@Nullable CloseableReference<CloseableImage> newResult) {
            CloseableImage closeableImage;
            int bitmapByteCount;
            if (newResult != null && newResult.isValid() && (closeableImage = newResult.get()) != null && !closeableImage.isClosed() && (closeableImage instanceof CloseableStaticBitmap)) {
                CloseableStaticBitmap staticBitmap = (CloseableStaticBitmap) closeableImage;
                Bitmap bitmap = staticBitmap.getUnderlyingBitmap();
                if (bitmap == null || (bitmapByteCount = bitmap.getRowBytes() * bitmap.getHeight()) < this.mMinBitmapSizeBytes || bitmapByteCount > this.mMaxBitmapSizeBytes) {
                    return;
                }
                bitmap.prepareToDraw();
            }
        }
    }
}
