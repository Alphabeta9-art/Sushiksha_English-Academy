package com.linkedin.android.litr;

import android.text.TextUtils;
import android.util.Log;
import com.linkedin.android.litr.analytics.TransformationStatsCollector;
import com.linkedin.android.litr.exception.InsufficientDiskSpaceException;
import com.linkedin.android.litr.exception.MediaTransformationException;
import com.linkedin.android.litr.exception.TrackTranscoderException;
import com.linkedin.android.litr.io.MediaSource;
import com.linkedin.android.litr.io.MediaTarget;
import com.linkedin.android.litr.transcoder.TrackTranscoder;
import com.linkedin.android.litr.transcoder.TrackTranscoderFactory;
import com.linkedin.android.litr.utils.DiskUtil;
import com.linkedin.android.litr.utils.TranscoderUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes13.dex */
class TransformationJob implements Runnable {
    private static final float DEFAULT_SIZE_PADDING = 0.1f;
    private static final String TAG = TransformationJob.class.getSimpleName();
    int granularity;
    private final String jobId;
    private final MarshallingTransformationListener marshallingTransformationListener;
    List<TrackTranscoder> trackTranscoders;
    private final List<TrackTransform> trackTransforms;
    float lastProgress = 0.0f;
    TrackTranscoderFactory trackTranscoderFactory = new TrackTranscoderFactory();
    DiskUtil diskUtil = new DiskUtil();
    TransformationStatsCollector statsCollector = new TransformationStatsCollector();

