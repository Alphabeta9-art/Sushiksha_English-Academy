package androidx.media3.exoplayer;

import androidx.media3.common.MediaPeriodId;
import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;

/* JADX INFO: loaded from: classes11.dex */
public interface LoadControl {

    @Deprecated
    public static final MediaPeriodId EMPTY_MEDIA_PERIOD_ID = new MediaPeriodId(new Object());

    Allocator getAllocator();

    long getBackBufferDurationUs();

    void onPrepared();

    void onReleased();

    void onStopped();

    boolean retainBackBufferFromKeyframe();

    boolean shouldContinueLoading(long j, long j2, float f);

    default void onTracksSelected(Timeline timeline, MediaPeriodId mediaPeriodId, Renderer[] renderers, TrackGroupArray trackGroups, ExoTrackSelection[] trackSelections) {
        onTracksSelected(renderers, trackGroups, trackSelections);
    }

    @Deprecated
    default void onTracksSelected(Renderer[] renderers, TrackGroupArray trackGroups, ExoTrackSelection[] trackSelections) {
        onTracksSelected(Timeline.EMPTY, EMPTY_MEDIA_PERIOD_ID, renderers, trackGroups, trackSelections);
    }

    default boolean shouldStartPlayback(Timeline timeline, MediaPeriodId mediaPeriodId, long bufferedDurationUs, float playbackSpeed, boolean rebuffering, long targetLiveOffsetUs) {
        return shouldStartPlayback(bufferedDurationUs, playbackSpeed, rebuffering, targetLiveOffsetUs);
    }

    @Deprecated
    default boolean shouldStartPlayback(long bufferedDurationUs, float playbackSpeed, boolean rebuffering, long targetLiveOffsetUs) {
        return shouldStartPlayback(Timeline.EMPTY, EMPTY_MEDIA_PERIOD_ID, bufferedDurationUs, playbackSpeed, rebuffering, targetLiveOffsetUs);
    }
}
