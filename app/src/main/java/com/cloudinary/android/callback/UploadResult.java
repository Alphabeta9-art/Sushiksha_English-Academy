package com.cloudinary.android.callback;

import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class UploadResult {
    private final ErrorInfo error;
    private final Map successResultData;

    public UploadResult(Map successResultData, ErrorInfo error) {
        this.successResultData = successResultData;
        this.error = error;
    }

    public Map getSuccessResultData() {
        return this.successResultData;
    }

    public ErrorInfo getError() {
        return this.error;
    }
}
