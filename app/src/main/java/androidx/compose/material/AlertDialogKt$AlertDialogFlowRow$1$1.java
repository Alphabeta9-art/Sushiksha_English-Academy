package androidx.compose.material;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: AlertDialog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class AlertDialogKt$AlertDialogFlowRow$1$1 implements MeasurePolicy {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-unit-Dp$-crossAxisSpacing$0, reason: not valid java name */
    final /* synthetic */ float f61$$v$c$androidxcomposeuiunitDp$crossAxisSpacing$0;

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-unit-Dp$-mainAxisSpacing$0, reason: not valid java name */
    final /* synthetic */ float f62$$v$c$androidxcomposeuiunitDp$mainAxisSpacing$0;

    AlertDialogKt$AlertDialogFlowRow$1$1(float f, float f2) {
        this.f62$$v$c$androidxcomposeuiunitDp$mainAxisSpacing$0 = f;
        this.f61$$v$c$androidxcomposeuiunitDp$crossAxisSpacing$0 = f2;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo42measure3p2s80s(final MeasureScope $this$Layout, List<? extends Measurable> list, long j) {
        final int mainAxisLayoutSize;
        final List sequences = new ArrayList();
        List crossAxisSizes = new ArrayList();
        final List crossAxisPositions = new ArrayList();
        Ref.IntRef mainAxisSpace = new Ref.IntRef();
        Ref.IntRef crossAxisSpace = new Ref.IntRef();
        List currentSequence = new ArrayList();
        Ref.IntRef currentMainAxisSize = new Ref.IntRef();
        Ref.IntRef currentCrossAxisSize = new Ref.IntRef();
        long childConstraints = ConstraintsKt.Constraints$default(0, Constraints.m7858getMaxWidthimpl(j), 0, 0, 13, null);
        float f = this.f62$$v$c$androidxcomposeuiunitDp$mainAxisSpacing$0;
        float f2 = this.f61$$v$c$androidxcomposeuiunitDp$crossAxisSpacing$0;
        List<? extends Measurable> list2 = list;
        int index$iv = 0;
        int size = list2.size();
        while (index$iv < size) {
            Object item$iv = list2.get(index$iv);
            float f3 = f2;
            Measurable measurable = (Measurable) item$iv;
            Placeable placeable = measurable.mo6541measureBRTryo0(childConstraints);
            List<? extends Measurable> list3 = list2;
            int index$iv2 = index$iv;
            int i = size;
            Ref.IntRef currentMainAxisSize2 = currentMainAxisSize;
            List currentSequence2 = currentSequence;
            if (!measure_3p2s80s$canAddToCurrentSequence(currentSequence2, currentMainAxisSize2, $this$Layout, f, j, placeable)) {
                measure_3p2s80s$startNewSequence(sequences, crossAxisSpace, $this$Layout, f3, currentSequence2, crossAxisSizes, currentCrossAxisSize, crossAxisPositions, mainAxisSpace, currentMainAxisSize2);
                currentSequence2 = currentSequence2;
            }
            if (!currentSequence2.isEmpty()) {
                currentMainAxisSize2.element += $this$Layout.mo400roundToPx0680j_4(f);
            }
            currentSequence2.add(placeable);
            currentMainAxisSize2.element += placeable.getWidth();
            currentCrossAxisSize.element = Math.max(currentCrossAxisSize.element, placeable.getHeight());
            index$iv = index$iv2 + 1;
            currentSequence = currentSequence2;
            currentMainAxisSize = currentMainAxisSize2;
            childConstraints = childConstraints;
            f2 = f3;
            list2 = list3;
            size = i;
        }
        Ref.IntRef currentMainAxisSize3 = currentMainAxisSize;
        List currentSequence3 = currentSequence;
        if (!currentSequence3.isEmpty()) {
            measure_3p2s80s$startNewSequence(sequences, crossAxisSpace, $this$Layout, this.f61$$v$c$androidxcomposeuiunitDp$crossAxisSpacing$0, currentSequence3, crossAxisSizes, currentCrossAxisSize, crossAxisPositions, mainAxisSpace, currentMainAxisSize3);
        }
        if (Constraints.m7858getMaxWidthimpl(j) != Integer.MAX_VALUE) {
            mainAxisLayoutSize = Constraints.m7858getMaxWidthimpl(j);
        } else {
            mainAxisLayoutSize = Math.max(mainAxisSpace.element, Constraints.m7860getMinWidthimpl(j));
        }
        int crossAxisLayoutSize = Math.max(crossAxisSpace.element, Constraints.m7859getMinHeightimpl(j));
        int layoutWidth = mainAxisLayoutSize;
        final float f4 = this.f62$$v$c$androidxcomposeuiunitDp$mainAxisSpacing$0;
        return MeasureScope.layout$default($this$Layout, layoutWidth, crossAxisLayoutSize, null, new Function1() { // from class: androidx.compose.material.AlertDialogKt$AlertDialogFlowRow$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AlertDialogKt$AlertDialogFlowRow$1$1.measure_3p2s80s$lambda$1(sequences, $this$Layout, f4, mainAxisLayoutSize, crossAxisPositions, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    private static final boolean measure_3p2s80s$canAddToCurrentSequence(List<Placeable> list, Ref.IntRef currentMainAxisSize, MeasureScope $this_Layout, float f, long j, Placeable placeable) {
        return list.isEmpty() || (currentMainAxisSize.element + $this_Layout.mo400roundToPx0680j_4(f)) + placeable.getWidth() <= Constraints.m7858getMaxWidthimpl(j);
    }

    private static final void measure_3p2s80s$startNewSequence(List<List<Placeable>> list, Ref.IntRef crossAxisSpace, MeasureScope $this_Layout, float f, List<Placeable> list2, List<Integer> list3, Ref.IntRef currentCrossAxisSize, List<Integer> list4, Ref.IntRef mainAxisSpace, Ref.IntRef currentMainAxisSize) {
        if (!list.isEmpty()) {
            crossAxisSpace.element += $this_Layout.mo400roundToPx0680j_4(f);
        }
        list.add(0, CollectionsKt.toList(list2));
        list3.add(Integer.valueOf(currentCrossAxisSize.element));
        list4.add(Integer.valueOf(crossAxisSpace.element));
        crossAxisSpace.element += currentCrossAxisSize.element;
        mainAxisSpace.element = Math.max(mainAxisSpace.element, currentMainAxisSize.element);
        list2.clear();
        currentMainAxisSize.element = 0;
        currentCrossAxisSize.element = 0;
    }

    static final Unit measure_3p2s80s$lambda$1(List $sequences, MeasureScope $this_Layout, float f, int $mainAxisLayoutSize, List $crossAxisPositions, Placeable.PlacementScope $this$layout) {
        List $this$fastForEachIndexed$iv = $sequences;
        int index$iv = 0;
        int size = $this$fastForEachIndexed$iv.size();
        while (index$iv < size) {
            Object item$iv = $this$fastForEachIndexed$iv.get(index$iv);
            List placeables = (List) item$iv;
            int i = index$iv;
            int size2 = placeables.size();
            int[] childrenMainAxisSizes = new int[size2];
            int i2 = 0;
            while (i2 < size2) {
                childrenMainAxisSizes[i2] = ((Placeable) placeables.get(i2)).getWidth() + (i2 < CollectionsKt.getLastIndex(placeables) ? $this_Layout.mo400roundToPx0680j_4(f) : 0);
                i2++;
            }
            Arrangement.Vertical arrangement = Arrangement.INSTANCE.getBottom();
            int[] mainAxisPositions = new int[childrenMainAxisSizes.length];
            arrangement.arrange($this_Layout, $mainAxisLayoutSize, childrenMainAxisSizes, mainAxisPositions);
            int index$iv2 = 0;
            int size3 = placeables.size();
            while (index$iv2 < size3) {
                Object item$iv2 = placeables.get(index$iv2);
                Placeable placeable = (Placeable) item$iv2;
                int j = index$iv2;
                Placeable.PlacementScope.place$default($this$layout, placeable, mainAxisPositions[j], ((Number) $crossAxisPositions.get(i)).intValue(), 0.0f, 4, null);
                index$iv2++;
                $this$fastForEachIndexed$iv = $this$fastForEachIndexed$iv;
            }
            index$iv++;
            $this$fastForEachIndexed$iv = $this$fastForEachIndexed$iv;
        }
        return Unit.INSTANCE;
    }
}
