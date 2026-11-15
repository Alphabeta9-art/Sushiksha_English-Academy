package androidx.work.impl.utils;

import androidx.work.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes11.dex */
public class WorkTimer {
    private static final String TAG = Logger.tagWithPrefix("WorkTimer");
    private final ThreadFactory mBackgroundThreadFactory = new ThreadFactory() { // from class: androidx.work.impl.utils.WorkTimer.1
        private int mThreadsCreated = 0;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            Thread thread = Executors.defaultThreadFactory().newThread(r);
            thread.setName("WorkManager-WorkTimer-thread-" + this.mThreadsCreated);
            this.mThreadsCreated++;
            return thread;
        }
    };
    final Map<String, WorkTimerRunnable> mTimerMap = new HashMap();
    final Map<String, TimeLimitExceededListener> mListeners = new HashMap();
    final Object mLock = new Object();
    private final ScheduledExecutorService mExecutorService = Executors.newSingleThreadScheduledExecutor(this.mBackgroundThreadFactory);

    public interface TimeLimitExceededListener {
        void onTimeLimitExceeded(String workSpecId);
    }

    public void startTimer(final String workSpecId, long processingTimeMillis, TimeLimitExceededListener listener) {
        synchronized (this.mLock) {
            Logger.get().debug(TAG, String.format("Starting timer for %s", workSpecId), new Throwable[0]);
            stopTimer(workSpecId);
            WorkTimerRunnable runnable = new WorkTimerRunnable(this, workSpecId);
            this.mTimerMap.put(workSpecId, runnable);
            this.mListeners.put(workSpecId, listener);
            this.mExecutorService.schedule(runnable, processingTimeMillis, TimeUnit.MILLISECONDS);
        }
    }

    public void stopTimer(final String workSpecId) {
        synchronized (this.mLock) {
            WorkTimerRunnable removed = this.mTimerMap.remove(workSpecId);
            if (removed != null) {
                Logger.get().debug(TAG, String.format("Stopping timer for %s", workSpecId), new Throwable[0]);
                this.mListeners.remove(workSpecId);
            }
        }
    }

    public void onDestroy() {
        if (!this.mExecutorService.isShutdown()) {
            this.mExecutorService.shutdownNow();
        }
    }

    public synchronized Map<String, WorkTimerRunnable> getTimerMap() {
        return this.mTimerMap;
    }

    public synchronized Map<String, TimeLimitExceededListener> getListeners() {
        return this.mListeners;
    }

    public ScheduledExecutorService getExecutorService() {
        return this.mExecutorService;
    }

    public static class WorkTimerRunnable implements Runnable {
        static final String TAG = "WrkTimerRunnable";
        private final String mWorkSpecId;
        private final WorkTimer mWorkTimer;

        WorkTimerRunnable(WorkTimer workTimer, String workSpecId) {
            this.mWorkTimer = workTimer;
            this.mWorkSpecId = workSpecId;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this.mWorkTimer.mLock) {
                WorkTimerRunnable removed = this.mWorkTimer.mTimerMap.remove(this.mWorkSpecId);
                if (removed != null) {
                    TimeLimitExceededListener listener = this.mWorkTimer.mListeners.remove(this.mWorkSpecId);
                    if (listener != null) {
                        listener.onTimeLimitExceeded(this.mWorkSpecId);
                    }
                } else {
                    Logger.get().debug(TAG, String.format("Timer with %s is already marked as complete.", this.mWorkSpecId), new Throwable[0]);
                }
            }
        }
    }
}
