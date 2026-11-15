package androidx.media3.common.util;

import android.os.Bundle;
import android.os.IBinder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes11.dex */
public final class BundleUtil {
    private static final String TAG = "BundleUtil";
    private static Method getIBinderMethod;
    private static Method putIBinderMethod;

    public static IBinder getBinder(Bundle bundle, String key) {
        if (Util.SDK_INT >= 18) {
            return bundle.getBinder(key);
        }
        return getBinderByReflection(bundle, key);
    }

    public static void putBinder(Bundle bundle, String key, IBinder binder) {
        if (Util.SDK_INT >= 18) {
            bundle.putBinder(key, binder);
        } else {
            putBinderByReflection(bundle, key, binder);
        }
    }

    private static IBinder getBinderByReflection(Bundle bundle, String key) {
        Method getIBinder = getIBinderMethod;
        if (getIBinder == null) {
            try {
                getIBinderMethod = Bundle.class.getMethod("getIBinder", String.class);
                getIBinderMethod.setAccessible(true);
                getIBinder = getIBinderMethod;
            } catch (NoSuchMethodException e) {
                Log.i(TAG, "Failed to retrieve getIBinder method", e);
                return null;
            }
        }
        try {
            return (IBinder) getIBinder.invoke(bundle, key);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
            Log.i(TAG, "Failed to invoke getIBinder via reflection", e2);
            return null;
        }
    }

    private static void putBinderByReflection(Bundle bundle, String key, IBinder binder) {
        Method putIBinder = putIBinderMethod;
        if (putIBinder == null) {
            try {
                putIBinderMethod = Bundle.class.getMethod("putIBinder", String.class, IBinder.class);
                putIBinderMethod.setAccessible(true);
                putIBinder = putIBinderMethod;
            } catch (NoSuchMethodException e) {
                Log.i(TAG, "Failed to retrieve putIBinder method", e);
                return;
            }
        }
        try {
            putIBinder.invoke(bundle, key, binder);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
            Log.i(TAG, "Failed to invoke putIBinder via reflection", e2);
        }
    }

    private BundleUtil() {
    }
}
