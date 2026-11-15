package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class CommandHandler implements ExecutionListener {
    static final String ACTION_CONSTRAINTS_CHANGED = "ACTION_CONSTRAINTS_CHANGED";
    static final String ACTION_DELAY_MET = "ACTION_DELAY_MET";
    static final String ACTION_EXECUTION_COMPLETED = "ACTION_EXECUTION_COMPLETED";
    static final String ACTION_RESCHEDULE = "ACTION_RESCHEDULE";
    static final String ACTION_SCHEDULE_WORK = "ACTION_SCHEDULE_WORK";
    static final String ACTION_STOP_WORK = "ACTION_STOP_WORK";
    private static final String KEY_NEEDS_RESCHEDULE = "KEY_NEEDS_RESCHEDULE";
    private static final String KEY_WORKSPEC_ID = "KEY_WORKSPEC_ID";
    private static final String TAG = Logger.tagWithPrefix("CommandHandler");
    static final long WORK_PROCESSING_TIME_IN_MS = 600000;
    private final Context mContext;
    private final Map<String, ExecutionListener> mPendingDelayMet = new HashMap();
    private final Object mLock = new Object();

    static Intent createScheduleWorkIntent(Context context, String workSpecId) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction(ACTION_SCHEDULE_WORK);
        intent.putExtra(KEY_WORKSPEC_ID, workSpecId);
        return intent;
    }

    static Intent createDelayMetIntent(Context context, String workSpecId) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction(ACTION_DELAY_MET);
        intent.putExtra(KEY_WORKSPEC_ID, workSpecId);
        return intent;
    }

    static Intent createStopWorkIntent(Context context, String workSpecId) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction(ACTION_STOP_WORK);
        intent.putExtra(KEY_WORKSPEC_ID, workSpecId);
        return intent;
    }

    static Intent createConstraintsChangedIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction(ACTION_CONSTRAINTS_CHANGED);
        return intent;
    }

    static Intent createRescheduleIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction(ACTION_RESCHEDULE);
        return intent;
    }

    static Intent createExecutionCompletedIntent(Context context, String workSpecId, boolean needsReschedule) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction(ACTION_EXECUTION_COMPLETED);
        intent.putExtra(KEY_WORKSPEC_ID, workSpecId);
        intent.putExtra(KEY_NEEDS_RESCHEDULE, needsReschedule);
        return intent;
    }

    CommandHandler(Context context) {
        this.mContext = context;
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(String workSpecId, boolean needsReschedule) {
        synchronized (this.mLock) {
            ExecutionListener listener = this.mPendingDelayMet.remove(workSpecId);
            if (listener != null) {
                listener.onExecuted(workSpecId, needsReschedule);
            }
        }
    }

    boolean hasPendingCommands() {
        boolean z;
        synchronized (this.mLock) {
            z = !this.mPendingDelayMet.isEmpty();
        }
        return z;
    }

    void onHandleIntent(Intent intent, int startId, SystemAlarmDispatcher dispatcher) throws Throwable {
        String action = intent.getAction();
        if (ACTION_CONSTRAINTS_CHANGED.equals(action)) {
            handleConstraintsChanged(intent, startId, dispatcher);
            return;
        }
        if (ACTION_RESCHEDULE.equals(action)) {
            handleReschedule(intent, startId, dispatcher);
            return;
        }
        Bundle extras = intent.getExtras();
        if (!hasKeys(extras, KEY_WORKSPEC_ID)) {
            Logger.get().error(TAG, String.format("Invalid request for %s, requires %s.", action, KEY_WORKSPEC_ID), new Throwable[0]);
            return;
        }
        if (ACTION_SCHEDULE_WORK.equals(action)) {
            handleScheduleWorkIntent(intent, startId, dispatcher);
            return;
        }
        if (ACTION_DELAY_MET.equals(action)) {
            handleDelayMet(intent, startId, dispatcher);
            return;
        }
        if (ACTION_STOP_WORK.equals(action)) {
            handleStopWork(intent, dispatcher);
        } else if (ACTION_EXECUTION_COMPLETED.equals(action)) {
            handleExecutionCompleted(intent, startId);
        } else {
            Logger.get().warning(TAG, String.format("Ignoring intent %s", intent), new Throwable[0]);
        }
    }

    private void handleScheduleWorkIntent(Intent intent, int startId, SystemAlarmDispatcher dispatcher) throws Throwable {
        Bundle extras = intent.getExtras();
        String workSpecId = extras.getString(KEY_WORKSPEC_ID);
        Logger.get().debug(TAG, String.format("Handling schedule work for %s", workSpecId), new Throwable[0]);
        WorkManagerImpl workManager = dispatcher.getWorkManager();
        WorkDatabase workDatabase = workManager.getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            WorkSpecDao workSpecDao = workDatabase.workSpecDao();
            WorkSpec workSpec = workSpecDao.getWorkSpec(workSpecId);
            if (workSpec == null) {
                Logger.get().warning(TAG, "Skipping scheduling " + workSpecId + " because it's no longer in the DB", new Throwable[0]);
                workDatabase.endTransaction();
                return;
            }
            if (workSpec.state.isFinished()) {
                Logger.get().warning(TAG, "Skipping scheduling " + workSpecId + "because it is finished.", new Throwable[0]);
                workDatabase.endTransaction();
                return;
            }
            long triggerAt = workSpec.calculateNextRunTime();
            if (workSpec.hasConstraints()) {
                Logger.get().debug(TAG, String.format("Opportunistically setting an alarm for %s at %s", workSpecId, Long.valueOf(triggerAt)), new Throwable[0]);
                Alarms.setAlarm(this.mContext, dispatcher.getWorkManager(), workSpecId, triggerAt);
                Intent constraintsUpdate = createConstraintsChangedIntent(this.mContext);
                try {
                    dispatcher.postOnMainThread(new SystemAlarmDispatcher.AddRunnable(dispatcher, constraintsUpdate, startId));
                } catch (Throwable th) {
                    th = th;
                    workDatabase.endTransaction();
                    throw th;
                }
            } else {
                Logger.get().debug(TAG, String.format("Setting up Alarms for %s at %s", workSpecId, Long.valueOf(triggerAt)), new Throwable[0]);
                Alarms.setAlarm(this.mContext, dispatcher.getWorkManager(), workSpecId, triggerAt);
            }
            workDatabase.setTransactionSuccessful();
            workDatabase.endTransaction();
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void handleDelayMet(Intent intent, int startId, SystemAlarmDispatcher dispatcher) {
        Bundle extras = intent.getExtras();
        synchronized (this.mLock) {
            String workSpecId = extras.getString(KEY_WORKSPEC_ID);
            Logger.get().debug(TAG, String.format("Handing delay met for %s", workSpecId), new Throwable[0]);
            if (!this.mPendingDelayMet.containsKey(workSpecId)) {
                DelayMetCommandHandler delayMetCommandHandler = new DelayMetCommandHandler(this.mContext, startId, workSpecId, dispatcher);
                this.mPendingDelayMet.put(workSpecId, delayMetCommandHandler);
                delayMetCommandHandler.handleProcessWork();
            } else {
                Logger.get().debug(TAG, String.format("WorkSpec %s is already being handled for ACTION_DELAY_MET", workSpecId), new Throwable[0]);
            }
        }
    }

    private void handleStopWork(Intent intent, SystemAlarmDispatcher dispatcher) {
        Bundle extras = intent.getExtras();
        String workSpecId = extras.getString(KEY_WORKSPEC_ID);
        Logger.get().debug(TAG, String.format("Handing stopWork work for %s", workSpecId), new Throwable[0]);
        dispatcher.getWorkManager().stopWork(workSpecId);
        Alarms.cancelAlarm(this.mContext, dispatcher.getWorkManager(), workSpecId);
        dispatcher.onExecuted(workSpecId, false);
    }

    private void handleConstraintsChanged(Intent intent, int startId, SystemAlarmDispatcher dispatcher) {
        Logger.get().debug(TAG, String.format("Handling constraints changed %s", intent), new Throwable[0]);
        ConstraintsCommandHandler changedCommandHandler = new ConstraintsCommandHandler(this.mContext, startId, dispatcher);
        changedCommandHandler.handleConstraintsChanged();
    }

    private void handleReschedule(Intent intent, int startId, SystemAlarmDispatcher dispatcher) {
        Logger.get().debug(TAG, String.format("Handling reschedule %s, %s", intent, Integer.valueOf(startId)), new Throwable[0]);
        dispatcher.getWorkManager().rescheduleEligibleWork();
    }

    private void handleExecutionCompleted(Intent intent, int startId) {
        Bundle extras = intent.getExtras();
        String workSpecId = extras.getString(KEY_WORKSPEC_ID);
        boolean needsReschedule = extras.getBoolean(KEY_NEEDS_RESCHEDULE);
        Logger.get().debug(TAG, String.format("Handling onExecutionCompleted %s, %s", intent, Integer.valueOf(startId)), new Throwable[0]);
        onExecuted(workSpecId, needsReschedule);
    }

    private static boolean hasKeys(Bundle bundle, String... keys) {
        if (bundle == null || bundle.isEmpty()) {
            return false;
        }
        for (String key : keys) {
            if (bundle.get(key) == null) {
                return false;
            }
        }
        return true;
    }
}
