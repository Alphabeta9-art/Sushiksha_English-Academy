package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEvent_androidKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.platform.ViewConfiguration;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: SelectionGestures.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0002\u001a\u00020\u0003*\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0000\u001a\"\u0010\b\u001a\u00020\u0007*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0080@¢\u0006\u0002\u0010\u000e\u001a\"\u0010\u000f\u001a\u00020\u0007*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0080@¢\u0006\u0002\u0010\u0014\u001a*\u0010\u0015\u001a\u00020\u0007*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0001H\u0082@¢\u0006\u0002\u0010\u0017\u001a*\u0010\u0018\u001a\u00020\u0007*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0013H\u0080@¢\u0006\u0002\u0010\u001c\u001a\u0012\u0010\u001d\u001a\u00020\u0013*\u00020\u0010H\u0082@¢\u0006\u0002\u0010\u001e\u001a \u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"STATIC_KEY", "", "updateSelectionTouchMode", "Landroidx/compose/ui/Modifier;", "updateTouchMode", "Lkotlin/Function1;", "", "", "awaitSelectionGestures", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "mouseSelectionObserver", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "textDragObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/TextDragObserver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "touchSelectionFirstPress", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "observer", "downEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/TextDragObserver;Landroidx/compose/ui/input/pointer/PointerEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "touchSelectionSubsequentPress", "clicks", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/TextDragObserver;Landroidx/compose/ui/input/pointer/PointerEvent;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mouseSelection", "clicksCounter", "Landroidx/compose/foundation/text/selection/ClicksCounter;", "down", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/selection/ClicksCounter;Landroidx/compose/ui/input/pointer/PointerEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitDown", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "distanceIsTolerable", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "change1", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "change2", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SelectionGesturesKt {
    private static final int STATIC_KEY = 8675309;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1, reason: invalid class name */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0}, l = {340}, m = "awaitDown", n = {"$this$awaitDown"}, s = {"L$0"}, v = 1)
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
            return SelectionGesturesKt.awaitDown(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelection$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 1, 1, 1}, l = {267, 294}, m = "mouseSelection", n = {"$this$mouseSelection", "observer", "$this$mouseSelection", "observer", "dragConsumed"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2"}, v = 1)
    static final class C02791 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C02791(Continuation<? super C02791> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.mouseSelection(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionFirstPress$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 0, 1, 1}, l = {141, 145}, m = "touchSelectionFirstPress", n = {"$this$touchSelectionFirstPress", "observer", "firstDown", "$this$touchSelectionFirstPress", "observer"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"}, v = 1)
    static final class C02801 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C02801(Continuation<? super C02801> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.touchSelectionFirstPress(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionSubsequentPress$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 0, 0, 1, 1}, l = {193, 232}, m = "touchSelectionSubsequentPress", n = {"$this$touchSelectionSubsequentPress", "observer", "overSlop", "pointerId", "$this$touchSelectionSubsequentPress", "observer"}, s = {"L$0", "L$1", "L$2", "J$0", "L$0", "L$1"}, v = 1)
    static final class C02811 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C02811(Continuation<? super C02811> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.touchSelectionSubsequentPress(null, null, null, 0, this);
        }
    }

    public static final Modifier updateSelectionTouchMode(Modifier $this$updateSelectionTouchMode, final Function1<? super Boolean, Unit> function1) {
        return SuspendingPointerInputFilterKt.pointerInput($this$updateSelectionTouchMode, Integer.valueOf(STATIC_KEY), new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt.updateSelectionTouchMode.1

            /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: SelectionGestures.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1$1", f = "SelectionGestures.kt", i = {0}, l = {94}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"}, v = 1)
            static final class C00411 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Function1<Boolean, Unit> $updateTouchMode;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                C00411(Function1<? super Boolean, Unit> function1, Continuation<? super C00411> continuation) {
                    super(2, continuation);
                    this.$updateTouchMode = function1;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00411 c00411 = new C00411(this.$updateTouchMode, continuation);
                    c00411.L$0 = obj;
                    return c00411;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return ((C00411) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code duplicated, block: B:10:0x0037 A[RETURN] */
                /* JADX WARN: Code duplicated, block: B:11:0x0038  */
                /* JADX WARN: Code duplicated, block: B:14:0x0048  */
                /* JADX WARN: Code duplicated, block: B:15:0x004a  */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0038 -> B:12:0x003e). Please report as a decompilation issue!!! */
                /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                    jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:15:0x004a
                    	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                    	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                    	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                    */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final java.lang.Object invokeSuspend(java.lang.Object r9) {
                    /*
                        r8 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r8.label
                        r2 = 1
                        switch(r1) {
                            case 0: goto L1f;
                            case 1: goto L13;
                            default: goto La;
                        }
                    La:
                        java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r9.<init>(r0)
                        throw r9
                    L13:
                        java.lang.Object r1 = r8.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                        kotlin.ResultKt.throwOnFailure(r9)
                        r4 = r8
                        r3 = r1
                        r1 = r0
                        r0 = r9
                        goto L3e
                    L1f:
                        kotlin.ResultKt.throwOnFailure(r9)
                        java.lang.Object r1 = r8.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                        r3 = r8
                    L27:
                        androidx.compose.ui.input.pointer.PointerEventPass r4 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                        r5 = r3
                        kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                        r3.L$0 = r1
                        r3.label = r2
                        java.lang.Object r4 = r1.awaitPointerEvent(r4, r5)
                        if (r4 != r0) goto L38
                        return r0
                    L38:
                        r7 = r0
                        r0 = r9
                        r9 = r4
                        r4 = r3
                        r3 = r1
                        r1 = r7
                    L3e:
                        androidx.compose.ui.input.pointer.PointerEvent r9 = (androidx.compose.ui.input.pointer.PointerEvent) r9
                        kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> r5 = r4.$updateTouchMode
                        boolean r6 = androidx.compose.foundation.text.selection.SelectionGestures_androidKt.isMouseOrTouchPad(r9)
                        if (r6 != 0) goto L4a
                        r9 = r2
                        goto L4b
                    L4a:
                        r9 = 0
                    L4b:
                        java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r9)
                        r5.invoke(r9)
                        r9 = r0
                        r0 = r1
                        r1 = r3
                        r3 = r4
                        goto L27
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.C02821.C00411.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                Object objAwaitPointerEventScope = $this$pointerInput.awaitPointerEventScope(new C00411(function1, null), continuation);
                return objAwaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitPointerEventScope : Unit.INSTANCE;
            }
        });
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitSelectionGestures$2, reason: invalid class name */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitSelectionGestures$2", f = "SelectionGestures.kt", i = {0}, l = {111, 119, 122, 124}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"}, v = 1)
    static final class AnonymousClass2 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ClicksCounter $clicksCounter;
        final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
        final /* synthetic */ TextDragObserver $textDragObserver;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ClicksCounter clicksCounter, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$clicksCounter = clicksCounter;
            this.$mouseSelectionObserver = mouseSelectionObserver;
            this.$textDragObserver = textDragObserver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$clicksCounter, this.$mouseSelectionObserver, this.$textDragObserver, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:32:0x00ac  */
        /* JADX WARN: Code duplicated, block: B:33:0x00ae  */
        /* JADX WARN: Code duplicated, block: B:35:0x00b6  */
        /* JADX WARN: Code duplicated, block: B:38:0x00c7  */
        /* JADX WARN: Code duplicated, block: B:40:0x00ca  */
        /* JADX WARN: Code duplicated, block: B:43:0x00e1  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object $result;
            AwaitPointerEventScope $this$awaitEachGesture;
            Object objAwaitDown;
            PointerEvent downEvent;
            boolean isPrecise;
            Object $result2;
            Object $result3;
            boolean z;
            Object $result4;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    $result = obj;
                    $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                    this.L$0 = $this$awaitEachGesture;
                    this.label = 1;
                    objAwaitDown = SelectionGesturesKt.awaitDown($this$awaitEachGesture, this);
                    if (objAwaitDown == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    downEvent = (PointerEvent) objAwaitDown;
                    this.$clicksCounter.update(downEvent);
                    isPrecise = SelectionGestures_androidKt.isMouseOrTouchPad(downEvent);
                    if (!isPrecise && PointerEvent_androidKt.m6383isPrimaryPressedaHzCxE(downEvent.getButtons())) {
                        List<PointerInputChange> changes = downEvent.getChanges();
                        int index$iv$iv = 0;
                        int size = changes.size();
                        while (true) {
                            if (index$iv$iv < size) {
                                Object it$iv = changes.get(index$iv$iv);
                                PointerInputChange it = (PointerInputChange) it$iv;
                                z = false;
                                if (!it.isConsumed()) {
                                    index$iv$iv++;
                                }
                            } else {
                                z = true;
                            }
                        }
                        if (z) {
                            this.L$0 = null;
                            this.label = 2;
                            if (SelectionGesturesKt.mouseSelection($this$awaitEachGesture, this.$mouseSelectionObserver, this.$clicksCounter, downEvent, this) != coroutine_suspended) {
                                $result4 = $result;
                            }
                        } else if (!isPrecise) {
                            if (this.$clicksCounter.getClicks() == 1) {
                                this.L$0 = null;
                                this.label = 3;
                                if (SelectionGesturesKt.touchSelectionFirstPress($this$awaitEachGesture, this.$textDragObserver, downEvent, this) != coroutine_suspended) {
                                    $result3 = $result;
                                }
                            } else {
                                this.L$0 = null;
                                this.label = 4;
                                if (SelectionGesturesKt.touchSelectionSubsequentPress($this$awaitEachGesture, this.$textDragObserver, downEvent, this.$clicksCounter.getClicks(), this) != coroutine_suspended) {
                                    $result2 = $result;
                                }
                            }
                        }
                        return coroutine_suspended;
                    }
                    if (!isPrecise) {
                        if (this.$clicksCounter.getClicks() == 1) {
                            this.L$0 = null;
                            this.label = 3;
                            if (SelectionGesturesKt.touchSelectionFirstPress($this$awaitEachGesture, this.$textDragObserver, downEvent, this) != coroutine_suspended) {
                                $result3 = $result;
                            }
                        } else {
                            this.L$0 = null;
                            this.label = 4;
                            if (SelectionGesturesKt.touchSelectionSubsequentPress($this$awaitEachGesture, this.$textDragObserver, downEvent, this.$clicksCounter.getClicks(), this) != coroutine_suspended) {
                                $result2 = $result;
                            }
                        }
                        return coroutine_suspended;
                    }
                    Object $result5 = Unit.INSTANCE;
                    return $result5;
                case 1:
                    $result = obj;
                    $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    objAwaitDown = $result;
                    downEvent = (PointerEvent) objAwaitDown;
                    this.$clicksCounter.update(downEvent);
                    isPrecise = SelectionGestures_androidKt.isMouseOrTouchPad(downEvent);
                    if (!isPrecise) {
                        if (!isPrecise) {
                            if (this.$clicksCounter.getClicks() == 1) {
                                this.L$0 = null;
                                this.label = 3;
                                if (SelectionGesturesKt.touchSelectionFirstPress($this$awaitEachGesture, this.$textDragObserver, downEvent, this) != coroutine_suspended) {
                                    $result3 = $result;
                                }
                            } else {
                                this.L$0 = null;
                                this.label = 4;
                                if (SelectionGesturesKt.touchSelectionSubsequentPress($this$awaitEachGesture, this.$textDragObserver, downEvent, this.$clicksCounter.getClicks(), this) != coroutine_suspended) {
                                    $result2 = $result;
                                }
                            }
                            return coroutine_suspended;
                        }
                    } else if (!isPrecise) {
                        if (this.$clicksCounter.getClicks() == 1) {
                            this.L$0 = null;
                            this.label = 3;
                            if (SelectionGesturesKt.touchSelectionFirstPress($this$awaitEachGesture, this.$textDragObserver, downEvent, this) != coroutine_suspended) {
                                $result3 = $result;
                            }
                        } else {
                            this.L$0 = null;
                            this.label = 4;
                            if (SelectionGesturesKt.touchSelectionSubsequentPress($this$awaitEachGesture, this.$textDragObserver, downEvent, this.$clicksCounter.getClicks(), this) != coroutine_suspended) {
                                $result2 = $result;
                            }
                        }
                        return coroutine_suspended;
                    }
                    Object $result6 = Unit.INSTANCE;
                    return $result6;
                case 2:
                    $result4 = obj;
                    ResultKt.throwOnFailure($result4);
                    Object $result7 = Unit.INSTANCE;
                    return $result7;
                case 3:
                    $result3 = obj;
                    ResultKt.throwOnFailure($result3);
                    Object $result8 = Unit.INSTANCE;
                    return $result8;
                case 4:
                    $result2 = obj;
                    ResultKt.throwOnFailure($result2);
                    Object $result9 = Unit.INSTANCE;
                    return $result9;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public static final Object awaitSelectionGestures(PointerInputScope $this$awaitSelectionGestures, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        ClicksCounter clicksCounter = new ClicksCounter($this$awaitSelectionGestures.getViewConfiguration());
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$awaitSelectionGestures, new AnonymousClass2(clicksCounter, mouseSelectionObserver, textDragObserver, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00ab A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:34:0x00b5 A[Catch: CancellationException -> 0x00e8, TryCatch #1 {CancellationException -> 0x00e8, blocks: (B:32:0x00ac, B:34:0x00b5, B:36:0x00c8, B:38:0x00d6, B:39:0x00d9, B:40:0x00dd, B:41:0x00e2, B:18:0x004c, B:25:0x0077, B:27:0x007b, B:29:0x0085, B:21:0x0055), top: B:50:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x00c8 A[Catch: CancellationException -> 0x00e8, TryCatch #1 {CancellationException -> 0x00e8, blocks: (B:32:0x00ac, B:34:0x00b5, B:36:0x00c8, B:38:0x00d6, B:39:0x00d9, B:40:0x00dd, B:41:0x00e2, B:18:0x004c, B:25:0x0077, B:27:0x007b, B:29:0x0085, B:21:0x0055), top: B:50:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x00d6 A[Catch: CancellationException -> 0x00e8, TryCatch #1 {CancellationException -> 0x00e8, blocks: (B:32:0x00ac, B:34:0x00b5, B:36:0x00c8, B:38:0x00d6, B:39:0x00d9, B:40:0x00dd, B:41:0x00e2, B:18:0x004c, B:25:0x0077, B:27:0x007b, B:29:0x0085, B:21:0x0055), top: B:50:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x00e2 A[Catch: CancellationException -> 0x00e8, TRY_LEAVE, TryCatch #1 {CancellationException -> 0x00e8, blocks: (B:32:0x00ac, B:34:0x00b5, B:36:0x00c8, B:38:0x00d6, B:39:0x00d9, B:40:0x00dd, B:41:0x00e2, B:18:0x004c, B:25:0x0077, B:27:0x007b, B:29:0x0085, B:21:0x0055), top: B:50:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x00d9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object touchSelectionFirstPress(AwaitPointerEventScope $this$touchSelectionFirstPress, final TextDragObserver observer, PointerEvent downEvent, Continuation<? super Unit> continuation) {
        C02801 c02801;
        CancellationException c;
        TextDragObserver observer2;
        AwaitPointerEventScope $this$touchSelectionFirstPress2;
        PointerInputChange firstDown;
        Object obj;
        PointerInputChange longPress;
        Object objM474dragjO51t88;
        boolean dragCompletedWithUp;
        List<PointerInputChange> changes;
        int index$iv;
        int size;
        PointerInputChange it;
        if (continuation instanceof C02801) {
            c02801 = (C02801) continuation;
            if ((c02801.label & Integer.MIN_VALUE) != 0) {
                c02801.label -= Integer.MIN_VALUE;
            } else {
                c02801 = new C02801(continuation);
            }
        } else {
            c02801 = new C02801(continuation);
        }
        Object $result = c02801.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (c02801.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PointerInputChange firstDown2 = (PointerInputChange) CollectionsKt.first((List) downEvent.getChanges());
                    long id = firstDown2.getId();
                    c02801.L$0 = $this$touchSelectionFirstPress;
                    c02801.L$1 = observer;
                    c02801.L$2 = firstDown2;
                    c02801.label = 1;
                    Object objM464awaitLongPressOrCancellationrnUCldI = DragGestureDetectorKt.m464awaitLongPressOrCancellationrnUCldI($this$touchSelectionFirstPress, id, c02801);
                    if (objM464awaitLongPressOrCancellationrnUCldI == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this$touchSelectionFirstPress2 = $this$touchSelectionFirstPress;
                    firstDown = firstDown2;
                    obj = objM464awaitLongPressOrCancellationrnUCldI;
                    longPress = (PointerInputChange) obj;
                    if (longPress != null && distanceIsTolerable($this$touchSelectionFirstPress2.getViewConfiguration(), firstDown, longPress)) {
                        observer.mo1256onStart3MmeM6k(longPress.getPosition(), SelectionAdjustment.INSTANCE.getWord());
                        long id2 = longPress.getId();
                        Function1 function1 = new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return SelectionGesturesKt.touchSelectionFirstPress$lambda$0(observer, (PointerInputChange) obj2);
                            }
                        };
                        c02801.L$0 = $this$touchSelectionFirstPress2;
                        c02801.L$1 = observer;
                        c02801.L$2 = null;
                        c02801.label = 2;
                        objM474dragjO51t88 = DragGestureDetectorKt.m474dragjO51t88($this$touchSelectionFirstPress2, id2, function1, c02801);
                        if (objM474dragjO51t88 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        dragCompletedWithUp = ((Boolean) objM474dragjO51t88).booleanValue();
                        if (dragCompletedWithUp) {
                            changes = $this$touchSelectionFirstPress2.getCurrentEvent().getChanges();
                            size = changes.size();
                            for (index$iv = 0; index$iv < size; index$iv++) {
                                Object item$iv = changes.get(index$iv);
                                it = (PointerInputChange) item$iv;
                                if (PointerEventKt.changedToUp(it)) {
                                    it.consume();
                                }
                            }
                            observer.onStop();
                        } else {
                            observer.onCancel();
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    firstDown = (PointerInputChange) c02801.L$2;
                    observer = (TextDragObserver) c02801.L$1;
                    $this$touchSelectionFirstPress2 = (AwaitPointerEventScope) c02801.L$0;
                    ResultKt.throwOnFailure($result);
                    obj = $result;
                    longPress = (PointerInputChange) obj;
                    if (longPress != null) {
                        observer.mo1256onStart3MmeM6k(longPress.getPosition(), SelectionAdjustment.INSTANCE.getWord());
                        long id3 = longPress.getId();
                        Function1 function2 = new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return SelectionGesturesKt.touchSelectionFirstPress$lambda$0(observer, (PointerInputChange) obj2);
                            }
                        };
                        c02801.L$0 = $this$touchSelectionFirstPress2;
                        c02801.L$1 = observer;
                        c02801.L$2 = null;
                        c02801.label = 2;
                        objM474dragjO51t88 = DragGestureDetectorKt.m474dragjO51t88($this$touchSelectionFirstPress2, id3, function2, c02801);
                        if (objM474dragjO51t88 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        dragCompletedWithUp = ((Boolean) objM474dragjO51t88).booleanValue();
                        if (dragCompletedWithUp) {
                            changes = $this$touchSelectionFirstPress2.getCurrentEvent().getChanges();
                            size = changes.size();
                            while (index$iv < size) {
                                Object item$iv2 = changes.get(index$iv);
                                it = (PointerInputChange) item$iv2;
                                if (PointerEventKt.changedToUp(it)) {
                                    it.consume();
                                }
                            }
                            observer.onStop();
                        } else {
                            observer.onCancel();
                        }
                    }
                    return Unit.INSTANCE;
                case 2:
                    observer2 = (TextDragObserver) c02801.L$1;
                    AwaitPointerEventScope $this$touchSelectionFirstPress3 = (AwaitPointerEventScope) c02801.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        $this$touchSelectionFirstPress2 = $this$touchSelectionFirstPress3;
                        observer = observer2;
                        objM474dragjO51t88 = $result;
                        dragCompletedWithUp = ((Boolean) objM474dragjO51t88).booleanValue();
                        if (dragCompletedWithUp) {
                            changes = $this$touchSelectionFirstPress2.getCurrentEvent().getChanges();
                            size = changes.size();
                            while (index$iv < size) {
                                Object item$iv3 = changes.get(index$iv);
                                it = (PointerInputChange) item$iv3;
                                if (PointerEventKt.changedToUp(it)) {
                                    it.consume();
                                }
                            }
                            observer.onStop();
                        } else {
                            observer.onCancel();
                        }
                        return Unit.INSTANCE;
                    } catch (CancellationException e) {
                        c = e;
                        observer2.onCancel();
                        throw c;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (CancellationException e2) {
            c = e2;
            observer2 = observer;
        }
    }

    static final Unit touchSelectionFirstPress$lambda$0(TextDragObserver $observer, PointerInputChange it) {
        $observer.mo1255onDragk4lQ0M(PointerEventKt.positionChange(it));
        it.consume();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:29:0x00b0 A[Catch: CancellationException -> 0x0126, TryCatch #2 {CancellationException -> 0x0126, blocks: (B:16:0x004b, B:27:0x00ac, B:29:0x00b0, B:30:0x00b2, B:32:0x00b8, B:34:0x00be, B:36:0x00c2, B:38:0x00c8, B:40:0x00cc, B:41:0x00d1), top: B:68:0x004b }] */
    /* JADX WARN: Code duplicated, block: B:32:0x00b8 A[Catch: CancellationException -> 0x0126, TryCatch #2 {CancellationException -> 0x0126, blocks: (B:16:0x004b, B:27:0x00ac, B:29:0x00b0, B:30:0x00b2, B:32:0x00b8, B:34:0x00be, B:36:0x00c2, B:38:0x00c8, B:40:0x00cc, B:41:0x00d1), top: B:68:0x004b }] */
    /* JADX WARN: Code duplicated, block: B:34:0x00be A[Catch: CancellationException -> 0x0126, TryCatch #2 {CancellationException -> 0x0126, blocks: (B:16:0x004b, B:27:0x00ac, B:29:0x00b0, B:30:0x00b2, B:32:0x00b8, B:34:0x00be, B:36:0x00c2, B:38:0x00c8, B:40:0x00cc, B:41:0x00d1), top: B:68:0x004b }] */
    /* JADX WARN: Code duplicated, block: B:36:0x00c2 A[Catch: CancellationException -> 0x0126, TryCatch #2 {CancellationException -> 0x0126, blocks: (B:16:0x004b, B:27:0x00ac, B:29:0x00b0, B:30:0x00b2, B:32:0x00b8, B:34:0x00be, B:36:0x00c2, B:38:0x00c8, B:40:0x00cc, B:41:0x00d1), top: B:68:0x004b }] */
    /* JADX WARN: Code duplicated, block: B:38:0x00c8 A[Catch: CancellationException -> 0x0126, TryCatch #2 {CancellationException -> 0x0126, blocks: (B:16:0x004b, B:27:0x00ac, B:29:0x00b0, B:30:0x00b2, B:32:0x00b8, B:34:0x00be, B:36:0x00c2, B:38:0x00c8, B:40:0x00cc, B:41:0x00d1), top: B:68:0x004b }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00cc A[Catch: CancellationException -> 0x0126, TryCatch #2 {CancellationException -> 0x0126, blocks: (B:16:0x004b, B:27:0x00ac, B:29:0x00b0, B:30:0x00b2, B:32:0x00b8, B:34:0x00be, B:36:0x00c2, B:38:0x00c8, B:40:0x00cc, B:41:0x00d1), top: B:68:0x004b }] */
    /* JADX WARN: Code duplicated, block: B:43:0x00e4 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:44:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:47:0x00f0 A[Catch: CancellationException -> 0x0124, TryCatch #1 {CancellationException -> 0x0124, blocks: (B:13:0x0037, B:45:0x00e7, B:47:0x00f0, B:49:0x0103, B:51:0x0111, B:52:0x0114, B:53:0x0119, B:54:0x011e), top: B:67:0x0037 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x0103 A[Catch: CancellationException -> 0x0124, TryCatch #1 {CancellationException -> 0x0124, blocks: (B:13:0x0037, B:45:0x00e7, B:47:0x00f0, B:49:0x0103, B:51:0x0111, B:52:0x0114, B:53:0x0119, B:54:0x011e), top: B:67:0x0037 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x0111 A[Catch: CancellationException -> 0x0124, TryCatch #1 {CancellationException -> 0x0124, blocks: (B:13:0x0037, B:45:0x00e7, B:47:0x00f0, B:49:0x0103, B:51:0x0111, B:52:0x0114, B:53:0x0119, B:54:0x011e), top: B:67:0x0037 }] */
    /* JADX WARN: Code duplicated, block: B:54:0x011e A[Catch: CancellationException -> 0x0124, TRY_LEAVE, TryCatch #1 {CancellationException -> 0x0124, blocks: (B:13:0x0037, B:45:0x00e7, B:47:0x00f0, B:49:0x0103, B:51:0x0111, B:52:0x0114, B:53:0x0119, B:54:0x011e), top: B:67:0x0037 }] */
    /* JADX WARN: Code duplicated, block: B:71:0x0114 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object touchSelectionSubsequentPress(AwaitPointerEventScope $this$touchSelectionSubsequentPress, TextDragObserver observer, PointerEvent downEvent, int clicks, Continuation<? super Unit> continuation) {
        C02811 c02811;
        CancellationException c;
        TextDragObserver observer2;
        Ref.LongRef overSlop;
        AwaitPointerEventScope $this$touchSelectionSubsequentPress2;
        final TextDragObserver observer3;
        long pointerId;
        Object obj;
        DownResolution downResolution;
        Object objM474dragjO51t88;
        AwaitPointerEventScope $this$touchSelectionSubsequentPress3;
        boolean dragCompletedWithUp;
        List<PointerInputChange> changes;
        int index$iv;
        int size;
        PointerInputChange it;
        if (continuation instanceof C02811) {
            c02811 = (C02811) continuation;
            if ((c02811.label & Integer.MIN_VALUE) != 0) {
                c02811.label -= Integer.MIN_VALUE;
            } else {
                c02811 = new C02811(continuation);
            }
        } else {
            c02811 = new C02811(continuation);
        }
        Object $result = c02811.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02811.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    PointerInputChange firstDown = (PointerInputChange) CollectionsKt.first((List) downEvent.getChanges());
                    long pointerId2 = firstDown.getId();
                    observer.mo1256onStart3MmeM6k(firstDown.getPosition(), clicks > 2 ? SelectionAdjustment.INSTANCE.getParagraph() : SelectionAdjustment.INSTANCE.getWord());
                    overSlop = new Ref.LongRef();
                    overSlop.element = Offset.INSTANCE.m4867getUnspecifiedF1C5BW0();
                    long longPressTimeoutMillis = $this$touchSelectionSubsequentPress.getViewConfiguration().getLongPressTimeoutMillis();
                    SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1 selectionGesturesKt$touchSelectionSubsequentPress$downResolution$1 = new SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1(pointerId2, overSlop, null);
                    c02811.L$0 = $this$touchSelectionSubsequentPress;
                    c02811.L$1 = observer;
                    c02811.L$2 = overSlop;
                    c02811.J$0 = pointerId2;
                    c02811.label = 1;
                    Object objWithTimeoutOrNull = $this$touchSelectionSubsequentPress.withTimeoutOrNull(longPressTimeoutMillis, selectionGesturesKt$touchSelectionSubsequentPress$downResolution$1, c02811);
                    if (objWithTimeoutOrNull == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this$touchSelectionSubsequentPress2 = $this$touchSelectionSubsequentPress;
                    observer3 = observer;
                    pointerId = pointerId2;
                    obj = objWithTimeoutOrNull;
                    downResolution = (DownResolution) obj;
                    if (downResolution == null) {
                        downResolution = DownResolution.Timeout;
                    }
                    if (downResolution == DownResolution.Cancel) {
                        observer3.onCancel();
                        return Unit.INSTANCE;
                    }
                    if (downResolution == DownResolution.Up) {
                        observer3.onStop();
                        return Unit.INSTANCE;
                    }
                    if (downResolution == DownResolution.Drag) {
                        observer3.mo1255onDragk4lQ0M(overSlop.element);
                    }
                    Function1 function1 = new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return SelectionGesturesKt.touchSelectionSubsequentPress$lambda$0(observer3, (PointerInputChange) obj2);
                        }
                    };
                    c02811.L$0 = $this$touchSelectionSubsequentPress2;
                    c02811.L$1 = observer3;
                    c02811.L$2 = null;
                    c02811.label = 2;
                    objM474dragjO51t88 = DragGestureDetectorKt.m474dragjO51t88($this$touchSelectionSubsequentPress2, pointerId, function1, c02811);
                    if (objM474dragjO51t88 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    observer2 = observer3;
                    $this$touchSelectionSubsequentPress3 = $this$touchSelectionSubsequentPress2;
                    dragCompletedWithUp = ((Boolean) objM474dragjO51t88).booleanValue();
                    if (dragCompletedWithUp) {
                        changes = $this$touchSelectionSubsequentPress3.getCurrentEvent().getChanges();
                        size = changes.size();
                        for (index$iv = 0; index$iv < size; index$iv++) {
                            Object item$iv = changes.get(index$iv);
                            it = (PointerInputChange) item$iv;
                            if (PointerEventKt.changedToUp(it)) {
                                it.consume();
                            }
                        }
                        observer2.onStop();
                    } else {
                        observer2.onCancel();
                    }
                    return Unit.INSTANCE;
                } catch (CancellationException e) {
                    c = e;
                    observer2 = observer;
                    observer2.onCancel();
                    throw c;
                }
            case 1:
                pointerId = c02811.J$0;
                overSlop = (Ref.LongRef) c02811.L$2;
                observer3 = (TextDragObserver) c02811.L$1;
                $this$touchSelectionSubsequentPress2 = (AwaitPointerEventScope) c02811.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    obj = $result;
                    downResolution = (DownResolution) obj;
                    if (downResolution == null) {
                        downResolution = DownResolution.Timeout;
                    }
                    if (downResolution == DownResolution.Cancel) {
                        observer3.onCancel();
                        return Unit.INSTANCE;
                    }
                    if (downResolution == DownResolution.Up) {
                        observer3.onStop();
                        return Unit.INSTANCE;
                    }
                    if (downResolution == DownResolution.Drag) {
                        observer3.mo1255onDragk4lQ0M(overSlop.element);
                    }
                    Function1 function2 = new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return SelectionGesturesKt.touchSelectionSubsequentPress$lambda$0(observer3, (PointerInputChange) obj2);
                        }
                    };
                    c02811.L$0 = $this$touchSelectionSubsequentPress2;
                    c02811.L$1 = observer3;
                    c02811.L$2 = null;
                    c02811.label = 2;
                    objM474dragjO51t88 = DragGestureDetectorKt.m474dragjO51t88($this$touchSelectionSubsequentPress2, pointerId, function2, c02811);
                    if (objM474dragjO51t88 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    observer2 = observer3;
                    $this$touchSelectionSubsequentPress3 = $this$touchSelectionSubsequentPress2;
                    dragCompletedWithUp = ((Boolean) objM474dragjO51t88).booleanValue();
                    if (dragCompletedWithUp) {
                        changes = $this$touchSelectionSubsequentPress3.getCurrentEvent().getChanges();
                        size = changes.size();
                        while (index$iv < size) {
                            Object item$iv2 = changes.get(index$iv);
                            it = (PointerInputChange) item$iv2;
                            if (PointerEventKt.changedToUp(it)) {
                                it.consume();
                            }
                        }
                        observer2.onStop();
                    } else {
                        observer2.onCancel();
                    }
                    return Unit.INSTANCE;
                } catch (CancellationException e2) {
                    c = e2;
                    observer2 = observer3;
                    observer2.onCancel();
                    throw c;
                }
            case 2:
                observer2 = (TextDragObserver) c02811.L$1;
                $this$touchSelectionSubsequentPress3 = (AwaitPointerEventScope) c02811.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    objM474dragjO51t88 = $result;
                    dragCompletedWithUp = ((Boolean) objM474dragjO51t88).booleanValue();
                    if (dragCompletedWithUp) {
                        changes = $this$touchSelectionSubsequentPress3.getCurrentEvent().getChanges();
                        size = changes.size();
                        while (index$iv < size) {
                            Object item$iv3 = changes.get(index$iv);
                            it = (PointerInputChange) item$iv3;
                            if (PointerEventKt.changedToUp(it)) {
                                it.consume();
                            }
                        }
                        observer2.onStop();
                    } else {
                        observer2.onCancel();
                    }
                    return Unit.INSTANCE;
                } catch (CancellationException e3) {
                    c = e3;
                    observer2.onCancel();
                    throw c;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    static final Unit touchSelectionSubsequentPress$lambda$0(TextDragObserver $observer, PointerInputChange it) {
        $observer.mo1255onDragk4lQ0M(PointerEventKt.positionChange(it));
        it.consume();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0095 A[Catch: all -> 0x00c3, TryCatch #0 {all -> 0x00c3, blocks: (B:16:0x0047, B:26:0x008c, B:28:0x0095, B:30:0x00a8, B:32:0x00b6), top: B:73:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:30:0x00a8 A[Catch: all -> 0x00c3, TryCatch #0 {all -> 0x00c3, blocks: (B:16:0x0047, B:26:0x008c, B:28:0x0095, B:30:0x00a8, B:32:0x00b6), top: B:73:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x00b6 A[Catch: all -> 0x00c3, TRY_LEAVE, TryCatch #0 {all -> 0x00c3, blocks: (B:16:0x0047, B:26:0x008c, B:28:0x0095, B:30:0x00a8, B:32:0x00b6), top: B:73:0x0047 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x014a A[Catch: all -> 0x0164, TryCatch #1 {all -> 0x0164, blocks: (B:13:0x0039, B:56:0x012a, B:58:0x0133, B:60:0x0137, B:62:0x014a, B:64:0x0158, B:49:0x00fa, B:52:0x010c), top: B:75:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:64:0x0158 A[Catch: all -> 0x0164, TRY_LEAVE, TryCatch #1 {all -> 0x0164, blocks: (B:13:0x0039, B:56:0x012a, B:58:0x0133, B:60:0x0137, B:62:0x014a, B:64:0x0158, B:49:0x00fa, B:52:0x010c), top: B:75:0x0021 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code duplicated, block: B:81:0x015b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x00b9 A[SYNTHETIC] */
    public static final Object mouseSelection(AwaitPointerEventScope $this$mouseSelection, final MouseSelectionObserver observer, ClicksCounter clicksCounter, PointerEvent down, Continuation<? super Unit> continuation) throws Throwable {
        C02791 c02791;
        final SelectionAdjustment selectionAdjustment;
        Object objM474dragjO51t88;
        AwaitPointerEventScope $this$mouseSelection2;
        Ref.BooleanRef dragConsumed;
        Throwable th;
        MouseSelectionObserver observer2;
        Object objM474dragjO51t89;
        AwaitPointerEventScope $this$mouseSelection3;
        boolean shouldConsumeUp;
        List<PointerInputChange> changes;
        int index$iv;
        int size;
        PointerInputChange it;
        boolean shouldConsumeUp2;
        List<PointerInputChange> changes2;
        int index$iv2;
        int size2;
        PointerInputChange it2;
        if (continuation instanceof C02791) {
            c02791 = (C02791) continuation;
            if ((c02791.label & Integer.MIN_VALUE) != 0) {
                c02791.label -= Integer.MIN_VALUE;
            } else {
                c02791 = new C02791(continuation);
            }
        } else {
            c02791 = new C02791(continuation);
        }
        Object $result = c02791.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (c02791.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PointerInputChange downChange = down.getChanges().get(0);
                    if (PointerEvent_androidKt.m6386isShiftPressed5xRPYO0(down.getKeyboardModifiers())) {
                        boolean started = observer.mo1515onExtendk4lQ0M(downChange.getPosition());
                        if (started) {
                            try {
                                downChange.consume();
                                long id = downChange.getId();
                                Function1 function1 = new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$$ExternalSyntheticLambda0
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return SelectionGesturesKt.mouseSelection$lambda$0(observer, (PointerInputChange) obj);
                                    }
                                };
                                c02791.L$0 = $this$mouseSelection;
                                c02791.L$1 = observer;
                                c02791.label = 1;
                                objM474dragjO51t89 = DragGestureDetectorKt.m474dragjO51t88($this$mouseSelection, id, function1, c02791);
                                if (objM474dragjO51t89 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $this$mouseSelection3 = $this$mouseSelection;
                                observer2 = observer;
                                shouldConsumeUp = ((Boolean) objM474dragjO51t89).booleanValue();
                                if (shouldConsumeUp) {
                                    changes = $this$mouseSelection3.getCurrentEvent().getChanges();
                                    size = changes.size();
                                    for (index$iv = 0; index$iv < size; index$iv++) {
                                        Object item$iv = changes.get(index$iv);
                                        it = (PointerInputChange) item$iv;
                                        if (PointerEventKt.changedToUp(it)) {
                                            it.consume();
                                        }
                                    }
                                }
                                observer2.onDragDone();
                            } catch (Throwable th2) {
                                th = th2;
                                observer2 = observer;
                                observer2.onDragDone();
                                throw th;
                            }
                        }
                    } else {
                        switch (clicksCounter.getClicks()) {
                            case 1:
                                selectionAdjustment = SelectionAdjustment.INSTANCE.getNone();
                                break;
                            case 2:
                                selectionAdjustment = SelectionAdjustment.INSTANCE.getWord();
                                break;
                            default:
                                selectionAdjustment = SelectionAdjustment.INSTANCE.getParagraph();
                                break;
                        }
                        boolean started2 = observer.mo1517onStart9KIMszo(downChange.getPosition(), selectionAdjustment, clicksCounter.getClicks());
                        if (started2) {
                            final Ref.BooleanRef dragConsumed2 = new Ref.BooleanRef();
                            dragConsumed2.element = Intrinsics.areEqual(selectionAdjustment, SelectionAdjustment.INSTANCE.getNone()) ? false : true;
                            long id2 = downChange.getId();
                            Function1 function2 = new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return SelectionGesturesKt.mouseSelection$lambda$2(observer, selectionAdjustment, dragConsumed2, (PointerInputChange) obj);
                                }
                            };
                            c02791.L$0 = $this$mouseSelection;
                            c02791.L$1 = observer;
                            c02791.L$2 = dragConsumed2;
                            c02791.label = 2;
                            objM474dragjO51t88 = DragGestureDetectorKt.m474dragjO51t88($this$mouseSelection, id2, function2, c02791);
                            if (objM474dragjO51t88 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $this$mouseSelection2 = $this$mouseSelection;
                            dragConsumed = dragConsumed2;
                            shouldConsumeUp2 = ((Boolean) objM474dragjO51t88).booleanValue();
                            if (shouldConsumeUp2 && dragConsumed.element) {
                                changes2 = $this$mouseSelection2.getCurrentEvent().getChanges();
                                size2 = changes2.size();
                                for (index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                                    Object item$iv2 = changes2.get(index$iv2);
                                    it2 = (PointerInputChange) item$iv2;
                                    if (PointerEventKt.changedToUp(it2)) {
                                        it2.consume();
                                    }
                                }
                            }
                            observer.onDragDone();
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    observer2 = (MouseSelectionObserver) c02791.L$1;
                    $this$mouseSelection3 = (AwaitPointerEventScope) c02791.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        objM474dragjO51t89 = $result;
                        shouldConsumeUp = ((Boolean) objM474dragjO51t89).booleanValue();
                        if (shouldConsumeUp) {
                            changes = $this$mouseSelection3.getCurrentEvent().getChanges();
                            size = changes.size();
                            while (index$iv < size) {
                                Object item$iv3 = changes.get(index$iv);
                                it = (PointerInputChange) item$iv3;
                                if (PointerEventKt.changedToUp(it)) {
                                    it.consume();
                                }
                            }
                        }
                        observer2.onDragDone();
                        return Unit.INSTANCE;
                    } catch (Throwable th3) {
                        th = th3;
                        observer2.onDragDone();
                        throw th;
                    }
                case 2:
                    dragConsumed = (Ref.BooleanRef) c02791.L$2;
                    observer = (MouseSelectionObserver) c02791.L$1;
                    $this$mouseSelection2 = (AwaitPointerEventScope) c02791.L$0;
                    ResultKt.throwOnFailure($result);
                    objM474dragjO51t88 = $result;
                    shouldConsumeUp2 = ((Boolean) objM474dragjO51t88).booleanValue();
                    if (shouldConsumeUp2) {
                        changes2 = $this$mouseSelection2.getCurrentEvent().getChanges();
                        size2 = changes2.size();
                        while (index$iv2 < size2) {
                            Object item$iv4 = changes2.get(index$iv2);
                            it2 = (PointerInputChange) item$iv4;
                            if (PointerEventKt.changedToUp(it2)) {
                                it2.consume();
                            }
                        }
                    }
                    observer.onDragDone();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th4) {
            observer.onDragDone();
            throw th4;
        }
    }

    static final Unit mouseSelection$lambda$0(MouseSelectionObserver $observer, PointerInputChange it) {
        if ($observer.mo1516onExtendDragk4lQ0M(it.getPosition())) {
            it.consume();
        }
        return Unit.INSTANCE;
    }

    static final Unit mouseSelection$lambda$2(MouseSelectionObserver $observer, SelectionAdjustment $selectionAdjustment, Ref.BooleanRef $dragConsumed, PointerInputChange it) {
        if ($observer.mo1514onDrag3MmeM6k(it.getPosition(), $selectionAdjustment)) {
            it.consume();
            $dragConsumed.element = true;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:20:0x0068  */
    /* JADX WARN: Code duplicated, block: B:23:0x007b A[LOOP:0: B:19:0x0066->B:23:0x007b, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:29:0x0079 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x004e -> B:18:0x0055). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object awaitDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r18, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerEvent> r19) {
        /*
            r0 = r19
            boolean r1 = r0 instanceof androidx.compose.foundation.text.selection.SelectionGesturesKt.AnonymousClass1
            if (r1 == 0) goto L16
            r1 = r0
            androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1 r1 = (androidx.compose.foundation.text.selection.SelectionGesturesKt.AnonymousClass1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r2 = r1.label
            int r2 = r2 - r3
            r1.label = r2
            goto L1b
        L16:
            androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1 r1 = new androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1
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
            goto L55
        L3b:
            kotlin.ResultKt.throwOnFailure(r2)
            r4 = r18
        L41:
            androidx.compose.ui.input.pointer.PointerEventPass r6 = androidx.compose.ui.input.pointer.PointerEventPass.Main
            r1.L$0 = r4
            r1.label = r5
            java.lang.Object r6 = r4.awaitPointerEvent(r6, r1)
            if (r6 != r3) goto L4e
            return r3
        L4e:
            r17 = r3
            r3 = r2
            r2 = r6
            r6 = r4
            r4 = r17
        L55:
            androidx.compose.ui.input.pointer.PointerEvent r2 = (androidx.compose.ui.input.pointer.PointerEvent) r2
            java.util.List r7 = r2.getChanges()
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = r7
            java.util.Collection r11 = (java.util.Collection) r11
            int r11 = r11.size()
        L66:
            if (r10 >= r11) goto L7f
            java.lang.Object r12 = r7.get(r10)
            r13 = r12
            r14 = 0
            r15 = r13
            androidx.compose.ui.input.pointer.PointerInputChange r15 = (androidx.compose.ui.input.pointer.PointerInputChange) r15
            r16 = 0
            boolean r15 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDown(r15)
            if (r15 != 0) goto L7b
            r11 = 0
            goto L81
        L7b:
            int r10 = r10 + 1
            goto L66
        L7f:
            r11 = r5
        L81:
            if (r11 == 0) goto L84
            return r2
        L84:
            r2 = r3
            r3 = r4
            r4 = r6
            goto L41
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.awaitDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean distanceIsTolerable(ViewConfiguration viewConfiguration, PointerInputChange change1, PointerInputChange change2) {
        float slop = DragGestureDetectorKt.m477pointerSlopE8SPZFQ(viewConfiguration, change1.getType());
        return Offset.m4850getDistanceimpl(Offset.m4856minusMKHz9U(change1.getPosition(), change2.getPosition())) < slop;
    }
}
