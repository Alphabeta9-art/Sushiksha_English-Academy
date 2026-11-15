package androidx.media3.exoplayer;

import androidx.media3.common.C;
import androidx.media3.common.MediaPeriodId;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.DefaultAllocator;

/* JADX INFO: loaded from: classes11.dex */
public class DefaultLoadControl implements LoadControl {
    public static final int DEFAULT_AUDIO_BUFFER_SIZE = 13107200;
    public static final int DEFAULT_BACK_BUFFER_DURATION_MS = 0;
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS = 5000;
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_MS = 2500;
    public static final int DEFAULT_CAMERA_MOTION_BUFFER_SIZE = 131072;
    public static final int DEFAULT_IMAGE_BUFFER_SIZE = 131072;
    public static final int DEFAULT_MAX_BUFFER_MS = 50000;
    public static final int DEFAULT_METADATA_BUFFER_SIZE = 131072;
    public static final int DEFAULT_MIN_BUFFER_MS = 50000;
    public static final int DEFAULT_MIN_BUFFER_SIZE = 13107200;
    public static final int DEFAULT_MUXED_BUFFER_SIZE = 144310272;
    public static final boolean DEFAULT_PRIORITIZE_TIME_OVER_SIZE_THRESHOLDS = false;
    public static final boolean DEFAULT_RETAIN_BACK_BUFFER_FROM_KEYFRAME = false;
    public static final int DEFAULT_TARGET_BUFFER_BYTES = -1;
    public static final int DEFAULT_TEXT_BUFFER_SIZE = 131072;
    public static final int DEFAULT_VIDEO_BUFFER_SIZE = 131072000;
    private final DefaultAllocator allocator;
    private final long backBufferDurationUs;
    private final long bufferForPlaybackAfterRebufferUs;
    private final long bufferForPlaybackUs;
    private boolean isLoading;
    private final long maxBufferUs;
    private final long minBufferUs;
    private final boolean prioritizeTimeOverSizeThresholds;
    private final boolean retainBackBufferFromKeyframe;
    private int targetBufferBytes;
    private final int targetBufferBytesOverwrite;

    public static final class Builder {
        private DefaultAllocator allocator;
        private boolean buildCalled;
        private int minBufferMs = 50000;
        private int maxBufferMs = 50000;
        private int bufferForPlaybackMs = DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS;
        private int bufferForPlaybackAfterRebufferMs = 5000;
        private int targetBufferBytes = -1;
        private boolean prioritizeTimeOverSizeThresholds = false;
        private int backBufferDurationMs = 0;
        private boolean retainBackBufferFromKeyframe = false;

        public Builder setAllocator(DefaultAllocator allocator) {
            Assertions.checkState(!this.buildCalled);
            this.allocator = allocator;
            return this;
        }

