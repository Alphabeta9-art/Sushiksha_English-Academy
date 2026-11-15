package androidx.work.impl.constraints.trackers;

import android.content.Context;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

/* JADX INFO: loaded from: classes11.dex */
public class Trackers {
    private static Trackers sInstance;
    private BatteryChargingTracker mBatteryChargingTracker;
    private BatteryNotLowTracker mBatteryNotLowTracker;
    private NetworkStateTracker mNetworkStateTracker;
    private StorageNotLowTracker mStorageNotLowTracker;

    public static synchronized Trackers getInstance(Context context, TaskExecutor taskExecutor) {
        if (sInstance == null) {
            sInstance = new Trackers(context, taskExecutor);
        }
        return sInstance;
    }

    public static synchronized void setInstance(Trackers trackers) {
        sInstance = trackers;
    }

    private Trackers(Context context, TaskExecutor taskExecutor) {
        Context appContext = context.getApplicationContext();
        this.mBatteryChargingTracker = new BatteryChargingTracker(appContext, taskExecutor);
        this.mBatteryNotLowTracker = new BatteryNotLowTracker(appContext, taskExecutor);
        this.mNetworkStateTracker = new NetworkStateTracker(appContext, taskExecutor);
        this.mStorageNotLowTracker = new StorageNotLowTracker(appContext, taskExecutor);
    }

    public BatteryChargingTracker getBatteryChargingTracker() {
        return this.mBatteryChargingTracker;
    }

    public BatteryNotLowTracker getBatteryNotLowTracker() {
        return this.mBatteryNotLowTracker;
    }

    public NetworkStateTracker getNetworkStateTracker() {
        return this.mNetworkStateTracker;
    }

    public StorageNotLowTracker getStorageNotLowTracker() {
        return this.mStorageNotLowTracker;
    }
}
