package androidx.work.impl.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import androidx.work.Configuration;
import androidx.work.Logger;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public class ProcessUtils {
    private static final String TAG = Logger.tagWithPrefix("ProcessUtils");

    private ProcessUtils() {
    }

    public static boolean isDefaultProcess(Context context, Configuration configuration) {
        String processName = getProcessName(context);
        if (!TextUtils.isEmpty(configuration.getDefaultProcessName())) {
            return TextUtils.equals(processName, configuration.getDefaultProcessName());
        }
        ApplicationInfo info = context.getApplicationInfo();
        return TextUtils.equals(processName, info.processName);
    }

    public static String getProcessName(Context context) {
        List<ActivityManager.RunningAppProcessInfo> processes;
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        try {
            Class<?> activityThread = Class.forName("android.app.ActivityThread", false, ProcessUtils.class.getClassLoader());
            int i = Build.VERSION.SDK_INT;
            Method currentProcessName = activityThread.getDeclaredMethod("currentProcessName", new Class[0]);
            currentProcessName.setAccessible(true);
            Object packageName = currentProcessName.invoke(null, new Object[0]);
            if (packageName instanceof String) {
                return (String) packageName;
            }
        } catch (Throwable exception) {
            Logger.get().debug(TAG, "Unable to check ActivityThread for processName", exception);
        }
        int pid = Process.myPid();
        ActivityManager am = (ActivityManager) context.getSystemService("activity");
        if (am != null && (processes = am.getRunningAppProcesses()) != null && !processes.isEmpty()) {
            for (ActivityManager.RunningAppProcessInfo process : processes) {
                if (process.pid == pid) {
                    return process.processName;
                }
            }
        }
        return null;
    }
}
