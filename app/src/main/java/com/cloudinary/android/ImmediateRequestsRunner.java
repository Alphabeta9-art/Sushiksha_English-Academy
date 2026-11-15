package com.cloudinary.android;

import android.content.Context;

/* JADX INFO: loaded from: classes11.dex */
public interface ImmediateRequestsRunner {
    int cancelAllRequests();

    boolean cancelRequest(String str);

    void runRequest(Context context, UploadRequest uploadRequest);
}
