package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.animation.core.FloatDecayAnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.gestures.snapping.SnapFlingBehaviorKt;
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.ComposerImplKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: AnchoredDraggable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000À\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\\\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u001ah\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0010\u001a\u00020\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007\u001aT\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u001a`\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0010\u001a\u00020\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007\u001aQ\u0010\u0014\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u001626\u0010\u0017\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00150\u0018H\u0086\b\u001a5\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0016\"\b\b\u0000\u0010\u0002*\u00020\u001f2\u001d\u0010 \u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020!\u0012\u0004\u0012\u00020\u00150\u0012¢\u0006\u0002\b\"\u001a\u0093\u0001\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\"\u0004\b\u0000\u0010\u00022\u0006\u0010$\u001a\u0002H\u00022!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u001c0\u00122\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001c0*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001c0,2#\b\u0002\u0010-\u001a\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00060\u0012H\u0007¢\u0006\u0002\u0010/\u001a¡\u0001\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\"\u0004\b\u0000\u0010\u00022\u0006\u0010$\u001a\u0002H\u00022\f\u00100\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00162!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u001c0\u00122\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001c0*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001c0,2#\b\u0002\u0010-\u001a\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00060\u0012H\u0007¢\u0006\u0002\u00101\u001a&\u00102\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u00103\u001a\u0002H\u0002H\u0086@¢\u0006\u0002\u00104\u001aR\u00105\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u00106\u001a\u00020\u001c2\u0006\u00107\u001a\u0002082\f\u00100\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00162\u0006\u00109\u001a\u0002H\u00022\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001c0*H\u0082@¢\u0006\u0002\u0010:\u001a6\u00105\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u00103\u001a\u0002H\u00022\u000e\b\u0002\u0010;\u001a\b\u0012\u0004\u0012\u00020\u001c0*H\u0086@¢\u0006\u0002\u0010<\u001aN\u0010=\u001a\u00020\u001c\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u00103\u001a\u0002H\u00022\u0006\u00106\u001a\u00020\u001c2\u000e\b\u0002\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001c0*2\u000e\b\u0002\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001c0,H\u0086@¢\u0006\u0002\u0010>\u001a^\u0010?\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00162\u0006\u0010@\u001a\u00020\u001c2\u0006\u00106\u001a\u00020\u001c2!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u001c0\u00122\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0(H\u0002¢\u0006\u0002\u0010A\u001a\u0014\u0010B\u001a\u00020\u001c*\u00020\u001c2\u0006\u0010C\u001a\u00020\u001cH\u0002\u001aF\u0010D\u001a\u00020\u0015\"\u0004\b\u0000\u0010E2\f\u0010F\u001a\b\u0012\u0004\u0012\u0002HE0(2\"\u0010\u0017\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002HE\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150G\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u0018H\u0082@¢\u0006\u0002\u0010H\u001a\u0014\u0010I\u001a\b\u0012\u0004\u0012\u0002H\u00020J\"\u0004\b\u0000\u0010\u0002H\u0002\u001aU\u0010W\u001a\u00020X\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010Y\u001a\u00020Z2!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u001c0\u00122\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001c0*H\u0000\u001aM\u0010[\u001a\u00020\\\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u001c0\u00122\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0(H\u0002\u001a\u0017\u0010_\u001a\u00020\u00152\f\u0010`\u001a\b\u0012\u0004\u0012\u00020S0(H\u0082\b\"\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00060\u0012X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010K\u001a\u000e\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020\u001c0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010M\u001a\u00020NX\u0080\u0004¢\u0006\n\n\u0002\u0010Q\u001a\u0004\bO\u0010P\"\u000e\u0010R\u001a\u00020SX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010T\u001a\u00020SX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010U\u001a\u00020SX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010V\u001a\u00020SX\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010]\u001a\b\u0012\u0004\u0012\u00020\u001c0,X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010^\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006a"}, d2 = {"anchoredDraggable", "Landroidx/compose/ui/Modifier;", ExifInterface.GPS_DIRECTION_TRUE, "state", "Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "reverseDirection", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "enabled", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "startDragImmediately", "AlwaysDrag", "Lkotlin/Function1;", "Landroidx/compose/ui/input/pointer/PointerType;", "forEach", "", "Landroidx/compose/foundation/gestures/DraggableAnchors;", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "key", "", "position", "DraggableAnchors", "", "builder", "Landroidx/compose/foundation/gestures/DraggableAnchorsConfig;", "Lkotlin/ExtensionFunctionType;", "AnchoredDraggableState", "initialValue", "positionalThreshold", "totalDistance", "velocityThreshold", "Lkotlin/Function0;", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "decayAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "confirmValueChange", "newValue", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;Lkotlin/jvm/functions/Function1;)Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "anchors", "(Ljava/lang/Object;Landroidx/compose/foundation/gestures/DraggableAnchors;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;Lkotlin/jvm/functions/Function1;)Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "snapTo", "targetValue", "(Landroidx/compose/foundation/gestures/AnchoredDraggableState;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateTo", "velocity", "anchoredDragScope", "Landroidx/compose/foundation/gestures/AnchoredDragScope;", "latestTarget", "(Landroidx/compose/foundation/gestures/AnchoredDraggableState;FLandroidx/compose/foundation/gestures/AnchoredDragScope;Landroidx/compose/foundation/gestures/DraggableAnchors;Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animationSpec", "(Landroidx/compose/foundation/gestures/AnchoredDraggableState;Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateToWithDecay", "(Landroidx/compose/foundation/gestures/AnchoredDraggableState;Ljava/lang/Object;FLandroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "computeTarget", "currentOffset", "(Landroidx/compose/foundation/gestures/DraggableAnchors;FFLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "coerceToTarget", TypedValues.AttributesType.S_TARGET, "restartable", "I", "inputs", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emptyDraggableAnchors", "Landroidx/compose/foundation/gestures/DefaultDraggableAnchors;", "GetOrNan", "", "AnchoredDraggableMinFlingVelocity", "Landroidx/compose/ui/unit/Dp;", "getAnchoredDraggableMinFlingVelocity", "()F", "F", "ConfigurationMovedToModifier", "", "SettleWithVelocityDeprecated", "StartDragImmediatelyDeprecated", "ConfirmValueChangeDeprecated", "anchoredDraggableFlingBehavior", "Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "density", "Landroidx/compose/ui/unit/Density;", "AnchoredDraggableLayoutInfoProvider", "Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;", "NoOpDecayAnimationSpec", "DEBUG", "debugLog", "generateMsg", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AnchoredDraggableKt {
    private static final String ConfigurationMovedToModifier = "This constructor of AnchoredDraggableState has been deprecated. Please pass thresholds and animation specs to AnchoredDraggableDefaults.flingBehavior(..) instead, which can be passed to Modifier.anchoredDraggable.";
    private static final String ConfirmValueChangeDeprecated = "confirmValueChange is deprecated without replacement. Rather than relying on a callback to veto state changes, the anchor set should not include disallowed anchors. See androidx.compose.foundation.samples.AnchoredDraggableDynamicAnchorsSample for an example of using dynamic anchors over confirmValueChange.";
    private static final boolean DEBUG = false;
    private static final String SettleWithVelocityDeprecated = "settle does not accept a velocity anymore. Please use FlingBehavior#performFling instead. See AnchoredDraggableSample.kt for example usages.";
    private static final String StartDragImmediatelyDeprecated = "startDragImmediately has been removed without replacement. Modifier.anchoredDraggable sets startDragImmediately to true by default when animations are running.";
    private static final Function1<PointerType, Boolean> AlwaysDrag = new Function1() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return Boolean.valueOf(AnchoredDraggableKt.AlwaysDrag$lambda$0((PointerType) obj));
        }
    };
    private static final Function1<Integer, Float> GetOrNan = new Function1() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return Float.valueOf(AnchoredDraggableKt.GetOrNan$lambda$0(((Integer) obj).intValue()));
        }
    };
    private static final float AnchoredDraggableMinFlingVelocity = Dp.m7905constructorimpl(ComposerImplKt.nodeKey);
    private static final DecayAnimationSpec<Float> NoOpDecayAnimationSpec = DecayAnimationSpecKt.generateDecayAnimationSpec(new FloatDecayAnimationSpec() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableKt$NoOpDecayAnimationSpec$1
        private final float absVelocityThreshold;

        @Override // androidx.compose.animation.core.FloatDecayAnimationSpec
        public float getAbsVelocityThreshold() {
            return this.absVelocityThreshold;
        }

        @Override // androidx.compose.animation.core.FloatDecayAnimationSpec
        public float getValueFromNanos(long playTimeNanos, float initialValue, float initialVelocity) {
            return 0.0f;
        }

        @Override // androidx.compose.animation.core.FloatDecayAnimationSpec
        public long getDurationNanos(float initialValue, float initialVelocity) {
            return 0L;
        }

        @Override // androidx.compose.animation.core.FloatDecayAnimationSpec
        public float getVelocityFromNanos(long playTimeNanos, float initialValue, float initialVelocity) {
            return 0.0f;
        }

        @Override // androidx.compose.animation.core.FloatDecayAnimationSpec
        public float getTargetValue(float initialValue, float initialVelocity) {
            return 0.0f;
        }
    });

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableKt", f = "AnchoredDraggable.kt", i = {0, 0}, l = {1383}, m = "animateToWithDecay", n = {"remainingVelocity", "velocity"}, s = {"L$0", "F$0"}, v = 1)
    static final class C01861<T> extends ContinuationImpl {
        float F$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C01861(Continuation<? super C01861> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnchoredDraggableKt.animateToWithDecay(null, null, 0.0f, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableKt", f = "AnchoredDraggable.kt", i = {}, l = {1547}, m = "restartable", n = {}, s = {}, v = 1)
    static final class C01871<I> extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C01871(Continuation<? super C01871> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnchoredDraggableKt.restartable(null, null, this);
        }
    }

    public static final <T> Modifier anchoredDraggable(Modifier $this$anchoredDraggable, AnchoredDraggableState<T> anchoredDraggableState, boolean reverseDirection, Orientation orientation, boolean enabled, MutableInteractionSource interactionSource, OverscrollEffect overscrollEffect, FlingBehavior flingBehavior) {
        return $this$anchoredDraggable.then(new AnchoredDraggableElement(anchoredDraggableState, orientation, enabled, Boolean.valueOf(reverseDirection), interactionSource, null, overscrollEffect, flingBehavior, 32, null));
    }

    @Deprecated(message = StartDragImmediatelyDeprecated)
    public static final <T> Modifier anchoredDraggable(Modifier $this$anchoredDraggable, AnchoredDraggableState<T> anchoredDraggableState, boolean reverseDirection, Orientation orientation, boolean enabled, MutableInteractionSource interactionSource, OverscrollEffect overscrollEffect, boolean startDragImmediately, FlingBehavior flingBehavior) {
        return $this$anchoredDraggable.then(new AnchoredDraggableElement(anchoredDraggableState, orientation, enabled, Boolean.valueOf(reverseDirection), interactionSource, Boolean.valueOf(startDragImmediately), overscrollEffect, flingBehavior));
    }

    public static final <T> Modifier anchoredDraggable(Modifier $this$anchoredDraggable, AnchoredDraggableState<T> anchoredDraggableState, Orientation orientation, boolean enabled, MutableInteractionSource interactionSource, OverscrollEffect overscrollEffect, FlingBehavior flingBehavior) {
        return $this$anchoredDraggable.then(new AnchoredDraggableElement(anchoredDraggableState, orientation, enabled, null, interactionSource, null, overscrollEffect, flingBehavior, 32, null));
    }

    @Deprecated(message = StartDragImmediatelyDeprecated)
    public static final <T> Modifier anchoredDraggable(Modifier $this$anchoredDraggable, AnchoredDraggableState<T> anchoredDraggableState, Orientation orientation, boolean enabled, MutableInteractionSource interactionSource, OverscrollEffect overscrollEffect, boolean startDragImmediately, FlingBehavior flingBehavior) {
        return $this$anchoredDraggable.then(new AnchoredDraggableElement(anchoredDraggableState, orientation, enabled, null, interactionSource, Boolean.valueOf(startDragImmediately), overscrollEffect, flingBehavior));
    }

    static final boolean AlwaysDrag$lambda$0(PointerType it) {
        return true;
    }

    public static final <T> void forEach(DraggableAnchors<T> draggableAnchors, Function2<? super T, ? super Float, Unit> function2) {
        int size = draggableAnchors.getSize();
        for (int i = 0; i < size; i++) {
            Object key = draggableAnchors.anchorAt(i);
            if (key == null) {
                throw new IllegalArgumentException(("There was no key at index " + i + ". Please report a bug.").toString());
            }
            function2.invoke(key, Float.valueOf(draggableAnchors.positionAt(i)));
        }
    }

    public static final <T> DraggableAnchors<T> DraggableAnchors(Function1<? super DraggableAnchorsConfig<T>, Unit> function1) {
        DraggableAnchorsConfig config = new DraggableAnchorsConfig();
        function1.invoke(config);
        return new DefaultDraggableAnchors(config.buildKeys$foundation(), config.buildPositions$foundation());
    }

    public static /* synthetic */ AnchoredDraggableState AnchoredDraggableState$default(Object obj, Function1 function1, Function0 function0, AnimationSpec animationSpec, DecayAnimationSpec decayAnimationSpec, Function1 function2, int i, Object obj2) {
        Function1 function3;
        if ((i & 32) == 0) {
            function3 = function2;
        } else {
            function3 = new Function1() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return Boolean.valueOf(AnchoredDraggableKt.AnchoredDraggableState$lambda$0(obj3));
                }
            };
        }
        return AnchoredDraggableState(obj, function1, function0, animationSpec, decayAnimationSpec, function3);
    }

    static final boolean AnchoredDraggableState$lambda$0(Object it) {
        return true;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = ConfigurationMovedToModifier)
    public static final <T> AnchoredDraggableState<T> AnchoredDraggableState(T t, Function1<? super Float, Float> function1, Function0<Float> function0, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, Function1<? super T, Boolean> function2) {
        AnchoredDraggableState<T> anchoredDraggableState = new AnchoredDraggableState<>(t, function2);
        anchoredDraggableState.setPositionalThreshold$foundation(function1);
        anchoredDraggableState.setVelocityThreshold$foundation(function0);
        anchoredDraggableState.setSnapAnimationSpec$foundation(animationSpec);
        anchoredDraggableState.setDecayAnimationSpec$foundation(decayAnimationSpec);
        return anchoredDraggableState;
    }

    public static /* synthetic */ AnchoredDraggableState AnchoredDraggableState$default(Object obj, DraggableAnchors draggableAnchors, Function1 function1, Function0 function0, AnimationSpec animationSpec, DecayAnimationSpec decayAnimationSpec, Function1 function2, int i, Object obj2) {
        Function1 function3;
        if ((i & 64) == 0) {
            function3 = function2;
        } else {
            function3 = new Function1() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return Boolean.valueOf(AnchoredDraggableKt.AnchoredDraggableState$lambda$2(obj3));
                }
            };
        }
        return AnchoredDraggableState(obj, draggableAnchors, function1, function0, animationSpec, decayAnimationSpec, function3);
    }

    static final boolean AnchoredDraggableState$lambda$2(Object it) {
        return true;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = ConfigurationMovedToModifier)
    public static final <T> AnchoredDraggableState<T> AnchoredDraggableState(T t, DraggableAnchors<T> draggableAnchors, Function1<? super Float, Float> function1, Function0<Float> function0, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, Function1<? super T, Boolean> function2) {
        AnchoredDraggableState<T> anchoredDraggableState = new AnchoredDraggableState<>(t, draggableAnchors, function2);
        anchoredDraggableState.setPositionalThreshold$foundation(function1);
        anchoredDraggableState.setVelocityThreshold$foundation(function0);
        anchoredDraggableState.setSnapAnimationSpec$foundation(animationSpec);
        anchoredDraggableState.setDecayAnimationSpec$foundation(decayAnimationSpec);
        return anchoredDraggableState;
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableKt$snapTo$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0006\u001a\u0002H\u0002H\n"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/gestures/AnchoredDragScope;", "anchors", "Landroidx/compose/foundation/gestures/DraggableAnchors;", "latestTarget"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableKt$snapTo$2", f = "AnchoredDraggable.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01892<T> extends SuspendLambda implements Function4<AnchoredDragScope, DraggableAnchors<T>, T, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ Object L$2;
        int label;

        C01892(Continuation<? super C01892> continuation) {
            super(4, continuation);
        }

        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(AnchoredDragScope anchoredDragScope, DraggableAnchors<T> draggableAnchors, T t, Continuation<? super Unit> continuation) {
            C01892 c01892 = new C01892(continuation);
            c01892.L$0 = anchoredDragScope;
            c01892.L$1 = draggableAnchors;
            c01892.L$2 = t;
            return c01892.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    AnchoredDragScope $this$anchoredDrag = (AnchoredDragScope) this.L$0;
                    DraggableAnchors draggableAnchors = (DraggableAnchors) this.L$1;
                    Object latestTarget = this.L$2;
                    float targetOffset = draggableAnchors.positionOf(latestTarget);
                    if (!Float.isNaN(targetOffset)) {
                        AnchoredDragScope.dragTo$default($this$anchoredDrag, targetOffset, 0.0f, 2, null);
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public static final <T> Object snapTo(AnchoredDraggableState<T> anchoredDraggableState, T t, Continuation<? super Unit> continuation) {
        Object objAnchoredDrag$default = AnchoredDraggableState.anchoredDrag$default(anchoredDraggableState, t, null, new C01892(null), continuation, 2, null);
        return objAnchoredDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnchoredDrag$default : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:16:0x0047  */
    public static final <T> Object animateTo(AnchoredDraggableState<T> anchoredDraggableState, float velocity, final AnchoredDragScope anchoredDragScope, DraggableAnchors<T> draggableAnchors, T t, AnimationSpec<Float> animationSpec, Continuation<? super Unit> continuation) {
        float targetOffset = draggableAnchors.positionOf(t);
        final Ref.FloatRef prev = new Ref.FloatRef();
        prev.element = Float.isNaN(anchoredDraggableState.getOffset()) ? 0.0f : anchoredDraggableState.getOffset();
        if (!Float.isNaN(targetOffset)) {
            if (!(prev.element == targetOffset)) {
                Object objAnimate = SuspendAnimationKt.animate(prev.element, targetOffset, velocity, animationSpec, new Function2() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return AnchoredDraggableKt.animateTo$lambda$0$1(anchoredDragScope, prev, ((Float) obj).floatValue(), ((Float) obj2).floatValue());
                    }
                }, continuation);
                if (objAnimate == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return objAnimate;
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit animateTo$lambda$0$1(AnchoredDragScope $this_with, Ref.FloatRef $prev, float value, float velocity) {
        $this_with.dragTo(value, velocity);
        $prev.element = value;
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object animateTo$default(AnchoredDraggableState anchoredDraggableState, Object obj, AnimationSpec animationSpec, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            if (anchoredDraggableState.getUsePreModifierChangeBehavior$foundation()) {
                animationSpec = anchoredDraggableState.getSnapAnimationSpec();
            } else {
                animationSpec = AnchoredDraggableDefaults.INSTANCE.getSnapAnimationSpec();
            }
        }
        return animateTo(anchoredDraggableState, obj, animationSpec, continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableKt$animateTo$4, reason: invalid class name */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0006\u001a\u0002H\u0002H\n"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/gestures/AnchoredDragScope;", "anchors", "Landroidx/compose/foundation/gestures/DraggableAnchors;", "latestTarget"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableKt$animateTo$4", f = "AnchoredDraggable.kt", i = {}, l = {1347}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass4<T> extends SuspendLambda implements Function4<AnchoredDragScope, DraggableAnchors<T>, T, Continuation<? super Unit>, Object> {
        final /* synthetic */ AnimationSpec<Float> $animationSpec;
        final /* synthetic */ AnchoredDraggableState<T> $this_animateTo;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(AnchoredDraggableState<T> anchoredDraggableState, AnimationSpec<Float> animationSpec, Continuation<? super AnonymousClass4> continuation) {
            super(4, continuation);
            this.$this_animateTo = anchoredDraggableState;
            this.$animationSpec = animationSpec;
        }

        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(AnchoredDragScope anchoredDragScope, DraggableAnchors<T> draggableAnchors, T t, Continuation<? super Unit> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.$this_animateTo, this.$animationSpec, continuation);
            anonymousClass4.L$0 = anchoredDragScope;
            anonymousClass4.L$1 = draggableAnchors;
            anonymousClass4.L$2 = t;
            return anonymousClass4.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    AnchoredDragScope $this$anchoredDrag = (AnchoredDragScope) this.L$0;
                    DraggableAnchors anchors = (DraggableAnchors) this.L$1;
                    Object latestTarget = this.L$2;
                    this.L$0 = null;
                    this.L$1 = null;
                    this.label = 1;
                    if (AnchoredDraggableKt.animateTo(this.$this_animateTo, this.$this_animateTo.getLastVelocity(), $this$anchoredDrag, anchors, latestTarget, this.$animationSpec, this) == coroutine_suspended) {
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

    public static final <T> Object animateTo(AnchoredDraggableState<T> anchoredDraggableState, T t, AnimationSpec<Float> animationSpec, Continuation<? super Unit> continuation) {
        Object objAnchoredDrag$default = AnchoredDraggableState.anchoredDrag$default(anchoredDraggableState, t, null, new AnonymousClass4(anchoredDraggableState, animationSpec, null), continuation, 2, null);
        return objAnchoredDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnchoredDrag$default : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final <T> Object animateToWithDecay(AnchoredDraggableState<T> anchoredDraggableState, T t, float velocity, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, Continuation<? super Float> continuation) {
        C01861 c01861;
        float velocity2;
        Ref.FloatRef remainingVelocity;
        if (continuation instanceof C01861) {
            c01861 = (C01861) continuation;
            if ((c01861.label & Integer.MIN_VALUE) != 0) {
                c01861.label -= Integer.MIN_VALUE;
            } else {
                c01861 = new C01861(continuation);
            }
        } else {
            c01861 = new C01861(continuation);
        }
        C01861 c01862 = c01861;
        Object $result = c01862.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c01862.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Ref.FloatRef remainingVelocity2 = new Ref.FloatRef();
                remainingVelocity2.element = velocity;
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(anchoredDraggableState, velocity, animationSpec, remainingVelocity2, decayAnimationSpec, null);
                c01862.L$0 = remainingVelocity2;
                c01862.F$0 = velocity;
                c01862.label = 1;
                if (AnchoredDraggableState.anchoredDrag$default(anchoredDraggableState, t, null, anonymousClass2, c01862, 2, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                velocity2 = velocity;
                remainingVelocity = remainingVelocity2;
                break;
                break;
            case 1:
                velocity2 = c01862.F$0;
                remainingVelocity = (Ref.FloatRef) c01862.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Boxing.boxFloat(velocity2 - remainingVelocity.element);
    }

    public static /* synthetic */ Object animateToWithDecay$default(AnchoredDraggableState anchoredDraggableState, Object obj, float f, AnimationSpec animationSpec, DecayAnimationSpec decayAnimationSpec, Continuation continuation, int i, Object obj2) {
        AnimationSpec animationSpec2;
        DecayAnimationSpec decayAnimationSpec2;
        DecayAnimationSpec<Float> decayAnimationSpec3;
        AnimationSpec<Float> snapAnimationSpec;
        if ((i & 4) == 0) {
            animationSpec2 = animationSpec;
        } else {
            if (anchoredDraggableState.getUsePreModifierChangeBehavior$foundation()) {
                snapAnimationSpec = anchoredDraggableState.getSnapAnimationSpec();
            } else {
                snapAnimationSpec = AnchoredDraggableDefaults.INSTANCE.getSnapAnimationSpec();
            }
            animationSpec2 = snapAnimationSpec;
        }
        if ((i & 8) == 0) {
            decayAnimationSpec2 = decayAnimationSpec;
        } else {
            if (anchoredDraggableState.getUsePreModifierChangeBehavior$foundation()) {
                decayAnimationSpec3 = anchoredDraggableState.getDecayAnimationSpec();
            } else {
                decayAnimationSpec3 = AnchoredDraggableDefaults.INSTANCE.getDecayAnimationSpec();
            }
            decayAnimationSpec2 = decayAnimationSpec3;
        }
        return animateToWithDecay(anchoredDraggableState, obj, f, animationSpec2, decayAnimationSpec2, continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$2, reason: invalid class name */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0006\u001a\u0002H\u0002H\n"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/gestures/AnchoredDragScope;", "anchors", "Landroidx/compose/foundation/gestures/DraggableAnchors;", "latestTarget"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$2", f = "AnchoredDraggable.kt", i = {}, l = {1394, 1412, 1436}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2<T> extends SuspendLambda implements Function4<AnchoredDragScope, DraggableAnchors<T>, T, Continuation<? super Unit>, Object> {
        final /* synthetic */ DecayAnimationSpec<Float> $decayAnimationSpec;
        final /* synthetic */ Ref.FloatRef $remainingVelocity;
        final /* synthetic */ AnimationSpec<Float> $snapAnimationSpec;
        final /* synthetic */ AnchoredDraggableState<T> $this_animateToWithDecay;
        final /* synthetic */ float $velocity;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(AnchoredDraggableState<T> anchoredDraggableState, float f, AnimationSpec<Float> animationSpec, Ref.FloatRef floatRef, DecayAnimationSpec<Float> decayAnimationSpec, Continuation<? super AnonymousClass2> continuation) {
            super(4, continuation);
            this.$this_animateToWithDecay = anchoredDraggableState;
            this.$velocity = f;
            this.$snapAnimationSpec = animationSpec;
            this.$remainingVelocity = floatRef;
            this.$decayAnimationSpec = decayAnimationSpec;
        }

        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(AnchoredDragScope anchoredDragScope, DraggableAnchors<T> draggableAnchors, T t, Continuation<? super Unit> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_animateToWithDecay, this.$velocity, this.$snapAnimationSpec, this.$remainingVelocity, this.$decayAnimationSpec, continuation);
            anonymousClass2.L$0 = anchoredDragScope;
            anonymousClass2.L$1 = draggableAnchors;
            anonymousClass2.L$2 = t;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:48:0x0106  */
        /* JADX WARN: Code duplicated, block: B:51:0x011d  */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object $result2;
            Object $result3;
            Object $result4;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    final AnchoredDragScope $this$anchoredDrag = (AnchoredDragScope) this.L$0;
                    DraggableAnchors draggableAnchors = (DraggableAnchors) this.L$1;
                    Object latestTarget = this.L$2;
                    final float targetOffset = draggableAnchors.positionOf(latestTarget);
                    if (!Float.isNaN(targetOffset)) {
                        final Ref.FloatRef prev = new Ref.FloatRef();
                        prev.element = Float.isNaN(this.$this_animateToWithDecay.getOffset()) ? 0.0f : this.$this_animateToWithDecay.getOffset();
                        boolean canDecayToTarget = false;
                        if (!(prev.element == targetOffset)) {
                            if (this.$velocity * (targetOffset - prev.element) < 0.0f) {
                                this.L$0 = null;
                                this.L$1 = null;
                                this.label = 1;
                                if (AnchoredDraggableKt.animateTo(this.$this_animateToWithDecay, this.$velocity, $this$anchoredDrag, draggableAnchors, latestTarget, this.$snapAnimationSpec, this) != coroutine_suspended) {
                                    $result2 = $result;
                                    this.$remainingVelocity.element = 0.0f;
                                }
                            } else {
                                if (this.$velocity == 0.0f) {
                                    this.L$0 = null;
                                    this.L$1 = null;
                                    this.label = 1;
                                    if (AnchoredDraggableKt.animateTo(this.$this_animateToWithDecay, this.$velocity, $this$anchoredDrag, draggableAnchors, latestTarget, this.$snapAnimationSpec, this) != coroutine_suspended) {
                                        $result2 = $result;
                                        this.$remainingVelocity.element = 0.0f;
                                    }
                                } else {
                                    float projectedDecayOffset = DecayAnimationSpecKt.calculateTargetValue(this.$decayAnimationSpec, prev.element, this.$velocity);
                                    float f = this.$velocity;
                                    if (this.$velocity > 0.0f) {
                                        if (projectedDecayOffset >= targetOffset) {
                                            canDecayToTarget = true;
                                        }
                                    } else if (projectedDecayOffset <= targetOffset) {
                                        canDecayToTarget = true;
                                    }
                                    if (canDecayToTarget) {
                                        AnimationState animationStateAnimationState$default = AnimationStateKt.AnimationState$default(prev.element, this.$velocity, 0L, 0L, false, 28, null);
                                        DecayAnimationSpec<Float> decayAnimationSpec = this.$decayAnimationSpec;
                                        final Ref.FloatRef floatRef = this.$remainingVelocity;
                                        this.L$0 = null;
                                        this.L$1 = null;
                                        this.label = 2;
                                        if (SuspendAnimationKt.animateDecay$default(animationStateAnimationState$default, decayAnimationSpec, false, new Function1() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableKt$animateToWithDecay$2$$ExternalSyntheticLambda0
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                return AnchoredDraggableKt.AnonymousClass2.invokeSuspend$lambda$2(targetOffset, prev, $this$anchoredDrag, floatRef, (AnimationScope) obj);
                                            }
                                        }, this, 2, null) != coroutine_suspended) {
                                            $result4 = $result;
                                        }
                                    } else {
                                        this.L$0 = null;
                                        this.L$1 = null;
                                        this.label = 3;
                                        if (AnchoredDraggableKt.animateTo(this.$this_animateToWithDecay, this.$velocity, $this$anchoredDrag, draggableAnchors, latestTarget, this.$snapAnimationSpec, this) != coroutine_suspended) {
                                            $result3 = $result;
                                            this.$remainingVelocity.element = 0.0f;
                                        }
                                    }
                                }
                            }
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    $result2 = $result;
                    ResultKt.throwOnFailure($result2);
                    this.$remainingVelocity.element = 0.0f;
                    return Unit.INSTANCE;
                case 2:
                    $result4 = $result;
                    ResultKt.throwOnFailure($result4);
                    return Unit.INSTANCE;
                case 3:
                    $result3 = $result;
                    ResultKt.throwOnFailure($result3);
                    this.$remainingVelocity.element = 0.0f;
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        static final Unit invokeSuspend$lambda$2(float $targetOffset, Ref.FloatRef $prev, AnchoredDragScope $$this$anchoredDrag, Ref.FloatRef $remainingVelocity, AnimationScope $this$animateDecay) {
            if ((((Number) $this$animateDecay.getValue()).floatValue() < $targetOffset && $prev.element > $targetOffset) || (((Number) $this$animateDecay.getValue()).floatValue() > $targetOffset && $prev.element < $targetOffset)) {
                float finalValue = AnchoredDraggableKt.coerceToTarget(((Number) $this$animateDecay.getValue()).floatValue(), $targetOffset);
                $$this$anchoredDrag.dragTo(finalValue, ((Number) $this$animateDecay.getVelocity()).floatValue());
                $remainingVelocity.element = Float.isNaN(((Number) $this$animateDecay.getVelocity()).floatValue()) ? 0.0f : ((Number) $this$animateDecay.getVelocity()).floatValue();
                $prev.element = finalValue;
                $this$animateDecay.cancelAnimation();
            } else {
                $$this$anchoredDrag.dragTo(((Number) $this$animateDecay.getValue()).floatValue(), ((Number) $this$animateDecay.getVelocity()).floatValue());
                $remainingVelocity.element = ((Number) $this$animateDecay.getVelocity()).floatValue();
                $prev.element = ((Number) $this$animateDecay.getValue()).floatValue();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0091, code lost:
    
        if (r3 != false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0096, code lost:
    
        if (r3 != false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x009b, code lost:
    
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:?, code lost:
    
        return r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> T computeTarget(androidx.compose.foundation.gestures.DraggableAnchors<T> r16, float r17, float r18, kotlin.jvm.functions.Function1<? super java.lang.Float, java.lang.Float> r19, kotlin.jvm.functions.Function0<java.lang.Float> r20) {
        /*
            r0 = r17
            r1 = r16
            boolean r2 = java.lang.Float.isNaN(r0)
            if (r2 != 0) goto La2
            float r2 = java.lang.Math.abs(r18)
            r3 = 0
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            r4 = 0
            r5 = 1
            if (r2 <= 0) goto L17
            r2 = r5
            goto L18
        L17:
            r2 = r4
        L18:
            if (r2 == 0) goto L20
            int r3 = (r18 > r3 ? 1 : (r18 == r3 ? 0 : -1))
            if (r3 <= 0) goto L20
            r3 = r5
            goto L21
        L20:
            r3 = r4
        L21:
            if (r2 != 0) goto L2e
            java.lang.Object r4 = r1.closestAnchor(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            r12 = r19
            goto L9b
        L2e:
            float r6 = java.lang.Math.abs(r18)
            java.lang.Object r7 = r20.invoke()
            java.lang.Number r7 = (java.lang.Number) r7
            float r7 = r7.floatValue()
            float r7 = java.lang.Math.abs(r7)
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 < 0) goto L4e
            java.lang.Object r4 = r1.closestAnchor(r0, r3)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            r12 = r19
            goto L9b
        L4e:
            java.lang.Object r6 = r1.closestAnchor(r0, r4)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            float r7 = r1.positionOf(r6)
            java.lang.Object r8 = r1.closestAnchor(r0, r5)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            float r9 = r1.positionOf(r8)
            float r10 = r7 - r9
            float r10 = java.lang.Math.abs(r10)
            java.lang.Float r11 = java.lang.Float.valueOf(r10)
            r12 = r19
            java.lang.Object r11 = r12.invoke(r11)
            java.lang.Number r11 = (java.lang.Number) r11
            float r11 = r11.floatValue()
            float r11 = java.lang.Math.abs(r11)
            if (r3 == 0) goto L82
            r13 = r7
            goto L83
        L82:
            r13 = r9
        L83:
            float r14 = r13 - r0
            float r14 = java.lang.Math.abs(r14)
            int r15 = (r14 > r11 ? 1 : (r14 == r11 ? 0 : -1))
            if (r15 < 0) goto L8f
            r4 = r5
        L8f:
            if (r4 != r5) goto L94
            if (r3 == 0) goto L98
            goto L9a
        L94:
            if (r4 != 0) goto L9c
            if (r3 == 0) goto L9a
        L98:
            r4 = r6
            goto L9b
        L9a:
            r4 = r8
        L9b:
            return r4
        L9c:
            kotlin.NoWhenBranchMatchedException r4 = new kotlin.NoWhenBranchMatchedException
            r4.<init>()
            throw r4
        La2:
            r12 = r19
            r2 = 0
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "The offset provided to computeTarget must not be NaN."
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableKt.computeTarget(androidx.compose.foundation.gestures.DraggableAnchors, float, float, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float coerceToTarget(float $this$coerceToTarget, float target) {
        if (target == 0.0f) {
            return 0.0f;
        }
        return target > 0.0f ? RangesKt.coerceAtMost($this$coerceToTarget, target) : RangesKt.coerceAtLeast($this$coerceToTarget, target);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <I> Object restartable(Function0<? extends I> function0, Function2<? super I, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        C01871 c01871;
        if (continuation instanceof C01871) {
            c01871 = (C01871) continuation;
            if ((c01871.label & Integer.MIN_VALUE) != 0) {
                c01871.label -= Integer.MIN_VALUE;
            } else {
                c01871 = new C01871(continuation);
            }
        } else {
            c01871 = new C01871(continuation);
        }
        Object $result = c01871.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (c01871.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    C01882 c01882 = new C01882(function0, function2, null);
                    c01871.label = 1;
                    if (CoroutineScopeKt.coroutineScope(c01882, c01871) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (AnchoredDragFinishedSignal e) {
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2", f = "AnchoredDraggable.kt", i = {}, l = {1549}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01882 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<I, Continuation<? super Unit>, Object> $block;
        final /* synthetic */ Function0<I> $inputs;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C01882(Function0<? extends I> function0, Function2<? super I, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super C01882> continuation) {
            super(2, continuation);
            this.$inputs = function0;
            this.$block = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01882 c01882 = new C01882(this.$inputs, this.$block, continuation);
            c01882.L$0 = obj;
            return c01882;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01882) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    Ref.ObjectRef previousDrag = new Ref.ObjectRef();
                    this.label = 1;
                    if (SnapshotStateKt.snapshotFlow(this.$inputs).collect(new AnonymousClass1(previousDrag, $this$coroutineScope, this.$block), this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: AnchoredDraggable.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        static final class AnonymousClass1<T> implements FlowCollector {
            final /* synthetic */ CoroutineScope $$this$coroutineScope;
            final /* synthetic */ Function2<I, Continuation<? super Unit>, Object> $block;
            final /* synthetic */ Ref.ObjectRef<Job> $previousDrag;

            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(Ref.ObjectRef<Job> objectRef, CoroutineScope coroutineScope, Function2<? super I, ? super Continuation<? super Unit>, ? extends Object> function2) {
                this.$previousDrag = objectRef;
                this.$$this$coroutineScope = coroutineScope;
                this.$block = function2;
            }

            /* JADX WARN: Code duplicated, block: B:7:0x0014  */
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(I i, Continuation<? super Unit> continuation) {
                AnchoredDraggableKt$restartable$2$1$emit$1 anchoredDraggableKt$restartable$2$1$emit$1;
                AnonymousClass1<T> anonymousClass1;
                Object obj;
                AnonymousClass1<T> anonymousClass2;
                Object obj2;
                if (continuation instanceof AnchoredDraggableKt$restartable$2$1$emit$1) {
                    anchoredDraggableKt$restartable$2$1$emit$1 = (AnchoredDraggableKt$restartable$2$1$emit$1) continuation;
                    if ((anchoredDraggableKt$restartable$2$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                        anchoredDraggableKt$restartable$2$1$emit$1.label -= Integer.MIN_VALUE;
                    } else {
                        anchoredDraggableKt$restartable$2$1$emit$1 = new AnchoredDraggableKt$restartable$2$1$emit$1(this, continuation);
                    }
                } else {
                    anchoredDraggableKt$restartable$2$1$emit$1 = new AnchoredDraggableKt$restartable$2$1$emit$1(this, continuation);
                }
                Object obj3 = anchoredDraggableKt$restartable$2$1$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (anchoredDraggableKt$restartable$2$1$emit$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj3);
                        anonymousClass1 = this;
                        Job job = anonymousClass1.$previousDrag.element;
                        obj2 = i;
                        if (job != null) {
                            job.cancel((CancellationException) new AnchoredDragFinishedSignal());
                            anchoredDraggableKt$restartable$2$1$emit$1.L$0 = i;
                            anchoredDraggableKt$restartable$2$1$emit$1.L$1 = job;
                            anchoredDraggableKt$restartable$2$1$emit$1.label = 1;
                            if (job.join(anchoredDraggableKt$restartable$2$1$emit$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            obj = i;
                            anonymousClass2 = anonymousClass1;
                            Object obj4 = obj;
                            anonymousClass1 = anonymousClass2;
                            obj2 = obj4;
                        }
                        anonymousClass1.$previousDrag.element = (T) BuildersKt__Builders_commonKt.launch$default(anonymousClass1.$$this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new C00132(anonymousClass1.$block, obj2, anonymousClass1.$$this$coroutineScope, null), 1, null);
                        return Unit.INSTANCE;
                    case 1:
                        anonymousClass2 = this;
                        Object obj5 = anchoredDraggableKt$restartable$2$1$emit$1.L$0;
                        ResultKt.throwOnFailure(obj3);
                        obj = obj5;
                        Object obj6 = obj;
                        anonymousClass1 = anonymousClass2;
                        obj2 = obj6;
                        anonymousClass1.$previousDrag.element = (T) BuildersKt__Builders_commonKt.launch$default(anonymousClass1.$$this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new C00132(anonymousClass1.$block, obj2, anonymousClass1.$$this$coroutineScope, null), 1, null);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2$1$2, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: AnchoredDraggable.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableKt$restartable$2$1$2", f = "AnchoredDraggable.kt", i = {}, l = {1556}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class C00132 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ CoroutineScope $$this$coroutineScope;
                final /* synthetic */ Function2<I, Continuation<? super Unit>, Object> $block;
                final /* synthetic */ I $latestInputs;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                C00132(Function2<? super I, ? super Continuation<? super Unit>, ? extends Object> function2, I i, CoroutineScope coroutineScope, Continuation<? super C00132> continuation) {
                    super(2, continuation);
                    this.$block = function2;
                    this.$latestInputs = i;
                    this.$$this$coroutineScope = coroutineScope;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00132(this.$block, this.$latestInputs, this.$$this$coroutineScope, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00132) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Type inference fix 'apply assigned field type' failed
                java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                 */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            Function2<I, Continuation<? super Unit>, Object> function2 = this.$block;
                            I i = this.$latestInputs;
                            this.label = 1;
                            if (function2.invoke(i, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            break;
                        case 1:
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    CoroutineScopeKt.cancel(this.$$this$coroutineScope, new AnchoredDragFinishedSignal());
                    return Unit.INSTANCE;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> DefaultDraggableAnchors<T> emptyDraggableAnchors() {
        return new DefaultDraggableAnchors<>(CollectionsKt.emptyList(), new float[0]);
    }

    static final float GetOrNan$lambda$0(int it) {
        return Float.NaN;
    }

    public static final float getAnchoredDraggableMinFlingVelocity() {
        return AnchoredDraggableMinFlingVelocity;
    }

    public static final <T> TargetedFlingBehavior anchoredDraggableFlingBehavior(AnchoredDraggableState<T> anchoredDraggableState, final Density density, Function1<? super Float, Float> function1, AnimationSpec<Float> animationSpec) {
        return SnapFlingBehaviorKt.snapFlingBehavior(AnchoredDraggableLayoutInfoProvider(anchoredDraggableState, function1, new Function0() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Float.valueOf(density.mo406toPx0680j_4(Dp.m7905constructorimpl(ComposerImplKt.nodeKey)));
            }
        }), NoOpDecayAnimationSpec, animationSpec);
    }

    private static final <T> SnapLayoutInfoProvider AnchoredDraggableLayoutInfoProvider(final AnchoredDraggableState<T> anchoredDraggableState, final Function1<? super Float, Float> function1, final Function0<Float> function0) {
        return new SnapLayoutInfoProvider() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableKt.AnchoredDraggableLayoutInfoProvider.1
            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateApproachOffset(float velocity, float decayOffset) {
                return 0.0f;
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateSnapOffset(float velocity) {
                Object targetValue;
                float currentOffset = anchoredDraggableState.requireOffset();
                Object proposedTargetValue = AnchoredDraggableKt.computeTarget(anchoredDraggableState.getAnchors(), currentOffset, velocity, function1, function0);
                if (((Boolean) anchoredDraggableState.getConfirmValueChange$foundation().invoke(proposedTargetValue)).booleanValue()) {
                    targetValue = proposedTargetValue;
                } else {
                    targetValue = anchoredDraggableState.getSettledValue();
                }
                return anchoredDraggableState.getAnchors().positionOf(targetValue) - currentOffset;
            }
        };
    }

    private static final void debugLog(Function0<String> function0) {
    }
}
