package androidx.work.impl.constraints;

import android.content.Context;
import androidx.work.Logger;
import androidx.work.impl.constraints.controllers.BatteryChargingController;
import androidx.work.impl.constraints.controllers.BatteryNotLowController;
import androidx.work.impl.constraints.controllers.ConstraintController;
import androidx.work.impl.constraints.controllers.NetworkConnectedController;
import androidx.work.impl.constraints.controllers.NetworkMeteredController;
import androidx.work.impl.constraints.controllers.NetworkNotRoamingController;
import androidx.work.impl.constraints.controllers.NetworkUnmeteredController;
import androidx.work.impl.constraints.controllers.StorageNotLowController;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public class WorkConstraintsTracker implements ConstraintController.OnConstraintUpdatedCallback {
    private static final String TAG = Logger.tagWithPrefix("WorkConstraintsTracker");
    private final WorkConstraintsCallback mCallback;
    private final ConstraintController<?>[] mConstraintControllers;
    private final Object mLock;

    public WorkConstraintsTracker(Context context, TaskExecutor taskExecutor, WorkConstraintsCallback callback) {
        Context appContext = context.getApplicationContext();
        this.mCallback = callback;
        this.mConstraintControllers = new ConstraintController[]{new BatteryChargingController(appContext, taskExecutor), new BatteryNotLowController(appContext, taskExecutor), new StorageNotLowController(appContext, taskExecutor), new NetworkConnectedController(appContext, taskExecutor), new NetworkUnmeteredController(appContext, taskExecutor), new NetworkNotRoamingController(appContext, taskExecutor), new NetworkMeteredController(appContext, taskExecutor)};
        this.mLock = new Object();
    }

    WorkConstraintsTracker(WorkConstraintsCallback callback, ConstraintController<?>[] controllers) {
        this.mCallback = callback;
        this.mConstraintControllers = controllers;
        this.mLock = new Object();
    }

    public void replace(Iterable<WorkSpec> workSpecs) {
        synchronized (this.mLock) {
            for (ConstraintController<?> controller : this.mConstraintControllers) {
                controller.setCallback(null);
            }
            for (ConstraintController<?> controller2 : this.mConstraintControllers) {
                controller2.replace(workSpecs);
            }
            for (ConstraintController<?> controller3 : this.mConstraintControllers) {
                controller3.setCallback(this);
            }
        }
    }

    public void reset() {
        synchronized (this.mLock) {
            for (ConstraintController<?> controller : this.mConstraintControllers) {
                controller.reset();
            }
        }
    }

    public boolean areAllConstraintsMet(String workSpecId) {
        synchronized (this.mLock) {
            for (ConstraintController<?> constraintController : this.mConstraintControllers) {
                if (constraintController.isWorkSpecConstrained(workSpecId)) {
                    Logger.get().debug(TAG, String.format("Work %s constrained by %s", workSpecId, constraintController.getClass().getSimpleName()), new Throwable[0]);
                    return false;
                }
            }
            return true;
        }
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController.OnConstraintUpdatedCallback
    public void onConstraintMet(List<String> workSpecIds) {
        synchronized (this.mLock) {
            List<String> unconstrainedWorkSpecIds = new ArrayList<>();
            for (String workSpecId : workSpecIds) {
                if (areAllConstraintsMet(workSpecId)) {
                    Logger.get().debug(TAG, String.format("Constraints met for %s", workSpecId), new Throwable[0]);
                    unconstrainedWorkSpecIds.add(workSpecId);
                }
            }
            if (this.mCallback != null) {
                this.mCallback.onAllConstraintsMet(unconstrainedWorkSpecIds);
            }
        }
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController.OnConstraintUpdatedCallback
    public void onConstraintNotMet(List<String> workSpecIds) {
        synchronized (this.mLock) {
            if (this.mCallback != null) {
                this.mCallback.onAllConstraintsNotMet(workSpecIds);
            }
        }
    }
}
