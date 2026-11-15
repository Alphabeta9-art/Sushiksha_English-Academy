package androidx.compose.material;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.foundation.gestures.DragScope;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.imageutils.JfifUtil;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: Swipeable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0017\u0018\u0000 w*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001wBD\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012#\b\u0002\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\b¢\u0006\u0004\b\r\u0010\u000eJ!\u0010=\u001a\u00020>2\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000,H\u0000¢\u0006\u0002\b@J8\u0010A\u001a\u00020>2\u0012\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000,2\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000,H\u0080@¢\u0006\u0004\bC\u0010DJ\u0016\u0010\\\u001a\u00020>2\u0006\u0010]\u001a\u00020\u0006H\u0082@¢\u0006\u0002\u0010^J$\u0010_\u001a\u00020>2\u0006\u0010]\u001a\u00020\u00062\f\u0010`\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@¢\u0006\u0002\u0010aJ\u0016\u0010n\u001a\u00020>2\u0006\u0010b\u001a\u00028\u0000H\u0087@¢\u0006\u0002\u0010oJ&\u0010p\u001a\u00020>2\u0006\u0010b\u001a\u00028\u00002\u000e\b\u0002\u0010q\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087@¢\u0006\u0002\u0010rJ\u0016\u0010s\u001a\u00020>2\u0006\u0010t\u001a\u00020\u0006H\u0086@¢\u0006\u0002\u0010^J\u000e\u0010u\u001a\u00020\u00062\u0006\u0010v\u001a\u00020\u0006R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R/\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R+\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00028\u00008F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R+\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060!8F¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00060!8F¢\u0006\u0006\u001a\u0004\b%\u0010#R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060+X\u0082\u0004¢\u0006\u0002\n\u0000RC\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000,2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000,8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b2\u0010\u001a\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R \u00103\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000,04X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00105\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010:\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u00107\"\u0004\b<\u00109RO\u0010F\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060E2\u0018\u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060E8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bK\u0010\u001a\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR+\u0010L\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00068@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bO\u0010P\u001a\u0004\bM\u00107\"\u0004\bN\u00109R/\u0010R\u001a\u0004\u0018\u00010Q2\b\u0010\u0013\u001a\u0004\u0018\u00010Q8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bW\u0010\u001a\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u0014\u0010X\u001a\u00020YX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010[R\u001a\u0010b\u001a\u00028\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\bc\u0010d\u001a\u0004\be\u0010\u0016R \u0010f\u001a\b\u0012\u0004\u0012\u00028\u00000g8FX\u0087\u0004¢\u0006\f\u0012\u0004\bh\u0010d\u001a\u0004\bi\u0010jR\u001a\u0010k\u001a\u00020\u00068FX\u0087\u0004¢\u0006\f\u0012\u0004\bl\u0010d\u001a\u0004\bm\u00107¨\u0006x"}, d2 = {"Landroidx/compose/material/SwipeableState;", ExifInterface.GPS_DIRECTION_TRUE, "", "initialValue", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmStateChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "newValue", "", "<init>", "(Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;)V", "getAnimationSpec$material", "()Landroidx/compose/animation/core/AnimationSpec;", "getConfirmStateChange$material", "()Lkotlin/jvm/functions/Function1;", "<set-?>", "currentValue", "getCurrentValue", "()Ljava/lang/Object;", "setCurrentValue", "(Ljava/lang/Object;)V", "currentValue$delegate", "Landroidx/compose/runtime/MutableState;", "isAnimationRunning", "()Z", "setAnimationRunning", "(Z)V", "isAnimationRunning$delegate", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/runtime/State;", "getOffset", "()Landroidx/compose/runtime/State;", "overflow", "getOverflow", "offsetState", "Landroidx/compose/runtime/MutableFloatState;", "overflowState", "absoluteOffset", "animationTarget", "Landroidx/compose/runtime/MutableState;", "", "anchors", "getAnchors$material", "()Ljava/util/Map;", "setAnchors$material", "(Ljava/util/Map;)V", "anchors$delegate", "latestNonEmptyAnchorsFlow", "Lkotlinx/coroutines/flow/Flow;", "minBound", "getMinBound$material", "()F", "setMinBound$material", "(F)V", "maxBound", "getMaxBound$material", "setMaxBound$material", "ensureInit", "", "newAnchors", "ensureInit$material", "processNewAnchors", "oldAnchors", "processNewAnchors$material", "(Ljava/util/Map;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/Function2;", "thresholds", "getThresholds$material", "()Lkotlin/jvm/functions/Function2;", "setThresholds$material", "(Lkotlin/jvm/functions/Function2;)V", "thresholds$delegate", "velocityThreshold", "getVelocityThreshold$material", "setVelocityThreshold$material", "velocityThreshold$delegate", "Landroidx/compose/runtime/MutableFloatState;", "Landroidx/compose/material/ResistanceConfig;", "resistance", "getResistance$material", "()Landroidx/compose/material/ResistanceConfig;", "setResistance$material", "(Landroidx/compose/material/ResistanceConfig;)V", "resistance$delegate", "draggableState", "Landroidx/compose/foundation/gestures/DraggableState;", "getDraggableState$material", "()Landroidx/compose/foundation/gestures/DraggableState;", "snapInternalToOffset", TypedValues.AttributesType.S_TARGET, "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateInternalToOffset", "spec", "(FLandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "targetValue", "getTargetValue$annotations", "()V", "getTargetValue", "progress", "Landroidx/compose/material/SwipeProgress;", "getProgress$annotations", "getProgress", "()Landroidx/compose/material/SwipeProgress;", "direction", "getDirection$annotations", "getDirection", "snapTo", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateTo", "anim", "(Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performFling", "velocity", "performDrag", "delta", "Companion", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated(message = "Material's Swipeable has been replaced by Foundation's AnchoredDraggable APIs. Please see developer.android.com for an overview of the changes and a migration guide.")
public class SwipeableState<T> {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final MutableFloatState absoluteOffset;

