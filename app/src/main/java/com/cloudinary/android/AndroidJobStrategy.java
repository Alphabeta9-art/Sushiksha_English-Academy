package com.cloudinary.android;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.cloudinary.android.callback.UploadStatus;
import com.cloudinary.android.policy.UploadPolicy;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes11.dex */
public class AndroidJobStrategy implements BackgroundRequestStrategy {
    private static final String JOB_TAG = "CLD";
    private static final Map<String, WeakReference<Thread>> threads = new ConcurrentHashMap();
    private static final Object threadsMapLockObject = new Object();
    private Context context;

    public static OneTimeWorkRequest adapt(UploadRequest<?> request, File payloadFile) {
        UploadPolicy policy = request.getUploadPolicy();
        Constraints.Builder constraintsBuilder = new Constraints.Builder().setRequiredNetworkType(adaptNetworkType(policy.getNetworkType())).setRequiresCharging(policy.isRequiresCharging());
        int i = Build.VERSION.SDK_INT;
        constraintsBuilder.setRequiresDeviceIdle(policy.isRequiresIdle());
        Constraints constraints = constraintsBuilder.build();
        Data inputData = request.buildPayload(payloadFile);
        return new OneTimeWorkRequest.Builder(UploadJob.class).setBackoffCriteria(adaptBackoffPolicy(policy.getBackoffPolicy()), policy.getBackoffMillis(), TimeUnit.MILLISECONDS).setInputData(inputData).setConstraints(constraints).addTag(request.getRequestId()).build();
    }

    private static BackoffPolicy adaptBackoffPolicy(UploadPolicy.BackoffPolicy backoffPolicy) {
        switch (backoffPolicy) {
            case LINEAR:
                return BackoffPolicy.LINEAR;
            default:
                return BackoffPolicy.EXPONENTIAL;
        }
    }

    private static NetworkType adaptNetworkType(UploadPolicy.NetworkType networkType) {
        switch (networkType) {
            case NONE:
                return NetworkType.NOT_REQUIRED;
            case ANY:
                return NetworkType.CONNECTED;
            case UNMETERED:
                return NetworkType.UNMETERED;
            default:
                return NetworkType.NOT_REQUIRED;
        }
    }

    @Override // com.cloudinary.android.BackgroundRequestStrategy
    public void init(Context context) {
        this.context = context;
    }

