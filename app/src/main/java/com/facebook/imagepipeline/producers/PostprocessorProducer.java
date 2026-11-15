package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessorRunner;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class PostprocessorProducer implements Producer<CloseableReference<CloseableImage>> {
    public static final String NAME = "PostprocessorProducer";
    static final String POSTPROCESSOR = "Postprocessor";
    private final PlatformBitmapFactory mBitmapFactory;
    private final Executor mExecutor;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;

    public PostprocessorProducer(Producer<CloseableReference<CloseableImage>> inputProducer, PlatformBitmapFactory platformBitmapFactory, Executor executor) {
        this.mInputProducer = (Producer) Preconditions.checkNotNull(inputProducer);
        this.mBitmapFactory = platformBitmapFactory;
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext context) {
        Consumer<CloseableReference<CloseableImage>> postprocessorConsumer;
        ProducerListener2 listener = context.getProducerListener();
        Postprocessor postprocessor = context.getImageRequest().getPostprocessor();
        Preconditions.checkNotNull(postprocessor);
        ProducerContext context2 = context;
        PostprocessorConsumer basePostprocessorConsumer = new PostprocessorConsumer(consumer, listener, postprocessor, context2);
        if (postprocessor instanceof RepeatedPostprocessor) {
            postprocessorConsumer = new RepeatedPostprocessorConsumer(basePostprocessorConsumer, (RepeatedPostprocessor) postprocessor, context2);
            context2 = context2;
        } else {
            postprocessorConsumer = new SingleUsePostprocessorConsumer(basePostprocessorConsumer);
        }
        this.mInputProducer.produceResults(postprocessorConsumer, context2);
    }

    private class PostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        private boolean mIsClosed;
        private boolean mIsDirty;
        private boolean mIsPostProcessingRunning;
        private final ProducerListener2 mListener;
        private final Postprocessor mPostprocessor;
        private final ProducerContext mProducerContext;

        @Nullable
        private CloseableReference<CloseableImage> mSourceImageRef;
        private int mStatus;

        public PostprocessorConsumer(Consumer<CloseableReference<CloseableImage>> consumer, ProducerListener2 listener, Postprocessor postprocessor, ProducerContext producerContext) {
            super(consumer);
            this.mSourceImageRef = null;
            this.mStatus = 0;
            this.mIsDirty = false;
            this.mIsPostProcessingRunning = false;
            this.mListener = listener;
            this.mPostprocessor = postprocessor;
            this.mProducerContext = producerContext;
            producerContext.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.PostprocessorProducer.PostprocessorConsumer.1
                @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onCancellationRequested() {
                    PostprocessorConsumer.this.maybeNotifyOnCancellation();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable CloseableReference<CloseableImage> newResult, int status) {
            if (!CloseableReference.isValid(newResult)) {
                if (isLast(status)) {
                    maybeNotifyOnNewResult(null, status);
                    return;
                }
                return;
            }
            updateSourceImageRef(newResult, status);
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        protected void onFailureImpl(Throwable t) {
            maybeNotifyOnFailure(t);
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        protected void onCancellationImpl() {
            maybeNotifyOnCancellation();
        }

        private void updateSourceImageRef(@Nullable CloseableReference<CloseableImage> sourceImageRef, int status) {
            synchronized (this) {
                if (this.mIsClosed) {
                    return;
                }
                CloseableReference<CloseableImage> oldSourceImageRef = this.mSourceImageRef;
                this.mSourceImageRef = CloseableReference.cloneOrNull(sourceImageRef);
                this.mStatus = status;
                this.mIsDirty = true;
                boolean shouldSubmit = setRunningIfDirtyAndNotRunning();
                CloseableReference.closeSafely(oldSourceImageRef);
                if (shouldSubmit) {
                    submitPostprocessing();
                }
            }
        }

        private void submitPostprocessing() {
            PostprocessorProducer.this.mExecutor.execute(new Runnable() { // from class: com.facebook.imagepipeline.producers.PostprocessorProducer.PostprocessorConsumer.2
                @Override // java.lang.Runnable
                public void run() {
                    CloseableReference<CloseableImage> closeableImageRef;
                    int status;
                    synchronized (PostprocessorConsumer.this) {
                        closeableImageRef = PostprocessorConsumer.this.mSourceImageRef;
                        status = PostprocessorConsumer.this.mStatus;
                        PostprocessorConsumer.this.mSourceImageRef = null;
                        PostprocessorConsumer.this.mIsDirty = false;
                    }
                    if (CloseableReference.isValid(closeableImageRef)) {
                        try {
                            PostprocessorConsumer.this.doPostprocessing(closeableImageRef, status);
                            CloseableReference.closeSafely(closeableImageRef);
                        } catch (Throwable th) {
                            CloseableReference.closeSafely(closeableImageRef);
                            throw th;
                        }
                    }
                    PostprocessorConsumer.this.clearRunningAndStartIfDirty();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRunningAndStartIfDirty() {
            boolean shouldExecuteAgain;
            synchronized (this) {
                this.mIsPostProcessingRunning = false;
                shouldExecuteAgain = setRunningIfDirtyAndNotRunning();
            }
            if (shouldExecuteAgain) {
                submitPostprocessing();
            }
        }

        private synchronized boolean setRunningIfDirtyAndNotRunning() {
            if (this.mIsClosed || !this.mIsDirty || this.mIsPostProcessingRunning || !CloseableReference.isValid(this.mSourceImageRef)) {
                return false;
            }
            this.mIsPostProcessingRunning = true;
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void doPostprocessing(CloseableReference<CloseableImage> sourceImageRef, int status) {
            Preconditions.checkArgument(Boolean.valueOf(CloseableReference.isValid(sourceImageRef)));
            if (!shouldPostprocess(sourceImageRef.get())) {
                maybeNotifyOnNewResult(sourceImageRef, status);
                return;
            }
            this.mListener.onProducerStart(this.mProducerContext, PostprocessorProducer.NAME);
            CloseableReference<CloseableImage> destImageRef = null;
            try {
                destImageRef = postprocessInternal(sourceImageRef.get());
                this.mListener.onProducerFinishWithSuccess(this.mProducerContext, PostprocessorProducer.NAME, getExtraMap(this.mListener, this.mProducerContext, this.mPostprocessor));
                maybeNotifyOnNewResult(destImageRef, status);
            } catch (Exception e) {
                this.mListener.onProducerFinishWithFailure(this.mProducerContext, PostprocessorProducer.NAME, e, getExtraMap(this.mListener, this.mProducerContext, this.mPostprocessor));
                maybeNotifyOnFailure(e);
            } finally {
                CloseableReference.closeSafely(destImageRef);
            }
        }

        @Nullable
        private Map<String, String> getExtraMap(ProducerListener2 listener, ProducerContext producerContext, Postprocessor postprocessor) {
            if (!listener.requiresExtraMap(producerContext, PostprocessorProducer.NAME)) {
                return null;
            }
            return ImmutableMap.of(PostprocessorProducer.POSTPROCESSOR, postprocessor.getName());
        }

        private boolean shouldPostprocess(CloseableImage sourceImage) {
            return sourceImage instanceof CloseableStaticBitmap;
        }

        private CloseableReference<CloseableImage> postprocessInternal(CloseableImage sourceImage) {
            CloseableStaticBitmap staticBitmap = (CloseableStaticBitmap) sourceImage;
            Bitmap sourceBitmap = staticBitmap.getUnderlyingBitmap();
            CloseableReference<Bitmap> bitmapRef = this.mPostprocessor.process(sourceBitmap, PostprocessorProducer.this.mBitmapFactory);
            int rotationAngle = staticBitmap.getRotationAngle();
            int exifOrientation = staticBitmap.getExifOrientation();
            try {
                CloseableStaticBitmap closeableStaticBitmap = CloseableStaticBitmap.of(bitmapRef, sourceImage.getQualityInfo(), rotationAngle, exifOrientation);
                closeableStaticBitmap.putExtras(staticBitmap.getExtras());
                return CloseableReference.of(closeableStaticBitmap);
            } finally {
                CloseableReference.closeSafely(bitmapRef);
            }
        }

        private void maybeNotifyOnNewResult(@Nullable CloseableReference<CloseableImage> newRef, int status) {
            boolean isLast = isLast(status);
            if ((!isLast && !isClosed()) || (isLast && close())) {
                getConsumer().onNewResult(newRef, status);
            }
        }

        private void maybeNotifyOnFailure(Throwable throwable) {
            if (close()) {
                getConsumer().onFailure(throwable);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void maybeNotifyOnCancellation() {
            if (close()) {
                getConsumer().onCancellation();
            }
        }

        private synchronized boolean isClosed() {
            return this.mIsClosed;
        }

        private boolean close() {
            synchronized (this) {
                if (this.mIsClosed) {
                    return false;
                }
                CloseableReference<CloseableImage> oldSourceImageRef = this.mSourceImageRef;
                this.mSourceImageRef = null;
                this.mIsClosed = true;
                CloseableReference.closeSafely(oldSourceImageRef);
                return true;
            }
        }
    }

    class SingleUsePostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        private SingleUsePostprocessorConsumer(PostprocessorConsumer postprocessorConsumer) {
            super(postprocessorConsumer);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable CloseableReference<CloseableImage> newResult, int status) {
            if (isNotLast(status)) {
                return;
            }
            getConsumer().onNewResult(newResult, status);
        }
    }

    class RepeatedPostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> implements RepeatedPostprocessorRunner {
        private boolean mIsClosed;

        @Nullable
        private CloseableReference<CloseableImage> mSourceImageRef;

        private RepeatedPostprocessorConsumer(PostprocessorConsumer postprocessorConsumer, RepeatedPostprocessor repeatedPostprocessor, ProducerContext context) {
            super(postprocessorConsumer);
            this.mIsClosed = false;
            this.mSourceImageRef = null;
            repeatedPostprocessor.setCallback(this);
            context.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.PostprocessorProducer.RepeatedPostprocessorConsumer.1
                @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onCancellationRequested() {
                    if (RepeatedPostprocessorConsumer.this.close()) {
                        RepeatedPostprocessorConsumer.this.getConsumer().onCancellation();
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(CloseableReference<CloseableImage> newResult, int status) {
            if (isNotLast(status)) {
                return;
            }
            setSourceImageRef(newResult);
            updateInternal();
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        protected void onFailureImpl(Throwable throwable) {
            if (close()) {
                getConsumer().onFailure(throwable);
            }
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        protected void onCancellationImpl() {
            if (close()) {
                getConsumer().onCancellation();
            }
        }

        @Override // com.facebook.imagepipeline.request.RepeatedPostprocessorRunner
        public synchronized void update() {
            updateInternal();
        }

        private void updateInternal() {
            synchronized (this) {
                if (this.mIsClosed) {
                    return;
                }
                CloseableReference<CloseableImage> sourceImageRef = CloseableReference.cloneOrNull(this.mSourceImageRef);
                try {
                    getConsumer().onNewResult(sourceImageRef, 0);
                } finally {
                    CloseableReference.closeSafely(sourceImageRef);
                }
            }
        }

        private void setSourceImageRef(CloseableReference<CloseableImage> sourceImageRef) {
            synchronized (this) {
                if (this.mIsClosed) {
                    return;
                }
                CloseableReference<CloseableImage> oldSourceImageRef = this.mSourceImageRef;
                this.mSourceImageRef = CloseableReference.cloneOrNull(sourceImageRef);
                CloseableReference.closeSafely(oldSourceImageRef);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean close() {
            synchronized (this) {
                if (this.mIsClosed) {
                    return false;
                }
                CloseableReference<CloseableImage> oldSourceImageRef = this.mSourceImageRef;
                this.mSourceImageRef = null;
                this.mIsClosed = true;
                CloseableReference.closeSafely(oldSourceImageRef);
                return true;
            }
        }
    }
}