    /* JADX INFO: renamed from: anchors$delegate, reason: from kotlin metadata */
    private final MutableState anchors;
    private final AnimationSpec<Float> animationSpec;
    private final MutableState<Float> animationTarget;
    private final Function1<T, Boolean> confirmStateChange;

    /* JADX INFO: renamed from: currentValue$delegate, reason: from kotlin metadata */
    private final MutableState currentValue;
    private final DraggableState draggableState;

    /* JADX INFO: renamed from: isAnimationRunning$delegate, reason: from kotlin metadata */
    private final MutableState isAnimationRunning;
    private final Flow<Map<Float, T>> latestNonEmptyAnchorsFlow;
    private float maxBound;
    private float minBound;
    private final MutableFloatState offsetState;
    private final MutableFloatState overflowState;

    /* JADX INFO: renamed from: resistance$delegate, reason: from kotlin metadata */
    private final MutableState resistance;

    /* JADX INFO: renamed from: thresholds$delegate, reason: from kotlin metadata */
    private final MutableState thresholds;

    /* JADX INFO: renamed from: velocityThreshold$delegate, reason: from kotlin metadata */
    private final MutableFloatState velocityThreshold;

    public static /* synthetic */ void getDirection$annotations() {
    }

    public static /* synthetic */ void getProgress$annotations() {
    }

