package androidx.media3.exoplayer.text;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.text.SubtitleDecoder;
import androidx.media3.extractor.text.cea.Cea608Decoder;
import androidx.media3.extractor.text.cea.Cea708Decoder;
import androidx.media3.extractor.text.dvb.DvbDecoder;
import androidx.media3.extractor.text.pgs.PgsDecoder;
import androidx.media3.extractor.text.ssa.SsaDecoder;
import androidx.media3.extractor.text.subrip.SubripDecoder;
import androidx.media3.extractor.text.ttml.TtmlDecoder;
import androidx.media3.extractor.text.tx3g.Tx3gDecoder;
import androidx.media3.extractor.text.webvtt.Mp4WebvttDecoder;
import androidx.media3.extractor.text.webvtt.WebvttDecoder;
import com.google.common.base.Ascii;

/* JADX INFO: loaded from: classes11.dex */
public interface SubtitleDecoderFactory {
    public static final SubtitleDecoderFactory DEFAULT = new SubtitleDecoderFactory() { // from class: androidx.media3.exoplayer.text.SubtitleDecoderFactory.1
        @Override // androidx.media3.exoplayer.text.SubtitleDecoderFactory
        public boolean supportsFormat(Format format) {
            String mimeType = format.sampleMimeType;
            return MimeTypes.TEXT_VTT.equals(mimeType) || MimeTypes.TEXT_SSA.equals(mimeType) || MimeTypes.APPLICATION_TTML.equals(mimeType) || MimeTypes.APPLICATION_MP4VTT.equals(mimeType) || MimeTypes.APPLICATION_SUBRIP.equals(mimeType) || MimeTypes.APPLICATION_TX3G.equals(mimeType) || MimeTypes.APPLICATION_CEA608.equals(mimeType) || MimeTypes.APPLICATION_MP4CEA608.equals(mimeType) || MimeTypes.APPLICATION_CEA708.equals(mimeType) || MimeTypes.APPLICATION_DVBSUBS.equals(mimeType) || MimeTypes.APPLICATION_PGS.equals(mimeType) || MimeTypes.TEXT_EXOPLAYER_CUES.equals(mimeType);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:43:0x008e  */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // androidx.media3.exoplayer.text.SubtitleDecoderFactory
        public SubtitleDecoder createDecoder(Format format) {
            byte b;
            String mimeType = format.sampleMimeType;
            if (mimeType != null) {
                switch (mimeType.hashCode()) {
                    case -1351681404:
                        if (!mimeType.equals(MimeTypes.APPLICATION_DVBSUBS)) {
                            b = -1;
                        } else {
                            b = 9;
                        }
                        break;
                    case -1248334819:
                        if (!mimeType.equals(MimeTypes.APPLICATION_PGS)) {
                            b = -1;
                        } else {
                            b = 10;
                        }
                        break;
                    case -1026075066:
                        if (!mimeType.equals(MimeTypes.APPLICATION_MP4VTT)) {
                            b = -1;
                        } else {
                            b = 2;
                        }
                        break;
                    case -1004728940:
                        if (!mimeType.equals(MimeTypes.TEXT_VTT)) {
                            b = -1;
                        } else {
                            b = 0;
                        }
                        break;
                    case 691401887:
                        if (!mimeType.equals(MimeTypes.APPLICATION_TX3G)) {
                            b = -1;
                        } else {
                            b = 5;
                        }
                        break;
                    case 822864842:
                        if (!mimeType.equals(MimeTypes.TEXT_SSA)) {
                            b = -1;
                        } else {
                            b = 1;
                        }
                        break;
                    case 930165504:
                        if (!mimeType.equals(MimeTypes.APPLICATION_MP4CEA608)) {
                            b = -1;
                        } else {
                            b = 7;
                        }
                        break;
                    case 1201784583:
                        if (!mimeType.equals(MimeTypes.TEXT_EXOPLAYER_CUES)) {
                            b = -1;
                        } else {
                            b = Ascii.VT;
                        }
                        break;
                    case 1566015601:
                        if (!mimeType.equals(MimeTypes.APPLICATION_CEA608)) {
                            b = -1;
                        } else {
                            b = 6;
                        }
                        break;
                    case 1566016562:
                        if (!mimeType.equals(MimeTypes.APPLICATION_CEA708)) {
                            b = -1;
                        } else {
                            b = 8;
                        }
                        break;
                    case 1668750253:
                        if (!mimeType.equals(MimeTypes.APPLICATION_SUBRIP)) {
                            b = -1;
                        } else {
                            b = 4;
                        }
                        break;
                    case 1693976202:
                        if (!mimeType.equals(MimeTypes.APPLICATION_TTML)) {
                            b = -1;
                        } else {
                            b = 3;
                        }
                        break;
                    default:
                        b = -1;
                        break;
                }
                switch (b) {
                    case 0:
                        return new WebvttDecoder();
                    case 1:
                        return new SsaDecoder(format.initializationData);
                    case 2:
                        return new Mp4WebvttDecoder();
                    case 3:
                        return new TtmlDecoder();
                    case 4:
                        return new SubripDecoder();
                    case 5:
                        return new Tx3gDecoder(format.initializationData);
                    case 6:
                    case 7:
                        return new Cea608Decoder(mimeType, format.accessibilityChannel, Cea608Decoder.MIN_DATA_CHANNEL_TIMEOUT_MS);
                    case 8:
                        return new Cea708Decoder(format.accessibilityChannel, format.initializationData);
                    case 9:
                        return new DvbDecoder(format.initializationData);
                    case 10:
                        return new PgsDecoder();
                    case 11:
                        return new ExoplayerCuesDecoder();
                }
            }
            throw new IllegalArgumentException("Attempted to create decoder for unsupported MIME type: " + mimeType);
        }
    };

    SubtitleDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}
