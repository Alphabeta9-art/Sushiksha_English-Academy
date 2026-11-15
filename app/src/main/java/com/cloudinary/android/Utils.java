package com.cloudinary.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/* JADX INFO: loaded from: classes11.dex */
public class Utils {
    public static String cloudinaryUrlFromContext(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo info = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if (info == null || info.metaData == null) {
                return "";
            }
            String url = (String) info.metaData.get("CLOUDINARY_URL");
            return url;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }
}
