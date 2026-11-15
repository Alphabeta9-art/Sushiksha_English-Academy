package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.common.Priority;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class PriorityStarvingThrottlingProducer<T> implements Producer<T> {
    public static final String PRODUCER_NAME = "PriorityStarvingThrottlingProducer";
    private final Executor mExecutor;
    private final Producer<T> mInputProducer;
    private final int mMaxSimultaneousRequests;
    private final Queue<Item<T>> mPendingRequests = new PriorityQueue(11, new PriorityComparator());
    private int mNumCurrentRequests = 0;

    public PriorityStarvingThrottlingProducer(int maxSimultaneousRequests, Executor executor, Producer<T> inputProducer) {
        this.mMaxSimultaneousRequests = maxSimultaneousRequests;
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(inputProducer);
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<T> consumer, ProducerContext producerContext) {
        boolean delayRequest;
        long time = System.nanoTime();
        ProducerListener2 producerListener = producerContext.getProducerListener();
        producerListener.onProducerStart(producerContext, PRODUCER_NAME);
        synchronized (this) {
            if (this.mNumCurrentRequests >= this.mMaxSimultaneousRequests) {
                this.mPendingRequests.add(new Item<>(consumer, producerContext, time));
                delayRequest = true;
            } else {
                this.mNumCurrentRequests++;
                delayRequest = false;
            }
        }
        if (!delayRequest) {
            produceResultsInternal(new Item<>(consumer, producerContext, time));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void produceResultsInternal(Item<T> item) {
        ProducerListener2 producerListener = item.producerContext.getProducerListener();
        producerListener.onProducerFinishWithSuccess(item.producerContext, PRODUCER_NAME, null);
        this.mInputProducer.produceResults(new ThrottlerConsumer(item.consumer), item.producerContext);
    }

    static class Item<T> {
        final Consumer<T> consumer;
        final ProducerContext producerContext;
        final long time;

        Item(Consumer<T> consumer, ProducerContext producerContext, long time) {
            this.consumer = consumer;
            this.producerContext = producerContext;
            this.time = time;
        }
    }

    static class PriorityComparator<T> implements Comparator<Item<T>> {
        PriorityComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Item<T> o1, Item<T> o2) {
            Priority p1 = o1.producerContext.getPriority();
            Priority p2 = o2.producerContext.getPriority();
            if (p1 == p2) {
                return Double.compare(o1.time, o2.time);
            }
            if (p1.ordinal() > p2.ordinal()) {
                return -1;
            }
            return 1;
        }
    }

    private class ThrottlerConsumer extends DelegatingConsumer<T, T> {
        private ThrottlerConsumer(Consumer<T> consumer) {
            super(consumer);
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        protected void onNewResultImpl(@Nullable T newResult, int status) {
            getConsumer().onNewResult(newResult, status);
            if (isLast(status)) {
                onRequestFinished();
            }
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        protected void onFailureImpl(Throwable t) {
            getConsumer().onFailure(t);
            onRequestFinished();
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        protected void onCancellationImpl() {
            getConsumer().onCancellation();
            onRequestFinished();
        }

        private void onRequestFinished() {
            final Item<T> nextRequest;
            synchronized (PriorityStarvingThrottlingProducer.this) {
                nextRequest = (Item) PriorityStarvingThrottlingProducer.this.mPendingRequests.poll();
                if (nextRequest == null) {
                    PriorityStarvingThrottlingProducer.this.mNumCurrentRequests--;
                }
            }
            if (nextRequest != null) {
                PriorityStarvingThrottlingProducer.this.mExecutor.execute(new Runnable() { // from class: com.facebook.imagepipeline.producers.PriorityStarvingThrottlingProducer.ThrottlerConsumer.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PriorityStarvingThrottlingProducer.this.produceResultsInternal(nextRequest);
                    }
                });
            }
        }
    }
}
