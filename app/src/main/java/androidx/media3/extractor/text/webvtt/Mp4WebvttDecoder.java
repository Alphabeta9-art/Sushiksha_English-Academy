package androidx.media3.extractor.text.webvtt;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.SimpleSubtitleDecoder;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleDecoderException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public final class Mp4WebvttDecoder extends SimpleSubtitleDecoder {
    private static final int BOX_HEADER_SIZE = 8;
    private static final int TYPE_payl = 1885436268;
    private static final int TYPE_sttg = 1937011815;
    private static final int TYPE_vttc = 1987343459;
    private final ParsableByteArray sampleData;

    public Mp4WebvttDecoder() {
        super("Mp4WebvttDecoder");
        this.sampleData = new ParsableByteArray();
    }

    @Override // androidx.media3.extractor.text.SimpleSubtitleDecoder
    protected Subtitle decode(byte[] data, int length, boolean reset) throws SubtitleDecoderException {
        this.sampleData.reset(data, length);
        List<Cue> resultingCueList = new ArrayList<>();
        while (this.sampleData.bytesLeft() > 0) {
            if (this.sampleData.bytesLeft() < 8) {
                throw new SubtitleDecoderException("Incomplete Mp4Webvtt Top Level box header found.");
            }
            int boxSize = this.sampleData.readInt();
            int boxType = this.sampleData.readInt();
            if (boxType == TYPE_vttc) {
                resultingCueList.add(parseVttCueBox(this.sampleData, boxSize - 8));
            } else {
                this.sampleData.skipBytes(boxSize - 8);
            }
        }
        return new Mp4WebvttSubtitle(resultingCueList);
    }

    private static Cue parseVttCueBox(ParsableByteArray sampleData, int remainingCueBoxBytes) throws SubtitleDecoderException {
        Cue.Builder cueBuilder = null;
        CharSequence cueText = null;
        while (remainingCueBoxBytes > 0) {
            if (remainingCueBoxBytes < 8) {
                throw new SubtitleDecoderException("Incomplete vtt cue box header found.");
            }
            int boxSize = sampleData.readInt();
            int boxType = sampleData.readInt();
            int payloadLength = boxSize - 8;
            String boxPayload = Util.fromUtf8Bytes(sampleData.getData(), sampleData.getPosition(), payloadLength);
            sampleData.skipBytes(payloadLength);
            remainingCueBoxBytes = (remainingCueBoxBytes - 8) - payloadLength;
            if (boxType == TYPE_sttg) {
                cueBuilder = WebvttCueParser.parseCueSettingsList(boxPayload);
            } else if (boxType == TYPE_payl) {
                cueText = WebvttCueParser.parseCueText(null, boxPayload.trim(), Collections.emptyList());
            }
        }
        if (cueText == null) {
            cueText = "";
        }
        if (cueBuilder != null) {
            return cueBuilder.setText(cueText).build();
        }
        return WebvttCueParser.newCueForText(cueText);
    }
}
