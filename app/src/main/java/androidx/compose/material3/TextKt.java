package androidx.compose.material3;

import androidx.compose.foundation.text.BasicTextKt;
import androidx.compose.foundation.text.InlineTextContent;
import androidx.compose.foundation.text.TextAutoSize;
import androidx.compose.material3.tokens.TypographyTokensKt;
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
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aÛ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\u0016\b\u0002\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u0001\u0018\u00010 2\b\b\u0002\u0010\"\u001a\u00020#H\u0007¢\u0006\u0004\b$\u0010%\u001aï\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020&2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\u0014\b\u0002\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020)0(2\u0014\b\u0002\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00010 2\b\b\u0002\u0010\"\u001a\u00020#H\u0007¢\u0006\u0004\b*\u0010+\u001aÏ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\u0016\b\u0002\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u0001\u0018\u00010 2\b\b\u0002\u0010\"\u001a\u00020#H\u0007¢\u0006\u0004\b,\u0010-\u001aã\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020&2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\u0014\b\u0002\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020)0(2\u0014\b\u0002\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00010 2\b\b\u0002\u0010\"\u001a\u00020#H\u0007¢\u0006\u0004\b.\u0010/\u001aÃ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u0014\b\u0002\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00010 2\b\b\u0002\u0010\"\u001a\u00020#H\u0007¢\u0006\u0004\b0\u00101\u001aÙ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020&2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u0014\b\u0002\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020)0(2\u0014\b\u0002\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00010 2\b\b\u0002\u0010\"\u001a\u00020#H\u0007¢\u0006\u0004\b,\u00102\u001a(\u00107\u001a\u00020\u00012\u0006\u00108\u001a\u00020#2\u0011\u00109\u001a\r\u0012\u0004\u0012\u00020\u00010:¢\u0006\u0002\b;H\u0007¢\u0006\u0002\u0010<\u001a\u0018\u0010=\u001a\u00020&2\u0006\u0010\u0002\u001a\u00020&2\u0006\u0010>\u001a\u00020?H\u0002\u001a\r\u0010@\u001a\u00020?H\u0003¢\u0006\u0002\u0010A\"\u0017\u00103\u001a\b\u0012\u0004\u0012\u00020#04¢\u0006\b\n\u0000\u001a\u0004\b5\u00106¨\u0006B"}, d2 = {"Text", "", "text", "", "modifier", "Landroidx/compose/ui/Modifier;", "color", "Landroidx/compose/ui/graphics/Color;", "autoSize", "Landroidx/compose/foundation/text/TextAutoSize;", TtmlNode.ATTR_TTS_FONT_SIZE, "Landroidx/compose/ui/unit/TextUnit;", TtmlNode.ATTR_TTS_FONT_STYLE, "Landroidx/compose/ui/text/font/FontStyle;", TtmlNode.ATTR_TTS_FONT_WEIGHT, "Landroidx/compose/ui/text/font/FontWeight;", TtmlNode.ATTR_TTS_FONT_FAMILY, "Landroidx/compose/ui/text/font/FontFamily;", "letterSpacing", TtmlNode.ATTR_TTS_TEXT_DECORATION, "Landroidx/compose/ui/text/style/TextDecoration;", TtmlNode.ATTR_TTS_TEXT_ALIGN, "Landroidx/compose/ui/text/style/TextAlign;", "lineHeight", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "minLines", "onTextLayout", "Lkotlin/Function1;", "Landroidx/compose/ui/text/TextLayoutResult;", TtmlNode.TAG_STYLE, "Landroidx/compose/ui/text/TextStyle;", "Text-Nvy7gAk", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;JLandroidx/compose/foundation/text/TextAutoSize;JLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZIILkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/ui/text/AnnotatedString;", "inlineContent", "", "Landroidx/compose/foundation/text/InlineTextContent;", "Text-Z58ophY", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;JLandroidx/compose/foundation/text/TextAutoSize;JLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZIILjava/util/Map;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "Text--4IGK_g", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZIILkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "Text-IbK3jfQ", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZIILjava/util/Map;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "Text-fLXpl1I", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZILkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZILjava/util/Map;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "LocalTextStyle", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalTextStyle", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "ProvideTextStyle", "value", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "createTextWithLinkStyles", "linkStyles", "Landroidx/compose/ui/text/TextLinkStyles;", "rememberTextLinkStyles", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/text/TextLinkStyles;", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextKt {
    private static final ProvidableCompositionLocal<TextStyle> LocalTextStyle = CompositionLocalKt.compositionLocalOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0() { // from class: androidx.compose.material3.TextKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return TypographyTokensKt.getDefaultTextStyle();
        }
    });

    static final Unit ProvideTextStyle$lambda$20(TextStyle textStyle, Function2 function2, int i, Composer composer, int i2) {
        ProvideTextStyle(textStyle, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Text_IbK3jfQ$lambda$12(AnnotatedString annotatedString, Modifier modifier, long j, long j2, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long j3, TextDecoration textDecoration, TextAlign textAlign, long j4, int i, boolean z, int i2, int i3, Map map, Function1 function1, TextStyle textStyle, int i4, int i5, int i6, Composer composer, int i7) {
        m3071TextIbK3jfQ(annotatedString, modifier, j, j2, fontStyle, fontWeight, fontFamily, j3, textDecoration, textAlign, j4, i, z, i2, i3, map, function1, textStyle, composer, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
        return Unit.INSTANCE;
    }

    static final Unit Text_Nvy7gAk$lambda$2(String str, Modifier modifier, long j, TextAutoSize textAutoSize, long j2, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long j3, TextDecoration textDecoration, TextAlign textAlign, long j4, int i, boolean z, int i2, int i3, Function1 function1, TextStyle textStyle, int i4, int i5, int i6, Composer composer, int i7) {
        m3072TextNvy7gAk(str, modifier, j, textAutoSize, j2, fontStyle, fontWeight, fontFamily, j3, textDecoration, textAlign, j4, i, z, i2, i3, function1, textStyle, composer, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
        return Unit.INSTANCE;
    }

    static final Unit Text_Z58ophY$lambda$8(AnnotatedString annotatedString, Modifier modifier, long j, TextAutoSize textAutoSize, long j2, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long j3, TextDecoration textDecoration, TextAlign textAlign, long j4, int i, boolean z, int i2, int i3, Map map, Function1 function1, TextStyle textStyle, int i4, int i5, int i6, Composer composer, int i7) {
        m3073TextZ58ophY(annotatedString, modifier, j, textAutoSize, j2, fontStyle, fontWeight, fontFamily, j3, textDecoration, textAlign, j4, i, z, i2, i3, map, function1, textStyle, composer, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
        return Unit.INSTANCE;
    }

    static final Unit Text__4IGK_g$lambda$18(AnnotatedString annotatedString, Modifier modifier, long j, long j2, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long j3, TextDecoration textDecoration, TextAlign textAlign, long j4, int i, boolean z, int i2, Map map, Function1 function1, TextStyle textStyle, int i3, int i4, int i5, Composer composer, int i6) {
        m3069Text4IGK_g(annotatedString, modifier, j, j2, fontStyle, fontWeight, fontFamily, j3, textDecoration, textAlign, j4, i, z, i2, map, function1, textStyle, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
        return Unit.INSTANCE;
    }

    static final Unit Text__4IGK_g$lambda$9(String str, Modifier modifier, long j, long j2, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long j3, TextDecoration textDecoration, TextAlign textAlign, long j4, int i, boolean z, int i2, int i3, Function1 function1, TextStyle textStyle, int i4, int i5, int i6, Composer composer, int i7) {
        m3070Text4IGK_g(str, modifier, j, j2, fontStyle, fontWeight, fontFamily, j3, textDecoration, textAlign, j4, i, z, i2, i3, function1, textStyle, composer, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
        return Unit.INSTANCE;
    }

    static final Unit Text_fLXpl1I$lambda$15(String str, Modifier modifier, long j, long j2, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long j3, TextDecoration textDecoration, TextAlign textAlign, long j4, int i, boolean z, int i2, Function1 function1, TextStyle textStyle, int i3, int i4, int i5, Composer composer, int i6) {
        m3074TextfLXpl1I(str, modifier, j, j2, fontStyle, fontWeight, fontFamily, j3, textDecoration, textAlign, j4, i, z, i2, function1, textStyle, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: Text-Nvy7gAk, reason: not valid java name */
    public static final void m3072TextNvy7gAk(final String text, Modifier modifier, long color, TextAutoSize autoSize, long fontSize, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long letterSpacing, TextDecoration textDecoration, TextAlign textAlign, long lineHeight, int overflow, boolean softWrap, int maxLines, int minLines, Function1<? super TextLayoutResult, Unit> function1, TextStyle style, Composer $composer, final int $changed, final int $changed1, final int i) {
        String str;
        long color2;
        TextAutoSize autoSize2;
        long fontSize2;
        FontStyle fontStyle2;
        FontWeight fontWeight2;
        int $dirty;
        int $dirty1;
        int $dirty2;
        Composer $composer2;
        final Modifier modifier2;
        final TextDecoration textDecoration2;
        final long lineHeight2;
        final int overflow2;
        final boolean softWrap2;
        final int minLines2;
        final int minLines3;
        final TextStyle style2;
        final TextAutoSize autoSize3;
        final long color3;
        final FontStyle fontStyle3;
        final long fontSize3;
        final FontWeight fontWeight3;
        final FontFamily fontFamily2;
        final long letterSpacing2;
        final TextAlign textAlign2;
        final Function1<? super TextLayoutResult, Unit> function2;
        Modifier.Companion modifier3;
        TextAlign textAlign3;
        int overflow3;
        int maxLines2;
        int minLines4;
        Function1<? super TextLayoutResult, Unit> function3;
        FontFamily fontFamily3;
        TextStyle style3;
        FontStyle fontStyle4;
        long fontSize4;
        TextDecoration textDecoration3;
        FontWeight fontWeight4;
        long letterSpacing3;
        long lineHeight3;
        long textColor;
        Composer $composer3 = $composer.startRestartGroup(1809465675);
        ComposerKt.sourceInformation($composer3, "C(Text)N(text,modifier,color:c#ui.graphics.Color,autoSize,fontSize:c#ui.unit.TextUnit,fontStyle:c#ui.text.font.FontStyle,fontWeight,fontFamily,letterSpacing:c#ui.unit.TextUnit,textDecoration,textAlign:c#ui.text.style.TextAlign,lineHeight:c#ui.unit.TextUnit,overflow:c#ui.text.style.TextOverflow,softWrap,maxLines,minLines,onTextLayout,style)124@6409L698:Text.kt#uh7d8r");
        int $dirty3 = $changed;
        int $dirty4 = $changed1;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            str = text;
        } else if (($changed & 6) == 0) {
            str = text;
            $dirty3 |= $composer3.changed(str) ? 4 : 2;
        } else {
            str = text;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changed(modifier) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty3 |= 384;
            color2 = color;
        } else if (($changed & 384) == 0) {
            color2 = color;
            $dirty3 |= $composer3.changed(color2) ? 256 : 128;
        } else {
            color2 = color;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty3 |= 3072;
            autoSize2 = autoSize;
        } else if (($changed & 3072) == 0) {
            autoSize2 = autoSize;
            $dirty3 |= $composer3.changedInstance(autoSize2) ? 2048 : 1024;
        } else {
            autoSize2 = autoSize;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty3 |= 24576;
            fontSize2 = fontSize;
        } else if (($changed & 24576) == 0) {
            fontSize2 = fontSize;
            $dirty3 |= $composer3.changed(fontSize2) ? 16384 : 8192;
        } else {
            fontSize2 = fontSize;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            fontStyle2 = fontStyle;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            fontStyle2 = fontStyle;
            $dirty3 |= $composer3.changed(fontStyle2) ? 131072 : 65536;
        } else {
            fontStyle2 = fontStyle;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty3 |= 1572864;
            fontWeight2 = fontWeight;
        } else if (($changed & 1572864) == 0) {
            fontWeight2 = fontWeight;
            $dirty3 |= $composer3.changed(fontWeight2) ? 1048576 : 524288;
        } else {
            fontWeight2 = fontWeight;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changed(fontFamily) ? 8388608 : 4194304;
        }
        int $dirty5 = i & 256;
        if ($dirty5 != 0) {
            $dirty = $dirty3 | 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty = $dirty3 | ($composer3.changed(letterSpacing) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432);
        } else {
            $dirty = $dirty3;
        }
        int $dirty6 = i & 512;
        if ($dirty6 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changed(textDecoration) ? 536870912 : 268435456;
        }
        int $dirty7 = $dirty;
        int $dirty8 = i & 1024;
        if ($dirty8 != 0) {
            $dirty4 |= 6;
        } else {
            int $dirty9 = $changed1 & 6;
            if ($dirty9 == 0) {
                $dirty4 |= $composer3.changed(textAlign) ? 4 : 2;
            }
        }
        int $dirty10 = i & 2048;
        if ($dirty10 != 0) {
            $dirty4 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty4 |= $composer3.changed(lineHeight) ? 32 : 16;
        }
        int $dirty11 = $dirty4;
        int i9 = i & 4096;
        if (i9 != 0) {
            $dirty1 = $dirty11 | 384;
        } else {
            int $dirty12 = $dirty11;
            int $dirty13 = $changed1 & 384;
            if ($dirty13 == 0) {
                $dirty12 |= $composer3.changed(overflow) ? 256 : 128;
            }
            $dirty1 = $dirty12;
        }
        int i10 = i & 8192;
        if (i10 != 0) {
            $dirty2 = $dirty1 | 3072;
        } else {
            int $dirty14 = $dirty1;
            int $dirty15 = $changed1 & 3072;
            if ($dirty15 == 0) {
                $dirty2 = $dirty14 | ($composer3.changed(softWrap) ? 2048 : 1024);
            } else {
                $dirty2 = $dirty14;
            }
        }
        int i11 = i & 16384;
        if (i11 != 0) {
            $dirty2 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty2 |= $composer3.changed(maxLines) ? 16384 : 8192;
        }
        int i12 = i & 32768;
        if (i12 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer3.changed(minLines) ? 131072 : 65536;
        }
        int i13 = i & 65536;
        if (i13 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(function1) ? 1048576 : 524288;
        }
        if (($changed1 & 12582912) == 0) {
            $dirty2 |= ((i & 131072) == 0 && $composer3.changed(style)) ? 8388608 : 4194304;
        }
        if ($composer3.shouldExecute((($dirty7 & 306783379) == 306783378 && (4793491 & $dirty2) == 4793490) ? false : true, $dirty7 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "119@6296L7");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if (i3 != 0) {
                    color2 = Color.INSTANCE.m5133getUnspecified0d7_KjU();
                }
                if (i4 != 0) {
                    autoSize2 = null;
                }
                if (i5 != 0) {
                    fontSize2 = TextUnit.INSTANCE.m8110getUnspecifiedXSAIIZE();
                }
                if (i6 != 0) {
                    fontStyle2 = null;
                }
                if (i7 != 0) {
                    fontWeight2 = null;
                }
                FontFamily fontFamily4 = i8 != 0 ? null : fontFamily;
                long letterSpacing4 = $dirty5 != 0 ? TextUnit.INSTANCE.m8110getUnspecifiedXSAIIZE() : letterSpacing;
                TextDecoration textDecoration4 = $dirty6 != 0 ? null : textDecoration;
                textAlign3 = $dirty8 != 0 ? null : textAlign;
                long lineHeight4 = $dirty10 != 0 ? TextUnit.INSTANCE.m8110getUnspecifiedXSAIIZE() : lineHeight;
                overflow3 = i9 != 0 ? TextOverflow.INSTANCE.m7815getClipgIe3tQ8() : overflow;
                softWrap2 = i10 != 0 ? true : softWrap;
                maxLines2 = i11 != 0 ? Integer.MAX_VALUE : maxLines;
                minLines4 = i12 != 0 ? 1 : minLines;
                function3 = i13 != 0 ? null : function1;
                if ((i & 131072) != 0) {
                    Modifier modifier4 = modifier3;
                    ProvidableCompositionLocal<TextStyle> providableCompositionLocal = LocalTextStyle;
                    FontFamily fontFamily5 = fontFamily4;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(providableCompositionLocal);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty2 &= -29360129;
                    fontFamily3 = fontFamily5;
                    style3 = (TextStyle) objConsume;
                    fontStyle4 = fontStyle2;
                    fontSize4 = fontSize2;
                    textDecoration3 = textDecoration4;
                    fontWeight4 = fontWeight2;
                    letterSpacing3 = letterSpacing4;
                    lineHeight3 = lineHeight4;
                    modifier3 = modifier4;
                } else {
                    fontFamily3 = fontFamily4;
                    style3 = style;
                    fontStyle4 = fontStyle2;
                    fontSize4 = fontSize2;
                    textDecoration3 = textDecoration4;
                    fontWeight4 = fontWeight2;
                    letterSpacing3 = letterSpacing4;
                    lineHeight3 = lineHeight4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 131072) != 0) {
                    $dirty2 &= -29360129;
                }
                modifier3 = modifier;
                fontFamily3 = fontFamily;
                letterSpacing3 = letterSpacing;
                textDecoration3 = textDecoration;
                textAlign3 = textAlign;
                lineHeight3 = lineHeight;
                overflow3 = overflow;
                softWrap2 = softWrap;
                maxLines2 = maxLines;
                minLines4 = minLines;
                function3 = function1;
                style3 = style;
                fontStyle4 = fontStyle2;
                fontSize4 = fontSize2;
                fontWeight4 = fontWeight2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1809465675, $dirty7, $dirty2, "androidx.compose.material3.Text (Text.kt:120)");
            }
            $composer3.startReplaceGroup(-565217106);
            ComposerKt.sourceInformation($composer3, "");
            long $this$takeOrElse_u2dDxMtmZc$iv = color2;
            if ($this$takeOrElse_u2dDxMtmZc$iv != 16) {
                $dirty7 = $dirty7;
                modifier3 = modifier3;
                textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            } else {
                $composer3.startReplaceGroup(-565216333);
                ComposerKt.sourceInformation($composer3, "*122@6392L7");
                long $this$takeOrElse_u2dDxMtmZc$iv2 = style3.m7358getColor0d7_KjU();
                if (!($this$takeOrElse_u2dDxMtmZc$iv2 != 16)) {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume2 = $composer3.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $this$takeOrElse_u2dDxMtmZc$iv2 = ((Color) objConsume2).m5107unboximpl();
                }
                $composer3.endReplaceGroup();
                textColor = $this$takeOrElse_u2dDxMtmZc$iv2;
            }
            $composer3.endReplaceGroup();
            BasicTextKt.m1156BasicTextRWo7tUw(str, modifier3, TextStyle.m7348mergedA7vx0o$default(style3, textColor, fontSize4, fontWeight4, fontStyle4, null, fontFamily3, null, letterSpacing3, null, null, null, 0L, textDecoration3, null, null, textAlign3 != null ? textAlign3.m7757unboximpl() : TextAlign.INSTANCE.m7764getUnspecifiede0LSkKk(), 0, lineHeight3, null, null, 0, 0, null, null, 16609104, null), function3, overflow3, softWrap2, maxLines2, minLines4, (ColorProducer) null, autoSize2, $composer3, ($dirty7 & 14) | ($dirty7 & 112) | (($dirty2 >> 9) & 7168) | (($dirty2 << 6) & 57344) | (($dirty2 << 6) & 458752) | (($dirty2 << 6) & 3670016) | (($dirty2 << 6) & 29360128) | (($dirty7 << 18) & 1879048192), 256);
            Modifier modifier5 = modifier3;
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier5;
            textAlign2 = textAlign3;
            minLines3 = minLines4;
            function2 = function3;
            overflow2 = overflow3;
            style2 = style3;
            fontFamily2 = fontFamily3;
            letterSpacing2 = letterSpacing3;
            textDecoration2 = textDecoration3;
            lineHeight2 = lineHeight3;
            minLines2 = maxLines2;
            autoSize3 = autoSize2;
            color3 = color2;
            fontSize3 = fontSize4;
            fontWeight3 = fontWeight4;
            fontStyle3 = fontStyle4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            textDecoration2 = textDecoration;
            lineHeight2 = lineHeight;
            overflow2 = overflow;
            softWrap2 = softWrap;
            minLines2 = maxLines;
            minLines3 = minLines;
            style2 = style;
            autoSize3 = autoSize2;
            color3 = color2;
            fontStyle3 = fontStyle2;
            fontSize3 = fontSize2;
            fontWeight3 = fontWeight2;
            fontFamily2 = fontFamily;
            letterSpacing2 = letterSpacing;
            textAlign2 = textAlign;
            function2 = function1;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TextKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextKt.Text_Nvy7gAk$lambda$2(text, modifier2, color3, autoSize3, fontSize3, fontStyle3, fontWeight3, fontFamily2, letterSpacing2, textDecoration2, textAlign2, lineHeight2, overflow2, softWrap2, minLines2, minLines3, function2, style2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: Text-Z58ophY, reason: not valid java name */
    public static final void m3073TextZ58ophY(final AnnotatedString text, Modifier modifier, long color, TextAutoSize autoSize, long fontSize, FontStyle fontStyle, FontWeight fontWeight, FontFamily fontFamily, long letterSpacing, TextDecoration textDecoration, TextAlign textAlign, long lineHeight, int overflow, boolean softWrap, int maxLines, int minLines, Map<String, InlineTextContent> map, Function1<? super TextLayoutResult, Unit> function1, TextStyle style, Composer $composer, final int $changed, final int $changed1, final int $dirty) {
        Modifier modifier2;
        TextAutoSize textAutoSize;
        long j;
        FontStyle fontStyle2;
        FontWeight fontWeight2;
        int $dirty2;
        int $dirty1;
        int $dirty3;
        Composer $composer2;
        final long color2;
        final TextDecoration textDecoration2;
        final int overflow2;
        final boolean softWrap2;
        final int minLines2;
        final int minLines3;
        final Map<String, InlineTextContent> map2;
        final Function1<? super TextLayoutResult, Unit> function2;
        final TextStyle style2;
        final TextAutoSize autoSize2;
        final long fontSize2;
        final Modifier modifier3;
        final FontStyle fontStyle3;
        final FontWeight fontWeight3;
        final FontFamily fontFamily2;
        final long letterSpacing2;
        final TextAlign textAlign2;
        final long lineHeight2;
        TextAutoSize autoSize3;
        TextAlign textAlign3;
        int overflow3;
        int maxLines2;
        int minLines4;
        Map<String, InlineTextContent> mapEmptyMap;
        Function1<? super TextLayoutResult, Unit> function3;
        long color3;
        Function1<? super TextLayoutResult, Unit> function4;
        TextStyle style3;
        long fontSize3;
        FontStyle fontStyle4;
        FontWeight fontWeight4;
        FontFamily fontFamily3;
        long letterSpacing3;
        TextDecoration textDecoration3;
        long lineHeight3;
        int $dirty4;
        long textColor;
        Composer $composer3 = $composer.startRestartGroup(292247417);
        ComposerKt.sourceInformation($composer3, "C(Text)N(text,modifier,color:c#ui.graphics.Color,autoSize,fontSize:c#ui.unit.TextUnit,fontStyle:c#ui.text.font.FontStyle,fontWeight,fontFamily,letterSpacing:c#ui.unit.TextUnit,textDecoration,textAlign:c#ui.text.style.TextAlign,lineHeight:c#ui.unit.TextUnit,overflow:c#ui.text.style.TextOverflow,softWrap,maxLines,minLines,inlineContent,onTextLayout,style)230@11970L24,232@12040L73,234@12119L759:Text.kt#uh7d8r");
        int $dirty5 = $changed;
        int $dirty6 = $changed1;
        if (($dirty & 1) != 0) {
            $dirty5 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty5 |= $composer3.changed(text) ? 4 : 2;
        }
        int i = $dirty & 2;
        if (i != 0) {
            $dirty5 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i2 = $dirty & 4;
        if (i2 != 0) {
            $dirty5 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty5 |= $composer3.changed(color) ? 256 : 128;
        }
        int i3 = $dirty & 8;
        if (i3 != 0) {
            $dirty5 |= 3072;
            textAutoSize = autoSize;
        } else if (($changed & 3072) == 0) {
            textAutoSize = autoSize;
            $dirty5 |= $composer3.changedInstance(textAutoSize) ? 2048 : 1024;
        } else {
            textAutoSize = autoSize;
        }
        int i4 = $dirty & 16;
        if (i4 != 0) {
            $dirty5 |= 24576;
            j = fontSize;
        } else if (($changed & 24576) == 0) {
            j = fontSize;
            $dirty5 |= $composer3.changed(j) ? 16384 : 8192;
        } else {
            j = fontSize;
        }
        int i5 = $dirty & 32;
        if (i5 != 0) {
            $dirty5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            fontStyle2 = fontStyle;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            fontStyle2 = fontStyle;
            $dirty5 |= $composer3.changed(fontStyle2) ? 131072 : 65536;
        } else {
            fontStyle2 = fontStyle;
        }
        int i6 = $dirty & 64;
        if (i6 != 0) {
            $dirty5 |= 1572864;
            fontWeight2 = fontWeight;
        } else if (($changed & 1572864) == 0) {
            fontWeight2 = fontWeight;
            $dirty5 |= $composer3.changed(fontWeight2) ? 1048576 : 524288;
        } else {
            fontWeight2 = fontWeight;
        }
        int $dirty7 = $dirty & 128;
        if ($dirty7 != 0) {
            $dirty5 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty5 |= $composer3.changed(fontFamily) ? 8388608 : 4194304;
        }
        int $dirty8 = $dirty & 256;
        if ($dirty8 != 0) {
            $dirty2 = $dirty5 | 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 = $dirty5 | ($composer3.changed(letterSpacing) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432);
        } else {
            $dirty2 = $dirty5;
        }
        int $dirty9 = $dirty & 512;
        if ($dirty9 != 0) {
            $dirty2 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty2 |= $composer3.changed(textDecoration) ? 536870912 : 268435456;
        }
        int $dirty10 = $dirty2;
        int i7 = $dirty & 1024;
        if (i7 != 0) {
            $dirty6 |= 6;
        } else {
            int $dirty11 = $changed1 & 6;
            if ($dirty11 == 0) {
                $dirty6 |= $composer3.changed(textAlign) ? 4 : 2;
            }
        }
        int i8 = $dirty & 2048;
        if (i8 != 0) {
            $dirty6 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty6 |= $composer3.changed(lineHeight) ? 32 : 16;
        }
        int $dirty12 = $dirty6;
        int i9 = $dirty & 4096;
        if (i9 != 0) {
            $dirty1 = $dirty12 | 384;
        } else {
            int $dirty13 = $dirty12;
            int $dirty14 = $changed1 & 384;
            if ($dirty14 == 0) {
                $dirty13 |= $composer3.changed(overflow) ? 256 : 128;
            }
            $dirty1 = $dirty13;
        }
        int i10 = $dirty & 8192;
        if (i10 != 0) {
            $dirty3 = $dirty1 | 3072;
        } else {
            int $dirty15 = $dirty1;
            int $dirty16 = $changed1 & 3072;
            if ($dirty16 == 0) {
                $dirty3 = $dirty15 | ($composer3.changed(softWrap) ? 2048 : 1024);
            } else {
                $dirty3 = $dirty15;
            }
        }
        int i11 = $dirty & 16384;
        if (i11 != 0) {
            $dirty3 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty3 |= $composer3.changed(maxLines) ? 16384 : 8192;
        }
        int i12 = $dirty & 32768;
        if (i12 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= $composer3.changed(minLines) ? 131072 : 65536;
        }
        int i13 = $dirty & 65536;
        if (i13 != 0) {
            $dirty3 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty3 |= $composer3.changedInstance(map) ? 1048576 : 524288;
        }
        int i14 = $dirty & 131072;
        if (i14 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty3 |= $composer3.changedInstance(function1) ? 8388608 : 4194304;
        }
        if (($changed1 & 100663296) == 0) {
            $dirty3 |= (($dirty & 262144) == 0 && $composer3.changed(style)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty17 = $dirty3;
        if ($composer3.shouldExecute((($dirty10 & 306783379) == 306783378 && ($dirty17 & 38347923) == 38347922) ? false : true, $dirty10 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "226@11800L2,227@11842L7");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                long color4 = i2 != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : color;
                autoSize3 = i3 != 0 ? null : textAutoSize;
                long fontSize4 = i4 != 0 ? TextUnit.INSTANCE.m8110getUnspecifiedXSAIIZE() : j;
                FontStyle fontStyle5 = i5 != 0 ? null : fontStyle2;
                FontWeight fontWeight5 = i6 != 0 ? null : fontWeight2;
                FontFamily fontFamily4 = $dirty7 != 0 ? null : fontFamily;
                long letterSpacing4 = $dirty8 != 0 ? TextUnit.INSTANCE.m8110getUnspecifiedXSAIIZE() : letterSpacing;
                TextDecoration textDecoration4 = $dirty9 != 0 ? null : textDecoration;
                textAlign3 = i7 != 0 ? null : textAlign;
                long lineHeight4 = i8 != 0 ? TextUnit.INSTANCE.m8110getUnspecifiedXSAIIZE() : lineHeight;
                overflow3 = i9 != 0 ? TextOverflow.INSTANCE.m7815getClipgIe3tQ8() : overflow;
                softWrap2 = i10 != 0 ? true : softWrap;
                maxLines2 = i11 != 0 ? Integer.MAX_VALUE : maxLines;
                minLines4 = i12 != 0 ? 1 : minLines;
                mapEmptyMap = i13 != 0 ? MapsKt.emptyMap() : map;
                if (i14 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 1676916987, "CC(remember):Text.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = new Function1() { // from class: androidx.compose.material3.TextKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return Unit.INSTANCE;
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    function3 = (Function1) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    function3 = function1;
                }
                if (($dirty & 262144) != 0) {
                    ProvidableCompositionLocal<TextStyle> providableCompositionLocal = LocalTextStyle;
                    Function1<? super TextLayoutResult, Unit> function5 = function3;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(providableCompositionLocal);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    style3 = (TextStyle) objConsume;
                    fontSize3 = fontSize4;
                    fontStyle4 = fontStyle5;
                    fontWeight4 = fontWeight5;
                    fontFamily3 = fontFamily4;
                    letterSpacing3 = letterSpacing4;
                    textDecoration3 = textDecoration4;
                    lineHeight3 = lineHeight4;
                    color3 = color4;
                    $dirty4 = $dirty17 & (-234881025);
                    function4 = function5;
                } else {
                    Function1<? super TextLayoutResult, Unit> function6 = function3;
                    color3 = color4;
                    function4 = function6;
                    style3 = style;
                    fontSize3 = fontSize4;
                    fontStyle4 = fontStyle5;
                    fontWeight4 = fontWeight5;
                    fontFamily3 = fontFamily4;
                    letterSpacing3 = letterSpacing4;
                    textDecoration3 = textDecoration4;
                    lineHeight3 = lineHeight4;
                    $dirty4 = $dirty17;
                }
            } else {
                $composer3.skipToGroupEnd();
                if (($dirty & 262144) != 0) {
                    fontFamily3 = fontFamily;
                    letterSpacing3 = letterSpacing;
                    textDecoration3 = textDecoration;
                    textAlign3 = textAlign;
                    lineHeight3 = lineHeight;
                    overflow3 = overflow;
                    softWrap2 = softWrap;
                    maxLines2 = maxLines;
                    minLines4 = minLines;
                    mapEmptyMap = map;
                    function4 = function1;
                    style3 = style;
                    autoSize3 = textAutoSize;
                    fontSize3 = j;
                    fontStyle4 = fontStyle2;
                    fontWeight4 = fontWeight2;
                    $dirty4 = $dirty17 & (-234881025);
                    color3 = color;
                } else {
                    color3 = color;
                    fontFamily3 = fontFamily;
                    letterSpacing3 = letterSpacing;
                    textDecoration3 = textDecoration;
                    textAlign3 = textAlign;
                    lineHeight3 = lineHeight;
                    overflow3 = overflow;
                    softWrap2 = softWrap;
                    maxLines2 = maxLines;
                    minLines4 = minLines;
                    mapEmptyMap = map;
                    function4 = function1;
                    style3 = style;
                    autoSize3 = textAutoSize;
                    fontSize3 = j;
                    fontStyle4 = fontStyle2;
                    fontWeight4 = fontWeight2;
                    $dirty4 = $dirty17;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(292247417, $dirty10, $dirty4, "androidx.compose.material3.Text (Text.kt:228)");
            }
            $composer3.startReplaceGroup(1676919644);
            ComposerKt.sourceInformation($composer3, "");
            long $this$takeOrElse_u2dDxMtmZc$iv = color3;
            if ($this$takeOrElse_u2dDxMtmZc$iv != 16) {
                $dirty10 = $dirty10;
                color3 = color3;
                textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            } else {
                $composer3.startReplaceGroup(1676920417);
                ComposerKt.sourceInformation($composer3, "*229@11937L7");
                long $this$takeOrElse_u2dDxMtmZc$iv2 = style3.m7358getColor0d7_KjU();
                if (!($this$takeOrElse_u2dDxMtmZc$iv2 != 16)) {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume2 = $composer3.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $this$takeOrElse_u2dDxMtmZc$iv2 = ((Color) objConsume2).m5107unboximpl();
                }
                $composer3.endReplaceGroup();
                textColor = $this$takeOrElse_u2dDxMtmZc$iv2;
            }
            $composer3.endReplaceGroup();
            TextLinkStyles linkStyles = rememberTextLinkStyles($composer3, 0);
            ComposerKt.sourceInformationMarkerStart($composer3, 1676924738, "CC(remember):Text.kt#9igjgp");
            boolean invalid$iv = (($dirty10 & 14) == 4) | $composer3.changed(linkStyles);
            Object it$iv2 = $composer3.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = createTextWithLinkStyles(text, linkStyles);
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            AnnotatedString textWithMaterialLinkStyles = (AnnotatedString) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Function1<? super TextLayoutResult, Unit> function7 = function4;
            BasicTextKt.m1154BasicTextCL7eQgs(textWithMaterialLinkStyles, modifier2, TextStyle.m7348mergedA7vx0o$default(style3, textColor, fontSize3, fontWeight4, fontStyle4, null, fontFamily3, null, letterSpacing3, null, null, null, 0L, textDecoration3, null, null, textAlign3 != null ? textAlign3.m7757unboximpl() : TextAlign.INSTANCE.m7764getUnspecifiede0LSkKk(), 0, lineHeight3, null, null, 0, 0, null, null, 16609104, null), function7, overflow3, softWrap2, maxLines2, minLines4, mapEmptyMap, null, autoSize3, $composer3, ($dirty10 & 112) | (($dirty4 >> 12) & 7168) | (($dirty4 << 6) & 57344) | (($dirty4 << 6) & 458752) | (($dirty4 << 6) & 3670016) | (($dirty4 << 6) & 29360128) | (($dirty4 << 6) & 234881024), ($dirty10 >> 9) & 14, 512);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            color2 = color3;
            function2 = function7;
            autoSize2 = autoSize3;
            modifier3 = modifier2;
            overflow2 = overflow3;
            textAlign2 = textAlign3;
            style2 = style3;
            fontWeight3 = fontWeight4;
            fontStyle3 = fontStyle4;
            fontFamily2 = fontFamily3;
            letterSpacing2 = letterSpacing3;
            textDecoration2 = textDecoration3;
            lineHeight2 = lineHeight3;
            map2 = mapEmptyMap;
            minLines3 = minLines4;
            minLines2 = maxLines2;
            fontSize2 = fontSize3;
        } else {
            $dirty10 = $dirty10;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            color2 = color;
            textDecoration2 = textDecoration;
            overflow2 = overflow;
            softWrap2 = softWrap;
            minLines2 = maxLines;
            minLines3 = minLines;
            map2 = map;
            function2 = function1;
            style2 = style;
            autoSize2 = textAutoSize;
            fontSize2 = j;
            modifier3 = modifier2;
            fontStyle3 = fontStyle2;
            fontWeight3 = fontWeight2;
            fontFamily2 = fontFamily;
            letterSpacing2 = letterSpacing;
            textAlign2 = textAlign;
            lineHeight2 = lineHeight;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TextKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextKt.Text_Z58ophY$lambda$8(text, modifier3, color2, autoSize2, fontSize2, fontStyle3, fontWeight3, fontFamily2, letterSpacing2, textDecoration2, textAlign2, lineHeight2, overflow2, softWrap2, minLines2, minLines3, map2, function2, style2, $changed, $changed1, $dirty, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: PrepareForCodeGen
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r6v0 int
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.dex.instructions.args.RegisterArg.sameCodeVar(RegisterArg.java:193)
        	at jadx.core.dex.visitors.PrepareForCodeGen.modifyArith(PrepareForCodeGen.java:242)
        	at jadx.core.dex.visitors.PrepareForCodeGen.visit(PrepareForCodeGen.java:88)
        */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with autoSize instead")
    /* JADX INFO: renamed from: Text--4IGK_g, reason: not valid java name */
    public static final /* synthetic */ void m3070Text4IGK_g(final java.lang.String r44, androidx.compose.ui.Modifier r45, long r46, long r48, androidx.compose.ui.text.font.FontStyle r50, androidx.compose.ui.text.font.FontWeight r51, androidx.compose.ui.text.font.FontFamily r52, long r53, androidx.compose.ui.text.style.TextDecoration r55, androidx.compose.ui.text.style.TextAlign r56, long r57, int r59, boolean r60, int r61, int r62, kotlin.jvm.functions.Function1 r63, androidx.compose.ui.text.TextStyle r64, androidx.compose.runtime.Composer r65, final int r66, final int r67, final int r68) {
        /*
            Method dump skipped, instruction units count: 1181
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextKt.m3070Text4IGK_g(java.lang.String, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, int, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: PrepareForCodeGen
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r5v0 int
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.dex.instructions.args.RegisterArg.sameCodeVar(RegisterArg.java:193)
        	at jadx.core.dex.visitors.PrepareForCodeGen.modifyArith(PrepareForCodeGen.java:242)
        	at jadx.core.dex.visitors.PrepareForCodeGen.visit(PrepareForCodeGen.java:88)
        */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with autoSize instead")
    /* JADX INFO: renamed from: Text-IbK3jfQ, reason: not valid java name */
    public static final /* synthetic */ void m3071TextIbK3jfQ(final androidx.compose.ui.text.AnnotatedString r42, androidx.compose.ui.Modifier r43, long r44, long r46, androidx.compose.ui.text.font.FontStyle r48, androidx.compose.ui.text.font.FontWeight r49, androidx.compose.ui.text.font.FontFamily r50, long r51, androidx.compose.ui.text.style.TextDecoration r53, androidx.compose.ui.text.style.TextAlign r54, long r55, int r57, boolean r58, int r59, int r60, java.util.Map r61, kotlin.jvm.functions.Function1 r62, androidx.compose.ui.text.TextStyle r63, androidx.compose.runtime.Composer r64, final int r65, final int r66, final int r67) {
        /*
            Method dump skipped, instruction units count: 1263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextKt.m3071TextIbK3jfQ(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, int, java.util.Map, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
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
    public static final /* synthetic */ void m3074TextfLXpl1I(final java.lang.String r45, androidx.compose.ui.Modifier r46, long r47, long r49, androidx.compose.ui.text.font.FontStyle r51, androidx.compose.ui.text.font.FontWeight r52, androidx.compose.ui.text.font.FontFamily r53, long r54, androidx.compose.ui.text.style.TextDecoration r56, androidx.compose.ui.text.style.TextAlign r57, long r58, int r60, boolean r61, int r62, kotlin.jvm.functions.Function1 r63, androidx.compose.ui.text.TextStyle r64, androidx.compose.runtime.Composer r65, final int r66, final int r67, final int r68) {
        /*
            Method dump skipped, instruction units count: 1188
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextKt.m3074TextfLXpl1I(java.lang.String, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
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
    public static final /* synthetic */ void m3069Text4IGK_g(final androidx.compose.ui.text.AnnotatedString r44, androidx.compose.ui.Modifier r45, long r46, long r48, androidx.compose.ui.text.font.FontStyle r50, androidx.compose.ui.text.font.FontWeight r51, androidx.compose.ui.text.font.FontFamily r52, long r53, androidx.compose.ui.text.style.TextDecoration r55, androidx.compose.ui.text.style.TextAlign r56, long r57, int r59, boolean r60, int r61, java.util.Map r62, kotlin.jvm.functions.Function1 r63, androidx.compose.ui.text.TextStyle r64, androidx.compose.runtime.Composer r65, final int r66, final int r67, final int r68) {
        /*
            Method dump skipped, instruction units count: 1234
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextKt.m3069Text4IGK_g(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, java.util.Map, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }

    public static final ProvidableCompositionLocal<TextStyle> getLocalTextStyle() {
        return LocalTextStyle;
    }

    public static final void ProvideTextStyle(final TextStyle value, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(15327438);
        ComposerKt.sourceInformation($composer2, "C(ProvideTextStyle)N(value,content)460@19447L7,461@19472L80:Text.kt#uh7d8r");
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
                ComposerKt.traceEventStart(15327438, $dirty, -1, "androidx.compose.material3.ProvideTextStyle (Text.kt:459)");
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TextKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextKt.ProvideTextStyle$lambda$20(value, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final AnnotatedString createTextWithLinkStyles(AnnotatedString text, final TextLinkStyles linkStyles) {
        return text.mapAnnotations(new Function1() { // from class: androidx.compose.material3.TextKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextKt.createTextWithLinkStyles$lambda$21(linkStyles, (AnnotatedString.Range) obj);
            }
        });
    }

    static final AnnotatedString.Range createTextWithLinkStyles$lambda$21(TextLinkStyles $linkStyles, AnnotatedString.Range range) {
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
        ComposerKt.sourceInformationMarkerStart($composer, 1113329403, "C(rememberTextLinkStyles)482@20251L11,483@20282L159:Text.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1113329403, $changed, -1, "androidx.compose.material3.rememberTextLinkStyles (Text.kt:481)");
        }
        long primaryColor = MaterialTheme.INSTANCE.getColorScheme($composer, 6).getPrimary();
        ComposerKt.sourceInformationMarkerStart($composer, 462704058, "CC(remember):Text.kt#9igjgp");
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
