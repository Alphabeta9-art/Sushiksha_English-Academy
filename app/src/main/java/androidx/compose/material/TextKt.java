package androidx.compose.material;

import androidx.compose.foundation.text.BasicTextKt;
import androidx.compose.foundation.text.InlineTextContent;
import androidx.compose.foundation.text.TextAutoSize;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.LinkAnnotation;
import androidx.compose.ui.text.PlatformSpanStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextLinkStyles;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.TextUnit;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Text.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aÏ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\t2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\t2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\u0016\b\u0002\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001e2\b\b\u0002\u0010 \u001a\u00020!H\u0007¢\u0006\u0004\b\"\u0010#\u001aÃ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\t2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\t2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\u0014\b\u0002\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u001e2\b\b\u0002\u0010 \u001a\u00020!H\u0007¢\u0006\u0004\b$\u0010%\u001aã\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020&2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\t2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\t2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\u0014\b\u0002\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020)0(2\u0014\b\u0002\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u001e2\b\b\u0002\u0010 \u001a\u00020!H\u0007¢\u0006\u0004\b*\u0010+\u001aÙ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020&2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\t2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\t2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\u0014\b\u0002\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020)0(2\u0014\b\u0002\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u001e2\b\b\u0002\u0010 \u001a\u00020!H\u0007¢\u0006\u0004\b\"\u0010,\u001a(\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u00020!2\u0011\u00103\u001a\r\u0012\u0004\u0012\u00020\u000104¢\u0006\u0002\b5H\u0007¢\u0006\u0002\u00106\u001a\u0018\u00107\u001a\u00020&2\u0006\u0010\u0002\u001a\u00020&2\u0006\u00108\u001a\u000209H\u0002\u001a\r\u0010:\u001a\u000209H\u0003¢\u0006\u0002\u0010;\"\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020!0.¢\u0006\b\n\u0000\u001a\u0004\b/\u00100¨\u0006<"}, d2 = {"Text", "", "text", "", "modifier", "Landroidx/compose/ui/Modifier;", "color", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_FONT_SIZE, "Landroidx/compose/ui/unit/TextUnit;", TtmlNode.ATTR_TTS_FONT_STYLE, "Landroidx/compose/ui/text/font/FontStyle;", TtmlNode.ATTR_TTS_FONT_WEIGHT, "Landroidx/compose/ui/text/font/FontWeight;", TtmlNode.ATTR_TTS_FONT_FAMILY, "Landroidx/compose/ui/text/font/FontFamily;", "letterSpacing", TtmlNode.ATTR_TTS_TEXT_DECORATION, "Landroidx/compose/ui/text/style/TextDecoration;", TtmlNode.ATTR_TTS_TEXT_ALIGN, "Landroidx/compose/ui/text/style/TextAlign;", "lineHeight", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "minLines", "onTextLayout", "Lkotlin/Function1;", "Landroidx/compose/ui/text/TextLayoutResult;", TtmlNode.TAG_STYLE, "Landroidx/compose/ui/text/TextStyle;", "Text--4IGK_g", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZIILkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "Text-fLXpl1I", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZILkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/ui/text/AnnotatedString;", "inlineContent", "", "Landroidx/compose/foundation/text/InlineTextContent;", "Text-IbK3jfQ", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZIILjava/util/Map;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZILjava/util/Map;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "LocalTextStyle", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalTextStyle", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "ProvideTextStyle", "value", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "createTextWithLinkStyles", "linkStyles", "Landroidx/compose/ui/text/TextLinkStyles;", "rememberTextLinkStyles", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/text/TextLinkStyles;", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextKt {
    private static final ProvidableCompositionLocal<TextStyle> LocalTextStyle = CompositionLocalKt.compositionLocalOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0() { // from class: androidx.compose.material.TextKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return TypographyKt.getDefaultTextStyle();
        }
    });

    static final Unit ProvideTextStyle$lambda$0(TextStyle textStyle, Function2 function2, int i, Composer composer, int i2) {
        ProvideTextStyle(textStyle, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Text_IbK3jfQ$lambda$3(AnnotatedString annotatedString, Modifier modifier, long j, long j2, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long j3, TextDecoration textDecoration, TextAlign textAlign, long j4, int i, boolean z, int i2, int i3, Map map, Function1 function1, TextStyle textStyle, int i4, int i5, int i6, Composer composer, int i7) {
        m2014TextIbK3jfQ(annotatedString, modifier, j, j2, fontStyle, fontWeight, fontFamily, j3, textDecoration, textAlign, j4, i, z, i2, i3, map, function1, textStyle, composer, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
        return Unit.INSTANCE;
    }

    static final Unit Text__4IGK_g$lambda$1(String str, Modifier modifier, long j, long j2, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long j3, TextDecoration textDecoration, TextAlign textAlign, long j4, int i, boolean z, int i2, int i3, Function1 function1, TextStyle textStyle, int i4, int i5, int i6, Composer composer, int i7) {
        m2013Text4IGK_g(str, modifier, j, j2, fontStyle, fontWeight, fontFamily, j3, textDecoration, textAlign, j4, i, z, i2, i3, (Function1<? super TextLayoutResult, Unit>) function1, textStyle, composer, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
        return Unit.INSTANCE;
    }

    static final Unit Text__4IGK_g$lambda$3(AnnotatedString annotatedString, Modifier modifier, long j, long j2, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long j3, TextDecoration textDecoration, TextAlign textAlign, long j4, int i, boolean z, int i2, Map map, Function1 function1, TextStyle textStyle, int i3, int i4, int i5, Composer composer, int i6) {
        m2012Text4IGK_g(annotatedString, modifier, j, j2, fontStyle, fontWeight, fontFamily, j3, textDecoration, textAlign, j4, i, z, i2, map, function1, textStyle, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
        return Unit.INSTANCE;
    }

    static final Unit Text_fLXpl1I$lambda$1(String str, Modifier modifier, long j, long j2, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long j3, TextDecoration textDecoration, TextAlign textAlign, long j4, int i, boolean z, int i2, Function1 function1, TextStyle textStyle, int i3, int i4, int i5, Composer composer, int i6) {
        m2015TextfLXpl1I(str, modifier, j, j2, fontStyle, fontWeight, fontFamily, j3, textDecoration, textAlign, j4, i, z, i2, function1, textStyle, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:272:0x04e5  */
    /* JADX INFO: renamed from: Text--4IGK_g, reason: not valid java name */
    public static final void m2013Text4IGK_g(final String text, Modifier modifier, long j, long j2, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long j3, TextDecoration textDecoration, TextAlign textAlign, long j4, int i, boolean softWrap, int maxLines, int minLines, Function1<? super TextLayoutResult, Unit> function1, TextStyle style, Composer $composer, final int $changed, final int $changed1, final int i2) {
        String str;
        Modifier modifier2;
        long jM5133getUnspecified0d7_KjU;
        long jM8110getUnspecifiedXSAIIZE;
        final FontStyle fontStyle2;
        FontWeight fontWeight2;
        FontFamily fontFamily2;
        int $dirty;
        int $dirty1;
        int $dirty2;
        Composer $composer2;
        final TextAlign textAlign2;
        final boolean softWrap2;
        final int maxLines2;
        final int minLines2;
        final Function1<? super TextLayoutResult, Unit> function2;
        final TextStyle style2;
        final long j5;
        final long j6;
        final Modifier modifier3;
        final FontWeight fontWeight3;
        final FontFamily fontFamily3;
        final long j7;
        final TextDecoration textDecoration2;
        final long j8;
        final int $dirty3;
        int iM7815getClipgIe3tQ8;
        boolean softWrap3;
        Function1<? super TextLayoutResult, Unit> function3;
        int minLines3;
        TextDecoration textDecoration3;
        FontStyle fontStyle3;
        FontWeight fontWeight4;
        FontFamily fontFamily4;
        long j9;
        long j10;
        TextStyle style3;
        long j11;
        final long overrideColorOrUnspecified;
        int minLines4;
        Composer $composer3 = $composer.startRestartGroup(1028090691);
        ComposerKt.sourceInformation($composer3, "C(Text)N(text,modifier,color:c#ui.graphics.Color,fontSize:c#ui.unit.TextUnit,fontStyle:c#ui.text.font.FontStyle,fontWeight,fontFamily,letterSpacing:c#ui.unit.TextUnit,textDecoration,textAlign:c#ui.text.style.TextAlign,lineHeight:c#ui.unit.TextUnit,overflow:c#ui.text.style.TextOverflow,softWrap,maxLines,minLines,onTextLayout,style)133@7187L7,134@7241L7,163@8145L30,144@7500L682:Text.kt#jmzs0o");
        int $dirty4 = $changed;
        int $dirty5 = $changed1;
        if (($changed & 6) == 0) {
            str = text;
            $dirty4 |= $composer3.changed(str) ? 4 : 2;
        } else {
            str = text;
        }
        int i3 = i2 & 2;
        if (i3 != 0) {
            $dirty4 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i2 & 4;
        if (i4 != 0) {
            $dirty4 |= 384;
            jM5133getUnspecified0d7_KjU = j;
        } else if (($changed & 384) == 0) {
            jM5133getUnspecified0d7_KjU = j;
            $dirty4 |= $composer3.changed(jM5133getUnspecified0d7_KjU) ? 256 : 128;
        } else {
            jM5133getUnspecified0d7_KjU = j;
        }
        int i5 = i2 & 8;
        if (i5 != 0) {
            $dirty4 |= 3072;
            jM8110getUnspecifiedXSAIIZE = j2;
        } else if (($changed & 3072) == 0) {
            jM8110getUnspecifiedXSAIIZE = j2;
            $dirty4 |= $composer3.changed(jM8110getUnspecifiedXSAIIZE) ? 2048 : 1024;
        } else {
            jM8110getUnspecifiedXSAIIZE = j2;
        }
        int i6 = i2 & 16;
        if (i6 != 0) {
            $dirty4 |= 24576;
            fontStyle2 = fontStyle;
        } else if (($changed & 24576) == 0) {
            fontStyle2 = fontStyle;
            $dirty4 |= $composer3.changed(fontStyle2) ? 16384 : 8192;
        } else {
            fontStyle2 = fontStyle;
        }
        int i7 = i2 & 32;
        if (i7 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            fontWeight2 = fontWeight;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            fontWeight2 = fontWeight;
            $dirty4 |= $composer3.changed(fontWeight2) ? 131072 : 65536;
        } else {
            fontWeight2 = fontWeight;
        }
        int i8 = i2 & 64;
        if (i8 != 0) {
            $dirty4 |= 1572864;
            fontFamily2 = fontFamily;
        } else if (($changed & 1572864) == 0) {
            fontFamily2 = fontFamily;
            $dirty4 |= $composer3.changed(fontFamily2) ? 1048576 : 524288;
        } else {
            fontFamily2 = fontFamily;
        }
        int $dirty6 = i2 & 128;
        if ($dirty6 != 0) {
            $dirty = $dirty4 | 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty = $dirty4 | ($composer3.changed(j3) ? 8388608 : 4194304);
        } else {
            $dirty = $dirty4;
        }
        int $dirty7 = i2 & 256;
        if ($dirty7 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(textDecoration) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty8 = i2 & 512;
        if ($dirty8 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changed(textAlign) ? 536870912 : 268435456;
        }
        int $dirty9 = $dirty;
        int $dirty10 = i2 & 1024;
        if ($dirty10 != 0) {
            $dirty5 |= 6;
        } else {
            int $dirty11 = $changed1 & 6;
            if ($dirty11 == 0) {
                $dirty5 |= $composer3.changed(j4) ? 4 : 2;
            }
        }
        int $dirty12 = i2 & 2048;
        if ($dirty12 != 0) {
            $dirty5 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty5 |= $composer3.changed(i) ? 32 : 16;
        }
        int $dirty13 = $dirty5;
        int i9 = i2 & 4096;
        if (i9 != 0) {
            $dirty1 = $dirty13 | 384;
        } else {
            int $dirty14 = $dirty13;
            int $dirty15 = $changed1 & 384;
            if ($dirty15 == 0) {
                $dirty14 |= $composer3.changed(softWrap) ? 256 : 128;
            }
            $dirty1 = $dirty14;
        }
        int i10 = i2 & 8192;
        if (i10 != 0) {
            $dirty2 = $dirty1 | 3072;
        } else {
            int $dirty16 = $dirty1;
            int $dirty17 = $changed1 & 3072;
            if ($dirty17 == 0) {
                $dirty2 = $dirty16 | ($composer3.changed(maxLines) ? 2048 : 1024);
            } else {
                $dirty2 = $dirty16;
            }
        }
        int i11 = i2 & 16384;
        if (i11 != 0) {
            $dirty2 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty2 |= $composer3.changed(minLines) ? 16384 : 8192;
        }
        int i12 = i2 & 32768;
        if (i12 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer3.changedInstance(function1) ? 131072 : 65536;
        }
        if (($changed1 & 1572864) == 0) {
            $dirty2 |= ((i2 & 65536) == 0 && $composer3.changed(style)) ? 1048576 : 524288;
        }
        if ($composer3.shouldExecute((($dirty9 & 306783379) == 306783378 && (599187 & $dirty2) == 599186) ? false : true, $dirty9 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "114@5974L7");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    jM5133getUnspecified0d7_KjU = Color.INSTANCE.m5133getUnspecified0d7_KjU();
                }
                if (i5 != 0) {
                    jM8110getUnspecifiedXSAIIZE = TextUnit.INSTANCE.m8110getUnspecifiedXSAIIZE();
                }
                if (i6 != 0) {
                    fontStyle2 = null;
                }
                if (i7 != 0) {
                    fontWeight2 = null;
                }
                if (i8 != 0) {
                    fontFamily2 = null;
                }
                long jM8110getUnspecifiedXSAIIZE2 = $dirty6 != 0 ? TextUnit.INSTANCE.m8110getUnspecifiedXSAIIZE() : j3;
                TextDecoration textDecoration4 = $dirty7 != 0 ? null : textDecoration;
                textAlign2 = $dirty8 != 0 ? null : textAlign;
                long jM8110getUnspecifiedXSAIIZE3 = $dirty10 != 0 ? TextUnit.INSTANCE.m8110getUnspecifiedXSAIIZE() : j4;
                iM7815getClipgIe3tQ8 = $dirty12 != 0 ? TextOverflow.INSTANCE.m7815getClipgIe3tQ8() : i;
                softWrap3 = i9 != 0 ? true : softWrap;
                maxLines2 = i10 != 0 ? Integer.MAX_VALUE : maxLines;
                int minLines5 = i11 != 0 ? 1 : minLines;
                function3 = i12 != 0 ? null : function1;
                if ((i2 & 65536) != 0) {
                    ProvidableCompositionLocal<TextStyle> providableCompositionLocal = LocalTextStyle;
                    TextDecoration textDecoration5 = textDecoration4;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(providableCompositionLocal);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty2 &= -3670017;
                    textDecoration3 = textDecoration5;
                    fontStyle3 = fontStyle2;
                    j11 = jM8110getUnspecifiedXSAIIZE;
                    fontWeight4 = fontWeight2;
                    fontFamily4 = fontFamily2;
                    j9 = jM8110getUnspecifiedXSAIIZE2;
                    j10 = jM8110getUnspecifiedXSAIIZE3;
                    style3 = (TextStyle) objConsume;
                    minLines3 = minLines5;
                } else {
                    TextDecoration textDecoration6 = textDecoration4;
                    minLines3 = minLines5;
                    textDecoration3 = textDecoration6;
                    fontStyle3 = fontStyle2;
                    fontWeight4 = fontWeight2;
                    fontFamily4 = fontFamily2;
                    j9 = jM8110getUnspecifiedXSAIIZE2;
                    j10 = jM8110getUnspecifiedXSAIIZE3;
                    style3 = style;
                    j11 = jM8110getUnspecifiedXSAIIZE;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i2 & 65536) != 0) {
                    $dirty2 &= -3670017;
                }
                j9 = j3;
                textDecoration3 = textDecoration;
                textAlign2 = textAlign;
                j10 = j4;
                iM7815getClipgIe3tQ8 = i;
                softWrap3 = softWrap;
                maxLines2 = maxLines;
                minLines3 = minLines;
                function3 = function1;
                style3 = style;
                fontStyle3 = fontStyle2;
                j11 = jM8110getUnspecifiedXSAIIZE;
                fontWeight4 = fontWeight2;
                fontFamily4 = fontFamily2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1028090691, $dirty9, $dirty2, "androidx.compose.material.Text (Text.kt:115)");
            }
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            long localContentColor2 = ((Color) objConsume2).m5107unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer3.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            float localContentAlpha2 = ((Number) objConsume3).floatValue();
            long $this$isSpecified$iv = jM5133getUnspecified0d7_KjU;
            if (($this$isSpecified$iv != 16 ? 1 : 0) != 0) {
                overrideColorOrUnspecified = jM5133getUnspecified0d7_KjU;
            } else {
                long $this$isSpecified$iv2 = style3.m7358getColor0d7_KjU();
                overrideColorOrUnspecified = $this$isSpecified$iv2 != 16 ? style3.m7358getColor0d7_KjU() : Color.m5095copywmQWz5c(localContentColor2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(localContentColor2) : localContentAlpha2, (14 & 2) != 0 ? Color.m5103getRedimpl(localContentColor2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(localContentColor2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(localContentColor2) : 0.0f);
            }
            TextStyle textStyleM7348mergedA7vx0o$default = TextStyle.m7348mergedA7vx0o$default(style3, 0L, j11, fontWeight4, fontStyle3, null, fontFamily4, null, j9, null, null, null, 0L, textDecoration3, null, null, textAlign2 != null ? textAlign2.m7757unboximpl() : TextAlign.INSTANCE.m7764getUnspecifiede0LSkKk(), 0, j10, null, null, 0, 0, null, null, 16609105, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 152766689, "CC(remember):Text.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(overrideColorOrUnspecified);
            ColorProducer value$iv = $composer3.rememberedValue();
            if (invalid$iv) {
                minLines4 = minLines3;
            } else {
                minLines4 = minLines3;
                if (value$iv == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                BasicTextKt.m1156BasicTextRWo7tUw(str, modifier2, textStyleM7348mergedA7vx0o$default, function3, iM7815getClipgIe3tQ8, softWrap3, maxLines2, minLines4, (ColorProducer) value$iv, (TextAutoSize) null, $composer3, ($dirty9 & 14) | ($dirty9 & 112) | (($dirty2 >> 6) & 7168) | (($dirty2 << 9) & 57344) | (($dirty2 << 9) & 458752) | (($dirty2 << 9) & 3670016) | (($dirty2 << 9) & 29360128), 512);
                int minLines6 = minLines4;
                $composer2 = $composer3;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier2;
                $dirty3 = iM7815getClipgIe3tQ8;
                softWrap2 = softWrap3;
                style2 = style3;
                j6 = j11;
                fontStyle2 = fontStyle3;
                j7 = j9;
                textDecoration2 = textDecoration3;
                j8 = j10;
                j5 = jM5133getUnspecified0d7_KjU;
                function2 = function3;
                fontWeight3 = fontWeight4;
                fontFamily3 = fontFamily4;
                minLines2 = minLines6;
            }
            value$iv = new ColorProducer() { // from class: androidx.compose.material.TextKt$Text$1$1
                @Override // androidx.compose.ui.graphics.ColorProducer
                /* JADX INFO: renamed from: invoke-0d7_KjU */
                public final long mo1797invoke0d7_KjU() {
                    return overrideColorOrUnspecified;
                }
            };
            $composer3.updateRememberedValue(value$iv);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            BasicTextKt.m1156BasicTextRWo7tUw(str, modifier2, textStyleM7348mergedA7vx0o$default, function3, iM7815getClipgIe3tQ8, softWrap3, maxLines2, minLines4, (ColorProducer) value$iv, (TextAutoSize) null, $composer3, ($dirty9 & 14) | ($dirty9 & 112) | (($dirty2 >> 6) & 7168) | (($dirty2 << 9) & 57344) | (($dirty2 << 9) & 458752) | (($dirty2 << 9) & 3670016) | (($dirty2 << 9) & 29360128), 512);
            int minLines7 = minLines4;
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            $dirty3 = iM7815getClipgIe3tQ8;
            softWrap2 = softWrap3;
            style2 = style3;
            j6 = j11;
            fontStyle2 = fontStyle3;
            j7 = j9;
            textDecoration2 = textDecoration3;
            j8 = j10;
            j5 = jM5133getUnspecified0d7_KjU;
            function2 = function3;
            fontWeight3 = fontWeight4;
            fontFamily3 = fontFamily4;
            minLines2 = minLines7;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            textAlign2 = textAlign;
            softWrap2 = softWrap;
            maxLines2 = maxLines;
            minLines2 = minLines;
            function2 = function1;
            style2 = style;
            j5 = jM5133getUnspecified0d7_KjU;
            j6 = jM8110getUnspecifiedXSAIIZE;
            modifier3 = modifier2;
            fontWeight3 = fontWeight2;
            fontFamily3 = fontFamily2;
            j7 = j3;
            textDecoration2 = textDecoration;
            j8 = j4;
            $dirty3 = i;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextKt.Text__4IGK_g$lambda$1(text, modifier3, j5, j6, fontStyle2, fontWeight3, fontFamily3, j7, textDecoration2, textAlign2, j8, $dirty3, softWrap2, maxLines2, minLines2, function2, style2, $changed, $changed1, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Text_fLXpl1I$lambda$0$0(TextLayoutResult it) {
        return Unit.INSTANCE;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: PrepareForCodeGen
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r6v0 int
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.dex.instructions.args.RegisterArg.sameCodeVar(RegisterArg.java:193)
        	at jadx.core.dex.visitors.PrepareForCodeGen.modifyArith(PrepareForCodeGen.java:242)
        	at jadx.core.dex.visitors.PrepareForCodeGen.visit(PrepareForCodeGen.java:88)
        */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with minLines instead")
    /* JADX INFO: renamed from: Text-fLXpl1I, reason: not valid java name */
    public static final /* synthetic */ void m2015TextfLXpl1I(final java.lang.String r42, androidx.compose.ui.Modifier r43, long r44, long r46, androidx.compose.ui.text.font.FontStyle r48, androidx.compose.ui.text.font.FontWeight r49, androidx.compose.ui.text.font.FontFamily r50, long r51, androidx.compose.ui.text.style.TextDecoration r53, androidx.compose.ui.text.style.TextAlign r54, long r55, int r57, boolean r58, int r59, kotlin.jvm.functions.Function1 r60, androidx.compose.ui.text.TextStyle r61, androidx.compose.runtime.Composer r62, final int r63, final int r64, final int r65) {
        /*
            Method dump skipped, instruction units count: 1124
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextKt.m2015TextfLXpl1I(java.lang.String, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Text_IbK3jfQ$lambda$0$0(TextLayoutResult it) {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:290:0x04e2  */
    /* JADX WARN: Code duplicated, block: B:291:0x04e7  */
    /* JADX WARN: Code duplicated, block: B:297:0x054e  */
    /* JADX WARN: Code duplicated, block: B:300:0x05ba  */
    /* JADX INFO: renamed from: Text-IbK3jfQ, reason: not valid java name */
    public static final void m2014TextIbK3jfQ(final AnnotatedString text, Modifier modifier, long j, long j2, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long j3, TextDecoration textDecoration, TextAlign textAlign, long j4, int i, boolean softWrap, int maxLines, int minLines, Map<String, InlineTextContent> map, Function1<? super TextLayoutResult, Unit> function1, TextStyle style, Composer $composer, final int $changed, final int $changed1, final int i2) {
        Modifier modifier2;
        long jM5133getUnspecified0d7_KjU;
        long jM8110getUnspecifiedXSAIIZE;
        FontStyle fontStyle2;
        FontWeight fontWeight2;
        int $dirty;
        int $dirty1;
        int $dirty2;
        Composer $composer2;
        final TextAlign textAlign2;
        final int i3;
        final boolean softWrap2;
        final int maxLines2;
        final int minLines2;
        final Function1<? super TextLayoutResult, Unit> function2;
        final TextStyle style2;
        final FontStyle fontStyle3;
        final FontWeight fontWeight3;
        final long j5;
        final Modifier modifier3;
        final long j6;
        final FontFamily fontFamily2;
        final long j7;
        final TextDecoration textDecoration2;
        final long j8;
        final Map<String, InlineTextContent> map2;
        TextAlign textAlign3;
        int iM7815getClipgIe3tQ8;
        boolean softWrap3;
        int maxLines3;
        Map<String, InlineTextContent> mapEmptyMap;
        Function1<? super TextLayoutResult, Unit> function3;
        FontFamily fontFamily3;
        TextDecoration textDecoration3;
        Function1<? super TextLayoutResult, Unit> function4;
        int minLines3;
        TextStyle style3;
        int $dirty3;
        FontStyle fontStyle4;
        FontWeight fontWeight4;
        long j9;
        long j10;
        long j11;
        final long overrideColorOrUnspecified;
        int $dirty4;
        int iM7764getUnspecifiede0LSkKk;
        boolean invalid$iv;
        ColorProducer value$iv;
        Composer $composer3 = $composer.startRestartGroup(744129681);
        ComposerKt.sourceInformation($composer3, "C(Text)N(text,modifier,color:c#ui.graphics.Color,fontSize:c#ui.unit.TextUnit,fontStyle:c#ui.text.font.FontStyle,fontWeight,fontFamily,letterSpacing:c#ui.unit.TextUnit,textDecoration,textAlign:c#ui.text.style.TextAlign,lineHeight:c#ui.unit.TextUnit,overflow:c#ui.text.style.TextOverflow,softWrap,maxLines,minLines,inlineContent,onTextLayout,style)306@15065L7,307@15119L7,317@15388L24,319@15458L73,341@16243L30,321@15537L743:Text.kt#jmzs0o");
        int $dirty5 = $changed;
        int $dirty6 = $changed1;
        if (($changed & 6) == 0) {
            $dirty5 |= $composer3.changed(text) ? 4 : 2;
        }
        int i4 = i2 & 2;
        if (i4 != 0) {
            $dirty5 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i5 = i2 & 4;
        if (i5 != 0) {
            $dirty5 |= 384;
            jM5133getUnspecified0d7_KjU = j;
        } else if (($changed & 384) == 0) {
            jM5133getUnspecified0d7_KjU = j;
            $dirty5 |= $composer3.changed(jM5133getUnspecified0d7_KjU) ? 256 : 128;
        } else {
            jM5133getUnspecified0d7_KjU = j;
        }
        int i6 = i2 & 8;
        if (i6 != 0) {
            $dirty5 |= 3072;
            jM8110getUnspecifiedXSAIIZE = j2;
        } else if (($changed & 3072) == 0) {
            jM8110getUnspecifiedXSAIIZE = j2;
            $dirty5 |= $composer3.changed(jM8110getUnspecifiedXSAIIZE) ? 2048 : 1024;
        } else {
            jM8110getUnspecifiedXSAIIZE = j2;
        }
        int i7 = i2 & 16;
        if (i7 != 0) {
            $dirty5 |= 24576;
            fontStyle2 = fontStyle;
        } else if (($changed & 24576) == 0) {
            fontStyle2 = fontStyle;
            $dirty5 |= $composer3.changed(fontStyle2) ? 16384 : 8192;
        } else {
            fontStyle2 = fontStyle;
        }
        int i8 = i2 & 32;
        if (i8 != 0) {
            $dirty5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            fontWeight2 = fontWeight;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            fontWeight2 = fontWeight;
            $dirty5 |= $composer3.changed(fontWeight2) ? 131072 : 65536;
        } else {
            fontWeight2 = fontWeight;
        }
        int i9 = i2 & 64;
        if (i9 != 0) {
            $dirty5 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty5 |= $composer3.changed(fontFamily) ? 1048576 : 524288;
        }
        int $dirty7 = i2 & 128;
        if ($dirty7 != 0) {
            $dirty = $dirty5 | 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty = $dirty5 | ($composer3.changed(j3) ? 8388608 : 4194304);
        } else {
            $dirty = $dirty5;
        }
        int $dirty8 = i2 & 256;
        if ($dirty8 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(textDecoration) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty9 = i2 & 512;
        if ($dirty9 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changed(textAlign) ? 536870912 : 268435456;
        }
        int $dirty10 = $dirty;
        int i10 = i2 & 1024;
        if (i10 != 0) {
            $dirty6 |= 6;
        } else {
            int $dirty11 = $changed1 & 6;
            if ($dirty11 == 0) {
                $dirty6 |= $composer3.changed(j4) ? 4 : 2;
            }
        }
        int i11 = i2 & 2048;
        if (i11 != 0) {
            $dirty6 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty6 |= $composer3.changed(i) ? 32 : 16;
        }
        int $dirty12 = $dirty6;
        int i12 = i2 & 4096;
        if (i12 != 0) {
            $dirty1 = $dirty12 | 384;
        } else {
            int $dirty13 = $dirty12;
            int $dirty14 = $changed1 & 384;
            if ($dirty14 == 0) {
                $dirty13 |= $composer3.changed(softWrap) ? 256 : 128;
            }
            $dirty1 = $dirty13;
        }
        int i13 = i2 & 8192;
        if (i13 != 0) {
            $dirty2 = $dirty1 | 3072;
        } else {
            int $dirty15 = $dirty1;
            int $dirty16 = $changed1 & 3072;
            if ($dirty16 == 0) {
                $dirty2 = $dirty15 | ($composer3.changed(maxLines) ? 2048 : 1024);
            } else {
                $dirty2 = $dirty15;
            }
        }
        int i14 = i2 & 16384;
        if (i14 != 0) {
            $dirty2 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty2 |= $composer3.changed(minLines) ? 16384 : 8192;
        }
        int i15 = i2 & 32768;
        if (i15 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer3.changedInstance(map) ? 131072 : 65536;
        }
        int i16 = i2 & 65536;
        if (i16 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(function1) ? 1048576 : 524288;
        }
        if (($changed1 & 12582912) == 0) {
            $dirty2 |= ((i2 & 131072) == 0 && $composer3.changed(style)) ? 8388608 : 4194304;
        }
        int $dirty17 = $dirty2;
        if ($composer3.shouldExecute((($dirty10 & 306783379) == 306783378 && ($dirty17 & 4793491) == 4793490) ? false : true, $dirty10 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "286@13810L2,287@13852L7");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i5 != 0) {
                    jM5133getUnspecified0d7_KjU = Color.INSTANCE.m5133getUnspecified0d7_KjU();
                }
                if (i6 != 0) {
                    jM8110getUnspecifiedXSAIIZE = TextUnit.INSTANCE.m8110getUnspecifiedXSAIIZE();
                }
                if (i7 != 0) {
                    fontStyle2 = null;
                }
                if (i8 != 0) {
                    fontWeight2 = null;
                }
                FontFamily fontFamily4 = i9 != 0 ? null : fontFamily;
                long jM8110getUnspecifiedXSAIIZE2 = $dirty7 != 0 ? TextUnit.INSTANCE.m8110getUnspecifiedXSAIIZE() : j3;
                TextDecoration textDecoration4 = $dirty8 != 0 ? null : textDecoration;
                textAlign3 = $dirty9 != 0 ? null : textAlign;
                long jM8110getUnspecifiedXSAIIZE3 = i10 != 0 ? TextUnit.INSTANCE.m8110getUnspecifiedXSAIIZE() : j4;
                iM7815getClipgIe3tQ8 = i11 != 0 ? TextOverflow.INSTANCE.m7815getClipgIe3tQ8() : i;
                softWrap3 = i12 != 0 ? true : softWrap;
                maxLines3 = i13 != 0 ? Integer.MAX_VALUE : maxLines;
                int minLines4 = i14 != 0 ? 1 : minLines;
                mapEmptyMap = i15 != 0 ? MapsKt.emptyMap() : map;
                if (i16 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 2144074259, "CC(remember):Text.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = new Function1() { // from class: androidx.compose.material.TextKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return TextKt.Text_IbK3jfQ$lambda$0$0((TextLayoutResult) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                        it$iv = value$iv2;
                    }
                    function3 = (Function1) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    function3 = function1;
                }
                if ((i2 & 131072) != 0) {
                    ProvidableCompositionLocal<TextStyle> providableCompositionLocal = LocalTextStyle;
                    Function1<? super TextLayoutResult, Unit> function5 = function3;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(providableCompositionLocal);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty3 = $dirty17 & (-29360129);
                    fontFamily3 = fontFamily4;
                    textDecoration3 = textDecoration4;
                    function4 = function5;
                    style3 = (TextStyle) objConsume;
                    fontStyle4 = fontStyle2;
                    fontWeight4 = fontWeight2;
                    j9 = jM8110getUnspecifiedXSAIIZE;
                    j10 = jM8110getUnspecifiedXSAIIZE2;
                    j11 = jM8110getUnspecifiedXSAIIZE3;
                    minLines3 = minLines4;
                } else {
                    fontFamily3 = fontFamily4;
                    textDecoration3 = textDecoration4;
                    function4 = function3;
                    minLines3 = minLines4;
                    style3 = style;
                    $dirty3 = $dirty17;
                    fontStyle4 = fontStyle2;
                    fontWeight4 = fontWeight2;
                    j9 = jM8110getUnspecifiedXSAIIZE;
                    j10 = jM8110getUnspecifiedXSAIIZE2;
                    j11 = jM8110getUnspecifiedXSAIIZE3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i2 & 131072) != 0) {
                    fontFamily3 = fontFamily;
                    j10 = j3;
                    textDecoration3 = textDecoration;
                    textAlign3 = textAlign;
                    j11 = j4;
                    iM7815getClipgIe3tQ8 = i;
                    softWrap3 = softWrap;
                    maxLines3 = maxLines;
                    mapEmptyMap = map;
                    function4 = function1;
                    style3 = style;
                    $dirty3 = $dirty17 & (-29360129);
                    fontStyle4 = fontStyle2;
                    fontWeight4 = fontWeight2;
                    j9 = jM8110getUnspecifiedXSAIIZE;
                    minLines3 = minLines;
                } else {
                    fontFamily3 = fontFamily;
                    j10 = j3;
                    textDecoration3 = textDecoration;
                    textAlign3 = textAlign;
                    j11 = j4;
                    iM7815getClipgIe3tQ8 = i;
                    softWrap3 = softWrap;
                    maxLines3 = maxLines;
                    minLines3 = minLines;
                    mapEmptyMap = map;
                    function4 = function1;
                    style3 = style;
                    $dirty3 = $dirty17;
                    fontStyle4 = fontStyle2;
                    fontWeight4 = fontWeight2;
                    j9 = jM8110getUnspecifiedXSAIIZE;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(744129681, $dirty10, $dirty3, "androidx.compose.material.Text (Text.kt:288)");
            }
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            long localContentColor2 = ((Color) objConsume2).m5107unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            int minLines5 = minLines3;
            Function1<? super TextLayoutResult, Unit> function6 = function4;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer3.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            float localContentAlpha2 = ((Number) objConsume3).floatValue();
            long $this$isSpecified$iv = jM5133getUnspecified0d7_KjU;
            if ($this$isSpecified$iv != 16) {
                overrideColorOrUnspecified = jM5133getUnspecified0d7_KjU;
            } else {
                long $this$isSpecified$iv2 = style3.m7358getColor0d7_KjU();
                overrideColorOrUnspecified = ($this$isSpecified$iv2 > 16L ? 1 : ($this$isSpecified$iv2 == 16L ? 0 : -1)) != 0 ? style3.m7358getColor0d7_KjU() : Color.m5095copywmQWz5c(localContentColor2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(localContentColor2) : localContentAlpha2, (14 & 2) != 0 ? Color.m5103getRedimpl(localContentColor2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(localContentColor2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(localContentColor2) : 0.0f);
            }
            TextLinkStyles linkStyles = rememberTextLinkStyles($composer3, 0);
            ComposerKt.sourceInformationMarkerStart($composer3, 2144127066, "CC(remember):Text.kt#9igjgp");
            boolean invalid$iv2 = (($dirty10 & 14) == 4) | $composer3.changed(linkStyles);
            Object it$iv2 = $composer3.rememberedValue();
            if (invalid$iv2) {
                $dirty4 = $dirty3;
            } else {
                $dirty4 = $dirty3;
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                }
                AnnotatedString textWithMaterialLinkStyles = (AnnotatedString) it$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (textAlign3 != null) {
                    iM7764getUnspecifiede0LSkKk = textAlign3.m7757unboximpl();
                } else {
                    iM7764getUnspecifiede0LSkKk = TextAlign.INSTANCE.m7764getUnspecifiede0LSkKk();
                }
                TextStyle textStyleM7348mergedA7vx0o$default = TextStyle.m7348mergedA7vx0o$default(style3, 0L, j9, fontWeight4, fontStyle4, null, fontFamily3, null, j10, null, null, null, 0L, textDecoration3, null, null, iM7764getUnspecifiede0LSkKk, 0, j11, null, null, 0, 0, null, null, 16609105, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 2144152143, "CC(remember):Text.kt#9igjgp");
                invalid$iv = $composer3.changed(overrideColorOrUnspecified);
                value$iv = $composer3.rememberedValue();
                if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = new ColorProducer() { // from class: androidx.compose.material.TextKt$Text$6$1
                        @Override // androidx.compose.ui.graphics.ColorProducer
                        /* JADX INFO: renamed from: invoke-0d7_KjU */
                        public final long mo1797invoke0d7_KjU() {
                            return overrideColorOrUnspecified;
                        }
                    };
                    $composer3.updateRememberedValue(value$iv);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                BasicTextKt.m1154BasicTextCL7eQgs(textWithMaterialLinkStyles, modifier2, textStyleM7348mergedA7vx0o$default, function6, iM7815getClipgIe3tQ8, softWrap3, maxLines3, minLines5, mapEmptyMap, (ColorProducer) value$iv, null, $composer3, ($dirty10 & 112) | (($dirty4 >> 9) & 7168) | (($dirty4 << 9) & 57344) | (($dirty4 << 9) & 458752) | (($dirty4 << 9) & 3670016) | (($dirty4 << 9) & 29360128) | (($dirty4 << 9) & 234881024), 0, 1024);
                $composer2 = $composer3;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function2 = function6;
                minLines2 = minLines5;
                j5 = jM5133getUnspecified0d7_KjU;
                modifier3 = modifier2;
                style2 = style3;
                j6 = j9;
                fontWeight3 = fontWeight4;
                fontFamily2 = fontFamily3;
                j7 = j10;
                textDecoration2 = textDecoration3;
                j8 = j11;
                map2 = mapEmptyMap;
                maxLines2 = maxLines3;
                softWrap2 = softWrap3;
                i3 = iM7815getClipgIe3tQ8;
                textAlign2 = textAlign3;
                fontStyle3 = fontStyle4;
            }
            Object value$iv3 = createTextWithLinkStyles(text, linkStyles);
            $composer3.updateRememberedValue(value$iv3);
            it$iv2 = value$iv3;
            AnnotatedString textWithMaterialLinkStyles2 = (AnnotatedString) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (textAlign3 != null) {
                iM7764getUnspecifiede0LSkKk = textAlign3.m7757unboximpl();
            } else {
                iM7764getUnspecifiede0LSkKk = TextAlign.INSTANCE.m7764getUnspecifiede0LSkKk();
            }
            TextStyle textStyleM7348mergedA7vx0o$default2 = TextStyle.m7348mergedA7vx0o$default(style3, 0L, j9, fontWeight4, fontStyle4, null, fontFamily3, null, j10, null, null, null, 0L, textDecoration3, null, null, iM7764getUnspecifiede0LSkKk, 0, j11, null, null, 0, 0, null, null, 16609105, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 2144152143, "CC(remember):Text.kt#9igjgp");
            invalid$iv = $composer3.changed(overrideColorOrUnspecified);
            value$iv = $composer3.rememberedValue();
            if (invalid$iv) {
                value$iv = new ColorProducer() { // from class: androidx.compose.material.TextKt$Text$6$1
                    @Override // androidx.compose.ui.graphics.ColorProducer
                    /* JADX INFO: renamed from: invoke-0d7_KjU */
                    public final long mo1797invoke0d7_KjU() {
                        return overrideColorOrUnspecified;
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = new ColorProducer() { // from class: androidx.compose.material.TextKt$Text$6$1
                    @Override // androidx.compose.ui.graphics.ColorProducer
                    /* JADX INFO: renamed from: invoke-0d7_KjU */
                    public final long mo1797invoke0d7_KjU() {
                        return overrideColorOrUnspecified;
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            BasicTextKt.m1154BasicTextCL7eQgs(textWithMaterialLinkStyles2, modifier2, textStyleM7348mergedA7vx0o$default2, function6, iM7815getClipgIe3tQ8, softWrap3, maxLines3, minLines5, mapEmptyMap, (ColorProducer) value$iv, null, $composer3, ($dirty10 & 112) | (($dirty4 >> 9) & 7168) | (($dirty4 << 9) & 57344) | (($dirty4 << 9) & 458752) | (($dirty4 << 9) & 3670016) | (($dirty4 << 9) & 29360128) | (($dirty4 << 9) & 234881024), 0, 1024);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function2 = function6;
            minLines2 = minLines5;
            j5 = jM5133getUnspecified0d7_KjU;
            modifier3 = modifier2;
            style2 = style3;
            j6 = j9;
            fontWeight3 = fontWeight4;
            fontFamily2 = fontFamily3;
            j7 = j10;
            textDecoration2 = textDecoration3;
            j8 = j11;
            map2 = mapEmptyMap;
            maxLines2 = maxLines3;
            softWrap2 = softWrap3;
            i3 = iM7815getClipgIe3tQ8;
            textAlign2 = textAlign3;
            fontStyle3 = fontStyle4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            textAlign2 = textAlign;
            i3 = i;
            softWrap2 = softWrap;
            maxLines2 = maxLines;
            minLines2 = minLines;
            function2 = function1;
            style2 = style;
            fontStyle3 = fontStyle2;
            fontWeight3 = fontWeight2;
            j5 = jM5133getUnspecified0d7_KjU;
            modifier3 = modifier2;
            j6 = jM8110getUnspecifiedXSAIIZE;
            fontFamily2 = fontFamily;
            j7 = j3;
            textDecoration2 = textDecoration;
            j8 = j4;
            map2 = map;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextKt.Text_IbK3jfQ$lambda$3(text, modifier3, j5, j6, fontStyle3, fontWeight3, fontFamily2, j7, textDecoration2, textAlign2, j8, i3, softWrap2, maxLines2, minLines2, map2, function2, style2, $changed, $changed1, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Text__4IGK_g$lambda$2$0(TextLayoutResult it) {
        return Unit.INSTANCE;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: PrepareForCodeGen
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r6v0 int
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.dex.instructions.args.RegisterArg.sameCodeVar(RegisterArg.java:193)
        	at jadx.core.dex.visitors.PrepareForCodeGen.modifyArith(PrepareForCodeGen.java:242)
        	at jadx.core.dex.visitors.PrepareForCodeGen.visit(PrepareForCodeGen.java:88)
        */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with minLines instead")
    /* JADX INFO: renamed from: Text--4IGK_g, reason: not valid java name */
    public static final /* synthetic */ void m2012Text4IGK_g(final androidx.compose.ui.text.AnnotatedString r43, androidx.compose.ui.Modifier r44, long r45, long r47, androidx.compose.ui.text.font.FontStyle r49, androidx.compose.ui.text.font.FontWeight r50, androidx.compose.ui.text.font.FontFamily r51, long r52, androidx.compose.ui.text.style.TextDecoration r54, androidx.compose.ui.text.style.TextAlign r55, long r56, int r58, boolean r59, int r60, java.util.Map r61, kotlin.jvm.functions.Function1 r62, androidx.compose.ui.text.TextStyle r63, androidx.compose.runtime.Composer r64, final int r65, final int r66, final int r67) {
        /*
            Method dump skipped, instruction units count: 1188
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextKt.m2012Text4IGK_g(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, java.util.Map, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }

    public static final ProvidableCompositionLocal<TextStyle> getLocalTextStyle() {
        return LocalTextStyle;
    }

    public static final void ProvideTextStyle(final TextStyle value, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-13499697);
        ComposerKt.sourceInformation($composer2, "C(ProvideTextStyle)N(value,content)410@18449L7,411@18474L80:Text.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(value) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-13499697, $dirty, -1, "androidx.compose.material.ProvideTextStyle (Text.kt:409)");
            }
            ProvidableCompositionLocal<TextStyle> providableCompositionLocal = LocalTextStyle;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(providableCompositionLocal);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            TextStyle mergedStyle = ((TextStyle) objConsume).merge(value);
            CompositionLocalKt.CompositionLocalProvider(LocalTextStyle.provides(mergedStyle), function2, $composer2, ProvidedValue.$stable | ($dirty & 112));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextKt.ProvideTextStyle$lambda$0(value, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final AnnotatedString createTextWithLinkStyles(AnnotatedString text, final TextLinkStyles linkStyles) {
        return text.mapAnnotations(new Function1() { // from class: androidx.compose.material.TextKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextKt.createTextWithLinkStyles$lambda$0(linkStyles, (AnnotatedString.Range) obj);
            }
        });
    }

    static final AnnotatedString.Range createTextWithLinkStyles$lambda$0(TextLinkStyles $linkStyles, AnnotatedString.Range range) {
        AnnotatedString.Annotation link = (AnnotatedString.Annotation) range.getItem();
        if ((link instanceof LinkAnnotation.Url) && ((LinkAnnotation.Url) link).getStyles() == null) {
            Intrinsics.checkNotNull(range, "null cannot be cast to non-null type androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.LinkAnnotation.Url>");
            return AnnotatedString.Range.copy$default(range, LinkAnnotation.Url.copy$default((LinkAnnotation.Url) link, null, $linkStyles, null, 5, null), 0, 0, null, 14, null);
        }
        if ((link instanceof LinkAnnotation.Clickable) && ((LinkAnnotation.Clickable) link).getStyles() == null) {
            Intrinsics.checkNotNull(range, "null cannot be cast to non-null type androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.LinkAnnotation.Clickable>");
            return AnnotatedString.Range.copy$default(range, LinkAnnotation.Clickable.copy$default((LinkAnnotation.Clickable) link, null, $linkStyles, null, 5, null), 0, 0, null, 14, null);
        }
        return range;
    }

    private static final TextLinkStyles rememberTextLinkStyles(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 853203714, "C(rememberTextLinkStyles)432@19253L6,433@19279L159:Text.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(853203714, $changed, -1, "androidx.compose.material.rememberTextLinkStyles (Text.kt:431)");
        }
        long primaryColor = MaterialTheme.INSTANCE.getColors($composer, 6).m1765getPrimary0d7_KjU();
        ComposerKt.sourceInformationMarkerStart($composer, -409980703, "CC(remember):Text.kt#9igjgp");
        boolean invalid$iv = $composer.changed(primaryColor);
        Object value$iv = $composer.rememberedValue();
        if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new TextLinkStyles(new SpanStyle(primaryColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, TextDecoration.INSTANCE.getUnderline(), (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 61438, (DefaultConstructorMarker) null), null, null, null, 14, null);
            $composer.updateRememberedValue(value$iv);
        }
        TextLinkStyles textLinkStyles = (TextLinkStyles) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return textLinkStyles;
    }
}
