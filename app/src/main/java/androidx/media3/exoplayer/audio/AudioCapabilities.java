package androidx.media3.exoplayer.audio;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.net.Uri;
import android.provider.Settings;
import android.util.Pair;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.primitives.Ints;
import java.util.Arrays;

/* JADX INFO: loaded from: classes11.dex */
public final class AudioCapabilities {
    private static final int DEFAULT_MAX_CHANNEL_COUNT = 10;
    static final int DEFAULT_SAMPLE_RATE_HZ = 48000;
    private static final String EXTERNAL_SURROUND_SOUND_KEY = "external_surround_sound_enabled";
    private final int maxChannelCount;
    private final int[] supportedEncodings;
    public static final AudioCapabilities DEFAULT_AUDIO_CAPABILITIES = new AudioCapabilities(new int[]{2}, 10);
    private static final ImmutableList<Integer> EXTERNAL_SURROUND_SOUND_ENCODINGS = ImmutableList.of(2, 5, 6);
    private static final ImmutableMap<Integer, Integer> ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS = new ImmutableMap.Builder().put(5, 6).put(17, 6).put(7, 6).put(30, 10).put(18, 6).put(6, 8).put(8, 8).put(14, 8).buildOrThrow();

    public static AudioCapabilities getCapabilities(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"));
        return getCapabilities(context, intent);
    }

