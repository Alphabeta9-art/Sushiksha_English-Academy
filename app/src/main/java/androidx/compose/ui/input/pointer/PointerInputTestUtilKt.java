package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.IntSize;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PointerInputTestUtil.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\u0004\u001a@\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH\u0000\u001a(\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0000\u001a(\u0010\f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u0006H\u0000\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0000\u001aA\u0010\u0016\u001a\u00020\u0015*\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0011j\u0002`\u00172\u0006\u0010\u0018\u001a\u00020\u00122\b\b\u0002\u0010\u0019\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001aI\u0010\u001c\u001a\u00020\u0015*\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0011j\u0002`\u00172\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u00132\b\b\u0002\u0010\u0019\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001aU\u0010 \u001a\u00020\u0015*\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0011j\u0002`\u00172\u0006\u0010\u0018\u001a\u00020\u00122\u0012\u0010!\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130\"\"\u00020\u00132\b\b\u0002\u0010\u0019\u001a\u00020\u0014H\u0000¢\u0006\u0004\b#\u0010$\u001aO\u0010 \u001a\u00020\u0015*\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0011j\u0002`\u00172\u0006\u0010\u0018\u001a\u00020\u00122\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00130\t2\b\b\u0002\u0010\u0019\u001a\u00020\u0014H\u0000¢\u0006\u0004\b#\u0010%*<\b\u0000\u0010\u0010\"\u001a\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00112\u001a\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0011¨\u0006&"}, d2 = {"down", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "id", "", "durationMillis", "x", "", "y", "historicalData", "", "Landroidx/compose/ui/input/pointer/HistoricalChange;", "moveTo", "moveBy", "dx", "dy", "up", "PointerInputHandler", "Lkotlin/Function3;", "Landroidx/compose/ui/input/pointer/PointerEvent;", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "Landroidx/compose/ui/unit/IntSize;", "", "invokeOverAllPasses", "Landroidx/compose/ui/input/pointer/PointerInputHandler;", "pointerEvent", "size", "invokeOverAllPasses-H0pRuoY", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/input/pointer/PointerEvent;J)V", "invokeOverPass", "pointerEventPass", "invokeOverPass-hUlJWOE", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "invokeOverPasses", "pointerEventPasses", "", "invokeOverPasses-hUlJWOE", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/input/pointer/PointerEvent;[Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/input/pointer/PointerEvent;Ljava/util/List;J)V", "ui"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PointerInputTestUtilKt {
    public static final PointerInputChange down(long id, long durationMillis, float x, float y, List<HistoricalChange> list) {
        long jM6390constructorimpl = PointerId.m6390constructorimpl(id);
        long v1$iv$iv = Float.floatToRawIntBits(x);
        long v2$iv$iv = Float.floatToRawIntBits(y);
        long jM4844constructorimpl = Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        long v1$iv$iv2 = Float.floatToRawIntBits(x);
        long v2$iv$iv2 = Float.floatToRawIntBits(y);
        PointerInputChange pointerInputChange = new PointerInputChange(jM6390constructorimpl, durationMillis, jM4844constructorimpl, true, 1.0f, durationMillis, Offset.m4844constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L)), false, false, 0, 0L, 1536, (DefaultConstructorMarker) null);
        List<HistoricalChange> list2 = list;
        if (list2 == null || list2.isEmpty()) {
            return pointerInputChange;
        }
        return pointerInputChange.m6405copyOHpmEuE((731 & 1) != 0 ? pointerInputChange.id : 0L, (731 & 2) != 0 ? pointerInputChange.uptimeMillis : 0L, (731 & 4) != 0 ? pointerInputChange.position : 0L, (731 & 8) != 0 ? pointerInputChange.pressed : false, (731 & 16) != 0 ? pointerInputChange.previousUptimeMillis : 0L, (731 & 32) != 0 ? pointerInputChange.previousPosition : 0L, (731 & 64) != 0 ? pointerInputChange.previousPressed : false, (731 & 128) != 0 ? pointerInputChange.type : 0, list, (731 & 512) != 0 ? pointerInputChange.scrollDelta : 0L);
    }

    public static /* synthetic */ PointerInputChange moveTo$default(PointerInputChange pointerInputChange, long j, float f, float f2, int i, Object obj) {
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        if ((i & 4) != 0) {
            f2 = 0.0f;
        }
        return moveTo(pointerInputChange, j, f, f2);
    }

    public static final PointerInputChange moveTo(PointerInputChange $this$moveTo, long durationMillis, float x, float y) {
        long id = $this$moveTo.getId();
        long uptimeMillis = $this$moveTo.getUptimeMillis();
        boolean pressed = $this$moveTo.getPressed();
        long position = $this$moveTo.getPosition();
        long v1$iv$iv = Float.floatToRawIntBits(x);
        long v2$iv$iv = Float.floatToRawIntBits(y);
        return new PointerInputChange(id, durationMillis, Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)), true, 1.0f, uptimeMillis, position, pressed, false, 0, 0L, 1536, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ PointerInputChange moveBy$default(PointerInputChange pointerInputChange, long j, float f, float f2, int i, Object obj) {
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        if ((i & 4) != 0) {
            f2 = 0.0f;
        }
        return moveBy(pointerInputChange, j, f, f2);
    }

    public static final PointerInputChange moveBy(PointerInputChange $this$moveBy, long durationMillis, float dx, float dy) {
        long id = $this$moveBy.getId();
        long uptimeMillis = $this$moveBy.getUptimeMillis();
        boolean pressed = $this$moveBy.getPressed();
        long position = $this$moveBy.getPosition();
        long uptimeMillis2 = $this$moveBy.getUptimeMillis() + durationMillis;
        int bits$iv$iv$iv = (int) ($this$moveBy.getPosition() >> 32);
        float x$iv = Float.intBitsToFloat(bits$iv$iv$iv) + dx;
        int bits$iv$iv$iv2 = (int) ($this$moveBy.getPosition() & 4294967295L);
        float y$iv = Float.intBitsToFloat(bits$iv$iv$iv2) + dy;
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        return new PointerInputChange(id, uptimeMillis2, Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)), true, 1.0f, uptimeMillis, position, pressed, false, 0, 0L, 1536, (DefaultConstructorMarker) null);
    }

    public static final PointerInputChange up(PointerInputChange $this$up, long durationMillis) {
        long id = $this$up.getId();
        long uptimeMillis = $this$up.getUptimeMillis();
        boolean pressed = $this$up.getPressed();
        return new PointerInputChange(id, durationMillis, $this$up.getPosition(), false, 1.0f, uptimeMillis, $this$up.getPosition(), pressed, false, 0, 0L, 1536, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: renamed from: invokeOverAllPasses-H0pRuoY$default, reason: not valid java name */
    public static /* synthetic */ void m6461invokeOverAllPassesH0pRuoY$default(Function3 function3, PointerEvent pointerEvent, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = IntSize.m8071constructorimpl((((long) Integer.MAX_VALUE) << 32) | (((long) Integer.MAX_VALUE) & 4294967295L));
        }
        m6460invokeOverAllPassesH0pRuoY(function3, pointerEvent, j);
    }

    /* JADX INFO: renamed from: invokeOverAllPasses-H0pRuoY, reason: not valid java name */
    public static final void m6460invokeOverAllPassesH0pRuoY(Function3<? super PointerEvent, ? super PointerEventPass, ? super IntSize, Unit> function3, PointerEvent pointerEvent, long j) {
        m6464invokeOverPasseshUlJWOE(function3, pointerEvent, (List<? extends PointerEventPass>) CollectionsKt.listOf((Object[]) new PointerEventPass[]{PointerEventPass.Initial, PointerEventPass.Main, PointerEventPass.Final}), j);
    }

    /* JADX INFO: renamed from: invokeOverPass-hUlJWOE$default, reason: not valid java name */
    public static /* synthetic */ void m6463invokeOverPasshUlJWOE$default(Function3 function3, PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            j = IntSize.m8071constructorimpl((((long) Integer.MAX_VALUE) << 32) | (((long) Integer.MAX_VALUE) & 4294967295L));
        }
        m6462invokeOverPasshUlJWOE(function3, pointerEvent, pointerEventPass, j);
    }

    /* JADX INFO: renamed from: invokeOverPass-hUlJWOE, reason: not valid java name */
    public static final void m6462invokeOverPasshUlJWOE(Function3<? super PointerEvent, ? super PointerEventPass, ? super IntSize, Unit> function3, PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        m6464invokeOverPasseshUlJWOE(function3, pointerEvent, (List<? extends PointerEventPass>) CollectionsKt.listOf(pointerEventPass), j);
    }

    /* JADX INFO: renamed from: invokeOverPasses-hUlJWOE$default, reason: not valid java name */
    public static /* synthetic */ void m6467invokeOverPasseshUlJWOE$default(Function3 function3, PointerEvent pointerEvent, PointerEventPass[] pointerEventPassArr, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            j = IntSize.m8071constructorimpl((((long) Integer.MAX_VALUE) << 32) | (((long) Integer.MAX_VALUE) & 4294967295L));
        }
        m6465invokeOverPasseshUlJWOE((Function3<? super PointerEvent, ? super PointerEventPass, ? super IntSize, Unit>) function3, pointerEvent, pointerEventPassArr, j);
    }

    /* JADX INFO: renamed from: invokeOverPasses-hUlJWOE, reason: not valid java name */
    public static final void m6465invokeOverPasseshUlJWOE(Function3<? super PointerEvent, ? super PointerEventPass, ? super IntSize, Unit> function3, PointerEvent pointerEvent, PointerEventPass[] pointerEventPasses, long j) {
        m6464invokeOverPasseshUlJWOE(function3, pointerEvent, (List<? extends PointerEventPass>) ArraysKt.toList(pointerEventPasses), j);
    }

    /* JADX INFO: renamed from: invokeOverPasses-hUlJWOE$default, reason: not valid java name */
    public static /* synthetic */ void m6466invokeOverPasseshUlJWOE$default(Function3 function3, PointerEvent pointerEvent, List list, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            j = IntSize.m8071constructorimpl((((long) Integer.MAX_VALUE) << 32) | (((long) Integer.MAX_VALUE) & 4294967295L));
        }
        m6464invokeOverPasseshUlJWOE((Function3<? super PointerEvent, ? super PointerEventPass, ? super IntSize, Unit>) function3, pointerEvent, (List<? extends PointerEventPass>) list, j);
    }

    /* JADX INFO: renamed from: invokeOverPasses-hUlJWOE, reason: not valid java name */
    public static final void m6464invokeOverPasseshUlJWOE(Function3<? super PointerEvent, ? super PointerEventPass, ? super IntSize, Unit> function3, PointerEvent pointerEvent, List<? extends PointerEventPass> list, long j) {
        if (pointerEvent.getChanges().isEmpty()) {
            throw new IllegalArgumentException("invokeOverPasses called with no changes".toString());
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("invokeOverPasses called with no passes".toString());
        }
        int size = list.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list.get(index$iv);
            PointerEventPass it = (PointerEventPass) item$iv;
            function3.invoke(pointerEvent, it, IntSize.m8068boximpl(j));
        }
    }
}
