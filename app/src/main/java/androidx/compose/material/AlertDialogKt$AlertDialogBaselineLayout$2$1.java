package androidx.compose.material;

import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlertDialog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class AlertDialogKt$AlertDialogBaselineLayout$2$1 implements MeasurePolicy {
    public static final AlertDialogKt$AlertDialogBaselineLayout$2$1 INSTANCE = new AlertDialogKt$AlertDialogBaselineLayout$2$1();

    AlertDialogKt$AlertDialogBaselineLayout$2$1() {
    }

    /* JADX WARN: Code duplicated, block: B:38:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:47:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:55:0x011a  */
    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo42measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long j) {
        Object it$iv;
        Placeable placeableMo6541measureBRTryo0;
        Object it$iv2;
        Placeable placeableMo6541measureBRTryo1;
        int iIntValue;
        int iIntValue2;
        int iIntValue3;
        int height;
        final int textPositionY;
        int index$iv$iv = 0;
        int size = list.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = list.get(index$iv$iv);
                it$iv = item$iv$iv;
                Measurable it = (Measurable) it$iv;
                if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "title")) {
                    break;
                }
                index$iv$iv++;
            } else {
                it$iv = null;
                break;
            }
        }
        Measurable measurable = (Measurable) it$iv;
        if (measurable != null) {
            placeableMo6541measureBRTryo0 = measurable.mo6541measureBRTryo0(Constraints.m7847copyZbe2FdA(j, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(j) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(j) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(j) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(j) : 0));
        } else {
            placeableMo6541measureBRTryo0 = null;
        }
        final Placeable titlePlaceable = placeableMo6541measureBRTryo0;
        int index$iv$iv2 = 0;
        int size2 = list.size();
        while (true) {
            if (index$iv$iv2 < size2) {
                Object item$iv$iv2 = list.get(index$iv$iv2);
                it$iv2 = item$iv$iv2;
                Measurable it2 = (Measurable) it$iv2;
                if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "text")) {
                    break;
                }
                index$iv$iv2++;
            } else {
                it$iv2 = null;
                break;
            }
        }
        Measurable measurable2 = (Measurable) it$iv2;
        if (measurable2 != null) {
            placeableMo6541measureBRTryo1 = measurable2.mo6541measureBRTryo0(Constraints.m7847copyZbe2FdA(j, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(j) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(j) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(j) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(j) : 0));
        } else {
            placeableMo6541measureBRTryo1 = null;
        }
        final Placeable textPlaceable = placeableMo6541measureBRTryo1;
        int height2 = 0;
        int layoutWidth = Math.max(titlePlaceable != null ? titlePlaceable.getWidth() : 0, textPlaceable != null ? textPlaceable.getWidth() : 0);
        if (titlePlaceable != null) {
            int baseline = Integer.valueOf(titlePlaceable.get(AlignmentLineKt.getFirstBaseline())).intValue();
            Integer numValueOf = baseline == Integer.MIN_VALUE ? null : Integer.valueOf(baseline);
            if (numValueOf != null) {
                iIntValue = numValueOf.intValue();
            } else {
                iIntValue = 0;
            }
        } else {
            iIntValue = 0;
        }
        int firstTitleBaseline = iIntValue;
        if (titlePlaceable != null) {
            int baseline2 = titlePlaceable.get(AlignmentLineKt.getLastBaseline());
            Integer numValueOf2 = baseline2 == Integer.MIN_VALUE ? null : Integer.valueOf(baseline2);
            if (numValueOf2 != null) {
                iIntValue2 = numValueOf2.intValue();
            } else {
                iIntValue2 = 0;
            }
        } else {
            iIntValue2 = 0;
        }
        int lastTitleBaseline = iIntValue2;
        int titleOffset = $this$Layout.mo399roundToPxR2X_6o(AlertDialogKt.TitleBaselineDistanceFromTop);
        final int titlePositionY = titleOffset - firstTitleBaseline;
        if (textPlaceable != null) {
            int baseline3 = textPlaceable.get(AlignmentLineKt.getFirstBaseline());
            Integer numValueOf3 = baseline3 != Integer.MIN_VALUE ? Integer.valueOf(baseline3) : null;
            if (numValueOf3 != null) {
                iIntValue3 = numValueOf3.intValue();
            } else {
                iIntValue3 = 0;
            }
        } else {
            iIntValue3 = 0;
        }
        int firstTextBaseline = iIntValue3;
        int textOffset = titlePlaceable == null ? $this$Layout.mo399roundToPxR2X_6o(AlertDialogKt.TextBaselineDistanceFromTop) : $this$Layout.mo399roundToPxR2X_6o(AlertDialogKt.TextBaselineDistanceFromTitle);
        if (titlePlaceable == null) {
            height = 0;
        } else {
            height = titlePlaceable.getHeight() + titlePositionY;
        }
        int titleHeightWithSpacing = height;
        if (titlePlaceable == null) {
            textPositionY = textOffset - firstTextBaseline;
        } else if (lastTitleBaseline == 0) {
            textPositionY = (titleHeightWithSpacing - firstTextBaseline) + textOffset;
        } else {
            textPositionY = ((titlePositionY + lastTitleBaseline) - firstTextBaseline) + textOffset;
        }
        if (textPlaceable != null) {
            if (lastTitleBaseline == 0) {
                height2 = (textPlaceable.getHeight() + textOffset) - firstTextBaseline;
            } else {
                height2 = ((textPlaceable.getHeight() + textOffset) - firstTextBaseline) - ((titlePlaceable != null ? titlePlaceable.getHeight() : 0) - lastTitleBaseline);
            }
        }
        int textHeightWithSpacing = height2;
        int layoutWidth2 = titleHeightWithSpacing + textHeightWithSpacing;
        return MeasureScope.layout$default($this$Layout, layoutWidth, layoutWidth2, null, new Function1() { // from class: androidx.compose.material.AlertDialogKt$AlertDialogBaselineLayout$2$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AlertDialogKt$AlertDialogBaselineLayout$2$1.measure_3p2s80s$lambda$7(titlePlaceable, titlePositionY, textPlaceable, textPositionY, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$7(Placeable $titlePlaceable, int $titlePositionY, Placeable $textPlaceable, int $textPositionY, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope $this$layout2;
        if ($titlePlaceable != null) {
            $this$layout2 = $this$layout;
            Placeable.PlacementScope.place$default($this$layout2, $titlePlaceable, 0, $titlePositionY, 0.0f, 4, null);
        } else {
            $this$layout2 = $this$layout;
        }
        if ($textPlaceable != null) {
            Placeable.PlacementScope.place$default($this$layout2, $textPlaceable, 0, $textPositionY, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }
}
