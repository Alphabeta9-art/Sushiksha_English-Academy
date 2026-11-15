package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: DragGestureDetector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aV\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000426\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0004\b\r\u0010\u000e\u001a0\u0010\u000f\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f0\u0012H\u0086@¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0080\u0001\u0010\u0018\u001a\u00020\f*\u00020\u00192\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\f0\u00122\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u001c2\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u001c26\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0002\u0010\u001f\u001aè\u0001\u0010\u0018\u001a\u00020\f*\u00020\u00192\b\u0010 \u001a\u0004\u0018\u00010!2M\b\u0002\u0010\u001a\u001aG\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(#\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\f0\"2#\b\u0002\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\f0\u00122\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u001c2\u000e\b\u0002\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00100\u001c26\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0002\u0010'\u001a\u0080\u0001\u0010(\u001a\u00020\f*\u00020\u00192\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\f0\u00122\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u001c2\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u001c26\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0002\u0010\u001f\u001aV\u0010)\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000426\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0004\b+\u0010\u000e\u001a^\u0010,\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010-\u001a\u00020.26\u0010/\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0004\b0\u00101\u001a0\u00102\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f0\u0012H\u0086@¢\u0006\u0004\b3\u0010\u0014\u001a\u001e\u00104\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0004\b5\u0010\u0017\u001a\u0080\u0001\u00106\u001a\u00020\f*\u00020\u00192\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\f0\u00122\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u001c2\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u001c26\u00107\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0002\u0010\u001f\u001aV\u00108\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000426\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0004\b9\u0010\u000e\u001a^\u0010:\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010-\u001a\u00020.26\u0010/\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0004\b;\u00101\u001a0\u0010<\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f0\u0012H\u0086@¢\u0006\u0004\b=\u0010\u0014\u001a\u001e\u0010>\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0004\b?\u0010\u0017\u001a\u0080\u0001\u0010@\u001a\u00020\f*\u00020\u00192\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\f0\u00122\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u001c2\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u001c26\u0010A\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0002\u0010\u001f\u001aP\u0010\u000f\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f0\u00122\b\u0010B\u001a\u0004\u0018\u00010!2\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00100\u0012H\u0080H¢\u0006\u0004\bD\u0010E\u001a2\u0010F\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00100\u0012H\u0082H¢\u0006\u0004\bH\u0010\u0014\u001aT\u0010I\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010-\u001a\u00020.2\b\u0010B\u001a\u0004\u0018\u00010!2\b\b\u0002\u0010J\u001a\u00020\n2\u0018\u0010/\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\f0\u0006H\u0080H¢\u0006\u0004\bK\u0010L\u001a$\u0010M\u001a\u00020\u0010*\u00020\u00022\u0006\u0010J\u001a\u00020\u00012\b\b\u0002\u0010N\u001a\u00020OH\u0080@¢\u0006\u0002\u0010P\u001a\u001e\u0010Q\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0004\bR\u0010\u0017\u001a\u001b\u0010S\u001a\u00020\u0010*\u00020T2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¢\u0006\u0004\bU\u0010V\u001a\u001b\u0010\\\u001a\u00020**\u00020]2\u0006\u0010-\u001a\u00020.H\u0000¢\u0006\u0004\b^\u0010_\"\u0010\u0010W\u001a\u00020XX\u0082\u0004¢\u0006\u0004\n\u0002\u0010Y\"\u0010\u0010Z\u001a\u00020XX\u0082\u0004¢\u0006\u0004\n\u0002\u0010Y\"\u000e\u0010[\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006`"}, d2 = {"awaitTouchSlopOrCancellation", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "onTouchSlopReached", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "change", "Landroidx/compose/ui/geometry/Offset;", "overSlop", "", "awaitTouchSlopOrCancellation-jO51t88", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "drag", "", "onDrag", "Lkotlin/Function1;", "drag-jO51t88", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitDragOrCancellation", "awaitDragOrCancellation-rnUCldI", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectDragGestures", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "onDragStart", "onDragEnd", "Lkotlin/Function0;", "onDragCancel", "dragAmount", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "orientationLock", "Landroidx/compose/foundation/gestures/Orientation;", "Lkotlin/Function3;", "down", "slopTriggerChange", "overSlopOffset", "shouldAwaitTouchSlop", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/gestures/Orientation;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectDragGesturesAfterLongPress", "awaitVerticalTouchSlopOrCancellation", "", "awaitVerticalTouchSlopOrCancellation-jO51t88", "awaitVerticalPointerSlopOrCancellation", "pointerType", "Landroidx/compose/ui/input/pointer/PointerType;", "onPointerSlopReached", "awaitVerticalPointerSlopOrCancellation-gDDlDlE", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verticalDrag", "verticalDrag-jO51t88", "awaitVerticalDragOrCancellation", "awaitVerticalDragOrCancellation-rnUCldI", "detectVerticalDragGestures", "onVerticalDrag", "awaitHorizontalTouchSlopOrCancellation", "awaitHorizontalTouchSlopOrCancellation-jO51t88", "awaitHorizontalPointerSlopOrCancellation", "awaitHorizontalPointerSlopOrCancellation-gDDlDlE", "horizontalDrag", "horizontalDrag-jO51t88", "awaitHorizontalDragOrCancellation", "awaitHorizontalDragOrCancellation-rnUCldI", "detectHorizontalDragGestures", "onHorizontalDrag", "orientation", "motionConsumed", "drag-VnAYq1g", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/jvm/functions/Function1;Landroidx/compose/foundation/gestures/Orientation;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitDragOrUp", "hasDragged", "awaitDragOrUp-jO51t88", "awaitPointerSlopOrCancellation", "initialPositionChange", "awaitPointerSlopOrCancellation-6ksA65w", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILandroidx/compose/foundation/gestures/Orientation;JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitAllPointersUpWithSlopDetection", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/ui/input/pointer/PointerInputChange;Landroidx/compose/ui/input/pointer/PointerEventPass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitLongPressOrCancellation", "awaitLongPressOrCancellation-rnUCldI", "isPointerUp", "Landroidx/compose/ui/input/pointer/PointerEvent;", "isPointerUp-DmW0f2w", "(Landroidx/compose/ui/input/pointer/PointerEvent;J)Z", "mouseSlop", "Landroidx/compose/ui/unit/Dp;", "F", "defaultTouchSlop", "mouseToTouchSlopRatio", "pointerSlop", "Landroidx/compose/ui/platform/ViewConfiguration;", "pointerSlop-E8SPZFQ", "(Landroidx/compose/ui/platform/ViewConfiguration;I)F", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DragGestureDetectorKt {
    private static final float mouseSlop = Dp.m7905constructorimpl((float) 0.125d);
    private static final float defaultTouchSlop = Dp.m7905constructorimpl(18);
    private static final float mouseToTouchSlopRatio = mouseSlop / defaultTouchSlop;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitAllPointersUpWithSlopDetection$1, reason: invalid class name */
    /* JADX INFO: compiled from: DragGestureDetector.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt", f = "DragGestureDetector.kt", i = {0, 0, 0, 0, 0, 0}, l = {897}, m = "awaitAllPointersUpWithSlopDetection", n = {"$this$awaitAllPointersUpWithSlopDetection", "pass", "pointer", "touchSlopDetector", "pointerSlopReached", "touchSlop"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "F$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DragGestureDetectorKt.awaitAllPointersUpWithSlopDetection(null, null, null, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:26:0x0108 A[LOOP:0: B:23:0x00e5->B:26:0x0108, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:67:0x011b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x01d8 -> B:60:0x01e1). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    public static final java.lang.Object m468awaitTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super androidx.compose.ui.geometry.Offset, kotlin.Unit> r25, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r26) {
        /*
            Method dump skipped, instruction units count: 508
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m468awaitTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:16:0x004e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:17:0x004f  */
    /* JADX WARN: Code duplicated, block: B:22:0x005c  */
    /* JADX WARN: Code duplicated, block: B:24:0x0062  */
    /* JADX WARN: Code duplicated, block: B:26:0x0067  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x004f -> B:18:0x0052). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: drag-jO51t88, reason: not valid java name */
    public static final java.lang.Object m474dragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r8, long r9, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r11, kotlin.coroutines.Continuation<? super java.lang.Boolean> r12) {
        /*
            boolean r0 = r12 instanceof androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1
            if (r0 == 0) goto L14
            r0 = r12
            androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1 r0 = (androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1 r0 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1
            r0.<init>(r12)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            switch(r3) {
                case 0: goto L3b;
                case 1: goto L2e;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2e:
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r9 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r9
            kotlin.ResultKt.throwOnFailure(r1)
            r10 = r1
            goto L52
        L3b:
            kotlin.ResultKt.throwOnFailure(r1)
            r5 = r9
            r9 = r8
            r8 = r11
        L41:
            r0.L$0 = r9
            r0.L$1 = r8
            r0.label = r4
            java.lang.Object r10 = m459awaitDragOrCancellationrnUCldI(r9, r5, r0)
            if (r10 != r2) goto L4f
            return r2
        L4f:
            r7 = r1
            r1 = r10
            r10 = r7
        L52:
            androidx.compose.ui.input.pointer.PointerInputChange r1 = (androidx.compose.ui.input.pointer.PointerInputChange) r1
            if (r1 != 0) goto L5c
            r11 = 0
            java.lang.Boolean r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r11)
            return r11
        L5c:
            boolean r11 = androidx.compose.ui.input.pointer.PointerEventKt.changedToUpIgnoreConsumed(r1)
            if (r11 == 0) goto L67
            java.lang.Boolean r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r11
        L67:
            r8.invoke(r1)
            long r5 = r1.getId()
            r1 = r10
            goto L41
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m474dragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:23:0x008b  */
    /* JADX WARN: Code duplicated, block: B:25:0x00aa A[LOOP:0: B:22:0x0089->B:25:0x00aa, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:57:0x00ba A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x006f -> B:21:0x0078). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitDragOrCancellation-rnUCldI, reason: not valid java name */
    public static final java.lang.Object m459awaitDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r21, long r22, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r24) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m459awaitDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object detectDragGestures$default(PointerInputScope pointerInputScope, Function1 function1, Function0 function0, Function0 function2, Function2 function3, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 2) != 0) {
            function0 = new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 4) != 0) {
            function2 = new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        Function0 function4 = function2;
        return detectDragGestures(pointerInputScope, function1, function0, function4, function3, continuation);
    }

    public static final Object detectDragGestures(PointerInputScope $this$detectDragGestures, final Function1<? super Offset, Unit> function1, final Function0<Unit> function0, Function0<Unit> function2, Function2<? super PointerInputChange, ? super Offset, Unit> function3, Continuation<? super Unit> continuation) {
        Object objDetectDragGestures = detectDragGestures($this$detectDragGestures, null, new Function3() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return DragGestureDetectorKt.detectDragGestures$lambda$3(function1, (PointerInputChange) obj, (PointerInputChange) obj2, (Offset) obj3);
            }
        }, new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DragGestureDetectorKt.detectDragGestures$lambda$4(function0, (PointerInputChange) obj);
            }
        }, function2, new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(DragGestureDetectorKt.detectDragGestures$lambda$5());
            }
        }, function3, continuation);
        return objDetectDragGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectDragGestures : Unit.INSTANCE;
    }

    static final Unit detectDragGestures$lambda$3(Function1 $onDragStart, PointerInputChange pointerInputChange, PointerInputChange slopTriggerChange, Offset offset) {
        $onDragStart.invoke(Offset.m4841boximpl(slopTriggerChange.getPosition()));
        return Unit.INSTANCE;
    }

    static final Unit detectDragGestures$lambda$4(Function0 $onDragEnd, PointerInputChange it) {
        $onDragEnd.invoke();
        return Unit.INSTANCE;
    }

    static final boolean detectDragGestures$lambda$5() {
        return true;
    }

    static final boolean detectDragGestures$lambda$9() {
        return true;
    }

    public static final Object detectDragGestures(PointerInputScope $this$detectDragGestures, Orientation orientationLock, Function3<? super PointerInputChange, ? super PointerInputChange, ? super Offset, Unit> function3, Function1<? super PointerInputChange, Unit> function1, Function0<Unit> function0, Function0<Boolean> function2, Function2<? super PointerInputChange, ? super Offset, Unit> function4, Continuation<? super Unit> continuation) {
        Ref.LongRef overSlop = new Ref.LongRef();
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$detectDragGestures, new AnonymousClass13(function2, overSlop, orientationLock, function3, function4, function0, function1, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$13, reason: invalid class name */
    /* JADX INFO: compiled from: DragGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$13", f = "DragGestureDetector.kt", i = {0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7}, l = {248, 254, 1116, 1158, 279, 1197, 1240, 1252}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "initialDown", "awaitTouchSlop", "$this$awaitEachGesture", "down", "$this$awaitPointerSlopOrCancellation_u2d6ksA65w_u24default$iv", "pointer$iv", "touchSlopDetector$iv", "touchSlop$iv", "$this$awaitEachGesture", "down", "$this$awaitPointerSlopOrCancellation_u2d6ksA65w_u24default$iv", "pointer$iv", "touchSlopDetector$iv", "dragEvent$iv", "touchSlop$iv", "$this$awaitEachGesture", "down", "drag", "$this$awaitEachGesture", "down", "$this$awaitPointerSlopOrCancellation_u2d6ksA65w$iv", "pointer$iv", "touchSlopDetector$iv", "touchSlop$iv", "$this$awaitEachGesture", "down", "$this$awaitPointerSlopOrCancellation_u2d6ksA65w$iv", "pointer$iv", "touchSlopDetector$iv", "dragEvent$iv", "touchSlop$iv", "$this$drag_u2dVnAYq1g$iv", "$this$awaitDragOrUp_u2djO51t88$iv$iv", "pointer$iv$iv"}, s = {"L$0", "L$0", "L$1", "Z$0", "L$0", "L$1", "L$2", "L$4", "L$5", "F$0", "L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "F$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$4", "L$5", "F$0", "L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "F$0", "L$0", "L$2", "L$3"}, v = 1)
    static final class AnonymousClass13 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<PointerInputChange, Offset, Unit> $onDrag;
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function1<PointerInputChange, Unit> $onDragEnd;
        final /* synthetic */ Function3<PointerInputChange, PointerInputChange, Offset, Unit> $onDragStart;
        final /* synthetic */ Orientation $orientationLock;
        final /* synthetic */ Ref.LongRef $overSlop;
        final /* synthetic */ Function0<Boolean> $shouldAwaitTouchSlop;
        float F$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        boolean Z$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass13(Function0<Boolean> function0, Ref.LongRef longRef, Orientation orientation, Function3<? super PointerInputChange, ? super PointerInputChange, ? super Offset, Unit> function3, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Function0<Unit> function1, Function1<? super PointerInputChange, Unit> function4, Continuation<? super AnonymousClass13> continuation) {
            super(2, continuation);
            this.$shouldAwaitTouchSlop = function0;
            this.$overSlop = longRef;
            this.$orientationLock = orientation;
            this.$onDragStart = function3;
            this.$onDrag = function2;
            this.$onDragCancel = function1;
            this.$onDragEnd = function4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass13 anonymousClass13 = new AnonymousClass13(this.$shouldAwaitTouchSlop, this.$overSlop, this.$orientationLock, this.$onDragStart, this.$onDrag, this.$onDragCancel, this.$onDragEnd, continuation);
            anonymousClass13.L$0 = obj;
            return anonymousClass13;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass13) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:257:0x0296 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:38:0x025d  */
        /* JADX WARN: Code duplicated, block: B:40:0x0280 A[LOOP:8: B:37:0x025b->B:40:0x0280, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:78:0x037a A[PHI: r2 r3 r4 r11 r16 r18 r22
  0x037a: PHI (r2v31 androidx.compose.ui.input.pointer.PointerInputChange) = 
  (r2v17 androidx.compose.ui.input.pointer.PointerInputChange)
  (r2v22 androidx.compose.ui.input.pointer.PointerInputChange)
  (r2v23 androidx.compose.ui.input.pointer.PointerInputChange)
  (r2v24 androidx.compose.ui.input.pointer.PointerInputChange)
  (r2v30 androidx.compose.ui.input.pointer.PointerInputChange)
  (r2v32 androidx.compose.ui.input.pointer.PointerInputChange)
 binds: [B:67:0x032a, B:58:0x02e9, B:47:0x02ab, B:44:0x029a, B:29:0x0203, B:76:0x0376] A[DONT_GENERATE, DONT_INLINE]
  0x037a: PHI (r3v29 java.lang.Object) = 
  (r3v19 java.lang.Object)
  (r3v23 java.lang.Object)
  (r3v24 java.lang.Object)
  (r3v25 java.lang.Object)
  (r3v28 java.lang.Object)
  (r3v30 java.lang.Object)
 binds: [B:67:0x032a, B:58:0x02e9, B:47:0x02ab, B:44:0x029a, B:29:0x0203, B:76:0x0376] A[DONT_GENERATE, DONT_INLINE]
  0x037a: PHI (r4v25 androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$13) = 
  (r4v17 androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$13)
  (r4v19 androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$13)
  (r4v20 androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$13)
  (r4v21 androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$13)
  (r4v24 androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$13)
  (r4v26 androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$13)
 binds: [B:67:0x032a, B:58:0x02e9, B:47:0x02ab, B:44:0x029a, B:29:0x0203, B:76:0x0376] A[DONT_GENERATE, DONT_INLINE]
  0x037a: PHI (r11v17 ??) = (r11v6 ??), (r11v10 ??), (r11v12 ??), (r11v13 ??), (r11v54 ??), (r11v20 ??) binds: [B:67:0x032a, B:58:0x02e9, B:47:0x02ab, B:44:0x029a, B:29:0x0203, B:76:0x0376] A[DONT_GENERATE, DONT_INLINE]
  0x037a: PHI (r16v9 long) = (r16v6 long), (r16v6 long), (r16v6 long), (r16v6 long), (r16v8 long), (r16v10 long) binds: [B:67:0x032a, B:58:0x02e9, B:47:0x02ab, B:44:0x029a, B:29:0x0203, B:76:0x0376] A[DONT_GENERATE, DONT_INLINE]
  0x037a: PHI (r18v9 long) = (r18v6 long), (r18v6 long), (r18v6 long), (r18v6 long), (r18v8 long), (r18v10 long) binds: [B:67:0x032a, B:58:0x02e9, B:47:0x02ab, B:44:0x029a, B:29:0x0203, B:76:0x0376] A[DONT_GENERATE, DONT_INLINE]
  0x037a: PHI (r22v11 androidx.compose.ui.input.pointer.AwaitPointerEventScope) = 
  (r22v5 androidx.compose.ui.input.pointer.AwaitPointerEventScope)
  (r22v6 androidx.compose.ui.input.pointer.AwaitPointerEventScope)
  (r22v8 androidx.compose.ui.input.pointer.AwaitPointerEventScope)
  (r22v9 androidx.compose.ui.input.pointer.AwaitPointerEventScope)
  (r22v10 androidx.compose.ui.input.pointer.AwaitPointerEventScope)
  (r22v12 androidx.compose.ui.input.pointer.AwaitPointerEventScope)
 binds: [B:67:0x032a, B:58:0x02e9, B:47:0x02ab, B:44:0x029a, B:29:0x0203, B:76:0x0376] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r11v0 */
        /* JADX WARN: Type inference failed for: r11v1 */
        /* JADX WARN: Type inference failed for: r11v10 */
        /* JADX WARN: Type inference failed for: r11v12 */
        /* JADX WARN: Type inference failed for: r11v13 */
        /* JADX WARN: Type inference failed for: r11v15, types: [androidx.compose.ui.input.pointer.PointerEventPass, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r11v16, types: [kotlin.jvm.internal.DefaultConstructorMarker] */
        /* JADX WARN: Type inference failed for: r11v17, types: [androidx.compose.ui.input.pointer.PointerInputChange] */
        /* JADX WARN: Type inference failed for: r11v18 */
        /* JADX WARN: Type inference failed for: r11v19 */
        /* JADX WARN: Type inference failed for: r11v20 */
        /* JADX WARN: Type inference failed for: r11v37 */
        /* JADX WARN: Type inference failed for: r11v44, types: [androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r11v46 */
        /* JADX WARN: Type inference failed for: r11v5 */
        /* JADX WARN: Type inference failed for: r11v54 */
        /* JADX WARN: Type inference failed for: r11v6 */
        /* JADX WARN: Type inference failed for: r11v9 */
        /* JADX WARN: Type inference failed for: r35v2 */
        /* JADX WARN: Type inference failed for: r3v62 */
        /* JADX WARN: Type inference failed for: r3v63, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v66 */
        /* JADX WARN: Type inference failed for: r4v31 */
        /* JADX WARN: Type inference failed for: r4v44 */
        /* JADX WARN: Type inference failed for: r4v54 */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:114:0x0447 -> B:94:0x03c4). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:129:0x04b4 -> B:176:0x063c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:143:0x054e -> B:176:0x063c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:146:0x0561 -> B:176:0x063c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:157:0x05a4 -> B:176:0x063c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:166:0x05e9 -> B:176:0x063c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:172:0x0627 -> B:173:0x0631). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:178:0x0648 -> B:84:0x038e). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:188:0x06ba -> B:189:0x06c5). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0203 -> B:78:0x037a). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x029a -> B:78:0x037a). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x02ab -> B:78:0x037a). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x02e9 -> B:78:0x037a). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:67:0x032a -> B:78:0x037a). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:73:0x0366 -> B:74:0x0370). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r38) {
            /*
                Method dump skipped, instruction units count: 2074
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.AnonymousClass13.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static /* synthetic */ Object detectDragGesturesAfterLongPress$default(PointerInputScope pointerInputScope, Function1 function1, Function0 function0, Function0 function2, Function2 function3, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 2) != 0) {
            function0 = new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 4) != 0) {
            function2 = new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        Function0 function4 = function2;
        return detectDragGesturesAfterLongPress(pointerInputScope, function1, function0, function4, function3, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5, reason: invalid class name */
    /* JADX INFO: compiled from: DragGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5", f = "DragGestureDetector.kt", i = {0, 1, 2}, l = {359, 360, 365}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "$this$awaitEachGesture"}, s = {"L$0", "L$0", "L$0"}, v = 1)
    static final class AnonymousClass5 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<PointerInputChange, Offset, Unit> $onDrag;
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function0<Unit> $onDragEnd;
        final /* synthetic */ Function1<Offset, Unit> $onDragStart;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass5(Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function2, Function2<? super PointerInputChange, ? super Offset, Unit> function3, Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
            this.$onDragStart = function1;
            this.$onDragEnd = function0;
            this.$onDragCancel = function2;
            this.$onDrag = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass5 anonymousClass5 = new AnonymousClass5(this.$onDragStart, this.$onDragEnd, this.$onDragCancel, this.$onDrag, continuation);
            anonymousClass5.L$0 = obj;
            return anonymousClass5;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass5) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:22:0x0063  */
        /* JADX WARN: Code duplicated, block: B:23:0x0064  */
        /* JADX WARN: Code duplicated, block: B:26:0x0069 A[Catch: CancellationException -> 0x00d2, TryCatch #0 {CancellationException -> 0x00d2, blocks: (B:30:0x0093, B:32:0x009b, B:34:0x00ae, B:36:0x00bc, B:37:0x00bf, B:38:0x00c3, B:39:0x00ca, B:24:0x0065, B:26:0x0069, B:20:0x004f), top: B:47:0x004f }] */
        /* JADX WARN: Code duplicated, block: B:29:0x0091  */
        /* JADX WARN: Code duplicated, block: B:32:0x009b A[Catch: CancellationException -> 0x00d2, TryCatch #0 {CancellationException -> 0x00d2, blocks: (B:30:0x0093, B:32:0x009b, B:34:0x00ae, B:36:0x00bc, B:37:0x00bf, B:38:0x00c3, B:39:0x00ca, B:24:0x0065, B:26:0x0069, B:20:0x004f), top: B:47:0x004f }] */
        /* JADX WARN: Code duplicated, block: B:34:0x00ae A[Catch: CancellationException -> 0x00d2, TryCatch #0 {CancellationException -> 0x00d2, blocks: (B:30:0x0093, B:32:0x009b, B:34:0x00ae, B:36:0x00bc, B:37:0x00bf, B:38:0x00c3, B:39:0x00ca, B:24:0x0065, B:26:0x0069, B:20:0x004f), top: B:47:0x004f }] */
        /* JADX WARN: Code duplicated, block: B:36:0x00bc A[Catch: CancellationException -> 0x00d2, TryCatch #0 {CancellationException -> 0x00d2, blocks: (B:30:0x0093, B:32:0x009b, B:34:0x00ae, B:36:0x00bc, B:37:0x00bf, B:38:0x00c3, B:39:0x00ca, B:24:0x0065, B:26:0x0069, B:20:0x004f), top: B:47:0x004f }] */
        /* JADX WARN: Code duplicated, block: B:39:0x00ca A[Catch: CancellationException -> 0x00d2, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x00d2, blocks: (B:30:0x0093, B:32:0x009b, B:34:0x00ae, B:36:0x00bc, B:37:0x00bf, B:38:0x00c3, B:39:0x00ca, B:24:0x0065, B:26:0x0069, B:20:0x004f), top: B:47:0x004f }] */
        /* JADX WARN: Code duplicated, block: B:51:0x00bf A[SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AwaitPointerEventScope $this$awaitEachGesture;
            Object objM464awaitLongPressOrCancellationrnUCldI;
            PointerInputChange drag;
            Object objM474dragjO51t88;
            AwaitPointerEventScope $this$awaitEachGesture2;
            List<PointerInputChange> changes;
            int index$iv;
            int size;
            PointerInputChange it;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                        this.L$0 = $this$awaitEachGesture;
                        this.label = 1;
                        Object objAwaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture, false, null, this, 2, null);
                        if (objAwaitFirstDown$default == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objAwaitFirstDown$default;
                        try {
                            PointerInputChange down = (PointerInputChange) $result;
                            this.L$0 = $this$awaitEachGesture;
                            this.label = 2;
                            objM464awaitLongPressOrCancellationrnUCldI = DragGestureDetectorKt.m464awaitLongPressOrCancellationrnUCldI($this$awaitEachGesture, down.getId(), this);
                            if (objM464awaitLongPressOrCancellationrnUCldI == coroutine_suspended) {
                                $result = objM464awaitLongPressOrCancellationrnUCldI;
                                drag = (PointerInputChange) $result;
                                if (drag != null) {
                                    this.$onDragStart.invoke(Offset.m4841boximpl(drag.getPosition()));
                                    long id = drag.getId();
                                    final Function2<PointerInputChange, Offset, Unit> function2 = this.$onDrag;
                                    this.L$0 = $this$awaitEachGesture;
                                    this.label = 3;
                                    objM474dragjO51t88 = DragGestureDetectorKt.m474dragjO51t88($this$awaitEachGesture, id, new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5$$ExternalSyntheticLambda0
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            return DragGestureDetectorKt.AnonymousClass5.invokeSuspend$lambda$0(function2, (PointerInputChange) obj);
                                        }
                                    }, this);
                                    if (objM474dragjO51t88 != coroutine_suspended) {
                                        $this$awaitEachGesture2 = $this$awaitEachGesture;
                                        $result = objM474dragjO51t88;
                                        if (((Boolean) $result).booleanValue()) {
                                            changes = $this$awaitEachGesture2.getCurrentEvent().getChanges();
                                            size = changes.size();
                                            for (index$iv = 0; index$iv < size; index$iv++) {
                                                Object item$iv = changes.get(index$iv);
                                                it = (PointerInputChange) item$iv;
                                                if (PointerEventKt.changedToUp(it)) {
                                                    it.consume();
                                                }
                                            }
                                            this.$onDragEnd.invoke();
                                        } else {
                                            this.$onDragCancel.invoke();
                                        }
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                            return coroutine_suspended;
                        } catch (CancellationException e) {
                            c = e;
                            this.$onDragCancel.invoke();
                            throw c;
                        }
                    case 1:
                        AwaitPointerEventScope $this$awaitEachGesture3 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure($result);
                        $this$awaitEachGesture = $this$awaitEachGesture3;
                        PointerInputChange down2 = (PointerInputChange) $result;
                        this.L$0 = $this$awaitEachGesture;
                        this.label = 2;
                        objM464awaitLongPressOrCancellationrnUCldI = DragGestureDetectorKt.m464awaitLongPressOrCancellationrnUCldI($this$awaitEachGesture, down2.getId(), this);
                        if (objM464awaitLongPressOrCancellationrnUCldI == coroutine_suspended) {
                            $result = objM464awaitLongPressOrCancellationrnUCldI;
                            drag = (PointerInputChange) $result;
                            if (drag != null) {
                                this.$onDragStart.invoke(Offset.m4841boximpl(drag.getPosition()));
                                long id2 = drag.getId();
                                final Function2 function3 = this.$onDrag;
                                this.L$0 = $this$awaitEachGesture;
                                this.label = 3;
                                objM474dragjO51t88 = DragGestureDetectorKt.m474dragjO51t88($this$awaitEachGesture, id2, new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5$$ExternalSyntheticLambda0
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return DragGestureDetectorKt.AnonymousClass5.invokeSuspend$lambda$0(function3, (PointerInputChange) obj);
                                    }
                                }, this);
                                if (objM474dragjO51t88 != coroutine_suspended) {
                                    $this$awaitEachGesture2 = $this$awaitEachGesture;
                                    $result = objM474dragjO51t88;
                                    if (((Boolean) $result).booleanValue()) {
                                        changes = $this$awaitEachGesture2.getCurrentEvent().getChanges();
                                        size = changes.size();
                                        while (index$iv < size) {
                                            Object item$iv2 = changes.get(index$iv);
                                            it = (PointerInputChange) item$iv2;
                                            if (PointerEventKt.changedToUp(it)) {
                                                it.consume();
                                            }
                                        }
                                        this.$onDragEnd.invoke();
                                    } else {
                                        this.$onDragCancel.invoke();
                                    }
                                }
                            }
                            return Unit.INSTANCE;
                        }
                        return coroutine_suspended;
                    case 2:
                        AwaitPointerEventScope $this$awaitEachGesture4 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure($result);
                        $this$awaitEachGesture = $this$awaitEachGesture4;
                        drag = (PointerInputChange) $result;
                        if (drag != null) {
                            this.$onDragStart.invoke(Offset.m4841boximpl(drag.getPosition()));
                            long id3 = drag.getId();
                            final Function2 function4 = this.$onDrag;
                            this.L$0 = $this$awaitEachGesture;
                            this.label = 3;
                            objM474dragjO51t88 = DragGestureDetectorKt.m474dragjO51t88($this$awaitEachGesture, id3, new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return DragGestureDetectorKt.AnonymousClass5.invokeSuspend$lambda$0(function4, (PointerInputChange) obj);
                                }
                            }, this);
                            if (objM474dragjO51t88 != coroutine_suspended) {
                                $this$awaitEachGesture2 = $this$awaitEachGesture;
                                $result = objM474dragjO51t88;
                                if (((Boolean) $result).booleanValue()) {
                                    changes = $this$awaitEachGesture2.getCurrentEvent().getChanges();
                                    size = changes.size();
                                    while (index$iv < size) {
                                        Object item$iv3 = changes.get(index$iv);
                                        it = (PointerInputChange) item$iv3;
                                        if (PointerEventKt.changedToUp(it)) {
                                            it.consume();
                                        }
                                    }
                                    this.$onDragEnd.invoke();
                                } else {
                                    this.$onDragCancel.invoke();
                                }
                            }
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    case 3:
                        $this$awaitEachGesture2 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure($result);
                        if (((Boolean) $result).booleanValue()) {
                            changes = $this$awaitEachGesture2.getCurrentEvent().getChanges();
                            size = changes.size();
                            while (index$iv < size) {
                                Object item$iv4 = changes.get(index$iv);
                                it = (PointerInputChange) item$iv4;
                                if (PointerEventKt.changedToUp(it)) {
                                    it.consume();
                                }
                            }
                            this.$onDragEnd.invoke();
                        } else {
                            this.$onDragCancel.invoke();
                        }
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (CancellationException e2) {
                c = e2;
            }
        }

        static final Unit invokeSuspend$lambda$0(Function2 $onDrag, PointerInputChange it) {
            $onDrag.invoke(it, Offset.m4841boximpl(PointerEventKt.positionChange(it)));
            it.consume();
            return Unit.INSTANCE;
        }
    }

    public static final Object detectDragGesturesAfterLongPress(PointerInputScope $this$detectDragGesturesAfterLongPress, Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function2, Function2<? super PointerInputChange, ? super Offset, Unit> function3, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$detectDragGesturesAfterLongPress, new AnonymousClass5(function1, function0, function2, function3, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:26:0x010a A[LOOP:0: B:23:0x00e7->B:26:0x010a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:67:0x011d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x01f4 -> B:60:0x01fd). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitVerticalTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    public static final java.lang.Object m471awaitVerticalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r25, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r26) {
        /*
            Method dump skipped, instruction units count: 536
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m471awaitVerticalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:26:0x0107 A[LOOP:0: B:23:0x00e2->B:26:0x0107, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:67:0x0119 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x01fc -> B:60:0x0204). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitVerticalPointerSlopOrCancellation-gDDlDlE, reason: not valid java name */
    public static final java.lang.Object m470awaitVerticalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope r26, long r27, int r29, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r30, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r31) {
        /*
            Method dump skipped, instruction units count: 540
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m470awaitVerticalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:26:0x00dc A[LOOP:0: B:23:0x00b9->B:26:0x00dc, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:71:0x00ef A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0095 -> B:22:0x00a2). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: verticalDrag-jO51t88, reason: not valid java name */
    public static final java.lang.Object m478verticalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r25, long r26, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r28, kotlin.coroutines.Continuation<? super java.lang.Boolean> r29) {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m478verticalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:23:0x008b  */
    /* JADX WARN: Code duplicated, block: B:25:0x00aa A[LOOP:0: B:22:0x0089->B:25:0x00aa, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:59:0x00ba A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x006f -> B:21:0x0078). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitVerticalDragOrCancellation-rnUCldI, reason: not valid java name */
    public static final java.lang.Object m469awaitVerticalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r21, long r22, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r24) {
        /*
            Method dump skipped, instruction units count: 328
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m469awaitVerticalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object detectVerticalDragGestures$default(PointerInputScope pointerInputScope, Function1 function1, Function0 function0, Function0 function2, Function2 function3, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 2) != 0) {
            function0 = new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 4) != 0) {
            function2 = new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        Function0 function4 = function2;
        return detectVerticalDragGestures(pointerInputScope, function1, function0, function4, function3, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DragGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5", f = "DragGestureDetector.kt", i = {0, 1, 1}, l = {533, 536, 544}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "overSlop"}, s = {"L$0", "L$0", "L$1"}, v = 1)
    static final class C01935 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function0<Unit> $onDragEnd;
        final /* synthetic */ Function1<Offset, Unit> $onDragStart;
        final /* synthetic */ Function2<PointerInputChange, Float, Unit> $onVerticalDrag;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C01935(Function1<? super Offset, Unit> function1, Function2<? super PointerInputChange, ? super Float, Unit> function2, Function0<Unit> function0, Function0<Unit> function3, Continuation<? super C01935> continuation) {
            super(2, continuation);
            this.$onDragStart = function1;
            this.$onVerticalDrag = function2;
            this.$onDragEnd = function0;
            this.$onDragCancel = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01935 c01935 = new C01935(this.$onDragStart, this.$onVerticalDrag, this.$onDragEnd, this.$onDragCancel, continuation);
            c01935.L$0 = obj;
            return c01935;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((C01935) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x0073 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:18:0x0079  */
        /* JADX WARN: Code duplicated, block: B:20:0x00ae A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:23:0x00b7  */
        /* JADX WARN: Code duplicated, block: B:24:0x00bd  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AwaitPointerEventScope $this$awaitEachGesture;
            final Ref.FloatRef overSlop;
            PointerInputChange drag;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                    this.L$0 = $this$awaitEachGesture;
                    this.label = 1;
                    Object objAwaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture, false, null, this, 2, null);
                    if (objAwaitFirstDown$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objAwaitFirstDown$default;
                    PointerInputChange down = (PointerInputChange) $result;
                    overSlop = new Ref.FloatRef();
                    this.L$0 = $this$awaitEachGesture;
                    this.L$1 = overSlop;
                    this.label = 2;
                    $result = DragGestureDetectorKt.m470awaitVerticalPointerSlopOrCancellationgDDlDlE($this$awaitEachGesture, down.getId(), down.getType(), new Function2() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return DragGestureDetectorKt.C01935.invokeSuspend$lambda$0(overSlop, (PointerInputChange) obj, ((Float) obj2).floatValue());
                        }
                    }, this);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    drag = (PointerInputChange) $result;
                    if (drag != null) {
                        this.$onDragStart.invoke(Offset.m4841boximpl(drag.getPosition()));
                        this.$onVerticalDrag.invoke(drag, Boxing.boxFloat(overSlop.element));
                        long id = drag.getId();
                        final Function2<PointerInputChange, Float, Unit> function2 = this.$onVerticalDrag;
                        this.L$0 = null;
                        this.L$1 = null;
                        this.label = 3;
                        $result = DragGestureDetectorKt.m478verticalDragjO51t88($this$awaitEachGesture, id, new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return DragGestureDetectorKt.C01935.invokeSuspend$lambda$1(function2, (PointerInputChange) obj);
                            }
                        }, this);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (((Boolean) $result).booleanValue()) {
                            this.$onDragEnd.invoke();
                        } else {
                            this.$onDragCancel.invoke();
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    AwaitPointerEventScope $this$awaitEachGesture2 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    $this$awaitEachGesture = $this$awaitEachGesture2;
                    PointerInputChange down2 = (PointerInputChange) $result;
                    overSlop = new Ref.FloatRef();
                    this.L$0 = $this$awaitEachGesture;
                    this.L$1 = overSlop;
                    this.label = 2;
                    $result = DragGestureDetectorKt.m470awaitVerticalPointerSlopOrCancellationgDDlDlE($this$awaitEachGesture, down2.getId(), down2.getType(), new Function2() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return DragGestureDetectorKt.C01935.invokeSuspend$lambda$0(overSlop, (PointerInputChange) obj, ((Float) obj2).floatValue());
                        }
                    }, this);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    drag = (PointerInputChange) $result;
                    if (drag != null) {
                        this.$onDragStart.invoke(Offset.m4841boximpl(drag.getPosition()));
                        this.$onVerticalDrag.invoke(drag, Boxing.boxFloat(overSlop.element));
                        long id2 = drag.getId();
                        final Function2 function3 = this.$onVerticalDrag;
                        this.L$0 = null;
                        this.L$1 = null;
                        this.label = 3;
                        $result = DragGestureDetectorKt.m478verticalDragjO51t88($this$awaitEachGesture, id2, new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return DragGestureDetectorKt.C01935.invokeSuspend$lambda$1(function3, (PointerInputChange) obj);
                            }
                        }, this);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (((Boolean) $result).booleanValue()) {
                            this.$onDragEnd.invoke();
                        } else {
                            this.$onDragCancel.invoke();
                        }
                    }
                    return Unit.INSTANCE;
                case 2:
                    Ref.FloatRef overSlop2 = (Ref.FloatRef) this.L$1;
                    $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    overSlop = overSlop2;
                    drag = (PointerInputChange) $result;
                    if (drag != null) {
                        this.$onDragStart.invoke(Offset.m4841boximpl(drag.getPosition()));
                        this.$onVerticalDrag.invoke(drag, Boxing.boxFloat(overSlop.element));
                        long id3 = drag.getId();
                        final Function2 function4 = this.$onVerticalDrag;
                        this.L$0 = null;
                        this.L$1 = null;
                        this.label = 3;
                        $result = DragGestureDetectorKt.m478verticalDragjO51t88($this$awaitEachGesture, id3, new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return DragGestureDetectorKt.C01935.invokeSuspend$lambda$1(function4, (PointerInputChange) obj);
                            }
                        }, this);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (((Boolean) $result).booleanValue()) {
                            this.$onDragEnd.invoke();
                        } else {
                            this.$onDragCancel.invoke();
                        }
                    }
                    return Unit.INSTANCE;
                case 3:
                    ResultKt.throwOnFailure($result);
                    if (((Boolean) $result).booleanValue()) {
                        this.$onDragEnd.invoke();
                    } else {
                        this.$onDragCancel.invoke();
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        static final Unit invokeSuspend$lambda$0(Ref.FloatRef $overSlop, PointerInputChange change, float over) {
            change.consume();
            $overSlop.element = over;
            return Unit.INSTANCE;
        }

        static final Unit invokeSuspend$lambda$1(Function2 $onVerticalDrag, PointerInputChange it) {
            int bits$iv$iv$iv = (int) (4294967295L & PointerEventKt.positionChange(it));
            $onVerticalDrag.invoke(it, Float.valueOf(Float.intBitsToFloat(bits$iv$iv$iv)));
            it.consume();
            return Unit.INSTANCE;
        }
    }

    public static final Object detectVerticalDragGestures(PointerInputScope $this$detectVerticalDragGestures, Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function2, Function2<? super PointerInputChange, ? super Float, Unit> function3, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$detectVerticalDragGestures, new C01935(function1, function3, function0, function2, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:26:0x010a A[LOOP:0: B:23:0x00e7->B:26:0x010a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:67:0x011d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x01f1 -> B:60:0x01fa). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitHorizontalTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    public static final java.lang.Object m463awaitHorizontalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r25, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r26) {
        /*
            Method dump skipped, instruction units count: 532
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m463awaitHorizontalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:26:0x0107 A[LOOP:0: B:23:0x00e2->B:26:0x0107, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:67:0x0119 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x01f9 -> B:60:0x0201). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitHorizontalPointerSlopOrCancellation-gDDlDlE, reason: not valid java name */
    public static final java.lang.Object m462awaitHorizontalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope r24, long r25, int r27, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r28, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r29) {
        /*
            Method dump skipped, instruction units count: 536
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m462awaitHorizontalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:26:0x00dc A[LOOP:0: B:23:0x00b9->B:26:0x00dc, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:71:0x00ef A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0095 -> B:22:0x00a2). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: horizontalDrag-jO51t88, reason: not valid java name */
    public static final java.lang.Object m475horizontalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r25, long r26, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r28, kotlin.coroutines.Continuation<? super java.lang.Boolean> r29) {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m475horizontalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:23:0x008b  */
    /* JADX WARN: Code duplicated, block: B:25:0x00aa A[LOOP:0: B:22:0x0089->B:25:0x00aa, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:59:0x00ba A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x006f -> B:21:0x0078). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitHorizontalDragOrCancellation-rnUCldI, reason: not valid java name */
    public static final java.lang.Object m461awaitHorizontalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r21, long r22, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r24) {
        /*
            Method dump skipped, instruction units count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m461awaitHorizontalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object detectHorizontalDragGestures$default(PointerInputScope pointerInputScope, Function1 function1, Function0 function0, Function0 function2, Function2 function3, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 2) != 0) {
            function0 = new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 4) != 0) {
            function2 = new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        Function0 function4 = function2;
        return detectHorizontalDragGestures(pointerInputScope, function1, function0, function4, function3, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DragGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5", f = "DragGestureDetector.kt", i = {0, 1, 1}, l = {TypedValues.TransitionType.TYPE_TO, TypedValues.TransitionType.TYPE_INTERPOLATOR, 713}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "overSlop"}, s = {"L$0", "L$0", "L$1"}, v = 1)
    static final class C01925 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function0<Unit> $onDragEnd;
        final /* synthetic */ Function1<Offset, Unit> $onDragStart;
        final /* synthetic */ Function2<PointerInputChange, Float, Unit> $onHorizontalDrag;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C01925(Function1<? super Offset, Unit> function1, Function2<? super PointerInputChange, ? super Float, Unit> function2, Function0<Unit> function0, Function0<Unit> function3, Continuation<? super C01925> continuation) {
            super(2, continuation);
            this.$onDragStart = function1;
            this.$onHorizontalDrag = function2;
            this.$onDragEnd = function0;
            this.$onDragCancel = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01925 c01925 = new C01925(this.$onDragStart, this.$onHorizontalDrag, this.$onDragEnd, this.$onDragCancel, continuation);
            c01925.L$0 = obj;
            return c01925;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((C01925) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x0073 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:18:0x0079  */
        /* JADX WARN: Code duplicated, block: B:20:0x00ae A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:23:0x00b7  */
        /* JADX WARN: Code duplicated, block: B:24:0x00bd  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AwaitPointerEventScope $this$awaitEachGesture;
            final Ref.FloatRef overSlop;
            PointerInputChange drag;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                    this.L$0 = $this$awaitEachGesture;
                    this.label = 1;
                    Object objAwaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture, false, null, this, 2, null);
                    if (objAwaitFirstDown$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objAwaitFirstDown$default;
                    PointerInputChange down = (PointerInputChange) $result;
                    overSlop = new Ref.FloatRef();
                    this.L$0 = $this$awaitEachGesture;
                    this.L$1 = overSlop;
                    this.label = 2;
                    $result = DragGestureDetectorKt.m462awaitHorizontalPointerSlopOrCancellationgDDlDlE($this$awaitEachGesture, down.getId(), down.getType(), new Function2() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return DragGestureDetectorKt.C01925.invokeSuspend$lambda$0(overSlop, (PointerInputChange) obj, ((Float) obj2).floatValue());
                        }
                    }, this);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    drag = (PointerInputChange) $result;
                    if (drag != null) {
                        this.$onDragStart.invoke(Offset.m4841boximpl(drag.getPosition()));
                        this.$onHorizontalDrag.invoke(drag, Boxing.boxFloat(overSlop.element));
                        long id = drag.getId();
                        final Function2<PointerInputChange, Float, Unit> function2 = this.$onHorizontalDrag;
                        this.L$0 = null;
                        this.L$1 = null;
                        this.label = 3;
                        $result = DragGestureDetectorKt.m475horizontalDragjO51t88($this$awaitEachGesture, id, new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return DragGestureDetectorKt.C01925.invokeSuspend$lambda$1(function2, (PointerInputChange) obj);
                            }
                        }, this);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (((Boolean) $result).booleanValue()) {
                            this.$onDragEnd.invoke();
                        } else {
                            this.$onDragCancel.invoke();
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    AwaitPointerEventScope $this$awaitEachGesture2 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    $this$awaitEachGesture = $this$awaitEachGesture2;
                    PointerInputChange down2 = (PointerInputChange) $result;
                    overSlop = new Ref.FloatRef();
                    this.L$0 = $this$awaitEachGesture;
                    this.L$1 = overSlop;
                    this.label = 2;
                    $result = DragGestureDetectorKt.m462awaitHorizontalPointerSlopOrCancellationgDDlDlE($this$awaitEachGesture, down2.getId(), down2.getType(), new Function2() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return DragGestureDetectorKt.C01925.invokeSuspend$lambda$0(overSlop, (PointerInputChange) obj, ((Float) obj2).floatValue());
                        }
                    }, this);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    drag = (PointerInputChange) $result;
                    if (drag != null) {
                        this.$onDragStart.invoke(Offset.m4841boximpl(drag.getPosition()));
                        this.$onHorizontalDrag.invoke(drag, Boxing.boxFloat(overSlop.element));
                        long id2 = drag.getId();
                        final Function2 function3 = this.$onHorizontalDrag;
                        this.L$0 = null;
                        this.L$1 = null;
                        this.label = 3;
                        $result = DragGestureDetectorKt.m475horizontalDragjO51t88($this$awaitEachGesture, id2, new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return DragGestureDetectorKt.C01925.invokeSuspend$lambda$1(function3, (PointerInputChange) obj);
                            }
                        }, this);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (((Boolean) $result).booleanValue()) {
                            this.$onDragEnd.invoke();
                        } else {
                            this.$onDragCancel.invoke();
                        }
                    }
                    return Unit.INSTANCE;
                case 2:
                    Ref.FloatRef overSlop2 = (Ref.FloatRef) this.L$1;
                    $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    overSlop = overSlop2;
                    drag = (PointerInputChange) $result;
                    if (drag != null) {
                        this.$onDragStart.invoke(Offset.m4841boximpl(drag.getPosition()));
                        this.$onHorizontalDrag.invoke(drag, Boxing.boxFloat(overSlop.element));
                        long id3 = drag.getId();
                        final Function2 function4 = this.$onHorizontalDrag;
                        this.L$0 = null;
                        this.L$1 = null;
                        this.label = 3;
                        $result = DragGestureDetectorKt.m475horizontalDragjO51t88($this$awaitEachGesture, id3, new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return DragGestureDetectorKt.C01925.invokeSuspend$lambda$1(function4, (PointerInputChange) obj);
                            }
                        }, this);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (((Boolean) $result).booleanValue()) {
                            this.$onDragEnd.invoke();
                        } else {
                            this.$onDragCancel.invoke();
                        }
                    }
                    return Unit.INSTANCE;
                case 3:
                    ResultKt.throwOnFailure($result);
                    if (((Boolean) $result).booleanValue()) {
                        this.$onDragEnd.invoke();
                    } else {
                        this.$onDragCancel.invoke();
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        static final Unit invokeSuspend$lambda$0(Ref.FloatRef $overSlop, PointerInputChange change, float over) {
            change.consume();
            $overSlop.element = over;
            return Unit.INSTANCE;
        }

        static final Unit invokeSuspend$lambda$1(Function2 $onHorizontalDrag, PointerInputChange it) {
            int bits$iv$iv$iv = (int) (PointerEventKt.positionChange(it) >> 32);
            $onHorizontalDrag.invoke(it, Float.valueOf(Float.intBitsToFloat(bits$iv$iv$iv)));
            it.consume();
            return Unit.INSTANCE;
        }
    }

    public static final Object detectHorizontalDragGestures(PointerInputScope $this$detectHorizontalDragGestures, Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function2, Function2<? super PointerInputChange, ? super Float, Unit> function3, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$detectHorizontalDragGestures, new C01925(function1, function3, function0, function2, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:26:0x00e9 A[LOOP:0: B:23:0x00c4->B:26:0x00e9, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:66:0x00ff A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v25, types: [androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r20v2, types: [androidx.compose.ui.input.pointer.PointerInputChange] */
    /* JADX WARN: Type inference failed for: r20v3 */
    /* JADX WARN: Type inference failed for: r20v4 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x009d -> B:22:0x00ab). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: drag-VnAYq1g, reason: not valid java name */
    public static final java.lang.Object m472dragVnAYq1g(androidx.compose.ui.input.pointer.AwaitPointerEventScope r25, long r26, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r28, androidx.compose.foundation.gestures.Orientation r29, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Boolean> r30, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r31) {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m472dragVnAYq1g(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, androidx.compose.foundation.gestures.Orientation, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v5, types: [androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r22v2, types: [androidx.compose.ui.input.pointer.PointerInputChange] */
    /* JADX WARN: Type inference failed for: r22v3 */
    /* JADX WARN: Type inference failed for: r22v4 */
    /* JADX INFO: renamed from: drag-VnAYq1g$$forInline, reason: not valid java name */
    private static final Object m473dragVnAYq1g$$forInline(AwaitPointerEventScope $this$drag_u2dVnAYq1g, long j, Function1<? super PointerInputChange, Unit> function1, Orientation orientation, Function1<? super PointerInputChange, Boolean> function2, Continuation<? super PointerInputChange> continuation) {
        PointerEventPass pointerEventPass;
        int i;
        AwaitPointerEventScope $this$awaitDragOrUp_u2djO51t88$iv;
        Object it$iv$iv;
        ?? r22;
        float motionChange;
        Object it$iv$iv2;
        int i2 = 0;
        PointerEventPass pointerEventPass2 = null;
        if (m476isPointerUpDmW0f2w($this$drag_u2dVnAYq1g.getCurrentEvent(), j)) {
            return null;
        }
        long pointer = j;
        while (true) {
            AwaitPointerEventScope $this$awaitDragOrUp_u2djO51t88$iv2 = $this$drag_u2dVnAYq1g;
            Ref.LongRef pointer$iv = new Ref.LongRef();
            pointer$iv.element = pointer;
            while (true) {
                PointerEvent event$iv = (PointerEvent) AwaitPointerEventScope.awaitPointerEvent$default($this$awaitDragOrUp_u2djO51t88$iv2, pointerEventPass2, continuation, 1, pointerEventPass2);
                List<PointerInputChange> changes = event$iv.getChanges();
                pointerEventPass = pointerEventPass2;
                int size = changes.size();
                int index$iv$iv$iv = 0;
                while (true) {
                    if (index$iv$iv$iv >= size) {
                        i = i2;
                        $this$awaitDragOrUp_u2djO51t88$iv = $this$awaitDragOrUp_u2djO51t88$iv2;
                        it$iv$iv = pointerEventPass;
                        break;
                    }
                    i = i2;
                    List<PointerInputChange> list = changes;
                    Object item$iv$iv$iv = list.get(index$iv$iv$iv);
                    it$iv$iv = item$iv$iv$iv;
                    PointerInputChange it$iv = (PointerInputChange) it$iv$iv;
                    $this$awaitDragOrUp_u2djO51t88$iv = $this$awaitDragOrUp_u2djO51t88$iv2;
                    if (Boolean.valueOf(PointerId.m6392equalsimpl0(it$iv.getId(), pointer$iv.element)).booleanValue()) {
                        break;
                    }
                    index$iv$iv$iv++;
                    i2 = i;
                    $this$awaitDragOrUp_u2djO51t88$iv2 = $this$awaitDragOrUp_u2djO51t88$iv;
                    changes = list;
                }
                r22 = (PointerInputChange) it$iv$iv;
                if (r22 == 0) {
                    r22 = pointerEventPass;
                    break;
                }
                if (PointerEventKt.changedToUpIgnoreConsumed(r22)) {
                    List<PointerInputChange> changes2 = event$iv.getChanges();
                    int index$iv$iv$iv2 = 0;
                    int size2 = changes2.size();
                    while (true) {
                        if (index$iv$iv$iv2 >= size2) {
                            it$iv$iv2 = pointerEventPass;
                            break;
                        }
                        Object item$iv$iv$iv2 = changes2.get(index$iv$iv$iv2);
                        it$iv$iv2 = item$iv$iv$iv2;
                        PointerInputChange it$iv2 = (PointerInputChange) it$iv$iv2;
                        if (Boolean.valueOf(it$iv2.getPressed()).booleanValue()) {
                            break;
                        }
                        index$iv$iv$iv2++;
                    }
                    PointerInputChange otherDown$iv = (PointerInputChange) it$iv$iv2;
                    if (otherDown$iv == null) {
                        break;
                    }
                    pointer$iv.element = otherDown$iv.getId();
                    orientation = orientation;
                    i2 = i;
                    pointerEventPass2 = pointerEventPass;
                    $this$awaitDragOrUp_u2djO51t88$iv2 = $this$awaitDragOrUp_u2djO51t88$iv;
                } else {
                    PointerInputChange it = (PointerInputChange) r22;
                    long positionChange = PointerEventKt.positionChangeIgnoreConsumed(it);
                    if (orientation == null) {
                        motionChange = Offset.m4850getDistanceimpl(positionChange);
                    } else if (orientation == Orientation.Vertical) {
                        int bits$iv$iv$iv = (int) (positionChange & 4294967295L);
                        motionChange = Float.intBitsToFloat(bits$iv$iv$iv);
                    } else {
                        int bits$iv$iv$iv2 = (int) (positionChange >> 32);
                        motionChange = Float.intBitsToFloat(bits$iv$iv$iv2);
                    }
                    if (Boolean.valueOf(!(motionChange == 0.0f)).booleanValue()) {
                        break;
                    }
                    orientation = orientation;
                    i2 = i;
                    pointerEventPass2 = pointerEventPass;
                    $this$awaitDragOrUp_u2djO51t88$iv2 = $this$awaitDragOrUp_u2djO51t88$iv;
                }
            }
            if (r22 == 0) {
                return pointerEventPass;
            }
            ?? r0 = r22;
            if (function2.invoke(r0).booleanValue()) {
                return pointerEventPass;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(r0)) {
                return r0;
            }
            function1.invoke(r0);
            pointer = r0.getId();
            i2 = i;
            pointerEventPass2 = pointerEventPass;
        }
    }

    /* JADX INFO: renamed from: awaitDragOrUp-jO51t88, reason: not valid java name */
    private static final Object m460awaitDragOrUpjO51t88(AwaitPointerEventScope $this$awaitDragOrUp_u2djO51t88, long j, Function1<? super PointerInputChange, Boolean> function1, Continuation<? super PointerInputChange> continuation) {
        Object obj;
        PointerEvent event;
        Object it$iv;
        Object obj2;
        Ref.LongRef pointer = new Ref.LongRef();
        pointer.element = j;
        while (true) {
            Object obj3 = null;
            PointerEvent event2 = (PointerEvent) AwaitPointerEventScope.awaitPointerEvent$default($this$awaitDragOrUp_u2djO51t88, null, continuation, 1, null);
            List<PointerInputChange> changes = event2.getChanges();
            int index$iv$iv = 0;
            int size = changes.size();
            while (true) {
                if (index$iv$iv >= size) {
                    obj = obj3;
                    event = event2;
                    it$iv = obj;
                    break;
                }
                it$iv = changes.get(index$iv$iv);
                PointerInputChange it = (PointerInputChange) it$iv;
                obj = obj3;
                event = event2;
                if (Boolean.valueOf(PointerId.m6392equalsimpl0(it.getId(), pointer.element)).booleanValue()) {
                    break;
                }
                index$iv$iv++;
                obj3 = obj;
                event2 = event;
            }
            PointerInputChange dragEvent = (PointerInputChange) it$iv;
            if (dragEvent == null) {
                return obj;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(dragEvent)) {
                List<PointerInputChange> changes2 = event.getChanges();
                int index$iv$iv2 = 0;
                int size2 = changes2.size();
                while (true) {
                    if (index$iv$iv2 >= size2) {
                        obj2 = obj;
                        break;
                    }
                    Object item$iv$iv = changes2.get(index$iv$iv2);
                    PointerInputChange it2 = (PointerInputChange) item$iv$iv;
                    if (Boolean.valueOf(it2.getPressed()).booleanValue()) {
                        obj2 = item$iv$iv;
                        break;
                    }
                    index$iv$iv2++;
                }
                PointerInputChange otherDown = (PointerInputChange) obj2;
                if (otherDown == null) {
                    return dragEvent;
                }
                pointer.element = otherDown.getId();
            } else if (function1.invoke(dragEvent).booleanValue()) {
                return dragEvent;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:26:0x00f5 A[LOOP:0: B:23:0x00d0->B:26:0x00f5, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:67:0x0107 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x01ba -> B:60:0x01be). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitPointerSlopOrCancellation-6ksA65w, reason: not valid java name */
    public static final java.lang.Object m465awaitPointerSlopOrCancellation6ksA65w(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, int r25, androidx.compose.foundation.gestures.Orientation r26, long r27, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super androidx.compose.ui.geometry.Offset, kotlin.Unit> r29, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r30) {
        /*
            Method dump skipped, instruction units count: 476
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m465awaitPointerSlopOrCancellation6ksA65w(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, androidx.compose.foundation.gestures.Orientation, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: awaitPointerSlopOrCancellation-6ksA65w$default, reason: not valid java name */
    public static /* synthetic */ Object m467awaitPointerSlopOrCancellation6ksA65w$default(AwaitPointerEventScope $this$awaitPointerSlopOrCancellation_u2d6ksA65w_u24default, long j, int i, Orientation orientation, long j2, Function2 onPointerSlopReached, Continuation $completion, int i2, Object obj) {
        Object it$iv;
        Object it$iv2;
        PointerEventPass pointerEventPass;
        long jM4868getZeroF1C5BW0 = (i2 & 8) != 0 ? Offset.INSTANCE.m4868getZeroF1C5BW0() : j2;
        int i3 = 0;
        PointerEventPass pointerEventPass2 = null;
        if (m476isPointerUpDmW0f2w($this$awaitPointerSlopOrCancellation_u2d6ksA65w_u24default.getCurrentEvent(), j)) {
            return null;
        }
        float touchSlop = m477pointerSlopE8SPZFQ($this$awaitPointerSlopOrCancellation_u2d6ksA65w_u24default.getViewConfiguration(), i);
        long pointer = j;
        TouchSlopDetector touchSlopDetector = new TouchSlopDetector(orientation, jM4868getZeroF1C5BW0, null);
        while (true) {
            PointerEvent event = (PointerEvent) AwaitPointerEventScope.awaitPointerEvent$default($this$awaitPointerSlopOrCancellation_u2d6ksA65w_u24default, pointerEventPass2, $completion, 1, pointerEventPass2);
            List<PointerInputChange> changes = event.getChanges();
            int size = changes.size();
            PointerEventPass pointerEventPass3 = pointerEventPass2;
            int index$iv$iv = 0;
            while (true) {
                if (index$iv$iv >= size) {
                    jM4868getZeroF1C5BW0 = jM4868getZeroF1C5BW0;
                    it$iv = pointerEventPass3;
                    break;
                }
                jM4868getZeroF1C5BW0 = jM4868getZeroF1C5BW0;
                Object item$iv$iv = changes.get(index$iv$iv);
                it$iv = item$iv$iv;
                PointerInputChange it = (PointerInputChange) it$iv;
                if (PointerId.m6392equalsimpl0(it.getId(), pointer)) {
                    break;
                }
                index$iv$iv++;
                jM4868getZeroF1C5BW0 = jM4868getZeroF1C5BW0;
            }
            PointerInputChange dragEvent = (PointerInputChange) it$iv;
            if (dragEvent == null) {
                return pointerEventPass3;
            }
            if (dragEvent.isConsumed()) {
                return pointerEventPass3;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(dragEvent)) {
                List<PointerInputChange> changes2 = event.getChanges();
                int size2 = changes2.size();
                int i4 = i3;
                int index$iv$iv2 = 0;
                while (true) {
                    if (index$iv$iv2 >= size2) {
                        it$iv2 = pointerEventPass3;
                        break;
                    }
                    Object item$iv$iv2 = changes2.get(index$iv$iv2);
                    it$iv2 = item$iv$iv2;
                    PointerInputChange it2 = (PointerInputChange) it$iv2;
                    if (it2.getPressed()) {
                        break;
                    }
                    index$iv$iv2++;
                }
                PointerInputChange otherDown = (PointerInputChange) it$iv2;
                if (otherDown == null) {
                    return pointerEventPass3;
                }
                pointer = otherDown.getId();
                pointerEventPass2 = pointerEventPass3;
                i3 = i4;
            } else {
                int i5 = i3;
                long j3 = pointer;
                TouchSlopDetector touchSlopDetector2 = touchSlopDetector;
                long postSlopOffset = touchSlopDetector2.m605addPositionsakrDWew(dragEvent.getPosition(), dragEvent.getPreviousPosition(), touchSlop);
                if ((postSlopOffset & 9223372034707292159L) != InlineClassHelperKt.UnspecifiedPackedFloats) {
                    onPointerSlopReached.invoke(dragEvent, Offset.m4841boximpl(postSlopOffset));
                    if (dragEvent.isConsumed()) {
                        return dragEvent;
                    }
                    pointerEventPass = pointerEventPass3;
                    TouchSlopDetector.m604resetk4lQ0M$default(touchSlopDetector2, 0L, 1, pointerEventPass);
                } else {
                    pointerEventPass = pointerEventPass3;
                    $this$awaitPointerSlopOrCancellation_u2d6ksA65w_u24default.awaitPointerEvent(PointerEventPass.Final, $completion);
                    if (dragEvent.isConsumed()) {
                        return pointerEventPass;
                    }
                }
                pointerEventPass2 = pointerEventPass;
                touchSlopDetector = touchSlopDetector2;
                pointer = j3;
                i3 = i5;
            }
        }
    }

    /* JADX INFO: renamed from: awaitPointerSlopOrCancellation-6ksA65w$$forInline, reason: not valid java name */
    private static final Object m466awaitPointerSlopOrCancellation6ksA65w$$forInline(AwaitPointerEventScope $this$awaitPointerSlopOrCancellation_u2d6ksA65w, long j, int i, Orientation orientation, long j2, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Continuation<? super PointerInputChange> continuation) {
        Object it$iv;
        PointerEventPass pointerEventPass;
        Object it$iv2;
        PointerEventPass pointerEventPass2 = null;
        if (m476isPointerUpDmW0f2w($this$awaitPointerSlopOrCancellation_u2d6ksA65w.getCurrentEvent(), j)) {
            return null;
        }
        float touchSlop = m477pointerSlopE8SPZFQ($this$awaitPointerSlopOrCancellation_u2d6ksA65w.getViewConfiguration(), i);
        Ref.LongRef pointer = new Ref.LongRef();
        pointer.element = j;
        TouchSlopDetector touchSlopDetector = new TouchSlopDetector(orientation, j2, null);
        while (true) {
            PointerEvent event = (PointerEvent) AwaitPointerEventScope.awaitPointerEvent$default($this$awaitPointerSlopOrCancellation_u2d6ksA65w, pointerEventPass2, continuation, 1, pointerEventPass2);
            List<PointerInputChange> changes = event.getChanges();
            PointerEventPass pointerEventPass3 = pointerEventPass2;
            int size = changes.size();
            int index$iv$iv = 0;
            while (true) {
                if (index$iv$iv >= size) {
                    it$iv = pointerEventPass3;
                    break;
                }
                List<PointerInputChange> list = changes;
                Object item$iv$iv = list.get(index$iv$iv);
                it$iv = item$iv$iv;
                PointerInputChange it = (PointerInputChange) it$iv;
                int i2 = size;
                if (Boolean.valueOf(PointerId.m6392equalsimpl0(it.getId(), pointer.element)).booleanValue()) {
                    break;
                }
                index$iv$iv++;
                changes = list;
                size = i2;
            }
            PointerInputChange dragEvent = (PointerInputChange) it$iv;
            if (dragEvent == null) {
                return pointerEventPass3;
            }
            if (dragEvent.isConsumed()) {
                return pointerEventPass3;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(dragEvent)) {
                List<PointerInputChange> changes2 = event.getChanges();
                int index$iv$iv2 = 0;
                int size2 = changes2.size();
                while (true) {
                    if (index$iv$iv2 >= size2) {
                        it$iv2 = pointerEventPass3;
                        break;
                    }
                    Object item$iv$iv2 = changes2.get(index$iv$iv2);
                    it$iv2 = item$iv$iv2;
                    PointerInputChange it2 = (PointerInputChange) it$iv2;
                    if (Boolean.valueOf(it2.getPressed()).booleanValue()) {
                        break;
                    }
                    index$iv$iv2++;
                }
                PointerInputChange otherDown = (PointerInputChange) it$iv2;
                if (otherDown == null) {
                    return pointerEventPass3;
                }
                pointer.element = otherDown.getId();
                pointerEventPass = pointerEventPass3;
            } else {
                long postSlopOffset = touchSlopDetector.m605addPositionsakrDWew(dragEvent.getPosition(), dragEvent.getPreviousPosition(), touchSlop);
                if ((9223372034707292159L & postSlopOffset) != InlineClassHelperKt.UnspecifiedPackedFloats) {
                    function2.invoke(dragEvent, Offset.m4841boximpl(postSlopOffset));
                    if (dragEvent.isConsumed()) {
                        return dragEvent;
                    }
                    pointerEventPass = pointerEventPass3;
                    TouchSlopDetector.m604resetk4lQ0M$default(touchSlopDetector, 0L, 1, pointerEventPass);
                } else {
                    pointerEventPass = pointerEventPass3;
                    $this$awaitPointerSlopOrCancellation_u2d6ksA65w.awaitPointerEvent(PointerEventPass.Final, continuation);
                    if (dragEvent.isConsumed()) {
                        return pointerEventPass;
                    }
                }
            }
            pointerEventPass2 = pointerEventPass;
        }
    }

    /* JADX WARN: Code duplicated, block: B:25:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:27:0x00ec A[LOOP:0: B:23:0x00c7->B:27:0x00ec, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:71:0x00ff A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x00aa -> B:22:0x00b5). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object awaitAllPointersUpWithSlopDetection(androidx.compose.ui.input.pointer.AwaitPointerEventScope r24, androidx.compose.ui.input.pointer.PointerInputChange r25, androidx.compose.ui.input.pointer.PointerEventPass r26, kotlin.coroutines.Continuation<? super java.lang.Boolean> r27) {
        /*
            Method dump skipped, instruction units count: 460
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.awaitAllPointersUpWithSlopDetection(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.ui.input.pointer.PointerInputChange, androidx.compose.ui.input.pointer.PointerEventPass, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object awaitAllPointersUpWithSlopDetection$default(AwaitPointerEventScope awaitPointerEventScope, PointerInputChange pointerInputChange, PointerEventPass pointerEventPass, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            pointerEventPass = PointerEventPass.Main;
        }
        return awaitAllPointersUpWithSlopDetection(awaitPointerEventScope, pointerInputChange, pointerEventPass, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00ce A[Catch: PointerEventTimeoutCancellationException -> 0x00da, TRY_LEAVE, TryCatch #0 {PointerEventTimeoutCancellationException -> 0x00da, blocks: (B:13:0x003d, B:32:0x00ca, B:34:0x00ce), top: B:48:0x003d }] */
    /* JADX WARN: Code duplicated, block: B:36:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:45:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:46:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:55:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:57:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v1, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v6, types: [androidx.compose.ui.input.pointer.PointerInputChange] */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX INFO: renamed from: awaitLongPressOrCancellation-rnUCldI, reason: not valid java name */
    public static final Object m464awaitLongPressOrCancellationrnUCldI(AwaitPointerEventScope awaitPointerEventScope, long j, Continuation<? super PointerInputChange> continuation) {
        DragGestureDetectorKt$awaitLongPressOrCancellation$1 dragGestureDetectorKt$awaitLongPressOrCancellation$1;
        PointerInputChange pointerInputChange;
        ?? r6;
        Ref.ObjectRef objectRef;
        Ref.BooleanRef booleanRef;
        PointerInputChange pointerInputChange2;
        if (continuation instanceof DragGestureDetectorKt$awaitLongPressOrCancellation$1) {
            dragGestureDetectorKt$awaitLongPressOrCancellation$1 = (DragGestureDetectorKt$awaitLongPressOrCancellation$1) continuation;
            if ((dragGestureDetectorKt$awaitLongPressOrCancellation$1.label & Integer.MIN_VALUE) != 0) {
                dragGestureDetectorKt$awaitLongPressOrCancellation$1.label -= Integer.MIN_VALUE;
            } else {
                dragGestureDetectorKt$awaitLongPressOrCancellation$1 = new DragGestureDetectorKt$awaitLongPressOrCancellation$1(continuation);
            }
        } else {
            dragGestureDetectorKt$awaitLongPressOrCancellation$1 = new DragGestureDetectorKt$awaitLongPressOrCancellation$1(continuation);
        }
        DragGestureDetectorKt$awaitLongPressOrCancellation$1 dragGestureDetectorKt$awaitLongPressOrCancellation$2 = dragGestureDetectorKt$awaitLongPressOrCancellation$1;
        Object obj = dragGestureDetectorKt$awaitLongPressOrCancellation$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (dragGestureDetectorKt$awaitLongPressOrCancellation$2.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                if (m476isPointerUpDmW0f2w(awaitPointerEventScope.getCurrentEvent(), j)) {
                    return null;
                }
                List<PointerInputChange> changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                int i = 0;
                int i2 = 0;
                int size = changes.size();
                while (true) {
                    if (i2 < size) {
                        pointerInputChange = changes.get(i2);
                        List<PointerInputChange> list = changes;
                        int i3 = i;
                        if (!PointerId.m6392equalsimpl0(pointerInputChange.getId(), j)) {
                            i2++;
                            i = i3;
                            changes = list;
                        }
                    } else {
                        pointerInputChange = null;
                    }
                }
                PointerInputChange pointerInputChange3 = pointerInputChange;
                if (pointerInputChange3 == null) {
                    return null;
                }
                r6 = pointerInputChange3;
                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                objectRef3.element = r6;
                long longPressTimeoutMillis = awaitPointerEventScope.getViewConfiguration().getLongPressTimeoutMillis();
                try {
                    Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                    DragGestureDetectorKt$awaitLongPressOrCancellation$2 dragGestureDetectorKt$awaitLongPressOrCancellation$3 = new DragGestureDetectorKt$awaitLongPressOrCancellation$2(booleanRef2, objectRef3, objectRef2, null);
                    dragGestureDetectorKt$awaitLongPressOrCancellation$2.L$0 = r6;
                    dragGestureDetectorKt$awaitLongPressOrCancellation$2.L$1 = objectRef2;
                    dragGestureDetectorKt$awaitLongPressOrCancellation$2.L$2 = booleanRef2;
                    dragGestureDetectorKt$awaitLongPressOrCancellation$2.label = 1;
                    if (awaitPointerEventScope.withTimeout(longPressTimeoutMillis, dragGestureDetectorKt$awaitLongPressOrCancellation$3, dragGestureDetectorKt$awaitLongPressOrCancellation$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRef2;
                    booleanRef = booleanRef2;
                    r6 = r6;
                    if (booleanRef.element) {
                        return null;
                    }
                    pointerInputChange2 = (PointerInputChange) objectRef.element;
                    if (pointerInputChange2 == null) {
                        return r6;
                    }
                    return pointerInputChange2;
                } catch (PointerEventTimeoutCancellationException e) {
                    objectRef = objectRef2;
                    PointerInputChange pointerInputChange4 = (PointerInputChange) objectRef.element;
                    return pointerInputChange4 == null ? r6 : pointerInputChange4;
                }
            case 1:
                booleanRef = (Ref.BooleanRef) dragGestureDetectorKt$awaitLongPressOrCancellation$2.L$2;
                objectRef = (Ref.ObjectRef) dragGestureDetectorKt$awaitLongPressOrCancellation$2.L$1;
                r6 = (PointerInputChange) dragGestureDetectorKt$awaitLongPressOrCancellation$2.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    r6 = r6;
                    if (booleanRef.element) {
                        return null;
                    }
                    pointerInputChange2 = (PointerInputChange) objectRef.element;
                    if (pointerInputChange2 == null) {
                        return r6;
                    }
                    return pointerInputChange2;
                } catch (PointerEventTimeoutCancellationException e2) {
                    PointerInputChange pointerInputChange5 = (PointerInputChange) objectRef.element;
                    if (pointerInputChange5 == null) {
                    }
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isPointerUp-DmW0f2w, reason: not valid java name */
    public static final boolean m476isPointerUpDmW0f2w(PointerEvent $this$isPointerUp_u2dDmW0f2w, long j) {
        Object it$iv;
        List<PointerInputChange> changes = $this$isPointerUp_u2dDmW0f2w.getChanges();
        int index$iv$iv = 0;
        int size = changes.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = changes.get(index$iv$iv);
                it$iv = item$iv$iv;
                PointerInputChange it = (PointerInputChange) it$iv;
                if (PointerId.m6392equalsimpl0(it.getId(), j)) {
                    break;
                }
                index$iv$iv++;
            } else {
                it$iv = null;
                break;
            }
        }
        PointerInputChange pointerInputChange = (PointerInputChange) it$iv;
        boolean z = false;
        if (pointerInputChange != null && pointerInputChange.getPressed()) {
            z = true;
        }
        return !z;
    }

    /* JADX INFO: renamed from: pointerSlop-E8SPZFQ, reason: not valid java name */
    public static final float m477pointerSlopE8SPZFQ(ViewConfiguration $this$pointerSlop_u2dE8SPZFQ, int i) {
        return PointerType.m6481equalsimpl0(i, PointerType.INSTANCE.m6486getMouseT8wyACA()) ? $this$pointerSlop_u2dE8SPZFQ.getTouchSlop() * mouseToTouchSlopRatio : $this$pointerSlop_u2dE8SPZFQ.getTouchSlop();
    }
}
