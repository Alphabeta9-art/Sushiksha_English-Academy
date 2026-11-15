package com.cloudinary.android.cldvideoplayer.analytics;

import android.content.Context;
import android.content.SharedPreferences;
import com.cloudinary.AuthToken$$ExternalSyntheticBackport0;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.cldvideoplayer.analytics.models.TrackingType;
import com.cloudinary.android.cldvideoplayer.analytics.models.VideoEvent;
import com.cloudinary.android.cldvideoplayer.analytics.models.VideoEventJSONKeys;
import com.cloudinary.android.cldvideoplayer.analytics.models.events.VideoLoadMetadata;
import com.cloudinary.android.cldvideoplayer.analytics.models.events.VideoPauseEvent;
import com.cloudinary.android.cldvideoplayer.analytics.models.events.VideoPlayEvent;
import com.cloudinary.android.cldvideoplayer.analytics.models.events.VideoViewEnd;
import com.cloudinary.android.cldvideoplayer.analytics.models.events.VideoViewStartEvent;
import com.google.common.net.HttpHeaders;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes11.dex */
public class VideoEventsManager {
    private static String CLD_ANALYTICS_ENDPOINT_DEVELOPMENT_URL = "http://10.0.2.2:3001/events";
    public String publicId;
    private SharedPreferences sharedPreferences;
    private final String CLD_ANALYTICS_ENDPOINT_PRODUCTION_URL = "https://video-analytics-api.cloudinary.com/v1/video-analytics";
    public TrackingType trackingType = TrackingType.AUTO;
    public final List<VideoEvent> eventQueue = new ArrayList();
    private String viewId = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    private String userId = getUserId();
    public String cloudName = MediaManager.get().getCloudinary().config.cloudName;

    public VideoEventsManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences("CldVideoPlayerRefs", 0);
    }

    public String getUserId() {
        String storedUserId = this.sharedPreferences.getString("CLDVideoPlayerUserId", null);
        if (storedUserId != null) {
            return storedUserId;
        }
        String newUserId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString("CLDVideoPlayerUserId", newUserId);
        editor.apply();
        return newUserId;
    }

    public void sendViewStartEvent(String videoUrl, Map<String, Object> providedData) {
        Map<String, String> trackingData = new HashMap<>();
        trackingData.put("cloudName", this.cloudName != null ? this.cloudName : "");
        trackingData.put("publicId", this.publicId != null ? this.publicId : "");
        VideoViewStartEvent event = new VideoViewStartEvent(this.trackingType, videoUrl, trackingData, providedData);
        addEventToQueue(event);
    }

    public void sendViewEndEvent(Map<String, Object> providedData) {
        VideoViewEnd event = new VideoViewEnd(this.trackingType, providedData);
        addEventToQueue(event);
    }

    public void sendLoadMetadataEvent(int duration, Map<String, Object> providedData) {
        VideoLoadMetadata event = new VideoLoadMetadata(this.trackingType, duration, providedData);
        addEventToQueue(event);
    }

    public void sendPlayEvent(Map<String, Object> providedData) {
        VideoPlayEvent event = new VideoPlayEvent(this.trackingType, providedData);
        addEventToQueue(event);
    }

    public void sendPauseEvent(Map<String, Object> providedData) {
        VideoPauseEvent event = new VideoPauseEvent(this.trackingType, providedData);
        addEventToQueue(event);
    }

    private void addEventToQueue(VideoEvent event) {
        this.eventQueue.add(event);
    }

    public void sendEvents() {
        if (this.eventQueue.isEmpty()) {
            return;
        }
        List<VideoEvent> eventsToSend = new ArrayList<>(this.eventQueue);
        sendEventToEndpoint(eventsToSend);
        this.eventQueue.removeAll(eventsToSend);
    }

    public void sendEventToEndpoint(final List<VideoEvent> childEvents) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() { // from class: com.cloudinary.android.cldvideoplayer.analytics.VideoEventsManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m8578x7069058e(childEvents);
            }
        });
        executor.shutdown();
    }

    /* JADX INFO: renamed from: lambda$sendEventToEndpoint$0$com-cloudinary-android-cldvideoplayer-analytics-VideoEventsManager, reason: not valid java name */
    /* synthetic */ void m8578x7069058e(List childEvents) {
        try {
            String boundary = "Boundary-" + UUID.randomUUID().toString().toUpperCase();
            URL url = new URL("https://video-analytics-api.cloudinary.com/v1/video-analytics");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "multipart/form-data; boundary=" + boundary);
            Map<String, List<String>> requestHeaders = urlConnection.getRequestProperties();
            for (Map.Entry<String, List<String>> entry : requestHeaders.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();
                for (String value : values) {
                    System.out.println(key + ": " + value);
                }
            }
            String formData = buildFormData(boundary, childEvents, this.viewId, this.userId);
            OutputStream outputStream = urlConnection.getOutputStream();
            try {
                outputStream.write(formData.getBytes());
                if (outputStream != null) {
                    outputStream.close();
                }
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == 200) {
                    System.out.println("Success: " + responseCode);
                } else {
                    System.out.println("Error: " + responseCode);
                }
                urlConnection.disconnect();
            } catch (Throwable th) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String buildFormData(String boundary, List<VideoEvent> events, String viewId, String userId) {
        StringBuilder formData = new StringBuilder();
        try {
            formData.append(buildFormDataPart(boundary, "userId", userId));
            formData.append(buildFormDataPart(boundary, "viewId", viewId));
            formData.append(buildFormDataPart(boundary, "events", buildEventsJson(events)));
            formData.append("--").append(boundary).append("--\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formData.toString();
    }

    private String buildFormDataPart(String boundary, String name, String value) {
        StringBuilder formData = new StringBuilder();
        formData.append("--").append(boundary).append("\r\n");
        formData.append("Content-Disposition: form-data; name=\"").append(name).append("\"\r\n\r\n");
        formData.append(value).append("\r\n");
        return formData.toString();
    }

    private String buildEventsJson(List<VideoEvent> events) {
        List<String> eventObjects = new ArrayList<>();
        for (VideoEvent event : events) {
            Map<String, Object> eventDetails = new HashMap<>();
            eventDetails.put(VideoEventJSONKeys.EVENT_NAME.getValue(), event.eventName);
            eventDetails.put(VideoEventJSONKeys.EVENT_TIME.getValue(), Integer.valueOf(event.eventTime));
            eventDetails.put(VideoEventJSONKeys.EVENT_DETAILS.getValue(), event.eventDetails);
            String eventJson = mapToJson(eventDetails);
            eventObjects.add(eventJson);
        }
        return "[" + AuthToken$$ExternalSyntheticBackport0.m(",", eventObjects) + "]";
    }

    private String mapToJson(Map<String, Object> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (json.length() > 1) {
                json.append(",");
            }
            json.append("\"").append(entry.getKey()).append("\":");
            Object value = entry.getValue();
            if (value instanceof String) {
                json.append("\"").append(escapeString((String) value)).append("\"");
            } else if (value instanceof Map) {
                json.append(mapToJson((Map) value));
            } else {
                json.append(value);
            }
        }
        json.append("}");
        return json.toString();
    }

    private String escapeString(String value) {
        return value.replace("\"", "\\\"");
    }
}
