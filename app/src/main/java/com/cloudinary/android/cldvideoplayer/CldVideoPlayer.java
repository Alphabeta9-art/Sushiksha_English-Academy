package com.cloudinary.android.cldvideoplayer;

import android.content.Context;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import com.cloudinary.Transformation;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.cldvideoplayer.analytics.VideoEventsManager;
import com.cloudinary.android.cldvideoplayer.analytics.models.AnalyticsType;
import com.cloudinary.android.cldvideoplayer.analytics.models.TrackingType;
import java.net.URL;

/* JADX INFO: loaded from: classes11.dex */
public class CldVideoPlayer {
    ExoPlayer player;
    String url;
    VideoEventsManager videoEventsManager;
    boolean viewStartSent = false;
    private boolean analytics = true;

    public CldVideoPlayer(Context context, URL url) {
        this.url = url.toString();
        initPlayer(context, this.url);
    }

    public CldVideoPlayer(Context context, String publicId) {
        initiliaze(context, publicId, null, false);
    }

    public CldVideoPlayer(Context context, String publicId, Transformation transformation) {
        initiliaze(context, publicId, transformation, false);
    }

    public CldVideoPlayer(Context context, String publicId, Transformation transformation, Boolean automaticStreamingProfile) {
        initiliaze(context, publicId, transformation, automaticStreamingProfile);
    }

    public CldVideoPlayer(Context context, String publicId, Boolean automaticStreamingProfile) {
        initiliaze(context, publicId, null, automaticStreamingProfile);
    }

    private void initiliaze(Context context, String publicId, Transformation transformation, Boolean automaticStreamingProfile) {
        MediaManager.get().getCloudinary().analytics.setFeatureFlag("F");
        if (automaticStreamingProfile.booleanValue() && transformation == null) {
            Transformation transformation2 = new Transformation();
            transformation2.streamingProfile("auto");
            this.url = MediaManager.get().url().resourceType(MimeTypes.BASE_TYPE_VIDEO).transformation(transformation2).format("m3u8").generate(publicId);
        } else {
            this.url = MediaManager.get().url().resourceType(MimeTypes.BASE_TYPE_VIDEO).transformation(transformation).generate(publicId);
        }
        MediaManager.get().getCloudinary().analytics.setFeatureFlag("0");
        initPlayer(context, this.url);
    }

    private void initPlayer(Context context, String url) {
        this.videoEventsManager = new VideoEventsManager(context);
        this.player = new ExoPlayer.Builder(context).build();
        this.player.setMediaItem(MediaItem.fromUri(url));
        setListeners();
        this.player.prepare();
    }

    private void setListeners() {
        this.player.addListener(new Player.Listener() { // from class: com.cloudinary.android.cldvideoplayer.CldVideoPlayer.1
            @Override // androidx.media3.common.Player.Listener
            public void onPlaybackStateChanged(int state) {
                super.onPlaybackStateChanged(state);
                if (state == 3 && !CldVideoPlayer.this.viewStartSent && CldVideoPlayer.this.analytics) {
                    CldVideoPlayer.this.viewStartSent = true;
                    CldVideoPlayer.this.videoEventsManager.sendViewStartEvent(CldVideoPlayer.this.url, null);
                    int duration = ((int) CldVideoPlayer.this.player.getDuration()) / 1000;
                    CldVideoPlayer.this.videoEventsManager.sendLoadMetadataEvent(duration, null);
                }
            }

            @Override // androidx.media3.common.Player.Listener
            public void onIsPlayingChanged(boolean isPlaying) {
                super.onIsPlayingChanged(isPlaying);
                if (CldVideoPlayer.this.analytics) {
                    if (isPlaying) {
                        CldVideoPlayer.this.videoEventsManager.sendPlayEvent(null);
                    } else {
                        CldVideoPlayer.this.videoEventsManager.sendPauseEvent(null);
                    }
                }
            }
        });
    }

    public void releasePlayer() {
        if (this.player != null) {
            this.player.release();
            this.player = null;
            if (this.analytics) {
                this.videoEventsManager.sendViewEndEvent(null);
                this.videoEventsManager.sendEvents();
            }
        }
    }

    public void play() {
        if (this.player != null) {
            this.player.play();
        }
    }

    public void setAnalytics(AnalyticsType type, String cloudName, String publicId) {
        switch (type) {
            case AUTO:
                this.analytics = true;
                this.videoEventsManager.trackingType = TrackingType.AUTO;
                this.videoEventsManager.cloudName = cloudName != null ? cloudName : MediaManager.get().getCloudinary().config.cloudName;
                this.videoEventsManager.publicId = publicId != null ? cloudName : "";
                break;
            case MANUAL:
                this.analytics = true;
                this.videoEventsManager.trackingType = TrackingType.MANUAL;
                this.videoEventsManager.cloudName = cloudName != null ? cloudName : MediaManager.get().getCloudinary().config.cloudName;
                this.videoEventsManager.publicId = publicId != null ? cloudName : "";
                break;
            case DISABLED:
                this.analytics = false;
                break;
        }
    }

    public void setPlayer(ExoPlayer player) {
        this.player = player;
    }

    public ExoPlayer getPlayer() {
        return this.player;
    }

    public String getUrl() {
        return this.url;
    }
}
