package androidx.media3.exoplayer;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSourceException;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.metadata.MetadataRenderer;
import androidx.media3.exoplayer.source.BehindLiveWindowException;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.ShuffleOrder;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.text.TextRenderer;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes11.dex */
final class ExoPlayerImplInternal implements Handler.Callback, MediaPeriod.Callback, TrackSelector.InvalidationListener, MediaSourceList.MediaSourceListInfoRefreshListener, DefaultMediaClock.PlaybackParametersListener, PlayerMessage.Sender {
    private static final int ACTIVE_INTERVAL_MS = 10;
    private static final int IDLE_INTERVAL_MS = 1000;
    private static final int MSG_ADD_MEDIA_SOURCES = 18;
    private static final int MSG_ATTEMPT_RENDERER_ERROR_RECOVERY = 25;
    private static final int MSG_DO_SOME_WORK = 2;
    private static final int MSG_MOVE_MEDIA_SOURCES = 19;
    private static final int MSG_PERIOD_PREPARED = 8;
    private static final int MSG_PLAYBACK_PARAMETERS_CHANGED_INTERNAL = 16;
    private static final int MSG_PLAYLIST_UPDATE_REQUESTED = 22;
    private static final int MSG_PREPARE = 0;
    private static final int MSG_RELEASE = 7;
    private static final int MSG_REMOVE_MEDIA_SOURCES = 20;
    private static final int MSG_RENDERER_CAPABILITIES_CHANGED = 26;
    private static final int MSG_SEEK_TO = 3;
    private static final int MSG_SEND_MESSAGE = 14;
    private static final int MSG_SEND_MESSAGE_TO_TARGET_THREAD = 15;
    private static final int MSG_SET_FOREGROUND_MODE = 13;
    private static final int MSG_SET_MEDIA_SOURCES = 17;
    private static final int MSG_SET_OFFLOAD_SCHEDULING_ENABLED = 24;
    private static final int MSG_SET_PAUSE_AT_END_OF_WINDOW = 23;
    private static final int MSG_SET_PLAYBACK_PARAMETERS = 4;
    private static final int MSG_SET_PLAY_WHEN_READY = 1;
    private static final int MSG_SET_REPEAT_MODE = 11;
    private static final int MSG_SET_SEEK_PARAMETERS = 5;
    private static final int MSG_SET_SHUFFLE_ENABLED = 12;
    private static final int MSG_SET_SHUFFLE_ORDER = 21;
    private static final int MSG_SOURCE_CONTINUE_LOADING_REQUESTED = 9;
    private static final int MSG_STOP = 6;
    private static final int MSG_TRACK_SELECTION_INVALIDATED = 10;
    private static final long PLAYBACK_BUFFER_EMPTY_THRESHOLD_US = 500000;
    private static final long PLAYBACK_STUCK_AFTER_MS = 4000;
    private static final String TAG = "ExoPlayerImplInternal";
    private final long backBufferDurationUs;
    private final BandwidthMeter bandwidthMeter;
    private final Clock clock;
    private boolean deliverPendingMessageAtStartPositionRequired;
    private final TrackSelectorResult emptyTrackSelectorResult;
    private int enabledRendererCount;
    private boolean foregroundMode;
    private final HandlerWrapper handler;
    private final HandlerThread internalPlaybackThread;
    private boolean isRebuffering;
    private final LivePlaybackSpeedControl livePlaybackSpeedControl;
    private final LoadControl loadControl;
    private final DefaultMediaClock mediaClock;
    private final MediaSourceList mediaSourceList;
    private int nextPendingMessageIndexHint;
    private boolean offloadSchedulingEnabled;
    private boolean pauseAtEndOfWindow;
    private SeekPosition pendingInitialSeekPosition;
    private final ArrayList<PendingMessageInfo> pendingMessages;
    private boolean pendingPauseAtEndOfPeriod;
    private ExoPlaybackException pendingRecoverableRendererError;
    private final Timeline.Period period;
    private PlaybackInfo playbackInfo;
    private PlaybackInfoUpdate playbackInfoUpdate;
    private final PlaybackInfoUpdateListener playbackInfoUpdateListener;
    private final Looper playbackLooper;
    private long playbackMaybeBecameStuckAtMs = C.TIME_UNSET;
    private final MediaPeriodQueue queue;
    private final long releaseTimeoutMs;
    private boolean released;
    private final RendererCapabilities[] rendererCapabilities;
    private long rendererPositionUs;
    private final Renderer[] renderers;
    private final Set<Renderer> renderersToReset;
    private int repeatMode;
    private boolean requestForRendererSleep;
    private final boolean retainBackBufferFromKeyframe;
    private SeekParameters seekParameters;
    private long setForegroundModeTimeoutMs;
    private boolean shouldContinueLoading;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;
    private final Timeline.Window window;

    public interface PlaybackInfoUpdateListener {
        void onPlaybackInfoUpdate(PlaybackInfoUpdate playbackInfoUpdate);
    }

    public static final class PlaybackInfoUpdate {
        public int discontinuityReason;
        private boolean hasPendingChange;
        public boolean hasPlayWhenReadyChangeReason;
        public int operationAcks;
        public int playWhenReadyChangeReason;
        public PlaybackInfo playbackInfo;
        public boolean positionDiscontinuity;

        public PlaybackInfoUpdate(PlaybackInfo playbackInfo) {
            this.playbackInfo = playbackInfo;
        }

        public void incrementPendingOperationAcks(int operationAcks) {
            this.hasPendingChange |= operationAcks > 0;
            this.operationAcks += operationAcks;
        }

        public void setPlaybackInfo(PlaybackInfo playbackInfo) {
            this.hasPendingChange |= this.playbackInfo != playbackInfo;
            this.playbackInfo = playbackInfo;
        }

        public void setPositionDiscontinuity(int discontinuityReason) {
            if (this.positionDiscontinuity && this.discontinuityReason != 5) {
                Assertions.checkArgument(discontinuityReason == 5);
                return;
            }
            this.hasPendingChange = true;
            this.positionDiscontinuity = true;
            this.discontinuityReason = discontinuityReason;
        }

        public void setPlayWhenReadyChangeReason(int playWhenReadyChangeReason) {
            this.hasPendingChange = true;
            this.hasPlayWhenReadyChangeReason = true;
            this.playWhenReadyChangeReason = playWhenReadyChangeReason;
        }
    }

    public ExoPlayerImplInternal(Renderer[] renderers, TrackSelector trackSelector, TrackSelectorResult emptyTrackSelectorResult, LoadControl loadControl, BandwidthMeter bandwidthMeter, int repeatMode, boolean shuffleModeEnabled, AnalyticsCollector analyticsCollector, SeekParameters seekParameters, LivePlaybackSpeedControl livePlaybackSpeedControl, long releaseTimeoutMs, boolean pauseAtEndOfWindow, Looper applicationLooper, Clock clock, PlaybackInfoUpdateListener playbackInfoUpdateListener, PlayerId playerId, Looper playbackLooper) {
        this.playbackInfoUpdateListener = playbackInfoUpdateListener;
        this.renderers = renderers;
        this.trackSelector = trackSelector;
        this.emptyTrackSelectorResult = emptyTrackSelectorResult;
        this.loadControl = loadControl;
        this.bandwidthMeter = bandwidthMeter;
        this.repeatMode = repeatMode;
        this.shuffleModeEnabled = shuffleModeEnabled;
        this.seekParameters = seekParameters;
        this.livePlaybackSpeedControl = livePlaybackSpeedControl;
        this.releaseTimeoutMs = releaseTimeoutMs;
        this.setForegroundModeTimeoutMs = releaseTimeoutMs;
        this.pauseAtEndOfWindow = pauseAtEndOfWindow;
        this.clock = clock;
        this.backBufferDurationUs = loadControl.getBackBufferDurationUs();
        this.retainBackBufferFromKeyframe = loadControl.retainBackBufferFromKeyframe();
        this.playbackInfo = PlaybackInfo.createDummy(emptyTrackSelectorResult);
        this.playbackInfoUpdate = new PlaybackInfoUpdate(this.playbackInfo);
        this.rendererCapabilities = new RendererCapabilities[renderers.length];
        RendererCapabilities.Listener rendererCapabilitiesListener = trackSelector.getRendererCapabilitiesListener();
        for (int i = 0; i < renderers.length; i++) {
            renderers[i].init(i, playerId);
            this.rendererCapabilities[i] = renderers[i].getCapabilities();
            if (rendererCapabilitiesListener != null) {
                this.rendererCapabilities[i].setListener(rendererCapabilitiesListener);
            }
        }
        this.mediaClock = new DefaultMediaClock(this, clock);
        this.pendingMessages = new ArrayList<>();
        this.renderersToReset = Sets.newIdentityHashSet();
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        trackSelector.init(this, bandwidthMeter);
        this.deliverPendingMessageAtStartPositionRequired = true;
        HandlerWrapper eventHandler = clock.createHandler(applicationLooper, null);
        this.queue = new MediaPeriodQueue(analyticsCollector, eventHandler);
        this.mediaSourceList = new MediaSourceList(this, analyticsCollector, eventHandler, playerId);
        if (playbackLooper != null) {
            this.internalPlaybackThread = null;
            this.playbackLooper = playbackLooper;
        } else {
            this.internalPlaybackThread = new HandlerThread("ExoPlayer:Playback", -16);
            this.internalPlaybackThread.start();
            this.playbackLooper = this.internalPlaybackThread.getLooper();
        }
        this.handler = clock.createHandler(this.playbackLooper, this);
    }

    public void experimentalSetForegroundModeTimeoutMs(long setForegroundModeTimeoutMs) {
        this.setForegroundModeTimeoutMs = setForegroundModeTimeoutMs;
    }

    public void experimentalSetOffloadSchedulingEnabled(boolean z) {
        this.handler.obtainMessage(24, z ? 1 : 0, 0).sendToTarget();
    }

    public void prepare() {
        this.handler.obtainMessage(0).sendToTarget();
    }

    public void setPlayWhenReady(boolean z, int i) {
        this.handler.obtainMessage(1, z ? 1 : 0, i).sendToTarget();
    }

    public void setPauseAtEndOfWindow(boolean z) {
        this.handler.obtainMessage(23, z ? 1 : 0, 0).sendToTarget();
    }

    public void setRepeatMode(int repeatMode) {
        this.handler.obtainMessage(11, repeatMode, 0).sendToTarget();
    }

    public void setShuffleModeEnabled(boolean z) {
        this.handler.obtainMessage(12, z ? 1 : 0, 0).sendToTarget();
    }

    public void seekTo(Timeline timeline, int windowIndex, long positionUs) {
        this.handler.obtainMessage(3, new SeekPosition(timeline, windowIndex, positionUs)).sendToTarget();
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.handler.obtainMessage(4, playbackParameters).sendToTarget();
    }

    public void setSeekParameters(SeekParameters seekParameters) {
        this.handler.obtainMessage(5, seekParameters).sendToTarget();
    }

    public void stop() {
        this.handler.obtainMessage(6).sendToTarget();
    }

    public void setMediaSources(List<MediaSourceList.MediaSourceHolder> mediaSources, int windowIndex, long positionUs, ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(17, new MediaSourceListUpdateMessage(mediaSources, shuffleOrder, windowIndex, positionUs)).sendToTarget();
    }

    public void addMediaSources(int index, List<MediaSourceList.MediaSourceHolder> mediaSources, ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(18, index, 0, new MediaSourceListUpdateMessage(mediaSources, shuffleOrder, -1, C.TIME_UNSET)).sendToTarget();
    }

    public void removeMediaSources(int fromIndex, int toIndex, ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(20, fromIndex, toIndex, shuffleOrder).sendToTarget();
    }

    public void moveMediaSources(int fromIndex, int toIndex, int newFromIndex, ShuffleOrder shuffleOrder) {
        MoveMediaItemsMessage moveMediaItemsMessage = new MoveMediaItemsMessage(fromIndex, toIndex, newFromIndex, shuffleOrder);
        this.handler.obtainMessage(19, moveMediaItemsMessage).sendToTarget();
    }

    public void setShuffleOrder(ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(21, shuffleOrder).sendToTarget();
    }

    @Override // androidx.media3.exoplayer.PlayerMessage.Sender
    public synchronized void sendMessage(PlayerMessage message) {
        if (!this.released && this.playbackLooper.getThread().isAlive()) {
            this.handler.obtainMessage(14, message).sendToTarget();
            return;
        }
        Log.w(TAG, "Ignoring messages sent after release.");
        message.markAsProcessed(false);
    }

