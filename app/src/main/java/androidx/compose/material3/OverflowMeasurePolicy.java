package androidx.compose.material3;

import androidx.compose.material3.internal.LayoutUtilKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AppBarDsl.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ/\u0010\f\u001a\u00020\r*\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/compose/material3/OverflowMeasurePolicy;", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "overflowState", "Landroidx/compose/material3/AppBarOverflowState;", "maxItemCount", "", "isVertical", "", "<init>", "(Landroidx/compose/material3/AppBarOverflowState;IZ)V", "getMaxItemCount", "()I", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class OverflowMeasurePolicy implements MultiContentMeasurePolicy {
    public static final int $stable = 0;
    private final boolean isVertical;
    private final int maxItemCount;
    private final AppBarOverflowState overflowState;

    public OverflowMeasurePolicy(AppBarOverflowState overflowState, int maxItemCount, boolean isVertical) {
        this.overflowState = overflowState;
        this.maxItemCount = maxItemCount;
        this.isVertical = isVertical;
    }

    public /* synthetic */ OverflowMeasurePolicy(AppBarOverflowState appBarOverflowState, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(appBarOverflowState, i, (i2 & 4) != 0 ? false : z);
    }

    public final int getMaxItemCount() {
        return this.maxItemCount;
    }

    /* JADX WARN: Code duplicated, block: B:111:0x0282  */
    /* JADX WARN: Code duplicated, block: B:146:0x0339  */
    /* JADX WARN: Code duplicated, block: B:179:0x03e6  */
    /* JADX WARN: Code duplicated, block: B:188:0x015a A[EDGE_INSN: B:188:0x015a->B:67:0x015a BREAK  A[LOOP:0: B:40:0x0104->B:64:0x0148], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:64:0x0148 A[LOOP:0: B:40:0x0104->B:64:0x0148, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:95:0x0231  */
    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo763measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends List<? extends Measurable>> list, long constraints) {
        boolean z;
        Integer numValueOf;
        int reservedOverflowSpace;
        List<? extends Measurable> list2;
        final ArrayList overflowPlaceables;
        int overflowSpace;
        Integer numValueOf2;
        Integer numValueOf3;
        int iIntValue;
        int childrenMaxSpace;
        Integer numValueOf4;
        Integer numValueOf5;
        int iIntValue2;
        Integer numValueOf6;
        Integer numValueOf7;
        boolean hasSufficientSpace;
        Integer numValueOf8;
        long looseConstraints = Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0);
        List<? extends Measurable> list3 = list.get(0);
        boolean z2 = true;
        List<? extends Measurable> list4 = list.get(1);
        this.overflowState.setTotalItemCount(list3.size());
        if (this.isVertical) {
            if (list4.isEmpty()) {
                z = true;
                numValueOf8 = null;
            } else {
                Measurable it = list4.get(0);
                numValueOf8 = Integer.valueOf(it.maxIntrinsicHeight(Constraints.m7858getMaxWidthimpl(constraints)));
                int i$iv = 1;
                int lastIndex = CollectionsKt.getLastIndex(list4);
                if (1 <= lastIndex) {
                    while (true) {
                        z = z2;
                        Measurable it2 = list4.get(i$iv);
                        Integer numValueOf9 = Integer.valueOf(it2.maxIntrinsicHeight(Constraints.m7858getMaxWidthimpl(constraints)));
                        if (numValueOf9.compareTo(numValueOf8) > 0) {
                            numValueOf8 = numValueOf9;
                        }
                        if (i$iv == lastIndex) {
                            break;
                        }
                        i$iv++;
                        z2 = z;
                    }
                } else {
                    z = true;
                }
            }
            Integer num = numValueOf8;
            reservedOverflowSpace = num != null ? num.intValue() : 0;
        } else {
            z = true;
            if (!list4.isEmpty()) {
                Measurable it3 = list4.get(0);
                numValueOf = Integer.valueOf(it3.maxIntrinsicWidth(Constraints.m7857getMaxHeightimpl(constraints)));
                int i$iv2 = 1;
                int lastIndex2 = CollectionsKt.getLastIndex(list4);
                if (1 <= lastIndex2) {
                    while (true) {
                        Measurable it4 = list4.get(i$iv2);
                        Integer numValueOf10 = Integer.valueOf(it4.maxIntrinsicWidth(Constraints.m7857getMaxHeightimpl(constraints)));
                        if (numValueOf10.compareTo(numValueOf) > 0) {
                            numValueOf = numValueOf10;
                        }
                        if (i$iv2 == lastIndex2) {
                            break;
                        }
                        i$iv2++;
                    }
                }
            } else {
                numValueOf = null;
            }
            Integer num2 = numValueOf;
            reservedOverflowSpace = num2 != null ? num2.intValue() : 0;
        }
        int remainingSpace = this.isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
        int remainingSpace2 = LayoutUtilKt.subtractConstraintSafely(remainingSpace, reservedOverflowSpace);
        int currentSpace = 0;
        final List contentPlaceables = new ArrayList();
        int i = 0;
        int size = list3.size();
        while (true) {
            if (i >= size) {
                list2 = list3;
                break;
            }
            Placeable placeable = list3.get(i).mo6541measureBRTryo0(looseConstraints);
            boolean isLastContent = i == CollectionsKt.getLastIndex(list3) ? z : false;
            if (!isLastContent && i == this.maxItemCount - 1) {
                list2 = list3;
                break;
            }
            int placeableSpace = this.isVertical ? placeable.getHeight() : placeable.getWidth();
            if (placeableSpace > remainingSpace2) {
                if (isLastContent) {
                    list2 = list3;
                    if (placeableSpace <= remainingSpace2 + reservedOverflowSpace) {
                    }
                    if (!hasSufficientSpace) {
                        break;
                    }
                    contentPlaceables.add(placeable);
                    currentSpace += placeableSpace;
                    remainingSpace2 = LayoutUtilKt.subtractConstraintSafely(remainingSpace2, placeableSpace);
                    i++;
                    list3 = list2;
                } else {
                    list2 = list3;
                }
                hasSufficientSpace = false;
                if (!hasSufficientSpace) {
                    break;
                    break;
                }
                contentPlaceables.add(placeable);
                currentSpace += placeableSpace;
                remainingSpace2 = LayoutUtilKt.subtractConstraintSafely(remainingSpace2, placeableSpace);
                i++;
                list3 = list2;
            } else {
                list2 = list3;
            }
            hasSufficientSpace = z;
            if (!hasSufficientSpace) {
                break;
                break;
            }
            contentPlaceables.add(placeable);
            currentSpace += placeableSpace;
            remainingSpace2 = LayoutUtilKt.subtractConstraintSafely(remainingSpace2, placeableSpace);
            i++;
            list3 = list2;
        }
        this.overflowState.setVisibleItemCount(contentPlaceables.size());
        if (contentPlaceables.size() != list2.size()) {
            long overflowConstraints = this.isVertical ? Constraints.m7847copyZbe2FdA(looseConstraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(looseConstraints) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(looseConstraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(looseConstraints) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(looseConstraints) : remainingSpace2 + reservedOverflowSpace) : Constraints.m7847copyZbe2FdA(looseConstraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(looseConstraints) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(looseConstraints) : remainingSpace2 + reservedOverflowSpace, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(looseConstraints) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(looseConstraints) : 0);
            ArrayList target$iv = new ArrayList(list4.size());
            int remainingSpace3 = 0;
            for (int size2 = list4.size(); remainingSpace3 < size2; size2 = size2) {
                Object item$iv$iv = list4.get(remainingSpace3);
                int index$iv$iv = remainingSpace3;
                Measurable it5 = (Measurable) item$iv$iv;
                target$iv.add(it5.mo6541measureBRTryo0(overflowConstraints));
                remainingSpace3 = index$iv$iv + 1;
            }
            overflowPlaceables = target$iv;
        } else {
            overflowPlaceables = null;
        }
        if (this.isVertical) {
            if (overflowPlaceables != null) {
                List $this$fastMaxOfOrNull$iv = overflowPlaceables;
                if (!$this$fastMaxOfOrNull$iv.isEmpty()) {
                    Placeable it6 = (Placeable) $this$fastMaxOfOrNull$iv.get(0);
                    numValueOf7 = Integer.valueOf(it6.getHeight());
                    int i$iv3 = 1;
                    int lastIndex3 = CollectionsKt.getLastIndex($this$fastMaxOfOrNull$iv);
                    if (1 <= lastIndex3) {
                        while (true) {
                            Placeable it7 = (Placeable) $this$fastMaxOfOrNull$iv.get(i$iv3);
                            Integer numValueOf11 = Integer.valueOf(it7.getHeight());
                            if (numValueOf11.compareTo(numValueOf7) > 0) {
                                numValueOf7 = numValueOf11;
                            }
                            if (i$iv3 == lastIndex3) {
                                break;
                            }
                            i$iv3++;
                        }
                    }
                } else {
                    numValueOf7 = null;
                }
                Integer num3 = numValueOf7;
                if (num3 != null) {
                    overflowSpace = num3.intValue();
                } else {
                    overflowSpace = 0;
                }
            } else {
                overflowSpace = 0;
            }
        } else if (overflowPlaceables != null) {
            List $this$fastMaxOfOrNull$iv2 = overflowPlaceables;
            if (!$this$fastMaxOfOrNull$iv2.isEmpty()) {
                Placeable it8 = (Placeable) $this$fastMaxOfOrNull$iv2.get(0);
                numValueOf2 = Integer.valueOf(it8.getWidth());
                int i$iv4 = 1;
                int lastIndex4 = CollectionsKt.getLastIndex($this$fastMaxOfOrNull$iv2);
                if (1 <= lastIndex4) {
                    while (true) {
                        Placeable it9 = (Placeable) $this$fastMaxOfOrNull$iv2.get(i$iv4);
                        Integer numValueOf12 = Integer.valueOf(it9.getWidth());
                        if (numValueOf12.compareTo(numValueOf2) > 0) {
                            numValueOf2 = numValueOf12;
                        }
                        if (i$iv4 == lastIndex4) {
                            break;
                        }
                        i$iv4++;
                    }
                }
            } else {
                numValueOf2 = null;
            }
            Integer num4 = numValueOf2;
            if (num4 != null) {
                overflowSpace = num4.intValue();
            } else {
                overflowSpace = 0;
            }
        } else {
            overflowSpace = 0;
        }
        int currentSpace2 = currentSpace + overflowSpace;
        if (this.isVertical) {
            if (contentPlaceables.isEmpty()) {
                numValueOf5 = null;
            } else {
                Placeable it10 = (Placeable) contentPlaceables.get(0);
                numValueOf5 = Integer.valueOf(it10.getWidth());
                int i$iv5 = 1;
                int lastIndex5 = CollectionsKt.getLastIndex(contentPlaceables);
                if (1 <= lastIndex5) {
                    while (true) {
                        Placeable it11 = (Placeable) contentPlaceables.get(i$iv5);
                        int overflowSpace2 = overflowSpace;
                        Integer numValueOf13 = Integer.valueOf(it11.getWidth());
                        if (numValueOf13.compareTo(numValueOf5) > 0) {
                            numValueOf5 = numValueOf13;
                        }
                        if (i$iv5 == lastIndex5) {
                            break;
                        }
                        i$iv5++;
                        overflowSpace = overflowSpace2;
                    }
                }
            }
            Integer num5 = numValueOf5;
            int iIntValue3 = num5 != null ? num5.intValue() : 0;
            if (overflowPlaceables != null) {
                List $this$fastMaxOfOrNull$iv3 = overflowPlaceables;
                if ($this$fastMaxOfOrNull$iv3.isEmpty()) {
                    numValueOf6 = null;
                } else {
                    Placeable it12 = (Placeable) $this$fastMaxOfOrNull$iv3.get(0);
                    numValueOf6 = Integer.valueOf(it12.getWidth());
                    int i$iv6 = 1;
                    int lastIndex6 = CollectionsKt.getLastIndex($this$fastMaxOfOrNull$iv3);
                    if (1 <= lastIndex6) {
                        while (true) {
                            Placeable it13 = (Placeable) $this$fastMaxOfOrNull$iv3.get(i$iv6);
                            List $this$fastMaxOfOrNull$iv4 = $this$fastMaxOfOrNull$iv3;
                            Integer numValueOf14 = Integer.valueOf(it13.getWidth());
                            if (numValueOf14.compareTo(numValueOf6) > 0) {
                                numValueOf6 = numValueOf14;
                            }
                            if (i$iv6 == lastIndex6) {
                                break;
                            }
                            i$iv6++;
                            $this$fastMaxOfOrNull$iv3 = $this$fastMaxOfOrNull$iv4;
                        }
                    }
                }
                Integer num6 = numValueOf6;
                if (num6 != null) {
                    iIntValue2 = num6.intValue();
                } else {
                    iIntValue2 = 0;
                }
            } else {
                iIntValue2 = 0;
            }
            childrenMaxSpace = Math.max(iIntValue3, iIntValue2);
        } else {
            if (!contentPlaceables.isEmpty()) {
                Placeable it14 = (Placeable) contentPlaceables.get(0);
                numValueOf3 = Integer.valueOf(it14.getHeight());
                int i$iv7 = 1;
                int lastIndex7 = CollectionsKt.getLastIndex(contentPlaceables);
                if (1 <= lastIndex7) {
                    while (true) {
                        Placeable it15 = (Placeable) contentPlaceables.get(i$iv7);
                        Integer numValueOf15 = Integer.valueOf(it15.getHeight());
                        if (numValueOf15.compareTo(numValueOf3) > 0) {
                            numValueOf3 = numValueOf15;
                        }
                        if (i$iv7 == lastIndex7) {
                            break;
                        }
                        i$iv7++;
                    }
                }
            } else {
                numValueOf3 = null;
            }
            Integer num7 = numValueOf3;
            int iIntValue4 = num7 != null ? num7.intValue() : 0;
            if (overflowPlaceables != null) {
                List $this$fastMaxOfOrNull$iv5 = overflowPlaceables;
                if ($this$fastMaxOfOrNull$iv5.isEmpty()) {
                    numValueOf4 = null;
                } else {
                    Placeable it16 = (Placeable) $this$fastMaxOfOrNull$iv5.get(0);
                    numValueOf4 = Integer.valueOf(it16.getHeight());
                    int i$iv8 = 1;
                    int lastIndex8 = CollectionsKt.getLastIndex($this$fastMaxOfOrNull$iv5);
                    if (1 <= lastIndex8) {
                        while (true) {
                            Placeable it17 = (Placeable) $this$fastMaxOfOrNull$iv5.get(i$iv8);
                            List $this$fastMaxOfOrNull$iv6 = $this$fastMaxOfOrNull$iv5;
                            Integer numValueOf16 = Integer.valueOf(it17.getHeight());
                            if (numValueOf16.compareTo(numValueOf4) > 0) {
                                numValueOf4 = numValueOf16;
                            }
                            if (i$iv8 == lastIndex8) {
                                break;
                            }
                            i$iv8++;
                            $this$fastMaxOfOrNull$iv5 = $this$fastMaxOfOrNull$iv6;
                        }
                    }
                }
                Integer num8 = numValueOf4;
                if (num8 != null) {
                    iIntValue = num8.intValue();
                } else {
                    iIntValue = 0;
                }
            } else {
                iIntValue = 0;
            }
            childrenMaxSpace = Math.max(iIntValue4, iIntValue);
        }
        if (this.isVertical) {
            int width = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, childrenMaxSpace);
            int height = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, currentSpace2);
            return MeasureScope.layout$default($this$measure_u2d3p2s80s, width, height, null, new Function1() { // from class: androidx.compose.material3.OverflowMeasurePolicy$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return OverflowMeasurePolicy.measure_3p2s80s$lambda$11(contentPlaceables, overflowPlaceables, (Placeable.PlacementScope) obj);
                }
            }, 4, null);
        }
        int width2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, currentSpace2);
        int height2 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, childrenMaxSpace);
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, width2, height2, null, new Function1() { // from class: androidx.compose.material3.OverflowMeasurePolicy$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return OverflowMeasurePolicy.measure_3p2s80s$lambda$14(contentPlaceables, overflowPlaceables, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$11(List $contentPlaceables, List $overflowPlaceables, Placeable.PlacementScope $this$layout) {
        int index$iv = 0;
        int size = $contentPlaceables.size();
        int currentY = 0;
        while (index$iv < size) {
            Object item$iv = $contentPlaceables.get(index$iv);
            Placeable it = (Placeable) item$iv;
            Placeable.PlacementScope $this$layout2 = $this$layout;
            Placeable.PlacementScope.placeRelative$default($this$layout2, it, 0, currentY, 0.0f, 4, null);
            currentY += it.getHeight();
            index$iv++;
            $this$layout = $this$layout2;
        }
        Placeable.PlacementScope $this$layout3 = $this$layout;
        if ($overflowPlaceables != null) {
            int size2 = $overflowPlaceables.size();
            for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                Object item$iv2 = $overflowPlaceables.get(index$iv2);
                Placeable.PlacementScope.placeRelative$default($this$layout3, (Placeable) item$iv2, 0, currentY, 0.0f, 4, null);
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit measure_3p2s80s$lambda$14(List $contentPlaceables, List $overflowPlaceables, Placeable.PlacementScope $this$layout) {
        int index$iv = 0;
        int size = $contentPlaceables.size();
        int currentX = 0;
        while (index$iv < size) {
            Object item$iv = $contentPlaceables.get(index$iv);
            Placeable it = (Placeable) item$iv;
            Placeable.PlacementScope $this$layout2 = $this$layout;
            Placeable.PlacementScope.placeRelative$default($this$layout2, it, currentX, 0, 0.0f, 4, null);
            currentX += it.getWidth();
            index$iv++;
            $this$layout = $this$layout2;
        }
        Placeable.PlacementScope $this$layout3 = $this$layout;
        if ($overflowPlaceables != null) {
            int size2 = $overflowPlaceables.size();
            for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                Object item$iv2 = $overflowPlaceables.get(index$iv2);
                Placeable.PlacementScope.placeRelative$default($this$layout3, (Placeable) item$iv2, currentX, 0, 0.0f, 4, null);
            }
        }
        return Unit.INSTANCE;
    }
}
