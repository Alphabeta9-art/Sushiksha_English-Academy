package com.cloudinary.android.callback;

import android.app.Service;
import android.content.Intent;
import com.cloudinary.android.MediaManager;
import com.cloudinary.utils.StringUtils;

/* JADX INFO: loaded from: classes11.dex */
public abstract class ListenerService extends Service implements UploadCallback {
    private static final String TAG = "ListenerService";

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        MediaManager.get().registerCallback(this);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String requestId = intent.getStringExtra(MediaManager.INTENT_EXTRA_REQUEST_ID);
            if (StringUtils.isNotBlank(requestId)) {
                if (MediaManager.ACTION_REQUEST_STARTED.equals(intent.getAction())) {
                    onStart(requestId);
                    return 1;
                }
                if (MediaManager.ACTION_REQUEST_FINISHED.equals(intent.getAction())) {
                    UploadStatus result = (UploadStatus) intent.getSerializableExtra(MediaManager.INTENT_EXTRA_REQUEST_RESULT_STATUS);
                    UploadResult uploadResult = MediaManager.get().popPendingResult(requestId);
                    if (result == UploadStatus.FAILURE) {
                        onError(requestId, uploadResult.getError());
                        return 1;
                    }
                    if (result == UploadStatus.SUCCESS) {
                        onSuccess(requestId, uploadResult.getSuccessResultData());
                        return 1;
                    }
                    return 1;
                }
                return 1;
            }
            return 1;
        }
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        MediaManager.get().unregisterCallback(this);
    }
}
