package com.cloudinary.android.preprocess;

import android.content.Context;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.media3.common.MimeTypes;
import com.linkedin.android.litr.MediaTransformer;
import com.linkedin.android.litr.TransformationListener;
import com.linkedin.android.litr.TransformationOptions;
import com.linkedin.android.litr.analytics.TrackTransformationInfo;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes11.dex */
public class Transcode implements Preprocess<Uri> {
    private static final String TAG = Transcode.class.getSimpleName();
    private boolean isTransformationFinished;
    private final Object lockObject = new Object();
    private Parameters parameters;
    private Throwable transcodingError;

    public Transcode(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override // com.cloudinary.android.preprocess.Preprocess
    public Uri execute(Context context, Uri resource) throws PreprocessException {
        int i = Build.VERSION.SDK_INT;
        String targetFilePath = context.getFilesDir() + File.separator + UUID.randomUUID().toString();
        File outputVideoFile = new File(targetFilePath);
        try {
            outputVideoFile.createNewFile();
            MediaFormat targetVideoFormat = createTargetVideoFormat(this.parameters.getWidth(), this.parameters.getHeight(), this.parameters.getTargetVideoBitrateKbps(), this.parameters.getKeyFramesInterval(), this.parameters.getFrameRate());
            MediaFormat targetAudioFormat = createTargetAudioFormat(context, resource, this.parameters.getTargetAudioBitrateKbps());
            TransformationListener transformationListener = new VideoTransformationListener();
            MediaTransformer mediaTransformer = new MediaTransformer(context.getApplicationContext());
            TransformationOptions options = new TransformationOptions.Builder().setGranularity(100).build();
            mediaTransformer.transform(this.parameters.getRequestId(), resource, targetFilePath, targetVideoFormat, targetAudioFormat, transformationListener, options);
            synchronized (this.lockObject) {
                try {
                    if (!this.isTransformationFinished) {
                        this.lockObject.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (this.transcodingError != null) {
                String errorMessage = this.transcodingError.getMessage();
                this.transcodingError = null;
                throw new PreprocessException(errorMessage);
            }
            return Uri.parse(targetFilePath);
        } catch (IOException e2) {
            throw new PreprocessException("Cannot create output video file.");
        }
    }

    private MediaFormat createTargetVideoFormat(int width, int height, int bitrate, int keyFrameInterval, int frameRate) {
        MediaFormat targetVideoFormat = new MediaFormat();
        targetVideoFormat.setString("mime", "video/avc");
        targetVideoFormat.setInteger("width", width);
        targetVideoFormat.setInteger("height", height);
        targetVideoFormat.setInteger("bitrate", bitrate);
        targetVideoFormat.setInteger("i-frame-interval", keyFrameInterval);
        targetVideoFormat.setInteger("frame-rate", frameRate);
        targetVideoFormat.setInteger("color-format", 2130708361);
        return targetVideoFormat;
    }

    private MediaFormat createTargetAudioFormat(Context context, Uri sourceVideoUri, int targetBitrateKbps) {
        MediaFormat audioMediaFormat = null;
        try {
            MediaExtractor mediaExtractor = new MediaExtractor();
            mediaExtractor.setDataSource(context, sourceVideoUri, (Map<String, String>) null);
            int trackCount = mediaExtractor.getTrackCount();
            for (int track = 0; track < trackCount; track++) {
                MediaFormat mediaFormat = mediaExtractor.getTrackFormat(track);
                String mimeType = null;
                if (mediaFormat.containsKey("mime")) {
                    mimeType = mediaFormat.getString("mime");
                }
                if (mimeType != null && mimeType.startsWith(MimeTypes.BASE_TYPE_AUDIO)) {
                    audioMediaFormat = new MediaFormat();
                    audioMediaFormat.setString("mime", mediaFormat.getString("mime"));
                    audioMediaFormat.setInteger("channel-count", mediaFormat.getInteger("channel-count"));
                    audioMediaFormat.setInteger("sample-rate", mediaFormat.getInteger("sample-rate"));
                    audioMediaFormat.setInteger("bitrate", targetBitrateKbps * 1000);
                }
            }
        } catch (IOException ex) {
            Log.e(TAG, "Failed to extract audio track metadata: " + ex);
        }
        return audioMediaFormat;
    }

    private class VideoTransformationListener implements TransformationListener {
        private VideoTransformationListener() {
        }

        @Override // com.linkedin.android.litr.TransformationListener
        public void onStarted(String id) {
        }

        @Override // com.linkedin.android.litr.TransformationListener
        public void onProgress(String id, float progress) {
        }

        @Override // com.linkedin.android.litr.TransformationListener
        public void onCompleted(String id, List<TrackTransformationInfo> trackTransformationInfos) {
            synchronized (Transcode.this.lockObject) {
                Transcode.this.isTransformationFinished = true;
                Transcode.this.lockObject.notify();
            }
        }

        @Override // com.linkedin.android.litr.TransformationListener
        public void onCancelled(String id, List<TrackTransformationInfo> trackTransformationInfos) {
            synchronized (Transcode.this.lockObject) {
                Transcode.this.isTransformationFinished = true;
                Transcode.this.lockObject.notify();
            }
        }

        @Override // com.linkedin.android.litr.TransformationListener
        public void onError(String id, Throwable cause, List<TrackTransformationInfo> trackTransformationInfos) {
            synchronized (Transcode.this.lockObject) {
                Transcode.this.isTransformationFinished = true;
                Transcode.this.transcodingError = cause;
                Transcode.this.lockObject.notify();
            }
        }
    }
}
