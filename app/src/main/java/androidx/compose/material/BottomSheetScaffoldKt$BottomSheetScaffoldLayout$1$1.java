package androidx.compose.material;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: BottomSheetScaffold.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1 implements MultiContentMeasurePolicy {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-material-FabPosition$-floatingActionButtonPosition$0, reason: not valid java name */
    final /* synthetic */ int f65xbcc8c926;

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-unit-Dp$-sheetPeekHeight$0, reason: not valid java name */
    final /* synthetic */ float f66$$v$c$androidxcomposeuiunitDp$sheetPeekHeight$0;
    final /* synthetic */ Function0<Float> $sheetOffset;
    final /* synthetic */ BottomSheetState $sheetState;

    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BottomSheetValue.values().length];
            try {
                iArr[BottomSheetValue.Collapsed.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BottomSheetValue.Expanded.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1(Function0<Float> function0, int i, float f, BottomSheetState bottomSheetState) {
        this.$sheetOffset = function0;
        this.f65xbcc8c926 = i;
        this.f66$$v$c$androidxcomposeuiunitDp$sheetPeekHeight$0 = f;
        this.$sheetState = bottomSheetState;
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo763measure3p2s80s(final MeasureScope $this$Layout, List<? extends List<? extends Measurable>> list, long j) {
        Object maxElem$iv;
        Object maxElem$iv2;
        Object maxElem$iv3;
        Object maxElem$iv4;
        Object maxElem$iv5;
        List<? extends Measurable> list2 = list.get(0);
        List<? extends Measurable> list3 = list.get(1);
        List<? extends Measurable> list4 = list.get(2);
        List<? extends Measurable> list5 = list.get(3);
        List<? extends Measurable> list6 = list.get(4);
        final int layoutWidth = Constraints.m7858getMaxWidthimpl(j);
        final int layoutHeight = Constraints.m7857getMaxHeightimpl(j);
        long looseConstraints = Constraints.m7847copyZbe2FdA(j, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(j) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(j) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(j) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(j) : 0);
        List target$iv = new ArrayList(list4.size());
        int index$iv$iv = 0;
        int size = list4.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list4.get(index$iv$iv);
            int i = size;
            Measurable it = (Measurable) item$iv$iv;
            target$iv.add(it.mo6541measureBRTryo0(looseConstraints));
            index$iv$iv++;
            list6 = list6;
            size = i;
        }
        List<? extends Measurable> list7 = list6;
        final List sheetPlaceables = target$iv;
        List<? extends Measurable> list8 = list2;
        int $i$f$fastMap = 0;
        List target$iv2 = new ArrayList(list8.size());
        int index$iv$iv2 = 0;
        int size2 = list8.size();
        while (index$iv$iv2 < size2) {
            Object item$iv$iv2 = list8.get(index$iv$iv2);
            int $i$f$fastMap2 = $i$f$fastMap;
            Measurable it2 = (Measurable) item$iv$iv2;
            target$iv2.add(it2.mo6541measureBRTryo0(looseConstraints));
            index$iv$iv2++;
            list8 = list8;
            $i$f$fastMap = $i$f$fastMap2;
        }
        final List topBarPlaceables = target$iv2;
        if (!topBarPlaceables.isEmpty()) {
            maxElem$iv = topBarPlaceables.get(0);
            Placeable it3 = (Placeable) maxElem$iv;
            int maxValue$iv = it3.getHeight();
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex(topBarPlaceables);
            if (1 <= lastIndex) {
                while (true) {
                    Object e$iv = topBarPlaceables.get(i$iv);
                    Placeable it4 = (Placeable) e$iv;
                    int height = it4.getHeight();
                    if (maxValue$iv < height) {
                        maxElem$iv = e$iv;
                        maxValue$iv = height;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                }
            }
        } else {
            maxElem$iv = null;
        }
        Placeable placeable = (Placeable) maxElem$iv;
        final int topBarHeight = placeable != null ? placeable.getHeight() : 0;
        long bodyConstraints = Constraints.m7847copyZbe2FdA(looseConstraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(looseConstraints) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(looseConstraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(looseConstraints) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(looseConstraints) : layoutHeight - topBarHeight);
        List target$iv3 = new ArrayList(list3.size());
        int index$iv$iv3 = 0;
        int size3 = list3.size();
        while (index$iv$iv3 < size3) {
            Object item$iv$iv3 = list3.get(index$iv$iv3);
            List<? extends Measurable> list9 = list3;
            Measurable it5 = (Measurable) item$iv$iv3;
            target$iv3.add(it5.mo6541measureBRTryo0(bodyConstraints));
            index$iv$iv3++;
            size3 = size3;
            list3 = list9;
        }
        final List bodyPlaceables = target$iv3;
        List target$iv4 = new ArrayList(list5.size());
        int index$iv$iv4 = 0;
        int size4 = list5.size();
        while (index$iv$iv4 < size4) {
            Object item$iv$iv4 = list5.get(index$iv$iv4);
            Measurable it6 = (Measurable) item$iv$iv4;
            target$iv4.add(it6.mo6541measureBRTryo0(looseConstraints));
            index$iv$iv4++;
            bodyConstraints = bodyConstraints;
        }
        final List fabPlaceable = target$iv4;
        if (!fabPlaceable.isEmpty()) {
            maxElem$iv2 = fabPlaceable.get(0);
            Placeable it7 = (Placeable) maxElem$iv2;
            int maxValue$iv2 = it7.getWidth();
            int i$iv2 = 1;
            int lastIndex2 = CollectionsKt.getLastIndex(fabPlaceable);
            if (1 <= lastIndex2) {
                while (true) {
                    Object e$iv2 = fabPlaceable.get(i$iv2);
                    Placeable it8 = (Placeable) e$iv2;
                    int v$iv = it8.getWidth();
                    if (maxValue$iv2 < v$iv) {
                        maxElem$iv2 = e$iv2;
                        maxValue$iv2 = v$iv;
                    }
                    if (i$iv2 == lastIndex2) {
                        break;
                    }
                    i$iv2++;
                }
            }
        } else {
            maxElem$iv2 = null;
        }
        Placeable placeable2 = (Placeable) maxElem$iv2;
        final int fabWidth = placeable2 != null ? placeable2.getWidth() : 0;
        if (!fabPlaceable.isEmpty()) {
            maxElem$iv3 = fabPlaceable.get(0);
            Placeable it9 = (Placeable) maxElem$iv3;
            int maxValue$iv3 = it9.getHeight();
            int i$iv3 = 1;
            int lastIndex3 = CollectionsKt.getLastIndex(fabPlaceable);
            if (1 <= lastIndex3) {
                while (true) {
                    Object e$iv3 = fabPlaceable.get(i$iv3);
                    Placeable it10 = (Placeable) e$iv3;
                    int v$iv2 = it10.getHeight();
                    if (maxValue$iv3 < v$iv2) {
                        maxElem$iv3 = e$iv3;
                        maxValue$iv3 = v$iv2;
                    }
                    if (i$iv3 == lastIndex3) {
                        break;
                    }
                    i$iv3++;
                }
            }
        } else {
            maxElem$iv3 = null;
        }
        Placeable placeable3 = (Placeable) maxElem$iv3;
        final int fabHeight = placeable3 != null ? placeable3.getHeight() : 0;
        List<? extends Measurable> list10 = list7;
        int $i$f$fastMap3 = 0;
        List target$iv5 = new ArrayList(list10.size());
        int index$iv$iv5 = 0;
        int size5 = list10.size();
        while (index$iv$iv5 < size5) {
            Object item$iv$iv5 = list10.get(index$iv$iv5);
            int $i$f$fastMap4 = $i$f$fastMap3;
            Measurable it11 = (Measurable) item$iv$iv5;
            target$iv5.add(it11.mo6541measureBRTryo0(looseConstraints));
            index$iv$iv5++;
            list10 = list10;
            $i$f$fastMap3 = $i$f$fastMap4;
        }
        final List snackbarPlaceables = target$iv5;
        if (!snackbarPlaceables.isEmpty()) {
            maxElem$iv4 = snackbarPlaceables.get(0);
            Placeable it12 = (Placeable) maxElem$iv4;
            int maxValue$iv4 = it12.getWidth();
            int i$iv4 = 1;
            int lastIndex4 = CollectionsKt.getLastIndex(snackbarPlaceables);
            if (1 <= lastIndex4) {
                while (true) {
                    Object e$iv4 = snackbarPlaceables.get(i$iv4);
                    Placeable it13 = (Placeable) e$iv4;
                    int v$iv3 = it13.getWidth();
                    if (maxValue$iv4 < v$iv3) {
                        maxElem$iv4 = e$iv4;
                        maxValue$iv4 = v$iv3;
                    }
                    if (i$iv4 == lastIndex4) {
                        break;
                    }
                    i$iv4++;
                }
            }
        } else {
            maxElem$iv4 = null;
        }
        Placeable placeable4 = (Placeable) maxElem$iv4;
        final int snackbarWidth = placeable4 != null ? placeable4.getWidth() : 0;
        if (snackbarPlaceables.isEmpty()) {
            maxElem$iv5 = null;
        } else {
            Object maxElem$iv6 = snackbarPlaceables.get(0);
            Placeable it14 = (Placeable) maxElem$iv6;
            int maxValue$iv5 = it14.getHeight();
            int i$iv5 = 1;
            int lastIndex5 = CollectionsKt.getLastIndex(snackbarPlaceables);
            if (1 <= lastIndex5) {
                while (true) {
                    Object e$iv5 = snackbarPlaceables.get(i$iv5);
                    Placeable it15 = (Placeable) e$iv5;
                    int height2 = it15.getHeight();
                    if (maxValue$iv5 < height2) {
                        maxElem$iv6 = e$iv5;
                        maxValue$iv5 = height2;
                    }
                    if (i$iv5 == lastIndex5) {
                        break;
                    }
                    i$iv5++;
                }
            }
            maxElem$iv5 = maxElem$iv6;
        }
        Placeable placeable5 = (Placeable) maxElem$iv5;
        final int snackbarHeight = placeable5 != null ? placeable5.getHeight() : 0;
        final Function0<Float> function0 = this.$sheetOffset;
        final int i2 = this.f65xbcc8c926;
        final float f = this.f66$$v$c$androidxcomposeuiunitDp$sheetPeekHeight$0;
        final BottomSheetState bottomSheetState = this.$sheetState;
        return MeasureScope.layout$default($this$Layout, layoutWidth, layoutHeight, null, new Function1() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1.measure_3p2s80s$lambda$10(function0, i2, $this$Layout, layoutWidth, fabWidth, f, fabHeight, snackbarWidth, bottomSheetState, snackbarHeight, layoutHeight, bodyPlaceables, topBarPlaceables, sheetPlaceables, fabPlaceable, snackbarPlaceables, topBarHeight, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$10(Function0 $sheetOffset, int i, MeasureScope $this_Layout, int $layoutWidth, int $fabWidth, float f, int $fabHeight, int $snackbarWidth, BottomSheetState $sheetState, int $snackbarHeight, int $layoutHeight, List $bodyPlaceables, List $topBarPlaceables, List $sheetPlaceables, List $fabPlaceable, List $snackbarPlaceables, int $topBarHeight, Placeable.PlacementScope $this$layout) {
        int i2;
        int fabOffsetY;
        int i3;
        int sheetOffsetY = MathKt.roundToInt(((Number) $sheetOffset.invoke()).floatValue());
        if (FabPosition.m1829equalsimpl0(i, FabPosition.INSTANCE.m1835getStart5ygKITE())) {
            i2 = $this_Layout.mo400roundToPx0680j_4(BottomSheetScaffoldKt.FabSpacing);
        } else {
            i2 = FabPosition.m1829equalsimpl0(i, FabPosition.INSTANCE.m1833getCenter5ygKITE()) ? ($layoutWidth - $fabWidth) / 2 : ($layoutWidth - $fabWidth) - $this_Layout.mo400roundToPx0680j_4(BottomSheetScaffoldKt.FabSpacing);
        }
        int fabOffsetX = i2;
        if ($this_Layout.mo406toPx0680j_4(f) < $fabHeight / 2) {
            fabOffsetY = (sheetOffsetY - $fabHeight) - $this_Layout.mo400roundToPx0680j_4(BottomSheetScaffoldKt.FabSpacing);
        } else {
            fabOffsetY = sheetOffsetY - ($fabHeight / 2);
        }
        int snackbarOffsetY = ($layoutWidth - $snackbarWidth) / 2;
        switch (WhenMappings.$EnumSwitchMapping$0[$sheetState.getCurrentValue().ordinal()]) {
            case 1:
                i3 = fabOffsetY - $snackbarHeight;
                break;
            case 2:
                i3 = $layoutHeight - $snackbarHeight;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        int snackbarOffsetY2 = i3;
        int size = $bodyPlaceables.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $bodyPlaceables.get(index$iv);
            Placeable it = (Placeable) item$iv;
            Placeable.PlacementScope.placeRelative$default($this$layout, it, 0, $topBarHeight, 0.0f, 4, null);
        }
        int size2 = $topBarPlaceables.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = $topBarPlaceables.get(index$iv2);
            Placeable it2 = (Placeable) item$iv2;
            Placeable.PlacementScope.placeRelative$default($this$layout, it2, 0, 0, 0.0f, 4, null);
        }
        int size3 = $sheetPlaceables.size();
        for (int index$iv3 = 0; index$iv3 < size3; index$iv3++) {
            Object item$iv3 = $sheetPlaceables.get(index$iv3);
            Placeable it3 = (Placeable) item$iv3;
            Placeable.PlacementScope.placeRelative$default($this$layout, it3, 0, 0, 0.0f, 4, null);
        }
        int size4 = $fabPlaceable.size();
        for (int index$iv4 = 0; index$iv4 < size4; index$iv4++) {
            Object item$iv4 = $fabPlaceable.get(index$iv4);
            Placeable it4 = (Placeable) item$iv4;
            Placeable.PlacementScope.placeRelative$default($this$layout, it4, fabOffsetX, fabOffsetY, 0.0f, 4, null);
        }
        int index$iv5 = 0;
        int size5 = $snackbarPlaceables.size();
        while (index$iv5 < size5) {
            Object item$iv5 = $snackbarPlaceables.get(index$iv5);
            Placeable it5 = (Placeable) item$iv5;
            int snackbarOffsetX = snackbarOffsetY;
            int snackbarOffsetX2 = snackbarOffsetY2;
            Placeable.PlacementScope.placeRelative$default($this$layout, it5, snackbarOffsetX, snackbarOffsetX2, 0.0f, 4, null);
            index$iv5++;
            snackbarOffsetY2 = snackbarOffsetX2;
            snackbarOffsetY = snackbarOffsetX;
        }
        return Unit.INSTANCE;
    }
}