        public Builder setBufferDurationsMs(int minBufferMs, int maxBufferMs, int bufferForPlaybackMs, int bufferForPlaybackAfterRebufferMs) {
            Assertions.checkState(!this.buildCalled);
            DefaultLoadControl.assertGreaterOrEqual(bufferForPlaybackMs, 0, "bufferForPlaybackMs", "0");
            DefaultLoadControl.assertGreaterOrEqual(bufferForPlaybackAfterRebufferMs, 0, "bufferForPlaybackAfterRebufferMs", "0");
            DefaultLoadControl.assertGreaterOrEqual(minBufferMs, bufferForPlaybackMs, "minBufferMs", "bufferForPlaybackMs");
            DefaultLoadControl.assertGreaterOrEqual(minBufferMs, bufferForPlaybackAfterRebufferMs, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
            DefaultLoadControl.assertGreaterOrEqual(maxBufferMs, minBufferMs, "maxBufferMs", "minBufferMs");
            this.minBufferMs = minBufferMs;
            this.maxBufferMs = maxBufferMs;
            this.bufferForPlaybackMs = bufferForPlaybackMs;
            this.bufferForPlaybackAfterRebufferMs = bufferForPlaybackAfterRebufferMs;
            return this;
        }

        public Builder setTargetBufferBytes(int targetBufferBytes) {
            Assertions.checkState(!this.buildCalled);
            this.targetBufferBytes = targetBufferBytes;
            return this;
        }

        public Builder setPrioritizeTimeOverSizeThresholds(boolean prioritizeTimeOverSizeThresholds) {
            Assertions.checkState(!this.buildCalled);
            this.prioritizeTimeOverSizeThresholds = prioritizeTimeOverSizeThresholds;
            return this;
        }

        public Builder setBackBuffer(int backBufferDurationMs, boolean retainBackBufferFromKeyframe) {
            Assertions.checkState(!this.buildCalled);
            DefaultLoadControl.assertGreaterOrEqual(backBufferDurationMs, 0, "backBufferDurationMs", "0");
            this.backBufferDurationMs = backBufferDurationMs;
            this.retainBackBufferFromKeyframe = retainBackBufferFromKeyframe;
            return this;
        }

        public DefaultLoadControl build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            if (this.allocator == null) {
                this.allocator = new DefaultAllocator(true, 65536);
            }
            return new DefaultLoadControl(this.allocator, this.minBufferMs, this.maxBufferMs, this.bufferForPlaybackMs, this.bufferForPlaybackAfterRebufferMs, this.targetBufferBytes, this.prioritizeTimeOverSizeThresholds, this.backBufferDurationMs, this.retainBackBufferFromKeyframe);
        }
    }

    public DefaultLoadControl() {
        this(new DefaultAllocator(true, 65536), 50000, 50000, DEFAULT_BUFFER_FOR_PLAYBACK_MS, 5000, -1, false, 0, false);
    }

