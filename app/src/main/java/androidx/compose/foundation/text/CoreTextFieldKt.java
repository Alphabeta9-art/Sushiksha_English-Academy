package androidx.compose.foundation.text;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.relocation.BringIntoViewRequester;
import androidx.compose.foundation.relocation.BringIntoViewRequesterKt;
import androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter;
import androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter_androidKt;
import androidx.compose.foundation.text.selection.OffsetProvider;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.SelectionHandleAnchor;
import androidx.compose.foundation.text.selection.SelectionHandleInfo;
import androidx.compose.foundation.text.selection.SelectionHandlesKt;
import androidx.compose.foundation.text.selection.SimpleLayoutKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: CoreTextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aú\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00132\b\b\u0002\u0010\u001c\u001a\u00020\u001323\b\u0002\u0010\u001d\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u001f2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$H\u0001¢\u0006\u0002\u0010%\u001a0\u0010&\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010'\u001a\u00020(2\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u001fH\u0003¢\u0006\u0002\u0010*\u001a\u001c\u0010+\u001a\u00020\u0007*\u00020\u00072\u0006\u0010,\u001a\u00020-2\u0006\u0010'\u001a\u00020(H\u0002\u001a \u0010.\u001a\u00020\u00012\u0006\u0010,\u001a\u00020-2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u0013H\u0000\u001a0\u00102\u001a\u00020\u00012\u0006\u00103\u001a\u0002042\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00105\u001a\u000206H\u0002\u001a\u0010\u00107\u001a\u00020\u00012\u0006\u0010,\u001a\u00020-H\u0002\u001a2\u00108\u001a\u00020\u0001*\u0002092\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\r2\u0006\u00105\u001a\u000206H\u0080@¢\u0006\u0002\u0010=\u001a\u001d\u0010>\u001a\u00020\u00012\u0006\u0010'\u001a\u00020(2\u0006\u0010?\u001a\u00020\u0013H\u0003¢\u0006\u0002\u0010@\u001a\u0015\u0010A\u001a\u00020\u00012\u0006\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\u0010B\u001a \u0010C\u001a\u00020\u00012\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00105\u001a\u000206H\u0002\u001a\u001c\u0010D\u001a\u00020\u0007*\u00020\u00072\u0006\u0010E\u001a\u00020(2\u0006\u0010F\u001a\u00020GH\u0002¨\u0006H²\u0006\n\u0010I\u001a\u00020\u0013X\u008a\u0084\u0002"}, d2 = {"CoreTextField", "", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "onTextLayout", "Landroidx/compose/ui/text/TextLayoutResult;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "softWrap", "", "maxLines", "", "minLines", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "enabled", "readOnly", "decorationBox", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "innerTextField", "textScrollerPosition", "Landroidx/compose/foundation/text/TextFieldScrollerPosition;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;ZIILandroidx/compose/ui/text/input/ImeOptions;Landroidx/compose/foundation/text/KeyboardActions;ZZLkotlin/jvm/functions/Function3;Landroidx/compose/foundation/text/TextFieldScrollerPosition;Landroidx/compose/runtime/Composer;III)V", "CoreTextFieldRootBox", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", UriUtil.LOCAL_CONTENT_SCHEME, "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "previewKeyEventToDeselectOnBack", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "tapToFocus", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "allowKeyboard", "startInputSession", "textInputService", "Landroidx/compose/ui/text/input/TextInputService;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "endInputSession", "bringSelectionEndIntoView", "Landroidx/compose/foundation/relocation/BringIntoViewRequester;", "textDelegate", "Landroidx/compose/foundation/text/TextDelegate;", "textLayoutResult", "(Landroidx/compose/foundation/relocation/BringIntoViewRequester;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/foundation/text/TextDelegate;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/text/input/OffsetMapping;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "SelectionToolbarAndHandles", "show", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;ZLandroidx/compose/runtime/Composer;I)V", "TextFieldCursorHandle", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/runtime/Composer;I)V", "notifyFocusedRect", "addContextMenuComponents", "textFieldSelectionManager", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "foundation", "writeable"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class CoreTextFieldKt {
    static final Unit CoreTextField$lambda$27(TextFieldValue textFieldValue, Function1 function1, Modifier modifier, TextStyle textStyle, VisualTransformation visualTransformation, Function1 function2, MutableInteractionSource mutableInteractionSource, Brush brush, boolean z, int i, int i2, ImeOptions imeOptions, KeyboardActions keyboardActions, boolean z2, boolean z3, Function3 function3, TextFieldScrollerPosition textFieldScrollerPosition, int i3, int i4, int i5, Composer composer, int i6) {
        CoreTextField(textFieldValue, function1, modifier, textStyle, visualTransformation, function2, mutableInteractionSource, brush, z, i, i2, imeOptions, keyboardActions, z2, z3, function3, textFieldScrollerPosition, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
        return Unit.INSTANCE;
    }

    static final Unit CoreTextFieldRootBox$lambda$1(Modifier modifier, TextFieldSelectionManager textFieldSelectionManager, Function2 function2, int i, Composer composer, int i2) {
        CoreTextFieldRootBox(modifier, textFieldSelectionManager, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SelectionToolbarAndHandles$lambda$1(TextFieldSelectionManager textFieldSelectionManager, boolean z, int i, Composer composer, int i2) {
        SelectionToolbarAndHandles(textFieldSelectionManager, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TextFieldCursorHandle$lambda$4(TextFieldSelectionManager textFieldSelectionManager, int i, Composer composer, int i2) {
        TextFieldCursorHandle(textFieldSelectionManager, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CoreTextField$lambda$0$0(TextLayoutResult it) {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:321:0x06f2  */
    /* JADX WARN: Code duplicated, block: B:322:0x0709  */
    /* JADX WARN: Code duplicated, block: B:325:0x075e  */
    /* JADX WARN: Code duplicated, block: B:326:0x0773  */
    /* JADX WARN: Code duplicated, block: B:329:0x079b  */
    /* JADX WARN: Code duplicated, block: B:333:0x07ca  */
    /* JADX WARN: Code duplicated, block: B:334:0x07d8  */
    /* JADX WARN: Code duplicated, block: B:337:0x0860  */
    /* JADX WARN: Code duplicated, block: B:338:0x087a  */
    /* JADX WARN: Code duplicated, block: B:341:0x08ca  */
    /* JADX WARN: Code duplicated, block: B:342:0x08cc  */
    /* JADX WARN: Code duplicated, block: B:345:0x08d6  */
    /* JADX WARN: Code duplicated, block: B:346:0x08d8  */
    /* JADX WARN: Code duplicated, block: B:349:0x08e4  */
    /* JADX WARN: Code duplicated, block: B:350:0x08e6  */
    /* JADX WARN: Code duplicated, block: B:353:0x08f0  */
    /* JADX WARN: Code duplicated, block: B:355:0x08f8  */
    /* JADX WARN: Code duplicated, block: B:356:0x08fb  */
    /* JADX WARN: Code duplicated, block: B:359:0x0905 A[PHI: r12 r63
  0x0905: PHI (r12v52 androidx.compose.ui.text.input.ImeOptions) = (r12v40 androidx.compose.ui.text.input.ImeOptions), (r12v53 androidx.compose.ui.text.input.ImeOptions) binds: [B:358:0x0903, B:355:0x08f8] A[DONT_GENERATE, DONT_INLINE]
  0x0905: PHI (r63v45 boolean) = (r63v21 boolean), (r63v46 boolean) binds: [B:358:0x0903, B:355:0x08f8] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:360:0x0907  */
    /* JADX WARN: Code duplicated, block: B:363:0x0930  */
    /* JADX WARN: Code duplicated, block: B:366:0x094a A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:370:0x0995 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:372:0x0999  */
    /* JADX WARN: Code duplicated, block: B:375:0x09d1  */
    /* JADX WARN: Code duplicated, block: B:377:0x09d7  */
    /* JADX WARN: Code duplicated, block: B:383:0x09ed  */
    /* JADX WARN: Code duplicated, block: B:386:0x0a00 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:390:0x0a52  */
    /* JADX WARN: Code duplicated, block: B:393:0x0a5d  */
    /* JADX WARN: Code duplicated, block: B:397:0x0a88  */
    /* JADX WARN: Code duplicated, block: B:398:0x0a8a  */
    /* JADX WARN: Code duplicated, block: B:401:0x0a92  */
    /* JADX WARN: Code duplicated, block: B:402:0x0a94  */
    /* JADX WARN: Code duplicated, block: B:405:0x0aac  */
    /* JADX WARN: Code duplicated, block: B:407:0x0ab6  */
    /* JADX WARN: Code duplicated, block: B:408:0x0abc  */
    /* JADX WARN: Code duplicated, block: B:412:0x0b14  */
    /* JADX WARN: Code duplicated, block: B:414:0x0b1e  */
    /* JADX WARN: Code duplicated, block: B:415:0x0b21  */
    /* JADX WARN: Code duplicated, block: B:419:0x0b5f  */
    /* JADX WARN: Code duplicated, block: B:420:0x0b61  */
    /* JADX WARN: Code duplicated, block: B:423:0x0b74  */
    /* JADX WARN: Code duplicated, block: B:426:0x0b7f A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:430:0x0baf  */
    /* JADX WARN: Code duplicated, block: B:431:0x0bb1  */
    /* JADX WARN: Code duplicated, block: B:434:0x0bc4  */
    /* JADX WARN: Code duplicated, block: B:435:0x0bc6  */
    /* JADX WARN: Code duplicated, block: B:438:0x0bd9  */
    /* JADX WARN: Code duplicated, block: B:441:0x0be6  */
    /* JADX WARN: Code duplicated, block: B:444:0x0c40 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:450:0x0c50  */
    /* JADX WARN: Code duplicated, block: B:453:0x0c87  */
    /* JADX WARN: Code duplicated, block: B:455:0x0c91  */
    /* JADX WARN: Code duplicated, block: B:456:0x0c94  */
    /* JADX WARN: Code duplicated, block: B:460:0x0cc5  */
    /* JADX WARN: Code duplicated, block: B:461:0x0cc7  */
    /* JADX WARN: Code duplicated, block: B:464:0x0cd4  */
    /* JADX WARN: Code duplicated, block: B:466:0x0cda  */
    /* JADX WARN: Code duplicated, block: B:472:0x0cf2  */
    /* JADX WARN: Code duplicated, block: B:475:0x0cfd  */
    /* JADX WARN: Code duplicated, block: B:479:0x0d30  */
    /* JADX WARN: Code duplicated, block: B:480:0x0d33  */
    /* JADX WARN: Code duplicated, block: B:483:0x0d62  */
    /* JADX WARN: Code duplicated, block: B:486:0x0d74  */
    /* JADX WARN: Code duplicated, block: B:489:0x0da2  */
    /* JADX WARN: Code duplicated, block: B:491:0x0dac  */
    /* JADX WARN: Code duplicated, block: B:492:0x0daf  */
    /* JADX WARN: Code duplicated, block: B:496:0x0e2b  */
    /* JADX WARN: Code duplicated, block: B:499:0x0e36 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:503:0x0ea0  */
    /* JADX WARN: Code duplicated, block: B:510:0x0eb5  */
    /* JADX WARN: Code duplicated, block: B:513:0x0eba  */
    /* JADX WARN: Code duplicated, block: B:514:0x0ec7  */
    /* JADX WARN: Code duplicated, block: B:517:0x0f47  */
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
    public static final void CoreTextField(final TextFieldValue textFieldValue, final Function1<? super TextFieldValue, Unit> function1, Modifier modifier, TextStyle textStyle, VisualTransformation visualTransformation, Function1<? super TextLayoutResult, Unit> function2, MutableInteractionSource mutableInteractionSource, Brush brush, boolean z, int i, int i2, ImeOptions imeOptions, KeyboardActions keyboardActions, boolean z2, boolean z3, Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function3, TextFieldScrollerPosition textFieldScrollerPosition, Composer composer, final int i3, final int i4, final int i5) {
        Modifier modifier2;
        TextStyle textStyle2;
        VisualTransformation visualTransformation2;
        Function1<? super TextLayoutResult, Unit> function4;
        MutableInteractionSource mutableInteractionSource2;
        Brush brush2;
        int i6;
        int i7;
        int i8;
        final ImeOptions imeOptions2;
        final boolean z4;
        final Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> function5;
        final TextFieldScrollerPosition textFieldScrollerPosition2;
        final VisualTransformation visualTransformation3;
        final Brush brush3;
        final MutableInteractionSource mutableInteractionSource3;
        final Function1<? super TextLayoutResult, Unit> function6;
        final TextStyle textStyle3;
        final Modifier modifier3;
        final boolean z5;
        final int i9;
        final int i10;
        final KeyboardActions keyboardActions2;
        final boolean z6;
        Composer composer2;
        boolean z7;
        ImeOptions imeOptions3;
        int i11;
        KeyboardActions keyboardActions3;
        Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit> lambda$559628295$foundation;
        MutableInteractionSource mutableInteractionSource4;
        Modifier modifier4;
        boolean z8;
        final boolean z9;
        final Function1<? super TextLayoutResult, Unit> function7;
        int i12;
        TextFieldScrollerPosition textFieldScrollerPosition3;
        int i13;
        ImeOptions imeOptions4;
        int i14;
        MutableInteractionSource mutableInteractionSource5;
        TextFieldScrollerPosition textFieldScrollerPosition4;
        ImeOptions imeOptions5;
        TransformedText transformedText;
        Object obj;
        AnnotatedString annotatedString;
        AnnotatedString annotatedString2;
        Object objRememberedValue;
        UndoManager undoManager;
        Object objRememberedValue2;
        Object objRememberedValue3;
        Object objRememberedValue4;
        final TextFieldSelectionManager textFieldSelectionManager;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        ImeOptions imeOptions6;
        boolean z14;
        boolean z15;
        boolean zChangedInstance;
        LegacyTextFieldState legacyTextFieldState;
        boolean z16;
        TextInputService textInputService;
        final TextFieldValue textFieldValue2;
        OffsetMapping offsetMapping;
        TextFieldSelectionManager textFieldSelectionManager2;
        BringIntoViewRequester bringIntoViewRequester;
        CoroutineScope coroutineScope;
        Object obj2;
        MutableInteractionSource mutableInteractionSource6;
        boolean z17;
        State stateRememberUpdatedState;
        final boolean z18;
        TextFieldSelectionManager textFieldSelectionManager3;
        TextInputService textInputService2;
        boolean zChanged;
        final LegacyTextFieldState legacyTextFieldState2;
        State state;
        final TextInputService textInputService3;
        TextFieldSelectionManager textFieldSelectionManager4;
        CoreTextFieldKt$CoreTextField$5$1 coreTextFieldKt$CoreTextField$5$1;
        boolean zChangedInstance2;
        Object objRememberedValue5;
        ImeOptions imeOptions7;
        boolean z19;
        boolean z20;
        boolean zChangedInstance3;
        Object objRememberedValue6;
        TextFieldSelectionManager textFieldSelectionManager5;
        boolean z21;
        final OffsetMapping offsetMapping2;
        final LegacyTextFieldState legacyTextFieldState3;
        Object obj3;
        final TextFieldSelectionManager textFieldSelectionManager6;
        boolean zChangedInstance4;
        final boolean z22;
        PointerInputEventHandler pointerInputEventHandlerRememberedValue;
        MutableInteractionSource mutableInteractionSource7;
        boolean z23;
        boolean zChangedInstance5;
        Object objRememberedValue7;
        boolean z24;
        boolean z25;
        boolean zChangedInstance6;
        final LegacyTextFieldState legacyTextFieldState4;
        Object objRememberedValue8;
        LegacyTextFieldState legacyTextFieldState5;
        final ImeOptions imeOptions8;
        boolean z26;
        Modifier modifierCursor;
        boolean zChangedInstance7;
        Object objRememberedValue9;
        VisualTransformation visualTransformation4;
        boolean z27;
        boolean z28;
        Object objRememberedValue10;
        Modifier modifier5;
        final int i15;
        boolean z29;
        final boolean z30;
        boolean zChanged2;
        Object objRememberedValue11;
        TextInputService textInputService4;
        boolean zChangedInstance8;
        Object objRememberedValue12;
        boolean z31;
        Modifier.Companion companionTextFieldMagnifier;
        Composer composerStartRestartGroup = composer.startRestartGroup(31062401);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CoreTextField)N(value,onValueChange,modifier,textStyle,visualTransformation,onTextLayout,interactionSource,cursorBrush,softWrap,maxLines,minLines,imeOptions,keyboardActions,enabled,readOnly,decorationBox,textScrollerPosition)213@12062L29,214@12132L58,215@12236L72,220@12366L7,221@12427L7,222@12495L7,223@12560L7,224@12605L7,225@12674L7,246@13516L277,258@14098L21,260@14144L453,289@14997L26,292@15090L24,293@15148L37,295@15205L51,301@15494L7,303@15589L7,304@15646L7,314@16020L206,327@16443L1514,362@18064L42,363@18132L971,363@18111L992,389@19173L28,390@19269L992,412@20352L177,421@20630L577,439@21278L1606,492@23496L48,492@23470L74,494@23579L503,494@23550L532,526@24756L987,545@25860L7,546@25917L7,550@26055L327,559@26411L35,587@27673L5409,587@27620L5462:CoreTextField.kt#423gt5");
        int i16 = i3;
        int i17 = i4;
        if ((i3 & 6) == 0) {
            i16 |= composerStartRestartGroup.changed(textFieldValue) ? 4 : 2;
        }
        if ((i3 & 48) == 0) {
            i16 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        int i18 = i5 & 4;
        if (i18 != 0) {
            i16 |= 384;
            modifier2 = modifier;
        } else if ((i3 & 384) == 0) {
            modifier2 = modifier;
            i16 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i19 = i5 & 8;
        if (i19 != 0) {
            i16 |= 3072;
            textStyle2 = textStyle;
        } else if ((i3 & 3072) == 0) {
            textStyle2 = textStyle;
            i16 |= composerStartRestartGroup.changed(textStyle2) ? 2048 : 1024;
        } else {
            textStyle2 = textStyle;
        }
        int i20 = i5 & 16;
        if (i20 != 0) {
            i16 |= 24576;
            visualTransformation2 = visualTransformation;
        } else if ((i3 & 24576) == 0) {
            visualTransformation2 = visualTransformation;
            i16 |= composerStartRestartGroup.changed(visualTransformation2) ? 16384 : 8192;
        } else {
            visualTransformation2 = visualTransformation;
        }
        int i21 = i5 & 32;
        if (i21 != 0) {
            i16 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function4 = function2;
        } else if ((i3 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function4 = function2;
            i16 |= composerStartRestartGroup.changedInstance(function4) ? 131072 : 65536;
        } else {
            function4 = function2;
        }
        int i22 = i5 & 64;
        if (i22 != 0) {
            i16 |= 1572864;
            mutableInteractionSource2 = mutableInteractionSource;
        } else if ((i3 & 1572864) == 0) {
            mutableInteractionSource2 = mutableInteractionSource;
            i16 |= composerStartRestartGroup.changed(mutableInteractionSource2) ? 1048576 : 524288;
        } else {
            mutableInteractionSource2 = mutableInteractionSource;
        }
        int i23 = i5 & 128;
        if (i23 != 0) {
            i16 |= 12582912;
            brush2 = brush;
        } else if ((i3 & 12582912) == 0) {
            brush2 = brush;
            i16 |= composerStartRestartGroup.changed(brush2) ? 8388608 : 4194304;
        } else {
            brush2 = brush;
        }
        int i24 = i5 & 256;
        if (i24 != 0) {
            i16 |= 100663296;
        } else if ((i3 & 100663296) == 0) {
            i16 |= composerStartRestartGroup.changed(z) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i25 = i5 & 512;
        if (i25 != 0) {
            i16 |= C.ENCODING_PCM_32BIT;
        } else if ((i3 & C.ENCODING_PCM_32BIT) == 0) {
            i16 |= composerStartRestartGroup.changed(i) ? 536870912 : 268435456;
        }
        int i26 = i5 & 1024;
        if (i26 != 0) {
            i17 |= 6;
        } else if ((i4 & 6) == 0) {
            i17 |= composerStartRestartGroup.changed(i2) ? 4 : 2;
        }
        if ((i4 & 48) == 0) {
            i17 |= ((i5 & 2048) == 0 && composerStartRestartGroup.changed(imeOptions)) ? 32 : 16;
        }
        int i27 = i5 & 4096;
        if (i27 != 0) {
            i17 |= 384;
            i6 = i27;
        } else {
            i6 = i27;
            if ((i4 & 384) == 0) {
                i17 |= composerStartRestartGroup.changed(keyboardActions) ? 256 : 128;
            }
        }
        int i28 = i5 & 8192;
        if (i28 != 0) {
            i17 |= 3072;
            i7 = i28;
        } else {
            i7 = i28;
            if ((i4 & 3072) == 0) {
                i17 |= composerStartRestartGroup.changed(z2) ? 2048 : 1024;
            }
        }
        int i29 = i5 & 16384;
        if (i29 != 0) {
            i17 |= 24576;
            i8 = i29;
        } else {
            i8 = i29;
            if ((i4 & 24576) == 0) {
                i17 |= composerStartRestartGroup.changed(z3) ? 16384 : 8192;
            }
        }
        int i30 = i5 & 32768;
        if (i30 != 0) {
            i17 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            i17 |= composerStartRestartGroup.changedInstance(function3) ? 131072 : 65536;
        }
        int i31 = i5 & 65536;
        if (i31 != 0) {
            i17 |= 1572864;
        } else if ((i4 & 1572864) == 0) {
            i17 |= composerStartRestartGroup.changed(textFieldScrollerPosition) ? 1048576 : 524288;
        }
        int i32 = i17;
        if (composerStartRestartGroup.shouldExecute(((i16 & 306783379) == 306783378 && (i32 & 599187) == 599186) ? false : true, i16 & 1)) {
            composerStartRestartGroup.startDefaults();
            ComposerKt.sourceInformation(composerStartRestartGroup, "198@11387L2");
            if ((i3 & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                if (i18 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i19 != 0) {
                    textStyle2 = TextStyle.INSTANCE.getDefault();
                }
                VisualTransformation none = i20 != 0 ? VisualTransformation.INSTANCE.getNone() : visualTransformation2;
                if (i21 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -213794269, "CC(remember):CoreTextField.kt#9igjgp");
                    Object objRememberedValue13 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                        Function1 function8 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj4) {
                                return CoreTextFieldKt.CoreTextField$lambda$0$0((TextLayoutResult) obj4);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(function8);
                        objRememberedValue13 = function8;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function4 = (Function1) objRememberedValue13;
                }
                MutableInteractionSource mutableInteractionSource8 = i22 != 0 ? null : mutableInteractionSource2;
                SolidColor solidColor = i23 != 0 ? new SolidColor(Color.INSTANCE.m5133getUnspecified0d7_KjU(), null) : brush2;
                z7 = i24 != 0 ? true : z;
                int i33 = i25 != 0 ? Integer.MAX_VALUE : i;
                int i34 = i26 != 0 ? 1 : i2;
                if ((i5 & 2048) != 0) {
                    imeOptions3 = ImeOptions.INSTANCE.getDefault();
                    i11 = i32 & (-113);
                } else {
                    imeOptions3 = imeOptions;
                    i11 = i32;
                }
                keyboardActions3 = i6 != 0 ? KeyboardActions.INSTANCE.getDefault() : keyboardActions;
                boolean z32 = i7 != 0 ? true : z2;
                boolean z33 = i8 != 0 ? false : z3;
                lambda$559628295$foundation = i30 != 0 ? ComposableSingletons$CoreTextFieldKt.INSTANCE.getLambda$559628295$foundation() : function3;
                if (i31 != 0) {
                    textFieldScrollerPosition3 = null;
                    Brush brush4 = solidColor;
                    mutableInteractionSource4 = mutableInteractionSource8;
                    modifier4 = modifier2;
                    z8 = z32;
                    z9 = z33;
                    function7 = function4;
                    i12 = i34;
                    brush2 = brush4;
                    i13 = i11;
                    imeOptions4 = imeOptions3;
                    i14 = i33;
                    visualTransformation2 = none;
                } else {
                    Brush brush5 = solidColor;
                    mutableInteractionSource4 = mutableInteractionSource8;
                    modifier4 = modifier2;
                    z8 = z32;
                    z9 = z33;
                    function7 = function4;
                    i12 = i34;
                    brush2 = brush5;
                    textFieldScrollerPosition3 = textFieldScrollerPosition;
                    i13 = i11;
                    imeOptions4 = imeOptions3;
                    i14 = i33;
                    visualTransformation2 = none;
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i5 & 2048) != 0) {
                    z7 = z;
                    imeOptions4 = imeOptions;
                    keyboardActions3 = keyboardActions;
                    z9 = z3;
                    lambda$559628295$foundation = function3;
                    textFieldScrollerPosition3 = textFieldScrollerPosition;
                    i13 = i32 & (-113);
                    mutableInteractionSource4 = mutableInteractionSource2;
                    function7 = function4;
                    modifier4 = modifier2;
                    i14 = i;
                    i12 = i2;
                    z8 = z2;
                } else {
                    z7 = z;
                    imeOptions4 = imeOptions;
                    keyboardActions3 = keyboardActions;
                    z9 = z3;
                    lambda$559628295$foundation = function3;
                    textFieldScrollerPosition3 = textFieldScrollerPosition;
                    i13 = i32;
                    mutableInteractionSource4 = mutableInteractionSource2;
                    function7 = function4;
                    modifier4 = modifier2;
                    i14 = i;
                    i12 = i2;
                    z8 = z2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(31062401, i16, i13, "androidx.compose.foundation.text.CoreTextField (CoreTextField.kt:212)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -213772642, "CC(remember):CoreTextField.kt#9igjgp");
            int i35 = i16;
            Object objRememberedValue14 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                FocusRequester focusRequester = new FocusRequester();
                composerStartRestartGroup.updateRememberedValue(focusRequester);
                objRememberedValue14 = focusRequester;
            }
            FocusRequester focusRequester2 = (FocusRequester) objRememberedValue14;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -213770373, "CC(remember):CoreTextField.kt#9igjgp");
            Object objRememberedValue15 = composerStartRestartGroup.rememberedValue();
            final int i36 = i12;
            if (objRememberedValue15 == Composer.INSTANCE.getEmpty()) {
                LegacyPlatformTextInputServiceAdapter legacyPlatformTextInputServiceAdapterCreateLegacyPlatformTextInputServiceAdapter = LegacyPlatformTextInputServiceAdapter_androidKt.createLegacyPlatformTextInputServiceAdapter();
                composerStartRestartGroup.updateRememberedValue(legacyPlatformTextInputServiceAdapterCreateLegacyPlatformTextInputServiceAdapter);
                objRememberedValue15 = legacyPlatformTextInputServiceAdapterCreateLegacyPlatformTextInputServiceAdapter;
            }
            final LegacyPlatformTextInputServiceAdapter legacyPlatformTextInputServiceAdapter = (LegacyPlatformTextInputServiceAdapter) objRememberedValue15;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -213767031, "CC(remember):CoreTextField.kt#9igjgp");
            Object objRememberedValue16 = composerStartRestartGroup.rememberedValue();
            final TextStyle textStyle4 = textStyle2;
            if (objRememberedValue16 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue16 = new TextInputService(legacyPlatformTextInputServiceAdapter);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue16);
            }
            final TextInputService textInputService5 = (TextInputService) objRememberedValue16;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            KeyboardActions keyboardActions4 = keyboardActions3;
            Modifier modifier6 = modifier4;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Density density = (Density) objConsume;
            ProvidableCompositionLocal<FontFamily.Resolver> localFontFamilyResolver = CompositionLocalsKt.getLocalFontFamilyResolver();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localFontFamilyResolver);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            FontFamily.Resolver resolver = (FontFamily.Resolver) objConsume2;
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume3 = composerStartRestartGroup.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long selectionBackgroundColor = ((SelectionColors) objConsume3).getSelectionBackgroundColor();
            ProvidableCompositionLocal<FocusManager> localFocusManager = CompositionLocalsKt.getLocalFocusManager();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume4 = composerStartRestartGroup.consume(localFocusManager);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            FocusManager focusManager = (FocusManager) objConsume4;
            ProvidableCompositionLocal<WindowInfo> localWindowInfo = CompositionLocalsKt.getLocalWindowInfo();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume5 = composerStartRestartGroup.consume(localWindowInfo);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final WindowInfo windowInfo = (WindowInfo) objConsume5;
            ProvidableCompositionLocal<SoftwareKeyboardController> localSoftwareKeyboardController = CompositionLocalsKt.getLocalSoftwareKeyboardController();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume6 = composerStartRestartGroup.consume(localSoftwareKeyboardController);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SoftwareKeyboardController softwareKeyboardController = (SoftwareKeyboardController) objConsume6;
            final Orientation orientation = i14 == 1 && z7 == 0 && imeOptions4.getSingleLine() ? Orientation.Horizontal : Orientation.Vertical;
            if (textFieldScrollerPosition3 == null) {
                composerStartRestartGroup.startReplaceGroup(-213744626);
                ComposerKt.sourceInformation(composerStartRestartGroup, "232@13005L70,232@12934L141");
                Object[] objArr = {orientation};
                Saver<TextFieldScrollerPosition, Object> saver = TextFieldScrollerPosition.INSTANCE.getSaver();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -213742425, "CC(remember):CoreTextField.kt#9igjgp");
                boolean zChanged3 = composerStartRestartGroup.changed(orientation.ordinal());
                Object objRememberedValue17 = composerStartRestartGroup.rememberedValue();
                if (zChanged3) {
                    mutableInteractionSource5 = mutableInteractionSource4;
                } else {
                    mutableInteractionSource5 = mutableInteractionSource4;
                    if (objRememberedValue17 != Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    TextFieldScrollerPosition textFieldScrollerPosition5 = (TextFieldScrollerPosition) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) saver, (Function0) objRememberedValue17, composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                    textFieldScrollerPosition4 = textFieldScrollerPosition5;
                }
                objRememberedValue17 = new Function0() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CoreTextFieldKt.CoreTextField$lambda$4$0(orientation);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue17);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                TextFieldScrollerPosition textFieldScrollerPosition6 = (TextFieldScrollerPosition) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) saver, (Function0) objRememberedValue17, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
                textFieldScrollerPosition4 = textFieldScrollerPosition6;
            } else {
                mutableInteractionSource5 = mutableInteractionSource4;
                composerStartRestartGroup.startReplaceGroup(-213745742);
                composerStartRestartGroup.endReplaceGroup();
                textFieldScrollerPosition4 = textFieldScrollerPosition3;
            }
            if (textFieldScrollerPosition4.getOrientation() != orientation) {
                throw new IllegalArgumentException("Mismatching scroller orientation; " + (orientation == Orientation.Vertical ? "only single-line, non-wrap text fields can scroll horizontally" : "single-line, non-wrap text fields can only scroll horizontally"));
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -213725866, "CC(remember):CoreTextField.kt#9igjgp");
            boolean z34 = ((i35 & 14) == 4) | ((i35 & 57344) == 16384);
            Object objRememberedValue18 = composerStartRestartGroup.rememberedValue();
            if (z34 || objRememberedValue18 == Composer.INSTANCE.getEmpty()) {
                TransformedText transformedTextFilterWithValidation = ValidatingOffsetMappingKt.filterWithValidation(visualTransformation2, textFieldValue.getText());
                TextRange composition = textFieldValue.getComposition();
                if (composition != null) {
                    imeOptions5 = imeOptions4;
                    TransformedText transformedTextM1262applyCompositionDecoration72CqOWE = TextFieldDelegate.INSTANCE.m1262applyCompositionDecoration72CqOWE(composition.getPackedValue(), transformedTextFilterWithValidation);
                    if (transformedTextM1262applyCompositionDecoration72CqOWE != null) {
                        transformedText = transformedTextFilterWithValidation;
                        transformedText = transformedTextM1262applyCompositionDecoration72CqOWE;
                    }
                } else {
                    imeOptions5 = imeOptions4;
                    transformedText = transformedTextFilterWithValidation;
                }
                transformedText = transformedTextFilterWithValidation;
                composerStartRestartGroup.updateRememberedValue(transformedText);
                obj = transformedText;
            } else {
                imeOptions5 = imeOptions4;
                obj = objRememberedValue18;
            }
            TransformedText transformedText2 = (TransformedText) obj;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            AnnotatedString text = transformedText2.getText();
            final OffsetMapping offsetMapping3 = transformedText2.getOffsetMapping();
            RecomposeScope currentRecomposeScope = ComposablesKt.getCurrentRecomposeScope(composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -213705594, "CC(remember):CoreTextField.kt#9igjgp");
            boolean zChanged4 = composerStartRestartGroup.changed(softwareKeyboardController);
            Object objRememberedValue19 = composerStartRestartGroup.rememberedValue();
            if (zChanged4) {
                annotatedString = text;
            } else {
                annotatedString = text;
                if (objRememberedValue19 != Composer.INSTANCE.getEmpty()) {
                    annotatedString2 = annotatedString;
                }
                final LegacyTextFieldState legacyTextFieldState6 = (LegacyTextFieldState) objRememberedValue19;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                legacyTextFieldState6.m1227updatefnh65Uc(textFieldValue.getText(), annotatedString2, textStyle4, z7, 
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x06c2: INVOKE 
                      (r0v85 'legacyTextFieldState6' androidx.compose.foundation.text.LegacyTextFieldState A[D('state' androidx.compose.foundation.text.LegacyTextFieldState)])
                      (wrap androidx.compose.ui.text.AnnotatedString:0x069f: INVOKE 
                      (r61v0 'textFieldValue' androidx.compose.ui.text.input.TextFieldValue A[D('value' androidx.compose.ui.text.input.TextFieldValue)])
                     VIRTUAL call: androidx.compose.ui.text.input.TextFieldValue.getAnnotatedString():androidx.compose.ui.text.AnnotatedString A[MD:():androidx.compose.ui.text.AnnotatedString (m), WRAPPED] (LINE:275))
                      (r48v2 'annotatedString2' androidx.compose.ui.text.AnnotatedString A[D('visualText' androidx.compose.ui.text.AnnotatedString)])
                      (r65v1 'textStyle4' androidx.compose.ui.text.TextStyle A[D('textStyle' androidx.compose.ui.text.TextStyle)])
                      (r7v9 'z7' boolean)
                      (r70v1 androidx.compose.ui.unit.Density A[D('density' androidx.compose.ui.unit.Density)])
                      (r0v59 'resolver' androidx.compose.ui.text.font.FontFamily$Resolver A[D('fontFamilyResolver' androidx.compose.ui.text.font.FontFamily$Resolver)])
                      (r62v0 'function1' kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> A[D('onValueChange' kotlin.jvm.functions.Function1)])
                      (r77v1 'keyboardActions4' androidx.compose.foundation.text.KeyboardActions A[D('keyboardActions' androidx.compose.foundation.text.KeyboardActions)])
                      (r0v64 'focusManager' androidx.compose.ui.focus.FocusManager A[D('focusManager' androidx.compose.ui.focus.FocusManager)])
                      (r30v15 'selectionBackgroundColor' long A[D('selectionBackgroundColor' long)])
                     VIRTUAL call: androidx.compose.foundation.text.LegacyTextFieldState.update-fnh65Uc(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.text.TextStyle, boolean, androidx.compose.ui.unit.Density, androidx.compose.ui.text.font.FontFamily$Resolver, kotlin.jvm.functions.Function1, androidx.compose.foundation.text.KeyboardActions, androidx.compose.ui.focus.FocusManager, long):void A[MD:(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.text.TextStyle, boolean, androidx.compose.ui.unit.Density, androidx.compose.ui.text.font.FontFamily$Resolver, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit>, androidx.compose.foundation.text.KeyboardActions, androidx.compose.ui.focus.FocusManager, long):void (m)] (LINE:274) in method: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField(androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit>, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit>, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, boolean, int, int, androidx.compose.ui.text.input.ImeOptions, androidx.compose.foundation.text.KeyboardActions, boolean, boolean, kotlin.jvm.functions.Function3<? super kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, androidx.compose.foundation.text.TextFieldScrollerPosition, androidx.compose.runtime.Composer, int, int, int):void, file: classes.dex
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
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r70v1 androidx.compose.ui.unit.Density
                    	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                    */
                /*
                    Method dump skipped, instruction units count: 4009
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField(androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, boolean, int, int, androidx.compose.ui.text.input.ImeOptions, androidx.compose.foundation.text.KeyboardActions, boolean, boolean, kotlin.jvm.functions.Function3, androidx.compose.foundation.text.TextFieldScrollerPosition, androidx.compose.runtime.Composer, int, int, int):void");
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final TextFieldScrollerPosition CoreTextField$lambda$4$0(Orientation $orientation) {
                return new TextFieldScrollerPosition($orientation, 0.0f, 2, null);
            }

            static final Unit CoreTextField$lambda$10(TextFieldSelectionManager $manager, AnnotatedString it) {
                $manager.paste$foundation(it);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final Unit CoreTextField$lambda$13$0(LegacyTextFieldState $state, boolean $enabled, boolean $readOnly, TextInputService $textInputService, TextFieldValue $value, ImeOptions $imeOptions, OffsetMapping $offsetMapping, TextFieldSelectionManager $manager, CoroutineScope $coroutineScope, BringIntoViewRequester $bringIntoViewRequester, FocusState it) {
                TextFieldValue textFieldValue;
                OffsetMapping offsetMapping;
                TextLayoutResultProxy layoutResult;
                if ($state.getHasFocus() != it.isFocused()) {
                    $state.setHasFocus(it.isFocused());
                    if ($state.getHasFocus() && $enabled && !$readOnly) {
                        textFieldValue = $value;
                        offsetMapping = $offsetMapping;
                        startInputSession($textInputService, $state, textFieldValue, $imeOptions, offsetMapping);
                    } else {
                        textFieldValue = $value;
                        offsetMapping = $offsetMapping;
                        endInputSession($state);
                    }
                    if (it.isFocused() && (layoutResult = $state.getLayoutResult()) != null) {
                        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new CoreTextFieldKt$CoreTextField$focusModifier$1$1$1$1($bringIntoViewRequester, textFieldValue, $state, layoutResult, offsetMapping, null), 3, null);
                    }
                    if (!it.isFocused()) {
                        TextFieldSelectionManager.m1656deselect_kEHs6E$foundation$default($manager, null, 1, null);
                    }
                    return Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final boolean CoreTextField$lambda$14(State<Boolean> state) {
                Object thisObj$iv = state.getValue();
                return ((Boolean) thisObj$iv).booleanValue();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final Unit CoreTextField$lambda$16$0(LegacyTextFieldState $state, boolean it) {
                $state.setInTouchMode(it);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final Unit CoreTextField$lambda$17$0(LegacyTextFieldState $state, FocusRequester $focusRequester, boolean $readOnly, boolean $enabled, TextFieldSelectionManager $manager, OffsetMapping $offsetMapping, Offset offset) {
                tapToFocus($state, $focusRequester, !$readOnly);
                if ($state.getHasFocus() && $enabled) {
                    if ($state.getHandleState() != HandleState.Selection) {
                        TextLayoutResultProxy layoutResult = $state.getLayoutResult();
                        if (layoutResult != null) {
                            TextFieldDelegate.INSTANCE.m1265setCursorOffsetULxng0E$foundation(offset.m4862unboximpl(), layoutResult, $state.getProcessor(), $offsetMapping, $state.getOnValueChange());
                            if ($state.getTextDelegate().getText().length() > 0) {
                                $state.setHandleState(HandleState.Cursor);
                            }
                        }
                    } else {
                        $manager.m1660deselect_kEHs6E$foundation(offset);
                    }
                }
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final Unit CoreTextField$lambda$19$0(LegacyTextFieldState $state, TextFieldValue $value, OffsetMapping $offsetMapping, DrawScope $this$drawBehind) {
                TextLayoutResultProxy layoutResult = $state.getLayoutResult();
                if (layoutResult != null) {
                    Canvas canvas = $this$drawBehind.getDrawContext().getCanvas();
                    TextFieldDelegate.INSTANCE.m1263drawQ1vqE60$foundation(canvas, $value, $state.m1222getSelectionPreviewHighlightRanged9O1mEE(), $state.m1219getDeletionPreviewHighlightRanged9O1mEE(), $offsetMapping, layoutResult.getValue(), $state.getHighlightPaint(), $state.getSelectionBackgroundColor());
                }
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final Unit CoreTextField$lambda$20$0(LegacyTextFieldState $state, boolean $enabled, WindowInfo $windowInfo, TextFieldSelectionManager $manager, TextFieldValue $value, OffsetMapping $offsetMapping, LayoutCoordinates it) {
                TextInputSession inputSession;
                $state.setLayoutCoordinates(it);
                TextLayoutResultProxy layoutResult = $state.getLayoutResult();
                if (layoutResult != null) {
                    layoutResult.setInnerTextFieldCoordinates(it);
                }
                if ($enabled) {
                    if ($state.getHandleState() == HandleState.Selection) {
                        if ($state.getShowFloatingToolbar() && $windowInfo.isWindowFocused()) {
                            $manager.showSelectionToolbar$foundation();
                        } else {
                            $manager.hideSelectionToolbar$foundation();
                        }
                        $state.setShowSelectionHandleStart(TextFieldSelectionManager_androidKt.isSelectionHandleInVisibleBound($manager, true));
                        $state.setShowSelectionHandleEnd(TextFieldSelectionManager_androidKt.isSelectionHandleInVisibleBound($manager, false));
                        $state.setShowCursorHandle(TextRange.m7322getCollapsedimpl($value.getSelection()));
                    } else if ($state.getHandleState() == HandleState.Cursor) {
                        $state.setShowCursorHandle(TextFieldSelectionManager_androidKt.isSelectionHandleInVisibleBound($manager, true));
                    }
                    notifyFocusedRect($state, $value, $offsetMapping);
                    TextLayoutResultProxy layoutResult2 = $state.getLayoutResult();
                    if (layoutResult2 != null && (inputSession = $state.getInputSession()) != null && $state.getHasFocus()) {
                        TextFieldDelegate.INSTANCE.updateTextLayoutResult$foundation(inputSession, $value, $offsetMapping, layoutResult2);
                    }
                }
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DisposableEffectResult CoreTextField$lambda$21$0(final TextFieldSelectionManager $manager, DisposableEffectScope $this$DisposableEffect) {
                return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$lambda$21$0$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public void dispose() {
                        $manager.hideSelectionToolbar$foundation();
                    }
                };
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DisposableEffectResult CoreTextField$lambda$22$0(LegacyTextFieldState $state, TextInputService $textInputService, TextFieldValue $value, ImeOptions $imeOptions, DisposableEffectScope $this$DisposableEffect) {
                if ($state.getHasFocus()) {
                    $state.setInputSession(TextFieldDelegate.INSTANCE.restartInput$foundation($textInputService, $value, $state.getProcessor(), $imeOptions, $state.getOnValueChange(), $state.getOnImeActionPerformed()));
                }
                return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$lambda$22$0$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public void dispose() {
                    }
                };
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final Unit CoreTextField$lambda$23$0(boolean $handwritingEnabled, LegacyPlatformTextInputServiceAdapter $legacyTextInputServiceAdapter) {
                if ($handwritingEnabled) {
                    $legacyTextInputServiceAdapter.startStylusHandwriting();
                }
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final Unit CoreTextField$lambda$24$0(LegacyTextFieldState $state, Brush $autofillHighlightBrush, ContentDrawScope $this$drawWithContent) {
                $this$drawWithContent.drawContent();
                if ($state.getAutofillHighlightOn() || $state.getJustAutofilled()) {
                    DrawScope.m5664drawRectAsUm42w$default($this$drawWithContent, $autofillHighlightBrush, 0L, 0L, 0.0f, null, null, 0, WebSocketProtocol.PAYLOAD_SHORT, null);
                }
                return Unit.INSTANCE;
            }

            static final Unit CoreTextField$lambda$25(LegacyTextFieldState $state, LayoutCoordinates it) {
                TextLayoutResultProxy layoutResult = $state.getLayoutResult();
                if (layoutResult != null) {
                    layoutResult.setDecorationBoxCoordinates(it);
                }
                return Unit.INSTANCE;
            }

            static final Unit CoreTextField$lambda$26(Function3 $decorationBox, final LegacyTextFieldState $state, final TextStyle $textStyle, final int $minLines, final int $maxLines, final TextFieldScrollerPosition $scrollerPosition, final TextFieldValue $value, final VisualTransformation $visualTransformation, final Modifier $cursorModifier, final Modifier $drawModifier, final Modifier $onPositionedModifier, final Modifier $magnifierModifier, final BringIntoViewRequester $bringIntoViewRequester, final TextFieldSelectionManager $manager, final boolean $showHandleAndMagnifier, final boolean $readOnly, final Function1 $onTextLayout, final OffsetMapping $offsetMapping, final Density $density, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C588@27697L5379,588@27683L5393:CoreTextField.kt#423gt5");
                if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-814563849, $changed, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous> (CoreTextField.kt:588)");
                    }
                    $decorationBox.invoke(ComposableLambdaKt.rememberComposableLambda(-44346382, true, new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda23
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return CoreTextFieldKt.CoreTextField$lambda$26$0($state, $textStyle, $minLines, $maxLines, $scrollerPosition, $value, $visualTransformation, $cursorModifier, $drawModifier, $onPositionedModifier, $magnifierModifier, $bringIntoViewRequester, $manager, $showHandleAndMagnifier, $readOnly, $onTextLayout, $offsetMapping, $density, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, $composer, 54), $composer, 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer.skipToGroupEnd();
                }
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final Unit CoreTextField$lambda$26$0(final LegacyTextFieldState $state, TextStyle $textStyle, int $minLines, final int $maxLines, TextFieldScrollerPosition $scrollerPosition, final TextFieldValue $value, VisualTransformation $visualTransformation, Modifier $cursorModifier, Modifier $drawModifier, Modifier $onPositionedModifier, Modifier $magnifierModifier, BringIntoViewRequester $bringIntoViewRequester, final TextFieldSelectionManager $manager, final boolean $showHandleAndMagnifier, final boolean $readOnly, final Function1 $onTextLayout, final OffsetMapping $offsetMapping, final Density $density, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C602@28548L22,611@28935L4131,611@28899L4167:CoreTextField.kt#423gt5");
                if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-44346382, $changed, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous> (CoreTextField.kt:591)");
                    }
                    Modifier modifierHeightInLines = HeightInLinesModifierKt.heightInLines(SizeKt.m853heightInVpY3zN4$default(Modifier.INSTANCE, $state.m1220getMinHeightForSingleLineFieldD9Ej5fM(), 0.0f, 2, null), $textStyle, $minLines, $maxLines);
                    ComposerKt.sourceInformationMarkerStart($composer, 332827112, "CC(remember):CoreTextField.kt#9igjgp");
                    boolean invalid$iv = $composer.changedInstance($state);
                    Object it$iv = $composer.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = new Function0() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return $state.getLayoutResult();
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier coreTextFieldModifier = BringIntoViewRequesterKt.bringIntoViewRequester(TextFieldSizeKt.textFieldMinSize(TextFieldScroll_androidKt.textFieldScroll(modifierHeightInLines, $scrollerPosition, $value, $visualTransformation, (Function0) it$iv).then($cursorModifier).then($drawModifier), $textStyle).then($onPositionedModifier).then($magnifierModifier), $bringIntoViewRequester);
                    SimpleLayoutKt.SimpleLayout(coreTextFieldModifier, ComposableLambdaKt.rememberComposableLambda(1412697320, true, new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return CoreTextFieldKt.CoreTextField$lambda$26$0$1($manager, $state, $showHandleAndMagnifier, $readOnly, $onTextLayout, $value, $offsetMapping, $density, $maxLines, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, $composer, 54), $composer, 48, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer.skipToGroupEnd();
                }
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX WARN: Code duplicated, block: B:26:0x010b  */
            public static final Unit CoreTextField$lambda$26$0$1(TextFieldSelectionManager $manager, LegacyTextFieldState $state, boolean $showHandleAndMagnifier, boolean $readOnly, Function1 $onTextLayout, TextFieldValue $value, OffsetMapping $offsetMapping, Density $density, int $maxLines, Composer $composer, int $changed) {
                boolean z;
                ComposerKt.sourceInformation($composer, "C612@28953L3502,677@32473L359:CoreTextField.kt#423gt5");
                if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                    $composer.skipToGroupEnd();
                } else {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1412697320, $changed, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous>.<anonymous> (CoreTextField.kt:612)");
                    }
                    MeasurePolicy measurePolicy$iv = new CoreTextFieldKt$CoreTextField$8$1$1$2($state, $onTextLayout, $value, $offsetMapping, $density, $maxLines);
                    ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    Modifier modifier$iv = Modifier.INSTANCE;
                    int compositeKeyHash$iv = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                    CompositionLocalMap localMap$iv = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv = ((0 << 6) & 896) | 6;
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
                    Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl($this$Layout_u24lambda_u240$iv, Integer.valueOf(compositeKeyHash$iv), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl($this$Layout_u24lambda_u240$iv, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i = ($changed$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, 1613409842, "C:CoreTextField.kt#423gt5");
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if ($state.getHandleState() == HandleState.None || $state.getLayoutCoordinates() == null) {
                        z = false;
                    } else {
                        LayoutCoordinates layoutCoordinates = $state.getLayoutCoordinates();
                        Intrinsics.checkNotNull(layoutCoordinates);
                        if (layoutCoordinates.isAttached() && $showHandleAndMagnifier) {
                            z = true;
                        } else {
                            z = false;
                        }
                    }
                    SelectionToolbarAndHandles($manager, z, $composer, 0);
                    if ($state.getHandleState() == HandleState.Cursor && !$readOnly && $showHandleAndMagnifier) {
                        $composer.startReplaceGroup(-714666198);
                        ComposerKt.sourceInformation($composer, "689@32994L40");
                        TextFieldCursorHandle($manager, $composer, 0);
                    } else {
                        $composer.startReplaceGroup(-747377894);
                    }
                    $composer.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                return Unit.INSTANCE;
            }

            private static final void CoreTextFieldRootBox(final Modifier modifier, final TextFieldSelectionManager manager, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
                Composer $composer2;
                Function0<ComposeUiNode> function0;
                Composer $composer3 = $composer.startRestartGroup(2036174316);
                ComposerKt.sourceInformation($composer3, "C(CoreTextFieldRootBox)N(modifier,manager,content)702@33241L83:CoreTextField.kt#423gt5");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer3.changed(modifier) ? 4 : 2;
                }
                if (($changed & 48) == 0) {
                    $dirty |= $composer3.changedInstance(manager) ? 32 : 16;
                }
                if (($changed & 384) == 0) {
                    $dirty |= $composer3.changedInstance(function2) ? 256 : 128;
                }
                if (!$composer3.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                    $composer2 = $composer3;
                    $composer2.skipToGroupEnd();
                } else {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(2036174316, $dirty, -1, "androidx.compose.foundation.text.CoreTextFieldRootBox (CoreTextField.kt:701)");
                    }
                    int $changed$iv = ($dirty & 14) | 384;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
                    int $changed$iv$iv = ($changed$iv << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                    CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                    $composer2 = $composer3;
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    int $dirty2 = $dirty;
                    ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
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
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl($this$Layout_u24lambda_u240$iv$iv, Integer.valueOf(compositeKeyHash$iv$iv), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl($this$Layout_u24lambda_u240$iv$iv, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i2 = (($changed$iv >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1799443472, "C702@33289L33:CoreTextField.kt#423gt5");
                    ContextMenu_androidKt.ContextMenuArea(manager, function2, $composer3, (($dirty2 >> 3) & 14) | (($dirty2 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return CoreTextFieldKt.CoreTextFieldRootBox$lambda$1(modifier, manager, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }

            private static final Modifier previewKeyEventToDeselectOnBack(Modifier $this$previewKeyEventToDeselectOnBack, final LegacyTextFieldState state, final TextFieldSelectionManager manager) {
                return KeyInputModifierKt.onPreviewKeyEvent($this$previewKeyEventToDeselectOnBack, new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.previewKeyEventToDeselectOnBack.1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                        return m1197invokeZmokQxo(keyEvent.m6242unboximpl());
                    }

                    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                    public final Boolean m1197invokeZmokQxo(android.view.KeyEvent keyEvent) {
                        boolean z;
                        if (state.getHandleState() == HandleState.Selection && KeyEventHelpers_androidKt.m1200cancelsTextSelectionZmokQxo(keyEvent)) {
                            z = true;
                            TextFieldSelectionManager.m1656deselect_kEHs6E$foundation$default(manager, null, 1, null);
                        } else {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    }
                });
            }

            public static final void tapToFocus(LegacyTextFieldState state, FocusRequester focusRequester, boolean allowKeyboard) {
                SoftwareKeyboardController keyboardController;
                if (!state.getHasFocus()) {
                    FocusRequester.m4757requestFocus3ESFkO8$default(focusRequester, 0, 1, null);
                } else {
                    if (!allowKeyboard || (keyboardController = state.getKeyboardController()) == null) {
                        return;
                    }
                    keyboardController.show();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void startInputSession(TextInputService textInputService, LegacyTextFieldState state, TextFieldValue value, ImeOptions imeOptions, OffsetMapping offsetMapping) {
                state.setInputSession(TextFieldDelegate.INSTANCE.onFocus$foundation(textInputService, value, state.getProcessor(), imeOptions, state.getOnValueChange(), state.getOnImeActionPerformed()));
                notifyFocusedRect(state, value, offsetMapping);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void endInputSession(LegacyTextFieldState state) {
                TextInputSession session = state.getInputSession();
                if (session != null) {
                    TextFieldDelegate.INSTANCE.onBlur$foundation(session, state.getProcessor(), state.getOnValueChange());
                }
                state.setInputSession(null);
            }

            public static final Object bringSelectionEndIntoView(BringIntoViewRequester $this$bringSelectionEndIntoView, TextFieldValue value, TextDelegate textDelegate, TextLayoutResult textLayoutResult, OffsetMapping offsetMapping, Continuation<? super Unit> continuation) {
                Rect selectionEndBounds;
                int selectionEndInTransformed = offsetMapping.originalToTransformed(TextRange.m7325getMaximpl(value.getSelection()));
                if (selectionEndInTransformed < textLayoutResult.getLayoutInput().getText().length()) {
                    selectionEndBounds = textLayoutResult.getBoundingBox(selectionEndInTransformed);
                } else if (selectionEndInTransformed != 0) {
                    selectionEndBounds = textLayoutResult.getBoundingBox(selectionEndInTransformed - 1);
                } else {
                    long defaultSize = TextFieldDelegateKt.computeSizeForDefaultText$default(textDelegate.getStyle(), textDelegate.getDensity(), textDelegate.getFontFamilyResolver(), null, 0, 24, null);
                    selectionEndBounds = new Rect(0.0f, 0.0f, 1.0f, (int) (4294967295L & defaultSize));
                }
                Object objBringIntoView = $this$bringSelectionEndIntoView.bringIntoView(selectionEndBounds, continuation);
                return objBringIntoView == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objBringIntoView : Unit.INSTANCE;
            }

            private static final void SelectionToolbarAndHandles(final TextFieldSelectionManager manager, final boolean show, Composer $composer, final int $changed) {
                TextLayoutResultProxy layoutResult;
                TextLayoutResult value;
                Composer $composer2 = $composer.startRestartGroup(626339208);
                ComposerKt.sourceInformation($composer2, "C(SelectionToolbarAndHandles)N(manager,show):CoreTextField.kt#423gt5");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer2.changedInstance(manager) ? 4 : 2;
                }
                if (($changed & 48) == 0) {
                    $dirty |= $composer2.changed(show) ? 32 : 16;
                }
                if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(626339208, $dirty, -1, "androidx.compose.foundation.text.SelectionToolbarAndHandles (CoreTextField.kt:1054)");
                    }
                    if (show) {
                        $composer2.startReplaceGroup(1530097388);
                        ComposerKt.sourceInformation($composer2, "");
                        LegacyTextFieldState state = manager.getState();
                        TextLayoutResult it = null;
                        if (state != null && (layoutResult = state.getLayoutResult()) != null && (value = layoutResult.getValue()) != null) {
                            LegacyTextFieldState state2 = manager.getState();
                            if (!(state2 != null ? state2.getIsLayoutResultStale() : true)) {
                                it = value;
                            }
                        }
                        if (it == null) {
                            $composer2.startReplaceGroup(1530097387);
                        } else {
                            $composer2.startReplaceGroup(1530097388);
                            ComposerKt.sourceInformation($composer2, "");
                            if (TextRange.m7322getCollapsedimpl(manager.getValue$foundation().getSelection())) {
                                $composer2.startReplaceGroup(2062097806);
                            } else {
                                $composer2.startReplaceGroup(2109807302);
                                ComposerKt.sourceInformation($composer2, "");
                                int startOffset = manager.getOffsetMapping().originalToTransformed(TextRange.m7328getStartimpl(manager.getValue$foundation().getSelection()));
                                int endOffset = manager.getOffsetMapping().originalToTransformed(TextRange.m7323getEndimpl(manager.getValue$foundation().getSelection()));
                                ResolvedTextDirection startDirection = it.getBidiRunDirection(startOffset);
                                ResolvedTextDirection endDirection = it.getBidiRunDirection(Math.max(endOffset - 1, 0));
                                LegacyTextFieldState state3 = manager.getState();
                                if (state3 != null && state3.getShowSelectionHandleStart()) {
                                    $composer2.startReplaceGroup(2110225306);
                                    ComposerKt.sourceInformation($composer2, "1070@48536L220");
                                    TextFieldSelectionManagerKt.TextFieldSelectionHandle(true, startDirection, manager, $composer2, (($dirty << 6) & 896) | 6);
                                } else {
                                    $composer2.startReplaceGroup(2062097806);
                                }
                                $composer2.endReplaceGroup();
                                LegacyTextFieldState state4 = manager.getState();
                                if (state4 != null && state4.getShowSelectionHandleEnd()) {
                                    $composer2.startReplaceGroup(2110574459);
                                    ComposerKt.sourceInformation($composer2, "1077@48888L219");
                                    TextFieldSelectionManagerKt.TextFieldSelectionHandle(false, endDirection, manager, $composer2, (($dirty << 6) & 896) | 6);
                                } else {
                                    $composer2.startReplaceGroup(2062097806);
                                }
                                $composer2.endReplaceGroup();
                            }
                            $composer2.endReplaceGroup();
                            LegacyTextFieldState textFieldState = manager.getState();
                            if (textFieldState != null) {
                                if (manager.isTextChanged$foundation()) {
                                    textFieldState.setShowFloatingToolbar(false);
                                }
                                if (textFieldState.getHasFocus()) {
                                    if (textFieldState.getShowFloatingToolbar()) {
                                        manager.showSelectionToolbar$foundation();
                                    } else {
                                        manager.hideSelectionToolbar$foundation();
                                    }
                                }
                                Unit unit = Unit.INSTANCE;
                            }
                        }
                        $composer2.endReplaceGroup();
                        $composer2.endReplaceGroup();
                    } else {
                        $composer2.startReplaceGroup(1989076778);
                        $composer2.endReplaceGroup();
                        manager.hideSelectionToolbar$foundation();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer2.skipToGroupEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda22
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return CoreTextFieldKt.SelectionToolbarAndHandles$lambda$1(manager, show, $changed, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }

            /* JADX WARN: Code duplicated, block: B:33:0x0067  */
            /* JADX WARN: Code duplicated, block: B:60:0x016e  */
            public static final void TextFieldCursorHandle(final TextFieldSelectionManager manager, Composer $composer, final int $changed) {
                boolean z;
                Composer $composer2 = $composer.startRestartGroup(-1436003720);
                ComposerKt.sourceInformation($composer2, "C(TextFieldCursorHandle)N(manager):CoreTextField.kt#423gt5");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer2.changedInstance(manager) ? 4 : 2;
                }
                int $dirty2 = $dirty;
                if (!$composer2.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
                    $composer2.skipToGroupEnd();
                } else {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1436003720, $dirty2, -1, "androidx.compose.foundation.text.TextFieldCursorHandle (CoreTextField.kt:1101)");
                    }
                    LegacyTextFieldState state = manager.getState();
                    if (state != null && state.getShowCursorHandle()) {
                        AnnotatedString transformedText$foundation = manager.getTransformedText$foundation();
                        if (transformedText$foundation == null) {
                            z = false;
                        } else {
                            if (transformedText$foundation.length() > 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                        }
                        if (z) {
                            $composer2.startReplaceGroup(-2112351432);
                            ComposerKt.sourceInformation($composer2, "1103@50051L50,1104@50164L7,1106@50224L12,1108@50309L645,1120@50986L385,1105@50181L1201");
                            ComposerKt.sourceInformationMarkerStart($composer2, -1315066870, "CC(remember):CoreTextField.kt#9igjgp");
                            boolean invalid$iv = $composer2.changed(manager);
                            Object it$iv = $composer2.rememberedValue();
                            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                                Object value$iv = manager.cursorDragObserver$foundation();
                                $composer2.updateRememberedValue(value$iv);
                                it$iv = value$iv;
                            }
                            final TextDragObserver observer = (TextDragObserver) it$iv;
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                            Object objConsume = $composer2.consume(localDensity);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            final long position = manager.m1662getCursorPositiontuRUvjQ$foundation((Density) objConsume);
                            ComposerKt.sourceInformationMarkerStart($composer2, -1315061372, "CC(remember):CoreTextField.kt#9igjgp");
                            boolean invalid$iv2 = $composer2.changed(position);
                            Object it$iv2 = $composer2.rememberedValue();
                            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                Object value$iv2 = (OffsetProvider) new OffsetProvider() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$1$1
                                    @Override // androidx.compose.foundation.text.selection.OffsetProvider
                                    /* JADX INFO: renamed from: provide-F1C5BW0 */
                                    public final long mo1145provideF1C5BW0() {
                                        return position;
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv2);
                                it$iv2 = value$iv2;
                            }
                            OffsetProvider offsetProvider = (OffsetProvider) it$iv2;
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            Modifier.Companion companion = Modifier.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer2, -1315058019, "CC(remember):CoreTextField.kt#9igjgp");
                            boolean invalid$iv3 = $composer2.changedInstance(observer) | $composer2.changedInstance(manager);
                            Object it$iv3 = $composer2.rememberedValue();
                            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                                Object value$iv3 = (PointerInputEventHandler) new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1

                                    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1, reason: invalid class name */
                                    /* JADX INFO: compiled from: CoreTextField.kt */
                                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                                    @DebugMetadata(c = "androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1", f = "CoreTextField.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
                                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                        final /* synthetic */ TextFieldSelectionManager $manager;
                                        final /* synthetic */ TextDragObserver $observer;
                                        final /* synthetic */ PointerInputScope $this_pointerInput;
                                        private /* synthetic */ Object L$0;
                                        int label;

                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        AnonymousClass1(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, TextFieldSelectionManager textFieldSelectionManager, Continuation<? super AnonymousClass1> continuation) {
                                            super(2, continuation);
                                            this.$this_pointerInput = pointerInputScope;
                                            this.$observer = textDragObserver;
                                            this.$manager = textFieldSelectionManager;
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_pointerInput, this.$observer, this.$manager, continuation);
                                            anonymousClass1.L$0 = obj;
                                            return anonymousClass1;
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                        }

                                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object obj) {
                                            IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                            switch (this.label) {
                                                case 0:
                                                    ResultKt.throwOnFailure(obj);
                                                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                                                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new C00291(this.$this_pointerInput, this.$observer, null), 1, null);
                                                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass2(this.$this_pointerInput, this.$manager, null), 1, null);
                                                    return Unit.INSTANCE;
                                                default:
                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                        }

                                        /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$1, reason: invalid class name and collision with other inner class name */
                                        /* JADX INFO: compiled from: CoreTextField.kt */
                                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                                        @DebugMetadata(c = "androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$1", f = "CoreTextField.kt", i = {}, l = {1114}, m = "invokeSuspend", n = {}, s = {}, v = 1)
                                        static final class C00291 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            final /* synthetic */ TextDragObserver $observer;
                                            final /* synthetic */ PointerInputScope $this_pointerInput;
                                            int label;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            C00291(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, Continuation<? super C00291> continuation) {
                                                super(2, continuation);
                                                this.$this_pointerInput = pointerInputScope;
                                                this.$observer = textDragObserver;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                return new C00291(this.$this_pointerInput, this.$observer, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                return ((C00291) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Object invokeSuspend(Object $result) {
                                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                switch (this.label) {
                                                    case 0:
                                                        ResultKt.throwOnFailure($result);
                                                        this.label = 1;
                                                        if (LongPressTextDragObserverKt.detectDownAndDragGesturesWithObserver(this.$this_pointerInput, this.$observer, this) == coroutine_suspended) {
                                                            return coroutine_suspended;
                                                        }
                                                        break;
                                                    case 1:
                                                        ResultKt.throwOnFailure($result);
                                                        break;
                                                    default:
                                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                }
                                                return Unit.INSTANCE;
                                            }
                                        }

                                        /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$2, reason: invalid class name */
                                        /* JADX INFO: compiled from: CoreTextField.kt */
                                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                                        @DebugMetadata(c = "androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$2", f = "CoreTextField.kt", i = {}, l = {1117}, m = "invokeSuspend", n = {}, s = {}, v = 1)
                                        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                            final /* synthetic */ TextFieldSelectionManager $manager;
                                            final /* synthetic */ PointerInputScope $this_pointerInput;
                                            int label;

                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            AnonymousClass2(PointerInputScope pointerInputScope, TextFieldSelectionManager textFieldSelectionManager, Continuation<? super AnonymousClass2> continuation) {
                                                super(2, continuation);
                                                this.$this_pointerInput = pointerInputScope;
                                                this.$manager = textFieldSelectionManager;
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                return new AnonymousClass2(this.$this_pointerInput, this.$manager, continuation);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                            }

                                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Object invokeSuspend(Object $result) {
                                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                switch (this.label) {
                                                    case 0:
                                                        ResultKt.throwOnFailure($result);
                                                        PointerInputScope pointerInputScope = this.$this_pointerInput;
                                                        final TextFieldSelectionManager textFieldSelectionManager = this.$manager;
                                                        this.label = 1;
                                                        if (TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, null, 
                                                        /*  JADX ERROR: Method code generation error
                                                            jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$2$$ExternalSyntheticLambda0, state: NOT_LOADED
                                                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                                                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                                            	at jadx.core.codegen.ConditionGen.addCompare(ConditionGen.java:129)
                                                            	at jadx.core.codegen.ConditionGen.add(ConditionGen.java:57)
                                                            	at jadx.core.codegen.ConditionGen.add(ConditionGen.java:46)
                                                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:115)
                                                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                                            	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:267)
                                                            	at jadx.core.dex.regions.SwitchRegion.generate(SwitchRegion.java:90)
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
                                                            */
                                                        /*
                                                            this = this;
                                                            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                                                            int r1 = r9.label
                                                            switch(r1) {
                                                                case 0: goto L16;
                                                                case 1: goto L12;
                                                                default: goto L9;
                                                            }
                                                        L9:
                                                            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                                                            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                                                            r10.<init>(r0)
                                                            throw r10
                                                        L12:
                                                            kotlin.ResultKt.throwOnFailure(r10)
                                                            goto L34
                                                        L16:
                                                            kotlin.ResultKt.throwOnFailure(r10)
                                                            androidx.compose.ui.input.pointer.PointerInputScope r1 = r9.$this_pointerInput
                                                            androidx.compose.foundation.text.selection.TextFieldSelectionManager r2 = r9.$manager
                                                            androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$2$$ExternalSyntheticLambda0 r5 = new androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1$1$2$$ExternalSyntheticLambda0
                                                            r5.<init>(r2)
                                                            r6 = r9
                                                            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                                                            r2 = 1
                                                            r9.label = r2
                                                            r2 = 0
                                                            r3 = 0
                                                            r4 = 0
                                                            r7 = 7
                                                            r8 = 0
                                                            java.lang.Object r1 = androidx.compose.foundation.gestures.TapGestureDetectorKt.detectTapGestures$default(r1, r2, r3, r4, r5, r6, r7, r8)
                                                            if (r1 != r0) goto L34
                                                            return r0
                                                        L34:
                                                            kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                                            return r0
                                                        */
                                                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$2$1.AnonymousClass1.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
                                                    }

                                                    static final Unit invokeSuspend$lambda$0(TextFieldSelectionManager $manager, Offset it) {
                                                        $manager.showSelectionToolbar$foundation();
                                                        return Unit.INSTANCE;
                                                    }
                                                }
                                            }

                                            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                                            public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                                                Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass1($this$pointerInput, observer, manager, null), continuation);
                                                return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
                                            }
                                        };
                                        $composer2.updateRememberedValue(value$iv3);
                                        it$iv3 = value$iv3;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, observer, (PointerInputEventHandler) it$iv3);
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1315036615, "CC(remember):CoreTextField.kt#9igjgp");
                                    boolean invalid$iv4 = $composer2.changed(position);
                                    Object it$iv4 = $composer2.rememberedValue();
                                    if (invalid$iv4 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                                        Object value$iv4 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda20
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                return CoreTextFieldKt.TextFieldCursorHandle$lambda$3$0(position, (SemanticsPropertyReceiver) obj);
                                            }
                                        };
                                        $composer2.updateRememberedValue(value$iv4);
                                        it$iv4 = value$iv4;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    AndroidCursorHandle_androidKt.m1131CursorHandleUSBMPiE(offsetProvider, SemanticsModifierKt.semantics$default(modifierPointerInput, false, (Function1) it$iv4, 1, null), 0L, $composer2, 0, 4);
                                } else {
                                    $composer2.startReplaceGroup(2132946858);
                                }
                            } else {
                                $composer2.startReplaceGroup(2132946858);
                            }
                            $composer2.endReplaceGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$$ExternalSyntheticLambda21
                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(Object obj, Object obj2) {
                                    return CoreTextFieldKt.TextFieldCursorHandle$lambda$4(manager, $changed, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public static final Unit TextFieldCursorHandle$lambda$3$0(long $position, SemanticsPropertyReceiver $this$semantics) {
                        $this$semantics.set(SelectionHandlesKt.getSelectionHandleInfoKey(), new SelectionHandleInfo(Handle.Cursor, $position, SelectionHandleAnchor.Middle, true, null));
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public static final void notifyFocusedRect(LegacyTextFieldState state, TextFieldValue value, OffsetMapping offsetMapping) {
                        TextInputSession inputSession;
                        LayoutCoordinates layoutCoordinates;
                        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
                        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
                        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
                        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
                        try {
                            TextLayoutResultProxy layoutResult = state.getLayoutResult();
                            if (layoutResult != null && (inputSession = state.getInputSession()) != null && (layoutCoordinates = state.getLayoutCoordinates()) != null) {
                                TextFieldDelegate.INSTANCE.notifyFocusedRect$foundation(value, state.getTextDelegate(), layoutResult.getValue(), layoutCoordinates, inputSession, state.getHasFocus(), offsetMapping);
                                Unit unit = Unit.INSTANCE;
                            }
                        } finally {
                            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                        }
                    }

                    private static final Modifier addContextMenuComponents(Modifier $this$addContextMenuComponents, TextFieldSelectionManager textFieldSelectionManager, CoroutineScope coroutineScope) {
                        if (ComposeFoundationFlags.isNewContextMenuEnabled) {
                            return TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents($this$addContextMenuComponents, textFieldSelectionManager, coroutineScope);
                        }
                        return $this$addContextMenuComponents;
                    }
                }
