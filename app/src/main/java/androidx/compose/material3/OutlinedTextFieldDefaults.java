package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.text.input.OutputTransformation;
import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.foundation.text.input.TextFieldDecorator;
import androidx.compose.foundation.text.input.TextFieldLineLimits;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.material3.internal.TextFieldType;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.OutlinedTextFieldTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import com.cloudinary.metadata.MetadataField;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;

/* JADX INFO: compiled from: TextFieldDefaults.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0002\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2 \b\u0002\u0010!\u001a\u001a\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$\u0018\u00010\"¢\u0006\u0002\b%¢\u0006\u0002\b&2\u0015\b\u0002\u0010'\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010)\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010*\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010+\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010,\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010-\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\b\b\u0002\u0010.\u001a\u00020\u00182\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u0002022\u0013\b\u0002\u00103\u001a\r\u0012\u0004\u0012\u00020$0(¢\u0006\u0002\b%H\u0007¢\u0006\u0002\u00104JY\u00105\u001a\u00020$2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u00108\u001a\u00020\t2\b\b\u0002\u00109\u001a\u00020\tH\u0007¢\u0006\u0004\b:\u0010;J\u009c\u0002\u0010<\u001a\u00020$2\u0006\u0010=\u001a\u00020>2\u0011\u0010?\u001a\r\u0012\u0004\u0012\u00020$0(¢\u0006\u0002\b%2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010@\u001a\u00020\u00182\u0006\u0010A\u001a\u00020B2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010.\u001a\u00020\u00182\u0015\b\u0002\u0010!\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010'\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010)\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010*\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010+\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010,\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010-\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u0002022\u0013\b\u0002\u00103\u001a\r\u0012\u0004\u0012\u00020$0(¢\u0006\u0002\b%H\u0007¢\u0006\u0002\u0010CJ5\u00101\u001a\u0002022\b\b\u0002\u0010D\u001a\u00020\t2\b\b\u0002\u0010E\u001a\u00020\t2\b\b\u0002\u0010F\u001a\u00020\t2\b\b\u0002\u0010G\u001a\u00020\t¢\u0006\u0004\bH\u0010IJ\r\u0010/\u001a\u000200H\u0007¢\u0006\u0002\u0010JJ¿\u0003\u0010/\u001a\u0002002\b\b\u0002\u0010K\u001a\u00020L2\b\b\u0002\u0010M\u001a\u00020L2\b\b\u0002\u0010N\u001a\u00020L2\b\b\u0002\u0010O\u001a\u00020L2\b\b\u0002\u0010P\u001a\u00020L2\b\b\u0002\u0010Q\u001a\u00020L2\b\b\u0002\u0010R\u001a\u00020L2\b\b\u0002\u0010S\u001a\u00020L2\b\b\u0002\u0010T\u001a\u00020L2\b\b\u0002\u0010U\u001a\u00020L2\n\b\u0002\u0010V\u001a\u0004\u0018\u00010W2\b\b\u0002\u0010X\u001a\u00020L2\b\b\u0002\u0010Y\u001a\u00020L2\b\b\u0002\u0010Z\u001a\u00020L2\b\b\u0002\u0010[\u001a\u00020L2\b\b\u0002\u0010\\\u001a\u00020L2\b\b\u0002\u0010]\u001a\u00020L2\b\b\u0002\u0010^\u001a\u00020L2\b\b\u0002\u0010_\u001a\u00020L2\b\b\u0002\u0010`\u001a\u00020L2\b\b\u0002\u0010a\u001a\u00020L2\b\b\u0002\u0010b\u001a\u00020L2\b\b\u0002\u0010c\u001a\u00020L2\b\b\u0002\u0010d\u001a\u00020L2\b\b\u0002\u0010e\u001a\u00020L2\b\b\u0002\u0010f\u001a\u00020L2\b\b\u0002\u0010g\u001a\u00020L2\b\b\u0002\u0010h\u001a\u00020L2\b\b\u0002\u0010i\u001a\u00020L2\b\b\u0002\u0010j\u001a\u00020L2\b\b\u0002\u0010k\u001a\u00020L2\b\b\u0002\u0010l\u001a\u00020L2\b\b\u0002\u0010m\u001a\u00020L2\b\b\u0002\u0010n\u001a\u00020L2\b\b\u0002\u0010o\u001a\u00020L2\b\b\u0002\u0010p\u001a\u00020L2\b\b\u0002\u0010q\u001a\u00020L2\b\b\u0002\u0010r\u001a\u00020L2\b\b\u0002\u0010s\u001a\u00020L2\b\b\u0002\u0010t\u001a\u00020L2\b\b\u0002\u0010u\u001a\u00020L2\b\b\u0002\u0010v\u001a\u00020L2\b\b\u0002\u0010w\u001a\u00020LH\u0007¢\u0006\u0004\bx\u0010yJP\u0010~\u001a\u00020$2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u00108\u001a\u00020\t2\b\b\u0002\u00109\u001a\u00020\tH\u0007¢\u0006\u0005\b\u007f\u0010\u0080\u0001R\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\r\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000bR\u0013\u0010\u000f\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0010\u0010\u000bR\u0013\u0010\u0011\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0012\u0010\u000bR\u0018\u0010z\u001a\u000200*\u00020{8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b|\u0010}¨\u0006\u0081\u0001"}, d2 = {"Landroidx/compose/material3/OutlinedTextFieldDefaults;", "", "<init>", "()V", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "MinHeight", "Landroidx/compose/ui/unit/Dp;", "getMinHeight-D9Ej5fM", "()F", "F", "MinWidth", "getMinWidth-D9Ej5fM", "UnfocusedBorderThickness", "getUnfocusedBorderThickness-D9Ej5fM", "FocusedBorderThickness", "getFocusedBorderThickness-D9Ej5fM", "decorator", "Landroidx/compose/foundation/text/input/TextFieldDecorator;", "state", "Landroidx/compose/foundation/text/input/TextFieldState;", "enabled", "", "lineLimits", "Landroidx/compose/foundation/text/input/TextFieldLineLimits;", "outputTransformation", "Landroidx/compose/foundation/text/input/OutputTransformation;", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "labelPosition", "Landroidx/compose/material3/TextFieldLabelPosition;", MetadataField.LABEL, "Lkotlin/Function1;", "Landroidx/compose/material3/TextFieldLabelScope;", "", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "placeholder", "Lkotlin/Function0;", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "isError", "colors", "Landroidx/compose/material3/TextFieldColors;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", TtmlNode.RUBY_CONTAINER, "(Landroidx/compose/foundation/text/input/TextFieldState;ZLandroidx/compose/foundation/text/input/TextFieldLineLimits;Landroidx/compose/foundation/text/input/OutputTransformation;Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldLabelPosition;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)Landroidx/compose/foundation/text/input/TextFieldDecorator;", TextFieldImplKt.ContainerId, "modifier", "Landroidx/compose/ui/Modifier;", "focusedBorderThickness", "unfocusedBorderThickness", "Container-4EFweAY", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "DecorationBox", "value", "", "innerTextField", "singleLine", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", TtmlNode.START, "top", TtmlNode.END, "bottom", "contentPadding-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", "focusedTextColor", "Landroidx/compose/ui/graphics/Color;", "unfocusedTextColor", "disabledTextColor", "errorTextColor", "focusedContainerColor", "unfocusedContainerColor", "disabledContainerColor", "errorContainerColor", "cursorColor", "errorCursorColor", "selectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "disabledPlaceholderColor", "errorPlaceholderColor", "focusedSupportingTextColor", "unfocusedSupportingTextColor", "disabledSupportingTextColor", "errorSupportingTextColor", "focusedPrefixColor", "unfocusedPrefixColor", "disabledPrefixColor", "errorPrefixColor", "focusedSuffixColor", "unfocusedSuffixColor", "disabledSuffixColor", "errorSuffixColor", "colors-0hiis_0", "(JJJJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIIII)Landroidx/compose/material3/TextFieldColors;", "defaultOutlinedTextFieldColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultOutlinedTextFieldColors", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", "ContainerBox", "ContainerBox-nbWgWpA", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class OutlinedTextFieldDefaults {
    public static final int $stable = 0;
    public static final OutlinedTextFieldDefaults INSTANCE = new OutlinedTextFieldDefaults();
    private static final float MinHeight = Dp.m7905constructorimpl(56);
    private static final float MinWidth = Dp.m7905constructorimpl(280);
    private static final float UnfocusedBorderThickness = Dp.m7905constructorimpl(1);
    private static final float FocusedBorderThickness = Dp.m7905constructorimpl(2);

    static final Unit ContainerBox_nbWgWpA$lambda$7(OutlinedTextFieldDefaults outlinedTextFieldDefaults, boolean z, boolean z2, InteractionSource interactionSource, TextFieldColors textFieldColors, Shape shape, float f, float f2, int i, int i2, Composer composer, int i3) {
        outlinedTextFieldDefaults.m2707ContainerBoxnbWgWpA(z, z2, interactionSource, textFieldColors, shape, f, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Container_4EFweAY$lambda$0(OutlinedTextFieldDefaults outlinedTextFieldDefaults, boolean z, boolean z2, InteractionSource interactionSource, Modifier modifier, TextFieldColors textFieldColors, Shape shape, float f, float f2, int i, int i2, Composer composer, int i3) {
        outlinedTextFieldDefaults.m2706Container4EFweAY(z, z2, interactionSource, modifier, textFieldColors, shape, f, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit DecorationBox$lambda$3(OutlinedTextFieldDefaults outlinedTextFieldDefaults, String str, Function2 function2, boolean z, boolean z2, VisualTransformation visualTransformation, InteractionSource interactionSource, boolean z3, Function2 function3, Function2 function4, Function2 function5, Function2 function6, Function2 function7, Function2 function8, Function2 function9, TextFieldColors textFieldColors, PaddingValues paddingValues, Function2 function10, int i, int i2, int i3, Composer composer, int i4) {
        outlinedTextFieldDefaults.DecorationBox(str, function2, z, z2, visualTransformation, interactionSource, z3, function3, function4, function5, function6, function7, function8, function9, textFieldColors, paddingValues, function10, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    private OutlinedTextFieldDefaults() {
    }

    public final Shape getShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1066756961, "C(<get-shape>)887@46101L5:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1066756961, $changed, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.<get-shape> (TextFieldDefaults.kt:887)");
        }
        Shape value = ShapesKt.getValue(OutlinedTextFieldTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m2711getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m2712getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    /* JADX INFO: renamed from: getUnfocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m2713getUnfocusedBorderThicknessD9Ej5fM() {
        return UnfocusedBorderThickness;
    }

    /* JADX INFO: renamed from: getFocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m2710getFocusedBorderThicknessD9Ej5fM() {
        return FocusedBorderThickness;
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldDefaults$decorator$2, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldDefaults.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0004H\n"}, d2 = {"<anonymous>", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass2 implements TextFieldDecorator {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ Function2<Composer, Integer, Unit> $container;
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ Function3<TextFieldLabelScope, Composer, Integer, Unit> $label;
        final /* synthetic */ TextFieldLabelPosition $labelPosition;
        final /* synthetic */ Function2<Composer, Integer, Unit> $leadingIcon;
        final /* synthetic */ TextFieldLineLimits $lineLimits;
        final /* synthetic */ OutputTransformation $outputTransformation;
        final /* synthetic */ Function2<Composer, Integer, Unit> $placeholder;
        final /* synthetic */ Function2<Composer, Integer, Unit> $prefix;
        final /* synthetic */ TextFieldState $state;
        final /* synthetic */ Function2<Composer, Integer, Unit> $suffix;
        final /* synthetic */ Function2<Composer, Integer, Unit> $supportingText;
        final /* synthetic */ Function2<Composer, Integer, Unit> $trailingIcon;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(OutputTransformation outputTransformation, TextFieldState textFieldState, TextFieldLineLimits textFieldLineLimits, TextFieldLabelPosition textFieldLabelPosition, Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, boolean z, boolean z2, InteractionSource interactionSource, PaddingValues paddingValues, TextFieldColors textFieldColors, Function2<? super Composer, ? super Integer, Unit> function9) {
            this.$outputTransformation = outputTransformation;
            this.$state = textFieldState;
            this.$lineLimits = textFieldLineLimits;
            this.$labelPosition = textFieldLabelPosition;
            this.$label = function3;
            this.$placeholder = function2;
            this.$leadingIcon = function4;
            this.$trailingIcon = function5;
            this.$prefix = function6;
            this.$suffix = function7;
            this.$supportingText = function8;
            this.$enabled = z;
            this.$isError = z2;
            this.$interactionSource = interactionSource;
            this.$contentPadding = paddingValues;
            this.$colors = textFieldColors;
            this.$container = function9;
        }

        static final Unit Decoration$lambda$2(AnonymousClass2 anonymousClass2, Function2 function2, int i, Composer composer, int i2) {
            anonymousClass2.Decoration(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
            return Unit.INSTANCE;
        }

        @Override // androidx.compose.foundation.text.input.TextFieldDecorator
        public final void Decoration(final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
            Composer $composer2;
            TextFieldBuffer textFieldBuffer;
            CharSequence visualText;
            Composer $composer3 = $composer.startRestartGroup(794272399);
            ComposerKt.sourceInformation($composer3, "C(Decoration)N(innerTextField)1006@52904L720:TextFieldDefaults.kt#uh7d8r");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer3.changedInstance(function2) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer3.changed(this) ? 32 : 16;
            }
            if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                $composer2 = $composer3;
                $composer2.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(794272399, $dirty, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.decorator.<no name provided>.Decoration (TextFieldDefaults.kt:994)");
                }
                if (this.$outputTransformation == null) {
                    visualText = this.$state.getText();
                } else {
                    TextFieldState this_$iv = this.$state;
                    TextFieldBuffer mutableValue$iv = this_$iv.startEdit();
                    try {
                        this_$iv.commitEdit(mutableValue$iv);
                        this_$iv.finishEditing();
                        OutputTransformation $this$Decoration_u24lambda_u241 = this.$outputTransformation;
                        if (mutableValue$iv == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("buffer");
                            textFieldBuffer = null;
                        } else {
                            textFieldBuffer = mutableValue$iv;
                        }
                        $this$Decoration_u24lambda_u241.transformOutput(textFieldBuffer);
                        visualText = mutableValue$iv.asCharSequence();
                    } catch (Throwable th) {
                        this_$iv.finishEditing();
                        throw th;
                    }
                }
                $composer2 = $composer3;
                TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, visualText, function2, this.$labelPosition, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, Intrinsics.areEqual(this.$lineLimits, TextFieldLineLimits.SingleLine.INSTANCE), this.$enabled, this.$isError, this.$interactionSource, this.$contentPadding, this.$colors, this.$container, $composer2, (($dirty << 6) & 896) | 6, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$decorator$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return OutlinedTextFieldDefaults.AnonymousClass2.Decoration$lambda$2(this.f$0, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
    }

    public final TextFieldDecorator decorator(TextFieldState state, boolean enabled, TextFieldLineLimits lineLimits, OutputTransformation outputTransformation, InteractionSource interactionSource, TextFieldLabelPosition labelPosition, Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, boolean isError, TextFieldColors colors, PaddingValues contentPadding, Function2<? super Composer, ? super Integer, Unit> function9, Composer $composer, int $changed, int $changed1, int i) {
        TextFieldLabelPosition labelPosition2;
        Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function10;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        Function2<? super Composer, ? super Integer, Unit> function15;
        Function2<? super Composer, ? super Integer, Unit> function16;
        final boolean isError2;
        OutlinedTextFieldDefaults outlinedTextFieldDefaults;
        final TextFieldColors colors2;
        PaddingValues contentPadding2;
        final boolean z;
        final InteractionSource interactionSource2;
        Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda;
        ComposerKt.sourceInformationMarkerStart($composer, -449059361, "C(decorator)N(state,enabled,lineLimits,outputTransformation,interactionSource,labelPosition,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,isError,colors,contentPadding,container)980@51820L8,982@51932L371:TextFieldDefaults.kt#uh7d8r");
        if ((i & 32) != 0) {
            labelPosition2 = new TextFieldLabelPosition.Attached(false, null, null, 7, null);
        } else {
            labelPosition2 = labelPosition;
        }
        if ((i & 64) == 0) {
            function10 = function3;
        } else {
            function10 = null;
        }
        if ((i & 128) == 0) {
            function11 = function2;
        } else {
            function11 = null;
        }
        if ((i & 256) == 0) {
            function12 = function4;
        } else {
            function12 = null;
        }
        if ((i & 512) == 0) {
            function13 = function5;
        } else {
            function13 = null;
        }
        if ((i & 1024) == 0) {
            function14 = function6;
        } else {
            function14 = null;
        }
        if ((i & 2048) == 0) {
            function15 = function7;
        } else {
            function15 = null;
        }
        if ((i & 4096) == 0) {
            function16 = function8;
        } else {
            function16 = null;
        }
        if ((i & 8192) == 0) {
            isError2 = isError;
        } else {
            isError2 = false;
        }
        if ((i & 16384) == 0) {
            outlinedTextFieldDefaults = this;
            colors2 = colors;
        } else {
            outlinedTextFieldDefaults = this;
            colors2 = outlinedTextFieldDefaults.colors($composer, ($changed1 >> 21) & 14);
        }
        if ((32768 & i) == 0) {
            contentPadding2 = contentPadding;
        } else {
            contentPadding2 = m2705contentPaddinga9UjIt4$default(outlinedTextFieldDefaults, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
        }
        if ((i & 65536) == 0) {
            z = enabled;
            interactionSource2 = interactionSource;
            function2RememberComposableLambda = function9;
        } else {
            z = enabled;
            interactionSource2 = interactionSource;
            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-163468598, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults.decorator.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer2, int $changed2) {
                    ComposerKt.sourceInformation($composer2, "C988@52139L5,983@51946L347:TextFieldDefaults.kt#uh7d8r");
                    if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-163468598, $changed2, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.decorator.<anonymous> (TextFieldDefaults.kt:983)");
                    }
                    OutlinedTextFieldDefaults.INSTANCE.m2706Container4EFweAY(z, isError2, interactionSource2, null, colors2, OutlinedTextFieldDefaults.INSTANCE.getShape($composer2, 6), OutlinedTextFieldDefaults.INSTANCE.m2710getFocusedBorderThicknessD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2713getUnfocusedBorderThicknessD9Ej5fM(), $composer2, 114819072, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer, 54);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-449059361, $changed, $changed1, "androidx.compose.material3.OutlinedTextFieldDefaults.decorator (TextFieldDefaults.kt:993)");
        }
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(outputTransformation, state, lineLimits, labelPosition2, function10, function11, function12, function13, function14, function15, function16, z, isError2, interactionSource2, contentPadding2, colors2, function2RememberComposableLambda);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return anonymousClass2;
    }

    /* JADX INFO: renamed from: Container-4EFweAY, reason: not valid java name */
    public final void m2706Container4EFweAY(final boolean enabled, final boolean isError, final InteractionSource interactionSource, Modifier modifier, TextFieldColors colors, Shape shape, float focusedBorderThickness, float unfocusedBorderThickness, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TextFieldColors textFieldColors;
        Shape shape2;
        float f;
        float f2;
        Composer $composer2;
        final Modifier modifier3;
        final Shape shape3;
        final TextFieldColors colors2;
        final float focusedBorderThickness2;
        final float unfocusedBorderThickness2;
        Modifier.Companion modifier4;
        TextFieldColors colors3;
        Shape shape4;
        float focusedBorderThickness3;
        Modifier modifier5;
        Shape shape5;
        float unfocusedBorderThickness3;
        int $dirty;
        TextFieldColors colors4;
        float focusedBorderThickness4;
        Composer $composer3 = $composer.startRestartGroup(1035477640);
        ComposerKt.sourceInformation($composer3, "C(Container)N(enabled,isError,interactionSource,modifier,colors,shape,focusedBorderThickness:c#ui.unit.Dp,unfocusedBorderThickness:c#ui.unit.Dp)1055@55084L25,1057@55155L222,1069@55662L7,1067@55495L189,1071@55693L153:TextFieldDefaults.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(enabled) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(isError) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty2 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                textFieldColors = colors;
                int i3 = $composer3.changed(textFieldColors) ? 16384 : 8192;
                $dirty2 |= i3;
            } else {
                textFieldColors = colors;
            }
            $dirty2 |= i3;
        } else {
            textFieldColors = colors;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i4 = $composer3.changed(shape2) ? 131072 : 65536;
                $dirty2 |= i4;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i4;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                f = focusedBorderThickness;
                int i5 = $composer3.changed(f) ? 1048576 : 524288;
                $dirty2 |= i5;
            } else {
                f = focusedBorderThickness;
            }
            $dirty2 |= i5;
        } else {
            f = focusedBorderThickness;
        }
        if ((12582912 & $changed) == 0) {
            if ((i & 128) == 0) {
                f2 = unfocusedBorderThickness;
                int i6 = $composer3.changed(f2) ? 8388608 : 4194304;
                $dirty2 |= i6;
            } else {
                f2 = unfocusedBorderThickness;
            }
            $dirty2 |= i6;
        } else {
            f2 = unfocusedBorderThickness;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(this) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ($composer3.shouldExecute((38347923 & $dirty2) != 38347922, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1050@54844L8,1051@54903L5");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty2 &= -29360129;
                }
                focusedBorderThickness4 = f;
                shape5 = shape2;
                modifier5 = modifier2;
                unfocusedBorderThickness3 = f2;
                $dirty = $dirty2;
                colors4 = textFieldColors;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 16) == 0) {
                    colors3 = textFieldColors;
                } else {
                    colors3 = colors($composer3, ($dirty2 >> 24) & 14);
                    $dirty2 &= -57345;
                }
                if ((i & 32) == 0) {
                    shape4 = shape2;
                } else {
                    shape4 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -458753;
                }
                if ((i & 64) == 0) {
                    focusedBorderThickness3 = f;
                } else {
                    focusedBorderThickness3 = FocusedBorderThickness;
                    $dirty2 &= -3670017;
                }
                if ((i & 128) == 0) {
                    modifier5 = modifier4;
                    shape5 = shape4;
                    unfocusedBorderThickness3 = f2;
                    $dirty = $dirty2;
                    colors4 = colors3;
                    focusedBorderThickness4 = focusedBorderThickness3;
                } else {
                    $dirty = $dirty2 & (-29360129);
                    colors4 = colors3;
                    shape5 = shape4;
                    focusedBorderThickness4 = focusedBorderThickness3;
                    unfocusedBorderThickness3 = UnfocusedBorderThickness;
                    modifier5 = modifier4;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1035477640, $dirty, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.Container (TextFieldDefaults.kt:1054)");
            }
            boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource, $composer3, ($dirty >> 6) & 14).getValue().booleanValue();
            State<BorderStroke> stateM3455animateBorderStrokeAsStateNuRrP5Q = TextFieldImplKt.m3455animateBorderStrokeAsStateNuRrP5Q(enabled, isError, focused, colors4, focusedBorderThickness4, unfocusedBorderThickness3, $composer3, ($dirty & 14) | ($dirty & 112) | (($dirty >> 3) & 7168) | (($dirty >> 6) & 57344) | (($dirty >> 6) & 458752));
            Modifier modifier6 = modifier5;
            Shape shape6 = shape5;
            final State<Color> stateM135animateColorAsStateeuL9pac = SingleValueAnimationKt.m135animateColorAsStateeuL9pac(colors4.m2987containerColorXeAY9LY$material3(enabled, isError, focused), MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer3, 6), null, null, $composer3, 0, 12);
            $composer2 = $composer3;
            BoxKt.Box(TextFieldImplKt.textFieldBackground(BorderKt.border(modifier6, stateM3455animateBorderStrokeAsStateNuRrP5Q.getValue(), shape6), new TextFieldDefaults$sam$androidx_compose_ui_graphics_ColorProducer$0(new PropertyReference0Impl(stateM135animateColorAsStateeuL9pac) { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$Container$1
                @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return ((State) this.receiver).getValue();
                }
            }), shape6), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            unfocusedBorderThickness2 = unfocusedBorderThickness3;
            shape3 = shape6;
            focusedBorderThickness2 = focusedBorderThickness4;
            colors2 = colors4;
            modifier3 = modifier6;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            shape3 = shape2;
            colors2 = textFieldColors;
            focusedBorderThickness2 = f;
            unfocusedBorderThickness2 = f2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldDefaults.Container_4EFweAY$lambda$0(this.f$0, enabled, isError, interactionSource, modifier3, colors2, shape3, focusedBorderThickness2, unfocusedBorderThickness2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:266:0x0409  */
    /* JADX WARN: Code duplicated, block: B:267:0x0417  */
    /* JADX WARN: Code duplicated, block: B:270:0x04bd  */
    public final void DecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> function2, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, Function2<? super Composer, ? super Integer, Unit> function9, TextFieldColors colors, PaddingValues contentPadding, Function2<? super Composer, ? super Integer, Unit> function10, Composer $composer, final int $changed, final int $changed1, final int i) {
        final boolean isError2;
        int i2;
        int i3;
        Composer $composer2;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final Function2<? super Composer, ? super Integer, Unit> function12;
        final Function2<? super Composer, ? super Integer, Unit> function13;
        final Function2<? super Composer, ? super Integer, Unit> function14;
        final Function2<? super Composer, ? super Integer, Unit> function15;
        final TextFieldColors colors2;
        final PaddingValues contentPadding2;
        final Function2<? super Composer, ? super Integer, Unit> function16;
        final boolean isError3;
        final Function2<? super Composer, ? super Integer, Unit> function17;
        final Function2<? super Composer, ? super Integer, Unit> function18;
        Function2<? super Composer, ? super Integer, Unit> function19;
        Function2<? super Composer, ? super Integer, Unit> function20;
        Function2<? super Composer, ? super Integer, Unit> function21;
        Function2<? super Composer, ? super Integer, Unit> function22;
        Function2<? super Composer, ? super Integer, Unit> function23;
        Function2<? super Composer, ? super Integer, Unit> function24;
        Function2<? super Composer, ? super Integer, Unit> function25;
        final TextFieldColors colors3;
        int $dirty1;
        PaddingValues contentPadding3;
        Function2<? super Composer, ? super Integer, Unit> function26;
        Function2<? super Composer, ? super Integer, Unit> function27;
        Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda;
        Function2<? super Composer, ? super Integer, Unit> function28;
        boolean isError4;
        Function2<? super Composer, ? super Integer, Unit> function29;
        Function2<? super Composer, ? super Integer, Unit> function30;
        int $dirty2;
        TextFieldColors colors4;
        Function2<? super Composer, ? super Integer, Unit> function31;
        Function2<? super Composer, ? super Integer, Unit> function32;
        PaddingValues contentPadding4;
        int $dirty3;
        ComposableLambda composableLambda;
        Composer $composer3 = $composer.startRestartGroup(-1732281618);
        ComposerKt.sourceInformation($composer3, "C(DecorationBox)N(value,innerTextField,enabled,singleLine,visualTransformation,interactionSource,isError,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,colors,contentPadding,container)1170@61544L129,1176@61727L751:TextFieldDefaults.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty4 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(enabled) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(singleLine) ? 2048 : 1024;
        }
        int i4 = 8192;
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer3.changed(interactionSource) ? 131072 : 65536;
        }
        int i5 = i & 64;
        if (i5 != 0) {
            $dirty |= 1572864;
            isError2 = isError;
        } else if (($changed & 1572864) == 0) {
            isError2 = isError;
            $dirty |= $composer3.changed(isError2) ? 1048576 : 524288;
        } else {
            isError2 = isError;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 8388608 : 4194304;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i8 = i & 512;
        if (i8 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changedInstance(function5) ? 536870912 : 268435456;
        }
        int i9 = i & 1024;
        if (i9 != 0) {
            $dirty4 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty4 |= $composer3.changedInstance(function6) ? 4 : 2;
        }
        int i10 = i & 2048;
        if (i10 != 0) {
            $dirty4 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty4 |= $composer3.changedInstance(function7) ? 32 : 16;
        }
        int i11 = i & 4096;
        if (i11 != 0) {
            $dirty4 |= 384;
            i2 = i11;
        } else {
            i2 = i11;
            if (($changed1 & 384) == 0) {
                $dirty4 |= $composer3.changedInstance(function8) ? 256 : 128;
            }
        }
        int i12 = i & 8192;
        if (i12 != 0) {
            $dirty4 |= 3072;
            i3 = i12;
        } else {
            i3 = i12;
            if (($changed1 & 3072) == 0) {
                $dirty4 |= $composer3.changedInstance(function9) ? 2048 : 1024;
            }
        }
        if (($changed1 & 24576) == 0) {
            if ((i & 16384) == 0 && $composer3.changed(colors)) {
                i4 = 16384;
            }
            $dirty4 |= i4;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty4 |= ((i & 32768) == 0 && $composer3.changed(contentPadding)) ? 131072 : 65536;
        }
        int i13 = i & 65536;
        if (i13 != 0) {
            $dirty4 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty4 |= $composer3.changedInstance(function10) ? 1048576 : 524288;
        }
        if ((i & 131072) != 0) {
            $dirty4 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty4 |= $composer3.changed(this) ? 8388608 : 4194304;
        }
        int $dirty5 = $dirty4;
        if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty5 & 4793491) == 4793490) ? false : true, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1154@60977L8,1156@61089L408");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty6 = (i & 16384) != 0 ? $dirty5 & (-57345) : $dirty5;
                if ((32768 & i) != 0) {
                    $dirty6 &= -458753;
                }
                function26 = function3;
                function31 = function5;
                function28 = function6;
                function32 = function7;
                function30 = function8;
                function29 = function9;
                colors4 = colors;
                contentPadding4 = contentPadding;
                function2RememberComposableLambda = function10;
                $dirty2 = $dirty6;
                isError4 = isError2;
                function27 = function4;
            } else {
                if (i5 != 0) {
                    isError2 = false;
                }
                if (i6 == 0) {
                    function19 = function3;
                } else {
                    function19 = null;
                }
                if (i7 == 0) {
                    function20 = function4;
                } else {
                    function20 = null;
                }
                if (i8 == 0) {
                    function21 = function5;
                } else {
                    function21 = null;
                }
                if (i9 == 0) {
                    function22 = function6;
                } else {
                    function22 = null;
                }
                if (i10 == 0) {
                    function23 = function7;
                } else {
                    function23 = null;
                }
                if (i2 == 0) {
                    function24 = function8;
                } else {
                    function24 = null;
                }
                if (i3 == 0) {
                    function25 = function9;
                } else {
                    function25 = null;
                }
                if ((i & 16384) == 0) {
                    colors3 = colors;
                    $dirty1 = $dirty5;
                } else {
                    colors3 = colors($composer3, ($dirty5 >> 21) & 14);
                    $dirty1 = $dirty5 & (-57345);
                }
                if ((i & 32768) == 0) {
                    contentPadding3 = contentPadding;
                } else {
                    contentPadding3 = m2705contentPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty1 &= -458753;
                }
                if (i13 == 0) {
                    PaddingValues contentPadding5 = contentPadding3;
                    function26 = function19;
                    function27 = function20;
                    function2RememberComposableLambda = function10;
                    function28 = function22;
                    isError4 = isError2;
                    function29 = function25;
                    function30 = function24;
                    $dirty2 = $dirty1;
                    colors4 = colors3;
                    function31 = function21;
                    function32 = function23;
                    contentPadding4 = contentPadding5;
                } else {
                    PaddingValues contentPadding6 = contentPadding3;
                    Function2<? super Composer, ? super Integer, Unit> function33 = function19;
                    function27 = function20;
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-896270173, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C1163@61333L5,1157@61103L384:TextFieldDefaults.kt#uh7d8r");
                            if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-896270173, $changed2, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:1157)");
                            }
                            OutlinedTextFieldDefaults.INSTANCE.m2706Container4EFweAY(enabled, isError2, interactionSource, Modifier.INSTANCE, colors3, OutlinedTextFieldDefaults.INSTANCE.getShape($composer4, 6), OutlinedTextFieldDefaults.INSTANCE.m2710getFocusedBorderThicknessD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2713getUnfocusedBorderThicknessD9Ej5fM(), $composer4, 114822144, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                    function28 = function22;
                    isError4 = isError2;
                    function29 = function25;
                    function30 = function24;
                    $dirty2 = $dirty1;
                    function26 = function33;
                    colors4 = colors3;
                    function31 = function21;
                    function32 = function23;
                    contentPadding4 = contentPadding6;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1732281618, $dirty, $dirty2, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox (TextFieldDefaults.kt:1168)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1724716623, "CC(remember):TextFieldDefaults.kt#9igjgp");
            final Function2<? super Composer, ? super Integer, Unit> function34 = function26;
            boolean invalid$iv = (($dirty & 14) == 4) | ((57344 & $dirty) == 16384);
            Function2<? super Composer, ? super Integer, Unit> function35 = function27;
            Object value$iv = $composer3.rememberedValue();
            if (!invalid$iv) {
                $dirty3 = $dirty2;
                if (value$iv != Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                String text = ((TransformedText) value$iv).getText().getText();
                TextFieldType textFieldType = TextFieldType.Outlined;
                TextFieldLabelPosition.Attached attached = new TextFieldLabelPosition.Attached(false, null, null, 7, null);
                if (function34 == null) {
                    $composer3.startReplaceGroup(1927058812);
                    $composer3.endReplaceGroup();
                    composableLambda = null;
                } else {
                    $composer3.startReplaceGroup(1927058813);
                    ComposerKt.sourceInformation($composer3, "*1182@62008L15");
                    Function2 it = ComposableLambdaKt.rememberComposableLambda(-1459717586, true, new Function3<TextFieldLabelScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$DecorationBox$2$1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(TextFieldLabelScope textFieldLabelScope, Composer composer, Integer num) {
                            invoke(textFieldLabelScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(TextFieldLabelScope $this$let, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C1182@62013L8:TextFieldDefaults.kt#uh7d8r");
                            if (!$composer4.shouldExecute(($changed2 & 17) != 16, $changed2 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1459717586, $changed2, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.<anonymous>.<anonymous> (TextFieldDefaults.kt:1182)");
                            }
                            function34.invoke($composer4, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                    $composer3.endReplaceGroup();
                    composableLambda = (Function3) it;
                }
                TextFieldImplKt.CommonDecorationBox(textFieldType, text, function2, attached, composableLambda, function35, function31, function28, function32, function30, function29, singleLine, enabled, isError4, interactionSource, contentPadding4, colors4, function2RememberComposableLambda, $composer3, (($dirty << 3) & 896) | 6 | (($dirty >> 9) & 458752) | (($dirty >> 9) & 3670016) | (($dirty3 << 21) & 29360128) | (($dirty3 << 21) & 234881024) | (($dirty3 << 21) & 1879048192), (($dirty3 >> 9) & 14) | (($dirty >> 6) & 112) | ($dirty & 896) | (($dirty >> 9) & 7168) | (($dirty >> 3) & 57344) | ($dirty3 & 458752) | (($dirty3 << 6) & 3670016) | (($dirty3 << 3) & 29360128));
                $composer2 = $composer3;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function17 = function30;
                function15 = function29;
                contentPadding2 = contentPadding4;
                function16 = function2RememberComposableLambda;
                function13 = function28;
                function14 = function32;
                function11 = function35;
                function12 = function31;
                isError3 = isError4;
                colors2 = colors4;
                function18 = function34;
            } else {
                $dirty3 = $dirty2;
            }
            value$iv = visualTransformation.filter(new AnnotatedString(value, null, 2, null));
            $composer3.updateRememberedValue(value$iv);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            String text2 = ((TransformedText) value$iv).getText().getText();
            TextFieldType textFieldType2 = TextFieldType.Outlined;
            TextFieldLabelPosition.Attached attached2 = new TextFieldLabelPosition.Attached(false, null, null, 7, null);
            if (function34 == null) {
                $composer3.startReplaceGroup(1927058812);
                $composer3.endReplaceGroup();
                composableLambda = null;
            } else {
                $composer3.startReplaceGroup(1927058813);
                ComposerKt.sourceInformation($composer3, "*1182@62008L15");
                Function2 it2 = ComposableLambdaKt.rememberComposableLambda(-1459717586, true, new Function3<TextFieldLabelScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$DecorationBox$2$1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(TextFieldLabelScope textFieldLabelScope, Composer composer, Integer num) {
                        invoke(textFieldLabelScope, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(TextFieldLabelScope $this$let, Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "C1182@62013L8:TextFieldDefaults.kt#uh7d8r");
                        if (!$composer4.shouldExecute(($changed2 & 17) != 16, $changed2 & 1)) {
                            $composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1459717586, $changed2, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox.<anonymous>.<anonymous> (TextFieldDefaults.kt:1182)");
                        }
                        function34.invoke($composer4, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54);
                $composer3.endReplaceGroup();
                composableLambda = (Function3) it2;
            }
            TextFieldImplKt.CommonDecorationBox(textFieldType2, text2, function2, attached2, composableLambda, function35, function31, function28, function32, function30, function29, singleLine, enabled, isError4, interactionSource, contentPadding4, colors4, function2RememberComposableLambda, $composer3, (($dirty << 3) & 896) | 6 | (($dirty >> 9) & 458752) | (($dirty >> 9) & 3670016) | (($dirty3 << 21) & 29360128) | (($dirty3 << 21) & 234881024) | (($dirty3 << 21) & 1879048192), (($dirty3 >> 9) & 14) | (($dirty >> 6) & 112) | ($dirty & 896) | (($dirty >> 9) & 7168) | (($dirty >> 3) & 57344) | ($dirty3 & 458752) | (($dirty3 << 6) & 3670016) | (($dirty3 << 3) & 29360128));
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function17 = function30;
            function15 = function29;
            contentPadding2 = contentPadding4;
            function16 = function2RememberComposableLambda;
            function13 = function28;
            function14 = function32;
            function11 = function35;
            function12 = function31;
            isError3 = isError4;
            colors2 = colors4;
            function18 = function34;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            function11 = function4;
            function12 = function5;
            function13 = function6;
            function14 = function7;
            function15 = function9;
            colors2 = colors;
            contentPadding2 = contentPadding;
            function16 = function10;
            isError3 = isError2;
            function17 = function8;
            function18 = function3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldDefaults.DecorationBox$lambda$3(this.f$0, value, function2, enabled, singleLine, visualTransformation, interactionSource, isError3, function18, function11, function12, function13, function14, function17, function15, colors2, contentPadding2, function16, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: contentPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2705contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults outlinedTextFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldImplKt.getTextFieldPadding();
        }
        return outlinedTextFieldDefaults.m2709contentPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: contentPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2709contentPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m815PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    public final TextFieldColors colors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -471651810, "C(colors)1215@63265L11,1215@63277L30:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-471651810, $changed, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.colors (TextFieldDefaults.kt:1215)");
        }
        TextFieldColors defaultOutlinedTextFieldColors = getDefaultOutlinedTextFieldColors(MaterialTheme.INSTANCE.getColorScheme($composer, 6), $composer, ($changed << 3) & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultOutlinedTextFieldColors;
    }

    /* JADX INFO: renamed from: colors-0hiis_0, reason: not valid java name */
    public final TextFieldColors m2708colors0hiis_0(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long errorTextColor, long focusedContainerColor, long unfocusedContainerColor, long disabledContainerColor, long errorContainerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedBorderColor, long unfocusedBorderColor, long disabledBorderColor, long errorBorderColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long errorPlaceholderColor, long focusedSupportingTextColor, long unfocusedSupportingTextColor, long disabledSupportingTextColor, long errorSupportingTextColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor, Composer $composer, int $changed, int $changed1, int $changed2, int $changed3, int $changed4, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart($composer, 1767617725, "C(colors)N(focusedTextColor:c#ui.graphics.Color,unfocusedTextColor:c#ui.graphics.Color,disabledTextColor:c#ui.graphics.Color,errorTextColor:c#ui.graphics.Color,focusedContainerColor:c#ui.graphics.Color,unfocusedContainerColor:c#ui.graphics.Color,disabledContainerColor:c#ui.graphics.Color,errorContainerColor:c#ui.graphics.Color,cursorColor:c#ui.graphics.Color,errorCursorColor:c#ui.graphics.Color,selectionColors,focusedBorderColor:c#ui.graphics.Color,unfocusedBorderColor:c#ui.graphics.Color,disabledBorderColor:c#ui.graphics.Color,errorBorderColor:c#ui.graphics.Color,focusedLeadingIconColor:c#ui.graphics.Color,unfocusedLeadingIconColor:c#ui.graphics.Color,disabledLeadingIconColor:c#ui.graphics.Color,errorLeadingIconColor:c#ui.graphics.Color,focusedTrailingIconColor:c#ui.graphics.Color,unfocusedTrailingIconColor:c#ui.graphics.Color,disabledTrailingIconColor:c#ui.graphics.Color,errorTrailingIconColor:c#ui.graphics.Color,focusedLabelColor:c#ui.graphics.Color,unfocusedLabelColor:c#ui.graphics.Color,disabledLabelColor:c#ui.graphics.Color,errorLabelColor:c#ui.graphics.Color,focusedPlaceholderColor:c#ui.graphics.Color,unfocusedPlaceholderColor:c#ui.graphics.Color,disabledPlaceholderColor:c#ui.graphics.Color,errorPlaceholderColor:c#ui.graphics.Color,focusedSupportingTextColor:c#ui.graphics.Color,unfocusedSupportingTextColor:c#ui.graphics.Color,disabledSupportingTextColor:c#ui.graphics.Color,errorSupportingTextColor:c#ui.graphics.Color,focusedPrefixColor:c#ui.graphics.Color,unfocusedPrefixColor:c#ui.graphics.Color,disabledPrefixColor:c#ui.graphics.Color,errorPrefixColor:c#ui.graphics.Color,focusedSuffixColor:c#ui.graphics.Color,unfocusedSuffixColor:c#ui.graphics.Color,disabledSuffixColor:c#ui.graphics.Color,errorSuffixColor:c#ui.graphics.Color)1317@70088L11,1317@70100L30:TextFieldDefaults.kt#uh7d8r");
        long focusedTextColor2 = (i & 1) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedTextColor;
        long unfocusedTextColor2 = (i & 2) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedTextColor;
        long disabledTextColor2 = (i & 4) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledTextColor;
        long errorTextColor2 = (i & 8) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorTextColor;
        long focusedContainerColor2 = (i & 16) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedContainerColor;
        long unfocusedContainerColor2 = (i & 32) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedContainerColor;
        long disabledContainerColor2 = (i & 64) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledContainerColor;
        long errorContainerColor2 = (i & 128) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorContainerColor;
        long cursorColor2 = (i & 256) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : cursorColor;
        long errorCursorColor2 = (i & 512) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorCursorColor;
        SelectionColors selectionColors2 = (i & 1024) != 0 ? null : selectionColors;
        long focusedBorderColor2 = (i & 2048) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedBorderColor;
        long unfocusedBorderColor2 = (i & 4096) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedBorderColor;
        long disabledBorderColor2 = (i & 8192) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledBorderColor;
        long errorBorderColor2 = (i & 16384) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorBorderColor;
        long focusedLeadingIconColor2 = (32768 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (65536 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedLeadingIconColor;
        long disabledLeadingIconColor2 = (131072 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledLeadingIconColor;
        long errorLeadingIconColor2 = (262144 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorLeadingIconColor;
        long focusedTrailingIconColor2 = (524288 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (1048576 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedTrailingIconColor;
        long disabledTrailingIconColor2 = (2097152 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledTrailingIconColor;
        long errorTrailingIconColor2 = (4194304 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorTrailingIconColor;
        long focusedLabelColor2 = (8388608 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedLabelColor;
        long unfocusedLabelColor2 = (16777216 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedLabelColor;
        long disabledLabelColor2 = (33554432 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledLabelColor;
        long errorLabelColor2 = (67108864 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorLabelColor;
        long focusedPlaceholderColor2 = (134217728 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedPlaceholderColor;
        long unfocusedPlaceholderColor2 = (268435456 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedPlaceholderColor;
        long disabledPlaceholderColor2 = (536870912 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledPlaceholderColor;
        long errorPlaceholderColor2 = (i & 1073741824) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorPlaceholderColor;
        long focusedSupportingTextColor2 = (i2 & 1) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedSupportingTextColor;
        long unfocusedSupportingTextColor2 = (i2 & 2) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedSupportingTextColor;
        long disabledSupportingTextColor2 = (i2 & 4) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledSupportingTextColor;
        long errorSupportingTextColor2 = (i2 & 8) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorSupportingTextColor;
        long focusedPrefixColor2 = (i2 & 16) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedPrefixColor;
        long unfocusedPrefixColor2 = (i2 & 32) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedPrefixColor;
        long disabledPrefixColor2 = (i2 & 64) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledPrefixColor;
        long errorPrefixColor2 = (i2 & 128) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorPrefixColor;
        long focusedSuffixColor2 = (i2 & 256) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedSuffixColor;
        long unfocusedSuffixColor2 = (i2 & 512) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedSuffixColor;
        long disabledSuffixColor2 = (i2 & 1024) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledSuffixColor;
        long errorSuffixColor2 = (i2 & 2048) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorSuffixColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1767617725, $changed, $changed1, "androidx.compose.material3.OutlinedTextFieldDefaults.colors (TextFieldDefaults.kt:1317)");
        }
        TextFieldColors textFieldColorsM2988copyejIjP34 = getDefaultOutlinedTextFieldColors(MaterialTheme.INSTANCE.getColorScheme($composer, 6), $composer, ($changed4 >> 6) & 112).m2988copyejIjP34(focusedTextColor2, unfocusedTextColor2, disabledTextColor2, errorTextColor2, focusedContainerColor2, unfocusedContainerColor2, disabledContainerColor2, errorContainerColor2, cursorColor2, errorCursorColor2, selectionColors2, focusedBorderColor2, unfocusedBorderColor2, disabledBorderColor2, errorBorderColor2, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, focusedPlaceholderColor2, unfocusedPlaceholderColor2, disabledPlaceholderColor2, errorPlaceholderColor2, focusedSupportingTextColor2, unfocusedSupportingTextColor2, disabledSupportingTextColor2, errorSupportingTextColor2, focusedPrefixColor2, unfocusedPrefixColor2, disabledPrefixColor2, errorPrefixColor2, focusedSuffixColor2, unfocusedSuffixColor2, disabledSuffixColor2, errorSuffixColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return textFieldColorsM2988copyejIjP34;
    }

    public final TextFieldColors getDefaultOutlinedTextFieldColors(ColorScheme $this$defaultOutlinedTextFieldColors, Composer $composer, int $changed) {
        TextFieldColors it;
        TextFieldColors it2;
        ComposerKt.sourceInformationMarkerStart($composer, -292363577, "C(<get-defaultOutlinedTextFieldColors>):TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-292363577, $changed, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.<get-defaultOutlinedTextFieldColors> (TextFieldDefaults.kt:1365)");
        }
        TextFieldColors cachedColors = $this$defaultOutlinedTextFieldColors.getDefaultOutlinedTextFieldColorsCached();
        if (cachedColors == null) {
            $composer.startReplaceGroup(390452338);
            $composer.endReplaceGroup();
            it2 = null;
        } else {
            $composer.startReplaceGroup(390452339);
            ComposerKt.sourceInformation($composer, "*1367@72895L7");
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SelectionColors localTextSelectionColors2 = (SelectionColors) objConsume;
            if (Intrinsics.areEqual(cachedColors.getTextSelectionColors(), localTextSelectionColors2)) {
                it = cachedColors;
            } else {
                it = cachedColors.m2988copyejIjP34(((-1025) & 1) != 0 ? cachedColors.focusedTextColor : 0L, ((-1025) & 2) != 0 ? cachedColors.unfocusedTextColor : 0L, ((-1025) & 4) != 0 ? cachedColors.disabledTextColor : 0L, ((-1025) & 8) != 0 ? cachedColors.errorTextColor : 0L, ((-1025) & 16) != 0 ? cachedColors.focusedContainerColor : 0L, ((-1025) & 32) != 0 ? cachedColors.unfocusedContainerColor : 0L, ((-1025) & 64) != 0 ? cachedColors.disabledContainerColor : 0L, ((-1025) & 128) != 0 ? cachedColors.errorContainerColor : 0L, ((-1025) & 256) != 0 ? cachedColors.cursorColor : 0L, ((-1025) & 512) != 0 ? cachedColors.errorCursorColor : 0L, ((-1025) & 1024) != 0 ? cachedColors.textSelectionColors : localTextSelectionColors2, ((-1025) & 2048) != 0 ? cachedColors.focusedIndicatorColor : 0L, ((-1025) & 4096) != 0 ? cachedColors.unfocusedIndicatorColor : 0L, ((-1025) & 8192) != 0 ? cachedColors.disabledIndicatorColor : 0L, ((-1025) & 16384) != 0 ? cachedColors.errorIndicatorColor : 0L, ((-1025) & 32768) != 0 ? cachedColors.focusedLeadingIconColor : 0L, ((-1025) & 65536) != 0 ? cachedColors.unfocusedLeadingIconColor : 0L, ((-1025) & 131072) != 0 ? cachedColors.disabledLeadingIconColor : 0L, ((-1025) & 262144) != 0 ? cachedColors.errorLeadingIconColor : 0L, ((-1025) & 524288) != 0 ? cachedColors.focusedTrailingIconColor : 0L, ((-1025) & 1048576) != 0 ? cachedColors.unfocusedTrailingIconColor : 0L, ((-1025) & 2097152) != 0 ? cachedColors.disabledTrailingIconColor : 0L, ((-1025) & 4194304) != 0 ? cachedColors.errorTrailingIconColor : 0L, ((-1025) & 8388608) != 0 ? cachedColors.focusedLabelColor : 0L, ((-1025) & 16777216) != 0 ? cachedColors.unfocusedLabelColor : 0L, ((-1025) & 33554432) != 0 ? cachedColors.disabledLabelColor : 0L, ((-1025) & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? cachedColors.errorLabelColor : 0L, ((-1025) & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? cachedColors.focusedPlaceholderColor : 0L, ((-1025) & 268435456) != 0 ? cachedColors.unfocusedPlaceholderColor : 0L, ((-1025) & 536870912) != 0 ? cachedColors.disabledPlaceholderColor : 0L, ((-1025) & 1073741824) != 0 ? cachedColors.errorPlaceholderColor : 0L, ((-1025) & Integer.MIN_VALUE) != 0 ? cachedColors.focusedSupportingTextColor : 0L, (2047 & 1) != 0 ? cachedColors.unfocusedSupportingTextColor : 0L, (2047 & 2) != 0 ? cachedColors.disabledSupportingTextColor : 0L, (2047 & 4) != 0 ? cachedColors.errorSupportingTextColor : 0L, (2047 & 8) != 0 ? cachedColors.focusedPrefixColor : 0L, (2047 & 16) != 0 ? cachedColors.unfocusedPrefixColor : 0L, (2047 & 32) != 0 ? cachedColors.disabledPrefixColor : 0L, (2047 & 64) != 0 ? cachedColors.errorPrefixColor : 0L, (2047 & 128) != 0 ? cachedColors.focusedSuffixColor : 0L, (2047 & 256) != 0 ? cachedColors.unfocusedSuffixColor : 0L, (2047 & 512) != 0 ? cachedColors.disabledSuffixColor : 0L, (2047 & 1024) != 0 ? cachedColors.errorSuffixColor : 0L);
                $this$defaultOutlinedTextFieldColors.setDefaultOutlinedTextFieldColorsCached$material3(it);
            }
            $composer.endReplaceGroup();
            it2 = it;
        }
        if (it2 == null) {
            $composer.startReplaceGroup(-1788321191);
            ComposerKt.sourceInformation($composer, "1389@74318L7");
            long jFromToken = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getFocusInputColor());
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputColor());
            long jFromToken3 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getDisabledInputColor());
            long jM5095copywmQWz5c = Color.m5095copywmQWz5c(jFromToken3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken3) : 0.38f, (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken3) : 0.0f);
            long jFromToken4 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getErrorInputColor());
            long jM5132getTransparent0d7_KjU = Color.INSTANCE.m5132getTransparent0d7_KjU();
            long jM5132getTransparent0d7_KjU2 = Color.INSTANCE.m5132getTransparent0d7_KjU();
            long jM5132getTransparent0d7_KjU3 = Color.INSTANCE.m5132getTransparent0d7_KjU();
            long jM5132getTransparent0d7_KjU4 = Color.INSTANCE.m5132getTransparent0d7_KjU();
            long jFromToken5 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getCaretColor());
            long jFromToken6 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getErrorFocusCaretColor());
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors3 = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer.consume(localTextSelectionColors3);
            ComposerKt.sourceInformationMarkerEnd($composer);
            long jFromToken7 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getFocusOutlineColor());
            long jFromToken8 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getOutlineColor());
            long jFromToken9 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getDisabledOutlineColor());
            long jM5095copywmQWz5c2 = Color.m5095copywmQWz5c(jFromToken9, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken9) : 0.12f, (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken9) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken9) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken9) : 0.0f);
            long jFromToken10 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getErrorOutlineColor());
            long jFromToken11 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getFocusLeadingIconColor());
            long jFromToken12 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getLeadingIconColor());
            long jFromToken13 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getDisabledLeadingIconColor());
            long jM5095copywmQWz5c3 = Color.m5095copywmQWz5c(jFromToken13, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken13) : 0.38f, (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken13) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken13) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken13) : 0.0f);
            long jFromToken14 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getErrorLeadingIconColor());
            long jFromToken15 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getFocusTrailingIconColor());
            long jFromToken16 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getTrailingIconColor());
            long jFromToken17 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getDisabledTrailingIconColor());
            long jM5095copywmQWz5c4 = Color.m5095copywmQWz5c(jFromToken17, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken17) : 0.38f, (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken17) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken17) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken17) : 0.0f);
            long jFromToken18 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getErrorTrailingIconColor());
            long jFromToken19 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getFocusLabelColor());
            long jFromToken20 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getLabelColor());
            long jFromToken21 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getDisabledLabelColor());
            long jM5095copywmQWz5c5 = Color.m5095copywmQWz5c(jFromToken21, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken21) : 0.38f, (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken21) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken21) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken21) : 0.0f);
            long jFromToken22 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getErrorLabelColor());
            long jFromToken23 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor());
            long jFromToken24 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor());
            long jFromToken25 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getDisabledInputColor());
            long jM5095copywmQWz5c6 = Color.m5095copywmQWz5c(jFromToken25, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken25) : 0.38f, (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken25) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken25) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken25) : 0.0f);
            long jFromToken26 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputPlaceholderColor());
            long jFromToken27 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getFocusSupportingColor());
            long jFromToken28 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getSupportingColor());
            long jFromToken29 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getDisabledSupportingColor());
            long jM5095copywmQWz5c7 = Color.m5095copywmQWz5c(jFromToken29, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken29) : 0.38f, (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken29) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken29) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken29) : 0.0f);
            long jFromToken30 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getErrorSupportingColor());
            long jFromToken31 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor());
            long jFromToken32 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor());
            long jFromToken33 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor());
            long jM5095copywmQWz5c8 = Color.m5095copywmQWz5c(jFromToken33, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken33) : 0.38f, (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken33) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken33) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken33) : 0.0f);
            long jFromToken34 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputPrefixColor());
            long jFromToken35 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor());
            long jFromToken36 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor());
            long jFromToken37 = ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor());
            it2 = new TextFieldColors(jFromToken, jFromToken2, jM5095copywmQWz5c, jFromToken4, jM5132getTransparent0d7_KjU, jM5132getTransparent0d7_KjU2, jM5132getTransparent0d7_KjU3, jM5132getTransparent0d7_KjU4, jFromToken5, jFromToken6, (SelectionColors) objConsume2, jFromToken7, jFromToken8, jM5095copywmQWz5c2, jFromToken10, jFromToken11, jFromToken12, jM5095copywmQWz5c3, jFromToken14, jFromToken15, jFromToken16, jM5095copywmQWz5c4, jFromToken18, jFromToken19, jFromToken20, jM5095copywmQWz5c5, jFromToken22, jFromToken23, jFromToken24, jM5095copywmQWz5c6, jFromToken26, jFromToken27, jFromToken28, jM5095copywmQWz5c7, jFromToken30, jFromToken31, jFromToken32, jM5095copywmQWz5c8, jFromToken34, jFromToken35, jFromToken36, Color.m5095copywmQWz5c(jFromToken37, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken37) : 0.38f, (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken37) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken37) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken37) : 0.0f), ColorSchemeKt.fromToken($this$defaultOutlinedTextFieldColors, OutlinedTextFieldTokens.INSTANCE.getInputSuffixColor()), null);
            $this$defaultOutlinedTextFieldColors.setDefaultOutlinedTextFieldColorsCached$material3(it2);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-1788515437);
            $composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return it2;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to OutlinedTextFieldDefaults.Container", replaceWith = @ReplaceWith(expression = "Container(\n    enabled = enabled,\n    isError = isError,\n    interactionSource = interactionSource,\n    colors = colors,\n    shape = shape,\n    focusedBorderThickness = focusedBorderThickness,\n    unfocusedBorderThickness = unfocusedBorderThickness,\n)", imports = {}))
    /* JADX INFO: renamed from: ContainerBox-nbWgWpA, reason: not valid java name */
    public final void m2707ContainerBoxnbWgWpA(final boolean enabled, final boolean isError, final InteractionSource interactionSource, TextFieldColors colors, Shape shape, float focusedBorderThickness, float unfocusedBorderThickness, Composer $composer, final int $changed, final int i) {
        boolean z;
        boolean z2;
        InteractionSource interactionSource2;
        TextFieldColors colors2;
        Shape shape2;
        float focusedBorderThickness2;
        float unfocusedBorderThickness2;
        Composer $composer2;
        final TextFieldColors colors3;
        final Shape shape3;
        final float focusedBorderThickness3;
        final float focusedBorderThickness4;
        int $dirty;
        Composer $composer3 = $composer.startRestartGroup(1461761386);
        ComposerKt.sourceInformation($composer3, "C(ContainerBox)N(enabled,isError,interactionSource,colors,shape,focusedBorderThickness:c#ui.unit.Dp,unfocusedBorderThickness:c#ui.unit.Dp)1482@80172L348:TextFieldDefaults.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            z = enabled;
        } else if (($changed & 6) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = enabled;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            z2 = isError;
        } else if (($changed & 48) == 0) {
            z2 = isError;
            $dirty2 |= $composer3.changed(z2) ? 32 : 16;
        } else {
            z2 = isError;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
            interactionSource2 = interactionSource;
        } else if (($changed & 384) == 0) {
            interactionSource2 = interactionSource;
            $dirty2 |= $composer3.changed(interactionSource2) ? 256 : 128;
        } else {
            interactionSource2 = interactionSource;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                colors2 = colors;
                int i2 = $composer3.changed(colors2) ? 2048 : 1024;
                $dirty2 |= i2;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i2;
        } else {
            colors2 = colors;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i3 = $composer3.changed(shape2) ? 16384 : 8192;
                $dirty2 |= i3;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i3;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                focusedBorderThickness2 = focusedBorderThickness;
                int i4 = $composer3.changed(focusedBorderThickness2) ? 131072 : 65536;
                $dirty2 |= i4;
            } else {
                focusedBorderThickness2 = focusedBorderThickness;
            }
            $dirty2 |= i4;
        } else {
            focusedBorderThickness2 = focusedBorderThickness;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                unfocusedBorderThickness2 = unfocusedBorderThickness;
                int i5 = $composer3.changed(unfocusedBorderThickness2) ? 1048576 : 524288;
                $dirty2 |= i5;
            } else {
                unfocusedBorderThickness2 = unfocusedBorderThickness;
            }
            $dirty2 |= i5;
        } else {
            unfocusedBorderThickness2 = unfocusedBorderThickness;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(this) ? 8388608 : 4194304;
        }
        if ($composer3.shouldExecute((4793491 & $dirty2) != 4793490, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1477@79964L8,1478@80023L5");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if ((i & 8) != 0) {
                    colors2 = colors($composer3, ($dirty2 >> 21) & 14);
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    shape2 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    focusedBorderThickness2 = FocusedBorderThickness;
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    unfocusedBorderThickness2 = UnfocusedBorderThickness;
                    $dirty = $dirty2 & (-3670017);
                } else {
                    $dirty = $dirty2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                $dirty = $dirty2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1461761386, $dirty, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.ContainerBox (TextFieldDefaults.kt:1482)");
            }
            boolean z3 = z;
            InteractionSource interactionSource3 = interactionSource2;
            colors3 = colors2;
            shape3 = shape2;
            focusedBorderThickness3 = focusedBorderThickness2;
            focusedBorderThickness4 = unfocusedBorderThickness2;
            m2706Container4EFweAY(z3, z2, interactionSource3, Modifier.INSTANCE, colors3, shape3, focusedBorderThickness3, focusedBorderThickness4, $composer3, ($dirty & 14) | 3072 | ($dirty & 112) | ($dirty & 896) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128) | (($dirty << 3) & 234881024), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            colors3 = colors2;
            shape3 = shape2;
            focusedBorderThickness3 = focusedBorderThickness2;
            focusedBorderThickness4 = unfocusedBorderThickness2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldDefaults$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldDefaults.ContainerBox_nbWgWpA$lambda$7(this.f$0, enabled, isError, interactionSource, colors3, shape3, focusedBorderThickness3, focusedBorderThickness4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
