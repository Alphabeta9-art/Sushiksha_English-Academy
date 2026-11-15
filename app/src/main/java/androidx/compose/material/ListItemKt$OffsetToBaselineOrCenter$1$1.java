package androidx.compose.material;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ListItem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class ListItemKt$OffsetToBaselineOrCenter$1$1 implements MeasurePolicy {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-unit-Dp$-offset$0, reason: not valid java name */
    final /* synthetic */ float f74$$v$c$androidxcomposeuiunitDp$offset$0;

    ListItemKt$OffsetToBaselineOrCenter$1$1(float f) {
        this.f74$$v$c$androidxcomposeuiunitDp$offset$0 = f;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo42measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long j) {
        MeasureScope measureScope;
        final int y;
        int containerHeight;
        final Placeable placeable = list.get(0).mo6541measureBRTryo0(Constraints.m7847copyZbe2FdA(j, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(j) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(j) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(j) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(j) : 0));
        int baseline = placeable.get(AlignmentLineKt.getFirstBaseline());
        if (baseline != Integer.MIN_VALUE) {
            measureScope = $this$Layout;
            y = measureScope.mo400roundToPx0680j_4(this.f74$$v$c$androidxcomposeuiunitDp$offset$0) - baseline;
            int y2 = Constraints.m7859getMinHeightimpl(j);
            containerHeight = Math.max(y2, placeable.getHeight() + y);
        } else {
            measureScope = $this$Layout;
            int containerHeight2 = Math.max(Constraints.m7859getMinHeightimpl(j), placeable.getHeight());
            Alignment center = Alignment.INSTANCE.getCenter();
            long jM8081getZeroYbymL2g = IntSize.INSTANCE.m8081getZeroYbymL2g();
            int height$iv = containerHeight2 - placeable.getHeight();
            y = IntOffset.m8034getYimpl(center.mo4571alignKFBX0sM(jM8081getZeroYbymL2g, IntSize.m8071constructorimpl((((long) 0) << 32) | (((long) height$iv) & 4294967295L)), measureScope.getLayoutDirection()));
            containerHeight = containerHeight2;
        }
        return MeasureScope.layout$default(measureScope, placeable.getWidth(), containerHeight, null, new Function1() { // from class: androidx.compose.material.ListItemKt$OffsetToBaselineOrCenter$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ListItemKt$OffsetToBaselineOrCenter$1$1.measure_3p2s80s$lambda$0(placeable, y, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$0(Placeable $placeable, int $y, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelative$default($this$layout, $placeable, 0, $y, 0.0f, 4, null);
        return Unit.INSTANCE;
    }
}
