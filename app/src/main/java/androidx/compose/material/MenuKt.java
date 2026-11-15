package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.IntrinsicKt;
import androidx.compose.foundation.layout.IntrinsicSize;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntRect;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Menu.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\u001aY\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0001¢\u0006\u0002\u0010\u0011\u001ac\u0010\u0012\u001a\u00020\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0001¢\u0006\u0002\u0010\u001b\u001a\u001d\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0000¢\u0006\u0002\u0010/\"\u0010\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001e\"\u0016\u0010\u001f\u001a\u00020\u001dX\u0080\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b \u0010!\"\u0010\u0010\"\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001e\"\u0016\u0010#\u001a\u00020\u001dX\u0080\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b$\u0010!\"\u0010\u0010%\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001e\"\u0010\u0010&\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001e\"\u0010\u0010'\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001e\"\u000e\u0010(\u001a\u00020)X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010*\u001a\u00020)X\u0080T¢\u0006\u0002\n\u0000¨\u00060²\u0006\n\u00101\u001a\u000202X\u008a\u0084\u0002²\u0006\n\u00103\u001a\u000202X\u008a\u0084\u0002"}, d2 = {"DropdownMenuContent", "", "expandedStates", "Landroidx/compose/animation/core/MutableTransitionState;", "", "transformOriginState", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/ui/graphics/TransformOrigin;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "modifier", "Landroidx/compose/ui/Modifier;", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/runtime/MutableState;Landroidx/compose/foundation/ScrollState;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DropdownMenuItemContent", "onClick", "Lkotlin/Function0;", "enabled", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Landroidx/compose/foundation/layout/RowScope;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "MenuElevation", "Landroidx/compose/ui/unit/Dp;", "F", "MenuVerticalMargin", "getMenuVerticalMargin", "()F", "DropdownMenuItemHorizontalPadding", "DropdownMenuVerticalPadding", "getDropdownMenuVerticalPadding", "DropdownMenuItemDefaultMinWidth", "DropdownMenuItemDefaultMaxWidth", "DropdownMenuItemDefaultMinHeight", "InTransitionDuration", "", "OutTransitionDuration", "calculateTransformOrigin", "parentBounds", "Landroidx/compose/ui/unit/IntRect;", "menuBounds", "(Landroidx/compose/ui/unit/IntRect;Landroidx/compose/ui/unit/IntRect;)J", "material", "scale", "", "alpha"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class MenuKt {
    public static final int InTransitionDuration = 120;
    public static final int OutTransitionDuration = 75;
    private static final float MenuElevation = Dp.m7905constructorimpl(8);
    private static final float MenuVerticalMargin = Dp.m7905constructorimpl(48);
    private static final float DropdownMenuItemHorizontalPadding = Dp.m7905constructorimpl(16);
    private static final float DropdownMenuVerticalPadding = Dp.m7905constructorimpl(8);
    private static final float DropdownMenuItemDefaultMinWidth = Dp.m7905constructorimpl(112);
    private static final float DropdownMenuItemDefaultMaxWidth = Dp.m7905constructorimpl(280);
    private static final float DropdownMenuItemDefaultMinHeight = Dp.m7905constructorimpl(48);

    static final Unit DropdownMenuContent$lambda$8(MutableTransitionState mutableTransitionState, MutableState mutableState, ScrollState scrollState, Modifier modifier, Function3 function3, int i, int i2, Composer composer, int i3) {
        DropdownMenuContent(mutableTransitionState, mutableState, scrollState, modifier, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit DropdownMenuItemContent$lambda$1(Function0 function0, Modifier modifier, boolean z, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, Function3 function3, int i, int i2, Composer composer, int i3) {
        DropdownMenuItemContent(function0, modifier, z, paddingValues, mutableInteractionSource, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void DropdownMenuContent(final MutableTransitionState<Boolean> mutableTransitionState, final MutableState<TransformOrigin> mutableState, final ScrollState scrollState, Modifier modifier, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Composer $composer2;
        final Modifier modifier3;
        Composer $composer3 = $composer.startRestartGroup(1077393800);
        ComposerKt.sourceInformation($composer3, "C(DropdownMenuContent)N(expandedStates,transformOriginState,scrollState,modifier,content)156@7382L50,159@7470L622,180@8130L549,201@8744L173,208@8960L277,199@8684L553:Menu.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer3.changed(mutableTransitionState) : $composer3.changedInstance(mutableTransitionState) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(mutableState) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(scrollState) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 16384 : 8192;
        }
        if ($composer3.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            final Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1077393800, $dirty, -1, "androidx.compose.material.DropdownMenuContent (Menu.kt:154)");
            }
            Transition transition = TransitionKt.rememberTransition(mutableTransitionState, "DropDownMenu", $composer3, MutableTransitionState.$stable | 48 | ($dirty & 14), 0);
            Function3 transitionSpec$iv = new Function3() { // from class: androidx.compose.material.MenuKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MenuKt.DropdownMenuContent$lambda$0((Transition.Segment) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            };
            ComposerKt.sourceInformationMarkerStart($composer3, -1338768149, "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv = (0 & 14) | ((0 << 3) & 896) | ((0 << 3) & 7168) | ((0 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer3, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
            int $changed2 = ($changed$iv$iv >> 9) & 112;
            boolean it = ((Boolean) transition.getCurrentState()).booleanValue();
            $composer3.startReplaceGroup(-1833869404);
            ComposerKt.sourceInformation($composer3, "CN(it):Menu.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1833869404, $changed2, -1, "androidx.compose.material.DropdownMenuContent.<anonymous> (Menu.kt:170)");
            }
            float f = it ? 1.0f : 0.8f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer3.endReplaceGroup();
            Float fValueOf = Float.valueOf(f);
            int $changed3 = ($changed$iv$iv >> 9) & 112;
            boolean it2 = ((Boolean) transition.getTargetState()).booleanValue();
            $composer3.startReplaceGroup(-1833869404);
            ComposerKt.sourceInformation($composer3, "CN(it):Menu.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1833869404, $changed3, -1, "androidx.compose.material.DropdownMenuContent.<anonymous> (Menu.kt:170)");
            }
            float f2 = it2 ? 1.0f : 0.8f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer3.endReplaceGroup();
            Object targetValue$iv$iv = Float.valueOf(f2);
            FiniteAnimationSpec animationSpec$iv$iv = (FiniteAnimationSpec) transitionSpec$iv.invoke(transition.getSegment(), $composer3, Integer.valueOf(($changed$iv$iv >> 3) & 112));
            final State scale$delegate = TransitionKt.createTransitionAnimation(transition, fValueOf, targetValue$iv$iv, animationSpec$iv$iv, vectorConverter, "FloatAnimation", $composer3, ($changed$iv$iv & 14) | (($changed$iv$iv << 9) & 57344) | (($changed$iv$iv << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Function3 transitionSpec$iv2 = new Function3() { // from class: androidx.compose.material.MenuKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MenuKt.DropdownMenuContent$lambda$3((Transition.Segment) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            };
            ComposerKt.sourceInformationMarkerStart($composer3, -1338768149, "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv2 = (0 & 14) | ((0 << 3) & 896) | ((0 << 3) & 7168) | ((0 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer3, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
            int $changed4 = ($changed$iv$iv2 >> 9) & 112;
            boolean it3 = ((Boolean) transition.getCurrentState()).booleanValue();
            $composer3.startReplaceGroup(-1578341192);
            ComposerKt.sourceInformation($composer3, "CN(it):Menu.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1578341192, $changed4, -1, "androidx.compose.material.DropdownMenuContent.<anonymous> (Menu.kt:191)");
            }
            float f3 = it3 ? 1.0f : 0.0f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer3.endReplaceGroup();
            Object initialValue$iv$iv = Float.valueOf(f3);
            int $changed5 = ($changed$iv$iv2 >> 9) & 112;
            boolean it4 = ((Boolean) transition.getTargetState()).booleanValue();
            $composer3.startReplaceGroup(-1578341192);
            ComposerKt.sourceInformation($composer3, "CN(it):Menu.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1578341192, $changed5, -1, "androidx.compose.material.DropdownMenuContent.<anonymous> (Menu.kt:191)");
            }
            float f4 = it4 ? 1.0f : 0.0f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer3.endReplaceGroup();
            Object targetValue$iv$iv2 = Float.valueOf(f4);
            FiniteAnimationSpec animationSpec$iv$iv2 = (FiniteAnimationSpec) transitionSpec$iv2.invoke(transition.getSegment(), $composer3, Integer.valueOf(($changed$iv$iv2 >> 3) & 112));
            final State alpha$delegate = TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv, targetValue$iv$iv2, animationSpec$iv$iv2, vectorConverter2, "FloatAnimation", $composer3, ($changed$iv$iv2 & 14) | (($changed$iv$iv2 << 9) & 57344) | (($changed$iv$iv2 << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 288377077, "CC(remember):Menu.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(scale$delegate) | $composer3.changed(alpha$delegate) | (($dirty & 112) == 32);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material.MenuKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MenuKt.DropdownMenuContent$lambda$6$0(mutableState, scale$delegate, alpha$delegate, (GraphicsLayerScope) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier5 = modifier4;
            CardKt.m1738CardFjzlyU(GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) it$iv), null, 0L, 0L, null, MenuElevation, ComposableLambdaKt.rememberComposableLambda(-707086267, true, new Function2() { // from class: androidx.compose.material.MenuKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MenuKt.DropdownMenuContent$lambda$7(modifier4, scrollState, function3, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer3, 1769472, 30);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.MenuKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MenuKt.DropdownMenuContent$lambda$8(mutableTransitionState, mutableState, scrollState, modifier3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float DropdownMenuContent$lambda$2(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    static final FiniteAnimationSpec DropdownMenuContent$lambda$0(Transition.Segment $this$animateFloat, Composer $composer, int $changed) {
        TweenSpec tweenSpecTween$default;
        $composer.startReplaceGroup(445475263);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(445475263, $changed, -1, "androidx.compose.material.DropdownMenuContent.<anonymous> (Menu.kt:161)");
        }
        if ($this$animateFloat.isTransitioningTo(false, true)) {
            tweenSpecTween$default = AnimationSpecKt.tween$default(InTransitionDuration, 0, EasingKt.getLinearOutSlowInEasing(), 2, null);
        } else {
            tweenSpecTween$default = AnimationSpecKt.tween$default(1, 74, null, 4, null);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return tweenSpecTween$default;
    }

    private static final float DropdownMenuContent$lambda$5(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    static final FiniteAnimationSpec DropdownMenuContent$lambda$3(Transition.Segment $this$animateFloat, Composer $composer, int $changed) {
        $composer.startReplaceGroup(701003475);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(701003475, $changed, -1, "androidx.compose.material.DropdownMenuContent.<anonymous> (Menu.kt:182)");
        }
        TweenSpec tweenSpecTween$default = $this$animateFloat.isTransitioningTo(false, true) ? AnimationSpecKt.tween$default(30, 0, null, 6, null) : AnimationSpecKt.tween$default(75, 0, null, 6, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return tweenSpecTween$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DropdownMenuContent$lambda$6$0(MutableState $transformOriginState, State $scale$delegate, State $alpha$delegate, GraphicsLayerScope $this$graphicsLayer) {
        $this$graphicsLayer.setScaleX(DropdownMenuContent$lambda$2($scale$delegate));
        $this$graphicsLayer.setScaleY(DropdownMenuContent$lambda$2($scale$delegate));
        $this$graphicsLayer.setAlpha(DropdownMenuContent$lambda$5($alpha$delegate));
        $this$graphicsLayer.mo5298setTransformOrigin__ExYCQ(((TransformOrigin) $transformOriginState.getValue()).getPackedValue());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0161  */
    static final Unit DropdownMenuContent$lambda$7(Modifier $modifier, ScrollState $scrollState, Function3 $content, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Composer $composer$iv;
        ComposerKt.sourceInformation($composer, "C209@8970L261:Menu.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-707086267, $changed, -1, "androidx.compose.material.DropdownMenuContent.<anonymous> (Menu.kt:209)");
            }
            Modifier modifier$iv = ScrollKt.verticalScroll$default(IntrinsicKt.width(PaddingKt.m821paddingVpY3zN4$default($modifier, 0.0f, DropdownMenuVerticalPadding, 1, null), IntrinsicSize.Max), $scrollState, false, null, false, 14, null);
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
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
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                $composer$iv = $composer;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                Composer $composer$iv2 = $composer$iv;
                ComposerKt.sourceInformationMarkerStart($composer$iv2, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                $content.invoke(ColumnScopeInstance.INSTANCE, $composer$iv2, Integer.valueOf(((0 >> 6) & 112) | 6));
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
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = ($changed$iv$iv$iv >> 6) & 14;
            Composer $composer$iv3 = $composer$iv;
            ComposerKt.sourceInformationMarkerStart($composer$iv3, -384672921, "C89@4556L9:Column.kt#2w3rfo");
            $content.invoke(ColumnScopeInstance.INSTANCE, $composer$iv3, Integer.valueOf(((0 >> 6) & 112) | 6));
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

    public static final void DropdownMenuItemContent(final Function0<Unit> function0, Modifier modifier, boolean enabled, PaddingValues contentPadding, MutableInteractionSource interactionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function1;
        Modifier modifier2;
        boolean z;
        PaddingValues paddingValues;
        final MutableInteractionSource interactionSource2;
        final Modifier modifier3;
        final boolean enabled2;
        final PaddingValues contentPadding2;
        int i2;
        Modifier modifier4;
        final boolean enabled3;
        PaddingValues contentPadding3;
        MutableInteractionSource interactionSource3;
        Function0<ComposeUiNode> function2;
        Composer $composer2 = $composer.startRestartGroup(-674391690);
        ComposerKt.sourceInformation($composer2, "C(DropdownMenuItemContent)N(onClick,modifier,enabled,contentPadding,interactionSource,content)230@9650L1031:Menu.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            function1 = function0;
            $dirty |= $composer2.changedInstance(function1) ? 4 : 2;
        } else {
            function1 = function0;
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
            z = enabled;
        } else if (($changed & 384) == 0) {
            z = enabled;
            $dirty |= $composer2.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            paddingValues = contentPadding;
        } else if (($changed & 3072) == 0) {
            paddingValues = contentPadding;
            $dirty |= $composer2.changed(paddingValues) ? 2048 : 1024;
        } else {
            paddingValues = contentPadding;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 131072 : 65536;
        }
        if ($composer2.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            if (i3 != 0) {
                modifier4 = Modifier.INSTANCE;
                i2 = i5;
            } else {
                i2 = i5;
                modifier4 = modifier2;
            }
            if (i4 == 0) {
                enabled3 = z;
            } else {
                enabled3 = true;
            }
            if (i2 == 0) {
                contentPadding3 = paddingValues;
            } else {
                contentPadding3 = MenuDefaults.INSTANCE.getDropdownMenuItemContentPadding();
            }
            if (i6 == 0) {
                interactionSource3 = interactionSource;
            } else {
                interactionSource3 = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-674391690, $dirty, -1, "androidx.compose.material.DropdownMenuItemContent (Menu.kt:228)");
            }
            Modifier modifier$iv = PaddingKt.padding(SizeKt.m869sizeInqDBjuR0$default(SizeKt.fillMaxWidth$default(ClickableKt.m295clickableO2vRcR0(modifier4, interactionSource3, RippleKt.m1907rippleH2RKhps$default(true, 0.0f, 0L, 6, null), (28 & 4) != 0 ? true : enabled3, (28 & 8) != 0 ? null : null, (28 & 16) != 0 ? null : null, function1), 0.0f, 1, null), DropdownMenuItemDefaultMinWidth, DropdownMenuItemDefaultMinHeight, DropdownMenuItemDefaultMaxWidth, 0.0f, 8, null), contentPadding3);
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            PaddingValues contentPadding4 = contentPadding3;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function2 = constructor;
                $composer2.createNode(function2);
            } else {
                function2 = constructor;
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
            int i7 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            int i8 = ((384 >> 6) & 112) | 6;
            final RowScope $this$DropdownMenuItemContent_u24lambda_u240 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -575380304, "C249@10425L10,250@10483L192,250@10444L231:Menu.kt#jmzs0o");
            Typography typography = MaterialTheme.INSTANCE.getTypography($composer2, 6);
            TextKt.ProvideTextStyle(typography.getSubtitle1(), ComposableLambdaKt.rememberComposableLambda(-77738101, true, new Function2() { // from class: androidx.compose.material.MenuKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MenuKt.DropdownMenuItemContent$lambda$0$0(enabled3, function3, $this$DropdownMenuItemContent_u24lambda_u240, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, 48);
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
            interactionSource2 = interactionSource3;
            enabled2 = enabled3;
            contentPadding2 = contentPadding4;
        } else {
            $composer2.skipToGroupEnd();
            interactionSource2 = interactionSource;
            modifier3 = modifier2;
            enabled2 = z;
            contentPadding2 = paddingValues;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.MenuKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MenuKt.DropdownMenuItemContent$lambda$1(function0, modifier3, enabled2, contentPadding2, interactionSource2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DropdownMenuItemContent$lambda$0$0(boolean $enabled, final Function3 $content, final RowScope $this_Row, Composer $composer, int $changed) {
        float contentAlpha;
        ComposerKt.sourceInformation($composer, "C252@10652L13,252@10586L79:Menu.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-77738101, $changed, -1, "androidx.compose.material.DropdownMenuItemContent.<anonymous>.<anonymous> (Menu.kt:251)");
            }
            if ($enabled) {
                $composer.startReplaceGroup(-1691869137);
                ComposerKt.sourceInformation($composer, "251@10542L4");
                contentAlpha = ContentAlpha.INSTANCE.getHigh($composer, 6);
            } else {
                $composer.startReplaceGroup(-1691868397);
                ComposerKt.sourceInformation($composer, "251@10565L8");
                contentAlpha = ContentAlpha.INSTANCE.getDisabled($composer, 6);
            }
            $composer.endReplaceGroup();
            CompositionLocalKt.CompositionLocalProvider(ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(contentAlpha)), ComposableLambdaKt.rememberComposableLambda(-308149173, true, new Function2() { // from class: androidx.compose.material.MenuKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MenuKt.DropdownMenuItemContent$lambda$0$0$0($content, $this_Row, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DropdownMenuItemContent$lambda$0$0$0(Function3 $content, RowScope $this_Row, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C252@10654L9:Menu.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-308149173, $changed, -1, "androidx.compose.material.DropdownMenuItemContent.<anonymous>.<anonymous>.<anonymous> (Menu.kt:252)");
            }
            $content.invoke($this_Row, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static final float getMenuVerticalMargin() {
        return MenuVerticalMargin;
    }

    public static final float getDropdownMenuVerticalPadding() {
        return DropdownMenuVerticalPadding;
    }

    public static final long calculateTransformOrigin(IntRect parentBounds, IntRect menuBounds) {
        float pivotX;
        float pivotY = 1.0f;
        if (menuBounds.getLeft() >= parentBounds.getRight()) {
            pivotX = 0.0f;
        } else if (menuBounds.getRight() <= parentBounds.getLeft()) {
            pivotX = 1.0f;
        } else if (menuBounds.getWidth() == 0) {
            pivotX = 0.0f;
        } else {
            int intersectionCenter = (Math.max(parentBounds.getLeft(), menuBounds.getLeft()) + Math.min(parentBounds.getRight(), menuBounds.getRight())) / 2;
            pivotX = (intersectionCenter - menuBounds.getLeft()) / menuBounds.getWidth();
        }
        if (menuBounds.getTop() >= parentBounds.getBottom()) {
            pivotY = 0.0f;
        } else if (menuBounds.getBottom() > parentBounds.getTop()) {
            if (menuBounds.getHeight() == 0) {
                pivotY = 0.0f;
            } else {
                int intersectionCenter2 = (Math.max(parentBounds.getTop(), menuBounds.getTop()) + Math.min(parentBounds.getBottom(), menuBounds.getBottom())) / 2;
                pivotY = (intersectionCenter2 - menuBounds.getTop()) / menuBounds.getHeight();
            }
        }
        return TransformOriginKt.TransformOrigin(pivotX, pivotY);
    }
}
