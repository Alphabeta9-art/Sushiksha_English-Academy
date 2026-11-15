package androidx.media3.extractor.text.dvb;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.text.SimpleSubtitleDecoder;
import androidx.media3.extractor.text.Subtitle;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public final class DvbDecoder extends SimpleSubtitleDecoder {
    private final DvbParser parser;

    public DvbDecoder(List<byte[]> initializationData) {
        super("DvbDecoder");
        ParsableByteArray data = new ParsableByteArray(initializationData.get(0));
        int subtitleCompositionPage = data.readUnsignedShort();
        int subtitleAncillaryPage = data.readUnsignedShort();
        this.parser = new DvbParser(subtitleCompositionPage, subtitleAncillaryPage);
    }

    @Override // androidx.media3.extractor.text.SimpleSubtitleDecoder
    protected Subtitle decode(byte[] data, int length, boolean reset) {
        if (reset) {
            this.parser.reset();
        }
        return new DvbSubtitle(this.parser.decode(data, length));
    }
}
