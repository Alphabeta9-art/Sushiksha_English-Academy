package androidx.compose.material3;

import androidx.compose.foundation.gestures.AnchoredDraggableKt;
import androidx.compose.foundation.gestures.AnchoredDraggableState;
import androidx.compose.foundation.gestures.DraggableAnchorsConfig;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: NavigationDrawer.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class NavigationDrawerKt$DismissibleNavigationDrawer$2$2$1 implements MeasurePolicy {
    final /* synthetic */ MutableState<Boolean> $anchorsInitialized$delegate;
    final /* synthetic */ DrawerState $drawerState;

    NavigationDrawerKt$DismissibleNavigationDrawer$2$2$1(DrawerState drawerState, MutableState<Boolean> mutableState) {
        this.$drawerState = drawerState;
        this.$anchorsInitialized$delegate = mutableState;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo42measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long constraints) {
        final Placeable sheetPlaceable = list.get(0).mo6541measureBRTryo0(constraints);
        final Placeable contentPlaceable = list.get(1).mo6541measureBRTryo0(constraints);
        int width = contentPlaceable.getWidth();
        int height = contentPlaceable.getHeight();
        final DrawerState drawerState = this.$drawerState;
        final MutableState<Boolean> mutableState = this.$anchorsInitialized$delegate;
        return MeasureScope.layout$default($this$Layout, width, height, null, new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$2$2$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavigationDrawerKt$DismissibleNavigationDrawer$2$2$1.measure_3p2s80s$lambda$1(drawerState, sheetPlaceable, contentPlaceable, mutableState, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0026  */
    /* JADX WARN: Code duplicated, block: B:12:0x002c  */
    static final Unit measure_3p2s80s$lambda$1(DrawerState $drawerState, Placeable $sheetPlaceable, Placeable $contentPlaceable, MutableState $anchorsInitialized$delegate, Placeable.PlacementScope $this$layout) {
        float currentClosedAnchor = $drawerState.getAnchoredDraggableState$material3().getAnchors().positionOf(DrawerValue.Closed);
        final float calculatedClosedAnchor = -$sheetPlaceable.getWidth();
        if (NavigationDrawerKt.DismissibleNavigationDrawer$lambda$27($anchorsInitialized$delegate)) {
            if (!(currentClosedAnchor == calculatedClosedAnchor)) {
                if (!NavigationDrawerKt.DismissibleNavigationDrawer$lambda$27($anchorsInitialized$delegate)) {
                    NavigationDrawerKt.DismissibleNavigationDrawer$lambda$28($anchorsInitialized$delegate, true);
                }
                AnchoredDraggableState.updateAnchors$default($drawerState.getAnchoredDraggableState$material3(), AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$2$2$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavigationDrawerKt$DismissibleNavigationDrawer$2$2$1.measure_3p2s80s$lambda$1$lambda$0(calculatedClosedAnchor, (DraggableAnchorsConfig) obj);
                    }
                }), null, 2, null);
            }
        } else {
            if (!NavigationDrawerKt.DismissibleNavigationDrawer$lambda$27($anchorsInitialized$delegate)) {
                NavigationDrawerKt.DismissibleNavigationDrawer$lambda$28($anchorsInitialized$delegate, true);
            }
            AnchoredDraggableState.updateAnchors$default($drawerState.getAnchoredDraggableState$material3(), AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleNavigationDrawer$2$2$1$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return NavigationDrawerKt$DismissibleNavigationDrawer$2$2$1.measure_3p2s80s$lambda$1$lambda$0(calculatedClosedAnchor, (DraggableAnchorsConfig) obj);
                }
            }), null, 2, null);
        }
        Placeable.PlacementScope.placeRelative$default($this$layout, $contentPlaceable, $sheetPlaceable.getWidth() + MathKt.roundToInt($drawerState.requireOffset$material3()), 0, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default($this$layout, $sheetPlaceable, MathKt.roundToInt($drawerState.requireOffset$material3()), 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    static final Unit measure_3p2s80s$lambda$1$lambda$0(float $calculatedClosedAnchor, DraggableAnchorsConfig $this$DraggableAnchors) {
        $this$DraggableAnchors.at(DrawerValue.Closed, $calculatedClosedAnchor);
        $this$DraggableAnchors.at(DrawerValue.Open, 0.0f);
        return Unit.INSTANCE;
    }
}
