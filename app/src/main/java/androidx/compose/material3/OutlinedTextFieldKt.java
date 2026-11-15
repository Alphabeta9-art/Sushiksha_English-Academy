package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.BasicTextFieldKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.KeyboardActionHandler;
import androidx.compose.foundation.text.input.OutputTransformation;
import androidx.compose.foundation.text.input.TextFieldLineLimits;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.internal.FloatProducer;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import com.cloudinary.metadata.MetadataField;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: OutlinedTextField.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000Ö\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a£\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2 \b\u0002\u0010\r\u001a\u001a\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00072\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\b\b\u0002\u0010\"\u001a\u00020#28\b\u0002\u0010$\u001a2\u0012\u0004\u0012\u00020&\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010'0\u0013¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\u0001\u0018\u00010%¢\u0006\u0002\b\u00112\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u0002022\n\b\u0002\u00103\u001a\u0004\u0018\u000104H\u0007¢\u0006\u0002\u00105\u001aØ\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u00106\u001a\u0002072\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u00010\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010;\u001a\u00020<2\b\b\u0002\u0010=\u001a\u00020\u00072\b\b\u0002\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u00020?2\n\b\u0002\u00103\u001a\u0004\u0018\u0001042\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u000200H\u0007¢\u0006\u0002\u0010A\u001aØ\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u00106\u001a\u00020B2\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u00020\u00010\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010;\u001a\u00020<2\b\b\u0002\u0010=\u001a\u00020\u00072\b\b\u0002\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u00020?2\n\b\u0002\u00103\u001a\u0004\u0018\u0001042\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u000200H\u0007¢\u0006\u0002\u0010C\u001a\u0088\u0002\u0010D\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0011\u0010E\u001a\r\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u00102\u0019\u0010\u0012\u001a\u0015\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e¢\u0006\u0002\b\u00102\u0013\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010F\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010G\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0006\u0010=\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010H\u001a\u00020I2\u0012\u0010J\u001a\u000e\u0012\u0004\u0012\u00020K\u0012\u0004\u0012\u00020\u00010\u000e2\u0011\u0010L\u001a\r\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010M\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0006\u0010N\u001a\u000202H\u0001¢\u0006\u0002\u0010O\u001a*\u0010P\u001a\u00020\u0005*\u00020\u00052\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020K0\u00132\u0006\u0010R\u001a\u00020S2\u0006\u0010N\u001a\u000202H\u0000\"\u0010\u0010T\u001a\u00020UX\u0082\u0004¢\u0006\u0004\n\u0002\u0010V¨\u0006W"}, d2 = {"OutlinedTextField", "", "state", "Landroidx/compose/foundation/text/input/TextFieldState;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "readOnly", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "labelPosition", "Landroidx/compose/material3/TextFieldLabelPosition;", MetadataField.LABEL, "Lkotlin/Function1;", "Landroidx/compose/material3/TextFieldLabelScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "placeholder", "Lkotlin/Function0;", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "isError", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "outputTransformation", "Landroidx/compose/foundation/text/input/OutputTransformation;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "onKeyboardAction", "Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "lineLimits", "Landroidx/compose/foundation/text/input/TextFieldLineLimits;", "onTextLayout", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/text/TextLayoutResult;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "getResult", "scrollState", "Landroidx/compose/foundation/ScrollState;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/TextFieldColors;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/material3/TextFieldLabelPosition;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/foundation/text/input/OutputTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Landroidx/compose/foundation/text/input/TextFieldLineLimits;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/ScrollState;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;IIII)V", "value", "", "onValueChange", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "singleLine", "maxLines", "", "minLines", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "Landroidx/compose/ui/text/input/TextFieldValue;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "OutlinedTextFieldLayout", "textField", "leading", "trailing", "labelProgress", "Landroidx/compose/material3/internal/FloatProducer;", "onLabelMeasured", "Landroidx/compose/ui/geometry/Size;", TtmlNode.RUBY_CONTAINER, "supporting", "paddingValues", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/TextFieldLabelPosition;Landroidx/compose/material3/internal/FloatProducer;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;II)V", "outlineCutout", "labelSize", "alignment", "Landroidx/compose/ui/Alignment$Horizontal;", "OutlinedTextFieldInnerPadding", "Landroidx/compose/ui/unit/Dp;", "F", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class OutlinedTextFieldKt {
    private static final float OutlinedTextFieldInnerPadding = Dp.m7905constructorimpl(4);

    static final Unit OutlinedTextField$lambda$2(TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, TextFieldLabelPosition textFieldLabelPosition, Function3 function3, Function2 function2, Function2 function4, Function2 function5, Function2 function6, Function2 function7, Function2 function8, boolean z3, InputTransformation inputTransformation, OutputTransformation outputTransformation, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, TextFieldLineLimits textFieldLineLimits, Function2 function9, ScrollState scrollState, Shape shape, TextFieldColors textFieldColors, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, int i4, Composer composer, int i5) {
        OutlinedTextField(textFieldState, modifier, z, z2, textStyle, textFieldLabelPosition, function3, function2, function4, function5, function6, function7, function8, z3, inputTransformation, outputTransformation, keyboardOptions, keyboardActionHandler, textFieldLineLimits, function9, scrollState, shape, textFieldColors, paddingValues, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit OutlinedTextField$lambda$5(String str, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, Function2 function6, Function2 function7, Function2 function8, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i3, int i4, int i5, int i6, Composer composer, int i7) {
        OutlinedTextField(str, (Function1<? super String, Unit>) function1, modifier, z, z2, textStyle, (Function2<? super Composer, ? super Integer, Unit>) function2, (Function2<? super Composer, ? super Integer, Unit>) function3, (Function2<? super Composer, ? super Integer, Unit>) function4, (Function2<? super Composer, ? super Integer, Unit>) function5, (Function2<? super Composer, ? super Integer, Unit>) function6, (Function2<? super Composer, ? super Integer, Unit>) function7, (Function2<? super Composer, ? super Integer, Unit>) function8, z3, visualTransformation, keyboardOptions, keyboardActions, z4, i, i2, mutableInteractionSource, shape, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
        return Unit.INSTANCE;
    }

    static final Unit OutlinedTextField$lambda$8(TextFieldValue textFieldValue, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, Function2 function6, Function2 function7, Function2 function8, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i3, int i4, int i5, int i6, Composer composer, int i7) {
        OutlinedTextField(textFieldValue, (Function1<? super TextFieldValue, Unit>) function1, modifier, z, z2, textStyle, (Function2<? super Composer, ? super Integer, Unit>) function2, (Function2<? super Composer, ? super Integer, Unit>) function3, (Function2<? super Composer, ? super Integer, Unit>) function4, (Function2<? super Composer, ? super Integer, Unit>) function5, (Function2<? super Composer, ? super Integer, Unit>) function6, (Function2<? super Composer, ? super Integer, Unit>) function7, (Function2<? super Composer, ? super Integer, Unit>) function8, z3, visualTransformation, keyboardOptions, keyboardActions, z4, i, i2, mutableInteractionSource, shape, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
        return Unit.INSTANCE;
    }

    static final Unit OutlinedTextFieldLayout$lambda$20(Modifier modifier, Function2 function2, Function3 function3, Function2 function4, Function2 function5, Function2 function6, Function2 function7, Function2 function8, boolean z, TextFieldLabelPosition textFieldLabelPosition, FloatProducer floatProducer, Function1 function1, Function2 function9, Function2 function10, PaddingValues paddingValues, int i, int i2, Composer composer, int i3) {
        OutlinedTextFieldLayout(modifier, function2, function3, function4, function5, function6, function7, function8, z, textFieldLabelPosition, floatProducer, function1, function9, function10, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    public static final void OutlinedTextField(final TextFieldState state, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, TextFieldLabelPosition labelPosition, Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, boolean isError, InputTransformation inputTransformation, OutputTransformation outputTransformation, KeyboardOptions keyboardOptions, KeyboardActionHandler onKeyboardAction, TextFieldLineLimits lineLimits, Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function9, ScrollState scrollState, Shape shape, TextFieldColors colors, PaddingValues contentPadding, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        TextFieldState textFieldState;
        Modifier modifier2;
        boolean enabled2;
        boolean readOnly2;
        TextStyle textStyle2;
        TextFieldLabelPosition labelPosition2;
        int i2;
        int i3;
        int i4;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final InputTransformation inputTransformation2;
        final OutputTransformation outputTransformation2;
        final KeyboardOptions keyboardOptions2;
        final KeyboardActionHandler onKeyboardAction2;
        final TextFieldLineLimits lineLimits2;
        final Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function12;
        final ScrollState scrollState2;
        final Shape shape2;
        final TextFieldColors colors2;
        final PaddingValues contentPadding2;
        final MutableInteractionSource interactionSource2;
        final boolean enabled3;
        final boolean readOnly3;
        final TextStyle textStyle3;
        final TextFieldLabelPosition labelPosition3;
        final Modifier modifier3;
        final Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function13;
        final Function2<? super Composer, ? super Integer, Unit> function14;
        final Function2<? super Composer, ? super Integer, Unit> function15;
        final Function2<? super Composer, ? super Integer, Unit> function16;
        final Function2<? super Composer, ? super Integer, Unit> function17;
        final boolean isError2;
        Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function18;
        Function2<? super Composer, ? super Integer, Unit> function19;
        Function2<? super Composer, ? super Integer, Unit> function20;
        Function2<? super Composer, ? super Integer, Unit> function21;
        Function2<? super Composer, ? super Integer, Unit> function22;
        Function2<? super Composer, ? super Integer, Unit> function23;
        Function2<? super Composer, ? super Integer, Unit> function24;
        boolean isError3;
        InputTransformation inputTransformation3;
        OutputTransformation outputTransformation3;
        KeyboardOptions keyboardOptions3;
        KeyboardActionHandler onKeyboardAction3;
        TextFieldLineLimits lineLimits3;
        Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function25;
        ScrollState scrollState3;
        Shape shape3;
        TextFieldColors colors3;
        PaddingValues contentPadding3;
        Shape shape4;
        PaddingValues contentPadding4;
        boolean readOnly4;
        Function2<? super Composer, ? super Integer, Unit> function26;
        InputTransformation inputTransformation4;
        OutputTransformation outputTransformation4;
        KeyboardOptions keyboardOptions4;
        KeyboardActionHandler onKeyboardAction4;
        Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function27;
        ScrollState scrollState4;
        Function2<? super Composer, ? super Integer, Unit> function28;
        TextFieldColors colors4;
        Function2<? super Composer, ? super Integer, Unit> function29;
        TextStyle textStyle4;
        Modifier modifier4;
        Function2<? super Composer, ? super Integer, Unit> function30;
        Function2<? super Composer, ? super Integer, Unit> function31;
        boolean isError4;
        MutableInteractionSource interactionSource3;
        Function2<? super Composer, ? super Integer, Unit> function32;
        TextFieldLabelPosition labelPosition4;
        Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function33;
        MutableInteractionSource interactionSource4;
        MutableInteractionSource interactionSource5;
        int i5;
        Composer $composer2 = $composer.startRestartGroup(-2007078942);
        ComposerKt.sourceInformation($composer2, "C(OutlinedTextField)N(state,modifier,enabled,readOnly,textStyle,labelPosition,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,isError,inputTransformation,outputTransformation,keyboardOptions,onKeyboardAction,lineLimits,onTextLayout,scrollState,shape,colors,contentPadding,interactionSource)239@14004L2781,239@13917L2868:OutlinedTextField.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        int $dirty2 = $changed2;
        if ((i & 1) != 0) {
            $dirty |= 6;
            textFieldState = state;
        } else if (($changed & 6) == 0) {
            textFieldState = state;
            $dirty |= $composer2.changed(textFieldState) ? 4 : 2;
        } else {
            textFieldState = state;
        }
        int i6 = i & 2;
        if (i6 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 4;
        if (i7 != 0) {
            $dirty |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty |= $composer2.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        int i8 = i & 8;
        if (i8 != 0) {
            $dirty |= 3072;
            readOnly2 = readOnly;
        } else if (($changed & 3072) == 0) {
            readOnly2 = readOnly;
            $dirty |= $composer2.changed(readOnly2) ? 2048 : 1024;
        } else {
            readOnly2 = readOnly;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                textStyle2 = textStyle;
                if ($composer2.changed(textStyle2)) {
                    i5 = 16384;
                }
                $dirty |= i5;
            } else {
                textStyle2 = textStyle;
            }
            i5 = 8192;
            $dirty |= i5;
        } else {
            textStyle2 = textStyle;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                labelPosition2 = labelPosition;
                int i9 = $composer2.changed(labelPosition2) ? 131072 : 65536;
                $dirty |= i9;
            } else {
                labelPosition2 = labelPosition;
            }
            $dirty |= i9;
        } else {
            labelPosition2 = labelPosition;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 1048576 : 524288;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 8388608 : 4194304;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i13 = i & 512;
        if (i13 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer2.changedInstance(function5) ? 536870912 : 268435456;
        }
        int i14 = i & 1024;
        if (i14 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer2.changedInstance(function6) ? 4 : 2;
        }
        int i15 = i & 2048;
        if (i15 != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer2.changedInstance(function7) ? 32 : 16;
        }
        int i16 = i & 4096;
        if (i16 != 0) {
            $dirty1 |= 384;
            i2 = i16;
        } else {
            i2 = i16;
            if (($changed1 & 384) == 0) {
                $dirty1 |= $composer2.changedInstance(function8) ? 256 : 128;
            }
        }
        int i17 = i & 8192;
        if (i17 != 0) {
            $dirty1 |= 3072;
            i3 = i17;
        } else {
            i3 = i17;
            if (($changed1 & 3072) == 0) {
                $dirty1 |= $composer2.changed(isError) ? 2048 : 1024;
            }
        }
        int i18 = i & 16384;
        if (i18 != 0) {
            $dirty1 |= 24576;
            i4 = i18;
        } else {
            i4 = i18;
            if (($changed1 & 24576) == 0) {
                $dirty1 |= $composer2.changed(inputTransformation) ? 16384 : 8192;
            }
        }
        int i19 = i & 32768;
        if (i19 != 0) {
            $dirty1 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= $composer2.changed(outputTransformation) ? 131072 : 65536;
        }
        int i20 = i & 65536;
        if (i20 != 0) {
            $dirty1 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty1 |= $composer2.changed(keyboardOptions) ? 1048576 : 524288;
        }
        int i21 = i & 131072;
        if (i21 != 0) {
            $dirty1 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty1 |= $composer2.changed(onKeyboardAction) ? 8388608 : 4194304;
        }
        if (($changed1 & 100663296) == 0) {
            $dirty1 |= ((i & 262144) == 0 && $composer2.changed(lineLimits)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i22 = i & 524288;
        if (i22 != 0) {
            $dirty1 |= C.ENCODING_PCM_32BIT;
        } else if (($changed1 & C.ENCODING_PCM_32BIT) == 0) {
            $dirty1 |= $composer2.changedInstance(function9) ? 536870912 : 268435456;
        }
        if (($changed2 & 6) == 0) {
            $dirty2 |= ((i & 1048576) == 0 && $composer2.changed(scrollState)) ? 4 : 2;
        }
        if (($changed2 & 48) == 0) {
            $dirty2 |= ((i & 2097152) == 0 && $composer2.changed(shape)) ? 32 : 16;
        }
        if (($changed2 & 384) == 0) {
            $dirty2 |= ((i & 4194304) == 0 && $composer2.changed(colors)) ? 256 : 128;
        }
        if (($changed2 & 3072) == 0) {
            $dirty2 |= ((i & 8388608) == 0 && $composer2.changed(contentPadding)) ? 2048 : 1024;
        }
        int i23 = i & 16777216;
        if (i23 != 0) {
            $dirty2 |= 24576;
        } else if (($changed2 & 24576) == 0) {
            $dirty2 |= $composer2.changed(interactionSource) ? 16384 : 8192;
        }
        if ($composer2.shouldExecute((($dirty & 306783379) == 306783378 && (306783379 & $dirty1) == 306783378 && ($dirty2 & 9363) == 9362) ? false : true, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "207@12265L7,223@13155L21,224@13223L5,225@13286L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((262144 & i) != 0) {
                    $dirty1 &= -234881025;
                }
                if ((i & 1048576) != 0) {
                    $dirty2 &= -15;
                }
                if ((2097152 & i) != 0) {
                    $dirty2 &= -113;
                }
                if ((i & 4194304) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8388608) != 0) {
                    $dirty2 &= -7169;
                }
                function28 = function2;
                function29 = function4;
                function32 = function5;
                function30 = function6;
                function31 = function7;
                function26 = function8;
                isError4 = isError;
                inputTransformation4 = inputTransformation;
                outputTransformation4 = outputTransformation;
                keyboardOptions4 = keyboardOptions;
                onKeyboardAction4 = onKeyboardAction;
                lineLimits2 = lineLimits;
                function27 = function9;
                scrollState4 = scrollState;
                shape4 = shape;
                colors4 = colors;
                contentPadding4 = contentPadding;
                interactionSource3 = interactionSource;
                readOnly4 = readOnly2;
                textStyle4 = textStyle2;
                modifier4 = modifier2;
                labelPosition4 = labelPosition2;
                function33 = function3;
            } else {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    enabled2 = true;
                }
                if (i8 != 0) {
                    readOnly2 = false;
                }
                if ((i & 16) != 0) {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer2.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty &= -57345;
                    textStyle2 = (TextStyle) objConsume;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    labelPosition2 = new TextFieldLabelPosition.Attached(false, null, null, 7, null);
                }
                if (i10 == 0) {
                    function18 = function3;
                } else {
                    function18 = null;
                }
                if (i11 == 0) {
                    function19 = function2;
                } else {
                    function19 = null;
                }
                if (i12 == 0) {
                    function20 = function4;
                } else {
                    function20 = null;
                }
                if (i13 == 0) {
                    function21 = function5;
                } else {
                    function21 = null;
                }
                if (i14 == 0) {
                    function22 = function6;
                } else {
                    function22 = null;
                }
                if (i15 == 0) {
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
                    isError3 = isError;
                } else {
                    isError3 = false;
                }
                if (i4 == 0) {
                    inputTransformation3 = inputTransformation;
                } else {
                    inputTransformation3 = null;
                }
                if (i19 == 0) {
                    outputTransformation3 = outputTransformation;
                } else {
                    outputTransformation3 = null;
                }
                if (i20 == 0) {
                    keyboardOptions3 = keyboardOptions;
                } else {
                    keyboardOptions3 = KeyboardOptions.INSTANCE.getDefault();
                }
                if (i21 == 0) {
                    onKeyboardAction3 = onKeyboardAction;
                } else {
                    onKeyboardAction3 = null;
                }
                if ((i & 262144) == 0) {
                    lineLimits3 = lineLimits;
                } else {
                    lineLimits3 = TextFieldLineLimits.INSTANCE.getDefault();
                    $dirty1 &= -234881025;
                }
                if (i22 == 0) {
                    function25 = function9;
                } else {
                    function25 = null;
                }
                if ((i & 1048576) != 0) {
                    scrollState3 = ScrollKt.rememberScrollState(0, $composer2, 0, 1);
                    $dirty2 &= -15;
                } else {
                    scrollState3 = scrollState;
                }
                if ((2097152 & i) == 0) {
                    shape3 = shape;
                } else {
                    shape3 = OutlinedTextFieldDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty2 &= -113;
                }
                if ((i & 4194304) != 0) {
                    colors3 = OutlinedTextFieldDefaults.INSTANCE.colors($composer2, 6);
                    $dirty2 &= -897;
                } else {
                    colors3 = colors;
                }
                if ((i & 8388608) == 0) {
                    contentPadding3 = contentPadding;
                } else {
                    contentPadding3 = OutlinedTextFieldDefaults.m2705contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -7169;
                }
                if (i23 == 0) {
                    shape4 = shape3;
                    contentPadding4 = contentPadding3;
                    readOnly4 = readOnly2;
                    function26 = function24;
                    inputTransformation4 = inputTransformation3;
                    outputTransformation4 = outputTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    onKeyboardAction4 = onKeyboardAction3;
                    function27 = function25;
                    scrollState4 = scrollState3;
                    function28 = function19;
                    colors4 = colors3;
                    function29 = function20;
                    textStyle4 = textStyle2;
                    modifier4 = modifier2;
                    function30 = function22;
                    function31 = function23;
                    isError4 = isError3;
                    lineLimits2 = lineLimits3;
                    interactionSource3 = interactionSource;
                    function32 = function21;
                    labelPosition4 = labelPosition2;
                    function33 = function18;
                } else {
                    shape4 = shape3;
                    contentPadding4 = contentPadding3;
                    readOnly4 = readOnly2;
                    function26 = function24;
                    inputTransformation4 = inputTransformation3;
                    outputTransformation4 = outputTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    onKeyboardAction4 = onKeyboardAction3;
                    function27 = function25;
                    scrollState4 = scrollState3;
                    function28 = function19;
                    colors4 = colors3;
                    function29 = function20;
                    textStyle4 = textStyle2;
                    modifier4 = modifier2;
                    function30 = function22;
                    interactionSource3 = null;
                    function31 = function23;
                    isError4 = isError3;
                    lineLimits2 = lineLimits3;
                    function32 = function21;
                    labelPosition4 = labelPosition2;
                    function33 = function18;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2007078942, $dirty, $dirty1, "androidx.compose.material3.OutlinedTextField (OutlinedTextField.kt:228)");
            }
            if (interactionSource3 == null) {
                $composer2.startReplaceGroup(1647415065);
                ComposerKt.sourceInformation($composer2, "230@13518L39");
                ComposerKt.sourceInformationMarkerStart($composer2, -362499575, "CC(remember):OutlinedTextField.kt#9igjgp");
                interactionSource4 = interactionSource3;
                Object it$iv = $composer2.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
                interactionSource5 = (MutableInteractionSource) it$iv;
            } else {
                interactionSource4 = interactionSource3;
                $composer2.startReplaceGroup(-362500226);
                $composer2.endReplaceGroup();
                interactionSource5 = interactionSource4;
            }
            $composer2.startReplaceGroup(-362494116);
            ComposerKt.sourceInformation($composer2, "*234@13742L25");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle4.m7358getColor0d7_KjU();
            if (($this$takeOrElse_u2dDxMtmZc$iv != 16 ? 1 : 0) == 0) {
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource5, $composer2, 0).getValue().booleanValue();
                $this$takeOrElse_u2dDxMtmZc$iv = colors4.m3039textColorXeAY9LY$material3(enabled2, isError4, focused);
            }
            $composer2.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle4.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            TextFieldColors colors5 = colors4;
            boolean enabled4 = enabled2;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors4.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(-416142558, true, new AnonymousClass1(modifier4, function33, labelPosition4, isError4, colors5, textFieldState, enabled4, lineLimits2, outputTransformation4, interactionSource5, function28, function29, function32, function30, function31, function26, contentPadding4, readOnly4, inputTransformation4, mergedTextStyle, keyboardOptions4, onKeyboardAction4, function27, scrollState4, shape4), $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            labelPosition3 = labelPosition4;
            isError2 = isError4;
            enabled3 = enabled4;
            function10 = function28;
            function14 = function29;
            function15 = function32;
            function11 = function30;
            function16 = function31;
            contentPadding2 = contentPadding4;
            inputTransformation2 = inputTransformation4;
            keyboardOptions2 = keyboardOptions4;
            onKeyboardAction2 = onKeyboardAction4;
            scrollState2 = scrollState4;
            shape2 = shape4;
            interactionSource2 = interactionSource4;
            function13 = function33;
            colors2 = colors5;
            outputTransformation2 = outputTransformation4;
            function17 = function26;
            function12 = function27;
            textStyle3 = textStyle4;
            readOnly3 = readOnly4;
        } else {
            $composer2.skipToGroupEnd();
            function10 = function2;
            function11 = function6;
            inputTransformation2 = inputTransformation;
            outputTransformation2 = outputTransformation;
            keyboardOptions2 = keyboardOptions;
            onKeyboardAction2 = onKeyboardAction;
            lineLimits2 = lineLimits;
            function12 = function9;
            scrollState2 = scrollState;
            shape2 = shape;
            colors2 = colors;
            contentPadding2 = contentPadding;
            interactionSource2 = interactionSource;
            enabled3 = enabled2;
            readOnly3 = readOnly2;
            textStyle3 = textStyle2;
            labelPosition3 = labelPosition2;
            modifier3 = modifier2;
            function13 = function3;
            function14 = function4;
            function15 = function5;
            function16 = function7;
            function17 = function8;
            isError2 = isError;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldKt.OutlinedTextField$lambda$2(state, modifier3, enabled3, readOnly3, textStyle3, labelPosition3, function13, function10, function14, function15, function11, function16, function17, isError2, inputTransformation2, outputTransformation2, keyboardOptions2, onKeyboardAction2, lineLimits2, function12, scrollState2, shape2, colors2, contentPadding2, interactionSource2, $changed, $changed1, $changed2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1, reason: invalid class name */
    /* JADX INFO: compiled from: OutlinedTextField.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ InputTransformation $inputTransformation;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ Function3<TextFieldLabelScope, Composer, Integer, Unit> $label;
        final /* synthetic */ TextFieldLabelPosition $labelPosition;
        final /* synthetic */ Function2<Composer, Integer, Unit> $leadingIcon;
        final /* synthetic */ TextFieldLineLimits $lineLimits;
        final /* synthetic */ TextStyle $mergedTextStyle;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ KeyboardActionHandler $onKeyboardAction;
        final /* synthetic */ Function2<Density, Function0<TextLayoutResult>, Unit> $onTextLayout;
        final /* synthetic */ OutputTransformation $outputTransformation;
        final /* synthetic */ Function2<Composer, Integer, Unit> $placeholder;
        final /* synthetic */ Function2<Composer, Integer, Unit> $prefix;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ ScrollState $scrollState;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ TextFieldState $state;
        final /* synthetic */ Function2<Composer, Integer, Unit> $suffix;
        final /* synthetic */ Function2<Composer, Integer, Unit> $supportingText;
        final /* synthetic */ Function2<Composer, Integer, Unit> $trailingIcon;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Modifier modifier, Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function3, TextFieldLabelPosition textFieldLabelPosition, boolean z, TextFieldColors textFieldColors, TextFieldState textFieldState, boolean z2, TextFieldLineLimits textFieldLineLimits, OutputTransformation outputTransformation, MutableInteractionSource mutableInteractionSource, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, PaddingValues paddingValues, boolean z3, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function9, ScrollState scrollState, Shape shape) {
            this.$modifier = modifier;
            this.$label = function3;
            this.$labelPosition = textFieldLabelPosition;
            this.$isError = z;
            this.$colors = textFieldColors;
            this.$state = textFieldState;
            this.$enabled = z2;
            this.$lineLimits = textFieldLineLimits;
            this.$outputTransformation = outputTransformation;
            this.$interactionSource = mutableInteractionSource;
            this.$placeholder = function2;
            this.$leadingIcon = function4;
            this.$trailingIcon = function5;
            this.$prefix = function6;
            this.$suffix = function7;
            this.$supportingText = function8;
            this.$contentPadding = paddingValues;
            this.$readOnly = z3;
            this.$inputTransformation = inputTransformation;
            this.$mergedTextStyle = textStyle;
            this.$keyboardOptions = keyboardOptions;
            this.$onKeyboardAction = keyboardActionHandler;
            this.$onTextLayout = function9;
            this.$scrollState = scrollState;
            this.$shape = shape;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            Modifier.Companion companionM823paddingqDBjuR0$default;
            ComposerKt.sourceInformation($composer, "C255@14761L38,290@16390L359,273@15619L1149,240@14014L2765:OutlinedTextField.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-416142558, $changed, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:240)");
            }
            Modifier modifier = this.$modifier;
            if (this.$label != null && !(this.$labelPosition instanceof TextFieldLabelPosition.Above)) {
                $composer.startReplaceGroup(-2027097767);
                ComposerKt.sourceInformation($composer, "249@14513L2,250@14563L26");
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, -481023420, "CC(remember):OutlinedTextField.kt#9igjgp");
                Object it$iv = $composer.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = new Function1() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Unit.INSTANCE;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                companionM823paddingqDBjuR0$default = PaddingKt.m823paddingqDBjuR0$default(SemanticsModifierKt.semantics(companion, true, (Function1) it$iv), 0.0f, TextFieldImplKt.minimizedLabelHalfHeight($composer, 0), 0.0f, 0.0f, 13, null);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-2026714080);
                $composer.endReplaceGroup();
                companionM823paddingqDBjuR0$default = Modifier.INSTANCE;
            }
            Modifier modifierThen = modifier.then(companionM823paddingqDBjuR0$default);
            boolean z = this.$isError;
            Strings.Companion companion2 = Strings.INSTANCE;
            Modifier modifierM849defaultMinSizeVpY3zN4 = SizeKt.m849defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifierThen, z, Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer, 0)), OutlinedTextFieldDefaults.INSTANCE.m2712getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2711getMinHeightD9Ej5fM());
            SolidColor solidColor = new SolidColor(this.$colors.m2989cursorColorvNxB06k$material3(this.$isError), null);
            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
            TextFieldState textFieldState = this.$state;
            boolean z2 = this.$enabled;
            TextFieldLineLimits textFieldLineLimits = this.$lineLimits;
            OutputTransformation outputTransformation = this.$outputTransformation;
            MutableInteractionSource mutableInteractionSource = this.$interactionSource;
            TextFieldLabelPosition textFieldLabelPosition = this.$labelPosition;
            Function3<TextFieldLabelScope, Composer, Integer, Unit> function3 = this.$label;
            Function2<Composer, Integer, Unit> function2 = this.$placeholder;
            Function2<Composer, Integer, Unit> function4 = this.$leadingIcon;
            Function2<Composer, Integer, Unit> function5 = this.$trailingIcon;
            Function2<Composer, Integer, Unit> function6 = this.$prefix;
            Function2<Composer, Integer, Unit> function7 = this.$suffix;
            Function2<Composer, Integer, Unit> function8 = this.$supportingText;
            boolean z3 = this.$isError;
            TextFieldColors textFieldColors = this.$colors;
            PaddingValues paddingValues = this.$contentPadding;
            final boolean z4 = this.$enabled;
            final boolean z5 = this.$isError;
            final MutableInteractionSource mutableInteractionSource2 = this.$interactionSource;
            final TextFieldColors textFieldColors2 = this.$colors;
            final Shape shape = this.$shape;
            BasicTextFieldKt.BasicTextField(this.$state, modifierM849defaultMinSizeVpY3zN4, this.$enabled, this.$readOnly, this.$inputTransformation, this.$mergedTextStyle, this.$keyboardOptions, this.$onKeyboardAction, this.$lineLimits, this.$onTextLayout, this.$interactionSource, solidColor, this.$outputTransformation, outlinedTextFieldDefaults.decorator(textFieldState, z2, textFieldLineLimits, outputTransformation, mutableInteractionSource, textFieldLabelPosition, function3, function2, function4, function5, function6, function7, function8, z3, textFieldColors, paddingValues, ComposableLambdaKt.rememberComposableLambda(-98391231, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.1.2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer2, int $changed2) {
                    ComposerKt.sourceInformation($composer2, "C291@16442L285:OutlinedTextField.kt#uh7d8r");
                    if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-98391231, $changed2, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:291)");
                    }
                    OutlinedTextFieldDefaults.INSTANCE.m2706Container4EFweAY(z4, z5, mutableInteractionSource2, null, textFieldColors2, shape, 0.0f, 0.0f, $composer2, 100663296, 200);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer, 54), $composer, 0, 14155776, 0), this.$scrollState, $composer, 0, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static final void OutlinedTextField(final String value, final Function1<? super String, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        String str;
        Modifier modifier2;
        boolean enabled2;
        boolean readOnly2;
        TextStyle textStyle2;
        Function2<? super Composer, ? super Integer, Unit> function9;
        int i2;
        int i3;
        int i4;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        Function2<? super Composer, ? super Integer, Unit> function11;
        final Function2<? super Composer, ? super Integer, Unit> function12;
        final boolean isError2;
        final KeyboardOptions keyboardOptions2;
        final KeyboardActions keyboardActions2;
        final boolean enabled3;
        final int maxLines2;
        final int minLines2;
        final MutableInteractionSource interactionSource2;
        final Shape shape2;
        final TextFieldColors colors2;
        final boolean enabled4;
        final boolean readOnly3;
        final TextStyle textStyle3;
        final Function2<? super Composer, ? super Integer, Unit> function13;
        final Function2<? super Composer, ? super Integer, Unit> function14;
        final Function2<? super Composer, ? super Integer, Unit> function15;
        final Function2<? super Composer, ? super Integer, Unit> function16;
        final VisualTransformation visualTransformation2;
        Function2<? super Composer, ? super Integer, Unit> function17;
        Function2<? super Composer, ? super Integer, Unit> function18;
        Function2<? super Composer, ? super Integer, Unit> function19;
        Function2<? super Composer, ? super Integer, Unit> function20;
        Function2<? super Composer, ? super Integer, Unit> function21;
        Function2<? super Composer, ? super Integer, Unit> function22;
        boolean isError3;
        VisualTransformation visualTransformation3;
        KeyboardOptions keyboardOptions3;
        KeyboardActions keyboardActions3;
        boolean singleLine2;
        int maxLines3;
        int minLines3;
        MutableInteractionSource interactionSource3;
        Shape shape3;
        Function2<? super Composer, ? super Integer, Unit> function23;
        Shape shape4;
        Function2<? super Composer, ? super Integer, Unit> function24;
        TextStyle textStyle4;
        Function2<? super Composer, ? super Integer, Unit> function25;
        Function2<? super Composer, ? super Integer, Unit> function26;
        Function2<? super Composer, ? super Integer, Unit> function27;
        Function2<? super Composer, ? super Integer, Unit> function28;
        VisualTransformation visualTransformation4;
        MutableInteractionSource interactionSource4;
        TextFieldColors colors3;
        int minLines4;
        int minLines5;
        boolean singleLine3;
        KeyboardActions keyboardActions4;
        KeyboardOptions keyboardOptions4;
        boolean readOnly4;
        Modifier modifier3;
        MutableInteractionSource interactionSource5;
        MutableInteractionSource interactionSource6;
        int i5;
        Composer $composer2 = $composer.startRestartGroup(1901501544);
        ComposerKt.sourceInformation($composer2, "C(OutlinedTextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,isError,visualTransformation,keyboardOptions,keyboardActions,singleLine,maxLines,minLines,interactionSource,shape,colors)404@23117L2830,404@23030L2917:OutlinedTextField.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        int $dirty2 = $changed2;
        if ((i & 1) != 0) {
            $dirty |= 6;
            str = value;
        } else if (($changed & 6) == 0) {
            str = value;
            $dirty |= $composer2.changed(str) ? 4 : 2;
        } else {
            str = value;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty |= $composer2.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty |= 24576;
            readOnly2 = readOnly;
        } else if (($changed & 24576) == 0) {
            readOnly2 = readOnly;
            $dirty |= $composer2.changed(readOnly2) ? 16384 : 8192;
        } else {
            readOnly2 = readOnly;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                textStyle2 = textStyle;
                if ($composer2.changed(textStyle2)) {
                    i5 = 131072;
                }
                $dirty |= i5;
            } else {
                textStyle2 = textStyle;
            }
            i5 = 65536;
            $dirty |= i5;
        } else {
            textStyle2 = textStyle;
        }
        int i9 = i & 64;
        if (i9 != 0) {
            $dirty |= 1572864;
            function9 = function2;
        } else if (($changed & 1572864) == 0) {
            function9 = function2;
            $dirty |= $composer2.changedInstance(function9) ? 1048576 : 524288;
        } else {
            function9 = function2;
        }
        int i10 = i & 128;
        if (i10 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 8388608 : 4194304;
        }
        int i11 = i & 256;
        if (i11 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i12 = i & 512;
        if (i12 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer2.changedInstance(function5) ? 536870912 : 268435456;
        }
        int i13 = i & 1024;
        if (i13 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer2.changedInstance(function6) ? 4 : 2;
        }
        int i14 = i & 2048;
        if (i14 != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer2.changedInstance(function7) ? 32 : 16;
        }
        int i15 = i & 4096;
        if (i15 != 0) {
            $dirty1 |= 384;
            i2 = i15;
        } else {
            i2 = i15;
            if (($changed1 & 384) == 0) {
                $dirty1 |= $composer2.changedInstance(function8) ? 256 : 128;
            }
        }
        int i16 = i & 8192;
        if (i16 != 0) {
            $dirty1 |= 3072;
            i3 = i16;
        } else {
            i3 = i16;
            if (($changed1 & 3072) == 0) {
                $dirty1 |= $composer2.changed(isError) ? 2048 : 1024;
            }
        }
        int i17 = i & 16384;
        if (i17 != 0) {
            $dirty1 |= 24576;
            i4 = i17;
        } else {
            i4 = i17;
            if (($changed1 & 24576) == 0) {
                $dirty1 |= $composer2.changed(visualTransformation) ? 16384 : 8192;
            }
        }
        int i18 = i & 32768;
        if (i18 != 0) {
            $dirty1 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= $composer2.changed(keyboardOptions) ? 131072 : 65536;
        }
        int i19 = i & 65536;
        if (i19 != 0) {
            $dirty1 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty1 |= $composer2.changed(keyboardActions) ? 1048576 : 524288;
        }
        int i20 = i & 131072;
        if (i20 != 0) {
            $dirty1 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty1 |= $composer2.changed(singleLine) ? 8388608 : 4194304;
        }
        if (($changed1 & 100663296) == 0) {
            $dirty1 |= ((i & 262144) == 0 && $composer2.changed(maxLines)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i21 = i & 524288;
        if (i21 != 0) {
            $dirty1 |= C.ENCODING_PCM_32BIT;
        } else if (($changed1 & C.ENCODING_PCM_32BIT) == 0) {
            $dirty1 |= $composer2.changed(minLines) ? 536870912 : 268435456;
        }
        int i22 = i & 1048576;
        if (i22 != 0) {
            $dirty2 |= 6;
        } else if (($changed2 & 6) == 0) {
            $dirty2 |= $composer2.changed(interactionSource) ? 4 : 2;
        }
        if (($changed2 & 48) == 0) {
            $dirty2 |= ((i & 2097152) == 0 && $composer2.changed(shape)) ? 32 : 16;
        }
        if (($changed2 & 384) == 0) {
            $dirty2 |= ((i & 4194304) == 0 && $composer2.changed(colors)) ? 256 : 128;
        }
        if ($composer2.shouldExecute((($dirty & 306783379) == 306783378 && (306783379 & $dirty1) == 306783378 && ($dirty2 & 147) == 146) ? false : true, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "375@21669L7,391@22473L5,392@22536L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((262144 & i) != 0) {
                    $dirty1 &= -234881025;
                }
                if ((2097152 & i) != 0) {
                    $dirty2 &= -113;
                }
                if ((i & 4194304) != 0) {
                    $dirty2 &= -897;
                }
                function23 = function3;
                function11 = function4;
                function24 = function5;
                function25 = function6;
                function27 = function7;
                function28 = function8;
                isError2 = isError;
                visualTransformation4 = visualTransformation;
                keyboardOptions4 = keyboardOptions;
                keyboardActions4 = keyboardActions;
                singleLine3 = singleLine;
                minLines5 = maxLines;
                minLines4 = minLines;
                interactionSource4 = interactionSource;
                shape4 = shape;
                readOnly4 = readOnly2;
                textStyle4 = textStyle2;
                function26 = function9;
                colors3 = colors;
                modifier3 = modifier2;
            } else {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    enabled2 = true;
                }
                if (i8 != 0) {
                    readOnly2 = false;
                }
                if ((i & 32) != 0) {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer2.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty &= -458753;
                    textStyle2 = (TextStyle) objConsume;
                }
                if (i9 != 0) {
                    function9 = null;
                }
                if (i10 == 0) {
                    function17 = function3;
                } else {
                    function17 = null;
                }
                if (i11 == 0) {
                    function18 = function4;
                } else {
                    function18 = null;
                }
                if (i12 == 0) {
                    function19 = function5;
                } else {
                    function19 = null;
                }
                if (i13 == 0) {
                    function20 = function6;
                } else {
                    function20 = null;
                }
                if (i14 == 0) {
                    function21 = function7;
                } else {
                    function21 = null;
                }
                if (i2 == 0) {
                    function22 = function8;
                } else {
                    function22 = null;
                }
                if (i3 == 0) {
                    isError3 = isError;
                } else {
                    isError3 = false;
                }
                if (i4 == 0) {
                    visualTransformation3 = visualTransformation;
                } else {
                    visualTransformation3 = VisualTransformation.INSTANCE.getNone();
                }
                if (i18 == 0) {
                    keyboardOptions3 = keyboardOptions;
                } else {
                    keyboardOptions3 = KeyboardOptions.INSTANCE.getDefault();
                }
                if (i19 == 0) {
                    keyboardActions3 = keyboardActions;
                } else {
                    keyboardActions3 = KeyboardActions.INSTANCE.getDefault();
                }
                if (i20 == 0) {
                    singleLine2 = singleLine;
                } else {
                    singleLine2 = false;
                }
                if ((i & 262144) == 0) {
                    maxLines3 = maxLines;
                } else {
                    maxLines3 = singleLine2 ? 1 : Integer.MAX_VALUE;
                    $dirty1 &= -234881025;
                }
                if (i21 == 0) {
                    minLines3 = minLines;
                } else {
                    minLines3 = 1;
                }
                if (i22 == 0) {
                    interactionSource3 = interactionSource;
                } else {
                    interactionSource3 = null;
                }
                Function2<? super Composer, ? super Integer, Unit> function29 = function17;
                if ((i & 2097152) != 0) {
                    shape3 = OutlinedTextFieldDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty2 &= -113;
                } else {
                    shape3 = shape;
                }
                if ((i & 4194304) == 0) {
                    function23 = function29;
                    function11 = function18;
                    shape4 = shape3;
                    function24 = function19;
                    textStyle4 = textStyle2;
                    function25 = function20;
                    function26 = function9;
                    function27 = function21;
                    function28 = function22;
                    visualTransformation4 = visualTransformation3;
                    isError2 = isError3;
                    interactionSource4 = interactionSource3;
                    colors3 = colors;
                    minLines4 = minLines3;
                    minLines5 = maxLines3;
                    singleLine3 = singleLine2;
                    keyboardActions4 = keyboardActions3;
                    keyboardOptions4 = keyboardOptions3;
                    readOnly4 = readOnly2;
                    modifier3 = modifier2;
                } else {
                    Shape shape5 = shape3;
                    $dirty2 &= -897;
                    Function2<? super Composer, ? super Integer, Unit> function30 = function9;
                    colors3 = OutlinedTextFieldDefaults.INSTANCE.colors($composer2, 6);
                    textStyle4 = textStyle2;
                    function26 = function30;
                    function23 = function29;
                    function11 = function18;
                    shape4 = shape5;
                    function24 = function19;
                    function25 = function20;
                    function27 = function21;
                    function28 = function22;
                    visualTransformation4 = visualTransformation3;
                    isError2 = isError3;
                    interactionSource4 = interactionSource3;
                    minLines4 = minLines3;
                    minLines5 = maxLines3;
                    singleLine3 = singleLine2;
                    keyboardActions4 = keyboardActions3;
                    keyboardOptions4 = keyboardOptions3;
                    readOnly4 = readOnly2;
                    modifier3 = modifier2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1901501544, $dirty, $dirty1, "androidx.compose.material3.OutlinedTextField (OutlinedTextField.kt:393)");
            }
            if (interactionSource4 == null) {
                $composer2.startReplaceGroup(1310051731);
                ComposerKt.sourceInformation($composer2, "395@22631L39");
                ComposerKt.sourceInformationMarkerStart($composer2, 1981922383, "CC(remember):OutlinedTextField.kt#9igjgp");
                interactionSource5 = interactionSource4;
                Object it$iv = $composer2.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
                interactionSource6 = (MutableInteractionSource) it$iv;
            } else {
                interactionSource5 = interactionSource4;
                $composer2.startReplaceGroup(1981921732);
                $composer2.endReplaceGroup();
                interactionSource6 = interactionSource5;
            }
            $composer2.startReplaceGroup(1981927842);
            ComposerKt.sourceInformation($composer2, "*399@22855L25");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle4.m7358getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource6, $composer2, 0).getValue().booleanValue();
                $this$takeOrElse_u2dDxMtmZc$iv = colors3.m3039textColorXeAY9LY$material3(enabled2, isError2, focused);
            }
            $composer2.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle4.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            boolean enabled5 = enabled2;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors3.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(1874034984, true, new AnonymousClass3(modifier3, function26, isError2, colors3, str, function1, enabled5, readOnly4, mergedTextStyle, keyboardOptions4, keyboardActions4, singleLine3, minLines5, minLines4, visualTransformation4, interactionSource6, function23, function11, function24, function25, function27, function28, shape4), $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            keyboardOptions2 = keyboardOptions4;
            keyboardActions2 = keyboardActions4;
            minLines2 = minLines4;
            function10 = function23;
            function14 = function24;
            function12 = function25;
            function15 = function27;
            shape2 = shape4;
            interactionSource2 = interactionSource5;
            function13 = function26;
            readOnly3 = readOnly4;
            maxLines2 = minLines5;
            function16 = function28;
            textStyle3 = textStyle4;
            enabled4 = enabled5;
            enabled3 = singleLine3;
            colors2 = colors3;
            visualTransformation2 = visualTransformation4;
        } else {
            $composer2.skipToGroupEnd();
            function10 = function3;
            function11 = function4;
            function12 = function6;
            isError2 = isError;
            keyboardOptions2 = keyboardOptions;
            keyboardActions2 = keyboardActions;
            enabled3 = singleLine;
            maxLines2 = maxLines;
            minLines2 = minLines;
            interactionSource2 = interactionSource;
            shape2 = shape;
            colors2 = colors;
            enabled4 = enabled2;
            readOnly3 = readOnly2;
            textStyle3 = textStyle2;
            function13 = function9;
            function14 = function5;
            function15 = function7;
            function16 = function8;
            visualTransformation2 = visualTransformation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final Function2<? super Composer, ? super Integer, Unit> function31 = function11;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldKt.OutlinedTextField$lambda$5(value, function1, modifier4, enabled4, readOnly3, textStyle3, function13, function10, function31, function14, function12, function15, function16, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, enabled3, maxLines2, minLines2, interactionSource2, shape2, colors2, $changed, $changed1, $changed2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3, reason: invalid class name */
    /* JADX INFO: compiled from: OutlinedTextField.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass3 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ Function2<Composer, Integer, Unit> $label;
        final /* synthetic */ Function2<Composer, Integer, Unit> $leadingIcon;
        final /* synthetic */ int $maxLines;
        final /* synthetic */ TextStyle $mergedTextStyle;
        final /* synthetic */ int $minLines;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ Function1<String, Unit> $onValueChange;
        final /* synthetic */ Function2<Composer, Integer, Unit> $placeholder;
        final /* synthetic */ Function2<Composer, Integer, Unit> $prefix;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ Function2<Composer, Integer, Unit> $suffix;
        final /* synthetic */ Function2<Composer, Integer, Unit> $supportingText;
        final /* synthetic */ Function2<Composer, Integer, Unit> $trailingIcon;
        final /* synthetic */ String $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, boolean z, TextFieldColors textFieldColors, String str, Function1<? super String, Unit> function1, boolean z2, boolean z3, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, VisualTransformation visualTransformation, MutableInteractionSource mutableInteractionSource, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, Shape shape) {
            this.$modifier = modifier;
            this.$label = function2;
            this.$isError = z;
            this.$colors = textFieldColors;
            this.$value = str;
            this.$onValueChange = function1;
            this.$enabled = z2;
            this.$readOnly = z3;
            this.$mergedTextStyle = textStyle;
            this.$keyboardOptions = keyboardOptions;
            this.$keyboardActions = keyboardActions;
            this.$singleLine = z4;
            this.$maxLines = i;
            this.$minLines = i2;
            this.$visualTransformation = visualTransformation;
            this.$interactionSource = mutableInteractionSource;
            this.$placeholder = function3;
            this.$leadingIcon = function4;
            this.$trailingIcon = function5;
            this.$prefix = function6;
            this.$suffix = function7;
            this.$supportingText = function8;
            this.$shape = shape;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            Modifier.Companion companionM823paddingqDBjuR0$default;
            ComposerKt.sourceInformation($composer, "C420@23824L38,438@24644L1286,405@23127L2814:OutlinedTextField.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1874034984, $changed, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:405)");
            }
            Modifier modifier = this.$modifier;
            if (this.$label != null) {
                $composer.startReplaceGroup(-903490605);
                ComposerKt.sourceInformation($composer, "414@23576L2,415@23626L26");
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 1356337290, "CC(remember):OutlinedTextField.kt#9igjgp");
                Object it$iv = $composer.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = new Function1() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Unit.INSTANCE;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                companionM823paddingqDBjuR0$default = PaddingKt.m823paddingqDBjuR0$default(SemanticsModifierKt.semantics(companion, true, (Function1) it$iv), 0.0f, TextFieldImplKt.minimizedLabelHalfHeight($composer, 0), 0.0f, 0.0f, 13, null);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-903106918);
                $composer.endReplaceGroup();
                companionM823paddingqDBjuR0$default = Modifier.INSTANCE;
            }
            Modifier modifierThen = modifier.then(companionM823paddingqDBjuR0$default);
            boolean z = this.$isError;
            Strings.Companion companion2 = Strings.INSTANCE;
            Modifier modifierM849defaultMinSizeVpY3zN4 = SizeKt.m849defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifierThen, z, Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer, 0)), OutlinedTextFieldDefaults.INSTANCE.m2712getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2711getMinHeightD9Ej5fM());
            SolidColor solidColor = new SolidColor(this.$colors.m2989cursorColorvNxB06k$material3(this.$isError), null);
            final String str = this.$value;
            final boolean z2 = this.$enabled;
            final boolean z3 = this.$singleLine;
            final VisualTransformation visualTransformation = this.$visualTransformation;
            final MutableInteractionSource mutableInteractionSource = this.$interactionSource;
            final boolean z4 = this.$isError;
            final Function2<Composer, Integer, Unit> function2 = this.$label;
            final Function2<Composer, Integer, Unit> function3 = this.$placeholder;
            final Function2<Composer, Integer, Unit> function4 = this.$leadingIcon;
            final Function2<Composer, Integer, Unit> function5 = this.$trailingIcon;
            final Function2<Composer, Integer, Unit> function6 = this.$prefix;
            final Function2<Composer, Integer, Unit> function7 = this.$suffix;
            final Function2<Composer, Integer, Unit> function8 = this.$supportingText;
            final TextFieldColors textFieldColors = this.$colors;
            final Shape shape = this.$shape;
            BasicTextFieldKt.BasicTextField(this.$value, this.$onValueChange, modifierM849defaultMinSizeVpY3zN4, this.$enabled, this.$readOnly, this.$mergedTextStyle, this.$keyboardOptions, this.$keyboardActions, this.$singleLine, this.$maxLines, this.$minLines, this.$visualTransformation, (Function1<? super TextLayoutResult, Unit>) null, this.$interactionSource, solidColor, ComposableLambdaKt.rememberComposableLambda(-1189274459, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.3.2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function9, Composer composer, Integer num) {
                    invoke((Function2<? super Composer, ? super Integer, Unit>) function9, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Function2<? super Composer, ? super Integer, Unit> function9, Composer $composer2, int $changed2) {
                    ComposerKt.sourceInformation($composer2, "CN(innerTextField)455@25498L391,439@24710L1202:OutlinedTextField.kt#uh7d8r");
                    int $dirty = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty |= $composer2.changedInstance(function9) ? 4 : 2;
                    }
                    if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1189274459, $dirty, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:439)");
                        }
                        OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                        int $dirty2 = $dirty;
                        String str2 = str;
                        boolean z5 = z2;
                        boolean z6 = z3;
                        VisualTransformation visualTransformation2 = visualTransformation;
                        MutableInteractionSource mutableInteractionSource2 = mutableInteractionSource;
                        boolean z7 = z4;
                        Function2<Composer, Integer, Unit> function10 = function2;
                        Function2<Composer, Integer, Unit> function11 = function3;
                        Function2<Composer, Integer, Unit> function12 = function4;
                        Function2<Composer, Integer, Unit> function13 = function5;
                        Function2<Composer, Integer, Unit> function14 = function6;
                        Function2<Composer, Integer, Unit> function15 = function7;
                        Function2<Composer, Integer, Unit> function16 = function8;
                        TextFieldColors textFieldColors2 = textFieldColors;
                        final boolean z8 = z2;
                        final boolean z9 = z4;
                        final MutableInteractionSource mutableInteractionSource3 = mutableInteractionSource;
                        final TextFieldColors textFieldColors3 = textFieldColors;
                        final Shape shape2 = shape;
                        outlinedTextFieldDefaults.DecorationBox(str2, function9, z5, z6, visualTransformation2, mutableInteractionSource2, z7, function10, function11, function12, function13, function14, function15, function16, textFieldColors2, null, ComposableLambdaKt.rememberComposableLambda(-656940872, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.3.2.1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer3, int $changed3) {
                                ComposerKt.sourceInformation($composer3, "C456@25554L309:OutlinedTextField.kt#uh7d8r");
                                if (!$composer3.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                    $composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-656940872, $changed3, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:456)");
                                }
                                OutlinedTextFieldDefaults.INSTANCE.m2706Container4EFweAY(z8, z9, mutableInteractionSource3, null, textFieldColors3, shape2, 0.0f, 0.0f, $composer3, 100663296, 200);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer2, 54), $composer2, ($dirty2 << 3) & 112, 14155776, 32768);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer2.skipToGroupEnd();
                }
            }, $composer, 54), $composer, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static final void OutlinedTextField(final TextFieldValue value, final Function1<? super TextFieldValue, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        TextFieldValue textFieldValue;
        Modifier modifier2;
        boolean enabled2;
        boolean readOnly2;
        TextStyle textStyle2;
        Function2<? super Composer, ? super Integer, Unit> function9;
        int i2;
        int i3;
        int i4;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        Function2<? super Composer, ? super Integer, Unit> function11;
        final Function2<? super Composer, ? super Integer, Unit> function12;
        final boolean isError2;
        final KeyboardOptions keyboardOptions2;
        final KeyboardActions keyboardActions2;
        final boolean enabled3;
        final int maxLines2;
        final int minLines2;
        final MutableInteractionSource interactionSource2;
        final Shape shape2;
        final TextFieldColors colors2;
        final boolean enabled4;
        final boolean readOnly3;
        final TextStyle textStyle3;
        final Function2<? super Composer, ? super Integer, Unit> function13;
        final Function2<? super Composer, ? super Integer, Unit> function14;
        final Function2<? super Composer, ? super Integer, Unit> function15;
        final Function2<? super Composer, ? super Integer, Unit> function16;
        final VisualTransformation visualTransformation2;
        Function2<? super Composer, ? super Integer, Unit> function17;
        Function2<? super Composer, ? super Integer, Unit> function18;
        Function2<? super Composer, ? super Integer, Unit> function19;
        Function2<? super Composer, ? super Integer, Unit> function20;
        Function2<? super Composer, ? super Integer, Unit> function21;
        Function2<? super Composer, ? super Integer, Unit> function22;
        boolean isError3;
        VisualTransformation visualTransformation3;
        KeyboardOptions keyboardOptions3;
        KeyboardActions keyboardActions3;
        boolean singleLine2;
        int maxLines3;
        int minLines3;
        MutableInteractionSource interactionSource3;
        Shape shape3;
        Function2<? super Composer, ? super Integer, Unit> function23;
        Shape shape4;
        Function2<? super Composer, ? super Integer, Unit> function24;
        TextStyle textStyle4;
        Function2<? super Composer, ? super Integer, Unit> function25;
        Function2<? super Composer, ? super Integer, Unit> function26;
        Function2<? super Composer, ? super Integer, Unit> function27;
        Function2<? super Composer, ? super Integer, Unit> function28;
        VisualTransformation visualTransformation4;
        MutableInteractionSource interactionSource4;
        TextFieldColors colors3;
        int minLines4;
        int minLines5;
        boolean singleLine3;
        KeyboardActions keyboardActions4;
        KeyboardOptions keyboardOptions4;
        boolean readOnly4;
        Modifier modifier3;
        MutableInteractionSource interactionSource5;
        MutableInteractionSource interactionSource6;
        int i5;
        Composer $composer2 = $composer.startRestartGroup(2057288437);
        ComposerKt.sourceInformation($composer2, "C(OutlinedTextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,isError,visualTransformation,keyboardOptions,keyboardActions,singleLine,maxLines,minLines,interactionSource,shape,colors)571@32380L2835,571@32293L2922:OutlinedTextField.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        int $dirty2 = $changed2;
        if ((i & 1) != 0) {
            $dirty |= 6;
            textFieldValue = value;
        } else if (($changed & 6) == 0) {
            textFieldValue = value;
            $dirty |= $composer2.changed(textFieldValue) ? 4 : 2;
        } else {
            textFieldValue = value;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty |= $composer2.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty |= 24576;
            readOnly2 = readOnly;
        } else if (($changed & 24576) == 0) {
            readOnly2 = readOnly;
            $dirty |= $composer2.changed(readOnly2) ? 16384 : 8192;
        } else {
            readOnly2 = readOnly;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                textStyle2 = textStyle;
                if ($composer2.changed(textStyle2)) {
                    i5 = 131072;
                }
                $dirty |= i5;
            } else {
                textStyle2 = textStyle;
            }
            i5 = 65536;
            $dirty |= i5;
        } else {
            textStyle2 = textStyle;
        }
        int i9 = i & 64;
        if (i9 != 0) {
            $dirty |= 1572864;
            function9 = function2;
        } else if (($changed & 1572864) == 0) {
            function9 = function2;
            $dirty |= $composer2.changedInstance(function9) ? 1048576 : 524288;
        } else {
            function9 = function2;
        }
        int i10 = i & 128;
        if (i10 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 8388608 : 4194304;
        }
        int i11 = i & 256;
        if (i11 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i12 = i & 512;
        if (i12 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer2.changedInstance(function5) ? 536870912 : 268435456;
        }
        int i13 = i & 1024;
        if (i13 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer2.changedInstance(function6) ? 4 : 2;
        }
        int i14 = i & 2048;
        if (i14 != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer2.changedInstance(function7) ? 32 : 16;
        }
        int i15 = i & 4096;
        if (i15 != 0) {
            $dirty1 |= 384;
            i2 = i15;
        } else {
            i2 = i15;
            if (($changed1 & 384) == 0) {
                $dirty1 |= $composer2.changedInstance(function8) ? 256 : 128;
            }
        }
        int i16 = i & 8192;
        if (i16 != 0) {
            $dirty1 |= 3072;
            i3 = i16;
        } else {
            i3 = i16;
            if (($changed1 & 3072) == 0) {
                $dirty1 |= $composer2.changed(isError) ? 2048 : 1024;
            }
        }
        int i17 = i & 16384;
        if (i17 != 0) {
            $dirty1 |= 24576;
            i4 = i17;
        } else {
            i4 = i17;
            if (($changed1 & 24576) == 0) {
                $dirty1 |= $composer2.changed(visualTransformation) ? 16384 : 8192;
            }
        }
        int i18 = i & 32768;
        if (i18 != 0) {
            $dirty1 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= $composer2.changed(keyboardOptions) ? 131072 : 65536;
        }
        int i19 = i & 65536;
        if (i19 != 0) {
            $dirty1 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty1 |= $composer2.changed(keyboardActions) ? 1048576 : 524288;
        }
        int i20 = i & 131072;
        if (i20 != 0) {
            $dirty1 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty1 |= $composer2.changed(singleLine) ? 8388608 : 4194304;
        }
        if (($changed1 & 100663296) == 0) {
            $dirty1 |= ((i & 262144) == 0 && $composer2.changed(maxLines)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i21 = i & 524288;
        if (i21 != 0) {
            $dirty1 |= C.ENCODING_PCM_32BIT;
        } else if (($changed1 & C.ENCODING_PCM_32BIT) == 0) {
            $dirty1 |= $composer2.changed(minLines) ? 536870912 : 268435456;
        }
        int i22 = i & 1048576;
        if (i22 != 0) {
            $dirty2 |= 6;
        } else if (($changed2 & 6) == 0) {
            $dirty2 |= $composer2.changed(interactionSource) ? 4 : 2;
        }
        if (($changed2 & 48) == 0) {
            $dirty2 |= ((i & 2097152) == 0 && $composer2.changed(shape)) ? 32 : 16;
        }
        if (($changed2 & 384) == 0) {
            $dirty2 |= ((i & 4194304) == 0 && $composer2.changed(colors)) ? 256 : 128;
        }
        if ($composer2.shouldExecute((($dirty & 306783379) == 306783378 && (306783379 & $dirty1) == 306783378 && ($dirty2 & 147) == 146) ? false : true, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "542@30932L7,558@31736L5,559@31799L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((262144 & i) != 0) {
                    $dirty1 &= -234881025;
                }
                if ((2097152 & i) != 0) {
                    $dirty2 &= -113;
                }
                if ((i & 4194304) != 0) {
                    $dirty2 &= -897;
                }
                function23 = function3;
                function11 = function4;
                function24 = function5;
                function25 = function6;
                function27 = function7;
                function28 = function8;
                isError2 = isError;
                visualTransformation4 = visualTransformation;
                keyboardOptions4 = keyboardOptions;
                keyboardActions4 = keyboardActions;
                singleLine3 = singleLine;
                minLines5 = maxLines;
                minLines4 = minLines;
                interactionSource4 = interactionSource;
                shape4 = shape;
                readOnly4 = readOnly2;
                textStyle4 = textStyle2;
                function26 = function9;
                colors3 = colors;
                modifier3 = modifier2;
            } else {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    enabled2 = true;
                }
                if (i8 != 0) {
                    readOnly2 = false;
                }
                if ((i & 32) != 0) {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer2.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty &= -458753;
                    textStyle2 = (TextStyle) objConsume;
                }
                if (i9 != 0) {
                    function9 = null;
                }
                if (i10 == 0) {
                    function17 = function3;
                } else {
                    function17 = null;
                }
                if (i11 == 0) {
                    function18 = function4;
                } else {
                    function18 = null;
                }
                if (i12 == 0) {
                    function19 = function5;
                } else {
                    function19 = null;
                }
                if (i13 == 0) {
                    function20 = function6;
                } else {
                    function20 = null;
                }
                if (i14 == 0) {
                    function21 = function7;
                } else {
                    function21 = null;
                }
                if (i2 == 0) {
                    function22 = function8;
                } else {
                    function22 = null;
                }
                if (i3 == 0) {
                    isError3 = isError;
                } else {
                    isError3 = false;
                }
                if (i4 == 0) {
                    visualTransformation3 = visualTransformation;
                } else {
                    visualTransformation3 = VisualTransformation.INSTANCE.getNone();
                }
                if (i18 == 0) {
                    keyboardOptions3 = keyboardOptions;
                } else {
                    keyboardOptions3 = KeyboardOptions.INSTANCE.getDefault();
                }
                if (i19 == 0) {
                    keyboardActions3 = keyboardActions;
                } else {
                    keyboardActions3 = KeyboardActions.INSTANCE.getDefault();
                }
                if (i20 == 0) {
                    singleLine2 = singleLine;
                } else {
                    singleLine2 = false;
                }
                if ((i & 262144) == 0) {
                    maxLines3 = maxLines;
                } else {
                    maxLines3 = singleLine2 ? 1 : Integer.MAX_VALUE;
                    $dirty1 &= -234881025;
                }
                if (i21 == 0) {
                    minLines3 = minLines;
                } else {
                    minLines3 = 1;
                }
                if (i22 == 0) {
                    interactionSource3 = interactionSource;
                } else {
                    interactionSource3 = null;
                }
                Function2<? super Composer, ? super Integer, Unit> function29 = function17;
                if ((i & 2097152) != 0) {
                    shape3 = OutlinedTextFieldDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty2 &= -113;
                } else {
                    shape3 = shape;
                }
                if ((i & 4194304) == 0) {
                    function23 = function29;
                    function11 = function18;
                    shape4 = shape3;
                    function24 = function19;
                    textStyle4 = textStyle2;
                    function25 = function20;
                    function26 = function9;
                    function27 = function21;
                    function28 = function22;
                    visualTransformation4 = visualTransformation3;
                    isError2 = isError3;
                    interactionSource4 = interactionSource3;
                    colors3 = colors;
                    minLines4 = minLines3;
                    minLines5 = maxLines3;
                    singleLine3 = singleLine2;
                    keyboardActions4 = keyboardActions3;
                    keyboardOptions4 = keyboardOptions3;
                    readOnly4 = readOnly2;
                    modifier3 = modifier2;
                } else {
                    Shape shape5 = shape3;
                    $dirty2 &= -897;
                    Function2<? super Composer, ? super Integer, Unit> function30 = function9;
                    colors3 = OutlinedTextFieldDefaults.INSTANCE.colors($composer2, 6);
                    textStyle4 = textStyle2;
                    function26 = function30;
                    function23 = function29;
                    function11 = function18;
                    shape4 = shape5;
                    function24 = function19;
                    function25 = function20;
                    function27 = function21;
                    function28 = function22;
                    visualTransformation4 = visualTransformation3;
                    isError2 = isError3;
                    interactionSource4 = interactionSource3;
                    minLines4 = minLines3;
                    minLines5 = maxLines3;
                    singleLine3 = singleLine2;
                    keyboardActions4 = keyboardActions3;
                    keyboardOptions4 = keyboardOptions3;
                    readOnly4 = readOnly2;
                    modifier3 = modifier2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2057288437, $dirty, $dirty1, "androidx.compose.material3.OutlinedTextField (OutlinedTextField.kt:560)");
            }
            if (interactionSource4 == null) {
                $composer2.startReplaceGroup(-502250010);
                ComposerKt.sourceInformation($composer2, "562@31894L39");
                ComposerKt.sourceInformationMarkerStart($composer2, 1369271708, "CC(remember):OutlinedTextField.kt#9igjgp");
                interactionSource5 = interactionSource4;
                Object it$iv = $composer2.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
                interactionSource6 = (MutableInteractionSource) it$iv;
            } else {
                interactionSource5 = interactionSource4;
                $composer2.startReplaceGroup(1369271057);
                $composer2.endReplaceGroup();
                interactionSource6 = interactionSource5;
            }
            $composer2.startReplaceGroup(1369277167);
            ComposerKt.sourceInformation($composer2, "*566@32118L25");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle4.m7358getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource6, $composer2, 0).getValue().booleanValue();
                $this$takeOrElse_u2dDxMtmZc$iv = colors3.m3039textColorXeAY9LY$material3(enabled2, isError2, focused);
            }
            $composer2.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle4.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            boolean enabled5 = enabled2;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors3.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(-2094276683, true, new AnonymousClass5(modifier3, function26, isError2, colors3, textFieldValue, function1, enabled5, readOnly4, mergedTextStyle, keyboardOptions4, keyboardActions4, singleLine3, minLines5, minLines4, visualTransformation4, interactionSource6, function23, function11, function24, function25, function27, function28, shape4), $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            keyboardOptions2 = keyboardOptions4;
            keyboardActions2 = keyboardActions4;
            minLines2 = minLines4;
            function10 = function23;
            function14 = function24;
            function12 = function25;
            function15 = function27;
            shape2 = shape4;
            interactionSource2 = interactionSource5;
            function13 = function26;
            readOnly3 = readOnly4;
            maxLines2 = minLines5;
            function16 = function28;
            textStyle3 = textStyle4;
            enabled4 = enabled5;
            enabled3 = singleLine3;
            colors2 = colors3;
            visualTransformation2 = visualTransformation4;
        } else {
            $composer2.skipToGroupEnd();
            function10 = function3;
            function11 = function4;
            function12 = function6;
            isError2 = isError;
            keyboardOptions2 = keyboardOptions;
            keyboardActions2 = keyboardActions;
            enabled3 = singleLine;
            maxLines2 = maxLines;
            minLines2 = minLines;
            interactionSource2 = interactionSource;
            shape2 = shape;
            colors2 = colors;
            enabled4 = enabled2;
            readOnly3 = readOnly2;
            textStyle3 = textStyle2;
            function13 = function9;
            function14 = function5;
            function15 = function7;
            function16 = function8;
            visualTransformation2 = visualTransformation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final Function2<? super Composer, ? super Integer, Unit> function31 = function11;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldKt.OutlinedTextField$lambda$8(value, function1, modifier4, enabled4, readOnly3, textStyle3, function13, function10, function31, function14, function12, function15, function16, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, enabled3, maxLines2, minLines2, interactionSource2, shape2, colors2, $changed, $changed1, $changed2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$5, reason: invalid class name */
    /* JADX INFO: compiled from: OutlinedTextField.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass5 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ Function2<Composer, Integer, Unit> $label;
        final /* synthetic */ Function2<Composer, Integer, Unit> $leadingIcon;
        final /* synthetic */ int $maxLines;
        final /* synthetic */ TextStyle $mergedTextStyle;
        final /* synthetic */ int $minLines;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ Function1<TextFieldValue, Unit> $onValueChange;
        final /* synthetic */ Function2<Composer, Integer, Unit> $placeholder;
        final /* synthetic */ Function2<Composer, Integer, Unit> $prefix;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ Function2<Composer, Integer, Unit> $suffix;
        final /* synthetic */ Function2<Composer, Integer, Unit> $supportingText;
        final /* synthetic */ Function2<Composer, Integer, Unit> $trailingIcon;
        final /* synthetic */ TextFieldValue $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass5(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, boolean z, TextFieldColors textFieldColors, TextFieldValue textFieldValue, Function1<? super TextFieldValue, Unit> function1, boolean z2, boolean z3, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, VisualTransformation visualTransformation, MutableInteractionSource mutableInteractionSource, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, Shape shape) {
            this.$modifier = modifier;
            this.$label = function2;
            this.$isError = z;
            this.$colors = textFieldColors;
            this.$value = textFieldValue;
            this.$onValueChange = function1;
            this.$enabled = z2;
            this.$readOnly = z3;
            this.$mergedTextStyle = textStyle;
            this.$keyboardOptions = keyboardOptions;
            this.$keyboardActions = keyboardActions;
            this.$singleLine = z4;
            this.$maxLines = i;
            this.$minLines = i2;
            this.$visualTransformation = visualTransformation;
            this.$interactionSource = mutableInteractionSource;
            this.$placeholder = function3;
            this.$leadingIcon = function4;
            this.$trailingIcon = function5;
            this.$prefix = function6;
            this.$suffix = function7;
            this.$supportingText = function8;
            this.$shape = shape;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            Modifier.Companion companionM823paddingqDBjuR0$default;
            ComposerKt.sourceInformation($composer, "C587@33087L38,605@33907L1291,572@32390L2819:OutlinedTextField.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2094276683, $changed, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:572)");
            }
            Modifier modifier = this.$modifier;
            if (this.$label != null) {
                $composer.startReplaceGroup(-1901539802);
                ComposerKt.sourceInformation($composer, "581@32839L2,582@32889L26");
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 1739784151, "CC(remember):OutlinedTextField.kt#9igjgp");
                Object it$iv = $composer.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = new Function1() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$5$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Unit.INSTANCE;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                companionM823paddingqDBjuR0$default = PaddingKt.m823paddingqDBjuR0$default(SemanticsModifierKt.semantics(companion, true, (Function1) it$iv), 0.0f, TextFieldImplKt.minimizedLabelHalfHeight($composer, 0), 0.0f, 0.0f, 13, null);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1901156115);
                $composer.endReplaceGroup();
                companionM823paddingqDBjuR0$default = Modifier.INSTANCE;
            }
            Modifier modifierThen = modifier.then(companionM823paddingqDBjuR0$default);
            boolean z = this.$isError;
            Strings.Companion companion2 = Strings.INSTANCE;
            Modifier modifierM849defaultMinSizeVpY3zN4 = SizeKt.m849defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifierThen, z, Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer, 0)), OutlinedTextFieldDefaults.INSTANCE.m2712getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2711getMinHeightD9Ej5fM());
            SolidColor solidColor = new SolidColor(this.$colors.m2989cursorColorvNxB06k$material3(this.$isError), null);
            final TextFieldValue textFieldValue = this.$value;
            final boolean z2 = this.$enabled;
            final boolean z3 = this.$singleLine;
            final VisualTransformation visualTransformation = this.$visualTransformation;
            final MutableInteractionSource mutableInteractionSource = this.$interactionSource;
            final boolean z4 = this.$isError;
            final Function2<Composer, Integer, Unit> function2 = this.$label;
            final Function2<Composer, Integer, Unit> function3 = this.$placeholder;
            final Function2<Composer, Integer, Unit> function4 = this.$leadingIcon;
            final Function2<Composer, Integer, Unit> function5 = this.$trailingIcon;
            final Function2<Composer, Integer, Unit> function6 = this.$prefix;
            final Function2<Composer, Integer, Unit> function7 = this.$suffix;
            final Function2<Composer, Integer, Unit> function8 = this.$supportingText;
            final TextFieldColors textFieldColors = this.$colors;
            final Shape shape = this.$shape;
            BasicTextFieldKt.BasicTextField(this.$value, this.$onValueChange, modifierM849defaultMinSizeVpY3zN4, this.$enabled, this.$readOnly, this.$mergedTextStyle, this.$keyboardOptions, this.$keyboardActions, this.$singleLine, this.$maxLines, this.$minLines, this.$visualTransformation, (Function1<? super TextLayoutResult, Unit>) null, this.$interactionSource, solidColor, ComposableLambdaKt.rememberComposableLambda(674541106, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.5.2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function9, Composer composer, Integer num) {
                    invoke((Function2<? super Composer, ? super Integer, Unit>) function9, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Function2<? super Composer, ? super Integer, Unit> function9, Composer $composer2, int $changed2) {
                    ComposerKt.sourceInformation($composer2, "CN(innerTextField)622@34766L391,606@33973L1207:OutlinedTextField.kt#uh7d8r");
                    int $dirty = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty |= $composer2.changedInstance(function9) ? 4 : 2;
                    }
                    if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(674541106, $dirty, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:606)");
                        }
                        OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                        String text = textFieldValue.getText();
                        boolean z5 = z2;
                        int $dirty2 = $dirty;
                        boolean z6 = z3;
                        VisualTransformation visualTransformation2 = visualTransformation;
                        MutableInteractionSource mutableInteractionSource2 = mutableInteractionSource;
                        boolean z7 = z4;
                        Function2<Composer, Integer, Unit> function10 = function2;
                        Function2<Composer, Integer, Unit> function11 = function3;
                        Function2<Composer, Integer, Unit> function12 = function4;
                        Function2<Composer, Integer, Unit> function13 = function5;
                        Function2<Composer, Integer, Unit> function14 = function6;
                        Function2<Composer, Integer, Unit> function15 = function7;
                        Function2<Composer, Integer, Unit> function16 = function8;
                        TextFieldColors textFieldColors2 = textFieldColors;
                        final boolean z8 = z2;
                        final boolean z9 = z4;
                        final MutableInteractionSource mutableInteractionSource3 = mutableInteractionSource;
                        final TextFieldColors textFieldColors3 = textFieldColors;
                        final Shape shape2 = shape;
                        outlinedTextFieldDefaults.DecorationBox(text, function9, z5, z6, visualTransformation2, mutableInteractionSource2, z7, function10, function11, function12, function13, function14, function15, function16, textFieldColors2, null, ComposableLambdaKt.rememberComposableLambda(1409265477, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.5.2.1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer3, int $changed3) {
                                ComposerKt.sourceInformation($composer3, "C623@34822L309:OutlinedTextField.kt#uh7d8r");
                                if (!$composer3.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                    $composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1409265477, $changed3, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:623)");
                                }
                                OutlinedTextFieldDefaults.INSTANCE.m2706Container4EFweAY(z8, z9, mutableInteractionSource3, null, textFieldColors3, shape2, 0.0f, 0.0f, $composer3, 100663296, 200);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer2, 54), $composer2, ($dirty2 << 3) & 112, 14155776, 32768);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer2.skipToGroupEnd();
                }
            }, $composer, 54), $composer, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:277:0x0a9e  */
    /* JADX WARN: Code duplicated, block: B:278:0x0abc  */
    /* JADX WARN: Code duplicated, block: B:281:0x0ac4  */
    /* JADX WARN: Code duplicated, block: B:283:0x0ae0  */
    /* JADX WARN: Code duplicated, block: B:285:0x0ae4  */
    /* JADX WARN: Code duplicated, block: B:288:0x0aef  */
    /* JADX WARN: Code duplicated, block: B:290:0x0af6  */
    /* JADX WARN: Code duplicated, block: B:296:0x0b0d  */
    /* JADX WARN: Code duplicated, block: B:299:0x0b8e  */
    /* JADX WARN: Code duplicated, block: B:302:0x0b9a  */
    /* JADX WARN: Code duplicated, block: B:303:0x0ba0  */
    /* JADX WARN: Code duplicated, block: B:312:0x0c5c  */
    /* JADX WARN: Code duplicated, block: B:314:0x0c6d  */
    /* JADX WARN: Code duplicated, block: B:316:0x0d03  */
    /* JADX WARN: Code duplicated, block: B:319:0x0d0f  */
    /* JADX WARN: Code duplicated, block: B:320:0x0d15  */
    /* JADX WARN: Code duplicated, block: B:323:0x0d46  */
    /* JADX WARN: Code duplicated, block: B:325:0x0d58  */
    /* JADX WARN: Code duplicated, block: B:326:0x0d5b  */
    /* JADX WARN: Code duplicated, block: B:329:0x0dcf  */
    /* JADX WARN: Code duplicated, block: B:332:0x0df0  */
    public static final void OutlinedTextFieldLayout(final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super Modifier, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final Function2<? super Composer, ? super Integer, Unit> function6, final Function2<? super Composer, ? super Integer, Unit> function7, final Function2<? super Composer, ? super Integer, Unit> function8, final boolean singleLine, TextFieldLabelPosition labelPosition, final FloatProducer labelProgress, final Function1<? super Size, Unit> function1, final Function2<? super Composer, ? super Integer, Unit> function9, Function2<? super Composer, ? super Integer, Unit> function10, PaddingValues paddingValues, Composer $composer, final int $changed, final int $changed1) {
        int $dirty;
        final TextFieldLabelPosition textFieldLabelPosition;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Composer $composer2;
        PaddingValues paddingValues2;
        Object value$iv;
        Function0<ComposeUiNode> function0;
        float $this$coerceAtLeast_u2dYgX7TsA$iv;
        float $this$coerceAtLeast_u2dYgX7TsA$iv2;
        float endPadding;
        Function0<ComposeUiNode> function12;
        int $changed$iv;
        Modifier.Companion labelPadding;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function13;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        int $changed$iv2;
        final FloatProducer floatProducer;
        Object value$iv2;
        int compositeKeyHash$iv$iv2;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function14;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        Function0<ComposeUiNode> function15;
        Function0<ComposeUiNode> function16;
        Function0<ComposeUiNode> function17;
        Function0<ComposeUiNode> function18;
        Composer $composer3 = $composer.startRestartGroup(753699262);
        ComposerKt.sourceInformation($composer3, "C(OutlinedTextFieldLayout)N(modifier,textField,placeholder,label,leading,trailing,prefix,suffix,singleLine,labelPosition,labelProgress,onLabelMeasured,container,supporting,paddingValues)660@36112L32,662@36177L554,679@36779L7,680@36791L4040:OutlinedTextField.kt#uh7d8r");
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty = $changed | ($composer3.changed(modifier) ? 4 : 2);
        } else {
            $dirty = $changed;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function5) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer3.changedInstance(function6) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(function7) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changedInstance(function8) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(singleLine) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changed(labelPosition) ? 536870912 : 268435456;
        }
        int $dirty2 = $dirty;
        int $dirty3 = $changed1 & 6;
        if ($dirty3 == 0) {
            $dirty1 |= ($changed1 & 8) == 0 ? $composer3.changed(labelProgress) : $composer3.changedInstance(labelProgress) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty1 |= $composer3.changedInstance(function9) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty1 |= $composer3.changedInstance(function10) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty1 |= $composer3.changed(paddingValues) ? 16384 : 8192;
        }
        int $dirty4 = $dirty1;
        boolean invalid$iv = true;
        if (!$composer3.shouldExecute((($dirty2 & 306783379) == 306783378 && ($dirty4 & 9363) == 9362) ? false : true, $dirty2 & 1)) {
            textFieldLabelPosition = labelPosition;
            function11 = function10;
            $composer2 = $composer3;
            paddingValues2 = paddingValues;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(753699262, $dirty2, $dirty4, "androidx.compose.material3.OutlinedTextFieldLayout (OutlinedTextField.kt:659)");
            }
            float horizontalIconPadding = TextFieldImplKt.textFieldHorizontalIconPadding($composer3, 0);
            ComposerKt.sourceInformationMarkerStart($composer3, -1544458712, "CC(remember):OutlinedTextField.kt#9igjgp");
            boolean invalid$iv2 = (($dirty4 & 112) == 32) | ((234881024 & $dirty2) == 67108864) | ((1879048192 & $dirty2) == 536870912) | (($dirty4 & 14) == 4 || (($dirty4 & 8) != 0 && $composer3.changed(labelProgress))) | ((57344 & $dirty4) == 16384) | $composer3.changed(horizontalIconPadding);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                paddingValues2 = paddingValues;
                value$iv = new OutlinedTextFieldMeasurePolicy(function1, singleLine, labelPosition, labelProgress, paddingValues2, horizontalIconPadding, null);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
                paddingValues2 = paddingValues;
            }
            OutlinedTextFieldMeasurePolicy measurePolicy = (OutlinedTextFieldMeasurePolicy) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection = (LayoutDirection) objConsume;
            int $changed$iv3 = ($dirty2 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer3, modifier);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = (($changed$iv3 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor3;
                $composer3.createNode(function0);
            } else {
                function0 = constructor3;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2145696067, "C683@36860L11,751@39346L187:OutlinedTextField.kt#uh7d8r");
            function9.invoke($composer3, Integer.valueOf(($dirty4 >> 6) & 14));
            if (function5 != null) {
                $composer3.startReplaceGroup(2145628269);
                ComposerKt.sourceInformation($composer3, "686@36924L224");
                Modifier modifier$iv = InteractiveComponentSizeKt.minimumInteractiveComponentSize(LayoutIdKt.layoutId(Modifier.INSTANCE, "Leading"));
                Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (54 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function18 = constructor4;
                    $composer3.createNode(function18);
                } else {
                    function18 = constructor4;
                    $composer3.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash2);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i3 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 227456132, "C690@37121L9:OutlinedTextField.kt#uh7d8r");
                function5.invoke($composer3, Integer.valueOf(($dirty2 >> 12) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(2145874285);
                $composer3.endReplaceGroup();
            }
            if (function6 != null) {
                $composer3.startReplaceGroup(2145917003);
                ComposerKt.sourceInformation($composer3, "694@37215L226");
                Modifier modifier$iv2 = InteractiveComponentSizeKt.minimumInteractiveComponentSize(LayoutIdKt.layoutId(Modifier.INSTANCE, "Trailing"));
                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                int $changed$iv$iv3 = (54 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function17 = constructor5;
                    $composer3.createNode(function17);
                } else {
                    function17 = constructor5;
                    $composer3.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                    $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                    $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash3);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i5 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 531295628, "C698@37413L10:OutlinedTextField.kt#uh7d8r");
                function6.invoke($composer3, Integer.valueOf(($dirty2 >> 15) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(2146164941);
                $composer3.endReplaceGroup();
            }
            float startTextFieldPadding = PaddingKt.calculateStartPadding(paddingValues2, layoutDirection);
            float endTextFieldPadding = PaddingKt.calculateEndPadding(paddingValues2, layoutDirection);
            if (function5 == null) {
                $this$coerceAtLeast_u2dYgX7TsA$iv = startTextFieldPadding;
            } else {
                float $this$coerceAtLeast_u2dYgX7TsA$iv3 = Dp.m7905constructorimpl(startTextFieldPadding - horizontalIconPadding);
                float minimumValue$iv = Dp.m7905constructorimpl(0);
                $this$coerceAtLeast_u2dYgX7TsA$iv = Dp.m7905constructorimpl(RangesKt.coerceAtLeast($this$coerceAtLeast_u2dYgX7TsA$iv3, minimumValue$iv));
            }
            float startPadding = $this$coerceAtLeast_u2dYgX7TsA$iv;
            if (function6 == null) {
                $this$coerceAtLeast_u2dYgX7TsA$iv2 = endTextFieldPadding;
            } else {
                float $this$coerceAtLeast_u2dYgX7TsA$iv4 = Dp.m7905constructorimpl(endTextFieldPadding - horizontalIconPadding);
                float minimumValue$iv2 = Dp.m7905constructorimpl(0);
                $this$coerceAtLeast_u2dYgX7TsA$iv2 = Dp.m7905constructorimpl(RangesKt.coerceAtLeast($this$coerceAtLeast_u2dYgX7TsA$iv4, minimumValue$iv2));
            }
            if (function7 != null) {
                $composer3.startReplaceGroup(2146868920);
                ComposerKt.sourceInformation($composer3, "719@38172L309");
                Modifier modifier$iv3 = PaddingKt.m823paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m853heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.PrefixId), TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null), startPadding, 0.0f, TextFieldImplKt.getPrefixSuffixTextPadding(), 0.0f, 10, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                int $changed$iv$iv4 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
                Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv3 = (($changed$iv$iv4 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function16 = constructor6;
                    $composer3.createNode(function16);
                } else {
                    function16 = constructor6;
                    $composer3.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv5 = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv5.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv5.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv5))) {
                    $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
                    $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash4);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                int i6 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                int i7 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 843401773, "C725@38455L8:OutlinedTextField.kt#uh7d8r");
                function7.invoke($composer3, Integer.valueOf(($dirty2 >> 18) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(2147196621);
                $composer3.endReplaceGroup();
            }
            if (function8 != null) {
                $composer3.startReplaceGroup(2147239866);
                ComposerKt.sourceInformation($composer3, "729@38546L307");
                endPadding = $this$coerceAtLeast_u2dYgX7TsA$iv2;
                Modifier modifier$iv4 = PaddingKt.m823paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m853heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.SuffixId), TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null), TextFieldImplKt.getPrefixSuffixTextPadding(), 0.0f, endPadding, 0.0f, 10, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
                int $changed$iv$iv5 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer3, modifier$iv4);
                Function0<ComposeUiNode> constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                int $i$f$Box = $changed$iv$iv5 << 6;
                int $changed$iv$iv$iv4 = ($i$f$Box & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function15 = constructor7;
                    $composer3.createNode(function15);
                } else {
                    function15 = constructor7;
                    $composer3.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv6.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv6.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv6))) {
                    $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                    $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash5);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = ($changed$iv$iv$iv4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                int i9 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 1155507980, "C735@38827L8:OutlinedTextField.kt#uh7d8r");
                function8.invoke($composer3, Integer.valueOf(($dirty2 >> 21) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                endPadding = $this$coerceAtLeast_u2dYgX7TsA$iv2;
                $composer3.startReplaceGroup(-2147401651);
                $composer3.endReplaceGroup();
            }
            Modifier textPadding = PaddingKt.m823paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m853heightInVpY3zN4$default(Modifier.INSTANCE, TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null), function7 == null ? startPadding : Dp.m7905constructorimpl(0), 0.0f, function8 == null ? endPadding : Dp.m7905constructorimpl(0), 0.0f, 10, null);
            if (function3 != null) {
                $composer3.startReplaceGroup(-2147031666);
                ComposerKt.sourceInformation($composer3, "748@39255L63");
                function3.invoke(LayoutIdKt.layoutId(Modifier.INSTANCE, "Hint").then(textPadding), $composer3, Integer.valueOf(($dirty2 >> 3) & 112));
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-2146940371);
                $composer3.endReplaceGroup();
            }
            Modifier modifier$iv$iv = LayoutIdKt.layoutId(Modifier.INSTANCE, "TextField").then(textPadding);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv5 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv5 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv5, true);
            int $changed$iv$iv6 = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv5 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer3, modifier$iv$iv);
            Function0<ComposeUiNode> constructor8 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv5 = (($changed$iv$iv6 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function12 = constructor8;
                $composer3.createNode(function12);
            } else {
                function12 = constructor8;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv7.getInserting()) {
                $changed$iv = 384;
            } else {
                $changed$iv = 384;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv7.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv7))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
                int i10 = ($changed$iv$iv$iv5 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                int i11 = (($changed$iv >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -332553027, "C755@39508L11:OutlinedTextField.kt#uh7d8r");
                function2.invoke($composer3, Integer.valueOf(($dirty2 >> 3) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                textFieldLabelPosition = labelPosition;
                if (textFieldLabelPosition instanceof TextFieldLabelPosition.Above) {
                    labelPadding = PaddingKt.m823paddingqDBjuR0$default(Modifier.INSTANCE, TextFieldImplKt.getAboveLabelHorizontalPadding(), 0.0f, TextFieldImplKt.getAboveLabelHorizontalPadding(), TextFieldImplKt.getAboveLabelBottomPadding(), 2, null);
                } else {
                    labelPadding = Modifier.INSTANCE;
                }
                if (function4 != null) {
                    $composer3.startReplaceGroup(-2146287790);
                    ComposerKt.sourceInformation($composer3, "771@40053L123,770@39995L379");
                    Modifier.Companion companion = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2147442928, "CC(remember):OutlinedTextField.kt#9igjgp");
                    if (($dirty4 & 14) != 4) {
                        if (($dirty4 & 8) != 0) {
                            floatProducer = labelProgress;
                            $composer2 = $composer3;
                            if (!$composer2.changedInstance(floatProducer)) {
                            }
                        } else {
                            floatProducer = labelProgress;
                            $composer2 = $composer3;
                        }
                        invalid$iv = false;
                    } else {
                        floatProducer = labelProgress;
                        $composer2 = $composer3;
                    }
                    value$iv2 = $composer3.rememberedValue();
                    if (!invalid$iv || value$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = new Function0() { // from class: androidx.compose.material3.OutlinedTextFieldKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Dp.m7903boximpl(DpKt.m7948lerpMdfbLM(TextFieldImplKt.getMinTextLineHeight(), TextFieldImplKt.getMinFocusedLabelLineHeight(), floatProducer.invoke()));
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifier$iv5 = LayoutIdKt.layoutId(SizeKt.wrapContentHeight$default(TextFieldImplKt.textFieldLabelMinHeight(companion, (Function0) value$iv2), null, false, 3, null), "Label").then(labelPadding);
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv6 = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv6 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv6, false);
                    int $changed$iv$iv7 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv6 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv6 = ComposedModifierKt.materializeModifier($composer3, modifier$iv5);
                    constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv6 = (($changed$iv$iv7 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function14 = constructor2;
                        $composer3.createNode(function14);
                    } else {
                        function14 = constructor2;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash7);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv6, ComposeUiNode.INSTANCE.getSetModifier());
                    int i12 = ($changed$iv$iv$iv6 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                    int i13 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1779720363, "C778@40349L7:OutlinedTextField.kt#uh7d8r");
                    function4.invoke($composer3, Integer.valueOf(($dirty2 >> 9) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceGroup();
                } else {
                    $composer2 = $composer3;
                    $composer3.startReplaceGroup(-2145892819);
                    $composer3.endReplaceGroup();
                }
                if (function10 != null) {
                    $composer3.startReplaceGroup(-2145844304);
                    ComposerKt.sourceInformation($composer3, "783@40444L317");
                    Modifier modifier$iv6 = PaddingKt.padding(SizeKt.wrapContentHeight$default(SizeKt.m853heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.SupportingId), TextFieldImplKt.getMinSupportingTextLineHeight(), 0.0f, 2, null), null, false, 3, null), TextFieldDefaults.m3048supportingTextPaddinga9UjIt4$material3$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null));
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv7 = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv7 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv7, false);
                    int $changed$iv$iv8 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv7 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv7 = ComposedModifierKt.materializeModifier($composer3, modifier$iv6);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv7 = (($changed$iv$iv8 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function13 = constructor;
                        $composer3.createNode(function13);
                    } else {
                        function13 = constructor;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                        $changed$iv2 = 0;
                        if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv7, ComposeUiNode.INSTANCE.getSetModifier());
                        int i14 = ($changed$iv$iv$iv7 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                        int i15 = (($changed$iv2 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 2091826725, "C789@40731L12:OutlinedTextField.kt#uh7d8r");
                        function11 = function10;
                        function11.invoke($composer3, Integer.valueOf(($dirty4 >> 9) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endReplaceGroup();
                    } else {
                        $changed$iv2 = 0;
                    }
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash8);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv7, ComposeUiNode.INSTANCE.getSetModifier());
                    int i16 = ($changed$iv$iv$iv7 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
                    int i17 = (($changed$iv2 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2091826725, "C789@40731L12:OutlinedTextField.kt#uh7d8r");
                    function11 = function10;
                    function11.invoke($composer3, Integer.valueOf(($dirty4 >> 9) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceGroup();
                } else {
                    function11 = function10;
                    $composer3.startReplaceGroup(-2145508915);
                    $composer3.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
            $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash6);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
            int i18 = ($changed$iv$iv$iv5 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance9 = BoxScopeInstance.INSTANCE;
            int i19 = (($changed$iv >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -332553027, "C755@39508L11:OutlinedTextField.kt#uh7d8r");
            function2.invoke($composer3, Integer.valueOf(($dirty2 >> 3) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            textFieldLabelPosition = labelPosition;
            if (textFieldLabelPosition instanceof TextFieldLabelPosition.Above) {
                labelPadding = PaddingKt.m823paddingqDBjuR0$default(Modifier.INSTANCE, TextFieldImplKt.getAboveLabelHorizontalPadding(), 0.0f, TextFieldImplKt.getAboveLabelHorizontalPadding(), TextFieldImplKt.getAboveLabelBottomPadding(), 2, null);
            } else {
                labelPadding = Modifier.INSTANCE;
            }
            if (function4 != null) {
                $composer3.startReplaceGroup(-2146287790);
                ComposerKt.sourceInformation($composer3, "771@40053L123,770@39995L379");
                Modifier.Companion companion2 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -2147442928, "CC(remember):OutlinedTextField.kt#9igjgp");
                if (($dirty4 & 14) != 4) {
                    if (($dirty4 & 8) != 0) {
                        floatProducer = labelProgress;
                        $composer2 = $composer3;
                        if (!$composer2.changedInstance(floatProducer)) {
                        }
                    } else {
                        floatProducer = labelProgress;
                        $composer2 = $composer3;
                    }
                    invalid$iv = false;
                } else {
                    floatProducer = labelProgress;
                    $composer2 = $composer3;
                }
                value$iv2 = $composer3.rememberedValue();
                if (!invalid$iv) {
                    value$iv2 = new Function0() { // from class: androidx.compose.material3.OutlinedTextFieldKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Dp.m7903boximpl(DpKt.m7948lerpMdfbLM(TextFieldImplKt.getMinTextLineHeight(), TextFieldImplKt.getMinFocusedLabelLineHeight(), floatProducer.invoke()));
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = new Function0() { // from class: androidx.compose.material3.OutlinedTextFieldKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Dp.m7903boximpl(DpKt.m7948lerpMdfbLM(TextFieldImplKt.getMinTextLineHeight(), TextFieldImplKt.getMinFocusedLabelLineHeight(), floatProducer.invoke()));
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifier$iv7 = LayoutIdKt.layoutId(SizeKt.wrapContentHeight$default(TextFieldImplKt.textFieldLabelMinHeight(companion2, (Function0) value$iv2), null, false, 3, null), "Label").then(labelPadding);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv8 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv8 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv8, false);
                int $changed$iv$iv9 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv8 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv8 = ComposedModifierKt.materializeModifier($composer3, modifier$iv7);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv8 = (($changed$iv$iv9 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function14 = constructor2;
                    $composer3.createNode(function14);
                } else {
                    function14 = constructor2;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash9 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash9);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv8, ComposeUiNode.INSTANCE.getSetModifier());
                int i110 = ($changed$iv$iv$iv8 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance10 = BoxScopeInstance.INSTANCE;
                int i111 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 1779720363, "C778@40349L7:OutlinedTextField.kt#uh7d8r");
                function4.invoke($composer3, Integer.valueOf(($dirty2 >> 9) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                $composer2 = $composer3;
                $composer3.startReplaceGroup(-2145892819);
                $composer3.endReplaceGroup();
            }
            if (function10 != null) {
                $composer3.startReplaceGroup(-2145844304);
                ComposerKt.sourceInformation($composer3, "783@40444L317");
                Modifier modifier$iv8 = PaddingKt.padding(SizeKt.wrapContentHeight$default(SizeKt.m853heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.SupportingId), TextFieldImplKt.getMinSupportingTextLineHeight(), 0.0f, 2, null), null, false, 3, null), TextFieldDefaults.m3048supportingTextPaddinga9UjIt4$material3$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null));
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv9 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv9 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv9, false);
                int $changed$iv$iv10 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv9 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv9 = ComposedModifierKt.materializeModifier($composer3, modifier$iv8);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv9 = (($changed$iv$iv10 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function13 = constructor;
                    $composer3.createNode(function13);
                } else {
                    function13 = constructor;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv9, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv9, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash10 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    $changed$iv2 = 0;
                    if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv9, ComposeUiNode.INSTANCE.getSetModifier());
                    int i112 = ($changed$iv$iv$iv9 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance11 = BoxScopeInstance.INSTANCE;
                    int i113 = (($changed$iv2 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2091826725, "C789@40731L12:OutlinedTextField.kt#uh7d8r");
                    function11 = function10;
                    function11.invoke($composer3, Integer.valueOf(($dirty4 >> 9) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endReplaceGroup();
                } else {
                    $changed$iv2 = 0;
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash10);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv9, ComposeUiNode.INSTANCE.getSetModifier());
                int i114 = ($changed$iv$iv$iv9 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance12 = BoxScopeInstance.INSTANCE;
                int i115 = (($changed$iv2 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 2091826725, "C789@40731L12:OutlinedTextField.kt#uh7d8r");
                function11 = function10;
                function11.invoke($composer3, Integer.valueOf(($dirty4 >> 9) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                function11 = function10;
                $composer3.startReplaceGroup(-2145508915);
                $composer3.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final PaddingValues paddingValues3 = paddingValues2;
            final Function2<? super Composer, ? super Integer, Unit> function19 = function11;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldKt.OutlinedTextFieldLayout$lambda$20(modifier, function2, function3, function4, function5, function6, function7, function8, singleLine, textFieldLabelPosition, labelProgress, function1, function9, function19, paddingValues3, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final Modifier outlineCutout(Modifier $this$outlineCutout, final Function0<Size> function0, final Alignment.Horizontal alignment, final PaddingValues paddingValues) {
        return DrawModifierKt.drawWithContent($this$outlineCutout, new Function1() { // from class: androidx.compose.material3.OutlinedTextFieldKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return OutlinedTextFieldKt.outlineCutout$lambda$22(function0, paddingValues, alignment, (ContentDrawScope) obj);
            }
        });
    }

    static final Unit outlineCutout$lambda$22(Function0 $labelSize, PaddingValues $paddingValues, Alignment.Horizontal $alignment, ContentDrawScope $this$drawWithContent) {
        long labelSizeValue = ((Size) $labelSize.invoke()).m4926unboximpl();
        int bits$iv$iv$iv = (int) (labelSizeValue >> 32);
        float labelWidth = Float.intBitsToFloat(bits$iv$iv$iv);
        if (labelWidth > 0.0f) {
            float innerPadding = $this$drawWithContent.mo406toPx0680j_4(OutlinedTextFieldInnerPadding);
            float leftPadding = $this$drawWithContent.mo406toPx0680j_4($paddingValues.mo769calculateLeftPaddingu2uoSUM($this$drawWithContent.getLayoutDirection()));
            float rightPadding = $this$drawWithContent.mo406toPx0680j_4($paddingValues.mo770calculateRightPaddingu2uoSUM($this$drawWithContent.getLayoutDirection()));
            int iRoundToInt = MathKt.roundToInt(labelWidth);
            long arg0$iv = $this$drawWithContent.mo5671getSizeNHjbRc();
            int bits$iv$iv$iv2 = (int) (arg0$iv >> 32);
            float labelCenter = $alignment.align(iRoundToInt, MathKt.roundToInt((Float.intBitsToFloat(bits$iv$iv$iv2) - leftPadding) - rightPadding), $this$drawWithContent.getLayoutDirection()) + leftPadding + (labelWidth / 2.0f);
            float left = RangesKt.coerceAtLeast((labelCenter - (labelWidth / 2.0f)) - innerPadding, 0.0f);
            float f = (labelWidth / 2.0f) + labelCenter + innerPadding;
            long arg0$iv2 = $this$drawWithContent.mo5671getSizeNHjbRc();
            int bits$iv$iv$iv3 = (int) (arg0$iv2 >> 32);
            float right = RangesKt.coerceAtMost(f, Float.intBitsToFloat(bits$iv$iv$iv3));
            int bits$iv$iv$iv4 = (int) (4294967295L & labelSizeValue);
            float labelHeight = Float.intBitsToFloat(bits$iv$iv$iv4);
            ContentDrawScope $this$clipRect_u2drOu3jXo$iv = $this$drawWithContent;
            float top$iv = (-labelHeight) / 2.0f;
            float bottom$iv = labelHeight / 2.0f;
            int clipOp$iv = ClipOp.INSTANCE.m5085getDifferencertfAjoo();
            DrawContext $this$withTransform_u24lambda_u246$iv$iv = $this$clipRect_u2drOu3jXo$iv.getDrawContext();
            long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv.mo5592getSizeNHjbRc();
            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().save();
            try {
                DrawTransform $this$clipRect_rOu3jXo_u24lambda_u244$iv = $this$withTransform_u24lambda_u246$iv$iv.getTransform();
                $this$clipRect_rOu3jXo_u24lambda_u244$iv.mo5595clipRectN_I0leg(left, top$iv, right, bottom$iv, clipOp$iv);
                $this$drawWithContent.drawContent();
            } finally {
                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u246$iv$iv.mo5593setSizeuvyYCjk(previousSize$iv$iv);
            }
        } else {
            $this$drawWithContent.drawContent();
        }
        return Unit.INSTANCE;
    }
}
