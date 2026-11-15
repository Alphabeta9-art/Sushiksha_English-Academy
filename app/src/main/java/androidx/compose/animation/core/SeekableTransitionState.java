package androidx.compose.animation.core;

import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.MonotonicFrameClockKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: Transition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u0000 Z*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002YZB\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010=\u001a\u00020\u001fH\u0002J\u000e\u0010>\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010?J\u000e\u0010@\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010?J\u000e\u0010A\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010?J\u0018\u0010B\u001a\u00020\u001f2\u0006\u0010C\u001a\u0002072\u0006\u0010D\u001a\u00020\u0018H\u0002J\u0016\u0010E\u001a\u00020\u001f2\u0006\u0010\u0007\u001a\u00028\u0000H\u0086@¢\u0006\u0002\u0010FJ\"\u0010G\u001a\u00020\u001f2\b\b\u0001\u0010!\u001a\u00020 2\b\b\u0002\u0010\u0007\u001a\u00028\u0000H\u0086@¢\u0006\u0002\u0010HJ\u000e\u0010I\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010?J\u000e\u0010J\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010?J\b\u0010K\u001a\u00020\u001fH\u0002J*\u0010L\u001a\u00020\u001f2\b\b\u0002\u0010\u0007\u001a\u00028\u00002\u0010\b\u0002\u0010M\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010NH\u0086@¢\u0006\u0002\u0010OJ\u001b\u0010P\u001a\u00020\u001f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0010¢\u0006\u0002\bQJ\r\u0010R\u001a\u00020\u001fH\u0010¢\u0006\u0002\bSJ\r\u0010T\u001a\u00020\u001fH\u0000¢\u0006\u0002\bUJ\r\u0010V\u001a\u00020\u001fH\u0000¢\u0006\u0002\bWJ\b\u0010X\u001a\u00020\u001fH\u0002R+\u0010\u0007\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00028\u00008V@PX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0005R+\u0010\r\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00028\u00008V@PX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\f\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u0005R\u001c\u0010\u0011\u001a\u00028\u0000X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\t\"\u0004\b\u0013\u0010\u0005R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010!\u001a\u00020 2\u0006\u0010\u0006\u001a\u00020 8G@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\"\u0010(\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010)X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020/X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00105\u001a\b\u0012\u0004\u0012\u00020706X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u000107X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001f0:X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001f0:X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006["}, d2 = {"Landroidx/compose/animation/core/SeekableTransitionState;", ExifInterface.LATITUDE_SOUTH, "Landroidx/compose/animation/core/TransitionState;", "initialState", "<init>", "(Ljava/lang/Object;)V", "<set-?>", "targetState", "getTargetState", "()Ljava/lang/Object;", "setTargetState$animation_core", "targetState$delegate", "Landroidx/compose/runtime/MutableState;", "currentState", "getCurrentState", "setCurrentState$animation_core", "currentState$delegate", "composedTargetState", "getComposedTargetState$animation_core", "setComposedTargetState$animation_core", "Ljava/lang/Object;", "transition", "Landroidx/compose/animation/core/Transition;", "totalDurationNanos", "", "getTotalDurationNanos$animation_core", "()J", "setTotalDurationNanos$animation_core", "(J)V", "recalculateTotalDurationNanos", "Lkotlin/Function0;", "", "", "fraction", "getFraction", "()F", "setFraction", "(F)V", "fraction$delegate", "Landroidx/compose/runtime/MutableFloatState;", "compositionContinuation", "Lkotlinx/coroutines/CancellableContinuation;", "getCompositionContinuation$animation_core", "()Lkotlinx/coroutines/CancellableContinuation;", "setCompositionContinuation$animation_core", "(Lkotlinx/coroutines/CancellableContinuation;)V", "compositionContinuationMutex", "Lkotlinx/coroutines/sync/Mutex;", "getCompositionContinuationMutex$animation_core", "()Lkotlinx/coroutines/sync/Mutex;", "mutatorMutex", "Landroidx/compose/animation/core/MutatorMutex;", "lastFrameTimeNanos", "initialValueAnimations", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/animation/core/SeekableTransitionState$SeekingAnimationState;", "currentAnimation", "firstFrameLambda", "Lkotlin/Function1;", "durationScale", "animateOneFrameLambda", "endAllAnimations", "runAnimations", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doOneFrame", "animateOneFrame", "recalculateAnimationValue", "animation", "deltaPlayTimeNanos", "snapTo", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "seekTo", "(FLjava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "waitForCompositionAfterTargetStateChange", "waitForComposition", "moveAnimationToInitialState", "animateTo", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "(Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transitionConfigured", "transitionConfigured$animation_core", "transitionRemoved", "transitionRemoved$animation_core", "observeTotalDuration", "observeTotalDuration$animation_core", "onTotalDurationChanged", "onTotalDurationChanged$animation_core", "seekToFraction", "SeekingAnimationState", "Companion", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SeekableTransitionState<S> extends TransitionState<S> {
    private final Function1<Long, Unit> animateOneFrameLambda;
    private S composedTargetState;
    private CancellableContinuation<? super S> compositionContinuation;
    private final Mutex compositionContinuationMutex;
    private SeekingAnimationState currentAnimation;

    /* JADX INFO: renamed from: currentState$delegate, reason: from kotlin metadata */
    private final MutableState currentState;
    private float durationScale;
    private final Function1<Long, Unit> firstFrameLambda;

    /* JADX INFO: renamed from: fraction$delegate, reason: from kotlin metadata */
    private final MutableFloatState fraction;
    private final MutableObjectList<SeekingAnimationState> initialValueAnimations;
    private long lastFrameTimeNanos;
    private final MutatorMutex mutatorMutex;
    private final Function0<Unit> recalculateTotalDurationNanos;

    /* JADX INFO: renamed from: targetState$delegate, reason: from kotlin metadata */
    private final MutableState targetState;
    private long totalDurationNanos;
    private Transition<S> transition;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final AnimationVector1D ZeroVelocity = new AnimationVector1D(0.0f);
    private static final AnimationVector1D Target1 = new AnimationVector1D(1.0f);

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$runAnimations$1, reason: invalid class name */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState", f = "Transition.kt", i = {}, l = {354, 357}, m = "runAnimations", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SeekableTransitionState<S> seekableTransitionState, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = seekableTransitionState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.runAnimations(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$waitForComposition$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState", f = "Transition.kt", i = {0, 1}, l = {544, 2169}, m = "waitForComposition", n = {"expectedState", "expectedState"}, s = {"L$0", "L$0"}, v = 1)
    static final class C01721 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01721(SeekableTransitionState<S> seekableTransitionState, Continuation<? super C01721> continuation) {
            super(continuation);
            this.this$0 = seekableTransitionState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.waitForComposition(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$waitForCompositionAfterTargetStateChange$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState", f = "Transition.kt", i = {0, 1}, l = {520, 2169}, m = "waitForCompositionAfterTargetStateChange", n = {"expectedState", "expectedState"}, s = {"L$0", "L$0"}, v = 1)
    static final class C01731 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01731(SeekableTransitionState<S> seekableTransitionState, Continuation<? super C01731> continuation) {
            super(continuation);
            this.this$0 = seekableTransitionState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.waitForCompositionAfterTargetStateChange(this);
        }
    }

    public SeekableTransitionState(S s) {
        super(null);
        this.targetState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(s, null, 2, null);
        this.currentState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(s, null, 2, null);
        this.composedTargetState = s;
        this.recalculateTotalDurationNanos = new Function0() { // from class: androidx.compose.animation.core.SeekableTransitionState$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SeekableTransitionState.recalculateTotalDurationNanos$lambda$0(this.f$0);
            }
        };
        this.fraction = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.compositionContinuationMutex = MutexKt.Mutex$default(false, 1, null);
        this.mutatorMutex = new MutatorMutex();
        this.lastFrameTimeNanos = Long.MIN_VALUE;
        this.initialValueAnimations = new MutableObjectList<>(0, 1, null);
        this.firstFrameLambda = new Function1() { // from class: androidx.compose.animation.core.SeekableTransitionState$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SeekableTransitionState.firstFrameLambda$lambda$0(this.f$0, ((Long) obj).longValue());
            }
        };
        this.animateOneFrameLambda = new Function1() { // from class: androidx.compose.animation.core.SeekableTransitionState$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SeekableTransitionState.animateOneFrameLambda$lambda$0(this.f$0, ((Long) obj).longValue());
            }
        };
    }

    @Override // androidx.compose.animation.core.TransitionState
    public S getTargetState() {
        return (S) this.targetState.getValue();
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void setTargetState$animation_core(S s) {
        MutableState $this$setValue$iv = this.targetState;
        $this$setValue$iv.setValue(s);
    }

    @Override // androidx.compose.animation.core.TransitionState
    public S getCurrentState() {
        return (S) this.currentState.getValue();
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void setCurrentState$animation_core(S s) {
        MutableState $this$setValue$iv = this.currentState;
        $this$setValue$iv.setValue(s);
    }

    public final S getComposedTargetState$animation_core() {
        return this.composedTargetState;
    }

    public final void setComposedTargetState$animation_core(S s) {
        this.composedTargetState = s;
    }

    /* JADX INFO: renamed from: getTotalDurationNanos$animation_core, reason: from getter */
    public final long getTotalDurationNanos() {
        return this.totalDurationNanos;
    }

    public final void setTotalDurationNanos$animation_core(long j) {
        this.totalDurationNanos = j;
    }

    static final Unit recalculateTotalDurationNanos$lambda$0(SeekableTransitionState this$0) {
        Transition<S> transition = this$0.transition;
        this$0.totalDurationNanos = transition != null ? transition.getTotalDurationNanos() : 0L;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFraction(float f) {
        MutableFloatState $this$setValue$iv = this.fraction;
        $this$setValue$iv.setFloatValue(f);
    }

    public final float getFraction() {
        FloatState $this$getValue$iv = this.fraction;
        return $this$getValue$iv.getFloatValue();
    }

    public final CancellableContinuation<S> getCompositionContinuation$animation_core() {
        return this.compositionContinuation;
    }

    public final void setCompositionContinuation$animation_core(CancellableContinuation<? super S> cancellableContinuation) {
        this.compositionContinuation = cancellableContinuation;
    }

    /* JADX INFO: renamed from: getCompositionContinuationMutex$animation_core, reason: from getter */
    public final Mutex getCompositionContinuationMutex() {
        return this.compositionContinuationMutex;
    }

    static final Unit firstFrameLambda$lambda$0(SeekableTransitionState this$0, long frameTimeNanos) {
        this$0.lastFrameTimeNanos = frameTimeNanos;
        return Unit.INSTANCE;
    }

    static final Unit animateOneFrameLambda$lambda$0(SeekableTransitionState this$0, long frameTimeNanos) {
        long delta = frameTimeNanos - this$0.lastFrameTimeNanos;
        this$0.lastFrameTimeNanos = frameTimeNanos;
        long deltaPlayTimeNanos = MathKt.roundToLong(delta / ((double) this$0.durationScale));
        if (this$0.initialValueAnimations.isNotEmpty()) {
            ObjectList this_$iv = this$0.initialValueAnimations;
            Object[] content$iv = this_$iv.content;
            int i = this_$iv._size;
            for (int i$iv = 0; i$iv < i; i$iv++) {
                SeekingAnimationState animation = (SeekingAnimationState) content$iv[i$iv];
                this$0.recalculateAnimationValue(animation, deltaPlayTimeNanos);
                animation.setComplete(true);
            }
            Transition<S> transition = this$0.transition;
            if (transition != null) {
                transition.updateInitialValues$animation_core();
            }
            MutableObjectList<SeekingAnimationState> mutableObjectList = this$0.initialValueAnimations;
            int gap$iv = 0;
            int size$iv = mutableObjectList._size;
            Object[] content$iv2 = mutableObjectList.content;
            MutableObjectList<SeekingAnimationState> this_$iv$iv = mutableObjectList;
            IntRange intRangeUntil = RangesKt.until(0, this_$iv$iv._size);
            int i$iv2 = intRangeUntil.getFirst();
            int last = intRangeUntil.getLast();
            if (i$iv2 <= last) {
                while (true) {
                    content$iv2[i$iv2 - gap$iv] = content$iv2[i$iv2];
                    SeekingAnimationState it = (SeekingAnimationState) content$iv2[i$iv2];
                    if (it.getIsComplete()) {
                        gap$iv++;
                    }
                    if (i$iv2 == last) {
                        break;
                    }
                    i$iv2++;
                }
            }
            ArraysKt.fill(content$iv2, (Object) null, size$iv - gap$iv, size$iv);
            mutableObjectList._size -= gap$iv;
        }
        SeekingAnimationState currentAnimation = this$0.currentAnimation;
        if (currentAnimation != null) {
            currentAnimation.setDurationNanos(this$0.totalDurationNanos);
            this$0.recalculateAnimationValue(currentAnimation, deltaPlayTimeNanos);
            this$0.setFraction(currentAnimation.getValue());
            if (currentAnimation.getValue() == 1.0f) {
                this$0.currentAnimation = null;
            }
            this$0.seekToFraction();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void endAllAnimations() {
        Transition<S> transition = this.transition;
        if (transition != null) {
            transition.clearInitialAnimations$animation_core();
        }
        this.initialValueAnimations.clear();
        SeekingAnimationState current = this.currentAnimation;
        if (current != null) {
            this.currentAnimation = null;
            setFraction(1.0f);
            seekToFraction();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:35:0x0081  */
    /* JADX WARN: Code duplicated, block: B:45:? A[PHI: r3
  PHI (r3v3 androidx.compose.animation.core.SeekableTransitionState) = 
  (r3v1 androidx.compose.animation.core.SeekableTransitionState)
  (r3v1 androidx.compose.animation.core.SeekableTransitionState)
  (r3v2 androidx.compose.animation.core.SeekableTransitionState)
  (r3v4 androidx.compose.animation.core.SeekableTransitionState)
 binds: [B:28:0x006b, B:30:0x0075, B:13:0x0034, B:12:0x002f] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object runAnimations(Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        SeekableTransitionState seekableTransitionState;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(this, continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(this, continuation);
        }
        Object $result = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                seekableTransitionState = this;
                if (seekableTransitionState.initialValueAnimations.isEmpty() && seekableTransitionState.currentAnimation == null) {
                    return Unit.INSTANCE;
                }
                if (!(SuspendAnimationKt.getDurationScale(anonymousClass1.getContext()) == 0.0f)) {
                    if (seekableTransitionState.lastFrameTimeNanos == Long.MIN_VALUE) {
                        Function1<Long, Unit> function1 = seekableTransitionState.firstFrameLambda;
                        anonymousClass1.label = 1;
                        if (MonotonicFrameClockKt.withFrameNanos(function1, anonymousClass1) != coroutine_suspended) {
                            do {
                                if (!seekableTransitionState.initialValueAnimations.isNotEmpty() || seekableTransitionState.currentAnimation != null) {
                                    anonymousClass1.label = 2;
                                } else {
                                    seekableTransitionState.lastFrameTimeNanos = Long.MIN_VALUE;
                                    return Unit.INSTANCE;
                                }
                            } while (seekableTransitionState.animateOneFrame(anonymousClass1) != coroutine_suspended);
                        }
                    } else {
                        do {
                            if (!seekableTransitionState.initialValueAnimations.isNotEmpty()) {
                            }
                            anonymousClass1.label = 2;
                        } while (seekableTransitionState.animateOneFrame(anonymousClass1) != coroutine_suspended);
                    }
                    return coroutine_suspended;
                }
                seekableTransitionState.endAllAnimations();
                seekableTransitionState.lastFrameTimeNanos = Long.MIN_VALUE;
                return Unit.INSTANCE;
            case 1:
                seekableTransitionState = this;
                ResultKt.throwOnFailure($result);
                do {
                    if (!seekableTransitionState.initialValueAnimations.isNotEmpty()) {
                    }
                    anonymousClass1.label = 2;
                    break;
                } while (seekableTransitionState.animateOneFrame(anonymousClass1) != coroutine_suspended);
                return coroutine_suspended;
            case 2:
                seekableTransitionState = this;
                ResultKt.throwOnFailure($result);
                do {
                    if (!seekableTransitionState.initialValueAnimations.isNotEmpty()) {
                    }
                    anonymousClass1.label = 2;
                    break;
                } while (seekableTransitionState.animateOneFrame(anonymousClass1) != coroutine_suspended);
                return coroutine_suspended;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object doOneFrame(Continuation<? super Unit> continuation) {
        if (this.lastFrameTimeNanos == Long.MIN_VALUE) {
            Object objWithFrameNanos = MonotonicFrameClockKt.withFrameNanos(this.firstFrameLambda, continuation);
            return objWithFrameNanos == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithFrameNanos : Unit.INSTANCE;
        }
        Object objAnimateOneFrame = animateOneFrame(continuation);
        return objAnimateOneFrame == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateOneFrame : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object animateOneFrame(Continuation<? super Unit> continuation) {
        float durationScale = SuspendAnimationKt.getDurationScale(continuation.getContext());
        if (durationScale <= 0.0f) {
            endAllAnimations();
            return Unit.INSTANCE;
        }
        this.durationScale = durationScale;
        Object objWithFrameNanos = MonotonicFrameClockKt.withFrameNanos(this.animateOneFrameLambda, continuation);
        return objWithFrameNanos == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithFrameNanos : Unit.INSTANCE;
    }

    private final void recalculateAnimationValue(SeekingAnimationState animation, long deltaPlayTimeNanos) {
        long playTimeNanos = animation.getProgressNanos() + deltaPlayTimeNanos;
        animation.setProgressNanos(playTimeNanos);
        long durationNanos = animation.getAnimationSpecDuration();
        if (playTimeNanos >= durationNanos) {
            animation.setValue(1.0f);
            return;
        }
        VectorizedAnimationSpec<AnimationVector1D> animationSpec = animation.getAnimationSpec();
        if (animationSpec == null) {
            float start$iv = animation.getStart().get$animation_core(0);
            float fraction$iv = playTimeNanos / durationNanos;
            animation.setValue(((1.0f - fraction$iv) * start$iv) + (1.0f * fraction$iv));
        } else {
            AnimationVector1D start = animation.getStart();
            AnimationVector1D animationVector1D = Target1;
            AnimationVector1D initialVelocity = animation.getInitialVelocity();
            if (initialVelocity == null) {
                initialVelocity = ZeroVelocity;
            }
            animation.setValue(RangesKt.coerceIn(((AnimationVector1D) animationSpec.getValueFromNanos(playTimeNanos, start, animationVector1D, initialVelocity)).get$animation_core(0), 0.0f, 1.0f));
        }
    }

    public final Object snapTo(S s, Continuation<? super Unit> continuation) {
        Transition<S> transition = this.transition;
        if (transition == null) {
            return Unit.INSTANCE;
        }
        if (Intrinsics.areEqual(getCurrentState(), s) && Intrinsics.areEqual(getTargetState(), s)) {
            return Unit.INSTANCE;
        }
        Object objMutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new C01712(this, s, transition, null), continuation, 1, null);
        return objMutate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMutate$default : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$snapTo$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$snapTo$2", f = "Transition.kt", i = {}, l = {458}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01712 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ S $targetState;
        final /* synthetic */ Transition<S> $transition;
        int label;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01712(SeekableTransitionState<S> seekableTransitionState, S s, Transition<S> transition, Continuation<? super C01712> continuation) {
            super(1, continuation);
            this.this$0 = seekableTransitionState;
            this.$targetState = s;
            this.$transition = transition;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C01712(this.this$0, this.$targetState, this.$transition, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((C01712) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            float fraction;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.this$0.endAllAnimations();
                    ((SeekableTransitionState) this.this$0).lastFrameTimeNanos = Long.MIN_VALUE;
                    this.this$0.setFraction(0.0f);
                    S s = this.$targetState;
                    if (Intrinsics.areEqual(s, this.this$0.getCurrentState())) {
                        fraction = -4.0f;
                    } else {
                        fraction = Intrinsics.areEqual(s, this.this$0.getTargetState()) ? -5.0f : -3.0f;
                    }
                    this.$transition.updateTarget$animation_core(this.$targetState);
                    this.$transition.setPlayTimeNanos(0L);
                    this.this$0.setTargetState$animation_core(this.$targetState);
                    this.this$0.setFraction(0.0f);
                    this.this$0.setCurrentState$animation_core(this.$targetState);
                    this.$transition.resetAnimationFraction$animation_core(fraction);
                    if (fraction == -3.0f) {
                        this.label = 1;
                        if (this.this$0.waitForCompositionAfterTargetStateChange(this) == coroutine_suspended) {
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
            this.$transition.onTransitionEnd$animation_core();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object seekTo$default(SeekableTransitionState seekableTransitionState, float f, Object obj, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            obj = seekableTransitionState.getTargetState();
        }
        return seekableTransitionState.seekTo(f, obj, continuation);
    }

    public final Object seekTo(float fraction, S s, Continuation<? super Unit> continuation) {
        boolean value$iv = false;
        if (0.0f <= fraction && fraction <= 1.0f) {
            value$iv = true;
        }
        if (!value$iv) {
            PreconditionsKt.throwIllegalArgumentException("Expecting fraction between 0 and 1. Got " + fraction);
        }
        Transition<S> transition = this.transition;
        if (transition == null) {
            return Unit.INSTANCE;
        }
        Object oldTargetState = getTargetState();
        Object objMutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new AnonymousClass3(s, oldTargetState, this, transition, fraction, null), continuation, 1, null);
        return objMutate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMutate$default : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$seekTo$3, reason: invalid class name */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$seekTo$3", f = "Transition.kt", i = {}, l = {489}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass3 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ float $fraction;
        final /* synthetic */ S $oldTargetState;
        final /* synthetic */ S $targetState;
        final /* synthetic */ Transition<S> $transition;
        int label;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(S s, S s2, SeekableTransitionState<S> seekableTransitionState, Transition<S> transition, float f, Continuation<? super AnonymousClass3> continuation) {
            super(1, continuation);
            this.$targetState = s;
            this.$oldTargetState = s2;
            this.this$0 = seekableTransitionState;
            this.$transition = transition;
            this.$fraction = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass3(this.$targetState, this.$oldTargetState, this.this$0, this.$transition, this.$fraction, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$seekTo$3$1, reason: invalid class name */
        /* JADX INFO: compiled from: Transition.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$seekTo$3$1", f = "Transition.kt", i = {}, l = {FrameMetricsAggregator.EVERY_DURATION}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ float $fraction;
            final /* synthetic */ S $oldTargetState;
            final /* synthetic */ S $targetState;
            final /* synthetic */ Transition<S> $transition;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SeekableTransitionState<S> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(S s, S s2, SeekableTransitionState<S> seekableTransitionState, Transition<S> transition, float f, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$targetState = s;
                this.$oldTargetState = s2;
                this.this$0 = seekableTransitionState;
                this.$transition = transition;
                this.$fraction = f;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$targetState, this.$oldTargetState, this.this$0, this.$transition, this.$fraction, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                        if (!Intrinsics.areEqual(this.$targetState, this.$oldTargetState)) {
                            this.this$0.moveAnimationToInitialState();
                        } else {
                            ((SeekableTransitionState) this.this$0).currentAnimation = null;
                            if (Intrinsics.areEqual(this.this$0.getCurrentState(), this.$targetState)) {
                                return Unit.INSTANCE;
                            }
                        }
                        if (!Intrinsics.areEqual(this.$targetState, this.$oldTargetState)) {
                            this.$transition.updateTarget$animation_core(this.$targetState);
                            this.$transition.setPlayTimeNanos(0L);
                            this.this$0.setTargetState$animation_core(this.$targetState);
                            this.$transition.resetAnimationFraction$animation_core(this.$fraction);
                        }
                        this.this$0.setFraction(this.$fraction);
                        if (((SeekableTransitionState) this.this$0).initialValueAnimations.isNotEmpty()) {
                            BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, null, new C00031(this.this$0, null), 3, null);
                        } else {
                            ((SeekableTransitionState) this.this$0).lastFrameTimeNanos = Long.MIN_VALUE;
                        }
                        this.label = 1;
                        if (this.this$0.waitForCompositionAfterTargetStateChange(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                this.this$0.seekToFraction();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$seekTo$3$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: Transition.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$seekTo$3$1$1", f = "Transition.kt", i = {}, l = {TypedValues.PositionType.TYPE_PERCENT_Y}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class C00031 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;
                final /* synthetic */ SeekableTransitionState<S> this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00031(SeekableTransitionState<S> seekableTransitionState, Continuation<? super C00031> continuation) {
                    super(2, continuation);
                    this.this$0 = seekableTransitionState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00031(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00031) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            this.label = 1;
                            if (this.this$0.runAnimations(this) == coroutine_suspended) {
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
                    this.label = 1;
                    if (CoroutineScopeKt.coroutineScope(new AnonymousClass1(this.$targetState, this.$oldTargetState, this.this$0, this.$transition, this.$fraction, null), this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:19:0x005d  */
    /* JADX WARN: Code duplicated, block: B:20:0x0063  */
    /* JADX WARN: Code duplicated, block: B:22:0x0093  */
    /* JADX WARN: Code duplicated, block: B:24:0x009b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x009c  */
    /* JADX WARN: Code duplicated, block: B:30:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object waitForCompositionAfterTargetStateChange(Continuation<? super Unit> continuation) {
        C01731 c01731;
        SeekableTransitionState<S> seekableTransitionState;
        Object expectedState;
        Object expectedState2;
        SeekableTransitionState<S> seekableTransitionState2;
        if (continuation instanceof C01731) {
            c01731 = (C01731) continuation;
            if ((c01731.label & Integer.MIN_VALUE) != 0) {
                c01731.label -= Integer.MIN_VALUE;
            } else {
                c01731 = new C01731(this, continuation);
            }
        } else {
            c01731 = new C01731(this, continuation);
        }
        Object $result = c01731.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c01731.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                seekableTransitionState = this;
                expectedState = seekableTransitionState.getTargetState();
                Mutex mutex = seekableTransitionState.compositionContinuationMutex;
                c01731.L$0 = expectedState;
                c01731.label = 1;
                if (Mutex.DefaultImpls.lock$default(mutex, null, c01731, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                if (Intrinsics.areEqual(expectedState, seekableTransitionState.composedTargetState)) {
                    Mutex.DefaultImpls.unlock$default(seekableTransitionState.compositionContinuationMutex, null, 1, null);
                } else {
                    c01731.L$0 = expectedState;
                    c01731.label = 2;
                    Continuation uCont$iv = c01731;
                    CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(uCont$iv), 1);
                    cancellable$iv.initCancellability();
                    CancellableContinuationImpl continuation2 = cancellable$iv;
                    seekableTransitionState.setCompositionContinuation$animation_core(continuation2);
                    Mutex.DefaultImpls.unlock$default(seekableTransitionState.getCompositionContinuationMutex(), null, 1, null);
                    expectedState2 = cancellable$iv.getResult();
                    if (expectedState2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        DebugProbesKt.probeCoroutineSuspended(c01731);
                    }
                    if (expectedState2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    seekableTransitionState2 = seekableTransitionState;
                    if (!Intrinsics.areEqual(expectedState2, expectedState)) {
                        seekableTransitionState2.lastFrameTimeNanos = Long.MIN_VALUE;
                        throw new CancellationException("snapTo() was canceled because state was changed to " + expectedState2 + " instead of " + expectedState);
                    }
                }
                return Unit.INSTANCE;
            case 1:
                seekableTransitionState = this;
                expectedState = c01731.L$0;
                ResultKt.throwOnFailure($result);
                if (Intrinsics.areEqual(expectedState, seekableTransitionState.composedTargetState)) {
                    Mutex.DefaultImpls.unlock$default(seekableTransitionState.compositionContinuationMutex, null, 1, null);
                } else {
                    c01731.L$0 = expectedState;
                    c01731.label = 2;
                    Continuation uCont$iv2 = c01731;
                    CancellableContinuationImpl cancellable$iv2 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(uCont$iv2), 1);
                    cancellable$iv2.initCancellability();
                    CancellableContinuationImpl continuation3 = cancellable$iv2;
                    seekableTransitionState.setCompositionContinuation$animation_core(continuation3);
                    Mutex.DefaultImpls.unlock$default(seekableTransitionState.getCompositionContinuationMutex(), null, 1, null);
                    expectedState2 = cancellable$iv2.getResult();
                    if (expectedState2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        DebugProbesKt.probeCoroutineSuspended(c01731);
                    }
                    if (expectedState2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    seekableTransitionState2 = seekableTransitionState;
                    if (!Intrinsics.areEqual(expectedState2, expectedState)) {
                        seekableTransitionState2.lastFrameTimeNanos = Long.MIN_VALUE;
                        throw new CancellationException("snapTo() was canceled because state was changed to " + expectedState2 + " instead of " + expectedState);
                    }
                }
                return Unit.INSTANCE;
            case 2:
                seekableTransitionState2 = this;
                Object expectedState3 = c01731.L$0;
                ResultKt.throwOnFailure($result);
                expectedState = expectedState3;
                expectedState2 = $result;
                if (!Intrinsics.areEqual(expectedState2, expectedState)) {
                    seekableTransitionState2.lastFrameTimeNanos = Long.MIN_VALUE;
                    throw new CancellationException("snapTo() was canceled because state was changed to " + expectedState2 + " instead of " + expectedState);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:19:0x0085  */
    /* JADX WARN: Code duplicated, block: B:21:0x008d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:22:0x008e  */
    /* JADX WARN: Code duplicated, block: B:25:0x0098  */
    /* JADX WARN: Code duplicated, block: B:27:0x009b  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object waitForComposition(Continuation<? super Unit> continuation) {
        C01721 c01721;
        SeekableTransitionState<S> seekableTransitionState;
        Object expectedState;
        Object expectedState2;
        SeekableTransitionState<S> seekableTransitionState2;
        if (continuation instanceof C01721) {
            c01721 = (C01721) continuation;
            if ((c01721.label & Integer.MIN_VALUE) != 0) {
                c01721.label -= Integer.MIN_VALUE;
            } else {
                c01721 = new C01721(this, continuation);
            }
        } else {
            c01721 = new C01721(this, continuation);
        }
        Object $result = c01721.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c01721.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                seekableTransitionState = this;
                expectedState = seekableTransitionState.getTargetState();
                Mutex mutex = seekableTransitionState.compositionContinuationMutex;
                c01721.L$0 = expectedState;
                c01721.label = 1;
                if (Mutex.DefaultImpls.lock$default(mutex, null, c01721, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                c01721.L$0 = expectedState;
                c01721.label = 2;
                Continuation uCont$iv = c01721;
                CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(uCont$iv), 1);
                cancellable$iv.initCancellability();
                CancellableContinuationImpl continuation2 = cancellable$iv;
                seekableTransitionState.setCompositionContinuation$animation_core(continuation2);
                Mutex.DefaultImpls.unlock$default(seekableTransitionState.getCompositionContinuationMutex(), null, 1, null);
                expectedState2 = cancellable$iv.getResult();
                if (expectedState2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(c01721);
                }
                if (expectedState2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                seekableTransitionState2 = seekableTransitionState;
                if (!Intrinsics.areEqual(expectedState2, expectedState)) {
                    seekableTransitionState2.lastFrameTimeNanos = Long.MIN_VALUE;
                    throw new CancellationException("targetState while waiting for composition");
                }
                return Unit.INSTANCE;
            case 1:
                seekableTransitionState = this;
                expectedState = c01721.L$0;
                ResultKt.throwOnFailure($result);
                c01721.L$0 = expectedState;
                c01721.label = 2;
                Continuation uCont$iv2 = c01721;
                CancellableContinuationImpl cancellable$iv2 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(uCont$iv2), 1);
                cancellable$iv2.initCancellability();
                CancellableContinuationImpl continuation3 = cancellable$iv2;
                seekableTransitionState.setCompositionContinuation$animation_core(continuation3);
                Mutex.DefaultImpls.unlock$default(seekableTransitionState.getCompositionContinuationMutex(), null, 1, null);
                expectedState2 = cancellable$iv2.getResult();
                if (expectedState2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(c01721);
                }
                if (expectedState2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                seekableTransitionState2 = seekableTransitionState;
                if (!Intrinsics.areEqual(expectedState2, expectedState)) {
                    seekableTransitionState2.lastFrameTimeNanos = Long.MIN_VALUE;
                    throw new CancellationException("targetState while waiting for composition");
                }
                return Unit.INSTANCE;
            case 2:
                seekableTransitionState2 = this;
                Object expectedState3 = c01721.L$0;
                ResultKt.throwOnFailure($result);
                expectedState = expectedState3;
                expectedState2 = $result;
                if (!Intrinsics.areEqual(expectedState2, expectedState)) {
                    seekableTransitionState2.lastFrameTimeNanos = Long.MIN_VALUE;
                    throw new CancellationException("targetState while waiting for composition");
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:17:0x0062  */
    public final void moveAnimationToInitialState() {
        Transition<S> transition = this.transition;
        if (transition == null) {
            return;
        }
        SeekingAnimationState it = this.currentAnimation;
        if (it == null) {
            if (this.totalDurationNanos <= 0) {
                it = null;
            } else {
                if ((getFraction() == 1.0f) || Intrinsics.areEqual(getCurrentState(), getTargetState())) {
                    it = null;
                } else {
                    it = new SeekingAnimationState();
                    it.setValue(getFraction());
                    long totalDurationNanos = this.totalDurationNanos;
                    it.setDurationNanos(totalDurationNanos);
                    it.setAnimationSpecDuration(MathKt.roundToLong(totalDurationNanos * (1.0d - ((double) getFraction()))));
                    it.getStart().set$animation_core(0, getFraction());
                }
            }
        }
        if (it != null) {
            it.setDurationNanos(this.totalDurationNanos);
            this.initialValueAnimations.add(it);
            transition.setInitialAnimations$animation_core(it);
        }
        this.currentAnimation = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateTo$default(SeekableTransitionState seekableTransitionState, Object obj, FiniteAnimationSpec finiteAnimationSpec, Continuation continuation, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = seekableTransitionState.getTargetState();
        }
        if ((i & 2) != 0) {
            finiteAnimationSpec = null;
        }
        return seekableTransitionState.animateTo(obj, finiteAnimationSpec, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$animateTo$2, reason: invalid class name */
    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$animateTo$2", f = "Transition.kt", i = {}, l = {600}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
        final /* synthetic */ S $targetState;
        final /* synthetic */ Transition<S> $transition;
        int label;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Transition<S> transition, SeekableTransitionState<S> seekableTransitionState, S s, FiniteAnimationSpec<Float> finiteAnimationSpec, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.$transition = transition;
            this.this$0 = seekableTransitionState;
            this.$targetState = s;
            this.$animationSpec = finiteAnimationSpec;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass2(this.$transition, this.this$0, this.$targetState, this.$animationSpec, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$animateTo$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: Transition.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$animateTo$2$1", f = "Transition.kt", i = {0}, l = {2174, 613, 615, 669, 671}, m = "invokeSuspend", n = {"$this$withLock_u24default$iv"}, s = {"L$0"}, v = 1)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
            final /* synthetic */ S $targetState;
            final /* synthetic */ Transition<S> $transition;
            Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ SeekableTransitionState<S> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(SeekableTransitionState<S> seekableTransitionState, S s, Transition<S> transition, FiniteAnimationSpec<Float> finiteAnimationSpec, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = seekableTransitionState;
                this.$targetState = s;
                this.$transition = transition;
                this.$animationSpec = finiteAnimationSpec;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$targetState, this.$transition, this.$animationSpec, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:21:0x00ab  */
            /* JADX WARN: Code duplicated, block: B:24:0x00bf A[PHI: r2
  0x00bf: PHI (r2v4 '$result' java.lang.Object) = (r2v2 '$result' java.lang.Object), (r2v5 '$result' java.lang.Object) binds: [B:22:0x00bb, B:9:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:28:0x00d0 A[PHI: r2
  0x00d0: PHI (r2v6 '$result' java.lang.Object) = (r2v2 '$result' java.lang.Object), (r2v4 '$result' java.lang.Object), (r2v7 '$result' java.lang.Object) binds: [B:20:0x00a9, B:25:0x00cb, B:8:0x0026] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:30:0x00de  */
            /* JADX WARN: Code duplicated, block: B:32:0x00ea  */
            /* JADX WARN: Code duplicated, block: B:34:0x00f4  */
            /* JADX WARN: Code duplicated, block: B:35:0x00ff  */
            /* JADX WARN: Code duplicated, block: B:37:0x0102  */
            /* JADX WARN: Code duplicated, block: B:40:0x010e A[DONT_INVERT] */
            /* JADX WARN: Code duplicated, block: B:41:0x0110  */
            /* JADX WARN: Code duplicated, block: B:42:0x0115  */
            /* JADX WARN: Code duplicated, block: B:45:0x011b  */
            /* JADX WARN: Code duplicated, block: B:47:0x0137  */
            /* JADX WARN: Code duplicated, block: B:49:0x014c  */
            /* JADX WARN: Code duplicated, block: B:50:0x014e  */
            /* JADX WARN: Code duplicated, block: B:60:0x0184  */
            /* JADX WARN: Code duplicated, block: B:62:0x018e  */
            /* JADX WARN: Code duplicated, block: B:65:0x01c5  */
            /* JADX WARN: Code duplicated, block: B:66:0x01dc  */
            /* JADX WARN: Code duplicated, block: B:71:0x020d A[PHI: r2
  0x020d: PHI (r2v8 '$result' java.lang.Object) = (r2v6 '$result' java.lang.Object), (r2v9 '$result' java.lang.Object) binds: [B:69:0x020a, B:7:0x001f] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:74:0x0223  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object $result;
                Mutex $this$withLock_u24default$iv;
                SeekableTransitionState<S> seekableTransitionState;
                Object owner$iv;
                Object composedTargetState;
                SeekingAnimationState newAnimation;
                FiniteAnimationSpec<Float> finiteAnimationSpec;
                VectorizedFiniteAnimationSpec newSpec;
                VectorizedAnimationSpec<AnimationVector1D> animationSpec;
                VectorizedAnimationSpec<AnimationVector1D> vectorizedAnimationSpec;
                AnimationVector1D oldVelocity;
                long jRoundToLong;
                AnimationVector1D initialVelocity;
                Object $result2;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        $result = obj;
                        S targetState = this.this$0.getTargetState();
                        if (!Intrinsics.areEqual(this.$targetState, targetState)) {
                            this.this$0.moveAnimationToInitialState();
                            this.this$0.setFraction(0.0f);
                            this.$transition.updateTarget$animation_core(this.$targetState);
                            this.$transition.setPlayTimeNanos(0L);
                            this.this$0.setCurrentState$animation_core(targetState);
                            this.this$0.setTargetState$animation_core(this.$targetState);
                        }
                        $this$withLock_u24default$iv = this.this$0.getCompositionContinuationMutex();
                        seekableTransitionState = this.this$0;
                        owner$iv = null;
                        this.L$0 = $this$withLock_u24default$iv;
                        this.L$1 = seekableTransitionState;
                        this.label = 1;
                        if ($this$withLock_u24default$iv.lock(null, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        try {
                            composedTargetState = seekableTransitionState.getComposedTargetState$animation_core();
                            $this$withLock_u24default$iv.unlock(owner$iv);
                            if (!Intrinsics.areEqual(this.$targetState, composedTargetState)) {
                                if (!Intrinsics.areEqual(this.this$0.getCurrentState(), this.$targetState)) {
                                    if (this.this$0.getFraction() < 1.0f) {
                                        newAnimation = ((SeekableTransitionState) this.this$0).currentAnimation;
                                        finiteAnimationSpec = this.$animationSpec;
                                        if (finiteAnimationSpec != null) {
                                            newSpec = finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
                                        } else {
                                            newSpec = null;
                                        }
                                        if (newAnimation != null) {
                                            if (newAnimation != null) {
                                                animationSpec = newAnimation.getAnimationSpec();
                                            } else {
                                                animationSpec = null;
                                            }
                                            vectorizedAnimationSpec = animationSpec;
                                            if (vectorizedAnimationSpec != null) {
                                                long progressNanos = newAnimation.getProgressNanos();
                                                AnimationVector1D start = newAnimation.getStart();
                                                AnimationVector1D target1 = SeekableTransitionState.Companion.getTarget1();
                                                initialVelocity = newAnimation.getInitialVelocity();
                                                if (initialVelocity == null) {
                                                    initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                                }
                                                oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos, start, target1, initialVelocity);
                                            } else if (newAnimation != null) {
                                                oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            } else {
                                                oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            if (newAnimation == null) {
                                                newAnimation = new SeekingAnimationState();
                                            }
                                            newAnimation.setAnimationSpec(newSpec);
                                            newAnimation.setComplete(false);
                                            newAnimation.setValue(this.this$0.getFraction());
                                            newAnimation.getStart().set$animation_core(0, this.this$0.getFraction());
                                            newAnimation.setDurationNanos(this.this$0.getTotalDurationNanos());
                                            newAnimation.setProgressNanos(0L);
                                            newAnimation.setInitialVelocity(oldVelocity);
                                            if (newSpec != null) {
                                                jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                            } else {
                                                jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                            }
                                            newAnimation.setAnimationSpecDuration(jRoundToLong);
                                            ((SeekableTransitionState) this.this$0).currentAnimation = newAnimation;
                                        } else {
                                            if (newAnimation != null) {
                                                animationSpec = newAnimation.getAnimationSpec();
                                            } else {
                                                animationSpec = null;
                                            }
                                            vectorizedAnimationSpec = animationSpec;
                                            if (vectorizedAnimationSpec != null) {
                                                long progressNanos2 = newAnimation.getProgressNanos();
                                                AnimationVector1D start2 = newAnimation.getStart();
                                                AnimationVector1D target2 = SeekableTransitionState.Companion.getTarget1();
                                                initialVelocity = newAnimation.getInitialVelocity();
                                                if (initialVelocity == null) {
                                                    initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                                }
                                                oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos2, start2, target2, initialVelocity);
                                            } else if (newAnimation != null) {
                                                oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            } else {
                                                oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            if (newAnimation == null) {
                                                newAnimation = new SeekingAnimationState();
                                            }
                                            newAnimation.setAnimationSpec(newSpec);
                                            newAnimation.setComplete(false);
                                            newAnimation.setValue(this.this$0.getFraction());
                                            newAnimation.getStart().set$animation_core(0, this.this$0.getFraction());
                                            newAnimation.setDurationNanos(this.this$0.getTotalDurationNanos());
                                            newAnimation.setProgressNanos(0L);
                                            newAnimation.setInitialVelocity(oldVelocity);
                                            if (newSpec != null) {
                                                jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                            } else {
                                                jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                            }
                                            newAnimation.setAnimationSpecDuration(jRoundToLong);
                                            ((SeekableTransitionState) this.this$0).currentAnimation = newAnimation;
                                        }
                                    }
                                    this.L$0 = null;
                                    this.L$1 = null;
                                    this.label = 4;
                                    if (this.this$0.runAnimations(this) != coroutine_suspended) {
                                        this.this$0.setCurrentState$animation_core(this.$targetState);
                                        this.label = 5;
                                        if (this.this$0.waitForComposition(this) != coroutine_suspended) {
                                            $result2 = $result;
                                            this.this$0.setFraction(0.0f);
                                        }
                                    }
                                }
                                Object $result3 = Unit.INSTANCE;
                                return $result3;
                            }
                            this.L$0 = null;
                            this.L$1 = null;
                            this.label = 2;
                            if (this.this$0.doOneFrame(this) != coroutine_suspended) {
                                this.label = 3;
                                if (this.this$0.waitForCompositionAfterTargetStateChange(this) != coroutine_suspended) {
                                    if (!Intrinsics.areEqual(this.this$0.getCurrentState(), this.$targetState)) {
                                        if (this.this$0.getFraction() < 1.0f) {
                                            newAnimation = ((SeekableTransitionState) this.this$0).currentAnimation;
                                            finiteAnimationSpec = this.$animationSpec;
                                            if (finiteAnimationSpec != null) {
                                                newSpec = finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
                                            } else {
                                                newSpec = null;
                                            }
                                            if (newAnimation != null || !Intrinsics.areEqual(newSpec, newAnimation.getAnimationSpec())) {
                                                if (newAnimation != null) {
                                                    animationSpec = newAnimation.getAnimationSpec();
                                                } else {
                                                    animationSpec = null;
                                                }
                                                vectorizedAnimationSpec = animationSpec;
                                                if (vectorizedAnimationSpec != null) {
                                                    long progressNanos3 = newAnimation.getProgressNanos();
                                                    AnimationVector1D start3 = newAnimation.getStart();
                                                    AnimationVector1D target3 = SeekableTransitionState.Companion.getTarget1();
                                                    initialVelocity = newAnimation.getInitialVelocity();
                                                    if (initialVelocity == null) {
                                                        initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                                    }
                                                    oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos3, start3, target3, initialVelocity);
                                                } else if (newAnimation != null || newAnimation.getProgressNanos() == 0) {
                                                    oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                                } else {
                                                    long oldDurationNanos = newAnimation.getDurationNanos();
                                                    if (oldDurationNanos == Long.MIN_VALUE) {
                                                        oldDurationNanos = this.this$0.getTotalDurationNanos();
                                                    }
                                                    float oldDuration = oldDurationNanos / 1.0E9f;
                                                    oldVelocity = oldDuration <= 0.0f ? SeekableTransitionState.Companion.getZeroVelocity() : new AnimationVector1D(1.0f / oldDuration);
                                                }
                                                if (newAnimation == null) {
                                                    newAnimation = new SeekingAnimationState();
                                                }
                                                newAnimation.setAnimationSpec(newSpec);
                                                newAnimation.setComplete(false);
                                                newAnimation.setValue(this.this$0.getFraction());
                                                newAnimation.getStart().set$animation_core(0, this.this$0.getFraction());
                                                newAnimation.setDurationNanos(this.this$0.getTotalDurationNanos());
                                                newAnimation.setProgressNanos(0L);
                                                newAnimation.setInitialVelocity(oldVelocity);
                                                if (newSpec != null) {
                                                    jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                                } else {
                                                    jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                                }
                                                newAnimation.setAnimationSpecDuration(jRoundToLong);
                                                ((SeekableTransitionState) this.this$0).currentAnimation = newAnimation;
                                            }
                                        }
                                        this.L$0 = null;
                                        this.L$1 = null;
                                        this.label = 4;
                                        if (this.this$0.runAnimations(this) != coroutine_suspended) {
                                            this.this$0.setCurrentState$animation_core(this.$targetState);
                                            this.label = 5;
                                            if (this.this$0.waitForComposition(this) != coroutine_suspended) {
                                                $result2 = $result;
                                                this.this$0.setFraction(0.0f);
                                            }
                                        }
                                    }
                                    Object $result4 = Unit.INSTANCE;
                                    return $result4;
                                }
                            }
                            return coroutine_suspended;
                        } catch (Throwable th) {
                            $this$withLock_u24default$iv.unlock(owner$iv);
                            throw th;
                        }
                    case 1:
                        $result = obj;
                        seekableTransitionState = (SeekableTransitionState) this.L$1;
                        owner$iv = null;
                        $this$withLock_u24default$iv = (Mutex) this.L$0;
                        ResultKt.throwOnFailure($result);
                        composedTargetState = seekableTransitionState.getComposedTargetState$animation_core();
                        $this$withLock_u24default$iv.unlock(owner$iv);
                        if (!Intrinsics.areEqual(this.$targetState, composedTargetState)) {
                            if (!Intrinsics.areEqual(this.this$0.getCurrentState(), this.$targetState)) {
                                if (this.this$0.getFraction() < 1.0f) {
                                    newAnimation = ((SeekableTransitionState) this.this$0).currentAnimation;
                                    finiteAnimationSpec = this.$animationSpec;
                                    if (finiteAnimationSpec != null) {
                                        newSpec = finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
                                    } else {
                                        newSpec = null;
                                    }
                                    if (newAnimation != null) {
                                        if (newAnimation != null) {
                                            animationSpec = newAnimation.getAnimationSpec();
                                        } else {
                                            animationSpec = null;
                                        }
                                        vectorizedAnimationSpec = animationSpec;
                                        if (vectorizedAnimationSpec != null) {
                                            long progressNanos4 = newAnimation.getProgressNanos();
                                            AnimationVector1D start4 = newAnimation.getStart();
                                            AnimationVector1D target4 = SeekableTransitionState.Companion.getTarget1();
                                            initialVelocity = newAnimation.getInitialVelocity();
                                            if (initialVelocity == null) {
                                                initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos4, start4, target4, initialVelocity);
                                        } else if (newAnimation != null) {
                                            oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        } else {
                                            oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        if (newAnimation == null) {
                                            newAnimation = new SeekingAnimationState();
                                        }
                                        newAnimation.setAnimationSpec(newSpec);
                                        newAnimation.setComplete(false);
                                        newAnimation.setValue(this.this$0.getFraction());
                                        newAnimation.getStart().set$animation_core(0, this.this$0.getFraction());
                                        newAnimation.setDurationNanos(this.this$0.getTotalDurationNanos());
                                        newAnimation.setProgressNanos(0L);
                                        newAnimation.setInitialVelocity(oldVelocity);
                                        if (newSpec != null) {
                                            jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                        } else {
                                            jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                        }
                                        newAnimation.setAnimationSpecDuration(jRoundToLong);
                                        ((SeekableTransitionState) this.this$0).currentAnimation = newAnimation;
                                    } else {
                                        if (newAnimation != null) {
                                            animationSpec = newAnimation.getAnimationSpec();
                                        } else {
                                            animationSpec = null;
                                        }
                                        vectorizedAnimationSpec = animationSpec;
                                        if (vectorizedAnimationSpec != null) {
                                            long progressNanos5 = newAnimation.getProgressNanos();
                                            AnimationVector1D start5 = newAnimation.getStart();
                                            AnimationVector1D target5 = SeekableTransitionState.Companion.getTarget1();
                                            initialVelocity = newAnimation.getInitialVelocity();
                                            if (initialVelocity == null) {
                                                initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos5, start5, target5, initialVelocity);
                                        } else if (newAnimation != null) {
                                            oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        } else {
                                            oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        if (newAnimation == null) {
                                            newAnimation = new SeekingAnimationState();
                                        }
                                        newAnimation.setAnimationSpec(newSpec);
                                        newAnimation.setComplete(false);
                                        newAnimation.setValue(this.this$0.getFraction());
                                        newAnimation.getStart().set$animation_core(0, this.this$0.getFraction());
                                        newAnimation.setDurationNanos(this.this$0.getTotalDurationNanos());
                                        newAnimation.setProgressNanos(0L);
                                        newAnimation.setInitialVelocity(oldVelocity);
                                        if (newSpec != null) {
                                            jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                        } else {
                                            jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                        }
                                        newAnimation.setAnimationSpecDuration(jRoundToLong);
                                        ((SeekableTransitionState) this.this$0).currentAnimation = newAnimation;
                                    }
                                }
                                this.L$0 = null;
                                this.L$1 = null;
                                this.label = 4;
                                if (this.this$0.runAnimations(this) != coroutine_suspended) {
                                    this.this$0.setCurrentState$animation_core(this.$targetState);
                                    this.label = 5;
                                    if (this.this$0.waitForComposition(this) != coroutine_suspended) {
                                        $result2 = $result;
                                        this.this$0.setFraction(0.0f);
                                    }
                                }
                            }
                            Object $result5 = Unit.INSTANCE;
                            return $result5;
                        }
                        this.L$0 = null;
                        this.L$1 = null;
                        this.label = 2;
                        if (this.this$0.doOneFrame(this) != coroutine_suspended) {
                            this.label = 3;
                            if (this.this$0.waitForCompositionAfterTargetStateChange(this) != coroutine_suspended) {
                                if (!Intrinsics.areEqual(this.this$0.getCurrentState(), this.$targetState)) {
                                    if (this.this$0.getFraction() < 1.0f) {
                                        newAnimation = ((SeekableTransitionState) this.this$0).currentAnimation;
                                        finiteAnimationSpec = this.$animationSpec;
                                        if (finiteAnimationSpec != null) {
                                            newSpec = finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
                                        } else {
                                            newSpec = null;
                                        }
                                        if (newAnimation != null) {
                                            if (newAnimation != null) {
                                                animationSpec = newAnimation.getAnimationSpec();
                                            } else {
                                                animationSpec = null;
                                            }
                                            vectorizedAnimationSpec = animationSpec;
                                            if (vectorizedAnimationSpec != null) {
                                                long progressNanos6 = newAnimation.getProgressNanos();
                                                AnimationVector1D start6 = newAnimation.getStart();
                                                AnimationVector1D target6 = SeekableTransitionState.Companion.getTarget1();
                                                initialVelocity = newAnimation.getInitialVelocity();
                                                if (initialVelocity == null) {
                                                    initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                                }
                                                oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos6, start6, target6, initialVelocity);
                                            } else if (newAnimation != null) {
                                                oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            } else {
                                                oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            if (newAnimation == null) {
                                                newAnimation = new SeekingAnimationState();
                                            }
                                            newAnimation.setAnimationSpec(newSpec);
                                            newAnimation.setComplete(false);
                                            newAnimation.setValue(this.this$0.getFraction());
                                            newAnimation.getStart().set$animation_core(0, this.this$0.getFraction());
                                            newAnimation.setDurationNanos(this.this$0.getTotalDurationNanos());
                                            newAnimation.setProgressNanos(0L);
                                            newAnimation.setInitialVelocity(oldVelocity);
                                            if (newSpec != null) {
                                                jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                            } else {
                                                jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                            }
                                            newAnimation.setAnimationSpecDuration(jRoundToLong);
                                            ((SeekableTransitionState) this.this$0).currentAnimation = newAnimation;
                                        } else {
                                            if (newAnimation != null) {
                                                animationSpec = newAnimation.getAnimationSpec();
                                            } else {
                                                animationSpec = null;
                                            }
                                            vectorizedAnimationSpec = animationSpec;
                                            if (vectorizedAnimationSpec != null) {
                                                long progressNanos7 = newAnimation.getProgressNanos();
                                                AnimationVector1D start7 = newAnimation.getStart();
                                                AnimationVector1D target7 = SeekableTransitionState.Companion.getTarget1();
                                                initialVelocity = newAnimation.getInitialVelocity();
                                                if (initialVelocity == null) {
                                                    initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                                }
                                                oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos7, start7, target7, initialVelocity);
                                            } else if (newAnimation != null) {
                                                oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            } else {
                                                oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            if (newAnimation == null) {
                                                newAnimation = new SeekingAnimationState();
                                            }
                                            newAnimation.setAnimationSpec(newSpec);
                                            newAnimation.setComplete(false);
                                            newAnimation.setValue(this.this$0.getFraction());
                                            newAnimation.getStart().set$animation_core(0, this.this$0.getFraction());
                                            newAnimation.setDurationNanos(this.this$0.getTotalDurationNanos());
                                            newAnimation.setProgressNanos(0L);
                                            newAnimation.setInitialVelocity(oldVelocity);
                                            if (newSpec != null) {
                                                jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                            } else {
                                                jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                            }
                                            newAnimation.setAnimationSpecDuration(jRoundToLong);
                                            ((SeekableTransitionState) this.this$0).currentAnimation = newAnimation;
                                        }
                                    }
                                    this.L$0 = null;
                                    this.L$1 = null;
                                    this.label = 4;
                                    if (this.this$0.runAnimations(this) != coroutine_suspended) {
                                        this.this$0.setCurrentState$animation_core(this.$targetState);
                                        this.label = 5;
                                        if (this.this$0.waitForComposition(this) != coroutine_suspended) {
                                            $result2 = $result;
                                            this.this$0.setFraction(0.0f);
                                        }
                                    }
                                }
                                Object $result6 = Unit.INSTANCE;
                                return $result6;
                            }
                        }
                        return coroutine_suspended;
                    case 2:
                        $result = obj;
                        ResultKt.throwOnFailure($result);
                        this.label = 3;
                        if (this.this$0.waitForCompositionAfterTargetStateChange(this) != coroutine_suspended) {
                            if (!Intrinsics.areEqual(this.this$0.getCurrentState(), this.$targetState)) {
                                if (this.this$0.getFraction() < 1.0f) {
                                    newAnimation = ((SeekableTransitionState) this.this$0).currentAnimation;
                                    finiteAnimationSpec = this.$animationSpec;
                                    if (finiteAnimationSpec != null) {
                                        newSpec = finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
                                    } else {
                                        newSpec = null;
                                    }
                                    if (newAnimation != null) {
                                        if (newAnimation != null) {
                                            animationSpec = newAnimation.getAnimationSpec();
                                        } else {
                                            animationSpec = null;
                                        }
                                        vectorizedAnimationSpec = animationSpec;
                                        if (vectorizedAnimationSpec != null) {
                                            long progressNanos8 = newAnimation.getProgressNanos();
                                            AnimationVector1D start8 = newAnimation.getStart();
                                            AnimationVector1D target8 = SeekableTransitionState.Companion.getTarget1();
                                            initialVelocity = newAnimation.getInitialVelocity();
                                            if (initialVelocity == null) {
                                                initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos8, start8, target8, initialVelocity);
                                        } else if (newAnimation != null) {
                                            oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        } else {
                                            oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        if (newAnimation == null) {
                                            newAnimation = new SeekingAnimationState();
                                        }
                                        newAnimation.setAnimationSpec(newSpec);
                                        newAnimation.setComplete(false);
                                        newAnimation.setValue(this.this$0.getFraction());
                                        newAnimation.getStart().set$animation_core(0, this.this$0.getFraction());
                                        newAnimation.setDurationNanos(this.this$0.getTotalDurationNanos());
                                        newAnimation.setProgressNanos(0L);
                                        newAnimation.setInitialVelocity(oldVelocity);
                                        if (newSpec != null) {
                                            jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                        } else {
                                            jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                        }
                                        newAnimation.setAnimationSpecDuration(jRoundToLong);
                                        ((SeekableTransitionState) this.this$0).currentAnimation = newAnimation;
                                    } else {
                                        if (newAnimation != null) {
                                            animationSpec = newAnimation.getAnimationSpec();
                                        } else {
                                            animationSpec = null;
                                        }
                                        vectorizedAnimationSpec = animationSpec;
                                        if (vectorizedAnimationSpec != null) {
                                            long progressNanos9 = newAnimation.getProgressNanos();
                                            AnimationVector1D start9 = newAnimation.getStart();
                                            AnimationVector1D target9 = SeekableTransitionState.Companion.getTarget1();
                                            initialVelocity = newAnimation.getInitialVelocity();
                                            if (initialVelocity == null) {
                                                initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos9, start9, target9, initialVelocity);
                                        } else if (newAnimation != null) {
                                            oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        } else {
                                            oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        if (newAnimation == null) {
                                            newAnimation = new SeekingAnimationState();
                                        }
                                        newAnimation.setAnimationSpec(newSpec);
                                        newAnimation.setComplete(false);
                                        newAnimation.setValue(this.this$0.getFraction());
                                        newAnimation.getStart().set$animation_core(0, this.this$0.getFraction());
                                        newAnimation.setDurationNanos(this.this$0.getTotalDurationNanos());
                                        newAnimation.setProgressNanos(0L);
                                        newAnimation.setInitialVelocity(oldVelocity);
                                        if (newSpec != null) {
                                            jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                        } else {
                                            jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                        }
                                        newAnimation.setAnimationSpecDuration(jRoundToLong);
                                        ((SeekableTransitionState) this.this$0).currentAnimation = newAnimation;
                                    }
                                }
                                this.L$0 = null;
                                this.L$1 = null;
                                this.label = 4;
                                if (this.this$0.runAnimations(this) != coroutine_suspended) {
                                    this.this$0.setCurrentState$animation_core(this.$targetState);
                                    this.label = 5;
                                    if (this.this$0.waitForComposition(this) != coroutine_suspended) {
                                        $result2 = $result;
                                        this.this$0.setFraction(0.0f);
                                    }
                                }
                            }
                            Object $result7 = Unit.INSTANCE;
                            return $result7;
                        }
                        return coroutine_suspended;
                    case 3:
                        $result = obj;
                        ResultKt.throwOnFailure($result);
                        if (!Intrinsics.areEqual(this.this$0.getCurrentState(), this.$targetState)) {
                            if (this.this$0.getFraction() < 1.0f) {
                                newAnimation = ((SeekableTransitionState) this.this$0).currentAnimation;
                                finiteAnimationSpec = this.$animationSpec;
                                if (finiteAnimationSpec != null) {
                                    newSpec = finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
                                } else {
                                    newSpec = null;
                                }
                                if (newAnimation != null) {
                                    if (newAnimation != null) {
                                        animationSpec = newAnimation.getAnimationSpec();
                                    } else {
                                        animationSpec = null;
                                    }
                                    vectorizedAnimationSpec = animationSpec;
                                    if (vectorizedAnimationSpec != null) {
                                        long progressNanos10 = newAnimation.getProgressNanos();
                                        AnimationVector1D start10 = newAnimation.getStart();
                                        AnimationVector1D target10 = SeekableTransitionState.Companion.getTarget1();
                                        initialVelocity = newAnimation.getInitialVelocity();
                                        if (initialVelocity == null) {
                                            initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos10, start10, target10, initialVelocity);
                                    } else if (newAnimation != null) {
                                        oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    } else {
                                        oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    }
                                    if (newAnimation == null) {
                                        newAnimation = new SeekingAnimationState();
                                    }
                                    newAnimation.setAnimationSpec(newSpec);
                                    newAnimation.setComplete(false);
                                    newAnimation.setValue(this.this$0.getFraction());
                                    newAnimation.getStart().set$animation_core(0, this.this$0.getFraction());
                                    newAnimation.setDurationNanos(this.this$0.getTotalDurationNanos());
                                    newAnimation.setProgressNanos(0L);
                                    newAnimation.setInitialVelocity(oldVelocity);
                                    if (newSpec != null) {
                                        jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                    } else {
                                        jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                    }
                                    newAnimation.setAnimationSpecDuration(jRoundToLong);
                                    ((SeekableTransitionState) this.this$0).currentAnimation = newAnimation;
                                } else {
                                    if (newAnimation != null) {
                                        animationSpec = newAnimation.getAnimationSpec();
                                    } else {
                                        animationSpec = null;
                                    }
                                    vectorizedAnimationSpec = animationSpec;
                                    if (vectorizedAnimationSpec != null) {
                                        long progressNanos11 = newAnimation.getProgressNanos();
                                        AnimationVector1D start11 = newAnimation.getStart();
                                        AnimationVector1D target11 = SeekableTransitionState.Companion.getTarget1();
                                        initialVelocity = newAnimation.getInitialVelocity();
                                        if (initialVelocity == null) {
                                            initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        oldVelocity = (AnimationVector1D) vectorizedAnimationSpec.getVelocityFromNanos(progressNanos11, start11, target11, initialVelocity);
                                    } else if (newAnimation != null) {
                                        oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    } else {
                                        oldVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    }
                                    if (newAnimation == null) {
                                        newAnimation = new SeekingAnimationState();
                                    }
                                    newAnimation.setAnimationSpec(newSpec);
                                    newAnimation.setComplete(false);
                                    newAnimation.setValue(this.this$0.getFraction());
                                    newAnimation.getStart().set$animation_core(0, this.this$0.getFraction());
                                    newAnimation.setDurationNanos(this.this$0.getTotalDurationNanos());
                                    newAnimation.setProgressNanos(0L);
                                    newAnimation.setInitialVelocity(oldVelocity);
                                    if (newSpec != null) {
                                        jRoundToLong = newSpec.getDurationNanos(newAnimation.getStart(), SeekableTransitionState.Companion.getTarget1(), oldVelocity);
                                    } else {
                                        jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                    }
                                    newAnimation.setAnimationSpecDuration(jRoundToLong);
                                    ((SeekableTransitionState) this.this$0).currentAnimation = newAnimation;
                                }
                            }
                            this.L$0 = null;
                            this.L$1 = null;
                            this.label = 4;
                            if (this.this$0.runAnimations(this) != coroutine_suspended) {
                                this.this$0.setCurrentState$animation_core(this.$targetState);
                                this.label = 5;
                                if (this.this$0.waitForComposition(this) != coroutine_suspended) {
                                    $result2 = $result;
                                    this.this$0.setFraction(0.0f);
                                }
                            }
                            return coroutine_suspended;
                        }
                        Object $result8 = Unit.INSTANCE;
                        return $result8;
                    case 4:
                        $result = obj;
                        ResultKt.throwOnFailure($result);
                        this.this$0.setCurrentState$animation_core(this.$targetState);
                        this.label = 5;
                        if (this.this$0.waitForComposition(this) != coroutine_suspended) {
                            $result2 = $result;
                            this.this$0.setFraction(0.0f);
                            Object $result9 = Unit.INSTANCE;
                            return $result9;
                        }
                        return coroutine_suspended;
                    case 5:
                        $result2 = obj;
                        ResultKt.throwOnFailure($result2);
                        this.this$0.setFraction(0.0f);
                        Object $result10 = Unit.INSTANCE;
                        return $result10;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (CoroutineScopeKt.coroutineScope(new AnonymousClass1(this.this$0, this.$targetState, this.$transition, this.$animationSpec, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            this.$transition.onTransitionEnd$animation_core();
            return Unit.INSTANCE;
        }
    }

    public final Object animateTo(S s, FiniteAnimationSpec<Float> finiteAnimationSpec, Continuation<? super Unit> continuation) {
        Transition<S> transition = this.transition;
        if (transition == null) {
            return Unit.INSTANCE;
        }
        Object objMutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new AnonymousClass2(transition, this, s, finiteAnimationSpec, null), continuation, 1, null);
        return objMutate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMutate$default : Unit.INSTANCE;
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void transitionConfigured$animation_core(Transition<S> transition) {
        boolean value$iv = this.transition == null || Intrinsics.areEqual(transition, this.transition);
        if (!value$iv) {
            PreconditionsKt.throwIllegalStateException("An instance of SeekableTransitionState has been used in different Transitions. Previous instance: " + this.transition + ", new instance: " + transition);
        }
        this.transition = transition;
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void transitionRemoved$animation_core() {
        this.transition = null;
        TransitionKt.getSeekableStateObserver().clear(this);
    }

    public final void observeTotalDuration$animation_core() {
        TransitionKt.getSeekableStateObserver().observeReads(this, TransitionKt.SeekableTransitionStateTotalDurationChanged, this.recalculateTotalDurationNanos);
    }

    public final void onTotalDurationChanged$animation_core() {
        long previousTotalDurationNanos = this.totalDurationNanos;
        observeTotalDuration$animation_core();
        if (previousTotalDurationNanos != this.totalDurationNanos) {
            SeekingAnimationState animation = this.currentAnimation;
            if (animation == null) {
                if (this.totalDurationNanos != 0) {
                    seekToFraction();
                }
            } else {
                if (animation.getProgressNanos() > this.totalDurationNanos) {
                    endAllAnimations();
                    return;
                }
                animation.setDurationNanos(this.totalDurationNanos);
                if (animation.getAnimationSpec() == null) {
                    animation.setAnimationSpecDuration(MathKt.roundToLong((1.0d - ((double) animation.getStart().get$animation_core(0))) * this.totalDurationNanos));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void seekToFraction() {
        Transition<S> transition = this.transition;
        if (transition == null) {
            return;
        }
        long playTimeNanos = MathKt.roundToLong(((double) getFraction()) * transition.getTotalDurationNanos());
        transition.seekAnimations$animation_core(playTimeNanos);
    }

    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010*\u001a\u00020+H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\"\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R\u001a\u0010$\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0007\"\u0004\b&\u0010\tR\u001a\u0010'\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0007\"\u0004\b)\u0010\t¨\u0006,"}, d2 = {"Landroidx/compose/animation/core/SeekableTransitionState$SeekingAnimationState;", "", "<init>", "()V", "progressNanos", "", "getProgressNanos", "()J", "setProgressNanos", "(J)V", "animationSpec", "Landroidx/compose/animation/core/VectorizedAnimationSpec;", "Landroidx/compose/animation/core/AnimationVector1D;", "getAnimationSpec", "()Landroidx/compose/animation/core/VectorizedAnimationSpec;", "setAnimationSpec", "(Landroidx/compose/animation/core/VectorizedAnimationSpec;)V", "isComplete", "", "()Z", "setComplete", "(Z)V", "value", "", "getValue", "()F", "setValue", "(F)V", TtmlNode.START, "getStart", "()Landroidx/compose/animation/core/AnimationVector1D;", "setStart", "(Landroidx/compose/animation/core/AnimationVector1D;)V", "initialVelocity", "getInitialVelocity", "setInitialVelocity", "durationNanos", "getDurationNanos", "setDurationNanos", "animationSpecDuration", "getAnimationSpecDuration", "setAnimationSpecDuration", "toString", "", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class SeekingAnimationState {
        public static final int $stable = 8;
        private VectorizedAnimationSpec<AnimationVector1D> animationSpec;
        private long animationSpecDuration;
        private long durationNanos;
        private AnimationVector1D initialVelocity;
        private boolean isComplete;
        private long progressNanos;
        private AnimationVector1D start = new AnimationVector1D(0.0f);
        private float value;

        public final long getProgressNanos() {
            return this.progressNanos;
        }

        public final void setProgressNanos(long j) {
            this.progressNanos = j;
        }

        public final VectorizedAnimationSpec<AnimationVector1D> getAnimationSpec() {
            return this.animationSpec;
        }

        public final void setAnimationSpec(VectorizedAnimationSpec<AnimationVector1D> vectorizedAnimationSpec) {
            this.animationSpec = vectorizedAnimationSpec;
        }

        /* JADX INFO: renamed from: isComplete, reason: from getter */
        public final boolean getIsComplete() {
            return this.isComplete;
        }

        public final void setComplete(boolean z) {
            this.isComplete = z;
        }

        public final float getValue() {
            return this.value;
        }

        public final void setValue(float f) {
            this.value = f;
        }

        public final AnimationVector1D getStart() {
            return this.start;
        }

        public final void setStart(AnimationVector1D animationVector1D) {
            this.start = animationVector1D;
        }

        public final AnimationVector1D getInitialVelocity() {
            return this.initialVelocity;
        }

        public final void setInitialVelocity(AnimationVector1D animationVector1D) {
            this.initialVelocity = animationVector1D;
        }

        public final long getDurationNanos() {
            return this.durationNanos;
        }

        public final void setDurationNanos(long j) {
            this.durationNanos = j;
        }

        public final long getAnimationSpecDuration() {
            return this.animationSpecDuration;
        }

        public final void setAnimationSpecDuration(long j) {
            this.animationSpecDuration = j;
        }

        public String toString() {
            return "progress nanos: " + this.progressNanos + ", animationSpec: " + this.animationSpec + ", isComplete: " + this.isComplete + ", value: " + this.value + ", start: " + this.start + ", initialVelocity: " + this.initialVelocity + ", durationNanos: " + this.durationNanos + ", animationSpecDuration: " + this.animationSpecDuration;
        }
    }

    /* JADX INFO: compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/animation/core/SeekableTransitionState$Companion;", "", "<init>", "()V", "ZeroVelocity", "Landroidx/compose/animation/core/AnimationVector1D;", "getZeroVelocity", "()Landroidx/compose/animation/core/AnimationVector1D;", "Target1", "getTarget1", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AnimationVector1D getZeroVelocity() {
            return SeekableTransitionState.ZeroVelocity;
        }

        public final AnimationVector1D getTarget1() {
            return SeekableTransitionState.Target1;
        }
    }
}
