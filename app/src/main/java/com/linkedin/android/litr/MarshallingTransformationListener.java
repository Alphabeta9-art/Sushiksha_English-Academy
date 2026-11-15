package com.linkedin.android.litr;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.linkedin.android.litr.analytics.TrackTransformationInfo;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes13.dex */
class MarshallingTransformationListener {
    private static final int EVENT_CANCELLED = 4;
    private static final int EVENT_COMPLETED = 1;
    private static final int EVENT_ERROR = 2;
    private static final int EVENT_PROGRESS = 3;
    private static final int EVENT_STARTED = 0;
    private static final String KEY_JOB_ID = "jobId";
    private static final String KEY_PROGRESS = "progress";
    private static final String KEY_THROWABLE = "throwable";
    private static final String TAG = MarshallingTransformationListener.class.getSimpleName();
    private Bundle data = new Bundle();
    private final Map<String, Future<?>> futureMap;
    private MarshallingHandler handler;
    private final TransformationListener listener;

    MarshallingTransformationListener(Map<String, Future<?>> futureMap, final TransformationListener listener, Looper looper) {
        this.futureMap = futureMap;
        this.listener = listener;
        if (looper != null) {
            this.handler = new MarshallingHandler(looper, listener);
        }
    }

    void onStarted(String jobId) {
        if (this.handler == null) {
            this.listener.onStarted(jobId);
            return;
        }
        Message msg = Message.obtain(this.handler, 0);
        msg.obj = null;
        this.data.putString(KEY_JOB_ID, jobId);
        msg.setData(this.data);
        msg.sendToTarget();
    }

    void onCompleted(String jobId, final List<TrackTransformationInfo> trackTransformationInfos) {
        this.futureMap.remove(jobId);
        if (this.handler == null) {
            this.listener.onCompleted(jobId, trackTransformationInfos);
            return;
        }
        Message msg = Message.obtain(this.handler, 1);
        msg.obj = trackTransformationInfos;
        this.data.putString(KEY_JOB_ID, jobId);
        msg.setData(this.data);
        msg.sendToTarget();
    }

    void onCancelled(final String jobId, final List<TrackTransformationInfo> trackTransformationInfos) {
        this.futureMap.remove(jobId);
        if (this.handler == null) {
            this.listener.onCancelled(jobId, trackTransformationInfos);
            return;
        }
        Message msg = Message.obtain(this.handler, 4);
        msg.obj = trackTransformationInfos;
        this.data.putString(KEY_JOB_ID, jobId);
        msg.setData(this.data);
        msg.sendToTarget();
    }

    void onError(final String jobId, final Throwable cause, final List<TrackTransformationInfo> trackTransformationInfos) {
        this.futureMap.remove(jobId);
        if (this.handler == null) {
            this.listener.onError(jobId, cause, trackTransformationInfos);
            return;
        }
        Message msg = Message.obtain(this.handler, 2);
        msg.obj = trackTransformationInfos;
        this.data.putString(KEY_JOB_ID, jobId);
        this.data.putSerializable(KEY_THROWABLE, cause);
        msg.setData(this.data);
        msg.sendToTarget();
    }

    void onProgress(String jobId, float progress) {
        if (this.handler == null) {
            this.listener.onProgress(jobId, progress);
            return;
        }
        Message msg = Message.obtain(this.handler, 3);
        msg.obj = null;
        this.data.putString(KEY_JOB_ID, jobId);
        this.data.putFloat("progress", progress);
        msg.setData(this.data);
        msg.sendToTarget();
    }

    private static class MarshallingHandler extends Handler {
        private final TransformationListener listener;

        private MarshallingHandler(Looper looper, TransformationListener listener) {
            super(looper);
            this.listener = listener;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            List<TrackTransformationInfo> trackTransformationInfos = message.obj == null ? null : (List) message.obj;
            Bundle data = message.getData();
            String jobId = data.getString(MarshallingTransformationListener.KEY_JOB_ID);
            if (jobId == null) {
                throw new IllegalArgumentException("Handler message doesn't contain an id!");
            }
            switch (message.what) {
                case 0:
                    this.listener.onStarted(jobId);
                    return;
                case 1:
                    this.listener.onCompleted(jobId, trackTransformationInfos);
                    return;
                case 2:
                    Throwable cause = (Throwable) data.getSerializable(MarshallingTransformationListener.KEY_THROWABLE);
                    this.listener.onError(jobId, cause, trackTransformationInfos);
                    return;
                case 3:
                    float progress = data.getFloat("progress");
                    this.listener.onProgress(jobId, progress);
                    return;
                case 4:
                    this.listener.onCancelled(jobId, trackTransformationInfos);
                    return;
                default:
                    Log.e(MarshallingTransformationListener.TAG, "Unknown event received: " + message.what);
                    return;
            }
        }
    }
}
