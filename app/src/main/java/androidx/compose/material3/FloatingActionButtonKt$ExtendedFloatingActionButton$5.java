package androidx.compose.material3;

import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;

/* JADX INFO: compiled from: FloatingActionButton.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class FloatingActionButtonKt$ExtendedFloatingActionButton$5 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ float $endPadding;
    final /* synthetic */ boolean $expanded;
    final /* synthetic */ Function2<Composer, Integer, Unit> $icon;
    final /* synthetic */ float $iconPadding;
    final /* synthetic */ float $minHeight;
    final /* synthetic */ float $minWidth;
    final /* synthetic */ float $startPadding;
    final /* synthetic */ Function2<Composer, Integer, Unit> $text;

    /* JADX WARN: Multi-variable type inference failed */
    FloatingActionButtonKt$ExtendedFloatingActionButton$5(boolean z, float f, float f2, float f3, float f4, Function2<? super Composer, ? super Integer, Unit> function2, float f5, Function2<? super Composer, ? super Integer, Unit> function3) {
        this.$expanded = z;
        this.$minWidth = f;
        this.$minHeight = f2;
        this.$startPadding = f3;
        this.$endPadding = f4;
        this.$icon = function2;
        this.$iconPadding = f5;
        this.$text = function3;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    static final MeasureResult invoke$lambda$4$lambda$3(float $minWidth, State $expandedWidthProgress, MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        int expandedWidth = measurable.maxIntrinsicWidth(Constraints.m7857getMaxHeightimpl(constraints.getValue()));
        int width = MathHelpersKt.lerp($this$layout.mo400roundToPx0680j_4($minWidth), expandedWidth, ((Number) $expandedWidthProgress.getValue()).floatValue());
        final Placeable placeable = measurable.mo6541measureBRTryo0(constraints.getValue());
        return MeasureScope.layout$default($this$layout, width, placeable.getHeight(), null, new Function1() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$5$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FloatingActionButtonKt$ExtendedFloatingActionButton$5.invoke$lambda$4$lambda$3$lambda$2(placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit invoke$lambda$4$lambda$3$lambda$2(Placeable $placeable, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.place$default($this$layout, $placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public final void invoke(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Object value$iv;
        Function0<ComposeUiNode> function1;
        ComposerKt.sourceInformation($composer, "C464@21161L68,466@21372L14,467@21456L14,469@21536L59,471@21661L62,474@21792L435,472@21732L1313:FloatingActionButton.kt#uh7d8r");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-827388388, $changed, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:464)");
        }
        final Transition expandTransition = TransitionKt.updateTransition(Float.valueOf(this.$expanded ? 1.0f : 0.0f), "expanded state", $composer, 48, 0);
        final FiniteAnimationSpec sizeAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, $composer, 6);
        final FiniteAnimationSpec opacityAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer, 6);
        Function3<Transition.Segment<Float>, Composer, Integer, FiniteAnimationSpec<Float>> function3 = new Function3<Transition.Segment<Float>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$5$expandedWidthProgress$1
            public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Float> segment, Composer $composer2, int $changed2) {
                $composer2.startReplaceGroup(-1114419602);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1114419602, $changed2, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:469)");
                }
                FiniteAnimationSpec<Float> finiteAnimationSpec = sizeAnimationSpec;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer2.endReplaceGroup();
                return finiteAnimationSpec;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Float> segment, Composer composer, Integer num) {
                return invoke(segment, composer, num.intValue());
            }
        };
        ComposerKt.sourceInformationMarkerStart($composer, -1338768149, "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli");
        TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
        int $changed$iv$iv = (0 & 14) | ((0 << 3) & 896) | ((0 << 3) & 7168) | ((0 << 3) & 57344);
        ComposerKt.sourceInformationMarkerStart($composer, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
        int $changed2 = ($changed$iv$iv >> 9) & 112;
        float it = ((Number) expandTransition.getCurrentState()).floatValue();
        $composer.startReplaceGroup(-157343033);
        ComposerKt.sourceInformation($composer, "CN(it):FloatingActionButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-157343033, $changed2, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:469)");
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        Object initialValue$iv$iv = Float.valueOf(it);
        int $changed3 = ($changed$iv$iv >> 9) & 112;
        float it2 = ((Number) 
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00ed: INVOKE (r1v17 'it2' float) = 
              (wrap java.lang.Number:0x00eb: CHECK_CAST (java.lang.Number) (wrap java.lang.Object:0x00e3: INVOKE (r1v13 ?? I:??[OBJECT, ARRAY] A[D('opacityAnimationSpec' androidx.compose.animation.core.FiniteAnimationSpec)]) VIRTUAL call: androidx.compose.animation.core.Transition.getTargetState():java.lang.Object A[MD:():S (m), WRAPPED] (LINE:821)))
             VIRTUAL call: java.lang.Number.floatValue():float A[DECLARE_VAR, MD:():float (c)] (LINE:821) in method: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$5.invoke(androidx.compose.runtime.Composer, int):void, file: classes10.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r1v13 ??
            	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
            */
        /*
            Method dump skipped, instruction units count: 1585
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$5.invoke(androidx.compose.runtime.Composer, int):void");
    }

    static final boolean invoke$lambda$12$lambda$6$lambda$5(Transition $expandTransition) {
        return ((((Number) $expandTransition.getCurrentState()).floatValue() > 0.0f ? 1 : (((Number) $expandTransition.getCurrentState()).floatValue() == 0.0f ? 0 : -1)) == 0) && !$expandTransition.isRunning();
    }

    static final Unit invoke$lambda$12$lambda$10$lambda$9(State $expandedAlphaProgress, GraphicsLayerScope $this$graphicsLayer) {
        $this$graphicsLayer.setAlpha(((Number) $expandedAlphaProgress.getValue()).floatValue());
        return Unit.INSTANCE;
    }
}
