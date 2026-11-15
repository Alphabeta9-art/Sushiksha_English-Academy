package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsStateKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyStaggeredGridMeasurePolicy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001ae\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0001¢\u0006\u0004\b\u0016\u0010\u0017\u001a!\u0010\u0018\u001a\u00020\u000e*\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002¢\u0006\u0002\u0010\u001b\u001a)\u0010\u001c\u001a\u00020\u000e*\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002¢\u0006\u0002\u0010\u001d\u001a)\u0010\u001e\u001a\u00020\u000e*\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002¢\u0006\u0002\u0010\u001d¨\u0006\u001f"}, d2 = {"rememberStaggeredGridMeasurePolicy", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "mainAxisSpacing", "Landroidx/compose/ui/unit/Dp;", "crossAxisSpacing", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "slots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "rememberStaggeredGridMeasurePolicy-qKj4JfE", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;FFLkotlinx/coroutines/CoroutineScope;Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;Landroidx/compose/ui/graphics/GraphicsContext;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;", "startPadding", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/unit/LayoutDirection;)F", "beforePadding", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/gestures/Orientation;ZLandroidx/compose/ui/unit/LayoutDirection;)F", "afterPadding", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyStaggeredGridMeasurePolicyKt {

    /* JADX INFO: compiled from: LazyStaggeredGridMeasurePolicy.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Orientation.values().length];
            try {
                iArr[Orientation.Vertical.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Orientation.Horizontal.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0039 A[PHI: r7
  0x0039: PHI (r7v3 androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState) = 
  (r7v1 androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState)
  (r7v4 androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState)
 binds: [B:12:0x0037, B:8:0x0030] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:17:0x0044  */
    /* JADX WARN: Code duplicated, block: B:20:0x004d  */
    /* JADX WARN: Code duplicated, block: B:23:0x0053 A[PHI: r10
  0x0053: PHI (r10v3 kotlin.jvm.functions.Function0<? extends androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemProvider>) = 
  (r10v1 kotlin.jvm.functions.Function0<? extends androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemProvider>)
  (r10v4 kotlin.jvm.functions.Function0<? extends androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemProvider>)
 binds: [B:22:0x0051, B:18:0x004a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:24:0x0055  */
    /* JADX WARN: Code duplicated, block: B:27:0x005f  */
    /* JADX WARN: Code duplicated, block: B:30:0x0068  */
    /* JADX WARN: Code duplicated, block: B:33:0x006e A[PHI: r11
  0x006e: PHI (r11v3 androidx.compose.foundation.layout.PaddingValues) = (r11v1 androidx.compose.foundation.layout.PaddingValues), (r11v4 androidx.compose.foundation.layout.PaddingValues) binds: [B:32:0x006c, B:28:0x0065] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:34:0x0070  */
    /* JADX WARN: Code duplicated, block: B:37:0x007a  */
    /* JADX WARN: Code duplicated, block: B:40:0x0083  */
    /* JADX WARN: Code duplicated, block: B:43:0x0089 A[PHI: r12
  0x0089: PHI (r12v3 boolean) = (r12v1 boolean), (r12v4 boolean) binds: [B:42:0x0087, B:38:0x0080] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:44:0x008b  */
    /* JADX WARN: Code duplicated, block: B:47:0x0097  */
    /* JADX WARN: Code duplicated, block: B:49:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:55:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:58:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:61:0x00c6 A[PHI: r13
  0x00c6: PHI (r13v3 float) = (r13v1 float), (r13v4 float) binds: [B:60:0x00c4, B:56:0x00bd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:62:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:65:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:68:0x00dd A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:71:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:72:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:75:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:78:0x00f9 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:81:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:84:0x0113  */
    /* JADX WARN: Code duplicated, block: B:87:0x011c  */
    /* JADX WARN: Code duplicated, block: B:90:0x013f  */
    /* JADX INFO: renamed from: rememberStaggeredGridMeasurePolicy-qKj4JfE, reason: not valid java name */
    public static final LazyLayoutMeasurePolicy m1046rememberStaggeredGridMeasurePolicyqKj4JfE(LazyStaggeredGridState state, Function0<? extends LazyStaggeredGridItemProvider> function0, PaddingValues contentPadding, boolean reverseLayout, final Orientation orientation, float f, float f2, final CoroutineScope coroutineScope, final LazyGridStaggeredGridSlotsProvider slots, final GraphicsContext graphicsContext, Composer $composer, int $changed) {
        final LazyStaggeredGridState lazyStaggeredGridState;
        boolean z;
        final Function0<? extends LazyStaggeredGridItemProvider> function1;
        boolean z2;
        final PaddingValues paddingValues;
        boolean z3;
        final boolean z4;
        boolean z5;
        final float f3;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean invalid$iv;
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 234882793, "C(rememberStaggeredGridMeasurePolicy)N(state,itemProviderLambda,contentPadding,reverseLayout,orientation,mainAxisSpacing:c#ui.unit.Dp,crossAxisSpacing:c#ui.unit.Dp,coroutineScope,slots,graphicsContext)50@2088L3728:LazyStaggeredGridMeasurePolicy.kt#fzvcnm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(234882793, $changed, -1, "androidx.compose.foundation.lazy.staggeredgrid.rememberStaggeredGridMeasurePolicy (LazyStaggeredGridMeasurePolicy.kt:50)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1910884999, "CC(remember):LazyStaggeredGridMeasurePolicy.kt#9igjgp");
        if ((($changed & 14) ^ 6) > 4) {
            lazyStaggeredGridState = state;
            if ($composer.changed(lazyStaggeredGridState)) {
                z = true;
            }
            if ((($changed & 112) ^ 48) > 32) {
                function1 = function0;
                if (!$composer.changed(function1)) {
                    z2 = true;
                }
                boolean z9 = z | z2;
                if ((($changed & 896) ^ 384) > 256) {
                    paddingValues = contentPadding;
                    if (!$composer.changed(paddingValues)) {
                        z3 = true;
                    }
                    boolean z10 = z9 | z3;
                    if ((($changed & 7168) ^ 3072) > 2048) {
                        z4 = reverseLayout;
                        if (!$composer.changed(z4)) {
                            z5 = true;
                        }
                        boolean z11 = z10 | z5 | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(orientation.ordinal())) || ($changed & 24576) == 16384);
                        if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
                            f3 = f;
                            if (!$composer.changed(f3)) {
                                z6 = true;
                            }
                            boolean z12 = z11 | z6;
                            if (((3670016 & $changed) ^ 1572864) <= 1048576 && $composer.changed(f2)) {
                                z7 = true;
                            } else if ((1572864 & $changed) == 1048576) {
                                z7 = true;
                            } else {
                                z7 = false;
                            }
                            boolean z13 = z12 | z7;
                            if (((234881024 & $changed) ^ 100663296) > 67108864 || !$composer.changed(slots)) {
                                z8 = (100663296 & $changed) == 67108864;
                            }
                            invalid$iv = z13 | z8 | $composer.changed(graphicsContext);
                            value$iv = $composer.rememberedValue();
                            if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                                value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                                    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                                    /* JADX INFO: renamed from: measure-0kLqBqw */
                                    public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                        ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                        boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                        CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                        LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                        boolean isVertical = orientation == Orientation.Vertical;
                                        LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                        int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                        int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                        int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                        int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                        int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                        long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                        PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                        int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                        PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                        int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                        List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                        int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                        int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                        LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                        LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                        return measureResult;
                                    }
                                };
                                $composer.updateRememberedValue(value$iv);
                            }
                            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy = (LazyLayoutMeasurePolicy) value$iv;
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            return lazyLayoutMeasurePolicy;
                        }
                        f3 = f;
                        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        boolean z14 = z11 | z6;
                        if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                        }
                        if ((1572864 & $changed) == 1048576) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        boolean z15 = z14 | z7;
                        if (((234881024 & $changed) ^ 100663296) > 67108864) {
                        }
                        if ((100663296 & $changed) == 67108864) {
                        }
                        invalid$iv = z15 | z8 | $composer.changed(graphicsContext);
                        value$iv = $composer.rememberedValue();
                        if (invalid$iv) {
                            value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                                /* JADX INFO: renamed from: measure-0kLqBqw */
                                public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                    ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                    boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                    CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                    LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                    boolean isVertical = orientation == Orientation.Vertical;
                                    LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                    int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                    int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                    long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                    int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                    int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                    List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                    int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                    int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                    LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                    LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                    return measureResult;
                                }
                            };
                            $composer.updateRememberedValue(value$iv);
                        } else {
                            value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                                /* JADX INFO: renamed from: measure-0kLqBqw */
                                public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                    ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                    boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                    CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                    LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                    boolean isVertical = orientation == Orientation.Vertical;
                                    LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                    int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                    int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                    long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                    int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                    int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                    List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                    int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                    int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                    LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                    LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                    return measureResult;
                                }
                            };
                            $composer.updateRememberedValue(value$iv);
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy2 = (LazyLayoutMeasurePolicy) value$iv;
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        return lazyLayoutMeasurePolicy2;
                    }
                    z4 = reverseLayout;
                    if (($changed & 3072) == 2048) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    boolean z16 = z10 | z5 | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(orientation.ordinal())) || ($changed & 24576) == 16384);
                    if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
                        f3 = f;
                        if (!$composer.changed(f3)) {
                            z6 = true;
                        }
                        boolean z17 = z16 | z6;
                        if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                        }
                        if ((1572864 & $changed) == 1048576) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        boolean z18 = z17 | z7;
                        if (((234881024 & $changed) ^ 100663296) > 67108864) {
                        }
                        if ((100663296 & $changed) == 67108864) {
                        }
                        invalid$iv = z18 | z8 | $composer.changed(graphicsContext);
                        value$iv = $composer.rememberedValue();
                        if (invalid$iv) {
                            value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                                /* JADX INFO: renamed from: measure-0kLqBqw */
                                public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                    ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                    boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                    CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                    LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                    boolean isVertical = orientation == Orientation.Vertical;
                                    LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                    int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                    int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                    long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                    int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                    int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                    List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                    int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                    int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                    LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                    LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                    return measureResult;
                                }
                            };
                            $composer.updateRememberedValue(value$iv);
                        } else {
                            value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                                /* JADX INFO: renamed from: measure-0kLqBqw */
                                public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                    ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                    boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                    CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                    LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                    boolean isVertical = orientation == Orientation.Vertical;
                                    LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                    int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                    int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                    long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                    int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                    int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                    List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                    int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                    int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                    LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                    LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                    return measureResult;
                                }
                            };
                            $composer.updateRememberedValue(value$iv);
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy3 = (LazyLayoutMeasurePolicy) value$iv;
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        return lazyLayoutMeasurePolicy3;
                    }
                    f3 = f;
                    if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    boolean z19 = z16 | z6;
                    if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                    }
                    if ((1572864 & $changed) == 1048576) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    boolean z110 = z19 | z7;
                    if (((234881024 & $changed) ^ 100663296) > 67108864) {
                    }
                    if ((100663296 & $changed) == 67108864) {
                    }
                    invalid$iv = z110 | z8 | $composer.changed(graphicsContext);
                    value$iv = $composer.rememberedValue();
                    if (invalid$iv) {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    } else {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy4 = (LazyLayoutMeasurePolicy) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    return lazyLayoutMeasurePolicy4;
                }
                paddingValues = contentPadding;
                if (($changed & 384) == 256) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z111 = z9 | z3;
                if ((($changed & 7168) ^ 3072) > 2048) {
                    z4 = reverseLayout;
                    if (!$composer.changed(z4)) {
                        z5 = true;
                    }
                    boolean z112 = z111 | z5 | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(orientation.ordinal())) || ($changed & 24576) == 16384);
                    if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
                        f3 = f;
                        if (!$composer.changed(f3)) {
                            z6 = true;
                        }
                        boolean z113 = z112 | z6;
                        if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                        }
                        if ((1572864 & $changed) == 1048576) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        boolean z114 = z113 | z7;
                        if (((234881024 & $changed) ^ 100663296) > 67108864) {
                        }
                        if ((100663296 & $changed) == 67108864) {
                        }
                        invalid$iv = z114 | z8 | $composer.changed(graphicsContext);
                        value$iv = $composer.rememberedValue();
                        if (invalid$iv) {
                            value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                                /* JADX INFO: renamed from: measure-0kLqBqw */
                                public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                    ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                    boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                    CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                    LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                    boolean isVertical = orientation == Orientation.Vertical;
                                    LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                    int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                    int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                    long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                    int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                    int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                    List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                    int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                    int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                    LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                    LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                    return measureResult;
                                }
                            };
                            $composer.updateRememberedValue(value$iv);
                        } else {
                            value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                                /* JADX INFO: renamed from: measure-0kLqBqw */
                                public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                    ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                    boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                    CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                    LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                    boolean isVertical = orientation == Orientation.Vertical;
                                    LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                    int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                    int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                    long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                    int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                    int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                    List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                    int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                    int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                    LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                    LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                    return measureResult;
                                }
                            };
                            $composer.updateRememberedValue(value$iv);
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy5 = (LazyLayoutMeasurePolicy) value$iv;
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        return lazyLayoutMeasurePolicy5;
                    }
                    f3 = f;
                    if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    boolean z115 = z112 | z6;
                    if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                    }
                    if ((1572864 & $changed) == 1048576) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    boolean z116 = z115 | z7;
                    if (((234881024 & $changed) ^ 100663296) > 67108864) {
                    }
                    if ((100663296 & $changed) == 67108864) {
                    }
                    invalid$iv = z116 | z8 | $composer.changed(graphicsContext);
                    value$iv = $composer.rememberedValue();
                    if (invalid$iv) {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    } else {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy6 = (LazyLayoutMeasurePolicy) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    return lazyLayoutMeasurePolicy6;
                }
                z4 = reverseLayout;
                if (($changed & 3072) == 2048) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                boolean z117 = z111 | z5 | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(orientation.ordinal())) || ($changed & 24576) == 16384);
                if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
                    f3 = f;
                    if (!$composer.changed(f3)) {
                        z6 = true;
                    }
                    boolean z118 = z117 | z6;
                    if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                    }
                    if ((1572864 & $changed) == 1048576) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    boolean z119 = z118 | z7;
                    if (((234881024 & $changed) ^ 100663296) > 67108864) {
                    }
                    if ((100663296 & $changed) == 67108864) {
                    }
                    invalid$iv = z119 | z8 | $composer.changed(graphicsContext);
                    value$iv = $composer.rememberedValue();
                    if (invalid$iv) {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    } else {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy7 = (LazyLayoutMeasurePolicy) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    return lazyLayoutMeasurePolicy7;
                }
                f3 = f;
                if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z1110 = z117 | z6;
                if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                }
                if ((1572864 & $changed) == 1048576) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean z1111 = z1110 | z7;
                if (((234881024 & $changed) ^ 100663296) > 67108864) {
                }
                if ((100663296 & $changed) == 67108864) {
                }
                invalid$iv = z1111 | z8 | $composer.changed(graphicsContext);
                value$iv = $composer.rememberedValue();
                if (invalid$iv) {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy8 = (LazyLayoutMeasurePolicy) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                return lazyLayoutMeasurePolicy8;
            }
            function1 = function0;
            if (($changed & 48) == 32) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z20 = z | z2;
            if ((($changed & 896) ^ 384) > 256) {
                paddingValues = contentPadding;
                if (!$composer.changed(paddingValues)) {
                    z3 = true;
                }
                boolean z1112 = z20 | z3;
                if ((($changed & 7168) ^ 3072) > 2048) {
                    z4 = reverseLayout;
                    if (!$composer.changed(z4)) {
                        z5 = true;
                    }
                    boolean z1113 = z1112 | z5 | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(orientation.ordinal())) || ($changed & 24576) == 16384);
                    if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
                        f3 = f;
                        if (!$composer.changed(f3)) {
                            z6 = true;
                        }
                        boolean z1114 = z1113 | z6;
                        if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                        }
                        if ((1572864 & $changed) == 1048576) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        boolean z1115 = z1114 | z7;
                        if (((234881024 & $changed) ^ 100663296) > 67108864) {
                        }
                        if ((100663296 & $changed) == 67108864) {
                        }
                        invalid$iv = z1115 | z8 | $composer.changed(graphicsContext);
                        value$iv = $composer.rememberedValue();
                        if (invalid$iv) {
                            value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                                /* JADX INFO: renamed from: measure-0kLqBqw */
                                public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                    ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                    boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                    CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                    LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                    boolean isVertical = orientation == Orientation.Vertical;
                                    LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                    int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                    int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                    long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                    int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                    int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                    List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                    int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                    int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                    LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                    LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                    return measureResult;
                                }
                            };
                            $composer.updateRememberedValue(value$iv);
                        } else {
                            value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                                /* JADX INFO: renamed from: measure-0kLqBqw */
                                public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                    ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                    boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                    CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                    LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                    boolean isVertical = orientation == Orientation.Vertical;
                                    LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                    int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                    int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                    long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                    int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                    int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                    List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                    int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                    int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                    LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                    LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                    return measureResult;
                                }
                            };
                            $composer.updateRememberedValue(value$iv);
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy9 = (LazyLayoutMeasurePolicy) value$iv;
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        return lazyLayoutMeasurePolicy9;
                    }
                    f3 = f;
                    if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    boolean z1116 = z1113 | z6;
                    if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                    }
                    if ((1572864 & $changed) == 1048576) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    boolean z1117 = z1116 | z7;
                    if (((234881024 & $changed) ^ 100663296) > 67108864) {
                    }
                    if ((100663296 & $changed) == 67108864) {
                    }
                    invalid$iv = z1117 | z8 | $composer.changed(graphicsContext);
                    value$iv = $composer.rememberedValue();
                    if (invalid$iv) {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    } else {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy10 = (LazyLayoutMeasurePolicy) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    return lazyLayoutMeasurePolicy10;
                }
                z4 = reverseLayout;
                if (($changed & 3072) == 2048) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                boolean z1118 = z1112 | z5 | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(orientation.ordinal())) || ($changed & 24576) == 16384);
                if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
                    f3 = f;
                    if (!$composer.changed(f3)) {
                        z6 = true;
                    }
                    boolean z1119 = z1118 | z6;
                    if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                    }
                    if ((1572864 & $changed) == 1048576) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    boolean z11110 = z1119 | z7;
                    if (((234881024 & $changed) ^ 100663296) > 67108864) {
                    }
                    if ((100663296 & $changed) == 67108864) {
                    }
                    invalid$iv = z11110 | z8 | $composer.changed(graphicsContext);
                    value$iv = $composer.rememberedValue();
                    if (invalid$iv) {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    } else {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy11 = (LazyLayoutMeasurePolicy) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    return lazyLayoutMeasurePolicy11;
                }
                f3 = f;
                if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z11111 = z1118 | z6;
                if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                }
                if ((1572864 & $changed) == 1048576) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean z11112 = z11111 | z7;
                if (((234881024 & $changed) ^ 100663296) > 67108864) {
                }
                if ((100663296 & $changed) == 67108864) {
                }
                invalid$iv = z11112 | z8 | $composer.changed(graphicsContext);
                value$iv = $composer.rememberedValue();
                if (invalid$iv) {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy12 = (LazyLayoutMeasurePolicy) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                return lazyLayoutMeasurePolicy12;
            }
            paddingValues = contentPadding;
            if (($changed & 384) == 256) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z11113 = z20 | z3;
            if ((($changed & 7168) ^ 3072) > 2048) {
                z4 = reverseLayout;
                if (!$composer.changed(z4)) {
                    z5 = true;
                }
                boolean z11114 = z11113 | z5 | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(orientation.ordinal())) || ($changed & 24576) == 16384);
                if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
                    f3 = f;
                    if (!$composer.changed(f3)) {
                        z6 = true;
                    }
                    boolean z11115 = z11114 | z6;
                    if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                    }
                    if ((1572864 & $changed) == 1048576) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    boolean z11116 = z11115 | z7;
                    if (((234881024 & $changed) ^ 100663296) > 67108864) {
                    }
                    if ((100663296 & $changed) == 67108864) {
                    }
                    invalid$iv = z11116 | z8 | $composer.changed(graphicsContext);
                    value$iv = $composer.rememberedValue();
                    if (invalid$iv) {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    } else {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy13 = (LazyLayoutMeasurePolicy) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    return lazyLayoutMeasurePolicy13;
                }
                f3 = f;
                if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z11117 = z11114 | z6;
                if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                }
                if ((1572864 & $changed) == 1048576) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean z11118 = z11117 | z7;
                if (((234881024 & $changed) ^ 100663296) > 67108864) {
                }
                if ((100663296 & $changed) == 67108864) {
                }
                invalid$iv = z11118 | z8 | $composer.changed(graphicsContext);
                value$iv = $composer.rememberedValue();
                if (invalid$iv) {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy14 = (LazyLayoutMeasurePolicy) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                return lazyLayoutMeasurePolicy14;
            }
            z4 = reverseLayout;
            if (($changed & 3072) == 2048) {
                z5 = true;
            } else {
                z5 = false;
            }
            boolean z11119 = z11113 | z5 | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(orientation.ordinal())) || ($changed & 24576) == 16384);
            if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
                f3 = f;
                if (!$composer.changed(f3)) {
                    z6 = true;
                }
                boolean z111110 = z11119 | z6;
                if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                }
                if ((1572864 & $changed) == 1048576) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean z111111 = z111110 | z7;
                if (((234881024 & $changed) ^ 100663296) > 67108864) {
                }
                if ((100663296 & $changed) == 67108864) {
                }
                invalid$iv = z111111 | z8 | $composer.changed(graphicsContext);
                value$iv = $composer.rememberedValue();
                if (invalid$iv) {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy15 = (LazyLayoutMeasurePolicy) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                return lazyLayoutMeasurePolicy15;
            }
            f3 = f;
            if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
                z6 = true;
            } else {
                z6 = false;
            }
            boolean z111112 = z11119 | z6;
            if (((3670016 & $changed) ^ 1572864) <= 1048576) {
            }
            if ((1572864 & $changed) == 1048576) {
                z7 = true;
            } else {
                z7 = false;
            }
            boolean z111113 = z111112 | z7;
            if (((234881024 & $changed) ^ 100663296) > 67108864) {
            }
            if ((100663296 & $changed) == 67108864) {
            }
            invalid$iv = z111113 | z8 | $composer.changed(graphicsContext);
            value$iv = $composer.rememberedValue();
            if (invalid$iv) {
                value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                    /* JADX INFO: renamed from: measure-0kLqBqw */
                    public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                        ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                        boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                        CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                        LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                        boolean isVertical = orientation == Orientation.Vertical;
                        LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                        int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                        int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                        long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                        int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                        int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                        List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                        int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                        int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                        LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                        LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                        return measureResult;
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                    /* JADX INFO: renamed from: measure-0kLqBqw */
                    public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                        ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                        boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                        CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                        LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                        boolean isVertical = orientation == Orientation.Vertical;
                        LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                        int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                        int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                        long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                        int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                        int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                        List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                        int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                        int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                        LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                        LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                        return measureResult;
                    }
                };
                $composer.updateRememberedValue(value$iv);
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy16 = (LazyLayoutMeasurePolicy) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            return lazyLayoutMeasurePolicy16;
        }
        lazyStaggeredGridState = state;
        if (($changed & 6) == 4) {
            z = true;
        } else {
            z = false;
        }
        if ((($changed & 112) ^ 48) > 32) {
            function1 = function0;
            if (!$composer.changed(function1)) {
                z2 = true;
            }
            boolean z21 = z | z2;
            if ((($changed & 896) ^ 384) > 256) {
                paddingValues = contentPadding;
                if (!$composer.changed(paddingValues)) {
                    z3 = true;
                }
                boolean z111114 = z21 | z3;
                if ((($changed & 7168) ^ 3072) > 2048) {
                    z4 = reverseLayout;
                    if (!$composer.changed(z4)) {
                        z5 = true;
                    }
                    boolean z111115 = z111114 | z5 | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(orientation.ordinal())) || ($changed & 24576) == 16384);
                    if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
                        f3 = f;
                        if (!$composer.changed(f3)) {
                            z6 = true;
                        }
                        boolean z111116 = z111115 | z6;
                        if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                        }
                        if ((1572864 & $changed) == 1048576) {
                            z7 = true;
                        } else {
                            z7 = false;
                        }
                        boolean z111117 = z111116 | z7;
                        if (((234881024 & $changed) ^ 100663296) > 67108864) {
                        }
                        if ((100663296 & $changed) == 67108864) {
                        }
                        invalid$iv = z111117 | z8 | $composer.changed(graphicsContext);
                        value$iv = $composer.rememberedValue();
                        if (invalid$iv) {
                            value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                                /* JADX INFO: renamed from: measure-0kLqBqw */
                                public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                    ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                    boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                    CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                    LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                    boolean isVertical = orientation == Orientation.Vertical;
                                    LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                    int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                    int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                    long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                    int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                    int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                    List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                    int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                    int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                    LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                    LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                    return measureResult;
                                }
                            };
                            $composer.updateRememberedValue(value$iv);
                        } else {
                            value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                                /* JADX INFO: renamed from: measure-0kLqBqw */
                                public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                    ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                    boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                    CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                    LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                    boolean isVertical = orientation == Orientation.Vertical;
                                    LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                    int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                    int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                    int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                    long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                    int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                    PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                    int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                    List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                    int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                    int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                    LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                    LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                    return measureResult;
                                }
                            };
                            $composer.updateRememberedValue(value$iv);
                        }
                        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy17 = (LazyLayoutMeasurePolicy) value$iv;
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        return lazyLayoutMeasurePolicy17;
                    }
                    f3 = f;
                    if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    boolean z111118 = z111115 | z6;
                    if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                    }
                    if ((1572864 & $changed) == 1048576) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    boolean z111119 = z111118 | z7;
                    if (((234881024 & $changed) ^ 100663296) > 67108864) {
                    }
                    if ((100663296 & $changed) == 67108864) {
                    }
                    invalid$iv = z111119 | z8 | $composer.changed(graphicsContext);
                    value$iv = $composer.rememberedValue();
                    if (invalid$iv) {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    } else {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy18 = (LazyLayoutMeasurePolicy) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    return lazyLayoutMeasurePolicy18;
                }
                z4 = reverseLayout;
                if (($changed & 3072) == 2048) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                boolean z1111110 = z111114 | z5 | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(orientation.ordinal())) || ($changed & 24576) == 16384);
                if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
                    f3 = f;
                    if (!$composer.changed(f3)) {
                        z6 = true;
                    }
                    boolean z1111111 = z1111110 | z6;
                    if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                    }
                    if ((1572864 & $changed) == 1048576) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    boolean z1111112 = z1111111 | z7;
                    if (((234881024 & $changed) ^ 100663296) > 67108864) {
                    }
                    if ((100663296 & $changed) == 67108864) {
                    }
                    invalid$iv = z1111112 | z8 | $composer.changed(graphicsContext);
                    value$iv = $composer.rememberedValue();
                    if (invalid$iv) {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    } else {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy19 = (LazyLayoutMeasurePolicy) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    return lazyLayoutMeasurePolicy19;
                }
                f3 = f;
                if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z1111113 = z1111110 | z6;
                if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                }
                if ((1572864 & $changed) == 1048576) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean z1111114 = z1111113 | z7;
                if (((234881024 & $changed) ^ 100663296) > 67108864) {
                }
                if ((100663296 & $changed) == 67108864) {
                }
                invalid$iv = z1111114 | z8 | $composer.changed(graphicsContext);
                value$iv = $composer.rememberedValue();
                if (invalid$iv) {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy110 = (LazyLayoutMeasurePolicy) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                return lazyLayoutMeasurePolicy110;
            }
            paddingValues = contentPadding;
            if (($changed & 384) == 256) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z1111115 = z21 | z3;
            if ((($changed & 7168) ^ 3072) > 2048) {
                z4 = reverseLayout;
                if (!$composer.changed(z4)) {
                    z5 = true;
                }
                boolean z1111116 = z1111115 | z5 | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(orientation.ordinal())) || ($changed & 24576) == 16384);
                if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
                    f3 = f;
                    if (!$composer.changed(f3)) {
                        z6 = true;
                    }
                    boolean z1111117 = z1111116 | z6;
                    if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                    }
                    if ((1572864 & $changed) == 1048576) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    boolean z1111118 = z1111117 | z7;
                    if (((234881024 & $changed) ^ 100663296) > 67108864) {
                    }
                    if ((100663296 & $changed) == 67108864) {
                    }
                    invalid$iv = z1111118 | z8 | $composer.changed(graphicsContext);
                    value$iv = $composer.rememberedValue();
                    if (invalid$iv) {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    } else {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy111 = (LazyLayoutMeasurePolicy) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    return lazyLayoutMeasurePolicy111;
                }
                f3 = f;
                if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z1111119 = z1111116 | z6;
                if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                }
                if ((1572864 & $changed) == 1048576) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean z11111110 = z1111119 | z7;
                if (((234881024 & $changed) ^ 100663296) > 67108864) {
                }
                if ((100663296 & $changed) == 67108864) {
                }
                invalid$iv = z11111110 | z8 | $composer.changed(graphicsContext);
                value$iv = $composer.rememberedValue();
                if (invalid$iv) {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy112 = (LazyLayoutMeasurePolicy) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                return lazyLayoutMeasurePolicy112;
            }
            z4 = reverseLayout;
            if (($changed & 3072) == 2048) {
                z5 = true;
            } else {
                z5 = false;
            }
            boolean z11111111 = z1111115 | z5 | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(orientation.ordinal())) || ($changed & 24576) == 16384);
            if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
                f3 = f;
                if (!$composer.changed(f3)) {
                    z6 = true;
                }
                boolean z11111112 = z11111111 | z6;
                if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                }
                if ((1572864 & $changed) == 1048576) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean z11111113 = z11111112 | z7;
                if (((234881024 & $changed) ^ 100663296) > 67108864) {
                }
                if ((100663296 & $changed) == 67108864) {
                }
                invalid$iv = z11111113 | z8 | $composer.changed(graphicsContext);
                value$iv = $composer.rememberedValue();
                if (invalid$iv) {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy113 = (LazyLayoutMeasurePolicy) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                return lazyLayoutMeasurePolicy113;
            }
            f3 = f;
            if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
                z6 = true;
            } else {
                z6 = false;
            }
            boolean z11111114 = z11111111 | z6;
            if (((3670016 & $changed) ^ 1572864) <= 1048576) {
            }
            if ((1572864 & $changed) == 1048576) {
                z7 = true;
            } else {
                z7 = false;
            }
            boolean z11111115 = z11111114 | z7;
            if (((234881024 & $changed) ^ 100663296) > 67108864) {
            }
            if ((100663296 & $changed) == 67108864) {
            }
            invalid$iv = z11111115 | z8 | $composer.changed(graphicsContext);
            value$iv = $composer.rememberedValue();
            if (invalid$iv) {
                value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                    /* JADX INFO: renamed from: measure-0kLqBqw */
                    public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                        ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                        boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                        CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                        LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                        boolean isVertical = orientation == Orientation.Vertical;
                        LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                        int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                        int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                        long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                        int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                        int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                        List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                        int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                        int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                        LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                        LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                        return measureResult;
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                    /* JADX INFO: renamed from: measure-0kLqBqw */
                    public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                        ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                        boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                        CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                        LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                        boolean isVertical = orientation == Orientation.Vertical;
                        LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                        int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                        int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                        long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                        int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                        int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                        List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                        int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                        int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                        LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                        LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                        return measureResult;
                    }
                };
                $composer.updateRememberedValue(value$iv);
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy114 = (LazyLayoutMeasurePolicy) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            return lazyLayoutMeasurePolicy114;
        }
        function1 = function0;
        if (($changed & 48) == 32) {
            z2 = true;
        } else {
            z2 = false;
        }
        boolean z22 = z | z2;
        if ((($changed & 896) ^ 384) > 256) {
            paddingValues = contentPadding;
            if (!$composer.changed(paddingValues)) {
                z3 = true;
            }
            boolean z11111116 = z22 | z3;
            if ((($changed & 7168) ^ 3072) > 2048) {
                z4 = reverseLayout;
                if (!$composer.changed(z4)) {
                    z5 = true;
                }
                boolean z11111117 = z11111116 | z5 | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(orientation.ordinal())) || ($changed & 24576) == 16384);
                if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
                    f3 = f;
                    if (!$composer.changed(f3)) {
                        z6 = true;
                    }
                    boolean z11111118 = z11111117 | z6;
                    if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                    }
                    if ((1572864 & $changed) == 1048576) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    boolean z11111119 = z11111118 | z7;
                    if (((234881024 & $changed) ^ 100663296) > 67108864) {
                    }
                    if ((100663296 & $changed) == 67108864) {
                    }
                    invalid$iv = z11111119 | z8 | $composer.changed(graphicsContext);
                    value$iv = $composer.rememberedValue();
                    if (invalid$iv) {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    } else {
                        value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                            /* JADX INFO: renamed from: measure-0kLqBqw */
                            public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                                ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                                boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                                CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                                LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                                boolean isVertical = orientation == Orientation.Vertical;
                                LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                                int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                                int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                                int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                                long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                                int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                                PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                                int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                                List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                                int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                                int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                                LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                                LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                                return measureResult;
                            }
                        };
                        $composer.updateRememberedValue(value$iv);
                    }
                    LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy115 = (LazyLayoutMeasurePolicy) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    return lazyLayoutMeasurePolicy115;
                }
                f3 = f;
                if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z111111110 = z11111117 | z6;
                if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                }
                if ((1572864 & $changed) == 1048576) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean z111111111 = z111111110 | z7;
                if (((234881024 & $changed) ^ 100663296) > 67108864) {
                }
                if ((100663296 & $changed) == 67108864) {
                }
                invalid$iv = z111111111 | z8 | $composer.changed(graphicsContext);
                value$iv = $composer.rememberedValue();
                if (invalid$iv) {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy116 = (LazyLayoutMeasurePolicy) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                return lazyLayoutMeasurePolicy116;
            }
            z4 = reverseLayout;
            if (($changed & 3072) == 2048) {
                z5 = true;
            } else {
                z5 = false;
            }
            boolean z111111112 = z11111116 | z5 | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(orientation.ordinal())) || ($changed & 24576) == 16384);
            if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
                f3 = f;
                if (!$composer.changed(f3)) {
                    z6 = true;
                }
                boolean z111111113 = z111111112 | z6;
                if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                }
                if ((1572864 & $changed) == 1048576) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean z111111114 = z111111113 | z7;
                if (((234881024 & $changed) ^ 100663296) > 67108864) {
                }
                if ((100663296 & $changed) == 67108864) {
                }
                invalid$iv = z111111114 | z8 | $composer.changed(graphicsContext);
                value$iv = $composer.rememberedValue();
                if (invalid$iv) {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy117 = (LazyLayoutMeasurePolicy) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                return lazyLayoutMeasurePolicy117;
            }
            f3 = f;
            if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
                z6 = true;
            } else {
                z6 = false;
            }
            boolean z111111115 = z111111112 | z6;
            if (((3670016 & $changed) ^ 1572864) <= 1048576) {
            }
            if ((1572864 & $changed) == 1048576) {
                z7 = true;
            } else {
                z7 = false;
            }
            boolean z111111116 = z111111115 | z7;
            if (((234881024 & $changed) ^ 100663296) > 67108864) {
            }
            if ((100663296 & $changed) == 67108864) {
            }
            invalid$iv = z111111116 | z8 | $composer.changed(graphicsContext);
            value$iv = $composer.rememberedValue();
            if (invalid$iv) {
                value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                    /* JADX INFO: renamed from: measure-0kLqBqw */
                    public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                        ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                        boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                        CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                        LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                        boolean isVertical = orientation == Orientation.Vertical;
                        LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                        int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                        int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                        long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                        int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                        int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                        List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                        int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                        int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                        LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                        LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                        return measureResult;
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                    /* JADX INFO: renamed from: measure-0kLqBqw */
                    public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                        ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                        boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                        CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                        LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                        boolean isVertical = orientation == Orientation.Vertical;
                        LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                        int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                        int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                        long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                        int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                        int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                        List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                        int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                        int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                        LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                        LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                        return measureResult;
                    }
                };
                $composer.updateRememberedValue(value$iv);
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy118 = (LazyLayoutMeasurePolicy) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            return lazyLayoutMeasurePolicy118;
        }
        paddingValues = contentPadding;
        if (($changed & 384) == 256) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean z111111117 = z22 | z3;
        if ((($changed & 7168) ^ 3072) > 2048) {
            z4 = reverseLayout;
            if (!$composer.changed(z4)) {
                z5 = true;
            }
            boolean z111111118 = z111111117 | z5 | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(orientation.ordinal())) || ($changed & 24576) == 16384);
            if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
                f3 = f;
                if (!$composer.changed(f3)) {
                    z6 = true;
                }
                boolean z111111119 = z111111118 | z6;
                if (((3670016 & $changed) ^ 1572864) <= 1048576) {
                }
                if ((1572864 & $changed) == 1048576) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                boolean z1111111110 = z111111119 | z7;
                if (((234881024 & $changed) ^ 100663296) > 67108864) {
                }
                if ((100663296 & $changed) == 67108864) {
                }
                invalid$iv = z1111111110 | z8 | $composer.changed(graphicsContext);
                value$iv = $composer.rememberedValue();
                if (invalid$iv) {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                        /* JADX INFO: renamed from: measure-0kLqBqw */
                        public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                            ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                            boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                            CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                            LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                            boolean isVertical = orientation == Orientation.Vertical;
                            LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                            int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                            int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                            int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                            long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                            int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                            PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                            int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                            List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                            int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                            int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                            LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                            LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                            return measureResult;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                }
                LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy119 = (LazyLayoutMeasurePolicy) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                return lazyLayoutMeasurePolicy119;
            }
            f3 = f;
            if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
                z6 = true;
            } else {
                z6 = false;
            }
            boolean z1111111111 = z111111118 | z6;
            if (((3670016 & $changed) ^ 1572864) <= 1048576) {
            }
            if ((1572864 & $changed) == 1048576) {
                z7 = true;
            } else {
                z7 = false;
            }
            boolean z1111111112 = z1111111111 | z7;
            if (((234881024 & $changed) ^ 100663296) > 67108864) {
            }
            if ((100663296 & $changed) == 67108864) {
            }
            invalid$iv = z1111111112 | z8 | $composer.changed(graphicsContext);
            value$iv = $composer.rememberedValue();
            if (invalid$iv) {
                value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                    /* JADX INFO: renamed from: measure-0kLqBqw */
                    public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                        ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                        boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                        CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                        LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                        boolean isVertical = orientation == Orientation.Vertical;
                        LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                        int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                        int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                        long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                        int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                        int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                        List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                        int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                        int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                        LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                        LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                        return measureResult;
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                    /* JADX INFO: renamed from: measure-0kLqBqw */
                    public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                        ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                        boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                        CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                        LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                        boolean isVertical = orientation == Orientation.Vertical;
                        LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                        int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                        int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                        long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                        int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                        int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                        List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                        int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                        int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                        LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                        LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                        return measureResult;
                    }
                };
                $composer.updateRememberedValue(value$iv);
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1110 = (LazyLayoutMeasurePolicy) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            return lazyLayoutMeasurePolicy1110;
        }
        z4 = reverseLayout;
        if (($changed & 3072) == 2048) {
            z5 = true;
        } else {
            z5 = false;
        }
        boolean z1111111113 = z111111117 | z5 | ((((57344 & $changed) ^ 24576) <= 16384 && $composer.changed(orientation.ordinal())) || ($changed & 24576) == 16384);
        if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072) {
            f3 = f;
            if (!$composer.changed(f3)) {
                z6 = true;
            }
            boolean z1111111114 = z1111111113 | z6;
            if (((3670016 & $changed) ^ 1572864) <= 1048576) {
            }
            if ((1572864 & $changed) == 1048576) {
                z7 = true;
            } else {
                z7 = false;
            }
            boolean z1111111115 = z1111111114 | z7;
            if (((234881024 & $changed) ^ 100663296) > 67108864) {
            }
            if ((100663296 & $changed) == 67108864) {
            }
            invalid$iv = z1111111115 | z8 | $composer.changed(graphicsContext);
            value$iv = $composer.rememberedValue();
            if (invalid$iv) {
                value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                    /* JADX INFO: renamed from: measure-0kLqBqw */
                    public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                        ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                        boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                        CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                        LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                        boolean isVertical = orientation == Orientation.Vertical;
                        LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                        int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                        int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                        long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                        int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                        int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                        List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                        int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                        int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                        LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                        LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                        return measureResult;
                    }
                };
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                    /* JADX INFO: renamed from: measure-0kLqBqw */
                    public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                        ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                        boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                        CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                        LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                        boolean isVertical = orientation == Orientation.Vertical;
                        LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                        int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                        int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                        int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                        long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                        int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                        PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                        int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                        List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                        int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                        int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                        LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                        LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                        return measureResult;
                    }
                };
                $composer.updateRememberedValue(value$iv);
            }
            LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1111 = (LazyLayoutMeasurePolicy) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            return lazyLayoutMeasurePolicy1111;
        }
        f3 = f;
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072) {
            z6 = true;
        } else {
            z6 = false;
        }
        boolean z1111111116 = z1111111113 | z6;
        if (((3670016 & $changed) ^ 1572864) <= 1048576) {
        }
        if ((1572864 & $changed) == 1048576) {
            z7 = true;
        } else {
            z7 = false;
        }
        boolean z1111111117 = z1111111116 | z7;
        if (((234881024 & $changed) ^ 100663296) > 67108864) {
        }
        if ((100663296 & $changed) == 67108864) {
        }
        invalid$iv = z1111111117 | z8 | $composer.changed(graphicsContext);
        value$iv = $composer.rememberedValue();
        if (invalid$iv) {
            value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                /* JADX INFO: renamed from: measure-0kLqBqw */
                public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                    ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                    boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                    CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                    LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                    boolean isVertical = orientation == Orientation.Vertical;
                    LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                    int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                    int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                    int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                    int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                    int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                    long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                    PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                    int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                    PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                    int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                    List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                    int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                    int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                    LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                    LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                    return measureResult;
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = new LazyLayoutMeasurePolicy() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
                /* JADX INFO: renamed from: measure-0kLqBqw */
                public final MeasureResult mo921measure0kLqBqw(LazyLayoutMeasureScope $this$LazyLayoutMeasurePolicy, long constraints) {
                    ObservableScopeInvalidator.m1006attachToScopeimpl(lazyStaggeredGridState.m1051getMeasurementScopeInvalidatorzYiylxw$foundation());
                    boolean isInLookaheadScope = lazyStaggeredGridState.getHasLookaheadOccurred() || $this$LazyLayoutMeasurePolicy.isLookingAhead();
                    CheckScrollableContainerConstraintsKt.m292checkScrollableContainerConstraintsK40F9xA(constraints, orientation);
                    LazyStaggeredGridSlots resolvedSlots = slots.mo1026invoke0kLqBqw($this$LazyLayoutMeasurePolicy, constraints);
                    boolean isVertical = orientation == Orientation.Vertical;
                    LazyStaggeredGridItemProvider itemProvider = function1.invoke();
                    int beforeContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                    int afterContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(paddingValues, orientation, z4, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                    int startContentPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(paddingValues, orientation, $this$LazyLayoutMeasurePolicy.getLayoutDirection()));
                    int maxMainAxisSize = isVertical ? Constraints.m7857getMaxHeightimpl(constraints) : Constraints.m7858getMaxWidthimpl(constraints);
                    int mainAxisAvailableSize = (maxMainAxisSize - beforeContentPadding) - afterContentPadding;
                    long contentOffset = isVertical ? IntOffset.m8027constructorimpl((((long) startContentPadding) << 32) | (((long) beforeContentPadding) & 4294967295L)) : IntOffset.m8027constructorimpl((((long) beforeContentPadding) << 32) | (((long) startContentPadding) & 4294967295L));
                    PaddingValues $this$measure_0kLqBqw_u24lambda_u240 = paddingValues;
                    int horizontalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl(PaddingKt.calculateStartPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection()) + PaddingKt.calculateEndPadding($this$measure_0kLqBqw_u24lambda_u240, $this$LazyLayoutMeasurePolicy.getLayoutDirection())));
                    PaddingValues $this$measure_0kLqBqw_u24lambda_u241 = paddingValues;
                    int verticalPadding = $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(Dp.m7905constructorimpl($this$measure_0kLqBqw_u24lambda_u241.getTop() + $this$measure_0kLqBqw_u24lambda_u241.getBottom()));
                    List<Integer> listCalculateLazyLayoutPinnedIndices = LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(itemProvider, lazyStaggeredGridState.getPinnedItems(), lazyStaggeredGridState.getBeyondBoundsInfo());
                    int horizontalPadding2 = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, horizontalPadding);
                    int horizontalPadding3 = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, verticalPadding);
                    LazyStaggeredGridMeasureResult measureResult = LazyStaggeredGridMeasureKt.m1045measureStaggeredGridC6celF4($this$LazyLayoutMeasurePolicy, lazyStaggeredGridState, listCalculateLazyLayoutPinnedIndices, itemProvider, resolvedSlots, Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : horizontalPadding2, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : horizontalPadding3, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0), isVertical, z4, contentOffset, mainAxisAvailableSize, $this$LazyLayoutMeasurePolicy.mo400roundToPx0680j_4(f3), beforeContentPadding, afterContentPadding, coroutineScope, isInLookaheadScope, $this$LazyLayoutMeasurePolicy.isLookingAhead(), lazyStaggeredGridState.getApproachLayoutInfo(), graphicsContext);
                    LazyStaggeredGridState.applyMeasureResult$foundation$default(lazyStaggeredGridState, measureResult, $this$LazyLayoutMeasurePolicy.isLookingAhead(), false, 4, null);
                    return measureResult;
                }
            };
            $composer.updateRememberedValue(value$iv);
        }
        LazyLayoutMeasurePolicy lazyLayoutMeasurePolicy1112 = (LazyLayoutMeasurePolicy) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return lazyLayoutMeasurePolicy1112;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float startPadding(PaddingValues $this$startPadding, Orientation orientation, LayoutDirection layoutDirection) {
        switch (WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()]) {
            case 1:
                return PaddingKt.calculateStartPadding($this$startPadding, layoutDirection);
            case 2:
                return $this$startPadding.getTop();
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float beforePadding(PaddingValues $this$beforePadding, Orientation orientation, boolean reverseLayout, LayoutDirection layoutDirection) {
        switch (WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()]) {
            case 1:
                return reverseLayout ? $this$beforePadding.getBottom() : $this$beforePadding.getTop();
            case 2:
                if (reverseLayout) {
                    return PaddingKt.calculateEndPadding($this$beforePadding, layoutDirection);
                }
                return PaddingKt.calculateStartPadding($this$beforePadding, layoutDirection);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float afterPadding(PaddingValues $this$afterPadding, Orientation orientation, boolean reverseLayout, LayoutDirection layoutDirection) {
        switch (WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()]) {
            case 1:
                return reverseLayout ? $this$afterPadding.getTop() : $this$afterPadding.getBottom();
            case 2:
                if (reverseLayout) {
                    return PaddingKt.calculateStartPadding($this$afterPadding, layoutDirection);
                }
                return PaddingKt.calculateEndPadding($this$afterPadding, layoutDirection);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
