package androidx.compose.foundation.gestures;

import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException;
import androidx.compose.ui.input.pointer.PointerEvent_androidKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.core.location.LocationRequestCompat;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.facebook.imagepipeline.common.RotationOptions;
import com.google.logging.type.LogSeverity;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: TapGestureDetector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000|\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u008b\u0001\u0010\t\u001a\u00020\u0005*\u00020\n2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f2\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f2/\b\u0002\u0010\u000e\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0001¢\u0006\u0002\b\u00072\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0005\u0018\u00010\fH\u0086@¢\u0006\u0002\u0010\u0010\u001a\u0012\u0010\u0011\u001a\u00020\u0005*\u00020\u0012H\u0082@¢\u0006\u0002\u0010\u0013\u001a\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0015H\u0082@¢\u0006\u0002\u0010\u0017\u001a[\u0010\u0018\u001a\u00020\u0005*\u00020\n2/\b\u0002\u0010\u000e\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0001¢\u0006\u0002\b\u00072\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0005\u0018\u00010\fH\u0080@¢\u0006\u0002\u0010\u0019\u001a\u001c\u0010\u001a\u001a\u00020\u0015*\u00020\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0087@¢\u0006\u0002\u0010\u001d\u001a&\u0010\u001a\u001a\u00020\u0015*\u00020\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0086@¢\u0006\u0002\u0010 \u001a&\u0010!\u001a\u00020\u0015*\u00020\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0080@¢\u0006\u0002\u0010 \u001a\u001e\u0010\"\u001a\u00020\u001c*\u00020#2\u0006\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010$\u001a\u00020\u001cH\u0000\u001a\u0014\u0010%\u001a\u0004\u0018\u00010\u0015*\u00020\u0012H\u0087@¢\u0006\u0002\u0010\u0013\u001a\u001e\u0010%\u001a\u0004\u0018\u00010\u0015*\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0086@¢\u0006\u0002\u0010&\u001a\u001c\u0010'\u001a\u00020(*\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0080@¢\u0006\u0002\u0010&\u001aL\u00106\u001a\u000207*\u0002082\u0006\u00109\u001a\u0002072\b\b\u0002\u0010:\u001a\u0002022'\u0010;\u001a#\b\u0001\u0012\u0004\u0012\u000208\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060<¢\u0006\u0002\b\u0007H\u0002¢\u0006\u0002\u0010=\"7\u0010\u0000\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0001¢\u0006\u0002\b\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"*\u0010*\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020\u001c8F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100\"\u001a\u00101\u001a\u0002028BX\u0082\u0004¢\u0006\f\u0012\u0004\b3\u0010,\u001a\u0004\b4\u00105¨\u0006>"}, d2 = {"NoPressGesture", "Lkotlin/Function3;", "Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function3;", "detectTapGestures", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "onDoubleTap", "Lkotlin/Function1;", "onLongPress", "onPress", "onTap", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumeUntilUp", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitSecondDown", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "firstUp", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/ui/input/pointer/PointerInputChange;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectTapAndPress", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitFirstDown", "requireUnconsumed", "", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;ZLandroidx/compose/ui/input/pointer/PointerEventPass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitPrimaryFirstDown", "isChangedToDown", "Landroidx/compose/ui/input/pointer/PointerEvent;", "onlyPrimaryMouseButton", "waitForUpOrCancellation", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/ui/input/pointer/PointerEventPass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "waitForLongPress", "Landroidx/compose/foundation/gestures/LongPressResult;", "value", "DetectTapGesturesEnableNewDispatchingBehavior", "getDetectTapGesturesEnableNewDispatchingBehavior$annotations", "()V", "getDetectTapGesturesEnableNewDispatchingBehavior", "()Z", "setDetectTapGesturesEnableNewDispatchingBehavior", "(Z)V", "coroutineStartForCurrentDispatchBehavior", "Lkotlinx/coroutines/CoroutineStart;", "getCoroutineStartForCurrentDispatchBehavior$annotations", "getCoroutineStartForCurrentDispatchBehavior", "()Lkotlinx/coroutines/CoroutineStart;", "launchAwaitingReset", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;", "resetJob", TtmlNode.START, "block", "Lkotlin/Function2;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/Job;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TapGestureDetectorKt {
    private static final Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> NoPressGesture = new TapGestureDetectorKt$NoPressGesture$1(null);

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2, reason: invalid class name */
    /* JADX INFO: compiled from: TapGestureDetector.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", i = {0, 0, 0}, l = {291}, m = "awaitFirstDown", n = {"$this$awaitFirstDown", "pass", "requireUnconsumed"}, s = {"L$0", "L$1", "Z$0"}, v = 1)
    static final class AnonymousClass2 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TapGestureDetectorKt.awaitFirstDown(null, false, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitPrimaryFirstDown$1, reason: invalid class name */
    /* JADX INFO: compiled from: TapGestureDetector.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", i = {0, 0, 0}, l = {304}, m = "awaitPrimaryFirstDown", n = {"$this$awaitPrimaryFirstDown", "pass", "requireUnconsumed"}, s = {"L$0", "L$1", "Z$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TapGestureDetectorKt.awaitPrimaryFirstDown(null, false, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$consumeUntilUp$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TapGestureDetector.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", i = {0}, l = {209}, m = "consumeUntilUp", n = {"$this$consumeUntilUp"}, s = {"L$0"}, v = 1)
    static final class C02131 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C02131(Continuation<? super C02131> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TapGestureDetectorKt.consumeUntilUp(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForLongPress$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TapGestureDetector.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", i = {0}, l = {384}, m = "waitForLongPress", n = {"result"}, s = {"L$0"}, v = 1)
    static final class C02171 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C02171(Continuation<? super C02171> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TapGestureDetectorKt.waitForLongPress(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForUpOrCancellation$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TapGestureDetector.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", i = {0, 0, 1, 1}, l = {352, 366}, m = "waitForUpOrCancellation", n = {"$this$waitForUpOrCancellation", "pass", "$this$waitForUpOrCancellation", "pass"}, s = {"L$0", "L$1", "L$0", "L$1"}, v = 1)
    static final class C02192 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C02192(Continuation<? super C02192> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TapGestureDetectorKt.waitForUpOrCancellation(null, null, this);
        }
    }

    private static /* synthetic */ void getCoroutineStartForCurrentDispatchBehavior$annotations() {
    }

    @Deprecated(message = "This flag has been moved to ComposeFoundationFlags and renamed to isDetectTapGesturesImmediateCoroutineDispatchEnabled. For compatibility,  DetectTapGesturesEnableNewDispatchingBehavior controls the new flag (isDetectTapGesturesImmediateCoroutineDispatchEnabled). Please use  isDetectTapGesturesImmediateCoroutineDispatchEnabled instead.", replaceWith = @ReplaceWith(expression = "isDetectTapGesturesImmediateCoroutineDispatchEnabled", imports = {"androidx.compose.foundation.ComposeFoundationFlags.isDetectTapGesturesImmediateCoroutineDispatchEnabled"}))
    public static /* synthetic */ void getDetectTapGesturesEnableNewDispatchingBehavior$annotations() {
    }

    public static /* synthetic */ Object detectTapGestures$default(PointerInputScope pointerInputScope, Function1 function1, Function1 function2, Function3 function3, Function1 function4, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = null;
        }
        if ((i & 2) != 0) {
            function2 = null;
        }
        if ((i & 4) != 0) {
            function3 = NoPressGesture;
        }
        if ((i & 8) != 0) {
            function4 = null;
        }
        return detectTapGestures(pointerInputScope, function1, function2, function3, function4, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TapGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2", f = "TapGestureDetector.kt", i = {}, l = {LocationRequestCompat.QUALITY_LOW_POWER}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C02152 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Offset, Unit> $onDoubleTap;
        final /* synthetic */ Function1<Offset, Unit> $onLongPress;
        final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
        final /* synthetic */ Function1<Offset, Unit> $onTap;
        final /* synthetic */ PointerInputScope $this_detectTapGestures;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C02152(PointerInputScope pointerInputScope, Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function1, Function1<? super Offset, Unit> function2, Function1<? super Offset, Unit> function4, Continuation<? super C02152> continuation) {
            super(2, continuation);
            this.$this_detectTapGestures = pointerInputScope;
            this.$onPress = function3;
            this.$onLongPress = function1;
            this.$onDoubleTap = function2;
            this.$onTap = function4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02152 c02152 = new C02152(this.$this_detectTapGestures, this.$onPress, this.$onLongPress, this.$onDoubleTap, this.$onTap, continuation);
            c02152.L$0 = obj;
            return c02152;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02152) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    PressGestureScopeImpl pressScope = new PressGestureScopeImpl(this.$this_detectTapGestures);
                    this.label = 1;
                    if (ForEachGestureKt.awaitEachGesture(this.$this_detectTapGestures, new AnonymousClass1($this$coroutineScope, this.$onPress, this.$onLongPress, this.$onDoubleTap, this.$onTap, pressScope, null), this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: TapGestureDetector.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1", f = "TapGestureDetector.kt", i = {0, 1, 1, 2, 2, 2, 3, 4, 4, 4, 5, 5, 6, 6, 6, 6, 7}, l = {105, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, 119, 122, 149, 167, 169, RotationOptions.ROTATE_180}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "resetJob", "$this$awaitEachGesture", "down", "resetJob", "resetJob", "$this$awaitEachGesture", "upOrCancel", "cancelOrReleaseJob", "resetJob", "upOrCancel", "$this$awaitEachGesture", "resetJob", "upOrCancel", "secondDown", "resetJob"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$0"}, v = 1)
        static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ CoroutineScope $$this$coroutineScope;
            final /* synthetic */ Function1<Offset, Unit> $onDoubleTap;
            final /* synthetic */ Function1<Offset, Unit> $onLongPress;
            final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
            final /* synthetic */ Function1<Offset, Unit> $onTap;
            final /* synthetic */ PressGestureScopeImpl $pressScope;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(CoroutineScope coroutineScope, Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function1, Function1<? super Offset, Unit> function2, Function1<? super Offset, Unit> function4, PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$$this$coroutineScope = coroutineScope;
                this.$onPress = function3;
                this.$onLongPress = function1;
                this.$onDoubleTap = function2;
                this.$onTap = function4;
                this.$pressScope = pressGestureScopeImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$coroutineScope, this.$onPress, this.$onLongPress, this.$onDoubleTap, this.$onTap, this.$pressScope, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:101:0x02ef  */
            /* JADX WARN: Code duplicated, block: B:105:0x02f8  */
            /* JADX WARN: Code duplicated, block: B:20:0x00d4  */
            /* JADX WARN: Code duplicated, block: B:23:0x00ee  */
            /* JADX WARN: Code duplicated, block: B:25:0x00fe A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:26:0x00ff  */
            /* JADX WARN: Code duplicated, block: B:28:0x0104  */
            /* JADX WARN: Code duplicated, block: B:30:0x0116 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:33:0x0121  */
            /* JADX WARN: Code duplicated, block: B:35:0x0140 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:36:0x0141  */
            /* JADX WARN: Code duplicated, block: B:39:0x0158  */
            /* JADX WARN: Code duplicated, block: B:41:0x015c  */
            /* JADX WARN: Code duplicated, block: B:42:0x0164  */
            /* JADX WARN: Code duplicated, block: B:44:0x0168  */
            /* JADX WARN: Code duplicated, block: B:47:0x016e  */
            /* JADX WARN: Code duplicated, block: B:48:0x0183  */
            /* JADX WARN: Code duplicated, block: B:50:0x019b  */
            /* JADX WARN: Code duplicated, block: B:52:0x019f  */
            /* JADX WARN: Code duplicated, block: B:54:0x01a3  */
            /* JADX WARN: Code duplicated, block: B:56:0x01b2  */
            /* JADX WARN: Code duplicated, block: B:58:0x01c4 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:59:0x01c5  */
            /* JADX WARN: Code duplicated, block: B:62:0x01cd  */
            /* JADX WARN: Code duplicated, block: B:64:0x01d1  */
            /* JADX WARN: Code duplicated, block: B:66:0x01e0  */
            /* JADX WARN: Code duplicated, block: B:68:0x0200  */
            /* JADX WARN: Code duplicated, block: B:71:0x0218  */
            /* JADX WARN: Code duplicated, block: B:73:0x022a A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:74:0x022b  */
            /* JADX WARN: Code duplicated, block: B:76:0x0231  */
            /* JADX WARN: Code duplicated, block: B:78:0x0245 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:79:0x0246  */
            /* JADX WARN: Code duplicated, block: B:82:0x0255  */
            /* JADX WARN: Code duplicated, block: B:84:0x0277 A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:85:0x0278  */
            /* JADX WARN: Code duplicated, block: B:88:0x028f  */
            /* JADX WARN: Code duplicated, block: B:90:0x0293  */
            /* JADX WARN: Code duplicated, block: B:91:0x029e  */
            /* JADX WARN: Code duplicated, block: B:93:0x02a2  */
            /* JADX WARN: Code duplicated, block: B:96:0x02a9  */
            /* JADX WARN: Code duplicated, block: B:97:0x02cc  */
            /* JADX WARN: Code duplicated, block: B:99:0x02e2  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                AwaitPointerEventScope $this$awaitEachGesture;
                PointerInputChange down;
                Job resetJob;
                AwaitPointerEventScope $this$awaitEachGesture2;
                PointerInputChange upOrCancel;
                Job resetJob2;
                Job cancelOrReleaseJob;
                Object objAwaitSecondDown;
                PointerInputChange upOrCancel2;
                AwaitPointerEventScope $this$awaitEachGesture3;
                Function1<Offset, Unit> function1;
                LongPressResult longPressResult;
                PointerInputChange finalUpChange;
                Job resetJob3;
                PointerInputChange secondDown;
                Job resetJob4;
                Object objWaitForLongPress$default;
                PointerInputChange upOrCancel3;
                PointerInputChange upOrCancel4;
                Job resetJob5;
                PointerInputChange upOrCancel5;
                Function1<Offset, Unit> function2;
                PointerInputChange secondUp;
                Job resetJob6;
                Function1<Offset, Unit> function3;
                LongPressResult longPressResult2;
                Job resetJob7;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        AwaitPointerEventScope $this$awaitEachGesture4 = (AwaitPointerEventScope) this.L$0;
                        this.L$0 = $this$awaitEachGesture4;
                        this.label = 1;
                        Object objAwaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture4, false, null, this, 3, null);
                        if (objAwaitFirstDown$default == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objAwaitFirstDown$default;
                        $this$awaitEachGesture = $this$awaitEachGesture4;
                        down = (PointerInputChange) $result;
                        down.consume();
                        resetJob = BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new TapGestureDetectorKt$detectTapGestures$2$1$resetJob$1(this.$pressScope, null), 1, null);
                        if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob, null, new C00201(this.$onPress, this.$pressScope, down, null), 2, null);
                        }
                        if (this.$onLongPress == null) {
                            this.L$0 = $this$awaitEachGesture;
                            this.L$1 = resetJob;
                            this.label = 2;
                            $result = TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null);
                            if ($result == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $this$awaitEachGesture2 = $this$awaitEachGesture;
                            upOrCancel = (PointerInputChange) $result;
                            resetJob2 = resetJob;
                            if (upOrCancel == null) {
                                cancelOrReleaseJob = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob2, null, new AnonymousClass3(this.$pressScope, null), 2, null);
                            } else {
                                upOrCancel.consume();
                                cancelOrReleaseJob = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob2, null, new AnonymousClass4(this.$pressScope, null), 2, null);
                            }
                            if (upOrCancel != null) {
                                if (this.$onDoubleTap == null) {
                                    function1 = this.$onTap;
                                    if (function1 != null) {
                                        function1.invoke(Offset.m4841boximpl(upOrCancel.getPosition()));
                                    }
                                } else {
                                    this.L$0 = $this$awaitEachGesture2;
                                    this.L$1 = upOrCancel;
                                    this.L$2 = cancelOrReleaseJob;
                                    this.label = 5;
                                    objAwaitSecondDown = TapGestureDetectorKt.awaitSecondDown($this$awaitEachGesture2, upOrCancel, this);
                                    if (objAwaitSecondDown == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    AwaitPointerEventScope awaitPointerEventScope = $this$awaitEachGesture2;
                                    upOrCancel2 = upOrCancel;
                                    $result = objAwaitSecondDown;
                                    $this$awaitEachGesture3 = awaitPointerEventScope;
                                    secondDown = (PointerInputChange) $result;
                                    if (secondDown == null) {
                                        function2 = this.$onTap;
                                        if (function2 != null) {
                                            function2.invoke(Offset.m4841boximpl(upOrCancel2.getPosition()));
                                        }
                                    } else {
                                        resetJob4 = BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new AnonymousClass5(cancelOrReleaseJob, this.$pressScope, null), 1, null);
                                        if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob4, null, new AnonymousClass6(this.$onPress, this.$pressScope, secondDown, null), 2, null);
                                        }
                                        if (this.$onLongPress == null) {
                                            this.L$0 = resetJob4;
                                            this.L$1 = upOrCancel2;
                                            this.L$2 = null;
                                            this.label = 6;
                                            $result = TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture3, null, this, 1, null);
                                            if ($result == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            upOrCancel5 = upOrCancel2;
                                            secondUp = (PointerInputChange) $result;
                                            resetJob6 = resetJob4;
                                            if (secondUp != null) {
                                                secondUp.consume();
                                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                                this.$onDoubleTap.invoke(Offset.m4841boximpl(secondUp.getPosition()));
                                            } else {
                                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                                function3 = this.$onTap;
                                                if (function3 != null) {
                                                    function3.invoke(Offset.m4841boximpl(upOrCancel5.getPosition()));
                                                }
                                            }
                                        } else {
                                            this.L$0 = $this$awaitEachGesture3;
                                            this.L$1 = resetJob4;
                                            this.L$2 = upOrCancel2;
                                            this.L$3 = secondDown;
                                            this.label = 7;
                                            objWaitForLongPress$default = TapGestureDetectorKt.waitForLongPress$default($this$awaitEachGesture3, null, this, 1, null);
                                            if (objWaitForLongPress$default == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            upOrCancel3 = secondDown;
                                            $result = objWaitForLongPress$default;
                                            upOrCancel4 = upOrCancel2;
                                            resetJob5 = resetJob4;
                                            longPressResult2 = (LongPressResult) $result;
                                            if (Intrinsics.areEqual(longPressResult2, LongPressResult.Success.INSTANCE)) {
                                                this.$onLongPress.invoke(Offset.m4841boximpl(upOrCancel3.getPosition()));
                                                this.L$0 = resetJob5;
                                                this.L$1 = null;
                                                this.L$2 = null;
                                                this.L$3 = null;
                                                this.label = 8;
                                                if (TapGestureDetectorKt.consumeUntilUp($this$awaitEachGesture3, this) == coroutine_suspended) {
                                                    return coroutine_suspended;
                                                }
                                                resetJob7 = resetJob5;
                                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob7, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                                                return Unit.INSTANCE;
                                            }
                                            if (longPressResult2 instanceof LongPressResult.Released) {
                                                secondUp = ((LongPressResult.Released) longPressResult2).getFinalUpChange();
                                                upOrCancel5 = upOrCancel4;
                                                resetJob6 = resetJob5;
                                            } else {
                                                if (longPressResult2 instanceof LongPressResult.Canceled) {
                                                    throw new NoWhenBranchMatchedException();
                                                }
                                                secondUp = null;
                                                upOrCancel5 = upOrCancel4;
                                                resetJob6 = resetJob5;
                                            }
                                            if (secondUp != null) {
                                                secondUp.consume();
                                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                                this.$onDoubleTap.invoke(Offset.m4841boximpl(secondUp.getPosition()));
                                            } else {
                                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                                function3 = this.$onTap;
                                                if (function3 != null) {
                                                    function3.invoke(Offset.m4841boximpl(upOrCancel5.getPosition()));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            return Unit.INSTANCE;
                        }
                        this.L$0 = $this$awaitEachGesture;
                        this.L$1 = down;
                        this.L$2 = resetJob;
                        this.label = 3;
                        $result = TapGestureDetectorKt.waitForLongPress$default($this$awaitEachGesture, null, this, 1, null);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        longPressResult = (LongPressResult) $result;
                        if (Intrinsics.areEqual(longPressResult, LongPressResult.Success.INSTANCE)) {
                            this.$onLongPress.invoke(Offset.m4841boximpl(down.getPosition()));
                            this.L$0 = resetJob;
                            this.L$1 = null;
                            this.L$2 = null;
                            this.label = 4;
                            if (TapGestureDetectorKt.consumeUntilUp($this$awaitEachGesture, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            resetJob3 = resetJob;
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob3, null, new C00212(this.$pressScope, null), 2, null);
                            return Unit.INSTANCE;
                        }
                        if (longPressResult instanceof LongPressResult.Released) {
                            finalUpChange = ((LongPressResult.Released) longPressResult).getFinalUpChange();
                        } else {
                            if (!(longPressResult instanceof LongPressResult.Canceled)) {
                                throw new NoWhenBranchMatchedException();
                            }
                            finalUpChange = null;
                        }
                        upOrCancel = finalUpChange;
                        $this$awaitEachGesture2 = $this$awaitEachGesture;
                        resetJob2 = resetJob;
                        if (upOrCancel == null) {
                            cancelOrReleaseJob = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob2, null, new AnonymousClass3(this.$pressScope, null), 2, null);
                        } else {
                            upOrCancel.consume();
                            cancelOrReleaseJob = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob2, null, new AnonymousClass4(this.$pressScope, null), 2, null);
                        }
                        if (upOrCancel != null) {
                            if (this.$onDoubleTap == null) {
                                function1 = this.$onTap;
                                if (function1 != null) {
                                    function1.invoke(Offset.m4841boximpl(upOrCancel.getPosition()));
                                }
                            } else {
                                this.L$0 = $this$awaitEachGesture2;
                                this.L$1 = upOrCancel;
                                this.L$2 = cancelOrReleaseJob;
                                this.label = 5;
                                objAwaitSecondDown = TapGestureDetectorKt.awaitSecondDown($this$awaitEachGesture2, upOrCancel, this);
                                if (objAwaitSecondDown == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                AwaitPointerEventScope awaitPointerEventScope2 = $this$awaitEachGesture2;
                                upOrCancel2 = upOrCancel;
                                $result = objAwaitSecondDown;
                                $this$awaitEachGesture3 = awaitPointerEventScope2;
                                secondDown = (PointerInputChange) $result;
                                if (secondDown == null) {
                                    function2 = this.$onTap;
                                    if (function2 != null) {
                                        function2.invoke(Offset.m4841boximpl(upOrCancel2.getPosition()));
                                    }
                                } else {
                                    resetJob4 = BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new AnonymousClass5(cancelOrReleaseJob, this.$pressScope, null), 1, null);
                                    if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob4, null, new AnonymousClass6(this.$onPress, this.$pressScope, secondDown, null), 2, null);
                                    }
                                    if (this.$onLongPress == null) {
                                        this.L$0 = resetJob4;
                                        this.L$1 = upOrCancel2;
                                        this.L$2 = null;
                                        this.label = 6;
                                        $result = TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture3, null, this, 1, null);
                                        if ($result == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        upOrCancel5 = upOrCancel2;
                                        secondUp = (PointerInputChange) $result;
                                        resetJob6 = resetJob4;
                                        if (secondUp != null) {
                                            secondUp.consume();
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                            this.$onDoubleTap.invoke(Offset.m4841boximpl(secondUp.getPosition()));
                                        } else {
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                            function3 = this.$onTap;
                                            if (function3 != null) {
                                                function3.invoke(Offset.m4841boximpl(upOrCancel5.getPosition()));
                                            }
                                        }
                                    } else {
                                        this.L$0 = $this$awaitEachGesture3;
                                        this.L$1 = resetJob4;
                                        this.L$2 = upOrCancel2;
                                        this.L$3 = secondDown;
                                        this.label = 7;
                                        objWaitForLongPress$default = TapGestureDetectorKt.waitForLongPress$default($this$awaitEachGesture3, null, this, 1, null);
                                        if (objWaitForLongPress$default == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        upOrCancel3 = secondDown;
                                        $result = objWaitForLongPress$default;
                                        upOrCancel4 = upOrCancel2;
                                        resetJob5 = resetJob4;
                                        longPressResult2 = (LongPressResult) $result;
                                        if (Intrinsics.areEqual(longPressResult2, LongPressResult.Success.INSTANCE)) {
                                            this.$onLongPress.invoke(Offset.m4841boximpl(upOrCancel3.getPosition()));
                                            this.L$0 = resetJob5;
                                            this.L$1 = null;
                                            this.L$2 = null;
                                            this.L$3 = null;
                                            this.label = 8;
                                            if (TapGestureDetectorKt.consumeUntilUp($this$awaitEachGesture3, this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            resetJob7 = resetJob5;
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob7, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                                            return Unit.INSTANCE;
                                        }
                                        if (longPressResult2 instanceof LongPressResult.Released) {
                                            secondUp = ((LongPressResult.Released) longPressResult2).getFinalUpChange();
                                            upOrCancel5 = upOrCancel4;
                                            resetJob6 = resetJob5;
                                        } else {
                                            if (longPressResult2 instanceof LongPressResult.Canceled) {
                                                throw new NoWhenBranchMatchedException();
                                            }
                                            secondUp = null;
                                            upOrCancel5 = upOrCancel4;
                                            resetJob6 = resetJob5;
                                        }
                                        if (secondUp != null) {
                                            secondUp.consume();
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                            this.$onDoubleTap.invoke(Offset.m4841boximpl(secondUp.getPosition()));
                                        } else {
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                            function3 = this.$onTap;
                                            if (function3 != null) {
                                                function3.invoke(Offset.m4841boximpl(upOrCancel5.getPosition()));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    case 1:
                        AwaitPointerEventScope $this$awaitEachGesture5 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure($result);
                        $this$awaitEachGesture = $this$awaitEachGesture5;
                        down = (PointerInputChange) $result;
                        down.consume();
                        resetJob = BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new TapGestureDetectorKt$detectTapGestures$2$1$resetJob$1(this.$pressScope, null), 1, null);
                        if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob, null, new C00201(this.$onPress, this.$pressScope, down, null), 2, null);
                        }
                        if (this.$onLongPress == null) {
                            this.L$0 = $this$awaitEachGesture;
                            this.L$1 = resetJob;
                            this.label = 2;
                            $result = TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null);
                            if ($result == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $this$awaitEachGesture2 = $this$awaitEachGesture;
                            upOrCancel = (PointerInputChange) $result;
                            resetJob2 = resetJob;
                            if (upOrCancel == null) {
                                cancelOrReleaseJob = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob2, null, new AnonymousClass3(this.$pressScope, null), 2, null);
                            } else {
                                upOrCancel.consume();
                                cancelOrReleaseJob = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob2, null, new AnonymousClass4(this.$pressScope, null), 2, null);
                            }
                            if (upOrCancel != null) {
                                if (this.$onDoubleTap == null) {
                                    function1 = this.$onTap;
                                    if (function1 != null) {
                                        function1.invoke(Offset.m4841boximpl(upOrCancel.getPosition()));
                                    }
                                } else {
                                    this.L$0 = $this$awaitEachGesture2;
                                    this.L$1 = upOrCancel;
                                    this.L$2 = cancelOrReleaseJob;
                                    this.label = 5;
                                    objAwaitSecondDown = TapGestureDetectorKt.awaitSecondDown($this$awaitEachGesture2, upOrCancel, this);
                                    if (objAwaitSecondDown == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    AwaitPointerEventScope awaitPointerEventScope3 = $this$awaitEachGesture2;
                                    upOrCancel2 = upOrCancel;
                                    $result = objAwaitSecondDown;
                                    $this$awaitEachGesture3 = awaitPointerEventScope3;
                                    secondDown = (PointerInputChange) $result;
                                    if (secondDown == null) {
                                        function2 = this.$onTap;
                                        if (function2 != null) {
                                            function2.invoke(Offset.m4841boximpl(upOrCancel2.getPosition()));
                                        }
                                    } else {
                                        resetJob4 = BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new AnonymousClass5(cancelOrReleaseJob, this.$pressScope, null), 1, null);
                                        if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob4, null, new AnonymousClass6(this.$onPress, this.$pressScope, secondDown, null), 2, null);
                                        }
                                        if (this.$onLongPress == null) {
                                            this.L$0 = resetJob4;
                                            this.L$1 = upOrCancel2;
                                            this.L$2 = null;
                                            this.label = 6;
                                            $result = TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture3, null, this, 1, null);
                                            if ($result == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            upOrCancel5 = upOrCancel2;
                                            secondUp = (PointerInputChange) $result;
                                            resetJob6 = resetJob4;
                                            if (secondUp != null) {
                                                secondUp.consume();
                                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                                this.$onDoubleTap.invoke(Offset.m4841boximpl(secondUp.getPosition()));
                                            } else {
                                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                                function3 = this.$onTap;
                                                if (function3 != null) {
                                                    function3.invoke(Offset.m4841boximpl(upOrCancel5.getPosition()));
                                                }
                                            }
                                        } else {
                                            this.L$0 = $this$awaitEachGesture3;
                                            this.L$1 = resetJob4;
                                            this.L$2 = upOrCancel2;
                                            this.L$3 = secondDown;
                                            this.label = 7;
                                            objWaitForLongPress$default = TapGestureDetectorKt.waitForLongPress$default($this$awaitEachGesture3, null, this, 1, null);
                                            if (objWaitForLongPress$default == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            upOrCancel3 = secondDown;
                                            $result = objWaitForLongPress$default;
                                            upOrCancel4 = upOrCancel2;
                                            resetJob5 = resetJob4;
                                            longPressResult2 = (LongPressResult) $result;
                                            if (Intrinsics.areEqual(longPressResult2, LongPressResult.Success.INSTANCE)) {
                                                this.$onLongPress.invoke(Offset.m4841boximpl(upOrCancel3.getPosition()));
                                                this.L$0 = resetJob5;
                                                this.L$1 = null;
                                                this.L$2 = null;
                                                this.L$3 = null;
                                                this.label = 8;
                                                if (TapGestureDetectorKt.consumeUntilUp($this$awaitEachGesture3, this) == coroutine_suspended) {
                                                    return coroutine_suspended;
                                                }
                                                resetJob7 = resetJob5;
                                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob7, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                                                return Unit.INSTANCE;
                                            }
                                            if (longPressResult2 instanceof LongPressResult.Released) {
                                                secondUp = ((LongPressResult.Released) longPressResult2).getFinalUpChange();
                                                upOrCancel5 = upOrCancel4;
                                                resetJob6 = resetJob5;
                                            } else {
                                                if (longPressResult2 instanceof LongPressResult.Canceled) {
                                                    throw new NoWhenBranchMatchedException();
                                                }
                                                secondUp = null;
                                                upOrCancel5 = upOrCancel4;
                                                resetJob6 = resetJob5;
                                            }
                                            if (secondUp != null) {
                                                secondUp.consume();
                                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                                this.$onDoubleTap.invoke(Offset.m4841boximpl(secondUp.getPosition()));
                                            } else {
                                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                                function3 = this.$onTap;
                                                if (function3 != null) {
                                                    function3.invoke(Offset.m4841boximpl(upOrCancel5.getPosition()));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            return Unit.INSTANCE;
                        }
                        this.L$0 = $this$awaitEachGesture;
                        this.L$1 = down;
                        this.L$2 = resetJob;
                        this.label = 3;
                        $result = TapGestureDetectorKt.waitForLongPress$default($this$awaitEachGesture, null, this, 1, null);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        longPressResult = (LongPressResult) $result;
                        if (Intrinsics.areEqual(longPressResult, LongPressResult.Success.INSTANCE)) {
                            this.$onLongPress.invoke(Offset.m4841boximpl(down.getPosition()));
                            this.L$0 = resetJob;
                            this.L$1 = null;
                            this.L$2 = null;
                            this.label = 4;
                            if (TapGestureDetectorKt.consumeUntilUp($this$awaitEachGesture, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            resetJob3 = resetJob;
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob3, null, new C00212(this.$pressScope, null), 2, null);
                            return Unit.INSTANCE;
                        }
                        if (longPressResult instanceof LongPressResult.Released) {
                            finalUpChange = ((LongPressResult.Released) longPressResult).getFinalUpChange();
                        } else {
                            if (!(longPressResult instanceof LongPressResult.Canceled)) {
                                throw new NoWhenBranchMatchedException();
                            }
                            finalUpChange = null;
                        }
                        upOrCancel = finalUpChange;
                        $this$awaitEachGesture2 = $this$awaitEachGesture;
                        resetJob2 = resetJob;
                        if (upOrCancel == null) {
                            cancelOrReleaseJob = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob2, null, new AnonymousClass3(this.$pressScope, null), 2, null);
                        } else {
                            upOrCancel.consume();
                            cancelOrReleaseJob = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob2, null, new AnonymousClass4(this.$pressScope, null), 2, null);
                        }
                        if (upOrCancel != null) {
                            if (this.$onDoubleTap == null) {
                                function1 = this.$onTap;
                                if (function1 != null) {
                                    function1.invoke(Offset.m4841boximpl(upOrCancel.getPosition()));
                                }
                            } else {
                                this.L$0 = $this$awaitEachGesture2;
                                this.L$1 = upOrCancel;
                                this.L$2 = cancelOrReleaseJob;
                                this.label = 5;
                                objAwaitSecondDown = TapGestureDetectorKt.awaitSecondDown($this$awaitEachGesture2, upOrCancel, this);
                                if (objAwaitSecondDown == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                AwaitPointerEventScope awaitPointerEventScope4 = $this$awaitEachGesture2;
                                upOrCancel2 = upOrCancel;
                                $result = objAwaitSecondDown;
                                $this$awaitEachGesture3 = awaitPointerEventScope4;
                                secondDown = (PointerInputChange) $result;
                                if (secondDown == null) {
                                    function2 = this.$onTap;
                                    if (function2 != null) {
                                        function2.invoke(Offset.m4841boximpl(upOrCancel2.getPosition()));
                                    }
                                } else {
                                    resetJob4 = BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new AnonymousClass5(cancelOrReleaseJob, this.$pressScope, null), 1, null);
                                    if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob4, null, new AnonymousClass6(this.$onPress, this.$pressScope, secondDown, null), 2, null);
                                    }
                                    if (this.$onLongPress == null) {
                                        this.L$0 = resetJob4;
                                        this.L$1 = upOrCancel2;
                                        this.L$2 = null;
                                        this.label = 6;
                                        $result = TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture3, null, this, 1, null);
                                        if ($result == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        upOrCancel5 = upOrCancel2;
                                        secondUp = (PointerInputChange) $result;
                                        resetJob6 = resetJob4;
                                        if (secondUp != null) {
                                            secondUp.consume();
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                            this.$onDoubleTap.invoke(Offset.m4841boximpl(secondUp.getPosition()));
                                        } else {
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                            function3 = this.$onTap;
                                            if (function3 != null) {
                                                function3.invoke(Offset.m4841boximpl(upOrCancel5.getPosition()));
                                            }
                                        }
                                    } else {
                                        this.L$0 = $this$awaitEachGesture3;
                                        this.L$1 = resetJob4;
                                        this.L$2 = upOrCancel2;
                                        this.L$3 = secondDown;
                                        this.label = 7;
                                        objWaitForLongPress$default = TapGestureDetectorKt.waitForLongPress$default($this$awaitEachGesture3, null, this, 1, null);
                                        if (objWaitForLongPress$default == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        upOrCancel3 = secondDown;
                                        $result = objWaitForLongPress$default;
                                        upOrCancel4 = upOrCancel2;
                                        resetJob5 = resetJob4;
                                        longPressResult2 = (LongPressResult) $result;
                                        if (Intrinsics.areEqual(longPressResult2, LongPressResult.Success.INSTANCE)) {
                                            this.$onLongPress.invoke(Offset.m4841boximpl(upOrCancel3.getPosition()));
                                            this.L$0 = resetJob5;
                                            this.L$1 = null;
                                            this.L$2 = null;
                                            this.L$3 = null;
                                            this.label = 8;
                                            if (TapGestureDetectorKt.consumeUntilUp($this$awaitEachGesture3, this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            resetJob7 = resetJob5;
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob7, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                                            return Unit.INSTANCE;
                                        }
                                        if (longPressResult2 instanceof LongPressResult.Released) {
                                            secondUp = ((LongPressResult.Released) longPressResult2).getFinalUpChange();
                                            upOrCancel5 = upOrCancel4;
                                            resetJob6 = resetJob5;
                                        } else {
                                            if (longPressResult2 instanceof LongPressResult.Canceled) {
                                                throw new NoWhenBranchMatchedException();
                                            }
                                            secondUp = null;
                                            upOrCancel5 = upOrCancel4;
                                            resetJob6 = resetJob5;
                                        }
                                        if (secondUp != null) {
                                            secondUp.consume();
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                            this.$onDoubleTap.invoke(Offset.m4841boximpl(secondUp.getPosition()));
                                        } else {
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                            function3 = this.$onTap;
                                            if (function3 != null) {
                                                function3.invoke(Offset.m4841boximpl(upOrCancel5.getPosition()));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    case 2:
                        Job resetJob8 = (Job) this.L$1;
                        $this$awaitEachGesture2 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure($result);
                        resetJob = resetJob8;
                        $result = $result;
                        upOrCancel = (PointerInputChange) $result;
                        resetJob2 = resetJob;
                        if (upOrCancel == null) {
                            cancelOrReleaseJob = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob2, null, new AnonymousClass3(this.$pressScope, null), 2, null);
                        } else {
                            upOrCancel.consume();
                            cancelOrReleaseJob = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob2, null, new AnonymousClass4(this.$pressScope, null), 2, null);
                        }
                        if (upOrCancel != null) {
                            if (this.$onDoubleTap == null) {
                                function1 = this.$onTap;
                                if (function1 != null) {
                                    function1.invoke(Offset.m4841boximpl(upOrCancel.getPosition()));
                                }
                            } else {
                                this.L$0 = $this$awaitEachGesture2;
                                this.L$1 = upOrCancel;
                                this.L$2 = cancelOrReleaseJob;
                                this.label = 5;
                                objAwaitSecondDown = TapGestureDetectorKt.awaitSecondDown($this$awaitEachGesture2, upOrCancel, this);
                                if (objAwaitSecondDown == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                AwaitPointerEventScope awaitPointerEventScope5 = $this$awaitEachGesture2;
                                upOrCancel2 = upOrCancel;
                                $result = objAwaitSecondDown;
                                $this$awaitEachGesture3 = awaitPointerEventScope5;
                                secondDown = (PointerInputChange) $result;
                                if (secondDown == null) {
                                    function2 = this.$onTap;
                                    if (function2 != null) {
                                        function2.invoke(Offset.m4841boximpl(upOrCancel2.getPosition()));
                                    }
                                } else {
                                    resetJob4 = BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new AnonymousClass5(cancelOrReleaseJob, this.$pressScope, null), 1, null);
                                    if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob4, null, new AnonymousClass6(this.$onPress, this.$pressScope, secondDown, null), 2, null);
                                    }
                                    if (this.$onLongPress == null) {
                                        this.L$0 = resetJob4;
                                        this.L$1 = upOrCancel2;
                                        this.L$2 = null;
                                        this.label = 6;
                                        $result = TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture3, null, this, 1, null);
                                        if ($result == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        upOrCancel5 = upOrCancel2;
                                        secondUp = (PointerInputChange) $result;
                                        resetJob6 = resetJob4;
                                        if (secondUp != null) {
                                            secondUp.consume();
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                            this.$onDoubleTap.invoke(Offset.m4841boximpl(secondUp.getPosition()));
                                        } else {
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                            function3 = this.$onTap;
                                            if (function3 != null) {
                                                function3.invoke(Offset.m4841boximpl(upOrCancel5.getPosition()));
                                            }
                                        }
                                    } else {
                                        this.L$0 = $this$awaitEachGesture3;
                                        this.L$1 = resetJob4;
                                        this.L$2 = upOrCancel2;
                                        this.L$3 = secondDown;
                                        this.label = 7;
                                        objWaitForLongPress$default = TapGestureDetectorKt.waitForLongPress$default($this$awaitEachGesture3, null, this, 1, null);
                                        if (objWaitForLongPress$default == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        upOrCancel3 = secondDown;
                                        $result = objWaitForLongPress$default;
                                        upOrCancel4 = upOrCancel2;
                                        resetJob5 = resetJob4;
                                        longPressResult2 = (LongPressResult) $result;
                                        if (Intrinsics.areEqual(longPressResult2, LongPressResult.Success.INSTANCE)) {
                                            this.$onLongPress.invoke(Offset.m4841boximpl(upOrCancel3.getPosition()));
                                            this.L$0 = resetJob5;
                                            this.L$1 = null;
                                            this.L$2 = null;
                                            this.L$3 = null;
                                            this.label = 8;
                                            if (TapGestureDetectorKt.consumeUntilUp($this$awaitEachGesture3, this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            resetJob7 = resetJob5;
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob7, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                                            return Unit.INSTANCE;
                                        }
                                        if (longPressResult2 instanceof LongPressResult.Released) {
                                            secondUp = ((LongPressResult.Released) longPressResult2).getFinalUpChange();
                                            upOrCancel5 = upOrCancel4;
                                            resetJob6 = resetJob5;
                                        } else {
                                            if (longPressResult2 instanceof LongPressResult.Canceled) {
                                                throw new NoWhenBranchMatchedException();
                                            }
                                            secondUp = null;
                                            upOrCancel5 = upOrCancel4;
                                            resetJob6 = resetJob5;
                                        }
                                        if (secondUp != null) {
                                            secondUp.consume();
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                            this.$onDoubleTap.invoke(Offset.m4841boximpl(secondUp.getPosition()));
                                        } else {
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                            function3 = this.$onTap;
                                            if (function3 != null) {
                                                function3.invoke(Offset.m4841boximpl(upOrCancel5.getPosition()));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    case 3:
                        Job resetJob9 = (Job) this.L$2;
                        down = (PointerInputChange) this.L$1;
                        $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure($result);
                        resetJob = resetJob9;
                        $result = $result;
                        longPressResult = (LongPressResult) $result;
                        if (Intrinsics.areEqual(longPressResult, LongPressResult.Success.INSTANCE)) {
                            this.$onLongPress.invoke(Offset.m4841boximpl(down.getPosition()));
                            this.L$0 = resetJob;
                            this.L$1 = null;
                            this.L$2 = null;
                            this.label = 4;
                            if (TapGestureDetectorKt.consumeUntilUp($this$awaitEachGesture, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            resetJob3 = resetJob;
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob3, null, new C00212(this.$pressScope, null), 2, null);
                            return Unit.INSTANCE;
                        }
                        if (longPressResult instanceof LongPressResult.Released) {
                            finalUpChange = ((LongPressResult.Released) longPressResult).getFinalUpChange();
                        } else {
                            if (!(longPressResult instanceof LongPressResult.Canceled)) {
                                throw new NoWhenBranchMatchedException();
                            }
                            finalUpChange = null;
                        }
                        upOrCancel = finalUpChange;
                        $this$awaitEachGesture2 = $this$awaitEachGesture;
                        resetJob2 = resetJob;
                        if (upOrCancel == null) {
                            cancelOrReleaseJob = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob2, null, new AnonymousClass3(this.$pressScope, null), 2, null);
                        } else {
                            upOrCancel.consume();
                            cancelOrReleaseJob = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob2, null, new AnonymousClass4(this.$pressScope, null), 2, null);
                        }
                        if (upOrCancel != null) {
                            if (this.$onDoubleTap == null) {
                                function1 = this.$onTap;
                                if (function1 != null) {
                                    function1.invoke(Offset.m4841boximpl(upOrCancel.getPosition()));
                                }
                            } else {
                                this.L$0 = $this$awaitEachGesture2;
                                this.L$1 = upOrCancel;
                                this.L$2 = cancelOrReleaseJob;
                                this.label = 5;
                                objAwaitSecondDown = TapGestureDetectorKt.awaitSecondDown($this$awaitEachGesture2, upOrCancel, this);
                                if (objAwaitSecondDown == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                AwaitPointerEventScope awaitPointerEventScope6 = $this$awaitEachGesture2;
                                upOrCancel2 = upOrCancel;
                                $result = objAwaitSecondDown;
                                $this$awaitEachGesture3 = awaitPointerEventScope6;
                                secondDown = (PointerInputChange) $result;
                                if (secondDown == null) {
                                    function2 = this.$onTap;
                                    if (function2 != null) {
                                        function2.invoke(Offset.m4841boximpl(upOrCancel2.getPosition()));
                                    }
                                } else {
                                    resetJob4 = BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new AnonymousClass5(cancelOrReleaseJob, this.$pressScope, null), 1, null);
                                    if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob4, null, new AnonymousClass6(this.$onPress, this.$pressScope, secondDown, null), 2, null);
                                    }
                                    if (this.$onLongPress == null) {
                                        this.L$0 = resetJob4;
                                        this.L$1 = upOrCancel2;
                                        this.L$2 = null;
                                        this.label = 6;
                                        $result = TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture3, null, this, 1, null);
                                        if ($result == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        upOrCancel5 = upOrCancel2;
                                        secondUp = (PointerInputChange) $result;
                                        resetJob6 = resetJob4;
                                        if (secondUp != null) {
                                            secondUp.consume();
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                            this.$onDoubleTap.invoke(Offset.m4841boximpl(secondUp.getPosition()));
                                        } else {
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                            function3 = this.$onTap;
                                            if (function3 != null) {
                                                function3.invoke(Offset.m4841boximpl(upOrCancel5.getPosition()));
                                            }
                                        }
                                    } else {
                                        this.L$0 = $this$awaitEachGesture3;
                                        this.L$1 = resetJob4;
                                        this.L$2 = upOrCancel2;
                                        this.L$3 = secondDown;
                                        this.label = 7;
                                        objWaitForLongPress$default = TapGestureDetectorKt.waitForLongPress$default($this$awaitEachGesture3, null, this, 1, null);
                                        if (objWaitForLongPress$default == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        upOrCancel3 = secondDown;
                                        $result = objWaitForLongPress$default;
                                        upOrCancel4 = upOrCancel2;
                                        resetJob5 = resetJob4;
                                        longPressResult2 = (LongPressResult) $result;
                                        if (Intrinsics.areEqual(longPressResult2, LongPressResult.Success.INSTANCE)) {
                                            this.$onLongPress.invoke(Offset.m4841boximpl(upOrCancel3.getPosition()));
                                            this.L$0 = resetJob5;
                                            this.L$1 = null;
                                            this.L$2 = null;
                                            this.L$3 = null;
                                            this.label = 8;
                                            if (TapGestureDetectorKt.consumeUntilUp($this$awaitEachGesture3, this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            resetJob7 = resetJob5;
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob7, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                                            return Unit.INSTANCE;
                                        }
                                        if (longPressResult2 instanceof LongPressResult.Released) {
                                            secondUp = ((LongPressResult.Released) longPressResult2).getFinalUpChange();
                                            upOrCancel5 = upOrCancel4;
                                            resetJob6 = resetJob5;
                                        } else {
                                            if (longPressResult2 instanceof LongPressResult.Canceled) {
                                                throw new NoWhenBranchMatchedException();
                                            }
                                            secondUp = null;
                                            upOrCancel5 = upOrCancel4;
                                            resetJob6 = resetJob5;
                                        }
                                        if (secondUp != null) {
                                            secondUp.consume();
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                            this.$onDoubleTap.invoke(Offset.m4841boximpl(secondUp.getPosition()));
                                        } else {
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                            function3 = this.$onTap;
                                            if (function3 != null) {
                                                function3.invoke(Offset.m4841boximpl(upOrCancel5.getPosition()));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    case 4:
                        Job resetJob10 = (Job) this.L$0;
                        ResultKt.throwOnFailure($result);
                        resetJob3 = resetJob10;
                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob3, null, new C00212(this.$pressScope, null), 2, null);
                        return Unit.INSTANCE;
                    case 5:
                        Job cancelOrReleaseJob2 = (Job) this.L$2;
                        upOrCancel2 = (PointerInputChange) this.L$1;
                        AwaitPointerEventScope $this$awaitEachGesture6 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure($result);
                        $this$awaitEachGesture3 = $this$awaitEachGesture6;
                        cancelOrReleaseJob = cancelOrReleaseJob2;
                        $result = $result;
                        secondDown = (PointerInputChange) $result;
                        if (secondDown == null) {
                            function2 = this.$onTap;
                            if (function2 != null) {
                                function2.invoke(Offset.m4841boximpl(upOrCancel2.getPosition()));
                            }
                        } else {
                            resetJob4 = BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new AnonymousClass5(cancelOrReleaseJob, this.$pressScope, null), 1, null);
                            if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob4, null, new AnonymousClass6(this.$onPress, this.$pressScope, secondDown, null), 2, null);
                            }
                            if (this.$onLongPress == null) {
                                this.L$0 = resetJob4;
                                this.L$1 = upOrCancel2;
                                this.L$2 = null;
                                this.label = 6;
                                $result = TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture3, null, this, 1, null);
                                if ($result == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                upOrCancel5 = upOrCancel2;
                                secondUp = (PointerInputChange) $result;
                                resetJob6 = resetJob4;
                                if (secondUp != null) {
                                    secondUp.consume();
                                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                    this.$onDoubleTap.invoke(Offset.m4841boximpl(secondUp.getPosition()));
                                } else {
                                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                    function3 = this.$onTap;
                                    if (function3 != null) {
                                        function3.invoke(Offset.m4841boximpl(upOrCancel5.getPosition()));
                                    }
                                }
                            } else {
                                this.L$0 = $this$awaitEachGesture3;
                                this.L$1 = resetJob4;
                                this.L$2 = upOrCancel2;
                                this.L$3 = secondDown;
                                this.label = 7;
                                objWaitForLongPress$default = TapGestureDetectorKt.waitForLongPress$default($this$awaitEachGesture3, null, this, 1, null);
                                if (objWaitForLongPress$default == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                upOrCancel3 = secondDown;
                                $result = objWaitForLongPress$default;
                                upOrCancel4 = upOrCancel2;
                                resetJob5 = resetJob4;
                                longPressResult2 = (LongPressResult) $result;
                                if (Intrinsics.areEqual(longPressResult2, LongPressResult.Success.INSTANCE)) {
                                    this.$onLongPress.invoke(Offset.m4841boximpl(upOrCancel3.getPosition()));
                                    this.L$0 = resetJob5;
                                    this.L$1 = null;
                                    this.L$2 = null;
                                    this.L$3 = null;
                                    this.label = 8;
                                    if (TapGestureDetectorKt.consumeUntilUp($this$awaitEachGesture3, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    resetJob7 = resetJob5;
                                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob7, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                                    return Unit.INSTANCE;
                                }
                                if (longPressResult2 instanceof LongPressResult.Released) {
                                    secondUp = ((LongPressResult.Released) longPressResult2).getFinalUpChange();
                                    upOrCancel5 = upOrCancel4;
                                    resetJob6 = resetJob5;
                                } else {
                                    if (longPressResult2 instanceof LongPressResult.Canceled) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    secondUp = null;
                                    upOrCancel5 = upOrCancel4;
                                    resetJob6 = resetJob5;
                                }
                                if (secondUp != null) {
                                    secondUp.consume();
                                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                    this.$onDoubleTap.invoke(Offset.m4841boximpl(secondUp.getPosition()));
                                } else {
                                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                    function3 = this.$onTap;
                                    if (function3 != null) {
                                        function3.invoke(Offset.m4841boximpl(upOrCancel5.getPosition()));
                                    }
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    case 6:
                        upOrCancel5 = (PointerInputChange) this.L$1;
                        Job resetJob11 = (Job) this.L$0;
                        ResultKt.throwOnFailure($result);
                        resetJob4 = resetJob11;
                        secondUp = (PointerInputChange) $result;
                        resetJob6 = resetJob4;
                        if (secondUp != null) {
                            secondUp.consume();
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                            this.$onDoubleTap.invoke(Offset.m4841boximpl(secondUp.getPosition()));
                        } else {
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                            function3 = this.$onTap;
                            if (function3 != null) {
                                function3.invoke(Offset.m4841boximpl(upOrCancel5.getPosition()));
                            }
                        }
                        return Unit.INSTANCE;
                    case 7:
                        PointerInputChange secondDown2 = (PointerInputChange) this.L$3;
                        PointerInputChange upOrCancel6 = (PointerInputChange) this.L$2;
                        resetJob5 = (Job) this.L$1;
                        AwaitPointerEventScope $this$awaitEachGesture7 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure($result);
                        $this$awaitEachGesture3 = $this$awaitEachGesture7;
                        upOrCancel4 = upOrCancel6;
                        upOrCancel3 = secondDown2;
                        $result = $result;
                        longPressResult2 = (LongPressResult) $result;
                        if (Intrinsics.areEqual(longPressResult2, LongPressResult.Success.INSTANCE)) {
                            this.$onLongPress.invoke(Offset.m4841boximpl(upOrCancel3.getPosition()));
                            this.L$0 = resetJob5;
                            this.L$1 = null;
                            this.L$2 = null;
                            this.L$3 = null;
                            this.label = 8;
                            if (TapGestureDetectorKt.consumeUntilUp($this$awaitEachGesture3, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            resetJob7 = resetJob5;
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob7, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                            return Unit.INSTANCE;
                        }
                        if (longPressResult2 instanceof LongPressResult.Released) {
                            secondUp = ((LongPressResult.Released) longPressResult2).getFinalUpChange();
                            upOrCancel5 = upOrCancel4;
                            resetJob6 = resetJob5;
                        } else {
                            if (longPressResult2 instanceof LongPressResult.Canceled) {
                                throw new NoWhenBranchMatchedException();
                            }
                            secondUp = null;
                            upOrCancel5 = upOrCancel4;
                            resetJob6 = resetJob5;
                        }
                        if (secondUp != null) {
                            secondUp.consume();
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                            this.$onDoubleTap.invoke(Offset.m4841boximpl(secondUp.getPosition()));
                        } else {
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob6, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                            function3 = this.$onTap;
                            if (function3 != null) {
                                function3.invoke(Offset.m4841boximpl(upOrCancel5.getPosition()));
                            }
                        }
                        return Unit.INSTANCE;
                    case 8:
                        Job resetJob12 = (Job) this.L$0;
                        ResultKt.throwOnFailure($result);
                        resetJob7 = resetJob12;
                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob7, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: TapGestureDetector.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$1", f = "TapGestureDetector.kt", i = {}, l = {110}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class C00201 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PointerInputChange $down;
                final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                C00201(Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, PressGestureScopeImpl pressGestureScopeImpl, PointerInputChange pointerInputChange, Continuation<? super C00201> continuation) {
                    super(2, continuation);
                    this.$onPress = function3;
                    this.$pressScope = pressGestureScopeImpl;
                    this.$down = pointerInputChange;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00201(this.$onPress, this.$pressScope, this.$down, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00201) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> function3 = this.$onPress;
                            PressGestureScopeImpl pressGestureScopeImpl = this.$pressScope;
                            Offset offsetM4841boximpl = Offset.m4841boximpl(this.$down.getPosition());
                            this.label = 1;
                            if (function3.invoke(pressGestureScopeImpl, offsetM4841boximpl, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$2, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: TapGestureDetector.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$2", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class C00212 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00212(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super C00212> continuation) {
                    super(2, continuation);
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00212(this.$pressScope, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00212) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            this.$pressScope.release();
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$3, reason: invalid class name */
            /* JADX INFO: compiled from: TapGestureDetector.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$3", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass3(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass3> continuation) {
                    super(2, continuation);
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass3(this.$pressScope, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            this.$pressScope.cancel();
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$4, reason: invalid class name */
            /* JADX INFO: compiled from: TapGestureDetector.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$4", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass4(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass4> continuation) {
                    super(2, continuation);
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass4(this.$pressScope, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            this.$pressScope.release();
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$5, reason: invalid class name */
            /* JADX INFO: compiled from: TapGestureDetector.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$5", f = "TapGestureDetector.kt", i = {}, l = {157, 158}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Job $cancelOrReleaseJob;
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass5(Job job, PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass5> continuation) {
                    super(2, continuation);
                    this.$cancelOrReleaseJob = job;
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass5(this.$cancelOrReleaseJob, this.$pressScope, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code restructure failed: missing block: B:12:0x0038, code lost:
                
                    if (r4.$pressScope.reset(r4) == r0) goto L13;
                 */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r5) {
                    /*
                        r4 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r4.label
                        switch(r1) {
                            case 0: goto L1a;
                            case 1: goto L16;
                            case 2: goto L12;
                            default: goto L9;
                        }
                    L9:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r0)
                        throw r5
                    L12:
                        kotlin.ResultKt.throwOnFailure(r5)
                        goto L3b
                    L16:
                        kotlin.ResultKt.throwOnFailure(r5)
                        goto L2c
                    L1a:
                        kotlin.ResultKt.throwOnFailure(r5)
                        kotlinx.coroutines.Job r1 = r4.$cancelOrReleaseJob
                        r2 = r4
                        kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                        r3 = 1
                        r4.label = r3
                        java.lang.Object r1 = r1.join(r2)
                        if (r1 != r0) goto L2c
                        goto L3a
                    L2c:
                        androidx.compose.foundation.gestures.PressGestureScopeImpl r1 = r4.$pressScope
                        r2 = r4
                        kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                        r3 = 2
                        r4.label = r3
                        java.lang.Object r1 = r1.reset(r2)
                        if (r1 != r0) goto L3b
                    L3a:
                        return r0
                    L3b:
                        kotlin.Unit r0 = kotlin.Unit.INSTANCE
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.C02152.AnonymousClass1.AnonymousClass5.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$6, reason: invalid class name */
            /* JADX INFO: compiled from: TapGestureDetector.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$6", f = "TapGestureDetector.kt", i = {}, l = {161}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class AnonymousClass6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                final /* synthetic */ PointerInputChange $secondDown;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass6(Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, PressGestureScopeImpl pressGestureScopeImpl, PointerInputChange pointerInputChange, Continuation<? super AnonymousClass6> continuation) {
                    super(2, continuation);
                    this.$onPress = function3;
                    this.$pressScope = pressGestureScopeImpl;
                    this.$secondDown = pointerInputChange;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass6(this.$onPress, this.$pressScope, this.$secondDown, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> function3 = this.$onPress;
                            PressGestureScopeImpl pressGestureScopeImpl = this.$pressScope;
                            Offset offsetM4841boximpl = Offset.m4841boximpl(this.$secondDown.getPosition());
                            this.label = 1;
                            if (function3.invoke(pressGestureScopeImpl, offsetM4841boximpl, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$7, reason: invalid class name */
            /* JADX INFO: compiled from: TapGestureDetector.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$7", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class AnonymousClass7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass7(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass7> continuation) {
                    super(2, continuation);
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass7(this.$pressScope, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass7) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            this.$pressScope.release();
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$8, reason: invalid class name */
            /* JADX INFO: compiled from: TapGestureDetector.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$8", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class AnonymousClass8 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass8(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass8> continuation) {
                    super(2, continuation);
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass8(this.$pressScope, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass8) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            this.$pressScope.cancel();
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }
        }
    }

    public static final Object detectTapGestures(PointerInputScope $this$detectTapGestures, Function1<? super Offset, Unit> function1, Function1<? super Offset, Unit> function2, Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function4, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C02152($this$detectTapGestures, function3, function2, function1, function4, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:23:0x0085  */
    /* JADX WARN: Code duplicated, block: B:26:0x0097 A[LOOP:1: B:22:0x0083->B:26:0x0097, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:34:0x0095 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x004c -> B:18:0x0053). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object consumeUntilUp(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, kotlin.coroutines.Continuation<? super kotlin.Unit> r18) {
        /*
            r0 = r18
            boolean r1 = r0 instanceof androidx.compose.foundation.gestures.TapGestureDetectorKt.C02131
            if (r1 == 0) goto L16
            r1 = r0
            androidx.compose.foundation.gestures.TapGestureDetectorKt$consumeUntilUp$1 r1 = (androidx.compose.foundation.gestures.TapGestureDetectorKt.C02131) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r2 = r1.label
            int r2 = r2 - r3
            r1.label = r2
            goto L1b
        L16:
            androidx.compose.foundation.gestures.TapGestureDetectorKt$consumeUntilUp$1 r1 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$consumeUntilUp$1
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
            goto L53
        L3b:
            kotlin.ResultKt.throwOnFailure(r2)
            r4 = r17
        L40:
            r1.L$0 = r4
            r1.label = r5
            r6 = 0
            java.lang.Object r6 = androidx.compose.ui.input.pointer.AwaitPointerEventScope.awaitPointerEvent$default(r4, r6, r1, r5, r6)
            if (r6 != r3) goto L4c
            return r3
        L4c:
            r16 = r3
            r3 = r2
            r2 = r6
            r6 = r4
            r4 = r16
        L53:
            androidx.compose.ui.input.pointer.PointerEvent r2 = (androidx.compose.ui.input.pointer.PointerEvent) r2
            java.util.List r7 = r2.getChanges()
            r8 = 0
            r9 = 0
            r10 = r7
            java.util.Collection r10 = (java.util.Collection) r10
            int r10 = r10.size()
        L62:
            if (r9 >= r10) goto L73
            java.lang.Object r11 = r7.get(r9)
            r12 = r11
            androidx.compose.ui.input.pointer.PointerInputChange r12 = (androidx.compose.ui.input.pointer.PointerInputChange) r12
            r13 = 0
            r12.consume()
            int r9 = r9 + 1
            goto L62
        L73:
            java.util.List r2 = r2.getChanges()
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = r2
            java.util.Collection r10 = (java.util.Collection) r10
            int r10 = r10.size()
        L83:
            if (r9 >= r10) goto L9b
            java.lang.Object r11 = r2.get(r9)
            r12 = r11
            r13 = 0
            r14 = r12
            androidx.compose.ui.input.pointer.PointerInputChange r14 = (androidx.compose.ui.input.pointer.PointerInputChange) r14
            r15 = 0
            boolean r14 = r14.getPressed()
            if (r14 == 0) goto L97
            r2 = r5
            goto L9d
        L97:
            int r9 = r9 + 1
            goto L83
        L9b:
            r2 = 0
        L9d:
            if (r2 != 0) goto La2
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        La2:
            r2 = r3
            r3 = r4
            r4 = r6
            goto L40
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.consumeUntilUp(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitSecondDown$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TapGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitSecondDown$2", f = "TapGestureDetector.kt", i = {0, 0}, l = {227}, m = "invokeSuspend", n = {"$this$withTimeoutOrNull", "minUptime"}, s = {"L$0", "J$0"}, v = 1)
    static final class C02122 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super PointerInputChange>, Object> {
        final /* synthetic */ PointerInputChange $firstUp;
        long J$0;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02122(PointerInputChange pointerInputChange, Continuation<? super C02122> continuation) {
            super(2, continuation);
            this.$firstUp = pointerInputChange;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02122 c02122 = new C02122(this.$firstUp, continuation);
            c02122.L$0 = obj;
            return c02122;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super PointerInputChange> continuation) {
            return ((C02122) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:10:0x004c A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:11:0x004d  */
        /* JADX WARN: Code duplicated, block: B:14:0x005b A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:15:0x005c  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x004d -> B:12:0x0051). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                switch(r1) {
                    case 0: goto L20;
                    case 1: goto L12;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L12:
                long r1 = r10.J$0
                java.lang.Object r3 = r10.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r3 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r3
                kotlin.ResultKt.throwOnFailure(r11)
                r9 = r10
                r7 = r1
                r1 = r0
                r0 = r11
                goto L51
            L20:
                kotlin.ResultKt.throwOnFailure(r11)
                java.lang.Object r1 = r10.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                androidx.compose.ui.input.pointer.PointerInputChange r2 = r10.$firstUp
                long r2 = r2.getUptimeMillis()
                androidx.compose.ui.platform.ViewConfiguration r4 = r1.getViewConfiguration()
                long r4 = r4.getDoubleTapMinTimeMillis()
                long r2 = r2 + r4
                r9 = r10
                r7 = r2
            L38:
                r4 = r9
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r9.L$0 = r1
                r9.J$0 = r7
                r2 = 1
                r9.label = r2
                r2 = 0
                r3 = 0
                r5 = 3
                r6 = 0
                java.lang.Object r2 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r1, r2, r3, r4, r5, r6)
                if (r2 != r0) goto L4d
                return r0
            L4d:
                r3 = r1
                r1 = r0
                r0 = r11
                r11 = r2
            L51:
                androidx.compose.ui.input.pointer.PointerInputChange r11 = (androidx.compose.ui.input.pointer.PointerInputChange) r11
                long r4 = r11.getUptimeMillis()
                int r2 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
                if (r2 < 0) goto L5c
                return r11
            L5c:
                r11 = r0
                r0 = r1
                r1 = r3
                goto L38
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.C02122.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object awaitSecondDown(AwaitPointerEventScope $this$awaitSecondDown, PointerInputChange firstUp, Continuation<? super PointerInputChange> continuation) {
        return $this$awaitSecondDown.withTimeoutOrNull($this$awaitSecondDown.getViewConfiguration().getDoubleTapTimeoutMillis(), new C02122(firstUp, null), continuation);
    }

    public static /* synthetic */ Object detectTapAndPress$default(PointerInputScope pointerInputScope, Function3 function3, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function3 = NoPressGesture;
        }
        if ((i & 2) != 0) {
            function1 = null;
        }
        return detectTapAndPress(pointerInputScope, function3, function1, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TapGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2", f = "TapGestureDetector.kt", i = {}, l = {247}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C02142 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
        final /* synthetic */ Function1<Offset, Unit> $onTap;
        final /* synthetic */ PressGestureScopeImpl $pressScope;
        final /* synthetic */ PointerInputScope $this_detectTapAndPress;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C02142(PointerInputScope pointerInputScope, Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function1, PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super C02142> continuation) {
            super(2, continuation);
            this.$this_detectTapAndPress = pointerInputScope;
            this.$onPress = function3;
            this.$onTap = function1;
            this.$pressScope = pressGestureScopeImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02142 c02142 = new C02142(this.$this_detectTapAndPress, this.$onPress, this.$onTap, this.$pressScope, continuation);
            c02142.L$0 = obj;
            return c02142;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02142) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: TapGestureDetector.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1", f = "TapGestureDetector.kt", i = {0, 0, 1}, l = {251, 257}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "resetJob", "resetJob"}, s = {"L$0", "L$1", "L$0"}, v = 1)
        static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ CoroutineScope $$this$coroutineScope;
            final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
            final /* synthetic */ Function1<Offset, Unit> $onTap;
            final /* synthetic */ PressGestureScopeImpl $pressScope;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(CoroutineScope coroutineScope, Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function1, PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$$this$coroutineScope = coroutineScope;
                this.$onPress = function3;
                this.$onTap = function1;
                this.$pressScope = pressGestureScopeImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$coroutineScope, this.$onPress, this.$onTap, this.$pressScope, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:14:0x0077  */
            /* JADX WARN: Code duplicated, block: B:17:0x009b A[RETURN] */
            /* JADX WARN: Code duplicated, block: B:20:0x00a0  */
            /* JADX WARN: Code duplicated, block: B:21:0x00b3  */
            /* JADX WARN: Code duplicated, block: B:23:0x00cc  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Job resetJob;
                AwaitPointerEventScope $this$awaitEachGesture;
                PointerInputChange down;
                PointerInputChange up;
                Function1<Offset, Unit> function1;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        AwaitPointerEventScope $this$awaitEachGesture2 = (AwaitPointerEventScope) this.L$0;
                        Job resetJob2 = BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new TapGestureDetectorKt$detectTapAndPress$2$1$resetJob$1(this.$pressScope, null), 1, null);
                        this.L$0 = $this$awaitEachGesture2;
                        this.L$1 = resetJob2;
                        this.label = 1;
                        Object objAwaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture2, false, null, this, 3, null);
                        if (objAwaitFirstDown$default == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objAwaitFirstDown$default;
                        resetJob = resetJob2;
                        $this$awaitEachGesture = $this$awaitEachGesture2;
                        PointerInputChange it = (PointerInputChange) $result;
                        it.consume();
                        down = (PointerInputChange) $result;
                        if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob, null, new C00181(this.$onPress, this.$pressScope, down, null), 2, null);
                        }
                        this.L$0 = resetJob;
                        this.L$1 = null;
                        this.label = 2;
                        $result = TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        up = (PointerInputChange) $result;
                        if (up == null) {
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob, null, new C00192(this.$pressScope, null), 2, null);
                        } else {
                            up.consume();
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob, null, new AnonymousClass3(this.$pressScope, null), 2, null);
                            function1 = this.$onTap;
                            if (function1 != null) {
                                function1.invoke(Offset.m4841boximpl(up.getPosition()));
                            }
                        }
                        return Unit.INSTANCE;
                    case 1:
                        Job resetJob3 = (Job) this.L$1;
                        AwaitPointerEventScope $this$awaitEachGesture3 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure($result);
                        resetJob = resetJob3;
                        $this$awaitEachGesture = $this$awaitEachGesture3;
                        PointerInputChange it2 = (PointerInputChange) $result;
                        it2.consume();
                        down = (PointerInputChange) $result;
                        if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob, null, new C00181(this.$onPress, this.$pressScope, down, null), 2, null);
                        }
                        this.L$0 = resetJob;
                        this.L$1 = null;
                        this.label = 2;
                        $result = TapGestureDetectorKt.waitForUpOrCancellation$default($this$awaitEachGesture, null, this, 1, null);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        up = (PointerInputChange) $result;
                        if (up == null) {
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob, null, new C00192(this.$pressScope, null), 2, null);
                        } else {
                            up.consume();
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob, null, new AnonymousClass3(this.$pressScope, null), 2, null);
                            function1 = this.$onTap;
                            if (function1 != null) {
                                function1.invoke(Offset.m4841boximpl(up.getPosition()));
                            }
                        }
                        return Unit.INSTANCE;
                    case 2:
                        Job resetJob4 = (Job) this.L$0;
                        ResultKt.throwOnFailure($result);
                        resetJob = resetJob4;
                        up = (PointerInputChange) $result;
                        if (up == null) {
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob, null, new C00192(this.$pressScope, null), 2, null);
                        } else {
                            up.consume();
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, resetJob, null, new AnonymousClass3(this.$pressScope, null), 2, null);
                            function1 = this.$onTap;
                            if (function1 != null) {
                                function1.invoke(Offset.m4841boximpl(up.getPosition()));
                            }
                        }
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: TapGestureDetector.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$1", f = "TapGestureDetector.kt", i = {}, l = {254}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class C00181 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PointerInputChange $down;
                final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                C00181(Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, PressGestureScopeImpl pressGestureScopeImpl, PointerInputChange pointerInputChange, Continuation<? super C00181> continuation) {
                    super(2, continuation);
                    this.$onPress = function3;
                    this.$pressScope = pressGestureScopeImpl;
                    this.$down = pointerInputChange;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00181(this.$onPress, this.$pressScope, this.$down, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00181) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> function3 = this.$onPress;
                            PressGestureScopeImpl pressGestureScopeImpl = this.$pressScope;
                            Offset offsetM4841boximpl = Offset.m4841boximpl(this.$down.getPosition());
                            this.label = 1;
                            if (function3.invoke(pressGestureScopeImpl, offsetM4841boximpl, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$2, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: TapGestureDetector.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$2", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class C00192 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00192(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super C00192> continuation) {
                    super(2, continuation);
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00192(this.$pressScope, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00192) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            this.$pressScope.cancel();
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$3, reason: invalid class name */
            /* JADX INFO: compiled from: TapGestureDetector.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$3", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass3(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass3> continuation) {
                    super(2, continuation);
                    this.$pressScope = pressGestureScopeImpl;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass3(this.$pressScope, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            this.$pressScope.release();
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    this.label = 1;
                    if (ForEachGestureKt.awaitEachGesture(this.$this_detectTapAndPress, new AnonymousClass1($this$coroutineScope, this.$onPress, this.$onTap, this.$pressScope, null), this) == coroutine_suspended) {
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

    public static final Object detectTapAndPress(PointerInputScope $this$detectTapAndPress, Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function1, Continuation<? super Unit> continuation) {
        PressGestureScopeImpl pressScope = new PressGestureScopeImpl($this$detectTapAndPress);
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C02142($this$detectTapAndPress, function3, function1, pressScope, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    public static /* synthetic */ Object awaitFirstDown$default(AwaitPointerEventScope awaitPointerEventScope, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return awaitFirstDown(awaitPointerEventScope, z, continuation);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with PointerEventPass instead.")
    public static final /* synthetic */ Object awaitFirstDown(AwaitPointerEventScope $this$awaitFirstDown, boolean requireUnconsumed, Continuation $completion) {
        return awaitFirstDown($this$awaitFirstDown, requireUnconsumed, PointerEventPass.Main, $completion);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0053 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:17:0x0054  */
    /* JADX WARN: Code duplicated, block: B:20:0x005d  */
    /* JADX WARN: Code duplicated, block: B:21:0x005f  */
    /* JADX WARN: Code duplicated, block: B:24:0x0068  */
    /* JADX WARN: Code duplicated, block: B:26:0x0071  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0054 -> B:18:0x0058). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object awaitFirstDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r10, boolean r11, androidx.compose.ui.input.pointer.PointerEventPass r12, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r13) {
        /*
            boolean r0 = r13 instanceof androidx.compose.foundation.gestures.TapGestureDetectorKt.AnonymousClass2
            if (r0 == 0) goto L14
            r0 = r13
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2 r0 = (androidx.compose.foundation.gestures.TapGestureDetectorKt.AnonymousClass2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2 r0 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2
            r0.<init>(r13)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            switch(r3) {
                case 0: goto L3e;
                case 1: goto L2e;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2e:
            boolean r10 = r0.Z$0
            java.lang.Object r11 = r0.L$1
            androidx.compose.ui.input.pointer.PointerEventPass r11 = (androidx.compose.ui.input.pointer.PointerEventPass) r11
            java.lang.Object r12 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r12 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r12
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r2
            r2 = r1
            goto L58
        L3e:
            kotlin.ResultKt.throwOnFailure(r1)
            r9 = r12
            r12 = r10
            r10 = r11
            r11 = r9
        L45:
            r0.L$0 = r12
            r0.L$1 = r11
            r0.Z$0 = r10
            r0.label = r4
            java.lang.Object r3 = r12.awaitPointerEvent(r11, r0)
            if (r3 != r2) goto L54
            return r2
        L54:
            r9 = r2
            r2 = r1
            r1 = r3
            r3 = r9
        L58:
            androidx.compose.ui.input.pointer.PointerEvent r1 = (androidx.compose.ui.input.pointer.PointerEvent) r1
            r5 = 0
            if (r10 == 0) goto L5f
            r6 = r4
            goto L60
        L5f:
            r6 = r5
        L60:
            r7 = 2
            r8 = 0
            boolean r6 = isChangedToDown$default(r1, r6, r5, r7, r8)
            if (r6 == 0) goto L71
            java.util.List r3 = r1.getChanges()
            java.lang.Object r3 = r3.get(r5)
            return r3
        L71:
            r1 = r2
            r2 = r3
            goto L45
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, boolean, androidx.compose.ui.input.pointer.PointerEventPass, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object awaitFirstDown$default(AwaitPointerEventScope awaitPointerEventScope, boolean z, PointerEventPass pointerEventPass, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            pointerEventPass = PointerEventPass.Main;
        }
        return awaitFirstDown(awaitPointerEventScope, z, pointerEventPass, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0053 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:17:0x0054  */
    /* JADX WARN: Code duplicated, block: B:20:0x005d  */
    /* JADX WARN: Code duplicated, block: B:21:0x005f  */
    /* JADX WARN: Code duplicated, block: B:24:0x0066  */
    /* JADX WARN: Code duplicated, block: B:26:0x006f  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0054 -> B:18:0x0058). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object awaitPrimaryFirstDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r8, boolean r9, androidx.compose.ui.input.pointer.PointerEventPass r10, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r11) {
        /*
            boolean r0 = r11 instanceof androidx.compose.foundation.gestures.TapGestureDetectorKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitPrimaryFirstDown$1 r0 = (androidx.compose.foundation.gestures.TapGestureDetectorKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitPrimaryFirstDown$1 r0 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitPrimaryFirstDown$1
            r0.<init>(r11)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            switch(r3) {
                case 0: goto L3e;
                case 1: goto L2e;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2e:
            boolean r8 = r0.Z$0
            java.lang.Object r9 = r0.L$1
            androidx.compose.ui.input.pointer.PointerEventPass r9 = (androidx.compose.ui.input.pointer.PointerEventPass) r9
            java.lang.Object r10 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r10 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r10
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r2
            r2 = r1
            goto L58
        L3e:
            kotlin.ResultKt.throwOnFailure(r1)
            r7 = r10
            r10 = r8
            r8 = r9
            r9 = r7
        L45:
            r0.L$0 = r10
            r0.L$1 = r9
            r0.Z$0 = r8
            r0.label = r4
            java.lang.Object r3 = r10.awaitPointerEvent(r9, r0)
            if (r3 != r2) goto L54
            return r2
        L54:
            r7 = r2
            r2 = r1
            r1 = r3
            r3 = r7
        L58:
            androidx.compose.ui.input.pointer.PointerEvent r1 = (androidx.compose.ui.input.pointer.PointerEvent) r1
            r5 = 0
            if (r8 == 0) goto L5f
            r6 = r4
            goto L60
        L5f:
            r6 = r5
        L60:
            boolean r6 = isChangedToDown(r1, r6, r4)
            if (r6 == 0) goto L6f
            java.util.List r3 = r1.getChanges()
            java.lang.Object r3 = r3.get(r5)
            return r3
        L6f:
            r1 = r2
            r2 = r3
            goto L45
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitPrimaryFirstDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, boolean, androidx.compose.ui.input.pointer.PointerEventPass, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object awaitPrimaryFirstDown$default(AwaitPointerEventScope awaitPointerEventScope, boolean z, PointerEventPass pointerEventPass, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            pointerEventPass = PointerEventPass.Main;
        }
        return awaitPrimaryFirstDown(awaitPointerEventScope, z, pointerEventPass, continuation);
    }

    public static /* synthetic */ boolean isChangedToDown$default(PointerEvent pointerEvent, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = TapGestureDetector_androidKt.firstDownRefersToPrimaryMouseButtonOnly();
        }
        return isChangedToDown(pointerEvent, z, z2);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x003b  */
    public static final boolean isChangedToDown(PointerEvent $this$isChangedToDown, boolean requireUnconsumed, boolean onlyPrimaryMouseButton) {
        boolean onlyPrimaryButtonCausesDown;
        boolean z;
        if (!onlyPrimaryMouseButton) {
            onlyPrimaryButtonCausesDown = false;
        } else {
            List<PointerInputChange> changes = $this$isChangedToDown.getChanges();
            int index$iv$iv = 0;
            int size = changes.size();
            while (true) {
                if (index$iv$iv < size) {
                    Object item$iv$iv = changes.get(index$iv$iv);
                    if (!PointerType.m6481equalsimpl0(((PointerInputChange) item$iv$iv).getType(), PointerType.INSTANCE.m6486getMouseT8wyACA())) {
                        z = false;
                        break;
                    }
                    index$iv$iv++;
                } else {
                    z = true;
                    break;
                }
            }
            if (z) {
                onlyPrimaryButtonCausesDown = true;
            } else {
                onlyPrimaryButtonCausesDown = false;
            }
        }
        if (onlyPrimaryButtonCausesDown && !PointerEvent_androidKt.m6383isPrimaryPressedaHzCxE($this$isChangedToDown.getButtons())) {
            return false;
        }
        List<PointerInputChange> changes2 = $this$isChangedToDown.getChanges();
        int size2 = changes2.size();
        for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
            Object item$iv$iv2 = changes2.get(index$iv$iv2);
            PointerInputChange it = (PointerInputChange) item$iv$iv2;
            if (!(requireUnconsumed ? PointerEventKt.changedToDown(it) : PointerEventKt.changedToDownIgnoreConsumed(it))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0082  */
    /* JADX WARN: Code duplicated, block: B:24:0x0098 A[LOOP:1: B:20:0x0080->B:24:0x0098, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:61:0x0096 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x010b -> B:48:0x010d). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object waitForUpOrCancellation(androidx.compose.ui.input.pointer.AwaitPointerEventScope r21, androidx.compose.ui.input.pointer.PointerEventPass r22, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r23) {
        /*
            Method dump skipped, instruction units count: 334
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.waitForUpOrCancellation(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.ui.input.pointer.PointerEventPass, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object waitForUpOrCancellation$default(AwaitPointerEventScope awaitPointerEventScope, PointerEventPass pointerEventPass, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            pointerEventPass = PointerEventPass.Main;
        }
        return waitForUpOrCancellation(awaitPointerEventScope, pointerEventPass, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r4v0, types: [T, androidx.compose.foundation.gestures.LongPressResult$Canceled] */
    public static final Object waitForLongPress(AwaitPointerEventScope $this$waitForLongPress, PointerEventPass pass, Continuation<? super LongPressResult> continuation) {
        C02171 c02171;
        Ref.ObjectRef result;
        if (continuation instanceof C02171) {
            c02171 = (C02171) continuation;
            if ((c02171.label & Integer.MIN_VALUE) != 0) {
                c02171.label -= Integer.MIN_VALUE;
            } else {
                c02171 = new C02171(continuation);
            }
        } else {
            c02171 = new C02171(continuation);
        }
        Object $result = c02171.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (c02171.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    Ref.ObjectRef result2 = new Ref.ObjectRef();
                    result2.element = LongPressResult.Canceled.INSTANCE;
                    long longPressTimeoutMillis = $this$waitForLongPress.getViewConfiguration().getLongPressTimeoutMillis();
                    C02182 c02182 = new C02182(pass, result2, null);
                    c02171.L$0 = result2;
                    c02171.label = 1;
                    if ($this$waitForLongPress.withTimeout(longPressTimeoutMillis, c02182, c02171) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    result = result2;
                    break;
                    break;
                case 1:
                    result = (Ref.ObjectRef) c02171.L$0;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return result.element;
        } catch (PointerEventTimeoutCancellationException e) {
            return LongPressResult.Success.INSTANCE;
        }
    }

    public static /* synthetic */ Object waitForLongPress$default(AwaitPointerEventScope awaitPointerEventScope, PointerEventPass pointerEventPass, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            pointerEventPass = PointerEventPass.Main;
        }
        return waitForLongPress(awaitPointerEventScope, pointerEventPass, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForLongPress$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TapGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForLongPress$2", f = "TapGestureDetector.kt", i = {0, 1}, l = {386, 409}, m = "invokeSuspend", n = {"$this$withTimeout", "$this$withTimeout"}, s = {"L$0", "L$0"}, v = 1)
    static final class C02182 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PointerEventPass $pass;
        final /* synthetic */ Ref.ObjectRef<LongPressResult> $result;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02182(PointerEventPass pointerEventPass, Ref.ObjectRef<LongPressResult> objectRef, Continuation<? super C02182> continuation) {
            super(2, continuation);
            this.$pass = pointerEventPass;
            this.$result = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02182 c02182 = new C02182(this.$pass, this.$result, continuation);
            c02182.L$0 = obj;
            return c02182;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((C02182) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x0066  */
        /* JADX WARN: Code duplicated, block: B:18:0x007a A[LOOP:1: B:14:0x0064->B:18:0x007a, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:57:0x0078 A[SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r2v10, types: [T, androidx.compose.foundation.gestures.LongPressResult$Canceled] */
        /* JADX WARN: Type inference failed for: r2v12, types: [T, androidx.compose.foundation.gestures.LongPressResult$Success] */
        /* JADX WARN: Type inference failed for: r2v21, types: [T, androidx.compose.foundation.gestures.LongPressResult$Canceled] */
        /* JADX WARN: Type inference failed for: r4v4, types: [T, androidx.compose.foundation.gestures.LongPressResult$Released] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x010a -> B:43:0x010d). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r20) {
            /*
                Method dump skipped, instruction units count: 342
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.C02182.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final void setDetectTapGesturesEnableNewDispatchingBehavior(boolean value) {
        ComposeFoundationFlags composeFoundationFlags = ComposeFoundationFlags.INSTANCE;
        ComposeFoundationFlags.isDetectTapGesturesImmediateCoroutineDispatchEnabled = value;
    }

    public static final boolean getDetectTapGesturesEnableNewDispatchingBehavior() {
        return ComposeFoundationFlags.isDetectTapGesturesImmediateCoroutineDispatchEnabled;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CoroutineStart getCoroutineStartForCurrentDispatchBehavior() {
        if (ComposeFoundationFlags.isDetectTapGesturesImmediateCoroutineDispatchEnabled) {
            return CoroutineStart.UNDISPATCHED;
        }
        return CoroutineStart.DEFAULT;
    }

    static /* synthetic */ Job launchAwaitingReset$default(CoroutineScope coroutineScope, Job job, CoroutineStart coroutineStart, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            coroutineStart = getCoroutineStartForCurrentDispatchBehavior();
        }
        return launchAwaitingReset(coroutineScope, job, coroutineStart, function2);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$launchAwaitingReset$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TapGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$launchAwaitingReset$1", f = "TapGestureDetector.kt", i = {0}, l = {498, LogSeverity.ERROR_VALUE}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"}, v = 1)
    static final class C02161 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<CoroutineScope, Continuation<? super Unit>, Object> $block;
        final /* synthetic */ Job $resetJob;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C02161(Job job, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super C02161> continuation) {
            super(2, continuation);
            this.$resetJob = job;
            this.$block = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02161 c02161 = new C02161(this.$resetJob, this.$block, continuation);
            c02161.L$0 = obj;
            return c02161;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02161) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:16:0x0049 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            CoroutineScope $this$launch;
            Function2<CoroutineScope, Continuation<? super Unit>, Object> function2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$launch = (CoroutineScope) this.L$0;
                    if (ComposeFoundationFlags.isDetectTapGesturesImmediateCoroutineDispatchEnabled) {
                        this.L$0 = $this$launch;
                        this.label = 1;
                        if (this.$resetJob.join(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    function2 = this.$block;
                    this.L$0 = null;
                    this.label = 2;
                    if (function2.invoke($this$launch, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 1:
                    $this$launch = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    function2 = this.$block;
                    this.L$0 = null;
                    this.label = 2;
                    if (function2.invoke($this$launch, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                case 2:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    private static final Job launchAwaitingReset(CoroutineScope $this$launchAwaitingReset, Job resetJob, CoroutineStart start, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return BuildersKt__Builders_commonKt.launch$default($this$launchAwaitingReset, null, start, new C02161(resetJob, function2, null), 1, null);
    }
}
