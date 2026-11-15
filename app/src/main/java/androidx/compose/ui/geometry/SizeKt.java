package androidx.compose.ui.geometry;

import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: Size.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0087\b¢\u0006\u0002\u0010\u0005\u001a\"\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0011H\u0086\b¢\u0006\u0004\b\u0012\u0010\u0013\u001a'\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u001c\u0010\u001a\u001a\u00020\u0001*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0001H\u0087\n¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u001c\u0010\u001a\u001a\u00020\u0001*\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u0001H\u0087\n¢\u0006\u0004\b\u001d\u0010 \u001a\u001c\u0010\u001a\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u0001H\u0087\n¢\u0006\u0004\b\u001d\u0010!\u001a\u0013\u0010\"\u001a\u00020#*\u00020\u0001H\u0007¢\u0006\u0004\b$\u0010%\"\u001f\u0010\u0006\u001a\u00020\u0007*\u00020\u00018Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u001f\u0010\f\u001a\u00020\u0007*\u00020\u00018Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\r\u0010\t\u001a\u0004\b\u000e\u0010\u000b\"\u001e\u0010&\u001a\u00020'*\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b(\u0010\t\u001a\u0004\b)\u0010*¨\u0006+"}, d2 = {"Size", "Landroidx/compose/ui/geometry/Size;", "width", "", "height", "(FF)J", "isSpecified", "", "isSpecified-uvyYCjk$annotations", "(J)V", "isSpecified-uvyYCjk", "(J)Z", "isUnspecified", "isUnspecified-uvyYCjk$annotations", "isUnspecified-uvyYCjk", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse-TmRCtEA", "(JLkotlin/jvm/functions/Function0;)J", "lerp", TtmlNode.START, "stop", "fraction", "lerp-VgWVRYQ", "(JJF)J", "times", "", "size", "times-d16Qtg0", "(IJ)J", "", "(DJ)J", "(FJ)J", "toRect", "Landroidx/compose/ui/geometry/Rect;", "toRect-uvyYCjk", "(J)Landroidx/compose/ui/geometry/Rect;", TtmlNode.CENTER, "Landroidx/compose/ui/geometry/Offset;", "getCenter-uvyYCjk$annotations", "getCenter-uvyYCjk", "(J)J", "ui-geometry"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SizeKt {
    /* JADX INFO: renamed from: getCenter-uvyYCjk$annotations, reason: not valid java name */
    public static /* synthetic */ void m4932getCenteruvyYCjk$annotations(long j) {
    }

    /* JADX INFO: renamed from: isSpecified-uvyYCjk$annotations, reason: not valid java name */
    public static /* synthetic */ void m4934isSpecifieduvyYCjk$annotations(long j) {
    }

    /* JADX INFO: renamed from: isUnspecified-uvyYCjk$annotations, reason: not valid java name */
    public static /* synthetic */ void m4936isUnspecifieduvyYCjk$annotations(long j) {
    }

    public static final long Size(float width, float height) {
        long v1$iv = Float.floatToRawIntBits(width);
        long v2$iv = Float.floatToRawIntBits(height);
        return Size.m4912constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* JADX INFO: renamed from: isSpecified-uvyYCjk, reason: not valid java name */
    public static final boolean m4933isSpecifieduvyYCjk(long j) {
        return j != InlineClassHelperKt.UnspecifiedPackedFloats;
    }

    /* JADX INFO: renamed from: isUnspecified-uvyYCjk, reason: not valid java name */
    public static final boolean m4935isUnspecifieduvyYCjk(long j) {
        return j == InlineClassHelperKt.UnspecifiedPackedFloats;
    }

    /* JADX INFO: renamed from: takeOrElse-TmRCtEA, reason: not valid java name */
    public static final long m4938takeOrElseTmRCtEA(long j, Function0<Size> function0) {
        return (j > InlineClassHelperKt.UnspecifiedPackedFloats ? 1 : (j == InlineClassHelperKt.UnspecifiedPackedFloats ? 0 : -1)) != 0 ? j : function0.invoke().m4926unboximpl();
    }

    /* JADX INFO: renamed from: lerp-VgWVRYQ, reason: not valid java name */
    public static final long m4937lerpVgWVRYQ(long j, long j2, float fraction) {
        int bits$iv$iv = (int) (j >> 32);
        int bits$iv$iv2 = (int) (j2 >> 32);
        float val1$iv = MathHelpersKt.lerp(Float.intBitsToFloat(bits$iv$iv), Float.intBitsToFloat(bits$iv$iv2), fraction);
        int bits$iv$iv3 = (int) (j & 4294967295L);
        int bits$iv$iv4 = (int) (j2 & 4294967295L);
        float val2$iv = MathHelpersKt.lerp(Float.intBitsToFloat(bits$iv$iv3), Float.intBitsToFloat(bits$iv$iv4), fraction);
        long v1$iv = Float.floatToRawIntBits(val1$iv);
        long v2$iv = Float.floatToRawIntBits(val2$iv);
        return Size.m4912constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* JADX INFO: renamed from: times-d16Qtg0, reason: not valid java name */
    public static final long m4941timesd16Qtg0(int $this$times_u2dd16Qtg0, long j) {
        return Size.m4924times7Ah8Wj8(j, $this$times_u2dd16Qtg0);
    }

    /* JADX INFO: renamed from: times-d16Qtg0, reason: not valid java name */
    public static final long m4939timesd16Qtg0(double $this$times_u2dd16Qtg0, long j) {
        return Size.m4924times7Ah8Wj8(j, (float) $this$times_u2dd16Qtg0);
    }

    /* JADX INFO: renamed from: times-d16Qtg0, reason: not valid java name */
    public static final long m4940timesd16Qtg0(float $this$times_u2dd16Qtg0, long j) {
        return Size.m4924times7Ah8Wj8(j, $this$times_u2dd16Qtg0);
    }

    /* JADX INFO: renamed from: toRect-uvyYCjk, reason: not valid java name */
    public static final Rect m4942toRectuvyYCjk(long j) {
        return RectKt.m4892Recttz77jQw(Offset.INSTANCE.m4868getZeroF1C5BW0(), j);
    }

    /* JADX INFO: renamed from: getCenter-uvyYCjk, reason: not valid java name */
    public static final long m4931getCenteruvyYCjk(long j) {
        int bits$iv$iv = (int) (j >> 32);
        float x$iv = Float.intBitsToFloat(bits$iv$iv) / 2.0f;
        int bits$iv$iv2 = (int) (j & 4294967295L);
        float y$iv = Float.intBitsToFloat(bits$iv$iv2) / 2.0f;
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        return Offset.m4844constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }
}