    @Override // com.cloudinary.android.BackgroundRequestStrategy
    public void doDispatch(UploadRequest request) {
        File cacheDir = this.context.getCacheDir();
        try {
            File payloadFile = File.createTempFile("payload", request.getRequestId(), cacheDir);
            OneTimeWorkRequest uploadWorkRequest = adapt(request, payloadFile);
            WorkManager.getInstance(this.context).beginUniqueWork(request.getRequestId(), ExistingWorkPolicy.KEEP, uploadWorkRequest).enqueue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // com.cloudinary.android.BackgroundRequestStrategy
    public void executeRequestsNow(int howMany) {
    }

    @Override // com.cloudinary.android.BackgroundRequestStrategy
    public boolean cancelRequest(String requestId) {
        Operation operation = WorkManager.getInstance(this.context).cancelAllWorkByTag(requestId);
        killThread(requestId);
        return operation.getResult().isCancelled();
    }

    @Override // com.cloudinary.android.BackgroundRequestStrategy
    public int cancelAllRequests() {
        WorkManager.getInstance(this.context).cancelAllWork();
        return killAllThreads();
    }

    private void killThread(String requestId) {
        synchronized (threadsMapLockObject) {
            WeakReference<Thread> ref = threads.remove(requestId);
            if (ref != null) {
                Thread thread = ref.get();
                if (thread != null) {
                    thread.interrupt();
                }
                ref.clear();
            }
        }
    }

    private int killAllThreads() {
        int size;
        synchronized (threadsMapLockObject) {
            size = threads.size();
            for (String requestId : threads.keySet()) {
                WeakReference<Thread> ref = threads.get(requestId);
                Thread thread = ref.get();
                if (thread != null) {
                    thread.interrupt();
                }
                ref.clear();
            }
            threads.clear();
        }
        return size;
    }

    @Override // com.cloudinary.android.BackgroundRequestStrategy
    public int getPendingImmediateJobsCount() {
        return getJobCountByState(WorkInfo.State.ENQUEUED);
    }

    @Override // com.cloudinary.android.BackgroundRequestStrategy
    public int getRunningJobsCount() {
        return getJobCountByState(WorkInfo.State.RUNNING);
    }

    private int getJobCountByState(WorkInfo.State state) {
        int counter = 0;
        List<WorkInfo> list = WorkManager.getInstance(this.context).getWorkInfosByTagLiveData(JOB_TAG).getValue();
        if (list != null) {
            for (WorkInfo info : list) {
                if (info.getState() == state) {
                    counter++;
                }
            }
            return counter;
        }
        return 0;
    }

    public static final class UploadJob extends Worker {
        private final Context context;
        private String requestId;
        private final WorkerParameters workParams;

        public UploadJob(Context context, WorkerParameters workerParams) {
            super(context, workerParams);
            this.context = context;
            this.workParams = workerParams;
        }

        @Override // androidx.work.ListenableWorker
        public void onStopped() {
            super.onStopped();
            unregisterThread(this.requestId);
        }

        private void registerThread(String requestId, Thread thread) {
            synchronized (AndroidJobStrategy.threadsMapLockObject) {
                AndroidJobStrategy.threads.put(requestId, new WeakReference(thread));
            }
        }

        private void unregisterThread(String requestId) {
            synchronized (AndroidJobStrategy.threadsMapLockObject) {
                if (requestId != null) {
                    WeakReference<Thread> removed = (WeakReference) AndroidJobStrategy.threads.remove(requestId);
                    if (removed != null) {
                        removed.clear();
                    }
                }
            }
        }

        @Override // androidx.work.Worker
        public ListenableWorker.Result doWork() throws Exception {
            String payloadFilePath = this.workParams.getInputData().getString(UploadRequest.PayloadData.KEY);
            if (payloadFilePath == null) {
                return ListenableWorker.Result.failure();
            }
            File payloadFile = new File(payloadFilePath);
            try {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(payloadFile));
                    try {
                        UploadRequest.PayloadData payloadData = (UploadRequest.PayloadData) ois.readObject();
                        AndroidJobRequestParams jobInputData = new AndroidJobRequestParams(payloadData);
                        this.requestId = payloadData.getRequestId();
                        registerThread(this.requestId, Thread.currentThread());
                        UploadStatus result = MediaManager.get().processRequest(this.context, jobInputData);
                        ListenableWorker.Result resultAdaptResult = adaptResult(result);
                        ois.close();
                        unregisterThread(this.requestId);
                        return resultAdaptResult;
                    } catch (Throwable th) {
                        try {
                            ois.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                } catch (IOException | ClassNotFoundException | NullPointerException e) {
                    e.printStackTrace();
                    ListenableWorker.Result resultFailure = ListenableWorker.Result.failure();
                    unregisterThread(this.requestId);
                    return resultFailure;
                }
            } catch (Throwable e2) {
                unregisterThread(this.requestId);
                throw e2;
            }
        }

        private ListenableWorker.Result adaptResult(UploadStatus res) {
            switch (res) {
                case FAILURE:
                    return ListenableWorker.Result.failure();
                case SUCCESS:
                    return ListenableWorker.Result.success();
                case RESCHEDULE:
                    return ListenableWorker.Result.retry();
                default:
                    return ListenableWorker.Result.failure();
            }
        }
    }

    private static final class AndroidJobRequestParams implements RequestParams {
        private final Bundle data;

        private AndroidJobRequestParams(UploadRequest.PayloadData payloadData) {
            this.data = new Bundle();
            this.data.putString("uri", payloadData.getUri());
            this.data.putString("requestId", payloadData.getRequestId());
            this.data.putInt("maxErrorRetries", payloadData.getMaxErrorRetries());
            this.data.putString("options", payloadData.getOptions());
        }

        @Override // com.cloudinary.android.RequestParams
        public void putString(String key, String value) {
            this.data.putString(key, value);
        }

        @Override // com.cloudinary.android.RequestParams
        public void putInt(String key, int value) {
            this.data.putInt(key, value);
        }

        @Override // com.cloudinary.android.RequestParams
        public void putLong(String key, long value) {
            this.data.putLong(key, value);
        }

        @Override // com.cloudinary.android.RequestParams
        public void putBoolean(String key, boolean value) {
            this.data.putBoolean(key, value);
        }

        @Override // com.cloudinary.android.RequestParams
        public String getString(String key, String defaultValue) {
            return this.data.getString(key) != null ? this.data.getString(key) : defaultValue;
        }

        @Override // com.cloudinary.android.RequestParams
        public int getInt(String key, int defaultValue) {
            return this.data.getInt(key, defaultValue);
        }

        @Override // com.cloudinary.android.RequestParams
        public long getLong(String key, long defaultValue) {
            return this.data.getLong(key, defaultValue);
        }

        @Override // com.cloudinary.android.RequestParams
        public boolean getBoolean(String key, boolean defaultValue) {
            return this.data.getBoolean(key, defaultValue);
        }
    }
}
