package androidx.compose.foundation.lazy.grid;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItemKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutStickyItemsKt;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.foundation.lazy.layout.StickyItemsPlacement;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.math.MathKt;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyGridMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¸\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\u0002\u001aü\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u00032\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030 2\u0006\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00132\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*23\u0010+\u001a/\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0011000 0,2!\u00101\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020\u00030,2\b\u00103\u001a\u0004\u0018\u0001042/\u00105\u001a+\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u0002080,¢\u0006\u0002\b9\u0012\u0004\u0012\u00020:06H\u0000¢\u0006\u0004\b;\u0010<\u001aA\u0010=\u001a\b\u0012\u0004\u0012\u00020\u001d0 2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030 2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00130,H\u0082\b\u001aF\u0010?\u001a\b\u0012\u0004\u0012\u00020@0 2\u0006\u0010A\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u00132\f\u0010B\u001a\b\u0012\u0004\u0012\u00020@0 2\b\u0010C\u001a\u0004\u0018\u00010$H\u0002\u001a\u008c\u0001\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001d0E2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020@0 2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u001d0 2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u001d0 2\u0006\u0010I\u001a\u00020\u00032\u0006\u0010J\u001a\u00020\u00032\u0006\u0010K\u001a\u00020\u00032\u0006\u0010L\u001a\u00020\u00032\u0006\u0010M\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\u0002\u001a+\u0010N\u001a\u000208\"\u0004\b\u0000\u0010O*\b\u0012\u0004\u0012\u0002HO0E2\f\u0010P\u001a\b\u0012\u0004\u0012\u0002HO0QH\u0002¢\u0006\u0002\u0010R¨\u0006S"}, d2 = {"measureLazyGrid", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "itemsCount", "", "measuredLineProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;", "measuredItemProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenLines", "firstVisibleLineIndex", "firstVisibleLineScrollOffset", "scrollToBeConsumed", "", "constraints", "Landroidx/compose/ui/unit/Constraints;", "isVertical", "", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "itemAnimator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "slotsPerLine", "pinnedItems", "", "isInLookaheadScope", "isLookingAhead", "approachLayoutInfo", "Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "prefetchInfoRetriever", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "line", "Lkotlin/Pair;", "lineIndexProvider", "itemIndex", "stickyItemsScrollBehavior", "Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;", "layout", "Lkotlin/Function3;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyGrid-t1x4au0", "(ILandroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;IIIIIIFJZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;ILjava/util/List;ZZLandroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/graphics/GraphicsContext;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "calculateExtraItems", "filter", "linesRetainedForLookahead", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLine;", "lastVisibleItemIndex", "visibleLines", "lastApproachLayoutInfo", "calculateItemsOffsets", "", "lines", "itemsBefore", "itemsAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "firstLineScrollOffset", "addAllFromArray", ExifInterface.GPS_DIRECTION_TRUE, "arr", "", "(Ljava/util/List;[Ljava/lang/Object;)V", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyGridMeasureKt {
    /* JADX WARN: Code duplicated, block: B:128:0x0323  */
    /* JADX WARN: Code duplicated, block: B:130:0x0340  */
    /* JADX WARN: Code duplicated, block: B:131:0x034c  */
    /* JADX WARN: Code duplicated, block: B:133:0x0354  */
    /* JADX WARN: Code duplicated, block: B:166:0x0468  */
    /* JADX WARN: Code duplicated, block: B:231:0x0681  */
    /* JADX WARN: Code duplicated, block: B:232:0x0684  */
    /* JADX WARN: Failed to apply debug info
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r1v17 ??, new type: androidx.compose.foundation.lazy.grid.LazyGridMeasuredLine
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyDebugInfo(TypeUpdate.java:77)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:133)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:79)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
    Caused by: java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r1v17 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r1v17 ??, new type: androidx.compose.foundation.lazy.grid.LazyGridMeasuredLine
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:159)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:136)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:241)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
    Caused by: java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r1v17 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r1v17 ??, new type: androidx.compose.foundation.lazy.grid.LazyGridMeasuredLine
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException
     */
    /* JADX WARN: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r1v17 androidx.compose.foundation.lazy.grid.LazyGridMeasuredLine, new type: androidx.compose.foundation.lazy.grid.LazyGridMeasuredLine
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnSame(TypeUpdate.java:73)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.applyResolvedVars(TypeSearch.java:100)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:76)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.runMultiVariableSearch(FixTypesVisitor.java:119)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
    Caused by: java.lang.NullPointerException
     */
    /* JADX INFO: renamed from: measureLazyGrid-t1x4au0, reason: not valid java name */
    public static final LazyGridMeasureResult m959measureLazyGridt1x4au0(int itemsCount, final LazyGridMeasuredLineProvider measuredLineProvider, final LazyGridMeasuredItemProvider measuredItemProvider, int mainAxisAvailableSize, int beforeContentPadding, int afterContentPadding, int spaceBetweenLines, int firstVisibleLineIndex, int firstVisibleLineScrollOffset, float scrollToBeConsumed, long j, boolean isVertical, Arrangement.Vertical verticalArrangement, Arrangement.Horizontal horizontalArrangement, boolean reverseLayout, Density density, LazyLayoutItemAnimator<LazyGridMeasuredItem> lazyLayoutItemAnimator, int slotsPerLine, List<Integer> list, boolean isInLookaheadScope, final boolean isLookingAhead, LazyGridLayoutInfo approachLayoutInfo, CoroutineScope coroutineScope, final MutableState<Unit> mutableState, GraphicsContext graphicsContext, Function1<? super Integer, ? extends List<Pair<Integer, Constraints>>> function1, Function1<? super Integer, Integer> function2, StickyItemsPlacement stickyItemsScrollBehavior, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> function3) {
        int currentFirstLineIndex;
        int currentFirstLineIndex2;
        int scrollDelta;
        int currentMainAxisOffset;
        int currentFirstLineScrollOffset;
        int currentFirstLineScrollOffset2;
        int layoutWidth;
        int layoutHeight;
        float consumedScroll;
        List<LazyGridMeasuredItem> list2;
        int layoutWidth2;
        int layoutHeight2;
        Orientation orientation;
        int i;
        boolean z;
        List arrayList;
        boolean z2;
        boolean z3;
        int i2;
        int i3;
        int i4;
        boolean z4;
        LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider;
        List arrayList2;
        LazyGridMeasuredItem[] items;
        LazyGridMeasuredItem lazyGridMeasuredItem;
        LazyGridMeasuredItem[] items2;
        LazyGridMeasuredItem lazyGridMeasuredItem2;
        int i5;
        int i6 = itemsCount;
        boolean z5 = true;
        boolean value$iv = beforeContentPadding >= 0;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("negative beforeContentPadding");
        }
        boolean value$iv2 = afterContentPadding >= 0;
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalArgumentException("negative afterContentPadding");
        }
        char c = ' ';
        if (i6 <= 0) {
            int iM7860getMinWidthimpl = Constraints.m7860getMinWidthimpl(j);
            int iM7859getMinHeightimpl = Constraints.m7859getMinHeightimpl(j);
            lazyLayoutItemAnimator.onMeasured(0, iM7860getMinWidthimpl, iM7859getMinHeightimpl, new ArrayList(), measuredItemProvider.getKeyIndexMap(), measuredItemProvider, isVertical, isLookingAhead, slotsPerLine, isInLookaheadScope, 0, 0, coroutineScope, graphicsContext);
            if (!isLookingAhead) {
                long jM988getMinSizeToFitDisappearingItemsYbymL2g = lazyLayoutItemAnimator.m988getMinSizeToFitDisappearingItemsYbymL2g();
                if (!IntSize.m8074equalsimpl0(jM988getMinSizeToFitDisappearingItemsYbymL2g, IntSize.INSTANCE.m8081getZeroYbymL2g())) {
                    iM7860getMinWidthimpl = ConstraintsKt.m7875constrainWidthK40F9xA(j, (int) (jM988getMinSizeToFitDisappearingItemsYbymL2g >> 32));
                    iM7859getMinHeightimpl = ConstraintsKt.m7874constrainHeightK40F9xA(j, (int) (jM988getMinSizeToFitDisappearingItemsYbymL2g & 4294967295L));
                }
            }
            return new LazyGridMeasureResult(null, 0, false, 0.0f, function3.invoke(Integer.valueOf(iM7860getMinWidthimpl), Integer.valueOf(iM7859getMinHeightimpl), new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Unit.INSTANCE;
                }
            }), 0.0f, false, coroutineScope, density, slotsPerLine, function1, function2, CollectionsKt.emptyList(), -beforeContentPadding, mainAxisAvailableSize + afterContentPadding, 0, reverseLayout, isVertical ? Orientation.Vertical : Orientation.Horizontal, afterContentPadding, spaceBetweenLines);
        }
        int currentFirstLineIndex3 = firstVisibleLineIndex;
        int scrollDelta2 = Math.round(scrollToBeConsumed);
        int currentFirstLineScrollOffset3 = firstVisibleLineScrollOffset - scrollDelta2;
        if (currentFirstLineIndex3 == 0 && currentFirstLineScrollOffset3 < 0) {
            scrollDelta2 += currentFirstLineScrollOffset3;
            currentFirstLineScrollOffset3 = 0;
        }
        ArrayDeque visibleLines = new ArrayDeque();
        int minOffset = (spaceBetweenLines < 0 ? spaceBetweenLines : 0) + (-beforeContentPadding);
        int currentFirstLineScrollOffset4 = currentFirstLineScrollOffset3 + minOffset;
        while (currentFirstLineScrollOffset4 < 0 && currentFirstLineIndex3 > 0) {
            int i7 = currentFirstLineIndex3 - 1;
            char c2 = c;
            LazyGridMeasuredLine andMeasure = measuredLineProvider.getAndMeasure(i7);
            visibleLines.add(0, andMeasure);
            currentFirstLineScrollOffset4 += andMeasure.getMainAxisSizeWithSpacings();
            currentFirstLineIndex3 = i7;
            c = c2;
        }
        char c3 = c;
        if (currentFirstLineScrollOffset4 < minOffset) {
            int i8 = minOffset - currentFirstLineScrollOffset4;
            currentFirstLineScrollOffset4 = minOffset;
            scrollDelta2 -= i8;
        }
        int currentFirstLineScrollOffset5 = currentFirstLineScrollOffset4 - minOffset;
        int index = currentFirstLineIndex3;
        int maxMainAxis = RangesKt.coerceAtLeast(mainAxisAvailableSize + afterContentPadding, 0);
        int currentMainAxisOffset2 = -currentFirstLineScrollOffset5;
        boolean remeasureNeeded = false;
        int indexInVisibleLines = 0;
        while (true) {
            currentFirstLineIndex = currentFirstLineIndex3;
            if (indexInVisibleLines >= visibleLines.size()) {
                break;
            }
            if (currentMainAxisOffset2 >= maxMainAxis) {
                visibleLines.remove(indexInVisibleLines);
                Unit unit = Unit.INSTANCE;
                remeasureNeeded = true;
            } else {
                index++;
                currentMainAxisOffset2 += ((LazyGridMeasuredLine) visibleLines.get(indexInVisibleLines)).getMainAxisSizeWithSpacings();
                Integer.valueOf(indexInVisibleLines);
                indexInVisibleLines++;
            }
            currentFirstLineIndex3 = currentFirstLineIndex;
        }
        int i9 = currentMainAxisOffset2;
        int index2 = index;
        int currentMainAxisOffset3 = i9;
        boolean remeasureNeeded2 = remeasureNeeded;
        int currentFirstLineIndex4 = currentFirstLineIndex;
        while (true) {
            if (index2 >= i6) {
                currentFirstLineIndex2 = currentFirstLineIndex4;
                break;
            }
            if (currentMainAxisOffset3 >= maxMainAxis && currentMainAxisOffset3 > 0 && !visibleLines.isEmpty()) {
                currentFirstLineIndex2 = currentFirstLineIndex4;
                break;
            }
            currentFirstLineIndex2 = currentFirstLineIndex4;
            LazyGridMeasuredLine andMeasure2 = measuredLineProvider.getAndMeasure(index2);
            if (andMeasure2.isEmpty()) {
                break;
            }
            currentMainAxisOffset3 += andMeasure2.getMainAxisSizeWithSpacings();
            if (currentMainAxisOffset3 > minOffset) {
                i5 = currentFirstLineScrollOffset5;
            } else {
                i5 = currentFirstLineScrollOffset5;
                if (((LazyGridMeasuredItem) ArraysKt.last(andMeasure2.getItems())).getIndex() != itemsCount - 1) {
                    currentFirstLineScrollOffset5 = i5 - andMeasure2.getMainAxisSizeWithSpacings();
                    Unit unit2 = Unit.INSTANCE;
                    remeasureNeeded2 = true;
                    currentFirstLineIndex2 = index2 + 1;
                }
                index2++;
                i6 = itemsCount;
                currentFirstLineIndex4 = currentFirstLineIndex2;
            }
            Boolean.valueOf(visibleLines.add(andMeasure2));
            currentFirstLineScrollOffset5 = i5;
            index2++;
            i6 = itemsCount;
            currentFirstLineIndex4 = currentFirstLineIndex2;
        }
        int currentFirstLineIndex5 = scrollDelta2;
        if (currentMainAxisOffset3 >= mainAxisAvailableSize) {
            scrollDelta = scrollDelta2;
            currentMainAxisOffset = currentMainAxisOffset3;
            currentFirstLineScrollOffset = currentFirstLineScrollOffset5;
        } else {
            int i10 = mainAxisAvailableSize - currentMainAxisOffset3;
            int mainAxisSizeWithSpacings = currentFirstLineScrollOffset5 - i10;
            int i11 = currentMainAxisOffset3 + i10;
            while (mainAxisSizeWithSpacings < beforeContentPadding && currentFirstLineIndex2 > 0) {
                int i12 = i10;
                int i13 = currentFirstLineIndex2 - 1;
                int i14 = mainAxisSizeWithSpacings;
                LazyGridMeasuredLine andMeasure3 = measuredLineProvider.getAndMeasure(i13);
                visibleLines.add(0, andMeasure3);
                currentFirstLineIndex2 = i13;
                mainAxisSizeWithSpacings = i14 + andMeasure3.getMainAxisSizeWithSpacings();
                i10 = i12;
            }
            int i15 = mainAxisSizeWithSpacings;
            int i16 = scrollDelta2 + i10;
            if (i15 >= 0) {
                scrollDelta = i16;
                currentMainAxisOffset = i11;
                currentFirstLineScrollOffset = i15;
            } else {
                currentFirstLineScrollOffset = 0;
                scrollDelta = i16 + i15;
                currentMainAxisOffset = i11 + i15;
            }
        }
        float consumedScroll2 = (MathKt.getSign(Math.round(scrollToBeConsumed)) == MathKt.getSign(scrollDelta) && Math.abs(Math.round(scrollToBeConsumed)) >= Math.abs(scrollDelta)) ? scrollDelta : scrollToBeConsumed;
        float unconsumedScroll = scrollToBeConsumed - consumedScroll2;
        float f = 0.0f;
        if (isLookingAhead && scrollDelta > currentFirstLineIndex5 && unconsumedScroll <= 0.0f) {
            f = (scrollDelta - currentFirstLineIndex5) + unconsumedScroll;
        }
        float scrollBackAmount = f;
        boolean value$iv3 = currentFirstLineScrollOffset >= 0;
        if (!value$iv3) {
            InlineClassHelperKt.throwIllegalArgumentException("negative initial offset");
        }
        int visibleLinesScrollOffset = -currentFirstLineScrollOffset;
        LazyGridMeasuredLine firstLine = (LazyGridMeasuredLine) visibleLines.firstOrNull();
        int firstItemIndex = (firstLine == null || (items2 = firstLine.getItems()) == null || (lazyGridMeasuredItem2 = (LazyGridMeasuredItem) ArraysKt.firstOrNull(items2)) == null) ? 0 : lazyGridMeasuredItem2.getIndex();
        LazyGridMeasuredLine lazyGridMeasuredLine = (LazyGridMeasuredLine) visibleLines.lastOrNull();
        int lastItemIndex = (lazyGridMeasuredLine == null || (items = lazyGridMeasuredLine.getItems()) == null || (lazyGridMeasuredItem = (LazyGridMeasuredItem) ArraysKt.lastOrNull(items)) == null) ? 0 : lazyGridMeasuredItem.getIndex();
        LazyGridMeasuredLineProvider measuredLineProvider$iv = measuredLineProvider;
        List<Integer> list3 = list;
        int preScrollBackScrollDelta = list3.size();
        int currentFirstLineScrollOffset6 = currentFirstLineScrollOffset;
        List extraItemsBefore = null;
        int index$iv$iv = 0;
        while (index$iv$iv < preScrollBackScrollDelta) {
            int i17 = preScrollBackScrollDelta;
            List<Integer> list4 = list3;
            int iIntValue = list4.get(index$iv$iv).intValue();
            if (iIntValue >= 0) {
                i2 = index$iv$iv;
                i3 = indexInVisibleLines;
                i4 = firstItemIndex;
                z4 = iIntValue < i4;
                if (z4) {
                    lazyGridMeasuredLineProvider = measuredLineProvider$iv;
                    int iSpanOf = lazyGridMeasuredLineProvider.spanOf(iIntValue);
                    LazyGridMeasuredItem lazyGridMeasuredItemMo934getAndMeasurehBUhpc = measuredItemProvider.mo934getAndMeasurehBUhpc(iIntValue, 0, iSpanOf, lazyGridMeasuredLineProvider.m963childConstraintsJhjzzOo$foundation(0, iSpanOf));
                    if (extraItemsBefore == null) {
                        arrayList2 = extraItemsBefore;
                    } else {
                        arrayList2 = new ArrayList();
                    }
                    arrayList2.add(lazyGridMeasuredItemMo934getAndMeasurehBUhpc);
                    extraItemsBefore = arrayList2;
                } else {
                    lazyGridMeasuredLineProvider = measuredLineProvider$iv;
                }
                measuredLineProvider$iv = lazyGridMeasuredLineProvider;
                firstItemIndex = i4;
                list3 = list4;
                indexInVisibleLines = i3;
                index$iv$iv = i2 + 1;
                preScrollBackScrollDelta = i17;
            } else {
                i2 = index$iv$iv;
                i3 = indexInVisibleLines;
                i4 = firstItemIndex;
            }
            if (z4) {
                lazyGridMeasuredLineProvider = measuredLineProvider$iv;
                int iSpanOf2 = lazyGridMeasuredLineProvider.spanOf(iIntValue);
                LazyGridMeasuredItem lazyGridMeasuredItemMo934getAndMeasurehBUhpc2 = measuredItemProvider.mo934getAndMeasurehBUhpc(iIntValue, 0, iSpanOf2, lazyGridMeasuredLineProvider.m963childConstraintsJhjzzOo$foundation(0, iSpanOf2));
                if (extraItemsBefore == null) {
                    arrayList2 = extraItemsBefore;
                } else {
                    arrayList2 = new ArrayList();
                }
                arrayList2.add(lazyGridMeasuredItemMo934getAndMeasurehBUhpc2);
                extraItemsBefore = arrayList2;
            } else {
                lazyGridMeasuredLineProvider = measuredLineProvider$iv;
            }
            measuredLineProvider$iv = lazyGridMeasuredLineProvider;
            firstItemIndex = i4;
            list3 = list4;
            indexInVisibleLines = i3;
            index$iv$iv = i2 + 1;
            preScrollBackScrollDelta = i17;
        }
        int indexInVisibleLines2 = firstItemIndex;
        if (extraItemsBefore == null) {
            extraItemsBefore = CollectionsKt.emptyList();
        }
        float consumedScroll3 = consumedScroll2;
        int i18 = itemsCount;
        int index3 = currentMainAxisOffset;
        int lastItemIndex2 = lastItemIndex;
        List<LazyGridMeasuredLine> listLinesRetainedForLookahead = linesRetainedForLookahead(lastItemIndex2, i18, measuredLineProvider, isLookingAhead, visibleLines, approachLayoutInfo);
        int $i$f$calculateExtraItems = 0;
        List<Integer> list5 = list;
        int lastItemIndex3 = list5.size();
        List extraItemsAfter = null;
        int index$iv$iv2 = 0;
        while (index$iv$iv2 < lastItemIndex3) {
            int i19 = lastItemIndex3;
            List<Integer> list6 = list5;
            int iIntValue2 = list6.get(index$iv$iv2).intValue();
            int i20 = index$iv$iv2;
            int i21 = $i$f$calculateExtraItems;
            if (lastItemIndex2 + 1 <= iIntValue2 && iIntValue2 < i18) {
                if (isLookingAhead) {
                    List<LazyGridMeasuredLine> list7 = listLinesRetainedForLookahead;
                    int size = list7.size();
                    int i22 = 0;
                    while (true) {
                        if (i22 < size) {
                            int i23 = size;
                            List<LazyGridMeasuredLine> list8 = list7;
                            LazyGridMeasuredItem[] items3 = list8.get(i22).getItems();
                            int i24 = i22;
                            int length = items3.length;
                            int i25 = 0;
                            while (true) {
                                if (i25 < length) {
                                    int i26 = i25;
                                    if (items3[i25].getIndex() == iIntValue2) {
                                        z3 = true;
                                        break;
                                    }
                                    i25 = i26 + 1;
                                } else {
                                    z3 = false;
                                    break;
                                }
                            }
                            if (z3) {
                                z2 = true;
                                break;
                            }
                            i22 = i24 + 1;
                            size = i23;
                            list7 = list8;
                        } else {
                            z2 = false;
                            break;
                        }
                    }
                    if (z2) {
                        z = false;
                    }
                }
                z = true;
            } else {
                z = false;
            }
            if (z) {
                int iSpanOf3 = measuredLineProvider.spanOf(iIntValue2);
                LazyGridMeasuredItem lazyGridMeasuredItemMo934getAndMeasurehBUhpc3 = measuredItemProvider.mo934getAndMeasurehBUhpc(iIntValue2, 0, iSpanOf3, measuredLineProvider.m963childConstraintsJhjzzOo$foundation(0, iSpanOf3));
                if (extraItemsAfter != null) {
                    arrayList = extraItemsAfter;
                } else {
                    arrayList = new ArrayList();
                }
                arrayList.add(lazyGridMeasuredItemMo934getAndMeasurehBUhpc3);
                extraItemsAfter = arrayList;
            }
            index$iv$iv2 = i20 + 1;
            lastItemIndex3 = i19;
            list5 = list6;
            $i$f$calculateExtraItems = i21;
            i18 = itemsCount;
        }
        if (extraItemsAfter == null) {
            extraItemsAfter = CollectionsKt.emptyList();
        }
        if (beforeContentPadding > 0 || spaceBetweenLines < 0) {
            int size2 = visibleLines.size();
            int currentFirstLineScrollOffset7 = currentFirstLineScrollOffset6;
            for (int i27 = 0; i27 < size2; i27++) {
                int size3 = ((LazyGridMeasuredLine) visibleLines.get(i27)).getMainAxisSizeWithSpacings();
                if (currentFirstLineScrollOffset7 == 0 || size3 > currentFirstLineScrollOffset7 || i27 == CollectionsKt.getLastIndex(visibleLines)) {
                    break;
                }
                currentFirstLineScrollOffset7 -= size3;
                firstLine = (LazyGridMeasuredLine) visibleLines.get(i27 + 1);
            }
            currentFirstLineScrollOffset2 = currentFirstLineScrollOffset7;
        } else {
            currentFirstLineScrollOffset2 = currentFirstLineScrollOffset6;
        }
        if (isVertical) {
            layoutWidth = Constraints.m7858getMaxWidthimpl(j);
        } else {
            layoutWidth = ConstraintsKt.m7875constrainWidthK40F9xA(j, index3);
        }
        if (isVertical) {
            layoutHeight = ConstraintsKt.m7874constrainHeightK40F9xA(j, index3);
        } else {
            layoutHeight = Constraints.m7857getMaxHeightimpl(j);
        }
        List extraItemsBefore2 = extraItemsBefore;
        List extraItemsBefore3 = extraItemsAfter;
        List<LazyGridMeasuredItem> listCalculateItemsOffsets = calculateItemsOffsets(listLinesRetainedForLookahead.isEmpty() ? visibleLines : CollectionsKt.plus((Collection) visibleLines, (Iterable) listLinesRetainedForLookahead), extraItemsBefore2, extraItemsBefore3, layoutWidth, layoutHeight, index3, mainAxisAvailableSize, visibleLinesScrollOffset, isVertical, verticalArrangement, horizontalArrangement, reverseLayout, density);
        int currentFirstLineScrollOffset8 = currentFirstLineScrollOffset2;
        int layoutWidth3 = layoutWidth;
        int layoutHeight3 = layoutHeight;
        lazyLayoutItemAnimator.onMeasured((int) consumedScroll3, layoutWidth3, layoutHeight3, listCalculateItemsOffsets, measuredItemProvider.getKeyIndexMap(), measuredItemProvider, isVertical, isLookingAhead, slotsPerLine, isInLookaheadScope, currentFirstLineScrollOffset8, index3, coroutineScope, graphicsContext);
        if (!isLookingAhead) {
            long jM988getMinSizeToFitDisappearingItemsYbymL2g2 = lazyLayoutItemAnimator.m988getMinSizeToFitDisappearingItemsYbymL2g();
            consumedScroll = consumedScroll3;
            list2 = listCalculateItemsOffsets;
            if (!IntSize.m8074equalsimpl0(jM988getMinSizeToFitDisappearingItemsYbymL2g2, IntSize.INSTANCE.m8081getZeroYbymL2g())) {
                int i28 = isVertical ? layoutHeight3 : layoutWidth3;
                int iM7875constrainWidthK40F9xA = ConstraintsKt.m7875constrainWidthK40F9xA(j, Math.max(layoutWidth3, (int) (jM988getMinSizeToFitDisappearingItemsYbymL2g2 >> c3)));
                int iM7874constrainHeightK40F9xA = ConstraintsKt.m7874constrainHeightK40F9xA(j, Math.max(layoutHeight3, (int) (jM988getMinSizeToFitDisappearingItemsYbymL2g2 & 4294967295L)));
                if (isVertical) {
                    i = iM7874constrainHeightK40F9xA;
                } else {
                    i = iM7875constrainWidthK40F9xA;
                }
                if (i != i28) {
                    int i29 = 0;
                    for (int size4 = list2.size(); i29 < size4; size4 = size4) {
                        list2.get(i29).updateMainAxisLayoutSize(i);
                        i29++;
                    }
                }
                layoutWidth2 = iM7875constrainWidthK40F9xA;
                layoutHeight2 = iM7874constrainHeightK40F9xA;
            }
            final List<LazyGridMeasuredItem> list9 = list2;
            final List stickingItems = LazyLayoutStickyItemsKt.applyStickyItems(stickyItemsScrollBehavior, indexInVisibleLines2, lastItemIndex2, list9, measuredItemProvider.getHeaderIndices(), beforeContentPadding, afterContentPadding, layoutWidth2, layoutHeight2, new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LazyGridMeasureKt.measureLazyGrid_t1x4au0$lambda$7(measuredLineProvider, measuredItemProvider, ((Integer) obj).intValue());
                }
            });
            int layoutWidth4 = layoutWidth2;
            int layoutHeight4 = layoutHeight2;
            if (lastItemIndex2 == itemsCount - 1 && index3 <= mainAxisAvailableSize) {
                z5 = false;
            }
            MeasureResult measureResultInvoke = function3.invoke(Integer.valueOf(layoutWidth4), Integer.valueOf(layoutHeight4), new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LazyGridMeasureKt.measureLazyGrid_t1x4au0$lambda$8(mutableState, list9, stickingItems, isLookingAhead, (Placeable.PlacementScope) obj);
                }
            });
            int i30 = -beforeContentPadding;
            int i31 = mainAxisAvailableSize + afterContentPadding;
            List listUpdatedVisibleItems = LazyLayoutMeasuredItemKt.updatedVisibleItems(indexInVisibleLines2, lastItemIndex2, list9, stickingItems);
            if (isVertical) {
                orientation = Orientation.Vertical;
            } else {
                orientation = Orientation.Horizontal;
            }
            return new LazyGridMeasureResult(
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x06d2: RETURN 
                  (wrap androidx.compose.foundation.lazy.grid.LazyGridMeasureResult:0x06cb: CONSTRUCTOR 
                  (r1v13 androidx.compose.foundation.lazy.grid.LazyGridMeasuredLine)
                  (r24v1 'currentFirstLineScrollOffset8' int A[D('currentFirstLineScrollOffset' int)])
                  (r28v1 'z5' boolean)
                  (r17v16 'consumedScroll' float A[D('consumedScroll' float)])
                  (r11v8 'measureResultInvoke' androidx.compose.ui.layout.MeasureResult A[D('currentMainAxisOffset' int)])
                  (r49v0 'scrollBackAmount' float A[D('scrollBackAmount' float)])
                  (r46v1 'remeasureNeeded2' boolean A[D('remeasureNeeded' boolean)])
                  (r88v0 'coroutineScope' kotlinx.coroutines.CoroutineScope A[D('coroutineScope' kotlinx.coroutines.CoroutineScope)])
                  (r81v0 'density' androidx.compose.ui.unit.Density A[D('density' androidx.compose.ui.unit.Density)])
                  (r83v0 'slotsPerLine' int A[D('slotsPerLine' int)])
                  (r91v0 'function1' kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends java.util.List<kotlin.Pair<java.lang.Integer, androidx.compose.ui.unit.Constraints>>> A[D('prefetchInfoRetriever' kotlin.jvm.functions.Function1)])
                  (r92v0 'function2' kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.Integer> A[D('lineIndexProvider' kotlin.jvm.functions.Function1)])
                  (r13v4 'listUpdatedVisibleItems' java.util.List)
                  (r14v7 'i30' int)
                  (r15v4 'i31' int)
                  (r65v0 'itemsCount' int A[D('itemsCount' int)])
                  (r80v0 'reverseLayout' boolean A[D('reverseLayout' boolean)])
                  (r12v3 'orientation' androidx.compose.foundation.gestures.Orientation)
                  (r70v0 'afterContentPadding' int A[D('afterContentPadding' int)])
                  (r71v0 'spaceBetweenLines' int A[D('spaceBetweenLines' int)])
                 A[MD:(androidx.compose.foundation.lazy.grid.LazyGridMeasuredLine, int, boolean, float, androidx.compose.ui.layout.MeasureResult, float, boolean, kotlinx.coroutines.CoroutineScope, androidx.compose.ui.unit.Density, int, kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends java.util.List<kotlin.Pair<java.lang.Integer, androidx.compose.ui.unit.Constraints>>>, kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.Integer>, java.util.List<androidx.compose.foundation.lazy.grid.LazyGridMeasuredItem>, int, int, int, boolean, androidx.compose.foundation.gestures.Orientation, int, int):void (m), WRAPPED] (LINE:427) call: androidx.compose.foundation.lazy.grid.LazyGridMeasureResult.<init>(androidx.compose.foundation.lazy.grid.LazyGridMeasuredLine, int, boolean, float, androidx.compose.ui.layout.MeasureResult, float, boolean, kotlinx.coroutines.CoroutineScope, androidx.compose.ui.unit.Density, int, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, java.util.List, int, int, int, boolean, androidx.compose.foundation.gestures.Orientation, int, int):void type: CONSTRUCTOR)
                 (LINE:427) in method: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt.measureLazyGrid-t1x4au0(int, androidx.compose.foundation.lazy.grid.LazyGridMeasuredLineProvider, androidx.compose.foundation.lazy.grid.LazyGridMeasuredItemProvider, int, int, int, int, int, int, float, long, boolean, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, boolean, androidx.compose.ui.unit.Density, androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator<androidx.compose.foundation.lazy.grid.LazyGridMeasuredItem>, int, java.util.List<java.lang.Integer>, boolean, boolean, androidx.compose.foundation.lazy.grid.LazyGridLayoutInfo, kotlinx.coroutines.CoroutineScope, androidx.compose.runtime.MutableState<kotlin.Unit>, androidx.compose.ui.graphics.GraphicsContext, kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends java.util.List<kotlin.Pair<java.lang.Integer, androidx.compose.ui.unit.Constraints>>>, kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.Integer>, androidx.compose.foundation.lazy.layout.StickyItemsPlacement, kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super java.lang.Integer, ? super kotlin.jvm.functions.Function1<? super androidx.compose.ui.layout.Placeable$PlacementScope, kotlin.Unit>, ? extends androidx.compose.ui.layout.MeasureResult>):androidx.compose.foundation.lazy.grid.LazyGridMeasureResult, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r1v13 androidx.compose.foundation.lazy.grid.LazyGridMeasuredLine
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                */
            /*
                Method dump skipped, instruction units count: 1747
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt.m959measureLazyGridt1x4au0(int, androidx.compose.foundation.lazy.grid.LazyGridMeasuredLineProvider, androidx.compose.foundation.lazy.grid.LazyGridMeasuredItemProvider, int, int, int, int, int, int, float, long, boolean, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, boolean, androidx.compose.ui.unit.Density, androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator, int, java.util.List, boolean, boolean, androidx.compose.foundation.lazy.grid.LazyGridLayoutInfo, kotlinx.coroutines.CoroutineScope, androidx.compose.runtime.MutableState, androidx.compose.ui.graphics.GraphicsContext, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, androidx.compose.foundation.lazy.layout.StickyItemsPlacement, kotlin.jvm.functions.Function3):androidx.compose.foundation.lazy.grid.LazyGridMeasureResult");
        }

        static final LazyGridMeasuredItem measureLazyGrid_t1x4au0$lambda$7(LazyGridMeasuredLineProvider $measuredLineProvider, LazyGridMeasuredItemProvider $measuredItemProvider, int it) {
            int span = $measuredLineProvider.spanOf(it);
            long childConstraints = $measuredLineProvider.m963childConstraintsJhjzzOo$foundation(0, span);
            return $measuredItemProvider.mo934getAndMeasurehBUhpc(it, 0, span, childConstraints);
        }

        static final Unit measureLazyGrid_t1x4au0$lambda$8(MutableState mutableState, final List $positionedItems, final List $stickingItems, final boolean $isLookingAhead, Placeable.PlacementScope $this$layout) {
            $this$layout.withMotionFrameOfReferencePlacement(new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LazyGridMeasureKt.measureLazyGrid_t1x4au0$lambda$8$0($positionedItems, $stickingItems, $isLookingAhead, (Placeable.PlacementScope) obj);
                }
            });
            ObservableScopeInvalidator.m1006attachToScopeimpl(mutableState);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit measureLazyGrid_t1x4au0$lambda$8$0(List $positionedItems, List $stickingItems, boolean $isLookingAhead, Placeable.PlacementScope $this$withMotionFrameOfReferencePlacement) {
            int size = $positionedItems.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = $positionedItems.get(index$iv);
                LazyGridMeasuredItem it = (LazyGridMeasuredItem) item$iv;
                it.place($this$withMotionFrameOfReferencePlacement, $isLookingAhead);
            }
            int size2 = $stickingItems.size();
            for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                Object item$iv2 = $stickingItems.get(index$iv2);
                LazyGridMeasuredItem it2 = (LazyGridMeasuredItem) item$iv2;
                it2.place($this$withMotionFrameOfReferencePlacement, $isLookingAhead);
            }
            return Unit.INSTANCE;
        }

        private static final List<LazyGridMeasuredItem> calculateExtraItems(List<Integer> list, LazyGridMeasuredItemProvider measuredItemProvider, LazyGridMeasuredLineProvider measuredLineProvider, Function1<? super Integer, Boolean> function1) {
            ArrayList arrayList = null;
            int size = list.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = list.get(index$iv);
                int index = ((Number) item$iv).intValue();
                if (function1.invoke(Integer.valueOf(index)).booleanValue()) {
                    int span = measuredLineProvider.spanOf(index);
                    long constraints = measuredLineProvider.m963childConstraintsJhjzzOo$foundation(0, span);
                    LazyGridMeasuredItem measuredItem = measuredItemProvider.mo934getAndMeasurehBUhpc(index, 0, span, constraints);
                    if (arrayList == null) {
                        Object items = new ArrayList();
                        arrayList = (List) items;
                    }
                    arrayList.add(measuredItem);
                }
            }
            return arrayList == null ? CollectionsKt.emptyList() : arrayList;
        }

        /* JADX WARN: Code duplicated, block: B:49:0x00d3 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:50:0x00d5  */
        /* JADX WARN: Code duplicated, block: B:52:0x00e9  */
        /* JADX WARN: Code duplicated, block: B:54:0x00ed A[LOOP:1: B:25:0x007b->B:54:0x00ed, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:68:0x00fe A[EDGE_INSN: B:68:0x00fe->B:59:0x00fe BREAK  A[LOOP:1: B:25:0x007b->B:54:0x00ed], SYNTHETIC] */
        private static final List<LazyGridMeasuredLine> linesRetainedForLookahead(int lastVisibleItemIndex, int itemsCount, LazyGridMeasuredLineProvider measuredLineProvider, boolean isLookingAhead, List<LazyGridMeasuredLine> list, LazyGridLayoutInfo lastApproachLayoutInfo) {
            int i;
            int iMin;
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4;
            List list2 = null;
            if (isLookingAhead && lastApproachLayoutInfo != null && !lastApproachLayoutInfo.getVisibleItemsInfo().isEmpty()) {
                List<LazyGridItemInfo> visibleItemsInfo = lastApproachLayoutInfo.getVisibleItemsInfo();
                LazyGridItemInfo found = null;
                boolean z5 = true;
                for (int i2 = visibleItemsInfo.size() - 1; -1 < i2; i2--) {
                    if (visibleItemsInfo.get(i2).getIndex() > lastVisibleItemIndex && (i2 == 0 || visibleItemsInfo.get(i2 - 1).getIndex() <= lastVisibleItemIndex)) {
                        LazyGridItemInfo found2 = visibleItemsInfo.get(i2);
                        found = found2;
                        break;
                    }
                }
                LazyGridItemInfo lastVisibleItem = (LazyGridItemInfo) CollectionsKt.last((List) lastApproachLayoutInfo.getVisibleItemsInfo());
                LazyGridMeasuredLine it = (LazyGridMeasuredLine) CollectionsKt.lastOrNull((List) list);
                int lineIndex = it != null ? it.getIndex() + 1 : 0;
                if (found != null && (i = found.getIndex()) <= (iMin = Math.min(lastVisibleItem.getIndex(), itemsCount - 1))) {
                    while (true) {
                        if (list2 != null) {
                            List $this$fastAny$iv = list2;
                            int index$iv$iv = 0;
                            int size = $this$fastAny$iv.size();
                            while (true) {
                                if (index$iv$iv >= size) {
                                    z3 = false;
                                    break;
                                }
                                Object item$iv$iv = $this$fastAny$iv.get(index$iv$iv);
                                LazyGridMeasuredItem[] items = ((LazyGridMeasuredLine) item$iv$iv).getItems();
                                int length = items.length;
                                int i3 = 0;
                                while (true) {
                                    if (i3 >= length) {
                                        z4 = false;
                                        break;
                                    }
                                    int i4 = i3;
                                    if (items[i3].getIndex() == i) {
                                        z4 = true;
                                        break;
                                    }
                                    i3 = i4 + 1;
                                }
                                if (z4) {
                                    z3 = true;
                                    break;
                                }
                                index$iv$iv++;
                            }
                            z = true;
                            z2 = z3;
                            if (z2) {
                                if (list2 == null) {
                                    list2 = new ArrayList();
                                }
                                LazyGridMeasuredLine measuredLine = measuredLineProvider.getAndMeasure(lineIndex);
                                lineIndex++;
                                list2.add(measuredLine);
                            }
                            if (i != iMin) {
                                break;
                            }
                            i++;
                            z5 = z;
                        } else {
                            z = z5;
                        }
                        if (z2) {
                            if (list2 == null) {
                                list2 = new ArrayList();
                            }
                            LazyGridMeasuredLine measuredLine2 = measuredLineProvider.getAndMeasure(lineIndex);
                            lineIndex++;
                            list2.add(measuredLine2);
                        }
                        if (i != iMin) {
                            break;
                            break;
                        }
                        i++;
                        z5 = z;
                    }
                }
            }
            return list2 == null ? CollectionsKt.emptyList() : list2;
        }

        private static final List<LazyGridMeasuredItem> calculateItemsOffsets(List<LazyGridMeasuredLine> list, List<LazyGridMeasuredItem> list2, List<LazyGridMeasuredItem> list3, int layoutWidth, int layoutHeight, int finalMainAxisOffset, int maxOffset, int firstLineScrollOffset, boolean isVertical, Arrangement.Vertical verticalArrangement, Arrangement.Horizontal horizontalArrangement, boolean reverseLayout, Density density) {
            List<LazyGridMeasuredLine> list4 = list;
            boolean z = reverseLayout;
            int mainAxisLayoutSize = isVertical ? layoutHeight : layoutWidth;
            boolean hasSpareSpace = finalMainAxisOffset < Math.min(mainAxisLayoutSize, maxOffset);
            if (hasSpareSpace) {
                boolean value$iv = firstLineScrollOffset == 0;
                if (!value$iv) {
                    InlineClassHelperKt.throwIllegalStateException("non-zero firstLineScrollOffset");
                }
            }
            int sum$iv = 0;
            int size = list.size();
            for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                Object item$iv$iv = list.get(index$iv$iv);
                sum$iv += ((LazyGridMeasuredLine) item$iv$iv).getItems().length;
            }
            ArrayList positionedItems = new ArrayList(sum$iv);
            if (hasSpareSpace) {
                boolean value$iv2 = list2.isEmpty() && list3.isEmpty();
                if (!value$iv2) {
                    InlineClassHelperKt.throwIllegalArgumentException("no items");
                }
                int linesCount = list4.size();
                int[] sizes = new int[linesCount];
                for (int i = 0; i < linesCount; i++) {
                    sizes[i] = list4.get(calculateItemsOffsets$reverseAware(i, z, linesCount)).getMainAxisSize();
                }
                int[] offsets = new int[linesCount];
                if (isVertical) {
                    if (verticalArrangement == null) {
                        InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null verticalArrangement");
                        throw new KotlinNothingValueException();
                    }
                    verticalArrangement.arrange(density, mainAxisLayoutSize, sizes, offsets);
                } else {
                    if (horizontalArrangement == null) {
                        InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null horizontalArrangement");
                        throw new KotlinNothingValueException();
                    }
                    horizontalArrangement.arrange(density, mainAxisLayoutSize, sizes, LayoutDirection.Ltr, offsets);
                }
                IntRange reverseAwareOffsetIndices = ArraysKt.getIndices(offsets);
                if (z) {
                    reverseAwareOffsetIndices = RangesKt.reversed(reverseAwareOffsetIndices);
                }
                int index = reverseAwareOffsetIndices.getFirst();
                int last = reverseAwareOffsetIndices.getLast();
                int step = reverseAwareOffsetIndices.getStep();
                if ((step > 0 && index <= last) || (step < 0 && last <= index)) {
                    while (true) {
                        int absoluteOffset = offsets[index];
                        LazyGridMeasuredLine line = list4.get(calculateItemsOffsets$reverseAware(index, z, linesCount));
                        int relativeOffset = z ? (mainAxisLayoutSize - absoluteOffset) - line.getMainAxisSize() : absoluteOffset;
                        IntProgression reverseAwareOffsetIndices2 = reverseAwareOffsetIndices;
                        addAllFromArray(positionedItems, line.position(relativeOffset, layoutWidth, layoutHeight));
                        if (index == last) {
                            break;
                        }
                        index += step;
                        list4 = list;
                        z = reverseLayout;
                        reverseAwareOffsetIndices = reverseAwareOffsetIndices2;
                    }
                }
            } else {
                int currentMainAxis = firstLineScrollOffset;
                int size2 = list2.size() - 1;
                if (size2 >= 0) {
                    do {
                        int index$iv = size2;
                        size2--;
                        Object item$iv = list2.get(index$iv);
                        LazyGridMeasuredItem it = (LazyGridMeasuredItem) item$iv;
                        currentMainAxis -= it.getMainAxisSizeWithSpacings();
                        it.position(currentMainAxis, 0, layoutWidth, layoutHeight);
                        positionedItems.add(it);
                    } while (size2 >= 0);
                }
                int currentMainAxis2 = firstLineScrollOffset;
                int size3 = list.size();
                for (int index$iv2 = 0; index$iv2 < size3; index$iv2++) {
                    Object item$iv2 = list.get(index$iv2);
                    LazyGridMeasuredLine it2 = (LazyGridMeasuredLine) item$iv2;
                    addAllFromArray(positionedItems, it2.position(currentMainAxis2, layoutWidth, layoutHeight));
                    currentMainAxis2 += it2.getMainAxisSizeWithSpacings();
                }
                int size4 = list3.size();
                for (int index$iv3 = 0; index$iv3 < size4; index$iv3++) {
                    Object item$iv3 = list3.get(index$iv3);
                    LazyGridMeasuredItem it3 = (LazyGridMeasuredItem) item$iv3;
                    it3.position(currentMainAxis2, 0, layoutWidth, layoutHeight);
                    positionedItems.add(it3);
                    currentMainAxis2 += it3.getMainAxisSizeWithSpacings();
                }
            }
            return positionedItems;
        }

        private static final int calculateItemsOffsets$reverseAware(int $this$calculateItemsOffsets_u24reverseAware, boolean $reverseLayout, int linesCount) {
            return !$reverseLayout ? $this$calculateItemsOffsets_u24reverseAware : (linesCount - $this$calculateItemsOffsets_u24reverseAware) - 1;
        }

        private static final <T> void addAllFromArray(List<T> list, T[] tArr) {
            for (T t : tArr) {
                list.add(t);
            }
        }
    }
