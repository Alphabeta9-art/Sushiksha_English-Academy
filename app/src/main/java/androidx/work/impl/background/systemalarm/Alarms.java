package androidx.work.impl.background.systemalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.work.Logger;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.utils.IdGenerator;

/* JADX INFO: loaded from: classes11.dex */
class Alarms {
    private static final String TAG = Logger.tagWithPrefix("Alarms");

    public static void setAlarm(Context context, WorkManagerImpl workManager, String workSpecId, long triggerAtMillis) {
        WorkDatabase workDatabase = workManager.getWorkDatabase();
        SystemIdInfoDao systemIdInfoDao = workDatabase.systemIdInfoDao();
        SystemIdInfo systemIdInfo = systemIdInfoDao.getSystemIdInfo(workSpecId);
        if (systemIdInfo != null) {
            cancelExactAlarm(context, workSpecId, systemIdInfo.systemId);
            setExactAlarm(context, workSpecId, systemIdInfo.systemId, triggerAtMillis);
            return;
        }
        IdGenerator idGenerator = new IdGenerator(workDatabase);
        int alarmId = idGenerator.nextAlarmManagerId();
        SystemIdInfo newSystemIdInfo = new SystemIdInfo(workSpecId, alarmId);
        systemIdInfoDao.insertSystemIdInfo(newSystemIdInfo);
        setExactAlarm(context, workSpecId, alarmId, triggerAtMillis);
    }

    public static void cancelAlarm(Context context, WorkManagerImpl workManager, String workSpecId) {
        WorkDatabase workDatabase = workManager.getWorkDatabase();
        SystemIdInfoDao systemIdInfoDao = workDatabase.systemIdInfoDao();
        SystemIdInfo systemIdInfo = systemIdInfoDao.getSystemIdInfo(workSpecId);
        if (systemIdInfo != null) {
            cancelExactAlarm(context, workSpecId, systemIdInfo.systemId);
            Logger.get().debug(TAG, String.format("Removing SystemIdInfo for workSpecId (%s)", workSpecId), new Throwable[0]);
            systemIdInfoDao.removeSystemIdInfo(workSpecId);
        }
    }

    private static void cancelExactAlarm(Context context, String workSpecId, int alarmId) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent delayMet = CommandHandler.createDelayMetIntent(context, workSpecId);
        int i = Build.VERSION.SDK_INT;
        int flags = 536870912 | AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
        PendingIntent pendingIntent = PendingIntent.getService(context, alarmId, delayMet, flags);
        if (pendingIntent != null && alarmManager != null) {
            Logger.get().debug(TAG, String.format("Cancelling existing alarm with (workSpecId, systemId) (%s, %s)", workSpecId, Integer.valueOf(alarmId)), new Throwable[0]);
            alarmManager.cancel(pendingIntent);
        }
    }

    private static void setExactAlarm(Context context, String workSpecId, int alarmId, long triggerAtMillis) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        int i = Build.VERSION.SDK_INT;
        int flags = 134217728 | AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
        Intent delayMet = CommandHandler.createDelayMetIntent(context, workSpecId);
        PendingIntent pendingIntent = PendingIntent.getService(context, alarmId, delayMet, flags);
        if (alarmManager != null) {
            int i2 = Build.VERSION.SDK_INT;
            alarmManager.setExact(0, triggerAtMillis, pendingIntent);
        }
    }

    private Alarms() {
    }
}
