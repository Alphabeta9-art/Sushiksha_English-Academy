package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.cache.BoundedLinkedHashSet;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class EncodedProbeProducer implements Producer<EncodedImage> {
    public static final String PRODUCER_NAME = "EncodedProbeProducer";
    private final CacheKeyFactory mCacheKeyFactory;
    private final BufferedDiskCache mDefaultBufferedDiskCache;
    private final BoundedLinkedHashSet<CacheKey> mDiskCacheHistory;
    private final BoundedLinkedHashSet<CacheKey> mEncodedMemoryCacheHistory;
    private final Producer<EncodedImage> mInputProducer;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;

    public EncodedProbeProducer(BufferedDiskCache defaultBufferedDiskCache, BufferedDiskCache smallImageBufferedDiskCache, CacheKeyFactory cacheKeyFactory, BoundedLinkedHashSet encodedMemoryCacheHistory, BoundedLinkedHashSet diskCacheHistory, Producer<EncodedImage> inputProducer) {
        this.mDefaultBufferedDiskCache = defaultBufferedDiskCache;
        this.mSmallImageBufferedDiskCache = smallImageBufferedDiskCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mEncodedMemoryCacheHistory = encodedMemoryCacheHistory;
        this.mDiskCacheHistory = diskCacheHistory;
        this.mInputProducer = inputProducer;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0060  */
    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) throws Throwable {
        try {
            if (FrescoSystrace.isTracing()) {
                try {
                    FrescoSystrace.beginSection("EncodedProbeProducer#produceResults");
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
                Consumer<EncodedImage> consumerOfInputProducer = new ProbeConsumer(consumer, producerContext, this.mDefaultBufferedDiskCache, this.mSmallImageBufferedDiskCache, this.mCacheKeyFactory, this.mEncodedMemoryCacheHistory, this.mDiskCacheHistory);
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

    private static class ProbeConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private final CacheKeyFactory mCacheKeyFactory;
        private final BufferedDiskCache mDefaultBufferedDiskCache;
        private final BoundedLinkedHashSet<CacheKey> mDiskCacheHistory;
        private final BoundedLinkedHashSet<CacheKey> mEncodedMemoryCacheHistory;
        private final ProducerContext mProducerContext;
        private final BufferedDiskCache mSmallImageBufferedDiskCache;

        public ProbeConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext, BufferedDiskCache defaultBufferedDiskCache, BufferedDiskCache smallImageBufferedDiskCache, CacheKeyFactory cacheKeyFactory, BoundedLinkedHashSet<CacheKey> encodedMemoryCacheHistory, BoundedLinkedHashSet<CacheKey> diskCacheHistory) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mDefaultBufferedDiskCache = defaultBufferedDiskCache;
            this.mSmallImageBufferedDiskCache = smallImageBufferedDiskCache;
            this.mCacheKeyFactory = cacheKeyFactory;
            this.mEncodedMemoryCacheHistory = encodedMemoryCacheHistory;
            this.mDiskCacheHistory = diskCacheHistory;
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable EncodedImage newResult, int status) {
            boolean zIsTracing;
            try {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.beginSection("EncodedProbeProducer#onNewResultImpl");
                }
                if (!isNotLast(status) && newResult != null && !statusHasAnyFlag(status, 10) && newResult.getImageFormat() != ImageFormat.UNKNOWN) {
                    ImageRequest imageRequest = this.mProducerContext.getImageRequest();
                    CacheKey cacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, this.mProducerContext.getCallerContext());
                    this.mEncodedMemoryCacheHistory.add(cacheKey);
                    if ("memory_encoded".equals(this.mProducerContext.getExtra("origin"))) {
                        if (!this.mDiskCacheHistory.contains(cacheKey)) {
                            boolean isSmallRequest = imageRequest.getCacheChoice() == ImageRequest.CacheChoice.SMALL;
                            BufferedDiskCache preferredCache = isSmallRequest ? this.mSmallImageBufferedDiskCache : this.mDefaultBufferedDiskCache;
                            preferredCache.addKeyForAsyncProbing(cacheKey);
                            this.mDiskCacheHistory.add(cacheKey);
                        }
                    } else if ("disk".equals(this.mProducerContext.getExtra("origin"))) {
                        this.mDiskCacheHistory.add(cacheKey);
                    }
                    getConsumer().onNewResult(newResult, status);
                    if (zIsTracing) {
                        return;
                    } else {
                        return;
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
