package androidx.compose.material;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: SnackbarHost.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0017B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00078F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Landroidx/compose/material/SnackbarHostState;", "", "<init>", "()V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "<set-?>", "Landroidx/compose/material/SnackbarData;", "currentSnackbarData", "getCurrentSnackbarData", "()Landroidx/compose/material/SnackbarData;", "setCurrentSnackbarData", "(Landroidx/compose/material/SnackbarData;)V", "currentSnackbarData$delegate", "Landroidx/compose/runtime/MutableState;", "showSnackbar", "Landroidx/compose/material/SnackbarResult;", "message", "", "actionLabel", TypedValues.TransitionType.S_DURATION, "Landroidx/compose/material/SnackbarDuration;", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/material/SnackbarDuration;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "SnackbarDataImpl", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SnackbarHostState {
    public static final int $stable = 0;
    private final Mutex mutex = MutexKt.Mutex$default(false, 1, null);

    /* JADX INFO: renamed from: currentSnackbarData$delegate, reason: from kotlin metadata */
    private final MutableState currentSnackbarData = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* JADX INFO: renamed from: androidx.compose.material.SnackbarHostState$showSnackbar$1, reason: invalid class name */
    /* JADX INFO: compiled from: SnackbarHost.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material.SnackbarHostState", f = "SnackbarHost.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {368, 371}, m = "showSnackbar", n = {"message", "actionLabel", TypedValues.TransitionType.S_DURATION, "$this$withLock_u24default$iv", "message", "actionLabel", TypedValues.TransitionType.S_DURATION, "$this$withLock_u24default$iv", "$completion$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnackbarHostState.this.showSnackbar(null, null, null, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrentSnackbarData(SnackbarData snackbarData) {
        MutableState $this$setValue$iv = this.currentSnackbarData;
        $this$setValue$iv.setValue(snackbarData);
    }

    public final SnackbarData getCurrentSnackbarData() {
        State $this$getValue$iv = this.currentSnackbarData;
        return (SnackbarData) $this$getValue$iv.getValue();
    }

    public static /* synthetic */ Object showSnackbar$default(SnackbarHostState snackbarHostState, String str, String str2, SnackbarDuration snackbarDuration, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            snackbarDuration = SnackbarDuration.Short;
        }
        return snackbarHostState.showSnackbar(str, str2, snackbarDuration, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x00df A[Catch: all -> 0x00f8, TRY_LEAVE, TryCatch #3 {all -> 0x00f8, blocks: (B:23:0x00a3, B:25:0x00df), top: B:49:0x00a3 }] */
    /* JADX WARN: Code duplicated, block: B:27:0x00e7 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:28:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object showSnackbar(String str, String str2, SnackbarDuration snackbarDuration, Continuation<? super SnackbarResult> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        SnackbarHostState snackbarHostState;
        String actionLabel;
        String message;
        SnackbarDuration duration;
        Mutex $this$withLock_u24default$iv;
        Mutex $this$withLock_u24default$iv2;
        Object owner$iv;
        Object result;
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
                snackbarHostState = this;
                actionLabel = str2;
                message = str;
                duration = snackbarDuration;
                Mutex $this$withLock_u24default$iv3 = snackbarHostState.mutex;
                anonymousClass2.L$0 = message;
                anonymousClass2.L$1 = actionLabel;
                anonymousClass2.L$2 = duration;
                anonymousClass2.L$3 = $this$withLock_u24default$iv3;
                anonymousClass2.label = 1;
                if ($this$withLock_u24default$iv3.lock(null, anonymousClass2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $this$withLock_u24default$iv = $this$withLock_u24default$iv3;
                $this$withLock_u24default$iv2 = null;
                owner$iv = null;
                try {
                    anonymousClass2.L$0 = message;
                    anonymousClass2.L$1 = actionLabel;
                    anonymousClass2.L$2 = duration;
                    anonymousClass2.L$3 = $this$withLock_u24default$iv;
                    anonymousClass2.L$4 = anonymousClass2;
                    anonymousClass2.label = 2;
                    AnonymousClass1 uCont$iv = anonymousClass2;
                    CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(uCont$iv), 1);
                    cancellable$iv.initCancellability();
                    CancellableContinuationImpl continuation2 = cancellable$iv;
                    snackbarHostState.setCurrentSnackbarData(new SnackbarDataImpl(message, actionLabel, duration, continuation2));
                    result = cancellable$iv.getResult();
                    if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        DebugProbesKt.probeCoroutineSuspended(anonymousClass2);
                    }
                    if (result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    try {
                        snackbarHostState.setCurrentSnackbarData(null);
                        $this$withLock_u24default$iv.unlock(owner$iv);
                        return result;
                    } catch (Throwable th) {
                        th = th;
                        $this$withLock_u24default$iv.unlock(owner$iv);
                        throw th;
                    }
                    break;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        snackbarHostState.setCurrentSnackbarData(null);
                        throw th;
                    } catch (Throwable th3) {
                        th = th3;
                        $this$withLock_u24default$iv.unlock(owner$iv);
                        throw th;
                    }
                }
            case 1:
                snackbarHostState = this;
                $this$withLock_u24default$iv2 = null;
                Mutex $this$withLock_u24default$iv4 = (Mutex) anonymousClass2.L$3;
                duration = (SnackbarDuration) anonymousClass2.L$2;
                actionLabel = (String) anonymousClass2.L$1;
                message = (String) anonymousClass2.L$0;
                ResultKt.throwOnFailure($result);
                owner$iv = null;
                $this$withLock_u24default$iv = $this$withLock_u24default$iv4;
                anonymousClass2.L$0 = message;
                anonymousClass2.L$1 = actionLabel;
                anonymousClass2.L$2 = duration;
                anonymousClass2.L$3 = $this$withLock_u24default$iv;
                anonymousClass2.L$4 = anonymousClass2;
                anonymousClass2.label = 2;
                AnonymousClass1 uCont$iv2 = anonymousClass2;
                CancellableContinuationImpl cancellable$iv2 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(uCont$iv2), 1);
                cancellable$iv2.initCancellability();
                CancellableContinuationImpl continuation3 = cancellable$iv2;
                snackbarHostState.setCurrentSnackbarData(new SnackbarDataImpl(message, actionLabel, duration, continuation3));
                result = cancellable$iv2.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(anonymousClass2);
                    break;
                }
                if (result == coroutine_suspended) {
                    return coroutine_suspended;
                }
                snackbarHostState.setCurrentSnackbarData(null);
                $this$withLock_u24default$iv.unlock(owner$iv);
                return result;
            case 2:
                snackbarHostState = this;
                $this$withLock_u24default$iv = (Mutex) anonymousClass2.L$3;
                try {
                    ResultKt.throwOnFailure($result);
                    result = $result;
                    owner$iv = null;
                    snackbarHostState.setCurrentSnackbarData(null);
                    $this$withLock_u24default$iv.unlock(owner$iv);
                    return result;
                } catch (Throwable th4) {
                    th = th4;
                    owner$iv = null;
                    snackbarHostState.setCurrentSnackbarData(null);
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: compiled from: SnackbarHost.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/compose/material/SnackbarHostState$SnackbarDataImpl;", "Landroidx/compose/material/SnackbarData;", "message", "", "actionLabel", TypedValues.TransitionType.S_DURATION, "Landroidx/compose/material/SnackbarDuration;", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "Landroidx/compose/material/SnackbarResult;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/material/SnackbarDuration;Lkotlinx/coroutines/CancellableContinuation;)V", "getMessage", "()Ljava/lang/String;", "getActionLabel", "getDuration", "()Landroidx/compose/material/SnackbarDuration;", "performAction", "", "dismiss", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class SnackbarDataImpl implements SnackbarData {
        private final String actionLabel;
        private final CancellableContinuation<SnackbarResult> continuation;
        private final SnackbarDuration duration;
        private final String message;

        /* JADX WARN: Multi-variable type inference failed */
        public SnackbarDataImpl(String message, String actionLabel, SnackbarDuration duration, CancellableContinuation<? super SnackbarResult> cancellableContinuation) {
            this.message = message;
            this.actionLabel = actionLabel;
            this.duration = duration;
            this.continuation = cancellableContinuation;
        }

        @Override // androidx.compose.material.SnackbarData
        public String getMessage() {
            return this.message;
        }

        @Override // androidx.compose.material.SnackbarData
        public String getActionLabel() {
            return this.actionLabel;
        }

        @Override // androidx.compose.material.SnackbarData
        public SnackbarDuration getDuration() {
            return this.duration;
        }

        @Override // androidx.compose.material.SnackbarData
        public void performAction() {
            if (this.continuation.isActive()) {
                CancellableContinuation<SnackbarResult> cancellableContinuation = this.continuation;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m9088constructorimpl(SnackbarResult.ActionPerformed));
            }
        }

        @Override // androidx.compose.material.SnackbarData
        public void dismiss() {
            if (this.continuation.isActive()) {
                CancellableContinuation<SnackbarResult> cancellableContinuation = this.continuation;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m9088constructorimpl(SnackbarResult.Dismissed));
            }
        }
    }
}
