package androidx.work.impl.background.systemjob;

import android.app.Application;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.os.PersistableBundle;
import android.text.TextUtils;
import androidx.work.Logger;
import androidx.work.WorkerParameters;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.WorkManagerImpl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class SystemJobService extends JobService implements ExecutionListener {
    private static final String TAG = Logger.tagWithPrefix("SystemJobService");
    private final Map<String, JobParameters> mJobParameters = new HashMap();
    private WorkManagerImpl mWorkManagerImpl;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            this.mWorkManagerImpl = WorkManagerImpl.getInstance(getApplicationContext());
            this.mWorkManagerImpl.getProcessor().addExecutionListener(this);
        } catch (IllegalStateException e) {
            if (!Application.class.equals(getApplication().getClass())) {
                throw new IllegalStateException("WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().");
            }
            Logger.get().warning(TAG, "Could not find WorkManager instance; this may be because an auto-backup is in progress. Ignoring JobScheduler commands for now. Please make sure that you are initializing WorkManager if you have manually disabled WorkManagerInitializer.", new Throwable[0]);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (this.mWorkManagerImpl != null) {
            this.mWorkManagerImpl.getProcessor().removeExecutionListener(this);
        }
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters params) {
        if (this.mWorkManagerImpl == null) {
            Logger.get().debug(TAG, "WorkManager is not initialized; requesting retry.", new Throwable[0]);
            jobFinished(params, true);
            return false;
        }
        String workSpecId = getWorkSpecIdFromJobParameters(params);
        if (TextUtils.isEmpty(workSpecId)) {
            Logger.get().error(TAG, "WorkSpec id not found!", new Throwable[0]);
            return false;
        }
        synchronized (this.mJobParameters) {
            if (this.mJobParameters.containsKey(workSpecId)) {
                Logger.get().debug(TAG, String.format("Job is already being executed by SystemJobService: %s", workSpecId), new Throwable[0]);
                return false;
            }
            Logger.get().debug(TAG, String.format("onStartJob for %s", workSpecId), new Throwable[0]);
            this.mJobParameters.put(workSpecId, params);
            int i = Build.VERSION.SDK_INT;
            WorkerParameters.RuntimeExtras runtimeExtras = new WorkerParameters.RuntimeExtras();
            if (params.getTriggeredContentUris() != null) {
                runtimeExtras.triggeredContentUris = Arrays.asList(params.getTriggeredContentUris());
            }
            if (params.getTriggeredContentAuthorities() != null) {
                runtimeExtras.triggeredContentAuthorities = Arrays.asList(params.getTriggeredContentAuthorities());
            }
            if (Build.VERSION.SDK_INT >= 28) {
                runtimeExtras.network = params.getNetwork();
            }
            this.mWorkManagerImpl.startWork(workSpecId, runtimeExtras);
            return true;
        }
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters params) {
        if (this.mWorkManagerImpl == null) {
            Logger.get().debug(TAG, "WorkManager is not initialized; requesting retry.", new Throwable[0]);
            return true;
        }
        String workSpecId = getWorkSpecIdFromJobParameters(params);
        if (TextUtils.isEmpty(workSpecId)) {
            Logger.get().error(TAG, "WorkSpec id not found!", new Throwable[0]);
            return false;
        }
        Logger.get().debug(TAG, String.format("onStopJob for %s", workSpecId), new Throwable[0]);
        synchronized (this.mJobParameters) {
            this.mJobParameters.remove(workSpecId);
        }
        this.mWorkManagerImpl.stopWork(workSpecId);
        return !this.mWorkManagerImpl.getProcessor().isCancelled(workSpecId);
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(String workSpecId, boolean needsReschedule) {
        JobParameters parameters;
        Logger.get().debug(TAG, String.format("%s executed on JobScheduler", workSpecId), new Throwable[0]);
        synchronized (this.mJobParameters) {
            parameters = this.mJobParameters.remove(workSpecId);
        }
        if (parameters != null) {
            jobFinished(parameters, needsReschedule);
        }
    }

    private static String getWorkSpecIdFromJobParameters(JobParameters parameters) {
        try {
            PersistableBundle extras = parameters.getExtras();
            if (extras != null && extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                return extras.getString("EXTRA_WORK_SPEC_ID");
            }
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
