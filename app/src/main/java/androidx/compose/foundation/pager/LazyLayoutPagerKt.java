package androidx.compose.foundation.pager;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.ScrollableAreaKt;
import androidx.compose.foundation.gestures.BringIntoViewSpec;
import androidx.compose.foundation.gestures.BringIntoViewSpec_androidKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierLocalKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.reflect.KProperty0;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: LazyLayoutPager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aå\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182#\u0010\u0019\u001a\u001f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001a2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$21\u0010%\u001a-\u0012\u0004\u0012\u00020'\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u00010&¢\u0006\u0002\b)¢\u0006\u0002\b*H\u0001¢\u0006\u0004\b+\u0010,\u001a\u0081\u0001\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\u0006\u0010\u0004\u001a\u00020\u000521\u0010%\u001a-\u0012\u0004\u0012\u00020'\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u00010&¢\u0006\u0002\b)¢\u0006\u0002\b*2#\u0010\u0019\u001a\u001f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001a2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00120.H\u0003¢\u0006\u0002\u00101\u001a\u0014\u00102\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¨\u00063"}, d2 = {"Pager", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/pager/PagerState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "flingBehavior", "Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "userScrollEnabled", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "beyondViewportPageCount", "", "pageSpacing", "Landroidx/compose/ui/unit/Dp;", "pageSize", "Landroidx/compose/foundation/pager/PageSize;", "pageNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "key", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "index", "", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "snapPosition", "Landroidx/compose/foundation/gestures/snapping/SnapPosition;", "pageContent", "Lkotlin/Function2;", "Landroidx/compose/foundation/pager/PagerScope;", "page", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "Pager-eLwUrMk", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/gestures/TargetedFlingBehavior;ZLandroidx/compose/foundation/OverscrollEffect;IFLandroidx/compose/foundation/pager/PageSize;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/snapping/SnapPosition;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;III)V", "rememberPagerItemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "pageCount", "(Landroidx/compose/foundation/pager/PagerState;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function0;", "dragDirectionDetector", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyLayoutPagerKt {
    static final Unit Pager_eLwUrMk$lambda$5(Modifier modifier, PagerState pagerState, PaddingValues paddingValues, boolean z, Orientation orientation, TargetedFlingBehavior targetedFlingBehavior, boolean z2, OverscrollEffect overscrollEffect, int i, float f, PageSize pageSize, NestedScrollConnection nestedScrollConnection, Function1 function1, Alignment.Horizontal horizontal, Alignment.Vertical vertical, SnapPosition snapPosition, Function4 function4, int i2, int i3, int i4, Composer composer, int i5) {
        m1068PagereLwUrMk(modifier, pagerState, paddingValues, z, orientation, targetedFlingBehavior, z2, overscrollEffect, i, f, pageSize, nestedScrollConnection, function1, horizontal, vertical, snapPosition, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:172:0x02a0  */
    /* JADX WARN: Code duplicated, block: B:173:0x02b5  */
    /* JADX WARN: Code duplicated, block: B:176:0x02da  */
    /* JADX WARN: Code duplicated, block: B:177:0x02dd  */
    /* JADX WARN: Code duplicated, block: B:187:0x0364  */
    /* JADX WARN: Code duplicated, block: B:188:0x0367  */
    /* JADX WARN: Code duplicated, block: B:191:0x037b  */
    /* JADX WARN: Code duplicated, block: B:192:0x037e  */
    /* JADX WARN: Code duplicated, block: B:195:0x0386  */
    /* JADX WARN: Code duplicated, block: B:196:0x0389  */
    /* JADX WARN: Code duplicated, block: B:202:0x03a3  */
    /* JADX WARN: Code duplicated, block: B:205:0x03e5  */
    /* JADX WARN: Code duplicated, block: B:206:0x03e8  */
    /* JADX WARN: Code duplicated, block: B:212:0x0403  */
    /* JADX WARN: Code duplicated, block: B:215:0x0419  */
    /* JADX WARN: Code duplicated, block: B:216:0x0444  */
    /* JADX WARN: Code duplicated, block: B:219:0x0490  */
    /* JADX WARN: Code duplicated, block: B:220:0x0493  */
    /* JADX WARN: Code duplicated, block: B:223:0x04e6  */
    /* JADX INFO: renamed from: Pager-eLwUrMk, reason: not valid java name */
    public static final void m1068PagereLwUrMk(final Modifier modifier, final PagerState state, final PaddingValues contentPadding, final boolean reverseLayout, final Orientation orientation, TargetedFlingBehavior flingBehavior, final boolean userScrollEnabled, final OverscrollEffect overscrollEffect, int beyondViewportPageCount, float f, final PageSize pageSize, final NestedScrollConnection pageNestedScrollConnection, final Function1<? super Integer, ? extends Object> function1, final Alignment.Horizontal horizontalAlignment, final Alignment.Vertical verticalAlignment, final SnapPosition snapPosition, final Function4<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int $changed1, final int i) {
        int i2;
        PagerState pagerState;
        int $dirty;
        TargetedFlingBehavior targetedFlingBehavior;
        Composer $composer2;
        final float f2;
        final int beyondViewportPageCount2;
        int beyondViewportPageCount3;
        int $dirty2;
        Object value$iv$iv;
        boolean invalid$iv;
        Object it$iv;
        int beyondViewportPageCount4;
        boolean z;
        boolean z2;
        boolean z3;
        boolean invalid$iv2;
        Object it$iv2;
        BringIntoViewSpec defaultBringIntoViewSpec;
        boolean z4;
        boolean invalid$iv3;
        Object it$iv3;
        Modifier.Companion companionLazyLayoutBeyondBoundsModifier;
        boolean z5;
        Composer $composer3 = $composer.startRestartGroup(-572816025);
        ComposerKt.sourceInformation($composer3, "C(Pager)N(modifier,state,contentPadding,reverseLayout,orientation,flingBehavior,userScrollEnabled,overscrollEffect,beyondViewportPageCount,pageSpacing:c#ui.unit.Dp,pageSize,pageNestedScrollConnection,key,horizontalAlignment,verticalAlignment,snapPosition,pageContent)109@5362L39,109@5277L124,113@5428L24,129@6071L19,116@5486L615,132@6127L70,135@6239L82,137@6381L7,139@6430L121,164@7268L301,159@7099L1450:LazyLayoutPager.kt#g6yjnt");
        int $dirty3 = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changed(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty3 |= $composer3.changed(contentPadding) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty3 |= $composer3.changed(reverseLayout) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty3 |= $composer3.changed(orientation.ordinal()) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= $composer3.changed(flingBehavior) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            $dirty3 |= $composer3.changed(userScrollEnabled) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changed(overscrollEffect) ? 8388608 : 4194304;
        }
        int i3 = i & 256;
        if (i3 != 0) {
            $dirty3 |= 100663296;
            i2 = beyondViewportPageCount;
        } else if (($changed & 100663296) == 0) {
            i2 = beyondViewportPageCount;
            $dirty3 |= $composer3.changed(i2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = beyondViewportPageCount;
        }
        int i4 = i & 512;
        if (i4 != 0) {
            $dirty3 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty3 |= $composer3.changed(f) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(pageSize) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changedInstance(pageNestedScrollConnection) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty1 |= $composer3.changedInstance(function1) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty1 |= $composer3.changed(horizontalAlignment) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty1 |= $composer3.changed(verticalAlignment) ? 16384 : 8192;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= $composer3.changed(snapPosition) ? 131072 : 65536;
        }
        if (($changed1 & 1572864) == 0) {
            $dirty1 |= $composer3.changedInstance(function4) ? 1048576 : 524288;
        }
        if ($composer3.shouldExecute((($dirty3 & 306783379) == 306783378 && (599187 & $dirty1) == 599186) ? false : true, $dirty3 & 1)) {
            int beyondViewportPageCount5 = i3 != 0 ? 0 : i2;
            float fM7905constructorimpl = i4 != 0 ? Dp.m7905constructorimpl(0) : f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-572816025, $dirty3, $dirty1, "androidx.compose.foundation.pager.Pager (LazyLayoutPager.kt:102)");
            }
            if (!(beyondViewportPageCount5 >= 0)) {
                InlineClassHelperKt.throwIllegalArgumentException("beyondViewportPageCount should be greater than or equal to 0, you selected " + beyondViewportPageCount5);
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -720319826, "CC(remember):LazyLayoutPager.kt#9igjgp");
            boolean invalid$iv4 = ($dirty3 & 112) == 32;
            Object it$iv4 = $composer3.rememberedValue();
            if (invalid$iv4) {
                beyondViewportPageCount3 = beyondViewportPageCount5;
            } else {
                beyondViewportPageCount3 = beyondViewportPageCount5;
                if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $dirty2 = $dirty3;
                int $dirty4 = $dirty1;
                Function0<PagerLazyLayoutItemProvider> function0RememberPagerItemProviderLambda = rememberPagerItemProviderLambda(state, function4, function1, (Function0) it$iv4, $composer3, (($dirty3 >> 3) & 14) | (($dirty1 >> 15) & 112) | ($dirty1 & 896));
                ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart($composer3, 683736516, "CC(remember):Effects.kt#9igjgp");
                value$iv$iv = $composer3.rememberedValue();
                if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3);
                    $composer3.updateRememberedValue(value$iv$iv);
                }
                CoroutineScope coroutineScope = (CoroutineScope) value$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, -720297158, "CC(remember):LazyLayoutPager.kt#9igjgp");
                if (($dirty2 & 112) == 32) {
                    invalid$iv = true;
                } else {
                    invalid$iv = false;
                }
                it$iv = $composer3.rememberedValue();
                if (!invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = new Function0() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Integer.valueOf(state.getPageCount());
                        }
                    };
                    $composer3.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                beyondViewportPageCount4 = beyondViewportPageCount3;
                $dirty = $dirty2;
                LazyLayoutMeasurePolicy measurePolicy = PagerMeasurePolicyKt.m1080rememberPagerMeasurePolicy8u0NR3k(function0RememberPagerItemProviderLambda, state, contentPadding, reverseLayout, orientation, beyondViewportPageCount4, fM7905constructorimpl, pageSize, horizontalAlignment, verticalAlignment, snapPosition, coroutineScope, (Function0) it$iv, $composer3, ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (($dirty2 >> 9) & 458752) | (($dirty2 >> 9) & 3670016) | (($dirty4 << 21) & 29360128) | (($dirty4 << 15) & 234881024) | (($dirty4 << 15) & 1879048192), ($dirty4 >> 15) & 14);
                pagerState = state;
                float f3 = fM7905constructorimpl;
                if (orientation == Orientation.Vertical) {
                    z = true;
                } else {
                    z = false;
                }
                LazyLayoutSemanticState semanticState = PagerSemanticsKt.rememberPagerSemanticState(pagerState, z, $composer3, ($dirty >> 3) & 14);
                ComposerKt.sourceInformationMarkerStart($composer3, -720291719, "CC(remember):LazyLayoutPager.kt#9igjgp");
                if (($dirty & 112) == 32) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (($dirty & 458752) == 131072) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                invalid$iv2 = z2 | z3;
                it$iv2 = $composer3.rememberedValue();
                if (!invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    targetedFlingBehavior = flingBehavior;
                    Object value$iv2 = new PagerWrapperFlingBehavior(targetedFlingBehavior, pagerState);
                    $composer3.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                } else {
                    targetedFlingBehavior = flingBehavior;
                }
                PagerWrapperFlingBehavior resolvedFlingBehavior = (PagerWrapperFlingBehavior) it$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ProvidableCompositionLocal<BringIntoViewSpec> localBringIntoViewSpec = BringIntoViewSpec_androidKt.getLocalBringIntoViewSpec();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer3.consume(localBringIntoViewSpec);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                defaultBringIntoViewSpec = (BringIntoViewSpec) objConsume;
                ComposerKt.sourceInformationMarkerStart($composer3, -720285568, "CC(remember):LazyLayoutPager.kt#9igjgp");
                if (($dirty & 112) == 32) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                invalid$iv3 = z4 | $composer3.changed(defaultBringIntoViewSpec);
                it$iv3 = $composer3.rememberedValue();
                if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = new PagerBringIntoViewSpec(pagerState, defaultBringIntoViewSpec);
                    $composer3.updateRememberedValue(value$iv3);
                    it$iv3 = value$iv3;
                }
                PagerBringIntoViewSpec pagerBringIntoViewSpec = (PagerBringIntoViewSpec) it$iv3;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (userScrollEnabled) {
                    $composer3.startReplaceGroup(-853822717);
                    ComposerKt.sourceInformation($composer3, "147@6714L167");
                    companionLazyLayoutBeyondBoundsModifier = LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(Modifier.INSTANCE, PagerBeyondBoundsModifierKt.rememberPagerBeyondBoundsState(pagerState, beyondViewportPageCount4, $composer3, (($dirty >> 3) & 14) | (($dirty >> 21) & 112)), pagerState.getBeyondBoundsInfo(), reverseLayout, orientation);
                    $composer3.endReplaceGroup();
                } else {
                    $composer3.startReplaceGroup(-853392933);
                    $composer3.endReplaceGroup();
                    companionLazyLayoutBeyondBoundsModifier = Modifier.INSTANCE;
                }
                Modifier beyondBoundsModifier = companionLazyLayoutBeyondBoundsModifier;
                Modifier modifierLazyLayoutSemantics = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(pagerState.getRemeasurementModifier()).then(pagerState.getAwaitLayoutModifier()), function0RememberPagerItemProviderLambda, semanticState, orientation, userScrollEnabled, reverseLayout, $composer3, (($dirty << 6) & 458752) | (($dirty >> 3) & 7168) | (($dirty >> 6) & 57344));
                $composer2 = $composer3;
                if (orientation == Orientation.Vertical) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                LazyLayoutKt.LazyLayout(function0RememberPagerItemProviderLambda, NestedScrollModifierKt.nestedScroll$default(dragDirectionDetector(ScrollableAreaKt.scrollableArea(PagerKt.pagerSemantics(modifierLazyLayoutSemantics, pagerState, z5, coroutineScope, userScrollEnabled).then(beyondBoundsModifier), pagerState, orientation, overscrollEffect, userScrollEnabled, reverseLayout, resolvedFlingBehavior, pagerState.getInternalInteractionSource(), pagerBringIntoViewSpec), pagerState), pageNestedScrollConnection, null, 2, null), pagerState.getPrefetchState(), measurePolicy, $composer2, 0, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                beyondViewportPageCount2 = beyondViewportPageCount4;
                f2 = f3;
            }
            Object value$iv4 = new Function0() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Integer.valueOf(state.getPageCount());
                }
            };
            $composer3.updateRememberedValue(value$iv4);
            it$iv4 = value$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $dirty2 = $dirty3;
            int $dirty5 = $dirty1;
            Function0<PagerLazyLayoutItemProvider> function0RememberPagerItemProviderLambda2 = rememberPagerItemProviderLambda(state, function4, function1, (Function0) it$iv4, $composer3, (($dirty3 >> 3) & 14) | (($dirty1 >> 15) & 112) | ($dirty1 & 896));
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, 683736516, "CC(remember):Effects.kt#9igjgp");
            value$iv$iv = $composer3.rememberedValue();
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3);
                $composer3.updateRememberedValue(value$iv$iv);
            }
            CoroutineScope coroutineScope2 = (CoroutineScope) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -720297158, "CC(remember):LazyLayoutPager.kt#9igjgp");
            if (($dirty2 & 112) == 32) {
                invalid$iv = true;
            } else {
                invalid$iv = false;
            }
            it$iv = $composer3.rememberedValue();
            if (!invalid$iv) {
            }
            Object value$iv5 = new Function0() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Integer.valueOf(state.getPageCount());
                }
            };
            $composer3.updateRememberedValue(value$iv5);
            it$iv = value$iv5;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            beyondViewportPageCount4 = beyondViewportPageCount3;
            $dirty = $dirty2;
            LazyLayoutMeasurePolicy measurePolicy2 = PagerMeasurePolicyKt.m1080rememberPagerMeasurePolicy8u0NR3k(function0RememberPagerItemProviderLambda2, state, contentPadding, reverseLayout, orientation, beyondViewportPageCount4, fM7905constructorimpl, pageSize, horizontalAlignment, verticalAlignment, snapPosition, coroutineScope2, (Function0) it$iv, $composer3, ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (($dirty2 >> 9) & 458752) | (($dirty2 >> 9) & 3670016) | (($dirty5 << 21) & 29360128) | (($dirty5 << 15) & 234881024) | (($dirty5 << 15) & 1879048192), ($dirty5 >> 15) & 14);
            pagerState = state;
            float f4 = fM7905constructorimpl;
            if (orientation == Orientation.Vertical) {
                z = true;
            } else {
                z = false;
            }
            LazyLayoutSemanticState semanticState2 = PagerSemanticsKt.rememberPagerSemanticState(pagerState, z, $composer3, ($dirty >> 3) & 14);
            ComposerKt.sourceInformationMarkerStart($composer3, -720291719, "CC(remember):LazyLayoutPager.kt#9igjgp");
            if (($dirty & 112) == 32) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (($dirty & 458752) == 131072) {
                z3 = true;
            } else {
                z3 = false;
            }
            invalid$iv2 = z2 | z3;
            it$iv2 = $composer3.rememberedValue();
            if (invalid$iv2) {
                targetedFlingBehavior = flingBehavior;
                Object value$iv6 = new PagerWrapperFlingBehavior(targetedFlingBehavior, pagerState);
                $composer3.updateRememberedValue(value$iv6);
                it$iv2 = value$iv6;
            } else {
                targetedFlingBehavior = flingBehavior;
                Object value$iv7 = new PagerWrapperFlingBehavior(targetedFlingBehavior, pagerState);
                $composer3.updateRememberedValue(value$iv7);
                it$iv2 = value$iv7;
            }
            PagerWrapperFlingBehavior resolvedFlingBehavior2 = (PagerWrapperFlingBehavior) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ProvidableCompositionLocal<BringIntoViewSpec> localBringIntoViewSpec2 = BringIntoViewSpec_androidKt.getLocalBringIntoViewSpec();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localBringIntoViewSpec2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            defaultBringIntoViewSpec = (BringIntoViewSpec) objConsume2;
            ComposerKt.sourceInformationMarkerStart($composer3, -720285568, "CC(remember):LazyLayoutPager.kt#9igjgp");
            if (($dirty & 112) == 32) {
                z4 = true;
            } else {
                z4 = false;
            }
            invalid$iv3 = z4 | $composer3.changed(defaultBringIntoViewSpec);
            it$iv3 = $composer3.rememberedValue();
            if (invalid$iv3) {
                Object value$iv8 = new PagerBringIntoViewSpec(pagerState, defaultBringIntoViewSpec);
                $composer3.updateRememberedValue(value$iv8);
                it$iv3 = value$iv8;
            } else {
                Object value$iv9 = new PagerBringIntoViewSpec(pagerState, defaultBringIntoViewSpec);
                $composer3.updateRememberedValue(value$iv9);
                it$iv3 = value$iv9;
            }
            PagerBringIntoViewSpec pagerBringIntoViewSpec2 = (PagerBringIntoViewSpec) it$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (userScrollEnabled) {
                $composer3.startReplaceGroup(-853822717);
                ComposerKt.sourceInformation($composer3, "147@6714L167");
                companionLazyLayoutBeyondBoundsModifier = LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(Modifier.INSTANCE, PagerBeyondBoundsModifierKt.rememberPagerBeyondBoundsState(pagerState, beyondViewportPageCount4, $composer3, (($dirty >> 3) & 14) | (($dirty >> 21) & 112)), pagerState.getBeyondBoundsInfo(), reverseLayout, orientation);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-853392933);
                $composer3.endReplaceGroup();
                companionLazyLayoutBeyondBoundsModifier = Modifier.INSTANCE;
            }
            Modifier beyondBoundsModifier2 = companionLazyLayoutBeyondBoundsModifier;
            Modifier modifierLazyLayoutSemantics2 = LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(pagerState.getRemeasurementModifier()).then(pagerState.getAwaitLayoutModifier()), function0RememberPagerItemProviderLambda2, semanticState2, orientation, userScrollEnabled, reverseLayout, $composer3, (($dirty << 6) & 458752) | (($dirty >> 3) & 7168) | (($dirty >> 6) & 57344));
            $composer2 = $composer3;
            if (orientation == Orientation.Vertical) {
                z5 = true;
            } else {
                z5 = false;
            }
            LazyLayoutKt.LazyLayout(function0RememberPagerItemProviderLambda2, NestedScrollModifierKt.nestedScroll$default(dragDirectionDetector(ScrollableAreaKt.scrollableArea(PagerKt.pagerSemantics(modifierLazyLayoutSemantics2, pagerState, z5, coroutineScope2, userScrollEnabled).then(beyondBoundsModifier2), pagerState, orientation, overscrollEffect, userScrollEnabled, reverseLayout, resolvedFlingBehavior2, pagerState.getInternalInteractionSource(), pagerBringIntoViewSpec2), pagerState), pageNestedScrollConnection, null, 2, null), pagerState.getPrefetchState(), measurePolicy2, $composer2, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            beyondViewportPageCount2 = beyondViewportPageCount4;
            f2 = f4;
        } else {
            pagerState = state;
            $dirty = $dirty3;
            targetedFlingBehavior = flingBehavior;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            f2 = f;
            beyondViewportPageCount2 = i2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final PagerState pagerState2 = pagerState;
            final TargetedFlingBehavior targetedFlingBehavior2 = targetedFlingBehavior;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LazyLayoutPagerKt.Pager_eLwUrMk$lambda$5(modifier, pagerState2, contentPadding, reverseLayout, orientation, targetedFlingBehavior2, userScrollEnabled, overscrollEffect, beyondViewportPageCount2, f2, pageSize, pageNestedScrollConnection, function1, horizontalAlignment, verticalAlignment, snapPosition, function4, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final Function0<PagerLazyLayoutItemProvider> rememberPagerItemProviderLambda(final PagerState state, Function4<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Function1<? super Integer, ? extends Object> function1, final Function0<Integer> function0, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1052364153, "C(rememberPagerItemProviderLambda)N(state,pageContent,key,pageCount)258@10763L33,259@10817L25,260@10854L742:LazyLayoutPager.kt#g6yjnt");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1052364153, $changed, -1, "androidx.compose.foundation.pager.rememberPagerItemProviderLambda (LazyLayoutPager.kt:257)");
        }
        final State latestContent = SnapshotStateKt.rememberUpdatedState(function4, $composer, ($changed >> 3) & 14);
        final State latestKey = SnapshotStateKt.rememberUpdatedState(function1, $composer, ($changed >> 6) & 14);
        ComposerKt.sourceInformationMarkerStart($composer, 2004647903, "CC(remember):LazyLayoutPager.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(state)) || ($changed & 6) == 4) | $composer.changed(latestContent) | $composer.changed(latestKey) | (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(function0)) || ($changed & 3072) == 2048);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            final State intervalContentState = SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new Function0() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return LazyLayoutPagerKt.rememberPagerItemProviderLambda$lambda$0$0(latestContent, latestKey, function0);
                }
            });
            final State itemProviderState = SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new Function0() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return LazyLayoutPagerKt.rememberPagerItemProviderLambda$lambda$0$1(intervalContentState, state);
                }
            });
            Object value$iv = (KProperty0) new PropertyReference0Impl(itemProviderState) { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$1
                @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return ((State) this.receiver).getValue();
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        KProperty0 kProperty0 = (KProperty0) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return kProperty0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PagerLayoutIntervalContent rememberPagerItemProviderLambda$lambda$0$0(State $latestContent, State $latestKey, Function0 $pageCount) {
        return new PagerLayoutIntervalContent((Function4) $latestContent.getValue(), (Function1) $latestKey.getValue(), ((Number) $pageCount.invoke()).intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PagerLazyLayoutItemProvider rememberPagerItemProviderLambda$lambda$0$1(State $intervalContentState, PagerState $state) {
        PagerLayoutIntervalContent intervalContent = (PagerLayoutIntervalContent) $intervalContentState.getValue();
        NearestRangeKeyIndexMap map = new NearestRangeKeyIndexMap($state.getNearestRange$foundation(), intervalContent);
        return new PagerLazyLayoutItemProvider($state, intervalContent, map);
    }

    private static final Modifier dragDirectionDetector(Modifier $this$dragDirectionDetector, final PagerState state) {
        return $this$dragDirectionDetector.then(SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, state, new PointerInputEventHandler() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt.dragDirectionDetector.1

            /* JADX INFO: renamed from: androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: LazyLayoutPager.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1", f = "LazyLayoutPager.kt", i = {}, l = {285}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class C00251 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PagerState $state;
                final /* synthetic */ PointerInputScope $this_pointerInput;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00251(PointerInputScope pointerInputScope, PagerState pagerState, Continuation<? super C00251> continuation) {
                    super(2, continuation);
                    this.$this_pointerInput = pointerInputScope;
                    this.$state = pagerState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00251(this.$this_pointerInput, this.$state, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00251) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX INFO: renamed from: androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: LazyLayoutPager.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1$1", f = "LazyLayoutPager.kt", i = {0, 1, 1, 1}, l = {287, 291}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "downEvent", "upEventOrCancellation"}, s = {"L$0", "L$0", "L$1", "L$2"}, v = 1)
                static final class C00261 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ PagerState $state;
                    private /* synthetic */ Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00261(PagerState pagerState, Continuation<? super C00261> continuation) {
                        super(2, continuation);
                        this.$state = pagerState;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        C00261 c00261 = new C00261(this.$state, continuation);
                        c00261.L$0 = obj;
                        return c00261;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                        return ((C00261) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Code duplicated, block: B:20:0x00a1  */
                    /* JADX WARN: Code duplicated, block: B:23:0x00b7 A[LOOP:0: B:19:0x009f->B:23:0x00b7, LOOP_END] */
                    /* JADX WARN: Code duplicated, block: B:31:0x00b5 A[SYNTHETIC] */
                    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0085 -> B:18:0x008e). Please report as a decompilation issue!!! */
                    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
                        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                        */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final java.lang.Object invokeSuspend(java.lang.Object r22) {
                        /*
                            Method dump skipped, instruction units count: 246
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.LazyLayoutPagerKt.AnonymousClass1.C00251.C00261.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            this.label = 1;
                            if (ForEachGestureKt.awaitEachGesture(this.$this_pointerInput, new C00261(this.$state, null), this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            break;
                        case 1:
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C00251($this$pointerInput, state, null), continuation);
                return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
            }
        }));
    }
}
