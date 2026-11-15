package androidx.work.impl.workers;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes11.dex */
public class DiagnosticsWorker extends Worker {
    private static final String TAG = Logger.tagWithPrefix("DiagnosticsWrkr");

    public DiagnosticsWorker(Context context, WorkerParameters parameters) {
        super(context, parameters);
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        WorkManagerImpl workManager = WorkManagerImpl.getInstance(getApplicationContext());
        WorkDatabase database = workManager.getWorkDatabase();
        WorkSpecDao workSpecDao = database.workSpecDao();
        WorkNameDao workNameDao = database.workNameDao();
        WorkTagDao workTagDao = database.workTagDao();
        SystemIdInfoDao systemIdInfoDao = database.systemIdInfoDao();
        long startAt = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1L);
        List<WorkSpec> completed = workSpecDao.getRecentlyCompletedWork(startAt);
        List<WorkSpec> running = workSpecDao.getRunningWork();
        List<WorkSpec> enqueued = workSpecDao.getAllEligibleWorkSpecsForScheduling(200);
        if (completed != null && !completed.isEmpty()) {
            Logger.get().info(TAG, "Recently completed work:\n\n", new Throwable[0]);
            Logger.get().info(TAG, workSpecRows(workNameDao, workTagDao, systemIdInfoDao, completed), new Throwable[0]);
        }
        if (running != null && !running.isEmpty()) {
            Logger.get().info(TAG, "Running work:\n\n", new Throwable[0]);
            Logger.get().info(TAG, workSpecRows(workNameDao, workTagDao, systemIdInfoDao, running), new Throwable[0]);
        }
        if (enqueued != null && !enqueued.isEmpty()) {
            Logger.get().info(TAG, "Enqueued work:\n\n", new Throwable[0]);
            Logger.get().info(TAG, workSpecRows(workNameDao, workTagDao, systemIdInfoDao, enqueued), new Throwable[0]);
        }
        return ListenableWorker.Result.success();
    }

    private static String workSpecRows(WorkNameDao workNameDao, WorkTagDao workTagDao, SystemIdInfoDao systemIdInfoDao, List<WorkSpec> workSpecs) {
        StringBuilder sb = new StringBuilder();
        int i = Build.VERSION.SDK_INT;
        String header = String.format("\n Id \t Class Name\t %s\t State\t Unique Name\t Tags\t", "Job Id");
        sb.append(header);
        for (WorkSpec workSpec : workSpecs) {
            Integer systemId = null;
            SystemIdInfo info = systemIdInfoDao.getSystemIdInfo(workSpec.id);
            if (info != null) {
                systemId = Integer.valueOf(info.systemId);
            }
            List<String> names = workNameDao.getNamesForWorkSpecId(workSpec.id);
            List<String> tags = workTagDao.getTagsForWorkSpecId(workSpec.id);
            sb.append(workSpecRow(workSpec, TextUtils.join(",", names), systemId, TextUtils.join(",", tags)));
        }
        return sb.toString();
    }

    private static String workSpecRow(WorkSpec workSpec, String name, Integer systemId, String tags) {
        return String.format("\n%s\t %s\t %s\t %s\t %s\t %s\t", workSpec.id, workSpec.workerClassName, systemId, workSpec.state.name(), name, tags);
    }
}
