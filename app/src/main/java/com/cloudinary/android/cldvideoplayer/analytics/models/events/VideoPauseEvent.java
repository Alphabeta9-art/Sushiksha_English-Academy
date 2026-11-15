package com.cloudinary.android.cldvideoplayer.analytics.models.events;

import com.cloudinary.android.cldvideoplayer.analytics.models.EventNames;
import com.cloudinary.android.cldvideoplayer.analytics.models.TrackingType;
import com.cloudinary.android.cldvideoplayer.analytics.models.VideoEvent;
import com.cloudinary.android.cldvideoplayer.analytics.models.VideoEventJSONKeys;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class VideoPauseEvent extends VideoEvent {
    public VideoPauseEvent(TrackingType trackingType, Map<String, Object> providedData) {
        super(trackingType, EventNames.PAUSE.getValue(), new HashMap());
        this.eventDetails.put(VideoEventJSONKeys.TRACKING_TYPE.getValue(), trackingType.getValue());
        this.eventDetails.put(VideoEventJSONKeys.CUSTOMER_DATA.getValue(), createCustomerData(null, providedData));
    }
}
