package androidx.compose.ui.unit;

import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Dp.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\n\u001a\"\u0010\n\u001a\u00020\u0002*\u00020\u00022\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\fH\u0086\b¢\u0006\u0004\b\r\u0010\u000e\u001a\u001c\u0010\u001a\u001a\u00020\u0002*\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0002H\u0087\n¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001c\u0010\u001a\u001a\u00020\u0002*\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0002H\u0087\n¢\u0006\u0004\b\u001c\u0010\u001e\u001a\u001c\u0010\u001a\u001a\u00020\u0002*\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0002H\u0087\n¢\u0006\u0004\b\u001c\u0010\u001f\u001a \u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\b#\u0010\u001d\u001a \u0010$\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\b%\u0010\u001d\u001a$\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010'\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\b)\u0010*\u001a\u001c\u0010+\u001a\u00020\u0002*\u00020\u00022\u0006\u0010'\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\b,\u0010\u001d\u001a\u001c\u0010-\u001a\u00020\u0002*\u00020\u00022\u0006\u0010(\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\b.\u0010\u001d\u001a'\u00102\u001a\u00020\u00022\u0006\u00103\u001a\u00020\u00022\u0006\u00104\u001a\u00020\u00022\u0006\u00105\u001a\u00020\u0018H\u0007¢\u0006\u0004\b6\u0010*\u001a \u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00022\u0006\u0010:\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\b;\u0010<\u001a\"\u0010\n\u001a\u000208*\u0002082\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002080\fH\u0086\b¢\u0006\u0004\bC\u0010D\u001a'\u00102\u001a\u0002082\u0006\u00103\u001a\u0002082\u0006\u00104\u001a\u0002082\u0006\u00105\u001a\u00020\u0018H\u0007¢\u0006\u0004\bE\u0010F\u001a\u001f\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\u00022\u0006\u0010J\u001a\u00020\u0002H\u0007¢\u0006\u0004\bK\u0010<\u001a\"\u0010\n\u001a\u00020H*\u00020H2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020H0\fH\u0086\b¢\u0006\u0004\bP\u0010D\u001a\u001c\u0010\u001a\u001a\u00020H*\u00020\u00102\u0006\u0010U\u001a\u00020HH\u0087\n¢\u0006\u0004\bV\u0010W\u001a\u001c\u0010\u001a\u001a\u00020H*\u00020\u00182\u0006\u0010U\u001a\u00020HH\u0087\n¢\u0006\u0004\bV\u0010X\u001a'\u00102\u001a\u00020H2\u0006\u00103\u001a\u00020H2\u0006\u00104\u001a\u00020H2\u0006\u00105\u001a\u00020\u0018H\u0007¢\u0006\u0004\bY\u0010F\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001f\u0010\u0007\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\u0006\"\u001f\u0010\u000f\u001a\u00020\u0002*\u00020\u00108Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u001f\u0010\u000f\u001a\u00020\u0002*\u00020\u00158Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0016\u001a\u0004\b\u0013\u0010\u0017\"\u001f\u0010\u000f\u001a\u00020\u0002*\u00020\u00188Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0013\u0010\u0019\"\u001f\u0010/\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b0\u0010\u0004\u001a\u0004\b1\u0010\u0006\"\u001f\u0010\u0000\u001a\u00020\u0001*\u0002088Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b=\u0010>\u001a\u0004\b?\u0010@\"\u001f\u0010\u0007\u001a\u00020\u0001*\u0002088Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\bA\u0010>\u001a\u0004\bB\u0010@\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020H8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\bL\u0010>\u001a\u0004\bM\u0010@\"\u001f\u0010\u0007\u001a\u00020\u0001*\u00020H8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\bN\u0010>\u001a\u0004\bO\u0010@\"\u001e\u0010Q\u001a\u000208*\u00020H8FX\u0087\u0004¢\u0006\f\u0012\u0004\bR\u0010>\u001a\u0004\bS\u0010T\"\u001f\u0010I\u001a\u00020\u0002*\u00020Z8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b[\u0010\\\u001a\u0004\b]\u0010^\"\u001f\u0010J\u001a\u00020\u0002*\u00020Z8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b_\u0010\\\u001a\u0004\b`\u0010^\"\u001f\u0010U\u001a\u00020H*\u00020Z8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\ba\u0010\\\u001a\u0004\bb\u0010c¨\u0006d"}, d2 = {"isSpecified", "", "Landroidx/compose/ui/unit/Dp;", "isSpecified-0680j_4$annotations", "(F)V", "isSpecified-0680j_4", "(F)Z", "isUnspecified", "isUnspecified-0680j_4$annotations", "isUnspecified-0680j_4", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse-D5KLDUw", "(FLkotlin/jvm/functions/Function0;)F", "dp", "", "getDp$annotations", "(I)V", "getDp", "(I)F", "", "(D)V", "(D)F", "", "(F)F", "times", "other", "times-3ABfNKs", "(FF)F", "(DF)F", "(IF)F", MetadataValidation.MIN, CmcdHeadersFactory.OBJECT_TYPE_AUDIO_ONLY, "b", "min-YgX7TsA", MetadataValidation.MAX, "max-YgX7TsA", "coerceIn", "minimumValue", "maximumValue", "coerceIn-2z7ARbQ", "(FFF)F", "coerceAtLeast", "coerceAtLeast-YgX7TsA", "coerceAtMost", "coerceAtMost-YgX7TsA", "isFinite", "isFinite-0680j_4$annotations", "isFinite-0680j_4", "lerp", TtmlNode.START, "stop", "fraction", "lerp-Md-fbLM", "DpOffset", "Landroidx/compose/ui/unit/DpOffset;", "x", "y", "DpOffset-YgX7TsA", "(FF)J", "isSpecified-jo-Fl9I$annotations", "(J)V", "isSpecified-jo-Fl9I", "(J)Z", "isUnspecified-jo-Fl9I$annotations", "isUnspecified-jo-Fl9I", "takeOrElse-gVKV90s", "(JLkotlin/jvm/functions/Function0;)J", "lerp-xhh869w", "(JJF)J", "DpSize", "Landroidx/compose/ui/unit/DpSize;", "width", "height", "DpSize-YgX7TsA", "isSpecified-EaSLcWc$annotations", "isSpecified-EaSLcWc", "isUnspecified-EaSLcWc$annotations", "isUnspecified-EaSLcWc", "takeOrElse-itqla9I", TtmlNode.CENTER, "getCenter-EaSLcWc$annotations", "getCenter-EaSLcWc", "(J)J", "size", "times-6HolHcs", "(IJ)J", "(FJ)J", "lerp-IDex15A", "Landroidx/compose/ui/unit/DpRect;", "getWidth$annotations", "(Landroidx/compose/ui/unit/DpRect;)V", "getWidth", "(Landroidx/compose/ui/unit/DpRect;)F", "getHeight$annotations", "getHeight", "getSize$annotations", "getSize", "(Landroidx/compose/ui/unit/DpRect;)J", "ui-unit"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DpKt {
    /* JADX INFO: renamed from: getCenter-EaSLcWc$annotations, reason: not valid java name */
    public static /* synthetic */ void m7932getCenterEaSLcWc$annotations(long j) {
    }

    public static /* synthetic */ void getDp$annotations(double d) {
    }

    public static /* synthetic */ void getDp$annotations(float f) {
    }

    public static /* synthetic */ void getDp$annotations(int i) {
    }

    public static /* synthetic */ void getHeight$annotations(DpRect dpRect) {
    }

    public static /* synthetic */ void getSize$annotations(DpRect dpRect) {
    }

    public static /* synthetic */ void getWidth$annotations(DpRect dpRect) {
    }

    /* JADX INFO: renamed from: isFinite-0680j_4$annotations, reason: not valid java name */
    public static /* synthetic */ void m7934isFinite0680j_4$annotations(float f) {
    }

    /* JADX INFO: renamed from: isSpecified-0680j_4$annotations, reason: not valid java name */
    public static /* synthetic */ void m7936isSpecified0680j_4$annotations(float f) {
    }

    /* JADX INFO: renamed from: isSpecified-EaSLcWc$annotations, reason: not valid java name */
    public static /* synthetic */ void m7938isSpecifiedEaSLcWc$annotations(long j) {
    }

    /* JADX INFO: renamed from: isSpecified-jo-Fl9I$annotations, reason: not valid java name */
    public static /* synthetic */ void m7940isSpecifiedjoFl9I$annotations(long j) {
    }

    /* JADX INFO: renamed from: isUnspecified-0680j_4$annotations, reason: not valid java name */
    public static /* synthetic */ void m7942isUnspecified0680j_4$annotations(float f) {
    }

    /* JADX INFO: renamed from: isUnspecified-EaSLcWc$annotations, reason: not valid java name */
    public static /* synthetic */ void m7944isUnspecifiedEaSLcWc$annotations(long j) {
    }

    /* JADX INFO: renamed from: isUnspecified-jo-Fl9I$annotations, reason: not valid java name */
    public static /* synthetic */ void m7946isUnspecifiedjoFl9I$annotations(long j) {
    }

    /* JADX INFO: renamed from: isSpecified-0680j_4, reason: not valid java name */
    public static final boolean m7935isSpecified0680j_4(float f) {
        return !Float.isNaN(f);
    }

    /* JADX INFO: renamed from: isUnspecified-0680j_4, reason: not valid java name */
    public static final boolean m7941isUnspecified0680j_4(float f) {
        return Float.isNaN(f);
    }

    /* JADX INFO: renamed from: takeOrElse-D5KLDUw, reason: not valid java name */
    public static final float m7952takeOrElseD5KLDUw(float f, Function0<Dp> function0) {
        return !Float.isNaN(f) ? f : function0.invoke().m7919unboximpl();
    }

    public static final float getDp(int $this$dp) {
        return Dp.m7905constructorimpl($this$dp);
    }

    public static final float getDp(double $this$dp) {
        return Dp.m7905constructorimpl((float) $this$dp);
    }

    public static final float getDp(float $this$dp) {
        return Dp.m7905constructorimpl($this$dp);
    }

    /* JADX INFO: renamed from: times-3ABfNKs, reason: not valid java name */
    public static final float m7956times3ABfNKs(float $this$times_u2d3ABfNKs, float f) {
        return Dp.m7905constructorimpl($this$times_u2d3ABfNKs * f);
    }

    /* JADX INFO: renamed from: times-3ABfNKs, reason: not valid java name */
    public static final float m7955times3ABfNKs(double $this$times_u2d3ABfNKs, float f) {
        return Dp.m7905constructorimpl(((float) $this$times_u2d3ABfNKs) * f);
    }

    /* JADX INFO: renamed from: times-3ABfNKs, reason: not valid java name */
    public static final float m7957times3ABfNKs(int $this$times_u2d3ABfNKs, float f) {
        return Dp.m7905constructorimpl($this$times_u2d3ABfNKs * f);
    }

    /* JADX INFO: renamed from: min-YgX7TsA, reason: not valid java name */
    public static final float m7951minYgX7TsA(float f, float f2) {
        return Dp.m7905constructorimpl(Math.min(f, f2));
    }

    /* JADX INFO: renamed from: max-YgX7TsA, reason: not valid java name */
    public static final float m7950maxYgX7TsA(float f, float f2) {
        return Dp.m7905constructorimpl(Math.max(f, f2));
    }

    /* JADX INFO: renamed from: coerceIn-2z7ARbQ, reason: not valid java name */
    public static final float m7930coerceIn2z7ARbQ(float f, float f2, float f3) {
        return Dp.m7905constructorimpl(RangesKt.coerceIn(f, f2, f3));
    }

    /* JADX INFO: renamed from: coerceAtLeast-YgX7TsA, reason: not valid java name */
    public static final float m7928coerceAtLeastYgX7TsA(float f, float f2) {
        return Dp.m7905constructorimpl(RangesKt.coerceAtLeast(f, f2));
    }

    /* JADX INFO: renamed from: coerceAtMost-YgX7TsA, reason: not valid java name */
    public static final float m7929coerceAtMostYgX7TsA(float f, float f2) {
        return Dp.m7905constructorimpl(RangesKt.coerceAtMost(f, f2));
    }

    /* JADX INFO: renamed from: isFinite-0680j_4, reason: not valid java name */
    public static final boolean m7933isFinite0680j_4(float f) {
        return (Float.floatToRawIntBits(f) & Integer.MAX_VALUE) < 2139095040;
    }

    /* JADX INFO: renamed from: lerp-Md-fbLM, reason: not valid java name */
    public static final float m7948lerpMdfbLM(float f, float f2, float fraction) {
        return Dp.m7905constructorimpl(MathHelpersKt.lerp(f, f2, fraction));
    }

    /* JADX INFO: renamed from: DpOffset-YgX7TsA, reason: not valid java name */
    public static final long m7926DpOffsetYgX7TsA(float f, float f2) {
        long v1$iv = Float.floatToRawIntBits(f);
        long v2$iv = Float.floatToRawIntBits(f2);
        return DpOffset.m7961constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* JADX INFO: renamed from: isSpecified-jo-Fl9I, reason: not valid java name */
    public static final boolean m7939isSpecifiedjoFl9I(long j) {
        return j != androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats;
    }

    /* JADX INFO: renamed from: isUnspecified-jo-Fl9I, reason: not valid java name */
    public static final boolean m7945isUnspecifiedjoFl9I(long j) {
        return j == androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats;
    }

    /* JADX INFO: renamed from: takeOrElse-gVKV90s, reason: not valid java name */
    public static final long m7953takeOrElsegVKV90s(long j, Function0<DpOffset> function0) {
        return (j > androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats ? 1 : (j == androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats ? 0 : -1)) != 0 ? j : function0.invoke().m7974unboximpl();
    }

    /* JADX INFO: renamed from: lerp-xhh869w, reason: not valid java name */
    public static final long m7949lerpxhh869w(long j, long j2, float fraction) {
        float val1$iv = MathHelpersKt.lerp(DpOffset.m7966getXD9Ej5fM(j), DpOffset.m7966getXD9Ej5fM(j2), fraction);
        float val2$iv = MathHelpersKt.lerp(DpOffset.m7968getYD9Ej5fM(j), DpOffset.m7968getYD9Ej5fM(j2), fraction);
        long v1$iv = Float.floatToRawIntBits(val1$iv);
        long v2$iv = Float.floatToRawIntBits(val2$iv);
        return DpOffset.m7961constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* JADX INFO: renamed from: DpSize-YgX7TsA, reason: not valid java name */
    public static final long m7927DpSizeYgX7TsA(float f, float f2) {
        long v1$iv = Float.floatToRawIntBits(f);
        long v2$iv = Float.floatToRawIntBits(f2);
        return DpSize.m7994constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* JADX INFO: renamed from: isSpecified-EaSLcWc, reason: not valid java name */
    public static final boolean m7937isSpecifiedEaSLcWc(long j) {
        return j != androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats;
    }

    /* JADX INFO: renamed from: isUnspecified-EaSLcWc, reason: not valid java name */
    public static final boolean m7943isUnspecifiedEaSLcWc(long j) {
        return j == androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats;
    }

    /* JADX INFO: renamed from: takeOrElse-itqla9I, reason: not valid java name */
    public static final long m7954takeOrElseitqla9I(long j, Function0<DpSize> function0) {
        return (j > androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats ? 1 : (j == androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats ? 0 : -1)) != 0 ? j : function0.invoke().getPackedValue();
    }

    /* JADX INFO: renamed from: getCenter-EaSLcWc, reason: not valid java name */
    public static final long m7931getCenterEaSLcWc(long j) {
        float fM7905constructorimpl = Dp.m7905constructorimpl(DpSize.m8003getWidthD9Ej5fM(j) / 2.0f);
        float fM7905constructorimpl2 = Dp.m7905constructorimpl(DpSize.m8001getHeightD9Ej5fM(j) / 2.0f);
        long v1$iv = Float.floatToRawIntBits(fM7905constructorimpl);
        long v2$iv = Float.floatToRawIntBits(fM7905constructorimpl2);
        return DpOffset.m7961constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* JADX INFO: renamed from: times-6HolHcs, reason: not valid java name */
    public static final long m7959times6HolHcs(int $this$times_u2d6HolHcs, long j) {
        return DpSize.m8009timesGh9hcWk(j, $this$times_u2d6HolHcs);
    }

    /* JADX INFO: renamed from: times-6HolHcs, reason: not valid java name */
    public static final long m7958times6HolHcs(float $this$times_u2d6HolHcs, long j) {
        return DpSize.m8008timesGh9hcWk(j, $this$times_u2d6HolHcs);
    }

    /* JADX INFO: renamed from: lerp-IDex15A, reason: not valid java name */
    public static final long m7947lerpIDex15A(long j, long j2, float fraction) {
        float val1$iv = m7948lerpMdfbLM(DpSize.m8003getWidthD9Ej5fM(j), DpSize.m8003getWidthD9Ej5fM(j2), fraction);
        float val2$iv = m7948lerpMdfbLM(DpSize.m8001getHeightD9Ej5fM(j), DpSize.m8001getHeightD9Ej5fM(j2), fraction);
        long v1$iv = Float.floatToRawIntBits(val1$iv);
        long v2$iv = Float.floatToRawIntBits(val2$iv);
        return DpSize.m7994constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    public static final float getWidth(DpRect $this$width) {
        return Dp.m7905constructorimpl($this$width.m7989getRightD9Ej5fM() - $this$width.m7988getLeftD9Ej5fM());
    }

    public static final float getHeight(DpRect $this$height) {
        return Dp.m7905constructorimpl($this$height.m7987getBottomD9Ej5fM() - $this$height.m7990getTopD9Ej5fM());
    }

    public static final long getSize(DpRect $this$size) {
        return m7927DpSizeYgX7TsA(Dp.m7905constructorimpl($this$size.m7989getRightD9Ej5fM() - $this$size.m7988getLeftD9Ej5fM()), Dp.m7905constructorimpl($this$size.m7987getBottomD9Ej5fM() - $this$size.m7990getTopD9Ej5fM()));
    }
}
