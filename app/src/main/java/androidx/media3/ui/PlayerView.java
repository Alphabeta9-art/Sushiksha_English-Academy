package androidx.media3.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.opengl.GLSurfaceView;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.media3.common.AdOverlayInfo;
import androidx.media3.common.AdViewProvider;
import androidx.media3.common.ErrorMessageProvider;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes11.dex */
public class PlayerView extends FrameLayout implements AdViewProvider {
    public static final int ARTWORK_DISPLAY_MODE_FILL = 2;
    public static final int ARTWORK_DISPLAY_MODE_FIT = 1;
    public static final int ARTWORK_DISPLAY_MODE_OFF = 0;
    public static final int SHOW_BUFFERING_ALWAYS = 2;
    public static final int SHOW_BUFFERING_NEVER = 0;
    public static final int SHOW_BUFFERING_WHEN_PLAYING = 1;
    private static final int SURFACE_TYPE_NONE = 0;
    private static final int SURFACE_TYPE_SPHERICAL_GL_SURFACE_VIEW = 3;
    private static final int SURFACE_TYPE_SURFACE_VIEW = 1;
    private static final int SURFACE_TYPE_TEXTURE_VIEW = 2;
    private static final int SURFACE_TYPE_VIDEO_DECODER_GL_SURFACE_VIEW = 4;
    private final FrameLayout adOverlayFrameLayout;
    private int artworkDisplayMode;
    private final ImageView artworkView;
    private final View bufferingView;
    private final ComponentListener componentListener;
    private final AspectRatioFrameLayout contentFrame;
    private final PlayerControlView controller;
    private boolean controllerAutoShow;
    private boolean controllerHideDuringAds;
    private boolean controllerHideOnTouch;
    private int controllerShowTimeoutMs;
    private ControllerVisibilityListener controllerVisibilityListener;
    private CharSequence customErrorMessage;
    private Drawable defaultArtwork;
    private ErrorMessageProvider<? super PlaybackException> errorMessageProvider;
    private final TextView errorMessageView;
    private FullscreenButtonClickListener fullscreenButtonClickListener;
    private boolean isTouching;
    private boolean keepContentOnPlayerReset;
    private PlayerControlView.VisibilityListener legacyControllerVisibilityListener;
    private final FrameLayout overlayFrameLayout;
    private Player player;
    private int showBuffering;
    private final View shutterView;
    private final SubtitleView subtitleView;
    private final View surfaceView;
    private final boolean surfaceViewIgnoresVideoAspectRatio;
    private int textureViewRotation;
    private boolean useController;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ArtworkDisplayMode {
    }

    public interface ControllerVisibilityListener {
        void onVisibilityChanged(int i);
    }

    public interface FullscreenButtonClickListener {
        void onFullscreenButtonClick(boolean z);
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShowBuffering {
    }

    public PlayerView(Context context) {
        this(context, null);
    }

