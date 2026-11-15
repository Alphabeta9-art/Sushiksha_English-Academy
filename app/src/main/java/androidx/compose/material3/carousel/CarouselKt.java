package androidx.compose.material3.carousel;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.pager.PagerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Carousel.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0098\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u001021\u0010\u0011\u001a-\u0012\u0004\u0012\u00020\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u008e\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u001021\u0010\u0011\u001a-\u0012\u0004\u0012\u00020\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0084\u0001\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u001021\u0010\u0011\u001a-\u0012\u0004\u0012\u00020\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0004\b \u0010!\u001az\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\u001021\u0010\u0011\u001a-\u0012\u0004\u0012\u00020\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0004\b\"\u0010#\u001a\u009a\u0001\u0010$\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010%\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u001021\u0010\u0011\u001a-\u0012\u0004\u0012\u00020\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0004\b&\u0010'\u001aÂ\u0001\u0010(\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*26\u0010+\u001a2\u0012\u0013\u0012\u00110,¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(-\u0012\u0013\u0012\u00110,¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020.0\u00122\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\u00142\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f21\u0010\u0011\u001a-\u0012\u0004\u0012\u00020\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0001¢\u0006\u0004\b0\u00101\u001a\u0019\u00102\u001a\u00020,*\u00020\u00102\u0006\u0010)\u001a\u00020*H\u0003¢\u0006\u0002\u00103\u001a\u0019\u00104\u001a\u00020,*\u00020\u00102\u0006\u0010)\u001a\u00020*H\u0003¢\u0006\u0002\u00103\u001a:\u00105\u001a\u00020\u0007*\u00020\u00072\u0006\u00106\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\f\u00107\u001a\b\u0012\u0004\u0012\u000209082\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0000\u001a7\u0010>\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010?\u001a\u00020@2\b\b\u0002\u0010A\u001a\u00020B2\b\b\u0002\u0010C\u001a\u00020\u0005H\u0002¢\u0006\u0004\bD\u0010E\u001a\u0018\u0010F\u001a\u00020,2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00107\u001a\u000209H\u0000\u001a\u0018\u0010G\u001a\u00020,2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00107\u001a\u000209H\u0001\u001a \u0010H\u001a\u00020,2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020J2\u0006\u0010L\u001a\u00020,H\u0002¨\u0006M"}, d2 = {"HorizontalMultiBrowseCarousel", "", "state", "Landroidx/compose/material3/carousel/CarouselState;", "preferredItemWidth", "Landroidx/compose/ui/unit/Dp;", "modifier", "Landroidx/compose/ui/Modifier;", "itemSpacing", "flingBehavior", "Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "userScrollEnabled", "", "minSmallItemWidth", "maxSmallItemWidth", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function2;", "Landroidx/compose/material3/carousel/CarouselItemScope;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "itemIndex", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "HorizontalMultiBrowseCarousel-3tcCNu0", "(Landroidx/compose/material3/carousel/CarouselState;FLandroidx/compose/ui/Modifier;FLandroidx/compose/foundation/gestures/TargetedFlingBehavior;ZFFLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "HorizontalMultiBrowseCarousel-zCIJ0Nk", "(Landroidx/compose/material3/carousel/CarouselState;FLandroidx/compose/ui/Modifier;FLandroidx/compose/foundation/gestures/TargetedFlingBehavior;FFLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "HorizontalUncontainedCarousel", "itemWidth", "HorizontalUncontainedCarousel-VUP9l70", "(Landroidx/compose/material3/carousel/CarouselState;FLandroidx/compose/ui/Modifier;FLandroidx/compose/foundation/gestures/TargetedFlingBehavior;ZLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "HorizontalUncontainedCarousel-9QcgTRs", "(Landroidx/compose/material3/carousel/CarouselState;FLandroidx/compose/ui/Modifier;FLandroidx/compose/foundation/gestures/TargetedFlingBehavior;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "HorizontalCenteredHeroCarousel", "maxItemWidth", "HorizontalCenteredHeroCarousel-p2lB3Bg", "(Landroidx/compose/material3/carousel/CarouselState;Landroidx/compose/ui/Modifier;FFLandroidx/compose/foundation/gestures/TargetedFlingBehavior;ZFFLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "Carousel", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "keylineList", "", "availableSpace", "Landroidx/compose/material3/carousel/KeylineList;", "maxNonFocalVisibleItemCount", "Carousel-cJHQLPU", "(Landroidx/compose/material3/carousel/CarouselState;Landroidx/compose/foundation/gestures/Orientation;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;ILandroidx/compose/ui/Modifier;FLandroidx/compose/foundation/gestures/TargetedFlingBehavior;ZLkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "calculateBeforeContentPadding", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/runtime/Composer;I)F", "calculateAfterContentPadding", "carouselItem", "index", "strategy", "Lkotlin/Function0;", "Landroidx/compose/material3/carousel/Strategy;", "carouselItemDrawInfo", "Landroidx/compose/material3/carousel/CarouselItemDrawInfoImpl;", "clipShape", "Landroidx/compose/ui/graphics/Shape;", "drawDebugLines", "pageSize", "Landroidx/compose/material3/carousel/CarouselPageSize;", "strokeColor", "Landroidx/compose/ui/graphics/Color;", "strokeWidth", "drawDebugLines-1Yev-eo", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/carousel/CarouselState;Landroidx/compose/material3/carousel/CarouselPageSize;JF)Landroidx/compose/ui/Modifier;", "calculateCurrentScrollOffset", "calculateMaxScrollOffset", "getProgress", TtmlNode.ANNOTATION_POSITION_BEFORE, "Landroidx/compose/material3/carousel/Keyline;", TtmlNode.ANNOTATION_POSITION_AFTER, "unadjustedOffset", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class CarouselKt {
    static final Unit Carousel_cJHQLPU$lambda$19(CarouselState carouselState, Orientation orientation, Function2 function2, PaddingValues paddingValues, int i, Modifier modifier, float f, TargetedFlingBehavior targetedFlingBehavior, boolean z, Function4 function4, int i2, int i3, Composer composer, int i4) {
        m3330CarouselcJHQLPU(carouselState, orientation, function2, paddingValues, i, modifier, f, targetedFlingBehavior, z, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit HorizontalCenteredHeroCarousel_p2lB3Bg$lambda$13(CarouselState carouselState, Modifier modifier, float f, float f2, TargetedFlingBehavior targetedFlingBehavior, boolean z, float f3, float f4, PaddingValues paddingValues, Function4 function4, int i, int i2, Composer composer, int i3) {
        m3331HorizontalCenteredHeroCarouselp2lB3Bg(carouselState, modifier, f, f2, targetedFlingBehavior, z, f3, f4, paddingValues, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit HorizontalMultiBrowseCarousel_3tcCNu0$lambda$3(CarouselState carouselState, float f, Modifier modifier, float f2, TargetedFlingBehavior targetedFlingBehavior, boolean z, float f3, float f4, PaddingValues paddingValues, Function4 function4, int i, int i2, Composer composer, int i3) {
        m3332HorizontalMultiBrowseCarousel3tcCNu0(carouselState, f, modifier, f2, targetedFlingBehavior, z, f3, f4, paddingValues, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit HorizontalMultiBrowseCarousel_zCIJ0Nk$lambda$4(CarouselState carouselState, float f, Modifier modifier, float f2, TargetedFlingBehavior targetedFlingBehavior, float f3, float f4, PaddingValues paddingValues, Function4 function4, int i, int i2, Composer composer, int i3) {
        m3333HorizontalMultiBrowseCarouselzCIJ0Nk(carouselState, f, modifier, f2, targetedFlingBehavior, f3, f4, paddingValues, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit HorizontalUncontainedCarousel_9QcgTRs$lambda$9(CarouselState carouselState, float f, Modifier modifier, float f2, TargetedFlingBehavior targetedFlingBehavior, PaddingValues paddingValues, Function4 function4, int i, int i2, Composer composer, int i3) {
        m3334HorizontalUncontainedCarousel9QcgTRs(carouselState, f, modifier, f2, targetedFlingBehavior, paddingValues, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit HorizontalUncontainedCarousel_VUP9l70$lambda$8(CarouselState carouselState, float f, Modifier modifier, float f2, TargetedFlingBehavior targetedFlingBehavior, boolean z, PaddingValues paddingValues, Function4 function4, int i, int i2, Composer composer, int i3) {
        m3335HorizontalUncontainedCarouselVUP9l70(carouselState, f, modifier, f2, targetedFlingBehavior, z, paddingValues, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: HorizontalMultiBrowseCarousel-3tcCNu0, reason: not valid java name */
    public static final void m3332HorizontalMultiBrowseCarousel3tcCNu0(final CarouselState state, final float preferredItemWidth, Modifier modifier, float itemSpacing, TargetedFlingBehavior flingBehavior, boolean userScrollEnabled, float minSmallItemWidth, float maxSmallItemWidth, PaddingValues contentPadding, final Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float itemSpacing2;
        TargetedFlingBehavior flingBehavior2;
        boolean userScrollEnabled2;
        final float minSmallItemWidth2;
        PaddingValues paddingValues;
        Composer $composer2;
        final float maxSmallItemWidth2;
        final Modifier modifier3;
        final float itemSpacing3;
        final TargetedFlingBehavior flingBehavior3;
        final boolean userScrollEnabled3;
        final PaddingValues contentPadding2;
        Modifier modifier4;
        CarouselState carouselState;
        float maxSmallItemWidth3;
        PaddingValues contentPadding3;
        float maxSmallItemWidth4;
        float maxSmallItemWidth5;
        TargetedFlingBehavior flingBehavior4;
        boolean userScrollEnabled4;
        Modifier modifier5;
        int $dirty;
        float maxSmallItemWidth6;
        Composer $composer3 = $composer.startRestartGroup(-221490402);
        ComposerKt.sourceInformation($composer3, "C(HorizontalMultiBrowseCarousel)N(state,preferredItemWidth:c#ui.unit.Dp,modifier,itemSpacing:c#ui.unit.Dp,flingBehavior,userScrollEnabled,minSmallItemWidth:c#ui.unit.Dp,maxSmallItemWidth:c#ui.unit.Dp,contentPadding,content)121@6176L7,125@6289L565,122@6188L1107:Carousel.kt#dcf9yb");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changedInstance(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(preferredItemWidth) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
            itemSpacing2 = itemSpacing;
        } else if (($changed & 3072) == 0) {
            itemSpacing2 = itemSpacing;
            $dirty2 |= $composer3.changed(itemSpacing2) ? 2048 : 1024;
        } else {
            itemSpacing2 = itemSpacing;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                flingBehavior2 = flingBehavior;
                int i4 = $composer3.changed(flingBehavior2) ? 16384 : 8192;
                $dirty2 |= i4;
            } else {
                flingBehavior2 = flingBehavior;
            }
            $dirty2 |= i4;
        } else {
            flingBehavior2 = flingBehavior;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            userScrollEnabled2 = userScrollEnabled;
        } else if ((196608 & $changed) == 0) {
            userScrollEnabled2 = userScrollEnabled;
            $dirty2 |= $composer3.changed(userScrollEnabled2) ? 131072 : 65536;
        } else {
            userScrollEnabled2 = userScrollEnabled;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
            minSmallItemWidth2 = minSmallItemWidth;
        } else if (($changed & 1572864) == 0) {
            minSmallItemWidth2 = minSmallItemWidth;
            $dirty2 |= $composer3.changed(minSmallItemWidth2) ? 1048576 : 524288;
        } else {
            minSmallItemWidth2 = minSmallItemWidth;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(maxSmallItemWidth) ? 8388608 : 4194304;
        }
        int i8 = i & 256;
        if (i8 != 0) {
            $dirty2 |= 100663296;
            paddingValues = contentPadding;
        } else if (($changed & 100663296) == 0) {
            paddingValues = contentPadding;
            $dirty2 |= $composer3.changed(paddingValues) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            paddingValues = contentPadding;
        }
        int $dirty3 = $dirty2;
        if ((i & 512) != 0) {
            $dirty3 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty3 |= $composer3.changedInstance(function4) ? 536870912 : 268435456;
        }
        if ($composer3.shouldExecute(($dirty3 & 306783379) != 306783378, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "114@5807L41");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                carouselState = state;
                modifier5 = modifier2;
                maxSmallItemWidth5 = minSmallItemWidth2;
                flingBehavior4 = flingBehavior2;
                userScrollEnabled4 = userScrollEnabled2;
                $dirty = $dirty3;
                contentPadding3 = paddingValues;
                maxSmallItemWidth4 = maxSmallItemWidth;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i3 != 0) {
                    itemSpacing2 = Dp.m7905constructorimpl(0);
                }
                int $this$dp$iv = i & 16;
                if ($this$dp$iv != 0) {
                    int i9 = ($dirty3 & 14) | 384;
                    carouselState = state;
                    $dirty3 &= -57345;
                    flingBehavior2 = CarouselDefaults.INSTANCE.singleAdvanceFlingBehavior(carouselState, null, $composer3, i9, 2);
                } else {
                    carouselState = state;
                }
                if (i5 != 0) {
                    userScrollEnabled2 = true;
                }
                if (i6 != 0) {
                    minSmallItemWidth2 = CarouselDefaults.INSTANCE.m3329getMinSmallItemSizeD9Ej5fM();
                }
                if (i7 == 0) {
                    maxSmallItemWidth3 = maxSmallItemWidth;
                } else {
                    maxSmallItemWidth3 = CarouselDefaults.INSTANCE.m3328getMaxSmallItemSizeD9Ej5fM();
                }
                if (i8 == 0) {
                    contentPadding3 = contentPadding;
                    maxSmallItemWidth4 = maxSmallItemWidth3;
                    maxSmallItemWidth5 = minSmallItemWidth2;
                    flingBehavior4 = flingBehavior2;
                    userScrollEnabled4 = userScrollEnabled2;
                    modifier5 = modifier4;
                    $dirty = $dirty3;
                } else {
                    contentPadding3 = PaddingKt.m812PaddingValues0680j_4(Dp.m7905constructorimpl(0));
                    userScrollEnabled4 = userScrollEnabled2;
                    modifier5 = modifier4;
                    $dirty = $dirty3;
                    maxSmallItemWidth4 = maxSmallItemWidth3;
                    maxSmallItemWidth5 = minSmallItemWidth2;
                    flingBehavior4 = flingBehavior2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-221490402, $dirty, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:120)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            final float minSmallItemWidth3 = maxSmallItemWidth5;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Density density = (Density) objConsume;
            Orientation orientation = Orientation.Horizontal;
            ComposerKt.sourceInformationMarkerStart($composer3, 1896112723, "CC(remember):Carousel.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(density) | (($dirty & 112) == 32) | $composer3.changedInstance(carouselState) | ((3670016 & $dirty) == 1048576) | ((29360128 & $dirty) == 8388608);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                final float maxSmallItemWidth7 = maxSmallItemWidth4;
                Object value$iv = new Function2() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        Density density2 = density;
                        return KeylinesKt.multiBrowseKeylineList(density2, ((Float) obj).floatValue(), density2.mo406toPx0680j_4(preferredItemWidth), ((Float) obj2).floatValue(), state.getPagerState().getPageCountState().getValue().invoke().intValue(), density2.mo406toPx0680j_4(minSmallItemWidth3), density2.mo406toPx0680j_4(maxSmallItemWidth7));
                    }
                };
                maxSmallItemWidth6 = maxSmallItemWidth7;
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            } else {
                maxSmallItemWidth6 = maxSmallItemWidth4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            PaddingValues contentPadding4 = contentPadding3;
            float itemSpacing4 = itemSpacing2;
            m3330CarouselcJHQLPU(state, orientation, (Function2) it$iv, contentPadding4, 2, modifier5, itemSpacing4, flingBehavior4, userScrollEnabled4, function4, $composer3, ($dirty & 14) | 24624 | (($dirty >> 15) & 7168) | (($dirty << 9) & 458752) | (($dirty << 9) & 3670016) | (($dirty << 9) & 29360128) | (($dirty << 9) & 234881024) | (1879048192 & $dirty), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contentPadding2 = contentPadding4;
            modifier3 = modifier5;
            itemSpacing3 = itemSpacing4;
            flingBehavior3 = flingBehavior4;
            userScrollEnabled3 = userScrollEnabled4;
            minSmallItemWidth2 = minSmallItemWidth3;
            maxSmallItemWidth2 = maxSmallItemWidth6;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            maxSmallItemWidth2 = maxSmallItemWidth;
            modifier3 = modifier2;
            itemSpacing3 = itemSpacing2;
            flingBehavior3 = flingBehavior2;
            userScrollEnabled3 = userScrollEnabled2;
            contentPadding2 = contentPadding;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CarouselKt.HorizontalMultiBrowseCarousel_3tcCNu0$lambda$3(state, preferredItemWidth, modifier3, itemSpacing3, flingBehavior3, userScrollEnabled3, minSmallItemWidth2, maxSmallItemWidth2, contentPadding2, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility")
    /* JADX INFO: renamed from: HorizontalMultiBrowseCarousel-zCIJ0Nk, reason: not valid java name */
    public static final /* synthetic */ void m3333HorizontalMultiBrowseCarouselzCIJ0Nk(final CarouselState state, final float preferredItemWidth, Modifier modifier, float itemSpacing, TargetedFlingBehavior flingBehavior, float minSmallItemWidth, float maxSmallItemWidth, PaddingValues contentPadding, final Function4 content, Composer $composer, final int $changed, final int i) {
        CarouselState carouselState;
        float f;
        float f2;
        TargetedFlingBehavior flingBehavior2;
        float minSmallItemWidth2;
        float maxSmallItemWidth2;
        PaddingValues paddingValues;
        Composer $composer2;
        final Modifier modifier2;
        final PaddingValues contentPadding2;
        final float itemSpacing2;
        final TargetedFlingBehavior flingBehavior3;
        final float maxSmallItemWidth3;
        final float minSmallItemWidth3;
        Modifier modifier3;
        int $dirty;
        float minSmallItemWidth4;
        TargetedFlingBehavior flingBehavior4;
        float maxSmallItemWidth4;
        float itemSpacing3;
        int i2;
        PaddingValues contentPadding3;
        Composer $composer3 = $composer.startRestartGroup(-118598974);
        ComposerKt.sourceInformation($composer3, "C(HorizontalMultiBrowseCarousel)N(state,preferredItemWidth:c#ui.unit.Dp,modifier,itemSpacing:c#ui.unit.Dp,flingBehavior,minSmallItemWidth:c#ui.unit.Dp,maxSmallItemWidth:c#ui.unit.Dp,contentPadding,content)165@7948L407:Carousel.kt#dcf9yb");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            carouselState = state;
        } else if (($changed & 6) == 0) {
            carouselState = state;
            $dirty2 |= $composer3.changedInstance(carouselState) ? 4 : 2;
        } else {
            carouselState = state;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            f = preferredItemWidth;
        } else if (($changed & 48) == 0) {
            f = preferredItemWidth;
            $dirty2 |= $composer3.changed(f) ? 32 : 16;
        } else {
            f = preferredItemWidth;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            f2 = itemSpacing;
        } else if (($changed & 3072) == 0) {
            f2 = itemSpacing;
            $dirty2 |= $composer3.changed(f2) ? 2048 : 1024;
        } else {
            f2 = itemSpacing;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                flingBehavior2 = flingBehavior;
                int i5 = $composer3.changed(flingBehavior2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                flingBehavior2 = flingBehavior;
            }
            $dirty2 |= i5;
        } else {
            flingBehavior2 = flingBehavior;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            minSmallItemWidth2 = minSmallItemWidth;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            minSmallItemWidth2 = minSmallItemWidth;
            $dirty2 |= $composer3.changed(minSmallItemWidth2) ? 131072 : 65536;
        } else {
            minSmallItemWidth2 = minSmallItemWidth;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            maxSmallItemWidth2 = maxSmallItemWidth;
        } else if (($changed & 1572864) == 0) {
            maxSmallItemWidth2 = maxSmallItemWidth;
            $dirty2 |= $composer3.changed(maxSmallItemWidth2) ? 1048576 : 524288;
        } else {
            maxSmallItemWidth2 = maxSmallItemWidth;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
            paddingValues = contentPadding;
        } else if (($changed & 12582912) == 0) {
            paddingValues = contentPadding;
            $dirty2 |= $composer3.changed(paddingValues) ? 8388608 : 4194304;
        } else {
            paddingValues = contentPadding;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changedInstance(content) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty3 = $dirty2;
        if ($composer3.shouldExecute(($dirty3 & 38347923) != 38347922, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "159@7645L41");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier;
                float itemSpacing4 = i4 != 0 ? Dp.m7905constructorimpl(0) : f2;
                if ((i & 16) != 0) {
                    int i9 = ($dirty3 & 14) | 384;
                    $dirty3 &= -57345;
                    flingBehavior2 = CarouselDefaults.INSTANCE.singleAdvanceFlingBehavior(carouselState, null, $composer3, i9, 2);
                }
                if (i6 != 0) {
                    minSmallItemWidth2 = CarouselDefaults.INSTANCE.m3329getMinSmallItemSizeD9Ej5fM();
                }
                if (i7 != 0) {
                    maxSmallItemWidth2 = CarouselDefaults.INSTANCE.m3328getMaxSmallItemSizeD9Ej5fM();
                }
                if (i8 != 0) {
                    float f3 = minSmallItemWidth2;
                    modifier3 = modifier4;
                    $dirty = $dirty3;
                    minSmallItemWidth4 = f3;
                    flingBehavior4 = flingBehavior2;
                    maxSmallItemWidth4 = maxSmallItemWidth2;
                    itemSpacing3 = itemSpacing4;
                    contentPadding3 = PaddingKt.m812PaddingValues0680j_4(Dp.m7905constructorimpl(0));
                    i2 = -118598974;
                } else {
                    float f4 = minSmallItemWidth2;
                    modifier3 = modifier4;
                    $dirty = $dirty3;
                    minSmallItemWidth4 = f4;
                    flingBehavior4 = flingBehavior2;
                    maxSmallItemWidth4 = maxSmallItemWidth2;
                    itemSpacing3 = itemSpacing4;
                    i2 = -118598974;
                    contentPadding3 = contentPadding;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                contentPadding3 = paddingValues;
                itemSpacing3 = f2;
                flingBehavior4 = flingBehavior2;
                maxSmallItemWidth4 = maxSmallItemWidth2;
                $dirty = $dirty3;
                i2 = -118598974;
                minSmallItemWidth4 = minSmallItemWidth2;
                modifier3 = modifier;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel (Carousel.kt:165)");
            }
            $composer2 = $composer3;
            m3332HorizontalMultiBrowseCarousel3tcCNu0(state, f, modifier3, itemSpacing3, flingBehavior4, true, minSmallItemWidth4, maxSmallItemWidth4, contentPadding3, content, $composer2, ($dirty & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128) | (($dirty << 3) & 234881024) | (($dirty << 3) & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            itemSpacing2 = itemSpacing3;
            flingBehavior3 = flingBehavior4;
            minSmallItemWidth3 = minSmallItemWidth4;
            maxSmallItemWidth3 = maxSmallItemWidth4;
            contentPadding2 = contentPadding3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            contentPadding2 = contentPadding;
            itemSpacing2 = f2;
            flingBehavior3 = flingBehavior2;
            maxSmallItemWidth3 = maxSmallItemWidth2;
            minSmallItemWidth3 = minSmallItemWidth2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CarouselKt.HorizontalMultiBrowseCarousel_zCIJ0Nk$lambda$4(state, preferredItemWidth, modifier2, itemSpacing2, flingBehavior3, minSmallItemWidth3, maxSmallItemWidth3, contentPadding2, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: HorizontalUncontainedCarousel-VUP9l70, reason: not valid java name */
    public static final void m3335HorizontalUncontainedCarouselVUP9l70(final CarouselState state, final float itemWidth, Modifier modifier, float itemSpacing, TargetedFlingBehavior flingBehavior, boolean userScrollEnabled, PaddingValues contentPadding, final Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        CarouselState carouselState;
        Modifier modifier2;
        float itemSpacing2;
        TargetedFlingBehavior flingBehavior2;
        boolean userScrollEnabled2;
        PaddingValues paddingValues;
        final PaddingValues contentPadding2;
        final Modifier modifier3;
        final float itemSpacing3;
        final TargetedFlingBehavior flingBehavior3;
        final boolean userScrollEnabled3;
        int $dirty;
        float itemSpacing4;
        TargetedFlingBehavior flingBehavior4;
        boolean userScrollEnabled4;
        PaddingValues contentPadding3;
        Composer $composer2 = $composer.startRestartGroup(534621863);
        ComposerKt.sourceInformation($composer2, "C(HorizontalUncontainedCarousel)N(state,itemWidth:c#ui.unit.Dp,modifier,itemSpacing:c#ui.unit.Dp,flingBehavior,userScrollEnabled,contentPadding,content)218@10352L7,222@10465L337,219@10364L866:Carousel.kt#dcf9yb");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            carouselState = state;
        } else if (($changed & 6) == 0) {
            carouselState = state;
            $dirty2 |= $composer2.changedInstance(carouselState) ? 4 : 2;
        } else {
            carouselState = state;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changed(itemWidth) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
            itemSpacing2 = itemSpacing;
        } else if (($changed & 3072) == 0) {
            itemSpacing2 = itemSpacing;
            $dirty2 |= $composer2.changed(itemSpacing2) ? 2048 : 1024;
        } else {
            itemSpacing2 = itemSpacing;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                flingBehavior2 = flingBehavior;
                int i4 = $composer2.changed(flingBehavior2) ? 16384 : 8192;
                $dirty2 |= i4;
            } else {
                flingBehavior2 = flingBehavior;
            }
            $dirty2 |= i4;
        } else {
            flingBehavior2 = flingBehavior;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            userScrollEnabled2 = userScrollEnabled;
        } else if ((196608 & $changed) == 0) {
            userScrollEnabled2 = userScrollEnabled;
            $dirty2 |= $composer2.changed(userScrollEnabled2) ? 131072 : 65536;
        } else {
            userScrollEnabled2 = userScrollEnabled;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
            paddingValues = contentPadding;
        } else if (($changed & 1572864) == 0) {
            paddingValues = contentPadding;
            $dirty2 |= $composer2.changed(paddingValues) ? 1048576 : 524288;
        } else {
            paddingValues = contentPadding;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer2.changedInstance(function4) ? 8388608 : 4194304;
        }
        int $dirty3 = $dirty2;
        if ($composer2.shouldExecute(($dirty2 & 4793491) != 4793490, $dirty3 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "213@10129L21");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    itemSpacing4 = itemSpacing2;
                    flingBehavior4 = flingBehavior2;
                    userScrollEnabled4 = userScrollEnabled2;
                    contentPadding3 = paddingValues;
                } else {
                    $dirty = $dirty3;
                    itemSpacing4 = itemSpacing2;
                    flingBehavior4 = flingBehavior2;
                    userScrollEnabled4 = userScrollEnabled2;
                    contentPadding3 = paddingValues;
                }
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    itemSpacing2 = Dp.m7905constructorimpl(0);
                }
                int $this$dp$iv = i & 16;
                if ($this$dp$iv != 0) {
                    $dirty = $dirty3 & (-57345);
                    flingBehavior2 = CarouselDefaults.INSTANCE.noSnapFlingBehavior($composer2, 6);
                } else {
                    $dirty = $dirty3;
                }
                if (i5 != 0) {
                    userScrollEnabled2 = true;
                }
                if (i6 == 0) {
                    itemSpacing4 = itemSpacing2;
                    flingBehavior4 = flingBehavior2;
                    userScrollEnabled4 = userScrollEnabled2;
                    contentPadding3 = paddingValues;
                } else {
                    itemSpacing4 = itemSpacing2;
                    userScrollEnabled4 = userScrollEnabled2;
                    contentPadding3 = PaddingKt.m812PaddingValues0680j_4(Dp.m7905constructorimpl(0));
                    flingBehavior4 = flingBehavior2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(534621863, $dirty, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:217)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final Density density = (Density) objConsume;
            Orientation orientation = Orientation.Horizontal;
            ComposerKt.sourceInformationMarkerStart($composer2, -1624446376, "CC(remember):Carousel.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(density) | (($dirty & 112) == 32);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function2() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        Density density2 = density;
                        return KeylinesKt.uncontainedKeylineList(density2, ((Float) obj).floatValue(), density2.mo406toPx0680j_4(itemWidth), ((Float) obj2).floatValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier4 = modifier2;
            m3330CarouselcJHQLPU(carouselState, orientation, (Function2) it$iv, contentPadding3, 0, modifier4, itemSpacing4, flingBehavior4, userScrollEnabled4, function4, $composer2, ($dirty & 14) | 24624 | (($dirty >> 9) & 7168) | (($dirty << 9) & 458752) | (($dirty << 9) & 3670016) | (($dirty << 9) & 29360128) | (($dirty << 9) & 234881024) | (($dirty << 6) & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contentPadding2 = contentPadding3;
            itemSpacing3 = itemSpacing4;
            flingBehavior3 = flingBehavior4;
            userScrollEnabled3 = userScrollEnabled4;
            modifier3 = modifier4;
        } else {
            $composer2 = $composer2;
            $composer2.skipToGroupEnd();
            contentPadding2 = paddingValues;
            modifier3 = modifier2;
            itemSpacing3 = itemSpacing2;
            flingBehavior3 = flingBehavior2;
            userScrollEnabled3 = userScrollEnabled2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CarouselKt.HorizontalUncontainedCarousel_VUP9l70$lambda$8(state, itemWidth, modifier3, itemSpacing3, flingBehavior3, userScrollEnabled3, contentPadding2, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility")
    /* JADX INFO: renamed from: HorizontalUncontainedCarousel-9QcgTRs, reason: not valid java name */
    public static final /* synthetic */ void m3334HorizontalUncontainedCarousel9QcgTRs(final CarouselState state, final float itemWidth, Modifier modifier, float itemSpacing, TargetedFlingBehavior flingBehavior, PaddingValues contentPadding, final Function4 content, Composer $composer, final int $changed, final int i) {
        CarouselState carouselState;
        float f;
        Modifier modifier2;
        float itemSpacing2;
        TargetedFlingBehavior flingBehavior2;
        PaddingValues paddingValues;
        Composer $composer2;
        final Modifier modifier3;
        final float itemSpacing3;
        final TargetedFlingBehavior flingBehavior3;
        final PaddingValues contentPadding2;
        Modifier modifier4;
        float itemSpacing4;
        PaddingValues contentPadding3;
        TargetedFlingBehavior flingBehavior4;
        Composer $composer3 = $composer.startRestartGroup(-2013916597);
        ComposerKt.sourceInformation($composer3, "C(HorizontalUncontainedCarousel)N(state,itemWidth:c#ui.unit.Dp,modifier,itemSpacing:c#ui.unit.Dp,flingBehavior,contentPadding,content)256@11720L295:Carousel.kt#dcf9yb");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            carouselState = state;
        } else if (($changed & 6) == 0) {
            carouselState = state;
            $dirty |= $composer3.changedInstance(carouselState) ? 4 : 2;
        } else {
            carouselState = state;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
            f = itemWidth;
        } else if (($changed & 48) == 0) {
            f = itemWidth;
            $dirty |= $composer3.changed(f) ? 32 : 16;
        } else {
            f = itemWidth;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            itemSpacing2 = itemSpacing;
        } else if (($changed & 3072) == 0) {
            itemSpacing2 = itemSpacing;
            $dirty |= $composer3.changed(itemSpacing2) ? 2048 : 1024;
        } else {
            itemSpacing2 = itemSpacing;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                flingBehavior2 = flingBehavior;
                int i4 = $composer3.changed(flingBehavior2) ? 16384 : 8192;
                $dirty |= i4;
            } else {
                flingBehavior2 = flingBehavior;
            }
            $dirty |= i4;
        } else {
            flingBehavior2 = flingBehavior;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            paddingValues = contentPadding;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            paddingValues = contentPadding;
            $dirty |= $composer3.changed(paddingValues) ? 131072 : 65536;
        } else {
            paddingValues = contentPadding;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(content) ? 1048576 : 524288;
        }
        if ($composer3.shouldExecute((599187 & $dirty) != 599186, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "252@11563L21");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if (i3 != 0) {
                    itemSpacing2 = Dp.m7905constructorimpl(0);
                }
                int $this$dp$iv = i & 16;
                if ($this$dp$iv != 0) {
                    $dirty &= -57345;
                    flingBehavior2 = CarouselDefaults.INSTANCE.noSnapFlingBehavior($composer3, 6);
                }
                if (i5 != 0) {
                    modifier4 = modifier5;
                    contentPadding3 = PaddingKt.m812PaddingValues0680j_4(Dp.m7905constructorimpl(0));
                    itemSpacing4 = itemSpacing2;
                    flingBehavior4 = flingBehavior2;
                } else {
                    modifier4 = modifier5;
                    itemSpacing4 = itemSpacing2;
                    contentPadding3 = paddingValues;
                    flingBehavior4 = flingBehavior2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                modifier4 = modifier2;
                itemSpacing4 = itemSpacing2;
                contentPadding3 = paddingValues;
                flingBehavior4 = flingBehavior2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2013916597, $dirty, -1, "androidx.compose.material3.carousel.HorizontalUncontainedCarousel (Carousel.kt:256)");
            }
            $composer2 = $composer3;
            m3335HorizontalUncontainedCarouselVUP9l70(carouselState, f, modifier4, itemSpacing4, flingBehavior4, true, contentPadding3, content, $composer2, ($dirty & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            itemSpacing3 = itemSpacing4;
            flingBehavior3 = flingBehavior4;
            contentPadding2 = contentPadding3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            itemSpacing3 = itemSpacing2;
            flingBehavior3 = flingBehavior2;
            contentPadding2 = paddingValues;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CarouselKt.HorizontalUncontainedCarousel_9QcgTRs$lambda$9(state, itemWidth, modifier3, itemSpacing3, flingBehavior3, contentPadding2, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: HorizontalCenteredHeroCarousel-p2lB3Bg, reason: not valid java name */
    public static final void m3331HorizontalCenteredHeroCarouselp2lB3Bg(final CarouselState state, Modifier modifier, float maxItemWidth, float itemSpacing, TargetedFlingBehavior flingBehavior, boolean userScrollEnabled, float minSmallItemWidth, float maxSmallItemWidth, PaddingValues contentPadding, final Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float maxItemWidth2;
        float itemSpacing2;
        TargetedFlingBehavior flingBehavior2;
        boolean userScrollEnabled2;
        final float minSmallItemWidth2;
        PaddingValues paddingValues;
        Composer $composer2;
        final PaddingValues contentPadding2;
        final Modifier modifier3;
        final float maxItemWidth3;
        final float itemSpacing3;
        final TargetedFlingBehavior flingBehavior3;
        final boolean userScrollEnabled3;
        final float maxSmallItemWidth2;
        Modifier modifier4;
        CarouselState carouselState;
        float maxSmallItemWidth3;
        PaddingValues contentPadding3;
        float maxSmallItemWidth4;
        float maxSmallItemWidth5;
        float itemSpacing4;
        TargetedFlingBehavior flingBehavior4;
        Modifier modifier5;
        int $dirty;
        float maxItemWidth4;
        Composer $composer3 = $composer.startRestartGroup(1493031269);
        ComposerKt.sourceInformation($composer3, "C(HorizontalCenteredHeroCarousel)N(state,modifier,maxItemWidth:c#ui.unit.Dp,itemSpacing:c#ui.unit.Dp,flingBehavior,userScrollEnabled,minSmallItemWidth:c#ui.unit.Dp,maxSmallItemWidth:c#ui.unit.Dp,contentPadding,content)305@14133L7,309@14246L625,306@14145L1168:Carousel.kt#dcf9yb");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changedInstance(state) ? 4 : 2;
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
            maxItemWidth2 = maxItemWidth;
        } else if (($changed & 384) == 0) {
            maxItemWidth2 = maxItemWidth;
            $dirty2 |= $composer3.changed(maxItemWidth2) ? 256 : 128;
        } else {
            maxItemWidth2 = maxItemWidth;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            itemSpacing2 = itemSpacing;
        } else if (($changed & 3072) == 0) {
            itemSpacing2 = itemSpacing;
            $dirty2 |= $composer3.changed(itemSpacing2) ? 2048 : 1024;
        } else {
            itemSpacing2 = itemSpacing;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                flingBehavior2 = flingBehavior;
                int i5 = $composer3.changed(flingBehavior2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                flingBehavior2 = flingBehavior;
            }
            $dirty2 |= i5;
        } else {
            flingBehavior2 = flingBehavior;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            userScrollEnabled2 = userScrollEnabled;
        } else if ((196608 & $changed) == 0) {
            userScrollEnabled2 = userScrollEnabled;
            $dirty2 |= $composer3.changed(userScrollEnabled2) ? 131072 : 65536;
        } else {
            userScrollEnabled2 = userScrollEnabled;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            minSmallItemWidth2 = minSmallItemWidth;
        } else if (($changed & 1572864) == 0) {
            minSmallItemWidth2 = minSmallItemWidth;
            $dirty2 |= $composer3.changed(minSmallItemWidth2) ? 1048576 : 524288;
        } else {
            minSmallItemWidth2 = minSmallItemWidth;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(maxSmallItemWidth) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty2 |= 100663296;
            paddingValues = contentPadding;
        } else if (($changed & 100663296) == 0) {
            paddingValues = contentPadding;
            $dirty2 |= $composer3.changed(paddingValues) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            paddingValues = contentPadding;
        }
        int $dirty3 = $dirty2;
        if ((i & 512) != 0) {
            $dirty3 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty3 |= $composer3.changedInstance(function4) ? 536870912 : 268435456;
        }
        if ($composer3.shouldExecute(($dirty3 & 306783379) != 306783378, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "298@13764L41");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                carouselState = state;
                modifier5 = modifier2;
                maxSmallItemWidth5 = minSmallItemWidth2;
                itemSpacing4 = itemSpacing2;
                flingBehavior4 = flingBehavior2;
                $dirty = $dirty3;
                contentPadding3 = paddingValues;
                maxSmallItemWidth4 = maxSmallItemWidth;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i3 != 0) {
                    maxItemWidth2 = Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM();
                }
                if (i4 != 0) {
                    itemSpacing2 = Dp.m7905constructorimpl(0);
                }
                int $this$dp$iv = i & 16;
                if ($this$dp$iv != 0) {
                    int i10 = ($dirty3 & 14) | 384;
                    carouselState = state;
                    $dirty3 &= -57345;
                    flingBehavior2 = CarouselDefaults.INSTANCE.singleAdvanceFlingBehavior(carouselState, null, $composer3, i10, 2);
                } else {
                    carouselState = state;
                }
                if (i6 != 0) {
                    userScrollEnabled2 = true;
                }
                if (i7 != 0) {
                    minSmallItemWidth2 = CarouselDefaults.INSTANCE.m3329getMinSmallItemSizeD9Ej5fM();
                }
                if (i8 == 0) {
                    maxSmallItemWidth3 = maxSmallItemWidth;
                } else {
                    maxSmallItemWidth3 = CarouselDefaults.INSTANCE.m3328getMaxSmallItemSizeD9Ej5fM();
                }
                if (i9 == 0) {
                    contentPadding3 = contentPadding;
                    maxSmallItemWidth4 = maxSmallItemWidth3;
                    maxSmallItemWidth5 = minSmallItemWidth2;
                    itemSpacing4 = itemSpacing2;
                    flingBehavior4 = flingBehavior2;
                    modifier5 = modifier4;
                    $dirty = $dirty3;
                } else {
                    contentPadding3 = PaddingKt.m812PaddingValues0680j_4(Dp.m7905constructorimpl(0));
                    itemSpacing4 = itemSpacing2;
                    modifier5 = modifier4;
                    $dirty = $dirty3;
                    maxSmallItemWidth4 = maxSmallItemWidth3;
                    maxSmallItemWidth5 = minSmallItemWidth2;
                    flingBehavior4 = flingBehavior2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1493031269, $dirty, -1, "androidx.compose.material3.carousel.HorizontalCenteredHeroCarousel (Carousel.kt:304)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            final float minSmallItemWidth3 = maxSmallItemWidth5;
            final float maxSmallItemWidth6 = maxSmallItemWidth4;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Density density = (Density) objConsume;
            Orientation orientation = Orientation.Horizontal;
            ComposerKt.sourceInformationMarkerStart($composer3, 283997334, "CC(remember):Carousel.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(density) | (($dirty & 896) == 256) | $composer3.changedInstance(carouselState) | ((3670016 & $dirty) == 1048576) | ((29360128 & $dirty) == 8388608);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                final float maxItemWidth5 = maxItemWidth2;
                Object value$iv = new Function2() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        Density density2 = density;
                        float f = maxItemWidth5;
                        return KeylinesKt.heroKeylineList(density2, ((Float) obj).floatValue(), !Float.isNaN(f) ? Float.valueOf(density2.mo406toPx0680j_4(f)) : null, ((Float) obj2).floatValue(), state.getPagerState().getPageCountState().getValue().invoke().intValue(), true, density2.mo406toPx0680j_4(minSmallItemWidth3), density2.mo406toPx0680j_4(maxSmallItemWidth6));
                    }
                };
                maxItemWidth4 = maxItemWidth5;
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            } else {
                maxItemWidth4 = maxItemWidth2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            PaddingValues contentPadding4 = contentPadding3;
            boolean userScrollEnabled4 = userScrollEnabled2;
            m3330CarouselcJHQLPU(state, orientation, (Function2) it$iv, contentPadding4, 2, modifier5, itemSpacing4, flingBehavior4, userScrollEnabled4, function4, $composer3, ($dirty & 14) | 24624 | (($dirty >> 15) & 7168) | (($dirty << 12) & 458752) | (($dirty << 9) & 3670016) | (($dirty << 9) & 29360128) | (($dirty << 9) & 234881024) | (1879048192 & $dirty), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contentPadding2 = contentPadding4;
            modifier3 = modifier5;
            itemSpacing3 = itemSpacing4;
            flingBehavior3 = flingBehavior4;
            userScrollEnabled3 = userScrollEnabled4;
            maxItemWidth3 = maxItemWidth4;
            minSmallItemWidth2 = minSmallItemWidth3;
            maxSmallItemWidth2 = maxSmallItemWidth6;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            contentPadding2 = contentPadding;
            modifier3 = modifier2;
            maxItemWidth3 = maxItemWidth2;
            itemSpacing3 = itemSpacing2;
            flingBehavior3 = flingBehavior2;
            userScrollEnabled3 = userScrollEnabled2;
            maxSmallItemWidth2 = maxSmallItemWidth;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CarouselKt.HorizontalCenteredHeroCarousel_p2lB3Bg$lambda$13(state, modifier3, maxItemWidth3, itemSpacing3, flingBehavior3, userScrollEnabled3, minSmallItemWidth2, maxSmallItemWidth2, contentPadding2, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:110:0x0149  */
    /* JADX WARN: Code duplicated, block: B:111:0x014b  */
    /* JADX WARN: Code duplicated, block: B:114:0x0154  */
    /* JADX WARN: Code duplicated, block: B:122:0x017f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:123:0x0181  */
    /* JADX WARN: Code duplicated, block: B:124:0x0188  */
    /* JADX WARN: Code duplicated, block: B:126:0x018c  */
    /* JADX WARN: Code duplicated, block: B:129:0x0198  */
    /* JADX WARN: Code duplicated, block: B:130:0x01af  */
    /* JADX WARN: Code duplicated, block: B:132:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:133:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:136:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:139:0x0203  */
    /* JADX WARN: Code duplicated, block: B:140:0x0205  */
    /* JADX WARN: Code duplicated, block: B:143:0x0211  */
    /* JADX WARN: Code duplicated, block: B:146:0x021c  */
    /* JADX WARN: Code duplicated, block: B:150:0x023a  */
    /* JADX WARN: Code duplicated, block: B:152:0x027b  */
    /* JADX WARN: Code duplicated, block: B:155:0x02f0  */
    /* JADX WARN: Code duplicated, block: B:157:0x02f8  */
    /* JADX WARN: Code duplicated, block: B:159:0x0366  */
    /* JADX WARN: Code duplicated, block: B:162:0x03d5  */
    /* JADX WARN: Code duplicated, block: B:165:0x03e5  */
    /* JADX WARN: Code duplicated, block: B:167:0x03f1  */
    /* JADX WARN: Code duplicated, block: B:170:0x0401  */
    /* JADX WARN: Code duplicated, block: B:171:0x0414  */
    /* JADX INFO: renamed from: Carousel-cJHQLPU, reason: not valid java name */
    public static final void m3330CarouselcJHQLPU(CarouselState state, final Orientation orientation, final Function2<? super Float, ? super Float, KeylineList> function2, final PaddingValues contentPadding, final int maxNonFocalVisibleItemCount, Modifier modifier, float itemSpacing, TargetedFlingBehavior flingBehavior, boolean userScrollEnabled, final Function4<? super CarouselItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        int i2;
        Modifier modifier2;
        float itemSpacing2;
        TargetedFlingBehavior flingBehavior2;
        int i3;
        boolean z;
        final CarouselState carouselState;
        Composer $composer2;
        final boolean userScrollEnabled2;
        final TargetedFlingBehavior flingBehavior3;
        final float itemSpacing3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier3;
        int $this$dp$iv;
        boolean z2;
        boolean userScrollEnabled3;
        TargetedFlingBehavior flingBehavior4;
        int $dirty;
        float itemSpacing4;
        boolean invalid$iv;
        Object it$iv;
        int $dirty2;
        CarouselPageSize pageSize;
        SnapPosition snapPosition;
        Object it$iv2;
        Object it$iv3;
        Composer $composer3 = $composer.startRestartGroup(-2077912500);
        ComposerKt.sourceInformation($composer3, "C(Carousel)N(state,orientation,keylineList,contentPadding,maxNonFocalVisibleItemCount,modifier,itemSpacing:c#ui.unit.Dp,flingBehavior,userScrollEnabled,content)375@17497L42,376@17585L41,378@17654L118:Carousel.kt#dcf9yb");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changedInstance(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changed(orientation.ordinal()) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty3 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty3 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty3 |= $composer3.changed(contentPadding) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty3 |= 24576;
            i2 = maxNonFocalVisibleItemCount;
        } else if (($changed & 24576) == 0) {
            i2 = maxNonFocalVisibleItemCount;
            $dirty3 |= $composer3.changed(i2) ? 16384 : 8192;
        } else {
            i2 = maxNonFocalVisibleItemCount;
        }
        int i4 = i & 32;
        if (i4 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            modifier2 = modifier;
        } else if ((196608 & $changed) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 131072 : 65536;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 64;
        if (i5 != 0) {
            $dirty3 |= 1572864;
            itemSpacing2 = itemSpacing;
        } else if ((1572864 & $changed) == 0) {
            itemSpacing2 = itemSpacing;
            $dirty3 |= $composer3.changed(itemSpacing2) ? 1048576 : 524288;
        } else {
            itemSpacing2 = itemSpacing;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                flingBehavior2 = flingBehavior;
                int i6 = $composer3.changed(flingBehavior2) ? 8388608 : 4194304;
                $dirty3 |= i6;
            } else {
                flingBehavior2 = flingBehavior;
            }
            $dirty3 |= i6;
        } else {
            flingBehavior2 = flingBehavior;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changed(userScrollEnabled) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty4 = $dirty3;
        if ((i & 512) == 0) {
            if (($changed & C.ENCODING_PCM_32BIT) == 0) {
                i3 = $dirty4 | ($composer3.changedInstance(function4) ? 536870912 : 268435456);
            }
            if (($dirty4 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if ($composer3.shouldExecute(z, $dirty4 & 1)) {
                $composer3.startDefaults();
                ComposerKt.sourceInformation($composer3, "371@17296L41");
                if (($changed & 1) != 0 || $composer3.getDefaultsInvalid()) {
                    if (i4 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i5 != 0) {
                        itemSpacing2 = Dp.m7905constructorimpl(0);
                    }
                    $this$dp$iv = i & 128;
                    if ($this$dp$iv != 0) {
                        int i8 = ($dirty4 & 14) | 384;
                        z2 = true;
                        carouselState = state;
                        $dirty4 &= -29360129;
                        flingBehavior2 = CarouselDefaults.INSTANCE.singleAdvanceFlingBehavior(carouselState, null, $composer3, i8, 2);
                    } else {
                        carouselState = state;
                        z2 = true;
                    }
                    if (i7 != 0) {
                        userScrollEnabled3 = true;
                        flingBehavior4 = flingBehavior2;
                        modifier2 = modifier3;
                        $dirty = $dirty4;
                        itemSpacing4 = itemSpacing2;
                    } else {
                        userScrollEnabled3 = userScrollEnabled;
                        flingBehavior4 = flingBehavior2;
                        modifier2 = modifier3;
                        $dirty = $dirty4;
                        itemSpacing4 = itemSpacing2;
                    }
                } else {
                    $composer3.skipToGroupEnd();
                    if ((i & 128) != 0) {
                        $dirty4 &= -29360129;
                    }
                    carouselState = state;
                    userScrollEnabled3 = userScrollEnabled;
                    z2 = true;
                    flingBehavior4 = flingBehavior2;
                    $dirty = $dirty4;
                    itemSpacing4 = itemSpacing2;
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2077912500, $dirty, -1, "androidx.compose.material3.carousel.Carousel (Carousel.kt:374)");
                }
                float beforeContentPadding = calculateBeforeContentPadding(contentPadding, orientation, $composer3, (($dirty >> 9) & 14) | ($dirty & 112));
                float afterContentPadding = calculateAfterContentPadding(contentPadding, orientation, $composer3, (($dirty >> 9) & 14) | ($dirty & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, 285904898, "CC(remember):Carousel.kt#9igjgp");
                if (($dirty & 896) == 256) {
                    invalid$iv = z2;
                } else {
                    invalid$iv = false;
                }
                it$iv = $composer3.rememberedValue();
                if (invalid$iv) {
                    $dirty2 = $dirty;
                } else {
                    $dirty2 = $dirty;
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                    }
                    pageSize = (CarouselPageSize) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    snapPosition = KeylineSnapPositionKt.KeylineSnapPosition(pageSize);
                    if (orientation == Orientation.Horizontal) {
                        $composer3.startReplaceGroup(273387289);
                        ComposerKt.sourceInformation($composer3, "399@18566L24,400@18602L1024,385@17885L1741");
                        CarouselPagerState pagerState = carouselState.getPagerState();
                        PaddingValues paddingValuesM816PaddingValuesa9UjIt4$default = PaddingKt.m816PaddingValuesa9UjIt4$default(0.0f, contentPadding.getTop(), 0.0f, contentPadding.getBottom(), 5, null);
                        ComposerKt.sourceInformationMarkerStart($composer3, 285933988, "CC(remember):Carousel.kt#9igjgp");
                        it$iv3 = $composer3.rememberedValue();
                        if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                            Object value$iv = new Function1() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda12
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return CarouselKt.Carousel_cJHQLPU$lambda$16$lambda$15((SemanticsPropertyReceiver) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv);
                            it$iv3 = value$iv;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        PagerKt.m1073HorizontalPager8jOkeI(pagerState, SemanticsModifierKt.semantics$default(modifier2, false, (Function1) it$iv3, 1, null), paddingValuesM816PaddingValuesa9UjIt4$default, pageSize, i2, itemSpacing4, null, flingBehavior4, userScrollEnabled3, false, null, null, snapPosition, null, ComposableLambdaKt.rememberComposableLambda(-1817116752, true, new CarouselKt$Carousel$2(carouselState, pageSize, function4), $composer3, 54), $composer3, ($dirty2 & 57344) | (($dirty2 >> 3) & 458752) | ($dirty2 & 29360128) | ($dirty2 & 234881024), 24576, 11840);
                        $composer3.endReplaceGroup();
                        $composer2 = $composer3;
                    } else if (orientation == Orientation.Vertical) {
                        $composer3.startReplaceGroup(275178469);
                        ComposerKt.sourceInformation($composer3, "434@19985L7,435@20077L7,443@20422L24,444@20458L1024,429@19689L1793");
                        CarouselPagerState pagerState2 = carouselState.getPagerState();
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                        Object objConsume = $composer3.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        float fCalculateStartPadding = PaddingKt.calculateStartPadding(contentPadding, (LayoutDirection) objConsume);
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                        Object objConsume2 = $composer3.consume(localLayoutDirection2);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        PaddingValues paddingValuesM816PaddingValuesa9UjIt4$default2 = PaddingKt.m816PaddingValuesa9UjIt4$default(fCalculateStartPadding, 0.0f, PaddingKt.calculateEndPadding(contentPadding, (LayoutDirection) objConsume2), 0.0f, 10, null);
                        ComposerKt.sourceInformationMarkerStart($composer3, 285993380, "CC(remember):Carousel.kt#9igjgp");
                        it$iv2 = $composer3.rememberedValue();
                        if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                            Object value$iv2 = new Function1() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda13
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return CarouselKt.Carousel_cJHQLPU$lambda$18$lambda$17((SemanticsPropertyReceiver) obj);
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                            it$iv2 = value$iv2;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        PagerKt.m1075VerticalPager8jOkeI(pagerState2, SemanticsModifierKt.semantics$default(modifier2, false, (Function1) it$iv2, 1, null), paddingValuesM816PaddingValuesa9UjIt4$default2, pageSize, maxNonFocalVisibleItemCount, itemSpacing4, null, flingBehavior4, userScrollEnabled3, false, null, null, snapPosition, null, ComposableLambdaKt.rememberComposableLambda(-1042567175, true, new CarouselKt$Carousel$4(carouselState, pageSize, function4), $composer3, 54), $composer3, ($dirty2 & 57344) | (($dirty2 >> 3) & 458752) | ($dirty2 & 29360128) | ($dirty2 & 234881024), 24576, 11840);
                        $composer2 = $composer3;
                        $composer2.endReplaceGroup();
                    } else {
                        $composer2 = $composer3;
                        $composer2.startReplaceGroup(276916918);
                        $composer2.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    itemSpacing3 = itemSpacing4;
                    flingBehavior3 = flingBehavior4;
                    userScrollEnabled2 = userScrollEnabled3;
                }
                Object value$iv3 = new CarouselPageSize(function2, beforeContentPadding, afterContentPadding);
                $composer3.updateRememberedValue(value$iv3);
                it$iv = value$iv3;
                pageSize = (CarouselPageSize) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                snapPosition = KeylineSnapPositionKt.KeylineSnapPosition(pageSize);
                if (orientation == Orientation.Horizontal) {
                    $composer3.startReplaceGroup(273387289);
                    ComposerKt.sourceInformation($composer3, "399@18566L24,400@18602L1024,385@17885L1741");
                    CarouselPagerState pagerState3 = carouselState.getPagerState();
                    PaddingValues paddingValuesM816PaddingValuesa9UjIt4$default3 = PaddingKt.m816PaddingValuesa9UjIt4$default(0.0f, contentPadding.getTop(), 0.0f, contentPadding.getBottom(), 5, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 285933988, "CC(remember):Carousel.kt#9igjgp");
                    it$iv3 = $composer3.rememberedValue();
                    if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv4 = new Function1() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda12
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return CarouselKt.Carousel_cJHQLPU$lambda$16$lambda$15((SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv4);
                        it$iv3 = value$iv4;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    PagerKt.m1073HorizontalPager8jOkeI(pagerState3, SemanticsModifierKt.semantics$default(modifier2, false, (Function1) it$iv3, 1, null), paddingValuesM816PaddingValuesa9UjIt4$default3, pageSize, i2, itemSpacing4, null, flingBehavior4, userScrollEnabled3, false, null, null, snapPosition, null, ComposableLambdaKt.rememberComposableLambda(-1817116752, true, new CarouselKt$Carousel$2(carouselState, pageSize, function4), $composer3, 54), $composer3, ($dirty2 & 57344) | (($dirty2 >> 3) & 458752) | ($dirty2 & 29360128) | ($dirty2 & 234881024), 24576, 11840);
                    $composer3.endReplaceGroup();
                    $composer2 = $composer3;
                } else if (orientation == Orientation.Vertical) {
                    $composer3.startReplaceGroup(275178469);
                    ComposerKt.sourceInformation($composer3, "434@19985L7,435@20077L7,443@20422L24,444@20458L1024,429@19689L1793");
                    CarouselPagerState pagerState4 = carouselState.getPagerState();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume3 = $composer3.consume(localLayoutDirection3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    float fCalculateStartPadding2 = PaddingKt.calculateStartPadding(contentPadding, (LayoutDirection) objConsume3);
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection4 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume4 = $composer3.consume(localLayoutDirection4);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    PaddingValues paddingValuesM816PaddingValuesa9UjIt4$default4 = PaddingKt.m816PaddingValuesa9UjIt4$default(fCalculateStartPadding2, 0.0f, PaddingKt.calculateEndPadding(contentPadding, (LayoutDirection) objConsume4), 0.0f, 10, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 285993380, "CC(remember):Carousel.kt#9igjgp");
                    it$iv2 = $composer3.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv5 = new Function1() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda13
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return CarouselKt.Carousel_cJHQLPU$lambda$18$lambda$17((SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv5);
                        it$iv2 = value$iv5;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    PagerKt.m1075VerticalPager8jOkeI(pagerState4, SemanticsModifierKt.semantics$default(modifier2, false, (Function1) it$iv2, 1, null), paddingValuesM816PaddingValuesa9UjIt4$default4, pageSize, maxNonFocalVisibleItemCount, itemSpacing4, null, flingBehavior4, userScrollEnabled3, false, null, null, snapPosition, null, ComposableLambdaKt.rememberComposableLambda(-1042567175, true, new CarouselKt$Carousel$4(carouselState, pageSize, function4), $composer3, 54), $composer3, ($dirty2 & 57344) | (($dirty2 >> 3) & 458752) | ($dirty2 & 29360128) | ($dirty2 & 234881024), 24576, 11840);
                    $composer2 = $composer3;
                    $composer2.endReplaceGroup();
                } else {
                    $composer2 = $composer3;
                    $composer2.startReplaceGroup(276916918);
                    $composer2.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                itemSpacing3 = itemSpacing4;
                flingBehavior3 = flingBehavior4;
                userScrollEnabled2 = userScrollEnabled3;
            } else {
                carouselState = state;
                $composer2 = $composer3;
                $composer2.skipToGroupEnd();
                userScrollEnabled2 = userScrollEnabled;
                flingBehavior3 = flingBehavior2;
                itemSpacing3 = itemSpacing2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier4 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return CarouselKt.Carousel_cJHQLPU$lambda$19(carouselState, orientation, function2, contentPadding, maxNonFocalVisibleItemCount, modifier4, itemSpacing3, flingBehavior3, userScrollEnabled2, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 = $dirty4 | C.ENCODING_PCM_32BIT;
        $dirty4 = i3;
        if (($dirty4 & 306783379) != 306783378) {
            z = true;
        } else {
            z = false;
        }
        if ($composer3.shouldExecute(z, $dirty4 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "371@17296L41");
            if (($changed & 1) != 0) {
                if (i4 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i5 != 0) {
                    itemSpacing2 = Dp.m7905constructorimpl(0);
                }
                $this$dp$iv = i & 128;
                if ($this$dp$iv != 0) {
                    int i9 = ($dirty4 & 14) | 384;
                    z2 = true;
                    carouselState = state;
                    $dirty4 &= -29360129;
                    flingBehavior2 = CarouselDefaults.INSTANCE.singleAdvanceFlingBehavior(carouselState, null, $composer3, i9, 2);
                } else {
                    carouselState = state;
                    z2 = true;
                }
                if (i7 != 0) {
                    userScrollEnabled3 = true;
                    flingBehavior4 = flingBehavior2;
                    modifier2 = modifier3;
                    $dirty = $dirty4;
                    itemSpacing4 = itemSpacing2;
                } else {
                    userScrollEnabled3 = userScrollEnabled;
                    flingBehavior4 = flingBehavior2;
                    modifier2 = modifier3;
                    $dirty = $dirty4;
                    itemSpacing4 = itemSpacing2;
                }
            } else {
                if (i4 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i5 != 0) {
                    itemSpacing2 = Dp.m7905constructorimpl(0);
                }
                $this$dp$iv = i & 128;
                if ($this$dp$iv != 0) {
                    int i10 = ($dirty4 & 14) | 384;
                    z2 = true;
                    carouselState = state;
                    $dirty4 &= -29360129;
                    flingBehavior2 = CarouselDefaults.INSTANCE.singleAdvanceFlingBehavior(carouselState, null, $composer3, i10, 2);
                } else {
                    carouselState = state;
                    z2 = true;
                }
                if (i7 != 0) {
                    userScrollEnabled3 = true;
                    flingBehavior4 = flingBehavior2;
                    modifier2 = modifier3;
                    $dirty = $dirty4;
                    itemSpacing4 = itemSpacing2;
                } else {
                    userScrollEnabled3 = userScrollEnabled;
                    flingBehavior4 = flingBehavior2;
                    modifier2 = modifier3;
                    $dirty = $dirty4;
                    itemSpacing4 = itemSpacing2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2077912500, $dirty, -1, "androidx.compose.material3.carousel.Carousel (Carousel.kt:374)");
            }
            float beforeContentPadding2 = calculateBeforeContentPadding(contentPadding, orientation, $composer3, (($dirty >> 9) & 14) | ($dirty & 112));
            float afterContentPadding2 = calculateAfterContentPadding(contentPadding, orientation, $composer3, (($dirty >> 9) & 14) | ($dirty & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, 285904898, "CC(remember):Carousel.kt#9igjgp");
            if (($dirty & 896) == 256) {
                invalid$iv = z2;
            } else {
                invalid$iv = false;
            }
            it$iv = $composer3.rememberedValue();
            if (invalid$iv) {
                $dirty2 = $dirty;
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                }
                pageSize = (CarouselPageSize) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                snapPosition = KeylineSnapPositionKt.KeylineSnapPosition(pageSize);
                if (orientation == Orientation.Horizontal) {
                    $composer3.startReplaceGroup(273387289);
                    ComposerKt.sourceInformation($composer3, "399@18566L24,400@18602L1024,385@17885L1741");
                    CarouselPagerState pagerState5 = carouselState.getPagerState();
                    PaddingValues paddingValuesM816PaddingValuesa9UjIt4$default5 = PaddingKt.m816PaddingValuesa9UjIt4$default(0.0f, contentPadding.getTop(), 0.0f, contentPadding.getBottom(), 5, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 285933988, "CC(remember):Carousel.kt#9igjgp");
                    it$iv3 = $composer3.rememberedValue();
                    if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv6 = new Function1() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda12
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return CarouselKt.Carousel_cJHQLPU$lambda$16$lambda$15((SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv6);
                        it$iv3 = value$iv6;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    PagerKt.m1073HorizontalPager8jOkeI(pagerState5, SemanticsModifierKt.semantics$default(modifier2, false, (Function1) it$iv3, 1, null), paddingValuesM816PaddingValuesa9UjIt4$default5, pageSize, i2, itemSpacing4, null, flingBehavior4, userScrollEnabled3, false, null, null, snapPosition, null, ComposableLambdaKt.rememberComposableLambda(-1817116752, true, new CarouselKt$Carousel$2(carouselState, pageSize, function4), $composer3, 54), $composer3, ($dirty2 & 57344) | (($dirty2 >> 3) & 458752) | ($dirty2 & 29360128) | ($dirty2 & 234881024), 24576, 11840);
                    $composer3.endReplaceGroup();
                    $composer2 = $composer3;
                } else if (orientation == Orientation.Vertical) {
                    $composer3.startReplaceGroup(275178469);
                    ComposerKt.sourceInformation($composer3, "434@19985L7,435@20077L7,443@20422L24,444@20458L1024,429@19689L1793");
                    CarouselPagerState pagerState6 = carouselState.getPagerState();
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection5 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume5 = $composer3.consume(localLayoutDirection5);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    float fCalculateStartPadding3 = PaddingKt.calculateStartPadding(contentPadding, (LayoutDirection) objConsume5);
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection6 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume6 = $composer3.consume(localLayoutDirection6);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    PaddingValues paddingValuesM816PaddingValuesa9UjIt4$default6 = PaddingKt.m816PaddingValuesa9UjIt4$default(fCalculateStartPadding3, 0.0f, PaddingKt.calculateEndPadding(contentPadding, (LayoutDirection) objConsume6), 0.0f, 10, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 285993380, "CC(remember):Carousel.kt#9igjgp");
                    it$iv2 = $composer3.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv7 = new Function1() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda13
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return CarouselKt.Carousel_cJHQLPU$lambda$18$lambda$17((SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv7);
                        it$iv2 = value$iv7;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    PagerKt.m1075VerticalPager8jOkeI(pagerState6, SemanticsModifierKt.semantics$default(modifier2, false, (Function1) it$iv2, 1, null), paddingValuesM816PaddingValuesa9UjIt4$default6, pageSize, maxNonFocalVisibleItemCount, itemSpacing4, null, flingBehavior4, userScrollEnabled3, false, null, null, snapPosition, null, ComposableLambdaKt.rememberComposableLambda(-1042567175, true, new CarouselKt$Carousel$4(carouselState, pageSize, function4), $composer3, 54), $composer3, ($dirty2 & 57344) | (($dirty2 >> 3) & 458752) | ($dirty2 & 29360128) | ($dirty2 & 234881024), 24576, 11840);
                    $composer2 = $composer3;
                    $composer2.endReplaceGroup();
                } else {
                    $composer2 = $composer3;
                    $composer2.startReplaceGroup(276916918);
                    $composer2.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                itemSpacing3 = itemSpacing4;
                flingBehavior3 = flingBehavior4;
                userScrollEnabled2 = userScrollEnabled3;
            } else {
                $dirty2 = $dirty;
            }
            Object value$iv8 = new CarouselPageSize(function2, beforeContentPadding2, afterContentPadding2);
            $composer3.updateRememberedValue(value$iv8);
            it$iv = value$iv8;
            pageSize = (CarouselPageSize) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            snapPosition = KeylineSnapPositionKt.KeylineSnapPosition(pageSize);
            if (orientation == Orientation.Horizontal) {
                $composer3.startReplaceGroup(273387289);
                ComposerKt.sourceInformation($composer3, "399@18566L24,400@18602L1024,385@17885L1741");
                CarouselPagerState pagerState7 = carouselState.getPagerState();
                PaddingValues paddingValuesM816PaddingValuesa9UjIt4$default7 = PaddingKt.m816PaddingValuesa9UjIt4$default(0.0f, contentPadding.getTop(), 0.0f, contentPadding.getBottom(), 5, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 285933988, "CC(remember):Carousel.kt#9igjgp");
                it$iv3 = $composer3.rememberedValue();
                if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv9 = new Function1() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return CarouselKt.Carousel_cJHQLPU$lambda$16$lambda$15((SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv9);
                    it$iv3 = value$iv9;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                PagerKt.m1073HorizontalPager8jOkeI(pagerState7, SemanticsModifierKt.semantics$default(modifier2, false, (Function1) it$iv3, 1, null), paddingValuesM816PaddingValuesa9UjIt4$default7, pageSize, i2, itemSpacing4, null, flingBehavior4, userScrollEnabled3, false, null, null, snapPosition, null, ComposableLambdaKt.rememberComposableLambda(-1817116752, true, new CarouselKt$Carousel$2(carouselState, pageSize, function4), $composer3, 54), $composer3, ($dirty2 & 57344) | (($dirty2 >> 3) & 458752) | ($dirty2 & 29360128) | ($dirty2 & 234881024), 24576, 11840);
                $composer3.endReplaceGroup();
                $composer2 = $composer3;
            } else if (orientation == Orientation.Vertical) {
                $composer3.startReplaceGroup(275178469);
                ComposerKt.sourceInformation($composer3, "434@19985L7,435@20077L7,443@20422L24,444@20458L1024,429@19689L1793");
                CarouselPagerState pagerState8 = carouselState.getPagerState();
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection7 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume7 = $composer3.consume(localLayoutDirection7);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                float fCalculateStartPadding4 = PaddingKt.calculateStartPadding(contentPadding, (LayoutDirection) objConsume7);
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection8 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume8 = $composer3.consume(localLayoutDirection8);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                PaddingValues paddingValuesM816PaddingValuesa9UjIt4$default8 = PaddingKt.m816PaddingValuesa9UjIt4$default(fCalculateStartPadding4, 0.0f, PaddingKt.calculateEndPadding(contentPadding, (LayoutDirection) objConsume8), 0.0f, 10, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 285993380, "CC(remember):Carousel.kt#9igjgp");
                it$iv2 = $composer3.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv10 = new Function1() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return CarouselKt.Carousel_cJHQLPU$lambda$18$lambda$17((SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv10);
                    it$iv2 = value$iv10;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                PagerKt.m1075VerticalPager8jOkeI(pagerState8, SemanticsModifierKt.semantics$default(modifier2, false, (Function1) it$iv2, 1, null), paddingValuesM816PaddingValuesa9UjIt4$default8, pageSize, maxNonFocalVisibleItemCount, itemSpacing4, null, flingBehavior4, userScrollEnabled3, false, null, null, snapPosition, null, ComposableLambdaKt.rememberComposableLambda(-1042567175, true, new CarouselKt$Carousel$4(carouselState, pageSize, function4), $composer3, 54), $composer3, ($dirty2 & 57344) | (($dirty2 >> 3) & 458752) | ($dirty2 & 29360128) | ($dirty2 & 234881024), 24576, 11840);
                $composer2 = $composer3;
                $composer2.endReplaceGroup();
            } else {
                $composer2 = $composer3;
                $composer2.startReplaceGroup(276916918);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            itemSpacing3 = itemSpacing4;
            flingBehavior3 = flingBehavior4;
            userScrollEnabled2 = userScrollEnabled3;
        } else {
            carouselState = state;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            userScrollEnabled2 = userScrollEnabled;
            flingBehavior3 = flingBehavior2;
            itemSpacing3 = itemSpacing2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CarouselKt.Carousel_cJHQLPU$lambda$19(carouselState, orientation, function2, contentPadding, maxNonFocalVisibleItemCount, modifier5, itemSpacing3, flingBehavior3, userScrollEnabled2, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Carousel_cJHQLPU$lambda$16$lambda$15(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.m7118setRolekuIjeqM($this$semantics, Role.INSTANCE.m7100getCarouselo7Vup1c());
        return Unit.INSTANCE;
    }

    static final Unit Carousel_cJHQLPU$lambda$18$lambda$17(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.m7118setRolekuIjeqM($this$semantics, Role.INSTANCE.m7100getCarouselo7Vup1c());
        return Unit.INSTANCE;
    }

    private static final float calculateBeforeContentPadding(PaddingValues $this$calculateBeforeContentPadding, Orientation orientation, Composer $composer, int $changed) {
        float dpValue;
        ComposerKt.sourceInformationMarkerStart($composer, 1896839347, "C(calculateBeforeContentPadding)N(orientation)484@21819L7:Carousel.kt#dcf9yb");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1896839347, $changed, -1, "androidx.compose.material3.carousel.calculateBeforeContentPadding (Carousel.kt:476)");
        }
        if (orientation == Orientation.Vertical) {
            $composer.startReplaceGroup(-143556958);
            $composer.endReplaceGroup();
            dpValue = $this$calculateBeforeContentPadding.getTop();
        } else {
            $composer.startReplaceGroup(-143505436);
            ComposerKt.sourceInformation($composer, "481@21770L7");
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer);
            dpValue = PaddingKt.calculateStartPadding($this$calculateBeforeContentPadding, (LayoutDirection) objConsume);
            $composer.endReplaceGroup();
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume2 = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Density $this$calculateBeforeContentPadding_u24lambda_u2420 = (Density) objConsume2;
        float fMo406toPx0680j_4 = $this$calculateBeforeContentPadding_u24lambda_u2420.mo406toPx0680j_4(dpValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return fMo406toPx0680j_4;
    }

    private static final float calculateAfterContentPadding(PaddingValues $this$calculateAfterContentPadding, Orientation orientation, Composer $composer, int $changed) {
        float dpValue;
        ComposerKt.sourceInformationMarkerStart($composer, 1018496720, "C(calculateAfterContentPadding)N(orientation)496@22177L7:Carousel.kt#dcf9yb");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1018496720, $changed, -1, "androidx.compose.material3.carousel.calculateAfterContentPadding (Carousel.kt:488)");
        }
        if (orientation == Orientation.Vertical) {
            $composer.startReplaceGroup(-1907991582);
            $composer.endReplaceGroup();
            dpValue = $this$calculateAfterContentPadding.getBottom();
        } else {
            $composer.startReplaceGroup(-1907937239);
            ComposerKt.sourceInformation($composer, "493@22128L7");
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer);
            dpValue = PaddingKt.calculateEndPadding($this$calculateAfterContentPadding, (LayoutDirection) objConsume);
            $composer.endReplaceGroup();
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume2 = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Density $this$calculateAfterContentPadding_u24lambda_u2421 = (Density) objConsume2;
        float fMo406toPx0680j_4 = $this$calculateAfterContentPadding_u24lambda_u2421.mo406toPx0680j_4(dpValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return fMo406toPx0680j_4;
    }

    public static final Modifier carouselItem(Modifier $this$carouselItem, final int index, final CarouselState state, final Function0<Strategy> function0, final CarouselItemDrawInfoImpl carouselItemDrawInfo, final Shape clipShape) {
        return LayoutModifierKt.layout($this$carouselItem, new Function3() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CarouselKt.carouselItem$lambda$26(function0, state, index, carouselItemDrawInfo, clipShape, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
            }
        });
    }

    static final MeasureResult carouselItem$lambda$26(Function0 $strategy, final CarouselState $state, final int $index, final CarouselItemDrawInfoImpl $carouselItemDrawInfo, final Shape $clipShape, MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        long jM7847copyZbe2FdA;
        final Strategy strategyResult = (Strategy) $strategy.invoke();
        if (!strategyResult.getIsValid()) {
            return MeasureScope.layout$default($this$layout, 0, 0, null, new Function1() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Unit.INSTANCE;
                }
            }, 4, null);
        }
        final boolean isVertical = $state.getPagerState().getLayoutInfo().getOrientation() == Orientation.Vertical;
        final boolean isRtl = $this$layout.getLayoutDirection() == LayoutDirection.Rtl;
        float mainAxisSize = strategyResult.getItemMainAxisSize();
        if (isVertical) {
            jM7847copyZbe2FdA = Constraints.m7847copyZbe2FdA(constraints.getValue(), Constraints.m7860getMinWidthimpl(constraints.getValue()), Constraints.m7858getMaxWidthimpl(constraints.getValue()), MathKt.roundToInt(mainAxisSize), MathKt.roundToInt(mainAxisSize));
        } else {
            jM7847copyZbe2FdA = Constraints.m7847copyZbe2FdA(constraints.getValue(), MathKt.roundToInt(mainAxisSize), MathKt.roundToInt(mainAxisSize), Constraints.m7859getMinHeightimpl(constraints.getValue()), Constraints.m7857getMaxHeightimpl(constraints.getValue()));
        }
        long itemConstraints = jM7847copyZbe2FdA;
        final Placeable placeable = measurable.mo6541measureBRTryo0(itemConstraints);
        final float itemZIndex = 1.0f;
        if ($index != $state.getPagerState().getCurrentPage()) {
            if ($index == 0) {
                itemZIndex = 0.0f;
            } else {
                itemZIndex = 1.0f / $index;
            }
        }
        return MeasureScope.layout$default($this$layout, placeable.getWidth(), placeable.getHeight(), null, new Function1() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CarouselKt.carouselItem$lambda$26$lambda$25(placeable, itemZIndex, $state, strategyResult, $index, isVertical, $carouselItemDrawInfo, $clipShape, isRtl, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit carouselItem$lambda$26$lambda$25(Placeable $placeable, float $itemZIndex, final CarouselState $state, final Strategy $strategyResult, final int $index, final boolean $isVertical, final CarouselItemDrawInfoImpl $carouselItemDrawInfo, final Shape $clipShape, final boolean $isRtl, Placeable.PlacementScope $this$layout) {
        $this$layout.placeWithLayer($placeable, 0, 0, $itemZIndex, new Function1() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CarouselKt.carouselItem$lambda$26$lambda$25$lambda$24($state, $strategyResult, $index, $isVertical, $carouselItemDrawInfo, $clipShape, $isRtl, (GraphicsLayerScope) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit carouselItem$lambda$26$lambda$25$lambda$24(CarouselState $state, Strategy $strategyResult, int $index, boolean $isVertical, CarouselItemDrawInfoImpl $carouselItemDrawInfo, Shape $clipShape, boolean $isRtl, GraphicsLayerScope $this$placeWithLayer) {
        float itemMainAxisSize;
        float f;
        float centerY;
        char c;
        float size;
        float halfMaskHeight;
        float scrollOffset = calculateCurrentScrollOffset($state, $strategyResult);
        float maxScrollOffset = calculateMaxScrollOffset($state, $strategyResult);
        KeylineList keylines = Strategy.getKeylineListForScrollOffset$material3$default($strategyResult, scrollOffset, maxScrollOffset, false, 4, null);
        KeylineList roundedKeylines = $strategyResult.getKeylineListForScrollOffset$material3(scrollOffset, maxScrollOffset, true);
        float itemSizeWithSpacing = $strategyResult.getItemMainAxisSize() + $strategyResult.getItemSpacing();
        float unadjustedCenter = (($index * itemSizeWithSpacing) + ($strategyResult.getItemMainAxisSize() / 2.0f)) - scrollOffset;
        Keyline keylineBefore = keylines.getKeylineBefore(unadjustedCenter);
        Keyline keylineAfter = keylines.getKeylineAfter(unadjustedCenter);
        float progress = getProgress(keylineBefore, keylineAfter, unadjustedCenter);
        Keyline interpolatedKeyline = KeylineListKt.lerp(keylineBefore, keylineAfter, progress);
        boolean isOutOfKeylineBounds = Intrinsics.areEqual(keylineBefore, keylineAfter);
        if ($isVertical) {
            long arg0$iv = $this$placeWithLayer.getSize();
            int bits$iv$iv$iv = (int) (arg0$iv & 4294967295L);
            itemMainAxisSize = Float.intBitsToFloat(bits$iv$iv$iv);
        } else {
            itemMainAxisSize = $strategyResult.getItemMainAxisSize();
        }
        float f2 = itemMainAxisSize / 2.0f;
        if ($isVertical) {
            centerY = $strategyResult.getItemMainAxisSize() / 2.0f;
            f = 2.0f;
        } else {
            long arg0$iv2 = $this$placeWithLayer.getSize();
            f = 2.0f;
            int bits$iv$iv$iv2 = (int) (arg0$iv2 & 4294967295L);
            centerY = Float.intBitsToFloat(bits$iv$iv$iv2) / 2.0f;
        }
        if ($isVertical) {
            long arg0$iv3 = $this$placeWithLayer.getSize();
            c = ' ';
            int bits$iv$iv$iv3 = (int) (arg0$iv3 >> 32);
            size = Float.intBitsToFloat(bits$iv$iv$iv3);
        } else {
            c = ' ';
            size = interpolatedKeyline.getSize();
        }
        float halfMaskWidth = size / f;
        if ($isVertical) {
            halfMaskHeight = interpolatedKeyline.getSize() / f;
        } else {
            long arg0$iv4 = $this$placeWithLayer.getSize();
            int bits$iv$iv$iv4 = (int) (arg0$iv4 & 4294967295L);
            halfMaskHeight = Float.intBitsToFloat(bits$iv$iv$iv4) / f;
        }
        Rect maskRect = new Rect(f2 - halfMaskWidth, centerY - halfMaskHeight, f2 + halfMaskWidth, centerY + halfMaskHeight);
        $carouselItemDrawInfo.setSizeState(interpolatedKeyline.getSize());
        KeylineList $this$minBy$iv = roundedKeylines;
        int $i$f$minByOrThrow = 0;
        Iterator<Keyline> it = $this$minBy$iv.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        Object minElem$iv = it.next();
        if (it.hasNext()) {
            Keyline it2 = (Keyline) minElem$iv;
            float minValue$iv = it2.getSize();
            while (true) {
                Object e$iv = it.next();
                Keyline it3 = (Keyline) e$iv;
                float size2 = it3.getSize();
                int $i$f$minByOrThrow2 = $i$f$minByOrThrow;
                if (Float.compare(minValue$iv, size2) > 0) {
                    minElem$iv = e$iv;
                    minValue$iv = size2;
                }
                if (!it.hasNext()) {
                    break;
                }
                $i$f$minByOrThrow = $i$f$minByOrThrow2;
            }
        }
        $carouselItemDrawInfo.setMinSizeState(((Keyline) minElem$iv).getSize());
        $carouselItemDrawInfo.setMaxSizeState(roundedKeylines.getFirstFocal().getSize());
        $carouselItemDrawInfo.setMaskRectState(maskRect);
        long arg0$iv5 = $this$placeWithLayer.getSize();
        int bits$iv$iv$iv5 = (int) (arg0$iv5 >> c);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv5);
        long arg0$iv6 = $this$placeWithLayer.getSize();
        int bits$iv$iv$iv6 = (int) (arg0$iv6 & 4294967295L);
        $this$placeWithLayer.setClip(!Intrinsics.areEqual(maskRect, new Rect(0.0f, 0.0f, fIntBitsToFloat, Float.intBitsToFloat(bits$iv$iv$iv6))));
        $this$placeWithLayer.setShape($clipShape);
        float translation = interpolatedKeyline.getOffset() - unadjustedCenter;
        if (isOutOfKeylineBounds) {
            float outOfBoundsOffset = (unadjustedCenter - interpolatedKeyline.getUnadjustedOffset()) / interpolatedKeyline.getSize();
            translation += outOfBoundsOffset;
        }
        if ($isVertical) {
            $this$placeWithLayer.setTranslationY(translation);
        } else {
            $this$placeWithLayer.setTranslationX($isRtl ? -translation : translation);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: drawDebugLines-1Yev-eo$default, reason: not valid java name */
    static /* synthetic */ Modifier m3337drawDebugLines1Yeveo$default(Modifier modifier, CarouselState carouselState, CarouselPageSize carouselPageSize, long j, float f, int i, Object obj) {
        long jM5130getMagenta0d7_KjU;
        float fM7905constructorimpl;
        if ((i & 4) == 0) {
            jM5130getMagenta0d7_KjU = j;
        } else {
            jM5130getMagenta0d7_KjU = Color.INSTANCE.m5130getMagenta0d7_KjU();
        }
        if ((i & 8) == 0) {
            fM7905constructorimpl = f;
        } else {
            fM7905constructorimpl = Dp.m7905constructorimpl(4);
        }
        return m3336drawDebugLines1Yeveo(modifier, carouselState, carouselPageSize, jM5130getMagenta0d7_KjU, fM7905constructorimpl);
    }

    /* JADX INFO: renamed from: drawDebugLines-1Yev-eo, reason: not valid java name */
    private static final Modifier m3336drawDebugLines1Yeveo(Modifier $this$drawDebugLines_u2d1Yev_u2deo, final CarouselState state, final CarouselPageSize pageSize, final long strokeColor, final float strokeWidth) {
        return DrawModifierKt.drawWithContent($this$drawDebugLines_u2d1Yev_u2deo, new Function1() { // from class: androidx.compose.material3.carousel.CarouselKt$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CarouselKt.drawDebugLines_1Yev_eo$lambda$28(pageSize, state, strokeWidth, strokeColor, (ContentDrawScope) obj);
            }
        });
    }

    static final Unit drawDebugLines_1Yev_eo$lambda$28(CarouselPageSize $pageSize, CarouselState $state, float $strokeWidth, long $strokeColor, ContentDrawScope $this$drawWithContent) {
        ContentDrawScope contentDrawScope = $this$drawWithContent;
        contentDrawScope.drawContent();
        Strategy strategyResult = $pageSize.getStrategy();
        float scrollOffset = calculateCurrentScrollOffset($state, strategyResult);
        float maxScrollOffset = calculateMaxScrollOffset($state, strategyResult);
        Iterable keylines = Strategy.getKeylineListForScrollOffset$material3$default(strategyResult, scrollOffset, maxScrollOffset, false, 4, null);
        float strokeWidthPx = contentDrawScope.mo406toPx0680j_4($strokeWidth);
        Iterable $this$forEach$iv = keylines;
        for (Object element$iv : $this$forEach$iv) {
            Keyline it = (Keyline) element$iv;
            float x$iv = it.getOffset();
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(0.0f);
            long jM4844constructorimpl = Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
            float x$iv2 = it.getOffset();
            long v1$iv$iv2 = Float.floatToRawIntBits(x$iv2);
            long v2$iv$iv2 = Float.floatToRawIntBits(100.0f);
            DrawScope.m5657drawLineNGM6Ib0$default(contentDrawScope, $strokeColor, jM4844constructorimpl, Offset.m4844constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L)), strokeWidthPx, 0, null, 0.0f, null, 0, 496, null);
            contentDrawScope = $this$drawWithContent;
        }
        return Unit.INSTANCE;
    }

    public static final float calculateCurrentScrollOffset(CarouselState state, Strategy strategy) {
        float itemSizeWithSpacing = strategy.getItemMainAxisSize() + strategy.getItemSpacing();
        float currentItemScrollOffset = (state.getPagerState().getCurrentPage() * itemSizeWithSpacing) + (state.getPagerState().getCurrentPageOffsetFraction() * itemSizeWithSpacing);
        return currentItemScrollOffset - KeylineSnapPositionKt.getSnapPositionOffset(strategy, state.getPagerState().getCurrentPage(), state.getPagerState().getPageCount());
    }

    public static final float calculateMaxScrollOffset(CarouselState state, Strategy strategy) {
        float itemCount = state.getPagerState().getPageCount();
        float maxScrollPossible = (strategy.getItemMainAxisSize() * itemCount) + (strategy.getItemSpacing() * (itemCount - 1.0f));
        return RangesKt.coerceAtLeast(maxScrollPossible - strategy.getAvailableSpace(), 0.0f);
    }

    private static final float getProgress(Keyline before, Keyline after, float unadjustedOffset) {
        if (Intrinsics.areEqual(before, after)) {
            return 1.0f;
        }
        float total = after.getUnadjustedOffset() - before.getUnadjustedOffset();
        return (unadjustedOffset - before.getUnadjustedOffset()) / total;
    }
}
