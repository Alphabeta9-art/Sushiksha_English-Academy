package androidx.media3.exoplayer.video;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;

/* JADX INFO: loaded from: classes11.dex */
public final class VideoFrameReleaseHelper {
    private static final long MAX_ALLOWED_ADJUSTMENT_NS = 20000000;
    private static final int MINIMUM_FRAMES_WITHOUT_SYNC_TO_CLEAR_SURFACE_FRAME_RATE = 30;
    private static final long MINIMUM_MATCHING_FRAME_DURATION_FOR_HIGH_CONFIDENCE_NS = 5000000000L;
    private static final float MINIMUM_MEDIA_FRAME_RATE_CHANGE_FOR_UPDATE_HIGH_CONFIDENCE = 0.02f;
    private static final float MINIMUM_MEDIA_FRAME_RATE_CHANGE_FOR_UPDATE_LOW_CONFIDENCE = 1.0f;
    private static final String TAG = "VideoFrameReleaseHelper";
    private static final long VSYNC_OFFSET_PERCENTAGE = 80;
    private static final long VSYNC_SAMPLE_UPDATE_PERIOD_MS = 500;
    private int changeFrameRateStrategy;
    private final DisplayHelper displayHelper;
    private float formatFrameRate;
    private long frameIndex;
    private final FixedFrameRateEstimator frameRateEstimator = new FixedFrameRateEstimator();
    private long lastAdjustedFrameIndex;
    private long lastAdjustedReleaseTimeNs;
    private long pendingLastAdjustedFrameIndex;
    private long pendingLastAdjustedReleaseTimeNs;
    private float playbackSpeed;
    private boolean started;
    private Surface surface;
    private float surfaceMediaFrameRate;
    private float surfacePlaybackFrameRate;
    private long vsyncDurationNs;
    private long vsyncOffsetNs;
    private final VSyncSampler vsyncSampler;

    /* JADX INFO: Access modifiers changed from: private */
    interface DisplayHelper {

        public interface Listener {
            void onDefaultDisplayChanged(Display display);
        }

        void register(Listener listener);

        void unregister();
    }

    public VideoFrameReleaseHelper(Context context) {
        this.displayHelper = maybeBuildDisplayHelper(context);
        this.vsyncSampler = this.displayHelper != null ? VSyncSampler.getInstance() : null;
        this.vsyncDurationNs = C.TIME_UNSET;
        this.vsyncOffsetNs = C.TIME_UNSET;
        this.formatFrameRate = -1.0f;
        this.playbackSpeed = 1.0f;
        this.changeFrameRateStrategy = 0;
    }

    public void setChangeFrameRateStrategy(int changeFrameRateStrategy) {
        if (this.changeFrameRateStrategy == changeFrameRateStrategy) {
            return;
        }
        this.changeFrameRateStrategy = changeFrameRateStrategy;
        updateSurfacePlaybackFrameRate(true);
    }

    public void onStarted() {
        this.started = true;
        resetAdjustment();
        if (this.displayHelper != null) {
            ((VSyncSampler) Assertions.checkNotNull(this.vsyncSampler)).addObserver();
            this.displayHelper.register(new DisplayHelper.Listener() { // from class: androidx.media3.exoplayer.video.VideoFrameReleaseHelper$$ExternalSyntheticLambda0
                @Override // androidx.media3.exoplayer.video.VideoFrameReleaseHelper.DisplayHelper.Listener
                public final void onDefaultDisplayChanged(Display display) {
                    this.f$0.updateDefaultDisplayRefreshRateParams(display);
                }
            });
        }
        updateSurfacePlaybackFrameRate(false);
    }

    public void onSurfaceChanged(Surface surface) {
        if (surface instanceof PlaceholderSurface) {
            surface = null;
        }
        if (this.surface == surface) {
            return;
        }
        clearSurfaceFrameRate();
        this.surface = surface;
        updateSurfacePlaybackFrameRate(true);
    }

    public void onPositionReset() {
        resetAdjustment();
    }

    public void onPlaybackSpeed(float playbackSpeed) {
        this.playbackSpeed = playbackSpeed;
        resetAdjustment();
        updateSurfacePlaybackFrameRate(false);
    }

    public void onFormatChanged(float formatFrameRate) {
        this.formatFrameRate = formatFrameRate;
        this.frameRateEstimator.reset();
        updateSurfaceMediaFrameRate();
    }

