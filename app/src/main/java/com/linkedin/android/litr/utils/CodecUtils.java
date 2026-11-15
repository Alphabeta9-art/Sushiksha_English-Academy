package com.linkedin.android.litr.utils;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.text.TextUtils;
import android.view.Surface;
import com.linkedin.android.litr.exception.TrackTranscoderException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes13.dex */
public class CodecUtils {
    private static Map<String, int[]> CODEC_PROFILE_RANK_MAP = new HashMap();
    public static final String MIME_TYPE_VIDEO_AV1 = "video/av01";
    public static final String MIME_TYPE_VIDEO_AVC = "video/avc";
    public static final String MIME_TYPE_VIDEO_HEVC = "video/hevc";
    public static final String MIME_TYPE_VIDEO_VP8 = "video/x-vnd.on2.vp8";
    public static final String MIME_TYPE_VIDEO_VP9 = "video/x-vnd.on2.vp9";
    public static final int UNDEFINED_VALUE = -1;

    static {
        int[] hevcProfileRanks;
        int[] avcProfileRanks = Build.VERSION.SDK_INT >= 27 ? new int[]{65536, 1, 4, 2, 524288, 8, 16, 32, 64} : new int[]{1, 4, 2, 8, 16, 32, 64};
        CODEC_PROFILE_RANK_MAP.put("video/avc", avcProfileRanks);
        int[] vp8ProfileRanks = {1};
        CODEC_PROFILE_RANK_MAP.put("video/x-vnd.on2.vp8", vp8ProfileRanks);
        int i = Build.VERSION.SDK_INT;
        if (Build.VERSION.SDK_INT >= 29) {
            hevcProfileRanks = new int[]{1, 2, 4096, 8192};
        } else {
            int i2 = Build.VERSION.SDK_INT;
            hevcProfileRanks = new int[]{1, 2, 4096};
        }
        CODEC_PROFILE_RANK_MAP.put("video/hevc", hevcProfileRanks);
        int i3 = Build.VERSION.SDK_INT;
        int[] vp9ProfileRanks = Build.VERSION.SDK_INT >= 29 ? new int[]{1, 2, 4, 4096, 16384, 8, 8192, 32768} : new int[]{1, 2, 4, 4096, 8, 8192};
        CODEC_PROFILE_RANK_MAP.put("video/x-vnd.on2.vp9", vp9ProfileRanks);
        if (Build.VERSION.SDK_INT >= 29) {
            int[] av1ProfileRanks = {1, 2, 4096, 8192};
            CODEC_PROFILE_RANK_MAP.put("video/av01", av1ProfileRanks);
        }
    }

    private CodecUtils() {
    }

    public static int getHighestSupportedProfile(String mimeType, boolean isEncoder) {
        return getHighestSupportedProfile(mimeType, isEncoder, -1);
    }

