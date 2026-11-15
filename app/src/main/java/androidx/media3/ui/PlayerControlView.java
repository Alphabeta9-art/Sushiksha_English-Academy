package androidx.media3.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.RepeatModeUtil;
import androidx.media3.common.util.Util;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.imagepipeline.transcoder.JpegTranscoderUtils;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: loaded from: classes11.dex */
public class PlayerControlView extends FrameLayout {
    public static final int DEFAULT_REPEAT_TOGGLE_MODES = 0;
    public static final int DEFAULT_SHOW_TIMEOUT_MS = 5000;
    public static final int DEFAULT_TIME_BAR_MIN_UPDATE_INTERVAL_MS = 200;
    private static final int MAX_UPDATE_INTERVAL_MS = 1000;
    public static final int MAX_WINDOWS_FOR_MULTI_WINDOW_TIME_BAR = 100;
    private static final float[] PLAYBACK_SPEEDS;
    private static final int SETTINGS_AUDIO_TRACK_SELECTION_POSITION = 1;
    private static final int SETTINGS_PLAYBACK_SPEED_POSITION = 0;
    private long[] adGroupTimesMs;
    private final View audioTrackButton;
    private final AudioTrackSelectionAdapter audioTrackSelectionAdapter;
    private final float buttonAlphaDisabled;
    private final float buttonAlphaEnabled;
    private final ComponentListener componentListener;
    private final PlayerControlViewLayoutManager controlViewLayoutManager;
    private long currentWindowOffset;
    private final TextView durationView;
    private long[] extraAdGroupTimesMs;
    private boolean[] extraPlayedAdGroups;
    private final View fastForwardButton;
    private final TextView fastForwardButtonTextView;
    private final StringBuilder formatBuilder;
    private final Formatter formatter;
    private final ImageView fullScreenButton;
    private final String fullScreenEnterContentDescription;
    private final Drawable fullScreenEnterDrawable;
    private final String fullScreenExitContentDescription;
    private final Drawable fullScreenExitDrawable;
    private boolean isAttachedToWindow;
    private boolean isFullScreen;
    private final ImageView minimalFullScreenButton;
    private boolean multiWindowTimeBar;
    private boolean needToHideBars;
    private final View nextButton;
    private OnFullScreenModeChangedListener onFullScreenModeChangedListener;
    private final Timeline.Period period;
    private final View playPauseButton;
    private final PlaybackSpeedAdapter playbackSpeedAdapter;
    private final View playbackSpeedButton;
    private boolean[] playedAdGroups;
    private Player player;
    private final TextView positionView;
    private final View previousButton;
    private ProgressUpdateListener progressUpdateListener;
    private final String repeatAllButtonContentDescription;
    private final Drawable repeatAllButtonDrawable;
    private final String repeatOffButtonContentDescription;
    private final Drawable repeatOffButtonDrawable;
    private final String repeatOneButtonContentDescription;
    private final Drawable repeatOneButtonDrawable;
    private final ImageView repeatToggleButton;
    private int repeatToggleModes;
    private final Resources resources;
    private final View rewindButton;
    private final TextView rewindButtonTextView;
    private boolean scrubbing;
    private final SettingsAdapter settingsAdapter;
    private final View settingsButton;
    private final RecyclerView settingsView;
    private final PopupWindow settingsWindow;
    private final int settingsWindowMargin;
    private boolean showMultiWindowTimeBar;
    private int showTimeoutMs;
    private final ImageView shuffleButton;
    private final Drawable shuffleOffButtonDrawable;
    private final String shuffleOffContentDescription;
    private final Drawable shuffleOnButtonDrawable;
    private final String shuffleOnContentDescription;
    private final ImageView subtitleButton;
    private final Drawable subtitleOffButtonDrawable;
    private final String subtitleOffContentDescription;
    private final Drawable subtitleOnButtonDrawable;
    private final String subtitleOnContentDescription;
    private final TextTrackSelectionAdapter textTrackSelectionAdapter;
    private final TimeBar timeBar;
    private int timeBarMinUpdateIntervalMs;
    private final TrackNameProvider trackNameProvider;
    private final Runnable updateProgressAction;
    private final CopyOnWriteArrayList<VisibilityListener> visibilityListeners;
    private final View vrButton;
    private final Timeline.Window window;

    @Deprecated
    public interface OnFullScreenModeChangedListener {
        void onFullScreenModeChanged(boolean z);
    }

    public interface ProgressUpdateListener {
        void onProgressUpdate(long j, long j2);
    }

    @Deprecated
    public interface VisibilityListener {
        void onVisibilityChange(int i);
    }

    static {
        MediaLibraryInfo.registerModule("media3.ui");
        PLAYBACK_SPEEDS = new float[]{0.25f, 0.5f, 0.75f, 1.0f, 1.25f, 1.5f, 2.0f};
    }

    public PlayerControlView(Context context) {
        this(context, null);
    }

