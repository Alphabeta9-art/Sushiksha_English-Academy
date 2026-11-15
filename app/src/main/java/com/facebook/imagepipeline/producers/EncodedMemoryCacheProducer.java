package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class EncodedMemoryCacheProducer implements Producer<EncodedImage> {
    public static final String EXTRA_CACHED_VALUE_FOUND = "cached_value_found";
    public static final String PRODUCER_NAME = "EncodedMemoryCacheProducer";
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer<EncodedImage> mInputProducer;
    private final MemoryCache<CacheKey, PooledByteBuffer> mMemoryCache;

    public EncodedMemoryCacheProducer(MemoryCache<CacheKey, PooledByteBuffer> memoryCache, CacheKeyFactory cacheKeyFactory, Producer<EncodedImage> inputProducer) {
        this.mMemoryCache = memoryCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = inputProducer;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("EncodedMemoryCacheProducer#produceResults");
            }
            ProducerListener2 listener = producerContext.getProducerListener();
            listener.onProducerStart(producerContext, PRODUCER_NAME);
            ImageRequest imageRequest = producerContext.getImageRequest();
            CacheKey cacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, producerContext.getCallerContext());
            boolean isEncodedCacheEnabledForRead = producerContext.getImageRequest().isCacheEnabled(4);
            CloseableReference<PooledByteBuffer> cachedReference = isEncodedCacheEnabledForRead ? this.mMemoryCache.get(cacheKey) : null;
            try {
                if (cachedReference != null) {
                    EncodedImage cachedEncodedImage = new EncodedImage(cachedReference);
                    try {
                        listener.onProducerFinishWithSuccess(producerContext, PRODUCER_NAME, listener.requiresExtraMap(producerContext, PRODUCER_NAME) ? ImmutableMap.of("cached_value_found", "true") : null);
                        listener.onUltimateProducerReached(producerContext, PRODUCER_NAME, true);
                        producerContext.putOriginExtra("memory_encoded");
                        consumer.onProgressUpdate(1.0f);
                        consumer.onNewResult(cachedEncodedImage, 1);
                        EncodedImage.closeSafely(cachedEncodedImage);
                        CloseableReference.closeSafely(cachedReference);
                        if (FrescoSystrace.isTracing()) {
                            FrescoSystrace.endSection();
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        EncodedImage.closeSafely(cachedEncodedImage);
                        throw th;
                    }
                }
                if (producerContext.getLowestPermittedRequestLevel().getValue() < ImageRequest.RequestLevel.ENCODED_MEMORY_CACHE.getValue()) {
                    Consumer consumerOfInputProducer = new EncodedMemoryCacheConsumer(consumer, this.mMemoryCache, cacheKey, producerContext.getImageRequest().isCacheEnabled(8), producerContext.getImagePipelineConfig().getExperiments().getIsEncodedCacheEnabled());
                    listener.onProducerFinishWithSuccess(producerContext, PRODUCER_NAME, listener.requiresExtraMap(producerContext, PRODUCER_NAME) ? ImmutableMap.of("cached_value_found", "false") : null);
                    this.mInputProducer.produceResults(consumerOfInputProducer, producerContext);
                    CloseableReference.closeSafely(cachedReference);
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                        return;
                    }
                    return;
                }
                listener.onProducerFinishWithSuccess(producerContext, PRODUCER_NAME, listener.requiresExtraMap(producerContext, PRODUCER_NAME) ? ImmutableMap.of("cached_value_found", "false") : null);
                listener.onUltimateProducerReached(producerContext, PRODUCER_NAME, false);
                producerContext.putOriginExtra("memory_encoded", "nil-result");
                consumer.onNewResult(null, 1);
                CloseableReference.closeSafely(cachedReference);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            } catch (Throwable th2) {
                CloseableReference.closeSafely(cachedReference);
                throw th2;
            }
        } catch (Throwable th3) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th3;
        }
    }

    private static class EncodedMemoryCacheConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private final boolean mEncodedCacheEnabled;
        private final boolean mIsEncodedCacheEnabledForWrite;
        private final MemoryCache<CacheKey, PooledByteBuffer> mMemoryCache;
        private final CacheKey mRequestedCacheKey;

        public EncodedMemoryCacheConsumer(Consumer<EncodedImage> consumer, MemoryCache<CacheKey, PooledByteBuffer> memoryCache, CacheKey requestedCacheKey, boolean isEncodedCacheEnabledForWrite, boolean encodedCacheEnabled) {
            super(consumer);
            this.mMemoryCache = memoryCache;
            this.mRequestedCacheKey = requestedCacheKey;
            this.mIsEncodedCacheEnabledForWrite = isEncodedCacheEnabledForWrite;
            this.mEncodedCacheEnabled = encodedCacheEnabled;
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable EncodedImage newResult, int status) {
            try {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.beginSection("EncodedMemoryCacheProducer#onNewResultImpl");
                }
                if (!isNotLast(status) && newResult != null && !statusHasAnyFlag(status, 10) && newResult.getImageFormat() != ImageFormat.UNKNOWN) {
                    CloseableReference<PooledByteBuffer> ref = newResult.getByteBufferRef();
                    if (ref != null) {
                        CloseableReference<PooledByteBuffer> cachedResult = null;
                        try {
                            if (this.mEncodedCacheEnabled && this.mIsEncodedCacheEnabledForWrite) {
                                cachedResult = this.mMemoryCache.cache(this.mRequestedCacheKey, ref);
                            }
                            CloseableReference.closeSafely(ref);
                            if (cachedResult != null) {
                                try {
                                    EncodedImage cachedEncodedImage = new EncodedImage(cachedResult);
                                    cachedEncodedImage.copyMetaDataFrom(newResult);
                                    CloseableReference.closeSafely(cachedResult);
                                    try {
                                        getConsumer().onProgressUpdate(1.0f);
                                        getConsumer().onNewResult(cachedEncodedImage, status);
                                        EncodedImage.closeSafely(cachedEncodedImage);
                                        if (FrescoSystrace.isTracing()) {
                                            FrescoSystrace.endSection();
                                            return;
                                        }
                                        return;
                                    } catch (Throwable th) {
                                        EncodedImage.closeSafely(cachedEncodedImage);
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    CloseableReference.closeSafely(cachedResult);
                                    throw th2;
                                }
                            }
                        } catch (Throwable th3) {
                            CloseableReference.closeSafely(ref);
                            throw th3;
                        }
                    }
                    getConsumer().onNewResult(newResult, status);
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                        return;
                    }
                    return;
                }
                getConsumer().onNewResult(newResult, status);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            } catch (Throwable th4) {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                throw th4;
            }
        }
    }
}
