package androidx.compose.ui.text;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.ShadowKt;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.FontWeightKt;
import androidx.compose.ui.text.font.SystemFontFamily;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.BaselineShiftKt;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDrawStyleKt;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextGeometricTransformKt;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SpanStyle.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a'\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a+\u0010\u0010\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u00112\u0006\u0010\n\u001a\u0002H\u00112\u0006\u0010\u000b\u001a\u0002H\u00112\u0006\u0010\u0012\u001a\u00020\rH\u0000¢\u0006\u0002\u0010\u0013\u001a\u001e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\r\u001a&\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u00192\b\u0010\u0017\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0012\u001a\u00020\rH\u0002\u001a\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0015H\u0000\u001a½\u0001\u0010\u001c\u001a\u00020\u0015*\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\u00012\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020\u00012\b\u0010-\u001a\u0004\u0018\u00010.2\b\u0010/\u001a\u0004\u0018\u0001002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u00020\u00052\b\u00104\u001a\u0004\u0018\u0001052\b\u00106\u001a\u0004\u0018\u0001072\b\u00108\u001a\u0004\u0018\u00010\u00192\b\u00109\u001a\u0004\u0018\u00010:H\u0000¢\u0006\u0004\b;\u0010<\u001a\u0018\u0010=\u001a\u0004\u0018\u00010\u0019*\u00020\u00152\b\u0010>\u001a\u0004\u0018\u00010\u0019H\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"DefaultFontSize", "Landroidx/compose/ui/unit/TextUnit;", "J", "DefaultLetterSpacing", "DefaultBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "DefaultColor", "DefaultColorForegroundStyle", "Landroidx/compose/ui/text/style/TextForegroundStyle;", "lerpTextUnitInheritable", CmcdHeadersFactory.OBJECT_TYPE_AUDIO_ONLY, "b", "t", "", "lerpTextUnitInheritable-C3pnCVY", "(JJF)J", "lerpDiscrete", ExifInterface.GPS_DIRECTION_TRUE, "fraction", "(Ljava/lang/Object;Ljava/lang/Object;F)Ljava/lang/Object;", "lerp", "Landroidx/compose/ui/text/SpanStyle;", TtmlNode.START, "stop", "lerpPlatformStyle", "Landroidx/compose/ui/text/PlatformSpanStyle;", "resolveSpanStyleDefaults", TtmlNode.TAG_STYLE, "fastMerge", "color", "brush", "Landroidx/compose/ui/graphics/Brush;", "alpha", TtmlNode.ATTR_TTS_FONT_SIZE, TtmlNode.ATTR_TTS_FONT_WEIGHT, "Landroidx/compose/ui/text/font/FontWeight;", TtmlNode.ATTR_TTS_FONT_STYLE, "Landroidx/compose/ui/text/font/FontStyle;", "fontSynthesis", "Landroidx/compose/ui/text/font/FontSynthesis;", TtmlNode.ATTR_TTS_FONT_FAMILY, "Landroidx/compose/ui/text/font/FontFamily;", "fontFeatureSettings", "", "letterSpacing", "baselineShift", "Landroidx/compose/ui/text/style/BaselineShift;", "textGeometricTransform", "Landroidx/compose/ui/text/style/TextGeometricTransform;", "localeList", "Landroidx/compose/ui/text/intl/LocaleList;", "background", TtmlNode.ATTR_TTS_TEXT_DECORATION, "Landroidx/compose/ui/text/style/TextDecoration;", "shadow", "Landroidx/compose/ui/graphics/Shadow;", "platformStyle", "drawStyle", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "fastMerge-dSHsh3o", "(Landroidx/compose/ui/text/SpanStyle;JLandroidx/compose/ui/graphics/Brush;FJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/PlatformSpanStyle;Landroidx/compose/ui/graphics/drawscope/DrawStyle;)Landroidx/compose/ui/text/SpanStyle;", "mergePlatformStyle", "other", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SpanStyleKt {
    private static final long DefaultFontSize = TextUnitKt.getSp(14);
    private static final long DefaultLetterSpacing = TextUnitKt.getSp(0);
    private static final long DefaultBackgroundColor = Color.INSTANCE.m5132getTransparent0d7_KjU();
    private static final long DefaultColor = Color.INSTANCE.m5123getBlack0d7_KjU();
    private static final TextForegroundStyle DefaultColorForegroundStyle = TextForegroundStyle.INSTANCE.m7785from8_81llA(DefaultColor);

    /* JADX INFO: renamed from: lerpTextUnitInheritable-C3pnCVY, reason: not valid java name */
    public static final long m7275lerpTextUnitInheritableC3pnCVY(long j, long j2, float t) {
        if (!(TextUnit.m8097getRawTypeimpl(j) == 0)) {
            if (!(TextUnit.m8097getRawTypeimpl(j2) == 0)) {
                return TextUnitKt.m8119lerpC3pnCVY(j, j2, t);
            }
        }
        return ((TextUnit) lerpDiscrete(TextUnit.m8089boximpl(j), TextUnit.m8089boximpl(j2), t)).getPackedValue();
    }

    public static final <T> T lerpDiscrete(T t, T t2, float fraction) {
        return ((double) fraction) < 0.5d ? t : t2;
    }

    public static final SpanStyle lerp(SpanStyle start, SpanStyle stop, float fraction) {
        TextForegroundStyle textForegroundStyleLerp = TextDrawStyleKt.lerp(start.getTextForegroundStyle(), stop.getTextForegroundStyle(), fraction);
        FontFamily fontFamily = (FontFamily) lerpDiscrete(start.getFontFamily(), stop.getFontFamily(), fraction);
        long jM7275lerpTextUnitInheritableC3pnCVY = m7275lerpTextUnitInheritableC3pnCVY(start.getFontSize(), stop.getFontSize(), fraction);
        FontWeight fontWeight = start.getFontWeight();
        if (fontWeight == null) {
            fontWeight = FontWeight.INSTANCE.getNormal();
        }
        FontWeight fontWeight2 = stop.getFontWeight();
        if (fontWeight2 == null) {
            fontWeight2 = FontWeight.INSTANCE.getNormal();
        }
        FontWeight fontWeightLerp = FontWeightKt.lerp(fontWeight, fontWeight2, fraction);
        FontStyle fontStyle = (FontStyle) lerpDiscrete(start.getFontStyle(), stop.getFontStyle(), fraction);
        FontSynthesis fontSynthesis = (FontSynthesis) lerpDiscrete(start.getFontSynthesis(), stop.getFontSynthesis(), fraction);
        String str = (String) lerpDiscrete(start.getFontFeatureSettings(), stop.getFontFeatureSettings(), fraction);
        long jM7275lerpTextUnitInheritableC3pnCVY2 = m7275lerpTextUnitInheritableC3pnCVY(start.getLetterSpacing(), stop.getLetterSpacing(), fraction);
        BaselineShift baselineShift = start.getBaselineShift();
        float fM7626unboximpl = baselineShift != null ? baselineShift.m7626unboximpl() : BaselineShift.m7621constructorimpl(0.0f);
        BaselineShift baselineShift2 = stop.getBaselineShift();
        float fM7636lerpjWV1Mfo = BaselineShiftKt.m7636lerpjWV1Mfo(fM7626unboximpl, baselineShift2 != null ? baselineShift2.m7626unboximpl() : BaselineShift.m7621constructorimpl(0.0f), fraction);
        TextGeometricTransform textGeometricTransform = start.getTextGeometricTransform();
        if (textGeometricTransform == null) {
            textGeometricTransform = TextGeometricTransform.INSTANCE.getNone$ui_text();
        }
        TextGeometricTransform textGeometricTransform2 = stop.getTextGeometricTransform();
        if (textGeometricTransform2 == null) {
            textGeometricTransform2 = TextGeometricTransform.INSTANCE.getNone$ui_text();
        }
        TextGeometricTransform textGeometricTransformLerp = TextGeometricTransformKt.lerp(textGeometricTransform, textGeometricTransform2, fraction);
        LocaleList localeList = (LocaleList) lerpDiscrete(start.getLocaleList(), stop.getLocaleList(), fraction);
        long jM5148lerpjxsXWHM = ColorKt.m5148lerpjxsXWHM(start.getBackground(), stop.getBackground(), fraction);
        TextDecoration textDecoration = (TextDecoration) lerpDiscrete(start.getTextDecoration(), stop.getTextDecoration(), fraction);
        Shadow shadow = start.getShadow();
        if (shadow == null) {
            shadow = new Shadow(0L, 0L, 0.0f, 7, null);
        }
        Shadow shadow2 = stop.getShadow();
        if (shadow2 == null) {
            shadow2 = new Shadow(0L, 0L, 0.0f, 7, null);
        }
        return new SpanStyle(textForegroundStyleLerp, jM7275lerpTextUnitInheritableC3pnCVY, fontWeightLerp, fontStyle, fontSynthesis, fontFamily, str, jM7275lerpTextUnitInheritableC3pnCVY2, BaselineShift.m7620boximpl(fM7636lerpjWV1Mfo), textGeometricTransformLerp, localeList, jM5148lerpjxsXWHM, textDecoration, ShadowKt.lerp(shadow, shadow2, fraction), lerpPlatformStyle(start.getPlatformStyle(), stop.getPlatformStyle(), fraction), (DrawStyle) lerpDiscrete(start.getDrawStyle(), stop.getDrawStyle(), fraction), (DefaultConstructorMarker) null);
    }

    private static final PlatformSpanStyle lerpPlatformStyle(PlatformSpanStyle start, PlatformSpanStyle stop, float fraction) {
        if (start == null && stop == null) {
            return null;
        }
        PlatformSpanStyle startNonNull = start == null ? PlatformSpanStyle.INSTANCE.getDefault() : start;
        PlatformSpanStyle stopNonNull = stop == null ? PlatformSpanStyle.INSTANCE.getDefault() : stop;
        return AndroidTextStyle_androidKt.lerp(startNonNull, stopNonNull, fraction);
    }

    public static final SpanStyle resolveSpanStyleDefaults(SpanStyle style) {
        long letterSpacing;
        TextForegroundStyle textForegroundStyleTakeOrElse = style.getTextForegroundStyle().takeOrElse(new Function0() { // from class: androidx.compose.ui.text.SpanStyleKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SpanStyleKt.DefaultColorForegroundStyle;
            }
        });
        long fontSize = (TextUnit.m8097getRawTypeimpl(style.getFontSize()) > 0L ? 1 : (TextUnit.m8097getRawTypeimpl(style.getFontSize()) == 0L ? 0 : -1)) == 0 ? DefaultFontSize : style.getFontSize();
        FontWeight fontWeight = style.getFontWeight();
        if (fontWeight == null) {
            fontWeight = FontWeight.INSTANCE.getNormal();
        }
        FontStyle fontStyle = style.getFontStyle();
        FontStyle fontStyleM7437boximpl = FontStyle.m7437boximpl(fontStyle != null ? fontStyle.m7443unboximpl() : FontStyle.INSTANCE.m7447getNormal_LCdwA());
        FontSynthesis fontSynthesis = style.getFontSynthesis();
        FontSynthesis fontSynthesisM7448boximpl = FontSynthesis.m7448boximpl(fontSynthesis != null ? fontSynthesis.m7456unboximpl() : FontSynthesis.INSTANCE.m7457getAllGVVA2EU());
        SystemFontFamily fontFamily = style.getFontFamily();
        if (fontFamily == null) {
            fontFamily = FontFamily.INSTANCE.getDefault();
        }
        String fontFeatureSettings = style.getFontFeatureSettings();
        if (fontFeatureSettings == null) {
            fontFeatureSettings = "";
        }
        if (TextUnit.m8097getRawTypeimpl(style.getLetterSpacing()) == 0) {
            letterSpacing = DefaultLetterSpacing;
        } else {
            letterSpacing = style.getLetterSpacing();
        }
        BaselineShift baselineShift = style.getBaselineShift();
        float fM7626unboximpl = baselineShift != null ? baselineShift.m7626unboximpl() : BaselineShift.INSTANCE.m7631getNoney9eOQZs();
        if (Float.isNaN(fM7626unboximpl)) {
            fM7626unboximpl = BaselineShift.INSTANCE.m7631getNoney9eOQZs();
        }
        BaselineShift baselineShiftM7620boximpl = BaselineShift.m7620boximpl(fM7626unboximpl);
        TextGeometricTransform textGeometricTransform = style.getTextGeometricTransform();
        if (textGeometricTransform == null) {
            textGeometricTransform = TextGeometricTransform.INSTANCE.getNone$ui_text();
        }
        TextGeometricTransform textGeometricTransform2 = textGeometricTransform;
        LocaleList localeList = style.getLocaleList();
        if (localeList == null) {
            localeList = LocaleList.INSTANCE.getCurrent();
        }
        LocaleList localeList2 = localeList;
        long background = style.getBackground();
        if (!(background != 16)) {
            background = DefaultBackgroundColor;
        }
        TextDecoration textDecoration = style.getTextDecoration();
        if (textDecoration == null) {
            textDecoration = TextDecoration.INSTANCE.getNone();
        }
        TextDecoration textDecoration2 = textDecoration;
        Shadow shadow = style.getShadow();
        if (shadow == null) {
            shadow = Shadow.INSTANCE.getNone();
        }
        Shadow shadow2 = shadow;
        PlatformSpanStyle platformStyle = style.getPlatformStyle();
        Fill drawStyle = style.getDrawStyle();
        if (drawStyle == null) {
            drawStyle = Fill.INSTANCE;
        }
        return new SpanStyle(textForegroundStyleTakeOrElse, fontSize, fontWeight, fontStyleM7437boximpl, fontSynthesisM7448boximpl, fontFamily, fontFeatureSettings, letterSpacing, baselineShiftM7620boximpl, textGeometricTransform2, localeList2, background, textDecoration2, shadow2, platformStyle, drawStyle, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Code duplicated, block: B:106:0x01fc A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:107:0x01fd A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:108:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:109:0x0206  */
    /* JADX WARN: Code duplicated, block: B:112:0x0218  */
    /* JADX WARN: Code duplicated, block: B:113:0x021f  */
    /* JADX WARN: Code duplicated, block: B:116:0x022d  */
    /* JADX WARN: Code duplicated, block: B:117:0x0230  */
    /* JADX WARN: Code duplicated, block: B:119:0x0234  */
    /* JADX WARN: Code duplicated, block: B:120:0x0237  */
    /* JADX WARN: Code duplicated, block: B:122:0x023f  */
    /* JADX WARN: Code duplicated, block: B:123:0x0246  */
    /* JADX WARN: Code duplicated, block: B:125:0x024a  */
    /* JADX WARN: Code duplicated, block: B:126:0x0251  */
    /* JADX WARN: Code duplicated, block: B:128:0x0255  */
    /* JADX WARN: Code duplicated, block: B:129:0x025c  */
    /* JADX WARN: Code duplicated, block: B:131:0x0260  */
    /* JADX WARN: Code duplicated, block: B:132:0x0267  */
    /* JADX WARN: Code duplicated, block: B:135:0x0275  */
    /* JADX WARN: Code duplicated, block: B:136:0x0278  */
    /* JADX WARN: Code duplicated, block: B:138:0x027c  */
    /* JADX WARN: Code duplicated, block: B:139:0x027f  */
    /* JADX WARN: Code duplicated, block: B:142:0x0288  */
    /* JADX WARN: Code duplicated, block: B:143:0x028f  */
    /* JADX WARN: Code duplicated, block: B:145:0x0293  */
    /* JADX WARN: Code duplicated, block: B:146:0x029a  */
    /* JADX WARN: Code duplicated, block: B:148:0x029e  */
    /* JADX WARN: Code duplicated, block: B:149:0x02a5  */
    /* JADX WARN: Code duplicated, block: B:152:0x02b3  */
    /* JADX WARN: Code duplicated, block: B:154:0x02b7  */
    /* JADX WARN: Code duplicated, block: B:155:0x02ba  */
    /* JADX WARN: Code duplicated, block: B:158:0x02c5  */
    /* JADX WARN: Code duplicated, block: B:159:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:161:0x02d0  */
    /* JADX WARN: Code duplicated, block: B:162:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:165:0x02e1  */
    /* JADX WARN: Code duplicated, block: B:166:0x02e8  */
    /* JADX WARN: Code duplicated, block: B:21:0x0077  */
    /* JADX WARN: Code duplicated, block: B:25:0x008d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:26:0x008f  */
    /* JADX WARN: Code duplicated, block: B:29:0x00a3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:30:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:33:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:35:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:36:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:39:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:42:0x00e3 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:44:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:47:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:50:0x010b  */
    /* JADX WARN: Code duplicated, block: B:52:0x0117  */
    /* JADX WARN: Code duplicated, block: B:53:0x011a  */
    /* JADX WARN: Code duplicated, block: B:55:0x011e  */
    /* JADX WARN: Code duplicated, block: B:56:0x0128  */
    /* JADX WARN: Code duplicated, block: B:57:0x012a  */
    /* JADX WARN: Code duplicated, block: B:60:0x013e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:61:0x0140  */
    /* JADX WARN: Code duplicated, block: B:64:0x0154 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:65:0x0156  */
    /* JADX WARN: Code duplicated, block: B:68:0x016a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:69:0x016c  */
    /* JADX WARN: Code duplicated, block: B:71:0x0178  */
    /* JADX WARN: Code duplicated, block: B:72:0x0180  */
    /* JADX WARN: Code duplicated, block: B:74:0x0184  */
    /* JADX WARN: Code duplicated, block: B:77:0x0198 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:80:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:81:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:83:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:86:0x01b9 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:88:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:90:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:91:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:93:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:96:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:98:0x01e1  */
    /* JADX INFO: renamed from: fastMerge-dSHsh3o, reason: not valid java name */
    public static final SpanStyle m7274fastMergedSHsh3o(SpanStyle $this$fastMerge_u2ddSHsh3o, long j, Brush brush, float alpha, long j2, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String fontFeatureSettings, long j3, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j4, TextDecoration textDecoration, Shadow shadow, PlatformSpanStyle platformStyle, DrawStyle drawStyle) {
        long j5;
        boolean z;
        TextGeometricTransform textGeometricTransform2;
        Shadow shadow2;
        PlatformSpanStyle platformSpanStyle;
        int i;
        boolean requiresAlloc;
        boolean z2;
        TextForegroundStyle otherTextForegroundStyle;
        FontFamily fontFamily2;
        boolean z3;
        long fontSize;
        FontWeight fontWeight2;
        FontStyle fontStyle2;
        FontSynthesis fontSynthesis2;
        String fontFeatureSettings2;
        boolean z4;
        long letterSpacing;
        BaselineShift baselineShift2;
        TextGeometricTransform textGeometricTransform3;
        LocaleList localeList2;
        long background;
        TextDecoration textDecoration2;
        Shadow shadow3;
        DrawStyle drawStyle2;
        if (!(TextUnit.m8097getRawTypeimpl(j2) == 0)) {
            j5 = j2;
            if (!TextUnit.m8096equalsimpl0(j5, $this$fastMerge_u2ddSHsh3o.getFontSize())) {
                textGeometricTransform2 = textGeometricTransform;
                shadow2 = shadow;
                platformSpanStyle = platformStyle;
            }
            requiresAlloc = true;
            if (!requiresAlloc) {
                return $this$fastMerge_u2ddSHsh3o;
            }
            if (brush != null) {
                otherTextForegroundStyle = TextForegroundStyle.INSTANCE.from(brush, alpha);
            } else {
                otherTextForegroundStyle = TextForegroundStyle.INSTANCE.m7785from8_81llA(j);
            }
            TextForegroundStyle textForegroundStyleMerge = $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().merge(otherTextForegroundStyle);
            if (fontFamily == null) {
                fontFamily2 = $this$fastMerge_u2ddSHsh3o.getFontFamily();
            } else {
                fontFamily2 = fontFamily;
            }
            if (TextUnit.m8097getRawTypeimpl(j2) == 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3) {
                fontSize = $this$fastMerge_u2ddSHsh3o.getFontSize();
            } else {
                fontSize = j5;
            }
            if (fontWeight == null) {
                fontWeight2 = $this$fastMerge_u2ddSHsh3o.getFontWeight();
            } else {
                fontWeight2 = fontWeight;
            }
            if (fontStyle == null) {
                fontStyle2 = $this$fastMerge_u2ddSHsh3o.getFontStyle();
            } else {
                fontStyle2 = fontStyle;
            }
            if (fontSynthesis == null) {
                fontSynthesis2 = $this$fastMerge_u2ddSHsh3o.getFontSynthesis();
            } else {
                fontSynthesis2 = fontSynthesis;
            }
            if (fontFeatureSettings == null) {
                fontFeatureSettings2 = $this$fastMerge_u2ddSHsh3o.getFontFeatureSettings();
            } else {
                fontFeatureSettings2 = fontFeatureSettings;
            }
            if (TextUnit.m8097getRawTypeimpl(j3) == 0) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (z4) {
                letterSpacing = $this$fastMerge_u2ddSHsh3o.getLetterSpacing();
            } else {
                letterSpacing = j3;
            }
            if (baselineShift == null) {
                baselineShift2 = $this$fastMerge_u2ddSHsh3o.getBaselineShift();
            } else {
                baselineShift2 = baselineShift;
            }
            if (textGeometricTransform2 == null) {
                textGeometricTransform3 = $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform();
            } else {
                textGeometricTransform3 = textGeometricTransform2;
            }
            if (localeList == null) {
                localeList2 = $this$fastMerge_u2ddSHsh3o.getLocaleList();
            } else {
                localeList2 = localeList;
            }
            if (j4 != 16) {
                background = j4;
            } else {
                background = $this$fastMerge_u2ddSHsh3o.getBackground();
            }
            if (textDecoration == null) {
                textDecoration2 = $this$fastMerge_u2ddSHsh3o.getTextDecoration();
            } else {
                textDecoration2 = textDecoration;
            }
            if (shadow2 == null) {
                shadow3 = $this$fastMerge_u2ddSHsh3o.getShadow();
            } else {
                shadow3 = shadow2;
            }
            PlatformSpanStyle platformSpanStyleMergePlatformStyle = mergePlatformStyle($this$fastMerge_u2ddSHsh3o, platformSpanStyle);
            if (drawStyle == null) {
                drawStyle2 = $this$fastMerge_u2ddSHsh3o.getDrawStyle();
            } else {
                drawStyle2 = drawStyle;
            }
            return new SpanStyle(textForegroundStyleMerge, fontSize, fontWeight2, fontStyle2, fontSynthesis2, fontFamily2, fontFeatureSettings2, letterSpacing, baselineShift2, textGeometricTransform3, localeList2, background, textDecoration2, shadow3, platformSpanStyleMergePlatformStyle, drawStyle2, (DefaultConstructorMarker) null);
        }
        j5 = j2;
        if (brush != null) {
            if (fontStyle == null && !Intrinsics.areEqual(fontStyle, $this$fastMerge_u2ddSHsh3o.getFontStyle())) {
                textGeometricTransform2 = textGeometricTransform;
                shadow2 = shadow;
                platformSpanStyle = platformStyle;
            } else if (fontWeight == null && !Intrinsics.areEqual(fontWeight, $this$fastMerge_u2ddSHsh3o.getFontWeight())) {
                textGeometricTransform2 = textGeometricTransform;
                shadow2 = shadow;
                platformSpanStyle = platformStyle;
            } else if (fontFamily != null || fontFamily == $this$fastMerge_u2ddSHsh3o.getFontFamily()) {
                if (TextUnit.m8097getRawTypeimpl(j3) == 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (z && !TextUnit.m8096equalsimpl0(j3, $this$fastMerge_u2ddSHsh3o.getLetterSpacing())) {
                    textGeometricTransform2 = textGeometricTransform;
                    shadow2 = shadow;
                    platformSpanStyle = platformStyle;
                } else if ((textDecoration == null && !Intrinsics.areEqual(textDecoration, $this$fastMerge_u2ddSHsh3o.getTextDecoration())) || !Intrinsics.areEqual(brush, $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getBrush())) {
                    textGeometricTransform2 = textGeometricTransform;
                    shadow2 = shadow;
                    platformSpanStyle = platformStyle;
                } else if (brush != null) {
                    if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (!z2) {
                        textGeometricTransform2 = textGeometricTransform;
                        shadow2 = shadow;
                        platformSpanStyle = platformStyle;
                    } else if (fontSynthesis == null) {
                        if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (fontFeatureSettings == null) {
                        if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (baselineShift != null) {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    } else {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    }
                } else if (fontSynthesis == null && !Intrinsics.areEqual(fontSynthesis, $this$fastMerge_u2ddSHsh3o.getFontSynthesis())) {
                    textGeometricTransform2 = textGeometricTransform;
                    shadow2 = shadow;
                    platformSpanStyle = platformStyle;
                } else if (fontFeatureSettings == null && !Intrinsics.areEqual(fontFeatureSettings, $this$fastMerge_u2ddSHsh3o.getFontFeatureSettings())) {
                    textGeometricTransform2 = textGeometricTransform;
                    shadow2 = shadow;
                    platformSpanStyle = platformStyle;
                } else if (baselineShift != null || Intrinsics.areEqual(baselineShift, $this$fastMerge_u2ddSHsh3o.getBaselineShift())) {
                    if (textGeometricTransform != null) {
                        textGeometricTransform2 = textGeometricTransform;
                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                            shadow2 = shadow;
                            platformSpanStyle = platformStyle;
                        }
                    } else {
                        textGeometricTransform2 = textGeometricTransform;
                    }
                    if (localeList != null || Intrinsics.areEqual(localeList, $this$fastMerge_u2ddSHsh3o.getLocaleList())) {
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0 || Color.m5098equalsimpl0(j4, $this$fastMerge_u2ddSHsh3o.getBackground())) {
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null || Intrinsics.areEqual(drawStyle, $this$fastMerge_u2ddSHsh3o.getDrawStyle())) {
                                requiresAlloc = false;
                            }
                        } else {
                            shadow2 = shadow;
                            platformSpanStyle = platformStyle;
                        }
                    } else {
                        shadow2 = shadow;
                        platformSpanStyle = platformStyle;
                    }
                } else {
                    textGeometricTransform2 = textGeometricTransform;
                    shadow2 = shadow;
                    platformSpanStyle = platformStyle;
                }
            } else {
                textGeometricTransform2 = textGeometricTransform;
                shadow2 = shadow;
                platformSpanStyle = platformStyle;
            }
            requiresAlloc = true;
        } else {
            if ((j != 16) && !Color.m5098equalsimpl0(j, $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().mo7638getColor0d7_KjU())) {
                textGeometricTransform2 = textGeometricTransform;
                shadow2 = shadow;
                platformSpanStyle = platformStyle;
            } else if (fontStyle == null) {
                if (fontWeight == null) {
                    if (fontFamily != null) {
                        if (TextUnit.m8097getRawTypeimpl(j3) == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                        }
                        if (textDecoration == null) {
                            if (brush != null) {
                                if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                if (!z2) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                } else if (fontSynthesis == null) {
                                    if (fontFeatureSettings == null) {
                                        if (baselineShift != null) {
                                            if (textGeometricTransform != null) {
                                                textGeometricTransform2 = textGeometricTransform;
                                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                    shadow2 = shadow;
                                                    platformSpanStyle = platformStyle;
                                                }
                                            } else {
                                                textGeometricTransform2 = textGeometricTransform;
                                            }
                                            if (localeList != null) {
                                            }
                                            if (j4 != 16) {
                                                i = 1;
                                            } else {
                                                i = 0;
                                            }
                                            if (i != 0) {
                                            }
                                            if (shadow != null) {
                                                shadow2 = shadow;
                                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                    platformSpanStyle = platformStyle;
                                                }
                                            } else {
                                                shadow2 = shadow;
                                            }
                                            if (platformStyle != null) {
                                                platformSpanStyle = platformStyle;
                                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                                }
                                            } else {
                                                platformSpanStyle = platformStyle;
                                            }
                                            if (drawStyle != null) {
                                            }
                                            requiresAlloc = false;
                                        } else {
                                            if (textGeometricTransform != null) {
                                                textGeometricTransform2 = textGeometricTransform;
                                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                    shadow2 = shadow;
                                                    platformSpanStyle = platformStyle;
                                                }
                                            } else {
                                                textGeometricTransform2 = textGeometricTransform;
                                            }
                                            if (localeList != null) {
                                            }
                                            if (j4 != 16) {
                                                i = 1;
                                            } else {
                                                i = 0;
                                            }
                                            if (i != 0) {
                                            }
                                            if (shadow != null) {
                                                shadow2 = shadow;
                                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                    platformSpanStyle = platformStyle;
                                                }
                                            } else {
                                                shadow2 = shadow;
                                            }
                                            if (platformStyle != null) {
                                                platformSpanStyle = platformStyle;
                                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                                }
                                            } else {
                                                platformSpanStyle = platformStyle;
                                            }
                                            if (drawStyle != null) {
                                            }
                                            requiresAlloc = false;
                                        }
                                    } else if (baselineShift != null) {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    } else {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    }
                                } else if (fontFeatureSettings == null) {
                                    if (baselineShift != null) {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    } else {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    }
                                } else if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (fontSynthesis == null) {
                                if (fontFeatureSettings == null) {
                                    if (baselineShift != null) {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    } else {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    }
                                } else if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (brush != null) {
                            if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (!z2) {
                                textGeometricTransform2 = textGeometricTransform;
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            } else if (fontSynthesis == null) {
                                if (fontFeatureSettings == null) {
                                    if (baselineShift != null) {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    } else {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    }
                                } else if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontSynthesis == null) {
                            if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else {
                        if (TextUnit.m8097getRawTypeimpl(j3) == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                        }
                        if (textDecoration == null) {
                            if (brush != null) {
                                if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                if (!z2) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                } else if (fontSynthesis == null) {
                                    if (fontFeatureSettings == null) {
                                        if (baselineShift != null) {
                                            if (textGeometricTransform != null) {
                                                textGeometricTransform2 = textGeometricTransform;
                                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                    shadow2 = shadow;
                                                    platformSpanStyle = platformStyle;
                                                }
                                            } else {
                                                textGeometricTransform2 = textGeometricTransform;
                                            }
                                            if (localeList != null) {
                                            }
                                            if (j4 != 16) {
                                                i = 1;
                                            } else {
                                                i = 0;
                                            }
                                            if (i != 0) {
                                            }
                                            if (shadow != null) {
                                                shadow2 = shadow;
                                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                    platformSpanStyle = platformStyle;
                                                }
                                            } else {
                                                shadow2 = shadow;
                                            }
                                            if (platformStyle != null) {
                                                platformSpanStyle = platformStyle;
                                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                                }
                                            } else {
                                                platformSpanStyle = platformStyle;
                                            }
                                            if (drawStyle != null) {
                                            }
                                            requiresAlloc = false;
                                        } else {
                                            if (textGeometricTransform != null) {
                                                textGeometricTransform2 = textGeometricTransform;
                                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                    shadow2 = shadow;
                                                    platformSpanStyle = platformStyle;
                                                }
                                            } else {
                                                textGeometricTransform2 = textGeometricTransform;
                                            }
                                            if (localeList != null) {
                                            }
                                            if (j4 != 16) {
                                                i = 1;
                                            } else {
                                                i = 0;
                                            }
                                            if (i != 0) {
                                            }
                                            if (shadow != null) {
                                                shadow2 = shadow;
                                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                    platformSpanStyle = platformStyle;
                                                }
                                            } else {
                                                shadow2 = shadow;
                                            }
                                            if (platformStyle != null) {
                                                platformSpanStyle = platformStyle;
                                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                                }
                                            } else {
                                                platformSpanStyle = platformStyle;
                                            }
                                            if (drawStyle != null) {
                                            }
                                            requiresAlloc = false;
                                        }
                                    } else if (baselineShift != null) {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    } else {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    }
                                } else if (fontFeatureSettings == null) {
                                    if (baselineShift != null) {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    } else {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    }
                                } else if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (fontSynthesis == null) {
                                if (fontFeatureSettings == null) {
                                    if (baselineShift != null) {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    } else {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    }
                                } else if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (brush != null) {
                            if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (!z2) {
                                textGeometricTransform2 = textGeometricTransform;
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            } else if (fontSynthesis == null) {
                                if (fontFeatureSettings == null) {
                                    if (baselineShift != null) {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    } else {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    }
                                } else if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontSynthesis == null) {
                            if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    }
                } else if (fontFamily != null) {
                    if (TextUnit.m8097getRawTypeimpl(j3) == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                    }
                    if (textDecoration == null) {
                        if (brush != null) {
                            if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (!z2) {
                                textGeometricTransform2 = textGeometricTransform;
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            } else if (fontSynthesis == null) {
                                if (fontFeatureSettings == null) {
                                    if (baselineShift != null) {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    } else {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    }
                                } else if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontSynthesis == null) {
                            if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (brush != null) {
                        if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (!z2) {
                            textGeometricTransform2 = textGeometricTransform;
                            shadow2 = shadow;
                            platformSpanStyle = platformStyle;
                        } else if (fontSynthesis == null) {
                            if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (fontSynthesis == null) {
                        if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (fontFeatureSettings == null) {
                        if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (baselineShift != null) {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    } else {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    }
                } else {
                    if (TextUnit.m8097getRawTypeimpl(j3) == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                    }
                    if (textDecoration == null) {
                        if (brush != null) {
                            if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (!z2) {
                                textGeometricTransform2 = textGeometricTransform;
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            } else if (fontSynthesis == null) {
                                if (fontFeatureSettings == null) {
                                    if (baselineShift != null) {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    } else {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    }
                                } else if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontSynthesis == null) {
                            if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (brush != null) {
                        if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (!z2) {
                            textGeometricTransform2 = textGeometricTransform;
                            shadow2 = shadow;
                            platformSpanStyle = platformStyle;
                        } else if (fontSynthesis == null) {
                            if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (fontSynthesis == null) {
                        if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (fontFeatureSettings == null) {
                        if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (baselineShift != null) {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    } else {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    }
                }
            } else if (fontWeight == null) {
                if (fontFamily != null) {
                    if (TextUnit.m8097getRawTypeimpl(j3) == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                    }
                    if (textDecoration == null) {
                        if (brush != null) {
                            if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (!z2) {
                                textGeometricTransform2 = textGeometricTransform;
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            } else if (fontSynthesis == null) {
                                if (fontFeatureSettings == null) {
                                    if (baselineShift != null) {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    } else {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    }
                                } else if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontSynthesis == null) {
                            if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (brush != null) {
                        if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (!z2) {
                            textGeometricTransform2 = textGeometricTransform;
                            shadow2 = shadow;
                            platformSpanStyle = platformStyle;
                        } else if (fontSynthesis == null) {
                            if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (fontSynthesis == null) {
                        if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (fontFeatureSettings == null) {
                        if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (baselineShift != null) {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    } else {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    }
                } else {
                    if (TextUnit.m8097getRawTypeimpl(j3) == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                    }
                    if (textDecoration == null) {
                        if (brush != null) {
                            if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (!z2) {
                                textGeometricTransform2 = textGeometricTransform;
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            } else if (fontSynthesis == null) {
                                if (fontFeatureSettings == null) {
                                    if (baselineShift != null) {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    } else {
                                        if (textGeometricTransform != null) {
                                            textGeometricTransform2 = textGeometricTransform;
                                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                                shadow2 = shadow;
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            textGeometricTransform2 = textGeometricTransform;
                                        }
                                        if (localeList != null) {
                                        }
                                        if (j4 != 16) {
                                            i = 1;
                                        } else {
                                            i = 0;
                                        }
                                        if (i != 0) {
                                        }
                                        if (shadow != null) {
                                            shadow2 = shadow;
                                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                                platformSpanStyle = platformStyle;
                                            }
                                        } else {
                                            shadow2 = shadow;
                                        }
                                        if (platformStyle != null) {
                                            platformSpanStyle = platformStyle;
                                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                            }
                                        } else {
                                            platformSpanStyle = platformStyle;
                                        }
                                        if (drawStyle != null) {
                                        }
                                        requiresAlloc = false;
                                    }
                                } else if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontSynthesis == null) {
                            if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (brush != null) {
                        if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (!z2) {
                            textGeometricTransform2 = textGeometricTransform;
                            shadow2 = shadow;
                            platformSpanStyle = platformStyle;
                        } else if (fontSynthesis == null) {
                            if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (fontSynthesis == null) {
                        if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (fontFeatureSettings == null) {
                        if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (baselineShift != null) {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    } else {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    }
                }
            } else if (fontFamily != null) {
                if (TextUnit.m8097getRawTypeimpl(j3) == 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                }
                if (textDecoration == null) {
                    if (brush != null) {
                        if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (!z2) {
                            textGeometricTransform2 = textGeometricTransform;
                            shadow2 = shadow;
                            platformSpanStyle = platformStyle;
                        } else if (fontSynthesis == null) {
                            if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (fontSynthesis == null) {
                        if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (fontFeatureSettings == null) {
                        if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (baselineShift != null) {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    } else {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    }
                } else if (brush != null) {
                    if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (!z2) {
                        textGeometricTransform2 = textGeometricTransform;
                        shadow2 = shadow;
                        platformSpanStyle = platformStyle;
                    } else if (fontSynthesis == null) {
                        if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (fontFeatureSettings == null) {
                        if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (baselineShift != null) {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    } else {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    }
                } else if (fontSynthesis == null) {
                    if (fontFeatureSettings == null) {
                        if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (baselineShift != null) {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    } else {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    }
                } else if (fontFeatureSettings == null) {
                    if (baselineShift != null) {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    } else {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    }
                } else if (baselineShift != null) {
                    if (textGeometricTransform != null) {
                        textGeometricTransform2 = textGeometricTransform;
                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                            shadow2 = shadow;
                            platformSpanStyle = platformStyle;
                        }
                    } else {
                        textGeometricTransform2 = textGeometricTransform;
                    }
                    if (localeList != null) {
                    }
                    if (j4 != 16) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if (i != 0) {
                    }
                    if (shadow != null) {
                        shadow2 = shadow;
                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                            platformSpanStyle = platformStyle;
                        }
                    } else {
                        shadow2 = shadow;
                    }
                    if (platformStyle != null) {
                        platformSpanStyle = platformStyle;
                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                        }
                    } else {
                        platformSpanStyle = platformStyle;
                    }
                    if (drawStyle != null) {
                    }
                    requiresAlloc = false;
                } else {
                    if (textGeometricTransform != null) {
                        textGeometricTransform2 = textGeometricTransform;
                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                            shadow2 = shadow;
                            platformSpanStyle = platformStyle;
                        }
                    } else {
                        textGeometricTransform2 = textGeometricTransform;
                    }
                    if (localeList != null) {
                    }
                    if (j4 != 16) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if (i != 0) {
                    }
                    if (shadow != null) {
                        shadow2 = shadow;
                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                            platformSpanStyle = platformStyle;
                        }
                    } else {
                        shadow2 = shadow;
                    }
                    if (platformStyle != null) {
                        platformSpanStyle = platformStyle;
                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                        }
                    } else {
                        platformSpanStyle = platformStyle;
                    }
                    if (drawStyle != null) {
                    }
                    requiresAlloc = false;
                }
            } else {
                if (TextUnit.m8097getRawTypeimpl(j3) == 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                }
                if (textDecoration == null) {
                    if (brush != null) {
                        if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (!z2) {
                            textGeometricTransform2 = textGeometricTransform;
                            shadow2 = shadow;
                            platformSpanStyle = platformStyle;
                        } else if (fontSynthesis == null) {
                            if (fontFeatureSettings == null) {
                                if (baselineShift != null) {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                } else {
                                    if (textGeometricTransform != null) {
                                        textGeometricTransform2 = textGeometricTransform;
                                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                            shadow2 = shadow;
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        textGeometricTransform2 = textGeometricTransform;
                                    }
                                    if (localeList != null) {
                                    }
                                    if (j4 != 16) {
                                        i = 1;
                                    } else {
                                        i = 0;
                                    }
                                    if (i != 0) {
                                    }
                                    if (shadow != null) {
                                        shadow2 = shadow;
                                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                            platformSpanStyle = platformStyle;
                                        }
                                    } else {
                                        shadow2 = shadow;
                                    }
                                    if (platformStyle != null) {
                                        platformSpanStyle = platformStyle;
                                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                        }
                                    } else {
                                        platformSpanStyle = platformStyle;
                                    }
                                    if (drawStyle != null) {
                                    }
                                    requiresAlloc = false;
                                }
                            } else if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (fontSynthesis == null) {
                        if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (fontFeatureSettings == null) {
                        if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (baselineShift != null) {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    } else {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    }
                } else if (brush != null) {
                    if (alpha == $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().getAlpha()) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (!z2) {
                        textGeometricTransform2 = textGeometricTransform;
                        shadow2 = shadow;
                        platformSpanStyle = platformStyle;
                    } else if (fontSynthesis == null) {
                        if (fontFeatureSettings == null) {
                            if (baselineShift != null) {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            } else {
                                if (textGeometricTransform != null) {
                                    textGeometricTransform2 = textGeometricTransform;
                                    if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                        shadow2 = shadow;
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    textGeometricTransform2 = textGeometricTransform;
                                }
                                if (localeList != null) {
                                }
                                if (j4 != 16) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                if (i != 0) {
                                }
                                if (shadow != null) {
                                    shadow2 = shadow;
                                    if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                        platformSpanStyle = platformStyle;
                                    }
                                } else {
                                    shadow2 = shadow;
                                }
                                if (platformStyle != null) {
                                    platformSpanStyle = platformStyle;
                                    if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                    }
                                } else {
                                    platformSpanStyle = platformStyle;
                                }
                                if (drawStyle != null) {
                                }
                                requiresAlloc = false;
                            }
                        } else if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (fontFeatureSettings == null) {
                        if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (baselineShift != null) {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    } else {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    }
                } else if (fontSynthesis == null) {
                    if (fontFeatureSettings == null) {
                        if (baselineShift != null) {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        } else {
                            if (textGeometricTransform != null) {
                                textGeometricTransform2 = textGeometricTransform;
                                if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                    shadow2 = shadow;
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                textGeometricTransform2 = textGeometricTransform;
                            }
                            if (localeList != null) {
                            }
                            if (j4 != 16) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i != 0) {
                            }
                            if (shadow != null) {
                                shadow2 = shadow;
                                if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                    platformSpanStyle = platformStyle;
                                }
                            } else {
                                shadow2 = shadow;
                            }
                            if (platformStyle != null) {
                                platformSpanStyle = platformStyle;
                                if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                                }
                            } else {
                                platformSpanStyle = platformStyle;
                            }
                            if (drawStyle != null) {
                            }
                            requiresAlloc = false;
                        }
                    } else if (baselineShift != null) {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    } else {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    }
                } else if (fontFeatureSettings == null) {
                    if (baselineShift != null) {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    } else {
                        if (textGeometricTransform != null) {
                            textGeometricTransform2 = textGeometricTransform;
                            if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                                shadow2 = shadow;
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            textGeometricTransform2 = textGeometricTransform;
                        }
                        if (localeList != null) {
                        }
                        if (j4 != 16) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                        }
                        if (shadow != null) {
                            shadow2 = shadow;
                            if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                                platformSpanStyle = platformStyle;
                            }
                        } else {
                            shadow2 = shadow;
                        }
                        if (platformStyle != null) {
                            platformSpanStyle = platformStyle;
                            if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                            }
                        } else {
                            platformSpanStyle = platformStyle;
                        }
                        if (drawStyle != null) {
                        }
                        requiresAlloc = false;
                    }
                } else if (baselineShift != null) {
                    if (textGeometricTransform != null) {
                        textGeometricTransform2 = textGeometricTransform;
                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                            shadow2 = shadow;
                            platformSpanStyle = platformStyle;
                        }
                    } else {
                        textGeometricTransform2 = textGeometricTransform;
                    }
                    if (localeList != null) {
                    }
                    if (j4 != 16) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if (i != 0) {
                    }
                    if (shadow != null) {
                        shadow2 = shadow;
                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                            platformSpanStyle = platformStyle;
                        }
                    } else {
                        shadow2 = shadow;
                    }
                    if (platformStyle != null) {
                        platformSpanStyle = platformStyle;
                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                        }
                    } else {
                        platformSpanStyle = platformStyle;
                    }
                    if (drawStyle != null) {
                    }
                    requiresAlloc = false;
                } else {
                    if (textGeometricTransform != null) {
                        textGeometricTransform2 = textGeometricTransform;
                        if (!Intrinsics.areEqual(textGeometricTransform2, $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform())) {
                            shadow2 = shadow;
                            platformSpanStyle = platformStyle;
                        }
                    } else {
                        textGeometricTransform2 = textGeometricTransform;
                    }
                    if (localeList != null) {
                    }
                    if (j4 != 16) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if (i != 0) {
                    }
                    if (shadow != null) {
                        shadow2 = shadow;
                        if (!Intrinsics.areEqual(shadow2, $this$fastMerge_u2ddSHsh3o.getShadow())) {
                            platformSpanStyle = platformStyle;
                        }
                    } else {
                        shadow2 = shadow;
                    }
                    if (platformStyle != null) {
                        platformSpanStyle = platformStyle;
                        if (Intrinsics.areEqual(platformSpanStyle, $this$fastMerge_u2ddSHsh3o.getPlatformStyle())) {
                        }
                    } else {
                        platformSpanStyle = platformStyle;
                    }
                    if (drawStyle != null) {
                    }
                    requiresAlloc = false;
                }
            }
            requiresAlloc = true;
        }
        if (!requiresAlloc) {
            return $this$fastMerge_u2ddSHsh3o;
        }
        if (brush != null) {
            otherTextForegroundStyle = TextForegroundStyle.INSTANCE.from(brush, alpha);
        } else {
            otherTextForegroundStyle = TextForegroundStyle.INSTANCE.m7785from8_81llA(j);
        }
        TextForegroundStyle textForegroundStyleMerge2 = $this$fastMerge_u2ddSHsh3o.getTextForegroundStyle().merge(otherTextForegroundStyle);
        if (fontFamily == null) {
            fontFamily2 = $this$fastMerge_u2ddSHsh3o.getFontFamily();
        } else {
            fontFamily2 = fontFamily;
        }
        if (TextUnit.m8097getRawTypeimpl(j2) == 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            fontSize = j5;
        } else {
            fontSize = $this$fastMerge_u2ddSHsh3o.getFontSize();
        }
        if (fontWeight == null) {
            fontWeight2 = $this$fastMerge_u2ddSHsh3o.getFontWeight();
        } else {
            fontWeight2 = fontWeight;
        }
        if (fontStyle == null) {
            fontStyle2 = $this$fastMerge_u2ddSHsh3o.getFontStyle();
        } else {
            fontStyle2 = fontStyle;
        }
        if (fontSynthesis == null) {
            fontSynthesis2 = $this$fastMerge_u2ddSHsh3o.getFontSynthesis();
        } else {
            fontSynthesis2 = fontSynthesis;
        }
        if (fontFeatureSettings == null) {
            fontFeatureSettings2 = $this$fastMerge_u2ddSHsh3o.getFontFeatureSettings();
        } else {
            fontFeatureSettings2 = fontFeatureSettings;
        }
        if (TextUnit.m8097getRawTypeimpl(j3) == 0) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4) {
            letterSpacing = j3;
        } else {
            letterSpacing = $this$fastMerge_u2ddSHsh3o.getLetterSpacing();
        }
        if (baselineShift == null) {
            baselineShift2 = $this$fastMerge_u2ddSHsh3o.getBaselineShift();
        } else {
            baselineShift2 = baselineShift;
        }
        if (textGeometricTransform2 == null) {
            textGeometricTransform3 = $this$fastMerge_u2ddSHsh3o.getTextGeometricTransform();
        } else {
            textGeometricTransform3 = textGeometricTransform2;
        }
        if (localeList == null) {
            localeList2 = $this$fastMerge_u2ddSHsh3o.getLocaleList();
        } else {
            localeList2 = localeList;
        }
        if (j4 != 16) {
            background = j4;
        } else {
            background = $this$fastMerge_u2ddSHsh3o.getBackground();
        }
        if (textDecoration == null) {
            textDecoration2 = $this$fastMerge_u2ddSHsh3o.getTextDecoration();
        } else {
            textDecoration2 = textDecoration;
        }
        if (shadow2 == null) {
            shadow3 = $this$fastMerge_u2ddSHsh3o.getShadow();
        } else {
            shadow3 = shadow2;
        }
        PlatformSpanStyle platformSpanStyleMergePlatformStyle2 = mergePlatformStyle($this$fastMerge_u2ddSHsh3o, platformSpanStyle);
        if (drawStyle == null) {
            drawStyle2 = $this$fastMerge_u2ddSHsh3o.getDrawStyle();
        } else {
            drawStyle2 = drawStyle;
        }
        return new SpanStyle(textForegroundStyleMerge2, fontSize, fontWeight2, fontStyle2, fontSynthesis2, fontFamily2, fontFeatureSettings2, letterSpacing, baselineShift2, textGeometricTransform3, localeList2, background, textDecoration2, shadow3, platformSpanStyleMergePlatformStyle2, drawStyle2, (DefaultConstructorMarker) null);
    }

    private static final PlatformSpanStyle mergePlatformStyle(SpanStyle $this$mergePlatformStyle, PlatformSpanStyle other) {
        if ($this$mergePlatformStyle.getPlatformStyle() == null) {
            return other;
        }
        return other == null ? $this$mergePlatformStyle.getPlatformStyle() : $this$mergePlatformStyle.getPlatformStyle().merge(other);
    }
}
