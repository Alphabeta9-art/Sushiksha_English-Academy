package androidx.compose.material;

import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
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
import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.foundation.text.input.TextFieldDecorator;
import androidx.compose.foundation.text.input.TextFieldLineLimits;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
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
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.profileinstaller.ProfileVerifier;
import com.cloudinary.metadata.MetadataField;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
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
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ö\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0085\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#H\u0007¢\u0006\u0002\u0010$\u001a\u0093\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00010(2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020\u00072\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u00100\u001a\u00020/2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0007¢\u0006\u0002\u00101\u001a\u0087\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00010(2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020\u00072\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0007¢\u0006\u0002\u00102\u001a\u0093\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010%\u001a\u0002032\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u00010(2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020\u00072\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u00100\u001a\u00020/2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0007¢\u0006\u0002\u00104\u001a\u0087\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010%\u001a\u0002032\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u00010(2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020\u00072\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0007¢\u0006\u0002\u00105\u001aÁ\u0001\u00106\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0011\u00107\u001a\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\r2\u0019\u0010\u000e\u001a\u0015\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010(¢\u0006\u0002\b\r2\u0013\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0013\u00108\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0013\u00109\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0006\u0010-\u001a\u00020\u00072\u0006\u0010:\u001a\u00020;2\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020\u00010(2\u0011\u0010>\u001a\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\r2\u0006\u0010?\u001a\u00020@H\u0001¢\u0006\u0002\u0010A\u001aW\u0010B\u001a\u00020/2\u0006\u0010C\u001a\u00020/2\u0006\u0010D\u001a\u00020/2\u0006\u0010E\u001a\u00020/2\u0006\u0010F\u001a\u00020/2\u0006\u0010G\u001a\u00020/2\u0006\u0010:\u001a\u00020;2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020;2\u0006\u0010?\u001a\u00020@H\u0002¢\u0006\u0004\bK\u0010L\u001aW\u0010M\u001a\u00020/2\u0006\u0010N\u001a\u00020/2\u0006\u0010O\u001a\u00020/2\u0006\u0010P\u001a\u00020/2\u0006\u0010Q\u001a\u00020/2\u0006\u0010R\u001a\u00020/2\u0006\u0010:\u001a\u00020;2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020;2\u0006\u0010?\u001a\u00020@H\u0002¢\u0006\u0004\bS\u0010L\u001a|\u0010T\u001a\u00020\u0001*\u00020U2\u0006\u0010V\u001a\u00020/2\u0006\u0010W\u001a\u00020/2\b\u0010X\u001a\u0004\u0018\u00010Y2\b\u0010Z\u001a\u0004\u0018\u00010Y2\u0006\u0010[\u001a\u00020Y2\b\u0010\\\u001a\u0004\u0018\u00010Y2\b\u0010]\u001a\u0004\u0018\u00010Y2\u0006\u0010^\u001a\u00020Y2\u0006\u0010:\u001a\u00020;2\u0006\u0010-\u001a\u00020\u00072\u0006\u0010J\u001a\u00020;2\u0006\u0010_\u001a\u00020`2\u0006\u0010?\u001a\u00020@H\u0002\u001a#\u0010a\u001a\u00020\u0005*\u00020\u00052\u0006\u0010b\u001a\u00020=2\u0006\u0010?\u001a\u00020@H\u0000¢\u0006\u0004\bc\u0010d\"\u0010\u0010e\u001a\u00020fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010g\"\u0016\u0010h\u001a\u00020iX\u0080\u0004¢\u0006\n\n\u0002\u0010l\u001a\u0004\bj\u0010k\"\u000e\u0010m\u001a\u00020&X\u0080T¢\u0006\u0002\n\u0000¨\u0006n"}, d2 = {"OutlinedTextField", "", "state", "Landroidx/compose/foundation/text/input/TextFieldState;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "readOnly", "textStyle", "Landroidx/compose/ui/text/TextStyle;", MetadataField.LABEL, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "placeholder", "leadingIcon", "trailingIcon", "isError", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "outputTransformation", "Landroidx/compose/foundation/text/input/OutputTransformation;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "onKeyboardAction", "Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "lineLimits", "Landroidx/compose/foundation/text/input/TextFieldLineLimits;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material/TextFieldColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/foundation/text/input/OutputTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Landroidx/compose/foundation/text/input/TextFieldLineLimits;Landroidx/compose/foundation/ScrollState;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "value", "", "onValueChange", "Lkotlin/Function1;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "singleLine", "maxLines", "", "minLines", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/runtime/Composer;III)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/ui/text/input/TextFieldValue;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/runtime/Composer;III)V", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/runtime/Composer;III)V", "OutlinedTextFieldLayout", "textField", "leading", "trailing", "animationProgress", "", "onLabelMeasured", "Landroidx/compose/ui/geometry/Size;", OutlinedTextFieldKt.BorderId, "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZFLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;II)V", "calculateWidth", "leadingPlaceableWidth", "trailingPlaceableWidth", "textFieldPlaceableWidth", "labelPlaceableWidth", "placeholderPlaceableWidth", "constraints", "Landroidx/compose/ui/unit/Constraints;", "density", "calculateWidth-O3s9Psw", "(IIIIIFJFLandroidx/compose/foundation/layout/PaddingValues;)I", "calculateHeight", "leadingPlaceableHeight", "trailingPlaceableHeight", "textFieldPlaceableHeight", "labelPlaceableHeight", "placeholderPlaceableHeight", "calculateHeight-O3s9Psw", "place", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "height", "width", "leadingPlaceable", "Landroidx/compose/ui/layout/Placeable;", "trailingPlaceable", "textFieldPlaceable", "labelPlaceable", "placeholderPlaceable", "borderPlaceable", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "outlineCutout", "labelSize", "outlineCutout-12SF9DM", "(Landroidx/compose/ui/Modifier;JLandroidx/compose/foundation/layout/PaddingValues;)Landroidx/compose/ui/Modifier;", "OutlinedTextFieldInnerPadding", "Landroidx/compose/ui/unit/Dp;", "F", "OutlinedTextFieldTopPadding", "Landroidx/compose/ui/unit/TextUnit;", "getOutlinedTextFieldTopPadding", "()J", "J", "BorderId", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class OutlinedTextFieldKt {
    public static final String BorderId = "border";
    private static final float OutlinedTextFieldInnerPadding = Dp.m7905constructorimpl(4);
    private static final long OutlinedTextFieldTopPadding = TextUnitKt.getSp(8);

    /* JADX INFO: compiled from: OutlinedTextField.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutDirection.values().length];
            try {
                iArr[LayoutDirection.Rtl.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit OutlinedTextField$lambda$10(String str, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i3, int i4, int i5, Composer composer, int i6) {
        OutlinedTextField(str, (Function1<? super String, Unit>) function1, modifier, z, z2, textStyle, (Function2<? super Composer, ? super Integer, Unit>) function2, (Function2<? super Composer, ? super Integer, Unit>) function3, (Function2<? super Composer, ? super Integer, Unit>) function4, (Function2<? super Composer, ? super Integer, Unit>) function5, z3, visualTransformation, keyboardOptions, keyboardActions, z4, i, i2, mutableInteractionSource, shape, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
        return Unit.INSTANCE;
    }

    static final Unit OutlinedTextField$lambda$12(String str, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i2, int i3, int i4, Composer composer, int i5) {
        OutlinedTextField(str, function1, modifier, z, z2, textStyle, function2, function3, function4, function5, z3, visualTransformation, keyboardOptions, keyboardActions, z4, i, mutableInteractionSource, shape, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit OutlinedTextField$lambda$18(TextFieldValue textFieldValue, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i3, int i4, int i5, Composer composer, int i6) {
        OutlinedTextField(textFieldValue, (Function1<? super TextFieldValue, Unit>) function1, modifier, z, z2, textStyle, (Function2<? super Composer, ? super Integer, Unit>) function2, (Function2<? super Composer, ? super Integer, Unit>) function3, (Function2<? super Composer, ? super Integer, Unit>) function4, (Function2<? super Composer, ? super Integer, Unit>) function5, z3, visualTransformation, keyboardOptions, keyboardActions, z4, i, i2, mutableInteractionSource, shape, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
        return Unit.INSTANCE;
    }

    static final Unit OutlinedTextField$lambda$20(TextFieldValue textFieldValue, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i2, int i3, int i4, Composer composer, int i5) {
        OutlinedTextField(textFieldValue, function1, modifier, z, z2, textStyle, function2, function3, function4, function5, z3, visualTransformation, keyboardOptions, keyboardActions, z4, i, mutableInteractionSource, shape, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit OutlinedTextField$lambda$4(TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, boolean z3, InputTransformation inputTransformation, OutputTransformation outputTransformation, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, TextFieldLineLimits textFieldLineLimits, ScrollState scrollState, Shape shape, TextFieldColors textFieldColors, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        OutlinedTextField(textFieldState, modifier, z, z2, textStyle, (Function2<? super Composer, ? super Integer, Unit>) function2, (Function2<? super Composer, ? super Integer, Unit>) function3, (Function2<? super Composer, ? super Integer, Unit>) function4, (Function2<? super Composer, ? super Integer, Unit>) function5, z3, inputTransformation, outputTransformation, keyboardOptions, keyboardActionHandler, textFieldLineLimits, scrollState, shape, textFieldColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit OutlinedTextFieldLayout$lambda$2(Modifier modifier, Function2 function2, Function3 function3, Function2 function4, Function2 function5, Function2 function6, boolean z, float f, Function1 function1, Function2 function7, PaddingValues paddingValues, int i, int i2, Composer composer, int i3) {
        OutlinedTextFieldLayout(modifier, function2, function3, function4, function5, function6, z, f, function1, function7, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    public static final void OutlinedTextField(final TextFieldState state, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, boolean isError, InputTransformation inputTransformation, OutputTransformation outputTransformation, KeyboardOptions keyboardOptions, KeyboardActionHandler onKeyboardAction, TextFieldLineLimits lineLimits, ScrollState scrollState, Shape shape, TextFieldColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        boolean enabled2;
        boolean z;
        TextStyle textStyle2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        int i2;
        int i3;
        int $dirty1;
        Composer $composer2;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final KeyboardOptions keyboardOptions2;
        final TextFieldLineLimits lineLimits2;
        final ScrollState scrollState2;
        final TextFieldColors colors2;
        final MutableInteractionSource interactionSource2;
        final boolean enabled3;
        final boolean readOnly2;
        final TextStyle textStyle3;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final boolean isError2;
        final InputTransformation inputTransformation2;
        final OutputTransformation outputTransformation2;
        final KeyboardActionHandler onKeyboardAction2;
        final Shape shape2;
        Modifier.Companion modifier4;
        boolean enabled4;
        boolean readOnly3;
        TextStyle textStyle4;
        int $dirty;
        Function2<? super Composer, ? super Integer, Unit> function12;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        Function2<? super Composer, ? super Integer, Unit> function15;
        boolean isError3;
        InputTransformation inputTransformation3;
        OutputTransformation outputTransformation3;
        KeyboardOptions keyboardOptions3;
        KeyboardActionHandler onKeyboardAction3;
        TextFieldLineLimits lineLimits3;
        ScrollState scrollState3;
        Shape shape3;
        int $dirty2;
        Composer $composer3;
        TextFieldColors colors3;
        MutableInteractionSource interactionSource3;
        int $dirty3;
        boolean isError4;
        int $dirty4;
        MutableInteractionSource interactionSource4;
        Modifier.Companion companionM823paddingqDBjuR0$default;
        int $dirty5;
        int i4;
        Composer $composer4 = $composer.startRestartGroup(1708163690);
        ComposerKt.sourceInformation($composer4, "C(OutlinedTextField)N(state,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,isError,inputTransformation,outputTransformation,keyboardOptions,onKeyboardAction,lineLimits,scrollState,shape,colors,interactionSource)185@10504L7,202@11180L38,210@11525L20,187@10517L2817:OutlinedTextField.kt#jmzs0o");
        int $dirty6 = $changed;
        int $dirty7 = $changed1;
        if (($changed & 6) == 0) {
            $dirty6 |= $composer4.changed(state) ? 4 : 2;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty6 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer4.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty6 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty6 |= $composer4.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty6 |= 3072;
            z = readOnly;
        } else if (($changed & 3072) == 0) {
            z = readOnly;
            $dirty6 |= $composer4.changed(z) ? 2048 : 1024;
        } else {
            z = readOnly;
        }
        int i8 = 8192;
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                textStyle2 = textStyle;
                if ($composer4.changed(textStyle2)) {
                    i4 = 16384;
                }
                $dirty6 |= i4;
            } else {
                textStyle2 = textStyle;
            }
            i4 = 8192;
            $dirty6 |= i4;
        } else {
            textStyle2 = textStyle;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function6 = function2;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function6 = function2;
            $dirty6 |= $composer4.changedInstance(function6) ? 131072 : 65536;
        } else {
            function6 = function2;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty6 |= 1572864;
            function7 = function3;
        } else if (($changed & 1572864) == 0) {
            function7 = function3;
            $dirty6 |= $composer4.changedInstance(function7) ? 1048576 : 524288;
        } else {
            function7 = function3;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty6 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty6 |= $composer4.changedInstance(function4) ? 8388608 : 4194304;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty6 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty6 |= $composer4.changedInstance(function5) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i13 = i & 512;
        if (i13 != 0) {
            $dirty6 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty6 |= $composer4.changed(isError) ? 536870912 : 268435456;
        }
        int i14 = i & 1024;
        if (i14 != 0) {
            $dirty7 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty7 |= $composer4.changed(inputTransformation) ? 4 : 2;
        }
        int i15 = i & 2048;
        if (i15 != 0) {
            $dirty7 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty7 |= $composer4.changed(outputTransformation) ? 32 : 16;
        }
        int i16 = i & 4096;
        if (i16 != 0) {
            $dirty7 |= 384;
            i2 = i16;
        } else {
            i2 = i16;
            if (($changed1 & 384) == 0) {
                $dirty7 |= $composer4.changed(keyboardOptions) ? 256 : 128;
            }
        }
        int i17 = i & 8192;
        if (i17 != 0) {
            $dirty7 |= 3072;
            i3 = i17;
        } else {
            i3 = i17;
            if (($changed1 & 3072) == 0) {
                $dirty7 |= $composer4.changed(onKeyboardAction) ? 2048 : 1024;
            }
        }
        if (($changed1 & 24576) == 0) {
            if ((i & 16384) == 0 && $composer4.changed(lineLimits)) {
                i8 = 16384;
            }
            $dirty7 |= i8;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty7 |= ((i & 32768) == 0 && $composer4.changed(scrollState)) ? 131072 : 65536;
        }
        if (($changed1 & 1572864) == 0) {
            $dirty7 |= ((i & 65536) == 0 && $composer4.changed(shape)) ? 1048576 : 524288;
        }
        if (($changed1 & 12582912) == 0) {
            if ((i & 131072) == 0) {
                $dirty5 = $dirty7;
                int i18 = $composer4.changed(colors) ? 8388608 : 4194304;
                $dirty1 = $dirty5 | i18;
            } else {
                $dirty5 = $dirty7;
            }
            $dirty1 = $dirty5 | i18;
        } else {
            $dirty1 = $dirty7;
        }
        int i19 = i & 262144;
        if (i19 != 0) {
            $dirty1 |= 100663296;
        } else if (($changed1 & 100663296) == 0) {
            $dirty1 |= $composer4.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ($composer4.shouldExecute((($dirty6 & 306783379) == 306783378 && ($dirty1 & 38347923) == 38347922) ? false : true, $dirty6 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "163@9330L7,174@9893L21,175@9953L22,176@10025L25");
            if (($changed & 1) != 0 && !$composer4.getDefaultsInvalid()) {
                $composer4.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty6 &= -57345;
                }
                if ((i & 16384) != 0) {
                    $dirty1 &= -57345;
                }
                if ((32768 & i) != 0) {
                    $dirty1 &= -458753;
                }
                if ((i & 65536) != 0) {
                    $dirty1 &= -3670017;
                }
                if ((i & 131072) != 0) {
                    $dirty1 &= -29360129;
                }
                function14 = function4;
                function15 = function5;
                isError4 = isError;
                inputTransformation3 = inputTransformation;
                outputTransformation3 = outputTransformation;
                keyboardOptions3 = keyboardOptions;
                onKeyboardAction3 = onKeyboardAction;
                lineLimits3 = lineLimits;
                scrollState3 = scrollState;
                shape3 = shape;
                $composer3 = $composer4;
                $dirty3 = $dirty6;
                readOnly3 = z;
                function12 = function6;
                function13 = function7;
                $dirty4 = $dirty1;
                colors3 = colors;
                interactionSource3 = interactionSource;
            } else {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i6 == 0) {
                    enabled4 = enabled2;
                } else {
                    enabled4 = true;
                }
                if (i7 == 0) {
                    readOnly3 = z;
                } else {
                    readOnly3 = false;
                }
                if ((i & 16) == 0) {
                    textStyle4 = textStyle2;
                    $dirty = $dirty6;
                } else {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer4.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    textStyle4 = (TextStyle) objConsume;
                    $dirty = $dirty6 & (-57345);
                }
                if (i9 != 0) {
                    function12 = null;
                } else {
                    function12 = function6;
                }
                if (i10 == 0) {
                    function13 = function7;
                } else {
                    function13 = null;
                }
                if (i11 == 0) {
                    function14 = function4;
                } else {
                    function14 = null;
                }
                if (i12 == 0) {
                    function15 = function5;
                } else {
                    function15 = null;
                }
                if (i13 == 0) {
                    isError3 = isError;
                } else {
                    isError3 = false;
                }
                if (i14 == 0) {
                    inputTransformation3 = inputTransformation;
                } else {
                    inputTransformation3 = null;
                }
                if (i15 == 0) {
                    outputTransformation3 = outputTransformation;
                } else {
                    outputTransformation3 = null;
                }
                if (i2 == 0) {
                    keyboardOptions3 = keyboardOptions;
                } else {
                    keyboardOptions3 = KeyboardOptions.INSTANCE.getDefault();
                }
                if (i3 == 0) {
                    onKeyboardAction3 = onKeyboardAction;
                } else {
                    onKeyboardAction3 = null;
                }
                if ((i & 16384) == 0) {
                    lineLimits3 = lineLimits;
                } else {
                    $dirty1 &= -57345;
                    lineLimits3 = TextFieldLineLimits.INSTANCE.getDefault();
                }
                if ((32768 & i) == 0) {
                    scrollState3 = scrollState;
                } else {
                    $dirty1 &= -458753;
                    scrollState3 = ScrollKt.rememberScrollState(0, $composer4, 0, 1);
                }
                if ((i & 65536) == 0) {
                    shape3 = shape;
                    $dirty2 = $dirty1;
                } else {
                    shape3 = TextFieldDefaults.INSTANCE.getOutlinedTextFieldShape($composer4, 6);
                    $dirty2 = $dirty1 & (-3670017);
                }
                if ((i & 131072) == 0) {
                    $composer3 = $composer4;
                    colors3 = colors;
                } else {
                    colors3 = TextFieldDefaults.INSTANCE.m1996outlinedTextFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer4, 0, 0, 48, 2097151);
                    $composer3 = $composer4;
                    $dirty2 &= -29360129;
                }
                if (i19 == 0) {
                    interactionSource3 = interactionSource;
                    modifier2 = modifier4;
                    textStyle2 = textStyle4;
                    enabled2 = enabled4;
                    $dirty3 = $dirty;
                    isError4 = isError3;
                    $dirty4 = $dirty2;
                } else {
                    interactionSource3 = null;
                    modifier2 = modifier4;
                    textStyle2 = textStyle4;
                    enabled2 = enabled4;
                    $dirty3 = $dirty;
                    isError4 = isError3;
                    $dirty4 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1708163690, $dirty3, $dirty4, "androidx.compose.material.OutlinedTextField (OutlinedTextField.kt:178)");
            }
            if (interactionSource3 == null) {
                $composer3.startReplaceGroup(1133009585);
                ComposerKt.sourceInformation($composer3, "180@10194L39");
                ComposerKt.sourceInformationMarkerStart($composer3, 867832689, "CC(remember):OutlinedTextField.kt#9igjgp");
                Composer $this$cache$iv = $composer3;
                Object it$iv = $this$cache$iv.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $this$cache$iv.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                interactionSource4 = (MutableInteractionSource) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(867832038);
                $composer3.endReplaceGroup();
                interactionSource4 = interactionSource3;
            }
            $composer3.startReplaceGroup(867837784);
            ComposerKt.sourceInformation($composer3, "*182@10373L18");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle2.m7358getColor0d7_KjU();
            if (($this$takeOrElse_u2dDxMtmZc$iv != 16 ? 1 : 0) == 0) {
                $this$takeOrElse_u2dDxMtmZc$iv = colors3.textColor(enabled2, $composer3, (($dirty3 >> 6) & 14) | (($dirty4 >> 18) & 112)).getValue().m5107unboximpl();
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle2.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            Composer $composer$iv = $composer3;
            ComposerKt.sourceInformationMarkerStart($composer$iv, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer$iv.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            Density density = (Density) objConsume2;
            if (function12 != null) {
                $composer3.startReplaceGroup(1133480847);
                ComposerKt.sourceInformation($composer3, "196@10930L2");
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 867856204, "CC(remember):OutlinedTextField.kt#9igjgp");
                Composer $this$cache$iv2 = $composer3;
                Object it$iv2 = $this$cache$iv2.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = new Function1() { // from class: androidx.compose.material.OutlinedTextFieldKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return OutlinedTextFieldKt.OutlinedTextField$lambda$2$0((SemanticsPropertyReceiver) obj);
                        }
                    };
                    $this$cache$iv2.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                companionM823paddingqDBjuR0$default = PaddingKt.m823paddingqDBjuR0$default(SemanticsModifierKt.semantics(companion, true, (Function1) it$iv2), 0.0f, density.mo401toDpGaN1DYA(OutlinedTextFieldTopPadding), 0.0f, 0.0f, 13, null);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(1133866208);
                $composer3.endReplaceGroup();
                companionM823paddingqDBjuR0$default = Modifier.INSTANCE;
            }
            boolean isError5 = isError4;
            Composer $composer5 = $composer3;
            BasicTextFieldKt.BasicTextField(state, SizeKt.m849defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifier2.then(companionM823paddingqDBjuR0$default), isError4, Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1947getDefaultErrorMessageUdPEhr4(), $composer3, 6)), TextFieldDefaults.INSTANCE.m1993getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m1992getMinHeightD9Ej5fM()), enabled2, readOnly3, inputTransformation3, mergedTextStyle, keyboardOptions3, onKeyboardAction3, lineLimits3, (Function2<? super Density, ? super Function0<TextLayoutResult>, Unit>) null, interactionSource4, new SolidColor(colors3.cursorColor(isError4, $composer3, (($dirty3 >> 27) & 14) | (($dirty4 >> 18) & 112)).getValue().m5107unboximpl(), null), outputTransformation3, new AnonymousClass3(outputTransformation3, state, lineLimits3, enabled2, interactionSource4, isError5, function12, function13, function14, function15, shape3, colors3), scrollState3, $composer5, ($dirty3 & 14) | ($dirty3 & 896) | ($dirty3 & 7168) | (($dirty4 << 12) & 57344) | (($dirty4 << 12) & 3670016) | (($dirty4 << 12) & 29360128) | (($dirty4 << 12) & 234881024), (($dirty4 << 3) & 896) | (($dirty4 >> 3) & 57344), 512);
            $composer2 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors2 = colors3;
            textStyle3 = textStyle2;
            enabled3 = enabled2;
            modifier3 = modifier2;
            interactionSource2 = interactionSource3;
            readOnly2 = readOnly3;
            function9 = function12;
            function10 = function13;
            function11 = function14;
            function8 = function15;
            isError2 = isError5;
            inputTransformation2 = inputTransformation3;
            outputTransformation2 = outputTransformation3;
            keyboardOptions2 = keyboardOptions3;
            onKeyboardAction2 = onKeyboardAction3;
            lineLimits2 = lineLimits3;
            scrollState2 = scrollState3;
            shape2 = shape3;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            function8 = function5;
            keyboardOptions2 = keyboardOptions;
            lineLimits2 = lineLimits;
            scrollState2 = scrollState;
            colors2 = colors;
            interactionSource2 = interactionSource;
            enabled3 = enabled2;
            readOnly2 = z;
            textStyle3 = textStyle2;
            function9 = function6;
            modifier3 = modifier2;
            function10 = function7;
            function11 = function4;
            isError2 = isError;
            inputTransformation2 = inputTransformation;
            outputTransformation2 = outputTransformation;
            onKeyboardAction2 = onKeyboardAction;
            shape2 = shape;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.OutlinedTextFieldKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldKt.OutlinedTextField$lambda$4(state, modifier3, enabled3, readOnly2, textStyle3, function9, function10, function11, function8, isError2, inputTransformation2, outputTransformation2, keyboardOptions2, onKeyboardAction2, lineLimits2, scrollState2, shape2, colors2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit OutlinedTextField$lambda$2$0(SemanticsPropertyReceiver $this$semantics) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material.OutlinedTextFieldKt$OutlinedTextField$3, reason: invalid class name */
    /* JADX INFO: compiled from: OutlinedTextField.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0004H\n"}, d2 = {"<anonymous>", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass3 implements TextFieldDecorator {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ Function2<Composer, Integer, Unit> $label;
        final /* synthetic */ Function2<Composer, Integer, Unit> $leadingIcon;
        final /* synthetic */ TextFieldLineLimits $lineLimits;
        final /* synthetic */ OutputTransformation $outputTransformation;
        final /* synthetic */ Function2<Composer, Integer, Unit> $placeholder;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ TextFieldState $state;
        final /* synthetic */ Function2<Composer, Integer, Unit> $trailingIcon;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(OutputTransformation outputTransformation, TextFieldState textFieldState, TextFieldLineLimits textFieldLineLimits, boolean z, MutableInteractionSource mutableInteractionSource, boolean z2, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Shape shape, TextFieldColors textFieldColors) {
            this.$outputTransformation = outputTransformation;
            this.$state = textFieldState;
            this.$lineLimits = textFieldLineLimits;
            this.$enabled = z;
            this.$interactionSource = mutableInteractionSource;
            this.$isError = z2;
            this.$label = function2;
            this.$placeholder = function3;
            this.$leadingIcon = function4;
            this.$trailingIcon = function5;
            this.$shape = shape;
            this.$colors = textFieldColors;
        }

        static final Unit Decoration$lambda$3(AnonymousClass3 anonymousClass3, Function2 function2, int i, Composer composer, int i2) {
            anonymousClass3.Decoration(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
            return Unit.INSTANCE;
        }

        @Override // androidx.compose.foundation.text.input.TextFieldDecorator
        public final void Decoration(Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
            Composer $composer2;
            TextFieldBuffer textFieldBuffer;
            String textPostTransformation;
            final Function2<? super Composer, ? super Integer, Unit> function3 = function2;
            Composer $composer3 = $composer.startRestartGroup(1251830469);
            ComposerKt.sourceInformation($composer3, "C(Decoration)N(innerTextField)246@13183L119,232@12557L760:OutlinedTextField.kt#jmzs0o");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer3.changedInstance(function3) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer3.changed(this) ? 32 : 16;
            }
            if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                $composer2 = $composer3;
                $composer2.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1251830469, $dirty, -1, "androidx.compose.material.OutlinedTextField.<no name provided>.Decoration (OutlinedTextField.kt:219)");
                }
                if (this.$outputTransformation == null) {
                    textPostTransformation = this.$state.getText().toString();
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
                        textPostTransformation = mutableValue$iv.asCharSequence().toString();
                    } catch (Throwable th) {
                        this_$iv.finishEditing();
                        throw th;
                    }
                }
                TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                boolean zAreEqual = Intrinsics.areEqual(this.$lineLimits, TextFieldLineLimits.SingleLine.INSTANCE);
                boolean z = this.$enabled;
                MutableInteractionSource mutableInteractionSource = this.$interactionSource;
                boolean z2 = this.$isError;
                Function2<Composer, Integer, Unit> function4 = this.$label;
                Function2<Composer, Integer, Unit> function5 = this.$placeholder;
                Function2<Composer, Integer, Unit> function6 = this.$leadingIcon;
                Function2<Composer, Integer, Unit> function7 = this.$trailingIcon;
                Shape shape = this.$shape;
                TextFieldColors textFieldColors = this.$colors;
                final boolean z3 = this.$enabled;
                String textPostTransformation2 = textPostTransformation;
                final boolean z4 = this.$isError;
                final MutableInteractionSource mutableInteractionSource2 = this.$interactionSource;
                final TextFieldColors textFieldColors2 = this.$colors;
                final Shape shape2 = this.$shape;
                function3 = function2;
                $composer2 = $composer3;
                textFieldDefaults.OutlinedTextFieldDecorationBox(textPostTransformation2, function3, z, zAreEqual, none, mutableInteractionSource, z2, function4, function5, function6, function7, shape, textFieldColors, null, ComposableLambdaKt.rememberComposableLambda(413830957, true, new Function2() { // from class: androidx.compose.material.OutlinedTextFieldKt$OutlinedTextField$3$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return OutlinedTextFieldKt.AnonymousClass3.Decoration$lambda$2(z3, z4, mutableInteractionSource2, textFieldColors2, shape2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer3, 54), $composer2, (($dirty << 3) & 112) | 24576, 221184, 8192);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.OutlinedTextFieldKt$OutlinedTextField$3$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return OutlinedTextFieldKt.AnonymousClass3.Decoration$lambda$3(this.f$0, function3, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        static final Unit Decoration$lambda$2(boolean $enabled, boolean $isError, MutableInteractionSource $interactionSource, TextFieldColors $colors, Shape $shape, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C247@13223L61:OutlinedTextField.kt#jmzs0o");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(413830957, $changed, -1, "androidx.compose.material.OutlinedTextField.<no name provided>.Decoration.<anonymous> (OutlinedTextField.kt:247)");
                }
                TextFieldDefaults.INSTANCE.m1990BorderBoxnbWgWpA($enabled, $isError, $interactionSource, $colors, $shape, 0.0f, 0.0f, $composer, 12582912, 96);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static final void OutlinedTextField(final String value, final Function1<? super String, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        boolean enabled2;
        boolean z;
        TextStyle textStyle2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        int i2;
        int i3;
        int i4;
        int $dirty1;
        Composer $composer2;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        final boolean isError2;
        final KeyboardOptions keyboardOptions2;
        final boolean singleLine2;
        final int maxLines2;
        final int minLines2;
        final MutableInteractionSource interactionSource2;
        final Shape shape2;
        final TextFieldColors colors2;
        final boolean enabled3;
        final boolean readOnly2;
        final TextStyle textStyle3;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final VisualTransformation visualTransformation2;
        final KeyboardActions keyboardActions2;
        Modifier.Companion modifier4;
        boolean enabled4;
        boolean readOnly3;
        TextStyle textStyle4;
        int $dirty;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        boolean isError3;
        VisualTransformation visualTransformation3;
        KeyboardOptions keyboardOptions3;
        KeyboardActions keyboardActions3;
        boolean singleLine3;
        int maxLines3;
        int minLines3;
        MutableInteractionSource interactionSource3;
        Shape shape3;
        int $dirty2;
        Composer $composer3;
        TextFieldColors colors3;
        boolean readOnly4;
        TextStyle textStyle5;
        int $dirty3;
        boolean isError4;
        VisualTransformation visualTransformation4;
        KeyboardOptions keyboardOptions4;
        KeyboardActions keyboardActions4;
        boolean singleLine4;
        int maxLines4;
        int minLines4;
        int $dirty4;
        MutableInteractionSource interactionSource4;
        Modifier.Companion companionM823paddingqDBjuR0$default;
        int $dirty5;
        Composer $composer4 = $composer.startRestartGroup(801000036);
        ComposerKt.sourceInformation($composer4, "C(OutlinedTextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,isError,visualTransformation,keyboardOptions,keyboardActions,singleLine,maxLines,minLines,interactionSource,shape,colors)346@19208L7,363@19884L38,372@20268L20,381@20622L1027,348@19221L2435:OutlinedTextField.kt#jmzs0o");
        int $dirty6 = $changed;
        int $dirty7 = $changed1;
        if (($changed & 6) == 0) {
            $dirty6 |= $composer4.changed(value) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty6 |= $composer4.changedInstance(function1) ? 32 : 16;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty6 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer4.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty6 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty6 |= $composer4.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty6 |= 24576;
            z = readOnly;
        } else if (($changed & 24576) == 0) {
            z = readOnly;
            $dirty6 |= $composer4.changed(z) ? 16384 : 8192;
        } else {
            z = readOnly;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                textStyle2 = textStyle;
                int i8 = $composer4.changed(textStyle2) ? 131072 : 65536;
                $dirty6 |= i8;
            } else {
                textStyle2 = textStyle;
            }
            $dirty6 |= i8;
        } else {
            textStyle2 = textStyle;
        }
        int i9 = i & 64;
        if (i9 != 0) {
            $dirty6 |= 1572864;
            function6 = function2;
        } else if (($changed & 1572864) == 0) {
            function6 = function2;
            $dirty6 |= $composer4.changedInstance(function6) ? 1048576 : 524288;
        } else {
            function6 = function2;
        }
        int i10 = i & 128;
        if (i10 != 0) {
            $dirty6 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty6 |= $composer4.changedInstance(function3) ? 8388608 : 4194304;
        }
        int i11 = i & 256;
        if (i11 != 0) {
            $dirty6 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty6 |= $composer4.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i12 = i & 512;
        if (i12 != 0) {
            $dirty6 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty6 |= $composer4.changedInstance(function5) ? 536870912 : 268435456;
        }
        int i13 = i & 1024;
        if (i13 != 0) {
            $dirty7 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty7 |= $composer4.changed(isError) ? 4 : 2;
        }
        int i14 = i & 2048;
        if (i14 != 0) {
            $dirty7 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty7 |= $composer4.changed(visualTransformation) ? 32 : 16;
        }
        int i15 = i & 4096;
        if (i15 != 0) {
            $dirty7 |= 384;
            i2 = i15;
        } else {
            i2 = i15;
            if (($changed1 & 384) == 0) {
                $dirty7 |= $composer4.changed(keyboardOptions) ? 256 : 128;
            }
        }
        int i16 = i & 8192;
        if (i16 != 0) {
            $dirty7 |= 3072;
            i3 = i16;
        } else {
            i3 = i16;
            if (($changed1 & 3072) == 0) {
                $dirty7 |= $composer4.changed(keyboardActions) ? 2048 : 1024;
            }
        }
        int i17 = i & 16384;
        if (i17 != 0) {
            $dirty7 |= 24576;
            i4 = i17;
        } else {
            i4 = i17;
            if (($changed1 & 24576) == 0) {
                $dirty7 |= $composer4.changed(singleLine) ? 16384 : 8192;
            }
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty7 |= ((i & 32768) == 0 && $composer4.changed(maxLines)) ? 131072 : 65536;
        }
        int i18 = i & 65536;
        if (i18 != 0) {
            $dirty7 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty7 |= $composer4.changed(minLines) ? 1048576 : 524288;
        }
        int i19 = i & 131072;
        if (i19 != 0) {
            $dirty7 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty7 |= $composer4.changed(interactionSource) ? 8388608 : 4194304;
        }
        if (($changed1 & 100663296) == 0) {
            $dirty7 |= ((i & 262144) == 0 && $composer4.changed(shape)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed1 & C.ENCODING_PCM_32BIT) == 0) {
            if ((i & 524288) == 0) {
                $dirty5 = $dirty7;
                int i20 = $composer4.changed(colors) ? 536870912 : 268435456;
                $dirty1 = $dirty5 | i20;
            } else {
                $dirty5 = $dirty7;
            }
            $dirty1 = $dirty5 | i20;
        } else {
            $dirty1 = $dirty7;
        }
        if ($composer4.shouldExecute((($dirty6 & 306783379) == 306783378 && ($dirty1 & 306783379) == 306783378) ? false : true, $dirty6 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "324@18064L7,337@18714L22,338@18786L25");
            if (($changed & 1) != 0 && !$composer4.getDefaultsInvalid()) {
                $composer4.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty6 &= -458753;
                }
                if ((32768 & i) != 0) {
                    $dirty1 &= -458753;
                }
                if ((262144 & i) != 0) {
                    $dirty1 &= -234881025;
                }
                if ((i & 524288) != 0) {
                    $dirty1 &= -1879048193;
                }
                function12 = function3;
                function13 = function4;
                function14 = function5;
                isError4 = isError;
                visualTransformation4 = visualTransformation;
                keyboardActions4 = keyboardActions;
                maxLines4 = maxLines;
                interactionSource3 = interactionSource;
                shape3 = shape;
                $composer3 = $composer4;
                $dirty3 = $dirty6;
                readOnly4 = z;
                textStyle5 = textStyle2;
                function11 = function6;
                modifier4 = modifier2;
                $dirty4 = $dirty1;
                keyboardOptions4 = keyboardOptions;
                singleLine4 = singleLine;
                minLines4 = minLines;
                colors3 = colors;
            } else {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i6 == 0) {
                    enabled4 = enabled2;
                } else {
                    enabled4 = true;
                }
                if (i7 == 0) {
                    readOnly3 = z;
                } else {
                    readOnly3 = false;
                }
                if ((i & 32) == 0) {
                    textStyle4 = textStyle2;
                    $dirty = $dirty6;
                } else {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer4.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    textStyle4 = (TextStyle) objConsume;
                    $dirty = $dirty6 & (-458753);
                }
                if (i9 != 0) {
                    function11 = null;
                } else {
                    function11 = function6;
                }
                if (i10 == 0) {
                    function12 = function3;
                } else {
                    function12 = null;
                }
                if (i11 == 0) {
                    function13 = function4;
                } else {
                    function13 = null;
                }
                if (i12 == 0) {
                    function14 = function5;
                } else {
                    function14 = null;
                }
                if (i13 == 0) {
                    isError3 = isError;
                } else {
                    isError3 = false;
                }
                if (i14 == 0) {
                    visualTransformation3 = visualTransformation;
                } else {
                    visualTransformation3 = VisualTransformation.INSTANCE.getNone();
                }
                if (i2 == 0) {
                    keyboardOptions3 = keyboardOptions;
                } else {
                    keyboardOptions3 = KeyboardOptions.INSTANCE.getDefault();
                }
                if (i3 == 0) {
                    keyboardActions3 = keyboardActions;
                } else {
                    keyboardActions3 = KeyboardActions.INSTANCE.getDefault();
                }
                if (i4 == 0) {
                    singleLine3 = singleLine;
                } else {
                    singleLine3 = false;
                }
                if ((32768 & i) == 0) {
                    maxLines3 = maxLines;
                } else {
                    $dirty1 &= -458753;
                    maxLines3 = singleLine3 ? 1 : Integer.MAX_VALUE;
                }
                if (i18 == 0) {
                    minLines3 = minLines;
                } else {
                    minLines3 = 1;
                }
                if (i19 == 0) {
                    interactionSource3 = interactionSource;
                } else {
                    interactionSource3 = null;
                }
                if ((262144 & i) == 0) {
                    shape3 = shape;
                    $dirty2 = $dirty1;
                } else {
                    shape3 = TextFieldDefaults.INSTANCE.getOutlinedTextFieldShape($composer4, 6);
                    $dirty2 = $dirty1 & (-234881025);
                }
                if ((i & 524288) == 0) {
                    $composer3 = $composer4;
                    colors3 = colors;
                    enabled2 = enabled4;
                    readOnly4 = readOnly3;
                    textStyle5 = textStyle4;
                    $dirty3 = $dirty;
                    isError4 = isError3;
                    visualTransformation4 = visualTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    keyboardActions4 = keyboardActions3;
                    singleLine4 = singleLine3;
                    maxLines4 = maxLines3;
                    minLines4 = minLines3;
                    $dirty4 = $dirty2;
                } else {
                    $composer3 = $composer4;
                    colors3 = TextFieldDefaults.INSTANCE.m1996outlinedTextFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer4, 0, 0, 48, 2097151);
                    enabled2 = enabled4;
                    readOnly4 = readOnly3;
                    textStyle5 = textStyle4;
                    $dirty3 = $dirty;
                    isError4 = isError3;
                    visualTransformation4 = visualTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    keyboardActions4 = keyboardActions3;
                    singleLine4 = singleLine3;
                    maxLines4 = maxLines3;
                    minLines4 = minLines3;
                    $dirty4 = $dirty2 & (-1879048193);
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(801000036, $dirty3, $dirty4, "androidx.compose.material.OutlinedTextField (OutlinedTextField.kt:339)");
            }
            if (interactionSource3 == null) {
                $composer3.startReplaceGroup(-245181289);
                ComposerKt.sourceInformation($composer3, "341@18898L39");
                ComposerKt.sourceInformationMarkerStart($composer3, 961922251, "CC(remember):OutlinedTextField.kt#9igjgp");
                Composer $this$cache$iv = $composer3;
                Object it$iv = $this$cache$iv.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $this$cache$iv.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
                interactionSource4 = (MutableInteractionSource) it$iv;
            } else {
                $composer3.startReplaceGroup(961921600);
                $composer3.endReplaceGroup();
                interactionSource4 = interactionSource3;
            }
            $composer3.startReplaceGroup(961927346);
            ComposerKt.sourceInformation($composer3, "*343@19077L18");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle5.m7358getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                $this$takeOrElse_u2dDxMtmZc$iv = colors3.textColor(enabled2, $composer3, (($dirty3 >> 9) & 14) | (($dirty4 >> 24) & 112)).getValue().m5107unboximpl();
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle5.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            Composer $composer$iv = $composer3;
            TextStyle textStyle6 = textStyle5;
            final boolean enabled5 = enabled2;
            ComposerKt.sourceInformationMarkerStart($composer$iv, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer$iv.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            Density density = (Density) objConsume2;
            if (function11 != null) {
                $composer3.startReplaceGroup(-244710027);
                ComposerKt.sourceInformation($composer3, "357@19634L2");
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 961945766, "CC(remember):OutlinedTextField.kt#9igjgp");
                Composer $this$cache$iv2 = $composer3;
                Object it$iv2 = $this$cache$iv2.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = new Function1() { // from class: androidx.compose.material.OutlinedTextFieldKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return OutlinedTextFieldKt.OutlinedTextField$lambda$7$0((SemanticsPropertyReceiver) obj);
                        }
                    };
                    $this$cache$iv2.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                companionM823paddingqDBjuR0$default = PaddingKt.m823paddingqDBjuR0$default(SemanticsModifierKt.semantics(companion, true, (Function1) it$iv2), 0.0f, density.mo401toDpGaN1DYA(OutlinedTextFieldTopPadding), 0.0f, 0.0f, 13, null);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-244324666);
                $composer3.endReplaceGroup();
                companionM823paddingqDBjuR0$default = Modifier.INSTANCE;
            }
            final boolean isError5 = isError4;
            final TextFieldColors colors4 = colors3;
            final boolean singleLine5 = singleLine4;
            final VisualTransformation visualTransformation5 = visualTransformation4;
            final MutableInteractionSource interactionSource5 = interactionSource4;
            final Function2<? super Composer, ? super Integer, Unit> function15 = function11;
            final Function2<? super Composer, ? super Integer, Unit> function16 = function12;
            final Function2<? super Composer, ? super Integer, Unit> function17 = function13;
            final Function2<? super Composer, ? super Integer, Unit> function18 = function14;
            final Shape shape4 = shape3;
            boolean readOnly5 = readOnly4;
            Composer $composer5 = $composer3;
            BasicTextFieldKt.BasicTextField(value, function1, SizeKt.m849defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifier4.then(companionM823paddingqDBjuR0$default), isError4, Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1947getDefaultErrorMessageUdPEhr4(), $composer3, 6)), TextFieldDefaults.INSTANCE.m1993getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m1992getMinHeightD9Ej5fM()), enabled5, readOnly5, mergedTextStyle, keyboardOptions4, keyboardActions4, singleLine4, maxLines4, minLines4, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, interactionSource4, new SolidColor(colors3.cursorColor(isError4, $composer3, ($dirty4 & 14) | (($dirty4 >> 24) & 112)).getValue().m5107unboximpl(), null), ComposableLambdaKt.rememberComposableLambda(-591103097, true, new Function3() { // from class: androidx.compose.material.OutlinedTextFieldKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return OutlinedTextFieldKt.OutlinedTextField$lambda$9(value, enabled5, singleLine5, visualTransformation5, interactionSource5, isError5, function15, function16, function17, function18, shape4, colors4, (Function2) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), $composer5, ($dirty3 & 14) | ($dirty3 & 112) | ($dirty3 & 7168) | (57344 & $dirty3) | (($dirty4 << 12) & 3670016) | (($dirty4 << 12) & 29360128) | (($dirty4 << 12) & 234881024) | (($dirty4 << 12) & 1879048192), (($dirty4 >> 18) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ($dirty4 & 112), 4096);
            $composer2 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            VisualTransformation visualTransformation6 = visualTransformation4;
            singleLine2 = singleLine4;
            visualTransformation2 = visualTransformation6;
            modifier3 = modifier4;
            enabled3 = enabled5;
            readOnly2 = readOnly5;
            maxLines2 = maxLines4;
            minLines2 = minLines4;
            colors2 = colors4;
            textStyle3 = textStyle6;
            function8 = function11;
            function7 = function12;
            function9 = function13;
            interactionSource2 = interactionSource3;
            shape2 = shape3;
            keyboardOptions2 = keyboardOptions4;
            keyboardActions2 = keyboardActions4;
            function10 = function14;
            isError2 = isError5;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            function7 = function3;
            isError2 = isError;
            keyboardOptions2 = keyboardOptions;
            singleLine2 = singleLine;
            maxLines2 = maxLines;
            minLines2 = minLines;
            interactionSource2 = interactionSource;
            shape2 = shape;
            colors2 = colors;
            enabled3 = enabled2;
            readOnly2 = z;
            textStyle3 = textStyle2;
            function8 = function6;
            modifier3 = modifier2;
            function9 = function4;
            function10 = function5;
            visualTransformation2 = visualTransformation;
            keyboardActions2 = keyboardActions;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.OutlinedTextFieldKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldKt.OutlinedTextField$lambda$10(value, function1, modifier3, enabled3, readOnly2, textStyle3, function8, function7, function9, function10, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, singleLine2, maxLines2, minLines2, interactionSource2, shape2, colors2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit OutlinedTextField$lambda$7$0(SemanticsPropertyReceiver $this$semantics) {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    static final Unit OutlinedTextField$lambda$9(String $value, final boolean $enabled, boolean $singleLine, VisualTransformation $visualTransformation, final MutableInteractionSource $interactionSource, final boolean $isError, Function2 $label, Function2 $placeholder, Function2 $leadingIcon, Function2 $trailingIcon, final Shape $shape, final TextFieldColors $colors, Function2 innerTextField, Composer $composer, int $changed) {
        Function2 function2;
        ComposerKt.sourceInformation($composer, "CN(innerTextField)396@21322L294,382@20676L959:OutlinedTextField.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            function2 = innerTextField;
            $dirty |= $composer.changedInstance(function2) ? 4 : 2;
        } else {
            function2 = innerTextField;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-591103097, $dirty, -1, "androidx.compose.material.OutlinedTextField.<anonymous> (OutlinedTextField.kt:382)");
            }
            TextFieldDefaults.INSTANCE.OutlinedTextFieldDecorationBox($value, function2, $enabled, $singleLine, $visualTransformation, $interactionSource, $isError, $label, $placeholder, $leadingIcon, $trailingIcon, $shape, $colors, null, ComposableLambdaKt.rememberComposableLambda(-1118701585, true, new Function2() { // from class: androidx.compose.material.OutlinedTextFieldKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldKt.OutlinedTextField$lambda$9$0($enabled, $isError, $interactionSource, $colors, $shape, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, ($dirty << 3) & 112, 221184, 8192);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit OutlinedTextField$lambda$9$0(boolean $enabled, boolean $isError, MutableInteractionSource $interactionSource, TextFieldColors $colors, Shape $shape, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C397@21366L228:OutlinedTextField.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1118701585, $changed, -1, "androidx.compose.material.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:397)");
            }
            TextFieldDefaults.INSTANCE.m1990BorderBoxnbWgWpA($enabled, $isError, $interactionSource, $colors, $shape, 0.0f, 0.0f, $composer, 12582912, 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with minLines instead")
    public static final /* synthetic */ void OutlinedTextField(final String value, final Function1 onValueChange, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2 label, Function2 placeholder, Function2 leadingIcon, Function2 trailingIcon, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        boolean enabled2;
        boolean z;
        TextStyle textStyle2;
        Function2 function2;
        int i2;
        int i3;
        int i4;
        TextFieldColors textFieldColors;
        int $dirty1;
        Composer $composer2;
        final Function2 placeholder2;
        final boolean isError2;
        final KeyboardOptions keyboardOptions2;
        final boolean singleLine2;
        final int maxLines2;
        final MutableInteractionSource interactionSource2;
        final Shape shape2;
        final TextFieldColors colors2;
        final boolean enabled3;
        final boolean readOnly2;
        final TextStyle textStyle3;
        final Function2 label2;
        final Modifier modifier3;
        final Function2 leadingIcon2;
        final Function2 label3;
        final VisualTransformation visualTransformation2;
        final KeyboardActions keyboardActions2;
        TextStyle textStyle4;
        int $dirty;
        MutableInteractionSource interactionSource3;
        Shape shape3;
        int $dirty2;
        Modifier modifier4;
        TextFieldColors colors3;
        TextStyle textStyle5;
        boolean readOnly3;
        int $dirty3;
        Function2 label4;
        Function2 placeholder3;
        Function2 leadingIcon3;
        Function2 trailingIcon2;
        boolean isError3;
        VisualTransformation visualTransformation3;
        KeyboardOptions keyboardOptions3;
        KeyboardActions keyboardActions3;
        boolean singleLine3;
        int maxLines3;
        MutableInteractionSource interactionSource4;
        Shape shape4;
        int $dirty4;
        int $dirty5;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(1453565505);
        ComposerKt.sourceInformation($composer3, "C(OutlinedTextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,isError,visualTransformation,keyboardOptions,keyboardActions,singleLine,maxLines,interactionSource,shape,colors)436@22763L417:OutlinedTextField.kt#jmzs0o");
        int $dirty6 = $changed;
        int $dirty7 = $changed1;
        if (($changed & 6) == 0) {
            $dirty6 |= $composer3.changed(value) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty6 |= $composer3.changedInstance(onValueChange) ? 32 : 16;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty6 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty6 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty6 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty6 |= 24576;
            z = readOnly;
        } else if (($changed & 24576) == 0) {
            z = readOnly;
            $dirty6 |= $composer3.changed(z) ? 16384 : 8192;
        } else {
            z = readOnly;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                textStyle2 = textStyle;
                if ($composer3.changed(textStyle2)) {
                    i5 = 131072;
                }
                $dirty6 |= i5;
            } else {
                textStyle2 = textStyle;
            }
            i5 = 65536;
            $dirty6 |= i5;
        } else {
            textStyle2 = textStyle;
        }
        int i9 = i & 64;
        if (i9 != 0) {
            $dirty6 |= 1572864;
            function2 = label;
        } else if (($changed & 1572864) == 0) {
            function2 = label;
            $dirty6 |= $composer3.changedInstance(function2) ? 1048576 : 524288;
        } else {
            function2 = label;
        }
        int i10 = i & 128;
        if (i10 != 0) {
            $dirty6 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty6 |= $composer3.changedInstance(placeholder) ? 8388608 : 4194304;
        }
        int i11 = i & 256;
        if (i11 != 0) {
            $dirty6 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty6 |= $composer3.changedInstance(leadingIcon) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i12 = i & 512;
        if (i12 != 0) {
            $dirty6 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty6 |= $composer3.changedInstance(trailingIcon) ? 536870912 : 268435456;
        }
        int i13 = i & 1024;
        if (i13 != 0) {
            $dirty7 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty7 |= $composer3.changed(isError) ? 4 : 2;
        }
        int i14 = i & 2048;
        if (i14 != 0) {
            $dirty7 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty7 |= $composer3.changed(visualTransformation) ? 32 : 16;
        }
        int i15 = i & 4096;
        if (i15 != 0) {
            $dirty7 |= 384;
            i2 = i15;
        } else {
            i2 = i15;
            if (($changed1 & 384) == 0) {
                $dirty7 |= $composer3.changed(keyboardOptions) ? 256 : 128;
            }
        }
        int i16 = i & 8192;
        if (i16 != 0) {
            $dirty7 |= 3072;
            i3 = i16;
        } else {
            i3 = i16;
            if (($changed1 & 3072) == 0) {
                $dirty7 |= $composer3.changed(keyboardActions) ? 2048 : 1024;
            }
        }
        int i17 = i & 16384;
        if (i17 != 0) {
            $dirty7 |= 24576;
            i4 = i17;
        } else {
            i4 = i17;
            if (($changed1 & 24576) == 0) {
                $dirty7 |= $composer3.changed(singleLine) ? 16384 : 8192;
            }
        }
        int i18 = i & 32768;
        if (i18 != 0) {
            $dirty7 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty7 |= $composer3.changed(maxLines) ? 131072 : 65536;
        }
        int i19 = i & 65536;
        if (i19 != 0) {
            $dirty7 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty7 |= $composer3.changed(interactionSource) ? 1048576 : 524288;
        }
        if (($changed1 & 12582912) == 0) {
            $dirty7 |= ((i & 131072) == 0 && $composer3.changed(shape)) ? 8388608 : 4194304;
        }
        if (($changed1 & 100663296) == 0) {
            if ((i & 262144) == 0) {
                $dirty5 = $dirty7;
                textFieldColors = colors;
                int i20 = $composer3.changed(textFieldColors) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                $dirty1 = $dirty5 | i20;
            } else {
                $dirty5 = $dirty7;
                textFieldColors = colors;
            }
            $dirty1 = $dirty5 | i20;
        } else {
            int $dirty8 = $dirty7;
            textFieldColors = colors;
            $dirty1 = $dirty8;
        }
        if ($composer3.shouldExecute((($dirty6 & 306783379) == 306783378 && ($dirty1 & 38347923) == 38347922) ? false : true, $dirty6 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "421@22018L7,432@22578L39,433@22656L22,434@22728L25");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i6 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i7 != 0 ? true : enabled2;
                boolean readOnly4 = i8 != 0 ? false : z;
                if ((i & 32) != 0) {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    textStyle4 = (TextStyle) objConsume;
                    $dirty = $dirty6 & (-458753);
                } else {
                    textStyle4 = textStyle2;
                    $dirty = $dirty6;
                }
                Function2 label5 = i9 != 0 ? null : function2;
                Function2 placeholder4 = i10 != 0 ? null : placeholder;
                Function2 leadingIcon4 = i11 != 0 ? null : leadingIcon;
                Function2 trailingIcon3 = i12 != 0 ? null : trailingIcon;
                boolean isError4 = i13 != 0 ? false : isError;
                VisualTransformation visualTransformation4 = i14 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                KeyboardOptions keyboardOptions4 = i2 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions;
                KeyboardActions keyboardActions4 = i3 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions;
                boolean singleLine4 = i4 != 0 ? false : singleLine;
                int maxLines4 = i18 != 0 ? Integer.MAX_VALUE : maxLines;
                if (i19 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 1012103976, "CC(remember):OutlinedTextField.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    interactionSource3 = (MutableInteractionSource) it$iv;
                } else {
                    interactionSource3 = interactionSource;
                }
                if ((i & 131072) != 0) {
                    shape3 = TextFieldDefaults.INSTANCE.getOutlinedTextFieldShape($composer3, 6);
                    $dirty2 = $dirty1 & (-29360129);
                } else {
                    shape3 = shape;
                    $dirty2 = $dirty1;
                }
                if ((i & 262144) != 0) {
                    $composer2 = $composer3;
                    modifier4 = modifier5;
                    textStyle5 = textStyle4;
                    enabled2 = enabled4;
                    colors3 = TextFieldDefaults.INSTANCE.m1996outlinedTextFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 0, 48, 2097151);
                    readOnly3 = readOnly4;
                    $dirty3 = $dirty;
                    label4 = label5;
                    placeholder3 = placeholder4;
                    leadingIcon3 = leadingIcon4;
                    trailingIcon2 = trailingIcon3;
                    isError3 = isError4;
                    visualTransformation3 = visualTransformation4;
                    keyboardOptions3 = keyboardOptions4;
                    keyboardActions3 = keyboardActions4;
                    singleLine3 = singleLine4;
                    maxLines3 = maxLines4;
                    interactionSource4 = interactionSource3;
                    shape4 = shape3;
                    $dirty4 = $dirty2 & (-234881025);
                } else {
                    $composer2 = $composer3;
                    modifier4 = modifier5;
                    colors3 = colors;
                    textStyle5 = textStyle4;
                    enabled2 = enabled4;
                    readOnly3 = readOnly4;
                    $dirty3 = $dirty;
                    label4 = label5;
                    placeholder3 = placeholder4;
                    leadingIcon3 = leadingIcon4;
                    trailingIcon2 = trailingIcon3;
                    isError3 = isError4;
                    visualTransformation3 = visualTransformation4;
                    keyboardOptions3 = keyboardOptions4;
                    keyboardActions3 = keyboardActions4;
                    singleLine3 = singleLine4;
                    maxLines3 = maxLines4;
                    interactionSource4 = interactionSource3;
                    shape4 = shape3;
                    $dirty4 = $dirty2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty6 &= -458753;
                }
                if ((i & 131072) != 0) {
                    $dirty1 &= -29360129;
                }
                if ((i & 262144) != 0) {
                    $dirty1 &= -234881025;
                }
                placeholder3 = placeholder;
                trailingIcon2 = trailingIcon;
                visualTransformation3 = visualTransformation;
                keyboardOptions3 = keyboardOptions;
                keyboardActions3 = keyboardActions;
                singleLine3 = singleLine;
                maxLines3 = maxLines;
                interactionSource4 = interactionSource;
                shape4 = shape;
                $composer2 = $composer3;
                $dirty3 = $dirty6;
                colors3 = textFieldColors;
                readOnly3 = z;
                textStyle5 = textStyle2;
                label4 = function2;
                modifier4 = modifier2;
                $dirty4 = $dirty1;
                leadingIcon3 = leadingIcon;
                isError3 = isError;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1453565505, $dirty3, $dirty4, "androidx.compose.material.OutlinedTextField (OutlinedTextField.kt:435)");
            }
            OutlinedTextField(value, (Function1<? super String, Unit>) onValueChange, modifier4, enabled2, readOnly3, textStyle5, (Function2<? super Composer, ? super Integer, Unit>) label4, (Function2<? super Composer, ? super Integer, Unit>) placeholder3, (Function2<? super Composer, ? super Integer, Unit>) leadingIcon3, (Function2<? super Composer, ? super Integer, Unit>) trailingIcon2, isError3, visualTransformation3, keyboardOptions3, keyboardActions3, singleLine3, maxLines3, 1, interactionSource4, shape4, colors3, $composer2, ($dirty3 & 14) | ($dirty3 & 112) | ($dirty3 & 896) | ($dirty3 & 7168) | (57344 & $dirty3) | (458752 & $dirty3) | (3670016 & $dirty3) | (29360128 & $dirty3) | (234881024 & $dirty3) | (1879048192 & $dirty3), ($dirty4 & 14) | 1572864 | ($dirty4 & 112) | ($dirty4 & 896) | ($dirty4 & 7168) | (57344 & $dirty4) | (458752 & $dirty4) | (($dirty4 << 3) & 29360128) | (($dirty4 << 3) & 234881024) | (($dirty4 << 3) & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enabled3 = enabled2;
            readOnly2 = readOnly3;
            textStyle3 = textStyle5;
            label2 = label4;
            placeholder2 = placeholder3;
            leadingIcon2 = leadingIcon3;
            label3 = trailingIcon2;
            isError2 = isError3;
            visualTransformation2 = visualTransformation3;
            keyboardOptions2 = keyboardOptions3;
            keyboardActions2 = keyboardActions3;
            singleLine2 = singleLine3;
            maxLines2 = maxLines3;
            interactionSource2 = interactionSource4;
            shape2 = shape4;
            colors2 = colors3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            placeholder2 = placeholder;
            isError2 = isError;
            keyboardOptions2 = keyboardOptions;
            singleLine2 = singleLine;
            maxLines2 = maxLines;
            interactionSource2 = interactionSource;
            shape2 = shape;
            colors2 = colors;
            enabled3 = enabled2;
            readOnly2 = z;
            textStyle3 = textStyle2;
            label2 = function2;
            modifier3 = modifier2;
            leadingIcon2 = leadingIcon;
            label3 = trailingIcon;
            visualTransformation2 = visualTransformation;
            keyboardActions2 = keyboardActions;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.OutlinedTextFieldKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldKt.OutlinedTextField$lambda$12(value, onValueChange, modifier3, enabled3, readOnly2, textStyle3, label2, placeholder2, leadingIcon2, label3, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, singleLine2, maxLines2, interactionSource2, shape2, colors2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void OutlinedTextField(final TextFieldValue value, final Function1<? super TextFieldValue, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        boolean enabled2;
        boolean z;
        TextStyle textStyle2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        int i2;
        int i3;
        int $dirty1;
        Composer $composer2;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        final boolean isError2;
        final KeyboardOptions keyboardOptions2;
        final boolean singleLine2;
        final int maxLines2;
        final int minLines2;
        final MutableInteractionSource interactionSource2;
        final Shape shape2;
        final TextFieldColors colors2;
        final boolean enabled3;
        final boolean readOnly2;
        final TextStyle textStyle3;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final VisualTransformation visualTransformation2;
        final KeyboardActions keyboardActions2;
        Modifier.Companion modifier4;
        boolean enabled4;
        boolean readOnly3;
        int i4;
        int $dirty;
        TextStyle textStyle4;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        boolean isError3;
        VisualTransformation visualTransformation3;
        KeyboardOptions keyboardOptions3;
        int $dirty2;
        KeyboardActions keyboardActions3;
        boolean singleLine3;
        int maxLines3;
        int minLines3;
        MutableInteractionSource interactionSource3;
        Shape shape3;
        int $dirty3;
        Composer $composer3;
        TextFieldColors colors3;
        KeyboardActions keyboardActions4;
        boolean readOnly4;
        TextStyle textStyle5;
        int $dirty4;
        boolean isError4;
        VisualTransformation visualTransformation4;
        KeyboardOptions keyboardOptions4;
        boolean singleLine4;
        int maxLines4;
        int minLines4;
        int $dirty5;
        MutableInteractionSource interactionSource4;
        Modifier.Companion companionM823paddingqDBjuR0$default;
        int $dirty6;
        Composer $composer4 = $composer.startRestartGroup(-365650761);
        ComposerKt.sourceInformation($composer4, "C(OutlinedTextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,isError,visualTransformation,keyboardOptions,keyboardActions,singleLine,maxLines,minLines,interactionSource,shape,colors)553@29149L7,570@29825L38,579@30209L20,588@30563L1032,555@29162L2440:OutlinedTextField.kt#jmzs0o");
        int $dirty7 = $changed;
        int $dirty8 = $changed1;
        if (($changed & 6) == 0) {
            $dirty7 |= $composer4.changed(value) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty7 |= $composer4.changedInstance(function1) ? 32 : 16;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty7 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty7 |= $composer4.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty7 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty7 |= $composer4.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty7 |= 24576;
            z = readOnly;
        } else if (($changed & 24576) == 0) {
            z = readOnly;
            $dirty7 |= $composer4.changed(z) ? 16384 : 8192;
        } else {
            z = readOnly;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                textStyle2 = textStyle;
                int i8 = $composer4.changed(textStyle2) ? 131072 : 65536;
                $dirty7 |= i8;
            } else {
                textStyle2 = textStyle;
            }
            $dirty7 |= i8;
        } else {
            textStyle2 = textStyle;
        }
        int i9 = i & 64;
        if (i9 != 0) {
            $dirty7 |= 1572864;
            function6 = function2;
        } else if (($changed & 1572864) == 0) {
            function6 = function2;
            $dirty7 |= $composer4.changedInstance(function6) ? 1048576 : 524288;
        } else {
            function6 = function2;
        }
        int i10 = i & 128;
        if (i10 != 0) {
            $dirty7 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty7 |= $composer4.changedInstance(function3) ? 8388608 : 4194304;
        }
        int $dirty9 = i & 256;
        if ($dirty9 != 0) {
            $dirty7 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty7 |= $composer4.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty10 = i & 512;
        if ($dirty10 != 0) {
            $dirty7 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty7 |= $composer4.changedInstance(function5) ? 536870912 : 268435456;
        }
        int $dirty11 = i & 1024;
        if ($dirty11 != 0) {
            $dirty8 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty8 |= $composer4.changed(isError) ? 4 : 2;
        }
        int $dirty12 = i & 2048;
        if ($dirty12 != 0) {
            $dirty8 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty8 |= $composer4.changed(visualTransformation) ? 32 : 16;
        }
        int i11 = i & 4096;
        if (i11 != 0) {
            $dirty8 |= 384;
            i2 = i11;
        } else {
            i2 = i11;
            if (($changed1 & 384) == 0) {
                $dirty8 |= $composer4.changed(keyboardOptions) ? 256 : 128;
            }
        }
        if (($changed1 & 3072) == 0) {
            $dirty8 |= ((i & 8192) == 0 && $composer4.changed(keyboardActions)) ? 2048 : 1024;
        }
        int i12 = i & 16384;
        if (i12 != 0) {
            $dirty8 |= 24576;
            i3 = i12;
        } else {
            i3 = i12;
            if (($changed1 & 24576) == 0) {
                $dirty8 |= $composer4.changed(singleLine) ? 16384 : 8192;
            }
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty8 |= ((i & 32768) == 0 && $composer4.changed(maxLines)) ? 131072 : 65536;
        }
        int i13 = i & 65536;
        if (i13 != 0) {
            $dirty8 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty8 |= $composer4.changed(minLines) ? 1048576 : 524288;
        }
        int i14 = i & 131072;
        if (i14 != 0) {
            $dirty8 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty8 |= $composer4.changed(interactionSource) ? 8388608 : 4194304;
        }
        if (($changed1 & 100663296) == 0) {
            $dirty8 |= ((i & 262144) == 0 && $composer4.changed(shape)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed1 & C.ENCODING_PCM_32BIT) == 0) {
            if ((i & 524288) == 0) {
                $dirty6 = $dirty8;
                int i15 = $composer4.changed(colors) ? 536870912 : 268435456;
                $dirty1 = $dirty6 | i15;
            } else {
                $dirty6 = $dirty8;
            }
            $dirty1 = $dirty6 | i15;
        } else {
            $dirty1 = $dirty8;
        }
        int $dirty13 = $dirty7;
        if ($composer4.shouldExecute((($dirty7 & 306783379) == 306783378 && (306783379 & $dirty1) == 306783378) ? false : true, $dirty13 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "531@28011L7,544@28655L22,545@28727L25");
            if (($changed & 1) != 0 && !$composer4.getDefaultsInvalid()) {
                $composer4.skipToGroupEnd();
                int $dirty14 = (i & 32) != 0 ? $dirty13 & (-458753) : $dirty13;
                if ((i & 8192) != 0) {
                    $dirty1 &= -7169;
                }
                if ((32768 & i) != 0) {
                    $dirty1 &= -458753;
                }
                if ((262144 & i) != 0) {
                    $dirty1 &= -234881025;
                }
                if ((i & 524288) != 0) {
                    $dirty1 &= -1879048193;
                }
                function12 = function3;
                function13 = function4;
                function14 = function5;
                isError4 = isError;
                visualTransformation4 = visualTransformation;
                keyboardActions4 = keyboardActions;
                maxLines4 = maxLines;
                interactionSource3 = interactionSource;
                shape3 = shape;
                colors3 = colors;
                $composer3 = $composer4;
                readOnly4 = z;
                function11 = function6;
                singleLine4 = singleLine;
                $dirty5 = $dirty1;
                $dirty4 = $dirty14;
                textStyle5 = textStyle2;
                modifier4 = modifier2;
                keyboardOptions4 = keyboardOptions;
                minLines4 = minLines;
            } else {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i6 == 0) {
                    enabled4 = enabled2;
                } else {
                    enabled4 = true;
                }
                if (i7 == 0) {
                    readOnly3 = z;
                } else {
                    readOnly3 = false;
                }
                if ((i & 32) == 0) {
                    i4 = -458753;
                    $dirty = $dirty13;
                    textStyle4 = textStyle2;
                } else {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    i4 = -458753;
                    ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer4.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    textStyle4 = (TextStyle) objConsume;
                    $dirty = $dirty13 & (-458753);
                }
                if (i9 != 0) {
                    function11 = null;
                } else {
                    function11 = function6;
                }
                if (i10 == 0) {
                    function12 = function3;
                } else {
                    function12 = null;
                }
                if ($dirty9 == 0) {
                    function13 = function4;
                } else {
                    function13 = null;
                }
                if ($dirty10 == 0) {
                    function14 = function5;
                } else {
                    function14 = null;
                }
                if ($dirty11 == 0) {
                    isError3 = isError;
                } else {
                    isError3 = false;
                }
                if ($dirty12 == 0) {
                    visualTransformation3 = visualTransformation;
                } else {
                    visualTransformation3 = VisualTransformation.INSTANCE.getNone();
                }
                if (i2 == 0) {
                    keyboardOptions3 = keyboardOptions;
                } else {
                    keyboardOptions3 = KeyboardOptions.INSTANCE.getDefault();
                }
                if ((i & 8192) == 0) {
                    $dirty2 = $dirty1;
                    keyboardActions3 = keyboardActions;
                } else {
                    $dirty2 = $dirty1 & (-7169);
                    keyboardActions3 = new KeyboardActions(null, null, null, null, null, null, 63, null);
                }
                if (i3 == 0) {
                    singleLine3 = singleLine;
                } else {
                    singleLine3 = false;
                }
                if ((32768 & i) == 0) {
                    maxLines3 = maxLines;
                } else {
                    $dirty2 &= i4;
                    maxLines3 = singleLine3 ? 1 : Integer.MAX_VALUE;
                }
                if (i13 == 0) {
                    minLines3 = minLines;
                } else {
                    minLines3 = 1;
                }
                if (i14 == 0) {
                    interactionSource3 = interactionSource;
                } else {
                    interactionSource3 = null;
                }
                if ((262144 & i) == 0) {
                    shape3 = shape;
                    $dirty3 = $dirty2;
                } else {
                    shape3 = TextFieldDefaults.INSTANCE.getOutlinedTextFieldShape($composer4, 6);
                    $dirty3 = $dirty2 & (-234881025);
                }
                if ((i & 524288) == 0) {
                    $composer3 = $composer4;
                    colors3 = colors;
                    keyboardActions4 = keyboardActions3;
                    enabled2 = enabled4;
                    readOnly4 = readOnly3;
                    textStyle5 = textStyle4;
                    $dirty4 = $dirty;
                    isError4 = isError3;
                    visualTransformation4 = visualTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    singleLine4 = singleLine3;
                    maxLines4 = maxLines3;
                    minLines4 = minLines3;
                    $dirty5 = $dirty3;
                } else {
                    $composer3 = $composer4;
                    keyboardActions4 = keyboardActions3;
                    colors3 = TextFieldDefaults.INSTANCE.m1996outlinedTextFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer4, 0, 0, 48, 2097151);
                    enabled2 = enabled4;
                    readOnly4 = readOnly3;
                    textStyle5 = textStyle4;
                    $dirty4 = $dirty;
                    isError4 = isError3;
                    visualTransformation4 = visualTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    singleLine4 = singleLine3;
                    maxLines4 = maxLines3;
                    minLines4 = minLines3;
                    $dirty5 = $dirty3 & (-1879048193);
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-365650761, $dirty4, $dirty5, "androidx.compose.material.OutlinedTextField (OutlinedTextField.kt:546)");
            }
            if (interactionSource3 == null) {
                $composer3.startReplaceGroup(-1063705564);
                ComposerKt.sourceInformation($composer3, "548@28839L39");
                ComposerKt.sourceInformationMarkerStart($composer3, 796970910, "CC(remember):OutlinedTextField.kt#9igjgp");
                Composer $this$cache$iv = $composer3;
                Object it$iv = $this$cache$iv.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $this$cache$iv.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
                interactionSource4 = (MutableInteractionSource) it$iv;
            } else {
                $composer3.startReplaceGroup(796970259);
                $composer3.endReplaceGroup();
                interactionSource4 = interactionSource3;
            }
            $composer3.startReplaceGroup(796976005);
            ComposerKt.sourceInformation($composer3, "*550@29018L18");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle5.m7358getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                $this$takeOrElse_u2dDxMtmZc$iv = colors3.textColor(enabled2, $composer3, (($dirty4 >> 9) & 14) | (($dirty5 >> 24) & 112)).getValue().m5107unboximpl();
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle5.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            Composer $composer$iv = $composer3;
            TextStyle textStyle6 = textStyle5;
            final boolean enabled5 = enabled2;
            ComposerKt.sourceInformationMarkerStart($composer$iv, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer$iv.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            Density density = (Density) objConsume2;
            if (function11 != null) {
                $composer3.startReplaceGroup(-1063234302);
                ComposerKt.sourceInformation($composer3, "564@29575L2");
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 796994425, "CC(remember):OutlinedTextField.kt#9igjgp");
                Composer $this$cache$iv2 = $composer3;
                Object it$iv2 = $this$cache$iv2.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = new Function1() { // from class: androidx.compose.material.OutlinedTextFieldKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return OutlinedTextFieldKt.OutlinedTextField$lambda$15$0((SemanticsPropertyReceiver) obj);
                        }
                    };
                    $this$cache$iv2.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                companionM823paddingqDBjuR0$default = PaddingKt.m823paddingqDBjuR0$default(SemanticsModifierKt.semantics(companion, true, (Function1) it$iv2), 0.0f, density.mo401toDpGaN1DYA(OutlinedTextFieldTopPadding), 0.0f, 0.0f, 13, null);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-1062848941);
                $composer3.endReplaceGroup();
                companionM823paddingqDBjuR0$default = Modifier.INSTANCE;
            }
            final boolean isError5 = isError4;
            final TextFieldColors colors4 = colors3;
            final boolean singleLine5 = singleLine4;
            final VisualTransformation visualTransformation5 = visualTransformation4;
            final MutableInteractionSource interactionSource5 = interactionSource4;
            final Function2<? super Composer, ? super Integer, Unit> function15 = function11;
            final Function2<? super Composer, ? super Integer, Unit> function16 = function12;
            final Function2<? super Composer, ? super Integer, Unit> function17 = function13;
            final Function2<? super Composer, ? super Integer, Unit> function18 = function14;
            final Shape shape4 = shape3;
            boolean readOnly5 = readOnly4;
            Composer $composer5 = $composer3;
            BasicTextFieldKt.BasicTextField(value, function1, SizeKt.m849defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifier4.then(companionM823paddingqDBjuR0$default), isError4, Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1947getDefaultErrorMessageUdPEhr4(), $composer3, 6)), TextFieldDefaults.INSTANCE.m1993getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m1992getMinHeightD9Ej5fM()), enabled5, readOnly5, mergedTextStyle, keyboardOptions4, keyboardActions4, singleLine4, maxLines4, minLines4, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, interactionSource4, new SolidColor(colors3.cursorColor(isError4, $composer3, ($dirty5 & 14) | (($dirty5 >> 24) & 112)).getValue().m5107unboximpl(), null), ComposableLambdaKt.rememberComposableLambda(-1881867558, true, new Function3() { // from class: androidx.compose.material.OutlinedTextFieldKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return OutlinedTextFieldKt.OutlinedTextField$lambda$17(value, enabled5, singleLine5, visualTransformation5, interactionSource5, isError5, function15, function16, function17, function18, shape4, colors4, (Function2) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), $composer5, ($dirty4 & 14) | ($dirty4 & 112) | ($dirty4 & 7168) | (57344 & $dirty4) | (($dirty5 << 12) & 3670016) | (($dirty5 << 12) & 29360128) | (($dirty5 << 12) & 234881024) | (($dirty5 << 12) & 1879048192), (($dirty5 >> 18) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ($dirty5 & 112), 4096);
            $composer2 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            VisualTransformation visualTransformation6 = visualTransformation4;
            singleLine2 = singleLine4;
            visualTransformation2 = visualTransformation6;
            textStyle3 = textStyle6;
            colors2 = colors4;
            enabled3 = enabled5;
            readOnly2 = readOnly5;
            maxLines2 = maxLines4;
            minLines2 = minLines4;
            function8 = function11;
            function7 = function12;
            function9 = function13;
            interactionSource2 = interactionSource3;
            shape2 = shape3;
            modifier3 = modifier4;
            keyboardOptions2 = keyboardOptions4;
            keyboardActions2 = keyboardActions4;
            function10 = function14;
            isError2 = isError5;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            function7 = function3;
            isError2 = isError;
            keyboardOptions2 = keyboardOptions;
            singleLine2 = singleLine;
            maxLines2 = maxLines;
            minLines2 = minLines;
            interactionSource2 = interactionSource;
            shape2 = shape;
            colors2 = colors;
            enabled3 = enabled2;
            readOnly2 = z;
            textStyle3 = textStyle2;
            function8 = function6;
            modifier3 = modifier2;
            function9 = function4;
            function10 = function5;
            visualTransformation2 = visualTransformation;
            keyboardActions2 = keyboardActions;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.OutlinedTextFieldKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldKt.OutlinedTextField$lambda$18(value, function1, modifier3, enabled3, readOnly2, textStyle3, function8, function7, function9, function10, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, singleLine2, maxLines2, minLines2, interactionSource2, shape2, colors2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit OutlinedTextField$lambda$15$0(SemanticsPropertyReceiver $this$semantics) {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    static final Unit OutlinedTextField$lambda$17(TextFieldValue $value, final boolean $enabled, boolean $singleLine, VisualTransformation $visualTransformation, final MutableInteractionSource $interactionSource, final boolean $isError, Function2 $label, Function2 $placeholder, Function2 $leadingIcon, Function2 $trailingIcon, final Shape $shape, final TextFieldColors $colors, Function2 innerTextField, Composer $composer, int $changed) {
        Function2 function2;
        ComposerKt.sourceInformation($composer, "CN(innerTextField)603@31268L294,589@30617L964:OutlinedTextField.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            function2 = innerTextField;
            $dirty |= $composer.changedInstance(function2) ? 4 : 2;
        } else {
            function2 = innerTextField;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1881867558, $dirty, -1, "androidx.compose.material.OutlinedTextField.<anonymous> (OutlinedTextField.kt:589)");
            }
            TextFieldDefaults.INSTANCE.OutlinedTextFieldDecorationBox($value.getText(), function2, $enabled, $singleLine, $visualTransformation, $interactionSource, $isError, $label, $placeholder, $leadingIcon, $trailingIcon, $shape, $colors, null, ComposableLambdaKt.rememberComposableLambda(-185364670, true, new Function2() { // from class: androidx.compose.material.OutlinedTextFieldKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldKt.OutlinedTextField$lambda$17$0($enabled, $isError, $interactionSource, $colors, $shape, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, ($dirty << 3) & 112, 221184, 8192);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit OutlinedTextField$lambda$17$0(boolean $enabled, boolean $isError, MutableInteractionSource $interactionSource, TextFieldColors $colors, Shape $shape, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C604@31312L228:OutlinedTextField.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-185364670, $changed, -1, "androidx.compose.material.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:604)");
            }
            TextFieldDefaults.INSTANCE.m1990BorderBoxnbWgWpA($enabled, $isError, $interactionSource, $colors, $shape, 0.0f, 0.0f, $composer, 12582912, 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with minLines instead")
    public static final /* synthetic */ void OutlinedTextField(final TextFieldValue value, final Function1 onValueChange, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2 label, Function2 placeholder, Function2 leadingIcon, Function2 trailingIcon, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        boolean enabled2;
        boolean z;
        TextStyle textStyle2;
        Function2 function2;
        int i2;
        int i3;
        TextFieldColors textFieldColors;
        int $dirty1;
        Composer $composer2;
        final Function2 placeholder2;
        final boolean isError2;
        final KeyboardOptions keyboardOptions2;
        final boolean singleLine2;
        final int maxLines2;
        final MutableInteractionSource interactionSource2;
        final Shape shape2;
        final TextFieldColors colors2;
        final boolean enabled3;
        final boolean readOnly2;
        final TextStyle textStyle3;
        final Function2 label2;
        final Modifier modifier3;
        final Function2 leadingIcon2;
        final Function2 label3;
        final VisualTransformation visualTransformation2;
        final KeyboardActions keyboardActions2;
        int $dirty;
        TextStyle textStyle4;
        int $dirty2;
        KeyboardActions keyboardActions3;
        MutableInteractionSource interactionSource3;
        Shape shape3;
        int $dirty3;
        TextFieldColors colors3;
        KeyboardActions keyboardActions4;
        Modifier modifier4;
        boolean readOnly3;
        TextStyle textStyle5;
        int $dirty4;
        Function2 label4;
        Function2 placeholder3;
        Function2 leadingIcon3;
        Function2 trailingIcon2;
        boolean isError3;
        VisualTransformation visualTransformation3;
        KeyboardOptions keyboardOptions3;
        boolean singleLine3;
        int maxLines3;
        MutableInteractionSource interactionSource4;
        Shape shape4;
        int $dirty5;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(1710103060);
        ComposerKt.sourceInformation($composer3, "C(OutlinedTextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,isError,visualTransformation,keyboardOptions,keyboardActions,singleLine,maxLines,interactionSource,shape,colors)643@32719L417:OutlinedTextField.kt#jmzs0o");
        int $dirty6 = $changed;
        int $dirty7 = $changed1;
        if (($changed & 6) == 0) {
            $dirty6 |= $composer3.changed(value) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty6 |= $composer3.changedInstance(onValueChange) ? 32 : 16;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty6 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty6 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty6 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty6 |= 24576;
            z = readOnly;
        } else if (($changed & 24576) == 0) {
            z = readOnly;
            $dirty6 |= $composer3.changed(z) ? 16384 : 8192;
        } else {
            z = readOnly;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                textStyle2 = textStyle;
                if ($composer3.changed(textStyle2)) {
                    i4 = 131072;
                }
                $dirty6 |= i4;
            } else {
                textStyle2 = textStyle;
            }
            i4 = 65536;
            $dirty6 |= i4;
        } else {
            textStyle2 = textStyle;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty6 |= 1572864;
            function2 = label;
        } else if (($changed & 1572864) == 0) {
            function2 = label;
            $dirty6 |= $composer3.changedInstance(function2) ? 1048576 : 524288;
        } else {
            function2 = label;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty6 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty6 |= $composer3.changedInstance(placeholder) ? 8388608 : 4194304;
        }
        int $dirty8 = i & 256;
        if ($dirty8 != 0) {
            $dirty6 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty6 |= $composer3.changedInstance(leadingIcon) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty9 = i & 512;
        if ($dirty9 != 0) {
            $dirty6 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty6 |= $composer3.changedInstance(trailingIcon) ? 536870912 : 268435456;
        }
        int $dirty10 = i & 1024;
        if ($dirty10 != 0) {
            $dirty7 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty7 |= $composer3.changed(isError) ? 4 : 2;
        }
        int $dirty11 = i & 2048;
        if ($dirty11 != 0) {
            $dirty7 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty7 |= $composer3.changed(visualTransformation) ? 32 : 16;
        }
        int i10 = i & 4096;
        if (i10 != 0) {
            $dirty7 |= 384;
            i2 = i10;
        } else {
            i2 = i10;
            if (($changed1 & 384) == 0) {
                $dirty7 |= $composer3.changed(keyboardOptions) ? 256 : 128;
            }
        }
        if (($changed1 & 3072) == 0) {
            $dirty7 |= ((i & 8192) == 0 && $composer3.changed(keyboardActions)) ? 2048 : 1024;
        }
        int i11 = i & 16384;
        if (i11 != 0) {
            $dirty7 |= 24576;
            i3 = i11;
        } else {
            i3 = i11;
            if (($changed1 & 24576) == 0) {
                $dirty7 |= $composer3.changed(singleLine) ? 16384 : 8192;
            }
        }
        int i12 = i & 32768;
        if (i12 != 0) {
            $dirty7 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty7 |= $composer3.changed(maxLines) ? 131072 : 65536;
        }
        int i13 = i & 65536;
        if (i13 != 0) {
            $dirty7 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty7 |= $composer3.changed(interactionSource) ? 1048576 : 524288;
        }
        if (($changed1 & 12582912) == 0) {
            $dirty7 |= ((i & 131072) == 0 && $composer3.changed(shape)) ? 8388608 : 4194304;
        }
        if (($changed1 & 100663296) == 0) {
            if ((i & 262144) == 0) {
                $dirty5 = $dirty7;
                textFieldColors = colors;
                int i14 = $composer3.changed(textFieldColors) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                $dirty1 = $dirty5 | i14;
            } else {
                $dirty5 = $dirty7;
                textFieldColors = colors;
            }
            $dirty1 = $dirty5 | i14;
        } else {
            int $dirty12 = $dirty7;
            textFieldColors = colors;
            $dirty1 = $dirty12;
        }
        int $dirty13 = $dirty6;
        if ($composer3.shouldExecute((($dirty6 & 306783379) == 306783378 && (38347923 & $dirty1) == 38347922) ? false : true, $dirty13 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "628@31980L7,639@32534L39,640@32612L22,641@32684L25");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i6 != 0 ? true : enabled2;
                boolean readOnly4 = i7 != 0 ? false : z;
                if ((i & 32) != 0) {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    textStyle4 = (TextStyle) objConsume;
                    $dirty = $dirty13 & (-458753);
                } else {
                    $dirty = $dirty13;
                    textStyle4 = textStyle2;
                }
                Function2 label5 = i8 != 0 ? null : function2;
                Function2 placeholder4 = i9 != 0 ? null : placeholder;
                Function2 leadingIcon4 = $dirty8 != 0 ? null : leadingIcon;
                Function2 trailingIcon3 = $dirty9 != 0 ? null : trailingIcon;
                boolean isError4 = $dirty10 != 0 ? false : isError;
                VisualTransformation visualTransformation4 = $dirty11 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                KeyboardOptions keyboardOptions4 = i2 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions;
                if ((i & 8192) != 0) {
                    $dirty2 = $dirty1 & (-7169);
                    keyboardActions3 = new KeyboardActions(null, null, null, null, null, null, 63, null);
                } else {
                    $dirty2 = $dirty1;
                    keyboardActions3 = keyboardActions;
                }
                boolean singleLine4 = i3 != 0 ? false : singleLine;
                int maxLines4 = i12 != 0 ? Integer.MAX_VALUE : maxLines;
                if (i13 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -1563408517, "CC(remember):OutlinedTextField.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    interactionSource3 = (MutableInteractionSource) it$iv;
                } else {
                    interactionSource3 = interactionSource;
                }
                if ((i & 131072) != 0) {
                    shape3 = TextFieldDefaults.INSTANCE.getOutlinedTextFieldShape($composer3, 6);
                    $dirty3 = $dirty2 & (-29360129);
                } else {
                    shape3 = shape;
                    $dirty3 = $dirty2;
                }
                if ((i & 262144) != 0) {
                    $composer2 = $composer3;
                    keyboardActions4 = keyboardActions3;
                    modifier4 = modifier5;
                    colors3 = TextFieldDefaults.INSTANCE.m1996outlinedTextFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 0, 48, 2097151);
                    enabled2 = enabled4;
                    readOnly3 = readOnly4;
                    textStyle5 = textStyle4;
                    $dirty4 = $dirty;
                    label4 = label5;
                    placeholder3 = placeholder4;
                    leadingIcon3 = leadingIcon4;
                    trailingIcon2 = trailingIcon3;
                    isError3 = isError4;
                    visualTransformation3 = visualTransformation4;
                    keyboardOptions3 = keyboardOptions4;
                    singleLine3 = singleLine4;
                    maxLines3 = maxLines4;
                    interactionSource4 = interactionSource3;
                    shape4 = shape3;
                    $dirty1 = $dirty3 & (-234881025);
                } else {
                    $composer2 = $composer3;
                    colors3 = colors;
                    keyboardActions4 = keyboardActions3;
                    modifier4 = modifier5;
                    enabled2 = enabled4;
                    readOnly3 = readOnly4;
                    textStyle5 = textStyle4;
                    $dirty4 = $dirty;
                    label4 = label5;
                    placeholder3 = placeholder4;
                    leadingIcon3 = leadingIcon4;
                    trailingIcon2 = trailingIcon3;
                    isError3 = isError4;
                    visualTransformation3 = visualTransformation4;
                    keyboardOptions3 = keyboardOptions4;
                    singleLine3 = singleLine4;
                    maxLines3 = maxLines4;
                    interactionSource4 = interactionSource3;
                    shape4 = shape3;
                    $dirty1 = $dirty3;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty14 = (i & 32) != 0 ? $dirty13 & (-458753) : $dirty13;
                if ((i & 8192) != 0) {
                    $dirty1 &= -7169;
                }
                if ((i & 131072) != 0) {
                    $dirty1 &= -29360129;
                }
                if ((i & 262144) != 0) {
                    $dirty1 &= -234881025;
                }
                placeholder3 = placeholder;
                trailingIcon2 = trailingIcon;
                visualTransformation3 = visualTransformation;
                keyboardOptions3 = keyboardOptions;
                keyboardActions4 = keyboardActions;
                singleLine3 = singleLine;
                maxLines3 = maxLines;
                interactionSource4 = interactionSource;
                shape4 = shape;
                $composer2 = $composer3;
                $dirty4 = $dirty14;
                colors3 = textFieldColors;
                readOnly3 = z;
                textStyle5 = textStyle2;
                label4 = function2;
                modifier4 = modifier2;
                leadingIcon3 = leadingIcon;
                isError3 = isError;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1710103060, $dirty4, $dirty1, "androidx.compose.material.OutlinedTextField (OutlinedTextField.kt:642)");
            }
            OutlinedTextField(value, (Function1<? super TextFieldValue, Unit>) onValueChange, modifier4, enabled2, readOnly3, textStyle5, (Function2<? super Composer, ? super Integer, Unit>) label4, (Function2<? super Composer, ? super Integer, Unit>) placeholder3, (Function2<? super Composer, ? super Integer, Unit>) leadingIcon3, (Function2<? super Composer, ? super Integer, Unit>) trailingIcon2, isError3, visualTransformation3, keyboardOptions3, keyboardActions4, singleLine3, maxLines3, 1, interactionSource4, shape4, colors3, $composer2, ($dirty4 & 14) | ($dirty4 & 112) | ($dirty4 & 896) | ($dirty4 & 7168) | (57344 & $dirty4) | (458752 & $dirty4) | (3670016 & $dirty4) | (29360128 & $dirty4) | (234881024 & $dirty4) | (1879048192 & $dirty4), ($dirty1 & 14) | 1572864 | ($dirty1 & 112) | ($dirty1 & 896) | ($dirty1 & 7168) | (57344 & $dirty1) | (458752 & $dirty1) | (($dirty1 << 3) & 29360128) | (($dirty1 << 3) & 234881024) | (($dirty1 << 3) & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enabled3 = enabled2;
            readOnly2 = readOnly3;
            textStyle3 = textStyle5;
            label2 = label4;
            placeholder2 = placeholder3;
            leadingIcon2 = leadingIcon3;
            label3 = trailingIcon2;
            isError2 = isError3;
            visualTransformation2 = visualTransformation3;
            keyboardOptions2 = keyboardOptions3;
            keyboardActions2 = keyboardActions4;
            singleLine2 = singleLine3;
            maxLines2 = maxLines3;
            interactionSource2 = interactionSource4;
            shape2 = shape4;
            colors2 = colors3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            placeholder2 = placeholder;
            isError2 = isError;
            keyboardOptions2 = keyboardOptions;
            singleLine2 = singleLine;
            maxLines2 = maxLines;
            interactionSource2 = interactionSource;
            shape2 = shape;
            colors2 = colors;
            enabled3 = enabled2;
            readOnly2 = z;
            textStyle3 = textStyle2;
            label2 = function2;
            modifier3 = modifier2;
            leadingIcon2 = leadingIcon;
            label3 = trailingIcon;
            visualTransformation2 = visualTransformation;
            keyboardActions2 = keyboardActions;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.OutlinedTextFieldKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldKt.OutlinedTextField$lambda$20(value, onValueChange, modifier3, enabled3, readOnly2, textStyle3, label2, placeholder2, leadingIcon2, label3, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, singleLine2, maxLines2, interactionSource2, shape2, colors2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:116:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:119:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:120:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:130:0x028a  */
    /* JADX WARN: Code duplicated, block: B:132:0x0308  */
    /* JADX WARN: Code duplicated, block: B:135:0x0314  */
    /* JADX WARN: Code duplicated, block: B:136:0x031a  */
    /* JADX WARN: Code duplicated, block: B:145:0x03d2  */
    /* JADX WARN: Code duplicated, block: B:148:0x03e1  */
    /* JADX WARN: Code duplicated, block: B:150:0x0455  */
    /* JADX WARN: Code duplicated, block: B:153:0x0461  */
    /* JADX WARN: Code duplicated, block: B:154:0x0467  */
    /* JADX WARN: Code duplicated, block: B:163:0x051f  */
    /* JADX WARN: Code duplicated, block: B:166:0x0538  */
    /* JADX WARN: Code duplicated, block: B:167:0x0558  */
    /* JADX WARN: Code duplicated, block: B:170:0x055d  */
    /* JADX WARN: Code duplicated, block: B:171:0x057d  */
    /* JADX WARN: Code duplicated, block: B:174:0x058e  */
    /* JADX WARN: Code duplicated, block: B:175:0x05b3  */
    /* JADX WARN: Code duplicated, block: B:178:0x0631  */
    /* JADX WARN: Code duplicated, block: B:181:0x063d  */
    /* JADX WARN: Code duplicated, block: B:182:0x0643  */
    /* JADX WARN: Code duplicated, block: B:192:0x06f9  */
    /* JADX WARN: Code duplicated, block: B:194:0x0762  */
    /* JADX WARN: Code duplicated, block: B:197:0x076e  */
    /* JADX WARN: Code duplicated, block: B:198:0x0774  */
    /* JADX WARN: Code duplicated, block: B:201:0x07a5  */
    /* JADX WARN: Code duplicated, block: B:203:0x07b7  */
    /* JADX WARN: Code duplicated, block: B:204:0x07ba  */
    /* JADX WARN: Code duplicated, block: B:207:0x0829  */
    /* JADX WARN: Code duplicated, block: B:210:0x084a  */
    public static final void OutlinedTextFieldLayout(final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super Modifier, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final Function2<? super Composer, ? super Integer, Unit> function6, final boolean singleLine, final float animationProgress, final Function1<? super Size, Unit> function1, final Function2<? super Composer, ? super Integer, Unit> function7, final PaddingValues paddingValues, Composer $composer, final int $changed, final int $changed1) {
        Function2<? super Composer, ? super Integer, Unit> function8;
        Composer $composer2;
        int $dirty;
        int compositeKeyHash$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        Composer $this$Layout_u24lambda_u240$iv;
        float startTextFieldPadding;
        float endTextFieldPadding;
        float f;
        float f2;
        Modifier padding;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function9;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        int compositeKeyHash$iv$iv2;
        Function0<ComposeUiNode> constructor3;
        Function0<ComposeUiNode> function10;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        int $changed$iv;
        Composer $composer$iv;
        int compositeKeyHash$iv$iv3;
        Function0<ComposeUiNode> constructor4;
        Function0<ComposeUiNode> function11;
        Composer $this$Layout_u24lambda_u240$iv$iv3;
        int compositeKeyHash$iv$iv4;
        Function0<ComposeUiNode> constructor5;
        Function0<ComposeUiNode> function12;
        Composer $this$Layout_u24lambda_u240$iv$iv4;
        Composer $composer3 = $composer.startRestartGroup(36320288);
        ComposerKt.sourceInformation($composer3, "C(OutlinedTextFieldLayout)N(modifier,textField,placeholder,label,leading,trailing,singleLine,animationProgress,onLabelMeasured,border,paddingValues)687@33864L268,695@34180L7,696@34192L2415:OutlinedTextField.kt#jmzs0o");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changedInstance(function4) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changedInstance(function5) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty2 |= $composer3.changedInstance(function6) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty2 |= $composer3.changed(singleLine) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(animationProgress) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changedInstance(function1) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty2 |= $composer3.changedInstance(function7) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(paddingValues) ? 4 : 2;
        }
        if ($composer3.shouldExecute((($dirty2 & 306783379) == 306783378 && ($dirty1 & 3) == 2) ? false : true, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(36320288, $dirty2, $dirty1, "androidx.compose.material.OutlinedTextFieldLayout (OutlinedTextField.kt:685)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 544073420, "CC(remember):OutlinedTextField.kt#9igjgp");
            boolean invalid$iv = ((234881024 & $dirty2) == 67108864) | ((3670016 & $dirty2) == 1048576) | ((29360128 & $dirty2) == 8388608) | (($dirty1 & 14) == 4);
            Object value$iv = $composer3.rememberedValue();
            if (!invalid$iv) {
                $dirty = $dirty2;
                if (value$iv == Composer.INSTANCE.getEmpty()) {
                }
                MeasurePolicy measurePolicy = (OutlinedTextFieldMeasurePolicy) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer3.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                LayoutDirection layoutDirection = (LayoutDirection) objConsume;
                MeasurePolicy measurePolicy$iv = measurePolicy;
                int $changed$iv2 = ($dirty << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer3, modifier);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv = (($changed$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function0 = constructor;
                    $composer3.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer3);
                $composer2 = $composer3;
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                    $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                    $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 1336985421, "C704@34696L8,744@36249L183:OutlinedTextField.kt#jmzs0o");
                function7.invoke($composer3, Integer.valueOf(($dirty >> 27) & 14));
                if (function5 != null) {
                    $composer3.startReplaceGroup(1336978507);
                    ComposerKt.sourceInformation($composer3, "707@34757L224");
                    Modifier modifier$iv = InteractiveComponentSizeKt.minimumInteractiveComponentSize(LayoutIdKt.layoutId(Modifier.INSTANCE, "Leading"));
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                    int $changed$iv$iv2 = (48 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                    constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function12 = constructor5;
                        $composer3.createNode(function12);
                    } else {
                        function12 = constructor5;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4367constructorimpl($composer3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                        $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                        $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash2);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i2 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i3 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2068226786, "C711@34954L9:OutlinedTextField.kt#jmzs0o");
                    function5.invoke($composer3, Integer.valueOf(($dirty >> 12) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    $composer3.startReplaceGroup(1302508491);
                }
                $composer3.endReplaceGroup();
                if (function6 != null) {
                    $composer3.startReplaceGroup(1337267241);
                    ComposerKt.sourceInformation($composer3, "715@35048L226");
                    Modifier modifier$iv2 = InteractiveComponentSizeKt.minimumInteractiveComponentSize(LayoutIdKt.layoutId(Modifier.INSTANCE, "Trailing"));
                    Alignment contentAlignment$iv2 = Alignment.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                    int $changed$iv$iv3 = (48 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                    constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function11 = constructor4;
                        $composer3.createNode(function11);
                    } else {
                        function11 = constructor4;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i5 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 479587178, "C719@35246L10:OutlinedTextField.kt#jmzs0o");
                    function6.invoke($composer3, Integer.valueOf(($dirty >> 15) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    $composer3.startReplaceGroup(1302508491);
                }
                $composer3.endReplaceGroup();
                startTextFieldPadding = PaddingKt.calculateStartPadding(paddingValues, layoutDirection);
                endTextFieldPadding = PaddingKt.calculateEndPadding(paddingValues, layoutDirection);
                Modifier.Companion companion = Modifier.INSTANCE;
                if (function5 != null) {
                    float other$iv = TextFieldImplKt.getHorizontalIconPadding();
                    float other$iv2 = Dp.m7905constructorimpl(startTextFieldPadding - other$iv);
                    float minimumValue$iv = Dp.m7905constructorimpl(0);
                    float $this$coerceAtLeast_u2dYgX7TsA$iv = Dp.m7905constructorimpl(RangesKt.coerceAtLeast(other$iv2, minimumValue$iv));
                    f = $this$coerceAtLeast_u2dYgX7TsA$iv;
                } else {
                    f = startTextFieldPadding;
                }
                if (function6 != null) {
                    float other$iv3 = TextFieldImplKt.getHorizontalIconPadding();
                    float other$iv4 = Dp.m7905constructorimpl(endTextFieldPadding - other$iv3);
                    float minimumValue$iv2 = Dp.m7905constructorimpl(0);
                    float $this$coerceAtLeast_u2dYgX7TsA$iv2 = Dp.m7905constructorimpl(RangesKt.coerceAtLeast(other$iv4, minimumValue$iv2));
                    f2 = $this$coerceAtLeast_u2dYgX7TsA$iv2;
                } else {
                    f2 = endTextFieldPadding;
                }
                padding = PaddingKt.m823paddingqDBjuR0$default(companion, f, 0.0f, f2, 0.0f, 10, null);
                if (function3 != null) {
                    $composer3.startReplaceGroup(1338367152);
                    ComposerKt.sourceInformation($composer3, "741@36162L59");
                    function3.invoke(LayoutIdKt.layoutId(Modifier.INSTANCE, "Hint").then(padding), $composer3, Integer.valueOf(($dirty >> 3) & 112));
                } else {
                    $composer3.startReplaceGroup(1302508491);
                }
                $composer3.endReplaceGroup();
                Modifier modifier$iv3 = LayoutIdKt.layoutId(Modifier.INSTANCE, "TextField").then(padding);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, true);
                int $changed$iv$iv4 = (384 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv3 = (($changed$iv$iv4 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function9 = constructor2;
                    $composer3.createNode(function9);
                } else {
                    function9 = constructor2;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash4);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                int i6 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                int i7 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -842047077, "C748@36407L11:OutlinedTextField.kt#jmzs0o");
                function2.invoke($composer3, Integer.valueOf(($dirty >> 3) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (function4 != null) {
                    $composer3.startReplaceGroup(1338685429);
                    ComposerKt.sourceInformation($composer3, "752@36483L54");
                    Modifier modifier$iv4 = LayoutIdKt.layoutId(Modifier.INSTANCE, "Label");
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv5 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
                    int $changed$iv$iv5 = (6 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer3, modifier$iv4);
                    constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv4 = (($changed$iv$iv5 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function10 = constructor3;
                        $composer3.createNode(function10);
                    } else {
                        function10 = constructor3;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                        $changed$iv = 6;
                        $composer$iv = $composer3;
                    } else {
                        $changed$iv = 6;
                        $composer$iv = $composer3;
                        if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                        int i8 = ($changed$iv$iv$iv4 >> 6) & 14;
                        Composer $composer$iv2 = $composer$iv;
                        ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                        int i9 = (($changed$iv >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer$iv2, 1905963083, "C752@36528L7:OutlinedTextField.kt#jmzs0o");
                        function8 = function4;
                        function8.invoke($composer$iv2, Integer.valueOf(($dirty >> 9) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                        $composer$iv.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    }
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash5);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                    int i10 = ($changed$iv$iv$iv4 >> 6) & 14;
                    Composer $composer$iv3 = $composer$iv;
                    ComposerKt.sourceInformationMarkerStart($composer$iv3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                    int i11 = (($changed$iv >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv3, 1905963083, "C752@36528L7:OutlinedTextField.kt#jmzs0o");
                    function8 = function4;
                    function8.invoke($composer$iv3, Integer.valueOf(($dirty >> 9) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                    $composer$iv.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                } else {
                    function8 = function4;
                    $composer3.startReplaceGroup(1302508491);
                }
                $composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0839: INVOKE (r15v6 ?? I:??[OBJECT, ARRAY]) INTERFACE call: androidx.compose.runtime.Composer.endNode():void A[MD:():void (m)] (LINE:1466) in method: androidx.compose.material.OutlinedTextFieldKt.OutlinedTextFieldLayout(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function3<? super androidx.compose.ui.Modifier, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, boolean, float, kotlin.jvm.functions.Function1<? super androidx.compose.ui.geometry.Size, kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int, int):void, file: classes.dex
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
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r15v6 ??
                    	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                    */
                /*
                    Method dump skipped, instruction units count: 2174
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.OutlinedTextFieldKt.OutlinedTextFieldLayout(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, float, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2, androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int, int):void");
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX INFO: renamed from: calculateWidth-O3s9Psw, reason: not valid java name */
            public static final int m1880calculateWidthO3s9Psw(int leadingPlaceableWidth, int trailingPlaceableWidth, int textFieldPlaceableWidth, int labelPlaceableWidth, int placeholderPlaceableWidth, float animationProgress, long j, float density, PaddingValues paddingValues) {
                int middleSection = Math.max(textFieldPlaceableWidth, Math.max(MathHelpersKt.lerp(labelPlaceableWidth, 0, animationProgress), placeholderPlaceableWidth));
                int wrappedWidth = leadingPlaceableWidth + middleSection + trailingPlaceableWidth;
                float arg0$iv = paddingValues.mo769calculateLeftPaddingu2uoSUM(LayoutDirection.Ltr);
                float other$iv = paddingValues.mo770calculateRightPaddingu2uoSUM(LayoutDirection.Ltr);
                float labelHorizontalPadding = Dp.m7905constructorimpl(arg0$iv + other$iv) * density;
                int focusedLabelWidth = MathKt.roundToInt((labelPlaceableWidth + labelHorizontalPadding) * animationProgress);
                return ConstraintsKt.m7875constrainWidthK40F9xA(j, Math.max(wrappedWidth, focusedLabelWidth));
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX INFO: renamed from: calculateHeight-O3s9Psw, reason: not valid java name */
            public static final int m1879calculateHeightO3s9Psw(int leadingPlaceableHeight, int trailingPlaceableHeight, int textFieldPlaceableHeight, int labelPlaceableHeight, int placeholderPlaceableHeight, float animationProgress, long j, float density, PaddingValues paddingValues) {
                int inputFieldHeight = Math.max(textFieldPlaceableHeight, Math.max(placeholderPlaceableHeight, MathHelpersKt.lerp(labelPlaceableHeight, 0, animationProgress)));
                float topPadding = paddingValues.getTop() * density;
                float actualTopPadding = MathHelpersKt.lerp(topPadding, Math.max(topPadding, labelPlaceableHeight / 2.0f), animationProgress);
                float bottomPadding = paddingValues.getBottom() * density;
                float middleSectionHeight = inputFieldHeight + actualTopPadding + bottomPadding;
                return ConstraintsKt.m7874constrainHeightK40F9xA(j, Math.max(leadingPlaceableHeight, Math.max(trailingPlaceableHeight, MathKt.roundToInt(middleSectionHeight))));
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void place(Placeable.PlacementScope $this$place, int height, int width, Placeable leadingPlaceable, Placeable trailingPlaceable, Placeable textFieldPlaceable, Placeable labelPlaceable, Placeable placeholderPlaceable, Placeable borderPlaceable, float animationProgress, boolean singleLine, float density, LayoutDirection layoutDirection, PaddingValues paddingValues) {
                int iAlign;
                int iAlign2;
                int startPositionY;
                float fWidthOrZero;
                int topPadding = MathKt.roundToInt(paddingValues.getTop() * density);
                int startPadding = MathKt.roundToInt(PaddingKt.calculateStartPadding(paddingValues, layoutDirection) * density);
                float iconPadding = TextFieldImplKt.getHorizontalIconPadding() * density;
                if (leadingPlaceable != null) {
                    Placeable.PlacementScope.placeRelative$default($this$place, leadingPlaceable, 0, Alignment.INSTANCE.getCenterVertically().align(leadingPlaceable.getHeight(), height), 0.0f, 4, null);
                }
                if (trailingPlaceable != null) {
                    Placeable.PlacementScope.placeRelative$default($this$place, trailingPlaceable, width - trailingPlaceable.getWidth(), Alignment.INSTANCE.getCenterVertically().align(trailingPlaceable.getHeight(), height), 0.0f, 4, null);
                }
                if (labelPlaceable != null) {
                    if (singleLine) {
                        startPositionY = Alignment.INSTANCE.getCenterVertically().align(labelPlaceable.getHeight(), height);
                    } else {
                        startPositionY = topPadding;
                    }
                    int positionY = MathHelpersKt.lerp(startPositionY, -(labelPlaceable.getHeight() / 2), animationProgress);
                    if (leadingPlaceable == null) {
                        fWidthOrZero = 0.0f;
                    } else {
                        fWidthOrZero = (TextFieldImplKt.widthOrZero(leadingPlaceable) - iconPadding) * (1.0f - animationProgress);
                    }
                    int positionX = MathKt.roundToInt(fWidthOrZero) + startPadding;
                    Placeable.PlacementScope.placeRelative$default($this$place, labelPlaceable, positionX, positionY, 0.0f, 4, null);
                }
                if (singleLine) {
                    iAlign = Alignment.INSTANCE.getCenterVertically().align(textFieldPlaceable.getHeight(), height);
                } else {
                    iAlign = topPadding;
                }
                int textVerticalPosition = Math.max(iAlign, TextFieldImplKt.heightOrZero(labelPlaceable) / 2);
                Placeable.PlacementScope.placeRelative$default($this$place, textFieldPlaceable, TextFieldImplKt.widthOrZero(leadingPlaceable), textVerticalPosition, 0.0f, 4, null);
                if (placeholderPlaceable != null) {
                    if (singleLine) {
                        iAlign2 = Alignment.INSTANCE.getCenterVertically().align(placeholderPlaceable.getHeight(), height);
                    } else {
                        iAlign2 = topPadding;
                    }
                    int placeholderVerticalPosition = Math.max(iAlign2, TextFieldImplKt.heightOrZero(labelPlaceable) / 2);
                    Placeable.PlacementScope.placeRelative$default($this$place, placeholderPlaceable, TextFieldImplKt.widthOrZero(leadingPlaceable), placeholderVerticalPosition, 0.0f, 4, null);
                }
                Placeable.PlacementScope.m6607place70tqf50$default($this$place, borderPlaceable, IntOffset.INSTANCE.m8044getZeronOccac(), 0.0f, 2, null);
            }

            /* JADX INFO: renamed from: outlineCutout-12SF9DM, reason: not valid java name */
            public static final Modifier m1881outlineCutout12SF9DM(Modifier $this$outlineCutout_u2d12SF9DM, final long j, final PaddingValues paddingValues) {
                return DrawModifierKt.drawWithContent($this$outlineCutout_u2d12SF9DM, new Function1() { // from class: androidx.compose.material.OutlinedTextFieldKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return OutlinedTextFieldKt.outlineCutout_12SF9DM$lambda$0(j, paddingValues, (ContentDrawScope) obj);
                    }
                });
            }

            /* JADX WARN: Failed to apply debug info
            jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r0v6 ??, new type: float
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyDebugInfo(TypeUpdate.java:77)
            	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
            	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:133)
            	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:79)
            	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
            	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
            Caused by: java.lang.NullPointerException
             */
            /* JADX WARN: Failed to calculate best type for var: r0v6 ??
            jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r0v6 ??, new type: float
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:159)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:136)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:241)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
            Caused by: java.lang.NullPointerException
             */
            /* JADX WARN: Failed to calculate best type for var: r0v6 ??
            jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r0v6 ??, new type: float
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
            Caused by: java.lang.NullPointerException
             */
            /* JADX WARN: Failed to calculate best type for var: r0v7 ??
            jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r0v7 ??, new type: float
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
            Caused by: java.lang.NullPointerException
             */
            /* JADX WARN: Failed to calculate best type for var: r12v0 ??
            jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r12v0 ??, new type: androidx.compose.ui.graphics.drawscope.DrawContext
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
            Caused by: java.lang.NullPointerException
             */
            /* JADX WARN: Failed to calculate best type for var: r18v0 ??
            jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r18v0 ??, new type: androidx.compose.ui.graphics.drawscope.DrawTransform
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
            Caused by: java.lang.NullPointerException
             */
            /* JADX WARN: Failed to calculate best type for var: r20v0 ??
            jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r20v0 ??, new type: float
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
            Caused by: java.lang.NullPointerException
             */
            /* JADX WARN: Failed to calculate best type for var: r22v0 ??
            jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r22v0 ??, new type: float
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
            Caused by: java.lang.NullPointerException
             */
            /* JADX WARN: Failed to calculate best type for var: r7v6 ??
            jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r7v6 ??, new type: androidx.compose.ui.graphics.drawscope.ContentDrawScope
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
            Caused by: java.lang.NullPointerException
             */
            /*  JADX ERROR: Types fix failed
                jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r0v6 ??, new type: float
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:186)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:245)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                Caused by: java.lang.NullPointerException
                */
            static final kotlin.Unit outlineCutout_12SF9DM$lambda$0(long r24, androidx.compose.foundation.layout.PaddingValues r26, androidx.compose.ui.graphics.drawscope.ContentDrawScope r27) {
                /*
                    Method dump skipped, instruction units count: 258
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.OutlinedTextFieldKt.outlineCutout_12SF9DM$lambda$0(long, androidx.compose.foundation.layout.PaddingValues, androidx.compose.ui.graphics.drawscope.ContentDrawScope):kotlin.Unit");
            }

            public static final long getOutlinedTextFieldTopPadding() {
                return OutlinedTextFieldTopPadding;
            }
        }
