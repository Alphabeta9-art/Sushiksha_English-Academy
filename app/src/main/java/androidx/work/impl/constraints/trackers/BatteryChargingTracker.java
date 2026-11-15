package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.work.Logger;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

/* JADX INFO: loaded from: classes11.dex */
public class BatteryChargingTracker extends BroadcastReceiverConstraintTracker<Boolean> {
    private static final String TAG = Logger.tagWithPrefix("BatteryChrgTracker");

    public BatteryChargingTracker(Context context, TaskExecutor taskExecutor) {
        super(context, taskExecutor);
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public Boolean getInitialState() {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        Intent intent = this.mAppContext.registerReceiver(null, intentFilter);
        if (intent == null) {
            Logger.get().error(TAG, "getInitialState - null intent received", new Throwable[0]);
            return null;
        }
        return Boolean.valueOf(isBatteryChangedIntentCharging(intent));
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        int i = Build.VERSION.SDK_INT;
        intentFilter.addAction("android.os.action.CHARGING");
        intentFilter.addAction("android.os.action.DISCHARGING");
        return intentFilter;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:20:0x004e  */
    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public void onBroadcastReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) {
        }
        Logger.get().debug(TAG, String.format("Received %s", action), new Throwable[0]);
        switch (action) {
            case "android.os.action.CHARGING":
                setState(true);
                break;
            case "android.os.action.DISCHARGING":
                setState(false);
                break;
            case "android.intent.action.ACTION_POWER_CONNECTED":
                setState(true);
                break;
            case "android.intent.action.ACTION_POWER_DISCONNECTED":
                setState(false);
                break;
        }
    }

    private boolean isBatteryChangedIntentCharging(Intent intent) {
        int i = Build.VERSION.SDK_INT;
        int status = intent.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
        return status == 2 || status == 5;
    }
}
