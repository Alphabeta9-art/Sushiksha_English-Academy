package androidx.compose.material3.internal;

import androidx.autofill.HintConstants;
import androidx.compose.animation.ColorVectorConverterKt;
import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.InteractiveComponentSizeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.MotionSchemeKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextFieldLabelPosition;
import androidx.compose.material3.TextFieldLabelScope;
import androidx.compose.material3.Typography;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.SmallIconButtonTokens;
import androidx.compose.material3.tokens.TypeScaleTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnit;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.cloudinary.metadata.MetadataField;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextFieldImpl.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b&\u001a\u0099\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\b2\u0006\u0010\t\u001a\u00020\n2\u001e\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\b¢\u0006\u0002\b\u000e2\u0013\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0011\u0010\u001f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\bH\u0001¢\u0006\u0002\u0010 \u001a2\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0011\u0010/\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\bH\u0003¢\u0006\u0004\b0\u00101\u001a*\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020,2\u0011\u0010/\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\bH\u0003¢\u0006\u0004\b2\u00103\u001a\u001c\u00104\u001a\u000205*\u0002052\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u00106\u001a\u000207H\u0000\u001a\u001c\u00108\u001a\u000205*\u0002052\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0000\u001a\u001a\u0010=\u001a\u000205*\u0002052\f\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\u0007H\u0000\u001aÔ\u0001\u0010@\u001a\u00020\u00012\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020,2\u0006\u0010D\u001a\u00020,2\u0006\u0010E\u001a\u00020,2\u0006\u0010!\u001a\u00020\u00162\u0099\u0001\u0010/\u001a\u0094\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020H0G¢\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(K\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020,0G¢\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(L\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020,0G¢\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(M\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020H0G¢\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(N\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020H0G¢\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(O\u0012\u0004\u0012\u00020\u00010F¢\u0006\u0002\b\bH\u0083\b¢\u0006\u0004\bP\u0010Q\u001aE\u0010R\u001a\b\u0012\u0004\u0012\u00020S0G2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010T\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010U\u001a\u00020?2\u0006\u0010V\u001a\u00020?H\u0001¢\u0006\u0004\bW\u0010X\u001a\r\u0010b\u001a\u00020?H\u0001¢\u0006\u0002\u0010c\u001a\r\u0010d\u001a\u00020?H\u0001¢\u0006\u0002\u0010c\"\u0018\u0010!\u001a\u00020\u0016*\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#\"\u0018\u0010$\u001a\u00020%*\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'\"\u0018\u0010(\u001a\u00020%*\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b)\u0010'\"\u000e\u0010Y\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010Z\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010[\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\\\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010]\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010^\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010_\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010`\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010a\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010e\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bf\u0010g\"\u0016\u0010i\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bj\u0010g\"\u0016\u0010k\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bl\u0010g\"\u0016\u0010m\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bn\u0010g\"\u0016\u0010o\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bp\u0010g\"\u0016\u0010q\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\br\u0010g\"\u0016\u0010s\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bt\u0010g\"\u0016\u0010u\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bv\u0010g¨\u0006w²\u0006\n\u0010x\u001a\u00020\u0016X\u008a\u0084\u0002²\u0006\n\u0010y\u001a\u00020\u0016X\u008a\u0084\u0002"}, d2 = {"CommonDecorationBox", "", "type", "Landroidx/compose/material3/internal/TextFieldType;", "visualText", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "labelPosition", "Landroidx/compose/material3/TextFieldLabelPosition;", MetadataField.LABEL, "Lkotlin/Function1;", "Landroidx/compose/material3/TextFieldLabelScope;", "Lkotlin/ExtensionFunctionType;", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "singleLine", "", "enabled", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "colors", "Landroidx/compose/material3/TextFieldColors;", TtmlNode.RUBY_CONTAINER, "(Landroidx/compose/material3/internal/TextFieldType;Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/TextFieldLabelPosition;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/material3/TextFieldColors;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "showExpandedLabel", "getShowExpandedLabel", "(Landroidx/compose/material3/TextFieldLabelPosition;)Z", "minimizedAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "getMinimizedAlignment", "(Landroidx/compose/material3/TextFieldLabelPosition;)Landroidx/compose/ui/Alignment$Horizontal;", "expandedAlignment", "getExpandedAlignment", "Decoration", "contentColor", "Landroidx/compose/ui/graphics/Color;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", UriUtil.LOCAL_CONTENT_SCHEME, "Decoration-3J-VO9M", "(JLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "Decoration-Iv8Zu3U", "(JLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "defaultErrorSemantics", "Landroidx/compose/ui/Modifier;", "defaultErrorMessage", "", "textFieldBackground", "color", "Landroidx/compose/ui/graphics/ColorProducer;", "shape", "Landroidx/compose/ui/graphics/Shape;", "textFieldLabelMinHeight", "minHeight", "Landroidx/compose/ui/unit/Dp;", "TextFieldTransitionScope", "inputState", "Landroidx/compose/material3/internal/InputPhase;", "focusedLabelTextStyleColor", "unfocusedLabelTextStyleColor", "labelColor", "Lkotlin/Function5;", "Landroidx/compose/runtime/State;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "labelProgress", "labelTextStyleColor", "labelContentColor", "placeholderOpacity", "prefixSuffixOpacity", "TextFieldTransitionScope-Jy8F4Js", "(Landroidx/compose/material3/internal/InputPhase;JJJZLkotlin/jvm/functions/Function7;Landroidx/compose/runtime/Composer;I)V", "animateBorderStrokeAsState", "Landroidx/compose/foundation/BorderStroke;", "focused", "focusedBorderThickness", "unfocusedBorderThickness", "animateBorderStrokeAsState-NuRrP5Q", "(ZZZLandroidx/compose/material3/TextFieldColors;FFLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "TextFieldId", "PlaceholderId", "LabelId", "LeadingId", "TrailingId", "PrefixId", "SuffixId", "SupportingId", "ContainerId", "textFieldHorizontalIconPadding", "(Landroidx/compose/runtime/Composer;I)F", "minimizedLabelHalfHeight", "TextFieldPadding", "getTextFieldPadding", "()F", "F", "AboveLabelHorizontalPadding", "getAboveLabelHorizontalPadding", "AboveLabelBottomPadding", "getAboveLabelBottomPadding", "SupportingTopPadding", "getSupportingTopPadding", "PrefixSuffixTextPadding", "getPrefixSuffixTextPadding", "MinTextLineHeight", "getMinTextLineHeight", "MinFocusedLabelLineHeight", "getMinFocusedLabelLineHeight", "MinSupportingTextLineHeight", "getMinSupportingTextLineHeight", "material3", "showPlaceholder", "showPrefixSuffix"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextFieldImplKt {
    public static final String ContainerId = "Container";
    public static final String LabelId = "Label";
    public static final String LeadingId = "Leading";
    public static final String PlaceholderId = "Hint";
    public static final String PrefixId = "Prefix";
    public static final String SuffixId = "Suffix";
    public static final String SupportingId = "Supporting";
    public static final String TextFieldId = "TextField";
    public static final String TrailingId = "Trailing";
    private static final float TextFieldPadding = Dp.m7905constructorimpl(16);
    private static final float AboveLabelHorizontalPadding = Dp.m7905constructorimpl(4);
    private static final float AboveLabelBottomPadding = Dp.m7905constructorimpl(4);
    private static final float SupportingTopPadding = Dp.m7905constructorimpl(4);
    private static final float PrefixSuffixTextPadding = Dp.m7905constructorimpl(2);
    private static final float MinTextLineHeight = Dp.m7905constructorimpl(24);
    private static final float MinFocusedLabelLineHeight = Dp.m7905constructorimpl(16);
    private static final float MinSupportingTextLineHeight = Dp.m7905constructorimpl(16);

    /* JADX INFO: compiled from: TextFieldImpl.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TextFieldType.values().length];
            try {
                iArr[TextFieldType.Filled.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TextFieldType.Outlined.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[InputPhase.values().length];
            try {
                iArr2[InputPhase.Focused.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr2[InputPhase.UnfocusedEmpty.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr2[InputPhase.UnfocusedNotEmpty.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static final Unit CommonDecorationBox$lambda$19(TextFieldType textFieldType, CharSequence charSequence, Function2 function2, TextFieldLabelPosition textFieldLabelPosition, Function3 function3, Function2 function4, Function2 function5, Function2 function6, Function2 function7, Function2 function8, Function2 function9, boolean z, boolean z2, boolean z3, InteractionSource interactionSource, PaddingValues paddingValues, TextFieldColors textFieldColors, Function2 function10, int i, int i2, Composer composer, int i3) {
        CommonDecorationBox(textFieldType, charSequence, function2, textFieldLabelPosition, function3, function4, function5, function6, function7, function8, function9, z, z2, z3, interactionSource, paddingValues, textFieldColors, function10, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    static final Unit Decoration_3J_VO9M$lambda$20(long j, TextStyle textStyle, Function2 function2, int i, Composer composer, int i2) {
        m3450Decoration3JVO9M(j, textStyle, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Decoration_Iv8Zu3U$lambda$21(long j, Function2 function2, int i, Composer composer, int i2) {
        m3451DecorationIv8Zu3U(j, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:376:0x0abf  */
    /* JADX WARN: Code duplicated, block: B:429:0x0d39  */
    /* JADX WARN: Code duplicated, block: B:432:0x0d44  */
    public static final void CommonDecorationBox(final TextFieldType type, final CharSequence visualText, final Function2<? super Composer, ? super Integer, Unit> function2, final TextFieldLabelPosition labelPosition, final Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final Function2<? super Composer, ? super Integer, Unit> function6, final Function2<? super Composer, ? super Integer, Unit> function7, final Function2<? super Composer, ? super Integer, Unit> function8, final Function2<? super Composer, ? super Integer, Unit> function9, final boolean singleLine, final boolean enabled, final boolean isError, final InteractionSource interactionSource, final PaddingValues contentPadding, final TextFieldColors colors, final Function2<? super Composer, ? super Integer, Unit> function10, Composer $composer, final int $changed, final int $changed1) {
        TextFieldColors textFieldColors;
        Composer $composer2;
        boolean z;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        final State labelProgress;
        Function2 function2RememberComposableLambda;
        Function3 function11;
        State prefixSuffixAlpha;
        Function2 function12;
        Function2 decoratedPrefix;
        Function2 function13;
        Function2 decoratedTrailing;
        Function2 decoratedSupporting;
        Composer $composer3;
        Composer $composer4;
        boolean invalid$iv;
        Object value$iv;
        Function2 borderContainerWithId;
        Composer $composer5 = $composer.startRestartGroup(546805032);
        ComposerKt.sourceInformation($composer5, "C(CommonDecorationBox)N(type,visualText,innerTextField,labelPosition,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,singleLine,enabled,isError,interactionSource,contentPadding,colors,container)99@4125L25,109@4463L10,116@4780L8045:TextFieldImpl.kt#mqatfk");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty |= $composer5.changed(type.ordinal()) ? 4 : 2;
        }
        int $dirty2 = ($changed & 48) == 0 ? $dirty | ($composer5.changedInstance(visualText) ? 32 : 16) : $dirty;
        if (($changed & 384) == 0) {
            $dirty2 |= $composer5.changedInstance(function2) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer5.changed(labelPosition) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer5.changedInstance(function3) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty2 |= $composer5.changedInstance(function4) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty2 |= $composer5.changedInstance(function5) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty2 |= $composer5.changedInstance(function6) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty2 |= $composer5.changedInstance(function7) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((805306368 & $changed) == 0) {
            $dirty2 |= $composer5.changedInstance(function8) ? 536870912 : 268435456;
        }
        int $dirty3 = $dirty2;
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer5.changedInstance(function9) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty1 |= $composer5.changed(singleLine) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty1 |= $composer5.changed(enabled) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty1 |= $composer5.changed(isError) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty1 |= $composer5.changed(interactionSource) ? 16384 : 8192;
        }
        if ((196608 & $changed1) == 0) {
            $dirty1 |= $composer5.changed(contentPadding) ? 131072 : 65536;
        }
        if ((1572864 & $changed1) == 0) {
            textFieldColors = colors;
            $dirty1 |= $composer5.changed(textFieldColors) ? 1048576 : 524288;
        } else {
            textFieldColors = colors;
        }
        if (($changed1 & 12582912) == 0) {
            $dirty1 |= $composer5.changedInstance(function10) ? 8388608 : 4194304;
        }
        int $dirty4 = $dirty1;
        if ($composer5.shouldExecute((($dirty3 & 306783379) == 306783378 && (4793491 & $dirty4) == 4793490) ? false : true, $dirty3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(546805032, $dirty3, $dirty4, "androidx.compose.material3.internal.CommonDecorationBox (TextFieldImpl.kt:98)");
            }
            boolean isFocused = FocusInteractionKt.collectIsFocusedAsState(interactionSource, $composer5, ($dirty4 >> 12) & 14).getValue().booleanValue();
            InputPhase inputState = isFocused ? InputPhase.Focused : visualText.length() == 0 ? InputPhase.UnfocusedEmpty : InputPhase.UnfocusedNotEmpty;
            long labelColor = textFieldColors.m3033labelColorXeAY9LY$material3(enabled, isError, isFocused);
            Typography typography = MaterialTheme.INSTANCE.getTypography($composer5, 6);
            final TextStyle bodyLarge = typography.getBodyLarge();
            final TextStyle bodySmall = typography.getBodySmall();
            final boolean overrideLabelTextStyleColor = (Color.m5098equalsimpl0(bodyLarge.m7358getColor0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU()) && !Color.m5098equalsimpl0(bodySmall.m7358getColor0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU())) || (!Color.m5098equalsimpl0(bodyLarge.m7358getColor0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU()) && Color.m5098equalsimpl0(bodySmall.m7358getColor0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU()));
            long $this$CommonDecorationBox_u24lambda_u241 = bodySmall.m7358getColor0d7_KjU();
            if (overrideLabelTextStyleColor) {
                $this$CommonDecorationBox_u24lambda_u241 = ($this$CommonDecorationBox_u24lambda_u241 > 16L ? 1 : ($this$CommonDecorationBox_u24lambda_u241 == 16L ? 0 : -1)) != 0 ? $this$CommonDecorationBox_u24lambda_u241 : labelColor;
            }
            long $this$CommonDecorationBox_u24lambda_u243 = bodyLarge.m7358getColor0d7_KjU();
            if (overrideLabelTextStyleColor) {
                if (!($this$CommonDecorationBox_u24lambda_u243 != 16)) {
                    $this$CommonDecorationBox_u24lambda_u243 = labelColor;
                }
            }
            if (function3 == null || !getShowExpandedLabel(labelPosition)) {
                $this$CommonDecorationBox_u24lambda_u243 = $this$CommonDecorationBox_u24lambda_u243;
                $this$CommonDecorationBox_u24lambda_u243 = $this$CommonDecorationBox_u24lambda_u243;
                z = false;
            } else {
                $this$CommonDecorationBox_u24lambda_u243 = $this$CommonDecorationBox_u24lambda_u243;
                z = true;
            }
            boolean showExpandedLabel$iv = z;
            long focusedLabelTextStyleColor$iv = $this$CommonDecorationBox_u24lambda_u241;
            long unfocusedLabelTextStyleColor$iv = $this$CommonDecorationBox_u24lambda_u243;
            ComposerKt.sourceInformationMarkerStart($composer5, -2132505973, "CC(TextFieldTransitionScope)N(inputState,focusedLabelTextStyleColor:c#ui.graphics.Color,unfocusedLabelTextStyleColor:c#ui.graphics.Color,labelColor:c#ui.graphics.Color,showExpandedLabel,content)385@16247L59,388@16444L14,390@16502L300,398@16874L14,399@16959L14,401@17022L830,424@17903L347,435@18316L14,437@18380L288,449@18771L167,455@18944L150:TextFieldImpl.kt#mqatfk");
            Transition transition$iv = TransitionKt.updateTransition(inputState, "TextFieldInputState", $composer5, (0 & 14) | 48, 0);
            FiniteAnimationSpec labelTransitionSpec$iv = MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, $composer5, 6);
            Function3 transitionSpec$iv$iv = new TextFieldImplKt$TextFieldTransitionScope$labelProgress$1(labelTransitionSpec$iv);
            ComposerKt.sourceInformationMarkerStart($composer5, -1338768149, "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv$iv = (384 & 14) | ((384 << 3) & 896) | ((384 << 3) & 7168) | ((384 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer5, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
            int $changed$iv = ($changed$iv$iv$iv >> 9) & 112;
            InputPhase it$iv = (InputPhase) transition$iv.getCurrentState();
            $composer5.startReplaceGroup(-1436405362);
            ComposerKt.sourceInformation($composer5, "CN(it):TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1436405362, $changed$iv, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:391)");
            }
            switch (WhenMappings.$EnumSwitchMapping$1[it$iv.ordinal()]) {
                case 1:
                    f = 1.0f;
                    break;
                case 2:
                    f = !showExpandedLabel$iv ? 1.0f : 0.0f;
                    break;
                case 3:
                    f = 1.0f;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer5.endReplaceGroup();
            Object initialValue$iv$iv$iv = Float.valueOf(f);
            int $changed$iv2 = ($changed$iv$iv$iv >> 9) & 112;
            InputPhase inputPhase = (InputPhase) transition$iv.getTargetState();
            $composer5.startReplaceGroup(-1436405362);
            ComposerKt.sourceInformation($composer5, "CN(it):TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1436405362, $changed$iv2, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:391)");
            }
            switch (WhenMappings.$EnumSwitchMapping$1[inputPhase.ordinal()]) {
                case 1:
                    f2 = 1.0f;
                    break;
                case 2:
                    f2 = !showExpandedLabel$iv ? 1.0f : 0.0f;
                    break;
                case 3:
                    f2 = 1.0f;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer5.endReplaceGroup();
            Object targetValue$iv$iv$iv = Float.valueOf(f2);
            final State labelProgress$iv = TransitionKt.createTransitionAnimation(transition$iv, initialValue$iv$iv$iv, targetValue$iv$iv$iv, transitionSpec$iv$iv.invoke(transition$iv.getSegment(), $composer5, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112)), vectorConverter, "LabelProgress", $composer5, ($changed$iv$iv$iv & 14) | (($changed$iv$iv$iv << 9) & 57344) | (($changed$iv$iv$iv << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer5);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            FiniteAnimationSpec fastOpacityTransitionSpec$iv = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer5, 6);
            FiniteAnimationSpec slowOpacityTransitionSpec$iv = MotionSchemeKt.value(MotionSchemeKeyTokens.SlowEffects, $composer5, 6);
            Function3 transitionSpec$iv$iv2 = new TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1(fastOpacityTransitionSpec$iv, slowOpacityTransitionSpec$iv);
            ComposerKt.sourceInformationMarkerStart($composer5, -1338768149, "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv$iv2 = (384 & 14) | ((384 << 3) & 896) | ((384 << 3) & 7168) | ((384 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer5, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
            int $changed$iv3 = ($changed$iv$iv$iv2 >> 9) & 112;
            InputPhase it$iv2 = (InputPhase) transition$iv.getCurrentState();
            $composer5.startReplaceGroup(-1093194547);
            ComposerKt.sourceInformation($composer5, "CN(it):TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1093194547, $changed$iv3, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:416)");
            }
            switch (WhenMappings.$EnumSwitchMapping$1[it$iv2.ordinal()]) {
                case 1:
                    f3 = 1.0f;
                    break;
                case 2:
                    f3 = !showExpandedLabel$iv ? 1.0f : 0.0f;
                    break;
                case 3:
                    f3 = 0.0f;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer5.endReplaceGroup();
            Object initialValue$iv$iv$iv2 = Float.valueOf(f3);
            int $changed$iv4 = ($changed$iv$iv$iv2 >> 9) & 112;
            InputPhase it$iv3 = (InputPhase) transition$iv.getTargetState();
            $composer5.startReplaceGroup(-1093194547);
            ComposerKt.sourceInformation($composer5, "CN(it):TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1093194547, $changed$iv4, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:416)");
            }
            switch (WhenMappings.$EnumSwitchMapping$1[it$iv3.ordinal()]) {
                case 1:
                    f4 = 1.0f;
                    break;
                case 2:
                    f4 = !showExpandedLabel$iv ? 1.0f : 0.0f;
                    break;
                case 3:
                    f4 = 0.0f;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer5.endReplaceGroup();
            Object targetValue$iv$iv$iv2 = Float.valueOf(f4);
            final State placeholderOpacity$iv = TransitionKt.createTransitionAnimation(transition$iv, initialValue$iv$iv$iv2, targetValue$iv$iv$iv2, transitionSpec$iv$iv2.invoke(transition$iv.getSegment(), $composer5, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112)), vectorConverter2, "PlaceholderOpacity", $composer5, ($changed$iv$iv$iv2 & 14) | (($changed$iv$iv$iv2 << 9) & 57344) | (($changed$iv$iv$iv2 << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer5);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            Function3 transitionSpec$iv$iv3 = new TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1(fastOpacityTransitionSpec$iv);
            ComposerKt.sourceInformationMarkerStart($composer5, -1338768149, "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter3 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv$iv3 = (384 & 14) | ((384 << 3) & 896) | ((384 << 3) & 7168) | ((384 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer5, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
            int $changed$iv5 = ($changed$iv$iv$iv3 >> 9) & 112;
            InputPhase it$iv4 = (InputPhase) transition$iv.getCurrentState();
            $composer5.startReplaceGroup(-1258455321);
            ComposerKt.sourceInformation($composer5, "CN(it):TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1258455321, $changed$iv5, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:428)");
            }
            switch (WhenMappings.$EnumSwitchMapping$1[it$iv4.ordinal()]) {
                case 1:
                    f5 = 1.0f;
                    break;
                case 2:
                    f5 = !showExpandedLabel$iv ? 1.0f : 0.0f;
                    break;
                case 3:
                    f5 = 1.0f;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer5.endReplaceGroup();
            Object initialValue$iv$iv$iv3 = Float.valueOf(f5);
            int $changed$iv6 = ($changed$iv$iv$iv3 >> 9) & 112;
            InputPhase it$iv5 = (InputPhase) transition$iv.getTargetState();
            $composer5.startReplaceGroup(-1258455321);
            ComposerKt.sourceInformation($composer5, "CN(it):TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1258455321, $changed$iv6, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:428)");
            }
            switch (WhenMappings.$EnumSwitchMapping$1[it$iv5.ordinal()]) {
                case 1:
                    f6 = 1.0f;
                    break;
                case 2:
                    f6 = !showExpandedLabel$iv ? 1.0f : 0.0f;
                    break;
                case 3:
                    f6 = 1.0f;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer5.endReplaceGroup();
            Object targetValue$iv$iv$iv3 = Float.valueOf(f6);
            final State prefixSuffixOpacity$iv = TransitionKt.createTransitionAnimation(transition$iv, initialValue$iv$iv$iv3, targetValue$iv$iv$iv3, transitionSpec$iv$iv3.invoke(transition$iv.getSegment(), $composer5, Integer.valueOf(($changed$iv$iv$iv3 >> 3) & 112)), vectorConverter3, "PrefixSuffixOpacity", $composer5, ($changed$iv$iv$iv3 & 14) | (($changed$iv$iv$iv3 << 9) & 57344) | (($changed$iv$iv$iv3 << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer5);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            FiniteAnimationSpec colorTransitionSpec$iv = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer5, 6);
            Function3 transitionSpec$iv$iv4 = new TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1(colorTransitionSpec$iv);
            ComposerKt.sourceInformationMarkerStart($composer5, -1939694975, "CC(animateColor)P(2)67@3229L31,68@3296L58,70@3367L70:Transition.kt#xbi5r1");
            int $changed$iv7 = (384 >> 6) & 112;
            InputPhase it$iv6 = (InputPhase) transition$iv.getTargetState();
            $composer5.startReplaceGroup(-12973394);
            ComposerKt.sourceInformation($composer5, "CN(it):TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-12973394, $changed$iv7, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:441)");
            }
            long j = WhenMappings.$EnumSwitchMapping$1[it$iv6.ordinal()] == 1 ? focusedLabelTextStyleColor$iv : unfocusedLabelTextStyleColor$iv;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer5.endReplaceGroup();
            ColorSpace colorSpace$iv$iv = Color.m5101getColorSpaceimpl(j);
            ComposerKt.sourceInformationMarkerStart($composer5, 1918408359, "CC(remember):Transition.kt#9igjgp");
            boolean invalid$iv$iv$iv = $composer5.changed(colorSpace$iv$iv);
            Object value$iv$iv$iv = $composer5.rememberedValue();
            if (invalid$iv$iv$iv || value$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(colorSpace$iv$iv);
                $composer5.updateRememberedValue(value$iv$iv$iv);
            }
            TwoWayConverter typeConverter$iv$iv = (TwoWayConverter) value$iv$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer5);
            int $changed$iv$iv$iv4 = (
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0748: ARITH (r10v26 '$changed$iv$iv$iv4' int) = (wrap int:0x0743: ARITH (wrap int:0x073e: ARITH (wrap int:0x0738: ARITH (r10v18 int) & (14 int) A[WRAPPED] (LINE:627)) | (wrap int:0x073c: ARITH (wrap int:0x073a: ARITH (r10v18 int) << (3 int) A[WRAPPED]) & (896 int) A[WRAPPED]) A[DONT_WRAP, WRAPPED] (LINE:627)) | (wrap int:0x0741: ARITH (wrap int:0x073f: ARITH (r10v18 int) << (3 int) A[WRAPPED]) & (7168 int) A[WRAPPED]) A[DONT_WRAP, WRAPPED] (LINE:627)) | (wrap int:0x0746: ARITH (wrap int:0x0744: ARITH (384 int) << (3 int) A[WRAPPED] (LINE:558)) & (57344 int) A[WRAPPED] (LINE:558)) A[DECLARE_VAR] (LINE:558) in method: androidx.compose.material3.internal.TextFieldImplKt.CommonDecorationBox(androidx.compose.material3.internal.TextFieldType, java.lang.CharSequence, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, androidx.compose.material3.TextFieldLabelPosition, kotlin.jvm.functions.Function3<? super androidx.compose.material3.TextFieldLabelScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, boolean, boolean, boolean, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.foundation.layout.PaddingValues, androidx.compose.material3.TextFieldColors, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, androidx.compose.runtime.Composer, int, int):void, file: classes10.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r10v18 int
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                */
            /*
                Method dump skipped, instruction units count: 3792
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.TextFieldImplKt.CommonDecorationBox(androidx.compose.material3.internal.TextFieldType, java.lang.CharSequence, kotlin.jvm.functions.Function2, androidx.compose.material3.TextFieldLabelPosition, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, boolean, boolean, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.foundation.layout.PaddingValues, androidx.compose.material3.TextFieldColors, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
        }

        private static final boolean CommonDecorationBox$lambda$18$lambda$8(State<Boolean> state) {
            Object thisObj$iv = state.getValue();
            return ((Boolean) thisObj$iv).booleanValue();
        }

        static final boolean CommonDecorationBox$lambda$18$lambda$7$lambda$6(State $placeholderAlpha) {
            return ((Number) $placeholderAlpha.getValue()).floatValue() > 0.0f;
        }

        private static final boolean CommonDecorationBox$lambda$18$lambda$11(State<Boolean> state) {
            Object thisObj$iv = state.getValue();
            return ((Boolean) thisObj$iv).booleanValue();
        }

        static final boolean CommonDecorationBox$lambda$18$lambda$10$lambda$9(State $prefixSuffixAlpha) {
            return ((Number) $prefixSuffixAlpha.getValue()).floatValue() > 0.0f;
        }

        /* JADX WARN: Code duplicated, block: B:17:0x0080  */
        static final Unit CommonDecorationBox$lambda$18$lambda$17$lambda$16(TextFieldLabelPosition $labelPosition, State $labelProgress, MutableState $cutoutSize, Size it) {
            if ($labelPosition instanceof TextFieldLabelPosition.Above) {
                return Unit.INSTANCE;
            }
            float progress = ((Number) $labelProgress.getValue()).floatValue();
            long arg0$iv = it.m4926unboximpl();
            int bits$iv$iv$iv = (int) (arg0$iv >> 32);
            float labelWidth = Float.intBitsToFloat(bits$iv$iv$iv) * progress;
            long arg0$iv2 = it.m4926unboximpl();
            int bits$iv$iv$iv2 = (int) (arg0$iv2 & 4294967295L);
            float labelHeight = Float.intBitsToFloat(bits$iv$iv$iv2) * progress;
            long arg0$iv3 = ((Size) $cutoutSize.getValue()).m4926unboximpl();
            int bits$iv$iv$iv3 = (int) (arg0$iv3 >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv3) == labelWidth) {
                long arg0$iv4 = ((Size) $cutoutSize.getValue()).m4926unboximpl();
                int bits$iv$iv$iv4 = (int) (arg0$iv4 & 4294967295L);
                if (!(Float.intBitsToFloat(bits$iv$iv$iv4) == labelHeight)) {
                    long v1$iv$iv = Float.floatToRawIntBits(labelWidth);
                    long v2$iv$iv = Float.floatToRawIntBits(labelHeight);
                    $cutoutSize.setValue(Size.m4909boximpl(Size.m4912constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv))));
                }
            } else {
                long v1$iv$iv2 = Float.floatToRawIntBits(labelWidth);
                long v2$iv$iv2 = Float.floatToRawIntBits(labelHeight);
                $cutoutSize.setValue(Size.m4909boximpl(Size.m4912constructorimpl((v1$iv$iv2 << 32) | (4294967295L & v2$iv$iv2))));
            }
            return Unit.INSTANCE;
        }

        private static final boolean getShowExpandedLabel(TextFieldLabelPosition $this$showExpandedLabel) {
            return ($this$showExpandedLabel instanceof TextFieldLabelPosition.Attached) && !((TextFieldLabelPosition.Attached) $this$showExpandedLabel).getAlwaysMinimize();
        }

        public static final Alignment.Horizontal getMinimizedAlignment(TextFieldLabelPosition $this$minimizedAlignment) {
            if ($this$minimizedAlignment instanceof TextFieldLabelPosition.Above) {
                return ((TextFieldLabelPosition.Above) $this$minimizedAlignment).getAlignment();
            }
            if ($this$minimizedAlignment instanceof TextFieldLabelPosition.Attached) {
                return ((TextFieldLabelPosition.Attached) $this$minimizedAlignment).getMinimizedAlignment();
            }
            throw new IllegalArgumentException("Unknown position: " + $this$minimizedAlignment);
        }

        public static final Alignment.Horizontal getExpandedAlignment(TextFieldLabelPosition $this$expandedAlignment) {
            if ($this$expandedAlignment instanceof TextFieldLabelPosition.Above) {
                return ((TextFieldLabelPosition.Above) $this$expandedAlignment).getAlignment();
            }
            if ($this$expandedAlignment instanceof TextFieldLabelPosition.Attached) {
                return ((TextFieldLabelPosition.Attached) $this$expandedAlignment).getExpandedAlignment();
            }
            throw new IllegalArgumentException("Unknown position: " + $this$expandedAlignment);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Decoration-3J-VO9M, reason: not valid java name */
        public static final void m3450Decoration3JVO9M(final long contentColor, final TextStyle textStyle, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
            Function2<? super Composer, ? super Integer, Unit> function3;
            Composer $composer2 = $composer.startRestartGroup(396611577);
            ComposerKt.sourceInformation($composer2, "C(Decoration)N(contentColor:c#ui.graphics.Color,textStyle,content)325@13794L62:TextFieldImpl.kt#mqatfk");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer2.changed(contentColor) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer2.changed(textStyle) ? 32 : 16;
            }
            if (($changed & 384) == 0) {
                function3 = function2;
                $dirty |= $composer2.changedInstance(function3) ? 256 : 128;
            } else {
                function3 = function2;
            }
            int $dirty2 = $dirty;
            if ($composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(396611577, $dirty2, -1, "androidx.compose.material3.internal.Decoration (TextFieldImpl.kt:325)");
                }
                ProvideContentColorTextStyleKt.m3367ProvideContentColorTextStyle3JVO9M(contentColor, textStyle, function3, $composer2, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.internal.TextFieldImplKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return TextFieldImplKt.Decoration_3J_VO9M$lambda$20(contentColor, textStyle, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: Decoration-Iv8Zu3U, reason: not valid java name */
        public static final void m3451DecorationIv8Zu3U(final long contentColor, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
            Composer $composer2 = $composer.startRestartGroup(590397809);
            ComposerKt.sourceInformation($composer2, "C(Decoration)N(contentColor:c#ui.graphics.Color,content)330@14001L84:TextFieldImpl.kt#mqatfk");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer2.changed(contentColor) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
            }
            if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(590397809, $dirty, -1, "androidx.compose.material3.internal.Decoration (TextFieldImpl.kt:330)");
                }
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(contentColor)), function2, $composer2, ProvidedValue.$stable | ($dirty & 112));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.internal.TextFieldImplKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return TextFieldImplKt.Decoration_Iv8Zu3U$lambda$21(contentColor, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        public static final Modifier defaultErrorSemantics(Modifier $this$defaultErrorSemantics, boolean isError, final String defaultErrorMessage) {
            return isError ? SemanticsModifierKt.semantics$default($this$defaultErrorSemantics, false, new Function1() { // from class: androidx.compose.material3.internal.TextFieldImplKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TextFieldImplKt.defaultErrorSemantics$lambda$22(defaultErrorMessage, (SemanticsPropertyReceiver) obj);
                }
            }, 1, null) : $this$defaultErrorSemantics;
        }

        static final Unit defaultErrorSemantics$lambda$22(String $defaultErrorMessage, SemanticsPropertyReceiver $this$semantics) {
            SemanticsPropertiesKt.error($this$semantics, $defaultErrorMessage);
            return Unit.INSTANCE;
        }

        public static final Modifier textFieldBackground(Modifier $this$textFieldBackground, final ColorProducer color, final Shape shape) {
            return DrawModifierKt.drawWithCache($this$textFieldBackground, new Function1() { // from class: androidx.compose.material3.internal.TextFieldImplKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TextFieldImplKt.textFieldBackground$lambda$24(shape, color, (CacheDrawScope) obj);
                }
            });
        }

        static final DrawResult textFieldBackground$lambda$24(Shape $shape, final ColorProducer $color, CacheDrawScope $this$drawWithCache) {
            final Outline outline = $shape.mo321createOutlinePq9zytI($this$drawWithCache.m4632getSizeNHjbRc(), $this$drawWithCache.getLayoutDirection(), $this$drawWithCache);
            return $this$drawWithCache.onDrawBehind(new Function1() { // from class: androidx.compose.material3.internal.TextFieldImplKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TextFieldImplKt.textFieldBackground$lambda$24$lambda$23(outline, $color, (DrawScope) obj);
                }
            });
        }

        static final Unit textFieldBackground$lambda$24$lambda$23(Outline $outline, ColorProducer $color, DrawScope $this$onDrawBehind) {
            OutlineKt.m5369drawOutlinewDX37Ww($this$onDrawBehind, $outline, $color.mo1797invoke0d7_KjU(), (60 & 4) != 0 ? 1.0f : 0.0f, (60 & 8) != 0 ? Fill.INSTANCE : null, (60 & 16) != 0 ? null : null, (60 & 32) != 0 ? DrawScope.INSTANCE.m5673getDefaultBlendMode0nO6VwU() : 0);
            return Unit.INSTANCE;
        }

        public static final Modifier textFieldLabelMinHeight(Modifier $this$textFieldLabelMinHeight, final Function0<Dp> function0) {
            return LayoutModifierKt.layout($this$textFieldLabelMinHeight, new Function3() { // from class: androidx.compose.material3.internal.TextFieldImplKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TextFieldImplKt.textFieldLabelMinHeight$lambda$26(function0, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                }
            });
        }

        static final MeasureResult textFieldLabelMinHeight$lambda$26(Function0 $minHeight, MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
            float minHeight = ((Dp) $minHeight.invoke()).m7919unboximpl();
            int resolvedMinHeight = ConstraintsKt.m7874constrainHeightK40F9xA(constraints.getValue(), !Dp.m7910equalsimpl0(minHeight, Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM()) ? $this$layout.mo400roundToPx0680j_4(minHeight) : 0);
            long value = constraints.getValue();
            final Placeable placeable = measurable.mo6541measureBRTryo0(Constraints.m7847copyZbe2FdA(value, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(value) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(value) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(value) : resolvedMinHeight, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(value) : 0));
            return MeasureScope.layout$default($this$layout, placeable.getWidth(), placeable.getHeight(), null, new Function1() { // from class: androidx.compose.material3.internal.TextFieldImplKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TextFieldImplKt.textFieldLabelMinHeight$lambda$26$lambda$25(placeable, (Placeable.PlacementScope) obj);
                }
            }, 4, null);
        }

        static final Unit textFieldLabelMinHeight$lambda$26$lambda$25(Placeable $placeable, Placeable.PlacementScope $this$layout) {
            Placeable.PlacementScope.place$default($this$layout, $placeable, 0, 0, 0.0f, 4, null);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: TextFieldTransitionScope-Jy8F4Js, reason: not valid java name */
        private static final void m3452TextFieldTransitionScopeJy8F4Js(InputPhase inputState, long focusedLabelTextStyleColor, long unfocusedLabelTextStyleColor, long labelColor, boolean showExpandedLabel, Function7<? super State<Float>, ? super State<Color>, ? super State<Color>, ? super State<Float>, ? super State<Float>, ? super Composer, ? super Integer, Unit> function7, Composer $composer, int $changed) {
            float f;
            float f2;
            float f3;
            float f4;
            float f5;
            ComposerKt.sourceInformationMarkerStart($composer, -2132505973, "CC(TextFieldTransitionScope)N(inputState,focusedLabelTextStyleColor:c#ui.graphics.Color,unfocusedLabelTextStyleColor:c#ui.graphics.Color,labelColor:c#ui.graphics.Color,showExpandedLabel,content)385@16247L59,388@16444L14,390@16502L300,398@16874L14,399@16959L14,401@17022L830,424@17903L347,435@18316L14,437@18380L288,449@18771L167,455@18944L150:TextFieldImpl.kt#mqatfk");
            Transition transition = TransitionKt.updateTransition(inputState, "TextFieldInputState", $composer, ($changed & 14) | 48, 0);
            FiniteAnimationSpec labelTransitionSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, $composer, 6);
            Function3 transitionSpec$iv = new TextFieldImplKt$TextFieldTransitionScope$labelProgress$1(labelTransitionSpec);
            ComposerKt.sourceInformationMarkerStart($composer, -1338768149, "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv = (384 & 14) | ((384 << 3) & 896) | ((384 << 3) & 7168) | ((384 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
            int $changed2 = ($changed$iv$iv >> 9) & 112;
            InputPhase it = (InputPhase) transition.getCurrentState();
            $composer.startReplaceGroup(-1436405362);
            ComposerKt.sourceInformation($composer, "CN(it):TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1436405362, $changed2, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:391)");
            }
            float f6 = 0.0f;
            switch (WhenMappings.$EnumSwitchMapping$1[it.ordinal()]) {
                case 1:
                    f = 1.0f;
                    break;
                case 2:
                    f = !showExpandedLabel ? 1.0f : 0.0f;
                    break;
                case 3:
                    f = 1.0f;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            Object initialValue$iv$iv = Float.valueOf(f);
            int $changed3 = ($changed$iv$iv >> 9) & 112;
            InputPhase inputPhase = (InputPhase) 
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00d1: CHECK_CAST (r2v10 'inputPhase' androidx.compose.material3.internal.InputPhase) = (androidx.compose.material3.internal.InputPhase) (wrap java.lang.Object:0x00c9: INVOKE (r0v7 ?? I:??[OBJECT, ARRAY]) VIRTUAL call: androidx.compose.animation.core.Transition.getTargetState():java.lang.Object A[MD:():S (m), WRAPPED] (LINE:692)) in method: androidx.compose.material3.internal.TextFieldImplKt.TextFieldTransitionScope-Jy8F4Js(androidx.compose.material3.internal.InputPhase, long, long, long, boolean, kotlin.jvm.functions.Function7<? super androidx.compose.runtime.State<java.lang.Float>, ? super androidx.compose.runtime.State<androidx.compose.ui.graphics.Color>, ? super androidx.compose.runtime.State<androidx.compose.ui.graphics.Color>, ? super androidx.compose.runtime.State<java.lang.Float>, ? super androidx.compose.runtime.State<java.lang.Float>, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, androidx.compose.runtime.Composer, int):void, file: classes10.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r0v7 ??
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                */
            /*
                Method dump skipped, instruction units count: 1846
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.TextFieldImplKt.m3452TextFieldTransitionScopeJy8F4Js(androidx.compose.material3.internal.InputPhase, long, long, long, boolean, kotlin.jvm.functions.Function7, androidx.compose.runtime.Composer, int):void");
        }

        /* JADX INFO: renamed from: animateBorderStrokeAsState-NuRrP5Q, reason: not valid java name */
        public static final State<BorderStroke> m3455animateBorderStrokeAsStateNuRrP5Q(boolean enabled, boolean isError, boolean focused, TextFieldColors colors, float focusedBorderThickness, float unfocusedBorderThickness, Composer $composer, int $changed) {
            State<Color> stateRememberUpdatedState;
            State<Dp> stateRememberUpdatedState2;
            Composer composer = $composer;
            ComposerKt.sourceInformationMarkerStart(composer, 2047013045, "C(animateBorderStrokeAsState)N(enabled,isError,focused,colors,focusedBorderThickness:c#ui.unit.Dp,unfocusedBorderThickness:c#ui.unit.Dp)475@19543L14,483@19812L11,492@20135L73:TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2047013045, $changed, -1, "androidx.compose.material3.internal.animateBorderStrokeAsState (TextFieldImpl.kt:472)");
            }
            long targetColor = colors.m3032indicatorColorXeAY9LY$material3(enabled, isError, focused);
            FiniteAnimationSpec colorAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, composer, 6);
            if (!enabled) {
                composer.startReplaceGroup(-1674427244);
                ComposerKt.sourceInformation(composer, "480@19700L33");
                stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m5087boximpl(targetColor), composer, 0);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(-1674507999);
                ComposerKt.sourceInformation(composer, "478@19618L52");
                stateRememberUpdatedState = SingleValueAnimationKt.m135animateColorAsStateeuL9pac(targetColor, colorAnimationSpec, null, null, $composer, 0, 12);
                composer = $composer;
                composer.endReplaceGroup();
            }
            State<Color> state = stateRememberUpdatedState;
            FiniteAnimationSpec thicknessAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composer, 6);
            if (enabled) {
                composer.startReplaceGroup(-1674245832);
                ComposerKt.sourceInformation(composer, "487@19979L57");
                float targetThickness = focused ? focusedBorderThickness : unfocusedBorderThickness;
                stateRememberUpdatedState2 = AnimateAsStateKt.m162animateDpAsStateAjpBEmI(targetThickness, thicknessAnimationSpec, null, null, composer, 0, 12);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(-1674063769);
                ComposerKt.sourceInformation(composer, "489@20066L46");
                stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(Dp.m7903boximpl(unfocusedBorderThickness), composer, ($changed >> 15) & 14);
                composer.endReplaceGroup();
            }
            State<BorderStroke> stateRememberUpdatedState3 = SnapshotStateKt.rememberUpdatedState(BorderStrokeKt.m291BorderStrokecXLIe8U(stateRememberUpdatedState2.getValue().m7919unboximpl(), state.getValue().m5107unboximpl()), composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            return stateRememberUpdatedState3;
        }

        public static final float textFieldHorizontalIconPadding(Composer $composer, int $changed) {
            ComposerKt.sourceInformationMarkerStart($composer, 1986450462, "C(textFieldHorizontalIconPadding)521@21199L7:TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1986450462, $changed, -1, "androidx.compose.material3.internal.textFieldHorizontalIconPadding (TextFieldImpl.kt:520)");
            }
            ProvidableCompositionLocal<Dp> localMinimumInteractiveComponentSize = InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localMinimumInteractiveComponentSize);
            ComposerKt.sourceInformationMarkerEnd($composer);
            float interactiveSizeOrNaN = ((Dp) objConsume).m7919unboximpl();
            float interactiveSize = Float.isNaN(interactiveSizeOrNaN) ? Dp.m7905constructorimpl(0) : interactiveSizeOrNaN;
            float other$iv = SmallIconButtonTokens.INSTANCE.m4096getIconSizeD9Ej5fM();
            float arg0$iv = Dp.m7905constructorimpl(Dp.m7905constructorimpl(interactiveSize - other$iv) / 2);
            float minimumValue$iv = Dp.m7905constructorimpl(0);
            float $this$coerceAtLeast_u2dYgX7TsA$iv = Dp.m7905constructorimpl(RangesKt.coerceAtLeast(arg0$iv, minimumValue$iv));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            return $this$coerceAtLeast_u2dYgX7TsA$iv;
        }

        public static final float minimizedLabelHalfHeight(Composer $composer, int $changed) {
            ComposerKt.sourceInformationMarkerStart($composer, 1251545215, "C(minimizedLabelHalfHeight)528@21499L10,531@21709L7:TextFieldImpl.kt#mqatfk");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1251545215, $changed, -1, "androidx.compose.material3.internal.minimizedLabelHalfHeight (TextFieldImpl.kt:527)");
            }
            long compositionLocalValue = MaterialTheme.INSTANCE.getTypography($composer, 6).getBodySmall().m7367getLineHeightXSAIIZE();
            long fallbackValue = TypeScaleTokens.INSTANCE.m4205getBodySmallLineHeightXSAIIZE();
            long value = TextUnit.m8102isSpimpl(compositionLocalValue) ? compositionLocalValue : fallbackValue;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Density $this$minimizedLabelHalfHeight_u24lambda_u2432 = (Density) objConsume;
            float arg0$iv = $this$minimizedLabelHalfHeight_u24lambda_u2432.mo401toDpGaN1DYA(value);
            float arg0$iv2 = Dp.m7905constructorimpl(arg0$iv / 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            return arg0$iv2;
        }

        public static final float getTextFieldPadding() {
            return TextFieldPadding;
        }

        public static final float getAboveLabelHorizontalPadding() {
            return AboveLabelHorizontalPadding;
        }

        public static final float getAboveLabelBottomPadding() {
            return AboveLabelBottomPadding;
        }

        public static final float getSupportingTopPadding() {
            return SupportingTopPadding;
        }

        public static final float getPrefixSuffixTextPadding() {
            return PrefixSuffixTextPadding;
        }

        public static final float getMinTextLineHeight() {
            return MinTextLineHeight;
        }

        public static final float getMinFocusedLabelLineHeight() {
            return MinFocusedLabelLineHeight;
        }

        public static final float getMinSupportingTextLineHeight() {
            return MinSupportingTextLineHeight;
        }
    }
