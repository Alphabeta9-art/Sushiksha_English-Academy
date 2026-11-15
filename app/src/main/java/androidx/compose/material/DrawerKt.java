package androidx.compose.material;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
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
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.Velocity;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Drawer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000~\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a+\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¢\u0006\u0002\u0010\u0007\u001a;\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\n2\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0007¢\u0006\u0002\u0010\u000e\u001a\u0090\u0001\u0010\u000f\u001a\u00020\u00102\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00100\u0005¢\u0006\u0002\b\u0013¢\u0006\u0002\b\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00012\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010 \u001a\u00020\u001e2\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00100\"¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0004\b#\u0010$\u001a\u0090\u0001\u0010%\u001a\u00020\u00102\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00100\u0005¢\u0006\u0002\b\u0013¢\u0006\u0002\b\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\t2\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010 \u001a\u00020\u001e2\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00100\"¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0004\b&\u0010'\u001a \u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00020\r2\u0006\u0010*\u001a\u00020\r2\u0006\u0010+\u001a\u00020\rH\u0002\u001a-\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u001e2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00100\"2\u0006\u0010/\u001a\u00020\u0006H\u0003¢\u0006\u0004\b0\u00101\u001a;\u00102\u001a\u00020\u00102\u0006\u00103\u001a\u00020\u00062\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00100\"2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\r0\"2\u0006\u0010-\u001a\u00020\u001eH\u0003¢\u0006\u0004\b6\u00107\u001a\u0014\u0010?\u001a\u00020@2\n\u0010A\u001a\u0006\u0012\u0002\b\u00030BH\u0002\"\u0010\u00108\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0004\n\u0002\u00109\"\u0010\u0010:\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0004\n\u0002\u00109\"\u0010\u0010;\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0004\n\u0002\u00109\"\u0014\u0010<\u001a\b\u0012\u0004\u0012\u00020\r0=X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010>\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006C²\u0006\n\u0010D\u001a\u00020\rX\u008a\u0084\u0002"}, d2 = {"rememberDrawerState", "Landroidx/compose/material/DrawerState;", "initialValue", "Landroidx/compose/material/DrawerValue;", "confirmStateChange", "Lkotlin/Function1;", "", "(Landroidx/compose/material/DrawerValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/DrawerState;", "rememberBottomDrawerState", "Landroidx/compose/material/BottomDrawerState;", "Landroidx/compose/material/BottomDrawerValue;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "(Landroidx/compose/material/BottomDrawerValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/BottomDrawerState;", "ModalDrawer", "", "drawerContent", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "drawerState", "gesturesEnabled", "drawerShape", "Landroidx/compose/ui/graphics/Shape;", "drawerElevation", "Landroidx/compose/ui/unit/Dp;", "drawerBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "drawerContentColor", "scrimColor", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function0;", "ModalDrawer-Gs3lGvM", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material/DrawerState;ZLandroidx/compose/ui/graphics/Shape;FJJJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "BottomDrawer", "BottomDrawer-Gs3lGvM", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material/BottomDrawerState;ZLandroidx/compose/ui/graphics/Shape;FJJJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "calculateFraction", CmcdHeadersFactory.OBJECT_TYPE_AUDIO_ONLY, "b", "pos", "BottomDrawerScrim", "color", "onDismiss", "visible", "BottomDrawerScrim-3J-VO9M", "(JLkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "Scrim", TtmlNode.TEXT_EMPHASIS_MARK_OPEN, "onClose", "fraction", "Scrim-Bx497Mc", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;JLandroidx/compose/runtime/Composer;I)V", "EndDrawerPadding", "F", "DrawerPositionalThreshold", "DrawerVelocityThreshold", "AnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "BottomDrawerOpenFraction", "ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "state", "Landroidx/compose/material/AnchoredDraggableState;", "material", "alpha"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DrawerKt {
    private static final float BottomDrawerOpenFraction = 0.5f;
    private static final float EndDrawerPadding = Dp.m7905constructorimpl(56);
    private static final float DrawerPositionalThreshold = Dp.m7905constructorimpl(56);
    private static final float DrawerVelocityThreshold = Dp.m7905constructorimpl(400);
    private static final TweenSpec<Float> AnimationSpec = new TweenSpec<>(256, 0, null, 6, null);

    /* JADX INFO: compiled from: Drawer.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BottomDrawerValue.values().length];
            try {
                iArr[BottomDrawerValue.Closed.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BottomDrawerValue.Open.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BottomDrawerValue.Expanded.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit BottomDrawerScrim_3J_VO9M$lambda$4(long j, Function0 function0, boolean z, int i, Composer composer, int i2) {
        m1809BottomDrawerScrim3JVO9M(j, function0, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit BottomDrawer_Gs3lGvM$lambda$1(Function3 function3, Modifier modifier, BottomDrawerState bottomDrawerState, boolean z, Shape shape, float f, long j, long j2, long j3, Function2 function2, int i, int i2, Composer composer, int i3) {
        m1808BottomDrawerGs3lGvM(function3, modifier, bottomDrawerState, z, shape, f, j, j2, j3, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ModalDrawer_Gs3lGvM$lambda$1(Function3 function3, Modifier modifier, DrawerState drawerState, boolean z, Shape shape, float f, long j, long j2, long j3, Function2 function2, int i, int i2, Composer composer, int i3) {
        m1810ModalDrawerGs3lGvM(function3, modifier, drawerState, z, shape, f, j, j2, j3, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Scrim_Bx497Mc$lambda$3(boolean z, Function0 function0, Function0 function1, long j, int i, Composer composer, int i2) {
        m1811ScrimBx497Mc(z, function0, function1, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rememberDrawerState$lambda$0$0(DrawerValue it) {
        return true;
    }

    public static final DrawerState rememberDrawerState(final DrawerValue initialValue, final Function1<? super DrawerValue, Boolean> function1, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -1435874229, "C(rememberDrawerState)N(initialValue,confirmStateChange)389@14774L8,391@14876L61,391@14812L125:Drawer.kt#jmzs0o");
        if ((i & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, -1190628621, "CC(remember):Drawer.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(DrawerKt.rememberDrawerState$lambda$0$0((DrawerValue) obj));
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            function1 = (Function1) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1435874229, $changed, -1, "androidx.compose.material.rememberDrawerState (Drawer.kt:390)");
        }
        Object[] objArr = new Object[0];
        Saver<DrawerState, DrawerValue> Saver = DrawerState.INSTANCE.Saver(function1);
        ComposerKt.sourceInformationMarkerStart($composer, -1190625304, "CC(remember):Drawer.kt#9igjgp");
        boolean z = true;
        boolean z2 = ((($changed & 14) ^ 6) > 4 && $composer.changed(initialValue.ordinal())) || ($changed & 6) == 4;
        if (((($changed & 112) ^ 48) <= 32 || !$composer.changed(function1)) && ($changed & 48) != 32) {
            z = false;
        }
        boolean invalid$iv = z2 | z;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function0() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return DrawerKt.rememberDrawerState$lambda$1$0(initialValue, function1);
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final DrawerState rememberDrawerState$lambda$1$0(DrawerValue $initialValue, Function1 $confirmStateChange) {
        return new DrawerState($initialValue, $confirmStateChange);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rememberBottomDrawerState$lambda$0$0(BottomDrawerValue it) {
        return true;
    }

    public static final BottomDrawerState rememberBottomDrawerState(final BottomDrawerValue initialValue, final Function1<? super BottomDrawerValue, Boolean> function1, final AnimationSpec<Float> animationSpec, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1477366969, "C(rememberBottomDrawerState)N(initialValue,confirmStateChange,animationSpec)407@15417L8,410@15553L7,414@15698L91,411@15572L217:Drawer.kt#jmzs0o");
        if ((i & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, -1884513759, "CC(remember):Drawer.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(DrawerKt.rememberBottomDrawerState$lambda$0$0((BottomDrawerValue) obj));
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            function1 = (Function1) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
        }
        if ((i & 4) != 0) {
            AnimationSpec animationSpec2 = DrawerDefaults.INSTANCE.getAnimationSpec();
            animationSpec = animationSpec2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1477366969, $changed, -1, "androidx.compose.material.rememberBottomDrawerState (Drawer.kt:409)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Density density = (Density) objConsume;
        Object[] objArr = {density};
        Saver<BottomDrawerState, BottomDrawerValue> Saver = BottomDrawerState.INSTANCE.Saver(density, function1, animationSpec);
        ComposerKt.sourceInformationMarkerStart($composer, -1884504684, "CC(remember):Drawer.kt#9igjgp");
        boolean z = true;
        boolean zChanged = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialValue.ordinal())) || ($changed & 6) == 4) | $composer.changed(density);
        if (((($changed & 112) ^ 48) <= 32 || !$composer.changed(function1)) && ($changed & 48) != 32) {
            z = false;
        }
        boolean invalid$iv = zChanged | z | $composer.changedInstance(animationSpec);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function0() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return DrawerKt.rememberBottomDrawerState$lambda$1$0(initialValue, density, function1, animationSpec);
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        BottomDrawerState bottomDrawerState = (BottomDrawerState) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) Saver, (Function0) it$iv2, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return bottomDrawerState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BottomDrawerState rememberBottomDrawerState$lambda$1$0(BottomDrawerValue $initialValue, Density $density, Function1 $confirmStateChange, AnimationSpec $animationSpec) {
        return new BottomDrawerState($initialValue, $density, $confirmStateChange, $animationSpec);
    }

    /* JADX INFO: renamed from: ModalDrawer-Gs3lGvM, reason: not valid java name */
    public static final void m1810ModalDrawerGs3lGvM(final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, DrawerState drawerState, boolean gesturesEnabled, Shape drawerShape, float f, long j, long j2, long j3, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        DrawerState drawerState2;
        boolean z;
        Shape shape;
        float f2;
        int i2;
        int $dirty;
        int $dirty2;
        Composer $composer2;
        final Modifier.Companion modifier3;
        final DrawerState drawerState3;
        final boolean gesturesEnabled2;
        final Shape drawerShape2;
        final float f3;
        final long j4;
        final long j5;
        final long j6;
        DrawerState drawerState4;
        Shape drawerShape3;
        long backgroundColor;
        long jM1783contentColorForek8zF_U;
        final long scrimColor;
        final boolean gesturesEnabled3;
        final Shape drawerShape4;
        final float f4;
        final long j7;
        final long j8;
        final DrawerState drawerState5;
        int $dirty3;
        Composer $composer3 = $composer.startRestartGroup(1979404999);
        ComposerKt.sourceInformation($composer3, "C(ModalDrawer)N(drawerContent,modifier,drawerState,gesturesEnabled,drawerShape,drawerElevation:c#ui.unit.Dp,drawerBackgroundColor:c#ui.graphics.Color,drawerContentColor:c#ui.graphics.Color,scrimColor:c#ui.graphics.Color,content)462@17984L24,463@18056L3421,463@18013L3464:Drawer.kt#jmzs0o");
        int $dirty4 = $changed;
        if (($changed & 6) == 0) {
            $dirty4 |= $composer3.changedInstance(function3) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty4 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                drawerState2 = drawerState;
                int i4 = $composer3.changed(drawerState2) ? 256 : 128;
                $dirty4 |= i4;
            } else {
                drawerState2 = drawerState;
            }
            $dirty4 |= i4;
        } else {
            drawerState2 = drawerState;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty4 |= 3072;
            z = gesturesEnabled;
        } else if (($changed & 3072) == 0) {
            z = gesturesEnabled;
            $dirty4 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = gesturesEnabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape = drawerShape;
                int i6 = $composer3.changed(shape) ? 16384 : 8192;
                $dirty4 |= i6;
            } else {
                shape = drawerShape;
            }
            $dirty4 |= i6;
        } else {
            shape = drawerShape;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f2 = f;
        } else if ((196608 & $changed) == 0) {
            f2 = f;
            $dirty4 |= $composer3.changed(f2) ? 131072 : 65536;
        } else {
            f2 = f;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                i2 = i3;
                int i8 = $composer3.changed(j) ? 1048576 : 524288;
                $dirty4 |= i8;
            } else {
                i2 = i3;
            }
            $dirty4 |= i8;
        } else {
            i2 = i3;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                $dirty3 = $dirty4;
                int i9 = $composer3.changed(j2) ? 8388608 : 4194304;
                $dirty = $dirty3 | i9;
            } else {
                $dirty3 = $dirty4;
            }
            $dirty = $dirty3 | i9;
        } else {
            $dirty = $dirty4;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= ((i & 256) == 0 && $composer3.changed(j3)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty2 = $dirty | ($composer3.changedInstance(function2) ? 536870912 : 268435456);
        } else {
            $dirty2 = $dirty;
        }
        int $dirty5 = $dirty2;
        if ($composer3.shouldExecute(($dirty5 & 306783379) != 306783378, $dirty5 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "453@17560L39,455@17678L5,457@17787L15,458@17836L38,459@17915L10");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    drawerState4 = rememberDrawerState(DrawerValue.Closed, null, $composer3, 6, 2);
                    $dirty5 &= -897;
                } else {
                    drawerState4 = drawerState2;
                }
                boolean gesturesEnabled4 = i5 != 0 ? true : z;
                if ((i & 16) != 0) {
                    drawerShape3 = DrawerDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty5 &= -57345;
                } else {
                    drawerShape3 = shape;
                }
                float fM1803getElevationD9Ej5fM = i7 != 0 ? DrawerDefaults.INSTANCE.m1803getElevationD9Ej5fM() : f2;
                if ((i & 64) != 0) {
                    backgroundColor = DrawerDefaults.INSTANCE.getBackgroundColor($composer3, 6);
                    $dirty5 &= -3670017;
                } else {
                    backgroundColor = j;
                }
                if ((i & 128) != 0) {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(backgroundColor, $composer3, ($dirty5 >> 18) & 14);
                    $dirty5 &= -29360129;
                } else {
                    jM1783contentColorForek8zF_U = j2;
                }
                if ((i & 256) != 0) {
                    $dirty5 &= -234881025;
                    drawerShape4 = drawerShape3;
                    f4 = fM1803getElevationD9Ej5fM;
                    j7 = backgroundColor;
                    j8 = jM1783contentColorForek8zF_U;
                    scrimColor = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                    drawerState5 = drawerState4;
                    gesturesEnabled3 = gesturesEnabled4;
                } else {
                    scrimColor = j3;
                    gesturesEnabled3 = gesturesEnabled4;
                    drawerShape4 = drawerShape3;
                    f4 = fM1803getElevationD9Ej5fM;
                    j7 = backgroundColor;
                    j8 = jM1783contentColorForek8zF_U;
                    drawerState5 = drawerState4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty5 &= -897;
                }
                if ((i & 16) != 0) {
                    $dirty5 &= -57345;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty5 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty5 &= -234881025;
                }
                j7 = j;
                j8 = j2;
                scrimColor = j3;
                modifier3 = modifier2;
                gesturesEnabled3 = z;
                drawerShape4 = shape;
                f4 = f2;
                drawerState5 = drawerState2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1979404999, $dirty5, -1, "androidx.compose.material.ModalDrawer (Drawer.kt:461)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object it$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3);
                $composer3.updateRememberedValue(value$iv$iv);
                it$iv$iv = value$iv$iv;
            }
            final CoroutineScope scope = (CoroutineScope) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            BoxWithConstraintsKt.BoxWithConstraints(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null), null, false, ComposableLambdaKt.rememberComposableLambda(-1549911011, true, new Function3() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return DrawerKt.ModalDrawer_Gs3lGvM$lambda$0(drawerState5, gesturesEnabled3, scope, scrimColor, drawerShape4, j7, j8, f4, function2, function3, (BoxWithConstraintsScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), $composer3, 3072, 6);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            drawerState3 = drawerState5;
            gesturesEnabled2 = gesturesEnabled3;
            j6 = scrimColor;
            drawerShape2 = drawerShape4;
            j4 = j7;
            j5 = j8;
            f3 = f4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            drawerState3 = drawerState2;
            gesturesEnabled2 = z;
            drawerShape2 = shape;
            f3 = f2;
            j4 = j;
            j5 = j2;
            j6 = j3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DrawerKt.ModalDrawer_Gs3lGvM$lambda$1(function3, modifier3, drawerState3, gesturesEnabled2, drawerShape2, f3, j4, j5, j6, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:63:0x035e  */
    /* JADX WARN: Code duplicated, block: B:69:0x0396  */
    /* JADX WARN: Code duplicated, block: B:75:0x0421  */
    /* JADX WARN: Code duplicated, block: B:81:0x046c  */
    /* JADX WARN: Code duplicated, block: B:84:0x04cc  */
    static final Unit ModalDrawer_Gs3lGvM$lambda$0(final DrawerState $drawerState, final boolean $gesturesEnabled, final CoroutineScope $scope, long j, Shape $drawerShape, long j2, long j3, float f, Function2 $content, final Function3 $drawerContent, BoxWithConstraintsScope $this$BoxWithConstraints, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Composer $composer$iv;
        boolean invalid$iv;
        Object it$iv;
        boolean invalid$iv2;
        Object it$iv2;
        final String navigationMenu;
        boolean invalid$iv3;
        Object it$iv3;
        boolean invalid$iv4;
        Object it$iv4;
        ComposerKt.sourceInformation($composer, "C472@18435L7,473@18462L274,473@18451L285,482@18779L7,483@18818L2653:Drawer.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed($this$BoxWithConstraints) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if ($composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1549911011, $dirty2, -1, "androidx.compose.material.ModalDrawer.<anonymous> (Drawer.kt:464)");
            }
            long modalDrawerConstraints = $this$BoxWithConstraints.mo713getConstraintsmsEJaDk();
            if (!Constraints.m7854getHasBoundedWidthimpl(modalDrawerConstraints)) {
                throw new IllegalStateException("Drawer shouldn't have infinite width");
            }
            final float minValue = -Constraints.m7858getMaxWidthimpl(modalDrawerConstraints);
            final float maxValue = 0.0f;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer);
            final Density density = (Density) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer, 889183407, "CC(remember):Drawer.kt#9igjgp");
            boolean invalid$iv5 = $composer.changed($drawerState) | $composer.changed(density) | $composer.changed(minValue);
            Object it$iv5 = $composer.rememberedValue();
            if (invalid$iv5 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$0$0($drawerState, density, minValue, maxValue);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv5 = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            EffectsKt.SideEffect((Function0) it$iv5, $composer, 0);
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer);
            boolean isRtl = objConsume2 == LayoutDirection.Rtl;
            Modifier.Companion companion = Modifier.INSTANCE;
            AnchoredDraggableState<DrawerValue> anchoredDraggableState$material = $drawerState.getAnchoredDraggableState$material();
            Modifier modifier$iv = AnchoredDraggableKt.anchoredDraggable(companion, anchoredDraggableState$material, Orientation.Horizontal, (48 & 4) != 0 ? true : $gesturesEnabled, (48 & 8) != 0 ? false : isRtl, (48 & 16) != 0 ? null : null, (48 & 32) != 0 ? anchoredDraggableState$material.isAnimationRunning() : false);
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
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -215522521, "C491@19100L17,494@19206L358,504@19593L70,492@19130L584,507@19748L33,510@19868L7,518@20348L58,520@20499L679,539@21374L87,508@19794L1667:Drawer.kt#jmzs0o");
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Modifier modifier$iv2 = Modifier.INSTANCE;
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
            int $changed$iv$iv2 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer, modifier$iv2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function1 = constructor2;
                $composer.createNode(function1);
            } else {
                function1 = constructor2;
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                $composer$iv = $composer;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                Composer $composer$iv2 = $composer$iv;
                ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i4 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv2, -1269670255, "C491@19106L9:Drawer.kt#jmzs0o");
                $content.invoke($composer$iv2, 0);
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                boolean zIsOpen = $drawerState.isOpen();
                ComposerKt.sourceInformationMarkerStart($composer, -561140279, "CC(remember):Drawer.kt#9igjgp");
                invalid$iv = $composer.changed($gesturesEnabled) | $composer.changed($drawerState) | $composer.changedInstance($scope);
                it$iv = $composer.rememberedValue();
                if (!invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = new Function0() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$1$1$0($gesturesEnabled, $drawerState, $scope);
                        }
                    };
                    $composer.updateRememberedValue(value$iv2);
                    it$iv = value$iv2;
                }
                Function0 function2 = (Function0) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, -561128183, "CC(remember):Drawer.kt#9igjgp");
                invalid$iv2 = $composer.changed(minValue) | $composer.changed($drawerState);
                it$iv2 = $composer.rememberedValue();
                if (!invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = new Function0() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Float.valueOf(DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$1$2$0(minValue, maxValue, $drawerState));
                        }
                    };
                    $composer.updateRememberedValue(value$iv3);
                    it$iv2 = value$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                m1811ScrimBx497Mc(zIsOpen, function2, (Function0) it$iv2, j, $composer, 0);
                navigationMenu = Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1949getNavigationMenuUdPEhr4(), $composer, 6);
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Density $this$ModalDrawer_Gs3lGvM_u24lambda_u240_u241_u243 = (Density) objConsume3;
                Modifier modifierM868sizeInqDBjuR0 = SizeKt.m868sizeInqDBjuR0(Modifier.INSTANCE, $this$ModalDrawer_Gs3lGvM_u24lambda_u240_u241_u243.mo403toDpu2uoSUM(Constraints.m7860getMinWidthimpl(modalDrawerConstraints)), $this$ModalDrawer_Gs3lGvM_u24lambda_u240_u241_u243.mo403toDpu2uoSUM(Constraints.m7859getMinHeightimpl(modalDrawerConstraints)), $this$ModalDrawer_Gs3lGvM_u24lambda_u240_u241_u243.mo403toDpu2uoSUM(Constraints.m7858getMaxWidthimpl(modalDrawerConstraints)), $this$ModalDrawer_Gs3lGvM_u24lambda_u240_u241_u243.mo403toDpu2uoSUM(Constraints.m7857getMaxHeightimpl(modalDrawerConstraints)));
                ComposerKt.sourceInformationMarkerStart($composer, -561104035, "CC(remember):Drawer.kt#9igjgp");
                invalid$iv3 = $composer.changed($drawerState);
                it$iv3 = $composer.rememberedValue();
                if (!invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv4 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$1$4$0($drawerState, (Density) obj);
                        }
                    };
                    $composer.updateRememberedValue(value$iv4);
                    it$iv3 = value$iv4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier modifierM823paddingqDBjuR0$default = PaddingKt.m823paddingqDBjuR0$default(OffsetKt.offset(modifierM868sizeInqDBjuR0, (Function1) it$iv3), 0.0f, 0.0f, EndDrawerPadding, 0.0f, 11, null);
                ComposerKt.sourceInformationMarkerStart($composer, -561098582, "CC(remember):Drawer.kt#9igjgp");
                invalid$iv4 = $composer.changed(navigationMenu) | $composer.changed($drawerState) | $composer.changedInstance($scope);
                it$iv4 = $composer.rememberedValue();
                if (!invalid$iv4 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv5 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$1$5$0(navigationMenu, $drawerState, $scope, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer.updateRememberedValue(value$iv5);
                    it$iv4 = value$iv5;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                SurfaceKt.m1954SurfaceFjzlyU(SemanticsModifierKt.semantics$default(modifierM823paddingqDBjuR0$default, false, (Function1) it$iv4, 1, null), $drawerShape, j2, j3, null, f, ComposableLambdaKt.rememberComposableLambda(1265707871, true, new Function2() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$1$6($drawerContent, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer, 54), $composer, 1572864, 16);
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
                $composer$iv = $composer;
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = ($changed$iv$iv$iv2 >> 6) & 14;
            Composer $composer$iv3 = $composer$iv;
            ComposerKt.sourceInformationMarkerStart($composer$iv3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer$iv3, -1269670255, "C491@19106L9:Drawer.kt#jmzs0o");
            $content.invoke($composer$iv3, 0);
            ComposerKt.sourceInformationMarkerEnd($composer$iv3);
            ComposerKt.sourceInformationMarkerEnd($composer$iv3);
            $composer$iv.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            boolean zIsOpen2 = $drawerState.isOpen();
            ComposerKt.sourceInformationMarkerStart($composer, -561140279, "CC(remember):Drawer.kt#9igjgp");
            invalid$iv = $composer.changed($gesturesEnabled) | $composer.changed($drawerState) | $composer.changedInstance($scope);
            it$iv = $composer.rememberedValue();
            if (!invalid$iv) {
                Object value$iv6 = new Function0() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$1$1$0($gesturesEnabled, $drawerState, $scope);
                    }
                };
                $composer.updateRememberedValue(value$iv6);
                it$iv = value$iv6;
            } else {
                Object value$iv7 = new Function0() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$1$1$0($gesturesEnabled, $drawerState, $scope);
                    }
                };
                $composer.updateRememberedValue(value$iv7);
                it$iv = value$iv7;
            }
            Function0 function3 = (Function0) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -561128183, "CC(remember):Drawer.kt#9igjgp");
            invalid$iv2 = $composer.changed(minValue) | $composer.changed($drawerState);
            it$iv2 = $composer.rememberedValue();
            if (!invalid$iv2) {
                Object value$iv8 = new Function0() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$1$2$0(minValue, maxValue, $drawerState));
                    }
                };
                $composer.updateRememberedValue(value$iv8);
                it$iv2 = value$iv8;
            } else {
                Object value$iv9 = new Function0() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$1$2$0(minValue, maxValue, $drawerState));
                    }
                };
                $composer.updateRememberedValue(value$iv9);
                it$iv2 = value$iv9;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            m1811ScrimBx497Mc(zIsOpen2, function3, (Function0) it$iv2, j, $composer, 0);
            navigationMenu = Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1949getNavigationMenuUdPEhr4(), $composer, 6);
            ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer.consume(localDensity3);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Density $this$ModalDrawer_Gs3lGvM_u24lambda_u240_u241_u244 = (Density) objConsume4;
            Modifier modifierM868sizeInqDBjuR1 = SizeKt.m868sizeInqDBjuR0(Modifier.INSTANCE, $this$ModalDrawer_Gs3lGvM_u24lambda_u240_u241_u244.mo403toDpu2uoSUM(Constraints.m7860getMinWidthimpl(modalDrawerConstraints)), $this$ModalDrawer_Gs3lGvM_u24lambda_u240_u241_u244.mo403toDpu2uoSUM(Constraints.m7859getMinHeightimpl(modalDrawerConstraints)), $this$ModalDrawer_Gs3lGvM_u24lambda_u240_u241_u244.mo403toDpu2uoSUM(Constraints.m7858getMaxWidthimpl(modalDrawerConstraints)), $this$ModalDrawer_Gs3lGvM_u24lambda_u240_u241_u244.mo403toDpu2uoSUM(Constraints.m7857getMaxHeightimpl(modalDrawerConstraints)));
            ComposerKt.sourceInformationMarkerStart($composer, -561104035, "CC(remember):Drawer.kt#9igjgp");
            invalid$iv3 = $composer.changed($drawerState);
            it$iv3 = $composer.rememberedValue();
            if (!invalid$iv3) {
                Object value$iv10 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$1$4$0($drawerState, (Density) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv10);
                it$iv3 = value$iv10;
            } else {
                Object value$iv11 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$1$4$0($drawerState, (Density) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv11);
                it$iv3 = value$iv11;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierM823paddingqDBjuR0$default2 = PaddingKt.m823paddingqDBjuR0$default(OffsetKt.offset(modifierM868sizeInqDBjuR1, (Function1) it$iv3), 0.0f, 0.0f, EndDrawerPadding, 0.0f, 11, null);
            ComposerKt.sourceInformationMarkerStart($composer, -561098582, "CC(remember):Drawer.kt#9igjgp");
            invalid$iv4 = $composer.changed(navigationMenu) | $composer.changed($drawerState) | $composer.changedInstance($scope);
            it$iv4 = $composer.rememberedValue();
            if (!invalid$iv4) {
                Object value$iv12 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$1$5$0(navigationMenu, $drawerState, $scope, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv12);
                it$iv4 = value$iv12;
            } else {
                Object value$iv13 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$1$5$0(navigationMenu, $drawerState, $scope, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv13);
                it$iv4 = value$iv13;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            SurfaceKt.m1954SurfaceFjzlyU(SemanticsModifierKt.semantics$default(modifierM823paddingqDBjuR0$default2, false, (Function1) it$iv4, 1, null), $drawerShape, j2, j3, null, f, ComposableLambdaKt.rememberComposableLambda(1265707871, true, new Function2() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$1$6($drawerContent, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 1572864, 16);
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
    public static final Unit ModalDrawer_Gs3lGvM$lambda$0$0$0(DrawerState $drawerState, Density $density, final float $minValue, final float $maxValue) {
        $drawerState.setDensity$material($density);
        DraggableAnchors anchors = AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda30
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$0$0$0($minValue, $maxValue, (DraggableAnchorsConfig) obj);
            }
        });
        AnchoredDraggableState.updateAnchors$default($drawerState.getAnchoredDraggableState$material(), anchors, null, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ModalDrawer_Gs3lGvM$lambda$0$0$0$0(float $minValue, float $maxValue, DraggableAnchorsConfig $this$DraggableAnchors) {
        $this$DraggableAnchors.at(DrawerValue.Closed, $minValue);
        $this$DraggableAnchors.at(DrawerValue.Open, $maxValue);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ModalDrawer_Gs3lGvM$lambda$0$1$1$0(boolean $gesturesEnabled, DrawerState $drawerState, CoroutineScope $scope) {
        if ($gesturesEnabled && $drawerState.getAnchoredDraggableState$material().getConfirmValueChange$material().invoke(DrawerValue.Closed).booleanValue()) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new DrawerKt$ModalDrawer$1$2$2$1$1($drawerState, null), 3, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float ModalDrawer_Gs3lGvM$lambda$0$1$2$0(float $minValue, float $maxValue, DrawerState $drawerState) {
        return calculateFraction($minValue, $maxValue, $drawerState.requireOffset$material());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntOffset ModalDrawer_Gs3lGvM$lambda$0$1$4$0(DrawerState $drawerState, Density $this$offset) {
        int x$iv = MathKt.roundToInt($drawerState.requireOffset$material());
        return IntOffset.m8024boximpl(IntOffset.m8027constructorimpl((((long) x$iv) << 32) | (((long) 0) & 4294967295L)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ModalDrawer_Gs3lGvM$lambda$0$1$5$0(String $navigationMenu, final DrawerState $drawerState, final CoroutineScope $scope, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setPaneTitle($this$semantics, $navigationMenu);
        if ($drawerState.isOpen()) {
            SemanticsPropertiesKt.dismiss$default($this$semantics, null, new Function0() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(DrawerKt.ModalDrawer_Gs3lGvM$lambda$0$1$5$0$0($drawerState, $scope));
                }
            }, 1, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ModalDrawer_Gs3lGvM$lambda$0$1$5$0$0(DrawerState $drawerState, CoroutineScope $scope) {
        if ($drawerState.getAnchoredDraggableState$material().getConfirmValueChange$material().invoke(DrawerValue.Closed).booleanValue()) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new DrawerKt$ModalDrawer$1$2$6$1$1$1($drawerState, null), 3, null);
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:27:0x0147  */
    public static final Unit ModalDrawer_Gs3lGvM$lambda$0$1$6(Function3 $drawerContent, Composer $composer, int $changed) {
        int $changed$iv;
        ComposerKt.sourceInformation($composer, "C540@21392L55:Drawer.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1265707871, $changed, -1, "androidx.compose.material.ModalDrawer.<anonymous>.<anonymous>.<anonymous> (Drawer.kt:540)");
            }
            Modifier modifier$iv = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            int $changed$iv$iv = (6 << 3) & 112;
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
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                $changed$iv = 6;
            } else {
                $changed$iv = 6;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                $drawerContent.invoke(ColumnScopeInstance.INSTANCE, $composer, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
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
            int i2 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384672921, "C89@4556L9:Column.kt#2w3rfo");
            $drawerContent.invoke(ColumnScopeInstance.INSTANCE, $composer, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
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

    /* JADX INFO: renamed from: BottomDrawer-Gs3lGvM, reason: not valid java name */
    public static final void m1808BottomDrawerGs3lGvM(final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, BottomDrawerState drawerState, boolean gesturesEnabled, Shape drawerShape, float f, long j, long j2, long j3, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        BottomDrawerState bottomDrawerState;
        boolean z;
        Shape shape;
        float f2;
        long j4;
        int i2;
        Composer $composer2;
        final Modifier modifier2;
        final BottomDrawerState drawerState2;
        final boolean gesturesEnabled2;
        final Shape drawerShape2;
        final float f3;
        final long j5;
        final long j6;
        final long j7;
        int $dirty;
        BottomDrawerState drawerState3;
        Shape drawerShape3;
        long backgroundColor;
        long jM1783contentColorForek8zF_U;
        final boolean gesturesEnabled3;
        final long scrimColor;
        final BottomDrawerState drawerState4;
        final Shape drawerShape4;
        final float f4;
        final long j8;
        final long j9;
        Modifier modifier3;
        Composer $composer3 = $composer.startRestartGroup(-1403479060);
        ComposerKt.sourceInformation($composer3, "C(BottomDrawer)N(drawerContent,modifier,drawerState,gesturesEnabled,drawerShape,drawerElevation:c#ui.unit.Dp,drawerBackgroundColor:c#ui.graphics.Color,drawerContentColor:c#ui.graphics.Color,scrimColor:c#ui.graphics.Color,content)589@23753L24,590@23825L4662,590@23782L4705:Drawer.kt#jmzs0o");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                bottomDrawerState = drawerState;
                int i4 = $composer3.changedInstance(bottomDrawerState) ? 256 : 128;
                $dirty2 |= i4;
            } else {
                bottomDrawerState = drawerState;
            }
            $dirty2 |= i4;
        } else {
            bottomDrawerState = drawerState;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty2 |= 3072;
            z = gesturesEnabled;
        } else if (($changed & 3072) == 0) {
            z = gesturesEnabled;
            $dirty2 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = gesturesEnabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape = drawerShape;
                int i6 = $composer3.changed(shape) ? 16384 : 8192;
                $dirty2 |= i6;
            } else {
                shape = drawerShape;
            }
            $dirty2 |= i6;
        } else {
            shape = drawerShape;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f2 = f;
        } else if ((196608 & $changed) == 0) {
            f2 = f;
            $dirty2 |= $composer3.changed(f2) ? 131072 : 65536;
        } else {
            f2 = f;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                j4 = j;
                int i8 = $composer3.changed(j4) ? 1048576 : 524288;
                $dirty2 |= i8;
            } else {
                j4 = j;
            }
            $dirty2 |= i8;
        } else {
            j4 = j;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                i2 = i3;
                int i9 = $composer3.changed(j2) ? 8388608 : 4194304;
                $dirty2 |= i9;
            } else {
                i2 = i3;
            }
            $dirty2 |= i9;
        } else {
            i2 = i3;
        }
        if ((100663296 & $changed) == 0) {
            $dirty2 |= ((i & 256) == 0 && $composer3.changed(j3)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((805306368 & $changed) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 536870912 : 268435456;
        }
        if ($composer3.shouldExecute(($dirty2 & 306783379) != 306783378, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "580@23335L33,582@23447L5,584@23556L15,585@23605L38,586@23684L10");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if ((i & 4) != 0) {
                    int $dirty3 = $dirty2;
                    drawerState3 = rememberBottomDrawerState(BottomDrawerValue.Closed, null, null, $composer3, 6, 6);
                    $dirty = $dirty3 & (-897);
                } else {
                    $dirty = $dirty2;
                    drawerState3 = bottomDrawerState;
                }
                boolean gesturesEnabled4 = i5 != 0 ? true : z;
                if ((i & 16) != 0) {
                    drawerShape3 = DrawerDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty &= -57345;
                } else {
                    drawerShape3 = shape;
                }
                float fM1803getElevationD9Ej5fM = i7 != 0 ? DrawerDefaults.INSTANCE.m1803getElevationD9Ej5fM() : f2;
                if ((i & 64) != 0) {
                    backgroundColor = DrawerDefaults.INSTANCE.getBackgroundColor($composer3, 6);
                    $dirty &= -3670017;
                } else {
                    backgroundColor = j;
                }
                if ((i & 128) != 0) {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(backgroundColor, $composer3, ($dirty >> 18) & 14);
                    $dirty &= -29360129;
                } else {
                    jM1783contentColorForek8zF_U = j2;
                }
                if ((i & 256) != 0) {
                    gesturesEnabled3 = gesturesEnabled4;
                    drawerState4 = drawerState3;
                    drawerShape4 = drawerShape3;
                    f4 = fM1803getElevationD9Ej5fM;
                    j8 = backgroundColor;
                    j9 = jM1783contentColorForek8zF_U;
                    scrimColor = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                    $dirty2 = $dirty & (-234881025);
                    modifier3 = modifier4;
                } else {
                    gesturesEnabled3 = gesturesEnabled4;
                    scrimColor = j3;
                    drawerState4 = drawerState3;
                    drawerShape4 = drawerShape3;
                    f4 = fM1803getElevationD9Ej5fM;
                    j8 = backgroundColor;
                    j9 = jM1783contentColorForek8zF_U;
                    $dirty2 = $dirty;
                    modifier3 = modifier4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty2 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty2 &= -234881025;
                }
                modifier3 = modifier;
                j9 = j2;
                scrimColor = j3;
                j8 = j4;
                drawerState4 = bottomDrawerState;
                drawerShape4 = shape;
                f4 = f2;
                gesturesEnabled3 = z;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1403479060, $dirty2, -1, "androidx.compose.material.BottomDrawer (Drawer.kt:588)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object it$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3);
                $composer3.updateRememberedValue(value$iv$iv);
                it$iv$iv = value$iv$iv;
            }
            final CoroutineScope scope = (CoroutineScope) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            BoxWithConstraintsKt.BoxWithConstraints(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null), null, false, ComposableLambdaKt.rememberComposableLambda(468886998, true, new Function3() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0(gesturesEnabled3, drawerState4, function2, scrimColor, scope, drawerShape4, j8, j9, f4, function3, (BoxWithConstraintsScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), $composer3, 3072, 6);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            gesturesEnabled2 = gesturesEnabled3;
            drawerState2 = drawerState4;
            j7 = scrimColor;
            drawerShape2 = drawerShape4;
            j5 = j8;
            j6 = j9;
            f3 = f4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            drawerState2 = bottomDrawerState;
            gesturesEnabled2 = z;
            drawerShape2 = shape;
            f3 = f2;
            j5 = j;
            j6 = j2;
            j7 = j3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DrawerKt.BottomDrawer_Gs3lGvM$lambda$1(function3, modifier2, drawerState2, gesturesEnabled2, drawerShape2, f3, j5, j6, j7, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:46:0x024a  */
    /* JADX WARN: Code duplicated, block: B:48:0x0254  */
    /* JADX WARN: Code duplicated, block: B:49:0x0257  */
    /* JADX WARN: Code duplicated, block: B:53:0x0278  */
    /* JADX WARN: Code duplicated, block: B:54:0x027b  */
    /* JADX WARN: Code duplicated, block: B:57:0x02bd  */
    /* JADX WARN: Code duplicated, block: B:60:0x02ca A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:64:0x0301  */
    /* JADX WARN: Code duplicated, block: B:67:0x030e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:71:0x0349  */
    /* JADX WARN: Code duplicated, block: B:74:0x0354 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:78:0x03ba  */
    static final Unit BottomDrawer_Gs3lGvM$lambda$0(final boolean $gesturesEnabled, final BottomDrawerState $drawerState, Function2 $content, long j, final CoroutineScope $scope, Shape $drawerShape, long j2, long j3, float f, final Function3 $drawerContent, BoxWithConstraintsScope $this$BoxWithConstraints, Composer $composer, int $changed) {
        Modifier.Companion nestedScroll;
        Function0<ComposeUiNode> function0;
        Composer $composer$iv;
        Composer $composer$iv2;
        boolean invalid$iv;
        Object value$iv;
        Composer $composer2;
        boolean z;
        boolean invalid$iv2;
        Object value$iv2;
        boolean invalid$iv3;
        Object value$iv3;
        boolean invalid$iv4;
        Object value$iv4;
        ComposerKt.sourceInformation($composer, "C594@24017L7,606@24452L7,617@24824L3657:Drawer.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed($this$BoxWithConstraints) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if ($composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(468886998, $dirty2, -1, "androidx.compose.material.BottomDrawer.<anonymous> (Drawer.kt:591)");
            }
            final float fullHeight = Constraints.m7857getMaxHeightimpl($this$BoxWithConstraints.mo713getConstraintsmsEJaDk());
            boolean isLandscape = Constraints.m7858getMaxWidthimpl($this$BoxWithConstraints.mo713getConstraintsmsEJaDk()) > Constraints.m7857getMaxHeightimpl($this$BoxWithConstraints.mo713getConstraintsmsEJaDk());
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Density $this$BottomDrawer_Gs3lGvM_u24lambda_u240_u240 = (Density) objConsume;
            Modifier drawerConstraints = SizeKt.m869sizeInqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, $this$BottomDrawer_Gs3lGvM_u24lambda_u240_u240.mo403toDpu2uoSUM(Constraints.m7858getMaxWidthimpl($this$BoxWithConstraints.mo713getConstraintsmsEJaDk())), $this$BottomDrawer_Gs3lGvM_u24lambda_u240_u240.mo403toDpu2uoSUM(Constraints.m7857getMaxHeightimpl($this$BoxWithConstraints.mo713getConstraintsmsEJaDk())), 3, null);
            if ($gesturesEnabled) {
                nestedScroll = NestedScrollModifierKt.nestedScroll$default(Modifier.INSTANCE, $drawerState.getNestedScrollConnection(), null, 2, null);
            } else {
                nestedScroll = Modifier.INSTANCE;
            }
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer);
            boolean isRtl = objConsume2 == LayoutDirection.Rtl;
            Modifier modifierThen = Modifier.INSTANCE.then(nestedScroll);
            final boolean isLandscape2 = isLandscape;
            AnchoredDraggableState<BottomDrawerValue> anchoredDraggableState$material = $drawerState.getAnchoredDraggableState$material();
            Modifier swipeable = AnchoredDraggableKt.anchoredDraggable(modifierThen, anchoredDraggableState$material, Orientation.Vertical, (48 & 4) != 0 ? true : $gesturesEnabled, (48 & 8) != 0 ? false : isRtl, (48 & 16) != 0 ? null : null, (48 & 32) != 0 ? anchoredDraggableState$material.isAnimationRunning() : false);
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, swipeable);
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
                $composer$iv = $composer;
            } else {
                $composer$iv = $composer;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                $composer$iv2 = $composer$iv;
                ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i2 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv2, -195134127, "C618@24853L9,621@24958L187,619@24875L346,628@25255L33,631@25379L2130,668@27538L66,669@27636L576,686@28408L63,629@25301L3170:Drawer.kt#jmzs0o");
                $content.invoke($composer$iv2, 0);
                ComposerKt.sourceInformationMarkerStart($composer$iv2, -699031381, "CC(remember):Drawer.kt#9igjgp");
                invalid$iv = $composer$iv2.changed($gesturesEnabled) | $composer$iv2.changedInstance($drawerState) | $composer$iv2.changedInstance($scope);
                value$iv = $composer$iv2.rememberedValue();
                if (!invalid$iv) {
                    $composer2 = $composer$iv2;
                    if (value$iv != Composer.INSTANCE.getEmpty()) {
                    }
                    Function0 function1 = (Function0) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if ($drawerState.getTargetValue() != BottomDrawerValue.Closed) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Composer $composer3 = $composer2;
                    m1809BottomDrawerScrim3JVO9M(j, function1, z, $composer3, 0);
                    final String navigationMenu = Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1949getNavigationMenuUdPEhr4(), $composer3, 6);
                    ComposerKt.sourceInformationMarkerStart($composer3, -699015966, "CC(remember):Drawer.kt#9igjgp");
                    boolean isLandscape3 = $composer3.changed(fullHeight);
                    invalid$iv2 = isLandscape3 | $composer3.changed(isLandscape2) | $composer3.changedInstance($drawerState);
                    value$iv2 = $composer3.rememberedValue();
                    if (!invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda25
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$1$0($drawerState, fullHeight, isLandscape2, (IntSize) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv2);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierOnSizeChanged = OnRemeasuredModifierKt.onSizeChanged(drawerConstraints, (Function1) value$iv2);
                    ComposerKt.sourceInformationMarkerStart($composer3, -698948942, "CC(remember):Drawer.kt#9igjgp");
                    invalid$iv3 = $composer3.changedInstance($drawerState);
                    value$iv3 = $composer3.rememberedValue();
                    if (!invalid$iv3 || value$iv3 == Composer.INSTANCE.getEmpty()) {
                        value$iv3 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda26
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$2$0($drawerState, (Density) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv3);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierOffset = OffsetKt.offset(modifierOnSizeChanged, (Function1) value$iv3);
                    ComposerKt.sourceInformationMarkerStart($composer3, -698945296, "CC(remember):Drawer.kt#9igjgp");
                    invalid$iv4 = $composer3.changed(navigationMenu) | $composer3.changedInstance($drawerState) | $composer3.changedInstance($scope);
                    value$iv4 = $composer3.rememberedValue();
                    if (!invalid$iv4 || value$iv4 == Composer.INSTANCE.getEmpty()) {
                        value$iv4 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda27
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$3$0(navigationMenu, $drawerState, $scope, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv4);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    SurfaceKt.m1954SurfaceFjzlyU(SemanticsModifierKt.semantics$default(modifierOffset, false, (Function1) value$iv4, 1, null), $drawerShape, j2, j3, null, f, ComposableLambdaKt.rememberComposableLambda(1691510868, true, new Function2() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda28
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$4($drawerContent, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, $composer3, 54), $composer3, 1572864, 16);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    $composer$iv.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    $composer2 = $composer$iv2;
                }
                value$iv = new Function0() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$0$0($gesturesEnabled, $drawerState, $scope);
                    }
                };
                $composer$iv2.updateRememberedValue(value$iv);
                Function0 function2 = (Function0) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if ($drawerState.getTargetValue() != BottomDrawerValue.Closed) {
                    z = true;
                } else {
                    z = false;
                }
                Composer $composer4 = $composer2;
                m1809BottomDrawerScrim3JVO9M(j, function2, z, $composer4, 0);
                final String navigationMenu2 = Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1949getNavigationMenuUdPEhr4(), $composer4, 6);
                ComposerKt.sourceInformationMarkerStart($composer4, -699015966, "CC(remember):Drawer.kt#9igjgp");
                boolean isLandscape4 = $composer4.changed(fullHeight);
                invalid$iv2 = isLandscape4 | $composer4.changed(isLandscape2) | $composer4.changedInstance($drawerState);
                value$iv2 = $composer4.rememberedValue();
                if (!invalid$iv2) {
                }
                value$iv2 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda25
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$1$0($drawerState, fullHeight, isLandscape2, (IntSize) obj);
                    }
                };
                $composer4.updateRememberedValue(value$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                Modifier modifierOnSizeChanged2 = OnRemeasuredModifierKt.onSizeChanged(drawerConstraints, (Function1) value$iv2);
                ComposerKt.sourceInformationMarkerStart($composer4, -698948942, "CC(remember):Drawer.kt#9igjgp");
                invalid$iv3 = $composer4.changedInstance($drawerState);
                value$iv3 = $composer4.rememberedValue();
                if (!invalid$iv3) {
                }
                value$iv3 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda26
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$2$0($drawerState, (Density) obj);
                    }
                };
                $composer4.updateRememberedValue(value$iv3);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                Modifier modifierOffset2 = OffsetKt.offset(modifierOnSizeChanged2, (Function1) value$iv3);
                ComposerKt.sourceInformationMarkerStart($composer4, -698945296, "CC(remember):Drawer.kt#9igjgp");
                invalid$iv4 = $composer4.changed(navigationMenu2) | $composer4.changedInstance($drawerState) | $composer4.changedInstance($scope);
                value$iv4 = $composer4.rememberedValue();
                if (!invalid$iv4) {
                }
                value$iv4 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda27
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$3$0(navigationMenu2, $drawerState, $scope, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer4.updateRememberedValue(value$iv4);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                SurfaceKt.m1954SurfaceFjzlyU(SemanticsModifierKt.semantics$default(modifierOffset2, false, (Function1) value$iv4, 1, null), $drawerShape, j2, j3, null, f, ComposableLambdaKt.rememberComposableLambda(1691510868, true, new Function2() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda28
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$4($drawerContent, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer4, 54), $composer4, 1572864, 16);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            $composer$iv2 = $composer$iv;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i4 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, -195134127, "C618@24853L9,621@24958L187,619@24875L346,628@25255L33,631@25379L2130,668@27538L66,669@27636L576,686@28408L63,629@25301L3170:Drawer.kt#jmzs0o");
            $content.invoke($composer$iv2, 0);
            ComposerKt.sourceInformationMarkerStart($composer$iv2, -699031381, "CC(remember):Drawer.kt#9igjgp");
            invalid$iv = $composer$iv2.changed($gesturesEnabled) | $composer$iv2.changedInstance($drawerState) | $composer$iv2.changedInstance($scope);
            value$iv = $composer$iv2.rememberedValue();
            if (!invalid$iv) {
                $composer2 = $composer$iv2;
                if (value$iv != Composer.INSTANCE.getEmpty()) {
                }
                Function0 function3 = (Function0) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if ($drawerState.getTargetValue() != BottomDrawerValue.Closed) {
                    z = true;
                } else {
                    z = false;
                }
                Composer $composer5 = $composer2;
                m1809BottomDrawerScrim3JVO9M(j, function3, z, $composer5, 0);
                final String navigationMenu3 = Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1949getNavigationMenuUdPEhr4(), $composer5, 6);
                ComposerKt.sourceInformationMarkerStart($composer5, -699015966, "CC(remember):Drawer.kt#9igjgp");
                boolean isLandscape5 = $composer5.changed(fullHeight);
                invalid$iv2 = isLandscape5 | $composer5.changed(isLandscape2) | $composer5.changedInstance($drawerState);
                value$iv2 = $composer5.rememberedValue();
                if (!invalid$iv2) {
                }
                value$iv2 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda25
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$1$0($drawerState, fullHeight, isLandscape2, (IntSize) obj);
                    }
                };
                $composer5.updateRememberedValue(value$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer5);
                Modifier modifierOnSizeChanged3 = OnRemeasuredModifierKt.onSizeChanged(drawerConstraints, (Function1) value$iv2);
                ComposerKt.sourceInformationMarkerStart($composer5, -698948942, "CC(remember):Drawer.kt#9igjgp");
                invalid$iv3 = $composer5.changedInstance($drawerState);
                value$iv3 = $composer5.rememberedValue();
                if (!invalid$iv3) {
                }
                value$iv3 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda26
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$2$0($drawerState, (Density) obj);
                    }
                };
                $composer5.updateRememberedValue(value$iv3);
                ComposerKt.sourceInformationMarkerEnd($composer5);
                Modifier modifierOffset3 = OffsetKt.offset(modifierOnSizeChanged3, (Function1) value$iv3);
                ComposerKt.sourceInformationMarkerStart($composer5, -698945296, "CC(remember):Drawer.kt#9igjgp");
                invalid$iv4 = $composer5.changed(navigationMenu3) | $composer5.changedInstance($drawerState) | $composer5.changedInstance($scope);
                value$iv4 = $composer5.rememberedValue();
                if (!invalid$iv4) {
                }
                value$iv4 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda27
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$3$0(navigationMenu3, $drawerState, $scope, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer5.updateRememberedValue(value$iv4);
                ComposerKt.sourceInformationMarkerEnd($composer5);
                SurfaceKt.m1954SurfaceFjzlyU(SemanticsModifierKt.semantics$default(modifierOffset3, false, (Function1) value$iv4, 1, null), $drawerShape, j2, j3, null, f, ComposableLambdaKt.rememberComposableLambda(1691510868, true, new Function2() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda28
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$4($drawerContent, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer5, 54), $composer5, 1572864, 16);
                ComposerKt.sourceInformationMarkerEnd($composer5);
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2 = $composer$iv2;
            }
            value$iv = new Function0() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$0$0($gesturesEnabled, $drawerState, $scope);
                }
            };
            $composer$iv2.updateRememberedValue(value$iv);
            Function0 function4 = (Function0) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if ($drawerState.getTargetValue() != BottomDrawerValue.Closed) {
                z = true;
            } else {
                z = false;
            }
            Composer $composer6 = $composer2;
            m1809BottomDrawerScrim3JVO9M(j, function4, z, $composer6, 0);
            final String navigationMenu4 = Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1949getNavigationMenuUdPEhr4(), $composer6, 6);
            ComposerKt.sourceInformationMarkerStart($composer6, -699015966, "CC(remember):Drawer.kt#9igjgp");
            boolean isLandscape6 = $composer6.changed(fullHeight);
            invalid$iv2 = isLandscape6 | $composer6.changed(isLandscape2) | $composer6.changedInstance($drawerState);
            value$iv2 = $composer6.rememberedValue();
            if (!invalid$iv2) {
            }
            value$iv2 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$1$0($drawerState, fullHeight, isLandscape2, (IntSize) obj);
                }
            };
            $composer6.updateRememberedValue(value$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer6);
            Modifier modifierOnSizeChanged4 = OnRemeasuredModifierKt.onSizeChanged(drawerConstraints, (Function1) value$iv2);
            ComposerKt.sourceInformationMarkerStart($composer6, -698948942, "CC(remember):Drawer.kt#9igjgp");
            invalid$iv3 = $composer6.changedInstance($drawerState);
            value$iv3 = $composer6.rememberedValue();
            if (!invalid$iv3) {
            }
            value$iv3 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$2$0($drawerState, (Density) obj);
                }
            };
            $composer6.updateRememberedValue(value$iv3);
            ComposerKt.sourceInformationMarkerEnd($composer6);
            Modifier modifierOffset4 = OffsetKt.offset(modifierOnSizeChanged4, (Function1) value$iv3);
            ComposerKt.sourceInformationMarkerStart($composer6, -698945296, "CC(remember):Drawer.kt#9igjgp");
            invalid$iv4 = $composer6.changed(navigationMenu4) | $composer6.changedInstance($drawerState) | $composer6.changedInstance($scope);
            value$iv4 = $composer6.rememberedValue();
            if (!invalid$iv4) {
            }
            value$iv4 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$3$0(navigationMenu4, $drawerState, $scope, (SemanticsPropertyReceiver) obj);
                }
            };
            $composer6.updateRememberedValue(value$iv4);
            ComposerKt.sourceInformationMarkerEnd($composer6);
            SurfaceKt.m1954SurfaceFjzlyU(SemanticsModifierKt.semantics$default(modifierOffset4, false, (Function1) value$iv4, 1, null), $drawerShape, j2, j3, null, f, ComposableLambdaKt.rememberComposableLambda(1691510868, true, new Function2() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$4($drawerContent, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer6, 54), $composer6, 1572864, 16);
            ComposerKt.sourceInformationMarkerEnd($composer6);
            ComposerKt.sourceInformationMarkerEnd($composer$iv2);
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomDrawer_Gs3lGvM$lambda$0$1$0$0(boolean $gesturesEnabled, BottomDrawerState $drawerState, CoroutineScope $scope) {
        if ($gesturesEnabled && $drawerState.confirmStateChange$material(BottomDrawerValue.Closed)) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new DrawerKt$BottomDrawer$1$1$1$1$1($drawerState, null), 3, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomDrawer_Gs3lGvM$lambda$0$1$1$0(BottomDrawerState $drawerState, final float $fullHeight, final boolean $isLandscape, IntSize drawerSize) {
        BottomDrawerValue newTarget;
        BottomDrawerValue newTarget2;
        long arg0$iv = drawerSize.m8080unboximpl();
        final float drawerHeight = (int) (4294967295L & arg0$iv);
        DraggableAnchors<BottomDrawerValue> DraggableAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$1$0$0($fullHeight, drawerHeight, $isLandscape, (DraggableAnchorsConfig) obj);
            }
        });
        boolean hasAnchors = $drawerState.getAnchoredDraggableState$material().getAnchors().getSize() > 0;
        if (!hasAnchors && DraggableAnchors.hasAnchorFor($drawerState.getCurrentValue())) {
            newTarget = $drawerState.getCurrentValue();
        } else {
            switch (WhenMappings.$EnumSwitchMapping$0[$drawerState.getTargetValue().ordinal()]) {
                case 1:
                    newTarget = BottomDrawerValue.Closed;
                    break;
                case 2:
                case 3:
                    boolean hasHalfExpandedState = DraggableAnchors.hasAnchorFor(BottomDrawerValue.Open);
                    if (hasHalfExpandedState) {
                        newTarget2 = BottomDrawerValue.Open;
                    } else {
                        newTarget2 = DraggableAnchors.hasAnchorFor(BottomDrawerValue.Expanded) ? BottomDrawerValue.Expanded : BottomDrawerValue.Closed;
                    }
                    newTarget = newTarget2;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        $drawerState.getAnchoredDraggableState$material().updateAnchors(DraggableAnchors, newTarget);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomDrawer_Gs3lGvM$lambda$0$1$1$0$0(float $fullHeight, float $drawerHeight, boolean $isLandscape, DraggableAnchorsConfig $this$DraggableAnchors) {
        $this$DraggableAnchors.at(BottomDrawerValue.Closed, $fullHeight);
        float peekHeight = 0.5f * $fullHeight;
        if ($drawerHeight > peekHeight || $isLandscape) {
            $this$DraggableAnchors.at(BottomDrawerValue.Open, peekHeight);
        }
        if ($drawerHeight > 0.0f) {
            $this$DraggableAnchors.at(BottomDrawerValue.Expanded, Math.max(0.0f, $fullHeight - $drawerHeight));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntOffset BottomDrawer_Gs3lGvM$lambda$0$1$2$0(BottomDrawerState $drawerState, Density $this$offset) {
        int y$iv = MathKt.roundToInt($drawerState.requireOffset$material());
        return IntOffset.m8024boximpl(IntOffset.m8027constructorimpl((((long) 0) << 32) | (((long) y$iv) & 4294967295L)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomDrawer_Gs3lGvM$lambda$0$1$3$0(String $navigationMenu, final BottomDrawerState $drawerState, final CoroutineScope $scope, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setPaneTitle($this$semantics, $navigationMenu);
        if ($drawerState.isOpen()) {
            SemanticsPropertiesKt.dismiss$default($this$semantics, null, new Function0() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(DrawerKt.BottomDrawer_Gs3lGvM$lambda$0$1$3$0$0($drawerState, $scope));
                }
            }, 1, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean BottomDrawer_Gs3lGvM$lambda$0$1$3$0$0(BottomDrawerState $drawerState, CoroutineScope $scope) {
        if ($drawerState.confirmStateChange$material(BottomDrawerValue.Closed)) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new DrawerKt$BottomDrawer$1$1$4$1$1$1($drawerState, null), 3, null);
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:27:0x0141  */
    public static final Unit BottomDrawer_Gs3lGvM$lambda$0$1$4(Function3 $drawerContent, Composer $composer, int $changed) {
        Composer $composer$iv;
        int $changed$iv;
        ComposerKt.sourceInformation($composer, "C687@28426L31:Drawer.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1691510868, $changed, -1, "androidx.compose.material.BottomDrawer.<anonymous>.<anonymous>.<anonymous> (Drawer.kt:687)");
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
                $drawerContent.invoke(ColumnScopeInstance.INSTANCE, $composer$iv2, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
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
            $drawerContent.invoke(ColumnScopeInstance.INSTANCE, $composer$iv3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
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

    private static final float calculateFraction(float a, float b, float pos) {
        float $this$fastCoerceIn$iv = (pos - a) / (b - a);
        float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
            return 1.0f;
        }
        return $this$fastCoerceAtLeast$iv$iv;
    }

    /* JADX INFO: renamed from: BottomDrawerScrim-3J-VO9M, reason: not valid java name */
    private static final void m1809BottomDrawerScrim3JVO9M(final long j, final Function0<Unit> function0, final boolean visible, Composer $composer, final int $changed) {
        Modifier.Companion dismissModifier;
        Composer $composer2 = $composer.startRestartGroup(-513067266);
        ComposerKt.sourceInformation($composer2, "C(BottomDrawerScrim)N(color:c#ui.graphics.Color,onDismiss,visible):Drawer.kt#jmzs0o");
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
                ComposerKt.traceEventStart(-513067266, $dirty, -1, "androidx.compose.material.BottomDrawerScrim (Drawer.kt:725)");
            }
            if ((j != 16 ? 1 : 0) != 0) {
                $composer2.startReplaceGroup(1001814516);
                ComposerKt.sourceInformation($composer2, "728@29733L87,729@29847L30,744@30414L62,744@30361L115");
                int $dirty2 = $dirty;
                final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(visible ? 1065353216 : 0, new TweenSpec(0, 0, null, 7, null), 0.0f, null, null, $composer2, 48, 28);
                final String closeDrawer = Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1945getCloseDrawerUdPEhr4(), $composer2, 6);
                if (visible) {
                    $composer2.startReplaceGroup(1002034864);
                    ComposerKt.sourceInformation($composer2, "732@29984L37,733@30078L213");
                    Modifier.Companion companion = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, 863608995, "CC(remember):Drawer.kt#9igjgp");
                    boolean invalid$iv = ($dirty2 & 112) == 32;
                    Object it$iv = $composer2.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = (PointerInputEventHandler) new DrawerKt$BottomDrawerScrim$dismissModifier$1$1(function0);
                        $composer2.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, function0, (PointerInputEventHandler) it$iv);
                    ComposerKt.sourceInformationMarkerStart($composer2, 863612179, "CC(remember):Drawer.kt#9igjgp");
                    boolean invalid$iv2 = $composer2.changed(closeDrawer) | (($dirty2 & 112) == 32);
                    Object it$iv2 = $composer2.rememberedValue();
                    if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda19
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return DrawerKt.BottomDrawerScrim_3J_VO9M$lambda$2$0(closeDrawer, function0, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv2);
                        it$iv2 = value$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    dismissModifier = SemanticsModifierKt.semantics(modifierPointerInput, true, (Function1) it$iv2);
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(1002399548);
                    $composer2.endReplaceGroup();
                    dismissModifier = Modifier.INSTANCE;
                }
                Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissModifier);
                ComposerKt.sourceInformationMarkerStart($composer2, 863622780, "CC(remember):Drawer.kt#9igjgp");
                boolean invalid$iv3 = (($dirty2 & 14) == 4) | $composer2.changed(stateAnimateFloatAsState);
                Object it$iv3 = $composer2.rememberedValue();
                if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda20
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return DrawerKt.BottomDrawerScrim_3J_VO9M$lambda$3$0(j, stateAnimateFloatAsState, (DrawScope) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv3);
                    it$iv3 = value$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(modifierThen, (Function1) it$iv3, $composer2, 0);
            } else {
                $composer2.startReplaceGroup(972328804);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DrawerKt.BottomDrawerScrim_3J_VO9M$lambda$4(j, function0, visible, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float BottomDrawerScrim_3J_VO9M$lambda$0(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomDrawerScrim_3J_VO9M$lambda$2$0(String $closeDrawer, final Function0 $onDismiss, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContentDescription($this$semantics, $closeDrawer);
        SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(DrawerKt.BottomDrawerScrim_3J_VO9M$lambda$2$0$0($onDismiss));
            }
        }, 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean BottomDrawerScrim_3J_VO9M$lambda$2$0$0(Function0 $onDismiss) {
        $onDismiss.invoke();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomDrawerScrim_3J_VO9M$lambda$3$0(long j, State $alpha$delegate, DrawScope $this$Canvas) {
        DrawScope.m5665drawRectnJ9OG0$default($this$Canvas, j, 0L, 0L, BottomDrawerScrim_3J_VO9M$lambda$0($alpha$delegate), null, null, 0, 118, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: Scrim-Bx497Mc, reason: not valid java name */
    private static final void m1811ScrimBx497Mc(final boolean open, final Function0<Unit> function0, final Function0<Float> function1, final long j, Composer $composer, final int $changed) {
        Modifier.Companion dismissDrawer;
        Composer $composer2 = $composer.startRestartGroup(1983403750);
        ComposerKt.sourceInformation($composer2, "C(Scrim)N(open,onClose,fraction,color:c#ui.graphics.Color)752@30613L30,767@31111L39,767@31060L90:Drawer.kt#jmzs0o");
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
            $dirty |= $composer2.changed(j) ? 2048 : 1024;
        }
        if (!$composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1983403750, $dirty, -1, "androidx.compose.material.Scrim (Drawer.kt:751)");
            }
            final String closeDrawer = Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1945getCloseDrawerUdPEhr4(), $composer2, 6);
            if (open) {
                $composer2.startReplaceGroup(1716213970);
                ComposerKt.sourceInformation($composer2, "755@30731L35,756@30819L187");
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -914468439, "CC(remember):Drawer.kt#9igjgp");
                boolean invalid$iv = ($dirty & 112) == 32;
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = (PointerInputEventHandler) new DrawerKt$Scrim$dismissDrawer$1$1(function0);
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, function0, (PointerInputEventHandler) it$iv);
                ComposerKt.sourceInformationMarkerStart($composer2, -914465471, "CC(remember):Drawer.kt#9igjgp");
                boolean invalid$iv2 = $composer2.changed(closeDrawer) | (($dirty & 112) == 32);
                Object it$iv2 = $composer2.rememberedValue();
                if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda16
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return DrawerKt.Scrim_Bx497Mc$lambda$1$0(closeDrawer, function0, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                dismissDrawer = SemanticsModifierKt.semantics(modifierPointerInput, true, (Function1) it$iv2);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(1716538044);
                $composer2.endReplaceGroup();
                dismissDrawer = Modifier.INSTANCE;
            }
            Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissDrawer);
            ComposerKt.sourceInformationMarkerStart($composer2, -914456275, "CC(remember):Drawer.kt#9igjgp");
            boolean invalid$iv3 = (($dirty & 7168) == 2048) | (($dirty & 896) == 256);
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = new Function1() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DrawerKt.Scrim_Bx497Mc$lambda$2$0(j, function1, (DrawScope) obj);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DrawerKt.Scrim_Bx497Mc$lambda$3(open, function0, function1, j, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Scrim_Bx497Mc$lambda$1$0(String $closeDrawer, final Function0 $onClose, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContentDescription($this$semantics, $closeDrawer);
        SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0() { // from class: androidx.compose.material.DrawerKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(DrawerKt.Scrim_Bx497Mc$lambda$1$0$0($onClose));
            }
        }, 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean Scrim_Bx497Mc$lambda$1$0$0(Function0 $onClose) {
        $onClose.invoke();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Scrim_Bx497Mc$lambda$2$0(long j, Function0 $fraction, DrawScope $this$Canvas) {
        DrawScope.m5665drawRectnJ9OG0$default($this$Canvas, j, 0L, 0L, ((Number) $fraction.invoke()).floatValue(), null, null, 0, 118, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material.DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1, reason: invalid class name */
    /* JADX INFO: compiled from: Drawer.kt */
    @Metadata(d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u0012H\u0096@¢\u0006\u0004\b\u0013\u0010\u0014J \u0010\u0015\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u0012H\u0096@¢\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0018\u001a\u00020\u0007*\u00020\u0019H\u0002¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u0019*\u00020\u0012H\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0013\u0010\u001b\u001a\u00020\u0019*\u00020\u0007H\u0003¢\u0006\u0004\b\u001e\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u001f"}, d2 = {"androidx/compose/material/DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "onPreScroll", "Landroidx/compose/ui/geometry/Offset;", "available", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPreScroll-OzD1aCk", "(JI)J", "onPostScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostFling", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toOffset", "", "(F)J", "toFloat", "velocityToFloat", "(J)F", "offsetToFloat", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AnonymousClass1 implements NestedScrollConnection {
        final /* synthetic */ AnchoredDraggableState<?> $state;
        private final Orientation orientation = Orientation.Vertical;

        AnonymousClass1(AnchoredDraggableState<?> anchoredDraggableState) {
            this.$state = anchoredDraggableState;
        }

        public final Orientation getOrientation() {
            return this.orientation;
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
            DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1;
            if (continuation instanceof DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1) {
                drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = (DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1) continuation;
                if ((drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label & Integer.MIN_VALUE) != 0) {
                    drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label -= Integer.MIN_VALUE;
                } else {
                    drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = new DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1(this, continuation);
                }
            } else {
                drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = new DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1(this, continuation);
            }
            Object $result = drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    float toFling = velocityToFloat(j);
                    float currentOffset = this.$state.requireOffset();
                    if (toFling < 0.0f && currentOffset > this.$state.getAnchors().minAnchor()) {
                        AnchoredDraggableState<?> anchoredDraggableState = this.$state;
                        drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.J$0 = j;
                        drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label = 1;
                        if (anchoredDraggableState.settle(toFling, drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        j = Velocity.INSTANCE.m8154getZero9UxMQ8M();
                    }
                    break;
                case 1:
                    j = drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.J$0;
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
            DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1;
            if (continuation instanceof DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) {
                drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = (DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) continuation;
                if ((drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label & Integer.MIN_VALUE) != 0) {
                    drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label -= Integer.MIN_VALUE;
                } else {
                    drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = new DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1(this, continuation);
                }
            } else {
                drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = new DrawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1(this, continuation);
            }
            Object $result = drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    AnchoredDraggableState<?> anchoredDraggableState = this.$state;
                    float fVelocityToFloat = velocityToFloat(j2);
                    drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.J$0 = j2;
                    drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label = 1;
                    if (anchoredDraggableState.settle(fVelocityToFloat, drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    j2 = drawerKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.J$0;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Velocity.m8134boximpl(j2);
        }

        private final long toOffset(float $this$toOffset) {
            float x$iv = this.orientation == Orientation.Horizontal ? $this$toOffset : 0.0f;
            float y$iv = this.orientation == Orientation.Vertical ? $this$toOffset : 0.0f;
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(y$iv);
            return Offset.m4844constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
        }

        private final float velocityToFloat(long $this$toFloat) {
            return this.orientation == Orientation.Horizontal ? Velocity.m8143getXimpl($this$toFloat) : Velocity.m8144getYimpl($this$toFloat);
        }

        private final float offsetToFloat(long $this$toFloat) {
            if (this.orientation == Orientation.Horizontal) {
                int bits$iv$iv$iv = (int) ($this$toFloat >> 32);
                return Float.intBitsToFloat(bits$iv$iv$iv);
            }
            int bits$iv$iv$iv2 = (int) (4294967295L & $this$toFloat);
            return Float.intBitsToFloat(bits$iv$iv$iv2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NestedScrollConnection ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(AnchoredDraggableState<?> anchoredDraggableState) {
        return new AnonymousClass1(anchoredDraggableState);
    }
}
