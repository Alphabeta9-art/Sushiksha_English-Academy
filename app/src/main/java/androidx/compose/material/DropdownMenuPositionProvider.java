package androidx.compose.material;

import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;
import com.cloudinary.metadata.MetadataValidation;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: Menu.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0081\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u001a\b\u0002\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0004\b\n\u0010\u000bJ/\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u001e\u0010\rJ\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\u001b\u0010 \u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007HÆ\u0003J@\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u001a\b\u0002\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007HÆ\u0001¢\u0006\u0004\b\"\u0010#J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020+HÖ\u0001R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R#\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006,"}, d2 = {"Landroidx/compose/material/DropdownMenuPositionProvider;", "Landroidx/compose/ui/window/PopupPositionProvider;", "contentOffset", "Landroidx/compose/ui/unit/DpOffset;", "density", "Landroidx/compose/ui/unit/Density;", "onPositionCalculated", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/IntRect;", "", "<init>", "(JLandroidx/compose/ui/unit/Density;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getContentOffset-RKDOV3M", "()J", "J", "getDensity", "()Landroidx/compose/ui/unit/Density;", "getOnPositionCalculated", "()Lkotlin/jvm/functions/Function2;", "calculatePosition", "Landroidx/compose/ui/unit/IntOffset;", "anchorBounds", "windowSize", "Landroidx/compose/ui/unit/IntSize;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "popupContentSize", "calculatePosition-llwVHH4", "(Landroidx/compose/ui/unit/IntRect;JLandroidx/compose/ui/unit/LayoutDirection;J)J", "component1", "component1-RKDOV3M", "component2", "component3", "copy", "copy-rOJDEFc", "(JLandroidx/compose/ui/unit/Density;Lkotlin/jvm/functions/Function2;)Landroidx/compose/material/DropdownMenuPositionProvider;", MetadataValidation.EQUALS, "", "other", "", "hashCode", "", "toString", "", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class DropdownMenuPositionProvider implements PopupPositionProvider {
    public static final int $stable = 0;
    private final long contentOffset;
    private final Density density;
    private final Function2<IntRect, IntRect, Unit> onPositionCalculated;

    public /* synthetic */ DropdownMenuPositionProvider(long j, Density density, Function2 function2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, density, function2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: copy-rOJDEFc$default, reason: not valid java name */
    public static /* synthetic */ DropdownMenuPositionProvider m1812copyrOJDEFc$default(DropdownMenuPositionProvider dropdownMenuPositionProvider, long j, Density density, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = dropdownMenuPositionProvider.contentOffset;
        }
        if ((i & 2) != 0) {
            density = dropdownMenuPositionProvider.density;
        }
        if ((i & 4) != 0) {
            function2 = dropdownMenuPositionProvider.onPositionCalculated;
        }
        return dropdownMenuPositionProvider.m1814copyrOJDEFc(j, density, function2);
    }

    /* JADX INFO: renamed from: component1-RKDOV3M, reason: not valid java name and from getter */
    public final long getContentOffset() {
        return this.contentOffset;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    public final Function2<IntRect, IntRect, Unit> component3() {
        return this.onPositionCalculated;
    }

    /* JADX INFO: renamed from: copy-rOJDEFc, reason: not valid java name */
    public final DropdownMenuPositionProvider m1814copyrOJDEFc(long contentOffset, Density density, Function2<? super IntRect, ? super IntRect, Unit> onPositionCalculated) {
        return new DropdownMenuPositionProvider(contentOffset, density, onPositionCalculated, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DropdownMenuPositionProvider)) {
            return false;
        }
        DropdownMenuPositionProvider dropdownMenuPositionProvider = (DropdownMenuPositionProvider) other;
        return DpOffset.m7965equalsimpl0(this.contentOffset, dropdownMenuPositionProvider.contentOffset) && Intrinsics.areEqual(this.density, dropdownMenuPositionProvider.density) && Intrinsics.areEqual(this.onPositionCalculated, dropdownMenuPositionProvider.onPositionCalculated);
    }

    public int hashCode() {
        return (((DpOffset.m7970hashCodeimpl(this.contentOffset) * 31) + this.density.hashCode()) * 31) + this.onPositionCalculated.hashCode();
    }

    public String toString() {
        return "DropdownMenuPositionProvider(contentOffset=" + ((Object) DpOffset.m7973toStringimpl(this.contentOffset)) + ", density=" + this.density + ", onPositionCalculated=" + this.onPositionCalculated + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    private DropdownMenuPositionProvider(long contentOffset, Density density, Function2<? super IntRect, ? super IntRect, Unit> function2) {
        this.contentOffset = contentOffset;
        this.density = density;
        this.onPositionCalculated = function2;
    }

    public /* synthetic */ DropdownMenuPositionProvider(long j, Density density, Function2 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Function2 function3;
        if ((i & 4) == 0) {
            function3 = function2;
        } else {
            function3 = new Function2() { // from class: androidx.compose.material.DropdownMenuPositionProvider$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return Unit.INSTANCE;
                }
            };
        }
        this(j, density, function3, null);
    }

    /* JADX INFO: renamed from: getContentOffset-RKDOV3M, reason: not valid java name */
    public final long m1815getContentOffsetRKDOV3M() {
        return this.contentOffset;
    }

    public final Density getDensity() {
        return this.density;
    }

    public final Function2<IntRect, IntRect, Unit> getOnPositionCalculated() {
        return this.onPositionCalculated;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0113 A[LOOP:0: B:19:0x00cf->B:29:0x0113, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:47:0x01ee A[LOOP:1: B:36:0x01ab->B:47:0x01ee, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:56:0x0121 A[EDGE_INSN: B:56:0x0121->B:31:0x0121 BREAK  A[LOOP:0: B:19:0x00cf->B:29:0x0113], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:58:0x01ec A[SYNTHETIC] */
    @Override // androidx.compose.ui.window.PopupPositionProvider
    /* JADX INFO: renamed from: calculatePosition-llwVHH4 */
    public long mo374calculatePositionllwVHH4(IntRect anchorBounds, long windowSize, LayoutDirection layoutDirection, long popupContentSize) {
        Sequence $this$firstOrNull$iv;
        char c;
        char c2;
        char c3;
        Object element$iv;
        Object obj;
        int verticalMargin;
        int bottomToWindowBottom;
        char c4;
        char c5;
        Density $this$calculatePosition_llwVHH4_u24lambda_u240 = this.density;
        int verticalMargin2 = $this$calculatePosition_llwVHH4_u24lambda_u240.mo400roundToPx0680j_4(MenuKt.getMenuVerticalMargin());
        Density $this$calculatePosition_llwVHH4_u24lambda_u241 = this.density;
        int contentOffsetX = $this$calculatePosition_llwVHH4_u24lambda_u241.mo400roundToPx0680j_4(DpOffset.m7966getXD9Ej5fM(this.contentOffset)) * (layoutDirection == LayoutDirection.Ltr ? 1 : -1);
        Density $this$calculatePosition_llwVHH4_u24lambda_u242 = this.density;
        int contentOffsetY = $this$calculatePosition_llwVHH4_u24lambda_u242.mo400roundToPx0680j_4(DpOffset.m7968getYD9Ej5fM(this.contentOffset));
        int leftToAnchorLeft = anchorBounds.getLeft() + contentOffsetX;
        int rightToAnchorRight = (anchorBounds.getRight() - ((int) (popupContentSize >> 32))) + contentOffsetX;
        char c6 = ' ';
        int rightToWindowRight = ((int) (windowSize >> 32)) - ((int) (popupContentSize >> 32));
        char c7 = 2;
        char c8 = 0;
        if (layoutDirection == LayoutDirection.Ltr) {
            Integer[] numArr = new Integer[3];
            numArr[0] = Integer.valueOf(leftToAnchorLeft);
            numArr[1] = Integer.valueOf(rightToAnchorRight);
            numArr[2] = Integer.valueOf(anchorBounds.getLeft() >= 0 ? rightToWindowRight : 0);
            $this$firstOrNull$iv = SequencesKt.sequenceOf((Object[]) numArr);
        } else {
            Integer[] numArr2 = new Integer[3];
            numArr2[0] = Integer.valueOf(rightToAnchorRight);
            numArr2[1] = Integer.valueOf(leftToAnchorLeft);
            numArr2[2] = Integer.valueOf(anchorBounds.getRight() <= ((int) (windowSize >> 32)) ? 0 : rightToWindowRight);
            $this$firstOrNull$iv = SequencesKt.sequenceOf((Object[]) numArr2);
        }
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                c = c7;
                c2 = c8;
                c3 = c6;
                element$iv = null;
                break;
            }
            element$iv = it.next();
            int it2 = ((Number) element$iv).intValue();
            if (it2 >= 0) {
                c = c7;
                c2 = c8;
                c3 = c6;
                if (it2 + ((int) (popupContentSize >> c6)) <= ((int) (windowSize >> c3))) {
                    c5 = 1;
                }
                if (c5 == 0) {
                    break;
                }
                c6 = c3;
                c7 = c;
                c8 = c2;
            } else {
                c = c7;
                c2 = c8;
                c3 = c6;
            }
            c5 = c2;
            if (c5 == 0) {
                break;
                break;
            }
            c6 = c3;
            c7 = c;
            c8 = c2;
        }
        Integer num = (Integer) element$iv;
        int x = num != null ? num.intValue() : rightToAnchorRight;
        int topToAnchorBottom = Math.max(anchorBounds.getBottom() + contentOffsetY, verticalMargin2);
        int bottomToAnchorTop = (anchorBounds.getTop() - ((int) (popupContentSize & 4294967295L))) + contentOffsetY;
        int centerToAnchorTop = (anchorBounds.getTop() - (((int) (popupContentSize & 4294967295L)) / 2)) + contentOffsetY;
        int bottomToWindowBottom2 = (((int) (windowSize & 4294967295L)) - ((int) (popupContentSize & 4294967295L))) - verticalMargin2;
        Integer[] numArr3 = new Integer[4];
        numArr3[c2] = Integer.valueOf(topToAnchorBottom);
        numArr3[1] = Integer.valueOf(bottomToAnchorTop);
        numArr3[c] = Integer.valueOf(centerToAnchorTop);
        numArr3[3] = Integer.valueOf(bottomToWindowBottom2);
        Sequence $this$firstOrNull$iv2 = SequencesKt.sequenceOf((Object[]) numArr3);
        Iterator it3 = $this$firstOrNull$iv2.iterator();
        while (true) {
            if (!it3.hasNext()) {
                obj = null;
                break;
            }
            Object element$iv2 = it3.next();
            int it4 = ((Number) element$iv2).intValue();
            if (it4 >= verticalMargin2) {
                verticalMargin = verticalMargin2;
                bottomToWindowBottom = bottomToWindowBottom2;
                if (((int) (popupContentSize & 4294967295L)) + it4 <= ((int) (windowSize & 4294967295L)) - verticalMargin) {
                    c4 = 1;
                }
                if (c4 != 0) {
                    obj = element$iv2;
                    break;
                }
                bottomToWindowBottom2 = bottomToWindowBottom;
                verticalMargin2 = verticalMargin;
            } else {
                verticalMargin = verticalMargin2;
                bottomToWindowBottom = bottomToWindowBottom2;
            }
            c4 = c2;
            if (c4 != 0) {
                obj = element$iv2;
                break;
            }
            bottomToWindowBottom2 = bottomToWindowBottom;
            verticalMargin2 = verticalMargin;
        }
        Integer num2 = (Integer) obj;
        int y = num2 != null ? num2.intValue() : bottomToAnchorTop;
        this.onPositionCalculated.invoke(anchorBounds, new IntRect(x, y, ((int) (popupContentSize >> c3)) + x, ((int) (popupContentSize & 4294967295L)) + y));
        int y$iv = y;
        int x$iv = x;
        return IntOffset.m8027constructorimpl((((long) x$iv) << c3) | (((long) y$iv) & 4294967295L));
    }
}
