package androidx.compose.material3;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.HorizontalAlignmentLine;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.VerticalAlignmentLine;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Dp;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: InteractiveComponentSize.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J#\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0014\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0002R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/compose/material3/MinimumInteractiveModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "<init>", "()V", "alignmentLinesCache", "", "Landroidx/compose/ui/layout/AlignmentLine;", "", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "updateAlignmentLines", "", "sizePx", "placeable", "Landroidx/compose/ui/layout/Placeable;", "getAlignmentLinesCache", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MinimumInteractiveModifierNode extends Modifier.Node implements CompositionLocalConsumerModifierNode, LayoutModifierNode {
    public static final int $stable = 8;
    private Map<AlignmentLine, Integer> alignmentLinesCache;

    /* JADX WARN: Code duplicated, block: B:9:0x0047  */
    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo70measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        boolean enforcement;
        final int width;
        final int height;
        float $this$coerceAtLeast_u2dYgX7TsA$iv = ((Dp) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize())).m7919unboximpl();
        float minimumValue$iv = Dp.m7905constructorimpl(0);
        float $this$coerceAtLeast_u2dYgX7TsA$iv2 = Dp.m7905constructorimpl(RangesKt.coerceAtLeast($this$coerceAtLeast_u2dYgX7TsA$iv, minimumValue$iv));
        final Placeable placeable = measurable.mo6541measureBRTryo0(constraints);
        if (!getIsAttached() || Float.isNaN($this$coerceAtLeast_u2dYgX7TsA$iv2)) {
            enforcement = false;
        } else {
            int $this$dp$iv = Dp.m7904compareTo0680j_4($this$coerceAtLeast_u2dYgX7TsA$iv2, Dp.m7905constructorimpl(0));
            if ($this$dp$iv > 0) {
                enforcement = true;
            } else {
                enforcement = false;
            }
        }
        int sizePx = Float.isNaN($this$coerceAtLeast_u2dYgX7TsA$iv2) ? 0 : $this$measure_u2d3p2s80s.mo400roundToPx0680j_4($this$coerceAtLeast_u2dYgX7TsA$iv2);
        if (enforcement) {
            width = Math.max(placeable.getWidth(), sizePx);
        } else {
            width = placeable.getWidth();
        }
        if (enforcement) {
            height = Math.max(placeable.getHeight(), sizePx);
        } else {
            height = placeable.getHeight();
        }
        if (enforcement) {
            updateAlignmentLines(sizePx, placeable);
        }
        Map<AlignmentLine, Integer> mapEmptyMap = this.alignmentLinesCache;
        if (mapEmptyMap == null) {
            mapEmptyMap = MapsKt.emptyMap();
        }
        return $this$measure_u2d3p2s80s.layout(width, height, mapEmptyMap, new Function1() { // from class: androidx.compose.material3.MinimumInteractiveModifierNode$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MinimumInteractiveModifierNode.measure_3p2s80s$lambda$0(width, placeable, height, (Placeable.PlacementScope) obj);
            }
        });
    }

    static final Unit measure_3p2s80s$lambda$0(int $width, Placeable $placeable, int $height, Placeable.PlacementScope $this$layout) {
        int centerX = MathKt.roundToInt(($width - $placeable.getWidth()) / 2.0f);
        int centerY = MathKt.roundToInt(($height - $placeable.getHeight()) / 2.0f);
        Placeable.PlacementScope.place$default($this$layout, $placeable, centerX, centerY, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    private final void updateAlignmentLines(int sizePx, Placeable placeable) {
        Map<AlignmentLine, Integer> alignmentLinesCache = getAlignmentLinesCache();
        VerticalAlignmentLine minimumInteractiveLeftAlignmentLine = InteractiveComponentSizeKt.getMinimumInteractiveLeftAlignmentLine();
        float $this$fastRoundToInt$iv = (sizePx - placeable.getWidth()) / 2.0f;
        alignmentLinesCache.put(minimumInteractiveLeftAlignmentLine, Integer.valueOf(RangesKt.coerceAtLeast(Math.round($this$fastRoundToInt$iv), 0)));
        HorizontalAlignmentLine minimumInteractiveTopAlignmentLine = InteractiveComponentSizeKt.getMinimumInteractiveTopAlignmentLine();
        float $this$fastRoundToInt$iv2 = (sizePx - placeable.getHeight()) / 2.0f;
        alignmentLinesCache.put(minimumInteractiveTopAlignmentLine, Integer.valueOf(RangesKt.coerceAtLeast(Math.round($this$fastRoundToInt$iv2), 0)));
    }

    private final Map<AlignmentLine, Integer> getAlignmentLinesCache() {
        Map<AlignmentLine, Integer> map = this.alignmentLinesCache;
        if (map != null) {
            return map;
        }
        LinkedHashMap it = new LinkedHashMap(2);
        this.alignmentLinesCache = it;
        return it;
    }
}
