package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class PostprocessedBitmapMemoryCacheProducer implements Producer<CloseableReference<CloseableImage>> {
    public static final String PRODUCER_NAME = "PostprocessedBitmapMemoryCacheProducer";
    static final String VALUE_FOUND = "cached_value_found";
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;
    private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;

    public PostprocessedBitmapMemoryCacheProducer(MemoryCache<CacheKey, CloseableImage> memoryCache, CacheKeyFactory cacheKeyFactory, Producer<CloseableReference<CloseableImage>> inputProducer) {
        this.mMemoryCache = memoryCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = inputProducer;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        Map<String, String> mapOf;
        Map<String, String> mapOf2;
        ProducerListener2 listener = producerContext.getProducerListener();
        ImageRequest imageRequest = producerContext.getImageRequest();
        Object callerContext = producerContext.getCallerContext();
        Postprocessor postprocessor = imageRequest.getPostprocessor();
        if (postprocessor == null || postprocessor.getCacheKey() == null) {
            this.mInputProducer.produceResults(consumer, producerContext);
            return;
        }
        listener.onProducerStart(producerContext, getProducerName());
        CacheKey cacheKey = this.mCacheKeyFactory.getPostprocessedBitmapCacheKey(imageRequest, callerContext);
        boolean isBitmapCacheEnabledForRead = producerContext.getImageRequest().isCacheEnabled(1);
        CloseableReference<CloseableImage> cachedReference = isBitmapCacheEnabledForRead ? this.mMemoryCache.get(cacheKey) : null;
        if (cachedReference != null) {
            String producerName = getProducerName();
            if (listener.requiresExtraMap(producerContext, getProducerName())) {
                mapOf2 = ImmutableMap.of("cached_value_found", "true");
            } else {
                mapOf2 = null;
            }
            listener.onProducerFinishWithSuccess(producerContext, producerName, mapOf2);
            listener.onUltimateProducerReached(producerContext, PRODUCER_NAME, true);
            producerContext.putOriginExtra("memory_bitmap", "postprocessed");
            consumer.onProgressUpdate(1.0f);
            consumer.onNewResult(cachedReference, 1);
            cachedReference.close();
            return;
        }
        boolean isRepeatedProcessor = postprocessor instanceof RepeatedPostprocessor;
        boolean isBitmapCacheEnabledForWrite = producerContext.getImageRequest().isCacheEnabled(2);
        Consumer<CloseableReference<CloseableImage>> cachedConsumer = new CachedPostprocessorConsumer(consumer, cacheKey, isRepeatedProcessor, this.mMemoryCache, isBitmapCacheEnabledForWrite);
        String producerName2 = getProducerName();
        if (listener.requiresExtraMap(producerContext, getProducerName())) {
            mapOf = ImmutableMap.of("cached_value_found", "false");
        } else {
            mapOf = null;
        }
        listener.onProducerFinishWithSuccess(producerContext, producerName2, mapOf);
        this.mInputProducer.produceResults(cachedConsumer, producerContext);
    }

    public static class CachedPostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        private final CacheKey mCacheKey;
        private final boolean mIsBitmapCacheEnabledForWrite;
        private final boolean mIsRepeatedProcessor;
        private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;

        public CachedPostprocessorConsumer(Consumer<CloseableReference<CloseableImage>> consumer, CacheKey cacheKey, boolean isRepeatedProcessor, MemoryCache<CacheKey, CloseableImage> memoryCache, boolean isBitmapCacheEnabledForWrite) {
            super(consumer);
            this.mCacheKey = cacheKey;
            this.mIsRepeatedProcessor = isRepeatedProcessor;
            this.mMemoryCache = memoryCache;
            this.mIsBitmapCacheEnabledForWrite = isBitmapCacheEnabledForWrite;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable CloseableReference<CloseableImage> newResult, int status) {
            if (newResult == null) {
                if (isLast(status)) {
                    getConsumer().onNewResult(null, status);
                }
            } else {
                if (isNotLast(status) && !this.mIsRepeatedProcessor) {
                    return;
                }
                CloseableReference<CloseableImage> newCachedResult = null;
                if (this.mIsBitmapCacheEnabledForWrite) {
                    newCachedResult = this.mMemoryCache.cache(this.mCacheKey, newResult);
                }
                try {
                    getConsumer().onProgressUpdate(1.0f);
                    getConsumer().onNewResult(newCachedResult != null ? newCachedResult : newResult, status);
                } finally {
                    CloseableReference.closeSafely(newCachedResult);
                }
            }
        }
    }

    protected String getProducerName() {
        return PRODUCER_NAME;
    }
}
