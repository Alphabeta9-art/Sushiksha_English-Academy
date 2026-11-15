package com.cloudinary.android;

import android.content.Context;
import com.cloudinary.android.callback.UploadStatus;

/* JADX INFO: loaded from: classes11.dex */
interface RequestProcessor {
    UploadStatus processRequest(Context context, RequestParams requestParams);
}
