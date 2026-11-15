package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Closeables;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public abstract class LocalFetchProducer implements Producer<EncodedImage> {
    private final Executor mExecutor;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    @Nullable
    protected abstract EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException;

    protected abstract String getProducerName();

    protected LocalFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory) {
        this.mExecutor = executor;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        final ProducerListener2 listener = producerContext.getProducerListener();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        producerContext.putOriginExtra(ImagesContract.LOCAL, "fetch");
        final StatefulProducerRunnable<EncodedImage> cancellableProducerRunnable = new StatefulProducerRunnable<EncodedImage>(consumer, listener, producerContext, getProducerName()) { // from class: com.facebook.imagepipeline.producers.LocalFetchProducer.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.common.executors.StatefulRunnable
            @Nullable
            public EncodedImage getResult() throws Exception {
                EncodedImage encodedImage = LocalFetchProducer.this.getEncodedImage(imageRequest);
                if (encodedImage == null) {
                    listener.onUltimateProducerReached(producerContext, LocalFetchProducer.this.getProducerName(), false);
                    producerContext.putOriginExtra(ImagesContract.LOCAL, "fetch");
                    return null;
                }
                encodedImage.parseMetaData();
                listener.onUltimateProducerReached(producerContext, LocalFetchProducer.this.getProducerName(), true);
                producerContext.putOriginExtra(ImagesContract.LOCAL, "fetch");
                producerContext.putExtra("image_color_space", encodedImage.getColorSpace());
                return encodedImage;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
            public void disposeResult(@Nullable EncodedImage result) {
                EncodedImage.closeSafely(result);
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.LocalFetchProducer.2
            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                cancellableProducerRunnable.cancel();
            }
        });
        this.mExecutor.execute(cancellableProducerRunnable);
    }

    protected EncodedImage getByteBufferBackedEncodedImage(InputStream inputStream, int length) throws IOException {
        CloseableReference<PooledByteBuffer> ref = null;
        try {
            if (length <= 0) {
                ref = CloseableReference.of(this.mPooledByteBufferFactory.newByteBuffer(inputStream));
            } else {
                CloseableReference<PooledByteBuffer> ref2 = CloseableReference.of(this.mPooledByteBufferFactory.newByteBuffer(inputStream, length));
                ref = ref2;
            }
            EncodedImage encodedImage = new EncodedImage(ref);
            Closeables.closeQuietly(inputStream);
            CloseableReference.closeSafely(ref);
            return encodedImage;
        } catch (Throwable th) {
            Closeables.closeQuietly(inputStream);
            CloseableReference.closeSafely(ref);
            throw th;
        }
    }

    protected EncodedImage getEncodedImage(InputStream inputStream, int length) throws IOException {
        return getByteBufferBackedEncodedImage(inputStream, length);
    }
}