    public static int getHighestSupportedProfile(String mimeType, boolean isEncoder, int targetProfile) {
        int highestSupportedProfile = -1;
        int i = Build.VERSION.SDK_INT;
        int maxProfileRank = targetProfile == -1 ? Integer.MAX_VALUE : getProfileRank(mimeType, targetProfile);
        MediaCodecList mediaCodecList = new MediaCodecList(1);
        for (MediaCodecInfo mediaCodecInfo : mediaCodecList.getCodecInfos()) {
            if (supportsType(mediaCodecInfo, mimeType) && mediaCodecInfo.isEncoder() == isEncoder) {
                MediaCodecInfo.CodecCapabilities codecCapabilities = mediaCodecInfo.getCapabilitiesForType(mimeType);
                for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : codecCapabilities.profileLevels) {
                    if (getProfileRank(mimeType, codecProfileLevel.profile) > getProfileRank(mimeType, highestSupportedProfile) && getProfileRank(mimeType, codecProfileLevel.profile) <= maxProfileRank) {
                        highestSupportedProfile = codecProfileLevel.profile;
                    }
                }
            }
        }
        return highestSupportedProfile;
    }

    public static MediaCodec getAndConfigureCodec(MediaFormat mediaFormat, Surface surface, boolean isEncoder, TrackTranscoderException.Error codecNotFoundError, TrackTranscoderException.Error codecFormatNotFoundError, TrackTranscoderException.Error codecConfigurationError) throws TrackTranscoderException {
        try {
            int i = Build.VERSION.SDK_INT;
            MediaCodec mediaCodec = getAndConfigureCodecByConfig(mediaFormat, surface, isEncoder);
            if (mediaCodec == null) {
                int i2 = Build.VERSION.SDK_INT;
                throw new IllegalStateException("Try fallbackToGetCodecByType");
            }
            return mediaCodec;
        } catch (IOException | IllegalStateException e) {
            int i3 = Build.VERSION.SDK_INT;
            try {
                MediaCodec mediaCodec2 = getAndConfigureCodecByType(mediaFormat, surface, isEncoder);
                if (mediaCodec2 == null) {
                    throw new TrackTranscoderException(codecNotFoundError, mediaFormat, null, null);
                }
                return mediaCodec2;
            } catch (IOException | IllegalStateException ex) {
                if (ex instanceof IOException) {
                    throw new TrackTranscoderException(codecFormatNotFoundError, mediaFormat, null, null, ex);
                }
                throw new TrackTranscoderException(codecConfigurationError, mediaFormat, null, null, ex);
            }
        }
    }

    private static MediaCodec getAndConfigureCodecByType(MediaFormat mediaFormat, Surface surface, boolean isEncoder) throws IllegalStateException, IOException {
        String mimeType = mediaFormat.getString("mime");
        List<Callable<MediaCodec>> supportedMediaCodecs = findCodecForFormatOrType(isEncoder, mimeType, null);
        if (supportedMediaCodecs.isEmpty()) {
            return null;
        }
        MediaCodec mediaCodec = createAndConfigureCodec(mediaFormat, surface, isEncoder, supportedMediaCodecs);
        return mediaCodec;
    }

    private static MediaCodec getAndConfigureCodecByConfig(MediaFormat mediaFormat, Surface surface, boolean isEncoder) throws IllegalStateException, IOException {
        String mimeType = mediaFormat.getString("mime");
        List<Callable<MediaCodec>> supportedMediaCodecs = findCodecForFormatOrType(isEncoder, mimeType, mediaFormat);
        if (supportedMediaCodecs.isEmpty()) {
            return null;
        }
        MediaCodec mediaCodec = createAndConfigureCodec(mediaFormat, surface, isEncoder, supportedMediaCodecs);
        return mediaCodec;
    }

    private static MediaCodec createAndConfigureCodec(MediaFormat mediaFormat, Surface surface, boolean isEncoder, List<Callable<MediaCodec>> supportedMediaCodecs) throws IllegalStateException, IOException {
        MediaCodec mediaCodec = null;
        IOException error = null;
        for (Callable<MediaCodec> callable : supportedMediaCodecs) {
            try {
                mediaCodec = callable.call();
                if (mediaCodec != null) {
                    configureMediaFormat(mediaCodec, mediaFormat, surface, isEncoder);
                    break;
                }
                continue;
            } catch (Exception e) {
                if (mediaCodec != null) {
                    mediaCodec.release();
                    mediaCodec = null;
                }
                if (e instanceof IOException) {
                    error = (IOException) e;
                }
            }
        }
        if (mediaCodec == null) {
            if (error != null) {
                throw error;
            }
            throw new IllegalStateException();
        }
        return mediaCodec;
    }

    private static void configureMediaFormat(MediaCodec mediaCodec, MediaFormat mediaFormat, Surface surface, boolean z) throws IllegalStateException {
        mediaCodec.configure(mediaFormat, surface, (MediaCrypto) null, z ? 1 : 0);
    }

    private static List<Callable<MediaCodec>> findCodecForFormatOrType(boolean encoder, String mimeType, MediaFormat mediaFormat) {
        List<Callable<MediaCodec>> supportedMediaCodecs = new ArrayList<>();
        int i = Build.VERSION.SDK_INT;
        MediaCodecList mediaCodecList = new MediaCodecList(1);
        for (final MediaCodecInfo info : mediaCodecList.getCodecInfos()) {
            if (info.isEncoder() == encoder) {
                try {
                    MediaCodecInfo.CodecCapabilities caps = info.getCapabilitiesForType(mimeType);
                    if (caps != null && (mediaFormat == null || caps.isFormatSupported(mediaFormat))) {
                        supportedMediaCodecs.add(new Callable() { // from class: com.linkedin.android.litr.utils.CodecUtils$$ExternalSyntheticLambda0
                            @Override // java.util.concurrent.Callable
                            public final Object call() {
                                return MediaCodec.createByCodecName(info.getName());
                            }
                        });
                    }
                } catch (IllegalArgumentException e) {
                }
            }
        }
        return supportedMediaCodecs;
    }

    static /* synthetic */ MediaCodec lambda$findCodecForFormatOrType$1(final boolean encoder, final String mimeType) throws Exception {
        return encoder ? MediaCodec.createEncoderByType(mimeType) : MediaCodec.createDecoderByType(mimeType);
    }

    private static boolean supportsType(MediaCodecInfo mediaCodecInfo, String mimeType) {
        String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
        for (String supportedType : supportedTypes) {
            if (TextUtils.equals(mimeType, supportedType)) {
                return true;
            }
        }
        return false;
    }

    private static int getProfileRank(String mimeType, int profile) {
        int[] rankedProfiles;
        if (profile == -1 || (rankedProfiles = CODEC_PROFILE_RANK_MAP.get(mimeType)) == null) {
            return -1;
        }
        for (int ranking = 0; ranking < rankedProfiles.length; ranking++) {
            if (profile == rankedProfiles[ranking]) {
                return ranking;
            }
        }
        return -1;
    }
}