    static AudioCapabilities getCapabilities(Context context, Intent intent) {
        if (Util.SDK_INT >= 23 && Api23.isBluetoothConnected(context)) {
            return DEFAULT_AUDIO_CAPABILITIES;
        }
        ImmutableSet.Builder<Integer> supportedEncodings = new ImmutableSet.Builder<>();
        if (deviceMaySetExternalSurroundSoundGlobalSetting() && Settings.Global.getInt(context.getContentResolver(), EXTERNAL_SURROUND_SOUND_KEY, 0) == 1) {
            supportedEncodings.addAll(EXTERNAL_SURROUND_SOUND_ENCODINGS);
        }
        if (Util.SDK_INT >= 29 && (Util.isTv(context) || Util.isAutomotive(context))) {
            supportedEncodings.addAll(Api29.getDirectPlaybackSupportedEncodings());
            return new AudioCapabilities(Ints.toArray(supportedEncodings.build()), 10);
        }
        if (intent != null && intent.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) == 1) {
            int[] encodingsFromExtra = intent.getIntArrayExtra("android.media.extra.ENCODINGS");
            if (encodingsFromExtra != null) {
                supportedEncodings.addAll(Ints.asList(encodingsFromExtra));
            }
            return new AudioCapabilities(Ints.toArray(supportedEncodings.build()), intent.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 10));
        }
        ImmutableSet<Integer> supportedEncodingsSet = supportedEncodings.build();
        if (!supportedEncodingsSet.isEmpty()) {
            return new AudioCapabilities(Ints.toArray(supportedEncodingsSet), 10);
        }
        return DEFAULT_AUDIO_CAPABILITIES;
    }

    static Uri getExternalSurroundSoundGlobalSettingUri() {
        if (deviceMaySetExternalSurroundSoundGlobalSetting()) {
            return Settings.Global.getUriFor(EXTERNAL_SURROUND_SOUND_KEY);
        }
        return null;
    }

    public AudioCapabilities(int[] supportedEncodings, int maxChannelCount) {
        if (supportedEncodings != null) {
            this.supportedEncodings = Arrays.copyOf(supportedEncodings, supportedEncodings.length);
            Arrays.sort(this.supportedEncodings);
        } else {
            this.supportedEncodings = new int[0];
        }
        this.maxChannelCount = maxChannelCount;
    }

    public boolean supportsEncoding(int encoding) {
        return Arrays.binarySearch(this.supportedEncodings, encoding) >= 0;
    }

    public int getMaxChannelCount() {
        return this.maxChannelCount;
    }

    public boolean isPassthroughPlaybackSupported(Format format) {
        return getEncodingAndChannelConfigForPassthrough(format) != null;
    }

    public Pair<Integer, Integer> getEncodingAndChannelConfigForPassthrough(Format format) {
        int channelCount;
        int encoding = MimeTypes.getEncoding((String) Assertions.checkNotNull(format.sampleMimeType), format.codecs);
        if (!ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.containsKey(Integer.valueOf(encoding))) {
            return null;
        }
        if (encoding == 18 && !supportsEncoding(18)) {
            encoding = 6;
        } else if ((encoding == 8 && !supportsEncoding(8)) || (encoding == 30 && !supportsEncoding(30))) {
            encoding = 7;
        }
        if (!supportsEncoding(encoding)) {
            return null;
        }
        if (format.channelCount == -1 || encoding == 18) {
            int channelCount2 = format.sampleRate;
            int sampleRate = channelCount2 != -1 ? format.sampleRate : 48000;
            channelCount = getMaxSupportedChannelCountForPassthrough(encoding, sampleRate);
        } else {
            channelCount = format.channelCount;
            if (format.sampleMimeType.equals(MimeTypes.AUDIO_DTS_X)) {
                if (channelCount > 10) {
                    return null;
                }
            } else if (channelCount > this.maxChannelCount) {
                return null;
            }
        }
        int channelConfig = getChannelConfigForPassthrough(channelCount);
        if (channelConfig == 0) {
            return null;
        }
        return Pair.create(Integer.valueOf(encoding), Integer.valueOf(channelConfig));
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AudioCapabilities)) {
            return false;
        }
        AudioCapabilities audioCapabilities = (AudioCapabilities) other;
        return Arrays.equals(this.supportedEncodings, audioCapabilities.supportedEncodings) && this.maxChannelCount == audioCapabilities.maxChannelCount;
    }

    public int hashCode() {
        return this.maxChannelCount + (Arrays.hashCode(this.supportedEncodings) * 31);
    }

    public String toString() {
        return "AudioCapabilities[maxChannelCount=" + this.maxChannelCount + ", supportedEncodings=" + Arrays.toString(this.supportedEncodings) + "]";
    }

    private static boolean deviceMaySetExternalSurroundSoundGlobalSetting() {
        return Util.SDK_INT >= 17 && ("Amazon".equals(Util.MANUFACTURER) || "Xiaomi".equals(Util.MANUFACTURER));
    }

    private static int getMaxSupportedChannelCountForPassthrough(int encoding, int sampleRate) {
        if (Util.SDK_INT >= 29) {
            return Api29.getMaxSupportedChannelCountForPassthrough(encoding, sampleRate);
        }
        return ((Integer) Assertions.checkNotNull(ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.getOrDefault(Integer.valueOf(encoding), 0))).intValue();
    }

    private static int getChannelConfigForPassthrough(int channelCount) {
        if (Util.SDK_INT <= 28) {
            if (channelCount == 7) {
                channelCount = 8;
            } else if (channelCount == 3 || channelCount == 4 || channelCount == 5) {
                channelCount = 6;
            }
        }
        if (Util.SDK_INT <= 26 && "fugu".equals(Util.DEVICE) && channelCount == 1) {
            channelCount = 2;
        }
        return Util.getAudioTrackChannelConfig(channelCount);
    }

    private static final class Api23 {
        private Api23() {
        }

        public static final boolean isBluetoothConnected(Context context) {
            AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
            AudioDeviceInfo[] audioDeviceInfos = ((AudioManager) Assertions.checkNotNull(audioManager)).getDevices(2);
            ImmutableSet<Integer> allBluetoothDeviceTypesSet = getAllBluetoothDeviceTypes();
            for (AudioDeviceInfo audioDeviceInfo : audioDeviceInfos) {
                if (allBluetoothDeviceTypesSet.contains(Integer.valueOf(audioDeviceInfo.getType()))) {
                    return true;
                }
            }
            return false;
        }

        private static final ImmutableSet<Integer> getAllBluetoothDeviceTypes() {
            ImmutableSet.Builder<Integer> allBluetoothDeviceTypes = new ImmutableSet.Builder().add((Object[]) new Integer[]{8, 7});
            if (Util.SDK_INT >= 31) {
                allBluetoothDeviceTypes.add(26, 27);
            }
            if (Util.SDK_INT >= 33) {
                allBluetoothDeviceTypes.add(30);
            }
            return allBluetoothDeviceTypes.build();
        }
    }

    private static final class Api29 {
        private static final AudioAttributes DEFAULT_AUDIO_ATTRIBUTES = new AudioAttributes.Builder().setUsage(1).setContentType(3).setFlags(0).build();

        private Api29() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static ImmutableList<Integer> getDirectPlaybackSupportedEncodings() {
            ImmutableList.Builder<Integer> supportedEncodingsListBuilder = ImmutableList.builder();
            UnmodifiableIterator it = AudioCapabilities.ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.keySet().iterator();
            while (it.hasNext()) {
                int encoding = ((Integer) it.next()).intValue();
                if (Util.SDK_INT >= 34 || encoding != 30) {
                    if (AudioTrack.isDirectPlaybackSupported(new AudioFormat.Builder().setChannelMask(12).setEncoding(encoding).setSampleRate(48000).build(), DEFAULT_AUDIO_ATTRIBUTES)) {
                        supportedEncodingsListBuilder.add(Integer.valueOf(encoding));
                    }
                }
            }
            supportedEncodingsListBuilder.add(2);
            return supportedEncodingsListBuilder.build();
        }

        public static int getMaxSupportedChannelCountForPassthrough(int encoding, int sampleRate) {
            for (int channelCount = 10; channelCount > 0; channelCount--) {
                AudioFormat audioFormat = new AudioFormat.Builder().setEncoding(encoding).setSampleRate(sampleRate).setChannelMask(Util.getAudioTrackChannelConfig(channelCount)).build();
                if (AudioTrack.isDirectPlaybackSupported(audioFormat, DEFAULT_AUDIO_ATTRIBUTES)) {
                    return channelCount;
                }
            }
            return 0;
        }
    }
}
