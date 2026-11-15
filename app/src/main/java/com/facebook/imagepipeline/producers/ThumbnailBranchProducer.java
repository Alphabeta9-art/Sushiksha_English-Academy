package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class ThumbnailBranchProducer implements Producer<EncodedImage> {
    private final ThumbnailProducer<EncodedImage>[] mThumbnailProducers;

    public ThumbnailBranchProducer(ThumbnailProducer<EncodedImage>... thumbnailProducers) {
        this.mThumbnailProducers = (ThumbnailProducer[]) Preconditions.checkNotNull(thumbnailProducers);
        Preconditions.checkElementIndex(0, this.mThumbnailProducers.length);
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext context) {
        if (context.getImageRequest().getResizeOptions() == null) {
            consumer.onNewResult(null, 1);
            return;
        }
        boolean requested = produceResultsFromThumbnailProducer(0, consumer, context);
        if (!requested) {
            consumer.onNewResult(null, 1);
        }
    }

    private class ThumbnailConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private final ProducerContext mProducerContext;
        private final int mProducerIndex;

        @Nullable
        private final ResizeOptions mResizeOptions;

        public ThumbnailConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext, int producerIndex) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mProducerIndex = producerIndex;
            this.mResizeOptions = this.mProducerContext.getImageRequest().getResizeOptions();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable EncodedImage newResult, int status) {
            if (newResult != null && (isNotLast(status) || ThumbnailSizeChecker.isImageBigEnough(newResult, this.mResizeOptions))) {
                getConsumer().onNewResult(newResult, status);
                return;
            }
            if (isLast(status)) {
                EncodedImage.closeSafely(newResult);
                boolean fallback = ThumbnailBranchProducer.this.produceResultsFromThumbnailProducer(this.mProducerIndex + 1, getConsumer(), this.mProducerContext);
                if (!fallback) {
                    getConsumer().onNewResult(null, 1);
                }
            }
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        protected void onFailureImpl(Throwable t) {
            boolean fallback = ThumbnailBranchProducer.this.produceResultsFromThumbnailProducer(this.mProducerIndex + 1, getConsumer(), this.mProducerContext);
            if (!fallback) {
                getConsumer().onFailure(t);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean produceResultsFromThumbnailProducer(int startIndex, Consumer<EncodedImage> consumer, ProducerContext context) {
        int producerIndex = findFirstProducerForSize(startIndex, context.getImageRequest().getResizeOptions());
        if (producerIndex == -1) {
            return false;
        }
        this.mThumbnailProducers[producerIndex].produceResults(new ThumbnailConsumer(consumer, context, producerIndex), context);
        return true;
    }

    private int findFirstProducerForSize(int startIndex, @Nullable ResizeOptions resizeOptions) {
        for (int i = startIndex; i < this.mThumbnailProducers.length; i++) {
            if (this.mThumbnailProducers[i].canProvideImageForSize(resizeOptions)) {
                return i;
            }
        }
        return -1;
    }
}
