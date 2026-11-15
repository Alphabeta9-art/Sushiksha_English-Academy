package androidx.compose.material;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImplKt;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.Velocity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BackdropScaffold.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aH\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007\u001aE\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\u0010\u0010\u001aï\u0001\u0010\u0011\u001a\u00020\u00122\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00120\u0014¢\u0006\u0002\b\u00152\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\u00120\u0014¢\u0006\u0002\b\u00152\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00120\u0014¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00012\u0019\b\u0002\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00120\n¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020#2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020\u001e2\b\b\u0002\u0010(\u001a\u00020#2\b\b\u0002\u0010)\u001a\u00020#2\b\b\u0002\u0010*\u001a\u00020#H\u0007¢\u0006\u0004\b+\u0010,\u001a-\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020#2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00120\u00142\u0006\u00100\u001a\u00020\u000bH\u0003¢\u0006\u0004\b1\u00102\u001a;\u00103\u001a\u00020\u00122\u0006\u00104\u001a\u00020\u00032\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00120\u0014¢\u0006\u0002\b\u00152\u0011\u00105\u001a\r\u0012\u0004\u0012\u00020\u00120\u0014¢\u0006\u0002\b\u0015H\u0003¢\u0006\u0002\u00106\u001a[\u00107\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00192\u0011\u00108\u001a\r\u0012\u0004\u0012\u00020\u00120\u0014¢\u0006\u0002\b\u00152\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:0\n2\u001d\u0010;\u001a\u0019\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120<¢\u0006\u0002\b\u0015H\u0003¢\u0006\u0002\u0010=\u001a\u001c\u0010B\u001a\u00020C2\n\u0010D\u001a\u0006\u0012\u0002\b\u00030E2\u0006\u0010F\u001a\u00020GH\u0000\"\u0010\u0010>\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010?\"\u0010\u0010@\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010?\"\u0010\u0010A\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010?¨\u0006H²\u0006\n\u0010I\u001a\u00020\bX\u008a\u0084\u0002²\u0006\n\u0010J\u001a\u00020\bX\u008a\u0084\u0002"}, d2 = {"BackdropScaffoldState", "Landroidx/compose/material/BackdropScaffoldState;", "initialValue", "Landroidx/compose/material/BackdropValue;", "density", "Landroidx/compose/ui/unit/Density;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmValueChange", "Lkotlin/Function1;", "", "snackbarHostState", "Landroidx/compose/material/SnackbarHostState;", "rememberBackdropScaffoldState", "confirmStateChange", "(Landroidx/compose/material/BackdropValue;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/material/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BackdropScaffoldState;", "BackdropScaffold", "", "appBar", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "backLayerContent", "frontLayerContent", "modifier", "Landroidx/compose/ui/Modifier;", "scaffoldState", "snackbarHost", "gesturesEnabled", "peekHeight", "Landroidx/compose/ui/unit/Dp;", "headerHeight", "persistentAppBar", "stickyFrontLayer", "backLayerBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "backLayerContentColor", "frontLayerShape", "Landroidx/compose/ui/graphics/Shape;", "frontLayerElevation", "frontLayerBackgroundColor", "frontLayerContentColor", "frontLayerScrimColor", "BackdropScaffold-0hNv9B8", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/material/BackdropScaffoldState;Lkotlin/jvm/functions/Function3;ZFFZZJJLandroidx/compose/ui/graphics/Shape;FJJJLandroidx/compose/runtime/Composer;III)V", "Scrim", "color", "onDismiss", "visible", "Scrim-3J-VO9M", "(JLkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "BackLayerTransition", TypedValues.AttributesType.S_TARGET, UriUtil.LOCAL_CONTENT_SCHEME, "(Landroidx/compose/material/BackdropValue;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "BackdropStack", "backLayer", "calculateBackLayerConstraints", "Landroidx/compose/ui/unit/Constraints;", "frontLayer", "Lkotlin/Function2;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;I)V", "AnimationSlideOffset", "F", "VelocityThreshold", "PositionalThreshold", "ConsumeSwipeNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "state", "Landroidx/compose/material/AnchoredDraggableState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "material", "alpha", "animationProgress"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BackdropScaffoldKt {
    private static final float AnimationSlideOffset = Dp.m7905constructorimpl(20);
    private static final float VelocityThreshold = Dp.m7905constructorimpl(ComposerImplKt.nodeKey);
    private static final float PositionalThreshold = Dp.m7905constructorimpl(56);

    /* JADX INFO: compiled from: BackdropScaffold.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BackdropValue.values().length];
            try {
                iArr[BackdropValue.Concealed.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BackdropValue.Revealed.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit BackLayerTransition$lambda$3(BackdropValue backdropValue, Function2 function2, Function2 function3, int i, Composer composer, int i2) {
        BackLayerTransition(backdropValue, function2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit BackdropScaffold_0hNv9B8$lambda$6(Function2 function2, Function2 function3, Function2 function4, Modifier modifier, BackdropScaffoldState backdropScaffoldState, Function3 function5, boolean z, float f, float f2, boolean z2, boolean z3, long j, long j2, Shape shape, float f3, long j3, long j4, long j5, int i, int i2, int i3, Composer composer, int i4) {
        m1706BackdropScaffold0hNv9B8(function2, function3, function4, modifier, backdropScaffoldState, function5, z, f, f2, z2, z3, j, j2, shape, f3, j3, j4, j5, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit BackdropStack$lambda$1(Modifier modifier, Function2 function2, Function1 function1, Function4 function4, int i, Composer composer, int i2) {
        BackdropStack(modifier, function2, function1, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Scrim_3J_VO9M$lambda$3(long j, Function0 function0, boolean z, int i, Composer composer, int i2) {
        m1707Scrim3JVO9M(j, function0, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ BackdropScaffoldState BackdropScaffoldState$default(BackdropValue backdropValue, Density density, AnimationSpec animationSpec, Function1 function1, SnackbarHostState snackbarHostState, int i, Object obj) {
        if ((i & 4) != 0) {
            animationSpec = BackdropScaffoldDefaults.INSTANCE.getAnimationSpec();
        }
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(BackdropScaffoldKt.BackdropScaffoldState$lambda$0((BackdropValue) obj2));
                }
            };
        }
        if ((i & 16) != 0) {
            snackbarHostState = new SnackbarHostState();
        }
        return BackdropScaffoldState(backdropValue, density, animationSpec, function1, snackbarHostState);
    }

    static final boolean BackdropScaffoldState$lambda$0(BackdropValue it) {
        return true;
    }

    public static final BackdropScaffoldState BackdropScaffoldState(BackdropValue initialValue, Density density, AnimationSpec<Float> animationSpec, Function1<? super BackdropValue, Boolean> function1, SnackbarHostState snackbarHostState) {
        BackdropScaffoldState it = new BackdropScaffoldState(initialValue, animationSpec, function1, snackbarHostState);
        it.setDensity$material(density);
        return it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rememberBackdropScaffoldState$lambda$0$0(BackdropValue it) {
        return true;
    }

    public static final BackdropScaffoldState rememberBackdropScaffoldState(final BackdropValue initialValue, AnimationSpec<Float> animationSpec, Function1<? super BackdropValue, Boolean> function1, SnackbarHostState snackbarHostState, Composer $composer, int $changed, int i) {
        final Function1<? super BackdropValue, Boolean> function2;
        final SnackbarHostState snackbarHostState2;
        ComposerKt.sourceInformationMarkerStart($composer, -862178912, "C(rememberBackdropScaffoldState)N(initialValue,animationSpec,confirmStateChange,snackbarHostState)255@10390L8,256@10443L32,258@10535L7,270@10922L267,259@10554L635:BackdropScaffold.kt#jmzs0o");
        final AnimationSpec<Float> animationSpec2 = (i & 2) != 0 ? BackdropScaffoldDefaults.INSTANCE.getAnimationSpec() : animationSpec;
        if ((i & 4) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, 374760200, "CC(remember):BackdropScaffold.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(BackdropScaffoldKt.rememberBackdropScaffoldState$lambda$0$0((BackdropValue) obj));
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            function2 = (Function1) it$iv;
        } else {
            function2 = function1;
        }
        if ((i & 8) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, 374761920, "CC(remember):BackdropScaffold.kt#9igjgp");
            Object it$iv2 = $composer.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new SnackbarHostState();
                $composer.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            SnackbarHostState snackbarHostState3 = (SnackbarHostState) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer);
            snackbarHostState2 = snackbarHostState3;
        } else {
            snackbarHostState2 = snackbarHostState;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-862178912, $changed, -1, "androidx.compose.material.rememberBackdropScaffoldState (BackdropScaffold.kt:257)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Density density = (Density) objConsume;
        Object[] objArr = {animationSpec2, function2, snackbarHostState2};
        Saver<BackdropScaffoldState, ?> Saver = BackdropScaffoldState.INSTANCE.Saver(animationSpec2, function2, snackbarHostState2, density);
        ComposerKt.sourceInformationMarkerStart($composer, 374777483, "CC(remember):BackdropScaffold.kt#9igjgp");
        boolean z = true;
        boolean zChanged = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialValue.ordinal())) || ($changed & 6) == 4) | $composer.changed(density) | $composer.changedInstance(animationSpec2) | (((($changed & 896) ^ 384) > 256 && $composer.changed(function2)) || ($changed & 384) == 256);
        if (((($changed & 7168) ^ 3072) <= 2048 || !$composer.changed(snackbarHostState2)) && ($changed & 3072) != 2048) {
            z = false;
        }
        boolean invalid$iv = zChanged | z;
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
            Object value$iv3 = new Function0() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BackdropScaffoldKt.BackdropScaffoldState(initialValue, density, animationSpec2, function2, snackbarHostState2);
                }
            };
            $composer.updateRememberedValue(value$iv3);
            it$iv3 = value$iv3;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        BackdropScaffoldState backdropScaffoldState = (BackdropScaffoldState) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) Saver, (Function0) it$iv3, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return backdropScaffoldState;
    }

    /* JADX INFO: renamed from: BackdropScaffold-0hNv9B8, reason: not valid java name */
    public static final void m1706BackdropScaffold0hNv9B8(Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, Modifier modifier, BackdropScaffoldState scaffoldState, Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function5, boolean gesturesEnabled, float f, float f2, boolean persistentAppBar, boolean stickyFrontLayer, long j, long j2, Shape frontLayerShape, float f3, long j3, long j4, long j5, Composer $composer, final int $changed, final int $changed1, final int i) {
        BackdropScaffoldState backdropScaffoldState;
        Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function6;
        boolean z;
        float f4;
        float f5;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        Composer $composer2;
        final Modifier modifier2;
        final float f6;
        final float f7;
        final boolean persistentAppBar2;
        final boolean stickyFrontLayer2;
        final long j6;
        final float f8;
        final long j7;
        final long j8;
        final long j9;
        final boolean gesturesEnabled2;
        final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function8;
        final BackdropScaffoldState scaffoldState2;
        final long j10;
        final Shape frontLayerShape2;
        Modifier.Companion modifier3;
        final BackdropScaffoldState scaffoldState3;
        boolean gesturesEnabled3;
        float fM1698getPeekHeightD9Ej5fM;
        float fM1697getHeaderHeightD9Ej5fM;
        final boolean persistentAppBar3;
        boolean stickyFrontLayer3;
        long jM1765getPrimary0d7_KjU;
        int $dirty1;
        long jM1783contentColorForek8zF_U;
        Shape frontLayerShape3;
        float fM1696getFrontLayerElevationD9Ej5fM;
        long jM1769getSurface0d7_KjU;
        long jM1783contentColorForek8zF_U2;
        final Shape frontLayerShape4;
        final long frontLayerScrimColor;
        final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function9;
        final boolean stickyFrontLayer4;
        int $dirty2;
        final long j11;
        long j12;
        final Modifier modifier4;
        final boolean gesturesEnabled4;
        long j13;
        final float f9;
        int i2;
        Composer $composer3 = $composer.startRestartGroup(-1364899063);
        ComposerKt.sourceInformation($composer3, "C(BackdropScaffold)N(appBar,backLayerContent,frontLayerContent,modifier,scaffoldState,snackbarHost,gesturesEnabled,peekHeight:c#ui.unit.Dp,headerHeight:c#ui.unit.Dp,persistentAppBar,stickyFrontLayer,backLayerBackgroundColor:c#ui.graphics.Color,backLayerContentColor:c#ui.graphics.Color,frontLayerShape,frontLayerElevation:c#ui.unit.Dp,frontLayerBackgroundColor:c#ui.graphics.Color,frontLayerContentColor:c#ui.graphics.Color,frontLayerScrimColor:c#ui.graphics.Color)365@16886L7,366@16909L35,366@16898L46,368@16987L7,369@17061L7,372@17135L384,386@17590L100,393@17848L4098,393@17768L4178:BackdropScaffold.kt#jmzs0o");
        int $dirty = $changed;
        int $dirty3 = $changed1;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(modifier) ? 2048 : 1024;
        }
        int i4 = 8192;
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                backdropScaffoldState = scaffoldState;
                if ($composer3.changed(backdropScaffoldState)) {
                    i2 = 16384;
                }
                $dirty |= i2;
            } else {
                backdropScaffoldState = scaffoldState;
            }
            i2 = 8192;
            $dirty |= i2;
        } else {
            backdropScaffoldState = scaffoldState;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function6 = function5;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function6 = function5;
            $dirty |= $composer3.changedInstance(function6) ? 131072 : 65536;
        } else {
            function6 = function5;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty |= 1572864;
            z = gesturesEnabled;
        } else if (($changed & 1572864) == 0) {
            z = gesturesEnabled;
            $dirty |= $composer3.changed(z) ? 1048576 : 524288;
        } else {
            z = gesturesEnabled;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                f4 = f;
                int i7 = $composer3.changed(f4) ? 8388608 : 4194304;
                $dirty |= i7;
            } else {
                f4 = f;
            }
            $dirty |= i7;
        } else {
            f4 = f;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0) {
                f5 = f2;
                int i8 = $composer3.changed(f5) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                $dirty |= i8;
            } else {
                f5 = f2;
            }
            $dirty |= i8;
        } else {
            f5 = f2;
        }
        int i9 = i & 512;
        if (i9 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changed(persistentAppBar) ? 536870912 : 268435456;
        }
        int i10 = i & 1024;
        if (i10 != 0) {
            $dirty3 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty3 |= $composer3.changed(stickyFrontLayer) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty3 |= ((i & 2048) == 0 && $composer3.changed(j)) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty3 |= ((i & 4096) == 0 && $composer3.changed(j2)) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty3 |= ((i & 8192) == 0 && $composer3.changed(frontLayerShape)) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            if ((i & 16384) == 0 && $composer3.changed(f3)) {
                i4 = 16384;
            }
            $dirty3 |= i4;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= ((i & 32768) == 0 && $composer3.changed(j3)) ? 131072 : 65536;
        }
        if (($changed1 & 1572864) == 0) {
            $dirty3 |= ((i & 65536) == 0 && $composer3.changed(j4)) ? 1048576 : 524288;
        }
        if (($changed1 & 12582912) == 0) {
            $dirty3 |= ((i & 131072) == 0 && $composer3.changed(j5)) ? 8388608 : 4194304;
        }
        int $dirty4 = $dirty3;
        if ($composer3.shouldExecute(((306783379 & $dirty) == 306783378 && ($dirty4 & 4793491) == 4793490) ? false : true, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "349@15888L40,356@16297L6,357@16348L41,358@16445L15,360@16591L6,361@16643L42,362@16746L20");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 128) != 0) {
                    $dirty &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty &= -234881025;
                }
                if ((i & 2048) != 0) {
                    $dirty4 &= -113;
                }
                int $dirty5 = $dirty4;
                if ((i & 4096) != 0) {
                    $dirty5 &= -897;
                }
                if ((i & 8192) != 0) {
                    $dirty5 &= -7169;
                }
                if ((i & 16384) != 0) {
                    $dirty5 &= -57345;
                }
                if ((i & 32768) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 65536) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i & 131072) != 0) {
                    $dirty5 &= -29360129;
                }
                stickyFrontLayer4 = stickyFrontLayer;
                frontLayerShape4 = frontLayerShape;
                f9 = f3;
                j11 = j3;
                jM1783contentColorForek8zF_U2 = j4;
                frontLayerScrimColor = j5;
                $dirty2 = $dirty5;
                scaffoldState3 = backdropScaffoldState;
                fM1698getPeekHeightD9Ej5fM = f4;
                fM1697getHeaderHeightD9Ej5fM = f5;
                gesturesEnabled4 = z;
                function9 = function6;
                modifier4 = modifier;
                persistentAppBar3 = persistentAppBar;
                j12 = j;
                j13 = j2;
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if ((i & 16) != 0) {
                    scaffoldState3 = rememberBackdropScaffoldState(BackdropValue.Concealed, null, null, null, $composer3, 6, 14);
                    $dirty &= -57345;
                } else {
                    scaffoldState3 = scaffoldState;
                }
                Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> lambda$2057676608$material = i5 != 0 ? ComposableSingletons$BackdropScaffoldKt.INSTANCE.getLambda$2057676608$material() : function6;
                if (i6 == 0) {
                    gesturesEnabled3 = z;
                } else {
                    gesturesEnabled3 = true;
                }
                if ((i & 128) == 0) {
                    fM1698getPeekHeightD9Ej5fM = f;
                } else {
                    fM1698getPeekHeightD9Ej5fM = BackdropScaffoldDefaults.INSTANCE.m1698getPeekHeightD9Ej5fM();
                    $dirty &= -29360129;
                }
                if ((i & 256) == 0) {
                    fM1697getHeaderHeightD9Ej5fM = f2;
                } else {
                    fM1697getHeaderHeightD9Ej5fM = BackdropScaffoldDefaults.INSTANCE.m1697getHeaderHeightD9Ej5fM();
                    $dirty &= -234881025;
                }
                if (i9 == 0) {
                    persistentAppBar3 = persistentAppBar;
                } else {
                    persistentAppBar3 = true;
                }
                if (i10 == 0) {
                    stickyFrontLayer3 = stickyFrontLayer;
                } else {
                    stickyFrontLayer3 = true;
                }
                if ((i & 2048) == 0) {
                    jM1765getPrimary0d7_KjU = j;
                    $dirty1 = $dirty4;
                } else {
                    jM1765getPrimary0d7_KjU = MaterialTheme.INSTANCE.getColors($composer3, 6).m1765getPrimary0d7_KjU();
                    $dirty1 = $dirty4 & (-113);
                }
                if ((i & 4096) == 0) {
                    jM1783contentColorForek8zF_U = j2;
                } else {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1765getPrimary0d7_KjU, $composer3, ($dirty1 >> 3) & 14);
                    $dirty1 &= -897;
                }
                if ((i & 8192) != 0) {
                    frontLayerShape3 = BackdropScaffoldDefaults.INSTANCE.getFrontLayerShape($composer3, 0);
                    $dirty1 &= -7169;
                } else {
                    frontLayerShape3 = frontLayerShape;
                }
                if ((i & 16384) == 0) {
                    fM1696getFrontLayerElevationD9Ej5fM = f3;
                } else {
                    fM1696getFrontLayerElevationD9Ej5fM = BackdropScaffoldDefaults.INSTANCE.m1696getFrontLayerElevationD9Ej5fM();
                    $dirty1 &= -57345;
                }
                if ((i & 32768) != 0) {
                    $dirty1 &= -458753;
                    jM1769getSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer3, 6).m1769getSurface0d7_KjU();
                } else {
                    jM1769getSurface0d7_KjU = j3;
                }
                if ((i & 65536) == 0) {
                    jM1783contentColorForek8zF_U2 = j4;
                } else {
                    jM1783contentColorForek8zF_U2 = ColorsKt.m1783contentColorForek8zF_U(jM1769getSurface0d7_KjU, $composer3, ($dirty1 >> 15) & 14);
                    $dirty1 &= -3670017;
                }
                if ((i & 131072) == 0) {
                    frontLayerShape4 = frontLayerShape3;
                    frontLayerScrimColor = j5;
                    function9 = lambda$2057676608$material;
                    stickyFrontLayer4 = stickyFrontLayer3;
                    $dirty2 = $dirty1;
                    j11 = jM1769getSurface0d7_KjU;
                    j12 = jM1765getPrimary0d7_KjU;
                    modifier4 = modifier3;
                    gesturesEnabled4 = gesturesEnabled3;
                    j13 = jM1783contentColorForek8zF_U;
                    f9 = fM1696getFrontLayerElevationD9Ej5fM;
                } else {
                    function9 = lambda$2057676608$material;
                    stickyFrontLayer4 = stickyFrontLayer3;
                    $dirty2 = $dirty1 & (-29360129);
                    j11 = jM1769getSurface0d7_KjU;
                    frontLayerScrimColor = BackdropScaffoldDefaults.INSTANCE.getFrontLayerScrimColor($composer3, 0);
                    j12 = jM1765getPrimary0d7_KjU;
                    modifier4 = modifier3;
                    frontLayerShape4 = frontLayerShape3;
                    gesturesEnabled4 = gesturesEnabled3;
                    j13 = jM1783contentColorForek8zF_U;
                    f9 = fM1696getFrontLayerElevationD9Ej5fM;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1364899063, $dirty, $dirty2, "androidx.compose.material.BackdropScaffold (BackdropScaffold.kt:363)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            int $dirty6 = $dirty2;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Density density = (Density) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer3, -1702934228, "CC(remember):BackdropScaffold.kt#9igjgp");
            boolean invalid$iv = ((((57344 & $dirty) ^ 24576) > 16384 && $composer3.changed(scaffoldState3)) || ($dirty & 24576) == 16384) | $composer3.changed(density);
            Object value$iv = $composer3.rememberedValue();
            if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function0() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BackdropScaffoldKt.BackdropScaffold_0hNv9B8$lambda$0$0(scaffoldState3, density);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.SideEffect((Function0) value$iv, $composer3, 0);
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density $this$BackdropScaffold_0hNv9B8_u24lambda_u241 = (Density) objConsume2;
            final float peekHeightPx = $this$BackdropScaffold_0hNv9B8_u24lambda_u241.mo406toPx0680j_4(fM1698getPeekHeightD9Ej5fM);
            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer3.consume(localDensity3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density $this$BackdropScaffold_0hNv9B8_u24lambda_u242 = (Density) objConsume3;
            final float headerHeightPx = $this$BackdropScaffold_0hNv9B8_u24lambda_u242.mo406toPx0680j_4(fM1697getHeaderHeightD9Ej5fM);
            function7 = function2;
            final Function2 backLayer = ComposableLambdaKt.rememberComposableLambda(998762775, true, new Function2() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BackdropScaffoldKt.BackdropScaffold_0hNv9B8$lambda$3(persistentAppBar3, scaffoldState3, function7, function3, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54);
            ComposerKt.sourceInformationMarkerStart($composer3, -1702912371, "CC(remember):BackdropScaffold.kt#9igjgp");
            boolean invalid$iv2 = $composer3.changed(headerHeightPx);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return BackdropScaffoldKt.BackdropScaffold_0hNv9B8$lambda$4$0(headerHeightPx, (Constraints) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv = value$iv2;
            }
            final Function1 calculateBackLayerConstraints = (Function1) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final AnchoredDraggableState<BackdropValue> anchoredDraggableState$material = scaffoldState3.getAnchoredDraggableState$material();
            final BackdropScaffoldState scaffoldState4 = scaffoldState3;
            final float f10 = fM1698getPeekHeightD9Ej5fM;
            final float f11 = fM1697getHeaderHeightD9Ej5fM;
            final long j14 = jM1783contentColorForek8zF_U2;
            long j15 = j13;
            SurfaceKt.m1954SurfaceFjzlyU(null, null, j12, j15, null, 0.0f, ComposableLambdaKt.rememberComposableLambda(-1243386547, true, new Function2() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BackdropScaffoldKt.BackdropScaffold_0hNv9B8$lambda$5(modifier4, backLayer, calculateBackLayerConstraints, headerHeightPx, stickyFrontLayer4, gesturesEnabled4, scaffoldState4, anchoredDraggableState$material, frontLayerShape4, j11, j14, f9, f11, peekHeightPx, f10, function4, frontLayerScrimColor, function9, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer3, (($dirty6 << 3) & 896) | 1572864 | (($dirty6 << 3) & 7168), 51);
            long j16 = j12;
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            persistentAppBar2 = persistentAppBar3;
            modifier2 = modifier4;
            stickyFrontLayer2 = stickyFrontLayer4;
            frontLayerShape2 = frontLayerShape4;
            f8 = f9;
            f7 = f11;
            f6 = f10;
            j6 = j16;
            j10 = j15;
            gesturesEnabled2 = gesturesEnabled4;
            scaffoldState2 = scaffoldState4;
            j7 = j11;
            j8 = j14;
            j9 = frontLayerScrimColor;
            function8 = function9;
        } else {
            function7 = function2;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            f6 = f;
            f7 = f2;
            persistentAppBar2 = persistentAppBar;
            stickyFrontLayer2 = stickyFrontLayer;
            j6 = j;
            f8 = f3;
            j7 = j3;
            j8 = j4;
            j9 = j5;
            gesturesEnabled2 = z;
            function8 = function6;
            scaffoldState2 = scaffoldState;
            j10 = j2;
            frontLayerShape2 = frontLayerShape;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Function2<? super Composer, ? super Integer, Unit> function10 = function7;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BackdropScaffoldKt.BackdropScaffold_0hNv9B8$lambda$6(function10, function3, function4, modifier2, scaffoldState2, function8, gesturesEnabled2, f6, f7, persistentAppBar2, stickyFrontLayer2, j6, j10, frontLayerShape2, f8, j7, j8, j9, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BackdropScaffold_0hNv9B8$lambda$0$0(BackdropScaffoldState $scaffoldState, Density $density) {
        $scaffoldState.setDensity$material($density);
        return Unit.INSTANCE;
    }

    static final Unit BackdropScaffold_0hNv9B8$lambda$3(boolean $persistentAppBar, BackdropScaffoldState $scaffoldState, Function2 $appBar, Function2 $backLayerContent, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C:BackdropScaffold.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(998762775, $changed, -1, "androidx.compose.material.BackdropScaffold.<anonymous> (BackdropScaffold.kt:373)");
            }
            if ($persistentAppBar) {
                $composer.startReplaceGroup(-1391557043);
                ComposerKt.sourceInformation($composer, "374@17189L94");
                ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                Modifier modifier$iv = Modifier.INSTANCE;
                Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv = $changed$iv$iv << 6;
                int $changed$iv$iv$iv = ($changed$iv & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i2 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -541208306, "C375@17218L8,376@17247L18:BackdropScaffold.kt#jmzs0o");
                $appBar.invoke($composer, 0);
                $backLayerContent.invoke($composer, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1391423619);
                ComposerKt.sourceInformation($composer, "379@17321L174");
                BackLayerTransition($scaffoldState.getAnchoredDraggableState$material().getTargetValue(), $appBar, $backLayerContent, $composer, 0);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Constraints BackdropScaffold_0hNv9B8$lambda$4$0(float $headerHeightPx, Constraints it) {
        long value = it.getValue();
        return Constraints.m7845boximpl(ConstraintsKt.m7878offsetNN6EwU$default(Constraints.m7847copyZbe2FdA(value, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(value) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(value) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(value) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(value) : 0), 0, -MathKt.roundToInt($headerHeightPx), 1, null));
    }

    static final Unit BackdropScaffold_0hNv9B8$lambda$5(Modifier $modifier, Function2 $backLayer, Function1 $calculateBackLayerConstraints, final float $headerHeightPx, final boolean $stickyFrontLayer, final boolean $gesturesEnabled, final BackdropScaffoldState $scaffoldState, final AnchoredDraggableState $state, final Shape $frontLayerShape, final long j, final long j2, final float f, final float f2, final float $peekHeightPx, final float f3, final Function2 $frontLayerContent, final long j3, final Function3 $snackbarHost, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C394@17870L24,395@17983L3957,395@17903L4037:BackdropScaffold.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1243386547, $changed, -1, "androidx.compose.material.BackdropScaffold.<anonymous> (BackdropScaffold.kt:394)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object it$iv$iv = $composer.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer);
                $composer.updateRememberedValue(value$iv$iv);
                it$iv$iv = value$iv$iv;
            }
            final CoroutineScope scope = (CoroutineScope) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            BackdropStack(SizeKt.fillMaxSize$default($modifier, 0.0f, 1, null), $backLayer, $calculateBackLayerConstraints, ComposableLambdaKt.rememberComposableLambda(-323178911, true, new Function4() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function4
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    return BackdropScaffoldKt.BackdropScaffold_0hNv9B8$lambda$5$0($headerHeightPx, $stickyFrontLayer, $gesturesEnabled, $scaffoldState, $state, scope, $frontLayerShape, j, j2, f, f2, $peekHeightPx, f3, $frontLayerContent, j3, $snackbarHost, (Constraints) obj, ((Float) obj2).floatValue(), (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 3120);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:46:0x0146  */
    public static final Unit BackdropScaffold_0hNv9B8$lambda$5$0(float $headerHeightPx, boolean $stickyFrontLayer, final boolean $gesturesEnabled, final BackdropScaffoldState $scaffoldState, AnchoredDraggableState $state, final CoroutineScope $scope, Shape $frontLayerShape, long j, long j2, float f, float f2, final float $peekHeightPx, final float f3, final Function2 $frontLayerContent, final long j3, Function3 $snackbarHost, Constraints constraints, float backLayerHeight, Composer $composer, int $changed) {
        Modifier.Companion companionNestedScroll$default;
        float fM7905constructorimpl;
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "CN(constraints:c#ui.unit.Constraints,backLayerHeight)437@19852L735,458@20799L576,411@18496L2879,474@21418L512:BackdropScaffold.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(constraints.getValue()) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer.changed(backLayerHeight) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if ($composer.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-323178911, $dirty2, -1, "androidx.compose.material.BackdropScaffold.<anonymous>.<anonymous> (BackdropScaffold.kt:398)");
            }
            final Ref.FloatRef revealedHeight = new Ref.FloatRef();
            revealedHeight.element = Constraints.m7857getMaxHeightimpl(constraints.getValue()) - $headerHeightPx;
            if ($stickyFrontLayer) {
                revealedHeight.element = Math.min(revealedHeight.element, backLayerHeight);
            }
            if ($gesturesEnabled) {
                companionNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(Modifier.INSTANCE, $scaffoldState.getNestedScrollConnection(), null, 2, null);
            } else {
                companionNestedScroll$default = Modifier.INSTANCE;
            }
            Modifier nestedScroll = companionNestedScroll$default;
            Modifier modifierAnchoredDraggable = AnchoredDraggableKt.anchoredDraggable(AnchoredDraggableKt.draggableAnchors(nestedScroll, $state, Orientation.Vertical, new Function2() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BackdropScaffoldKt.BackdropScaffold_0hNv9B8$lambda$5$0$0($peekHeightPx, $scaffoldState, revealedHeight, (IntSize) obj, (Constraints) obj2);
                }
            }), $state, Orientation.Vertical, (48 & 4) != 0 ? true : $gesturesEnabled, (48 & 8) != 0 ? false : false, (48 & 16) != 0 ? null : null, (48 & 32) != 0 ? $state.isAnimationRunning() : false);
            ComposerKt.sourceInformationMarkerStart($composer, -1337227616, "CC(remember):BackdropScaffold.kt#9igjgp");
            boolean invalid$iv = $composer.changed($scaffoldState) | $composer.changedInstance($scope);
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda21
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return BackdropScaffoldKt.BackdropScaffold_0hNv9B8$lambda$5$0$1$0($scaffoldState, $scope, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            SurfaceKt.m1954SurfaceFjzlyU(SemanticsModifierKt.semantics$default(modifierAnchoredDraggable, false, (Function1) it$iv, 1, null), $frontLayerShape, j, j2, null, f, ComposableLambdaKt.rememberComposableLambda(356452509, true, new Function2() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BackdropScaffoldKt.BackdropScaffold_0hNv9B8$lambda$5$0$2(f3, $frontLayerContent, j3, $gesturesEnabled, $scaffoldState, $scope, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 1572864, 16);
            Modifier.Companion companion = Modifier.INSTANCE;
            if ($scaffoldState.isRevealed()) {
                if (revealedHeight.element == ((float) Constraints.m7857getMaxHeightimpl(constraints.getValue())) - $headerHeightPx) {
                    fM7905constructorimpl = f2;
                } else {
                    fM7905constructorimpl = Dp.m7905constructorimpl(0);
                }
            } else {
                fM7905constructorimpl = Dp.m7905constructorimpl(0);
            }
            Modifier modifier$iv = PaddingKt.m823paddingqDBjuR0$default(companion, 0.0f, 0.0f, 0.0f, fM7905constructorimpl, 7, null);
            Alignment contentAlignment$iv = Alignment.INSTANCE.getBottomCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -725880465, "C486@21871L45:BackdropScaffold.kt#jmzs0o");
            $snackbarHost.invoke($scaffoldState.getSnackbarHostState(), $composer, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair BackdropScaffold_0hNv9B8$lambda$5$0$0(final float $peekHeightPx, BackdropScaffoldState $scaffoldState, final Ref.FloatRef $revealedHeight, IntSize layoutSize, Constraints constraints) {
        BackdropValue newTarget;
        long arg0$iv = layoutSize.m8080unboximpl();
        final float sheetHeight = (int) (arg0$iv & 4294967295L);
        long arg0$iv2 = layoutSize.m8080unboximpl();
        final float collapsedHeight = ((int) (4294967295L & arg0$iv2)) - $peekHeightPx;
        DraggableAnchors newAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BackdropScaffoldKt.BackdropScaffold_0hNv9B8$lambda$5$0$0$0(sheetHeight, $peekHeightPx, collapsedHeight, $revealedHeight, (DraggableAnchorsConfig) obj);
            }
        });
        switch (WhenMappings.$EnumSwitchMapping$0[$scaffoldState.getTargetValue().ordinal()]) {
            case 1:
                newTarget = BackdropValue.Concealed;
                break;
            case 2:
                newTarget = !newAnchors.hasAnchorFor(BackdropValue.Revealed) ? BackdropValue.Concealed : BackdropValue.Revealed;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return TuplesKt.to(newAnchors, newTarget);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:12:0x0020  */
    public static final Unit BackdropScaffold_0hNv9B8$lambda$5$0$0$0(float $sheetHeight, float $peekHeightPx, float $collapsedHeight, Ref.FloatRef $revealedHeight, DraggableAnchorsConfig $this$DraggableAnchors) {
        if ($sheetHeight == 0.0f) {
            $this$DraggableAnchors.at(BackdropValue.Concealed, $collapsedHeight);
        } else {
            if ($sheetHeight == $peekHeightPx) {
                $this$DraggableAnchors.at(BackdropValue.Concealed, $collapsedHeight);
            } else {
                $this$DraggableAnchors.at(BackdropValue.Concealed, $peekHeightPx);
                $this$DraggableAnchors.at(BackdropValue.Revealed, $revealedHeight.element);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BackdropScaffold_0hNv9B8$lambda$5$0$1$0(final BackdropScaffoldState $scaffoldState, final CoroutineScope $scope, SemanticsPropertyReceiver $this$semantics) {
        if ($scaffoldState.isConcealed()) {
            SemanticsPropertiesKt.collapse$default($this$semantics, null, new Function0() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(BackdropScaffoldKt.BackdropScaffold_0hNv9B8$lambda$5$0$1$0$0($scaffoldState, $scope));
                }
            }, 1, null);
        } else {
            SemanticsPropertiesKt.expand$default($this$semantics, null, new Function0() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(BackdropScaffoldKt.BackdropScaffold_0hNv9B8$lambda$5$0$1$0$1($scaffoldState, $scope));
                }
            }, 1, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean BackdropScaffold_0hNv9B8$lambda$5$0$1$0$0(BackdropScaffoldState $scaffoldState, CoroutineScope $scope) {
        if ($scaffoldState.getConfirmValueChange().invoke(BackdropValue.Revealed).booleanValue()) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new BackdropScaffoldKt$BackdropScaffold$2$1$2$1$1$1($scaffoldState, null), 3, null);
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean BackdropScaffold_0hNv9B8$lambda$5$0$1$0$1(BackdropScaffoldState $scaffoldState, CoroutineScope $scope) {
        if ($scaffoldState.getConfirmValueChange().invoke(BackdropValue.Concealed).booleanValue()) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new BackdropScaffoldKt$BackdropScaffold$2$1$2$1$2$1($scaffoldState, null), 3, null);
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:27:0x016c  */
    /* JADX WARN: Code duplicated, block: B:30:0x0177  */
    /* JADX WARN: Code duplicated, block: B:34:0x0196  */
    /* JADX WARN: Code duplicated, block: B:35:0x0199  */
    /* JADX WARN: Code duplicated, block: B:38:0x01bf  */
    public static final Unit BackdropScaffold_0hNv9B8$lambda$5$0$2(float f, Function2 $frontLayerContent, long j, final boolean $gesturesEnabled, final BackdropScaffoldState $scaffoldState, final CoroutineScope $scope, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        int $changed$iv;
        boolean invalid$iv;
        Object value$iv;
        Composer $composer2;
        boolean z;
        ComposerKt.sourceInformation($composer, "C459@20817L544:BackdropScaffold.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(356452509, $changed, -1, "androidx.compose.material.BackdropScaffold.<anonymous>.<anonymous>.<anonymous> (BackdropScaffold.kt:459)");
            }
            Modifier modifier$iv = PaddingKt.m823paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, f, 7, null);
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                $changed$iv = 0;
            } else {
                $changed$iv = 0;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i2 = (($changed$iv >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1810730913, "C460@20882L19,463@21019L228,461@20922L421:BackdropScaffold.kt#jmzs0o");
                $frontLayerContent.invoke($composer, 0);
                ComposerKt.sourceInformationMarkerStart($composer, -1443879845, "CC(remember):BackdropScaffold.kt#9igjgp");
                invalid$iv = $composer.changed($gesturesEnabled) | $composer.changed($scaffoldState) | $composer.changedInstance($scope);
                value$iv = $composer.rememberedValue();
                if (!invalid$iv) {
                    $composer2 = $composer;
                    if (value$iv == Composer.INSTANCE.getEmpty()) {
                    }
                    Function0 function1 = (Function0) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if ($scaffoldState.getTargetValue() == BackdropValue.Revealed) {
                        z = true;
                    } else {
                        z = false;
                    }
                    m1707Scrim3JVO9M(j, function1, z, $composer2, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer2 = $composer;
                }
                value$iv = new Function0() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BackdropScaffoldKt.BackdropScaffold_0hNv9B8$lambda$5$0$2$0$0$0($gesturesEnabled, $scaffoldState, $scope);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                Function0 function2 = (Function0) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if ($scaffoldState.getTargetValue() == BackdropValue.Revealed) {
                    z = true;
                } else {
                    z = false;
                }
                m1707Scrim3JVO9M(j, function2, z, $composer2, 0);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i4 = (($changed$iv >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1810730913, "C460@20882L19,463@21019L228,461@20922L421:BackdropScaffold.kt#jmzs0o");
            $frontLayerContent.invoke($composer, 0);
            ComposerKt.sourceInformationMarkerStart($composer, -1443879845, "CC(remember):BackdropScaffold.kt#9igjgp");
            invalid$iv = $composer.changed($gesturesEnabled) | $composer.changed($scaffoldState) | $composer.changedInstance($scope);
            value$iv = $composer.rememberedValue();
            if (!invalid$iv) {
                $composer2 = $composer;
                if (value$iv == Composer.INSTANCE.getEmpty()) {
                }
                Function0 function3 = (Function0) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if ($scaffoldState.getTargetValue() == BackdropValue.Revealed) {
                    z = true;
                } else {
                    z = false;
                }
                m1707Scrim3JVO9M(j, function3, z, $composer2, 0);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2 = $composer;
            }
            value$iv = new Function0() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BackdropScaffoldKt.BackdropScaffold_0hNv9B8$lambda$5$0$2$0$0$0($gesturesEnabled, $scaffoldState, $scope);
                }
            };
            $composer.updateRememberedValue(value$iv);
            Function0 function4 = (Function0) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if ($scaffoldState.getTargetValue() == BackdropValue.Revealed) {
                z = true;
            } else {
                z = false;
            }
            m1707Scrim3JVO9M(j, function4, z, $composer2, 0);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BackdropScaffold_0hNv9B8$lambda$5$0$2$0$0$0(boolean $gesturesEnabled, BackdropScaffoldState $scaffoldState, CoroutineScope $scope) {
        if ($gesturesEnabled && $scaffoldState.getConfirmValueChange().invoke(BackdropValue.Concealed).booleanValue()) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new BackdropScaffoldKt$BackdropScaffold$2$1$3$1$1$1$1($scaffoldState, null), 3, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: Scrim-3J-VO9M, reason: not valid java name */
    private static final void m1707Scrim3JVO9M(final long j, final Function0<Unit> function0, final boolean visible, Composer $composer, final int $changed) {
        Modifier.Companion dismissModifier;
        Composer $composer2 = $composer.startRestartGroup(-92141505);
        ComposerKt.sourceInformation($composer2, "C(Scrim)N(color:c#ui.graphics.Color,onDismiss,visible):BackdropScaffold.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(j) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(visible) ? 256 : 128;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-92141505, $dirty, -1, "androidx.compose.material.Scrim (BackdropScaffold.kt:493)");
            }
            if ((j != 16 ? 1 : 0) != 0) {
                $composer2.startReplaceGroup(-503366080);
                ComposerKt.sourceInformation($composer2, "496@22099L87,503@22447L62,503@22394L115");
                int $dirty2 = $dirty;
                final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(visible ? 1065353216 : 0, new TweenSpec(0, 0, null, 7, null), 0.0f, null, null, $composer2, 48, 28);
                if (visible) {
                    $composer2.startReplaceGroup(-503200478);
                    ComposerKt.sourceInformation($composer2, "499@22288L37");
                    Modifier.Companion companion = Modifier.INSTANCE;
                    Unit unit = Unit.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1646337124, "CC(remember):BackdropScaffold.kt#9igjgp");
                    boolean invalid$iv = ($dirty2 & 112) == 32;
                    Object it$iv = $composer2.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = (PointerInputEventHandler) new BackdropScaffoldKt$Scrim$dismissModifier$1$1(function0);
                        $composer2.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    dismissModifier = SuspendingPointerInputFilterKt.pointerInput(companion, unit, (PointerInputEventHandler) it$iv);
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(-503100069);
                    $composer2.endReplaceGroup();
                    dismissModifier = Modifier.INSTANCE;
                }
                Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissModifier);
                ComposerKt.sourceInformationMarkerStart($composer2, 1646342237, "CC(remember):BackdropScaffold.kt#9igjgp");
                boolean invalid$iv2 = (($dirty2 & 14) == 4) | $composer2.changed(stateAnimateFloatAsState);
                Object it$iv2 = $composer2.rememberedValue();
                if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = new Function1() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda16
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return BackdropScaffoldKt.Scrim_3J_VO9M$lambda$2$0(j, stateAnimateFloatAsState, (DrawScope) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(modifierThen, (Function1) it$iv2, $composer2, 0);
            } else {
                $composer2.startReplaceGroup(-525268541);
            }
            $composer2.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BackdropScaffoldKt.Scrim_3J_VO9M$lambda$3(j, function0, visible, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float Scrim_3J_VO9M$lambda$0(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Scrim_3J_VO9M$lambda$2$0(long j, State $alpha$delegate, DrawScope $this$Canvas) {
        DrawScope.m5665drawRectnJ9OG0$default($this$Canvas, j, 0L, 0L, Scrim_3J_VO9M$lambda$0($alpha$delegate), null, null, 0, 118, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0491  */
    /* JADX WARN: Code duplicated, block: B:103:0x04a3  */
    /* JADX WARN: Code duplicated, block: B:104:0x04a6  */
    /* JADX WARN: Code duplicated, block: B:108:0x0530  */
    /* JADX WARN: Code duplicated, block: B:66:0x02b0  */
    /* JADX WARN: Code duplicated, block: B:69:0x02bc  */
    /* JADX WARN: Code duplicated, block: B:70:0x02c2  */
    /* JADX WARN: Code duplicated, block: B:94:0x044e  */
    /* JADX WARN: Code duplicated, block: B:97:0x045a  */
    /* JADX WARN: Code duplicated, block: B:98:0x0460  */
    private static final void BackLayerTransition(final BackdropValue target, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        int $dirty;
        boolean invalid$iv;
        Object value$iv;
        boolean invalid$iv2;
        Object value$iv2;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function1;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        boolean invalid$iv3;
        Object it$iv;
        boolean invalid$iv4;
        Object it$iv2;
        int compositeKeyHash$iv$iv2;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function4;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        Composer $composer$iv;
        Composer $composer3 = $composer.startRestartGroup(2129748230);
        ComposerKt.sourceInformation($composer3, "C(BackLayerTransition)N(target,appBar,content)523@23139L133,527@23322L7,529@23368L1451:BackdropScaffold.kt#jmzs0o");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(target.ordinal()) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 256 : 128;
        }
        int $dirty3 = $dirty2;
        if (!$composer3.shouldExecute(($dirty3 & 147) != 146, $dirty3 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2129748230, $dirty3, -1, "androidx.compose.material.BackLayerTransition (BackdropScaffold.kt:519)");
            }
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(target == BackdropValue.Revealed ? 0.0f : 2.0f, new TweenSpec(0, 0, null, 7, null), 0.0f, null, null, $composer3, 48, 28);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density $this$BackLayerTransition_u24lambda_u241 = (Density) objConsume;
            final float animationSlideOffset = $this$BackLayerTransition_u24lambda_u241.mo406toPx0680j_4(AnimationSlideOffset);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Modifier modifier$iv = Modifier.INSTANCE;
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            $composer2 = $composer3;
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor3;
                $composer3.createNode(function0);
            } else {
                function0 = constructor3;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                $dirty = $dirty3;
            } else {
                $dirty = $dirty3;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i2 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -287273344, "C531@23415L351,538@23798L218,530@23382L677,548@24163L353,555@24548L221,546@24068L745:BackdropScaffold.kt#jmzs0o");
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 1237659083, "CC(remember):BackdropScaffold.kt#9igjgp");
                invalid$iv = $composer3.changed(stateAnimateFloatAsState);
                value$iv = $composer3.rememberedValue();
                if (!invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = new Function3() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return BackdropScaffoldKt.BackLayerTransition$lambda$2$0$0(stateAnimateFloatAsState, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierLayout = LayoutModifierKt.layout(companion, (Function3) value$iv);
                ComposerKt.sourceInformationMarkerStart($composer3, 1237671206, "CC(remember):BackdropScaffold.kt#9igjgp");
                invalid$iv2 = $composer3.changed(stateAnimateFloatAsState) | $composer3.changed(animationSlideOffset);
                value$iv2 = $composer3.rememberedValue();
                if (!invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = new Function1() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return BackdropScaffoldKt.BackLayerTransition$lambda$2$1$0(animationSlideOffset, stateAnimateFloatAsState, (GraphicsLayerScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifier$iv2 = GraphicsLayerModifierKt.graphicsLayer(modifierLayout, (Function1) value$iv2);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                int $changed$iv$iv2 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
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
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i4 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 327736681, "C544@24041L8:BackdropScaffold.kt#jmzs0o");
                function2.invoke($composer3, Integer.valueOf(($dirty >> 3) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier.Companion companion2 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 1237683021, "CC(remember):BackdropScaffold.kt#9igjgp");
                invalid$iv3 = $composer3.changed(stateAnimateFloatAsState);
                it$iv = $composer3.rememberedValue();
                if (!invalid$iv3 || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = new Function3() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return BackdropScaffoldKt.BackLayerTransition$lambda$2$3$0(stateAnimateFloatAsState, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv3);
                    it$iv = value$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierLayout2 = LayoutModifierKt.layout(companion2, (Function3) it$iv);
                ComposerKt.sourceInformationMarkerStart($composer3, 1237695209, "CC(remember):BackdropScaffold.kt#9igjgp");
                invalid$iv4 = $composer3.changed(stateAnimateFloatAsState) | $composer3.changed(animationSlideOffset);
                it$iv2 = $composer3.rememberedValue();
                if (!invalid$iv4 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv4 = new Function1() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return BackdropScaffoldKt.BackLayerTransition$lambda$2$4$0(animationSlideOffset, stateAnimateFloatAsState, (GraphicsLayerScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv4);
                    it$iv2 = value$iv4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifier$iv3 = GraphicsLayerModifierKt.graphicsLayer(modifierLayout2, (Function1) it$iv2);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                int $changed$iv$iv3 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function4 = constructor2;
                    $composer3.createNode(function4);
                } else {
                    function4 = constructor2;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                    $composer$iv = $composer3;
                    if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5 = ($changed$iv$iv$iv3 >> 6) & 14;
                    Composer $composer$iv2 = $composer$iv;
                    ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    int i6 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv2, -786845391, "C561@24794L9:BackdropScaffold.kt#jmzs0o");
                    function3.invoke($composer$iv2, Integer.valueOf(($dirty >> 6) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    $composer$iv.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer$iv = $composer3;
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                int i7 = ($changed$iv$iv$iv3 >> 6) & 14;
                Composer $composer$iv3 = $composer$iv;
                ComposerKt.sourceInformationMarkerStart($composer$iv3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                int i8 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv3, -786845391, "C561@24794L9:BackdropScaffold.kt#jmzs0o");
                function3.invoke($composer$iv3, Integer.valueOf(($dirty >> 6) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i9 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
            int i10 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -287273344, "C531@23415L351,538@23798L218,530@23382L677,548@24163L353,555@24548L221,546@24068L745:BackdropScaffold.kt#jmzs0o");
            Modifier.Companion companion3 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 1237659083, "CC(remember):BackdropScaffold.kt#9igjgp");
            invalid$iv = $composer3.changed(stateAnimateFloatAsState);
            value$iv = $composer3.rememberedValue();
            if (!invalid$iv) {
            }
            value$iv = new Function3() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return BackdropScaffoldKt.BackLayerTransition$lambda$2$0$0(stateAnimateFloatAsState, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                }
            };
            $composer3.updateRememberedValue(value$iv);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierLayout3 = LayoutModifierKt.layout(companion3, (Function3) value$iv);
            ComposerKt.sourceInformationMarkerStart($composer3, 1237671206, "CC(remember):BackdropScaffold.kt#9igjgp");
            invalid$iv2 = $composer3.changed(stateAnimateFloatAsState) | $composer3.changed(animationSlideOffset);
            value$iv2 = $composer3.rememberedValue();
            if (!invalid$iv2) {
            }
            value$iv2 = new Function1() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return BackdropScaffoldKt.BackLayerTransition$lambda$2$1$0(animationSlideOffset, stateAnimateFloatAsState, (GraphicsLayerScope) obj);
                }
            };
            $composer3.updateRememberedValue(value$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier$iv4 = GraphicsLayerModifierKt.graphicsLayer(modifierLayout3, (Function1) value$iv2);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
            int $changed$iv$iv4 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer3, modifier$iv4);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
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
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash4);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
            int i11 = ($changed$iv$iv$iv4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
            int i12 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 327736681, "C544@24041L8:BackdropScaffold.kt#jmzs0o");
            function2.invoke($composer3, Integer.valueOf(($dirty >> 3) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier.Companion companion4 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 1237683021, "CC(remember):BackdropScaffold.kt#9igjgp");
            invalid$iv3 = $composer3.changed(stateAnimateFloatAsState);
            it$iv = $composer3.rememberedValue();
            if (!invalid$iv3) {
            }
            Object value$iv5 = new Function3() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return BackdropScaffoldKt.BackLayerTransition$lambda$2$3$0(stateAnimateFloatAsState, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                }
            };
            $composer3.updateRememberedValue(value$iv5);
            it$iv = value$iv5;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierLayout4 = LayoutModifierKt.layout(companion4, (Function3) it$iv);
            ComposerKt.sourceInformationMarkerStart($composer3, 1237695209, "CC(remember):BackdropScaffold.kt#9igjgp");
            invalid$iv4 = $composer3.changed(stateAnimateFloatAsState) | $composer3.changed(animationSlideOffset);
            it$iv2 = $composer3.rememberedValue();
            if (!invalid$iv4) {
            }
            Object value$iv6 = new Function1() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return BackdropScaffoldKt.BackLayerTransition$lambda$2$4$0(animationSlideOffset, stateAnimateFloatAsState, (GraphicsLayerScope) obj);
                }
            };
            $composer3.updateRememberedValue(value$iv6);
            it$iv2 = value$iv6;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier$iv5 = GraphicsLayerModifierKt.graphicsLayer(modifierLayout4, (Function1) it$iv2);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv5 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv5 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv5, false);
            int $changed$iv$iv5 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv5 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer3, modifier$iv5);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv5 = (($changed$iv$iv5 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function4 = constructor2;
                $composer3.createNode(function4);
            } else {
                function4 = constructor2;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                $composer$iv = $composer3;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
                int i13 = ($changed$iv$iv$iv5 >> 6) & 14;
                Composer $composer$iv4 = $composer$iv;
                ComposerKt.sourceInformationMarkerStart($composer$iv4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                int i14 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv4, -786845391, "C561@24794L9:BackdropScaffold.kt#jmzs0o");
                function3.invoke($composer$iv4, Integer.valueOf(($dirty >> 6) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer$iv4);
                ComposerKt.sourceInformationMarkerEnd($composer$iv4);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer$iv = $composer3;
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash5);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
            int i15 = ($changed$iv$iv$iv5 >> 6) & 14;
            Composer $composer$iv5 = $composer$iv;
            ComposerKt.sourceInformationMarkerStart($composer$iv5, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
            int i16 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer$iv5, -786845391, "C561@24794L9:BackdropScaffold.kt#jmzs0o");
            function3.invoke($composer$iv5, Integer.valueOf(($dirty >> 6) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer$iv5);
            ComposerKt.sourceInformationMarkerEnd($composer$iv5);
            $composer$iv.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BackdropScaffoldKt.BackLayerTransition$lambda$3(target, function2, function3, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float BackLayerTransition$lambda$0(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult BackLayerTransition$lambda$2$0$0(State $animationProgress$delegate, MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        float $this$fastCoerceIn$iv = BackLayerTransition$lambda$0($animationProgress$delegate) - 1.0f;
        final float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
            $this$fastCoerceAtLeast$iv$iv = 1.0f;
        }
        final Placeable placeable = measurable.mo6541measureBRTryo0(constraints.getValue());
        return MeasureScope.layout$default($this$layout, placeable.getWidth(), placeable.getHeight(), null, new Function1() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BackdropScaffoldKt.BackLayerTransition$lambda$2$0$0$0(placeable, $this$fastCoerceAtLeast$iv$iv, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BackLayerTransition$lambda$2$0$0$0(Placeable $placeable, float $appBarFloat, Placeable.PlacementScope $this$layout) {
        $this$layout.place($placeable, 0, 0, $appBarFloat);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BackLayerTransition$lambda$2$1$0(float $animationSlideOffset, State $animationProgress$delegate, GraphicsLayerScope $this$graphicsLayer) {
        float $this$fastCoerceIn$iv = BackLayerTransition$lambda$0($animationProgress$delegate) - 1.0f;
        float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
            $this$fastCoerceAtLeast$iv$iv = 1.0f;
        }
        $this$graphicsLayer.setAlpha($this$fastCoerceAtLeast$iv$iv);
        $this$graphicsLayer.setTranslationY((1.0f - $this$fastCoerceAtLeast$iv$iv) * $animationSlideOffset);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult BackLayerTransition$lambda$2$3$0(State $animationProgress$delegate, MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        float $this$fastCoerceIn$iv = 1.0f - BackLayerTransition$lambda$0($animationProgress$delegate);
        final float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
            $this$fastCoerceAtLeast$iv$iv = 1.0f;
        }
        final Placeable placeable = measurable.mo6541measureBRTryo0(constraints.getValue());
        return MeasureScope.layout$default($this$layout, placeable.getWidth(), placeable.getHeight(), null, new Function1() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BackdropScaffoldKt.BackLayerTransition$lambda$2$3$0$0(placeable, $this$fastCoerceAtLeast$iv$iv, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BackLayerTransition$lambda$2$3$0$0(Placeable $placeable, float $contentFloat, Placeable.PlacementScope $this$layout) {
        $this$layout.place($placeable, 0, 0, $contentFloat);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BackLayerTransition$lambda$2$4$0(float $animationSlideOffset, State $animationProgress$delegate, GraphicsLayerScope $this$graphicsLayer) {
        float $this$fastCoerceIn$iv = 1.0f - BackLayerTransition$lambda$0($animationProgress$delegate);
        float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
            $this$fastCoerceAtLeast$iv$iv = 1.0f;
        }
        $this$graphicsLayer.setAlpha($this$fastCoerceAtLeast$iv$iv);
        $this$graphicsLayer.setTranslationY((1.0f - $this$fastCoerceAtLeast$iv$iv) * $animationSlideOffset);
        return Unit.INSTANCE;
    }

    private static final void BackdropStack(final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final Function1<? super Constraints, Constraints> function1, final Function4<? super Constraints, ? super Float, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(1490100844);
        ComposerKt.sourceInformation($composer2, "C(BackdropStack)N(modifier,backLayer,calculateBackLayerConstraints,frontLayer)574@25128L896,574@25101L923:BackdropScaffold.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? 2048 : 1024;
        }
        if (!$composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1490100844, $dirty, -1, "androidx.compose.material.BackdropStack (BackdropScaffold.kt:573)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1762810092, "CC(remember):BackdropScaffold.kt#9igjgp");
            boolean invalid$iv = (($dirty & 112) == 32) | (($dirty & 896) == 256) | (($dirty & 7168) == 2048);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function2() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return BackdropScaffoldKt.BackdropStack$lambda$0$0(function2, function1, function4, (SubcomposeMeasureScope) obj, (Constraints) obj2);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SubcomposeLayoutKt.SubcomposeLayout(modifier, (Function2) it$iv, $composer2, $dirty & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BackdropScaffoldKt.BackdropStack$lambda$1(modifier, function2, function1, function4, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult BackdropStack$lambda$0$0(Function2 $backLayer, Function1 $calculateBackLayerConstraints, final Function4 $frontLayer, SubcomposeMeasureScope $this$SubcomposeLayout, final Constraints constraints) {
        final Placeable backLayerPlaceable = ((Measurable) CollectionsKt.first((List) $this$SubcomposeLayout.subcompose(BackdropLayers.Back, $backLayer))).mo6541measureBRTryo0(((Constraints) $calculateBackLayerConstraints.invoke(constraints)).getValue());
        final float backLayerHeight = backLayerPlaceable.getHeight();
        List<Measurable> listSubcompose = $this$SubcomposeLayout.subcompose(BackdropLayers.Front, ComposableLambdaKt.composableLambdaInstance(-344317043, true, new Function2() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return BackdropScaffoldKt.BackdropStack$lambda$0$0$0($frontLayer, constraints, backLayerHeight, (Composer) obj, ((Integer) obj2).intValue());
            }
        }));
        List target$iv = new ArrayList(listSubcompose.size());
        int size = listSubcompose.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = listSubcompose.get(index$iv$iv);
            target$iv.add(((Measurable) item$iv$iv).mo6541measureBRTryo0(constraints.getValue()));
        }
        final List placeables = target$iv;
        int maxWidth = Math.max(Constraints.m7860getMinWidthimpl(constraints.getValue()), backLayerPlaceable.getWidth());
        int maxHeight = Math.max(Constraints.m7859getMinHeightimpl(constraints.getValue()), backLayerPlaceable.getHeight());
        int size2 = placeables.size();
        int maxWidth2 = maxWidth;
        int maxHeight2 = maxHeight;
        for (int index$iv = 0; index$iv < size2; index$iv++) {
            Object item$iv = placeables.get(index$iv);
            Placeable it = (Placeable) item$iv;
            maxWidth2 = Math.max(maxWidth2, it.getWidth());
            maxHeight2 = Math.max(maxHeight2, it.getHeight());
        }
        return MeasureScope.layout$default($this$SubcomposeLayout, maxWidth2, maxHeight2, null, new Function1() { // from class: androidx.compose.material.BackdropScaffoldKt$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BackdropScaffoldKt.BackdropStack$lambda$0$0$3(backLayerPlaceable, placeables, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BackdropStack$lambda$0$0$0(Function4 $frontLayer, Constraints $constraints, float $backLayerHeight, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C583@25467L40:BackdropScaffold.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-344317043, $changed, -1, "androidx.compose.material.BackdropStack.<anonymous>.<anonymous>.<anonymous> (BackdropScaffold.kt:583)");
            }
            $frontLayer.invoke($constraints, Float.valueOf($backLayerHeight), $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BackdropStack$lambda$0$0$3(Placeable $backLayerPlaceable, List $placeables, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelative$default($this$layout, $backLayerPlaceable, 0, 0, 0.0f, 4, null);
        int size = $placeables.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $placeables.get(index$iv);
            Placeable it = (Placeable) item$iv;
            Placeable.PlacementScope.placeRelative$default($this$layout, it, 0, 0, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material.BackdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1, reason: invalid class name */
    /* JADX INFO: compiled from: BackdropScaffold.kt */
    @Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ'\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000eH\u0096@¢\u0006\u0004\b\u000f\u0010\u0010J \u0010\u0011\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000eH\u0096@¢\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0014\u001a\u00020\u0003*\u00020\u0015H\u0002¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u0015*\u00020\u000eH\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u0017\u001a\u00020\u0015*\u00020\u0003H\u0003¢\u0006\u0004\b\u001a\u0010\u0019¨\u0006\u001b"}, d2 = {"androidx/compose/material/BackdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "onPreScroll", "Landroidx/compose/ui/geometry/Offset;", "available", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPreScroll-OzD1aCk", "(JI)J", "onPostScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostFling", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toOffset", "", "(F)J", "toFloat", "velocityToFloat", "(J)F", "offsetToFloat", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AnonymousClass1 implements NestedScrollConnection {
        final /* synthetic */ Orientation $orientation;
        final /* synthetic */ AnchoredDraggableState<?> $state;

        AnonymousClass1(AnchoredDraggableState<?> anchoredDraggableState, Orientation $orientation) {
            this.$state = anchoredDraggableState;
            this.$orientation = $orientation;
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
        public long mo897onPreScrollOzD1aCk(long available, int source) {
            float delta = offsetToFloat(available);
            if (delta < 0.0f && NestedScrollSource.m6278equalsimpl0(source, NestedScrollSource.INSTANCE.m6290getUserInputWNlRxjI())) {
                return toOffset(this.$state.dispatchRawDelta(delta));
            }
            return Offset.INSTANCE.m4868getZeroF1C5BW0();
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
        public long mo574onPostScrollDzOQY0M(long consumed, long available, int source) {
            if (NestedScrollSource.m6278equalsimpl0(source, NestedScrollSource.INSTANCE.m6290getUserInputWNlRxjI())) {
                return toOffset(this.$state.dispatchRawDelta(offsetToFloat(available)));
            }
            return Offset.INSTANCE.m4868getZeroF1C5BW0();
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPreFling-QWom1Mo */
        public Object mo896onPreFlingQWom1Mo(long j, Continuation<? super Velocity> continuation) {
            BackdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1 backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1;
            if (continuation instanceof BackdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1) {
                backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1 = (BackdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1) continuation;
                if ((backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1.label & Integer.MIN_VALUE) != 0) {
                    backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1.label -= Integer.MIN_VALUE;
                } else {
                    backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1 = new BackdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1(this, continuation);
                }
            } else {
                backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1 = new BackdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1(this, continuation);
            }
            Object $result = backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    float toFling = velocityToFloat(j);
                    float currentOffset = this.$state.requireOffset();
                    if (toFling < 0.0f && currentOffset > this.$state.getAnchors().minAnchor()) {
                        AnchoredDraggableState<?> anchoredDraggableState = this.$state;
                        backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1.J$0 = j;
                        backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1.label = 1;
                        if (anchoredDraggableState.settle(toFling, backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        j = Velocity.INSTANCE.m8154getZero9UxMQ8M();
                    }
                    break;
                case 1:
                    j = backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPreFling$1.J$0;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Velocity.m8134boximpl(j);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
        public Object mo573onPostFlingRZ2iAVY(long j, long j2, Continuation<? super Velocity> continuation) {
            BackdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1 backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1;
            if (continuation instanceof BackdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1) {
                backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1 = (BackdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1) continuation;
                if ((backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1.label & Integer.MIN_VALUE) != 0) {
                    backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1.label -= Integer.MIN_VALUE;
                } else {
                    backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1 = new BackdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1(this, continuation);
                }
            } else {
                backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1 = new BackdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1(this, continuation);
            }
            Object $result = backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    AnchoredDraggableState<?> anchoredDraggableState = this.$state;
                    float fVelocityToFloat = velocityToFloat(j2);
                    backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1.J$0 = j2;
                    backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1.label = 1;
                    if (anchoredDraggableState.settle(fVelocityToFloat, backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    j2 = backdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1.J$0;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Velocity.m8134boximpl(j2);
        }

        private final long toOffset(float $this$toOffset) {
            float x$iv = this.$orientation == Orientation.Horizontal ? $this$toOffset : 0.0f;
            float y$iv = this.$orientation == Orientation.Vertical ? $this$toOffset : 0.0f;
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(y$iv);
            return Offset.m4844constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
        }

        private final float velocityToFloat(long $this$toFloat) {
            return this.$orientation == Orientation.Horizontal ? Velocity.m8143getXimpl($this$toFloat) : Velocity.m8144getYimpl($this$toFloat);
        }

        private final float offsetToFloat(long $this$toFloat) {
            if (this.$orientation == Orientation.Horizontal) {
                int bits$iv$iv$iv = (int) ($this$toFloat >> 32);
                return Float.intBitsToFloat(bits$iv$iv$iv);
            }
            int bits$iv$iv$iv2 = (int) (4294967295L & $this$toFloat);
            return Float.intBitsToFloat(bits$iv$iv$iv2);
        }
    }

    public static final NestedScrollConnection ConsumeSwipeNestedScrollConnection(AnchoredDraggableState<?> anchoredDraggableState, Orientation orientation) {
        return new AnonymousClass1(anchoredDraggableState, orientation);
    }
}
