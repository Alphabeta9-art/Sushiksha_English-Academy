package androidx.compose.material;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.ts.TsExtractor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Swipeable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u001aZ\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u00022\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062#\b\u0002\u0010\b\u001a\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\tH\u0007¢\u0006\u0002\u0010\u000e\u001aI\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0010\u001a\u0002H\u00022\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00120\t2\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0001¢\u0006\u0002\u0010\u0013\u001a³\u0001\u0010\u0014\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u0002*\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\r2\b\b\u0002\u0010\u001c\u001a\u00020\r2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e28\b\u0002\u0010\u001f\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(!\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020#0 2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\b\b\u0002\u0010&\u001a\u00020'H\u0007¢\u0006\u0004\b(\u0010)\u001a$\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00070+2\u0006\u0010,\u001a\u00020\u00072\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070-H\u0002\u001aP\u0010.\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00072\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070-2\u0018\u0010\u001f\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070 2\u0006\u00100\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0007H\u0002\u001a-\u00101\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u00020\u00182\u0006\u0010\u0016\u001a\u0002H\u0002H\u0002¢\u0006\u0002\u00102\"*\u00103\u001a\u000204\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00018@X\u0081\u0004¢\u0006\f\u0012\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u000e\u00109\u001a\u00020:X\u0082T¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"rememberSwipeableState", "Landroidx/compose/material/SwipeableState;", ExifInterface.GPS_DIRECTION_TRUE, "", "initialValue", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmStateChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "newValue", "", "(Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/SwipeableState;", "rememberSwipeableStateFor", "value", "onValueChange", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/SwipeableState;", "swipeable", "Landroidx/compose/ui/Modifier;", "state", "anchors", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "enabled", "reverseDirection", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "thresholds", "Lkotlin/Function2;", TypedValues.TransitionType.S_FROM, TypedValues.TransitionType.S_TO, "Landroidx/compose/material/ThresholdConfig;", "resistance", "Landroidx/compose/material/ResistanceConfig;", "velocityThreshold", "Landroidx/compose/ui/unit/Dp;", "swipeable-pPrIpRY", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material/SwipeableState;Ljava/util/Map;Landroidx/compose/foundation/gestures/Orientation;ZZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/material/ResistanceConfig;F)Landroidx/compose/ui/Modifier;", "findBounds", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "computeTarget", "lastValue", "velocity", "getOffset", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Float;", "PreUpPostDownNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "getPreUpPostDownNestedScrollConnection$annotations", "(Landroidx/compose/material/SwipeableState;)V", "getPreUpPostDownNestedScrollConnection", "(Landroidx/compose/material/SwipeableState;)Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "SwipeableDeprecation", "", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SwipeableKt {
    private static final String SwipeableDeprecation = "Material's Swipeable has been replaced by Foundation's AnchoredDraggable APIs. Please see developer.android.com for an overview of the changes and a migration guide.";

    public static /* synthetic */ void getPreUpPostDownNestedScrollConnection$annotations(SwipeableState swipeableState) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rememberSwipeableState$lambda$0$0(Object it) {
        return true;
    }

    @Deprecated(message = SwipeableDeprecation)
    public static final <T> SwipeableState<T> rememberSwipeableState(final T t, final AnimationSpec<Float> animationSpec, final Function1<? super T, Boolean> function1, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -1237755169, "C(rememberSwipeableState)N(initialValue,animationSpec,confirmStateChange)465@19542L8,473@19778L178,467@19586L370:Swipeable.kt#jmzs0o");
        if ((i & 2) != 0) {
            AnimationSpec animationSpec2 = SwipeableDefaults.INSTANCE.getAnimationSpec();
            animationSpec = animationSpec2;
        }
        if ((i & 4) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, 223228935, "CC(remember):Swipeable.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material.SwipeableKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(SwipeableKt.rememberSwipeableState$lambda$0$0(obj));
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            function1 = (Function1) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1237755169, $changed, -1, "androidx.compose.material.rememberSwipeableState (Swipeable.kt:466)");
        }
        Object[] objArr = new Object[0];
        Saver<SwipeableState<T>, T> Saver = SwipeableState.INSTANCE.Saver(animationSpec, function1);
        ComposerKt.sourceInformationMarkerStart($composer, 223236657, "CC(remember):Swipeable.kt#9igjgp");
        boolean z = true;
        boolean zChangedInstance = (((($changed & 14) ^ 6) > 4 && $composer.changedInstance(t)) || ($changed & 6) == 4) | $composer.changedInstance(animationSpec);
        if (((($changed & 896) ^ 384) <= 256 || !$composer.changed(function1)) && ($changed & 384) != 256) {
            z = false;
        }
        boolean invalid$iv = zChangedInstance | z;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function0() { // from class: androidx.compose.material.SwipeableKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SwipeableKt.rememberSwipeableState$lambda$1$0(t, animationSpec, function1);
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        SwipeableState<T> swipeableState = (SwipeableState) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) Saver, (Function0) it$iv2, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return swipeableState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SwipeableState rememberSwipeableState$lambda$1$0(Object $initialValue, AnimationSpec $animationSpec, Function1 $confirmStateChange) {
        return new SwipeableState($initialValue, $animationSpec, $confirmStateChange);
    }

    @Deprecated(message = SwipeableDeprecation)
    public static final <T> SwipeableState<T> rememberSwipeableStateFor(final T t, final Function1<? super T, Unit> function1, AnimationSpec<Float> animationSpec, Composer $composer, int $changed, int i) {
        SpringSpec<Float> animationSpec2;
        boolean z;
        ComposerKt.sourceInformationMarkerStart($composer, 1156387078, "C(rememberSwipeableStateFor)N(value,onValueChange,animationSpec)498@20818L170,505@21019L34,506@21107L113,506@21058L162,511@21271L212,511@21225L258:Swipeable.kt#jmzs0o");
        if ((i & 4) != 0) {
            animationSpec2 = SwipeableDefaults.INSTANCE.getAnimationSpec();
        } else {
            animationSpec2 = animationSpec;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1156387078, $changed, -1, "androidx.compose.material.rememberSwipeableStateFor (Swipeable.kt:497)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1108541392, "CC(remember):Swipeable.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new SwipeableState(t, animationSpec2, new Function1() { // from class: androidx.compose.material.SwipeableKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(SwipeableKt.rememberSwipeableStateFor$lambda$0$0(obj));
                }
            });
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        final SwipeableState<T> swipeableState = (SwipeableState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -1108535096, "CC(remember):Swipeable.kt#9igjgp");
        Object it$iv2 = $composer.rememberedValue();
        if (it$iv2 == Composer.INSTANCE.getEmpty()) {
            z = false;
            Object value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        } else {
            z = false;
        }
        final MutableState forceAnimationCheck = (MutableState) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        Object value = forceAnimationCheck.getValue();
        ComposerKt.sourceInformationMarkerStart($composer, -1108532201, "CC(remember):Swipeable.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) <= 4 || !$composer.changedInstance(t)) && ($changed & 6) != 4) ? z : true;
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
            Object value$iv3 = (Function2) new SwipeableKt$rememberSwipeableStateFor$1$1(t, swipeableState, null);
            $composer.updateRememberedValue(value$iv3);
            it$iv3 = value$iv3;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(t, value, (Function2) it$iv3, $composer, ($changed & 8) | ($changed & 14));
        T currentValue = swipeableState.getCurrentValue();
        ComposerKt.sourceInformationMarkerStart($composer, -1108526854, "CC(remember):Swipeable.kt#9igjgp");
        boolean invalid$iv2 = ((((($changed & 14) ^ 6) <= 4 || !$composer.changedInstance(t)) && ($changed & 6) != 4) ? z : true) | ((((($changed & 112) ^ 48) <= 32 || !$composer.changed(function1)) && ($changed & 48) != 32) ? z : true);
        Object it$iv4 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
            Object value$iv4 = new Function1() { // from class: androidx.compose.material.SwipeableKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SwipeableKt.rememberSwipeableStateFor$lambda$3$0(t, swipeableState, function1, forceAnimationCheck, (DisposableEffectScope) obj);
                }
            };
            $composer.updateRememberedValue(value$iv4);
            it$iv4 = value$iv4;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.DisposableEffect(currentValue, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv4, $composer, $changed & 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return swipeableState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rememberSwipeableStateFor$lambda$0$0(Object it) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult rememberSwipeableStateFor$lambda$3$0(Object $value, SwipeableState $swipeableState, Function1 $onValueChange, MutableState $forceAnimationCheck, DisposableEffectScope $this$DisposableEffect) {
        if (!Intrinsics.areEqual($value, $swipeableState.getCurrentValue())) {
            $onValueChange.invoke($swipeableState.getCurrentValue());
            $forceAnimationCheck.setValue(Boolean.valueOf(!((Boolean) $forceAnimationCheck.getValue()).booleanValue()));
        }
        return new DisposableEffectResult() { // from class: androidx.compose.material.SwipeableKt$rememberSwipeableStateFor$lambda$3$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
            }
        };
    }

    static final FixedThreshold swipeable_pPrIpRY$lambda$0(Object obj, Object obj2) {
        return new FixedThreshold(Dp.m7905constructorimpl(56), null);
    }

    @Deprecated(message = SwipeableDeprecation)
    /* JADX INFO: renamed from: swipeable-pPrIpRY, reason: not valid java name */
    public static final <T> Modifier m1964swipeablepPrIpRY(Modifier $this$swipeable_u2dpPrIpRY, final SwipeableState<T> swipeableState, final Map<Float, ? extends T> map, final Orientation orientation, final boolean enabled, final boolean reverseDirection, final MutableInteractionSource interactionSource, final Function2<? super T, ? super T, ? extends ThresholdConfig> function2, final ResistanceConfig resistance, final float f) {
        return ComposedModifierKt.composed($this$swipeable_u2dpPrIpRY, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material.SwipeableKt$swipeable-pPrIpRY$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $this$null) {
                $this$null.setName("swipeable");
                $this$null.getProperties().set("state", swipeableState);
                $this$null.getProperties().set("anchors", map);
                $this$null.getProperties().set("orientation", orientation);
                $this$null.getProperties().set("enabled", Boolean.valueOf(enabled));
                $this$null.getProperties().set("reverseDirection", Boolean.valueOf(reverseDirection));
                $this$null.getProperties().set("interactionSource", interactionSource);
                $this$null.getProperties().set("thresholds", function2);
                $this$null.getProperties().set("resistance", resistance);
                $this$null.getProperties().set("velocityThreshold", Dp.m7903boximpl(f));
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: androidx.compose.material.SwipeableKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return SwipeableKt.swipeable_pPrIpRY$lambda$2(map, swipeableState, resistance, function2, f, orientation, enabled, interactionSource, reverseDirection, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        });
    }

    static final Modifier swipeable_pPrIpRY$lambda$2(Map $anchors, SwipeableState $state, ResistanceConfig $resistance, Function2 $thresholds, float f, Orientation $orientation, boolean $enabled, MutableInteractionSource $interactionSource, boolean $reverseDirection, Modifier $this$composed, Composer $composer, int $changed) {
        $composer.startReplaceGroup(43594985);
        ComposerKt.sourceInformation($composer, "C589@25416L7,591@25497L495,591@25466L526,610@26281L55:Swipeable.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(43594985, $changed, -1, "androidx.compose.material.swipeable.<anonymous> (Swipeable.kt:585)");
        }
        if ($anchors.isEmpty()) {
            throw new IllegalArgumentException("You must have at least one anchor.".toString());
        }
        if (!(CollectionsKt.distinct($anchors.values()).size() == $anchors.size())) {
            throw new IllegalArgumentException("You cannot have two anchors mapped to the same state.".toString());
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Density density = (Density) objConsume;
        $state.ensureInit$material($anchors);
        ComposerKt.sourceInformationMarkerStart($composer, -1054880872, "CC(remember):Swipeable.kt#9igjgp");
        boolean invalid$iv = $composer.changed($state) | $composer.changedInstance($anchors) | $composer.changed($resistance) | $composer.changed($thresholds) | $composer.changed(density) | $composer.changed(f);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = (Function2) new SwipeableKt$swipeable$3$3$1($state, $anchors, $resistance, density, $thresholds, f, null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect($anchors, $state, (Function2) it$iv, $composer, 0);
        Modifier.Companion companion = Modifier.INSTANCE;
        boolean zIsAnimationRunning = $state.isAnimationRunning();
        DraggableState draggableState = $state.getDraggableState();
        Modifier.Companion companion2 = companion;
        ComposerKt.sourceInformationMarkerStart($composer, -1054856224, "CC(remember):Swipeable.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed($state);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = (Function3) new SwipeableKt$swipeable$3$4$1($state, null);
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierDraggable = DraggableKt.draggable(companion2, draggableState, $orientation, (TsExtractor.TS_PACKET_SIZE & 4) != 0 ? true : $enabled, (TsExtractor.TS_PACKET_SIZE & 8) != 0 ? null : $interactionSource, (TsExtractor.TS_PACKET_SIZE & 16) != 0 ? false : zIsAnimationRunning, (TsExtractor.TS_PACKET_SIZE & 32) != 0 ? DraggableKt.NoOpOnDragStarted : null, (TsExtractor.TS_PACKET_SIZE & 64) != 0 ? DraggableKt.NoOpOnDragStopped : (Function3) it$iv2, (TsExtractor.TS_PACKET_SIZE & 128) != 0 ? false : $reverseDirection);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierDraggable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Float> findBounds(float offset, Set<Float> set) {
        Object maxElem$iv;
        Set<Float> $this$filter$iv = set;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            float it = ((Number) element$iv$iv).floatValue();
            if (((double) it) <= ((double) offset) + 0.001d) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List $this$fastMaxBy$iv = (List) destination$iv$iv;
        Object minElem$iv = null;
        if (!$this$fastMaxBy$iv.isEmpty()) {
            maxElem$iv = $this$fastMaxBy$iv.get(0);
            float maxValue$iv = ((Number) maxElem$iv).floatValue();
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex($this$fastMaxBy$iv);
            if (1 <= lastIndex) {
                while (true) {
                    Object e$iv = $this$fastMaxBy$iv.get(i$iv);
                    float v$iv = ((Number) e$iv).floatValue();
                    if (Float.compare(maxValue$iv, v$iv) < 0) {
                        maxElem$iv = e$iv;
                        maxValue$iv = v$iv;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                }
            }
        } else {
            maxElem$iv = null;
        }
        Float a = (Float) maxElem$iv;
        Set<Float> $this$filter$iv2 = set;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            float it2 = ((Number) element$iv$iv2).floatValue();
            if (((double) it2) >= ((double) offset) - 0.001d) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        List $this$fastMinByOrNull$iv = (List) destination$iv$iv2;
        if (!$this$fastMinByOrNull$iv.isEmpty()) {
            minElem$iv = $this$fastMinByOrNull$iv.get(0);
            float minValue$iv = ((Number) minElem$iv).floatValue();
            int i$iv2 = 1;
            int lastIndex2 = CollectionsKt.getLastIndex($this$fastMinByOrNull$iv);
            if (1 <= lastIndex2) {
                while (true) {
                    Object e$iv2 = $this$fastMinByOrNull$iv.get(i$iv2);
                    float v$iv2 = ((Number) e$iv2).floatValue();
                    if (Float.compare(minValue$iv, v$iv2) > 0) {
                        minElem$iv = e$iv2;
                        minValue$iv = v$iv2;
                    }
                    if (i$iv2 == lastIndex2) {
                        break;
                    }
                    i$iv2++;
                }
            }
        }
        Float b = (Float) minElem$iv;
        if (a == null) {
            return CollectionsKt.listOfNotNull(b);
        }
        if (b == null) {
            return CollectionsKt.listOf(a);
        }
        return Intrinsics.areEqual(a, b) ? CollectionsKt.listOf(a) : CollectionsKt.listOf((Object[]) new Float[]{a, b});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float computeTarget(float offset, float lastValue, Set<Float> set, Function2<? super Float, ? super Float, Float> function2, float velocity, float velocityThreshold) {
        List<Float> listFindBounds = findBounds(offset, set);
        switch (listFindBounds.size()) {
            case 0:
                return lastValue;
            case 1:
                return listFindBounds.get(0).floatValue();
            default:
                float lower = listFindBounds.get(0).floatValue();
                float upper = listFindBounds.get(1).floatValue();
                if (lastValue <= offset) {
                    if (velocity >= velocityThreshold) {
                        return upper;
                    }
                    float threshold = function2.invoke(Float.valueOf(lower), Float.valueOf(upper)).floatValue();
                    if (offset < threshold) {
                        return lower;
                    }
                } else {
                    if (velocity <= (-velocityThreshold)) {
                        return lower;
                    }
                    float threshold2 = function2.invoke(Float.valueOf(upper), Float.valueOf(lower)).floatValue();
                    if (offset <= threshold2) {
                        return lower;
                    }
                }
                return upper;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> Float getOffset(Map<Float, ? extends T> map, T t) {
        Object element$iv;
        Map.Entry it;
        Iterable $this$firstOrNull$iv = map.entrySet();
        Iterator<T> it2 = $this$firstOrNull$iv.iterator();
        do {
            if (!it2.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it2.next();
            it = (Map.Entry) element$iv;
        } while (!Intrinsics.areEqual(it.getValue(), t));
        Map.Entry entry = (Map.Entry) element$iv;
        if (entry != null) {
            return (Float) entry.getKey();
        }
        return null;
    }

    public static final <T> NestedScrollConnection getPreUpPostDownNestedScrollConnection(SwipeableState<T> swipeableState) {
        return new SwipeableKt$PreUpPostDownNestedScrollConnection$1(swipeableState);
    }
}
