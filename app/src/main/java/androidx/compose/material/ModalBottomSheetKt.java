package androidx.compose.material;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
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
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ModalBottomSheet.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aE\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\u000b\u001a\u0090\u0001\u0010\f\u001a\u00020\r2\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\r0\b¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00012\b\b\u0002\u0010\u0015\u001a\u00020\t2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u001b2\u0011\u0010\u001e\u001a\r\u0012\u0004\u0012\u00020\r0\u001f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0004\b \u0010!\u001a\u0014\u0010\"\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0001H\u0002\u001a-\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u001b2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\r0\u001f2\u0006\u0010&\u001a\u00020\tH\u0003¢\u0006\u0004\b'\u0010(\u001a\u001c\u0010)\u001a\u00020*2\n\u0010+\u001a\u0006\u0012\u0002\b\u00030,2\u0006\u0010-\u001a\u00020.H\u0002\"\u0010\u0010/\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u00101\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u00102\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u00100¨\u00063²\u0006\n\u00104\u001a\u00020\u0006X\u008a\u0084\u0002"}, d2 = {"rememberModalBottomSheetState", "Landroidx/compose/material/ModalBottomSheetState;", "initialValue", "Landroidx/compose/material/ModalBottomSheetValue;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmValueChange", "Lkotlin/Function1;", "", "skipHalfExpanded", "(Landroidx/compose/material/ModalBottomSheetValue;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ModalBottomSheetState;", "ModalBottomSheetLayout", "", "sheetContent", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "sheetState", "sheetGesturesEnabled", "sheetShape", "Landroidx/compose/ui/graphics/Shape;", "sheetElevation", "Landroidx/compose/ui/unit/Dp;", "sheetBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "sheetContentColor", "scrimColor", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function0;", "ModalBottomSheetLayout-Gs3lGvM", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material/ModalBottomSheetState;ZLandroidx/compose/ui/graphics/Shape;FJJJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "modalBottomSheetAnchors", "Scrim", "color", "onDismiss", "visible", "Scrim-3J-VO9M", "(JLkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "state", "Landroidx/compose/material/AnchoredDraggableState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "ModalBottomSheetPositionalThreshold", "F", "ModalBottomSheetVelocityThreshold", "MaxModalBottomSheetWidth", "material", "alpha"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ModalBottomSheetKt {
    private static final float ModalBottomSheetPositionalThreshold = Dp.m7905constructorimpl(56);
    private static final float ModalBottomSheetVelocityThreshold = Dp.m7905constructorimpl(ComposerImplKt.nodeKey);
    private static final float MaxModalBottomSheetWidth = Dp.m7905constructorimpl(640);

    /* JADX INFO: compiled from: ModalBottomSheet.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ModalBottomSheetValue.values().length];
            try {
                iArr[ModalBottomSheetValue.Hidden.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ModalBottomSheetValue.HalfExpanded.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ModalBottomSheetValue.Expanded.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit ModalBottomSheetLayout_Gs3lGvM$lambda$1(Function3 function3, Modifier modifier, ModalBottomSheetState modalBottomSheetState, boolean z, Shape shape, float f, long j, long j2, long j3, Function2 function2, int i, int i2, Composer composer, int i3) {
        m1864ModalBottomSheetLayoutGs3lGvM(function3, modifier, modalBottomSheetState, z, shape, f, j, j2, j3, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Scrim_3J_VO9M$lambda$4(long j, Function0 function0, boolean z, int i, Composer composer, int i2) {
        m1865Scrim3JVO9M(j, function0, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rememberModalBottomSheetState$lambda$0$0(ModalBottomSheetValue it) {
        return true;
    }

    public static final ModalBottomSheetState rememberModalBottomSheetState(final ModalBottomSheetValue initialValue, AnimationSpec<Float> animationSpec, Function1<? super ModalBottomSheetValue, Boolean> function1, boolean skipHalfExpanded, Composer $composer, int $changed, int i) {
        Function1<? super ModalBottomSheetValue, Boolean> function2;
        ComposerKt.sourceInformationMarkerStart($composer, -126412120, "C(rememberModalBottomSheetState)N(initialValue,animationSpec,confirmValueChange,skipHalfExpanded)275@11458L8,278@11565L7:ModalBottomSheet.kt#jmzs0o");
        final AnimationSpec<Float> animationSpec2 = (i & 2) != 0 ? ModalBottomSheetDefaults.INSTANCE.getAnimationSpec() : animationSpec;
        if ((i & 4) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, -1222956016, "CC(remember):ModalBottomSheet.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(ModalBottomSheetKt.rememberModalBottomSheetState$lambda$0$0((ModalBottomSheetValue) obj));
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            function2 = (Function1) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
        } else {
            function2 = function1;
        }
        final boolean skipHalfExpanded2 = (i & 8) != 0 ? false : skipHalfExpanded;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-126412120, $changed, -1, "androidx.compose.material.rememberModalBottomSheetState (ModalBottomSheet.kt:277)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Density density = (Density) objConsume;
        $composer.startMovableGroup(-1222944377, initialValue);
        ComposerKt.sourceInformation($composer, "296@12260L299,283@11826L733");
        Object[] objArr = {initialValue, animationSpec2, Boolean.valueOf(skipHalfExpanded2), function2, density};
        Saver<ModalBottomSheetState, ?> Saver = ModalBottomSheetState.INSTANCE.Saver(animationSpec2, function2, skipHalfExpanded2, density);
        ComposerKt.sourceInformationMarkerStart($composer, -1222930061, "CC(remember):ModalBottomSheet.kt#9igjgp");
        boolean z = true;
        boolean zChanged = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialValue.ordinal())) || ($changed & 6) == 4) | $composer.changed(density) | (((($changed & 896) ^ 384) > 256 && $composer.changed(function2)) || ($changed & 384) == 256) | $composer.changedInstance(animationSpec2);
        if (((($changed & 7168) ^ 3072) <= 2048 || !$composer.changed(skipHalfExpanded2)) && ($changed & 3072) != 2048) {
            z = false;
        }
        boolean invalid$iv = z | zChanged;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            final Function1<? super ModalBottomSheetValue, Boolean> function3 = function2;
            Object value$iv2 = new Function0() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ModalBottomSheetKt.rememberModalBottomSheetState$lambda$1$0(initialValue, density, function3, animationSpec2, skipHalfExpanded2);
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        ModalBottomSheetState modalBottomSheetState = (ModalBottomSheetState) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) Saver, (Function0) it$iv2, $composer, 0);
        $composer.endMovableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return modalBottomSheetState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ModalBottomSheetState rememberModalBottomSheetState$lambda$1$0(ModalBottomSheetValue $initialValue, Density $density, Function1 $confirmValueChange, AnimationSpec $animationSpec, boolean $skipHalfExpanded) {
        return new ModalBottomSheetState($initialValue, $density, $confirmValueChange, $animationSpec, $skipHalfExpanded);
    }

    /* JADX WARN: Code duplicated, block: B:207:0x0536  */
    /* JADX WARN: Code duplicated, block: B:208:0x0538  */
    /* JADX WARN: Code duplicated, block: B:211:0x057c  */
    /* JADX WARN: Code duplicated, block: B:219:0x05d2  */
    /* JADX WARN: Code duplicated, block: B:225:0x05ff  */
    /* JADX WARN: Code duplicated, block: B:228:0x0615  */
    /* JADX WARN: Code duplicated, block: B:233:0x0647  */
    /* JADX WARN: Code duplicated, block: B:235:0x0665  */
    /* JADX WARN: Code duplicated, block: B:238:0x06cd  */
    /* JADX INFO: renamed from: ModalBottomSheetLayout-Gs3lGvM, reason: not valid java name */
    public static final void m1864ModalBottomSheetLayoutGs3lGvM(final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, ModalBottomSheetState sheetState, boolean sheetGesturesEnabled, Shape sheetShape, float f, long j, long j2, long j3, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        boolean z;
        Shape shape;
        float f2;
        long j4;
        int i2;
        final Modifier modifier2;
        final ModalBottomSheetState sheetState2;
        final Shape sheetShape2;
        final float f3;
        final long j5;
        final boolean sheetGesturesEnabled2;
        final long j6;
        final long j7;
        int $dirty;
        int i3;
        final ModalBottomSheetState sheetState3;
        boolean sheetGesturesEnabled3;
        CornerBasedShape sheetShape3;
        float fM1858getElevationD9Ej5fM;
        long jM1769getSurface0d7_KjU;
        long jM1783contentColorForek8zF_U;
        long scrimColor;
        Shape sheetShape4;
        float f4;
        long j8;
        long j9;
        int $dirty2;
        Modifier modifier3;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Composer $composer2;
        boolean z2;
        Modifier.Companion companionNestedScroll$default;
        boolean z3;
        Modifier.Companion companionSemantics$default;
        boolean invalid$iv;
        Object it$iv;
        boolean invalid$iv2;
        Object it$iv2;
        Composer $composer3 = $composer.startRestartGroup(-336264970);
        ComposerKt.sourceInformation($composer3, "C(ModalBottomSheetLayout)N(sheetContent,modifier,sheetState,sheetGesturesEnabled,sheetShape,sheetElevation:c#ui.unit.Dp,sheetBackgroundColor:c#ui.graphics.Color,sheetContentColor:c#ui.graphics.Color,scrimColor:c#ui.graphics.Color,content)353@15034L24,355@15106L3927:ModalBottomSheet.kt#jmzs0o");
        int $dirty3 = $changed;
        if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changedInstance(function3) ? 4 : 2;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty3 |= ((i & 4) == 0 && $composer3.changedInstance(sheetState)) ? 256 : 128;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty3 |= 3072;
            z = sheetGesturesEnabled;
        } else if (($changed & 3072) == 0) {
            z = sheetGesturesEnabled;
            $dirty3 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = sheetGesturesEnabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape = sheetShape;
                int i6 = $composer3.changed(shape) ? 16384 : 8192;
                $dirty3 |= i6;
            } else {
                shape = sheetShape;
            }
            $dirty3 |= i6;
        } else {
            shape = sheetShape;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                f2 = f;
                int i7 = $composer3.changed(f2) ? 131072 : 65536;
                $dirty3 |= i7;
            } else {
                f2 = f;
            }
            $dirty3 |= i7;
        } else {
            f2 = f;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                j4 = j;
                int i8 = $composer3.changed(j4) ? 1048576 : 524288;
                $dirty3 |= i8;
            } else {
                j4 = j;
            }
            $dirty3 |= i8;
        } else {
            j4 = j;
        }
        if ((12582912 & $changed) == 0) {
            $dirty3 |= ((i & 128) == 0 && $composer3.changed(j2)) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            if ((i & 256) == 0) {
                i2 = i4;
                int i9 = $composer3.changed(j3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                $dirty3 |= i9;
            } else {
                i2 = i4;
            }
            $dirty3 |= i9;
        } else {
            i2 = i4;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 536870912 : 268435456;
        }
        if ($composer3.shouldExecute(($dirty3 & 306783379) != 306783378, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "344@14588L37,346@14707L6,348@14830L6,349@14877L37,350@14965L10");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier;
                if ((i & 4) != 0) {
                    int $dirty4 = $dirty3;
                    i3 = 0;
                    sheetState3 = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, null, null, false, $composer3, 6, 14);
                    $dirty = $dirty4 & (-897);
                } else {
                    $dirty = $dirty3;
                    i3 = 0;
                    sheetState3 = sheetState;
                }
                sheetGesturesEnabled3 = i5 != 0 ? true : z;
                if ((i & 16) != 0) {
                    sheetShape3 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                    $dirty &= -57345;
                } else {
                    sheetShape3 = sheetShape;
                }
                if ((i & 32) != 0) {
                    fM1858getElevationD9Ej5fM = ModalBottomSheetDefaults.INSTANCE.m1858getElevationD9Ej5fM();
                    $dirty &= -458753;
                } else {
                    fM1858getElevationD9Ej5fM = f;
                }
                if ((i & 64) != 0) {
                    jM1769getSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer3, 6).m1769getSurface0d7_KjU();
                    $dirty &= -3670017;
                } else {
                    jM1769getSurface0d7_KjU = j4;
                }
                if ((i & 128) != 0) {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1769getSurface0d7_KjU, $composer3, ($dirty >> 18) & 14);
                    $dirty &= -29360129;
                } else {
                    jM1783contentColorForek8zF_U = j2;
                }
                if ((i & 256) != 0) {
                    scrimColor = ModalBottomSheetDefaults.INSTANCE.getScrimColor($composer3, i3);
                    sheetShape4 = sheetShape3;
                    f4 = fM1858getElevationD9Ej5fM;
                    j8 = jM1769getSurface0d7_KjU;
                    j9 = jM1783contentColorForek8zF_U;
                    $dirty2 = $dirty & (-234881025);
                    modifier3 = modifier4;
                } else {
                    scrimColor = j3;
                    sheetShape4 = sheetShape3;
                    f4 = fM1858getElevationD9Ej5fM;
                    j8 = jM1769getSurface0d7_KjU;
                    j9 = jM1783contentColorForek8zF_U;
                    $dirty2 = $dirty;
                    modifier3 = modifier4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                }
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty3 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty3 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty3 &= -234881025;
                }
                modifier3 = modifier;
                j9 = j2;
                i3 = 0;
                sheetShape4 = shape;
                $composer3 = $composer3;
                f4 = f2;
                j8 = j4;
                sheetGesturesEnabled3 = z;
                scrimColor = j3;
                $dirty2 = $dirty3;
                sheetState3 = sheetState;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-336264970, $dirty2, -1, "androidx.compose.material.ModalBottomSheetLayout (ModalBottomSheet.kt:352)");
            }
            Composer $composer$iv = $composer3;
            ComposerKt.sourceInformationMarkerStart($composer$iv, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer$iv, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object value$iv$iv = $composer$iv.rememberedValue();
            Modifier modifier5 = modifier3;
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer$iv);
                $composer$iv.updateRememberedValue(value$iv$iv);
            }
            final CoroutineScope scope = (CoroutineScope) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            Orientation orientation = Orientation.Vertical;
            int $changed$iv = ($dirty2 >> 3) & 14;
            Composer $composer$iv2 = $composer3;
            boolean sheetGesturesEnabled4 = sheetGesturesEnabled3;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            int $dirty5 = $dirty2;
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = ($changed$iv << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer$iv2, 0);
            CompositionLocalMap localMap$iv$iv = $composer$iv2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer$iv2, modifier5);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer$iv2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer$iv2.startReusableNode();
            if ($composer$iv2.getInserting()) {
                function0 = constructor;
                $composer$iv2.createNode(function0);
            } else {
                function0 = constructor;
                $composer$iv2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer$iv2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i10 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            int i11 = (($changed$iv >> 6) & 112) | 6;
            BoxScope $this$ModalBottomSheetLayout_Gs3lGvM_u24lambda_u240 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, 350779258, "C356@15130L431,437@18973L54,368@15570L3457:ModalBottomSheet.kt#jmzs0o");
            Modifier modifier$iv = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer$iv2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
            int $changed$iv$iv2 = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer$iv2, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer$iv2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer$iv2, modifier$iv);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
            long j10 = scrimColor;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer$iv2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer$iv2.startReusableNode();
            if ($composer$iv2.getInserting()) {
                function1 = constructor2;
                $composer$iv2.createNode(function1);
            } else {
                function1 = constructor2;
                $composer$iv2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer$iv2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            int i12 = ($changed$iv$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i13 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, -1832269826, "C357@15172L9,360@15265L188,358@15194L357:ModalBottomSheet.kt#jmzs0o");
            function2.invoke($composer$iv2, Integer.valueOf(($dirty5 >> 27) & 14));
            ComposerKt.sourceInformationMarkerStart($composer$iv2, -751839354, "CC(remember):ModalBottomSheet.kt#9igjgp");
            boolean invalid$iv3 = $composer3.changedInstance(sheetState3) | $composer3.changedInstance(scope);
            Object value$iv = $composer$iv2.rememberedValue();
            if (invalid$iv3) {
                $composer2 = $composer$iv2;
            } else {
                $composer2 = $composer$iv2;
                if (value$iv != Composer.INSTANCE.getEmpty()) {
                }
                Function0 function4 = (Function0) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (sheetState3.getAnchoredDraggableState$material().getTargetValue() != ModalBottomSheetValue.Hidden) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                m1865Scrim3JVO9M(j10, function4, z2, $composer2, ($dirty5 >> 24) & 14);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                $composer$iv2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SizeKt.m872widthInVpY3zN4$default($this$ModalBottomSheetLayout_Gs3lGvM_u24lambda_u240.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0.0f, MaxModalBottomSheetWidth, 1, null), 0.0f, 1, null);
                if (sheetGesturesEnabled4) {
                    $composer$iv2.startReplaceGroup(351375666);
                    ComposerKt.sourceInformation($composer$iv2, "375@15915L355");
                    Modifier.Companion companion = Modifier.INSTANCE;
                    AnchoredDraggableState<ModalBottomSheetValue> anchoredDraggableState$material = sheetState3.getAnchoredDraggableState$material();
                    ComposerKt.sourceInformationMarkerStart($composer$iv2, 1258263027, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    invalid$iv2 = $composer$iv2.changed(anchoredDraggableState$material);
                    it$iv2 = $composer$iv2.rememberedValue();
                    if (!invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(sheetState3.getAnchoredDraggableState$material(), orientation);
                        $composer$iv2.updateRememberedValue(value$iv2);
                        it$iv2 = value$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    companionNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(companion, (NestedScrollConnection) it$iv2, null, 2, null);
                    $composer$iv2.endReplaceGroup();
                } else {
                    $composer$iv2.startReplaceGroup(1258275768);
                    $composer$iv2.endReplaceGroup();
                    companionNestedScroll$default = Modifier.INSTANCE;
                }
                Modifier modifierModalBottomSheetAnchors = modalBottomSheetAnchors(modifierFillMaxWidth$default.then(companionNestedScroll$default), sheetState3);
                AnchoredDraggableState<ModalBottomSheetValue> anchoredDraggableState$material2 = sheetState3.getAnchoredDraggableState$material();
                if (sheetGesturesEnabled4 || sheetState3.getAnchoredDraggableState$material().getCurrentValue() == ModalBottomSheetValue.Hidden) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                Modifier modifierAnchoredDraggable = AnchoredDraggableKt.anchoredDraggable(modifierModalBottomSheetAnchors, anchoredDraggableState$material2, orientation, (48 & 4) != 0 ? true : z3, (48 & 8) != 0 ? false : false, (48 & 16) != 0 ? null : null, (48 & 32) != 0 ? anchoredDraggableState$material2.isAnimationRunning() : false);
                if (sheetGesturesEnabled4) {
                    $composer$iv2.startReplaceGroup(352377090);
                    ComposerKt.sourceInformation($composer$iv2, "394@16846L1901");
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer$iv2, 1258294365, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    invalid$iv = $composer3.changedInstance(sheetState3) | $composer3.changedInstance(scope);
                    it$iv = $composer$iv2.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv3 = new Function1() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda8
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return ModalBottomSheetKt.ModalBottomSheetLayout_Gs3lGvM$lambda$0$2$0(sheetState3, scope, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer$iv2.updateRememberedValue(value$iv3);
                        it$iv = value$iv3;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    companionSemantics$default = SemanticsModifierKt.semantics$default(companion2, false, (Function1) it$iv, 1, null);
                    $composer$iv2.endReplaceGroup();
                } else {
                    $composer3 = $composer3;
                    $composer$iv2.startReplaceGroup(1258354200);
                    $composer$iv2.endReplaceGroup();
                    companionSemantics$default = Modifier.INSTANCE;
                }
                SurfaceKt.m1954SurfaceFjzlyU(modifierAnchoredDraggable.then(companionSemantics$default), sheetShape4, j8, j9, null, f4, ComposableLambdaKt.rememberComposableLambda(-1557535116, true, new Function2() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ModalBottomSheetKt.ModalBottomSheetLayout_Gs3lGvM$lambda$0$3(function3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer$iv2, 54), $composer$iv2, (($dirty5 >> 9) & 112) | 1572864 | (($dirty5 >> 12) & 896) | (($dirty5 >> 12) & 7168) | ($dirty5 & 458752), 16);
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                $composer$iv2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                sheetGesturesEnabled2 = sheetGesturesEnabled4;
                sheetState2 = sheetState3;
                sheetShape2 = sheetShape4;
                j5 = j8;
                j6 = j9;
                f3 = f4;
                j7 = j10;
                modifier2 = modifier5;
            }
            value$iv = new Function0() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ModalBottomSheetKt.ModalBottomSheetLayout_Gs3lGvM$lambda$0$0$0$0(sheetState3, scope);
                }
            };
            $composer$iv2.updateRememberedValue(value$iv);
            Function0 function5 = (Function0) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (sheetState3.getAnchoredDraggableState$material().getTargetValue() != ModalBottomSheetValue.Hidden) {
                z2 = true;
            } else {
                z2 = false;
            }
            m1865Scrim3JVO9M(j10, function5, z2, $composer2, ($dirty5 >> 24) & 14);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer$iv2);
            $composer$iv2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer$iv2);
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(SizeKt.m872widthInVpY3zN4$default($this$ModalBottomSheetLayout_Gs3lGvM_u24lambda_u240.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0.0f, MaxModalBottomSheetWidth, 1, null), 0.0f, 1, null);
            if (sheetGesturesEnabled4) {
                $composer$iv2.startReplaceGroup(351375666);
                ComposerKt.sourceInformation($composer$iv2, "375@15915L355");
                Modifier.Companion companion3 = Modifier.INSTANCE;
                AnchoredDraggableState<ModalBottomSheetValue> anchoredDraggableState$material3 = sheetState3.getAnchoredDraggableState$material();
                ComposerKt.sourceInformationMarkerStart($composer$iv2, 1258263027, "CC(remember):ModalBottomSheet.kt#9igjgp");
                invalid$iv2 = $composer$iv2.changed(anchoredDraggableState$material3);
                it$iv2 = $composer$iv2.rememberedValue();
                if (!invalid$iv2) {
                }
                Object value$iv4 = ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(sheetState3.getAnchoredDraggableState$material(), orientation);
                $composer$iv2.updateRememberedValue(value$iv4);
                it$iv2 = value$iv4;
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                companionNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(companion3, (NestedScrollConnection) it$iv2, null, 2, null);
                $composer$iv2.endReplaceGroup();
            } else {
                $composer$iv2.startReplaceGroup(1258275768);
                $composer$iv2.endReplaceGroup();
                companionNestedScroll$default = Modifier.INSTANCE;
            }
            Modifier modifierModalBottomSheetAnchors2 = modalBottomSheetAnchors(modifierFillMaxWidth$default2.then(companionNestedScroll$default), sheetState3);
            AnchoredDraggableState<ModalBottomSheetValue> anchoredDraggableState$material4 = sheetState3.getAnchoredDraggableState$material();
            if (sheetGesturesEnabled4) {
                z3 = false;
            } else {
                z3 = false;
            }
            Modifier modifierAnchoredDraggable2 = AnchoredDraggableKt.anchoredDraggable(modifierModalBottomSheetAnchors2, anchoredDraggableState$material4, orientation, (48 & 4) != 0 ? true : z3, (48 & 8) != 0 ? false : false, (48 & 16) != 0 ? null : null, (48 & 32) != 0 ? anchoredDraggableState$material4.isAnimationRunning() : false);
            if (sheetGesturesEnabled4) {
                $composer$iv2.startReplaceGroup(352377090);
                ComposerKt.sourceInformation($composer$iv2, "394@16846L1901");
                Modifier.Companion companion4 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer$iv2, 1258294365, "CC(remember):ModalBottomSheet.kt#9igjgp");
                invalid$iv = $composer3.changedInstance(sheetState3) | $composer3.changedInstance(scope);
                it$iv = $composer$iv2.rememberedValue();
                if (invalid$iv) {
                    Object value$iv5 = new Function1() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ModalBottomSheetKt.ModalBottomSheetLayout_Gs3lGvM$lambda$0$2$0(sheetState3, scope, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer$iv2.updateRememberedValue(value$iv5);
                    it$iv = value$iv5;
                } else {
                    Object value$iv6 = new Function1() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ModalBottomSheetKt.ModalBottomSheetLayout_Gs3lGvM$lambda$0$2$0(sheetState3, scope, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer$iv2.updateRememberedValue(value$iv6);
                    it$iv = value$iv6;
                }
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                companionSemantics$default = SemanticsModifierKt.semantics$default(companion4, false, (Function1) it$iv, 1, null);
                $composer$iv2.endReplaceGroup();
            } else {
                $composer3 = $composer3;
                $composer$iv2.startReplaceGroup(1258354200);
                $composer$iv2.endReplaceGroup();
                companionSemantics$default = Modifier.INSTANCE;
            }
            SurfaceKt.m1954SurfaceFjzlyU(modifierAnchoredDraggable2.then(companionSemantics$default), sheetShape4, j8, j9, null, f4, ComposableLambdaKt.rememberComposableLambda(-1557535116, true, new Function2() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ModalBottomSheetKt.ModalBottomSheetLayout_Gs3lGvM$lambda$0$3(function3, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer$iv2, 54), $composer$iv2, (($dirty5 >> 9) & 112) | 1572864 | (($dirty5 >> 12) & 896) | (($dirty5 >> 12) & 7168) | ($dirty5 & 458752), 16);
            ComposerKt.sourceInformationMarkerEnd($composer$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer$iv2);
            $composer$iv2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer$iv2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            sheetGesturesEnabled2 = sheetGesturesEnabled4;
            sheetState2 = sheetState3;
            sheetShape2 = sheetShape4;
            j5 = j8;
            j6 = j9;
            f3 = f4;
            j7 = j10;
            modifier2 = modifier5;
        } else {
            $composer3 = $composer3;
            $composer3.skipToGroupEnd();
            modifier2 = modifier;
            sheetState2 = sheetState;
            sheetShape2 = sheetShape;
            f3 = f;
            j5 = j4;
            sheetGesturesEnabled2 = z;
            j6 = j2;
            j7 = j3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ModalBottomSheetKt.ModalBottomSheetLayout_Gs3lGvM$lambda$1(function3, modifier2, sheetState2, sheetGesturesEnabled2, sheetShape2, f3, j5, j6, j7, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ModalBottomSheetLayout_Gs3lGvM$lambda$0$0$0$0(ModalBottomSheetState $sheetState, CoroutineScope $scope) {
        if ($sheetState.getAnchoredDraggableState$material().getConfirmValueChange$material().invoke(ModalBottomSheetValue.Hidden).booleanValue()) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new ModalBottomSheetKt$ModalBottomSheetLayout$1$1$1$1$1($sheetState, null), 3, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ModalBottomSheetLayout_Gs3lGvM$lambda$0$2$0(final ModalBottomSheetState $sheetState, final CoroutineScope $scope, SemanticsPropertyReceiver $this$semantics) {
        if ($sheetState.isVisible()) {
            SemanticsPropertiesKt.dismiss$default($this$semantics, null, new Function0() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(ModalBottomSheetKt.ModalBottomSheetLayout_Gs3lGvM$lambda$0$2$0$0($sheetState, $scope));
                }
            }, 1, null);
            if ($sheetState.getAnchoredDraggableState$material().getCurrentValue() == ModalBottomSheetValue.HalfExpanded) {
                SemanticsPropertiesKt.expand$default($this$semantics, null, new Function0() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(ModalBottomSheetKt.ModalBottomSheetLayout_Gs3lGvM$lambda$0$2$0$1($sheetState, $scope));
                    }
                }, 1, null);
            } else if ($sheetState.getHasHalfExpandedState$material()) {
                SemanticsPropertiesKt.collapse$default($this$semantics, null, new Function0() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(ModalBottomSheetKt.ModalBottomSheetLayout_Gs3lGvM$lambda$0$2$0$2($sheetState, $scope));
                    }
                }, 1, null);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ModalBottomSheetLayout_Gs3lGvM$lambda$0$2$0$0(ModalBottomSheetState $sheetState, CoroutineScope $scope) {
        if ($sheetState.getAnchoredDraggableState$material().getConfirmValueChange$material().invoke(ModalBottomSheetValue.Hidden).booleanValue()) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new ModalBottomSheetKt$ModalBottomSheetLayout$1$3$1$1$1($sheetState, null), 3, null);
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ModalBottomSheetLayout_Gs3lGvM$lambda$0$2$0$1(ModalBottomSheetState $sheetState, CoroutineScope $scope) {
        if ($sheetState.getAnchoredDraggableState$material().getConfirmValueChange$material().invoke(ModalBottomSheetValue.Expanded).booleanValue()) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new ModalBottomSheetKt$ModalBottomSheetLayout$1$3$1$2$1($sheetState, null), 3, null);
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ModalBottomSheetLayout_Gs3lGvM$lambda$0$2$0$2(ModalBottomSheetState $sheetState, CoroutineScope $scope) {
        if ($sheetState.getAnchoredDraggableState$material().getConfirmValueChange$material().invoke(ModalBottomSheetValue.HalfExpanded).booleanValue()) {
            BuildersKt__Builders_commonKt.launch$default($scope, null, null, new ModalBottomSheetKt$ModalBottomSheetLayout$1$3$1$3$1($sheetState, null), 3, null);
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:27:0x0141  */
    public static final Unit ModalBottomSheetLayout_Gs3lGvM$lambda$0$3(Function3 $sheetContent, Composer $composer, int $changed) {
        Composer $composer$iv;
        int $changed$iv;
        ComposerKt.sourceInformation($composer, "C438@18987L30:ModalBottomSheet.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1557535116, $changed, -1, "androidx.compose.material.ModalBottomSheetLayout.<anonymous>.<anonymous> (ModalBottomSheet.kt:438)");
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
                $sheetContent.invoke(ColumnScopeInstance.INSTANCE, $composer$iv2, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
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
            $sheetContent.invoke(ColumnScopeInstance.INSTANCE, $composer$iv3, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
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

    private static final Modifier modalBottomSheetAnchors(Modifier $this$modalBottomSheetAnchors, final ModalBottomSheetState sheetState) {
        return AnchoredDraggableKt.draggableAnchors($this$modalBottomSheetAnchors, sheetState.getAnchoredDraggableState$material(), Orientation.Vertical, new Function2() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ModalBottomSheetKt.modalBottomSheetAnchors$lambda$0(sheetState, (IntSize) obj, (Constraints) obj2);
            }
        });
    }

    static final Pair modalBottomSheetAnchors$lambda$0(final ModalBottomSheetState $sheetState, final IntSize sheetSize, Constraints constraints) {
        ModalBottomSheetValue newTarget;
        final float fullHeight = Constraints.m7857getMaxHeightimpl(constraints.getValue());
        DraggableAnchors newAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ModalBottomSheetKt.modalBottomSheetAnchors$lambda$0$0(fullHeight, $sheetState, sheetSize, (DraggableAnchorsConfig) obj);
            }
        });
        boolean isInitialized = $sheetState.getAnchoredDraggableState$material().getAnchors().getSize() > 0;
        ModalBottomSheetValue previousValue = $sheetState.getCurrentValue();
        if (!isInitialized && newAnchors.hasAnchorFor(previousValue)) {
            newTarget = previousValue;
        } else {
            switch (WhenMappings.$EnumSwitchMapping$0[$sheetState.getTargetValue().ordinal()]) {
                case 1:
                    newTarget = ModalBottomSheetValue.Hidden;
                    break;
                case 2:
                case 3:
                    boolean hasHalfExpandedState = newAnchors.hasAnchorFor(ModalBottomSheetValue.HalfExpanded);
                    if (hasHalfExpandedState) {
                        newTarget = ModalBottomSheetValue.HalfExpanded;
                    } else if (newAnchors.hasAnchorFor(ModalBottomSheetValue.Expanded)) {
                        newTarget = ModalBottomSheetValue.Expanded;
                    } else {
                        newTarget = ModalBottomSheetValue.Hidden;
                    }
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        return TuplesKt.to(newAnchors, newTarget);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit modalBottomSheetAnchors$lambda$0$0(float $fullHeight, ModalBottomSheetState $sheetState, IntSize $sheetSize, DraggableAnchorsConfig $this$DraggableAnchors) {
        $this$DraggableAnchors.at(ModalBottomSheetValue.Hidden, $fullHeight);
        float halfHeight = $fullHeight / 2.0f;
        if (!$sheetState.getIsSkipHalfExpanded()) {
            long arg0$iv = $sheetSize.m8080unboximpl();
            if (((int) (arg0$iv & 4294967295L)) > halfHeight) {
                $this$DraggableAnchors.at(ModalBottomSheetValue.HalfExpanded, halfHeight);
            }
        }
        long arg0$iv2 = $sheetSize.m8080unboximpl();
        if (((int) (arg0$iv2 & 4294967295L)) != 0) {
            ModalBottomSheetValue modalBottomSheetValue = ModalBottomSheetValue.Expanded;
            long arg0$iv3 = $sheetSize.m8080unboximpl();
            $this$DraggableAnchors.at(modalBottomSheetValue, Math.max(0.0f, $fullHeight - ((int) (4294967295L & arg0$iv3))));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: Scrim-3J-VO9M, reason: not valid java name */
    private static final void m1865Scrim3JVO9M(final long j, final Function0<Unit> function0, final boolean visible, Composer $composer, final int $changed) {
        Modifier.Companion dismissModifier;
        Composer $composer2 = $composer.startRestartGroup(-526532668);
        ComposerKt.sourceInformation($composer2, "C(Scrim)N(color:c#ui.graphics.Color,onDismiss,visible):ModalBottomSheet.kt#jmzs0o");
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
                ComposerKt.traceEventStart(-526532668, $dirty, -1, "androidx.compose.material.Scrim (ModalBottomSheet.kt:489)");
            }
            if ((j != 16 ? 1 : 0) != 0) {
                $composer2.startReplaceGroup(-714029408);
                ComposerKt.sourceInformation($composer2, "492@21083L87,493@21196L29,508@21761L79,508@21708L132");
                int $dirty2 = $dirty;
                final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(visible ? 1065353216 : 0, new TweenSpec(0, 0, null, 7, null), 0.0f, null, null, $composer2, 48, 28);
                final String closeSheet = Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1946getCloseSheetUdPEhr4(), $composer2, 6);
                if (visible) {
                    $composer2.startReplaceGroup(-713811509);
                    ComposerKt.sourceInformation($composer2, "496@21332L37,497@21426L212");
                    Modifier.Companion companion = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, 808259113, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    boolean invalid$iv = ($dirty2 & 112) == 32;
                    Object it$iv = $composer2.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = (PointerInputEventHandler) new ModalBottomSheetKt$Scrim$dismissModifier$1$1(function0);
                        $composer2.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, function0, (PointerInputEventHandler) it$iv);
                    ComposerKt.sourceInformationMarkerStart($composer2, 808262296, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    boolean invalid$iv2 = $composer2.changed(closeSheet) | (($dirty2 & 112) == 32);
                    Object it$iv2 = $composer2.rememberedValue();
                    if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = new Function1() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return ModalBottomSheetKt.Scrim_3J_VO9M$lambda$2$0(closeSheet, function0, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv2);
                        it$iv2 = value$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    dismissModifier = SemanticsModifierKt.semantics(modifierPointerInput, true, (Function1) it$iv2);
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(-713447786);
                    $composer2.endReplaceGroup();
                    dismissModifier = Modifier.INSTANCE;
                }
                Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissModifier);
                ComposerKt.sourceInformationMarkerStart($composer2, 808272883, "CC(remember):ModalBottomSheet.kt#9igjgp");
                boolean invalid$iv3 = (($dirty2 & 14) == 4) | $composer2.changed(stateAnimateFloatAsState);
                Object it$iv3 = $composer2.rememberedValue();
                if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = new Function1() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ModalBottomSheetKt.Scrim_3J_VO9M$lambda$3$0(j, stateAnimateFloatAsState, (DrawScope) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv3);
                    it$iv3 = value$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(modifierThen, (Function1) it$iv3, $composer2, 0);
            } else {
                $composer2.startReplaceGroup(-734934754);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ModalBottomSheetKt.Scrim_3J_VO9M$lambda$4(j, function0, visible, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float Scrim_3J_VO9M$lambda$0(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Scrim_3J_VO9M$lambda$2$0(String $closeSheet, final Function0 $onDismiss, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContentDescription($this$semantics, $closeSheet);
        SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0() { // from class: androidx.compose.material.ModalBottomSheetKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(ModalBottomSheetKt.Scrim_3J_VO9M$lambda$2$0$0($onDismiss));
            }
        }, 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean Scrim_3J_VO9M$lambda$2$0$0(Function0 $onDismiss) {
        $onDismiss.invoke();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Scrim_3J_VO9M$lambda$3$0(long j, State $alpha$delegate, DrawScope $this$Canvas) {
        DrawScope.m5665drawRectnJ9OG0$default($this$Canvas, j, 0L, 0L, RangesKt.coerceIn(Scrim_3J_VO9M$lambda$0($alpha$delegate), 0.0f, 1.0f), null, null, 0, 118, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material.ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1, reason: invalid class name */
    /* JADX INFO: compiled from: ModalBottomSheet.kt */
    @Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ'\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000eH\u0096@¢\u0006\u0004\b\u000f\u0010\u0010J \u0010\u0011\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000eH\u0096@¢\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0014\u001a\u00020\u0003*\u00020\u0015H\u0002¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u0015*\u00020\u000eH\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u0017\u001a\u00020\u0015*\u00020\u0003H\u0003¢\u0006\u0004\b\u001a\u0010\u0019¨\u0006\u001b"}, d2 = {"androidx/compose/material/ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "onPreScroll", "Landroidx/compose/ui/geometry/Offset;", "available", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPreScroll-OzD1aCk", "(JI)J", "onPostScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostFling", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toOffset", "", "(F)J", "toFloat", "velocityToFloat", "(J)F", "offsetToFloat", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
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
            ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1;
            if (continuation instanceof ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1) {
                modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = (ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1) continuation;
                if ((modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label & Integer.MIN_VALUE) != 0) {
                    modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label -= Integer.MIN_VALUE;
                } else {
                    modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = new ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1(this, continuation);
                }
            } else {
                modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1 = new ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1(this, continuation);
            }
            Object $result = modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    float toFling = velocityToFloat(j);
                    float currentOffset = this.$state.requireOffset();
                    if (toFling < 0.0f && currentOffset > this.$state.getAnchors().minAnchor()) {
                        AnchoredDraggableState<?> anchoredDraggableState = this.$state;
                        modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.J$0 = j;
                        modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.label = 1;
                        if (anchoredDraggableState.settle(toFling, modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        j = Velocity.INSTANCE.m8154getZero9UxMQ8M();
                    }
                    break;
                case 1:
                    j = modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPreFling$1.J$0;
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
            ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1;
            if (continuation instanceof ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) {
                modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = (ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) continuation;
                if ((modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label & Integer.MIN_VALUE) != 0) {
                    modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label -= Integer.MIN_VALUE;
                } else {
                    modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = new ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1(this, continuation);
                }
            } else {
                modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1 = new ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1(this, continuation);
            }
            Object $result = modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    AnchoredDraggableState<?> anchoredDraggableState = this.$state;
                    float fVelocityToFloat = velocityToFloat(j2);
                    modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.J$0 = j2;
                    modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.label = 1;
                    if (anchoredDraggableState.settle(fVelocityToFloat, modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    j2 = modalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1$onPostFling$1.J$0;
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
