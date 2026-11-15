package com.linkedin.android.litr.exception;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.os.Build;
import android.util.Log;
import java.util.Arrays;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes13.dex */
public class TrackTranscoderException extends MediaTransformationException {
    private static final String CODEC_IN_RELEASED_STATE_ERROR_TEXT = "Codecs are in released state.";
    private static final String DECODER_CONFIGURATION_ERROR_TEXT = "Failed to configure decoder codec.";
    private static final String DECODER_FORMAT_NOT_FOUND_ERROR_TEXT = "Failed to create decoder codec.";
    private static final String DECODER_NOT_FOUND_ERROR_TEXT = "No decoder found.";
    private static final String DECODER_NOT_PROVIDED_TEXT = "Decoder is not provided";
    private static final String ENCODER_CONFIGURATION_ERROR_TEXT = "Failed to configure encoder codec.";
    private static final String ENCODER_FORMAT_NOT_FOUND_ERROR_TEXT = "Failed to create encoder codec.";
    private static final String ENCODER_NOT_FOUND_ERROR_TEXT = "No encoder found.";
    private static final String ENCODER_NOT_PROVIDED_TEXT = "Encoder is not provided";
    private static final String INTERNAL_CODEC_ERROR_TEXT = "Internal codec error occurred.";
    private static final String NO_FRAME_AVAILABLE_ERROR_TEXT = "No frame available for specified tag";
    private static final String NO_TRACKS_FOUND_ERROR_TEXT = "No tracks found.";
    private static final String RENDERER_NOT_PROVIDED_TEXT = "Renderer is not provided";
    private static final String SOURCE_TRACK_MIME_TYPE_NOT_FOUND_ERROR_TEXT = "Mime type not found for the source track.";
    private static final String TAG = TrackTranscoderException.class.getName();
    private final Error error;
    private final MediaCodec mediaCodec;
    private final MediaCodecList mediaCodecList;
    private final MediaFormat mediaFormat;

    public enum Error {
        DECODER_FORMAT_NOT_FOUND(TrackTranscoderException.DECODER_FORMAT_NOT_FOUND_ERROR_TEXT),
        DECODER_CONFIGURATION_ERROR(TrackTranscoderException.DECODER_CONFIGURATION_ERROR_TEXT),
        ENCODER_FORMAT_NOT_FOUND(TrackTranscoderException.ENCODER_FORMAT_NOT_FOUND_ERROR_TEXT),
        ENCODER_CONFIGURATION_ERROR(TrackTranscoderException.ENCODER_CONFIGURATION_ERROR_TEXT),
        DECODER_NOT_FOUND(TrackTranscoderException.DECODER_NOT_FOUND_ERROR_TEXT),
        ENCODER_NOT_FOUND(TrackTranscoderException.ENCODER_NOT_FOUND_ERROR_TEXT),
        CODEC_IN_RELEASED_STATE(TrackTranscoderException.CODEC_IN_RELEASED_STATE_ERROR_TEXT),
        SOURCE_TRACK_MIME_TYPE_NOT_FOUND(TrackTranscoderException.SOURCE_TRACK_MIME_TYPE_NOT_FOUND_ERROR_TEXT),
        NO_TRACKS_FOUND(TrackTranscoderException.NO_TRACKS_FOUND_ERROR_TEXT),
        INTERNAL_CODEC_ERROR(TrackTranscoderException.INTERNAL_CODEC_ERROR_TEXT),
        NO_FRAME_AVAILABLE(TrackTranscoderException.NO_FRAME_AVAILABLE_ERROR_TEXT),
        DECODER_NOT_PROVIDED(TrackTranscoderException.DECODER_NOT_PROVIDED_TEXT),
        ENCODER_NOT_PROVIDED(TrackTranscoderException.ENCODER_NOT_PROVIDED_TEXT),
        RENDERER_NOT_PROVIDED(TrackTranscoderException.RENDERER_NOT_PROVIDED_TEXT);

        private final String message;

        Error(String message) {
            this.message = message;
        }
    }

    public TrackTranscoderException(Error error) {
        this(error, null, null, null);
    }

    public TrackTranscoderException(Error error, Throwable cause) {
        this(error, null, null, null, cause);
    }

    public TrackTranscoderException(Error error, MediaFormat sourceFormat, MediaCodec mediaCodec, MediaCodecList mediaCodecList) {
        this(error, sourceFormat, mediaCodec, mediaCodecList, null);
    }

    public TrackTranscoderException(Error error, MediaFormat sourceFormat, MediaCodec mediaCodec, MediaCodecList mediaCodecList, Throwable cause) {
        super(cause);
        this.error = error;
        this.mediaFormat = sourceFormat;
        this.mediaCodec = mediaCodec;
        this.mediaCodecList = mediaCodecList;
    }

    public Error getError() {
        return this.error;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.error.message;
    }

    @Override // com.linkedin.android.litr.exception.MediaTransformationException, java.lang.Throwable
    public String toString() {
        String ret = super.toString() + '\n';
        if (this.mediaFormat != null) {
            ret = ret + "Media format: " + this.mediaFormat.toString() + '\n';
        }
        if (this.mediaCodec != null) {
            ret = ret + "Selected media codec info: " + convertMediaCodecInfoToString(this.mediaCodec) + '\n';
        }
        if (this.mediaCodecList != null) {
            ret = ret + "Available media codec info list (Name, IsEncoder, Supported Types): " + convertMediaCodecListToString(this.mediaCodecList);
        }
        int i = Build.VERSION.SDK_INT;
        if (getCause() != null) {
            return ret + "Diagnostic info: " + getExceptionDiagnosticInfo(getCause());
        }
        return ret;
    }

    private String convertMediaCodecListToString(MediaCodecList mediaCodecList) {
        StringBuilder builder = new StringBuilder();
        try {
            int i = Build.VERSION.SDK_INT;
            for (MediaCodecInfo mediaCodecInfo : mediaCodecList.getCodecInfos()) {
                if (mediaCodecInfo != null) {
                    builder.append('\n').append(convertMediaCodecInfoToString(mediaCodecInfo));
                }
            }
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to retrieve media codec info.", e);
        }
        return builder.toString();
    }

    private String convertMediaCodecInfoToString(MediaCodec mediaCodec) {
        try {
            return convertMediaCodecInfoToString(mediaCodec.getCodecInfo());
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to retrieve media codec info.");
            return "";
        }
    }

    private String convertMediaCodecInfoToString(MediaCodecInfo mediaCodecInfo) {
        return "MediaCodecInfo: " + mediaCodecInfo.getName() + AbstractJsonLexerKt.COMMA + mediaCodecInfo.isEncoder() + AbstractJsonLexerKt.COMMA + Arrays.asList(mediaCodecInfo.getSupportedTypes()).toString();
    }

    private String getExceptionDiagnosticInfo(Throwable cause) {
        if (!(cause instanceof MediaCodec.CodecException)) {
            return null;
        }
        return ((MediaCodec.CodecException) cause).getDiagnosticInfo();
    }
}
