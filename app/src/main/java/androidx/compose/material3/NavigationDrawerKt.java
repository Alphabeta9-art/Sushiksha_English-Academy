package androidx.compose.material3;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.gestures.AnchoredDraggableKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.material3.internal.BackHandler_androidKt;
import androidx.compose.material3.internal.FloatProducer;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.NavigationDrawerTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.profileinstaller.ProfileVerifier;
import com.cloudinary.metadata.MetadataField;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: NavigationDrawer.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a+\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¢\u0006\u0002\u0010\u0007\u001a]\u0010\b\u001a\u00020\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\u0002\b\fH\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001aQ\u0010\u0016\u001a\u00020\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00062\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\u0010\u0017\u001a=\u0010\u0018\u001a\u00020\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\u0010\u0019\u001ai\u0010\u001a\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\u0002\b\f¢\u0006\u0002\b$H\u0007¢\u0006\u0004\b%\u0010&\u001aq\u0010\u001a\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\u0002\b\f¢\u0006\u0002\b$H\u0007¢\u0006\u0004\b'\u0010(\u001ai\u0010)\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\u0002\b\f¢\u0006\u0002\b$H\u0007¢\u0006\u0004\b*\u0010&\u001aq\u0010)\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\u0002\b\f¢\u0006\u0002\b$H\u0007¢\u0006\u0004\b+\u0010(\u001ai\u0010,\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\u0002\b\f¢\u0006\u0002\b$H\u0007¢\u0006\u0004\b-\u0010&\u001a{\u0010.\u001a\u00020\t2\b\u0010/\u001a\u0004\u0018\u0001002\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u00101\u001a\u0002022\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\u0002\b\f¢\u0006\u0002\b$H\u0001¢\u0006\u0004\b3\u00104\u001a$\u00105\u001a\u00020\u000e*\u00020\u000e2\u0006\u00101\u001a\u0002022\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\u0006H\u0002\u001a$\u00109\u001a\u00020\u000e*\u00020\u000e2\u0006\u00101\u001a\u0002022\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\u0006H\u0002\u001a\u001c\u0010:\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010/\u001a\u0002002\u0006\u00108\u001a\u00020\u0006H\u0002\u001a\u001c\u0010;\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010/\u001a\u0002002\u0006\u00108\u001a\u00020\u0006H\u0002\u001a\u0014\u0010<\u001a\u000207*\u00020=2\u0006\u0010/\u001a\u000200H\u0002\u001a\u0014\u0010>\u001a\u000207*\u00020=2\u0006\u0010/\u001a\u000200H\u0002\u001a.\u0010?\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00012\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\u0002\b\fH\u0001¢\u0006\u0002\u0010@\u001a\u008e\u0001\u0010A\u001a\u00020\t2\u0011\u0010B\u001a\r\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\u0002\b\f2\u0006\u0010C\u001a\u00020\u00062\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0015\b\u0002\u0010E\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0015\b\u0002\u0010F\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010G\u001a\u00020\u001c2\b\b\u0002\u0010H\u001a\u00020I2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010KH\u0007¢\u0006\u0002\u0010L\u001a \u0010M\u001a\u0002072\u0006\u0010N\u001a\u0002072\u0006\u0010O\u001a\u0002072\u0006\u0010P\u001a\u000207H\u0002\u001a;\u0010Q\u001a\u00020\t2\u0006\u0010R\u001a\u00020\u00062\f\u0010S\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\f\u0010T\u001a\b\u0012\u0004\u0012\u0002070\u000b2\u0006\u0010U\u001a\u00020\u0012H\u0003¢\u0006\u0004\bV\u0010W\"\u000e\u0010X\u001a\u000207X\u0082D¢\u0006\u0002\n\u0000\"\u0010\u0010Y\u001a\u00020 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010Z\"\u0010\u0010[\u001a\u00020 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010Z\"\u0016\u0010\\\u001a\u00020 X\u0080\u0004¢\u0006\n\n\u0002\u0010Z\u001a\u0004\b]\u0010^\"\u0016\u0010_\u001a\u00020 X\u0080\u0004¢\u0006\n\n\u0002\u0010Z\u001a\u0004\b`\u0010^\"\u0016\u0010a\u001a\u00020 X\u0080\u0004¢\u0006\n\n\u0002\u0010Z\u001a\u0004\bb\u0010^\"\u0014\u0010c\u001a\b\u0012\u0004\u0012\u0002070dX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006e²\u0006\n\u0010f\u001a\u00020\u0006X\u008a\u008e\u0002²\u0006\n\u0010g\u001a\u000207X\u008a\u008e\u0002²\u0006\n\u0010f\u001a\u00020\u0006X\u008a\u008e\u0002"}, d2 = {"rememberDrawerState", "Landroidx/compose/material3/DrawerState;", "initialValue", "Landroidx/compose/material3/DrawerValue;", "confirmStateChange", "Lkotlin/Function1;", "", "(Landroidx/compose/material3/DrawerValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DrawerState;", "ModalNavigationDrawer", "", "drawerContent", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "drawerState", "gesturesEnabled", "scrimColor", "Landroidx/compose/ui/graphics/Color;", UriUtil.LOCAL_CONTENT_SCHEME, "ModalNavigationDrawer-FHprtrg", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DrawerState;ZJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "DismissibleNavigationDrawer", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DrawerState;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "PermanentNavigationDrawer", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ModalDrawerSheet", "drawerShape", "Landroidx/compose/ui/graphics/Shape;", "drawerContainerColor", "drawerContentColor", "drawerTonalElevation", "Landroidx/compose/ui/unit/Dp;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "ModalDrawerSheet-afqeVBk", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ModalDrawerSheet-Snr_uVM", "(Landroidx/compose/material3/DrawerState;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DismissibleDrawerSheet", "DismissibleDrawerSheet-afqeVBk", "DismissibleDrawerSheet-Snr_uVM", "PermanentDrawerSheet", "PermanentDrawerSheet-afqeVBk", "DrawerSheet", "drawerPredictiveBackState", "Landroidx/compose/material3/DrawerPredictiveBackState;", "drawerOffset", "Landroidx/compose/material3/internal/FloatProducer;", "DrawerSheet-cm3T3N0", "(Landroidx/compose/material3/DrawerPredictiveBackState;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJFLandroidx/compose/material3/internal/FloatProducer;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "horizontalScaleUp", "drawerWidth", "", "isRtl", "horizontalScaleDown", "predictiveBackDrawerContainer", "predictiveBackDrawerChild", "calculatePredictiveBackScaleX", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "calculatePredictiveBackScaleY", "DrawerPredictiveBackHandler", "(Landroidx/compose/material3/DrawerState;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "NavigationDrawerItem", MetadataField.LABEL, "selected", "onClick", "icon", "badge", "shape", "colors", "Landroidx/compose/material3/NavigationDrawerItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/NavigationDrawerItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "calculateFraction", CmcdHeadersFactory.OBJECT_TYPE_AUDIO_ONLY, "b", "pos", "Scrim", TtmlNode.TEXT_EMPHASIS_MARK_OPEN, "onClose", "fraction", "color", "Scrim-Bx497Mc", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;JLandroidx/compose/runtime/Composer;I)V", "DrawerPositionalThreshold", "DrawerVelocityThreshold", "F", "MinimumDrawerWidth", "PredictiveBackDrawerMaxScaleXDistanceGrow", "getPredictiveBackDrawerMaxScaleXDistanceGrow", "()F", "PredictiveBackDrawerMaxScaleXDistanceShrink", "getPredictiveBackDrawerMaxScaleXDistanceShrink", "PredictiveBackDrawerMaxScaleYDistance", "getPredictiveBackDrawerMaxScaleYDistance", "AnchoredDraggableDefaultAnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "material3", "anchorsInitialized", "minValue"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class NavigationDrawerKt {
    private static final float DrawerPositionalThreshold = 0.5f;
    private static final float DrawerVelocityThreshold = Dp.m7905constructorimpl(400);
    private static final float MinimumDrawerWidth = Dp.m7905constructorimpl(PsExtractor.VIDEO_STREAM_MASK);
    private static final float PredictiveBackDrawerMaxScaleXDistanceGrow = Dp.m7905constructorimpl(12);
    private static final float PredictiveBackDrawerMaxScaleXDistanceShrink = Dp.m7905constructorimpl(24);
    private static final float PredictiveBackDrawerMaxScaleYDistance = Dp.m7905constructorimpl(48);
    private static final TweenSpec<Float> AnchoredDraggableDefaultAnimationSpec = new TweenSpec<>(256, 0, null, 6, null);

    static final Unit DismissibleDrawerSheet_Snr_uVM$lambda$46(DrawerState drawerState, Modifier modifier, Shape shape, long j, long j2, float f, WindowInsets windowInsets, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2643DismissibleDrawerSheetSnr_uVM(drawerState, modifier, shape, j, j2, f, windowInsets, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit DismissibleDrawerSheet_afqeVBk$lambda$45(Modifier modifier, Shape shape, long j, long j2, float f, WindowInsets windowInsets, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2644DismissibleDrawerSheetafqeVBk(modifier, shape, j, j2, f, windowInsets, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit DismissibleNavigationDrawer$lambda$39(Function2 function2, Modifier modifier, DrawerState drawerState, boolean z, Function2 function3, int i, int i2, Composer composer, int i3) {
        DismissibleNavigationDrawer(function2, modifier, drawerState, z, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit DrawerPredictiveBackHandler$lambda$62(DrawerState drawerState, Function3 function3, int i, Composer composer, int i2) {
        DrawerPredictiveBackHandler(drawerState, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DrawerSheet_cm3T3N0$lambda$53(DrawerPredictiveBackState drawerPredictiveBackState, WindowInsets windowInsets, Modifier modifier, Shape shape, long j, long j2, float f, FloatProducer floatProducer, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2645DrawerSheetcm3T3N0(drawerPredictiveBackState, windowInsets, modifier, shape, j, j2, f, floatProducer, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ModalDrawerSheet_Snr_uVM$lambda$44(DrawerState drawerState, Modifier modifier, Shape shape, long j, long j2, float f, WindowInsets windowInsets, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2646ModalDrawerSheetSnr_uVM(drawerState, modifier, shape, j, j2, f, windowInsets, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ModalDrawerSheet_afqeVBk$lambda$43(Modifier modifier, Shape shape, long j, long j2, float f, WindowInsets windowInsets, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2647ModalDrawerSheetafqeVBk(modifier, shape, j, j2, f, windowInsets, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ModalNavigationDrawer_FHprtrg$lambda$25(Function2 function2, Modifier modifier, DrawerState drawerState, boolean z, long j, Function2 function3, int i, int i2, Composer composer, int i3) {
        m2648ModalNavigationDrawerFHprtrg(function2, modifier, drawerState, z, j, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit NavigationDrawerItem$lambda$65(Function2 function2, boolean z, Function0 function0, Modifier modifier, Function2 function3, Function2 function4, Shape shape, NavigationDrawerItemColors navigationDrawerItemColors, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        NavigationDrawerItem(function2, z, function0, modifier, function3, function4, shape, navigationDrawerItemColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit PermanentDrawerSheet_afqeVBk$lambda$49(Modifier modifier, Shape shape, long j, long j2, float f, WindowInsets windowInsets, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2649PermanentDrawerSheetafqeVBk(modifier, shape, j, j2, f, windowInsets, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit PermanentNavigationDrawer$lambda$42(Function2 function2, Modifier modifier, Function2 function3, int i, int i2, Composer composer, int i3) {
        PermanentNavigationDrawer(function2, modifier, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Scrim_Bx497Mc$lambda$72(boolean z, Function0 function0, Function0 function1, long j, int i, Composer composer, int i2) {
        m2650ScrimBx497Mc(z, function0, function1, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final boolean rememberDrawerState$lambda$1$lambda$0(DrawerValue it) {
        return true;
    }

    public static final DrawerState rememberDrawerState(final DrawerValue initialValue, final Function1<? super DrawerValue, Boolean> function1, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 2098699222, "C(rememberDrawerState)N(initialValue,confirmStateChange)304@12444L8,306@12546L61,306@12482L125:NavigationDrawer.kt#uh7d8r");
        if ((i & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, -1784672962, "CC(remember):NavigationDrawer.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(NavigationDrawerKt.rememberDrawerState$lambda$1$lambda$0((DrawerValue) obj));
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            function1 = (Function1) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2098699222, $changed, -1, "androidx.compose.material3.rememberDrawerState (NavigationDrawer.kt:305)");
        }
        Object[] objArr = new Object[0];
        Saver<DrawerState, DrawerValue> Saver = DrawerState.INSTANCE.Saver(function1);
        ComposerKt.sourceInformationMarkerStart($composer, -1784669645, "CC(remember):NavigationDrawer.kt#9igjgp");
        boolean z = true;
        boolean z2 = ((($changed & 14) ^ 6) > 4 && $composer.changed(initialValue.ordinal())) || ($changed & 6) == 4;
        if (((($changed & 112) ^ 48) <= 32 || !$composer.changed(function1)) && ($changed & 48) != 32) {
            z = false;
        }
        boolean invalid$iv = z2 | z;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return NavigationDrawerKt.rememberDrawerState$lambda$3$lambda$2(initialValue, function1);
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        DrawerState drawerState = (DrawerState) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) Saver, (Function0) it$iv2, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return drawerState;
    }

    static final DrawerState rememberDrawerState$lambda$3$lambda$2(DrawerValue $initialValue, Function1 $confirmStateChange) {
        return new DrawerState($initialValue, $confirmStateChange);
    }

    /* JADX WARN: Code duplicated, block: B:103:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:104:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:122:0x026f  */
    /* JADX WARN: Code duplicated, block: B:124:0x0279  */
    /* JADX WARN: Code duplicated, block: B:125:0x0282  */
    /* JADX WARN: Code duplicated, block: B:129:0x02cd  */
    /* JADX WARN: Code duplicated, block: B:130:0x02d0  */
    /* JADX WARN: Code duplicated, block: B:133:0x0369  */
    /* JADX WARN: Code duplicated, block: B:136:0x0375  */
    /* JADX WARN: Code duplicated, block: B:137:0x037b  */
    /* JADX WARN: Code duplicated, block: B:140:0x03ae  */
    /* JADX WARN: Code duplicated, block: B:143:0x03c3 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:147:0x047e  */
    /* JADX WARN: Code duplicated, block: B:150:0x048a  */
    /* JADX WARN: Code duplicated, block: B:151:0x0490  */
    /* JADX WARN: Code duplicated, block: B:154:0x04c3  */
    /* JADX WARN: Code duplicated, block: B:157:0x04d8 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:161:0x055b  */
    /* JADX WARN: Code duplicated, block: B:162:0x055d  */
    /* JADX WARN: Code duplicated, block: B:165:0x0566  */
    /* JADX WARN: Code duplicated, block: B:168:0x056f  */
    /* JADX WARN: Code duplicated, block: B:171:0x0577 A[PHI: r4
  0x0577: PHI (r4v54 'drawerState' androidx.compose.material3.DrawerState) = 
  (r4v51 'drawerState' androidx.compose.material3.DrawerState)
  (r4v55 'drawerState' androidx.compose.material3.DrawerState)
 binds: [B:170:0x0575, B:166:0x056c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:172:0x0579  */
    /* JADX WARN: Code duplicated, block: B:175:0x058a  */
    /* JADX WARN: Code duplicated, block: B:178:0x0595 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:182:0x05be  */
    /* JADX WARN: Code duplicated, block: B:184:0x05c4  */
    /* JADX WARN: Code duplicated, block: B:190:0x05d8  */
    /* JADX WARN: Code duplicated, block: B:193:0x05e3 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:197:0x0619  */
    /* JADX WARN: Code duplicated, block: B:199:0x061f  */
    /* JADX WARN: Code duplicated, block: B:205:0x0634  */
    /* JADX WARN: Code duplicated, block: B:208:0x063d  */
    /* JADX WARN: Code duplicated, block: B:211:0x0666  */
    /* JADX WARN: Code duplicated, block: B:213:0x066c  */
    /* JADX WARN: Code duplicated, block: B:219:0x0684  */
    /* JADX WARN: Code duplicated, block: B:222:0x068f A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:226:0x06b9  */
    /* JADX WARN: Code duplicated, block: B:228:0x06bf  */
    /* JADX WARN: Code duplicated, block: B:234:0x06d5  */
    /* JADX WARN: Code duplicated, block: B:237:0x06e4  */
    /* JADX WARN: Code duplicated, block: B:240:0x073d  */
    /* JADX WARN: Code duplicated, block: B:243:0x0749  */
    /* JADX WARN: Code duplicated, block: B:244:0x074d  */
    /* JADX WARN: Code duplicated, block: B:247:0x077b  */
    /* JADX WARN: Code duplicated, block: B:249:0x078d  */
    /* JADX WARN: Code duplicated, block: B:250:0x0790  */
    /* JADX WARN: Code duplicated, block: B:254:0x07e7  */
    /* JADX WARN: Code duplicated, block: B:256:0x07f2  */
    /* JADX WARN: Code duplicated, block: B:259:0x0801  */
    /* JADX WARN: Code duplicated, block: B:261:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:68:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:72:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:83:0x00f5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:84:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:85:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:88:0x0101  */
    /* JADX WARN: Code duplicated, block: B:90:0x010c  */
    /* JADX WARN: Code duplicated, block: B:93:0x0112  */
    /* JADX WARN: Code duplicated, block: B:96:0x0125  */
    /* JADX WARN: Code duplicated, block: B:99:0x0157  */
    /* JADX INFO: renamed from: ModalNavigationDrawer-FHprtrg, reason: not valid java name */
    public static final void m2648ModalNavigationDrawerFHprtrg(Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, DrawerState drawerState, boolean gesturesEnabled, long scrimColor, final Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        DrawerState drawerState2;
        boolean gesturesEnabled2;
        long scrimColor2;
        int i2;
        boolean z;
        final Modifier modifier3;
        final DrawerState drawerState3;
        final boolean gesturesEnabled3;
        final long scrimColor3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier.Companion modifier4;
        Object it$iv$iv;
        Density density;
        Object value$iv;
        MutableState anchorsInitialized$delegate;
        boolean invalid$iv;
        Object it$iv;
        final MutableFloatState minValue$delegate;
        final FiniteAnimationSpec anchoredDraggableMotion;
        final FiniteAnimationSpec openMotion;
        boolean invalid$iv2;
        Object value$iv2;
        final Density density2;
        DrawerState drawerState4;
        Density density3;
        FiniteAnimationSpec anchoredDraggableMotion2;
        FiniteAnimationSpec anchoredDraggableMotion3;
        Object objConsume;
        boolean isRtl;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        int compositeKeyHash$iv$iv2;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function1;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        boolean z2;
        final DrawerState drawerState5;
        boolean z3;
        boolean invalid$iv3;
        Object it$iv2;
        boolean invalid$iv4;
        Object it$iv3;
        boolean invalid$iv5;
        Object value$iv3;
        boolean invalid$iv6;
        Object it$iv4;
        boolean invalid$iv7;
        Object it$iv5;
        MutableState anchorsInitialized$delegate2;
        int compositeKeyHash$iv;
        Function0<ComposeUiNode> constructor3;
        Composer $this$Layout_u24lambda_u240$iv;
        int compositeKeyHash$iv2;
        final Function2<? super Composer, ? super Integer, Unit> function4 = function2;
        Composer $composer2 = $composer.startRestartGroup(-1907430816);
        ComposerKt.sourceInformation($composer2, "C(ModalNavigationDrawer)N(drawerContent,modifier,drawerState,gesturesEnabled,scrimColor:c#ui.graphics.Color,content)339@13901L24,340@13951L33,341@14016L7,342@14054L34,343@14109L45,348@14357L7,349@14451L7,350@14543L7,352@14567L229,352@14556L240,359@14835L7,360@14870L3155:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? 4 : 2;
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
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                drawerState2 = drawerState;
                int i4 = $composer2.changed(drawerState2) ? 256 : 128;
                $dirty |= i4;
            } else {
                drawerState2 = drawerState;
            }
            $dirty |= i4;
        } else {
            drawerState2 = drawerState;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            gesturesEnabled2 = gesturesEnabled;
        } else if (($changed & 3072) == 0) {
            gesturesEnabled2 = gesturesEnabled;
            $dirty |= $composer2.changed(gesturesEnabled2) ? 2048 : 1024;
        } else {
            gesturesEnabled2 = gesturesEnabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                scrimColor2 = scrimColor;
                int i6 = $composer2.changed(scrimColor2) ? 16384 : 8192;
                $dirty |= i6;
            } else {
                scrimColor2 = scrimColor;
            }
            $dirty |= i6;
        } else {
            scrimColor2 = scrimColor;
        }
        if ((i & 32) == 0) {
            if ((196608 & $changed) == 0) {
                i2 = $composer2.changedInstance(function3) ? 131072 : 65536;
            }
            if ((74899 & $dirty) != 74898) {
                z = true;
            } else {
                z = false;
            }
            if ($composer2.shouldExecute(z, $dirty & 1)) {
                $composer2.startDefaults();
                ComposerKt.sourceInformation($composer2, "334@13715L39,336@13832L10");
                if (($changed & 1) != 0 || $composer2.getDefaultsInvalid()) {
                    if (i3 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i & 4) != 0) {
                        $dirty &= -897;
                        drawerState2 = rememberDrawerState(DrawerValue.Closed, null, $composer2, 6, 2);
                    }
                    if (i5 != 0) {
                        gesturesEnabled2 = true;
                    }
                    if ((i & 16) != 0) {
                        scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer2, 6);
                        $dirty &= -57345;
                    }
                } else {
                    $composer2.skipToGroupEnd();
                    if ((i & 4) != 0) {
                        $dirty &= -897;
                    }
                    if ((i & 16) != 0) {
                        $dirty &= -57345;
                    }
                    modifier4 = modifier2;
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1907430816, $dirty, -1, "androidx.compose.material3.ModalNavigationDrawer (NavigationDrawer.kt:338)");
                }
                ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart($composer2, 683737348, "CC(remember):Effects.kt#9igjgp");
                it$iv$iv = $composer2.rememberedValue();
                if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2);
                    $composer2.updateRememberedValue(value$iv$iv);
                    it$iv$iv = value$iv$iv;
                }
                final CoroutineScope scope = (CoroutineScope) it$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Strings.Companion companion = Strings.INSTANCE;
                final String navigationMenu = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(androidx.compose.ui.R.string.navigation_menu), $composer2, 0);
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer2.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                density = (Density) objConsume2;
                ComposerKt.sourceInformationMarkerStart($composer2, 910036834, "CC(remember):NavigationDrawer.kt#9igjgp");
                value$iv = $composer2.rememberedValue();
                if (value$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    $composer2.updateRememberedValue(value$iv);
                }
                anchorsInitialized$delegate = (MutableState) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, 910038605, "CC(remember):NavigationDrawer.kt#9igjgp");
                invalid$iv = $composer2.changed(density);
                it$iv = $composer2.rememberedValue();
                if (!invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv4 = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
                    $composer2.updateRememberedValue(value$iv4);
                    it$iv = value$iv4;
                }
                minValue$delegate = (MutableFloatState) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                final float maxValue = 0.0f;
                long scrimColor4 = scrimColor2;
                anchoredDraggableMotion = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer2, 6);
                openMotion = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer2, 6);
                final FiniteAnimationSpec closeMotion = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer2, 6);
                ComposerKt.sourceInformationMarkerStart($composer2, 910053445, "CC(remember):NavigationDrawer.kt#9igjgp");
                invalid$iv2 = (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState2)) || ($dirty & 384) == 256) | $composer2.changed(density) | $composer2.changedInstance(openMotion) | $composer2.changedInstance(closeMotion) | $composer2.changedInstance(anchoredDraggableMotion);
                value$iv2 = $composer2.rememberedValue();
                if (invalid$iv2) {
                    density2 = density;
                } else {
                    density2 = density;
                    if (value$iv2 != Composer.INSTANCE.getEmpty()) {
                        drawerState4 = drawerState2;
                        anchoredDraggableMotion3 = anchoredDraggableMotion;
                        anchoredDraggableMotion2 = openMotion;
                        density3 = density2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.SideEffect((Function0) value$iv2, $composer2, 0);
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    objConsume = $composer2.consume(localLayoutDirection);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (objConsume == LayoutDirection.Rtl) {
                        isRtl = true;
                    } else {
                        isRtl = false;
                    }
                    final boolean gesturesEnabled4 = gesturesEnabled2;
                    Modifier modifier$iv = AnchoredDraggableKt.anchoredDraggable(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, null), drawerState4.getAnchoredDraggableState$material3(), isRtl, Orientation.Horizontal, (112 & 8) != 0 ? true : gesturesEnabled4, (112 & 16) != 0 ? null : null, (112 & 32) != 0 ? null : null, (112 & 64) != 0 ? null : null);
                    Modifier modifier5 = modifier4;
                    ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                    int $changed$iv$iv = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                    CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
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
                    $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i7 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i8 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 101870438, "C370@15180L17,373@15274L183,378@15482L70,371@15206L389,384@15704L601,396@16337L452,407@16801L1218,381@15604L2415:NavigationDrawer.kt#uh7d8r");
                    ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Modifier modifier$iv2 = Modifier.INSTANCE;
                    Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                    int $changed$iv$iv2 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                    CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
                    constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                        function1 = constructor2;
                        $composer2.createNode(function1);
                    } else {
                        function1 = constructor2;
                        $composer2.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer2);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i9 = ($changed$iv$iv$iv2 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i10 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 343987054, "C370@15186L9:NavigationDrawer.kt#uh7d8r");
                    function3.invoke($composer2, Integer.valueOf(($dirty >> 15) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean zIsOpen = drawerState4.isOpen();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2081496477, "CC(remember):NavigationDrawer.kt#9igjgp");
                    if (($dirty & 7168) == 2048) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if ((($dirty & 896) ^ 384) > 256) {
                        drawerState5 = drawerState4;
                        if (!$composer2.changed(drawerState5)) {
                            z3 = true;
                        }
                        invalid$iv3 = z2 | z3 | $composer2.changedInstance(scope);
                        it$iv2 = $composer2.rememberedValue();
                        if (!invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                            Object value$iv5 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda12
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$14$lambda$13(gesturesEnabled4, drawerState5, scope);
                                }
                            };
                            $composer2.updateRememberedValue(value$iv5);
                            it$iv2 = value$iv5;
                        }
                        Function0 function5 = (Function0) it$iv2;
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerStart($composer2, 2081503020, "CC(remember):NavigationDrawer.kt#9igjgp");
                        invalid$iv4 = $composer2.changed(minValue$delegate) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256);
                        it$iv3 = $composer2.rememberedValue();
                        if (!invalid$iv4 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                            Object value$iv6 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda13
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return Float.valueOf(NavigationDrawerKt.calculateFraction(NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$8(minValue$delegate), maxValue, drawerState5.requireOffset$material3()));
                                }
                            };
                            $composer2.updateRememberedValue(value$iv6);
                            it$iv3 = value$iv6;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        m2650ScrimBx497Mc(zIsOpen, function5, (Function0) it$iv3, scrimColor4, $composer2, ($dirty >> 3) & 7168);
                        Modifier.Companion companion2 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer2, 2081510655, "CC(remember):NavigationDrawer.kt#9igjgp");
                        invalid$iv5 = ((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256;
                        value$iv3 = $composer2.rememberedValue();
                        if (invalid$iv5 || value$iv3 == Composer.INSTANCE.getEmpty()) {
                            value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda14
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$19$lambda$18(drawerState5, (Density) obj);
                                }
                            };
                            $composer2.updateRememberedValue(value$iv3);
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        Modifier modifierOffset = OffsetKt.offset(companion2, (Function1) value$iv3);
                        ComposerKt.sourceInformationMarkerStart($composer2, 2081530762, "CC(remember):NavigationDrawer.kt#9igjgp");
                        invalid$iv6 = $composer2.changed(navigationMenu) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope);
                        it$iv4 = $composer2.rememberedValue();
                        if (!invalid$iv6 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                            Object value$iv7 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda15
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$22$lambda$21(navigationMenu, drawerState5, scope, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            $composer2.updateRememberedValue(value$iv7);
                            it$iv4 = value$iv7;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        boolean z4 = true;
                        Modifier modifier$iv3 = SemanticsModifierKt.semantics$default(modifierOffset, false, (Function1) it$iv4, 1, null);
                        ComposerKt.sourceInformationMarkerStart($composer2, 2081546376, "CC(remember):NavigationDrawer.kt#9igjgp");
                        if (((($dirty & 896) ^ 384) > 256 || !$composer2.changed(drawerState5)) && ($dirty & 384) != 256) {
                        }
                        invalid$iv7 = z4 | $composer2.changed(minValue$delegate);
                        it$iv5 = $composer2.rememberedValue();
                        if (!invalid$iv7 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                            anchorsInitialized$delegate2 = anchorsInitialized$delegate;
                            Object value$iv8 = (MeasurePolicy) new NavigationDrawerKt$ModalNavigationDrawer$2$6$1(drawerState5, anchorsInitialized$delegate2, minValue$delegate, 0.0f);
                            $composer2.updateRememberedValue(value$iv8);
                            it$iv5 = value$iv8;
                        } else {
                            anchorsInitialized$delegate2 = anchorsInitialized$delegate;
                        }
                        MeasurePolicy measurePolicy$iv3 = (MeasurePolicy) it$iv5;
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        int $changed$iv = $dirty & 14;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                        compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                        CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
                        Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv3);
                        constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv3 = (($changed$iv << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                        if (!($composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer2.startReusableNode();
                        if ($composer2.getInserting()) {
                            $composer2.createNode(constructor3);
                        } else {
                            $composer2.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer2);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                            compositeKeyHash$iv2 = compositeKeyHash$iv;
                        } else {
                            compositeKeyHash$iv2 = compositeKeyHash$iv;
                            if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv2))) {
                            }
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                            function4 = function2;
                            function4.invoke($composer2, Integer.valueOf(($changed$iv$iv3 >> 6) & 14));
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            drawerState3 = drawerState5;
                            gesturesEnabled3 = gesturesEnabled4;
                            scrimColor3 = scrimColor4;
                            modifier3 = modifier5;
                        }
                        $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv2));
                        $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv2), setCompositeKeyHash3);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        function4 = function2;
                        function4.invoke($composer2, Integer.valueOf(($changed$iv$iv3 >> 6) & 14));
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        drawerState3 = drawerState5;
                        gesturesEnabled3 = gesturesEnabled4;
                        scrimColor3 = scrimColor4;
                        modifier3 = modifier5;
                    } else {
                        drawerState5 = drawerState4;
                    }
                    if (($dirty & 384) == 256) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    invalid$iv3 = z2 | z3 | $composer2.changedInstance(scope);
                    it$iv2 = $composer2.rememberedValue();
                    if (!invalid$iv3) {
                    }
                    Object value$iv9 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$14$lambda$13(gesturesEnabled4, drawerState5, scope);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv9);
                    it$iv2 = value$iv9;
                    Function0 function6 = (Function0) it$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 2081503020, "CC(remember):NavigationDrawer.kt#9igjgp");
                    invalid$iv4 = $composer2.changed(minValue$delegate) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256);
                    it$iv3 = $composer2.rememberedValue();
                    if (!invalid$iv4) {
                    }
                    Object value$iv10 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Float.valueOf(NavigationDrawerKt.calculateFraction(NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$8(minValue$delegate), maxValue, drawerState5.requireOffset$material3()));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv10);
                    it$iv3 = value$iv10;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    m2650ScrimBx497Mc(zIsOpen, function6, (Function0) it$iv3, scrimColor4, $composer2, ($dirty >> 3) & 7168);
                    Modifier.Companion companion3 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2081510655, "CC(remember):NavigationDrawer.kt#9igjgp");
                    if ((($dirty & 896) ^ 384) <= 256) {
                    }
                    value$iv3 = $composer2.rememberedValue();
                    if (invalid$iv5) {
                        value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$19$lambda$18(drawerState5, (Density) obj);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv3);
                    } else {
                        value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$19$lambda$18(drawerState5, (Density) obj);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv3);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier modifierOffset2 = OffsetKt.offset(companion3, (Function1) value$iv3);
                    ComposerKt.sourceInformationMarkerStart($composer2, 2081530762, "CC(remember):NavigationDrawer.kt#9igjgp");
                    invalid$iv6 = $composer2.changed(navigationMenu) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope);
                    it$iv4 = $composer2.rememberedValue();
                    if (!invalid$iv6) {
                    }
                    Object value$iv11 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$22$lambda$21(navigationMenu, drawerState5, scope, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv11);
                    it$iv4 = value$iv11;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean z5 = true;
                    Modifier modifier$iv4 = SemanticsModifierKt.semantics$default(modifierOffset2, false, (Function1) it$iv4, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 2081546376, "CC(remember):NavigationDrawer.kt#9igjgp");
                    z5 = (($dirty & 896) ^ 384) > 256 ? false : false;
                    invalid$iv7 = z5 | $composer2.changed(minValue$delegate);
                    it$iv5 = $composer2.rememberedValue();
                    if (invalid$iv7) {
                        anchorsInitialized$delegate2 = anchorsInitialized$delegate;
                        Object value$iv12 = (MeasurePolicy) new NavigationDrawerKt$ModalNavigationDrawer$2$6$1(drawerState5, anchorsInitialized$delegate2, minValue$delegate, 0.0f);
                        $composer2.updateRememberedValue(value$iv12);
                        it$iv5 = value$iv12;
                    } else {
                        anchorsInitialized$delegate2 = anchorsInitialized$delegate;
                        Object value$iv13 = (MeasurePolicy) new NavigationDrawerKt$ModalNavigationDrawer$2$6$1(drawerState5, anchorsInitialized$delegate2, minValue$delegate, 0.0f);
                        $composer2.updateRememberedValue(value$iv13);
                        it$iv5 = value$iv13;
                    }
                    MeasurePolicy measurePolicy$iv4 = (MeasurePolicy) it$iv5;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    int $changed$iv2 = $dirty & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                    CompositionLocalMap localMap$iv2 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materialized$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv4);
                    constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv4 = (($changed$iv2 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                        $composer2.createNode(constructor3);
                    } else {
                        $composer2.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer2);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                        compositeKeyHash$iv2 = compositeKeyHash$iv;
                        if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv2))) {
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                        function4 = function2;
                        function4.invoke($composer2, Integer.valueOf(($changed$iv$iv4 >> 6) & 14));
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        drawerState3 = drawerState5;
                        gesturesEnabled3 = gesturesEnabled4;
                        scrimColor3 = scrimColor4;
                        modifier3 = modifier5;
                    } else {
                        compositeKeyHash$iv2 = compositeKeyHash$iv;
                    }
                    $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv2));
                    $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv2), setCompositeKeyHash4);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                    function4 = function2;
                    function4.invoke($composer2, Integer.valueOf(($changed$iv$iv4 >> 6) & 14));
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    drawerState3 = drawerState5;
                    gesturesEnabled3 = gesturesEnabled4;
                    scrimColor3 = scrimColor4;
                    modifier3 = modifier5;
                }
                final DrawerState drawerState6 = drawerState2;
                drawerState4 = drawerState6;
                density3 = density2;
                anchoredDraggableMotion2 = openMotion;
                anchoredDraggableMotion3 = anchoredDraggableMotion;
                value$iv2 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$11$lambda$10(drawerState6, density2, openMotion, closeMotion, anchoredDraggableMotion);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.SideEffect((Function0) value$iv2, $composer2, 0);
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                objConsume = $composer2.consume(localLayoutDirection2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (objConsume == LayoutDirection.Rtl) {
                    isRtl = true;
                } else {
                    isRtl = false;
                }
                final boolean gesturesEnabled5 = gesturesEnabled2;
                Modifier modifier$iv5 = AnchoredDraggableKt.anchoredDraggable(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, null), drawerState4.getAnchoredDraggableState$material3(), isRtl, Orientation.Horizontal, (112 & 8) != 0 ? true : gesturesEnabled5, (112 & 16) != 0 ? null : null, (112 & 32) != 0 ? null : null, (112 & 64) != 0 ? null : null);
                Modifier modifier6 = modifier4;
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv5 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                int $changed$iv$iv5 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv3 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer2, modifier$iv5);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv3 = (($changed$iv$iv5 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
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
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash5);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                int i11 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                int i12 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 101870438, "C370@15180L17,373@15274L183,378@15482L70,371@15206L389,384@15704L601,396@16337L452,407@16801L1218,381@15604L2415:NavigationDrawer.kt#uh7d8r");
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Modifier modifier$iv6 = Modifier.INSTANCE;
                Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv6 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
                int $changed$iv$iv6 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv4 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer2, modifier$iv6);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv4 = (($changed$iv$iv6 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function1 = constructor2;
                    $composer2.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash6);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                int i13 = ($changed$iv$iv$iv4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                int i14 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 343987054, "C370@15186L9:NavigationDrawer.kt#uh7d8r");
                function3.invoke($composer2, Integer.valueOf(($dirty >> 15) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                boolean zIsOpen2 = drawerState4.isOpen();
                ComposerKt.sourceInformationMarkerStart($composer2, 2081496477, "CC(remember):NavigationDrawer.kt#9igjgp");
                if (($dirty & 7168) == 2048) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((($dirty & 896) ^ 384) > 256) {
                    drawerState5 = drawerState4;
                    if (!$composer2.changed(drawerState5)) {
                        z3 = true;
                    }
                    invalid$iv3 = z2 | z3 | $composer2.changedInstance(scope);
                    it$iv2 = $composer2.rememberedValue();
                    if (!invalid$iv3) {
                    }
                    Object value$iv14 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$14$lambda$13(gesturesEnabled5, drawerState5, scope);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv14);
                    it$iv2 = value$iv14;
                    Function0 function7 = (Function0) it$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 2081503020, "CC(remember):NavigationDrawer.kt#9igjgp");
                    invalid$iv4 = $composer2.changed(minValue$delegate) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256);
                    it$iv3 = $composer2.rememberedValue();
                    if (!invalid$iv4) {
                    }
                    Object value$iv15 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Float.valueOf(NavigationDrawerKt.calculateFraction(NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$8(minValue$delegate), maxValue, drawerState5.requireOffset$material3()));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv15);
                    it$iv3 = value$iv15;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    m2650ScrimBx497Mc(zIsOpen2, function7, (Function0) it$iv3, scrimColor4, $composer2, ($dirty >> 3) & 7168);
                    Modifier.Companion companion4 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2081510655, "CC(remember):NavigationDrawer.kt#9igjgp");
                    if ((($dirty & 896) ^ 384) <= 256) {
                    }
                    value$iv3 = $composer2.rememberedValue();
                    if (invalid$iv5) {
                        value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$19$lambda$18(drawerState5, (Density) obj);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv3);
                    } else {
                        value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$19$lambda$18(drawerState5, (Density) obj);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv3);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier modifierOffset3 = OffsetKt.offset(companion4, (Function1) value$iv3);
                    ComposerKt.sourceInformationMarkerStart($composer2, 2081530762, "CC(remember):NavigationDrawer.kt#9igjgp");
                    invalid$iv6 = $composer2.changed(navigationMenu) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope);
                    it$iv4 = $composer2.rememberedValue();
                    if (!invalid$iv6) {
                    }
                    Object value$iv16 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$22$lambda$21(navigationMenu, drawerState5, scope, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv16);
                    it$iv4 = value$iv16;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean z6 = true;
                    Modifier modifier$iv7 = SemanticsModifierKt.semantics$default(modifierOffset3, false, (Function1) it$iv4, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 2081546376, "CC(remember):NavigationDrawer.kt#9igjgp");
                    if ((($dirty & 896) ^ 384) > 256) {
                    }
                    invalid$iv7 = z6 | $composer2.changed(minValue$delegate);
                    it$iv5 = $composer2.rememberedValue();
                    if (invalid$iv7) {
                        anchorsInitialized$delegate2 = anchorsInitialized$delegate;
                        Object value$iv17 = (MeasurePolicy) new NavigationDrawerKt$ModalNavigationDrawer$2$6$1(drawerState5, anchorsInitialized$delegate2, minValue$delegate, 0.0f);
                        $composer2.updateRememberedValue(value$iv17);
                        it$iv5 = value$iv17;
                    } else {
                        anchorsInitialized$delegate2 = anchorsInitialized$delegate;
                        Object value$iv18 = (MeasurePolicy) new NavigationDrawerKt$ModalNavigationDrawer$2$6$1(drawerState5, anchorsInitialized$delegate2, minValue$delegate, 0.0f);
                        $composer2.updateRememberedValue(value$iv18);
                        it$iv5 = value$iv18;
                    }
                    MeasurePolicy measurePolicy$iv7 = (MeasurePolicy) it$iv5;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    int $changed$iv3 = $dirty & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                    CompositionLocalMap localMap$iv3 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materialized$iv3 = ComposedModifierKt.materializeModifier($composer2, modifier$iv7);
                    constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv7 = (($changed$iv3 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                        $composer2.createNode(constructor3);
                    } else {
                        $composer2.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer2);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                        compositeKeyHash$iv2 = compositeKeyHash$iv;
                        if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv2))) {
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                        function4 = function2;
                        function4.invoke($composer2, Integer.valueOf(($changed$iv$iv7 >> 6) & 14));
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        drawerState3 = drawerState5;
                        gesturesEnabled3 = gesturesEnabled5;
                        scrimColor3 = scrimColor4;
                        modifier3 = modifier6;
                    } else {
                        compositeKeyHash$iv2 = compositeKeyHash$iv;
                    }
                    $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv2));
                    $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv2), setCompositeKeyHash7);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                    function4 = function2;
                    function4.invoke($composer2, Integer.valueOf(($changed$iv$iv7 >> 6) & 14));
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    drawerState3 = drawerState5;
                    gesturesEnabled3 = gesturesEnabled5;
                    scrimColor3 = scrimColor4;
                    modifier3 = modifier6;
                } else {
                    drawerState5 = drawerState4;
                }
                if (($dirty & 384) == 256) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                invalid$iv3 = z2 | z3 | $composer2.changedInstance(scope);
                it$iv2 = $composer2.rememberedValue();
                if (!invalid$iv3) {
                }
                Object value$iv19 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$14$lambda$13(gesturesEnabled5, drawerState5, scope);
                    }
                };
                $composer2.updateRememberedValue(value$iv19);
                it$iv2 = value$iv19;
                Function0 function8 = (Function0) it$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, 2081503020, "CC(remember):NavigationDrawer.kt#9igjgp");
                invalid$iv4 = $composer2.changed(minValue$delegate) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256);
                it$iv3 = $composer2.rememberedValue();
                if (!invalid$iv4) {
                }
                Object value$iv110 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(NavigationDrawerKt.calculateFraction(NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$8(minValue$delegate), maxValue, drawerState5.requireOffset$material3()));
                    }
                };
                $composer2.updateRememberedValue(value$iv110);
                it$iv3 = value$iv110;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                m2650ScrimBx497Mc(zIsOpen2, function8, (Function0) it$iv3, scrimColor4, $composer2, ($dirty >> 3) & 7168);
                Modifier.Companion companion5 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, 2081510655, "CC(remember):NavigationDrawer.kt#9igjgp");
                if ((($dirty & 896) ^ 384) <= 256) {
                }
                value$iv3 = $composer2.rememberedValue();
                if (invalid$iv5) {
                    value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$19$lambda$18(drawerState5, (Density) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$19$lambda$18(drawerState5, (Density) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv3);
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierOffset4 = OffsetKt.offset(companion5, (Function1) value$iv3);
                ComposerKt.sourceInformationMarkerStart($composer2, 2081530762, "CC(remember):NavigationDrawer.kt#9igjgp");
                invalid$iv6 = $composer2.changed(navigationMenu) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope);
                it$iv4 = $composer2.rememberedValue();
                if (!invalid$iv6) {
                }
                Object value$iv111 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$22$lambda$21(navigationMenu, drawerState5, scope, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv111);
                it$iv4 = value$iv111;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                boolean z7 = true;
                Modifier modifier$iv8 = SemanticsModifierKt.semantics$default(modifierOffset4, false, (Function1) it$iv4, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 2081546376, "CC(remember):NavigationDrawer.kt#9igjgp");
                if ((($dirty & 896) ^ 384) > 256) {
                }
                invalid$iv7 = z7 | $composer2.changed(minValue$delegate);
                it$iv5 = $composer2.rememberedValue();
                if (invalid$iv7) {
                    anchorsInitialized$delegate2 = anchorsInitialized$delegate;
                    Object value$iv112 = (MeasurePolicy) new NavigationDrawerKt$ModalNavigationDrawer$2$6$1(drawerState5, anchorsInitialized$delegate2, minValue$delegate, 0.0f);
                    $composer2.updateRememberedValue(value$iv112);
                    it$iv5 = value$iv112;
                } else {
                    anchorsInitialized$delegate2 = anchorsInitialized$delegate;
                    Object value$iv113 = (MeasurePolicy) new NavigationDrawerKt$ModalNavigationDrawer$2$6$1(drawerState5, anchorsInitialized$delegate2, minValue$delegate, 0.0f);
                    $composer2.updateRememberedValue(value$iv113);
                    it$iv5 = value$iv113;
                }
                MeasurePolicy measurePolicy$iv8 = (MeasurePolicy) it$iv5;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                int $changed$iv4 = $dirty & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv4 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv4 = ComposedModifierKt.materializeModifier($composer2, modifier$iv8);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv8 = (($changed$iv4 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor3);
                } else {
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                    compositeKeyHash$iv2 = compositeKeyHash$iv;
                    if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv2))) {
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                    function4 = function2;
                    function4.invoke($composer2, Integer.valueOf(($changed$iv$iv8 >> 6) & 14));
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    drawerState3 = drawerState5;
                    gesturesEnabled3 = gesturesEnabled5;
                    scrimColor3 = scrimColor4;
                    modifier3 = modifier6;
                } else {
                    compositeKeyHash$iv2 = compositeKeyHash$iv;
                }
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv2));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv2), setCompositeKeyHash8);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                function4 = function2;
                function4.invoke($composer2, Integer.valueOf(($changed$iv$iv8 >> 6) & 14));
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                drawerState3 = drawerState5;
                gesturesEnabled3 = gesturesEnabled5;
                scrimColor3 = scrimColor4;
                modifier3 = modifier6;
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier2;
                drawerState3 = drawerState2;
                gesturesEnabled3 = gesturesEnabled2;
                scrimColor3 = scrimColor2;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$25(function4, modifier3, drawerState3, gesturesEnabled3, scrimColor3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i2 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        $dirty |= i2;
        if ((74899 & $dirty) != 74898) {
            z = true;
        } else {
            z = false;
        }
        if ($composer2.shouldExecute(z, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "334@13715L39,336@13832L10");
            if (($changed & 1) != 0) {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    drawerState2 = rememberDrawerState(DrawerValue.Closed, null, $composer2, 6, 2);
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                }
                if ((i & 16) != 0) {
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer2, 6);
                    $dirty &= -57345;
                }
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    drawerState2 = rememberDrawerState(DrawerValue.Closed, null, $composer2, 6, 2);
                }
                if (i5 != 0) {
                    gesturesEnabled2 = true;
                }
                if ((i & 16) != 0) {
                    scrimColor2 = DrawerDefaults.INSTANCE.getScrimColor($composer2, 6);
                    $dirty &= -57345;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1907430816, $dirty, -1, "androidx.compose.material3.ModalNavigationDrawer (NavigationDrawer.kt:338)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer2, 683737348, "CC(remember):Effects.kt#9igjgp");
            it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv2 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2);
                $composer2.updateRememberedValue(value$iv$iv2);
                it$iv$iv = value$iv$iv2;
            }
            final CoroutineScope scope2 = (CoroutineScope) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Strings.Companion companion6 = Strings.INSTANCE;
            final String navigationMenu2 = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(androidx.compose.ui.R.string.navigation_menu), $composer2, 0);
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            density = (Density) objConsume3;
            ComposerKt.sourceInformationMarkerStart($composer2, 910036834, "CC(remember):NavigationDrawer.kt#9igjgp");
            value$iv = $composer2.rememberedValue();
            if (value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer2.updateRememberedValue(value$iv);
            }
            anchorsInitialized$delegate = (MutableState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 910038605, "CC(remember):NavigationDrawer.kt#9igjgp");
            invalid$iv = $composer2.changed(density);
            it$iv = $composer2.rememberedValue();
            if (!invalid$iv) {
            }
            Object value$iv20 = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
            $composer2.updateRememberedValue(value$iv20);
            it$iv = value$iv20;
            minValue$delegate = (MutableFloatState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final float maxValue2 = 0.0f;
            long scrimColor5 = scrimColor2;
            anchoredDraggableMotion = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer2, 6);
            openMotion = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer2, 6);
            final FiniteAnimationSpec closeMotion2 = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer2, 6);
            ComposerKt.sourceInformationMarkerStart($composer2, 910053445, "CC(remember):NavigationDrawer.kt#9igjgp");
            invalid$iv2 = (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState2)) || ($dirty & 384) == 256) | $composer2.changed(density) | $composer2.changedInstance(openMotion) | $composer2.changedInstance(closeMotion2) | $composer2.changedInstance(anchoredDraggableMotion);
            value$iv2 = $composer2.rememberedValue();
            if (invalid$iv2) {
                density2 = density;
                if (value$iv2 != Composer.INSTANCE.getEmpty()) {
                    drawerState4 = drawerState2;
                    anchoredDraggableMotion3 = anchoredDraggableMotion;
                    anchoredDraggableMotion2 = openMotion;
                    density3 = density2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.SideEffect((Function0) value$iv2, $composer2, 0);
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                objConsume = $composer2.consume(localLayoutDirection3);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (objConsume == LayoutDirection.Rtl) {
                    isRtl = true;
                } else {
                    isRtl = false;
                }
                final boolean gesturesEnabled6 = gesturesEnabled2;
                Modifier modifier$iv9 = AnchoredDraggableKt.anchoredDraggable(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, null), drawerState4.getAnchoredDraggableState$material3(), isRtl, Orientation.Horizontal, (112 & 8) != 0 ? true : gesturesEnabled6, (112 & 16) != 0 ? null : null, (112 & 32) != 0 ? null : null, (112 & 64) != 0 ? null : null);
                Modifier modifier7 = modifier4;
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv5 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv9 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv5, false);
                int $changed$iv$iv9 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv5 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer2, modifier$iv9);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv5 = (($changed$iv$iv9 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
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
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv9, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash9 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash9);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
                int i15 = ($changed$iv$iv$iv5 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                int i16 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 101870438, "C370@15180L17,373@15274L183,378@15482L70,371@15206L389,384@15704L601,396@16337L452,407@16801L1218,381@15604L2415:NavigationDrawer.kt#uh7d8r");
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Modifier modifier$iv10 = Modifier.INSTANCE;
                Alignment contentAlignment$iv6 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv10 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv6, false);
                int $changed$iv$iv10 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv6 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv6 = ComposedModifierKt.materializeModifier($composer2, modifier$iv10);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv6 = (($changed$iv$iv10 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function1 = constructor2;
                    $composer2.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv10, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash10 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash10);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv6, ComposeUiNode.INSTANCE.getSetModifier());
                int i17 = ($changed$iv$iv$iv6 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                int i18 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 343987054, "C370@15186L9:NavigationDrawer.kt#uh7d8r");
                function3.invoke($composer2, Integer.valueOf(($dirty >> 15) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                boolean zIsOpen3 = drawerState4.isOpen();
                ComposerKt.sourceInformationMarkerStart($composer2, 2081496477, "CC(remember):NavigationDrawer.kt#9igjgp");
                if (($dirty & 7168) == 2048) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((($dirty & 896) ^ 384) > 256) {
                    drawerState5 = drawerState4;
                    if (!$composer2.changed(drawerState5)) {
                        z3 = true;
                    }
                    invalid$iv3 = z2 | z3 | $composer2.changedInstance(scope2);
                    it$iv2 = $composer2.rememberedValue();
                    if (!invalid$iv3) {
                    }
                    Object value$iv114 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$14$lambda$13(gesturesEnabled6, drawerState5, scope2);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv114);
                    it$iv2 = value$iv114;
                    Function0 function9 = (Function0) it$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 2081503020, "CC(remember):NavigationDrawer.kt#9igjgp");
                    invalid$iv4 = $composer2.changed(minValue$delegate) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256);
                    it$iv3 = $composer2.rememberedValue();
                    if (!invalid$iv4) {
                    }
                    Object value$iv115 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Float.valueOf(NavigationDrawerKt.calculateFraction(NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$8(minValue$delegate), maxValue2, drawerState5.requireOffset$material3()));
                        }
                    };
                    $composer2.updateRememberedValue(value$iv115);
                    it$iv3 = value$iv115;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    m2650ScrimBx497Mc(zIsOpen3, function9, (Function0) it$iv3, scrimColor5, $composer2, ($dirty >> 3) & 7168);
                    Modifier.Companion companion7 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2081510655, "CC(remember):NavigationDrawer.kt#9igjgp");
                    if ((($dirty & 896) ^ 384) <= 256) {
                    }
                    value$iv3 = $composer2.rememberedValue();
                    if (invalid$iv5) {
                        value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$19$lambda$18(drawerState5, (Density) obj);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv3);
                    } else {
                        value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$19$lambda$18(drawerState5, (Density) obj);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv3);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier modifierOffset5 = OffsetKt.offset(companion7, (Function1) value$iv3);
                    ComposerKt.sourceInformationMarkerStart($composer2, 2081530762, "CC(remember):NavigationDrawer.kt#9igjgp");
                    invalid$iv6 = $composer2.changed(navigationMenu2) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope2);
                    it$iv4 = $composer2.rememberedValue();
                    if (!invalid$iv6) {
                    }
                    Object value$iv116 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$22$lambda$21(navigationMenu2, drawerState5, scope2, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv116);
                    it$iv4 = value$iv116;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean z8 = true;
                    Modifier modifier$iv11 = SemanticsModifierKt.semantics$default(modifierOffset5, false, (Function1) it$iv4, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 2081546376, "CC(remember):NavigationDrawer.kt#9igjgp");
                    if ((($dirty & 896) ^ 384) > 256) {
                    }
                    invalid$iv7 = z8 | $composer2.changed(minValue$delegate);
                    it$iv5 = $composer2.rememberedValue();
                    if (invalid$iv7) {
                        anchorsInitialized$delegate2 = anchorsInitialized$delegate;
                        Object value$iv117 = (MeasurePolicy) new NavigationDrawerKt$ModalNavigationDrawer$2$6$1(drawerState5, anchorsInitialized$delegate2, minValue$delegate, 0.0f);
                        $composer2.updateRememberedValue(value$iv117);
                        it$iv5 = value$iv117;
                    } else {
                        anchorsInitialized$delegate2 = anchorsInitialized$delegate;
                        Object value$iv118 = (MeasurePolicy) new NavigationDrawerKt$ModalNavigationDrawer$2$6$1(drawerState5, anchorsInitialized$delegate2, minValue$delegate, 0.0f);
                        $composer2.updateRememberedValue(value$iv118);
                        it$iv5 = value$iv118;
                    }
                    MeasurePolicy measurePolicy$iv11 = (MeasurePolicy) it$iv5;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    int $changed$iv5 = $dirty & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                    CompositionLocalMap localMap$iv5 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materialized$iv5 = ComposedModifierKt.materializeModifier($composer2, modifier$iv11);
                    constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv11 = (($changed$iv5 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                        $composer2.createNode(constructor3);
                    } else {
                        $composer2.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer2);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv11, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash11 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                        compositeKeyHash$iv2 = compositeKeyHash$iv;
                        if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv2))) {
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv5, ComposeUiNode.INSTANCE.getSetModifier());
                        function4 = function2;
                        function4.invoke($composer2, Integer.valueOf(($changed$iv$iv11 >> 6) & 14));
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        drawerState3 = drawerState5;
                        gesturesEnabled3 = gesturesEnabled6;
                        scrimColor3 = scrimColor5;
                        modifier3 = modifier7;
                    } else {
                        compositeKeyHash$iv2 = compositeKeyHash$iv;
                    }
                    $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv2));
                    $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv2), setCompositeKeyHash11);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv5, ComposeUiNode.INSTANCE.getSetModifier());
                    function4 = function2;
                    function4.invoke($composer2, Integer.valueOf(($changed$iv$iv11 >> 6) & 14));
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    drawerState3 = drawerState5;
                    gesturesEnabled3 = gesturesEnabled6;
                    scrimColor3 = scrimColor5;
                    modifier3 = modifier7;
                } else {
                    drawerState5 = drawerState4;
                }
                if (($dirty & 384) == 256) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                invalid$iv3 = z2 | z3 | $composer2.changedInstance(scope2);
                it$iv2 = $composer2.rememberedValue();
                if (!invalid$iv3) {
                }
                Object value$iv119 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$14$lambda$13(gesturesEnabled6, drawerState5, scope2);
                    }
                };
                $composer2.updateRememberedValue(value$iv119);
                it$iv2 = value$iv119;
                Function0 function10 = (Function0) it$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, 2081503020, "CC(remember):NavigationDrawer.kt#9igjgp");
                invalid$iv4 = $composer2.changed(minValue$delegate) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256);
                it$iv3 = $composer2.rememberedValue();
                if (!invalid$iv4) {
                }
                Object value$iv1110 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(NavigationDrawerKt.calculateFraction(NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$8(minValue$delegate), maxValue2, drawerState5.requireOffset$material3()));
                    }
                };
                $composer2.updateRememberedValue(value$iv1110);
                it$iv3 = value$iv1110;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                m2650ScrimBx497Mc(zIsOpen3, function10, (Function0) it$iv3, scrimColor5, $composer2, ($dirty >> 3) & 7168);
                Modifier.Companion companion8 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, 2081510655, "CC(remember):NavigationDrawer.kt#9igjgp");
                if ((($dirty & 896) ^ 384) <= 256) {
                }
                value$iv3 = $composer2.rememberedValue();
                if (invalid$iv5) {
                    value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$19$lambda$18(drawerState5, (Density) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$19$lambda$18(drawerState5, (Density) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv3);
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierOffset6 = OffsetKt.offset(companion8, (Function1) value$iv3);
                ComposerKt.sourceInformationMarkerStart($composer2, 2081530762, "CC(remember):NavigationDrawer.kt#9igjgp");
                invalid$iv6 = $composer2.changed(navigationMenu2) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope2);
                it$iv4 = $composer2.rememberedValue();
                if (!invalid$iv6) {
                }
                Object value$iv1111 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$22$lambda$21(navigationMenu2, drawerState5, scope2, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv1111);
                it$iv4 = value$iv1111;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                boolean z9 = true;
                Modifier modifier$iv12 = SemanticsModifierKt.semantics$default(modifierOffset6, false, (Function1) it$iv4, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 2081546376, "CC(remember):NavigationDrawer.kt#9igjgp");
                if ((($dirty & 896) ^ 384) > 256) {
                }
                invalid$iv7 = z9 | $composer2.changed(minValue$delegate);
                it$iv5 = $composer2.rememberedValue();
                if (invalid$iv7) {
                    anchorsInitialized$delegate2 = anchorsInitialized$delegate;
                    Object value$iv1112 = (MeasurePolicy) new NavigationDrawerKt$ModalNavigationDrawer$2$6$1(drawerState5, anchorsInitialized$delegate2, minValue$delegate, 0.0f);
                    $composer2.updateRememberedValue(value$iv1112);
                    it$iv5 = value$iv1112;
                } else {
                    anchorsInitialized$delegate2 = anchorsInitialized$delegate;
                    Object value$iv1113 = (MeasurePolicy) new NavigationDrawerKt$ModalNavigationDrawer$2$6$1(drawerState5, anchorsInitialized$delegate2, minValue$delegate, 0.0f);
                    $composer2.updateRememberedValue(value$iv1113);
                    it$iv5 = value$iv1113;
                }
                MeasurePolicy measurePolicy$iv12 = (MeasurePolicy) it$iv5;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                int $changed$iv6 = $dirty & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv6 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv6 = ComposedModifierKt.materializeModifier($composer2, modifier$iv12);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv12 = (($changed$iv6 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor3);
                } else {
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv12, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash12 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                    compositeKeyHash$iv2 = compositeKeyHash$iv;
                    if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv2))) {
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv6, ComposeUiNode.INSTANCE.getSetModifier());
                    function4 = function2;
                    function4.invoke($composer2, Integer.valueOf(($changed$iv$iv12 >> 6) & 14));
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    drawerState3 = drawerState5;
                    gesturesEnabled3 = gesturesEnabled6;
                    scrimColor3 = scrimColor5;
                    modifier3 = modifier7;
                } else {
                    compositeKeyHash$iv2 = compositeKeyHash$iv;
                }
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv2));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv2), setCompositeKeyHash12);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv6, ComposeUiNode.INSTANCE.getSetModifier());
                function4 = function2;
                function4.invoke($composer2, Integer.valueOf(($changed$iv$iv12 >> 6) & 14));
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                drawerState3 = drawerState5;
                gesturesEnabled3 = gesturesEnabled6;
                scrimColor3 = scrimColor5;
                modifier3 = modifier7;
            } else {
                density2 = density;
            }
            final DrawerState drawerState7 = drawerState2;
            drawerState4 = drawerState7;
            density3 = density2;
            anchoredDraggableMotion2 = openMotion;
            anchoredDraggableMotion3 = anchoredDraggableMotion;
            value$iv2 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$11$lambda$10(drawerState7, density2, openMotion, closeMotion2, anchoredDraggableMotion);
                }
            };
            $composer2.updateRememberedValue(value$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.SideEffect((Function0) value$iv2, $composer2, 0);
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection4 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            objConsume = $composer2.consume(localLayoutDirection4);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (objConsume == LayoutDirection.Rtl) {
                isRtl = true;
            } else {
                isRtl = false;
            }
            final boolean gesturesEnabled7 = gesturesEnabled2;
            Modifier modifier$iv13 = AnchoredDraggableKt.anchoredDraggable(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, null), drawerState4.getAnchoredDraggableState$material3(), isRtl, Orientation.Horizontal, (112 & 8) != 0 ? true : gesturesEnabled7, (112 & 16) != 0 ? null : null, (112 & 32) != 0 ? null : null, (112 & 64) != 0 ? null : null);
            Modifier modifier8 = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv7 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv13 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv7, false);
            int $changed$iv$iv13 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv7 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv7 = ComposedModifierKt.materializeModifier($composer2, modifier$iv13);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv7 = (($changed$iv$iv13 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
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
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv13, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash13 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash13);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv7, ComposeUiNode.INSTANCE.getSetModifier());
            int i19 = ($changed$iv$iv$iv7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
            int i110 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 101870438, "C370@15180L17,373@15274L183,378@15482L70,371@15206L389,384@15704L601,396@16337L452,407@16801L1218,381@15604L2415:NavigationDrawer.kt#uh7d8r");
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Modifier modifier$iv14 = Modifier.INSTANCE;
            Alignment contentAlignment$iv8 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv14 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv8, false);
            int $changed$iv$iv14 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv8 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv8 = ComposedModifierKt.materializeModifier($composer2, modifier$iv14);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv8 = (($changed$iv$iv14 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function1 = constructor2;
                $composer2.createNode(function1);
            } else {
                function1 = constructor2;
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv14, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash14 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash14);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv8, ComposeUiNode.INSTANCE.getSetModifier());
            int i111 = ($changed$iv$iv$iv8 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
            int i112 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 343987054, "C370@15186L9:NavigationDrawer.kt#uh7d8r");
            function3.invoke($composer2, Integer.valueOf(($dirty >> 15) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            boolean zIsOpen4 = drawerState4.isOpen();
            ComposerKt.sourceInformationMarkerStart($composer2, 2081496477, "CC(remember):NavigationDrawer.kt#9igjgp");
            if (($dirty & 7168) == 2048) {
                z2 = true;
            } else {
                z2 = false;
            }
            if ((($dirty & 896) ^ 384) > 256) {
                drawerState5 = drawerState4;
                if (!$composer2.changed(drawerState5)) {
                    z3 = true;
                }
                invalid$iv3 = z2 | z3 | $composer2.changedInstance(scope2);
                it$iv2 = $composer2.rememberedValue();
                if (!invalid$iv3) {
                }
                Object value$iv1114 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$14$lambda$13(gesturesEnabled7, drawerState5, scope2);
                    }
                };
                $composer2.updateRememberedValue(value$iv1114);
                it$iv2 = value$iv1114;
                Function0 function11 = (Function0) it$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, 2081503020, "CC(remember):NavigationDrawer.kt#9igjgp");
                invalid$iv4 = $composer2.changed(minValue$delegate) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256);
                it$iv3 = $composer2.rememberedValue();
                if (!invalid$iv4) {
                }
                Object value$iv1115 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(NavigationDrawerKt.calculateFraction(NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$8(minValue$delegate), maxValue2, drawerState5.requireOffset$material3()));
                    }
                };
                $composer2.updateRememberedValue(value$iv1115);
                it$iv3 = value$iv1115;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                m2650ScrimBx497Mc(zIsOpen4, function11, (Function0) it$iv3, scrimColor5, $composer2, ($dirty >> 3) & 7168);
                Modifier.Companion companion9 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, 2081510655, "CC(remember):NavigationDrawer.kt#9igjgp");
                if ((($dirty & 896) ^ 384) <= 256) {
                }
                value$iv3 = $composer2.rememberedValue();
                if (invalid$iv5) {
                    value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$19$lambda$18(drawerState5, (Density) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$19$lambda$18(drawerState5, (Density) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv3);
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierOffset7 = OffsetKt.offset(companion9, (Function1) value$iv3);
                ComposerKt.sourceInformationMarkerStart($composer2, 2081530762, "CC(remember):NavigationDrawer.kt#9igjgp");
                invalid$iv6 = $composer2.changed(navigationMenu2) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope2);
                it$iv4 = $composer2.rememberedValue();
                if (!invalid$iv6) {
                }
                Object value$iv1116 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$22$lambda$21(navigationMenu2, drawerState5, scope2, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv1116);
                it$iv4 = value$iv1116;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                boolean z10 = true;
                Modifier modifier$iv15 = SemanticsModifierKt.semantics$default(modifierOffset7, false, (Function1) it$iv4, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 2081546376, "CC(remember):NavigationDrawer.kt#9igjgp");
                if ((($dirty & 896) ^ 384) > 256) {
                }
                invalid$iv7 = z10 | $composer2.changed(minValue$delegate);
                it$iv5 = $composer2.rememberedValue();
                if (invalid$iv7) {
                    anchorsInitialized$delegate2 = anchorsInitialized$delegate;
                    Object value$iv1117 = (MeasurePolicy) new NavigationDrawerKt$ModalNavigationDrawer$2$6$1(drawerState5, anchorsInitialized$delegate2, minValue$delegate, 0.0f);
                    $composer2.updateRememberedValue(value$iv1117);
                    it$iv5 = value$iv1117;
                } else {
                    anchorsInitialized$delegate2 = anchorsInitialized$delegate;
                    Object value$iv1118 = (MeasurePolicy) new NavigationDrawerKt$ModalNavigationDrawer$2$6$1(drawerState5, anchorsInitialized$delegate2, minValue$delegate, 0.0f);
                    $composer2.updateRememberedValue(value$iv1118);
                    it$iv5 = value$iv1118;
                }
                MeasurePolicy measurePolicy$iv15 = (MeasurePolicy) it$iv5;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                int $changed$iv7 = $dirty & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv7 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv7 = ComposedModifierKt.materializeModifier($composer2, modifier$iv15);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv15 = (($changed$iv7 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor3);
                } else {
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv15, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash15 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                    compositeKeyHash$iv2 = compositeKeyHash$iv;
                    if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv2))) {
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv7, ComposeUiNode.INSTANCE.getSetModifier());
                    function4 = function2;
                    function4.invoke($composer2, Integer.valueOf(($changed$iv$iv15 >> 6) & 14));
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    drawerState3 = drawerState5;
                    gesturesEnabled3 = gesturesEnabled7;
                    scrimColor3 = scrimColor5;
                    modifier3 = modifier8;
                } else {
                    compositeKeyHash$iv2 = compositeKeyHash$iv;
                }
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv2));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv2), setCompositeKeyHash15);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv7, ComposeUiNode.INSTANCE.getSetModifier());
                function4 = function2;
                function4.invoke($composer2, Integer.valueOf(($changed$iv$iv15 >> 6) & 14));
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                drawerState3 = drawerState5;
                gesturesEnabled3 = gesturesEnabled7;
                scrimColor3 = scrimColor5;
                modifier3 = modifier8;
            } else {
                drawerState5 = drawerState4;
            }
            if (($dirty & 384) == 256) {
                z3 = true;
            } else {
                z3 = false;
            }
            invalid$iv3 = z2 | z3 | $composer2.changedInstance(scope2);
            it$iv2 = $composer2.rememberedValue();
            if (!invalid$iv3) {
            }
            Object value$iv1119 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$14$lambda$13(gesturesEnabled7, drawerState5, scope2);
                }
            };
            $composer2.updateRememberedValue(value$iv1119);
            it$iv2 = value$iv1119;
            Function0 function12 = (Function0) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 2081503020, "CC(remember):NavigationDrawer.kt#9igjgp");
            invalid$iv4 = $composer2.changed(minValue$delegate) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256);
            it$iv3 = $composer2.rememberedValue();
            if (!invalid$iv4) {
            }
            Object value$iv11110 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Float.valueOf(NavigationDrawerKt.calculateFraction(NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$8(minValue$delegate), maxValue2, drawerState5.requireOffset$material3()));
                }
            };
            $composer2.updateRememberedValue(value$iv11110);
            it$iv3 = value$iv11110;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            m2650ScrimBx497Mc(zIsOpen4, function12, (Function0) it$iv3, scrimColor5, $composer2, ($dirty >> 3) & 7168);
            Modifier.Companion companion10 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 2081510655, "CC(remember):NavigationDrawer.kt#9igjgp");
            if ((($dirty & 896) ^ 384) <= 256) {
            }
            value$iv3 = $composer2.rememberedValue();
            if (invalid$iv5) {
                value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$19$lambda$18(drawerState5, (Density) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$19$lambda$18(drawerState5, (Density) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierOffset8 = OffsetKt.offset(companion10, (Function1) value$iv3);
            ComposerKt.sourceInformationMarkerStart($composer2, 2081530762, "CC(remember):NavigationDrawer.kt#9igjgp");
            invalid$iv6 = $composer2.changed(navigationMenu2) | (((($dirty & 896) ^ 384) <= 256 && $composer2.changed(drawerState5)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope2);
            it$iv4 = $composer2.rememberedValue();
            if (!invalid$iv6) {
            }
            Object value$iv11111 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$22$lambda$21(navigationMenu2, drawerState5, scope2, (SemanticsPropertyReceiver) obj);
                }
            };
            $composer2.updateRememberedValue(value$iv11111);
            it$iv4 = value$iv11111;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            boolean z11 = true;
            Modifier modifier$iv16 = SemanticsModifierKt.semantics$default(modifierOffset8, false, (Function1) it$iv4, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 2081546376, "CC(remember):NavigationDrawer.kt#9igjgp");
            if ((($dirty & 896) ^ 384) > 256) {
            }
            invalid$iv7 = z11 | $composer2.changed(minValue$delegate);
            it$iv5 = $composer2.rememberedValue();
            if (invalid$iv7) {
                anchorsInitialized$delegate2 = anchorsInitialized$delegate;
                Object value$iv11112 = (MeasurePolicy) new NavigationDrawerKt$ModalNavigationDrawer$2$6$1(drawerState5, anchorsInitialized$delegate2, minValue$delegate, 0.0f);
                $composer2.updateRememberedValue(value$iv11112);
                it$iv5 = value$iv11112;
            } else {
                anchorsInitialized$delegate2 = anchorsInitialized$delegate;
                Object value$iv11113 = (MeasurePolicy) new NavigationDrawerKt$ModalNavigationDrawer$2$6$1(drawerState5, anchorsInitialized$delegate2, minValue$delegate, 0.0f);
                $composer2.updateRememberedValue(value$iv11113);
                it$iv5 = value$iv11113;
            }
            MeasurePolicy measurePolicy$iv16 = (MeasurePolicy) it$iv5;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            int $changed$iv8 = $dirty & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv8 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv8 = ComposedModifierKt.materializeModifier($composer2, modifier$iv16);
            constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv16 = (($changed$iv8 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor3);
            } else {
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv16, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash16 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                compositeKeyHash$iv2 = compositeKeyHash$iv;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv2))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv8, ComposeUiNode.INSTANCE.getSetModifier());
                function4 = function2;
                function4.invoke($composer2, Integer.valueOf(($changed$iv$iv16 >> 6) & 14));
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                drawerState3 = drawerState5;
                gesturesEnabled3 = gesturesEnabled7;
                scrimColor3 = scrimColor5;
                modifier3 = modifier8;
            } else {
                compositeKeyHash$iv2 = compositeKeyHash$iv;
            }
            $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv2));
            $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv2), setCompositeKeyHash16);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv8, ComposeUiNode.INSTANCE.getSetModifier());
            function4 = function2;
            function4.invoke($composer2, Integer.valueOf(($changed$iv$iv16 >> 6) & 14));
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            drawerState3 = drawerState5;
            gesturesEnabled3 = gesturesEnabled7;
            scrimColor3 = scrimColor5;
            modifier3 = modifier8;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            drawerState3 = drawerState2;
            gesturesEnabled3 = gesturesEnabled2;
            scrimColor3 = scrimColor2;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$25(function4, modifier3, drawerState3, gesturesEnabled3, scrimColor3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ModalNavigationDrawer_FHprtrg$lambda$5(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ModalNavigationDrawer_FHprtrg$lambda$6(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float ModalNavigationDrawer_FHprtrg$lambda$8(MutableFloatState $minValue$delegate) {
        MutableFloatState $this$getValue$iv = $minValue$delegate;
        return $this$getValue$iv.getFloatValue();
    }

    static final Unit ModalNavigationDrawer_FHprtrg$lambda$11$lambda$10(DrawerState $drawerState, Density $density, FiniteAnimationSpec $openMotion, FiniteAnimationSpec $closeMotion, FiniteAnimationSpec $anchoredDraggableMotion) {
        $drawerState.setDensity$material3($density);
        $drawerState.setOpenDrawerMotionSpec$material3($openMotion);
        $drawerState.setCloseDrawerMotionSpec$material3($closeMotion);
        $drawerState.setAnchoredDraggableMotionSpec$material3($anchoredDraggableMotion);
        return Unit.INSTANCE;
    }

    static final Unit ModalNavigationDrawer_FHprtrg$lambda$24$lambda$14$lambda$13(boolean $gesturesEnabled, DrawerState $drawerState, CoroutineScope $scope) {
        if ($gesturesEnabled && $drawerState.getConfirmStateChange$material3().invoke(DrawerValue.Closed).booleanValue()) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new NavigationDrawerKt$ModalNavigationDrawer$2$2$1$1($drawerState, null), 3, null);
        }
        return Unit.INSTANCE;
    }

    static final IntOffset ModalNavigationDrawer_FHprtrg$lambda$24$lambda$19$lambda$18(DrawerState $drawerState, Density $this$offset) {
        int offsetX;
        float offset = $drawerState.getCurrentOffset();
        if (Float.isNaN(offset)) {
            offsetX = $drawerState.isOpen() ? 0 : -$this$offset.mo400roundToPx0680j_4(DrawerDefaults.INSTANCE.m2408getMaximumDrawerWidthD9Ej5fM());
        } else {
            offsetX = MathKt.roundToInt(offset);
        }
        return IntOffset.m8024boximpl(IntOffset.m8027constructorimpl((((long) offsetX) << 32) | (((long) 0) & 4294967295L)));
    }

    static final Unit ModalNavigationDrawer_FHprtrg$lambda$24$lambda$22$lambda$21(String $navigationMenu, final DrawerState $drawerState, final CoroutineScope $scope, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setPaneTitle($this$semantics, $navigationMenu);
        if ($drawerState.isOpen()) {
            SemanticsPropertiesKt.dismiss$default($this$semantics, null, new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(NavigationDrawerKt.ModalNavigationDrawer_FHprtrg$lambda$24$lambda$22$lambda$21$lambda$20($drawerState, $scope));
                }
            }, 1, null);
        }
        return Unit.INSTANCE;
    }

    static final boolean ModalNavigationDrawer_FHprtrg$lambda$24$lambda$22$lambda$21$lambda$20(DrawerState $drawerState, CoroutineScope $scope) {
        if ($drawerState.getConfirmStateChange$material3().invoke(DrawerValue.Closed).booleanValue()) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new NavigationDrawerKt$ModalNavigationDrawer$2$5$1$1$1($drawerState, null), 3, null);
            return true;
        }
        return true;
    }

    public static final void DismissibleNavigationDrawer(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, DrawerState drawerState, boolean gesturesEnabled, Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final DrawerState drawerState2;
        boolean z;
        Function2<? super Composer, ? super Integer, Unit> function4;
        final Modifier modifier3;
        final boolean gesturesEnabled2;
        final DrawerState drawerState3;
        Modifier modifier4;
        boolean gesturesEnabled3;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function5;
        Function0<ComposeUiNode> function6;
        Composer $composer2 = $composer.startRestartGroup(1150092038);
        ComposerKt.sourceInformation($composer2, "C(DismissibleNavigationDrawer)N(drawerContent,modifier,drawerState,gesturesEnabled,content)464@19353L34,465@19419L7,468@19586L7,469@19678L7,471@19702L155,471@19691L166,477@19875L24,478@19925L33,480@19997L7,481@20032L2189:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
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
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                drawerState2 = drawerState;
                int i3 = $composer2.changed(drawerState2) ? 256 : 128;
                $dirty |= i3;
            } else {
                drawerState2 = drawerState;
            }
            $dirty |= i3;
        } else {
            drawerState2 = drawerState;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            z = gesturesEnabled;
        } else if (($changed & 3072) == 0) {
            z = gesturesEnabled;
            $dirty |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = gesturesEnabled;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 16384 : 8192;
        }
        if ($composer2.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "460@19204L39");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    drawerState2 = rememberDrawerState(DrawerValue.Closed, null, $composer2, 6, 2);
                }
                if (i4 != 0) {
                    modifier4 = modifier5;
                    gesturesEnabled3 = true;
                } else {
                    modifier4 = modifier5;
                    gesturesEnabled3 = z;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier4 = modifier2;
                gesturesEnabled3 = z;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1150092038, $dirty, -1, "androidx.compose.material3.DismissibleNavigationDrawer (NavigationDrawer.kt:463)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1431863176, "CC(remember):NavigationDrawer.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            MutableState anchorsInitialized$delegate = (MutableState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final Density density = (Density) objConsume;
            final FiniteAnimationSpec openMotion = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer2, 6);
            final FiniteAnimationSpec closeMotion = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer2, 6);
            ComposerKt.sourceInformationMarkerStart($composer2, 1431874465, "CC(remember):NavigationDrawer.kt#9igjgp");
            boolean invalid$iv = (((($dirty & 896) ^ 384) > 256 && $composer2.changed(drawerState2)) || ($dirty & 384) == 256) | $composer2.changed(density) | $composer2.changedInstance(openMotion) | $composer2.changedInstance(closeMotion);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda29
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return NavigationDrawerKt.DismissibleNavigationDrawer$lambda$30$lambda$29(drawerState2, density, openMotion, closeMotion);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.SideEffect((Function0) it$iv2, $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer2, 683737348, "CC(remember):Effects.kt#9igjgp");
            Object value$iv$iv = $composer2.rememberedValue();
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2);
                $composer2.updateRememberedValue(value$iv$iv);
            }
            final CoroutineScope scope = (CoroutineScope) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Strings.Companion companion = Strings.INSTANCE;
            final String navigationMenu = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(androidx.compose.ui.R.string.navigation_menu), $composer2, 0);
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            boolean isRtl = objConsume2 == LayoutDirection.Rtl;
            Modifier modifier$iv = AnchoredDraggableKt.anchoredDraggable(modifier4, drawerState2.getAnchoredDraggableState$material3(), isRtl, Orientation.Horizontal, (112 & 8) != 0 ? true : gesturesEnabled3, (112 & 16) != 0 ? null : null, (112 & 32) != 0 ? null : null, (112 & 64) != 0 ? null : null);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
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
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -901227702, "C508@20959L1256,489@20282L1933:NavigationDrawer.kt#uh7d8r");
            ComposerKt.sourceInformationMarkerStart($composer2, -1691618860, "CC(remember):NavigationDrawer.kt#9igjgp");
            boolean invalid$iv2 = ((($dirty & 896) ^ 384) > 256 && $composer2.changed(drawerState2)) || ($dirty & 384) == 256;
            boolean invalid$iv3 = invalid$iv2;
            NavigationDrawerKt$DismissibleNavigationDrawer$2$2$1 value$iv3 = $composer2.rememberedValue();
            if (invalid$iv3 || value$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = new NavigationDrawerKt$DismissibleNavigationDrawer$2$2$1(drawerState2, anchorsInitialized$delegate);
                $composer2.updateRememberedValue(value$iv3);
            }
            MeasurePolicy measurePolicy$iv2 = (MeasurePolicy) value$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            Modifier modifier$iv2 = Modifier.INSTANCE;
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv2 = ((0 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function1 = constructor2;
                $composer2.createNode(function1);
            } else {
                function1 = constructor2;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash2);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i7 = ($changed$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1778119236, "C492@20374L452,491@20330L570,506@20917L17:NavigationDrawer.kt#uh7d8r");
            Modifier.Companion companion2 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -2020850041, "CC(remember):NavigationDrawer.kt#9igjgp");
            boolean invalid$iv4 = $composer2.changed(navigationMenu) | (((($dirty & 896) ^ 384) > 256 && $composer2.changed(drawerState2)) || ($dirty & 384) == 256) | $composer2.changedInstance(scope);
            int $dirty2 = $dirty;
            Object value$iv4 = $composer2.rememberedValue();
            if (invalid$iv4 || value$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda30
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavigationDrawerKt.DismissibleNavigationDrawer$lambda$38$lambda$36$lambda$33$lambda$32(navigationMenu, drawerState2, scope, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv4);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv3 = SemanticsModifierKt.semantics$default(companion2, false, (Function1) value$iv4, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
            int $changed$iv$iv3 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv3);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function5 = constructor3;
                $composer2.createNode(function5);
            } else {
                function5 = constructor3;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = ($changed$iv$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i9 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1897277909, "C504@20867L15:NavigationDrawer.kt#uh7d8r");
            function2.invoke($composer2, Integer.valueOf($dirty2 & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Modifier modifier$iv4 = Modifier.INSTANCE;
            Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
            int $changed$iv$iv4 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv3 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer2, modifier$iv4);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv3 = (($changed$iv$iv4 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function6 = constructor4;
                $composer2.createNode(function6);
            } else {
                function6 = constructor4;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash4);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
            int i10 = ($changed$iv$iv$iv3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            int i11 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -272067686, "C506@20923L9:NavigationDrawer.kt#uh7d8r");
            function4 = function3;
            function4.invoke($composer2, Integer.valueOf(($dirty2 >> 12) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            gesturesEnabled2 = gesturesEnabled3;
            drawerState3 = drawerState2;
        } else {
            function4 = function3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            gesturesEnabled2 = z;
            drawerState3 = drawerState2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Function2<? super Composer, ? super Integer, Unit> function7 = function4;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda31
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationDrawerKt.DismissibleNavigationDrawer$lambda$39(function2, modifier3, drawerState3, gesturesEnabled2, function7, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DismissibleNavigationDrawer$lambda$27(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DismissibleNavigationDrawer$lambda$28(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit DismissibleNavigationDrawer$lambda$30$lambda$29(DrawerState $drawerState, Density $density, FiniteAnimationSpec $openMotion, FiniteAnimationSpec $closeMotion) {
        $drawerState.setDensity$material3($density);
        $drawerState.setOpenDrawerMotionSpec$material3($openMotion);
        $drawerState.setCloseDrawerMotionSpec$material3($closeMotion);
        return Unit.INSTANCE;
    }

    static final Unit DismissibleNavigationDrawer$lambda$38$lambda$36$lambda$33$lambda$32(String $navigationMenu, final DrawerState $drawerState, final CoroutineScope $scope, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setPaneTitle($this$semantics, $navigationMenu);
        if ($drawerState.isOpen()) {
            SemanticsPropertiesKt.dismiss$default($this$semantics, null, new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda32
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(NavigationDrawerKt.DismissibleNavigationDrawer$lambda$38$lambda$36$lambda$33$lambda$32$lambda$31($drawerState, $scope));
                }
            }, 1, null);
        }
        return Unit.INSTANCE;
    }

    static final boolean DismissibleNavigationDrawer$lambda$38$lambda$36$lambda$33$lambda$32$lambda$31(DrawerState $drawerState, CoroutineScope $scope) {
        if ($drawerState.getConfirmStateChange$material3().invoke(DrawerValue.Closed).booleanValue()) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new NavigationDrawerKt$DismissibleNavigationDrawer$2$1$1$1$1$1($drawerState, null), 3, null);
            return true;
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:61:0x020b  */
    /* JADX WARN: Code duplicated, block: B:64:0x0217  */
    /* JADX WARN: Code duplicated, block: B:65:0x021d  */
    /* JADX WARN: Code duplicated, block: B:75:0x02f1  */
    public static final void PermanentNavigationDrawer(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Composer $composer2;
        final Modifier modifier3;
        Modifier.Companion modifier4;
        Function0<ComposeUiNode> function0;
        int $dirty;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function1;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        Composer $composer3 = $composer.startRestartGroup(89297160);
        ComposerKt.sourceInformation($composer3, "C(PermanentNavigationDrawer)N(drawerContent,modifier,content)562@23211L85:NavigationDrawer.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 4 : 2;
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
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 256 : 128;
        }
        int $dirty3 = $dirty2;
        if (!$composer3.shouldExecute(($dirty3 & 147) != 146, $dirty3 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(89297160, $dirty3, -1, "androidx.compose.material3.PermanentNavigationDrawer (NavigationDrawer.kt:561)");
            }
            Modifier modifier$iv = SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier modifier5 = modifier4;
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor2;
                $composer3.createNode(function0);
            } else {
                function0 = constructor2;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
            $composer2 = $composer3;
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                $dirty = $dirty3;
            } else {
                $dirty = $dirty3;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i4 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -688649266, "C563@23249L15,564@23273L17:NavigationDrawer.kt#uh7d8r");
                function2.invoke($composer2, Integer.valueOf($dirty & 14));
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Modifier modifier$iv2 = Modifier.INSTANCE;
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
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
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i6 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1964035624, "C564@23279L9:NavigationDrawer.kt#uh7d8r");
                function3.invoke($composer2, Integer.valueOf(($dirty >> 6) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
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
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i7 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            int i8 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -688649266, "C563@23249L15,564@23273L17:NavigationDrawer.kt#uh7d8r");
            function2.invoke($composer2, Integer.valueOf($dirty & 14));
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Modifier modifier$iv3 = Modifier.INSTANCE;
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
            int $changed$iv$iv3 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv3 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer2, modifier$iv3);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
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
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
            int i9 = ($changed$iv$iv$iv3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i10 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1964035624, "C564@23279L9:NavigationDrawer.kt#uh7d8r");
            function3.invoke($composer2, Integer.valueOf(($dirty >> 6) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationDrawerKt.PermanentNavigationDrawer$lambda$42(function2, modifier3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: ModalDrawerSheet-afqeVBk, reason: not valid java name */
    public static final void m2647ModalDrawerSheetafqeVBk(Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        WindowInsets windowInsets2;
        Composer $composer2;
        final Modifier modifier3;
        final long drawerContainerColor3;
        final float drawerTonalElevation3;
        final Shape drawerShape3;
        final long drawerContentColor3;
        final WindowInsets windowInsets3;
        Shape drawerShape4;
        long drawerContentColor4;
        float drawerTonalElevation4;
        WindowInsets windowInsets4;
        int i2;
        Modifier modifier4;
        long drawerContainerColor4;
        Composer $composer3 = $composer.startRestartGroup(1922633461);
        ComposerKt.sourceInformation($composer3, "C(ModalDrawerSheet)N(modifier,drawerShape,drawerContainerColor:c#ui.graphics.Color,drawerContentColor:c#ui.graphics.Color,drawerTonalElevation:c#ui.unit.Dp,windowInsets,content)598@24971L343:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                drawerShape2 = drawerShape;
                int i4 = $composer3.changed(drawerShape2) ? 32 : 16;
                $dirty |= i4;
            } else {
                drawerShape2 = drawerShape;
            }
            $dirty |= i4;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                int i5 = $composer3.changed(drawerContainerColor2) ? 256 : 128;
                $dirty |= i5;
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            $dirty |= i5;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                drawerContentColor2 = drawerContentColor;
                int i6 = $composer3.changed(drawerContentColor2) ? 2048 : 1024;
                $dirty |= i6;
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            $dirty |= i6;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty |= 24576;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if (($changed & 24576) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty |= $composer3.changed(drawerTonalElevation2) ? 16384 : 8192;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                int i8 = $composer3.changed(windowInsets2) ? 131072 : 65536;
                $dirty |= i8;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty |= i8;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        }
        if (!$composer3.shouldExecute((599187 & $dirty) != 599186, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerTonalElevation3 = drawerTonalElevation2;
            drawerShape3 = drawerShape2;
            drawerContentColor3 = drawerContentColor2;
            windowInsets3 = windowInsets2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "591@24636L5,592@24692L19,593@24745L37,595@24900L12");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
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
                drawerShape4 = drawerShape2;
                drawerContentColor4 = drawerContentColor2;
                drawerTonalElevation4 = drawerTonalElevation2;
                windowInsets4 = windowInsets2;
                i2 = 1922633461;
                modifier4 = modifier2;
                drawerContainerColor4 = drawerContainerColor2;
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 2) != 0) {
                    $dirty &= -113;
                    drawerShape2 = DrawerDefaults.INSTANCE.getShape($composer3, 6);
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getModalContainerColor($composer3, 6);
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m2263contentColorForek8zF_U(drawerContainerColor2, $composer3, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m2409getModalDrawerElevationD9Ej5fM();
                }
                if ((i & 32) == 0) {
                    drawerShape4 = drawerShape2;
                    drawerContentColor4 = drawerContentColor2;
                    drawerTonalElevation4 = drawerTonalElevation2;
                    windowInsets4 = windowInsets2;
                    i2 = 1922633461;
                    modifier4 = modifier2;
                    drawerContainerColor4 = drawerContainerColor2;
                } else {
                    $dirty &= -458753;
                    modifier4 = modifier2;
                    drawerShape4 = drawerShape2;
                    drawerContentColor4 = drawerContentColor2;
                    drawerTonalElevation4 = drawerTonalElevation2;
                    i2 = 1922633461;
                    windowInsets4 = DrawerDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    drawerContainerColor4 = drawerContainerColor2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.ModalDrawerSheet (NavigationDrawer.kt:597)");
            }
            $composer2 = $composer3;
            m2645DrawerSheetcm3T3N0(null, windowInsets4, modifier4, drawerShape4, drawerContainerColor4, drawerContentColor4, drawerTonalElevation4, null, function3, $composer2, (($dirty >> 12) & 112) | 6 | (($dirty << 6) & 896) | (($dirty << 6) & 7168) | (($dirty << 6) & 57344) | (($dirty << 6) & 458752) | (($dirty << 6) & 3670016) | (($dirty << 6) & 234881024), 128);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            windowInsets3 = windowInsets4;
            modifier3 = modifier4;
            drawerContainerColor3 = drawerContainerColor4;
            drawerContentColor3 = drawerContentColor4;
            drawerTonalElevation3 = drawerTonalElevation4;
            drawerShape3 = drawerShape4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationDrawerKt.ModalDrawerSheet_afqeVBk$lambda$43(modifier3, drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, windowInsets3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: ModalDrawerSheet-Snr_uVM, reason: not valid java name */
    public static final void m2646ModalDrawerSheetSnr_uVM(final DrawerState drawerState, Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        WindowInsets windowInsets2;
        final Modifier modifier3;
        final Shape drawerShape3;
        final long drawerContainerColor3;
        final long drawerContentColor3;
        final float drawerTonalElevation3;
        final WindowInsets windowInsets3;
        int $dirty;
        WindowInsets windowInsets4;
        Composer $composer2 = $composer.startRestartGroup(-1620540727);
        ComposerKt.sourceInformation($composer2, "C(ModalDrawerSheet)N(drawerState,modifier,drawerShape,drawerContainerColor:c#ui.graphics.Color,drawerContentColor:c#ui.graphics.Color,drawerTonalElevation:c#ui.unit.Dp,windowInsets,content)642@27084L519,642@27043L560:NavigationDrawer.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(drawerState) ? 4 : 2;
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
            if ((i & 4) == 0) {
                drawerShape2 = drawerShape;
                int i3 = $composer2.changed(drawerShape2) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                drawerShape2 = drawerShape;
            }
            $dirty2 |= i3;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                int i4 = $composer2.changed(drawerContainerColor2) ? 2048 : 1024;
                $dirty2 |= i4;
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            $dirty2 |= i4;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                drawerContentColor2 = drawerContentColor;
                int i5 = $composer2.changed(drawerContentColor2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            $dirty2 |= i5;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if ((196608 & $changed) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty2 |= $composer2.changed(drawerTonalElevation2) ? 131072 : 65536;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                windowInsets2 = windowInsets;
                int i7 = $composer2.changed(windowInsets2) ? 1048576 : 524288;
                $dirty2 |= i7;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty2 |= i7;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer2.changedInstance(function3) ? 8388608 : 4194304;
        }
        if ($composer2.shouldExecute(($dirty2 & 4793491) != 4793490, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "635@26708L5,636@26764L19,637@26817L37,639@26972L12");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                modifier3 = modifier2;
                drawerShape3 = drawerShape2;
                drawerContainerColor3 = drawerContainerColor2;
                drawerContentColor3 = drawerContentColor2;
                drawerTonalElevation3 = drawerTonalElevation2;
                $dirty = $dirty2;
                windowInsets4 = windowInsets2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                    drawerShape2 = DrawerDefaults.INSTANCE.getShape($composer2, 6);
                }
                if ((i & 8) != 0) {
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getModalContainerColor($composer2, 6);
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m2263contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 9) & 14);
                    $dirty2 &= -57345;
                }
                if (i6 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m2409getModalDrawerElevationD9Ej5fM();
                }
                if ((i & 64) == 0) {
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    $dirty = $dirty2;
                    windowInsets4 = windowInsets2;
                } else {
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    $dirty = $dirty2 & (-3670017);
                    windowInsets4 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1620540727, $dirty, -1, "androidx.compose.material3.ModalDrawerSheet (NavigationDrawer.kt:641)");
            }
            DrawerPredictiveBackHandler(drawerState, ComposableLambdaKt.rememberComposableLambda(797187326, true, new NavigationDrawerKt$ModalDrawerSheet$2(windowInsets4, modifier3, drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, drawerState, function3), $composer2, 54), $composer2, ($dirty & 14) | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            windowInsets3 = windowInsets4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            drawerShape3 = drawerShape2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerContentColor3 = drawerContentColor2;
            drawerTonalElevation3 = drawerTonalElevation2;
            windowInsets3 = windowInsets2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationDrawerKt.ModalDrawerSheet_Snr_uVM$lambda$44(drawerState, modifier3, drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, windowInsets3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: DismissibleDrawerSheet-afqeVBk, reason: not valid java name */
    public static final void m2644DismissibleDrawerSheetafqeVBk(Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        WindowInsets windowInsets2;
        Composer $composer2;
        final Shape drawerShape3;
        final long drawerContentColor3;
        final Modifier modifier3;
        final long drawerContainerColor3;
        final float drawerTonalElevation3;
        final WindowInsets windowInsets3;
        long drawerContainerColor4;
        long drawerContentColor4;
        float drawerTonalElevation4;
        WindowInsets windowInsets4;
        int i2;
        Modifier modifier4;
        Shape drawerShape4;
        Composer $composer3 = $composer.startRestartGroup(-1496398234);
        ComposerKt.sourceInformation($composer3, "C(DismissibleDrawerSheet)N(modifier,drawerShape,drawerContainerColor:c#ui.graphics.Color,drawerContentColor:c#ui.graphics.Color,drawerTonalElevation:c#ui.unit.Dp,windowInsets,content)687@29311L343:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty |= 48;
            drawerShape2 = drawerShape;
        } else if (($changed & 48) == 0) {
            drawerShape2 = drawerShape;
            $dirty |= $composer3.changed(drawerShape2) ? 32 : 16;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                int i5 = $composer3.changed(drawerContainerColor2) ? 256 : 128;
                $dirty |= i5;
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            $dirty |= i5;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                drawerContentColor2 = drawerContentColor;
                int i6 = $composer3.changed(drawerContentColor2) ? 2048 : 1024;
                $dirty |= i6;
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            $dirty |= i6;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty |= 24576;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if (($changed & 24576) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty |= $composer3.changed(drawerTonalElevation2) ? 16384 : 8192;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                int i8 = $composer3.changed(windowInsets2) ? 131072 : 65536;
                $dirty |= i8;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty |= i8;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        }
        if (!$composer3.shouldExecute(($dirty & 599187) != 599186, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            drawerShape3 = drawerShape2;
            drawerContentColor3 = drawerContentColor2;
            modifier3 = modifier2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerTonalElevation3 = drawerTonalElevation2;
            windowInsets3 = windowInsets2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "681@29023L22,682@29079L37,684@29240L12");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                drawerContainerColor4 = drawerContainerColor2;
                drawerContentColor4 = drawerContentColor2;
                drawerTonalElevation4 = drawerTonalElevation2;
                windowInsets4 = windowInsets2;
                i2 = -1496398234;
                modifier4 = modifier2;
                drawerShape4 = drawerShape2;
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getStandardContainerColor($composer3, 6);
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m2263contentColorForek8zF_U(drawerContainerColor2, $composer3, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m2407getDismissibleDrawerElevationD9Ej5fM();
                }
                if ((i & 32) == 0) {
                    drawerContainerColor4 = drawerContainerColor2;
                    drawerContentColor4 = drawerContentColor2;
                    drawerTonalElevation4 = drawerTonalElevation2;
                    windowInsets4 = windowInsets2;
                    i2 = -1496398234;
                    modifier4 = modifier2;
                    drawerShape4 = drawerShape2;
                } else {
                    $dirty &= -458753;
                    drawerShape4 = drawerShape2;
                    drawerContainerColor4 = drawerContainerColor2;
                    drawerContentColor4 = drawerContentColor2;
                    drawerTonalElevation4 = drawerTonalElevation2;
                    windowInsets4 = DrawerDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    modifier4 = modifier2;
                    i2 = -1496398234;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.DismissibleDrawerSheet (NavigationDrawer.kt:686)");
            }
            $composer2 = $composer3;
            m2645DrawerSheetcm3T3N0(null, windowInsets4, modifier4, drawerShape4, drawerContainerColor4, drawerContentColor4, drawerTonalElevation4, null, function3, $composer2, (($dirty >> 12) & 112) | 6 | (($dirty << 6) & 896) | (($dirty << 6) & 7168) | (($dirty << 6) & 57344) | (($dirty << 6) & 458752) | (($dirty << 6) & 3670016) | (($dirty << 6) & 234881024), 128);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            windowInsets3 = windowInsets4;
            modifier3 = modifier4;
            drawerContainerColor3 = drawerContainerColor4;
            drawerContentColor3 = drawerContentColor4;
            drawerTonalElevation3 = drawerTonalElevation4;
            drawerShape3 = drawerShape4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationDrawerKt.DismissibleDrawerSheet_afqeVBk$lambda$45(modifier3, drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, windowInsets3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: DismissibleDrawerSheet-Snr_uVM, reason: not valid java name */
    public static final void m2643DismissibleDrawerSheetSnr_uVM(final DrawerState drawerState, Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        final WindowInsets windowInsets2;
        final Modifier modifier3;
        final Shape drawerShape3;
        final long drawerContainerColor3;
        final float drawerTonalElevation3;
        final long drawerContainerColor4;
        int $dirty;
        WindowInsets windowInsets3;
        Composer $composer2 = $composer.startRestartGroup(496605370);
        ComposerKt.sourceInformation($composer2, "C(DismissibleDrawerSheet)N(drawerState,modifier,drawerShape,drawerContainerColor:c#ui.graphics.Color,drawerContentColor:c#ui.graphics.Color,drawerTonalElevation:c#ui.unit.Dp,windowInsets,content)731@31451L519,731@31410L560:NavigationDrawer.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(drawerState) ? 4 : 2;
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
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            drawerShape2 = drawerShape;
        } else if (($changed & 384) == 0) {
            drawerShape2 = drawerShape;
            $dirty2 |= $composer2.changed(drawerShape2) ? 256 : 128;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                int i4 = $composer2.changed(drawerContainerColor2) ? 2048 : 1024;
                $dirty2 |= i4;
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            $dirty2 |= i4;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                drawerContentColor2 = drawerContentColor;
                int i5 = $composer2.changed(drawerContentColor2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            $dirty2 |= i5;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if ((196608 & $changed) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty2 |= $composer2.changed(drawerTonalElevation2) ? 131072 : 65536;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if (($changed & 1572864) == 0) {
            $dirty2 |= ((i & 64) == 0 && $composer2.changed(windowInsets)) ? 1048576 : 524288;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer2.changedInstance(function3) ? 8388608 : 4194304;
        }
        if ($composer2.shouldExecute(($dirty2 & 4793491) != 4793490, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "725@31122L22,726@31178L37,728@31339L12");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                modifier3 = modifier2;
                drawerShape3 = drawerShape2;
                drawerContainerColor3 = drawerContainerColor2;
                drawerTonalElevation3 = drawerTonalElevation2;
                drawerContainerColor4 = drawerContentColor2;
                $dirty = $dirty2;
                windowInsets3 = windowInsets;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 8) != 0) {
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getStandardContainerColor($composer2, 6);
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m2263contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty2 >> 9) & 14);
                    $dirty2 &= -57345;
                }
                if (i6 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m2407getDismissibleDrawerElevationD9Ej5fM();
                }
                if ((i & 64) == 0) {
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    drawerContainerColor4 = drawerContentColor2;
                    $dirty = $dirty2;
                    windowInsets3 = windowInsets;
                } else {
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    drawerContainerColor4 = drawerContentColor2;
                    $dirty = $dirty2 & (-3670017);
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(496605370, $dirty, -1, "androidx.compose.material3.DismissibleDrawerSheet (NavigationDrawer.kt:730)");
            }
            DrawerPredictiveBackHandler(drawerState, ComposableLambdaKt.rememberComposableLambda(1623455535, true, new NavigationDrawerKt$DismissibleDrawerSheet$2(windowInsets3, modifier3, drawerShape3, drawerContainerColor3, drawerContainerColor4, drawerTonalElevation3, drawerState, function3), $composer2, 54), $composer2, ($dirty & 14) | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            windowInsets2 = windowInsets3;
        } else {
            $composer2.skipToGroupEnd();
            windowInsets2 = windowInsets;
            modifier3 = modifier2;
            drawerShape3 = drawerShape2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerTonalElevation3 = drawerTonalElevation2;
            drawerContainerColor4 = drawerContentColor2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationDrawerKt.DismissibleDrawerSheet_Snr_uVM$lambda$46(drawerState, modifier3, drawerShape3, drawerContainerColor3, drawerContainerColor4, drawerTonalElevation3, windowInsets2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: PermanentDrawerSheet-afqeVBk, reason: not valid java name */
    public static final void m2649PermanentDrawerSheetafqeVBk(Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Shape shape;
        long j;
        long drawerContentColor2;
        float f;
        WindowInsets windowInsets2;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function4;
        Composer $composer2;
        final Shape drawerShape2;
        final long drawerContentColor3;
        final Modifier modifier2;
        final long drawerContainerColor2;
        final float drawerTonalElevation2;
        final WindowInsets windowInsets3;
        Modifier.Companion modifier3;
        Shape drawerShape3;
        long drawerContainerColor3;
        float drawerTonalElevation3;
        float drawerTonalElevation4;
        long drawerContentColor4;
        WindowInsets windowInsets4;
        Shape drawerShape4;
        int i2;
        Composer $composer3 = $composer.startRestartGroup(1877158612);
        ComposerKt.sourceInformation($composer3, "C(PermanentDrawerSheet)N(modifier,drawerShape,drawerContainerColor:c#ui.graphics.Color,drawerContentColor:c#ui.graphics.Color,drawerTonalElevation:c#ui.unit.Dp,windowInsets,content)772@33449L33,776@33617L30,773@33487L384:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty |= 48;
            shape = drawerShape;
        } else if (($changed & 48) == 0) {
            shape = drawerShape;
            $dirty |= $composer3.changed(shape) ? 32 : 16;
        } else {
            shape = drawerShape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j = drawerContainerColor;
                int i5 = $composer3.changed(j) ? 256 : 128;
                $dirty |= i5;
            } else {
                j = drawerContainerColor;
            }
            $dirty |= i5;
        } else {
            j = drawerContainerColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                drawerContentColor2 = drawerContentColor;
                int i6 = $composer3.changed(drawerContentColor2) ? 2048 : 1024;
                $dirty |= i6;
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            $dirty |= i6;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty |= 24576;
            f = drawerTonalElevation;
        } else if (($changed & 24576) == 0) {
            f = drawerTonalElevation;
            $dirty |= $composer3.changed(f) ? 16384 : 8192;
        } else {
            f = drawerTonalElevation;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                int i8 = $composer3.changed(windowInsets2) ? 131072 : 65536;
                $dirty |= i8;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty |= i8;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
            function4 = function3;
        } else if (($changed & 1572864) == 0) {
            function4 = function3;
            $dirty |= $composer3.changedInstance(function4) ? 1048576 : 524288;
        } else {
            function4 = function3;
        }
        if (!$composer3.shouldExecute(($dirty & 599187) != 599186, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            drawerShape2 = shape;
            drawerContentColor3 = drawerContentColor2;
            modifier2 = modifier;
            drawerContainerColor2 = j;
            drawerTonalElevation2 = f;
            windowInsets3 = windowInsets2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "766@33142L22,767@33198L37,769@33357L12");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                modifier3 = modifier;
                drawerContentColor4 = drawerContentColor2;
                drawerTonalElevation4 = f;
                windowInsets4 = windowInsets2;
                i2 = 1877158612;
                drawerShape4 = shape;
                drawerContainerColor3 = j;
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if (i4 == 0) {
                    drawerShape3 = shape;
                } else {
                    drawerShape3 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) == 0) {
                    drawerContainerColor3 = j;
                } else {
                    drawerContainerColor3 = DrawerDefaults.INSTANCE.getStandardContainerColor($composer3, 6);
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m2263contentColorForek8zF_U(drawerContainerColor3, $composer3, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                }
                if (i7 == 0) {
                    drawerTonalElevation3 = f;
                } else {
                    drawerTonalElevation3 = DrawerDefaults.INSTANCE.m2410getPermanentDrawerElevationD9Ej5fM();
                }
                if ((i & 32) == 0) {
                    drawerTonalElevation4 = drawerTonalElevation3;
                    drawerContentColor4 = drawerContentColor2;
                    windowInsets4 = windowInsets2;
                    drawerShape4 = drawerShape3;
                    i2 = 1877158612;
                } else {
                    $dirty &= -458753;
                    drawerShape4 = drawerShape3;
                    drawerTonalElevation4 = drawerTonalElevation3;
                    drawerContentColor4 = drawerContentColor2;
                    i2 = 1877158612;
                    windowInsets4 = DrawerDefaults.INSTANCE.getWindowInsets($composer3, 6);
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.PermanentDrawerSheet (NavigationDrawer.kt:771)");
            }
            Strings.Companion companion = Strings.INSTANCE;
            final String navigationMenu = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(androidx.compose.ui.R.string.navigation_menu), $composer3, 0);
            ComposerKt.sourceInformationMarkerStart($composer3, 63182258, "CC(remember):NavigationDrawer.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(navigationMenu);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda27
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavigationDrawerKt.PermanentDrawerSheet_afqeVBk$lambda$48$lambda$47(navigationMenu, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function5 = function4;
            long drawerContainerColor4 = drawerContainerColor3;
            m2645DrawerSheetcm3T3N0(null, windowInsets4, SemanticsModifierKt.semantics$default(modifier3, false, (Function1) it$iv, 1, null), drawerShape4, drawerContainerColor4, drawerContentColor4, drawerTonalElevation4, null, function5, $composer2, (($dirty >> 12) & 112) | 6 | (($dirty << 6) & 7168) | (($dirty << 6) & 57344) | (($dirty << 6) & 458752) | (($dirty << 6) & 3670016) | (($dirty << 6) & 234881024), 128);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            windowInsets3 = windowInsets4;
            drawerContainerColor2 = drawerContainerColor4;
            drawerContentColor3 = drawerContentColor4;
            drawerTonalElevation2 = drawerTonalElevation4;
            drawerShape2 = drawerShape4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationDrawerKt.PermanentDrawerSheet_afqeVBk$lambda$49(modifier2, drawerShape2, drawerContainerColor2, drawerContentColor3, drawerTonalElevation2, windowInsets3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit PermanentDrawerSheet_afqeVBk$lambda$48$lambda$47(String $navigationMenu, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setPaneTitle($this$semantics, $navigationMenu);
        return Unit.INSTANCE;
    }

    static final float DrawerSheet_cm3T3N0$lambda$51$lambda$50() {
        return 0.0f;
    }

    /* JADX WARN: Code duplicated, block: B:88:0x00fd  */
    /* JADX INFO: renamed from: DrawerSheet-cm3T3N0, reason: not valid java name */
    public static final void m2645DrawerSheetcm3T3N0(final DrawerPredictiveBackState drawerPredictiveBackState, final WindowInsets windowInsets, Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, FloatProducer drawerOffset, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        WindowInsets windowInsets2;
        Modifier modifier2;
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        Composer $composer2;
        final Modifier modifier3;
        final Shape drawerShape3;
        final long drawerContainerColor3;
        final long drawerContentColor3;
        final float drawerTonalElevation2;
        final FloatProducer drawerOffset2;
        int $dirty;
        float drawerTonalElevation3;
        int $dirty2;
        FloatProducer drawerOffset3;
        float drawerTonalElevation4;
        long drawerContainerColor4;
        int i2;
        long drawerContentColor4;
        Shape drawerShape4;
        Modifier.Companion predictiveBackDrawerContainerModifier;
        int i3;
        Composer $composer3 = $composer.startRestartGroup(1560288494);
        ComposerKt.sourceInformation($composer3, "C(DrawerSheet)N(drawerPredictiveBackState,windowInsets,modifier,drawerShape,drawerContainerColor:c#ui.graphics.Color,drawerContentColor:c#ui.graphics.Color,drawerTonalElevation:c#ui.unit.Dp,drawerOffset,content)797@34436L7,800@34593L7,826@35796L867,807@34858L1805:NavigationDrawer.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changed(drawerPredictiveBackState) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
            windowInsets2 = windowInsets;
        } else if (($changed & 48) == 0) {
            windowInsets2 = windowInsets;
            $dirty3 |= $composer3.changed(windowInsets2) ? 32 : 16;
        } else {
            windowInsets2 = windowInsets;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty3 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty3 |= 3072;
            drawerShape2 = drawerShape;
        } else if (($changed & 3072) == 0) {
            drawerShape2 = drawerShape;
            $dirty3 |= $composer3.changed(drawerShape2) ? 2048 : 1024;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                int i6 = $composer3.changed(drawerContainerColor2) ? 16384 : 8192;
                $dirty3 |= i6;
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            $dirty3 |= i6;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                drawerContentColor2 = drawerContentColor;
                int i7 = $composer3.changed(drawerContentColor2) ? 131072 : 65536;
                $dirty3 |= i7;
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            $dirty3 |= i7;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty3 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty3 |= $composer3.changed(drawerTonalElevation) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) != 0) {
                i3 = 4194304;
            } else {
                if ((16777216 & $changed) == 0 ? $composer3.changed(drawerOffset) : $composer3.changedInstance(drawerOffset)) {
                    i3 = 8388608;
                } else {
                    i3 = 4194304;
                }
            }
            $dirty3 |= i3;
        }
        if ((i & 256) != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty4 = $dirty3;
        if ($composer3.shouldExecute(($dirty3 & 38347923) != 38347922, $dirty4 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "791@34129L22,792@34185L37,794@34330L20");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty5 = (i & 16) != 0 ? $dirty4 & (-57345) : $dirty4;
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 128) != 0) {
                    $dirty5 &= -29360129;
                }
                drawerTonalElevation4 = drawerTonalElevation;
                $dirty2 = $dirty5;
                drawerContainerColor4 = drawerContainerColor2;
                i2 = 1560288494;
                drawerOffset3 = drawerOffset;
                drawerContentColor4 = drawerContentColor2;
                drawerShape4 = drawerShape2;
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i5 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 16) == 0) {
                    $dirty = $dirty4;
                } else {
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getStandardContainerColor($composer3, 6);
                    $dirty = $dirty4 & (-57345);
                }
                if ((i & 32) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m2263contentColorForek8zF_U(drawerContainerColor2, $composer3, ($dirty >> 12) & 14);
                    $dirty &= -458753;
                }
                if (i8 == 0) {
                    drawerTonalElevation3 = drawerTonalElevation;
                } else {
                    drawerTonalElevation3 = DrawerDefaults.INSTANCE.m2410getPermanentDrawerElevationD9Ej5fM();
                }
                if ((i & 128) == 0) {
                    $dirty2 = $dirty;
                    drawerOffset3 = drawerOffset;
                    drawerTonalElevation4 = drawerTonalElevation3;
                    drawerContainerColor4 = drawerContainerColor2;
                    i2 = 1560288494;
                    drawerContentColor4 = drawerContentColor2;
                    drawerShape4 = drawerShape2;
                } else {
                    ComposerKt.sourceInformationMarkerStart($composer3, 493671010, "CC(remember):NavigationDrawer.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    int $dirty6 = $dirty;
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = new FloatProducer() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda17
                            @Override // androidx.compose.material3.internal.FloatProducer
                            public final float invoke() {
                                return NavigationDrawerKt.DrawerSheet_cm3T3N0$lambda$51$lambda$50();
                            }
                        };
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    drawerOffset3 = (FloatProducer) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty2 = $dirty6 & (-29360129);
                    drawerTonalElevation4 = drawerTonalElevation3;
                    drawerContainerColor4 = drawerContainerColor2;
                    i2 = 1560288494;
                    drawerContentColor4 = drawerContentColor2;
                    drawerShape4 = drawerShape2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty2, -1, "androidx.compose.material3.DrawerSheet (NavigationDrawer.kt:796)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density density = (Density) objConsume;
            final float maxWidth = NavigationDrawerTokens.INSTANCE.m3908getContainerWidthD9Ej5fM();
            final float maxWidthPx = density.mo406toPx0680j_4(maxWidth);
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            boolean isRtl = objConsume2 == LayoutDirection.Rtl;
            if (drawerPredictiveBackState != null) {
                predictiveBackDrawerContainerModifier = predictiveBackDrawerContainer(Modifier.INSTANCE, drawerPredictiveBackState, isRtl);
            } else {
                predictiveBackDrawerContainerModifier = Modifier.INSTANCE;
            }
            Modifier modifier4 = modifier2;
            final FloatProducer drawerOffset4 = drawerOffset3;
            final boolean isRtl2 = isRtl;
            final WindowInsets windowInsets3 = windowInsets2;
            $composer2 = $composer3;
            SurfaceKt.m2929SurfaceT9BRK9s(SizeKt.fillMaxHeight$default(horizontalScaleUp(SizeKt.m869sizeInqDBjuR0$default(modifier4, MinimumDrawerWidth, 0.0f, maxWidth, 0.0f, 10, null), drawerOffset3, maxWidthPx, isRtl).then(predictiveBackDrawerContainerModifier), 0.0f, 1, null), drawerShape4, drawerContainerColor4, drawerContentColor4, drawerTonalElevation4, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-315420087, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DrawerSheet$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    Modifier.Companion predictiveBackDrawerChildModifier;
                    Function0<ComposeUiNode> function0;
                    ComposerKt.sourceInformation($composer4, "C831@36016L641:NavigationDrawer.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-315420087, $changed2, -1, "androidx.compose.material3.DrawerSheet.<anonymous> (NavigationDrawer.kt:827)");
                    }
                    if (drawerPredictiveBackState != null) {
                        predictiveBackDrawerChildModifier = NavigationDrawerKt.predictiveBackDrawerChild(Modifier.INSTANCE, drawerPredictiveBackState, isRtl2);
                    } else {
                        predictiveBackDrawerChildModifier = Modifier.INSTANCE;
                    }
                    Modifier modifier$iv = WindowInsetsPaddingKt.windowInsetsPadding(NavigationDrawerKt.horizontalScaleDown(SizeKt.m869sizeInqDBjuR0$default(Modifier.INSTANCE, NavigationDrawerKt.MinimumDrawerWidth, 0.0f, maxWidth, 0.0f, 10, null), drawerOffset4, maxWidthPx, isRtl2).then(predictiveBackDrawerChildModifier), windowInsets3);
                    Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                    ComposerKt.sourceInformationMarkerStart($composer4, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer4.startReusableNode();
                    if ($composer4.getInserting()) {
                        function0 = constructor;
                        $composer4.createNode(function0);
                    } else {
                        function0 = constructor;
                        $composer4.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer4);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i9 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                    function4.invoke(ColumnScopeInstance.INSTANCE, $composer4, Integer.valueOf(((0 >> 6) & 112) | 6));
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer2, (($dirty2 >> 6) & 112) | 12582912 | (($dirty2 >> 6) & 896) | (($dirty2 >> 6) & 7168) | (($dirty2 >> 6) & 57344), 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            drawerOffset2 = drawerOffset4;
            drawerShape3 = drawerShape4;
            drawerContainerColor3 = drawerContainerColor4;
            drawerContentColor3 = drawerContentColor4;
            drawerTonalElevation2 = drawerTonalElevation4;
            modifier3 = modifier4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            drawerShape3 = drawerShape2;
            drawerContainerColor3 = drawerContainerColor2;
            drawerContentColor3 = drawerContentColor2;
            drawerTonalElevation2 = drawerTonalElevation;
            drawerOffset2 = drawerOffset;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationDrawerKt.DrawerSheet_cm3T3N0$lambda$53(drawerPredictiveBackState, windowInsets, modifier3, drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation2, drawerOffset2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final Modifier horizontalScaleUp(Modifier $this$horizontalScaleUp, final FloatProducer drawerOffset, final float drawerWidth, final boolean isRtl) {
        return GraphicsLayerModifierKt.graphicsLayer($this$horizontalScaleUp, new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavigationDrawerKt.horizontalScaleUp$lambda$54(drawerOffset, drawerWidth, isRtl, (GraphicsLayerScope) obj);
            }
        });
    }

    static final Unit horizontalScaleUp$lambda$54(FloatProducer $drawerOffset, float $drawerWidth, boolean $isRtl, GraphicsLayerScope $this$graphicsLayer) {
        float offset = $drawerOffset.invoke();
        $this$graphicsLayer.setScaleX(offset > 0.0f ? (offset / $drawerWidth) + 1.0f : 1.0f);
        $this$graphicsLayer.mo5298setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin($isRtl ? 0.0f : 1.0f, 0.5f));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier horizontalScaleDown(Modifier $this$horizontalScaleDown, final FloatProducer drawerOffset, final float drawerWidth, final boolean isRtl) {
        return GraphicsLayerModifierKt.graphicsLayer($this$horizontalScaleDown, new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavigationDrawerKt.horizontalScaleDown$lambda$55(drawerOffset, drawerWidth, isRtl, (GraphicsLayerScope) obj);
            }
        });
    }

    static final Unit horizontalScaleDown$lambda$55(FloatProducer $drawerOffset, float $drawerWidth, boolean $isRtl, GraphicsLayerScope $this$graphicsLayer) {
        float offset = $drawerOffset.invoke();
        $this$graphicsLayer.setScaleX(offset > 0.0f ? 1.0f / ((offset / $drawerWidth) + 1.0f) : 1.0f);
        $this$graphicsLayer.mo5298setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin($isRtl ? 0.0f : 1.0f, 0.0f));
        return Unit.INSTANCE;
    }

    private static final Modifier predictiveBackDrawerContainer(Modifier $this$predictiveBackDrawerContainer, final DrawerPredictiveBackState drawerPredictiveBackState, final boolean isRtl) {
        return GraphicsLayerModifierKt.graphicsLayer($this$predictiveBackDrawerContainer, new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavigationDrawerKt.predictiveBackDrawerContainer$lambda$56(drawerPredictiveBackState, isRtl, (GraphicsLayerScope) obj);
            }
        });
    }

    static final Unit predictiveBackDrawerContainer$lambda$56(DrawerPredictiveBackState $drawerPredictiveBackState, boolean $isRtl, GraphicsLayerScope $this$graphicsLayer) {
        $this$graphicsLayer.setScaleX(calculatePredictiveBackScaleX($this$graphicsLayer, $drawerPredictiveBackState));
        $this$graphicsLayer.setScaleY(calculatePredictiveBackScaleY($this$graphicsLayer, $drawerPredictiveBackState));
        $this$graphicsLayer.mo5298setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin($isRtl ? 1.0f : 0.0f, 0.5f));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier predictiveBackDrawerChild(Modifier $this$predictiveBackDrawerChild, final DrawerPredictiveBackState drawerPredictiveBackState, final boolean isRtl) {
        return GraphicsLayerModifierKt.graphicsLayer($this$predictiveBackDrawerChild, new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavigationDrawerKt.predictiveBackDrawerChild$lambda$57(drawerPredictiveBackState, isRtl, (GraphicsLayerScope) obj);
            }
        });
    }

    static final Unit predictiveBackDrawerChild$lambda$57(DrawerPredictiveBackState $drawerPredictiveBackState, boolean $isRtl, GraphicsLayerScope $this$graphicsLayer) {
        float containerScaleX = calculatePredictiveBackScaleX($this$graphicsLayer, $drawerPredictiveBackState);
        float containerScaleY = calculatePredictiveBackScaleY($this$graphicsLayer, $drawerPredictiveBackState);
        $this$graphicsLayer.setScaleX(!((containerScaleX > 0.0f ? 1 : (containerScaleX == 0.0f ? 0 : -1)) == 0) ? containerScaleY / containerScaleX : 1.0f);
        $this$graphicsLayer.mo5298setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin($isRtl ? 0.0f : 1.0f, 0.0f));
        return Unit.INSTANCE;
    }

    private static final float calculatePredictiveBackScaleX(GraphicsLayerScope $this$calculatePredictiveBackScaleX, DrawerPredictiveBackState drawerPredictiveBackState) {
        long arg0$iv = $this$calculatePredictiveBackScaleX.getSize();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float width = Float.intBitsToFloat(bits$iv$iv$iv);
        if (Float.isNaN(width)) {
            return 1.0f;
        }
        if (width == 0.0f) {
            return 1.0f;
        }
        int scaleXDirection = drawerPredictiveBackState.getSwipeEdgeMatchesDrawer() ? 1 : -1;
        return 1.0f + ((drawerPredictiveBackState.getScaleXDistance() * scaleXDirection) / width);
    }

    private static final float calculatePredictiveBackScaleY(GraphicsLayerScope $this$calculatePredictiveBackScaleY, DrawerPredictiveBackState drawerPredictiveBackState) {
        long arg0$iv = $this$calculatePredictiveBackScaleY.getSize();
        int bits$iv$iv$iv = (int) (4294967295L & arg0$iv);
        float height = Float.intBitsToFloat(bits$iv$iv$iv);
        if (Float.isNaN(height)) {
            return 1.0f;
        }
        if (height == 0.0f) {
            return 1.0f;
        }
        return 1.0f - (drawerPredictiveBackState.getScaleYDistance() / height);
    }

    public static final void DrawerPredictiveBackHandler(final DrawerState drawerState, final Function3<? super DrawerPredictiveBackState, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        DrawerPredictiveBackState drawerPredictiveBackState;
        String str;
        NavigationDrawerKt$DrawerPredictiveBackHandler$2$1 value$iv;
        Composer $composer2 = $composer.startRestartGroup(-383087355);
        ComposerKt.sourceInformation($composer2, "C(DrawerPredictiveBackHandler)N(drawerState,content)942@40202L40,943@40259L24,944@40321L7,948@40483L7,954@40796L1297,954@40744L1349,986@42136L99,986@42099L136,992@42241L34:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(drawerState) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-383087355, $dirty2, -1, "androidx.compose.material3.DrawerPredictiveBackHandler (NavigationDrawer.kt:941)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1220245293, "CC(remember):NavigationDrawer.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new DrawerPredictiveBackState();
                $composer2.updateRememberedValue(value$iv2);
                it$iv = value$iv2;
            }
            DrawerPredictiveBackState drawerPredictiveBackState2 = (DrawerPredictiveBackState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer2, 683737348, "CC(remember):Effects.kt#9igjgp");
            Object it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2);
                $composer2.updateRememberedValue(value$iv$iv);
                it$iv$iv = value$iv$iv;
            }
            CoroutineScope scope = (CoroutineScope) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            boolean isRtl = objConsume == LayoutDirection.Rtl;
            Ref.FloatRef maxScaleXDistanceGrow = new Ref.FloatRef();
            Ref.FloatRef maxScaleXDistanceShrink = new Ref.FloatRef();
            Ref.FloatRef maxScaleYDistance = new Ref.FloatRef();
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$DrawerPredictiveBackHandler_u24lambda_u2459 = (Density) objConsume2;
            maxScaleXDistanceGrow.element = $this$DrawerPredictiveBackHandler_u24lambda_u2459.mo406toPx0680j_4(PredictiveBackDrawerMaxScaleXDistanceGrow);
            maxScaleXDistanceShrink.element = $this$DrawerPredictiveBackHandler_u24lambda_u2459.mo406toPx0680j_4(PredictiveBackDrawerMaxScaleXDistanceShrink);
            maxScaleYDistance.element = $this$DrawerPredictiveBackHandler_u24lambda_u2459.mo406toPx0680j_4(PredictiveBackDrawerMaxScaleYDistance);
            boolean zIsOpen = drawerState.isOpen();
            ComposerKt.sourceInformationMarkerStart($composer2, 1220265558, "CC(remember):NavigationDrawer.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(isRtl) | $composer2.changed(maxScaleXDistanceGrow.element) | $composer2.changed(maxScaleXDistanceShrink.element) | $composer2.changed(maxScaleYDistance.element) | $composer2.changedInstance(scope) | (($dirty2 & 14) == 4);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                drawerPredictiveBackState = drawerPredictiveBackState2;
                str = "CC(remember):NavigationDrawer.kt#9igjgp";
                value$iv = new NavigationDrawerKt$DrawerPredictiveBackHandler$2$1(drawerPredictiveBackState, scope, drawerState, isRtl, maxScaleXDistanceGrow, maxScaleXDistanceShrink, maxScaleYDistance, null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                drawerPredictiveBackState = drawerPredictiveBackState2;
                value$iv = it$iv2;
                str = "CC(remember):NavigationDrawer.kt#9igjgp";
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            BackHandler_androidKt.PredictiveBackHandler(zIsOpen, (Function2) value$iv, $composer2, 0, 0);
            Boolean boolValueOf = Boolean.valueOf(drawerState.isClosed());
            ComposerKt.sourceInformationMarkerStart($composer2, 1220307240, str);
            boolean invalid$iv2 = ($dirty2 & 14) == 4;
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = (Function2) new NavigationDrawerKt$DrawerPredictiveBackHandler$3$1(drawerState, drawerPredictiveBackState, null);
                $composer2.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(boolValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv3, $composer2, 0);
            function3.invoke(drawerPredictiveBackState, $composer2, Integer.valueOf(($dirty2 & 112) | 6));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationDrawerKt.DrawerPredictiveBackHandler$lambda$62(drawerState, function3, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Type inference failed for: r1v15, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    public static final void NavigationDrawerItem(final Function2<? super Composer, ? super Integer, Unit> function2, final boolean z, final Function0<Unit> function0, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Shape shape, NavigationDrawerItemColors navigationDrawerItemColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Shape shape2;
        NavigationDrawerItemColors navigationDrawerItemColors2;
        Composer composer2;
        final NavigationDrawerItemColors navigationDrawerItemColors3;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final Shape shape3;
        final MutableInteractionSource mutableInteractionSource2;
        Modifier.Companion companion;
        Function2<? super Composer, ? super Integer, Unit> function9;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Shape value;
        int i3;
        boolean z2;
        boolean z3;
        NavigationDrawerItemColors navigationDrawerItemColorsM2642colorsoq7We08;
        MutableInteractionSource mutableInteractionSource3;
        int i4;
        Modifier modifier4;
        ?? r1;
        Composer composerStartRestartGroup = composer.startRestartGroup(-583709666);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(NavigationDrawerItem)N(label,selected,onClick,modifier,icon,badge,shape,colors,interactionSource)1083@46164L19,1087@46341L24,1089@46426L885,1078@46032L1279:NavigationDrawer.kt#uh7d8r");
        int i5 = i;
        if ((i2 & 1) != 0) {
            i5 |= 6;
        } else if ((i & 6) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function2) ? 4 : 2;
        }
        if ((i2 & 2) != 0) {
            i5 |= 48;
        } else if ((i & 48) == 0) {
            i5 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i2 & 4) != 0) {
            i5 |= 384;
        } else if ((i & 384) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function0) ? 256 : 128;
        }
        int i6 = i2 & 8;
        if (i6 != 0) {
            i5 |= 3072;
            modifier2 = modifier;
        } else if ((i & 3072) == 0) {
            modifier2 = modifier;
            i5 |= composerStartRestartGroup.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i7 = i2 & 16;
        if (i7 != 0) {
            i5 |= 24576;
            function5 = function3;
        } else if ((i & 24576) == 0) {
            function5 = function3;
            i5 |= composerStartRestartGroup.changedInstance(function5) ? 16384 : 8192;
        } else {
            function5 = function3;
        }
        int i8 = i2 & 32;
        if (i8 != 0) {
            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function6 = function4;
        } else if ((196608 & i) == 0) {
            function6 = function4;
            i5 |= composerStartRestartGroup.changedInstance(function6) ? 131072 : 65536;
        } else {
            function6 = function4;
        }
        if ((1572864 & i) == 0) {
            if ((i2 & 64) == 0) {
                shape2 = shape;
                int i9 = composerStartRestartGroup.changed(shape2) ? 1048576 : 524288;
                i5 |= i9;
            } else {
                shape2 = shape;
            }
            i5 |= i9;
        } else {
            shape2 = shape;
        }
        if ((i & 12582912) == 0) {
            if ((i2 & 128) == 0) {
                navigationDrawerItemColors2 = navigationDrawerItemColors;
                int i10 = composerStartRestartGroup.changed(navigationDrawerItemColors2) ? 8388608 : 4194304;
                i5 |= i10;
            } else {
                navigationDrawerItemColors2 = navigationDrawerItemColors;
            }
            i5 |= i10;
        } else {
            navigationDrawerItemColors2 = navigationDrawerItemColors;
        }
        int i11 = i2 & 256;
        if (i11 != 0) {
            i5 |= 100663296;
        } else if ((i & 100663296) == 0) {
            i5 |= composerStartRestartGroup.changed(mutableInteractionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (composerStartRestartGroup.shouldExecute((i5 & 38347923) != 38347922, i5 & 1)) {
            composerStartRestartGroup.startDefaults();
            ComposerKt.sourceInformation(composerStartRestartGroup, "1074@45880L5,1075@45957L8");
            if ((i & 1) != 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 64) != 0) {
                    i5 &= -3670017;
                }
                if ((i2 & 128) != 0) {
                    i5 &= -29360129;
                }
                mutableInteractionSource3 = mutableInteractionSource;
                r1 = 1;
                function9 = function5;
                function10 = function6;
                value = shape2;
                z3 = false;
                i4 = i5;
                modifier4 = modifier2;
            } else {
                if (i6 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i7 == 0) {
                    function9 = function5;
                } else {
                    function9 = null;
                }
                if (i8 == 0) {
                    function10 = function6;
                } else {
                    function10 = null;
                }
                if ((i2 & 64) == 0) {
                    value = shape2;
                    i3 = i5;
                } else {
                    value = ShapesKt.getValue(NavigationDrawerTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                    i3 = i5 & (-3670017);
                }
                if ((i2 & 128) != 0) {
                    z3 = false;
                    z2 = true;
                    navigationDrawerItemColorsM2642colorsoq7We08 = NavigationDrawerItemDefaults.INSTANCE.m2642colorsoq7We08(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 100663296, 255);
                    i3 &= -29360129;
                } else {
                    z2 = true;
                    z3 = false;
                    navigationDrawerItemColorsM2642colorsoq7We08 = navigationDrawerItemColors;
                }
                if (i11 == 0) {
                    composerStartRestartGroup = composerStartRestartGroup;
                    mutableInteractionSource3 = mutableInteractionSource;
                    navigationDrawerItemColors2 = navigationDrawerItemColorsM2642colorsoq7We08;
                    i4 = i3;
                    modifier4 = companion;
                    r1 = z2;
                } else {
                    composerStartRestartGroup = composerStartRestartGroup;
                    navigationDrawerItemColors2 = navigationDrawerItemColorsM2642colorsoq7We08;
                    mutableInteractionSource3 = null;
                    i4 = i3;
                    modifier4 = companion;
                    r1 = z2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-583709666, i4, -1, "androidx.compose.material3.NavigationDrawerItem (NavigationDrawer.kt:1077)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1695745713, "CC(remember):NavigationDrawer.kt#9igjgp");
            Composer composer3 = composerStartRestartGroup;
            Object objRememberedValue = composer3.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Function1 function1 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavigationDrawerKt.NavigationDrawerItem$lambda$64$lambda$63((SemanticsPropertyReceiver) obj);
                    }
                };
                composer3.updateRememberedValue(function1);
                objRememberedValue = function1;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final NavigationDrawerItemColors navigationDrawerItemColors4 = navigationDrawerItemColors2;
            final Function2<? super Composer, ? super Integer, Unit> function11 = function9;
            final Function2<? super Composer, ? super Integer, Unit> function12 = function10;
            Modifier modifier5 = modifier4;
            Shape shape4 = value;
            SurfaceKt.m2930Surfaced85dljk(z, function0, SizeKt.fillMaxWidth$default(SizeKt.m853heightInVpY3zN4$default(SemanticsModifierKt.semantics$default(modifier4, z3, (Function1) objRememberedValue, r1, null), NavigationDrawerTokens.INSTANCE.m3906getActiveIndicatorHeightD9Ej5fM(), 0.0f, 2, null), 0.0f, r1, null), false, shape4, navigationDrawerItemColors2.containerColor(z, composerStartRestartGroup, ((i4 >> 3) & 14) | ((i4 >> 18) & 112)).getValue().m5107unboximpl(), 0L, 0.0f, 0.0f, (BorderStroke) null, mutableInteractionSource3, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1173018444, r1, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt.NavigationDrawerItem.2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                    invoke(composer4, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code duplicated, block: B:27:0x016c  */
                /* JADX WARN: Code duplicated, block: B:28:0x01b4  */
                /* JADX WARN: Code duplicated, block: B:31:0x0231  */
                /* JADX WARN: Code duplicated, block: B:34:0x023d  */
                /* JADX WARN: Code duplicated, block: B:35:0x0243  */
                /* JADX WARN: Code duplicated, block: B:45:0x0316  */
                /* JADX WARN: Code duplicated, block: B:46:0x0359  */
                /* JADX WARN: Code duplicated, block: B:49:0x037f  */
                /* JADX WARN: Code duplicated, block: B:52:? A[RETURN, SYNTHETIC] */
                public final void invoke(Composer $composer, int $changed) {
                    Function0<ComposeUiNode> function13;
                    int $changed$iv;
                    int compositeKeyHash$iv$iv;
                    Function0<ComposeUiNode> constructor;
                    Function0<ComposeUiNode> function14;
                    Composer $this$Layout_u24lambda_u240$iv$iv;
                    ComposerKt.sourceInformation($composer, "C1090@46436L869:NavigationDrawer.kt#uh7d8r");
                    if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                        $composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1173018444, $changed, -1, "androidx.compose.material3.NavigationDrawerItem.<anonymous> (NavigationDrawer.kt:1090)");
                    }
                    Modifier modifier$iv = PaddingKt.m823paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m7905constructorimpl(16), 0.0f, Dp.m7905constructorimpl(24), 0.0f, 10, null);
                    Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                    Function2<Composer, Integer, Unit> function15 = function11;
                    NavigationDrawerItemColors navigationDrawerItemColors5 = navigationDrawerItemColors4;
                    boolean z4 = z;
                    Function2<Composer, Integer, Unit> function16 = function12;
                    Function2<Composer, Integer, Unit> function17 = function2;
                    ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
                    Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                    int $changed$iv$iv = (390 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function13 = constructor2;
                        $composer.createNode(function13);
                    } else {
                        function13 = constructor2;
                        $composer.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                        $changed$iv = 390;
                        if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i12 = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                        int i13 = (($changed$iv >> 6) & 112) | 6;
                        RowScope $this$invoke_u24lambda_u241 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer, -2013922645, "C1099@46835L203:NavigationDrawer.kt#uh7d8r");
                        if (function15 != null) {
                            $composer.startReplaceGroup(-2013920011);
                            ComposerKt.sourceInformation($composer, "1095@46642L19,1096@46684L78,1097@46779L29");
                            long iconColor = navigationDrawerItemColors5.iconColor(z4, $composer, 0).getValue().m5107unboximpl();
                            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(iconColor)), function15, $composer, ProvidedValue.$stable);
                            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), $composer, 6);
                            $composer.endReplaceGroup();
                        } else {
                            $composer.startReplaceGroup(-2013707630);
                            $composer.endReplaceGroup();
                        }
                        Modifier modifier$iv2 = RowScope.weight$default($this$invoke_u24lambda_u241, Modifier.INSTANCE, 1.0f, false, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                        int $changed$iv$iv2 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        CompositionLocalMap localMap$iv$iv2 = $composer.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer, modifier$iv2);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function14 = constructor;
                            $composer.createNode(function14);
                        } else {
                            function14 = constructor;
                            $composer.useNode();
                        }
                        $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                        int i14 = ($changed$iv$iv$iv2 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i15 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -1767721817, "C1100@46902L19,1101@46944L80:NavigationDrawer.kt#uh7d8r");
                        long labelColor = navigationDrawerItemColors5.textColor(z4, $composer, 0).getValue().m5107unboximpl();
                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(labelColor)), function17, $composer, ProvidedValue.$stable);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        if (function16 != null) {
                            $composer.startReplaceGroup(-2013454639);
                            ComposerKt.sourceInformation($composer, "1104@47088L29,1105@47158L20,1106@47201L80");
                            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), $composer, 6);
                            long badgeColor = navigationDrawerItemColors5.badgeColor(z4, $composer, 0).getValue().m5107unboximpl();
                            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(badgeColor)), function16, $composer, ProvidedValue.$stable);
                            $composer.endReplaceGroup();
                        } else {
                            $composer.startReplaceGroup(-2013238414);
                            $composer.endReplaceGroup();
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    $changed$iv = 390;
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i16 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                    int i17 = (($changed$iv >> 6) & 112) | 6;
                    RowScope $this$invoke_u24lambda_u242 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer, -2013922645, "C1099@46835L203:NavigationDrawer.kt#uh7d8r");
                    if (function15 != null) {
                        $composer.startReplaceGroup(-2013920011);
                        ComposerKt.sourceInformation($composer, "1095@46642L19,1096@46684L78,1097@46779L29");
                        long iconColor2 = navigationDrawerItemColors5.iconColor(z4, $composer, 0).getValue().m5107unboximpl();
                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(iconColor2)), function15, $composer, ProvidedValue.$stable);
                        SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), $composer, 6);
                        $composer.endReplaceGroup();
                    } else {
                        $composer.startReplaceGroup(-2013707630);
                        $composer.endReplaceGroup();
                    }
                    Modifier modifier$iv3 = RowScope.weight$default($this$invoke_u24lambda_u242, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                    int $changed$iv$iv3 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv3 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer, modifier$iv3);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function14 = constructor;
                        $composer.createNode(function14);
                    } else {
                        function14 = constructor;
                        $composer.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i18 = ($changed$iv$iv$iv3 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i19 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -1767721817, "C1100@46902L19,1101@46944L80:NavigationDrawer.kt#uh7d8r");
                    long labelColor2 = navigationDrawerItemColors5.textColor(z4, $composer, 0).getValue().m5107unboximpl();
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(labelColor2)), function17, $composer, ProvidedValue.$stable);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (function16 != null) {
                        $composer.startReplaceGroup(-2013454639);
                        ComposerKt.sourceInformation($composer, "1104@47088L29,1105@47158L20,1106@47201L80");
                        SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), $composer, 6);
                        long badgeColor2 = navigationDrawerItemColors5.badgeColor(z4, $composer, 0).getValue().m5107unboximpl();
                        CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(badgeColor2)), function16, $composer, ProvidedValue.$stable);
                        $composer.endReplaceGroup();
                    } else {
                        $composer.startReplaceGroup(-2013238414);
                        $composer.endReplaceGroup();
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i4 >> 3) & 14) | ((i4 >> 3) & 112) | ((i4 >> 6) & 57344), ((i4 >> 24) & 14) | 48, 968);
            composer2 = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape3 = shape4;
            mutableInteractionSource2 = mutableInteractionSource3;
            navigationDrawerItemColors3 = navigationDrawerItemColors4;
            modifier3 = modifier5;
            function7 = function9;
            function8 = function10;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            navigationDrawerItemColors3 = navigationDrawerItemColors;
            modifier3 = modifier2;
            function7 = function5;
            function8 = function6;
            shape3 = shape2;
            mutableInteractionSource2 = mutableInteractionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationDrawerKt.NavigationDrawerItem$lambda$65(function2, z, function0, modifier3, function7, function8, shape3, navigationDrawerItemColors3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit NavigationDrawerItem$lambda$64$lambda$63(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.m7118setRolekuIjeqM($this$semantics, Role.INSTANCE.m7106getTabo7Vup1c());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculateFraction(float a, float b, float pos) {
        return RangesKt.coerceIn((pos - a) / (b - a), 0.0f, 1.0f);
    }

    /* JADX INFO: renamed from: Scrim-Bx497Mc, reason: not valid java name */
    private static final void m2650ScrimBx497Mc(final boolean open, final Function0<Unit> function0, final Function0<Float> function1, final long color, Composer $composer, final int $changed) {
        Modifier.Companion dismissDrawer;
        Composer $composer2 = $composer.startRestartGroup(2106487387);
        ComposerKt.sourceInformation($composer2, "C(Scrim)N(open,onClose,fraction,color:c#ui.graphics.Color)1285@54138L30,1300@54636L39,1300@54585L90:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(open) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(color) ? 2048 : 1024;
        }
        if (!$composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2106487387, $dirty, -1, "androidx.compose.material3.Scrim (NavigationDrawer.kt:1284)");
            }
            Strings.Companion companion = Strings.INSTANCE;
            final String closeDrawer = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(androidx.compose.ui.R.string.close_drawer), $composer2, 0);
            if (open) {
                $composer2.startReplaceGroup(598792893);
                ComposerKt.sourceInformation($composer2, "1288@54256L35,1289@54344L187");
                Modifier.Companion companion2 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, 1404790366, "CC(remember):NavigationDrawer.kt#9igjgp");
                boolean invalid$iv = ($dirty & 112) == 32;
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = (PointerInputEventHandler) new NavigationDrawerKt$Scrim$dismissDrawer$1$1(function0);
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion2, function0, (PointerInputEventHandler) it$iv);
                ComposerKt.sourceInformationMarkerStart($composer2, 1404793334, "CC(remember):NavigationDrawer.kt#9igjgp");
                boolean invalid$iv2 = $composer2.changed(closeDrawer) | (($dirty & 112) == 32);
                Object it$iv2 = $composer2.rememberedValue();
                if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationDrawerKt.Scrim_Bx497Mc$lambda$69$lambda$68(closeDrawer, function0, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                dismissDrawer = SemanticsModifierKt.semantics(modifierPointerInput, true, (Function1) it$iv2);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(599116967);
                $composer2.endReplaceGroup();
                dismissDrawer = Modifier.INSTANCE;
            }
            Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissDrawer);
            ComposerKt.sourceInformationMarkerStart($composer2, 1404802530, "CC(remember):NavigationDrawer.kt#9igjgp");
            boolean invalid$iv3 = (($dirty & 7168) == 2048) | (($dirty & 896) == 256);
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavigationDrawerKt.Scrim_Bx497Mc$lambda$71$lambda$70(color, function1, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierThen, (Function1) it$iv3, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationDrawerKt.Scrim_Bx497Mc$lambda$72(open, function0, function1, color, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Scrim_Bx497Mc$lambda$69$lambda$68(String $closeDrawer, final Function0 $onClose, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContentDescription($this$semantics, $closeDrawer);
        SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0() { // from class: androidx.compose.material3.NavigationDrawerKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(NavigationDrawerKt.Scrim_Bx497Mc$lambda$69$lambda$68$lambda$67($onClose));
            }
        }, 1, null);
        return Unit.INSTANCE;
    }

    static final boolean Scrim_Bx497Mc$lambda$69$lambda$68$lambda$67(Function0 $onClose) {
        $onClose.invoke();
        return true;
    }

    static final Unit Scrim_Bx497Mc$lambda$71$lambda$70(long $color, Function0 $fraction, DrawScope $this$Canvas) {
        DrawScope.m5665drawRectnJ9OG0$default($this$Canvas, $color, 0L, 0L, ((Number) $fraction.invoke()).floatValue(), null, null, 0, 118, null);
        return Unit.INSTANCE;
    }

    public static final float getPredictiveBackDrawerMaxScaleXDistanceGrow() {
        return PredictiveBackDrawerMaxScaleXDistanceGrow;
    }

    public static final float getPredictiveBackDrawerMaxScaleXDistanceShrink() {
        return PredictiveBackDrawerMaxScaleXDistanceShrink;
    }

    public static final float getPredictiveBackDrawerMaxScaleYDistance() {
        return PredictiveBackDrawerMaxScaleYDistance;
    }
}
