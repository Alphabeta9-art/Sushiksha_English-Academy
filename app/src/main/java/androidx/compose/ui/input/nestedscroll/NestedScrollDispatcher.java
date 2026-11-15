package androidx.compose.ui.input.nestedscroll;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: NestedScrollModifier.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#¢\u0006\u0004\b$\u0010%J%\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#¢\u0006\u0004\b(\u0010)J\u0018\u0010*\u001a\u00020+2\u0006\u0010!\u001a\u00020+H\u0086@¢\u0006\u0004\b,\u0010-J \u0010.\u001a\u00020+2\u0006\u0010'\u001a\u00020+2\u0006\u0010!\u001a\u00020+H\u0086@¢\u0006\u0004\b/\u00100R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\"\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0016R\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u001c8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u00061"}, d2 = {"Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "", "<init>", "()V", "nestedScrollNode", "Landroidx/compose/ui/input/nestedscroll/NestedScrollNode;", "getNestedScrollNode$ui", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollNode;", "setNestedScrollNode$ui", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollNode;)V", "lastKnownParentNode", "getLastKnownParentNode$ui", "setLastKnownParentNode$ui", "calculateNestedScrollScope", "Lkotlin/Function0;", "Lkotlinx/coroutines/CoroutineScope;", "getCalculateNestedScrollScope$ui", "()Lkotlin/jvm/functions/Function0;", "setCalculateNestedScrollScope$ui", "(Lkotlin/jvm/functions/Function0;)V", "scope", "getScope$ui", "()Lkotlinx/coroutines/CoroutineScope;", "setScope$ui", "(Lkotlinx/coroutines/CoroutineScope;)V", "coroutineScope", "getCoroutineScope", "parent", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "getParent$ui", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "dispatchPreScroll", "Landroidx/compose/ui/geometry/Offset;", "available", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "dispatchPreScroll-OzD1aCk", "(JI)J", "dispatchPostScroll", "consumed", "dispatchPostScroll-DzOQY0M", "(JJI)J", "dispatchPreFling", "Landroidx/compose/ui/unit/Velocity;", "dispatchPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispatchPostFling", "dispatchPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ui"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class NestedScrollDispatcher {
    public static final int $stable = 8;
    private Function0<? extends CoroutineScope> calculateNestedScrollScope = new Function0<CoroutineScope>() { // from class: androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$calculateNestedScrollScope$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final CoroutineScope invoke() {
            return this.this$0.getScope();
        }
    };
    private NestedScrollNode lastKnownParentNode;
    private NestedScrollNode nestedScrollNode;
    private CoroutineScope scope;

    /* JADX INFO: renamed from: getNestedScrollNode$ui, reason: from getter */
    public final NestedScrollNode getNestedScrollNode() {
        return this.nestedScrollNode;
    }

    public final void setNestedScrollNode$ui(NestedScrollNode nestedScrollNode) {
        this.nestedScrollNode = nestedScrollNode;
    }

    /* JADX INFO: renamed from: getLastKnownParentNode$ui, reason: from getter */
    public final NestedScrollNode getLastKnownParentNode() {
        return this.lastKnownParentNode;
    }

    public final void setLastKnownParentNode$ui(NestedScrollNode nestedScrollNode) {
        this.lastKnownParentNode = nestedScrollNode;
    }

    public final Function0<CoroutineScope> getCalculateNestedScrollScope$ui() {
        return this.calculateNestedScrollScope;
    }

    public final void setCalculateNestedScrollScope$ui(Function0<? extends CoroutineScope> function0) {
        this.calculateNestedScrollScope = function0;
    }

    /* JADX INFO: renamed from: getScope$ui, reason: from getter */
    public final CoroutineScope getScope() {
        return this.scope;
    }

    public final void setScope$ui(CoroutineScope coroutineScope) {
        this.scope = coroutineScope;
    }

    public final CoroutineScope getCoroutineScope() {
        CoroutineScope coroutineScopeInvoke = this.calculateNestedScrollScope.invoke();
        if (coroutineScopeInvoke == null) {
            throw new IllegalStateException("in order to access nested coroutine scope you need to attach dispatcher to the `Modifier.nestedScroll` first.");
        }
        return coroutineScopeInvoke;
    }

    public final NestedScrollConnection getParent$ui() {
        NestedScrollNode nestedScrollNode = this.nestedScrollNode;
        return nestedScrollNode != null ? nestedScrollNode.getParentNestedScrollNode$ui() : null;
    }

    /* JADX INFO: renamed from: dispatchPreScroll-OzD1aCk, reason: not valid java name */
    public final long m6274dispatchPreScrollOzD1aCk(long available, int source) {
        NestedScrollConnection parent$ui = getParent$ui();
        return parent$ui != null ? parent$ui.mo897onPreScrollOzD1aCk(available, source) : Offset.INSTANCE.m4868getZeroF1C5BW0();
    }

    /* JADX INFO: renamed from: dispatchPostScroll-DzOQY0M, reason: not valid java name */
    public final long m6272dispatchPostScrollDzOQY0M(long consumed, long available, int source) {
        NestedScrollConnection parent$ui = getParent$ui();
        return parent$ui != null ? parent$ui.mo574onPostScrollDzOQY0M(consumed, available, source) : Offset.INSTANCE.m4868getZeroF1C5BW0();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX INFO: renamed from: dispatchPreFling-QWom1Mo, reason: not valid java name */
    public final Object m6273dispatchPreFlingQWom1Mo(long j, Continuation<? super Velocity> continuation) {
        NestedScrollDispatcher$dispatchPreFling$1 nestedScrollDispatcher$dispatchPreFling$1;
        long jM8154getZero9UxMQ8M;
        Object objMo896onPreFlingQWom1Mo;
        if (continuation instanceof NestedScrollDispatcher$dispatchPreFling$1) {
            nestedScrollDispatcher$dispatchPreFling$1 = (NestedScrollDispatcher$dispatchPreFling$1) continuation;
            if ((nestedScrollDispatcher$dispatchPreFling$1.label & Integer.MIN_VALUE) != 0) {
                nestedScrollDispatcher$dispatchPreFling$1.label -= Integer.MIN_VALUE;
            } else {
                nestedScrollDispatcher$dispatchPreFling$1 = new NestedScrollDispatcher$dispatchPreFling$1(this, continuation);
            }
        } else {
            nestedScrollDispatcher$dispatchPreFling$1 = new NestedScrollDispatcher$dispatchPreFling$1(this, continuation);
        }
        Object $result = nestedScrollDispatcher$dispatchPreFling$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (nestedScrollDispatcher$dispatchPreFling$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                NestedScrollConnection parent$ui = getParent$ui();
                if (parent$ui != null) {
                    nestedScrollDispatcher$dispatchPreFling$1.label = 1;
                    objMo896onPreFlingQWom1Mo = parent$ui.mo896onPreFlingQWom1Mo(j, nestedScrollDispatcher$dispatchPreFling$1);
                    if (objMo896onPreFlingQWom1Mo == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    jM8154getZero9UxMQ8M = ((Velocity) objMo896onPreFlingQWom1Mo).getPackedValue();
                } else {
                    jM8154getZero9UxMQ8M = Velocity.INSTANCE.m8154getZero9UxMQ8M();
                }
                return Velocity.m8134boximpl(jM8154getZero9UxMQ8M);
            case 1:
                ResultKt.throwOnFailure($result);
                objMo896onPreFlingQWom1Mo = $result;
                jM8154getZero9UxMQ8M = ((Velocity) objMo896onPreFlingQWom1Mo).getPackedValue();
                return Velocity.m8134boximpl(jM8154getZero9UxMQ8M);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004e, code lost:
    
        if (r9 == r7) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006c, code lost:
    
        if (r9 == r7) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006e, code lost:
    
        return r7;
     */
    /* JADX INFO: renamed from: dispatchPostFling-RZ2iAVY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m6271dispatchPostFlingRZ2iAVY(long r9, long r11, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r13) {
        /*
            r8 = this;
            boolean r0 = r13 instanceof androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$dispatchPostFling$1
            if (r0 == 0) goto L14
            r0 = r13
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$dispatchPostFling$1 r0 = (androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$dispatchPostFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$dispatchPostFling$1 r0 = new androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$dispatchPostFling$1
            r0.<init>(r8, r13)
        L19:
            r6 = r0
            java.lang.Object r0 = r6.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            switch(r1) {
                case 0: goto L37;
                case 1: goto L32;
                case 2: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2d:
            kotlin.ResultKt.throwOnFailure(r0)
            r9 = r0
            goto L6f
        L32:
            kotlin.ResultKt.throwOnFailure(r0)
            r9 = r0
            goto L51
        L37:
            kotlin.ResultKt.throwOnFailure(r0)
            r1 = r8
            r2 = r9
            r4 = r11
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r9 = r1.getParent$ui()
            if (r9 != 0) goto L5f
            androidx.compose.ui.input.nestedscroll.NestedScrollNode r1 = r1.lastKnownParentNode
            if (r1 == 0) goto L58
            r9 = 1
            r6.label = r9
            java.lang.Object r9 = r1.mo573onPostFlingRZ2iAVY(r2, r4, r6)
            if (r9 != r7) goto L51
            goto L6e
        L51:
            androidx.compose.ui.unit.Velocity r9 = (androidx.compose.ui.unit.Velocity) r9
            long r9 = r9.getPackedValue()
            goto L7c
        L58:
            androidx.compose.ui.unit.Velocity$Companion r9 = androidx.compose.ui.unit.Velocity.INSTANCE
            long r9 = r9.m8154getZero9UxMQ8M()
            goto L7c
        L5f:
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r1 = r1.getParent$ui()
            if (r1 == 0) goto L76
            r9 = 2
            r6.label = r9
            java.lang.Object r9 = r1.mo573onPostFlingRZ2iAVY(r2, r4, r6)
            if (r9 != r7) goto L6f
        L6e:
            return r7
        L6f:
            androidx.compose.ui.unit.Velocity r9 = (androidx.compose.ui.unit.Velocity) r9
            long r9 = r9.getPackedValue()
            goto L7c
        L76:
            androidx.compose.ui.unit.Velocity$Companion r9 = androidx.compose.ui.unit.Velocity.INSTANCE
            long r9 = r9.m8154getZero9UxMQ8M()
        L7c:
            androidx.compose.ui.unit.Velocity r9 = androidx.compose.ui.unit.Velocity.m8134boximpl(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher.m6271dispatchPostFlingRZ2iAVY(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
