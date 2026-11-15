package androidx.compose.ui.text;

import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextIndentKt;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnit;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ParagraphStyle.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a&\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\n2\b\u0010\u0006\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0000\u001ac\u0010\u000f\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00012\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0000¢\u0006\u0004\b \u0010!\u001a\u0018\u0010\"\u001a\u0004\u0018\u00010\n*\u00020\u00042\b\u0010#\u001a\u0004\u0018\u00010\nH\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002¨\u0006$"}, d2 = {"DefaultLineHeight", "Landroidx/compose/ui/unit/TextUnit;", "J", "lerp", "Landroidx/compose/ui/text/ParagraphStyle;", TtmlNode.START, "stop", "fraction", "", "lerpPlatformStyle", "Landroidx/compose/ui/text/PlatformParagraphStyle;", "resolveParagraphStyleDefaults", TtmlNode.TAG_STYLE, "direction", "Landroidx/compose/ui/unit/LayoutDirection;", "fastMerge", TtmlNode.ATTR_TTS_TEXT_ALIGN, "Landroidx/compose/ui/text/style/TextAlign;", "textDirection", "Landroidx/compose/ui/text/style/TextDirection;", "lineHeight", "textIndent", "Landroidx/compose/ui/text/style/TextIndent;", "platformStyle", "lineHeightStyle", "Landroidx/compose/ui/text/style/LineHeightStyle;", "lineBreak", "Landroidx/compose/ui/text/style/LineBreak;", "hyphens", "Landroidx/compose/ui/text/style/Hyphens;", "textMotion", "Landroidx/compose/ui/text/style/TextMotion;", "fastMerge-j5T8yCg", "(Landroidx/compose/ui/text/ParagraphStyle;IIJLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformParagraphStyle;Landroidx/compose/ui/text/style/LineHeightStyle;IILandroidx/compose/ui/text/style/TextMotion;)Landroidx/compose/ui/text/ParagraphStyle;", "mergePlatformStyle", "other", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ParagraphStyleKt {
    private static final long DefaultLineHeight = TextUnit.INSTANCE.m8110getUnspecifiedXSAIIZE();

    public static final ParagraphStyle lerp(ParagraphStyle start, ParagraphStyle stop, float fraction) {
        int iM7757unboximpl = ((TextAlign) SpanStyleKt.lerpDiscrete(TextAlign.m7751boximpl(start.getTextAlign()), TextAlign.m7751boximpl(stop.getTextAlign()), fraction)).m7757unboximpl();
        int iM7774unboximpl = ((TextDirection) SpanStyleKt.lerpDiscrete(TextDirection.m7768boximpl(start.getTextDirection()), TextDirection.m7768boximpl(stop.getTextDirection()), fraction)).m7774unboximpl();
        long jM7275lerpTextUnitInheritableC3pnCVY = SpanStyleKt.m7275lerpTextUnitInheritableC3pnCVY(start.getLineHeight(), stop.getLineHeight(), fraction);
        TextIndent textIndent = start.getTextIndent();
        if (textIndent == null) {
            textIndent = TextIndent.INSTANCE.getNone();
        }
        TextIndent textIndent2 = stop.getTextIndent();
        if (textIndent2 == null) {
            textIndent2 = TextIndent.INSTANCE.getNone();
        }
        return new ParagraphStyle(iM7757unboximpl, iM7774unboximpl, jM7275lerpTextUnitInheritableC3pnCVY, TextIndentKt.lerp(textIndent, textIndent2, fraction), lerpPlatformStyle(start.getPlatformStyle(), stop.getPlatformStyle(), fraction), (LineHeightStyle) SpanStyleKt.lerpDiscrete(start.getLineHeightStyle(), stop.getLineHeightStyle(), fraction), ((LineBreak) SpanStyleKt.lerpDiscrete(LineBreak.m7656boximpl(start.getLineBreak()), LineBreak.m7656boximpl(stop.getLineBreak()), fraction)).getMask(), ((Hyphens) SpanStyleKt.lerpDiscrete(Hyphens.m7643boximpl(start.getHyphens()), Hyphens.m7643boximpl(stop.getHyphens()), fraction)).m7649unboximpl(), (TextMotion) SpanStyleKt.lerpDiscrete(start.getTextMotion(), stop.getTextMotion(), fraction), (DefaultConstructorMarker) null);
    }

    private static final PlatformParagraphStyle lerpPlatformStyle(PlatformParagraphStyle start, PlatformParagraphStyle stop, float fraction) {
        if (start == null && stop == null) {
            return null;
        }
        PlatformParagraphStyle startNonNull = start == null ? PlatformParagraphStyle.INSTANCE.getDefault() : start;
        PlatformParagraphStyle stopNonNull = stop == null ? PlatformParagraphStyle.INSTANCE.getDefault() : stop;
        return AndroidTextStyle_androidKt.lerp(startNonNull, stopNonNull, fraction);
    }

    public static final ParagraphStyle resolveParagraphStyleDefaults(ParagraphStyle style, LayoutDirection direction) {
        int iM7763getStarte0LSkKk = TextAlign.m7754equalsimpl0(style.getTextAlign(), TextAlign.INSTANCE.m7764getUnspecifiede0LSkKk()) ? TextAlign.INSTANCE.m7763getStarte0LSkKk() : style.getTextAlign();
        int iM7374resolveTextDirectionIhaHGbI = TextStyleKt.m7374resolveTextDirectionIhaHGbI(direction, style.getTextDirection());
        long lineHeight = (TextUnit.m8097getRawTypeimpl(style.getLineHeight()) > 0L ? 1 : (TextUnit.m8097getRawTypeimpl(style.getLineHeight()) == 0L ? 0 : -1)) == 0 ? DefaultLineHeight : style.getLineHeight();
        TextIndent textIndent = style.getTextIndent();
        if (textIndent == null) {
            textIndent = TextIndent.INSTANCE.getNone();
        }
        PlatformParagraphStyle platformStyle = style.getPlatformStyle();
        LineHeightStyle lineHeightStyle = style.getLineHeightStyle();
        int iM7675getSimplerAG3T2k = LineBreak.m7662equalsimpl0(style.getLineBreak(), LineBreak.INSTANCE.m7676getUnspecifiedrAG3T2k()) ? LineBreak.INSTANCE.m7675getSimplerAG3T2k() : style.getLineBreak();
        int iM7651getNonevmbZdU8 = Hyphens.m7646equalsimpl0(style.getHyphens(), Hyphens.INSTANCE.m7652getUnspecifiedvmbZdU8()) ? Hyphens.INSTANCE.m7651getNonevmbZdU8() : style.getHyphens();
        TextMotion textMotion = style.getTextMotion();
        if (textMotion == null) {
            textMotion = TextMotion.INSTANCE.getStatic();
        }
        return new ParagraphStyle(iM7763getStarte0LSkKk, iM7374resolveTextDirectionIhaHGbI, lineHeight, textIndent, platformStyle, lineHeightStyle, iM7675getSimplerAG3T2k, iM7651getNonevmbZdU8, textMotion, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Code duplicated, block: B:51:0x00db A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:52:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:54:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:56:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:57:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:60:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:61:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:64:0x010c  */
    /* JADX WARN: Code duplicated, block: B:65:0x010e  */
    /* JADX WARN: Code duplicated, block: B:68:0x011f  */
    /* JADX WARN: Code duplicated, block: B:69:0x0122  */
    /* JADX WARN: Code duplicated, block: B:72:0x012e  */
    /* JADX WARN: Code duplicated, block: B:73:0x0135  */
    /* JADX WARN: Code duplicated, block: B:76:0x0143  */
    /* JADX WARN: Code duplicated, block: B:77:0x0146  */
    /* JADX WARN: Code duplicated, block: B:80:0x0158  */
    /* JADX WARN: Code duplicated, block: B:81:0x015b  */
    /* JADX WARN: Code duplicated, block: B:83:0x0163  */
    /* JADX WARN: Code duplicated, block: B:84:0x016a  */
    /* JADX INFO: renamed from: fastMerge-j5T8yCg, reason: not valid java name */
    public static final ParagraphStyle m7236fastMergej5T8yCg(ParagraphStyle $this$fastMerge_u2dj5T8yCg, int i, int i2, long j, TextIndent textIndent, PlatformParagraphStyle platformStyle, LineHeightStyle lineHeightStyle, int i3, int i4, TextMotion textMotion) {
        long j2;
        long j3;
        boolean requiresAlloc;
        long lineHeight;
        TextIndent textIndent2;
        int textAlign;
        int textDirection;
        LineHeightStyle lineHeightStyle2;
        int lineBreak;
        int hyphens;
        TextMotion textMotion2;
        if (TextAlign.m7754equalsimpl0(i, TextAlign.INSTANCE.m7764getUnspecifiede0LSkKk()) || TextAlign.m7754equalsimpl0(i, $this$fastMerge_u2dj5T8yCg.getTextAlign())) {
            if (!(TextUnit.m8097getRawTypeimpl(j) == 0)) {
                j2 = 0;
                j3 = j;
                if (TextUnit.m8096equalsimpl0(j3, $this$fastMerge_u2dj5T8yCg.getLineHeight())) {
                }
                if (!requiresAlloc) {
                    return $this$fastMerge_u2dj5T8yCg;
                }
                if (TextUnit.m8097getRawTypeimpl(j) == j2) {
                    lineHeight = $this$fastMerge_u2dj5T8yCg.getLineHeight();
                } else {
                    lineHeight = j3;
                }
                if (textIndent == null) {
                    textIndent2 = $this$fastMerge_u2dj5T8yCg.getTextIndent();
                } else {
                    textIndent2 = textIndent;
                }
                if (TextAlign.m7754equalsimpl0(i, TextAlign.INSTANCE.m7764getUnspecifiede0LSkKk())) {
                    textAlign = $this$fastMerge_u2dj5T8yCg.getTextAlign();
                } else {
                    textAlign = i;
                }
                if (TextDirection.m7771equalsimpl0(i2, TextDirection.INSTANCE.m7780getUnspecifieds_7Xco())) {
                    textDirection = $this$fastMerge_u2dj5T8yCg.getTextDirection();
                } else {
                    textDirection = i2;
                }
                PlatformParagraphStyle platformParagraphStyleMergePlatformStyle = mergePlatformStyle($this$fastMerge_u2dj5T8yCg, platformStyle);
                if (lineHeightStyle == null) {
                    lineHeightStyle2 = $this$fastMerge_u2dj5T8yCg.getLineHeightStyle();
                } else {
                    lineHeightStyle2 = lineHeightStyle;
                }
                if (LineBreak.m7662equalsimpl0(i3, LineBreak.INSTANCE.m7676getUnspecifiedrAG3T2k())) {
                    lineBreak = $this$fastMerge_u2dj5T8yCg.getLineBreak();
                } else {
                    lineBreak = i3;
                }
                if (Hyphens.m7646equalsimpl0(i4, Hyphens.INSTANCE.m7652getUnspecifiedvmbZdU8())) {
                    hyphens = $this$fastMerge_u2dj5T8yCg.getHyphens();
                } else {
                    hyphens = i4;
                }
                if (textMotion == null) {
                    textMotion2 = $this$fastMerge_u2dj5T8yCg.getTextMotion();
                } else {
                    textMotion2 = textMotion;
                }
                return new ParagraphStyle(textAlign, textDirection, lineHeight, textIndent2, platformParagraphStyleMergePlatformStyle, lineHeightStyle2, lineBreak, hyphens, textMotion2, (DefaultConstructorMarker) null);
            }
            j2 = 0;
            j3 = j;
            if ((textIndent == null || Intrinsics.areEqual(textIndent, $this$fastMerge_u2dj5T8yCg.getTextIndent())) && ((TextDirection.m7771equalsimpl0(i2, TextDirection.INSTANCE.m7780getUnspecifieds_7Xco()) || TextDirection.m7771equalsimpl0(i2, $this$fastMerge_u2dj5T8yCg.getTextDirection())) && ((platformStyle == null || Intrinsics.areEqual(platformStyle, $this$fastMerge_u2dj5T8yCg.getPlatformStyle())) && ((lineHeightStyle == null || Intrinsics.areEqual(lineHeightStyle, $this$fastMerge_u2dj5T8yCg.getLineHeightStyle())) && ((LineBreak.m7662equalsimpl0(i3, LineBreak.INSTANCE.m7676getUnspecifiedrAG3T2k()) || LineBreak.m7662equalsimpl0(i3, $this$fastMerge_u2dj5T8yCg.getLineBreak())) && ((Hyphens.m7646equalsimpl0(i4, Hyphens.INSTANCE.m7652getUnspecifiedvmbZdU8()) || Hyphens.m7646equalsimpl0(i4, $this$fastMerge_u2dj5T8yCg.getHyphens())) && (textMotion == null || Intrinsics.areEqual(textMotion, $this$fastMerge_u2dj5T8yCg.getTextMotion())))))))) {
                requiresAlloc = false;
            }
            if (!requiresAlloc) {
                return $this$fastMerge_u2dj5T8yCg;
            }
            if (TextUnit.m8097getRawTypeimpl(j) == j2) {
                lineHeight = $this$fastMerge_u2dj5T8yCg.getLineHeight();
            } else {
                lineHeight = j3;
            }
            if (textIndent == null) {
                textIndent2 = $this$fastMerge_u2dj5T8yCg.getTextIndent();
            } else {
                textIndent2 = textIndent;
            }
            if (TextAlign.m7754equalsimpl0(i, TextAlign.INSTANCE.m7764getUnspecifiede0LSkKk())) {
                textAlign = i;
            } else {
                textAlign = $this$fastMerge_u2dj5T8yCg.getTextAlign();
            }
            if (TextDirection.m7771equalsimpl0(i2, TextDirection.INSTANCE.m7780getUnspecifieds_7Xco())) {
                textDirection = i2;
            } else {
                textDirection = $this$fastMerge_u2dj5T8yCg.getTextDirection();
            }
            PlatformParagraphStyle platformParagraphStyleMergePlatformStyle2 = mergePlatformStyle($this$fastMerge_u2dj5T8yCg, platformStyle);
            if (lineHeightStyle == null) {
                lineHeightStyle2 = $this$fastMerge_u2dj5T8yCg.getLineHeightStyle();
            } else {
                lineHeightStyle2 = lineHeightStyle;
            }
            if (LineBreak.m7662equalsimpl0(i3, LineBreak.INSTANCE.m7676getUnspecifiedrAG3T2k())) {
                lineBreak = i3;
            } else {
                lineBreak = $this$fastMerge_u2dj5T8yCg.getLineBreak();
            }
            if (Hyphens.m7646equalsimpl0(i4, Hyphens.INSTANCE.m7652getUnspecifiedvmbZdU8())) {
                hyphens = i4;
            } else {
                hyphens = $this$fastMerge_u2dj5T8yCg.getHyphens();
            }
            if (textMotion == null) {
                textMotion2 = $this$fastMerge_u2dj5T8yCg.getTextMotion();
            } else {
                textMotion2 = textMotion;
            }
            return new ParagraphStyle(textAlign, textDirection, lineHeight, textIndent2, platformParagraphStyleMergePlatformStyle2, lineHeightStyle2, lineBreak, hyphens, textMotion2, (DefaultConstructorMarker) null);
        }
        j2 = 0;
        j3 = j;
        requiresAlloc = true;
        if (!requiresAlloc) {
            return $this$fastMerge_u2dj5T8yCg;
        }
        if (TextUnit.m8097getRawTypeimpl(j) == j2) {
            lineHeight = $this$fastMerge_u2dj5T8yCg.getLineHeight();
        } else {
            lineHeight = j3;
        }
        if (textIndent == null) {
            textIndent2 = $this$fastMerge_u2dj5T8yCg.getTextIndent();
        } else {
            textIndent2 = textIndent;
        }
        if (TextAlign.m7754equalsimpl0(i, TextAlign.INSTANCE.m7764getUnspecifiede0LSkKk())) {
            textAlign = i;
        } else {
            textAlign = $this$fastMerge_u2dj5T8yCg.getTextAlign();
        }
        if (TextDirection.m7771equalsimpl0(i2, TextDirection.INSTANCE.m7780getUnspecifieds_7Xco())) {
            textDirection = i2;
        } else {
            textDirection = $this$fastMerge_u2dj5T8yCg.getTextDirection();
        }
        PlatformParagraphStyle platformParagraphStyleMergePlatformStyle3 = mergePlatformStyle($this$fastMerge_u2dj5T8yCg, platformStyle);
        if (lineHeightStyle == null) {
            lineHeightStyle2 = $this$fastMerge_u2dj5T8yCg.getLineHeightStyle();
        } else {
            lineHeightStyle2 = lineHeightStyle;
        }
        if (LineBreak.m7662equalsimpl0(i3, LineBreak.INSTANCE.m7676getUnspecifiedrAG3T2k())) {
            lineBreak = i3;
        } else {
            lineBreak = $this$fastMerge_u2dj5T8yCg.getLineBreak();
        }
        if (Hyphens.m7646equalsimpl0(i4, Hyphens.INSTANCE.m7652getUnspecifiedvmbZdU8())) {
            hyphens = i4;
        } else {
            hyphens = $this$fastMerge_u2dj5T8yCg.getHyphens();
        }
        if (textMotion == null) {
            textMotion2 = $this$fastMerge_u2dj5T8yCg.getTextMotion();
        } else {
            textMotion2 = textMotion;
        }
        return new ParagraphStyle(textAlign, textDirection, lineHeight, textIndent2, platformParagraphStyleMergePlatformStyle3, lineHeightStyle2, lineBreak, hyphens, textMotion2, (DefaultConstructorMarker) null);
    }

    private static final PlatformParagraphStyle mergePlatformStyle(ParagraphStyle $this$mergePlatformStyle, PlatformParagraphStyle other) {
        if ($this$mergePlatformStyle.getPlatformStyle() == null) {
            return other;
        }
        return other == null ? $this$mergePlatformStyle.getPlatformStyle() : $this$mergePlatformStyle.getPlatformStyle().merge(other);
    }
}
