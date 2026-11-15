package androidx.compose.material;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.CornerSizeKt;
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
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
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
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
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

/* JADX INFO: compiled from: TextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ä\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0085\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#H\u0007¢\u0006\u0002\u0010$\u001a\u0093\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00010(2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020\u00072\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u00100\u001a\u00020/2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0007¢\u0006\u0002\u00101\u001a\u0087\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00010(2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020\u00072\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0007¢\u0006\u0002\u00102\u001a\u0093\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010%\u001a\u0002032\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u00010(2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020\u00072\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u00100\u001a\u00020/2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0007¢\u0006\u0002\u00104\u001a\u0087\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010%\u001a\u0002032\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u00010(2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020\u00072\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0007¢\u0006\u0002\u00105\u001a\u009a\u0001\u00106\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0011\u00107\u001a\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\r2\u0013\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0019\u0010\u000e\u001a\u0015\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010(¢\u0006\u0002\b\r2\u0013\u00108\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0013\u00109\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\u0006\u0010-\u001a\u00020\u00072\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0001¢\u0006\u0002\u0010>\u001a?\u0010?\u001a\u00020/2\u0006\u0010@\u001a\u00020/2\u0006\u0010A\u001a\u00020/2\u0006\u0010B\u001a\u00020/2\u0006\u0010C\u001a\u00020/2\u0006\u0010D\u001a\u00020/2\u0006\u0010E\u001a\u00020FH\u0002¢\u0006\u0004\bG\u0010H\u001aW\u0010I\u001a\u00020/2\u0006\u0010J\u001a\u00020/2\u0006\u0010K\u001a\u00020\u00072\u0006\u0010L\u001a\u00020/2\u0006\u0010M\u001a\u00020/2\u0006\u0010N\u001a\u00020/2\u0006\u0010O\u001a\u00020/2\u0006\u0010E\u001a\u00020F2\u0006\u0010P\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0002¢\u0006\u0004\bQ\u0010R\u001at\u0010S\u001a\u00020\u0001*\u00020T2\u0006\u0010U\u001a\u00020/2\u0006\u0010V\u001a\u00020/2\u0006\u0010W\u001a\u00020X2\b\u0010Y\u001a\u0004\u0018\u00010X2\b\u0010Z\u001a\u0004\u0018\u00010X2\b\u0010[\u001a\u0004\u0018\u00010X2\b\u0010\\\u001a\u0004\u0018\u00010X2\u0006\u0010-\u001a\u00020\u00072\u0006\u0010]\u001a\u00020/2\u0006\u0010^\u001a\u00020/2\u0006\u0010:\u001a\u00020;2\u0006\u0010P\u001a\u00020;H\u0002\u001aZ\u0010_\u001a\u00020\u0001*\u00020T2\u0006\u0010U\u001a\u00020/2\u0006\u0010V\u001a\u00020/2\u0006\u0010`\u001a\u00020X2\b\u0010Z\u001a\u0004\u0018\u00010X2\b\u0010[\u001a\u0004\u0018\u00010X2\b\u0010\\\u001a\u0004\u0018\u00010X2\u0006\u0010-\u001a\u00020\u00072\u0006\u0010P\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0002\u001a\u0014\u0010a\u001a\u00020\u0005*\u00020\u00052\u0006\u0010b\u001a\u00020cH\u0000\"\u0016\u0010d\u001a\u00020eX\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bf\u0010g\"\u0016\u0010i\u001a\u00020eX\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bj\u0010g\"\u0016\u0010k\u001a\u00020eX\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bl\u0010g¨\u0006m"}, d2 = {"TextField", "", "state", "Landroidx/compose/foundation/text/input/TextFieldState;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "readOnly", "textStyle", "Landroidx/compose/ui/text/TextStyle;", MetadataField.LABEL, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "placeholder", "leadingIcon", "trailingIcon", "isError", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "outputTransformation", "Landroidx/compose/foundation/text/input/OutputTransformation;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "onKeyboardAction", "Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "lineLimits", "Landroidx/compose/foundation/text/input/TextFieldLineLimits;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material/TextFieldColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/foundation/text/input/OutputTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Landroidx/compose/foundation/text/input/TextFieldLineLimits;Landroidx/compose/foundation/ScrollState;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "value", "", "onValueChange", "Lkotlin/Function1;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "singleLine", "maxLines", "", "minLines", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/runtime/Composer;III)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/ui/text/input/TextFieldValue;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/runtime/Composer;III)V", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/runtime/Composer;III)V", "TextFieldLayout", "textField", "leading", "trailing", "animationProgress", "", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)V", "calculateWidth", "leadingWidth", "trailingWidth", "textFieldWidth", "labelWidth", "placeholderWidth", "constraints", "Landroidx/compose/ui/unit/Constraints;", "calculateWidth-VsPV1Ek", "(IIIIIJ)I", "calculateHeight", "textFieldHeight", "hasLabel", "labelBaseline", "leadingHeight", "trailingHeight", "placeholderHeight", "density", "calculateHeight-O3s9Psw", "(IZIIIIJFLandroidx/compose/foundation/layout/PaddingValues;)I", "placeWithLabel", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "width", "height", "textfieldPlaceable", "Landroidx/compose/ui/layout/Placeable;", "labelPlaceable", "placeholderPlaceable", "leadingPlaceable", "trailingPlaceable", "labelEndPosition", "textPosition", "placeWithoutLabel", "textPlaceable", "drawIndicatorLine", "indicatorBorder", "Landroidx/compose/foundation/BorderStroke;", "FirstBaselineOffset", "Landroidx/compose/ui/unit/Dp;", "getFirstBaselineOffset", "()F", "F", "TextFieldBottomPadding", "getTextFieldBottomPadding", "TextFieldTopPadding", "getTextFieldTopPadding", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextFieldKt {
    private static final float FirstBaselineOffset = Dp.m7905constructorimpl(20);
    private static final float TextFieldBottomPadding = Dp.m7905constructorimpl(10);
    private static final float TextFieldTopPadding = Dp.m7905constructorimpl(2);

    static final Unit TextField$lambda$12(TextFieldValue textFieldValue, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i3, int i4, int i5, Composer composer, int i6) {
        TextField(textFieldValue, (Function1<? super TextFieldValue, Unit>) function1, modifier, z, z2, textStyle, (Function2<? super Composer, ? super Integer, Unit>) function2, (Function2<? super Composer, ? super Integer, Unit>) function3, (Function2<? super Composer, ? super Integer, Unit>) function4, (Function2<? super Composer, ? super Integer, Unit>) function5, z3, visualTransformation, keyboardOptions, keyboardActions, z4, i, i2, mutableInteractionSource, shape, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
        return Unit.INSTANCE;
    }

    static final Unit TextField$lambda$14(TextFieldValue textFieldValue, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i2, int i3, int i4, Composer composer, int i5) {
        TextField(textFieldValue, function1, modifier, z, z2, textStyle, function2, function3, function4, function5, z3, visualTransformation, keyboardOptions, keyboardActions, z4, i, mutableInteractionSource, shape, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit TextField$lambda$2(TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, boolean z3, InputTransformation inputTransformation, OutputTransformation outputTransformation, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, TextFieldLineLimits textFieldLineLimits, ScrollState scrollState, Shape shape, TextFieldColors textFieldColors, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        TextField(textFieldState, modifier, z, z2, textStyle, (Function2<? super Composer, ? super Integer, Unit>) function2, (Function2<? super Composer, ? super Integer, Unit>) function3, (Function2<? super Composer, ? super Integer, Unit>) function4, (Function2<? super Composer, ? super Integer, Unit>) function5, z3, inputTransformation, outputTransformation, keyboardOptions, keyboardActionHandler, textFieldLineLimits, scrollState, shape, textFieldColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit TextField$lambda$6(String str, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i3, int i4, int i5, Composer composer, int i6) {
        TextField(str, (Function1<? super String, Unit>) function1, modifier, z, z2, textStyle, (Function2<? super Composer, ? super Integer, Unit>) function2, (Function2<? super Composer, ? super Integer, Unit>) function3, (Function2<? super Composer, ? super Integer, Unit>) function4, (Function2<? super Composer, ? super Integer, Unit>) function5, z3, visualTransformation, keyboardOptions, keyboardActions, z4, i, i2, mutableInteractionSource, shape, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
        return Unit.INSTANCE;
    }

    static final Unit TextField$lambda$8(String str, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i2, int i3, int i4, Composer composer, int i5) {
        TextField(str, function1, modifier, z, z2, textStyle, function2, function3, function4, function5, z3, visualTransformation, keyboardOptions, keyboardActions, z4, i, mutableInteractionSource, shape, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit TextFieldLayout$lambda$2(Modifier modifier, Function2 function2, Function2 function3, Function3 function4, Function2 function5, Function2 function6, boolean z, float f, PaddingValues paddingValues, int i, Composer composer, int i2) {
        TextFieldLayout(modifier, function2, function3, function4, function5, function6, z, f, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void TextField(final TextFieldState state, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, boolean isError, InputTransformation inputTransformation, OutputTransformation outputTransformation, KeyboardOptions keyboardOptions, KeyboardActionHandler onKeyboardAction, TextFieldLineLimits lineLimits, ScrollState scrollState, Shape shape, TextFieldColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
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
        boolean z2;
        boolean z3;
        TextFieldColors colors3;
        MutableInteractionSource interactionSource3;
        int $dirty3;
        int $dirty4;
        MutableInteractionSource interactionSource4;
        int $dirty5;
        int i4;
        Composer $composer4 = $composer.startRestartGroup(-345138714);
        ComposerKt.sourceInformation($composer4, "C(TextField)N(state,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,isError,inputTransformation,outputTransformation,keyboardOptions,onKeyboardAction,lineLimits,scrollState,shape,colors,interactionSource)209@11147L38,217@11492L20,204@10944L2203:TextField.kt#jmzs0o");
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
            ComposerKt.sourceInformation($composer4, "182@9813L7,193@10376L21,194@10436L14,195@10500L17");
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
                $composer3 = $composer4;
                z2 = false;
                function14 = function4;
                function15 = function5;
                isError3 = isError;
                inputTransformation3 = inputTransformation;
                outputTransformation3 = outputTransformation;
                keyboardOptions3 = keyboardOptions;
                onKeyboardAction3 = onKeyboardAction;
                lineLimits3 = lineLimits;
                scrollState3 = scrollState;
                shape3 = shape;
                $dirty3 = $dirty6;
                z3 = true;
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
                    shape3 = TextFieldDefaults.INSTANCE.getTextFieldShape($composer4, 6);
                    $dirty2 = $dirty1 & (-3670017);
                }
                if ((i & 131072) == 0) {
                    $composer3 = $composer4;
                    z2 = false;
                    z3 = true;
                    colors3 = colors;
                } else {
                    z3 = true;
                    z2 = false;
                    colors3 = TextFieldDefaults.INSTANCE.m1998textFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer4, 0, 0, 48, 2097151);
                    $composer3 = $composer4;
                    $dirty2 &= -29360129;
                }
                if (i19 == 0) {
                    interactionSource3 = interactionSource;
                    modifier2 = modifier4;
                    textStyle2 = textStyle4;
                    enabled2 = enabled4;
                    $dirty3 = $dirty;
                    $dirty4 = $dirty2;
                } else {
                    interactionSource3 = null;
                    modifier2 = modifier4;
                    textStyle2 = textStyle4;
                    enabled2 = enabled4;
                    $dirty3 = $dirty;
                    $dirty4 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-345138714, $dirty3, $dirty4, "androidx.compose.material.TextField (TextField.kt:197)");
            }
            if (interactionSource3 == null) {
                $composer3.startReplaceGroup(125297845);
                ComposerKt.sourceInformation($composer3, "199@10661L39");
                ComposerKt.sourceInformationMarkerStart($composer3, -965789459, "CC(remember):TextField.kt#9igjgp");
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
                $composer3.startReplaceGroup(-965790110);
                $composer3.endReplaceGroup();
                interactionSource4 = interactionSource3;
            }
            $composer3.startReplaceGroup(-965784364);
            ComposerKt.sourceInformation($composer3, "*201@10840L18");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle2.m7358getColor0d7_KjU();
            if ($this$takeOrElse_u2dDxMtmZc$iv != 16) {
                z2 = z3;
            }
            if (!z2) {
                $this$takeOrElse_u2dDxMtmZc$iv = colors3.textColor(enabled2, $composer3, (($dirty3 >> 6) & 14) | (($dirty4 >> 18) & 112)).getValue().m5107unboximpl();
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle2.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            TextFieldColors colors4 = colors3;
            boolean enabled5 = enabled2;
            boolean isError4 = isError3;
            int $dirty8 = $dirty4;
            Composer $composer5 = $composer3;
            BasicTextFieldKt.BasicTextField(state, SizeKt.m849defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(TextFieldDefaults.m1986indicatorLinegv0btCI$default(TextFieldDefaults.INSTANCE, modifier2, enabled5, isError4, interactionSource4, colors4, 0.0f, 0.0f, 48, null), isError4, Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1947getDefaultErrorMessageUdPEhr4(), $composer3, 6)), TextFieldDefaults.INSTANCE.m1993getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m1992getMinHeightD9Ej5fM()), enabled5, readOnly3, inputTransformation3, mergedTextStyle, keyboardOptions3, onKeyboardAction3, lineLimits3, (Function2<? super Density, ? super Function0<TextLayoutResult>, Unit>) null, interactionSource4, new SolidColor(colors4.cursorColor(isError4, $composer3, (($dirty3 >> 27) & 14) | (($dirty4 >> 18) & 112)).getValue().m5107unboximpl(), null), outputTransformation3, new AnonymousClass1(outputTransformation3, state, lineLimits3, enabled5, interactionSource4, isError4, function12, function13, function14, function15, shape3, colors4), scrollState3, $composer5, ($dirty3 & 14) | ($dirty3 & 896) | ($dirty3 & 7168) | (($dirty8 << 12) & 57344) | (($dirty8 << 12) & 3670016) | (($dirty8 << 12) & 29360128) | (($dirty8 << 12) & 234881024), (($dirty8 << 3) & 896) | (($dirty8 >> 3) & 57344), 512);
            $composer2 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource2 = interactionSource3;
            colors2 = colors4;
            textStyle3 = textStyle2;
            modifier3 = modifier2;
            enabled3 = enabled5;
            readOnly2 = readOnly3;
            function9 = function12;
            function10 = function13;
            function8 = function15;
            inputTransformation2 = inputTransformation3;
            outputTransformation2 = outputTransformation3;
            keyboardOptions2 = keyboardOptions3;
            onKeyboardAction2 = onKeyboardAction3;
            lineLimits2 = lineLimits3;
            scrollState2 = scrollState3;
            shape2 = shape3;
            isError2 = isError4;
            function11 = function14;
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextFieldKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldKt.TextField$lambda$2(state, modifier3, enabled3, readOnly2, textStyle3, function9, function10, function11, function8, isError2, inputTransformation2, outputTransformation2, keyboardOptions2, onKeyboardAction2, lineLimits2, scrollState2, shape2, colors2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material.TextFieldKt$TextField$1, reason: invalid class name */
    /* JADX INFO: compiled from: TextField.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0004H\n"}, d2 = {"<anonymous>", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass1 implements TextFieldDecorator {
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
        AnonymousClass1(OutputTransformation outputTransformation, TextFieldState textFieldState, TextFieldLineLimits textFieldLineLimits, boolean z, MutableInteractionSource mutableInteractionSource, boolean z2, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Shape shape, TextFieldColors textFieldColors) {
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

        static final Unit Decoration$lambda$2(AnonymousClass1 anonymousClass1, Function2 function2, int i, Composer composer, int i2) {
            anonymousClass1.Decoration(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
            return Unit.INSTANCE;
        }

        @Override // androidx.compose.foundation.text.input.TextFieldDecorator
        public final void Decoration(final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
            Composer $composer2;
            TextFieldBuffer textFieldBuffer;
            String textPostTransformation;
            Composer $composer3 = $composer.startRestartGroup(-1516988351);
            ComposerKt.sourceInformation($composer3, "C(Decoration)N(innerTextField)239@12524L606:TextField.kt#jmzs0o");
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
                    ComposerKt.traceEventStart(-1516988351, $dirty, -1, "androidx.compose.material.TextField.<no name provided>.Decoration (TextField.kt:226)");
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
                $composer2 = $composer3;
                String textPostTransformation2 = textPostTransformation;
                TextFieldDefaults.INSTANCE.TextFieldDecorationBox(textPostTransformation2, function2, this.$enabled, Intrinsics.areEqual(this.$lineLimits, TextFieldLineLimits.SingleLine.INSTANCE), VisualTransformation.INSTANCE.getNone(), this.$interactionSource, this.$isError, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$shape, this.$colors, null, $composer2, (($dirty << 3) & 112) | 24576, 24576, 8192);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextFieldKt$TextField$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return TextFieldKt.AnonymousClass1.Decoration$lambda$2(this.f$0, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
    }

    public static final void TextField(final String value, final Function1<? super String, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int i) {
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
        VisualTransformation visualTransformation4;
        KeyboardOptions keyboardOptions4;
        boolean singleLine4;
        int maxLines4;
        int $dirty5;
        MutableInteractionSource interactionSource4;
        int $dirty6;
        Composer $composer4 = $composer.startRestartGroup(179130848);
        ComposerKt.sourceInformation($composer4, "C(TextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,isError,visualTransformation,keyboardOptions,keyboardActions,singleLine,maxLines,minLines,interactionSource,shape,colors)356@19163L38,365@19547L20,374@19901L787,351@18960L1735:TextField.kt#jmzs0o");
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
            ComposerKt.sourceInformation($composer4, "329@17865L7,342@18509L14,343@18573L17");
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
                $composer3 = $composer4;
                function12 = function3;
                function13 = function4;
                function14 = function5;
                isError3 = isError;
                visualTransformation4 = visualTransformation;
                keyboardActions4 = keyboardActions;
                maxLines4 = maxLines;
                minLines3 = minLines;
                interactionSource3 = interactionSource;
                shape3 = shape;
                colors3 = colors;
                $dirty4 = $dirty14;
                readOnly4 = z;
                function11 = function6;
                singleLine4 = singleLine;
                $dirty5 = $dirty1;
                textStyle5 = textStyle2;
                keyboardOptions4 = keyboardOptions;
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
                    shape3 = TextFieldDefaults.INSTANCE.getTextFieldShape($composer4, 6);
                    $dirty3 = $dirty2 & (-234881025);
                }
                if ((i & 524288) != 0) {
                    colors3 = TextFieldDefaults.INSTANCE.m1998textFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer4, 0, 0, 48, 2097151);
                    $composer3 = $composer4;
                    keyboardActions4 = keyboardActions3;
                    modifier2 = modifier4;
                    enabled2 = enabled4;
                    readOnly4 = readOnly3;
                    textStyle5 = textStyle4;
                    $dirty4 = $dirty;
                    visualTransformation4 = visualTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    singleLine4 = singleLine3;
                    maxLines4 = maxLines3;
                    $dirty5 = $dirty3 & (-1879048193);
                } else {
                    $composer3 = $composer4;
                    colors3 = colors;
                    keyboardActions4 = keyboardActions3;
                    modifier2 = modifier4;
                    enabled2 = enabled4;
                    readOnly4 = readOnly3;
                    textStyle5 = textStyle4;
                    $dirty4 = $dirty;
                    visualTransformation4 = visualTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    singleLine4 = singleLine3;
                    maxLines4 = maxLines3;
                    $dirty5 = $dirty3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(179130848, $dirty4, $dirty5, "androidx.compose.material.TextField (TextField.kt:344)");
            }
            if (interactionSource3 == null) {
                $composer3.startReplaceGroup(2138724187);
                ComposerKt.sourceInformation($composer3, "346@18677L39");
                ComposerKt.sourceInformationMarkerStart($composer3, 346085767, "CC(remember):TextField.kt#9igjgp");
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
                $composer3.startReplaceGroup(346085116);
                $composer3.endReplaceGroup();
                interactionSource4 = interactionSource3;
            }
            $composer3.startReplaceGroup(346090862);
            ComposerKt.sourceInformation($composer3, "*348@18856L18");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle5.m7358getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                $this$takeOrElse_u2dDxMtmZc$iv = colors3.textColor(enabled2, $composer3, (($dirty4 >> 9) & 14) | (($dirty5 >> 24) & 112)).getValue().m5107unboximpl();
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle5.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            final boolean enabled5 = enabled2;
            Modifier modifier5 = modifier2;
            final boolean isError4 = isError3;
            TextStyle textStyle6 = textStyle5;
            Modifier modifierM849defaultMinSizeVpY3zN4 = SizeKt.m849defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(TextFieldDefaults.m1986indicatorLinegv0btCI$default(TextFieldDefaults.INSTANCE, modifier5, enabled5, isError4, interactionSource4, colors3, 0.0f, 0.0f, 48, null), isError4, Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1947getDefaultErrorMessageUdPEhr4(), $composer3, 6)), TextFieldDefaults.INSTANCE.m1993getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m1992getMinHeightD9Ej5fM());
            SolidColor solidColor = new SolidColor(colors3.cursorColor(isError4, $composer3, ($dirty5 & 14) | (($dirty5 >> 24) & 112)).getValue().m5107unboximpl(), null);
            final MutableInteractionSource interactionSource5 = interactionSource4;
            final TextFieldColors colors4 = colors3;
            final boolean isError5 = singleLine4;
            final VisualTransformation visualTransformation5 = visualTransformation4;
            final Function2<? super Composer, ? super Integer, Unit> function15 = function11;
            final Function2<? super Composer, ? super Integer, Unit> function16 = function12;
            final Function2<? super Composer, ? super Integer, Unit> function17 = function13;
            final Function2<? super Composer, ? super Integer, Unit> function18 = function14;
            final Shape shape4 = shape3;
            Composer $composer5 = $composer3;
            int minLines4 = minLines3;
            BasicTextFieldKt.BasicTextField(value, function1, modifierM849defaultMinSizeVpY3zN4, enabled2, readOnly4, mergedTextStyle, keyboardOptions4, keyboardActions4, singleLine4, maxLines4, minLines4, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, interactionSource5, solidColor, ComposableLambdaKt.rememberComposableLambda(-83351293, true, new Function3() { // from class: androidx.compose.material.TextFieldKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TextFieldKt.TextField$lambda$5(value, enabled5, isError5, visualTransformation5, interactionSource5, isError4, function15, function16, function17, function18, shape4, colors4, (Function2) obj, (Composer) obj2, ((Integer) obj3).intValue());
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
            enabled3 = enabled2;
            readOnly2 = readOnly4;
            maxLines2 = maxLines4;
            minLines2 = minLines4;
            function8 = function11;
            function7 = function12;
            function9 = function13;
            interactionSource2 = interactionSource3;
            shape2 = shape3;
            keyboardOptions2 = keyboardOptions4;
            keyboardActions2 = keyboardActions4;
            function10 = function14;
            isError2 = isError4;
            modifier3 = modifier5;
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextFieldKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldKt.TextField$lambda$6(value, function1, modifier3, enabled3, readOnly2, textStyle3, function8, function7, function9, function10, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, singleLine2, maxLines2, minLines2, interactionSource2, shape2, colors2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit TextField$lambda$5(String $value, boolean $enabled, boolean $singleLine, VisualTransformation $visualTransformation, MutableInteractionSource $interactionSource, boolean $isError, Function2 $label, Function2 $placeholder, Function2 $leadingIcon, Function2 $trailingIcon, Shape $shape, TextFieldColors $colors, Function2 innerTextField, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(innerTextField)376@20048L626:TextField.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changedInstance(innerTextField) ? 4 : 2;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-83351293, $dirty, -1, "androidx.compose.material.TextField.<anonymous> (TextField.kt:376)");
            }
            TextFieldDefaults.INSTANCE.TextFieldDecorationBox($value, innerTextField, $enabled, $singleLine, $visualTransformation, $interactionSource, $isError, $label, $placeholder, $leadingIcon, $trailingIcon, $shape, $colors, null, $composer, ($dirty << 3) & 112, 24576, 8192);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with minLines instead")
    public static final /* synthetic */ void TextField(final String value, final Function1 onValueChange, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2 label, Function2 placeholder, Function2 leadingIcon, Function2 trailingIcon, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int i) {
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
        Composer $composer3 = $composer.startRestartGroup(378210749);
        ComposerKt.sourceInformation($composer3, "C(TextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,isError,visualTransformation,keyboardOptions,keyboardActions,singleLine,maxLines,interactionSource,shape,colors)422@21837L409:TextField.kt#jmzs0o");
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
            ComposerKt.sourceInformation($composer3, "406@21049L7,417@21603L39,419@21685L6,420@21810L17");
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
                    ComposerKt.sourceInformationMarkerStart($composer3, -1611665948, "CC(remember):TextField.kt#9igjgp");
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
                    shape3 = CornerBasedShape.copy$default(MaterialTheme.INSTANCE.getShapes($composer3, 6).getSmall(), null, null, CornerSizeKt.getZeroCornerSize(), CornerSizeKt.getZeroCornerSize(), 3, null);
                    $dirty3 = $dirty2 & (-29360129);
                } else {
                    shape3 = shape;
                    $dirty3 = $dirty2;
                }
                if ((i & 262144) != 0) {
                    $composer2 = $composer3;
                    keyboardActions4 = keyboardActions3;
                    modifier4 = modifier5;
                    colors3 = TextFieldDefaults.INSTANCE.m1998textFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 0, 48, 2097151);
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
                ComposerKt.traceEventStart(378210749, $dirty4, $dirty1, "androidx.compose.material.TextField (TextField.kt:421)");
            }
            TextField(value, (Function1<? super String, Unit>) onValueChange, modifier4, enabled2, readOnly3, textStyle5, (Function2<? super Composer, ? super Integer, Unit>) label4, (Function2<? super Composer, ? super Integer, Unit>) placeholder3, (Function2<? super Composer, ? super Integer, Unit>) leadingIcon3, (Function2<? super Composer, ? super Integer, Unit>) trailingIcon2, isError3, visualTransformation3, keyboardOptions3, keyboardActions4, singleLine3, maxLines3, 1, interactionSource4, shape4, colors3, $composer2, ($dirty4 & 14) | ($dirty4 & 112) | ($dirty4 & 896) | ($dirty4 & 7168) | (57344 & $dirty4) | (458752 & $dirty4) | (3670016 & $dirty4) | (29360128 & $dirty4) | (234881024 & $dirty4) | (1879048192 & $dirty4), ($dirty1 & 14) | 1572864 | ($dirty1 & 112) | ($dirty1 & 896) | ($dirty1 & 7168) | (57344 & $dirty1) | (458752 & $dirty1) | (($dirty1 << 3) & 29360128) | (($dirty1 << 3) & 234881024) | (($dirty1 << 3) & 1879048192), 0);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextFieldKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldKt.TextField$lambda$8(value, onValueChange, modifier3, enabled3, readOnly2, textStyle3, label2, placeholder2, leadingIcon2, label3, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, singleLine2, maxLines2, interactionSource2, shape2, colors2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void TextField(final TextFieldValue value, final Function1<? super TextFieldValue, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int i) {
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
        VisualTransformation visualTransformation4;
        KeyboardOptions keyboardOptions4;
        boolean singleLine4;
        int maxLines4;
        int $dirty5;
        MutableInteractionSource interactionSource4;
        int $dirty6;
        Composer $composer4 = $composer.startRestartGroup(-1470183117);
        ComposerKt.sourceInformation($composer4, "C(TextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,isError,visualTransformation,keyboardOptions,keyboardActions,singleLine,maxLines,minLines,interactionSource,shape,colors)546@28465L38,555@28849L20,564@29203L792,541@28262L1740:TextField.kt#jmzs0o");
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
            ComposerKt.sourceInformation($composer4, "519@27167L7,532@27811L14,533@27875L17");
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
                $composer3 = $composer4;
                function12 = function3;
                function13 = function4;
                function14 = function5;
                isError3 = isError;
                visualTransformation4 = visualTransformation;
                keyboardActions4 = keyboardActions;
                maxLines4 = maxLines;
                minLines3 = minLines;
                interactionSource3 = interactionSource;
                shape3 = shape;
                colors3 = colors;
                $dirty4 = $dirty14;
                readOnly4 = z;
                function11 = function6;
                singleLine4 = singleLine;
                $dirty5 = $dirty1;
                textStyle5 = textStyle2;
                keyboardOptions4 = keyboardOptions;
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
                    shape3 = TextFieldDefaults.INSTANCE.getTextFieldShape($composer4, 6);
                    $dirty3 = $dirty2 & (-234881025);
                }
                if ((i & 524288) != 0) {
                    colors3 = TextFieldDefaults.INSTANCE.m1998textFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer4, 0, 0, 48, 2097151);
                    $composer3 = $composer4;
                    keyboardActions4 = keyboardActions3;
                    modifier2 = modifier4;
                    enabled2 = enabled4;
                    readOnly4 = readOnly3;
                    textStyle5 = textStyle4;
                    $dirty4 = $dirty;
                    visualTransformation4 = visualTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    singleLine4 = singleLine3;
                    maxLines4 = maxLines3;
                    $dirty5 = $dirty3 & (-1879048193);
                } else {
                    $composer3 = $composer4;
                    colors3 = colors;
                    keyboardActions4 = keyboardActions3;
                    modifier2 = modifier4;
                    enabled2 = enabled4;
                    readOnly4 = readOnly3;
                    textStyle5 = textStyle4;
                    $dirty4 = $dirty;
                    visualTransformation4 = visualTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    singleLine4 = singleLine3;
                    maxLines4 = maxLines3;
                    $dirty5 = $dirty3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1470183117, $dirty4, $dirty5, "androidx.compose.material.TextField (TextField.kt:534)");
            }
            if (interactionSource3 == null) {
                $composer3.startReplaceGroup(1852274984);
                ComposerKt.sourceInformation($composer3, "536@27979L39");
                ComposerKt.sourceInformationMarkerStart($composer3, 198298138, "CC(remember):TextField.kt#9igjgp");
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
                $composer3.startReplaceGroup(198297487);
                $composer3.endReplaceGroup();
                interactionSource4 = interactionSource3;
            }
            $composer3.startReplaceGroup(198303233);
            ComposerKt.sourceInformation($composer3, "*538@28158L18");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle5.m7358getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                $this$takeOrElse_u2dDxMtmZc$iv = colors3.textColor(enabled2, $composer3, (($dirty4 >> 9) & 14) | (($dirty5 >> 24) & 112)).getValue().m5107unboximpl();
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle5.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            final boolean enabled5 = enabled2;
            Modifier modifier5 = modifier2;
            final boolean isError4 = isError3;
            TextStyle textStyle6 = textStyle5;
            Modifier modifierM849defaultMinSizeVpY3zN4 = SizeKt.m849defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(TextFieldDefaults.m1986indicatorLinegv0btCI$default(TextFieldDefaults.INSTANCE, modifier5, enabled5, isError4, interactionSource4, colors3, 0.0f, 0.0f, 48, null), isError4, Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1947getDefaultErrorMessageUdPEhr4(), $composer3, 6)), TextFieldDefaults.INSTANCE.m1993getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m1992getMinHeightD9Ej5fM());
            SolidColor solidColor = new SolidColor(colors3.cursorColor(isError4, $composer3, ($dirty5 & 14) | (($dirty5 >> 24) & 112)).getValue().m5107unboximpl(), null);
            final MutableInteractionSource interactionSource5 = interactionSource4;
            final TextFieldColors colors4 = colors3;
            final boolean isError5 = singleLine4;
            final VisualTransformation visualTransformation5 = visualTransformation4;
            final Function2<? super Composer, ? super Integer, Unit> function15 = function11;
            final Function2<? super Composer, ? super Integer, Unit> function16 = function12;
            final Function2<? super Composer, ? super Integer, Unit> function17 = function13;
            final Function2<? super Composer, ? super Integer, Unit> function18 = function14;
            final Shape shape4 = shape3;
            Composer $composer5 = $composer3;
            int minLines4 = minLines3;
            BasicTextFieldKt.BasicTextField(value, function1, modifierM849defaultMinSizeVpY3zN4, enabled2, readOnly4, mergedTextStyle, keyboardOptions4, keyboardActions4, singleLine4, maxLines4, minLines4, visualTransformation4, (Function1<? super TextLayoutResult, Unit>) null, interactionSource5, solidColor, ComposableLambdaKt.rememberComposableLambda(1565379926, true, new Function3() { // from class: androidx.compose.material.TextFieldKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TextFieldKt.TextField$lambda$11(value, enabled5, isError5, visualTransformation5, interactionSource5, isError4, function15, function16, function17, function18, shape4, colors4, (Function2) obj, (Composer) obj2, ((Integer) obj3).intValue());
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
            enabled3 = enabled2;
            readOnly2 = readOnly4;
            maxLines2 = maxLines4;
            minLines2 = minLines4;
            function8 = function11;
            function7 = function12;
            function9 = function13;
            interactionSource2 = interactionSource3;
            shape2 = shape3;
            keyboardOptions2 = keyboardOptions4;
            keyboardActions2 = keyboardActions4;
            function10 = function14;
            isError2 = isError4;
            modifier3 = modifier5;
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextFieldKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldKt.TextField$lambda$12(value, function1, modifier3, enabled3, readOnly2, textStyle3, function8, function7, function9, function10, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, singleLine2, maxLines2, minLines2, interactionSource2, shape2, colors2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit TextField$lambda$11(TextFieldValue $value, boolean $enabled, boolean $singleLine, VisualTransformation $visualTransformation, MutableInteractionSource $interactionSource, boolean $isError, Function2 $label, Function2 $placeholder, Function2 $leadingIcon, Function2 $trailingIcon, Shape $shape, TextFieldColors $colors, Function2 innerTextField, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(innerTextField)566@29350L631:TextField.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changedInstance(innerTextField) ? 4 : 2;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1565379926, $dirty, -1, "androidx.compose.material.TextField.<anonymous> (TextField.kt:566)");
            }
            TextFieldDefaults.INSTANCE.TextFieldDecorationBox($value.getText(), innerTextField, $enabled, $singleLine, $visualTransformation, $interactionSource, $isError, $label, $placeholder, $leadingIcon, $trailingIcon, $shape, $colors, null, $composer, ($dirty << 3) & 112, 24576, 8192);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with minLines instead")
    public static final /* synthetic */ void TextField(final TextFieldValue value, final Function1 onValueChange, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2 label, Function2 placeholder, Function2 leadingIcon, Function2 trailingIcon, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int i) {
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
        Composer $composer3 = $composer.startRestartGroup(1181426320);
        ComposerKt.sourceInformation($composer3, "C(TextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,isError,visualTransformation,keyboardOptions,keyboardActions,singleLine,maxLines,interactionSource,shape,colors)611@31095L409:TextField.kt#jmzs0o");
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
            ComposerKt.sourceInformation($composer3, "596@30372L7,607@30926L39,608@31004L14,609@31068L17");
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
                    ComposerKt.sourceInformationMarkerStart($composer3, 1479352311, "CC(remember):TextField.kt#9igjgp");
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
                    shape3 = TextFieldDefaults.INSTANCE.getTextFieldShape($composer3, 6);
                    $dirty3 = $dirty2 & (-29360129);
                } else {
                    shape3 = shape;
                    $dirty3 = $dirty2;
                }
                if ((i & 262144) != 0) {
                    $composer2 = $composer3;
                    keyboardActions4 = keyboardActions3;
                    modifier4 = modifier5;
                    colors3 = TextFieldDefaults.INSTANCE.m1998textFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 0, 48, 2097151);
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
                ComposerKt.traceEventStart(1181426320, $dirty4, $dirty1, "androidx.compose.material.TextField (TextField.kt:610)");
            }
            TextField(value, (Function1<? super TextFieldValue, Unit>) onValueChange, modifier4, enabled2, readOnly3, textStyle5, (Function2<? super Composer, ? super Integer, Unit>) label4, (Function2<? super Composer, ? super Integer, Unit>) placeholder3, (Function2<? super Composer, ? super Integer, Unit>) leadingIcon3, (Function2<? super Composer, ? super Integer, Unit>) trailingIcon2, isError3, visualTransformation3, keyboardOptions3, keyboardActions4, singleLine3, maxLines3, 1, interactionSource4, shape4, colors3, $composer2, ($dirty4 & 14) | ($dirty4 & 112) | ($dirty4 & 896) | ($dirty4 & 7168) | (57344 & $dirty4) | (458752 & $dirty4) | (3670016 & $dirty4) | (29360128 & $dirty4) | (234881024 & $dirty4) | (1879048192 & $dirty4), ($dirty1 & 14) | 1572864 | ($dirty1 & 112) | ($dirty1 & 896) | ($dirty1 & 7168) | (57344 & $dirty1) | (458752 & $dirty1) | (($dirty1 << 3) & 29360128) | (($dirty1 << 3) & 234881024) | (($dirty1 << 3) & 1879048192), 0);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextFieldKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldKt.TextField$lambda$14(value, onValueChange, modifier3, enabled3, readOnly2, textStyle3, label2, placeholder2, leadingIcon2, label3, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, singleLine2, maxLines2, interactionSource2, shape2, colors2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void TextFieldLayout(final Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function3<? super Modifier, ? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final Function2<? super Composer, ? super Integer, Unit> function6, final boolean singleLine, final float animationProgress, final PaddingValues paddingValues, Composer $composer, final int $changed) {
        Function2<? super Composer, ? super Integer, Unit> function7;
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        float f;
        float f2;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function8;
        Function0<ComposeUiNode> function9;
        Function0<ComposeUiNode> function10;
        Composer $composer3 = $composer.startRestartGroup(-1595074580);
        ComposerKt.sourceInformation($composer3, "C(TextFieldLayout)N(modifier,textField,label,placeholder,leading,trailing,singleLine,animationProgress,paddingValues)652@32043L147,655@32238L7,656@32250L1959:TextField.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
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
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function6) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer3.changed(singleLine) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer3.changed(animationProgress) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty |= $composer3.changed(paddingValues) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute((38347923 & $dirty2) != 38347922, $dirty2 & 1)) {
            function7 = function2;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1595074580, $dirty2, -1, "androidx.compose.material.TextFieldLayout (TextField.kt:650)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 436665599, "CC(remember):TextField.kt#9igjgp");
            boolean invalid$iv = ((3670016 & $dirty2) == 1048576) | ((29360128 & $dirty2) == 8388608) | ((234881024 & $dirty2) == 67108864);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new TextFieldMeasurePolicy(singleLine, animationProgress, paddingValues);
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            MeasurePolicy measurePolicy = (TextFieldMeasurePolicy) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection = (LayoutDirection) objConsume;
            MeasurePolicy measurePolicy$iv = measurePolicy;
            int $changed$iv = ($dirty2 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv = $composer3.getCurrentCompositionLocalMap();
            $composer2 = $composer3;
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer3, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = (($changed$iv << 6) & 896) | 6;
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
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1444583530, "C699@33970L183:TextField.kt#jmzs0o");
            if (function5 != null) {
                $composer3.startReplaceGroup(-1444611617);
                ComposerKt.sourceInformation($composer3, "660@32358L224");
                Modifier modifier$iv = InteractiveComponentSizeKt.minimumInteractiveComponentSize(LayoutIdKt.layoutId(Modifier.INSTANCE, "Leading"));
                Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (48 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function10 = constructor2;
                    $composer3.createNode(function10);
                } else {
                    function10 = constructor2;
                    $composer3.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i3 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1684668010, "C664@32555L9:TextField.kt#jmzs0o");
                function5.invoke($composer3, Integer.valueOf(($dirty2 >> 12) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
            } else {
                $composer3.startReplaceGroup(-1476701825);
            }
            $composer3.endReplaceGroup();
            if (function6 != null) {
                $composer3.startReplaceGroup(-1444322883);
                ComposerKt.sourceInformation($composer3, "668@32649L226");
                Modifier modifier$iv2 = InteractiveComponentSizeKt.minimumInteractiveComponentSize(LayoutIdKt.layoutId(Modifier.INSTANCE, "Trailing"));
                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                int $changed$iv$iv3 = (48 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function9 = constructor3;
                    $composer3.createNode(function9);
                } else {
                    function9 = constructor3;
                    $composer3.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i5 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1222956578, "C672@32847L10:TextField.kt#jmzs0o");
                function6.invoke($composer3, Integer.valueOf(($dirty2 >> 15) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
            } else {
                $composer3.startReplaceGroup(-1476701825);
            }
            $composer3.endReplaceGroup();
            float startTextFieldPadding = PaddingKt.calculateStartPadding(paddingValues, layoutDirection);
            float endTextFieldPadding = PaddingKt.calculateEndPadding(paddingValues, layoutDirection);
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
            Modifier padding = PaddingKt.m823paddingqDBjuR0$default(companion, f, 0.0f, f2, 0.0f, 10, null);
            if (function4 != null) {
                $composer3.startReplaceGroup(-1443222972);
                ComposerKt.sourceInformation($composer3, "694@33763L59");
                function4.invoke(LayoutIdKt.layoutId(Modifier.INSTANCE, "Hint").then(padding), $composer3, Integer.valueOf(($dirty2 >> 6) & 112));
            } else {
                $composer3.startReplaceGroup(-1476701825);
            }
            $composer3.endReplaceGroup();
            if (function3 != null) {
                $composer3.startReplaceGroup(-1443101018);
                ComposerKt.sourceInformation($composer3, "697@33886L57");
                Modifier modifier$iv3 = LayoutIdKt.layoutId(Modifier.INSTANCE, "Label").then(padding);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                int $changed$iv$iv4 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv3 = (($changed$iv$iv4 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function8 = constructor4;
                    $composer3.createNode(function8);
                } else {
                    function8 = constructor4;
                    $composer3.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash4);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                int i6 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                int i7 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -674436289, "C697@33934L7:TextField.kt#jmzs0o");
                function3.invoke($composer3, Integer.valueOf(($dirty2 >> 6) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
            } else {
                $composer3.startReplaceGroup(-1476701825);
            }
            $composer3.endReplaceGroup();
            Modifier modifier$iv4 = LayoutIdKt.layoutId(Modifier.INSTANCE, "TextField").then(padding);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv5 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, true);
            int $changed$iv$iv5 = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer3, modifier$iv4);
            Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv4 = (($changed$iv$iv5 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor5;
                $composer3.createNode(function1);
            } else {
                function1 = constructor5;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash5);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = ($changed$iv$iv$iv4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
            int i9 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1270525839, "C703@34128L11:TextField.kt#jmzs0o");
            function7 = function2;
            function7.invoke($composer3, Integer.valueOf(($dirty2 >> 3) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Function2<? super Composer, ? super Integer, Unit> function11 = function7;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextFieldKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldKt.TextFieldLayout$lambda$2(modifier, function11, function3, function4, function5, function6, singleLine, animationProgress, paddingValues, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateWidth-VsPV1Ek, reason: not valid java name */
    public static final int m2009calculateWidthVsPV1Ek(int leadingWidth, int trailingWidth, int textFieldWidth, int labelWidth, int placeholderWidth, long j) {
        int middleSection = Math.max(textFieldWidth, Math.max(labelWidth, placeholderWidth));
        int wrappedWidth = leadingWidth + middleSection + trailingWidth;
        return ConstraintsKt.m7875constrainWidthK40F9xA(j, wrappedWidth);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateHeight-O3s9Psw, reason: not valid java name */
    public static final int m2008calculateHeightO3s9Psw(int textFieldHeight, boolean hasLabel, int labelBaseline, int leadingHeight, int trailingHeight, int placeholderHeight, long j, float density, PaddingValues paddingValues) {
        float middleSectionHeight;
        float paddingToLabel = TextFieldTopPadding * density;
        float topPaddingValue = paddingValues.getTop() * density;
        float bottomPaddingValue = paddingValues.getBottom() * density;
        int inputFieldHeight = Math.max(textFieldHeight, placeholderHeight);
        if (hasLabel) {
            middleSectionHeight = labelBaseline + paddingToLabel + inputFieldHeight + bottomPaddingValue;
        } else {
            middleSectionHeight = inputFieldHeight + topPaddingValue + bottomPaddingValue;
        }
        return ConstraintsKt.m7874constrainHeightK40F9xA(j, Math.max(MathKt.roundToInt(middleSectionHeight), Math.max(leadingHeight, trailingHeight)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeWithLabel(Placeable.PlacementScope $this$placeWithLabel, int width, int height, Placeable textfieldPlaceable, Placeable labelPlaceable, Placeable placeholderPlaceable, Placeable leadingPlaceable, Placeable trailingPlaceable, boolean singleLine, int labelEndPosition, int textPosition, float animationProgress, float density) {
        int startPosition;
        if (leadingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithLabel, leadingPlaceable, 0, Alignment.INSTANCE.getCenterVertically().align(leadingPlaceable.getHeight(), height), 0.0f, 4, null);
        }
        if (trailingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithLabel, trailingPlaceable, width - trailingPlaceable.getWidth(), Alignment.INSTANCE.getCenterVertically().align(trailingPlaceable.getHeight(), height), 0.0f, 4, null);
        }
        if (labelPlaceable != null) {
            if (singleLine) {
                startPosition = Alignment.INSTANCE.getCenterVertically().align(labelPlaceable.getHeight(), height);
            } else {
                startPosition = MathKt.roundToInt(TextFieldImplKt.getTextFieldPadding() * density);
            }
            int distance = startPosition - labelEndPosition;
            int positionY = startPosition - MathKt.roundToInt(distance * animationProgress);
            Placeable.PlacementScope.placeRelative$default($this$placeWithLabel, labelPlaceable, TextFieldImplKt.widthOrZero(leadingPlaceable), positionY, 0.0f, 4, null);
        }
        Placeable.PlacementScope.placeRelative$default($this$placeWithLabel, textfieldPlaceable, TextFieldImplKt.widthOrZero(leadingPlaceable), textPosition, 0.0f, 4, null);
        if (placeholderPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithLabel, placeholderPlaceable, TextFieldImplKt.widthOrZero(leadingPlaceable), textPosition, 0.0f, 4, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeWithoutLabel(Placeable.PlacementScope $this$placeWithoutLabel, int width, int height, Placeable textPlaceable, Placeable placeholderPlaceable, Placeable leadingPlaceable, Placeable trailingPlaceable, boolean singleLine, float density, PaddingValues paddingValues) {
        int textVerticalPosition;
        int placeholderVerticalPosition;
        int topPadding = MathKt.roundToInt(paddingValues.getTop() * density);
        if (leadingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithoutLabel, leadingPlaceable, 0, Alignment.INSTANCE.getCenterVertically().align(leadingPlaceable.getHeight(), height), 0.0f, 4, null);
        }
        if (trailingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$placeWithoutLabel, trailingPlaceable, width - trailingPlaceable.getWidth(), Alignment.INSTANCE.getCenterVertically().align(trailingPlaceable.getHeight(), height), 0.0f, 4, null);
        }
        if (singleLine) {
            textVerticalPosition = Alignment.INSTANCE.getCenterVertically().align(textPlaceable.getHeight(), height);
        } else {
            textVerticalPosition = topPadding;
        }
        Placeable.PlacementScope.placeRelative$default($this$placeWithoutLabel, textPlaceable, TextFieldImplKt.widthOrZero(leadingPlaceable), textVerticalPosition, 0.0f, 4, null);
        if (placeholderPlaceable != null) {
            if (singleLine) {
                placeholderVerticalPosition = Alignment.INSTANCE.getCenterVertically().align(placeholderPlaceable.getHeight(), height);
            } else {
                placeholderVerticalPosition = topPadding;
            }
            Placeable.PlacementScope.placeRelative$default($this$placeWithoutLabel, placeholderPlaceable, TextFieldImplKt.widthOrZero(leadingPlaceable), placeholderVerticalPosition, 0.0f, 4, null);
        }
    }

    public static final Modifier drawIndicatorLine(Modifier $this$drawIndicatorLine, final BorderStroke indicatorBorder) {
        final float strokeWidthDp = indicatorBorder.getWidth();
        return DrawModifierKt.drawWithContent($this$drawIndicatorLine, new Function1() { // from class: androidx.compose.material.TextFieldKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldKt.drawIndicatorLine$lambda$0(strokeWidthDp, indicatorBorder, (ContentDrawScope) obj);
            }
        });
    }

    static final Unit drawIndicatorLine$lambda$0(float $strokeWidthDp, BorderStroke $indicatorBorder, ContentDrawScope $this$drawWithContent) {
        $this$drawWithContent.drawContent();
        if (Dp.m7910equalsimpl0($strokeWidthDp, Dp.INSTANCE.m7923getHairlineD9Ej5fM())) {
            return Unit.INSTANCE;
        }
        float strokeWidth = $strokeWidthDp * $this$drawWithContent.getDensity();
        long arg0$iv = $this$drawWithContent.mo5671getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv & 4294967295L);
        float y = Float.intBitsToFloat(bits$iv$iv$iv) - (strokeWidth / 2.0f);
        Brush brush = $indicatorBorder.getBrush();
        long v1$iv$iv = Float.floatToRawIntBits(0.0f);
        long v2$iv$iv = Float.floatToRawIntBits(y);
        long jM4844constructorimpl = Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        long arg0$iv2 = $this$drawWithContent.mo5671getSizeNHjbRc();
        int bits$iv$iv$iv2 = (int) (arg0$iv2 >> 32);
        float x$iv = Float.intBitsToFloat(bits$iv$iv$iv2);
        long v1$iv$iv2 = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv2 = Float.floatToRawIntBits(y);
        DrawScope.m5656drawLine1RTmtNc$default($this$drawWithContent, brush, jM4844constructorimpl, Offset.m4844constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L)), strokeWidth, 0, null, 0.0f, null, 0, 496, null);
        return Unit.INSTANCE;
    }

    public static final float getFirstBaselineOffset() {
        return FirstBaselineOffset;
    }

    public static final float getTextFieldBottomPadding() {
        return TextFieldBottomPadding;
    }

    public static final float getTextFieldTopPadding() {
        return TextFieldTopPadding;
    }
}
