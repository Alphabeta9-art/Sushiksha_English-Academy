package com.cloudinary.android;

import android.content.Context;

/* JADX INFO: loaded from: classes11.dex */
public interface RequestDispatcher {
    int cancelAllRequests();

    boolean cancelRequest(String str);

    String dispatch(UploadRequest uploadRequest);

    void queueRoomFreed();

    String startNow(Context context, UploadRequest uploadRequest);
}
