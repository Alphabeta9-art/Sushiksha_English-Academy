package androidx.compose.foundation.pager;

import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsStateKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PagerMeasurePolicy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 implements LazyLayoutMeasurePolicy {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-unit-Dp$-pageSpacing$0, reason: not valid java name */
    final /* synthetic */ float f53$$v$c$androidxcomposeuiunitDp$pageSpacing$0;
    final /* synthetic */ int $beyondViewportPageCount;
    final /* synthetic */ PaddingValues $contentPadding;
    final /* synthetic */ CoroutineScope $coroutineScope;
    final /* synthetic */ Alignment.Horizontal $horizontalAlignment;
    final /* synthetic */ Function0<PagerLazyLayoutItemProvider> $itemProviderLambda;
    final /* synthetic */ Orientation $orientation;
    final /* synthetic */ Function0<Integer> $pageCount;
    final /* synthetic */ PageSize $pageSize;
    final /* synthetic */ boolean $reverseLayout;
    final /* synthetic */ SnapPosition $snapPosition;
    final /* synthetic */ PagerState $state;
    final /* synthetic */ Alignment.Vertical $verticalAlignment;

    PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(PagerState pagerState, Orientation orientation, PaddingValues paddingValues, boolean z, float f, PageSize pageSize, Function0<PagerLazyLayoutItemProvider> function0, Function0<Integer> function1, Alignment.Vertical vertical, Alignment.Horizontal horizontal, int i, SnapPosition snapPosition, CoroutineScope coroutineScope) {
        this.$state = pagerState;
        this.$orientation = orientation;
        this.$contentPadding = paddingValues;
        this.$reverseLayout = z;
        this.f53$$v$c$androidxcomposeuiunitDp$pageSpacing$0 = f;
        this.$pageSize = pageSize;
        this.$itemProviderLambda = function0;
        this.$pageCount = function1;
        this.$verticalAlignment = vertical;
        this.$horizontalAlignment = horizontal;
        this.$beyondViewportPageCount = i;
        this.$snapPosition = snapPosition;
        this.$coroutineScope = coroutineScope;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
    /* JADX INFO: renamed from: measure-0kLqBqw */
    public final MeasureResult mo921measure0kLqBqw(final LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, final long containerConstraints) throws Throwable {
        int i;
        int i2;
        int i3;
        int iM7858getMaxWidthimpl;
        long jM8027constructorimpl;
        int iM7858getMaxWidthimpl2;
        int iM7857getMaxHeightimpl;
        Snapshot previousSnapshot$iv;
        Snapshot newSnapshot$iv;
        Function1<Object, Unit> function1;
        ObservableScopeInvalidator.m1006attachToScopeimpl(this.$state.m1082getMeasurementScopeInvalidatorzYiylxw$foundation());
        boolean isVertical = this.$orientation == Orientation.Vertical;
        CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(containerConstraints, isVertical ? Orientation.Vertical : Orientation.Horizontal);
        if (isVertical) {
            i = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(this.$contentPadding.mo769calculateLeftPaddingu2uoSUM($this$LazyLayoutMeasurePolicy.getLayoutDirection()));
        } else {
            i = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(PaddingKt.calculateStartPadding(this.$contentPadding, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
        }
        int startPadding = i;
        if (isVertical) {
            i2 = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(this.$contentPadding.mo770calculateRightPaddingu2uoSUM($this$LazyLayoutMeasurePolicy.getLayoutDirection()));
        } else {
            i2 = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(PaddingKt.calculateEndPadding(this.$contentPadding, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
        }
        int endPadding = i2;
        int topPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(this.$contentPadding.getTop());
        int bottomPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(this.$contentPadding.getBottom());
        int totalVerticalPadding = topPadding + bottomPadding;
        final int totalHorizontalPadding = startPadding + endPadding;
        int totalMainAxisPadding = isVertical ? totalVerticalPadding : totalHorizontalPadding;
        if (isVertical && !this.$reverseLayout) {
            i3 = topPadding;
        } else if (isVertical && this.$reverseLayout) {
            i3 = bottomPadding;
        } else {
            i3 = (isVertical || this.$reverseLayout) ? endPadding : startPadding;
        }
        int beforeContentPadding = i3;
        int afterContentPadding = totalMainAxisPadding - beforeContentPadding;
        long contentConstraints = ConstraintsKt.m7877offsetNN6EwU(containerConstraints, -totalHorizontalPadding, -totalVerticalPadding);
        this.$state.setDensity$foundation($this$LazyLayoutMeasurePolicy);
        int spaceBetweenPages = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(this.f53$$v$c$androidxcomposeuiunitDp$pageSpacing$0);
        if (isVertical) {
            iM7858getMaxWidthimpl = Constraints.m7857getMaxHeightimpl(containerConstraints) - totalVerticalPadding;
        } else {
            iM7858getMaxWidthimpl = Constraints.m7858getMaxWidthimpl(containerConstraints) - totalHorizontalPadding;
        }
        int mainAxisAvailableSize = iM7858getMaxWidthimpl;
        if (this.$reverseLayout && mainAxisAvailableSize <= 0) {
            int x$iv = isVertical ? startPadding : startPadding + mainAxisAvailableSize;
            int y$iv = isVertical ? topPadding + mainAxisAvailableSize : topPadding;
            int val2$iv$iv = y$iv;
            int val1$iv$iv = x$iv;
            totalVerticalPadding = totalVerticalPadding;
            jM8027constructorimpl = IntOffset.m8027constructorimpl((((long) val1$iv$iv) << 32) | (((long) val2$iv$iv) & 4294967295L));
        } else {
            char c = ' ';
            long j = 4294967295L;
            jM8027constructorimpl = IntOffset.m8027constructorimpl((((long) startPadding) << c) | (((long) topPadding) & j));
        }
        long visualItemOffset = jM8027constructorimpl;
        PageSize $this$measure_0kLqBqw_u24lambda_u240 = this.$pageSize;
        int pageAvailableSize = RangesKt.coerceAtLeast($this$measure_0kLqBqw_u24lambda_u240.calculateMainAxisPageSize($this$LazyLayoutMeasurePolicy, mainAxisAvailableSize, spaceBetweenPages), 0);
        PagerState pagerState = this.$state;
        if (this.$orientation == Orientation.Vertical) {
            iM7858getMaxWidthimpl2 = Constraints.m7858getMaxWidthimpl(contentConstraints);
        } else {
            iM7858getMaxWidthimpl2 = pageAvailableSize;
        }
        if (this.$orientation != Orientation.Vertical) {
            iM7857getMaxHeightimpl = Constraints.m7857getMaxHeightimpl(contentConstraints);
        } else {
            iM7857getMaxHeightimpl = pageAvailableSize;
        }
        pagerState.m1086setPremeasureConstraintsBRTryo0$foundation(ConstraintsKt.Constraints$default(0, iM7858getMaxWidthimpl2, 0, iM7857getMaxHeightimpl, 5, null));
        PagerLazyLayoutItemProvider itemProvider = this.$itemProviderLambda.invoke();
        int layoutSize = mainAxisAvailableSize + beforeContentPadding + afterContentPadding;
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        PagerState pagerState2 = this.$state;
        SnapPosition snapPosition = this.$snapPosition;
        Snapshot previousSnapshot$iv2 = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv2 != null ? previousSnapshot$iv2.getReadObserver() : null;
        Snapshot newSnapshot$iv2 = this_$iv.makeCurrentNonObservable(previousSnapshot$iv2);
        try {
            int currentPage = pagerState2.getCurrentPage();
            int currentPage2 = pagerState2.matchScrollPositionWithKey$foundation(itemProvider, currentPage);
            Function1<Object, Unit> function2 = readObserver;
            try {
                try {
                    newSnapshot$iv = newSnapshot$iv2;
                    function1 = function2;
                    previousSnapshot$iv = previousSnapshot$iv2;
                    try {
                        int currentPageOffset = PagerKt.currentPageOffset(snapPosition, layoutSize, pageAvailableSize, spaceBetweenPages, beforeContentPadding, afterContentPadding, pagerState2.getCurrentPage(), pagerState2.getCurrentPageOffsetFraction(), pagerState2.getPageCount());
                        try {
                            Unit unit = Unit.INSTANCE;
                            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, function1);
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, this.$state.getPinnedPages(), this.$state.getBeyondBoundsInfo());
                            MutableIntObjectMap placeablesCache = IntObjectMapKt.mutableIntObjectMapOf();
                            int currentPage3 = this.$pageCount.invoke().intValue();
                            final int totalVerticalPadding2 = totalVerticalPadding;
                            PagerMeasureResult measureResult = PagerMeasureKt.m1079measurePager7L1iB3k($this$LazyLayoutMeasurePolicy, currentPage3, itemProvider, mainAxisAvailableSize, beforeContentPadding, afterContentPadding, spaceBetweenPages, currentPage2, currentPageOffset, contentConstraints, this.$orientation, this.$verticalAlignment, this.$horizontalAlignment, this.$reverseLayout, visualItemOffset, pageAvailableSize, this.$beyondViewportPageCount, listCalculateLazyLayoutPinnedIndices, this.$snapPosition, this.$state.m1083getPlacementScopeInvalidatorzYiylxw$foundation(), this.$coroutineScope, $this$LazyLayoutMeasurePolicy, new Function3() { // from class: androidx.compose.foundation.pager.PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function3
                                public final Object invoke(Object obj, Object obj2, Object obj3) {
                                    LazyLayoutMeasureScope lazyLayoutMeasureScope = $this$LazyLayoutMeasurePolicy;
                                    long j2 = containerConstraints;
                                    return lazyLayoutMeasureScope.layout(ConstraintsKt.m7875constrainWidthK40F9xA(j2, ((Integer) obj).intValue() + totalHorizontalPadding), ConstraintsKt.m7874constrainHeightK40F9xA(j2, ((Integer) obj2).intValue() + totalVerticalPadding2), MapsKt.emptyMap(), (Function1) obj3);
                                }
                            }, placeablesCache);
                            PagerState.applyMeasureResult$foundation$default(this.$state, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            PagerMeasurePolicyKt.keepAroundItems($this$LazyLayoutMeasurePolicy, this.$state.getCacheWindowLogic(), measureResult.getVisiblePagesInfo());
                            return measureResult;
                        } catch (Throwable th) {
                            th = th;
                            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, function1);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    newSnapshot$iv = newSnapshot$iv2;
                    function1 = function2;
                    previousSnapshot$iv = previousSnapshot$iv2;
                }
            } catch (Throwable th4) {
                th = th4;
                newSnapshot$iv = newSnapshot$iv2;
                function1 = function2;
                previousSnapshot$iv = previousSnapshot$iv2;
            }
        } catch (Throwable th5) {
            th = th5;
            previousSnapshot$iv = previousSnapshot$iv2;
            newSnapshot$iv = newSnapshot$iv2;
            function1 = readObserver;
        }
    }
}
