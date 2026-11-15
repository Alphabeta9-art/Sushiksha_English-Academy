package com.facebook.imagepipeline.producers;

import android.net.Uri;
import bolts.Continuation;
import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class PartialDiskCacheProducer implements Producer<EncodedImage> {
    public static final String ENCODED_IMAGE_SIZE = "encodedImageSize";
    public static final String EXTRA_CACHED_VALUE_FOUND = "cached_value_found";
    public static final String PRODUCER_NAME = "PartialDiskCacheProducer";
    private final ByteArrayPool mByteArrayPool;
    private final CacheKeyFactory mCacheKeyFactory;
    private final BufferedDiskCache mDefaultBufferedDiskCache;
    private final Producer<EncodedImage> mInputProducer;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    public PartialDiskCacheProducer(BufferedDiskCache defaultBufferedDiskCache, CacheKeyFactory cacheKeyFactory, PooledByteBufferFactory pooledByteBufferFactory, ByteArrayPool byteArrayPool, Producer<EncodedImage> inputProducer) {
        this.mDefaultBufferedDiskCache = defaultBufferedDiskCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mByteArrayPool = byteArrayPool;
        this.mInputProducer = inputProducer;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        ImageRequest imageRequest = producerContext.getImageRequest();
        boolean isDiskCacheEnabledForRead = producerContext.getImageRequest().isCacheEnabled(16);
        boolean isDiskCacheEnabledForWrite = producerContext.getImageRequest().isCacheEnabled(32);
        if (!isDiskCacheEnabledForRead && !isDiskCacheEnabledForWrite) {
            this.mInputProducer.produceResults(consumer, producerContext);
            return;
        }
        ProducerListener2 listener = producerContext.getProducerListener();
        listener.onProducerStart(producerContext, PRODUCER_NAME);
        Uri uriForPartialCacheKey = createUriForPartialCacheKey(imageRequest);
        CacheKey partialImageCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, uriForPartialCacheKey, producerContext.getCallerContext());
        if (!isDiskCacheEnabledForRead) {
            listener.onProducerFinishWithSuccess(producerContext, PRODUCER_NAME, getExtraMap(listener, producerContext, false, 0));
            startInputProducer(consumer, producerContext, partialImageCacheKey, null);
            return;
        }
        AtomicBoolean isCancelled = new AtomicBoolean(false);
        Task<EncodedImage> diskLookupTask = this.mDefaultBufferedDiskCache.get(partialImageCacheKey, isCancelled);
        Continuation<EncodedImage, Void> continuation = onFinishDiskReads(consumer, producerContext, partialImageCacheKey);
        diskLookupTask.continueWith(continuation);
        subscribeTaskForRequestCancellation(isCancelled, producerContext);
    }

    private Continuation<EncodedImage, Void> onFinishDiskReads(final Consumer<EncodedImage> consumer, final ProducerContext producerContext, final CacheKey partialImageCacheKey) {
        final ProducerListener2 listener = producerContext.getProducerListener();
        return new Continuation<EncodedImage, Void>() { // from class: com.facebook.imagepipeline.producers.PartialDiskCacheProducer.1
            @Override // bolts.Continuation
            @Nullable
            public Void then(Task<EncodedImage> task) throws Exception {
                if (PartialDiskCacheProducer.isTaskCancelled(task)) {
                    listener.onProducerFinishWithCancellation(producerContext, PartialDiskCacheProducer.PRODUCER_NAME, null);
                    consumer.onCancellation();
                } else if (task.isFaulted()) {
                    listener.onProducerFinishWithFailure(producerContext, PartialDiskCacheProducer.PRODUCER_NAME, task.getError(), null);
                    PartialDiskCacheProducer.this.startInputProducer(consumer, producerContext, partialImageCacheKey, null);
                } else {
                    EncodedImage cachedReference = task.getResult();
                    if (cachedReference != null) {
                        listener.onProducerFinishWithSuccess(producerContext, PartialDiskCacheProducer.PRODUCER_NAME, PartialDiskCacheProducer.getExtraMap(listener, producerContext, true, cachedReference.getSize()));
                        BytesRange cachedRange = BytesRange.toMax(cachedReference.getSize() - 1);
                        cachedReference.setBytesRange(cachedRange);
                        int cachedLength = cachedReference.getSize();
                        ImageRequest originalRequest = producerContext.getImageRequest();
                        if (cachedRange.contains(originalRequest.getBytesRange())) {
                            producerContext.putOriginExtra("disk", "partial");
                            listener.onUltimateProducerReached(producerContext, PartialDiskCacheProducer.PRODUCER_NAME, true);
                            consumer.onNewResult(cachedReference, 9);
                        } else {
                            consumer.onNewResult(cachedReference, 8);
                            ImageRequest remainingRequest = ImageRequestBuilder.fromRequest(originalRequest).setBytesRange(BytesRange.from(cachedLength - 1)).build();
                            SettableProducerContext contextForRemainingRequest = new SettableProducerContext(remainingRequest, producerContext);
                            PartialDiskCacheProducer.this.startInputProducer(consumer, contextForRemainingRequest, partialImageCacheKey, cachedReference);
                        }
                    } else {
                        listener.onProducerFinishWithSuccess(producerContext, PartialDiskCacheProducer.PRODUCER_NAME, PartialDiskCacheProducer.getExtraMap(listener, producerContext, false, 0));
                        PartialDiskCacheProducer.this.startInputProducer(consumer, producerContext, partialImageCacheKey, cachedReference);
                    }
                }
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startInputProducer(Consumer<EncodedImage> consumerOfPartialDiskCacheProducer, ProducerContext producerContext, CacheKey partialImageCacheKey, @Nullable EncodedImage partialResultFromCache) {
        Consumer<EncodedImage> consumer = new PartialDiskCacheConsumer(consumerOfPartialDiskCacheProducer, this.mDefaultBufferedDiskCache, partialImageCacheKey, this.mPooledByteBufferFactory, this.mByteArrayPool, partialResultFromCache, producerContext.getImageRequest().isCacheEnabled(32));
        this.mInputProducer.produceResults(consumer, producerContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isTaskCancelled(Task<?> task) {
        return task.isCancelled() || (task.isFaulted() && (task.getError() instanceof CancellationException));
    }

    @Nullable
    static Map<String, String> getExtraMap(ProducerListener2 listener, ProducerContext producerContext, boolean valueFound, int sizeInBytes) {
        if (listener.requiresExtraMap(producerContext, PRODUCER_NAME)) {
            return valueFound ? ImmutableMap.of("cached_value_found", String.valueOf(valueFound), "encodedImageSize", String.valueOf(sizeInBytes)) : ImmutableMap.of("cached_value_found", String.valueOf(valueFound));
        }
        return null;
    }

    private void subscribeTaskForRequestCancellation(final AtomicBoolean isCancelled, ProducerContext producerContext) {
        producerContext.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.PartialDiskCacheProducer.2
            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                isCancelled.set(true);
            }
        });
    }

    private static Uri createUriForPartialCacheKey(ImageRequest imageRequest) {
        return imageRequest.getSourceUri().buildUpon().appendQueryParameter("fresco_partial", "true").build();
    }

    private static class PartialDiskCacheConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private static final int READ_SIZE = 16384;
        private final ByteArrayPool mByteArrayPool;
        private final BufferedDiskCache mDefaultBufferedDiskCache;
        private final boolean mIsDiskCacheEnabledForWrite;

        @Nullable
        private final EncodedImage mPartialEncodedImageFromCache;
        private final CacheKey mPartialImageCacheKey;
        private final PooledByteBufferFactory mPooledByteBufferFactory;

        private PartialDiskCacheConsumer(Consumer<EncodedImage> consumer, BufferedDiskCache defaultBufferedDiskCache, CacheKey partialImageCacheKey, PooledByteBufferFactory pooledByteBufferFactory, ByteArrayPool byteArrayPool, @Nullable EncodedImage partialEncodedImageFromCache, boolean isDiskCacheEnabledForWrite) {
            super(consumer);
            this.mDefaultBufferedDiskCache = defaultBufferedDiskCache;
            this.mPartialImageCacheKey = partialImageCacheKey;
            this.mPooledByteBufferFactory = pooledByteBufferFactory;
            this.mByteArrayPool = byteArrayPool;
            this.mPartialEncodedImageFromCache = partialEncodedImageFromCache;
            this.mIsDiskCacheEnabledForWrite = isDiskCacheEnabledForWrite;
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable EncodedImage newResult, int status) {
            if (isNotLast(status)) {
                return;
            }
            if (this.mPartialEncodedImageFromCache != null && newResult != null && newResult.getBytesRange() != null) {
                try {
                    try {
                        PooledByteBufferOutputStream pooledOutputStream = merge(this.mPartialEncodedImageFromCache, newResult);
                        sendFinalResultToConsumer(pooledOutputStream);
                    } catch (IOException e) {
                        FLog.e(PartialDiskCacheProducer.PRODUCER_NAME, "Error while merging image data", e);
                        getConsumer().onFailure(e);
                    }
                    newResult.close();
                    this.mPartialEncodedImageFromCache.close();
                    this.mDefaultBufferedDiskCache.remove(this.mPartialImageCacheKey);
                    return;
                } catch (Throwable th) {
                    newResult.close();
                    this.mPartialEncodedImageFromCache.close();
                    throw th;
                }
            }
            if (this.mIsDiskCacheEnabledForWrite && statusHasFlag(status, 8) && isLast(status) && newResult != null && newResult.getImageFormat() != ImageFormat.UNKNOWN) {
                this.mDefaultBufferedDiskCache.put(this.mPartialImageCacheKey, newResult);
                getConsumer().onNewResult(newResult, status);
            } else {
                getConsumer().onNewResult(newResult, status);
            }
        }

        private PooledByteBufferOutputStream merge(EncodedImage initialData, EncodedImage remainingData) throws IOException {
            int bytesToReadFromInitialData = ((BytesRange) Preconditions.checkNotNull(remainingData.getBytesRange())).from;
            int totalLength = remainingData.getSize() + bytesToReadFromInitialData;
            PooledByteBufferOutputStream pooledOutputStream = this.mPooledByteBufferFactory.newOutputStream(totalLength);
            copy(initialData.getInputStreamOrThrow(), pooledOutputStream, bytesToReadFromInitialData);
            copy(remainingData.getInputStreamOrThrow(), pooledOutputStream, remainingData.getSize());
            return pooledOutputStream;
        }

        private void copy(InputStream from, OutputStream to, int length) throws IOException {
            int bytesStillToRead = length;
            byte[] ioArray = this.mByteArrayPool.get(16384);
            while (bytesStillToRead > 0) {
                try {
                    int bufferLength = from.read(ioArray, 0, Math.min(16384, bytesStillToRead));
                    if (bufferLength < 0) {
                        break;
                    } else if (bufferLength > 0) {
                        to.write(ioArray, 0, bufferLength);
                        bytesStillToRead -= bufferLength;
                    }
                } catch (Throwable th) {
                    this.mByteArrayPool.release(ioArray);
                    throw th;
                }
            }
            this.mByteArrayPool.release(ioArray);
            if (bytesStillToRead > 0) {
                throw new IOException(String.format(null, "Failed to read %d bytes - finished %d short", Integer.valueOf(length), Integer.valueOf(bytesStillToRead)));
            }
        }

        private void sendFinalResultToConsumer(PooledByteBufferOutputStream pooledOutputStream) {
            CloseableReference<PooledByteBuffer> result = CloseableReference.of(pooledOutputStream.toByteBuffer());
            EncodedImage encodedImage = null;
            try {
                encodedImage = new EncodedImage(result);
                encodedImage.parseMetaData();
                getConsumer().onNewResult(encodedImage, 1);
            } finally {
                EncodedImage.closeSafely(encodedImage);
                CloseableReference.closeSafely(result);
            }
        }
    }
}
