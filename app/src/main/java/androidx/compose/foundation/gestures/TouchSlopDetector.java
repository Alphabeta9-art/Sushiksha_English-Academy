package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: DragGestureDetector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0001\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\f\u001a\u00020\r*\u00020\u0005¢\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0010\u001a\u00020\r*\u00020\u0005¢\u0006\u0004\b\u0011\u0010\u000fJ%\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\r¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u0005¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\rH\u0002¢\u0006\u0004\b \u0010!R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\u0012\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013¨\u0006\""}, d2 = {"Landroidx/compose/foundation/gestures/TouchSlopDetector;", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "initialPositionChange", "Landroidx/compose/ui/geometry/Offset;", "<init>", "(Landroidx/compose/foundation/gestures/Orientation;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "setOrientation", "(Landroidx/compose/foundation/gestures/Orientation;)V", "mainAxis", "", "mainAxis-k-4lQ0M", "(J)F", "crossAxis", "crossAxis-k-4lQ0M", "totalPositionChange", "J", "addPositions", "currentPosition", "previousPosition", "touchSlop", "addPositions-akrDWew", "(JJF)J", "reset", "", "initialPositionAccumulator", "reset-k-4lQ0M", "(J)V", "calculatePostSlopOffset", "calculatePostSlopOffset-tuRUvjQ", "(F)J", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TouchSlopDetector {
    public static final int $stable = 8;
    private Orientation orientation;
    private long totalPositionChange;

    public /* synthetic */ TouchSlopDetector(Orientation orientation, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(orientation, j);
    }

    private TouchSlopDetector(Orientation orientation, long initialPositionChange) {
        this.orientation = orientation;
        this.totalPositionChange = initialPositionChange;
    }

    public /* synthetic */ TouchSlopDetector(Orientation orientation, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : orientation, (i & 2) != 0 ? Offset.INSTANCE.m4868getZeroF1C5BW0() : j, null);
    }

    public final Orientation getOrientation() {
        return this.orientation;
    }

    public final void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    /* JADX INFO: renamed from: mainAxis-k-4lQ0M, reason: not valid java name */
    public final float m607mainAxisk4lQ0M(long j) {
        if (this.orientation == Orientation.Horizontal) {
            int bits$iv$iv$iv = (int) (j >> 32);
            return Float.intBitsToFloat(bits$iv$iv$iv);
        }
        int bits$iv$iv$iv2 = (int) (4294967295L & j);
        return Float.intBitsToFloat(bits$iv$iv$iv2);
    }

    /* JADX INFO: renamed from: crossAxis-k-4lQ0M, reason: not valid java name */
    public final float m606crossAxisk4lQ0M(long j) {
        if (this.orientation == Orientation.Horizontal) {
            int bits$iv$iv$iv = (int) (4294967295L & j);
            return Float.intBitsToFloat(bits$iv$iv$iv);
        }
        int bits$iv$iv$iv2 = (int) (j >> 32);
        return Float.intBitsToFloat(bits$iv$iv$iv2);
    }

    /* JADX INFO: renamed from: addPositions-akrDWew, reason: not valid java name */
    public final long m605addPositionsakrDWew(long currentPosition, long previousPosition, float touchSlop) {
        float inDirection;
        long positionChange = Offset.m4856minusMKHz9U(currentPosition, previousPosition);
        this.totalPositionChange = Offset.m4857plusMKHz9U(this.totalPositionChange, positionChange);
        if (this.orientation == null) {
            inDirection = Offset.m4850getDistanceimpl(this.totalPositionChange);
        } else {
            inDirection = Math.abs(m607mainAxisk4lQ0M(this.totalPositionChange));
        }
        boolean hasCrossedSlop = inDirection >= touchSlop;
        if (hasCrossedSlop) {
            return m603calculatePostSlopOffsettuRUvjQ(touchSlop);
        }
        return Offset.INSTANCE.m4867getUnspecifiedF1C5BW0();
    }

    /* JADX INFO: renamed from: reset-k-4lQ0M$default, reason: not valid java name */
    public static /* synthetic */ void m604resetk4lQ0M$default(TouchSlopDetector touchSlopDetector, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Offset.INSTANCE.m4868getZeroF1C5BW0();
        }
        touchSlopDetector.m608resetk4lQ0M(j);
    }

    /* JADX INFO: renamed from: reset-k-4lQ0M, reason: not valid java name */
    public final void m608resetk4lQ0M(long initialPositionAccumulator) {
        this.totalPositionChange = initialPositionAccumulator;
    }

    /* JADX INFO: renamed from: calculatePostSlopOffset-tuRUvjQ, reason: not valid java name */
    private final long m603calculatePostSlopOffsettuRUvjQ(float touchSlop) {
        if (this.orientation == null) {
            long touchSlopOffset = Offset.m4859timestuRUvjQ(Offset.m4847divtuRUvjQ(this.totalPositionChange, Offset.m4850getDistanceimpl(this.totalPositionChange)), touchSlop);
            return Offset.m4856minusMKHz9U(this.totalPositionChange, touchSlopOffset);
        }
        float finalMainAxisChange = m607mainAxisk4lQ0M(this.totalPositionChange) - (Math.signum(m607mainAxisk4lQ0M(this.totalPositionChange)) * touchSlop);
        float finalCrossAxisChange = m606crossAxisk4lQ0M(this.totalPositionChange);
        if (this.orientation == Orientation.Horizontal) {
            long v1$iv$iv = Float.floatToRawIntBits(finalMainAxisChange);
            long v2$iv$iv = Float.floatToRawIntBits(finalCrossAxisChange);
            return Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        }
        long v1$iv$iv2 = Float.floatToRawIntBits(finalCrossAxisChange);
        long v2$iv$iv2 = Float.floatToRawIntBits(finalMainAxisChange);
        return Offset.m4844constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L));
    }
}
