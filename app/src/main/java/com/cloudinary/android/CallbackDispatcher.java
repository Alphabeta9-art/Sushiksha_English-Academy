package com.cloudinary.android;

import android.content.Context;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.android.callback.UploadResult;
import com.cloudinary.android.callback.UploadStatus;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
interface CallbackDispatcher {
    void dispatchError(Context context, String str, ErrorInfo errorInfo);

    void dispatchProgress(String str, long j, long j2);

    void dispatchReschedule(Context context, String str, ErrorInfo errorInfo);

    void dispatchStart(String str);

    void dispatchSuccess(Context context, String str, Map map);

    UploadResult popPendingResult(String str);

    void registerCallback(UploadCallback uploadCallback);

    void registerCallback(String str, UploadCallback uploadCallback);

    void unregisterCallback(UploadCallback uploadCallback);

    void wakeListenerServiceWithRequestFinished(Context context, String str, UploadStatus uploadStatus);

    void wakeListenerServiceWithRequestStart(Context context, String str);
}
