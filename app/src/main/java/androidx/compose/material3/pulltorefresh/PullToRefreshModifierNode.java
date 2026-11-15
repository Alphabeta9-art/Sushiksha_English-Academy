package androidx.compose.material3.pulltorefresh;

import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollNodeKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PullToRefresh.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\b\u00108\u001a\u00020\u0007H\u0016J\u001f\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020:2\u0006\u0010<\u001a\u00020=H\u0016¢\u0006\u0004\b>\u0010?J'\u0010@\u001a\u00020:2\u0006\u0010A\u001a\u00020:2\u0006\u0010;\u001a\u00020:2\u0006\u0010<\u001a\u00020=H\u0016¢\u0006\u0004\bB\u0010CJ\u0018\u0010D\u001a\u00020E2\u0006\u0010;\u001a\u00020EH\u0096@¢\u0006\u0004\bF\u0010GJ\u0006\u0010H\u001a\u00020\u0007J\u0017\u0010I\u001a\u00020:2\u0006\u0010;\u001a\u00020:H\u0002¢\u0006\u0004\bJ\u0010KJ\u0016\u0010L\u001a\u00020&2\u0006\u0010M\u001a\u00020&H\u0082@¢\u0006\u0002\u0010NJ\b\u0010O\u001a\u00020&H\u0002J\u000e\u0010P\u001a\u00020\u0007H\u0082@¢\u0006\u0002\u0010QJ\u000e\u0010R\u001a\u00020\u0007H\u0082@¢\u0006\u0002\u0010QR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u000fR\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010'\u001a\u00020&2\u0006\u0010%\u001a\u00020&8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b(\u0010\u001d\"\u0004\b)\u0010\u001fR+\u0010,\u001a\u00020&2\u0006\u0010%\u001a\u00020&8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b/\u0010+\u001a\u0004\b-\u0010\u001d\"\u0004\b.\u0010\u001fR\u0014\u00100\u001a\u00020&8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b1\u0010\u001dR\u0014\u00102\u001a\u0002038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b4\u00105R\u0014\u00106\u001a\u00020&8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b7\u0010\u001d¨\u0006S"}, d2 = {"Landroidx/compose/material3/pulltorefresh/PullToRefreshModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "isRefreshing", "", "onRefresh", "Lkotlin/Function0;", "", "enabled", "state", "Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "threshold", "Landroidx/compose/ui/unit/Dp;", "<init>", "(ZLkotlin/jvm/functions/Function0;ZLandroidx/compose/material3/pulltorefresh/PullToRefreshState;FLkotlin/jvm/internal/DefaultConstructorMarker;)V", "()Z", "setRefreshing", "(Z)V", "getOnRefresh", "()Lkotlin/jvm/functions/Function0;", "setOnRefresh", "(Lkotlin/jvm/functions/Function0;)V", "getEnabled", "setEnabled", "getState", "()Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "setState", "(Landroidx/compose/material3/pulltorefresh/PullToRefreshState;)V", "getThreshold-D9Ej5fM", "()F", "setThreshold-0680j_4", "(F)V", "F", "shouldAutoInvalidate", "getShouldAutoInvalidate", "nestedScrollNode", "Landroidx/compose/ui/node/DelegatableNode;", "<set-?>", "", "verticalOffset", "getVerticalOffset", "setVerticalOffset", "verticalOffset$delegate", "Landroidx/compose/runtime/MutableFloatState;", "distancePulled", "getDistancePulled", "setDistancePulled", "distancePulled$delegate", "adjustedDistancePulled", "getAdjustedDistancePulled", "thresholdPx", "", "getThresholdPx", "()I", "progress", "getProgress", "onAttach", "onPreScroll", "Landroidx/compose/ui/geometry/Offset;", "available", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPreScroll-OzD1aCk", "(JI)J", "onPostScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "consumeAvailableOffset", "consumeAvailableOffset-MK-Hz9U", "(J)J", "onRelease", "velocity", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculateVerticalOffset", "animateToThreshold", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateToHidden", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PullToRefreshModifierNode extends DelegatingNode implements NestedScrollConnection {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: distancePulled$delegate, reason: from kotlin metadata */
    private final MutableFloatState distancePulled;
    private boolean enabled;
    private boolean isRefreshing;
    private DelegatableNode nestedScrollNode;
    private Function0<Unit> onRefresh;
    private PullToRefreshState state;
    private float threshold;

    /* JADX INFO: renamed from: verticalOffset$delegate, reason: from kotlin metadata */
    private final MutableFloatState verticalOffset;

    /* JADX INFO: renamed from: androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$animateToHidden$1, reason: invalid class name */
    /* JADX INFO: compiled from: PullToRefresh.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode", f = "PullToRefresh.kt", i = {}, l = {384}, m = "animateToHidden", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PullToRefreshModifierNode.this.animateToHidden(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$animateToThreshold$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PullToRefresh.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode", f = "PullToRefresh.kt", i = {}, l = {371}, m = "animateToThreshold", n = {}, s = {})
    static final class C03381 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C03381(Continuation<? super C03381> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PullToRefreshModifierNode.this.animateToThreshold(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$onRelease$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PullToRefresh.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode", f = "PullToRefresh.kt", i = {0}, l = {345}, m = "onRelease", n = {"consumed"}, s = {"F$0"})
    static final class C03401 extends ContinuationImpl {
        float F$0;
        int label;
        /* synthetic */ Object result;

        C03401(Continuation<? super C03401> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PullToRefreshModifierNode.this.onRelease(0.0f, this);
        }
    }

    public /* synthetic */ PullToRefreshModifierNode(boolean z, Function0 function0, boolean z2, PullToRefreshState pullToRefreshState, float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, function0, z2, pullToRefreshState, f);
    }

    /* JADX INFO: renamed from: isRefreshing, reason: from getter */
    public final boolean getIsRefreshing() {
        return this.isRefreshing;
    }

    public final void setRefreshing(boolean z) {
        this.isRefreshing = z;
    }

    public final Function0<Unit> getOnRefresh() {
        return this.onRefresh;
    }

    public final void setOnRefresh(Function0<Unit> function0) {
        this.onRefresh = function0;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    public final PullToRefreshState getState() {
        return this.state;
    }

    public final void setState(PullToRefreshState pullToRefreshState) {
        this.state = pullToRefreshState;
    }

    /* JADX INFO: renamed from: getThreshold-D9Ej5fM, reason: not valid java name and from getter */
    public final float getThreshold() {
        return this.threshold;
    }

    /* JADX INFO: renamed from: setThreshold-0680j_4, reason: not valid java name */
    public final void m3471setThreshold0680j_4(float f) {
        this.threshold = f;
    }

    private PullToRefreshModifierNode(boolean isRefreshing, Function0<Unit> function0, boolean enabled, PullToRefreshState state, float threshold) {
        this.isRefreshing = isRefreshing;
        this.onRefresh = function0;
        this.enabled = enabled;
        this.state = state;
        this.threshold = threshold;
        this.nestedScrollNode = NestedScrollNodeKt.nestedScrollModifierNode(this, null);
        this.verticalOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.distancePulled = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getVerticalOffset() {
        FloatState $this$getValue$iv = this.verticalOffset;
        return $this$getValue$iv.getFloatValue();
    }

    private final void setVerticalOffset(float f) {
        MutableFloatState $this$setValue$iv = this.verticalOffset;
        $this$setValue$iv.setFloatValue(f);
    }

    private final float getDistancePulled() {
        FloatState $this$getValue$iv = this.distancePulled;
        return $this$getValue$iv.getFloatValue();
    }

    private final void setDistancePulled(float f) {
        MutableFloatState $this$setValue$iv = this.distancePulled;
        $this$setValue$iv.setFloatValue(f);
    }

    private final float getAdjustedDistancePulled() {
        return getDistancePulled() * 0.5f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getThresholdPx() {
        Density $this$_get_thresholdPx__u24lambda_u240 = DelegatableNodeKt.requireDensity(this);
        return $this$_get_thresholdPx__u24lambda_u240.mo400roundToPx0680j_4(this.threshold);
    }

    private final float getProgress() {
        return getAdjustedDistancePulled() / getThresholdPx();
    }

    /* JADX INFO: renamed from: androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$onAttach$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PullToRefresh.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$onAttach$1", f = "PullToRefresh.kt", i = {}, l = {260}, m = "invokeSuspend", n = {}, s = {})
    static final class C03391 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C03391(Continuation<? super C03391> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PullToRefreshModifierNode.this.new C03391(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03391) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PullToRefreshState state = PullToRefreshModifierNode.this.getState();
                    float f = PullToRefreshModifierNode.this.getIsRefreshing() ? 1.0f : 0.0f;
                    this.label = 1;
                    if (state.snapTo(f, this) == coroutine_suspended) {
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

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        delegate(this.nestedScrollNode);
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new C03391(null), 3, null);
        setVerticalOffset(this.isRefreshing ? getThresholdPx() : 0.0f);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    public long mo897onPreScrollOzD1aCk(long available, int source) {
        if (!this.state.isAnimating() && this.enabled) {
            if (NestedScrollSource.m6278equalsimpl0(source, NestedScrollSource.INSTANCE.m6290getUserInputWNlRxjI())) {
                int bits$iv$iv$iv = (int) (4294967295L & available);
                if (Float.intBitsToFloat(bits$iv$iv$iv) < 0.0f) {
                    return m3469consumeAvailableOffsetMKHz9U(available);
                }
            }
            return Offset.INSTANCE.m4868getZeroF1C5BW0();
        }
        return Offset.INSTANCE.m4868getZeroF1C5BW0();
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo574onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (!this.state.isAnimating() && this.enabled) {
            if (NestedScrollSource.m6278equalsimpl0(source, NestedScrollSource.INSTANCE.m6290getUserInputWNlRxjI())) {
                long jM3469consumeAvailableOffsetMKHz9U = m3469consumeAvailableOffsetMKHz9U(available);
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new PullToRefreshModifierNode$onPostScroll$1(this, null), 3, null);
                return jM3469consumeAvailableOffsetMKHz9U;
            }
            return Offset.INSTANCE.m4868getZeroF1C5BW0();
        }
        return Offset.INSTANCE.m4868getZeroF1C5BW0();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
    public Object mo896onPreFlingQWom1Mo(long available, Continuation<? super Velocity> continuation) {
        PullToRefreshModifierNode$onPreFling$1 pullToRefreshModifierNode$onPreFling$1;
        float f;
        Object objOnRelease;
        if (continuation instanceof PullToRefreshModifierNode$onPreFling$1) {
            pullToRefreshModifierNode$onPreFling$1 = (PullToRefreshModifierNode$onPreFling$1) continuation;
            if ((pullToRefreshModifierNode$onPreFling$1.label & Integer.MIN_VALUE) != 0) {
                pullToRefreshModifierNode$onPreFling$1.label -= Integer.MIN_VALUE;
            } else {
                pullToRefreshModifierNode$onPreFling$1 = new PullToRefreshModifierNode$onPreFling$1(this, continuation);
            }
        } else {
            pullToRefreshModifierNode$onPreFling$1 = new PullToRefreshModifierNode$onPreFling$1(this, continuation);
        }
        Object $result = pullToRefreshModifierNode$onPreFling$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (pullToRefreshModifierNode$onPreFling$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                float fM8144getYimpl = Velocity.m8144getYimpl(available);
                f = 0.0f;
                pullToRefreshModifierNode$onPreFling$1.F$0 = 0.0f;
                pullToRefreshModifierNode$onPreFling$1.label = 1;
                objOnRelease = onRelease(fM8144getYimpl, pullToRefreshModifierNode$onPreFling$1);
                if (objOnRelease == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                float f2 = pullToRefreshModifierNode$onPreFling$1.F$0;
                ResultKt.throwOnFailure($result);
                f = f2;
                objOnRelease = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Velocity.m8134boximpl(VelocityKt.Velocity(f, ((Number) objOnRelease).floatValue()));
    }

    /* JADX INFO: renamed from: androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$update$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PullToRefresh.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode$update$1", f = "PullToRefresh.kt", i = {}, l = {304, 306}, m = "invokeSuspend", n = {}, s = {})
    static final class C03411 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C03411(Continuation<? super C03411> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PullToRefreshModifierNode.this.new C03411(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03411) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0030, code lost:
        
            if (r4.this$0.animateToHidden(r4) == r0) goto L16;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0040, code lost:
        
            if (r4.this$0.animateToThreshold(r4) == r0) goto L16;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0042, code lost:
        
            return r0;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r5) {
            /*
                r4 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r4.label
                switch(r1) {
                    case 0: goto L19;
                    case 1: goto L15;
                    case 2: goto L11;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r0)
                throw r5
            L11:
                kotlin.ResultKt.throwOnFailure(r5)
                goto L43
            L15:
                kotlin.ResultKt.throwOnFailure(r5)
                goto L33
            L19:
                kotlin.ResultKt.throwOnFailure(r5)
                androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode r1 = androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode.this
                boolean r1 = r1.getIsRefreshing()
                if (r1 != 0) goto L34
                androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode r1 = androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode.this
                r2 = r4
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r3 = 1
                r4.label = r3
                java.lang.Object r1 = androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode.access$animateToHidden(r1, r2)
                if (r1 != r0) goto L33
                goto L42
            L33:
                goto L44
            L34:
                androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode r1 = androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode.this
                r2 = r4
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r3 = 2
                r4.label = r3
                java.lang.Object r1 = androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode.access$animateToThreshold(r1, r2)
                if (r1 != r0) goto L43
            L42:
                return r0
            L43:
            L44:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.pulltorefresh.PullToRefreshModifierNode.C03411.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void update() {
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new C03411(null), 3, null);
    }

    /* JADX INFO: renamed from: consumeAvailableOffset-MK-Hz9U, reason: not valid java name */
    private final long m3469consumeAvailableOffsetMKHz9U(long available) {
        float dragConsumed = 0.0f;
        if (!this.isRefreshing) {
            int bits$iv$iv$iv = (int) (available & 4294967295L);
            float newOffset = RangesKt.coerceAtLeast(getDistancePulled() + Float.intBitsToFloat(bits$iv$iv$iv), 0.0f);
            dragConsumed = newOffset - getDistancePulled();
            setDistancePulled(newOffset);
            setVerticalOffset(calculateVerticalOffset());
        }
        long v1$iv$iv = Float.floatToRawIntBits(0.0f);
        long v2$iv$iv = Float.floatToRawIntBits(dragConsumed);
        return Offset.m4844constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object onRelease(float velocity, Continuation<? super Float> continuation) {
        C03401 c03401;
        float consumed;
        PullToRefreshModifierNode pullToRefreshModifierNode;
        if (continuation instanceof C03401) {
            c03401 = (C03401) continuation;
            if ((c03401.label & Integer.MIN_VALUE) != 0) {
                c03401.label -= Integer.MIN_VALUE;
            } else {
                c03401 = new C03401(continuation);
            }
        } else {
            c03401 = new C03401(continuation);
        }
        Object $result = c03401.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c03401.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (this.isRefreshing) {
                    return Boxing.boxFloat(0.0f);
                }
                if (getAdjustedDistancePulled() > getThresholdPx()) {
                    this.onRefresh.invoke();
                }
                if ((getDistancePulled() == 0.0f) || velocity < 0.0f) {
                    velocity = 0.0f;
                }
                c03401.F$0 = velocity;
                c03401.label = 1;
                if (animateToHidden(c03401) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                consumed = velocity;
                pullToRefreshModifierNode = this;
                break;
            case 1:
                pullToRefreshModifierNode = this;
                consumed = c03401.F$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        pullToRefreshModifierNode.setDistancePulled(0.0f);
        return Boxing.boxFloat(consumed);
    }

    private final float calculateVerticalOffset() {
        if (getAdjustedDistancePulled() > getThresholdPx()) {
            float overshootPercent = Math.abs(getProgress()) - 1.0f;
            float linearTension = RangesKt.coerceIn(overshootPercent, 0.0f, 2.0f);
            float tensionPercent = linearTension - (((float) Math.pow(linearTension, 2.0d)) / 4.0f);
            float extraOffset = getThresholdPx() * tensionPercent;
            float overshootPercent2 = getThresholdPx() + extraOffset;
            return overshootPercent2;
        }
        float overshootPercent3 = getAdjustedDistancePulled();
        return overshootPercent3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:24:0x004b  */
    /* JADX WARN: Code duplicated, block: B:31:0x0069  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object animateToThreshold(Continuation<? super Unit> continuation) throws Throwable {
        C03381 c03381;
        Throwable th;
        PullToRefreshModifierNode pullToRefreshModifierNode;
        if (continuation instanceof C03381) {
            c03381 = (C03381) continuation;
            if ((c03381.label & Integer.MIN_VALUE) != 0) {
                c03381.label -= Integer.MIN_VALUE;
            } else {
                c03381 = new C03381(continuation);
            }
        } else {
            c03381 = new C03381(continuation);
        }
        Object $result = c03381.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c03381.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    PullToRefreshState pullToRefreshState = this.state;
                    c03381.label = 1;
                    if (pullToRefreshState.animateToThreshold(c03381) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pullToRefreshModifierNode = this;
                    if (pullToRefreshModifierNode.getIsAttached()) {
                        pullToRefreshModifierNode.setDistancePulled(pullToRefreshModifierNode.getThresholdPx());
                        pullToRefreshModifierNode.setVerticalOffset(pullToRefreshModifierNode.getThresholdPx());
                    }
                    return Unit.INSTANCE;
                } catch (Throwable th2) {
                    th = th2;
                    pullToRefreshModifierNode = this;
                    if (pullToRefreshModifierNode.getIsAttached()) {
                        pullToRefreshModifierNode.setDistancePulled(pullToRefreshModifierNode.getThresholdPx());
                        pullToRefreshModifierNode.setVerticalOffset(pullToRefreshModifierNode.getThresholdPx());
                    }
                    throw th;
                }
            case 1:
                pullToRefreshModifierNode = this;
                try {
                    ResultKt.throwOnFailure($result);
                    if (pullToRefreshModifierNode.getIsAttached()) {
                        pullToRefreshModifierNode.setDistancePulled(pullToRefreshModifierNode.getThresholdPx());
                        pullToRefreshModifierNode.setVerticalOffset(pullToRefreshModifierNode.getThresholdPx());
                    }
                    return Unit.INSTANCE;
                } catch (Throwable th3) {
                    th = th3;
                    if (pullToRefreshModifierNode.getIsAttached()) {
                        pullToRefreshModifierNode.setDistancePulled(pullToRefreshModifierNode.getThresholdPx());
                        pullToRefreshModifierNode.setVerticalOffset(pullToRefreshModifierNode.getThresholdPx());
                    }
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object animateToHidden(Continuation<? super Unit> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        Throwable th;
        PullToRefreshModifierNode pullToRefreshModifierNode;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object $result = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    PullToRefreshState pullToRefreshState = this.state;
                    anonymousClass1.label = 1;
                    if (pullToRefreshState.animateToHidden(anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pullToRefreshModifierNode = this;
                    pullToRefreshModifierNode.setDistancePulled(0.0f);
                    pullToRefreshModifierNode.setVerticalOffset(0.0f);
                    return Unit.INSTANCE;
                } catch (Throwable th2) {
                    th = th2;
                    pullToRefreshModifierNode = this;
                    pullToRefreshModifierNode.setDistancePulled(0.0f);
                    pullToRefreshModifierNode.setVerticalOffset(0.0f);
                    throw th;
                }
            case 1:
                pullToRefreshModifierNode = this;
                try {
                    ResultKt.throwOnFailure($result);
                    pullToRefreshModifierNode.setDistancePulled(0.0f);
                    pullToRefreshModifierNode.setVerticalOffset(0.0f);
                    return Unit.INSTANCE;
                } catch (Throwable th3) {
                    th = th3;
                    pullToRefreshModifierNode.setDistancePulled(0.0f);
                    pullToRefreshModifierNode.setVerticalOffset(0.0f);
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