    public PlayerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerView(Context context, AttributeSet attrs, int defStyleAttr) throws Throwable {
        boolean shutterColorSet;
        int showBuffering;
        int controllerShowTimeoutMs;
        int resizeMode;
        boolean useController;
        int defaultArtworkId;
        int defaultArtworkId2;
        boolean useArtwork;
        int playerLayoutId;
        int playerLayoutId2;
        boolean controllerHideDuringAds;
        int showBuffering2;
        Context context2;
        boolean surfaceViewIgnoresVideoAspectRatio;
        super(context, attrs, defStyleAttr);
        PlayerView playerView = this;
        playerView.componentListener = playerView.new ComponentListener();
        if (playerView.isInEditMode()) {
            playerView.contentFrame = null;
            playerView.shutterView = null;
            playerView.surfaceView = null;
            playerView.surfaceViewIgnoresVideoAspectRatio = false;
            playerView.artworkView = null;
            playerView.subtitleView = null;
            playerView.bufferingView = null;
            playerView.errorMessageView = null;
            playerView.controller = null;
            playerView.adOverlayFrameLayout = null;
            playerView.overlayFrameLayout = null;
            ImageView logo = new ImageView(context);
            if (Util.SDK_INT >= 23) {
                configureEditModeLogoV23(context, playerView.getResources(), logo);
            } else {
                configureEditModeLogo(context, playerView.getResources(), logo);
            }
            playerView.addView(logo);
            return;
        }
        int playerLayoutId3 = R.layout.exo_player_view;
        boolean controllerHideOnTouch = true;
        boolean controllerAutoShow = true;
        if (attrs == null) {
            shutterColorSet = false;
            showBuffering = 5000;
            controllerShowTimeoutMs = 0;
            resizeMode = 1;
            useController = true;
            defaultArtworkId = 0;
            defaultArtworkId2 = 1;
            useArtwork = true;
            playerLayoutId = playerLayoutId3;
            playerLayoutId2 = 0;
            controllerHideDuringAds = true;
            showBuffering2 = 0;
        } else {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PlayerView, defStyleAttr, 0);
            try {
                boolean shutterColorSet2 = a.hasValue(R.styleable.PlayerView_shutter_background_color);
                try {
                    int shutterColor = a.getColor(R.styleable.PlayerView_shutter_background_color, 0);
                    int playerLayoutId4 = a.getResourceId(R.styleable.PlayerView_player_layout_id, playerLayoutId3);
                    boolean useArtwork2 = a.getBoolean(R.styleable.PlayerView_use_artwork, true);
                    int artworkDisplayMode = a.getInt(R.styleable.PlayerView_artwork_display_mode, 1);
                    int defaultArtworkId3 = a.getResourceId(R.styleable.PlayerView_default_artwork, 0);
                    boolean useController2 = a.getBoolean(R.styleable.PlayerView_use_controller, true);
                    int surfaceType = a.getInt(R.styleable.PlayerView_surface_type, 1);
                    int resizeMode2 = a.getInt(R.styleable.PlayerView_resize_mode, 0);
                    int controllerShowTimeoutMs2 = a.getInt(R.styleable.PlayerView_show_timeout, 5000);
                    controllerHideOnTouch = a.getBoolean(R.styleable.PlayerView_hide_on_touch, true);
                    controllerAutoShow = a.getBoolean(R.styleable.PlayerView_auto_show, true);
                    try {
                        int showBuffering3 = a.getInteger(R.styleable.PlayerView_show_buffering, 0);
                        try {
                            int i = R.styleable.PlayerView_keep_content_on_player_reset;
                            playerView = this;
                            try {
                                boolean shutterColorSet3 = playerView.keepContentOnPlayerReset;
                                playerView.keepContentOnPlayerReset = a.getBoolean(i, shutterColorSet3);
                                try {
                                    boolean controllerHideDuringAds2 = a.getBoolean(R.styleable.PlayerView_hide_during_ads, true);
                                    a.recycle();
                                    showBuffering2 = showBuffering3;
                                    shutterColorSet = shutterColorSet2;
                                    showBuffering = controllerShowTimeoutMs2;
                                    controllerShowTimeoutMs = resizeMode2;
                                    resizeMode = surfaceType;
                                    useController = useController2;
                                    defaultArtworkId = defaultArtworkId3;
                                    defaultArtworkId2 = artworkDisplayMode;
                                    useArtwork = useArtwork2;
                                    playerLayoutId = playerLayoutId4;
                                    playerLayoutId2 = shutterColor;
                                    controllerHideDuringAds = controllerHideDuringAds2;
                                } catch (Throwable th) {
                                    th = th;
                                    a.recycle();
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            } catch (Throwable th6) {
                th = th6;
            }
        }
        LayoutInflater.from(context).inflate(playerLayoutId, playerView);
        playerView.setDescendantFocusability(262144);
        playerView.contentFrame = (AspectRatioFrameLayout) playerView.findViewById(R.id.exo_content_frame);
        if (playerView.contentFrame != null) {
            setResizeModeRaw(playerView.contentFrame, controllerShowTimeoutMs);
        }
        playerView.shutterView = playerView.findViewById(R.id.exo_shutter);
        if (playerView.shutterView != null && shutterColorSet) {
            playerView.shutterView.setBackgroundColor(playerLayoutId2);
        }
        boolean surfaceViewIgnoresVideoAspectRatio2 = false;
        if (playerView.contentFrame != null && resizeMode != 0) {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
            switch (resizeMode) {
                case 2:
                    context2 = context;
                    playerView.surfaceView = new TextureView(context2);
                    break;
                case 3:
                    try {
                        Class<?> clazz = Class.forName("androidx.media3.exoplayer.video.spherical.SphericalGLSurfaceView");
                        playerView.surfaceView = (View) clazz.getConstructor(Context.class).newInstance(context);
                        context2 = context;
                        surfaceViewIgnoresVideoAspectRatio2 = true;
                    } catch (Exception e) {
                        throw new IllegalStateException("spherical_gl_surface_view requires an ExoPlayer dependency", e);
                    }
                    break;
                case 4:
                    try {
                        Class<?> clazz2 = Class.forName("androidx.media3.exoplayer.video.VideoDecoderGLSurfaceView");
                        try {
                            playerView.surfaceView = (View) clazz2.getConstructor(Context.class).newInstance(context);
                            context2 = context;
                        } catch (Exception e2) {
                            e = e2;
                            throw new IllegalStateException("video_decoder_gl_surface_view requires an ExoPlayer dependency", e);
                        }
                    } catch (Exception e3) {
                        e = e3;
                    }
                    break;
                default:
                    context2 = context;
                    playerView.surfaceView = new SurfaceView(context2);
                    break;
            }
            playerView.surfaceView.setLayoutParams(params);
            playerView.surfaceView.setOnClickListener(playerView.componentListener);
            playerView.surfaceView.setClickable(false);
            playerView.contentFrame.addView(playerView.surfaceView, 0);
            surfaceViewIgnoresVideoAspectRatio = surfaceViewIgnoresVideoAspectRatio2;
        } else {
            context2 = context;
            playerView.surfaceView = null;
            surfaceViewIgnoresVideoAspectRatio = false;
        }
        playerView.surfaceViewIgnoresVideoAspectRatio = surfaceViewIgnoresVideoAspectRatio;
        playerView.adOverlayFrameLayout = (FrameLayout) playerView.findViewById(R.id.exo_ad_overlay);
        playerView.overlayFrameLayout = (FrameLayout) playerView.findViewById(R.id.exo_overlay);
        playerView.artworkView = (ImageView) playerView.findViewById(R.id.exo_artwork);
        boolean isArtworkEnabled = (!useArtwork || defaultArtworkId2 == 0 || playerView.artworkView == null) ? false : true;
        playerView.artworkDisplayMode = isArtworkEnabled ? defaultArtworkId2 : 0;
        if (defaultArtworkId != 0) {
            playerView.defaultArtwork = ContextCompat.getDrawable(playerView.getContext(), defaultArtworkId);
        }
        playerView.subtitleView = (SubtitleView) playerView.findViewById(R.id.exo_subtitles);
        if (playerView.subtitleView != null) {
            playerView.subtitleView.setUserDefaultStyle();
            playerView.subtitleView.setUserDefaultTextSize();
        }
        playerView.bufferingView = playerView.findViewById(R.id.exo_buffering);
        if (playerView.bufferingView != null) {
            playerView.bufferingView.setVisibility(8);
        }
        playerView.showBuffering = showBuffering2;
        playerView.errorMessageView = (TextView) playerView.findViewById(R.id.exo_error_message);
        if (playerView.errorMessageView != null) {
            playerView.errorMessageView.setVisibility(8);
        }
        PlayerControlView customController = (PlayerControlView) playerView.findViewById(R.id.exo_controller);
        View controllerPlaceholder = playerView.findViewById(R.id.exo_controller_placeholder);
        if (customController != null) {
            playerView.controller = customController;
        } else if (controllerPlaceholder != null) {
            playerView.controller = new PlayerControlView(context2, null, 0, attrs);
            playerView.controller.setId(R.id.exo_controller);
            playerView.controller.setLayoutParams(controllerPlaceholder.getLayoutParams());
            ViewGroup parent = (ViewGroup) controllerPlaceholder.getParent();
            int controllerIndex = parent.indexOfChild(controllerPlaceholder);
            parent.removeView(controllerPlaceholder);
            parent.addView(playerView.controller, controllerIndex);
        } else {
            playerView.controller = null;
        }
        playerView.controllerShowTimeoutMs = playerView.controller != null ? showBuffering : 0;
        playerView.controllerHideOnTouch = controllerHideOnTouch;
        playerView.controllerAutoShow = controllerAutoShow;
        playerView.controllerHideDuringAds = controllerHideDuringAds;
        playerView.useController = useController && playerView.controller != null;
        if (playerView.controller != null) {
            playerView.controller.hideImmediately();
            playerView.controller.addVisibilityListener(playerView.componentListener);
        }
        if (useController) {
            playerView.setClickable(true);
        }
        playerView.updateContentDescription();
    }

    public static void switchTargetView(Player player, PlayerView oldPlayerView, PlayerView newPlayerView) {
        if (oldPlayerView == newPlayerView) {
            return;
        }
        if (newPlayerView != null) {
            newPlayerView.setPlayer(player);
        }
        if (oldPlayerView != null) {
            oldPlayerView.setPlayer(null);
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        Assertions.checkArgument(player == null || player.getApplicationLooper() == Looper.getMainLooper());
        if (this.player == player) {
            return;
        }
        Player oldPlayer = this.player;
        if (oldPlayer != null) {
            oldPlayer.removeListener(this.componentListener);
            if (oldPlayer.isCommandAvailable(27)) {
                if (this.surfaceView instanceof TextureView) {
                    oldPlayer.clearVideoTextureView((TextureView) this.surfaceView);
                } else if (this.surfaceView instanceof SurfaceView) {
                    oldPlayer.clearVideoSurfaceView((SurfaceView) this.surfaceView);
                }
            }
        }
        if (this.subtitleView != null) {
            this.subtitleView.setCues(null);
        }
        this.player = player;
        if (useController()) {
            this.controller.setPlayer(player);
        }
        updateBuffering();
        updateErrorMessage();
        updateForCurrentTrackSelections(true);
        if (player != null) {
            if (player.isCommandAvailable(27)) {
                if (this.surfaceView instanceof TextureView) {
                    player.setVideoTextureView((TextureView) this.surfaceView);
                } else if (this.surfaceView instanceof SurfaceView) {
                    player.setVideoSurfaceView((SurfaceView) this.surfaceView);
                }
                if (!player.isCommandAvailable(30) || player.getCurrentTracks().isTypeSupported(2)) {
                    updateAspectRatio();
                }
            }
            if (this.subtitleView != null && player.isCommandAvailable(28)) {
                this.subtitleView.setCues(player.getCurrentCues().cues);
            }
            player.addListener(this.componentListener);
            maybeShowController(false);
            return;
        }
        hideController();
    }

    @Override // android.view.View
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (this.surfaceView instanceof SurfaceView) {
            this.surfaceView.setVisibility(visibility);
        }
    }

    public void setResizeMode(int resizeMode) {
        Assertions.checkStateNotNull(this.contentFrame);
        this.contentFrame.setResizeMode(resizeMode);
    }

    public int getResizeMode() {
        Assertions.checkStateNotNull(this.contentFrame);
        return this.contentFrame.getResizeMode();
    }

    @Deprecated
    public boolean getUseArtwork() {
        return this.artworkDisplayMode != 0;
    }

    @Deprecated
    public void setUseArtwork(boolean z) {
        setArtworkDisplayMode(!z ? 1 : 0);
    }

    public void setArtworkDisplayMode(int artworkDisplayMode) {
        Assertions.checkState(artworkDisplayMode == 0 || this.artworkView != null);
        if (this.artworkDisplayMode != artworkDisplayMode) {
            this.artworkDisplayMode = artworkDisplayMode;
            updateForCurrentTrackSelections(false);
        }
    }

    public int getArtworkDisplayMode() {
        return this.artworkDisplayMode;
    }

    public Drawable getDefaultArtwork() {
        return this.defaultArtwork;
    }

    public void setDefaultArtwork(Drawable defaultArtwork) {
        if (this.defaultArtwork != defaultArtwork) {
            this.defaultArtwork = defaultArtwork;
            updateForCurrentTrackSelections(false);
        }
    }

    public boolean getUseController() {
        return this.useController;
    }

    public void setUseController(boolean useController) {
        Assertions.checkState((useController && this.controller == null) ? false : true);
        setClickable(useController || hasOnClickListeners());
        if (this.useController == useController) {
            return;
        }
        this.useController = useController;
        if (useController()) {
            this.controller.setPlayer(this.player);
        } else if (this.controller != null) {
            this.controller.hide();
            this.controller.setPlayer(null);
        }
        updateContentDescription();
    }

    public void setShutterBackgroundColor(int color) {
        if (this.shutterView != null) {
            this.shutterView.setBackgroundColor(color);
        }
    }

    public void setKeepContentOnPlayerReset(boolean keepContentOnPlayerReset) {
        if (this.keepContentOnPlayerReset != keepContentOnPlayerReset) {
            this.keepContentOnPlayerReset = keepContentOnPlayerReset;
            updateForCurrentTrackSelections(false);
        }
    }

    public void setShowBuffering(int showBuffering) {
        if (this.showBuffering != showBuffering) {
            this.showBuffering = showBuffering;
            updateBuffering();
        }
    }

    public void setErrorMessageProvider(ErrorMessageProvider<? super PlaybackException> errorMessageProvider) {
        if (this.errorMessageProvider != errorMessageProvider) {
            this.errorMessageProvider = errorMessageProvider;
            updateErrorMessage();
        }
    }

    public void setCustomErrorMessage(CharSequence message) {
        Assertions.checkState(this.errorMessageView != null);
        this.customErrorMessage = message;
        updateErrorMessage();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (this.player != null && this.player.isCommandAvailable(16) && this.player.isPlayingAd()) {
            return super.dispatchKeyEvent(event);
        }
        boolean isDpadKey = isDpadKey(event.getKeyCode());
        if (isDpadKey && useController() && !this.controller.isFullyVisible()) {
            maybeShowController(true);
            return true;
        }
        if (dispatchMediaKeyEvent(event) || super.dispatchKeyEvent(event)) {
            maybeShowController(true);
            return true;
        }
        if (!isDpadKey || !useController()) {
            return false;
        }
        maybeShowController(true);
        return false;
    }

    public boolean dispatchMediaKeyEvent(KeyEvent event) {
        return useController() && this.controller.dispatchMediaKeyEvent(event);
    }

    public boolean isControllerFullyVisible() {
        return this.controller != null && this.controller.isFullyVisible();
    }

    public void showController() {
        showController(shouldShowControllerIndefinitely());
    }

    public void hideController() {
        if (this.controller != null) {
            this.controller.hide();
        }
    }

    public int getControllerShowTimeoutMs() {
        return this.controllerShowTimeoutMs;
    }

    public void setControllerShowTimeoutMs(int controllerShowTimeoutMs) {
        Assertions.checkStateNotNull(this.controller);
        this.controllerShowTimeoutMs = controllerShowTimeoutMs;
        if (this.controller.isFullyVisible()) {
            showController();
        }
    }

    public boolean getControllerHideOnTouch() {
        return this.controllerHideOnTouch;
    }

    public void setControllerHideOnTouch(boolean controllerHideOnTouch) {
        Assertions.checkStateNotNull(this.controller);
        this.controllerHideOnTouch = controllerHideOnTouch;
        updateContentDescription();
    }

    public boolean getControllerAutoShow() {
        return this.controllerAutoShow;
    }

    public void setControllerAutoShow(boolean controllerAutoShow) {
        this.controllerAutoShow = controllerAutoShow;
    }

    public void setControllerHideDuringAds(boolean controllerHideDuringAds) {
        this.controllerHideDuringAds = controllerHideDuringAds;
    }

    public void setControllerVisibilityListener(ControllerVisibilityListener listener) {
        this.controllerVisibilityListener = listener;
        if (listener != null) {
            setControllerVisibilityListener((PlayerControlView.VisibilityListener) null);
        }
    }

    @Deprecated
    public void setControllerVisibilityListener(PlayerControlView.VisibilityListener listener) {
        Assertions.checkStateNotNull(this.controller);
        if (this.legacyControllerVisibilityListener == listener) {
            return;
        }
        if (this.legacyControllerVisibilityListener != null) {
            this.controller.removeVisibilityListener(this.legacyControllerVisibilityListener);
        }
        this.legacyControllerVisibilityListener = listener;
        if (listener != null) {
            this.controller.addVisibilityListener(listener);
            setControllerVisibilityListener((ControllerVisibilityListener) null);
        }
    }

    public void setFullscreenButtonClickListener(FullscreenButtonClickListener listener) {
        Assertions.checkStateNotNull(this.controller);
        this.fullscreenButtonClickListener = listener;
        this.controller.setOnFullScreenModeChangedListener(this.componentListener);
    }

    @Deprecated
    public void setControllerOnFullScreenModeChangedListener(PlayerControlView.OnFullScreenModeChangedListener listener) {
        Assertions.checkStateNotNull(this.controller);
        this.fullscreenButtonClickListener = null;
        this.controller.setOnFullScreenModeChangedListener(listener);
    }

    public void setShowRewindButton(boolean showRewindButton) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowRewindButton(showRewindButton);
    }

    public void setShowFastForwardButton(boolean showFastForwardButton) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowFastForwardButton(showFastForwardButton);
    }

    public void setShowPreviousButton(boolean showPreviousButton) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowPreviousButton(showPreviousButton);
    }

    public void setShowNextButton(boolean showNextButton) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowNextButton(showNextButton);
    }

