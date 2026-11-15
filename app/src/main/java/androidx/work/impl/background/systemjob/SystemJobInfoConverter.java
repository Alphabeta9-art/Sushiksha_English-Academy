package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.core.os.BuildCompat;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.ContentUriTriggers;
import androidx.work.Logger;
import androidx.work.NetworkType;
import androidx.work.impl.model.WorkSpec;

/* JADX INFO: loaded from: classes11.dex */
class SystemJobInfoConverter {
    static final String EXTRA_IS_PERIODIC = "EXTRA_IS_PERIODIC";
    static final String EXTRA_WORK_SPEC_ID = "EXTRA_WORK_SPEC_ID";
    private static final String TAG = Logger.tagWithPrefix("SystemJobInfoConverter");
    private final ComponentName mWorkServiceComponent;

    SystemJobInfoConverter(Context context) {
        Context appContext = context.getApplicationContext();
        this.mWorkServiceComponent = new ComponentName(appContext, (Class<?>) SystemJobService.class);
    }

    JobInfo convert(WorkSpec workSpec, int jobId) {
        Constraints constraints = workSpec.constraints;
        PersistableBundle extras = new PersistableBundle();
        extras.putString(EXTRA_WORK_SPEC_ID, workSpec.id);
        extras.putBoolean(EXTRA_IS_PERIODIC, workSpec.isPeriodic());
        JobInfo.Builder builder = new JobInfo.Builder(jobId, this.mWorkServiceComponent).setRequiresCharging(constraints.requiresCharging()).setRequiresDeviceIdle(constraints.requiresDeviceIdle()).setExtras(extras);
        setRequiredNetwork(builder, constraints.getRequiredNetworkType());
        if (!constraints.requiresDeviceIdle()) {
            int backoffPolicy = workSpec.backoffPolicy == BackoffPolicy.LINEAR ? 0 : 1;
            builder.setBackoffCriteria(workSpec.backoffDelayDuration, backoffPolicy);
        }
        long nextRunTime = workSpec.calculateNextRunTime();
        long now = System.currentTimeMillis();
        long offset = Math.max(nextRunTime - now, 0L);
        if (Build.VERSION.SDK_INT <= 28 || offset > 0) {
            builder.setMinimumLatency(offset);
        } else if (!workSpec.expedited) {
            builder.setImportantWhileForeground(true);
        }
        int i = Build.VERSION.SDK_INT;
        if (constraints.hasContentUriTriggers()) {
            ContentUriTriggers contentUriTriggers = constraints.getContentUriTriggers();
            for (ContentUriTriggers.Trigger trigger : contentUriTriggers.getTriggers()) {
                builder.addTriggerContentUri(convertContentUriTrigger(trigger));
            }
            builder.setTriggerContentUpdateDelay(constraints.getTriggerContentUpdateDelay());
            builder.setTriggerContentMaxDelay(constraints.getTriggerMaxContentDelay());
        }
        builder.setPersisted(false);
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setRequiresBatteryNotLow(constraints.requiresBatteryNotLow());
            builder.setRequiresStorageNotLow(constraints.requiresStorageNotLow());
        }
        boolean isRetry = workSpec.runAttemptCount > 0;
        boolean isDelayed = offset > 0;
        if (BuildCompat.isAtLeastS() && workSpec.expedited && !isRetry && !isDelayed) {
            builder.setExpedited(true);
        }
        return builder.build();
    }

    private static JobInfo.TriggerContentUri convertContentUriTrigger(ContentUriTriggers.Trigger trigger) {
        int flag = trigger.shouldTriggerForDescendants() ? 1 : 0;
        return new JobInfo.TriggerContentUri(trigger.getUri(), flag);
    }

    static void setRequiredNetwork(JobInfo.Builder builder, NetworkType networkType) {
        if (Build.VERSION.SDK_INT >= 30 && networkType == NetworkType.TEMPORARILY_UNMETERED) {
            NetworkRequest networkRequest = new NetworkRequest.Builder().addCapability(25).build();
            builder.setRequiredNetwork(networkRequest);
        } else {
            builder.setRequiredNetworkType(convertNetworkType(networkType));
        }
    }

    static int convertNetworkType(NetworkType networkType) {
        switch (networkType) {
            case NOT_REQUIRED:
                return 0;
            case CONNECTED:
                return 1;
            case UNMETERED:
                return 2;
            case NOT_ROAMING:
                int i = Build.VERSION.SDK_INT;
                return 3;
            case METERED:
                if (Build.VERSION.SDK_INT >= 26) {
                    return 4;
                }
                break;
        }
        Logger.get().debug(TAG, String.format("API version too low. Cannot convert network type value %s", networkType), new Throwable[0]);
        return 1;
    }
}
