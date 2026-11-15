package com.linkedin.android.litr.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaFormat;
import android.net.Uri;
import android.util.Log;
import androidx.media3.common.MimeTypes;
import com.facebook.common.util.UriUtil;
import com.linkedin.android.litr.TrackTransform;
import com.linkedin.android.litr.io.MediaRange;
import com.linkedin.android.litr.io.MediaSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes13.dex */
public final class TranscoderUtils {
    private static final int BITS_IN_BYTE = 8;
    private static final int BITS_IN_KILO = 1000;
    static final int COMMON_AUDIO_BITRATE_KBPS = 320;
    private static final String TAG = TranscoderUtils.class.getSimpleName();

    private TranscoderUtils() {
    }

    public static long getEstimatedTargetFileSize(List<TrackTransform> trackTransforms) {
        float estimatedFileSize = 0.0f;
        long maxDurationUs = 0;
        Iterator<TrackTransform> it = trackTransforms.iterator();
        while (it.hasNext()) {
            maxDurationUs = Math.max(getDuration(it.next()), maxDurationUs);
        }
        for (TrackTransform trackTransform : trackTransforms) {
            MediaFormat sourceTrackFormat = trackTransform.getMediaSource().getTrackFormat(trackTransform.getSourceTrack());
            int bitrate = getBitrate(sourceTrackFormat);
            long duration = getDuration(trackTransform);
            if (duration < 0) {
                Log.d(TAG, "Track duration is not available, using maximum duration");
                duration = maxDurationUs;
            }
            String mimeType = getMimeType(sourceTrackFormat);
            if (mimeType != null) {
                if (trackTransform.getTargetFormat() != null) {
                    bitrate = trackTransform.getTargetFormat().getInteger("bitrate");
                } else if (mimeType.startsWith(MimeTypes.BASE_TYPE_AUDIO) && bitrate < 0) {
                    bitrate = 320000;
                }
            }
            if (bitrate < 0) {
                Log.d(TAG, "Bitrate is not available, cannot use that track to estimate size");
                bitrate = 0;
            }
            estimatedFileSize += bitrate * TimeUtils.microsToSeconds(duration);
        }
        return (long) (estimatedFileSize / 8.0f);
    }

    public static long getEstimatedTargetVideoFileSize(MediaSource mediaSource, MediaFormat targetVideoFormat, MediaFormat targetAudioFormat) {
        List<TrackTransform> trackTransforms = new ArrayList<>(mediaSource.getTrackCount());
        for (int track = 0; track < mediaSource.getTrackCount(); track++) {
            MediaFormat sourceMediaFormat = mediaSource.getTrackFormat(track);
            TrackTransform.Builder trackTransformBuilder = new TrackTransform.Builder(mediaSource, track, null);
            if (sourceMediaFormat.containsKey("mime")) {
                String mimeType = sourceMediaFormat.getString("mime");
                if (mimeType.startsWith(MimeTypes.BASE_TYPE_VIDEO)) {
                    trackTransformBuilder.setTargetFormat(targetVideoFormat);
                } else if (mimeType.startsWith(MimeTypes.BASE_TYPE_AUDIO)) {
                    trackTransformBuilder.setTargetFormat(targetAudioFormat);
                }
            }
            trackTransforms.add(trackTransformBuilder.build());
        }
        return getEstimatedTargetFileSize(trackTransforms);
    }

    public static int estimateVideoTrackBitrate(MediaSource mediaSource, int trackIndex) {
        MediaFormat videoTrackFormat = mediaSource.getTrackFormat(trackIndex);
        if (videoTrackFormat.containsKey("bitrate")) {
            return videoTrackFormat.getInteger("bitrate");
        }
        float videoTrackDuration = TimeUtils.microsToSeconds(videoTrackFormat.getLong("durationUs"));
        if (videoTrackDuration == 0.0f) {
            return 0;
        }
        float unallocatedSize = mediaSource.getSize();
        float totalPixels = 0.0f;
        int trackCount = mediaSource.getTrackCount();
        for (int track = 0; track < trackCount; track++) {
            MediaFormat trackFormat = mediaSource.getTrackFormat(track);
            if (trackFormat.containsKey("mime")) {
                if (trackFormat.containsKey("bitrate") && trackFormat.containsKey("durationUs")) {
                    int bitrate = trackFormat.getInteger("bitrate");
                    long duration = trackFormat.getLong("durationUs");
                    unallocatedSize -= (bitrate * TimeUtils.microsToSeconds(duration)) / 8.0f;
                } else {
                    String mimeType = trackFormat.getString("mime");
                    if (mimeType.startsWith(MimeTypes.BASE_TYPE_VIDEO)) {
                        totalPixels += trackFormat.getInteger("width") * trackFormat.getInteger("height") * TimeUtils.microsToSeconds(trackFormat.getLong("durationUs"));
                    }
                }
            }
        }
        float trackPixels = videoTrackFormat.getInteger("width") * videoTrackFormat.getInteger("height") * videoTrackDuration;
        float trackSize = totalPixels > 0.0f ? (unallocatedSize * trackPixels) / totalPixels : unallocatedSize;
        return (int) ((8.0f * trackSize) / videoTrackDuration);
    }

    public static long getSize(Context context, Uri uri) {
        if (UriUtil.LOCAL_CONTENT_SCHEME.equals(uri.getScheme())) {
            AssetFileDescriptor fileDescriptor = null;
            try {
                try {
                    fileDescriptor = context.getContentResolver().openAssetFileDescriptor(uri, "r");
                    long size = fileDescriptor != null ? fileDescriptor.getParcelFileDescriptor().getStatSize() : 0L;
                    return size >= 0 ? size : -1L;
                } catch (FileNotFoundException | IllegalStateException e) {
                    Log.e(TAG, "Unable to extract length from targetFile: " + uri, e);
                    if (fileDescriptor != null) {
                        try {
                            fileDescriptor.close();
                        } catch (IOException e2) {
                            Log.e(TAG, "Unable to close file descriptor from targetFile: " + uri, e2);
                        }
                    }
                    return -1L;
                }
            } finally {
                if (0 != 0) {
                    try {
                        fileDescriptor.close();
                    } catch (IOException e3) {
                        Log.e(TAG, "Unable to close file descriptor from targetFile: " + uri, e3);
                    }
                }
            }
        }
        if (!UriUtil.LOCAL_FILE_SCHEME.equals(uri.getScheme()) || uri.getPath() == null) {
            return -1L;
        }
        File file = new File(uri.getPath());
        return file.length();
    }

    private static String getMimeType(MediaFormat trackFormat) {
        if (!trackFormat.containsKey("mime")) {
            return null;
        }
        String mimeType = trackFormat.getString("mime");
        return mimeType;
    }

    private static int getBitrate(MediaFormat trackFormat) {
        if (!trackFormat.containsKey("bitrate")) {
            return -1;
        }
        int bitrate = trackFormat.getInteger("bitrate");
        return bitrate;
    }

    private static long getDuration(final TrackTransform trackTransform) {
        MediaRange mediaRange = trackTransform.getMediaSource().getSelection();
        long trimmedDuration = mediaRange.getEnd() - mediaRange.getStart();
        MediaFormat trackFormat = trackTransform.getMediaSource().getTrackFormat(trackTransform.getSourceTrack());
        long trackDuration = -1;
        if (trackFormat.containsKey("durationUs")) {
            trackDuration = trackFormat.getLong("durationUs");
        }
        return Math.min(trimmedDuration, trackDuration);
    }
}