    TransformationJob(String jobId, List<TrackTransform> trackTransforms, int granularity, MarshallingTransformationListener marshallingTransformationListener) {
        this.jobId = jobId;
        this.trackTransforms = trackTransforms;
        this.granularity = granularity;
        this.marshallingTransformationListener = marshallingTransformationListener;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            transform();
        } catch (MediaTransformationException exception) {
            Log.e(TAG, "Transformation job error", exception);
            exception.setJobId(this.jobId);
            error(exception);
        } catch (RuntimeException e) {
            Log.e(TAG, "Transformation job error", e);
            Throwable cause = e.getCause();
            if (cause instanceof InterruptedException) {
                cancel();
            } else {
                error(e);
            }
        }
    }

    void transform() throws MediaTransformationException {
        boolean completed;
        initStatsCollector();
        verifyAvailableDiskSpace();
        createTrackTranscoders();
        startTrackTranscoders();
        seekToMediaRangeStart();
        this.marshallingTransformationListener.onStarted(this.jobId);
        this.lastProgress = 0.0f;
        do {
            completed = processNextFrame();
            if (Thread.interrupted()) {
                completed = false;
                cancel();
                break;
            }
        } while (!completed);
        release(completed);
    }

    void cancel() {
        release(false);
        this.marshallingTransformationListener.onCancelled(this.jobId, this.statsCollector.getStats());
    }

    protected void error(Throwable cause) {
        release(false);
        this.marshallingTransformationListener.onError(this.jobId, cause, this.statsCollector.getStats());
    }

    void initStatsCollector() {
        for (TrackTransform trackTransform : this.trackTransforms) {
            this.statsCollector.addSourceTrack(trackTransform.getMediaSource().getTrackFormat(trackTransform.getSourceTrack()));
        }
    }

    void verifyAvailableDiskSpace() throws InsufficientDiskSpaceException {
        long estimatedFileSizeInBytes = TranscoderUtils.getEstimatedTargetFileSize(this.trackTransforms);
        long estimatedFileSizeInBytesAfterPadding = (long) (estimatedFileSizeInBytes * 1.1f);
        long availableDiskSpaceInBytes = this.diskUtil.getAvailableDiskSpaceInDataDirectory();
        if (availableDiskSpaceInBytes != -1 && availableDiskSpaceInBytes < estimatedFileSizeInBytesAfterPadding) {
            throw new InsufficientDiskSpaceException(estimatedFileSizeInBytes, availableDiskSpaceInBytes);
        }
    }

    void createTrackTranscoders() throws TrackTranscoderException {
        int trackCount = this.trackTransforms.size();
        this.trackTranscoders = new ArrayList(trackCount);
        if (trackCount < 1) {
            throw new TrackTranscoderException(TrackTranscoderException.Error.NO_TRACKS_FOUND);
        }
        for (int track = 0; track < trackCount; track++) {
            TrackTransform trackTransform = this.trackTransforms.get(track);
            TrackTranscoder trackTranscoder = this.trackTranscoderFactory.create(trackTransform.getSourceTrack(), trackTransform.getTargetTrack(), trackTransform.getMediaSource(), trackTransform.getDecoder(), trackTransform.getRenderer(), trackTransform.getEncoder(), trackTransform.getMediaTarget(), trackTransform.getTargetFormat());
            this.trackTranscoders.add(trackTranscoder);
            this.statsCollector.setTrackCodecs(track, trackTranscoder.getDecoderName(), trackTranscoder.getEncoderName());
        }
    }

    void startTrackTranscoders() throws TrackTranscoderException {
        for (TrackTranscoder trackTranscoder : this.trackTranscoders) {
            trackTranscoder.start();
        }
    }

    private void seekToMediaRangeStart() {
        for (TrackTransform trackTransform : this.trackTransforms) {
            trackTransform.getMediaSource().seekTo(trackTransform.getMediaSource().getSelection().getStart(), 0);
        }
    }

    boolean processNextFrame() throws TrackTranscoderException {
        boolean completed = true;
        for (int track = 0; track < this.trackTranscoders.size(); track++) {
            TrackTranscoder trackTranscoder = this.trackTranscoders.get(track);
            long frameStartTime = System.currentTimeMillis();
            int result = trackTranscoder.processNextFrame();
            completed &= result == 3;
            this.statsCollector.increaseTrackProcessingDuration(track, System.currentTimeMillis() - frameStartTime);
        }
        float totalProgress = 0.0f;
        for (TrackTranscoder trackTranscoder2 : this.trackTranscoders) {
            totalProgress += trackTranscoder2.getProgress();
        }
        float totalProgress2 = totalProgress / this.trackTranscoders.size();
        if ((this.granularity == 0 && totalProgress2 != this.lastProgress) || (this.granularity != 0 && totalProgress2 >= this.lastProgress + (1.0f / this.granularity))) {
            this.marshallingTransformationListener.onProgress(this.jobId, totalProgress2);
            this.lastProgress = totalProgress2;
        }
        return completed;
    }

    void release(boolean success) {
        for (int track = 0; track < this.trackTranscoders.size(); track++) {
            TrackTranscoder trackTranscoder = this.trackTranscoders.get(track);
            trackTranscoder.stop();
            this.statsCollector.setTargetFormat(track, trackTranscoder.getTargetMediaFormat());
        }
        HashSet<MediaSource> mediaSources = new HashSet<>();
        HashSet<MediaTarget> mediaTargets = new HashSet<>();
        for (TrackTransform trackTransform : this.trackTransforms) {
            mediaSources.add(trackTransform.getMediaSource());
            mediaTargets.add(trackTransform.getMediaTarget());
        }
        for (MediaSource mediaSource : mediaSources) {
            mediaSource.release();
        }
        for (MediaTarget mediaTarget : mediaTargets) {
            mediaTarget.release();
            if (!success) {
                deleteOutputFile(mediaTarget.getOutputFilePath());
            }
        }
        if (success) {
            this.marshallingTransformationListener.onCompleted(this.jobId, this.statsCollector.getStats());
        }
    }

    boolean deleteOutputFile(String outputFilePath) {
        if (TextUtils.isEmpty(outputFilePath)) {
            return false;
        }
        File outputFile = new File(outputFilePath);
        return outputFile.delete();
    }
}