    public synchronized boolean setForegroundMode(boolean foregroundMode) {
        if (!this.released && this.playbackLooper.getThread().isAlive()) {
            if (foregroundMode) {
                this.handler.obtainMessage(13, 1, 0).sendToTarget();
                return true;
            }
            final AtomicBoolean processedFlag = new AtomicBoolean();
            this.handler.obtainMessage(13, 0, 0, processedFlag).sendToTarget();
            Objects.requireNonNull(processedFlag);
            waitUninterruptibly(new Supplier() { // from class: androidx.media3.exoplayer.ExoPlayerImplInternal$$ExternalSyntheticLambda2
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return Boolean.valueOf(processedFlag.get());
                }
            }, this.setForegroundModeTimeoutMs);
            return processedFlag.get();
        }
        return true;
    }

    public synchronized boolean release() {
        if (!this.released && this.playbackLooper.getThread().isAlive()) {
            this.handler.sendEmptyMessage(7);
            waitUninterruptibly(new Supplier() { // from class: androidx.media3.exoplayer.ExoPlayerImplInternal$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return this.f$0.m8241lambda$release$0$androidxmedia3exoplayerExoPlayerImplInternal();
                }
            }, this.releaseTimeoutMs);
            return this.released;
        }
        return true;
    }

    /* JADX INFO: renamed from: lambda$release$0$androidx-media3-exoplayer-ExoPlayerImplInternal, reason: not valid java name */
    /* synthetic */ Boolean m8241lambda$release$0$androidxmedia3exoplayerExoPlayerImplInternal() {
        return Boolean.valueOf(this.released);
    }

    public Looper getPlaybackLooper() {
        return this.playbackLooper;
    }

    @Override // androidx.media3.exoplayer.MediaSourceList.MediaSourceListInfoRefreshListener
    public void onPlaylistUpdateRequested() {
        this.handler.sendEmptyMessage(22);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod source) {
        this.handler.obtainMessage(8, source).sendToTarget();
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod source) {
        this.handler.obtainMessage(9, source).sendToTarget();
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector.InvalidationListener
    public void onTrackSelectionsInvalidated() {
        this.handler.sendEmptyMessage(10);
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector.InvalidationListener
    public void onRendererCapabilitiesChanged(Renderer renderer) {
        this.handler.sendEmptyMessage(26);
    }

    @Override // androidx.media3.exoplayer.DefaultMediaClock.PlaybackParametersListener
    public void onPlaybackParametersChanged(PlaybackParameters newPlaybackParameters) {
        this.handler.obtainMessage(16, newPlaybackParameters).sendToTarget();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) throws Throwable {
        int errorCode;
        MediaPeriodHolder readingPeriod;
        int errorCode2;
        try {
            switch (msg.what) {
                case 0:
                    prepareInternal();
                    break;
                case 1:
                    setPlayWhenReadyInternal(msg.arg1 != 0, msg.arg2, true, 1);
                    break;
                case 2:
                    doSomeWork();
                    break;
                case 3:
                    seekToInternal((SeekPosition) msg.obj);
                    break;
                case 4:
                    setPlaybackParametersInternal((PlaybackParameters) msg.obj);
                    break;
                case 5:
                    setSeekParametersInternal((SeekParameters) msg.obj);
                    break;
                case 6:
                    stopInternal(false, true);
                    break;
                case 7:
                    releaseInternal();
                    return true;
                case 8:
                    handlePeriodPrepared((MediaPeriod) msg.obj);
                    break;
                case 9:
                    handleContinueLoadingRequested((MediaPeriod) msg.obj);
                    break;
                case 10:
                    reselectTracksInternal();
                    break;
                case 11:
                    setRepeatModeInternal(msg.arg1);
                    break;
                case 12:
                    setShuffleModeEnabledInternal(msg.arg1 != 0);
                    break;
                case 13:
                    setForegroundModeInternal(msg.arg1 != 0, (AtomicBoolean) msg.obj);
                    break;
                case 14:
                    sendMessageInternal((PlayerMessage) msg.obj);
                    break;
                case 15:
                    sendMessageToTargetThread((PlayerMessage) msg.obj);
                    break;
                case 16:
                    handlePlaybackParameters((PlaybackParameters) msg.obj, false);
                    break;
                case 17:
                    setMediaItemsInternal((MediaSourceListUpdateMessage) msg.obj);
                    break;
                case 18:
                    addMediaItemsInternal((MediaSourceListUpdateMessage) msg.obj, msg.arg1);
                    break;
                case 19:
                    moveMediaItemsInternal((MoveMediaItemsMessage) msg.obj);
                    break;
                case 20:
                    removeMediaItemsInternal(msg.arg1, msg.arg2, (ShuffleOrder) msg.obj);
                    break;
                case 21:
                    setShuffleOrderInternal((ShuffleOrder) msg.obj);
                    break;
                case 22:
                    mediaSourceListUpdateRequestedInternal();
                    break;
                case 23:
                    setPauseAtEndOfWindowInternal(msg.arg1 != 0);
                    break;
                case 24:
                    setOffloadSchedulingEnabledInternal(msg.arg1 == 1);
                    break;
                case 25:
                    attemptRendererErrorRecovery();
                    break;
                case 26:
                    reselectTracksInternalAndSeek();
                    break;
                default:
                    return false;
            }
        } catch (ParserException e) {
            if (e.dataType == 1) {
                if (e.contentIsMalformed) {
                    errorCode2 = 3001;
                } else {
                    errorCode2 = 3003;
                }
            } else {
                int errorCode3 = e.dataType;
                if (errorCode3 == 4) {
                    if (e.contentIsMalformed) {
                        errorCode2 = 3002;
                    } else {
                        errorCode2 = 3004;
                    }
                } else {
                    errorCode2 = 1000;
                }
            }
            handleIoException(e, errorCode2);
        } catch (DataSourceException e2) {
            handleIoException(e2, e2.reason);
        } catch (ExoPlaybackException e3) {
            e = e3;
            if (e.type == 1 && (readingPeriod = this.queue.getReadingPeriod()) != null) {
                e = e.copyWithMediaPeriodId(readingPeriod.info.id);
            }
            if (e.isRecoverable && this.pendingRecoverableRendererError == null) {
                Log.w(TAG, "Recoverable renderer error", e);
                this.pendingRecoverableRendererError = e;
                this.handler.sendMessageAtFrontOfQueue(this.handler.obtainMessage(25, e));
            } else {
                if (this.pendingRecoverableRendererError != null) {
                    this.pendingRecoverableRendererError.addSuppressed(e);
                    e = this.pendingRecoverableRendererError;
                }
                Log.e(TAG, "Playback error", e);
                if (e.type == 1 && this.queue.getPlayingPeriod() != this.queue.getReadingPeriod()) {
                    while (this.queue.getPlayingPeriod() != this.queue.getReadingPeriod()) {
                        this.queue.advancePlayingPeriod();
                    }
                    MediaPeriodHolder newPlayingPeriodHolder = (MediaPeriodHolder) Assertions.checkNotNull(this.queue.getPlayingPeriod());
                    this.playbackInfo = handlePositionDiscontinuity(newPlayingPeriodHolder.info.id, newPlayingPeriodHolder.info.startPositionUs, newPlayingPeriodHolder.info.requestedContentPositionUs, newPlayingPeriodHolder.info.startPositionUs, true, 0);
                }
                stopInternal(true, false);
                this.playbackInfo = this.playbackInfo.copyWithPlaybackError(e);
            }
        } catch (DrmSession.DrmSessionException e4) {
            handleIoException(e4, e4.errorCode);
        } catch (BehindLiveWindowException e5) {
            handleIoException(e5, 1002);
        } catch (IOException e6) {
            handleIoException(e6, 2000);
        } catch (RuntimeException e7) {
            if ((e7 instanceof IllegalStateException) || (e7 instanceof IllegalArgumentException)) {
                errorCode = 1004;
            } else {
                errorCode = 1000;
            }
            ExoPlaybackException error = ExoPlaybackException.createForUnexpected(e7, errorCode);
            Log.e(TAG, "Playback error", error);
            stopInternal(true, false);
            this.playbackInfo = this.playbackInfo.copyWithPlaybackError(error);
        }
        maybeNotifyPlaybackInfoChanged();
        return true;
    }

    private void handleIoException(IOException e, int errorCode) {
        ExoPlaybackException error = ExoPlaybackException.createForSource(e, errorCode);
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null) {
            error = error.copyWithMediaPeriodId(playingPeriod.info.id);
        }
        Log.e(TAG, "Playback error", error);
        stopInternal(false, false);
        this.playbackInfo = this.playbackInfo.copyWithPlaybackError(error);
    }

    private synchronized void waitUninterruptibly(Supplier<Boolean> condition, long timeoutMs) {
        long deadlineMs = this.clock.elapsedRealtime() + timeoutMs;
        long remainingMs = timeoutMs;
        boolean wasInterrupted = false;
        while (!condition.get().booleanValue() && remainingMs > 0) {
            try {
                this.clock.onThreadBlocked();
                wait(remainingMs);
            } catch (InterruptedException e) {
                wasInterrupted = true;
            }
            remainingMs = deadlineMs - this.clock.elapsedRealtime();
        }
        if (wasInterrupted) {
            Thread.currentThread().interrupt();
        }
    }

    private void setState(int state) {
        if (this.playbackInfo.playbackState != state) {
            if (state != 2) {
                this.playbackMaybeBecameStuckAtMs = C.TIME_UNSET;
            }
            this.playbackInfo = this.playbackInfo.copyWithPlaybackState(state);
        }
    }

    private void maybeNotifyPlaybackInfoChanged() {
        this.playbackInfoUpdate.setPlaybackInfo(this.playbackInfo);
        if (this.playbackInfoUpdate.hasPendingChange) {
            this.playbackInfoUpdateListener.onPlaybackInfoUpdate(this.playbackInfoUpdate);
            this.playbackInfoUpdate = new PlaybackInfoUpdate(this.playbackInfo);
        }
    }

    private void prepareInternal() {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        resetInternal(false, false, false, true);
        this.loadControl.onPrepared();
        setState(this.playbackInfo.timeline.isEmpty() ? 4 : 2);
        this.mediaSourceList.prepare(this.bandwidthMeter.getTransferListener());
        this.handler.sendEmptyMessage(2);
    }

    private void setMediaItemsInternal(MediaSourceListUpdateMessage mediaSourceListUpdateMessage) throws Throwable {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        if (mediaSourceListUpdateMessage.windowIndex != -1) {
            this.pendingInitialSeekPosition = new SeekPosition(new PlaylistTimeline(mediaSourceListUpdateMessage.mediaSourceHolders, mediaSourceListUpdateMessage.shuffleOrder), mediaSourceListUpdateMessage.windowIndex, mediaSourceListUpdateMessage.positionUs);
        }
        Timeline timeline = this.mediaSourceList.setMediaSources(mediaSourceListUpdateMessage.mediaSourceHolders, mediaSourceListUpdateMessage.shuffleOrder);
        handleMediaSourceListInfoRefreshed(timeline, false);
    }

    private void addMediaItemsInternal(MediaSourceListUpdateMessage addMessage, int insertionIndex) throws Throwable {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        Timeline timeline = this.mediaSourceList.addMediaSources(insertionIndex == -1 ? this.mediaSourceList.getSize() : insertionIndex, addMessage.mediaSourceHolders, addMessage.shuffleOrder);
        handleMediaSourceListInfoRefreshed(timeline, false);
    }

    private void moveMediaItemsInternal(MoveMediaItemsMessage moveMediaItemsMessage) throws Throwable {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        Timeline timeline = this.mediaSourceList.moveMediaSourceRange(moveMediaItemsMessage.fromIndex, moveMediaItemsMessage.toIndex, moveMediaItemsMessage.newFromIndex, moveMediaItemsMessage.shuffleOrder);
        handleMediaSourceListInfoRefreshed(timeline, false);
    }

    private void removeMediaItemsInternal(int fromIndex, int toIndex, ShuffleOrder shuffleOrder) throws Throwable {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        Timeline timeline = this.mediaSourceList.removeMediaSourceRange(fromIndex, toIndex, shuffleOrder);
        handleMediaSourceListInfoRefreshed(timeline, false);
    }

    private void mediaSourceListUpdateRequestedInternal() throws Throwable {
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.createTimeline(), true);
    }

    private void setShuffleOrderInternal(ShuffleOrder shuffleOrder) throws Throwable {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        Timeline timeline = this.mediaSourceList.setShuffleOrder(shuffleOrder);
        handleMediaSourceListInfoRefreshed(timeline, false);
    }

    private void notifyTrackSelectionPlayWhenReadyChanged(boolean playWhenReady) {
        for (MediaPeriodHolder periodHolder = this.queue.getPlayingPeriod(); periodHolder != null; periodHolder = periodHolder.getNext()) {
            for (ExoTrackSelection trackSelection : periodHolder.getTrackSelectorResult().selections) {
                if (trackSelection != null) {
                    trackSelection.onPlayWhenReadyChanged(playWhenReady);
                }
            }
        }
    }

    private void setPlayWhenReadyInternal(boolean z, int i, boolean z2, int i2) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(z2 ? 1 : 0);
        this.playbackInfoUpdate.setPlayWhenReadyChangeReason(i2);
        this.playbackInfo = this.playbackInfo.copyWithPlayWhenReady(z, i);
        this.isRebuffering = false;
        notifyTrackSelectionPlayWhenReadyChanged(z);
        if (!shouldPlayWhenReady()) {
            stopRenderers();
            updatePlaybackPositions();
        } else if (this.playbackInfo.playbackState == 3) {
            startRenderers();
            this.handler.sendEmptyMessage(2);
        } else if (this.playbackInfo.playbackState == 2) {
            this.handler.sendEmptyMessage(2);
        }
    }

    private void setPauseAtEndOfWindowInternal(boolean pauseAtEndOfWindow) throws ExoPlaybackException {
        this.pauseAtEndOfWindow = pauseAtEndOfWindow;
        resetPendingPauseAtEndOfPeriod();
        if (this.pendingPauseAtEndOfPeriod && this.queue.getReadingPeriod() != this.queue.getPlayingPeriod()) {
            seekToCurrentPosition(true);
            handleLoadingMediaPeriodChanged(false);
        }
    }

    private void setOffloadSchedulingEnabledInternal(boolean offloadSchedulingEnabled) {
        if (offloadSchedulingEnabled == this.offloadSchedulingEnabled) {
            return;
        }
        this.offloadSchedulingEnabled = offloadSchedulingEnabled;
        if (!offloadSchedulingEnabled && this.playbackInfo.sleepingForOffload) {
            this.handler.sendEmptyMessage(2);
        }
    }

    private void setRepeatModeInternal(int repeatMode) throws ExoPlaybackException {
        this.repeatMode = repeatMode;
        if (!this.queue.updateRepeatMode(this.playbackInfo.timeline, repeatMode)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void setShuffleModeEnabledInternal(boolean shuffleModeEnabled) throws ExoPlaybackException {
        this.shuffleModeEnabled = shuffleModeEnabled;
        if (!this.queue.updateShuffleModeEnabled(this.playbackInfo.timeline, shuffleModeEnabled)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void seekToCurrentPosition(boolean sendDiscontinuity) throws ExoPlaybackException {
        MediaSource.MediaPeriodId periodId = this.queue.getPlayingPeriod().info.id;
        long newPositionUs = seekToPeriodPosition(periodId, this.playbackInfo.positionUs, true, false);
        if (newPositionUs != this.playbackInfo.positionUs) {
            this.playbackInfo = handlePositionDiscontinuity(periodId, newPositionUs, this.playbackInfo.requestedContentPositionUs, this.playbackInfo.discontinuityStartPositionUs, sendDiscontinuity, 5);
        }
    }

    private void startRenderers() throws ExoPlaybackException {
        this.isRebuffering = false;
        this.mediaClock.start();
        for (Renderer renderer : this.renderers) {
            if (isRendererEnabled(renderer)) {
                renderer.start();
            }
        }
    }

    private void stopRenderers() throws ExoPlaybackException {
        this.mediaClock.stop();
        for (Renderer renderer : this.renderers) {
            if (isRendererEnabled(renderer)) {
                ensureStopped(renderer);
            }
        }
    }

    private void attemptRendererErrorRecovery() throws ExoPlaybackException {
        reselectTracksInternalAndSeek();
    }

    private void updatePlaybackPositions() throws ExoPlaybackException {
        long discontinuityPositionUs;
        MediaPeriodHolder playingPeriodHolder = this.queue.getPlayingPeriod();
        if (playingPeriodHolder == null) {
            return;
        }
        if (playingPeriodHolder.prepared) {
            discontinuityPositionUs = playingPeriodHolder.mediaPeriod.readDiscontinuity();
        } else {
            discontinuityPositionUs = -9223372036854775807L;
        }
        if (discontinuityPositionUs != C.TIME_UNSET) {
            resetRendererPosition(discontinuityPositionUs);
            if (discontinuityPositionUs != this.playbackInfo.positionUs) {
                MediaSource.MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
                long discontinuityPositionUs2 = this.playbackInfo.requestedContentPositionUs;
                this.playbackInfo = handlePositionDiscontinuity(mediaPeriodId, discontinuityPositionUs, discontinuityPositionUs2, discontinuityPositionUs, true, 5);
            }
        } else {
            this.rendererPositionUs = this.mediaClock.syncAndGetPositionUs(playingPeriodHolder != this.queue.getReadingPeriod());
            long periodPositionUs = playingPeriodHolder.toPeriodTime(this.rendererPositionUs);
            maybeTriggerPendingMessages(this.playbackInfo.positionUs, periodPositionUs);
            this.playbackInfo.updatePositionUs(periodPositionUs);
        }
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        this.playbackInfo.bufferedPositionUs = loadingPeriod.getBufferedPositionUs();
        this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        if (this.playbackInfo.playWhenReady && this.playbackInfo.playbackState == 3 && shouldUseLivePlaybackSpeedControl(this.playbackInfo.timeline, this.playbackInfo.periodId) && this.playbackInfo.playbackParameters.speed == 1.0f) {
            float adjustedSpeed = this.livePlaybackSpeedControl.getAdjustedPlaybackSpeed(getCurrentLiveOffsetUs(), getTotalBufferedDurationUs());
            if (this.mediaClock.getPlaybackParameters().speed != adjustedSpeed) {
                setMediaClockPlaybackParameters(this.playbackInfo.playbackParameters.withSpeed(adjustedSpeed));
                handlePlaybackParameters(this.playbackInfo.playbackParameters, this.mediaClock.getPlaybackParameters().speed, false, false);
            }
        }
    }

    private void setMediaClockPlaybackParameters(PlaybackParameters playbackParameters) {
        this.handler.removeMessages(16);
        this.mediaClock.setPlaybackParameters(playbackParameters);
    }

    private void notifyTrackSelectionRebuffer() {
        for (MediaPeriodHolder periodHolder = this.queue.getPlayingPeriod(); periodHolder != null; periodHolder = periodHolder.getNext()) {
            for (ExoTrackSelection trackSelection : periodHolder.getTrackSelectorResult().selections) {
                if (trackSelection != null) {
                    trackSelection.onRebuffer();
                }
            }
        }
    }

    private void doSomeWork() throws ExoPlaybackException, IOException {
        long operationStartTimeMs = this.clock.uptimeMillis();
        this.handler.removeMessages(2);
        updatePeriods();
        if (this.playbackInfo.playbackState == 1 || this.playbackInfo.playbackState == 4) {
            return;
        }
        MediaPeriodHolder playingPeriodHolder = this.queue.getPlayingPeriod();
        if (playingPeriodHolder == null) {
            scheduleNextWork(operationStartTimeMs, 10L);
            return;
        }
        TraceUtil.beginSection("doSomeWork");
        updatePlaybackPositions();
        boolean renderersEnded = true;
        boolean renderersAllowPlayback = true;
        if (playingPeriodHolder.prepared) {
            long rendererPositionElapsedRealtimeUs = SystemClock.elapsedRealtime() * 1000;
            playingPeriodHolder.mediaPeriod.discardBuffer(this.playbackInfo.positionUs - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
            for (int i = 0; i < this.renderers.length; i++) {
                Renderer renderer = this.renderers[i];
                if (isRendererEnabled(renderer)) {
                    renderer.render(this.rendererPositionUs, rendererPositionElapsedRealtimeUs);
                    renderersEnded = renderersEnded && renderer.isEnded();
                    boolean isReadingAhead = playingPeriodHolder.sampleStreams[i] != renderer.getStream();
                    boolean isWaitingForNextStream = !isReadingAhead && renderer.hasReadStreamToEnd();
                    boolean allowsPlayback = isReadingAhead || isWaitingForNextStream || renderer.isReady() || renderer.isEnded();
                    boolean renderersAllowPlayback2 = renderersAllowPlayback && allowsPlayback;
                    if (!allowsPlayback) {
                        renderer.maybeThrowStreamError();
                    }
                    renderersAllowPlayback = renderersAllowPlayback2;
                }
            }
        } else {
            playingPeriodHolder.mediaPeriod.maybeThrowPrepareError();
        }
        long playingPeriodDurationUs = playingPeriodHolder.info.durationUs;
        boolean finishedRendering = renderersEnded && playingPeriodHolder.prepared && (playingPeriodDurationUs == C.TIME_UNSET || playingPeriodDurationUs <= this.playbackInfo.positionUs);
        if (finishedRendering && this.pendingPauseAtEndOfPeriod) {
            this.pendingPauseAtEndOfPeriod = false;
            setPlayWhenReadyInternal(false, this.playbackInfo.playbackSuppressionReason, false, 5);
        }
        if (finishedRendering && playingPeriodHolder.info.isFinal) {
            setState(4);
            stopRenderers();
        } else if (this.playbackInfo.playbackState != 2 || !shouldTransitionToReadyState(renderersAllowPlayback)) {
            if (this.playbackInfo.playbackState == 3 && (this.enabledRendererCount != 0 ? !renderersAllowPlayback : !isTimelineReady())) {
                this.isRebuffering = shouldPlayWhenReady();
                setState(2);
                if (this.isRebuffering) {
                    notifyTrackSelectionRebuffer();
                    this.livePlaybackSpeedControl.notifyRebuffer();
                }
                stopRenderers();
            }
        } else {
            setState(3);
            this.pendingRecoverableRendererError = null;
            if (shouldPlayWhenReady()) {
                startRenderers();
            }
        }
        boolean playbackMaybeStuck = false;
        if (this.playbackInfo.playbackState == 2) {
            for (int i2 = 0; i2 < this.renderers.length; i2++) {
                if (isRendererEnabled(this.renderers[i2]) && this.renderers[i2].getStream() == playingPeriodHolder.sampleStreams[i2]) {
                    this.renderers[i2].maybeThrowStreamError();
                }
            }
            if (!this.playbackInfo.isLoading && this.playbackInfo.totalBufferedDurationUs < PLAYBACK_BUFFER_EMPTY_THRESHOLD_US && isLoadingPossible()) {
                playbackMaybeStuck = true;
            }
        }
        if (!playbackMaybeStuck) {
            this.playbackMaybeBecameStuckAtMs = C.TIME_UNSET;
        } else if (this.playbackMaybeBecameStuckAtMs == C.TIME_UNSET) {
            this.playbackMaybeBecameStuckAtMs = this.clock.elapsedRealtime();
        } else if (this.clock.elapsedRealtime() - this.playbackMaybeBecameStuckAtMs >= PLAYBACK_STUCK_AFTER_MS) {
            throw new IllegalStateException("Playback stuck buffering and not loading");
        }
        boolean isPlaying = shouldPlayWhenReady() && this.playbackInfo.playbackState == 3;
        boolean sleepingForOffload = this.offloadSchedulingEnabled && this.requestForRendererSleep && isPlaying;
        if (this.playbackInfo.sleepingForOffload != sleepingForOffload) {
            this.playbackInfo = this.playbackInfo.copyWithSleepingForOffload(sleepingForOffload);
        }
        this.requestForRendererSleep = false;
        if (!sleepingForOffload && this.playbackInfo.playbackState != 4) {
            if (isPlaying || this.playbackInfo.playbackState == 2) {
                scheduleNextWork(operationStartTimeMs, 10L);
            } else if (this.playbackInfo.playbackState == 3 && this.enabledRendererCount != 0) {
                scheduleNextWork(operationStartTimeMs, 1000L);
            }
        }
        TraceUtil.endSection();
    }

    private long getCurrentLiveOffsetUs() {
        return getLiveOffsetUs(this.playbackInfo.timeline, this.playbackInfo.periodId.periodUid, this.playbackInfo.positionUs);
    }

    private long getLiveOffsetUs(Timeline timeline, Object periodUid, long periodPositionUs) {
        int windowIndex = timeline.getPeriodByUid(periodUid, this.period).windowIndex;
        timeline.getWindow(windowIndex, this.window);
        return (this.window.windowStartTimeMs != C.TIME_UNSET && this.window.isLive() && this.window.isDynamic) ? Util.msToUs(this.window.getCurrentUnixTimeMs() - this.window.windowStartTimeMs) - (this.period.getPositionInWindowUs() + periodPositionUs) : C.TIME_UNSET;
    }

    private boolean shouldUseLivePlaybackSpeedControl(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        if (mediaPeriodId.isAd() || timeline.isEmpty()) {
            return false;
        }
        int windowIndex = timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex;
        timeline.getWindow(windowIndex, this.window);
        return this.window.isLive() && this.window.isDynamic && this.window.windowStartTimeMs != C.TIME_UNSET;
    }

    private void scheduleNextWork(long thisOperationStartTimeMs, long intervalMs) {
        this.handler.sendEmptyMessageAtTime(2, thisOperationStartTimeMs + intervalMs);
    }

    private void seekToInternal(SeekPosition seekPosition) throws Throwable {
        long j;
        long requestedContentPositionUs;
        long periodPositionUs;
        boolean seekPositionAdjusted;
        MediaSource.MediaPeriodId periodId;
        long periodPositionUs2;
        long requestedContentPositionUs2;
        long periodPositionUs3;
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        Pair<Object, Long> resolvedSeekPosition = resolveSeekPositionUs(this.playbackInfo.timeline, seekPosition, true, this.repeatMode, this.shuffleModeEnabled, this.window, this.period);
        if (resolvedSeekPosition == null) {
            Pair<MediaSource.MediaPeriodId, Long> firstPeriodAndPositionUs = getPlaceholderFirstMediaPeriodPositionUs(this.playbackInfo.timeline);
            MediaSource.MediaPeriodId periodId2 = (MediaSource.MediaPeriodId) firstPeriodAndPositionUs.first;
            periodPositionUs = ((Long) firstPeriodAndPositionUs.second).longValue();
            requestedContentPositionUs = C.TIME_UNSET;
            seekPositionAdjusted = !this.playbackInfo.timeline.isEmpty();
            periodId = periodId2;
            j = 0;
        } else {
            Object periodUid = resolvedSeekPosition.first;
            long resolvedContentPositionUs = ((Long) resolvedSeekPosition.second).longValue();
            long requestedContentPositionUs3 = seekPosition.windowPositionUs == C.TIME_UNSET ? -9223372036854775807L : resolvedContentPositionUs;
            MediaSource.MediaPeriodId periodId3 = this.queue.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(this.playbackInfo.timeline, periodUid, resolvedContentPositionUs);
            if (periodId3.isAd()) {
                this.playbackInfo.timeline.getPeriodByUid(periodId3.periodUid, this.period);
                long periodPositionUs4 = this.period.getFirstAdIndexToPlay(periodId3.adGroupIndex) == periodId3.adIndexInAdGroup ? this.period.getAdResumePositionUs() : 0L;
                long j2 = periodPositionUs4;
                requestedContentPositionUs = requestedContentPositionUs3;
                periodPositionUs = j2;
                periodId = periodId3;
                seekPositionAdjusted = true;
                j = 0;
            } else {
                j = 0;
                requestedContentPositionUs = requestedContentPositionUs3;
                periodPositionUs = resolvedContentPositionUs;
                seekPositionAdjusted = seekPosition.windowPositionUs == C.TIME_UNSET;
                periodId = periodId3;
            }
        }
        try {
            try {
                if (!this.playbackInfo.timeline.isEmpty()) {
                    if (resolvedSeekPosition == null) {
                        if (this.playbackInfo.playbackState != 1) {
                            setState(4);
                        }
                        resetInternal(false, true, false, true);
                    } else {
                        long newPeriodPositionUs = periodPositionUs;
                        if (periodId.equals(this.playbackInfo.periodId)) {
                            MediaPeriodHolder playingPeriodHolder = this.queue.getPlayingPeriod();
                            long newPeriodPositionUs2 = (playingPeriodHolder == null || !playingPeriodHolder.prepared || newPeriodPositionUs == j) ? newPeriodPositionUs : playingPeriodHolder.mediaPeriod.getAdjustedSeekPositionUs(newPeriodPositionUs, this.seekParameters);
                            if (Util.usToMs(newPeriodPositionUs2) == Util.usToMs(this.playbackInfo.positionUs) && (this.playbackInfo.playbackState == 2 || this.playbackInfo.playbackState == 3)) {
                                long periodPositionUs5 = this.playbackInfo.positionUs;
                                this.playbackInfo = handlePositionDiscontinuity(periodId, periodPositionUs5, requestedContentPositionUs, periodPositionUs5, seekPositionAdjusted, 2);
                                return;
                            }
                            newPeriodPositionUs = newPeriodPositionUs2;
                            this.playbackInfo = handlePositionDiscontinuity(periodId, periodPositionUs2, requestedContentPositionUs, periodPositionUs2, seekPositionAdjusted, 2);
                            throw th;
                        }
                        seekPositionAdjusted = seekPositionAdjusted;
                        try {
                            long newPeriodPositionUs3 = seekToPeriodPosition(periodId, newPeriodPositionUs, this.playbackInfo.playbackState == 4);
                            seekPositionAdjusted |= periodPositionUs != newPeriodPositionUs3;
                            try {
                                MediaSource.MediaPeriodId periodId4 = periodId;
                                long requestedContentPositionUs4 = requestedContentPositionUs;
                                try {
                                    updatePlaybackSpeedSettingsForNewPeriod(this.playbackInfo.timeline, periodId4, this.playbackInfo.timeline, this.playbackInfo.periodId, requestedContentPositionUs4, true);
                                    periodId = periodId4;
                                    requestedContentPositionUs2 = requestedContentPositionUs4;
                                    periodPositionUs3 = newPeriodPositionUs3;
                                } catch (Throwable th) {
                                    th = th;
                                    periodId = periodId4;
                                    requestedContentPositionUs = requestedContentPositionUs4;
                                    periodPositionUs2 = newPeriodPositionUs3;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                periodPositionUs2 = newPeriodPositionUs3;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            periodPositionUs2 = periodPositionUs;
                        }
                    }
                    this.playbackInfo = handlePositionDiscontinuity(periodId, periodPositionUs3, requestedContentPositionUs2, periodPositionUs3, seekPositionAdjusted, 2);
                }
                this.pendingInitialSeekPosition = seekPosition;
                periodPositionUs3 = periodPositionUs;
                seekPositionAdjusted = seekPositionAdjusted;
                requestedContentPositionUs2 = requestedContentPositionUs;
                this.playbackInfo = handlePositionDiscontinuity(periodId, periodPositionUs3, requestedContentPositionUs2, periodPositionUs3, seekPositionAdjusted, 2);
            } catch (Throwable th4) {
                th = th4;
                periodPositionUs2 = periodPositionUs;
                seekPositionAdjusted = seekPositionAdjusted;
            }
        } catch (Throwable th5) {
            th = th5;
            seekPositionAdjusted = seekPositionAdjusted;
            periodPositionUs2 = periodPositionUs;
        }
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId periodId, long periodPositionUs, boolean forceBufferingState) throws ExoPlaybackException {
        return seekToPeriodPosition(periodId, periodPositionUs, this.queue.getPlayingPeriod() != this.queue.getReadingPeriod(), forceBufferingState);
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId periodId, long periodPositionUs, boolean forceDisableRenderers, boolean forceBufferingState) throws ExoPlaybackException {
        stopRenderers();
        this.isRebuffering = false;
        if (forceBufferingState || this.playbackInfo.playbackState == 3) {
            setState(2);
        }
        MediaPeriodHolder oldPlayingPeriodHolder = this.queue.getPlayingPeriod();
        MediaPeriodHolder newPlayingPeriodHolder = oldPlayingPeriodHolder;
        while (newPlayingPeriodHolder != null && !periodId.equals(newPlayingPeriodHolder.info.id)) {
            newPlayingPeriodHolder = newPlayingPeriodHolder.getNext();
        }
        if (forceDisableRenderers || oldPlayingPeriodHolder != newPlayingPeriodHolder || (newPlayingPeriodHolder != null && newPlayingPeriodHolder.toRendererTime(periodPositionUs) < 0)) {
            for (Renderer renderer : this.renderers) {
                disableRenderer(renderer);
            }
            if (newPlayingPeriodHolder != null) {
                while (this.queue.getPlayingPeriod() != newPlayingPeriodHolder) {
                    this.queue.advancePlayingPeriod();
                }
                this.queue.removeAfter(newPlayingPeriodHolder);
                newPlayingPeriodHolder.setRendererOffset(MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US);
                enableRenderers();
            }
        }
        if (newPlayingPeriodHolder != null) {
            this.queue.removeAfter(newPlayingPeriodHolder);
            if (!newPlayingPeriodHolder.prepared) {
                newPlayingPeriodHolder.info = newPlayingPeriodHolder.info.copyWithStartPositionUs(periodPositionUs);
            } else if (newPlayingPeriodHolder.hasEnabledTracks) {
                periodPositionUs = newPlayingPeriodHolder.mediaPeriod.seekToUs(periodPositionUs);
                newPlayingPeriodHolder.mediaPeriod.discardBuffer(periodPositionUs - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
            }
            resetRendererPosition(periodPositionUs);
            maybeContinueLoading();
        } else {
            this.queue.clear();
            resetRendererPosition(periodPositionUs);
        }
        handleLoadingMediaPeriodChanged(false);
        this.handler.sendEmptyMessage(2);
        return periodPositionUs;
    }

    private void resetRendererPosition(long periodPositionUs) throws ExoPlaybackException {
        long rendererTime;
        MediaPeriodHolder playingMediaPeriod = this.queue.getPlayingPeriod();
        if (playingMediaPeriod == null) {
            rendererTime = MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US + periodPositionUs;
        } else {
            rendererTime = playingMediaPeriod.toRendererTime(periodPositionUs);
        }
        this.rendererPositionUs = rendererTime;
        this.mediaClock.resetPosition(this.rendererPositionUs);
        for (Renderer renderer : this.renderers) {
            if (isRendererEnabled(renderer)) {
                renderer.resetPosition(this.rendererPositionUs);
            }
        }
        notifyTrackSelectionDiscontinuity();
    }

    private void setPlaybackParametersInternal(PlaybackParameters playbackParameters) throws ExoPlaybackException {
        setMediaClockPlaybackParameters(playbackParameters);
        handlePlaybackParameters(this.mediaClock.getPlaybackParameters(), true);
    }

    private void setSeekParametersInternal(SeekParameters seekParameters) {
        this.seekParameters = seekParameters;
    }

    private void setForegroundModeInternal(boolean foregroundMode, AtomicBoolean processedFlag) {
        if (this.foregroundMode != foregroundMode) {
            this.foregroundMode = foregroundMode;
            if (!foregroundMode) {
                for (Renderer renderer : this.renderers) {
                    if (!isRendererEnabled(renderer) && this.renderersToReset.remove(renderer)) {
                        renderer.reset();
                    }
                }
            }
        }
        if (processedFlag != null) {
            synchronized (this) {
                processedFlag.set(true);
                notifyAll();
            }
        }
    }

    private void stopInternal(boolean z, boolean z2) {
        resetInternal(z || !this.foregroundMode, false, true, false);
        this.playbackInfoUpdate.incrementPendingOperationAcks(z2 ? 1 : 0);
        this.loadControl.onStopped();
        setState(1);
    }

    private void releaseInternal() {
        resetInternal(true, false, true, false);
        releaseRenderers();
        this.loadControl.onReleased();
        setState(1);
        if (this.internalPlaybackThread != null) {
            this.internalPlaybackThread.quit();
        }
        synchronized (this) {
            this.released = true;
            notifyAll();
        }
    }

    /* JADX WARN: Code duplicated, block: B:45:0x00fc A[PHI: r3
  0x00fc: PHI (r3v3 'timeline' androidx.media3.common.Timeline) = 
  (r3v2 'timeline' androidx.media3.common.Timeline)
  (r3v2 'timeline' androidx.media3.common.Timeline)
  (r3v6 'timeline' androidx.media3.common.Timeline)
  (r3v6 'timeline' androidx.media3.common.Timeline)
 binds: [B:37:0x00bd, B:39:0x00c1, B:41:0x00d6, B:43:0x00ed] A[DONT_GENERATE, DONT_INLINE]] */
    private void resetInternal(boolean resetRenderers, boolean resetPosition, boolean releaseMediaSourceList, boolean resetError) {
        long requestedContentPositionUs;
        long startPositionUs;
        boolean resetTrackInfo;
        long requestedContentPositionUs2;
        MediaSource.MediaPeriodId mediaPeriodId;
        Timeline timeline;
        this.handler.removeMessages(2);
        ExoPlaybackException exoPlaybackException = null;
        this.pendingRecoverableRendererError = null;
        this.isRebuffering = false;
        this.mediaClock.stop();
        this.rendererPositionUs = MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US;
        for (Renderer renderer : this.renderers) {
            try {
                disableRenderer(renderer);
            } catch (ExoPlaybackException | RuntimeException e) {
                Log.e(TAG, "Disable failed.", e);
            }
        }
        if (resetRenderers) {
            for (Renderer renderer2 : this.renderers) {
                if (this.renderersToReset.remove(renderer2)) {
                    try {
                        renderer2.reset();
                    } catch (RuntimeException e2) {
                        Log.e(TAG, "Reset failed.", e2);
                    }
                }
            }
        }
        this.enabledRendererCount = 0;
        MediaSource.MediaPeriodId mediaPeriodId2 = this.playbackInfo.periodId;
        long startPositionUs2 = this.playbackInfo.positionUs;
        if (this.playbackInfo.periodId.isAd() || isUsingPlaceholderPeriod(this.playbackInfo, this.period)) {
            requestedContentPositionUs = this.playbackInfo.requestedContentPositionUs;
        } else {
            requestedContentPositionUs = this.playbackInfo.positionUs;
        }
        if (!resetPosition) {
            startPositionUs = startPositionUs2;
            resetTrackInfo = false;
            requestedContentPositionUs2 = requestedContentPositionUs;
        } else {
            this.pendingInitialSeekPosition = null;
            Pair<MediaSource.MediaPeriodId, Long> firstPeriodAndPositionUs = getPlaceholderFirstMediaPeriodPositionUs(this.playbackInfo.timeline);
            mediaPeriodId2 = (MediaSource.MediaPeriodId) firstPeriodAndPositionUs.first;
            long startPositionUs3 = ((Long) firstPeriodAndPositionUs.second).longValue();
            if (mediaPeriodId2.equals(this.playbackInfo.periodId)) {
                startPositionUs = startPositionUs3;
                resetTrackInfo = false;
                requestedContentPositionUs2 = -9223372036854775807L;
            } else {
                startPositionUs = startPositionUs3;
                resetTrackInfo = true;
                requestedContentPositionUs2 = -9223372036854775807L;
            }
        }
        this.queue.clear();
        this.shouldContinueLoading = false;
        Timeline timeline2 = this.playbackInfo.timeline;
        if (releaseMediaSourceList && (timeline2 instanceof PlaylistTimeline)) {
            timeline2 = ((PlaylistTimeline) this.playbackInfo.timeline).copyWithPlaceholderTimeline(this.mediaSourceList.getShuffleOrder());
            if (mediaPeriodId2.adGroupIndex != -1) {
                timeline2.getPeriodByUid(mediaPeriodId2.periodUid, this.period);
                if (timeline2.getWindow(this.period.windowIndex, this.window).isLive()) {
                    mediaPeriodId = new MediaSource.MediaPeriodId(mediaPeriodId2.periodUid, mediaPeriodId2.windowSequenceNumber);
                    timeline = timeline2;
                } else {
                    mediaPeriodId = mediaPeriodId2;
                    timeline = timeline2;
                }
            } else {
                mediaPeriodId = mediaPeriodId2;
                timeline = timeline2;
            }
        } else {
            mediaPeriodId = mediaPeriodId2;
            timeline = timeline2;
        }
        int i = this.playbackInfo.playbackState;
        if (!resetError) {
            exoPlaybackException = this.playbackInfo.playbackError;
        }
        this.playbackInfo = new PlaybackInfo(timeline, mediaPeriodId, requestedContentPositionUs2, startPositionUs, i, exoPlaybackException, false, resetTrackInfo ? TrackGroupArray.EMPTY : this.playbackInfo.trackGroups, resetTrackInfo ? this.emptyTrackSelectorResult : this.playbackInfo.trackSelectorResult, resetTrackInfo ? ImmutableList.of() : this.playbackInfo.staticMetadata, mediaPeriodId, this.playbackInfo.playWhenReady, this.playbackInfo.playbackSuppressionReason, this.playbackInfo.playbackParameters, startPositionUs, 0L, startPositionUs, 0L, false);
        if (releaseMediaSourceList) {
            this.mediaSourceList.release();
        }
    }

    private Pair<MediaSource.MediaPeriodId, Long> getPlaceholderFirstMediaPeriodPositionUs(Timeline timeline) {
        if (timeline.isEmpty()) {
            return Pair.create(PlaybackInfo.getDummyPeriodForEmptyTimeline(), 0L);
        }
        int firstWindowIndex = timeline.getFirstWindowIndex(this.shuffleModeEnabled);
        Pair<Object, Long> firstPeriodAndPositionUs = timeline.getPeriodPositionUs(this.window, this.period, firstWindowIndex, C.TIME_UNSET);
        MediaSource.MediaPeriodId firstPeriodId = this.queue.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(timeline, firstPeriodAndPositionUs.first, 0L);
        long positionUs = ((Long) firstPeriodAndPositionUs.second).longValue();
        if (firstPeriodId.isAd()) {
            timeline.getPeriodByUid(firstPeriodId.periodUid, this.period);
            positionUs = firstPeriodId.adIndexInAdGroup == this.period.getFirstAdIndexToPlay(firstPeriodId.adGroupIndex) ? this.period.getAdResumePositionUs() : 0L;
        }
        return Pair.create(firstPeriodId, Long.valueOf(positionUs));
    }

    private void sendMessageInternal(PlayerMessage message) throws ExoPlaybackException {
        if (message.getPositionMs() == C.TIME_UNSET) {
            sendMessageToTarget(message);
            return;
        }
        if (this.playbackInfo.timeline.isEmpty()) {
            this.pendingMessages.add(new PendingMessageInfo(message));
            return;
        }
        PendingMessageInfo pendingMessageInfo = new PendingMessageInfo(message);
        if (resolvePendingMessagePosition(pendingMessageInfo, this.playbackInfo.timeline, this.playbackInfo.timeline, this.repeatMode, this.shuffleModeEnabled, this.window, this.period)) {
            this.pendingMessages.add(pendingMessageInfo);
            Collections.sort(this.pendingMessages);
        } else {
            message.markAsProcessed(false);
        }
    }

    private void sendMessageToTarget(PlayerMessage message) throws ExoPlaybackException {
        if (message.getLooper() == this.playbackLooper) {
            deliverMessage(message);
            if (this.playbackInfo.playbackState == 3 || this.playbackInfo.playbackState == 2) {
                this.handler.sendEmptyMessage(2);
                return;
            }
            return;
        }
        this.handler.obtainMessage(15, message).sendToTarget();
    }

    private void sendMessageToTargetThread(final PlayerMessage message) {
        Looper looper = message.getLooper();
        if (!looper.getThread().isAlive()) {
            Log.w("TAG", "Trying to send message on a dead thread.");
            message.markAsProcessed(false);
        } else {
            this.clock.createHandler(looper, null).post(new Runnable() { // from class: androidx.media3.exoplayer.ExoPlayerImplInternal$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m8242x7e52fcd1(message);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$sendMessageToTargetThread$1$androidx-media3-exoplayer-ExoPlayerImplInternal, reason: not valid java name */
    /* synthetic */ void m8242x7e52fcd1(PlayerMessage message) {
        try {
            deliverMessage(message);
        } catch (ExoPlaybackException e) {
            Log.e(TAG, "Unexpected error delivering message on external thread.", e);
            throw new RuntimeException(e);
        }
    }

    private void deliverMessage(PlayerMessage message) throws ExoPlaybackException {
        if (message.isCanceled()) {
            return;
        }
        try {
            message.getTarget().handleMessage(message.getType(), message.getPayload());
        } finally {
            message.markAsProcessed(true);
        }
    }

    private void resolvePendingMessagePositions(Timeline newTimeline, Timeline previousTimeline) {
        if (newTimeline.isEmpty() && previousTimeline.isEmpty()) {
            return;
        }
        int i = this.pendingMessages.size() - 1;
        while (i >= 0) {
            Timeline newTimeline2 = newTimeline;
            Timeline previousTimeline2 = previousTimeline;
            if (!resolvePendingMessagePosition(this.pendingMessages.get(i), newTimeline2, previousTimeline2, this.repeatMode, this.shuffleModeEnabled, this.window, this.period)) {
                this.pendingMessages.get(i).message.markAsProcessed(false);
                this.pendingMessages.remove(i);
            }
            i--;
            newTimeline = newTimeline2;
            previousTimeline = previousTimeline2;
        }
        Collections.sort(this.pendingMessages);
    }

    private void maybeTriggerPendingMessages(long oldPeriodPositionUs, long newPeriodPositionUs) throws ExoPlaybackException {
        PendingMessageInfo nextInfo;
        PendingMessageInfo pendingMessageInfo;
        PendingMessageInfo pendingMessageInfo2;
        if (this.pendingMessages.isEmpty() || this.playbackInfo.periodId.isAd()) {
            return;
        }
        if (this.deliverPendingMessageAtStartPositionRequired) {
            oldPeriodPositionUs--;
            this.deliverPendingMessageAtStartPositionRequired = false;
        }
        int currentPeriodIndex = this.playbackInfo.timeline.getIndexOfPeriod(this.playbackInfo.periodId.periodUid);
        int nextPendingMessageIndex = Math.min(this.nextPendingMessageIndexHint, this.pendingMessages.size());
        PendingMessageInfo previousInfo = nextPendingMessageIndex > 0 ? this.pendingMessages.get(nextPendingMessageIndex - 1) : null;
        while (previousInfo != null && (previousInfo.resolvedPeriodIndex > currentPeriodIndex || (previousInfo.resolvedPeriodIndex == currentPeriodIndex && previousInfo.resolvedPeriodTimeUs > oldPeriodPositionUs))) {
            nextPendingMessageIndex--;
            previousInfo = nextPendingMessageIndex > 0 ? this.pendingMessages.get(nextPendingMessageIndex - 1) : null;
        }
        if (nextPendingMessageIndex < this.pendingMessages.size()) {
            nextInfo = this.pendingMessages.get(nextPendingMessageIndex);
        } else {
            nextInfo = null;
        }
        while (nextInfo != null && nextInfo.resolvedPeriodUid != null && (nextInfo.resolvedPeriodIndex < currentPeriodIndex || (nextInfo.resolvedPeriodIndex == currentPeriodIndex && nextInfo.resolvedPeriodTimeUs <= oldPeriodPositionUs))) {
            nextPendingMessageIndex++;
            if (nextPendingMessageIndex < this.pendingMessages.size()) {
                pendingMessageInfo2 = this.pendingMessages.get(nextPendingMessageIndex);
            } else {
                pendingMessageInfo2 = null;
            }
            nextInfo = pendingMessageInfo2;
        }
        while (nextInfo != null && nextInfo.resolvedPeriodUid != null && nextInfo.resolvedPeriodIndex == currentPeriodIndex && nextInfo.resolvedPeriodTimeUs > oldPeriodPositionUs && nextInfo.resolvedPeriodTimeUs <= newPeriodPositionUs) {
            try {
                sendMessageToTarget(nextInfo.message);
                if (nextInfo.message.getDeleteAfterDelivery() || nextInfo.message.isCanceled()) {
                    this.pendingMessages.remove(nextPendingMessageIndex);
                } else {
                    nextPendingMessageIndex++;
                }
                if (nextPendingMessageIndex < this.pendingMessages.size()) {
                    pendingMessageInfo = this.pendingMessages.get(nextPendingMessageIndex);
                } else {
                    pendingMessageInfo = null;
                }
                nextInfo = pendingMessageInfo;
            } catch (Throwable th) {
                if (nextInfo.message.getDeleteAfterDelivery() || nextInfo.message.isCanceled()) {
                    this.pendingMessages.remove(nextPendingMessageIndex);
                } else {
                    int i = nextPendingMessageIndex + 1;
                }
                throw th;
            }
        }
        this.nextPendingMessageIndexHint = nextPendingMessageIndex;
    }

    private void ensureStopped(Renderer renderer) {
        if (renderer.getState() == 2) {
            renderer.stop();
        }
    }

    private void disableRenderer(Renderer renderer) throws ExoPlaybackException {
        if (!isRendererEnabled(renderer)) {
            return;
        }
        this.mediaClock.onRendererDisabled(renderer);
        ensureStopped(renderer);
        renderer.disable();
        this.enabledRendererCount--;
    }

    private void reselectTracksInternalAndSeek() throws ExoPlaybackException {
        reselectTracksInternal();
        seekToCurrentPosition(true);
    }

    private void reselectTracksInternal() throws ExoPlaybackException {
        float playbackSpeed = this.mediaClock.getPlaybackParameters().speed;
        MediaPeriodHolder periodHolder = this.queue.getPlayingPeriod();
        MediaPeriodHolder readingPeriodHolder = this.queue.getReadingPeriod();
        boolean selectionsChangedForReadPeriod = true;
        for (MediaPeriodHolder periodHolder2 = periodHolder; periodHolder2 != null && periodHolder2.prepared; periodHolder2 = periodHolder2.getNext()) {
            TrackSelectorResult newTrackSelectorResult = periodHolder2.selectTracks(playbackSpeed, this.playbackInfo.timeline);
            if (!newTrackSelectorResult.isEquivalent(periodHolder2.getTrackSelectorResult())) {
                if (!selectionsChangedForReadPeriod) {
                    this.queue.removeAfter(periodHolder2);
                    if (periodHolder2.prepared) {
                        long loadingPeriodPositionUs = Math.max(periodHolder2.info.startPositionUs, periodHolder2.toPeriodTime(this.rendererPositionUs));
                        periodHolder2.applyTrackSelection(newTrackSelectorResult, loadingPeriodPositionUs, false);
                    }
                } else {
                    MediaPeriodHolder playingPeriodHolder = this.queue.getPlayingPeriod();
                    boolean recreateStreams = this.queue.removeAfter(playingPeriodHolder);
                    boolean[] streamResetFlags = new boolean[this.renderers.length];
                    long periodPositionUs = playingPeriodHolder.applyTrackSelection(newTrackSelectorResult, this.playbackInfo.positionUs, recreateStreams, streamResetFlags);
                    boolean hasDiscontinuity = (this.playbackInfo.playbackState == 4 || periodPositionUs == this.playbackInfo.positionUs) ? false : true;
                    long periodPositionUs2 = periodPositionUs;
                    this.playbackInfo = handlePositionDiscontinuity(this.playbackInfo.periodId, periodPositionUs2, this.playbackInfo.requestedContentPositionUs, this.playbackInfo.discontinuityStartPositionUs, hasDiscontinuity, 5);
                    if (hasDiscontinuity) {
                        resetRendererPosition(periodPositionUs2);
                    }
                    boolean[] rendererWasEnabledFlags = new boolean[this.renderers.length];
                    int i = 0;
                    while (i < this.renderers.length) {
                        Renderer renderer = this.renderers[i];
                        rendererWasEnabledFlags[i] = isRendererEnabled(renderer);
                        SampleStream sampleStream = playingPeriodHolder.sampleStreams[i];
                        if (rendererWasEnabledFlags[i]) {
                            if (sampleStream != renderer.getStream()) {
                                disableRenderer(renderer);
                            } else if (streamResetFlags[i]) {
                                renderer.resetPosition(this.rendererPositionUs);
                            }
                        }
                        i++;
                        periodPositionUs2 = periodPositionUs2;
                    }
                    enableRenderers(rendererWasEnabledFlags);
                }
                handleLoadingMediaPeriodChanged(true);
                if (this.playbackInfo.playbackState != 4) {
                    maybeContinueLoading();
                    updatePlaybackPositions();
                    this.handler.sendEmptyMessage(2);
                    return;
                }
                return;
            }
            if (periodHolder2 == readingPeriodHolder) {
                selectionsChangedForReadPeriod = false;
            }
        }
    }

    private void updateTrackSelectionPlaybackSpeed(float playbackSpeed) {
        for (MediaPeriodHolder periodHolder = this.queue.getPlayingPeriod(); periodHolder != null; periodHolder = periodHolder.getNext()) {
            for (ExoTrackSelection trackSelection : periodHolder.getTrackSelectorResult().selections) {
                if (trackSelection != null) {
                    trackSelection.onPlaybackSpeed(playbackSpeed);
                }
            }
        }
    }

    private void notifyTrackSelectionDiscontinuity() {
        for (MediaPeriodHolder periodHolder = this.queue.getPlayingPeriod(); periodHolder != null; periodHolder = periodHolder.getNext()) {
            for (ExoTrackSelection trackSelection : periodHolder.getTrackSelectorResult().selections) {
                if (trackSelection != null) {
                    trackSelection.onDiscontinuity();
                }
            }
        }
    }

    private boolean shouldTransitionToReadyState(boolean renderersReadyOrEnded) {
        long targetLiveOffsetUs;
        if (this.enabledRendererCount == 0) {
            return isTimelineReady();
        }
        if (!renderersReadyOrEnded) {
            return false;
        }
        if (!this.playbackInfo.isLoading) {
            return true;
        }
        MediaPeriodHolder playingPeriodHolder = this.queue.getPlayingPeriod();
        if (shouldUseLivePlaybackSpeedControl(this.playbackInfo.timeline, playingPeriodHolder.info.id)) {
            targetLiveOffsetUs = this.livePlaybackSpeedControl.getTargetLiveOffsetUs();
        } else {
            targetLiveOffsetUs = C.TIME_UNSET;
        }
        long targetLiveOffsetUs2 = targetLiveOffsetUs;
        MediaPeriodHolder loadingHolder = this.queue.getLoadingPeriod();
        boolean isBufferedToEnd = loadingHolder.isFullyBuffered() && loadingHolder.info.isFinal;
        boolean isAdPendingPreparation = loadingHolder.info.id.isAd() && !loadingHolder.prepared;
        return isBufferedToEnd || isAdPendingPreparation || this.loadControl.shouldStartPlayback(this.playbackInfo.timeline, playingPeriodHolder.info.id, getTotalBufferedDurationUs(), this.mediaClock.getPlaybackParameters().speed, this.isRebuffering, targetLiveOffsetUs2);
    }

    private boolean isTimelineReady() {
        MediaPeriodHolder playingPeriodHolder = this.queue.getPlayingPeriod();
        long playingPeriodDurationUs = playingPeriodHolder.info.durationUs;
        return playingPeriodHolder.prepared && (playingPeriodDurationUs == C.TIME_UNSET || this.playbackInfo.positionUs < playingPeriodDurationUs || !shouldPlayWhenReady());
    }

    /* JADX WARN: Code duplicated, block: B:103:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:106:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:111:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:131:0x00bf A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:20:0x005b  */
    /* JADX WARN: Code duplicated, block: B:30:0x0078 A[Catch: all -> 0x00cb, TRY_ENTER, TRY_LEAVE, TryCatch #4 {all -> 0x00cb, blocks: (B:30:0x0078, B:43:0x00a0, B:45:0x00a8, B:47:0x00b2, B:48:0x00bf, B:49:0x00c5), top: B:122:0x0059 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x0095  */
    /* JADX WARN: Code duplicated, block: B:43:0x00a0 A[Catch: all -> 0x00cb, TRY_ENTER, TryCatch #4 {all -> 0x00cb, blocks: (B:30:0x0078, B:43:0x00a0, B:45:0x00a8, B:47:0x00b2, B:48:0x00bf, B:49:0x00c5), top: B:122:0x0059 }] */
    /* JADX WARN: Code duplicated, block: B:45:0x00a8 A[Catch: all -> 0x00cb, TryCatch #4 {all -> 0x00cb, blocks: (B:30:0x0078, B:43:0x00a0, B:45:0x00a8, B:47:0x00b2, B:48:0x00bf, B:49:0x00c5), top: B:122:0x0059 }] */
    /* JADX WARN: Code duplicated, block: B:47:0x00b2 A[Catch: all -> 0x00cb, TryCatch #4 {all -> 0x00cb, blocks: (B:30:0x0078, B:43:0x00a0, B:45:0x00a8, B:47:0x00b2, B:48:0x00bf, B:49:0x00c5), top: B:122:0x0059 }] */
    /* JADX WARN: Code duplicated, block: B:55:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:56:0x00db  */
    /* JADX WARN: Code duplicated, block: B:62:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:64:0x00fc A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:70:0x0111  */
    /* JADX WARN: Code duplicated, block: B:73:0x011e  */
    /* JADX WARN: Code duplicated, block: B:78:0x014f  */
    /* JADX WARN: Code duplicated, block: B:88:0x016a  */
    /* JADX WARN: Code duplicated, block: B:89:0x016c  */
    /* JADX WARN: Code duplicated, block: B:92:0x0177  */
    /* JADX WARN: Code duplicated, block: B:95:0x0183  */
    /* JADX WARN: Code duplicated, block: B:97:0x018f A[ADDED_TO_REGION] */
    private void handleMediaSourceListInfoRefreshed(Timeline timeline, boolean isSourceRefresh) throws Throwable {
        int i;
        long j;
        boolean reportDiscontinuity;
        Timeline timeline2;
        MediaPeriodHolder periodHolder;
        long j2;
        boolean reportDiscontinuity2;
        PositionUpdateForPlaylistChange positionUpdate = resolvePositionForPlaylistChange(timeline, this.playbackInfo, this.pendingInitialSeekPosition, this.queue, this.repeatMode, this.shuffleModeEnabled, this.window, this.period);
        MediaSource.MediaPeriodId newPeriodId = positionUpdate.periodId;
        long newRequestedContentPositionUs = positionUpdate.requestedContentPositionUs;
        boolean forceBufferingState = positionUpdate.forceBufferingState;
        long newPositionUs = positionUpdate.periodPositionUs;
        boolean z = true;
        boolean periodPositionChanged = (this.playbackInfo.periodId.equals(newPeriodId) && newPositionUs == this.playbackInfo.positionUs) ? false : true;
        try {
            if (!positionUpdate.endPlayback) {
                if (periodPositionChanged) {
                    z = false;
                    i = 4;
                    timeline2 = timeline;
                    if (!timeline2.isEmpty()) {
                        for (periodHolder = this.queue.getPlayingPeriod(); periodHolder != null; periodHolder = periodHolder.getNext()) {
                            if (periodHolder.info.id.equals(newPeriodId)) {
                                periodHolder.info = this.queue.getUpdatedMediaPeriodInfo(timeline2, periodHolder.info);
                                periodHolder.updateClipping();
                            }
                        }
                        newPositionUs = seekToPeriodPosition(newPeriodId, newPositionUs, forceBufferingState);
                    }
                } else {
                    i = 4;
                    z = false;
                    timeline2 = timeline;
                    if (!this.queue.updateQueuedPeriods(timeline, this.rendererPositionUs, getMaxRendererReadPositionUs())) {
                        seekToCurrentPosition(false);
                    }
                }
                Timeline timeline3 = this.playbackInfo.timeline;
                MediaSource.MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
                if (positionUpdate.setTargetLiveOffset) {
                    j2 = newPositionUs;
                } else {
                    j2 = -9223372036854775807L;
                }
                updatePlaybackSpeedSettingsForNewPeriod(timeline2, newPeriodId, timeline3, mediaPeriodId, j2, false);
                if (periodPositionChanged) {
                    Object oldPeriodUid = this.playbackInfo.periodId.periodUid;
                    Timeline oldTimeline = this.playbackInfo.timeline;
                    if (periodPositionChanged) {
                        reportDiscontinuity2 = z;
                    } else {
                        reportDiscontinuity2 = z;
                    }
                    this.playbackInfo = handlePositionDiscontinuity(newPeriodId, newPositionUs, newRequestedContentPositionUs, this.playbackInfo.discontinuityStartPositionUs, reportDiscontinuity2, timeline2.getIndexOfPeriod(oldPeriodUid) == -1 ? i : 3);
                } else {
                    Object oldPeriodUid2 = this.playbackInfo.periodId.periodUid;
                    Timeline oldTimeline2 = this.playbackInfo.timeline;
                    if (periodPositionChanged) {
                        reportDiscontinuity2 = z;
                    } else {
                        reportDiscontinuity2 = z;
                    }
                    this.playbackInfo = handlePositionDiscontinuity(newPeriodId, newPositionUs, newRequestedContentPositionUs, this.playbackInfo.discontinuityStartPositionUs, reportDiscontinuity2, timeline2.getIndexOfPeriod(oldPeriodUid2) == -1 ? i : 3);
                }
                resetPendingPauseAtEndOfPeriod();
                resolvePendingMessagePositions(timeline2, this.playbackInfo.timeline);
                this.playbackInfo = this.playbackInfo.copyWithTimeline(timeline2);
                if (!timeline2.isEmpty()) {
                    this.pendingInitialSeekPosition = null;
                }
                handleLoadingMediaPeriodChanged(z);
                return;
            }
            try {
                if (this.playbackInfo.playbackState != 1) {
                    setState(4);
                }
                resetInternal(false, false, false, true);
                try {
                    if (periodPositionChanged) {
                        try {
                            try {
                                try {
                                    i = 4;
                                    z = false;
                                    try {
                                        timeline2 = timeline;
                                        if (!this.queue.updateQueuedPeriods(timeline, this.rendererPositionUs, getMaxRendererReadPositionUs())) {
                                            seekToCurrentPosition(false);
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        Timeline timeline4 = this.playbackInfo.timeline;
                                        MediaSource.MediaPeriodId mediaPeriodId2 = this.playbackInfo.periodId;
                                        if (positionUpdate.setTargetLiveOffset) {
                                            j = newPositionUs;
                                        } else {
                                            j = -9223372036854775807L;
                                        }
                                        updatePlaybackSpeedSettingsForNewPeriod(timeline, newPeriodId, timeline4, mediaPeriodId2, j, false);
                                        if (periodPositionChanged) {
                                            Object oldPeriodUid3 = this.playbackInfo.periodId.periodUid;
                                            Timeline oldTimeline3 = this.playbackInfo.timeline;
                                            if (periodPositionChanged) {
                                                reportDiscontinuity = z;
                                            } else {
                                                reportDiscontinuity = z;
                                            }
                                            this.playbackInfo = handlePositionDiscontinuity(newPeriodId, newPositionUs, newRequestedContentPositionUs, this.playbackInfo.discontinuityStartPositionUs, reportDiscontinuity, timeline.getIndexOfPeriod(oldPeriodUid3) == -1 ? i : 3);
                                        } else {
                                            Object oldPeriodUid4 = this.playbackInfo.periodId.periodUid;
                                            Timeline oldTimeline4 = this.playbackInfo.timeline;
                                            if (periodPositionChanged) {
                                                reportDiscontinuity = z;
                                            } else {
                                                reportDiscontinuity = z;
                                            }
                                            this.playbackInfo = handlePositionDiscontinuity(newPeriodId, newPositionUs, newRequestedContentPositionUs, this.playbackInfo.discontinuityStartPositionUs, reportDiscontinuity, timeline.getIndexOfPeriod(oldPeriodUid4) == -1 ? i : 3);
                                        }
                                        resetPendingPauseAtEndOfPeriod();
                                        resolvePendingMessagePositions(
                                        /*  JADX ERROR: Method code generation error
                                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x01d1: INVOKE 
                                              (r26v0 'this' androidx.media3.exoplayer.ExoPlayerImplInternal A[IMMUTABLE_TYPE, THIS])
                                              (r2v5 androidx.media3.common.Timeline)
                                              (wrap androidx.media3.common.Timeline:0x01cf: IGET 
                                              (wrap androidx.media3.exoplayer.PlaybackInfo:0x01cd: IGET (r26v0 'this' androidx.media3.exoplayer.ExoPlayerImplInternal A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:1969) androidx.media3.exoplayer.ExoPlayerImplInternal.playbackInfo androidx.media3.exoplayer.PlaybackInfo)
                                             A[WRAPPED] (LINE:1969) androidx.media3.exoplayer.PlaybackInfo.timeline androidx.media3.common.Timeline)
                                             DIRECT call: androidx.media3.exoplayer.ExoPlayerImplInternal.resolvePendingMessagePositions(androidx.media3.common.Timeline, androidx.media3.common.Timeline):void A[MD:(androidx.media3.common.Timeline, androidx.media3.common.Timeline):void (m)] (LINE:1892) in method: androidx.media3.exoplayer.ExoPlayerImplInternal.handleMediaSourceListInfoRefreshed(androidx.media3.common.Timeline, boolean):void, file: classes11.dex
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                            	at jadx.core.codegen.RegionGen.makeCatchBlock(RegionGen.java:383)
                                            	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:335)
                                            	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                            	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:320)
                                            	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                            	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:320)
                                            	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                            	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:320)
                                            	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                            	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:320)
                                            	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                            	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:320)
                                            	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                            	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:320)
                                            	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                                            	at java.base/java.util.ArrayList.forEach(Unknown Source)
                                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                                            	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r2v5 androidx.media3.common.Timeline
                                            	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                                            */
                                        /*
                                            Method dump skipped, instruction units count: 490
                                            To view this dump change 'Code comments level' option to 'DEBUG'
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.handleMediaSourceListInfoRefreshed(androidx.media3.common.Timeline, boolean):void");
                                    }

                                    private void updatePlaybackSpeedSettingsForNewPeriod(Timeline newTimeline, MediaSource.MediaPeriodId newPeriodId, Timeline oldTimeline, MediaSource.MediaPeriodId oldPeriodId, long positionForTargetOffsetOverrideUs, boolean forceSetTargetOffsetOverride) throws ExoPlaybackException {
                                        if (!shouldUseLivePlaybackSpeedControl(newTimeline, newPeriodId)) {
                                            PlaybackParameters targetPlaybackParameters = newPeriodId.isAd() ? PlaybackParameters.DEFAULT : this.playbackInfo.playbackParameters;
                                            if (!this.mediaClock.getPlaybackParameters().equals(targetPlaybackParameters)) {
                                                setMediaClockPlaybackParameters(targetPlaybackParameters);
                                                handlePlaybackParameters(this.playbackInfo.playbackParameters, targetPlaybackParameters.speed, false, false);
                                                return;
                                            }
                                            return;
                                        }
                                        int windowIndex = newTimeline.getPeriodByUid(newPeriodId.periodUid, this.period).windowIndex;
                                        newTimeline.getWindow(windowIndex, this.window);
                                        this.livePlaybackSpeedControl.setLiveConfiguration((MediaItem.LiveConfiguration) Util.castNonNull(this.window.liveConfiguration));
                                        if (positionForTargetOffsetOverrideUs != C.TIME_UNSET) {
                                            this.livePlaybackSpeedControl.setTargetLiveOffsetOverrideUs(getLiveOffsetUs(newTimeline, newPeriodId.periodUid, positionForTargetOffsetOverrideUs));
                                            return;
                                        }
                                        Object windowUid = this.window.uid;
                                        Object oldWindowUid = null;
                                        if (!oldTimeline.isEmpty()) {
                                            int oldWindowIndex = oldTimeline.getPeriodByUid(oldPeriodId.periodUid, this.period).windowIndex;
                                            oldWindowUid = oldTimeline.getWindow(oldWindowIndex, this.window).uid;
                                        }
                                        if (!Util.areEqual(oldWindowUid, windowUid) || forceSetTargetOffsetOverride) {
                                            this.livePlaybackSpeedControl.setTargetLiveOffsetOverrideUs(C.TIME_UNSET);
                                        }
                                    }

                                    private long getMaxRendererReadPositionUs() {
                                        MediaPeriodHolder readingHolder = this.queue.getReadingPeriod();
                                        if (readingHolder == null) {
                                            return 0L;
                                        }
                                        long maxReadPositionUs = readingHolder.getRendererOffset();
                                        if (!readingHolder.prepared) {
                                            return maxReadPositionUs;
                                        }
                                        for (int i = 0; i < this.renderers.length; i++) {
                                            if (isRendererEnabled(this.renderers[i]) && this.renderers[i].getStream() == readingHolder.sampleStreams[i]) {
                                                long readingPositionUs = this.renderers[i].getReadingPositionUs();
                                                if (readingPositionUs == Long.MIN_VALUE) {
                                                    return Long.MIN_VALUE;
                                                }
                                                maxReadPositionUs = Math.max(readingPositionUs, maxReadPositionUs);
                                            }
                                        }
                                        return maxReadPositionUs;
                                    }

                                    private void updatePeriods() throws ExoPlaybackException {
                                        if (this.playbackInfo.timeline.isEmpty() || !this.mediaSourceList.isPrepared()) {
                                            return;
                                        }
                                        maybeUpdateLoadingPeriod();
                                        maybeUpdateReadingPeriod();
                                        maybeUpdateReadingRenderers();
                                        maybeUpdatePlayingPeriod();
                                    }

                                    private void maybeUpdateLoadingPeriod() throws ExoPlaybackException {
                                        MediaPeriodInfo info;
                                        this.queue.reevaluateBuffer(this.rendererPositionUs);
                                        if (this.queue.shouldLoadNextMediaPeriod() && (info = this.queue.getNextMediaPeriodInfo(this.rendererPositionUs, this.playbackInfo)) != null) {
                                            MediaPeriodHolder mediaPeriodHolder = this.queue.enqueueNextMediaPeriodHolder(this.rendererCapabilities, this.trackSelector, this.loadControl.getAllocator(), this.mediaSourceList, info, this.emptyTrackSelectorResult);
                                            mediaPeriodHolder.mediaPeriod.prepare(this, info.startPositionUs);
                                            if (this.queue.getPlayingPeriod() == mediaPeriodHolder) {
                                                resetRendererPosition(info.startPositionUs);
                                            }
                                            handleLoadingMediaPeriodChanged(false);
                                        }
                                        if (this.shouldContinueLoading) {
                                            this.shouldContinueLoading = isLoadingPossible();
                                            updateIsLoading();
                                        } else {
                                            maybeContinueLoading();
                                        }
                                    }

                                    private void maybeUpdateReadingPeriod() throws ExoPlaybackException {
                                        long streamEndPositionUs;
                                        MediaPeriodHolder readingPeriodHolder = this.queue.getReadingPeriod();
                                        if (readingPeriodHolder == null) {
                                            return;
                                        }
                                        if (readingPeriodHolder.getNext() == null || this.pendingPauseAtEndOfPeriod) {
                                            if (readingPeriodHolder.info.isFinal || this.pendingPauseAtEndOfPeriod) {
                                                for (int i = 0; i < this.renderers.length; i++) {
                                                    Renderer renderer = this.renderers[i];
                                                    SampleStream sampleStream = readingPeriodHolder.sampleStreams[i];
                                                    if (sampleStream != null && renderer.getStream() == sampleStream && renderer.hasReadStreamToEnd()) {
                                                        if (readingPeriodHolder.info.durationUs != C.TIME_UNSET && readingPeriodHolder.info.durationUs != Long.MIN_VALUE) {
                                                            streamEndPositionUs = readingPeriodHolder.getRendererOffset() + readingPeriodHolder.info.durationUs;
                                                        } else {
                                                            streamEndPositionUs = -9223372036854775807L;
                                                        }
                                                        setCurrentStreamFinal(renderer, streamEndPositionUs);
                                                    }
                                                }
                                                return;
                                            }
                                            return;
                                        }
                                        if (!hasReadingPeriodFinishedReading()) {
                                            return;
                                        }
                                        if (!readingPeriodHolder.getNext().prepared && this.rendererPositionUs < readingPeriodHolder.getNext().getStartPositionRendererTime()) {
                                            return;
                                        }
                                        TrackSelectorResult oldTrackSelectorResult = readingPeriodHolder.getTrackSelectorResult();
                                        MediaPeriodHolder readingPeriodHolder2 = this.queue.advanceReadingPeriod();
                                        TrackSelectorResult newTrackSelectorResult = readingPeriodHolder2.getTrackSelectorResult();
                                        updatePlaybackSpeedSettingsForNewPeriod(this.playbackInfo.timeline, readingPeriodHolder2.info.id, this.playbackInfo.timeline, readingPeriodHolder.info.id, C.TIME_UNSET, false);
                                        if (readingPeriodHolder2.prepared && readingPeriodHolder2.mediaPeriod.readDiscontinuity() != C.TIME_UNSET) {
                                            setAllRendererStreamsFinal(readingPeriodHolder2.getStartPositionRendererTime());
                                            return;
                                        }
                                        for (int i2 = 0; i2 < this.renderers.length; i2++) {
                                            boolean oldRendererEnabled = oldTrackSelectorResult.isRendererEnabled(i2);
                                            boolean newRendererEnabled = newTrackSelectorResult.isRendererEnabled(i2);
                                            if (oldRendererEnabled && !this.renderers[i2].isCurrentStreamFinal()) {
                                                boolean isNoSampleRenderer = this.rendererCapabilities[i2].getTrackType() == -2;
                                                RendererConfiguration oldConfig = oldTrackSelectorResult.rendererConfigurations[i2];
                                                RendererConfiguration newConfig = newTrackSelectorResult.rendererConfigurations[i2];
                                                if (!newRendererEnabled || !newConfig.equals(oldConfig) || isNoSampleRenderer) {
                                                    setCurrentStreamFinal(this.renderers[i2], readingPeriodHolder2.getStartPositionRendererTime());
                                                }
                                            }
                                        }
                                    }

                                    private void maybeUpdateReadingRenderers() throws ExoPlaybackException {
                                        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
                                        if (readingPeriod != null && this.queue.getPlayingPeriod() != readingPeriod && !readingPeriod.allRenderersInCorrectState && replaceStreamsOrDisableRendererForTransition()) {
                                            enableRenderers();
                                        }
                                    }

                                    private boolean replaceStreamsOrDisableRendererForTransition() throws ExoPlaybackException {
                                        MediaPeriodHolder readingPeriodHolder = this.queue.getReadingPeriod();
                                        TrackSelectorResult newTrackSelectorResult = readingPeriodHolder.getTrackSelectorResult();
                                        boolean needsToWaitForRendererToEnd = false;
                                        for (int i = 0; i < this.renderers.length; i++) {
                                            Renderer renderer = this.renderers[i];
                                            if (isRendererEnabled(renderer)) {
                                                boolean rendererIsReadingOldStream = renderer.getStream() != readingPeriodHolder.sampleStreams[i];
                                                boolean rendererShouldBeEnabled = newTrackSelectorResult.isRendererEnabled(i);
                                                if (!rendererShouldBeEnabled || rendererIsReadingOldStream) {
                                                    if (!renderer.isCurrentStreamFinal()) {
                                                        Format[] formats = getFormats(newTrackSelectorResult.selections[i]);
                                                        renderer.replaceStream(formats, readingPeriodHolder.sampleStreams[i], readingPeriodHolder.getStartPositionRendererTime(), readingPeriodHolder.getRendererOffset());
                                                    } else if (renderer.isEnded()) {
                                                        disableRenderer(renderer);
                                                    } else {
                                                        needsToWaitForRendererToEnd = true;
                                                    }
                                                }
                                            }
                                        }
                                        return !needsToWaitForRendererToEnd;
                                    }

                                    private void maybeUpdatePlayingPeriod() throws ExoPlaybackException {
                                        boolean advancedPlayingPeriod = false;
                                        while (shouldAdvancePlayingPeriod()) {
                                            if (advancedPlayingPeriod) {
                                                maybeNotifyPlaybackInfoChanged();
                                            }
                                            MediaPeriodHolder newPlayingPeriodHolder = (MediaPeriodHolder) Assertions.checkNotNull(this.queue.advancePlayingPeriod());
                                            boolean z = true;
                                            boolean isCancelledSSAIAdTransition = this.playbackInfo.periodId.periodUid.equals(newPlayingPeriodHolder.info.id.periodUid) && this.playbackInfo.periodId.adGroupIndex == -1 && newPlayingPeriodHolder.info.id.adGroupIndex == -1 && this.playbackInfo.periodId.nextAdGroupIndex != newPlayingPeriodHolder.info.id.nextAdGroupIndex;
                                            MediaSource.MediaPeriodId mediaPeriodId = newPlayingPeriodHolder.info.id;
                                            long j = newPlayingPeriodHolder.info.startPositionUs;
                                            long j2 = newPlayingPeriodHolder.info.requestedContentPositionUs;
                                            long j3 = newPlayingPeriodHolder.info.startPositionUs;
                                            if (isCancelledSSAIAdTransition) {
                                                z = false;
                                            }
                                            this.playbackInfo = handlePositionDiscontinuity(mediaPeriodId, j, j2, j3, z, 0);
                                            resetPendingPauseAtEndOfPeriod();
                                            updatePlaybackPositions();
                                            advancedPlayingPeriod = true;
                                        }
                                    }

                                    private void resetPendingPauseAtEndOfPeriod() {
                                        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
                                        this.pendingPauseAtEndOfPeriod = playingPeriod != null && playingPeriod.info.isLastInTimelineWindow && this.pauseAtEndOfWindow;
                                    }

                                    private boolean shouldAdvancePlayingPeriod() {
                                        MediaPeriodHolder playingPeriodHolder;
                                        MediaPeriodHolder nextPlayingPeriodHolder;
                                        return shouldPlayWhenReady() && !this.pendingPauseAtEndOfPeriod && (playingPeriodHolder = this.queue.getPlayingPeriod()) != null && (nextPlayingPeriodHolder = playingPeriodHolder.getNext()) != null && this.rendererPositionUs >= nextPlayingPeriodHolder.getStartPositionRendererTime() && nextPlayingPeriodHolder.allRenderersInCorrectState;
                                    }

                                    private boolean hasReadingPeriodFinishedReading() {
                                        MediaPeriodHolder readingPeriodHolder = this.queue.getReadingPeriod();
                                        if (!readingPeriodHolder.prepared) {
                                            return false;
                                        }
                                        for (int i = 0; i < this.renderers.length; i++) {
                                            Renderer renderer = this.renderers[i];
                                            SampleStream sampleStream = readingPeriodHolder.sampleStreams[i];
                                            if (renderer.getStream() != sampleStream || (sampleStream != null && !renderer.hasReadStreamToEnd() && !hasReachedServerSideInsertedAdsTransition(renderer, readingPeriodHolder))) {
                                                return false;
                                            }
                                        }
                                        return true;
                                    }

                                    private boolean hasReachedServerSideInsertedAdsTransition(Renderer renderer, MediaPeriodHolder reading) {
                                        MediaPeriodHolder nextPeriod = reading.getNext();
                                        return reading.info.isFollowedByTransitionToSameStream && nextPeriod.prepared && ((renderer instanceof TextRenderer) || (renderer instanceof MetadataRenderer) || renderer.getReadingPositionUs() >= nextPeriod.getStartPositionRendererTime());
                                    }

                                    private void setAllRendererStreamsFinal(long streamEndPositionUs) {
                                        for (Renderer renderer : this.renderers) {
                                            if (renderer.getStream() != null) {
                                                setCurrentStreamFinal(renderer, streamEndPositionUs);
                                            }
                                        }
                                    }

                                    private void setCurrentStreamFinal(Renderer renderer, long streamEndPositionUs) {
                                        renderer.setCurrentStreamFinal();
                                        if (renderer instanceof TextRenderer) {
                                            ((TextRenderer) renderer).setFinalStreamEndPositionUs(streamEndPositionUs);
                                        }
                                    }

                                    private void handlePeriodPrepared(MediaPeriod mediaPeriod) throws ExoPlaybackException {
                                        if (!this.queue.isLoading(mediaPeriod)) {
                                            return;
                                        }
                                        MediaPeriodHolder loadingPeriodHolder = this.queue.getLoadingPeriod();
                                        loadingPeriodHolder.handlePrepared(this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.timeline);
                                        updateLoadControlTrackSelection(loadingPeriodHolder.info.id, loadingPeriodHolder.getTrackGroups(), loadingPeriodHolder.getTrackSelectorResult());
                                        if (loadingPeriodHolder == this.queue.getPlayingPeriod()) {
                                            resetRendererPosition(loadingPeriodHolder.info.startPositionUs);
                                            enableRenderers();
                                            this.playbackInfo = handlePositionDiscontinuity(this.playbackInfo.periodId, loadingPeriodHolder.info.startPositionUs, this.playbackInfo.requestedContentPositionUs, loadingPeriodHolder.info.startPositionUs, false, 5);
                                        }
                                        maybeContinueLoading();
                                    }

                                    private void handleContinueLoadingRequested(MediaPeriod mediaPeriod) {
                                        if (!this.queue.isLoading(mediaPeriod)) {
                                            return;
                                        }
                                        this.queue.reevaluateBuffer(this.rendererPositionUs);
                                        maybeContinueLoading();
                                    }

                                    private void handlePlaybackParameters(PlaybackParameters playbackParameters, boolean acknowledgeCommand) throws ExoPlaybackException {
                                        handlePlaybackParameters(playbackParameters, playbackParameters.speed, true, acknowledgeCommand);
                                    }

                                    private void handlePlaybackParameters(PlaybackParameters playbackParameters, float currentPlaybackSpeed, boolean updatePlaybackInfo, boolean acknowledgeCommand) throws ExoPlaybackException {
                                        if (updatePlaybackInfo) {
                                            if (acknowledgeCommand) {
                                                this.playbackInfoUpdate.incrementPendingOperationAcks(1);
                                            }
                                            this.playbackInfo = this.playbackInfo.copyWithPlaybackParameters(playbackParameters);
                                        }
                                        updateTrackSelectionPlaybackSpeed(playbackParameters.speed);
                                        for (Renderer renderer : this.renderers) {
                                            if (renderer != null) {
                                                renderer.setPlaybackSpeed(currentPlaybackSpeed, playbackParameters.speed);
                                            }
                                        }
                                    }

                                    private void maybeContinueLoading() {
                                        this.shouldContinueLoading = shouldContinueLoading();
                                        if (this.shouldContinueLoading) {
                                            this.queue.getLoadingPeriod().continueLoading(this.rendererPositionUs);
                                        }
                                        updateIsLoading();
                                    }

                                    private boolean shouldContinueLoading() {
                                        long playbackPositionUs;
                                        if (!isLoadingPossible()) {
                                            return false;
                                        }
                                        MediaPeriodHolder loadingPeriodHolder = this.queue.getLoadingPeriod();
                                        long bufferedDurationUs = getTotalBufferedDurationUs(loadingPeriodHolder.getNextLoadPositionUs());
                                        if (loadingPeriodHolder == this.queue.getPlayingPeriod()) {
                                            playbackPositionUs = loadingPeriodHolder.toPeriodTime(this.rendererPositionUs);
                                        } else {
                                            playbackPositionUs = loadingPeriodHolder.toPeriodTime(this.rendererPositionUs) - loadingPeriodHolder.info.startPositionUs;
                                        }
                                        boolean shouldContinueLoading = this.loadControl.shouldContinueLoading(playbackPositionUs, bufferedDurationUs, this.mediaClock.getPlaybackParameters().speed);
                                        if (shouldContinueLoading || bufferedDurationUs >= PLAYBACK_BUFFER_EMPTY_THRESHOLD_US) {
                                            return shouldContinueLoading;
                                        }
                                        if (this.backBufferDurationUs > 0 || this.retainBackBufferFromKeyframe) {
                                            this.queue.getPlayingPeriod().mediaPeriod.discardBuffer(this.playbackInfo.positionUs, false);
                                            return this.loadControl.shouldContinueLoading(playbackPositionUs, bufferedDurationUs, this.mediaClock.getPlaybackParameters().speed);
                                        }
                                        return shouldContinueLoading;
                                    }

                                    private boolean isLoadingPossible() {
                                        MediaPeriodHolder loadingPeriodHolder = this.queue.getLoadingPeriod();
                                        if (loadingPeriodHolder == null) {
                                            return false;
                                        }
                                        long nextLoadPositionUs = loadingPeriodHolder.getNextLoadPositionUs();
                                        if (nextLoadPositionUs == Long.MIN_VALUE) {
                                            return false;
                                        }
                                        return true;
                                    }

                                    private void updateIsLoading() {
                                        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
                                        boolean isLoading = this.shouldContinueLoading || (loadingPeriod != null && loadingPeriod.mediaPeriod.isLoading());
                                        if (isLoading != this.playbackInfo.isLoading) {
                                            this.playbackInfo = this.playbackInfo.copyWithIsLoading(isLoading);
                                        }
                                    }

                                    /* JADX WARN: Code duplicated, block: B:30:0x0082  */
                                    /* JADX WARN: Code duplicated, block: B:31:0x008a  */
                                    private PlaybackInfo handlePositionDiscontinuity(MediaSource.MediaPeriodId mediaPeriodId, long positionUs, long requestedContentPositionUs, long discontinuityStartPositionUs, boolean reportDiscontinuity, int discontinuityReason) {
                                        TrackGroupArray trackGroupArray;
                                        TrackSelectorResult trackSelectorResult;
                                        List<Metadata> staticMetadata;
                                        TrackGroupArray trackGroups;
                                        TrackSelectorResult trackSelectorResult2;
                                        this.deliverPendingMessageAtStartPositionRequired = (!this.deliverPendingMessageAtStartPositionRequired && positionUs == this.playbackInfo.positionUs && mediaPeriodId.equals(this.playbackInfo.periodId)) ? false : true;
                                        resetPendingPauseAtEndOfPeriod();
                                        TrackGroupArray trackGroupArray2 = this.playbackInfo.trackGroups;
                                        TrackSelectorResult trackSelectorResult3 = this.playbackInfo.trackSelectorResult;
                                        List<Metadata> staticMetadata2 = this.playbackInfo.staticMetadata;
                                        if (this.mediaSourceList.isPrepared()) {
                                            MediaPeriodHolder playingPeriodHolder = this.queue.getPlayingPeriod();
                                            if (playingPeriodHolder == null) {
                                                trackGroups = TrackGroupArray.EMPTY;
                                            } else {
                                                trackGroups = playingPeriodHolder.getTrackGroups();
                                            }
                                            trackGroupArray2 = trackGroups;
                                            if (playingPeriodHolder == null) {
                                                trackSelectorResult2 = this.emptyTrackSelectorResult;
                                            } else {
                                                trackSelectorResult2 = playingPeriodHolder.getTrackSelectorResult();
                                            }
                                            trackSelectorResult3 = trackSelectorResult2;
                                            staticMetadata2 = extractMetadataFromTrackSelectionArray(trackSelectorResult3.selections);
                                            if (playingPeriodHolder != null && playingPeriodHolder.info.requestedContentPositionUs != requestedContentPositionUs) {
                                                playingPeriodHolder.info = playingPeriodHolder.info.copyWithRequestedContentPositionUs(requestedContentPositionUs);
                                            }
                                        } else {
                                            if (!mediaPeriodId.equals(this.playbackInfo.periodId)) {
                                                TrackGroupArray trackGroupArray3 = TrackGroupArray.EMPTY;
                                                TrackSelectorResult trackSelectorResult4 = this.emptyTrackSelectorResult;
                                                List<Metadata> staticMetadata3 = ImmutableList.of();
                                                trackGroupArray = trackGroupArray3;
                                                trackSelectorResult = trackSelectorResult4;
                                                staticMetadata = staticMetadata3;
                                            }
                                            if (reportDiscontinuity) {
                                                this.playbackInfoUpdate.setPositionDiscontinuity(discontinuityReason);
                                            }
                                            return this.playbackInfo.copyWithNewPosition(mediaPeriodId, positionUs, requestedContentPositionUs, discontinuityStartPositionUs, getTotalBufferedDurationUs(), trackGroupArray, trackSelectorResult, staticMetadata);
                                        }
                                        trackGroupArray = trackGroupArray2;
                                        trackSelectorResult = trackSelectorResult3;
                                        staticMetadata = staticMetadata2;
                                        if (reportDiscontinuity) {
                                            this.playbackInfoUpdate.setPositionDiscontinuity(discontinuityReason);
                                        }
                                        return this.playbackInfo.copyWithNewPosition(mediaPeriodId, positionUs, requestedContentPositionUs, discontinuityStartPositionUs, getTotalBufferedDurationUs(), trackGroupArray, trackSelectorResult, staticMetadata);
                                    }

                                    private ImmutableList<Metadata> extractMetadataFromTrackSelectionArray(ExoTrackSelection[] trackSelections) {
                                        ImmutableList.Builder<Metadata> result = new ImmutableList.Builder<>();
                                        boolean seenNonEmptyMetadata = false;
                                        for (ExoTrackSelection trackSelection : trackSelections) {
                                            if (trackSelection != null) {
                                                Format format = trackSelection.getFormat(0);
                                                if (format.metadata == null) {
                                                    result.add(new Metadata(new Metadata.Entry[0]));
                                                } else {
                                                    result.add(format.metadata);
                                                    seenNonEmptyMetadata = true;
                                                }
                                            }
                                        }
                                        return seenNonEmptyMetadata ? result.build() : ImmutableList.of();
                                    }

                                    private void enableRenderers() throws ExoPlaybackException {
                                        enableRenderers(new boolean[this.renderers.length]);
                                    }

                                    private void enableRenderers(boolean[] rendererWasEnabledFlags) throws ExoPlaybackException {
                                        MediaPeriodHolder readingMediaPeriod = this.queue.getReadingPeriod();
                                        TrackSelectorResult trackSelectorResult = readingMediaPeriod.getTrackSelectorResult();
                                        for (int i = 0; i < this.renderers.length; i++) {
                                            if (!trackSelectorResult.isRendererEnabled(i) && this.renderersToReset.remove(this.renderers[i])) {
                                                this.renderers[i].reset();
                                            }
                                        }
                                        for (int i2 = 0; i2 < this.renderers.length; i2++) {
                                            if (trackSelectorResult.isRendererEnabled(i2)) {
                                                enableRenderer(i2, rendererWasEnabledFlags[i2]);
                                            }
                                        }
                                        readingMediaPeriod.allRenderersInCorrectState = true;
                                    }

                                    private void enableRenderer(int rendererIndex, boolean wasRendererEnabled) throws ExoPlaybackException {
                                        Renderer renderer = this.renderers[rendererIndex];
                                        if (isRendererEnabled(renderer)) {
                                            return;
                                        }
                                        MediaPeriodHolder periodHolder = this.queue.getReadingPeriod();
                                        boolean mayRenderStartOfStream = periodHolder == this.queue.getPlayingPeriod();
                                        TrackSelectorResult trackSelectorResult = periodHolder.getTrackSelectorResult();
                                        RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[rendererIndex];
                                        ExoTrackSelection newSelection = trackSelectorResult.selections[rendererIndex];
                                        Format[] formats = getFormats(newSelection);
                                        boolean playing = shouldPlayWhenReady() && this.playbackInfo.playbackState == 3;
                                        boolean joining = !wasRendererEnabled && playing;
                                        this.enabledRendererCount++;
                                        this.renderersToReset.add(renderer);
                                        renderer.enable(rendererConfiguration, formats, periodHolder.sampleStreams[rendererIndex], this.rendererPositionUs, joining, mayRenderStartOfStream, periodHolder.getStartPositionRendererTime(), periodHolder.getRendererOffset());
                                        renderer.handleMessage(11, new Renderer.WakeupListener() { // from class: androidx.media3.exoplayer.ExoPlayerImplInternal.1
                                            @Override // androidx.media3.exoplayer.Renderer.WakeupListener
                                            public void onSleep() {
                                                ExoPlayerImplInternal.this.requestForRendererSleep = true;
                                            }

                                            @Override // androidx.media3.exoplayer.Renderer.WakeupListener
                                            public void onWakeup() {
                                                ExoPlayerImplInternal.this.handler.sendEmptyMessage(2);
                                            }
                                        });
                                        this.mediaClock.onRendererEnabled(renderer);
                                        if (playing) {
                                            renderer.start();
                                        }
                                    }

                                    private void releaseRenderers() {
                                        for (int i = 0; i < this.renderers.length; i++) {
                                            this.rendererCapabilities[i].clearListener();
                                            this.renderers[i].release();
                                        }
                                    }

                                    private void handleLoadingMediaPeriodChanged(boolean loadingTrackSelectionChanged) {
                                        long bufferedPositionUs;
                                        MediaPeriodHolder loadingMediaPeriodHolder = this.queue.getLoadingPeriod();
                                        MediaSource.MediaPeriodId loadingMediaPeriodId = loadingMediaPeriodHolder == null ? this.playbackInfo.periodId : loadingMediaPeriodHolder.info.id;
                                        boolean loadingMediaPeriodChanged = !this.playbackInfo.loadingMediaPeriodId.equals(loadingMediaPeriodId);
                                        if (loadingMediaPeriodChanged) {
                                            this.playbackInfo = this.playbackInfo.copyWithLoadingMediaPeriodId(loadingMediaPeriodId);
                                        }
                                        PlaybackInfo playbackInfo = this.playbackInfo;
                                        if (loadingMediaPeriodHolder == null) {
                                            bufferedPositionUs = this.playbackInfo.positionUs;
                                        } else {
                                            bufferedPositionUs = loadingMediaPeriodHolder.getBufferedPositionUs();
                                        }
                                        playbackInfo.bufferedPositionUs = bufferedPositionUs;
                                        this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
                                        if ((loadingMediaPeriodChanged || loadingTrackSelectionChanged) && loadingMediaPeriodHolder != null && loadingMediaPeriodHolder.prepared) {
                                            updateLoadControlTrackSelection(loadingMediaPeriodHolder.info.id, loadingMediaPeriodHolder.getTrackGroups(), loadingMediaPeriodHolder.getTrackSelectorResult());
                                        }
                                    }

                                    private long getTotalBufferedDurationUs() {
                                        return getTotalBufferedDurationUs(this.playbackInfo.bufferedPositionUs);
                                    }

                                    private long getTotalBufferedDurationUs(long bufferedPositionInLoadingPeriodUs) {
                                        MediaPeriodHolder loadingPeriodHolder = this.queue.getLoadingPeriod();
                                        if (loadingPeriodHolder == null) {
                                            return 0L;
                                        }
                                        long totalBufferedDurationUs = bufferedPositionInLoadingPeriodUs - loadingPeriodHolder.toPeriodTime(this.rendererPositionUs);
                                        return Math.max(0L, totalBufferedDurationUs);
                                    }

                                    private void updateLoadControlTrackSelection(MediaSource.MediaPeriodId mediaPeriodId, TrackGroupArray trackGroups, TrackSelectorResult trackSelectorResult) {
                                        this.loadControl.onTracksSelected(this.playbackInfo.timeline, mediaPeriodId, this.renderers, trackGroups, trackSelectorResult.selections);
                                    }

                                    private boolean shouldPlayWhenReady() {
                                        return this.playbackInfo.playWhenReady && this.playbackInfo.playbackSuppressionReason == 0;
                                    }

                                    private static PositionUpdateForPlaylistChange resolvePositionForPlaylistChange(Timeline timeline, PlaybackInfo playbackInfo, SeekPosition pendingInitialSeekPosition, MediaPeriodQueue queue, int repeatMode, boolean shuffleModeEnabled, Timeline.Window window, Timeline.Period period) {
                                        long oldContentPositionUs;
                                        boolean isUsingPlaceholderPeriod;
                                        int startAtDefaultPositionWindowIndex;
                                        boolean forceBufferingState;
                                        boolean endPlayback;
                                        boolean setTargetLiveOffset;
                                        int startAtDefaultPositionWindowIndex2;
                                        Timeline timeline2;
                                        Timeline.Period period2;
                                        long newContentPositionUs;
                                        long contentPositionForAdResolutionUs;
                                        long periodPositionUs;
                                        long periodPositionUs2;
                                        if (timeline.isEmpty()) {
                                            return new PositionUpdateForPlaylistChange(PlaybackInfo.getDummyPeriodForEmptyTimeline(), 0L, C.TIME_UNSET, false, true, false);
                                        }
                                        MediaSource.MediaPeriodId oldPeriodId = playbackInfo.periodId;
                                        Object newPeriodUid = oldPeriodId.periodUid;
                                        boolean isUsingPlaceholderPeriod2 = isUsingPlaceholderPeriod(playbackInfo, period);
                                        if (playbackInfo.periodId.isAd() || isUsingPlaceholderPeriod2) {
                                            oldContentPositionUs = playbackInfo.requestedContentPositionUs;
                                        } else {
                                            oldContentPositionUs = playbackInfo.positionUs;
                                        }
                                        long newContentPositionUs2 = oldContentPositionUs;
                                        int startAtDefaultPositionWindowIndex3 = -1;
                                        boolean forceBufferingState2 = false;
                                        boolean endPlayback2 = false;
                                        boolean setTargetLiveOffset2 = false;
                                        boolean onlyNextAdGroupIndexIncreased = false;
                                        if (pendingInitialSeekPosition == null) {
                                            isUsingPlaceholderPeriod = isUsingPlaceholderPeriod2;
                                            if (playbackInfo.timeline.isEmpty()) {
                                                int startAtDefaultPositionWindowIndex4 = timeline.getFirstWindowIndex(shuffleModeEnabled);
                                                startAtDefaultPositionWindowIndex = startAtDefaultPositionWindowIndex4;
                                                forceBufferingState = false;
                                                endPlayback = false;
                                                setTargetLiveOffset = false;
                                            } else if (timeline.getIndexOfPeriod(newPeriodUid) == -1) {
                                                Object subsequentPeriodUid = resolveSubsequentPeriod(window, period, repeatMode, shuffleModeEnabled, newPeriodUid, playbackInfo.timeline, timeline);
                                                if (subsequentPeriodUid == null) {
                                                    endPlayback2 = true;
                                                    startAtDefaultPositionWindowIndex2 = timeline.getFirstWindowIndex(shuffleModeEnabled);
                                                } else {
                                                    startAtDefaultPositionWindowIndex2 = timeline.getPeriodByUid(subsequentPeriodUid, period).windowIndex;
                                                }
                                                startAtDefaultPositionWindowIndex = startAtDefaultPositionWindowIndex2;
                                                forceBufferingState = false;
                                                endPlayback = endPlayback2;
                                                setTargetLiveOffset = false;
                                            } else if (oldContentPositionUs == C.TIME_UNSET) {
                                                int startAtDefaultPositionWindowIndex5 = timeline.getPeriodByUid(newPeriodUid, period).windowIndex;
                                                startAtDefaultPositionWindowIndex = startAtDefaultPositionWindowIndex5;
                                                forceBufferingState = false;
                                                endPlayback = false;
                                                setTargetLiveOffset = false;
                                            } else if (!isUsingPlaceholderPeriod) {
                                                startAtDefaultPositionWindowIndex = -1;
                                                forceBufferingState = false;
                                                endPlayback = false;
                                                setTargetLiveOffset = false;
                                            } else {
                                                playbackInfo.timeline.getPeriodByUid(oldPeriodId.periodUid, period);
                                                if (playbackInfo.timeline.getWindow(period.windowIndex, window).firstPeriodIndex == playbackInfo.timeline.getIndexOfPeriod(oldPeriodId.periodUid)) {
                                                    long windowPositionUs = period.getPositionInWindowUs() + oldContentPositionUs;
                                                    int windowIndex = timeline.getPeriodByUid(newPeriodUid, period).windowIndex;
                                                    Pair<Object, Long> periodPositionUs3 = timeline.getPeriodPositionUs(window, period, windowIndex, windowPositionUs);
                                                    newPeriodUid = periodPositionUs3.first;
                                                    newContentPositionUs2 = ((Long) periodPositionUs3.second).longValue();
                                                }
                                                startAtDefaultPositionWindowIndex = -1;
                                                forceBufferingState = false;
                                                endPlayback = false;
                                                setTargetLiveOffset = true;
                                            }
                                        } else {
                                            isUsingPlaceholderPeriod = isUsingPlaceholderPeriod2;
                                            Pair<Object, Long> periodPosition = resolveSeekPositionUs(timeline, pendingInitialSeekPosition, true, repeatMode, shuffleModeEnabled, window, period);
                                            if (periodPosition == null) {
                                                endPlayback2 = true;
                                                startAtDefaultPositionWindowIndex3 = timeline.getFirstWindowIndex(shuffleModeEnabled);
                                            } else {
                                                if (pendingInitialSeekPosition.windowPositionUs == C.TIME_UNSET) {
                                                    startAtDefaultPositionWindowIndex3 = timeline.getPeriodByUid(periodPosition.first, period).windowIndex;
                                                } else {
                                                    newPeriodUid = periodPosition.first;
                                                    newContentPositionUs2 = ((Long) periodPosition.second).longValue();
                                                    setTargetLiveOffset2 = true;
                                                }
                                                forceBufferingState2 = playbackInfo.playbackState == 4;
                                            }
                                            startAtDefaultPositionWindowIndex = startAtDefaultPositionWindowIndex3;
                                            forceBufferingState = forceBufferingState2;
                                            endPlayback = endPlayback2;
                                            setTargetLiveOffset = setTargetLiveOffset2;
                                        }
                                        long contentPositionForAdResolutionUs2 = newContentPositionUs2;
                                        if (startAtDefaultPositionWindowIndex == -1) {
                                            timeline2 = timeline;
                                            period2 = period;
                                            newContentPositionUs = newContentPositionUs2;
                                            contentPositionForAdResolutionUs = contentPositionForAdResolutionUs2;
                                        } else {
                                            timeline2 = timeline;
                                            period2 = period;
                                            Pair<Object, Long> defaultPositionUs = timeline2.getPeriodPositionUs(window, period2, startAtDefaultPositionWindowIndex, C.TIME_UNSET);
                                            newPeriodUid = defaultPositionUs.first;
                                            long contentPositionForAdResolutionUs3 = ((Long) defaultPositionUs.second).longValue();
                                            newContentPositionUs = -9223372036854775807L;
                                            contentPositionForAdResolutionUs = contentPositionForAdResolutionUs3;
                                        }
                                        MediaSource.MediaPeriodId periodIdWithAds = queue.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(timeline2, newPeriodUid, contentPositionForAdResolutionUs);
                                        boolean earliestCuePointIsUnchangedOrLater = periodIdWithAds.nextAdGroupIndex == -1 || (oldPeriodId.nextAdGroupIndex != -1 && periodIdWithAds.nextAdGroupIndex >= oldPeriodId.nextAdGroupIndex);
                                        boolean sameOldAndNewPeriodUid = oldPeriodId.periodUid.equals(newPeriodUid);
                                        if (sameOldAndNewPeriodUid && !oldPeriodId.isAd() && !periodIdWithAds.isAd() && earliestCuePointIsUnchangedOrLater) {
                                            onlyNextAdGroupIndexIncreased = true;
                                        }
                                        boolean isInStreamAdChange = isIgnorableServerSideAdInsertionPeriodChange(isUsingPlaceholderPeriod, oldPeriodId, oldContentPositionUs, periodIdWithAds, timeline2.getPeriodByUid(newPeriodUid, period2), newContentPositionUs);
                                        MediaSource.MediaPeriodId newPeriodId = (onlyNextAdGroupIndexIncreased || isInStreamAdChange) ? oldPeriodId : periodIdWithAds;
                                        long periodPositionUs4 = contentPositionForAdResolutionUs;
                                        if (!newPeriodId.isAd()) {
                                            periodPositionUs = periodPositionUs4;
                                        } else if (newPeriodId.equals(oldPeriodId)) {
                                            periodPositionUs = playbackInfo.positionUs;
                                        } else {
                                            timeline2.getPeriodByUid(newPeriodId.periodUid, period2);
                                            if (newPeriodId.adIndexInAdGroup == period2.getFirstAdIndexToPlay(newPeriodId.adGroupIndex)) {
                                                periodPositionUs2 = period2.getAdResumePositionUs();
                                            } else {
                                                periodPositionUs2 = 0;
                                            }
                                            periodPositionUs = periodPositionUs2;
                                        }
                                        return new PositionUpdateForPlaylistChange(newPeriodId, periodPositionUs, newContentPositionUs, forceBufferingState, endPlayback, setTargetLiveOffset);
                                    }

                                    private static boolean isIgnorableServerSideAdInsertionPeriodChange(boolean isUsingPlaceholderPeriod, MediaSource.MediaPeriodId oldPeriodId, long oldContentPositionUs, MediaSource.MediaPeriodId newPeriodId, Timeline.Period newPeriod, long newContentPositionUs) {
                                        if (isUsingPlaceholderPeriod || oldContentPositionUs != newContentPositionUs || !oldPeriodId.periodUid.equals(newPeriodId.periodUid)) {
                                            return false;
                                        }
                                        if (oldPeriodId.isAd() && newPeriod.isServerSideInsertedAdGroup(oldPeriodId.adGroupIndex)) {
                                            return (newPeriod.getAdState(oldPeriodId.adGroupIndex, oldPeriodId.adIndexInAdGroup) == 4 || newPeriod.getAdState(oldPeriodId.adGroupIndex, oldPeriodId.adIndexInAdGroup) == 2) ? false : true;
                                        }
                                        return newPeriodId.isAd() && newPeriod.isServerSideInsertedAdGroup(newPeriodId.adGroupIndex);
                                    }

                                    private static boolean isUsingPlaceholderPeriod(PlaybackInfo playbackInfo, Timeline.Period period) {
                                        MediaSource.MediaPeriodId periodId = playbackInfo.periodId;
                                        Timeline timeline = playbackInfo.timeline;
                                        return timeline.isEmpty() || timeline.getPeriodByUid(periodId.periodUid, period).isPlaceholder;
                                    }

                                    private static boolean resolvePendingMessagePosition(PendingMessageInfo pendingMessageInfo, Timeline newTimeline, Timeline previousTimeline, int repeatMode, boolean shuffleModeEnabled, Timeline.Window window, Timeline.Period period) {
                                        long jMsToUs;
                                        if (pendingMessageInfo.resolvedPeriodUid == null) {
                                            if (pendingMessageInfo.message.getPositionMs() == Long.MIN_VALUE) {
                                                jMsToUs = C.TIME_UNSET;
                                            } else {
                                                jMsToUs = Util.msToUs(pendingMessageInfo.message.getPositionMs());
                                            }
                                            long requestPositionUs = jMsToUs;
                                            Pair<Object, Long> periodPosition = resolveSeekPositionUs(newTimeline, new SeekPosition(pendingMessageInfo.message.getTimeline(), pendingMessageInfo.message.getMediaItemIndex(), requestPositionUs), false, repeatMode, shuffleModeEnabled, window, period);
                                            if (periodPosition == null) {
                                                return false;
                                            }
                                            pendingMessageInfo.setResolvedPosition(newTimeline.getIndexOfPeriod(periodPosition.first), ((Long) periodPosition.second).longValue(), periodPosition.first);
                                            if (pendingMessageInfo.message.getPositionMs() == Long.MIN_VALUE) {
                                                resolvePendingMessageEndOfStreamPosition(newTimeline, pendingMessageInfo, window, period);
                                            }
                                            return true;
                                        }
                                        int index = newTimeline.getIndexOfPeriod(pendingMessageInfo.resolvedPeriodUid);
                                        if (index == -1) {
                                            return false;
                                        }
                                        if (pendingMessageInfo.message.getPositionMs() == Long.MIN_VALUE) {
                                            resolvePendingMessageEndOfStreamPosition(newTimeline, pendingMessageInfo, window, period);
                                            return true;
                                        }
                                        pendingMessageInfo.resolvedPeriodIndex = index;
                                        previousTimeline.getPeriodByUid(pendingMessageInfo.resolvedPeriodUid, period);
                                        if (period.isPlaceholder && previousTimeline.getWindow(period.windowIndex, window).firstPeriodIndex == previousTimeline.getIndexOfPeriod(pendingMessageInfo.resolvedPeriodUid)) {
                                            long windowPositionUs = pendingMessageInfo.resolvedPeriodTimeUs + period.getPositionInWindowUs();
                                            int windowIndex = newTimeline.getPeriodByUid(pendingMessageInfo.resolvedPeriodUid, period).windowIndex;
                                            Pair<Object, Long> periodPositionUs = newTimeline.getPeriodPositionUs(window, period, windowIndex, windowPositionUs);
                                            pendingMessageInfo.setResolvedPosition(newTimeline.getIndexOfPeriod(periodPositionUs.first), ((Long) periodPositionUs.second).longValue(), periodPositionUs.first);
                                        }
                                        return true;
                                    }

                                    private static void resolvePendingMessageEndOfStreamPosition(Timeline timeline, PendingMessageInfo messageInfo, Timeline.Window window, Timeline.Period period) {
                                        int windowIndex = timeline.getPeriodByUid(messageInfo.resolvedPeriodUid, period).windowIndex;
                                        int lastPeriodIndex = timeline.getWindow(windowIndex, window).lastPeriodIndex;
                                        Object lastPeriodUid = timeline.getPeriod(lastPeriodIndex, period, true).uid;
                                        long positionUs = period.durationUs != C.TIME_UNSET ? period.durationUs - 1 : Long.MAX_VALUE;
                                        messageInfo.setResolvedPosition(lastPeriodIndex, positionUs, lastPeriodUid);
                                    }

                                    private static Pair<Object, Long> resolveSeekPositionUs(Timeline timeline, SeekPosition seekPosition, boolean trySubsequentPeriods, int repeatMode, boolean shuffleModeEnabled, Timeline.Window window, Timeline.Period period) {
                                        Timeline timeline2;
                                        Object periodUid;
                                        Timeline seekTimeline = seekPosition.timeline;
                                        if (timeline.isEmpty()) {
                                            return null;
                                        }
                                        if (!seekTimeline.isEmpty()) {
                                            timeline2 = seekTimeline;
                                        } else {
                                            timeline2 = timeline;
                                        }
                                        try {
                                            Pair<Object, Long> periodPositionUs = timeline2.getPeriodPositionUs(window, period, seekPosition.windowIndex, seekPosition.windowPositionUs);
                                            Timeline timeline3 = timeline2;
                                            if (timeline.equals(timeline3)) {
                                                return periodPositionUs;
                                            }
                                            int periodIndex = timeline.getIndexOfPeriod(periodPositionUs.first);
                                            if (periodIndex != -1) {
                                                if (timeline3.getPeriodByUid(periodPositionUs.first, period).isPlaceholder && timeline3.getWindow(period.windowIndex, window).firstPeriodIndex == timeline3.getIndexOfPeriod(periodPositionUs.first)) {
                                                    int newWindowIndex = timeline.getPeriodByUid(periodPositionUs.first, period).windowIndex;
                                                    return timeline.getPeriodPositionUs(window, period, newWindowIndex, seekPosition.windowPositionUs);
                                                }
                                                return periodPositionUs;
                                            }
                                            if (!trySubsequentPeriods || (periodUid = resolveSubsequentPeriod(window, period, repeatMode, shuffleModeEnabled, periodPositionUs.first, timeline3, timeline)) == null) {
                                                return null;
                                            }
                                            return timeline.getPeriodPositionUs(window, period, timeline.getPeriodByUid(periodUid, period).windowIndex, C.TIME_UNSET);
                                        } catch (IndexOutOfBoundsException e) {
                                            return null;
                                        }
                                    }

                                    static Object resolveSubsequentPeriod(Timeline.Window window, Timeline.Period period, int repeatMode, boolean shuffleModeEnabled, Object oldPeriodUid, Timeline oldTimeline, Timeline newTimeline) {
                                        int oldPeriodIndex = oldTimeline.getIndexOfPeriod(oldPeriodUid);
                                        int maxIterations = oldTimeline.getPeriodCount();
                                        int newPeriodIndex = -1;
                                        for (int i = 0; i < maxIterations && newPeriodIndex == -1 && (oldPeriodIndex = oldTimeline.getNextPeriodIndex(oldPeriodIndex, period, window, repeatMode, shuffleModeEnabled)) != -1; i++) {
                                            newPeriodIndex = newTimeline.getIndexOfPeriod(oldTimeline.getUidOfPeriod(oldPeriodIndex));
                                        }
                                        if (newPeriodIndex == -1) {
                                            return null;
                                        }
                                        return newTimeline.getUidOfPeriod(newPeriodIndex);
                                    }

                                    private static Format[] getFormats(ExoTrackSelection newSelection) {
                                        int length = newSelection != null ? newSelection.length() : 0;
                                        Format[] formats = new Format[length];
                                        for (int i = 0; i < length; i++) {
                                            formats[i] = newSelection.getFormat(i);
                                        }
                                        return formats;
                                    }

                                    private static boolean isRendererEnabled(Renderer renderer) {
                                        return renderer.getState() != 0;
                                    }

                                    private static final class SeekPosition {
                                        public final Timeline timeline;
                                        public final int windowIndex;
                                        public final long windowPositionUs;

                                        public SeekPosition(Timeline timeline, int windowIndex, long windowPositionUs) {
                                            this.timeline = timeline;
                                            this.windowIndex = windowIndex;
                                            this.windowPositionUs = windowPositionUs;
                                        }
                                    }

                                    private static final class PositionUpdateForPlaylistChange {
                                        public final boolean endPlayback;
                                        public final boolean forceBufferingState;
                                        public final MediaSource.MediaPeriodId periodId;
                                        public final long periodPositionUs;
                                        public final long requestedContentPositionUs;
                                        public final boolean setTargetLiveOffset;

                                        public PositionUpdateForPlaylistChange(MediaSource.MediaPeriodId periodId, long periodPositionUs, long requestedContentPositionUs, boolean forceBufferingState, boolean endPlayback, boolean setTargetLiveOffset) {
                                            this.periodId = periodId;
                                            this.periodPositionUs = periodPositionUs;
                                            this.requestedContentPositionUs = requestedContentPositionUs;
                                            this.forceBufferingState = forceBufferingState;
                                            this.endPlayback = endPlayback;
                                            this.setTargetLiveOffset = setTargetLiveOffset;
                                        }
                                    }

                                    private static final class PendingMessageInfo implements Comparable<PendingMessageInfo> {
                                        public final PlayerMessage message;
                                        public int resolvedPeriodIndex;
                                        public long resolvedPeriodTimeUs;
                                        public Object resolvedPeriodUid;

                                        public PendingMessageInfo(PlayerMessage message) {
                                            this.message = message;
                                        }

                                        public void setResolvedPosition(int periodIndex, long periodTimeUs, Object periodUid) {
                                            this.resolvedPeriodIndex = periodIndex;
                                            this.resolvedPeriodTimeUs = periodTimeUs;
                                            this.resolvedPeriodUid = periodUid;
                                        }

                                        @Override // java.lang.Comparable
                                        public int compareTo(PendingMessageInfo other) {
                                            if ((this.resolvedPeriodUid == null) != (other.resolvedPeriodUid == null)) {
                                                return this.resolvedPeriodUid != null ? -1 : 1;
                                            }
                                            if (this.resolvedPeriodUid == null) {
                                                return 0;
                                            }
                                            int comparePeriodIndex = this.resolvedPeriodIndex - other.resolvedPeriodIndex;
                                            if (comparePeriodIndex != 0) {
                                                return comparePeriodIndex;
                                            }
                                            return Util.compareLong(this.resolvedPeriodTimeUs, other.resolvedPeriodTimeUs);
                                        }
                                    }

                                    private static final class MediaSourceListUpdateMessage {
                                        private final List<MediaSourceList.MediaSourceHolder> mediaSourceHolders;
                                        private final long positionUs;
                                        private final ShuffleOrder shuffleOrder;
                                        private final int windowIndex;

                                        private MediaSourceListUpdateMessage(List<MediaSourceList.MediaSourceHolder> mediaSourceHolders, ShuffleOrder shuffleOrder, int windowIndex, long positionUs) {
                                            this.mediaSourceHolders = mediaSourceHolders;
                                            this.shuffleOrder = shuffleOrder;
                                            this.windowIndex = windowIndex;
                                            this.positionUs = positionUs;
                                        }
                                    }

                                    private static class MoveMediaItemsMessage {
                                        public final int fromIndex;
                                        public final int newFromIndex;
                                        public final ShuffleOrder shuffleOrder;
                                        public final int toIndex;

                                        public MoveMediaItemsMessage(int fromIndex, int toIndex, int newFromIndex, ShuffleOrder shuffleOrder) {
                                            this.fromIndex = fromIndex;
                                            this.toIndex = toIndex;
                                            this.newFromIndex = newFromIndex;
                                            this.shuffleOrder = shuffleOrder;
                                        }
                                    }
                                }