    public void onNextFrame(long framePresentationTimeUs) {
        if (this.pendingLastAdjustedFrameIndex != -1) {
            this.lastAdjustedFrameIndex = this.pendingLastAdjustedFrameIndex;
            this.lastAdjustedReleaseTimeNs = this.pendingLastAdjustedReleaseTimeNs;
        }
        this.frameIndex++;
        this.frameRateEstimator.onNextFrame(1000 * framePresentationTimeUs);
        updateSurfaceMediaFrameRate();
    }

    public void onStopped() {
        this.started = false;
        if (this.displayHelper != null) {
            this.displayHelper.unregister();
            ((VSyncSampler) Assertions.checkNotNull(this.vsyncSampler)).removeObserver();
        }
        clearSurfaceFrameRate();
    }

    public long adjustReleaseTime(long releaseTimeNs) {
        long adjustedReleaseTimeNs;
        if (this.lastAdjustedFrameIndex != -1 && this.frameRateEstimator.isSynced()) {
            long frameDurationNs = this.frameRateEstimator.getFrameDurationNs();
            long candidateAdjustedReleaseTimeNs = this.lastAdjustedReleaseTimeNs + ((long) (((this.frameIndex - this.lastAdjustedFrameIndex) * frameDurationNs) / this.playbackSpeed));
            if (adjustmentAllowed(releaseTimeNs, candidateAdjustedReleaseTimeNs)) {
                adjustedReleaseTimeNs = candidateAdjustedReleaseTimeNs;
            } else {
                resetAdjustment();
                adjustedReleaseTimeNs = releaseTimeNs;
            }
        } else {
            adjustedReleaseTimeNs = releaseTimeNs;
        }
        long adjustedReleaseTimeNs2 = this.frameIndex;
        this.pendingLastAdjustedFrameIndex = adjustedReleaseTimeNs2;
        this.pendingLastAdjustedReleaseTimeNs = adjustedReleaseTimeNs;
        if (this.vsyncSampler == null || this.vsyncDurationNs == C.TIME_UNSET) {
            return adjustedReleaseTimeNs;
        }
        long sampledVsyncTimeNs = this.vsyncSampler.sampledVsyncTimeNs;
        if (sampledVsyncTimeNs == C.TIME_UNSET) {
            return adjustedReleaseTimeNs;
        }
        long snappedTimeNs = closestVsync(adjustedReleaseTimeNs, sampledVsyncTimeNs, this.vsyncDurationNs);
        return snappedTimeNs - this.vsyncOffsetNs;
    }

    private void resetAdjustment() {
        this.frameIndex = 0L;
        this.lastAdjustedFrameIndex = -1L;
        this.pendingLastAdjustedFrameIndex = -1L;
    }

    private static boolean adjustmentAllowed(long unadjustedReleaseTimeNs, long adjustedReleaseTimeNs) {
        return Math.abs(unadjustedReleaseTimeNs - adjustedReleaseTimeNs) <= MAX_ALLOWED_ADJUSTMENT_NS;
    }

    private void updateSurfaceMediaFrameRate() {
        float minimumChangeForUpdate;
        if (Util.SDK_INT < 30 || this.surface == null) {
            return;
        }
        float candidateFrameRate = this.frameRateEstimator.isSynced() ? this.frameRateEstimator.getFrameRate() : this.formatFrameRate;
        if (candidateFrameRate == this.surfaceMediaFrameRate) {
            return;
        }
        boolean shouldUpdate = true;
        if (candidateFrameRate != -1.0f && this.surfaceMediaFrameRate != -1.0f) {
            boolean candidateIsHighConfidence = this.frameRateEstimator.isSynced() && this.frameRateEstimator.getMatchingFrameDurationSumNs() >= MINIMUM_MATCHING_FRAME_DURATION_FOR_HIGH_CONFIDENCE_NS;
            if (candidateIsHighConfidence) {
                minimumChangeForUpdate = MINIMUM_MEDIA_FRAME_RATE_CHANGE_FOR_UPDATE_HIGH_CONFIDENCE;
            } else {
                minimumChangeForUpdate = 1.0f;
            }
            if (Math.abs(candidateFrameRate - this.surfaceMediaFrameRate) < minimumChangeForUpdate) {
                shouldUpdate = false;
            }
        } else if (candidateFrameRate != -1.0f) {
            shouldUpdate = true;
        } else if (this.frameRateEstimator.getFramesWithoutSyncCount() < 30) {
            shouldUpdate = false;
        }
        if (shouldUpdate) {
            this.surfaceMediaFrameRate = candidateFrameRate;
            updateSurfacePlaybackFrameRate(false);
        }
    }

