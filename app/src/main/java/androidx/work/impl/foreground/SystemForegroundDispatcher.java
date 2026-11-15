package androidx.work.impl.foreground;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.work.ForegroundInfo;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes11.dex */
public class SystemForegroundDispatcher implements WorkConstraintsCallback, ExecutionListener {
    private static final String ACTION_CANCEL_WORK = "ACTION_CANCEL_WORK";
    private static final String ACTION_NOTIFY = "ACTION_NOTIFY";
    private static final String ACTION_START_FOREGROUND = "ACTION_START_FOREGROUND";
    private static final String ACTION_STOP_FOREGROUND = "ACTION_STOP_FOREGROUND";
    private static final String KEY_FOREGROUND_SERVICE_TYPE = "KEY_FOREGROUND_SERVICE_TYPE";
    private static final String KEY_NOTIFICATION = "KEY_NOTIFICATION";
    private static final String KEY_NOTIFICATION_ID = "KEY_NOTIFICATION_ID";
    private static final String KEY_WORKSPEC_ID = "KEY_WORKSPEC_ID";
    static final String TAG = Logger.tagWithPrefix("SystemFgDispatcher");
    private Callback mCallback;
    final WorkConstraintsTracker mConstraintsTracker;
    private Context mContext;
    private final TaskExecutor mTaskExecutor;
    private WorkManagerImpl mWorkManagerImpl;
    final Object mLock = new Object();
    String mCurrentForegroundWorkSpecId = null;
    final Map<String, ForegroundInfo> mForegroundInfoById = new LinkedHashMap();
    final Set<WorkSpec> mTrackedWorkSpecs = new HashSet();
    final Map<String, WorkSpec> mWorkSpecById = new HashMap();

    interface Callback {
        void cancelNotification(int notificationId);

        void notify(int notificationId, Notification notification);

        void startForeground(int notificationId, int notificationType, Notification notification);

        void stop();
    }

    SystemForegroundDispatcher(Context context) {
        this.mContext = context;
        this.mWorkManagerImpl = WorkManagerImpl.getInstance(this.mContext);
        this.mTaskExecutor = this.mWorkManagerImpl.getWorkTaskExecutor();
        this.mConstraintsTracker = new WorkConstraintsTracker(this.mContext, this.mTaskExecutor, this);
        this.mWorkManagerImpl.getProcessor().addExecutionListener(this);
    }

