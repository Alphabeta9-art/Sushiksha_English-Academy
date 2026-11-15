package com.linkedin.android.litr;

import com.linkedin.android.litr.analytics.TrackTransformationInfo;
import java.util.List;

/* JADX INFO: loaded from: classes13.dex */
public interface TransformationListener {
    void onCancelled(String id, List<TrackTransformationInfo> trackTransformationInfos);

    void onCompleted(String id, List<TrackTransformationInfo> trackTransformationInfos);

    void onError(String id, Throwable cause, List<TrackTransformationInfo> trackTransformationInfos);

    void onProgress(String id, float progress);

    void onStarted(String id);
}
