package androidx.compose.foundation.text.input.internal;

import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: CursorAnimationState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0016\u001a\u00020\u0017H\u0086@¢\u0006\u0002\u0010\u0018J\u0006\u0010\u0019\u001a\u00020\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R$\u0010\b\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\n0\tj\n\u0012\u0006\u0012\u0004\u0018\u00010\n`\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR+\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001a"}, d2 = {"Landroidx/compose/foundation/text/input/internal/CursorAnimationState;", "", "animate", "", "<init>", "(Z)V", "getAnimate", "()Z", "animationJob", "Ljava/util/concurrent/atomic/AtomicReference;", "Lkotlinx/coroutines/Job;", "Landroidx/compose/foundation/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "<set-?>", "", "cursorAlpha", "getCursorAlpha", "()F", "setCursorAlpha", "(F)V", "cursorAlpha$delegate", "Landroidx/compose/runtime/MutableFloatState;", "snapToVisibleAndAnimate", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelAndHide", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CursorAnimationState {
    public static final int $stable = 8;
    private final boolean animate;
    private AtomicReference<Job> animationJob = new AtomicReference<>(null);

    /* JADX INFO: renamed from: cursorAlpha$delegate, reason: from kotlin metadata */
    private final MutableFloatState cursorAlpha = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);

    public CursorAnimationState(boolean animate) {
        this.animate = animate;
    }

    public final boolean getAnimate() {
        return this.animate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCursorAlpha(float f) {
        MutableFloatState $this$setValue$iv = this.cursorAlpha;
        $this$setValue$iv.setFloatValue(f);
    }

    public final float getCursorAlpha() {
        FloatState $this$getValue$iv = this.cursorAlpha;
        return $this$getValue$iv.getFloatValue();
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.CursorAnimationState$snapToVisibleAndAnimate$2, reason: invalid class name */
    /* JADX INFO: compiled from: CursorAnimationState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.CursorAnimationState$snapToVisibleAndAnimate$2", f = "CursorAnimationState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = CursorAnimationState.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    Job oldJob = (Job) CursorAnimationState.this.animationJob.getAndSet(null);
                    return Boxing.boxBoolean(MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(CursorAnimationState.this.animationJob, null, BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, null, new AnonymousClass1(oldJob, CursorAnimationState.this, null), 3, null)));
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.CursorAnimationState$snapToVisibleAndAnimate$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: CursorAnimationState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.CursorAnimationState$snapToVisibleAndAnimate$2$1", f = "CursorAnimationState.kt", i = {}, l = {72, 77, 79, 81}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Job $oldJob;
            int label;
            final /* synthetic */ CursorAnimationState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(Job job, CursorAnimationState cursorAnimationState, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$oldJob = job;
                this.this$0 = cursorAnimationState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$oldJob, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:19:0x004d A[Catch: all -> 0x0060, TRY_ENTER, TryCatch #0 {all -> 0x0060, blocks: (B:6:0x0017, B:7:0x001d, B:8:0x0022, B:22:0x005a, B:23:0x005f, B:19:0x004d), top: B:42:0x000b }] */
            /* JADX WARN: Code duplicated, block: B:26:0x0063  */
            /* JADX WARN: Code duplicated, block: B:30:0x0071  */
            /* JADX WARN: Code duplicated, block: B:31:0x0072 A[Catch: all -> 0x008a, PHI: r1
  0x0072: PHI (r1v14 androidx.compose.foundation.text.input.internal.CursorAnimationState$snapToVisibleAndAnimate$2$1) = 
  (r1v13 androidx.compose.foundation.text.input.internal.CursorAnimationState$snapToVisibleAndAnimate$2$1)
  (r1v15 androidx.compose.foundation.text.input.internal.CursorAnimationState$snapToVisibleAndAnimate$2$1)
 binds: [B:7:0x001d, B:29:0x006f] A[DONT_GENERATE, DONT_INLINE], TryCatch #1 {all -> 0x008a, blocks: (B:34:0x0084, B:28:0x0065, B:31:0x0072), top: B:43:0x0084 }] */
            /* JADX WARN: Code restructure failed: missing block: B:20:0x0057, code lost:
            
                if (kotlinx.coroutines.DelayKt.awaitCancellation(r8) == r0) goto L21;
             */
            /* JADX WARN: Code restructure failed: missing block: B:32:0x0081, code lost:
            
                if (kotlinx.coroutines.DelayKt.delay(500, r1) == r0) goto L33;
             */
            /* JADX WARN: Path cross not found for [B:13:0x0031, B:45:0x0040], limit reached: 41 */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0081 -> B:43:0x0084). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
                /*
                    r8 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r8.label
                    r2 = 500(0x1f4, double:2.47E-321)
                    r4 = 1065353216(0x3f800000, float:1.0)
                    r5 = 0
                    switch(r1) {
                        case 0: goto L2a;
                        case 1: goto L26;
                        case 2: goto L22;
                        case 3: goto L1d;
                        case 4: goto L17;
                        default: goto Le;
                    }
                Le:
                    java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r9.<init>(r0)
                    throw r9
                L17:
                    kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L60
                    r1 = r8
                    goto L84
                L1d:
                    kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L60
                    r1 = r8
                    goto L72
                L22:
                    kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L60
                    goto L5a
                L26:
                    kotlin.ResultKt.throwOnFailure(r9)
                    goto L3e
                L2a:
                    kotlin.ResultKt.throwOnFailure(r9)
                    kotlinx.coroutines.Job r1 = r8.$oldJob
                    if (r1 == 0) goto L3e
                    r6 = r8
                    kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                    r7 = 1
                    r8.label = r7
                    java.lang.Object r1 = kotlinx.coroutines.JobKt.cancelAndJoin(r1, r6)
                    if (r1 != r0) goto L3e
                    goto L59
                L3e:
                    androidx.compose.foundation.text.input.internal.CursorAnimationState r1 = r8.this$0     // Catch: java.lang.Throwable -> L8c
                    androidx.compose.foundation.text.input.internal.CursorAnimationState.access$setCursorAlpha(r1, r4)     // Catch: java.lang.Throwable -> L8c
                    androidx.compose.foundation.text.input.internal.CursorAnimationState r1 = r8.this$0     // Catch: java.lang.Throwable -> L8c
                    boolean r1 = r1.getAnimate()     // Catch: java.lang.Throwable -> L8c
                    if (r1 != 0) goto L63
                    r1 = r8
                    kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch: java.lang.Throwable -> L60
                    r2 = 2
                    r8.label = r2     // Catch: java.lang.Throwable -> L60
                    java.lang.Object r1 = kotlinx.coroutines.DelayKt.awaitCancellation(r1)     // Catch: java.lang.Throwable -> L60
                    if (r1 != r0) goto L5a
                L59:
                    return r0
                L5a:
                    kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch: java.lang.Throwable -> L60
                    r0.<init>()     // Catch: java.lang.Throwable -> L60
                    throw r0     // Catch: java.lang.Throwable -> L60
                L60:
                    r0 = move-exception
                    r1 = r8
                    goto L8e
                L63:
                    r1 = r8
                L64:
                    r6 = r1
                    kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Throwable -> L8a
                    r7 = 3
                    r1.label = r7     // Catch: java.lang.Throwable -> L8a
                    java.lang.Object r6 = kotlinx.coroutines.DelayKt.delay(r2, r6)     // Catch: java.lang.Throwable -> L8a
                    if (r6 != r0) goto L72
                    goto L83
                L72:
                    androidx.compose.foundation.text.input.internal.CursorAnimationState r6 = r1.this$0     // Catch: java.lang.Throwable -> L8a
                    androidx.compose.foundation.text.input.internal.CursorAnimationState.access$setCursorAlpha(r6, r5)     // Catch: java.lang.Throwable -> L8a
                    r6 = r1
                    kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Throwable -> L8a
                    r7 = 4
                    r1.label = r7     // Catch: java.lang.Throwable -> L8a
                    java.lang.Object r6 = kotlinx.coroutines.DelayKt.delay(r2, r6)     // Catch: java.lang.Throwable -> L8a
                    if (r6 != r0) goto L84
                L83:
                    return r0
                L84:
                    androidx.compose.foundation.text.input.internal.CursorAnimationState r6 = r1.this$0     // Catch: java.lang.Throwable -> L8a
                    androidx.compose.foundation.text.input.internal.CursorAnimationState.access$setCursorAlpha(r6, r4)     // Catch: java.lang.Throwable -> L8a
                    goto L64
                L8a:
                    r0 = move-exception
                    goto L8e
                L8c:
                    r0 = move-exception
                    r1 = r8
                L8e:
                    androidx.compose.foundation.text.input.internal.CursorAnimationState r2 = r1.this$0
                    androidx.compose.foundation.text.input.internal.CursorAnimationState.access$setCursorAlpha(r2, r5)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.CursorAnimationState.AnonymousClass2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    public final Object snapToVisibleAndAnimate(Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    public final void cancelAndHide() {
        Job job = this.animationJob.getAndSet(null);
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }
}
