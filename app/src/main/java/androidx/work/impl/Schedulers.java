package androidx.work.impl;

import android.content.Context;
import android.os.Build;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.PackageManagerHelper;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public class Schedulers {
    public static final String GCM_SCHEDULER = "androidx.work.impl.background.gcm.GcmScheduler";
    private static final String TAG = Logger.tagWithPrefix("Schedulers");

    public static void schedule(Configuration configuration, WorkDatabase workDatabase, List<Scheduler> schedulers) {
        if (schedulers == null || schedulers.size() == 0) {
            return;
        }
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        workDatabase.beginTransaction();
        try {
            List<WorkSpec> eligibleWorkSpecsForLimitedSlots = workSpecDao.getEligibleWorkForScheduling(configuration.getMaxSchedulerLimit());
            List<WorkSpec> allEligibleWorkSpecs = workSpecDao.getAllEligibleWorkSpecsForScheduling(200);
            if (eligibleWorkSpecsForLimitedSlots != null && eligibleWorkSpecsForLimitedSlots.size() > 0) {
                long now = System.currentTimeMillis();
                for (WorkSpec workSpec : eligibleWorkSpecsForLimitedSlots) {
                    workSpecDao.markWorkSpecScheduled(workSpec.id, now);
                }
            }
            workDatabase.setTransactionSuccessful();
            workDatabase.endTransaction();
            if (eligibleWorkSpecsForLimitedSlots != null && eligibleWorkSpecsForLimitedSlots.size() > 0) {
                WorkSpec[] eligibleWorkSpecsArray = new WorkSpec[eligibleWorkSpecsForLimitedSlots.size()];
                WorkSpec[] eligibleWorkSpecsArray2 = (WorkSpec[]) eligibleWorkSpecsForLimitedSlots.toArray(eligibleWorkSpecsArray);
                for (Scheduler scheduler : schedulers) {
                    if (scheduler.hasLimitedSchedulingSlots()) {
                        scheduler.schedule(eligibleWorkSpecsArray2);
                    }
                }
            }
            if (allEligibleWorkSpecs != null && allEligibleWorkSpecs.size() > 0) {
                WorkSpec[] enqueuedWorkSpecsArray = new WorkSpec[allEligibleWorkSpecs.size()];
                WorkSpec[] enqueuedWorkSpecsArray2 = (WorkSpec[]) allEligibleWorkSpecs.toArray(enqueuedWorkSpecsArray);
                for (Scheduler scheduler2 : schedulers) {
                    if (!scheduler2.hasLimitedSchedulingSlots()) {
                        scheduler2.schedule(enqueuedWorkSpecsArray2);
                    }
                }
            }
        } catch (Throwable th) {
            workDatabase.endTransaction();
            throw th;
        }
    }

    static Scheduler createBestAvailableBackgroundScheduler(Context context, WorkManagerImpl workManager) {
        int i = Build.VERSION.SDK_INT;
        Scheduler scheduler = new SystemJobScheduler(context, workManager);
        PackageManagerHelper.setComponentEnabled(context, SystemJobService.class, true);
        Logger.get().debug(TAG, "Created SystemJobScheduler and enabled SystemJobService", new Throwable[0]);
        return scheduler;
    }

    private static Scheduler tryCreateGcmBasedScheduler(Context context) {
        try {
            Class<?> klass = Class.forName(GCM_SCHEDULER);
            Scheduler scheduler = (Scheduler) klass.getConstructor(Context.class).newInstance(context);
            Logger.get().debug(TAG, String.format("Created %s", GCM_SCHEDULER), new Throwable[0]);
            return scheduler;
        } catch (Throwable throwable) {
            Logger.get().debug(TAG, "Unable to create GCM Scheduler", throwable);
            return null;
        }
    }

    private Schedulers() {
    }
}
