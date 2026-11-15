package androidx.compose.foundation.layout;

import android.graphics.Insets;
import android.os.CancellationSignal;
import android.view.View;
import android.view.WindowInsetsAnimationControlListener;
import android.view.WindowInsetsAnimationController;
import android.view.WindowInsetsController;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Velocity;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: WindowInsetsConnection.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010#\u001a\u0004\u0018\u00010\u0016H\u0082@¢\u0006\u0002\u0010$J\u001f\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)H\u0016¢\u0006\u0004\b*\u0010+J'\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)H\u0016¢\u0006\u0004\b.\u0010/J\u001f\u00100\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u00101\u001a\u00020\u001cH\u0002¢\u0006\u0004\b2\u00103J\u0018\u00104\u001a\u0002052\u0006\u0010'\u001a\u000205H\u0096@¢\u0006\u0004\b6\u00107J \u00108\u001a\u0002052\u0006\u0010-\u001a\u0002052\u0006\u0010'\u001a\u000205H\u0096@¢\u0006\u0004\b9\u0010:J(\u0010;\u001a\u0002052\u0006\u0010'\u001a\u0002052\u0006\u0010<\u001a\u00020\u001c2\u0006\u0010=\u001a\u00020\u0018H\u0082@¢\u0006\u0004\b>\u0010?J\u0010\u0010@\u001a\u00020 2\u0006\u0010A\u001a\u00020\u001cH\u0002J\u0018\u0010B\u001a\u00020 2\u0006\u0010C\u001a\u00020\u00162\u0006\u0010D\u001a\u00020EH\u0016J\u0006\u0010F\u001a\u00020 J\u0010\u0010G\u001a\u00020 2\u0006\u0010C\u001a\u00020\u0016H\u0016J\u0012\u0010H\u001a\u00020 2\b\u0010C\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010I\u001a\u00020 H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010!\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Landroidx/compose/foundation/layout/WindowInsetsNestedScrollConnection;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "Landroid/view/WindowInsetsAnimationControlListener;", "windowInsets", "Landroidx/compose/foundation/layout/AndroidWindowInsets;", "view", "Landroid/view/View;", "sideCalculator", "Landroidx/compose/foundation/layout/SideCalculator;", "density", "Landroidx/compose/ui/unit/Density;", "<init>", "(Landroidx/compose/foundation/layout/AndroidWindowInsets;Landroid/view/View;Landroidx/compose/foundation/layout/SideCalculator;Landroidx/compose/ui/unit/Density;)V", "getWindowInsets", "()Landroidx/compose/foundation/layout/AndroidWindowInsets;", "getView", "()Landroid/view/View;", "getSideCalculator", "()Landroidx/compose/foundation/layout/SideCalculator;", "getDensity", "()Landroidx/compose/ui/unit/Density;", "animationController", "Landroid/view/WindowInsetsAnimationController;", "isControllerRequested", "", "cancellationSignal", "Landroid/os/CancellationSignal;", "partialConsumption", "", "animationJob", "Lkotlinx/coroutines/Job;", "requestAnimationController", "", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "getAnimationController", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPreScroll", "Landroidx/compose/ui/geometry/Offset;", "available", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPreScroll-OzD1aCk", "(JI)J", "onPostScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "scroll", "scrollAmount", "scroll-8S9VItk", "(JF)J", "onPreFling", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostFling", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fling", "flingAmount", "towardShown", "fling-huYlsQE", "(JFZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "adjustInsets", "inset", "onReady", "controller", "types", "", "dispose", "onFinished", "onCancelled", "animationEnded", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class WindowInsetsNestedScrollConnection implements NestedScrollConnection, WindowInsetsAnimationControlListener {
    private WindowInsetsAnimationController animationController;
    private Job animationJob;
    private final CancellationSignal cancellationSignal = new CancellationSignal();
    private CancellableContinuation<? super WindowInsetsAnimationController> continuation;
    private final Density density;
    private boolean isControllerRequested;
    private float partialConsumption;
    private final SideCalculator sideCalculator;
    private final View view;
    private final AndroidWindowInsets windowInsets;

    public WindowInsetsNestedScrollConnection(AndroidWindowInsets windowInsets, View view, SideCalculator sideCalculator, Density density) {
        this.windowInsets = windowInsets;
        this.view = view;
        this.sideCalculator = sideCalculator;
        this.density = density;
    }

    public final AndroidWindowInsets getWindowInsets() {
        return this.windowInsets;
    }

    public final View getView() {
        return this.view;
    }

    public final SideCalculator getSideCalculator() {
        return this.sideCalculator;
    }

    public final Density getDensity() {
        return this.density;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestAnimationController() {
        if (!this.isControllerRequested) {
            this.isControllerRequested = true;
            WindowInsetsController windowInsetsController = this.view.getWindowInsetsController();
            if (windowInsetsController != null) {
                windowInsetsController.controlWindowInsetsAnimation(this.windowInsets.getType(), -1L, null, this.cancellationSignal, this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getAnimationController(Continuation<? super WindowInsetsAnimationController> continuation) {
        WindowInsetsAnimationController windowInsetsAnimationController = this.animationController;
        if (windowInsetsAnimationController != null) {
            return windowInsetsAnimationController;
        }
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl continuation2 = cancellable$iv;
        this.continuation = continuation2;
        requestAnimationController();
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk, reason: not valid java name */
    public long mo897onPreScrollOzD1aCk(long available, int source) {
        int bits$iv$iv$iv = (int) (available >> 32);
        int bits$iv$iv$iv2 = (int) (4294967295L & available);
        return m895scroll8S9VItk(available, this.sideCalculator.hideMotion(Float.intBitsToFloat(bits$iv$iv$iv), Float.intBitsToFloat(bits$iv$iv$iv2)));
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo574onPostScrollDzOQY0M(long consumed, long available, int source) {
        int bits$iv$iv$iv = (int) (available >> 32);
        int bits$iv$iv$iv2 = (int) (4294967295L & available);
        return m895scroll8S9VItk(available, this.sideCalculator.showMotion(Float.intBitsToFloat(bits$iv$iv$iv), Float.intBitsToFloat(bits$iv$iv$iv2)));
    }

    /* JADX INFO: renamed from: scroll-8S9VItk, reason: not valid java name */
    private final long m895scroll8S9VItk(long available, float scrollAmount) {
        Job it = this.animationJob;
        if (it != null) {
            it.cancel((CancellationException) new WindowInsetsAnimationCancelledException());
            this.animationJob = null;
        }
        WindowInsetsAnimationController animationController = this.animationController;
        if (!(scrollAmount == 0.0f)) {
            if (this.windowInsets.isVisible() != (scrollAmount > 0.0f) || animationController != null) {
                if (animationController == null) {
                    this.partialConsumption = 0.0f;
                    requestAnimationController();
                    return this.sideCalculator.mo846consumedOffsetsMKHz9U(available);
                }
                int hidden = this.sideCalculator.valueOf(animationController.getHiddenStateInsets());
                int shown = this.sideCalculator.valueOf(animationController.getShownStateInsets());
                Insets currentInsets = animationController.getCurrentInsets();
                int current = this.sideCalculator.valueOf(currentInsets);
                int target = scrollAmount > 0.0f ? shown : hidden;
                if (current == target) {
                    this.partialConsumption = 0.0f;
                    return Offset.INSTANCE.m4868getZeroF1C5BW0();
                }
                float total = current + scrollAmount + this.partialConsumption;
                int next = RangesKt.coerceIn(Math.round(total), hidden, shown);
                float $this$fastRoundToInt$iv = Math.round(total);
                this.partialConsumption = total - $this$fastRoundToInt$iv;
                if (next != current) {
                    animationController.setInsetsAndAlpha(this.sideCalculator.adjustInsets(currentInsets, next), 1.0f, 0.0f);
                }
                return this.sideCalculator.mo846consumedOffsetsMKHz9U(available);
            }
        }
        return Offset.INSTANCE.m4868getZeroF1C5BW0();
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreFling-QWom1Mo, reason: not valid java name */
    public Object mo896onPreFlingQWom1Mo(long j, Continuation<? super Velocity> continuation) {
        return m894flinghuYlsQE(j, this.sideCalculator.hideMotion(Velocity.m8143getXimpl(j), Velocity.m8144getYimpl(j)), false, continuation);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    public Object mo573onPostFlingRZ2iAVY(long j, long j2, Continuation<? super Velocity> continuation) {
        return m894flinghuYlsQE(j2, this.sideCalculator.showMotion(Velocity.m8143getXimpl(j2), Velocity.m8144getYimpl(j2)), true, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 4361. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    /* JADX INFO: renamed from: fling-huYlsQE, reason: not valid java name */
    public final java.lang.Object m894flinghuYlsQE(long r26, float r28, boolean r29, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r30) {
        /*
            Method dump skipped, instruction units count: 436
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.WindowInsetsNestedScrollConnection.m894flinghuYlsQE(long, float, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void adjustInsets(float inset) {
        WindowInsetsAnimationController it = this.animationController;
        if (it != null) {
            Insets currentInsets = it.getCurrentInsets();
            Insets nextInsets = this.sideCalculator.adjustInsets(currentInsets, Math.round(inset));
            it.setInsetsAndAlpha(nextInsets, 1.0f, 0.0f);
        }
    }

    public void onReady(WindowInsetsAnimationController controller, int types) {
        this.animationController = controller;
        this.isControllerRequested = false;
        CancellableContinuation<? super WindowInsetsAnimationController> cancellableContinuation = this.continuation;
        if (cancellableContinuation != null) {
            cancellableContinuation.resume(controller, (Function3<? super Throwable, ? super WindowInsetsAnimationController, ? super CoroutineContext, Unit>) new Function3() { // from class: androidx.compose.foundation.layout.WindowInsetsNestedScrollConnection$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return Unit.INSTANCE;
                }
            });
        }
        this.continuation = null;
    }

    public final void dispose() {
        CancellableContinuation<? super WindowInsetsAnimationController> cancellableContinuation = this.continuation;
        if (cancellableContinuation != null) {
            cancellableContinuation.resume((Object) null, new Function3() { // from class: androidx.compose.foundation.layout.WindowInsetsNestedScrollConnection.dispose.1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3) {
                    invoke((Throwable) p1, (Void) p2, (CoroutineContext) p3);
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable th, Void r2, CoroutineContext coroutineContext) {
                }
            });
        }
        Job job = this.animationJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        WindowInsetsAnimationController animationController = this.animationController;
        if (animationController != null) {
            boolean visible = !Intrinsics.areEqual(animationController.getCurrentInsets(), animationController.getHiddenStateInsets());
            animationController.finish(visible);
        }
    }

    public void onFinished(WindowInsetsAnimationController controller) {
        animationEnded();
    }

    public void onCancelled(WindowInsetsAnimationController controller) {
        animationEnded();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x000d  */
    private final void animationEnded() {
        boolean z;
        WindowInsetsAnimationController windowInsetsAnimationController;
        WindowInsetsAnimationController windowInsetsAnimationController2 = this.animationController;
        if (windowInsetsAnimationController2 != null) {
            z = windowInsetsAnimationController2.isReady();
        }
        if (z && (windowInsetsAnimationController = this.animationController) != null) {
            windowInsetsAnimationController.finish(this.windowInsets.isVisible());
        }
        this.animationController = null;
        CancellableContinuation<? super WindowInsetsAnimationController> cancellableContinuation = this.continuation;
        if (cancellableContinuation != null) {
            cancellableContinuation.resume((Object) null, new Function3() { // from class: androidx.compose.foundation.layout.WindowInsetsNestedScrollConnection.animationEnded.1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3) {
                    invoke((Throwable) p1, (Void) p2, (CoroutineContext) p3);
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable th, Void r2, CoroutineContext coroutineContext) {
                }
            });
        }
        this.continuation = null;
        Job job = this.animationJob;
        if (job != null) {
            job.cancel((CancellationException) new WindowInsetsAnimationCancelledException());
        }
        this.animationJob = null;
        this.partialConsumption = 0.0f;
        this.isControllerRequested = false;
    }
}
