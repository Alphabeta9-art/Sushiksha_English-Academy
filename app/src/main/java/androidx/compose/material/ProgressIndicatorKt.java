package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.IntCompanionObject;

/* JADX INFO: compiled from: ProgressIndicator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0014\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0000\u001aA\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\f\u0010\r\u001a7\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a5\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a-\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001a;\u0010\u0014\u001a\u00020\u0003*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0019\u0010\u001a\u001a+\u0010\u001b\u001a\u00020\u0003*\u00020\u00152\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u001c\u0010\u001d\u001aK\u0010\u001e\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\u001f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b \u0010!\u001aA\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\u001f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\"\u0010#\u001a5\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\u001fH\u0007¢\u0006\u0004\b$\u0010%\u001a-\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\u001fH\u0007¢\u0006\u0004\b&\u0010'\u001a3\u0010(\u001a\u00020\u0003*\u00020\u00152\u0006\u0010)\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010+\u001a\u00020,H\u0002¢\u0006\u0004\b-\u0010.\u001a#\u0010/\u001a\u00020\u0003*\u00020\u00152\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010+\u001a\u00020,H\u0002¢\u0006\u0004\b0\u00101\u001a3\u00102\u001a\u00020\u0003*\u00020\u00152\u0006\u0010)\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010+\u001a\u00020,H\u0002¢\u0006\u0004\b3\u0010.\u001a;\u00104\u001a\u00020\u0003*\u00020\u00152\u0006\u0010)\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010+\u001a\u00020,H\u0002¢\u0006\u0004\b5\u00106\"\u0010\u00107\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0004\n\u0002\u00108\"\u0010\u00109\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0004\n\u0002\u00108\"\u0010\u0010:\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0004\n\u0002\u00108\"\u000e\u0010;\u001a\u00020<X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010=\u001a\u00020<X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010>\u001a\u00020<X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010?\u001a\u00020<X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010@\u001a\u00020<X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010A\u001a\u00020<X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010B\u001a\u00020<X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010C\u001a\u00020<X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010D\u001a\u00020<X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010E\u001a\u00020FX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010G\u001a\u00020FX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010H\u001a\u00020FX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010I\u001a\u00020FX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010J\u001a\u00020<X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010K\u001a\u00020<X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010L\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010M\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010N\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010O\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010P\u001a\u00020<X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010Q\u001a\u00020<X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010R\u001a\u00020FX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006S²\u0006\n\u0010T\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u0010U\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u0010V\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u0010W\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u0010X\u001a\u00020<X\u008a\u0084\u0002²\u0006\n\u0010Y\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u0010Z\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u0010)\u001a\u00020\u0005X\u008a\u0084\u0002"}, d2 = {"increaseSemanticsBounds", "Landroidx/compose/ui/Modifier;", "LinearProgressIndicator", "", "progress", "", "modifier", "color", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "strokeCap", "Landroidx/compose/ui/graphics/StrokeCap;", "LinearProgressIndicator-_5eSR-E", "(FLandroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-2cYBFYY", "(Landroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-eaDK9VM", "(FLandroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-RIQooxk", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "drawLinearIndicator", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "startFraction", "endFraction", "strokeWidth", "drawLinearIndicator-qYKTg0g", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJFI)V", "drawLinearIndicatorBackground", "drawLinearIndicatorBackground-AZGd3zU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFI)V", "CircularProgressIndicator", "Landroidx/compose/ui/unit/Dp;", "CircularProgressIndicator-DUhRLBM", "(FLandroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-LxG7B9w", "(Landroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-MBs18nI", "(FLandroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-aM-cp0Q", "(Landroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "drawCircularIndicator", "startAngle", "sweep", "stroke", "Landroidx/compose/ui/graphics/drawscope/Stroke;", "drawCircularIndicator-42QJj7c", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawCircularIndicatorBackground", "drawCircularIndicatorBackground-bw27NRU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawDeterminateCircularIndicator", "drawDeterminateCircularIndicator-42QJj7c", "drawIndeterminateCircularIndicator", "drawIndeterminateCircularIndicator-hrjfTZI", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "LinearIndicatorHeight", "F", "LinearIndicatorWidth", "CircularIndicatorDiameter", "LinearAnimationDuration", "", "FirstLineHeadDuration", "FirstLineTailDuration", "SecondLineHeadDuration", "SecondLineTailDuration", "FirstLineHeadDelay", "FirstLineTailDelay", "SecondLineHeadDelay", "SecondLineTailDelay", "FirstLineHeadEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "FirstLineTailEasing", "SecondLineHeadEasing", "SecondLineTailEasing", "RotationsPerCycle", "RotationDuration", "StartAngleOffset", "BaseRotationAngle", "JumpRotationAngle", "RotationAngleOffset", "HeadAndTailAnimationDuration", "HeadAndTailDelayDuration", "CircularEasing", "material", "firstLineHead", "firstLineTail", "secondLineHead", "secondLineTail", "currentRotation", "baseRotation", "endAngle"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ProgressIndicatorKt {
    private static final float BaseRotationAngle = 286.0f;
    private static final int FirstLineHeadDelay = 0;
    private static final int FirstLineHeadDuration = 750;
    private static final int FirstLineTailDelay = 333;
    private static final int FirstLineTailDuration = 850;
    private static final int HeadAndTailAnimationDuration = 666;
    private static final int HeadAndTailDelayDuration = 666;
    private static final float JumpRotationAngle = 290.0f;
    private static final int LinearAnimationDuration = 1800;
    private static final float RotationAngleOffset = 216.0f;
    private static final int RotationDuration = 1332;
    private static final int RotationsPerCycle = 5;
    private static final int SecondLineHeadDelay = 1000;
    private static final int SecondLineHeadDuration = 567;
    private static final int SecondLineTailDelay = 1267;
    private static final int SecondLineTailDuration = 533;
    private static final float StartAngleOffset = -90.0f;
    private static final float LinearIndicatorHeight = ProgressIndicatorDefaults.INSTANCE.m1882getStrokeWidthD9Ej5fM();
    private static final float LinearIndicatorWidth = Dp.m7905constructorimpl(PsExtractor.VIDEO_STREAM_MASK);
    private static final float CircularIndicatorDiameter = Dp.m7905constructorimpl(40);
    private static final CubicBezierEasing FirstLineHeadEasing = new CubicBezierEasing(0.2f, 0.0f, 0.8f, 1.0f);
    private static final CubicBezierEasing FirstLineTailEasing = new CubicBezierEasing(0.4f, 0.0f, 1.0f, 1.0f);
    private static final CubicBezierEasing SecondLineHeadEasing = new CubicBezierEasing(0.0f, 0.0f, 0.65f, 1.0f);
    private static final CubicBezierEasing SecondLineTailEasing = new CubicBezierEasing(0.1f, 0.0f, 0.45f, 1.0f);
    private static final CubicBezierEasing CircularEasing = new CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f);

    static final Unit CircularProgressIndicator_DUhRLBM$lambda$3(float f, Modifier modifier, long j, float f2, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m1888CircularProgressIndicatorDUhRLBM(f, modifier, j, f2, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit CircularProgressIndicator_LxG7B9w$lambda$8(Modifier modifier, long j, float f, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m1889CircularProgressIndicatorLxG7B9w(modifier, j, f, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit CircularProgressIndicator_MBs18nI$lambda$0(float f, Modifier modifier, long j, float f2, int i, int i2, Composer composer, int i3) {
        m1890CircularProgressIndicatorMBs18nI(f, modifier, j, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit CircularProgressIndicator_aM_cp0Q$lambda$0(Modifier modifier, long j, float f, int i, int i2, Composer composer, int i3) {
        m1891CircularProgressIndicatoraMcp0Q(modifier, j, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator_2cYBFYY$lambda$9(Modifier modifier, long j, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m1892LinearProgressIndicator2cYBFYY(modifier, j, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator_RIQooxk$lambda$0(Modifier modifier, long j, long j2, int i, int i2, Composer composer, int i3) {
        m1893LinearProgressIndicatorRIQooxk(modifier, j, j2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator__5eSR_E$lambda$2(float f, Modifier modifier, long j, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m1894LinearProgressIndicator_5eSRE(f, modifier, j, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator_eaDK9VM$lambda$0(float f, Modifier modifier, long j, long j2, int i, int i2, Composer composer, int i3) {
        m1895LinearProgressIndicatoreaDK9VM(f, modifier, j, j2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final Modifier increaseSemanticsBounds(Modifier $this$increaseSemanticsBounds) {
        final float padding = Dp.m7905constructorimpl(10);
        return PaddingKt.m821paddingVpY3zN4$default(SemanticsModifierKt.semantics(LayoutModifierKt.layout($this$increaseSemanticsBounds, new Function3() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ProgressIndicatorKt.increaseSemanticsBounds$lambda$0(padding, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
            }
        }), true, new Function1() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        }), 0.0f, padding, 1, null);
    }

    static final MeasureResult increaseSemanticsBounds$lambda$0(float $padding, MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        final int paddingPx = $this$layout.mo400roundToPx0680j_4($padding);
        long newConstraint = ConstraintsKt.m7877offsetNN6EwU(constraints.getValue(), 0, paddingPx * 2);
        final Placeable placeable = measurable.mo6541measureBRTryo0(newConstraint);
        int height = placeable.getHeight() - (paddingPx * 2);
        int width = placeable.getWidth();
        return MeasureScope.layout$default($this$layout, width, height, null, new Function1() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt.increaseSemanticsBounds$lambda$0$0(placeable, paddingPx, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit increaseSemanticsBounds$lambda$0$0(Placeable $placeable, int $paddingPx, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.place$default($this$layout, $placeable, 0, -$paddingPx, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: LinearProgressIndicator-_5eSR-E, reason: not valid java name */
    public static final void m1894LinearProgressIndicator_5eSRE(float progress, Modifier modifier, long j, long j2, int i, Composer $composer, final int $changed, final int i2) {
        float f;
        Modifier modifier2;
        long j3;
        long j4;
        int iM5471getButtKaPHkGw;
        Modifier.Companion modifier3;
        final int i3;
        final long j5;
        final long j6;
        long jM1765getPrimary0d7_KjU;
        long jM5095copywmQWz5c;
        final int i4;
        final long j7;
        Composer $composer2 = $composer.startRestartGroup(-531984864);
        ComposerKt.sourceInformation($composer2, "C(LinearProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,backgroundColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap)115@5390L204,110@5177L417:ProgressIndicator.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            f = progress;
            $dirty |= $composer2.changed(f) ? 4 : 2;
        } else {
            f = progress;
        }
        int i5 = i2 & 2;
        if (i5 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i2 & 4) == 0) {
                j3 = j;
                int i6 = $composer2.changed(j3) ? 256 : 128;
                $dirty |= i6;
            } else {
                j3 = j;
            }
            $dirty |= i6;
        } else {
            j3 = j;
        }
        if (($changed & 3072) == 0) {
            if ((i2 & 8) == 0) {
                j4 = j2;
                int i7 = $composer2.changed(j4) ? 2048 : 1024;
                $dirty |= i7;
            } else {
                j4 = j2;
            }
            $dirty |= i7;
        } else {
            j4 = j2;
        }
        if (($changed & 24576) == 0) {
            if ((i2 & 16) == 0) {
                iM5471getButtKaPHkGw = i;
                int i8 = $composer2.changed(iM5471getButtKaPHkGw) ? 16384 : 8192;
                $dirty |= i8;
            } else {
                iM5471getButtKaPHkGw = i;
            }
            $dirty |= i8;
        } else {
            iM5471getButtKaPHkGw = i;
        }
        boolean z = true;
        if (!$composer2.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            i3 = iM5471getButtKaPHkGw;
            j5 = j3;
            j6 = j4;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "105@4977L6");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i2 & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i2 & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i2 & 16) != 0) {
                    $dirty &= -57345;
                }
                modifier3 = modifier2;
            } else {
                if (i5 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i2 & 4) == 0) {
                    jM1765getPrimary0d7_KjU = j3;
                } else {
                    $dirty &= -897;
                    jM1765getPrimary0d7_KjU = MaterialTheme.INSTANCE.getColors($composer2, 6).m1765getPrimary0d7_KjU();
                }
                if ((i2 & 8) == 0) {
                    jM5095copywmQWz5c = j4;
                } else {
                    jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM1765getPrimary0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1765getPrimary0d7_KjU) : 0.24f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1765getPrimary0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1765getPrimary0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1765getPrimary0d7_KjU) : 0.0f);
                    $dirty &= -7169;
                }
                if ((i2 & 16) == 0) {
                    j4 = jM5095copywmQWz5c;
                    j3 = jM1765getPrimary0d7_KjU;
                } else {
                    $dirty &= -57345;
                    iM5471getButtKaPHkGw = StrokeCap.INSTANCE.m5471getButtKaPHkGw();
                    j4 = jM5095copywmQWz5c;
                    j3 = jM1765getPrimary0d7_KjU;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-531984864, $dirty, -1, "androidx.compose.material.LinearProgressIndicator (ProgressIndicator.kt:108)");
            }
            float $this$fastCoerceAtLeast$iv$iv = progress;
            if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
                $this$fastCoerceAtLeast$iv$iv = 0.0f;
            }
            final float minimumValue$iv = $this$fastCoerceAtLeast$iv$iv > 1.0f ? 1.0f : $this$fastCoerceAtLeast$iv$iv;
            Modifier modifierIncreaseSemanticsBounds = increaseSemanticsBounds(modifier3);
            Float fValueOf = Float.valueOf(minimumValue$iv);
            float it = fValueOf.floatValue();
            if (Float.isNaN(it)) {
                fValueOf = null;
            }
            Modifier modifierM867sizeVpY3zN4 = SizeKt.m867sizeVpY3zN4(ProgressSemanticsKt.progressSemantics$default(modifierIncreaseSemanticsBounds, fValueOf != null ? fValueOf.floatValue() : 0.0f, null, 0, 6, null), LinearIndicatorWidth, LinearIndicatorHeight);
            ComposerKt.sourceInformationMarkerStart($composer2, -136327796, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean zChanged = (((($dirty & 7168) ^ 3072) > 2048 && $composer2.changed(j4)) || ($dirty & 3072) == 2048) | ((((57344 & $dirty) ^ 24576) > 16384 && $composer2.changed(iM5471getButtKaPHkGw)) || ($dirty & 24576) == 16384) | $composer2.changed(minimumValue$iv);
            if (((($dirty & 896) ^ 384) <= 256 || !$composer2.changed(j3)) && ($dirty & 384) != 256) {
                z = false;
            }
            boolean invalid$iv = zChanged | z;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                i4 = iM5471getButtKaPHkGw;
                j5 = j3;
                j7 = j4;
                Object value$iv = new Function1() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.LinearProgressIndicator__5eSR_E$lambda$1$0(j7, i4, minimumValue$iv, j5, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            } else {
                i4 = iM5471getButtKaPHkGw;
                j5 = j3;
                j7 = j4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierM867sizeVpY3zN4, (Function1) it$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j6 = j7;
            i3 = i4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final float f2 = f;
            final Modifier modifier4 = modifier3;
            final long j8 = j5;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator__5eSR_E$lambda$2(f2, modifier4, j8, j6, i3, $changed, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LinearProgressIndicator__5eSR_E$lambda$1$0(long j, int i, float $coercedProgress, long j2, DrawScope $this$Canvas) {
        long arg0$iv = $this$Canvas.mo5671getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (4294967295L & arg0$iv);
        float strokeWidth = Float.intBitsToFloat(bits$iv$iv$iv);
        m1901drawLinearIndicatorBackgroundAZGd3zU($this$Canvas, j, strokeWidth, i);
        m1900drawLinearIndicatorqYKTg0g($this$Canvas, 0.0f, $coercedProgress, j2, strokeWidth, i);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:124:0x030c A[PHI: r11
  0x030c: PHI (r11v25 long) = (r11v23 long), (r11v26 long) binds: [B:123:0x030a, B:119:0x0301] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:128:0x0329  */
    /* JADX WARN: Code duplicated, block: B:130:0x0333  */
    /* JADX WARN: Code duplicated, block: B:131:0x033c  */
    /* JADX WARN: Code duplicated, block: B:135:0x0366  */
    /* JADX INFO: renamed from: LinearProgressIndicator-2cYBFYY, reason: not valid java name */
    public static final void m1892LinearProgressIndicator2cYBFYY(Modifier modifier, long j, long j2, int i, Composer $composer, final int $changed, final int i2) {
        Modifier modifier2;
        long j3;
        long j4;
        int i3;
        final Modifier modifier3;
        final int i4;
        final long j5;
        final long j6;
        Modifier.Companion modifier4;
        long jM1765getPrimary0d7_KjU;
        long jM5095copywmQWz5c;
        int $dirty;
        int $dirty2;
        long j7;
        long j8;
        long j9;
        boolean z;
        boolean invalid$iv;
        Object it$iv;
        final int i5;
        final long j10;
        Composer $composer2 = $composer.startRestartGroup(1501635280);
        ComposerKt.sourceInformation($composer2, "C(LinearProgressIndicator)N(modifier,color:c#ui.graphics.Color,backgroundColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap)145@6690L28,155@7116L236,150@6980L397,168@7566L236,163@7430L397,181@8017L240,176@7881L401,194@8472L240,189@8336L401,206@8908L434,201@8742L600:ProgressIndicator.kt#jmzs0o");
        int $dirty3 = $changed;
        int i6 = i2 & 1;
        if (i6 != 0) {
            $dirty3 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i2 & 2) == 0) {
                j3 = j;
                int i7 = $composer2.changed(j3) ? 32 : 16;
                $dirty3 |= i7;
            } else {
                j3 = j;
            }
            $dirty3 |= i7;
        } else {
            j3 = j;
        }
        if (($changed & 384) == 0) {
            if ((i2 & 4) == 0) {
                j4 = j2;
                int i8 = $composer2.changed(j4) ? 256 : 128;
                $dirty3 |= i8;
            } else {
                j4 = j2;
            }
            $dirty3 |= i8;
        } else {
            j4 = j2;
        }
        if (($changed & 3072) == 0) {
            if ((i2 & 8) == 0) {
                i3 = i;
                int i9 = $composer2.changed(i3) ? 2048 : 1024;
                $dirty3 |= i9;
            } else {
                i3 = i;
            }
            $dirty3 |= i9;
        } else {
            i3 = i;
        }
        if (!$composer2.shouldExecute(($dirty3 & 1171) != 1170, $dirty3 & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            i4 = i3;
            j5 = j3;
            j6 = j4;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "141@6521L6");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i2 & 2) != 0) {
                    $dirty3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    $dirty3 &= -897;
                }
                if ((i2 & 8) != 0) {
                    $dirty3 &= -7169;
                }
                modifier4 = modifier2;
                j8 = j4;
                long j11 = j3;
                $dirty = $dirty3;
                $dirty2 = i3;
                j7 = j11;
            } else {
                if (i6 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 2) == 0) {
                    jM1765getPrimary0d7_KjU = j3;
                } else {
                    $dirty3 &= -113;
                    jM1765getPrimary0d7_KjU = MaterialTheme.INSTANCE.getColors($composer2, 6).m1765getPrimary0d7_KjU();
                }
                if ((i2 & 4) == 0) {
                    jM5095copywmQWz5c = j4;
                } else {
                    jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM1765getPrimary0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1765getPrimary0d7_KjU) : 0.24f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1765getPrimary0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1765getPrimary0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1765getPrimary0d7_KjU) : 0.0f);
                    $dirty3 &= -897;
                }
                if ((i2 & 8) == 0) {
                    long j12 = jM5095copywmQWz5c;
                    $dirty = $dirty3;
                    $dirty2 = i3;
                    j7 = jM1765getPrimary0d7_KjU;
                    j8 = j12;
                } else {
                    long j13 = jM5095copywmQWz5c;
                    $dirty = $dirty3 & (-7169);
                    $dirty2 = StrokeCap.INSTANCE.m5471getButtKaPHkGw();
                    j7 = jM1765getPrimary0d7_KjU;
                    j8 = j13;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1501635280, $dirty, -1, "androidx.compose.material.LinearProgressIndicator (ProgressIndicator.kt:144)");
            }
            InfiniteTransition infiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition(null, $composer2, 0, 1);
            ComposerKt.sourceInformationMarkerStart($composer2, -37484996, "CC(remember):ProgressIndicator.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.LinearProgressIndicator_2cYBFYY$lambda$0$0((KeyframesSpec.KeyframesSpecConfig) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv2 = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            int $dirty4 = $dirty;
            Modifier modifier5 = modifier4;
            long j14 = j8;
            final State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.0f, 1.0f, AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes((Function1) it$iv2), null, 0L, 6, null), null, $composer2, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            ComposerKt.sourceInformationMarkerStart($composer2, -37470596, "CC(remember):ProgressIndicator.kt#9igjgp");
            Object it$iv3 = $composer2.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.LinearProgressIndicator_2cYBFYY$lambda$2$0((KeyframesSpec.KeyframesSpecConfig) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                it$iv3 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final State<Float> stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.0f, 1.0f, AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes((Function1) it$iv3), null, 0L, 6, null), null, $composer2, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            ComposerKt.sourceInformationMarkerStart($composer2, -37456160, "CC(remember):ProgressIndicator.kt#9igjgp");
            Object it$iv4 = $composer2.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = new Function1() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.LinearProgressIndicator_2cYBFYY$lambda$4$0((KeyframesSpec.KeyframesSpecConfig) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
                it$iv4 = value$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            long j15 = j7;
            final State<Float> stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.0f, 1.0f, AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes((Function1) it$iv4), null, 0L, 6, null), null, $composer2, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            ComposerKt.sourceInformationMarkerStart($composer2, -37441600, "CC(remember):ProgressIndicator.kt#9igjgp");
            Object it$iv5 = $composer2.rememberedValue();
            if (it$iv5 == Composer.INSTANCE.getEmpty()) {
                Object value$iv4 = new Function1() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.LinearProgressIndicator_2cYBFYY$lambda$6$0((KeyframesSpec.KeyframesSpecConfig) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv4);
                it$iv5 = value$iv4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final State<Float> stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.0f, 1.0f, AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes((Function1) it$iv5), null, 0L, 6, null), null, $composer2, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            Modifier modifierM867sizeVpY3zN4 = SizeKt.m867sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(increaseSemanticsBounds(modifier5)), LinearIndicatorWidth, LinearIndicatorHeight);
            ComposerKt.sourceInformationMarkerStart($composer2, -37427454, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean zChanged = (((($dirty4 & 896) ^ 384) > 256 && $composer2.changed(j14)) || ($dirty4 & 384) == 256) | (((($dirty4 & 7168) ^ 3072) > 2048 && $composer2.changed($dirty2)) || ($dirty4 & 3072) == 2048) | $composer2.changed(stateAnimateFloat) | $composer2.changed(stateAnimateFloat2);
            if ((($dirty4 & 112) ^ 48) > 32) {
                j9 = j15;
                if ($composer2.changed(j9)) {
                    z = true;
                }
                invalid$iv = zChanged | z | $composer2.changed(stateAnimateFloat3) | $composer2.changed(stateAnimateFloat4);
                it$iv = $composer2.rememberedValue();
                if (!invalid$iv) {
                    i5 = $dirty2;
                    if (it$iv != Composer.INSTANCE.getEmpty()) {
                        j10 = j14;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    CanvasKt.Canvas(modifierM867sizeVpY3zN4, (Function1) it$iv, $composer2, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier5;
                    j5 = j9;
                    j6 = j10;
                    i4 = i5;
                } else {
                    i5 = $dirty2;
                }
                j10 = j14;
                final long j16 = j9;
                Object value$iv5 = new Function1() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.LinearProgressIndicator_2cYBFYY$lambda$8$0(j10, i5, j16, stateAnimateFloat, stateAnimateFloat2, stateAnimateFloat3, stateAnimateFloat4, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv5);
                it$iv = value$iv5;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(modifierM867sizeVpY3zN4, (Function1) it$iv, $composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier5;
                j5 = j9;
                j6 = j10;
                i4 = i5;
            } else {
                j9 = j15;
            }
            if (($dirty4 & 48) == 32) {
                z = true;
            } else {
                z = false;
            }
            invalid$iv = zChanged | z | $composer2.changed(stateAnimateFloat3) | $composer2.changed(stateAnimateFloat4);
            it$iv = $composer2.rememberedValue();
            if (!invalid$iv) {
                i5 = $dirty2;
                if (it$iv != Composer.INSTANCE.getEmpty()) {
                    j10 = j14;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(modifierM867sizeVpY3zN4, (Function1) it$iv, $composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier5;
                j5 = j9;
                j6 = j10;
                i4 = i5;
            } else {
                i5 = $dirty2;
            }
            j10 = j14;
            final long j17 = j9;
            Object value$iv6 = new Function1() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ProgressIndicatorKt.LinearProgressIndicator_2cYBFYY$lambda$8$0(j10, i5, j17, stateAnimateFloat, stateAnimateFloat2, stateAnimateFloat3, stateAnimateFloat4, (DrawScope) obj);
                }
            };
            $composer2.updateRememberedValue(value$iv6);
            it$iv = value$iv6;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierM867sizeVpY3zN4, (Function1) it$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            j5 = j9;
            j6 = j10;
            i4 = i5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator_2cYBFYY$lambda$9(modifier3, j5, j6, i4, $changed, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float LinearProgressIndicator_2cYBFYY$lambda$1(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LinearProgressIndicator_2cYBFYY$lambda$0$0(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(LinearAnimationDuration);
        $this$keyframes.using($this$keyframes.at(Float.valueOf(0.0f), 0), FirstLineHeadEasing);
        $this$keyframes.at(Float.valueOf(1.0f), FirstLineHeadDuration);
        return Unit.INSTANCE;
    }

    private static final float LinearProgressIndicator_2cYBFYY$lambda$3(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LinearProgressIndicator_2cYBFYY$lambda$2$0(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(LinearAnimationDuration);
        $this$keyframes.using($this$keyframes.at(Float.valueOf(0.0f), FirstLineTailDelay), FirstLineTailEasing);
        $this$keyframes.at(Float.valueOf(1.0f), 1183);
        return Unit.INSTANCE;
    }

    private static final float LinearProgressIndicator_2cYBFYY$lambda$5(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LinearProgressIndicator_2cYBFYY$lambda$4$0(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(LinearAnimationDuration);
        $this$keyframes.using($this$keyframes.at(Float.valueOf(0.0f), 1000), SecondLineHeadEasing);
        $this$keyframes.at(Float.valueOf(1.0f), 1567);
        return Unit.INSTANCE;
    }

    private static final float LinearProgressIndicator_2cYBFYY$lambda$7(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LinearProgressIndicator_2cYBFYY$lambda$6$0(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(LinearAnimationDuration);
        $this$keyframes.using($this$keyframes.at(Float.valueOf(0.0f), SecondLineTailDelay), SecondLineTailEasing);
        $this$keyframes.at(Float.valueOf(1.0f), LinearAnimationDuration);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LinearProgressIndicator_2cYBFYY$lambda$8$0(long j, int i, long j2, State $firstLineHead$delegate, State $firstLineTail$delegate, State $secondLineHead$delegate, State $secondLineTail$delegate, DrawScope $this$Canvas) {
        long arg0$iv = $this$Canvas.mo5671getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (4294967295L & arg0$iv);
        float strokeWidth = Float.intBitsToFloat(bits$iv$iv$iv);
        m1901drawLinearIndicatorBackgroundAZGd3zU($this$Canvas, j, strokeWidth, i);
        if (LinearProgressIndicator_2cYBFYY$lambda$1($firstLineHead$delegate) - LinearProgressIndicator_2cYBFYY$lambda$3($firstLineTail$delegate) > 0.0f) {
            m1900drawLinearIndicatorqYKTg0g($this$Canvas, LinearProgressIndicator_2cYBFYY$lambda$1($firstLineHead$delegate), LinearProgressIndicator_2cYBFYY$lambda$3($firstLineTail$delegate), j2, strokeWidth, i);
        }
        if (LinearProgressIndicator_2cYBFYY$lambda$5($secondLineHead$delegate) - LinearProgressIndicator_2cYBFYY$lambda$7($secondLineTail$delegate) > 0.0f) {
            m1900drawLinearIndicatorqYKTg0g($this$Canvas, LinearProgressIndicator_2cYBFYY$lambda$5($secondLineHead$delegate), LinearProgressIndicator_2cYBFYY$lambda$7($secondLineTail$delegate), j2, strokeWidth, i);
        }
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: LinearProgressIndicator-eaDK9VM, reason: not valid java name */
    public static final /* synthetic */ void m1895LinearProgressIndicatoreaDK9VM(final float progress, Modifier modifier, long j, long j2, Composer $composer, final int $changed, final int i) {
        float f;
        Modifier modifier2;
        long j3;
        long j4;
        final Modifier modifier3;
        final long j5;
        final long j6;
        long jM1765getPrimary0d7_KjU;
        long jM5095copywmQWz5c;
        long j7;
        Modifier modifier4;
        Composer $composer2 = $composer.startRestartGroup(-850309746);
        ComposerKt.sourceInformation($composer2, "C(LinearProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,backgroundColor:c#ui.graphics.Color)225@9657L95:ProgressIndicator.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            f = progress;
            $dirty |= $composer2.changed(f) ? 4 : 2;
        } else {
            f = progress;
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
                j3 = j;
                int i3 = $composer2.changed(j3) ? 256 : 128;
                $dirty |= i3;
            } else {
                j3 = j;
            }
            $dirty |= i3;
        } else {
            j3 = j;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j4 = j2;
                int i4 = $composer2.changed(j4) ? 2048 : 1024;
                $dirty |= i4;
            } else {
                j4 = j2;
            }
            $dirty |= i4;
        } else {
            j4 = j2;
        }
        if ($composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "223@9560L6");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    jM1765getPrimary0d7_KjU = MaterialTheme.INSTANCE.getColors($composer2, 6).m1765getPrimary0d7_KjU();
                } else {
                    jM1765getPrimary0d7_KjU = j3;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    modifier4 = modifier5;
                    jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM1765getPrimary0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1765getPrimary0d7_KjU) : 0.24f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1765getPrimary0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1765getPrimary0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1765getPrimary0d7_KjU) : 0.0f);
                    j7 = jM1765getPrimary0d7_KjU;
                } else {
                    jM5095copywmQWz5c = j4;
                    j7 = jM1765getPrimary0d7_KjU;
                    modifier4 = modifier5;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                jM5095copywmQWz5c = j4;
                modifier4 = modifier2;
                j7 = j3;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-850309746, $dirty, -1, "androidx.compose.material.LinearProgressIndicator (ProgressIndicator.kt:225)");
            }
            m1894LinearProgressIndicator_5eSRE(f, modifier4, j7, jM5095copywmQWz5c, StrokeCap.INSTANCE.m5471getButtKaPHkGw(), $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            j5 = j7;
            j6 = jM5095copywmQWz5c;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j5 = j3;
            j6 = j4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator_eaDK9VM$lambda$0(progress, modifier3, j5, j6, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: LinearProgressIndicator-RIQooxk, reason: not valid java name */
    public static final /* synthetic */ void m1893LinearProgressIndicatorRIQooxk(Modifier modifier, long j, long j2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j3;
        long j4;
        final Modifier modifier3;
        final long j5;
        final long j6;
        long jM1765getPrimary0d7_KjU;
        long jM5095copywmQWz5c;
        long j7;
        Modifier modifier4;
        Composer $composer2 = $composer.startRestartGroup(-819397058);
        ComposerKt.sourceInformation($composer2, "C(LinearProgressIndicator)N(modifier,color:c#ui.graphics.Color,backgroundColor:c#ui.graphics.Color)233@10044L85:ProgressIndicator.kt#jmzs0o");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                j3 = j;
                int i3 = $composer2.changed(j3) ? 32 : 16;
                $dirty |= i3;
            } else {
                j3 = j;
            }
            $dirty |= i3;
        } else {
            j3 = j;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j4 = j2;
                int i4 = $composer2.changed(j4) ? 256 : 128;
                $dirty |= i4;
            } else {
                j4 = j2;
            }
            $dirty |= i4;
        } else {
            j4 = j2;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "231@9947L6");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    $dirty &= -113;
                    jM1765getPrimary0d7_KjU = MaterialTheme.INSTANCE.getColors($composer2, 6).m1765getPrimary0d7_KjU();
                } else {
                    jM1765getPrimary0d7_KjU = j3;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    modifier4 = modifier5;
                    jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM1765getPrimary0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1765getPrimary0d7_KjU) : 0.24f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1765getPrimary0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1765getPrimary0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1765getPrimary0d7_KjU) : 0.0f);
                    j7 = jM1765getPrimary0d7_KjU;
                } else {
                    jM5095copywmQWz5c = j4;
                    j7 = jM1765getPrimary0d7_KjU;
                    modifier4 = modifier5;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                jM5095copywmQWz5c = j4;
                modifier4 = modifier2;
                j7 = j3;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-819397058, $dirty, -1, "androidx.compose.material.LinearProgressIndicator (ProgressIndicator.kt:233)");
            }
            m1892LinearProgressIndicator2cYBFYY(modifier4, j7, jM5095copywmQWz5c, StrokeCap.INSTANCE.m5471getButtKaPHkGw(), $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j5 = j7;
            j6 = jM5095copywmQWz5c;
            modifier3 = modifier4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j5 = j3;
            j6 = j4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator_RIQooxk$lambda$0(modifier3, j5, j6, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Failed to apply debug info
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r8v3 ??, new type: long
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyDebugInfo(TypeUpdate.java:77)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:133)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:79)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
    Caused by: java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r11v3 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r11v3 ??, new type: long
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r13v0 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r13v0 ??, new type: long
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r13v1 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r13v1 ??, new type: long
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r19v0 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r19v0 ??, new type: long
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r8v3 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r8v3 ??, new type: long
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:159)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:136)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:241)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
    Caused by: java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r8v3 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r8v3 ??, new type: long
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException
     */
    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r8v3 ??, new type: long
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:186)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:245)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
        Caused by: java.lang.NullPointerException
        */
    /* JADX INFO: renamed from: drawLinearIndicator-qYKTg0g, reason: not valid java name */
    private static final void m1900drawLinearIndicatorqYKTg0g(androidx.compose.ui.graphics.drawscope.DrawScope r45, float r46, float r47, long r48, float r50, int r51) {
        /*
            Method dump skipped, instruction units count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ProgressIndicatorKt.m1900drawLinearIndicatorqYKTg0g(androidx.compose.ui.graphics.drawscope.DrawScope, float, float, long, float, int):void");
    }

    /* JADX INFO: renamed from: drawLinearIndicatorBackground-AZGd3zU, reason: not valid java name */
    private static final void m1901drawLinearIndicatorBackgroundAZGd3zU(DrawScope $this$drawLinearIndicatorBackground_u2dAZGd3zU, long j, float strokeWidth, int i) {
        m1900drawLinearIndicatorqYKTg0g($this$drawLinearIndicatorBackground_u2dAZGd3zU, 0.0f, 1.0f, j, strokeWidth, i);
    }

    /* JADX INFO: renamed from: CircularProgressIndicator-DUhRLBM, reason: not valid java name */
    public static final void m1888CircularProgressIndicatorDUhRLBM(final float progress, Modifier modifier, long j, float f, long j2, int i, Composer $composer, final int $changed, final int i2) {
        Modifier modifier2;
        long jM1765getPrimary0d7_KjU;
        float fM1882getStrokeWidthD9Ej5fM;
        long jM5132getTransparent0d7_KjU;
        int i3;
        final Modifier modifier3;
        final long j3;
        final int i4;
        final float f2;
        final long j4;
        Modifier.Companion modifier4;
        int iM5471getButtKaPHkGw;
        Composer $composer2 = $composer.startRestartGroup(1746618448);
        ComposerKt.sourceInformation($composer2, "C(CircularProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,strokeWidth:c#ui.unit.Dp,backgroundColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap)315@13540L7,320@13765L251,316@13609L407:ProgressIndicator.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(progress) ? 4 : 2;
        }
        int i5 = i2 & 2;
        if (i5 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i2 & 4) == 0) {
                jM1765getPrimary0d7_KjU = j;
                int i6 = $composer2.changed(jM1765getPrimary0d7_KjU) ? 256 : 128;
                $dirty |= i6;
            } else {
                jM1765getPrimary0d7_KjU = j;
            }
            $dirty |= i6;
        } else {
            jM1765getPrimary0d7_KjU = j;
        }
        int i7 = i2 & 8;
        if (i7 != 0) {
            $dirty |= 3072;
            fM1882getStrokeWidthD9Ej5fM = f;
        } else if (($changed & 3072) == 0) {
            fM1882getStrokeWidthD9Ej5fM = f;
            $dirty |= $composer2.changed(fM1882getStrokeWidthD9Ej5fM) ? 2048 : 1024;
        } else {
            fM1882getStrokeWidthD9Ej5fM = f;
        }
        int i8 = i2 & 16;
        if (i8 != 0) {
            $dirty |= 24576;
            jM5132getTransparent0d7_KjU = j2;
        } else if (($changed & 24576) == 0) {
            jM5132getTransparent0d7_KjU = j2;
            $dirty |= $composer2.changed(jM5132getTransparent0d7_KjU) ? 16384 : 8192;
        } else {
            jM5132getTransparent0d7_KjU = j2;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i2 & 32) == 0) {
                i3 = i;
                int i9 = $composer2.changed(i3) ? 131072 : 65536;
                $dirty |= i9;
            } else {
                i3 = i;
            }
            $dirty |= i9;
        } else {
            i3 = i;
        }
        boolean z = true;
        if (!$composer2.shouldExecute(($dirty & 74899) != 74898, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = jM1765getPrimary0d7_KjU;
            i4 = i3;
            f2 = fM1882getStrokeWidthD9Ej5fM;
            j4 = jM5132getTransparent0d7_KjU;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "309@13277L6");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i2 & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i2 & 32) != 0) {
                    $dirty &= -458753;
                }
                modifier4 = modifier2;
                iM5471getButtKaPHkGw = i3;
            } else {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 4) != 0) {
                    $dirty &= -897;
                    jM1765getPrimary0d7_KjU = MaterialTheme.INSTANCE.getColors($composer2, 6).m1765getPrimary0d7_KjU();
                }
                if (i7 != 0) {
                    fM1882getStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m1882getStrokeWidthD9Ej5fM();
                }
                if (i8 != 0) {
                    jM5132getTransparent0d7_KjU = Color.INSTANCE.m5132getTransparent0d7_KjU();
                }
                if ((i2 & 32) == 0) {
                    iM5471getButtKaPHkGw = i3;
                } else {
                    $dirty &= -458753;
                    iM5471getButtKaPHkGw = StrokeCap.INSTANCE.m5471getButtKaPHkGw();
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1746618448, $dirty, -1, "androidx.compose.material.CircularProgressIndicator (ProgressIndicator.kt:313)");
            }
            float $this$fastCoerceAtLeast$iv$iv = progress;
            if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
                $this$fastCoerceAtLeast$iv$iv = 0.0f;
            }
            if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
                $this$fastCoerceAtLeast$iv$iv = 1.0f;
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$CircularProgressIndicator_DUhRLBM_u24lambda_u240 = (Density) objConsume;
            final Stroke stroke = new Stroke($this$CircularProgressIndicator_DUhRLBM_u24lambda_u240.mo406toPx0680j_4(fM1882getStrokeWidthD9Ej5fM), 0.0f, iM5471getButtKaPHkGw, 0, null, 26, null);
            Float fValueOf = Float.valueOf($this$fastCoerceAtLeast$iv$iv);
            float it = fValueOf.floatValue();
            if (Float.isNaN(it)) {
                fValueOf = null;
            }
            Modifier modifier5 = modifier4;
            Modifier modifierM865size3ABfNKs = SizeKt.m865size3ABfNKs(ProgressSemanticsKt.progressSemantics$default(modifier5, fValueOf != null ? fValueOf.floatValue() : 0.0f, null, 0, 6, null), CircularIndicatorDiameter);
            ComposerKt.sourceInformationMarkerStart($composer2, -831863413, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean zChanged = $composer2.changed($this$fastCoerceAtLeast$iv$iv) | ((57344 & $dirty) == 16384) | $composer2.changedInstance(stroke);
            if (((($dirty & 896) ^ 384) <= 256 || !$composer2.changed(jM1765getPrimary0d7_KjU)) && ($dirty & 384) != 256) {
                z = false;
            }
            boolean invalid$iv = zChanged | z;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                final long j5 = jM1765getPrimary0d7_KjU;
                final float coercedProgress = $this$fastCoerceAtLeast$iv$iv;
                final long j6 = jM5132getTransparent0d7_KjU;
                Object value$iv = new Function1() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.CircularProgressIndicator_DUhRLBM$lambda$2$0(coercedProgress, j6, stroke, j5, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierM865size3ABfNKs, (Function1) it$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            j3 = jM1765getPrimary0d7_KjU;
            i4 = iM5471getButtKaPHkGw;
            f2 = fM1882getStrokeWidthD9Ej5fM;
            j4 = jM5132getTransparent0d7_KjU;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.CircularProgressIndicator_DUhRLBM$lambda$3(progress, modifier3, j3, f2, j4, i4, $changed, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CircularProgressIndicator_DUhRLBM$lambda$2$0(float $coercedProgress, long j, Stroke $stroke, long j2, DrawScope $this$Canvas) {
        float sweep = $coercedProgress * 360.0f;
        m1897drawCircularIndicatorBackgroundbw27NRU($this$Canvas, j, $stroke);
        m1898drawDeterminateCircularIndicator42QJj7c($this$Canvas, 270.0f, sweep, j2, $stroke);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CircularProgressIndicator-LxG7B9w, reason: not valid java name */
    public static final void m1889CircularProgressIndicatorLxG7B9w(Modifier modifier, long j, float f, long j2, int i, Composer $composer, final int $changed, final int i2) {
        Modifier modifier2;
        long jM1765getPrimary0d7_KjU;
        float fM1882getStrokeWidthD9Ej5fM;
        int i3;
        final int i4;
        final long j3;
        final long j4;
        final Modifier modifier3;
        final float f2;
        Modifier.Companion modifier4;
        long jM5132getTransparent0d7_KjU;
        int $dirty;
        final long j5;
        int iM5473getSquareKaPHkGw;
        long j6;
        float f3;
        final long j7;
        Composer $composer2 = $composer.startRestartGroup(-1119119072);
        ComposerKt.sourceInformation($composer2, "C(CircularProgressIndicator)N(modifier,color:c#ui.graphics.Color,strokeWidth:c#ui.unit.Dp,backgroundColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap)354@15229L7,356@15316L28,359@15482L352,373@15957L209,387@16443L247,382@16292L423,401@16909L256,396@16758L432,408@17264L538,408@17195L607:ProgressIndicator.kt#jmzs0o");
        int $dirty2 = $changed;
        int i5 = i2 & 1;
        if (i5 != 0) {
            $dirty2 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i2 & 2) == 0) {
                jM1765getPrimary0d7_KjU = j;
                int i6 = $composer2.changed(jM1765getPrimary0d7_KjU) ? 32 : 16;
                $dirty2 |= i6;
            } else {
                jM1765getPrimary0d7_KjU = j;
            }
            $dirty2 |= i6;
        } else {
            jM1765getPrimary0d7_KjU = j;
        }
        int i7 = i2 & 4;
        if (i7 != 0) {
            $dirty2 |= 384;
            fM1882getStrokeWidthD9Ej5fM = f;
        } else if (($changed & 384) == 0) {
            fM1882getStrokeWidthD9Ej5fM = f;
            $dirty2 |= $composer2.changed(fM1882getStrokeWidthD9Ej5fM) ? 256 : 128;
        } else {
            fM1882getStrokeWidthD9Ej5fM = f;
        }
        int i8 = i2 & 8;
        if (i8 != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer2.changed(j2) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            if ((i2 & 16) == 0) {
                i3 = i;
                int i9 = $composer2.changed(i3) ? 16384 : 8192;
                $dirty2 |= i9;
            } else {
                i3 = i;
            }
            $dirty2 |= i9;
        } else {
            i3 = i;
        }
        if (!$composer2.shouldExecute(($dirty2 & 9363) != 9362, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            i4 = i3;
            j3 = jM1765getPrimary0d7_KjU;
            j4 = j2;
            modifier3 = modifier2;
            f2 = fM1882getStrokeWidthD9Ej5fM;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "349@15020L6");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i2 & 2) != 0) {
                    $dirty2 &= -113;
                }
                if ((i2 & 16) != 0) {
                    $dirty2 &= -57345;
                }
                j5 = j2;
                $dirty = $dirty2;
                modifier4 = modifier2;
                iM5473getSquareKaPHkGw = i3;
                j6 = jM1765getPrimary0d7_KjU;
                f3 = fM1882getStrokeWidthD9Ej5fM;
            } else {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 2) != 0) {
                    $dirty2 &= -113;
                    jM1765getPrimary0d7_KjU = MaterialTheme.INSTANCE.getColors($composer2, 6).m1765getPrimary0d7_KjU();
                }
                if (i7 != 0) {
                    fM1882getStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m1882getStrokeWidthD9Ej5fM();
                }
                if (i8 == 0) {
                    jM5132getTransparent0d7_KjU = j2;
                } else {
                    jM5132getTransparent0d7_KjU = Color.INSTANCE.m5132getTransparent0d7_KjU();
                }
                if ((i2 & 16) == 0) {
                    $dirty = $dirty2;
                    j5 = jM5132getTransparent0d7_KjU;
                    iM5473getSquareKaPHkGw = i3;
                    j6 = jM1765getPrimary0d7_KjU;
                    f3 = fM1882getStrokeWidthD9Ej5fM;
                } else {
                    j5 = jM5132getTransparent0d7_KjU;
                    iM5473getSquareKaPHkGw = StrokeCap.INSTANCE.m5473getSquareKaPHkGw();
                    j6 = jM1765getPrimary0d7_KjU;
                    $dirty = $dirty2 & (-57345);
                    f3 = fM1882getStrokeWidthD9Ej5fM;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1119119072, $dirty, -1, "androidx.compose.material.CircularProgressIndicator (ProgressIndicator.kt:353)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$CircularProgressIndicator_LxG7B9w_u24lambda_u240 = (Density) objConsume;
            final Stroke stroke = new Stroke($this$CircularProgressIndicator_LxG7B9w_u24lambda_u240.mo406toPx0680j_4(f3), 0.0f, iM5473getSquareKaPHkGw, 0, null, 26, null);
            int i10 = iM5473getSquareKaPHkGw;
            InfiniteTransition transition = InfiniteTransitionKt.rememberInfiniteTransition(null, $composer2, 0, 1);
            final float f4 = f3;
            Modifier modifier5 = modifier4;
            boolean z = true;
            final State currentRotation$delegate = InfiniteTransitionKt.animateValue(transition, 0, 5, VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE), AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(6660, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, $composer2, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 12), 16);
            final State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(transition, 0.0f, BaseRotationAngle, AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(RotationDuration, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), null, $composer2, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            ComposerKt.sourceInformationMarkerStart($composer2, -1731555241, "CC(remember):ProgressIndicator.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.CircularProgressIndicator_LxG7B9w$lambda$3$0((KeyframesSpec.KeyframesSpecConfig) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final State endAngle$delegate = InfiniteTransitionKt.animateFloat(transition, 0.0f, JumpRotationAngle, AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes((Function1) it$iv), null, 0L, 6, null), null, $composer2, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            ComposerKt.sourceInformationMarkerStart($composer2, -1731540320, "CC(remember):ProgressIndicator.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.CircularProgressIndicator_LxG7B9w$lambda$5$0((KeyframesSpec.KeyframesSpecConfig) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final State<Float> stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(transition, 0.0f, JumpRotationAngle, AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes((Function1) it$iv2), null, 0L, 6, null), null, $composer2, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            Modifier modifierM865size3ABfNKs = SizeKt.m865size3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier5), CircularIndicatorDiameter);
            ComposerKt.sourceInformationMarkerStart($composer2, -1731528678, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean zChangedInstance = (($dirty & 7168) == 2048) | $composer2.changedInstance(stroke) | $composer2.changed(currentRotation$delegate) | $composer2.changed(endAngle$delegate) | $composer2.changed(stateAnimateFloat2) | $composer2.changed(stateAnimateFloat) | (($dirty & 896) == 256);
            if (((($dirty & 112) ^ 48) <= 32 || !$composer2.changed(j6)) && ($dirty & 48) != 32) {
                z = false;
            }
            boolean invalid$iv = zChangedInstance | z;
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                j7 = j6;
                Object value$iv3 = new Function1() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.CircularProgressIndicator_LxG7B9w$lambda$7$0(j5, stroke, f4, j7, currentRotation$delegate, endAngle$delegate, stateAnimateFloat2, stateAnimateFloat, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            } else {
                j7 = j6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierM865size3ABfNKs, (Function1) it$iv3, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            i4 = i10;
            j4 = j5;
            f2 = f4;
            j3 = j7;
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.CircularProgressIndicator_LxG7B9w$lambda$8(modifier3, j3, f2, j4, i4, $changed, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final int CircularProgressIndicator_LxG7B9w$lambda$1(State<Integer> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).intValue();
    }

    private static final float CircularProgressIndicator_LxG7B9w$lambda$2(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    private static final float CircularProgressIndicator_LxG7B9w$lambda$4(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CircularProgressIndicator_LxG7B9w$lambda$3$0(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(RotationDuration);
        $this$keyframes.using($this$keyframes.at(Float.valueOf(0.0f), 0), CircularEasing);
        $this$keyframes.at(Float.valueOf(JumpRotationAngle), 666);
        return Unit.INSTANCE;
    }

    private static final float CircularProgressIndicator_LxG7B9w$lambda$6(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CircularProgressIndicator_LxG7B9w$lambda$5$0(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(RotationDuration);
        $this$keyframes.using($this$keyframes.at(Float.valueOf(0.0f), 666), CircularEasing);
        $this$keyframes.at(Float.valueOf(JumpRotationAngle), $this$keyframes.getDurationMillis());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CircularProgressIndicator_LxG7B9w$lambda$7$0(long j, Stroke $stroke, float f, long j2, State $currentRotation$delegate, State $endAngle$delegate, State $startAngle$delegate, State $baseRotation$delegate, DrawScope $this$Canvas) {
        m1897drawCircularIndicatorBackgroundbw27NRU($this$Canvas, j, $stroke);
        float currentRotationAngleOffset = (CircularProgressIndicator_LxG7B9w$lambda$1($currentRotation$delegate) * RotationAngleOffset) % 360.0f;
        float sweep = Math.abs(CircularProgressIndicator_LxG7B9w$lambda$4($endAngle$delegate) - CircularProgressIndicator_LxG7B9w$lambda$6($startAngle$delegate));
        float offset = StartAngleOffset + currentRotationAngleOffset + CircularProgressIndicator_LxG7B9w$lambda$2($baseRotation$delegate);
        m1899drawIndeterminateCircularIndicatorhrjfTZI($this$Canvas, CircularProgressIndicator_LxG7B9w$lambda$6($startAngle$delegate) + offset, f, sweep, j2, $stroke);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: CircularProgressIndicator-MBs18nI, reason: not valid java name */
    public static final /* synthetic */ void m1890CircularProgressIndicatorMBs18nI(final float progress, Modifier modifier, long j, float f, Composer $composer, final int $changed, final int i) {
        float f2;
        Modifier modifier2;
        long j2;
        float f3;
        final Modifier modifier3;
        final long j3;
        final float f4;
        long jM1765getPrimary0d7_KjU;
        Modifier modifier4;
        float fM1882getStrokeWidthD9Ej5fM;
        long j4;
        Composer $composer2 = $composer.startRestartGroup(-409649739);
        ComposerKt.sourceInformation($composer2, "C(CircularProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,strokeWidth:c#ui.unit.Dp)430@18107L185:ProgressIndicator.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            f2 = progress;
            $dirty |= $composer2.changed(f2) ? 4 : 2;
        } else {
            f2 = progress;
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
                j2 = j;
                int i3 = $composer2.changed(j2) ? 256 : 128;
                $dirty |= i3;
            } else {
                j2 = j;
            }
            $dirty |= i3;
        } else {
            j2 = j;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            f3 = f;
        } else if (($changed & 3072) == 0) {
            f3 = f;
            $dirty |= $composer2.changed(f3) ? 2048 : 1024;
        } else {
            f3 = f;
        }
        if ($composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "427@18022L6");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    jM1765getPrimary0d7_KjU = MaterialTheme.INSTANCE.getColors($composer2, 6).m1765getPrimary0d7_KjU();
                    $dirty &= -897;
                } else {
                    jM1765getPrimary0d7_KjU = j2;
                }
                if (i4 != 0) {
                    modifier4 = modifier5;
                    j4 = jM1765getPrimary0d7_KjU;
                    fM1882getStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m1882getStrokeWidthD9Ej5fM();
                } else {
                    modifier4 = modifier5;
                    fM1882getStrokeWidthD9Ej5fM = f3;
                    j4 = jM1765getPrimary0d7_KjU;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier4 = modifier2;
                fM1882getStrokeWidthD9Ej5fM = f3;
                j4 = j2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-409649739, $dirty, -1, "androidx.compose.material.CircularProgressIndicator (ProgressIndicator.kt:430)");
            }
            m1888CircularProgressIndicatorDUhRLBM(f2, modifier4, j4, fM1882getStrokeWidthD9Ej5fM, Color.INSTANCE.m5132getTransparent0d7_KjU(), StrokeCap.INSTANCE.m5471getButtKaPHkGw(), $composer2, ($dirty & 14) | 24576 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            j3 = j4;
            f4 = fM1882getStrokeWidthD9Ej5fM;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = j2;
            f4 = f3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.CircularProgressIndicator_MBs18nI$lambda$0(progress, modifier3, j3, f4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: CircularProgressIndicator-aM-cp0Q, reason: not valid java name */
    public static final /* synthetic */ void m1891CircularProgressIndicatoraMcp0Q(Modifier modifier, long j, float f, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long jM1765getPrimary0d7_KjU;
        float f2;
        final Modifier modifier3;
        final long j2;
        final float f3;
        float fM1882getStrokeWidthD9Ej5fM;
        long j3;
        Modifier modifier4;
        Composer $composer2 = $composer.startRestartGroup(-392089979);
        ComposerKt.sourceInformation($composer2, "C(CircularProgressIndicator)N(modifier,color:c#ui.graphics.Color,strokeWidth:c#ui.unit.Dp)446@18574L169:ProgressIndicator.kt#jmzs0o");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                jM1765getPrimary0d7_KjU = j;
                int i3 = $composer2.changed(jM1765getPrimary0d7_KjU) ? 32 : 16;
                $dirty |= i3;
            } else {
                jM1765getPrimary0d7_KjU = j;
            }
            $dirty |= i3;
        } else {
            jM1765getPrimary0d7_KjU = j;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            f2 = f;
        } else if (($changed & 384) == 0) {
            f2 = f;
            $dirty |= $composer2.changed(f2) ? 256 : 128;
        } else {
            f2 = f;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "443@18489L6");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    jM1765getPrimary0d7_KjU = MaterialTheme.INSTANCE.getColors($composer2, 6).m1765getPrimary0d7_KjU();
                    $dirty &= -113;
                }
                if (i4 != 0) {
                    fM1882getStrokeWidthD9Ej5fM = ProgressIndicatorDefaults.INSTANCE.m1882getStrokeWidthD9Ej5fM();
                    j3 = jM1765getPrimary0d7_KjU;
                    modifier4 = modifier5;
                } else {
                    fM1882getStrokeWidthD9Ej5fM = f2;
                    j3 = jM1765getPrimary0d7_KjU;
                    modifier4 = modifier5;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                fM1882getStrokeWidthD9Ej5fM = f2;
                j3 = jM1765getPrimary0d7_KjU;
                modifier4 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-392089979, $dirty, -1, "androidx.compose.material.CircularProgressIndicator (ProgressIndicator.kt:446)");
            }
            m1889CircularProgressIndicatorLxG7B9w(modifier4, j3, fM1882getStrokeWidthD9Ej5fM, Color.INSTANCE.m5132getTransparent0d7_KjU(), StrokeCap.INSTANCE.m5473getSquareKaPHkGw(), $composer2, ($dirty & 14) | 3072 | ($dirty & 112) | ($dirty & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j2 = j3;
            f3 = fM1882getStrokeWidthD9Ej5fM;
            modifier3 = modifier4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j2 = jM1765getPrimary0d7_KjU;
            f3 = f2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ProgressIndicatorKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.CircularProgressIndicator_aM_cp0Q$lambda$0(modifier3, j2, f3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: drawCircularIndicator-42QJj7c, reason: not valid java name */
    private static final void m1896drawCircularIndicator42QJj7c(DrawScope $this$drawCircularIndicator_u2d42QJj7c, float startAngle, float sweep, long j, Stroke stroke) {
        float diameterOffset = stroke.getWidth() / 2.0f;
        long arg0$iv = $this$drawCircularIndicator_u2d42QJj7c.mo5671getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float arcDimen = Float.intBitsToFloat(bits$iv$iv$iv) - (2.0f * diameterOffset);
        long v1$iv$iv = Float.floatToRawIntBits(diameterOffset);
        long v2$iv$iv = Float.floatToRawIntBits(diameterOffset);
        long jM4844constructorimpl = Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        long v1$iv$iv2 = Float.floatToRawIntBits(arcDimen);
        long v2$iv$iv2 = Float.floatToRawIntBits(arcDimen);
        DrawScope.m5650drawArcyD3GUKo$default($this$drawCircularIndicator_u2d42QJj7c, j, startAngle, sweep, false, jM4844constructorimpl, Size.m4912constructorimpl((v1$iv$iv2 << 32) | (4294967295L & v2$iv$iv2)), 0.0f, stroke, null, 0, 832, null);
    }

    /* JADX INFO: renamed from: drawCircularIndicatorBackground-bw27NRU, reason: not valid java name */
    private static final void m1897drawCircularIndicatorBackgroundbw27NRU(DrawScope $this$drawCircularIndicatorBackground_u2dbw27NRU, long j, Stroke stroke) {
        m1896drawCircularIndicator42QJj7c($this$drawCircularIndicatorBackground_u2dbw27NRU, 0.0f, 360.0f, j, stroke);
    }

    /* JADX INFO: renamed from: drawDeterminateCircularIndicator-42QJj7c, reason: not valid java name */
    private static final void m1898drawDeterminateCircularIndicator42QJj7c(DrawScope $this$drawDeterminateCircularIndicator_u2d42QJj7c, float startAngle, float sweep, long j, Stroke stroke) {
        m1896drawCircularIndicator42QJj7c($this$drawDeterminateCircularIndicator_u2d42QJj7c, startAngle, sweep, j, stroke);
    }

    /* JADX INFO: renamed from: drawIndeterminateCircularIndicator-hrjfTZI, reason: not valid java name */
    private static final void m1899drawIndeterminateCircularIndicatorhrjfTZI(DrawScope $this$drawIndeterminateCircularIndicator_u2dhrjfTZI, float startAngle, float f, float sweep, long j, Stroke stroke) {
        float strokeCapOffset;
        if (StrokeCap.m5467equalsimpl0(stroke.getCap(), StrokeCap.INSTANCE.m5471getButtKaPHkGw())) {
            strokeCapOffset = 0.0f;
        } else {
            float arg0$iv = CircularIndicatorDiameter;
            strokeCapOffset = ((f / Dp.m7905constructorimpl(arg0$iv / 2)) * 57.29578f) / 2.0f;
        }
        float adjustedStartAngle = startAngle + strokeCapOffset;
        float adjustedSweep = Math.max(sweep, 0.1f);
        m1896drawCircularIndicator42QJj7c($this$drawIndeterminateCircularIndicator_u2dhrjfTZI, adjustedStartAngle, adjustedSweep, j, stroke);
    }
}