    private void updateSurfacePlaybackFrameRate(boolean forceUpdate) {
        if (Util.SDK_INT < 30 || this.surface == null || this.changeFrameRateStrategy == Integer.MIN_VALUE) {
            return;
        }
        float surfacePlaybackFrameRate = 0.0f;
        if (this.started && this.surfaceMediaFrameRate != -1.0f) {
            surfacePlaybackFrameRate = this.surfaceMediaFrameRate * this.playbackSpeed;
        }
        if (!forceUpdate && this.surfacePlaybackFrameRate == surfacePlaybackFrameRate) {
            return;
        }
        this.surfacePlaybackFrameRate = surfacePlaybackFrameRate;
        Api30.setSurfaceFrameRate(this.surface, surfacePlaybackFrameRate);
    }

    private void clearSurfaceFrameRate() {
        if (Util.SDK_INT < 30 || this.surface == null || this.changeFrameRateStrategy == Integer.MIN_VALUE || this.surfacePlaybackFrameRate == 0.0f) {
            return;
        }
        this.surfacePlaybackFrameRate = 0.0f;
        Api30.setSurfaceFrameRate(this.surface, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDefaultDisplayRefreshRateParams(Display defaultDisplay) {
        if (defaultDisplay != null) {
            double defaultDisplayRefreshRate = defaultDisplay.getRefreshRate();
            this.vsyncDurationNs = (long) (1.0E9d / defaultDisplayRefreshRate);
            this.vsyncOffsetNs = (this.vsyncDurationNs * VSYNC_OFFSET_PERCENTAGE) / 100;
        } else {
            Log.w(TAG, "Unable to query display refresh rate");
            this.vsyncDurationNs = C.TIME_UNSET;
            this.vsyncOffsetNs = C.TIME_UNSET;
        }
    }

    private static long closestVsync(long releaseTime, long sampledVsyncTime, long vsyncDuration) {
        long snappedBeforeNs;
        long snappedAfterNs;
        long vsyncCount = (releaseTime - sampledVsyncTime) / vsyncDuration;
        long snappedTimeNs = sampledVsyncTime + (vsyncDuration * vsyncCount);
        if (releaseTime <= snappedTimeNs) {
            snappedBeforeNs = snappedTimeNs - vsyncDuration;
            snappedAfterNs = snappedTimeNs;
        } else {
            snappedBeforeNs = snappedTimeNs;
            snappedAfterNs = snappedTimeNs + vsyncDuration;
        }
        long snappedAfterDiff = snappedAfterNs - releaseTime;
        long snappedBeforeDiff = releaseTime - snappedBeforeNs;
        return snappedAfterDiff < snappedBeforeDiff ? snappedAfterNs : snappedBeforeNs;
    }

    private static DisplayHelper maybeBuildDisplayHelper(Context context) {
        DisplayHelper displayHelper = null;
        if (context == null) {
            return null;
        }
        Context context2 = context.getApplicationContext();
        if (Util.SDK_INT >= 17) {
            displayHelper = DisplayHelperV17.maybeBuildNewInstance(context2);
        }
        if (displayHelper == null) {
            DisplayHelper displayHelper2 = DisplayHelperV16.maybeBuildNewInstance(context2);
            return displayHelper2;
        }
        return displayHelper;
    }

    private static final class Api30 {
        private Api30() {
        }

        public static void setSurfaceFrameRate(Surface surface, float frameRate) {
            int compatibility;
            if (frameRate == 0.0f) {
                compatibility = 0;
            } else {
                compatibility = 1;
            }
            try {
                surface.setFrameRate(frameRate, compatibility);
            } catch (IllegalStateException e) {
                Log.e(VideoFrameReleaseHelper.TAG, "Failed to call Surface.setFrameRate", e);
            }
        }
    }

    private static final class DisplayHelperV16 implements DisplayHelper {
        private final WindowManager windowManager;

        public static DisplayHelper maybeBuildNewInstance(Context context) {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager != null) {
                return new DisplayHelperV16(windowManager);
            }
            return null;
        }

        private DisplayHelperV16(WindowManager windowManager) {
            this.windowManager = windowManager;
        }

        @Override // androidx.media3.exoplayer.video.VideoFrameReleaseHelper.DisplayHelper
        public void register(DisplayHelper.Listener listener) {
            listener.onDefaultDisplayChanged(this.windowManager.getDefaultDisplay());
        }

        @Override // androidx.media3.exoplayer.video.VideoFrameReleaseHelper.DisplayHelper
        public void unregister() {
        }
    }

    private static final class DisplayHelperV17 implements DisplayHelper, DisplayManager.DisplayListener {
        private final DisplayManager displayManager;
        private DisplayHelper.Listener listener;

        public static DisplayHelper maybeBuildNewInstance(Context context) {
            DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
            if (displayManager != null) {
                return new DisplayHelperV17(displayManager);
            }
            return null;
        }

        private DisplayHelperV17(DisplayManager displayManager) {
            this.displayManager = displayManager;
        }

        @Override // androidx.media3.exoplayer.video.VideoFrameReleaseHelper.DisplayHelper
        public void register(DisplayHelper.Listener listener) {
            this.listener = listener;
            this.displayManager.registerDisplayListener(this, Util.createHandlerForCurrentLooper());
            listener.onDefaultDisplayChanged(getDefaultDisplay());
        }

        @Override // androidx.media3.exoplayer.video.VideoFrameReleaseHelper.DisplayHelper
        public void unregister() {
            this.displayManager.unregisterDisplayListener(this);
            this.listener = null;
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int displayId) {
            if (this.listener != null && displayId == 0) {
                this.listener.onDefaultDisplayChanged(getDefaultDisplay());
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int displayId) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int displayId) {
        }

        private Display getDefaultDisplay() {
            return this.displayManager.getDisplay(0);
        }
    }

    private static final class VSyncSampler implements Choreographer.FrameCallback, Handler.Callback {
        private static final int CREATE_CHOREOGRAPHER = 0;
        private static final VSyncSampler INSTANCE = new VSyncSampler();
        private static final int MSG_ADD_OBSERVER = 1;
        private static final int MSG_REMOVE_OBSERVER = 2;
        private Choreographer choreographer;
        private final Handler handler;
        private int observerCount;
        public volatile long sampledVsyncTimeNs = C.TIME_UNSET;
        private final HandlerThread choreographerOwnerThread = new HandlerThread("ExoPlayer:FrameReleaseChoreographer");

        public static VSyncSampler getInstance() {
            return INSTANCE;
        }

        private VSyncSampler() {
            this.choreographerOwnerThread.start();
            this.handler = Util.createHandler(this.choreographerOwnerThread.getLooper(), this);
            this.handler.sendEmptyMessage(0);
        }

        public void addObserver() {
            this.handler.sendEmptyMessage(1);
        }

        public void removeObserver() {
            this.handler.sendEmptyMessage(2);
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long vsyncTimeNs) {
            this.sampledVsyncTimeNs = vsyncTimeNs;
            ((Choreographer) Assertions.checkNotNull(this.choreographer)).postFrameCallbackDelayed(this, 500L);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    createChoreographerInstanceInternal();
                    return true;
                case 1:
                    addObserverInternal();
                    return true;
                case 2:
                    removeObserverInternal();
                    return true;
                default:
                    return false;
            }
        }

        private void createChoreographerInstanceInternal() {
            try {
                this.choreographer = Choreographer.getInstance();
            } catch (RuntimeException e) {
                Log.w(VideoFrameReleaseHelper.TAG, "Vsync sampling disabled due to platform error", e);
            }
        }

        private void addObserverInternal() {
            if (this.choreographer != null) {
                this.observerCount++;
                if (this.observerCount == 1) {
                    this.choreographer.postFrameCallback(this);
                }
            }
        }

        private void removeObserverInternal() {
            if (this.choreographer != null) {
                this.observerCount--;
                if (this.observerCount == 0) {
                    this.choreographer.removeFrameCallback(this);
                    this.sampledVsyncTimeNs = C.TIME_UNSET;
                }
            }
        }
    }
}