    public void setRepeatToggleModes(int repeatToggleModes) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setRepeatToggleModes(repeatToggleModes);
    }

    public void setShowShuffleButton(boolean showShuffleButton) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowShuffleButton(showShuffleButton);
    }

    public void setShowSubtitleButton(boolean showSubtitleButton) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowSubtitleButton(showSubtitleButton);
    }

    public void setShowVrButton(boolean showVrButton) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowVrButton(showVrButton);
    }

    public void setShowMultiWindowTimeBar(boolean showMultiWindowTimeBar) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowMultiWindowTimeBar(showMultiWindowTimeBar);
    }

    public void setExtraAdGroupMarkers(long[] extraAdGroupTimesMs, boolean[] extraPlayedAdGroups) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setExtraAdGroupMarkers(extraAdGroupTimesMs, extraPlayedAdGroups);
    }

    public void setAspectRatioListener(AspectRatioFrameLayout.AspectRatioListener listener) {
        Assertions.checkStateNotNull(this.contentFrame);
        this.contentFrame.setAspectRatioListener(listener);
    }

    public View getVideoSurfaceView() {
        return this.surfaceView;
    }

    public FrameLayout getOverlayFrameLayout() {
        return this.overlayFrameLayout;
    }

    public SubtitleView getSubtitleView() {
        return this.subtitleView;
    }

    @Override // android.view.View
    public boolean performClick() {
        toggleControllerVisibility();
        return super.performClick();
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent ev) {
        if (!useController() || this.player == null) {
            return false;
        }
        maybeShowController(true);
        return true;
    }

    public void onResume() {
        if (this.surfaceView instanceof GLSurfaceView) {
            ((GLSurfaceView) this.surfaceView).onResume();
        }
    }

    public void onPause() {
        if (this.surfaceView instanceof GLSurfaceView) {
            ((GLSurfaceView) this.surfaceView).onPause();
        }
    }

    protected void onContentAspectRatioChanged(AspectRatioFrameLayout contentFrame, float aspectRatio) {
        if (contentFrame != null) {
            contentFrame.setAspectRatio(aspectRatio);
        }
    }

    @Override // androidx.media3.common.AdViewProvider
    public ViewGroup getAdViewGroup() {
        return (ViewGroup) Assertions.checkStateNotNull(this.adOverlayFrameLayout, "exo_ad_overlay must be present for ad playback");
    }

    @Override // androidx.media3.common.AdViewProvider
    public List<AdOverlayInfo> getAdOverlayInfos() {
        List<AdOverlayInfo> overlayViews = new ArrayList<>();
        if (this.overlayFrameLayout != null) {
            overlayViews.add(new AdOverlayInfo(this.overlayFrameLayout, 4, "Transparent overlay does not impact viewability"));
        }
        if (this.controller != null) {
            overlayViews.add(new AdOverlayInfo(this.controller, 1));
        }
        return ImmutableList.copyOf((Collection) overlayViews);
    }

    @EnsuresNonNullIf(expression = {"controller"}, result = true)
    private boolean useController() {
        if (this.useController) {
            Assertions.checkStateNotNull(this.controller);
            return true;
        }
        return false;
    }

    @EnsuresNonNullIf(expression = {"artworkView"}, result = true)
    private boolean useArtwork() {
        if (this.artworkDisplayMode != 0) {
            Assertions.checkStateNotNull(this.artworkView);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleControllerVisibility() {
        if (!useController() || this.player == null) {
            return;
        }
        if (!this.controller.isFullyVisible()) {
            maybeShowController(true);
        } else if (this.controllerHideOnTouch) {
            this.controller.hide();
        }
    }

    private void maybeShowController(boolean isForced) {
        if ((!isPlayingAd() || !this.controllerHideDuringAds) && useController()) {
            boolean wasShowingIndefinitely = this.controller.isFullyVisible() && this.controller.getShowTimeoutMs() <= 0;
            boolean shouldShowIndefinitely = shouldShowControllerIndefinitely();
            if (isForced || wasShowingIndefinitely || shouldShowIndefinitely) {
                showController(shouldShowIndefinitely);
            }
        }
    }

    private boolean shouldShowControllerIndefinitely() {
        if (this.player == null) {
            return true;
        }
        int playbackState = this.player.getPlaybackState();
        return this.controllerAutoShow && !(this.player.isCommandAvailable(17) && this.player.getCurrentTimeline().isEmpty()) && (playbackState == 1 || playbackState == 4 || !((Player) Assertions.checkNotNull(this.player)).getPlayWhenReady());
    }

    private void showController(boolean showIndefinitely) {
        if (!useController()) {
            return;
        }
        this.controller.setShowTimeoutMs(showIndefinitely ? 0 : this.controllerShowTimeoutMs);
        this.controller.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPlayingAd() {
        return this.player != null && this.player.isCommandAvailable(16) && this.player.isPlayingAd() && this.player.getPlayWhenReady();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateForCurrentTrackSelections(boolean isNewPlayer) {
        Player player = this.player;
        if (player == null || !player.isCommandAvailable(30) || player.getCurrentTracks().isEmpty()) {
            if (!this.keepContentOnPlayerReset) {
                hideArtwork();
                closeShutter();
                return;
            }
            return;
        }
        if (isNewPlayer && !this.keepContentOnPlayerReset) {
            closeShutter();
        }
        if (player.getCurrentTracks().isTypeSelected(2)) {
            hideArtwork();
            return;
        }
        closeShutter();
        if (useArtwork() && (setArtworkFromMediaMetadata(player) || setDrawableArtwork(this.defaultArtwork))) {
            return;
        }
        hideArtwork();
    }

    @RequiresNonNull({"artworkView"})
    private boolean setArtworkFromMediaMetadata(Player player) {
        if (!player.isCommandAvailable(18)) {
            return false;
        }
        MediaMetadata mediaMetadata = player.getMediaMetadata();
        if (mediaMetadata.artworkData == null) {
            return false;
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(mediaMetadata.artworkData, 0, mediaMetadata.artworkData.length);
        return setDrawableArtwork(new BitmapDrawable(getResources(), bitmap));
    }

    @RequiresNonNull({"artworkView"})
    private boolean setDrawableArtwork(Drawable drawable) {
        if (drawable != null) {
            int drawableWidth = drawable.getIntrinsicWidth();
            int drawableHeight = drawable.getIntrinsicHeight();
            if (drawableWidth > 0 && drawableHeight > 0) {
                float artworkLayoutAspectRatio = drawableWidth / drawableHeight;
                ImageView.ScaleType scaleStyle = ImageView.ScaleType.FIT_XY;
                if (this.artworkDisplayMode == 2) {
                    artworkLayoutAspectRatio = getWidth() / getHeight();
                    scaleStyle = ImageView.ScaleType.CENTER_CROP;
                }
                onContentAspectRatioChanged(this.contentFrame, artworkLayoutAspectRatio);
                this.artworkView.setScaleType(scaleStyle);
                this.artworkView.setImageDrawable(drawable);
                this.artworkView.setVisibility(0);
                return true;
            }
        }
        return false;
    }

    private void hideArtwork() {
        if (this.artworkView != null) {
            this.artworkView.setImageResource(android.R.color.transparent);
            this.artworkView.setVisibility(4);
        }
    }

    private void closeShutter() {
        if (this.shutterView != null) {
            this.shutterView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:15:0x0024  */
    public void updateBuffering() {
        boolean showBufferingSpinner;
        if (this.bufferingView != null) {
            if (this.player == null || this.player.getPlaybackState() != 2) {
                showBufferingSpinner = false;
            } else {
                showBufferingSpinner = true;
                if (this.showBuffering != 2 && (this.showBuffering != 1 || !this.player.getPlayWhenReady())) {
                    showBufferingSpinner = false;
                }
            }
            this.bufferingView.setVisibility(showBufferingSpinner ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateErrorMessage() {
        if (this.errorMessageView != null) {
            if (this.customErrorMessage != null) {
                this.errorMessageView.setText(this.customErrorMessage);
                this.errorMessageView.setVisibility(0);
                return;
            }
            PlaybackException error = this.player != null ? this.player.getPlayerError() : null;
            if (error != null && this.errorMessageProvider != null) {
                CharSequence errorMessage = (CharSequence) this.errorMessageProvider.getErrorMessage(error).second;
                this.errorMessageView.setText(errorMessage);
                this.errorMessageView.setVisibility(0);
                return;
            }
            this.errorMessageView.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateContentDescription() {
        if (this.controller == null || !this.useController) {
            setContentDescription(null);
        } else if (this.controller.isFullyVisible()) {
            setContentDescription(this.controllerHideOnTouch ? getResources().getString(R.string.exo_controls_hide) : null);
        } else {
            setContentDescription(getResources().getString(R.string.exo_controls_show));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateControllerVisibility() {
        if (isPlayingAd() && this.controllerHideDuringAds) {
            hideController();
        } else {
            maybeShowController(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAspectRatio() {
        VideoSize videoSize = this.player != null ? this.player.getVideoSize() : VideoSize.UNKNOWN;
        int width = videoSize.width;
        int height = videoSize.height;
        int unappliedRotationDegrees = videoSize.unappliedRotationDegrees;
        float videoAspectRatio = (height == 0 || width == 0) ? 0.0f : (width * videoSize.pixelWidthHeightRatio) / height;
        if (this.surfaceView instanceof TextureView) {
            if (videoAspectRatio > 0.0f && (unappliedRotationDegrees == 90 || unappliedRotationDegrees == 270)) {
                videoAspectRatio = 1.0f / videoAspectRatio;
            }
            if (this.textureViewRotation != 0) {
                this.surfaceView.removeOnLayoutChangeListener(this.componentListener);
            }
            this.textureViewRotation = unappliedRotationDegrees;
            if (this.textureViewRotation != 0) {
                this.surfaceView.addOnLayoutChangeListener(this.componentListener);
            }
            applyTextureViewRotation((TextureView) this.surfaceView, this.textureViewRotation);
        }
        onContentAspectRatioChanged(this.contentFrame, this.surfaceViewIgnoresVideoAspectRatio ? 0.0f : videoAspectRatio);
    }

    private static void configureEditModeLogoV23(Context context, Resources resources, ImageView logo) {
        logo.setImageDrawable(Util.getDrawable(context, resources, R.drawable.exo_edit_mode_logo));
        logo.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color, null));
    }

    private static void configureEditModeLogo(Context context, Resources resources, ImageView logo) {
        logo.setImageDrawable(Util.getDrawable(context, resources, R.drawable.exo_edit_mode_logo));
        logo.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color));
    }

    private static void setResizeModeRaw(AspectRatioFrameLayout aspectRatioFrame, int resizeMode) {
        aspectRatioFrame.setResizeMode(resizeMode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void applyTextureViewRotation(TextureView textureView, int textureViewRotation) {
        Matrix transformMatrix = new Matrix();
        float textureViewWidth = textureView.getWidth();
        float textureViewHeight = textureView.getHeight();
        if (textureViewWidth != 0.0f && textureViewHeight != 0.0f && textureViewRotation != 0) {
            float pivotX = textureViewWidth / 2.0f;
            float pivotY = textureViewHeight / 2.0f;
            transformMatrix.postRotate(textureViewRotation, pivotX, pivotY);
            RectF originalTextureRect = new RectF(0.0f, 0.0f, textureViewWidth, textureViewHeight);
            RectF rotatedTextureRect = new RectF();
            transformMatrix.mapRect(rotatedTextureRect, originalTextureRect);
            transformMatrix.postScale(textureViewWidth / rotatedTextureRect.width(), textureViewHeight / rotatedTextureRect.height(), pivotX, pivotY);
        }
        textureView.setTransform(transformMatrix);
    }

    private boolean isDpadKey(int keyCode) {
        return keyCode == 19 || keyCode == 270 || keyCode == 22 || keyCode == 271 || keyCode == 20 || keyCode == 269 || keyCode == 21 || keyCode == 268 || keyCode == 23;
    }

    private final class ComponentListener implements Player.Listener, View.OnLayoutChangeListener, View.OnClickListener, PlayerControlView.VisibilityListener, PlayerControlView.OnFullScreenModeChangedListener {
        private Object lastPeriodUidWithTracks;
        private final Timeline.Period period = new Timeline.Period();

        public ComponentListener() {
        }

        @Override // androidx.media3.common.Player.Listener
        public void onCues(CueGroup cueGroup) {
            if (PlayerView.this.subtitleView != null) {
                PlayerView.this.subtitleView.setCues(cueGroup.cues);
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onVideoSizeChanged(VideoSize videoSize) {
            if (!videoSize.equals(VideoSize.UNKNOWN) && PlayerView.this.player != null && PlayerView.this.player.getPlaybackState() != 1) {
                PlayerView.this.updateAspectRatio();
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onRenderedFirstFrame() {
            if (PlayerView.this.shutterView != null) {
                PlayerView.this.shutterView.setVisibility(4);
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTracksChanged(Tracks tracks) {
            Timeline timeline;
            Player player = (Player) Assertions.checkNotNull(PlayerView.this.player);
            if (player.isCommandAvailable(17)) {
                timeline = player.getCurrentTimeline();
            } else {
                timeline = Timeline.EMPTY;
            }
            if (timeline.isEmpty()) {
                this.lastPeriodUidWithTracks = null;
            } else if (player.isCommandAvailable(30) && !player.getCurrentTracks().isEmpty()) {
                this.lastPeriodUidWithTracks = timeline.getPeriod(player.getCurrentPeriodIndex(), this.period, true).uid;
            } else if (this.lastPeriodUidWithTracks != null) {
                int lastPeriodIndexWithTracks = timeline.getIndexOfPeriod(this.lastPeriodUidWithTracks);
                if (lastPeriodIndexWithTracks != -1) {
                    int lastWindowIndexWithTracks = timeline.getPeriod(lastPeriodIndexWithTracks, this.period).windowIndex;
                    if (player.getCurrentMediaItemIndex() == lastWindowIndexWithTracks) {
                        return;
                    }
                }
                this.lastPeriodUidWithTracks = null;
            }
            PlayerView.this.updateForCurrentTrackSelections(false);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackStateChanged(int playbackState) {
            PlayerView.this.updateBuffering();
            PlayerView.this.updateErrorMessage();
            PlayerView.this.updateControllerVisibility();
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayWhenReadyChanged(boolean playWhenReady, int reason) {
            PlayerView.this.updateBuffering();
            PlayerView.this.updateControllerVisibility();
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPositionDiscontinuity(Player.PositionInfo oldPosition, Player.PositionInfo newPosition, int reason) {
            if (PlayerView.this.isPlayingAd() && PlayerView.this.controllerHideDuringAds) {
                PlayerView.this.hideController();
            }
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            PlayerView.applyTextureViewRotation((TextureView) view, PlayerView.this.textureViewRotation);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PlayerView.this.toggleControllerVisibility();
        }

        @Override // androidx.media3.ui.PlayerControlView.VisibilityListener
        public void onVisibilityChange(int visibility) {
            PlayerView.this.updateContentDescription();
            if (PlayerView.this.controllerVisibilityListener != null) {
                PlayerView.this.controllerVisibilityListener.onVisibilityChanged(visibility);
            }
        }

        @Override // androidx.media3.ui.PlayerControlView.OnFullScreenModeChangedListener
        public void onFullScreenModeChanged(boolean isFullScreen) {
            if (PlayerView.this.fullscreenButtonClickListener != null) {
                PlayerView.this.fullscreenButtonClickListener.onFullscreenButtonClick(isFullScreen);
            }
        }
    }
}
