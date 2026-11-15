package androidx.media3.extractor.text.subrip;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.LongArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.text.SimpleSubtitleDecoder;
import androidx.media3.extractor.text.Subtitle;
import com.google.common.base.Charsets;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes11.dex */
public final class SubripDecoder extends SimpleSubtitleDecoder {
    private static final String ALIGN_BOTTOM_LEFT = "{\\an1}";
    private static final String ALIGN_BOTTOM_MID = "{\\an2}";
    private static final String ALIGN_BOTTOM_RIGHT = "{\\an3}";
    private static final String ALIGN_MID_LEFT = "{\\an4}";
    private static final String ALIGN_MID_MID = "{\\an5}";
    private static final String ALIGN_MID_RIGHT = "{\\an6}";
    private static final String ALIGN_TOP_LEFT = "{\\an7}";
    private static final String ALIGN_TOP_MID = "{\\an8}";
    private static final String ALIGN_TOP_RIGHT = "{\\an9}";
    private static final float END_FRACTION = 0.92f;
    private static final float MID_FRACTION = 0.5f;
    private static final float START_FRACTION = 0.08f;
    private static final String SUBRIP_ALIGNMENT_TAG = "\\{\\\\an[1-9]\\}";
    private static final String SUBRIP_TIMECODE = "(?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?";
    private static final String TAG = "SubripDecoder";
    private final ArrayList<String> tags;
    private final StringBuilder textBuilder;
    private static final Pattern SUBRIP_TIMING_LINE = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?)\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?)\\s*");
    private static final Pattern SUBRIP_TAG_PATTERN = Pattern.compile("\\{\\\\.*?\\}");

    public SubripDecoder() {
        super(TAG);
        this.textBuilder = new StringBuilder();
        this.tags = new ArrayList<>();
    }

    @Override // androidx.media3.extractor.text.SimpleSubtitleDecoder
    protected Subtitle decode(byte[] data, int length, boolean reset) {
        ArrayList<Cue> cues = new ArrayList<>();
        LongArray cueTimesUs = new LongArray();
        ParsableByteArray subripData = new ParsableByteArray(data, length);
        Charset charset = detectUtfCharset(subripData);
        while (true) {
            String currentLine = subripData.readLine(charset);
            if (currentLine == null) {
                break;
            }
            if (currentLine.length() != 0) {
                try {
                    Integer.parseInt(currentLine);
                    String currentLine2 = subripData.readLine(charset);
                    if (currentLine2 == null) {
                        Log.w(TAG, "Unexpected end");
                        break;
                    }
                    Matcher matcher = SUBRIP_TIMING_LINE.matcher(currentLine2);
                    if (!matcher.matches()) {
                        Log.w(TAG, "Skipping invalid timing: " + currentLine2);
                    } else {
                        cueTimesUs.add(parseTimecode(matcher, 1));
                        cueTimesUs.add(parseTimecode(matcher, 6));
                        this.textBuilder.setLength(0);
                        this.tags.clear();
                        for (String currentLine3 = subripData.readLine(charset); !TextUtils.isEmpty(currentLine3); currentLine3 = subripData.readLine(charset)) {
                            if (this.textBuilder.length() > 0) {
                                this.textBuilder.append("<br>");
                            }
                            this.textBuilder.append(processLine(currentLine3, this.tags));
                        }
                        Spanned text = Html.fromHtml(this.textBuilder.toString());
                        String alignmentTag = null;
                        for (int i = 0; i < this.tags.size(); i++) {
                            String tag = this.tags.get(i);
                            if (tag.matches(SUBRIP_ALIGNMENT_TAG)) {
                                alignmentTag = tag;
                                break;
                            }
                        }
                        cues.add(buildCue(text, alignmentTag));
                        cues.add(Cue.EMPTY);
                    }
                } catch (NumberFormatException e) {
                    Log.w(TAG, "Skipping invalid index: " + currentLine);
                }
            }
        }
        Cue[] cuesArray = (Cue[]) cues.toArray(new Cue[0]);
        long[] cueTimesUsArray = cueTimesUs.toArray();
        return new SubripSubtitle(cuesArray, cueTimesUsArray);
    }

    private Charset detectUtfCharset(ParsableByteArray data) {
        Charset charset = data.readUtfCharsetFromBom();
        return charset != null ? charset : Charsets.UTF_8;
    }

    private String processLine(String line, ArrayList<String> tags) {
        String line2 = line.trim();
        int removedCharacterCount = 0;
        StringBuilder processedLine = new StringBuilder(line2);
        Matcher matcher = SUBRIP_TAG_PATTERN.matcher(line2);
        while (matcher.find()) {
            String tag = matcher.group();
            tags.add(tag);
            int start = matcher.start() - removedCharacterCount;
            int tagLength = tag.length();
            processedLine.replace(start, start + tagLength, "");
            removedCharacterCount += tagLength;
        }
        return processedLine.toString();
    }

    private Cue buildCue(Spanned text, String alignmentTag) {
        Cue.Builder cue = new Cue.Builder().setText(text);
        if (alignmentTag != null) {
            switch (alignmentTag) {
                case "{\an1}":
                case "{\an4}":
                case "{\an7}":
                    cue.setPositionAnchor(0);
                    break;
                case "{\an3}":
                case "{\an6}":
                case "{\an9}":
                    cue.setPositionAnchor(2);
                    break;
                case "{\an2}":
                case "{\an5}":
                case "{\an8}":
                default:
                    cue.setPositionAnchor(1);
                    break;
            }
            switch (alignmentTag) {
                case "{\an1}":
                case "{\an2}":
                case "{\an3}":
                    cue.setLineAnchor(2);
                    break;
                case "{\an7}":
                case "{\an8}":
                case "{\an9}":
                    cue.setLineAnchor(0);
                    break;
                case "{\an4}":
                case "{\an5}":
                case "{\an6}":
                default:
                    cue.setLineAnchor(1);
                    break;
            }
            return cue.setPosition(getFractionalPositionForAnchorType(cue.getPositionAnchor())).setLine(getFractionalPositionForAnchorType(cue.getLineAnchor()), 0).build();
        }
        return cue.build();
    }

    private static long parseTimecode(Matcher matcher, int groupOffset) {
        String hours = matcher.group(groupOffset + 1);
        long timestampMs = (hours != null ? Long.parseLong(hours) * 60 * 60 * 1000 : 0L) + (Long.parseLong((String) Assertions.checkNotNull(matcher.group(groupOffset + 2))) * 60 * 1000) + (Long.parseLong((String) Assertions.checkNotNull(matcher.group(groupOffset + 3))) * 1000);
        String millis = matcher.group(groupOffset + 4);
        if (millis != null) {
            timestampMs += Long.parseLong(millis);
        }
        return 1000 * timestampMs;
    }

    static float getFractionalPositionForAnchorType(int anchorType) {
        switch (anchorType) {
            case 0:
                return 0.08f;
            case 1:
                return 0.5f;
            case 2:
                return END_FRACTION;
            default:
                throw new IllegalArgumentException();
        }
    }
}
