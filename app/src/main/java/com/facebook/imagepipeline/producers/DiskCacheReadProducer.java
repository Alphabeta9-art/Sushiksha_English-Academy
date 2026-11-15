package com.facebook.imagepipeline.producers;

import bolts.Continuation;
import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class DiskCacheReadProducer implements Producer<EncodedImage> {
    public static final String ENCODED_IMAGE_SIZE = "encodedImageSize";
    public static final String EXTRA_CACHED_VALUE_FOUND = "cached_value_found";
    public static final String PRODUCER_NAME = "DiskCacheProducer";
    private final CacheKeyFactory mCacheKeyFactory;
    private final BufferedDiskCache mDefaultBufferedDiskCache;

    @Nullable
    private final Map<String, BufferedDiskCache> mDynamicBufferedDiskCaches;
    private final Producer<EncodedImage> mInputProducer;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;

    public DiskCacheReadProducer(BufferedDiskCache defaultBufferedDiskCache, BufferedDiskCache smallImageBufferedDiskCache, @Nullable Map<String, BufferedDiskCache> dynamicBufferedDiskCaches, CacheKeyFactory cacheKeyFactory, Producer<EncodedImage> inputProducer) {
        this.mDefaultBufferedDiskCache = defaultBufferedDiskCache;
        this.mSmallImageBufferedDiskCache = smallImageBufferedDiskCache;
        this.mDynamicBufferedDiskCaches = dynamicBufferedDiskCaches;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = inputProducer;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        ImageRequest imageRequest = producerContext.getImageRequest();
        boolean isDiskCacheEnabledForRead = producerContext.getImageRequest().isCacheEnabled(16);
        if (!isDiskCacheEnabledForRead) {
            maybeStartInputProducer(consumer, producerContext);
            return;
        }
        producerContext.getProducerListener().onProducerStart(producerContext, PRODUCER_NAME);
        CacheKey cacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, producerContext.getCallerContext());
        BufferedDiskCache preferredCache = DiskCacheDecision.chooseDiskCacheForRequest(imageRequest, this.mSmallImageBufferedDiskCache, this.mDefaultBufferedDiskCache, this.mDynamicBufferedDiskCaches);
        if (preferredCache == null) {
            producerContext.getProducerListener().onProducerFinishWithFailure(producerContext, PRODUCER_NAME, new DiskCacheDecision.DiskCacheDecisionNoDiskCacheChosenException("Got no disk cache for CacheChoice: " + Integer.valueOf(imageRequest.getCacheChoice().ordinal()).toString()), null);
            maybeStartInputProducer(consumer, producerContext);
            return;
        }
        AtomicBoolean isCancelled = new AtomicBoolean(false);
        Task<EncodedImage> diskLookupTask = preferredCache.get(cacheKey, isCancelled);
        Continuation<EncodedImage, Void> continuation = onFinishDiskReads(consumer, producerContext);
        diskLookupTask.continueWith(continuation);
        subscribeTaskForRequestCancellation(isCancelled, producerContext);
    }

    private Continuation<EncodedImage, Void> onFinishDiskReads(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        final ProducerListener2 listener = producerContext.getProducerListener();
        return new Continuation<EncodedImage, Void>() { // from class: com.facebook.imagepipeline.producers.DiskCacheReadProducer.1
            @Override // bolts.Continuation
            @Nullable
            public Void then(Task<EncodedImage> task) throws Exception {
                if (DiskCacheReadProducer.isTaskCancelled(task)) {
                    listener.onProducerFinishWithCancellation(producerContext, DiskCacheReadProducer.PRODUCER_NAME, null);
                    consumer.onCancellation();
                } else if (task.isFaulted()) {
                    listener.onProducerFinishWithFailure(producerContext, DiskCacheReadProducer.PRODUCER_NAME, task.getError(), null);
                    DiskCacheReadProducer.this.mInputProducer.produceResults(consumer, producerContext);
                } else {
                    EncodedImage cachedReference = task.getResult();
                    if (cachedReference != null) {
                        listener.onProducerFinishWithSuccess(producerContext, DiskCacheReadProducer.PRODUCER_NAME, DiskCacheReadProducer.getExtraMap(listener, producerContext, true, cachedReference.getSize()));
                        listener.onUltimateProducerReached(producerContext, DiskCacheReadProducer.PRODUCER_NAME, true);
                        producerContext.putOriginExtra("disk");
                        consumer.onProgressUpdate(1.0f);
                        consumer.onNewResult(cachedReference, 1);
                        cachedReference.close();
                    } else {
                        listener.onProducerFinishWithSuccess(producerContext, DiskCacheReadProducer.PRODUCER_NAME, DiskCacheReadProducer.getExtraMap(listener, producerContext, false, 0));
                        DiskCacheReadProducer.this.mInputProducer.produceResults(consumer, producerContext);
                    }
                }
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isTaskCancelled(Task<?> task) {
        return task.isCancelled() || (task.isFaulted() && (task.getError() instanceof CancellationException));
    }

    private void maybeStartInputProducer(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest.RequestLevel.DISK_CACHE.getValue()) {
            producerContext.putOriginExtra("disk", "nil-result_read");
            consumer.onNewResult(null, 1);
        } else {
            this.mInputProducer.produceResults(consumer, producerContext);
        }
    }

    @Nullable
    static Map<String, String> getExtraMap(ProducerListener2 listener, ProducerContext producerContext, boolean valueFound, int sizeInBytes) {
        if (listener.requiresExtraMap(producerContext, PRODUCER_NAME)) {
            return valueFound ? ImmutableMap.of("cached_value_found", String.valueOf(valueFound), "encodedImageSize", String.valueOf(sizeInBytes)) : ImmutableMap.of("cached_value_found", String.valueOf(valueFound));
        }
        return null;
    }

    private void subscribeTaskForRequestCancellation(final AtomicBoolean isCancelled, ProducerContext producerContext) {
        producerContext.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.DiskCacheReadProducer.2
            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                isCancelled.set(true);
            }
        });
    }
}
