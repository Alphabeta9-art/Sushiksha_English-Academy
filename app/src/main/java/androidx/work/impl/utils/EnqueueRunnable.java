package androidx.work.impl.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.WorkRequest;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.Scheduler;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.model.Dependency;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTag;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes11.dex */
public class EnqueueRunnable implements Runnable {
    private static final String TAG = Logger.tagWithPrefix("EnqueueRunnable");
    private final OperationImpl mOperation = new OperationImpl();
    private final WorkContinuationImpl mWorkContinuation;

    public EnqueueRunnable(WorkContinuationImpl workContinuation) {
        this.mWorkContinuation = workContinuation;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.mWorkContinuation.hasCycles()) {
                throw new IllegalStateException(String.format("WorkContinuation has cycles (%s)", this.mWorkContinuation));
            }
            boolean needsScheduling = addToDatabase();
            if (needsScheduling) {
                Context context = this.mWorkContinuation.getWorkManagerImpl().getApplicationContext();
                PackageManagerHelper.setComponentEnabled(context, RescheduleReceiver.class, true);
                scheduleWorkInBackground();
            }
            this.mOperation.setState(Operation.SUCCESS);
        } catch (Throwable exception) {
            this.mOperation.setState(new Operation.State.FAILURE(exception));
        }
    }

    public Operation getOperation() {
        return this.mOperation;
    }

    public boolean addToDatabase() {
        WorkManagerImpl workManagerImpl = this.mWorkContinuation.getWorkManagerImpl();
        WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            boolean needsScheduling = processContinuation(this.mWorkContinuation);
            workDatabase.setTransactionSuccessful();
            return needsScheduling;
        } finally {
            workDatabase.endTransaction();
        }
    }

    public void scheduleWorkInBackground() {
        WorkManagerImpl workManager = this.mWorkContinuation.getWorkManagerImpl();
        Schedulers.schedule(workManager.getConfiguration(), workManager.getWorkDatabase(), workManager.getSchedulers());
    }

    private static boolean processContinuation(WorkContinuationImpl workContinuation) {
        boolean needsScheduling = false;
        List<WorkContinuationImpl> parents = workContinuation.getParents();
        if (parents != null) {
            for (WorkContinuationImpl parent : parents) {
                if (!parent.isEnqueued()) {
                    needsScheduling |= processContinuation(parent);
                } else {
                    Logger.get().warning(TAG, String.format("Already enqueued work ids (%s).", TextUtils.join(", ", parent.getIds())), new Throwable[0]);
                }
            }
        }
        return needsScheduling | enqueueContinuation(workContinuation);
    }

    private static boolean enqueueContinuation(WorkContinuationImpl workContinuation) {
        Set<String> prerequisiteIds = WorkContinuationImpl.prerequisitesFor(workContinuation);
        boolean needsScheduling = enqueueWorkWithPrerequisites(workContinuation.getWorkManagerImpl(), workContinuation.getWork(), (String[]) prerequisiteIds.toArray(new String[0]), workContinuation.getName(), workContinuation.getExistingWorkPolicy());
        workContinuation.markEnqueued();
        return needsScheduling;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:108:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:110:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:111:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:114:0x01de  */
    /* JADX WARN: Code duplicated, block: B:115:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:118:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:121:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:123:0x01f8 A[LOOP:4: B:122:0x01f6->B:123:0x01f8, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:125:0x0220  */
    /* JADX WARN: Code duplicated, block: B:129:0x0234 A[LOOP:5: B:127:0x022e->B:129:0x0234, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:131:0x024d  */
    /* JADX WARN: Code duplicated, block: B:145:0x025d A[SYNTHETIC] */
    private static boolean enqueueWorkWithPrerequisites(WorkManagerImpl workManagerImpl, List<? extends WorkRequest> workList, String[] prerequisiteIds, String name, ExistingWorkPolicy existingWorkPolicy) {
        boolean needsScheduling;
        WorkDatabase workDatabase;
        boolean z;
        boolean isNamed;
        Iterator<? extends WorkRequest> it;
        WorkRequest work;
        WorkSpec workSpec;
        String[] prerequisiteIds2;
        Iterator<? extends WorkRequest> it2;
        boolean needsScheduling2;
        int length;
        int i;
        boolean z2;
        String[] prerequisiteIds3 = prerequisiteIds;
        boolean needsScheduling3 = false;
        long currentTimeMillis = System.currentTimeMillis();
        WorkDatabase workDatabase2 = workManagerImpl.getWorkDatabase();
        boolean hasPrerequisite = prerequisiteIds3 != null && prerequisiteIds3.length > 0;
        boolean hasCompletedAllPrerequisites = true;
        boolean hasFailedPrerequisites = false;
        boolean hasCancelledPrerequisites = false;
        if (!hasPrerequisite) {
            needsScheduling = false;
            workDatabase = workDatabase2;
            z = true;
        } else {
            int length2 = prerequisiteIds3.length;
            int i2 = 0;
            while (i2 < length2) {
                String id = prerequisiteIds3[i2];
                WorkSpec prerequisiteWorkSpec = workDatabase2.workSpecDao().getWorkSpec(id);
                if (prerequisiteWorkSpec == null) {
                    Logger.get().error(TAG, String.format("Prerequisite %s doesn't exist; not enqueuing", id), new Throwable[0]);
                    return false;
                }
                boolean needsScheduling4 = needsScheduling3;
                WorkDatabase workDatabase3 = workDatabase2;
                WorkInfo.State prerequisiteState = prerequisiteWorkSpec.state;
                hasCompletedAllPrerequisites &= prerequisiteState == WorkInfo.State.SUCCEEDED;
                if (prerequisiteState == WorkInfo.State.FAILED) {
                    hasFailedPrerequisites = true;
                } else if (prerequisiteState == WorkInfo.State.CANCELLED) {
                    hasCancelledPrerequisites = true;
                }
                i2++;
                needsScheduling3 = needsScheduling4;
                workDatabase2 = workDatabase3;
            }
            needsScheduling = needsScheduling3;
            workDatabase = workDatabase2;
            z = true;
        }
        boolean needsScheduling5 = TextUtils.isEmpty(name);
        boolean isNamed2 = !needsScheduling5;
        boolean shouldApplyExistingWorkPolicy = (!isNamed2 || hasPrerequisite) ? false : z;
        if (shouldApplyExistingWorkPolicy) {
            List<WorkSpec.IdAndState> existingWorkSpecIdAndStates = workDatabase.workSpecDao().getWorkSpecIdAndStatesForName(name);
            if (!existingWorkSpecIdAndStates.isEmpty()) {
                if (existingWorkPolicy == ExistingWorkPolicy.APPEND || existingWorkPolicy == ExistingWorkPolicy.APPEND_OR_REPLACE) {
                    DependencyDao dependencyDao = workDatabase.dependencyDao();
                    List<String> newPrerequisiteIds = new ArrayList<>();
                    for (WorkSpec.IdAndState idAndState : existingWorkSpecIdAndStates) {
                        List<WorkSpec.IdAndState> existingWorkSpecIdAndStates2 = existingWorkSpecIdAndStates;
                        if (!dependencyDao.hasDependents(idAndState.id)) {
                            hasCompletedAllPrerequisites &= idAndState.state == WorkInfo.State.SUCCEEDED ? z : false;
                            if (idAndState.state == WorkInfo.State.FAILED) {
                                hasFailedPrerequisites = true;
                            } else if (idAndState.state == WorkInfo.State.CANCELLED) {
                                hasCancelledPrerequisites = true;
                            }
                            newPrerequisiteIds.add(idAndState.id);
                        }
                        existingWorkSpecIdAndStates = existingWorkSpecIdAndStates2;
                        dependencyDao = dependencyDao;
                    }
                    if (existingWorkPolicy == ExistingWorkPolicy.APPEND_OR_REPLACE && (hasCancelledPrerequisites || hasFailedPrerequisites)) {
                        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
                        List<WorkSpec.IdAndState> idAndStates = workSpecDao.getWorkSpecIdAndStatesForName(name);
                        Iterator<WorkSpec.IdAndState> it3 = idAndStates.iterator();
                        while (it3.hasNext()) {
                            workSpecDao.delete(it3.next().id);
                        }
                        newPrerequisiteIds = Collections.emptyList();
                        hasFailedPrerequisites = false;
                        hasCancelledPrerequisites = false;
                    }
                    prerequisiteIds3 = (String[]) newPrerequisiteIds.toArray(prerequisiteIds3);
                    if (prerequisiteIds3.length <= 0) {
                        z = false;
                    }
                    hasPrerequisite = z;
                    isNamed = needsScheduling;
                } else {
                    if (existingWorkPolicy != ExistingWorkPolicy.KEEP) {
                        z2 = false;
                    } else {
                        for (WorkSpec.IdAndState idAndState2 : existingWorkSpecIdAndStates) {
                            if (idAndState2.state == WorkInfo.State.ENQUEUED || idAndState2.state == WorkInfo.State.RUNNING) {
                                return false;
                            }
                        }
                        z2 = false;
                    }
                    CancelWorkRunnable.forName(name, workManagerImpl, z2).run();
                    WorkSpecDao workSpecDao2 = workDatabase.workSpecDao();
                    Iterator<WorkSpec.IdAndState> it4 = existingWorkSpecIdAndStates.iterator();
                    while (it4.hasNext()) {
                        workSpecDao2.delete(it4.next().id);
                        isNamed2 = isNamed2;
                    }
                    isNamed2 = isNamed2;
                    isNamed = true;
                }
            }
            it = workList.iterator();
            while (it.hasNext()) {
                work = it.next();
                workSpec = work.getWorkSpec();
                if (!hasPrerequisite && !hasCompletedAllPrerequisites) {
                    if (hasFailedPrerequisites) {
                        workSpec.state = WorkInfo.State.FAILED;
                    } else if (hasCancelledPrerequisites) {
                        workSpec.state = WorkInfo.State.CANCELLED;
                    } else {
                        workSpec.state = WorkInfo.State.BLOCKED;
                    }
                } else if (!workSpec.isPeriodic()) {
                    workSpec.periodStartTime = currentTimeMillis;
                } else {
                    workSpec.periodStartTime = 0L;
                }
                int i3 = Build.VERSION.SDK_INT;
                if (Build.VERSION.SDK_INT <= 25) {
                    tryDelegateConstrainedWorkSpec(workSpec);
                } else {
                    int i4 = Build.VERSION.SDK_INT;
                }
                if (workSpec.state == WorkInfo.State.ENQUEUED) {
                    isNamed = true;
                }
                workDatabase.workSpecDao().insertWorkSpec(workSpec);
                if (hasPrerequisite) {
                    prerequisiteIds2 = prerequisiteIds3;
                    it2 = it;
                    needsScheduling2 = isNamed;
                } else {
                    length = prerequisiteIds3.length;
                    i = 0;
                    while (i < length) {
                        String[] prerequisiteIds4 = prerequisiteIds3;
                        String prerequisiteId = prerequisiteIds4[i];
                        Iterator<? extends WorkRequest> it5 = it;
                        boolean needsScheduling6 = isNamed;
                        Dependency dep = new Dependency(work.getStringId(), prerequisiteId);
                        workDatabase.dependencyDao().insertDependency(dep);
                        i++;
                        it = it5;
                        prerequisiteIds3 = prerequisiteIds4;
                        isNamed = needsScheduling6;
                    }
                    prerequisiteIds2 = prerequisiteIds3;
                    it2 = it;
                    needsScheduling2 = isNamed;
                }
                for (String tag : work.getTags()) {
                    workDatabase.workTagDao().insert(new WorkTag(tag, work.getStringId()));
                }
                if (isNamed2) {
                    workDatabase.workNameDao().insert(new WorkName(name, work.getStringId()));
                }
                it = it2;
                prerequisiteIds3 = prerequisiteIds2;
                isNamed = needsScheduling2;
            }
            return isNamed;
        }
        isNamed = needsScheduling;
        it = workList.iterator();
        while (it.hasNext()) {
            work = it.next();
            workSpec = work.getWorkSpec();
            if (!hasPrerequisite) {
                if (!workSpec.isPeriodic()) {
                    workSpec.periodStartTime = currentTimeMillis;
                } else {
                    workSpec.periodStartTime = 0L;
                }
            } else if (!workSpec.isPeriodic()) {
                workSpec.periodStartTime = currentTimeMillis;
            } else {
                workSpec.periodStartTime = 0L;
            }
            int i5 = Build.VERSION.SDK_INT;
            if (Build.VERSION.SDK_INT <= 25) {
                tryDelegateConstrainedWorkSpec(workSpec);
            } else {
                int i6 = Build.VERSION.SDK_INT;
            }
            if (workSpec.state == WorkInfo.State.ENQUEUED) {
                isNamed = true;
            }
            workDatabase.workSpecDao().insertWorkSpec(workSpec);
            if (hasPrerequisite) {
                prerequisiteIds2 = prerequisiteIds3;
                it2 = it;
                needsScheduling2 = isNamed;
            } else {
                length = prerequisiteIds3.length;
                i = 0;
                while (i < length) {
                    String[] prerequisiteIds5 = prerequisiteIds3;
                    String prerequisiteId2 = prerequisiteIds5[i];
                    Iterator<? extends WorkRequest> it6 = it;
                    boolean needsScheduling7 = isNamed;
                    Dependency dep2 = new Dependency(work.getStringId(), prerequisiteId2);
                    workDatabase.dependencyDao().insertDependency(dep2);
                    i++;
                    it = it6;
                    prerequisiteIds3 = prerequisiteIds5;
                    isNamed = needsScheduling7;
                }
                prerequisiteIds2 = prerequisiteIds3;
                it2 = it;
                needsScheduling2 = isNamed;
            }
            while (r0.hasNext()) {
                workDatabase.workTagDao().insert(new WorkTag(tag, work.getStringId()));
            }
            if (isNamed2) {
                workDatabase.workNameDao().insert(new WorkName(name, work.getStringId()));
            }
            it = it2;
            prerequisiteIds3 = prerequisiteIds2;
            isNamed = needsScheduling2;
        }
        return isNamed;
    }

    private static void tryDelegateConstrainedWorkSpec(WorkSpec workSpec) {
        Constraints constraints = workSpec.constraints;
        String workerClassName = workSpec.workerClassName;
        boolean isConstraintTrackingWorker = workerClassName.equals(ConstraintTrackingWorker.class.getName());
        if (!isConstraintTrackingWorker) {
            if (constraints.requiresBatteryNotLow() || constraints.requiresStorageNotLow()) {
                Data.Builder builder = new Data.Builder();
                builder.putAll(workSpec.input).putString(ConstraintTrackingWorker.ARGUMENT_CLASS_NAME, workerClassName);
                workSpec.workerClassName = ConstraintTrackingWorker.class.getName();
                workSpec.input = builder.build();
            }
        }
    }

    private static boolean usesScheduler(WorkManagerImpl workManager, String className) {
        try {
            Class<?> klass = Class.forName(className);
            for (Scheduler scheduler : workManager.getSchedulers()) {
                if (klass.isAssignableFrom(scheduler.getClass())) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
