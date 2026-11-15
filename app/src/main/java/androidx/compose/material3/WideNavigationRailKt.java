package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.material3.internal.AnchoredDraggableKt;
import androidx.compose.material3.internal.AnchoredDraggableState;
import androidx.compose.material3.internal.DraggableAnchorsConfig;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.NavigationRailBaselineItemTokens;
import androidx.compose.material3.tokens.NavigationRailCollapsedTokens;
import androidx.compose.material3.tokens.NavigationRailExpandedTokens;
import androidx.compose.material3.tokens.NavigationRailHorizontalItemTokens;
import androidx.compose.material3.tokens.NavigationRailVerticalItemTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
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
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.profileinstaller.ProfileVerifier;
import com.cloudinary.metadata.MetadataField;
import com.facebook.common.util.UriUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: WideNavigationRail.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000Ê\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\u001as\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\u0010\u0012\u001am\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\fH\u0003¢\u0006\u0002\u0010\u0017\u001a\u009d\u0001\u0010\u0018\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u00152\b\b\u0002\u0010\u001a\u001a\u00020\u00072\b\b\u0002\u0010\u001b\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\fH\u0007¢\u0006\u0004\b \u0010!\u001a\u0089\u0001\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u00152\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0011\u0010%\u001a\r\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\f2\u0013\u0010&\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0006\u0010'\u001a\u00020\u00152\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010(\u001a\u00020\u00152\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010\b\u001a\u00020+2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-H\u0007¢\u0006\u0004\b.\u0010/\u001aÖ\u0001\u00100\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u00101\u001a\u00020\u00152\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u000205032\u0006\u00106\u001a\u00020721\u00108\u001a-\b\u0001\u0012\u0013\u0012\u001104¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010=\u0012\u0006\u0012\u0004\u0018\u00010>092\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010?\u001a\u00020@2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010A\u001a\u00020\u001d2\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010B\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00102\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\fH\u0003¢\u0006\u0004\bC\u0010D\u001a\u001c\u0010E\u001a\u000204*\u00020F2\u0006\u0010G\u001a\u0002042\u0006\u0010H\u001a\u00020\u0015H\u0002\u001a\u0014\u0010I\u001a\u000204*\u00020F2\u0006\u0010G\u001a\u000204H\u0002\u001a=\u0010J\u001a\u00020\u00012\u0006\u0010K\u001a\u00020L2\u001c\u0010M\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010=\u0012\u0006\u0012\u0004\u0018\u00010>0N2\u0006\u0010O\u001a\u00020\u0015H\u0003¢\u0006\u0004\bP\u0010Q\"\u0016\u0010R\u001a\u00020\u001dX\u0080\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\bS\u0010T\"\u0010\u0010V\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010W\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010X\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010Y\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010Z\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010[\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010\\\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010]\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010^\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010_\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010`\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010a\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u000e\u0010b\u001a\u000204X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010c\u001a\u00020dX\u0082T¢\u0006\u0002\n\u0000\"\u001a\u0010e\u001a\b\u0012\u0004\u0012\u00020g0fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010i\"\u001a\u0010j\u001a\b\u0012\u0004\u0012\u00020k0fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bl\u0010i¨\u0006m²\u0006\n\u0010n\u001a\u00020oX\u008a\u008e\u0002²\u0006\n\u0010p\u001a\u00020oX\u008a\u008e\u0002²\u0006\n\u0010q\u001a\u00020\u001dX\u008a\u0084\u0002²\u0006\n\u0010r\u001a\u00020\u001dX\u008a\u0084\u0002²\u0006\n\u0010s\u001a\u00020\u001dX\u008a\u0084\u0002²\u0006\n\u0010t\u001a\u00020\u001dX\u008a\u0084\u0002²\u0006\n\u0010u\u001a\u000204X\u008a\u0084\u0002²\u0006\n\u0010v\u001a\u00020\u0015X\u008a\u008e\u0002"}, d2 = {"WideNavigationRail", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/material3/WideNavigationRailState;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/WideNavigationRailColors;", WideNavigationRailKt.HeaderLayoutIdTag, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "arrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", UriUtil.LOCAL_CONTENT_SCHEME, "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/WideNavigationRailState;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/WideNavigationRailColors;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/foundation/layout/Arrangement$Vertical;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "WideNavigationRailLayout", "isModal", "", "expanded", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/material3/WideNavigationRailColors;Landroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/foundation/layout/Arrangement$Vertical;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "ModalWideNavigationRail", "hideOnCollapse", "collapsedShape", "expandedShape", "expandedHeaderTopPadding", "Landroidx/compose/ui/unit/Dp;", "expandedProperties", "Landroidx/compose/material3/ModalWideNavigationRailProperties;", "ModalWideNavigationRail-k3FuEkE", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/WideNavigationRailState;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/WideNavigationRailColors;Lkotlin/jvm/functions/Function2;FLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/material3/ModalWideNavigationRailProperties;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "WideNavigationRailItem", "selected", "onClick", "icon", MetadataField.LABEL, "railExpanded", "enabled", "iconPosition", "Landroidx/compose/material3/NavigationItemIconPosition;", "Landroidx/compose/material3/NavigationItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "WideNavigationRailItem-pli-t6k", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/Modifier;ZILandroidx/compose/material3/NavigationItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "ModalWideNavigationRailContent", "isStandaloneModal", "predictiveBackProgress", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "predictiveBackState", "Landroidx/compose/material3/RailPredictiveBackState;", "settleToDismiss", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "velocity", "Lkotlin/coroutines/Continuation;", "", "railState", "Landroidx/compose/material3/ModalWideNavigationRailState;", "openModalRailMaxWidth", "gesturesEnabled", "ModalWideNavigationRailContent-pU6N4AM", "(ZZLandroidx/compose/animation/core/Animatable;Landroidx/compose/material3/RailPredictiveBackState;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/ModalWideNavigationRailState;Landroidx/compose/material3/WideNavigationRailColors;Landroidx/compose/ui/graphics/Shape;FLkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "calculatePredictiveBackScaleX", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "progress", "swipeEdgeMatchesRail", "calculatePredictiveBackScaleY", "Scrim", "color", "Landroidx/compose/ui/graphics/Color;", "onDismissRequest", "Lkotlin/Function1;", "visible", "Scrim-3J-VO9M", "(JLkotlin/jvm/functions/Function1;ZLandroidx/compose/runtime/Composer;I)V", "WNRItemNoLabelIndicatorPadding", "getWNRItemNoLabelIndicatorPadding", "()F", "F", "ItemHorizontalPadding", "WNRVerticalPadding", "WNRHeaderPadding", "CollapsedRailWidth", "ExpandedRailMinWidth", "ExpandedRailMaxWidth", "TopIconItemMinHeight", "ItemTopIconIndicatorVerticalPadding", "ItemTopIconIndicatorHorizontalPadding", "ItemStartIconIndicatorVerticalPadding", "PredictiveBackMaxScaleXDistance", "PredictiveBackMaxScaleYDistance", "PredictiveBackPivotFractionY", "HeaderLayoutIdTag", "", "LocalWideNavigationRailOverride", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material3/WideNavigationRailOverride;", "getLocalWideNavigationRailOverride", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalModalWideNavigationRailOverride", "Landroidx/compose/material3/ModalWideNavigationRailOverride;", "getLocalModalWideNavigationRailOverride", "material3", "currentWidth", "", "actualMaxExpandedWidth", "minWidth", "widthFullRange", "itemVerticalSpacedBy", "itemMinHeight", "alpha", "dismiss"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class WideNavigationRailKt {
    private static final float CollapsedRailWidth;
    private static final float ExpandedRailMaxWidth;
    private static final float ExpandedRailMinWidth;
    private static final String HeaderLayoutIdTag = "header";
    private static final float ItemHorizontalPadding;
    private static final float ItemStartIconIndicatorVerticalPadding;
    private static final float ItemTopIconIndicatorHorizontalPadding;
    private static final float ItemTopIconIndicatorVerticalPadding;
    private static final ProvidableCompositionLocal<ModalWideNavigationRailOverride> LocalModalWideNavigationRailOverride;
    private static final ProvidableCompositionLocal<WideNavigationRailOverride> LocalWideNavigationRailOverride;
    private static final float PredictiveBackMaxScaleXDistance;
    private static final float PredictiveBackMaxScaleYDistance;
    private static final float PredictiveBackPivotFractionY = 0.5f;
    private static final float TopIconItemMinHeight;
    private static final float WNRHeaderPadding;
    private static final float WNRItemNoLabelIndicatorPadding;
    private static final float WNRVerticalPadding;

    static final Unit ModalWideNavigationRailContent_pU6N4AM$lambda$25(boolean z, boolean z2, Animatable animatable, RailPredictiveBackState railPredictiveBackState, Function2 function2, Modifier modifier, ModalWideNavigationRailState modalWideNavigationRailState, WideNavigationRailColors wideNavigationRailColors, Shape shape, float f, Function2 function3, WindowInsets windowInsets, boolean z3, Arrangement.Vertical vertical, Function2 function4, int i, int i2, Composer composer, int i3) {
        m3312ModalWideNavigationRailContentpU6N4AM(z, z2, animatable, railPredictiveBackState, function2, modifier, modalWideNavigationRailState, wideNavigationRailColors, shape, f, function3, windowInsets, z3, vertical, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    static final Unit ModalWideNavigationRail_k3FuEkE$lambda$14(Modifier modifier, WideNavigationRailState wideNavigationRailState, boolean z, Shape shape, Shape shape2, WideNavigationRailColors wideNavigationRailColors, Function2 function2, float f, WindowInsets windowInsets, Arrangement.Vertical vertical, ModalWideNavigationRailProperties modalWideNavigationRailProperties, Function2 function3, int i, int i2, int i3, Composer composer, int i4) {
        m3311ModalWideNavigationRailk3FuEkE(modifier, wideNavigationRailState, z, shape, shape2, wideNavigationRailColors, function2, f, windowInsets, vertical, modalWideNavigationRailProperties, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit Scrim_3J_VO9M$lambda$37(long j, Function1 function1, boolean z, int i, Composer composer, int i2) {
        m3313Scrim3JVO9M(j, function1, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit WideNavigationRail$lambda$1(Modifier modifier, WideNavigationRailState wideNavigationRailState, Shape shape, WideNavigationRailColors wideNavigationRailColors, Function2 function2, WindowInsets windowInsets, Arrangement.Vertical vertical, Function2 function3, int i, int i2, Composer composer, int i3) {
        WideNavigationRail(modifier, wideNavigationRailState, shape, wideNavigationRailColors, function2, windowInsets, vertical, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit WideNavigationRailItem_pli_t6k$lambda$16(boolean z, Function0 function0, Function2 function2, Function2 function3, boolean z2, Modifier modifier, boolean z3, int i, NavigationItemColors navigationItemColors, MutableInteractionSource mutableInteractionSource, int i2, int i3, Composer composer, int i4) {
        m3314WideNavigationRailItemplit6k(z, function0, function2, function3, z2, modifier, z3, i, navigationItemColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit WideNavigationRailLayout$lambda$12(Modifier modifier, boolean z, boolean z2, WideNavigationRailColors wideNavigationRailColors, Shape shape, Function2 function2, WindowInsets windowInsets, Arrangement.Vertical vertical, Function2 function3, int i, Composer composer, int i2) {
        WideNavigationRailLayout(modifier, z, z2, wideNavigationRailColors, shape, function2, windowInsets, vertical, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x004c  */
    public static final void WideNavigationRail(Modifier modifier, WideNavigationRailState state, Shape shape, WideNavigationRailColors colors, Function2<? super Composer, ? super Integer, Unit> function2, WindowInsets windowInsets, Arrangement.Vertical arrangement, final Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        WideNavigationRailColors colors2;
        Function2<? super Composer, ? super Integer, Unit> function4;
        WindowInsets windowInsets2;
        Arrangement.Vertical vertical;
        Function2<? super Composer, ? super Integer, Unit> function5;
        final Modifier modifier3;
        final Shape shape3;
        final WideNavigationRailColors colors3;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        final WindowInsets windowInsets3;
        final Arrangement.Vertical arrangement2;
        final WideNavigationRailState state2;
        Modifier.Companion modifier4;
        WideNavigationRailState state3;
        WideNavigationRailState state4;
        Shape shape4;
        WideNavigationRailColors colors4;
        Function2<? super Composer, ? super Integer, Unit> function7;
        WindowInsets windowInsets4;
        Arrangement.Vertical arrangement3;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(164193188);
        ComposerKt.sourceInformation($composer2, "C(WideNavigationRail)N(modifier,state,shape,colors,header,windowInsets,arrangement,content)170@8485L7,*181@8852L20:WideNavigationRail.kt#uh7d8r");
        int $dirty = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) != 0) {
                i2 = 16;
            } else {
                if (($changed & 64) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state)) {
                    i2 = 32;
                } else {
                    i2 = 16;
                }
            }
            $dirty |= i2;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                shape2 = shape;
                int i4 = $composer2.changed(shape2) ? 256 : 128;
                $dirty |= i4;
            } else {
                shape2 = shape;
            }
            $dirty |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                colors2 = colors;
                int i5 = $composer2.changed(colors2) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                colors2 = colors;
            }
            $dirty |= i5;
        } else {
            colors2 = colors;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            function4 = function2;
        } else if (($changed & 24576) == 0) {
            function4 = function2;
            $dirty |= $composer2.changedInstance(function4) ? 16384 : 8192;
        } else {
            function4 = function2;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                int i7 = $composer2.changed(windowInsets2) ? 131072 : 65536;
                $dirty |= i7;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty |= i7;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                vertical = arrangement;
                int i8 = $composer2.changed(vertical) ? 1048576 : 524288;
                $dirty |= i8;
            } else {
                vertical = arrangement;
            }
            $dirty |= i8;
        } else {
            vertical = arrangement;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
            function5 = function3;
        } else if (($changed & 12582912) == 0) {
            function5 = function3;
            $dirty |= $composer2.changedInstance(function5) ? 8388608 : 4194304;
        } else {
            function5 = function3;
        }
        if ($composer2.shouldExecute((4793491 & $dirty) != 4793490, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "162@8039L33,163@8120L5,164@8193L8,166@8309L12");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                }
                state4 = state;
                modifier4 = modifier2;
                shape4 = shape2;
                colors4 = colors2;
                function7 = function4;
                windowInsets4 = windowInsets2;
                arrangement3 = vertical;
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 2) == 0) {
                    state3 = state;
                } else {
                    state3 = WideNavigationRailStateKt.rememberWideNavigationRailState(null, $composer2, 0, 1);
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    shape2 = WideNavigationRailDefaults.INSTANCE.getShape($composer2, 6);
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    colors2 = WideNavigationRailDefaults.INSTANCE.colors($composer2, 6);
                }
                if (i6 != 0) {
                    function4 = null;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    windowInsets2 = WideNavigationRailDefaults.INSTANCE.getWindowInsets($composer2, 6);
                }
                if ((i & 64) == 0) {
                    state4 = state3;
                    shape4 = shape2;
                    colors4 = colors2;
                    function7 = function4;
                    windowInsets4 = windowInsets2;
                    arrangement3 = vertical;
                } else {
                    $dirty &= -3670017;
                    state4 = state3;
                    arrangement3 = WideNavigationRailDefaults.INSTANCE.getArrangement();
                    shape4 = shape2;
                    colors4 = colors2;
                    function7 = function4;
                    windowInsets4 = windowInsets2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(164193188, $dirty, -1, "androidx.compose.material3.WideNavigationRail (WideNavigationRail.kt:169)");
            }
            ProvidableCompositionLocal<WideNavigationRailOverride> providableCompositionLocal = LocalWideNavigationRailOverride;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(providableCompositionLocal);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            WideNavigationRailOverride $this$WideNavigationRail_u24lambda_u240 = (WideNavigationRailOverride) objConsume;
            Modifier modifier5 = modifier4;
            $this$WideNavigationRail_u24lambda_u240.WideNavigationRail(new WideNavigationRailOverrideScope(modifier5, state4, shape4, colors4, function7, windowInsets4, arrangement3, function5), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            state2 = state4;
            shape3 = shape4;
            colors3 = colors4;
            function6 = function7;
            windowInsets3 = windowInsets4;
            arrangement2 = arrangement3;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            shape3 = shape2;
            colors3 = colors2;
            function6 = function4;
            windowInsets3 = windowInsets2;
            arrangement2 = vertical;
            state2 = state;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return WideNavigationRailKt.WideNavigationRail$lambda$1(modifier3, state2, shape3, colors3, function6, windowInsets3, arrangement2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void WideNavigationRailLayout(final Modifier modifier, final boolean isModal, final boolean expanded, final WideNavigationRailColors colors, final Shape shape, final Function2<? super Composer, ? super Integer, Unit> function2, final WindowInsets windowInsets, final Arrangement.Vertical arrangement, final Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        WideNavigationRailColors wideNavigationRailColors;
        Function2<? super Composer, ? super Integer, Unit> function4;
        float minimumA11ySize;
        Composer $composer2 = $composer.startRestartGroup(-1004308036);
        ComposerKt.sourceInformation($composer2, "C(WideNavigationRailLayout)N(modifier,isModal,expanded,colors,shape,header,windowInsets,arrangement,content)219@9930L33,220@9998L33,222@10107L7,229@10373L11,230@10448L11,232@10488L195,237@10718L195,242@10954L170,247@11158L154,257@11558L8819,252@11318L9059:WideNavigationRail.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(isModal) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(expanded) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            wideNavigationRailColors = colors;
            $dirty |= $composer2.changed(wideNavigationRailColors) ? 2048 : 1024;
        } else {
            wideNavigationRailColors = colors;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(shape) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            function4 = function2;
            $dirty |= $composer2.changedInstance(function4) ? 131072 : 65536;
        } else {
            function4 = function2;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer2.changed(windowInsets) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changed(arrangement) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ($composer2.shouldExecute((38347923 & $dirty) != 38347922, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1004308036, $dirty, -1, "androidx.compose.material3.WideNavigationRailLayout (WideNavigationRail.kt:218)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1227630237, "CC(remember):WideNavigationRail.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = SnapshotIntStateKt.mutableIntStateOf(0);
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            MutableIntState currentWidth$delegate = (MutableIntState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1227632413, "CC(remember):WideNavigationRail.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = SnapshotIntStateKt.mutableIntStateOf(0);
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            MutableIntState actualMaxExpandedWidth$delegate = (MutableIntState) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ProvidableCompositionLocal<Dp> localMinimumInteractiveComponentSize = InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localMinimumInteractiveComponentSize);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (!Dp.m7910equalsimpl0(((Dp) objConsume).m7919unboximpl(), Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM())) {
                $composer2.startReplaceGroup(-597931134);
                ComposerKt.sourceInformation($composer2, "225@10219L7");
                ProvidableCompositionLocal<Dp> localMinimumInteractiveComponentSize2 = InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer2.consume(localMinimumInteractiveComponentSize2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                minimumA11ySize = ((Dp) objConsume2).m7919unboximpl();
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-597966102);
                $composer2.endReplaceGroup();
                minimumA11ySize = Dp.m7905constructorimpl(0);
            }
            FiniteAnimationSpec animationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer2, 6);
            FiniteAnimationSpec modalAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, $composer2, 6);
            float minimumA11ySize2 = minimumA11ySize;
            State<Dp> stateM162animateDpAsStateAjpBEmI = AnimateAsStateKt.m162animateDpAsStateAjpBEmI(!expanded ? CollapsedRailWidth : ExpandedRailMinWidth, !isModal ? animationSpec : modalAnimationSpec, null, null, $composer2, 0, 12);
            State<Dp> stateM162animateDpAsStateAjpBEmI2 = AnimateAsStateKt.m162animateDpAsStateAjpBEmI(!expanded ? CollapsedRailWidth : ExpandedRailMaxWidth, !isModal ? animationSpec : modalAnimationSpec, null, null, $composer2, 0, 12);
            State<Dp> stateM162animateDpAsStateAjpBEmI3 = AnimateAsStateKt.m162animateDpAsStateAjpBEmI(!expanded ? NavigationRailCollapsedTokens.INSTANCE.m3921getItemVerticalSpaceD9Ej5fM() : Dp.m7905constructorimpl(0), animationSpec, null, null, $composer2, 0, 12);
            State<Dp> stateM162animateDpAsStateAjpBEmI4 = AnimateAsStateKt.m162animateDpAsStateAjpBEmI(!expanded ? TopIconItemMinHeight : minimumA11ySize2, animationSpec, null, null, $composer2, 0, 12);
            int $dirty2 = $dirty;
            SurfaceKt.m2929SurfaceT9BRK9s(modifier, shape, !isModal ? wideNavigationRailColors.getContainerColor() : wideNavigationRailColors.getModalContainerColor(), !isModal ? wideNavigationRailColors.getContentColor() : wideNavigationRailColors.getModalContentColor(), 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1489314345, true, new AnonymousClass1(windowInsets, function4, expanded, stateM162animateDpAsStateAjpBEmI, minimumA11ySize2, stateM162animateDpAsStateAjpBEmI4, stateM162animateDpAsStateAjpBEmI2, actualMaxExpandedWidth$delegate, currentWidth$delegate, arrangement, stateM162animateDpAsStateAjpBEmI3, function3), $composer2, 54), $composer2, ($dirty2 & 14) | 12582912 | (($dirty2 >> 9) & 112), 112);
            $composer2 = $composer2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return WideNavigationRailKt.WideNavigationRailLayout$lambda$12(modifier, isModal, expanded, colors, shape, function2, windowInsets, arrangement, function3, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int WideNavigationRailLayout$lambda$3(MutableIntState $currentWidth$delegate) {
        MutableIntState $this$getValue$iv = $currentWidth$delegate;
        return $this$getValue$iv.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void WideNavigationRailLayout$lambda$4(MutableIntState $currentWidth$delegate, int i) {
        $currentWidth$delegate.setIntValue(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int WideNavigationRailLayout$lambda$6(MutableIntState $actualMaxExpandedWidth$delegate) {
        MutableIntState $this$getValue$iv = $actualMaxExpandedWidth$delegate;
        return $this$getValue$iv.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void WideNavigationRailLayout$lambda$7(MutableIntState $actualMaxExpandedWidth$delegate, int i) {
        $actualMaxExpandedWidth$delegate.setIntValue(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float WideNavigationRailLayout$lambda$8(State<Dp> state) {
        Object thisObj$iv = state.getValue();
        return ((Dp) thisObj$iv).m7919unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float WideNavigationRailLayout$lambda$9(State<Dp> state) {
        Object thisObj$iv = state.getValue();
        return ((Dp) thisObj$iv).m7919unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float WideNavigationRailLayout$lambda$10(State<Dp> state) {
        Object thisObj$iv = state.getValue();
        return ((Dp) thisObj$iv).m7919unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float WideNavigationRailLayout$lambda$11(State<Dp> state) {
        Object thisObj$iv = state.getValue();
        return ((Dp) thisObj$iv).m7919unboximpl();
    }

    /* JADX INFO: renamed from: androidx.compose.material3.WideNavigationRailKt$WideNavigationRailLayout$1, reason: invalid class name */
    /* JADX INFO: compiled from: WideNavigationRail.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ MutableIntState $actualMaxExpandedWidth$delegate;
        final /* synthetic */ Arrangement.Vertical $arrangement;
        final /* synthetic */ Function2<Composer, Integer, Unit> $content;
        final /* synthetic */ MutableIntState $currentWidth$delegate;
        final /* synthetic */ boolean $expanded;
        final /* synthetic */ Function2<Composer, Integer, Unit> $header;
        final /* synthetic */ State<Dp> $itemMinHeight$delegate;
        final /* synthetic */ State<Dp> $itemVerticalSpacedBy$delegate;
        final /* synthetic */ State<Dp> $minWidth$delegate;
        final /* synthetic */ float $minimumA11ySize;
        final /* synthetic */ State<Dp> $widthFullRange$delegate;
        final /* synthetic */ WindowInsets $windowInsets;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(WindowInsets windowInsets, Function2<? super Composer, ? super Integer, Unit> function2, boolean z, State<Dp> state, float f, State<Dp> state2, State<Dp> state3, MutableIntState mutableIntState, MutableIntState mutableIntState2, Arrangement.Vertical vertical, State<Dp> state4, Function2<? super Composer, ? super Integer, Unit> function3) {
            this.$windowInsets = windowInsets;
            this.$header = function2;
            this.$expanded = z;
            this.$minWidth$delegate = state;
            this.$minimumA11ySize = f;
            this.$itemMinHeight$delegate = state2;
            this.$widthFullRange$delegate = state3;
            this.$actualMaxExpandedWidth$delegate = mutableIntState;
            this.$currentWidth$delegate = mutableIntState2;
            this.$arrangement = vertical;
            this.$itemVerticalSpacedBy$delegate = state4;
            this.$content = function3;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        static final Unit invoke$lambda$1$lambda$0(SemanticsPropertyReceiver $this$semantics) {
            SemanticsPropertiesKt.setTraversalGroup($this$semantics, true);
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            ComposerKt.sourceInformation($composer, "C265@11877L27,258@11568L8803:WideNavigationRail.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1489314345, $changed, -1, "androidx.compose.material3.WideNavigationRailLayout.<anonymous> (WideNavigationRail.kt:258)");
            }
            Modifier modifierSelectableGroup = SelectableGroupKt.selectableGroup(PaddingKt.m823paddingqDBjuR0$default(SizeKt.m872widthInVpY3zN4$default(WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null), this.$windowInsets), 0.0f, WideNavigationRailKt.ExpandedRailMaxWidth, 1, null), 0.0f, WideNavigationRailKt.WNRVerticalPadding, 0.0f, 0.0f, 13, null));
            ComposerKt.sourceInformationMarkerStart($composer, -1006965742, "CC(remember):WideNavigationRail.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.WideNavigationRailKt$WideNavigationRailLayout$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return WideNavigationRailKt.AnonymousClass1.invoke$lambda$1$lambda$0((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierSelectableGroup, false, (Function1) it$iv, 1, null);
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$header, this.$expanded, this.$minWidth$delegate, this.$minimumA11ySize, this.$itemMinHeight$delegate, this.$widthFullRange$delegate, this.$actualMaxExpandedWidth$delegate, this.$currentWidth$delegate, this.$arrangement, this.$itemVerticalSpacedBy$delegate);
            Function2<Composer, Integer, Unit> function2 = this.$header;
            Function2<Composer, Integer, Unit> function3 = this.$content;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer, modifierSemantics$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = ((0 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, anonymousClass2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1714873591, "C270@12077L9:WideNavigationRail.kt#uh7d8r");
            if (function2 != null) {
                $composer.startReplaceGroup(1714892004);
                ComposerKt.sourceInformation($composer, "268@11988L54");
                Modifier modifier$iv = LayoutIdKt.layoutId(Modifier.INSTANCE, WideNavigationRailKt.HeaderLayoutIdTag);
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor2;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor2;
                    $composer.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i3 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 310577628, "C268@12032L8:WideNavigationRail.kt#uh7d8r");
                function2.invoke($composer, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(1714982338);
                $composer.endReplaceGroup();
            }
            function3.invoke($composer, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.WideNavigationRailKt$WideNavigationRailLayout$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: WideNavigationRail.kt */
        @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"androidx/compose/material3/WideNavigationRailKt$WideNavigationRailLayout$1$2", "Landroidx/compose/ui/layout/MeasurePolicy;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class AnonymousClass2 implements MeasurePolicy {
            final /* synthetic */ MutableIntState $actualMaxExpandedWidth$delegate;
            final /* synthetic */ Arrangement.Vertical $arrangement;
            final /* synthetic */ MutableIntState $currentWidth$delegate;
            final /* synthetic */ boolean $expanded;
            final /* synthetic */ Function2<Composer, Integer, Unit> $header;
            final /* synthetic */ State<Dp> $itemMinHeight$delegate;
            final /* synthetic */ State<Dp> $itemVerticalSpacedBy$delegate;
            final /* synthetic */ State<Dp> $minWidth$delegate;
            final /* synthetic */ float $minimumA11ySize;
            final /* synthetic */ State<Dp> $widthFullRange$delegate;

            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(Function2<? super Composer, ? super Integer, Unit> function2, boolean $expanded, State<Dp> state, float $minimumA11ySize, State<Dp> state2, State<Dp> state3, MutableIntState $actualMaxExpandedWidth$delegate, MutableIntState $currentWidth$delegate, Arrangement.Vertical $arrangement, State<Dp> state4) {
                this.$header = function2;
                this.$expanded = $expanded;
                this.$minWidth$delegate = state;
                this.$minimumA11ySize = $minimumA11ySize;
                this.$itemMinHeight$delegate = state2;
                this.$widthFullRange$delegate = state3;
                this.$actualMaxExpandedWidth$delegate = $actualMaxExpandedWidth$delegate;
                this.$currentWidth$delegate = $currentWidth$delegate;
                this.$arrangement = $arrangement;
                this.$itemVerticalSpacedBy$delegate = state4;
            }

            /* JADX WARN: Code duplicated, block: B:56:0x0242  */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r2v18, types: [T, androidx.compose.ui.layout.Placeable] */
            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* JADX INFO: renamed from: measure-3p2s80s */
            public MeasureResult mo42measure3p2s80s(final MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
                int actualExpandedMinWidth;
                int actualExpandedMinWidth2;
                List<? extends Measurable> list2;
                int height;
                int expandedItemMaxWidth;
                int width;
                int height2 = Constraints.m7857getMaxHeightimpl(constraints);
                int itemsCount = list.size();
                int actualExpandedMinWidth3 = Constraints.m7860getMinWidthimpl(constraints);
                if (Constraints.m7860getMinWidthimpl(constraints) == 0) {
                    int actualExpandedMinWidth4 = RangesKt.coerceAtMost($this$measure_u2d3p2s80s.mo400roundToPx0680j_4(WideNavigationRailKt.ExpandedRailMinWidth), Constraints.m7858getMaxWidthimpl(constraints));
                    actualExpandedMinWidth = actualExpandedMinWidth4;
                    actualExpandedMinWidth2 = RangesKt.coerceAtMost($this$measure_u2d3p2s80s.mo400roundToPx0680j_4(WideNavigationRailKt.WideNavigationRailLayout$lambda$8(this.$minWidth$delegate)), Constraints.m7858getMaxWidthimpl(constraints));
                } else {
                    actualExpandedMinWidth = actualExpandedMinWidth3;
                    actualExpandedMinWidth2 = Constraints.m7860getMinWidthimpl(constraints);
                }
                if (itemsCount < 1) {
                    return MeasureScope.layout$default($this$measure_u2d3p2s80s, actualExpandedMinWidth2, height2, null, new Function1() { // from class: androidx.compose.material3.WideNavigationRailKt$WideNavigationRailLayout$1$2$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Unit.INSTANCE;
                        }
                    }, 4, null);
                }
                int actualMinWidth = actualExpandedMinWidth2;
                long looseConstraints = Constraints.m7847copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(constraints) : 0);
                int constraintsOffset = 0;
                final Ref.ObjectRef headerPlaceable = new Ref.ObjectRef();
                if (this.$header != null) {
                    int size = list.size();
                    int index$iv$iv = 0;
                    while (true) {
                        if (index$iv$iv >= size) {
                            ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                            throw new KotlinNothingValueException();
                        }
                        Object item$iv$iv = list.get(index$iv$iv);
                        Measurable it = (Measurable) item$iv$iv;
                        int index$iv$iv2 = index$iv$iv;
                        height = height2;
                        if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), WideNavigationRailKt.HeaderLayoutIdTag)) {
                            headerPlaceable.element = ((Measurable) item$iv$iv).mo6541measureBRTryo0(looseConstraints);
                            List<? extends Measurable> listSubList = itemsCount > 1 ? list.subList(1, itemsCount) : list;
                            itemsCount--;
                            constraintsOffset = ((Placeable) headerPlaceable.element).getHeight();
                            list2 = listSubList;
                            break;
                        }
                        height2 = height;
                        index$iv$iv = index$iv$iv2 + 1;
                    }
                } else {
                    list2 = list;
                    height = height2;
                }
                List itemsPlaceables = itemsCount > 0 ? new ArrayList() : null;
                int itemMaxWidthConstraint = this.$expanded ? Constraints.m7858getMaxWidthimpl(looseConstraints) : actualMinWidth;
                if (itemsPlaceables != null) {
                    float f = this.$minimumA11ySize;
                    boolean z = this.$expanded;
                    State<Dp> state = this.$itemMinHeight$delegate;
                    List<? extends Measurable> list3 = list2;
                    int expandedItemMaxWidth2 = list3.size();
                    ArrayList target$iv = new ArrayList(expandedItemMaxWidth2);
                    List<? extends Measurable> list4 = list3;
                    int size2 = list4.size();
                    int index$iv$iv3 = 0;
                    long looseConstraints2 = looseConstraints;
                    int constraintsOffset2 = constraintsOffset;
                    int expandedItemMaxWidth3 = 0;
                    while (index$iv$iv3 < size2) {
                        int i = size2;
                        ArrayList arrayList = target$iv;
                        List<? extends Measurable> list5 = list4;
                        Measurable it2 = (Measurable) list4.get(index$iv$iv3);
                        int index$iv$iv4 = index$iv$iv3;
                        int index$iv$iv5 = -constraintsOffset2;
                        long looseConstraints3 = looseConstraints2;
                        looseConstraints2 = looseConstraints3;
                        int itemsCount2 = itemsCount;
                        boolean z2 = z;
                        float f2 = f;
                        State<Dp> state2 = state;
                        Placeable measuredItem = it2.mo6541measureBRTryo0(ConstraintsKt.m7873constrainN9IONVI(ConstraintsKt.m7878offsetNN6EwU$default(looseConstraints3, 0, index$iv$iv5, 1, null), Constraints.INSTANCE.m7867fitPrioritizingWidthZbe2FdA($this$measure_u2d3p2s80s.mo400roundToPx0680j_4(f), itemMaxWidthConstraint, $this$measure_u2d3p2s80s.mo400roundToPx0680j_4(WideNavigationRailKt.WideNavigationRailLayout$lambda$11(state)), Constraints.m7857getMaxHeightimpl(looseConstraints2))));
                        int maxItemWidth = measuredItem.getMeasuredWidth();
                        if (z2 && expandedItemMaxWidth3 < maxItemWidth) {
                            expandedItemMaxWidth3 = $this$measure_u2d3p2s80s.mo400roundToPx0680j_4(WideNavigationRailKt.ItemHorizontalPadding) + maxItemWidth;
                        }
                        int constraintsOffset3 = measuredItem.getHeight();
                        arrayList.add(Boolean.valueOf(itemsPlaceables.add(measuredItem)));
                        index$iv$iv3 = index$iv$iv4 + 1;
                        constraintsOffset2 = constraintsOffset3;
                        itemsCount = itemsCount2;
                        z = z2;
                        f = f2;
                        size2 = i;
                        list4 = list5;
                        state = state2;
                    }
                    expandedItemMaxWidth = expandedItemMaxWidth3;
                } else {
                    expandedItemMaxWidth = 0;
                }
                if (this.$expanded) {
                    Placeable placeable = (Placeable) headerPlaceable.element;
                    int widestElementWidth = Math.max(expandedItemMaxWidth, placeable != null ? placeable.getWidth() : 0);
                    if (widestElementWidth <= actualMinWidth || widestElementWidth <= actualExpandedMinWidth) {
                        width = actualMinWidth;
                    } else {
                        int widthConstrain = RangesKt.coerceAtMost(Math.max(widestElementWidth, actualExpandedMinWidth), Constraints.m7858getMaxWidthimpl(constraints));
                        int width2 = RangesKt.coerceAtMost($this$measure_u2d3p2s80s.mo400roundToPx0680j_4(WideNavigationRailKt.WideNavigationRailLayout$lambda$9(this.$widthFullRange$delegate)), widthConstrain);
                        WideNavigationRailKt.WideNavigationRailLayout$lambda$7(this.$actualMaxExpandedWidth$delegate, width2);
                        width = width2;
                    }
                } else if (WideNavigationRailKt.WideNavigationRailLayout$lambda$6(this.$actualMaxExpandedWidth$delegate) > 0) {
                    int width3 = RangesKt.coerceIn($this$measure_u2d3p2s80s.mo400roundToPx0680j_4(WideNavigationRailKt.WideNavigationRailLayout$lambda$9(this.$widthFullRange$delegate)), actualMinWidth, RangesKt.coerceAtLeast(WideNavigationRailKt.WideNavigationRailLayout$lambda$3(this.$currentWidth$delegate), actualMinWidth));
                    width = width3;
                } else {
                    width = actualMinWidth;
                }
                WideNavigationRailKt.WideNavigationRailLayout$lambda$4(this.$currentWidth$delegate, width);
                final Arrangement.Vertical vertical = this.$arrangement;
                final State<Dp> state3 = this.$itemVerticalSpacedBy$delegate;
                final List itemsPlaceables2 = itemsPlaceables;
                final int height3 = height;
                return MeasureScope.layout$default($this$measure_u2d3p2s80s, width, height3, null, new Function1() { // from class: androidx.compose.material3.WideNavigationRailKt$WideNavigationRailLayout$1$2$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return WideNavigationRailKt.AnonymousClass1.AnonymousClass2.measure_3p2s80s$lambda$6(height3, $this$measure_u2d3p2s80s, headerPlaceable, itemsPlaceables2, vertical, state3, (Placeable.PlacementScope) obj);
                    }
                }, 4, null);
            }

            /* JADX WARN: Multi-variable type inference failed */
            static final Unit measure_3p2s80s$lambda$6(int $height, MeasureScope $this_measure, Ref.ObjectRef $headerPlaceable, List $itemsPlaceables, Arrangement.Vertical $arrangement, State $itemVerticalSpacedBy$delegate, Placeable.PlacementScope $this$layout) {
                int layoutSize;
                int railHeight = $height - $this_measure.mo400roundToPx0680j_4(WideNavigationRailKt.WNRVerticalPadding);
                int headerOffset = 0;
                if ($headerPlaceable.element != 0 && ((Placeable) $headerPlaceable.element).getHeight() > 0) {
                    Placeable.PlacementScope.placeRelative$default($this$layout, (Placeable) $headerPlaceable.element, 0, 0, 0.0f, 4, null);
                    headerOffset = 0 + ((Placeable) $headerPlaceable.element).getHeight() + $this_measure.mo400roundToPx0680j_4(WideNavigationRailKt.WNRHeaderPadding);
                }
                if ($itemsPlaceables != null) {
                    if (Intrinsics.areEqual($arrangement, Arrangement.INSTANCE.getCenter())) {
                        layoutSize = railHeight;
                    } else {
                        layoutSize = railHeight - headerOffset;
                    }
                    int[] sizes = new int[$itemsPlaceables.size()];
                    int size = $itemsPlaceables.size();
                    for (int index$iv = 0; index$iv < size; index$iv++) {
                        Object item$iv = $itemsPlaceables.get(index$iv);
                        Placeable item = (Placeable) item$iv;
                        int index = index$iv;
                        sizes[index] = item.getHeight();
                        if (index < $itemsPlaceables.size() - 1) {
                            sizes[index] = sizes[index] + $this_measure.mo400roundToPx0680j_4(WideNavigationRailKt.WideNavigationRailLayout$lambda$10($itemVerticalSpacedBy$delegate));
                        }
                    }
                    int[] y = new int[$itemsPlaceables.size()];
                    $arrangement.arrange($this_measure, layoutSize, sizes, y);
                    int offset = Intrinsics.areEqual($arrangement, Arrangement.INSTANCE.getCenter()) ? 0 : headerOffset;
                    int size2 = $itemsPlaceables.size();
                    for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                        Object item$iv2 = $itemsPlaceables.get(index$iv2);
                        Placeable item2 = (Placeable) item$iv2;
                        Placeable.PlacementScope.placeRelative$default($this$layout, item2, 0, y[index$iv2] + offset, 0.0f, 4, null);
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x004e  */
    /* JADX INFO: renamed from: ModalWideNavigationRail-k3FuEkE, reason: not valid java name */
    public static final void m3311ModalWideNavigationRailk3FuEkE(Modifier modifier, WideNavigationRailState state, boolean hideOnCollapse, Shape collapsedShape, Shape expandedShape, WideNavigationRailColors colors, Function2<? super Composer, ? super Integer, Unit> function2, float expandedHeaderTopPadding, WindowInsets windowInsets, Arrangement.Vertical arrangement, ModalWideNavigationRailProperties expandedProperties, final Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        boolean z;
        Shape shape;
        Shape expandedShape2;
        WideNavigationRailColors wideNavigationRailColors;
        Function2<? super Composer, ? super Integer, Unit> function4;
        final WindowInsets windowInsets2;
        final ModalWideNavigationRailProperties expandedProperties2;
        final Shape expandedShape3;
        final WideNavigationRailColors colors2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function5;
        final boolean hideOnCollapse2;
        final WideNavigationRailState state2;
        final float expandedHeaderTopPadding2;
        final Arrangement.Vertical arrangement2;
        final Shape collapsedShape2;
        Modifier.Companion modifier4;
        WideNavigationRailState state3;
        int $dirty;
        boolean hideOnCollapse3;
        Shape collapsedShape3;
        WideNavigationRailColors colors3;
        Function2<? super Composer, ? super Integer, Unit> function6;
        float expandedHeaderTopPadding3;
        WindowInsets windowInsets3;
        Arrangement.Vertical arrangement3;
        ModalWideNavigationRailProperties expandedProperties3;
        Modifier modifier5;
        WideNavigationRailColors colors4;
        WideNavigationRailState state4;
        int $dirty2;
        Shape expandedShape4;
        Function2<? super Composer, ? super Integer, Unit> function7;
        boolean hideOnCollapse4;
        Shape collapsedShape4;
        float expandedHeaderTopPadding4;
        WindowInsets windowInsets4;
        Arrangement.Vertical arrangement4;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(-38559147);
        ComposerKt.sourceInformation($composer2, "C(ModalWideNavigationRail)N(modifier,state,hideOnCollapse,collapsedShape,expandedShape,colors,header,expandedHeaderTopPadding:c#ui.unit.Dp,windowInsets,arrangement,expandedProperties,content)493@24484L7,*493@24501L25:WideNavigationRail.kt#uh7d8r");
        int $dirty3 = $changed;
        int $dirty1 = $changed1;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty3 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) != 0) {
                i2 = 16;
            } else {
                if (($changed & 64) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state)) {
                    i2 = 32;
                } else {
                    i2 = 16;
                }
            }
            $dirty3 |= i2;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty3 |= 384;
            z = hideOnCollapse;
        } else if (($changed & 384) == 0) {
            z = hideOnCollapse;
            $dirty3 |= $composer2.changed(z) ? 256 : 128;
        } else {
            z = hideOnCollapse;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape = collapsedShape;
                int i5 = $composer2.changed(shape) ? 2048 : 1024;
                $dirty3 |= i5;
            } else {
                shape = collapsedShape;
            }
            $dirty3 |= i5;
        } else {
            shape = collapsedShape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                expandedShape2 = expandedShape;
                int i6 = $composer2.changed(expandedShape2) ? 16384 : 8192;
                $dirty3 |= i6;
            } else {
                expandedShape2 = expandedShape;
            }
            $dirty3 |= i6;
        } else {
            expandedShape2 = expandedShape;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                wideNavigationRailColors = colors;
                int i7 = $composer2.changed(wideNavigationRailColors) ? 131072 : 65536;
                $dirty3 |= i7;
            } else {
                wideNavigationRailColors = colors;
            }
            $dirty3 |= i7;
        } else {
            wideNavigationRailColors = colors;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty3 |= 1572864;
            function4 = function2;
        } else if (($changed & 1572864) == 0) {
            function4 = function2;
            $dirty3 |= $composer2.changedInstance(function4) ? 1048576 : 524288;
        } else {
            function4 = function2;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer2.changed(expandedHeaderTopPadding) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty3 |= ((i & 256) == 0 && $composer2.changed(windowInsets)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty3 |= ((i & 512) == 0 && $composer2.changed(arrangement)) ? 536870912 : 268435456;
        }
        int i10 = i & 1024;
        if (i10 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer2.changed(expandedProperties) ? 4 : 2;
        }
        if ((i & 2048) != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer2.changedInstance(function3) ? 32 : 16;
        }
        int $dirty4 = $dirty3;
        if ($composer2.shouldExecute((($dirty3 & 306783379) == 306783378 && ($dirty1 & 19) == 18) ? false : true, $dirty4 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "465@23180L33,467@23307L19,468@23382L18,469@23468L8,472@23625L12");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                $dirty2 = (i & 2) != 0 ? $dirty4 & (-113) : $dirty4;
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 256) != 0) {
                    $dirty2 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty2 &= -1879048193;
                }
                state4 = state;
                expandedHeaderTopPadding4 = expandedHeaderTopPadding;
                windowInsets4 = windowInsets;
                arrangement4 = arrangement;
                expandedProperties3 = expandedProperties;
                expandedShape4 = expandedShape2;
                colors4 = wideNavigationRailColors;
                modifier5 = modifier2;
                function7 = function4;
                hideOnCollapse4 = z;
                collapsedShape4 = shape;
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 2) == 0) {
                    state3 = state;
                    $dirty = $dirty4;
                } else {
                    state3 = WideNavigationRailStateKt.rememberWideNavigationRailState(null, $composer2, 0, 1);
                    $dirty = $dirty4 & (-113);
                }
                if (i4 == 0) {
                    hideOnCollapse3 = z;
                } else {
                    hideOnCollapse3 = false;
                }
                if ((i & 8) == 0) {
                    collapsedShape3 = shape;
                } else {
                    collapsedShape3 = WideNavigationRailDefaults.INSTANCE.getModalCollapsedShape($composer2, 6);
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    expandedShape2 = WideNavigationRailDefaults.INSTANCE.getModalExpandedShape($composer2, 6);
                    $dirty &= -57345;
                }
                if ((i & 32) == 0) {
                    colors3 = wideNavigationRailColors;
                } else {
                    colors3 = WideNavigationRailDefaults.INSTANCE.colors($composer2, 6);
                    $dirty &= -458753;
                }
                if (i8 == 0) {
                    function6 = function4;
                } else {
                    function6 = null;
                }
                if (i9 == 0) {
                    expandedHeaderTopPadding3 = expandedHeaderTopPadding;
                } else {
                    expandedHeaderTopPadding3 = Dp.m7905constructorimpl(0);
                }
                if ((i & 256) != 0) {
                    windowInsets3 = WideNavigationRailDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    $dirty &= -234881025;
                } else {
                    windowInsets3 = windowInsets;
                }
                if ((i & 512) == 0) {
                    arrangement3 = arrangement;
                } else {
                    arrangement3 = WideNavigationRailDefaults.INSTANCE.getArrangement();
                    $dirty &= -1879048193;
                }
                if (i10 == 0) {
                    expandedProperties3 = expandedProperties;
                    modifier5 = modifier4;
                    colors4 = colors3;
                    state4 = state3;
                    $dirty2 = $dirty;
                    expandedShape4 = expandedShape2;
                    function7 = function6;
                    hideOnCollapse4 = hideOnCollapse3;
                    collapsedShape4 = collapsedShape3;
                    expandedHeaderTopPadding4 = expandedHeaderTopPadding3;
                    windowInsets4 = windowInsets3;
                    arrangement4 = arrangement3;
                } else {
                    modifier5 = modifier4;
                    colors4 = colors3;
                    state4 = state3;
                    $dirty2 = $dirty;
                    expandedShape4 = expandedShape2;
                    function7 = function6;
                    hideOnCollapse4 = hideOnCollapse3;
                    collapsedShape4 = collapsedShape3;
                    expandedHeaderTopPadding4 = expandedHeaderTopPadding3;
                    windowInsets4 = windowInsets3;
                    arrangement4 = arrangement3;
                    expandedProperties3 = WideNavigationRailDefaults.INSTANCE.getModalExpandedProperties();
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-38559147, $dirty2, $dirty1, "androidx.compose.material3.ModalWideNavigationRail (WideNavigationRail.kt:477)");
            }
            ModalWideNavigationRailOverrideScope scope = new ModalWideNavigationRailOverrideScope(modifier5, state4, hideOnCollapse4, collapsedShape4, expandedShape4, colors4, function7, expandedHeaderTopPadding4, windowInsets4, arrangement4, expandedProperties3, function3, null);
            ProvidableCompositionLocal<ModalWideNavigationRailOverride> providableCompositionLocal = LocalModalWideNavigationRailOverride;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(providableCompositionLocal);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ModalWideNavigationRailOverride $this$ModalWideNavigationRail_k3FuEkE_u24lambda_u2413 = (ModalWideNavigationRailOverride) objConsume;
            $this$ModalWideNavigationRail_k3FuEkE_u24lambda_u2413.ModalWideNavigationRail(scope, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            state2 = state4;
            hideOnCollapse2 = hideOnCollapse4;
            collapsedShape2 = collapsedShape4;
            expandedShape3 = expandedShape4;
            colors2 = colors4;
            function5 = function7;
            expandedHeaderTopPadding2 = expandedHeaderTopPadding4;
            windowInsets2 = windowInsets4;
            arrangement2 = arrangement4;
            expandedProperties2 = expandedProperties3;
        } else {
            $composer2.skipToGroupEnd();
            windowInsets2 = windowInsets;
            expandedProperties2 = expandedProperties;
            expandedShape3 = expandedShape2;
            colors2 = wideNavigationRailColors;
            modifier3 = modifier2;
            function5 = function4;
            hideOnCollapse2 = z;
            state2 = state;
            expandedHeaderTopPadding2 = expandedHeaderTopPadding;
            arrangement2 = arrangement;
            collapsedShape2 = shape;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return WideNavigationRailKt.ModalWideNavigationRail_k3FuEkE$lambda$14(modifier3, state2, hideOnCollapse2, collapsedShape2, expandedShape3, colors2, function5, expandedHeaderTopPadding2, windowInsets2, arrangement2, expandedProperties2, function3, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: WideNavigationRailItem-pli-t6k, reason: not valid java name */
    public static final void m3314WideNavigationRailItemplit6k(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final boolean railExpanded, Modifier modifier, boolean enabled, int iconPosition, NavigationItemColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        boolean z;
        Function0<Unit> function1;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Modifier modifier2;
        boolean enabled2;
        int iconPosition2;
        NavigationItemColors colors2;
        Composer $composer2;
        final Modifier modifier3;
        final boolean enabled3;
        final int iconPosition3;
        final NavigationItemColors colors3;
        final MutableInteractionSource interactionSource2;
        int $dirty;
        MutableInteractionSource interactionSource3;
        Modifier modifier4;
        boolean enabled4;
        int iconPosition4;
        NavigationItemColors colors4;
        MutableInteractionSource interactionSource4;
        Composer $composer3 = $composer.startRestartGroup(-1894733304);
        ComposerKt.sourceInformation($composer3, "C(WideNavigationRailItem)N(selected,onClick,icon,label,railExpanded,modifier,enabled,iconPosition:c#material3.NavigationItemIconPosition,colors,interactionSource)696@33504L5,698@33677L5,699@33767L5,692@33324L1318:WideNavigationRail.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            z = selected;
        } else if (($changed & 6) == 0) {
            z = selected;
            $dirty2 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = selected;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            function1 = function0;
        } else if (($changed & 48) == 0) {
            function1 = function0;
            $dirty2 |= $composer3.changedInstance(function1) ? 32 : 16;
        } else {
            function1 = function0;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
            function4 = function2;
        } else if (($changed & 384) == 0) {
            function4 = function2;
            $dirty2 |= $composer3.changedInstance(function4) ? 256 : 128;
        } else {
            function4 = function2;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
            function5 = function3;
        } else if (($changed & 3072) == 0) {
            function5 = function3;
            $dirty2 |= $composer3.changedInstance(function5) ? 2048 : 1024;
        } else {
            function5 = function3;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changed(railExpanded) ? 16384 : 8192;
        }
        int i2 = i & 32;
        if (i2 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            modifier2 = modifier;
        } else if ((196608 & $changed) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 131072 : 65536;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 64;
        if (i3 != 0) {
            $dirty2 |= 1572864;
            enabled2 = enabled;
        } else if ((1572864 & $changed) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 1048576 : 524288;
        } else {
            enabled2 = enabled;
        }
        if ((12582912 & $changed) == 0) {
            if ((i & 128) == 0) {
                iconPosition2 = iconPosition;
                int i4 = $composer3.changed(iconPosition2) ? 8388608 : 4194304;
                $dirty2 |= i4;
            } else {
                iconPosition2 = iconPosition;
            }
            $dirty2 |= i4;
        } else {
            iconPosition2 = iconPosition;
        }
        if ((100663296 & $changed) == 0) {
            if ((i & 256) == 0) {
                colors2 = colors;
                int i5 = $composer3.changed(colors2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                $dirty2 |= i5;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i5;
        } else {
            colors2 = colors;
        }
        int i6 = i & 512;
        if (i6 != 0) {
            $dirty2 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 536870912 : 268435456;
        }
        int $dirty3 = $dirty2;
        if ($composer3.shouldExecute(($dirty2 & 306783379) != 306783378, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "686@33127L8");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty4 = (i & 128) != 0 ? $dirty3 & (-29360129) : $dirty3;
                if ((i & 256) != 0) {
                    $dirty4 &= -234881025;
                }
                $dirty = $dirty4;
                modifier4 = modifier2;
                enabled4 = enabled2;
                iconPosition4 = iconPosition2;
                colors4 = colors2;
                interactionSource3 = interactionSource;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    enabled2 = true;
                }
                if ((i & 128) == 0) {
                    $dirty = $dirty3;
                } else {
                    $dirty = $dirty3 & (-29360129);
                    iconPosition2 = WideNavigationRailItemDefaults.INSTANCE.m3310iconPositionFors8pcRp0(railExpanded);
                }
                if ((i & 256) != 0) {
                    $dirty &= -234881025;
                    colors2 = WideNavigationRailItemDefaults.INSTANCE.colors($composer3, 6);
                }
                if (i6 == 0) {
                    interactionSource3 = interactionSource;
                    modifier4 = modifier2;
                    enabled4 = enabled2;
                    iconPosition4 = iconPosition2;
                    colors4 = colors2;
                } else {
                    interactionSource3 = null;
                    modifier4 = modifier2;
                    enabled4 = enabled2;
                    iconPosition4 = iconPosition2;
                    colors4 = colors2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1894733304, $dirty, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
            }
            if (interactionSource3 == null) {
                $composer3.startReplaceGroup(-1539072909);
                ComposerKt.sourceInformation($composer3, "690@33279L39");
                ComposerKt.sourceInformationMarkerStart($composer3, 227447151, "CC(remember):WideNavigationRail.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
                interactionSource4 = (MutableInteractionSource) it$iv;
            } else {
                $composer3.startReplaceGroup(227446500);
                $composer3.endReplaceGroup();
                interactionSource4 = interactionSource3;
            }
            $composer2 = $composer3;
            NavigationItemKt.m2671AnimatedNavigationItemDQd_Gtc(z, function1, function4, ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), $composer3, 6), NavigationRailVerticalItemTokens.INSTANCE.m3935getActiveIndicatorWidthD9Ej5fM(), TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), $composer3, 6), TypographyKt.getValue(NavigationRailHorizontalItemTokens.INSTANCE.getLabelTextFont(), $composer3, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, NavigationRailVerticalItemTokens.INSTANCE.m3936getIconLabelSpaceD9Ej5fM(), NavigationRailHorizontalItemTokens.INSTANCE.m3930getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, NavigationRailHorizontalItemTokens.INSTANCE.m3932getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, colors4, modifier4, enabled4, function5, iconPosition4, interactionSource4, $composer2, ($dirty & 14) | 918577152 | ($dirty & 112) | ($dirty & 896), (($dirty >> 9) & 458752) | 28086 | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128) | (($dirty << 15) & 234881024) | (($dirty << 6) & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource2 = interactionSource3;
            colors3 = colors4;
            modifier3 = modifier4;
            enabled3 = enabled4;
            iconPosition3 = iconPosition4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enabled3 = enabled2;
            iconPosition3 = iconPosition2;
            colors3 = colors2;
            interactionSource2 = interactionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return WideNavigationRailKt.WideNavigationRailItem_pli_t6k$lambda$16(selected, function0, function2, function3, railExpanded, modifier3, enabled3, iconPosition3, colors3, interactionSource2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:157:0x027c  */
    /* JADX WARN: Code duplicated, block: B:158:0x027e  */
    /* JADX WARN: Code duplicated, block: B:164:0x029c  */
    /* JADX WARN: Code duplicated, block: B:170:0x02e4  */
    /* JADX WARN: Code duplicated, block: B:173:0x0353  */
    /* JADX INFO: renamed from: ModalWideNavigationRailContent-pU6N4AM, reason: not valid java name */
    public static final void m3312ModalWideNavigationRailContentpU6N4AM(final boolean expanded, final boolean isStandaloneModal, final Animatable<Float, AnimationVector1D> animatable, final RailPredictiveBackState predictiveBackState, final Function2<? super Float, ? super Continuation<? super Unit>, ? extends Object> function2, final Modifier modifier, final ModalWideNavigationRailState railState, final WideNavigationRailColors colors, final Shape shape, final float openModalRailMaxWidth, final Function2<? super Composer, ? super Integer, Unit> function3, final WindowInsets windowInsets, final boolean gesturesEnabled, final Arrangement.Vertical arrangement, final Function2<? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int $changed1) {
        WideNavigationRailColors wideNavigationRailColors;
        Composer $composer2;
        int $dirty;
        boolean z;
        boolean invalid$iv;
        Object value$iv;
        boolean invalid$iv2;
        Object it$iv;
        Composer $composer3 = $composer.startRestartGroup(-1593438005);
        ComposerKt.sourceInformation($composer3, "C(ModalWideNavigationRailContent)N(expanded,isStandaloneModal,predictiveBackProgress,predictiveBackState,settleToDismiss,modifier,railState,colors,shape,openModalRailMaxWidth:c#ui.unit.Dp,header,windowInsets,gesturesEnabled,arrangement,content)989@47315L7,990@47370L55,1000@47710L29,1001@47771L904,1020@48768L649,1041@49760L23,1043@49810L1311,992@47431L3690:WideNavigationRail.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(expanded) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(isStandaloneModal) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= ($changed & 512) == 0 ? $composer3.changed(animatable) : $composer3.changedInstance(animatable) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changed(predictiveBackState) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty2 |= $composer3.changedInstance(railState) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            wideNavigationRailColors = colors;
            $dirty2 |= $composer3.changed(wideNavigationRailColors) ? 8388608 : 4194304;
        } else {
            wideNavigationRailColors = colors;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(shape) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty2 |= $composer3.changed(openModalRailMaxWidth) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changedInstance(function3) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changed(windowInsets) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty1 |= $composer3.changed(gesturesEnabled) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty1 |= $composer3.changed(arrangement) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty1 |= $composer3.changedInstance(function4) ? 16384 : 8192;
        }
        if (!$composer3.shouldExecute((($dirty2 & 306783379) == 306783378 && ($dirty1 & 9363) == 9362) ? false : true, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1593438005, $dirty2, $dirty1, "androidx.compose.material3.ModalWideNavigationRailContent (WideNavigationRail.kt:988)");
            }
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final boolean isRtl = objConsume == LayoutDirection.Rtl;
            Strings.Companion companion = Strings.INSTANCE;
            final String railPaneTitle = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_wide_navigation_rail_pane_title), $composer3, 0);
            long modalContainerColor = wideNavigationRailColors.getModalContainerColor();
            long modalContentColor = wideNavigationRailColors.getModalContentColor();
            Modifier modifierFillMaxHeight$default = SizeKt.fillMaxHeight$default(SizeKt.m872widthInVpY3zN4$default(modifier, 0.0f, openModalRailMaxWidth, 1, null), 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 2009435560, "CC(remember):WideNavigationRail.kt#9igjgp");
            boolean invalid$iv3 = $composer3.changed(railPaneTitle);
            Object it$iv2 = $composer3.rememberedValue();
            if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return WideNavigationRailKt.ModalWideNavigationRailContent_pU6N4AM$lambda$18$lambda$17(railPaneTitle, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierFillMaxHeight$default, false, (Function1) it$iv2, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 2009438387, "CC(remember):WideNavigationRail.kt#9igjgp");
            boolean invalid$iv4 = (($dirty2 & 896) == 256 || (($dirty2 & 512) != 0 && $composer3.changedInstance(animatable))) | $composer3.changedInstance(railState) | (($dirty2 & 7168) == 2048) | $composer3.changed(isRtl);
            Object value$iv3 = $composer3.rememberedValue();
            if (!invalid$iv4) {
                $dirty = $dirty2;
                if (value$iv3 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierSemantics$default, (Function1) value$iv3);
                AnchoredDraggableState<WideNavigationRailValue> anchoredDraggableState$material3 = railState.getAnchoredDraggableState$material3();
                Orientation orientation = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart($composer3, 2009470036, "CC(remember):WideNavigationRail.kt#9igjgp");
                if (($dirty & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                invalid$iv = z | $composer3.changed(isRtl) | $composer3.changedInstance(railState);
                value$iv = $composer3.rememberedValue();
                if (!invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = new Function2() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return WideNavigationRailKt.ModalWideNavigationRailContent_pU6N4AM$lambda$23$lambda$22(isStandaloneModal, isRtl, railState, (IntSize) obj, (Constraints) obj2);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierDraggableAnchors = AnchoredDraggableKt.draggableAnchors(modifierGraphicsLayer, anchoredDraggableState$material3, orientation, (Function2) value$iv);
                DraggableState draggableState = railState.getAnchoredDraggableState$material3().getDraggableState();
                Orientation orientation2 = Orientation.Horizontal;
                boolean zIsAnimationRunning = railState.getAnchoredDraggableState$material3().isAnimationRunning();
                ComposerKt.sourceInformationMarkerStart($composer3, 2009501154, "CC(remember):WideNavigationRail.kt#9igjgp");
                invalid$iv2 = $composer3.changedInstance(function2);
                it$iv = $composer3.rememberedValue();
                if (!invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv4 = (Function3) new WideNavigationRailKt$ModalWideNavigationRailContent$4$1(function2, null);
                    $composer3.updateRememberedValue(value$iv4);
                    it$iv = value$iv4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                int i = (($dirty >> 21) & 112) | 12582912;
                $composer2 = $composer3;
                SurfaceKt.m2929SurfaceT9BRK9s(DraggableKt.draggable(modifierDraggableAnchors, draggableState, orientation2, (TsExtractor.TS_PACKET_SIZE & 4) != 0 ? true : gesturesEnabled, (TsExtractor.TS_PACKET_SIZE & 8) != 0 ? null : null, (TsExtractor.TS_PACKET_SIZE & 16) != 0 ? false : zIsAnimationRunning, (TsExtractor.TS_PACKET_SIZE & 32) != 0 ? DraggableKt.NoOpOnDragStarted : null, (TsExtractor.TS_PACKET_SIZE & 64) != 0 ? DraggableKt.NoOpOnDragStopped : (Function3) it$iv, (TsExtractor.TS_PACKET_SIZE & 128) != 0 ? false : false), shape, modalContainerColor, modalContentColor, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1043835354, true, new WideNavigationRailKt$ModalWideNavigationRailContent$5(animatable, predictiveBackState, isRtl, expanded, wideNavigationRailColors, shape, function3, windowInsets, arrangement, function4), $composer3, 54), $composer2, i, 112);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $dirty = $dirty2;
            }
            value$iv3 = new Function1() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WideNavigationRailKt.ModalWideNavigationRailContent_pU6N4AM$lambda$20$lambda$19(animatable, railState, predictiveBackState, isRtl, (GraphicsLayerScope) obj);
                }
            };
            $composer3.updateRememberedValue(value$iv3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierGraphicsLayer2 = GraphicsLayerModifierKt.graphicsLayer(modifierSemantics$default, (Function1) value$iv3);
            AnchoredDraggableState<WideNavigationRailValue> anchoredDraggableState$material4 = railState.getAnchoredDraggableState$material3();
            Orientation orientation3 = Orientation.Horizontal;
            ComposerKt.sourceInformationMarkerStart($composer3, 2009470036, "CC(remember):WideNavigationRail.kt#9igjgp");
            if (($dirty & 112) == 32) {
                z = true;
            } else {
                z = false;
            }
            invalid$iv = z | $composer3.changed(isRtl) | $composer3.changedInstance(railState);
            value$iv = $composer3.rememberedValue();
            if (!invalid$iv) {
                value$iv = new Function2() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return WideNavigationRailKt.ModalWideNavigationRailContent_pU6N4AM$lambda$23$lambda$22(isStandaloneModal, isRtl, railState, (IntSize) obj, (Constraints) obj2);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = new Function2() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return WideNavigationRailKt.ModalWideNavigationRailContent_pU6N4AM$lambda$23$lambda$22(isStandaloneModal, isRtl, railState, (IntSize) obj, (Constraints) obj2);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierDraggableAnchors2 = AnchoredDraggableKt.draggableAnchors(modifierGraphicsLayer2, anchoredDraggableState$material4, orientation3, (Function2) value$iv);
            DraggableState draggableState2 = railState.getAnchoredDraggableState$material3().getDraggableState();
            Orientation orientation4 = Orientation.Horizontal;
            boolean zIsAnimationRunning2 = railState.getAnchoredDraggableState$material3().isAnimationRunning();
            ComposerKt.sourceInformationMarkerStart($composer3, 2009501154, "CC(remember):WideNavigationRail.kt#9igjgp");
            invalid$iv2 = $composer3.changedInstance(function2);
            it$iv = $composer3.rememberedValue();
            if (!invalid$iv2) {
                Object value$iv5 = (Function3) new WideNavigationRailKt$ModalWideNavigationRailContent$4$1(function2, null);
                $composer3.updateRememberedValue(value$iv5);
                it$iv = value$iv5;
            } else {
                Object value$iv6 = (Function3) new WideNavigationRailKt$ModalWideNavigationRailContent$4$1(function2, null);
                $composer3.updateRememberedValue(value$iv6);
                it$iv = value$iv6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            int i2 = (($dirty >> 21) & 112) | 12582912;
            $composer2 = $composer3;
            SurfaceKt.m2929SurfaceT9BRK9s(DraggableKt.draggable(modifierDraggableAnchors2, draggableState2, orientation4, (TsExtractor.TS_PACKET_SIZE & 4) != 0 ? true : gesturesEnabled, (TsExtractor.TS_PACKET_SIZE & 8) != 0 ? null : null, (TsExtractor.TS_PACKET_SIZE & 16) != 0 ? false : zIsAnimationRunning2, (TsExtractor.TS_PACKET_SIZE & 32) != 0 ? DraggableKt.NoOpOnDragStarted : null, (TsExtractor.TS_PACKET_SIZE & 64) != 0 ? DraggableKt.NoOpOnDragStopped : (Function3) it$iv, (TsExtractor.TS_PACKET_SIZE & 128) != 0 ? false : false), shape, modalContainerColor, modalContentColor, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1043835354, true, new WideNavigationRailKt$ModalWideNavigationRailContent$5(animatable, predictiveBackState, isRtl, expanded, wideNavigationRailColors, shape, function3, windowInsets, arrangement, function4), $composer3, 54), $composer2, i2, 112);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return WideNavigationRailKt.ModalWideNavigationRailContent_pU6N4AM$lambda$25(expanded, isStandaloneModal, animatable, predictiveBackState, function2, modifier, railState, colors, shape, openModalRailMaxWidth, function3, windowInsets, gesturesEnabled, arrangement, function4, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit ModalWideNavigationRailContent_pU6N4AM$lambda$18$lambda$17(String $railPaneTitle, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setPaneTitle($this$semantics, $railPaneTitle);
        return Unit.INSTANCE;
    }

    static final Unit ModalWideNavigationRailContent_pU6N4AM$lambda$20$lambda$19(Animatable $predictiveBackProgress, ModalWideNavigationRailState $railState, RailPredictiveBackState $predictiveBackState, boolean $isRtl, GraphicsLayerScope $this$graphicsLayer) {
        float progress = ((Number) $predictiveBackProgress.getValue()).floatValue();
        if (progress <= 0.0f) {
            return Unit.INSTANCE;
        }
        float offset = $railState.getCurrentOffset();
        long arg0$iv = $this$graphicsLayer.getSize();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float width = Float.intBitsToFloat(bits$iv$iv$iv);
        if (!Float.isNaN(offset) && !Float.isNaN(width)) {
            if (!(width == 0.0f)) {
                $this$graphicsLayer.setScaleX(calculatePredictiveBackScaleX($this$graphicsLayer, progress, $predictiveBackState.getSwipeEdgeMatchesRail()));
                $this$graphicsLayer.setScaleY(calculatePredictiveBackScaleY($this$graphicsLayer, progress));
                $this$graphicsLayer.mo5298setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin($isRtl ? 1.0f : 0.0f, 0.5f));
            }
        }
        return Unit.INSTANCE;
    }

    static final Pair ModalWideNavigationRailContent_pU6N4AM$lambda$23$lambda$22(boolean $isStandaloneModal, boolean $isRtl, ModalWideNavigationRailState $railState, IntSize railSize, Constraints constraints) {
        final float minValue;
        long arg0$iv = railSize.m8080unboximpl();
        float width = (int) (arg0$iv >> 32);
        if ($isStandaloneModal) {
            minValue = $isRtl ? width : -width;
        } else {
            minValue = 0.0f;
        }
        final float maxValue = 0.0f;
        return TuplesKt.to(AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WideNavigationRailKt.ModalWideNavigationRailContent_pU6N4AM$lambda$23$lambda$22$lambda$21(minValue, maxValue, (DraggableAnchorsConfig) obj);
            }
        }), $railState.getTargetValue());
    }

    static final Unit ModalWideNavigationRailContent_pU6N4AM$lambda$23$lambda$22$lambda$21(float $minValue, float $maxValue, DraggableAnchorsConfig $this$DraggableAnchors) {
        $this$DraggableAnchors.at(WideNavigationRailValue.Collapsed, $minValue);
        $this$DraggableAnchors.at(WideNavigationRailValue.Expanded, $maxValue);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackScaleX(GraphicsLayerScope $this$calculatePredictiveBackScaleX, float progress, boolean swipeEdgeMatchesRail) {
        long arg0$iv = $this$calculatePredictiveBackScaleX.getSize();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float width = Float.intBitsToFloat(bits$iv$iv$iv);
        if (Float.isNaN(width)) {
            return 1.0f;
        }
        if (width == 0.0f) {
            return 1.0f;
        }
        float scaleXDirection = swipeEdgeMatchesRail ? 1.0f : -1.0f;
        return 1.0f + ((MathHelpersKt.lerp(0.0f, Math.min($this$calculatePredictiveBackScaleX.mo406toPx0680j_4(PredictiveBackMaxScaleXDistance), width), progress) * scaleXDirection) / width);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackScaleY(GraphicsLayerScope $this$calculatePredictiveBackScaleY, float progress) {
        long arg0$iv = $this$calculatePredictiveBackScaleY.getSize();
        int bits$iv$iv$iv = (int) (4294967295L & arg0$iv);
        float height = Float.intBitsToFloat(bits$iv$iv$iv);
        if (Float.isNaN(height)) {
            return 1.0f;
        }
        if (height == 0.0f) {
            return 1.0f;
        }
        return 1.0f - (MathHelpersKt.lerp(0.0f, Math.min($this$calculatePredictiveBackScaleY.mo406toPx0680j_4(PredictiveBackMaxScaleYDistance), height), progress) / height);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Scrim-3J-VO9M, reason: not valid java name */
    public static final void m3313Scrim3JVO9M(final long color, final Function1<? super Continuation<? super Unit>, ? extends Object> function1, final boolean visible, Composer $composer, final int $changed) {
        Modifier.Companion dismissModalRail;
        Composer $composer2 = $composer.startRestartGroup(144695261);
        ComposerKt.sourceInformation($composer2, "C(Scrim)N(color:c#ui.graphics.Color,onDismissRequest,visible):WideNavigationRail.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(color) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(visible) ? 256 : 128;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(144695261, $dirty, -1, "androidx.compose.material3.Scrim (WideNavigationRail.kt:1102)");
            }
            if ((color != 16 ? 1 : 0) != 0) {
                $composer2.startReplaceGroup(-1530482291);
                ComposerKt.sourceInformation($composer2, "1108@52256L7,1105@52025L253,1110@52302L34,1111@52366L28,1125@52948L79,1125@52894L133,1129@53061L35,1129@53037L59");
                int $dirty2 = $dirty;
                final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(visible ? 1065353216 : 0, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer2, 6), 0.0f, null, null, $composer2, 0, 28);
                ComposerKt.sourceInformationMarkerStart($composer2, 1613206495, "CC(remember):WideNavigationRail.kt#9igjgp");
                Object it$iv = $composer2.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                final MutableState dismiss$delegate = (MutableState) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Strings.Companion companion = Strings.INSTANCE;
                final String closeModalRail = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_wide_navigation_rail_close_rail), $composer2, 0);
                if (visible) {
                    $composer2.startReplaceGroup(-1530047423);
                    ComposerKt.sourceInformation($composer2, "1114@52509L40,1115@52606L219");
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1613213125, "CC(remember):WideNavigationRail.kt#9igjgp");
                    Object it$iv2 = $composer2.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = (PointerInputEventHandler) new WideNavigationRailKt$Scrim$dismissModalRail$1$1(dismiss$delegate);
                        $composer2.updateRememberedValue(value$iv2);
                        it$iv2 = value$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion2, function1, (PointerInputEventHandler) it$iv2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1613216408, "CC(remember):WideNavigationRail.kt#9igjgp");
                    boolean invalid$iv = $composer2.changed(closeModalRail);
                    Object it$iv3 = $composer2.rememberedValue();
                    if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv3 = new Function1() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda12
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return WideNavigationRailKt.Scrim_3J_VO9M$lambda$33$lambda$32(closeModalRail, dismiss$delegate, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv3);
                        it$iv3 = value$iv3;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    dismissModalRail = SemanticsModifierKt.semantics(modifierPointerInput, true, (Function1) it$iv3);
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(-1529667363);
                    $composer2.endReplaceGroup();
                    dismissModalRail = Modifier.INSTANCE;
                }
                Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissModalRail);
                ComposerKt.sourceInformationMarkerStart($composer2, 1613227212, "CC(remember):WideNavigationRail.kt#9igjgp");
                boolean invalid$iv2 = (($dirty2 & 14) == 4) | $composer2.changed(stateAnimateFloatAsState);
                Object it$iv4 = $composer2.rememberedValue();
                if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv4 = new Function1() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return WideNavigationRailKt.Scrim_3J_VO9M$lambda$35$lambda$34(color, stateAnimateFloatAsState, (DrawScope) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv4);
                    it$iv4 = value$iv4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(modifierThen, (Function1) it$iv4, $composer2, 0);
                Boolean boolValueOf = Boolean.valueOf(Scrim_3J_VO9M$lambda$28(dismiss$delegate));
                ComposerKt.sourceInformationMarkerStart($composer2, 1613230784, "CC(remember):WideNavigationRail.kt#9igjgp");
                boolean invalid$iv3 = $composer2.changedInstance(function1);
                Object it$iv5 = $composer2.rememberedValue();
                if (invalid$iv3 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv5 = (Function2) new WideNavigationRailKt$Scrim$2$1(function1, dismiss$delegate, null);
                    $composer2.updateRememberedValue(value$iv5);
                    it$iv5 = value$iv5;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(boolValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv5, $composer2, 0);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-1529413659);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return WideNavigationRailKt.Scrim_3J_VO9M$lambda$37(color, function1, visible, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float Scrim_3J_VO9M$lambda$26(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean Scrim_3J_VO9M$lambda$28(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Scrim_3J_VO9M$lambda$29(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit Scrim_3J_VO9M$lambda$33$lambda$32(String $closeModalRail, final MutableState $dismiss$delegate, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContentDescription($this$semantics, $closeModalRail);
        SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(WideNavigationRailKt.Scrim_3J_VO9M$lambda$33$lambda$32$lambda$31($dismiss$delegate));
            }
        }, 1, null);
        return Unit.INSTANCE;
    }

    static final boolean Scrim_3J_VO9M$lambda$33$lambda$32$lambda$31(MutableState $dismiss$delegate) {
        Scrim_3J_VO9M$lambda$29($dismiss$delegate, true);
        return true;
    }

    static final Unit Scrim_3J_VO9M$lambda$35$lambda$34(long $color, State $alpha$delegate, DrawScope $this$Canvas) {
        DrawScope.m5665drawRectnJ9OG0$default($this$Canvas, $color, 0L, 0L, RangesKt.coerceIn(Scrim_3J_VO9M$lambda$26($alpha$delegate), 0.0f, 1.0f), null, null, 0, 118, null);
        return Unit.INSTANCE;
    }

    public static final float getWNRItemNoLabelIndicatorPadding() {
        return WNRItemNoLabelIndicatorPadding;
    }

    static {
        float arg0$iv = NavigationRailVerticalItemTokens.INSTANCE.m3935getActiveIndicatorWidthD9Ej5fM();
        float other$iv = NavigationRailBaselineItemTokens.INSTANCE.m3918getIconSizeD9Ej5fM();
        WNRItemNoLabelIndicatorPadding = Dp.m7905constructorimpl(Dp.m7905constructorimpl(arg0$iv - other$iv) / 2);
        ItemHorizontalPadding = Dp.m7905constructorimpl(20);
        WNRVerticalPadding = NavigationRailCollapsedTokens.INSTANCE.m3923getTopSpaceD9Ej5fM();
        WNRHeaderPadding = NavigationRailBaselineItemTokens.INSTANCE.m3917getHeaderSpaceMinimumD9Ej5fM();
        CollapsedRailWidth = NavigationRailCollapsedTokens.INSTANCE.m3920getContainerWidthD9Ej5fM();
        ExpandedRailMinWidth = NavigationRailExpandedTokens.INSTANCE.m3926getContainerWidthMinimumD9Ej5fM();
        ExpandedRailMaxWidth = NavigationRailExpandedTokens.INSTANCE.m3925getContainerWidthMaximumD9Ej5fM();
        TopIconItemMinHeight = NavigationRailBaselineItemTokens.INSTANCE.m3915getContainerHeightD9Ej5fM();
        float arg0$iv2 = NavigationRailVerticalItemTokens.INSTANCE.m3934getActiveIndicatorHeightD9Ej5fM();
        float other$iv2 = NavigationRailBaselineItemTokens.INSTANCE.m3918getIconSizeD9Ej5fM();
        ItemTopIconIndicatorVerticalPadding = Dp.m7905constructorimpl(Dp.m7905constructorimpl(arg0$iv2 - other$iv2) / 2);
        float arg0$iv3 = NavigationRailVerticalItemTokens.INSTANCE.m3935getActiveIndicatorWidthD9Ej5fM();
        float other$iv3 = NavigationRailBaselineItemTokens.INSTANCE.m3918getIconSizeD9Ej5fM();
        ItemTopIconIndicatorHorizontalPadding = Dp.m7905constructorimpl(Dp.m7905constructorimpl(arg0$iv3 - other$iv3) / 2);
        float arg0$iv4 = NavigationRailHorizontalItemTokens.INSTANCE.m3929getActiveIndicatorHeightD9Ej5fM();
        float other$iv4 = NavigationRailBaselineItemTokens.INSTANCE.m3918getIconSizeD9Ej5fM();
        ItemStartIconIndicatorVerticalPadding = Dp.m7905constructorimpl(Dp.m7905constructorimpl(arg0$iv4 - other$iv4) / 2);
        PredictiveBackMaxScaleXDistance = Dp.m7905constructorimpl(24);
        PredictiveBackMaxScaleYDistance = Dp.m7905constructorimpl(48);
        LocalWideNavigationRailOverride = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DefaultWideNavigationRailOverride.INSTANCE;
            }
        }, 1, null);
        LocalModalWideNavigationRailOverride = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: androidx.compose.material3.WideNavigationRailKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DefaultModalWideNavigationRailOverride.INSTANCE;
            }
        }, 1, null);
    }

    public static final ProvidableCompositionLocal<WideNavigationRailOverride> getLocalWideNavigationRailOverride() {
        return LocalWideNavigationRailOverride;
    }

    public static final ProvidableCompositionLocal<ModalWideNavigationRailOverride> getLocalModalWideNavigationRailOverride() {
        return LocalModalWideNavigationRailOverride;
    }
}
