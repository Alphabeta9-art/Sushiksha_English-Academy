package androidx.work.impl;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import androidx.core.content.ContextCompat;
import androidx.work.Configuration;
import androidx.work.ForegroundInfo;
import androidx.work.Logger;
import androidx.work.WorkerParameters;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes11.dex */
public class Processor implements ExecutionListener, ForegroundProcessor {
    private static final String FOREGROUND_WAKELOCK_TAG = "ProcessorForegroundLck";
    private static final String TAG = Logger.tagWithPrefix("Processor");
    private Context mAppContext;
    private Configuration mConfiguration;
    private List<Scheduler> mSchedulers;
    private WorkDatabase mWorkDatabase;
    private TaskExecutor mWorkTaskExecutor;
    private Map<String, WorkerWrapper> mEnqueuedWorkMap = new HashMap();
    private Map<String, WorkerWrapper> mForegroundWorkMap = new HashMap();
    private Set<String> mCancelledIds = new HashSet();
    private final List<ExecutionListener> mOuterListeners = new ArrayList();
    private PowerManager.WakeLock mForegroundLock = null;
    private final Object mLock = new Object();

    public Processor(Context appContext, Configuration configuration, TaskExecutor workTaskExecutor, WorkDatabase workDatabase, List<Scheduler> schedulers) {
        this.mAppContext = appContext;
        this.mConfiguration = configuration;
        this.mWorkTaskExecutor = workTaskExecutor;
        this.mWorkDatabase = workDatabase;
        this.mSchedulers = schedulers;
    }

    public boolean startWork(String id) {
        return startWork(id, null);
    }

