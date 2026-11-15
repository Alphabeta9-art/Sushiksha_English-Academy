package androidx.compose.material;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.State;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FloatingActionButton.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u0010\u001a\u00020\u0003*\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J0\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0086@¢\u0006\u0004\b\u0015\u0010\u0016J\u000e\u0010\u0017\u001a\u00020\u0014H\u0082@¢\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u000eH\u0086@¢\u0006\u0002\u0010\u001bJ\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u001dR\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\u0004\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Landroidx/compose/material/FloatingActionButtonElevationAnimatable;", "", "defaultElevation", "Landroidx/compose/ui/unit/Dp;", "pressedElevation", "hoveredElevation", "focusedElevation", "<init>", "(FFFFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "animatable", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "lastTargetInteraction", "Landroidx/compose/foundation/interaction/Interaction;", "targetInteraction", "calculateTarget", "calculateTarget-u2uoSUM", "(Landroidx/compose/foundation/interaction/Interaction;)F", "updateElevation", "", "updateElevation-lDy3nrA", "(FFFFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "snapElevation", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateElevation", TypedValues.TransitionType.S_TO, "(Landroidx/compose/foundation/interaction/Interaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "asState", "Landroidx/compose/runtime/State;", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class FloatingActionButtonElevationAnimatable {
    private final Animatable<Dp, AnimationVector1D> animatable;
    private float defaultElevation;
    private float focusedElevation;
    private float hoveredElevation;
    private Interaction lastTargetInteraction;
    private float pressedElevation;
    private Interaction targetInteraction;

    /* JADX INFO: renamed from: androidx.compose.material.FloatingActionButtonElevationAnimatable$animateElevation$1, reason: invalid class name */
    /* JADX INFO: compiled from: FloatingActionButton.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material.FloatingActionButtonElevationAnimatable", f = "FloatingActionButton.kt", i = {0}, l = {384}, m = "animateElevation", n = {TypedValues.TransitionType.S_TO}, s = {"L$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FloatingActionButtonElevationAnimatable.this.animateElevation(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material.FloatingActionButtonElevationAnimatable$snapElevation$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FloatingActionButton.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material.FloatingActionButtonElevationAnimatable", f = "FloatingActionButton.kt", i = {}, l = {370}, m = "snapElevation", n = {}, s = {}, v = 1)
    static final class C02891 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C02891(Continuation<? super C02891> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FloatingActionButtonElevationAnimatable.this.snapElevation(this);
        }
    }

    public /* synthetic */ FloatingActionButtonElevationAnimatable(float f, float f2, float f3, float f4, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4);
    }

    private FloatingActionButtonElevationAnimatable(float defaultElevation, float pressedElevation, float hoveredElevation, float focusedElevation) {
        this.defaultElevation = defaultElevation;
        this.pressedElevation = pressedElevation;
        this.hoveredElevation = hoveredElevation;
        this.focusedElevation = focusedElevation;
        this.animatable = new Animatable<>(Dp.m7903boximpl(this.defaultElevation), VectorConvertersKt.getVectorConverter(Dp.INSTANCE), null, null, 12, null);
    }

    /* JADX INFO: renamed from: calculateTarget-u2uoSUM, reason: not valid java name */
    private final float m1841calculateTargetu2uoSUM(Interaction $this$calculateTarget_u2du2uoSUM) {
        if ($this$calculateTarget_u2du2uoSUM instanceof PressInteraction.Press) {
            return this.pressedElevation;
        }
        if ($this$calculateTarget_u2du2uoSUM instanceof HoverInteraction.Enter) {
            return this.hoveredElevation;
        }
        return $this$calculateTarget_u2du2uoSUM instanceof FocusInteraction.Focus ? this.focusedElevation : this.defaultElevation;
    }

    /* JADX INFO: renamed from: updateElevation-lDy3nrA, reason: not valid java name */
    public final Object m1842updateElevationlDy3nrA(float f, float f2, float f3, float f4, Continuation<? super Unit> continuation) throws Throwable {
        this.defaultElevation = f;
        this.pressedElevation = f2;
        this.hoveredElevation = f3;
        this.focusedElevation = f4;
        Object objSnapElevation = snapElevation(continuation);
        return objSnapElevation == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSnapElevation : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object snapElevation(Continuation<? super Unit> continuation) throws Throwable {
        C02891 c02891;
        Throwable th;
        FloatingActionButtonElevationAnimatable floatingActionButtonElevationAnimatable;
        if (continuation instanceof C02891) {
            c02891 = (C02891) continuation;
            if ((c02891.label & Integer.MIN_VALUE) != 0) {
                c02891.label -= Integer.MIN_VALUE;
            } else {
                c02891 = new C02891(continuation);
            }
        } else {
            c02891 = new C02891(continuation);
        }
        Object $result = c02891.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02891.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                float target = m1841calculateTargetu2uoSUM(this.targetInteraction);
                if (!Dp.m7910equalsimpl0(this.animatable.getTargetValue().m7919unboximpl(), target)) {
                    try {
                        Animatable<Dp, AnimationVector1D> animatable = this.animatable;
                        Dp dpM7903boximpl = Dp.m7903boximpl(target);
                        c02891.label = 1;
                        if (animatable.snapTo(dpM7903boximpl, c02891) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        floatingActionButtonElevationAnimatable = this;
                        floatingActionButtonElevationAnimatable.lastTargetInteraction = floatingActionButtonElevationAnimatable.targetInteraction;
                    } catch (Throwable th2) {
                        th = th2;
                        floatingActionButtonElevationAnimatable = this;
                        floatingActionButtonElevationAnimatable.lastTargetInteraction = floatingActionButtonElevationAnimatable.targetInteraction;
                        throw th;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                floatingActionButtonElevationAnimatable = this;
                try {
                    ResultKt.throwOnFailure($result);
                    floatingActionButtonElevationAnimatable.lastTargetInteraction = floatingActionButtonElevationAnimatable.targetInteraction;
                    return Unit.INSTANCE;
                } catch (Throwable th3) {
                    th = th3;
                    floatingActionButtonElevationAnimatable.lastTargetInteraction = floatingActionButtonElevationAnimatable.targetInteraction;
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object animateElevation(Interaction to, Continuation<? super Unit> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        FloatingActionButtonElevationAnimatable floatingActionButtonElevationAnimatable;
        Interaction to2;
        FloatingActionButtonElevationAnimatable floatingActionButtonElevationAnimatable2;
        Throwable th;
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
                floatingActionButtonElevationAnimatable = this;
                float target = floatingActionButtonElevationAnimatable.m1841calculateTargetu2uoSUM(to);
                floatingActionButtonElevationAnimatable.targetInteraction = to;
                try {
                    if (!Dp.m7910equalsimpl0(floatingActionButtonElevationAnimatable.animatable.getTargetValue().m7919unboximpl(), target)) {
                        Animatable<Dp, AnimationVector1D> animatable = floatingActionButtonElevationAnimatable.animatable;
                        Interaction interaction = floatingActionButtonElevationAnimatable.lastTargetInteraction;
                        anonymousClass1.L$0 = to;
                        anonymousClass1.label = 1;
                        if (ElevationKt.m1816animateElevationrAjV9yQ(animatable, target, interaction, to, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        to2 = to;
                        floatingActionButtonElevationAnimatable2 = floatingActionButtonElevationAnimatable;
                        floatingActionButtonElevationAnimatable = floatingActionButtonElevationAnimatable2;
                        to = to2;
                    }
                    floatingActionButtonElevationAnimatable.lastTargetInteraction = to;
                    return Unit.INSTANCE;
                } catch (Throwable th2) {
                    to2 = to;
                    floatingActionButtonElevationAnimatable2 = floatingActionButtonElevationAnimatable;
                    th = th2;
                    floatingActionButtonElevationAnimatable2.lastTargetInteraction = to2;
                    throw th;
                }
            case 1:
                floatingActionButtonElevationAnimatable2 = this;
                to2 = (Interaction) anonymousClass1.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    floatingActionButtonElevationAnimatable = floatingActionButtonElevationAnimatable2;
                    to = to2;
                    floatingActionButtonElevationAnimatable.lastTargetInteraction = to;
                    return Unit.INSTANCE;
                } catch (Throwable th3) {
                    th = th3;
                    floatingActionButtonElevationAnimatable2.lastTargetInteraction = to2;
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final State<Dp> asState() {
        return this.animatable.asState();
    }
}