    SystemForegroundDispatcher(Context context, WorkManagerImpl workManagerImpl, WorkConstraintsTracker tracker) {
        this.mContext = context;
        this.mWorkManagerImpl = workManagerImpl;
        this.mTaskExecutor = this.mWorkManagerImpl.getWorkTaskExecutor();
        this.mConstraintsTracker = tracker;
        this.mWorkManagerImpl.getProcessor().addExecutionListener(this);
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(String workSpecId, boolean needsReschedule) {
        Map.Entry<String, ForegroundInfo> entry;
        boolean removed = false;
        synchronized (this.mLock) {
            WorkSpec workSpec = this.mWorkSpecById.remove(workSpecId);
            if (workSpec != null) {
                removed = this.mTrackedWorkSpecs.remove(workSpec);
            }
            if (removed) {
                this.mConstraintsTracker.replace(this.mTrackedWorkSpecs);
            }
        }
        ForegroundInfo removedInfo = this.mForegroundInfoById.remove(workSpecId);
        if (workSpecId.equals(this.mCurrentForegroundWorkSpecId) && this.mForegroundInfoById.size() > 0) {
            Iterator<Map.Entry<String, ForegroundInfo>> iterator = this.mForegroundInfoById.entrySet().iterator();
            Map.Entry<String, ForegroundInfo> entry2 = iterator.next();
            while (true) {
                entry = entry2;
                if (!iterator.hasNext()) {
                    break;
                } else {
                    entry2 = iterator.next();
                }
            }
            this.mCurrentForegroundWorkSpecId = entry.getKey();
            if (this.mCallback != null) {
                ForegroundInfo info = entry.getValue();
                this.mCallback.startForeground(info.getNotificationId(), info.getForegroundServiceType(), info.getNotification());
                this.mCallback.cancelNotification(info.getNotificationId());
            }
        }
        Callback callback = this.mCallback;
        if (removedInfo != null && callback != null) {
            Logger.get().debug(TAG, String.format("Removing Notification (id: %s, workSpecId: %s ,notificationType: %s)", Integer.valueOf(removedInfo.getNotificationId()), workSpecId, Integer.valueOf(removedInfo.getForegroundServiceType())), new Throwable[0]);
            callback.cancelNotification(removedInfo.getNotificationId());
        }
    }

    void setCallback(Callback callback) {
        if (this.mCallback != null) {
            Logger.get().error(TAG, "A callback already exists.", new Throwable[0]);
        } else {
            this.mCallback = callback;
        }
    }

    WorkManagerImpl getWorkManager() {
        return this.mWorkManagerImpl;
    }

    void onStartCommand(Intent intent) {
        String action = intent.getAction();
        if (ACTION_START_FOREGROUND.equals(action)) {
            handleStartForeground(intent);
            handleNotify(intent);
        } else if (ACTION_NOTIFY.equals(action)) {
            handleNotify(intent);
        } else if (ACTION_CANCEL_WORK.equals(action)) {
            handleCancelWork(intent);
        } else if (ACTION_STOP_FOREGROUND.equals(action)) {
            handleStop(intent);
        }
    }

    void onDestroy() {
        this.mCallback = null;
        synchronized (this.mLock) {
            this.mConstraintsTracker.reset();
        }
        this.mWorkManagerImpl.getProcessor().removeExecutionListener(this);
    }

    private void handleStartForeground(Intent intent) {
        Logger.get().info(TAG, String.format("Started foreground service %s", intent), new Throwable[0]);
        final String workSpecId = intent.getStringExtra(KEY_WORKSPEC_ID);
        final WorkDatabase database = this.mWorkManagerImpl.getWorkDatabase();
        this.mTaskExecutor.executeOnBackgroundThread(new Runnable() { // from class: androidx.work.impl.foreground.SystemForegroundDispatcher.1
            @Override // java.lang.Runnable
            public void run() {
                WorkSpec workSpec = database.workSpecDao().getWorkSpec(workSpecId);
                if (workSpec != null && workSpec.hasConstraints()) {
                    synchronized (SystemForegroundDispatcher.this.mLock) {
                        SystemForegroundDispatcher.this.mWorkSpecById.put(workSpecId, workSpec);
                        SystemForegroundDispatcher.this.mTrackedWorkSpecs.add(workSpec);
                        SystemForegroundDispatcher.this.mConstraintsTracker.replace(SystemForegroundDispatcher.this.mTrackedWorkSpecs);
                    }
                }
            }
        });
    }

    private void handleNotify(Intent intent) {
        int notificationId = intent.getIntExtra(KEY_NOTIFICATION_ID, 0);
        int notificationType = intent.getIntExtra(KEY_FOREGROUND_SERVICE_TYPE, 0);
        String workSpecId = intent.getStringExtra(KEY_WORKSPEC_ID);
        Notification notification = (Notification) intent.getParcelableExtra(KEY_NOTIFICATION);
        Logger.get().debug(TAG, String.format("Notifying with (id: %s, workSpecId: %s, notificationType: %s)", Integer.valueOf(notificationId), workSpecId, Integer.valueOf(notificationType)), new Throwable[0]);
        if (notification != null && this.mCallback != null) {
            ForegroundInfo info = new ForegroundInfo(notificationId, notification, notificationType);
            this.mForegroundInfoById.put(workSpecId, info);
            if (TextUtils.isEmpty(this.mCurrentForegroundWorkSpecId)) {
                this.mCurrentForegroundWorkSpecId = workSpecId;
                this.mCallback.startForeground(notificationId, notificationType, notification);
                return;
            }
            this.mCallback.notify(notificationId, notification);
            if (notificationType != 0 && Build.VERSION.SDK_INT >= 29) {
                int foregroundServiceType = 0;
                for (Map.Entry<String, ForegroundInfo> entry : this.mForegroundInfoById.entrySet()) {
                    ForegroundInfo foregroundInfo = entry.getValue();
                    foregroundServiceType |= foregroundInfo.getForegroundServiceType();
                }
                ForegroundInfo currentInfo = this.mForegroundInfoById.get(this.mCurrentForegroundWorkSpecId);
                if (currentInfo != null) {
                    this.mCallback.startForeground(currentInfo.getNotificationId(), foregroundServiceType, currentInfo.getNotification());
                }
            }
        }
    }

    void handleStop(Intent intent) {
        Logger.get().info(TAG, "Stopping foreground service", new Throwable[0]);
        if (this.mCallback != null) {
            this.mCallback.stop();
        }
    }

    private void handleCancelWork(Intent intent) {
        Logger.get().info(TAG, String.format("Stopping foreground work for %s", intent), new Throwable[0]);
        String workSpecId = intent.getStringExtra(KEY_WORKSPEC_ID);
        if (workSpecId != null && !TextUtils.isEmpty(workSpecId)) {
            this.mWorkManagerImpl.cancelWorkById(UUID.fromString(workSpecId));
        }
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsMet(List<String> workSpecIds) {
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsNotMet(List<String> workSpecIds) {
        if (!workSpecIds.isEmpty()) {
            for (String workSpecId : workSpecIds) {
                Logger.get().debug(TAG, String.format("Constraints unmet for WorkSpec %s", workSpecId), new Throwable[0]);
                this.mWorkManagerImpl.stopForegroundWork(workSpecId);
            }
        }
    }

    public static Intent createStartForegroundIntent(Context context, String workSpecId, ForegroundInfo info) {
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction(ACTION_START_FOREGROUND);
        intent.putExtra(KEY_WORKSPEC_ID, workSpecId);
        intent.putExtra(KEY_NOTIFICATION_ID, info.getNotificationId());
        intent.putExtra(KEY_FOREGROUND_SERVICE_TYPE, info.getForegroundServiceType());
        intent.putExtra(KEY_NOTIFICATION, info.getNotification());
        intent.putExtra(KEY_WORKSPEC_ID, workSpecId);
        return intent;
    }

    public static Intent createCancelWorkIntent(Context context, String workSpecId) {
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction(ACTION_CANCEL_WORK);
        intent.setData(Uri.parse(String.format("workspec://%s", workSpecId)));
        intent.putExtra(KEY_WORKSPEC_ID, workSpecId);
        return intent;
    }

    public static Intent createNotifyIntent(Context context, String workSpecId, ForegroundInfo info) {
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction(ACTION_NOTIFY);
        intent.putExtra(KEY_NOTIFICATION_ID, info.getNotificationId());
        intent.putExtra(KEY_FOREGROUND_SERVICE_TYPE, info.getForegroundServiceType());
        intent.putExtra(KEY_NOTIFICATION, info.getNotification());
        intent.putExtra(KEY_WORKSPEC_ID, workSpecId);
        return intent;
    }

    public static Intent createStopForegroundIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction(ACTION_STOP_FOREGROUND);
        return intent;
    }
}
