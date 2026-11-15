package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: ScrollExtensions.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0086@¢\u0006\u0002\u0010\u0006\u001a,\u0010\u0000\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00072\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005H\u0086@¢\u0006\u0004\b\t\u0010\n\u001a\u001a\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@¢\u0006\u0002\u0010\f\u001a\u001c\u0010\u000b\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0007H\u0086@¢\u0006\u0004\b\r\u0010\u000e\u001a\u001c\u0010\u000f\u001a\u00020\u0010*\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010\u0013\u001a\u001c\u0010\u000f\u001a\u00020\u0010*\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"animateScrollBy", "", "Landroidx/compose/foundation/gestures/ScrollableState;", "value", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(Landroidx/compose/foundation/gestures/ScrollableState;FLandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/foundation/gestures/Scrollable2DState;", "animateScrollBy-ubNVwUQ", "(Landroidx/compose/foundation/gestures/Scrollable2DState;JLandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scrollBy", "(Landroidx/compose/foundation/gestures/ScrollableState;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scrollBy-d-4ec7I", "(Landroidx/compose/foundation/gestures/Scrollable2DState;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopScroll", "", "scrollPriority", "Landroidx/compose/foundation/MutatePriority;", "(Landroidx/compose/foundation/gestures/ScrollableState;Landroidx/compose/foundation/MutatePriority;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroidx/compose/foundation/gestures/Scrollable2DState;Landroidx/compose/foundation/MutatePriority;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ScrollExtensionsKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$1, reason: invalid class name */
    /* JADX INFO: compiled from: ScrollExtensions.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt", f = "ScrollExtensions.kt", i = {0}, l = {40}, m = "animateScrollBy", n = {"previousValue"}, s = {"L$0"}, v = 1)
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
            return ScrollExtensionsKt.animateScrollBy(null, 0.0f, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$3, reason: invalid class name */
    /* JADX INFO: compiled from: ScrollExtensions.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt", f = "ScrollExtensions.kt", i = {0}, l = {62}, m = "animateScrollBy-ubNVwUQ", n = {"previousValue"}, s = {"L$0"}, v = 1)
    static final class AnonymousClass3 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ScrollExtensionsKt.m559animateScrollByubNVwUQ(null, 0L, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ScrollExtensions.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt", f = "ScrollExtensions.kt", i = {0}, l = {83}, m = "scrollBy", n = {"consumed"}, s = {"L$0"}, v = 1)
    static final class C02051 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C02051(Continuation<? super C02051> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ScrollExtensionsKt.scrollBy(null, 0.0f, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ScrollExtensions.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt", f = "ScrollExtensions.kt", i = {0}, l = {98}, m = "scrollBy-d-4ec7I", n = {"consumed"}, s = {"L$0"}, v = 1)
    static final class C02073 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C02073(Continuation<? super C02073> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ScrollExtensionsKt.m561scrollByd4ec7I(null, 0L, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object animateScrollBy(ScrollableState $this$animateScrollBy, float value, AnimationSpec<Float> animationSpec, Continuation<? super Float> continuation) {
        AnonymousClass1 anonymousClass1;
        Ref.FloatRef previousValue;
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
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object $result = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                previousValue = new Ref.FloatRef();
                AnonymousClass2 anonymousClass3 = new AnonymousClass2(value, animationSpec, previousValue, null);
                anonymousClass2.L$0 = previousValue;
                anonymousClass2.label = 1;
                if (ScrollableState.scroll$default($this$animateScrollBy, null, anonymousClass3, anonymousClass2, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                previousValue = (Ref.FloatRef) anonymousClass2.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Boxing.boxFloat(previousValue.element);
    }

    public static /* synthetic */ Object animateScrollBy$default(ScrollableState scrollableState, float f, AnimationSpec animationSpec, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return animateScrollBy(scrollableState, f, animationSpec, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$2, reason: invalid class name */
    /* JADX INFO: compiled from: ScrollExtensions.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$2", f = "ScrollExtensions.kt", i = {}, l = {41}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AnimationSpec<Float> $animationSpec;
        final /* synthetic */ Ref.FloatRef $previousValue;
        final /* synthetic */ float $value;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(float f, AnimationSpec<Float> animationSpec, Ref.FloatRef floatRef, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$value = f;
            this.$animationSpec = animationSpec;
            this.$previousValue = floatRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$value, this.$animationSpec, this.$previousValue, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    final ScrollScope $this$scroll = (ScrollScope) this.L$0;
                    float f = this.$value;
                    AnimationSpec<Float> animationSpec = this.$animationSpec;
                    final Ref.FloatRef floatRef = this.$previousValue;
                    this.label = 1;
                    if (SuspendAnimationKt.animate$default(0.0f, f, 0.0f, animationSpec, new Function2() { // from class: androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$2$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return ScrollExtensionsKt.AnonymousClass2.invokeSuspend$lambda$0(floatRef, $this$scroll, ((Float) obj).floatValue(), ((Float) obj2).floatValue());
                        }
                    }, this, 4, null) == coroutine_suspended) {
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

        static final Unit invokeSuspend$lambda$0(Ref.FloatRef $previousValue, ScrollScope $$this$scroll, float currentValue, float f) {
            $previousValue.element += $$this$scroll.scrollBy(currentValue - $previousValue.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX INFO: renamed from: animateScrollBy-ubNVwUQ, reason: not valid java name */
    public static final Object m559animateScrollByubNVwUQ(Scrollable2DState $this$animateScrollBy_u2dubNVwUQ, long j, AnimationSpec<Offset> animationSpec, Continuation<? super Offset> continuation) {
        AnonymousClass3 anonymousClass3;
        Ref.LongRef previousValue;
        if (continuation instanceof AnonymousClass3) {
            anonymousClass3 = (AnonymousClass3) continuation;
            if ((anonymousClass3.label & Integer.MIN_VALUE) != 0) {
                anonymousClass3.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass3 = new AnonymousClass3(continuation);
            }
        } else {
            anonymousClass3 = new AnonymousClass3(continuation);
        }
        AnonymousClass3 anonymousClass4 = anonymousClass3;
        Object $result = anonymousClass4.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass4.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Ref.LongRef previousValue2 = new Ref.LongRef();
                previousValue2.element = Offset.INSTANCE.m4868getZeroF1C5BW0();
                AnonymousClass4 anonymousClass5 = new AnonymousClass4(j, animationSpec, previousValue2, null);
                anonymousClass4.L$0 = previousValue2;
                anonymousClass4.label = 1;
                if (Scrollable2DState.scroll$default($this$animateScrollBy_u2dubNVwUQ, null, anonymousClass5, anonymousClass4, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                previousValue = previousValue2;
                break;
                break;
            case 1:
                previousValue = (Ref.LongRef) anonymousClass4.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Offset.m4841boximpl(previousValue.element);
    }

    /* JADX INFO: renamed from: animateScrollBy-ubNVwUQ$default, reason: not valid java name */
    public static /* synthetic */ Object m560animateScrollByubNVwUQ$default(Scrollable2DState scrollable2DState, long j, AnimationSpec animationSpec, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return m559animateScrollByubNVwUQ(scrollable2DState, j, animationSpec, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$4, reason: invalid class name */
    /* JADX INFO: compiled from: ScrollExtensions.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/Scroll2DScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$4", f = "ScrollExtensions.kt", i = {}, l = {63}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<Scroll2DScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-geometry-Offset$-value$0, reason: not valid java name */
        final /* synthetic */ long f11$$v$c$androidxcomposeuigeometryOffset$value$0;
        final /* synthetic */ AnimationSpec<Offset> $animationSpec;
        final /* synthetic */ Ref.LongRef $previousValue;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(long j, AnimationSpec<Offset> animationSpec, Ref.LongRef longRef, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.f11$$v$c$androidxcomposeuigeometryOffset$value$0 = j;
            this.$animationSpec = animationSpec;
            this.$previousValue = longRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.f11$$v$c$androidxcomposeuigeometryOffset$value$0, this.$animationSpec, this.$previousValue, continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Scroll2DScope scroll2DScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(scroll2DScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    final Scroll2DScope $this$scroll = (Scroll2DScope) this.L$0;
                    TwoWayConverter<Offset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(Offset.INSTANCE);
                    Offset offsetM4841boximpl = Offset.m4841boximpl(Offset.INSTANCE.m4868getZeroF1C5BW0());
                    Offset offsetM4841boximpl2 = Offset.m4841boximpl(this.f11$$v$c$androidxcomposeuigeometryOffset$value$0);
                    AnimationSpec<Offset> animationSpec = this.$animationSpec;
                    final Ref.LongRef longRef = this.$previousValue;
                    this.label = 1;
                    if (SuspendAnimationKt.animate$default(vectorConverter, offsetM4841boximpl, offsetM4841boximpl2, null, animationSpec, new Function2() { // from class: androidx.compose.foundation.gestures.ScrollExtensionsKt$animateScrollBy$4$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return ScrollExtensionsKt.AnonymousClass4.invokeSuspend$lambda$0(longRef, $this$scroll, (Offset) obj, (Offset) obj2);
                        }
                    }, this, 8, null) == coroutine_suspended) {
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

        static final Unit invokeSuspend$lambda$0(Ref.LongRef $previousValue, Scroll2DScope $$this$scroll, Offset currentValue, Offset offset) {
            $previousValue.element = Offset.m4857plusMKHz9U($previousValue.element, $$this$scroll.mo447scrollByMKHz9U(Offset.m4856minusMKHz9U(currentValue.m4862unboximpl(), $previousValue.element)));
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object scrollBy(ScrollableState $this$scrollBy, float value, Continuation<? super Float> continuation) {
        C02051 c02051;
        Ref.FloatRef consumed;
        if (continuation instanceof C02051) {
            c02051 = (C02051) continuation;
            if ((c02051.label & Integer.MIN_VALUE) != 0) {
                c02051.label -= Integer.MIN_VALUE;
            } else {
                c02051 = new C02051(continuation);
            }
        } else {
            c02051 = new C02051(continuation);
        }
        C02051 c02052 = c02051;
        Object $result = c02052.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02052.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                consumed = new Ref.FloatRef();
                C02062 c02062 = new C02062(consumed, value, null);
                c02052.L$0 = consumed;
                c02052.label = 1;
                if (ScrollableState.scroll$default($this$scrollBy, null, c02062, c02052, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                consumed = (Ref.FloatRef) c02052.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Boxing.boxFloat(consumed.element);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ScrollExtensions.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$2", f = "ScrollExtensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C02062 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.FloatRef $consumed;
        final /* synthetic */ float $value;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02062(Ref.FloatRef floatRef, float f, Continuation<? super C02062> continuation) {
            super(2, continuation);
            this.$consumed = floatRef;
            this.$value = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02062 c02062 = new C02062(this.$consumed, this.$value, continuation);
            c02062.L$0 = obj;
            return c02062;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
            return ((C02062) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    ScrollScope $this$scroll = (ScrollScope) this.L$0;
                    this.$consumed.element = $this$scroll.scrollBy(this.$value);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX INFO: renamed from: scrollBy-d-4ec7I, reason: not valid java name */
    public static final Object m561scrollByd4ec7I(Scrollable2DState $this$scrollBy_u2dd_u2d4ec7I, long j, Continuation<? super Offset> continuation) {
        C02073 c02073;
        Ref.LongRef consumed;
        if (continuation instanceof C02073) {
            c02073 = (C02073) continuation;
            if ((c02073.label & Integer.MIN_VALUE) != 0) {
                c02073.label -= Integer.MIN_VALUE;
            } else {
                c02073 = new C02073(continuation);
            }
        } else {
            c02073 = new C02073(continuation);
        }
        C02073 c02074 = c02073;
        Object $result = c02074.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02074.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                consumed = new Ref.LongRef();
                consumed.element = Offset.INSTANCE.m4868getZeroF1C5BW0();
                C02084 c02084 = new C02084(consumed, j, null);
                c02074.L$0 = consumed;
                c02074.label = 1;
                if (Scrollable2DState.scroll$default($this$scrollBy_u2dd_u2d4ec7I, null, c02084, c02074, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                consumed = (Ref.LongRef) c02074.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Offset.m4841boximpl(consumed.element);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ScrollExtensions.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/Scroll2DScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt$scrollBy$4", f = "ScrollExtensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C02084 extends SuspendLambda implements Function2<Scroll2DScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-geometry-Offset$-value$0, reason: not valid java name */
        final /* synthetic */ long f12$$v$c$androidxcomposeuigeometryOffset$value$0;
        final /* synthetic */ Ref.LongRef $consumed;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02084(Ref.LongRef longRef, long j, Continuation<? super C02084> continuation) {
            super(2, continuation);
            this.$consumed = longRef;
            this.f12$$v$c$androidxcomposeuigeometryOffset$value$0 = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02084 c02084 = new C02084(this.$consumed, this.f12$$v$c$androidxcomposeuigeometryOffset$value$0, continuation);
            c02084.L$0 = obj;
            return c02084;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Scroll2DScope scroll2DScope, Continuation<? super Unit> continuation) {
            return ((C02084) create(scroll2DScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    Scroll2DScope $this$scroll = (Scroll2DScope) this.L$0;
                    this.$consumed.element = $this$scroll.mo447scrollByMKHz9U(this.f12$$v$c$androidxcomposeuigeometryOffset$value$0);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$stopScroll$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ScrollExtensions.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt$stopScroll$2", f = "ScrollExtensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C02092 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
        int label;

        C02092(Continuation<? super C02092> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C02092(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
            return ((C02092) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public static /* synthetic */ Object stopScroll$default(ScrollableState scrollableState, MutatePriority mutatePriority, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return stopScroll(scrollableState, mutatePriority, (Continuation<? super Unit>) continuation);
    }

    public static final Object stopScroll(ScrollableState $this$stopScroll, MutatePriority scrollPriority, Continuation<? super Unit> continuation) {
        Object objScroll = $this$stopScroll.scroll(scrollPriority, new C02092(null), continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollExtensionsKt$stopScroll$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ScrollExtensions.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/Scroll2DScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollExtensionsKt$stopScroll$4", f = "ScrollExtensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C02104 extends SuspendLambda implements Function2<Scroll2DScope, Continuation<? super Unit>, Object> {
        int label;

        C02104(Continuation<? super C02104> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C02104(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Scroll2DScope scroll2DScope, Continuation<? super Unit> continuation) {
            return ((C02104) create(scroll2DScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public static /* synthetic */ Object stopScroll$default(Scrollable2DState scrollable2DState, MutatePriority mutatePriority, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return stopScroll(scrollable2DState, mutatePriority, (Continuation<? super Unit>) continuation);
    }

    public static final Object stopScroll(Scrollable2DState $this$stopScroll, MutatePriority scrollPriority, Continuation<? super Unit> continuation) {
        Object objScroll = $this$stopScroll.scroll(scrollPriority, new C02104(null), continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }
}
