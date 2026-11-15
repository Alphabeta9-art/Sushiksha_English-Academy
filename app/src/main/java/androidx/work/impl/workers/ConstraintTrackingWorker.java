package androidx.work.impl.workers;

import android.content.Context;
import android.text.TextUtils;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public class ConstraintTrackingWorker extends ListenableWorker implements WorkConstraintsCallback {
    public static final String ARGUMENT_CLASS_NAME = "androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME";
    private static final String TAG = Logger.tagWithPrefix("ConstraintTrkngWrkr");
    volatile boolean mAreConstraintsUnmet;
    private ListenableWorker mDelegate;
    SettableFuture<ListenableWorker.Result> mFuture;
    final Object mLock;
    private WorkerParameters mWorkerParameters;

    public ConstraintTrackingWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
        this.mWorkerParameters = workerParams;
        this.mLock = new Object();
        this.mAreConstraintsUnmet = false;
        this.mFuture = SettableFuture.create();
    }

    @Override // androidx.work.ListenableWorker
    public ListenableFuture<ListenableWorker.Result> startWork() {
        getBackgroundExecutor().execute(new Runnable() { // from class: androidx.work.impl.workers.ConstraintTrackingWorker.1
            @Override // java.lang.Runnable
            public void run() {
                ConstraintTrackingWorker.this.setupAndRunConstraintTrackingWork();
            }
        });
        return this.mFuture;
    }

    void setupAndRunConstraintTrackingWork() {
        String className = getInputData().getString(ARGUMENT_CLASS_NAME);
        if (TextUtils.isEmpty(className)) {
            Logger.get().error(TAG, "No worker to delegate to.", new Throwable[0]);
            setFutureFailed();
            return;
        }
        this.mDelegate = getWorkerFactory().createWorkerWithDefaultFallback(getApplicationContext(), className, this.mWorkerParameters);
        if (this.mDelegate == null) {
            Logger.get().debug(TAG, "No worker to delegate to.", new Throwable[0]);
            setFutureFailed();
            return;
        }
        WorkDatabase workDatabase = getWorkDatabase();
        WorkSpec workSpec = workDatabase.workSpecDao().getWorkSpec(getId().toString());
        if (workSpec == null) {
            setFutureFailed();
            return;
        }
        WorkConstraintsTracker workConstraintsTracker = new WorkConstraintsTracker(getApplicationContext(), getTaskExecutor(), this);
        workConstraintsTracker.replace(Collections.singletonList(workSpec));
        if (workConstraintsTracker.areAllConstraintsMet(getId().toString())) {
            Logger.get().debug(TAG, String.format("Constraints met for delegate %s", className), new Throwable[0]);
            try {
                final ListenableFuture<ListenableWorker.Result> innerFuture = this.mDelegate.startWork();
                innerFuture.addListener(new Runnable() { // from class: androidx.work.impl.workers.ConstraintTrackingWorker.2
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (ConstraintTrackingWorker.this.mLock) {
                            if (ConstraintTrackingWorker.this.mAreConstraintsUnmet) {
                                ConstraintTrackingWorker.this.setFutureRetry();
                            } else {
                                ConstraintTrackingWorker.this.mFuture.setFuture(innerFuture);
                            }
                        }
                    }
                }, getBackgroundExecutor());
                return;
            } catch (Throwable exception) {
                Logger.get().debug(TAG, String.format("Delegated worker %s threw exception in startWork.", className), exception);
                synchronized (this.mLock) {
                    if (this.mAreConstraintsUnmet) {
                        Logger.get().debug(TAG, "Constraints were unmet, Retrying.", new Throwable[0]);
                        setFutureRetry();
                    } else {
                        setFutureFailed();
                    }
                    return;
                }
            }
        }
        Logger.get().debug(TAG, String.format("Constraints not met for delegate %s. Requesting retry.", className), new Throwable[0]);
        setFutureRetry();
    }

    void setFutureFailed() {
        this.mFuture.set(ListenableWorker.Result.failure());
    }

    void setFutureRetry() {
        this.mFuture.set(ListenableWorker.Result.retry());
    }

    @Override // androidx.work.ListenableWorker
    public void onStopped() {
        super.onStopped();
        if (this.mDelegate != null && !this.mDelegate.isStopped()) {
            this.mDelegate.stop();
        }
    }

    @Override // androidx.work.ListenableWorker
    public boolean isRunInForeground() {
        return this.mDelegate != null && this.mDelegate.isRunInForeground();
    }

    public WorkDatabase getWorkDatabase() {
        return WorkManagerImpl.getInstance(getApplicationContext()).getWorkDatabase();
    }

    @Override // androidx.work.ListenableWorker
    public TaskExecutor getTaskExecutor() {
        return WorkManagerImpl.getInstance(getApplicationContext()).getWorkTaskExecutor();
    }

    public ListenableWorker getDelegate() {
        return this.mDelegate;
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsMet(List<String> workSpecIds) {
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsNotMet(List<String> workSpecIds) {
        Logger.get().debug(TAG, String.format("Constraints changed for %s", workSpecIds), new Throwable[0]);
        synchronized (this.mLock) {
            this.mAreConstraintsUnmet = true;
        }
    }
}