    public static /* synthetic */ void getTargetValue$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SwipeableState(T t, AnimationSpec<Float> animationSpec, Function1<? super T, Boolean> function1) {
        this.animationSpec = animationSpec;
        this.confirmStateChange = function1;
        this.currentValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
        this.isAnimationRunning = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.offsetState = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.overflowState = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.absoluteOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.animationTarget = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.anchors = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(MapsKt.emptyMap(), null, 2, null);
        final Flow $this$filter$iv = SnapshotStateKt.snapshotFlow(new Function0() { // from class: androidx.compose.material.SwipeableState$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.getAnchors$material();
            }
        });
        this.latestNonEmptyAnchorsFlow = FlowKt.take(new Flow<Map<Float, ? extends T>>() { // from class: androidx.compose.material.SwipeableState$special$$inlined$filter$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector collector, Continuation $completion) {
                Object objCollect = $this$filter$iv.collect(new AnonymousClass2(collector), $completion);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: androidx.compose.material.SwipeableState$special$$inlined$filter$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Emitters.kt */
            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* JADX INFO: renamed from: androidx.compose.material.SwipeableState$special$$inlined$filter$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material.SwipeableState$special$$inlined$filter$1$2", f = "Swipeable.kt", i = {}, l = {50}, m = "emit", n = {}, s = {}, v = 1)
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    Object L$1;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Code duplicated, block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object value, Continuation $completion) {
                    AnonymousClass1 anonymousClass1;
                    if ($completion instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) $completion;
                        if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label -= Integer.MIN_VALUE;
                        } else {
                            anonymousClass1 = new AnonymousClass1($completion);
                        }
                    } else {
                        anonymousClass1 = new AnonymousClass1($completion);
                    }
                    Object $result = anonymousClass1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (anonymousClass1.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            FlowCollector $this$filter_u24lambda_u240 = this.$this_unsafeFlow;
                            Map it = (Map) value;
                            if (!it.isEmpty()) {
                                anonymousClass1.label = 1;
                                if ($this$filter_u24lambda_u240.emit(value, anonymousClass1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
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
        }, 1);
        this.minBound = Float.NEGATIVE_INFINITY;
        this.maxBound = Float.POSITIVE_INFINITY;
        this.thresholds = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new Function2() { // from class: androidx.compose.material.SwipeableState$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Float.valueOf(SwipeableState.thresholds_delegate$lambda$0(((Float) obj).floatValue(), ((Float) obj2).floatValue()));
            }
        }, null, 2, null);
        this.velocityThreshold = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.resistance = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.draggableState = DraggableKt.DraggableState(new Function1() { // from class: androidx.compose.material.SwipeableState$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SwipeableState.draggableState$lambda$0(this.f$0, ((Float) obj).floatValue());
            }
        });
    }

    public /* synthetic */ SwipeableState(Object obj, SpringSpec<Float> springSpec, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i & 2) != 0 ? SwipeableDefaults.INSTANCE.getAnimationSpec() : springSpec, (i & 4) != 0 ? new Function1() { // from class: androidx.compose.material.SwipeableState$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Boolean.valueOf(SwipeableState._init_$lambda$0(obj2));
            }
        } : function1);
    }

    public final AnimationSpec<Float> getAnimationSpec$material() {
        return this.animationSpec;
    }

    static final boolean _init_$lambda$0(Object it) {
        return true;
    }

    public final Function1<T, Boolean> getConfirmStateChange$material() {
        return this.confirmStateChange;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrentValue(T t) {
        MutableState $this$setValue$iv = this.currentValue;
        $this$setValue$iv.setValue(t);
    }

    public final T getCurrentValue() {
        State $this$getValue$iv = this.currentValue;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAnimationRunning(boolean z) {
        MutableState $this$setValue$iv = this.isAnimationRunning;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final boolean isAnimationRunning() {
        State $this$getValue$iv = this.isAnimationRunning;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final State<Float> getOffset() {
        return this.offsetState;
    }

    public final State<Float> getOverflow() {
        return this.overflowState;
    }

    public final Map<Float, T> getAnchors$material() {
        State $this$getValue$iv = this.anchors;
        return (Map) $this$getValue$iv.getValue();
    }

    public final void setAnchors$material(Map<Float, ? extends T> map) {
        MutableState $this$setValue$iv = this.anchors;
        $this$setValue$iv.setValue(map);
    }

    /* JADX INFO: renamed from: getMinBound$material, reason: from getter */
    public final float getMinBound() {
        return this.minBound;
    }

    public final void setMinBound$material(float f) {
        this.minBound = f;
    }

    /* JADX INFO: renamed from: getMaxBound$material, reason: from getter */
    public final float getMaxBound() {
        return this.maxBound;
    }

    public final void setMaxBound$material(float f) {
        this.maxBound = f;
    }

    public final void ensureInit$material(Map<Float, ? extends T> newAnchors) {
        if (getAnchors$material().isEmpty()) {
            Float initialOffset = SwipeableKt.getOffset(newAnchors, getCurrentValue());
            if (initialOffset == null) {
                throw new IllegalArgumentException("The initial value must have an associated anchor.".toString());
            }
            this.offsetState.setFloatValue(initialOffset.floatValue());
            this.absoluteOffset.setFloatValue(initialOffset.floatValue());
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:84:0x023d  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [androidx.compose.material.SwipeableState] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7, types: [androidx.compose.material.SwipeableState] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r6v14, types: [androidx.compose.material.SwipeableState] */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r7v13, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v8 */
    public final Object processNewAnchors$material(Map<Float, ? extends T> map, Map<Float, ? extends T> map2, Continuation<? super Unit> continuation) throws Throwable {
        SwipeableState$processNewAnchors$1 swipeableState$processNewAnchors$1;
        ?? r5;
        float f;
        ?? r7;
        SwipeableState swipeableState;
        Map<Float, ? extends T> map3;
        float fFloatValue;
        Map<Float, ? extends T> map4;
        float f2;
        ?? r6;
        float f3;
        ?? r8;
        float f4;
        ?? r9;
        ?? r10;
        if (continuation instanceof SwipeableState$processNewAnchors$1) {
            swipeableState$processNewAnchors$1 = (SwipeableState$processNewAnchors$1) continuation;
            if ((swipeableState$processNewAnchors$1.label & Integer.MIN_VALUE) != 0) {
                swipeableState$processNewAnchors$1.label -= Integer.MIN_VALUE;
            } else {
                swipeableState$processNewAnchors$1 = new SwipeableState$processNewAnchors$1(this, continuation);
            }
        } else {
            swipeableState$processNewAnchors$1 = new SwipeableState$processNewAnchors$1(this, continuation);
        }
        SwipeableState$processNewAnchors$1 swipeableState$processNewAnchors$2 = swipeableState$processNewAnchors$1;
        Object obj = swipeableState$processNewAnchors$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (swipeableState$processNewAnchors$2.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    swipeableState = this;
                    map3 = map2;
                    if (map.isEmpty()) {
                        Float fMinOrNull = CollectionsKt.minOrNull((Iterable<? extends Float>) map3.keySet());
                        Intrinsics.checkNotNull(fMinOrNull);
                        swipeableState.minBound = fMinOrNull.floatValue();
                        Float fMaxOrNull = CollectionsKt.maxOrNull((Iterable<? extends Float>) map3.keySet());
                        Intrinsics.checkNotNull(fMaxOrNull);
                        swipeableState.maxBound = fMaxOrNull.floatValue();
                        Float offset = SwipeableKt.getOffset(map3, swipeableState.getCurrentValue());
                        if (offset == null) {
                            throw new IllegalArgumentException("The initial value must have an associated anchor.".toString());
                        }
                        float fFloatValue2 = offset.floatValue();
                        swipeableState$processNewAnchors$2.label = 1;
                        if (swipeableState.snapInternalToOffset(fFloatValue2, swipeableState$processNewAnchors$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (!Intrinsics.areEqual(map3, map)) {
                        swipeableState.minBound = Float.NEGATIVE_INFINITY;
                        swipeableState.maxBound = Float.POSITIVE_INFINITY;
                        Float value = swipeableState.animationTarget.getValue();
                        Object next = null;
                        if (value != null) {
                            Float offset2 = SwipeableKt.getOffset(map3, map.get(value));
                            if (offset2 != null) {
                                fFloatValue = offset2.floatValue();
                            } else {
                                Iterator<T> it = map3.keySet().iterator();
                                if (it.hasNext()) {
                                    next = it.next();
                                    if (it.hasNext()) {
                                        float fAbs = Math.abs(((Number) next).floatValue() - value.floatValue());
                                        do {
                                            Object next2 = it.next();
                                            float fAbs2 = Math.abs(((Number) next2).floatValue() - value.floatValue());
                                            if (Float.compare(fAbs, fAbs2) > 0) {
                                                next = next2;
                                                fAbs = fAbs2;
                                            }
                                        } while (it.hasNext());
                                    }
                                }
                                Intrinsics.checkNotNull(next);
                                fFloatValue = ((Number) next).floatValue();
                            }
                        } else {
                            Object currentValue = map.get(swipeableState.getOffset().getValue());
                            if (Intrinsics.areEqual(currentValue, swipeableState.getCurrentValue())) {
                                currentValue = swipeableState.getCurrentValue();
                            }
                            Float offset3 = SwipeableKt.getOffset(map3, currentValue);
                            if (offset3 != null) {
                                fFloatValue = offset3.floatValue();
                            } else {
                                Iterator<T> it2 = map3.keySet().iterator();
                                if (it2.hasNext()) {
                                    next = it2.next();
                                    if (it2.hasNext()) {
                                        float fAbs3 = Math.abs(((Number) next).floatValue() - swipeableState.getOffset().getValue().floatValue());
                                        do {
                                            Object next3 = it2.next();
                                            float fAbs4 = Math.abs(((Number) next3).floatValue() - swipeableState.getOffset().getValue().floatValue());
                                            if (Float.compare(fAbs3, fAbs4) > 0) {
                                                next = next3;
                                                fAbs3 = fAbs4;
                                            }
                                        } while (it2.hasNext());
                                    }
                                }
                                Intrinsics.checkNotNull(next);
                                fFloatValue = ((Number) next).floatValue();
                            }
                        }
                        try {
                            AnimationSpec<Float> animationSpec = swipeableState.animationSpec;
                            swipeableState$processNewAnchors$2.L$0 = map3;
                            swipeableState$processNewAnchors$2.F$0 = fFloatValue == true ? 1.0f : 0.0f;
                            swipeableState$processNewAnchors$2.label = 2;
                            if (swipeableState.animateInternalToOffset(fFloatValue == true ? 1.0f : 0.0f, animationSpec, swipeableState$processNewAnchors$2) != coroutine_suspended) {
                                map4 = map3;
                                f2 = fFloatValue == true ? 1 : 0;
                                r8 = swipeableState;
                                r8.setCurrentValue(MapsKt.getValue(map4, Boxing.boxFloat(f2)));
                                Float fMinOrNull2 = CollectionsKt.minOrNull((Iterable<? extends Float>) map4.keySet());
                                Intrinsics.checkNotNull(fMinOrNull2);
                                r8.minBound = fMinOrNull2.floatValue();
                                Float fMaxOrNull2 = CollectionsKt.maxOrNull((Iterable<? extends Float>) map4.keySet());
                                Intrinsics.checkNotNull(fMaxOrNull2);
                                r8.maxBound = fMaxOrNull2.floatValue();
                                r9 = r8;
                                r7 = f2;
                                f4 = fFloatValue;
                            }
                            break;
                        } catch (CancellationException e) {
                            swipeableState$processNewAnchors$2.L$0 = map3;
                            swipeableState$processNewAnchors$2.F$0 = fFloatValue == true ? 1.0f : 0.0f;
                            swipeableState$processNewAnchors$2.label = 3;
                            if (swipeableState.snapInternalToOffset(fFloatValue == true ? 1.0f : 0.0f, swipeableState$processNewAnchors$2) != coroutine_suspended) {
                                r6 = swipeableState;
                                f3 = fFloatValue == true ? 1 : 0;
                                r10 = map3;
                                r6.setCurrentValue(MapsKt.getValue(r10, Boxing.boxFloat(f3)));
                                Float fMinOrNull3 = CollectionsKt.minOrNull((Iterable<? extends Float>) r10.keySet());
                                Intrinsics.checkNotNull(fMinOrNull3);
                                r6.minBound = fMinOrNull3.floatValue();
                                Float fMaxOrNull3 = CollectionsKt.maxOrNull((Iterable<? extends Float>) r10.keySet());
                                Intrinsics.checkNotNull(fMaxOrNull3);
                                r6.maxBound = fMaxOrNull3.floatValue();
                                r9 = f3;
                                r7 = r10;
                                f4 = fFloatValue;
                                return Unit.INSTANCE;
                            }
                        }
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 1:
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                case 2:
                    swipeableState = this;
                    float f5 = swipeableState$processNewAnchors$2.F$0;
                    Map<Float, ? extends T> map5 = (Map) swipeableState$processNewAnchors$2.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        r8 = swipeableState;
                        f2 = f5;
                        map4 = map5;
                        r8.setCurrentValue(MapsKt.getValue(map4, Boxing.boxFloat(f2)));
                        Float fMinOrNull4 = CollectionsKt.minOrNull((Iterable<? extends Float>) map4.keySet());
                        Intrinsics.checkNotNull(fMinOrNull4);
                        r8.minBound = fMinOrNull4.floatValue();
                        Float fMaxOrNull4 = CollectionsKt.maxOrNull((Iterable<? extends Float>) map4.keySet());
                        Intrinsics.checkNotNull(fMaxOrNull4);
                        r8.maxBound = fMaxOrNull4.floatValue();
                        r9 = r8;
                        r7 = f2;
                        f4 = fFloatValue;
                    } catch (CancellationException e2) {
                        fFloatValue = f5;
                        map3 = map5;
                        swipeableState$processNewAnchors$2.L$0 = map3;
                        swipeableState$processNewAnchors$2.F$0 = fFloatValue == true ? 1.0f : 0.0f;
                        swipeableState$processNewAnchors$2.label = 3;
                        if (swipeableState.snapInternalToOffset(fFloatValue == true ? 1.0f : 0.0f, swipeableState$processNewAnchors$2) != coroutine_suspended) {
                            r6 = swipeableState;
                            f3 = fFloatValue == true ? 1 : 0;
                            r10 = map3;
                            r6.setCurrentValue(MapsKt.getValue(r10, Boxing.boxFloat(f3)));
                            Float fMinOrNull5 = CollectionsKt.minOrNull((Iterable<? extends Float>) r10.keySet());
                            Intrinsics.checkNotNull(fMinOrNull5);
                            r6.minBound = fMinOrNull5.floatValue();
                            Float fMaxOrNull5 = CollectionsKt.maxOrNull((Iterable<? extends Float>) r10.keySet());
                            Intrinsics.checkNotNull(fMaxOrNull5);
                            r6.maxBound = fMaxOrNull5.floatValue();
                            r9 = f3;
                            r7 = r10;
                            f4 = fFloatValue;
                        }
                        return coroutine_suspended;
                    } catch (Throwable th) {
                        th = th;
                        r5 = swipeableState;
                        f = f5;
                        r7 = map5;
                        r5.setCurrentValue(MapsKt.getValue(r7, Boxing.boxFloat(f)));
                        Float fMinOrNull6 = CollectionsKt.minOrNull((Iterable<? extends Float>) r7.keySet());
                        Intrinsics.checkNotNull(fMinOrNull6);
                        r5.minBound = fMinOrNull6.floatValue();
                        Float fMaxOrNull6 = CollectionsKt.maxOrNull((Iterable<? extends Float>) r7.keySet());
                        Intrinsics.checkNotNull(fMaxOrNull6);
                        r5.maxBound = fMaxOrNull6.floatValue();
                        throw th;
                    }
                    return Unit.INSTANCE;
                case 3:
                    r5 = this;
                    f = swipeableState$processNewAnchors$2.F$0;
                    r7 = (Map) swipeableState$processNewAnchors$2.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        r6 = r5;
                        f3 = f;
                        r10 = r7;
                        r6.setCurrentValue(MapsKt.getValue(r10, Boxing.boxFloat(f3)));
                        Float fMinOrNull7 = CollectionsKt.minOrNull((Iterable<? extends Float>) r10.keySet());
                        Intrinsics.checkNotNull(fMinOrNull7);
                        r6.minBound = fMinOrNull7.floatValue();
                        Float fMaxOrNull7 = CollectionsKt.maxOrNull((Iterable<? extends Float>) r10.keySet());
                        Intrinsics.checkNotNull(fMaxOrNull7);
                        r6.maxBound = fMaxOrNull7.floatValue();
                        r9 = f3;
                        r7 = r10;
                        f4 = fFloatValue;
                        return Unit.INSTANCE;
                    } catch (Throwable th2) {
                        th = th2;
                        r5.setCurrentValue(MapsKt.getValue(r7, Boxing.boxFloat(f)));
                        Float fMinOrNull8 = CollectionsKt.minOrNull((Iterable<? extends Float>) r7.keySet());
                        Intrinsics.checkNotNull(fMinOrNull8);
                        r5.minBound = fMinOrNull8.floatValue();
                        Float fMaxOrNull8 = CollectionsKt.maxOrNull((Iterable<? extends Float>) r7.keySet());
                        Intrinsics.checkNotNull(fMaxOrNull8);
                        r5.maxBound = fMaxOrNull8.floatValue();
                        throw th;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th3) {
            th = th3;
            r5 = r9;
            f = f4;
        }
    }

    static final float thresholds_delegate$lambda$0(float f, float f2) {
        return 0.0f;
    }

    public final Function2<Float, Float, Float> getThresholds$material() {
        State $this$getValue$iv = this.thresholds;
        return (Function2) $this$getValue$iv.getValue();
    }

    public final void setThresholds$material(Function2<? super Float, ? super Float, Float> function2) {
        MutableState $this$setValue$iv = this.thresholds;
        $this$setValue$iv.setValue(function2);
    }

    public final float getVelocityThreshold$material() {
        FloatState $this$getValue$iv = this.velocityThreshold;
        return $this$getValue$iv.getFloatValue();
    }

    public final void setVelocityThreshold$material(float f) {
        MutableFloatState $this$setValue$iv = this.velocityThreshold;
        $this$setValue$iv.setFloatValue(f);
    }

    public final ResistanceConfig getResistance$material() {
        State $this$getValue$iv = this.resistance;
        return (ResistanceConfig) $this$getValue$iv.getValue();
    }

    public final void setResistance$material(ResistanceConfig resistanceConfig) {
        MutableState $this$setValue$iv = this.resistance;
        $this$setValue$iv.setValue(resistanceConfig);
    }

    /* JADX INFO: renamed from: getDraggableState$material, reason: from getter */
    public final DraggableState getDraggableState() {
        return this.draggableState;
    }

    static final Unit draggableState$lambda$0(SwipeableState this$0, float it) {
        float newAbsolute = this$0.absoluteOffset.getFloatValue() + it;
        float clamped = RangesKt.coerceIn(newAbsolute, this$0.minBound, this$0.maxBound);
        float overflow = newAbsolute - clamped;
        ResistanceConfig resistance$material = this$0.getResistance$material();
        float resistanceDelta = resistance$material != null ? resistance$material.computeResistance(overflow) : 0.0f;
        this$0.offsetState.setFloatValue(clamped + resistanceDelta);
        this$0.overflowState.setFloatValue(overflow);
        this$0.absoluteOffset.setFloatValue(newAbsolute);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material.SwipeableState$snapInternalToOffset$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Swipeable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/DragScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material.SwipeableState$snapInternalToOffset$2", f = "Swipeable.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C02942 extends SuspendLambda implements Function2<DragScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ float $target;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SwipeableState<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02942(float f, SwipeableState<T> swipeableState, Continuation<? super C02942> continuation) {
            super(2, continuation);
            this.$target = f;
            this.this$0 = swipeableState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02942 c02942 = new C02942(this.$target, this.this$0, continuation);
            c02942.L$0 = obj;
            return c02942;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DragScope dragScope, Continuation<? super Unit> continuation) {
            return ((C02942) create(dragScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    DragScope $this$drag = (DragScope) this.L$0;
                    $this$drag.dragBy(this.$target - ((SwipeableState) this.this$0).absoluteOffset.getFloatValue());
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object snapInternalToOffset(float target, Continuation<? super Unit> continuation) {
        Object objDrag$default = DraggableState.drag$default(this.draggableState, null, new C02942(target, this, null), continuation, 1, null);
        return objDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDrag$default : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material.SwipeableState$animateInternalToOffset$2, reason: invalid class name */
    /* JADX INFO: compiled from: Swipeable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/DragScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material.SwipeableState$animateInternalToOffset$2", f = "Swipeable.kt", i = {}, l = {JfifUtil.MARKER_EOI}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<DragScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AnimationSpec<Float> $spec;
        final /* synthetic */ float $target;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SwipeableState<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(SwipeableState<T> swipeableState, float f, AnimationSpec<Float> animationSpec, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = swipeableState;
            this.$target = f;
            this.$spec = animationSpec;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, this.$target, this.$spec, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DragScope dragScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(dragScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            Object $result2;
            Object $result3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        final DragScope $this$drag = (DragScope) this.L$0;
                        final Ref.FloatRef prevValue = new Ref.FloatRef();
                        prevValue.element = ((SwipeableState) this.this$0).absoluteOffset.getFloatValue();
                        ((SwipeableState) this.this$0).animationTarget.setValue(Boxing.boxFloat(this.$target));
                        this.this$0.setAnimationRunning(true);
                        Animatable animatableAnimatable$default = AnimatableKt.Animatable$default(prevValue.element, 0.0f, 2, null);
                        Float fBoxFloat = Boxing.boxFloat(this.$target);
                        AnimationSpec<Float> animationSpec = this.$spec;
                        Function1 function1 = new Function1() { // from class: androidx.compose.material.SwipeableState$animateInternalToOffset$2$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return SwipeableState.AnonymousClass2.invokeSuspend$lambda$0($this$drag, prevValue, (Animatable) obj);
                            }
                        };
                        this.label = 1;
                        Object objAnimateTo = animatableAnimatable$default.animateTo(fBoxFloat, (4 & 2) != 0 ? animatableAnimatable$default.defaultSpringSpec : animationSpec, (4 & 4) != 0 ? animatableAnimatable$default.getVelocity() : null, (4 & 8) != 0 ? null : function1, this);
                        if (objAnimateTo == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result2 = $result;
                        $result3 = objAnimateTo;
                        break;
                        break;
                    case 1:
                        $result3 = $result;
                        ResultKt.throwOnFailure($result3);
                        $result2 = $result3;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                try {
                    ((SwipeableState) this.this$0).animationTarget.setValue(null);
                    this.this$0.setAnimationRunning(false);
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    ((SwipeableState) this.this$0).animationTarget.setValue(null);
                    this.this$0.setAnimationRunning(false);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        static final Unit invokeSuspend$lambda$0(DragScope $$this$drag, Ref.FloatRef $prevValue, Animatable $this$animateTo) {
            $$this$drag.dragBy(((Number) $this$animateTo.getValue()).floatValue() - $prevValue.element);
            $prevValue.element = ((Number) $this$animateTo.getValue()).floatValue();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object animateInternalToOffset(float target, AnimationSpec<Float> animationSpec, Continuation<? super Unit> continuation) {
        Object objDrag$default = DraggableState.drag$default(this.draggableState, null, new AnonymousClass2(this, target, animationSpec, null), continuation, 1, null);
        return objDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDrag$default : Unit.INSTANCE;
    }

    public final T getTargetValue() {
        float target;
        Float value = this.animationTarget.getValue();
        if (value != null) {
            target = value.floatValue();
        } else {
            float fFloatValue = getOffset().getValue().floatValue();
            Float offset = SwipeableKt.getOffset(getAnchors$material(), getCurrentValue());
            target = SwipeableKt.computeTarget(fFloatValue, offset != null ? offset.floatValue() : getOffset().getValue().floatValue(), getAnchors$material().keySet(), getThresholds$material(), 0.0f, Float.POSITIVE_INFINITY);
        }
        T t = getAnchors$material().get(Float.valueOf(target));
        return t == null ? getCurrentValue() : t;
    }

    public final SwipeProgress<T> getProgress() {
        Object from;
        Object to;
        float fraction;
        Pair pair;
        List bounds = SwipeableKt.findBounds(getOffset().getValue().floatValue(), getAnchors$material().keySet());
        switch (bounds.size()) {
            case 0:
                from = getCurrentValue();
                to = getCurrentValue();
                fraction = 1.0f;
                break;
            case 1:
                from = MapsKt.getValue(getAnchors$material(), bounds.get(0));
                to = MapsKt.getValue(getAnchors$material(), bounds.get(0));
                fraction = 1.0f;
                break;
            default:
                if (getDirection() > 0.0f) {
                    pair = TuplesKt.to(bounds.get(0), bounds.get(1));
                } else {
                    pair = TuplesKt.to(bounds.get(1), bounds.get(0));
                }
                float a = ((Number) pair.component1()).floatValue();
                float b = ((Number) pair.component2()).floatValue();
                from = MapsKt.getValue(getAnchors$material(), Float.valueOf(a));
                to = MapsKt.getValue(getAnchors$material(), Float.valueOf(b));
                fraction = (getOffset().getValue().floatValue() - a) / (b - a);
                break;
        }
        return new SwipeProgress<>(from, to, fraction);
    }

    public final float getDirection() {
        Float offset = SwipeableKt.getOffset(getAnchors$material(), getCurrentValue());
        if (offset == null) {
            return 0.0f;
        }
        float it = offset.floatValue();
        return Math.signum(getOffset().getValue().floatValue() - it);
    }

    /* JADX INFO: renamed from: androidx.compose.material.SwipeableState$snapTo$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Swipeable.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02952<T> implements FlowCollector {
        final /* synthetic */ T $targetValue;
        final /* synthetic */ SwipeableState<T> this$0;

        C02952(T t, SwipeableState<T> swipeableState) {
            this.$targetValue = t;
            this.this$0 = swipeableState;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
            return emit((Map) value, (Continuation<? super Unit>) $completion);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        public final Object emit(Map<Float, ? extends T> map, Continuation<? super Unit> continuation) {
            SwipeableState$snapTo$2$emit$1 swipeableState$snapTo$2$emit$1;
            C02952<T> c02952;
            if (continuation instanceof SwipeableState$snapTo$2$emit$1) {
                swipeableState$snapTo$2$emit$1 = (SwipeableState$snapTo$2$emit$1) continuation;
                if ((swipeableState$snapTo$2$emit$1.label & Integer.MIN_VALUE) != 0) {
                    swipeableState$snapTo$2$emit$1.label -= Integer.MIN_VALUE;
                } else {
                    swipeableState$snapTo$2$emit$1 = new SwipeableState$snapTo$2$emit$1(this, continuation);
                }
            } else {
                swipeableState$snapTo$2$emit$1 = new SwipeableState$snapTo$2$emit$1(this, continuation);
            }
            Object $result = swipeableState$snapTo$2$emit$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (swipeableState$snapTo$2$emit$1.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    Float targetOffset = SwipeableKt.getOffset(map, this.$targetValue);
                    if (targetOffset == null) {
                        throw new IllegalArgumentException("The target value must have an associated anchor.".toString());
                    }
                    SwipeableState<T> swipeableState = this.this$0;
                    float fFloatValue = targetOffset.floatValue();
                    swipeableState$snapTo$2$emit$1.label = 1;
                    if (swipeableState.snapInternalToOffset(fFloatValue, swipeableState$snapTo$2$emit$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    c02952 = this;
                    break;
                    break;
                case 1:
                    c02952 = this;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c02952.this$0.setCurrentValue(c02952.$targetValue);
            return Unit.INSTANCE;
        }
    }

    public final Object snapTo(T t, Continuation<? super Unit> continuation) {
        Object objCollect = this.latestNonEmptyAnchorsFlow.collect(new C02952(t, this), continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material.SwipeableState$animateTo$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Swipeable.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02922<T> implements FlowCollector {
        final /* synthetic */ AnimationSpec<Float> $anim;
        final /* synthetic */ T $targetValue;
        final /* synthetic */ SwipeableState<T> this$0;

        C02922(T t, SwipeableState<T> swipeableState, AnimationSpec<Float> animationSpec) {
            this.$targetValue = t;
            this.this$0 = swipeableState;
            this.$anim = animationSpec;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
            return emit((Map) value, (Continuation<? super Unit>) $completion);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0018  */
        /* JADX WARN: Not initialized variable reg: 9, insn: 0x00f3: INVOKE (r13 I:java.util.Set) = (r9 I:java.util.Map A[D('$this$filterKeys$iv' java.util.Map)]) INTERFACE call: java.util.Map.entrySet():java.util.Set A[MD:():java.util.Set<java.util.Map$Entry<K, V>> (c)] (LINE:884), block:B:39:0x00e2 */
        /* JADX WARN: Type inference failed for: r5v0, types: [androidx.compose.material.SwipeableState$animateTo$2, int] */
        /* JADX WARN: Type inference failed for: r9v0, types: [java.util.Map] */
        public final Object emit(Map<Float, ? extends T> map, Continuation<? super Unit> continuation) {
            SwipeableState$animateTo$2$emit$1 swipeableState$animateTo$2$emit$1;
            ?? EntrySet;
            C02922<T> c02922;
            Map<Float, ? extends T> map2;
            if (continuation instanceof SwipeableState$animateTo$2$emit$1) {
                swipeableState$animateTo$2$emit$1 = (SwipeableState$animateTo$2$emit$1) continuation;
                if ((swipeableState$animateTo$2$emit$1.label & Integer.MIN_VALUE) != 0) {
                    swipeableState$animateTo$2$emit$1.label -= Integer.MIN_VALUE;
                } else {
                    swipeableState$animateTo$2$emit$1 = new SwipeableState$animateTo$2$emit$1(this, continuation);
                }
            } else {
                swipeableState$animateTo$2$emit$1 = new SwipeableState$animateTo$2$emit$1(this, continuation);
            }
            SwipeableState$animateTo$2$emit$1 swipeableState$animateTo$2$emit$2 = swipeableState$animateTo$2$emit$1;
            Object $result = swipeableState$animateTo$2$emit$2.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r5 = swipeableState$animateTo$2$emit$2.label;
            try {
                switch (r5) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        c02922 = this;
                        map2 = map;
                        Float targetOffset = SwipeableKt.getOffset(map2, c02922.$targetValue);
                        if (targetOffset == null) {
                            throw new IllegalArgumentException("The target value must have an associated anchor.".toString());
                        }
                        SwipeableState<T> swipeableState = c02922.this$0;
                        float fFloatValue = targetOffset.floatValue();
                        AnimationSpec<Float> animationSpec = c02922.$anim;
                        swipeableState$animateTo$2$emit$2.L$0 = map2;
                        swipeableState$animateTo$2$emit$2.label = 1;
                        if (swipeableState.animateInternalToOffset(fFloatValue, animationSpec, swipeableState$animateTo$2$emit$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        c02922 = this;
                        map2 = (Map) swipeableState$animateTo$2$emit$2.L$0;
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                float endOffset = ((SwipeableState) c02922.this$0).absoluteOffset.getFloatValue();
                LinkedHashMap result$iv = new LinkedHashMap();
                for (Map.Entry<Float, ? extends T> entry : map2.entrySet()) {
                    float anchorOffset = entry.getKey().floatValue();
                    if (Math.abs(anchorOffset - endOffset) < 0.5f) {
                        result$iv.put(entry.getKey(), entry.getValue());
                    }
                }
                Object endValue = CollectionsKt.firstOrNull(result$iv.values());
                if (endValue == null) {
                    endValue = c02922.this$0.getCurrentValue();
                }
                c02922.this$0.setCurrentValue(endValue);
                return Unit.INSTANCE;
            } catch (Throwable th) {
                float endOffset2 = ((SwipeableState) r5.this$0).absoluteOffset.getFloatValue();
                LinkedHashMap result$iv2 = new LinkedHashMap();
                for (Map.Entry entry$iv : EntrySet.entrySet()) {
                    float anchorOffset2 = ((Number) entry$iv.getKey()).floatValue();
                    if (Math.abs(anchorOffset2 - endOffset2) < 0.5f) {
                        result$iv2.put(entry$iv.getKey(), entry$iv.getValue());
                    }
                }
                Object endValue2 = CollectionsKt.firstOrNull(result$iv2.values());
                if (endValue2 == null) {
                    endValue2 = r5.this$0.getCurrentValue();
                }
                r5.this$0.setCurrentValue(endValue2);
                throw th;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateTo$default(SwipeableState swipeableState, Object obj, AnimationSpec animationSpec, Continuation continuation, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: animateTo");
        }
        if ((i & 2) != 0) {
            animationSpec = swipeableState.animationSpec;
        }
        return swipeableState.animateTo(obj, animationSpec, continuation);
    }

    public final Object animateTo(T t, AnimationSpec<Float> animationSpec, Continuation<? super Unit> continuation) {
        Object objCollect = this.latestNonEmptyAnchorsFlow.collect(new C02922(t, this, animationSpec), continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    public final Object performFling(final float velocity, Continuation<? super Unit> continuation) {
        Object objCollect = this.latestNonEmptyAnchorsFlow.collect(new FlowCollector(this) { // from class: androidx.compose.material.SwipeableState.performFling.2
            final /* synthetic */ SwipeableState<T> this$0;

            {
                this.this$0 = this;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                return emit((Map) value, (Continuation<? super Unit>) $completion);
            }

            public final Object emit(Map<Float, ? extends T> map, Continuation<? super Unit> continuation2) {
                Float offset = SwipeableKt.getOffset(map, this.this$0.getCurrentValue());
                Intrinsics.checkNotNull(offset);
                float lastAnchor = offset.floatValue();
                float targetValue = SwipeableKt.computeTarget(this.this$0.getOffset().getValue().floatValue(), lastAnchor, map.keySet(), this.this$0.getThresholds$material(), velocity, this.this$0.getVelocityThreshold$material());
                T t = map.get(Boxing.boxFloat(targetValue));
                if (t == null || !this.this$0.getConfirmStateChange$material().invoke(t).booleanValue()) {
                    Object objAnimateInternalToOffset = this.this$0.animateInternalToOffset(lastAnchor, this.this$0.getAnimationSpec$material(), continuation2);
                    return objAnimateInternalToOffset == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateInternalToOffset : Unit.INSTANCE;
                }
                Object objAnimateTo$default = SwipeableState.animateTo$default(this.this$0, t, null, continuation2, 2, null);
                return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    public final float performDrag(float delta) {
        float potentiallyConsumed = this.absoluteOffset.getFloatValue() + delta;
        float clamped = RangesKt.coerceIn(potentiallyConsumed, this.minBound, this.maxBound);
        float deltaToConsume = clamped - this.absoluteOffset.getFloatValue();
        if (Math.abs(deltaToConsume) > 0.0f) {
            this.draggableState.dispatchRawDelta(deltaToConsume);
        }
        return deltaToConsume;
    }

    /* JADX INFO: compiled from: Swipeable.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JD\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u0006\u0012\u0004\u0012\u0002H\u00070\u0005\"\b\b\u0001\u0010\u0007*\u00020\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\r0\f¨\u0006\u000e"}, d2 = {"Landroidx/compose/material/SwipeableState$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material/SwipeableState;", ExifInterface.GPS_DIRECTION_TRUE, "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmStateChange", "Lkotlin/Function1;", "", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T> Saver<SwipeableState<T>, T> Saver(final AnimationSpec<Float> animationSpec, final Function1<? super T, Boolean> confirmStateChange) {
            return SaverKt.Saver(new Function2() { // from class: androidx.compose.material.SwipeableState$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((SwipeableState) obj2).getCurrentValue();
                }
            }, new Function1() { // from class: androidx.compose.material.SwipeableState$Companion$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SwipeableState.Companion.Saver$lambda$1(animationSpec, confirmStateChange, obj);
                }
            });
        }

        static final SwipeableState Saver$lambda$1(AnimationSpec $animationSpec, Function1 $confirmStateChange, Object it) {
            return new SwipeableState(it, $animationSpec, $confirmStateChange);
        }
    }
}
