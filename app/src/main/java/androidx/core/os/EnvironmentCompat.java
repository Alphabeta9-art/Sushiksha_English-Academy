package androidx.core.os;

import android.os.Build;
import android.os.Environment;
import java.io.File;

/* JADX INFO: loaded from: classes10.dex */
public final class EnvironmentCompat {

    @Deprecated
    public static final String MEDIA_UNKNOWN = "unknown";

    public static String getStorageState(File path) {
        int i = Build.VERSION.SDK_INT;
        return Api21Impl.getExternalStorageState(path);
    }

    private EnvironmentCompat() {
    }

    static class Api21Impl {
        private Api21Impl() {
        }

        static String getExternalStorageState(File path) {
            return Environment.getExternalStorageState(path);
        }
    }
}
