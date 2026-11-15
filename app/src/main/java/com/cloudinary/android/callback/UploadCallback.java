package com.cloudinary.android.callback;

import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public interface UploadCallback {
    void onError(String str, ErrorInfo errorInfo);

    void onProgress(String str, long j, long j2);

    void onReschedule(String str, ErrorInfo errorInfo);

    void onStart(String str);

    void onSuccess(String str, Map map);
}
