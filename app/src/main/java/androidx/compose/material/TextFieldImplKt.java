package androidx.compose.material;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutIdParentData;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.profileinstaller.ProfileVerifier;
import com.cloudinary.metadata.MetadataField;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextFieldImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aÙ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\b2\u0006\u0010\t\u001a\u00020\n2\u0013\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0013\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\bH\u0001¢\u0006\u0002\u0010\u001c\u001aQ\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2 \u0010$\u001a\u001c\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\b¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\u0003\u0010\u0000H\u0001¢\u0006\u0004\b'\u0010(\u001a\u001c\u0010)\u001a\u00020**\u00020*2\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010+\u001a\u00020\u0005H\u0000\u001a\u0012\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0000\u001a\u0012\u00100\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0000\"\u001a\u00101\u001a\u0004\u0018\u000102*\u0002038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b4\u00105\"\u000e\u00106\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u00107\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u00108\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u00109\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010:\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010;\u001a\u00020-X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010<\u001a\u00020-X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010=\u001a\u00020-X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010>\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010B\u001a\u0004\b@\u0010A\"\u0016\u0010C\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010B\u001a\u0004\bD\u0010A¨\u0006E"}, d2 = {"CommonDecorationBox", "", "type", "Landroidx/compose/material/TextFieldType;", "value", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", MetadataField.LABEL, "placeholder", "leadingIcon", "trailingIcon", "singleLine", "", "enabled", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material/TextFieldColors;", OutlinedTextFieldKt.BorderId, "(Landroidx/compose/material/TextFieldType;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Decoration", "contentColor", "Landroidx/compose/ui/graphics/Color;", "typography", "Landroidx/compose/ui/text/TextStyle;", "contentAlpha", "", UriUtil.LOCAL_CONTENT_SCHEME, "Landroidx/compose/runtime/ComposableOpenTarget;", "index", "Decoration-euL9pac", "(JLandroidx/compose/ui/text/TextStyle;Ljava/lang/Float;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "defaultErrorSemantics", "Landroidx/compose/ui/Modifier;", "defaultErrorMessage", "widthOrZero", "", "placeable", "Landroidx/compose/ui/layout/Placeable;", "heightOrZero", "layoutId", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "getLayoutId", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;)Ljava/lang/Object;", "TextFieldId", "PlaceholderId", "LabelId", "LeadingId", "TrailingId", "AnimationDuration", "PlaceholderAnimationDuration", "PlaceholderAnimationDelayOrDuration", "TextFieldPadding", "Landroidx/compose/ui/unit/Dp;", "getTextFieldPadding", "()F", "F", "HorizontalIconPadding", "getHorizontalIconPadding", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextFieldImplKt {
    public static final int AnimationDuration = 150;
    public static final String LabelId = "Label";
    public static final String LeadingId = "Leading";
    private static final int PlaceholderAnimationDelayOrDuration = 67;
    private static final int PlaceholderAnimationDuration = 83;
    public static final String PlaceholderId = "Hint";
    public static final String TextFieldId = "TextField";
    public static final String TrailingId = "Trailing";
    private static final float TextFieldPadding = Dp.m7905constructorimpl(16);
    private static final float HorizontalIconPadding = Dp.m7905constructorimpl(12);

    static final Unit CommonDecorationBox$lambda$3(TextFieldType textFieldType, String str, Function2 function2, VisualTransformation visualTransformation, Function2 function3, Function2 function4, Function2 function5, Function2 function6, boolean z, boolean z2, boolean z3, InteractionSource interactionSource, PaddingValues paddingValues, Shape shape, TextFieldColors textFieldColors, Function2 function7, int i, int i2, Composer composer, int i3) {
        CommonDecorationBox(textFieldType, str, function2, visualTransformation, function3, function4, function5, function6, z, z2, z3, interactionSource, paddingValues, shape, textFieldColors, function7, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    static final Unit Decoration_euL9pac$lambda$1(long j, TextStyle textStyle, Float f, Function2 function2, int i, int i2, Composer composer, int i3) {
        m2003DecorationeuL9pac(j, textStyle, f, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void CommonDecorationBox(final TextFieldType type, final String value, final Function2<? super Composer, ? super Integer, Unit> function2, final VisualTransformation visualTransformation, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final Function2<? super Composer, ? super Integer, Unit> function6, final boolean singleLine, final boolean enabled, final boolean isError, final InteractionSource interactionSource, final PaddingValues contentPadding, final Shape shape, final TextFieldColors colors, final Function2<? super Composer, ? super Integer, Unit> function7, Composer $composer, final int $changed, final int $changed1) {
        Function2<? super Composer, ? super Integer, Unit> function8;
        Function2<? super Composer, ? super Integer, Unit> function9;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Composer $composer2;
        InputPhase inputState;
        long $this$takeOrElse_u2dDxMtmZc$iv;
        long $this$takeOrElse_u2dDxMtmZc$iv2;
        Composer $composer3 = $composer.startRestartGroup(418608794);
        ComposerKt.sourceInformation($composer3, "C(CommonDecorationBox)N(type,value,innerTextField,visualTransformation,label,placeholder,leadingIcon,trailingIcon,singleLine,enabled,isError,interactionSource,contentPadding,shape,colors,border)80@3060L121,86@3257L25,106@3942L10,116@4385L10,121@4612L10,127@4852L4436,113@4277L5011:TextFieldImpl.kt#jmzs0o");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(type.ordinal()) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(value) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            function8 = function2;
            $dirty |= $composer3.changedInstance(function8) ? 256 : 128;
        } else {
            function8 = function2;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(visualTransformation) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function9 = function4;
            $dirty |= $composer3.changedInstance(function9) ? 131072 : 65536;
        } else {
            function9 = function4;
        }
        if (($changed & 1572864) == 0) {
            function10 = function5;
            $dirty |= $composer3.changedInstance(function10) ? 1048576 : 524288;
        } else {
            function10 = function5;
        }
        if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changedInstance(function6) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(singleLine) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changed(enabled) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(isError) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changed(interactionSource) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty1 |= $composer3.changed(contentPadding) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty1 |= $composer3.changed(shape) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty1 |= $composer3.changed(colors) ? 16384 : 8192;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= $composer3.changedInstance(function7) ? 131072 : 65536;
        }
        if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && (74899 & $dirty1) == 74898) ? false : true, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(418608794, $dirty, $dirty1, "androidx.compose.material.CommonDecorationBox (TextFieldImpl.kt:78)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1443855405, "CC(remember):TextFieldImpl.kt#9igjgp");
            boolean invalid$iv = (($dirty & 112) == 32) | (($dirty & 7168) == 2048);
            Object value$iv = $composer3.rememberedValue();
            if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = visualTransformation.filter(new AnnotatedString(value, null, 2, null));
                $composer3.updateRememberedValue(value$iv);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            String transformedText = ((TransformedText) value$iv).getText().getText();
            boolean isFocused = FocusInteractionKt.collectIsFocusedAsState(interactionSource, $composer3, ($dirty1 >> 3) & 14).getValue().booleanValue();
            if (isFocused) {
                inputState = InputPhase.Focused;
            } else {
                inputState = transformedText.length() == 0 ? InputPhase.UnfocusedEmpty : InputPhase.UnfocusedNotEmpty;
            }
            Function3<InputPhase, Composer, Integer, Color> function11 = new Function3<InputPhase, Composer, Integer, Color>() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$labelColor$1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Color invoke(InputPhase inputPhase, Composer composer, Integer num) {
                    return Color.m5087boximpl(m2005invokeXeAY9LY(inputPhase, composer, num.intValue()));
                }

                /* JADX INFO: renamed from: invoke-XeAY9LY, reason: not valid java name */
                public final long m2005invokeXeAY9LY(InputPhase it, Composer $composer4, int $changed2) {
                    $composer4.startReplaceGroup(1423138213);
                    ComposerKt.sourceInformation($composer4, "CN(it)96@3582L298:TextFieldImpl.kt#jmzs0o");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1423138213, $changed2, -1, "androidx.compose.material.CommonDecorationBox.<anonymous> (TextFieldImpl.kt:95)");
                    }
                    long jM5107unboximpl = colors.labelColor(enabled, it == InputPhase.UnfocusedEmpty ? false : isError, interactionSource, $composer4, 0).getValue().m5107unboximpl();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer4.endReplaceGroup();
                    return jM5107unboximpl;
                }
            };
            Typography typography = MaterialTheme.INSTANCE.getTypography($composer3, 6);
            TextStyle subtitle1 = typography.getSubtitle1();
            TextStyle caption = typography.getCaption();
            boolean shouldOverrideTextStyleColor = (Color.m5098equalsimpl0(subtitle1.m7358getColor0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU()) && !Color.m5098equalsimpl0(caption.m7358getColor0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU())) || (!Color.m5098equalsimpl0(subtitle1.m7358getColor0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU()) && Color.m5098equalsimpl0(caption.m7358getColor0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU()));
            TextFieldTransitionScope textFieldTransitionScope = TextFieldTransitionScope.INSTANCE;
            $composer3.startReplaceGroup(-1443813555);
            ComposerKt.sourceInformation($composer3, "");
            long $this$CommonDecorationBox_u24lambda_u241 = MaterialTheme.INSTANCE.getTypography($composer3, 6).getCaption().m7358getColor0d7_KjU();
            if (shouldOverrideTextStyleColor) {
                $composer3.startReplaceGroup(-887928539);
                ComposerKt.sourceInformation($composer3, "*117@4481L22");
                $this$takeOrElse_u2dDxMtmZc$iv = $this$CommonDecorationBox_u24lambda_u241;
                if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                    $this$takeOrElse_u2dDxMtmZc$iv = function11.invoke(inputState, $composer3, 0).m5107unboximpl();
                }
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(1218284988);
                $composer3.endReplaceGroup();
                $this$takeOrElse_u2dDxMtmZc$iv = $this$CommonDecorationBox_u24lambda_u241;
            }
            $composer3.endReplaceGroup();
            $composer3.startReplaceGroup(-1443806289);
            ComposerKt.sourceInformation($composer3, "");
            long $this$CommonDecorationBox_u24lambda_u242 = MaterialTheme.INSTANCE.getTypography($composer3, 6).getSubtitle1().m7358getColor0d7_KjU();
            if (shouldOverrideTextStyleColor) {
                $composer3.startReplaceGroup(-1026713946);
                ComposerKt.sourceInformation($composer3, "*122@4710L22");
                $this$takeOrElse_u2dDxMtmZc$iv2 = $this$CommonDecorationBox_u24lambda_u242;
                if (($this$takeOrElse_u2dDxMtmZc$iv2 != 16 ? 1 : 0) == 0) {
                    $this$takeOrElse_u2dDxMtmZc$iv2 = function11.invoke(inputState, $composer3, 0).m5107unboximpl();
                }
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(798166043);
                $composer3.endReplaceGroup();
                $this$takeOrElse_u2dDxMtmZc$iv2 = $this$CommonDecorationBox_u24lambda_u242;
            }
            $composer3.endReplaceGroup();
            textFieldTransitionScope.m2010TransitionDTcfvLk(inputState, $this$takeOrElse_u2dDxMtmZc$iv, $this$takeOrElse_u2dDxMtmZc$iv2, function11, function3 != null, ComposableLambdaKt.rememberComposableLambda(33336375, true, new AnonymousClass3(function3, function9, transformedText, colors, enabled, isError, interactionSource, function10, function6, shape, type, function8, singleLine, contentPadding, shouldOverrideTextStyleColor, function7), $composer3, 54), $composer3, 1769472);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextFieldImplKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldImplKt.CommonDecorationBox$lambda$3(type, value, function2, visualTransformation, function3, function4, function5, function6, singleLine, enabled, isError, interactionSource, contentPadding, shape, colors, function7, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldImpl.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass3 implements Function6<Float, Color, Color, Float, Composer, Integer, Unit> {
        final /* synthetic */ Function2<Composer, Integer, Unit> $border;
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ Function2<Composer, Integer, Unit> $innerTextField;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ Function2<Composer, Integer, Unit> $label;
        final /* synthetic */ Function2<Composer, Integer, Unit> $leadingIcon;
        final /* synthetic */ Function2<Composer, Integer, Unit> $placeholder;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ boolean $shouldOverrideTextStyleColor;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ Function2<Composer, Integer, Unit> $trailingIcon;
        final /* synthetic */ String $transformedText;
        final /* synthetic */ TextFieldType $type;

        /* JADX INFO: renamed from: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$WhenMappings */
        /* JADX INFO: compiled from: TextFieldImpl.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

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
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, String str, TextFieldColors textFieldColors, boolean z, boolean z2, InteractionSource interactionSource, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Shape shape, TextFieldType textFieldType, Function2<? super Composer, ? super Integer, Unit> function6, boolean z3, PaddingValues paddingValues, boolean z4, Function2<? super Composer, ? super Integer, Unit> function7) {
            this.$label = function2;
            this.$placeholder = function3;
            this.$transformedText = str;
            this.$colors = textFieldColors;
            this.$enabled = z;
            this.$isError = z2;
            this.$interactionSource = interactionSource;
            this.$leadingIcon = function4;
            this.$trailingIcon = function5;
            this.$shape = shape;
            this.$type = textFieldType;
            this.$innerTextField = function6;
            this.$singleLine = z3;
            this.$contentPadding = paddingValues;
            this.$shouldOverrideTextStyleColor = z4;
            this.$border = function7;
        }

        @Override // kotlin.jvm.functions.Function6
        public /* bridge */ /* synthetic */ Unit invoke(Float f, Color color, Color color2, Float f2, Composer composer, Integer num) {
            m2004invokeRIQooxk(f.floatValue(), color.m5107unboximpl(), color2.m5107unboximpl(), f2.floatValue(), composer, num.intValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:55:0x00f8  */
        /* JADX INFO: renamed from: invoke-RIQooxk, reason: not valid java name */
        public final void m2004invokeRIQooxk(float labelProgress, long j, final long j2, final float placeholderAlphaProgress, Composer $composer, int $changed) {
            long j3;
            boolean z;
            int i;
            Function3 function3;
            Function2 function2;
            Function2 decoratedTrailing;
            Modifier backgroundModifier;
            final float f = labelProgress;
            ComposerKt.sourceInformation($composer, "CN(labelProgress,labelTextStyleColor:c#ui.graphics.Color,labelContentColor:c#ui.graphics.Color,placeholderAlphaProgress)161@6500L53,167@6793L54,173@7111L24:TextFieldImpl.kt#jmzs0o");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer.changed(f) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                j3 = j;
                $dirty |= $composer.changed(j3) ? 32 : 16;
            } else {
                j3 = j;
            }
            if (($changed & 384) == 0) {
                $dirty |= $composer.changed(j2) ? 256 : 128;
            }
            if (($changed & 3072) == 0) {
                $dirty |= $composer.changed(placeholderAlphaProgress) ? 2048 : 1024;
            }
            int $dirty2 = $dirty;
            if ($composer.shouldExecute(($dirty2 & 9363) != 9362, $dirty2 & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(33336375, $dirty2, -1, "androidx.compose.material.CommonDecorationBox.<anonymous> (TextFieldImpl.kt:128)");
                }
                final Function2<Composer, Integer, Unit> function4 = this.$label;
                Function2 function2RememberComposableLambda = null;
                if (function4 == null) {
                    $composer.startReplaceGroup(986681709);
                    $composer.endReplaceGroup();
                    z = true;
                    i = 54;
                } else {
                    $composer.startReplaceGroup(986681710);
                    ComposerKt.sourceInformation($composer, "*130@5046L621");
                    final boolean z2 = this.$shouldOverrideTextStyleColor;
                    final long j4 = j3;
                    z = true;
                    i = 54;
                    Function2 function5 = new Function2() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return TextFieldImplKt.AnonymousClass3.invoke_RIQooxk$lambda$0$0(f, j2, function4, z2, j4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    };
                    f = f;
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(723429411, true, function5, $composer, 54);
                    $composer.endReplaceGroup();
                }
                Function2 decoratedLabel = function2RememberComposableLambda;
                if (this.$placeholder == null) {
                    $composer.startReplaceGroup(988093542);
                    $composer.endReplaceGroup();
                    function3 = null;
                } else if ((this.$transformedText.length() == 0 ? z : false) && placeholderAlphaProgress > 0.0f) {
                    $composer.startReplaceGroup(987666549);
                    ComposerKt.sourceInformation($composer, "150@6042L394");
                    final TextFieldColors textFieldColors = this.$colors;
                    final boolean z3 = this.$enabled;
                    final Function2<Composer, Integer, Unit> function6 = this.$placeholder;
                    Function3 function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-426706263, z, new Function3() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return TextFieldImplKt.AnonymousClass3.invoke_RIQooxk$lambda$1(placeholderAlphaProgress, textFieldColors, z3, function6, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, $composer, i);
                    $composer.endReplaceGroup();
                    function3 = function3RememberComposableLambda;
                } else {
                    $composer.startReplaceGroup(988093542);
                    $composer.endReplaceGroup();
                    function3 = null;
                }
                Function3 decoratedPlaceholder = function3;
                final long leadingIconColor = this.$colors.leadingIconColor(this.$enabled, this.$isError, this.$interactionSource, $composer, 0).getValue().m5107unboximpl();
                final Function2<Composer, Integer, Unit> function7 = this.$leadingIcon;
                if (function7 == null) {
                    $composer.startReplaceGroup(988282301);
                    $composer.endReplaceGroup();
                    function2 = null;
                } else {
                    $composer.startReplaceGroup(988282302);
                    ComposerKt.sourceInformation($composer, "*164@6677L61");
                    Function2 it = ComposableLambdaKt.rememberComposableLambda(-317090443, z, new Function2() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return TextFieldImplKt.AnonymousClass3.invoke_RIQooxk$lambda$2$0(leadingIconColor, function7, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, $composer, i);
                    $composer.endReplaceGroup();
                    function2 = it;
                }
                Function2 decoratedLeading = function2;
                final long trailingIconColor = this.$colors.trailingIconColor(this.$enabled, this.$isError, this.$interactionSource, $composer, 0).getValue().m5107unboximpl();
                final Function2<Composer, Integer, Unit> function8 = this.$trailingIcon;
                if (function8 == null) {
                    $composer.startReplaceGroup(988575964);
                    $composer.endReplaceGroup();
                    decoratedTrailing = null;
                } else {
                    $composer.startReplaceGroup(988575965);
                    ComposerKt.sourceInformation($composer, "*170@6973L62");
                    Function2 it2 = ComposableLambdaKt.rememberComposableLambda(262889693, z, new Function2() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return TextFieldImplKt.AnonymousClass3.invoke_RIQooxk$lambda$3$0(trailingIconColor, function8, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, $composer, i);
                    $composer.endReplaceGroup();
                    decoratedTrailing = it2;
                }
                Modifier backgroundModifier2 = BackgroundKt.m264backgroundbw27NRU(Modifier.INSTANCE, this.$colors.backgroundColor(this.$enabled, $composer, 0).getValue().m5107unboximpl(), this.$shape);
                switch (WhenMappings.$EnumSwitchMapping$0[this.$type.ordinal()]) {
                    case 1:
                        $composer.startReplaceGroup(988856360);
                        ComposerKt.sourceInformation($composer, "177@7227L483");
                        TextFieldKt.TextFieldLayout(backgroundModifier2, this.$innerTextField, decoratedLabel, decoratedPlaceholder, decoratedLeading, decoratedTrailing, this.$singleLine, labelProgress, this.$contentPadding, $composer, ($dirty2 << 21) & 29360128);
                        $composer.endReplaceGroup();
                        break;
                    case 2:
                        $composer.startReplaceGroup(989436742);
                        ComposerKt.sourceInformation($composer, "191@7832L38,192@7928L287,209@8638L453,201@8233L1025");
                        ComposerKt.sourceInformationMarkerStart($composer, 1971580701, "CC(remember):TextFieldImpl.kt#9igjgp");
                        Object it$iv = $composer.rememberedValue();
                        if (it$iv == Composer.INSTANCE.getEmpty()) {
                            Object value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Size.m4909boximpl(Size.INSTANCE.m4930getZeroNHjbRc()), null, 2, null);
                            $composer.updateRememberedValue(value$iv);
                            it$iv = value$iv;
                        }
                        final MutableState labelSize = (MutableState) it$iv;
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        final PaddingValues paddingValues = this.$contentPadding;
                        final Function2<Composer, Integer, Unit> function9 = this.$border;
                        Function2 drawBorder = ComposableLambdaKt.rememberComposableLambda(-1107746014, true, new Function2() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return TextFieldImplKt.AnonymousClass3.invoke_RIQooxk$lambda$5(labelSize, paddingValues, function9, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        }, $composer, 54);
                        Function2<Composer, Integer, Unit> function10 = this.$innerTextField;
                        boolean z4 = this.$singleLine;
                        ComposerKt.sourceInformationMarkerStart($composer, 1971606908, "CC(remember):TextFieldImpl.kt#9igjgp");
                        boolean invalid$iv = ($dirty2 & 14) == 4;
                        Object value$iv2 = $composer.rememberedValue();
                        if (!invalid$iv) {
                            backgroundModifier = backgroundModifier2;
                            if (value$iv2 == Composer.INSTANCE.getEmpty()) {
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            OutlinedTextFieldKt.OutlinedTextFieldLayout(backgroundModifier, function10, decoratedPlaceholder, decoratedLabel, decoratedLeading, decoratedTrailing, z4, f, (Function1) value$iv2, drawBorder, this.$contentPadding, $composer, (($dirty2 << 21) & 29360128) | C.ENCODING_PCM_32BIT, 0);
                            $composer.endReplaceGroup();
                        } else {
                            backgroundModifier = backgroundModifier2;
                        }
                        value$iv2 = new Function1() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return TextFieldImplKt.AnonymousClass3.invoke_RIQooxk$lambda$6$0(f, labelSize, (Size) obj);
                            }
                        };
                        $composer.updateRememberedValue(value$iv2);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        OutlinedTextFieldKt.OutlinedTextFieldLayout(backgroundModifier, function10, decoratedPlaceholder, decoratedLabel, decoratedLeading, decoratedTrailing, z4, f, (Function1) value$iv2, drawBorder, this.$contentPadding, $composer, (($dirty2 << 21) & 29360128) | C.ENCODING_PCM_32BIT, 0);
                        $composer.endReplaceGroup();
                        break;
                    default:
                        $composer.startReplaceGroup(1971561250);
                        $composer.endReplaceGroup();
                        throw new NoWhenBranchMatchedException();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke_RIQooxk$lambda$0$0(float $labelProgress, long j, Function2 $it, boolean $shouldOverrideTextStyleColor, long j2, Composer $composer, int $changed) {
            TextStyle labelTextStyle;
            ComposerKt.sourceInformation($composer, "C133@5165L10,134@5233L10,142@5594L55:TextFieldImpl.kt#jmzs0o");
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(723429411, $changed, -1, "androidx.compose.material.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:131)");
                }
                TextStyle it = TextStyleKt.lerp(MaterialTheme.INSTANCE.getTypography($composer, 6).getSubtitle1(), MaterialTheme.INSTANCE.getTypography($composer, 6).getCaption(), $labelProgress);
                if ($shouldOverrideTextStyleColor) {
                    labelTextStyle = TextStyle.m7341copyp1EtxEg$default(it, j2, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null);
                } else {
                    labelTextStyle = it;
                }
                TextFieldImplKt.m2003DecorationeuL9pac(j, labelTextStyle, null, $it, $composer, 384, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        static final Unit invoke_RIQooxk$lambda$1(float $placeholderAlphaProgress, TextFieldColors $colors, boolean $enabled, Function2 $placeholder, Modifier modifier, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            ComposerKt.sourceInformation($composer, "CN(modifier)151@6076L342:TextFieldImpl.kt#jmzs0o");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer.changed(modifier) ? 4 : 2;
            }
            if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-426706263, $dirty, -1, "androidx.compose.material.CommonDecorationBox.<anonymous>.<anonymous> (TextFieldImpl.kt:151)");
                }
                Modifier modifier$iv = AlphaKt.alpha(modifier, $placeholderAlphaProgress);
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i2 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1083624968, "C153@6210L25,154@6298L10,152@6148L248:TextFieldImpl.kt#jmzs0o");
                TextFieldImplKt.m2003DecorationeuL9pac($colors.placeholderColor($enabled, $composer, 0).getValue().m5107unboximpl(), MaterialTheme.INSTANCE.getTypography($composer, 6).getSubtitle1(), null, $placeholder, $composer, 0, 4);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke_RIQooxk$lambda$2$0(long $leadingIconColor, Function2 $it, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C164@6679L57:TextFieldImpl.kt#jmzs0o");
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-317090443, $changed, -1, "androidx.compose.material.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:164)");
                }
                TextFieldImplKt.m2003DecorationeuL9pac($leadingIconColor, null, null, $it, $composer, 0, 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke_RIQooxk$lambda$3$0(long $trailingIconColor, Function2 $it, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C170@6975L58:TextFieldImpl.kt#jmzs0o");
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(262889693, $changed, -1, "androidx.compose.material.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:170)");
                }
                TextFieldImplKt.m2003DecorationeuL9pac($trailingIconColor, null, null, $it, $composer, 0, 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        static final Unit invoke_RIQooxk$lambda$5(MutableState $labelSize, PaddingValues $contentPadding, Function2 $border, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            ComposerKt.sourceInformation($composer, "C193@7950L247:TextFieldImpl.kt#jmzs0o");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1107746014, $changed, -1, "androidx.compose.material.CommonDecorationBox.<anonymous>.<anonymous> (TextFieldImpl.kt:193)");
                }
                Modifier modifier$iv = OutlinedTextFieldKt.m1881outlineCutout12SF9DM(LayoutIdKt.layoutId(Modifier.INSTANCE, OutlinedTextFieldKt.BorderId), ((Size) $labelSize.getValue()).m4926unboximpl(), $contentPadding);
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
                int $changed$iv$iv = (384 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i2 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1295987369, "C:TextFieldImpl.kt#jmzs0o");
                if ($border == null) {
                    $composer.startReplaceGroup(-1295979683);
                } else {
                    $composer.startReplaceGroup(235288868);
                    ComposerKt.sourceInformation($composer, "197@8167L8");
                    $border.invoke($composer, 0);
                }
                $composer.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:12:0x006f  */
        public static final Unit invoke_RIQooxk$lambda$6$0(float $labelProgress, MutableState $labelSize, Size it) {
            long arg0$iv = it.m4926unboximpl();
            int bits$iv$iv$iv = (int) (arg0$iv >> 32);
            float labelWidth = Float.intBitsToFloat(bits$iv$iv$iv) * $labelProgress;
            long arg0$iv2 = it.m4926unboximpl();
            int bits$iv$iv$iv2 = (int) (arg0$iv2 & 4294967295L);
            float labelHeight = Float.intBitsToFloat(bits$iv$iv$iv2) * $labelProgress;
            long arg0$iv3 = ((Size) $labelSize.getValue()).m4926unboximpl();
            int bits$iv$iv$iv3 = (int) (arg0$iv3 >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv3) == labelWidth) {
                long arg0$iv4 = ((Size) $labelSize.getValue()).m4926unboximpl();
                int bits$iv$iv$iv4 = (int) (arg0$iv4 & 4294967295L);
                if (!(Float.intBitsToFloat(bits$iv$iv$iv4) == labelHeight)) {
                    long v1$iv$iv = Float.floatToRawIntBits(labelWidth);
                    long v2$iv$iv = Float.floatToRawIntBits(labelHeight);
                    $labelSize.setValue(Size.m4909boximpl(Size.m4912constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv))));
                }
            } else {
                long v1$iv$iv2 = Float.floatToRawIntBits(labelWidth);
                long v2$iv$iv2 = Float.floatToRawIntBits(labelHeight);
                $labelSize.setValue(Size.m4909boximpl(Size.m4912constructorimpl((v1$iv$iv2 << 32) | (4294967295L & v2$iv$iv2))));
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: Decoration-euL9pac, reason: not valid java name */
    public static final void m2003DecorationeuL9pac(final long j, TextStyle typography, Float contentAlpha, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        final TextStyle typography2;
        final Float contentAlpha2;
        TextStyle typography3;
        final Float contentAlpha3;
        Composer $composer2 = $composer.startRestartGroup(2064632657);
        ComposerKt.sourceInformation($composer2, "C(Decoration)N(contentColor:c#ui.graphics.Color,typography,contentAlpha,content)238@9675L550:TextFieldImpl.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(j) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(typography) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(contentAlpha) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            typography2 = typography;
            contentAlpha2 = contentAlpha;
        } else {
            if (i2 != 0) {
                typography3 = null;
            } else {
                typography3 = typography;
            }
            if (i3 == 0) {
                contentAlpha3 = contentAlpha;
            } else {
                contentAlpha3 = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2064632657, $dirty2, -1, "androidx.compose.material.Decoration (TextFieldImpl.kt:236)");
            }
            Function2 colorAndEmphasis = ComposableLambdaKt.rememberComposableLambda(-650790565, true, new Function2() { // from class: androidx.compose.material.TextFieldImplKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldImplKt.Decoration_euL9pac$lambda$0(j, contentAlpha3, function2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54);
            if (typography3 != null) {
                $composer2.startReplaceGroup(-162880673);
                ComposerKt.sourceInformation($composer2, "253@10254L46");
                TextKt.ProvideTextStyle(typography3, colorAndEmphasis, $composer2, (($dirty2 >> 3) & 14) | 48);
            } else {
                $composer2.startReplaceGroup(-162879037);
                ComposerKt.sourceInformation($composer2, "253@10306L18");
                colorAndEmphasis.invoke($composer2, 6);
            }
            $composer2.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            typography2 = typography3;
            contentAlpha2 = contentAlpha3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextFieldImplKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldImplKt.Decoration_euL9pac$lambda$1(j, typography2, contentAlpha2, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Decoration_euL9pac$lambda$0(final long j, final Float $contentAlpha, final Function2 $content, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C239@9755L460,239@9689L526:TextFieldImpl.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-650790565, $changed, -1, "androidx.compose.material.Decoration.<anonymous> (TextFieldImpl.kt:239)");
            }
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(j)), ComposableLambdaKt.rememberComposableLambda(-1624601445, true, new Function2() { // from class: androidx.compose.material.TextFieldImplKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldImplKt.Decoration_euL9pac$lambda$0$0($contentAlpha, $content, j, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Decoration_euL9pac$lambda$0$0(Float $contentAlpha, Function2 $content, long j, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C:TextFieldImpl.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1624601445, $changed, -1, "androidx.compose.material.Decoration.<anonymous>.<anonymous> (TextFieldImpl.kt:240)");
            }
            if ($contentAlpha != null) {
                $composer.startReplaceGroup(1484860324);
                ComposerKt.sourceInformation($composer, "241@9821L155");
                CompositionLocalKt.CompositionLocalProvider(ContentAlphaKt.getLocalContentAlpha().provides($contentAlpha), (Function2<? super Composer, ? super Integer, Unit>) $content, $composer, ProvidedValue.$stable);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(1485059902);
                ComposerKt.sourceInformation($composer, "246@10022L161");
                CompositionLocalKt.CompositionLocalProvider(ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m5099getAlphaimpl(j))), (Function2<? super Composer, ? super Integer, Unit>) $content, $composer, ProvidedValue.$stable);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public static final Modifier defaultErrorSemantics(Modifier $this$defaultErrorSemantics, boolean isError, final String defaultErrorMessage) {
        return isError ? SemanticsModifierKt.semantics$default($this$defaultErrorSemantics, false, new Function1() { // from class: androidx.compose.material.TextFieldImplKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldImplKt.defaultErrorSemantics$lambda$0(defaultErrorMessage, (SemanticsPropertyReceiver) obj);
            }
        }, 1, null) : $this$defaultErrorSemantics;
    }

    static final Unit defaultErrorSemantics$lambda$0(String $defaultErrorMessage, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.error($this$semantics, $defaultErrorMessage);
        return Unit.INSTANCE;
    }

    public static final int widthOrZero(Placeable placeable) {
        if (placeable != null) {
            return placeable.getWidth();
        }
        return 0;
    }

    public static final int heightOrZero(Placeable placeable) {
        if (placeable != null) {
            return placeable.getHeight();
        }
        return 0;
    }

    public static final Object getLayoutId(IntrinsicMeasurable $this$layoutId) {
        Object parentData = $this$layoutId.getParentData();
        LayoutIdParentData layoutIdParentData = parentData instanceof LayoutIdParentData ? (LayoutIdParentData) parentData : null;
        if (layoutIdParentData != null) {
            return layoutIdParentData.getLayoutId();
        }
        return null;
    }

    public static final float getTextFieldPadding() {
        return TextFieldPadding;
    }

    public static final float getHorizontalIconPadding() {
        return HorizontalIconPadding;
    }
}
