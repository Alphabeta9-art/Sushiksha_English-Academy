package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import androidx.work.Logger;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
class ConstraintsCommandHandler {
    private static final String TAG = Logger.tagWithPrefix("ConstraintsCmdHandler");
    private final Context mContext;
    private final SystemAlarmDispatcher mDispatcher;
    private final int mStartId;
    private final WorkConstraintsTracker mWorkConstraintsTracker;

    ConstraintsCommandHandler(Context context, int startId, SystemAlarmDispatcher dispatcher) {
        this.mContext = context;
        this.mStartId = startId;
        this.mDispatcher = dispatcher;
        TaskExecutor taskExecutor = this.mDispatcher.getTaskExecutor();
        this.mWorkConstraintsTracker = new WorkConstraintsTracker(this.mContext, taskExecutor, null);
    }

    void handleConstraintsChanged() {
        List<WorkSpec> candidates = this.mDispatcher.getWorkManager().getWorkDatabase().workSpecDao().getScheduledWork();
        ConstraintProxy.updateAll(this.mContext, candidates);
        this.mWorkConstraintsTracker.replace(candidates);
        List<WorkSpec> eligibleWorkSpecs = new ArrayList<>(candidates.size());
        long now = System.currentTimeMillis();
        for (WorkSpec workSpec : candidates) {
            String workSpecId = workSpec.id;
            long triggerAt = workSpec.calculateNextRunTime();
            if (now >= triggerAt && (!workSpec.hasConstraints() || this.mWorkConstraintsTracker.areAllConstraintsMet(workSpecId))) {
                eligibleWorkSpecs.add(workSpec);
            }
        }
        Iterator<WorkSpec> it = eligibleWorkSpecs.iterator();
        while (it.hasNext()) {
            String workSpecId2 = it.next().id;
            Intent intent = CommandHandler.createDelayMetIntent(this.mContext, workSpecId2);
            Logger.get().debug(TAG, String.format("Creating a delay_met command for workSpec with id (%s)", workSpecId2), new Throwable[0]);
            this.mDispatcher.postOnMainThread(new SystemAlarmDispatcher.AddRunnable(this.mDispatcher, intent, this.mStartId));
        }
        this.mWorkConstraintsTracker.reset();
    }
}
