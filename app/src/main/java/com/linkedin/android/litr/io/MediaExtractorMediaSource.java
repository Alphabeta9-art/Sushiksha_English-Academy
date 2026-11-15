package com.linkedin.android.litr.io;

import android.content.Context;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import com.linkedin.android.litr.exception.MediaSourceException;
import com.linkedin.android.litr.utils.TranscoderUtils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

/* JADX INFO: loaded from: classes13.dex */
public class MediaExtractorMediaSource implements MediaSource {
    private final MediaExtractor mediaExtractor;
    private final MediaRange mediaRange;
    private int orientationHint;
    private long size;

    public MediaExtractorMediaSource(Context context, Uri uri) throws MediaSourceException {
        this(context, uri, new MediaRange(0L, Long.MAX_VALUE));
    }

    public MediaExtractorMediaSource(Context context, Uri uri, MediaRange mediaRange) throws IOException, MediaSourceException {
        this.mediaRange = mediaRange;
        this.mediaExtractor = new MediaExtractor();
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            this.mediaExtractor.setDataSource(context, uri, (Map<String, String>) null);
            mediaMetadataRetriever.setDataSource(context, uri);
            String rotation = mediaMetadataRetriever.extractMetadata(24);
            if (rotation != null) {
                this.orientationHint = Integer.parseInt(rotation);
            }
            this.size = TranscoderUtils.getSize(context, uri);
            mediaMetadataRetriever.release();
        } catch (IOException ex) {
            mediaMetadataRetriever.release();
            throw new MediaSourceException(MediaSourceException.Error.DATA_SOURCE, uri, ex);
        }
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public int getOrientationHint() {
        return this.orientationHint;
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public int getTrackCount() {
        return this.mediaExtractor.getTrackCount();
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public MediaFormat getTrackFormat(int track) {
        return this.mediaExtractor.getTrackFormat(track);
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public void selectTrack(int track) {
        this.mediaExtractor.selectTrack(track);
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public void seekTo(long position, int mode) {
        this.mediaExtractor.seekTo(position, mode);
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public int getSampleTrackIndex() {
        return this.mediaExtractor.getSampleTrackIndex();
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public int readSampleData(ByteBuffer buffer, int offset) {
        return this.mediaExtractor.readSampleData(buffer, offset);
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public long getSampleTime() {
        return this.mediaExtractor.getSampleTime();
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public int getSampleFlags() {
        return this.mediaExtractor.getSampleFlags();
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public void advance() {
        this.mediaExtractor.advance();
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public void release() {
        this.mediaExtractor.release();
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public long getSize() {
        return this.size;
    }

    @Override // com.linkedin.android.litr.io.MediaSource
    public MediaRange getSelection() {
        return this.mediaRange;
    }
}
