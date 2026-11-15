package androidx.media3.extractor.text.webvtt;

import android.text.TextUtils;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.text.SimpleSubtitleDecoder;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleDecoderException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public final class WebvttDecoder extends SimpleSubtitleDecoder {
    private static final String COMMENT_START = "NOTE";
    private static final int EVENT_COMMENT = 1;
    private static final int EVENT_CUE = 3;
    private static final int EVENT_END_OF_FILE = 0;
    private static final int EVENT_NONE = -1;
    private static final int EVENT_STYLE_BLOCK = 2;
    private static final String STYLE_START = "STYLE";
    private final WebvttCssParser cssParser;
    private final ParsableByteArray parsableWebvttData;

    public WebvttDecoder() {
        super("WebvttDecoder");
        this.parsableWebvttData = new ParsableByteArray();
        this.cssParser = new WebvttCssParser();
    }

    @Override // androidx.media3.extractor.text.SimpleSubtitleDecoder
    protected Subtitle decode(byte[] data, int length, boolean reset) throws SubtitleDecoderException {
        WebvttCueInfo cueInfo;
        this.parsableWebvttData.reset(data, length);
        List<WebvttCssStyle> definedStyles = new ArrayList<>();
        try {
            WebvttParserUtil.validateWebvttHeaderLine(this.parsableWebvttData);
            while (!TextUtils.isEmpty(this.parsableWebvttData.readLine())) {
            }
            List<WebvttCueInfo> cueInfos = new ArrayList<>();
            while (true) {
                int event = getNextEvent(this.parsableWebvttData);
                if (event != 0) {
                    if (event == 1) {
                        skipComment(this.parsableWebvttData);
                    } else if (event == 2) {
                        if (!cueInfos.isEmpty()) {
                            throw new SubtitleDecoderException("A style block was found after the first cue.");
                        }
                        this.parsableWebvttData.readLine();
                        definedStyles.addAll(this.cssParser.parseBlock(this.parsableWebvttData));
                    } else if (event == 3 && (cueInfo = WebvttCueParser.parseCue(this.parsableWebvttData, definedStyles)) != null) {
                        cueInfos.add(cueInfo);
                    }
                } else {
                    return new WebvttSubtitle(cueInfos);
                }
            }
        } catch (ParserException e) {
            throw new SubtitleDecoderException(e);
        }
    }

    private static int getNextEvent(ParsableByteArray parsableWebvttData) {
        int foundEvent = -1;
        int currentInputPosition = 0;
        while (foundEvent == -1) {
            currentInputPosition = parsableWebvttData.getPosition();
            String line = parsableWebvttData.readLine();
            if (line == null) {
                foundEvent = 0;
            } else if (STYLE_START.equals(line)) {
                foundEvent = 2;
            } else if (line.startsWith(COMMENT_START)) {
                foundEvent = 1;
            } else {
                foundEvent = 3;
            }
        }
        parsableWebvttData.setPosition(currentInputPosition);
        return foundEvent;
    }

    private static void skipComment(ParsableByteArray parsableWebvttData) {
        while (!TextUtils.isEmpty(parsableWebvttData.readLine())) {
        }
    }
}
