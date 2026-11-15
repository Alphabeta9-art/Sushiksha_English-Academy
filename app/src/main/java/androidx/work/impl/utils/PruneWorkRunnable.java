package androidx.work.impl.utils;

import androidx.work.Operation;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkSpecDao;

/* JADX INFO: loaded from: classes11.dex */
public class PruneWorkRunnable implements Runnable {
    private final OperationImpl mOperation = new OperationImpl();
    private final WorkManagerImpl mWorkManagerImpl;

    public PruneWorkRunnable(WorkManagerImpl workManagerImpl) {
        this.mWorkManagerImpl = workManagerImpl;
    }

    public Operation getOperation() {
        return this.mOperation;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            WorkDatabase workDatabase = this.mWorkManagerImpl.getWorkDatabase();
            WorkSpecDao workSpecDao = workDatabase.workSpecDao();
            workSpecDao.pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast();
            this.mOperation.setState(Operation.SUCCESS);
        } catch (Throwable exception) {
            this.mOperation.setState(new Operation.State.FAILURE(exception));
        }
    }
}
