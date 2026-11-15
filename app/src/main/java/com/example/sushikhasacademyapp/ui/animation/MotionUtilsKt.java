package com.example.sushikhasacademyapp.ui.animation;

import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteractionKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyListItemInfo;
import androidx.compose.foundation.lazy.LazyListLayoutInfo;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.TextFieldImplKt;
import androidx.compose.material3.FloatingActionButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.common.RotationOptions;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MotionUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000~\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0011\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u001a\u001c\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a\u001e\u0010\n\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r\u001aM\u0010\u000e\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u0016¢\u0006\u0004\b\u0017\u0010\u0018\u001a?\u0010\u0019\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\f\u001a\u00020\r2\u000e\b\u0002\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u001d2\b\b\u0002\u0010\u0015\u001a\u00020\u0016¢\u0006\u0004\b\u001e\u0010\u001f\u001aT\u0010 \u001a\u00020\u0006\"\u0004\b\u0000\u0010!2\u0006\u0010\"\u001a\u0002H!2\b\b\u0002\u0010#\u001a\u00020\u00012\u0014\b\u0002\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u00020\u00160%2\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u00020\u00060%¢\u0006\u0002\b'H\u0007¢\u0006\u0002\u0010(\u001a<\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u00162\b\b\u0002\u0010#\u001a\u00020\u00012\b\b\u0002\u0010+\u001a\u00020\u00162\u0011\u0010&\u001a\r\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b'H\u0007¢\u0006\u0002\u0010,\u001aI\u0010-\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u00032\b\b\u0002\u0010#\u001a\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010/\u001a\u00020\u00102\b\b\u0002\u00100\u001a\u00020\u001b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0004\b1\u00102\u001aI\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u00162\b\b\u0002\u0010#\u001a\u00020\u00012\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u0002062\b\b\u0002\u00108\u001a\u0002092\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007¢\u0006\u0004\b:\u0010;\u001a?\u0010<\u001a\u00020\u00062\u0006\u0010=\u001a\u00020\t2\b\b\u0002\u0010#\u001a\u00020\u00012\b\b\u0002\u0010>\u001a\u00020\u00102\b\b\u0002\u0010?\u001a\u00020\u00162\b\b\u0002\u0010@\u001a\u00020\u001bH\u0007¢\u0006\u0004\bA\u0010B\u001aE\u0010C\u001a\u00020\u00062\u0006\u0010D\u001a\u00020\t2\b\b\u0002\u0010#\u001a\u00020\u00012\u0011\u0010E\u001a\r\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b'2\u0011\u0010&\u001a\r\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b'H\u0007¢\u0006\u0002\u0010F\u001a\u001c\u0010G\u001a\u00020\u0001*\u00020\u00012\u0006\u0010H\u001a\u00020I2\b\b\u0002\u0010J\u001a\u00020\u0003\u001a\u001c\u0010K\u001a\u00020\u0001*\u00020\u00012\u0006\u0010H\u001a\u00020I2\b\b\u0002\u0010L\u001a\u00020\u0003\u001a\u001a\u0010M\u001a\u00020\u0001*\u00020\u00012\u0006\u0010N\u001a\u00020O2\u0006\u0010*\u001a\u00020\u0016\u001a?\u0010P\u001a\u00020\u00062\u0006\u0010H\u001a\u00020I2\b\b\u0002\u0010#\u001a\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010/\u001a\u00020\u00102\b\b\u0002\u00100\u001a\u00020\u001bH\u0007¢\u0006\u0004\bQ\u0010R\u001a=\u0010P\u001a\u00020\u00062\u0006\u0010N\u001a\u00020O2\u0006\u0010S\u001a\u00020\u00162\b\b\u0002\u0010#\u001a\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u00100\u001a\u00020\u001bH\u0007¢\u0006\u0004\bT\u0010U\u001a3\u0010V\u001a\u00020\u00062\u0006\u0010H\u001a\u00020I2\b\b\u0002\u0010#\u001a\u00020\u00012\b\b\u0002\u0010W\u001a\u00020\u00162\b\b\u0002\u0010X\u001a\u00020YH\u0007¢\u0006\u0002\u0010Z\u001a3\u0010V\u001a\u00020\u00062\u0006\u0010N\u001a\u00020O2\b\b\u0002\u0010#\u001a\u00020\u00012\b\b\u0002\u0010W\u001a\u00020\u00162\b\b\u0002\u0010X\u001a\u00020YH\u0007¢\u0006\u0002\u0010[\u001a#\u0010\\\u001a\u00020\u0001*\u00020\u00012\u0006\u0010H\u001a\u00020I2\b\b\u0002\u0010]\u001a\u00020\u001b¢\u0006\u0004\b^\u0010_¨\u0006`²\u0006\n\u0010\b\u001a\u00020\tX\u008a\u0084\u0002²\u0006\n\u0010a\u001a\u00020\u0003X\u008a\u0084\u0002²\u0006\n\u0010a\u001a\u00020\u0003X\u008a\u0084\u0002²\u0006\n\u0010b\u001a\u00020\u0003X\u008a\u0084\u0002²\u0006\n\u0010c\u001a\u00020\u0003X\u008a\u0084\u0002²\u0006\n\u0010a\u001a\u00020\u0003X\u008a\u0084\u0002²\u0006\n\u0010d\u001a\u00020\u0003X\u008a\u0084\u0002²\u0006\n\u0010e\u001a\u00020\u0016X\u008a\u008e\u0002²\u0006\n\u0010f\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010g\u001a\u00020\u0003X\u008a\u0084\u0002²\u0006\n\u0010h\u001a\u00020\u0016X\u008a\u0084\u0002²\u0006\n\u0010a\u001a\u00020\u0003X\u008a\u0084\u0002²\u0006\n\u0010i\u001a\u00020\u0003X\u008a\u0084\u0002²\u0006\n\u0010a\u001a\u00020\u0003X\u008a\u0084\u0002²\u0006\n\u0010c\u001a\u00020\u0003X\u008a\u0084\u0002²\u0006\n\u0010g\u001a\u00020\u0003X\u008a\u0084\u0002²\u0006\n\u0010g\u001a\u00020\u0003X\u008a\u0084\u0002²\u0006\n\u0010j\u001a\u00020\u001bX\u008a\u0084\u0002"}, d2 = {"bounceClick", "Landroidx/compose/ui/Modifier;", "scaleDown", "", "onClick", "Lkotlin/Function0;", "", "pressScale", "isPressed", "", "shimmerLoading", "enabled", "shape", "Landroidx/compose/ui/graphics/Shape;", "pulseGlow", "color", "Landroidx/compose/ui/graphics/Color;", "minAlpha", "maxAlpha", "minScale", "maxScale", "durationMillis", "", "pulseGlow-sW7UJKQ", "(Landroidx/compose/ui/Modifier;JFFFFI)Landroidx/compose/ui/Modifier;", "animatedGradientBorder", "borderWidth", "Landroidx/compose/ui/unit/Dp;", "colors", "", "animatedGradientBorder-rAjV9yQ", "(Landroidx/compose/ui/Modifier;FLandroidx/compose/ui/graphics/Shape;Ljava/util/List;I)Landroidx/compose/ui/Modifier;", "AnimatedTabContent", ExifInterface.GPS_DIRECTION_TRUE, "targetState", "modifier", "getTabIndex", "Lkotlin/Function1;", UriUtil.LOCAL_CONTENT_SCHEME, "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "StaggeredAnimatedItem", "index", "delayMultiplier", "(ILandroidx/compose/ui/Modifier;ILkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "AnimatedProgressBar", "progress", "trackColor", "height", "AnimatedProgressBar-_UMDTes", "(FLandroidx/compose/ui/Modifier;JJFLandroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;II)V", "AnimatedNumberCounter", "targetNumber", "prefix", "", "suffix", TtmlNode.TAG_STYLE, "Landroidx/compose/ui/text/TextStyle;", "AnimatedNumberCounter-V-9fs2A", "(ILandroidx/compose/ui/Modifier;Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;JLandroidx/compose/runtime/Composer;II)V", "AudioWaveVisualizer", "isPlaying", "barColor", "barCount", "maxHeight", "AudioWaveVisualizer-u4SGt0E", "(ZLandroidx/compose/ui/Modifier;JIFLandroidx/compose/runtime/Composer;II)V", "AnimatedExpandableCard", "isExpanded", "header", "(ZLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "parallaxScroll", "scrollState", "Landroidx/compose/foundation/ScrollState;", "rate", "scrollEntranceEffect", "thresholdOffset", "lazyItemScrollAnimation", "lazyListState", "Landroidx/compose/foundation/lazy/LazyListState;", "ScrollProgressIndicator", "ScrollProgressIndicator-pc5RIQQ", "(Landroidx/compose/foundation/ScrollState;Landroidx/compose/ui/Modifier;JJFLandroidx/compose/runtime/Composer;II)V", "totalItems", "ScrollProgressIndicator-q9LK7_k", "(Landroidx/compose/foundation/lazy/LazyListState;ILandroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "ScrollToTopFAB", "threshold", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroidx/compose/foundation/ScrollState;Landroidx/compose/ui/Modifier;ILkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/ui/Modifier;ILkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/Composer;II)V", "elevateOnScroll", "maxElevation", "elevateOnScroll-wH6b6FI", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/ScrollState;F)Landroidx/compose/ui/Modifier;", "app", "scale", "translateAnim", "alpha", TypedValues.CycleType.S_WAVE_OFFSET, "previousIndex", "isVisible", "animatedProgress", "animatedValue", "scaleFactor", "elevation"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class MotionUtilsKt {
    static final Unit AnimatedExpandableCard$lambda$53(boolean z, Modifier modifier, Function2 function2, Function2 function3, int i, int i2, Composer composer, int i3) {
        AnimatedExpandableCard(z, modifier, function2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AnimatedNumberCounter_V_9fs2A$lambda$46(int i, Modifier modifier, String str, String str2, TextStyle textStyle, long j, int i2, int i3, Composer composer, int i4) {
        m8736AnimatedNumberCounterV9fs2A(i, modifier, str, str2, textStyle, j, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit AnimatedProgressBar__UMDTes$lambda$44(float f, Modifier modifier, long j, long j2, float f2, Shape shape, int i, int i2, Composer composer, int i3) {
        m8737AnimatedProgressBar_UMDTes(f, modifier, j, j2, f2, shape, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AnimatedTabContent$lambda$33(Object obj, Modifier modifier, Function1 function1, Function3 function3, int i, int i2, Composer composer, int i3) {
        AnimatedTabContent(obj, modifier, function1, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AudioWaveVisualizer_u4SGt0E$lambda$50(boolean z, Modifier modifier, long j, int i, float f, int i2, int i3, Composer composer, int i4) {
        m8738AudioWaveVisualizeru4SGt0E(z, modifier, j, i, f, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit ScrollProgressIndicator_pc5RIQQ$lambda$70(ScrollState scrollState, Modifier modifier, long j, long j2, float f, int i, int i2, Composer composer, int i3) {
        m8739ScrollProgressIndicatorpc5RIQQ(scrollState, modifier, j, j2, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ScrollProgressIndicator_q9LK7_k$lambda$73(LazyListState lazyListState, int i, Modifier modifier, long j, float f, int i2, int i3, Composer composer, int i4) {
        m8740ScrollProgressIndicatorq9LK7_k(lazyListState, i, modifier, j, f, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit ScrollToTopFAB$lambda$81(ScrollState scrollState, Modifier modifier, int i, CoroutineScope coroutineScope, int i2, int i3, Composer composer, int i4) {
        ScrollToTopFAB(scrollState, modifier, i, coroutineScope, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit ScrollToTopFAB$lambda$89(LazyListState lazyListState, Modifier modifier, int i, CoroutineScope coroutineScope, int i2, int i3, Composer composer, int i4) {
        ScrollToTopFAB(lazyListState, modifier, i, coroutineScope, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit StaggeredAnimatedItem$lambda$41(int i, Modifier modifier, int i2, Function2 function2, int i3, int i4, Composer composer, int i5) {
        StaggeredAnimatedItem(i, modifier, i2, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Modifier bounceClick$default(Modifier modifier, float f, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.94f;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        return bounceClick(modifier, f, function0);
    }

    public static final Modifier bounceClick(Modifier $this$bounceClick, final float scaleDown, final Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter($this$bounceClick, "<this>");
        return ComposedModifierKt.composed$default($this$bounceClick, null, new Function3() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda36
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MotionUtilsKt.bounceClick$lambda$5(scaleDown, function0, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, null);
    }

    static final Modifier bounceClick$lambda$5(float $scaleDown, Function0 $onClick, Modifier composed, Composer $composer, int $changed) {
        Modifier.Companion companionM295clickableO2vRcR0;
        Intrinsics.checkNotNullParameter(composed, "$this$composed");
        $composer.startReplaceGroup(-1135666624);
        ComposerKt.sourceInformation($composer, "C45@1831L39,46@1910L25,48@1954L265,58@2253L65:MotionUtils.kt#b4fcsz");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1135666624, $changed, -1, "com.example.sushikhasacademyapp.ui.animation.bounceClick.<anonymous> (MotionUtils.kt:45)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -453931481, "CC(remember):MotionUtils.kt#9igjgp");
        Object objRememberedValue = $composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object objMutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
            $composer.updateRememberedValue(objMutableInteractionSource);
            objRememberedValue = objMutableInteractionSource;
        }
        MutableInteractionSource interactionSource = (MutableInteractionSource) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd($composer);
        final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(bounceClick$lambda$5$lambda$1(PressInteractionKt.collectIsPressedAsState(interactionSource, $composer, 6)) ? $scaleDown : 1.0f, AnimationSpecKt.spring$default(0.5f, 400.0f, null, 4, null), 0.0f, "bounceScale", null, $composer, 3120, 20);
        ComposerKt.sourceInformationMarkerStart($composer, -453917951, "CC(remember):MotionUtils.kt#9igjgp");
        boolean zChanged = $composer.changed(stateAnimateFloatAsState);
        Object objRememberedValue2 = $composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return MotionUtilsKt.bounceClick$lambda$5$lambda$4$lambda$3(stateAnimateFloatAsState, (GraphicsLayerScope) obj2);
                }
            };
            $composer.updateRememberedValue(obj);
            objRememberedValue2 = obj;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(composed, (Function1) objRememberedValue2);
        if ($onClick == null) {
            companionM295clickableO2vRcR0 = Modifier.INSTANCE;
        } else {
            companionM295clickableO2vRcR0 = ClickableKt.m295clickableO2vRcR0(Modifier.INSTANCE, interactionSource, null, (28 & 4) != 0, (28 & 8) != 0 ? null : null, (28 & 16) != 0 ? null : null, $onClick);
        }
        Modifier modifierThen = modifierGraphicsLayer.then(companionM295clickableO2vRcR0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierThen;
    }

    private static final boolean bounceClick$lambda$5$lambda$1(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final float bounceClick$lambda$5$lambda$2(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Unit bounceClick$lambda$5$lambda$4$lambda$3(State $scale$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(bounceClick$lambda$5$lambda$2($scale$delegate));
        graphicsLayer.setScaleY(bounceClick$lambda$5$lambda$2($scale$delegate));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Modifier pressScale$default(Modifier modifier, boolean z, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = 0.95f;
        }
        return pressScale(modifier, z, f);
    }

    public static final Modifier pressScale(Modifier $this$pressScale, final boolean isPressed, final float scaleDown) {
        Intrinsics.checkNotNullParameter($this$pressScale, "<this>");
        return ComposedModifierKt.composed$default($this$pressScale, null, new Function3() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda34
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MotionUtilsKt.pressScale$lambda$9(isPressed, scaleDown, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, null);
    }

    private static final float pressScale$lambda$9$lambda$6(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Modifier pressScale$lambda$9(boolean $isPressed, float $scaleDown, Modifier composed, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composed, "$this$composed");
        $composer.startReplaceGroup(-471974503);
        ComposerKt.sourceInformation($composer, "C82@2866L258,90@3148L53:MotionUtils.kt#b4fcsz");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-471974503, $changed, -1, "com.example.sushikhasacademyapp.ui.animation.pressScale.<anonymous> (MotionUtils.kt:82)");
        }
        final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState($isPressed ? $scaleDown : 1.0f, AnimationSpecKt.spring$default(0.5f, 200.0f, null, 4, null), 0.0f, "pressScale", null, $composer, 3120, 20);
        ComposerKt.sourceInformationMarkerStart($composer, 1699045678, "CC(remember):MotionUtils.kt#9igjgp");
        boolean zChanged = $composer.changed(stateAnimateFloatAsState);
        Object objRememberedValue = $composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return MotionUtilsKt.pressScale$lambda$9$lambda$8$lambda$7(stateAnimateFloatAsState, (GraphicsLayerScope) obj2);
                }
            };
            $composer.updateRememberedValue(obj);
            objRememberedValue = obj;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(composed, (Function1) objRememberedValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierGraphicsLayer;
    }

    static final Unit pressScale$lambda$9$lambda$8$lambda$7(State $scale$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(pressScale$lambda$9$lambda$6($scale$delegate));
        graphicsLayer.setScaleY(pressScale$lambda$9$lambda$6($scale$delegate));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Modifier shimmerLoading$default(Modifier modifier, boolean z, Shape shape, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            shape = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
        }
        return shimmerLoading(modifier, z, shape);
    }

    public static final Modifier shimmerLoading(Modifier $this$shimmerLoading, final boolean enabled, final Shape shape) {
        Intrinsics.checkNotNullParameter($this$shimmerLoading, "<this>");
        Intrinsics.checkNotNullParameter(shape, "shape");
        return ComposedModifierKt.composed$default($this$shimmerLoading, null, new Function3() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda30
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MotionUtilsKt.shimmerLoading$lambda$11(enabled, shape, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, null);
    }

    static final Modifier shimmerLoading$lambda$11(boolean $enabled, Shape $shape, Modifier composed, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composed, "$this$composed");
        $composer.startReplaceGroup(812487122);
        ComposerKt.sourceInformation($composer, "C105@3470L55,106@3562L286:MotionUtils.kt#b4fcsz");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(812487122, $changed, -1, "com.example.sushikhasacademyapp.ui.animation.shimmerLoading.<anonymous> (MotionUtils.kt:103)");
        }
        if (!$enabled) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return composed;
        }
        InfiniteTransition transition = InfiniteTransitionKt.rememberInfiniteTransition("shimmerTransition", $composer, 6, 0);
        State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(transition, 0.0f, 1200.0f, AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(1100, 0, EasingKt.getLinearEasing(), 2, null), RepeatMode.Restart, 0L, 4, null), "shimmerTranslate", $composer, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
        long jM5129getLightGray0d7_KjU = Color.INSTANCE.m5129getLightGray0d7_KjU();
        long jM5134getWhite0d7_KjU = Color.INSTANCE.m5134getWhite0d7_KjU();
        long jM5129getLightGray0d7_KjU2 = Color.INSTANCE.m5129getLightGray0d7_KjU();
        List shimmerColors = CollectionsKt.listOf((Object[]) new Color[]{Color.m5087boximpl(Color.m5095copywmQWz5c(jM5129getLightGray0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5129getLightGray0d7_KjU) : 0.25f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM5129getLightGray0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5129getLightGray0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5129getLightGray0d7_KjU) : 0.0f)), Color.m5087boximpl(Color.m5095copywmQWz5c(jM5134getWhite0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5134getWhite0d7_KjU) : 0.8f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM5134getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5134getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5134getWhite0d7_KjU) : 0.0f)), Color.m5087boximpl(Color.m5095copywmQWz5c(jM5129getLightGray0d7_KjU2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5129getLightGray0d7_KjU2) : 0.25f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM5129getLightGray0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5129getLightGray0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5129getLightGray0d7_KjU2) : 0.0f))});
        Brush brush = Brush.INSTANCE.m5057linearGradientmHitzGk((List<Color>) shimmerColors, (8 & 2) != 0 ? Offset.INSTANCE.m4868getZeroF1C5BW0() : Offset.m4844constructorimpl((((long) Float.floatToRawIntBits(shimmerLoading$lambda$11$lambda$10(stateAnimateFloat) - 250.0f)) << 32) | (((long) Float.floatToRawIntBits(shimmerLoading$lambda$11$lambda$10(stateAnimateFloat) - 250.0f)) & 4294967295L)), (8 & 4) != 0 ? Offset.INSTANCE.m4866getInfiniteF1C5BW0() : Offset.m4844constructorimpl((((long) Float.floatToRawIntBits(shimmerLoading$lambda$11$lambda$10(stateAnimateFloat))) << 32) | (((long) Float.floatToRawIntBits(shimmerLoading$lambda$11$lambda$10(stateAnimateFloat))) & 4294967295L)), (8 & 8) != 0 ? TileMode.INSTANCE.m5492getClamp3opZhB0() : 0);
        Modifier modifierBackground$default = BackgroundKt.background$default(ClipKt.clip(composed, $shape), brush, null, 0.0f, 6, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierBackground$default;
    }

    private static final float shimmerLoading$lambda$11$lambda$10(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX INFO: renamed from: pulseGlow-sW7UJKQ, reason: not valid java name */
    public static final Modifier m8745pulseGlowsW7UJKQ(Modifier pulseGlow, long color, final float minAlpha, final float maxAlpha, final float minScale, final float maxScale, final int durationMillis) {
        Intrinsics.checkNotNullParameter(pulseGlow, "$this$pulseGlow");
        return ComposedModifierKt.composed$default(pulseGlow, null, new Function3() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MotionUtilsKt.pulseGlow_sW7UJKQ$lambda$16(minAlpha, maxAlpha, durationMillis, minScale, maxScale, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, null);
    }

    static final Modifier pulseGlow_sW7UJKQ$lambda$16(float $minAlpha, float $maxAlpha, int $durationMillis, float $minScale, float $maxScale, Modifier composed, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composed, "$this$composed");
        $composer.startReplaceGroup(-699423228);
        ComposerKt.sourceInformation($composer, "C144@4631L47,145@4715L289,154@5041L289,164@5355L80:MotionUtils.kt#b4fcsz");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-699423228, $changed, -1, "com.example.sushikhasacademyapp.ui.animation.pulseGlow.<anonymous> (MotionUtils.kt:144)");
        }
        InfiniteTransition infiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition("pulseGlow", $composer, 6, 0);
        final State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransition, $minAlpha, $maxAlpha, AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default($durationMillis, 0, EasingKt.getFastOutSlowInEasing(), 2, null), RepeatMode.Reverse, 0L, 4, null), "pulseAlpha", $composer, InfiniteTransition.$stable | 24576 | (InfiniteRepeatableSpec.$stable << 9), 0);
        final State<Float> stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransition, $minScale, $maxScale, AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default($durationMillis, 0, EasingKt.getFastOutSlowInEasing(), 2, null), RepeatMode.Reverse, 0L, 4, null), "pulseScale", $composer, InfiniteTransition.$stable | 24576 | (InfiniteRepeatableSpec.$stable << 9), 0);
        ComposerKt.sourceInformationMarkerStart($composer, -2130799692, "CC(remember):MotionUtils.kt#9igjgp");
        boolean zChanged = $composer.changed(stateAnimateFloat2) | $composer.changed(stateAnimateFloat);
        Object objRememberedValue = $composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return MotionUtilsKt.pulseGlow_sW7UJKQ$lambda$16$lambda$15$lambda$14(stateAnimateFloat2, stateAnimateFloat, (GraphicsLayerScope) obj2);
                }
            };
            $composer.updateRememberedValue(obj);
            objRememberedValue = obj;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(composed, (Function1) objRememberedValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierGraphicsLayer;
    }

    private static final float pulseGlow_sW7UJKQ$lambda$16$lambda$12(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float pulseGlow_sW7UJKQ$lambda$16$lambda$13(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Unit pulseGlow_sW7UJKQ$lambda$16$lambda$15$lambda$14(State $scale$delegate, State $alpha$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(pulseGlow_sW7UJKQ$lambda$16$lambda$13($scale$delegate));
        graphicsLayer.setScaleY(pulseGlow_sW7UJKQ$lambda$16$lambda$13($scale$delegate));
        graphicsLayer.setAlpha(pulseGlow_sW7UJKQ$lambda$16$lambda$12($alpha$delegate));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: animatedGradientBorder-rAjV9yQ$default, reason: not valid java name */
    public static /* synthetic */ Modifier m8742animatedGradientBorderrAjV9yQ$default(Modifier modifier, float f, Shape shape, List list, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            f = Dp.m7905constructorimpl((float) 1.5d);
        }
        if ((i2 & 2) != 0) {
            shape = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16));
        }
        if ((i2 & 4) != 0) {
            list = CollectionsKt.listOf((Object[]) new Color[]{Color.m5087boximpl(ColorKt.Color(4278355143L)), Color.m5087boximpl(ColorKt.Color(4281908728L)), Color.m5087boximpl(ColorKt.Color(4286680312L)), Color.m5087boximpl(ColorKt.Color(4278355143L))});
        }
        if ((i2 & 8) != 0) {
            i = 3000;
        }
        return m8741animatedGradientBorderrAjV9yQ(modifier, f, shape, list, i);
    }

    /* JADX INFO: renamed from: animatedGradientBorder-rAjV9yQ, reason: not valid java name */
    public static final Modifier m8741animatedGradientBorderrAjV9yQ(Modifier animatedGradientBorder, final float borderWidth, final Shape shape, final List<Color> colors, final int durationMillis) {
        Intrinsics.checkNotNullParameter(animatedGradientBorder, "$this$animatedGradientBorder");
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(colors, "colors");
        return ComposedModifierKt.composed$default(animatedGradientBorder, null, new Function3() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda27
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MotionUtilsKt.animatedGradientBorder_rAjV9yQ$lambda$18(durationMillis, colors, borderWidth, shape, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, null);
    }

    static final Modifier animatedGradientBorder_rAjV9yQ$lambda$18(int $durationMillis, List $colors, float $borderWidth, Shape $shape, Modifier composed, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composed, "$this$composed");
        $composer.startReplaceGroup(1589245797);
        ComposerKt.sourceInformation($composer, "C180@5825L52,181@5907L275:MotionUtils.kt#b4fcsz");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1589245797, $changed, -1, "com.example.sushikhasacademyapp.ui.animation.animatedGradientBorder.<anonymous> (MotionUtils.kt:180)");
        }
        InfiniteTransition transition = InfiniteTransitionKt.rememberInfiniteTransition("gradientBorder", $composer, 6, 0);
        State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(transition, 0.0f, 1000.0f, AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default($durationMillis, 0, EasingKt.getLinearEasing(), 2, null), RepeatMode.Restart, 0L, 4, null), "borderOffset", $composer, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
        Brush brush = Brush.INSTANCE.m5057linearGradientmHitzGk((List<Color>) $colors, (8 & 2) != 0 ? Offset.INSTANCE.m4868getZeroF1C5BW0() : Offset.m4844constructorimpl((((long) Float.floatToRawIntBits(animatedGradientBorder_rAjV9yQ$lambda$18$lambda$17(stateAnimateFloat))) << 32) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L)), (8 & 4) != 0 ? Offset.INSTANCE.m4866getInfiniteF1C5BW0() : Offset.m4844constructorimpl((((long) Float.floatToRawIntBits(animatedGradientBorder_rAjV9yQ$lambda$18$lambda$17(stateAnimateFloat) + 300.0f)) << 32) | (((long) Float.floatToRawIntBits(600.0f)) & 4294967295L)), (8 & 8) != 0 ? TileMode.INSTANCE.m5492getClamp3opZhB0() : 0);
        Modifier modifierM278borderziNgDLE = BorderKt.m278borderziNgDLE(composed, $borderWidth, brush, $shape);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierM278borderziNgDLE;
    }

    private static final float animatedGradientBorder_rAjV9yQ$lambda$18$lambda$17(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final int AnimatedTabContent$lambda$20$lambda$19(Object it) {
        return 0;
    }

    public static final <T> void AnimatedTabContent(final T t, Modifier modifier, Function1<? super T, Integer> function1, final Function3<? super T, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function1<? super T, Integer> function2;
        final Modifier modifier3;
        final Function1<? super T, Integer> function3;
        Modifier.Companion modifier4;
        Function1<? super T, Integer> function4;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(2116090595);
        ComposerKt.sourceInformation($composer2, "C(AnimatedTabContent)N(targetState,modifier,getTabIndex,content)207@6629L5,210@6702L56,214@6873L44,214@6862L55,221@7029L1069,243@8143L39,218@6923L1259:MotionUtils.kt#b4fcsz");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(t) : $composer2.changedInstance(t) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            function2 = function1;
        } else if (($changed & 384) == 0) {
            function2 = function1;
            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
        } else {
            function2 = function1;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 2048 : 1024;
        }
        final int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            function3 = function2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 != 0) {
                ComposerKt.sourceInformationMarkerStart($composer2, 2043534792, "CC(remember):MotionUtils.kt#9igjgp");
                Object objRememberedValue = $composer2.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda17
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return Integer.valueOf(MotionUtilsKt.AnimatedTabContent$lambda$20$lambda$19(obj2));
                        }
                    };
                    $composer2.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                function4 = (Function1) objRememberedValue;
            } else {
                function4 = function2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2116090595, $dirty2, -1, "com.example.sushikhasacademyapp.ui.animation.AnimatedTabContent (MotionUtils.kt:209)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 2043537179, "CC(remember):MotionUtils.kt#9igjgp");
            Object objRememberedValue2 = $composer2.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object objMutableIntStateOf = SnapshotIntStateKt.mutableIntStateOf(function4.invoke(t).intValue());
                $composer2.updateRememberedValue(objMutableIntStateOf);
                objRememberedValue2 = objMutableIntStateOf;
            }
            final MutableIntState previousIndex$delegate = (MutableIntState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final int currentIndex = function4.invoke(t).intValue();
            final boolean isForward = currentIndex >= AnimatedTabContent$lambda$22(previousIndex$delegate);
            ComposerKt.sourceInformationMarkerStart($composer2, 2043542639, "CC(remember):MotionUtils.kt#9igjgp");
            boolean zChanged = $composer2.changed(currentIndex);
            Object objRememberedValue3 = $composer2.rememberedValue();
            if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MotionUtilsKt.AnimatedTabContent$lambda$25$lambda$24(currentIndex, previousIndex$delegate);
                    }
                };
                $composer2.updateRememberedValue(obj2);
                objRememberedValue3 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.SideEffect((Function0) objRememberedValue3, $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 2043548656, "CC(remember):MotionUtils.kt#9igjgp");
            boolean zChanged2 = $composer2.changed(isForward);
            Object objRememberedValue4 = $composer2.rememberedValue();
            if (zChanged2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object obj3 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return MotionUtilsKt.AnimatedTabContent$lambda$31$lambda$30(isForward, (AnimatedContentTransitionScope) obj4);
                    }
                };
                $composer2.updateRememberedValue(obj3);
                objRememberedValue4 = obj3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier5 = modifier4;
            AnimatedContentKt.AnimatedContent(t, modifier5, (Function1) objRememberedValue4, null, "AnimatedTabContent", null, ComposableLambdaKt.rememberComposableLambda(933359666, true, new Function4() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function4
                public final Object invoke(Object obj4, Object obj5, Object obj6, Object obj7) {
                    return MotionUtilsKt.AnimatedTabContent$lambda$32(content, $dirty2, (AnimatedContentScope) obj4, obj5, (Composer) obj6, ((Integer) obj7).intValue());
                }
            }, $composer2, 54), $composer2, ($dirty2 & 8) | 1597440 | ($dirty2 & 14) | ($dirty2 & 112), 40);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            function3 = function4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return MotionUtilsKt.AnimatedTabContent$lambda$33(t, modifier3, function3, content, $changed, i, (Composer) obj4, ((Integer) obj5).intValue());
                }
            });
        }
    }

    private static final int AnimatedTabContent$lambda$22(MutableIntState $previousIndex$delegate) {
        return $previousIndex$delegate.getIntValue();
    }

    static final Unit AnimatedTabContent$lambda$25$lambda$24(int $currentIndex, MutableIntState $previousIndex$delegate) {
        $previousIndex$delegate.setIntValue($currentIndex);
        return Unit.INSTANCE;
    }

    static final ContentTransform AnimatedTabContent$lambda$31$lambda$30(boolean $isForward, AnimatedContentTransitionScope AnimatedContent) {
        Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
        if ($isForward) {
            return AnimatedContentKt.togetherWith(EnterExitTransitionKt.slideInHorizontally(AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda38
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(MotionUtilsKt.AnimatedTabContent$lambda$31$lambda$30$lambda$26(((Integer) obj).intValue()));
                }
            }).plus(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 0, null, 6, null), 0.0f, 2, null)), EnterExitTransitionKt.slideOutHorizontally(AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda39
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(MotionUtilsKt.AnimatedTabContent$lambda$31$lambda$30$lambda$27(((Integer) obj).intValue()));
                }
            }).plus(EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(RotationOptions.ROTATE_180, 0, null, 6, null), 0.0f, 2, null)));
        }
        return AnimatedContentKt.togetherWith(EnterExitTransitionKt.slideInHorizontally(AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda40
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(MotionUtilsKt.AnimatedTabContent$lambda$31$lambda$30$lambda$28(((Integer) obj).intValue()));
            }
        }).plus(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 0, null, 6, null), 0.0f, 2, null)), EnterExitTransitionKt.slideOutHorizontally(AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null), new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda41
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(MotionUtilsKt.AnimatedTabContent$lambda$31$lambda$30$lambda$29(((Integer) obj).intValue()));
            }
        }).plus(EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(RotationOptions.ROTATE_180, 0, null, 6, null), 0.0f, 2, null)));
    }

    static final int AnimatedTabContent$lambda$31$lambda$30$lambda$26(int it) {
        return it / 3;
    }

    static final int AnimatedTabContent$lambda$31$lambda$30$lambda$27(int it) {
        return (-it) / 3;
    }

    static final int AnimatedTabContent$lambda$31$lambda$30$lambda$28(int it) {
        return (-it) / 3;
    }

    static final int AnimatedTabContent$lambda$31$lambda$30$lambda$29(int it) {
        return it / 3;
    }

    static final Unit AnimatedTabContent$lambda$32(Function3 $content, int $$dirty, AnimatedContentScope AnimatedContent, Object state, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
        ComposerKt.sourceInformation($composer, "CN(state)244@8162L14:MotionUtils.kt#b4fcsz");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(933359666, $changed, -1, "com.example.sushikhasacademyapp.ui.animation.AnimatedTabContent.<anonymous> (MotionUtils.kt:244)");
        }
        $content.invoke(state, $composer, Integer.valueOf(($$dirty & 8) | (($changed >> 3) & 14)));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    public static final void StaggeredAnimatedItem(final int index, Modifier modifier, int delayMultiplier, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        int i2;
        final Modifier modifier3;
        final int delayMultiplier2;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1840901502);
        ComposerKt.sourceInformation($composer2, "C(StaggeredAnimatedItem)N(index,modifier,delayMultiplier,content)258@8442L34,260@8503L100,260@8482L121,269@8815L26,278@9247L25,265@8609L663:MotionUtils.kt#b4fcsz");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(index) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            i2 = delayMultiplier;
        } else if (($changed & 384) == 0) {
            i2 = delayMultiplier;
            $dirty |= $composer2.changed(i2) ? 256 : 128;
        } else {
            i2 = delayMultiplier;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            Modifier.Companion modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier2;
            delayMultiplier2 = i4 != 0 ? 35 : i2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1840901502, $dirty2, -1, "com.example.sushikhasacademyapp.ui.animation.StaggeredAnimatedItem (MotionUtils.kt:257)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -420176672, "CC(remember):MotionUtils.kt#9igjgp");
            Object objRememberedValue = $composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer2.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue = objMutableStateOf$default;
            }
            MutableState isVisible$delegate = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -420174654, "CC(remember):MotionUtils.kt#9igjgp");
            boolean z = (($dirty2 & 14) == 4) | (($dirty2 & 896) == 256);
            Object objRememberedValue2 = $composer2.rememberedValue();
            if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj = (Function2) new MotionUtilsKt$StaggeredAnimatedItem$1$1(index, delayMultiplier2, isVisible$delegate, null);
                $composer2.updateRememberedValue(obj);
                objRememberedValue2 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, $composer2, 6);
            boolean zStaggeredAnimatedItem$lambda$35 = StaggeredAnimatedItem$lambda$35(isVisible$delegate);
            EnterTransition enterTransitionFadeIn$default = EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(320, 0, EasingKt.getLinearOutSlowInEasing(), 2, null), 0.0f, 2, null);
            SpringSpec springSpecSpring$default = AnimationSpecKt.spring$default(0.75f, 200.0f, null, 4, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -420164744, "CC(remember):MotionUtils.kt#9igjgp");
            boolean z2 = ($dirty2 & 14) == 4;
            Object objRememberedValue3 = $composer2.rememberedValue();
            if (z2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return Integer.valueOf(MotionUtilsKt.StaggeredAnimatedItem$lambda$39$lambda$38(index, ((Integer) obj3).intValue()));
                    }
                };
                $composer2.updateRememberedValue(obj2);
                objRememberedValue3 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier5 = modifier4;
            AnimatedVisibilityKt.AnimatedVisibility(zStaggeredAnimatedItem$lambda$35, modifier5, enterTransitionFadeIn$default.plus(EnterExitTransitionKt.slideInVertically(springSpecSpring$default, (Function1) objRememberedValue3)).plus(EnterExitTransitionKt.m87scaleInL8ZKhE$default(AnimationSpecKt.tween$default(300, 0, EasingKt.getLinearOutSlowInEasing(), 2, null), 0.94f, 0L, 4, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(TextFieldImplKt.AnimationDuration, 0, null, 6, null), 0.0f, 2, null), (String) null, ComposableLambdaKt.rememberComposableLambda(1525294502, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj3, Object obj4, Object obj5) {
                    return MotionUtilsKt.StaggeredAnimatedItem$lambda$40(content, (AnimatedVisibilityScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                }
            }, $composer2, 54), $composer2, ($dirty2 & 112) | 199680, 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            delayMultiplier2 = i2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return MotionUtilsKt.StaggeredAnimatedItem$lambda$41(index, modifier3, delayMultiplier2, content, $changed, i, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    private static final boolean StaggeredAnimatedItem$lambda$35(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void StaggeredAnimatedItem$lambda$36(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final int StaggeredAnimatedItem$lambda$39$lambda$38(int $index, int it) {
        return (($index % 5) + 1) * 28;
    }

    static final Unit StaggeredAnimatedItem$lambda$40(Function2 $content, AnimatedVisibilityScope AnimatedVisibility, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
        ComposerKt.sourceInformation($composer, "C279@9257L9:MotionUtils.kt#b4fcsz");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1525294502, $changed, -1, "com.example.sushikhasacademyapp.ui.animation.StaggeredAnimatedItem.<anonymous> (MotionUtils.kt:279)");
        }
        $content.invoke($composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: AnimatedProgressBar-_UMDTes, reason: not valid java name */
    public static final void m8737AnimatedProgressBar_UMDTes(final float progress, Modifier modifier, long color, long trackColor, float height, Shape shape, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long color2;
        long j;
        float height2;
        Shape shape2;
        Composer $composer2;
        final Shape shape3;
        final Modifier modifier3;
        final long color3;
        final float height3;
        final long trackColor2;
        long trackColor3;
        long color4;
        int $dirty;
        Function0<ComposeUiNode> function0;
        Composer $composer3 = $composer.startRestartGroup(-1521009095);
        ComposerKt.sourceInformation($composer3, "C(AnimatedProgressBar)N(progress,modifier,color:c#ui.graphics.Color,trackColor:c#ui.graphics.Color,height:c#ui.unit.Dp,shape)295@9599L248,304@9853L516:MotionUtils.kt#b4fcsz");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(progress) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            color2 = color;
        } else if (($changed & 384) == 0) {
            color2 = color;
            $dirty2 |= $composer3.changed(color2) ? 256 : 128;
        } else {
            color2 = color;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            j = trackColor;
        } else if (($changed & 3072) == 0) {
            j = trackColor;
            $dirty2 |= $composer3.changed(j) ? 2048 : 1024;
        } else {
            j = trackColor;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty2 |= 24576;
            height2 = height;
        } else if (($changed & 24576) == 0) {
            height2 = height;
            $dirty2 |= $composer3.changed(height2) ? 16384 : 8192;
        } else {
            height2 = height;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i6 = $composer3.changed(shape2) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i6;
        } else {
            shape2 = shape;
        }
        int $dirty3 = $dirty2;
        if ($composer3.shouldExecute(($dirty2 & 74899) != 74898, $dirty3 & 1)) {
            $composer3.startDefaults();
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty = $dirty3 & (-458753);
                    color4 = color2;
                    trackColor3 = j;
                } else {
                    color4 = color2;
                    trackColor3 = j;
                    $dirty = $dirty3;
                }
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    color2 = ColorKt.Color(4279673674L);
                }
                if (i4 == 0) {
                    trackColor3 = j;
                } else {
                    trackColor3 = ColorKt.Color(4293060848L);
                }
                if (i5 != 0) {
                    height2 = Dp.m7905constructorimpl(8);
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty3 & (-458753);
                    shape2 = RoundedCornerShapeKt.getCircleShape();
                    color4 = color2;
                } else {
                    color4 = color2;
                    $dirty = $dirty3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1521009095, $dirty, -1, "com.example.sushikhasacademyapp.ui.animation.AnimatedProgressBar (MotionUtils.kt:294)");
            }
            State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(RangesKt.coerceIn(progress, 0.0f, 1.0f), AnimationSpecKt.spring$default(1.0f, 200.0f, null, 4, null), 0.0f, "progressBar", null, $composer3, 3120, 20);
            $composer2 = $composer3;
            Modifier modifierM265backgroundbw27NRU$default = BackgroundKt.m265backgroundbw27NRU$default(ClipKt.clip(SizeKt.m851height3ABfNKs(SizeKt.fillMaxWidth$default(modifier2, 0.0f, 1, null), height2), shape2), trackColor3, null, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM265backgroundbw27NRU$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i9 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1925322292, "C311@10019L344:MotionUtils.kt#b4fcsz");
            BoxKt.Box(BackgroundKt.background$default(ClipKt.clip(SizeKt.fillMaxWidth(SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null), AnimatedProgressBar__UMDTes$lambda$42(stateAnimateFloatAsState)), shape2), Brush.Companion.m5044horizontalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m5087boximpl(Color.m5095copywmQWz5c(color4, (14 & 1) != 0 ? Color.m5099getAlphaimpl(color4) : 0.8f, (14 & 2) != 0 ? Color.m5103getRedimpl(color4) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(color4) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(color4) : 0.0f)), Color.m5087boximpl(color4)}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), $composer2, 0);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape3 = shape2;
            modifier3 = modifier2;
            color3 = color4;
            height3 = height2;
            trackColor2 = trackColor3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shape3 = shape2;
            modifier3 = modifier2;
            color3 = color2;
            height3 = height2;
            trackColor2 = j;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MotionUtilsKt.AnimatedProgressBar__UMDTes$lambda$44(progress, modifier3, color3, trackColor2, height3, shape3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float AnimatedProgressBar__UMDTes$lambda$42(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX INFO: renamed from: AnimatedNumberCounter-V-9fs2A, reason: not valid java name */
    public static final void m8736AnimatedNumberCounterV9fs2A(final int targetNumber, Modifier modifier, String prefix, String suffix, TextStyle style, long color, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        String str;
        String str2;
        TextStyle textStyle;
        Composer $composer2;
        final String prefix2;
        final String suffix2;
        final TextStyle style2;
        long color2;
        int i2;
        Modifier modifier3;
        String suffix3;
        TextStyle style3;
        Composer $composer3 = $composer.startRestartGroup(-1600495875);
        ComposerKt.sourceInformation($composer3, "C(AnimatedNumberCounter)N(targetNumber,modifier,prefix,suffix,style,color:c#ui.graphics.Color)337@10708L176,343@10890L132:MotionUtils.kt#b4fcsz");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(targetNumber) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            str = prefix;
        } else if (($changed & 384) == 0) {
            str = prefix;
            $dirty |= $composer3.changed(str) ? 256 : 128;
        } else {
            str = prefix;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            str2 = suffix;
        } else if (($changed & 3072) == 0) {
            str2 = suffix;
            $dirty |= $composer3.changed(str2) ? 2048 : 1024;
        } else {
            str2 = suffix;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            textStyle = style;
        } else if (($changed & 24576) == 0) {
            textStyle = style;
            $dirty |= $composer3.changed(textStyle) ? 16384 : 8192;
        } else {
            textStyle = style;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changed(color) ? 131072 : 65536;
        }
        if (!$composer3.shouldExecute(($dirty & 74899) != 74898, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            prefix2 = str;
            suffix2 = str2;
            style2 = textStyle;
            color2 = color;
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
                i2 = i5;
            } else {
                i2 = i5;
                modifier3 = modifier2;
            }
            if (i4 == 0) {
                prefix2 = str;
            } else {
                prefix2 = "";
            }
            if (i2 == 0) {
                suffix3 = str2;
            } else {
                suffix3 = "";
            }
            if (i6 == 0) {
                style3 = textStyle;
            } else {
                style3 = TextStyle.INSTANCE.getDefault();
            }
            if (i7 == 0) {
                color2 = color;
            } else {
                color2 = Color.INSTANCE.m5133getUnspecified0d7_KjU();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1600495875, $dirty, -1, "com.example.sushikhasacademyapp.ui.animation.AnimatedNumberCounter (MotionUtils.kt:336)");
            }
            $composer2 = $composer3;
            TextKt.m3072TextNvy7gAk(prefix2 + AnimatedNumberCounter_V_9fs2A$lambda$45(AnimateAsStateKt.animateIntAsState(targetNumber, AnimationSpecKt.tween$default(1000, 0, EasingKt.getFastOutSlowInEasing(), 2, null), "numberCounter", null, $composer3, ($dirty & 14) | 384, 8)) + suffix3, modifier3, color2, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, style3, $composer2, ($dirty & 112) | (($dirty >> 9) & 896), ($dirty << 9) & 29360128, 131064);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            suffix2 = suffix3;
            modifier2 = modifier3;
            style2 = style3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final long color3 = color2;
            final Modifier modifier4 = modifier2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MotionUtilsKt.AnimatedNumberCounter_V_9fs2A$lambda$46(targetNumber, modifier4, prefix2, suffix2, style2, color3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final int AnimatedNumberCounter_V_9fs2A$lambda$45(State<Integer> state) {
        return ((Number) state.getValue()).intValue();
    }

    /* JADX INFO: renamed from: AudioWaveVisualizer-u4SGt0E, reason: not valid java name */
    public static final void m8738AudioWaveVisualizeru4SGt0E(final boolean isPlaying, Modifier modifier, long barColor, int barCount, float maxHeight, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        int barCount2;
        float maxHeight2;
        final Modifier modifier3;
        final long barColor2;
        final int barCount3;
        final float maxHeight3;
        Modifier.Companion modifier4;
        long barColor3;
        Function0<ComposeUiNode> function0;
        Composer composer;
        MutableFloatState mutableFloatStateAnimateFloat;
        Composer $composer2 = $composer.startRestartGroup(-321965810);
        ComposerKt.sourceInformation($composer2, "C(AudioWaveVisualizer)N(isPlaying,modifier,barColor:c#ui.graphics.Color,barCount,maxHeight:c#ui.unit.Dp)362@11325L47,364@11378L1092:MotionUtils.kt#b4fcsz");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(isPlaying) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            j = barColor;
        } else if (($changed & 384) == 0) {
            j = barColor;
            $dirty |= $composer2.changed(j) ? 256 : 128;
        } else {
            j = barColor;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            barCount2 = barCount;
        } else if (($changed & 3072) == 0) {
            barCount2 = barCount;
            $dirty |= $composer2.changed(barCount2) ? 2048 : 1024;
        } else {
            barCount2 = barCount;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            maxHeight2 = maxHeight;
        } else if (($changed & 24576) == 0) {
            maxHeight2 = maxHeight;
            $dirty |= $composer2.changed(maxHeight2) ? 16384 : 8192;
        } else {
            maxHeight2 = maxHeight;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 9363) != 9362, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            barColor2 = j;
            barCount3 = barCount2;
            maxHeight3 = maxHeight2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 == 0) {
                barColor3 = j;
            } else {
                barColor3 = ColorKt.Color(4278355143L);
            }
            if (i4 != 0) {
                barCount2 = 5;
            }
            if (i5 != 0) {
                maxHeight2 = Dp.m7905constructorimpl(20);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-321965810, $dirty2, -1, "com.example.sushikhasacademyapp.ui.animation.AudioWaveVisualizer (MotionUtils.kt:361)");
            }
            InfiniteTransition infiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition("audioWave", $composer2, 6, 0);
            Arrangement.Horizontal horizontalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(3));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            int i6 = (($dirty2 >> 3) & 14) | 432;
            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM689spacedBy0680j_4, centerVertically, $composer2, ((i6 >> 3) & 14) | ((i6 >> 3) & 112));
            Modifier modifier5 = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifier4);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((i6 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer2);
            MeasurePolicy measurePolicy = measurePolicyRowMeasurePolicy;
            Updater.m4375setimpl(composerM4367constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i9 = ((i6 >> 6) & 112) | 6;
            Composer composer2 = $composer2;
            ComposerKt.sourceInformationMarkerStart(composer2, -566895976, "C:MotionUtils.kt#b4fcsz");
            composer2.startReplaceGroup(-2096496949);
            ComposerKt.sourceInformation(composer2, "*387@12237L217");
            int i10 = 0;
            while (i10 < barCount2) {
                if (isPlaying) {
                    composer2.startReplaceGroup(-566838565);
                    ComposerKt.sourceInformation(composer2, "371@11650L482");
                    Composer composer3 = composer2;
                    mutableFloatStateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.2f, 1.0f, AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(((i10 * 90) % 400) + 350, 0, EasingKt.getFastOutSlowInEasing(), 2, null), RepeatMode.Reverse, 0L, 4, null), "waveBar_" + i10, composer3, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 0);
                    composer = composer3;
                    composer.endReplaceGroup();
                } else {
                    composer = composer2;
                    composer.startReplaceGroup(-566318199);
                    ComposerKt.sourceInformation(composer, "384@12170L39");
                    ComposerKt.sourceInformationMarkerStart(composer, -2096477767, "CC(remember):MotionUtils.kt#9igjgp");
                    Object objRememberedValue = composer.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object objMutableFloatStateOf = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.25f);
                        composer.updateRememberedValue(objMutableFloatStateOf);
                        objRememberedValue = objMutableFloatStateOf;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    composer.endReplaceGroup();
                    mutableFloatStateAnimateFloat = (MutableFloatState) objRememberedValue;
                }
                BoxKt.Box(BackgroundKt.m265backgroundbw27NRU$default(ClipKt.clip(SizeKt.m851height3ABfNKs(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl((float) 3.5d)), Dp.m7905constructorimpl(maxHeight2 * AudioWaveVisualizer_u4SGt0E$lambda$49$lambda$48(mutableFloatStateAnimateFloat))), RoundedCornerShapeKt.getCircleShape()), barColor3, null, 2, null), composer, 0);
                i10++;
                centerVertically = centerVertically;
                infiniteTransition = infiniteTransition;
                composer2 = composer;
                modifierMaterializeModifier = modifierMaterializeModifier;
                measurePolicy = measurePolicy;
            }
            Composer composer4 = composer2;
            composer4.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            barColor2 = barColor3;
            modifier3 = modifier5;
            barCount3 = barCount2;
            maxHeight3 = maxHeight2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MotionUtilsKt.AudioWaveVisualizer_u4SGt0E$lambda$50(isPlaying, modifier3, barColor2, barCount3, maxHeight3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float AudioWaveVisualizer_u4SGt0E$lambda$49$lambda$48(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    public static final void AnimatedExpandableCard(final boolean isExpanded, Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> header, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        boolean z;
        Modifier modifier2;
        int $dirty;
        final Modifier modifier3;
        Modifier.Companion modifier4;
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(header, "header");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(312848288);
        ComposerKt.sourceInformation($composer2, "C(AnimatedExpandableCard)N(isExpanded,modifier,header,content)408@12737L591:MotionUtils.kt#b4fcsz");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            z = isExpanded;
            $dirty2 |= $composer2.changed(z) ? 4 : 2;
        } else {
            z = isExpanded;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changedInstance(header) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer2.changedInstance(content) ? 2048 : 1024;
        }
        if (!$composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $dirty = $dirty2;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(312848288, $dirty2, -1, "com.example.sushikhasacademyapp.ui.animation.AnimatedExpandableCard (MotionUtils.kt:407)");
            }
            int i3 = ($dirty2 >> 3) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((i3 >> 3) & 14) | ((i3 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifier4);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((i3 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            $dirty = $dirty2;
            Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            Modifier modifier5 = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            int i6 = ((i3 >> 6) & 112) | 6;
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 1958660906, "C409@12775L8,421@13289L33,410@12792L530:MotionUtils.kt#b4fcsz");
            header.invoke($composer2, Integer.valueOf(($dirty >> 6) & 14));
            AnimatedVisibilityKt.AnimatedVisibility(columnScope, z, (Modifier) null, EnterExitTransitionKt.expandVertically$default(AnimationSpecKt.spring$default(0.75f, 400.0f, null, 4, null), null, false, null, 14, null).plus(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(250, 0, null, 6, null), 0.0f, 2, null)), EnterExitTransitionKt.shrinkVertically$default(AnimationSpecKt.tween$default(200, 0, EasingKt.getFastOutSlowInEasing(), 2, null), null, false, null, 14, null).plus(EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(RotationOptions.ROTATE_180, 0, null, 6, null), 0.0f, 2, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(1433178862, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda42
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MotionUtilsKt.AnimatedExpandableCard$lambda$52$lambda$51(content, (AnimatedVisibilityScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, (i6 & 14) | 1575936 | (($dirty << 3) & 112), 18);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda43
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MotionUtilsKt.AnimatedExpandableCard$lambda$53(isExpanded, modifier3, header, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit AnimatedExpandableCard$lambda$52$lambda$51(Function2 $content, AnimatedVisibilityScope AnimatedVisibility, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
        ComposerKt.sourceInformation($composer, "C422@13303L9:MotionUtils.kt#b4fcsz");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1433178862, $changed, -1, "com.example.sushikhasacademyapp.ui.animation.AnimatedExpandableCard.<anonymous>.<anonymous> (MotionUtils.kt:422)");
        }
        $content.invoke($composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Modifier parallaxScroll$default(Modifier modifier, ScrollState scrollState, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = 0.35f;
        }
        return parallaxScroll(modifier, scrollState, f);
    }

    public static final Modifier parallaxScroll(Modifier $this$parallaxScroll, final ScrollState scrollState, final float rate) {
        Intrinsics.checkNotNullParameter($this$parallaxScroll, "<this>");
        Intrinsics.checkNotNullParameter(scrollState, "scrollState");
        return ComposedModifierKt.composed$default($this$parallaxScroll, null, new Function3() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MotionUtilsKt.parallaxScroll$lambda$56(scrollState, rate, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, null);
    }

    static final Modifier parallaxScroll$lambda$56(final ScrollState $scrollState, final float $rate, Modifier composed, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composed, "$this$composed");
        $composer.startReplaceGroup(723466546);
        ComposerKt.sourceInformation($composer, "C434@13548L163:MotionUtils.kt#b4fcsz");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(723466546, $changed, -1, "com.example.sushikhasacademyapp.ui.animation.parallaxScroll.<anonymous> (MotionUtils.kt:434)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -533350283, "CC(remember):MotionUtils.kt#9igjgp");
        boolean zChanged = $composer.changed($scrollState) | $composer.changed($rate);
        Object objRememberedValue = $composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda33
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return MotionUtilsKt.parallaxScroll$lambda$56$lambda$55$lambda$54($scrollState, $rate, (GraphicsLayerScope) obj2);
                }
            };
            $composer.updateRememberedValue(obj);
            objRememberedValue = obj;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(composed, (Function1) objRememberedValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierGraphicsLayer;
    }

    static final Unit parallaxScroll$lambda$56$lambda$55$lambda$54(ScrollState $scrollState, float $rate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setTranslationY(RangesKt.coerceAtMost($scrollState.getValue() * $rate, 250.0f));
        graphicsLayer.setAlpha(RangesKt.coerceIn(1.0f - (($scrollState.getValue() / 700.0f) * $rate), 0.25f, 1.0f));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Modifier scrollEntranceEffect$default(Modifier modifier, ScrollState scrollState, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = 300.0f;
        }
        return scrollEntranceEffect(modifier, scrollState, f);
    }

    public static final Modifier scrollEntranceEffect(Modifier $this$scrollEntranceEffect, final ScrollState scrollState, final float thresholdOffset) {
        Intrinsics.checkNotNullParameter($this$scrollEntranceEffect, "<this>");
        Intrinsics.checkNotNullParameter(scrollState, "scrollState");
        return ComposedModifierKt.composed$default($this$scrollEntranceEffect, null, new Function3() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda44
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MotionUtilsKt.scrollEntranceEffect$lambda$60(scrollState, thresholdOffset, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, null);
    }

    static final Modifier scrollEntranceEffect$lambda$60(ScrollState $scrollState, float $thresholdOffset, Modifier composed, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composed, "$this$composed");
        $composer.startReplaceGroup(1281329474);
        ComposerKt.sourceInformation($composer, "C449@14064L204,454@14292L65:MotionUtils.kt#b4fcsz");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1281329474, $changed, -1, "com.example.sushikhasacademyapp.ui.animation.scrollEntranceEffect.<anonymous> (MotionUtils.kt:448)");
        }
        float scrollOffset = $scrollState.getValue();
        final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(scrollOffset > $thresholdOffset ? 0.98f : 1.0f, AnimationSpecKt.spring$default(0.0f, 200.0f, null, 5, null), 0.0f, "scrollEntranceScale", null, $composer, 3120, 20);
        ComposerKt.sourceInformationMarkerStart($composer, -1297979645, "CC(remember):MotionUtils.kt#9igjgp");
        boolean zChanged = $composer.changed(stateAnimateFloatAsState);
        Object objRememberedValue = $composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return MotionUtilsKt.scrollEntranceEffect$lambda$60$lambda$59$lambda$58(stateAnimateFloatAsState, (GraphicsLayerScope) obj2);
                }
            };
            $composer.updateRememberedValue(obj);
            objRememberedValue = obj;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(composed, (Function1) objRememberedValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierGraphicsLayer;
    }

    private static final float scrollEntranceEffect$lambda$60$lambda$57(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Unit scrollEntranceEffect$lambda$60$lambda$59$lambda$58(State $scaleFactor$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(scrollEntranceEffect$lambda$60$lambda$57($scaleFactor$delegate));
        graphicsLayer.setScaleY(scrollEntranceEffect$lambda$60$lambda$57($scaleFactor$delegate));
        return Unit.INSTANCE;
    }

    public static final Modifier lazyItemScrollAnimation(Modifier $this$lazyItemScrollAnimation, final LazyListState lazyListState, final int index) {
        Intrinsics.checkNotNullParameter($this$lazyItemScrollAnimation, "<this>");
        Intrinsics.checkNotNullParameter(lazyListState, "lazyListState");
        return ComposedModifierKt.composed$default($this$lazyItemScrollAnimation, null, new Function3() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MotionUtilsKt.lazyItemScrollAnimation$lambda$66(lazyListState, index, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, null);
    }

    static final Modifier lazyItemScrollAnimation$lambda$66(LazyListState $lazyListState, int $index, Modifier composed, Composer $composer, int $changed) {
        Object next;
        float viewportHeight;
        Intrinsics.checkNotNullParameter(composed, "$this$composed");
        $composer.startReplaceGroup(-464294294);
        ComposerKt.sourceInformation($composer, "C480@15160L181,485@15359L171,491@15555L80:MotionUtils.kt#b4fcsz");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-464294294, $changed, -1, "com.example.sushikhasacademyapp.ui.animation.lazyItemScrollAnimation.<anonymous> (MotionUtils.kt:467)");
        }
        LazyListLayoutInfo layoutInfo = $lazyListState.getLayoutInfo();
        Iterator it = layoutInfo.getVisibleItemsInfo().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(((LazyListItemInfo) next).getIndex() == $index));
        LazyListItemInfo visibleItem = (LazyListItemInfo) next;
        if (visibleItem != null) {
            float viewportHeight2 = RangesKt.coerceAtLeast(layoutInfo.getViewportEndOffset() - layoutInfo.getViewportStartOffset(), 1.0f);
            float itemOffset = visibleItem.getOffset();
            float itemSize = visibleItem.getSize();
            float distanceFromBottom = viewportHeight2 - itemOffset;
            viewportHeight = RangesKt.coerceIn(distanceFromBottom / (50.0f + itemSize), 0.5f, 1.0f);
        } else {
            viewportHeight = 1.0f;
        }
        float animationPercent = viewportHeight;
        final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState((0.08f * animationPercent) + 0.92f, AnimationSpecKt.spring$default(0.0f, 200.0f, null, 5, null), 0.0f, "lazyItemScale", null, $composer, 3120, 20);
        final State<Float> stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(RangesKt.coerceIn((animationPercent * 0.5f) + 0.5f, 0.5f, 1.0f), AnimationSpecKt.tween$default(220, 0, null, 6, null), 0.0f, "lazyItemAlpha", null, $composer, 3120, 20);
        ComposerKt.sourceInformationMarkerStart($composer, 489813786, "CC(remember):MotionUtils.kt#9igjgp");
        boolean zChanged = $composer.changed(stateAnimateFloatAsState) | $composer.changed(stateAnimateFloatAsState2);
        Object objRememberedValue = $composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda35
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return MotionUtilsKt.lazyItemScrollAnimation$lambda$66$lambda$65$lambda$64(stateAnimateFloatAsState, stateAnimateFloatAsState2, (GraphicsLayerScope) obj2);
                }
            };
            $composer.updateRememberedValue(obj);
            objRememberedValue = obj;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(composed, (Function1) objRememberedValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierGraphicsLayer;
    }

    private static final float lazyItemScrollAnimation$lambda$66$lambda$62(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float lazyItemScrollAnimation$lambda$66$lambda$63(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Unit lazyItemScrollAnimation$lambda$66$lambda$65$lambda$64(State $scale$delegate, State $alpha$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setScaleX(lazyItemScrollAnimation$lambda$66$lambda$62($scale$delegate));
        graphicsLayer.setScaleY(lazyItemScrollAnimation$lambda$66$lambda$62($scale$delegate));
        graphicsLayer.setAlpha(lazyItemScrollAnimation$lambda$66$lambda$63($alpha$delegate));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: ScrollProgressIndicator-pc5RIQQ, reason: not valid java name */
    public static final void m8739ScrollProgressIndicatorpc5RIQQ(final ScrollState scrollState, Modifier modifier, long color, long trackColor, float height, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        float f;
        final Modifier modifier3;
        final long color2;
        final float height2;
        final long trackColor2;
        Modifier.Companion modifier4;
        long color3;
        long trackColor3;
        float height3;
        long trackColor4;
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(scrollState, "scrollState");
        Composer $composer2 = $composer.startRestartGroup(535530206);
        ComposerKt.sourceInformation($composer2, "C(ScrollProgressIndicator)N(scrollState,modifier,color:c#ui.graphics.Color,trackColor:c#ui.graphics.Color,height:c#ui.unit.Dp)512@16095L156:MotionUtils.kt#b4fcsz");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(scrollState) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            j = color;
        } else if (($changed & 384) == 0) {
            j = color;
            $dirty |= $composer2.changed(j) ? 256 : 128;
        } else {
            j = color;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(trackColor) ? 2048 : 1024;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            f = height;
        } else if (($changed & 24576) == 0) {
            f = height;
            $dirty |= $composer2.changed(f) ? 16384 : 8192;
        } else {
            f = height;
        }
        if ($composer2.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 == 0) {
                color3 = j;
            } else {
                color3 = ColorKt.Color(4279673674L);
            }
            if (i4 == 0) {
                trackColor3 = trackColor;
            } else {
                trackColor3 = Color.INSTANCE.m5132getTransparent0d7_KjU();
            }
            if (i5 == 0) {
                height3 = f;
            } else {
                height3 = Dp.m7905constructorimpl((float) 3.5d);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(535530206, $dirty, -1, "com.example.sushikhasacademyapp.ui.animation.ScrollProgressIndicator (MotionUtils.kt:508)");
            }
            Integer numValueOf = Integer.valueOf(scrollState.getMaxValue());
            if ((numValueOf.intValue() > 0 ? 1 : 0) == 0) {
                numValueOf = null;
            }
            int maxScroll = numValueOf != null ? numValueOf.intValue() : 1;
            float progress = RangesKt.coerceIn(scrollState.getValue() / maxScroll, 0.0f, 1.0f);
            State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(progress, AnimationSpecKt.spring$default(0.0f, 200.0f, null, 5, null), 0.0f, "scrollProgress", null, $composer2, 3120, 20);
            if (maxScroll > 10) {
                $composer2.startReplaceGroup(-1822543019);
                ComposerKt.sourceInformation($composer2, "519@16287L671");
                long trackColor5 = trackColor3;
                Modifier modifierM265backgroundbw27NRU$default = BackgroundKt.m265backgroundbw27NRU$default(SizeKt.m851height3ABfNKs(SizeKt.fillMaxWidth$default(modifier4, 0.0f, 1, null), height3), trackColor5, null, 2, null);
                trackColor4 = trackColor5;
                ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM265backgroundbw27NRU$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i6 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function0 = constructor;
                    $composer2.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer2.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i7 = (i6 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i8 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1259076788, "C525@16452L496:MotionUtils.kt#b4fcsz");
                BoxKt.Box(BackgroundKt.background$default(SizeKt.fillMaxWidth(SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollProgressIndicator_pc5RIQQ$lambda$68(stateAnimateFloatAsState)), Brush.Companion.m5044horizontalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m5087boximpl(Color.m5095copywmQWz5c(color3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(color3) : 0.6f, (14 & 2) != 0 ? Color.m5103getRedimpl(color3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(color3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(color3) : 0.0f)), Color.m5087boximpl(color3), Color.m5087boximpl(ColorKt.Color(4281908728L))}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), $composer2, 0);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
            } else {
                trackColor4 = trackColor3;
                $composer2.startReplaceGroup(-1838712092);
            }
            $composer2.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            height2 = height3;
            trackColor2 = trackColor4;
            color2 = color3;
            modifier3 = modifier4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color2 = j;
            height2 = f;
            trackColor2 = trackColor;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda31
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MotionUtilsKt.ScrollProgressIndicator_pc5RIQQ$lambda$70(scrollState, modifier3, color2, trackColor2, height2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float ScrollProgressIndicator_pc5RIQQ$lambda$68(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX INFO: renamed from: ScrollProgressIndicator-q9LK7_k, reason: not valid java name */
    public static final void m8740ScrollProgressIndicatorq9LK7_k(final LazyListState lazyListState, final int totalItems, Modifier modifier, long color, float height, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        float f;
        final Modifier modifier3;
        final long color2;
        final float height2;
        Modifier.Companion modifier4;
        long color3;
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(lazyListState, "lazyListState");
        Composer $composer2 = $composer.startRestartGroup(-1619505421);
        ComposerKt.sourceInformation($composer2, "C(ScrollProgressIndicator)N(lazyListState,totalItems,modifier,color:c#ui.graphics.Color,height:c#ui.unit.Dp)557@17454L160:MotionUtils.kt#b4fcsz");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(lazyListState) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(totalItems) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            j = color;
        } else if (($changed & 3072) == 0) {
            j = color;
            $dirty |= $composer2.changed(j) ? 2048 : 1024;
        } else {
            j = color;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            f = height;
        } else if (($changed & 24576) == 0) {
            f = height;
            $dirty |= $composer2.changed(f) ? 16384 : 8192;
        } else {
            f = height;
        }
        if ($composer2.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 == 0) {
                color3 = j;
            } else {
                color3 = ColorKt.Color(4279673674L);
            }
            if (i4 == 0) {
                height2 = f;
            } else {
                height2 = Dp.m7905constructorimpl((float) 3.5d);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1619505421, $dirty, -1, "com.example.sushikhasacademyapp.ui.animation.ScrollProgressIndicator (MotionUtils.kt:553)");
            }
            int firstVisibleIndex = lazyListState.getFirstVisibleItemIndex();
            float progress = totalItems > 1 ? RangesKt.coerceIn(firstVisibleIndex / (totalItems - 1), 0.0f, 1.0f) : 0.0f;
            State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(progress, AnimationSpecKt.spring$default(0.0f, 200.0f, null, 5, null), 0.0f, "lazyScrollProgress", null, $composer2, 3120, 20);
            if (totalItems > 1) {
                $composer2.startReplaceGroup(-1317748574);
                ComposerKt.sourceInformation($composer2, "564@17650L573");
                Modifier modifierM265backgroundbw27NRU$default = BackgroundKt.m265backgroundbw27NRU$default(SizeKt.m851height3ABfNKs(SizeKt.fillMaxWidth$default(modifier4, 0.0f, 1, null), height2), Color.INSTANCE.m5132getTransparent0d7_KjU(), null, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifierM265backgroundbw27NRU$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i5 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function0 = constructor;
                    $composer2.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer2.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i6 = (i5 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i7 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1763850184, "C570@17822L391:MotionUtils.kt#b4fcsz");
                BoxKt.Box(BackgroundKt.background$default(SizeKt.fillMaxWidth(SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null), RangesKt.coerceAtLeast(ScrollProgressIndicator_q9LK7_k$lambda$71(stateAnimateFloatAsState), 0.04f)), Brush.Companion.m5044horizontalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m5087boximpl(Color.m5095copywmQWz5c(color3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(color3) : 0.6f, (14 & 2) != 0 ? Color.m5103getRedimpl(color3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(color3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(color3) : 0.0f)), Color.m5087boximpl(color3), Color.m5087boximpl(ColorKt.Color(4281908728L))}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), $composer2, 0);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
            } else {
                $composer2.startReplaceGroup(-1335266705);
            }
            $composer2.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            color2 = color3;
            modifier3 = modifier4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color2 = j;
            height2 = f;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MotionUtilsKt.ScrollProgressIndicator_q9LK7_k$lambda$73(lazyListState, totalItems, modifier3, color2, height2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float ScrollProgressIndicator_q9LK7_k$lambda$71(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    public static final void ScrollToTopFAB(final ScrollState scrollState, Modifier modifier, int threshold, CoroutineScope coroutineScope, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        int i2;
        CoroutineScope coroutineScope2;
        final Modifier modifier3;
        final int $dirty;
        final CoroutineScope coroutineScope3;
        Modifier modifier4;
        int threshold2;
        int $dirty2;
        final CoroutineScope coroutineScope4;
        Intrinsics.checkNotNullParameter(scrollState, "scrollState");
        Composer $composer2 = $composer.startRestartGroup(1486969370);
        ComposerKt.sourceInformation($composer2, "C(ScrollToTopFAB)N(scrollState,modifier,threshold,coroutineScope)598@18664L10,599@18779L10,601@18879L646,596@18564L961:MotionUtils.kt#b4fcsz");
        int $dirty3 = $changed;
        if (($changed & 6) == 0) {
            $dirty3 |= $composer2.changed(scrollState) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty3 |= 384;
            i2 = threshold;
        } else if (($changed & 384) == 0) {
            i2 = threshold;
            $dirty3 |= $composer2.changed(i2) ? 256 : 128;
        } else {
            i2 = threshold;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                coroutineScope2 = coroutineScope;
                int i5 = $composer2.changedInstance(coroutineScope2) ? 2048 : 1024;
                $dirty3 |= i5;
            } else {
                coroutineScope2 = coroutineScope;
            }
            $dirty3 |= i5;
        } else {
            coroutineScope2 = coroutineScope;
        }
        if ($composer2.shouldExecute(($dirty3 & 1171) != 1170, $dirty3 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "592@18480L24");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                int threshold3 = i4 != 0 ? 300 : i2;
                if ((i & 8) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart($composer2, 683736516, "CC(remember):Effects.kt#9igjgp");
                    Object objRememberedValue = $composer2.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object objCreateCompositionCoroutineScope = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2);
                        $composer2.updateRememberedValue(objCreateCompositionCoroutineScope);
                        objRememberedValue = objCreateCompositionCoroutineScope;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    modifier4 = modifier5;
                    threshold2 = threshold3;
                    $dirty2 = $dirty3 & (-7169);
                    coroutineScope4 = (CoroutineScope) objRememberedValue;
                } else {
                    modifier4 = modifier5;
                    threshold2 = threshold3;
                    $dirty2 = $dirty3;
                    coroutineScope4 = coroutineScope2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                }
                $dirty2 = $dirty3;
                modifier4 = modifier2;
                threshold2 = i2;
                coroutineScope4 = coroutineScope2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1486969370, $dirty2, -1, "com.example.sushikhasacademyapp.ui.animation.ScrollToTopFAB (MotionUtils.kt:593)");
            }
            boolean isVisible = scrollState.getValue() > threshold2;
            ComposerKt.sourceInformationMarkerStart($composer2, -1246533980, "CC(remember):MotionUtils.kt#9igjgp");
            Object objRememberedValue2 = $composer2.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return Integer.valueOf(MotionUtilsKt.ScrollToTopFAB$lambda$75$lambda$74(((Integer) obj2).intValue()));
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue2 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EnterTransition enterTransitionPlus = EnterExitTransitionKt.slideInVertically$default(null, (Function1) objRememberedValue2, 1, null).plus(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(250, 0, null, 6, null), 0.0f, 2, null)).plus(EnterExitTransitionKt.m87scaleInL8ZKhE$default(null, 0.8f, 0L, 5, null));
            ComposerKt.sourceInformationMarkerStart($composer2, -1246530300, "CC(remember):MotionUtils.kt#9igjgp");
            Object objRememberedValue3 = $composer2.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return Integer.valueOf(MotionUtilsKt.ScrollToTopFAB$lambda$77$lambda$76(((Integer) obj3).intValue()));
                    }
                };
                $composer2.updateRememberedValue(obj2);
                objRememberedValue3 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AnimatedVisibilityKt.AnimatedVisibility(isVisible, modifier4, enterTransitionPlus, EnterExitTransitionKt.slideOutVertically$default(null, (Function1) objRememberedValue3, 1, null).plus(EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(200, 0, null, 6, null), 0.0f, 2, null)).plus(EnterExitTransitionKt.m89scaleOutL8ZKhE$default(null, 0.8f, 0L, 5, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(-272374206, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj3, Object obj4, Object obj5) {
                    return MotionUtilsKt.ScrollToTopFAB$lambda$80(coroutineScope4, scrollState, (AnimatedVisibilityScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                }
            }, $composer2, 54), $composer2, ($dirty2 & 112) | 200064, 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            coroutineScope3 = coroutineScope4;
            modifier3 = modifier4;
            $dirty = threshold2;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            $dirty = i2;
            coroutineScope3 = coroutineScope2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return MotionUtilsKt.ScrollToTopFAB$lambda$81(scrollState, modifier3, $dirty, coroutineScope3, $changed, i, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    static final int ScrollToTopFAB$lambda$75$lambda$74(int it) {
        return it / 2;
    }

    static final int ScrollToTopFAB$lambda$77$lambda$76(int it) {
        return it / 2;
    }

    static final Unit ScrollToTopFAB$lambda$80$lambda$79$lambda$78(CoroutineScope $coroutineScope, ScrollState $scrollState) {
        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new MotionUtilsKt$ScrollToTopFAB$3$1$1$1($scrollState, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x00a3  */
    static final Unit ScrollToTopFAB$lambda$80(final CoroutineScope $coroutineScope, final ScrollState $scrollState, AnimatedVisibilityScope AnimatedVisibility, Composer $composer, int $changed) {
        long j;
        Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
        ComposerKt.sourceInformation($composer, "C608@19170L11,609@19241L11,603@18938L187,602@18889L630:MotionUtils.kt#b4fcsz");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-272374206, $changed, -1, "com.example.sushikhasacademyapp.ui.animation.ScrollToTopFAB.<anonymous> (MotionUtils.kt:602)");
        }
        long jM2239getPrimaryContainer0d7_KjU = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimaryContainer();
        long jM2223getOnPrimaryContainer0d7_KjU = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimaryFixed();
        RoundedCornerShape circleShape = RoundedCornerShapeKt.getCircleShape();
        Modifier modifierBounceClick$default = bounceClick$default(Modifier.INSTANCE, 0.0f, null, 3, null);
        ComposerKt.sourceInformationMarkerStart($composer, 241395165, "CC(remember):MotionUtils.kt#9igjgp");
        boolean zChangedInstance = $composer.changedInstance($coroutineScope) | $composer.changed($scrollState);
        Object objRememberedValue = $composer.rememberedValue();
        if (!zChangedInstance) {
            j = jM2239getPrimaryContainer0d7_KjU;
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            FloatingActionButtonKt.m2477SmallFloatingActionButtonXz6DiA((Function0) objRememberedValue, modifierBounceClick$default, circleShape, j, jM2223getOnPrimaryContainer0d7_KjU, null, null, ComposableSingletons$MotionUtilsKt.INSTANCE.m8735getLambda$2105741857$app(), $composer, 12582912, 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return Unit.INSTANCE;
        }
        j = jM2239getPrimaryContainer0d7_KjU;
        Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda37
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MotionUtilsKt.ScrollToTopFAB$lambda$80$lambda$79$lambda$78($coroutineScope, $scrollState);
            }
        };
        $composer.updateRememberedValue(obj);
        objRememberedValue = obj;
        ComposerKt.sourceInformationMarkerEnd($composer);
        FloatingActionButtonKt.m2477SmallFloatingActionButtonXz6DiA((Function0) objRememberedValue, modifierBounceClick$default, circleShape, j, jM2223getOnPrimaryContainer0d7_KjU, null, null, ComposableSingletons$MotionUtilsKt.INSTANCE.m8735getLambda$2105741857$app(), $composer, 12582912, 96);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    public static final void ScrollToTopFAB(final LazyListState lazyListState, Modifier modifier, int threshold, CoroutineScope coroutineScope, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        int i2;
        CoroutineScope coroutineScope2;
        final Modifier modifier3;
        final int $dirty;
        final CoroutineScope coroutineScope3;
        Modifier modifier4;
        int threshold2;
        int $dirty2;
        final CoroutineScope coroutineScope4;
        Intrinsics.checkNotNullParameter(lazyListState, "lazyListState");
        Composer $composer2 = $composer.startRestartGroup(1757615509);
        ComposerKt.sourceInformation($composer2, "C(ScrollToTopFAB)N(lazyListState,modifier,threshold,coroutineScope)635@20034L10,636@20149L10,638@20249L589,633@19934L904:MotionUtils.kt#b4fcsz");
        int $dirty3 = $changed;
        if (($changed & 6) == 0) {
            $dirty3 |= $composer2.changed(lazyListState) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty3 |= 384;
            i2 = threshold;
        } else if (($changed & 384) == 0) {
            i2 = threshold;
            $dirty3 |= $composer2.changed(i2) ? 256 : 128;
        } else {
            i2 = threshold;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                coroutineScope2 = coroutineScope;
                int i5 = $composer2.changedInstance(coroutineScope2) ? 2048 : 1024;
                $dirty3 |= i5;
            } else {
                coroutineScope2 = coroutineScope;
            }
            $dirty3 |= i5;
        } else {
            coroutineScope2 = coroutineScope;
        }
        if ($composer2.shouldExecute(($dirty3 & 1171) != 1170, $dirty3 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "629@19780L24");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                int threshold3 = i4 != 0 ? 3 : i2;
                if ((i & 8) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart($composer2, 683736516, "CC(remember):Effects.kt#9igjgp");
                    Object objRememberedValue = $composer2.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object objCreateCompositionCoroutineScope = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2);
                        $composer2.updateRememberedValue(objCreateCompositionCoroutineScope);
                        objRememberedValue = objCreateCompositionCoroutineScope;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    modifier4 = modifier5;
                    threshold2 = threshold3;
                    $dirty2 = $dirty3 & (-7169);
                    coroutineScope4 = (CoroutineScope) objRememberedValue;
                } else {
                    modifier4 = modifier5;
                    threshold2 = threshold3;
                    $dirty2 = $dirty3;
                    coroutineScope4 = coroutineScope2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                }
                $dirty2 = $dirty3;
                modifier4 = modifier2;
                threshold2 = i2;
                coroutineScope4 = coroutineScope2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1757615509, $dirty2, -1, "com.example.sushikhasacademyapp.ui.animation.ScrollToTopFAB (MotionUtils.kt:630)");
            }
            boolean isVisible = lazyListState.getFirstVisibleItemIndex() > threshold2 || lazyListState.getFirstVisibleItemScrollOffset() > 300;
            ComposerKt.sourceInformationMarkerStart($composer2, 1146370143, "CC(remember):MotionUtils.kt#9igjgp");
            Object objRememberedValue2 = $composer2.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return Integer.valueOf(MotionUtilsKt.ScrollToTopFAB$lambda$83$lambda$82(((Integer) obj2).intValue()));
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue2 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EnterTransition enterTransitionPlus = EnterExitTransitionKt.slideInVertically$default(null, (Function1) objRememberedValue2, 1, null).plus(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(250, 0, null, 6, null), 0.0f, 2, null)).plus(EnterExitTransitionKt.m87scaleInL8ZKhE$default(null, 0.8f, 0L, 5, null));
            ComposerKt.sourceInformationMarkerStart($composer2, 1146373823, "CC(remember):MotionUtils.kt#9igjgp");
            Object objRememberedValue3 = $composer2.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return Integer.valueOf(MotionUtilsKt.ScrollToTopFAB$lambda$85$lambda$84(((Integer) obj3).intValue()));
                    }
                };
                $composer2.updateRememberedValue(obj2);
                objRememberedValue3 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AnimatedVisibilityKt.AnimatedVisibility(isVisible, modifier4, enterTransitionPlus, EnterExitTransitionKt.slideOutVertically$default(null, (Function1) objRememberedValue3, 1, null).plus(EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(200, 0, null, 6, null), 0.0f, 2, null)).plus(EnterExitTransitionKt.m89scaleOutL8ZKhE$default(null, 0.8f, 0L, 5, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(-1049413699, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj3, Object obj4, Object obj5) {
                    return MotionUtilsKt.ScrollToTopFAB$lambda$88(coroutineScope4, lazyListState, (AnimatedVisibilityScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                }
            }, $composer2, 54), $composer2, ($dirty2 & 112) | 200064, 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            coroutineScope3 = coroutineScope4;
            modifier3 = modifier4;
            $dirty = threshold2;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            $dirty = i2;
            coroutineScope3 = coroutineScope2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return MotionUtilsKt.ScrollToTopFAB$lambda$89(lazyListState, modifier3, $dirty, coroutineScope3, $changed, i, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    static final int ScrollToTopFAB$lambda$83$lambda$82(int it) {
        return it / 2;
    }

    static final int ScrollToTopFAB$lambda$85$lambda$84(int it) {
        return it / 2;
    }

    static final Unit ScrollToTopFAB$lambda$88$lambda$87$lambda$86(CoroutineScope $coroutineScope, LazyListState $lazyListState) {
        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new MotionUtilsKt$ScrollToTopFAB$7$1$1$1($lazyListState, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x00a3  */
    static final Unit ScrollToTopFAB$lambda$88(final CoroutineScope $coroutineScope, final LazyListState $lazyListState, AnimatedVisibilityScope AnimatedVisibility, Composer $composer, int $changed) {
        long j;
        Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
        ComposerKt.sourceInformation($composer, "C645@20483L11,646@20554L11,640@20308L130,639@20259L573:MotionUtils.kt#b4fcsz");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1049413699, $changed, -1, "com.example.sushikhasacademyapp.ui.animation.ScrollToTopFAB.<anonymous> (MotionUtils.kt:639)");
        }
        long jM2239getPrimaryContainer0d7_KjU = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimaryContainer();
        long jM2223getOnPrimaryContainer0d7_KjU = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimaryFixed();
        RoundedCornerShape circleShape = RoundedCornerShapeKt.getCircleShape();
        Modifier modifierBounceClick$default = bounceClick$default(Modifier.INSTANCE, 0.0f, null, 3, null);
        ComposerKt.sourceInformationMarkerStart($composer, 830751839, "CC(remember):MotionUtils.kt#9igjgp");
        boolean zChangedInstance = $composer.changedInstance($coroutineScope) | $composer.changed($lazyListState);
        Object objRememberedValue = $composer.rememberedValue();
        if (!zChangedInstance) {
            j = jM2239getPrimaryContainer0d7_KjU;
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            FloatingActionButtonKt.m2477SmallFloatingActionButtonXz6DiA((Function0) objRememberedValue, modifierBounceClick$default, circleShape, j, jM2223getOnPrimaryContainer0d7_KjU, null, null, ComposableSingletons$MotionUtilsKt.INSTANCE.m8734getLambda$1979134950$app(), $composer, 12582912, 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return Unit.INSTANCE;
        }
        j = jM2239getPrimaryContainer0d7_KjU;
        Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda32
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MotionUtilsKt.ScrollToTopFAB$lambda$88$lambda$87$lambda$86($coroutineScope, $lazyListState);
            }
        };
        $composer.updateRememberedValue(obj);
        objRememberedValue = obj;
        ComposerKt.sourceInformationMarkerEnd($composer);
        FloatingActionButtonKt.m2477SmallFloatingActionButtonXz6DiA((Function0) objRememberedValue, modifierBounceClick$default, circleShape, j, jM2223getOnPrimaryContainer0d7_KjU, null, null, ComposableSingletons$MotionUtilsKt.INSTANCE.m8734getLambda$1979134950$app(), $composer, 12582912, 96);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: elevateOnScroll-wH6b6FI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m8744elevateOnScrollwH6b6FI$default(Modifier modifier, ScrollState scrollState, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = Dp.m7905constructorimpl(6);
        }
        return m8743elevateOnScrollwH6b6FI(modifier, scrollState, f);
    }

    /* JADX INFO: renamed from: elevateOnScroll-wH6b6FI, reason: not valid java name */
    public static final Modifier m8743elevateOnScrollwH6b6FI(Modifier elevateOnScroll, final ScrollState scrollState, final float maxElevation) {
        Intrinsics.checkNotNullParameter(elevateOnScroll, "$this$elevateOnScroll");
        Intrinsics.checkNotNullParameter(scrollState, "scrollState");
        return ComposedModifierKt.composed$default(elevateOnScroll, null, new Function3() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return MotionUtilsKt.elevateOnScroll_wH6b6FI$lambda$93(scrollState, maxElevation, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, null);
    }

    private static final float elevateOnScroll_wH6b6FI$lambda$93$lambda$90(State<Dp> state) {
        return ((Dp) state.getValue()).m7919unboximpl();
    }

    static final Modifier elevateOnScroll_wH6b6FI$lambda$93(ScrollState $scrollState, float $maxElevation, Modifier composed, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composed, "$this$composed");
        $composer.startReplaceGroup(-37055720);
        ComposerKt.sourceInformation($composer, "C665@21046L167,670@21237L50:MotionUtils.kt#b4fcsz");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-37055720, $changed, -1, "com.example.sushikhasacademyapp.ui.animation.elevateOnScroll.<anonymous> (MotionUtils.kt:665)");
        }
        final State<Dp> stateM162animateDpAsStateAjpBEmI = AnimateAsStateKt.m162animateDpAsStateAjpBEmI($scrollState.getValue() > 10 ? $maxElevation : Dp.m7905constructorimpl(0), AnimationSpecKt.tween$default(200, 0, null, 6, null), "scrollElevation", null, $composer, 432, 8);
        ComposerKt.sourceInformationMarkerStart($composer, -1250801654, "CC(remember):MotionUtils.kt#9igjgp");
        boolean zChanged = $composer.changed(stateM162animateDpAsStateAjpBEmI);
        Object objRememberedValue = $composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return MotionUtilsKt.elevateOnScroll_wH6b6FI$lambda$93$lambda$92$lambda$91(stateM162animateDpAsStateAjpBEmI, (GraphicsLayerScope) obj2);
                }
            };
            $composer.updateRememberedValue(obj);
            objRememberedValue = obj;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(composed, (Function1) objRememberedValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierGraphicsLayer;
    }

    static final Unit elevateOnScroll_wH6b6FI$lambda$93$lambda$92$lambda$91(State $elevation$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setShadowElevation(graphicsLayer.mo406toPx0680j_4(elevateOnScroll_wH6b6FI$lambda$93$lambda$90($elevation$delegate)));
        return Unit.INSTANCE;
    }
}
