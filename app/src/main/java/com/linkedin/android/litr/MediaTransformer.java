package com.linkedin.android.litr;

import android.content.Context;
import android.media.MediaFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import androidx.media3.common.MimeTypes;
import com.google.android.gms.common.Scopes;
import com.linkedin.android.litr.codec.Decoder;
import com.linkedin.android.litr.codec.Encoder;
import com.linkedin.android.litr.codec.MediaCodecDecoder;
import com.linkedin.android.litr.codec.MediaCodecEncoder;
import com.linkedin.android.litr.exception.MediaSourceException;
import com.linkedin.android.litr.exception.MediaTargetException;
import com.linkedin.android.litr.filter.GlFilter;
import com.linkedin.android.litr.io.MediaExtractorMediaSource;
import com.linkedin.android.litr.io.MediaMuxerMediaTarget;
import com.linkedin.android.litr.io.MediaSource;
import com.linkedin.android.litr.io.MediaTarget;
import com.linkedin.android.litr.render.GlVideoRenderer;
import com.linkedin.android.litr.render.Renderer;
import com.linkedin.android.litr.utils.CodecUtils;
import com.linkedin.android.litr.utils.TranscoderUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes13.dex */
public class MediaTransformer {
    private static final int DEFAULT_FUTURE_MAP_SIZE = 10;
    public static final int DEFAULT_KEY_FRAME_INTERVAL = 5;
    public static final int GRANULARITY_DEFAULT = 100;
    public static final int GRANULARITY_NONE = 0;
    private static final String TAG = MediaTransformer.class.getSimpleName();
    private final Context context;
    private ExecutorService executorService;
    private Map<String, Future<?>> futureMap;
    private Looper looper;

    public MediaTransformer(Context context) {
        this(context, Looper.getMainLooper(), Executors.newSingleThreadExecutor());
    }

    public MediaTransformer(Context context, Looper looper, ExecutorService executorService) {
        this.context = context.getApplicationContext();
        this.futureMap = new HashMap(10);
        this.looper = looper;
        this.executorService = executorService;
    }

    @Deprecated
    public void transform(String requestId, Uri inputUri, String outputFilePath, MediaFormat targetVideoFormat, MediaFormat targetAudioFormat, TransformationListener listener, int granularity, List<GlFilter> filters) {
        TransformationOptions transformationOptions = new TransformationOptions.Builder().setGranularity(granularity).setVideoFilters(filters).build();
        transform(requestId, inputUri, outputFilePath, targetVideoFormat, targetAudioFormat, listener, transformationOptions);
    }