    protected DefaultLoadControl(DefaultAllocator allocator, int minBufferMs, int maxBufferMs, int bufferForPlaybackMs, int bufferForPlaybackAfterRebufferMs, int targetBufferBytes, boolean prioritizeTimeOverSizeThresholds, int backBufferDurationMs, boolean retainBackBufferFromKeyframe) {
        int i;
        assertGreaterOrEqual(bufferForPlaybackMs, 0, "bufferForPlaybackMs", "0");
        assertGreaterOrEqual(bufferForPlaybackAfterRebufferMs, 0, "bufferForPlaybackAfterRebufferMs", "0");
        assertGreaterOrEqual(minBufferMs, bufferForPlaybackMs, "minBufferMs", "bufferForPlaybackMs");
        assertGreaterOrEqual(minBufferMs, bufferForPlaybackAfterRebufferMs, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
        assertGreaterOrEqual(maxBufferMs, minBufferMs, "maxBufferMs", "minBufferMs");
        assertGreaterOrEqual(backBufferDurationMs, 0, "backBufferDurationMs", "0");
        this.allocator = allocator;
        this.minBufferUs = Util.msToUs(minBufferMs);
        this.maxBufferUs = Util.msToUs(maxBufferMs);
        this.bufferForPlaybackUs = Util.msToUs(bufferForPlaybackMs);
        this.bufferForPlaybackAfterRebufferUs = Util.msToUs(bufferForPlaybackAfterRebufferMs);
        this.targetBufferBytesOverwrite = targetBufferBytes;
        if (this.targetBufferBytesOverwrite != -1) {
            i = this.targetBufferBytesOverwrite;
        } else {
            i = 13107200;
        }
        this.targetBufferBytes = i;
        this.prioritizeTimeOverSizeThresholds = prioritizeTimeOverSizeThresholds;
        this.backBufferDurationUs = Util.msToUs(backBufferDurationMs);
        this.retainBackBufferFromKeyframe = retainBackBufferFromKeyframe;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onPrepared() {
        reset(false);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onTracksSelected(Timeline timeline, MediaPeriodId mediaPeriodId, Renderer[] renderers, TrackGroupArray trackGroups, ExoTrackSelection[] trackSelections) {
        int iCalculateTargetBufferBytes;
        if (this.targetBufferBytesOverwrite == -1) {
            iCalculateTargetBufferBytes = calculateTargetBufferBytes(renderers, trackSelections);
        } else {
            iCalculateTargetBufferBytes = this.targetBufferBytesOverwrite;
        }
        this.targetBufferBytes = iCalculateTargetBufferBytes;
        this.allocator.setTargetBufferSize(this.targetBufferBytes);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onStopped() {
        reset(true);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public void onReleased() {
        reset(true);
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public Allocator getAllocator() {
        return this.allocator;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public long getBackBufferDurationUs() {
        return this.backBufferDurationUs;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public boolean retainBackBufferFromKeyframe() {
        return this.retainBackBufferFromKeyframe;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public boolean shouldContinueLoading(long playbackPositionUs, long bufferedDurationUs, float playbackSpeed) {
        boolean z = true;
        boolean targetBufferSizeReached = this.allocator.getTotalBytesAllocated() >= this.targetBufferBytes;
        long minBufferUs = this.minBufferUs;
        if (playbackSpeed > 1.0f) {
            long mediaDurationMinBufferUs = Util.getMediaDurationForPlayoutDuration(minBufferUs, playbackSpeed);
            minBufferUs = Math.min(mediaDurationMinBufferUs, this.maxBufferUs);
        }
        if (bufferedDurationUs < Math.max(minBufferUs, 500000L)) {
            if (!this.prioritizeTimeOverSizeThresholds && targetBufferSizeReached) {
                z = false;
            }
            this.isLoading = z;
            if (!this.isLoading && bufferedDurationUs < 500000) {
                Log.w("DefaultLoadControl", "Target buffer size reached with less than 500ms of buffered media data.");
            }
        } else if (bufferedDurationUs >= this.maxBufferUs || targetBufferSizeReached) {
            this.isLoading = false;
        }
        return this.isLoading;
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public boolean shouldStartPlayback(Timeline timeline, MediaPeriodId mediaPeriodId, long bufferedDurationUs, float playbackSpeed, boolean rebuffering, long targetLiveOffsetUs) {
        long bufferedDurationUs2 = Util.getPlayoutDurationForMediaDuration(bufferedDurationUs, playbackSpeed);
        long minBufferDurationUs = rebuffering ? this.bufferForPlaybackAfterRebufferUs : this.bufferForPlaybackUs;
        if (targetLiveOffsetUs != C.TIME_UNSET) {
            minBufferDurationUs = Math.min(targetLiveOffsetUs / 2, minBufferDurationUs);
        }
        return minBufferDurationUs <= 0 || bufferedDurationUs2 >= minBufferDurationUs || (!this.prioritizeTimeOverSizeThresholds && this.allocator.getTotalBytesAllocated() >= this.targetBufferBytes);
    }

    protected int calculateTargetBufferBytes(Renderer[] renderers, ExoTrackSelection[] trackSelectionArray) {
        int targetBufferSize = 0;
        for (int i = 0; i < renderers.length; i++) {
            if (trackSelectionArray[i] != null) {
                targetBufferSize += getDefaultBufferSize(renderers[i].getTrackType());
            }
        }
        return Math.max(13107200, targetBufferSize);
    }

    private void reset(boolean resetAllocator) {
        int i;
        if (this.targetBufferBytesOverwrite == -1) {
            i = 13107200;
        } else {
            i = this.targetBufferBytesOverwrite;
        }
        this.targetBufferBytes = i;
        this.isLoading = false;
        if (resetAllocator) {
            this.allocator.reset();
        }
    }

    private static int getDefaultBufferSize(int trackType) {
        switch (trackType) {
            case -2:
                return 0;
            case -1:
            default:
                throw new IllegalArgumentException();
            case 0:
                return DEFAULT_MUXED_BUFFER_SIZE;
            case 1:
                return 13107200;
            case 2:
                return DEFAULT_VIDEO_BUFFER_SIZE;
            case 3:
                return 131072;
            case 4:
                return 131072;
            case 5:
                return 131072;
            case 6:
                return 131072;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assertGreaterOrEqual(int value1, int value2, String name1, String name2) {
        Assertions.checkArgument(value1 >= value2, name1 + " cannot be less than " + name2);
    }
}
