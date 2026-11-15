package com.cloudinary.android.callback;

/* JADX INFO: loaded from: classes11.dex */
public enum UploadStatus {
    FAILURE,
    SUCCESS,
    RESCHEDULE;

    public boolean isFinal() {
        return this == SUCCESS || this == FAILURE;
    }
}
