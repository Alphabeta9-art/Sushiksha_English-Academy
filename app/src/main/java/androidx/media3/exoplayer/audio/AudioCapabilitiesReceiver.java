package androidx.media3.exoplayer.audio;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;

/* JADX INFO: loaded from: classes11.dex */
public final class AudioCapabilitiesReceiver {
    AudioCapabilities audioCapabilities;
    private final AudioDeviceCallbackV23 audioDeviceCallback;
    private final Context context;
    private final ExternalSurroundSoundSettingObserver externalSurroundSoundSettingObserver;
    private final Handler handler;
    private final BroadcastReceiver hdmiAudioPlugBroadcastReceiver;
    private final Listener listener;
    private boolean registered;

    public interface Listener {
        void onAudioCapabilitiesChanged(AudioCapabilities audioCapabilities);
    }

    public AudioCapabilitiesReceiver(Context context, Listener listener) {
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext;
        this.listener = (Listener) Assertions.checkNotNull(listener);
        this.handler = Util.createHandlerForCurrentOrMainLooper();
        byte b = 0;
        this.audioDeviceCallback = Util.SDK_INT >= 23 ? new AudioDeviceCallbackV23() : null;
        this.hdmiAudioPlugBroadcastReceiver = Util.SDK_INT >= 21 ? new HdmiAudioPlugBroadcastReceiver() : null;
        Uri externalSurroundSoundGlobalSettingUri = AudioCapabilities.getExternalSurroundSoundGlobalSettingUri();
        this.externalSurroundSoundSettingObserver = externalSurroundSoundGlobalSettingUri != null ? new ExternalSurroundSoundSettingObserver(this.handler, applicationContext.getContentResolver(), externalSurroundSoundGlobalSettingUri) : null;
    }

    public AudioCapabilities register() {
        if (this.registered) {
            return (AudioCapabilities) Assertions.checkNotNull(this.audioCapabilities);
        }
        this.registered = true;
        if (this.externalSurroundSoundSettingObserver != null) {
            this.externalSurroundSoundSettingObserver.register();
        }
        if (Util.SDK_INT >= 23 && this.audioDeviceCallback != null) {
            Api23.registerAudioDeviceCallback(this.context, this.audioDeviceCallback, this.handler);
        }
        Intent stickyIntent = null;
        if (this.hdmiAudioPlugBroadcastReceiver != null) {
            IntentFilter intentFilter = new IntentFilter("android.media.action.HDMI_AUDIO_PLUG");
            stickyIntent = this.context.registerReceiver(this.hdmiAudioPlugBroadcastReceiver, intentFilter, null, this.handler);
        }
        this.audioCapabilities = AudioCapabilities.getCapabilities(this.context, stickyIntent);
        return this.audioCapabilities;
    }

    public void unregister() {
        if (!this.registered) {
            return;
        }
        this.audioCapabilities = null;
        if (Util.SDK_INT >= 23 && this.audioDeviceCallback != null) {
            Api23.unregisterAudioDeviceCallback(this.context, this.audioDeviceCallback);
        }
        if (this.hdmiAudioPlugBroadcastReceiver != null) {
            this.context.unregisterReceiver(this.hdmiAudioPlugBroadcastReceiver);
        }
        if (this.externalSurroundSoundSettingObserver != null) {
            this.externalSurroundSoundSettingObserver.unregister();
        }
        this.registered = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNewAudioCapabilities(AudioCapabilities newAudioCapabilities) {
        if (this.registered && !newAudioCapabilities.equals(this.audioCapabilities)) {
            this.audioCapabilities = newAudioCapabilities;
            this.listener.onAudioCapabilitiesChanged(newAudioCapabilities);
        }
    }

    private final class HdmiAudioPlugBroadcastReceiver extends BroadcastReceiver {
        private HdmiAudioPlugBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!isInitialStickyBroadcast()) {
                AudioCapabilitiesReceiver.this.onNewAudioCapabilities(AudioCapabilities.getCapabilities(context, intent));
            }
        }
    }

    private final class ExternalSurroundSoundSettingObserver extends ContentObserver {
        private final ContentResolver resolver;
        private final Uri settingUri;

        public ExternalSurroundSoundSettingObserver(Handler handler, ContentResolver resolver, Uri settingUri) {
            super(handler);
            this.resolver = resolver;
            this.settingUri = settingUri;
        }

        public void register() {
            this.resolver.registerContentObserver(this.settingUri, false, this);
        }

        public void unregister() {
            this.resolver.unregisterContentObserver(this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            AudioCapabilitiesReceiver.this.onNewAudioCapabilities(AudioCapabilities.getCapabilities(AudioCapabilitiesReceiver.this.context));
        }
    }

    private final class AudioDeviceCallbackV23 extends AudioDeviceCallback {
        private AudioDeviceCallbackV23() {
        }

        @Override // android.media.AudioDeviceCallback
        public void onAudioDevicesAdded(AudioDeviceInfo[] addedDevices) {
            AudioCapabilitiesReceiver.this.onNewAudioCapabilities(AudioCapabilities.getCapabilities(AudioCapabilitiesReceiver.this.context));
        }

        @Override // android.media.AudioDeviceCallback
        public void onAudioDevicesRemoved(AudioDeviceInfo[] removedDevices) {
            AudioCapabilitiesReceiver.this.onNewAudioCapabilities(AudioCapabilities.getCapabilities(AudioCapabilitiesReceiver.this.context));
        }
    }

    private static final class Api23 {
        public static void registerAudioDeviceCallback(Context context, AudioDeviceCallback callback, Handler handler) {
            AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
            ((AudioManager) Assertions.checkNotNull(audioManager)).registerAudioDeviceCallback(callback, handler);
        }

        public static void unregisterAudioDeviceCallback(Context context, AudioDeviceCallback callback) {
            AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
            ((AudioManager) Assertions.checkNotNull(audioManager)).unregisterAudioDeviceCallback(callback);
        }

        private Api23() {
        }
    }
}
