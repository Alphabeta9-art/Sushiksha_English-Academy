package androidx.compose.foundation.text.contextmenu.gestures;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: RightClickGestures.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0080@¢\u0006\u0002\u0010\u0006\u001a\u0012\u0010\u0007\u001a\u00020\b*\u00020\tH\u0082@¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"onRightClickDown", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "onDown", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitFirstRightClickDown", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RightClickGesturesKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.text.contextmenu.gestures.RightClickGesturesKt$awaitFirstRightClickDown$1, reason: invalid class name */
    /* JADX INFO: compiled from: RightClickGestures.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.contextmenu.gestures.RightClickGesturesKt", f = "RightClickGestures.kt", i = {0}, l = {45}, m = "awaitFirstRightClickDown", n = {"$this$awaitFirstRightClickDown"}, s = {"L$0"}, v = 1)
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
            return RightClickGesturesKt.awaitFirstRightClickDown(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.contextmenu.gestures.RightClickGesturesKt$onRightClickDown$2, reason: invalid class name */
    /* JADX INFO: compiled from: RightClickGestures.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.contextmenu.gestures.RightClickGesturesKt$onRightClickDown$2", f = "RightClickGestures.kt", i = {0}, l = {32, 35}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"}, v = 1)
    static final class AnonymousClass2 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Offset, Unit> $onDown;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function1<? super Offset, Unit> function1, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$onDown = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$onDown, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:14:0x005c A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:17:0x0061  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AwaitPointerEventScope $this$awaitEachGesture;
            PointerInputChange pointerInputChange;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    AwaitPointerEventScope $this$awaitEachGesture2 = (AwaitPointerEventScope) this.L$0;
                    this.L$0 = $this$awaitEachGesture2;
                    this.label = 1;
                    Object objAwaitFirstRightClickDown = RightClickGesturesKt.awaitFirstRightClickDown($this$awaitEachGesture2, this);
                    if (objAwaitFirstRightClickDown == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objAwaitFirstRightClickDown;
                    $this$awaitEachGesture = $this$awaitEachGesture2;
                    PointerInputChange down = (PointerInputChange) $result;
                    down.consume();
                    this.$onDown.invoke(Offset.m4841boximpl(down.getPosition()));
                    this.L$0 = null;
                    this.label = 2;
                    $result = TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pointerInputChange = (PointerInputChange) $result;
                    if (pointerInputChange != null) {
                        pointerInputChange.consume();
                    }
                    return Unit.INSTANCE;
                case 1:
                    AwaitPointerEventScope $this$awaitEachGesture3 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    $this$awaitEachGesture = $this$awaitEachGesture3;
                    PointerInputChange down2 = (PointerInputChange) $result;
                    down2.consume();
                    this.$onDown.invoke(Offset.m4841boximpl(down2.getPosition()));
                    this.L$0 = null;
                    this.label = 2;
                    $result = TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pointerInputChange = (PointerInputChange) $result;
                    if (pointerInputChange != null) {
                        pointerInputChange.consume();
                    }
                    return Unit.INSTANCE;
                case 2:
                    ResultKt.throwOnFailure($result);
                    pointerInputChange = (PointerInputChange) $result;
                    if (pointerInputChange != null) {
                        pointerInputChange.consume();
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public static final Object onRightClickDown(PointerInputScope $this$onRightClickDown, Function1<? super Offset, Unit> function1, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$onRightClickDown, new AnonymousClass2(function1, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:23:0x0072  */
    /* JADX WARN: Code duplicated, block: B:26:0x0086 A[LOOP:0: B:21:0x006f->B:26:0x0086, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:33:0x0084 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x004d -> B:18:0x0054). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object awaitFirstRightClickDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r19, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof androidx.compose.foundation.text.contextmenu.gestures.RightClickGesturesKt.AnonymousClass1
            if (r1 == 0) goto L16
            r1 = r0
            androidx.compose.foundation.text.contextmenu.gestures.RightClickGesturesKt$awaitFirstRightClickDown$1 r1 = (androidx.compose.foundation.text.contextmenu.gestures.RightClickGesturesKt.AnonymousClass1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r2 = r1.label
            int r2 = r2 - r3
            r1.label = r2
            goto L1b
        L16:
            androidx.compose.foundation.text.contextmenu.gestures.RightClickGesturesKt$awaitFirstRightClickDown$1 r1 = new androidx.compose.foundation.text.contextmenu.gestures.RightClickGesturesKt$awaitFirstRightClickDown$1
            r1.<init>(r0)
        L1b:
            java.lang.Object r2 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            r5 = 1
            switch(r4) {
                case 0: goto L3b;
                case 1: goto L30;
                default: goto L27;
            }
        L27:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L30:
            java.lang.Object r4 = r1.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
            kotlin.ResultKt.throwOnFailure(r2)
            r6 = r4
            r4 = r3
            r3 = r2
            goto L54
        L3b:
            kotlin.ResultKt.throwOnFailure(r2)
            r4 = r19
        L40:
            r1.L$0 = r4
            r1.label = r5
            r6 = 0
            java.lang.Object r6 = androidx.compose.ui.input.pointer.AwaitPointerEventScope.awaitPointerEvent$default(r4, r6, r1, r5, r6)
            if (r6 != r3) goto L4d
            return r3
        L4d:
            r18 = r3
            r3 = r2
            r2 = r6
            r6 = r4
            r4 = r18
        L54:
            androidx.compose.ui.input.pointer.PointerEvent r2 = (androidx.compose.ui.input.pointer.PointerEvent) r2
            int r7 = r2.getButtons()
            boolean r7 = androidx.compose.ui.input.pointer.PointerEvent_androidKt.m6385isSecondaryPressedaHzCxE(r7)
            if (r7 == 0) goto L97
            java.util.List r7 = r2.getChanges()
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = r7
            java.util.Collection r11 = (java.util.Collection) r11
            int r11 = r11.size()
        L6f:
            r12 = 0
            if (r10 >= r11) goto L8a
            java.lang.Object r13 = r7.get(r10)
            r14 = r13
            r15 = 0
            r16 = r14
            androidx.compose.ui.input.pointer.PointerInputChange r16 = (androidx.compose.ui.input.pointer.PointerInputChange) r16
            r17 = 0
            boolean r16 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDown(r16)
            if (r16 != 0) goto L86
            r7 = r12
            goto L8c
        L86:
            int r10 = r10 + 1
            goto L6f
        L8a:
            r7 = r5
        L8c:
            if (r7 == 0) goto L97
            java.util.List r4 = r2.getChanges()
            java.lang.Object r4 = r4.get(r12)
            return r4
        L97:
            r2 = r3
            r3 = r4
            r4 = r6
            goto L40
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.contextmenu.gestures.RightClickGesturesKt.awaitFirstRightClickDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
