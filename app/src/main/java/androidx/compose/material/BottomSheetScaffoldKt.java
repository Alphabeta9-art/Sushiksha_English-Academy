package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.CornerBasedShape;
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
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.Velocity;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BottomSheetScaffold.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a;\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\bH\u0007¢\u0006\u0002\u0010\n\u001a!\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00012\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010\u0010\u001aý\u0001\u0010\u0011\u001a\u00020\u00122\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00120\b¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\f2\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u001b¢\u0006\u0002\b\u00152\u0019\b\u0002\u0010\u001c\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00120\b¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u001b¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\t2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020&2\b\b\u0002\u0010(\u001a\u00020$2\b\b\u0002\u0010)\u001a\u00020&2\b\b\u0002\u0010*\u001a\u00020&2\u0017\u0010+\u001a\u0013\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u00120\b¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0004\b-\u0010.\u001ao\u0010/\u001a\u00020\u00122\u0006\u00100\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020$2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u001c\u0010+\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00120\b¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0003¢\u0006\u0004\b1\u00102\u001a\u0098\u0001\u00103\u001a\u00020\u00122\u0013\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u001b¢\u0006\u0002\b\u00152\u0011\u00104\u001a\r\u0012\u0004\u0012\u00020\u00120\u001b¢\u0006\u0002\b\u00152\u0011\u00105\u001a\r\u0012\u0004\u0012\u00020\u00120\u001b¢\u0006\u0002\b\u00152\u0013\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u001b¢\u0006\u0002\b\u00152\u0011\u0010\u001c\u001a\r\u0012\u0004\u0012\u00020\u00120\u001b¢\u0006\u0002\b\u00152\u0006\u0010(\u001a\u00020$2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00060\u001b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00107\u001a\u00020\u0001H\u0003¢\u0006\u0004\b8\u00109\u001a\u001c\u0010:\u001a\u00020;2\n\u00100\u001a\u0006\u0012\u0002\b\u00030<2\u0006\u0010=\u001a\u00020>H\u0002\"\u0010\u0010?\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010@\"\u0010\u0010A\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010@\"\u0010\u0010B\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010@¨\u0006C"}, d2 = {"rememberBottomSheetState", "Landroidx/compose/material/BottomSheetState;", "initialValue", "Landroidx/compose/material/BottomSheetValue;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmStateChange", "Lkotlin/Function1;", "", "(Landroidx/compose/material/BottomSheetValue;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BottomSheetState;", "rememberBottomSheetScaffoldState", "Landroidx/compose/material/BottomSheetScaffoldState;", "bottomSheetState", "snackbarHostState", "Landroidx/compose/material/SnackbarHostState;", "(Landroidx/compose/material/BottomSheetState;Landroidx/compose/material/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BottomSheetScaffoldState;", "BottomSheetScaffold", "", "sheetContent", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "scaffoldState", "topBar", "Lkotlin/Function0;", "snackbarHost", "floatingActionButton", "floatingActionButtonPosition", "Landroidx/compose/material/FabPosition;", "sheetGesturesEnabled", "sheetShape", "Landroidx/compose/ui/graphics/Shape;", "sheetElevation", "Landroidx/compose/ui/unit/Dp;", "sheetBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "sheetContentColor", "sheetPeekHeight", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "contentColor", UriUtil.LOCAL_CONTENT_SCHEME, "Landroidx/compose/foundation/layout/PaddingValues;", "BottomSheetScaffold-HnlDQGw", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material/BottomSheetScaffoldState;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;IZLandroidx/compose/ui/graphics/Shape;FJJFJJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "BottomSheet", "state", "BottomSheet-dAqlCkY", "(Landroidx/compose/material/BottomSheetState;ZLandroidx/compose/ui/graphics/Shape;FJJFLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomSheetScaffoldLayout", TtmlNode.TAG_BODY, "bottomSheet", "sheetOffset", "sheetState", "BottomSheetScaffoldLayout-HJHHjMs", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;FLkotlin/jvm/functions/Function0;ILandroidx/compose/material/BottomSheetState;Landroidx/compose/runtime/Composer;I)V", "ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "Landroidx/compose/material/AnchoredDraggableState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "FabSpacing", "F", "BottomSheetScaffoldPositionalThreshold", "BottomSheetScaffoldVelocityThreshold", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BottomSheetScaffoldKt {
    private static final float FabSpacing = Dp.m7905constructorimpl(16);
    private static final float BottomSheetScaffoldPositionalThreshold = Dp.m7905constructorimpl(56);
    private static final float BottomSheetScaffoldVelocityThreshold = Dp.m7905constructorimpl(ComposerImplKt.nodeKey);

    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BottomSheetValue.values().length];
            try {
                iArr[BottomSheetValue.Collapsed.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BottomSheetValue.Expanded.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit BottomSheetScaffoldLayout_HJHHjMs$lambda$1(Function2 function2, Function2 function3, Function2 function4, Function2 function5, Function2 function6, float f, Function0 function0, int i, BottomSheetState bottomSheetState, int i2, Composer composer, int i3) {
        m1726BottomSheetScaffoldLayoutHJHHjMs(function2, function3, function4, function5, function6, f, function0, i, bottomSheetState, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    static final Unit BottomSheetScaffold_HnlDQGw$lambda$1(Function3 function3, Modifier modifier, BottomSheetScaffoldState bottomSheetScaffoldState, Function2 function2, Function3 function4, Function2 function5, int i, boolean z, Shape shape, float f, long j, long j2, float f2, long j3, long j4, Function3 function6, int i2, int i3, int i4, Composer composer, int i5) {
        m1725BottomSheetScaffoldHnlDQGw(function3, modifier, bottomSheetScaffoldState, function2, function4, function5, i, z, shape, f, j, j2, f2, j3, j4, function6, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit BottomSheet_dAqlCkY$lambda$4(BottomSheetState bottomSheetState, boolean z, Shape shape, float f, long j, long j2, float f2, Modifier modifier, Function3 function3, int i, int i2, Composer composer, int i3) {
        m1724BottomSheetdAqlCkY(bottomSheetState, z, shape, f, j, j2, f2, modifier, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rememberBottomSheetState$lambda$0$0(BottomSheetValue it) {
        return true;
    }

    public static final BottomSheetState rememberBottomSheetState(final BottomSheetValue initialValue, final AnimationSpec<Float> animationSpec, final Function1<? super BottomSheetValue, Boolean> function1, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1808153344, "C(rememberBottomSheetState)N(initialValue,animationSpec,confirmStateChange)223@8800L8,225@8863L7,234@9135L211,226@8882L464:BottomSheetScaffold.kt#jmzs0o");
        if ((i & 2) != 0) {
            animationSpec = BottomSheetScaffoldDefaults.INSTANCE.getAnimationSpec();
        }
        if ((i & 4) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, -1826384664, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material.BottomSheetScaffoldKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(BottomSheetScaffoldKt.rememberBottomSheetState$lambda$0$0((BottomSheetValue) obj));
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            function1 = (Function1) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1808153344, $changed, -1, "androidx.compose.material.rememberBottomSheetState (BottomSheetScaffold.kt:224)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Density density = (Density) objConsume;
        Object[] objArr = {animationSpec};
        Saver<BottomSheetState, ?> Saver = BottomSheetState.INSTANCE.Saver(animationSpec, function1, density);
        ComposerKt.sourceInformationMarkerStart($composer, -1826373741, "CC(remember):BottomSheetScaffold.kt#9igjgp");
        boolean z = true;
        boolean zChanged = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialValue.ordinal())) || ($changed & 6) == 4) | $composer.changed(density) | $composer.changedInstance(animationSpec);
        if (((($changed & 896) ^ 384) <= 256 || !$composer.changed(function1)) && ($changed & 384) != 256) {
            z = false;
        }
        boolean invalid$iv = zChanged | z;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function0() { // from class: androidx.compose.material.BottomSheetScaffoldKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BottomSheetScaffoldKt.rememberBottomSheetState$lambda$1$0(initialValue, density, animationSpec, function1);
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        BottomSheetState bottomSheetState = (BottomSheetState) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) Saver, (Function0) it$iv2, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return bottomSheetState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BottomSheetState rememberBottomSheetState$lambda$1$0(BottomSheetValue $initialValue, Density $density, AnimationSpec $animationSpec, Function1 $confirmStateChange) {
        return new BottomSheetState($initialValue, $density, $animationSpec, $confirmStateChange);
    }

    public static final BottomSheetScaffoldState rememberBottomSheetScaffoldState(BottomSheetState bottomSheetState, SnackbarHostState snackbarHostState, Composer $composer, int $changed, int i) {
        Composer $composer2;
        ComposerKt.sourceInformationMarkerStart($composer, -1022285988, "C(rememberBottomSheetScaffoldState)N(bottomSheetState,snackbarHostState)264@10031L35,265@10111L32,267@10186L197:BottomSheetScaffold.kt#jmzs0o");
        if ((i & 1) != 0) {
            $composer2 = $composer;
            bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed, null, null, $composer2, 6, 6);
        } else {
            $composer2 = $composer;
        }
        if ((i & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer2, 1130681436, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            Composer $this$cache$iv = $composer2;
            Object it$iv = $this$cache$iv.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new SnackbarHostState();
                $this$cache$iv.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            snackbarHostState = (SnackbarHostState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1022285988, $changed, -1, "androidx.compose.material.rememberBottomSheetScaffoldState (BottomSheetScaffold.kt:266)");
        }
        ComposerKt.sourceInformationMarkerStart($composer2, 1130684001, "CC(remember):BottomSheetScaffold.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer2.changed(bottomSheetState)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer2.changed(snackbarHostState)) || ($changed & 48) == 32);
        Composer $this$cache$iv2 = $composer2;
        Object it$iv2 = $this$cache$iv2.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new BottomSheetScaffoldState(bottomSheetState, snackbarHostState);
            $this$cache$iv2.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        BottomSheetScaffoldState bottomSheetScaffoldState = (BottomSheetScaffoldState) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer2);
        return bottomSheetScaffoldState;
    }

    /* JADX INFO: renamed from: BottomSheetScaffold-HnlDQGw, reason: not valid java name */
    public static final void m1725BottomSheetScaffoldHnlDQGw(final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, BottomSheetScaffoldState scaffoldState, Function2<? super Composer, ? super Integer, Unit> function2, Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, int i, boolean sheetGesturesEnabled, Shape sheetShape, float f, long j, long j2, float f2, long j3, long j4, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function6, Composer $composer, final int $changed, final int $changed1, final int i2) {
        Modifier modifier2;
        BottomSheetScaffoldState scaffoldState2;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> lambda$937349512$material;
        Function2<? super Composer, ? super Integer, Unit> function8;
        int iM1834getEnd5ygKITE;
        boolean sheetGesturesEnabled2;
        int $dirty1;
        int $dirty2;
        Composer $composer2;
        final Shape sheetShape2;
        final long j5;
        final long j6;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function10;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final int i3;
        final Modifier modifier3;
        final BottomSheetScaffoldState scaffoldState3;
        final boolean sheetGesturesEnabled3;
        final float f3;
        final long j7;
        final long j8;
        final float f4;
        Function2<? super Composer, ? super Integer, Unit> function12;
        CornerBasedShape sheetShape3;
        float fM1719getSheetElevationD9Ej5fM;
        long jM1769getSurface0d7_KjU;
        long jM1783contentColorForek8zF_U;
        int $dirty3;
        float fM1720getSheetPeekHeightD9Ej5fM;
        long jM1758getBackground0d7_KjU;
        long j9;
        long j10;
        final Shape shape;
        final float f5;
        int $dirty4;
        Composer $composer3 = $composer.startRestartGroup(194495313);
        ComposerKt.sourceInformation($composer3, "C(BottomSheetScaffold)N(sheetContent,modifier,scaffoldState,topBar,snackbarHost,floatingActionButton,floatingActionButtonPosition:c#material.FabPosition,sheetGesturesEnabled,sheetShape,sheetElevation:c#ui.unit.Dp,sheetBackgroundColor:c#ui.graphics.Color,sheetContentColor:c#ui.graphics.Color,sheetPeekHeight:c#ui.unit.Dp,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,content)337@14316L1792,337@14230L1878:BottomSheetScaffold.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 4 : 2;
        }
        int i4 = i2 & 2;
        if (i4 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i2 & 4) == 0) {
                scaffoldState2 = scaffoldState;
                int i5 = $composer3.changed(scaffoldState2) ? 256 : 128;
                $dirty |= i5;
            } else {
                scaffoldState2 = scaffoldState;
            }
            $dirty |= i5;
        } else {
            scaffoldState2 = scaffoldState;
        }
        int i6 = i2 & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            function7 = function2;
        } else if (($changed & 3072) == 0) {
            function7 = function2;
            $dirty |= $composer3.changedInstance(function7) ? 2048 : 1024;
        } else {
            function7 = function2;
        }
        int i7 = i2 & 16;
        if (i7 != 0) {
            $dirty |= 24576;
            lambda$937349512$material = function4;
        } else if (($changed & 24576) == 0) {
            lambda$937349512$material = function4;
            $dirty |= $composer3.changedInstance(lambda$937349512$material) ? 16384 : 8192;
        } else {
            lambda$937349512$material = function4;
        }
        int i8 = i2 & 32;
        if (i8 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function8 = function5;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function8 = function5;
            $dirty |= $composer3.changedInstance(function8) ? 131072 : 65536;
        } else {
            function8 = function5;
        }
        int i9 = i2 & 64;
        if (i9 != 0) {
            $dirty |= 1572864;
            iM1834getEnd5ygKITE = i;
        } else if (($changed & 1572864) == 0) {
            iM1834getEnd5ygKITE = i;
            $dirty |= $composer3.changed(iM1834getEnd5ygKITE) ? 1048576 : 524288;
        } else {
            iM1834getEnd5ygKITE = i;
        }
        int i10 = i2 & 128;
        if (i10 != 0) {
            $dirty |= 12582912;
            sheetGesturesEnabled2 = sheetGesturesEnabled;
        } else if (($changed & 12582912) == 0) {
            sheetGesturesEnabled2 = sheetGesturesEnabled;
            $dirty |= $composer3.changed(sheetGesturesEnabled2) ? 8388608 : 4194304;
        } else {
            sheetGesturesEnabled2 = sheetGesturesEnabled;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= ((i2 & 256) == 0 && $composer3.changed(sheetShape)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= ((i2 & 512) == 0 && $composer3.changed(f)) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            if ((i2 & 1024) == 0) {
                $dirty4 = $changed1;
                int i11 = $composer3.changed(j) ? 4 : 2;
                $dirty1 = $dirty4 | i11;
            } else {
                $dirty4 = $changed1;
            }
            $dirty1 = $dirty4 | i11;
        } else {
            $dirty1 = $changed1;
        }
        if (($changed1 & 48) == 0) {
            int $dirty5 = $dirty1;
            int $dirty6 = i2 & 2048;
            $dirty1 = $dirty5 | (($dirty6 == 0 && $composer3.changed(j2)) ? 32 : 16);
        }
        int $dirty7 = $dirty1;
        int $dirty8 = $changed1 & 384;
        if ($dirty8 == 0) {
            $dirty2 = $dirty7 | (((i2 & 4096) == 0 && $composer3.changed(f2)) ? 256 : 128);
        } else {
            $dirty2 = $dirty7;
        }
        if (($changed1 & 3072) == 0) {
            $dirty2 |= ((i2 & 8192) == 0 && $composer3.changed(j3)) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty2 |= ((i2 & 16384) == 0 && $composer3.changed(j4)) ? 16384 : 8192;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer3.changedInstance(function6) ? 131072 : 65536;
        }
        if ($composer3.shouldExecute(((306783379 & $dirty) == 306783378 && ($dirty2 & 74899) == 74898) ? false : true, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "322@13393L34,328@13762L6,330@13893L6,331@13940L37,333@14093L6,334@14138L32");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i2 & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i2 & 256) != 0) {
                    $dirty &= -234881025;
                }
                if ((i2 & 512) != 0) {
                    $dirty &= -1879048193;
                }
                if ((i2 & 1024) != 0) {
                    $dirty2 &= -15;
                }
                if ((i2 & 2048) != 0) {
                    $dirty2 &= -113;
                }
                int $dirty9 = $dirty2;
                if ((i2 & 4096) != 0) {
                    $dirty9 &= -897;
                }
                if ((i2 & 8192) != 0) {
                    $dirty9 &= -7169;
                }
                if ((i2 & 16384) != 0) {
                    $dirty9 &= -57345;
                }
                fM1719getSheetElevationD9Ej5fM = f;
                jM1769getSurface0d7_KjU = j;
                jM1783contentColorForek8zF_U = j2;
                f5 = f2;
                j10 = j3;
                j9 = j4;
                $dirty3 = $dirty9;
                shape = sheetShape;
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 4) != 0) {
                    $dirty &= -897;
                    scaffoldState2 = rememberBottomSheetScaffoldState(null, null, $composer3, 0, 3);
                }
                if (i6 == 0) {
                    function12 = function7;
                } else {
                    function12 = null;
                }
                if (i7 != 0) {
                    lambda$937349512$material = ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$937349512$material();
                }
                if (i8 != 0) {
                    function8 = null;
                }
                if (i9 != 0) {
                    iM1834getEnd5ygKITE = FabPosition.INSTANCE.m1834getEnd5ygKITE();
                }
                if (i10 != 0) {
                    sheetGesturesEnabled2 = true;
                }
                if ((i2 & 256) == 0) {
                    sheetShape3 = sheetShape;
                } else {
                    sheetShape3 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                    $dirty &= -234881025;
                }
                if ((i2 & 512) == 0) {
                    fM1719getSheetElevationD9Ej5fM = f;
                } else {
                    fM1719getSheetElevationD9Ej5fM = BottomSheetScaffoldDefaults.INSTANCE.m1719getSheetElevationD9Ej5fM();
                    $dirty &= -1879048193;
                }
                if ((i2 & 1024) != 0) {
                    $dirty2 &= -15;
                    jM1769getSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer3, 6).m1769getSurface0d7_KjU();
                } else {
                    jM1769getSurface0d7_KjU = j;
                }
                if ((i2 & 2048) == 0) {
                    jM1783contentColorForek8zF_U = j2;
                    $dirty3 = $dirty2;
                } else {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1769getSurface0d7_KjU, $composer3, $dirty2 & 14);
                    $dirty3 = $dirty2 & (-113);
                }
                if ((i2 & 4096) == 0) {
                    fM1720getSheetPeekHeightD9Ej5fM = f2;
                } else {
                    fM1720getSheetPeekHeightD9Ej5fM = BottomSheetScaffoldDefaults.INSTANCE.m1720getSheetPeekHeightD9Ej5fM();
                    $dirty3 &= -897;
                }
                if ((i2 & 8192) != 0) {
                    $dirty3 &= -7169;
                    jM1758getBackground0d7_KjU = MaterialTheme.INSTANCE.getColors($composer3, 6).m1758getBackground0d7_KjU();
                } else {
                    jM1758getBackground0d7_KjU = j3;
                }
                if ((i2 & 16384) == 0) {
                    function7 = function12;
                    j9 = j4;
                    j10 = jM1758getBackground0d7_KjU;
                    shape = sheetShape3;
                    f5 = fM1720getSheetPeekHeightD9Ej5fM;
                } else {
                    long jM1783contentColorForek8zF_U2 = ColorsKt.m1783contentColorForek8zF_U(jM1758getBackground0d7_KjU, $composer3, ($dirty3 >> 9) & 14);
                    $dirty3 &= -57345;
                    function7 = function12;
                    j9 = jM1783contentColorForek8zF_U2;
                    j10 = jM1758getBackground0d7_KjU;
                    shape = sheetShape3;
                    f5 = fM1720getSheetPeekHeightD9Ej5fM;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(194495313, $dirty, $dirty3, "androidx.compose.material.BottomSheetScaffold (BottomSheetScaffold.kt:336)");
            }
            final long j11 = jM1769getSurface0d7_KjU;
            final Function2<? super Composer, ? super Integer, Unit> function13 = function7;
            final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function14 = lambda$937349512$material;
            final float f6 = fM1719getSheetElevationD9Ej5fM;
            final Function2<? super Composer, ? super Integer, Unit> function15 = function8;
            final int i12 = iM1834getEnd5ygKITE;
            final BottomSheetScaffoldState scaffoldState4 = scaffoldState2;
            final boolean sheetGesturesEnabled4 = sheetGesturesEnabled2;
            final long j12 = jM1783contentColorForek8zF_U;
            float f7 = f5;
            Shape sheetShape4 = shape;
            long j13 = j10;
            long j14 = j9;
            SurfaceKt.m1954SurfaceFjzlyU(SizeKt.fillMaxSize$default(modifier2, 0.0f, 1, null), null, j13, j14, null, 0.0f, ComposableLambdaKt.rememberComposableLambda(-747577963, true, new Function2() { // from class: androidx.compose.material.BottomSheetScaffoldKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomSheetScaffoldKt.BottomSheetScaffold_HnlDQGw$lambda$0(scaffoldState4, function13, function15, f5, i12, function6, sheetGesturesEnabled4, shape, f6, j11, j12, function3, function14, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer3, (($dirty3 >> 3) & 896) | 1572864 | (($dirty3 >> 3) & 7168), 50);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            float f8 = fM1719getSheetElevationD9Ej5fM;
            sheetShape2 = sheetShape4;
            function10 = lambda$937349512$material;
            sheetGesturesEnabled3 = sheetGesturesEnabled2;
            f4 = f7;
            function11 = function8;
            f3 = f8;
            function9 = function7;
            i3 = iM1834getEnd5ygKITE;
            modifier3 = modifier2;
            scaffoldState3 = scaffoldState2;
            j7 = j11;
            j8 = j12;
            j5 = j13;
            j6 = j14;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            sheetShape2 = sheetShape;
            j5 = j3;
            j6 = j4;
            function9 = function7;
            function10 = lambda$937349512$material;
            function11 = function8;
            i3 = iM1834getEnd5ygKITE;
            modifier3 = modifier2;
            scaffoldState3 = scaffoldState2;
            sheetGesturesEnabled3 = sheetGesturesEnabled2;
            f3 = f;
            j7 = j;
            j8 = j2;
            f4 = f2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.BottomSheetScaffoldKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomSheetScaffoldKt.BottomSheetScaffold_HnlDQGw$lambda$1(function3, modifier3, scaffoldState3, function9, function10, function11, i3, sheetGesturesEnabled3, sheetShape2, f3, j7, j8, f4, j5, j6, function6, $changed, $changed1, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomSheetScaffold_HnlDQGw$lambda$0$0(Function3 $content, float f, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C340@14403L48:BottomSheetScaffold.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(601061661, $changed, -1, "androidx.compose.material.BottomSheetScaffold.<anonymous>.<anonymous> (BottomSheetScaffold.kt:340)");
            }
            $content.invoke(PaddingKt.m816PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, f, 7, null), $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomSheetScaffold_HnlDQGw$lambda$0$1(boolean $sheetGesturesEnabled, BottomSheetScaffoldState $scaffoldState, float f, Shape $sheetShape, float f2, long j, long j2, Function3 $sheetContent, Composer $composer, int $changed) {
        Modifier.Companion companionNestedScroll$default;
        ComposerKt.sourceInformation($composer, "C353@15111L576:BottomSheetScaffold.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1835125948, $changed, -1, "androidx.compose.material.BottomSheetScaffold.<anonymous>.<anonymous> (BottomSheetScaffold.kt:342)");
            }
            if ($sheetGesturesEnabled) {
                $composer.startReplaceGroup(-401495582);
                ComposerKt.sourceInformation($composer, "345@14641L391");
                Modifier.Companion companion = Modifier.INSTANCE;
                AnchoredDraggableState<BottomSheetValue> anchoredDraggableState$material = $scaffoldState.getBottomSheetState().getAnchoredDraggableState$material();
                ComposerKt.sourceInformationMarkerStart($composer, -1675517117, "CC(remember):BottomSheetScaffold.kt#9igjgp");
                boolean invalid$iv = $composer.changed(anchoredDraggableState$material);
                Object it$iv = $composer.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection($scaffoldState.getBottomSheetState().getAnchoredDraggableState$material(), Orientation.Vertical);
                    $composer.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                companionNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(companion, (NestedScrollConnection) it$iv, null, 2, null);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1675503260);
                $composer.endReplaceGroup();
                companionNestedScroll$default = Modifier.INSTANCE;
            }
            Modifier nestedScroll = companionNestedScroll$default;
            m1724BottomSheetdAqlCkY($scaffoldState.getBottomSheetState(), $sheetGesturesEnabled, $sheetShape, f2, j, j2, f, SizeKt.m856requiredHeightInVpY3zN4$default(SizeKt.fillMaxWidth$default(nestedScroll, 0.0f, 1, null), f, 0.0f, 2, null), $sheetContent, $composer, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomSheetScaffold_HnlDQGw$lambda$0$2(Function3 $snackbarHost, BottomSheetScaffoldState $scaffoldState, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C366@15789L45:BottomSheetScaffold.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(8287226, $changed, -1, "androidx.compose.material.BottomSheetScaffold.<anonymous>.<anonymous> (BottomSheetScaffold.kt:366)");
            }
            $snackbarHost.invoke($scaffoldState.getSnackbarHostState(), $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit BottomSheetScaffold_HnlDQGw$lambda$0(final BottomSheetScaffoldState $scaffoldState, Function2 $topBar, Function2 $floatingActionButton, final float f, int i, final Function3 $content, final boolean $sheetGesturesEnabled, final Shape $sheetShape, final float f2, final long j, final long j2, final Function3 $sheetContent, final Function3 $snackbarHost, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C340@14401L52,341@14481L1220,366@15787L49,369@15968L50,338@14326L1776:BottomSheetScaffold.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-747577963, $changed, -1, "androidx.compose.material.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:338)");
            }
            BottomSheetState bottomSheetState = $scaffoldState.getBottomSheetState();
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(601061661, true, new Function2() { // from class: androidx.compose.material.BottomSheetScaffoldKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomSheetScaffoldKt.BottomSheetScaffold_HnlDQGw$lambda$0$0($content, f, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54);
            ComposableLambda composableLambdaRememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1835125948, true, new Function2() { // from class: androidx.compose.material.BottomSheetScaffoldKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomSheetScaffoldKt.BottomSheetScaffold_HnlDQGw$lambda$0$1($sheetGesturesEnabled, $scaffoldState, f, $sheetShape, f2, j, j2, $sheetContent, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54);
            ComposableLambda composableLambdaRememberComposableLambda3 = ComposableLambdaKt.rememberComposableLambda(8287226, true, new Function2() { // from class: androidx.compose.material.BottomSheetScaffoldKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomSheetScaffoldKt.BottomSheetScaffold_HnlDQGw$lambda$0$2($snackbarHost, $scaffoldState, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54);
            ComposerKt.sourceInformationMarkerStart($composer, -1162831097, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            boolean invalid$iv = $composer.changed($scaffoldState);
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material.BottomSheetScaffoldKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(BottomSheetScaffoldKt.BottomSheetScaffold_HnlDQGw$lambda$0$3$0($scaffoldState));
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            m1726BottomSheetScaffoldLayoutHJHHjMs($topBar, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, $floatingActionButton, composableLambdaRememberComposableLambda3, f, (Function0) it$iv, i, bottomSheetState, $composer, 25008);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float BottomSheetScaffold_HnlDQGw$lambda$0$3$0(BottomSheetScaffoldState $scaffoldState) {
        return $scaffoldState.getBottomSheetState().requireOffset();
    }

    /* JADX INFO: renamed from: BottomSheet-dAqlCkY, reason: not valid java name */
    private static final void m1724BottomSheetdAqlCkY(final BottomSheetState state, final boolean sheetGesturesEnabled, final Shape sheetShape, final float f, final long j, final long j2, final float f2, Modifier modifier, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-426833549);
        ComposerKt.sourceInformation($composer3, "C(BottomSheet)N(state,sheetGesturesEnabled,sheetShape,sheetElevation:c#ui.unit.Dp,sheetBackgroundColor:c#ui.graphics.Color,sheetContentColor:c#ui.graphics.Color,sheetPeekHeight:c#ui.unit.Dp,modifier,content)388@16491L24,389@16557L7,392@16705L816,415@17745L893,440@18802L29,390@16597L2241:BottomSheetScaffold.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(sheetGesturesEnabled) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(sheetShape) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(f) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(j) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changed(j2) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changed(f2) ? 1048576 : 524288;
        }
        int i2 = i & 128;
        if (i2 != 0) {
            $dirty |= 12582912;
            modifier2 = modifier;
        } else if (($changed & 12582912) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 8388608 : 4194304;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ($composer3.shouldExecute(($dirty & 38347923) != 38347922, $dirty & 1)) {
            if (i2 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-426833549, $dirty, -1, "androidx.compose.material.BottomSheet (BottomSheetScaffold.kt:387)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
            int $dirty2 = $dirty;
            ComposerKt.sourceInformationMarkerStart($composer3, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object value$iv$iv = $composer3.rememberedValue();
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3);
                $composer3.updateRememberedValue(value$iv$iv);
            }
            final CoroutineScope scope = (CoroutineScope) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density $this$BottomSheet_dAqlCkY_u24lambda_u240 = (Density) objConsume;
            final float peekHeightPx = $this$BottomSheet_dAqlCkY_u24lambda_u240.mo406toPx0680j_4(f2);
            AnchoredDraggableState<BottomSheetValue> anchoredDraggableState$material = state.getAnchoredDraggableState$material();
            Orientation orientation = Orientation.Vertical;
            ComposerKt.sourceInformationMarkerStart($composer3, 2129837731, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(peekHeightPx) | (($dirty2 & 14) == 4);
            Object value$iv = $composer3.rememberedValue();
            if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function2() { // from class: androidx.compose.material.BottomSheetScaffoldKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return BottomSheetScaffoldKt.BottomSheet_dAqlCkY$lambda$1$0(state, peekHeightPx, (IntSize) obj, (Constraints) obj2);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierDraggableAnchors = AnchoredDraggableKt.draggableAnchors(modifier2, anchoredDraggableState$material, orientation, (Function2) value$iv);
            AnchoredDraggableState<BottomSheetValue> anchoredDraggableState$material2 = state.getAnchoredDraggableState$material();
            Modifier modifierAnchoredDraggable = AnchoredDraggableKt.anchoredDraggable(modifierDraggableAnchors, anchoredDraggableState$material2, Orientation.Vertical, (48 & 4) != 0 ? true : sheetGesturesEnabled, (48 & 8) != 0 ? false : false, (48 & 16) != 0 ? null : null, (48 & 32) != 0 ? anchoredDraggableState$material2.isAnimationRunning() : false);
            ComposerKt.sourceInformationMarkerStart($composer3, 2129871088, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            boolean invalid$iv2 = (($dirty2 & 14) == 4) | $composer3.changedInstance(scope);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material.BottomSheetScaffoldKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return BottomSheetScaffoldKt.BottomSheet_dAqlCkY$lambda$2$0(state, scope, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            SurfaceKt.m1954SurfaceFjzlyU(SemanticsModifierKt.semantics$default(modifierAnchoredDraggable, false, (Function1) it$iv, 1, null), sheetShape, j, j2, null, f, ComposableLambdaKt.rememberComposableLambda(1065607095, true, new Function2() { // from class: androidx.compose.material.BottomSheetScaffoldKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomSheetScaffoldKt.BottomSheet_dAqlCkY$lambda$3(function3, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer2, (($dirty2 >> 3) & 112) | 1572864 | (($dirty2 >> 6) & 896) | (($dirty2 >> 6) & 7168) | (($dirty2 << 6) & 458752), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.BottomSheetScaffoldKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomSheetScaffoldKt.BottomSheet_dAqlCkY$lambda$4(state, sheetGesturesEnabled, sheetShape, f, j, j2, f2, modifier3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair BottomSheet_dAqlCkY$lambda$1$0(BottomSheetState $state, final float $peekHeightPx, IntSize sheetSize, Constraints constraints) {
        BottomSheetValue newTarget;
        final int layoutHeight = Constraints.m7857getMaxHeightimpl(constraints.getValue());
        long arg0$iv = sheetSize.m8080unboximpl();
        final float sheetHeight = (int) (4294967295L & arg0$iv);
        DraggableAnchors newAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material.BottomSheetScaffoldKt$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BottomSheetScaffoldKt.BottomSheet_dAqlCkY$lambda$1$0$0(layoutHeight, $peekHeightPx, sheetHeight, (DraggableAnchorsConfig) obj);
            }
        });
        switch (WhenMappings.$EnumSwitchMapping$0[$state.getAnchoredDraggableState$material().getTargetValue().ordinal()]) {
            case 1:
                newTarget = BottomSheetValue.Collapsed;
                break;
            case 2:
                newTarget = !newAnchors.hasAnchorFor(BottomSheetValue.Expanded) ? BottomSheetValue.Collapsed : BottomSheetValue.Expanded;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return TuplesKt.to(newAnchors, newTarget);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomSheet_dAqlCkY$lambda$1$0$0(int $layoutHeight, float $peekHeightPx, float $sheetHeight, DraggableAnchorsConfig $this$DraggableAnchors) {
        $this$DraggableAnchors.at(BottomSheetValue.Collapsed, $layoutHeight - $peekHeightPx);
        if ($sheetHeight > 0.0f) {
            if (!($sheetHeight == $peekHeightPx)) {
                $this$DraggableAnchors.at(BottomSheetValue.Expanded, $layoutHeight - $sheetHeight);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomSheet_dAqlCkY$lambda$2$0(final BottomSheetState $state, final CoroutineScope $scope, SemanticsPropertyReceiver $this$semantics) {
        if ($state.getAnchoredDraggableState$material().getAnchors().getSize() > 1) {
            if ($state.isCollapsed()) {
                SemanticsPropertiesKt.expand$default($this$semantics, null, new Function0() { // from class: androidx.compose.material.BottomSheetScaffoldKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(BottomSheetScaffoldKt.BottomSheet_dAqlCkY$lambda$2$0$0($state, $scope));
                    }
                }, 1, null);
            } else {
                SemanticsPropertiesKt.collapse$default($this$semantics, null, new Function0() { // from class: androidx.compose.material.BottomSheetScaffoldKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(BottomSheetScaffoldKt.BottomSheet_dAqlCkY$lambda$2$0$1($state, $scope));
                    }
                }, 1, null);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean BottomSheet_dAqlCkY$lambda$2$0$0(BottomSheetState $state, CoroutineScope $scope) {
        if ($state.getAnchoredDraggableState$material().getConfirmValueChange$material().invoke(BottomSheetValue.Expanded).booleanValue()) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new BottomSheetScaffoldKt$BottomSheet$2$1$1$1($state, null), 3, null);
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean BottomSheet_dAqlCkY$lambda$2$0$1(BottomSheetState $state, CoroutineScope $scope) {
        if ($state.getAnchoredDraggableState$material().getConfirmValueChange$material().invoke(BottomSheetValue.Collapsed).booleanValue()) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new BottomSheetScaffoldKt$BottomSheet$2$1$2$1($state, null), 3, null);
            return true;
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0141  */
    static final Unit BottomSheet_dAqlCkY$lambda$3(Function3 $content, Composer $composer, int $changed) {
        Composer $composer$iv;
        int $changed$iv;
        ComposerKt.sourceInformation($composer, "C440@18804L25:BottomSheetScaffold.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1065607095, $changed, -1, "androidx.compose.material.BottomSheet.<anonymous> (BottomSheetScaffold.kt:440)");
            }
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
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
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
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                $composer$iv = $composer;
                $changed$iv = 0;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                Composer $composer$iv2 = $composer$iv;
                ComposerKt.sourceInformationMarkerStart($composer$iv2, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                $content.invoke(ColumnScopeInstance.INSTANCE, $composer$iv2, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer$iv = $composer;
                $changed$iv = 0;
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = ($changed$iv$iv$iv >> 6) & 14;
            Composer $composer$iv3 = $composer$iv;
            ComposerKt.sourceInformationMarkerStart($composer$iv3, -384672921, "C89@4556L9:Column.kt#2w3rfo");
            $content.invoke(ColumnScopeInstance.INSTANCE, $composer$iv3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
            ComposerKt.sourceInformationMarkerEnd($composer$iv3);
            $composer$iv.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: BottomSheetScaffoldLayout-HJHHjMs, reason: not valid java name */
    private static final void m1726BottomSheetScaffoldLayoutHJHHjMs(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final Function2<? super Composer, ? super Integer, Unit> function6, final float f, final Function0<Float> function0, final int i, final BottomSheetState sheetState, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function1;
        Composer $composer2 = $composer.startRestartGroup(757616750);
        ComposerKt.sourceInformation($composer2, "C(BottomSheetScaffoldLayout)N(topBar,body,bottomSheet,floatingActionButton,snackbarHost,sheetPeekHeight:c#ui.unit.Dp,sheetOffset,floatingActionButtonPosition:c#material.FabPosition,sheetState)479@20010L2783,470@19764L3029:BottomSheetScaffold.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function5) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function6) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changed(f) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer2.changed(i) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty |= $composer2.changed(sheetState) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        boolean invalid$iv$iv = true;
        if ($composer2.shouldExecute((38347923 & $dirty) != 38347922, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(757616750, $dirty, -1, "androidx.compose.material.BottomSheetScaffoldLayout (BottomSheetScaffold.kt:469)");
            }
            Function2[] function2Arr = new Function2[5];
            function2Arr[0] = function2 == null ? ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m1788getLambda$505419337$material() : function2;
            function2Arr[1] = function3;
            function2Arr[2] = function4;
            function2Arr[3] = function5 == null ? ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$687232378$material() : function5;
            function2Arr[4] = function6;
            List contents$iv = CollectionsKt.listOf((Object[]) function2Arr);
            ComposerKt.sourceInformationMarkerStart($composer2, -2074720627, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            boolean invalid$iv = ((3670016 & $dirty) == 1048576) | ((29360128 & $dirty) == 8388608) | ((458752 & $dirty) == 131072) | ((234881024 & $dirty) == 67108864);
            BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1 value$iv = $composer2.rememberedValue();
            if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1(function0, i, f, sheetState);
                $composer2.updateRememberedValue(value$iv);
            }
            MultiContentMeasurePolicy measurePolicy$iv = (MultiContentMeasurePolicy) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1399185516, "CC(Layout)P(!1,2)168@6883L62,165@6769L182:Layout.kt#80mrfh");
            Modifier modifier$iv = Modifier.INSTANCE;
            Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(contents$iv);
            ComposerKt.sourceInformationMarkerStart($composer2, -290764973, "CC(remember):Layout.kt#9igjgp");
            if ((((0 & 896) ^ 384) <= 256 || !$composer2.changed(measurePolicy$iv)) && (0 & 384) != 256) {
                invalid$iv$iv = false;
            }
            Object it$iv$iv = $composer2.rememberedValue();
            if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv = MultiContentMeasurePolicyKt.createMeasurePolicy(measurePolicy$iv);
                $composer2.updateRememberedValue(value$iv$iv);
                it$iv$iv = value$iv$iv;
            }
            MeasurePolicy measurePolicy$iv$iv = (MeasurePolicy) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            int $changed$iv$iv = 0 & 112;
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
                function1 = constructor;
                $composer2.createNode(function1);
            } else {
                function1 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            function2CombineAsVirtualLayouts.invoke($composer2, Integer.valueOf(($changed$iv$iv$iv >> 6) & 14));
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.BottomSheetScaffoldKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomSheetScaffoldKt.BottomSheetScaffoldLayout_HJHHjMs$lambda$1(function2, function3, function4, function5, function6, f, function0, i, sheetState, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material.BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1, reason: invalid class name */
    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
    @Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ'\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000eH\u0096@¢\u0006\u0004\b\u000f\u0010\u0010J \u0010\u0011\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000eH\u0096@¢\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0014\u001a\u00020\u0003*\u00020\u0015H\u0002¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u0015*\u00020\u000eH\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u0017\u001a\u00020\u0015*\u00020\u0003H\u0003¢\u0006\u0004\b\u001a\u0010\u0019¨\u0006\u001b"}, d2 = {"androidx/compose/material/BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "onPreScroll", "Landroidx/compose/ui/geometry/Offset;", "available", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPreScroll-OzD1aCk", "(JI)J", "onPostScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostFling", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toOffset", "", "(F)J", "toFloat", "velocityToFloat", "(J)F", "offsetToFloat", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
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
            BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1;
            if (continuation instanceof BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1) {
                bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = (BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1) continuation;
                if ((bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label & Integer.MIN_VALUE) != 0) {
                    bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label -= Integer.MIN_VALUE;
                } else {
                    bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = new BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1(this, continuation);
                }
            } else {
                bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = new BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1(this, continuation);
            }
            Object $result = bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    float toFling = velocityToFloat(j);
                    float currentOffset = this.$state.requireOffset();
                    if (toFling < 0.0f && currentOffset > this.$state.getAnchors().minAnchor()) {
                        AnchoredDraggableState<?> anchoredDraggableState = this.$state;
                        bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.J$0 = j;
                        bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label = 1;
                        if (anchoredDraggableState.settle(toFling, bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        j = Velocity.INSTANCE.m8154getZero9UxMQ8M();
                    }
                    break;
                case 1:
                    j = bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.J$0;
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
            BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1;
            if (continuation instanceof BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) {
                bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = (BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) continuation;
                if ((bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label & Integer.MIN_VALUE) != 0) {
                    bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label -= Integer.MIN_VALUE;
                } else {
                    bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = new BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1(this, continuation);
                }
            } else {
                bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = new BottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1(this, continuation);
            }
            Object $result = bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    AnchoredDraggableState<?> anchoredDraggableState = this.$state;
                    float fVelocityToFloat = velocityToFloat(j2);
                    bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.J$0 = j2;
                    bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label = 1;
                    if (anchoredDraggableState.settle(fVelocityToFloat, bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    j2 = bottomSheetScaffoldKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.J$0;
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

    private static final NestedScrollConnection ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(AnchoredDraggableState<?> anchoredDraggableState, Orientation orientation) {
        return new AnonymousClass1(anchoredDraggableState, orientation);
    }
}
