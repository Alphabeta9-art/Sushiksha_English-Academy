package androidx.compose.material;

import androidx.autofill.HintConstants;
import androidx.compose.animation.ColorVectorConverterKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.FloatCompanionObject;

/* JADX INFO: compiled from: TextFieldImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\b\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J¯\u0001\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t0\f¢\u0006\u0002\b\r2\u0006\u0010\u000e\u001a\u00020\u000f2e\u0010\u0010\u001aa\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00050\u0011¢\u0006\u0002\b\rH\u0007¢\u0006\u0004\b\u0019\u0010\u001a¨\u0006\u001b²\u0006\n\u0010\u0015\u001a\u00020\u0012X\u008a\u0084\u0002²\u0006\n\u0010\u0018\u001a\u00020\u0012X\u008a\u0084\u0002²\u0006\n\u0010\u0016\u001a\u00020\tX\u008a\u0084\u0002²\u0006\n\u0010\u0017\u001a\u00020\tX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material/TextFieldTransitionScope;", "", "<init>", "()V", "Transition", "", "inputState", "Landroidx/compose/material/InputPhase;", "focusedTextStyleColor", "Landroidx/compose/ui/graphics/Color;", "unfocusedTextStyleColor", "contentColor", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composable;", "showLabel", "", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function4;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "labelProgress", "labelTextStyleColor", "labelContentColor", "placeholderOpacity", "Transition-DTcfvLk", "(Landroidx/compose/material/InputPhase;JJLkotlin/jvm/functions/Function3;ZLkotlin/jvm/functions/Function6;Landroidx/compose/runtime/Composer;I)V", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class TextFieldTransitionScope {
    public static final TextFieldTransitionScope INSTANCE = new TextFieldTransitionScope();

    /* JADX INFO: compiled from: TextFieldImpl.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[InputPhase.values().length];
            try {
                iArr[InputPhase.Focused.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[InputPhase.UnfocusedEmpty.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[InputPhase.UnfocusedNotEmpty.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit Transition_DTcfvLk$lambda$11(TextFieldTransitionScope textFieldTransitionScope, InputPhase inputPhase, long j, long j2, Function3 function3, boolean z, Function6 function6, int i, Composer composer, int i2) {
        textFieldTransitionScope.m2010TransitionDTcfvLk(inputPhase, j, j2, function3, z, function6, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private TextFieldTransitionScope() {
    }

    /* JADX WARN: Code duplicated, block: B:164:0x05e9  */
    /* JADX INFO: renamed from: Transition-DTcfvLk, reason: not valid java name */
    public final void m2010TransitionDTcfvLk(final InputPhase inputState, final long j, final long j2, final Function3<? super InputPhase, ? super Composer, ? super Integer, Color> function3, final boolean showLabel, final Function6<? super Float, ? super Color, ? super Color, ? super Float, ? super Composer, ? super Integer, Unit> function6, Composer $composer, final int $changed) {
        long j3;
        Function6<? super Float, ? super Color, ? super Color, ? super Float, ? super Composer, ? super Integer, Unit> function7;
        Composer $composer2;
        float f;
        float f2;
        float f3;
        float f4;
        State labelTextStyleColor$delegate;
        Composer $composer3 = $composer.startRestartGroup(509439888);
        ComposerKt.sourceInformation($composer3, "C(Transition)N(inputState,focusedTextStyleColor:c#ui.graphics.Color,unfocusedTextStyleColor:c#ui.graphics.Color,contentColor,showLabel,content)287@11617L59,290@11730L362,302@12151L1237,332@13448L332,343@13838L203,349@14051L82:TextFieldImpl.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(inputState.ordinal()) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(j) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            j3 = j2;
            $dirty |= $composer3.changed(j3) ? 256 : 128;
        } else {
            j3 = j2;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(showLabel) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            function7 = function6;
            $dirty |= $composer3.changedInstance(function7) ? 131072 : 65536;
        } else {
            function7 = function6;
        }
        if ($composer3.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(509439888, $dirty, -1, "androidx.compose.material.TextFieldTransitionScope.Transition (TextFieldImpl.kt:283)");
            }
            Transition transition = TransitionKt.updateTransition(inputState, "TextFieldInputState", $composer3, ($dirty & 14) | 48, 0);
            Function3 transitionSpec$iv = new Function3() { // from class: androidx.compose.material.TextFieldTransitionScope$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TextFieldTransitionScope.Transition_DTcfvLk$lambda$0((Transition.Segment) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            };
            ComposerKt.sourceInformationMarkerStart($composer3, -1338768149, "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv = (384 & 14) | ((384 << 3) & 896) | ((384 << 3) & 7168) | ((384 << 3) & 57344);
            $composer2 = $composer3;
            ComposerKt.sourceInformationMarkerStart($composer3, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
            int $changed2 = ($changed$iv$iv >> 9) & 112;
            InputPhase it = (InputPhase) transition.getCurrentState();
            $composer3.startReplaceGroup(389927550);
            ComposerKt.sourceInformation($composer3, "CN(it):TextFieldImpl.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(389927550, $changed2, -1, "androidx.compose.material.TextFieldTransitionScope.Transition.<anonymous> (TextFieldImpl.kt:294)");
            }
            switch (WhenMappings.$EnumSwitchMapping$0[it.ordinal()]) {
                case 1:
                    f = 1.0f;
                    break;
                case 2:
                    f = 0.0f;
                    break;
                case 3:
                    f = 1.0f;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer3.endReplaceGroup();
            Object initialValue$iv$iv = Float.valueOf(f);
            int $changed3 = ($changed$iv$iv >> 9) & 112;
            InputPhase inputPhase = (InputPhase) transition.getTargetState();
            $composer3.startReplaceGroup(389927550);
            ComposerKt.sourceInformation($composer3, "CN(it):TextFieldImpl.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(389927550, $changed3, -1, "androidx.compose.material.TextFieldTransitionScope.Transition.<anonymous> (TextFieldImpl.kt:294)");
            }
            switch (WhenMappings.$EnumSwitchMapping$0[inputPhase.ordinal()]) {
                case 1:
                    f2 = 1.0f;
                    break;
                case 2:
                    f2 = 0.0f;
                    break;
                case 3:
                    f2 = 1.0f;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer3.endReplaceGroup();
            Object targetValue$iv$iv = Float.valueOf(f2);
            FiniteAnimationSpec animationSpec$iv$iv = (FiniteAnimationSpec) transitionSpec$iv.invoke(transition.getSegment(), $composer3, Integer.valueOf(($changed$iv$iv >> 3) & 112));
            State labelProgress$delegate = TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv, targetValue$iv$iv, animationSpec$iv$iv, vectorConverter, "LabelProgress", $composer3, ($changed$iv$iv & 14) | (($changed$iv$iv << 9) & 57344) | (($changed$iv$iv << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Function3 transitionSpec$iv2 = new Function3() { // from class: androidx.compose.material.TextFieldTransitionScope$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TextFieldTransitionScope.Transition_DTcfvLk$lambda$3((Transition.Segment) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            };
            ComposerKt.sourceInformationMarkerStart($composer2, -1338768149, "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv2 = (384 & 14) | ((384 << 3) & 896) | ((384 << 3) & 7168) | ((384 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer2, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
            Object currentState = transition.getCurrentState();
            int $changed4 = ($changed$iv$iv2 >> 9) & 112;
            InputPhase it2 = (InputPhase) currentState;
            $composer2.startReplaceGroup(1246942589);
            ComposerKt.sourceInformation($composer2, "CN(it):TextFieldImpl.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1246942589, $changed4, -1, "androidx.compose.material.TextFieldTransitionScope.Transition.<anonymous> (TextFieldImpl.kt:324)");
            }
            switch (WhenMappings.$EnumSwitchMapping$0[it2.ordinal()]) {
                case 1:
                    f3 = 1.0f;
                    break;
                case 2:
                    f3 = !showLabel ? 1.0f : 0.0f;
                    break;
                case 3:
                    f3 = 0.0f;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer2.endReplaceGroup();
            Object initialValue$iv$iv2 = Float.valueOf(f3);
            int $changed5 = ($changed$iv$iv2 >> 9) & 112;
            InputPhase inputPhase2 = (InputPhase) transition.getTargetState();
            $composer2.startReplaceGroup(1246942589);
            ComposerKt.sourceInformation($composer2, "CN(it):TextFieldImpl.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1246942589, $changed5, -1, "androidx.compose.material.TextFieldTransitionScope.Transition.<anonymous> (TextFieldImpl.kt:324)");
            }
            switch (WhenMappings.$EnumSwitchMapping$0[inputPhase2.ordinal()]) {
                case 1:
                    f4 = 1.0f;
                    break;
                case 2:
                    f4 = !showLabel ? 1.0f : 0.0f;
                    break;
                case 3:
                    f4 = 0.0f;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer2.endReplaceGroup();
            Object targetValue$iv$iv2 = Float.valueOf(f4);
            FiniteAnimationSpec animationSpec$iv$iv2 = (FiniteAnimationSpec) transitionSpec$iv2.invoke(transition.getSegment(), $composer2, Integer.valueOf(($changed$iv$iv2 >> 3) & 112));
            State placeholderOpacity$delegate = TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv2, targetValue$iv$iv2, animationSpec$iv$iv2, vectorConverter2, "PlaceholderOpacity", $composer2, ($changed$iv$iv2 & 14) | (($changed$iv$iv2 << 9) & 57344) | (($changed$iv$iv2 << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Function3 transitionSpec$iv$iv = new Function3() { // from class: androidx.compose.material.TextFieldTransitionScope$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TextFieldTransitionScope.Transition_DTcfvLk$lambda$6((Transition.Segment) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            };
            ComposerKt.sourceInformationMarkerStart($composer2, -1939694975, "CC(animateColor)P(2)67@3229L31,68@3296L58,70@3367L70:Transition.kt#xbi5r1");
            int $changed6 = (384 >> 6) & 112;
            InputPhase it3 = (InputPhase) transition.getTargetState();
            $composer2.startReplaceGroup(-2001931362);
            ComposerKt.sourceInformation($composer2, "CN(it):TextFieldImpl.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2001931362, $changed6, -1, "androidx.compose.material.TextFieldTransitionScope.Transition.<anonymous> (TextFieldImpl.kt:336)");
            }
            long j4 = WhenMappings.$EnumSwitchMapping$0[it3.ordinal()] == 1 ? j : j3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer2.endReplaceGroup();
            ColorSpace colorSpace$iv = Color.m5101getColorSpaceimpl(j4);
            ComposerKt.sourceInformationMarkerStart($composer2, 1918408359, "CC(remember):Transition.kt#9igjgp");
            boolean invalid$iv$iv = $composer2.changed(colorSpace$iv);
            Object it$iv$iv = $composer2.rememberedValue();
            if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(colorSpace$iv);
                $composer2.updateRememberedValue(value$iv$iv);
                it$iv$iv = value$iv$iv;
            }
            TwoWayConverter typeConverter$iv = (TwoWayConverter) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            int $changed$iv$iv3 = (384 & 14) | ((384 << 3) & 896) | ((384 << 3) & 7168) | ((384 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer2, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
            int $changed7 = ($changed$iv$iv3 >> 9) & 112;
            InputPhase it4 = (InputPhase) transition.getCurrentState();
            $composer2.startReplaceGroup(-2001931362);
            ComposerKt.sourceInformation($composer2, "CN(it):TextFieldImpl.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2001931362, $changed7, -1, "androidx.compose.material.TextFieldTransitionScope.Transition.<anonymous> (TextFieldImpl.kt:336)");
            }
            long j5 = WhenMappings.$EnumSwitchMapping$0[it4.ordinal()] == 1 ? j : j3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer2.endReplaceGroup();
            Object initialValue$iv$iv3 = Color.m5087boximpl(j5);
            int $changed8 = ($changed$iv$iv3 >> 9) & 112;
            InputPhase it5 = (InputPhase) transition.getTargetState();
            $composer2.startReplaceGroup(-2001931362);
            ComposerKt.sourceInformation($composer2, "CN(it):TextFieldImpl.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2001931362, $changed8, -1, "androidx.compose.material.TextFieldTransitionScope.Transition.<anonymous> (TextFieldImpl.kt:336)");
            }
            long j6 = WhenMappings.$EnumSwitchMapping$0[it5.ordinal()] == 1 ? j : j3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer2.endReplaceGroup();
            Object targetValue$iv$iv3 = Color.m5087boximpl(j6);
            FiniteAnimationSpec animationSpec$iv$iv3 = (FiniteAnimationSpec) transitionSpec$iv$iv.invoke(transition.getSegment(), $composer2, Integer.valueOf(($changed$iv$iv3 >> 3) & 112));
            State labelTextStyleColor$delegate2 = TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv3, targetValue$iv$iv3, animationSpec$iv$iv3, typeConverter$iv, "LabelTextStyleColor", $composer2, ($changed$iv$iv3 & 14) | (($changed$iv$iv3 << 9) & 57344) | (($changed$iv$iv3 << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Function3 transitionSpec$iv3 = new Function3() { // from class: androidx.compose.material.TextFieldTransitionScope$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TextFieldTransitionScope.Transition_DTcfvLk$lambda$9((Transition.Segment) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            };
            int $changed$iv = ($dirty & 7168) | 384;
            ComposerKt.sourceInformationMarkerStart($composer2, -1939694975, "CC(animateColor)P(2)67@3229L31,68@3296L58,70@3367L70:Transition.kt#xbi5r1");
            ColorSpace colorSpace$iv2 = Color.m5101getColorSpaceimpl(function3.invoke(transition.getTargetState(), $composer2, Integer.valueOf(($changed$iv >> 6) & 112)).m5107unboximpl());
            ComposerKt.sourceInformationMarkerStart($composer2, 1918408359, "CC(remember):Transition.kt#9igjgp");
            boolean invalid$iv$iv2 = $composer2.changed(colorSpace$iv2);
            Object value$iv$iv2 = $composer2.rememberedValue();
            if (invalid$iv$iv2) {
                labelTextStyleColor$delegate = labelTextStyleColor$delegate2;
            } else {
                labelTextStyleColor$delegate = labelTextStyleColor$delegate2;
                if (value$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                }
                TwoWayConverter typeConverter$iv2 = (TwoWayConverter) value$iv$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                int $changed$iv$iv4 = ($changed$iv & 14) | (($changed$iv << 3) & 896) | (($changed$iv << 3) & 7168) | (($changed$iv << 3) & 57344);
                ComposerKt.sourceInformationMarkerStart($composer2, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
                Object initialValue$iv$iv4 = function3.invoke(transition.getCurrentState(), $composer2, Integer.valueOf(($changed$iv$iv4 >> 9) & 112));
                Object targetValue$iv$iv4 = function3.invoke(transition.getTargetState(), $composer2, Integer.valueOf(($changed$iv$iv4 >> 9) & 112));
                FiniteAnimationSpec animationSpec$iv$iv4 = (FiniteAnimationSpec) transitionSpec$iv3.invoke(transition.getSegment(), $composer2, Integer.valueOf(($changed$iv$iv4 >> 3) & 112));
                State labelContentColor$delegate = TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv4, targetValue$iv$iv4, animationSpec$iv$iv4, typeConverter$iv2, "LabelContentColor", $composer2, ($changed$iv$iv4 & 14) | (($changed$iv$iv4 << 9) & 57344) | (($changed$iv$iv4 << 6) & 458752));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                function7.invoke(Float.valueOf(Transition_DTcfvLk$lambda$2(labelProgress$delegate)), Color.m5087boximpl(Transition_DTcfvLk$lambda$8(labelTextStyleColor$delegate)), Color.m5087boximpl(Transition_DTcfvLk$lambda$10(labelContentColor$delegate)), Float.valueOf(Transition_DTcfvLk$lambda$5(placeholderOpacity$delegate)), $composer2, Integer.valueOf(($dirty >> 3) & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            value$iv$iv2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(colorSpace$iv2);
            $composer2.updateRememberedValue(value$iv$iv2);
            TwoWayConverter typeConverter$iv3 = (TwoWayConverter) value$iv$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            int $changed$iv$iv5 = ($changed$iv & 14) | (($changed$iv << 3) & 896) | (($changed$iv << 3) & 7168) | (($changed$iv << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer2, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
            Object initialValue$iv$iv5 = function3.invoke(transition.getCurrentState(), $composer2, Integer.valueOf(($changed$iv$iv5 >> 9) & 112));
            Object targetValue$iv$iv5 = function3.invoke(transition.getTargetState(), $composer2, Integer.valueOf(($changed$iv$iv5 >> 9) & 112));
            FiniteAnimationSpec animationSpec$iv$iv5 = (FiniteAnimationSpec) transitionSpec$iv3.invoke(transition.getSegment(), $composer2, Integer.valueOf(($changed$iv$iv5 >> 3) & 112));
            State labelContentColor$delegate2 = TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv5, targetValue$iv$iv5, animationSpec$iv$iv5, typeConverter$iv3, "LabelContentColor", $composer2, ($changed$iv$iv5 & 14) | (($changed$iv$iv5 << 9) & 57344) | (($changed$iv$iv5 << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            function7.invoke(Float.valueOf(Transition_DTcfvLk$lambda$2(labelProgress$delegate)), Color.m5087boximpl(Transition_DTcfvLk$lambda$8(labelTextStyleColor$delegate)), Color.m5087boximpl(Transition_DTcfvLk$lambda$10(labelContentColor$delegate2)), Float.valueOf(Transition_DTcfvLk$lambda$5(placeholderOpacity$delegate)), $composer2, Integer.valueOf(($dirty >> 3) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextFieldTransitionScope$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldTransitionScope.Transition_DTcfvLk$lambda$11(this.f$0, inputState, j, j2, function3, showLabel, function6, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float Transition_DTcfvLk$lambda$2(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    static final FiniteAnimationSpec Transition_DTcfvLk$lambda$0(Transition.Segment $this$animateFloat, Composer $composer, int $changed) {
        $composer.startReplaceGroup(-883519390);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-883519390, $changed, -1, "androidx.compose.material.TextFieldTransitionScope.Transition.<anonymous> (TextFieldImpl.kt:292)");
        }
        TweenSpec tweenSpecTween$default = AnimationSpecKt.tween$default(TextFieldImplKt.AnimationDuration, 0, null, 6, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return tweenSpecTween$default;
    }

    private static final float Transition_DTcfvLk$lambda$5(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    static final FiniteAnimationSpec Transition_DTcfvLk$lambda$3(Transition.Segment $this$animateFloat, Composer $composer, int $changed) {
        TweenSpec tweenSpecTween;
        $composer.startReplaceGroup(1849239065);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1849239065, $changed, -1, "androidx.compose.material.TextFieldTransitionScope.Transition.<anonymous> (TextFieldImpl.kt:305)");
        }
        if ($this$animateFloat.isTransitioningTo(InputPhase.Focused, InputPhase.UnfocusedEmpty)) {
            tweenSpecTween = AnimationSpecKt.tween$default(67, 0, EasingKt.getLinearEasing(), 2, null);
        } else if ($this$animateFloat.isTransitioningTo(InputPhase.UnfocusedEmpty, InputPhase.Focused) || $this$animateFloat.isTransitioningTo(InputPhase.UnfocusedNotEmpty, InputPhase.UnfocusedEmpty)) {
            tweenSpecTween = AnimationSpecKt.tween(83, 67, EasingKt.getLinearEasing());
        } else {
            tweenSpecTween = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return tweenSpecTween;
    }

    private static final long Transition_DTcfvLk$lambda$8(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m5107unboximpl();
    }

    static final FiniteAnimationSpec Transition_DTcfvLk$lambda$6(Transition.Segment $this$animateColor, Composer $composer, int $changed) {
        $composer.startReplaceGroup(-2017811095);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2017811095, $changed, -1, "androidx.compose.material.TextFieldTransitionScope.Transition.<anonymous> (TextFieldImpl.kt:333)");
        }
        TweenSpec tweenSpecTween$default = AnimationSpecKt.tween$default(TextFieldImplKt.AnimationDuration, 0, null, 6, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return tweenSpecTween$default;
    }

    private static final long Transition_DTcfvLk$lambda$10(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m5107unboximpl();
    }

    static final FiniteAnimationSpec Transition_DTcfvLk$lambda$9(Transition.Segment $this$animateColor, Composer $composer, int $changed) {
        $composer.startReplaceGroup(-1176639650);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1176639650, $changed, -1, "androidx.compose.material.TextFieldTransitionScope.Transition.<anonymous> (TextFieldImpl.kt:344)");
        }
        TweenSpec tweenSpecTween$default = AnimationSpecKt.tween$default(TextFieldImplKt.AnimationDuration, 0, null, 6, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return tweenSpecTween$default;
    }
}