    public boolean startWork(String id, WorkerParameters.RuntimeExtras runtimeExtras) throws Throwable {
        synchronized (this.mLock) {
            try {
                try {
                    if (isEnqueued(id)) {
                        try {
                            Logger.get().debug(TAG, String.format("Work %s is already enqueued for processing", id), new Throwable[0]);
                            return false;
                        } catch (Throwable th) {
                            th = th;
                        }
                    } else {
                        WorkerWrapper workWrapper = new WorkerWrapper.Builder(this.mAppContext, this.mConfiguration, this.mWorkTaskExecutor, this, this.mWorkDatabase, id).withSchedulers(this.mSchedulers).withRuntimeExtras(runtimeExtras).build();
                        ListenableFuture<Boolean> future = workWrapper.getFuture();
                        future.addListener(new FutureListener(this, id, future), this.mWorkTaskExecutor.getMainThreadExecutor());
                        this.mEnqueuedWorkMap.put(id, workWrapper);
                        this.mWorkTaskExecutor.getBackgroundExecutor().execute(workWrapper);
                        Logger.get().debug(TAG, String.format("%s: processing %s", getClass().getSimpleName(), id), new Throwable[0]);
                        return true;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
            throw th;
        }
    }

    @Override // androidx.work.impl.foreground.ForegroundProcessor
    public void startForeground(String workSpecId, ForegroundInfo foregroundInfo) {
        synchronized (this.mLock) {
            Logger.get().info(TAG, String.format("Moving WorkSpec (%s) to the foreground", workSpecId), new Throwable[0]);
            WorkerWrapper wrapper = this.mEnqueuedWorkMap.remove(workSpecId);
            if (wrapper != null) {
                if (this.mForegroundLock == null) {
                    this.mForegroundLock = WakeLocks.newWakeLock(this.mAppContext, FOREGROUND_WAKELOCK_TAG);
                    this.mForegroundLock.acquire();
                }
                this.mForegroundWorkMap.put(workSpecId, wrapper);
                Intent intent = SystemForegroundDispatcher.createStartForegroundIntent(this.mAppContext, workSpecId, foregroundInfo);
                ContextCompat.startForegroundService(this.mAppContext, intent);
            }
        }
    }

    public boolean stopForegroundWork(String id) {
        boolean zInterrupt;
        synchronized (this.mLock) {
            Logger.get().debug(TAG, String.format("Processor stopping foreground work %s", id), new Throwable[0]);
            WorkerWrapper wrapper = this.mForegroundWorkMap.remove(id);
            zInterrupt = interrupt(id, wrapper);
        }
        return zInterrupt;
    }

    public boolean stopWork(String id) {
        boolean zInterrupt;
        synchronized (this.mLock) {
            Logger.get().debug(TAG, String.format("Processor stopping background work %s", id), new Throwable[0]);
            WorkerWrapper wrapper = this.mEnqueuedWorkMap.remove(id);
            zInterrupt = interrupt(id, wrapper);
        }
        return zInterrupt;
    }

    public boolean stopAndCancelWork(String id) {
        boolean interrupted;
        synchronized (this.mLock) {
            Logger.get().debug(TAG, String.format("Processor cancelling %s", id), new Throwable[0]);
            this.mCancelledIds.add(id);
            WorkerWrapper wrapper = this.mForegroundWorkMap.remove(id);
            boolean isForegroundWork = wrapper != null;
            if (wrapper == null) {
                wrapper = this.mEnqueuedWorkMap.remove(id);
            }
            interrupted = interrupt(id, wrapper);
            if (isForegroundWork) {
                stopForegroundService();
            }
        }
        return interrupted;
    }

    @Override // androidx.work.impl.foreground.ForegroundProcessor
    public void stopForeground(String workSpecId) {
        synchronized (this.mLock) {
            this.mForegroundWorkMap.remove(workSpecId);
            stopForegroundService();
        }
    }

    public boolean isCancelled(String id) {
        boolean zContains;
        synchronized (this.mLock) {
            zContains = this.mCancelledIds.contains(id);
        }
        return zContains;
    }

    public boolean hasWork() {
        boolean z;
        synchronized (this.mLock) {
            z = (this.mEnqueuedWorkMap.isEmpty() && this.mForegroundWorkMap.isEmpty()) ? false : true;
        }
        return z;
    }

    public boolean isEnqueued(String workSpecId) {
        boolean z;
        synchronized (this.mLock) {
            z = this.mEnqueuedWorkMap.containsKey(workSpecId) || this.mForegroundWorkMap.containsKey(workSpecId);
        }
        return z;
    }

    public boolean isEnqueuedInForeground(String workSpecId) {
        boolean zContainsKey;
        synchronized (this.mLock) {
            zContainsKey = this.mForegroundWorkMap.containsKey(workSpecId);
        }
        return zContainsKey;
    }

    public void addExecutionListener(ExecutionListener executionListener) {
        synchronized (this.mLock) {
            this.mOuterListeners.add(executionListener);
        }
    }

    public void removeExecutionListener(ExecutionListener executionListener) {
        synchronized (this.mLock) {
            this.mOuterListeners.remove(executionListener);
        }
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(final String workSpecId, boolean needsReschedule) {
        synchronized (this.mLock) {
            this.mEnqueuedWorkMap.remove(workSpecId);
            Logger.get().debug(TAG, String.format("%s %s executed; reschedule = %s", getClass().getSimpleName(), workSpecId, Boolean.valueOf(needsReschedule)), new Throwable[0]);
            for (ExecutionListener executionListener : this.mOuterListeners) {
                executionListener.onExecuted(workSpecId, needsReschedule);
            }
        }
    }

    private void stopForegroundService() {
        synchronized (this.mLock) {
            boolean hasForegroundWork = !this.mForegroundWorkMap.isEmpty();
            if (!hasForegroundWork) {
                Intent intent = SystemForegroundDispatcher.createStopForegroundIntent(this.mAppContext);
                try {
                    this.mAppContext.startService(intent);
                } catch (Throwable throwable) {
                    Logger.get().error(TAG, "Unable to stop foreground service", throwable);
                }
                if (this.mForegroundLock != null) {
                    this.mForegroundLock.release();
                    this.mForegroundLock = null;
                }
            }
        }
    }

    private static boolean interrupt(String id, WorkerWrapper wrapper) {
        if (wrapper == null) {
            Logger.get().debug(TAG, String.format("WorkerWrapper could not be found for %s", id), new Throwable[0]);
            return false;
        }
        wrapper.interrupt();
        Logger.get().debug(TAG, String.format("WorkerWrapper interrupted for %s", id), new Throwable[0]);
        return true;
    }

    private static class FutureListener implements Runnable {
        private ExecutionListener mExecutionListener;
        private ListenableFuture<Boolean> mFuture;
        private String mWorkSpecId;

        FutureListener(ExecutionListener executionListener, String workSpecId, ListenableFuture<Boolean> future) {
            this.mExecutionListener = executionListener;
            this.mWorkSpecId = workSpecId;
            this.mFuture = future;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean needsReschedule;
            try {
                needsReschedule = this.mFuture.get().booleanValue();
            } catch (InterruptedException | ExecutionException e) {
                needsReschedule = true;
            }
            this.mExecutionListener.onExecuted(this.mWorkSpecId, needsReschedule);
        }
    }
}
