package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class DiskCacheWriteProducer implements Producer<EncodedImage> {
    static final String PRODUCER_NAME = "DiskCacheWriteProducer";
    private final CacheKeyFactory mCacheKeyFactory;
    private final BufferedDiskCache mDefaultBufferedDiskCache;

    @Nullable
    private final Map<String, BufferedDiskCache> mDynamicBufferedDiskCaches;
    private final Producer<EncodedImage> mInputProducer;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;

    public DiskCacheWriteProducer(BufferedDiskCache defaultBufferedDiskCache, BufferedDiskCache smallImageBufferedDiskCache, @Nullable Map<String, BufferedDiskCache> dynamicBufferedDiskCaches, CacheKeyFactory cacheKeyFactory, Producer<EncodedImage> inputProducer) {
        this.mDefaultBufferedDiskCache = defaultBufferedDiskCache;
        this.mSmallImageBufferedDiskCache = smallImageBufferedDiskCache;
        this.mDynamicBufferedDiskCaches = dynamicBufferedDiskCaches;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = inputProducer;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        maybeStartInputProducer(consumer, producerContext);
    }

    private void maybeStartInputProducer(Consumer<EncodedImage> consumerOfDiskCacheWriteProducer, ProducerContext producerContext) {
        ProducerContext producerContext2;
        Consumer<EncodedImage> consumer;
        if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest.RequestLevel.DISK_CACHE.getValue()) {
            producerContext.putOriginExtra("disk", "nil-result_write");
            consumerOfDiskCacheWriteProducer.onNewResult(null, 1);
            return;
        }
        boolean isDiskCacheEnabledForWrite = producerContext.getImageRequest().isCacheEnabled(32);
        if (isDiskCacheEnabledForWrite) {
            producerContext2 = producerContext;
            consumer = new DiskCacheWriteConsumer(consumerOfDiskCacheWriteProducer, producerContext2, this.mDefaultBufferedDiskCache, this.mSmallImageBufferedDiskCache, this.mDynamicBufferedDiskCaches, this.mCacheKeyFactory);
        } else {
            producerContext2 = producerContext;
            consumer = consumerOfDiskCacheWriteProducer;
        }
        this.mInputProducer.produceResults(consumer, producerContext2);
    }

    private static class DiskCacheWriteConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private final CacheKeyFactory mCacheKeyFactory;
        private final BufferedDiskCache mDefaultBufferedDiskCache;

        @Nullable
        private final Map<String, BufferedDiskCache> mDynamicBufferedDiskCaches;
        private final ProducerContext mProducerContext;
        private final BufferedDiskCache mSmallImageBufferedDiskCache;

        private DiskCacheWriteConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext, BufferedDiskCache defaultBufferedDiskCache, BufferedDiskCache smallImageBufferedDiskCache, @Nullable Map<String, BufferedDiskCache> dynamicBufferedDiskCaches, CacheKeyFactory cacheKeyFactory) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mDefaultBufferedDiskCache = defaultBufferedDiskCache;
            this.mSmallImageBufferedDiskCache = smallImageBufferedDiskCache;
            this.mDynamicBufferedDiskCaches = dynamicBufferedDiskCaches;
            this.mCacheKeyFactory = cacheKeyFactory;
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable EncodedImage newResult, int status) {
            this.mProducerContext.getProducerListener().onProducerStart(this.mProducerContext, DiskCacheWriteProducer.PRODUCER_NAME);
            if (isNotLast(status) || newResult == null || statusHasAnyFlag(status, 10) || newResult.getImageFormat() == ImageFormat.UNKNOWN) {
                this.mProducerContext.getProducerListener().onProducerFinishWithSuccess(this.mProducerContext, DiskCacheWriteProducer.PRODUCER_NAME, null);
                getConsumer().onNewResult(newResult, status);
                return;
            }
            ImageRequest imageRequest = this.mProducerContext.getImageRequest();
            CacheKey cacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, this.mProducerContext.getCallerContext());
            BufferedDiskCache bufferedDiskCache = DiskCacheDecision.chooseDiskCacheForRequest(imageRequest, this.mSmallImageBufferedDiskCache, this.mDefaultBufferedDiskCache, this.mDynamicBufferedDiskCaches);
            if (bufferedDiskCache == null) {
                this.mProducerContext.getProducerListener().onProducerFinishWithFailure(this.mProducerContext, DiskCacheWriteProducer.PRODUCER_NAME, new DiskCacheDecision.DiskCacheDecisionNoDiskCacheChosenException("Got no disk cache for CacheChoice: " + Integer.valueOf(imageRequest.getCacheChoice().ordinal()).toString()), null);
                getConsumer().onNewResult(newResult, status);
            } else {
                bufferedDiskCache.put(cacheKey, newResult);
                this.mProducerContext.getProducerListener().onProducerFinishWithSuccess(this.mProducerContext, DiskCacheWriteProducer.PRODUCER_NAME, null);
                getConsumer().onNewResult(newResult, status);
            }
        }
    }
}
