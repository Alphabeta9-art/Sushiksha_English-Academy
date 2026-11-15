package androidx.compose.foundation.lazy;

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
import androidx.compose.ui.Alignment;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyList.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class LazyListKt$rememberLazyListMeasurePolicy$1$1 implements LazyLayoutMeasurePolicy {
    final /* synthetic */ int $beyondBoundsItemCount;
    final /* synthetic */ PaddingValues $contentPadding;
    final /* synthetic */ CoroutineScope $coroutineScope;
    final /* synthetic */ GraphicsContext $graphicsContext;
    final /* synthetic */ Alignment.Horizontal $horizontalAlignment;
    final /* synthetic */ Arrangement.Horizontal $horizontalArrangement;
    final /* synthetic */ boolean $isVertical;
    final /* synthetic */ Function0<LazyListItemProvider> $itemProviderLambda;
    final /* synthetic */ boolean $reverseLayout;
    final /* synthetic */ LazyListState $state;
    final /* synthetic */ StickyItemsPlacement $stickyItemsPlacement;
    final /* synthetic */ Alignment.Vertical $verticalAlignment;
    final /* synthetic */ Arrangement.Vertical $verticalArrangement;

    /* JADX WARN: Multi-variable type inference failed */
    LazyListKt$rememberLazyListMeasurePolicy$1$1(LazyListState lazyListState, boolean z, PaddingValues paddingValues, boolean z2, Function0<? extends LazyListItemProvider> function0, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, int i, CoroutineScope coroutineScope, GraphicsContext graphicsContext, StickyItemsPlacement stickyItemsPlacement, Alignment.Horizontal horizontal2, Alignment.Vertical vertical2) {
        this.$state = lazyListState;
        this.$isVertical = z;
        this.$contentPadding = paddingValues;
        this.$reverseLayout = z2;
        this.$itemProviderLambda = function0;
        this.$verticalArrangement = vertical;
        this.$horizontalArrangement = horizontal;
        this.$beyondBoundsItemCount = i;
        this.$coroutineScope = coroutineScope;
        this.$graphicsContext = graphicsContext;
        this.$stickyItemsPlacement = stickyItemsPlacement;
        this.$horizontalAlignment = horizontal2;
        this.$verticalAlignment = vertical2;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
    /* JADX INFO: renamed from: measure-0kLqBqw, reason: not valid java name */
    public final MeasureResult mo921measure0kLqBqw(final LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, final long j) throws Throwable {
        int i;
        int i2;
        final int beforeContentPadding;
        float spacing;
        int iM7858getMaxWidthimpl;
        final long visualItemOffset;
        float scrollToBeConsumed;
        ObservableScopeInvalidator.m1006attachToScopeimpl(this.$state.m937getMeasurementScopeInvalidatorzYiylxw$foundation());
        boolean hasLookaheadOccurred = this.$state.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
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
        int val1$iv$iv = topPadding + bottomPadding;
        final int totalHorizontalPadding = startPadding + endPadding;
        int totalMainAxisPadding = this.$isVertical ? val1$iv$iv : totalHorizontalPadding;
        if (this.$isVertical && !this.$reverseLayout) {
            beforeContentPadding = topPadding;
        } else if (this.$isVertical && this.$reverseLayout) {
            beforeContentPadding = bottomPadding;
        } else {
            beforeContentPadding = (this.$isVertical || this.$reverseLayout) ? endPadding : startPadding;
        }
        final int afterContentPadding = totalMainAxisPadding - beforeContentPadding;
        final long contentConstraints = ConstraintsKt.m7877offsetNN6EwU(j, -totalHorizontalPadding, -val1$iv$iv);
        final LazyListItemProvider itemProvider = this.$itemProviderLambda.invoke();
        itemProvider.getItemScope().setMaxSize(Constraints.m7858getMaxWidthimpl(contentConstraints), Constraints.m7857getMaxHeightimpl(contentConstraints));
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
                InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null horizontalAlignment when isVertical == false");
                throw new KotlinNothingValueException();
            }
        }
        float spaceBetweenItemsDp = spacing;
        final int spaceBetweenItems = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(spaceBetweenItemsDp);
        final int itemsCount = itemProvider.getItemCount();
        if (this.$isVertical) {
            iM7858getMaxWidthimpl = Constraints.m7857getMaxHeightimpl(j) - val1$iv$iv;
        } else {
            iM7858getMaxWidthimpl = Constraints.m7858getMaxWidthimpl(j) - totalHorizontalPadding;
        }
        int mainAxisAvailableSize = iM7858getMaxWidthimpl;
        if (this.$reverseLayout && mainAxisAvailableSize <= 0) {
            int x$iv = this.$isVertical ? startPadding : startPadding + mainAxisAvailableSize;
            int y$iv = this.$isVertical ? topPadding + mainAxisAvailableSize : topPadding;
            int val2$iv$iv = y$iv;
            val1$iv$iv = val1$iv$iv;
            visualItemOffset = IntOffset.m8027constructorimpl((((long) x$iv) << 32) | (((long) val2$iv$iv) & 4294967295L));
        } else {
            long j2 = 4294967295L;
            visualItemOffset = IntOffset.m8027constructorimpl((((long) startPadding) << 32) | (((long) topPadding) & j2));
        }
        final boolean z = this.$isVertical;
        final Alignment.Horizontal horizontal2 = this.$horizontalAlignment;
        final Alignment.Vertical vertical2 = this.$verticalAlignment;
        final boolean z2 = this.$reverseLayout;
        final LazyListState lazyListState = this.$state;
        LazyListMeasuredItemProvider lazyListMeasuredItemProvider = new LazyListMeasuredItemProvider(contentConstraints, z, itemProvider, $this$LazyLayoutMeasurePolicy, itemsCount, spaceBetweenItems, horizontal2, vertical2, z2, beforeContentPadding, afterContentPadding, visualItemOffset, lazyListState) { // from class: androidx.compose.foundation.lazy.LazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1
            final /* synthetic */ int $afterContentPadding;
            final /* synthetic */ int $beforeContentPadding;
            final /* synthetic */ Alignment.Horizontal $horizontalAlignment;
            final /* synthetic */ boolean $isVertical;
            final /* synthetic */ int $itemsCount;
            final /* synthetic */ boolean $reverseLayout;
            final /* synthetic */ int $spaceBetweenItems;
            final /* synthetic */ LazyListState $state;
            final /* synthetic */ LazyLayoutMeasureScope $this_LazyLayoutMeasurePolicy;
            final /* synthetic */ Alignment.Vertical $verticalAlignment;
            final /* synthetic */ long $visualItemOffset;

            {
                this.$isVertical = z;
                this.$this_LazyLayoutMeasurePolicy = $this$LazyLayoutMeasurePolicy;
                this.$itemsCount = itemsCount;
                this.$spaceBetweenItems = spaceBetweenItems;
                this.$horizontalAlignment = horizontal2;
                this.$verticalAlignment = vertical2;
                this.$reverseLayout = z2;
                this.$beforeContentPadding = beforeContentPadding;
                this.$afterContentPadding = afterContentPadding;
                this.$visualItemOffset = visualItemOffset;
                this.$state = lazyListState;
            }

            @Override // androidx.compose.foundation.lazy.LazyListMeasuredItemProvider
            /* JADX INFO: renamed from: createItem-X9ElhV4, reason: not valid java name */
            public LazyListMeasuredItem mo922createItemX9ElhV4(int index, Object key, Object contentType, List<? extends Placeable> placeables, long constraints) {
                int spacing2 = index == this.$itemsCount + (-1) ? 0 : this.$spaceBetweenItems;
                return new LazyListMeasuredItem(index, placeables, this.$isVertical, this.$horizontalAlignment, this.$verticalAlignment, this.$this_LazyLayoutMeasurePolicy.getLayoutDirection(), this.$reverseLayout, this.$beforeContentPadding, this.$afterContentPadding, spacing2, this.$visualItemOffset, key, contentType, this.$state.getItemAnimator$foundation(), constraints, null);
            }
        };
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        LazyListState lazyListState2 = this.$state;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            int firstVisibleItemIndex = lazyListState2.getFirstVisibleItemIndex();
            int firstVisibleItemIndex2 = lazyListState2.updateScrollPositionIfTheFirstItemWasMoved$foundation(itemProvider, firstVisibleItemIndex);
            try {
                int firstVisibleScrollOffset = lazyListState2.getFirstVisibleItemScrollOffset();
                try {
                    Unit unit = Unit.INSTANCE;
                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                    List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, this.$state.getPinnedItems(), this.$state.getBeyondBoundsInfo());
                    if ($this$LazyLayoutMeasurePolicy.isLookingAhead() || !hasLookaheadOccurred) {
                        scrollToBeConsumed = this.$state.getScrollToBeConsumed();
                    } else {
                        scrollToBeConsumed = this.$state.getScrollDeltaBetweenPasses$foundation();
                    }
                    float scrollToBeConsumed2 = scrollToBeConsumed;
                    final int totalVerticalPadding = val1$iv$iv;
                    LazyListMeasureResult measureResult = LazyListMeasureKt.m927measureLazyList_s_dbAc(itemsCount, lazyListMeasuredItemProvider, mainAxisAvailableSize, beforeContentPadding, afterContentPadding, spaceBetweenItems, firstVisibleItemIndex2, firstVisibleScrollOffset, scrollToBeConsumed2, contentConstraints, this.$isVertical, this.$verticalArrangement, this.$horizontalArrangement, this.$reverseLayout, $this$LazyLayoutMeasurePolicy, this.$state.getItemAnimator$foundation(), this.$beyondBoundsItemCount, listCalculateLazyLayoutPinnedIndices, hasLookaheadOccurred, $this$LazyLayoutMeasurePolicy.isLookingAhead(), this.$coroutineScope, this.$state.m938getPlacementScopeInvalidatorzYiylxw$foundation(), this.$graphicsContext, this.$stickyItemsPlacement, new Function3() { // from class: androidx.compose.foundation.lazy.LazyListKt$rememberLazyListMeasurePolicy$1$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            LazyLayoutMeasureScope lazyLayoutMeasureScope = $this$LazyLayoutMeasurePolicy;
                            long j3 = j;
                            return lazyLayoutMeasureScope.layout(ConstraintsKt.m7875constrainWidthK40F9xA(j3, ((Integer) obj).intValue() + totalHorizontalPadding), ConstraintsKt.m7874constrainHeightK40F9xA(j3, ((Integer) obj2).intValue() + totalVerticalPadding), MapsKt.emptyMap(), (Function1) obj3);
                        }
                    });
                    LazyListState.applyMeasureResult$foundation$default(this.$state, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                    Object prefetchStrategy = this.$state.getPrefetchStrategy();
                    CacheWindowLogic cacheWindowLogic = prefetchStrategy instanceof CacheWindowLogic ? (CacheWindowLogic) prefetchStrategy : null;
                    if (cacheWindowLogic != null) {
                        LazyListKt.keepAroundItems(cacheWindowLogic, measureResult.getVisibleItemsInfo(), lazyListMeasuredItemProvider);
                    }
                    return measureResult;
                } catch (Throwable th) {
                    th = th;
                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
