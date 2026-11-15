package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.instrumentation.FrescoInstrumenter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class JobScheduler {
    static final String QUEUE_TIME_KEY = "queueTime";
    private final Executor mExecutor;
    private final JobRunnable mJobRunnable;
    private final int mMinimumJobIntervalMs;
    private final Runnable mDoJobRunnable = new Runnable() { // from class: com.facebook.imagepipeline.producers.JobScheduler.1
        @Override // java.lang.Runnable
        public void run() {
            JobScheduler.this.doJob();
        }
    };
    private final Runnable mSubmitJobRunnable = new Runnable() { // from class: com.facebook.imagepipeline.producers.JobScheduler.2
        @Override // java.lang.Runnable
        public void run() {
            JobScheduler.this.submitJob();
        }
    };

    @Nullable
    EncodedImage mEncodedImage = null;
    int mStatus = 0;
    JobState mJobState = JobState.IDLE;
    long mJobSubmitTime = 0;
    long mJobStartTime = 0;

    public interface JobRunnable {
        void run(@Nullable EncodedImage encodedImage, int i);
    }

    enum JobState {
        IDLE,
        QUEUED,
        RUNNING,
        RUNNING_AND_PENDING
    }

    static class JobStartExecutorSupplier {
        private static ScheduledExecutorService sJobStarterExecutor;

        JobStartExecutorSupplier() {
        }

        static ScheduledExecutorService get() {
            if (sJobStarterExecutor == null) {
                sJobStarterExecutor = Executors.newSingleThreadScheduledExecutor();
            }
            return sJobStarterExecutor;
        }
    }

    public JobScheduler(Executor executor, JobRunnable jobRunnable, int minimumJobIntervalMs) {
        this.mExecutor = executor;
        this.mJobRunnable = jobRunnable;
        this.mMinimumJobIntervalMs = minimumJobIntervalMs;
    }

    public void clearJob() {
        EncodedImage oldEncodedImage;
        synchronized (this) {
            oldEncodedImage = this.mEncodedImage;
            this.mEncodedImage = null;
            this.mStatus = 0;
        }
        EncodedImage.closeSafely(oldEncodedImage);
    }

    public boolean updateJob(@Nullable EncodedImage encodedImage, int status) {
        EncodedImage oldEncodedImage;
        if (!shouldProcess(encodedImage, status)) {
            return false;
        }
        synchronized (this) {
            oldEncodedImage = this.mEncodedImage;
            this.mEncodedImage = EncodedImage.cloneOrNull(encodedImage);
            this.mStatus = status;
        }
        EncodedImage.closeSafely(oldEncodedImage);
        return true;
    }

    public boolean scheduleJob() {
        long now = SystemClock.uptimeMillis();
        long when = 0;
        boolean shouldEnqueue = false;
        synchronized (this) {
            if (!shouldProcess(this.mEncodedImage, this.mStatus)) {
                return false;
            }
            switch (this.mJobState) {
                case IDLE:
                    when = Math.max(this.mJobStartTime + ((long) this.mMinimumJobIntervalMs), now);
                    shouldEnqueue = true;
                    this.mJobSubmitTime = now;
                    this.mJobState = JobState.QUEUED;
                    break;
                case RUNNING:
                    this.mJobState = JobState.RUNNING_AND_PENDING;
                    break;
            }
            if (shouldEnqueue) {
                enqueueJob(when - now);
                return true;
            }
            return true;
        }
    }

    private void enqueueJob(long delay) {
        Runnable submitJobRunnable = FrescoInstrumenter.decorateRunnable(this.mSubmitJobRunnable, "JobScheduler_enqueueJob");
        if (delay > 0) {
            JobStartExecutorSupplier.get().schedule(submitJobRunnable, delay, TimeUnit.MILLISECONDS);
        } else {
            submitJobRunnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void submitJob() {
        this.mExecutor.execute(FrescoInstrumenter.decorateRunnable(this.mDoJobRunnable, "JobScheduler_submitJob"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doJob() {
        EncodedImage input;
        int status;
        long now = SystemClock.uptimeMillis();
        synchronized (this) {
            input = this.mEncodedImage;
            status = this.mStatus;
            this.mEncodedImage = null;
            this.mStatus = 0;
            this.mJobState = JobState.RUNNING;
            this.mJobStartTime = now;
        }
        try {
            if (shouldProcess(input, status)) {
                this.mJobRunnable.run(input, status);
            }
        } finally {
            EncodedImage.closeSafely(input);
            onJobFinished();
        }
    }

    private void onJobFinished() {
        long now = SystemClock.uptimeMillis();
        long when = 0;
        boolean shouldEnqueue = false;
        synchronized (this) {
            if (this.mJobState == JobState.RUNNING_AND_PENDING) {
                when = Math.max(this.mJobStartTime + ((long) this.mMinimumJobIntervalMs), now);
                shouldEnqueue = true;
                this.mJobSubmitTime = now;
                this.mJobState = JobState.QUEUED;
            } else {
                this.mJobState = JobState.IDLE;
            }
        }
        if (shouldEnqueue) {
            enqueueJob(when - now);
        }
    }

    private static boolean shouldProcess(@Nullable EncodedImage encodedImage, int status) {
        return BaseConsumer.isLast(status) || BaseConsumer.statusHasFlag(status, 4) || EncodedImage.isValid(encodedImage);
    }

    public synchronized long getQueuedTime() {
        return this.mJobStartTime - this.mJobSubmitTime;
    }
}