    public void transform(String requestId, Uri inputUri, String outputFilePath, MediaFormat targetVideoFormat, MediaFormat targetAudioFormat, TransformationListener listener, TransformationOptions transformationOptions) {
        MediaTarget mediaTarget;
        int trackCount;
        String str = "mime";
        TransformationOptions options = transformationOptions == null ? new TransformationOptions.Builder().build() : transformationOptions;
        try {
            try {
                MediaSource mediaSource = new MediaExtractorMediaSource(this.context, inputUri, options.sourceMediaRange);
                try {
                    MediaTarget mediaTarget2 = new MediaMuxerMediaTarget(outputFilePath, mediaSource.getTrackCount(), mediaSource.getOrientationHint(), 0);
                    int trackCount2 = mediaSource.getTrackCount();
                    List<TrackTransform> trackTransforms = new ArrayList<>(trackCount2);
                    int track = 0;
                    while (track < trackCount2) {
                        MediaFormat sourceMediaFormat = mediaSource.getTrackFormat(track);
                        String mimeType = null;
                        if (sourceMediaFormat.containsKey(str)) {
                            mimeType = sourceMediaFormat.getString(str);
                        }
                        if (mimeType == null) {
                            Log.e(TAG, "Mime type is null for track " + track);
                            mediaTarget = mediaTarget2;
                            trackCount = trackCount2;
                        } else {
                            Decoder decoder = new MediaCodecDecoder();
                            Encoder encoder = new MediaCodecEncoder();
                            TrackTransform.Builder trackTransformBuilder = new TrackTransform.Builder(mediaSource, track, mediaTarget2).setTargetTrack(track);
                            if (mimeType.startsWith(MimeTypes.BASE_TYPE_VIDEO)) {
                                mediaTarget = mediaTarget2;
                                trackCount = trackCount2;
                                try {
                                    trackTransformBuilder.setDecoder(decoder).setRenderer(new GlVideoRenderer(options.videoFilters)).setEncoder(encoder).setTargetFormat(targetVideoFormat);
                                } catch (MediaSourceException e) {
                                    ex = e;
                                    listener.onError(requestId, ex, null);
                                } catch (MediaTargetException e2) {
                                    ex = e2;
                                    listener.onError(requestId, ex, null);
                                }
                            } else {
                                mediaTarget = mediaTarget2;
                                trackCount = trackCount2;
                                if (mimeType.startsWith(MimeTypes.BASE_TYPE_AUDIO)) {
                                    trackTransformBuilder.setDecoder(decoder).setEncoder(encoder).setTargetFormat(targetAudioFormat);
                                }
                            }
                            trackTransforms.add(trackTransformBuilder.build());
                        }
                        try {
                            track++;
                            str = str;
                            mediaSource = mediaSource;
                            mediaTarget2 = mediaTarget;
                            trackCount2 = trackCount;
                        } catch (MediaSourceException e3) {
                            ex = e3;
                            listener.onError(requestId, ex, null);
                        } catch (MediaTargetException e4) {
                            ex = e4;
                            listener.onError(requestId, ex, null);
                        }
                    }
                    transform(requestId, trackTransforms, listener, options.granularity);
                } catch (MediaSourceException e5) {
                    ex = e5;
                    listener.onError(requestId, ex, null);
                } catch (MediaTargetException e6) {
                    ex = e6;
                    listener.onError(requestId, ex, null);
                }
            } catch (MediaSourceException e7) {
                ex = e7;
                listener.onError(requestId, ex, null);
            } catch (MediaTargetException e8) {
                ex = e8;
                listener.onError(requestId, ex, null);
            }
        } catch (MediaSourceException | MediaTargetException e9) {
            ex = e9;
        }
    }

    @Deprecated
    public void transform(String requestId, MediaSource mediaSource, Decoder decoder, Renderer videoRenderer, Encoder encoder, MediaTarget mediaTarget, MediaFormat targetVideoFormat, MediaFormat targetAudioFormat, TransformationListener listener, int granularity) {
        if (this.futureMap.containsKey(requestId)) {
            throw new IllegalArgumentException("Request with id " + requestId + " already exists");
        }
        int trackCount = mediaSource.getTrackCount();
        List<TrackTransform> trackTransforms = new ArrayList<>(trackCount);
        for (int track = 0; track < trackCount; track++) {
            MediaFormat sourceMediaFormat = mediaSource.getTrackFormat(track);
            String mimeType = null;
            if (sourceMediaFormat.containsKey("mime")) {
                mimeType = sourceMediaFormat.getString("mime");
            }
            if (mimeType == null) {
                Log.e(TAG, "Mime type is null for track " + track);
            } else {
                TrackTransform.Builder trackTransformBuilder = new TrackTransform.Builder(mediaSource, track, mediaTarget).setTargetTrack(track);
                if (mimeType.startsWith(MimeTypes.BASE_TYPE_VIDEO)) {
                    trackTransformBuilder.setDecoder(decoder).setRenderer(videoRenderer).setEncoder(encoder).setTargetFormat(targetVideoFormat);
                } else if (mimeType.startsWith(MimeTypes.BASE_TYPE_AUDIO)) {
                    trackTransformBuilder.setDecoder(new MediaCodecDecoder()).setEncoder(new MediaCodecEncoder()).setTargetFormat(targetAudioFormat);
                }
                trackTransforms.add(trackTransformBuilder.build());
            }
        }
        transform(requestId, trackTransforms, listener, granularity);
    }

