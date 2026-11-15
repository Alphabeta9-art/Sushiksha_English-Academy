package com.linkedin.android.litr.utils;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

/* JADX INFO: loaded from: classes13.dex */
public class DiskUtil {
    public static final long FREE_DISK_SPACE_CHECK_FAILED = -1;
    private static final String TAG = DiskUtil.class.getSimpleName();

    public long getAvailableDiskSpaceInDataDirectory() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
            int i = Build.VERSION.SDK_INT;
            return statFs.getAvailableBytes();
        } catch (Exception e) {
            Log.e(TAG, "Could not get Available Disk Space");
            return -1L;
        }
    }
}
