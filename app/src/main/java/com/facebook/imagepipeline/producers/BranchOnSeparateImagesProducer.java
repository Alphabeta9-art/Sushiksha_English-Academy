package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class BranchOnSeparateImagesProducer implements Producer<EncodedImage> {
    private final Producer<EncodedImage> mInputProducer1;
    private final Producer<EncodedImage> mInputProducer2;

    public BranchOnSeparateImagesProducer(Producer<EncodedImage> inputProducer1, Producer<EncodedImage> inputProducer2) {
        this.mInputProducer1 = inputProducer1;
        this.mInputProducer2 = inputProducer2;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext context) {
        OnFirstImageConsumer onFirstImageConsumer = new OnFirstImageConsumer(consumer, context);
        this.mInputProducer1.produceResults(onFirstImageConsumer, context);
    }

    private class OnFirstImageConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private ProducerContext mProducerContext;

        private OnFirstImageConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer);
            this.mProducerContext = producerContext;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable EncodedImage newResult, int status) {
            ImageRequest request = this.mProducerContext.getImageRequest();
            boolean isLast = isLast(status);
            boolean isGoodEnough = ThumbnailSizeChecker.isImageBigEnough(newResult, request.getResizeOptions());
            if (newResult != null && (isGoodEnough || request.getLocalThumbnailPreviewsEnabled())) {
                if (isLast && isGoodEnough) {
                    getConsumer().onNewResult(newResult, status);
                } else {
                    int alteredStatus = turnOffStatusFlag(status, 1);
                    getConsumer().onNewResult(newResult, alteredStatus);
                }
            }
            if (isLast && !isGoodEnough && !request.getLoadThumbnailOnlyForAndroidSdkAboveQ()) {
                EncodedImage.closeSafely(newResult);
                BranchOnSeparateImagesProducer.this.mInputProducer2.produceResults(getConsumer(), this.mProducerContext);
            }
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        protected void onFailureImpl(@Nullable Throwable t) {
            BranchOnSeparateImagesProducer.this.mInputProducer2.produceResults(getConsumer(), this.mProducerContext);
        }
    }
}
