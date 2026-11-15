package com.cloudinary.android;

import android.content.Context;

/* JADX INFO: loaded from: classes11.dex */
interface BackgroundRequestStrategy {
    public static final int IMMEDIATE_THRESHOLD = 60000;
    public static final int SOON_THRESHOLD = 1800000;

    int cancelAllRequests();

    boolean cancelRequest(String str);

    void doDispatch(UploadRequest uploadRequest);

    void executeRequestsNow(int i);

    int getPendingImmediateJobsCount();

    int getRunningJobsCount();

    void init(Context context);
}
