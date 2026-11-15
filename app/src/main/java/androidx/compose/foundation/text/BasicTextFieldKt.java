package androidx.compose.foundation.text;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.HoverInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.contextmenu.modifier.ToolbarRequesterImpl;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.KeyboardActionHandler;
import androidx.compose.foundation.text.input.OutputTransformation;
import androidx.compose.foundation.text.input.TextFieldDecorator;
import androidx.compose.foundation.text.input.TextFieldLineLimits;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.internal.CodepointTransformation;
import androidx.compose.foundation.text.input.internal.SingleLineCodepointTransformation;
import androidx.compose.foundation.text.input.internal.TextFieldCoreModifier;
import androidx.compose.foundation.text.input.internal.TextFieldTextLayoutModifier;
import androidx.compose.foundation.text.input.internal.TextLayoutState;
import androidx.compose.foundation.text.input.internal.TransformedTextFieldState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt;
import androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt;
import androidx.compose.foundation.text.selection.OffsetProvider;
import androidx.compose.foundation.text.selection.PlatformSelectionBehaviors;
import androidx.compose.foundation.text.selection.PlatformSelectionBehaviors_androidKt;
import androidx.compose.foundation.text.selection.SelectedTextType;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.Clipboard;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* JADX INFO: compiled from: BasicTextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ð\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aÛ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u001228\b\u0002\u0010\u0013\u001a2\u0012\u0004\u0012\u00020\u0015\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0014¢\u0006\u0002\b\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\b\b\u0002\u0010$\u001a\u00020%H\u0007¢\u0006\u0002\u0010&\u001añ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u001228\b\u0002\u0010\u0013\u001a2\u0012\u0004\u0012\u00020\u0015\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0014¢\u0006\u0002\b\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010)\u001a\u00020\u0007H\u0001¢\u0006\u0002\u0010*\u001a\u001c\u0010+\u001a\u00020\u0005*\u00020\u00052\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0002\u001a\u0015\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u00020-H\u0001¢\u0006\u0002\u00102\u001a\u0015\u00103\u001a\u00020\u00012\u0006\u00101\u001a\u00020-H\u0001¢\u0006\u0002\u00102\u001aî\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u00108\u001a\u0002092\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020\u00010;2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020\u00072\b\b\u0002\u0010?\u001a\u00020@2\b\b\u0002\u0010A\u001a\u00020@2\b\b\u0002\u0010B\u001a\u00020C2\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010;2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f23\b\u0002\u0010D\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\bE¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u00010;¢\u0006\u0002\bEH\u0007¢\u0006\u0002\u0010G\u001aî\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u00108\u001a\u00020H2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020\u00010;2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020\u00072\b\b\u0002\u0010?\u001a\u00020@2\b\b\u0002\u0010A\u001a\u00020@2\b\b\u0002\u0010B\u001a\u00020C2\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010;2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f23\b\u0002\u0010D\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\bE¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u00010;¢\u0006\u0002\bEH\u0007¢\u0006\u0002\u0010I\u001aâ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u00108\u001a\u0002092\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020\u00010;2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020\u00072\b\b\u0002\u0010?\u001a\u00020@2\b\b\u0002\u0010B\u001a\u00020C2\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010;2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f23\b\u0002\u0010D\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\bE¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u00010;¢\u0006\u0002\bEH\u0007¢\u0006\u0002\u0010J\u001aâ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u00108\u001a\u00020H2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020\u00010;2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020\u00072\b\b\u0002\u0010?\u001a\u00020@2\b\b\u0002\u0010B\u001a\u00020C2\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010;2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f23\b\u0002\u0010D\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\bE¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u00010;¢\u0006\u0002\bEH\u0007¢\u0006\u0002\u0010K\"\u000e\u00104\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u00105\u001a\u000206X\u0082\u0004¢\u0006\u0004\n\u0002\u00107¨\u0006L²\u0006\n\u0010M\u001a\u00020NX\u008a\u0084\u0002²\u0006\n\u0010O\u001a\u00020NX\u008a\u0084\u0002²\u0006\n\u0010P\u001a\u00020NX\u008a\u0084\u0002²\u0006\n\u0010Q\u001a\u00020HX\u008a\u008e\u0002²\u0006\n\u0010R\u001a\u000209X\u008a\u008e\u0002"}, d2 = {"BasicTextField", "", "state", "Landroidx/compose/foundation/text/input/TextFieldState;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "readOnly", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "onKeyboardAction", "Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "lineLimits", "Landroidx/compose/foundation/text/input/TextFieldLineLimits;", "onTextLayout", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Lkotlin/Function0;", "Landroidx/compose/ui/text/TextLayoutResult;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "getResult", "Lkotlin/ExtensionFunctionType;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "outputTransformation", "Landroidx/compose/foundation/text/input/OutputTransformation;", "decorator", "Landroidx/compose/foundation/text/input/TextFieldDecorator;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Landroidx/compose/foundation/text/input/TextFieldLineLimits;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/foundation/text/input/OutputTransformation;Landroidx/compose/foundation/text/input/TextFieldDecorator;Landroidx/compose/foundation/ScrollState;Landroidx/compose/runtime/Composer;III)V", "codepointTransformation", "Landroidx/compose/foundation/text/input/internal/CodepointTransformation;", "isPassword", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Landroidx/compose/foundation/text/input/TextFieldLineLimits;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/foundation/text/input/internal/CodepointTransformation;Landroidx/compose/foundation/text/input/OutputTransformation;Landroidx/compose/foundation/text/input/TextFieldDecorator;Landroidx/compose/foundation/ScrollState;ZLandroidx/compose/runtime/Composer;III)V", "addContextMenuComponents", "textFieldSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "TextFieldCursorHandle", "selectionState", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/runtime/Composer;I)V", "TextFieldSelectionHandles", "DefaultTextFieldDecorator", "MinTouchTargetSizeForHandles", "Landroidx/compose/ui/unit/DpSize;", "J", "value", "", "onValueChange", "Lkotlin/Function1;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "singleLine", "maxLines", "", "minLines", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "decorationBox", "Landroidx/compose/runtime/Composable;", "innerTextField", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/ui/text/input/TextFieldValue;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "foundation", "cursorHandleState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldHandleState;", "startHandleState", "endHandleState", "textFieldValueState", "lastTextValue"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BasicTextFieldKt {
    private static final TextFieldDecorator DefaultTextFieldDecorator = BasicTextFieldKt$DefaultTextFieldDecorator$1.INSTANCE;
    private static final long MinTouchTargetSizeForHandles = DpKt.m7927DpSizeYgX7TsA(Dp.m7905constructorimpl(40), Dp.m7905constructorimpl(40));

    static final Unit BasicTextField$lambda$0(TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, TextFieldLineLimits textFieldLineLimits, Function2 function2, MutableInteractionSource mutableInteractionSource, Brush brush, OutputTransformation outputTransformation, TextFieldDecorator textFieldDecorator, ScrollState scrollState, int i, int i2, int i3, Composer composer, int i4) {
        BasicTextField(textFieldState, modifier, z, z2, inputTransformation, textStyle, keyboardOptions, keyboardActionHandler, textFieldLineLimits, (Function2<? super Density, ? super Function0<TextLayoutResult>, Unit>) function2, mutableInteractionSource, brush, outputTransformation, textFieldDecorator, scrollState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit BasicTextField$lambda$15(TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, TextFieldLineLimits textFieldLineLimits, Function2 function2, MutableInteractionSource mutableInteractionSource, Brush brush, CodepointTransformation codepointTransformation, OutputTransformation outputTransformation, TextFieldDecorator textFieldDecorator, ScrollState scrollState, boolean z3, int i, int i2, int i3, Composer composer, int i4) {
        BasicTextField(textFieldState, modifier, z, z2, inputTransformation, textStyle, keyboardOptions, keyboardActionHandler, textFieldLineLimits, function2, mutableInteractionSource, brush, codepointTransformation, outputTransformation, textFieldDecorator, scrollState, z3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit BasicTextField$lambda$25(String str, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z3, int i, int i2, VisualTransformation visualTransformation, Function1 function2, MutableInteractionSource mutableInteractionSource, Brush brush, Function3 function3, int i3, int i4, int i5, Composer composer, int i6) {
        BasicTextField(str, (Function1<? super String, Unit>) function1, modifier, z, z2, textStyle, keyboardOptions, keyboardActions, z3, i, i2, visualTransformation, (Function1<? super TextLayoutResult, Unit>) function2, mutableInteractionSource, brush, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) function3, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
        return Unit.INSTANCE;
    }

    static final Unit BasicTextField$lambda$28(TextFieldValue textFieldValue, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z3, int i, int i2, VisualTransformation visualTransformation, Function1 function2, MutableInteractionSource mutableInteractionSource, Brush brush, Function3 function3, int i3, int i4, int i5, Composer composer, int i6) {
        BasicTextField(textFieldValue, (Function1<? super TextFieldValue, Unit>) function1, modifier, z, z2, textStyle, keyboardOptions, keyboardActions, z3, i, i2, visualTransformation, (Function1<? super TextLayoutResult, Unit>) function2, mutableInteractionSource, brush, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) function3, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
        return Unit.INSTANCE;
    }

    static final Unit BasicTextField$lambda$31(String str, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z3, int i, VisualTransformation visualTransformation, Function1 function2, MutableInteractionSource mutableInteractionSource, Brush brush, Function3 function3, int i2, int i3, int i4, Composer composer, int i5) {
        BasicTextField(str, function1, modifier, z, z2, textStyle, keyboardOptions, keyboardActions, z3, i, visualTransformation, function2, mutableInteractionSource, brush, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit BasicTextField$lambda$34(TextFieldValue textFieldValue, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z3, int i, VisualTransformation visualTransformation, Function1 function2, MutableInteractionSource mutableInteractionSource, Brush brush, Function3 function3, int i2, int i3, int i4, Composer composer, int i5) {
        BasicTextField(textFieldValue, function1, modifier, z, z2, textStyle, keyboardOptions, keyboardActions, z3, i, visualTransformation, function2, mutableInteractionSource, brush, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit TextFieldCursorHandle$lambda$4(TextFieldSelectionState textFieldSelectionState, int i, Composer composer, int i2) {
        TextFieldCursorHandle(textFieldSelectionState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TextFieldSelectionHandles$lambda$8(TextFieldSelectionState textFieldSelectionState, int i, Composer composer, int i2) {
        TextFieldSelectionHandles(textFieldSelectionState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void BasicTextField(final TextFieldState state, Modifier modifier, boolean enabled, boolean readOnly, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler onKeyboardAction, TextFieldLineLimits lineLimits, Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function2, MutableInteractionSource interactionSource, Brush cursorBrush, OutputTransformation outputTransformation, TextFieldDecorator decorator, ScrollState scrollState, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        boolean z;
        boolean z2;
        InputTransformation inputTransformation2;
        TextStyle textStyle2;
        KeyboardOptions keyboardOptions2;
        int i2;
        int i3;
        Composer $composer2;
        final KeyboardActionHandler onKeyboardAction2;
        final MutableInteractionSource interactionSource2;
        final ScrollState scrollState2;
        final boolean enabled2;
        final InputTransformation inputTransformation3;
        final TextStyle textStyle3;
        final Modifier modifier3;
        final TextFieldLineLimits lineLimits2;
        final Brush cursorBrush2;
        final OutputTransformation outputTransformation2;
        final TextFieldDecorator decorator2;
        final KeyboardOptions keyboardOptions3;
        final boolean readOnly2;
        final Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function3;
        boolean enabled3;
        boolean readOnly3;
        InputTransformation inputTransformation4;
        KeyboardActionHandler onKeyboardAction3;
        TextFieldLineLimits lineLimits3;
        Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function4;
        MutableInteractionSource interactionSource3;
        SolidColor cursorBrush3;
        OutputTransformation outputTransformation3;
        TextFieldDecorator decorator3;
        ScrollState scrollState3;
        boolean readOnly4;
        int $dirty1;
        Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function5;
        TextFieldDecorator decorator4;
        Brush cursorBrush4;
        boolean enabled4;
        KeyboardActionHandler onKeyboardAction4;
        TextStyle textStyle4;
        TextFieldLineLimits lineLimits4;
        KeyboardOptions keyboardOptions4;
        Composer $composer3 = $composer.startRestartGroup(469439921);
        ComposerKt.sourceInformation($composer3, "C(BasicTextField)N(state,modifier,enabled,readOnly,inputTransformation,textStyle,keyboardOptions,onKeyboardAction,lineLimits,onTextLayout,interactionSource,cursorBrush,outputTransformation,decorator,scrollState)202@12315L610:BasicTextField.kt#423gt5");
        int $dirty = $changed;
        int $dirty2 = $changed1;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty |= 384;
            z = enabled;
        } else if (($changed & 384) == 0) {
            z = enabled;
            $dirty |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            z2 = readOnly;
        } else if (($changed & 3072) == 0) {
            z2 = readOnly;
            $dirty |= $composer3.changed(z2) ? 2048 : 1024;
        } else {
            z2 = readOnly;
        }
        int i7 = i & 16;
        int i8 = 8192;
        if (i7 != 0) {
            $dirty |= 24576;
            inputTransformation2 = inputTransformation;
        } else if (($changed & 24576) == 0) {
            inputTransformation2 = inputTransformation;
            $dirty |= $composer3.changed(inputTransformation2) ? 16384 : 8192;
        } else {
            inputTransformation2 = inputTransformation;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            textStyle2 = textStyle;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            textStyle2 = textStyle;
            $dirty |= $composer3.changed(textStyle2) ? 131072 : 65536;
        } else {
            textStyle2 = textStyle;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty |= 1572864;
            keyboardOptions2 = keyboardOptions;
        } else if (($changed & 1572864) == 0) {
            keyboardOptions2 = keyboardOptions;
            $dirty |= $composer3.changed(keyboardOptions2) ? 1048576 : 524288;
        } else {
            keyboardOptions2 = keyboardOptions;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changed(onKeyboardAction) ? 8388608 : 4194304;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(lineLimits) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i13 = i & 512;
        if (i13 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 536870912 : 268435456;
        }
        int i14 = i & 1024;
        if (i14 != 0) {
            $dirty2 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 4 : 2;
        }
        int i15 = i & 2048;
        if (i15 != 0) {
            $dirty2 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty2 |= $composer3.changed(cursorBrush) ? 32 : 16;
        }
        int i16 = i & 4096;
        if (i16 != 0) {
            $dirty2 |= 384;
            i2 = i16;
        } else {
            i2 = i16;
            if (($changed1 & 384) == 0) {
                $dirty2 |= $composer3.changed(outputTransformation) ? 256 : 128;
            }
        }
        int i17 = i & 8192;
        if (i17 != 0) {
            $dirty2 |= 3072;
            i3 = i17;
        } else {
            i3 = i17;
            if (($changed1 & 3072) == 0) {
                $dirty2 |= ($changed1 & 4096) == 0 ? $composer3.changed(decorator) : $composer3.changedInstance(decorator) ? 2048 : 1024;
            }
        }
        if (($changed1 & 24576) == 0) {
            if ((i & 16384) == 0 && $composer3.changed(scrollState)) {
                i8 = 16384;
            }
            $dirty2 |= i8;
        }
        if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty2 & 9363) == 9362) ? false : true, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "198@12169L21");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 16384) != 0) {
                    $dirty2 &= -57345;
                }
                function5 = function2;
                interactionSource3 = interactionSource;
                cursorBrush4 = cursorBrush;
                outputTransformation3 = outputTransformation;
                decorator4 = decorator;
                scrollState3 = scrollState;
                keyboardOptions4 = keyboardOptions2;
                $dirty1 = $dirty2;
                enabled4 = z;
                readOnly4 = z2;
                inputTransformation4 = inputTransformation2;
                textStyle4 = textStyle2;
                onKeyboardAction4 = onKeyboardAction;
                lineLimits4 = lineLimits;
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i5 == 0) {
                    enabled3 = z;
                } else {
                    enabled3 = true;
                }
                if (i6 == 0) {
                    readOnly3 = z2;
                } else {
                    readOnly3 = false;
                }
                if (i7 == 0) {
                    inputTransformation4 = inputTransformation2;
                } else {
                    inputTransformation4 = null;
                }
                if (i9 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                if (i10 != 0) {
                    keyboardOptions2 = KeyboardOptions.INSTANCE.getDefault();
                }
                if (i11 == 0) {
                    onKeyboardAction3 = onKeyboardAction;
                } else {
                    onKeyboardAction3 = null;
                }
                if (i12 == 0) {
                    lineLimits3 = lineLimits;
                } else {
                    lineLimits3 = TextFieldLineLimits.INSTANCE.getDefault();
                }
                if (i13 == 0) {
                    function4 = function2;
                } else {
                    function4 = null;
                }
                if (i14 == 0) {
                    interactionSource3 = interactionSource;
                } else {
                    interactionSource3 = null;
                }
                if (i15 == 0) {
                    cursorBrush3 = cursorBrush;
                } else {
                    cursorBrush3 = BasicTextFieldDefaults.INSTANCE.getCursorBrush();
                }
                if (i2 == 0) {
                    outputTransformation3 = outputTransformation;
                } else {
                    outputTransformation3 = null;
                }
                if (i3 == 0) {
                    decorator3 = decorator;
                } else {
                    decorator3 = null;
                }
                if ((i & 16384) == 0) {
                    boolean enabled5 = enabled3;
                    scrollState3 = scrollState;
                    readOnly4 = readOnly3;
                    $dirty1 = $dirty2;
                    function5 = function4;
                    decorator4 = decorator3;
                    cursorBrush4 = cursorBrush3;
                    enabled4 = enabled5;
                    onKeyboardAction4 = onKeyboardAction3;
                    textStyle4 = textStyle2;
                    lineLimits4 = lineLimits3;
                    keyboardOptions4 = keyboardOptions2;
                } else {
                    boolean enabled6 = enabled3;
                    scrollState3 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                    readOnly4 = readOnly3;
                    $dirty1 = $dirty2 & (-57345);
                    function5 = function4;
                    decorator4 = decorator3;
                    cursorBrush4 = cursorBrush3;
                    enabled4 = enabled6;
                    onKeyboardAction4 = onKeyboardAction3;
                    textStyle4 = textStyle2;
                    lineLimits4 = lineLimits3;
                    keyboardOptions4 = keyboardOptions2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(469439921, $dirty, $dirty1, "androidx.compose.foundation.text.BasicTextField (BasicTextField.kt:201)");
            }
            $composer2 = $composer3;
            Modifier modifier4 = modifier2;
            Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function6 = function5;
            BasicTextField(state, modifier4, enabled4, readOnly4, inputTransformation4, textStyle4, keyboardOptions4, onKeyboardAction4, lineLimits4, function6, interactionSource3, cursorBrush4, null, outputTransformation3, decorator4, scrollState3, false, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty) | (1879048192 & $dirty), ($dirty1 & 14) | 384 | ($dirty1 & 112) | (($dirty1 << 3) & 7168) | (($dirty1 << 3) & 57344) | (($dirty1 << 3) & 458752), 65536);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enabled2 = enabled4;
            readOnly2 = readOnly4;
            inputTransformation3 = inputTransformation4;
            textStyle3 = textStyle4;
            keyboardOptions3 = keyboardOptions4;
            onKeyboardAction2 = onKeyboardAction4;
            lineLimits2 = lineLimits4;
            function3 = function6;
            interactionSource2 = interactionSource3;
            cursorBrush2 = cursorBrush4;
            outputTransformation2 = outputTransformation3;
            decorator2 = decorator4;
            scrollState2 = scrollState3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            onKeyboardAction2 = onKeyboardAction;
            interactionSource2 = interactionSource;
            scrollState2 = scrollState;
            enabled2 = z;
            inputTransformation3 = inputTransformation2;
            textStyle3 = textStyle2;
            modifier3 = modifier2;
            lineLimits2 = lineLimits;
            cursorBrush2 = cursorBrush;
            outputTransformation2 = outputTransformation;
            decorator2 = decorator;
            keyboardOptions3 = keyboardOptions2;
            readOnly2 = z2;
            function3 = function2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BasicTextFieldKt.BasicTextField$lambda$0(state, modifier3, enabled2, readOnly2, inputTransformation3, textStyle3, keyboardOptions3, onKeyboardAction2, lineLimits2, function3, interactionSource2, cursorBrush2, outputTransformation2, decorator2, scrollState2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:304:0x0556  */
    /* JADX WARN: Code duplicated, block: B:305:0x055b  */
    /* JADX WARN: Code duplicated, block: B:308:0x058f  */
    /* JADX WARN: Code duplicated, block: B:309:0x05a4  */
    /* JADX WARN: Code duplicated, block: B:312:0x05b6  */
    /* JADX WARN: Code duplicated, block: B:314:0x05c7  */
    /* JADX WARN: Code duplicated, block: B:316:0x05d9  */
    /* JADX WARN: Code duplicated, block: B:319:0x05ff  */
    /* JADX WARN: Code duplicated, block: B:323:0x0641  */
    /* JADX WARN: Code duplicated, block: B:325:0x064b  */
    /* JADX WARN: Code duplicated, block: B:326:0x0657  */
    /* JADX WARN: Code duplicated, block: B:330:0x06f0  */
    /* JADX WARN: Code duplicated, block: B:333:0x06fb A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:337:0x0752  */
    /* JADX WARN: Code duplicated, block: B:338:0x0754  */
    /* JADX WARN: Code duplicated, block: B:341:0x0775  */
    /* JADX WARN: Code duplicated, block: B:342:0x0777  */
    /* JADX WARN: Code duplicated, block: B:345:0x077f  */
    /* JADX WARN: Code duplicated, block: B:346:0x0781  */
    /* JADX WARN: Code duplicated, block: B:349:0x078b  */
    /* JADX WARN: Code duplicated, block: B:350:0x078d  */
    /* JADX WARN: Code duplicated, block: B:353:0x0799  */
    /* JADX WARN: Code duplicated, block: B:355:0x07a3  */
    /* JADX WARN: Code duplicated, block: B:356:0x07af  */
    /* JADX WARN: Code duplicated, block: B:360:0x07fe  */
    /* JADX WARN: Code duplicated, block: B:363:0x0809 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:367:0x0828  */
    /* JADX WARN: Code duplicated, block: B:372:0x084a  */
    /* JADX WARN: Code duplicated, block: B:375:0x0865  */
    /* JADX WARN: Code duplicated, block: B:378:0x0870  */
    /* JADX WARN: Code duplicated, block: B:382:0x08c8  */
    /* JADX WARN: Code duplicated, block: B:385:0x08d3  */
    /* JADX WARN: Code duplicated, block: B:388:0x0990  */
    /* JADX WARN: Code duplicated, block: B:391:0x099c  */
    /* JADX WARN: Code duplicated, block: B:392:0x09a2  */
    /* JADX WARN: Code duplicated, block: B:395:0x0a60  */
    public static final void BasicTextField(final TextFieldState state, Modifier modifier, boolean enabled, boolean readOnly, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler onKeyboardAction, TextFieldLineLimits lineLimits, Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function2, MutableInteractionSource interactionSource, Brush cursorBrush, CodepointTransformation codepointTransformation, OutputTransformation outputTransformation, TextFieldDecorator decorator, ScrollState scrollState, boolean isPassword, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        boolean enabled2;
        boolean readOnly2;
        InputTransformation inputTransformation2;
        TextStyle textStyle2;
        KeyboardOptions keyboardOptions2;
        int i2;
        int i3;
        int i4;
        Composer $composer2;
        final KeyboardActionHandler onKeyboardAction2;
        final OutputTransformation outputTransformation2;
        final ScrollState scrollState2;
        final boolean isPassword2;
        final TextStyle textStyle3;
        final KeyboardOptions keyboardOptions3;
        final InputTransformation inputTransformation3;
        final boolean enabled3;
        final Modifier modifier3;
        final boolean readOnly3;
        final TextFieldLineLimits lineLimits2;
        final Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function3;
        final MutableInteractionSource interactionSource2;
        final Brush cursorBrush2;
        final CodepointTransformation codepointTransformation2;
        final TextFieldDecorator decorator2;
        KeyboardOptions keyboardOptions4;
        KeyboardActionHandler onKeyboardAction3;
        final TextFieldLineLimits lineLimits3;
        MutableInteractionSource interactionSource3;
        CodepointTransformation codepointTransformation3;
        ScrollState scrollState3;
        final TextStyle textStyle4;
        final Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function4;
        boolean enabled4;
        final boolean readOnly4;
        final Brush cursorBrush3;
        final ScrollState scrollState4;
        OutputTransformation outputTransformation3;
        final TextFieldDecorator decorator3;
        int $dirty1;
        boolean isPassword3;
        MutableInteractionSource mutableInteractionSource;
        final boolean isWindowAndTextFieldFocused;
        SingleLineCodepointTransformation singleLineCodepointTransformation;
        OutputTransformation outputTransformation4;
        KeyboardOptions keyboardOptions5;
        Object value$iv$iv;
        CoroutineScope coroutineScope;
        final PlatformSelectionBehaviors platformSelectionBehaviors;
        Object it$iv;
        Clipboard currentClipboard;
        boolean invalid$iv;
        Object it$iv2;
        CoroutineScope coroutineScope2;
        CoroutineScope coroutineScope3;
        Clipboard currentClipboard2;
        int $dirty2;
        final InputTransformation inputTransformation4;
        boolean invalid$iv2;
        Object it$iv3;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean invalid$iv3;
        Object it$iv4;
        Density density;
        final TransformedTextFieldState transformedState;
        boolean enabled5;
        boolean invalid$iv4;
        Object it$iv5;
        final boolean handwritingEnabled;
        boolean invalid$iv5;
        Object it$iv6;
        InputTransformation inputTransformation5;
        final boolean enabled6;
        boolean z5;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        LocaleList resolvedLocaleList;
        Object value$iv;
        Composer $composer3 = $composer.startRestartGroup(965149429);
        ComposerKt.sourceInformation($composer3, "C(BasicTextField)N(state,modifier,enabled,readOnly,inputTransformation,textStyle,keyboardOptions,onKeyboardAction,lineLimits,onTextLayout,interactionSource,cursorBrush,codepointTransformation,outputTransformation,decorator,scrollState,isPassword)252@14291L7,253@14346L7,259@14716L25,260@14790L25,264@15134L107,269@15278L846,287@16322L48,294@16698L24,303@17116L35,304@17194L7,306@17244L617,321@17914L7,322@17968L7,325@18014L2260,372@20280L256,379@20553L510,379@20542L521,395@21111L51,395@21069L93,397@21191L35,405@21528L1339,462@24584L3165:BasicTextField.kt#423gt5");
        int $dirty = $changed;
        int $dirty3 = $changed1;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty |= $composer3.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty |= 3072;
            readOnly2 = readOnly;
        } else if (($changed & 3072) == 0) {
            readOnly2 = readOnly;
            $dirty |= $composer3.changed(readOnly2) ? 2048 : 1024;
        } else {
            readOnly2 = readOnly;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty |= 24576;
            inputTransformation2 = inputTransformation;
        } else if (($changed & 24576) == 0) {
            inputTransformation2 = inputTransformation;
            $dirty |= $composer3.changed(inputTransformation2) ? 16384 : 8192;
        } else {
            inputTransformation2 = inputTransformation;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            textStyle2 = textStyle;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            textStyle2 = textStyle;
            $dirty |= $composer3.changed(textStyle2) ? 131072 : 65536;
        } else {
            textStyle2 = textStyle;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty |= 1572864;
            keyboardOptions2 = keyboardOptions;
        } else if (($changed & 1572864) == 0) {
            keyboardOptions2 = keyboardOptions;
            $dirty |= $composer3.changed(keyboardOptions2) ? 1048576 : 524288;
        } else {
            keyboardOptions2 = keyboardOptions;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changed(onKeyboardAction) ? 8388608 : 4194304;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(lineLimits) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i13 = i & 512;
        if (i13 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 536870912 : 268435456;
        }
        int i14 = i & 1024;
        if (i14 != 0) {
            $dirty3 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty3 |= $composer3.changed(interactionSource) ? 4 : 2;
        }
        int i15 = i & 2048;
        if (i15 != 0) {
            $dirty3 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty3 |= $composer3.changed(cursorBrush) ? 32 : 16;
        }
        int i16 = i & 4096;
        if (i16 != 0) {
            $dirty3 |= 384;
            i2 = i16;
        } else {
            i2 = i16;
            if (($changed1 & 384) == 0) {
                $dirty3 |= $composer3.changed(codepointTransformation) ? 256 : 128;
            }
        }
        int i17 = i & 8192;
        if (i17 != 0) {
            $dirty3 |= 3072;
            i3 = i17;
        } else {
            i3 = i17;
            if (($changed1 & 3072) == 0) {
                $dirty3 |= $composer3.changed(outputTransformation) ? 2048 : 1024;
            }
        }
        int i18 = i & 16384;
        if (i18 != 0) {
            $dirty3 |= 24576;
            i4 = i18;
        } else {
            i4 = i18;
            if (($changed1 & 24576) == 0) {
                $dirty3 |= ($changed1 & 32768) == 0 ? $composer3.changed(decorator) : $composer3.changedInstance(decorator) ? 16384 : 8192;
            }
        }
        if ((196608 & $changed1) == 0) {
            $dirty3 |= ((i & 32768) == 0 && $composer3.changed(scrollState)) ? 131072 : 65536;
        }
        int i19 = i & 65536;
        if (i19 != 0) {
            $dirty3 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty3 |= $composer3.changed(isPassword) ? 1048576 : 524288;
        }
        if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && (599187 & $dirty3) == 599186) ? false : true, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "247@14085L21");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    enabled2 = true;
                }
                if (i7 != 0) {
                    readOnly2 = false;
                }
                if (i8 != 0) {
                    inputTransformation2 = null;
                }
                TextStyle textStyle5 = i9 != 0 ? TextStyle.INSTANCE.getDefault() : textStyle2;
                keyboardOptions4 = i10 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions2;
                onKeyboardAction3 = i11 != 0 ? null : onKeyboardAction;
                lineLimits3 = i12 != 0 ? TextFieldLineLimits.INSTANCE.getDefault() : lineLimits;
                Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function5 = i13 != 0 ? null : function2;
                interactionSource3 = i14 != 0 ? null : interactionSource;
                SolidColor cursorBrush4 = i15 != 0 ? BasicTextFieldDefaults.INSTANCE.getCursorBrush() : cursorBrush;
                codepointTransformation3 = i2 != 0 ? null : codepointTransformation;
                OutputTransformation outputTransformation5 = i3 != 0 ? null : outputTransformation;
                TextFieldDecorator decorator4 = i4 != 0 ? null : decorator;
                if ((i & 32768) != 0) {
                    scrollState3 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                    $dirty3 &= -458753;
                } else {
                    scrollState3 = scrollState;
                }
                if (i19 != 0) {
                    textStyle4 = textStyle5;
                    function4 = function5;
                    enabled4 = enabled2;
                    readOnly4 = readOnly2;
                    cursorBrush3 = cursorBrush4;
                    scrollState4 = scrollState3;
                    decorator3 = decorator4;
                    $dirty1 = $dirty3;
                    isPassword3 = false;
                    outputTransformation3 = outputTransformation5;
                } else {
                    textStyle4 = textStyle5;
                    function4 = function5;
                    enabled4 = enabled2;
                    readOnly4 = readOnly2;
                    cursorBrush3 = cursorBrush4;
                    scrollState4 = scrollState3;
                    outputTransformation3 = outputTransformation5;
                    decorator3 = decorator4;
                    $dirty1 = $dirty3;
                    isPassword3 = isPassword;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32768) != 0) {
                    $dirty3 &= -458753;
                }
                onKeyboardAction3 = onKeyboardAction;
                function4 = function2;
                interactionSource3 = interactionSource;
                cursorBrush3 = cursorBrush;
                codepointTransformation3 = codepointTransformation;
                outputTransformation3 = outputTransformation;
                decorator3 = decorator;
                scrollState4 = scrollState;
                textStyle4 = textStyle2;
                keyboardOptions4 = keyboardOptions2;
                enabled4 = enabled2;
                readOnly4 = readOnly2;
                lineLimits3 = lineLimits;
                $dirty1 = $dirty3;
                isPassword3 = isPassword;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(965149429, $dirty, $dirty1, "androidx.compose.foundation.text.BasicTextField (BasicTextField.kt:251)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            final boolean isPassword4 = isPassword3;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density2 = (Density) objConsume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LayoutDirection layoutDirection = (LayoutDirection) objConsume2;
            final boolean singleLine = Intrinsics.areEqual(lineLimits3, TextFieldLineLimits.SingleLine.INSTANCE);
            if (interactionSource3 == null) {
                $composer3.startReplaceGroup(-2038132442);
                ComposerKt.sourceInformation($composer3, "257@14551L39");
                ComposerKt.sourceInformationMarkerStart($composer3, -204293540, "CC(remember):BasicTextField.kt#9igjgp");
                Object it$iv7 = $composer3.rememberedValue();
                if (it$iv7 == Composer.INSTANCE.getEmpty()) {
                    value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv7;
                }
                mutableInteractionSource = (MutableInteractionSource) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-204294191);
                $composer3.endReplaceGroup();
                mutableInteractionSource = interactionSource3;
            }
            MutableInteractionSource interactionSource4 = mutableInteractionSource;
            final Orientation orientation = singleLine ? Orientation.Horizontal : Orientation.Vertical;
            boolean isFocused = FocusInteractionKt.collectIsFocusedAsState(interactionSource4, $composer3, 0).getValue().booleanValue();
            final boolean isDragHovered = HoverInteractionKt.collectIsHoveredAsState(interactionSource4, $composer3, 0).getValue().booleanValue();
            if (isFocused) {
                $composer3.startReplaceGroup(-204276540);
                ComposerKt.sourceInformation($composer3, "263@15075L7");
                ProvidableCompositionLocal<WindowInfo> localWindowInfo = CompositionLocalsKt.getLocalWindowInfo();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer3.consume(localWindowInfo);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                boolean zIsWindowFocused = ((WindowInfo) objConsume3).isWindowFocused();
                $composer3.endReplaceGroup();
                isWindowAndTextFieldFocused = zIsWindowFocused;
            } else {
                $composer3.startReplaceGroup(-2037604207);
                $composer3.endReplaceGroup();
                isWindowAndTextFieldFocused = false;
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -204274816, "CC(remember):BasicTextField.kt#9igjgp");
            Object it$iv8 = $composer3.rememberedValue();
            if (it$iv8 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = SharedFlowKt.MutableSharedFlow$default(1, 0, BufferOverflow.DROP_LATEST, 2, null);
                $composer3.updateRememberedValue(value$iv2);
                it$iv8 = value$iv2;
            }
            final MutableSharedFlow stylusHandwritingTrigger = (MutableSharedFlow) it$iv8;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -204269469, "CC(remember):BasicTextField.kt#9igjgp");
            boolean invalid$iv6 = (($dirty & 14) == 4) | (($dirty1 & 896) == 256) | (($dirty1 & 7168) == 2048);
            Object it$iv9 = $composer3.rememberedValue();
            if (invalid$iv6 || it$iv9 == Composer.INSTANCE.getEmpty()) {
                if (codepointTransformation3 == null) {
                    SingleLineCodepointTransformation singleLineCodepointTransformation2 = SingleLineCodepointTransformation.INSTANCE;
                    if (!singleLine) {
                        singleLineCodepointTransformation2 = null;
                    }
                    singleLineCodepointTransformation = singleLineCodepointTransformation2;
                } else {
                    singleLineCodepointTransformation = codepointTransformation3;
                }
                CodepointTransformation appliedCodepointTransformation = singleLineCodepointTransformation;
                Object value$iv3 = new TransformedTextFieldState(state, inputTransformation2, appliedCodepointTransformation, outputTransformation3);
                $composer3.updateRememberedValue(value$iv3);
                it$iv9 = value$iv3;
            }
            final TransformedTextFieldState transformedState2 = (TransformedTextFieldState) it$iv9;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -204236859, "CC(remember):BasicTextField.kt#9igjgp");
            boolean invalid$iv7 = $composer3.changed(transformedState2);
            Object it$iv10 = $composer3.rememberedValue();
            if (invalid$iv7) {
                outputTransformation4 = outputTransformation3;
            } else {
                outputTransformation4 = outputTransformation3;
                if (it$iv10 == Composer.INSTANCE.getEmpty()) {
                }
                final TextLayoutState textLayoutState = (TextLayoutState) it$iv10;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (inputTransformation2 != null) {
                    keyboardOptions5 = inputTransformation2.getKeyboardOptions();
                } else {
                    keyboardOptions5 = null;
                }
                final KeyboardOptions resolvedKeyboardOptions = keyboardOptions4.fillUnspecifiedValuesWith$foundation(keyboardOptions5);
                ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart($composer3, 683736516, "CC(remember):Effects.kt#9igjgp");
                value$iv$iv = $composer3.rememberedValue();
                if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3);
                    $composer3.updateRememberedValue(value$iv$iv);
                }
                coroutineScope = (CoroutineScope) value$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposeFoundationFlags.isSmartSelectionEnabled) {
                    $composer3.startReplaceGroup(-2035832304);
                    ComposerKt.sourceInformation($composer3, "299@16959L85");
                    resolvedLocaleList = textStyle4.getLocaleList();
                    if (resolvedLocaleList == null) {
                        resolvedLocaleList = LocaleList.INSTANCE.getCurrent();
                    }
                    PlatformSelectionBehaviors platformSelectionBehaviorsRememberPlatformSelectionBehaviors = PlatformSelectionBehaviors_androidKt.rememberPlatformSelectionBehaviors(SelectedTextType.EditableText, resolvedLocaleList, $composer3, 6);
                    $composer3.endReplaceGroup();
                    platformSelectionBehaviors = platformSelectionBehaviorsRememberPlatformSelectionBehaviors;
                } else {
                    $composer3.startReplaceGroup(-2035643855);
                    $composer3.endReplaceGroup();
                    platformSelectionBehaviors = null;
                }
                ComposerKt.sourceInformationMarkerStart($composer3, -204211464, "CC(remember):BasicTextField.kt#9igjgp");
                it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv4 = new ToolbarRequesterImpl();
                    $composer3.updateRememberedValue(value$iv4);
                    it$iv = value$iv4;
                }
                final ToolbarRequesterImpl toolbarRequester = (ToolbarRequesterImpl) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ProvidableCompositionLocal<Clipboard> localClipboard = CompositionLocalsKt.getLocalClipboard();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume4 = $composer3.consume(localClipboard);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                currentClipboard = (Clipboard) objConsume4;
                ComposerKt.sourceInformationMarkerStart($composer3, -204206786, "CC(remember):BasicTextField.kt#9igjgp");
                invalid$iv = $composer3.changed(transformedState2);
                it$iv2 = $composer3.rememberedValue();
                if (invalid$iv) {
                    coroutineScope2 = coroutineScope;
                } else {
                    coroutineScope2 = coroutineScope;
                    if (it$iv2 != Composer.INSTANCE.getEmpty()) {
                        coroutineScope3 = coroutineScope2;
                        currentClipboard2 = currentClipboard;
                    }
                    final TextFieldSelectionState textFieldSelectionState = (TextFieldSelectionState) it$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ProvidableCompositionLocal<HapticFeedback> localHapticFeedback = CompositionLocalsKt.getLocalHapticFeedback();
                    $dirty2 = $dirty1;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume5 = $composer3.consume(localHapticFeedback);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    final HapticFeedback currentHapticFeedback = (HapticFeedback) objConsume5;
                    ProvidableCompositionLocal<TextToolbar> localTextToolbar = CompositionLocalsKt.getLocalTextToolbar();
                    inputTransformation4 = inputTransformation2;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume6 = $composer3.consume(localTextToolbar);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    TextToolbar currentTextToolbar = (TextToolbar) objConsume6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -204180503, "CC(remember):BasicTextField.kt#9igjgp");
                    invalid$iv2 = $composer3.changed(coroutineScope3) | $composer3.changed(currentTextToolbar);
                    it$iv3 = $composer3.rememberedValue();
                    if (!invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv5 = new BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1(currentTextToolbar, coroutineScope3);
                        $composer3.updateRememberedValue(value$iv5);
                        it$iv3 = value$iv5;
                    }
                    final BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1 textToolbarHandler = (BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1) it$iv3;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda16
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return BasicTextFieldKt.BasicTextField$lambda$8(textFieldSelectionState, (AnnotatedString) obj);
                        }
                    };
                    new Function0() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda17
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return TextFieldSelectionState.copyWithResult$foundation$default(textFieldSelectionState, false, 1, null);
                        }
                    };
                    new Function0() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda18
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return textFieldSelectionState.cutWithResult();
                        }
                    };
                    ComposerKt.sourceInformationMarkerStart($composer3, 1197772674, "CC(rememberClipboardEventsHandler)N(onPaste,onCopy,onCut,isEnabled):ClipboardEventsHandler.jvm.kt#423gt5");
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerStart($composer3, -204101005, "CC(remember):BasicTextField.kt#9igjgp");
                    boolean zChanged = $composer3.changed(transformedState2);
                    if ((57344 & $dirty) == 16384) {
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean zChangedInstance = zChanged | z | $composer3.changedInstance(textFieldSelectionState) | $composer3.changedInstance(currentHapticFeedback) | $composer3.changedInstance(currentClipboard2) | $composer3.changed(textToolbarHandler) | $composer3.changed(
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x076e: ARITH (r4v14 'zChangedInstance' boolean) = (wrap boolean:0x0769: ARITH (wrap boolean:0x0764: ARITH (wrap boolean:0x075f: ARITH (wrap boolean:0x075a: ARITH (wrap boolean:0x0755: ARITH (r4v8 'zChanged' boolean) | (r10v23 'z' boolean) A[DONT_WRAP, WRAPPED]) | (wrap boolean:0x0756: INVOKE 
                          (r5v1 '$composer3' androidx.compose.runtime.Composer A[D('$composer' androidx.compose.runtime.Composer)])
                          (r37v1 'textFieldSelectionState' androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState A[D('textFieldSelectionState' androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState)])
                         INTERFACE call: androidx.compose.runtime.Composer.changedInstance(java.lang.Object):boolean A[MD:(java.lang.Object):boolean (m), WRAPPED] (LINE:306)) A[DONT_WRAP, WRAPPED] (LINE:306)) | (wrap boolean:0x075b: INVOKE 
                          (r5v1 '$composer3' androidx.compose.runtime.Composer A[D('$composer' androidx.compose.runtime.Composer)])
                          (r9v22 'currentHapticFeedback' androidx.compose.ui.hapticfeedback.HapticFeedback A[D('currentHapticFeedback' androidx.compose.ui.hapticfeedback.HapticFeedback)])
                         INTERFACE call: androidx.compose.runtime.Composer.changedInstance(java.lang.Object):boolean A[MD:(java.lang.Object):boolean (m), WRAPPED]) A[DONT_WRAP, WRAPPED] (LINE:306)) | (wrap boolean:0x0760: INVOKE 
                          (r5v1 '$composer3' androidx.compose.runtime.Composer A[D('$composer' androidx.compose.runtime.Composer)])
                          (r2v46 'currentClipboard2' androidx.compose.ui.platform.Clipboard A[D('currentClipboard' androidx.compose.ui.platform.Clipboard)])
                         INTERFACE call: androidx.compose.runtime.Composer.changedInstance(java.lang.Object):boolean A[MD:(java.lang.Object):boolean (m), WRAPPED]) A[DONT_WRAP, WRAPPED] (LINE:306)) | (wrap boolean:0x0765: INVOKE 
                          (r5v1 '$composer3' androidx.compose.runtime.Composer A[D('$composer' androidx.compose.runtime.Composer)])
                          (r15v31 'textToolbarHandler' androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1 A[D('textToolbarHandler' androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1)])
                         INTERFACE call: androidx.compose.runtime.Composer.changed(java.lang.Object):boolean A[MD:(java.lang.Object):boolean (m), WRAPPED]) A[DONT_WRAP, WRAPPED] (LINE:306)) | (wrap boolean:0x076a: INVOKE 
                          (r5v1 '$composer3' androidx.compose.runtime.Composer A[D('$composer' androidx.compose.runtime.Composer)])
                          (r64v1 androidx.compose.ui.unit.Density)
                         INTERFACE call: androidx.compose.runtime.Composer.changed(java.lang.Object):boolean A[MD:(java.lang.Object):boolean (m), WRAPPED]) A[DECLARE_VAR] (LINE:306) in method: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(androidx.compose.foundation.text.input.TextFieldState, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.foundation.text.input.InputTransformation, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.input.KeyboardActionHandler, androidx.compose.foundation.text.input.TextFieldLineLimits, kotlin.jvm.functions.Function2<? super androidx.compose.ui.unit.Density, ? super kotlin.jvm.functions.Function0<androidx.compose.ui.text.TextLayoutResult>, kotlin.Unit>, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, androidx.compose.foundation.text.input.internal.CodepointTransformation, androidx.compose.foundation.text.input.OutputTransformation, androidx.compose.foundation.text.input.TextFieldDecorator, androidx.compose.foundation.ScrollState, boolean, androidx.compose.runtime.Composer, int, int, int):void, file: classes.dex
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
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                        	at java.base/java.util.ArrayList.forEach(Unknown Source)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r64v1 androidx.compose.ui.unit.Density
                        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                        */
                    /*
                        Method dump skipped, instruction units count: 2758
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(androidx.compose.foundation.text.input.TextFieldState, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.foundation.text.input.InputTransformation, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.input.KeyboardActionHandler, androidx.compose.foundation.text.input.TextFieldLineLimits, kotlin.jvm.functions.Function2, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, androidx.compose.foundation.text.input.internal.CodepointTransformation, androidx.compose.foundation.text.input.OutputTransformation, androidx.compose.foundation.text.input.TextFieldDecorator, androidx.compose.foundation.ScrollState, boolean, androidx.compose.runtime.Composer, int, int, int):void");
                }

                static final Unit BasicTextField$lambda$8(TextFieldSelectionState $textFieldSelectionState, AnnotatedString it) {
                    $textFieldSelectionState.onPasteEvent$foundation(it);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit BasicTextField$lambda$11$0(TransformedTextFieldState $transformedState, InputTransformation $inputTransformation, TextFieldSelectionState $textFieldSelectionState, HapticFeedback $currentHapticFeedback, Clipboard $currentClipboard, BasicTextFieldKt$BasicTextField$textToolbarHandler$1$1 $textToolbarHandler, Density $density, boolean $enabled, boolean $readOnly, boolean $isPassword) {
                    $transformedState.update($inputTransformation);
                    $textFieldSelectionState.update($currentHapticFeedback, $currentClipboard, $textToolbarHandler, $density, $enabled, $readOnly, $isPassword);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final DisposableEffectResult BasicTextField$lambda$12$0(final TextFieldSelectionState $textFieldSelectionState, DisposableEffectScope $this$DisposableEffect) {
                    return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$BasicTextField$lambda$12$0$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            $textFieldSelectionState.dispose();
                        }
                    };
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit BasicTextField$lambda$13$0(boolean $handwritingEnabled, MutableSharedFlow $stylusHandwritingTrigger) {
                    if ($handwritingEnabled) {
                        $stylusHandwritingTrigger.tryEmit(Unit.INSTANCE);
                    }
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit BasicTextField$lambda$14$0(TextFieldDecorator $decorator, final TextFieldLineLimits $lineLimits, final TextLayoutState $textLayoutState, final TextStyle $textStyle, final boolean $isWindowAndTextFieldFocused, final boolean $isDragHovered, final TransformedTextFieldState $transformedState, final TextFieldSelectionState $textFieldSelectionState, final Brush $cursorBrush, final boolean $enabled, final boolean $readOnly, final ScrollState $scrollState, final Orientation $orientation, final ToolbarRequesterImpl $toolbarRequester, final PlatformSelectionBehaviors $platformSelectionBehaviors, final boolean $singleLine, final Function2 $onTextLayout, final KeyboardOptions $resolvedKeyboardOptions, Composer $composer, int $changed) {
                    ComposerKt.sourceInformation($composer, "C465@24817L2916,465@24806L2927:BasicTextField.kt#423gt5");
                    if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                        $composer.skipToGroupEnd();
                    } else {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-673241599, $changed, -1, "androidx.compose.foundation.text.BasicTextField.<anonymous>.<anonymous> (BasicTextField.kt:464)");
                        }
                        TextFieldDecorator nonNullDecorator = $decorator == null ? DefaultTextFieldDecorator : $decorator;
                        nonNullDecorator.Decoration(ComposableLambdaKt.rememberComposableLambda(1969169726, true, new Function2() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda11
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return BasicTextFieldKt.BasicTextField$lambda$14$0$0($lineLimits, $textLayoutState, $textStyle, $isWindowAndTextFieldFocused, $isDragHovered, $transformedState, $textFieldSelectionState, $cursorBrush, $enabled, $readOnly, $scrollState, $orientation, $toolbarRequester, $platformSelectionBehaviors, $singleLine, $onTextLayout, $resolvedKeyboardOptions, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        }, $composer, 54), $composer, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit BasicTextField$lambda$14$0$0(TextFieldLineLimits $lineLimits, TextLayoutState $textLayoutState, TextStyle $textStyle, boolean $isWindowAndTextFieldFocused, boolean $isDragHovered, TransformedTextFieldState $transformedState, TextFieldSelectionState $textFieldSelectionState, Brush $cursorBrush, boolean $enabled, boolean $readOnly, ScrollState $scrollState, Orientation $orientation, ToolbarRequesterImpl $toolbarRequester, PlatformSelectionBehaviors $platformSelectionBehaviors, boolean $singleLine, Function2 $onTextLayout, KeyboardOptions $resolvedKeyboardOptions, Composer $composer, int $changed) {
                    int minLines;
                    int maxLines;
                    ComposerKt.sourceInformation($composer, "C476@25178L2541:BasicTextField.kt#423gt5");
                    if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1969169726, $changed, -1, "androidx.compose.foundation.text.BasicTextField.<anonymous>.<anonymous>.<anonymous> (BasicTextField.kt:466)");
                        }
                        if ($lineLimits instanceof TextFieldLineLimits.MultiLine) {
                            minLines = ((TextFieldLineLimits.MultiLine) $lineLimits).getMinHeightInLines();
                            maxLines = ((TextFieldLineLimits.MultiLine) $lineLimits).getMaxHeightInLines();
                        } else {
                            minLines = 1;
                            maxLines = 1;
                        }
                        Modifier modifier$iv = ClipKt.clipToBounds(TextFieldSizeKt.textFieldMinSize(HeightInLinesModifierKt.heightInLines(SizeKt.m853heightInVpY3zN4$default(Modifier.INSTANCE, $textLayoutState.m1449getMinHeightForSingleLineFieldD9Ej5fM(), 0.0f, 2, null), $textStyle, minLines, maxLines), $textStyle)).then(new TextFieldCoreModifier($isWindowAndTextFieldFocused, $isDragHovered, $textLayoutState, $transformedState, $textFieldSelectionState, $cursorBrush, $enabled && !$readOnly, $scrollState, $orientation, $toolbarRequester, $platformSelectionBehaviors));
                        ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
                        int $changed$iv$iv = (384 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                        CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            $composer.createNode(constructor);
                        } else {
                            $composer.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4371initimpl($this$Layout_u24lambda_u240$iv$iv, Integer.valueOf(compositeKeyHash$iv$iv), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4373reconcileimpl($this$Layout_u24lambda_u240$iv$iv, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i2 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -811364182, "C503@26688L530:BasicTextField.kt#423gt5");
                        BoxKt.Box(new TextFieldTextLayoutModifier($textLayoutState, $transformedState, $textStyle, $singleLine, $onTextLayout, $resolvedKeyboardOptions), $composer, 0);
                        if (!$enabled || !$isWindowAndTextFieldFocused || !$textFieldSelectionState.isInTouchMode()) {
                            $composer.startReplaceGroup(-837871074);
                        } else {
                            $composer.startReplaceGroup(-810654004);
                            ComposerKt.sourceInformation($composer, "520@27453L67");
                            TextFieldSelectionHandles($textFieldSelectionState, $composer, 0);
                            if (!$readOnly) {
                                $composer.startReplaceGroup(-810526873);
                                ComposerKt.sourceInformation($composer, "522@27590L63");
                                TextFieldCursorHandle($textFieldSelectionState, $composer, 0);
                            } else {
                                $composer.startReplaceGroup(-837871074);
                            }
                            $composer.endReplaceGroup();
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
                    } else {
                        $composer.skipToGroupEnd();
                    }
                    return Unit.INSTANCE;
                }

                private static final Modifier addContextMenuComponents(Modifier $this$addContextMenuComponents, TextFieldSelectionState textFieldSelectionState, CoroutineScope coroutineScope) {
                    if (ComposeFoundationFlags.isNewContextMenuEnabled) {
                        return TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents($this$addContextMenuComponents, textFieldSelectionState, coroutineScope);
                    }
                    return $this$addContextMenuComponents;
                }

                public static final void TextFieldCursorHandle(final TextFieldSelectionState selectionState, Composer $composer, final int $changed) {
                    Composer $composer2 = $composer.startRestartGroup(1991581797);
                    ComposerKt.sourceInformation($composer2, "C(TextFieldCursorHandle)N(selectionState)544@28301L128:BasicTextField.kt#423gt5");
                    int $dirty = $changed;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer2.changedInstance(selectionState) ? 4 : 2;
                    }
                    if (!$composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
                        $composer2.skipToGroupEnd();
                    } else {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1991581797, $dirty, -1, "androidx.compose.foundation.text.TextFieldCursorHandle (BasicTextField.kt:541)");
                        }
                        ComposerKt.sourceInformationMarkerStart($composer2, -1645301467, "CC(remember):BasicTextField.kt#9igjgp");
                        boolean invalid$iv = $composer2.changed(selectionState);
                        Object it$iv = $composer2.rememberedValue();
                        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                            Object value$iv = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda24
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return BasicTextFieldKt.TextFieldCursorHandle$lambda$0$0(selectionState);
                                }
                            });
                            $composer2.updateRememberedValue(value$iv);
                            it$iv = value$iv;
                        }
                        State cursorHandleState$delegate = (State) it$iv;
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (TextFieldCursorHandle$lambda$1(cursorHandleState$delegate).getVisible()) {
                            $composer2.startReplaceGroup(535433166);
                            ComposerKt.sourceInformation($composer2, "549@28518L100,553@28697L87,548@28475L383");
                            ComposerKt.sourceInformationMarkerStart($composer2, -1645294551, "CC(remember):BasicTextField.kt#9igjgp");
                            boolean invalid$iv2 = $composer2.changedInstance(selectionState);
                            Object it$iv2 = $composer2.rememberedValue();
                            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                Object value$iv2 = (OffsetProvider) new OffsetProvider() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldCursorHandle$1$1
                                    @Override // androidx.compose.foundation.text.selection.OffsetProvider
                                    /* JADX INFO: renamed from: provide-F1C5BW0, reason: not valid java name */
                                    public final long mo1145provideF1C5BW0() {
                                        return selectionState.getCursorHandleState$foundation(true).m1493getPositionF1C5BW0();
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv2);
                                it$iv2 = value$iv2;
                            }
                            OffsetProvider offsetProvider = (OffsetProvider) it$iv2;
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            Modifier.Companion companion = Modifier.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer2, -1645288836, "CC(remember):BasicTextField.kt#9igjgp");
                            boolean invalid$iv3 = $composer2.changedInstance(selectionState);
                            Object it$iv3 = $composer2.rememberedValue();
                            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                                Object value$iv3 = (PointerInputEventHandler) new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldCursorHandle$2$1
                                    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                                    public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                                        TextFieldSelectionState $this$invoke_u24lambda_u240 = selectionState;
                                        Object objCursorHandleGestures = $this$invoke_u24lambda_u240.cursorHandleGestures($this$pointerInput, continuation);
                                        return objCursorHandleGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCursorHandleGestures : Unit.INSTANCE;
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv3);
                                it$iv3 = value$iv3;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            AndroidCursorHandle_androidKt.m1131CursorHandleUSBMPiE(offsetProvider, SuspendingPointerInputFilterKt.pointerInput(companion, selectionState, (PointerInputEventHandler) it$iv3), MinTouchTargetSizeForHandles, $composer2, 384, 0);
                        } else {
                            $composer2.startReplaceGroup(507182525);
                        }
                        $composer2.endReplaceGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda25
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return BasicTextFieldKt.TextFieldCursorHandle$lambda$4(selectionState, $changed, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }

                private static final TextFieldHandleState TextFieldCursorHandle$lambda$1(State<TextFieldHandleState> state) {
                    Object thisObj$iv = state.getValue();
                    return (TextFieldHandleState) thisObj$iv;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final TextFieldHandleState TextFieldCursorHandle$lambda$0$0(TextFieldSelectionState $selectionState) {
                    return $selectionState.getCursorHandleState$foundation(false);
                }

                public static final void TextFieldSelectionHandles(final TextFieldSelectionState selectionState, Composer $composer, final int $changed) {
                    int i;
                    Composer $composer2 = $composer.startRestartGroup(2025287684);
                    ComposerKt.sourceInformation($composer2, "C(TextFieldSelectionHandles)N(selectionState)565@29064L240,594@30119L241:BasicTextField.kt#423gt5");
                    int $dirty = $changed;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer2.changedInstance(selectionState) ? 4 : 2;
                    }
                    int $dirty2 = $dirty;
                    if ($composer2.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(2025287684, $dirty2, -1, "androidx.compose.foundation.text.TextFieldSelectionHandles (BasicTextField.kt:562)");
                        }
                        ComposerKt.sourceInformationMarkerStart($composer2, 681285076, "CC(remember):BasicTextField.kt#9igjgp");
                        boolean invalid$iv = $composer2.changed(selectionState);
                        Object it$iv = $composer2.rememberedValue();
                        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                            Object value$iv = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda6
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return BasicTextFieldKt.TextFieldSelectionHandles$lambda$0$0(selectionState);
                                }
                            });
                            $composer2.updateRememberedValue(value$iv);
                            it$iv = value$iv;
                        }
                        State startHandleState$delegate = (State) it$iv;
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (TextFieldSelectionHandles$lambda$1(startHandleState$delegate).getVisible()) {
                            $composer2.startReplaceGroup(-354703320);
                            ComposerKt.sourceInformation($composer2, "575@29395L167,584@29789L94,574@29349L662");
                            ComposerKt.sourceInformationMarkerStart($composer2, 681295595, "CC(remember):BasicTextField.kt#9igjgp");
                            boolean invalid$iv2 = $composer2.changedInstance(selectionState);
                            Object it$iv2 = $composer2.rememberedValue();
                            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                Object value$iv2 = (OffsetProvider) new OffsetProvider() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldSelectionHandles$1$1
                                    @Override // androidx.compose.foundation.text.selection.OffsetProvider
                                    /* JADX INFO: renamed from: provide-F1C5BW0 */
                                    public final long mo1145provideF1C5BW0() {
                                        return selectionState.getSelectionHandleState$foundation(true, true).m1493getPositionF1C5BW0();
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv2);
                                it$iv2 = value$iv2;
                            }
                            OffsetProvider offsetProvider = (OffsetProvider) it$iv2;
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ResolvedTextDirection direction = TextFieldSelectionHandles$lambda$1(startHandleState$delegate).getDirection();
                            boolean handlesCrossed = TextFieldSelectionHandles$lambda$1(startHandleState$delegate).getHandlesCrossed();
                            Modifier.Companion companion = Modifier.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer2, 681308130, "CC(remember):BasicTextField.kt#9igjgp");
                            boolean invalid$iv3 = $composer2.changedInstance(selectionState);
                            Object it$iv3 = $composer2.rememberedValue();
                            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                                Object value$iv3 = (PointerInputEventHandler) new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldSelectionHandles$2$1
                                    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                                    public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                                        TextFieldSelectionState $this$invoke_u24lambda_u240 = selectionState;
                                        Object objSelectionHandleGestures = $this$invoke_u24lambda_u240.selectionHandleGestures($this$pointerInput, true, continuation);
                                        return objSelectionHandleGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSelectionHandleGestures : Unit.INSTANCE;
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv3);
                                it$iv3 = value$iv3;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            i = -383839042;
                            AndroidSelectionHandles_androidKt.m1580SelectionHandlewLIcFTc(offsetProvider, true, direction, handlesCrossed, MinTouchTargetSizeForHandles, TextFieldSelectionHandles$lambda$1(startHandleState$delegate).getLineHeight(), SuspendingPointerInputFilterKt.pointerInput(companion, selectionState, (PointerInputEventHandler) it$iv3), $composer2, 24624, 0);
                        } else {
                            i = -383839042;
                            $composer2.startReplaceGroup(-383839042);
                        }
                        $composer2.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerStart($composer2, 681318837, "CC(remember):BasicTextField.kt#9igjgp");
                        boolean invalid$iv4 = $composer2.changed(selectionState);
                        Object it$iv4 = $composer2.rememberedValue();
                        if (invalid$iv4 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                            Object value$iv4 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda7
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return BasicTextFieldKt.TextFieldSelectionHandles$lambda$4$0(selectionState);
                                }
                            });
                            $composer2.updateRememberedValue(value$iv4);
                            it$iv4 = value$iv4;
                        }
                        State endHandleState$delegate = (State) it$iv4;
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (TextFieldSelectionHandles$lambda$5(endHandleState$delegate).getVisible()) {
                            $composer2.startReplaceGroup(-353657845);
                            ComposerKt.sourceInformation($composer2, "604@30449L168,613@30841L95,603@30403L659");
                            ComposerKt.sourceInformationMarkerStart($composer2, 681329324, "CC(remember):BasicTextField.kt#9igjgp");
                            boolean invalid$iv5 = $composer2.changedInstance(selectionState);
                            Object it$iv5 = $composer2.rememberedValue();
                            if (invalid$iv5 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                                Object value$iv5 = (OffsetProvider) new OffsetProvider() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldSelectionHandles$3$1
                                    @Override // androidx.compose.foundation.text.selection.OffsetProvider
                                    /* JADX INFO: renamed from: provide-F1C5BW0 */
                                    public final long mo1145provideF1C5BW0() {
                                        return selectionState.getSelectionHandleState$foundation(false, true).m1493getPositionF1C5BW0();
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv5);
                                it$iv5 = value$iv5;
                            }
                            OffsetProvider offsetProvider2 = (OffsetProvider) it$iv5;
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ResolvedTextDirection direction2 = TextFieldSelectionHandles$lambda$5(endHandleState$delegate).getDirection();
                            boolean handlesCrossed2 = TextFieldSelectionHandles$lambda$5(endHandleState$delegate).getHandlesCrossed();
                            Modifier.Companion companion2 = Modifier.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer2, 681341795, "CC(remember):BasicTextField.kt#9igjgp");
                            boolean invalid$iv6 = $composer2.changedInstance(selectionState);
                            Object it$iv6 = $composer2.rememberedValue();
                            if (invalid$iv6 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                                Object value$iv6 = (PointerInputEventHandler) new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$TextFieldSelectionHandles$4$1
                                    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                                    public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                                        TextFieldSelectionState $this$invoke_u24lambda_u240 = selectionState;
                                        Object objSelectionHandleGestures = $this$invoke_u24lambda_u240.selectionHandleGestures($this$pointerInput, false, continuation);
                                        return objSelectionHandleGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSelectionHandleGestures : Unit.INSTANCE;
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv6);
                                it$iv6 = value$iv6;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            AndroidSelectionHandles_androidKt.m1580SelectionHandlewLIcFTc(offsetProvider2, false, direction2, handlesCrossed2, MinTouchTargetSizeForHandles, TextFieldSelectionHandles$lambda$5(endHandleState$delegate).getLineHeight(), SuspendingPointerInputFilterKt.pointerInput(companion2, selectionState, (PointerInputEventHandler) it$iv6), $composer2, 24624, 0);
                        } else {
                            $composer2.startReplaceGroup(i);
                        }
                        $composer2.endReplaceGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        $composer2.skipToGroupEnd();
                    }
                    ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda8
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return BasicTextFieldKt.TextFieldSelectionHandles$lambda$8(selectionState, $changed, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }

                private static final TextFieldHandleState TextFieldSelectionHandles$lambda$1(State<TextFieldHandleState> state) {
                    Object thisObj$iv = state.getValue();
                    return (TextFieldHandleState) thisObj$iv;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final TextFieldHandleState TextFieldSelectionHandles$lambda$0$0(TextFieldSelectionState $selectionState) {
                    return $selectionState.getSelectionHandleState$foundation(true, false);
                }

                private static final TextFieldHandleState TextFieldSelectionHandles$lambda$5(State<TextFieldHandleState> state) {
                    Object thisObj$iv = state.getValue();
                    return (TextFieldHandleState) thisObj$iv;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final TextFieldHandleState TextFieldSelectionHandles$lambda$4$0(TextFieldSelectionState $selectionState) {
                    return $selectionState.getSelectionHandleState$foundation(false, false);
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit BasicTextField$lambda$16$0(TextLayoutResult it) {
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code duplicated, block: B:246:0x0441  */
                /* JADX WARN: Code duplicated, block: B:247:0x0443  */
                /* JADX WARN: Code duplicated, block: B:250:0x044f  */
                /* JADX WARN: Code duplicated, block: B:252:0x0459  */
                /* JADX WARN: Code duplicated, block: B:253:0x045c  */
                /* JADX WARN: Code duplicated, block: B:257:0x047e  */
                /* JADX WARN: Code duplicated, block: B:258:0x0481  */
                /* JADX WARN: Code duplicated, block: B:260:0x0485  */
                /* JADX WARN: Code duplicated, block: B:261:0x0487  */
                /* JADX WARN: Code duplicated, block: B:264:0x049c  */
                /* JADX WARN: Code duplicated, block: B:265:0x049e  */
                /* JADX WARN: Code duplicated, block: B:268:0x04ac  */
                /* JADX WARN: Code duplicated, block: B:271:0x04b7 A[ADDED_TO_REGION] */
                /* JADX WARN: Code duplicated, block: B:275:0x052d  */
                public static final void BasicTextField(final String value, final Function1<? super String, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, VisualTransformation visualTransformation, Function1<? super TextLayoutResult, Unit> function2, MutableInteractionSource interactionSource, Brush cursorBrush, Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
                    Modifier modifier2;
                    boolean enabled2;
                    boolean readOnly2;
                    TextStyle textStyle2;
                    KeyboardOptions keyboardOptions2;
                    KeyboardActions keyboardActions2;
                    int i2;
                    int i3;
                    int i4;
                    Composer $composer2;
                    final int maxLines2;
                    final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function4;
                    final KeyboardActions keyboardActions3;
                    final boolean enabled3;
                    final KeyboardOptions keyboardOptions3;
                    final boolean readOnly3;
                    final TextStyle textStyle3;
                    final Modifier modifier3;
                    final boolean singleLine2;
                    final int minLines2;
                    final VisualTransformation visualTransformation2;
                    final Function1<? super TextLayoutResult, Unit> function5;
                    final MutableInteractionSource interactionSource2;
                    final Brush cursorBrush2;
                    int maxLines3;
                    Function1<? super TextLayoutResult, Unit> function6;
                    KeyboardActions keyboardActions4;
                    boolean singleLine3;
                    Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> lambda$759698998$foundation;
                    int maxLines4;
                    int minLines3;
                    TextStyle textStyle4;
                    Modifier modifier4;
                    VisualTransformation visualTransformation3;
                    int $dirty;
                    boolean readOnly4;
                    Function1<? super TextLayoutResult, Unit> function7;
                    Brush cursorBrush3;
                    KeyboardOptions keyboardOptions4;
                    MutableInteractionSource interactionSource3;
                    Object value$iv;
                    TextStyle textStyle5;
                    boolean invalid$iv;
                    Object it$iv;
                    TextFieldValue textFieldValue;
                    int i5;
                    int i6;
                    boolean z;
                    boolean invalid$iv2;
                    Object value$iv2;
                    Composer $composer3 = $composer.startRestartGroup(2026950908);
                    ComposerKt.sourceInformation($composer3, "C(BasicTextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,keyboardOptions,keyboardActions,singleLine,maxLines,minLines,visualTransformation,onTextLayout,interactionSource,cursorBrush,decorationBox)752@39049L57,758@39402L242,758@39391L253,769@39965L41,773@40083L373,771@40012L1033:BasicTextField.kt#423gt5");
                    int $dirty2 = $changed;
                    int $dirty1 = $changed1;
                    if (($changed & 6) == 0) {
                        $dirty2 |= $composer3.changed(value) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty2 |= $composer3.changedInstance(function1) ? 32 : 16;
                    }
                    int i7 = i & 4;
                    if (i7 != 0) {
                        $dirty2 |= 384;
                        modifier2 = modifier;
                    } else if (($changed & 384) == 0) {
                        modifier2 = modifier;
                        $dirty2 |= $composer3.changed(modifier2) ? 256 : 128;
                    } else {
                        modifier2 = modifier;
                    }
                    int i8 = i & 8;
                    if (i8 != 0) {
                        $dirty2 |= 3072;
                        enabled2 = enabled;
                    } else if (($changed & 3072) == 0) {
                        enabled2 = enabled;
                        $dirty2 |= $composer3.changed(enabled2) ? 2048 : 1024;
                    } else {
                        enabled2 = enabled;
                    }
                    int i9 = i & 16;
                    if (i9 != 0) {
                        $dirty2 |= 24576;
                        readOnly2 = readOnly;
                    } else if (($changed & 24576) == 0) {
                        readOnly2 = readOnly;
                        $dirty2 |= $composer3.changed(readOnly2) ? 16384 : 8192;
                    } else {
                        readOnly2 = readOnly;
                    }
                    int i10 = i & 32;
                    if (i10 != 0) {
                        $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        textStyle2 = textStyle;
                    } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        textStyle2 = textStyle;
                        $dirty2 |= $composer3.changed(textStyle2) ? 131072 : 65536;
                    } else {
                        textStyle2 = textStyle;
                    }
                    int i11 = i & 64;
                    if (i11 != 0) {
                        $dirty2 |= 1572864;
                        keyboardOptions2 = keyboardOptions;
                    } else if (($changed & 1572864) == 0) {
                        keyboardOptions2 = keyboardOptions;
                        $dirty2 |= $composer3.changed(keyboardOptions2) ? 1048576 : 524288;
                    } else {
                        keyboardOptions2 = keyboardOptions;
                    }
                    int i12 = i & 128;
                    if (i12 != 0) {
                        $dirty2 |= 12582912;
                        keyboardActions2 = keyboardActions;
                    } else if (($changed & 12582912) == 0) {
                        keyboardActions2 = keyboardActions;
                        $dirty2 |= $composer3.changed(keyboardActions2) ? 8388608 : 4194304;
                    } else {
                        keyboardActions2 = keyboardActions;
                    }
                    int i13 = i & 256;
                    if (i13 != 0) {
                        $dirty2 |= 100663296;
                    } else if (($changed & 100663296) == 0) {
                        $dirty2 |= $composer3.changed(singleLine) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                    }
                    if (($changed & C.ENCODING_PCM_32BIT) == 0) {
                        $dirty2 |= ((i & 512) == 0 && $composer3.changed(maxLines)) ? 536870912 : 268435456;
                    }
                    int i14 = i & 1024;
                    if (i14 != 0) {
                        $dirty1 |= 6;
                    } else if (($changed1 & 6) == 0) {
                        $dirty1 |= $composer3.changed(minLines) ? 4 : 2;
                    }
                    int i15 = i & 2048;
                    if (i15 != 0) {
                        $dirty1 |= 48;
                    } else if (($changed1 & 48) == 0) {
                        $dirty1 |= $composer3.changed(visualTransformation) ? 32 : 16;
                    }
                    int i16 = i & 4096;
                    if (i16 != 0) {
                        $dirty1 |= 384;
                        i2 = i16;
                    } else {
                        i2 = i16;
                        if (($changed1 & 384) == 0) {
                            $dirty1 |= $composer3.changedInstance(function2) ? 256 : 128;
                        }
                    }
                    int i17 = i & 8192;
                    if (i17 != 0) {
                        $dirty1 |= 3072;
                        i3 = i17;
                    } else {
                        i3 = i17;
                        if (($changed1 & 3072) == 0) {
                            $dirty1 |= $composer3.changed(interactionSource) ? 2048 : 1024;
                        }
                    }
                    int i18 = i & 16384;
                    if (i18 != 0) {
                        $dirty1 |= 24576;
                        i4 = i18;
                    } else {
                        i4 = i18;
                        if (($changed1 & 24576) == 0) {
                            $dirty1 |= $composer3.changed(cursorBrush) ? 16384 : 8192;
                        }
                    }
                    int i19 = i & 32768;
                    if (i19 != 0) {
                        $dirty1 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        $dirty1 |= $composer3.changedInstance(function3) ? 131072 : 65536;
                    }
                    if ($composer3.shouldExecute((($dirty2 & 306783379) == 306783378 && (74899 & $dirty1) == 74898) ? false : true, $dirty2 & 1)) {
                        $composer3.startDefaults();
                        ComposerKt.sourceInformation($composer3, "744@38634L2");
                        if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            boolean enabled4 = i8 != 0 ? true : enabled2;
                            if (i9 != 0) {
                                readOnly2 = false;
                            }
                            if (i10 != 0) {
                                textStyle2 = TextStyle.INSTANCE.getDefault();
                            }
                            if (i11 != 0) {
                                keyboardOptions2 = KeyboardOptions.INSTANCE.getDefault();
                            }
                            KeyboardActions keyboardActions5 = i12 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions2;
                            boolean singleLine4 = i13 != 0 ? false : singleLine;
                            if ((i & 512) != 0) {
                                maxLines3 = singleLine4 ? 1 : Integer.MAX_VALUE;
                                $dirty2 &= -1879048193;
                            } else {
                                maxLines3 = maxLines;
                            }
                            int minLines4 = i14 != 0 ? 1 : minLines;
                            VisualTransformation visualTransformation4 = i15 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                            if (i2 != 0) {
                                ComposerKt.sourceInformationMarkerStart($composer3, -2015311170, "CC(remember):BasicTextField.kt#9igjgp");
                                Object it$iv2 = $composer3.rememberedValue();
                                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv = new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda12
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            return BasicTextFieldKt.BasicTextField$lambda$16$0((TextLayoutResult) obj);
                                        }
                                    };
                                    $composer3.updateRememberedValue(value$iv);
                                } else {
                                    value$iv = it$iv2;
                                }
                                function6 = (Function1) value$iv;
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                            } else {
                                function6 = function2;
                            }
                            MutableInteractionSource interactionSource4 = i3 != 0 ? null : interactionSource;
                            SolidColor cursorBrush4 = i4 != 0 ? new SolidColor(Color.INSTANCE.m5123getBlack0d7_KjU(), null) : cursorBrush;
                            if (i19 != 0) {
                                keyboardActions4 = keyboardActions5;
                                lambda$759698998$foundation = ComposableSingletons$BasicTextFieldKt.INSTANCE.getLambda$759698998$foundation();
                                maxLines4 = maxLines3;
                                minLines3 = minLines4;
                                textStyle4 = textStyle2;
                                modifier4 = modifier2;
                                visualTransformation3 = visualTransformation4;
                                enabled2 = enabled4;
                                singleLine3 = singleLine4;
                                $dirty = $dirty2;
                                readOnly4 = readOnly2;
                                function7 = function6;
                                cursorBrush3 = cursorBrush4;
                                keyboardOptions4 = keyboardOptions2;
                                interactionSource3 = interactionSource4;
                            } else {
                                keyboardActions4 = keyboardActions5;
                                singleLine3 = singleLine4;
                                lambda$759698998$foundation = function3;
                                maxLines4 = maxLines3;
                                minLines3 = minLines4;
                                textStyle4 = textStyle2;
                                modifier4 = modifier2;
                                visualTransformation3 = visualTransformation4;
                                enabled2 = enabled4;
                                $dirty = $dirty2;
                                readOnly4 = readOnly2;
                                function7 = function6;
                                cursorBrush3 = cursorBrush4;
                                keyboardOptions4 = keyboardOptions2;
                                interactionSource3 = interactionSource4;
                            }
                        } else {
                            $composer3.skipToGroupEnd();
                            if ((i & 512) != 0) {
                                $dirty2 &= -1879048193;
                            }
                            singleLine3 = singleLine;
                            maxLines4 = maxLines;
                            minLines3 = minLines;
                            visualTransformation3 = visualTransformation;
                            lambda$759698998$foundation = function3;
                            keyboardActions4 = keyboardActions2;
                            keyboardOptions4 = keyboardOptions2;
                            textStyle4 = textStyle2;
                            modifier4 = modifier2;
                            interactionSource3 = interactionSource;
                            $dirty = $dirty2;
                            readOnly4 = readOnly2;
                            function7 = function2;
                            cursorBrush3 = cursorBrush;
                        }
                        $composer3.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(2026950908, $dirty, $dirty1, "androidx.compose.foundation.text.BasicTextField (BasicTextField.kt:749)");
                        }
                        ComposerKt.sourceInformationMarkerStart($composer3, -2015297835, "CC(remember):BasicTextField.kt#9igjgp");
                        Object value$iv3 = $composer3.rememberedValue();
                        Function1<? super TextLayoutResult, Unit> function8 = function7;
                        if (value$iv3 == Composer.INSTANCE.getEmpty()) {
                            value$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue(value, 0L, (TextRange) null, 6, (DefaultConstructorMarker) null), null, 2, null);
                            $composer3.updateRememberedValue(value$iv3);
                        }
                        final MutableState textFieldValueState$delegate = (MutableState) value$iv3;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        final TextFieldValue textFieldValue2 = TextFieldValue.m7574copy3r_uNRQ$default(BasicTextField$lambda$18(textFieldValueState$delegate), value, 0L, (TextRange) null, 6, (Object) null);
                        ComposerKt.sourceInformationMarkerStart($composer3, -2015286354, "CC(remember):BasicTextField.kt#9igjgp");
                        boolean invalid$iv3 = $composer3.changed(textFieldValue2);
                        Modifier modifier5 = modifier4;
                        Object value$iv4 = $composer3.rememberedValue();
                        if (invalid$iv3) {
                            textStyle5 = textStyle4;
                        } else {
                            textStyle5 = textStyle4;
                            if (value$iv4 != Composer.INSTANCE.getEmpty()) {
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            EffectsKt.SideEffect((Function0) value$iv4, $composer3, 0);
                            ComposerKt.sourceInformationMarkerStart($composer3, -2015268539, "CC(remember):BasicTextField.kt#9igjgp");
                            if (($dirty & 14) == 4) {
                                invalid$iv = true;
                            } else {
                                invalid$iv = false;
                            }
                            it$iv = $composer3.rememberedValue();
                            if (invalid$iv) {
                                textFieldValue = textFieldValue2;
                            } else {
                                textFieldValue = textFieldValue2;
                                if (it$iv != Composer.INSTANCE.getEmpty()) {
                                }
                                final MutableState lastTextValue$delegate = (MutableState) it$iv;
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ImeOptions imeOptions$foundation = keyboardOptions4.toImeOptions$foundation(singleLine3);
                                boolean z2 = !singleLine3;
                                if (singleLine3) {
                                    i5 = 1;
                                } else {
                                    i5 = minLines3;
                                }
                                if (singleLine3) {
                                    i6 = 1;
                                } else {
                                    i6 = maxLines4;
                                }
                                ComposerKt.sourceInformationMarkerStart($composer3, -2015264431, "CC(remember):BasicTextField.kt#9igjgp");
                                boolean zChanged = $composer3.changed(lastTextValue$delegate);
                                KeyboardOptions keyboardOptions5 = keyboardOptions4;
                                if (($dirty & 112) == 32) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                invalid$iv2 = zChanged | z;
                                boolean singleLine5 = singleLine3;
                                value$iv2 = $composer3.rememberedValue();
                                if (!invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv2 = new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda14
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            return BasicTextFieldKt.BasicTextField$lambda$24$0(function1, textFieldValueState$delegate, lastTextValue$delegate, (TextFieldValue) obj);
                                        }
                                    };
                                    $composer3.updateRememberedValue(value$iv2);
                                }
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                TextStyle textStyle6 = textStyle5;
                                $composer2 = $composer3;
                                boolean enabled5 = enabled2;
                                CoreTextFieldKt.CoreTextField(textFieldValue, (Function1) value$iv2, modifier5, textStyle6, visualTransformation3, function8, interactionSource3, cursorBrush3, z2, i6, i5, imeOptions$foundation, keyboardActions4, enabled5, readOnly4, lambda$759698998$foundation, null, $composer2, ($dirty & 896) | (($dirty >> 6) & 7168) | (($dirty1 << 9) & 57344) | (($dirty1 << 9) & 458752) | (($dirty1 << 9) & 3670016) | (($dirty1 << 9) & 29360128), (($dirty >> 15) & 896) | ($dirty & 7168) | (57344 & $dirty) | ($dirty1 & 458752), 65536);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier5;
                                textStyle3 = textStyle6;
                                interactionSource2 = interactionSource3;
                                cursorBrush2 = cursorBrush3;
                                keyboardActions3 = keyboardActions4;
                                enabled3 = enabled5;
                                readOnly3 = readOnly4;
                                function4 = lambda$759698998$foundation;
                                singleLine2 = singleLine5;
                                keyboardOptions3 = keyboardOptions5;
                                visualTransformation2 = visualTransformation3;
                                function5 = function8;
                                maxLines2 = maxLines4;
                                minLines2 = minLines3;
                            }
                            Object value$iv5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(value, null, 2, null);
                            $composer3.updateRememberedValue(value$iv5);
                            it$iv = value$iv5;
                            final MutableState lastTextValue$delegate2 = (MutableState) it$iv;
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ImeOptions imeOptions$foundation2 = keyboardOptions4.toImeOptions$foundation(singleLine3);
                            boolean z3 = !singleLine3;
                            if (singleLine3) {
                                i5 = 1;
                            } else {
                                i5 = minLines3;
                            }
                            if (singleLine3) {
                                i6 = 1;
                            } else {
                                i6 = maxLines4;
                            }
                            ComposerKt.sourceInformationMarkerStart($composer3, -2015264431, "CC(remember):BasicTextField.kt#9igjgp");
                            boolean zChanged2 = $composer3.changed(lastTextValue$delegate2);
                            KeyboardOptions keyboardOptions6 = keyboardOptions4;
                            if (($dirty & 112) == 32) {
                                z = true;
                            } else {
                                z = false;
                            }
                            invalid$iv2 = zChanged2 | z;
                            boolean singleLine6 = singleLine3;
                            value$iv2 = $composer3.rememberedValue();
                            if (!invalid$iv2) {
                            }
                            value$iv2 = new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda14
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return BasicTextFieldKt.BasicTextField$lambda$24$0(function1, textFieldValueState$delegate, lastTextValue$delegate2, (TextFieldValue) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            TextStyle textStyle7 = textStyle5;
                            $composer2 = $composer3;
                            boolean enabled6 = enabled2;
                            CoreTextFieldKt.CoreTextField(textFieldValue, (Function1) value$iv2, modifier5, textStyle7, visualTransformation3, function8, interactionSource3, cursorBrush3, z3, i6, i5, imeOptions$foundation2, keyboardActions4, enabled6, readOnly4, lambda$759698998$foundation, null, $composer2, ($dirty & 896) | (($dirty >> 6) & 7168) | (($dirty1 << 9) & 57344) | (($dirty1 << 9) & 458752) | (($dirty1 << 9) & 3670016) | (($dirty1 << 9) & 29360128), (($dirty >> 15) & 896) | ($dirty & 7168) | (57344 & $dirty) | ($dirty1 & 458752), 65536);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier5;
                            textStyle3 = textStyle7;
                            interactionSource2 = interactionSource3;
                            cursorBrush2 = cursorBrush3;
                            keyboardActions3 = keyboardActions4;
                            enabled3 = enabled6;
                            readOnly3 = readOnly4;
                            function4 = lambda$759698998$foundation;
                            singleLine2 = singleLine6;
                            keyboardOptions3 = keyboardOptions6;
                            visualTransformation2 = visualTransformation3;
                            function5 = function8;
                            maxLines2 = maxLines4;
                            minLines2 = minLines3;
                        }
                        value$iv4 = new Function0() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda13
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return BasicTextFieldKt.BasicTextField$lambda$20$0(textFieldValue2, textFieldValueState$delegate);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv4);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        EffectsKt.SideEffect((Function0) value$iv4, $composer3, 0);
                        ComposerKt.sourceInformationMarkerStart($composer3, -2015268539, "CC(remember):BasicTextField.kt#9igjgp");
                        if (($dirty & 14) == 4) {
                            invalid$iv = true;
                        } else {
                            invalid$iv = false;
                        }
                        it$iv = $composer3.rememberedValue();
                        if (invalid$iv) {
                            textFieldValue = textFieldValue2;
                            if (it$iv != Composer.INSTANCE.getEmpty()) {
                            }
                            final MutableState lastTextValue$delegate3 = (MutableState) it$iv;
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ImeOptions imeOptions$foundation3 = keyboardOptions4.toImeOptions$foundation(singleLine3);
                            boolean z4 = !singleLine3;
                            if (singleLine3) {
                                i5 = 1;
                            } else {
                                i5 = minLines3;
                            }
                            if (singleLine3) {
                                i6 = 1;
                            } else {
                                i6 = maxLines4;
                            }
                            ComposerKt.sourceInformationMarkerStart($composer3, -2015264431, "CC(remember):BasicTextField.kt#9igjgp");
                            boolean zChanged3 = $composer3.changed(lastTextValue$delegate3);
                            KeyboardOptions keyboardOptions7 = keyboardOptions4;
                            if (($dirty & 112) == 32) {
                                z = true;
                            } else {
                                z = false;
                            }
                            invalid$iv2 = zChanged3 | z;
                            boolean singleLine7 = singleLine3;
                            value$iv2 = $composer3.rememberedValue();
                            if (!invalid$iv2) {
                            }
                            value$iv2 = new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda14
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return BasicTextFieldKt.BasicTextField$lambda$24$0(function1, textFieldValueState$delegate, lastTextValue$delegate3, (TextFieldValue) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            TextStyle textStyle8 = textStyle5;
                            $composer2 = $composer3;
                            boolean enabled7 = enabled2;
                            CoreTextFieldKt.CoreTextField(textFieldValue, (Function1) value$iv2, modifier5, textStyle8, visualTransformation3, function8, interactionSource3, cursorBrush3, z4, i6, i5, imeOptions$foundation3, keyboardActions4, enabled7, readOnly4, lambda$759698998$foundation, null, $composer2, ($dirty & 896) | (($dirty >> 6) & 7168) | (($dirty1 << 9) & 57344) | (($dirty1 << 9) & 458752) | (($dirty1 << 9) & 3670016) | (($dirty1 << 9) & 29360128), (($dirty >> 15) & 896) | ($dirty & 7168) | (57344 & $dirty) | ($dirty1 & 458752), 65536);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier5;
                            textStyle3 = textStyle8;
                            interactionSource2 = interactionSource3;
                            cursorBrush2 = cursorBrush3;
                            keyboardActions3 = keyboardActions4;
                            enabled3 = enabled7;
                            readOnly3 = readOnly4;
                            function4 = lambda$759698998$foundation;
                            singleLine2 = singleLine7;
                            keyboardOptions3 = keyboardOptions7;
                            visualTransformation2 = visualTransformation3;
                            function5 = function8;
                            maxLines2 = maxLines4;
                            minLines2 = minLines3;
                        } else {
                            textFieldValue = textFieldValue2;
                        }
                        Object value$iv6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(value, null, 2, null);
                        $composer3.updateRememberedValue(value$iv6);
                        it$iv = value$iv6;
                        final MutableState lastTextValue$delegate4 = (MutableState) it$iv;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ImeOptions imeOptions$foundation4 = keyboardOptions4.toImeOptions$foundation(singleLine3);
                        boolean z5 = !singleLine3;
                        if (singleLine3) {
                            i5 = 1;
                        } else {
                            i5 = minLines3;
                        }
                        if (singleLine3) {
                            i6 = 1;
                        } else {
                            i6 = maxLines4;
                        }
                        ComposerKt.sourceInformationMarkerStart($composer3, -2015264431, "CC(remember):BasicTextField.kt#9igjgp");
                        boolean zChanged4 = $composer3.changed(lastTextValue$delegate4);
                        KeyboardOptions keyboardOptions8 = keyboardOptions4;
                        if (($dirty & 112) == 32) {
                            z = true;
                        } else {
                            z = false;
                        }
                        invalid$iv2 = zChanged4 | z;
                        boolean singleLine8 = singleLine3;
                        value$iv2 = $composer3.rememberedValue();
                        if (!invalid$iv2) {
                        }
                        value$iv2 = new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return BasicTextFieldKt.BasicTextField$lambda$24$0(function1, textFieldValueState$delegate, lastTextValue$delegate4, (TextFieldValue) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        TextStyle textStyle9 = textStyle5;
                        $composer2 = $composer3;
                        boolean enabled8 = enabled2;
                        CoreTextFieldKt.CoreTextField(textFieldValue, (Function1) value$iv2, modifier5, textStyle9, visualTransformation3, function8, interactionSource3, cursorBrush3, z5, i6, i5, imeOptions$foundation4, keyboardActions4, enabled8, readOnly4, lambda$759698998$foundation, null, $composer2, ($dirty & 896) | (($dirty >> 6) & 7168) | (($dirty1 << 9) & 57344) | (($dirty1 << 9) & 458752) | (($dirty1 << 9) & 3670016) | (($dirty1 << 9) & 29360128), (($dirty >> 15) & 896) | ($dirty & 7168) | (57344 & $dirty) | ($dirty1 & 458752), 65536);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier5;
                        textStyle3 = textStyle9;
                        interactionSource2 = interactionSource3;
                        cursorBrush2 = cursorBrush3;
                        keyboardActions3 = keyboardActions4;
                        enabled3 = enabled8;
                        readOnly3 = readOnly4;
                        function4 = lambda$759698998$foundation;
                        singleLine2 = singleLine8;
                        keyboardOptions3 = keyboardOptions8;
                        visualTransformation2 = visualTransformation3;
                        function5 = function8;
                        maxLines2 = maxLines4;
                        minLines2 = minLines3;
                    } else {
                        $composer2 = $composer3;
                        $composer2.skipToGroupEnd();
                        maxLines2 = maxLines;
                        function4 = function3;
                        keyboardActions3 = keyboardActions2;
                        enabled3 = enabled2;
                        keyboardOptions3 = keyboardOptions2;
                        readOnly3 = readOnly2;
                        textStyle3 = textStyle2;
                        modifier3 = modifier2;
                        singleLine2 = singleLine;
                        minLines2 = minLines;
                        visualTransformation2 = visualTransformation;
                        function5 = function2;
                        interactionSource2 = interactionSource;
                        cursorBrush2 = cursorBrush;
                    }
                    ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda15
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return BasicTextFieldKt.BasicTextField$lambda$25(value, function1, modifier3, enabled3, readOnly3, textStyle3, keyboardOptions3, keyboardActions3, singleLine2, maxLines2, minLines2, visualTransformation2, function5, interactionSource2, cursorBrush2, function4, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }

                private static final TextFieldValue BasicTextField$lambda$18(MutableState<TextFieldValue> mutableState) {
                    MutableState<TextFieldValue> $this$getValue$iv = mutableState;
                    return $this$getValue$iv.getValue();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit BasicTextField$lambda$20$0(TextFieldValue $textFieldValue, MutableState $textFieldValueState$delegate) {
                    if (!TextRange.m7321equalsimpl0($textFieldValue.getSelection(), BasicTextField$lambda$18($textFieldValueState$delegate).getSelection()) || !Intrinsics.areEqual($textFieldValue.getComposition(), BasicTextField$lambda$18($textFieldValueState$delegate).getComposition())) {
                        $textFieldValueState$delegate.setValue($textFieldValue);
                    }
                    return Unit.INSTANCE;
                }

                private static final String BasicTextField$lambda$22(MutableState<String> mutableState) {
                    MutableState<String> $this$getValue$iv = mutableState;
                    return $this$getValue$iv.getValue();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit BasicTextField$lambda$24$0(Function1 $onValueChange, MutableState $textFieldValueState$delegate, MutableState $lastTextValue$delegate, TextFieldValue newTextFieldValueState) {
                    $textFieldValueState$delegate.setValue(newTextFieldValueState);
                    boolean stringChangedSinceLastInvocation = !Intrinsics.areEqual(BasicTextField$lambda$22($lastTextValue$delegate), newTextFieldValueState.getText());
                    $lastTextValue$delegate.setValue(newTextFieldValueState.getText());
                    if (stringChangedSinceLastInvocation) {
                        $onValueChange.invoke(newTextFieldValueState.getText());
                    }
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit BasicTextField$lambda$26$0(TextLayoutResult it) {
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code duplicated, block: B:258:0x0453  */
                public static final void BasicTextField(final TextFieldValue value, final Function1<? super TextFieldValue, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, VisualTransformation visualTransformation, Function1<? super TextLayoutResult, Unit> function2, MutableInteractionSource interactionSource, Brush cursorBrush, Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
                    Modifier modifier2;
                    boolean enabled2;
                    boolean readOnly2;
                    TextStyle textStyle2;
                    KeyboardOptions keyboardOptions2;
                    KeyboardActions keyboardActions2;
                    int i2;
                    int i3;
                    int i4;
                    Composer $composer2;
                    final boolean singleLine2;
                    final int minLines2;
                    final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function4;
                    final KeyboardActions keyboardActions3;
                    final Modifier modifier3;
                    final boolean enabled3;
                    final KeyboardOptions keyboardOptions3;
                    final boolean readOnly3;
                    final TextStyle textStyle3;
                    final int maxLines2;
                    final VisualTransformation visualTransformation2;
                    final Function1<? super TextLayoutResult, Unit> function5;
                    final MutableInteractionSource interactionSource2;
                    final Brush cursorBrush2;
                    Modifier.Companion modifier4;
                    int maxLines3;
                    VisualTransformation visualTransformation3;
                    Function1<? super TextLayoutResult, Unit> function6;
                    boolean singleLine3;
                    int maxLines4;
                    int minLines3;
                    KeyboardOptions keyboardOptions4;
                    TextStyle textStyle4;
                    MutableInteractionSource interactionSource3;
                    Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> lambda$486633673$foundation;
                    Brush cursorBrush3;
                    int $dirty;
                    boolean enabled4;
                    KeyboardActions keyboardActions4;
                    Function1<? super TextLayoutResult, Unit> function7;
                    boolean singleLine4;
                    Composer $composer3 = $composer.startRestartGroup(-971111025);
                    ComposerKt.sourceInformation($composer3, "C(BasicTextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,keyboardOptions,keyboardActions,singleLine,maxLines,minLines,visualTransformation,onTextLayout,interactionSource,cursorBrush,decorationBox)909@47938L90,907@47876L741:BasicTextField.kt#423gt5");
                    int $dirty2 = $changed;
                    int $dirty1 = $changed1;
                    if (($changed & 6) == 0) {
                        $dirty2 |= $composer3.changed(value) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty2 |= $composer3.changedInstance(function1) ? 32 : 16;
                    }
                    int i5 = i & 4;
                    if (i5 != 0) {
                        $dirty2 |= 384;
                        modifier2 = modifier;
                    } else if (($changed & 384) == 0) {
                        modifier2 = modifier;
                        $dirty2 |= $composer3.changed(modifier2) ? 256 : 128;
                    } else {
                        modifier2 = modifier;
                    }
                    int i6 = i & 8;
                    if (i6 != 0) {
                        $dirty2 |= 3072;
                        enabled2 = enabled;
                    } else if (($changed & 3072) == 0) {
                        enabled2 = enabled;
                        $dirty2 |= $composer3.changed(enabled2) ? 2048 : 1024;
                    } else {
                        enabled2 = enabled;
                    }
                    int i7 = i & 16;
                    if (i7 != 0) {
                        $dirty2 |= 24576;
                        readOnly2 = readOnly;
                    } else if (($changed & 24576) == 0) {
                        readOnly2 = readOnly;
                        $dirty2 |= $composer3.changed(readOnly2) ? 16384 : 8192;
                    } else {
                        readOnly2 = readOnly;
                    }
                    int i8 = i & 32;
                    if (i8 != 0) {
                        $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        textStyle2 = textStyle;
                    } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        textStyle2 = textStyle;
                        $dirty2 |= $composer3.changed(textStyle2) ? 131072 : 65536;
                    } else {
                        textStyle2 = textStyle;
                    }
                    int i9 = i & 64;
                    if (i9 != 0) {
                        $dirty2 |= 1572864;
                        keyboardOptions2 = keyboardOptions;
                    } else if (($changed & 1572864) == 0) {
                        keyboardOptions2 = keyboardOptions;
                        $dirty2 |= $composer3.changed(keyboardOptions2) ? 1048576 : 524288;
                    } else {
                        keyboardOptions2 = keyboardOptions;
                    }
                    int i10 = i & 128;
                    if (i10 != 0) {
                        $dirty2 |= 12582912;
                        keyboardActions2 = keyboardActions;
                    } else if (($changed & 12582912) == 0) {
                        keyboardActions2 = keyboardActions;
                        $dirty2 |= $composer3.changed(keyboardActions2) ? 8388608 : 4194304;
                    } else {
                        keyboardActions2 = keyboardActions;
                    }
                    int i11 = i & 256;
                    if (i11 != 0) {
                        $dirty2 |= 100663296;
                    } else if (($changed & 100663296) == 0) {
                        $dirty2 |= $composer3.changed(singleLine) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                    }
                    if (($changed & C.ENCODING_PCM_32BIT) == 0) {
                        $dirty2 |= ((i & 512) == 0 && $composer3.changed(maxLines)) ? 536870912 : 268435456;
                    }
                    int i12 = i & 1024;
                    if (i12 != 0) {
                        $dirty1 |= 6;
                    } else if (($changed1 & 6) == 0) {
                        $dirty1 |= $composer3.changed(minLines) ? 4 : 2;
                    }
                    int i13 = i & 2048;
                    if (i13 != 0) {
                        $dirty1 |= 48;
                    } else if (($changed1 & 48) == 0) {
                        $dirty1 |= $composer3.changed(visualTransformation) ? 32 : 16;
                    }
                    int i14 = i & 4096;
                    if (i14 != 0) {
                        $dirty1 |= 384;
                        i2 = i14;
                    } else {
                        i2 = i14;
                        if (($changed1 & 384) == 0) {
                            $dirty1 |= $composer3.changedInstance(function2) ? 256 : 128;
                        }
                    }
                    int i15 = i & 8192;
                    if (i15 != 0) {
                        $dirty1 |= 3072;
                        i3 = i15;
                    } else {
                        i3 = i15;
                        if (($changed1 & 3072) == 0) {
                            $dirty1 |= $composer3.changed(interactionSource) ? 2048 : 1024;
                        }
                    }
                    int i16 = i & 16384;
                    if (i16 != 0) {
                        $dirty1 |= 24576;
                        i4 = i16;
                    } else {
                        i4 = i16;
                        if (($changed1 & 24576) == 0) {
                            $dirty1 |= $composer3.changed(cursorBrush) ? 16384 : 8192;
                        }
                    }
                    int i17 = i & 32768;
                    if (i17 != 0) {
                        $dirty1 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        $dirty1 |= $composer3.changedInstance(function3) ? 131072 : 65536;
                    }
                    if ($composer3.shouldExecute((($dirty2 & 306783379) == 306783378 && (74899 & $dirty1) == 74898) ? false : true, $dirty2 & 1)) {
                        $composer3.startDefaults();
                        ComposerKt.sourceInformation($composer3, "901@47615L2");
                        if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                            modifier4 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                            if (i6 != 0) {
                                enabled2 = true;
                            }
                            if (i7 != 0) {
                                readOnly2 = false;
                            }
                            if (i8 != 0) {
                                textStyle2 = TextStyle.INSTANCE.getDefault();
                            }
                            if (i9 != 0) {
                                keyboardOptions2 = KeyboardOptions.INSTANCE.getDefault();
                            }
                            KeyboardActions keyboardActions5 = i10 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions2;
                            boolean singleLine5 = i11 != 0 ? false : singleLine;
                            if ((i & 512) != 0) {
                                maxLines3 = singleLine5 ? 1 : Integer.MAX_VALUE;
                                $dirty2 &= -1879048193;
                            } else {
                                maxLines3 = maxLines;
                            }
                            int minLines4 = i12 != 0 ? 1 : minLines;
                            visualTransformation3 = i13 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                            if (i2 != 0) {
                                ComposerKt.sourceInformationMarkerStart($composer3, -1229772655, "CC(remember):BasicTextField.kt#9igjgp");
                                Object it$iv = $composer3.rememberedValue();
                                if (it$iv == Composer.INSTANCE.getEmpty()) {
                                    Object value$iv = new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda1
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            return BasicTextFieldKt.BasicTextField$lambda$26$0((TextLayoutResult) obj);
                                        }
                                    };
                                    $composer3.updateRememberedValue(value$iv);
                                    it$iv = value$iv;
                                }
                                function6 = (Function1) it$iv;
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                            } else {
                                function6 = function2;
                            }
                            MutableInteractionSource interactionSource4 = i3 != 0 ? null : interactionSource;
                            SolidColor cursorBrush4 = i4 != 0 ? new SolidColor(Color.INSTANCE.m5123getBlack0d7_KjU(), null) : cursorBrush;
                            if (i17 != 0) {
                                TextStyle textStyle5 = textStyle2;
                                lambda$486633673$foundation = ComposableSingletons$BasicTextFieldKt.INSTANCE.getLambda$486633673$foundation();
                                textStyle4 = textStyle5;
                                singleLine3 = singleLine5;
                                maxLines4 = maxLines3;
                                minLines3 = minLines4;
                                keyboardOptions4 = keyboardOptions2;
                                interactionSource3 = interactionSource4;
                                cursorBrush3 = cursorBrush4;
                                $dirty = $dirty2;
                                enabled4 = enabled2;
                                keyboardActions4 = keyboardActions5;
                                function7 = function6;
                            } else {
                                singleLine3 = singleLine5;
                                maxLines4 = maxLines3;
                                minLines3 = minLines4;
                                keyboardOptions4 = keyboardOptions2;
                                textStyle4 = textStyle2;
                                interactionSource3 = interactionSource4;
                                lambda$486633673$foundation = function3;
                                cursorBrush3 = cursorBrush4;
                                $dirty = $dirty2;
                                enabled4 = enabled2;
                                keyboardActions4 = keyboardActions5;
                                function7 = function6;
                            }
                        } else {
                            $composer3.skipToGroupEnd();
                            if ((i & 512) != 0) {
                                $dirty2 &= -1879048193;
                            }
                            maxLines4 = maxLines;
                            minLines3 = minLines;
                            visualTransformation3 = visualTransformation;
                            interactionSource3 = interactionSource;
                            $dirty = $dirty2;
                            modifier4 = modifier2;
                            keyboardOptions4 = keyboardOptions2;
                            textStyle4 = textStyle2;
                            function7 = function2;
                            cursorBrush3 = cursorBrush;
                            lambda$486633673$foundation = function3;
                            enabled4 = enabled2;
                            keyboardActions4 = keyboardActions2;
                            singleLine3 = singleLine;
                        }
                        $composer3.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-971111025, $dirty, $dirty1, "androidx.compose.foundation.text.BasicTextField (BasicTextField.kt:906)");
                        }
                        VisualTransformation visualTransformation4 = visualTransformation3;
                        ImeOptions imeOptions$foundation = keyboardOptions4.toImeOptions$foundation(singleLine3);
                        int $dirty3 = $dirty1;
                        boolean z = !singleLine3;
                        Brush cursorBrush5 = cursorBrush3;
                        int i18 = singleLine3 ? 1 : minLines3;
                        MutableInteractionSource interactionSource5 = interactionSource3;
                        int i19 = singleLine3 ? 1 : maxLines4;
                        KeyboardOptions keyboardOptions5 = keyboardOptions4;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1229762231, "CC(remember):BasicTextField.kt#9igjgp");
                        boolean invalid$iv = (($dirty & 14) == 4) | (($dirty & 112) == 32);
                        Object value$iv2 = $composer3.rememberedValue();
                        if (invalid$iv) {
                            singleLine4 = singleLine3;
                        } else {
                            singleLine4 = singleLine3;
                            if (value$iv2 == Composer.INSTANCE.getEmpty()) {
                            }
                            Function1 function8 = (Function1) value$iv2;
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            int i20 = ($dirty & 14) | ($dirty & 896) | (($dirty >> 6) & 7168) | (($dirty3 << 9) & 57344) | (($dirty3 << 9) & 458752) | (($dirty3 << 9) & 3670016) | (($dirty3 << 9) & 29360128);
                            int i21 = (($dirty >> 15) & 896) | ($dirty & 7168) | (57344 & $dirty) | ($dirty3 & 458752);
                            Function1<? super TextLayoutResult, Unit> function9 = function7;
                            boolean singleLine6 = singleLine4;
                            TextStyle textStyle6 = textStyle4;
                            Modifier modifier5 = modifier4;
                            CoreTextFieldKt.CoreTextField(value, function8, modifier5, textStyle6, visualTransformation4, function9, interactionSource5, cursorBrush5, z, i19, i18, imeOptions$foundation, keyboardActions4, enabled4, readOnly2, lambda$486633673$foundation, null, $composer3, i20, i21, 65536);
                            $composer2 = $composer3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            textStyle3 = textStyle6;
                            keyboardActions3 = keyboardActions4;
                            function4 = lambda$486633673$foundation;
                            maxLines2 = maxLines4;
                            minLines2 = minLines3;
                            singleLine2 = singleLine6;
                            modifier3 = modifier5;
                            visualTransformation2 = visualTransformation4;
                            cursorBrush2 = cursorBrush5;
                            enabled3 = enabled4;
                            keyboardOptions3 = keyboardOptions5;
                            function5 = function9;
                            readOnly3 = readOnly2;
                            interactionSource2 = interactionSource5;
                        }
                        value$iv2 = new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return BasicTextFieldKt.BasicTextField$lambda$27$0(value, function1, (TextFieldValue) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                        Function1 function10 = (Function1) value$iv2;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        int i22 = ($dirty & 14) | ($dirty & 896) | (($dirty >> 6) & 7168) | (($dirty3 << 9) & 57344) | (($dirty3 << 9) & 458752) | (($dirty3 << 9) & 3670016) | (($dirty3 << 9) & 29360128);
                        int i23 = (($dirty >> 15) & 896) | ($dirty & 7168) | (57344 & $dirty) | ($dirty3 & 458752);
                        Function1<? super TextLayoutResult, Unit> function11 = function7;
                        boolean singleLine7 = singleLine4;
                        TextStyle textStyle7 = textStyle4;
                        Modifier modifier6 = modifier4;
                        CoreTextFieldKt.CoreTextField(value, function10, modifier6, textStyle7, visualTransformation4, function11, interactionSource5, cursorBrush5, z, i19, i18, imeOptions$foundation, keyboardActions4, enabled4, readOnly2, lambda$486633673$foundation, null, $composer3, i22, i23, 65536);
                        $composer2 = $composer3;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        textStyle3 = textStyle7;
                        keyboardActions3 = keyboardActions4;
                        function4 = lambda$486633673$foundation;
                        maxLines2 = maxLines4;
                        minLines2 = minLines3;
                        singleLine2 = singleLine7;
                        modifier3 = modifier6;
                        visualTransformation2 = visualTransformation4;
                        cursorBrush2 = cursorBrush5;
                        enabled3 = enabled4;
                        keyboardOptions3 = keyboardOptions5;
                        function5 = function11;
                        readOnly3 = readOnly2;
                        interactionSource2 = interactionSource5;
                    } else {
                        $composer2 = $composer3;
                        $composer2.skipToGroupEnd();
                        singleLine2 = singleLine;
                        minLines2 = minLines;
                        function4 = function3;
                        keyboardActions3 = keyboardActions2;
                        modifier3 = modifier2;
                        enabled3 = enabled2;
                        keyboardOptions3 = keyboardOptions2;
                        readOnly3 = readOnly2;
                        textStyle3 = textStyle2;
                        maxLines2 = maxLines;
                        visualTransformation2 = visualTransformation;
                        function5 = function2;
                        interactionSource2 = interactionSource;
                        cursorBrush2 = cursorBrush;
                    }
                    ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return BasicTextFieldKt.BasicTextField$lambda$28(value, function1, modifier3, enabled3, readOnly3, textStyle3, keyboardOptions3, keyboardActions3, singleLine2, maxLines2, minLines2, visualTransformation2, function5, interactionSource2, cursorBrush2, function4, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit BasicTextField$lambda$27$0(TextFieldValue $value, Function1 $onValueChange, TextFieldValue it) {
                    if (!Intrinsics.areEqual($value, it)) {
                        $onValueChange.invoke(it);
                    }
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit BasicTextField$lambda$29$0(TextLayoutResult it) {
                    return Unit.INSTANCE;
                }

                @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
                public static final /* synthetic */ void BasicTextField(final String value, final Function1 onValueChange, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, VisualTransformation visualTransformation, Function1 onTextLayout, MutableInteractionSource interactionSource, Brush cursorBrush, Function3 decorationBox, Composer $composer, final int $changed, final int $changed1, final int i) {
                    Modifier modifier2;
                    boolean z;
                    boolean z2;
                    TextStyle textStyle2;
                    KeyboardOptions keyboardOptions2;
                    KeyboardActions keyboardActions2;
                    int i2;
                    int i3;
                    int i4;
                    Composer $composer2;
                    final KeyboardActions keyboardActions3;
                    final Modifier modifier3;
                    final int maxLines2;
                    final MutableInteractionSource interactionSource2;
                    final Function3 decorationBox2;
                    final boolean enabled2;
                    final boolean readOnly2;
                    final TextStyle textStyle3;
                    final KeyboardOptions keyboardOptions3;
                    final boolean singleLine2;
                    final VisualTransformation visualTransformation2;
                    final Function1 onTextLayout2;
                    final Brush cursorBrush2;
                    Function1 onTextLayout3;
                    int i5;
                    MutableInteractionSource interactionSource3;
                    Composer $composer3 = $composer.startRestartGroup(74291967);
                    ComposerKt.sourceInformation($composer3, "C(BasicTextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,keyboardOptions,keyboardActions,singleLine,maxLines,visualTransformation,onTextLayout,interactionSource,cursorBrush,decorationBox)945@49253L2,946@49307L39,951@49548L580:BasicTextField.kt#423gt5");
                    int $dirty = $changed;
                    int $dirty1 = $changed1;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer3.changed(value) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty |= $composer3.changedInstance(onValueChange) ? 32 : 16;
                    }
                    int i6 = i & 4;
                    if (i6 != 0) {
                        $dirty |= 384;
                        modifier2 = modifier;
                    } else if (($changed & 384) == 0) {
                        modifier2 = modifier;
                        $dirty |= $composer3.changed(modifier2) ? 256 : 128;
                    } else {
                        modifier2 = modifier;
                    }
                    int i7 = i & 8;
                    if (i7 != 0) {
                        $dirty |= 3072;
                        z = enabled;
                    } else if (($changed & 3072) == 0) {
                        z = enabled;
                        $dirty |= $composer3.changed(z) ? 2048 : 1024;
                    } else {
                        z = enabled;
                    }
                    int i8 = i & 16;
                    if (i8 != 0) {
                        $dirty |= 24576;
                        z2 = readOnly;
                    } else if (($changed & 24576) == 0) {
                        z2 = readOnly;
                        $dirty |= $composer3.changed(z2) ? 16384 : 8192;
                    } else {
                        z2 = readOnly;
                    }
                    int i9 = i & 32;
                    if (i9 != 0) {
                        $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        textStyle2 = textStyle;
                    } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        textStyle2 = textStyle;
                        $dirty |= $composer3.changed(textStyle2) ? 131072 : 65536;
                    } else {
                        textStyle2 = textStyle;
                    }
                    int i10 = i & 64;
                    if (i10 != 0) {
                        $dirty |= 1572864;
                        keyboardOptions2 = keyboardOptions;
                    } else if (($changed & 1572864) == 0) {
                        keyboardOptions2 = keyboardOptions;
                        $dirty |= $composer3.changed(keyboardOptions2) ? 1048576 : 524288;
                    } else {
                        keyboardOptions2 = keyboardOptions;
                    }
                    int i11 = i & 128;
                    if (i11 != 0) {
                        $dirty |= 12582912;
                        keyboardActions2 = keyboardActions;
                    } else if (($changed & 12582912) == 0) {
                        keyboardActions2 = keyboardActions;
                        $dirty |= $composer3.changed(keyboardActions2) ? 8388608 : 4194304;
                    } else {
                        keyboardActions2 = keyboardActions;
                    }
                    int i12 = i & 256;
                    if (i12 != 0) {
                        $dirty |= 100663296;
                    } else if (($changed & 100663296) == 0) {
                        $dirty |= $composer3.changed(singleLine) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                    }
                    int i13 = i & 512;
                    if (i13 != 0) {
                        $dirty |= C.ENCODING_PCM_32BIT;
                    } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
                        $dirty |= $composer3.changed(maxLines) ? 536870912 : 268435456;
                    }
                    int i14 = i & 1024;
                    if (i14 != 0) {
                        $dirty1 |= 6;
                    } else if (($changed1 & 6) == 0) {
                        $dirty1 |= $composer3.changed(visualTransformation) ? 4 : 2;
                    }
                    int i15 = i & 2048;
                    if (i15 != 0) {
                        $dirty1 |= 48;
                    } else if (($changed1 & 48) == 0) {
                        $dirty1 |= $composer3.changedInstance(onTextLayout) ? 32 : 16;
                    }
                    int i16 = i & 4096;
                    if (i16 != 0) {
                        $dirty1 |= 384;
                        i2 = i16;
                    } else {
                        i2 = i16;
                        if (($changed1 & 384) == 0) {
                            $dirty1 |= $composer3.changed(interactionSource) ? 256 : 128;
                        }
                    }
                    int i17 = i & 8192;
                    if (i17 != 0) {
                        $dirty1 |= 3072;
                        i3 = i17;
                    } else {
                        i3 = i17;
                        if (($changed1 & 3072) == 0) {
                            $dirty1 |= $composer3.changed(cursorBrush) ? 2048 : 1024;
                        }
                    }
                    int i18 = i & 16384;
                    if (i18 != 0) {
                        $dirty1 |= 24576;
                        i4 = i18;
                    } else {
                        i4 = i18;
                        if (($changed1 & 24576) == 0) {
                            $dirty1 |= $composer3.changedInstance(decorationBox) ? 16384 : 8192;
                        }
                    }
                    if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty1 & 9363) == 9362) ? false : true, $dirty & 1)) {
                        Modifier.Companion modifier4 = i6 != 0 ? Modifier.INSTANCE : modifier2;
                        boolean enabled3 = i7 != 0 ? true : z;
                        boolean readOnly3 = i8 != 0 ? false : z2;
                        TextStyle textStyle4 = i9 != 0 ? TextStyle.INSTANCE.getDefault() : textStyle2;
                        KeyboardOptions keyboardOptions4 = i10 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions2;
                        KeyboardActions keyboardActions4 = i11 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions2;
                        boolean singleLine3 = i12 != 0 ? false : singleLine;
                        int maxLines3 = i13 != 0 ? Integer.MAX_VALUE : maxLines;
                        VisualTransformation visualTransformation3 = i14 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                        if (i15 != 0) {
                            ComposerKt.sourceInformationMarkerStart($composer3, -1619846591, "CC(remember):BasicTextField.kt#9igjgp");
                            Object it$iv = $composer3.rememberedValue();
                            if (it$iv == Composer.INSTANCE.getEmpty()) {
                                Object value$iv = new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda4
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return BasicTextFieldKt.BasicTextField$lambda$29$0((TextLayoutResult) obj);
                                    }
                                };
                                $composer3.updateRememberedValue(value$iv);
                                it$iv = value$iv;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            onTextLayout3 = (Function1) it$iv;
                        } else {
                            onTextLayout3 = onTextLayout;
                        }
                        if (i2 != 0) {
                            ComposerKt.sourceInformationMarkerStart($composer3, -1619844826, "CC(remember):BasicTextField.kt#9igjgp");
                            Object it$iv2 = $composer3.rememberedValue();
                            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                                Object value$iv2 = InteractionSourceKt.MutableInteractionSource();
                                $composer3.updateRememberedValue(value$iv2);
                                it$iv2 = value$iv2;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            int i19 = i4;
                            interactionSource3 = (MutableInteractionSource) it$iv2;
                            i5 = i19;
                        } else {
                            i5 = i4;
                            interactionSource3 = interactionSource;
                        }
                        Brush cursorBrush3 = i3 != 0 ? new SolidColor(Color.INSTANCE.m5123getBlack0d7_KjU(), null) : cursorBrush;
                        Function3 decorationBox3 = i5 != 0 ? ComposableSingletons$BasicTextFieldKt.INSTANCE.getLambda$444370233$foundation() : decorationBox;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(74291967, $dirty, $dirty1, "androidx.compose.foundation.text.BasicTextField (BasicTextField.kt:950)");
                        }
                        int i20 = (($dirty1 << 3) & 112) | 6 | (($dirty1 << 3) & 896) | (($dirty1 << 3) & 7168) | (($dirty1 << 3) & 57344) | (($dirty1 << 3) & 458752);
                        $composer2 = $composer3;
                        Modifier modifier5 = modifier4;
                        BasicTextField(value, (Function1<? super String, Unit>) onValueChange, modifier5, enabled3, readOnly3, textStyle4, keyboardOptions4, keyboardActions4, singleLine3, maxLines3, 1, visualTransformation3, (Function1<? super TextLayoutResult, Unit>) onTextLayout3, interactionSource3, cursorBrush3, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) decorationBox3, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty) | (1879048192 & $dirty), i20, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier5;
                        enabled2 = enabled3;
                        readOnly2 = readOnly3;
                        textStyle3 = textStyle4;
                        keyboardOptions3 = keyboardOptions4;
                        keyboardActions3 = keyboardActions4;
                        singleLine2 = singleLine3;
                        maxLines2 = maxLines3;
                        visualTransformation2 = visualTransformation3;
                        onTextLayout2 = onTextLayout3;
                        interactionSource2 = interactionSource3;
                        cursorBrush2 = cursorBrush3;
                        decorationBox2 = decorationBox3;
                    } else {
                        $composer2 = $composer3;
                        $composer2.skipToGroupEnd();
                        Modifier modifier6 = modifier2;
                        keyboardActions3 = keyboardActions2;
                        modifier3 = modifier6;
                        maxLines2 = maxLines;
                        interactionSource2 = interactionSource;
                        decorationBox2 = decorationBox;
                        enabled2 = z;
                        readOnly2 = z2;
                        textStyle3 = textStyle2;
                        keyboardOptions3 = keyboardOptions2;
                        singleLine2 = singleLine;
                        visualTransformation2 = visualTransformation;
                        onTextLayout2 = onTextLayout;
                        cursorBrush2 = cursorBrush;
                    }
                    ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return BasicTextFieldKt.BasicTextField$lambda$31(value, onValueChange, modifier3, enabled2, readOnly2, textStyle3, keyboardOptions3, keyboardActions3, singleLine2, maxLines2, visualTransformation2, onTextLayout2, interactionSource2, cursorBrush2, decorationBox2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit BasicTextField$lambda$32$0(TextLayoutResult it) {
                    return Unit.INSTANCE;
                }

                @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
                public static final /* synthetic */ void BasicTextField(final TextFieldValue value, final Function1 onValueChange, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, VisualTransformation visualTransformation, Function1 onTextLayout, MutableInteractionSource interactionSource, Brush cursorBrush, Function3 decorationBox, Composer $composer, final int $changed, final int $changed1, final int i) {
                    Modifier modifier2;
                    boolean z;
                    boolean z2;
                    TextStyle textStyle2;
                    KeyboardOptions keyboardOptions2;
                    KeyboardActions keyboardActions2;
                    int i2;
                    int i3;
                    int i4;
                    Composer $composer2;
                    final KeyboardActions keyboardActions3;
                    final Modifier modifier3;
                    final int maxLines2;
                    final MutableInteractionSource interactionSource2;
                    final Function3 decorationBox2;
                    final boolean enabled2;
                    final boolean readOnly2;
                    final TextStyle textStyle3;
                    final KeyboardOptions keyboardOptions3;
                    final boolean singleLine2;
                    final VisualTransformation visualTransformation2;
                    final Function1 onTextLayout2;
                    final Brush cursorBrush2;
                    Function1 onTextLayout3;
                    int i5;
                    MutableInteractionSource interactionSource3;
                    Composer $composer3 = $composer.startRestartGroup(1742344466);
                    ComposerKt.sourceInformation($composer3, "C(BasicTextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,keyboardOptions,keyboardActions,singleLine,maxLines,visualTransformation,onTextLayout,interactionSource,cursorBrush,decorationBox)985@50780L2,986@50834L39,991@51075L580:BasicTextField.kt#423gt5");
                    int $dirty = $changed;
                    int $dirty1 = $changed1;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer3.changed(value) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty |= $composer3.changedInstance(onValueChange) ? 32 : 16;
                    }
                    int i6 = i & 4;
                    if (i6 != 0) {
                        $dirty |= 384;
                        modifier2 = modifier;
                    } else if (($changed & 384) == 0) {
                        modifier2 = modifier;
                        $dirty |= $composer3.changed(modifier2) ? 256 : 128;
                    } else {
                        modifier2 = modifier;
                    }
                    int i7 = i & 8;
                    if (i7 != 0) {
                        $dirty |= 3072;
                        z = enabled;
                    } else if (($changed & 3072) == 0) {
                        z = enabled;
                        $dirty |= $composer3.changed(z) ? 2048 : 1024;
                    } else {
                        z = enabled;
                    }
                    int i8 = i & 16;
                    if (i8 != 0) {
                        $dirty |= 24576;
                        z2 = readOnly;
                    } else if (($changed & 24576) == 0) {
                        z2 = readOnly;
                        $dirty |= $composer3.changed(z2) ? 16384 : 8192;
                    } else {
                        z2 = readOnly;
                    }
                    int i9 = i & 32;
                    if (i9 != 0) {
                        $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        textStyle2 = textStyle;
                    } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        textStyle2 = textStyle;
                        $dirty |= $composer3.changed(textStyle2) ? 131072 : 65536;
                    } else {
                        textStyle2 = textStyle;
                    }
                    int i10 = i & 64;
                    if (i10 != 0) {
                        $dirty |= 1572864;
                        keyboardOptions2 = keyboardOptions;
                    } else if (($changed & 1572864) == 0) {
                        keyboardOptions2 = keyboardOptions;
                        $dirty |= $composer3.changed(keyboardOptions2) ? 1048576 : 524288;
                    } else {
                        keyboardOptions2 = keyboardOptions;
                    }
                    int i11 = i & 128;
                    if (i11 != 0) {
                        $dirty |= 12582912;
                        keyboardActions2 = keyboardActions;
                    } else if (($changed & 12582912) == 0) {
                        keyboardActions2 = keyboardActions;
                        $dirty |= $composer3.changed(keyboardActions2) ? 8388608 : 4194304;
                    } else {
                        keyboardActions2 = keyboardActions;
                    }
                    int i12 = i & 256;
                    if (i12 != 0) {
                        $dirty |= 100663296;
                    } else if (($changed & 100663296) == 0) {
                        $dirty |= $composer3.changed(singleLine) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                    }
                    int i13 = i & 512;
                    if (i13 != 0) {
                        $dirty |= C.ENCODING_PCM_32BIT;
                    } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
                        $dirty |= $composer3.changed(maxLines) ? 536870912 : 268435456;
                    }
                    int i14 = i & 1024;
                    if (i14 != 0) {
                        $dirty1 |= 6;
                    } else if (($changed1 & 6) == 0) {
                        $dirty1 |= $composer3.changed(visualTransformation) ? 4 : 2;
                    }
                    int i15 = i & 2048;
                    if (i15 != 0) {
                        $dirty1 |= 48;
                    } else if (($changed1 & 48) == 0) {
                        $dirty1 |= $composer3.changedInstance(onTextLayout) ? 32 : 16;
                    }
                    int i16 = i & 4096;
                    if (i16 != 0) {
                        $dirty1 |= 384;
                        i2 = i16;
                    } else {
                        i2 = i16;
                        if (($changed1 & 384) == 0) {
                            $dirty1 |= $composer3.changed(interactionSource) ? 256 : 128;
                        }
                    }
                    int i17 = i & 8192;
                    if (i17 != 0) {
                        $dirty1 |= 3072;
                        i3 = i17;
                    } else {
                        i3 = i17;
                        if (($changed1 & 3072) == 0) {
                            $dirty1 |= $composer3.changed(cursorBrush) ? 2048 : 1024;
                        }
                    }
                    int i18 = i & 16384;
                    if (i18 != 0) {
                        $dirty1 |= 24576;
                        i4 = i18;
                    } else {
                        i4 = i18;
                        if (($changed1 & 24576) == 0) {
                            $dirty1 |= $composer3.changedInstance(decorationBox) ? 16384 : 8192;
                        }
                    }
                    if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty1 & 9363) == 9362) ? false : true, $dirty & 1)) {
                        Modifier.Companion modifier4 = i6 != 0 ? Modifier.INSTANCE : modifier2;
                        boolean enabled3 = i7 != 0 ? true : z;
                        boolean readOnly3 = i8 != 0 ? false : z2;
                        TextStyle textStyle4 = i9 != 0 ? TextStyle.INSTANCE.getDefault() : textStyle2;
                        KeyboardOptions keyboardOptions4 = i10 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions2;
                        KeyboardActions keyboardActions4 = i11 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions2;
                        boolean singleLine3 = i12 != 0 ? false : singleLine;
                        int maxLines3 = i13 != 0 ? Integer.MAX_VALUE : maxLines;
                        VisualTransformation visualTransformation3 = i14 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation;
                        if (i15 != 0) {
                            ComposerKt.sourceInformationMarkerStart($composer3, -644195948, "CC(remember):BasicTextField.kt#9igjgp");
                            Object it$iv = $composer3.rememberedValue();
                            if (it$iv == Composer.INSTANCE.getEmpty()) {
                                Object value$iv = new Function1() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda9
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return BasicTextFieldKt.BasicTextField$lambda$32$0((TextLayoutResult) obj);
                                    }
                                };
                                $composer3.updateRememberedValue(value$iv);
                                it$iv = value$iv;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            onTextLayout3 = (Function1) it$iv;
                        } else {
                            onTextLayout3 = onTextLayout;
                        }
                        if (i2 != 0) {
                            ComposerKt.sourceInformationMarkerStart($composer3, -644194183, "CC(remember):BasicTextField.kt#9igjgp");
                            Object it$iv2 = $composer3.rememberedValue();
                            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                                Object value$iv2 = InteractionSourceKt.MutableInteractionSource();
                                $composer3.updateRememberedValue(value$iv2);
                                it$iv2 = value$iv2;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            int i19 = i4;
                            interactionSource3 = (MutableInteractionSource) it$iv2;
                            i5 = i19;
                        } else {
                            i5 = i4;
                            interactionSource3 = interactionSource;
                        }
                        Brush cursorBrush3 = i3 != 0 ? new SolidColor(Color.INSTANCE.m5123getBlack0d7_KjU(), null) : cursorBrush;
                        Function3 decorationBox3 = i5 != 0 ? ComposableSingletons$BasicTextFieldKt.INSTANCE.m1167getLambda$665310900$foundation() : decorationBox;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1742344466, $dirty, $dirty1, "androidx.compose.foundation.text.BasicTextField (BasicTextField.kt:990)");
                        }
                        int i20 = (($dirty1 << 3) & 112) | 6 | (($dirty1 << 3) & 896) | (($dirty1 << 3) & 7168) | (($dirty1 << 3) & 57344) | (($dirty1 << 3) & 458752);
                        $composer2 = $composer3;
                        Modifier modifier5 = modifier4;
                        BasicTextField(value, (Function1<? super TextFieldValue, Unit>) onValueChange, modifier5, enabled3, readOnly3, textStyle4, keyboardOptions4, keyboardActions4, singleLine3, maxLines3, 1, visualTransformation3, (Function1<? super TextLayoutResult, Unit>) onTextLayout3, interactionSource3, cursorBrush3, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) decorationBox3, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty) | (1879048192 & $dirty), i20, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier5;
                        enabled2 = enabled3;
                        readOnly2 = readOnly3;
                        textStyle3 = textStyle4;
                        keyboardOptions3 = keyboardOptions4;
                        keyboardActions3 = keyboardActions4;
                        singleLine2 = singleLine3;
                        maxLines2 = maxLines3;
                        visualTransformation2 = visualTransformation3;
                        onTextLayout2 = onTextLayout3;
                        interactionSource2 = interactionSource3;
                        cursorBrush2 = cursorBrush3;
                        decorationBox2 = decorationBox3;
                    } else {
                        $composer2 = $composer3;
                        $composer2.skipToGroupEnd();
                        Modifier modifier6 = modifier2;
                        keyboardActions3 = keyboardActions2;
                        modifier3 = modifier6;
                        maxLines2 = maxLines;
                        interactionSource2 = interactionSource;
                        decorationBox2 = decorationBox;
                        enabled2 = z;
                        readOnly2 = z2;
                        textStyle3 = textStyle2;
                        keyboardOptions3 = keyboardOptions2;
                        singleLine2 = singleLine;
                        visualTransformation2 = visualTransformation;
                        onTextLayout2 = onTextLayout;
                        cursorBrush2 = cursorBrush;
                    }
                    ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$$ExternalSyntheticLambda10
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return BasicTextFieldKt.BasicTextField$lambda$34(value, onValueChange, modifier3, enabled2, readOnly2, textStyle3, keyboardOptions3, keyboardActions3, singleLine2, maxLines2, visualTransformation2, onTextLayout2, interactionSource2, cursorBrush2, decorationBox2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
            }
