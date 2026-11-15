package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.CacheWindowLogic;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsStateKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.foundation.lazy.layout.StickyItemsPlacement;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyGrid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class LazyGridKt$rememberLazyGridMeasurePolicy$1$1 implements LazyLayoutMeasurePolicy {
    final /* synthetic */ PaddingValues $contentPadding;
    final /* synthetic */ CoroutineScope $coroutineScope;
    final /* synthetic */ GraphicsContext $graphicsContext;
    final /* synthetic */ Arrangement.Horizontal $horizontalArrangement;
    final /* synthetic */ boolean $isVertical;
    final /* synthetic */ Function0<LazyGridItemProvider> $itemProviderLambda;
    final /* synthetic */ boolean $reverseLayout;
    final /* synthetic */ LazyGridSlotsProvider $slots;
    final /* synthetic */ LazyGridState $state;
    final /* synthetic */ StickyItemsPlacement $stickyItemsScrollBehavior;
    final /* synthetic */ Arrangement.Vertical $verticalArrangement;

    /* JADX WARN: Multi-variable type inference failed */
    LazyGridKt$rememberLazyGridMeasurePolicy$1$1(LazyGridState lazyGridState, boolean z, PaddingValues paddingValues, boolean z2, Function0<? extends LazyGridItemProvider> function0, LazyGridSlotsProvider lazyGridSlotsProvider, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, CoroutineScope coroutineScope, GraphicsContext graphicsContext, StickyItemsPlacement stickyItemsPlacement) {
        this.$state = lazyGridState;
        this.$isVertical = z;
        this.$contentPadding = paddingValues;
        this.$reverseLayout = z2;
        this.$itemProviderLambda = function0;
        this.$slots = lazyGridSlotsProvider;
        this.$verticalArrangement = vertical;
        this.$horizontalArrangement = horizontal;
        this.$coroutineScope = coroutineScope;
        this.$graphicsContext = graphicsContext;
        this.$stickyItemsScrollBehavior = stickyItemsPlacement;
    }

    /* JADX WARN: Code duplicated, block: B:91:0x026b  */
    /* JADX WARN: Code duplicated, block: B:94:0x032b  */
    /* JADX WARN: Code duplicated, block: B:97:0x0333  */
    /* JADX WARN: Type inference failed for: r23v2, types: [androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1] */
    /* JADX WARN: Type inference failed for: r28v0, types: [androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1] */
    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
    /* JADX INFO: renamed from: measure-0kLqBqw */
    public final MeasureResult mo921measure0kLqBqw(final LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, final long j) throws Throwable {
        int i;
        int i2;
        int beforeContentPadding;
        float spacing;
        int iM7858getMaxWidthimpl;
        long visualItemOffset;
        int firstVisibleLineIndex;
        int afterContentPadding;
        float scrollToBeConsumed;
        LazyGridMeasureResult measureResult;
        CacheWindowLogic cacheWindowLogic;
        ObservableScopeInvalidator.m1006attachToScopeimpl(this.$state.m964getMeasurementScopeInvalidatorzYiylxw$foundation());
        boolean isInLookaheadScope = this.$state.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
        CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(j, this.$isVertical ? Orientation.Vertical : Orientation.Horizontal);
        if (this.$isVertical) {
            i = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(this.$contentPadding.mo769calculateLeftPaddingu2uoSUM($this$LazyLayoutMeasurePolicy.getLayoutDirection()));
        } else {
            i = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(PaddingKt.calculateStartPadding(this.$contentPadding, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
        }
        int startPadding = i;
        if (this.$isVertical) {
            i2 = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(this.$contentPadding.mo770calculateRightPaddingu2uoSUM($this$LazyLayoutMeasurePolicy.getLayoutDirection()));
        } else {
            i2 = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(PaddingKt.calculateEndPadding(this.$contentPadding, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
        }
        int endPadding = i2;
        int topPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(this.$contentPadding.getTop());
        int bottomPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(this.$contentPadding.getBottom());
        final int totalVerticalPadding = topPadding + bottomPadding;
        int val1$iv$iv = startPadding + endPadding;
        int totalMainAxisPadding = this.$isVertical ? totalVerticalPadding : val1$iv$iv;
        if (this.$isVertical && !this.$reverseLayout) {
            beforeContentPadding = topPadding;
        } else if (this.$isVertical && this.$reverseLayout) {
            beforeContentPadding = bottomPadding;
        } else {
            beforeContentPadding = (this.$isVertical || this.$reverseLayout) ? endPadding : startPadding;
        }
        final int afterContentPadding2 = totalMainAxisPadding - beforeContentPadding;
        long contentConstraints = ConstraintsKt.m7877offsetNN6EwU(j, -val1$iv$iv, -totalVerticalPadding);
        final LazyGridItemProvider itemProvider = this.$itemProviderLambda.invoke();
        final LazyGridSpanLayoutProvider spanLayoutProvider = itemProvider.getSpanLayoutProvider();
        final LazyGridSlots resolvedSlots = this.$slots.mo947invoke0kLqBqw($this$LazyLayoutMeasurePolicy, contentConstraints);
        int slotsPerLine = resolvedSlots.getSizes().length;
        spanLayoutProvider.setSlotsPerLine(slotsPerLine);
        if (this.$isVertical) {
            Arrangement.Vertical vertical = this.$verticalArrangement;
            if (vertical != null) {
                spacing = vertical.getSpacing();
            } else {
                InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null verticalArrangement when isVertical == true");
                throw new KotlinNothingValueException();
            }
        } else {
            Arrangement.Horizontal horizontal = this.$horizontalArrangement;
            if (horizontal != null) {
                spacing = horizontal.getSpacing();
            } else {
                InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null horizontalArrangement when isVertical == false");
                throw new KotlinNothingValueException();
            }
        }
        float spaceBetweenLinesDp = spacing;
        final int spaceBetweenLines = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(spaceBetweenLinesDp);
        final int itemsCount = itemProvider.getItemCount();
        if (this.$isVertical) {
            iM7858getMaxWidthimpl = Constraints.m7857getMaxHeightimpl(j) - totalVerticalPadding;
        } else {
            iM7858getMaxWidthimpl = Constraints.m7858getMaxWidthimpl(j) - val1$iv$iv;
        }
        int mainAxisAvailableSize = iM7858getMaxWidthimpl;
        if (this.$reverseLayout && mainAxisAvailableSize <= 0) {
            int x$iv = this.$isVertical ? startPadding : startPadding + mainAxisAvailableSize;
            int y$iv = this.$isVertical ? topPadding + mainAxisAvailableSize : topPadding;
            int val2$iv$iv = y$iv;
            val1$iv$iv = val1$iv$iv;
            visualItemOffset = IntOffset.m8027constructorimpl((((long) x$iv) << 32) | (((long) val2$iv$iv) & 4294967295L));
        } else {
            char c = ' ';
            visualItemOffset = IntOffset.m8027constructorimpl((((long) startPadding) << c) | (((long) topPadding) & 4294967295L));
        }
        final int beforeContentPadding2 = beforeContentPadding;
        final LazyGridState lazyGridState = this.$state;
        final boolean z = this.$isVertical;
        final long visualItemOffset2 = visualItemOffset;
        final boolean z2 = this.$reverseLayout;
        final int totalHorizontalPadding = val1$iv$iv;
        final ?? r28 = new LazyGridMeasuredItemProvider(itemProvider, $this$LazyLayoutMeasurePolicy, spaceBetweenLines, lazyGridState, z, z2, beforeContentPadding2, afterContentPadding2, visualItemOffset2) { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1
            final /* synthetic */ int $afterContentPadding;
            final /* synthetic */ int $beforeContentPadding;
            final /* synthetic */ boolean $isVertical;
            final /* synthetic */ boolean $reverseLayout;
            final /* synthetic */ LazyGridState $state;
            final /* synthetic */ LazyLayoutMeasureScope $this_LazyLayoutMeasurePolicy;
            final /* synthetic */ long $visualItemOffset;

            {
                this.$this_LazyLayoutMeasurePolicy = $this$LazyLayoutMeasurePolicy;
                this.$state = lazyGridState;
                this.$isVertical = z;
                this.$reverseLayout = z2;
                this.$beforeContentPadding = beforeContentPadding2;
                this.$afterContentPadding = afterContentPadding2;
                this.$visualItemOffset = visualItemOffset2;
            }

            @Override // androidx.compose.foundation.lazy.grid.LazyGridMeasuredItemProvider
            /* JADX INFO: renamed from: createItem-O3s9Psw, reason: not valid java name */
            public LazyGridMeasuredItem mo956createItemO3s9Psw(int index, Object key, Object contentType, int crossAxisSize, int mainAxisSpacing, List<? extends Placeable> placeables, long constraints, int lane, int span) {
                return new LazyGridMeasuredItem(index, key, this.$isVertical, crossAxisSize, mainAxisSpacing, this.$reverseLayout, this.$this_LazyLayoutMeasurePolicy.getLayoutDirection(), this.$beforeContentPadding, this.$afterContentPadding, placeables, this.$visualItemOffset, contentType, this.$state.getItemAnimator$foundation(), constraints, lane, span, null);
            }
        };
        final boolean z3 = this.$isVertical;
        final ?? r23 = new LazyGridMeasuredLineProvider(z3, resolvedSlots, itemsCount, spaceBetweenLines, r28, spanLayoutProvider) { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1
            final /* synthetic */ boolean $isVertical;
            final /* synthetic */ LazyGridSlots $resolvedSlots;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(z3, resolvedSlots, itemsCount, spaceBetweenLines, r28, spanLayoutProvider);
                this.$isVertical = z3;
                this.$resolvedSlots = resolvedSlots;
            }

            @Override // androidx.compose.foundation.lazy.grid.LazyGridMeasuredLineProvider
            public LazyGridMeasuredLine createLine(int index, LazyGridMeasuredItem[] items, List<GridItemSpan> spans, int mainAxisSpacing) {
                return new LazyGridMeasuredLine(index, items, this.$resolvedSlots, spans, this.$isVertical, mainAxisSpacing);
            }
        };
        Function1 prefetchInfoRetriever = new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LazyGridKt$rememberLazyGridMeasurePolicy$1$1.measure_0kLqBqw$lambda$2(spanLayoutProvider, r23, ((Integer) obj).intValue());
            }
        };
        Function1 lineIndexProvider = new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(spanLayoutProvider.getLineIndexOfItem(((Integer) obj).intValue()));
            }
        };
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        LazyGridState lazyGridState2 = this.$state;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            int firstVisibleLineIndex2 = lazyGridState2.getFirstVisibleItemIndex();
            int index = lazyGridState2.updateScrollPositionIfTheFirstItemWasMoved$foundation(itemProvider, firstVisibleLineIndex2);
            if (index < itemsCount || itemsCount <= 0) {
                firstVisibleLineIndex = spanLayoutProvider.getLineIndexOfItem(index);
                try {
                    int firstVisibleLineScrollOffset = lazyGridState2.getFirstVisibleItemScrollOffset();
                    afterContentPadding = firstVisibleLineScrollOffset;
                    try {
                        Unit unit = Unit.INSTANCE;
                        this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                        List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, this.$state.getPinnedItems(), this.$state.getBeyondBoundsInfo());
                        if (!$this$LazyLayoutMeasurePolicy.isLookingAhead() || !isInLookaheadScope) {
                            scrollToBeConsumed = this.$state.getScrollToBeConsumed();
                        } else {
                            scrollToBeConsumed = this.$state.getScrollDeltaBetweenPasses$foundation();
                        }
                        float scrollToBeConsumed2 = scrollToBeConsumed;
                        int beforeContentPadding3 = firstVisibleLineIndex;
                        measureResult = LazyGridMeasureKt.m959measureLazyGridt1x4au0(itemsCount, (LazyGridMeasuredLineProvider) r23, (LazyGridMeasuredItemProvider) r28, mainAxisAvailableSize, beforeContentPadding2, afterContentPadding2, spaceBetweenLines, beforeContentPadding3, afterContentPadding, scrollToBeConsumed2, contentConstraints, this.$isVertical, this.$verticalArrangement, this.$horizontalArrangement, this.$reverseLayout, $this$LazyLayoutMeasurePolicy, this.$state.getItemAnimator$foundation(), slotsPerLine, listCalculateLazyLayoutPinnedIndices, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), this.$state.getApproachLayoutInfo(), this.$coroutineScope, this.$state.m965getPlacementScopeInvalidatorzYiylxw$foundation(), this.$graphicsContext, prefetchInfoRetriever, lineIndexProvider, this.$stickyItemsScrollBehavior, new Function3() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(Object obj, Object obj2, Object obj3) {
                                LazyLayoutMeasureScope lazyLayoutMeasureScope = $this$LazyLayoutMeasurePolicy;
                                long j2 = j;
                                return lazyLayoutMeasureScope.layout(ConstraintsKt.m7875constrainWidthK40F9xA(j2, ((Integer) obj).intValue() + totalHorizontalPadding), ConstraintsKt.m7874constrainHeightK40F9xA(j2, ((Integer) obj2).intValue() + totalVerticalPadding), MapsKt.emptyMap(), (Function1) obj3);
                            }
                        });
                        LazyGridState.applyMeasureResult$foundation$default(this.$state, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                        Object prefetchStrategy = this.$state.getPrefetchStrategy();
                        cacheWindowLogic = prefetchStrategy instanceof CacheWindowLogic ? (CacheWindowLogic) prefetchStrategy : null;
                        if (cacheWindowLogic != null) {
                            LazyGridKt.keepAroundItems(cacheWindowLogic, measureResult.getOrientation(), measureResult.getVisibleItemsInfo(), (LazyGridMeasuredLineProvider) r23);
                        }
                        return measureResult;
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                try {
                    int firstVisibleLineIndex3 = spanLayoutProvider.getLineIndexOfItem(itemsCount - 1);
                    firstVisibleLineIndex = firstVisibleLineIndex3;
                    afterContentPadding = 0;
                    Unit unit2 = Unit.INSTANCE;
                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                    List<Integer> listCalculateLazyLayoutPinnedIndices2 = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, this.$state.getPinnedItems(), this.$state.getBeyondBoundsInfo());
                    if (!$this$LazyLayoutMeasurePolicy.isLookingAhead()) {
                        scrollToBeConsumed = this.$state.getScrollToBeConsumed();
                    } else {
                        scrollToBeConsumed = this.$state.getScrollToBeConsumed();
                    }
                    float scrollToBeConsumed3 = scrollToBeConsumed;
                    int beforeContentPadding4 = firstVisibleLineIndex;
                    measureResult = LazyGridMeasureKt.m959measureLazyGridt1x4au0(itemsCount, (LazyGridMeasuredLineProvider) r23, (LazyGridMeasuredItemProvider) r28, mainAxisAvailableSize, beforeContentPadding2, afterContentPadding2, spaceBetweenLines, beforeContentPadding4, afterContentPadding, scrollToBeConsumed3, contentConstraints, this.$isVertical, this.$verticalArrangement, this.$horizontalArrangement, this.$reverseLayout, $this$LazyLayoutMeasurePolicy, this.$state.getItemAnimator$foundation(), slotsPerLine, listCalculateLazyLayoutPinnedIndices2, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), this.$state.getApproachLayoutInfo(), this.$coroutineScope, this.$state.m965getPlacementScopeInvalidatorzYiylxw$foundation(), this.$graphicsContext, prefetchInfoRetriever, lineIndexProvider, this.$stickyItemsScrollBehavior, new Function3() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            LazyLayoutMeasureScope lazyLayoutMeasureScope = $this$LazyLayoutMeasurePolicy;
                            long j2 = j;
                            return lazyLayoutMeasureScope.layout(ConstraintsKt.m7875constrainWidthK40F9xA(j2, ((Integer) obj).intValue() + totalHorizontalPadding), ConstraintsKt.m7874constrainHeightK40F9xA(j2, ((Integer) obj2).intValue() + totalVerticalPadding), MapsKt.emptyMap(), (Function1) obj3);
                        }
                    });
                    LazyGridState.applyMeasureResult$foundation$default(this.$state, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                    Object prefetchStrategy2 = this.$state.getPrefetchStrategy();
                    cacheWindowLogic = prefetchStrategy2 instanceof CacheWindowLogic ? (CacheWindowLogic) prefetchStrategy2 : null;
                    if (cacheWindowLogic != null) {
                        LazyGridKt.keepAroundItems(cacheWindowLogic, measureResult.getOrientation(), measureResult.getVisibleItemsInfo(), (LazyGridMeasuredLineProvider) r23);
                    }
                    return measureResult;
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        } catch (Throwable th4) {
            th = th4;
        }
        this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
        throw th;
    }

    static final ArrayList measure_0kLqBqw$lambda$2(LazyGridSpanLayoutProvider $spanLayoutProvider, LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1 $measuredLineProvider, int line) {
        LazyGridSpanLayoutProvider.LineConfiguration lineConfiguration = $spanLayoutProvider.getLineConfiguration(line);
        int index = lineConfiguration.getFirstItemIndex();
        int slot = 0;
        ArrayList result = new ArrayList(lineConfiguration.getSpans().size());
        List<GridItemSpan> spans = lineConfiguration.getSpans();
        int size = spans.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = spans.get(index$iv);
            long it = ((GridItemSpan) item$iv).getPackedValue();
            int span = GridItemSpan.m943getCurrentLineSpanimpl(it);
            result.add(TuplesKt.to(Integer.valueOf(index), Constraints.m7845boximpl($measuredLineProvider.m963childConstraintsJhjzzOo$foundation(slot, span))));
            index++;
            slot += span;
        }
        return result;
    }
}
