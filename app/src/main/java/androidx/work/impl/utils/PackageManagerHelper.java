package androidx.work.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.work.Logger;

/* JADX INFO: loaded from: classes11.dex */
public class PackageManagerHelper {
    private static final String TAG = Logger.tagWithPrefix("PackageManagerHelper");

    private PackageManagerHelper() {
    }

    public static void setComponentEnabled(Context context, Class<?> klazz, boolean enabled) {
        int i;
        try {
            PackageManager packageManager = context.getPackageManager();
            ComponentName componentName = new ComponentName(context, klazz.getName());
            if (enabled) {
                i = 1;
            } else {
                i = 2;
            }
            packageManager.setComponentEnabledSetting(componentName, i, 1);
            Logger.get().debug(TAG, String.format("%s %s", klazz.getName(), enabled ? "enabled" : "disabled"), new Throwable[0]);
        } catch (Exception exception) {
            Logger.get().debug(TAG, String.format("%s could not be %s", klazz.getName(), enabled ? "enabled" : "disabled"), exception);
        }
    }

    public static boolean isComponentExplicitlyEnabled(Context context, Class<?> klazz) {
        return isComponentExplicitlyEnabled(context, klazz.getName());
    }

    public static boolean isComponentExplicitlyEnabled(Context context, String className) {
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, className);
        int state = packageManager.getComponentEnabledSetting(componentName);
        return state == 1;
    }
}
