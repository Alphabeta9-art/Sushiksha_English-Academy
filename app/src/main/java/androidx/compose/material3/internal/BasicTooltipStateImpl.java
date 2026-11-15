package androidx.compose.material3.internal;

import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.material3.TooltipState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BasicTooltip.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0096@¢\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u0016H\u0016J\b\u0010\u001c\u001a\u00020\u0016H\u0016R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u000b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00038V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u000b\u0010\t\"\u0004\b\f\u0010\rR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/compose/material3/internal/BasicTooltipStateImpl;", "Landroidx/compose/material3/TooltipState;", "initialIsVisible", "", "isPersistent", "mutatorMutex", "Landroidx/compose/foundation/MutatorMutex;", "<init>", "(ZZLandroidx/compose/foundation/MutatorMutex;)V", "()Z", "<set-?>", "isVisible", "setVisible", "(Z)V", "isVisible$delegate", "Landroidx/compose/runtime/MutableState;", "transition", "Landroidx/compose/animation/core/MutableTransitionState;", "getTransition", "()Landroidx/compose/animation/core/MutableTransitionState;", "job", "Lkotlinx/coroutines/CancellableContinuation;", "", "show", "mutatePriority", "Landroidx/compose/foundation/MutatePriority;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dismiss", "onDispose", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class BasicTooltipStateImpl implements TooltipState {
    private final boolean isPersistent;

    /* JADX INFO: renamed from: isVisible$delegate, reason: from kotlin metadata */
    private final MutableState isVisible;
    private CancellableContinuation<? super Unit> job;
    private final MutatorMutex mutatorMutex;
    private final MutableTransitionState<Boolean> transition = new MutableTransitionState<>(false);

    public BasicTooltipStateImpl(boolean initialIsVisible, boolean isPersistent, MutatorMutex mutatorMutex) {
        this.isPersistent = isPersistent;
        this.mutatorMutex = mutatorMutex;
        this.isVisible = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(initialIsVisible), null, 2, null);
    }

    @Override // androidx.compose.material3.TooltipState
    /* JADX INFO: renamed from: isPersistent, reason: from getter */
    public boolean getIsPersistent() {
        return this.isPersistent;
    }

    @Override // androidx.compose.material3.TooltipState
    /* JADX INFO: renamed from: isVisible */
    public boolean getIsVisible() {
        State $this$getValue$iv = this.isVisible;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public void setVisible(boolean z) {
        MutableState $this$setValue$iv = this.isVisible;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    @Override // androidx.compose.material3.TooltipState
    public MutableTransitionState<Boolean> getTransition() {
        return this.transition;
    }

    @Override // androidx.compose.material3.TooltipState
    public Object show(MutatePriority mutatePriority, Continuation<? super Unit> continuation) {
        Function1 cancellableShow = new BasicTooltipStateImpl$show$cancellableShow$1(this, null);
        Object objMutate = this.mutatorMutex.mutate(mutatePriority, new AnonymousClass2(cancellableShow, null), continuation);
        return objMutate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMutate : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material3.internal.BasicTooltipStateImpl$show$2, reason: invalid class name */
    /* JADX INFO: compiled from: BasicTooltip.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltipStateImpl$show$2", f = "BasicTooltip.kt", i = {}, l = {410, 412}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Continuation<? super Unit>, Object> $cancellableShow;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.$cancellableShow = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return BasicTooltipStateImpl.this.new AnonymousClass2(this.$cancellableShow, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0049, code lost:
        
            if (kotlinx.coroutines.TimeoutKt.withTimeout(1500, new androidx.compose.material3.internal.BasicTooltipStateImpl.AnonymousClass2.AnonymousClass1(r6.$cancellableShow, null), r6) == r0) goto L18;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 0
                switch(r1) {
                    case 0: goto L1a;
                    case 1: goto L16;
                    case 2: goto L12;
                    default: goto La;
                }
            La:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L12:
                kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L56
                goto L4c
            L16:
                kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L56
                goto L32
            L1a:
                kotlin.ResultKt.throwOnFailure(r7)
                androidx.compose.material3.internal.BasicTooltipStateImpl r1 = androidx.compose.material3.internal.BasicTooltipStateImpl.this     // Catch: java.lang.Throwable -> L56
                boolean r1 = r1.getIsPersistent()     // Catch: java.lang.Throwable -> L56
                if (r1 == 0) goto L33
                kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r1 = r6.$cancellableShow     // Catch: java.lang.Throwable -> L56
                r3 = 1
                r6.label = r3     // Catch: java.lang.Throwable -> L56
                java.lang.Object r1 = r1.invoke(r6)     // Catch: java.lang.Throwable -> L56
                if (r1 != r0) goto L32
                goto L4b
            L32:
                goto L4d
            L33:
                androidx.compose.material3.internal.BasicTooltipStateImpl$show$2$1 r1 = new androidx.compose.material3.internal.BasicTooltipStateImpl$show$2$1     // Catch: java.lang.Throwable -> L56
                kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r3 = r6.$cancellableShow     // Catch: java.lang.Throwable -> L56
                r4 = 0
                r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> L56
                kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1     // Catch: java.lang.Throwable -> L56
                r3 = r6
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3     // Catch: java.lang.Throwable -> L56
                r4 = 2
                r6.label = r4     // Catch: java.lang.Throwable -> L56
                r4 = 1500(0x5dc, double:7.41E-321)
                java.lang.Object r1 = kotlinx.coroutines.TimeoutKt.withTimeout(r4, r1, r3)     // Catch: java.lang.Throwable -> L56
                if (r1 != r0) goto L4c
            L4b:
                return r0
            L4c:
            L4d:
                androidx.compose.material3.internal.BasicTooltipStateImpl r0 = androidx.compose.material3.internal.BasicTooltipStateImpl.this
                r0.setVisible(r2)
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            L56:
                r0 = move-exception
                androidx.compose.material3.internal.BasicTooltipStateImpl r1 = androidx.compose.material3.internal.BasicTooltipStateImpl.this
                r1.setVisible(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.BasicTooltipStateImpl.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: androidx.compose.material3.internal.BasicTooltipStateImpl$show$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: BasicTooltip.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.material3.internal.BasicTooltipStateImpl$show$2$1", f = "BasicTooltip.kt", i = {}, l = {412}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Continuation<? super Unit>, Object> $cancellableShow;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$cancellableShow = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$cancellableShow, continuation);
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
                        Function1<Continuation<? super Unit>, Object> function1 = this.$cancellableShow;
                        this.label = 1;
                        if (function1.invoke(this) == coroutine_suspended) {
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

    @Override // androidx.compose.material3.TooltipState
    public void dismiss() {
        setVisible(false);
    }

    @Override // androidx.compose.material3.TooltipState
    public void onDispose() {
        CancellableContinuation<? super Unit> cancellableContinuation = this.job;
        if (cancellableContinuation != null) {
            CancellableContinuation.DefaultImpls.cancel$default(cancellableContinuation, null, 1, null);
        }
    }
}
