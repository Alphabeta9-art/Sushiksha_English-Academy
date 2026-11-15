package androidx.compose.material;

import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ListItem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class ListItemKt$BaselinesOffsetColumn$1$1 implements MeasurePolicy {
    final /* synthetic */ List<Dp> $offsets;

    ListItemKt$BaselinesOffsetColumn$1$1(List<Dp> list) {
        this.$offsets = list;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo42measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long j) {
        long childConstraints = Constraints.m7847copyZbe2FdA(j, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(j) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(j) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(j) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(j) : Integer.MAX_VALUE);
        List target$iv = new ArrayList(list.size());
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            Measurable it = (Measurable) item$iv$iv;
            target$iv.add(it.mo6541measureBRTryo0(childConstraints));
        }
        final List placeables = target$iv;
        Object accumulator$iv = 0;
        int index$iv$iv2 = 0;
        int size2 = placeables.size();
        while (index$iv$iv2 < size2) {
            Object item$iv$iv2 = placeables.get(index$iv$iv2);
            int maxWidth = ((Number) accumulator$iv).intValue();
            accumulator$iv = Integer.valueOf(Math.max(maxWidth, ((Placeable) item$iv$iv2).getWidth()));
            index$iv$iv2++;
            childConstraints = childConstraints;
        }
        int containerWidth = ((Number) accumulator$iv).intValue();
        final int[] y = new int[placeables.size()];
        int containerHeight = 0;
        List<Dp> list2 = this.$offsets;
        int index$iv = 0;
        int size3 = placeables.size();
        while (index$iv < size3) {
            Object item$iv = placeables.get(index$iv);
            Placeable placeable = (Placeable) item$iv;
            int index = index$iv;
            int toPreviousBaseline = index > 0 ? ((Placeable) placeables.get(index - 1)).getHeight() - ((Placeable) placeables.get(index - 1)).get(AlignmentLineKt.getLastBaseline()) : 0;
            int topPadding = Math.max(0, ($this$Layout.mo400roundToPx0680j_4(list2.get(index).m7919unboximpl()) - placeable.get(AlignmentLineKt.getFirstBaseline())) - toPreviousBaseline);
            y[index] = topPadding + containerHeight;
            index$iv++;
            containerWidth = containerWidth;
            containerHeight += topPadding + placeable.getHeight();
        }
        return MeasureScope.layout$default($this$Layout, containerWidth, containerHeight, null, new Function1() { // from class: androidx.compose.material.ListItemKt$BaselinesOffsetColumn$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ListItemKt$BaselinesOffsetColumn$1$1.measure_3p2s80s$lambda$3(placeables, y, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$3(List $placeables, int[] $y, Placeable.PlacementScope $this$layout) {
        int size = $placeables.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $placeables.get(index$iv);
            Placeable placeable = (Placeable) item$iv;
            int index = index$iv;
            Placeable.PlacementScope.placeRelative$default($this$layout, placeable, 0, $y[index], 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }
}
