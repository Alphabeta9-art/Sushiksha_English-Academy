package androidx.compose.material3;

import androidx.compose.foundation.gestures.AnchoredDraggableKt;
import androidx.compose.foundation.gestures.AnchoredDraggableState;
import androidx.compose.foundation.gestures.DraggableAnchorsConfig;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: NavigationDrawer.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class NavigationDrawerKt$ModalNavigationDrawer$2$6$1 implements MeasurePolicy {
    final /* synthetic */ MutableState<Boolean> $anchorsInitialized$delegate;
    final /* synthetic */ DrawerState $drawerState;
    final /* synthetic */ float $maxValue;
    final /* synthetic */ MutableFloatState $minValue$delegate;

    NavigationDrawerKt$ModalNavigationDrawer$2$6$1(DrawerState drawerState, MutableState<Boolean> mutableState, MutableFloatState mutableFloatState, float f) {
        this.$drawerState = drawerState;
        this.$anchorsInitialized$delegate = mutableState;
        this.$minValue$delegate = mutableFloatState;
        this.$maxValue = f;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo42measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long constraints) {
        Integer numValueOf;
        long looseConstraints = Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0);
        List target$iv = new ArrayList(list.size());
        int size = list.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = list.get(index$iv$iv);
            Measurable it = (Measurable) item$iv$iv;
            target$iv.add(it.mo6541measureBRTryo0(looseConstraints));
        }
        final List $this$fastMaxOfOrNull$iv = target$iv;
        Comparable maxValue$iv = null;
        if (!$this$fastMaxOfOrNull$iv.isEmpty()) {
            Placeable it2 = (Placeable) $this$fastMaxOfOrNull$iv.get(0);
            numValueOf = Integer.valueOf(it2.getWidth());
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex($this$fastMaxOfOrNull$iv);
            if (1 <= lastIndex) {
                while (true) {
                    Placeable it3 = (Placeable) $this$fastMaxOfOrNull$iv.get(i$iv);
                    Integer numValueOf2 = Integer.valueOf(it3.getWidth());
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                }
            }
        } else {
            numValueOf = null;
        }
        Integer num = numValueOf;
        final int width = num != null ? num.intValue() : 0;
        if (!$this$fastMaxOfOrNull$iv.isEmpty()) {
            Placeable it4 = (Placeable) $this$fastMaxOfOrNull$iv.get(0);
            Integer numValueOf3 = Integer.valueOf(it4.getHeight());
            int i$iv2 = 1;
            int lastIndex2 = CollectionsKt.getLastIndex($this$fastMaxOfOrNull$iv);
            if (1 <= lastIndex2) {
                while (true) {
                    Placeable it5 = (Placeable) $this$fastMaxOfOrNull$iv.get(i$iv2);
                    Integer numValueOf4 = Integer.valueOf(it5.getHeight());
                    if (numValueOf4.compareTo(numValueOf3) > 0) {
                        numValueOf3 = numValueOf4;
                    }
                    if (i$iv2 == lastIndex2) {
                        break;
                    }
                    i$iv2++;
                }
            }
            maxValue$iv = numValueOf3;
        }
        Integer num2 = (Integer) maxValue$iv;
        int height = num2 != null ? num2.intValue() : 0;
        final DrawerState drawerState = this.$drawerState;
        final MutableState<Boolean> mutableState = this.$anchorsInitialized$delegate;
        final MutableFloatState mutableFloatState = this.$minValue$delegate;
        final float f = this.$maxValue;
        return MeasureScope.layout$default($this$Layout, width, height, null, new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$6$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavigationDrawerKt$ModalNavigationDrawer$2$6$1.measure_3p2s80s$lambda$5(drawerState, width, $this$fastMaxOfOrNull$iv, mutableState, mutableFloatState, f, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x002a  */
    /* JADX WARN: Code duplicated, block: B:12:0x0030  */
    /* JADX WARN: Code duplicated, block: B:13:0x0036  */
    static final Unit measure_3p2s80s$lambda$5(DrawerState $drawerState, int $width, List $placeables, MutableState $anchorsInitialized$delegate, final MutableFloatState $minValue$delegate, final float $maxValue, Placeable.PlacementScope $this$layout) {
        float currentClosedAnchor = $drawerState.getAnchoredDraggableState$material3().getAnchors().positionOf(DrawerValue.Closed);
        float calculatedClosedAnchor = -$width;
        if (!NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$5($anchorsInitialized$delegate)) {
            if (!NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$5($anchorsInitialized$delegate)) {
                NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$6($anchorsInitialized$delegate, true);
            }
            $minValue$delegate.setFloatValue(calculatedClosedAnchor);
            AnchoredDraggableState.updateAnchors$default($drawerState.getAnchoredDraggableState$material3(), AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$6$1$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return NavigationDrawerKt$ModalNavigationDrawer$2$6$1.measure_3p2s80s$lambda$5$lambda$3($maxValue, $minValue$delegate, (DraggableAnchorsConfig) obj);
                }
            }), null, 2, null);
        } else if (!(currentClosedAnchor == calculatedClosedAnchor)) {
            if (!NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$5($anchorsInitialized$delegate)) {
                NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$6($anchorsInitialized$delegate, true);
            }
            $minValue$delegate.setFloatValue(calculatedClosedAnchor);
            AnchoredDraggableState.updateAnchors$default($drawerState.getAnchoredDraggableState$material3(), AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalNavigationDrawer$2$6$1$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return NavigationDrawerKt$ModalNavigationDrawer$2$6$1.measure_3p2s80s$lambda$5$lambda$3($maxValue, $minValue$delegate, (DraggableAnchorsConfig) obj);
                }
            }), null, 2, null);
        }
        int size = $placeables.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $placeables.get(index$iv);
            Placeable it = (Placeable) item$iv;
            Placeable.PlacementScope.placeRelative$default($this$layout, it, 0, 0, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    static final Unit measure_3p2s80s$lambda$5$lambda$3(float $maxValue, MutableFloatState $minValue$delegate, DraggableAnchorsConfig $this$DraggableAnchors) {
        $this$DraggableAnchors.at(DrawerValue.Closed, NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$8($minValue$delegate));
        $this$DraggableAnchors.at(DrawerValue.Open, $maxValue);
        return Unit.INSTANCE;
    }
}
