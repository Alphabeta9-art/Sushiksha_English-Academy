package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.BoundedLinkedHashSet;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class BitmapProbeProducer implements Producer<CloseableReference<CloseableImage>> {
    public static final String PRODUCER_NAME = "BitmapProbeProducer";
    private final CacheKeyFactory mCacheKeyFactory;
    private final BufferedDiskCache mDefaultBufferedDiskCache;
    private final BoundedLinkedHashSet<CacheKey> mDiskCacheHistory;
    private final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    private final BoundedLinkedHashSet<CacheKey> mEncodedMemoryCacheHistory;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;

    public BitmapProbeProducer(MemoryCache<CacheKey, PooledByteBuffer> encodedMemoryCache, BufferedDiskCache defaultBufferedDiskCache, BufferedDiskCache smallImageBufferedDiskCache, CacheKeyFactory cacheKeyFactory, BoundedLinkedHashSet<CacheKey> encodedMemoryCacheHistory, BoundedLinkedHashSet<CacheKey> diskCacheHistory, Producer<CloseableReference<CloseableImage>> inputProducer) {
        this.mEncodedMemoryCache = encodedMemoryCache;
        this.mDefaultBufferedDiskCache = defaultBufferedDiskCache;
        this.mSmallImageBufferedDiskCache = smallImageBufferedDiskCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mEncodedMemoryCacheHistory = encodedMemoryCacheHistory;
        this.mDiskCacheHistory = diskCacheHistory;
        this.mInputProducer = inputProducer;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0062  */
    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) throws Throwable {
        try {
            if (FrescoSystrace.isTracing()) {
                try {
                    FrescoSystrace.beginSection("BitmapProbeProducer#produceResults");
                } catch (Throwable th) {
                    th = th;
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    throw th;
                }
            }
            ProducerListener2 listener = producerContext.getProducerListener();
            listener.onProducerStart(producerContext, getProducerName());
            try {
                Consumer<CloseableReference<CloseableImage>> consumerOfInputProducer = new ProbeConsumer(consumer, producerContext, this.mEncodedMemoryCache, this.mDefaultBufferedDiskCache, this.mSmallImageBufferedDiskCache, this.mCacheKeyFactory, this.mEncodedMemoryCacheHistory, this.mDiskCacheHistory);
                listener.onProducerFinishWithSuccess(producerContext, PRODUCER_NAME, null);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.beginSection("mInputProducer.produceResult");
                }
                this.mInputProducer.produceResults(consumerOfInputProducer, producerContext);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            } catch (Throwable th2) {
                th = th2;
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private static class ProbeConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        private final CacheKeyFactory mCacheKeyFactory;
        private final BufferedDiskCache mDefaultBufferedDiskCache;
        private final BoundedLinkedHashSet<CacheKey> mDiskCacheHistory;
        private final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
        private final BoundedLinkedHashSet<CacheKey> mEncodedMemoryCacheHistory;
        private final ProducerContext mProducerContext;
        private final BufferedDiskCache mSmallImageBufferedDiskCache;

        public ProbeConsumer(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext, MemoryCache<CacheKey, PooledByteBuffer> encodedMemoryCache, BufferedDiskCache defaultBufferedDiskCache, BufferedDiskCache smallImageBufferedDiskCache, CacheKeyFactory cacheKeyFactory, BoundedLinkedHashSet<CacheKey> encodedMemoryCacheHistory, BoundedLinkedHashSet<CacheKey> diskCacheHistory) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mEncodedMemoryCache = encodedMemoryCache;
            this.mDefaultBufferedDiskCache = defaultBufferedDiskCache;
            this.mSmallImageBufferedDiskCache = smallImageBufferedDiskCache;
            this.mCacheKeyFactory = cacheKeyFactory;
            this.mEncodedMemoryCacheHistory = encodedMemoryCacheHistory;
            this.mDiskCacheHistory = diskCacheHistory;
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable CloseableReference<CloseableImage> newResult, int status) {
            boolean zIsTracing;
            try {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.beginSection("BitmapProbeProducer#onNewResultImpl");
                }
                if (isNotLast(status) || newResult == null || statusHasAnyFlag(status, 8)) {
                    getConsumer().onNewResult(newResult, status);
                    if (zIsTracing) {
                        return;
                    } else {
                        return;
                    }
                }
                ImageRequest imageRequest = this.mProducerContext.getImageRequest();
                CacheKey cacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, this.mProducerContext.getCallerContext());
                String producerContextExtra = (String) this.mProducerContext.getExtra("origin");
                if (producerContextExtra != null && producerContextExtra.equals("memory_bitmap")) {
                    if (this.mProducerContext.getImagePipelineConfig().getExperiments().getIsEncodedMemoryCacheProbingEnabled() && !this.mEncodedMemoryCacheHistory.contains(cacheKey)) {
                        this.mEncodedMemoryCache.probe(cacheKey);
                        this.mEncodedMemoryCacheHistory.add(cacheKey);
                    }
                    if (this.mProducerContext.getImagePipelineConfig().getExperiments().getIsDiskCacheProbingEnabled() && !this.mDiskCacheHistory.contains(cacheKey)) {
                        boolean isSmallRequest = imageRequest.getCacheChoice() == ImageRequest.CacheChoice.SMALL;
                        BufferedDiskCache preferredCache = isSmallRequest ? this.mSmallImageBufferedDiskCache : this.mDefaultBufferedDiskCache;
                        preferredCache.addKeyForAsyncProbing(cacheKey);
                        this.mDiskCacheHistory.add(cacheKey);
                    }
                }
                getConsumer().onNewResult(newResult, status);
            } finally {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            }
        }
    }

    protected String getProducerName() {
        return PRODUCER_NAME;
    }
}
