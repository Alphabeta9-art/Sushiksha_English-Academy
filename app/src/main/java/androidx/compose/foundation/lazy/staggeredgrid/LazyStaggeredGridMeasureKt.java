package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntSize;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyStaggeredGridMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¬\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0016\u001a5\u0010\u0002\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u00030\u0007¢\u0006\u0002\b\bH\u0082\b¢\u0006\u0002\u0010\t\u001a\u001d\u0010\n\u001a\u00020\u000b*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fH\u0002¢\u0006\u0002\u0010\u000f\u001a\u0017\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013H\u0082\b\u001a£\u0001\u0010\u0014\u001a\u00020\u0015*\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00012\u0006\u0010,\u001a\u00020\u00012\b\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u000200H\u0000¢\u0006\u0004\b1\u00102\u001a,\u00103\u001a\u00020\u0015*\u0002042\u0006\u00105\u001a\u00020\u001a2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020\u0001H\u0002\u001aK\u0010:\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0019*\u0002042\u0012\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u0010<\u001a\u0002072\u0006\u0010=\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020\u001a2\u0006\u0010?\u001a\u00020\u001aH\u0002¢\u0006\u0002\u0010@\u001aG\u0010A\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0019*\u0002042\u0006\u0010B\u001a\u00020\u001a2\u0006\u0010C\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020\u00012\u0018\u0010D\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00110EH\u0082\b\u001aR\u0010F\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0019*\u0002042\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00110\u00072!\u0010G\u001a\u001d\u0012\u0013\u0012\u00110\u001a¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(J\u0012\u0004\u0012\u00020\u00010\u00072\u0006\u0010K\u001a\u00020\u0001H\u0082\b\u001a7\u0010L\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00192\b\b\u0002\u0010M\u001a\u00020\u00012\u0012\u0010N\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u00110\u0007H\u0082\b\u001a(\u0010O\u001a\u00020\u0011*\u00020P2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00110\u0007H\u0082\b¢\u0006\u0004\bQ\u0010R\u001a\u0014\u0010S\u001a\u00020\u0011*\u0002072\u0006\u0010T\u001a\u00020\u001aH\u0002\u001a\u001b\u0010U\u001a\u00020\u001a*\u0002072\u0006\u0010V\u001a\u00020PH\u0002¢\u0006\u0004\bW\u0010X\u001a\u0016\u0010Y\u001a\u00020\u001a*\u0002072\b\b\u0002\u0010Z\u001a\u00020\u001aH\u0000\u001a2\u0010[\u001a\u00020\u001a\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\f2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u001a0\u0007H\u0082\b¢\u0006\u0002\u0010\\\u001a\f\u0010]\u001a\u00020\u001a*\u000207H\u0002\u001a!\u0010^\u001a\u000207*\u0002072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0\u0007H\u0082\b\u001a\u001c\u0010_\u001a\u00020\u0011*\u0002042\u0006\u0010`\u001a\u0002072\u0006\u0010a\u001a\u00020\u001aH\u0002\u001a\u001c\u0010b\u001a\u00020\u001a*\u0002042\u0006\u0010c\u001a\u00020\u001a2\u0006\u0010d\u001a\u00020\u001aH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010e\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000¨\u0006f"}, d2 = {"DebugLoggingEnabled", "", "withDebugLogging", ExifInterface.GPS_DIRECTION_TRUE, "scope", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "debugRender", "", "", "Lkotlin/collections/ArrayDeque;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "([Lkotlin/collections/ArrayDeque;)Ljava/lang/String;", "debugLog", "", "message", "Lkotlin/Function0;", "measureStaggeredGrid", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "pinnedItems", "", "", "itemProvider", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;", "resolvedSlots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "isVertical", "reverseLayout", "contentOffset", "Landroidx/compose/ui/unit/IntOffset;", "mainAxisAvailableSize", "mainAxisSpacing", "beforeContentPadding", "afterContentPadding", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "isInLookaheadScope", "isLookingAhead", "approachLayoutInfo", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLayoutInfo;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "measureStaggeredGrid-C6celF4", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Ljava/util/List;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;JZZJIIIILkotlinx/coroutines/CoroutineScope;ZZLandroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLayoutInfo;Landroidx/compose/ui/graphics/GraphicsContext;)Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "measure", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;", "initialScrollDelta", "initialItemIndices", "", "initialItemOffsets", "canRestartMeasure", "calculateVisibleItems", "measuredItems", "itemScrollOffsets", "mainAxisLayoutSize", "minOffset", "maxOffset", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;[Lkotlin/collections/ArrayDeque;[IIII)Ljava/util/List;", "itemsRetainedForLookahead", "lastVisibleItemIndex", "itemsCount", "position", "Lkotlin/Function2;", "calculateExtraItems", "filter", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "itemIndex", "beforeVisibleBounds", "fastForEach", "reverse", "action", "forEach", "Landroidx/compose/foundation/lazy/staggeredgrid/SpanRange;", "forEach-nIS5qE8", "(JLkotlin/jvm/functions/Function1;)V", "offsetBy", "delta", "maxInRange", "indexRange", "maxInRange-jy6DScQ", "([IJ)I", "indexOfMinValue", "minBound", "indexOfMinBy", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)I", "indexOfMaxValue", "transform", "ensureIndicesInRange", "indices", "itemCount", "findPreviousItemIndex", "item", "lane", "Unset", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyStaggeredGridMeasureKt {
    private static final boolean DebugLoggingEnabled = false;
    private static final int Unset = Integer.MIN_VALUE;

    private static final <T> T withDebugLogging(LazyLayoutMeasureScope scope, Function1<? super LazyLayoutMeasureScope, ? extends T> function1) {
        return function1.invoke(scope);
    }

    private static final String debugRender(ArrayDeque<LazyStaggeredGridMeasuredItem>[] arrayDequeArr) {
        return "";
    }

    private static final void debugLog(Function0<String> function0) {
    }

    /* JADX INFO: renamed from: measureStaggeredGrid-C6celF4, reason: not valid java name */
    public static final LazyStaggeredGridMeasureResult m1045measureStaggeredGridC6celF4(LazyLayoutMeasureScope $this$measureStaggeredGrid_u2dC6celF4, LazyStaggeredGridState state, List<Integer> list, LazyStaggeredGridItemProvider itemProvider, LazyStaggeredGridSlots resolvedSlots, long j, boolean isVertical, boolean reverseLayout, long j2, int mainAxisAvailableSize, int mainAxisSpacing, int beforeContentPadding, int afterContentPadding, CoroutineScope coroutineScope, boolean isInLookaheadScope, boolean isLookingAhead, LazyStaggeredGridLayoutInfo approachLayoutInfo, GraphicsContext graphicsContext) {
        int[] initialItemIndices;
        int iM1044maxInRangejy6DScQ;
        int[] initialItemOffsets;
        int i;
        LazyStaggeredGridMeasureContext context = new LazyStaggeredGridMeasureContext(state, list, itemProvider, resolvedSlots, j, isVertical, $this$measureStaggeredGrid_u2dC6celF4, mainAxisAvailableSize, j2, beforeContentPadding, afterContentPadding, reverseLayout, mainAxisSpacing, coroutineScope, isInLookaheadScope, isLookingAhead, approachLayoutInfo, graphicsContext, null);
        int[] firstVisibleIndices = state.updateScrollPositionIfTheFirstItemWasMoved$foundation(itemProvider, state.getScrollPosition().getIndices());
        int[] firstVisibleOffsets = state.getScrollPosition().getScrollOffsets();
        if (firstVisibleIndices.length == context.getLaneCount()) {
            initialItemIndices = firstVisibleIndices;
        } else {
            context.getLaneInfo().reset();
            initialItemIndices = new int[context.getLaneCount()];
            int length = initialItemIndices.length;
            for (int lane = 0; lane < length; lane++) {
                if (lane < firstVisibleIndices.length && firstVisibleIndices[lane] != -1) {
                    iM1044maxInRangejy6DScQ = firstVisibleIndices[lane];
                } else if (lane == 0) {
                    iM1044maxInRangejy6DScQ = 0;
                } else {
                    iM1044maxInRangejy6DScQ = m1044maxInRangejy6DScQ(initialItemIndices, SpanRange.m1054constructorimpl(0, lane)) + 1;
                }
                initialItemIndices[lane] = iM1044maxInRangejy6DScQ;
                context.getLaneInfo().setLane(initialItemIndices[lane], lane);
            }
        }
        if (firstVisibleOffsets.length == context.getLaneCount()) {
            initialItemOffsets = firstVisibleOffsets;
        } else {
            initialItemOffsets = new int[context.getLaneCount()];
            int lane2 = 0;
            int length2 = initialItemOffsets.length;
            while (lane2 < length2) {
                if (lane2 < firstVisibleOffsets.length) {
                    i = firstVisibleOffsets[lane2];
                } else {
                    i = lane2 == 0 ? 0 : initialItemOffsets[lane2 - 1];
                }
                initialItemOffsets[lane2] = i;
                lane2++;
            }
        }
        float $this$fastRoundToInt$iv = state.scrollToBeConsumed$foundation(isLookingAhead);
        return measure(context, Math.round($this$fastRoundToInt$iv), initialItemIndices, initialItemOffsets, true);
    }

    /* JADX WARN: Code duplicated, block: B:125:0x03da  */
    /* JADX WARN: Code duplicated, block: B:378:0x0a45 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:379:0x0a47  */
    /* JADX WARN: Code duplicated, block: B:382:0x0a73  */
    /* JADX WARN: Code duplicated, block: B:384:0x0a8d  */
    /* JADX WARN: Code duplicated, block: B:385:0x0a8f  */
    /* JADX WARN: Code duplicated, block: B:388:0x0a95 A[LOOP:26: B:381:0x0a71->B:388:0x0a95, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:392:0x0aa6  */
    /* JADX WARN: Code duplicated, block: B:393:0x0aab  */
    /* JADX WARN: Code duplicated, block: B:396:0x0ada  */
    /* JADX WARN: Code duplicated, block: B:397:0x0add  */
    /* JADX WARN: Code duplicated, block: B:399:0x0af8  */
    /* JADX WARN: Code duplicated, block: B:401:0x0b04 A[LOOP:24: B:361:0x09f0->B:401:0x0b04, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:410:0x0b91  */
    /* JADX WARN: Code duplicated, block: B:412:0x0bb5  */
    /* JADX WARN: Code duplicated, block: B:413:0x0bbc A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:414:0x0bbe  */
    /* JADX WARN: Code duplicated, block: B:416:0x0bd8  */
    /* JADX WARN: Code duplicated, block: B:418:0x0bf2  */
    /* JADX WARN: Code duplicated, block: B:419:0x0bf4  */
    /* JADX WARN: Code duplicated, block: B:422:0x0bf9 A[LOOP:28: B:415:0x0bd6->B:422:0x0bf9, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:426:0x0c08  */
    /* JADX WARN: Code duplicated, block: B:427:0x0c0a  */
    /* JADX WARN: Code duplicated, block: B:430:0x0c11  */
    /* JADX WARN: Code duplicated, block: B:431:0x0c13  */
    /* JADX WARN: Code duplicated, block: B:433:0x0c1e  */
    /* JADX WARN: Code duplicated, block: B:435:0x0c24  */
    /* JADX WARN: Code duplicated, block: B:436:0x0c26  */
    /* JADX WARN: Code duplicated, block: B:438:0x0c32  */
    /* JADX WARN: Code duplicated, block: B:440:0x0c3e  */
    /* JADX WARN: Code duplicated, block: B:441:0x0c40  */
    /* JADX WARN: Code duplicated, block: B:444:0x0c45 A[LOOP:29: B:437:0x0c30->B:444:0x0c45, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:446:0x0c4c  */
    /* JADX WARN: Code duplicated, block: B:449:0x0c50  */
    /* JADX WARN: Code duplicated, block: B:451:0x0c5e  */
    /* JADX WARN: Code duplicated, block: B:452:0x0c6a  */
    /* JADX WARN: Code duplicated, block: B:454:0x0c93  */
    /* JADX WARN: Code duplicated, block: B:458:0x0cbe  */
    /* JADX WARN: Code duplicated, block: B:461:0x0ce1  */
    /* JADX WARN: Code duplicated, block: B:464:0x0d4f  */
    /* JADX WARN: Code duplicated, block: B:466:0x0d67  */
    /* JADX WARN: Code duplicated, block: B:468:0x0d6d  */
    /* JADX WARN: Code duplicated, block: B:469:0x0d70  */
    /* JADX WARN: Code duplicated, block: B:472:0x0db3  */
    /* JADX WARN: Code duplicated, block: B:473:0x0db6  */
    /* JADX WARN: Code duplicated, block: B:475:0x0dba  */
    /* JADX WARN: Code duplicated, block: B:477:0x0dc6 A[LOOP:30: B:476:0x0dc4->B:477:0x0dc6, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:480:0x0ddc  */
    /* JADX WARN: Code duplicated, block: B:481:0x0de1  */
    /* JADX WARN: Code duplicated, block: B:485:0x0ded  */
    /* JADX WARN: Code duplicated, block: B:487:0x0df7  */
    /* JADX WARN: Code duplicated, block: B:488:0x0df9  */
    /* JADX WARN: Code duplicated, block: B:491:0x0dfe A[LOOP:31: B:484:0x0deb->B:491:0x0dfe, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:494:0x0e04  */
    /* JADX WARN: Code duplicated, block: B:496:0x0e0b  */
    /* JADX WARN: Code duplicated, block: B:498:0x0e13  */
    /* JADX WARN: Code duplicated, block: B:499:0x0e15  */
    /* JADX WARN: Code duplicated, block: B:502:0x0e1a A[LOOP:32: B:495:0x0e09->B:502:0x0e1a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:505:0x0e20  */
    /* JADX WARN: Code duplicated, block: B:506:0x0e23  */
    /* JADX WARN: Code duplicated, block: B:509:0x0e9b  */
    /* JADX WARN: Code duplicated, block: B:511:0x0ee3  */
    /* JADX WARN: Code duplicated, block: B:512:0x0ee5  */
    /* JADX WARN: Code duplicated, block: B:514:0x0ee8  */
    /* JADX WARN: Code duplicated, block: B:515:0x0eea  */
    /* JADX WARN: Code duplicated, block: B:518:0x0f2c  */
    /* JADX WARN: Code duplicated, block: B:519:0x0f2e  */
    /* JADX WARN: Code duplicated, block: B:521:0x0f31  */
    /* JADX WARN: Code duplicated, block: B:523:0x0f3b  */
    /* JADX WARN: Code duplicated, block: B:524:0x0f42  */
    /* JADX WARN: Code duplicated, block: B:527:0x0f61  */
    /* JADX WARN: Code duplicated, block: B:529:0x0f65  */
    /* JADX WARN: Code duplicated, block: B:545:0x107a  */
    /* JADX WARN: Code duplicated, block: B:575:0x03ea A[EDGE_INSN: B:575:0x03ea->B:127:0x03ea BREAK  A[LOOP:9: B:105:0x039c->B:577:0x039c], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:615:0x0b16 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:618:0x0a92 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:619:0x0a9d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:623:0x0bf7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:624:0x0c01 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:625:0x0c4a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:626:0x0c43 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:628:0x0e01 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:629:0x0dfc A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:630:0x0e18 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:631:0x0e1d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:638:0x0f6b A[SYNTHETIC] */
    private static final LazyStaggeredGridMeasureResult measure(final LazyStaggeredGridMeasureContext $this$measure, int initialScrollDelta, int[] initialItemIndices, int[] initialItemOffsets, boolean canRestartMeasure) {
        int layoutWidth;
        int layoutHeight;
        int scrollDelta;
        boolean remeasureNeeded;
        int i;
        int scrollDelta2;
        int initialLaneToMeasure;
        int initialLaneToMeasure2;
        int currentLaneIndex;
        int itemIndex;
        long spanRange;
        boolean z;
        int i2;
        LazyStaggeredGridMeasuredItem measuredItem;
        int offset;
        boolean z2;
        int[] gaps;
        int i$iv;
        int i3;
        int lane;
        boolean z3;
        int maxOffset;
        boolean z4;
        int[] firstItemIndices;
        int[] firstItemOffsets;
        int[] itemScrollOffsets;
        ArrayDeque[] measuredItems;
        int extraItemOffset;
        int extraItemOffset2;
        List itemsRetainedForLookahead;
        LazyStaggeredGridMeasureContext $this$calculateExtraItems$iv;
        boolean beforeVisibleBounds$iv;
        int $i$f$calculateExtraItems;
        List<Integer> pinnedItems;
        List<Integer> list;
        int extraItemOffset3;
        int index$iv$iv$iv;
        List listEmptyList;
        int extraItemOffset4;
        final List positionedItems;
        int layoutHeight2;
        int layoutWidth2;
        int layoutHeight3;
        int length;
        int i4;
        boolean z5;
        boolean canScrollForward;
        int length2;
        int i5;
        boolean z6;
        int element$iv;
        int it;
        int element$iv2;
        int it2;
        long disappearingItemsSize;
        int i6;
        int oldMainAxisSize;
        int layoutWidth3;
        int layoutHeight4;
        int newMainAxisSize;
        int index$iv;
        int size;
        int index$iv2;
        int index$iv$iv$iv2;
        int $i$f$calculateExtraItems2;
        int itemCount;
        int lane2;
        int lane3;
        int i7;
        int itemCount2;
        int element$iv3;
        int i8;
        int it3;
        List $this$fastForEach$iv$iv;
        int itemCount3;
        int $i$f$calculateExtraItems3;
        boolean z7;
        int i9;
        List $this$fastForEach$iv$iv2;
        LazyStaggeredGridMeasuredItem it4;
        boolean z8;
        List list2;
        int i$iv2;
        int iMin;
        int lastVisibleItemIndex$iv;
        boolean isLookingAhead$iv;
        int lastVisibleItemIndex$iv2;
        LazyStaggeredGridMeasureContext $this$itemsRetainedForLookahead$iv;
        List<LazyStaggeredGridItemInfo> visibleItemsInfo;
        int size2;
        int index$iv$iv$iv3;
        Object obj;
        LazyStaggeredGridItemInfo lazyStaggeredGridItemInfo;
        int lane$iv;
        int[] it$iv;
        int crossAxisOffset$iv;
        int i10;
        List<LazyStaggeredGridItemInfo> list3;
        Object item$iv$iv$iv;
        boolean z9;
        boolean z10;
        int firstIndex;
        List list4;
        int laneIndex;
        int toScrollBack;
        boolean z11;
        boolean z12;
        $this$measure = $this$measure;
        final LazyLayoutMeasureScope scope$iv = $this$measure.getMeasureScope();
        int offset2 = $this$measure.getItemProvider().getItemCount();
        if (offset2 <= 0 || $this$measure.getLaneCount() == 0) {
            long j = 4294967295L;
            char c = ' ';
            int layoutWidth4 = Constraints.m7860getMinWidthimpl($this$measure.getConstraints());
            int layoutHeight5 = Constraints.m7859getMinHeightimpl($this$measure.getConstraints());
            $this$measure.getState().getItemAnimator$foundation().onMeasured(0, layoutWidth4, layoutHeight5, new ArrayList(), $this$measure.getMeasuredItemProvider().getKeyIndexMap(), $this$measure.getMeasuredItemProvider(), $this$measure.getIsVertical(), scope$iv.isLookingAhead(), $this$measure.getLaneCount(), $this$measure.getIsInLookaheadScope(), 0, 0, $this$measure.getCoroutineScope(), $this$measure.getGraphicsContext());
            if (scope$iv.isLookingAhead()) {
                layoutWidth = layoutWidth4;
                layoutHeight = layoutHeight5;
            } else {
                long disappearingItemsSize2 = $this$measure.getState().getItemAnimator$foundation().m988getMinSizeToFitDisappearingItemsYbymL2g();
                if (IntSize.m8074equalsimpl0(disappearingItemsSize2, IntSize.INSTANCE.m8081getZeroYbymL2g())) {
                    layoutWidth = layoutWidth4;
                    layoutHeight = layoutHeight5;
                } else {
                    int $i$f$unpackInt1 = (int) (disappearingItemsSize2 >> c);
                    int layoutWidth5 = ConstraintsKt.m7875constrainWidthK40F9xA($this$measure.getConstraints(), $i$f$unpackInt1);
                    int $i$f$unpackInt2 = (int) (disappearingItemsSize2 & j);
                    layoutWidth = layoutWidth5;
                    layoutHeight = ConstraintsKt.m7874constrainHeightK40F9xA($this$measure.getConstraints(), $i$f$unpackInt2);
                }
            }
            MeasureResult measureResultLayout$default = MeasureScope.layout$default(scope$iv, layoutWidth, layoutHeight, null, new Function1() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return LazyStaggeredGridMeasureKt.measure$lambda$0$0((Placeable.PlacementScope) obj2);
                }
            }, 4, null);
            boolean isVertical = $this$measure.getIsVertical();
            List listEmptyList2 = CollectionsKt.emptyList();
            int width$iv = Constraints.m7860getMinWidthimpl($this$measure.getConstraints());
            int height$iv = Constraints.m7859getMinHeightimpl($this$measure.getConstraints());
            return new LazyStaggeredGridMeasureResult(initialItemIndices, initialItemOffsets, 0.0f, measureResultLayout$default, 0.0f, false, isVertical, false, $this$measure.getResolvedSlots(), $this$measure.getItemProvider().getSpanProvider(), scope$iv, offset2, listEmptyList2, IntSize.m8071constructorimpl((((long) width$iv) << c) | (((long) height$iv) & j)), -$this$measure.getBeforeContentPadding(), $this$measure.getMainAxisAvailableSize() + $this$measure.getAfterContentPadding(), $this$measure.getBeforeContentPadding(), $this$measure.getAfterContentPadding(), $this$measure.getMainAxisSpacing(), $this$measure.getCoroutineScope(), null);
        }
        int scrollDelta3 = initialScrollDelta;
        int[] iArrCopyOf = Arrays.copyOf(initialItemIndices, initialItemIndices.length);
        String str = "copyOf(...)";
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        int[] firstItemIndices2 = iArrCopyOf;
        int[] iArrCopyOf2 = Arrays.copyOf(initialItemOffsets, initialItemOffsets.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf2, "copyOf(...)");
        int[] firstItemOffsets2 = iArrCopyOf2;
        boolean remeasureNeeded2 = false;
        ensureIndicesInRange($this$measure, firstItemIndices2, offset2);
        offsetBy(firstItemOffsets2, -scrollDelta3);
        int laneCount = $this$measure.getLaneCount();
        ArrayDeque[] measuredItems2 = new ArrayDeque[laneCount];
        for (int i11 = 0; i11 < laneCount; i11++) {
            measuredItems2[i11] = new ArrayDeque(16);
        }
        offsetBy(firstItemOffsets2, -$this$measure.getBeforeContentPadding());
        int laneToCheckForGaps = -1;
        while (true) {
            if (!measure$lambda$0$hasSpaceBeforeFirst(firstItemIndices2, firstItemOffsets2, $this$measure)) {
                scrollDelta = scrollDelta3;
                remeasureNeeded = remeasureNeeded2;
                i = 0;
                break;
            }
            int laneIndex2 = indexOfMaxValue(firstItemIndices2);
            int itemIndex2 = firstItemIndices2[laneIndex2];
            i = 0;
            int length3 = firstItemOffsets2.length;
            int i12 = 0;
            while (i12 < length3) {
                int scrollDelta4 = scrollDelta3;
                boolean remeasureNeeded3 = remeasureNeeded2;
                if (firstItemIndices2[i12] != firstItemIndices2[laneIndex2] && firstItemOffsets2[i12] < firstItemOffsets2[laneIndex2]) {
                    firstItemOffsets2[i12] = firstItemOffsets2[laneIndex2];
                }
                i12++;
                scrollDelta3 = scrollDelta4;
                remeasureNeeded2 = remeasureNeeded3;
            }
            scrollDelta = scrollDelta3;
            remeasureNeeded = remeasureNeeded2;
            int previousItemIndex = findPreviousItemIndex($this$measure, itemIndex2, laneIndex2);
            if (previousItemIndex < 0) {
                laneToCheckForGaps = laneIndex2;
                break;
            }
            long spanRange2 = $this$measure.m1039getSpanRangelOCCd4c($this$measure.getItemProvider(), previousItemIndex, laneIndex2);
            $this$measure.getLaneInfo().setLane(previousItemIndex, ((int) (spanRange2 & 4294967295L)) - ((int) (spanRange2 >> 32)) != 1 ? -2 : (int) (spanRange2 >> 32));
            LazyStaggeredGridMeasuredItem measuredItem2 = $this$measure.getMeasuredItemProvider().m1048getAndMeasurejy6DScQ(previousItemIndex, spanRange2);
            int offset3 = m1044maxInRangejy6DScQ(firstItemOffsets2, spanRange2);
            int[] gaps2 = ((int) (spanRange2 & 4294967295L)) - ((int) (spanRange2 >> 32)) != 1 ? $this$measure.getLaneInfo().getGaps(previousItemIndex) : null;
            int i$iv3 = (int) (spanRange2 >> 32);
            int i13 = (int) (spanRange2 & 4294967295L);
            for (int i$iv4 = i$iv3; i$iv4 < i13; i$iv4++) {
                int lane4 = i$iv4;
                firstItemIndices2[lane4] = previousItemIndex;
                int gap = gaps2 == null ? 0 : gaps2[lane4];
                int newOffset = offset3 + measuredItem2.getMainAxisSizeWithSpacings() + gap;
                firstItemOffsets2[lane4] = newOffset;
                if ($this$measure.getMainAxisAvailableSize() + newOffset <= 0) {
                    remeasureNeeded = true;
                }
            }
            scrollDelta3 = scrollDelta;
            remeasureNeeded2 = remeasureNeeded;
        }
        int $i$f$debugLog = $this$measure.getBeforeContentPadding();
        int minOffset = -$i$f$debugLog;
        if (firstItemOffsets2[i] < minOffset) {
            int notConsumedScrollDelta = minOffset - firstItemOffsets2[i];
            offsetBy(firstItemOffsets2, minOffset - firstItemOffsets2[i]);
            scrollDelta2 = scrollDelta - notConsumedScrollDelta;
        } else {
            scrollDelta2 = scrollDelta;
        }
        offsetBy(firstItemOffsets2, $this$measure.getBeforeContentPadding());
        int laneToCheckForGaps2 = laneToCheckForGaps == -1 ? ArraysKt.indexOf(firstItemIndices2, i) : laneToCheckForGaps;
        if (laneToCheckForGaps2 != -1) {
            int lane5 = laneToCheckForGaps2;
            if (measure$lambda$0$misalignedStart(firstItemIndices2, $this$measure, firstItemOffsets2, lane5) && canRestartMeasure) {
                $this$measure.getLaneInfo().reset();
                int length4 = firstItemIndices2.length;
                int[] iArr = new int[length4];
                for (int i14 = 0; i14 < length4; i14++) {
                    iArr[i14] = -1;
                }
                int length5 = firstItemOffsets2.length;
                int[] iArr2 = new int[length5];
                for (int i15 = 0; i15 < length5; i15++) {
                    iArr2[i15] = firstItemOffsets2[lane5];
                }
                return measure($this$measure, scrollDelta2, iArr, iArr2, false);
            }
        }
        int[] iArrCopyOf3 = Arrays.copyOf(firstItemIndices2, firstItemIndices2.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf3, "copyOf(...)");
        int[] currentItemIndices = iArrCopyOf3;
        int length6 = firstItemOffsets2.length;
        int[] currentItemOffsets = new int[length6];
        for (int i16 = 0; i16 < length6; i16++) {
            currentItemOffsets[i16] = -firstItemOffsets2[i16];
        }
        int minVisibleOffset = minOffset + $this$measure.getMainAxisSpacing();
        int maxOffset2 = RangesKt.coerceAtLeast($this$measure.getMainAxisAvailableSize() + $this$measure.getAfterContentPadding(), 0);
        int initialItemsMeasured = 0;
        boolean remeasureNeeded4 = remeasureNeeded;
        int initialLaneToMeasure3 = indexOfMinValue$default(currentItemIndices, 0, 1, null);
        while (initialLaneToMeasure3 != -1 && initialItemsMeasured < $this$measure.getLaneCount()) {
            int itemIndex3 = currentItemIndices[initialLaneToMeasure3];
            int laneIndex3 = initialLaneToMeasure3;
            initialLaneToMeasure3 = indexOfMinValue(currentItemIndices, itemIndex3);
            initialItemsMeasured++;
            if (itemIndex3 >= 0) {
                int[] currentItemIndices2 = currentItemIndices;
                int laneIndex4 = laneToCheckForGaps2;
                long spanRange3 = $this$measure.m1039getSpanRangelOCCd4c($this$measure.getItemProvider(), itemIndex3, laneIndex3);
                LazyStaggeredGridMeasuredItem measuredItem3 = $this$measure.getMeasuredItemProvider().m1048getAndMeasurejy6DScQ(itemIndex3, spanRange3);
                int[] firstItemIndices3 = firstItemIndices2;
                int[] firstItemOffsets3 = firstItemOffsets2;
                $this$measure.getLaneInfo().setLane(itemIndex3, ((int) (spanRange3 & 4294967295L)) - ((int) (spanRange3 >> 32)) != 1 ? -2 : (int) (spanRange3 >> 32));
                int offset4 = m1044maxInRangejy6DScQ(currentItemOffsets, spanRange3);
                int i$iv5 = (int) (spanRange3 >> 32);
                int i17 = (int) (spanRange3 & 4294967295L);
                int i$iv6 = i$iv5;
                while (i$iv6 < i17) {
                    int lane6 = i$iv6;
                    currentItemOffsets[lane6] = offset4 + measuredItem3.getMainAxisSizeWithSpacings();
                    currentItemIndices2[lane6] = itemIndex3;
                    measuredItems2[lane6].addLast(measuredItem3);
                    i$iv6++;
                    itemIndex3 = itemIndex3;
                }
                if (offset4 < minVisibleOffset && currentItemOffsets[(int) (spanRange3 >> 32)] <= minVisibleOffset) {
                    measuredItem3.setVisible(false);
                    remeasureNeeded4 = true;
                }
                if (((int) (spanRange3 & 4294967295L)) - ((int) (spanRange3 >> 32)) != 1) {
                    initialItemsMeasured = $this$measure.getLaneCount();
                    laneToCheckForGaps2 = laneIndex4;
                    initialLaneToMeasure3 = initialLaneToMeasure3;
                    currentItemIndices = currentItemIndices2;
                    firstItemIndices2 = firstItemIndices3;
                    firstItemOffsets2 = firstItemOffsets3;
                } else {
                    laneToCheckForGaps2 = laneIndex4;
                    initialLaneToMeasure3 = initialLaneToMeasure3;
                    initialItemsMeasured = initialItemsMeasured;
                    currentItemIndices = currentItemIndices2;
                    firstItemIndices2 = firstItemIndices3;
                    firstItemOffsets2 = firstItemOffsets3;
                }
            }
        }
        int[] currentItemIndices3 = currentItemIndices;
        int[] firstItemIndices4 = firstItemIndices2;
        int[] firstItemOffsets4 = firstItemOffsets2;
        while (true) {
            int length7 = currentItemOffsets.length;
            int i18 = 0;
            while (true) {
                if (i18 >= length7) {
                    initialLaneToMeasure = initialLaneToMeasure3;
                    initialLaneToMeasure2 = 0;
                    break;
                }
                int element$iv4 = currentItemOffsets[i18];
                initialLaneToMeasure = initialLaneToMeasure3;
                int it5 = (element$iv4 < maxOffset2 || element$iv4 <= 0) ? 1 : 0;
                if (it5 != 0) {
                    initialLaneToMeasure2 = 1;
                    break;
                }
                i18++;
                initialLaneToMeasure3 = initialLaneToMeasure;
            }
            if (initialLaneToMeasure2 == 0) {
                ArrayDeque[] arrayDequeArr = measuredItems2;
                int length8 = arrayDequeArr.length;
                int i19 = 0;
                while (true) {
                    if (i19 >= length8) {
                        z12 = true;
                        break;
                    }
                    if (!arrayDequeArr[i19].isEmpty()) {
                        z12 = false;
                        break;
                    }
                    i19++;
                }
                if (!z12) {
                    break;
                }
                currentLaneIndex = indexOfMinValue$default(currentItemOffsets, 0, 1, null);
                itemIndex = ArraysKt.maxOrThrow(currentItemIndices3) + 1;
                if (itemIndex >= offset2) {
                    break;
                }
                maxOffset2 = maxOffset2;
                measuredItems2 = measuredItems2;
                initialItemsMeasured = initialItemsMeasured;
                offset2 = offset2;
                initialLaneToMeasure3 = initialLaneToMeasure;
                firstItemIndices4 = firstItemIndices4;
                firstItemOffsets4 = firstItemOffsets4;
                spanRange = $this$measure.m1039getSpanRangelOCCd4c($this$measure.getItemProvider(), itemIndex, currentLaneIndex);
                LazyStaggeredGridLaneInfo laneInfo = $this$measure.getLaneInfo();
                if (((int) (spanRange & 4294967295L)) - ((int) (spanRange >> 32)) != 1) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    i2 = -2;
                } else {
                    i2 = (int) (spanRange >> 32);
                }
                laneInfo.setLane(itemIndex, i2);
                measuredItem = $this$measure.getMeasuredItemProvider().m1048getAndMeasurejy6DScQ(itemIndex, spanRange);
                offset = m1044maxInRangejy6DScQ(currentItemOffsets, spanRange);
                str = str;
                if (((int) (spanRange & 4294967295L)) - ((int) (spanRange >> 32)) != 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    gaps = $this$measure.getLaneInfo().getGaps(itemIndex);
                    if (gaps == null) {
                        gaps = new int[$this$measure.getLaneCount()];
                    }
                } else {
                    gaps = null;
                }
                int minVisibleOffset2 = minVisibleOffset;
                i3 = (int) (spanRange & 4294967295L);
                for (i$iv = (int) (spanRange >> 32); i$iv < i3; i$iv++) {
                    lane = i$iv;
                    if (gaps != null) {
                        gaps[lane] = offset - currentItemOffsets[lane];
                    }
                    currentItemIndices3[lane] = itemIndex;
                    currentItemOffsets[lane] = measuredItem.getMainAxisSizeWithSpacings() + offset;
                    measuredItems2[lane].addLast(measuredItem);
                }
                $this$measure.getLaneInfo().setGaps(itemIndex, gaps);
                minVisibleOffset = minVisibleOffset2;
                if (offset >= minVisibleOffset && currentItemOffsets[(int) (spanRange >> 32)] <= minVisibleOffset) {
                    measuredItem.setVisible(false);
                }
            } else {
                currentLaneIndex = indexOfMinValue$default(currentItemOffsets, 0, 1, null);
                itemIndex = ArraysKt.maxOrThrow(currentItemIndices3) + 1;
                if (itemIndex >= offset2) {
                    break;
                    break;
                }
                maxOffset2 = maxOffset2;
                measuredItems2 = measuredItems2;
                initialItemsMeasured = initialItemsMeasured;
                offset2 = offset2;
                initialLaneToMeasure3 = initialLaneToMeasure;
                firstItemIndices4 = firstItemIndices4;
                firstItemOffsets4 = firstItemOffsets4;
                spanRange = $this$measure.m1039getSpanRangelOCCd4c($this$measure.getItemProvider(), itemIndex, currentLaneIndex);
                LazyStaggeredGridLaneInfo laneInfo2 = $this$measure.getLaneInfo();
                if (((int) (spanRange & 4294967295L)) - ((int) (spanRange >> 32)) != 1) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    i2 = -2;
                } else {
                    i2 = (int) (spanRange >> 32);
                }
                laneInfo2.setLane(itemIndex, i2);
                measuredItem = $this$measure.getMeasuredItemProvider().m1048getAndMeasurejy6DScQ(itemIndex, spanRange);
                offset = m1044maxInRangejy6DScQ(currentItemOffsets, spanRange);
                str = str;
                if (((int) (spanRange & 4294967295L)) - ((int) (spanRange >> 32)) != 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    gaps = $this$measure.getLaneInfo().getGaps(itemIndex);
                    if (gaps == null) {
                        gaps = new int[$this$measure.getLaneCount()];
                    }
                } else {
                    gaps = null;
                }
                int minVisibleOffset3 = minVisibleOffset;
                i3 = (int) (spanRange & 4294967295L);
                while (i$iv < i3) {
                    lane = i$iv;
                    if (gaps != null) {
                        gaps[lane] = offset - currentItemOffsets[lane];
                    }
                    currentItemIndices3[lane] = itemIndex;
                    currentItemOffsets[lane] = measuredItem.getMainAxisSizeWithSpacings() + offset;
                    measuredItems2[lane].addLast(measuredItem);
                }
                $this$measure.getLaneInfo().setGaps(itemIndex, gaps);
                minVisibleOffset = minVisibleOffset3;
                if (offset >= minVisibleOffset) {
                }
            }
        }
        int length9 = measuredItems2.length;
        for (int laneIndex5 = 0; laneIndex5 < length9; laneIndex5++) {
            ArrayDeque laneItems = measuredItems2[laneIndex5];
            while (laneItems.size() > 1 && !((LazyStaggeredGridMeasuredItem) laneItems.first()).getIsVisible()) {
                LazyStaggeredGridMeasuredItem item = (LazyStaggeredGridMeasuredItem) laneItems.removeFirst();
                int[] gaps3 = item.getSpan() != 1 ? $this$measure.getLaneInfo().getGaps(item.getIndex()) : null;
                firstItemOffsets4[laneIndex5] = firstItemOffsets4[laneIndex5] - (item.getMainAxisSizeWithSpacings() + (gaps3 == null ? 0 : gaps3[laneIndex5]));
            }
            LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem = (LazyStaggeredGridMeasuredItem) laneItems.firstOrNull();
            firstItemIndices4[laneIndex5] = lazyStaggeredGridMeasuredItem != null ? lazyStaggeredGridMeasuredItem.getIndex() : -1;
        }
        int[] $this$any$iv = currentItemIndices3;
        int length10 = $this$any$iv.length;
        int i20 = 0;
        while (true) {
            if (i20 >= length10) {
                z3 = false;
                break;
            }
            int element$iv5 = $this$any$iv[i20];
            int[] $this$any$iv2 = $this$any$iv;
            if (element$iv5 == offset2 + (-1)) {
                z3 = true;
                break;
            }
            i20++;
            $this$any$iv = $this$any$iv2;
        }
        if (z3) {
            offsetBy(currentItemOffsets, -$this$measure.getMainAxisSpacing());
        }
        int preScrollBackScrollDelta = scrollDelta2;
        int[] $this$all$iv = currentItemOffsets;
        int length11 = $this$all$iv.length;
        int i21 = 0;
        while (true) {
            if (i21 >= length11) {
                maxOffset = maxOffset2;
                z4 = true;
                break;
            }
            int element$iv6 = $this$all$iv[i21];
            int[] $this$all$iv2 = $this$all$iv;
            maxOffset = maxOffset2;
            if (!(element$iv6 < $this$measure.getMainAxisAvailableSize())) {
                z4 = false;
                break;
            }
            i21++;
            $this$all$iv = $this$all$iv2;
            maxOffset2 = maxOffset;
        }
        if (z4) {
            int maxOffsetLane = indexOfMaxValue(currentItemOffsets);
            int toScrollBack2 = $this$measure.getMainAxisAvailableSize() - currentItemOffsets[maxOffsetLane];
            firstItemOffsets = firstItemOffsets4;
            offsetBy(firstItemOffsets, -toScrollBack2);
            offsetBy(currentItemOffsets, toScrollBack2);
            boolean gapDetected = false;
            while (true) {
                int maxOffsetLane2 = maxOffsetLane;
                int maxOffsetLane3 = firstItemOffsets.length;
                toScrollBack = toScrollBack2;
                int toScrollBack3 = 0;
                while (true) {
                    if (toScrollBack3 >= maxOffsetLane3) {
                        z11 = false;
                        break;
                    }
                    int element$iv7 = firstItemOffsets[toScrollBack3];
                    int i22 = maxOffsetLane3;
                    int i23 = toScrollBack3;
                    if (element$iv7 < $this$measure.getBeforeContentPadding()) {
                        z11 = true;
                        break;
                    }
                    toScrollBack3 = i23 + 1;
                    maxOffsetLane3 = i22;
                }
                if (!z11) {
                    firstItemIndices = firstItemIndices4;
                    break;
                }
                int laneIndex6 = indexOfMinValue$default(firstItemOffsets, 0, 1, null);
                int nextLaneIndex = indexOfMaxValue(firstItemIndices4);
                if (laneIndex6 != nextLaneIndex) {
                    if (firstItemOffsets[laneIndex6] == firstItemOffsets[nextLaneIndex]) {
                        laneIndex6 = nextLaneIndex;
                    } else {
                        gapDetected = true;
                    }
                }
                int currentIndex = firstItemIndices4[laneIndex6] == -1 ? offset2 : firstItemIndices4[laneIndex6];
                int previousIndex = findPreviousItemIndex($this$measure, currentIndex, laneIndex6);
                if (previousIndex < 0) {
                    if (!gapDetected) {
                        firstItemIndices = firstItemIndices4;
                        if (!measure$lambda$0$misalignedStart(firstItemIndices, $this$measure, firstItemOffsets, laneIndex6)) {
                        }
                        gapDetected = gapDetected;
                        break;
                    }
                    firstItemIndices = firstItemIndices4;
                    if (!canRestartMeasure) {
                        gapDetected = gapDetected;
                        break;
                    }
                    $this$measure.getLaneInfo().reset();
                    int length12 = firstItemIndices.length;
                    int[] iArr3 = new int[length12];
                    for (int currentIndex2 = 0; currentIndex2 < length12; currentIndex2++) {
                        iArr3[currentIndex2] = -1;
                    }
                    int length13 = firstItemOffsets.length;
                    int[] iArr4 = new int[length13];
                    for (int i24 = 0; i24 < length13; i24++) {
                        iArr4[i24] = firstItemOffsets[laneIndex6];
                    }
                    return measure($this$measure, scrollDelta2, iArr3, iArr4, false);
                }
                boolean gapDetected2 = gapDetected;
                int initialItemsMeasured2 = initialItemsMeasured;
                int[] firstItemIndices5 = firstItemIndices4;
                long spanRange4 = $this$measure.m1039getSpanRangelOCCd4c($this$measure.getItemProvider(), previousIndex, laneIndex6);
                int itemCount4 = offset2;
                $this$measure.getLaneInfo().setLane(previousIndex, ((int) (spanRange4 & 4294967295L)) - ((int) (spanRange4 >> 32)) != 1 ? -2 : (int) (spanRange4 >> 32));
                LazyStaggeredGridMeasuredItem measuredItem4 = $this$measure.getMeasuredItemProvider().m1048getAndMeasurejy6DScQ(previousIndex, spanRange4);
                int offset5 = m1044maxInRangejy6DScQ(firstItemOffsets, spanRange4);
                int[] gaps4 = ((int) (spanRange4 & 4294967295L)) - ((int) (spanRange4 >> 32)) != 1 ? $this$measure.getLaneInfo().getGaps(previousIndex) : null;
                int[] gaps5 = gaps4;
                int i$iv7 = (int) (spanRange4 >> 32);
                int i25 = (int) (spanRange4 & 4294967295L);
                int i$iv8 = i$iv7;
                while (i$iv8 < i25) {
                    int lane7 = i$iv8;
                    int i26 = i25;
                    if (firstItemOffsets[lane7] != offset5) {
                        gapDetected2 = true;
                    }
                    measuredItems2[lane7].addFirst(measuredItem4);
                    firstItemIndices5[lane7] = previousIndex;
                    int gap2 = gaps5 == null ? 0 : gaps5[lane7];
                    firstItemOffsets[lane7] = offset5 + measuredItem4.getMainAxisSizeWithSpacings() + gap2;
                    i$iv8++;
                    i25 = i26;
                }
                firstItemIndices4 = firstItemIndices5;
                maxOffsetLane = maxOffsetLane2;
                toScrollBack2 = toScrollBack;
                gapDetected = gapDetected2;
                initialItemsMeasured = initialItemsMeasured2;
                offset2 = itemCount4;
            }
            if (gapDetected && canRestartMeasure) {
                $this$measure.getLaneInfo().reset();
                return measure($this$measure, scrollDelta2, firstItemIndices, firstItemOffsets, false);
            }
            scrollDelta2 += toScrollBack;
            int minOffsetLane = indexOfMinValue$default(firstItemOffsets, 0, 1, null);
            if (firstItemOffsets[minOffsetLane] < 0) {
                int offsetValue = firstItemOffsets[minOffsetLane];
                scrollDelta2 += offsetValue;
                offsetBy(currentItemOffsets, offsetValue);
                offsetBy(firstItemOffsets, -offsetValue);
            }
        } else {
            offset2 = offset2;
            firstItemIndices = firstItemIndices4;
            firstItemOffsets = firstItemOffsets4;
        }
        float scrollToBeConsumed = $this$measure.getState().scrollToBeConsumed$foundation(scope$iv.isLookingAhead());
        float consumedScroll = (MathKt.getSign(Math.round(scrollToBeConsumed)) != MathKt.getSign(scrollDelta2) || Math.abs(Math.round(scrollToBeConsumed)) < Math.abs(scrollDelta2)) ? scrollToBeConsumed : scrollDelta2;
        float unconsumedScroll = scrollToBeConsumed - consumedScroll;
        float scrollBackAmount = (!scope$iv.isLookingAhead() || scrollDelta2 <= preScrollBackScrollDelta || unconsumedScroll > 0.0f) ? 0.0f : (scrollDelta2 - preScrollBackScrollDelta) + unconsumedScroll;
        int[] iArrCopyOf4 = Arrays.copyOf(firstItemOffsets, firstItemOffsets.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf4, str);
        int[] itemScrollOffsets2 = iArrCopyOf4;
        int length14 = itemScrollOffsets2.length;
        for (int i$iv9 = 0; i$iv9 < length14; i$iv9++) {
            int it6 = itemScrollOffsets2[i$iv9];
            itemScrollOffsets2[i$iv9] = -it6;
        }
        int $i$f$debugLog2 = $this$measure.getBeforeContentPadding();
        if ($i$f$debugLog2 > $this$measure.getMainAxisSpacing()) {
            int laneIndex7 = 0;
            int length15 = measuredItems2.length;
            while (laneIndex7 < length15) {
                ArrayDeque laneItems2 = measuredItems2[laneIndex7];
                int i27 = 0;
                int size3 = laneItems2.size();
                while (true) {
                    if (i27 >= size3) {
                        laneIndex = laneIndex7;
                        break;
                    }
                    LazyStaggeredGridMeasuredItem item2 = (LazyStaggeredGridMeasuredItem) laneItems2.get(i27);
                    int i28 = size3;
                    LazyStaggeredGridLaneInfo laneInfo3 = $this$measure.getLaneInfo();
                    laneIndex = laneIndex7;
                    int laneIndex8 = item2.getIndex();
                    int[] gaps6 = laneInfo3.getGaps(laneIndex8);
                    int size4 = item2.getMainAxisSizeWithSpacings() + (gaps6 == null ? 0 : gaps6[laneIndex]);
                    if (i27 == CollectionsKt.getLastIndex(laneItems2) || firstItemOffsets[laneIndex] == 0 || firstItemOffsets[laneIndex] < size4) {
                        break;
                    }
                    firstItemOffsets[laneIndex] = firstItemOffsets[laneIndex] - size4;
                    firstItemIndices[laneIndex] = ((LazyStaggeredGridMeasuredItem) laneItems2.get(i27 + 1)).getIndex();
                    i27++;
                    size3 = i28;
                    laneIndex7 = laneIndex;
                }
                laneIndex7 = laneIndex + 1;
            }
        }
        int $i$f$debugLog3 = $this$measure.getBeforeContentPadding();
        int contentPadding = $i$f$debugLog3 + $this$measure.getAfterContentPadding();
        int layoutWidth6 = $this$measure.getIsVertical() ? Constraints.m7858getMaxWidthimpl($this$measure.getConstraints()) : ConstraintsKt.m7875constrainWidthK40F9xA($this$measure.getConstraints(), ArraysKt.maxOrThrow(currentItemOffsets) + contentPadding);
        int layoutHeight6 = $this$measure.getIsVertical() ? ConstraintsKt.m7874constrainHeightK40F9xA($this$measure.getConstraints(), ArraysKt.maxOrThrow(currentItemOffsets) + contentPadding) : Constraints.m7857getMaxHeightimpl($this$measure.getConstraints());
        int it7 = Math.min($this$measure.getIsVertical() ? layoutHeight6 : layoutWidth6, $this$measure.getMainAxisAvailableSize());
        int mainAxisLayoutSize = (it7 - $this$measure.getBeforeContentPadding()) + $this$measure.getAfterContentPadding();
        int index$iv3 = itemScrollOffsets2[0];
        boolean beforeVisibleBounds$iv2 = true;
        LazyStaggeredGridMeasureContext $this$calculateExtraItems$iv2 = $this$measure;
        List listEmptyList3 = null;
        List<Integer> pinnedItems2 = $this$calculateExtraItems$iv2.getPinnedItems();
        int size5 = pinnedItems2.size() - 1;
        if (size5 >= 0) {
            while (true) {
                int index$iv$iv$iv4 = size5;
                size5--;
                int extraItemOffset5 = index$iv3;
                List<Integer> list5 = pinnedItems2;
                boolean beforeVisibleBounds$iv3 = beforeVisibleBounds$iv2;
                int index$iv4 = ((Number) list5.get(index$iv$iv$iv4)).intValue();
                itemScrollOffsets = itemScrollOffsets2;
                int lane8 = $this$measure.getLaneInfo().getLane(index$iv4);
                switch (lane8) {
                    case -2:
                    case -1:
                        measuredItems = measuredItems2;
                        int length16 = measuredItems.length;
                        int i29 = 0;
                        while (true) {
                            if (i29 >= length16) {
                                firstIndex = 1;
                            } else {
                                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem2 = (LazyStaggeredGridMeasuredItem) measuredItems[i29].firstOrNull();
                                int firstIndex2 = lazyStaggeredGridMeasuredItem2 != null ? lazyStaggeredGridMeasuredItem2.getIndex() : -1;
                                int i30 = i29;
                                int firstIndex3 = firstIndex2 > index$iv4 ? 1 : 0;
                                if (firstIndex3 == 0) {
                                    firstIndex = 0;
                                } else {
                                    i29 = i30 + 1;
                                }
                            }
                            break;
                        }
                        break;
                    default:
                        measuredItems = measuredItems2;
                        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem3 = (LazyStaggeredGridMeasuredItem) measuredItems[lane8].firstOrNull();
                        int firstIndex4 = lazyStaggeredGridMeasuredItem3 != null ? lazyStaggeredGridMeasuredItem3.getIndex() : -1;
                        firstIndex = firstIndex4 > index$iv4 ? 1 : 0;
                        break;
                }
                if (firstIndex != 0) {
                    long spanRange$iv = $this$calculateExtraItems$iv2.m1039getSpanRangelOCCd4c($this$calculateExtraItems$iv2.getItemProvider(), index$iv4, 0);
                    if (listEmptyList3 == null) {
                        Object result$iv = new ArrayList();
                        list4 = (List) result$iv;
                    } else {
                        list4 = listEmptyList3;
                    }
                    LazyStaggeredGridMeasuredItem measuredItem$iv = $this$calculateExtraItems$iv2.getMeasuredItemProvider().m1048getAndMeasurejy6DScQ(index$iv4, spanRange$iv);
                    index$iv3 = extraItemOffset5 - measuredItem$iv.getMainAxisSizeWithSpacings();
                    measuredItem$iv.position(index$iv3, 0, mainAxisLayoutSize);
                    list4.add(measuredItem$iv);
                    listEmptyList3 = list4;
                } else {
                    index$iv3 = extraItemOffset5;
                }
                if (size5 >= 0) {
                    beforeVisibleBounds$iv2 = beforeVisibleBounds$iv3;
                    pinnedItems2 = list5;
                    measuredItems2 = measuredItems;
                    itemScrollOffsets2 = itemScrollOffsets;
                    scrollDelta2 = scrollDelta2;
                    $this$calculateExtraItems$iv2 = $this$calculateExtraItems$iv2;
                }
            }
        } else {
            itemScrollOffsets = itemScrollOffsets2;
            measuredItems = measuredItems2;
        }
        if (listEmptyList3 == null) {
            listEmptyList3 = CollectionsKt.emptyList();
        }
        List extraItemsBefore = listEmptyList3;
        ArrayDeque[] measuredItems3 = measuredItems;
        int[] itemScrollOffsets3 = itemScrollOffsets;
        List<LazyStaggeredGridMeasuredItem> listCalculateVisibleItems = calculateVisibleItems($this$measure, measuredItems3, itemScrollOffsets3, mainAxisLayoutSize, minOffset, maxOffset);
        ArrayDeque[] measuredItems4 = measuredItems3;
        int extraItemOffset6 = itemScrollOffsets3[0];
        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem4 = (LazyStaggeredGridMeasuredItem) CollectionsKt.lastOrNull((List) listCalculateVisibleItems);
        int lastVisibleItemIndex$iv3 = lazyStaggeredGridMeasuredItem4 != null ? lazyStaggeredGridMeasuredItem4.getIndex() : -1;
        boolean isLookingAhead$iv2 = scope$iv.isLookingAhead();
        LazyStaggeredGridMeasureContext $this$itemsRetainedForLookahead$iv2 = $this$measure;
        int itemsCount$iv = offset2;
        List list$iv = null;
        if (isLookingAhead$iv2 && $this$itemsRetainedForLookahead$iv2.getApproachLayoutInfo() != null && !$this$itemsRetainedForLookahead$iv2.getApproachLayoutInfo().getVisibleItemsInfo().isEmpty()) {
            extraItemOffset = extraItemOffset6;
            List<LazyStaggeredGridItemInfo> visibleItemsInfo2 = $this$itemsRetainedForLookahead$iv2.getApproachLayoutInfo().getVisibleItemsInfo();
            LazyStaggeredGridItemInfo found$iv = null;
            int i$iv10 = visibleItemsInfo2.size() - 1;
            while (true) {
                ArrayDeque[] measuredItems5 = measuredItems4;
                if (-1 < i$iv10) {
                    if (visibleItemsInfo2.get(i$iv10).getIndex() <= lastVisibleItemIndex$iv3 || (i$iv10 != 0 && visibleItemsInfo2.get(i$iv10 - 1).getIndex() > lastVisibleItemIndex$iv3)) {
                        i$iv10--;
                        measuredItems4 = measuredItems5;
                    } else {
                        LazyStaggeredGridItemInfo found$iv2 = visibleItemsInfo2.get(i$iv10);
                        found$iv = found$iv2;
                    }
                }
            }
            LazyStaggeredGridItemInfo lastVisibleItem$iv = (LazyStaggeredGridItemInfo) CollectionsKt.last((List) $this$itemsRetainedForLookahead$iv2.getApproachLayoutInfo().getVisibleItemsInfo());
            if (found$iv != null && (i$iv2 = found$iv.getIndex()) <= (iMin = Math.min(lastVisibleItem$iv.getIndex(), itemsCount$iv - 1))) {
                int i$iv11 = i$iv2;
                int extraItemOffset7 = extraItemOffset;
                while (true) {
                    if (list$iv != null) {
                        List $this$fastAny$iv$iv = list$iv;
                        List $this$fastForEach$iv$iv$iv = $this$fastAny$iv$iv;
                        lastVisibleItemIndex$iv = lastVisibleItemIndex$iv3;
                        int lastVisibleItemIndex$iv4 = $this$fastForEach$iv$iv$iv.size();
                        isLookingAhead$iv = isLookingAhead$iv2;
                        int index$iv$iv$iv5 = 0;
                        while (true) {
                            if (index$iv$iv$iv5 < lastVisibleItemIndex$iv4) {
                                int i31 = lastVisibleItemIndex$iv4;
                                List $this$fastForEach$iv$iv$iv2 = $this$fastForEach$iv$iv$iv;
                                if (((LazyStaggeredGridMeasuredItem) $this$fastForEach$iv$iv$iv2.get(index$iv$iv$iv5)).getIndex() == i$iv11) {
                                    z10 = true;
                                } else {
                                    index$iv$iv$iv5++;
                                    lastVisibleItemIndex$iv4 = i31;
                                    $this$fastForEach$iv$iv$iv = $this$fastForEach$iv$iv$iv2;
                                }
                            } else {
                                z10 = false;
                            }
                        }
                        lastVisibleItemIndex$iv2 = z10 ? 1 : 0;
                        if (lastVisibleItemIndex$iv2 == 0) {
                            if (list$iv == null) {
                                list$iv = new ArrayList();
                            }
                            List list$iv2 = list$iv;
                            visibleItemsInfo = $this$itemsRetainedForLookahead$iv2.getApproachLayoutInfo().getVisibleItemsInfo();
                            size2 = visibleItemsInfo.size();
                            index$iv$iv$iv3 = 0;
                            while (true) {
                                if (index$iv$iv$iv3 < size2) {
                                    i10 = size2;
                                    list3 = visibleItemsInfo;
                                    item$iv$iv$iv = list3.get(index$iv$iv$iv3);
                                    if (((LazyStaggeredGridItemInfo) item$iv$iv$iv).getIndex() == i$iv11) {
                                        z9 = true;
                                    } else {
                                        z9 = false;
                                    }
                                    if (z9) {
                                        obj = item$iv$iv$iv;
                                    } else {
                                        index$iv$iv$iv3++;
                                        size2 = i10;
                                        visibleItemsInfo = list3;
                                    }
                                } else {
                                    obj = null;
                                }
                            }
                            lazyStaggeredGridItemInfo = (LazyStaggeredGridItemInfo) obj;
                            if (lazyStaggeredGridItemInfo != null) {
                                lane$iv = lazyStaggeredGridItemInfo.getLane();
                            } else {
                                lane$iv = 0;
                            }
                            $this$itemsRetainedForLookahead$iv = $this$itemsRetainedForLookahead$iv2;
                            long spanRange$iv2 = $this$itemsRetainedForLookahead$iv.m1039getSpanRangelOCCd4c($this$itemsRetainedForLookahead$iv2.getItemProvider(), i$iv11, lane$iv);
                            LazyStaggeredGridMeasuredItem item$iv = $this$itemsRetainedForLookahead$iv.getMeasuredItemProvider().m1048getAndMeasurejy6DScQ(i$iv11, spanRange$iv2);
                            list$iv2.add(item$iv);
                            list$iv = list$iv2;
                            it$iv = $this$itemsRetainedForLookahead$iv.getResolvedSlots().getPositions();
                            if (it$iv.length > lane$iv) {
                                crossAxisOffset$iv = it$iv[lane$iv];
                            } else {
                                crossAxisOffset$iv = 0;
                            }
                            int crossAxis = crossAxisOffset$iv;
                            item$iv.position(extraItemOffset7, crossAxis, mainAxisLayoutSize);
                            extraItemOffset7 += item$iv.getMainAxisSizeWithSpacings();
                        } else {
                            $this$itemsRetainedForLookahead$iv = $this$itemsRetainedForLookahead$iv2;
                        }
                        if (i$iv11 != iMin) {
                            i$iv11++;
                            $this$itemsRetainedForLookahead$iv2 = $this$itemsRetainedForLookahead$iv;
                            firstItemIndices = firstItemIndices;
                            isLookingAhead$iv2 = isLookingAhead$iv;
                            lastVisibleItemIndex$iv3 = lastVisibleItemIndex$iv;
                            preScrollBackScrollDelta = preScrollBackScrollDelta;
                            scrollToBeConsumed = scrollToBeConsumed;
                            contentPadding = contentPadding;
                        } else {
                            extraItemOffset2 = extraItemOffset7;
                        }
                    } else {
                        lastVisibleItemIndex$iv = lastVisibleItemIndex$iv3;
                        isLookingAhead$iv = isLookingAhead$iv2;
                    }
                    if (lastVisibleItemIndex$iv2 == 0) {
                        if (list$iv == null) {
                            list$iv = new ArrayList();
                        }
                        List list$iv3 = list$iv;
                        visibleItemsInfo = $this$itemsRetainedForLookahead$iv2.getApproachLayoutInfo().getVisibleItemsInfo();
                        size2 = visibleItemsInfo.size();
                        index$iv$iv$iv3 = 0;
                        while (true) {
                            if (index$iv$iv$iv3 < size2) {
                                i10 = size2;
                                list3 = visibleItemsInfo;
                                item$iv$iv$iv = list3.get(index$iv$iv$iv3);
                                if (((LazyStaggeredGridItemInfo) item$iv$iv$iv).getIndex() == i$iv11) {
                                    z9 = true;
                                } else {
                                    z9 = false;
                                }
                                if (z9) {
                                    obj = item$iv$iv$iv;
                                } else {
                                    index$iv$iv$iv3++;
                                    size2 = i10;
                                    visibleItemsInfo = list3;
                                }
                            } else {
                                obj = null;
                            }
                        }
                        lazyStaggeredGridItemInfo = (LazyStaggeredGridItemInfo) obj;
                        if (lazyStaggeredGridItemInfo != null) {
                            lane$iv = lazyStaggeredGridItemInfo.getLane();
                        } else {
                            lane$iv = 0;
                        }
                        $this$itemsRetainedForLookahead$iv = $this$itemsRetainedForLookahead$iv2;
                        long spanRange$iv3 = $this$itemsRetainedForLookahead$iv.m1039getSpanRangelOCCd4c($this$itemsRetainedForLookahead$iv2.getItemProvider(), i$iv11, lane$iv);
                        LazyStaggeredGridMeasuredItem item$iv2 = $this$itemsRetainedForLookahead$iv.getMeasuredItemProvider().m1048getAndMeasurejy6DScQ(i$iv11, spanRange$iv3);
                        list$iv3.add(item$iv2);
                        list$iv = list$iv3;
                        it$iv = $this$itemsRetainedForLookahead$iv.getResolvedSlots().getPositions();
                        if (it$iv.length > lane$iv) {
                            crossAxisOffset$iv = it$iv[lane$iv];
                        } else {
                            crossAxisOffset$iv = 0;
                        }
                        int crossAxis2 = crossAxisOffset$iv;
                        item$iv2.position(extraItemOffset7, crossAxis2, mainAxisLayoutSize);
                        extraItemOffset7 += item$iv2.getMainAxisSizeWithSpacings();
                    } else {
                        $this$itemsRetainedForLookahead$iv = $this$itemsRetainedForLookahead$iv2;
                    }
                    if (i$iv11 != iMin) {
                        i$iv11++;
                        $this$itemsRetainedForLookahead$iv2 = $this$itemsRetainedForLookahead$iv;
                        firstItemIndices = firstItemIndices;
                        isLookingAhead$iv2 = isLookingAhead$iv;
                        lastVisibleItemIndex$iv3 = lastVisibleItemIndex$iv;
                        preScrollBackScrollDelta = preScrollBackScrollDelta;
                        scrollToBeConsumed = scrollToBeConsumed;
                        contentPadding = contentPadding;
                    } else {
                        extraItemOffset2 = extraItemOffset7;
                    }
                }
            }
            itemsRetainedForLookahead = list$iv;
            $this$calculateExtraItems$iv = $this$measure;
            beforeVisibleBounds$iv = false;
            $i$f$calculateExtraItems = 0;
            pinnedItems = $this$calculateExtraItems$iv.getPinnedItems();
            list = pinnedItems;
            int extraItemOffset8 = extraItemOffset2;
            extraItemOffset3 = list.size();
            index$iv$iv$iv = 0;
            listEmptyList = null;
            extraItemOffset4 = extraItemOffset8;
            while (index$iv$iv$iv < extraItemOffset3) {
                int i32 = extraItemOffset3;
                List<Integer> list6 = list;
                index$iv2 = ((Number) list6.get(index$iv$iv$iv)).intValue();
                boolean beforeVisibleBounds$iv4 = beforeVisibleBounds$iv;
                int index$iv$iv$iv6 = index$iv$iv$iv;
                index$iv$iv$iv2 = offset2;
                if (index$iv2 >= index$iv$iv$iv2) {
                    if (itemsRetainedForLookahead != null) {
                        $this$fastForEach$iv$iv = itemsRetainedForLookahead;
                        offset2 = index$iv$iv$iv2;
                        itemCount3 = $this$fastForEach$iv$iv.size();
                        $i$f$calculateExtraItems2 = $i$f$calculateExtraItems;
                        $i$f$calculateExtraItems3 = 0;
                        while (true) {
                            if ($i$f$calculateExtraItems3 < itemCount3) {
                                i9 = itemCount3;
                                $this$fastForEach$iv$iv2 = $this$fastForEach$iv$iv;
                                Object item$iv$iv = $this$fastForEach$iv$iv2.get($i$f$calculateExtraItems3);
                                it4 = (LazyStaggeredGridMeasuredItem) item$iv$iv;
                                if (it4.getIndex() == index$iv2) {
                                    z8 = true;
                                } else {
                                    z8 = false;
                                }
                                if (z8) {
                                    z7 = true;
                                } else {
                                    $i$f$calculateExtraItems3++;
                                    itemCount3 = i9;
                                    $this$fastForEach$iv$iv = $this$fastForEach$iv$iv2;
                                }
                            } else {
                                z7 = false;
                            }
                        }
                        itemCount = z7 ? 1 : 0;
                        if (itemCount != 0) {
                            lane2 = $this$measure.getLaneInfo().getLane(index$iv2);
                            switch (lane2) {
                                case -2:
                                case -1:
                                    lane3 = currentItemIndices3.length;
                                    i7 = 0;
                                    while (true) {
                                        if (i7 < lane3) {
                                            itemCount2 = 1;
                                        } else {
                                            element$iv3 = currentItemIndices3[i7];
                                            i8 = lane3;
                                            if (element$iv3 < index$iv2) {
                                                it3 = 1;
                                            } else {
                                                it3 = 0;
                                            }
                                            if (it3 == 0) {
                                                itemCount2 = 0;
                                            } else {
                                                i7++;
                                                lane3 = i8;
                                            }
                                        }
                                        break;
                                    }
                                    break;
                                default:
                                    if (currentItemIndices3[lane2] < index$iv2) {
                                        itemCount2 = 1;
                                    } else {
                                        itemCount2 = 0;
                                    }
                                    break;
                            }
                        } else {
                            itemCount2 = 0;
                        }
                    } else {
                        offset2 = index$iv$iv$iv2;
                        $i$f$calculateExtraItems2 = $i$f$calculateExtraItems;
                    }
                    if (itemCount != 0) {
                        lane2 = $this$measure.getLaneInfo().getLane(index$iv2);
                        switch (lane2) {
                            case -2:
                            case -1:
                                lane3 = currentItemIndices3.length;
                                i7 = 0;
                                while (true) {
                                    if (i7 < lane3) {
                                        itemCount2 = 1;
                                    } else {
                                        element$iv3 = currentItemIndices3[i7];
                                        i8 = lane3;
                                        if (element$iv3 < index$iv2) {
                                            it3 = 1;
                                        } else {
                                            it3 = 0;
                                        }
                                        if (it3 == 0) {
                                            itemCount2 = 0;
                                        } else {
                                            i7++;
                                            lane3 = i8;
                                        }
                                    }
                                    break;
                                }
                                break;
                            default:
                                if (currentItemIndices3[lane2] < index$iv2) {
                                    itemCount2 = 1;
                                } else {
                                    itemCount2 = 0;
                                }
                                break;
                        }
                    } else {
                        itemCount2 = 0;
                    }
                } else {
                    offset2 = index$iv$iv$iv2;
                    $i$f$calculateExtraItems2 = $i$f$calculateExtraItems;
                    itemCount2 = 0;
                }
                if (itemCount2 != 0) {
                    long spanRange$iv4 = $this$calculateExtraItems$iv.m1039getSpanRangelOCCd4c($this$calculateExtraItems$iv.getItemProvider(), index$iv2, 0);
                    if (listEmptyList == null) {
                        Object result$iv2 = new ArrayList();
                        list2 = (List) result$iv2;
                    } else {
                        list2 = listEmptyList;
                    }
                    LazyStaggeredGridMeasuredItem measuredItem$iv2 = $this$calculateExtraItems$iv.getMeasuredItemProvider().m1048getAndMeasurejy6DScQ(index$iv2, spanRange$iv4);
                    measuredItem$iv2.position(extraItemOffset4, 0, mainAxisLayoutSize);
                    extraItemOffset4 += measuredItem$iv2.getMainAxisSizeWithSpacings();
                    list2.add(measuredItem$iv2);
                    listEmptyList = list2;
                }
                index$iv$iv$iv = index$iv$iv$iv6 + 1;
                pinnedItems = pinnedItems;
                extraItemOffset3 = i32;
                list = list6;
                beforeVisibleBounds$iv = beforeVisibleBounds$iv4;
                firstItemOffsets = firstItemOffsets;
                $this$calculateExtraItems$iv = $this$calculateExtraItems$iv;
                $i$f$calculateExtraItems = $i$f$calculateExtraItems2;
            }
            int[] firstItemOffsets5 = firstItemOffsets;
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            List extraItemsAfter = listEmptyList;
            positionedItems = new ArrayList();
            positionedItems.addAll(extraItemsBefore);
            positionedItems.addAll(listCalculateVisibleItems);
            if (itemsRetainedForLookahead != null) {
                positionedItems.addAll(itemsRetainedForLookahead);
            }
            positionedItems.addAll(extraItemsAfter);
            $this$measure.getState().getItemAnimator$foundation().onMeasured((int) consumedScroll, layoutWidth6, layoutHeight6, positionedItems, $this$measure.getMeasuredItemProvider().getKeyIndexMap(), $this$measure.getMeasuredItemProvider(), $this$measure.getIsVertical(), scope$iv.isLookingAhead(), $this$measure.getLaneCount(), $this$measure.getIsInLookaheadScope(), ArraysKt.minOrThrow(firstItemOffsets5), ArraysKt.maxOrThrow(currentItemOffsets) + contentPadding, $this$measure.getCoroutineScope(), $this$measure.getGraphicsContext());
            layoutHeight2 = layoutHeight6;
            if (!scope$iv.isLookingAhead()) {
                disappearingItemsSize = $this$measure.getState().getItemAnimator$foundation().m988getMinSizeToFitDisappearingItemsYbymL2g();
                if (!IntSize.m8074equalsimpl0(disappearingItemsSize, IntSize.INSTANCE.m8081getZeroYbymL2g())) {
                    if ($this$measure.getIsVertical()) {
                        i6 = layoutHeight2;
                    } else {
                        i6 = layoutWidth6;
                    }
                    oldMainAxisSize = i6;
                    layoutWidth3 = ConstraintsKt.m7875constrainWidthK40F9xA($this$measure.getConstraints(), Math.max(layoutWidth6, (int) (disappearingItemsSize >> 32)));
                    layoutHeight4 = ConstraintsKt.m7874constrainHeightK40F9xA($this$measure.getConstraints(), Math.max(layoutHeight2, (int) (disappearingItemsSize & 4294967295L)));
                    if ($this$measure.getIsVertical()) {
                        newMainAxisSize = layoutWidth3;
                    }
                    if (newMainAxisSize != oldMainAxisSize) {
                        size = positionedItems.size();
                        for (index$iv = 0; index$iv < size; index$iv++) {
                            newMainAxisSize = layoutHeight4;
                            LazyStaggeredGridMeasuredItem it8 = (LazyStaggeredGridMeasuredItem) positionedItems.get(index$iv);
                            it8.updateMainAxisLayoutSize(newMainAxisSize);
                        }
                        newMainAxisSize = layoutHeight4;
                    }
                    newMainAxisSize = layoutHeight4;
                    layoutWidth2 = layoutWidth3;
                    layoutHeight3 = layoutHeight4;
                }
                length = currentItemOffsets.length;
                i4 = 0;
                while (true) {
                    if (i4 < length) {
                        element$iv2 = currentItemOffsets[i4];
                        if (element$iv2 > $this$measure.getMainAxisAvailableSize()) {
                            it2 = 1;
                        } else {
                            it2 = 0;
                        }
                        if (it2 != 0) {
                            z5 = true;
                        } else {
                            i4++;
                        }
                    } else {
                        z5 = false;
                    }
                }
                if (z5) {
                    canScrollForward = true;
                } else {
                    length2 = currentItemIndices3.length;
                    i5 = 0;
                    while (true) {
                        if (i5 < length2) {
                            element$iv = currentItemIndices3[i5];
                            if (element$iv < offset2 - 1) {
                                it = 1;
                            } else {
                                it = 0;
                            }
                            if (it == 0) {
                                z6 = false;
                            } else {
                                i5++;
                            }
                        } else {
                            z6 = true;
                        }
                    }
                    if (z6) {
                        canScrollForward = true;
                    } else {
                        canScrollForward = false;
                    }
                }
                MeasureResult measureResultLayout$default2 = MeasureScope.layout$default(scope$iv, layoutWidth2, layoutHeight3, null, new Function1() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return LazyStaggeredGridMeasureKt.measure$lambda$0$37($this$measure, positionedItems, scope$iv, (Placeable.PlacementScope) obj2);
                    }
                }, 4, null);
                int height$iv2 = layoutHeight3;
                int width$iv2 = layoutWidth2;
                int[] firstItemIndices6 = firstItemIndices;
                int itemCount5 = offset2;
                return new LazyStaggeredGridMeasureResult(firstItemIndices6, firstItemOffsets5, consumedScroll, measureResultLayout$default2, scrollBackAmount, canScrollForward, $this$measure.getIsVertical(), remeasureNeeded4, $this$measure.getResolvedSlots(), $this$measure.getItemProvider().getSpanProvider(), scope$iv, itemCount5, listCalculateVisibleItems, IntSize.m8071constructorimpl((((long) width$iv2) << 32) | (((long) height$iv2) & 4294967295L)), minOffset, maxOffset, $this$measure.getBeforeContentPadding(), $this$measure.getAfterContentPadding(), $this$measure.getMainAxisSpacing(), $this$measure.getCoroutineScope(), null);
            }
            layoutWidth2 = layoutWidth6;
            layoutHeight3 = layoutHeight2;
            length = currentItemOffsets.length;
            i4 = 0;
            while (true) {
                if (i4 < length) {
                    element$iv2 = currentItemOffsets[i4];
                    if (element$iv2 > $this$measure.getMainAxisAvailableSize()) {
                        it2 = 1;
                    } else {
                        it2 = 0;
                    }
                    if (it2 != 0) {
                        z5 = true;
                    } else {
                        i4++;
                    }
                } else {
                    z5 = false;
                }
            }
            if (z5) {
                length2 = currentItemIndices3.length;
                i5 = 0;
                while (true) {
                    if (i5 < length2) {
                        element$iv = currentItemIndices3[i5];
                        if (element$iv < offset2 - 1) {
                            it = 1;
                        } else {
                            it = 0;
                        }
                        if (it == 0) {
                            z6 = false;
                        } else {
                            i5++;
                        }
                    } else {
                        z6 = true;
                    }
                }
                if (z6) {
                    canScrollForward = false;
                } else {
                    canScrollForward = true;
                }
            } else {
                canScrollForward = true;
            }
            MeasureResult measureResultLayout$default3 = MeasureScope.layout$default(scope$iv, layoutWidth2, layoutHeight3, null, new Function1() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return LazyStaggeredGridMeasureKt.measure$lambda$0$37($this$measure, positionedItems, scope$iv, (Placeable.PlacementScope) obj2);
                }
            }, 4, null);
            int height$iv3 = layoutHeight3;
            int width$iv3 = layoutWidth2;
            int[] firstItemIndices7 = firstItemIndices;
            int itemCount6 = offset2;
            return new LazyStaggeredGridMeasureResult(firstItemIndices7, firstItemOffsets5, consumedScroll, measureResultLayout$default3, scrollBackAmount, canScrollForward, $this$measure.getIsVertical(), remeasureNeeded4, $this$measure.getResolvedSlots(), $this$measure.getItemProvider().getSpanProvider(), scope$iv, itemCount6, listCalculateVisibleItems, IntSize.m8071constructorimpl((((long) width$iv3) << 32) | (((long) height$iv3) & 4294967295L)), minOffset, maxOffset, $this$measure.getBeforeContentPadding(), $this$measure.getAfterContentPadding(), $this$measure.getMainAxisSpacing(), $this$measure.getCoroutineScope(), null);
        }
        extraItemOffset = extraItemOffset6;
        extraItemOffset2 = extraItemOffset;
        itemsRetainedForLookahead = list$iv;
        $this$calculateExtraItems$iv = $this$measure;
        beforeVisibleBounds$iv = false;
        $i$f$calculateExtraItems = 0;
        pinnedItems = $this$calculateExtraItems$iv.getPinnedItems();
        list = pinnedItems;
        int extraItemOffset9 = extraItemOffset2;
        extraItemOffset3 = list.size();
        index$iv$iv$iv = 0;
        listEmptyList = null;
        extraItemOffset4 = extraItemOffset9;
        while (index$iv$iv$iv < extraItemOffset3) {
            int i33 = extraItemOffset3;
            List<Integer> list7 = list;
            index$iv2 = ((Number) list7.get(index$iv$iv$iv)).intValue();
            boolean beforeVisibleBounds$iv5 = beforeVisibleBounds$iv;
            int index$iv$iv$iv7 = index$iv$iv$iv;
            index$iv$iv$iv2 = offset2;
            if (index$iv2 >= index$iv$iv$iv2) {
                if (itemsRetainedForLookahead != null) {
                    $this$fastForEach$iv$iv = itemsRetainedForLookahead;
                    offset2 = index$iv$iv$iv2;
                    itemCount3 = $this$fastForEach$iv$iv.size();
                    $i$f$calculateExtraItems2 = $i$f$calculateExtraItems;
                    $i$f$calculateExtraItems3 = 0;
                    while (true) {
                        if ($i$f$calculateExtraItems3 < itemCount3) {
                            i9 = itemCount3;
                            $this$fastForEach$iv$iv2 = $this$fastForEach$iv$iv;
                            Object item$iv$iv2 = $this$fastForEach$iv$iv2.get($i$f$calculateExtraItems3);
                            it4 = (LazyStaggeredGridMeasuredItem) item$iv$iv2;
                            if (it4.getIndex() == index$iv2) {
                                z8 = true;
                            } else {
                                z8 = false;
                            }
                            if (z8) {
                                z7 = true;
                            } else {
                                $i$f$calculateExtraItems3++;
                                itemCount3 = i9;
                                $this$fastForEach$iv$iv = $this$fastForEach$iv$iv2;
                            }
                        } else {
                            z7 = false;
                        }
                    }
                    if (z7) {
                    }
                    if (itemCount != 0) {
                        lane2 = $this$measure.getLaneInfo().getLane(index$iv2);
                        switch (lane2) {
                            case -2:
                            case -1:
                                lane3 = currentItemIndices3.length;
                                i7 = 0;
                                while (true) {
                                    if (i7 < lane3) {
                                        itemCount2 = 1;
                                    } else {
                                        element$iv3 = currentItemIndices3[i7];
                                        i8 = lane3;
                                        if (element$iv3 < index$iv2) {
                                            it3 = 1;
                                        } else {
                                            it3 = 0;
                                        }
                                        if (it3 == 0) {
                                            itemCount2 = 0;
                                        } else {
                                            i7++;
                                            lane3 = i8;
                                        }
                                    }
                                    break;
                                }
                                break;
                            default:
                                if (currentItemIndices3[lane2] < index$iv2) {
                                    itemCount2 = 1;
                                } else {
                                    itemCount2 = 0;
                                }
                                break;
                        }
                    } else {
                        itemCount2 = 0;
                    }
                } else {
                    offset2 = index$iv$iv$iv2;
                    $i$f$calculateExtraItems2 = $i$f$calculateExtraItems;
                }
                if (itemCount != 0) {
                    lane2 = $this$measure.getLaneInfo().getLane(index$iv2);
                    switch (lane2) {
                        case -2:
                        case -1:
                            lane3 = currentItemIndices3.length;
                            i7 = 0;
                            while (true) {
                                if (i7 < lane3) {
                                    itemCount2 = 1;
                                } else {
                                    element$iv3 = currentItemIndices3[i7];
                                    i8 = lane3;
                                    if (element$iv3 < index$iv2) {
                                        it3 = 1;
                                    } else {
                                        it3 = 0;
                                    }
                                    if (it3 == 0) {
                                        itemCount2 = 0;
                                    } else {
                                        i7++;
                                        lane3 = i8;
                                    }
                                }
                                break;
                            }
                            break;
                        default:
                            if (currentItemIndices3[lane2] < index$iv2) {
                                itemCount2 = 1;
                            } else {
                                itemCount2 = 0;
                            }
                            break;
                    }
                } else {
                    itemCount2 = 0;
                }
            } else {
                offset2 = index$iv$iv$iv2;
                $i$f$calculateExtraItems2 = $i$f$calculateExtraItems;
                itemCount2 = 0;
            }
            if (itemCount2 != 0) {
                long spanRange$iv5 = $this$calculateExtraItems$iv.m1039getSpanRangelOCCd4c($this$calculateExtraItems$iv.getItemProvider(), index$iv2, 0);
                if (listEmptyList == null) {
                    Object result$iv3 = new ArrayList();
                    list2 = (List) result$iv3;
                } else {
                    list2 = listEmptyList;
                }
                LazyStaggeredGridMeasuredItem measuredItem$iv3 = $this$calculateExtraItems$iv.getMeasuredItemProvider().m1048getAndMeasurejy6DScQ(index$iv2, spanRange$iv5);
                measuredItem$iv3.position(extraItemOffset4, 0, mainAxisLayoutSize);
                extraItemOffset4 += measuredItem$iv3.getMainAxisSizeWithSpacings();
                list2.add(measuredItem$iv3);
                listEmptyList = list2;
            }
            index$iv$iv$iv = index$iv$iv$iv7 + 1;
            pinnedItems = pinnedItems;
            extraItemOffset3 = i33;
            list = list7;
            beforeVisibleBounds$iv = beforeVisibleBounds$iv5;
            firstItemOffsets = firstItemOffsets;
            $this$calculateExtraItems$iv = $this$calculateExtraItems$iv;
            $i$f$calculateExtraItems = $i$f$calculateExtraItems2;
        }
        int[] firstItemOffsets6 = firstItemOffsets;
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List extraItemsAfter2 = listEmptyList;
        positionedItems = new ArrayList();
        positionedItems.addAll(extraItemsBefore);
        positionedItems.addAll(listCalculateVisibleItems);
        if (itemsRetainedForLookahead != null) {
            positionedItems.addAll(itemsRetainedForLookahead);
        }
        positionedItems.addAll(extraItemsAfter2);
        $this$measure.getState().getItemAnimator$foundation().onMeasured((int) consumedScroll, layoutWidth6, layoutHeight6, positionedItems, $this$measure.getMeasuredItemProvider().getKeyIndexMap(), $this$measure.getMeasuredItemProvider(), $this$measure.getIsVertical(), scope$iv.isLookingAhead(), $this$measure.getLaneCount(), $this$measure.getIsInLookaheadScope(), ArraysKt.minOrThrow(firstItemOffsets6), ArraysKt.maxOrThrow(currentItemOffsets) + contentPadding, $this$measure.getCoroutineScope(), $this$measure.getGraphicsContext());
        layoutHeight2 = layoutHeight6;
        if (!scope$iv.isLookingAhead()) {
            disappearingItemsSize = $this$measure.getState().getItemAnimator$foundation().m988getMinSizeToFitDisappearingItemsYbymL2g();
            if (!IntSize.m8074equalsimpl0(disappearingItemsSize, IntSize.INSTANCE.m8081getZeroYbymL2g())) {
                if ($this$measure.getIsVertical()) {
                    i6 = layoutHeight2;
                } else {
                    i6 = layoutWidth6;
                }
                oldMainAxisSize = i6;
                layoutWidth3 = ConstraintsKt.m7875constrainWidthK40F9xA($this$measure.getConstraints(), Math.max(layoutWidth6, (int) (disappearingItemsSize >> 32)));
                layoutHeight4 = ConstraintsKt.m7874constrainHeightK40F9xA($this$measure.getConstraints(), Math.max(layoutHeight2, (int) (disappearingItemsSize & 4294967295L)));
                if ($this$measure.getIsVertical()) {
                    newMainAxisSize = layoutWidth3;
                }
                if (newMainAxisSize != oldMainAxisSize) {
                    size = positionedItems.size();
                    while (index$iv < size) {
                        newMainAxisSize = layoutHeight4;
                        LazyStaggeredGridMeasuredItem it9 = (LazyStaggeredGridMeasuredItem) positionedItems.get(index$iv);
                        it9.updateMainAxisLayoutSize(newMainAxisSize);
                    }
                    newMainAxisSize = layoutHeight4;
                }
                newMainAxisSize = layoutHeight4;
                layoutWidth2 = layoutWidth3;
                layoutHeight3 = layoutHeight4;
            }
            length = currentItemOffsets.length;
            i4 = 0;
            while (true) {
                if (i4 < length) {
                    element$iv2 = currentItemOffsets[i4];
                    if (element$iv2 > $this$measure.getMainAxisAvailableSize()) {
                        it2 = 1;
                    } else {
                        it2 = 0;
                    }
                    if (it2 != 0) {
                        z5 = true;
                    } else {
                        i4++;
                    }
                } else {
                    z5 = false;
                }
            }
            if (z5) {
                length2 = currentItemIndices3.length;
                i5 = 0;
                while (true) {
                    if (i5 < length2) {
                        element$iv = currentItemIndices3[i5];
                        if (element$iv < offset2 - 1) {
                            it = 1;
                        } else {
                            it = 0;
                        }
                        if (it == 0) {
                            z6 = false;
                        } else {
                            i5++;
                        }
                    } else {
                        z6 = true;
                    }
                }
                if (z6) {
                    canScrollForward = false;
                } else {
                    canScrollForward = true;
                }
            } else {
                canScrollForward = true;
            }
            MeasureResult measureResultLayout$default4 = MeasureScope.layout$default(scope$iv, layoutWidth2, layoutHeight3, null, new Function1() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return LazyStaggeredGridMeasureKt.measure$lambda$0$37($this$measure, positionedItems, scope$iv, (Placeable.PlacementScope) obj2);
                }
            }, 4, null);
            int height$iv4 = layoutHeight3;
            int width$iv4 = layoutWidth2;
            int[] firstItemIndices8 = firstItemIndices;
            int itemCount7 = offset2;
            return new LazyStaggeredGridMeasureResult(firstItemIndices8, firstItemOffsets6, consumedScroll, measureResultLayout$default4, scrollBackAmount, canScrollForward, $this$measure.getIsVertical(), remeasureNeeded4, $this$measure.getResolvedSlots(), $this$measure.getItemProvider().getSpanProvider(), scope$iv, itemCount7, listCalculateVisibleItems, IntSize.m8071constructorimpl((((long) width$iv4) << 32) | (((long) height$iv4) & 4294967295L)), minOffset, maxOffset, $this$measure.getBeforeContentPadding(), $this$measure.getAfterContentPadding(), $this$measure.getMainAxisSpacing(), $this$measure.getCoroutineScope(), null);
        }
        layoutWidth2 = layoutWidth6;
        layoutHeight3 = layoutHeight2;
        length = currentItemOffsets.length;
        i4 = 0;
        while (true) {
            if (i4 < length) {
                element$iv2 = currentItemOffsets[i4];
                if (element$iv2 > $this$measure.getMainAxisAvailableSize()) {
                    it2 = 1;
                } else {
                    it2 = 0;
                }
                if (it2 != 0) {
                    z5 = true;
                } else {
                    i4++;
                }
            } else {
                z5 = false;
            }
        }
        if (z5) {
            length2 = currentItemIndices3.length;
            i5 = 0;
            while (true) {
                if (i5 < length2) {
                    element$iv = currentItemIndices3[i5];
                    if (element$iv < offset2 - 1) {
                        it = 1;
                    } else {
                        it = 0;
                    }
                    if (it == 0) {
                        z6 = false;
                    } else {
                        i5++;
                    }
                } else {
                    z6 = true;
                }
            }
            if (z6) {
                canScrollForward = false;
            } else {
                canScrollForward = true;
            }
        } else {
            canScrollForward = true;
        }
        MeasureResult measureResultLayout$default5 = MeasureScope.layout$default(scope$iv, layoutWidth2, layoutHeight3, null, new Function1() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return LazyStaggeredGridMeasureKt.measure$lambda$0$37($this$measure, positionedItems, scope$iv, (Placeable.PlacementScope) obj2);
            }
        }, 4, null);
        int height$iv5 = layoutHeight3;
        int width$iv5 = layoutWidth2;
        int[] firstItemIndices9 = firstItemIndices;
        int itemCount8 = offset2;
        return new LazyStaggeredGridMeasureResult(firstItemIndices9, firstItemOffsets6, consumedScroll, measureResultLayout$default5, scrollBackAmount, canScrollForward, $this$measure.getIsVertical(), remeasureNeeded4, $this$measure.getResolvedSlots(), $this$measure.getItemProvider().getSpanProvider(), scope$iv, itemCount8, listCalculateVisibleItems, IntSize.m8071constructorimpl((((long) width$iv5) << 32) | (((long) height$iv5) & 4294967295L)), minOffset, maxOffset, $this$measure.getBeforeContentPadding(), $this$measure.getAfterContentPadding(), $this$measure.getMainAxisSpacing(), $this$measure.getCoroutineScope(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measure$lambda$0$0(Placeable.PlacementScope $this$layout) {
        return Unit.INSTANCE;
    }

    private static final boolean measure$lambda$0$hasSpaceBeforeFirst(int[] firstItemIndices, int[] firstItemOffsets, LazyStaggeredGridMeasureContext $this_measure) {
        int length = firstItemIndices.length;
        for (int lane = 0; lane < length; lane++) {
            int itemIndex = firstItemIndices[lane];
            int itemOffset = firstItemOffsets[lane];
            if (itemOffset < Math.max(-$this_measure.getMainAxisSpacing(), 0) && itemIndex > 0) {
                return true;
            }
        }
        return false;
    }

    private static final boolean measure$lambda$0$misalignedStart(int[] firstItemIndices, LazyStaggeredGridMeasureContext $this_measure, int[] firstItemOffsets, int referenceLane) {
        int lane = 0;
        int length = firstItemIndices.length;
        while (true) {
            boolean misalignedOffsets = false;
            if (lane < length) {
                if (findPreviousItemIndex($this_measure, firstItemIndices[lane], lane) == -1 && firstItemOffsets[lane] != firstItemOffsets[referenceLane]) {
                    misalignedOffsets = true;
                }
                if (misalignedOffsets) {
                    return true;
                }
                lane++;
            } else {
                int length2 = firstItemIndices.length;
                for (int lane2 = 0; lane2 < length2; lane2++) {
                    boolean moreItemsInOtherLanes = findPreviousItemIndex($this_measure, firstItemIndices[lane2], lane2) != -1 && firstItemOffsets[lane2] >= firstItemOffsets[referenceLane];
                    if (moreItemsInOtherLanes) {
                        return true;
                    }
                }
                int firstItemLane = $this_measure.getLaneInfo().getLane(0);
                return (firstItemLane == 0 || firstItemLane == -1 || firstItemLane == -2) ? false : true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measure$lambda$0$37(final LazyStaggeredGridMeasureContext $this_measure, final List $positionedItems, final LazyLayoutMeasureScope $this_withDebugLogging, Placeable.PlacementScope $this$layout) {
        $this$layout.withMotionFrameOfReferencePlacement(new Function1() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LazyStaggeredGridMeasureKt.measure$lambda$0$37$0($positionedItems, $this_measure, $this_withDebugLogging, (Placeable.PlacementScope) obj);
            }
        });
        ObservableScopeInvalidator.m1006attachToScopeimpl($this_measure.getState().m1052getPlacementScopeInvalidatorzYiylxw$foundation());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measure$lambda$0$37$0(List $positionedItems, LazyStaggeredGridMeasureContext $this_measure, LazyLayoutMeasureScope $this_withDebugLogging, Placeable.PlacementScope $this$withMotionFrameOfReferencePlacement) {
        int size = $positionedItems.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $positionedItems.get(index$iv);
            LazyStaggeredGridMeasuredItem item = (LazyStaggeredGridMeasuredItem) item$iv;
            item.place($this$withMotionFrameOfReferencePlacement, $this_measure, $this_withDebugLogging.isLookingAhead());
        }
        return Unit.INSTANCE;
    }

    private static final List<LazyStaggeredGridMeasuredItem> calculateVisibleItems(LazyStaggeredGridMeasureContext $this$calculateVisibleItems, ArrayDeque<LazyStaggeredGridMeasuredItem>[] arrayDequeArr, int[] itemScrollOffsets, int mainAxisLayoutSize, int minOffset, int maxOffset) {
        boolean z;
        ArrayDeque<LazyStaggeredGridMeasuredItem>[] arrayDequeArr2 = arrayDequeArr;
        int[] iArr = itemScrollOffsets;
        int size = 0;
        for (ArrayDeque<LazyStaggeredGridMeasuredItem> arrayDeque : arrayDequeArr2) {
            size += arrayDeque.size();
        }
        ArrayList positionedItems = new ArrayList(size);
        while (true) {
            int length = arrayDequeArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                }
                if (!arrayDequeArr[i].isEmpty()) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                return positionedItems;
            }
            int result$iv = -1;
            int min$iv = Integer.MAX_VALUE;
            int length2 = arrayDequeArr.length;
            for (int i$iv = 0; i$iv < length2; i$iv++) {
                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemFirstOrNull = arrayDequeArr[i$iv].firstOrNull();
                int value$iv = lazyStaggeredGridMeasuredItemFirstOrNull != null ? lazyStaggeredGridMeasuredItemFirstOrNull.getIndex() : Integer.MAX_VALUE;
                if (min$iv > value$iv) {
                    min$iv = value$iv;
                    result$iv = i$iv;
                }
            }
            LazyStaggeredGridMeasuredItem item = arrayDequeArr2[result$iv].removeFirst();
            if (item.getLane() == result$iv) {
                long spanRange = SpanRange.m1054constructorimpl(item.getLane(), item.getSpan());
                int mainAxisOffset = m1044maxInRangejy6DScQ(iArr, spanRange);
                int crossAxisOffset = $this$calculateVisibleItems.getResolvedSlots().getPositions()[result$iv];
                int maxEdge = item.getMainAxisSize() + mainAxisOffset;
                if (maxEdge >= minOffset && mainAxisOffset <= maxOffset) {
                    item.position(mainAxisOffset, crossAxisOffset, mainAxisLayoutSize);
                    positionedItems.add(item);
                }
                int i2 = (int) (spanRange & 4294967295L);
                for (int i$iv2 = (int) (spanRange >> 32); i$iv2 < i2; i$iv2++) {
                    int lane = i$iv2;
                    itemScrollOffsets[lane] = mainAxisOffset + item.getMainAxisSizeWithSpacings();
                }
                arrayDequeArr2 = arrayDequeArr;
                iArr = itemScrollOffsets;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:36:0x00b0  */
    private static final List<LazyStaggeredGridMeasuredItem> itemsRetainedForLookahead(LazyStaggeredGridMeasureContext $this$itemsRetainedForLookahead, int lastVisibleItemIndex, int itemsCount, boolean isLookingAhead, Function2<? super LazyStaggeredGridMeasuredItem, ? super Integer, Unit> function2) {
        int i;
        int iMin;
        boolean z;
        Object it$iv;
        boolean z2;
        List list = null;
        if (isLookingAhead && $this$itemsRetainedForLookahead.getApproachLayoutInfo() != null && !$this$itemsRetainedForLookahead.getApproachLayoutInfo().getVisibleItemsInfo().isEmpty()) {
            List<LazyStaggeredGridItemInfo> visibleItemsInfo = $this$itemsRetainedForLookahead.getApproachLayoutInfo().getVisibleItemsInfo();
            LazyStaggeredGridItemInfo found = null;
            boolean z3 = true;
            for (int i2 = visibleItemsInfo.size() - 1; -1 < i2; i2--) {
                if (visibleItemsInfo.get(i2).getIndex() > lastVisibleItemIndex && (i2 == 0 || visibleItemsInfo.get(i2 - 1).getIndex() <= lastVisibleItemIndex)) {
                    LazyStaggeredGridItemInfo found2 = visibleItemsInfo.get(i2);
                    found = found2;
                    break;
                }
            }
            LazyStaggeredGridItemInfo lastVisibleItem = (LazyStaggeredGridItemInfo) CollectionsKt.last((List) $this$itemsRetainedForLookahead.getApproachLayoutInfo().getVisibleItemsInfo());
            if (found != null && (i = found.getIndex()) <= (iMin = Math.min(lastVisibleItem.getIndex(), itemsCount - 1))) {
                while (true) {
                    if (list != null) {
                        List $this$fastAny$iv = list;
                        int index$iv$iv = 0;
                        int size = $this$fastAny$iv.size();
                        while (true) {
                            if (index$iv$iv >= size) {
                                z2 = false;
                                break;
                            }
                            Object item$iv$iv = $this$fastAny$iv.get(index$iv$iv);
                            if (((LazyStaggeredGridMeasuredItem) item$iv$iv).getIndex() == i ? z3 : false) {
                                z2 = z3;
                                break;
                            }
                            index$iv$iv++;
                        }
                        if (z2 == z3) {
                            z = z3;
                        } else {
                            z = false;
                        }
                    } else {
                        z = false;
                    }
                    if (!z) {
                        if (list == null) {
                            list = new ArrayList();
                        }
                        List<LazyStaggeredGridItemInfo> visibleItemsInfo2 = $this$itemsRetainedForLookahead.getApproachLayoutInfo().getVisibleItemsInfo();
                        int index$iv$iv2 = 0;
                        int size2 = visibleItemsInfo2.size();
                        while (true) {
                            if (index$iv$iv2 >= size2) {
                                it$iv = null;
                                break;
                            }
                            Object item$iv$iv2 = visibleItemsInfo2.get(index$iv$iv2);
                            it$iv = item$iv$iv2;
                            if (((LazyStaggeredGridItemInfo) it$iv).getIndex() == i) {
                                break;
                            }
                            index$iv$iv2++;
                        }
                        LazyStaggeredGridItemInfo lazyStaggeredGridItemInfo = (LazyStaggeredGridItemInfo) it$iv;
                        int lane = lazyStaggeredGridItemInfo != null ? lazyStaggeredGridItemInfo.getLane() : 0;
                        long spanRange = $this$itemsRetainedForLookahead.m1039getSpanRangelOCCd4c($this$itemsRetainedForLookahead.getItemProvider(), i, lane);
                        LazyStaggeredGridMeasuredItem item = $this$itemsRetainedForLookahead.getMeasuredItemProvider().m1048getAndMeasurejy6DScQ(i, spanRange);
                        list.add(item);
                        int[] it = $this$itemsRetainedForLookahead.getResolvedSlots().getPositions();
                        int crossAxisOffset = it.length > lane ? it[lane] : 0;
                        function2.invoke(item, Integer.valueOf(crossAxisOffset));
                    }
                    if (i == iMin) {
                        break;
                    }
                    i++;
                    z3 = true;
                }
            }
        }
        return list;
    }

    private static final List<LazyStaggeredGridMeasuredItem> calculateExtraItems(LazyStaggeredGridMeasureContext $this$calculateExtraItems, Function1<? super LazyStaggeredGridMeasuredItem, Unit> function1, Function1<? super Integer, Boolean> function2, boolean beforeVisibleBounds) {
        Function1<? super Integer, Boolean> function3 = function2;
        int $i$f$calculateExtraItems = 0;
        ArrayList arrayList = null;
        List<Integer> pinnedItems = $this$calculateExtraItems.getPinnedItems();
        if (beforeVisibleBounds) {
            int size = pinnedItems.size() - 1;
            if (size >= 0) {
                while (true) {
                    int index$iv$iv = size;
                    size--;
                    Object item$iv$iv = pinnedItems.get(index$iv$iv);
                    int index = ((Number) item$iv$iv).intValue();
                    if (function3.invoke(Integer.valueOf(index)).booleanValue()) {
                        ArrayList arrayList2 = arrayList;
                        long spanRange = $this$calculateExtraItems.m1039getSpanRangelOCCd4c($this$calculateExtraItems.getItemProvider(), index, 0);
                        ArrayList arrayList3 = arrayList2 == null ? new ArrayList() : arrayList2;
                        LazyStaggeredGridMeasuredItem measuredItem = $this$calculateExtraItems.getMeasuredItemProvider().m1048getAndMeasurejy6DScQ(index, spanRange);
                        function1.invoke(measuredItem);
                        arrayList3.add(measuredItem);
                        arrayList = arrayList3;
                    }
                    if (size < 0) {
                        break;
                    }
                    $i$f$calculateExtraItems = $i$f$calculateExtraItems;
                    pinnedItems = pinnedItems;
                }
            }
        } else {
            List<Integer> list = pinnedItems;
            int index$iv$iv2 = 0;
            int size2 = list.size();
            while (index$iv$iv2 < size2) {
                Object item$iv$iv2 = list.get(index$iv$iv2);
                int index2 = ((Number) item$iv$iv2).intValue();
                if (function3.invoke(Integer.valueOf(index2)).booleanValue()) {
                    long spanRange2 = $this$calculateExtraItems.m1039getSpanRangelOCCd4c($this$calculateExtraItems.getItemProvider(), index2, 0);
                    if (arrayList == null) {
                        Object result = new ArrayList();
                        arrayList = (List) result;
                    }
                    LazyStaggeredGridMeasuredItem measuredItem2 = $this$calculateExtraItems.getMeasuredItemProvider().m1048getAndMeasurejy6DScQ(index2, spanRange2);
                    function1.invoke(measuredItem2);
                    arrayList.add(measuredItem2);
                }
                index$iv$iv2++;
                function3 = function2;
                list = list;
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    static /* synthetic */ void fastForEach$default(List $this$fastForEach_u24default, boolean reverse, Function1 action, int i, Object obj) {
        if ((i & 1) != 0) {
            reverse = false;
        }
        if (reverse) {
            int size = $this$fastForEach_u24default.size() - 1;
            if (size < 0) {
                return;
            }
            do {
                int index$iv = size;
                size--;
                Object item$iv = $this$fastForEach_u24default.get(index$iv);
                action.invoke(item$iv);
            } while (size >= 0);
            return;
        }
        int size2 = $this$fastForEach_u24default.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = $this$fastForEach_u24default.get(index$iv2);
            action.invoke(item$iv2);
        }
    }

    private static final <T> void fastForEach(List<? extends T> list, boolean reverse, Function1<? super T, Unit> function1) {
        if (reverse) {
            int size = list.size() - 1;
            if (size < 0) {
                return;
            }
            do {
                int index$iv = size;
                size--;
                Object item$iv = list.get(index$iv);
                function1.invoke(item$iv);
            } while (size >= 0);
            return;
        }
        int size2 = list.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = list.get(index$iv2);
            function1.invoke(item$iv2);
        }
    }

    /* JADX INFO: renamed from: forEach-nIS5qE8, reason: not valid java name */
    private static final void m1043forEachnIS5qE8(long j, Function1<? super Integer, Unit> function1) {
        int i = (int) (4294967295L & j);
        for (int i2 = (int) (j >> 32); i2 < i; i2++) {
            function1.invoke(Integer.valueOf(i2));
        }
    }

    private static final void offsetBy(int[] $this$offsetBy, int delta) {
        int length = $this$offsetBy.length;
        for (int i = 0; i < length; i++) {
            $this$offsetBy[i] = $this$offsetBy[i] + delta;
        }
    }

    /* JADX INFO: renamed from: maxInRange-jy6DScQ, reason: not valid java name */
    private static final int m1044maxInRangejy6DScQ(int[] $this$maxInRange_u2djy6DScQ, long j) {
        int max = Integer.MIN_VALUE;
        int i = (int) (4294967295L & j);
        for (int i$iv = (int) (j >> 32); i$iv < i; i$iv++) {
            int it = i$iv;
            max = Math.max(max, $this$maxInRange_u2djy6DScQ[it]);
        }
        return max;
    }

    public static /* synthetic */ int indexOfMinValue$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Integer.MIN_VALUE;
        }
        return indexOfMinValue(iArr, i);
    }

    public static final int indexOfMinValue(int[] $this$indexOfMinValue, int minBound) {
        int result = -1;
        int min = Integer.MAX_VALUE;
        int length = $this$indexOfMinValue.length;
        for (int i = 0; i < length; i++) {
            int i2 = minBound + 1;
            int i3 = $this$indexOfMinValue[i];
            boolean z = false;
            if (i2 <= i3 && i3 < min) {
                z = true;
            }
            if (z) {
                min = $this$indexOfMinValue[i];
                result = i;
            }
        }
        return result;
    }

    private static final <T> int indexOfMinBy(T[] tArr, Function1<? super T, Integer> function1) {
        int result = -1;
        int min = Integer.MAX_VALUE;
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            int value = function1.invoke(tArr[i]).intValue();
            if (min > value) {
                min = value;
                result = i;
            }
        }
        return result;
    }

    private static final int indexOfMaxValue(int[] $this$indexOfMaxValue) {
        int result = -1;
        int max = Integer.MIN_VALUE;
        int length = $this$indexOfMaxValue.length;
        for (int i = 0; i < length; i++) {
            if (max < $this$indexOfMaxValue[i]) {
                max = $this$indexOfMaxValue[i];
                result = i;
            }
        }
        return result;
    }

    private static final int[] transform(int[] $this$transform, Function1<? super Integer, Integer> function1) {
        int length = $this$transform.length;
        for (int i = 0; i < length; i++) {
            $this$transform[i] = function1.invoke(Integer.valueOf($this$transform[i])).intValue();
        }
        return $this$transform;
    }

    private static final void ensureIndicesInRange(LazyStaggeredGridMeasureContext $this$ensureIndicesInRange, int[] indices, int itemCount) {
        int index$iv;
        int length = indices.length - 1;
        if (length >= 0) {
            do {
                int i = length;
                length--;
                while (true) {
                    if (indices[i] < itemCount && $this$ensureIndicesInRange.getLaneInfo().assignedToLane(indices[i], i)) {
                        break;
                    } else {
                        indices[i] = findPreviousItemIndex($this$ensureIndicesInRange, indices[i], i);
                    }
                }
                if (indices[i] >= 0) {
                    int itemIndex = indices[i];
                    if (!$this$ensureIndicesInRange.isFullSpan($this$ensureIndicesInRange.getItemProvider(), itemIndex)) {
                        if ($this$ensureIndicesInRange.getLaneInfo().getLane(itemIndex) != -2) {
                            index$iv = i;
                        } else {
                            index$iv = 0;
                            int length2 = indices.length;
                            while (true) {
                                if (index$iv < length2) {
                                    int it = indices[index$iv];
                                    if (it == itemIndex) {
                                        break;
                                    } else {
                                        index$iv++;
                                    }
                                } else {
                                    index$iv = -1;
                                    break;
                                }
                            }
                            int lane = index$iv + 1;
                            if (lane <= i) {
                                while (true) {
                                    if (indices[lane] == itemIndex) {
                                        indices[lane] = findPreviousItemIndex($this$ensureIndicesInRange, itemIndex, lane);
                                    }
                                    if (lane == i) {
                                        break;
                                    } else {
                                        lane++;
                                    }
                                }
                            }
                        }
                        $this$ensureIndicesInRange.getLaneInfo().setLane(itemIndex, index$iv);
                    }
                }
            } while (length >= 0);
        }
    }

    private static final int findPreviousItemIndex(LazyStaggeredGridMeasureContext $this$findPreviousItemIndex, int item, int lane) {
        return $this$findPreviousItemIndex.getLaneInfo().findPreviousItemIndex(item, lane);
    }
}
