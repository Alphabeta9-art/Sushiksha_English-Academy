package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderException;

/* JADX INFO: loaded from: classes11.dex */
public class MediaCodecDecoderException extends DecoderException {
    public final MediaCodecInfo codecInfo;
    public final String diagnosticInfo;

    public MediaCodecDecoderException(Throwable cause, MediaCodecInfo codecInfo) {
        super("Decoder failed: " + (codecInfo == null ? null : codecInfo.name), cause);
        this.codecInfo = codecInfo;
        this.diagnosticInfo = Util.SDK_INT >= 21 ? getDiagnosticInfoV21(cause) : null;
    }

    private static String getDiagnosticInfoV21(Throwable cause) {
        if (cause instanceof MediaCodec.CodecException) {
            return ((MediaCodec.CodecException) cause).getDiagnosticInfo();
        }
        return null;
    }
}
