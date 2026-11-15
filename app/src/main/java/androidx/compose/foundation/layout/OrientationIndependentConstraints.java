package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;

/* JADX INFO: compiled from: RowColumnImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0081@\u0018\u00002\u00020\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B)\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\u000bB\u0019\b\u0016\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u0004\u0010\u000fJ\r\u0010\u0016\u001a\u00020\u0000¢\u0006\u0004\b\u0017\u0010\u0005J\u0015\u0010\u0018\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u0019\u0010\u000fJ\u0015\u0010\u001a\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001d\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u001e\u0010\u001cJ5\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b \u0010!J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0007HÖ\u0001J\t\u0010&\u001a\u00020'HÖ\u0001R\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u0012\u0010\u0006\u001a\u00020\u00078Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\b\u001a\u00020\u00078Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0012R\u0012\u0010\t\u001a\u00020\u00078Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0012\u0010\n\u001a\u00020\u00078Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0012\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006("}, d2 = {"Landroidx/compose/foundation/layout/OrientationIndependentConstraints;", "", "value", "Landroidx/compose/ui/unit/Constraints;", "constructor-impl", "(J)J", "mainAxisMin", "", "mainAxisMax", "crossAxisMin", "crossAxisMax", "(IIII)J", "c", "orientation", "Landroidx/compose/foundation/layout/LayoutOrientation;", "(JLandroidx/compose/foundation/layout/LayoutOrientation;)J", "J", "getMainAxisMin-impl", "(J)I", "getMainAxisMax-impl", "getCrossAxisMin-impl", "getCrossAxisMax-impl", "stretchCrossAxis", "stretchCrossAxis-q4ezo7Y", "toBoxConstraints", "toBoxConstraints-OenEA2s", "maxWidth", "maxWidth-impl", "(JLandroidx/compose/foundation/layout/LayoutOrientation;)I", "maxHeight", "maxHeight-impl", "copy", "copy-yUG9Ft0", "(JIIII)J", MetadataValidation.EQUALS, "", "other", "hashCode", "toString", "", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
@JvmInline
public final class OrientationIndependentConstraints {
    private final long value;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ OrientationIndependentConstraints m785boximpl(long j) {
        return new OrientationIndependentConstraints(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static long m787constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m791equalsimpl(long j, Object obj) {
        return (obj instanceof OrientationIndependentConstraints) && Constraints.m7851equalsimpl0(j, ((OrientationIndependentConstraints) obj).getValue());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m792equalsimpl0(long j, long j2) {
        return Constraints.m7851equalsimpl0(j, j2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m797hashCodeimpl(long j) {
        return Constraints.m7861hashCodeimpl(j);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m802toStringimpl(long j) {
        return "OrientationIndependentConstraints(value=" + ((Object) Constraints.m7863toStringimpl(j)) + ')';
    }

    public boolean equals(Object other) {
        return m791equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m797hashCodeimpl(this.value);
    }

    public String toString() {
        return m802toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getValue() {
        return this.value;
    }

    private /* synthetic */ OrientationIndependentConstraints(long value) {
        this.value = value;
    }

    /* JADX INFO: renamed from: getMainAxisMin-impl, reason: not valid java name */
    public static final int m796getMainAxisMinimpl(long j) {
        return Constraints.m7860getMinWidthimpl(j);
    }

    /* JADX INFO: renamed from: getMainAxisMax-impl, reason: not valid java name */
    public static final int m795getMainAxisMaximpl(long j) {
        return Constraints.m7858getMaxWidthimpl(j);
    }

    /* JADX INFO: renamed from: getCrossAxisMin-impl, reason: not valid java name */
    public static final int m794getCrossAxisMinimpl(long j) {
        return Constraints.m7859getMinHeightimpl(j);
    }

    /* JADX INFO: renamed from: getCrossAxisMax-impl, reason: not valid java name */
    public static final int m793getCrossAxisMaximpl(long j) {
        return Constraints.m7857getMaxHeightimpl(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m786constructorimpl(int mainAxisMin, int mainAxisMax, int crossAxisMin, int crossAxisMax) {
        return m787constructorimpl(ConstraintsKt.Constraints(mainAxisMin, mainAxisMax, crossAxisMin, crossAxisMax));
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m788constructorimpl(long j, LayoutOrientation orientation) {
        return m786constructorimpl(orientation == LayoutOrientation.Horizontal ? Constraints.m7860getMinWidthimpl(j) : Constraints.m7859getMinHeightimpl(j), orientation == LayoutOrientation.Horizontal ? Constraints.m7858getMaxWidthimpl(j) : Constraints.m7857getMaxHeightimpl(j), orientation == LayoutOrientation.Horizontal ? Constraints.m7859getMinHeightimpl(j) : Constraints.m7860getMinWidthimpl(j), orientation == LayoutOrientation.Horizontal ? Constraints.m7857getMaxHeightimpl(j) : Constraints.m7858getMaxWidthimpl(j));
    }

    /* JADX INFO: renamed from: stretchCrossAxis-q4ezo7Y, reason: not valid java name */
    public static final long m800stretchCrossAxisq4ezo7Y(long j) {
        int iM7859getMinHeightimpl;
        int iM7860getMinWidthimpl = Constraints.m7860getMinWidthimpl(j);
        int iM7858getMaxWidthimpl = Constraints.m7858getMaxWidthimpl(j);
        if (Constraints.m7857getMaxHeightimpl(j) != Integer.MAX_VALUE) {
            iM7859getMinHeightimpl = Constraints.m7857getMaxHeightimpl(j);
        } else {
            iM7859getMinHeightimpl = Constraints.m7859getMinHeightimpl(j);
        }
        return m786constructorimpl(iM7860getMinWidthimpl, iM7858getMaxWidthimpl, iM7859getMinHeightimpl, Constraints.m7857getMaxHeightimpl(j));
    }

    /* JADX INFO: renamed from: toBoxConstraints-OenEA2s, reason: not valid java name */
    public static final long m801toBoxConstraintsOenEA2s(long j, LayoutOrientation orientation) {
        return orientation == LayoutOrientation.Horizontal ? ConstraintsKt.Constraints(Constraints.m7860getMinWidthimpl(j), Constraints.m7858getMaxWidthimpl(j), Constraints.m7859getMinHeightimpl(j), Constraints.m7857getMaxHeightimpl(j)) : ConstraintsKt.Constraints(Constraints.m7859getMinHeightimpl(j), Constraints.m7857getMaxHeightimpl(j), Constraints.m7860getMinWidthimpl(j), Constraints.m7858getMaxWidthimpl(j));
    }

    /* JADX INFO: renamed from: maxWidth-impl, reason: not valid java name */
    public static final int m799maxWidthimpl(long j, LayoutOrientation orientation) {
        if (orientation == LayoutOrientation.Horizontal) {
            return Constraints.m7858getMaxWidthimpl(j);
        }
        return Constraints.m7857getMaxHeightimpl(j);
    }

    /* JADX INFO: renamed from: maxHeight-impl, reason: not valid java name */
    public static final int m798maxHeightimpl(long j, LayoutOrientation orientation) {
        if (orientation == LayoutOrientation.Horizontal) {
            return Constraints.m7857getMaxHeightimpl(j);
        }
        return Constraints.m7858getMaxWidthimpl(j);
    }

    /* JADX INFO: renamed from: copy-yUG9Ft0, reason: not valid java name */
    public static final long m789copyyUG9Ft0(long j, int mainAxisMin, int mainAxisMax, int crossAxisMin, int crossAxisMax) {
        return m786constructorimpl(mainAxisMin, mainAxisMax, crossAxisMin, crossAxisMax);
    }
}
