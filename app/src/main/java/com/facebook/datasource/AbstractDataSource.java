package com.facebook.datasource;

import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public abstract class AbstractDataSource<T> implements DataSource<T> {

    @Nullable
    private static volatile DataSourceInstrumenter sDataSourceInstrumenter;

    @Nullable
    private Map<String, Object> mExtras;

    @Nullable
    private T mResult = null;

    @Nullable
    private Throwable mFailureThrowable = null;
    private float mProgress = 0.0f;
    private boolean mIsClosed = false;
    private DataSourceStatus mDataSourceStatus = DataSourceStatus.IN_PROGRESS;
    private final ConcurrentLinkedQueue<Pair<DataSubscriber<T>, Executor>> mSubscribers = new ConcurrentLinkedQueue<>();

    public interface DataSourceInstrumenter {
        Runnable decorateRunnable(Runnable runnable, String str);
    }

    private enum DataSourceStatus {
        IN_PROGRESS,
        SUCCESS,
        FAILURE
    }

    public static void provideInstrumenter(@Nullable DataSourceInstrumenter dataSourceInstrumenter) {
        sDataSourceInstrumenter = dataSourceInstrumenter;
    }

    protected AbstractDataSource() {
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized boolean isClosed() {
        return this.mIsClosed;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized boolean isFinished() {
        return this.mDataSourceStatus != DataSourceStatus.IN_PROGRESS;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized boolean hasResult() {
        return this.mResult != null;
    }

    @Override // com.facebook.datasource.DataSource
    @Nullable
    public synchronized T getResult() {
        return this.mResult;
    }

    @Override // com.facebook.datasource.DataSource
    @Nullable
    public Map<String, Object> getExtras() {
        return this.mExtras;
    }

    protected void setExtras(@Nullable Map<String, Object> extras) {
        this.mExtras = extras;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized boolean hasFailed() {
        return this.mDataSourceStatus == DataSourceStatus.FAILURE;
    }

    @Override // com.facebook.datasource.DataSource
    @Nullable
    public synchronized Throwable getFailureCause() {
        return this.mFailureThrowable;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized float getProgress() {
        return this.mProgress;
    }

    @Override // com.facebook.datasource.DataSource
    public boolean close() {
        synchronized (this) {
            if (this.mIsClosed) {
                return false;
            }
            this.mIsClosed = true;
            T resultToClose = this.mResult;
            this.mResult = null;
            if (resultToClose != null) {
                closeResult(resultToClose);
            }
            if (!isFinished()) {
                notifyDataSubscribers();
            }
            synchronized (this) {
                this.mSubscribers.clear();
            }
            return true;
        }
    }

    protected void closeResult(@Nullable T result) {
    }

    @Override // com.facebook.datasource.DataSource
    public void subscribe(DataSubscriber<T> dataSubscriber, Executor executor) {
        Preconditions.checkNotNull(dataSubscriber);
        Preconditions.checkNotNull(executor);
        synchronized (this) {
            if (this.mIsClosed) {
                return;
            }
            if (this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
                this.mSubscribers.add(Pair.create(dataSubscriber, executor));
            }
            boolean shouldNotify = hasResult() || isFinished() || wasCancelled();
            if (shouldNotify) {
                notifyDataSubscriber(dataSubscriber, executor, hasFailed(), wasCancelled());
            }
        }
    }

    private void notifyDataSubscribers() {
        boolean isFailure = hasFailed();
        boolean isCancellation = wasCancelled();
        for (Pair<DataSubscriber<T>, Executor> pair : this.mSubscribers) {
            notifyDataSubscriber((DataSubscriber) pair.first, (Executor) pair.second, isFailure, isCancellation);
        }
    }

    protected void notifyDataSubscriber(final DataSubscriber<T> dataSubscriber, Executor executor, final boolean isFailure, final boolean isCancellation) {
        Runnable runnable = new Runnable() { // from class: com.facebook.datasource.AbstractDataSource.1
            @Override // java.lang.Runnable
            public void run() {
                if (isFailure) {
                    dataSubscriber.onFailure(AbstractDataSource.this);
                } else if (isCancellation) {
                    dataSubscriber.onCancellation(AbstractDataSource.this);
                } else {
                    dataSubscriber.onNewResult(AbstractDataSource.this);
                }
            }
        };
        DataSourceInstrumenter instrumenter = getDataSourceInstrumenter();
        if (instrumenter != null) {
            runnable = instrumenter.decorateRunnable(runnable, "AbstractDataSource_notifyDataSubscriber");
        }
        executor.execute(runnable);
    }

    private synchronized boolean wasCancelled() {
        return isClosed() && !isFinished();
    }

    protected boolean setResult(@Nullable T value, boolean isLast, @Nullable Map<String, Object> extras) {
        setExtras(extras);
        boolean result = setResultInternal(value, isLast);
        if (result) {
            notifyDataSubscribers();
        }
        return result;
    }

    public boolean setResult(@Nullable T value, boolean isLast) {
        return setResult(value, isLast, null);
    }

    protected boolean setFailure(Throwable throwable) {
        return setFailure(throwable, null);
    }

    protected boolean setFailure(@Nullable Throwable throwable, @Nullable Map<String, Object> extras) {
        boolean result = setFailureInternal(throwable, extras);
        if (result) {
            notifyDataSubscribers();
        }
        return result;
    }

    protected boolean setProgress(float progress) {
        boolean result = setProgressInternal(progress);
        if (result) {
            notifyProgressUpdate();
        }
        return result;
    }

    private boolean setResultInternal(@Nullable T value, boolean isLast) {
        T resultToClose = null;
        try {
            synchronized (this) {
                if (!this.mIsClosed && this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
                    if (isLast) {
                        this.mDataSourceStatus = DataSourceStatus.SUCCESS;
                        this.mProgress = 1.0f;
                    }
                    if (this.mResult != value) {
                        resultToClose = this.mResult;
                        this.mResult = value;
                    }
                    if (resultToClose == null) {
                        return true;
                    }
                    closeResult(resultToClose);
                    return true;
                }
                if (value == null) {
                    return false;
                }
                closeResult(value);
                return false;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                closeResult(null);
            }
            throw th;
        }
    }

    private synchronized boolean setFailureInternal(@Nullable Throwable throwable, @Nullable Map<String, Object> extras) {
        if (!this.mIsClosed && this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
            this.mDataSourceStatus = DataSourceStatus.FAILURE;
            this.mFailureThrowable = throwable;
            this.mExtras = extras;
            return true;
        }
        return false;
    }

    private synchronized boolean setProgressInternal(float progress) {
        if (!this.mIsClosed && this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
            if (progress < this.mProgress) {
                return false;
            }
            this.mProgress = progress;
            return true;
        }
        return false;
    }

    protected void notifyProgressUpdate() {
        for (Pair<DataSubscriber<T>, Executor> pair : this.mSubscribers) {
            final DataSubscriber<T> subscriber = (DataSubscriber) pair.first;
            Executor executor = (Executor) pair.second;
            executor.execute(new Runnable() { // from class: com.facebook.datasource.AbstractDataSource.2
                @Override // java.lang.Runnable
                public void run() {
                    subscriber.onProgressUpdate(AbstractDataSource.this);
                }
            });
        }
    }

    @Nullable
    public static DataSourceInstrumenter getDataSourceInstrumenter() {
        return sDataSourceInstrumenter;
    }

    @Override // com.facebook.datasource.DataSource
    public boolean hasMultipleResults() {
        return false;
    }
}
