package androidx.compose.material3;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.material3.internal.MappedInteractionSource;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: NavigationItem.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\b\u001aµ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00032\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0004\b\u001d\u0010\u001e\u001aÕ\u0001\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\r2\u0006\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010)\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00032\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0004\b*\u0010+\u001a\u008d\u0001\u0010,\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u0010\n\u001a\u00020\u000b2\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00072\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\u00052\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rH\u0003¢\u0006\u0004\b2\u00103\u001a³\u0001\u00104\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u0010\n\u001a\u00020\u000b2\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\f\u00105\u001a\b\u0012\u0004\u0012\u0002010\u00052\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\r2\u0006\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010)\u001a\u00020\rH\u0003¢\u0006\u0004\b6\u00107\u001a3\u00108\u001a\u000209*\u00020:2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020<2\u0006\u0010?\u001a\u00020@H\u0002¢\u0006\u0004\bA\u0010B\u001aS\u0010C\u001a\u000209*\u00020:2\u0006\u0010D\u001a\u00020<2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020<2\u0006\u0010?\u001a\u00020@2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rH\u0002¢\u0006\u0004\bE\u0010F\u001aC\u0010G\u001a\u000209*\u00020:2\u0006\u0010D\u001a\u00020<2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020<2\u0006\u0010?\u001a\u00020@2\u0006\u0010\u0011\u001a\u00020\rH\u0002¢\u0006\u0004\bH\u0010I\u001a\u0091\u0001\u0010J\u001a\u000209*\u00020:2\u0006\u0010\u0019\u001a\u00020\u001a2\f\u00105\u001a\b\u0012\u0004\u0012\u0002010\u00052\u0006\u0010D\u001a\u00020<2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020<2\u0006\u0010 \u001a\u00020K2\u0006\u0010?\u001a\u00020@2\u0006\u0010%\u001a\u00020\r2\u0006\u0010$\u001a\u00020\r2\u0006\u0010#\u001a\u00020\r2\u0006\u0010&\u001a\u00020\r2\u0006\u0010'\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010)\u001a\u00020\rH\u0002¢\u0006\u0004\bL\u0010M\u001a@\u0010N\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00032\u0011\u0010O\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0007H\u0003¢\u0006\u0002\u0010P\u001a\u001b\u0010Q\u001a\b\u0012\u0004\u0012\u0002010R2\u0006\u0010\u0002\u001a\u00020\u0003H\u0003¢\u0006\u0002\u0010S\u001a\u001d\u0010T\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020-2\u0006\u0010\n\u001a\u00020\u000bH\u0003¢\u0006\u0002\u0010U\u001a-\u0010V\u001a\u00020\u00012\u0006\u0010.\u001a\u00020/2\u0006\u0010\n\u001a\u00020\u000b2\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\u0005H\u0003¢\u0006\u0004\bW\u0010X\"\u000e\u0010Y\u001a\u00020ZX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010[\u001a\u00020ZX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\\\u001a\u00020ZX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010]\u001a\u00020ZX\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010^\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010_¨\u0006`²\u0006\n\u0010a\u001a\u00020KX\u008a\u008e\u0002²\u0006\n\u0010a\u001a\u00020KX\u008a\u008e\u0002²\u0006\n\u00105\u001a\u000201X\u008a\u0084\u0002²\u0006\n\u0010b\u001a\u00020\tX\u008a\u0084\u0002"}, d2 = {"NavigationItem", "", "selected", "", "onClick", "Lkotlin/Function0;", NavigationItemKt.IconLayoutIdTag, "Landroidx/compose/runtime/Composable;", "labelTextStyle", "Landroidx/compose/ui/text/TextStyle;", "indicatorShape", "Landroidx/compose/ui/graphics/Shape;", "indicatorWidth", "Landroidx/compose/ui/unit/Dp;", "indicatorHorizontalPadding", "indicatorVerticalPadding", "indicatorToLabelVerticalPadding", "startIconToLabelHorizontalPadding", "topIconItemVerticalPadding", "colors", "Landroidx/compose/material3/NavigationItemColors;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "label", "iconPosition", "Landroidx/compose/material3/NavigationItemIconPosition;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "NavigationItem-8Df7sds", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/graphics/Shape;FFFFFFLandroidx/compose/material3/NavigationItemColors;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "AnimatedNavigationItem", "topIconIndicatorWidth", "topIconLabelTextStyle", "startIconLabelTextStyle", "topIconIndicatorHorizontalPadding", "topIconIndicatorVerticalPadding", "topIconIndicatorToLabelVerticalPadding", "startIconIndicatorHorizontalPadding", "startIconIndicatorVerticalPadding", "noLabelIndicatorPadding", "itemHorizontalPadding", "AnimatedNavigationItem-DQd_Gtc", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;FLandroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;FFFFFFFFLandroidx/compose/material3/NavigationItemColors;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "NavigationItemLayout", "Landroidx/compose/foundation/interaction/InteractionSource;", "indicatorColor", "Landroidx/compose/ui/graphics/Color;", "indicatorAnimationProgress", "", "NavigationItemLayout-KmRX-Dg", "(Landroidx/compose/foundation/interaction/InteractionSource;JLandroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function2;ILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;FFFFFLandroidx/compose/runtime/Composer;II)V", "AnimatedNavigationItemLayout", "iconPositionProgress", "AnimatedNavigationItemLayout-he0WsC4", "(Landroidx/compose/foundation/interaction/InteractionSource;JLandroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;ILkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;FFFFFFFFLandroidx/compose/runtime/Composer;II)V", "placeIcon", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "iconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "indicatorRipplePlaceable", "indicatorPlaceable", "constraints", "Landroidx/compose/ui/unit/Constraints;", "placeIcon-X9ElhV4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;J)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndTopIcon", "labelPlaceable", "placeLabelAndTopIcon-qoqLrGI", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JFFF)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndStartIcon", "placeLabelAndStartIcon-nru01g4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JF)Landroidx/compose/ui/layout/MeasureResult;", "placeAnimatedLabelAndIcon", "", "placeAnimatedLabelAndIcon-2QYhCQ8", "(Landroidx/compose/ui/layout/MeasureScope;ILkotlin/jvm/functions/Function0;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;IJFFFFFFF)Landroidx/compose/ui/layout/MeasureResult;", "StyledLabel", UriUtil.LOCAL_CONTENT_SCHEME, "(ZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/material3/NavigationItemColors;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "animateIndicatorProgressAsState", "Landroidx/compose/runtime/State;", "(ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "IndicatorRipple", "(Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)V", "Indicator", "Indicator-3J-VO9M", "(JLandroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "IndicatorRippleLayoutIdTag", "", "IndicatorLayoutIdTag", "IconLayoutIdTag", "LabelLayoutIdTag", "IndicatorVerticalOffset", "F", "material3", "itemWidth", "textStyle"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class NavigationItemKt {
    private static final String IconLayoutIdTag = "icon";
    private static final String IndicatorLayoutIdTag = "indicator";
    private static final String IndicatorRippleLayoutIdTag = "indicatorRipple";
    private static final float IndicatorVerticalOffset = Dp.m7905constructorimpl(12);
    private static final String LabelLayoutIdTag = "label";

    static final Unit AnimatedNavigationItemLayout_he0WsC4$lambda$35(InteractionSource interactionSource, long j, Shape shape, Function0 function0, Function2 function2, int i, Function0 function1, Function2 function3, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int i2, int i3, Composer composer, int i4) {
        m2672AnimatedNavigationItemLayouthe0WsC4(interactionSource, j, shape, function0, function2, i, function1, function3, f, f2, f3, f4, f5, f6, f7, f8, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    static final Unit AnimatedNavigationItem_DQd_Gtc$lambda$27(boolean z, Function0 function0, Function2 function2, Shape shape, float f, TextStyle textStyle, TextStyle textStyle2, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, NavigationItemColors navigationItemColors, Modifier modifier, boolean z2, Function2 function3, int i, MutableInteractionSource mutableInteractionSource, int i2, int i3, int i4, Composer composer, int i5) {
        m2671AnimatedNavigationItemDQd_Gtc(z, function0, function2, shape, f, textStyle, textStyle2, f2, f3, f4, f5, f6, f7, f8, f9, navigationItemColors, modifier, z2, function3, i, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), RecomposeScopeImplKt.updateChangedFlags(i4));
        return Unit.INSTANCE;
    }

    static final Unit IndicatorRipple$lambda$42(InteractionSource interactionSource, Shape shape, int i, Composer composer, int i2) {
        IndicatorRipple(interactionSource, shape, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Indicator_3J_VO9M$lambda$45(long j, Shape shape, Function0 function0, int i, Composer composer, int i2) {
        m2673Indicator3JVO9M(j, shape, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit NavigationItemLayout_KmRX_Dg$lambda$31(InteractionSource interactionSource, long j, Shape shape, Function2 function2, int i, Function2 function3, Function0 function0, float f, float f2, float f3, float f4, float f5, int i2, int i3, Composer composer, int i4) {
        m2675NavigationItemLayoutKmRXDg(interactionSource, j, shape, function2, i, function3, function0, f, f2, f3, f4, f5, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    static final Unit NavigationItem_8Df7sds$lambda$10(boolean z, Function0 function0, Function2 function2, TextStyle textStyle, Shape shape, float f, float f2, float f3, float f4, float f5, float f6, NavigationItemColors navigationItemColors, Modifier modifier, boolean z2, Function2 function3, int i, MutableInteractionSource mutableInteractionSource, int i2, int i3, Composer composer, int i4) {
        m2674NavigationItem8Df7sds(z, function0, function2, textStyle, shape, f, f2, f3, f4, f5, f6, navigationItemColors, modifier, z2, function3, i, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    static final Unit StyledLabel$lambda$41(boolean z, TextStyle textStyle, NavigationItemColors navigationItemColors, boolean z2, Function2 function2, int i, Composer composer, int i2) {
        StyledLabel(z, textStyle, navigationItemColors, z2, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: NavigationItem-8Df7sds, reason: not valid java name */
    public static final void m2674NavigationItem8Df7sds(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle labelTextStyle, final Shape indicatorShape, final float indicatorWidth, final float indicatorHorizontalPadding, final float indicatorVerticalPadding, final float indicatorToLabelVerticalPadding, final float startIconToLabelHorizontalPadding, final float topIconItemVerticalPadding, final NavigationItemColors colors, final Modifier modifier, final boolean enabled, final Function2<? super Composer, ? super Integer, Unit> function3, final int iconPosition, final MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1) {
        TextStyle textStyle;
        Composer $composer2;
        Function2 styledLabel;
        Function0<ComposeUiNode> function1;
        Composer $composer3 = $composer.startRestartGroup(2075155418);
        ComposerKt.sourceInformation($composer3, "C(NavigationItem)N(selected,onClick,icon,labelTextStyle,indicatorShape,indicatorWidth:c#ui.unit.Dp,indicatorHorizontalPadding:c#ui.unit.Dp,indicatorVerticalPadding:c#ui.unit.Dp,indicatorToLabelVerticalPadding:c#ui.unit.Dp,startIconToLabelHorizontalPadding:c#ui.unit.Dp,topIconItemVerticalPadding:c#ui.unit.Dp,colors,modifier,enabled,label,iconPosition:c#material3.NavigationItemIconPosition,interactionSource)247@10748L94,257@11065L33,270@11488L7,271@11562L7,273@11612L24,259@11104L2381:NavigationItem.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(selected) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            textStyle = labelTextStyle;
            $dirty |= $composer3.changed(textStyle) ? 2048 : 1024;
        } else {
            textStyle = labelTextStyle;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(indicatorShape) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer3.changed(indicatorWidth) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changed(indicatorHorizontalPadding) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changed(indicatorVerticalPadding) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(indicatorToLabelVerticalPadding) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changed(startIconToLabelHorizontalPadding) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(topIconItemVerticalPadding) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changed(colors) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty1 |= $composer3.changed(modifier) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty1 |= $composer3.changed(enabled) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty1 |= $composer3.changedInstance(function3) ? 16384 : 8192;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= $composer3.changed(iconPosition) ? 131072 : 65536;
        }
        if ((1572864 & $changed1) == 0) {
            $dirty1 |= $composer3.changed(interactionSource) ? 1048576 : 524288;
        }
        int $dirty2 = $dirty1;
        if ($composer3.shouldExecute(((306783379 & $dirty) == 306783378 && (599187 & $dirty2) == 599186) ? false : true, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2075155418, $dirty, $dirty2, "androidx.compose.material3.NavigationItem (NavigationItem.kt:245)");
            }
            final long iconColor = colors.m2660iconColorWaAFU9c(selected, enabled);
            Function2 styledIcon = ComposableLambdaKt.rememberComposableLambda(1119868672, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationItemKt$NavigationItem$styledIcon$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C248@10758L78:NavigationItem.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1119868672, $changed2, -1, "androidx.compose.material3.NavigationItem.<anonymous> (NavigationItem.kt:248)");
                    }
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(iconColor)), function2, $composer4, ProvidedValue.$stable);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54);
            if (function3 == null) {
                $composer3.startReplaceGroup(-803302356);
                $composer3.endReplaceGroup();
                styledLabel = null;
            } else {
                $composer3.startReplaceGroup(-803266737);
                ComposerKt.sourceInformation($composer3, "254@10967L65");
                final TextStyle textStyle2 = textStyle;
                Function2 function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1062206119, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationItemKt$NavigationItem$styledLabel$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "C254@10969L61:NavigationItem.kt#uh7d8r");
                        if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            $composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1062206119, $changed2, -1, "androidx.compose.material3.NavigationItem.<anonymous> (NavigationItem.kt:254)");
                        }
                        NavigationItemKt.StyledLabel(selected, textStyle2, colors, enabled, function3, $composer4, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54);
                $composer3.endReplaceGroup();
                styledLabel = function2RememberComposableLambda;
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1359565019, "CC(remember):NavigationItem.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = SnapshotIntStateKt.mutableIntStateOf(0);
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            final MutableIntState itemWidth$delegate = (MutableIntState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierM1089selectableO2vRcR0 = SelectableKt.m1089selectableO2vRcR0(modifier, selected, interactionSource, null, enabled, Role.m7092boximpl(Role.INSTANCE.m7106getTabo7Vup1c()), function0);
            ProvidableCompositionLocal<Dp> localMinimumInteractiveComponentSize = InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localMinimumInteractiveComponentSize);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            float fM7919unboximpl = ((Dp) objConsume).m7919unboximpl();
            ProvidableCompositionLocal<Dp> localMinimumInteractiveComponentSize2 = InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localMinimumInteractiveComponentSize2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierM849defaultMinSizeVpY3zN4 = SizeKt.m849defaultMinSizeVpY3zN4(modifierM1089selectableO2vRcR0, fM7919unboximpl, ((Dp) objConsume2).m7919unboximpl());
            ComposerKt.sourceInformationMarkerStart($composer3, 1359582514, "CC(remember):NavigationItem.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavigationItemKt.NavigationItem_8Df7sds$lambda$4$lambda$3(itemWidth$delegate, (IntSize) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier$iv = OnRemeasuredModifierKt.onSizeChanged(modifierM849defaultMinSizeVpY3zN4, (Function1) it$iv2);
            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
            int $changed$iv$iv = (432 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor;
                $composer3.createNode(function1);
            } else {
                function1 = constructor;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 484584471, "C277@11772L41,304@13049L54,297@12698L781:NavigationItem.kt#uh7d8r");
            final State<Float> stateAnimateIndicatorProgressAsState = animateIndicatorProgressAsState(selected, $composer3, $dirty & 14);
            MappedInteractionSource offsetInteractionSource = null;
            if (NavigationItemIconPosition.m2665equalsimpl0(iconPosition, NavigationItemIconPosition.INSTANCE.m2670getTopxw1Ddg())) {
                $composer3.startReplaceGroup(484755993);
                ComposerKt.sourceInformation($composer3, "284@12247L7,292@12542L136");
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer3.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density $this$NavigationItem_8Df7sds_u24lambda_u249_u24lambda_u245 = (Density) objConsume3;
                float x$iv = (NavigationItem_8Df7sds$lambda$1(itemWidth$delegate) - $this$NavigationItem_8Df7sds_u24lambda_u249_u24lambda_u245.mo400roundToPx0680j_4(indicatorWidth)) / 2.0f;
                float y$iv = $this$NavigationItem_8Df7sds_u24lambda_u249_u24lambda_u245.mo406toPx0680j_4(IndicatorVerticalOffset);
                long v1$iv$iv = Float.floatToRawIntBits(x$iv);
                long v2$iv$iv = Float.floatToRawIntBits(y$iv);
                long deltaOffset = Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
                Unit unit = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1092722808, "CC(remember):NavigationItem.kt#9igjgp");
                boolean invalid$iv = (($dirty2 & 3670016) == 1048576) | $composer3.changed(deltaOffset);
                Object value$iv3 = $composer3.rememberedValue();
                if (invalid$iv || value$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = new MappedInteractionSource(interactionSource, deltaOffset, null);
                    $composer3.updateRememberedValue(value$iv3);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                offsetInteractionSource = (MappedInteractionSource) value$iv3;
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(485471938);
                $composer3.endReplaceGroup();
            }
            MutableInteractionSource mutableInteractionSource = offsetInteractionSource != null ? offsetInteractionSource : interactionSource;
            long selectedIndicatorColor = colors.getSelectedIndicatorColor();
            ComposerKt.sourceInformationMarkerStart($composer3, -1092706666, "CC(remember):NavigationItem.kt#9igjgp");
            boolean invalid$iv2 = $composer3.changed(stateAnimateIndicatorProgressAsState);
            InteractionSource interactionSource2 = mutableInteractionSource;
            Object value$iv4 = $composer3.rememberedValue();
            if (invalid$iv2 || value$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = new Function0() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(RangesKt.coerceAtLeast(((Number) stateAnimateIndicatorProgressAsState.getValue()).floatValue(), 0.0f));
                    }
                };
                $composer3.updateRememberedValue(value$iv4);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            m2675NavigationItemLayoutKmRXDg(interactionSource2, selectedIndicatorColor, indicatorShape, styledIcon, iconPosition, styledLabel, (Function0) value$iv4, indicatorHorizontalPadding, indicatorVerticalPadding, indicatorToLabelVerticalPadding, startIconToLabelHorizontalPadding, topIconItemVerticalPadding, $composer3, (($dirty >> 6) & 896) | 3072 | ((
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0534: INVOKE 
                  (r16v4 'interactionSource2' androidx.compose.foundation.interaction.InteractionSource)
                  (r10v2 'selectedIndicatorColor' long)
                  (r60v0 'indicatorShape' androidx.compose.ui.graphics.Shape A[D('indicatorShape' androidx.compose.ui.graphics.Shape)])
                  (r19v3 'styledIcon' kotlin.jvm.functions.Function2 A[D('styledIcon' kotlin.jvm.functions.Function2)])
                  (r71v0 'iconPosition' int A[D('iconPosition' int)])
                  (r15v3 'styledLabel' kotlin.jvm.functions.Function2 A[D('styledLabel' kotlin.jvm.functions.Function2)])
                  (wrap kotlin.jvm.functions.Function0:0x04ea: CHECK_CAST (kotlin.jvm.functions.Function0) (r1v16 'value$iv4' java.lang.Object))
                  (r62v0 'indicatorHorizontalPadding' float A[D('indicatorHorizontalPadding' float)])
                  (r63v0 'indicatorVerticalPadding' float A[D('indicatorVerticalPadding' float)])
                  (r64v0 'indicatorToLabelVerticalPadding' float A[D('indicatorToLabelVerticalPadding' float)])
                  (r65v0 'startIconToLabelHorizontalPadding' float A[D('startIconToLabelHorizontalPadding' float)])
                  (r66v0 'topIconItemVerticalPadding' float A[D('topIconItemVerticalPadding' float)])
                  (r11v0 '$composer3' androidx.compose.runtime.Composer A[D('$composer' androidx.compose.runtime.Composer)])
                  (wrap int:0x0511: ARITH (wrap int:0x050b: ARITH (wrap int:0x0505: ARITH (wrap int:0x04ff: ARITH (wrap int:0x04f7: ARITH (wrap int:0x04f5: ARITH (wrap int:0x04f3: ARITH (r2v12 '$dirty' int) >> (6 int) A[WRAPPED] (LINE:310)) & (896 int) A[WRAPPED] (LINE:310)) | (3072 int) A[DONT_WRAP, WRAPPED] (LINE:310)) | (wrap int:0x04fe: ARITH (wrap int:0x04f9: ARITH (r17v2 int) >> (3 int) A[WRAPPED]) & (57344 int) A[WRAPPED]) A[DONT_WRAP, WRAPPED] (LINE:310)) | (wrap int:0x0504: ARITH (wrap int:0x0500: ARITH (r2v12 '$dirty' int) << (3 int) A[WRAPPED]) & (29360128 int) A[WRAPPED]) A[DONT_WRAP, WRAPPED] (LINE:310)) | (wrap int:0x050a: ARITH (wrap int:0x0506: ARITH (r2v12 '$dirty' int) << (3 int) A[WRAPPED]) & (234881024 int) A[WRAPPED]) A[DONT_WRAP, WRAPPED] (LINE:310)) | (wrap int:0x0510: ARITH (wrap int:0x050c: ARITH (r2v12 '$dirty' int) << (3 int) A[WRAPPED]) & (1879048192 int) A[WRAPPED]) A[WRAPPED] (LINE:310))
                  (wrap int:0x051b: ARITH (wrap int:0x0515: ARITH (wrap int:0x0513: ARITH (r2v12 '$dirty' int) >> (27 int) A[WRAPPED]) & (14 int) A[WRAPPED]) | (wrap int:0x0519: ARITH (wrap int:0x0517: ARITH (r17v2 int) << (3 int) A[WRAPPED]) & (112 int) A[WRAPPED]) A[WRAPPED])
                 STATIC call: androidx.compose.material3.NavigationItemKt.NavigationItemLayout-KmRX-Dg(androidx.compose.foundation.interaction.InteractionSource, long, androidx.compose.ui.graphics.Shape, kotlin.jvm.functions.Function2, int, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function0, float, float, float, float, float, androidx.compose.runtime.Composer, int, int):void A[MD:(androidx.compose.foundation.interaction.InteractionSource, long, androidx.compose.ui.graphics.Shape, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, int, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function0<java.lang.Float>, float, float, float, float, float, androidx.compose.runtime.Composer, int, int):void (m)] (LINE:298) in method: androidx.compose.material3.NavigationItemKt.NavigationItem-8Df7sds(boolean, kotlin.jvm.functions.Function0<kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, androidx.compose.ui.text.TextStyle, androidx.compose.ui.graphics.Shape, float, float, float, float, float, float, androidx.compose.material3.NavigationItemColors, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void, file: classes10.dex
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
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r17v2 int
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                */
            /*
                Method dump skipped, instruction units count: 1434
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationItemKt.m2674NavigationItem8Df7sds(boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function2, androidx.compose.ui.text.TextStyle, androidx.compose.ui.graphics.Shape, float, float, float, float, float, float, androidx.compose.material3.NavigationItemColors, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
        }

        private static final int NavigationItem_8Df7sds$lambda$1(MutableIntState $itemWidth$delegate) {
            MutableIntState $this$getValue$iv = $itemWidth$delegate;
            return $this$getValue$iv.getIntValue();
        }

        static final Unit NavigationItem_8Df7sds$lambda$4$lambda$3(MutableIntState $itemWidth$delegate, IntSize it) {
            long arg0$iv = it.m8080unboximpl();
            $itemWidth$delegate.setIntValue((int) (arg0$iv >> 32));
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:195:0x040c  */
        /* JADX WARN: Code duplicated, block: B:196:0x040f  */
        /* JADX WARN: Code duplicated, block: B:199:0x044e  */
        /* JADX WARN: Code duplicated, block: B:200:0x0463  */
        /* JADX WARN: Code duplicated, block: B:203:0x046f  */
        /* JADX WARN: Code duplicated, block: B:204:0x04b0  */
        /* JADX WARN: Code duplicated, block: B:207:0x04d8  */
        /* JADX WARN: Code duplicated, block: B:209:0x0548  */
        /* JADX WARN: Code duplicated, block: B:210:0x054b  */
        /* JADX WARN: Code duplicated, block: B:219:0x058c  */
        /* JADX WARN: Code duplicated, block: B:221:0x059d  */
        /* JADX WARN: Code duplicated, block: B:222:0x05a1  */
        /* JADX WARN: Code duplicated, block: B:232:0x05f7  */
        /* JADX WARN: Code duplicated, block: B:235:0x0602  */
        /* JADX WARN: Code duplicated, block: B:239:0x06a2  */
        /* JADX INFO: renamed from: AnimatedNavigationItem-DQd_Gtc, reason: not valid java name */
        public static final void m2671AnimatedNavigationItemDQd_Gtc(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final Shape indicatorShape, final float topIconIndicatorWidth, final TextStyle topIconLabelTextStyle, final TextStyle startIconLabelTextStyle, final float topIconIndicatorHorizontalPadding, final float topIconIndicatorVerticalPadding, final float topIconIndicatorToLabelVerticalPadding, final float startIconIndicatorHorizontalPadding, final float startIconIndicatorVerticalPadding, final float noLabelIndicatorPadding, final float startIconToLabelHorizontalPadding, final float itemHorizontalPadding, final NavigationItemColors colors, final Modifier modifier, final boolean enabled, final Function2<? super Composer, ? super Integer, Unit> function3, final int iconPosition, final MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int $changed2) {
            float f;
            Composer $composer2;
            Function0<ComposeUiNode> function1;
            MeasurePolicy measurePolicy$iv;
            CompositionLocalMap localMap$iv$iv;
            final boolean isIconPositionTop;
            float f2;
            final State<Float> stateAnimateFloatAsState;
            Object value$iv;
            State textStyle$delegate;
            final State<Float> state;
            boolean z;
            Composer $composer3;
            final State textStyle$delegate2;
            Function2 function2RememberComposableLambda;
            MappedInteractionSource offsetInteractionSource;
            MutableInteractionSource mutableInteractionSource;
            boolean invalid$iv;
            Object value$iv2;
            Function0 function4;
            boolean invalid$iv2;
            Object it$iv;
            Function0 function5;
            boolean propagateMinConstraints$iv;
            boolean z2;
            boolean invalid$iv3;
            Object it$iv2;
            Composer $composer4 = $composer.startRestartGroup(-1114086313);
            ComposerKt.sourceInformation($composer4, "C(AnimatedNavigationItem)N(selected,onClick,icon,indicatorShape,topIconIndicatorWidth:c#ui.unit.Dp,topIconLabelTextStyle,startIconLabelTextStyle,topIconIndicatorHorizontalPadding:c#ui.unit.Dp,topIconIndicatorVerticalPadding:c#ui.unit.Dp,topIconIndicatorToLabelVerticalPadding:c#ui.unit.Dp,startIconIndicatorHorizontalPadding:c#ui.unit.Dp,startIconIndicatorVerticalPadding:c#ui.unit.Dp,noLabelIndicatorPadding:c#ui.unit.Dp,startIconToLabelHorizontalPadding:c#ui.unit.Dp,itemHorizontalPadding:c#ui.unit.Dp,colors,modifier,enabled,label,iconPosition:c#material3.NavigationItemIconPosition,interactionSource)345@14658L94,349@14775L33,362@15198L7,363@15272L7,365@15322L24,351@14814L3783:NavigationItem.kt#uh7d8r");
            int $dirty = $changed;
            int $dirty1 = $changed1;
            int $dirty2 = $changed2;
            if (($changed & 6) == 0) {
                $dirty |= $composer4.changed(selected) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer4.changedInstance(function0) ? 32 : 16;
            }
            if (($changed & 384) == 0) {
                $dirty |= $composer4.changedInstance(function2) ? 256 : 128;
            }
            if (($changed & 3072) == 0) {
                $dirty |= $composer4.changed(indicatorShape) ? 2048 : 1024;
            }
            if (($changed & 24576) == 0) {
                $dirty |= $composer4.changed(topIconIndicatorWidth) ? 16384 : 8192;
            }
            if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                $dirty |= $composer4.changed(topIconLabelTextStyle) ? 131072 : 65536;
            }
            if (($changed & 1572864) == 0) {
                $dirty |= $composer4.changed(startIconLabelTextStyle) ? 1048576 : 524288;
            }
            if (($changed & 12582912) == 0) {
                $dirty |= $composer4.changed(topIconIndicatorHorizontalPadding) ? 8388608 : 4194304;
            }
            if (($changed & 100663296) == 0) {
                $dirty |= $composer4.changed(topIconIndicatorVerticalPadding) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
            }
            if (($changed & C.ENCODING_PCM_32BIT) == 0) {
                $dirty |= $composer4.changed(topIconIndicatorToLabelVerticalPadding) ? 536870912 : 268435456;
            }
            if (($changed1 & 6) == 0) {
                $dirty1 |= $composer4.changed(startIconIndicatorHorizontalPadding) ? 4 : 2;
            }
            if (($changed1 & 48) == 0) {
                f = startIconIndicatorVerticalPadding;
                $dirty1 |= $composer4.changed(f) ? 32 : 16;
            } else {
                f = startIconIndicatorVerticalPadding;
            }
            if (($changed1 & 384) == 0) {
                $dirty1 |= $composer4.changed(noLabelIndicatorPadding) ? 256 : 128;
            }
            if (($changed1 & 3072) == 0) {
                $dirty1 |= $composer4.changed(startIconToLabelHorizontalPadding) ? 2048 : 1024;
            }
            if (($changed1 & 24576) == 0) {
                $dirty1 |= $composer4.changed(itemHorizontalPadding) ? 16384 : 8192;
            }
            if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                $dirty1 |= $composer4.changed(colors) ? 131072 : 65536;
            }
            if (($changed1 & 1572864) == 0) {
                $dirty1 |= $composer4.changed(modifier) ? 1048576 : 524288;
            }
            if (($changed1 & 12582912) == 0) {
                $dirty1 |= $composer4.changed(enabled) ? 8388608 : 4194304;
            }
            if (($changed1 & 100663296) == 0) {
                $dirty1 |= $composer4.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
            }
            if (($changed1 & C.ENCODING_PCM_32BIT) == 0) {
                $dirty1 |= $composer4.changed(iconPosition) ? 536870912 : 268435456;
            }
            if (($changed2 & 6) == 0) {
                $dirty2 |= $composer4.changed(interactionSource) ? 4 : 2;
            }
            int $dirty3 = $dirty2;
            if (!$composer4.shouldExecute((($dirty & 306783379) == 306783378 && (306783379 & $dirty1) == 306783378 && ($dirty3 & 3) == 2) ? false : true, $dirty & 1)) {
                $composer2 = $composer4;
                $composer2.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1114086313, $dirty, $dirty1, "androidx.compose.material3.AnimatedNavigationItem (NavigationItem.kt:343)");
                }
                final long iconColor = colors.m2660iconColorWaAFU9c(selected, enabled);
                Function2 styledIcon = ComposableLambdaKt.rememberComposableLambda(851124593, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationItemKt$AnimatedNavigationItem$styledIcon$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer5, int $changed3) {
                        ComposerKt.sourceInformation($composer5, "C346@14668L78:NavigationItem.kt#uh7d8r");
                        if (!$composer5.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                            $composer5.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(851124593, $changed3, -1, "androidx.compose.material3.AnimatedNavigationItem.<anonymous> (NavigationItem.kt:346)");
                        }
                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(iconColor)), function2, $composer5, ProvidedValue.$stable);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer4, 54);
                ComposerKt.sourceInformationMarkerStart($composer4, -1190056456, "CC(remember):NavigationItem.kt#9igjgp");
                Object it$iv3 = $composer4.rememberedValue();
                if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = SnapshotIntStateKt.mutableIntStateOf(0);
                    $composer4.updateRememberedValue(value$iv3);
                    it$iv3 = value$iv3;
                }
                final MutableIntState itemWidth$delegate = (MutableIntState) it$iv3;
                ComposerKt.sourceInformationMarkerEnd($composer4);
                $composer2 = $composer4;
                int $dirty4 = $dirty;
                int $dirty5 = $dirty1;
                Modifier modifierM1089selectableO2vRcR0 = SelectableKt.m1089selectableO2vRcR0(modifier, selected, interactionSource, null, enabled, Role.m7092boximpl(Role.INSTANCE.m7106getTabo7Vup1c()), function0);
                ProvidableCompositionLocal<Dp> localMinimumInteractiveComponentSize = InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localMinimumInteractiveComponentSize);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                float fM7919unboximpl = ((Dp) objConsume).m7919unboximpl();
                ProvidableCompositionLocal<Dp> localMinimumInteractiveComponentSize2 = InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer2.consume(localMinimumInteractiveComponentSize2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierM849defaultMinSizeVpY3zN4 = SizeKt.m849defaultMinSizeVpY3zN4(modifierM1089selectableO2vRcR0, fM7919unboximpl, ((Dp) objConsume2).m7919unboximpl());
                ComposerKt.sourceInformationMarkerStart($composer2, -1190038961, "CC(remember):NavigationItem.kt#9igjgp");
                Object it$iv4 = $composer2.rememberedValue();
                if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv4 = new Function1() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationItemKt.AnimatedNavigationItem_DQd_Gtc$lambda$15$lambda$14(itemWidth$delegate, (IntSize) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv4);
                    it$iv4 = value$iv4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifier$iv = OnRemeasuredModifierKt.onSizeChanged(modifierM849defaultMinSizeVpY3zN4, (Function1) it$iv4);
                Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
                int $changed$iv$iv = (432 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function1 = constructor;
                    $composer2.createNode(function1);
                } else {
                    function1 = constructor;
                    $composer2.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    measurePolicy$iv = measurePolicy$iv2;
                    localMap$iv$iv = localMap$iv$iv2;
                } else {
                    measurePolicy$iv = measurePolicy$iv2;
                    localMap$iv$iv = localMap$iv$iv2;
                    if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i2 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 679531148, "C370@15561L41,375@15890L7,372@15651L261,378@15939L200,422@17712L54,425@17875L42,418@17458L1133:NavigationItem.kt#uh7d8r");
                    isIconPositionTop = NavigationItemIconPosition.m2665equalsimpl0(iconPosition, NavigationItemIconPosition.INSTANCE.m2670getTopxw1Ddg());
                    final State<Float> stateAnimateIndicatorProgressAsState = animateIndicatorProgressAsState(selected, $composer2, $dirty4 & 14);
                    if (isIconPositionTop) {
                        f2 = 0.0f;
                    } else {
                        f2 = 1.0f;
                    }
                    stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f2, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer2, 6), 0.0f, null, null, $composer2, 0, 28);
                    ComposerKt.sourceInformationMarkerStart($composer2, -532256231, "CC(remember):NavigationItem.kt#9igjgp");
                    value$iv = $composer2.rememberedValue();
                    if (value$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda13
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return NavigationItemKt.AnimatedNavigationItem_DQd_Gtc$lambda$26$lambda$18$lambda$17(isIconPositionTop, topIconLabelTextStyle, startIconLabelTextStyle, stateAnimateFloatAsState);
                            }
                        });
                        $composer2.updateRememberedValue(value$iv);
                    }
                    textStyle$delegate = (State) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (function3 != null) {
                        $composer2.startReplaceGroup(680212713);
                        ComposerKt.sourceInformation($composer2, "386@16242L296");
                        state = stateAnimateFloatAsState;
                        z = false;
                        $composer3 = $composer2;
                        textStyle$delegate2 = textStyle$delegate;
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(22475789, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationItemKt$AnimatedNavigationItem$2$styledLabel$1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer5, int $changed3) {
                                ComposerKt.sourceInformation($composer5, "C387@16264L256:NavigationItem.kt#uh7d8r");
                                if ($composer5.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(22475789, $changed3, -1, "androidx.compose.material3.AnimatedNavigationItem.<anonymous>.<anonymous> (NavigationItem.kt:387)");
                                    }
                                    NavigationItemKt.StyledLabel(selected, NavigationItemKt.AnimatedNavigationItem_DQd_Gtc$lambda$26$lambda$19(textStyle$delegate2), colors, enabled, function3, $composer5, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        }, $composer3, 54);
                        $composer3.endReplaceGroup();
                    } else {
                        state = stateAnimateFloatAsState;
                        z = false;
                        $composer3 = $composer2;
                        textStyle$delegate2 = textStyle$delegate;
                        $composer3.startReplaceGroup(680534989);
                        $composer3.endReplaceGroup();
                        function2RememberComposableLambda = null;
                    }
                    offsetInteractionSource = null;
                    if (isIconPositionTop) {
                        $composer3.startReplaceGroup(680694081);
                        ComposerKt.sourceInformation($composer3, "405@17000L7,413@17302L136");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                        Object objConsume3 = $composer3.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Density $this$AnimatedNavigationItem_DQd_Gtc_u24lambda_u2426_u24lambda_u2420 = (Density) objConsume3;
                        float x$iv = (AnimatedNavigationItem_DQd_Gtc$lambda$12(itemWidth$delegate) - $this$AnimatedNavigationItem_DQd_Gtc_u24lambda_u2426_u24lambda_u2420.mo400roundToPx0680j_4(topIconIndicatorWidth)) / 2.0f;
                        float y$iv = $this$AnimatedNavigationItem_DQd_Gtc_u24lambda_u2426_u24lambda_u2420.mo406toPx0680j_4(IndicatorVerticalOffset);
                        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
                        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
                        long deltaOffset = Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
                        Unit unit = Unit.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, -532212679, "CC(remember):NavigationItem.kt#9igjgp");
                        if (($dirty3 & 14) == 4) {
                            z2 = true;
                        } else {
                            z2 = z;
                        }
                        invalid$iv3 = z2 | $composer2.changed(deltaOffset);
                        Composer $this$cache$iv = $composer3;
                        it$iv2 = $this$cache$iv.rememberedValue();
                        if (!invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                            Object value$iv5 = new MappedInteractionSource(interactionSource, deltaOffset, null);
                            $this$cache$iv.updateRememberedValue(value$iv5);
                            it$iv2 = value$iv5;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        offsetInteractionSource = (MappedInteractionSource) it$iv2;
                        $composer3.endReplaceGroup();
                    } else {
                        $composer3.startReplaceGroup(681416753);
                        $composer3.endReplaceGroup();
                    }
                    if (offsetInteractionSource != null) {
                        mutableInteractionSource = offsetInteractionSource;
                    } else {
                        mutableInteractionSource = interactionSource;
                    }
                    InteractionSource interactionSource2 = mutableInteractionSource;
                    long selectedIndicatorColor = colors.getSelectedIndicatorColor();
                    ComposerKt.sourceInformationMarkerStart($composer3, -532199641, "CC(remember):NavigationItem.kt#9igjgp");
                    invalid$iv = $composer3.changed(
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x05b1: INVOKE (r0v55 'invalid$iv' boolean) = 
                          (r6v18 '$composer3' androidx.compose.runtime.Composer A[D('$composer' androidx.compose.runtime.Composer)])
                          (r26v4 java.lang.Object)
                         INTERFACE call: androidx.compose.runtime.Composer.changed(java.lang.Object):boolean A[MD:(java.lang.Object):boolean (m)] in method: androidx.compose.material3.NavigationItemKt.AnimatedNavigationItem-DQd_Gtc(boolean, kotlin.jvm.functions.Function0<kotlin.Unit>, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, androidx.compose.ui.graphics.Shape, float, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, float, float, float, float, float, float, float, float, androidx.compose.material3.NavigationItemColors, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void, file: classes10.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:140)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:140)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
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
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r26v4 java.lang.Object
                        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                        */
                    /*
                        Method dump skipped, instruction units count: 1782
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationItemKt.m2671AnimatedNavigationItemDQd_Gtc(boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function2, androidx.compose.ui.graphics.Shape, float, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, float, float, float, float, float, float, float, float, androidx.compose.material3.NavigationItemColors, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void");
                }

                private static final int AnimatedNavigationItem_DQd_Gtc$lambda$12(MutableIntState $itemWidth$delegate) {
                    MutableIntState $this$getValue$iv = $itemWidth$delegate;
                    return $this$getValue$iv.getIntValue();
                }

                static final Unit AnimatedNavigationItem_DQd_Gtc$lambda$15$lambda$14(MutableIntState $itemWidth$delegate, IntSize it) {
                    long arg0$iv = it.m8080unboximpl();
                    $itemWidth$delegate.setIntValue((int) (arg0$iv >> 32));
                    return Unit.INSTANCE;
                }

                private static final float AnimatedNavigationItem_DQd_Gtc$lambda$26$lambda$16(State<Float> state) {
                    Object thisObj$iv = state.getValue();
                    return ((Number) thisObj$iv).floatValue();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final TextStyle AnimatedNavigationItem_DQd_Gtc$lambda$26$lambda$19(State<TextStyle> state) {
                    Object thisObj$iv = state.getValue();
                    return (TextStyle) thisObj$iv;
                }

                static final TextStyle AnimatedNavigationItem_DQd_Gtc$lambda$26$lambda$18$lambda$17(boolean $isIconPositionTop, TextStyle $topIconLabelTextStyle, TextStyle $startIconLabelTextStyle, State $iconPositionProgress$delegate) {
                    return (!$isIconPositionTop || AnimatedNavigationItem_DQd_Gtc$lambda$26$lambda$16($iconPositionProgress$delegate) >= 0.5f) ? $startIconLabelTextStyle : $topIconLabelTextStyle;
                }

                /* JADX WARN: Code duplicated, block: B:130:0x02dc  */
                /* JADX WARN: Code duplicated, block: B:133:0x02e8  */
                /* JADX WARN: Code duplicated, block: B:134:0x02ee  */
                /* JADX WARN: Code duplicated, block: B:144:0x03a7  */
                /* JADX WARN: Code duplicated, block: B:146:0x0416  */
                /* JADX WARN: Code duplicated, block: B:149:0x0422  */
                /* JADX WARN: Code duplicated, block: B:150:0x0428  */
                /* JADX WARN: Code duplicated, block: B:159:0x04df  */
                /* JADX WARN: Code duplicated, block: B:162:0x04fe  */
                /* JADX INFO: renamed from: NavigationItemLayout-KmRX-Dg, reason: not valid java name */
                private static final void m2675NavigationItemLayoutKmRXDg(final InteractionSource interactionSource, final long indicatorColor, final Shape indicatorShape, final Function2<? super Composer, ? super Integer, Unit> function2, final int iconPosition, final Function2<? super Composer, ? super Integer, Unit> function3, final Function0<Float> function0, final float indicatorHorizontalPadding, final float indicatorVerticalPadding, final float indicatorToLabelVerticalPadding, final float startIconToLabelHorizontalPadding, final float topIconItemVerticalPadding, Composer $composer, final int $changed, final int $changed1) {
                    Function0<Float> function1;
                    float f;
                    float f2;
                    Object topIconOrIconOnlyMeasurePolicy;
                    Function0<ComposeUiNode> function4;
                    String str;
                    String str2;
                    int compositeKeyHash$iv$iv;
                    Function0<ComposeUiNode> constructor;
                    Function0<ComposeUiNode> function5;
                    Composer $this$Layout_u24lambda_u240$iv$iv;
                    int compositeKeyHash$iv$iv2;
                    Function0<ComposeUiNode> constructor2;
                    Function0<ComposeUiNode> function6;
                    Composer $this$Layout_u24lambda_u240$iv$iv2;
                    Composer $composer2 = $composer.startRestartGroup(-1473868071);
                    ComposerKt.sourceInformation($composer2, "C(NavigationItemLayout)N(interactionSource,indicatorColor:c#ui.graphics.Color,indicatorShape,icon,iconPosition:c#material3.NavigationItemIconPosition,label,indicatorAnimationProgress,indicatorHorizontalPadding:c#ui.unit.Dp,indicatorVerticalPadding:c#ui.unit.Dp,indicatorToLabelVerticalPadding:c#ui.unit.Dp,startIconToLabelHorizontalPadding:c#ui.unit.Dp,topIconItemVerticalPadding:c#ui.unit.Dp)454@19104L1389:NavigationItem.kt#uh7d8r");
                    int $dirty = $changed;
                    int $dirty1 = $changed1;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer2.changed(interactionSource) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty |= $composer2.changed(indicatorColor) ? 32 : 16;
                    }
                    if (($changed & 384) == 0) {
                        $dirty |= $composer2.changed(indicatorShape) ? 256 : 128;
                    }
                    if (($changed & 3072) == 0) {
                        $dirty |= $composer2.changedInstance(function2) ? 2048 : 1024;
                    }
                    if (($changed & 24576) == 0) {
                        $dirty |= $composer2.changed(iconPosition) ? 16384 : 8192;
                    }
                    if ((196608 & $changed) == 0) {
                        $dirty |= $composer2.changedInstance(function3) ? 131072 : 65536;
                    }
                    if ((1572864 & $changed) == 0) {
                        function1 = function0;
                        $dirty |= $composer2.changedInstance(function1) ? 1048576 : 524288;
                    } else {
                        function1 = function0;
                    }
                    if (($changed & 12582912) == 0) {
                        $dirty |= $composer2.changed(indicatorHorizontalPadding) ? 8388608 : 4194304;
                    }
                    if (($changed & 100663296) == 0) {
                        f = indicatorVerticalPadding;
                        $dirty |= $composer2.changed(f) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                    } else {
                        f = indicatorVerticalPadding;
                    }
                    if (($changed & C.ENCODING_PCM_32BIT) == 0) {
                        f2 = indicatorToLabelVerticalPadding;
                        $dirty |= $composer2.changed(f2) ? 536870912 : 268435456;
                    } else {
                        f2 = indicatorToLabelVerticalPadding;
                    }
                    if (($changed1 & 6) == 0) {
                        $dirty1 |= $composer2.changed(startIconToLabelHorizontalPadding) ? 4 : 2;
                    }
                    if (($changed1 & 48) == 0) {
                        $dirty1 |= $composer2.changed(topIconItemVerticalPadding) ? 32 : 16;
                    }
                    if ($composer2.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty1 & 19) == 18) ? false : true, $dirty & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1473868071, $dirty, $dirty1, "androidx.compose.material3.NavigationItemLayout (NavigationItem.kt:453)");
                        }
                        Modifier modifierBadgeBounds = BadgeKt.badgeBounds(Modifier.INSTANCE);
                        if (function3 == null || NavigationItemIconPosition.m2665equalsimpl0(iconPosition, NavigationItemIconPosition.INSTANCE.m2670getTopxw1Ddg())) {
                            topIconOrIconOnlyMeasurePolicy = new TopIconOrIconOnlyMeasurePolicy(function3 != null, function0, indicatorHorizontalPadding, indicatorVerticalPadding, f2, topIconItemVerticalPadding, null);
                        } else {
                            topIconOrIconOnlyMeasurePolicy = new StartIconMeasurePolicy(function1, indicatorHorizontalPadding, f, startIconToLabelHorizontalPadding, null);
                        }
                        ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                        int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                        CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
                        Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifierBadgeBounds);
                        Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv = ((0 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                        if (!($composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer2.startReusableNode();
                        if ($composer2.getInserting()) {
                            function4 = constructor3;
                            $composer2.createNode(function4);
                        } else {
                            function4 = constructor3;
                            $composer2.useNode();
                        }
                        int $dirty2 = $dirty;
                        Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer2);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, topIconOrIconOnlyMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                            str = "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh";
                        } else {
                            str = "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh";
                            if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                            }
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                            int i = ($changed$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, -2087603364, "C458@19231L50,461@19484L69,463@19567L50:NavigationItem.kt#uh7d8r");
                            IndicatorRipple(interactionSource, indicatorShape, $composer2, ($dirty2 & 14) | (($dirty2 >> 3) & 112));
                            str2 = str;
                            m2673Indicator3JVO9M(indicatorColor, indicatorShape, function0, $composer2, (($dirty2 >> 3) & 14) | (($dirty2 >> 3) & 112) | (($dirty2 >> 12) & 896));
                            Modifier modifier$iv = LayoutIdKt.layoutId(Modifier.INSTANCE, IconLayoutIdTag);
                            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                            int $changed$iv$iv2 = (6 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, str2);
                            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
                            constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                                function5 = constructor;
                                $composer2.createNode(function5);
                            } else {
                                function5 = constructor;
                                $composer2.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                            }
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                            int i2 = ($changed$iv$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i3 = ((6 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, 652632167, "C463@19609L6:NavigationItem.kt#uh7d8r");
                            function2.invoke($composer2, Integer.valueOf(($dirty2 >> 9) & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            if (function3 != null) {
                                $composer2.startReplaceGroup(-2087200706);
                                ComposerKt.sourceInformation($composer2, "466@19668L52");
                                Modifier modifier$iv2 = LayoutIdKt.layoutId(Modifier.INSTANCE, "label");
                                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                                int $changed$iv$iv3 = (6 << 3) & 112;
                                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, str2);
                                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
                                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                                if (!($composer2.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer2.startReusableNode();
                                if ($composer2.getInserting()) {
                                    function6 = constructor2;
                                    $composer2.createNode(function6);
                                } else {
                                    function6 = constructor2;
                                    $composer2.useNode();
                                }
                                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer2);
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
                                }
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                                int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                                int i5 = ((6 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer2, -2123117109, "C466@19711L7:NavigationItem.kt#uh7d8r");
                                function3.invoke($composer2, Integer.valueOf(($dirty2 >> 15) & 14));
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                $composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                $composer2.endReplaceGroup();
                            } else {
                                $composer2.startReplaceGroup(-2087119982);
                                $composer2.endReplaceGroup();
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                        $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                        $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i6 = ($changed$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer2, -2087603364, "C458@19231L50,461@19484L69,463@19567L50:NavigationItem.kt#uh7d8r");
                        IndicatorRipple(interactionSource, indicatorShape, $composer2, ($dirty2 & 14) | (($dirty2 >> 3) & 112));
                        str2 = str;
                        m2673Indicator3JVO9M(indicatorColor, indicatorShape, function0, $composer2, (($dirty2 >> 3) & 14) | (($dirty2 >> 3) & 112) | (($dirty2 >> 12) & 896));
                        Modifier modifier$iv3 = LayoutIdKt.layoutId(Modifier.INSTANCE, IconLayoutIdTag);
                        ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                        int $changed$iv$iv4 = (6 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, str2);
                        compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                        CompositionLocalMap localMap$iv$iv3 = $composer2.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer2, modifier$iv3);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv3 = (($changed$iv$iv4 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                        if (!($composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer2.startReusableNode();
                        if ($composer2.getInserting()) {
                            function5 = constructor;
                            $composer2.createNode(function5);
                        } else {
                            function5 = constructor;
                            $composer2.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                        }
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash4);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                        int i7 = ($changed$iv$iv$iv3 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                        int i8 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer2, 652632167, "C463@19609L6:NavigationItem.kt#uh7d8r");
                        function2.invoke($composer2, Integer.valueOf(($dirty2 >> 9) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (function3 != null) {
                            $composer2.startReplaceGroup(-2087200706);
                            ComposerKt.sourceInformation($composer2, "466@19668L52");
                            Modifier modifier$iv4 = LayoutIdKt.layoutId(Modifier.INSTANCE, "label");
                            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
                            int $changed$iv$iv5 = (6 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, str2);
                            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                            CompositionLocalMap localMap$iv$iv4 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer2, modifier$iv4);
                            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv4 = (($changed$iv$iv5 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                                function6 = constructor2;
                                $composer2.createNode(function6);
                            } else {
                                function6 = constructor2;
                                $composer2.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer2);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash5);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                            int i9 = ($changed$iv$iv$iv4 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                            int i10 = ((6 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -2123117109, "C466@19711L7:NavigationItem.kt#uh7d8r");
                            function3.invoke($composer2, Integer.valueOf(($dirty2 >> 15) & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endReplaceGroup();
                        } else {
                            $composer2.startReplaceGroup(-2087119982);
                            $composer2.endReplaceGroup();
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        $composer2.skipToGroupEnd();
                    }
                    ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda8
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationItemKt.NavigationItemLayout_KmRX_Dg$lambda$31(interactionSource, indicatorColor, indicatorShape, function2, iconPosition, function3, function0, indicatorHorizontalPadding, indicatorVerticalPadding, indicatorToLabelVerticalPadding, startIconToLabelHorizontalPadding, topIconItemVerticalPadding, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }

                /* JADX INFO: renamed from: AnimatedNavigationItemLayout-he0WsC4, reason: not valid java name */
                private static final void m2672AnimatedNavigationItemLayouthe0WsC4(final InteractionSource interactionSource, final long indicatorColor, final Shape indicatorShape, final Function0<Float> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final int iconPosition, final Function0<Float> function1, final Function2<? super Composer, ? super Integer, Unit> function3, final float topIconIndicatorHorizontalPadding, final float topIconIndicatorVerticalPadding, final float topIconIndicatorToLabelVerticalPadding, final float startIconIndicatorHorizontalPadding, final float startIconIndicatorVerticalPadding, final float noLabelIndicatorPadding, final float startIconToLabelHorizontalPadding, final float itemHorizontalPadding, Composer $composer, final int $changed, final int $changed1) {
                    long j;
                    Function0<Float> function4;
                    Function0<Float> function5;
                    float f;
                    Object topIconOrIconOnlyMeasurePolicy;
                    Function0<ComposeUiNode> function6;
                    Function0<ComposeUiNode> function7;
                    Function0<ComposeUiNode> function8;
                    int $changed$iv;
                    Composer $composer2 = $composer.startRestartGroup(94433406);
                    ComposerKt.sourceInformation($composer2, "C(AnimatedNavigationItemLayout)N(interactionSource,indicatorColor:c#ui.graphics.Color,indicatorShape,indicatorAnimationProgress,icon,iconPosition:c#material3.NavigationItemIconPosition,iconPositionProgress,label,topIconIndicatorHorizontalPadding:c#ui.unit.Dp,topIconIndicatorVerticalPadding:c#ui.unit.Dp,topIconIndicatorToLabelVerticalPadding:c#ui.unit.Dp,startIconIndicatorHorizontalPadding:c#ui.unit.Dp,startIconIndicatorVerticalPadding:c#ui.unit.Dp,noLabelIndicatorPadding:c#ui.unit.Dp,startIconToLabelHorizontalPadding:c#ui.unit.Dp,itemHorizontalPadding:c#ui.unit.Dp)509@21184L2129:NavigationItem.kt#uh7d8r");
                    int $dirty = $changed;
                    int $dirty1 = $changed1;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer2.changed(interactionSource) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        j = indicatorColor;
                        $dirty |= $composer2.changed(j) ? 32 : 16;
                    } else {
                        j = indicatorColor;
                    }
                    if (($changed & 384) == 0) {
                        $dirty |= $composer2.changed(indicatorShape) ? 256 : 128;
                    }
                    if (($changed & 3072) == 0) {
                        function4 = function0;
                        $dirty |= $composer2.changedInstance(function4) ? 2048 : 1024;
                    } else {
                        function4 = function0;
                    }
                    if (($changed & 24576) == 0) {
                        $dirty |= $composer2.changedInstance(function2) ? 16384 : 8192;
                    }
                    if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        $dirty |= $composer2.changed(iconPosition) ? 131072 : 65536;
                    }
                    if (($changed & 1572864) == 0) {
                        function5 = function1;
                        $dirty |= $composer2.changedInstance(function5) ? 1048576 : 524288;
                    } else {
                        function5 = function1;
                    }
                    if (($changed & 12582912) == 0) {
                        $dirty |= $composer2.changedInstance(function3) ? 8388608 : 4194304;
                    }
                    if (($changed & 100663296) == 0) {
                        f = topIconIndicatorHorizontalPadding;
                        $dirty |= $composer2.changed(f) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                    } else {
                        f = topIconIndicatorHorizontalPadding;
                    }
                    if (($changed & C.ENCODING_PCM_32BIT) == 0) {
                        $dirty |= $composer2.changed(topIconIndicatorVerticalPadding) ? 536870912 : 268435456;
                    }
                    if (($changed1 & 6) == 0) {
                        $dirty1 |= $composer2.changed(topIconIndicatorToLabelVerticalPadding) ? 4 : 2;
                    }
                    if (($changed1 & 48) == 0) {
                        $dirty1 |= $composer2.changed(startIconIndicatorHorizontalPadding) ? 32 : 16;
                    }
                    if (($changed1 & 384) == 0) {
                        $dirty1 |= $composer2.changed(startIconIndicatorVerticalPadding) ? 256 : 128;
                    }
                    if (($changed1 & 3072) == 0) {
                        $dirty1 |= $composer2.changed(noLabelIndicatorPadding) ? 2048 : 1024;
                    }
                    if (($changed1 & 24576) == 0) {
                        $dirty1 |= $composer2.changed(startIconToLabelHorizontalPadding) ? 16384 : 8192;
                    }
                    if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        $dirty1 |= $composer2.changed(itemHorizontalPadding) ? 131072 : 65536;
                    }
                    if ($composer2.shouldExecute((($dirty & 306783379) == 306783378 && (74899 & $dirty1) == 74898) ? false : true, $dirty & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(94433406, $dirty, $dirty1, "androidx.compose.material3.AnimatedNavigationItemLayout (NavigationItem.kt:508)");
                        }
                        Modifier modifierBadgeBounds = BadgeKt.badgeBounds(Modifier.INSTANCE);
                        if (function3 != null) {
                            topIconOrIconOnlyMeasurePolicy = new AnimatedMeasurePolicy(iconPosition, function5, function4, f, topIconIndicatorVerticalPadding, topIconIndicatorToLabelVerticalPadding, startIconIndicatorHorizontalPadding, startIconIndicatorVerticalPadding, startIconToLabelHorizontalPadding, itemHorizontalPadding, null);
                        } else {
                            topIconOrIconOnlyMeasurePolicy = new TopIconOrIconOnlyMeasurePolicy(false, function0, noLabelIndicatorPadding, noLabelIndicatorPadding, Dp.m7905constructorimpl(0), Dp.m7905constructorimpl(0), null);
                        }
                        ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                        int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                        CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
                        Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifierBadgeBounds);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $dirty2 = $dirty;
                        int $changed$iv$iv = ((0 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                        if (!($composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer2.startReusableNode();
                        if ($composer2.getInserting()) {
                            function6 = constructor;
                            $composer2.createNode(function6);
                        } else {
                            function6 = constructor;
                            $composer2.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer2);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, topIconOrIconOnlyMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                            $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                            $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i = ($changed$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer2, -988347483, "C513@21311L50,516@21564L69,518@21647L50:NavigationItem.kt#uh7d8r");
                        IndicatorRipple(interactionSource, indicatorShape, $composer2, ($dirty2 & 14) | (($dirty2 >> 3) & 112));
                        m2673Indicator3JVO9M(j, indicatorShape, function0, $composer2, (($dirty2 >> 3) & 14) | (($dirty2 >> 3) & 112) | (($dirty2 >> 3) & 896));
                        Modifier modifier$iv = LayoutIdKt.layoutId(Modifier.INSTANCE, IconLayoutIdTag);
                        ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                        int $changed$iv$iv2 = (6 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                        CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                        if (!($composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer2.startReusableNode();
                        if ($composer2.getInserting()) {
                            function7 = constructor2;
                            $composer2.createNode(function7);
                        } else {
                            function7 = constructor2;
                            $composer2.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2 = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i3 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1416456196, "C518@21689L6:NavigationItem.kt#uh7d8r");
                        function2.invoke($composer2, Integer.valueOf(($dirty2 >> 12) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (function3 != null) {
                            $composer2.startReplaceGroup(-987944825);
                            ComposerKt.sourceInformation($composer2, "521@21748L52");
                            Modifier modifier$iv2 = LayoutIdKt.layoutId(Modifier.INSTANCE, "label");
                            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                            int $changed$iv$iv3 = (6 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                            CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
                            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                                function8 = constructor3;
                                $composer2.createNode(function8);
                            } else {
                                function8 = constructor3;
                                $composer2.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer2);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                                $changed$iv = 6;
                            } else {
                                $changed$iv = 6;
                                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                }
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                                int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                                int i5 = (($changed$iv >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer2, 157288374, "C521@21791L7:NavigationItem.kt#uh7d8r");
                                function3.invoke($composer2, Integer.valueOf(($dirty2 >> 21) & 14));
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                $composer2.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                $composer2.endReplaceGroup();
                            }
                            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                            int i6 = ($changed$iv$iv$iv2 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                            int i7 = (($changed$iv >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, 157288374, "C521@21791L7:NavigationItem.kt#uh7d8r");
                            function3.invoke($composer2, Integer.valueOf(($dirty2 >> 21) & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endReplaceGroup();
                        } else {
                            $composer2.startReplaceGroup(-987864101);
                            $composer2.endReplaceGroup();
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        $composer2.skipToGroupEnd();
                    }
                    ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationItemKt.AnimatedNavigationItemLayout_he0WsC4$lambda$35(interactionSource, indicatorColor, indicatorShape, function0, function2, iconPosition, function1, function3, topIconIndicatorHorizontalPadding, topIconIndicatorVerticalPadding, topIconIndicatorToLabelVerticalPadding, startIconIndicatorHorizontalPadding, startIconIndicatorVerticalPadding, noLabelIndicatorPadding, startIconToLabelHorizontalPadding, itemHorizontalPadding, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }

                /* JADX INFO: Access modifiers changed from: private */
                /* JADX INFO: renamed from: placeIcon-X9ElhV4, reason: not valid java name */
                public static final MeasureResult m2681placeIconX9ElhV4(MeasureScope $this$placeIcon_u2dX9ElhV4, final Placeable iconPlaceable, final Placeable indicatorRipplePlaceable, final Placeable indicatorPlaceable, long constraints) {
                    int width = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, indicatorRipplePlaceable.getWidth());
                    int height = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, indicatorRipplePlaceable.getHeight());
                    final int indicatorX = (width - indicatorPlaceable.getWidth()) / 2;
                    final int indicatorY = (height - indicatorPlaceable.getHeight()) / 2;
                    final int iconX = (width - iconPlaceable.getWidth()) / 2;
                    final int iconY = (height - iconPlaceable.getHeight()) / 2;
                    final int rippleX = (width - indicatorRipplePlaceable.getWidth()) / 2;
                    final int rippleY = (height - indicatorRipplePlaceable.getHeight()) / 2;
                    return MeasureScope.layout$default($this$placeIcon_u2dX9ElhV4, width, height, null, new Function1() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationItemKt.placeIcon_X9ElhV4$lambda$36(indicatorPlaceable, indicatorX, indicatorY, iconPlaceable, iconX, iconY, indicatorRipplePlaceable, rippleX, rippleY, (Placeable.PlacementScope) obj);
                        }
                    }, 4, null);
                }

                static final Unit placeIcon_X9ElhV4$lambda$36(Placeable $indicatorPlaceable, int $indicatorX, int $indicatorY, Placeable $iconPlaceable, int $iconX, int $iconY, Placeable $indicatorRipplePlaceable, int $rippleX, int $rippleY, Placeable.PlacementScope $this$layout) {
                    Placeable.PlacementScope.placeRelative$default($this$layout, $indicatorPlaceable, $indicatorX, $indicatorY, 0.0f, 4, null);
                    Placeable.PlacementScope.placeRelative$default($this$layout, $iconPlaceable, $iconX, $iconY, 0.0f, 4, null);
                    Placeable.PlacementScope.placeRelative$default($this$layout, $indicatorRipplePlaceable, $rippleX, $rippleY, 0.0f, 4, null);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                /* JADX INFO: renamed from: placeLabelAndTopIcon-qoqLrGI, reason: not valid java name */
                public static final MeasureResult m2683placeLabelAndTopIconqoqLrGI(MeasureScope $this$placeLabelAndTopIcon_u2dqoqLrGI, final Placeable labelPlaceable, final Placeable iconPlaceable, final Placeable indicatorRipplePlaceable, final Placeable indicatorPlaceable, long constraints, float indicatorToLabelVerticalPadding, float indicatorVerticalPadding, float topIconItemVerticalPadding) {
                    int width = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, Math.max(labelPlaceable.getWidth(), indicatorRipplePlaceable.getWidth()));
                    float contentHeight = indicatorRipplePlaceable.getHeight() + $this$placeLabelAndTopIcon_u2dqoqLrGI.mo406toPx0680j_4(indicatorToLabelVerticalPadding) + labelPlaceable.getHeight();
                    int height = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, MathKt.roundToInt(($this$placeLabelAndTopIcon_u2dqoqLrGI.mo406toPx0680j_4(topIconItemVerticalPadding) * 2.0f) + contentHeight));
                    final int iconY = $this$placeLabelAndTopIcon_u2dqoqLrGI.mo400roundToPx0680j_4(Dp.m7905constructorimpl(topIconItemVerticalPadding + indicatorVerticalPadding));
                    final int iconX = (width - iconPlaceable.getWidth()) / 2;
                    final int indicatorX = (width - indicatorPlaceable.getWidth()) / 2;
                    final int indicatorY = iconY - $this$placeLabelAndTopIcon_u2dqoqLrGI.mo400roundToPx0680j_4(indicatorVerticalPadding);
                    final int labelX = (width - labelPlaceable.getWidth()) / 2;
                    final int labelY = iconY + iconPlaceable.getHeight() + $this$placeLabelAndTopIcon_u2dqoqLrGI.mo400roundToPx0680j_4(Dp.m7905constructorimpl(indicatorVerticalPadding + indicatorToLabelVerticalPadding));
                    final int rippleX = (width - indicatorRipplePlaceable.getWidth()) / 2;
                    return MeasureScope.layout$default($this$placeLabelAndTopIcon_u2dqoqLrGI, width, height, null, new Function1() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationItemKt.placeLabelAndTopIcon_qoqLrGI$lambda$37(indicatorPlaceable, indicatorX, indicatorY, labelPlaceable, labelX, labelY, iconPlaceable, iconX, iconY, indicatorRipplePlaceable, rippleX, indicatorY, (Placeable.PlacementScope) obj);
                        }
                    }, 4, null);
                }

                static final Unit placeLabelAndTopIcon_qoqLrGI$lambda$37(Placeable $indicatorPlaceable, int $indicatorX, int $indicatorY, Placeable $labelPlaceable, int $labelX, int $labelY, Placeable $iconPlaceable, int $iconX, int $iconY, Placeable $indicatorRipplePlaceable, int $rippleX, int $rippleY, Placeable.PlacementScope $this$layout) {
                    Placeable.PlacementScope.placeRelative$default($this$layout, $indicatorPlaceable, $indicatorX, $indicatorY, 0.0f, 4, null);
                    Placeable.PlacementScope.placeRelative$default($this$layout, $labelPlaceable, $labelX, $labelY, 0.0f, 4, null);
                    Placeable.PlacementScope.placeRelative$default($this$layout, $iconPlaceable, $iconX, $iconY, 0.0f, 4, null);
                    Placeable.PlacementScope.placeRelative$default($this$layout, $indicatorRipplePlaceable, $rippleX, $rippleY, 0.0f, 4, null);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                /* JADX INFO: renamed from: placeLabelAndStartIcon-nru01g4, reason: not valid java name */
                public static final MeasureResult m2682placeLabelAndStartIconnru01g4(MeasureScope $this$placeLabelAndStartIcon_u2dnru01g4, final Placeable labelPlaceable, final Placeable iconPlaceable, final Placeable indicatorRipplePlaceable, final Placeable indicatorPlaceable, long constraints, float startIconToLabelHorizontalPadding) {
                    int width = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, indicatorRipplePlaceable.getWidth());
                    int height = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, indicatorRipplePlaceable.getHeight());
                    final int indicatorX = (width - indicatorPlaceable.getWidth()) / 2;
                    final int indicatorY = (height - indicatorPlaceable.getHeight()) / 2;
                    final int iconY = (height - iconPlaceable.getHeight()) / 2;
                    final int labelY = (height - labelPlaceable.getHeight()) / 2;
                    int itemContentWidth = iconPlaceable.getWidth() + $this$placeLabelAndStartIcon_u2dnru01g4.mo400roundToPx0680j_4(startIconToLabelHorizontalPadding) + labelPlaceable.getWidth();
                    final int iconX = (width - itemContentWidth) / 2;
                    final int labelX = iconX + iconPlaceable.getWidth() + $this$placeLabelAndStartIcon_u2dnru01g4.mo400roundToPx0680j_4(startIconToLabelHorizontalPadding);
                    final int rippleX = (width - indicatorRipplePlaceable.getWidth()) / 2;
                    final int rippleY = (height - indicatorRipplePlaceable.getHeight()) / 2;
                    return MeasureScope.layout$default($this$placeLabelAndStartIcon_u2dnru01g4, width, height, null, new Function1() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda17
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationItemKt.placeLabelAndStartIcon_nru01g4$lambda$38(indicatorPlaceable, indicatorX, indicatorY, labelPlaceable, labelX, labelY, iconPlaceable, iconX, iconY, indicatorRipplePlaceable, rippleX, rippleY, (Placeable.PlacementScope) obj);
                        }
                    }, 4, null);
                }

                static final Unit placeLabelAndStartIcon_nru01g4$lambda$38(Placeable $indicatorPlaceable, int $indicatorX, int $indicatorY, Placeable $labelPlaceable, int $labelX, int $labelY, Placeable $iconPlaceable, int $iconX, int $iconY, Placeable $indicatorRipplePlaceable, int $rippleX, int $rippleY, Placeable.PlacementScope $this$layout) {
                    Placeable.PlacementScope.placeRelative$default($this$layout, $indicatorPlaceable, $indicatorX, $indicatorY, 0.0f, 4, null);
                    Placeable.PlacementScope.placeRelative$default($this$layout, $labelPlaceable, $labelX, $labelY, 0.0f, 4, null);
                    Placeable.PlacementScope.placeRelative$default($this$layout, $iconPlaceable, $iconX, $iconY, 0.0f, 4, null);
                    Placeable.PlacementScope.placeRelative$default($this$layout, $indicatorRipplePlaceable, $rippleX, $rippleY, 0.0f, 4, null);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                /* JADX WARN: Code duplicated, block: B:13:0x00bc  */
                /* JADX INFO: renamed from: placeAnimatedLabelAndIcon-2QYhCQ8, reason: not valid java name */
                public static final MeasureResult m2680placeAnimatedLabelAndIcon2QYhCQ8(MeasureScope $this$placeAnimatedLabelAndIcon_u2d2QYhCQ8, int iconPosition, Function0<Float> function0, final Placeable labelPlaceable, final Placeable iconPlaceable, final Placeable indicatorRipplePlaceable, final Placeable indicatorPlaceable, int topIconIndicatorWidth, long constraints, float topIconIndicatorToLabelVerticalPadding, float topIconIndicatorVerticalPadding, float topIconIndicatorHorizontalPadding, float startIconIndicatorHorizontalPadding, float startIconIndicatorVerticalPadding, float startIconToLabelHorizontalPadding, float itemHorizontalPadding) {
                    int indicatorX;
                    final float iconPositionProgress = function0.invoke().floatValue();
                    boolean isIconPositionTop = NavigationItemIconPosition.m2665equalsimpl0(iconPosition, NavigationItemIconPosition.INSTANCE.m2670getTopxw1Ddg());
                    int width = labelPlaceable.getWidth();
                    int other$iv = $this$placeAnimatedLabelAndIcon_u2d2QYhCQ8.mo400roundToPx0680j_4(Dp.m7905constructorimpl(2 * itemHorizontalPadding));
                    int widthTopIcon = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, Math.max(width, topIconIndicatorWidth + other$iv));
                    int widthStartIcon = ConstraintsKt.m7875constrainWidthK40F9xA(constraints, indicatorRipplePlaceable.getWidth() + $this$placeAnimatedLabelAndIcon_u2d2QYhCQ8.mo400roundToPx0680j_4(itemHorizontalPadding));
                    float width2 = widthTopIcon + ((widthStartIcon - widthTopIcon) * iconPositionProgress);
                    int heightTopIcon = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, MathKt.roundToInt(indicatorRipplePlaceable.getHeight() + $this$placeAnimatedLabelAndIcon_u2d2QYhCQ8.mo406toPx0680j_4(topIconIndicatorToLabelVerticalPadding) + labelPlaceable.getHeight()));
                    int heightStartIcon = ConstraintsKt.m7874constrainHeightK40F9xA(constraints, indicatorRipplePlaceable.getHeight());
                    int height = MathHelpersKt.lerp(heightTopIcon, heightStartIcon, iconPositionProgress);
                    final int rippleX = $this$placeAnimatedLabelAndIcon_u2d2QYhCQ8.mo400roundToPx0680j_4(itemHorizontalPadding);
                    int indicatorXTopIcon = MathKt.roundToInt((width2 - indicatorPlaceable.getWidth()) / 2.0f);
                    int indicatorXStartIcon = MathKt.roundToInt(((rippleX + width2) - indicatorPlaceable.getWidth()) / 2.0f);
                    if (iconPositionProgress == 0.0f) {
                        indicatorX = MathHelpersKt.lerp(indicatorXTopIcon, indicatorXStartIcon, iconPositionProgress);
                    } else {
                        if (iconPositionProgress == 1.0f) {
                            indicatorX = MathHelpersKt.lerp(indicatorXTopIcon, indicatorXStartIcon, iconPositionProgress);
                        } else {
                            indicatorX = $this$placeAnimatedLabelAndIcon_u2d2QYhCQ8.mo400roundToPx0680j_4(itemHorizontalPadding);
                        }
                    }
                    int iconXTopIcon = rippleX + $this$placeAnimatedLabelAndIcon_u2d2QYhCQ8.mo400roundToPx0680j_4(topIconIndicatorHorizontalPadding);
                    int iconXStartIcon = rippleX + $this$placeAnimatedLabelAndIcon_u2d2QYhCQ8.mo400roundToPx0680j_4(startIconIndicatorHorizontalPadding);
                    int iconYTopIcon = $this$placeAnimatedLabelAndIcon_u2d2QYhCQ8.mo400roundToPx0680j_4(topIconIndicatorVerticalPadding);
                    int iconYStartIcon = $this$placeAnimatedLabelAndIcon_u2d2QYhCQ8.mo400roundToPx0680j_4(startIconIndicatorVerticalPadding);
                    final int indicatorXStartIcon2 = indicatorX;
                    final int iconX = MathHelpersKt.lerp(iconXTopIcon, iconXStartIcon, iconPositionProgress);
                    final int iconY = MathHelpersKt.lerp(iconYTopIcon, iconYStartIcon, iconPositionProgress);
                    int width3 = iconPlaceable.getWidth();
                    float arg0$iv = Dp.m7905constructorimpl(2 * topIconIndicatorHorizontalPadding);
                    float other$iv2 = Dp.m7905constructorimpl(2 * itemHorizontalPadding);
                    int labelXTopIcon = ((width3 + $this$placeAnimatedLabelAndIcon_u2d2QYhCQ8.mo400roundToPx0680j_4(Dp.m7905constructorimpl(arg0$iv + other$iv2))) - labelPlaceable.getWidth()) / 2;
                    int labelYTopIcon = iconY + iconPlaceable.getHeight() + $this$placeAnimatedLabelAndIcon_u2d2QYhCQ8.mo400roundToPx0680j_4(Dp.m7905constructorimpl(topIconIndicatorToLabelVerticalPadding + topIconIndicatorToLabelVerticalPadding));
                    float labelXStartIconHorizontalOffset = (!isIconPositionTop || iconPositionProgress <= 0.0f) ? $this$placeAnimatedLabelAndIcon_u2d2QYhCQ8.mo400roundToPx0680j_4(itemHorizontalPadding) * (1.0f - iconPositionProgress) : 0.0f;
                    float labelXStartIcon = ((iconX + iconPlaceable.getWidth()) + $this$placeAnimatedLabelAndIcon_u2d2QYhCQ8.mo400roundToPx0680j_4(startIconToLabelHorizontalPadding)) - labelXStartIconHorizontalOffset;
                    int labelYStartIcon = (height - labelPlaceable.getHeight()) / 2;
                    final Object labelX = iconPositionProgress < 0.5f ? Integer.valueOf(labelXTopIcon) : Float.valueOf(labelXStartIcon * iconPositionProgress);
                    final int labelY = iconPositionProgress < 0.5f ? labelYTopIcon : labelYStartIcon;
                    return MeasureScope.layout$default($this$placeAnimatedLabelAndIcon_u2d2QYhCQ8, MathKt.roundToInt(width2), height, null, new Function1() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationItemKt.placeAnimatedLabelAndIcon_2QYhCQ8$lambda$40(indicatorPlaceable, indicatorXStartIcon2, iconPlaceable, iconX, iconY, labelPlaceable, labelX, labelY, indicatorRipplePlaceable, rippleX, iconPositionProgress, (Placeable.PlacementScope) obj);
                        }
                    }, 4, null);
                }

                static final Unit placeAnimatedLabelAndIcon_2QYhCQ8$lambda$40(Placeable $indicatorPlaceable, int $indicatorX, Placeable $iconPlaceable, int $iconX, int $iconY, Placeable $labelPlaceable, Object $labelX, int $labelY, Placeable $indicatorRipplePlaceable, int $rippleX, final float $iconPositionProgress, Placeable.PlacementScope $this$layout) {
                    Placeable.PlacementScope.placeRelativeWithLayer$default($this$layout, $indicatorPlaceable, $indicatorX, 0, 0.0f, (Function1) null, 12, (Object) null);
                    Placeable.PlacementScope.placeRelativeWithLayer$default($this$layout, $iconPlaceable, $iconX, $iconY, 0.0f, (Function1) null, 12, (Object) null);
                    Placeable.PlacementScope.placeRelativeWithLayer$default($this$layout, $labelPlaceable, ((Number) $labelX).intValue(), $labelY, 0.0f, new Function1() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationItemKt.placeAnimatedLabelAndIcon_2QYhCQ8$lambda$40$lambda$39($iconPositionProgress, (GraphicsLayerScope) obj);
                        }
                    }, 4, (Object) null);
                    Placeable.PlacementScope.placeRelativeWithLayer$default($this$layout, $indicatorRipplePlaceable, $rippleX, 0, 0.0f, (Function1) null, 12, (Object) null);
                    return Unit.INSTANCE;
                }

                static final Unit placeAnimatedLabelAndIcon_2QYhCQ8$lambda$40$lambda$39(float $iconPositionProgress, GraphicsLayerScope $this$placeRelativeWithLayer) {
                    $this$placeRelativeWithLayer.setAlpha(4.0f * ($iconPositionProgress - 0.5f) * ($iconPositionProgress - 0.5f));
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void StyledLabel(final boolean selected, final TextStyle labelTextStyle, final NavigationItemColors colors, final boolean enabled, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
                    Function2<? super Composer, ? super Integer, Unit> function3;
                    Composer $composer2 = $composer.startRestartGroup(-2136267443);
                    ComposerKt.sourceInformation($composer2, "C(StyledLabel)N(selected,labelTextStyle,colors,enabled,content)1085@45893L132:NavigationItem.kt#uh7d8r");
                    int $dirty = $changed;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer2.changed(selected) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty |= $composer2.changed(labelTextStyle) ? 32 : 16;
                    }
                    if (($changed & 384) == 0) {
                        $dirty |= $composer2.changed(colors) ? 256 : 128;
                    }
                    if (($changed & 3072) == 0) {
                        $dirty |= $composer2.changed(enabled) ? 2048 : 1024;
                    }
                    if (($changed & 24576) == 0) {
                        function3 = function2;
                        $dirty |= $composer2.changedInstance(function3) ? 16384 : 8192;
                    } else {
                        function3 = function2;
                    }
                    int $dirty2 = $dirty;
                    if (!$composer2.shouldExecute(($dirty2 & 9363) != 9362, $dirty2 & 1)) {
                        $composer2.skipToGroupEnd();
                    } else {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2136267443, $dirty2, -1, "androidx.compose.material3.StyledLabel (NavigationItem.kt:1083)");
                        }
                        long textColor = colors.m2661textColorWaAFU9c(selected, enabled);
                        ProvideContentColorTextStyleKt.m3367ProvideContentColorTextStyle3JVO9M(textColor, labelTextStyle, function3, $composer2, ($dirty2 & 112) | (($dirty2 >> 6) & 896));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda18
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationItemKt.StyledLabel$lambda$41(selected, labelTextStyle, colors, enabled, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }

                private static final State<Float> animateIndicatorProgressAsState(boolean selected, Composer $composer, int $changed) {
                    ComposerKt.sourceInformationMarkerStart($composer, -1105658511, "C(animateIndicatorProgressAsState)N(selected)1097@46316L7,1094@46110L220:NavigationItem.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1105658511, $changed, -1, "androidx.compose.material3.animateIndicatorProgressAsState (NavigationItem.kt:1094)");
                    }
                    State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(selected ? 1.0f : 0.0f, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer, 6), 0.0f, null, null, $composer, 0, 28);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    return stateAnimateFloatAsState;
                }

                private static final void IndicatorRipple(final InteractionSource interactionSource, final Shape indicatorShape, Composer $composer, final int $changed) {
                    Composer $composer2 = $composer.startRestartGroup(-629069867);
                    ComposerKt.sourceInformation($composer2, "C(IndicatorRipple)N(interactionSource,indicatorShape)1102@46439L151:NavigationItem.kt#uh7d8r");
                    int $dirty = $changed;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer2.changed(interactionSource) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty |= $composer2.changed(indicatorShape) ? 32 : 16;
                    }
                    if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                        $composer2.skipToGroupEnd();
                    } else {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-629069867, $dirty, -1, "androidx.compose.material3.IndicatorRipple (NavigationItem.kt:1101)");
                        }
                        BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, IndicatorRippleLayoutIdTag), indicatorShape), interactionSource, RippleKt.m2762rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), $composer2, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda7
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationItemKt.IndicatorRipple$lambda$42(interactionSource, indicatorShape, $changed, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }

                /* JADX INFO: renamed from: Indicator-3J-VO9M, reason: not valid java name */
                private static final void m2673Indicator3JVO9M(final long indicatorColor, final Shape indicatorShape, final Function0<Float> function0, Composer $composer, final int $changed) {
                    Composer $composer2 = $composer.startRestartGroup(-273382589);
                    ComposerKt.sourceInformation($composer2, "C(Indicator)N(indicatorColor:c#ui.graphics.Color,indicatorShape,indicatorAnimationProgress)1117@46816L40,1115@46736L198:NavigationItem.kt#uh7d8r");
                    int $dirty = $changed;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer2.changed(indicatorColor) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty |= $composer2.changed(indicatorShape) ? 32 : 16;
                    }
                    if (($changed & 384) == 0) {
                        $dirty |= $composer2.changedInstance(function0) ? 256 : 128;
                    }
                    if (!$composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                        $composer2.skipToGroupEnd();
                    } else {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-273382589, $dirty, -1, "androidx.compose.material3.Indicator (NavigationItem.kt:1114)");
                        }
                        Modifier modifierLayoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, IndicatorLayoutIdTag);
                        ComposerKt.sourceInformationMarkerStart($composer2, -727655829, "CC(remember):NavigationItem.kt#9igjgp");
                        boolean invalid$iv = ($dirty & 896) == 256;
                        Object it$iv = $composer2.rememberedValue();
                        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                            Object value$iv = new Function1() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return NavigationItemKt.Indicator_3J_VO9M$lambda$44$lambda$43(function0, (GraphicsLayerScope) obj);
                                }
                            };
                            $composer2.updateRememberedValue(value$iv);
                            it$iv = value$iv;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        BoxKt.Box(BackgroundKt.m264backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(modifierLayoutId, (Function1) it$iv), indicatorColor, indicatorShape), $composer2, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return NavigationItemKt.Indicator_3J_VO9M$lambda$45(indicatorColor, indicatorShape, function0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }

                static final Unit Indicator_3J_VO9M$lambda$44$lambda$43(Function0 $indicatorAnimationProgress, GraphicsLayerScope $this$graphicsLayer) {
                    $this$graphicsLayer.setAlpha(((Number) $indicatorAnimationProgress.invoke()).floatValue());
                    return Unit.INSTANCE;
                }
            }
