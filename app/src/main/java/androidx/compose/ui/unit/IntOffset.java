package androidx.compose.ui.unit;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: IntOffset.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0087@\u0018\u0000 12\u00020\u0001:\u00011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0011\u001a\u00020\tH\u0087\n¢\u0006\u0004\b\u0012\u0010\rJ\u0010\u0010\u0013\u001a\u00020\tH\u0087\n¢\u0006\u0004\b\u0014\u0010\rJ!\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u0016\u0010\u0017J\u0018\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0000H\u0087\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0000H\u0087\u0002¢\u0006\u0004\b\u001d\u0010\u001bJ\u0010\u0010\u001e\u001a\u00020\u0000H\u0087\u0002¢\u0006\u0004\b\u001f\u0010\u0005J\u0018\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"H\u0087\u0002¢\u0006\u0004\b#\u0010$J\u0018\u0010%\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"H\u0087\u0002¢\u0006\u0004\b&\u0010$J\u0018\u0010'\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\tH\u0087\u0002¢\u0006\u0004\b(\u0010)J\u000f\u0010*\u001a\u00020+H\u0017¢\u0006\u0004\b,\u0010-J\u0013\u0010.\u001a\u00020/2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\t8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\t8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\u000b\u001a\u0004\b\u0010\u0010\r\u0088\u0001\u0002¨\u00062"}, d2 = {"Landroidx/compose/ui/unit/IntOffset;", "", "packedValue", "", "constructor-impl", "(J)J", "getPackedValue", "()J", "x", "", "getX$annotations", "()V", "getX-impl", "(J)I", "y", "getY$annotations", "getY-impl", "component1", "component1-impl", "component2", "component2-impl", "copy", "copy-iSbpLlY", "(JII)J", "minus", "other", "minus-qkQi6aY", "(JJ)J", "plus", "plus-qkQi6aY", "unaryMinus", "unaryMinus-nOcc-ac", "times", "operand", "", "times-Bjo55l4", "(JF)J", TtmlNode.TAG_DIV, "div-Bjo55l4", "rem", "rem-Bjo55l4", "(JI)J", "toString", "", "toString-impl", "(J)Ljava/lang/String;", MetadataValidation.EQUALS, "", "hashCode", "Companion", "ui-unit"}, k = 1, mv = {2, 0, 0}, xi = 48)
@JvmInline
public final class IntOffset {
    private final long packedValue;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long Zero = m8027constructorimpl(0);
    private static final long Max = m8027constructorimpl(9223372034707292159L);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ IntOffset m8024boximpl(long j) {
        return new IntOffset(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m8027constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m8031equalsimpl(long j, Object obj) {
        return (obj instanceof IntOffset) && j == ((IntOffset) obj).m8042unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m8032equalsimpl0(long j, long j2) {
        return j == j2;
    }

    public static /* synthetic */ void getX$annotations() {
    }

    public static /* synthetic */ void getY$annotations() {
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m8035hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    public boolean equals(Object other) {
        return m8031equalsimpl(this.packedValue, other);
    }

    public int hashCode() {
        return m8035hashCodeimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m8042unboximpl() {
        return this.packedValue;
    }

    private /* synthetic */ IntOffset(long packedValue) {
        this.packedValue = packedValue;
    }

    public final long getPackedValue() {
        return this.packedValue;
    }

    /* JADX INFO: renamed from: getX-impl, reason: not valid java name */
    public static final int m8033getXimpl(long j) {
        return (int) (j >> 32);
    }

    /* JADX INFO: renamed from: getY-impl, reason: not valid java name */
    public static final int m8034getYimpl(long j) {
        return (int) (4294967295L & j);
    }

    /* JADX INFO: renamed from: component1-impl, reason: not valid java name */
    public static final int m8025component1impl(long j) {
        return m8033getXimpl(j);
    }

    /* JADX INFO: renamed from: component2-impl, reason: not valid java name */
    public static final int m8026component2impl(long j) {
        return m8034getYimpl(j);
    }

    /* JADX INFO: renamed from: copy-iSbpLlY$default, reason: not valid java name */
    public static /* synthetic */ long m8029copyiSbpLlY$default(long value$iv, int $i$f$unpackInt1, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            $i$f$unpackInt1 = (int) (value$iv >> 32);
        }
        if ((i2 & 2) != 0) {
            i = (int) (4294967295L & value$iv);
        }
        return m8028copyiSbpLlY(value$iv, $i$f$unpackInt1, i);
    }

    /* JADX INFO: renamed from: copy-iSbpLlY, reason: not valid java name */
    public static final long m8028copyiSbpLlY(long j, int x, int y) {
        return m8027constructorimpl((((long) x) << 32) | (((long) y) & 4294967295L));
    }

    /* JADX INFO: renamed from: minus-qkQi6aY, reason: not valid java name */
    public static final long m8036minusqkQi6aY(long j, long j2) {
        int val1$iv = ((int) (j >> 32)) - ((int) (j2 >> 32));
        int $i$f$unpackInt2 = (int) (j2 & 4294967295L);
        int val2$iv = ((int) (j & 4294967295L)) - $i$f$unpackInt2;
        return m8027constructorimpl((((long) val1$iv) << 32) | (((long) val2$iv) & 4294967295L));
    }

    /* JADX INFO: renamed from: plus-qkQi6aY, reason: not valid java name */
    public static final long m8037plusqkQi6aY(long j, long j2) {
        int val1$iv = ((int) (j >> 32)) + ((int) (j2 >> 32));
        int $i$f$unpackInt2 = (int) (j2 & 4294967295L);
        int val2$iv = ((int) (j & 4294967295L)) + $i$f$unpackInt2;
        return m8027constructorimpl((((long) val1$iv) << 32) | (((long) val2$iv) & 4294967295L));
    }

    /* JADX INFO: renamed from: unaryMinus-nOcc-ac, reason: not valid java name */
    public static final long m8041unaryMinusnOccac(long j) {
        int val1$iv = -((int) (j >> 32));
        int val2$iv = -((int) (j & 4294967295L));
        return m8027constructorimpl((((long) val1$iv) << 32) | (((long) val2$iv) & 4294967295L));
    }

    /* JADX INFO: renamed from: times-Bjo55l4, reason: not valid java name */
    public static final long m8039timesBjo55l4(long j, float operand) {
        float $this$fastRoundToInt$iv = ((int) (j >> 32)) * operand;
        int val1$iv = Math.round($this$fastRoundToInt$iv);
        float $this$fastRoundToInt$iv2 = ((int) (j & 4294967295L)) * operand;
        int val2$iv = Math.round($this$fastRoundToInt$iv2);
        return m8027constructorimpl((((long) val1$iv) << 32) | (((long) val2$iv) & 4294967295L));
    }

    /* JADX INFO: renamed from: div-Bjo55l4, reason: not valid java name */
    public static final long m8030divBjo55l4(long j, float operand) {
        float $this$fastRoundToInt$iv = ((int) (j >> 32)) / operand;
        int val1$iv = Math.round($this$fastRoundToInt$iv);
        float $this$fastRoundToInt$iv2 = ((int) (j & 4294967295L)) / operand;
        int val2$iv = Math.round($this$fastRoundToInt$iv2);
        return m8027constructorimpl((((long) val1$iv) << 32) | (((long) val2$iv) & 4294967295L));
    }

    /* JADX INFO: renamed from: rem-Bjo55l4, reason: not valid java name */
    public static final long m8038remBjo55l4(long j, int operand) {
        int val1$iv = ((int) (j >> 32)) % operand;
        int val2$iv = ((int) (j & 4294967295L)) % operand;
        return m8027constructorimpl((((long) val1$iv) << 32) | (((long) val2$iv) & 4294967295L));
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m8040toStringimpl(long j) {
        return '(' + m8033getXimpl(j) + ", " + m8034getYimpl(j) + ')';
    }

    public String toString() {
        return m8040toStringimpl(this.packedValue);
    }

    /* JADX INFO: compiled from: IntOffset.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/unit/IntOffset$Companion;", "", "<init>", "()V", "Zero", "Landroidx/compose/ui/unit/IntOffset;", "getZero-nOcc-ac", "()J", "J", "Max", "getMax-nOcc-ac", "ui-unit"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getZero-nOcc-ac, reason: not valid java name */
        public final long m8044getZeronOccac() {
            return IntOffset.Zero;
        }

        /* JADX INFO: renamed from: getMax-nOcc-ac, reason: not valid java name */
        public final long m8043getMaxnOccac() {
            return IntOffset.Max;
        }
    }
}
