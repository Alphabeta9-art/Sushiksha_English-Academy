package androidx.compose.material3;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.Velocity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.ts.TsExtractor;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SearchBar.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u0000 02\u00020\u0001:\u00010BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f¢\u0006\u0004\b\r\u0010\u000eJ\f\u0010%\u001a\u00020&*\u00020&H\u0016J\u0018\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0003H\u0082@¢\u0006\u0004\b.\u0010/R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00038V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR+\u0010 \u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00038V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b!\u0010\u001c\"\u0004\b\"\u0010\u001eR\u0014\u0010'\u001a\u00020(X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*¨\u00061"}, d2 = {"Landroidx/compose/material3/EnterAlwaysSearchBarScrollBehavior;", "Landroidx/compose/material3/SearchBarScrollBehavior;", "initialOffset", "", "initialOffsetLimit", "canScroll", "Lkotlin/Function0;", "", "reverseLayout", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "flingAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "<init>", "(FFLkotlin/jvm/functions/Function0;ZLandroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;)V", "getCanScroll", "()Lkotlin/jvm/functions/Function0;", "getReverseLayout", "()Z", "getSnapAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "getFlingAnimationSpec", "()Landroidx/compose/animation/core/DecayAnimationSpec;", "_offset", "Landroidx/compose/runtime/MutableFloatState;", "newOffset", "scrollOffset", "getScrollOffset", "()F", "setScrollOffset", "(F)V", "<set-?>", "scrollOffsetLimit", "getScrollOffsetLimit", "setScrollOffsetLimit", "scrollOffsetLimit$delegate", "Landroidx/compose/runtime/MutableFloatState;", "searchBarScrollBehavior", "Landroidx/compose/ui/Modifier;", "nestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "getNestedScrollConnection", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "settleSearchBar", "Landroidx/compose/ui/unit/Velocity;", "velocity", "settleSearchBar-OhffZ5M", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class EnterAlwaysSearchBarScrollBehavior implements SearchBarScrollBehavior {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private MutableFloatState _offset;
    private final Function0<Boolean> canScroll;
    private final DecayAnimationSpec<Float> flingAnimationSpec;
    private final NestedScrollConnection nestedScrollConnection = new NestedScrollConnection() { // from class: androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior$nestedScrollConnection$1
        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
        public long mo897onPreScrollOzD1aCk(long available, int source) {
            if (!this.this$0.getCanScroll().invoke().booleanValue()) {
                return Offset.INSTANCE.m4868getZeroF1C5BW0();
            }
            float prevScrollOffset = this.this$0.getScrollOffset();
            int bits$iv$iv$iv = (int) (4294967295L & available);
            this.this$0.setScrollOffset(this.this$0.getScrollOffset() + Float.intBitsToFloat(bits$iv$iv$iv));
            if (!this.this$0.getReverseLayout()) {
                if (!(prevScrollOffset == this.this$0.getScrollOffset())) {
                    return Offset.m4846copydBAh8RU$default(available, 0.0f, 0.0f, 2, null);
                }
            }
            return Offset.INSTANCE.m4868getZeroF1C5BW0();
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
        public long mo574onPostScrollDzOQY0M(long consumed, long available, int source) {
            if (!this.this$0.getCanScroll().invoke().booleanValue()) {
                return Offset.INSTANCE.m4868getZeroF1C5BW0();
            }
            if (this.this$0.getReverseLayout()) {
                int bits$iv$iv$iv = (int) (available & 4294967295L);
                if (Float.intBitsToFloat(bits$iv$iv$iv) > 0.0f) {
                    int bits$iv$iv$iv2 = (int) (4294967295L & available);
                    this.this$0.setScrollOffset(this.this$0.getScrollOffset() + Float.intBitsToFloat(bits$iv$iv$iv2));
                    return Offset.m4846copydBAh8RU$default(available, 0.0f, 0.0f, 2, null);
                }
            }
            if (!this.this$0.getReverseLayout()) {
                int bits$iv$iv$iv3 = (int) (4294967295L & consumed);
                this.this$0.setScrollOffset(this.this$0.getScrollOffset() + Float.intBitsToFloat(bits$iv$iv$iv3));
            }
            return Offset.INSTANCE.m4868getZeroF1C5BW0();
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
        public Object mo573onPostFlingRZ2iAVY(long consumed, long available, Continuation<? super Velocity> continuation) {
            return !this.this$0.getCanScroll().invoke().booleanValue() ? Velocity.m8134boximpl(Velocity.INSTANCE.m8154getZero9UxMQ8M()) : this.this$0.m2413settleSearchBarOhffZ5M(Velocity.m8144getYimpl(available), continuation);
        }
    };
    private final boolean reverseLayout;

    /* JADX INFO: renamed from: scrollOffsetLimit$delegate, reason: from kotlin metadata */
    private final MutableFloatState scrollOffsetLimit;
    private final AnimationSpec<Float> snapAnimationSpec;

    public EnterAlwaysSearchBarScrollBehavior(float initialOffset, float initialOffsetLimit, Function0<Boolean> function0, boolean reverseLayout, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec) {
        this.canScroll = function0;
        this.reverseLayout = reverseLayout;
        this.snapAnimationSpec = animationSpec;
        this.flingAnimationSpec = decayAnimationSpec;
        this._offset = PrimitiveSnapshotStateKt.mutableFloatStateOf(initialOffset);
        this.scrollOffsetLimit = PrimitiveSnapshotStateKt.mutableFloatStateOf(initialOffsetLimit);
    }

    public final Function0<Boolean> getCanScroll() {
        return this.canScroll;
    }

    public final boolean getReverseLayout() {
        return this.reverseLayout;
    }

    public final AnimationSpec<Float> getSnapAnimationSpec() {
        return this.snapAnimationSpec;
    }

    public final DecayAnimationSpec<Float> getFlingAnimationSpec() {
        return this.flingAnimationSpec;
    }

    @Override // androidx.compose.material3.SearchBarScrollBehavior
    public float getScrollOffset() {
        return this._offset.getFloatValue();
    }

    @Override // androidx.compose.material3.SearchBarScrollBehavior
    public void setScrollOffset(float newOffset) {
        this._offset.setFloatValue(RangesKt.coerceIn(newOffset, getScrollOffsetLimit(), 0.0f));
    }

    @Override // androidx.compose.material3.SearchBarScrollBehavior
    public float getScrollOffsetLimit() {
        FloatState $this$getValue$iv = this.scrollOffsetLimit;
        return $this$getValue$iv.getFloatValue();
    }

    @Override // androidx.compose.material3.SearchBarScrollBehavior
    public void setScrollOffsetLimit(float f) {
        MutableFloatState $this$setValue$iv = this.scrollOffsetLimit;
        $this$setValue$iv.setFloatValue(f);
    }

    @Override // androidx.compose.material3.SearchBarScrollBehavior
    public Modifier searchBarScrollBehavior(Modifier $this$searchBarScrollBehavior) {
        return OnRemeasuredModifierKt.onSizeChanged(LayoutModifierKt.layout(ClipKt.clipToBounds(DraggableKt.draggable($this$searchBarScrollBehavior, DraggableKt.DraggableState(new Function1() { // from class: androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EnterAlwaysSearchBarScrollBehavior.searchBarScrollBehavior$lambda$0(this.f$0, ((Float) obj).floatValue());
            }
        }), Orientation.Vertical, (TsExtractor.TS_PACKET_SIZE & 4) != 0 ? true : this.canScroll.invoke().booleanValue(), (TsExtractor.TS_PACKET_SIZE & 8) != 0 ? null : null, (TsExtractor.TS_PACKET_SIZE & 16) != 0 ? false : false, (TsExtractor.TS_PACKET_SIZE & 32) != 0 ? DraggableKt.NoOpOnDragStarted : null, (TsExtractor.TS_PACKET_SIZE & 64) != 0 ? DraggableKt.NoOpOnDragStopped : new AnonymousClass2(null), (TsExtractor.TS_PACKET_SIZE & 128) != 0 ? false : false)), new Function3() { // from class: androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return EnterAlwaysSearchBarScrollBehavior.searchBarScrollBehavior$lambda$2(this.f$0, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
            }
        }), new Function1() { // from class: androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EnterAlwaysSearchBarScrollBehavior.searchBarScrollBehavior$lambda$3(this.f$0, (IntSize) obj);
            }
        });
    }

    /* JADX INFO: renamed from: androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior$searchBarScrollBehavior$2, reason: invalid class name */
    /* JADX INFO: compiled from: SearchBar.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "velocity", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior$searchBarScrollBehavior$2", f = "SearchBar.kt", i = {}, l = {TypedValues.Custom.TYPE_BOOLEAN}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function3<CoroutineScope, Float, Continuation<? super Unit>, Object> {
        /* synthetic */ float F$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Float f, Continuation<? super Unit> continuation) {
            return invoke(coroutineScope, f.floatValue(), continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, float f, Continuation<? super Unit> continuation) {
            AnonymousClass2 anonymousClass2 = EnterAlwaysSearchBarScrollBehavior.this.new AnonymousClass2(continuation);
            anonymousClass2.F$0 = f;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    float velocity = this.F$0;
                    this.label = 1;
                    if (EnterAlwaysSearchBarScrollBehavior.this.m2413settleSearchBarOhffZ5M(velocity, this) == coroutine_suspended) {
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

    static final Unit searchBarScrollBehavior$lambda$0(EnterAlwaysSearchBarScrollBehavior this$0, float delta) {
        this$0.setScrollOffset(this$0.getScrollOffset() + delta);
        return Unit.INSTANCE;
    }

    static final MeasureResult searchBarScrollBehavior$lambda$2(EnterAlwaysSearchBarScrollBehavior this$0, MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        final Placeable placeable = measurable.mo6541measureBRTryo0(constraints.getValue());
        final int scrollOffset = MathKt.roundToInt(this$0.getScrollOffset());
        int scrolledHeight = RangesKt.coerceAtLeast(placeable.getHeight() + scrollOffset, 0);
        return MeasureScope.layout$default($this$layout, placeable.getWidth(), scrolledHeight, null, new Function1() { // from class: androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EnterAlwaysSearchBarScrollBehavior.searchBarScrollBehavior$lambda$2$lambda$1(placeable, scrollOffset, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit searchBarScrollBehavior$lambda$2$lambda$1(Placeable $placeable, int $scrollOffset, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeWithLayer$default($this$layout, $placeable, 0, $scrollOffset, 0.0f, (Function1) null, 12, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit searchBarScrollBehavior$lambda$3(EnterAlwaysSearchBarScrollBehavior this$0, IntSize size) {
        long arg0$iv = size.m8080unboximpl();
        this$0.setScrollOffsetLimit(-((int) (4294967295L & arg0$iv)));
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.material3.SearchBarScrollBehavior
    public NestedScrollConnection getNestedScrollConnection() {
        return this.nestedScrollConnection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:42:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:43:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:46:0x0122 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:47:0x0123  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX INFO: renamed from: settleSearchBar-OhffZ5M, reason: not valid java name */
    public final Object m2413settleSearchBarOhffZ5M(float velocity, Continuation<? super Velocity> continuation) {
        EnterAlwaysSearchBarScrollBehavior$settleSearchBar$1 enterAlwaysSearchBarScrollBehavior$settleSearchBar$1;
        Ref.FloatRef remainingVelocity;
        final EnterAlwaysSearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior;
        float scrollFraction;
        EnterAlwaysSearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior2;
        float scrollFraction2;
        Ref.FloatRef remainingVelocity2;
        AnimationState animationStateAnimationState$default;
        float scrollFraction3;
        Float fBoxFloat;
        AnimationSpec<Float> animationSpec;
        Function1 function1;
        Ref.FloatRef remainingVelocity3;
        if (continuation instanceof EnterAlwaysSearchBarScrollBehavior$settleSearchBar$1) {
            enterAlwaysSearchBarScrollBehavior$settleSearchBar$1 = (EnterAlwaysSearchBarScrollBehavior$settleSearchBar$1) continuation;
            if ((enterAlwaysSearchBarScrollBehavior$settleSearchBar$1.label & Integer.MIN_VALUE) != 0) {
                enterAlwaysSearchBarScrollBehavior$settleSearchBar$1.label -= Integer.MIN_VALUE;
            } else {
                enterAlwaysSearchBarScrollBehavior$settleSearchBar$1 = new EnterAlwaysSearchBarScrollBehavior$settleSearchBar$1(this, continuation);
            }
        } else {
            enterAlwaysSearchBarScrollBehavior$settleSearchBar$1 = new EnterAlwaysSearchBarScrollBehavior$settleSearchBar$1(this, continuation);
        }
        EnterAlwaysSearchBarScrollBehavior$settleSearchBar$1 enterAlwaysSearchBarScrollBehavior$settleSearchBar$2 = enterAlwaysSearchBarScrollBehavior$settleSearchBar$1;
        Object $result = enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                float scrollFraction4 = !((getScrollOffsetLimit() > 0.0f ? 1 : (getScrollOffsetLimit() == 0.0f ? 0 : -1)) == 0) ? getScrollOffset() / getScrollOffsetLimit() : 0.0f;
                if (scrollFraction4 >= 0.01f) {
                    if (!(scrollFraction4 == 1.0f)) {
                        final Ref.FloatRef remainingVelocity4 = new Ref.FloatRef();
                        remainingVelocity4.element = velocity;
                        if (Math.abs(velocity) > 1.0f) {
                            final Ref.FloatRef lastValue = new Ref.FloatRef();
                            AnimationState animationStateAnimationState$default2 = AnimationStateKt.AnimationState$default(0.0f, velocity, 0L, 0L, false, 28, null);
                            DecayAnimationSpec<Float> decayAnimationSpec = this.flingAnimationSpec;
                            Function1 function2 = new Function1() { // from class: androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return EnterAlwaysSearchBarScrollBehavior.settleSearchBar_OhffZ5M$lambda$4(lastValue, this, remainingVelocity4, (AnimationScope) obj);
                                }
                            };
                            enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.L$0 = remainingVelocity4;
                            enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.F$0 = scrollFraction4;
                            enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.label = 1;
                            float scrollFraction5 = scrollFraction4;
                            if (SuspendAnimationKt.animateDecay$default(animationStateAnimationState$default2, decayAnimationSpec, false, function2, enterAlwaysSearchBarScrollBehavior$settleSearchBar$2, 2, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            enterAlwaysSearchBarScrollBehavior2 = this;
                            scrollFraction2 = scrollFraction5;
                            remainingVelocity2 = remainingVelocity4;
                            enterAlwaysSearchBarScrollBehavior = enterAlwaysSearchBarScrollBehavior2;
                            scrollFraction = scrollFraction2;
                            remainingVelocity = remainingVelocity2;
                        } else {
                            remainingVelocity = remainingVelocity4;
                            enterAlwaysSearchBarScrollBehavior = this;
                            scrollFraction = scrollFraction4;
                        }
                        if (enterAlwaysSearchBarScrollBehavior.getScrollOffsetLimit() < enterAlwaysSearchBarScrollBehavior.getScrollOffset() && enterAlwaysSearchBarScrollBehavior.getScrollOffset() < 0.0f) {
                            animationStateAnimationState$default = AnimationStateKt.AnimationState$default(enterAlwaysSearchBarScrollBehavior.getScrollOffset(), 0.0f, 0L, 0L, false, 30, null);
                            if (scrollFraction < 0.5f) {
                                scrollFraction3 = 0.0f;
                            } else {
                                scrollFraction3 = enterAlwaysSearchBarScrollBehavior.getScrollOffsetLimit();
                            }
                            fBoxFloat = Boxing.boxFloat(scrollFraction3);
                            animationSpec = enterAlwaysSearchBarScrollBehavior.snapAnimationSpec;
                            function1 = new Function1() { // from class: androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return EnterAlwaysSearchBarScrollBehavior.settleSearchBar_OhffZ5M$lambda$5(this.f$0, (AnimationScope) obj);
                                }
                            };
                            enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.L$0 = remainingVelocity;
                            enterAlwaysSearchBarScrollBehavior$settleSearchBar$2.label = 2;
                            if (SuspendAnimationKt.animateTo(animationStateAnimationState$default, fBoxFloat, (4 & 2) != 0 ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : animationSpec, (4 & 4) != 0 ? false : false, (4 & 8) != 0 ? 
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda9, state: NOT_LOADED
                                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                	at jadx.core.codegen.InsnGen.makeTernary(InsnGen.java:1187)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:536)
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
                                Method dump skipped, instruction units count: 330
                                To view this dump change 'Code comments level' option to 'DEBUG'
                            */
                            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior.m2413settleSearchBarOhffZ5M(float, kotlin.coroutines.Continuation):java.lang.Object");
                        }

                        static final Unit settleSearchBar_OhffZ5M$lambda$4(Ref.FloatRef $lastValue, EnterAlwaysSearchBarScrollBehavior this$0, Ref.FloatRef $remainingVelocity, AnimationScope $this$animateDecay) {
                            float delta = ((Number) $this$animateDecay.getValue()).floatValue() - $lastValue.element;
                            float initialScrollOffset = this$0.getScrollOffset();
                            this$0.setScrollOffset(initialScrollOffset + delta);
                            float consumed = Math.abs(initialScrollOffset - this$0.getScrollOffset());
                            $lastValue.element = ((Number) $this$animateDecay.getValue()).floatValue();
                            $remainingVelocity.element = ((Number) $this$animateDecay.getVelocity()).floatValue();
                            if (Math.abs(delta - consumed) > 0.5f) {
                                $this$animateDecay.cancelAnimation();
                            }
                            return Unit.INSTANCE;
                        }

                        static final Unit settleSearchBar_OhffZ5M$lambda$5(EnterAlwaysSearchBarScrollBehavior this$0, AnimationScope $this$animateTo) {
                            this$0.setScrollOffset(((Number) $this$animateTo.getValue()).floatValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: compiled from: SearchBar.kt */
                        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J:\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u00030\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e¨\u0006\u000f"}, d2 = {"Landroidx/compose/material3/EnterAlwaysSearchBarScrollBehavior$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/EnterAlwaysSearchBarScrollBehavior;", "canScroll", "Lkotlin/Function0;", "", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "flingAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
                        public static final class Companion {
                            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                                this();
                            }

                            private Companion() {
                            }

                            public final Saver<EnterAlwaysSearchBarScrollBehavior, ?> Saver(final Function0<Boolean> canScroll, final AnimationSpec<Float> snapAnimationSpec, final DecayAnimationSpec<Float> flingAnimationSpec) {
                                return ListSaverKt.listSaver(new Function2() { // from class: androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior$Companion$$ExternalSyntheticLambda0
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        EnterAlwaysSearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior = (EnterAlwaysSearchBarScrollBehavior) obj2;
                                        return CollectionsKt.listOf(Float.valueOf(enterAlwaysSearchBarScrollBehavior.getScrollOffset()), Float.valueOf(enterAlwaysSearchBarScrollBehavior.getScrollOffsetLimit()), Boolean.valueOf(enterAlwaysSearchBarScrollBehavior.getReverseLayout()));
                                    }
                                }, new Function1() { // from class: androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior$Companion$$ExternalSyntheticLambda1
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return EnterAlwaysSearchBarScrollBehavior.Companion.Saver$lambda$1(canScroll, snapAnimationSpec, flingAnimationSpec, (List) obj);
                                    }
                                });
                            }

                            static final EnterAlwaysSearchBarScrollBehavior Saver$lambda$1(Function0 $canScroll, AnimationSpec $snapAnimationSpec, DecayAnimationSpec $flingAnimationSpec, List it) {
                                Object obj = it.get(0);
                                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Float");
                                float fFloatValue = ((Float) obj).floatValue();
                                Object obj2 = it.get(1);
                                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Float");
                                float fFloatValue2 = ((Float) obj2).floatValue();
                                Object obj3 = it.get(2);
                                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Boolean");
                                return new EnterAlwaysSearchBarScrollBehavior(fFloatValue, fFloatValue2, $canScroll, ((Boolean) obj3).booleanValue(), $snapAnimationSpec, $flingAnimationSpec);
                            }
                        }
                    }
