package com.linkedin.android.litr.transcoder;

import android.media.MediaFormat;
import android.util.Log;
import androidx.media3.common.MimeTypes;
import com.linkedin.android.litr.codec.Decoder;
import com.linkedin.android.litr.codec.Encoder;
import com.linkedin.android.litr.exception.TrackTranscoderException;
import com.linkedin.android.litr.io.MediaSource;
import com.linkedin.android.litr.io.MediaTarget;
import com.linkedin.android.litr.render.PassthroughSoftwareRenderer;
import com.linkedin.android.litr.render.Renderer;

/* JADX INFO: loaded from: classes13.dex */
public class TrackTranscoderFactory {
    private static final String TAG = TrackTranscoderFactory.class.getSimpleName();

    public TrackTranscoder create(int sourceTrack, int targetTrack, MediaSource mediaSource, Decoder decoder, Renderer renderer, Encoder encoder, MediaTarget mediaTarget, MediaFormat targetFormat) throws TrackTranscoderException {
        if (targetFormat == null) {
            return new PassthroughTranscoder(mediaSource, sourceTrack, mediaTarget, targetTrack);
        }
        String trackMimeType = targetFormat.getString("mime");
        if (trackMimeType == null) {
            throw new TrackTranscoderException(TrackTranscoderException.Error.SOURCE_TRACK_MIME_TYPE_NOT_FOUND, targetFormat, null, null);
        }
        if (trackMimeType.startsWith(MimeTypes.BASE_TYPE_VIDEO) || trackMimeType.startsWith(MimeTypes.BASE_TYPE_AUDIO)) {
            if (decoder == null) {
                throw new TrackTranscoderException(TrackTranscoderException.Error.DECODER_NOT_PROVIDED, targetFormat, null, null);
            }
            if (encoder == null) {
                throw new TrackTranscoderException(TrackTranscoderException.Error.ENCODER_NOT_PROVIDED, targetFormat, null, null);
            }
        }
        if (trackMimeType.startsWith(MimeTypes.BASE_TYPE_VIDEO)) {
            if (renderer != null) {
                return new VideoTrackTranscoder(mediaSource, sourceTrack, mediaTarget, targetTrack, targetFormat, renderer, decoder, encoder);
            }
            throw new TrackTranscoderException(TrackTranscoderException.Error.RENDERER_NOT_PROVIDED, targetFormat, null, null);
        }
        if (trackMimeType.startsWith(MimeTypes.BASE_TYPE_AUDIO)) {
            Renderer audioRenderer = renderer == null ? new PassthroughSoftwareRenderer(encoder) : renderer;
            return new AudioTrackTranscoder(mediaSource, sourceTrack, mediaTarget, targetTrack, targetFormat, audioRenderer, decoder, encoder);
        }
        Log.i(TAG, "Unsupported track mime type: " + trackMimeType + ", will use passthrough transcoder");
        return new PassthroughTranscoder(mediaSource, sourceTrack, mediaTarget, targetTrack);
    }
}
