package com.cloudinary.android.cldvideoplayer.analytics.models.events;

import com.cloudinary.android.cldvideoplayer.analytics.models.EventNames;
import com.cloudinary.android.cldvideoplayer.analytics.models.TrackingType;
import com.cloudinary.android.cldvideoplayer.analytics.models.VideoEvent;
import com.cloudinary.android.cldvideoplayer.analytics.models.VideoEventJSONKeys;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class VideoLoadMetadata extends VideoEvent {
    public VideoLoadMetadata(TrackingType trackingType, int duration, Map<String, Object> providedData) {
        super(trackingType, EventNames.LOAD_METADATA.getValue(), new HashMap());
        this.eventDetails.put(VideoEventJSONKeys.TRACKING_TYPE.getValue(), trackingType.getValue());
        this.eventDetails.put(VideoEventJSONKeys.VIDEO_DURATION.getValue(), Integer.valueOf(duration));
        this.eventDetails.put(VideoEventJSONKeys.CUSTOMER_DATA.getValue(), createCustomerData(null, providedData));
    }
}
