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
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
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
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextField.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000Ê\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a£\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2 \b\u0002\u0010\r\u001a\u001a\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00072\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\b\b\u0002\u0010\"\u001a\u00020#28\b\u0002\u0010$\u001a2\u0012\u0004\u0012\u00020&\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010'0\u0013¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\u0001\u0018\u00010%¢\u0006\u0002\b\u00112\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u0002022\n\b\u0002\u00103\u001a\u0004\u0018\u000104H\u0007¢\u0006\u0002\u00105\u001aØ\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u00106\u001a\u0002072\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u00010\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010;\u001a\u00020<2\b\b\u0002\u0010=\u001a\u00020\u00072\b\b\u0002\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u00020?2\n\b\u0002\u00103\u001a\u0004\u0018\u0001042\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u000200H\u0007¢\u0006\u0002\u0010A\u001aØ\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u00106\u001a\u00020B2\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u00020\u00010\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010;\u001a\u00020<2\b\b\u0002\u0010=\u001a\u00020\u00072\b\b\u0002\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u00020?2\n\b\u0002\u00103\u001a\u0004\u0018\u0001042\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u000200H\u0007¢\u0006\u0002\u0010C\u001aô\u0001\u0010D\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0011\u0010E\u001a\r\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0019\u0010\u0012\u001a\u0015\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e¢\u0006\u0002\b\u00102\u0013\u0010F\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010G\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0006\u0010=\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010H\u001a\u00020I2\u0011\u0010J\u001a\r\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010K\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0006\u0010L\u001a\u000202H\u0001¢\u0006\u0002\u0010M\"\u0016\u0010N\u001a\u00020OX\u0080\u0004¢\u0006\n\n\u0002\u0010R\u001a\u0004\bP\u0010Q¨\u0006S"}, d2 = {"TextField", "", "state", "Landroidx/compose/foundation/text/input/TextFieldState;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "readOnly", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "labelPosition", "Landroidx/compose/material3/TextFieldLabelPosition;", MetadataField.LABEL, "Lkotlin/Function1;", "Landroidx/compose/material3/TextFieldLabelScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "placeholder", "Lkotlin/Function0;", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "isError", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "outputTransformation", "Landroidx/compose/foundation/text/input/OutputTransformation;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "onKeyboardAction", "Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "lineLimits", "Landroidx/compose/foundation/text/input/TextFieldLineLimits;", "onTextLayout", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/text/TextLayoutResult;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "getResult", "scrollState", "Landroidx/compose/foundation/ScrollState;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/TextFieldColors;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/material3/TextFieldLabelPosition;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/foundation/text/input/OutputTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Landroidx/compose/foundation/text/input/TextFieldLineLimits;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/ScrollState;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;IIII)V", "value", "", "onValueChange", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "singleLine", "maxLines", "", "minLines", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "Landroidx/compose/ui/text/input/TextFieldValue;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "TextFieldLayout", "textField", "leading", "trailing", "labelProgress", "Landroidx/compose/material3/internal/FloatProducer;", TtmlNode.RUBY_CONTAINER, "supporting", "paddingValues", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/TextFieldLabelPosition;Landroidx/compose/material3/internal/FloatProducer;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;II)V", "TextFieldWithLabelVerticalPadding", "Landroidx/compose/ui/unit/Dp;", "getTextFieldWithLabelVerticalPadding", "()F", "F", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextFieldKt {
    private static final float TextFieldWithLabelVerticalPadding = Dp.m7905constructorimpl(8);

    static final Unit TextField$lambda$2(TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, TextFieldLabelPosition textFieldLabelPosition, Function3 function3, Function2 function2, Function2 function4, Function2 function5, Function2 function6, Function2 function7, Function2 function8, boolean z3, InputTransformation inputTransformation, OutputTransformation outputTransformation, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, TextFieldLineLimits textFieldLineLimits, Function2 function9, ScrollState scrollState, Shape shape, TextFieldColors textFieldColors, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, int i4, Composer composer, int i5) {
        TextField(textFieldState, modifier, z, z2, textStyle, textFieldLabelPosition, function3, function2, function4, function5, function6, function7, function8, z3, inputTransformation, outputTransformation, keyboardOptions, keyboardActionHandler, textFieldLineLimits, function9, scrollState, shape, textFieldColors, paddingValues, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit TextField$lambda$5(String str, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, Function2 function6, Function2 function7, Function2 function8, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i3, int i4, int i5, int i6, Composer composer, int i7) {
        TextField(str, (Function1<? super String, Unit>) function1, modifier, z, z2, textStyle, (Function2<? super Composer, ? super Integer, Unit>) function2, (Function2<? super Composer, ? super Integer, Unit>) function3, (Function2<? super Composer, ? super Integer, Unit>) function4, (Function2<? super Composer, ? super Integer, Unit>) function5, (Function2<? super Composer, ? super Integer, Unit>) function6, (Function2<? super Composer, ? super Integer, Unit>) function7, (Function2<? super Composer, ? super Integer, Unit>) function8, z3, visualTransformation, keyboardOptions, keyboardActions, z4, i, i2, mutableInteractionSource, shape, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
        return Unit.INSTANCE;
    }

    static final Unit TextField$lambda$8(TextFieldValue textFieldValue, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, Function2 function6, Function2 function7, Function2 function8, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i3, int i4, int i5, int i6, Composer composer, int i7) {
        TextField(textFieldValue, (Function1<? super TextFieldValue, Unit>) function1, modifier, z, z2, textStyle, (Function2<? super Composer, ? super Integer, Unit>) function2, (Function2<? super Composer, ? super Integer, Unit>) function3, (Function2<? super Composer, ? super Integer, Unit>) function4, (Function2<? super Composer, ? super Integer, Unit>) function5, (Function2<? super Composer, ? super Integer, Unit>) function6, (Function2<? super Composer, ? super Integer, Unit>) function7, (Function2<? super Composer, ? super Integer, Unit>) function8, z3, visualTransformation, keyboardOptions, keyboardActions, z4, i, i2, mutableInteractionSource, shape, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
        return Unit.INSTANCE;
    }

    static final Unit TextFieldLayout$lambda$20(Modifier modifier, Function2 function2, Function2 function3, Function3 function4, Function2 function5, Function2 function6, Function2 function7, Function2 function8, boolean z, TextFieldLabelPosition textFieldLabelPosition, FloatProducer floatProducer, Function2 function9, Function2 function10, PaddingValues paddingValues, int i, int i2, Composer composer, int i3) {
        TextFieldLayout(modifier, function2, function3, function4, function5, function6, function7, function8, z, textFieldLabelPosition, floatProducer, function9, function10, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    public static final void TextField(final TextFieldState state, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, TextFieldLabelPosition labelPosition, Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, boolean isError, InputTransformation inputTransformation, OutputTransformation outputTransformation, KeyboardOptions keyboardOptions, KeyboardActionHandler onKeyboardAction, TextFieldLineLimits lineLimits, Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function9, ScrollState scrollState, Shape shape, TextFieldColors colors, PaddingValues contentPadding, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        final TextFieldState textFieldState;
        Modifier modifier2;
        boolean enabled2;
        boolean readOnly2;
        TextStyle textStyle2;
        TextFieldLabelPosition labelPosition2;
        int i2;
        int i3;
        int i4;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final boolean isError2;
        final InputTransformation inputTransformation2;
        final OutputTransformation outputTransformation2;
        final KeyboardOptions keyboardOptions2;
        final KeyboardActionHandler onKeyboardAction2;
        final TextFieldLineLimits lineLimits2;
        final Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function11;
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
        final Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function12;
        final Function2<? super Composer, ? super Integer, Unit> function13;
        final Function2<? super Composer, ? super Integer, Unit> function14;
        final Function2<? super Composer, ? super Integer, Unit> function15;
        final Function2<? super Composer, ? super Integer, Unit> function16;
        final Function2<? super Composer, ? super Integer, Unit> function17;
        TextFieldLineLimits lineLimits3;
        ScrollState scrollState3;
        Shape shape3;
        TextFieldColors colors3;
        PaddingValues contentPadding3;
        final Shape shape4;
        final PaddingValues contentPadding4;
        final boolean readOnly4;
        final Function2<? super Composer, ? super Integer, Unit> function18;
        final InputTransformation inputTransformation3;
        final OutputTransformation outputTransformation3;
        final KeyboardOptions keyboardOptions3;
        final KeyboardActionHandler onKeyboardAction3;
        final Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function19;
        final ScrollState scrollState4;
        final Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function20;
        final Function2<? super Composer, ? super Integer, Unit> function21;
        MutableInteractionSource interactionSource3;
        final Function2<? super Composer, ? super Integer, Unit> function22;
        final TextFieldLabelPosition labelPosition4;
        final Modifier modifier4;
        final Function2<? super Composer, ? super Integer, Unit> function23;
        final Function2<? super Composer, ? super Integer, Unit> function24;
        final boolean isError3;
        final TextFieldLineLimits lineLimits4;
        final TextFieldColors colors4;
        final Function2<? super Composer, ? super Integer, Unit> function25;
        TextStyle textStyle4;
        MutableInteractionSource interactionSource4;
        final MutableInteractionSource interactionSource5;
        int i5;
        Composer $composer2 = $composer.startRestartGroup(-1717599650);
        ComposerKt.sourceInformation($composer2, "C(TextField)N(state,modifier,enabled,readOnly,textStyle,labelPosition,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,isError,inputTransformation,outputTransformation,keyboardOptions,onKeyboardAction,lineLimits,onTextLayout,scrollState,shape,colors,contentPadding,interactionSource)298@16411L2145,298@16324L2232:TextField.kt#uh7d8r");
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
            ComposerKt.sourceInformation($composer2, "261@14511L7,277@15401L21,278@15461L5,279@15516L8");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
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
                Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function26 = i10 != 0 ? null : function3;
                Function2<? super Composer, ? super Integer, Unit> function27 = i11 != 0 ? null : function2;
                Function2<? super Composer, ? super Integer, Unit> function28 = i12 != 0 ? null : function4;
                Function2<? super Composer, ? super Integer, Unit> function29 = i13 != 0 ? null : function5;
                Function2<? super Composer, ? super Integer, Unit> function30 = i14 != 0 ? null : function6;
                Function2<? super Composer, ? super Integer, Unit> function31 = i15 != 0 ? null : function7;
                Function2<? super Composer, ? super Integer, Unit> function32 = i2 != 0 ? null : function8;
                boolean isError4 = i3 != 0 ? false : isError;
                InputTransformation inputTransformation4 = i4 != 0 ? null : inputTransformation;
                OutputTransformation outputTransformation4 = i19 != 0 ? null : outputTransformation;
                KeyboardOptions keyboardOptions4 = i20 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions;
                KeyboardActionHandler onKeyboardAction4 = i21 != 0 ? null : onKeyboardAction;
                if ((i & 262144) != 0) {
                    lineLimits3 = TextFieldLineLimits.INSTANCE.getDefault();
                    $dirty1 &= -234881025;
                } else {
                    lineLimits3 = lineLimits;
                }
                Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function33 = i22 != 0 ? null : function9;
                if ((i & 1048576) != 0) {
                    scrollState3 = ScrollKt.rememberScrollState(0, $composer2, 0, 1);
                    $dirty2 &= -15;
                } else {
                    scrollState3 = scrollState;
                }
                if ((2097152 & i) != 0) {
                    shape3 = TextFieldDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty2 &= -113;
                } else {
                    shape3 = shape;
                }
                if ((i & 4194304) != 0) {
                    colors3 = TextFieldDefaults.INSTANCE.colors($composer2, 6);
                    $dirty2 &= -897;
                } else {
                    colors3 = colors;
                }
                if ((i & 8388608) != 0) {
                    contentPadding3 = (function26 == null || (labelPosition2 instanceof TextFieldLabelPosition.Above)) ? TextFieldDefaults.m3042contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null) : TextFieldDefaults.m3041contentPaddingWithLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -7169;
                } else {
                    contentPadding3 = contentPadding;
                }
                if (i23 != 0) {
                    shape4 = shape3;
                    contentPadding4 = contentPadding3;
                    readOnly4 = readOnly2;
                    interactionSource3 = null;
                    function18 = function32;
                    inputTransformation3 = inputTransformation4;
                    outputTransformation3 = outputTransformation4;
                    keyboardOptions3 = keyboardOptions4;
                    onKeyboardAction3 = onKeyboardAction4;
                    function19 = function33;
                    scrollState4 = scrollState3;
                    function20 = function26;
                    function21 = function27;
                    function22 = function28;
                    labelPosition4 = labelPosition2;
                    modifier4 = modifier2;
                    function23 = function30;
                    function24 = function31;
                    isError3 = isError4;
                    lineLimits4 = lineLimits3;
                    colors4 = colors3;
                    function25 = function29;
                    textStyle4 = textStyle2;
                } else {
                    shape4 = shape3;
                    contentPadding4 = contentPadding3;
                    readOnly4 = readOnly2;
                    function18 = function32;
                    inputTransformation3 = inputTransformation4;
                    outputTransformation3 = outputTransformation4;
                    keyboardOptions3 = keyboardOptions4;
                    onKeyboardAction3 = onKeyboardAction4;
                    function19 = function33;
                    scrollState4 = scrollState3;
                    function20 = function26;
                    function21 = function27;
                    interactionSource3 = interactionSource;
                    function22 = function28;
                    labelPosition4 = labelPosition2;
                    modifier4 = modifier2;
                    function23 = function30;
                    function24 = function31;
                    isError3 = isError4;
                    lineLimits4 = lineLimits3;
                    colors4 = colors3;
                    function25 = function29;
                    textStyle4 = textStyle2;
                }
            } else {
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
                function20 = function3;
                function21 = function2;
                function22 = function4;
                function25 = function5;
                function23 = function6;
                function24 = function7;
                function18 = function8;
                inputTransformation3 = inputTransformation;
                outputTransformation3 = outputTransformation;
                keyboardOptions3 = keyboardOptions;
                onKeyboardAction3 = onKeyboardAction;
                lineLimits4 = lineLimits;
                function19 = function9;
                scrollState4 = scrollState;
                shape4 = shape;
                colors4 = colors;
                contentPadding4 = contentPadding;
                interactionSource3 = interactionSource;
                readOnly4 = readOnly2;
                textStyle4 = textStyle2;
                labelPosition4 = labelPosition2;
                modifier4 = modifier2;
                isError3 = isError;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1717599650, $dirty, $dirty1, "androidx.compose.material3.TextField (TextField.kt:287)");
            }
            if (interactionSource3 == null) {
                $composer2.startReplaceGroup(1230824445);
                ComposerKt.sourceInformation($composer2, "289@15925L39");
                ComposerKt.sourceInformationMarkerStart($composer2, -1345769307, "CC(remember):TextField.kt#9igjgp");
                Object it$iv = $composer2.rememberedValue();
                interactionSource4 = interactionSource3;
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
                $composer2.startReplaceGroup(-1345769958);
                $composer2.endReplaceGroup();
                interactionSource5 = interactionSource4;
            }
            $composer2.startReplaceGroup(-1345763848);
            ComposerKt.sourceInformation($composer2, "*293@16149L25");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle4.m7358getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource5, $composer2, 0).getValue().booleanValue();
                $this$takeOrElse_u2dDxMtmZc$iv = colors4.m3039textColorXeAY9LY$material3(enabled2, isError3, focused);
            }
            $composer2.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            final TextStyle mergedTextStyle = textStyle4.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            final boolean enabled4 = enabled2;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors4.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(484558238, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt.TextField.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed3) {
                    ComposerKt.sourceInformation($composer3, "C303@16564L38,338@18169L351,321@17398L1141,299@16421L2129:TextField.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(484558238, $changed3, -1, "androidx.compose.material3.TextField.<anonymous> (TextField.kt:299)");
                    }
                    Modifier modifier5 = modifier4;
                    boolean z = isError3;
                    Strings.Companion companion = Strings.INSTANCE;
                    Modifier modifierM849defaultMinSizeVpY3zN4 = SizeKt.m849defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifier5, z, Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer3, 0)), TextFieldDefaults.INSTANCE.m3058getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m3057getMinHeightD9Ej5fM());
                    SolidColor solidColor = new SolidColor(colors4.m2989cursorColorvNxB06k$material3(isError3), null);
                    TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                    TextFieldState textFieldState2 = textFieldState;
                    boolean z2 = enabled4;
                    TextFieldLineLimits textFieldLineLimits = lineLimits4;
                    OutputTransformation outputTransformation5 = outputTransformation3;
                    MutableInteractionSource mutableInteractionSource = interactionSource5;
                    TextFieldLabelPosition textFieldLabelPosition = labelPosition4;
                    Function3<TextFieldLabelScope, Composer, Integer, Unit> function34 = function20;
                    Function2<Composer, Integer, Unit> function35 = function21;
                    Function2<Composer, Integer, Unit> function36 = function22;
                    Function2<Composer, Integer, Unit> function37 = function25;
                    Function2<Composer, Integer, Unit> function38 = function23;
                    Function2<Composer, Integer, Unit> function39 = function24;
                    Function2<Composer, Integer, Unit> function40 = function18;
                    boolean z3 = isError3;
                    TextFieldColors textFieldColors = colors4;
                    PaddingValues paddingValues = contentPadding4;
                    final boolean z4 = enabled4;
                    final boolean z5 = isError3;
                    final MutableInteractionSource mutableInteractionSource2 = interactionSource5;
                    final TextFieldColors textFieldColors2 = colors4;
                    final Shape shape5 = shape4;
                    BasicTextFieldKt.BasicTextField(textFieldState, modifierM849defaultMinSizeVpY3zN4, enabled4, readOnly4, inputTransformation3, mergedTextStyle, keyboardOptions3, onKeyboardAction3, lineLimits4, function19, interactionSource5, solidColor, outputTransformation3, textFieldDefaults.decorator(textFieldState2, z2, textFieldLineLimits, outputTransformation5, mutableInteractionSource, textFieldLabelPosition, function34, function35, function36, function37, function38, function39, function40, z3, textFieldColors, paddingValues, ComposableLambdaKt.rememberComposableLambda(-2009308227, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt.TextField.1.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed4) {
                            ComposerKt.sourceInformation($composer4, "C339@18213L285:TextField.kt#uh7d8r");
                            if (!$composer4.shouldExecute(($changed4 & 3) != 2, $changed4 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2009308227, $changed4, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:339)");
                            }
                            TextFieldDefaults.INSTANCE.m3051Container4EFweAY(z4, z5, mutableInteractionSource2, null, textFieldColors2, shape5, 0.0f, 0.0f, $composer4, 100663296, 200);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54), $composer3, 0, 14155776, 0), scrollState4, $composer3, 0, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enabled3 = enabled4;
            lineLimits2 = lineLimits4;
            outputTransformation2 = outputTransformation3;
            labelPosition3 = labelPosition4;
            function10 = function21;
            function13 = function22;
            function14 = function25;
            function15 = function23;
            function16 = function24;
            contentPadding2 = contentPadding4;
            inputTransformation2 = inputTransformation3;
            keyboardOptions2 = keyboardOptions3;
            onKeyboardAction2 = onKeyboardAction3;
            function11 = function19;
            shape2 = shape4;
            interactionSource2 = interactionSource4;
            colors2 = colors4;
            function12 = function20;
            scrollState2 = scrollState4;
            textStyle3 = textStyle4;
            isError2 = isError3;
            function17 = function18;
            readOnly3 = readOnly4;
        } else {
            $composer2.skipToGroupEnd();
            function10 = function2;
            isError2 = isError;
            inputTransformation2 = inputTransformation;
            outputTransformation2 = outputTransformation;
            keyboardOptions2 = keyboardOptions;
            onKeyboardAction2 = onKeyboardAction;
            lineLimits2 = lineLimits;
            function11 = function9;
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
            function12 = function3;
            function13 = function4;
            function14 = function5;
            function15 = function6;
            function16 = function7;
            function17 = function8;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TextFieldKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldKt.TextField$lambda$2(state, modifier3, enabled3, readOnly3, textStyle3, labelPosition3, function12, function10, function13, function14, function15, function16, function17, isError2, inputTransformation2, outputTransformation2, keyboardOptions2, onKeyboardAction2, lineLimits2, function11, scrollState2, shape2, colors2, contentPadding2, interactionSource2, $changed, $changed1, $changed2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void TextField(final String value, final Function1<? super String, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        final String str;
        Function1<? super String, Unit> function9;
        Modifier modifier2;
        boolean enabled2;
        boolean readOnly2;
        TextStyle textStyle2;
        Function2<? super Composer, ? super Integer, Unit> function10;
        int i2;
        int i3;
        int i4;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final Function2<? super Composer, ? super Integer, Unit> function12;
        final Function2<? super Composer, ? super Integer, Unit> function13;
        final VisualTransformation visualTransformation2;
        final KeyboardOptions keyboardOptions2;
        final KeyboardActions keyboardActions2;
        final boolean readOnly3;
        final int maxLines2;
        final int minLines2;
        final MutableInteractionSource interactionSource2;
        final Shape shape2;
        final TextFieldColors colors2;
        final boolean enabled3;
        final boolean readOnly4;
        final TextStyle textStyle3;
        final Function2<? super Composer, ? super Integer, Unit> function14;
        final Function2<? super Composer, ? super Integer, Unit> function15;
        final Function2<? super Composer, ? super Integer, Unit> function16;
        final Function2<? super Composer, ? super Integer, Unit> function17;
        final boolean isError2;
        Function2<? super Composer, ? super Integer, Unit> function18;
        Function2<? super Composer, ? super Integer, Unit> function19;
        Function2<? super Composer, ? super Integer, Unit> function20;
        Function2<? super Composer, ? super Integer, Unit> function21;
        Function2<? super Composer, ? super Integer, Unit> function22;
        Function2<? super Composer, ? super Integer, Unit> function23;
        boolean isError3;
        VisualTransformation visualTransformation3;
        KeyboardOptions keyboardOptions3;
        final KeyboardActions keyboardActions3;
        final boolean singleLine2;
        final int maxLines3;
        final int minLines3;
        MutableInteractionSource interactionSource3;
        Shape shape3;
        final Function2<? super Composer, ? super Integer, Unit> function24;
        final Shape shape4;
        final Function2<? super Composer, ? super Integer, Unit> function25;
        TextStyle textStyle4;
        final Function2<? super Composer, ? super Integer, Unit> function26;
        final Function2<? super Composer, ? super Integer, Unit> function27;
        final Function2<? super Composer, ? super Integer, Unit> function28;
        final Function2<? super Composer, ? super Integer, Unit> function29;
        final boolean isError4;
        MutableInteractionSource interactionSource4;
        final TextFieldColors colors3;
        final Modifier modifier3;
        final VisualTransformation visualTransformation4;
        final KeyboardOptions keyboardOptions4;
        final boolean readOnly5;
        MutableInteractionSource interactionSource5;
        final MutableInteractionSource interactionSource6;
        int i5;
        Composer $composer2 = $composer.startRestartGroup(-154966360);
        ComposerKt.sourceInformation($composer2, "C(TextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,isError,visualTransformation,keyboardOptions,keyboardActions,singleLine,maxLines,minLines,interactionSource,shape,colors)453@24857L1959,453@24770L2046:TextField.kt#uh7d8r");
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
            function9 = function1;
        } else if (($changed & 48) == 0) {
            function9 = function1;
            $dirty |= $composer2.changedInstance(function9) ? 32 : 16;
        } else {
            function9 = function1;
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
            function10 = function2;
        } else if (($changed & 1572864) == 0) {
            function10 = function2;
            $dirty |= $composer2.changedInstance(function10) ? 1048576 : 524288;
        } else {
            function10 = function2;
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
            ComposerKt.sourceInformation($composer2, "424@23425L7,440@24221L5,441@24276L8");
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
                function24 = function3;
                function12 = function4;
                function25 = function5;
                function26 = function6;
                function28 = function7;
                function29 = function8;
                isError4 = isError;
                visualTransformation4 = visualTransformation;
                keyboardOptions4 = keyboardOptions;
                keyboardActions3 = keyboardActions;
                singleLine2 = singleLine;
                maxLines3 = maxLines;
                minLines3 = minLines;
                interactionSource4 = interactionSource;
                shape4 = shape;
                readOnly5 = readOnly2;
                textStyle4 = textStyle2;
                function27 = function10;
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
                    function10 = null;
                }
                if (i10 == 0) {
                    function18 = function3;
                } else {
                    function18 = null;
                }
                if (i11 == 0) {
                    function19 = function4;
                } else {
                    function19 = null;
                }
                if (i12 == 0) {
                    function20 = function5;
                } else {
                    function20 = null;
                }
                if (i13 == 0) {
                    function21 = function6;
                } else {
                    function21 = null;
                }
                if (i14 == 0) {
                    function22 = function7;
                } else {
                    function22 = null;
                }
                if (i2 == 0) {
                    function23 = function8;
                } else {
                    function23 = null;
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
                Function2<? super Composer, ? super Integer, Unit> function30 = function18;
                if ((i & 2097152) != 0) {
                    shape3 = TextFieldDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty2 &= -113;
                } else {
                    shape3 = shape;
                }
                if ((i & 4194304) == 0) {
                    function24 = function30;
                    function12 = function19;
                    shape4 = shape3;
                    function25 = function20;
                    textStyle4 = textStyle2;
                    function26 = function21;
                    function27 = function10;
                    function28 = function22;
                    function29 = function23;
                    isError4 = isError3;
                    interactionSource4 = interactionSource3;
                    colors3 = colors;
                    modifier3 = modifier2;
                    visualTransformation4 = visualTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    readOnly5 = readOnly2;
                } else {
                    $dirty2 &= -897;
                    function24 = function30;
                    function12 = function19;
                    shape4 = shape3;
                    function25 = function20;
                    function26 = function21;
                    function27 = function10;
                    function28 = function22;
                    function29 = function23;
                    isError4 = isError3;
                    interactionSource4 = interactionSource3;
                    colors3 = TextFieldDefaults.INSTANCE.colors($composer2, 6);
                    textStyle4 = textStyle2;
                    visualTransformation4 = visualTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    modifier3 = modifier2;
                    readOnly5 = readOnly2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-154966360, $dirty, $dirty1, "androidx.compose.material3.TextField (TextField.kt:442)");
            }
            if (interactionSource4 == null) {
                $composer2.startReplaceGroup(488210003);
                ComposerKt.sourceInformation($composer2, "444@24371L39");
                ComposerKt.sourceInformationMarkerStart($composer2, 1401222031, "CC(remember):TextField.kt#9igjgp");
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
                $composer2.startReplaceGroup(1401221380);
                $composer2.endReplaceGroup();
                interactionSource6 = interactionSource5;
            }
            $composer2.startReplaceGroup(1401227490);
            ComposerKt.sourceInformation($composer2, "*448@24595L25");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle4.m7358getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource6, $composer2, 0).getValue().booleanValue();
                $this$takeOrElse_u2dDxMtmZc$iv = colors3.m3039textColorXeAY9LY$material3(enabled2, isError4, focused);
            }
            $composer2.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            final TextStyle mergedTextStyle = textStyle4.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            final Function1<? super String, Unit> function31 = function9;
            final boolean enabled4 = enabled2;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors3.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(1459735400, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt.TextField.3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed3) {
                    ComposerKt.sourceInformation($composer3, "C458@25010L38,476@25814L985,454@24867L1943:TextField.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1459735400, $changed3, -1, "androidx.compose.material3.TextField.<anonymous> (TextField.kt:454)");
                    }
                    Modifier modifier4 = modifier3;
                    boolean z = isError4;
                    Strings.Companion companion = Strings.INSTANCE;
                    Modifier modifierM849defaultMinSizeVpY3zN4 = SizeKt.m849defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifier4, z, Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer3, 0)), TextFieldDefaults.INSTANCE.m3058getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m3057getMinHeightD9Ej5fM());
                    SolidColor solidColor = new SolidColor(colors3.m2989cursorColorvNxB06k$material3(isError4), null);
                    final String str2 = str;
                    final boolean z2 = enabled4;
                    final boolean z3 = singleLine2;
                    final VisualTransformation visualTransformation5 = visualTransformation4;
                    final MutableInteractionSource mutableInteractionSource = interactionSource6;
                    final boolean z4 = isError4;
                    final Function2<Composer, Integer, Unit> function32 = function27;
                    final Function2<Composer, Integer, Unit> function33 = function24;
                    final Function2<Composer, Integer, Unit> function34 = function12;
                    final Function2<Composer, Integer, Unit> function35 = function25;
                    final Function2<Composer, Integer, Unit> function36 = function26;
                    final Function2<Composer, Integer, Unit> function37 = function28;
                    final Function2<Composer, Integer, Unit> function38 = function29;
                    final Shape shape5 = shape4;
                    final TextFieldColors textFieldColors = colors3;
                    BasicTextFieldKt.BasicTextField(str, function31, modifierM849defaultMinSizeVpY3zN4, enabled4, readOnly5, mergedTextStyle, keyboardOptions4, keyboardActions3, singleLine2, maxLines3, minLines3, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, interactionSource6, solidColor, ComposableLambdaKt.rememberComposableLambda(1451491557, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt.TextField.3.1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function39, Composer composer, Integer num) {
                            invoke((Function2<? super Composer, ? super Integer, Unit>) function39, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function39, Composer $composer4, int $changed4) {
                            Function2<? super Composer, ? super Integer, Unit> function40;
                            ComposerKt.sourceInformation($composer4, "CN(innerTextField)478@25969L812:TextField.kt#uh7d8r");
                            int $dirty3 = $changed4;
                            if (($changed4 & 6) == 0) {
                                function40 = function39;
                                $dirty3 |= $composer4.changedInstance(function40) ? 4 : 2;
                            } else {
                                function40 = function39;
                            }
                            if ($composer4.shouldExecute(($dirty3 & 19) != 18, $dirty3 & 1)) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1451491557, $dirty3, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:478)");
                                }
                                TextFieldDefaults.INSTANCE.DecorationBox(str2, function40, z2, z3, visualTransformation5, mutableInteractionSource, z4, function32, function33, function34, function35, function36, function37, function38, shape5, textFieldColors, null, null, $composer4, ($dirty3 << 3) & 112, 100663296, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer4.skipToGroupEnd();
                        }
                    }, $composer3, 54), $composer3, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            keyboardOptions2 = keyboardOptions4;
            maxLines2 = maxLines3;
            minLines2 = minLines3;
            visualTransformation2 = visualTransformation4;
            function11 = function24;
            function15 = function25;
            function16 = function26;
            function17 = function28;
            colors2 = colors3;
            readOnly4 = readOnly5;
            readOnly3 = singleLine2;
            function14 = function27;
            shape2 = shape4;
            textStyle3 = textStyle4;
            isError2 = isError4;
            enabled3 = enabled4;
            keyboardActions2 = keyboardActions3;
            function13 = function29;
            interactionSource2 = interactionSource5;
        } else {
            $composer2.skipToGroupEnd();
            function11 = function3;
            function12 = function4;
            function13 = function8;
            visualTransformation2 = visualTransformation;
            keyboardOptions2 = keyboardOptions;
            keyboardActions2 = keyboardActions;
            readOnly3 = singleLine;
            maxLines2 = maxLines;
            minLines2 = minLines;
            interactionSource2 = interactionSource;
            shape2 = shape;
            colors2 = colors;
            enabled3 = enabled2;
            readOnly4 = readOnly2;
            textStyle3 = textStyle2;
            function14 = function10;
            function15 = function5;
            function16 = function6;
            function17 = function7;
            isError2 = isError;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final Function2<? super Composer, ? super Integer, Unit> function32 = function12;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TextFieldKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldKt.TextField$lambda$5(value, function1, modifier4, enabled3, readOnly4, textStyle3, function14, function11, function32, function15, function16, function17, function13, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, readOnly3, maxLines2, minLines2, interactionSource2, shape2, colors2, $changed, $changed1, $changed2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void TextField(final TextFieldValue value, final Function1<? super TextFieldValue, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        final TextFieldValue textFieldValue;
        Function1<? super TextFieldValue, Unit> function9;
        Modifier modifier2;
        boolean enabled2;
        boolean readOnly2;
        TextStyle textStyle2;
        Function2<? super Composer, ? super Integer, Unit> function10;
        int i2;
        int i3;
        int i4;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final Function2<? super Composer, ? super Integer, Unit> function12;
        final Function2<? super Composer, ? super Integer, Unit> function13;
        final VisualTransformation visualTransformation2;
        final KeyboardOptions keyboardOptions2;
        final KeyboardActions keyboardActions2;
        final boolean readOnly3;
        final int maxLines2;
        final int minLines2;
        final MutableInteractionSource interactionSource2;
        final Shape shape2;
        final TextFieldColors colors2;
        final boolean enabled3;
        final boolean readOnly4;
        final TextStyle textStyle3;
        final Function2<? super Composer, ? super Integer, Unit> function14;
        final Function2<? super Composer, ? super Integer, Unit> function15;
        final Function2<? super Composer, ? super Integer, Unit> function16;
        final Function2<? super Composer, ? super Integer, Unit> function17;
        final boolean isError2;
        Function2<? super Composer, ? super Integer, Unit> function18;
        Function2<? super Composer, ? super Integer, Unit> function19;
        Function2<? super Composer, ? super Integer, Unit> function20;
        Function2<? super Composer, ? super Integer, Unit> function21;
        Function2<? super Composer, ? super Integer, Unit> function22;
        Function2<? super Composer, ? super Integer, Unit> function23;
        boolean isError3;
        VisualTransformation visualTransformation3;
        KeyboardOptions keyboardOptions3;
        final KeyboardActions keyboardActions3;
        final boolean singleLine2;
        final int maxLines3;
        final int minLines3;
        MutableInteractionSource interactionSource3;
        Shape shape3;
        final Function2<? super Composer, ? super Integer, Unit> function24;
        final Shape shape4;
        final Function2<? super Composer, ? super Integer, Unit> function25;
        TextStyle textStyle4;
        final Function2<? super Composer, ? super Integer, Unit> function26;
        final Function2<? super Composer, ? super Integer, Unit> function27;
        final Function2<? super Composer, ? super Integer, Unit> function28;
        final Function2<? super Composer, ? super Integer, Unit> function29;
        final boolean isError4;
        MutableInteractionSource interactionSource4;
        final TextFieldColors colors3;
        final Modifier modifier3;
        final VisualTransformation visualTransformation4;
        final KeyboardOptions keyboardOptions4;
        final boolean readOnly5;
        MutableInteractionSource interactionSource5;
        final MutableInteractionSource interactionSource6;
        int i5;
        Composer $composer2 = $composer.startRestartGroup(-1126989771);
        ComposerKt.sourceInformation($composer2, "C(TextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,isError,visualTransformation,keyboardOptions,keyboardActions,singleLine,maxLines,minLines,interactionSource,shape,colors)603@33216L1964,603@33129L2051:TextField.kt#uh7d8r");
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
            function9 = function1;
        } else if (($changed & 48) == 0) {
            function9 = function1;
            $dirty |= $composer2.changedInstance(function9) ? 32 : 16;
        } else {
            function9 = function1;
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
            function10 = function2;
        } else if (($changed & 1572864) == 0) {
            function10 = function2;
            $dirty |= $composer2.changedInstance(function10) ? 1048576 : 524288;
        } else {
            function10 = function2;
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
            ComposerKt.sourceInformation($composer2, "574@31784L7,590@32580L5,591@32635L8");
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
                function24 = function3;
                function12 = function4;
                function25 = function5;
                function26 = function6;
                function28 = function7;
                function29 = function8;
                isError4 = isError;
                visualTransformation4 = visualTransformation;
                keyboardOptions4 = keyboardOptions;
                keyboardActions3 = keyboardActions;
                singleLine2 = singleLine;
                maxLines3 = maxLines;
                minLines3 = minLines;
                interactionSource4 = interactionSource;
                shape4 = shape;
                readOnly5 = readOnly2;
                textStyle4 = textStyle2;
                function27 = function10;
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
                    function10 = null;
                }
                if (i10 == 0) {
                    function18 = function3;
                } else {
                    function18 = null;
                }
                if (i11 == 0) {
                    function19 = function4;
                } else {
                    function19 = null;
                }
                if (i12 == 0) {
                    function20 = function5;
                } else {
                    function20 = null;
                }
                if (i13 == 0) {
                    function21 = function6;
                } else {
                    function21 = null;
                }
                if (i14 == 0) {
                    function22 = function7;
                } else {
                    function22 = null;
                }
                if (i2 == 0) {
                    function23 = function8;
                } else {
                    function23 = null;
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
                Function2<? super Composer, ? super Integer, Unit> function30 = function18;
                if ((i & 2097152) != 0) {
                    shape3 = TextFieldDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty2 &= -113;
                } else {
                    shape3 = shape;
                }
                if ((i & 4194304) == 0) {
                    function24 = function30;
                    function12 = function19;
                    shape4 = shape3;
                    function25 = function20;
                    textStyle4 = textStyle2;
                    function26 = function21;
                    function27 = function10;
                    function28 = function22;
                    function29 = function23;
                    isError4 = isError3;
                    interactionSource4 = interactionSource3;
                    colors3 = colors;
                    modifier3 = modifier2;
                    visualTransformation4 = visualTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    readOnly5 = readOnly2;
                } else {
                    $dirty2 &= -897;
                    function24 = function30;
                    function12 = function19;
                    shape4 = shape3;
                    function25 = function20;
                    function26 = function21;
                    function27 = function10;
                    function28 = function22;
                    function29 = function23;
                    isError4 = isError3;
                    interactionSource4 = interactionSource3;
                    colors3 = TextFieldDefaults.INSTANCE.colors($composer2, 6);
                    textStyle4 = textStyle2;
                    visualTransformation4 = visualTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    modifier3 = modifier2;
                    readOnly5 = readOnly2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1126989771, $dirty, $dirty1, "androidx.compose.material3.TextField (TextField.kt:592)");
            }
            if (interactionSource4 == null) {
                $composer2.startReplaceGroup(-391701594);
                ComposerKt.sourceInformation($composer2, "594@32730L39");
                ComposerKt.sourceInformationMarkerStart($composer2, -705372196, "CC(remember):TextField.kt#9igjgp");
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
                $composer2.startReplaceGroup(-705372847);
                $composer2.endReplaceGroup();
                interactionSource6 = interactionSource5;
            }
            $composer2.startReplaceGroup(-705366737);
            ComposerKt.sourceInformation($composer2, "*598@32954L25");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle4.m7358getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource6, $composer2, 0).getValue().booleanValue();
                $this$takeOrElse_u2dDxMtmZc$iv = colors3.m3039textColorXeAY9LY$material3(enabled2, isError4, focused);
            }
            $composer2.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            final TextStyle mergedTextStyle = textStyle4.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            final Function1<? super TextFieldValue, Unit> function31 = function9;
            final boolean enabled4 = enabled2;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors3.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(-306109195, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt.TextField.5
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed3) {
                    ComposerKt.sourceInformation($composer3, "C608@33369L38,626@34173L990,604@33226L1948:TextField.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-306109195, $changed3, -1, "androidx.compose.material3.TextField.<anonymous> (TextField.kt:604)");
                    }
                    Modifier modifier4 = modifier3;
                    boolean z = isError4;
                    Strings.Companion companion = Strings.INSTANCE;
                    Modifier modifierM849defaultMinSizeVpY3zN4 = SizeKt.m849defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifier4, z, Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer3, 0)), TextFieldDefaults.INSTANCE.m3058getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m3057getMinHeightD9Ej5fM());
                    SolidColor solidColor = new SolidColor(colors3.m2989cursorColorvNxB06k$material3(isError4), null);
                    final TextFieldValue textFieldValue2 = textFieldValue;
                    final boolean z2 = enabled4;
                    final boolean z3 = singleLine2;
                    final VisualTransformation visualTransformation5 = visualTransformation4;
                    final MutableInteractionSource mutableInteractionSource = interactionSource6;
                    final boolean z4 = isError4;
                    final Function2<Composer, Integer, Unit> function32 = function27;
                    final Function2<Composer, Integer, Unit> function33 = function24;
                    final Function2<Composer, Integer, Unit> function34 = function12;
                    final Function2<Composer, Integer, Unit> function35 = function25;
                    final Function2<Composer, Integer, Unit> function36 = function26;
                    final Function2<Composer, Integer, Unit> function37 = function28;
                    final Function2<Composer, Integer, Unit> function38 = function29;
                    final Shape shape5 = shape4;
                    final TextFieldColors textFieldColors = colors3;
                    BasicTextFieldKt.BasicTextField(textFieldValue, function31, modifierM849defaultMinSizeVpY3zN4, enabled4, readOnly5, mergedTextStyle, keyboardOptions4, keyboardActions3, singleLine2, maxLines3, minLines3, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, interactionSource6, solidColor, ComposableLambdaKt.rememberComposableLambda(-609710734, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt.TextField.5.1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function39, Composer composer, Integer num) {
                            invoke((Function2<? super Composer, ? super Integer, Unit>) function39, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function39, Composer $composer4, int $changed4) {
                            Function2<? super Composer, ? super Integer, Unit> function40;
                            ComposerKt.sourceInformation($composer4, "CN(innerTextField)628@34328L817:TextField.kt#uh7d8r");
                            int $dirty3 = $changed4;
                            if (($changed4 & 6) == 0) {
                                function40 = function39;
                                $dirty3 |= $composer4.changedInstance(function40) ? 4 : 2;
                            } else {
                                function40 = function39;
                            }
                            if ($composer4.shouldExecute(($dirty3 & 19) != 18, $dirty3 & 1)) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-609710734, $dirty3, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:628)");
                                }
                                TextFieldDefaults.INSTANCE.DecorationBox(textFieldValue2.getText(), function40, z2, z3, visualTransformation5, mutableInteractionSource, z4, function32, function33, function34, function35, function36, function37, function38, shape5, textFieldColors, null, null, $composer4, ($dirty3 << 3) & 112, 100663296, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer4.skipToGroupEnd();
                        }
                    }, $composer3, 54), $composer3, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            keyboardOptions2 = keyboardOptions4;
            maxLines2 = maxLines3;
            minLines2 = minLines3;
            visualTransformation2 = visualTransformation4;
            function11 = function24;
            function15 = function25;
            function16 = function26;
            function17 = function28;
            colors2 = colors3;
            readOnly4 = readOnly5;
            readOnly3 = singleLine2;
            function14 = function27;
            shape2 = shape4;
            textStyle3 = textStyle4;
            isError2 = isError4;
            enabled3 = enabled4;
            keyboardActions2 = keyboardActions3;
            function13 = function29;
            interactionSource2 = interactionSource5;
        } else {
            $composer2.skipToGroupEnd();
            function11 = function3;
            function12 = function4;
            function13 = function8;
            visualTransformation2 = visualTransformation;
            keyboardOptions2 = keyboardOptions;
            keyboardActions2 = keyboardActions;
            readOnly3 = singleLine;
            maxLines2 = maxLines;
            minLines2 = minLines;
            interactionSource2 = interactionSource;
            shape2 = shape;
            colors2 = colors;
            enabled3 = enabled2;
            readOnly4 = readOnly2;
            textStyle3 = textStyle2;
            function14 = function10;
            function15 = function5;
            function16 = function6;
            function17 = function7;
            isError2 = isError;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final Function2<? super Composer, ? super Integer, Unit> function32 = function12;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TextFieldKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldKt.TextField$lambda$8(value, function1, modifier4, enabled3, readOnly4, textStyle3, function14, function11, function32, function15, function16, function17, function13, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, readOnly3, maxLines2, minLines2, interactionSource2, shape2, colors2, $changed, $changed1, $changed2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:147:0x026b  */
    /* JADX WARN: Code duplicated, block: B:150:0x0277  */
    /* JADX WARN: Code duplicated, block: B:151:0x027d  */
    /* JADX WARN: Code duplicated, block: B:161:0x0309  */
    /* JADX WARN: Code duplicated, block: B:163:0x0384  */
    /* JADX WARN: Code duplicated, block: B:166:0x0390  */
    /* JADX WARN: Code duplicated, block: B:167:0x0396  */
    /* JADX WARN: Code duplicated, block: B:176:0x044e  */
    /* JADX WARN: Code duplicated, block: B:178:0x045f  */
    /* JADX WARN: Code duplicated, block: B:180:0x04d3  */
    /* JADX WARN: Code duplicated, block: B:183:0x04df  */
    /* JADX WARN: Code duplicated, block: B:184:0x04e5  */
    /* JADX WARN: Code duplicated, block: B:193:0x059f  */
    /* JADX WARN: Code duplicated, block: B:196:0x05b7  */
    /* JADX WARN: Code duplicated, block: B:197:0x05d2  */
    /* JADX WARN: Code duplicated, block: B:200:0x05d9  */
    /* JADX WARN: Code duplicated, block: B:201:0x05f4  */
    /* JADX WARN: Code duplicated, block: B:204:0x05fd  */
    /* JADX WARN: Code duplicated, block: B:206:0x068c  */
    /* JADX WARN: Code duplicated, block: B:209:0x0698  */
    /* JADX WARN: Code duplicated, block: B:210:0x069e  */
    /* JADX WARN: Code duplicated, block: B:219:0x0758  */
    /* JADX WARN: Code duplicated, block: B:221:0x0767  */
    /* JADX WARN: Code duplicated, block: B:223:0x07f6  */
    /* JADX WARN: Code duplicated, block: B:226:0x0802  */
    /* JADX WARN: Code duplicated, block: B:227:0x0808  */
    /* JADX WARN: Code duplicated, block: B:236:0x08be  */
    /* JADX WARN: Code duplicated, block: B:239:0x08cd  */
    /* JADX WARN: Code duplicated, block: B:240:0x08eb  */
    /* JADX WARN: Code duplicated, block: B:243:0x0903  */
    /* JADX WARN: Code duplicated, block: B:245:0x0925  */
    /* JADX WARN: Code duplicated, block: B:247:0x0929  */
    /* JADX WARN: Code duplicated, block: B:250:0x0934  */
    /* JADX WARN: Code duplicated, block: B:252:0x093a  */
    /* JADX WARN: Code duplicated, block: B:256:0x094a  */
    /* JADX WARN: Code duplicated, block: B:259:0x0955  */
    /* JADX WARN: Code duplicated, block: B:263:0x09d2  */
    /* JADX WARN: Code duplicated, block: B:266:0x09de  */
    /* JADX WARN: Code duplicated, block: B:267:0x09e4  */
    /* JADX WARN: Code duplicated, block: B:270:0x0a17  */
    /* JADX WARN: Code duplicated, block: B:273:0x0a2c A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:276:0x0aa0  */
    /* JADX WARN: Code duplicated, block: B:279:0x0ac8  */
    /* JADX WARN: Code duplicated, block: B:280:0x0acb  */
    /* JADX WARN: Code duplicated, block: B:283:0x0ad7  */
    /* JADX WARN: Code duplicated, block: B:284:0x0ada  */
    /* JADX WARN: Code duplicated, block: B:287:0x0af2  */
    /* JADX WARN: Code duplicated, block: B:288:0x0b1c  */
    /* JADX WARN: Code duplicated, block: B:291:0x0b91  */
    /* JADX WARN: Code duplicated, block: B:294:0x0b9d  */
    /* JADX WARN: Code duplicated, block: B:295:0x0ba3  */
    /* JADX WARN: Code duplicated, block: B:305:0x0c5b  */
    /* JADX WARN: Code duplicated, block: B:307:0x0cf1  */
    /* JADX WARN: Code duplicated, block: B:310:0x0cfd  */
    /* JADX WARN: Code duplicated, block: B:311:0x0d03  */
    /* JADX WARN: Code duplicated, block: B:314:0x0d34  */
    /* JADX WARN: Code duplicated, block: B:316:0x0d46  */
    /* JADX WARN: Code duplicated, block: B:317:0x0d49  */
    /* JADX WARN: Code duplicated, block: B:320:0x0dbd  */
    /* JADX WARN: Code duplicated, block: B:323:0x0dde  */
    public static final void TextFieldLayout(final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function3<? super Modifier, ? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final Function2<? super Composer, ? super Integer, Unit> function6, final Function2<? super Composer, ? super Integer, Unit> function7, final Function2<? super Composer, ? super Integer, Unit> function8, final boolean singleLine, final TextFieldLabelPosition labelPosition, FloatProducer labelProgress, final Function2<? super Composer, ? super Integer, Unit> function9, Function2<? super Composer, ? super Integer, Unit> function10, PaddingValues paddingValues, Composer $composer, final int $changed, final int $changed1) {
        int $dirty;
        Function2<? super Composer, ? super Integer, Unit> function11;
        final FloatProducer floatProducer;
        Composer $composer2;
        PaddingValues paddingValues2;
        int $dirty1;
        Object value$iv;
        int compositeKeyHash$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        Composer $this$Layout_u24lambda_u240$iv;
        float minimumValue$iv;
        float endTextFieldPadding;
        float horizontalIconPadding;
        float startPadding;
        float fM7905constructorimpl;
        float endPadding;
        Modifier labelPadding;
        float fM7905constructorimpl2;
        float fM7905constructorimpl3;
        Modifier textPadding;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function1;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        int compositeKeyHash$iv$iv2;
        Function0<ComposeUiNode> constructor3;
        Function0<ComposeUiNode> function12;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        int $changed$iv;
        Composer $composer3;
        boolean invalid$iv;
        Object value$iv2;
        int compositeKeyHash$iv$iv3;
        Function0<ComposeUiNode> constructor4;
        Function0<ComposeUiNode> function13;
        Composer $this$Layout_u24lambda_u240$iv$iv3;
        int compositeKeyHash$iv$iv4;
        Function0<ComposeUiNode> constructor5;
        Function0<ComposeUiNode> function14;
        Composer $this$Layout_u24lambda_u240$iv$iv4;
        int compositeKeyHash$iv$iv5;
        Function0<ComposeUiNode> constructor6;
        Function0<ComposeUiNode> function15;
        Composer $this$Layout_u24lambda_u240$iv$iv5;
        int compositeKeyHash$iv$iv6;
        Function0<ComposeUiNode> constructor7;
        Function0<ComposeUiNode> function16;
        Composer $this$Layout_u24lambda_u240$iv$iv6;
        int compositeKeyHash$iv$iv7;
        Function0<ComposeUiNode> constructor8;
        Function0<ComposeUiNode> function17;
        Composer $this$Layout_u24lambda_u240$iv$iv7;
        Composer $composer4 = $composer.startRestartGroup(-1086465551);
        ComposerKt.sourceInformation($composer4, "C(TextFieldLayout)N(modifier,textField,label,placeholder,leading,trailing,prefix,suffix,singleLine,labelPosition,labelProgress,container,supporting,paddingValues)672@35929L26,674@35988L475,689@36511L7,690@36523L4465:TextField.kt#uh7d8r");
        int $dirty2 = $changed1;
        if (($changed & 6) == 0) {
            $dirty = $changed | ($composer4.changed(modifier) ? 4 : 2);
        } else {
            $dirty = $changed;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer4.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer4.changedInstance(function3) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer4.changedInstance(function4) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer4.changedInstance(function5) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer4.changedInstance(function6) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer4.changedInstance(function7) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer4.changedInstance(function8) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty |= $composer4.changed(singleLine) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer4.changed(labelPosition) ? 536870912 : 268435456;
        }
        int $dirty3 = $dirty;
        int $dirty4 = $changed1 & 6;
        if ($dirty4 == 0) {
            $dirty2 |= ($changed1 & 8) == 0 ? $composer4.changed(labelProgress) : $composer4.changedInstance(labelProgress) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty2 |= $composer4.changedInstance(function9) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty2 |= $composer4.changedInstance(function10) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty2 |= $composer4.changed(paddingValues) ? 2048 : 1024;
        }
        int $dirty5 = $dirty2;
        if (!$composer4.shouldExecute((($dirty3 & 306783379) == 306783378 && ($dirty5 & 1171) == 1170) ? false : true, $dirty3 & 1)) {
            function11 = function10;
            floatProducer = labelProgress;
            $composer2 = $composer4;
            paddingValues2 = paddingValues;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1086465551, $dirty3, $dirty5, "androidx.compose.material3.TextFieldLayout (TextField.kt:671)");
            }
            float minimizedLabelHalfHeight = TextFieldImplKt.minimizedLabelHalfHeight($composer4, 0);
            ComposerKt.sourceInformationMarkerStart($composer4, -416319668, "CC(remember):TextField.kt#9igjgp");
            boolean invalid$iv2 = ((234881024 & $dirty3) == 67108864) | ((1879048192 & $dirty3) == 536870912) | (($dirty5 & 14) == 4 || (($dirty5 & 8) != 0 && $composer4.changed(labelProgress))) | (($dirty5 & 7168) == 2048) | $composer4.changed(minimizedLabelHalfHeight);
            Object it$iv = $composer4.rememberedValue();
            if (!invalid$iv2) {
                $dirty1 = $dirty5;
                if (it$iv != Composer.INSTANCE.getEmpty()) {
                    value$iv = it$iv;
                    paddingValues2 = paddingValues;
                }
                TextFieldMeasurePolicy measurePolicy = (TextFieldMeasurePolicy) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer4.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                LayoutDirection layoutDirection = (LayoutDirection) objConsume;
                int $changed$iv2 = ($dirty3 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                CompositionLocalMap localMap$iv = $composer4.getCurrentCompositionLocalMap();
                Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer4, modifier);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv = (($changed$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer4.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer4.startReusableNode();
                if ($composer4.getInserting()) {
                    function0 = constructor;
                    $composer4.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer4.useNode();
                }
                $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer4);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                    $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                    $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer4, -1445107871, "C696@36842L11,718@37662L32,787@40302L187:TextField.kt#uh7d8r");
                function9.invoke($composer4, Integer.valueOf(($dirty1 >> 3) & 14));
                if (function5 != null) {
                    $composer4.startReplaceGroup(-1445181094);
                    ComposerKt.sourceInformation($composer4, "699@36906L224");
                    Modifier modifier$iv = InteractiveComponentSizeKt.minimumInteractiveComponentSize(LayoutIdKt.layoutId(Modifier.INSTANCE, "Leading"));
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                    int $changed$iv$iv2 = (54 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv7 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
                    constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer4.startReusableNode();
                    if ($composer4.getInserting()) {
                        function17 = constructor8;
                        $composer4.createNode(function17);
                    } else {
                        function17 = constructor8;
                        $composer4.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv7 = Updater.m4367constructorimpl($composer4);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv7, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv7, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv7.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv7.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv7))) {
                        $this$Layout_u24lambda_u240$iv$iv7.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv7));
                        $this$Layout_u24lambda_u240$iv$iv7.apply(Integer.valueOf(compositeKeyHash$iv$iv7), setCompositeKeyHash2);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv7, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i2 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i3 = ((54 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -130017935, "C703@37103L9:TextField.kt#uh7d8r");
                    function5.invoke($composer4, Integer.valueOf(($dirty3 >> 12) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endReplaceGroup();
                } else {
                    $composer4.startReplaceGroup(-1444935078);
                    $composer4.endReplaceGroup();
                }
                if (function6 != null) {
                    $composer4.startReplaceGroup(-1444892360);
                    ComposerKt.sourceInformation($composer4, "707@37197L226");
                    Modifier modifier$iv2 = InteractiveComponentSizeKt.minimumInteractiveComponentSize(LayoutIdKt.layoutId(Modifier.INSTANCE, "Trailing"));
                    Alignment contentAlignment$iv2 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                    int $changed$iv$iv3 = (54 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv6 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv2 = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer4, modifier$iv2);
                    constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer4.startReusableNode();
                    if ($composer4.getInserting()) {
                        function16 = constructor7;
                        $composer4.createNode(function16);
                    } else {
                        function16 = constructor7;
                        $composer4.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv6 = Updater.m4367constructorimpl($composer4);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv6, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv6, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv6.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv6.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv6))) {
                        $this$Layout_u24lambda_u240$iv$iv6.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv6));
                        $this$Layout_u24lambda_u240$iv$iv6.apply(Integer.valueOf(compositeKeyHash$iv$iv6), setCompositeKeyHash3);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv6, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i5 = ((54 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, 385425337, "C711@37395L10:TextField.kt#uh7d8r");
                    function6.invoke($composer4, Integer.valueOf(($dirty3 >> 15) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endReplaceGroup();
                } else {
                    $composer4.startReplaceGroup(-1444644422);
                    $composer4.endReplaceGroup();
                }
                minimumValue$iv = PaddingKt.calculateStartPadding(paddingValues2, layoutDirection);
                endTextFieldPadding = PaddingKt.calculateEndPadding(paddingValues2, layoutDirection);
                horizontalIconPadding = TextFieldImplKt.textFieldHorizontalIconPadding($composer4, 0);
                if (function5 != null) {
                    float $this$coerceAtLeast_u2dYgX7TsA$iv = Dp.m7905constructorimpl(minimumValue$iv - horizontalIconPadding);
                    float startTextFieldPadding = 0;
                    minimumValue$iv = Dp.m7905constructorimpl(RangesKt.coerceAtLeast($this$coerceAtLeast_u2dYgX7TsA$iv, Dp.m7905constructorimpl(startTextFieldPadding)));
                }
                startPadding = minimumValue$iv;
                if (function6 != null) {
                    fM7905constructorimpl = endTextFieldPadding;
                } else {
                    float $this$coerceAtLeast_u2dYgX7TsA$iv2 = Dp.m7905constructorimpl(endTextFieldPadding - horizontalIconPadding);
                    fM7905constructorimpl = Dp.m7905constructorimpl(RangesKt.coerceAtLeast($this$coerceAtLeast_u2dYgX7TsA$iv2, Dp.m7905constructorimpl(0)));
                }
                endPadding = fM7905constructorimpl;
                if (function7 != null) {
                    $composer4.startReplaceGroup(-1443868027);
                    ComposerKt.sourceInformation($composer4, "733@38227L309");
                    Modifier modifier$iv3 = PaddingKt.m823paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m853heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.PrefixId), TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null), startPadding, 0.0f, TextFieldImplKt.getPrefixSuffixTextPadding(), 0.0f, 10, null);
                    ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                    int $changed$iv$iv4 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv5 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv3 = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer4, modifier$iv3);
                    constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv3 = (($changed$iv$iv4 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer4.startReusableNode();
                    if ($composer4.getInserting()) {
                        function15 = constructor6;
                        $composer4.createNode(function15);
                    } else {
                        function15 = constructor6;
                        $composer4.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv5 = Updater.m4367constructorimpl($composer4);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv5, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv5, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv5.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv5.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv5))) {
                        $this$Layout_u24lambda_u240$iv$iv5.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv5));
                        $this$Layout_u24lambda_u240$iv$iv5.apply(Integer.valueOf(compositeKeyHash$iv$iv5), setCompositeKeyHash4);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv5, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i6 = ($changed$iv$iv$iv3 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    int i7 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1325247206, "C739@38510L8:TextField.kt#uh7d8r");
                    function7.invoke($composer4, Integer.valueOf(($dirty3 >> 18) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endReplaceGroup();
                } else {
                    $composer4.startReplaceGroup(-1443540326);
                    $composer4.endReplaceGroup();
                }
                if (function8 != null) {
                    $composer4.startReplaceGroup(-1443497081);
                    ComposerKt.sourceInformation($composer4, "743@38601L307");
                    Modifier modifier$iv4 = PaddingKt.m823paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m853heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.SuffixId), TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null), TextFieldImplKt.getPrefixSuffixTextPadding(), 0.0f, endPadding, 0.0f, 10, null);
                    ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
                    int $changed$iv$iv5 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv4 = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer4, modifier$iv4);
                    constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv4 = (($changed$iv$iv5 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer4.startReusableNode();
                    if ($composer4.getInserting()) {
                        function14 = constructor5;
                        $composer4.createNode(function14);
                    } else {
                        function14 = constructor5;
                        $composer4.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4367constructorimpl($composer4);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                        $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                        $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash5);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                    int i8 = ($changed$iv$iv$iv4 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                    int i9 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, 1259047609, "C749@38882L8:TextField.kt#uh7d8r");
                    function8.invoke($composer4, Integer.valueOf(($dirty3 >> 21) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endReplaceGroup();
                } else {
                    $composer4.startReplaceGroup(-1443171302);
                    $composer4.endReplaceGroup();
                }
                if (labelPosition instanceof TextFieldLabelPosition.Above) {
                    labelPadding = PaddingKt.m823paddingqDBjuR0$default(Modifier.INSTANCE, TextFieldImplKt.getAboveLabelHorizontalPadding(), 0.0f, TextFieldImplKt.getAboveLabelHorizontalPadding(), TextFieldImplKt.getAboveLabelBottomPadding(), 2, null);
                } else {
                    labelPadding = PaddingKt.m823paddingqDBjuR0$default(Modifier.INSTANCE, startPadding, 0.0f, endPadding, 0.0f, 10, null);
                }
                if (function3 != null) {
                    $composer4.startReplaceGroup(-1442671489);
                    ComposerKt.sourceInformation($composer4, "766@39532L123,764@39431L379");
                    Modifier modifierLayoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "Label");
                    ComposerKt.sourceInformationMarkerStart($composer4, 1061844387, "CC(remember):TextField.kt#9igjgp");
                    if (($dirty1 & 14) != 4) {
                        if (($dirty1 & 8) != 0) {
                            floatProducer = labelProgress;
                            $composer3 = $composer4;
                            if ($composer3.changedInstance(floatProducer)) {
                            }
                            value$iv2 = $composer4.rememberedValue();
                            if (!invalid$iv) {
                                $composer2 = $composer3;
                                if (value$iv2 == Composer.INSTANCE.getEmpty()) {
                                }
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                Modifier modifier$iv5 = SizeKt.wrapContentHeight$default(TextFieldImplKt.textFieldLabelMinHeight(modifierLayoutId, (Function0) value$iv2), null, false, 3, null).then(labelPadding);
                                ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                                Alignment contentAlignment$iv5 = Alignment.INSTANCE.getTopStart();
                                MeasurePolicy measurePolicy$iv5 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv5, false);
                                int $changed$iv$iv6 = (0 << 3) & 112;
                                ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                                CompositionLocalMap localMap$iv$iv5 = $composer4.getCurrentCompositionLocalMap();
                                Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer4, modifier$iv5);
                                constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                                int $changed$iv$iv$iv5 = (($changed$iv$iv6 << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                                if (!($composer4.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer4.startReusableNode();
                                if ($composer4.getInserting()) {
                                    function13 = constructor4;
                                    $composer4.createNode(function13);
                                } else {
                                    function13 = constructor4;
                                    $composer4.useNode();
                                }
                                $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer4);
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash6);
                                }
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
                                int i10 = ($changed$iv$iv$iv5 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                                int i11 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer4, -451624903, "C772@39785L7:TextField.kt#uh7d8r");
                                function3.invoke($composer4, Integer.valueOf(($dirty3 >> 6) & 14));
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                $composer4.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                $composer4.endReplaceGroup();
                            } else {
                                $composer2 = $composer3;
                            }
                            value$iv2 = new Function0() { // from class: androidx.compose.material3.TextFieldKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Dp.m7903boximpl(DpKt.m7948lerpMdfbLM(TextFieldImplKt.getMinTextLineHeight(), TextFieldImplKt.getMinFocusedLabelLineHeight(), floatProducer.invoke()));
                                }
                            };
                            $composer4.updateRememberedValue(value$iv2);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            Modifier modifier$iv6 = SizeKt.wrapContentHeight$default(TextFieldImplKt.textFieldLabelMinHeight(modifierLayoutId, (Function0) value$iv2), null, false, 3, null).then(labelPadding);
                            ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv6 = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv6 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv6, false);
                            int $changed$iv$iv7 = (0 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                            CompositionLocalMap localMap$iv$iv6 = $composer4.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv6 = ComposedModifierKt.materializeModifier($composer4, modifier$iv6);
                            constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv6 = (($changed$iv$iv7 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if ($composer4.getInserting()) {
                                function13 = constructor4;
                                $composer4.createNode(function13);
                            } else {
                                function13 = constructor4;
                                $composer4.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer4);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash7);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv6, ComposeUiNode.INSTANCE.getSetModifier());
                            int i12 = ($changed$iv$iv$iv6 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                            int i13 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -451624903, "C772@39785L7:TextField.kt#uh7d8r");
                            function3.invoke($composer4, Integer.valueOf(($dirty3 >> 6) & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceGroup();
                        } else {
                            floatProducer = labelProgress;
                            $composer3 = 
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0936: MOVE (r6v32 '$composer3' androidx.compose.runtime.Composer) = (r12v6 androidx.compose.runtime.Composer A[D('$changed$iv' int)]) in method: androidx.compose.material3.TextFieldKt.TextFieldLayout(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function3<? super androidx.compose.ui.Modifier, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, boolean, androidx.compose.material3.TextFieldLabelPosition, androidx.compose.material3.internal.FloatProducer, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int, int):void, file: classes10.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:140)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:140)
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
                                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r12v6 androidx.compose.runtime.Composer
                                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                                */
                            /*
                                Method dump skipped, instruction units count: 3620
                                To view this dump change 'Code comments level' option to 'DEBUG'
                            */
                            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldKt.TextFieldLayout(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, androidx.compose.material3.TextFieldLabelPosition, androidx.compose.material3.internal.FloatProducer, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int, int):void");
                        }

                        public static final float getTextFieldWithLabelVerticalPadding() {
                            return TextFieldWithLabelVerticalPadding;
                        }
                    }