    public PlayerControlView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayerControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, attrs);
    }

    public PlayerControlView(Context context, AttributeSet attrs, int defStyleAttr, AttributeSet playbackAttrs) {
        boolean showNextButton;
        boolean showShuffleButton;
        boolean showSubtitleButton;
        int controllerLayoutId;
        boolean showSubtitleButton2;
        boolean animationEnabled;
        boolean showRewindButton;
        boolean animationEnabled2;
        boolean showVrButton;
        boolean animationEnabled3;
        TextView textView;
        Context context2;
        super(context, attrs, defStyleAttr);
        int controllerLayoutId2 = R.layout.exo_player_control_view;
        this.showTimeoutMs = 5000;
        this.repeatToggleModes = 0;
        this.timeBarMinUpdateIntervalMs = 200;
        if (playbackAttrs == null) {
            showNextButton = true;
            showShuffleButton = false;
            showSubtitleButton = false;
            controllerLayoutId = controllerLayoutId2;
            showSubtitleButton2 = true;
            animationEnabled = true;
            showRewindButton = false;
            animationEnabled2 = true;
            showVrButton = true;
        } else {
            TypedArray a = context.getTheme().obtainStyledAttributes(playbackAttrs, R.styleable.PlayerControlView, defStyleAttr, 0);
            try {
                int controllerLayoutId3 = a.getResourceId(R.styleable.PlayerControlView_controller_layout_id, controllerLayoutId2);
                this.showTimeoutMs = a.getInt(R.styleable.PlayerControlView_show_timeout, this.showTimeoutMs);
                this.repeatToggleModes = getRepeatToggleModes(a, this.repeatToggleModes);
                boolean showRewindButton2 = a.getBoolean(R.styleable.PlayerControlView_show_rewind_button, true);
                boolean showFastForwardButton = a.getBoolean(R.styleable.PlayerControlView_show_fastforward_button, true);
                boolean showPreviousButton = a.getBoolean(R.styleable.PlayerControlView_show_previous_button, true);
                boolean showNextButton2 = a.getBoolean(R.styleable.PlayerControlView_show_next_button, true);
                boolean showShuffleButton2 = a.getBoolean(R.styleable.PlayerControlView_show_shuffle_button, false);
                boolean showSubtitleButton3 = a.getBoolean(R.styleable.PlayerControlView_show_subtitle_button, false);
                boolean showVrButton2 = a.getBoolean(R.styleable.PlayerControlView_show_vr_button, false);
                setTimeBarMinUpdateInterval(a.getInt(R.styleable.PlayerControlView_time_bar_min_update_interval, this.timeBarMinUpdateIntervalMs));
                boolean animationEnabled4 = a.getBoolean(R.styleable.PlayerControlView_animation_enabled, true);
                a.recycle();
                showNextButton = showNextButton2;
                showShuffleButton = showShuffleButton2;
                showSubtitleButton = showSubtitleButton3;
                controllerLayoutId = controllerLayoutId3;
                showSubtitleButton2 = showRewindButton2;
                animationEnabled = animationEnabled4;
                showRewindButton = showVrButton2;
                animationEnabled2 = showFastForwardButton;
                showVrButton = showPreviousButton;
            } catch (Throwable th) {
                a.recycle();
                throw th;
            }
        }
        LayoutInflater.from(context).inflate(controllerLayoutId, this);
        setDescendantFocusability(262144);
        this.componentListener = new ComponentListener();
        this.visibilityListeners = new CopyOnWriteArrayList<>();
        this.period = new Timeline.Period();
        this.window = new Timeline.Window();
        this.formatBuilder = new StringBuilder();
        this.formatter = new Formatter(this.formatBuilder, Locale.getDefault());
        this.adGroupTimesMs = new long[0];
        this.playedAdGroups = new boolean[0];
        this.extraAdGroupTimesMs = new long[0];
        this.extraPlayedAdGroups = new boolean[0];
        this.updateProgressAction = new Runnable() { // from class: androidx.media3.ui.PlayerControlView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.updateProgress();
            }
        };
        this.durationView = (TextView) findViewById(R.id.exo_duration);
        this.positionView = (TextView) findViewById(R.id.exo_position);
        this.subtitleButton = (ImageView) findViewById(R.id.exo_subtitle);
        if (this.subtitleButton != null) {
            this.subtitleButton.setOnClickListener(this.componentListener);
        }
        this.fullScreenButton = (ImageView) findViewById(R.id.exo_fullscreen);
        initializeFullScreenButton(this.fullScreenButton, new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onFullScreenButtonClicked(view);
            }
        });
        this.minimalFullScreenButton = (ImageView) findViewById(R.id.exo_minimal_fullscreen);
        initializeFullScreenButton(this.minimalFullScreenButton, new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onFullScreenButtonClicked(view);
            }
        });
        this.settingsButton = findViewById(R.id.exo_settings);
        if (this.settingsButton != null) {
            this.settingsButton.setOnClickListener(this.componentListener);
        }
        this.playbackSpeedButton = findViewById(R.id.exo_playback_speed);
        if (this.playbackSpeedButton != null) {
            this.playbackSpeedButton.setOnClickListener(this.componentListener);
        }
        this.audioTrackButton = findViewById(R.id.exo_audio_track);
        if (this.audioTrackButton != null) {
            this.audioTrackButton.setOnClickListener(this.componentListener);
        }
        TimeBar customTimeBar = (TimeBar) findViewById(R.id.exo_progress);
        View timeBarPlaceholder = findViewById(R.id.exo_progress_placeholder);
        if (customTimeBar != null) {
            this.timeBar = customTimeBar;
            animationEnabled3 = animationEnabled;
            textView = null;
            context2 = context;
        } else if (timeBarPlaceholder != null) {
            animationEnabled3 = animationEnabled;
            textView = null;
            context2 = context;
            DefaultTimeBar defaultTimeBar = new DefaultTimeBar(context2, null, 0, playbackAttrs, R.style.ExoStyledControls_TimeBar);
            defaultTimeBar.setId(R.id.exo_progress);
            defaultTimeBar.setLayoutParams(timeBarPlaceholder.getLayoutParams());
            ViewGroup parent = (ViewGroup) timeBarPlaceholder.getParent();
            timeBarPlaceholder = timeBarPlaceholder;
            int timeBarIndex = parent.indexOfChild(timeBarPlaceholder);
            parent.removeView(timeBarPlaceholder);
            parent.addView(defaultTimeBar, timeBarIndex);
            this.timeBar = defaultTimeBar;
        } else {
            animationEnabled3 = animationEnabled;
            textView = null;
            context2 = context;
            this.timeBar = null;
        }
        if (this.timeBar != null) {
            this.timeBar.addListener(this.componentListener);
        }
        this.playPauseButton = findViewById(R.id.exo_play_pause);
        if (this.playPauseButton != null) {
            this.playPauseButton.setOnClickListener(this.componentListener);
        }
        this.previousButton = findViewById(R.id.exo_prev);
        if (this.previousButton != null) {
            this.previousButton.setOnClickListener(this.componentListener);
        }
        this.nextButton = findViewById(R.id.exo_next);
        if (this.nextButton != null) {
            this.nextButton.setOnClickListener(this.componentListener);
        }
        Typeface typeface = ResourcesCompat.getFont(context2, R.font.roboto_medium_numbers);
        View rewButton = findViewById(R.id.exo_rew);
        this.rewindButtonTextView = rewButton == null ? (TextView) findViewById(R.id.exo_rew_with_amount) : textView;
        if (this.rewindButtonTextView != null) {
            this.rewindButtonTextView.setTypeface(typeface);
        }
        this.rewindButton = rewButton == null ? this.rewindButtonTextView : rewButton;
        if (this.rewindButton != null) {
            this.rewindButton.setOnClickListener(this.componentListener);
        }
        View ffwdButton = findViewById(R.id.exo_ffwd);
        this.fastForwardButtonTextView = ffwdButton == null ? (TextView) findViewById(R.id.exo_ffwd_with_amount) : textView;
        if (this.fastForwardButtonTextView != null) {
            this.fastForwardButtonTextView.setTypeface(typeface);
        }
        this.fastForwardButton = ffwdButton == null ? this.fastForwardButtonTextView : ffwdButton;
        if (this.fastForwardButton != null) {
            this.fastForwardButton.setOnClickListener(this.componentListener);
        }
        this.repeatToggleButton = (ImageView) findViewById(R.id.exo_repeat_toggle);
        if (this.repeatToggleButton != null) {
            this.repeatToggleButton.setOnClickListener(this.componentListener);
        }
        this.shuffleButton = (ImageView) findViewById(R.id.exo_shuffle);
        if (this.shuffleButton != null) {
            this.shuffleButton.setOnClickListener(this.componentListener);
        }
        this.resources = context2.getResources();
        this.buttonAlphaEnabled = this.resources.getInteger(R.integer.exo_media_button_opacity_percentage_enabled) / 100.0f;
        this.buttonAlphaDisabled = this.resources.getInteger(R.integer.exo_media_button_opacity_percentage_disabled) / 100.0f;
        this.vrButton = findViewById(R.id.exo_vr);
        if (this.vrButton != null) {
            updateButton(false, this.vrButton);
        }
        this.controlViewLayoutManager = new PlayerControlViewLayoutManager(this);
        this.controlViewLayoutManager.setAnimationEnabled(animationEnabled3);
        String[] settingTexts = {this.resources.getString(R.string.exo_controls_playback_speed), this.resources.getString(R.string.exo_track_selection_title_audio)};
        Drawable[] settingIcons = {Util.getDrawable(context2, this.resources, R.drawable.exo_styled_controls_speed), Util.getDrawable(context2, this.resources, R.drawable.exo_styled_controls_audiotrack)};
        this.settingsAdapter = new SettingsAdapter(settingTexts, settingIcons);
        this.settingsWindowMargin = this.resources.getDimensionPixelSize(R.dimen.exo_settings_offset);
        this.settingsView = (RecyclerView) LayoutInflater.from(context2).inflate(R.layout.exo_styled_settings_list, (ViewGroup) null);
        this.settingsView.setAdapter(this.settingsAdapter);
        this.settingsView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.settingsWindow = new PopupWindow((View) this.settingsView, -2, -2, true);
        if (Util.SDK_INT < 23) {
            this.settingsWindow.setBackgroundDrawable(new ColorDrawable(0));
        }
        this.settingsWindow.setOnDismissListener(this.componentListener);
        this.needToHideBars = true;
        this.trackNameProvider = new DefaultTrackNameProvider(getResources());
        this.subtitleOnButtonDrawable = Util.getDrawable(context2, this.resources, R.drawable.exo_styled_controls_subtitle_on);
        this.subtitleOffButtonDrawable = Util.getDrawable(context2, this.resources, R.drawable.exo_styled_controls_subtitle_off);
        this.subtitleOnContentDescription = this.resources.getString(R.string.exo_controls_cc_enabled_description);
        this.subtitleOffContentDescription = this.resources.getString(R.string.exo_controls_cc_disabled_description);
        this.textTrackSelectionAdapter = new TextTrackSelectionAdapter();
        this.audioTrackSelectionAdapter = new AudioTrackSelectionAdapter();
        this.playbackSpeedAdapter = new PlaybackSpeedAdapter(this.resources.getStringArray(R.array.exo_controls_playback_speeds), PLAYBACK_SPEEDS);
        this.fullScreenExitDrawable = Util.getDrawable(context2, this.resources, R.drawable.exo_styled_controls_fullscreen_exit);
        this.fullScreenEnterDrawable = Util.getDrawable(context2, this.resources, R.drawable.exo_styled_controls_fullscreen_enter);
        this.repeatOffButtonDrawable = Util.getDrawable(context2, this.resources, R.drawable.exo_styled_controls_repeat_off);
        this.repeatOneButtonDrawable = Util.getDrawable(context2, this.resources, R.drawable.exo_styled_controls_repeat_one);
        this.repeatAllButtonDrawable = Util.getDrawable(context2, this.resources, R.drawable.exo_styled_controls_repeat_all);
        this.shuffleOnButtonDrawable = Util.getDrawable(context2, this.resources, R.drawable.exo_styled_controls_shuffle_on);
        this.shuffleOffButtonDrawable = Util.getDrawable(context2, this.resources, R.drawable.exo_styled_controls_shuffle_off);
        this.fullScreenExitContentDescription = this.resources.getString(R.string.exo_controls_fullscreen_exit_description);
        this.fullScreenEnterContentDescription = this.resources.getString(R.string.exo_controls_fullscreen_enter_description);
        this.repeatOffButtonContentDescription = this.resources.getString(R.string.exo_controls_repeat_off_description);
        this.repeatOneButtonContentDescription = this.resources.getString(R.string.exo_controls_repeat_one_description);
        this.repeatAllButtonContentDescription = this.resources.getString(R.string.exo_controls_repeat_all_description);
        this.shuffleOnContentDescription = this.resources.getString(R.string.exo_controls_shuffle_on_description);
        this.shuffleOffContentDescription = this.resources.getString(R.string.exo_controls_shuffle_off_description);
        ViewGroup bottomBar = (ViewGroup) findViewById(R.id.exo_bottom_bar);
        this.controlViewLayoutManager.setShowButton(bottomBar, true);
        this.controlViewLayoutManager.setShowButton(this.fastForwardButton, animationEnabled2);
        this.controlViewLayoutManager.setShowButton(this.rewindButton, showSubtitleButton2);
        this.controlViewLayoutManager.setShowButton(this.previousButton, showVrButton);
        this.controlViewLayoutManager.setShowButton(this.nextButton, showNextButton);
        this.controlViewLayoutManager.setShowButton(this.shuffleButton, showShuffleButton);
        this.controlViewLayoutManager.setShowButton(this.subtitleButton, showSubtitleButton);
        boolean showVrButton3 = showRewindButton;
        this.controlViewLayoutManager.setShowButton(this.vrButton, showVrButton3);
        this.controlViewLayoutManager.setShowButton(this.repeatToggleButton, this.repeatToggleModes != 0);
        addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: androidx.media3.ui.PlayerControlView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                this.f$0.onLayoutChange(view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        boolean z = true;
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        if (player != null && player.getApplicationLooper() != Looper.getMainLooper()) {
            z = false;
        }
        Assertions.checkArgument(z);
        if (this.player == player) {
            return;
        }
        if (this.player != null) {
            this.player.removeListener(this.componentListener);
        }
        this.player = player;
        if (player != null) {
            player.addListener(this.componentListener);
        }
        updateAll();
    }

    public void setShowMultiWindowTimeBar(boolean showMultiWindowTimeBar) {
        this.showMultiWindowTimeBar = showMultiWindowTimeBar;
        updateTimeline();
    }

    public void setExtraAdGroupMarkers(long[] extraAdGroupTimesMs, boolean[] extraPlayedAdGroups) {
        if (extraAdGroupTimesMs == null) {
            this.extraAdGroupTimesMs = new long[0];
            this.extraPlayedAdGroups = new boolean[0];
        } else {
            boolean[] extraPlayedAdGroups2 = (boolean[]) Assertions.checkNotNull(extraPlayedAdGroups);
            Assertions.checkArgument(extraAdGroupTimesMs.length == extraPlayedAdGroups2.length);
            this.extraAdGroupTimesMs = extraAdGroupTimesMs;
            this.extraPlayedAdGroups = extraPlayedAdGroups2;
        }
        updateTimeline();
    }

    @Deprecated
    public void addVisibilityListener(VisibilityListener listener) {
        Assertions.checkNotNull(listener);
        this.visibilityListeners.add(listener);
    }

    @Deprecated
    public void removeVisibilityListener(VisibilityListener listener) {
        this.visibilityListeners.remove(listener);
    }

    public void setProgressUpdateListener(ProgressUpdateListener listener) {
        this.progressUpdateListener = listener;
    }

    public void setShowRewindButton(boolean showRewindButton) {
        this.controlViewLayoutManager.setShowButton(this.rewindButton, showRewindButton);
        updateNavigation();
    }

    public void setShowFastForwardButton(boolean showFastForwardButton) {
        this.controlViewLayoutManager.setShowButton(this.fastForwardButton, showFastForwardButton);
        updateNavigation();
    }

    public void setShowPreviousButton(boolean showPreviousButton) {
        this.controlViewLayoutManager.setShowButton(this.previousButton, showPreviousButton);
        updateNavigation();
    }

    public void setShowNextButton(boolean showNextButton) {
        this.controlViewLayoutManager.setShowButton(this.nextButton, showNextButton);
        updateNavigation();
    }

    public int getShowTimeoutMs() {
        return this.showTimeoutMs;
    }

    public void setShowTimeoutMs(int showTimeoutMs) {
        this.showTimeoutMs = showTimeoutMs;
        if (isFullyVisible()) {
            this.controlViewLayoutManager.resetHideCallbacks();
        }
    }

    public int getRepeatToggleModes() {
        return this.repeatToggleModes;
    }

    public void setRepeatToggleModes(int repeatToggleModes) {
        this.repeatToggleModes = repeatToggleModes;
        if (this.player != null && this.player.isCommandAvailable(15)) {
            int currentMode = this.player.getRepeatMode();
            if (repeatToggleModes == 0 && currentMode != 0) {
                this.player.setRepeatMode(0);
            } else if (repeatToggleModes == 1 && currentMode == 2) {
                this.player.setRepeatMode(1);
            } else if (repeatToggleModes == 2 && currentMode == 1) {
                this.player.setRepeatMode(2);
            }
        }
        this.controlViewLayoutManager.setShowButton(this.repeatToggleButton, repeatToggleModes != 0);
        updateRepeatModeButton();
    }

    public boolean getShowShuffleButton() {
        return this.controlViewLayoutManager.getShowButton(this.shuffleButton);
    }

    public void setShowShuffleButton(boolean showShuffleButton) {
        this.controlViewLayoutManager.setShowButton(this.shuffleButton, showShuffleButton);
        updateShuffleButton();
    }

    public boolean getShowSubtitleButton() {
        return this.controlViewLayoutManager.getShowButton(this.subtitleButton);
    }

    public void setShowSubtitleButton(boolean showSubtitleButton) {
        this.controlViewLayoutManager.setShowButton(this.subtitleButton, showSubtitleButton);
    }

    public boolean getShowVrButton() {
        return this.controlViewLayoutManager.getShowButton(this.vrButton);
    }

    public void setShowVrButton(boolean showVrButton) {
        this.controlViewLayoutManager.setShowButton(this.vrButton, showVrButton);
    }

    public void setVrButtonListener(View.OnClickListener onClickListener) {
        if (this.vrButton != null) {
            this.vrButton.setOnClickListener(onClickListener);
            updateButton(onClickListener != null, this.vrButton);
        }
    }

    public void setAnimationEnabled(boolean animationEnabled) {
        this.controlViewLayoutManager.setAnimationEnabled(animationEnabled);
    }

    public boolean isAnimationEnabled() {
        return this.controlViewLayoutManager.isAnimationEnabled();
    }

    public void setTimeBarMinUpdateInterval(int minUpdateIntervalMs) {
        this.timeBarMinUpdateIntervalMs = Util.constrainValue(minUpdateIntervalMs, 16, 1000);
    }

    @Deprecated
    public void setOnFullScreenModeChangedListener(OnFullScreenModeChangedListener listener) {
        this.onFullScreenModeChangedListener = listener;
        updateFullScreenButtonVisibility(this.fullScreenButton, listener != null);
        updateFullScreenButtonVisibility(this.minimalFullScreenButton, listener != null);
    }

    public void show() {
        this.controlViewLayoutManager.show();
    }

    public void hide() {
        this.controlViewLayoutManager.hide();
    }

    public void hideImmediately() {
        this.controlViewLayoutManager.hideImmediately();
    }

    public boolean isFullyVisible() {
        return this.controlViewLayoutManager.isFullyVisible();
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    void notifyOnVisibilityChange() {
        for (VisibilityListener visibilityListener : this.visibilityListeners) {
            visibilityListener.onVisibilityChange(getVisibility());
        }
    }

    void updateAll() {
        updatePlayPauseButton();
        updateNavigation();
        updateRepeatModeButton();
        updateShuffleButton();
        updateTrackLists();
        updatePlaybackSpeedList();
        updateTimeline();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlayPauseButton() {
        int drawableRes;
        int stringRes;
        if (isVisible() && this.isAttachedToWindow && this.playPauseButton != null) {
            boolean shouldShowPlayButton = Util.shouldShowPlayButton(this.player);
            if (shouldShowPlayButton) {
                drawableRes = R.drawable.exo_styled_controls_play;
            } else {
                drawableRes = R.drawable.exo_styled_controls_pause;
            }
            if (shouldShowPlayButton) {
                stringRes = R.string.exo_controls_play_description;
            } else {
                stringRes = R.string.exo_controls_pause_description;
            }
            ((ImageView) this.playPauseButton).setImageDrawable(Util.getDrawable(getContext(), this.resources, drawableRes));
            this.playPauseButton.setContentDescription(this.resources.getString(stringRes));
            boolean enablePlayPause = shouldEnablePlayPauseButton();
            updateButton(enablePlayPause, this.playPauseButton);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNavigation() {
        boolean zIsCommandAvailable;
        if (!isVisible() || !this.isAttachedToWindow) {
            return;
        }
        Player player = this.player;
        boolean enableSeeking = false;
        boolean enablePrevious = false;
        boolean enableRewind = false;
        boolean enableFastForward = false;
        boolean enableNext = false;
        if (player != null) {
            if (this.showMultiWindowTimeBar && canShowMultiWindowTimeBar(player, this.window)) {
                zIsCommandAvailable = player.isCommandAvailable(10);
            } else {
                zIsCommandAvailable = player.isCommandAvailable(5);
            }
            enableSeeking = zIsCommandAvailable;
            enablePrevious = player.isCommandAvailable(7);
            enableRewind = player.isCommandAvailable(11);
            enableFastForward = player.isCommandAvailable(12);
            enableNext = player.isCommandAvailable(9);
        }
        if (enableRewind) {
            updateRewindButton();
        }
        if (enableFastForward) {
            updateFastForwardButton();
        }
        updateButton(enablePrevious, this.previousButton);
        updateButton(enableRewind, this.rewindButton);
        updateButton(enableFastForward, this.fastForwardButton);
        updateButton(enableNext, this.nextButton);
        if (this.timeBar != null) {
            this.timeBar.setEnabled(enableSeeking);
        }
    }

    private void updateRewindButton() {
        long rewindMs = this.player != null ? this.player.getSeekBackIncrement() : 5000L;
        int rewindSec = (int) (rewindMs / 1000);
        if (this.rewindButtonTextView != null) {
            this.rewindButtonTextView.setText(String.valueOf(rewindSec));
        }
        if (this.rewindButton != null) {
            this.rewindButton.setContentDescription(this.resources.getQuantityString(R.plurals.exo_controls_rewind_by_amount_description, rewindSec, Integer.valueOf(rewindSec)));
        }
    }

    private void updateFastForwardButton() {
        long fastForwardMs = this.player != null ? this.player.getSeekForwardIncrement() : 15000L;
        int fastForwardSec = (int) (fastForwardMs / 1000);
        if (this.fastForwardButtonTextView != null) {
            this.fastForwardButtonTextView.setText(String.valueOf(fastForwardSec));
        }
        if (this.fastForwardButton != null) {
            this.fastForwardButton.setContentDescription(this.resources.getQuantityString(R.plurals.exo_controls_fastforward_by_amount_description, fastForwardSec, Integer.valueOf(fastForwardSec)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRepeatModeButton() {
        if (!isVisible() || !this.isAttachedToWindow || this.repeatToggleButton == null) {
            return;
        }
        if (this.repeatToggleModes == 0) {
            updateButton(false, this.repeatToggleButton);
        }
        Player player = this.player;
        if (player == null || !player.isCommandAvailable(15)) {
            updateButton(false, this.repeatToggleButton);
            this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
            this.repeatToggleButton.setContentDescription(this.repeatOffButtonContentDescription);
            return;
        }
        updateButton(true, this.repeatToggleButton);
        switch (player.getRepeatMode()) {
            case 0:
                this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatOffButtonContentDescription);
                break;
            case 1:
                this.repeatToggleButton.setImageDrawable(this.repeatOneButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatOneButtonContentDescription);
                break;
            case 2:
                this.repeatToggleButton.setImageDrawable(this.repeatAllButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatAllButtonContentDescription);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateShuffleButton() {
        String str;
        if (!isVisible() || !this.isAttachedToWindow || this.shuffleButton == null) {
            return;
        }
        Player player = this.player;
        if (!this.controlViewLayoutManager.getShowButton(this.shuffleButton)) {
            updateButton(false, this.shuffleButton);
            return;
        }
        if (player == null || !player.isCommandAvailable(14)) {
            updateButton(false, this.shuffleButton);
            this.shuffleButton.setImageDrawable(this.shuffleOffButtonDrawable);
            this.shuffleButton.setContentDescription(this.shuffleOffContentDescription);
            return;
        }
        updateButton(true, this.shuffleButton);
        this.shuffleButton.setImageDrawable(player.getShuffleModeEnabled() ? this.shuffleOnButtonDrawable : this.shuffleOffButtonDrawable);
        ImageView imageView = this.shuffleButton;
        if (player.getShuffleModeEnabled()) {
            str = this.shuffleOnContentDescription;
        } else {
            str = this.shuffleOffContentDescription;
        }
        imageView.setContentDescription(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTrackLists() {
        initTrackSelectionAdapter();
        updateButton(this.textTrackSelectionAdapter.getItemCount() > 0, this.subtitleButton);
        updateSettingsButton();
    }

    private void initTrackSelectionAdapter() {
        this.textTrackSelectionAdapter.clear();
        this.audioTrackSelectionAdapter.clear();
        if (this.player == null || !this.player.isCommandAvailable(30) || !this.player.isCommandAvailable(29)) {
            return;
        }
        Tracks tracks = this.player.getCurrentTracks();
        this.audioTrackSelectionAdapter.init(gatherSupportedTrackInfosOfType(tracks, 1));
        if (this.controlViewLayoutManager.getShowButton(this.subtitleButton)) {
            this.textTrackSelectionAdapter.init(gatherSupportedTrackInfosOfType(tracks, 3));
        } else {
            this.textTrackSelectionAdapter.init(ImmutableList.of());
        }
    }

    private ImmutableList<TrackInformation> gatherSupportedTrackInfosOfType(Tracks tracks, int trackType) {
        ImmutableList.Builder<TrackInformation> trackInfos = new ImmutableList.Builder<>();
        List<Tracks.Group> trackGroups = tracks.getGroups();
        for (int trackGroupIndex = 0; trackGroupIndex < trackGroups.size(); trackGroupIndex++) {
            Tracks.Group trackGroup = trackGroups.get(trackGroupIndex);
            if (trackGroup.getType() == trackType) {
                for (int trackIndex = 0; trackIndex < trackGroup.length; trackIndex++) {
                    if (trackGroup.isTrackSupported(trackIndex)) {
                        Format trackFormat = trackGroup.getTrackFormat(trackIndex);
                        if ((trackFormat.selectionFlags & 2) == 0) {
                            String trackName = this.trackNameProvider.getTrackName(trackFormat);
                            trackInfos.add(new TrackInformation(tracks, trackGroupIndex, trackIndex, trackName));
                        }
                    }
                }
            }
        }
        return trackInfos.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTimeline() {
        Timeline timeline;
        int j;
        Player player = this.player;
        if (player != null) {
            int i = 1;
            this.multiWindowTimeBar = this.showMultiWindowTimeBar && canShowMultiWindowTimeBar(player, this.window);
            long j2 = 0;
            this.currentWindowOffset = 0L;
            long durationUs = 0;
            int adGroupCount = 0;
            if (player.isCommandAvailable(17)) {
                timeline = player.getCurrentTimeline();
            } else {
                timeline = Timeline.EMPTY;
            }
            boolean zIsEmpty = timeline.isEmpty();
            long j3 = C.TIME_UNSET;
            if (!zIsEmpty) {
                int currentWindowIndex = player.getCurrentMediaItemIndex();
                int firstWindowIndex = this.multiWindowTimeBar ? 0 : currentWindowIndex;
                int lastWindowIndex = this.multiWindowTimeBar ? timeline.getWindowCount() - 1 : currentWindowIndex;
                int i2 = firstWindowIndex;
                while (i2 <= lastWindowIndex) {
                    if (i2 == currentWindowIndex) {
                        this.currentWindowOffset = Util.usToMs(durationUs);
                    }
                    timeline.getWindow(i2, this.window);
                    if (this.window.durationUs == j3) {
                        Assertions.checkState(!this.multiWindowTimeBar);
                        break;
                    }
                    int j4 = this.window.firstPeriodIndex;
                    while (j4 <= this.window.lastPeriodIndex) {
                        timeline.getPeriod(j4, this.period);
                        int removedGroups = this.period.getRemovedAdGroupCount();
                        int totalGroups = this.period.getAdGroupCount();
                        long j5 = j3;
                        int adGroupIndex = removedGroups;
                        while (adGroupIndex < totalGroups) {
                            long adGroupTimeInPeriodUs = this.period.getAdGroupTimeUs(adGroupIndex);
                            if (adGroupTimeInPeriodUs == Long.MIN_VALUE) {
                                j = j4;
                                if (this.period.durationUs != j5) {
                                    adGroupTimeInPeriodUs = this.period.durationUs;
                                }
                                adGroupCount = adGroupCount;
                                adGroupIndex++;
                                j4 = j;
                            } else {
                                j = j4;
                            }
                            long adGroupTimeInWindowUs = adGroupTimeInPeriodUs + this.period.getPositionInWindowUs();
                            if (adGroupTimeInWindowUs < j2) {
                                adGroupCount = adGroupCount;
                            } else {
                                if (adGroupCount == this.adGroupTimesMs.length) {
                                    int newLength = this.adGroupTimesMs.length == 0 ? i : this.adGroupTimesMs.length * 2;
                                    this.adGroupTimesMs = Arrays.copyOf(this.adGroupTimesMs, newLength);
                                    this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, newLength);
                                }
                                this.adGroupTimesMs[adGroupCount] = Util.usToMs(durationUs + adGroupTimeInWindowUs);
                                this.playedAdGroups[adGroupCount] = this.period.hasPlayedAdGroup(adGroupIndex);
                                adGroupCount++;
                            }
                            adGroupIndex++;
                            j4 = j;
                        }
                        j4++;
                        j3 = j5;
                    }
                    durationUs += this.window.durationUs;
                    i2++;
                    i = i;
                    j2 = j2;
                }
            } else if (player.isCommandAvailable(16)) {
                long playerDurationMs = player.getContentDuration();
                if (playerDurationMs != C.TIME_UNSET) {
                    durationUs = Util.msToUs(playerDurationMs);
                }
            }
            long durationMs = Util.usToMs(durationUs);
            if (this.durationView != null) {
                this.durationView.setText(Util.getStringForTime(this.formatBuilder, this.formatter, durationMs));
            }
            if (this.timeBar != null) {
                this.timeBar.setDuration(durationMs);
                int extraAdGroupCount = this.extraAdGroupTimesMs.length;
                int totalAdGroupCount = adGroupCount + extraAdGroupCount;
                if (totalAdGroupCount > this.adGroupTimesMs.length) {
                    this.adGroupTimesMs = Arrays.copyOf(this.adGroupTimesMs, totalAdGroupCount);
                    this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, totalAdGroupCount);
                }
                System.arraycopy(this.extraAdGroupTimesMs, 0, this.adGroupTimesMs, adGroupCount, extraAdGroupCount);
                System.arraycopy(this.extraPlayedAdGroups, 0, this.playedAdGroups, adGroupCount, extraAdGroupCount);
                this.timeBar.setAdGroupTimesMs(this.adGroupTimesMs, this.playedAdGroups, totalAdGroupCount);
            }
            updateProgress();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProgress() {
        long mediaTimeDelayMs;
        if (!isVisible() || !this.isAttachedToWindow) {
            return;
        }
        Player player = this.player;
        long position = 0;
        long bufferedPosition = 0;
        if (player != null && player.isCommandAvailable(16)) {
            position = this.currentWindowOffset + player.getContentPosition();
            bufferedPosition = this.currentWindowOffset + player.getContentBufferedPosition();
        }
        if (this.positionView != null && !this.scrubbing) {
            this.positionView.setText(Util.getStringForTime(this.formatBuilder, this.formatter, position));
        }
        if (this.timeBar != null) {
            this.timeBar.setPosition(position);
            this.timeBar.setBufferedPosition(bufferedPosition);
        }
        if (this.progressUpdateListener != null) {
            this.progressUpdateListener.onProgressUpdate(position, bufferedPosition);
        }
        removeCallbacks(this.updateProgressAction);
        int playbackState = player == null ? 1 : player.getPlaybackState();
        if (player != null && player.isPlaying()) {
            if (this.timeBar != null) {
                mediaTimeDelayMs = this.timeBar.getPreferredUpdateDelay();
            } else {
                mediaTimeDelayMs = 1000;
            }
            long mediaTimeUntilNextFullSecondMs = 1000 - (position % 1000);
            long mediaTimeDelayMs2 = Math.min(mediaTimeDelayMs, mediaTimeUntilNextFullSecondMs);
            float playbackSpeed = player.getPlaybackParameters().speed;
            long delayMs = playbackSpeed > 0.0f ? (long) (mediaTimeDelayMs2 / playbackSpeed) : 1000L;
            postDelayed(this.updateProgressAction, Util.constrainValue(delayMs, this.timeBarMinUpdateIntervalMs, 1000L));
            return;
        }
        if (playbackState != 4 && playbackState != 1) {
            postDelayed(this.updateProgressAction, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlaybackSpeedList() {
        if (this.player == null) {
            return;
        }
        this.playbackSpeedAdapter.updateSelectedIndex(this.player.getPlaybackParameters().speed);
        this.settingsAdapter.setSubTextAtPosition(0, this.playbackSpeedAdapter.getSelectedText());
        updateSettingsButton();
    }

    private void updateSettingsButton() {
        updateButton(this.settingsAdapter.hasSettingsToShow(), this.settingsButton);
    }

    private void updateSettingsWindowSize() {
        this.settingsView.measure(0, 0);
        int maxWidth = getWidth() - (this.settingsWindowMargin * 2);
        int itemWidth = this.settingsView.getMeasuredWidth();
        int width = Math.min(itemWidth, maxWidth);
        this.settingsWindow.setWidth(width);
        int maxHeight = getHeight() - (this.settingsWindowMargin * 2);
        int totalHeight = this.settingsView.getMeasuredHeight();
        int height = Math.min(maxHeight, totalHeight);
        this.settingsWindow.setHeight(height);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displaySettingsWindow(RecyclerView.Adapter<?> adapter, View anchorView) {
        this.settingsView.setAdapter(adapter);
        updateSettingsWindowSize();
        this.needToHideBars = false;
        this.settingsWindow.dismiss();
        this.needToHideBars = true;
        int xoff = (getWidth() - this.settingsWindow.getWidth()) - this.settingsWindowMargin;
        int yoff = (-this.settingsWindow.getHeight()) - this.settingsWindowMargin;
        this.settingsWindow.showAsDropDown(anchorView, xoff, yoff);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlaybackSpeed(float speed) {
        if (this.player == null || !this.player.isCommandAvailable(13)) {
            return;
        }
        this.player.setPlaybackParameters(this.player.getPlaybackParameters().withSpeed(speed));
    }

    void requestPlayPauseFocus() {
        if (this.playPauseButton != null) {
            this.playPauseButton.requestFocus();
        }
    }

    private void updateButton(boolean enabled, View view) {
        if (view == null) {
            return;
        }
        view.setEnabled(enabled);
        view.setAlpha(enabled ? this.buttonAlphaEnabled : this.buttonAlphaDisabled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void seekToTimeBarPosition(Player player, long positionMs) {
        if (this.multiWindowTimeBar) {
            if (player.isCommandAvailable(17) && player.isCommandAvailable(10)) {
                Timeline timeline = player.getCurrentTimeline();
                int windowCount = timeline.getWindowCount();
                int windowIndex = 0;
                while (true) {
                    long windowDurationMs = timeline.getWindow(windowIndex, this.window).getDurationMs();
                    if (positionMs < windowDurationMs) {
                        break;
                    }
                    if (windowIndex == windowCount - 1) {
                        positionMs = windowDurationMs;
                        break;
                    } else {
                        positionMs -= windowDurationMs;
                        windowIndex++;
                    }
                }
                player.seekTo(windowIndex, positionMs);
            }
        } else if (player.isCommandAvailable(5)) {
            player.seekTo(positionMs);
        }
        updateProgress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFullScreenButtonClicked(View v) {
        if (this.onFullScreenModeChangedListener == null) {
            return;
        }
        this.isFullScreen = !this.isFullScreen;
        updateFullScreenButtonForState(this.fullScreenButton, this.isFullScreen);
        updateFullScreenButtonForState(this.minimalFullScreenButton, this.isFullScreen);
        if (this.onFullScreenModeChangedListener != null) {
            this.onFullScreenModeChangedListener.onFullScreenModeChanged(this.isFullScreen);
        }
    }

    private void updateFullScreenButtonForState(ImageView fullScreenButton, boolean isFullScreen) {
        if (fullScreenButton == null) {
            return;
        }
        if (isFullScreen) {
            fullScreenButton.setImageDrawable(this.fullScreenExitDrawable);
            fullScreenButton.setContentDescription(this.fullScreenExitContentDescription);
        } else {
            fullScreenButton.setImageDrawable(this.fullScreenEnterDrawable);
            fullScreenButton.setContentDescription(this.fullScreenEnterContentDescription);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSettingViewClicked(int position) {
        if (position == 0) {
            displaySettingsWindow(this.playbackSpeedAdapter, (View) Assertions.checkNotNull(this.settingsButton));
        } else if (position == 1) {
            displaySettingsWindow(this.audioTrackSelectionAdapter, (View) Assertions.checkNotNull(this.settingsButton));
        } else {
            this.settingsWindow.dismiss();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.controlViewLayoutManager.onAttachedToWindow();
        this.isAttachedToWindow = true;
        if (isFullyVisible()) {
            this.controlViewLayoutManager.resetHideCallbacks();
        }
        updateAll();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.controlViewLayoutManager.onDetachedFromWindow();
        this.isAttachedToWindow = false;
        removeCallbacks(this.updateProgressAction);
        this.controlViewLayoutManager.removeHideCallbacks();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        return dispatchMediaKeyEvent(event) || super.dispatchKeyEvent(event);
    }

    public boolean dispatchMediaKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        Player player = this.player;
        if (player == null || !isHandledMediaKey(keyCode)) {
            return false;
        }
        if (event.getAction() == 0) {
            if (keyCode == 90) {
                if (player.getPlaybackState() != 4 && player.isCommandAvailable(12)) {
                    player.seekForward();
                    return true;
                }
                return true;
            }
            if (keyCode == 89 && player.isCommandAvailable(11)) {
                player.seekBack();
                return true;
            }
            if (event.getRepeatCount() == 0) {
                switch (keyCode) {
                    case 79:
                    case JpegTranscoderUtils.DEFAULT_JPEG_QUALITY /* 85 */:
                        Util.handlePlayPauseButtonAction(player);
                        return true;
                    case 87:
                        if (player.isCommandAvailable(9)) {
                            player.seekToNext();
                            return true;
                        }
                        return true;
                    case 88:
                        if (player.isCommandAvailable(7)) {
                            player.seekToPrevious();
                            return true;
                        }
                        return true;
                    case WebSocketProtocol.PAYLOAD_SHORT /* 126 */:
                        Util.handlePlayButtonAction(player);
                        return true;
                    case 127:
                        Util.handlePauseButtonAction(player);
                        return true;
                    default:
                        return true;
                }
            }
            return true;
        }
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.controlViewLayoutManager.onLayout(changed, left, top, right, bottom);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        int width = right - left;
        int height = bottom - top;
        int oldWidth = oldRight - oldLeft;
        int oldHeight = oldBottom - oldTop;
        if ((width != oldWidth || height != oldHeight) && this.settingsWindow.isShowing()) {
            updateSettingsWindowSize();
            int xOffset = (getWidth() - this.settingsWindow.getWidth()) - this.settingsWindowMargin;
            int yOffset = (-this.settingsWindow.getHeight()) - this.settingsWindowMargin;
            this.settingsWindow.update(v, xOffset, yOffset, -1, -1);
        }
    }

    private boolean shouldEnablePlayPauseButton() {
        return (this.player == null || !this.player.isCommandAvailable(1) || (this.player.isCommandAvailable(17) && this.player.getCurrentTimeline().isEmpty())) ? false : true;
    }

    private static boolean isHandledMediaKey(int keyCode) {
        return keyCode == 90 || keyCode == 89 || keyCode == 85 || keyCode == 79 || keyCode == 126 || keyCode == 127 || keyCode == 87 || keyCode == 88;
    }

    private static boolean canShowMultiWindowTimeBar(Player player, Timeline.Window window) {
        Timeline timeline;
        int windowCount;
        if (!player.isCommandAvailable(17) || (windowCount = (timeline = player.getCurrentTimeline()).getWindowCount()) <= 1 || windowCount > 100) {
            return false;
        }
        for (int i = 0; i < windowCount; i++) {
            if (timeline.getWindow(i, window).durationUs == C.TIME_UNSET) {
                return false;
            }
        }
        return true;
    }

    private static void initializeFullScreenButton(View fullScreenButton, View.OnClickListener listener) {
        if (fullScreenButton == null) {
            return;
        }
        fullScreenButton.setVisibility(8);
        fullScreenButton.setOnClickListener(listener);
    }

    private static void updateFullScreenButtonVisibility(View fullScreenButton, boolean visible) {
        if (fullScreenButton == null) {
            return;
        }
        if (visible) {
            fullScreenButton.setVisibility(0);
        } else {
            fullScreenButton.setVisibility(8);
        }
    }

    private static int getRepeatToggleModes(TypedArray a, int defaultValue) {
        return a.getInt(R.styleable.PlayerControlView_repeat_toggle_modes, defaultValue);
    }

    private final class ComponentListener implements Player.Listener, TimeBar.OnScrubListener, View.OnClickListener, PopupWindow.OnDismissListener {
        private ComponentListener() {
        }

        @Override // androidx.media3.common.Player.Listener
        public void onEvents(Player player, Player.Events events) {
            if (events.containsAny(4, 5, 13)) {
                PlayerControlView.this.updatePlayPauseButton();
            }
            if (events.containsAny(4, 5, 7, 13)) {
                PlayerControlView.this.updateProgress();
            }
            if (events.containsAny(8, 13)) {
                PlayerControlView.this.updateRepeatModeButton();
            }
            if (events.containsAny(9, 13)) {
                PlayerControlView.this.updateShuffleButton();
            }
            if (events.containsAny(8, 9, 11, 0, 16, 17, 13)) {
                PlayerControlView.this.updateNavigation();
            }
            if (events.containsAny(11, 0, 13)) {
                PlayerControlView.this.updateTimeline();
            }
            if (events.containsAny(12, 13)) {
                PlayerControlView.this.updatePlaybackSpeedList();
            }
            if (events.containsAny(2, 13)) {
                PlayerControlView.this.updateTrackLists();
            }
        }

        @Override // androidx.media3.ui.TimeBar.OnScrubListener
        public void onScrubStart(TimeBar timeBar, long position) {
            PlayerControlView.this.scrubbing = true;
            if (PlayerControlView.this.positionView != null) {
                PlayerControlView.this.positionView.setText(Util.getStringForTime(PlayerControlView.this.formatBuilder, PlayerControlView.this.formatter, position));
            }
            PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
        }

        @Override // androidx.media3.ui.TimeBar.OnScrubListener
        public void onScrubMove(TimeBar timeBar, long position) {
            if (PlayerControlView.this.positionView != null) {
                PlayerControlView.this.positionView.setText(Util.getStringForTime(PlayerControlView.this.formatBuilder, PlayerControlView.this.formatter, position));
            }
        }

        @Override // androidx.media3.ui.TimeBar.OnScrubListener
        public void onScrubStop(TimeBar timeBar, long position, boolean canceled) {
            PlayerControlView.this.scrubbing = false;
            if (!canceled && PlayerControlView.this.player != null) {
                PlayerControlView.this.seekToTimeBarPosition(PlayerControlView.this.player, position);
            }
            PlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            if (PlayerControlView.this.needToHideBars) {
                PlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Player player = PlayerControlView.this.player;
            if (player != null) {
                PlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
                if (PlayerControlView.this.nextButton != view) {
                    if (PlayerControlView.this.previousButton != view) {
                        if (PlayerControlView.this.fastForwardButton != view) {
                            if (PlayerControlView.this.rewindButton != view) {
                                if (PlayerControlView.this.playPauseButton != view) {
                                    if (PlayerControlView.this.repeatToggleButton != view) {
                                        if (PlayerControlView.this.shuffleButton != view) {
                                            if (PlayerControlView.this.settingsButton == view) {
                                                PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                                                PlayerControlView.this.displaySettingsWindow(PlayerControlView.this.settingsAdapter, PlayerControlView.this.settingsButton);
                                                return;
                                            }
                                            if (PlayerControlView.this.playbackSpeedButton == view) {
                                                PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                                                PlayerControlView.this.displaySettingsWindow(PlayerControlView.this.playbackSpeedAdapter, PlayerControlView.this.playbackSpeedButton);
                                                return;
                                            } else if (PlayerControlView.this.audioTrackButton == view) {
                                                PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                                                PlayerControlView.this.displaySettingsWindow(PlayerControlView.this.audioTrackSelectionAdapter, PlayerControlView.this.audioTrackButton);
                                                return;
                                            } else {
                                                if (PlayerControlView.this.subtitleButton == view) {
                                                    PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                                                    PlayerControlView.this.displaySettingsWindow(PlayerControlView.this.textTrackSelectionAdapter, PlayerControlView.this.subtitleButton);
                                                    return;
                                                }
                                                return;
                                            }
                                        }
                                        if (player.isCommandAvailable(14)) {
                                            player.setShuffleModeEnabled(!player.getShuffleModeEnabled());
                                            return;
                                        }
                                        return;
                                    }
                                    if (player.isCommandAvailable(15)) {
                                        player.setRepeatMode(RepeatModeUtil.getNextRepeatMode(player.getRepeatMode(), PlayerControlView.this.repeatToggleModes));
                                        return;
                                    }
                                    return;
                                }
                                Util.handlePlayPauseButtonAction(player);
                                return;
                            }
                            if (player.isCommandAvailable(11)) {
                                player.seekBack();
                                return;
                            }
                            return;
                        }
                        if (player.getPlaybackState() != 4 && player.isCommandAvailable(12)) {
                            player.seekForward();
                            return;
                        }
                        return;
                    }
                    if (player.isCommandAvailable(7)) {
                        player.seekToPrevious();
                        return;
                    }
                    return;
                }
                if (player.isCommandAvailable(9)) {
                    player.seekToNext();
                }
            }
        }
    }

    private class SettingsAdapter extends RecyclerView.Adapter<SettingViewHolder> {
        private final Drawable[] iconIds;
        private final String[] mainTexts;
        private final String[] subTexts;

        public SettingsAdapter(String[] mainTexts, Drawable[] iconIds) {
            this.mainTexts = mainTexts;
            this.subTexts = new String[mainTexts.length];
            this.iconIds = iconIds;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public SettingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(PlayerControlView.this.getContext()).inflate(R.layout.exo_styled_settings_list_item, parent, false);
            return PlayerControlView.this.new SettingViewHolder(v);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SettingViewHolder holder, int position) {
            if (shouldShowSetting(position)) {
                holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }
            holder.mainTextView.setText(this.mainTexts[position]);
            if (this.subTexts[position] == null) {
                holder.subTextView.setVisibility(8);
            } else {
                holder.subTextView.setText(this.subTexts[position]);
            }
            if (this.iconIds[position] == null) {
                holder.iconView.setVisibility(8);
            } else {
                holder.iconView.setImageDrawable(this.iconIds[position]);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int position) {
            return position;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mainTexts.length;
        }

        public void setSubTextAtPosition(int position, String subText) {
            this.subTexts[position] = subText;
        }

        public boolean hasSettingsToShow() {
            if (shouldShowSetting(1) || shouldShowSetting(0)) {
                return true;
            }
            return false;
        }

        private boolean shouldShowSetting(int position) {
            if (PlayerControlView.this.player == null) {
                return false;
            }
            switch (position) {
                case 0:
                    return PlayerControlView.this.player.isCommandAvailable(13);
                case 1:
                    return PlayerControlView.this.player.isCommandAvailable(30) && PlayerControlView.this.player.isCommandAvailable(29);
                default:
                    return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class SettingViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iconView;
        private final TextView mainTextView;
        private final TextView subTextView;

        public SettingViewHolder(View itemView) {
            super(itemView);
            if (Util.SDK_INT < 26) {
                itemView.setFocusable(true);
            }
            this.mainTextView = (TextView) itemView.findViewById(R.id.exo_main_text);
            this.subTextView = (TextView) itemView.findViewById(R.id.exo_sub_text);
            this.iconView = (ImageView) itemView.findViewById(R.id.exo_icon);
            itemView.setOnClickListener(new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$SettingViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m8340x7eeeb754(view);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$new$0$androidx-media3-ui-PlayerControlView$SettingViewHolder, reason: not valid java name */
        /* synthetic */ void m8340x7eeeb754(View v) {
            PlayerControlView.this.onSettingViewClicked(getAdapterPosition());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class PlaybackSpeedAdapter extends RecyclerView.Adapter<SubSettingViewHolder> {
        private final String[] playbackSpeedTexts;
        private final float[] playbackSpeeds;
        private int selectedIndex;

        public PlaybackSpeedAdapter(String[] playbackSpeedTexts, float[] playbackSpeeds) {
            this.playbackSpeedTexts = playbackSpeedTexts;
            this.playbackSpeeds = playbackSpeeds;
        }

        public void updateSelectedIndex(float playbackSpeed) {
            int closestMatchIndex = 0;
            float closestMatchDifference = Float.MAX_VALUE;
            for (int i = 0; i < this.playbackSpeeds.length; i++) {
                float difference = Math.abs(playbackSpeed - this.playbackSpeeds[i]);
                if (difference < closestMatchDifference) {
                    closestMatchIndex = i;
                    closestMatchDifference = difference;
                }
            }
            this.selectedIndex = closestMatchIndex;
        }

        public String getSelectedText() {
            return this.playbackSpeedTexts[this.selectedIndex];
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public SubSettingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(PlayerControlView.this.getContext()).inflate(R.layout.exo_styled_sub_settings_list_item, parent, false);
            return new SubSettingViewHolder(v);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SubSettingViewHolder holder, final int position) {
            if (position < this.playbackSpeedTexts.length) {
                holder.textView.setText(this.playbackSpeedTexts[position]);
            }
            if (position == this.selectedIndex) {
                holder.itemView.setSelected(true);
                holder.checkView.setVisibility(0);
            } else {
                holder.itemView.setSelected(false);
                holder.checkView.setVisibility(4);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$PlaybackSpeedAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m8339x9de2ddb7(position, view);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onBindViewHolder$0$androidx-media3-ui-PlayerControlView$PlaybackSpeedAdapter, reason: not valid java name */
        /* synthetic */ void m8339x9de2ddb7(int position, View v) {
            if (position != this.selectedIndex) {
                PlayerControlView.this.setPlaybackSpeed(this.playbackSpeeds[position]);
            }
            PlayerControlView.this.settingsWindow.dismiss();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.playbackSpeedTexts.length;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class TrackInformation {
        public final Tracks.Group trackGroup;
        public final int trackIndex;
        public final String trackName;

        public TrackInformation(Tracks tracks, int trackGroupIndex, int trackIndex, String trackName) {
            this.trackGroup = tracks.getGroups().get(trackGroupIndex);
            this.trackIndex = trackIndex;
            this.trackName = trackName;
        }

        public boolean isSelected() {
            return this.trackGroup.isTrackSelected(this.trackIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class TextTrackSelectionAdapter extends TrackSelectionAdapter {
        private TextTrackSelectionAdapter() {
            super();
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void init(List<TrackInformation> trackInformations) {
            boolean subtitleIsOn = false;
            for (int i = 0; i < trackInformations.size(); i++) {
                if (trackInformations.get(i).isSelected()) {
                    subtitleIsOn = true;
                    break;
                }
            }
            if (PlayerControlView.this.subtitleButton != null) {
                ImageView imageView = PlayerControlView.this.subtitleButton;
                PlayerControlView playerControlView = PlayerControlView.this;
                imageView.setImageDrawable(subtitleIsOn ? playerControlView.subtitleOnButtonDrawable : playerControlView.subtitleOffButtonDrawable);
                ImageView imageView2 = PlayerControlView.this.subtitleButton;
                PlayerControlView playerControlView2 = PlayerControlView.this;
                imageView2.setContentDescription(subtitleIsOn ? playerControlView2.subtitleOnContentDescription : playerControlView2.subtitleOffContentDescription);
            }
            this.tracks = trackInformations;
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void onBindViewHolderAtZeroPosition(SubSettingViewHolder holder) {
            holder.textView.setText(R.string.exo_track_selection_none);
            boolean isTrackSelectionOff = true;
            for (int i = 0; i < this.tracks.size(); i++) {
                if (this.tracks.get(i).isSelected()) {
                    isTrackSelectionOff = false;
                    break;
                }
            }
            holder.checkView.setVisibility(isTrackSelectionOff ? 0 : 4);
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$TextTrackSelectionAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m8341x7bd5d809(view);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onBindViewHolderAtZeroPosition$0$androidx-media3-ui-PlayerControlView$TextTrackSelectionAdapter, reason: not valid java name */
        /* synthetic */ void m8341x7bd5d809(View v) {
            if (PlayerControlView.this.player != null && PlayerControlView.this.player.isCommandAvailable(29)) {
                TrackSelectionParameters trackSelectionParameters = PlayerControlView.this.player.getTrackSelectionParameters();
                PlayerControlView.this.player.setTrackSelectionParameters(trackSelectionParameters.buildUpon().clearOverridesOfType(3).setIgnoredTextSelectionFlags(-3).build());
                PlayerControlView.this.settingsWindow.dismiss();
            }
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SubSettingViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            if (position > 0) {
                TrackInformation track = this.tracks.get(position - 1);
                holder.checkView.setVisibility(track.isSelected() ? 0 : 4);
            }
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void onTrackSelection(String subtext) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class AudioTrackSelectionAdapter extends TrackSelectionAdapter {
        private AudioTrackSelectionAdapter() {
            super();
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void onBindViewHolderAtZeroPosition(SubSettingViewHolder holder) {
            holder.textView.setText(R.string.exo_track_selection_auto);
            TrackSelectionParameters parameters = ((Player) Assertions.checkNotNull(PlayerControlView.this.player)).getTrackSelectionParameters();
            boolean hasSelectionOverride = hasSelectionOverride(parameters);
            holder.checkView.setVisibility(hasSelectionOverride ? 4 : 0);
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$AudioTrackSelectionAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m8338xa84b12b0(view);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onBindViewHolderAtZeroPosition$0$androidx-media3-ui-PlayerControlView$AudioTrackSelectionAdapter, reason: not valid java name */
        /* synthetic */ void m8338xa84b12b0(View v) {
            if (PlayerControlView.this.player != null && PlayerControlView.this.player.isCommandAvailable(29)) {
                TrackSelectionParameters trackSelectionParameters = PlayerControlView.this.player.getTrackSelectionParameters();
                ((Player) Util.castNonNull(PlayerControlView.this.player)).setTrackSelectionParameters(trackSelectionParameters.buildUpon().clearOverridesOfType(1).setTrackTypeDisabled(1, false).build());
                PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, PlayerControlView.this.getResources().getString(R.string.exo_track_selection_auto));
                PlayerControlView.this.settingsWindow.dismiss();
            }
        }

        private boolean hasSelectionOverride(TrackSelectionParameters trackSelectionParameters) {
            for (int i = 0; i < this.tracks.size(); i++) {
                TrackGroup trackGroup = this.tracks.get(i).trackGroup.getMediaTrackGroup();
                if (trackSelectionParameters.overrides.containsKey(trackGroup)) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void onTrackSelection(String subtext) {
            PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, subtext);
        }

        @Override // androidx.media3.ui.PlayerControlView.TrackSelectionAdapter
        public void init(List<TrackInformation> trackInformations) {
            this.tracks = trackInformations;
            TrackSelectionParameters params = ((Player) Assertions.checkNotNull(PlayerControlView.this.player)).getTrackSelectionParameters();
            if (trackInformations.isEmpty()) {
                PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, PlayerControlView.this.getResources().getString(R.string.exo_track_selection_none));
                return;
            }
            if (!hasSelectionOverride(params)) {
                PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, PlayerControlView.this.getResources().getString(R.string.exo_track_selection_auto));
                return;
            }
            for (int i = 0; i < trackInformations.size(); i++) {
                TrackInformation track = trackInformations.get(i);
                if (track.isSelected()) {
                    PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, track.trackName);
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    abstract class TrackSelectionAdapter extends RecyclerView.Adapter<SubSettingViewHolder> {
        protected List<TrackInformation> tracks = new ArrayList();

        public abstract void init(List<TrackInformation> list);

        protected abstract void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder);

        protected abstract void onTrackSelection(String str);

        protected TrackSelectionAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public SubSettingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(PlayerControlView.this.getContext()).inflate(R.layout.exo_styled_sub_settings_list_item, parent, false);
            return new SubSettingViewHolder(v);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SubSettingViewHolder holder, int position) {
            final Player player = PlayerControlView.this.player;
            if (player == null) {
                return;
            }
            if (position == 0) {
                onBindViewHolderAtZeroPosition(holder);
                return;
            }
            final TrackInformation track = this.tracks.get(position - 1);
            final TrackGroup mediaTrackGroup = track.trackGroup.getMediaTrackGroup();
            TrackSelectionParameters params = player.getTrackSelectionParameters();
            boolean explicitlySelected = params.overrides.get(mediaTrackGroup) != null && track.isSelected();
            holder.textView.setText(track.trackName);
            holder.checkView.setVisibility(explicitlySelected ? 0 : 4);
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: androidx.media3.ui.PlayerControlView$TrackSelectionAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m8342x45c3fb1a(player, mediaTrackGroup, track, view);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onBindViewHolder$0$androidx-media3-ui-PlayerControlView$TrackSelectionAdapter, reason: not valid java name */
        /* synthetic */ void m8342x45c3fb1a(Player player, TrackGroup mediaTrackGroup, TrackInformation track, View v) {
            if (!player.isCommandAvailable(29)) {
                return;
            }
            TrackSelectionParameters trackSelectionParameters = player.getTrackSelectionParameters();
            player.setTrackSelectionParameters(trackSelectionParameters.buildUpon().setOverrideForType(new TrackSelectionOverride(mediaTrackGroup, ImmutableList.of(Integer.valueOf(track.trackIndex)))).setTrackTypeDisabled(track.trackGroup.getType(), false).build());
            onTrackSelection(track.trackName);
            PlayerControlView.this.settingsWindow.dismiss();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (this.tracks.isEmpty()) {
                return 0;
            }
            return this.tracks.size() + 1;
        }

        protected void clear() {
            this.tracks = Collections.emptyList();
        }
    }

    private static class SubSettingViewHolder extends RecyclerView.ViewHolder {
        public final View checkView;
        public final TextView textView;

        public SubSettingViewHolder(View itemView) {
            super(itemView);
            if (Util.SDK_INT < 26) {
                itemView.setFocusable(true);
            }
            this.textView = (TextView) itemView.findViewById(R.id.exo_text);
            this.checkView = itemView.findViewById(R.id.exo_check);
        }
    }
}
