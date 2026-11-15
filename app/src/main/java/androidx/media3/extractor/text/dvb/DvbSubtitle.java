package androidx.media3.extractor.text.dvb;

import androidx.media3.common.text.Cue;
import androidx.media3.extractor.text.Subtitle;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
final class DvbSubtitle implements Subtitle {
    private final List<Cue> cues;

    public DvbSubtitle(List<Cue> cues) {
        this.cues = cues;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getNextEventTimeIndex(long timeUs) {
        return -1;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getEventTimeCount() {
        return 1;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public long getEventTime(int index) {
        return 0L;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public List<Cue> getCues(long timeUs) {
        return this.cues;
    }
}
