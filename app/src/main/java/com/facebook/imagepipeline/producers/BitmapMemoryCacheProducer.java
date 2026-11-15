package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.HasImageMetadata;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class BitmapMemoryCacheProducer implements Producer<CloseableReference<CloseableImage>> {
    public static final String EXTRA_CACHED_VALUE_FOUND = "cached_value_found";
    private static final String ORIGIN_SUBCATEGORY = "pipe_bg";
    public static final String PRODUCER_NAME = "BitmapMemoryCacheProducer";
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;
    private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;

    public BitmapMemoryCacheProducer(MemoryCache<CacheKey, CloseableImage> memoryCache, CacheKeyFactory cacheKeyFactory, Producer<CloseableReference<CloseableImage>> inputProducer) {
        this.mMemoryCache = memoryCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = inputProducer;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        boolean zIsTracing;
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("BitmapMemoryCacheProducer#produceResults");
            }
            ProducerListener2 listener = producerContext.getProducerListener();
            listener.onProducerStart(producerContext, getProducerName());
            ImageRequest imageRequest = producerContext.getImageRequest();
            Object callerContext = producerContext.getCallerContext();
            CacheKey cacheKey = this.mCacheKeyFactory.getBitmapCacheKey(imageRequest, callerContext);
            boolean isBitmapCacheEnabledForRead = producerContext.getImageRequest().isCacheEnabled(1);
            CloseableReference<CloseableImage> cachedReference = isBitmapCacheEnabledForRead ? this.mMemoryCache.get(cacheKey) : null;
            if (cachedReference != null) {
                maybeSetExtrasFromCloseableImage(cachedReference.get(), producerContext);
                boolean isFinal = cachedReference.get().getQualityInfo().isOfFullQuality();
                if (isFinal) {
                    listener.onProducerFinishWithSuccess(producerContext, getProducerName(), listener.requiresExtraMap(producerContext, getProducerName()) ? ImmutableMap.of("cached_value_found", "true") : null);
                    listener.onUltimateProducerReached(producerContext, getProducerName(), true);
                    producerContext.putOriginExtra("memory_bitmap", getOriginSubcategory());
                    consumer.onProgressUpdate(1.0f);
                }
                consumer.onNewResult(cachedReference, BaseConsumer.simpleStatusForIsLast(isFinal));
                cachedReference.close();
                if (isFinal) {
                    if (zIsTracing) {
                        return;
                    } else {
                        return;
                    }
                }
            }
            if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE.getValue()) {
                listener.onProducerFinishWithSuccess(producerContext, getProducerName(), listener.requiresExtraMap(producerContext, getProducerName()) ? ImmutableMap.of("cached_value_found", "false") : null);
                listener.onUltimateProducerReached(producerContext, getProducerName(), false);
                producerContext.putOriginExtra("memory_bitmap", getOriginSubcategory());
                consumer.onNewResult(null, 1);
                if (zIsTracing) {
                    return;
                } else {
                    return;
                }
            }
            Consumer<CloseableReference<CloseableImage>> wrappedConsumer = wrapConsumer(consumer, cacheKey, producerContext.getImageRequest().isCacheEnabled(2));
            listener.onProducerFinishWithSuccess(producerContext, getProducerName(), listener.requiresExtraMap(producerContext, getProducerName()) ? ImmutableMap.of("cached_value_found", "false") : null);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("mInputProducer.produceResult");
            }
            this.mInputProducer.produceResults(wrappedConsumer, producerContext);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    protected Consumer<CloseableReference<CloseableImage>> wrapConsumer(Consumer<CloseableReference<CloseableImage>> consumer, final CacheKey cacheKey, final boolean isBitmapCacheEnabledForWrite) {
        return new DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>>(consumer) { // from class: com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer.1
            @Override // com.facebook.imagepipeline.producers.BaseConsumer
            public void onNewResultImpl(@Nullable CloseableReference<CloseableImage> newResult, int status) {
                CloseableReference<CloseableImage> currentCachedResult;
                try {
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.beginSection("BitmapMemoryCacheProducer#onNewResultImpl");
                    }
                    boolean isLast = isLast(status);
                    if (newResult == null) {
                        if (isLast) {
                            getConsumer().onNewResult(null, status);
                        }
                        if (FrescoSystrace.isTracing()) {
                            FrescoSystrace.endSection();
                            return;
                        }
                        return;
                    }
                    if (newResult.get().isStateful() || statusHasFlag(status, 8)) {
                        getConsumer().onNewResult(newResult, status);
                        if (FrescoSystrace.isTracing()) {
                            FrescoSystrace.endSection();
                            return;
                        }
                        return;
                    }
                    if (!isLast && (currentCachedResult = BitmapMemoryCacheProducer.this.mMemoryCache.get(cacheKey)) != null) {
                        try {
                            QualityInfo newInfo = newResult.get().getQualityInfo();
                            QualityInfo cachedInfo = currentCachedResult.get().getQualityInfo();
                            if (cachedInfo.isOfFullQuality() || cachedInfo.getQuality() >= newInfo.getQuality()) {
                                getConsumer().onNewResult(currentCachedResult, status);
                                CloseableReference.closeSafely(currentCachedResult);
                                if (FrescoSystrace.isTracing()) {
                                    FrescoSystrace.endSection();
                                    return;
                                }
                                return;
                            }
                            CloseableReference.closeSafely(currentCachedResult);
                        } catch (Throwable th) {
                            CloseableReference.closeSafely(currentCachedResult);
                            throw th;
                        }
                    }
                    CloseableReference<CloseableImage> newCachedResult = isBitmapCacheEnabledForWrite ? BitmapMemoryCacheProducer.this.mMemoryCache.cache(cacheKey, newResult) : null;
                    if (isLast) {
                        try {
                            getConsumer().onProgressUpdate(1.0f);
                        } catch (Throwable th2) {
                            CloseableReference.closeSafely(newCachedResult);
                            throw th2;
                        }
                    }
                    getConsumer().onNewResult(newCachedResult != null ? newCachedResult : newResult, status);
                    CloseableReference.closeSafely(newCachedResult);
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                } catch (Throwable th3) {
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    throw th3;
                }
            }
        };
    }

    protected String getProducerName() {
        return PRODUCER_NAME;
    }

    private static void maybeSetExtrasFromCloseableImage(HasImageMetadata imageWithMeta, ProducerContext producerContext) {
        producerContext.putExtras(imageWithMeta.getExtras());
    }

    protected String getOriginSubcategory() {
        return ORIGIN_SUBCATEGORY;
    }
}
