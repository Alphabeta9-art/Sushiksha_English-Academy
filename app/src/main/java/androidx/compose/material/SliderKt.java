package androidx.compose.material;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.HoverableKt;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.DragScope;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PointMode;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import androidx.profileinstaller.ProfileVerifier;
import androidx.window.core.layout.WindowSizeClass;
import com.google.api.ClientProto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: Slider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\u001a\u007f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\b\b\u0003\u0010\f\u001a\u00020\r2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0007¢\u0006\u0002\u0010\u0014\u001aj\u0010\u0015\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\t2\u0018\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00050\u00182\u0014\u0010\u0019\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f0\u0018H\u0002\u001a\u007f\u0010\u001a\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0018\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\b\b\u0003\u0010\f\u001a\u00020\r2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0007¢\u0006\u0002\u0010\u001b\u001aK\u0010\u001c\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u00032\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u0010!\u001ak\u0010\"\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u00032\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u0007H\u0003¢\u0006\u0002\u0010)\u001aC\u0010*\u001a\u00020\u0001*\u00020+2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\t2\u0006\u0010.\u001a\u00020-H\u0003¢\u0006\u0004\b/\u00100\u001aS\u00101\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\t2\u0006\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u00032\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u00102\u001a\u00020\u00032\u0006\u00103\u001a\u00020\u0003H\u0003¢\u0006\u0002\u00104\u001a.\u00105\u001a\u00020\u00032\u0006\u00106\u001a\u00020\u00032\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u00107\u001a\u00020\u00032\u0006\u00108\u001a\u00020\u0003H\u0002\u001a2\u00109\u001a\u0010\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\u0003\u0018\u00010:*\u00020<2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@H\u0082@¢\u0006\u0004\bA\u0010B\u001a\u0016\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010\f\u001a\u00020\rH\u0002\u001a0\u0010D\u001a\u00020\u00032\u0006\u0010E\u001a\u00020\u00032\u0006\u0010F\u001a\u00020\u00032\u0006\u0010G\u001a\u00020\u00032\u0006\u0010H\u001a\u00020\u00032\u0006\u0010I\u001a\u00020\u0003H\u0002\u001a<\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010E\u001a\u00020\u00032\u0006\u0010F\u001a\u00020\u00032\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010H\u001a\u00020\u00032\u0006\u0010I\u001a\u00020\u0003H\u0002\u001a \u0010K\u001a\u00020\u00032\u0006\u0010L\u001a\u00020\u00032\u0006\u0010M\u001a\u00020\u00032\u0006\u0010N\u001a\u00020\u0003H\u0002\u001aS\u0010O\u001a\u00020\u00012\u0012\u0010P\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\f\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00030S2\u0006\u0010\u0002\u001a\u00020\u0003H\u0003¢\u0006\u0002\u0010T\u001a\\\u0010U\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0002\u001aj\u0010V\u001a\u00020\u0007*\u00020\u00072\u0006\u0010W\u001a\u00020X2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u00108\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\t2\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00030\u00182\u0018\u0010Z\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00050\u00182\f\u0010[\u001a\b\u0012\u0004\u0012\u00020\u00030S2\u0006\u0010\b\u001a\u00020\tH\u0002\u001a.\u0010\\\u001a\u00020\u00012\u0006\u0010W\u001a\u00020X2\u0006\u00106\u001a\u00020\u00032\u0006\u0010]\u001a\u00020\u00032\u0006\u0010^\u001a\u00020\u0003H\u0082@¢\u0006\u0002\u0010_\u001a\u0098\u0001\u0010`\u001a\u00020\u0007*\u00020\u00072\u0006\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u00112\f\u0010a\u001a\b\u0012\u0004\u0012\u00020\u00030\u00182\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00182\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\t2\u0006\u00108\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0018\u0010Z\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u00050\u00182\u001e\u0010c\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010d0\u0018H\u0002\"\u0016\u0010e\u001a\u00020-X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bf\u0010g\"\u0010\u0010i\u001a\u00020-X\u0082\u0004¢\u0006\u0004\n\u0002\u0010h\"\u0010\u0010j\u001a\u00020-X\u0082\u0004¢\u0006\u0004\n\u0002\u0010h\"\u0010\u0010k\u001a\u00020-X\u0082\u0004¢\u0006\u0004\n\u0002\u0010h\"\u0016\u0010l\u001a\u00020-X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bm\u0010g\"\u0010\u0010n\u001a\u00020-X\u0082\u0004¢\u0006\u0004\n\u0002\u0010h\"\u0010\u0010o\u001a\u00020-X\u0082\u0004¢\u0006\u0004\n\u0002\u0010h\"\u000e\u0010p\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010q\u001a\b\u0012\u0004\u0012\u00020\u00030rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006s"}, d2 = {"Slider", "", "value", "", "onValueChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "valueRange", "Lkotlin/ranges/ClosedFloatingPointRange;", "steps", "", "onValueChangeFinished", "Lkotlin/Function0;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "colors", "Landroidx/compose/material/SliderColors;", "(FLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/SliderColors;Landroidx/compose/runtime/Composer;II)V", "slideOnKeyEvents", "isRtl", "onValueChangeState", "Landroidx/compose/runtime/State;", "onValueChangeFinishedState", "RangeSlider", "(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/material/SliderColors;Landroidx/compose/runtime/Composer;II)V", "SliderImpl", "positionFraction", "tickFractions", "", "width", "(ZFLjava/util/List;Landroidx/compose/material/SliderColors;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "RangeSliderImpl", "positionFractionStart", "positionFractionEnd", "startInteractionSource", "endInteractionSource", "startThumbSemantics", "endThumbSemantics", "(ZFFLjava/util/List;Landroidx/compose/material/SliderColors;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "SliderThumb", "Landroidx/compose/foundation/layout/BoxScope;", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/unit/Dp;", "thumbSize", "SliderThumb-PcYyNuk", "(Landroidx/compose/foundation/layout/BoxScope;Landroidx/compose/ui/Modifier;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/SliderColors;ZFLandroidx/compose/runtime/Composer;I)V", "Track", "thumbPx", "trackStrokeWidth", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material/SliderColors;ZFFLjava/util/List;FFLandroidx/compose/runtime/Composer;I)V", "snapValueToTick", "current", "minPx", "maxPx", "awaitSlop", "Lkotlin/Pair;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "id", "Landroidx/compose/ui/input/pointer/PointerId;", "type", "Landroidx/compose/ui/input/pointer/PointerType;", "awaitSlop-8vUncbI", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stepsToTickFractions", "scale", "a1", "b1", "x1", "a2", "b2", "x", "calcFraction", CmcdHeadersFactory.OBJECT_TYPE_AUDIO_ONLY, "b", "pos", "CorrectValueSideEffect", "scaleToOffset", "trackRange", "valueState", "Landroidx/compose/runtime/MutableState;", "(Lkotlin/jvm/functions/Function1;Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/ranges/ClosedFloatingPointRange;Landroidx/compose/runtime/MutableState;FLandroidx/compose/runtime/Composer;I)V", "sliderSemantics", "sliderTapModifier", "draggableState", "Landroidx/compose/foundation/gestures/DraggableState;", "rawOffset", "gestureEndAction", "pressOffset", "animateToTarget", TypedValues.AttributesType.S_TARGET, "velocity", "(Landroidx/compose/foundation/gestures/DraggableState;FFFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rangeSliderPressDragModifier", "rawOffsetStart", "rawOffsetEnd", "onDrag", "Lkotlin/Function2;", "ThumbRadius", "getThumbRadius", "()F", "F", "ThumbRippleRadius", "ThumbDefaultElevation", "ThumbPressedElevation", "TrackHeight", "getTrackHeight", "SliderHeight", "SliderMinWidth", "DefaultSliderConstraints", "SliderToTickAnimation", "Landroidx/compose/animation/core/TweenSpec;", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SliderKt {
    private static final float ThumbRadius = Dp.m7905constructorimpl(10);
    private static final float ThumbRippleRadius = Dp.m7905constructorimpl(24);
    private static final float ThumbDefaultElevation = Dp.m7905constructorimpl(1);
    private static final float ThumbPressedElevation = Dp.m7905constructorimpl(6);
    private static final float TrackHeight = Dp.m7905constructorimpl(4);
    private static final float SliderHeight = Dp.m7905constructorimpl(48);
    private static final float SliderMinWidth = Dp.m7905constructorimpl(144);
    private static final Modifier DefaultSliderConstraints = SizeKt.m853heightInVpY3zN4$default(SizeKt.m872widthInVpY3zN4$default(Modifier.INSTANCE, SliderMinWidth, 0.0f, 2, null), 0.0f, SliderHeight, 1, null);
    private static final TweenSpec<Float> SliderToTickAnimation = new TweenSpec<>(100, 0, null, 6, null);

    static final Unit CorrectValueSideEffect$lambda$1(Function1 function1, ClosedFloatingPointRange closedFloatingPointRange, ClosedFloatingPointRange closedFloatingPointRange2, MutableState mutableState, float f, int i, Composer composer, int i2) {
        CorrectValueSideEffect(function1, closedFloatingPointRange, closedFloatingPointRange2, mutableState, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit RangeSlider$lambda$5(ClosedFloatingPointRange closedFloatingPointRange, Function1 function1, Modifier modifier, boolean z, ClosedFloatingPointRange closedFloatingPointRange2, int i, Function0 function0, SliderColors sliderColors, int i2, int i3, Composer composer, int i4) {
        RangeSlider(closedFloatingPointRange, function1, modifier, z, closedFloatingPointRange2, i, function0, sliderColors, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit RangeSliderImpl$lambda$1(boolean z, float f, float f2, List list, SliderColors sliderColors, float f3, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, Modifier modifier, Modifier modifier2, Modifier modifier3, int i, int i2, Composer composer, int i3) {
        RangeSliderImpl(z, f, f2, list, sliderColors, f3, mutableInteractionSource, mutableInteractionSource2, modifier, modifier2, modifier3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    static final Unit Slider$lambda$4(float f, Function1 function1, Modifier modifier, boolean z, ClosedFloatingPointRange closedFloatingPointRange, int i, Function0 function0, MutableInteractionSource mutableInteractionSource, SliderColors sliderColors, int i2, int i3, Composer composer, int i4) {
        Slider(f, function1, modifier, z, closedFloatingPointRange, i, function0, mutableInteractionSource, sliderColors, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit SliderImpl$lambda$1(boolean z, float f, List list, SliderColors sliderColors, float f2, MutableInteractionSource mutableInteractionSource, Modifier modifier, int i, Composer composer, int i2) {
        SliderImpl(z, f, list, sliderColors, f2, mutableInteractionSource, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SliderThumb_PcYyNuk$lambda$1(BoxScope boxScope, Modifier modifier, float f, MutableInteractionSource mutableInteractionSource, SliderColors sliderColors, boolean z, float f2, int i, Composer composer, int i2) {
        m1927SliderThumbPcYyNuk(boxScope, modifier, f, mutableInteractionSource, sliderColors, z, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Track$lambda$1(Modifier modifier, SliderColors sliderColors, boolean z, float f, float f2, List list, float f3, float f4, int i, Composer composer, int i2) {
        Track(modifier, sliderColors, z, f, f2, list, f3, f4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void Slider(final float value, final Function1<? super Float, Unit> function1, Modifier modifier, boolean enabled, ClosedFloatingPointRange<Float> closedFloatingPointRange, int steps, Function0<Unit> function0, MutableInteractionSource interactionSource, SliderColors colors, Composer $composer, final int $changed, final int i) {
        float f;
        Modifier modifier2;
        boolean enabled2;
        ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo;
        int steps2;
        Composer $composer2;
        final Function0<Unit> function2;
        final Modifier modifier3;
        final boolean enabled3;
        final ClosedFloatingPointRange<Float> closedFloatingPointRange2;
        final int steps3;
        final MutableInteractionSource interactionSource2;
        final SliderColors colors2;
        int $dirty;
        int steps4;
        Function0<Unit> function3;
        MutableInteractionSource interactionSource3;
        Composer $composer3;
        final SliderColors colors3;
        boolean enabled4;
        MutableInteractionSource mutableInteractionSource;
        Composer $composer4 = $composer.startRestartGroup(-1962335196);
        ComposerKt.sourceInformation($composer4, "C(Slider)N(value,onValueChange,modifier,enabled,valueRange,steps,onValueChangeFinished,interactionSource,colors)168@8088L35,169@8161L43,170@8229L47,190@8882L7,194@9014L3201,172@8282L3933:Slider.kt#jmzs0o");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            f = value;
            $dirty2 |= $composer4.changed(f) ? 4 : 2;
        } else {
            f = value;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer4.changedInstance(function1) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer4.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer4.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                closedFloatingPointRangeRangeTo = closedFloatingPointRange;
                int i4 = $composer4.changed(closedFloatingPointRangeRangeTo) ? 16384 : 8192;
                $dirty2 |= i4;
            } else {
                closedFloatingPointRangeRangeTo = closedFloatingPointRange;
            }
            $dirty2 |= i4;
        } else {
            closedFloatingPointRangeRangeTo = closedFloatingPointRange;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            steps2 = steps;
        } else if ((196608 & $changed) == 0) {
            steps2 = steps;
            $dirty2 |= $composer4.changed(steps2) ? 131072 : 65536;
        } else {
            steps2 = steps;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer4.changedInstance(function0) ? 1048576 : 524288;
        }
        int steps5 = i & 128;
        if (steps5 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer4.changed(interactionSource) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= ((i & 256) == 0 && $composer4.changed(colors)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty3 = $dirty2;
        if ($composer4.shouldExecute(($dirty2 & 38347923) != 38347922, $dirty3 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "163@7873L8");
            if (($changed & 1) != 0 && !$composer4.getDefaultsInvalid()) {
                $composer4.skipToGroupEnd();
                int $dirty4 = (i & 16) != 0 ? $dirty3 & (-57345) : $dirty3;
                if ((i & 256) != 0) {
                    $dirty4 &= -234881025;
                }
                $composer3 = $composer4;
                function3 = function0;
                interactionSource3 = interactionSource;
                colors3 = colors;
                $dirty = $dirty4;
                enabled4 = enabled2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    enabled2 = true;
                }
                if ((i & 16) == 0) {
                    $dirty = $dirty3;
                } else {
                    $dirty = $dirty3 & (-57345);
                    closedFloatingPointRangeRangeTo = RangesKt.rangeTo(0.0f, 1.0f);
                }
                if (i5 == 0) {
                    steps4 = steps2;
                } else {
                    steps4 = 0;
                }
                if (i6 == 0) {
                    function3 = function0;
                } else {
                    function3 = null;
                }
                if (steps5 == 0) {
                    interactionSource3 = interactionSource;
                } else {
                    interactionSource3 = null;
                }
                if ((i & 256) != 0) {
                    $composer3 = $composer4;
                    $dirty &= -234881025;
                    steps2 = steps4;
                    enabled4 = enabled2;
                    colors3 = SliderDefaults.INSTANCE.m1918colorsq0g_0yA(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer4, 0, 6, 1023);
                } else {
                    $composer3 = $composer4;
                    colors3 = colors;
                    steps2 = steps4;
                    enabled4 = enabled2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1962335196, $dirty, -1, "androidx.compose.material.Slider (Slider.kt:164)");
            }
            if (interactionSource3 == null) {
                $composer3.startReplaceGroup(-1127489737);
                ComposerKt.sourceInformation($composer3, "166@7968L39");
                ComposerKt.sourceInformationMarkerStart($composer3, -313465301, "CC(remember):Slider.kt#9igjgp");
                Composer $this$cache$iv = $composer3;
                Object it$iv = $this$cache$iv.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $this$cache$iv.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
                mutableInteractionSource = (MutableInteractionSource) it$iv;
            } else {
                $composer3.startReplaceGroup(-313465952);
                $composer3.endReplaceGroup();
                mutableInteractionSource = interactionSource3;
            }
            final MutableInteractionSource interactionSource4 = mutableInteractionSource;
            if (!(steps2 >= 0)) {
                throw new IllegalArgumentException("steps should be >= 0".toString());
            }
            final State onValueChangeState = SnapshotStateKt.rememberUpdatedState(function1, $composer3, ($dirty >> 3) & 14);
            State onValueChangeFinishedState = SnapshotStateKt.rememberUpdatedState(function3, $composer3, ($dirty >> 18) & 14);
            ComposerKt.sourceInformationMarkerStart($composer3, -313456941, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv = (458752 & $dirty) == 131072;
            Composer $this$cache$iv2 = $composer3;
            Object it$iv2 = $this$cache$iv2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = stepsToTickFractions(steps2);
                $this$cache$iv2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            final List tickFractions = (List) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier2);
            float arg0$iv = ThumbRadius;
            float fM7905constructorimpl = Dp.m7905constructorimpl(2 * arg0$iv);
            float arg0$iv2 = ThumbRadius;
            final Function0<Unit> function4 = function3;
            final boolean enabled5 = enabled4;
            Modifier modifierFocusable = FocusableKt.focusable(sliderSemantics(SizeKt.m861requiredSizeInqDBjuR0$default(modifierMinimumInteractiveComponentSize, fM7905constructorimpl, Dp.m7905constructorimpl(2 * arg0$iv2), 0.0f, 0.0f, 12, null), f, enabled5, function1, function4, closedFloatingPointRangeRangeTo, steps2), enabled5, interactionSource4);
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            Composer $composer$iv = $composer3;
            ComposerKt.sourceInformationMarkerStart($composer$iv, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer$iv.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            int steps6 = steps2;
            Modifier modifierSlideOnKeyEvents = slideOnKeyEvents(modifierFocusable, enabled5, steps6, closedFloatingPointRangeRangeTo, value, objConsume == LayoutDirection.Rtl, onValueChangeState, onValueChangeFinishedState);
            Composer $composer5 = $composer3;
            final ClosedFloatingPointRange<Float> closedFloatingPointRange3 = closedFloatingPointRangeRangeTo;
            BoxWithConstraintsKt.BoxWithConstraints(modifierSlideOnKeyEvents, null, false, ComposableLambdaKt.rememberComposableLambda(2085116814, true, new Function3() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return SliderKt.Slider$lambda$3(closedFloatingPointRange3, value, tickFractions, function4, interactionSource4, enabled5, colors3, onValueChangeState, (BoxWithConstraintsScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer5, 54), $composer5, 3072, 6);
            $composer2 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            enabled3 = enabled5;
            closedFloatingPointRange2 = closedFloatingPointRangeRangeTo;
            function2 = function4;
            colors2 = colors3;
            interactionSource2 = interactionSource3;
            steps3 = steps6;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            function2 = function0;
            modifier3 = modifier2;
            enabled3 = enabled2;
            closedFloatingPointRange2 = closedFloatingPointRangeRangeTo;
            steps3 = steps2;
            interactionSource2 = interactionSource;
            colors2 = colors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderKt.Slider$lambda$4(value, function1, modifier3, enabled3, closedFloatingPointRange2, steps3, function2, interactionSource2, colors2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final Unit Slider$lambda$3(final ClosedFloatingPointRange $valueRange, float $value, final List $tickFractions, final Function0 $onValueChangeFinished, MutableInteractionSource $interactionSource, boolean $enabled, SliderColors $colors, final State $onValueChangeState, BoxWithConstraintsScope $this$BoxWithConstraints, Composer $composer, int $changed) {
        Object value$iv;
        final SliderDraggableState draggableState;
        Ref.FloatRef minPx;
        Ref.FloatRef maxPx;
        ComposerKt.sourceInformation($composer, "C195@9057L7,200@9218L7,211@9632L24,212@9681L54,213@9762L36,216@9841L420,225@10294L15,225@10271L83,228@10437L633,228@10399L671,259@11645L55,266@11999L210:Slider.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed($this$BoxWithConstraints) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if ($composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2085116814, $dirty2, -1, "androidx.compose.material.Slider.<anonymous> (Slider.kt:195)");
            }
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer);
            boolean isRtl = objConsume == LayoutDirection.Rtl;
            float widthPx = Constraints.m7858getMaxWidthimpl($this$BoxWithConstraints.mo713getConstraintsmsEJaDk());
            final Ref.FloatRef maxPx2 = new Ref.FloatRef();
            final Ref.FloatRef minPx2 = new Ref.FloatRef();
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Density $this$Slider_u24lambda_u243_u240 = (Density) objConsume2;
            maxPx2.element = Math.max(widthPx - $this$Slider_u24lambda_u243_u240.mo406toPx0680j_4(ThumbRadius), 0.0f);
            minPx2.element = Math.min($this$Slider_u24lambda_u243_u240.mo406toPx0680j_4(ThumbRadius), maxPx2.element);
            ComposerKt.sourceInformationMarkerStart($composer, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object it$iv$iv = $composer.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer);
                $composer.updateRememberedValue(value$iv$iv);
                it$iv$iv = value$iv$iv;
            }
            final CoroutineScope scope = (CoroutineScope) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -1952447580, "CC(remember):Slider.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = PrimitiveSnapshotStateKt.mutableFloatStateOf(Slider$lambda$3$scaleToOffset($valueRange, minPx2, maxPx2, $value));
                $composer.updateRememberedValue(value$iv2);
                it$iv = value$iv2;
            }
            final MutableFloatState rawOffset = (MutableFloatState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -1952445006, "CC(remember):Slider.kt#9igjgp");
            Object it$iv2 = $composer.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
                $composer.updateRememberedValue(value$iv3);
                it$iv2 = value$iv3;
            }
            final MutableFloatState pressOffset = (MutableFloatState) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -1952442094, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv = $composer.changed(minPx2.element) | $composer.changed(maxPx2.element) | $composer.changed($valueRange);
            Object it$iv3 = $composer.rememberedValue();
            if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv = new SliderDraggableState(new Function1() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SliderKt.Slider$lambda$3$3$0(rawOffset, pressOffset, minPx2, maxPx2, $onValueChangeState, $valueRange, ((Float) obj).floatValue());
                    }
                });
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv3;
            }
            SliderDraggableState draggableState2 = (SliderDraggableState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -1952428003, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv2 = $composer.changed($valueRange) | $composer.changed(
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x01ca: ARITH (r2v19 'invalid$iv2' boolean) = (wrap boolean:0x01c3: ARITH (wrap boolean:0x01b9: INVOKE 
                  (r40v0 '$composer' androidx.compose.runtime.Composer A[D('$composer' androidx.compose.runtime.Composer)])
                  (r31v0 '$valueRange' kotlin.ranges.ClosedFloatingPointRange A[D('$valueRange' kotlin.ranges.ClosedFloatingPointRange)])
                 INTERFACE call: androidx.compose.runtime.Composer.changed(java.lang.Object):boolean A[MD:(java.lang.Object):boolean (m), WRAPPED]) | (wrap boolean:0x01bf: INVOKE 
                  (r40v0 '$composer' androidx.compose.runtime.Composer A[D('$composer' androidx.compose.runtime.Composer)])
                  (wrap float:0x01bd: IGET (r3v1 ?? I:??[OBJECT, ARRAY]) A[WRAPPED] kotlin.jvm.internal.Ref.FloatRef.element float)
                 INTERFACE call: androidx.compose.runtime.Composer.changed(float):boolean A[MD:(float):boolean (m), WRAPPED]) A[DONT_WRAP, WRAPPED]) | (wrap boolean:0x01c6: INVOKE 
                  (r40v0 '$composer' androidx.compose.runtime.Composer A[D('$composer' androidx.compose.runtime.Composer)])
                  (wrap float:0x01c4: IGET (r4v1 'maxPx2' kotlin.jvm.internal.Ref$FloatRef) A[WRAPPED] kotlin.jvm.internal.Ref.FloatRef.element float)
                 INTERFACE call: androidx.compose.runtime.Composer.changed(float):boolean A[MD:(float):boolean (m), WRAPPED]) A[DECLARE_VAR] in method: androidx.compose.material.SliderKt.Slider$lambda$3(kotlin.ranges.ClosedFloatingPointRange, float, java.util.List, kotlin.jvm.functions.Function0, androidx.compose.foundation.interaction.MutableInteractionSource, boolean, androidx.compose.material.SliderColors, androidx.compose.runtime.State, androidx.compose.foundation.layout.BoxWithConstraintsScope, androidx.compose.runtime.Composer, int):kotlin.Unit, file: classes.dex
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
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r3v1 ??
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                */
            /*
                Method dump skipped, instruction units count: 878
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SliderKt.Slider$lambda$3(kotlin.ranges.ClosedFloatingPointRange, float, java.util.List, kotlin.jvm.functions.Function0, androidx.compose.foundation.interaction.MutableInteractionSource, boolean, androidx.compose.material.SliderColors, androidx.compose.runtime.State, androidx.compose.foundation.layout.BoxWithConstraintsScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
        }

        private static final float Slider$lambda$3$scaleToUserValue(Ref.FloatRef minPx, Ref.FloatRef maxPx, ClosedFloatingPointRange<Float> closedFloatingPointRange, float offset) {
            return scale(minPx.element, maxPx.element, offset, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float Slider$lambda$3$scaleToOffset(ClosedFloatingPointRange<Float> closedFloatingPointRange, Ref.FloatRef minPx, Ref.FloatRef maxPx, float userValue) {
            return scale(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), userValue, minPx.element, maxPx.element);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit Slider$lambda$3$3$0(MutableFloatState $rawOffset, MutableFloatState $pressOffset, Ref.FloatRef $minPx, Ref.FloatRef $maxPx, State $onValueChangeState, ClosedFloatingPointRange $valueRange, float it) {
            $rawOffset.setFloatValue($rawOffset.getFloatValue() + it + $pressOffset.getFloatValue());
            $pressOffset.setFloatValue(0.0f);
            float offsetInTrack = RangesKt.coerceIn($rawOffset.getFloatValue(), $minPx.element, $maxPx.element);
            ((Function1) $onValueChangeState.getValue()).invoke(Float.valueOf(Slider$lambda$3$scaleToUserValue($minPx, $maxPx, $valueRange, offsetInTrack)));
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit Slider$lambda$3$5$0(MutableFloatState $rawOffset, List $tickFractions, Ref.FloatRef $minPx, Ref.FloatRef $maxPx, CoroutineScope $scope, SliderDraggableState $draggableState, Function0 $onValueChangeFinished, float velocity) {
            float current = $rawOffset.getFloatValue();
            float target = snapValueToTick(current, $tickFractions, $minPx.element, $maxPx.element);
            if (!(current == target)) {
                BuildersKt__Builders_commonKt.launch$default($scope, null, null, new SliderKt$Slider$2$gestureEndAction$1$1$1($draggableState, current, target, velocity, $onValueChangeFinished, null), 3, null);
            } else if (!$draggableState.isDragging() && $onValueChangeFinished != null) {
                $onValueChangeFinished.invoke();
            }
            return Unit.INSTANCE;
        }

        private static final Modifier slideOnKeyEvents(Modifier $this$slideOnKeyEvents, final boolean enabled, final int steps, final ClosedFloatingPointRange<Float> closedFloatingPointRange, final float value, final boolean isRtl, final State<? extends Function1<? super Float, Unit>> state, final State<? extends Function0<Unit>> state2) {
            if (steps >= 0) {
                return KeyInputModifierKt.onKeyEvent($this$slideOnKeyEvents, new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material.SliderKt.slideOnKeyEvents.2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                        return m1930invokeZmokQxo(keyEvent.m6242unboximpl());
                    }

                    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                    public final Boolean m1930invokeZmokQxo(android.view.KeyEvent keyEvent) {
                        int sign;
                        boolean z = false;
                        if (!enabled) {
                            return false;
                        }
                        int iM6254getTypeZmokQxo = KeyEvent_androidKt.m6254getTypeZmokQxo(keyEvent);
                        if (KeyEventType.m6246equalsimpl0(iM6254getTypeZmokQxo, KeyEventType.INSTANCE.m6250getKeyDownCS__XNY())) {
                            float rangeLength = Math.abs(closedFloatingPointRange.getEndInclusive().floatValue() - closedFloatingPointRange.getStart().floatValue());
                            int actualSteps = steps > 0 ? steps + 1 : 100;
                            float delta = rangeLength / actualSteps;
                            long jM6253getKeyZmokQxo = KeyEvent_androidKt.m6253getKeyZmokQxo(keyEvent);
                            if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6023getDirectionUpEK5gGoQ())) {
                                state.getValue().invoke(RangesKt.coerceIn(Float.valueOf(value + delta), closedFloatingPointRange));
                                z = true;
                            } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6018getDirectionDownEK5gGoQ())) {
                                state.getValue().invoke(RangesKt.coerceIn(Float.valueOf(value - delta), closedFloatingPointRange));
                                z = true;
                            } else {
                                if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6022getDirectionRightEK5gGoQ())) {
                                    sign = isRtl ? -1 : 1;
                                    state.getValue().invoke(RangesKt.coerceIn(Float.valueOf(value + (sign * delta)), closedFloatingPointRange));
                                    z = true;
                                } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6021getDirectionLeftEK5gGoQ())) {
                                    sign = isRtl ? -1 : 1;
                                    state.getValue().invoke(RangesKt.coerceIn(Float.valueOf(value - (sign * delta)), closedFloatingPointRange));
                                    z = true;
                                } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6097getMoveHomeEK5gGoQ())) {
                                    state.getValue().invoke(closedFloatingPointRange.getStart());
                                    z = true;
                                } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6096getMoveEndEK5gGoQ())) {
                                    state.getValue().invoke(closedFloatingPointRange.getEndInclusive());
                                    z = true;
                                } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6134getPageUpEK5gGoQ())) {
                                    int page = RangesKt.coerceIn(actualSteps / 10, 1, 10);
                                    state.getValue().invoke(RangesKt.coerceIn(Float.valueOf(value - (page * delta)), closedFloatingPointRange));
                                    z = true;
                                } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6133getPageDownEK5gGoQ())) {
                                    int page2 = RangesKt.coerceIn(actualSteps / 10, 1, 10);
                                    state.getValue().invoke(RangesKt.coerceIn(Float.valueOf(value + (page2 * delta)), closedFloatingPointRange));
                                    z = true;
                                }
                            }
                        } else if (KeyEventType.m6246equalsimpl0(iM6254getTypeZmokQxo, KeyEventType.INSTANCE.m6251getKeyUpCS__XNY())) {
                            long jM6253getKeyZmokQxo2 = KeyEvent_androidKt.m6253getKeyZmokQxo(keyEvent);
                            if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6023getDirectionUpEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6018getDirectionDownEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6022getDirectionRightEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6021getDirectionLeftEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6097getMoveHomeEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6096getMoveEndEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6134getPageUpEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6133getPageDownEK5gGoQ())) {
                                Function0<Unit> value2 = state2.getValue();
                                if (value2 != null) {
                                    value2.invoke();
                                }
                                z = true;
                            }
                        }
                        return Boolean.valueOf(z);
                    }
                });
            }
            throw new IllegalArgumentException("steps should be >= 0".toString());
        }

        public static final void RangeSlider(final ClosedFloatingPointRange<Float> closedFloatingPointRange, final Function1<? super ClosedFloatingPointRange<Float>, Unit> function1, Modifier modifier, boolean enabled, ClosedFloatingPointRange<Float> closedFloatingPointRange2, int steps, Function0<Unit> function0, SliderColors colors, Composer $composer, final int $changed, final int i) {
            ClosedFloatingPointRange<Float> closedFloatingPointRange3;
            Modifier modifier2;
            boolean enabled2;
            ClosedFloatingPointRange<Float> closedFloatingPointRange4;
            int i2;
            Function0<Unit> function2;
            Composer $composer2;
            final Modifier modifier3;
            final boolean enabled3;
            final ClosedFloatingPointRange<Float> closedFloatingPointRange5;
            final Function0<Unit> function3;
            final int steps2;
            final SliderColors colors2;
            ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo;
            int $dirty;
            int steps3;
            Function0<Unit> function4;
            Composer $composer3;
            final SliderColors colors3;
            final int steps4;
            final boolean enabled4;
            final Function0<Unit> function5;
            Composer $composer4 = $composer.startRestartGroup(-1556183027);
            ComposerKt.sourceInformation($composer4, "C(RangeSlider)N(value,onValueChange,modifier,enabled,valueRange,steps,onValueChangeFinished,colors)405@18059L39,406@18156L39,409@18277L35,410@18337L47,417@18596L5354,412@18390L5560:Slider.kt#jmzs0o");
            int $dirty2 = $changed;
            if (($changed & 6) == 0) {
                closedFloatingPointRange3 = closedFloatingPointRange;
                $dirty2 |= $composer4.changed(closedFloatingPointRange3) ? 4 : 2;
            } else {
                closedFloatingPointRange3 = closedFloatingPointRange;
            }
            if (($changed & 48) == 0) {
                $dirty2 |= $composer4.changedInstance(function1) ? 32 : 16;
            }
            int i3 = i & 4;
            if (i3 != 0) {
                $dirty2 |= 384;
                modifier2 = modifier;
            } else if (($changed & 384) == 0) {
                modifier2 = modifier;
                $dirty2 |= $composer4.changed(modifier2) ? 256 : 128;
            } else {
                modifier2 = modifier;
            }
            int i4 = i & 8;
            if (i4 != 0) {
                $dirty2 |= 3072;
                enabled2 = enabled;
            } else if (($changed & 3072) == 0) {
                enabled2 = enabled;
                $dirty2 |= $composer4.changed(enabled2) ? 2048 : 1024;
            } else {
                enabled2 = enabled;
            }
            if (($changed & 24576) == 0) {
                if ((i & 16) == 0) {
                    closedFloatingPointRange4 = closedFloatingPointRange2;
                    int i5 = $composer4.changed(closedFloatingPointRange4) ? 16384 : 8192;
                    $dirty2 |= i5;
                } else {
                    closedFloatingPointRange4 = closedFloatingPointRange2;
                }
                $dirty2 |= i5;
            } else {
                closedFloatingPointRange4 = closedFloatingPointRange2;
            }
            int i6 = i & 32;
            if (i6 != 0) {
                $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                i2 = steps;
            } else if ((196608 & $changed) == 0) {
                i2 = steps;
                $dirty2 |= $composer4.changed(i2) ? 131072 : 65536;
            } else {
                i2 = steps;
            }
            int i7 = i & 64;
            if (i7 != 0) {
                $dirty2 |= 1572864;
                function2 = function0;
            } else if (($changed & 1572864) == 0) {
                function2 = function0;
                $dirty2 |= $composer4.changedInstance(function2) ? 1048576 : 524288;
            } else {
                function2 = function0;
            }
            if (($changed & 12582912) == 0) {
                $dirty2 |= ((i & 128) == 0 && $composer4.changed(colors)) ? 8388608 : 4194304;
            }
            int $dirty3 = $dirty2;
            if ($composer4.shouldExecute(($dirty2 & 4793491) != 4793490, $dirty3 & 1)) {
                $composer4.startDefaults();
                ComposerKt.sourceInformation($composer4, "403@17986L8");
                if (($changed & 1) != 0 && !$composer4.getDefaultsInvalid()) {
                    $composer4.skipToGroupEnd();
                    if ((i & 16) != 0) {
                        $dirty3 &= -57345;
                    }
                    if ((i & 128) != 0) {
                        $dirty3 &= -29360129;
                    }
                    $composer3 = $composer4;
                    colors3 = colors;
                    enabled4 = enabled2;
                    function5 = function2;
                    steps4 = i2;
                    $dirty = $dirty3;
                } else {
                    if (i3 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        enabled2 = true;
                    }
                    if ((i & 16) == 0) {
                        closedFloatingPointRangeRangeTo = closedFloatingPointRange4;
                        $dirty = $dirty3;
                    } else {
                        closedFloatingPointRangeRangeTo = RangesKt.rangeTo(0.0f, 1.0f);
                        $dirty = $dirty3 & (-57345);
                    }
                    if (i6 == 0) {
                        steps3 = i2;
                    } else {
                        steps3 = 0;
                    }
                    if (i7 == 0) {
                        function4 = function2;
                    } else {
                        function4 = null;
                    }
                    if ((i & 128) != 0) {
                        $composer3 = $composer4;
                        $dirty &= -29360129;
                        steps4 = steps3;
                        enabled4 = enabled2;
                        colors3 = SliderDefaults.INSTANCE.m1918colorsq0g_0yA(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer4, 0, 6, 1023);
                        function5 = function4;
                        closedFloatingPointRange4 = closedFloatingPointRangeRangeTo;
                    } else {
                        $composer3 = $composer4;
                        colors3 = colors;
                        closedFloatingPointRange4 = closedFloatingPointRangeRangeTo;
                        steps4 = steps3;
                        enabled4 = enabled2;
                        function5 = function4;
                    }
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1556183027, $dirty, -1, "androidx.compose.material.RangeSlider (Slider.kt:404)");
                }
                ComposerKt.sourceInformationMarkerStart($composer3, -843255532, "CC(remember):Slider.kt#9igjgp");
                Composer $this$cache$iv = $composer3;
                Object it$iv = $this$cache$iv.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $this$cache$iv.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                final MutableInteractionSource startInteractionSource = (MutableInteractionSource) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, -843252428, "CC(remember):Slider.kt#9igjgp");
                Composer $this$cache$iv2 = $composer3;
                Object it$iv2 = $this$cache$iv2.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = InteractionSourceKt.MutableInteractionSource();
                    $this$cache$iv2.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                final MutableInteractionSource endInteractionSource = (MutableInteractionSource) it$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (!(steps4 >= 0)) {
                    throw new IllegalArgumentException("steps should be >= 0".toString());
                }
                final State onValueChangeState = SnapshotStateKt.rememberUpdatedState(function1, $composer3, ($dirty >> 3) & 14);
                ComposerKt.sourceInformationMarkerStart($composer3, -843246628, "CC(remember):Slider.kt#9igjgp");
                boolean invalid$iv = (458752 & $dirty) == 131072;
                Composer $this$cache$iv3 = $composer3;
                Object it$iv3 = $this$cache$iv3.rememberedValue();
                if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = stepsToTickFractions(steps4);
                    $this$cache$iv3.updateRememberedValue(value$iv3);
                    it$iv3 = value$iv3;
                }
                final List tickFractions = (List) it$iv3;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier2);
                float arg0$iv = ThumbRadius;
                float arg0$iv2 = Dp.m7905constructorimpl(4 * arg0$iv);
                float arg0$iv3 = ThumbRadius;
                Composer $composer5 = $composer3;
                final ClosedFloatingPointRange<Float> closedFloatingPointRange6 = closedFloatingPointRange4;
                final ClosedFloatingPointRange<Float> closedFloatingPointRange7 = closedFloatingPointRange3;
                BoxWithConstraintsKt.BoxWithConstraints(SizeKt.m861requiredSizeInqDBjuR0$default(modifierMinimumInteractiveComponentSize, arg0$iv2, Dp.m7905constructorimpl(2 * arg0$iv3), 0.0f, 0.0f, 12, null), null, false, ComposableLambdaKt.rememberComposableLambda(652589923, true, new Function3() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return SliderKt.RangeSlider$lambda$4(closedFloatingPointRange6, closedFloatingPointRange7, tickFractions, function5, onValueChangeState, startInteractionSource, endInteractionSource, enabled4, steps4, colors3, (BoxWithConstraintsScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer5, 54), $composer5, 3072, 6);
                $composer2 = $composer5;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier2;
                closedFloatingPointRange5 = closedFloatingPointRange6;
                function3 = function5;
                enabled3 = enabled4;
                steps2 = steps4;
                colors2 = colors3;
            } else {
                $composer2 = $composer4;
                $composer2.skipToGroupEnd();
                modifier3 = modifier2;
                enabled3 = enabled2;
                closedFloatingPointRange5 = closedFloatingPointRange4;
                function3 = function2;
                steps2 = i2;
                colors2 = colors;
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderKt.RangeSlider$lambda$5(closedFloatingPointRange, function1, modifier3, enabled3, closedFloatingPointRange5, steps2, function3, colors2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        /* JADX WARN: Code duplicated, block: B:69:0x047a  */
        /* JADX WARN: Code duplicated, block: B:72:0x0485  */
        /* JADX WARN: Code duplicated, block: B:76:0x04f7  */
        /* JADX WARN: Multi-variable type inference failed */
        static final Unit RangeSlider$lambda$4(ClosedFloatingPointRange $valueRange, final ClosedFloatingPointRange $value, final List $tickFractions, final Function0 $onValueChangeFinished, final State $onValueChangeState, MutableInteractionSource $startInteractionSource, MutableInteractionSource $endInteractionSource, boolean $enabled, int $steps, SliderColors $colors, BoxWithConstraintsScope $this$BoxWithConstraints, Composer $composer, int $changed) {
            final MutableFloatState rawOffsetEnd;
            final Ref.FloatRef maxPx;
            final Ref.FloatRef minPx;
            Object value$iv;
            MutableFloatState rawOffsetStart;
            MutableFloatState rawOffsetStart2;
            MutableFloatState rawOffsetStart3;
            final float coercedEnd;
            boolean invalid$iv;
            Object value$iv2;
            boolean invalid$iv2;
            Object value$iv3;
            float coercedEnd2;
            final ClosedFloatingPointRange closedFloatingPointRange = $valueRange;
            ComposerKt.sourceInformation($composer, "C418@18639L7,423@18800L7,434@19228L60,435@19316L67,438@19429L15,437@19393L165,445@19603L15,444@19567L170,452@19759L24,454@19867L1029,454@19827L1069,480@20978L985,480@20931L1032,525@23062L63,534@23393L65,540@23603L341:Slider.kt#jmzs0o");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer.changed($this$BoxWithConstraints) ? 4 : 2;
            }
            int $dirty2 = $dirty;
            if ($composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(652589923, $dirty2, -1, "androidx.compose.material.RangeSlider.<anonymous> (Slider.kt:418)");
                }
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer);
                boolean isRtl = objConsume == LayoutDirection.Rtl;
                float widthPx = Constraints.m7858getMaxWidthimpl($this$BoxWithConstraints.mo713getConstraintsmsEJaDk());
                Ref.FloatRef maxPx2 = new Ref.FloatRef();
                Ref.FloatRef minPx2 = new Ref.FloatRef();
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Density $this$RangeSlider_u24lambda_u244_u240 = (Density) objConsume2;
                maxPx2.element = widthPx - $this$RangeSlider_u24lambda_u244_u240.mo406toPx0680j_4(ThumbRadius);
                minPx2.element = $this$RangeSlider_u24lambda_u244_u240.mo406toPx0680j_4(ThumbRadius);
                ComposerKt.sourceInformationMarkerStart($composer, 73711071, "CC(remember):Slider.kt#9igjgp");
                Object it$iv = $composer.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv4 = PrimitiveSnapshotStateKt.mutableFloatStateOf(RangeSlider$lambda$4$scaleToOffset(closedFloatingPointRange, minPx2, maxPx2, ((Number) $value.getStart()).floatValue()));
                    $composer.updateRememberedValue(value$iv4);
                    it$iv = value$iv4;
                }
                final MutableFloatState rawOffsetStart4 = (MutableFloatState) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, 73713894, "CC(remember):Slider.kt#9igjgp");
                Object it$iv2 = $composer.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv5 = PrimitiveSnapshotStateKt.mutableFloatStateOf(RangeSlider$lambda$4$scaleToOffset(closedFloatingPointRange, minPx2, maxPx2, ((Number) $value.getEndInclusive()).floatValue()));
                    $composer.updateRememberedValue(value$iv5);
                    it$iv2 = value$iv5;
                }
                MutableFloatState rawOffsetEnd2 = (MutableFloatState) it$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, 73717458, "CC(remember):Slider.kt#9igjgp");
                boolean invalid$iv3 = $composer.changed(closedFloatingPointRange) | $composer.changed(minPx2.element) | $composer.changed(maxPx2.element);
                SliderKt$RangeSlider$2$2$1 value$iv6 = $composer.rememberedValue();
                if (invalid$iv3 || value$iv6 == Composer.INSTANCE.getEmpty()) {
                    value$iv6 = new SliderKt$RangeSlider$2$2$1(closedFloatingPointRange, minPx2, maxPx2);
                    $composer.updateRememberedValue(value$iv6);
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                CorrectValueSideEffect((Function1) ((KFunction) value$iv6), closedFloatingPointRange, RangesKt.rangeTo(minPx2.element, maxPx2.element), rawOffsetStart4, ((Number) $value.getStart()).floatValue(), $composer, 3072);
                ComposerKt.sourceInformationMarkerStart($composer, 73723026, "CC(remember):Slider.kt#9igjgp");
                boolean invalid$iv4 = $composer.changed(closedFloatingPointRange) | $composer.changed(minPx2.element) | $composer.changed(maxPx2.element);
                Object it$iv3 = $composer.rememberedValue();
                if (invalid$iv4 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv7 = (KFunction) new SliderKt$RangeSlider$2$3$1(closedFloatingPointRange, minPx2, maxPx2);
                    $composer.updateRememberedValue(value$iv7);
                    it$iv3 = value$iv7;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                CorrectValueSideEffect((Function1) ((KFunction) it$iv3), closedFloatingPointRange, RangesKt.rangeTo(minPx2.element, maxPx2.element), rawOffsetEnd2, ((Number) $value.getEndInclusive()).floatValue(), $composer, 3072);
                ComposerKt.sourceInformationMarkerStart($composer, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart($composer, 683736516, "CC(remember):Effects.kt#9igjgp");
                Object value$iv$iv = $composer.rememberedValue();
                if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer);
                    $composer.updateRememberedValue(value$iv$iv);
                }
                final CoroutineScope scope = (CoroutineScope) value$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, 73732488, "CC(remember):Slider.kt#9igjgp");
                boolean invalid$iv5 = $composer.changedInstance($tickFractions) | $composer.changed(minPx2.element) | $composer.changed(maxPx2.element) | $composer.changed($onValueChangeFinished) | $composer.changedInstance(scope) | $composer.changed($onValueChangeState) | $composer.changed(closedFloatingPointRange);
                Object it$iv4 = $composer.rememberedValue();
                if (invalid$iv5 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                    rawOffsetEnd = rawOffsetEnd2;
                    maxPx = maxPx2;
                    minPx = minPx2;
                    value$iv = new Function1() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SliderKt.RangeSlider$lambda$4$5$0(rawOffsetStart4, rawOffsetEnd, $tickFractions, minPx, maxPx, $onValueChangeFinished, scope, $onValueChangeState, closedFloatingPointRange, ((Boolean) obj).booleanValue());
                        }
                    };
                    rawOffsetStart = rawOffsetStart4;
                    closedFloatingPointRange = closedFloatingPointRange;
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv4;
                    maxPx = maxPx2;
                    minPx = minPx2;
                    rawOffsetEnd = rawOffsetEnd2;
                    rawOffsetStart = rawOffsetStart4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                State gestureEndAction = SnapshotStateKt.rememberUpdatedState((Function1) value$iv, $composer, 0);
                ComposerKt.sourceInformationMarkerStart($composer, 73767996, r7);
                boolean invalid$iv6 = $composer.changed(closedFloatingPointRange) | $composer.changed(minPx.element) | $composer.changed(maxPx.element) | $composer.changed($value) | $composer.changed($onValueChangeState);
                Object value$iv8 = $composer.rememberedValue();
                if (invalid$iv6) {
                    rawOffsetStart2 = rawOffsetStart;
                } else {
                    rawOffsetStart2 = rawOffsetStart;
                    if (value$iv8 != Composer.INSTANCE.getEmpty()) {
                        rawOffsetStart3 = rawOffsetStart2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    State onDrag = SnapshotStateKt.rememberUpdatedState((Function2) value$iv8, $composer, 0);
                    Ref.FloatRef minPx3 = minPx;
                    Ref.FloatRef maxPx3 = maxPx;
                    Modifier pressDrag = rangeSliderPressDragModifier(Modifier.INSTANCE, $startInteractionSource, $endInteractionSource, rawOffsetStart3, rawOffsetEnd, $enabled, isRtl, widthPx, $valueRange, gestureEndAction, onDrag);
                    final float coercedStart = RangesKt.coerceIn(((Number) $value.getStart()).floatValue(), ((Number) $valueRange.getStart()).floatValue(), ((Number) $value.getEndInclusive()).floatValue());
                    coercedEnd = RangesKt.coerceIn(((Number) $value.getEndInclusive()).floatValue(), ((Number) $value.getStart()).floatValue(), ((Number) $valueRange.getEndInclusive()).floatValue());
                    float fractionStart = calcFraction(((Number) $valueRange.getStart()).floatValue(), ((Number) $valueRange.getEndInclusive()).floatValue(), coercedStart);
                    float fractionEnd = calcFraction(((Number) $valueRange.getStart()).floatValue(), ((Number) $valueRange.getEndInclusive()).floatValue(), coercedEnd);
                    int startSteps = (int) Math.floor($steps * fractionEnd);
                    int endSteps = (int) Math.floor($steps * (1.0f - fractionStart));
                    Modifier.Companion companion = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, 73833762, r7);
                    invalid$iv = $composer.changed($onValueChangeState) | $composer.changed(coercedEnd);
                    value$iv2 = $composer.rememberedValue();
                    if (!invalid$iv || value$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = new Function1() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda15
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return SliderKt.RangeSlider$lambda$4$7$0($onValueChangeState, coercedEnd, ((Float) obj).floatValue());
                            }
                        };
                        $composer.updateRememberedValue(value$iv2);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier startThumbSemantics = sliderSemantics(companion, coercedStart, $enabled, (Function1) value$iv2, $onValueChangeFinished, RangesKt.rangeTo(((Number) $valueRange.getStart()).floatValue(), coercedEnd), startSteps);
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, 73844356, "CC(remember):Slider.kt#9igjgp");
                    invalid$iv2 = $composer.changed($onValueChangeState) | $composer.changed(coercedStart);
                    value$iv3 = $composer.rememberedValue();
                    if (invalid$iv2) {
                        coercedEnd2 = coercedEnd;
                    } else {
                        coercedEnd2 = coercedEnd;
                        if (value$iv3 == Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        Modifier endThumbSemantics = sliderSemantics(companion2, coercedEnd2, $enabled, (Function1) value$iv3, $onValueChangeFinished, RangesKt.rangeTo(coercedStart, ((Number) $valueRange.getEndInclusive()).floatValue()), endSteps);
                        RangeSliderImpl($enabled, fractionStart, fractionEnd, $tickFractions, $colors, maxPx3.element - minPx3.element, $startInteractionSource, $endInteractionSource, pressDrag, startThumbSemantics, endThumbSemantics, $composer, 14155776, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    value$iv3 = new Function1() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda16
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SliderKt.RangeSlider$lambda$4$8$0($onValueChangeState, coercedStart, ((Float) obj).floatValue());
                        }
                    };
                    $composer.updateRememberedValue(value$iv3);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier endThumbSemantics2 = sliderSemantics(companion2, coercedEnd2, $enabled, (Function1) value$iv3, $onValueChangeFinished, RangesKt.rangeTo(coercedStart, ((Number) $valueRange.getEndInclusive()).floatValue()), endSteps);
                    RangeSliderImpl($enabled, fractionStart, fractionEnd, $tickFractions, $colors, maxPx3.element - minPx3.element, $startInteractionSource, $endInteractionSource, pressDrag, startThumbSemantics, endThumbSemantics2, $composer, 14155776, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                final ClosedFloatingPointRange closedFloatingPointRange2 = closedFloatingPointRange;
                final MutableFloatState rawOffsetStart5 = rawOffsetStart2;
                rawOffsetStart3 = rawOffsetStart5;
                value$iv8 = new Function2() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderKt.RangeSlider$lambda$4$6$0(rawOffsetStart5, rawOffsetEnd, $value, minPx, maxPx, $onValueChangeState, closedFloatingPointRange2, ((Boolean) obj).booleanValue(), ((Float) obj2).floatValue());
                    }
                };
                $composer.updateRememberedValue(value$iv8);
                ComposerKt.sourceInformationMarkerEnd($composer);
                State onDrag2 = SnapshotStateKt.rememberUpdatedState((Function2) value$iv8, $composer, 0);
                Ref.FloatRef minPx4 = minPx;
                Ref.FloatRef maxPx4 = maxPx;
                Modifier pressDrag2 = rangeSliderPressDragModifier(Modifier.INSTANCE, $startInteractionSource, $endInteractionSource, rawOffsetStart3, rawOffsetEnd, $enabled, isRtl, widthPx, $valueRange, gestureEndAction, onDrag2);
                final float coercedStart2 = RangesKt.coerceIn(((Number) $value.getStart()).floatValue(), ((Number) $valueRange.getStart()).floatValue(), ((Number) $value.getEndInclusive()).floatValue());
                coercedEnd = RangesKt.coerceIn(((Number) $value.getEndInclusive()).floatValue(), ((Number) $value.getStart()).floatValue(), ((Number) $valueRange.getEndInclusive()).floatValue());
                float fractionStart2 = calcFraction(((Number) $valueRange.getStart()).floatValue(), ((Number) $valueRange.getEndInclusive()).floatValue(), coercedStart2);
                float fractionEnd2 = calcFraction(((Number) $valueRange.getStart()).floatValue(), ((Number) $valueRange.getEndInclusive()).floatValue(), coercedEnd);
                int startSteps2 = (int) Math.floor($steps * fractionEnd2);
                int endSteps2 = (int) Math.floor($steps * (1.0f - fractionStart2));
                Modifier.Companion companion3 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 73833762, r7);
                invalid$iv = $composer.changed($onValueChangeState) | $composer.changed(coercedEnd);
                value$iv2 = $composer.rememberedValue();
                if (!invalid$iv) {
                }
                value$iv2 = new Function1() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SliderKt.RangeSlider$lambda$4$7$0($onValueChangeState, coercedEnd, ((Float) obj).floatValue());
                    }
                };
                $composer.updateRememberedValue(value$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier startThumbSemantics2 = sliderSemantics(companion3, coercedStart2, $enabled, (Function1) value$iv2, $onValueChangeFinished, RangesKt.rangeTo(((Number) $valueRange.getStart()).floatValue(), coercedEnd), startSteps2);
                Modifier.Companion companion4 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 73844356, "CC(remember):Slider.kt#9igjgp");
                invalid$iv2 = $composer.changed($onValueChangeState) | $composer.changed(coercedStart2);
                value$iv3 = $composer.rememberedValue();
                if (invalid$iv2) {
                    coercedEnd2 = coercedEnd;
                    if (value$iv3 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier endThumbSemantics3 = sliderSemantics(companion4, coercedEnd2, $enabled, (Function1) value$iv3, $onValueChangeFinished, RangesKt.rangeTo(coercedStart2, ((Number) $valueRange.getEndInclusive()).floatValue()), endSteps2);
                    RangeSliderImpl($enabled, fractionStart2, fractionEnd2, $tickFractions, $colors, maxPx4.element - minPx4.element, $startInteractionSource, $endInteractionSource, pressDrag2, startThumbSemantics2, endThumbSemantics3, $composer, 14155776, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    coercedEnd2 = coercedEnd;
                }
                value$iv3 = new Function1() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SliderKt.RangeSlider$lambda$4$8$0($onValueChangeState, coercedStart2, ((Float) obj).floatValue());
                    }
                };
                $composer.updateRememberedValue(value$iv3);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier endThumbSemantics4 = sliderSemantics(companion4, coercedEnd2, $enabled, (Function1) value$iv3, $onValueChangeFinished, RangesKt.rangeTo(coercedStart2, ((Number) $valueRange.getEndInclusive()).floatValue()), endSteps2);
                RangeSliderImpl($enabled, fractionStart2, fractionEnd2, $tickFractions, $colors, maxPx4.element - minPx4.element, $startInteractionSource, $endInteractionSource, pressDrag2, startThumbSemantics2, endThumbSemantics4, $composer, 14155776, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ClosedFloatingPointRange<Float> RangeSlider$lambda$4$scaleToUserValue(Ref.FloatRef minPx, Ref.FloatRef maxPx, ClosedFloatingPointRange<Float> closedFloatingPointRange, ClosedFloatingPointRange<Float> closedFloatingPointRange2) {
            return scale(minPx.element, maxPx.element, closedFloatingPointRange2, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float RangeSlider$lambda$4$scaleToOffset(ClosedFloatingPointRange<Float> closedFloatingPointRange, Ref.FloatRef minPx, Ref.FloatRef maxPx, float userValue) {
            return scale(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), userValue, minPx.element, maxPx.element);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit RangeSlider$lambda$4$5$0(MutableFloatState $rawOffsetStart, MutableFloatState $rawOffsetEnd, List $tickFractions, Ref.FloatRef $minPx, Ref.FloatRef $maxPx, Function0 $onValueChangeFinished, CoroutineScope $scope, State $onValueChangeState, ClosedFloatingPointRange $valueRange, boolean isStart) {
            float current = (isStart ? $rawOffsetStart : $rawOffsetEnd).getFloatValue();
            float target = snapValueToTick(current, $tickFractions, $minPx.element, $maxPx.element);
            if (!(current == target)) {
                BuildersKt__Builders_commonKt.launch$default($scope, null, null, new SliderKt$RangeSlider$2$gestureEndAction$1$1$1(current, target, $onValueChangeFinished, isStart, $rawOffsetStart, $rawOffsetEnd, $onValueChangeState, $minPx, $maxPx, $valueRange, null), 3, null);
                return Unit.INSTANCE;
            }
            if ($onValueChangeFinished != null) {
                $onValueChangeFinished.invoke();
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final Unit RangeSlider$lambda$4$6$0(MutableFloatState $rawOffsetStart, MutableFloatState $rawOffsetEnd, ClosedFloatingPointRange $value, Ref.FloatRef $minPx, Ref.FloatRef $maxPx, State $onValueChangeState, ClosedFloatingPointRange $valueRange, boolean isStart, float offset) {
            ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo;
            if (isStart) {
                $rawOffsetStart.setFloatValue($rawOffsetStart.getFloatValue() + offset);
                $rawOffsetEnd.setFloatValue(RangeSlider$lambda$4$scaleToOffset($valueRange, $minPx, $maxPx, ((Number) $value.getEndInclusive()).floatValue()));
                float offsetEnd = $rawOffsetEnd.getFloatValue();
                closedFloatingPointRangeRangeTo = RangesKt.rangeTo(RangesKt.coerceIn($rawOffsetStart.getFloatValue(), $minPx.element, offsetEnd), offsetEnd);
            } else {
                $rawOffsetEnd.setFloatValue($rawOffsetEnd.getFloatValue() + offset);
                $rawOffsetStart.setFloatValue(RangeSlider$lambda$4$scaleToOffset($valueRange, $minPx, $maxPx, ((Number) $value.getStart()).floatValue()));
                float offsetStart = $rawOffsetStart.getFloatValue();
                closedFloatingPointRangeRangeTo = RangesKt.rangeTo(offsetStart, RangesKt.coerceIn($rawOffsetEnd.getFloatValue(), offsetStart, $maxPx.element));
            }
            ((Function1) $onValueChangeState.getValue()).invoke(RangeSlider$lambda$4$scaleToUserValue($minPx, $maxPx, $valueRange, closedFloatingPointRangeRangeTo));
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit RangeSlider$lambda$4$7$0(State $onValueChangeState, float $coercedEnd, float value) {
            ((Function1) $onValueChangeState.getValue()).invoke(RangesKt.rangeTo(value, $coercedEnd));
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit RangeSlider$lambda$4$8$0(State $onValueChangeState, float $coercedStart, float value) {
            ((Function1) $onValueChangeState.getValue()).invoke(RangesKt.rangeTo($coercedStart, value));
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:81:0x02c1  */
        private static final void SliderImpl(final boolean enabled, final float positionFraction, final List<Float> list, final SliderColors colors, final float width, final MutableInteractionSource interactionSource, final Modifier modifier, Composer $composer, final int $changed) {
            boolean z;
            float f;
            List<Float> list2;
            SliderColors sliderColors;
            Function0<ComposeUiNode> function0;
            Composer $composer$iv;
            Composer $composer2 = $composer.startRestartGroup(1679682785);
            ComposerKt.sourceInformation($composer2, "C(SliderImpl)N(enabled,positionFraction,tickFractions,colors,width,interactionSource,modifier)684@29780L713:Slider.kt#jmzs0o");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                z = enabled;
                $dirty |= $composer2.changed(z) ? 4 : 2;
            } else {
                z = enabled;
            }
            if (($changed & 48) == 0) {
                f = positionFraction;
                $dirty |= $composer2.changed(f) ? 32 : 16;
            } else {
                f = positionFraction;
            }
            if (($changed & 384) == 0) {
                list2 = list;
                $dirty |= $composer2.changedInstance(list2) ? 256 : 128;
            } else {
                list2 = list;
            }
            if (($changed & 3072) == 0) {
                sliderColors = colors;
                $dirty |= $composer2.changed(sliderColors) ? 2048 : 1024;
            } else {
                sliderColors = colors;
            }
            if (($changed & 24576) == 0) {
                $dirty |= $composer2.changed(width) ? 16384 : 8192;
            }
            if ((196608 & $changed) == 0) {
                $dirty |= $composer2.changed(interactionSource) ? 131072 : 65536;
            }
            if ((1572864 & $changed) == 0) {
                $dirty |= $composer2.changed(modifier) ? 1048576 : 524288;
            }
            if ($composer2.shouldExecute((599187 & $dirty) != 599186, $dirty & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1679682785, $dirty, -1, "androidx.compose.material.SliderImpl (Slider.kt:683)");
                }
                Modifier modifier$iv = modifier.then(DefaultSliderConstraints);
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv = (0 << 3) & 112;
                int $dirty2 = $dirty;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv = $changed$iv$iv << 6;
                int $changed$iv$iv$iv = ($changed$iv & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function0 = constructor;
                    $composer2.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer2.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    $composer$iv = $composer2;
                } else {
                    $composer$iv = $composer2;
                    if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i = ($changed$iv$iv$iv >> 6) & 14;
                    Composer $composer$iv2 = $composer$iv;
                    ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScope $this$SliderImpl_u24lambda_u240 = BoxScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer$iv2, 2014920892, "C688@29940L7,697@30185L217,707@30411L76:Slider.kt#jmzs0o");
                    ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart($composer$iv2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer$iv2.consume(localDensity);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    Density $this$SliderImpl_u24lambda_u240_u240 = (Density) objConsume;
                    float trackStrokeWidth = $this$SliderImpl_u24lambda_u240_u240.mo406toPx0680j_4(TrackHeight);
                    float thumbPx = $this$SliderImpl_u24lambda_u240_u240.mo406toPx0680j_4(ThumbRadius);
                    float widthDp = $this$SliderImpl_u24lambda_u240_u240.mo402toDpu2uoSUM(width);
                    float arg0$iv = ThumbRadius;
                    float arg0$iv2 = 2;
                    float thumbSize = Dp.m7905constructorimpl(arg0$iv2 * arg0$iv);
                    float other$iv = Dp.m7905constructorimpl(widthDp * positionFraction);
                    Track(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), sliderColors, z, 0.0f, f, list2, thumbPx, trackStrokeWidth, $composer$iv2, (($dirty2 >> 6) & 112) | 3078 | (($dirty2 << 6) & 896) | (($dirty2 << 9) & 57344) | (($dirty2 << 9) & 458752));
                    m1927SliderThumbPcYyNuk($this$SliderImpl_u24lambda_u240, Modifier.INSTANCE, other$iv, interactionSource, colors, enabled, thumbSize, $composer$iv2, ((((0 >> 6) & 112) | 6) & 14) | 1572912 | (($dirty2 >> 6) & 7168) | (($dirty2 << 3) & 57344) | (($dirty2 << 15) & 458752));
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    $composer$iv.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = ($changed$iv$iv$iv >> 6) & 14;
                Composer $composer$iv3 = $composer$iv;
                ComposerKt.sourceInformationMarkerStart($composer$iv3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScope $this$SliderImpl_u24lambda_u241 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer$iv3, 2014920892, "C688@29940L7,697@30185L217,707@30411L76:Slider.kt#jmzs0o");
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer$iv3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer$iv3.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                Density $this$SliderImpl_u24lambda_u240_u241 = (Density) objConsume2;
                float trackStrokeWidth2 = $this$SliderImpl_u24lambda_u240_u241.mo406toPx0680j_4(TrackHeight);
                float thumbPx2 = $this$SliderImpl_u24lambda_u240_u241.mo406toPx0680j_4(ThumbRadius);
                float widthDp2 = $this$SliderImpl_u24lambda_u240_u241.mo402toDpu2uoSUM(width);
                float arg0$iv3 = ThumbRadius;
                float arg0$iv4 = 2;
                float thumbSize2 = Dp.m7905constructorimpl(arg0$iv4 * arg0$iv3);
                float other$iv2 = Dp.m7905constructorimpl(widthDp2 * positionFraction);
                Track(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), sliderColors, z, 0.0f, f, list2, thumbPx2, trackStrokeWidth2, $composer$iv3, (($dirty2 >> 6) & 112) | 3078 | (($dirty2 << 6) & 896) | (($dirty2 << 9) & 57344) | (($dirty2 << 9) & 458752));
                m1927SliderThumbPcYyNuk($this$SliderImpl_u24lambda_u241, Modifier.INSTANCE, other$iv2, interactionSource, colors, enabled, thumbSize2, $composer$iv3, ((((0 >> 6) & 112) | 6) & 14) | 1572912 | (($dirty2 >> 6) & 7168) | (($dirty2 << 3) & 57344) | (($dirty2 << 15) & 458752));
                ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda21
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderKt.SliderImpl$lambda$1(enabled, positionFraction, list, colors, width, interactionSource, modifier, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        /* JADX WARN: Code duplicated, block: B:121:0x038e  */
        /* JADX WARN: Code duplicated, block: B:124:0x03ee  */
        private static final void RangeSliderImpl(final boolean enabled, final float positionFractionStart, final float positionFractionEnd, final List<Float> list, final SliderColors colors, final float width, final MutableInteractionSource startInteractionSource, final MutableInteractionSource endInteractionSource, final Modifier modifier, final Modifier startThumbSemantics, Modifier endThumbSemantics, Composer $composer, final int $changed, final int $changed1) {
            boolean z;
            float f;
            float f2;
            List<Float> list2;
            Modifier modifier2;
            Composer $composer2;
            Function0<ComposeUiNode> function0;
            int i;
            boolean invalid$iv;
            Object it$iv;
            Composer $composer3 = $composer.startRestartGroup(-278895713);
            ComposerKt.sourceInformation($composer3, "C(RangeSliderImpl)N(enabled,positionFractionStart,positionFractionEnd,tickFractions,colors,width,startInteractionSource,endInteractionSource,modifier,startThumbSemantics,endThumbSemantics)726@30939L35,727@31007L33,728@31045L1529:Slider.kt#jmzs0o");
            int $dirty = $changed;
            int $dirty1 = $changed1;
            if (($changed & 6) == 0) {
                z = enabled;
                $dirty |= $composer3.changed(z) ? 4 : 2;
            } else {
                z = enabled;
            }
            if (($changed & 48) == 0) {
                f = positionFractionStart;
                $dirty |= $composer3.changed(f) ? 32 : 16;
            } else {
                f = positionFractionStart;
            }
            if (($changed & 384) == 0) {
                f2 = positionFractionEnd;
                $dirty |= $composer3.changed(f2) ? 256 : 128;
            } else {
                f2 = positionFractionEnd;
            }
            if (($changed & 3072) == 0) {
                list2 = list;
                $dirty |= $composer3.changedInstance(list2) ? 2048 : 1024;
            } else {
                list2 = list;
            }
            if (($changed & 24576) == 0) {
                $dirty |= $composer3.changed(colors) ? 16384 : 8192;
            }
            if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                $dirty |= $composer3.changed(width) ? 131072 : 65536;
            }
            if (($changed & 1572864) == 0) {
                $dirty |= $composer3.changed(startInteractionSource) ? 1048576 : 524288;
            }
            if (($changed & 12582912) == 0) {
                $dirty |= $composer3.changed(endInteractionSource) ? 8388608 : 4194304;
            }
            if (($changed & 100663296) == 0) {
                $dirty |= $composer3.changed(modifier) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
            }
            if (($changed & C.ENCODING_PCM_32BIT) == 0) {
                $dirty |= $composer3.changed(startThumbSemantics) ? 536870912 : 268435456;
            }
            if (($changed1 & 6) == 0) {
                $dirty1 |= $composer3.changed(endThumbSemantics) ? 4 : 2;
            }
            if (!$composer3.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty1 & 3) == 2) ? false : true, $dirty & 1)) {
                modifier2 = endThumbSemantics;
                $composer2 = $composer3;
                $composer2.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-278895713, $dirty, $dirty1, "androidx.compose.material.RangeSliderImpl (Slider.kt:724)");
                }
                final String startContentDescription = Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1951getSliderRangeStartUdPEhr4(), $composer3, 6);
                final String endContentDescription = Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1950getSliderRangeEndUdPEhr4(), $composer3, 6);
                Modifier modifier$iv = modifier.then(DefaultSliderConstraints);
                $composer2 = $composer3;
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                int $dirty2 = $dirty;
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
                Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                int $changed2 = ((0 >> 6) & 112) | 6;
                BoxScope $this$RangeSliderImpl_u24lambda_u240 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 2114792334, "C732@31205L7,741@31513L268,753@31860L84,752@31791L389,765@32258L82,764@32189L379:Slider.kt#jmzs0o");
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer3.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density $this$RangeSliderImpl_u24lambda_u240_u240 = (Density) objConsume;
                float trackStrokeWidth = $this$RangeSliderImpl_u24lambda_u240_u240.mo406toPx0680j_4(TrackHeight);
                float thumbPx = $this$RangeSliderImpl_u24lambda_u240_u240.mo406toPx0680j_4(ThumbRadius);
                float widthDp = $this$RangeSliderImpl_u24lambda_u240_u240.mo402toDpu2uoSUM(width);
                float arg0$iv = ThumbRadius;
                float arg0$iv2 = 2;
                float thumbSize = Dp.m7905constructorimpl(arg0$iv2 * arg0$iv);
                float arg0$iv3 = Dp.m7905constructorimpl(widthDp * positionFractionStart);
                float other$iv = Dp.m7905constructorimpl(widthDp * positionFractionEnd);
                Track(SizeKt.fillMaxSize$default($this$RangeSliderImpl_u24lambda_u240.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterStart()), 0.0f, 1, null), colors, z, f, f2, list2, thumbPx, trackStrokeWidth, $composer3, (($dirty2 >> 9) & 112) | (($dirty2 << 6) & 896) | (($dirty2 << 6) & 7168) | (($dirty2 << 6) & 57344) | (($dirty2 << 6) & 458752));
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 1730810029, "CC(remember):Slider.kt#9igjgp");
                boolean invalid$iv2 = $composer3.changed(startContentDescription);
                Object it$iv2 = $composer3.rememberedValue();
                if (!invalid$iv2) {
                    i = 57344;
                    if (it$iv2 != Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    m1927SliderThumbPcYyNuk($this$RangeSliderImpl_u24lambda_u240, FocusableKt.focusable(SemanticsModifierKt.semantics(companion, true, (Function1) it$iv2), true, startInteractionSource).then(startThumbSemantics), arg0$iv3, startInteractionSource, colors, enabled, thumbSize, $composer3, ($changed2 & 14) | 1572864 | (($dirty2 >> 9) & 7168) | ($dirty2 & i) | (($dirty2 << 15) & 458752));
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1730822763, "CC(remember):Slider.kt#9igjgp");
                    invalid$iv = $composer3.changed(endContentDescription);
                    it$iv = $composer3.rememberedValue();
                    if (!invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = new Function1() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda6
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return SliderKt.RangeSliderImpl$lambda$0$2$0(endContentDescription, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierFocusable = FocusableKt.focusable(SemanticsModifierKt.semantics(companion2, true, (Function1) it$iv), true, endInteractionSource);
                    modifier2 = endThumbSemantics;
                    m1927SliderThumbPcYyNuk($this$RangeSliderImpl_u24lambda_u240, modifierFocusable.then(modifier2), other$iv, endInteractionSource, colors, enabled, thumbSize, $composer3, ($changed2 & 14) | 1572864 | (($dirty2 >> 12) & 7168) | ($dirty2 & i) | (($dirty2 << 15) & 458752));
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    i = 57344;
                }
                Object value$iv2 = new Function1() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SliderKt.RangeSliderImpl$lambda$0$1$0(startContentDescription, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                m1927SliderThumbPcYyNuk($this$RangeSliderImpl_u24lambda_u240, FocusableKt.focusable(SemanticsModifierKt.semantics(companion, true, (Function1) it$iv2), true, startInteractionSource).then(startThumbSemantics), arg0$iv3, startInteractionSource, colors, enabled, thumbSize, $composer3, ($changed2 & 14) | 1572864 | (($dirty2 >> 9) & 7168) | ($dirty2 & i) | (($dirty2 << 15) & 458752));
                Modifier.Companion companion3 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 1730822763, "CC(remember):Slider.kt#9igjgp");
                invalid$iv = $composer3.changed(endContentDescription);
                it$iv = $composer3.rememberedValue();
                if (!invalid$iv) {
                    Object value$iv3 = new Function1() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SliderKt.RangeSliderImpl$lambda$0$2$0(endContentDescription, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv3);
                    it$iv = value$iv3;
                } else {
                    Object value$iv4 = new Function1() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SliderKt.RangeSliderImpl$lambda$0$2$0(endContentDescription, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv4);
                    it$iv = value$iv4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierFocusable2 = FocusableKt.focusable(SemanticsModifierKt.semantics(companion3, true, (Function1) it$iv), true, endInteractionSource);
                modifier2 = endThumbSemantics;
                m1927SliderThumbPcYyNuk($this$RangeSliderImpl_u24lambda_u240, modifierFocusable2.then(modifier2), other$iv, endInteractionSource, colors, enabled, thumbSize, $composer3, ($changed2 & 14) | 1572864 | (($dirty2 >> 12) & 7168) | ($dirty2 & i) | (($dirty2 << 15) & 458752));
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
                final Modifier modifier3 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderKt.RangeSliderImpl$lambda$1(enabled, positionFractionStart, positionFractionEnd, list, colors, width, startInteractionSource, endInteractionSource, modifier, startThumbSemantics, modifier3, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit RangeSliderImpl$lambda$0$1$0(String $startContentDescription, SemanticsPropertyReceiver $this$semantics) {
            SemanticsPropertiesKt.setContentDescription($this$semantics, $startContentDescription);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit RangeSliderImpl$lambda$0$2$0(String $endContentDescription, SemanticsPropertyReceiver $this$semantics) {
            SemanticsPropertiesKt.setContentDescription($this$semantics, $endContentDescription);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: SliderThumb-PcYyNuk, reason: not valid java name */
        private static final void m1927SliderThumbPcYyNuk(final BoxScope $this$SliderThumb_u2dPcYyNuk, final Modifier modifier, final float f, final MutableInteractionSource interactionSource, final SliderColors colors, final boolean enabled, final float f2, Composer $composer, final int $changed) {
            float f3;
            Composer $composer2;
            Function0<ComposeUiNode> function0;
            Object it$iv;
            Composer $composer3 = $composer.startRestartGroup(428907178);
            ComposerKt.sourceInformation($composer3, "C(SliderThumb)N(modifier,offset:c#ui.unit.Dp,interactionSource,colors,enabled,thumbSize:c#ui.unit.Dp)788@32788L1539:Slider.kt#jmzs0o");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer3.changed($this$SliderThumb_u2dPcYyNuk) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer3.changed(modifier) ? 32 : 16;
            }
            if (($changed & 384) == 0) {
                f3 = f;
                $dirty |= $composer3.changed(f3) ? 256 : 128;
            } else {
                f3 = f;
            }
            if (($changed & 3072) == 0) {
                $dirty |= $composer3.changed(interactionSource) ? 2048 : 1024;
            }
            if (($changed & 24576) == 0) {
                $dirty |= $composer3.changed(colors) ? 16384 : 8192;
            }
            if ((196608 & $changed) == 0) {
                $dirty |= $composer3.changed(enabled) ? 131072 : 65536;
            }
            if ((1572864 & $changed) == 0) {
                $dirty |= $composer3.changed(f2) ? 1048576 : 524288;
            }
            int $dirty2 = $dirty;
            if ($composer3.shouldExecute((599187 & $dirty2) != 599186, $dirty2 & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(428907178, $dirty2, -1, "androidx.compose.material.SliderThumb (Slider.kt:787)");
                }
                Modifier modifier$iv = $this$SliderThumb_u2dPcYyNuk.align(PaddingKt.m823paddingqDBjuR0$default(Modifier.INSTANCE, f3, 0.0f, 0.0f, 0.0f, 14, null), Alignment.INSTANCE.getCenterStart());
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                $composer2 = $composer3;
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
                Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i2 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -264746013, "C789@32884L46,790@32973L658,790@32939L692,818@34272L19,809@33821L500:Slider.kt#jmzs0o");
                ComposerKt.sourceInformationMarkerStart($composer3, -2086750978, "CC(remember):Slider.kt#9igjgp");
                Object it$iv2 = $composer3.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = SnapshotStateKt.mutableStateListOf();
                    $composer3.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                } else {
                    it$iv = it$iv2;
                }
                SnapshotStateList interactions = (SnapshotStateList) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, -2086747518, "CC(remember):Slider.kt#9igjgp");
                boolean invalid$iv = ($dirty2 & 7168) == 2048;
                SliderKt$SliderThumb$1$1$1 value$iv2 = $composer3.rememberedValue();
                if (invalid$iv || value$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = new SliderKt$SliderThumb$1$1$1(interactionSource, interactions, null);
                    $composer3.updateRememberedValue(value$iv2);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.LaunchedEffect(interactionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2, $composer3, ($dirty2 >> 9) & 14);
                float elevation = !interactions.isEmpty() ? ThumbPressedElevation : ThumbDefaultElevation;
                SpacerKt.Spacer(BackgroundKt.m264backgroundbw27NRU(ShadowKt.m4695shadows4CzXII$default(HoverableKt.hoverable$default(IndicationKt.indication(SizeKt.m867sizeVpY3zN4(modifier, f2, f2), interactionSource, RippleKt.m1907rippleH2RKhps$default(false, ThumbRippleRadius, 0L, 4, null)), interactionSource, false, 2, null), enabled ? elevation : Dp.m7905constructorimpl(0), RoundedCornerShapeKt.getCircleShape(), false, 0L, 0L, 24, null), colors.thumbColor(enabled, $composer3, (($dirty2 >> 15) & 14) | (($dirty2 >> 9) & 112)).getValue().m5107unboximpl(), RoundedCornerShapeKt.getCircleShape()), $composer3, 0);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2 = $composer3;
                $composer2.skipToGroupEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderKt.SliderThumb_PcYyNuk$lambda$1($this$SliderThumb_u2dPcYyNuk, modifier, f, interactionSource, colors, enabled, f2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        private static final void Track(final Modifier modifier, final SliderColors colors, final boolean enabled, final float positionFractionStart, final float positionFractionEnd, final List<Float> list, final float thumbPx, final float trackStrokeWidth, Composer $composer, final int $changed) {
            final float f;
            float f2;
            float f3;
            float f4;
            Composer $composer2 = $composer.startRestartGroup(1833126050);
            ComposerKt.sourceInformation($composer2, "C(Track)N(modifier,colors,enabled,positionFractionStart,positionFractionEnd,tickFractions,thumbPx,trackStrokeWidth)834@34621L35,835@34691L34,836@34761L34,837@34829L33,838@34884L1463,838@34867L1480:Slider.kt#jmzs0o");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer2.changed(modifier) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer2.changed(colors) ? 32 : 16;
            }
            if (($changed & 384) == 0) {
                $dirty |= $composer2.changed(enabled) ? 256 : 128;
            }
            if (($changed & 3072) == 0) {
                f = positionFractionStart;
                $dirty |= $composer2.changed(f) ? 2048 : 1024;
            } else {
                f = positionFractionStart;
            }
            if (($changed & 24576) == 0) {
                f2 = positionFractionEnd;
                $dirty |= $composer2.changed(f2) ? 16384 : 8192;
            } else {
                f2 = positionFractionEnd;
            }
            if ((196608 & $changed) == 0) {
                $dirty |= $composer2.changedInstance(list) ? 131072 : 65536;
            }
            if ((1572864 & $changed) == 0) {
                f3 = thumbPx;
                $dirty |= $composer2.changed(f3) ? 1048576 : 524288;
            } else {
                f3 = thumbPx;
            }
            if ((12582912 & $changed) == 0) {
                f4 = trackStrokeWidth;
                $dirty |= $composer2.changed(f4) ? 8388608 : 4194304;
            } else {
                f4 = trackStrokeWidth;
            }
            if ($composer2.shouldExecute(($dirty & 4793491) != 4793490, $dirty & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1833126050, $dirty, -1, "androidx.compose.material.Track (Slider.kt:833)");
                }
                final State<Color> stateTrackColor = colors.trackColor(enabled, false, $composer2, (($dirty >> 6) & 14) | 48 | (($dirty << 3) & 896));
                final State<Color> stateTrackColor2 = colors.trackColor(enabled, true, $composer2, (($dirty >> 6) & 14) | 48 | (($dirty << 3) & 896));
                final State<Color> stateTickColor = colors.tickColor(enabled, false, $composer2, (($dirty >> 6) & 14) | 48 | (($dirty << 3) & 896));
                final State<Color> stateTickColor2 = colors.tickColor(enabled, true, $composer2, (($dirty >> 6) & 14) | 48 | (($dirty << 3) & 896));
                ComposerKt.sourceInformationMarkerStart($composer2, 697561209, "CC(remember):Slider.kt#9igjgp");
                boolean invalid$iv = ((3670016 & $dirty) == 1048576) | $composer2.changed(stateTrackColor) | ((29360128 & $dirty) == 8388608) | ((57344 & $dirty) == 16384) | (($dirty & 7168) == 2048) | $composer2.changed(stateTrackColor2) | $composer2.changedInstance(list) | $composer2.changed(stateTickColor) | $composer2.changed(stateTickColor2);
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    final float f5 = f2;
                    final float f6 = f4;
                    final float f7 = f3;
                    Object value$iv = new Function1() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SliderKt.Track$lambda$0$0(f7, stateTrackColor, f6, f5, f, stateTrackColor2, list, stateTickColor, stateTickColor2, (DrawScope) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(modifier, (Function1) it$iv, $composer2, $dirty & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderKt.Track$lambda$1(modifier, colors, enabled, positionFractionStart, positionFractionEnd, list, thumbPx, trackStrokeWidth, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit Track$lambda$0$0(float $thumbPx, State $inactiveTrackColor, float $trackStrokeWidth, float $positionFractionEnd, float $positionFractionStart, State $activeTrackColor, List $tickFractions, State $inactiveTickColor, State $activeTickColor, DrawScope $this$Canvas) {
            Object answer$iv$iv$iv;
            boolean isRtl = $this$Canvas.getLayoutDirection() == LayoutDirection.Rtl;
            long arg0$iv = $this$Canvas.mo5670getCenterF1C5BW0();
            int bits$iv$iv$iv = (int) (arg0$iv & 4294967295L);
            float y$iv = Float.intBitsToFloat(bits$iv$iv$iv);
            long v1$iv$iv = Float.floatToRawIntBits($thumbPx);
            long v2$iv$iv = Float.floatToRawIntBits(y$iv);
            long sliderLeft = Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
            long arg0$iv2 = $this$Canvas.mo5671getSizeNHjbRc();
            int bits$iv$iv$iv2 = (int) (arg0$iv2 >> 32);
            float x$iv = Float.intBitsToFloat(bits$iv$iv$iv2) - $thumbPx;
            long arg0$iv3 = $this$Canvas.mo5670getCenterF1C5BW0();
            int bits$iv$iv$iv3 = (int) (arg0$iv3 & 4294967295L);
            float y$iv2 = Float.intBitsToFloat(bits$iv$iv$iv3);
            long v1$iv$iv2 = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv2 = Float.floatToRawIntBits(y$iv2);
            long sliderRight = Offset.m4844constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L));
            long sliderStart = isRtl ? sliderRight : sliderLeft;
            long sliderEnd = isRtl ? sliderLeft : sliderRight;
            DrawScope.m5657drawLineNGM6Ib0$default($this$Canvas, ((Color) $inactiveTrackColor.getValue()).m5107unboximpl(), sliderStart, sliderEnd, $trackStrokeWidth, StrokeCap.INSTANCE.m5472getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
            int bits$iv$iv$iv4 = (int) (sliderStart >> 32);
            int bits$iv$iv$iv5 = (int) (sliderEnd >> 32);
            int bits$iv$iv$iv6 = (int) (sliderStart >> 32);
            float x$iv2 = Float.intBitsToFloat(bits$iv$iv$iv4) + ((Float.intBitsToFloat(bits$iv$iv$iv5) - Float.intBitsToFloat(bits$iv$iv$iv6)) * $positionFractionEnd);
            long arg0$iv4 = $this$Canvas.mo5670getCenterF1C5BW0();
            int bits$iv$iv$iv7 = (int) (arg0$iv4 & 4294967295L);
            float y$iv3 = Float.intBitsToFloat(bits$iv$iv$iv7);
            long v1$iv$iv3 = Float.floatToRawIntBits(x$iv2);
            long v2$iv$iv3 = Float.floatToRawIntBits(y$iv3);
            long sliderValueEnd = Offset.m4844constructorimpl((v1$iv$iv3 << 32) | (v2$iv$iv3 & 4294967295L));
            int bits$iv$iv$iv8 = (int) (sliderStart >> 32);
            long arg0$iv5 = sliderEnd;
            int bits$iv$iv$iv9 = (int) (arg0$iv5 >> 32);
            int bits$iv$iv$iv10 = (int) (sliderStart >> 32);
            float x$iv3 = Float.intBitsToFloat(bits$iv$iv$iv8) + ((Float.intBitsToFloat(bits$iv$iv$iv9) - Float.intBitsToFloat(bits$iv$iv$iv10)) * $positionFractionStart);
            long arg0$iv6 = $this$Canvas.mo5670getCenterF1C5BW0();
            int bits$iv$iv$iv11 = (int) (arg0$iv6 & 4294967295L);
            float y$iv4 = Float.intBitsToFloat(bits$iv$iv$iv11);
            long v1$iv$iv4 = Float.floatToRawIntBits(x$iv3);
            long v2$iv$iv4 = Float.floatToRawIntBits(y$iv4);
            long sliderValueStart = Offset.m4844constructorimpl((v1$iv$iv4 << 32) | (v2$iv$iv4 & 4294967295L));
            DrawScope.m5657drawLineNGM6Ib0$default($this$Canvas, ((Color) $activeTrackColor.getValue()).m5107unboximpl(), sliderValueStart, sliderValueEnd, $trackStrokeWidth, StrokeCap.INSTANCE.m5472getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
            long sliderValueStart2 = sliderValueStart;
            long sliderValueEnd2 = sliderValueEnd;
            List $this$groupBy$iv = $tickFractions;
            Map $this$forEach$iv = new LinkedHashMap();
            for (Object element$iv$iv : $this$groupBy$iv) {
                float it = ((Number) element$iv$iv).floatValue();
                Boolean boolValueOf = Boolean.valueOf(it > $positionFractionEnd || it < $positionFractionStart);
                long sliderValueEnd3 = sliderValueEnd2;
                Object value$iv$iv$iv = $this$forEach$iv.get(boolValueOf);
                if (value$iv$iv$iv == null) {
                    answer$iv$iv$iv = new ArrayList();
                    $this$forEach$iv.put(boolValueOf, answer$iv$iv$iv);
                } else {
                    answer$iv$iv$iv = value$iv$iv$iv;
                }
                List list$iv$iv = (List) answer$iv$iv$iv;
                list$iv$iv.add(element$iv$iv);
                sliderValueEnd2 = sliderValueEnd3;
                sliderValueStart2 = sliderValueStart2;
            }
            int $i$f$forEach = 0;
            Iterator it2 = $this$forEach$iv.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry element$iv = (Map.Entry) it2.next();
                int i = 0;
                boolean outsideFraction = ((Boolean) element$iv.getKey()).booleanValue();
                List list = (List) element$iv.getValue();
                ArrayList target$iv = new ArrayList(list.size());
                int $i$f$forEach2 = $i$f$forEach;
                int $i$f$forEach3 = list.size();
                Iterator it3 = it2;
                int index$iv$iv = 0;
                while (index$iv$iv < $i$f$forEach3) {
                    Object item$iv$iv = list.get(index$iv$iv);
                    int index$iv$iv2 = index$iv$iv;
                    Map.Entry element$iv2 = element$iv;
                    long sliderEnd2 = sliderEnd;
                    long arg0$iv7 = OffsetKt.m4875lerpWko1d7g(sliderStart, sliderEnd2, ((Number) item$iv$iv).floatValue());
                    int bits$iv$iv$iv12 = (int) (arg0$iv7 >> 32);
                    float x$iv4 = Float.intBitsToFloat(bits$iv$iv$iv12);
                    long arg0$iv8 = $this$Canvas.mo5670getCenterF1C5BW0();
                    int bits$iv$iv$iv13 = (int) (arg0$iv8 & 4294967295L);
                    float y$iv5 = Float.intBitsToFloat(bits$iv$iv$iv13);
                    long v1$iv$iv5 = Float.floatToRawIntBits(x$iv4);
                    long v2$iv$iv5 = Float.floatToRawIntBits(y$iv5);
                    target$iv.add(Offset.m4841boximpl(Offset.m4844constructorimpl((v1$iv$iv5 << 32) | (v2$iv$iv5 & 4294967295L))));
                    index$iv$iv = index$iv$iv2 + 1;
                    $i$f$forEach3 = $i$f$forEach3;
                    element$iv = element$iv2;
                    i = i;
                    sliderEnd = sliderEnd2;
                }
                long sliderEnd3 = sliderEnd;
                DrawScope.m5662drawPointsF8ZwMP8$default($this$Canvas, target$iv, PointMode.INSTANCE.m5417getPointsr_lszbg(), ((Color) (outsideFraction ? $inactiveTickColor : $activeTickColor).getValue()).m5107unboximpl(), $trackStrokeWidth, StrokeCap.INSTANCE.m5472getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
                it2 = it3;
                $i$f$forEach = $i$f$forEach2;
                sliderEnd = sliderEnd3;
            }
            return Unit.INSTANCE;
        }

        private static final float snapValueToTick(float current, List<Float> list, float minPx, float maxPx) {
            Object minElem$iv;
            if (!list.isEmpty()) {
                minElem$iv = list.get(0);
                float it = ((Number) minElem$iv).floatValue();
                float minValue$iv = Math.abs(MathHelpersKt.lerp(minPx, maxPx, it) - current);
                int i$iv = 1;
                int lastIndex = CollectionsKt.getLastIndex(list);
                if (1 <= lastIndex) {
                    while (true) {
                        Object e$iv = list.get(i$iv);
                        float it2 = ((Number) e$iv).floatValue();
                        float v$iv = Math.abs(MathHelpersKt.lerp(minPx, maxPx, it2) - current);
                        if (Float.compare(minValue$iv, v$iv) > 0) {
                            minElem$iv = e$iv;
                            minValue$iv = v$iv;
                        }
                        if (i$iv == lastIndex) {
                            break;
                        }
                        i$iv++;
                    }
                }
            } else {
                minElem$iv = null;
            }
            Object minElem$iv2 = (Float) minElem$iv;
            if (minElem$iv2 == null) {
                return current;
            }
            float $this$snapValueToTick_u24lambda_u241 = ((Number) minElem$iv2).floatValue();
            return MathHelpersKt.lerp(minPx, maxPx, $this$snapValueToTick_u24lambda_u241);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        /* JADX INFO: renamed from: awaitSlop-8vUncbI, reason: not valid java name */
        public static final Object m1929awaitSlop8vUncbI(AwaitPointerEventScope $this$awaitSlop_u2d8vUncbI, long j, int i, Continuation<? super Pair<PointerInputChange, Float>> continuation) {
            SliderKt$awaitSlop$1 sliderKt$awaitSlop$1;
            final Ref.FloatRef initialDelta;
            Object objM1799awaitHorizontalPointerSlopOrCancellationgDDlDlE;
            if (continuation instanceof SliderKt$awaitSlop$1) {
                sliderKt$awaitSlop$1 = (SliderKt$awaitSlop$1) continuation;
                if ((sliderKt$awaitSlop$1.label & Integer.MIN_VALUE) != 0) {
                    sliderKt$awaitSlop$1.label -= Integer.MIN_VALUE;
                } else {
                    sliderKt$awaitSlop$1 = new SliderKt$awaitSlop$1(continuation);
                }
            } else {
                sliderKt$awaitSlop$1 = new SliderKt$awaitSlop$1(continuation);
            }
            SliderKt$awaitSlop$1 sliderKt$awaitSlop$2 = sliderKt$awaitSlop$1;
            Object $result = sliderKt$awaitSlop$2.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (sliderKt$awaitSlop$2.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    initialDelta = new Ref.FloatRef();
                    Function2 postPointerSlop = new Function2() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return SliderKt.awaitSlop_8vUncbI$lambda$0(initialDelta, (PointerInputChange) obj, ((Float) obj2).floatValue());
                        }
                    };
                    sliderKt$awaitSlop$2.L$0 = initialDelta;
                    sliderKt$awaitSlop$2.label = 1;
                    objM1799awaitHorizontalPointerSlopOrCancellationgDDlDlE = DragGestureDetectorCopyKt.m1799awaitHorizontalPointerSlopOrCancellationgDDlDlE($this$awaitSlop_u2d8vUncbI, j, i, postPointerSlop, sliderKt$awaitSlop$2);
                    if (objM1799awaitHorizontalPointerSlopOrCancellationgDDlDlE == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    initialDelta = (Ref.FloatRef) sliderKt$awaitSlop$2.L$0;
                    ResultKt.throwOnFailure($result);
                    objM1799awaitHorizontalPointerSlopOrCancellationgDDlDlE = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            PointerInputChange afterSlopResult = (PointerInputChange) objM1799awaitHorizontalPointerSlopOrCancellationgDDlDlE;
            if (afterSlopResult != null) {
                return TuplesKt.to(afterSlopResult, Boxing.boxFloat(initialDelta.element));
            }
            return null;
        }

        static final Unit awaitSlop_8vUncbI$lambda$0(Ref.FloatRef $initialDelta, PointerInputChange pointerInput, float offset) {
            pointerInput.consume();
            $initialDelta.element = offset;
            return Unit.INSTANCE;
        }

        private static final List<Float> stepsToTickFractions(int steps) {
            if (steps == 0) {
                return CollectionsKt.emptyList();
            }
            int i = steps + 2;
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                int it = i2;
                arrayList.add(Float.valueOf(it / (steps + 1)));
            }
            return arrayList;
        }

        private static final float scale(float a1, float b1, float x1, float a2, float b2) {
            return MathHelpersKt.lerp(a2, b2, calcFraction(a1, b1, x1));
        }

        private static final ClosedFloatingPointRange<Float> scale(float a1, float b1, ClosedFloatingPointRange<Float> closedFloatingPointRange, float a2, float b2) {
            return RangesKt.rangeTo(scale(a1, b1, closedFloatingPointRange.getStart().floatValue(), a2, b2), scale(a1, b1, closedFloatingPointRange.getEndInclusive().floatValue(), a2, b2));
        }

        private static final float calcFraction(float a, float b, float pos) {
            float $this$fastCoerceIn$iv = ((b - a) > 0.0f ? 1 : ((b - a) == 0.0f ? 0 : -1)) == 0 ? 0.0f : (pos - a) / (b - a);
            float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
            if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
                $this$fastCoerceAtLeast$iv$iv = 0.0f;
            }
            if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
                return 1.0f;
            }
            return $this$fastCoerceAtLeast$iv$iv;
        }

        private static final void CorrectValueSideEffect(final Function1<? super Float, Float> function1, final ClosedFloatingPointRange<Float> closedFloatingPointRange, final ClosedFloatingPointRange<Float> closedFloatingPointRange2, final MutableState<Float> mutableState, final float value, Composer $composer, final int $changed) {
            final Function1<? super Float, Float> function2;
            final ClosedFloatingPointRange<Float> closedFloatingPointRange3;
            final ClosedFloatingPointRange<Float> closedFloatingPointRange4;
            final MutableState<Float> mutableState2;
            float f;
            Composer $composer2 = $composer.startRestartGroup(-743965752);
            ComposerKt.sourceInformation($composer2, "C(CorrectValueSideEffect)N(scaleToOffset,valueRange,trackRange,valueState,value)928@38204L300,928@38193L311:Slider.kt#jmzs0o");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                function2 = function1;
                $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
            } else {
                function2 = function1;
            }
            if (($changed & 48) == 0) {
                closedFloatingPointRange3 = closedFloatingPointRange;
                $dirty |= $composer2.changed(closedFloatingPointRange3) ? 32 : 16;
            } else {
                closedFloatingPointRange3 = closedFloatingPointRange;
            }
            if (($changed & 384) == 0) {
                closedFloatingPointRange4 = closedFloatingPointRange2;
                $dirty |= $composer2.changed(closedFloatingPointRange4) ? 256 : 128;
            } else {
                closedFloatingPointRange4 = closedFloatingPointRange2;
            }
            if (($changed & 3072) == 0) {
                mutableState2 = mutableState;
                $dirty |= $composer2.changed(mutableState2) ? 2048 : 1024;
            } else {
                mutableState2 = mutableState;
            }
            if (($changed & 24576) == 0) {
                f = value;
                $dirty |= $composer2.changed(f) ? 16384 : 8192;
            } else {
                f = value;
            }
            int $dirty2 = $dirty;
            if (!$composer2.shouldExecute(($dirty2 & 9363) != 9362, $dirty2 & 1)) {
                $composer2.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-743965752, $dirty2, -1, "androidx.compose.material.CorrectValueSideEffect (Slider.kt:927)");
                }
                ComposerKt.sourceInformationMarkerStart($composer2, -1986508652, "CC(remember):Slider.kt#9igjgp");
                boolean invalid$iv = (($dirty2 & 112) == 32) | (($dirty2 & 14) == 4) | ((57344 & $dirty2) == 16384) | (($dirty2 & 7168) == 2048) | (($dirty2 & 896) == 256);
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    final float f2 = f;
                    Object value$iv = new Function0() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SliderKt.CorrectValueSideEffect$lambda$0$0(closedFloatingPointRange3, function2, f2, mutableState2, closedFloatingPointRange4);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.SideEffect((Function0) it$iv, $composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderKt.CorrectValueSideEffect$lambda$1(function1, closedFloatingPointRange, closedFloatingPointRange2, mutableState, value, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final Unit CorrectValueSideEffect$lambda$0$0(ClosedFloatingPointRange $valueRange, Function1 $scaleToOffset, float $value, MutableState $valueState, ClosedFloatingPointRange $trackRange) {
            float error = (((Number) $valueRange.getEndInclusive()).floatValue() - ((Number) $valueRange.getStart()).floatValue()) / 1000.0f;
            float newOffset = ((Number) $scaleToOffset.invoke(Float.valueOf($value))).floatValue();
            if (Math.abs(newOffset - ((Number) $valueState.getValue()).floatValue()) > error && $trackRange.contains((Comparable) $valueState.getValue())) {
                $valueState.setValue(Float.valueOf(newOffset));
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Modifier sliderSemantics(Modifier $this$sliderSemantics, float value, final boolean enabled, final Function1<? super Float, Unit> function1, final Function0<Unit> function0, final ClosedFloatingPointRange<Float> closedFloatingPointRange, final int steps) {
            final float coerced = RangesKt.coerceIn(value, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
            return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default($this$sliderSemantics, false, new Function1() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SliderKt.sliderSemantics$lambda$0(enabled, closedFloatingPointRange, steps, coerced, function1, function0, (SemanticsPropertyReceiver) obj);
                }
            }, 1, null), value, closedFloatingPointRange, steps);
        }

        static final Unit sliderSemantics$lambda$0(boolean $enabled, final ClosedFloatingPointRange $valueRange, final int $steps, final float $coerced, final Function1 $onValueChange, final Function0 $onValueChangeFinished, SemanticsPropertyReceiver $this$semantics) {
            if (!$enabled) {
                SemanticsPropertiesKt.disabled($this$semantics);
            }
            SemanticsPropertiesKt.setProgress$default($this$semantics, null, new Function1() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(SliderKt.sliderSemantics$lambda$0$0($valueRange, $steps, $coerced, $onValueChange, $onValueChangeFinished, ((Float) obj).floatValue()));
                }
            }, 1, null);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final boolean sliderSemantics$lambda$0$0(ClosedFloatingPointRange $valueRange, int $steps, float $coerced, Function1 $onValueChange, Function0 $onValueChangeFinished, float targetValue) {
            float newValue = RangesKt.coerceIn(targetValue, ((Number) $valueRange.getStart()).floatValue(), ((Number) $valueRange.getEndInclusive()).floatValue());
            if ($steps > 0) {
                float distance = newValue;
                int i = 0;
                int i2 = $steps + 1;
                if (0 <= i2) {
                    while (true) {
                        float stepValue = MathHelpersKt.lerp(((Number) $valueRange.getStart()).floatValue(), ((Number) $valueRange.getEndInclusive()).floatValue(), i / ($steps + 1));
                        if (Math.abs(stepValue - newValue) <= distance) {
                            distance = Math.abs(stepValue - newValue);
                            newValue = stepValue;
                        }
                        if (i == i2) {
                            break;
                        }
                        i++;
                    }
                }
            }
            float resolvedValue = newValue;
            if (resolvedValue == $coerced) {
                return false;
            }
            $onValueChange.invoke(Float.valueOf(resolvedValue));
            if ($onValueChangeFinished != null) {
                $onValueChangeFinished.invoke();
            }
            return true;
        }

        static final Modifier sliderTapModifier$lambda$1(boolean $enabled, DraggableState $draggableState, MutableInteractionSource $interactionSource, float $maxPx, boolean $isRtl, MutableState $pressOffset, State $rawOffset, State $gestureEndAction, Modifier $this$composed, Composer $composer, int $changed) {
            Modifier modifierPointerInput;
            $composer.startReplaceGroup(1945228890);
            ComposerKt.sourceInformation($composer, "C:Slider.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1945228890, $changed, -1, "androidx.compose.material.sliderTapModifier.<anonymous> (Slider.kt:1000)");
            }
            if ($enabled) {
                $composer.startReplaceGroup(-1679801122);
                ComposerKt.sourceInformation($composer, "1001@40932L24,1002@41035L983");
                ComposerKt.sourceInformationMarkerStart($composer, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart($composer, 683736516, "CC(remember):Effects.kt#9igjgp");
                Object it$iv$iv = $composer.rememberedValue();
                if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer);
                    $composer.updateRememberedValue(value$iv$iv);
                    it$iv$iv = value$iv$iv;
                }
                CoroutineScope scope = (CoroutineScope) it$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Object[] objArr = {$draggableState, $interactionSource, Float.valueOf($maxPx), Boolean.valueOf($isRtl)};
                ComposerKt.sourceInformationMarkerStart($composer, 1054195633, "CC(remember):Slider.kt#9igjgp");
                boolean invalid$iv = $composer.changed($isRtl) | $composer.changed($maxPx) | $composer.changed($pressOffset) | $composer.changed($rawOffset) | $composer.changedInstance(scope) | $composer.changedInstance($draggableState) | $composer.changed($gestureEndAction);
                Object it$iv = $composer.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = (PointerInputEventHandler) new SliderKt$sliderTapModifier$2$1$1($isRtl, $maxPx, $pressOffset, $rawOffset, scope, $draggableState, $gestureEndAction);
                    $composer.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput($this$composed, objArr, (PointerInputEventHandler) it$iv);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1678708124);
                $composer.endReplaceGroup();
                modifierPointerInput = $this$composed;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return modifierPointerInput;
        }

        private static final Modifier sliderTapModifier(Modifier $this$sliderTapModifier, final DraggableState draggableState, final MutableInteractionSource interactionSource, final float maxPx, final boolean isRtl, final State<Float> state, final State<? extends Function1<? super Float, Unit>> state2, final MutableState<Float> mutableState, final boolean enabled) {
            return ComposedModifierKt.composed($this$sliderTapModifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material.SliderKt$sliderTapModifier$$inlined$debugInspectorInfo$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                    invoke2(inspectorInfo);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(InspectorInfo $this$null) {
                    $this$null.setName("sliderTapModifier");
                    $this$null.getProperties().set("draggableState", draggableState);
                    $this$null.getProperties().set("interactionSource", interactionSource);
                    $this$null.getProperties().set("maxPx", Float.valueOf(maxPx));
                    $this$null.getProperties().set("isRtl", Boolean.valueOf(isRtl));
                    $this$null.getProperties().set("rawOffset", state);
                    $this$null.getProperties().set("gestureEndAction", state2);
                    $this$null.getProperties().set("pressOffset", mutableState);
                    $this$null.getProperties().set("enabled", Boolean.valueOf(enabled));
                }
            } : InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: androidx.compose.material.SliderKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return SliderKt.sliderTapModifier$lambda$1(enabled, draggableState, interactionSource, maxPx, isRtl, mutableState, state, state2, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }

        /* JADX INFO: renamed from: androidx.compose.material.SliderKt$animateToTarget$2, reason: invalid class name */
        /* JADX INFO: compiled from: Slider.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/DragScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.material.SliderKt$animateToTarget$2", f = "Slider.kt", i = {}, l = {ClientProto.METHOD_SIGNATURE_FIELD_NUMBER}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass2 extends SuspendLambda implements Function2<DragScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ float $current;
            final /* synthetic */ float $target;
            final /* synthetic */ float $velocity;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(float f, float f2, float f3, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$current = f;
                this.$target = f2;
                this.$velocity = f3;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$current, this.$target, this.$velocity, continuation);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(DragScope dragScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(dragScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        final DragScope $this$drag = (DragScope) this.L$0;
                        final Ref.FloatRef latestValue = new Ref.FloatRef();
                        latestValue.element = this.$current;
                        this.label = 1;
                        if (AnimatableKt.Animatable$default(this.$current, 0.0f, 2, null).animateTo(Boxing.boxFloat(this.$target), SliderKt.SliderToTickAnimation, Boxing.boxFloat(this.$velocity), new Function1() { // from class: androidx.compose.material.SliderKt$animateToTarget$2$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return SliderKt.AnonymousClass2.invokeSuspend$lambda$0($this$drag, latestValue, (Animatable) obj);
                            }
                        }, this) == coroutine_suspended) {
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

            static final Unit invokeSuspend$lambda$0(DragScope $$this$drag, Ref.FloatRef $latestValue, Animatable $this$animateTo) {
                $$this$drag.dragBy(((Number) $this$animateTo.getValue()).floatValue() - $latestValue.element);
                $latestValue.element = ((Number) $this$animateTo.getValue()).floatValue();
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Object animateToTarget(DraggableState draggableState, float current, float target, float velocity, Continuation<? super Unit> continuation) {
            Object objDrag$default = DraggableState.drag$default(draggableState, null, new AnonymousClass2(current, target, velocity, null), continuation, 1, null);
            return objDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDrag$default : Unit.INSTANCE;
        }

        private static final Modifier rangeSliderPressDragModifier(Modifier $this$rangeSliderPressDragModifier, final MutableInteractionSource startInteractionSource, final MutableInteractionSource endInteractionSource, final State<Float> state, final State<Float> state2, boolean enabled, final boolean isRtl, final float maxPx, ClosedFloatingPointRange<Float> closedFloatingPointRange, final State<? extends Function1<? super Boolean, Unit>> state3, final State<? extends Function2<? super Boolean, ? super Float, Unit>> state4) {
            return enabled ? SuspendingPointerInputFilterKt.pointerInput($this$rangeSliderPressDragModifier, new Object[]{startInteractionSource, endInteractionSource, Float.valueOf(maxPx), Boolean.valueOf(isRtl), closedFloatingPointRange}, new PointerInputEventHandler() { // from class: androidx.compose.material.SliderKt.rangeSliderPressDragModifier.1
                @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                    RangeSliderLogic rangeSliderLogic = new RangeSliderLogic(startInteractionSource, endInteractionSource, state, state2, state4);
                    Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C00481($this$pointerInput, isRtl, maxPx, rangeSliderLogic, state, state3, state2, state4, null), continuation);
                    return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: Slider.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1", f = "Slider.kt", i = {}, l = {1081}, m = "invokeSuspend", n = {}, s = {}, v = 1)
                static final class C00481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ State<Function1<Boolean, Unit>> $gestureEndAction;
                    final /* synthetic */ boolean $isRtl;
                    final /* synthetic */ float $maxPx;
                    final /* synthetic */ State<Function2<Boolean, Float, Unit>> $onDrag;
                    final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
                    final /* synthetic */ State<Float> $rawOffsetEnd;
                    final /* synthetic */ State<Float> $rawOffsetStart;
                    final /* synthetic */ PointerInputScope $this_pointerInput;
                    private /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    C00481(PointerInputScope pointerInputScope, boolean z, float f, RangeSliderLogic rangeSliderLogic, State<Float> state, State<? extends Function1<? super Boolean, Unit>> state2, State<Float> state3, State<? extends Function2<? super Boolean, ? super Float, Unit>> state4, Continuation<? super C00481> continuation) {
                        super(2, continuation);
                        this.$this_pointerInput = pointerInputScope;
                        this.$isRtl = z;
                        this.$maxPx = f;
                        this.$rangeSliderLogic = rangeSliderLogic;
                        this.$rawOffsetStart = state;
                        this.$gestureEndAction = state2;
                        this.$rawOffsetEnd = state3;
                        this.$onDrag = state4;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        C00481 c00481 = new C00481(this.$this_pointerInput, this.$isRtl, this.$maxPx, this.$rangeSliderLogic, this.$rawOffsetStart, this.$gestureEndAction, this.$rawOffsetEnd, this.$onDrag, continuation);
                        c00481.L$0 = obj;
                        return c00481;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C00481) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX INFO: renamed from: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1, reason: invalid class name and collision with other inner class name */
                    /* JADX INFO: compiled from: Slider.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1", f = "Slider.kt", i = {0, 1, 1, 1, 1, 1, 2, 2}, l = {1082, 1093, 1115}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", NotificationCompat.CATEGORY_EVENT, "interaction", "posX", "draggingStart", "interaction", "draggingStart"}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1"}, v = 1)
                    static final class C00491 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ CoroutineScope $$this$coroutineScope;
                        final /* synthetic */ State<Function1<Boolean, Unit>> $gestureEndAction;
                        final /* synthetic */ boolean $isRtl;
                        final /* synthetic */ float $maxPx;
                        final /* synthetic */ State<Function2<Boolean, Float, Unit>> $onDrag;
                        final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
                        final /* synthetic */ State<Float> $rawOffsetEnd;
                        final /* synthetic */ State<Float> $rawOffsetStart;
                        private /* synthetic */ Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        Object L$4;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        C00491(boolean z, float f, RangeSliderLogic rangeSliderLogic, State<Float> state, CoroutineScope coroutineScope, State<? extends Function1<? super Boolean, Unit>> state2, State<Float> state3, State<? extends Function2<? super Boolean, ? super Float, Unit>> state4, Continuation<? super C00491> continuation) {
                            super(2, continuation);
                            this.$isRtl = z;
                            this.$maxPx = f;
                            this.$rangeSliderLogic = rangeSliderLogic;
                            this.$rawOffsetStart = state;
                            this.$$this$coroutineScope = coroutineScope;
                            this.$gestureEndAction = state2;
                            this.$rawOffsetEnd = state3;
                            this.$onDrag = state4;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            C00491 c00491 = new C00491(this.$isRtl, this.$maxPx, this.$rangeSliderLogic, this.$rawOffsetStart, this.$$this$coroutineScope, this.$gestureEndAction, this.$rawOffsetEnd, this.$onDrag, continuation);
                            c00491.L$0 = obj;
                            return c00491;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                            return ((C00491) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        /* JADX WARN: Code duplicated, block: B:19:0x008a  */
                        /* JADX WARN: Code duplicated, block: B:20:0x00a2  */
                        /* JADX WARN: Code duplicated, block: B:23:0x00c6 A[DONT_INVERT] */
                        /* JADX WARN: Code duplicated, block: B:24:0x00c8  */
                        /* JADX WARN: Code duplicated, block: B:25:0x00ca  */
                        /* JADX WARN: Code duplicated, block: B:26:0x00cc  */
                        /* JADX WARN: Code duplicated, block: B:28:0x00de  */
                        /* JADX WARN: Code duplicated, block: B:29:0x00e0  */
                        /* JADX WARN: Code duplicated, block: B:32:0x0101 A[RETURN] */
                        /* JADX WARN: Code duplicated, block: B:33:0x0102  */
                        /* JADX WARN: Code duplicated, block: B:36:0x0109  */
                        /* JADX WARN: Code duplicated, block: B:38:0x0131  */
                        /* JADX WARN: Code duplicated, block: B:41:0x0148  */
                        /* JADX WARN: Code duplicated, block: B:44:0x014c  */
                        /* JADX WARN: Code duplicated, block: B:46:0x0159  */
                        /* JADX WARN: Code duplicated, block: B:49:0x015e  */
                        /* JADX WARN: Code duplicated, block: B:54:0x0188  */
                        /* JADX WARN: Code duplicated, block: B:56:0x018c  */
                        /* JADX WARN: Code duplicated, block: B:60:0x01c2 A[RETURN] */
                        /* JADX WARN: Code duplicated, block: B:61:0x01c3  */
                        /* JADX WARN: Code duplicated, block: B:64:0x01d0 A[Catch: CancellationException -> 0x01e1, TryCatch #0 {CancellationException -> 0x01e1, blocks: (B:62:0x01c6, B:64:0x01d0, B:65:0x01d8), top: B:75:0x01c6 }] */
                        /* JADX WARN: Code duplicated, block: B:65:0x01d8 A[Catch: CancellationException -> 0x01e1, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x01e1, blocks: (B:62:0x01c6, B:64:0x01d0, B:65:0x01d8), top: B:75:0x01c6 }] */
                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object $result2;
                            Object $result3;
                            AwaitPointerEventScope $this$awaitEachGesture;
                            PointerInputChange event;
                            DragInteraction.Start interaction;
                            Ref.FloatRef posX;
                            char c;
                            float fIntBitsToFloat;
                            int compare;
                            Ref.BooleanRef draggingStart;
                            boolean z;
                            Object objM1929awaitSlop8vUncbI;
                            DragInteraction.Start interaction2;
                            Object $result4;
                            final Ref.BooleanRef draggingStart2;
                            Pair it;
                            Object $result5;
                            Ref.BooleanRef draggingStart3;
                            Object $result6;
                            State<Float> state;
                            boolean z2;
                            float slop;
                            boolean shouldUpdateCapturedThumb;
                            DragInteraction.Cancel finishInteraction;
                            boolean success;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    AwaitPointerEventScope $this$awaitEachGesture2 = (AwaitPointerEventScope) this.L$0;
                                    this.L$0 = $this$awaitEachGesture2;
                                    this.label = 1;
                                    Object objAwaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture2, false, null, this, 2, null);
                                    if (objAwaitFirstDown$default == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    $result2 = $result;
                                    $result3 = objAwaitFirstDown$default;
                                    $this$awaitEachGesture = $this$awaitEachGesture2;
                                    event = (PointerInputChange) $result3;
                                    interaction = new DragInteraction.Start();
                                    posX = new Ref.FloatRef();
                                    if (this.$isRtl) {
                                        float f = this.$maxPx;
                                        long arg0$iv = event.getPosition();
                                        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
                                        fIntBitsToFloat = f - Float.intBitsToFloat(bits$iv$iv$iv);
                                        c = ' ';
                                    } else {
                                        long arg0$iv2 = event.getPosition();
                                        c = ' ';
                                        int bits$iv$iv$iv2 = (int) (arg0$iv2 >> 32);
                                        fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv2);
                                    }
                                    posX.element = fIntBitsToFloat;
                                    compare = this.$rangeSliderLogic.compareOffsets(posX.element);
                                    draggingStart = new Ref.BooleanRef();
                                    if (compare != 0) {
                                        if (compare < 0) {
                                            z = true;
                                        } else {
                                            z = false;
                                        }
                                    } else if (this.$rawOffsetStart.getValue().floatValue() > posX.element) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    draggingStart.element = z;
                                    this.L$0 = $this$awaitEachGesture;
                                    this.L$1 = event;
                                    this.L$2 = interaction;
                                    this.L$3 = posX;
                                    this.L$4 = draggingStart;
                                    this.label = 2;
                                    objM1929awaitSlop8vUncbI = SliderKt.m1929awaitSlop8vUncbI($this$awaitEachGesture, event.getId(), event.getType(), this);
                                    if (objM1929awaitSlop8vUncbI == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    interaction2 = interaction;
                                    $result4 = objM1929awaitSlop8vUncbI;
                                    draggingStart2 = draggingStart;
                                    it = (Pair) $result4;
                                    if (it != null) {
                                        state = this.$rawOffsetEnd;
                                        State<Float> state2 = this.$rawOffsetStart;
                                        z2 = this.$isRtl;
                                        slop = DragGestureDetectorCopyKt.m1802pointerSlopE8SPZFQ($this$awaitEachGesture.getViewConfiguration(), event.getType());
                                        if (Math.abs(state.getValue().floatValue() - posX.element) < slop || Math.abs(state2.getValue().floatValue() - posX.element) >= slop) {
                                            shouldUpdateCapturedThumb = false;
                                        } else {
                                            shouldUpdateCapturedThumb = true;
                                        }
                                        if (shouldUpdateCapturedThumb) {
                                            float dir = ((Number) it.getSecond()).floatValue();
                                            draggingStart2.element = z2 ? dir < 0.0f : dir >= 0.0f;
                                            float f2 = posX.element;
                                            long arg0$iv3 = PointerEventKt.positionChange((PointerInputChange) it.getFirst());
                                            int bits$iv$iv$iv3 = (int) (arg0$iv3 >> c);
                                            posX.element = f2 + Float.intBitsToFloat(bits$iv$iv$iv3);
                                        }
                                    }
                                    this.$rangeSliderLogic.captureThumb(draggingStart2.element, posX.element, interaction2, this.$$this$coroutineScope);
                                    try {
                                        long id = event.getId();
                                        final State<Function2<Boolean, Float, Unit>> state3 = this.$onDrag;
                                        final boolean z3 = this.$isRtl;
                                        this.L$0 = interaction2;
                                        this.L$1 = draggingStart2;
                                        this.L$2 = null;
                                        this.L$3 = null;
                                        this.L$4 = null;
                                        this.label = 3;
                                        $result5 = DragGestureDetectorKt.m475horizontalDragjO51t88($this$awaitEachGesture, id, new Function1() { // from class: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1$$ExternalSyntheticLambda0
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                return SliderKt.AnonymousClass1.C00481.C00491.invokeSuspend$lambda$1(state3, draggingStart2, z3, (PointerInputChange) obj);
                                            }
                                        }, this);
                                        if ($result5 == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        draggingStart3 = draggingStart2;
                                        $result6 = $result2;
                                        try {
                                            success = ((Boolean) $result5).booleanValue();
                                            if (success) {
                                                finishInteraction = new DragInteraction.Stop(interaction2);
                                            } else {
                                                finishInteraction = new DragInteraction.Cancel(interaction2);
                                            }
                                            break;
                                        } catch (CancellationException e) {
                                            $result5 = $result6;
                                            draggingStart2 = draggingStart3;
                                            finishInteraction = new DragInteraction.Cancel(interaction2);
                                            draggingStart3 = draggingStart2;
                                        }
                                        this.$gestureEndAction.getValue().invoke(Boxing.boxBoolean(draggingStart3.element));
                                        BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, null, new AnonymousClass2(this.$rangeSliderLogic, draggingStart3, finishInteraction, null), 3, null);
                                        return Unit.INSTANCE;
                                    } catch (CancellationException e2) {
                                        $result5 = $result2;
                                        finishInteraction = new DragInteraction.Cancel(interaction2);
                                        draggingStart3 = draggingStart2;
                                        this.$gestureEndAction.getValue().invoke(Boxing.boxBoolean(draggingStart3.element));
                                        BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, null, new AnonymousClass2(this.$rangeSliderLogic, draggingStart3, finishInteraction, null), 3, null);
                                        return Unit.INSTANCE;
                                    }
                                case 1:
                                    $result3 = $result;
                                    AwaitPointerEventScope $this$awaitEachGesture3 = (AwaitPointerEventScope) this.L$0;
                                    ResultKt.throwOnFailure($result3);
                                    $this$awaitEachGesture = $this$awaitEachGesture3;
                                    $result2 = $result3;
                                    event = (PointerInputChange) $result3;
                                    interaction = new DragInteraction.Start();
                                    posX = new Ref.FloatRef();
                                    if (this.$isRtl) {
                                        float f3 = this.$maxPx;
                                        long arg0$iv4 = event.getPosition();
                                        int bits$iv$iv$iv4 = (int) (arg0$iv4 >> 32);
                                        fIntBitsToFloat = f3 - Float.intBitsToFloat(bits$iv$iv$iv4);
                                        c = ' ';
                                    } else {
                                        long arg0$iv5 = event.getPosition();
                                        c = ' ';
                                        int bits$iv$iv$iv5 = (int) (arg0$iv5 >> 32);
                                        fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv5);
                                    }
                                    posX.element = fIntBitsToFloat;
                                    compare = this.$rangeSliderLogic.compareOffsets(posX.element);
                                    draggingStart = new Ref.BooleanRef();
                                    if (compare != 0) {
                                        if (compare < 0) {
                                            z = true;
                                        } else {
                                            z = false;
                                        }
                                    } else if (this.$rawOffsetStart.getValue().floatValue() > posX.element) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    draggingStart.element = z;
                                    this.L$0 = $this$awaitEachGesture;
                                    this.L$1 = event;
                                    this.L$2 = interaction;
                                    this.L$3 = posX;
                                    this.L$4 = draggingStart;
                                    this.label = 2;
                                    objM1929awaitSlop8vUncbI = SliderKt.m1929awaitSlop8vUncbI($this$awaitEachGesture, event.getId(), event.getType(), this);
                                    if (objM1929awaitSlop8vUncbI == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    interaction2 = interaction;
                                    $result4 = objM1929awaitSlop8vUncbI;
                                    draggingStart2 = draggingStart;
                                    it = (Pair) $result4;
                                    if (it != null) {
                                        state = this.$rawOffsetEnd;
                                        State<Float> state4 = this.$rawOffsetStart;
                                        z2 = this.$isRtl;
                                        slop = DragGestureDetectorCopyKt.m1802pointerSlopE8SPZFQ($this$awaitEachGesture.getViewConfiguration(), event.getType());
                                        if (Math.abs(state.getValue().floatValue() - posX.element) < slop) {
                                            shouldUpdateCapturedThumb = false;
                                        } else {
                                            shouldUpdateCapturedThumb = false;
                                        }
                                        if (shouldUpdateCapturedThumb) {
                                            float dir2 = ((Number) it.getSecond()).floatValue();
                                            draggingStart2.element = z2 ? dir2 < 0.0f : dir2 >= 0.0f;
                                            float f4 = posX.element;
                                            long arg0$iv6 = PointerEventKt.positionChange((PointerInputChange) it.getFirst());
                                            int bits$iv$iv$iv6 = (int) (arg0$iv6 >> c);
                                            posX.element = f4 + Float.intBitsToFloat(bits$iv$iv$iv6);
                                        }
                                    }
                                    this.$rangeSliderLogic.captureThumb(draggingStart2.element, posX.element, interaction2, this.$$this$coroutineScope);
                                    long id2 = event.getId();
                                    final State state5 = this.$onDrag;
                                    final boolean z4 = this.$isRtl;
                                    this.L$0 = interaction2;
                                    this.L$1 = draggingStart2;
                                    this.L$2 = null;
                                    this.L$3 = null;
                                    this.L$4 = null;
                                    this.label = 3;
                                    $result5 = DragGestureDetectorKt.m475horizontalDragjO51t88($this$awaitEachGesture, id2, new Function1() { // from class: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1$$ExternalSyntheticLambda0
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            return SliderKt.AnonymousClass1.C00481.C00491.invokeSuspend$lambda$1(state5, draggingStart2, z4, (PointerInputChange) obj);
                                        }
                                    }, this);
                                    if ($result5 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    draggingStart3 = draggingStart2;
                                    $result6 = $result2;
                                    success = ((Boolean) $result5).booleanValue();
                                    if (success) {
                                        finishInteraction = new DragInteraction.Stop(interaction2);
                                    } else {
                                        finishInteraction = new DragInteraction.Cancel(interaction2);
                                    }
                                    this.$gestureEndAction.getValue().invoke(Boxing.boxBoolean(draggingStart3.element));
                                    BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, null, new AnonymousClass2(this.$rangeSliderLogic, draggingStart3, finishInteraction, null), 3, null);
                                    return Unit.INSTANCE;
                                case 2:
                                    $result4 = $result;
                                    Ref.BooleanRef draggingStart4 = (Ref.BooleanRef) this.L$4;
                                    posX = (Ref.FloatRef) this.L$3;
                                    DragInteraction.Start interaction3 = (DragInteraction.Start) this.L$2;
                                    event = (PointerInputChange) this.L$1;
                                    $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                                    ResultKt.throwOnFailure($result4);
                                    c = ' ';
                                    draggingStart2 = draggingStart4;
                                    interaction2 = interaction3;
                                    $result2 = $result4;
                                    it = (Pair) $result4;
                                    if (it != null) {
                                        state = this.$rawOffsetEnd;
                                        State<Float> state6 = this.$rawOffsetStart;
                                        z2 = this.$isRtl;
                                        slop = DragGestureDetectorCopyKt.m1802pointerSlopE8SPZFQ($this$awaitEachGesture.getViewConfiguration(), event.getType());
                                        if (Math.abs(state.getValue().floatValue() - posX.element) < slop) {
                                            shouldUpdateCapturedThumb = false;
                                        } else {
                                            shouldUpdateCapturedThumb = false;
                                        }
                                        if (shouldUpdateCapturedThumb) {
                                            float dir3 = ((Number) it.getSecond()).floatValue();
                                            draggingStart2.element = z2 ? dir3 < 0.0f : dir3 >= 0.0f;
                                            float f5 = posX.element;
                                            long arg0$iv7 = PointerEventKt.positionChange((PointerInputChange) it.getFirst());
                                            int bits$iv$iv$iv7 = (int) (arg0$iv7 >> c);
                                            posX.element = f5 + Float.intBitsToFloat(bits$iv$iv$iv7);
                                        }
                                    }
                                    this.$rangeSliderLogic.captureThumb(draggingStart2.element, posX.element, interaction2, this.$$this$coroutineScope);
                                    long id3 = event.getId();
                                    final State state7 = this.$onDrag;
                                    final boolean z5 = this.$isRtl;
                                    this.L$0 = interaction2;
                                    this.L$1 = draggingStart2;
                                    this.L$2 = null;
                                    this.L$3 = null;
                                    this.L$4 = null;
                                    this.label = 3;
                                    $result5 = DragGestureDetectorKt.m475horizontalDragjO51t88($this$awaitEachGesture, id3, new Function1() { // from class: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1$$ExternalSyntheticLambda0
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            return SliderKt.AnonymousClass1.C00481.C00491.invokeSuspend$lambda$1(state7, draggingStart2, z5, (PointerInputChange) obj);
                                        }
                                    }, this);
                                    if ($result5 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    draggingStart3 = draggingStart2;
                                    $result6 = $result2;
                                    success = ((Boolean) $result5).booleanValue();
                                    if (success) {
                                        finishInteraction = new DragInteraction.Stop(interaction2);
                                    } else {
                                        finishInteraction = new DragInteraction.Cancel(interaction2);
                                    }
                                    this.$gestureEndAction.getValue().invoke(Boxing.boxBoolean(draggingStart3.element));
                                    BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, null, new AnonymousClass2(this.$rangeSliderLogic, draggingStart3, finishInteraction, null), 3, null);
                                    return Unit.INSTANCE;
                                case 3:
                                    $result5 = $result;
                                    draggingStart2 = (Ref.BooleanRef) this.L$1;
                                    interaction2 = (DragInteraction.Start) this.L$0;
                                    try {
                                        ResultKt.throwOnFailure($result5);
                                        draggingStart3 = draggingStart2;
                                        $result6 = $result5;
                                        success = ((Boolean) $result5).booleanValue();
                                        if (success) {
                                            finishInteraction = new DragInteraction.Stop(interaction2);
                                        } else {
                                            finishInteraction = new DragInteraction.Cancel(interaction2);
                                        }
                                        break;
                                    } catch (CancellationException e3) {
                                        finishInteraction = new DragInteraction.Cancel(interaction2);
                                        draggingStart3 = draggingStart2;
                                        this.$gestureEndAction.getValue().invoke(Boxing.boxBoolean(draggingStart3.element));
                                        BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, null, new AnonymousClass2(this.$rangeSliderLogic, draggingStart3, finishInteraction, null), 3, null);
                                        return Unit.INSTANCE;
                                    }
                                    this.$gestureEndAction.getValue().invoke(Boxing.boxBoolean(draggingStart3.element));
                                    BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, null, new AnonymousClass2(this.$rangeSliderLogic, draggingStart3, finishInteraction, null), 3, null);
                                    return Unit.INSTANCE;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                        }

                        static final Unit invokeSuspend$lambda$1(State $onDrag, Ref.BooleanRef $draggingStart, boolean $isRtl, PointerInputChange it) {
                            long arg0$iv = PointerEventKt.positionChange(it);
                            int bits$iv$iv$iv = (int) (arg0$iv >> 32);
                            float deltaX = Float.intBitsToFloat(bits$iv$iv$iv);
                            ((Function2) $onDrag.getValue()).invoke(Boolean.valueOf($draggingStart.element), Float.valueOf($isRtl ? -deltaX : deltaX));
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1$2, reason: invalid class name */
                        /* JADX INFO: compiled from: Slider.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                        @DebugMetadata(c = "androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1$2", f = "Slider.kt", i = {}, l = {1133}, m = "invokeSuspend", n = {}, s = {}, v = 1)
                        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ Ref.BooleanRef $draggingStart;
                            final /* synthetic */ DragInteraction $finishInteraction;
                            final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass2(RangeSliderLogic rangeSliderLogic, Ref.BooleanRef booleanRef, DragInteraction dragInteraction, Continuation<? super AnonymousClass2> continuation) {
                                super(2, continuation);
                                this.$rangeSliderLogic = rangeSliderLogic;
                                this.$draggingStart = booleanRef;
                                this.$finishInteraction = dragInteraction;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass2(this.$rangeSliderLogic, this.$draggingStart, this.$finishInteraction, continuation);
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
                                        this.label = 1;
                                        if (this.$rangeSliderLogic.activeInteraction(this.$draggingStart.element).emit(this.$finishInteraction, this) == coroutine_suspended) {
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
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object $result) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                                this.label = 1;
                                if (ForEachGestureKt.awaitEachGesture(this.$this_pointerInput, new C00491(this.$isRtl, this.$maxPx, this.$rangeSliderLogic, this.$rawOffsetStart, $this$coroutineScope, this.$gestureEndAction, this.$rawOffsetEnd, this.$onDrag, null), this) == coroutine_suspended) {
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
            }) : $this$rangeSliderPressDragModifier;
        }

        public static final float getThumbRadius() {
            return ThumbRadius;
        }

        public static final float getTrackHeight() {
            return TrackHeight;
        }
    }
