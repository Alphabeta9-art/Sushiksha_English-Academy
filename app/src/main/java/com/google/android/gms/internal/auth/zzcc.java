package com.google.android.gms.internal.auth;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.os.UserManager;
import android.util.Log;

/* JADX INFO: compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzcc {
    private static UserManager zza;
    private static volatile boolean zzb = !zzb();

    private zzcc() {
    }

    public static boolean zzb() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0050 A[Catch: all -> 0x005c, TryCatch #1 {, blocks: (B:8:0x000e, B:10:0x0012, B:15:0x001a, B:17:0x001e, B:18:0x0028, B:32:0x0054, B:33:0x0056, B:21:0x002e, B:23:0x0034, B:30:0x0050, B:27:0x0041), top: B:43:0x000e, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x0054 A[Catch: all -> 0x005c, TryCatch #1 {, blocks: (B:8:0x000e, B:10:0x0012, B:15:0x001a, B:17:0x001e, B:18:0x0028, B:32:0x0054, B:33:0x0056, B:21:0x002e, B:23:0x0034, B:30:0x0050, B:27:0x0041), top: B:43:0x000e, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x0059 A[RETURN] */
    public static boolean zza(Context context) {
        boolean z;
        if (zzb() && !zzb) {
            synchronized (zzcc.class) {
                if (!zzb) {
                    int i = 1;
                    while (true) {
                        if (i <= 2) {
                            if (zza == null) {
                                zza = (UserManager) context.getSystemService(UserManager.class);
                            }
                            UserManager userManager = zza;
                            if (userManager == null) {
                                z = true;
                            } else {
                                try {
                                    if (userManager.isUserUnlocked() || !userManager.isUserRunning(Process.myUserHandle())) {
                                        z = true;
                                    }
                                    if (z) {
                                        zza = null;
                                    }
                                } catch (NullPointerException e) {
                                    Log.w("DirectBootUtils", "Failed to check if user is unlocked.", e);
                                    zza = null;
                                    i++;
                                }
                            }
                            if (z) {
                                zzb = true;
                            }
                            if (!z) {
                                return true;
                            }
                        }
                        z = false;
                        if (z) {
                            zza = null;
                        }
                        if (z) {
                            zzb = true;
                        }
                        if (!z) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
