package com.cloudinary.android.cldvideoplayer.analytics.models.events;

import com.cloudinary.android.cldvideoplayer.analytics.models.EventNames;
import com.cloudinary.android.cldvideoplayer.analytics.models.TrackingType;
import com.cloudinary.android.cldvideoplayer.analytics.models.VideoEvent;
import com.cloudinary.android.cldvideoplayer.analytics.models.VideoEventJSONKeys;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class VideoViewStartEvent extends VideoEvent {
    public VideoViewStartEvent(TrackingType trackingType, String videoUrl, Map<String, String> trackingData, Map<String, Object> providedData) {
        super(trackingType, EventNames.VIEW_START.getValue(), new HashMap());
        this.eventDetails.put(VideoEventJSONKeys.TRACKING_TYPE.getValue(), trackingType.getValue());
        this.eventDetails.put(VideoEventJSONKeys.VIDEO_URL.getValue(), videoUrl);
        this.eventDetails.put(VideoEventJSONKeys.CUSTOMER_DATA.getValue(), createCustomerData(trackingData, providedData));
    }
}
