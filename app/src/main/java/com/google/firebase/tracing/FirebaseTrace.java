package com.google.firebase.tracing;

import android.os.Build;
import android.os.Trace;

/* JADX INFO: loaded from: classes12.dex */
public final class FirebaseTrace {
    private FirebaseTrace() {
    }

    public static void pushTrace(String name) {
        int i = Build.VERSION.SDK_INT;
        Trace.beginSection(name);
    }

    public static void popTrace() {
        int i = Build.VERSION.SDK_INT;
        Trace.endSection();
    }
}