    public void transform(String requestId, List<TrackTransform> trackTransforms, TransformationListener listener, int granularity) {
        if (this.futureMap.containsKey(requestId)) {
            throw new IllegalArgumentException("Request with id " + requestId + " already exists");
        }
        int trackCount = trackTransforms.size();
        for (int trackIndex = 0; trackIndex < trackCount; trackIndex++) {
            TrackTransform trackTransform = trackTransforms.get(trackIndex);
            if (trackTransform.getTargetFormat() == null && trackTransform.getRenderer() != null && trackTransform.getRenderer().hasFilters()) {
                MediaFormat targetFormat = createTargetMediaFormat(trackTransform.getMediaSource(), trackTransform.getSourceTrack());
                TrackTransform updatedTrackTransform = new TrackTransform.Builder(trackTransform.getMediaSource(), trackTransform.getSourceTrack(), trackTransform.getMediaTarget()).setTargetTrack(trackTransform.getTargetTrack()).setDecoder(trackTransform.getDecoder()).setEncoder(trackTransform.getEncoder()).setRenderer(trackTransform.getRenderer()).setTargetFormat(targetFormat).build();
                trackTransforms.set(trackIndex, updatedTrackTransform);
            }
        }
        TransformationJob transformationJob = new TransformationJob(requestId, trackTransforms, granularity, new MarshallingTransformationListener(this.futureMap, listener, this.looper));
        Future<?> future = this.executorService.submit(transformationJob);
        this.futureMap.put(requestId, future);
    }

    public void cancel(String requestId) {
        Future<?> future = this.futureMap.get(requestId);
        if (future != null && !future.isCancelled() && !future.isDone()) {
            future.cancel(true);
        }
    }

    public void release() {
        this.executorService.shutdownNow();
    }

    public long getEstimatedTargetVideoSize(Uri inputUri, MediaFormat targetVideoFormat, MediaFormat targetAudioFormat) {
        try {
            MediaSource mediaSource = new MediaExtractorMediaSource(this.context, inputUri);
            return TranscoderUtils.getEstimatedTargetVideoFileSize(mediaSource, targetVideoFormat, targetAudioFormat);
        } catch (MediaSourceException e) {
            return -1L;
        }
    }

    private MediaFormat createTargetMediaFormat(MediaSource mediaSource, int sourceTrackIndex) {
        MediaFormat sourceMediaFormat = mediaSource.getTrackFormat(sourceTrackIndex);
        String mimeType = null;
        if (sourceMediaFormat.containsKey("mime")) {
            mimeType = sourceMediaFormat.getString("mime");
        }
        if (mimeType == null) {
            return null;
        }
        if (mimeType.startsWith(MimeTypes.BASE_TYPE_VIDEO)) {
            MediaFormat targetMediaFormat = MediaFormat.createVideoFormat(mimeType, sourceMediaFormat.getInteger("width"), sourceMediaFormat.getInteger("height"));
            int targetBitrate = TranscoderUtils.estimateVideoTrackBitrate(mediaSource, sourceTrackIndex);
            targetMediaFormat.setInteger("bitrate", targetBitrate);
            int targetKeyFrameInterval = 5;
            if (sourceMediaFormat.containsKey("i-frame-interval")) {
                targetKeyFrameInterval = sourceMediaFormat.getInteger("i-frame-interval");
            }
            targetMediaFormat.setInteger("i-frame-interval", targetKeyFrameInterval);
            int i = Build.VERSION.SDK_INT;
            if (sourceMediaFormat.containsKey(Scopes.PROFILE) && sourceMediaFormat.containsKey("mime")) {
                int sourceCodecProfile = sourceMediaFormat.getInteger(Scopes.PROFILE);
                int targetCodecProfile = CodecUtils.getHighestSupportedProfile(targetMediaFormat.getString("mime"), true, sourceCodecProfile);
                if (targetCodecProfile != -1) {
                    targetMediaFormat.setInteger(Scopes.PROFILE, targetCodecProfile);
                    return targetMediaFormat;
                }
                return targetMediaFormat;
            }
            return targetMediaFormat;
        }
        if (!mimeType.startsWith(MimeTypes.BASE_TYPE_AUDIO)) {
            return null;
        }
        MediaFormat targetMediaFormat2 = MediaFormat.createAudioFormat(mimeType, sourceMediaFormat.getInteger("sample-rate"), sourceMediaFormat.getInteger("channel-count"));
        targetMediaFormat2.setInteger("bitrate", sourceMediaFormat.getInteger("bitrate"));
        return targetMediaFormat2;
    }
}
