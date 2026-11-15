package androidx.work.impl.utils;

import android.content.Context;
import android.os.PowerManager;
import androidx.work.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes11.dex */
public class WakeLocks {
    private static final String TAG = Logger.tagWithPrefix("WakeLocks");
    private static final WeakHashMap<PowerManager.WakeLock, String> sWakeLocks = new WeakHashMap<>();

    public static PowerManager.WakeLock newWakeLock(Context context, String tag) {
        PowerManager powerManager = (PowerManager) context.getApplicationContext().getSystemService("power");
        String tagWithPrefix = "WorkManager: " + tag;
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(1, tagWithPrefix);
        synchronized (sWakeLocks) {
            sWakeLocks.put(wakeLock, tagWithPrefix);
        }
        return wakeLock;
    }

    public static void checkWakeLocks() {
        Map<PowerManager.WakeLock, String> wakeLocksCopy = new HashMap<>();
        synchronized (sWakeLocks) {
            wakeLocksCopy.putAll(sWakeLocks);
        }
        for (PowerManager.WakeLock wakeLock : wakeLocksCopy.keySet()) {
            if (wakeLock != null && wakeLock.isHeld()) {
                String message = String.format("WakeLock held for %s", wakeLocksCopy.get(wakeLock));
                Logger.get().warning(TAG, message, new Throwable[0]);
            }
        }
    }

    private WakeLocks() {
    }
}
