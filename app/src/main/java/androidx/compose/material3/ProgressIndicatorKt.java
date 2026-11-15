package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.internal.AccessibilityUtilKt;
import androidx.compose.material3.tokens.CircularProgressIndicatorTokens;
import androidx.compose.material3.tokens.LinearProgressIndicatorTokens;
import androidx.compose.material3.tokens.MotionTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ProgressIndicator.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\u001aE\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\f\u0010\r\u001aj\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0019\b\u0002\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001a7\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u0016\u0010\u0017\u001aA\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0018\u0010\u0019\u001a?\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\f\u0010\u001a\u001a5\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001a-\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u001d\u0010\u001e\u001a;\u0010\u001f\u001a\u00020\u0001*\u00020\u00122\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0004\b#\u0010$\u001aO\u0010%\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b&\u0010'\u001aY\u0010%\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0004\b(\u0010)\u001aA\u0010%\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b*\u0010+\u001aK\u0010%\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0004\b,\u0010-\u001aI\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b&\u0010.\u001a5\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000fH\u0007¢\u0006\u0004\b/\u00100\u001a-\u0010%\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000fH\u0007¢\u0006\u0004\b1\u00102\u001a3\u00103\u001a\u00020\u0001*\u00020\u00122\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u00106\u001a\u000207H\u0002¢\u0006\u0004\b8\u00109\u001a#\u0010:\u001a\u00020\u0001*\u00020\u00122\u0006\u0010\u0007\u001a\u00020\b2\u0006\u00106\u001a\u000207H\u0002¢\u0006\u0004\b;\u0010<\u001a3\u0010=\u001a\u00020\u0001*\u00020\u00122\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u00106\u001a\u000207H\u0002¢\u0006\u0004\b>\u00109\u001a;\u0010?\u001a\u00020\u0001*\u00020\u00122\u0006\u00104\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u000f2\u0006\u00105\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u00106\u001a\u000207H\u0002¢\u0006\u0004\b@\u0010A\"\u001a\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bD\u0010E\"\u001a\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010E\"\u001a\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bI\u0010E\"\u001a\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bK\u0010E\"\u001a\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bM\u0010E\"\u001a\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bO\u0010E\"\u001a\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010E\"\u0016\u0010R\u001a\u00020\u000fX\u0080\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\bS\u0010T\"\u0016\u0010V\u001a\u00020\u000fX\u0080\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\bW\u0010T\"\u0016\u0010X\u001a\u00020\u000fX\u0080\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\bY\u0010T\"\u0016\u0010Z\u001a\u00020\u000fX\u0080\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\b[\u0010T\"\u000e\u0010\\\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010^\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010_\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010`\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010a\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010b\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010c\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010d\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010e\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u0014\u0010f\u001a\u00020gX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010i\"\u0014\u0010j\u001a\u00020gX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bk\u0010i\"\u000e\u0010l\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010m\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010n\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010o\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010p\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010q\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010r\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006s"}, d2 = {"LinearProgressIndicator", "", "progress", "Lkotlin/Function0;", "", "modifier", "Landroidx/compose/ui/Modifier;", "color", "Landroidx/compose/ui/graphics/Color;", "trackColor", "strokeCap", "Landroidx/compose/ui/graphics/StrokeCap;", "LinearProgressIndicator-_5eSR-E", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "gapSize", "Landroidx/compose/ui/unit/Dp;", "drawStopIndicator", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lkotlin/ExtensionFunctionType;", "LinearProgressIndicator-GJbTh5U", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JJIFLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-2cYBFYY", "(Landroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-rIrjwxo", "(Landroidx/compose/ui/Modifier;JJIFLandroidx/compose/runtime/Composer;II)V", "(FLandroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-eaDK9VM", "(FLandroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-RIQooxk", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "drawLinearIndicator", "startFraction", "endFraction", "strokeWidth", "drawLinearIndicator-qYKTg0g", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJFI)V", "CircularProgressIndicator", "CircularProgressIndicator-DUhRLBM", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-IyT6zlY", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JFJIFLandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-LxG7B9w", "(Landroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-4lLiAd8", "(Landroidx/compose/ui/Modifier;JFJIFLandroidx/compose/runtime/Composer;II)V", "(FLandroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-MBs18nI", "(FLandroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-aM-cp0Q", "(Landroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "drawCircularIndicator", "startAngle", "sweep", "stroke", "Landroidx/compose/ui/graphics/drawscope/Stroke;", "drawCircularIndicator-42QJj7c", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawCircularIndicatorTrack", "drawCircularIndicatorTrack-bw27NRU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawDeterminateCircularIndicator", "drawDeterminateCircularIndicator-42QJj7c", "drawIndeterminateCircularIndicator", "drawIndeterminateCircularIndicator-hrjfTZI", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "circularIndeterminateGlobalRotationAnimationSpec", "Landroidx/compose/animation/core/InfiniteRepeatableSpec;", "getCircularIndeterminateGlobalRotationAnimationSpec", "()Landroidx/compose/animation/core/InfiniteRepeatableSpec;", "circularIndeterminateRotationAnimationSpec", "getCircularIndeterminateRotationAnimationSpec", "circularIndeterminateProgressAnimationSpec", "getCircularIndeterminateProgressAnimationSpec", "linearIndeterminateFirstLineHeadAnimationSpec", "getLinearIndeterminateFirstLineHeadAnimationSpec", "linearIndeterminateFirstLineTailAnimationSpec", "getLinearIndeterminateFirstLineTailAnimationSpec", "linearIndeterminateSecondLineHeadAnimationSpec", "getLinearIndeterminateSecondLineHeadAnimationSpec", "linearIndeterminateSecondLineTailAnimationSpec", "getLinearIndeterminateSecondLineTailAnimationSpec", "LinearIndicatorWidth", "getLinearIndicatorWidth", "()F", "F", "LinearIndicatorHeight", "getLinearIndicatorHeight", "StopIndicatorTrailingSpace", "getStopIndicatorTrailingSpace", "CircularIndicatorDiameter", "getCircularIndicatorDiameter", "LinearAnimationDuration", "", "FirstLineHeadDuration", "FirstLineTailDuration", "SecondLineHeadDuration", "SecondLineTailDuration", "FirstLineHeadDelay", "FirstLineTailDelay", "SecondLineHeadDelay", "SecondLineTailDelay", "LinearIndeterminateProgressEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "getLinearIndeterminateProgressEasing", "()Landroidx/compose/animation/core/CubicBezierEasing;", "CircularProgressEasing", "getCircularProgressEasing", "CircularIndeterminateMinProgress", "CircularIndeterminateMaxProgress", "CircularAnimationProgressDuration", "CircularAnimationAdditionalRotationDelay", "CircularAnimationAdditionalRotationDuration", "CircularAdditionalRotationDegreesTarget", "CircularGlobalRotationDegreesTarget", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ProgressIndicatorKt {
    public static final float CircularAdditionalRotationDegreesTarget = 360.0f;
    public static final int CircularAnimationAdditionalRotationDelay = 1500;
    public static final int CircularAnimationAdditionalRotationDuration = 300;
    public static final int CircularAnimationProgressDuration = 6000;
    public static final float CircularGlobalRotationDegreesTarget = 1080.0f;
    public static final float CircularIndeterminateMaxProgress = 0.87f;
    public static final float CircularIndeterminateMinProgress = 0.1f;
    public static final int FirstLineHeadDelay = 0;
    public static final int FirstLineHeadDuration = 1000;
    public static final int FirstLineTailDelay = 250;
    public static final int FirstLineTailDuration = 1000;
    public static final int LinearAnimationDuration = 1750;
    public static final int SecondLineHeadDelay = 650;
    public static final int SecondLineHeadDuration = 850;
    public static final int SecondLineTailDelay = 900;
    public static final int SecondLineTailDuration = 850;
    private static final float LinearIndicatorWidth = Dp.m7905constructorimpl(PsExtractor.VIDEO_STREAM_MASK);
    private static final float LinearIndicatorHeight = LinearProgressIndicatorTokens.INSTANCE.m3856getHeightD9Ej5fM();
    private static final float StopIndicatorTrailingSpace = Dp.m7905constructorimpl(6);
    private static final float CircularIndicatorDiameter = CircularProgressIndicatorTokens.INSTANCE.m3565getSizeD9Ej5fM();
    private static final CubicBezierEasing LinearIndeterminateProgressEasing = MotionTokens.INSTANCE.getEasingEmphasizedAccelerateCubicBezier();
    private static final CubicBezierEasing CircularProgressEasing = MotionTokens.INSTANCE.getEasingStandardCubicBezier();

    static final Unit CircularProgressIndicator_4lLiAd8$lambda$35(Modifier modifier, long j, float f, long j2, int i, float f2, int i2, int i3, Composer composer, int i4) {
        m2727CircularProgressIndicator4lLiAd8(modifier, j, f, j2, i, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit CircularProgressIndicator_DUhRLBM$lambda$20(Function0 function0, Modifier modifier, long j, float f, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m2729CircularProgressIndicatorDUhRLBM(function0, modifier, j, f, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit CircularProgressIndicator_DUhRLBM$lambda$38(float f, Modifier modifier, long j, float f2, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m2728CircularProgressIndicatorDUhRLBM(f, modifier, j, f2, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit CircularProgressIndicator_IyT6zlY$lambda$29(Function0 function0, Modifier modifier, long j, float f, long j2, int i, float f2, int i2, int i3, Composer composer, int i4) {
        m2730CircularProgressIndicatorIyT6zlY(function0, modifier, j, f, j2, i, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit CircularProgressIndicator_LxG7B9w$lambda$30(Modifier modifier, long j, float f, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m2731CircularProgressIndicatorLxG7B9w(modifier, j, f, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit CircularProgressIndicator_MBs18nI$lambda$39(float f, Modifier modifier, long j, float f2, int i, int i2, Composer composer, int i3) {
        m2732CircularProgressIndicatorMBs18nI(f, modifier, j, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit CircularProgressIndicator_aM_cp0Q$lambda$40(Modifier modifier, long j, float f, int i, int i2, Composer composer, int i3) {
        m2733CircularProgressIndicatoraMcp0Q(modifier, j, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator_2cYBFYY$lambda$11(Modifier modifier, long j, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m2734LinearProgressIndicator2cYBFYY(modifier, j, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator_GJbTh5U$lambda$10(Function0 function0, Modifier modifier, long j, long j2, int i, float f, Function1 function1, int i2, int i3, Composer composer, int i4) {
        m2735LinearProgressIndicatorGJbTh5U(function0, modifier, j, j2, i, f, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator_RIQooxk$lambda$19(Modifier modifier, long j, long j2, int i, int i2, Composer composer, int i3) {
        m2736LinearProgressIndicatorRIQooxk(modifier, j, j2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator__5eSR_E$lambda$0(Function0 function0, Modifier modifier, long j, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m2738LinearProgressIndicator_5eSRE(function0, modifier, j, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator__5eSR_E$lambda$17(float f, Modifier modifier, long j, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m2737LinearProgressIndicator_5eSRE(f, modifier, j, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator_eaDK9VM$lambda$18(float f, Modifier modifier, long j, long j2, int i, int i2, Composer composer, int i3) {
        m2739LinearProgressIndicatoreaDK9VM(f, modifier, j, j2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator_rIrjwxo$lambda$14(Modifier modifier, long j, long j2, int i, float f, int i2, int i3, Composer composer, int i4) {
        m2740LinearProgressIndicatorrIrjwxo(modifier, j, j2, i, f, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize` and `drawStopIndicator`, see `LegacyLinearProgressIndicatorSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "LinearProgressIndicator(progress, modifier, color, trackColor, strokeCap, gapSize, drawStopIndicator)", imports = {}))
    /* JADX INFO: renamed from: LinearProgressIndicator-_5eSR-E, reason: not valid java name */
    public static final /* synthetic */ void m2738LinearProgressIndicator_5eSRE(final Function0 progress, Modifier modifier, long color, long trackColor, int strokeCap, Composer $composer, final int $changed, final int i) {
        Function0 function0;
        Modifier modifier2;
        long j;
        long trackColor2;
        int i2;
        Composer $composer2;
        final Modifier modifier3;
        final long color2;
        final long trackColor3;
        final int strokeCap2;
        Modifier.Companion modifier4;
        long color3;
        long trackColor4;
        int strokeCap3;
        Modifier modifier5;
        long color4;
        Composer $composer3 = $composer.startRestartGroup(-1796992155);
        ComposerKt.sourceInformation($composer3, "C(LinearProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap)101@4670L193:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function0 = progress;
        } else if (($changed & 6) == 0) {
            function0 = progress;
            $dirty |= $composer3.changedInstance(function0) ? 4 : 2;
        } else {
            function0 = progress;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j = color;
                int i4 = $composer3.changed(j) ? 256 : 128;
                $dirty |= i4;
            } else {
                j = color;
            }
            $dirty |= i4;
        } else {
            j = color;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                trackColor2 = trackColor;
                int i5 = $composer3.changed(trackColor2) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                trackColor2 = trackColor;
            }
            $dirty |= i5;
        } else {
            trackColor2 = trackColor;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            i2 = strokeCap;
        } else if (($changed & 24576) == 0) {
            i2 = strokeCap;
            $dirty |= $composer3.changed(i2) ? 16384 : 8192;
        } else {
            i2 = strokeCap;
        }
        if (!$composer3.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color2 = j;
            trackColor3 = trackColor2;
            strokeCap2 = i2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "97@4511L11,98@4574L16");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                trackColor4 = trackColor2;
                strokeCap3 = i2;
                modifier5 = modifier2;
                color4 = j;
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 4) == 0) {
                    color3 = j;
                } else {
                    color3 = ProgressIndicatorDefaults.INSTANCE.getLinearColor($composer3, 6);
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    trackColor2 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor($composer3, 6);
                    $dirty &= -7169;
                }
                if (i6 == 0) {
                    trackColor4 = trackColor2;
                    strokeCap3 = i2;
                    modifier5 = modifier4;
                    color4 = color3;
                } else {
                    strokeCap3 = ProgressIndicatorDefaults.INSTANCE.m2725getLinearStrokeCapKaPHkGw();
                    trackColor4 = trackColor2;
                    modifier5 = modifier4;
                    color4 = color3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1796992155, $dirty, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:100)");
            }
            $composer2 = $composer3;
            m2735LinearProgressIndicatorGJbTh5U(function0, modifier5, color4, trackColor4, strokeCap3, ProgressIndicatorDefaults.INSTANCE.m2724getLinearIndicatorTrackGapSizeD9Ej5fM(), null, $composer2, ($dirty & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            color2 = color4;
            trackColor3 = trackColor4;
            strokeCap2 = strokeCap3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator__5eSR_E$lambda$0(progress, modifier3, color2, trackColor3, strokeCap2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit LinearProgressIndicator_GJbTh5U$lambda$2$lambda$1(long $color, int $strokeCap, DrawScope drawScope) throws Throwable {
        ProgressIndicatorDefaults.INSTANCE.m2719drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2726getLinearTrackStopIndicatorSizeD9Ej5fM(), $color, $strokeCap);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:156:0x0260  */
    /* JADX WARN: Code duplicated, block: B:157:0x0262  */
    /* JADX WARN: Code duplicated, block: B:160:0x026a  */
    /* JADX WARN: Code duplicated, block: B:161:0x026c  */
    /* JADX WARN: Code duplicated, block: B:191:0x02d5  */
    /* JADX WARN: Code duplicated, block: B:194:0x02f9  */
    /* JADX INFO: renamed from: LinearProgressIndicator-GJbTh5U, reason: not valid java name */
    public static final void m2735LinearProgressIndicatorGJbTh5U(final Function0<Float> function0, Modifier modifier, long color, long trackColor, int strokeCap, float gapSize, Function1<? super DrawScope, Unit> function1, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final long color2;
        long trackColor2;
        final int strokeCap2;
        float gapSize2;
        final float gapSize3;
        final long color3;
        final Modifier modifier3;
        final long trackColor3;
        final Function1<? super DrawScope, Unit> function2;
        final int strokeCap3;
        Function1<? super DrawScope, Unit> function3;
        final float gapSize4;
        final int strokeCap4;
        int $dirty;
        Modifier modifier4;
        boolean z;
        boolean z2;
        boolean invalid$iv;
        Object it$iv;
        final Function1<? super DrawScope, Unit> function4;
        final long color4;
        final long trackColor4;
        Composer $composer2 = $composer.startRestartGroup(-339970038);
        ComposerKt.sourceInformation($composer2, "C(LinearProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap,gapSize:c#ui.unit.Dp,drawStopIndicator)154@7102L35,158@7266L241,164@7577L806,155@7142L1241:ProgressIndicator.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(function0) ? 4 : 2;
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
                color2 = color;
                int i3 = $composer2.changed(color2) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                color2 = color;
            }
            $dirty2 |= i3;
        } else {
            color2 = color;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                trackColor2 = trackColor;
                int i4 = $composer2.changed(trackColor2) ? 2048 : 1024;
                $dirty2 |= i4;
            } else {
                trackColor2 = trackColor;
            }
            $dirty2 |= i4;
        } else {
            trackColor2 = trackColor;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty2 |= 24576;
            strokeCap2 = strokeCap;
        } else if (($changed & 24576) == 0) {
            strokeCap2 = strokeCap;
            $dirty2 |= $composer2.changed(strokeCap2) ? 16384 : 8192;
        } else {
            strokeCap2 = strokeCap;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            gapSize2 = gapSize;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            gapSize2 = gapSize;
            $dirty2 |= $composer2.changed(gapSize2) ? 131072 : 65536;
        } else {
            gapSize2 = gapSize;
        }
        if (($changed & 1572864) == 0) {
            $dirty2 |= ((i & 64) == 0 && $composer2.changedInstance(function1)) ? 1048576 : 524288;
        }
        if ($composer2.shouldExecute(($dirty2 & 599187) != 599186, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "141@6585L11,142@6648L16,145@6855L215");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                function3 = function1;
                gapSize4 = gapSize2;
                strokeCap4 = strokeCap2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    color2 = ProgressIndicatorDefaults.INSTANCE.getLinearColor($composer2, 6);
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    trackColor2 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor($composer2, 6);
                    $dirty2 &= -7169;
                }
                if (i5 != 0) {
                    strokeCap2 = ProgressIndicatorDefaults.INSTANCE.m2725getLinearStrokeCapKaPHkGw();
                }
                if (i6 != 0) {
                    gapSize2 = ProgressIndicatorDefaults.INSTANCE.m2724getLinearIndicatorTrackGapSizeD9Ej5fM();
                }
                if ((i & 64) != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, -293677151, "CC(remember):ProgressIndicator.kt#9igjgp");
                    boolean invalid$iv2 = (((($dirty2 & 896) ^ 384) > 256 && $composer2.changed(color2)) || ($dirty2 & 384) == 256) | ((57344 & $dirty2) == 16384);
                    Object value$iv = $composer2.rememberedValue();
                    if (!invalid$iv2) {
                        $dirty = $dirty2;
                        if (value$iv == Composer.INSTANCE.getEmpty()) {
                        }
                        function3 = (Function1) value$iv;
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $dirty2 = $dirty & (-3670017);
                        gapSize4 = gapSize2;
                        strokeCap4 = strokeCap2;
                    } else {
                        $dirty = $dirty2;
                    }
                    value$iv = new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.LinearProgressIndicator_GJbTh5U$lambda$2$lambda$1(color2, strokeCap2, (DrawScope) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    function3 = (Function1) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty2 = $dirty & (-3670017);
                    gapSize4 = gapSize2;
                    strokeCap4 = strokeCap2;
                } else {
                    function3 = function1;
                    gapSize4 = gapSize2;
                    strokeCap4 = strokeCap2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-339970038, $dirty2, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:153)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -293669427, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean invalid$iv3 = ($dirty2 & 14) == 4;
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function0() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(ProgressIndicatorKt.LinearProgressIndicator_GJbTh5U$lambda$4$lambda$3(function0));
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            final Function0 coercedProgress = (Function0) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierThen = modifier2.then(AccessibilityUtilKt.getIncreaseVerticalSemanticsBounds());
            ComposerKt.sourceInformationMarkerStart($composer2, -293663973, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean invalid$iv4 = $composer2.changed(coercedProgress);
            Object value$iv3 = $composer2.rememberedValue();
            if (!invalid$iv4) {
                modifier4 = modifier2;
                if (value$iv3 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                boolean z3 = true;
                Modifier modifierM867sizeVpY3zN4 = SizeKt.m867sizeVpY3zN4(SemanticsModifierKt.semantics(modifierThen, true, (Function1) value$iv3), LinearIndicatorWidth, LinearIndicatorHeight);
                ComposerKt.sourceInformationMarkerStart($composer2, -293653456, "CC(remember):ProgressIndicator.kt#9igjgp");
                if ((57344 & $dirty2) == 16384) {
                    z = true;
                } else {
                    z = false;
                }
                if ((458752 & $dirty2) == 131072) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean zChanged = z | z2 | $composer2.changed(coercedProgress) | (((($dirty2 & 7168) ^ 3072) <= 2048 && $composer2.changed(trackColor2)) || ($dirty2 & 3072) == 2048) | (((($dirty2 & 896) ^ 384) <= 256 && $composer2.changed(color2)) || ($dirty2 & 384) == 256);
                if ((((3670016 & $dirty2) ^ 1572864) > 1048576 || !$composer2.changed(function3)) && ($dirty2 & 1572864) != 1048576) {
                }
                invalid$iv = zChanged | z3;
                it$iv = $composer2.rememberedValue();
                if (!invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    function4 = function3;
                    color4 = color2;
                    trackColor4 = trackColor2;
                    Object value$iv4 = new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ProgressIndicatorKt.LinearProgressIndicator_GJbTh5U$lambda$9$lambda$8(strokeCap4, gapSize4, coercedProgress, trackColor4, color4, function4, (DrawScope) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv4);
                    it$iv = value$iv4;
                } else {
                    function4 = function3;
                    color4 = color2;
                    trackColor4 = trackColor2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(modifierM867sizeVpY3zN4, (Function1) it$iv, $composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                strokeCap3 = strokeCap4;
                gapSize3 = gapSize4;
                trackColor3 = trackColor4;
                color3 = color4;
                function2 = function4;
                modifier3 = modifier4;
            } else {
                modifier4 = modifier2;
            }
            value$iv3 = new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ProgressIndicatorKt.LinearProgressIndicator_GJbTh5U$lambda$7$lambda$6(coercedProgress, (SemanticsPropertyReceiver) obj);
                }
            };
            $composer2.updateRememberedValue(value$iv3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            boolean z4 = true;
            Modifier modifierM867sizeVpY3zN5 = SizeKt.m867sizeVpY3zN4(SemanticsModifierKt.semantics(modifierThen, true, (Function1) value$iv3), LinearIndicatorWidth, LinearIndicatorHeight);
            ComposerKt.sourceInformationMarkerStart($composer2, -293653456, "CC(remember):ProgressIndicator.kt#9igjgp");
            if ((57344 & $dirty2) == 16384) {
                z = true;
            } else {
                z = false;
            }
            if ((458752 & $dirty2) == 131072) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean zChanged2 = z | z2 | $composer2.changed(coercedProgress) | (((($dirty2 & 7168) ^ 3072) <= 2048 && $composer2.changed(trackColor2)) || ($dirty2 & 3072) == 2048) | (((($dirty2 & 896) ^ 384) <= 256 && $composer2.changed(color2)) || ($dirty2 & 384) == 256);
            z4 = ((3670016 & $dirty2) ^ 1572864) > 1048576 ? false : false;
            invalid$iv = zChanged2 | z4;
            it$iv = $composer2.rememberedValue();
            if (!invalid$iv) {
                function4 = function3;
                color4 = color2;
                trackColor4 = trackColor2;
                Object value$iv5 = new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.LinearProgressIndicator_GJbTh5U$lambda$9$lambda$8(strokeCap4, gapSize4, coercedProgress, trackColor4, color4, function4, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv5);
                it$iv = value$iv5;
            } else {
                function4 = function3;
                color4 = color2;
                trackColor4 = trackColor2;
                Object value$iv6 = new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.LinearProgressIndicator_GJbTh5U$lambda$9$lambda$8(strokeCap4, gapSize4, coercedProgress, trackColor4, color4, function4, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv6);
                it$iv = value$iv6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierM867sizeVpY3zN5, (Function1) it$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            strokeCap3 = strokeCap4;
            gapSize3 = gapSize4;
            trackColor3 = trackColor4;
            color3 = color4;
            function2 = function4;
            modifier3 = modifier4;
        } else {
            $composer2.skipToGroupEnd();
            long j = color2;
            gapSize3 = gapSize2;
            color3 = j;
            modifier3 = modifier2;
            trackColor3 = trackColor2;
            function2 = function1;
            strokeCap3 = strokeCap2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator_GJbTh5U$lambda$10(function0, modifier3, color3, trackColor3, strokeCap3, gapSize3, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final float LinearProgressIndicator_GJbTh5U$lambda$4$lambda$3(Function0 $progress) {
        float $this$fastCoerceIn$iv = ((Number) $progress.invoke()).floatValue();
        float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
            return 1.0f;
        }
        return $this$fastCoerceAtLeast$iv$iv;
    }

    static final Unit LinearProgressIndicator_GJbTh5U$lambda$7$lambda$6(Function0 $coercedProgress, SemanticsPropertyReceiver $this$semantics) {
        Object objInvoke = $coercedProgress.invoke();
        float it = ((Number) objInvoke).floatValue();
        if (Float.isNaN(it)) {
            objInvoke = null;
        }
        Float f = (Float) objInvoke;
        SemanticsPropertiesKt.setProgressBarRangeInfo($this$semantics, new ProgressBarRangeInfo(f != null ? f.floatValue() : 0.0f, RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0058  */
    static final Unit LinearProgressIndicator_GJbTh5U$lambda$9$lambda$8(int $strokeCap, float $gapSize, Function0 $coercedProgress, long $trackColor, long $color, Function1 $drawStopIndicator, DrawScope $this$Canvas) {
        float fM7905constructorimpl;
        long arg0$iv = $this$Canvas.mo5671getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv & 4294967295L);
        float strokeWidth = Float.intBitsToFloat(bits$iv$iv$iv);
        if (StrokeCap.m5467equalsimpl0($strokeCap, StrokeCap.INSTANCE.m5471getButtKaPHkGw())) {
            fM7905constructorimpl = $gapSize;
        } else {
            long arg0$iv2 = $this$Canvas.mo5671getSizeNHjbRc();
            int bits$iv$iv$iv2 = (int) (4294967295L & arg0$iv2);
            float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv2);
            long arg0$iv3 = $this$Canvas.mo5671getSizeNHjbRc();
            int bits$iv$iv$iv3 = (int) (arg0$iv3 >> 32);
            if (fIntBitsToFloat <= Float.intBitsToFloat(bits$iv$iv$iv3)) {
                float other$iv = $this$Canvas.mo402toDpu2uoSUM(strokeWidth);
                fM7905constructorimpl = Dp.m7905constructorimpl($gapSize + other$iv);
            } else {
                fM7905constructorimpl = $gapSize;
            }
        }
        float adjustedGapSize = fM7905constructorimpl;
        long arg0$iv4 = $this$Canvas.mo5671getSizeNHjbRc();
        int bits$iv$iv$iv4 = (int) (arg0$iv4 >> 32);
        float other$iv2 = $this$Canvas.mo402toDpu2uoSUM(Float.intBitsToFloat(bits$iv$iv$iv4));
        float currentCoercedProgress = ((Number) $coercedProgress.invoke()).floatValue();
        float trackStartFraction = Math.min(currentCoercedProgress, adjustedGapSize / other$iv2) + currentCoercedProgress;
        if (trackStartFraction <= 1.0f) {
            m2745drawLinearIndicatorqYKTg0g($this$Canvas, trackStartFraction, 1.0f, $trackColor, strokeWidth, $strokeCap);
        }
        m2745drawLinearIndicatorqYKTg0g($this$Canvas, 0.0f, currentCoercedProgress, $color, strokeWidth, $strokeCap);
        $drawStopIndicator.invoke($this$Canvas);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize`, see `LegacyIndeterminateLinearProgressIndicatorSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "LinearProgressIndicator(modifier, color, trackColor, strokeCap, gapSize)", imports = {}))
    /* JADX INFO: renamed from: LinearProgressIndicator-2cYBFYY, reason: not valid java name */
    public static final /* synthetic */ void m2734LinearProgressIndicator2cYBFYY(Modifier modifier, long color, long trackColor, int strokeCap, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        long trackColor2;
        int i2;
        final Modifier modifier3;
        final long color2;
        final long trackColor3;
        final int strokeCap2;
        Modifier.Companion modifier4;
        long color3;
        long trackColor4;
        int strokeCap3;
        Modifier modifier5;
        long color4;
        Composer $composer2 = $composer.startRestartGroup(-476865359);
        ComposerKt.sourceInformation($composer2, "C(LinearProgressIndicator)N(modifier,color:c#ui.graphics.Color,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap)220@9927L175:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
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
                j = color;
                int i4 = $composer2.changed(j) ? 32 : 16;
                $dirty |= i4;
            } else {
                j = color;
            }
            $dirty |= i4;
        } else {
            j = color;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                trackColor2 = trackColor;
                int i5 = $composer2.changed(trackColor2) ? 256 : 128;
                $dirty |= i5;
            } else {
                trackColor2 = trackColor;
            }
            $dirty |= i5;
        } else {
            trackColor2 = trackColor;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            i2 = strokeCap;
        } else if (($changed & 3072) == 0) {
            i2 = strokeCap;
            $dirty |= $composer2.changed(i2) ? 2048 : 1024;
        } else {
            i2 = strokeCap;
        }
        if (!$composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color2 = j;
            trackColor3 = trackColor2;
            strokeCap2 = i2;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "216@9768L11,217@9831L16");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                trackColor4 = trackColor2;
                strokeCap3 = i2;
                modifier5 = modifier2;
                color4 = j;
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 2) == 0) {
                    color3 = j;
                } else {
                    color3 = ProgressIndicatorDefaults.INSTANCE.getLinearColor($composer2, 6);
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    trackColor2 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor($composer2, 6);
                    $dirty &= -897;
                }
                if (i6 == 0) {
                    trackColor4 = trackColor2;
                    strokeCap3 = i2;
                    modifier5 = modifier4;
                    color4 = color3;
                } else {
                    strokeCap3 = ProgressIndicatorDefaults.INSTANCE.m2725getLinearStrokeCapKaPHkGw();
                    trackColor4 = trackColor2;
                    modifier5 = modifier4;
                    color4 = color3;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-476865359, $dirty, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:219)");
            }
            m2740LinearProgressIndicatorrIrjwxo(modifier5, color4, trackColor4, strokeCap3, ProgressIndicatorDefaults.INSTANCE.m2724getLinearIndicatorTrackGapSizeD9Ej5fM(), $composer2, ($dirty & 14) | 24576 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            color2 = color4;
            trackColor3 = trackColor4;
            strokeCap2 = strokeCap3;
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator_2cYBFYY$lambda$11(modifier3, color2, trackColor3, strokeCap2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: LinearProgressIndicator-rIrjwxo, reason: not valid java name */
    public static final void m2740LinearProgressIndicatorrIrjwxo(Modifier modifier, long color, long trackColor, int strokeCap, float gapSize, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long color2;
        long trackColor2;
        int strokeCap2;
        final float gapSize2;
        Composer $composer2;
        final Modifier modifier3;
        final long color3;
        final long trackColor3;
        final int strokeCap3;
        Modifier.Companion modifier4;
        final float gapSize3;
        final int strokeCap4;
        final long color4;
        final long trackColor4;
        Composer $composer3 = $composer.startRestartGroup(567589233);
        ComposerKt.sourceInformation($composer3, "C(LinearProgressIndicator)N(modifier,color:c#ui.graphics.Color,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap,gapSize:c#ui.unit.Dp)255@11480L28,257@11560L159,263@11771L159,269@11983L160,275@12196L160,285@12539L1839,280@12361L2017:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
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
                color2 = color;
                int i3 = $composer3.changed(color2) ? 32 : 16;
                $dirty |= i3;
            } else {
                color2 = color;
            }
            $dirty |= i3;
        } else {
            color2 = color;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                trackColor2 = trackColor;
                int i4 = $composer3.changed(trackColor2) ? 256 : 128;
                $dirty |= i4;
            } else {
                trackColor2 = trackColor;
            }
            $dirty |= i4;
        } else {
            trackColor2 = trackColor;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            strokeCap2 = strokeCap;
        } else if (($changed & 3072) == 0) {
            strokeCap2 = strokeCap;
            $dirty |= $composer3.changed(strokeCap2) ? 2048 : 1024;
        } else {
            strokeCap2 = strokeCap;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            gapSize2 = gapSize;
        } else if (($changed & 24576) == 0) {
            gapSize2 = gapSize;
            $dirty |= $composer3.changed(gapSize2) ? 16384 : 8192;
        } else {
            gapSize2 = gapSize;
        }
        if (!$composer3.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color3 = color2;
            trackColor3 = trackColor2;
            strokeCap3 = strokeCap2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "250@11223L11,251@11286L16");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier4 = modifier2;
                gapSize3 = gapSize2;
                strokeCap4 = strokeCap2;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 2) != 0) {
                    color2 = ProgressIndicatorDefaults.INSTANCE.getLinearColor($composer3, 6);
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    trackColor2 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor($composer3, 6);
                    $dirty &= -897;
                }
                if (i5 != 0) {
                    strokeCap2 = ProgressIndicatorDefaults.INSTANCE.m2725getLinearStrokeCapKaPHkGw();
                }
                if (i6 == 0) {
                    gapSize3 = gapSize2;
                    strokeCap4 = strokeCap2;
                } else {
                    gapSize3 = ProgressIndicatorDefaults.INSTANCE.m2724getLinearIndicatorTrackGapSizeD9Ej5fM();
                    strokeCap4 = strokeCap2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(567589233, $dirty, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:254)");
            }
            InfiniteTransition infiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition(null, $composer3, 0, 1);
            final State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.0f, 1.0f, getLinearIndeterminateFirstLineHeadAnimationSpec(), null, $composer3, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            final State<Float> stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.0f, 1.0f, getLinearIndeterminateFirstLineTailAnimationSpec(), null, $composer3, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            final State<Float> stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.0f, 1.0f, getLinearIndeterminateSecondLineHeadAnimationSpec(), null, $composer3, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            final State<Float> stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.0f, 1.0f, getLinearIndeterminateSecondLineTailAnimationSpec(), null, $composer3, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            $composer2 = $composer3;
            Modifier modifierM867sizeVpY3zN4 = SizeKt.m867sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(modifier4.then(AccessibilityUtilKt.getIncreaseVerticalSemanticsBounds())), LinearIndicatorWidth, LinearIndicatorHeight);
            ComposerKt.sourceInformationMarkerStart($composer2, -7544576, "CC(remember):ProgressIndicator.kt#9igjgp");
            Modifier modifier5 = modifier4;
            boolean invalid$iv = (($dirty & 7168) == 2048) | ((57344 & $dirty) == 16384) | $composer2.changed(stateAnimateFloat) | (((($dirty & 896) ^ 384) > 256 && $composer2.changed(trackColor2)) || ($dirty & 384) == 256) | $composer2.changed(stateAnimateFloat2) | (((($dirty & 112) ^ 48) > 32 && $composer2.changed(color2)) || ($dirty & 48) == 32) | $composer2.changed(stateAnimateFloat3) | $composer2.changed(stateAnimateFloat4);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                color4 = color2;
                trackColor4 = trackColor2;
                Object value$iv = new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.LinearProgressIndicator_rIrjwxo$lambda$13$lambda$12(strokeCap4, gapSize3, stateAnimateFloat, trackColor4, stateAnimateFloat2, color4, stateAnimateFloat3, stateAnimateFloat4, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            } else {
                color4 = color2;
                trackColor4 = trackColor2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierM867sizeVpY3zN4, (Function1) it$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            strokeCap3 = strokeCap4;
            gapSize2 = gapSize3;
            trackColor3 = trackColor4;
            color3 = color4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator_rIrjwxo$lambda$14(modifier3, color3, trackColor3, strokeCap3, gapSize2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0058  */
    static final Unit LinearProgressIndicator_rIrjwxo$lambda$13$lambda$12(int $strokeCap, float $gapSize, State $firstLineHead, long $trackColor, State $firstLineTail, long $color, State $secondLineHead, State $secondLineTail, DrawScope $this$Canvas) {
        float fM7905constructorimpl;
        long arg0$iv = $this$Canvas.mo5671getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv & 4294967295L);
        float strokeWidth = Float.intBitsToFloat(bits$iv$iv$iv);
        if (StrokeCap.m5467equalsimpl0($strokeCap, StrokeCap.INSTANCE.m5471getButtKaPHkGw())) {
            fM7905constructorimpl = $gapSize;
        } else {
            long arg0$iv2 = $this$Canvas.mo5671getSizeNHjbRc();
            int bits$iv$iv$iv2 = (int) (4294967295L & arg0$iv2);
            float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv2);
            long arg0$iv3 = $this$Canvas.mo5671getSizeNHjbRc();
            int bits$iv$iv$iv3 = (int) (arg0$iv3 >> 32);
            if (fIntBitsToFloat <= Float.intBitsToFloat(bits$iv$iv$iv3)) {
                fM7905constructorimpl = Dp.m7905constructorimpl($gapSize + $this$Canvas.mo402toDpu2uoSUM(strokeWidth));
            } else {
                fM7905constructorimpl = $gapSize;
            }
        }
        float adjustedGapSize = fM7905constructorimpl;
        long arg0$iv4 = $this$Canvas.mo5671getSizeNHjbRc();
        int bits$iv$iv$iv4 = (int) (arg0$iv4 >> 32);
        float other$iv = adjustedGapSize / $this$Canvas.mo402toDpu2uoSUM(Float.intBitsToFloat(bits$iv$iv$iv4));
        if (((Number) $firstLineHead.getValue()).floatValue() < 1.0f - other$iv) {
            float start = ((Number) $firstLineHead.getValue()).floatValue() > 0.0f ? ((Number) $firstLineHead.getValue()).floatValue() + other$iv : 0.0f;
            m2745drawLinearIndicatorqYKTg0g($this$Canvas, start, 1.0f, $trackColor, strokeWidth, $strokeCap);
        }
        if (((Number) $firstLineHead.getValue()).floatValue() - ((Number) $firstLineTail.getValue()).floatValue() > 0.0f) {
            m2745drawLinearIndicatorqYKTg0g($this$Canvas, ((Number) $firstLineHead.getValue()).floatValue(), ((Number) $firstLineTail.getValue()).floatValue(), $color, strokeWidth, $strokeCap);
        }
        if (((Number) $firstLineTail.getValue()).floatValue() > other$iv) {
            float start2 = ((Number) $secondLineHead.getValue()).floatValue() > 0.0f ? ((Number) $secondLineHead.getValue()).floatValue() + other$iv : 0.0f;
            float end = ((Number) $firstLineTail.getValue()).floatValue() < 1.0f ? ((Number) $firstLineTail.getValue()).floatValue() - other$iv : 1.0f;
            m2745drawLinearIndicatorqYKTg0g($this$Canvas, start2, end, $trackColor, strokeWidth, $strokeCap);
        }
        if (((Number) $secondLineHead.getValue()).floatValue() - ((Number) $secondLineTail.getValue()).floatValue() > 0.0f) {
            m2745drawLinearIndicatorqYKTg0g($this$Canvas, ((Number) $secondLineHead.getValue()).floatValue(), ((Number) $secondLineTail.getValue()).floatValue(), $color, strokeWidth, $strokeCap);
        }
        if (((Number) $secondLineTail.getValue()).floatValue() > other$iv) {
            float end2 = ((Number) $secondLineTail.getValue()).floatValue() < 1.0f ? ((Number) $secondLineTail.getValue()).floatValue() - other$iv : 1.0f;
            m2745drawLinearIndicatorqYKTg0g($this$Canvas, 0.0f, end2, $trackColor, strokeWidth, $strokeCap);
        }
        return Unit.INSTANCE;
    }

    @Deprecated(message = "Use the overload that takes `progress` as a lambda", replaceWith = @ReplaceWith(expression = "LinearProgressIndicator(\nprogress = { progress },\nmodifier = modifier,\ncolor = color,\ntrackColor = trackColor,\nstrokeCap = strokeCap,\n)", imports = {}))
    /* JADX INFO: renamed from: LinearProgressIndicator-_5eSR-E, reason: not valid java name */
    public static final void m2737LinearProgressIndicator_5eSRE(final float progress, Modifier modifier, long color, long trackColor, int strokeCap, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long color2;
        long trackColor2;
        int i2;
        Composer $composer2;
        final long color3;
        final Modifier modifier3;
        final long trackColor3;
        final int strokeCap2;
        int strokeCap3;
        long trackColor4;
        long trackColor5;
        Modifier modifier4;
        Composer $composer3 = $composer.startRestartGroup(905419617);
        ComposerKt.sourceInformation($composer3, "C(LinearProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap)360@15142L12,359@15098L179:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(progress) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                color2 = color;
                int i4 = $composer3.changed(color2) ? 256 : 128;
                $dirty |= i4;
            } else {
                color2 = color;
            }
            $dirty |= i4;
        } else {
            color2 = color;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                trackColor2 = trackColor;
                int i5 = $composer3.changed(trackColor2) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                trackColor2 = trackColor;
            }
            $dirty |= i5;
        } else {
            trackColor2 = trackColor;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            i2 = strokeCap;
        } else if (($changed & 24576) == 0) {
            i2 = strokeCap;
            $dirty |= $composer3.changed(i2) ? 16384 : 8192;
        } else {
            i2 = strokeCap;
        }
        if ($composer3.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "355@14939L11,356@15002L16");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                strokeCap3 = i2;
                trackColor4 = trackColor2;
                trackColor5 = color2;
                modifier4 = modifier2;
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    color2 = ProgressIndicatorDefaults.INSTANCE.getLinearColor($composer3, 6);
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    trackColor2 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor($composer3, 6);
                    $dirty &= -7169;
                }
                if (i6 == 0) {
                    strokeCap3 = i2;
                    trackColor4 = trackColor2;
                    trackColor5 = color2;
                    modifier4 = modifier2;
                } else {
                    strokeCap3 = ProgressIndicatorDefaults.INSTANCE.m2725getLinearStrokeCapKaPHkGw();
                    trackColor4 = trackColor2;
                    trackColor5 = color2;
                    modifier4 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(905419617, $dirty, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:359)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1770084819, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean invalid$iv = ($dirty & 14) == 4;
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(ProgressIndicatorKt.LinearProgressIndicator__5eSR_E$lambda$16$lambda$15(progress));
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            m2735LinearProgressIndicatorGJbTh5U((Function0) it$iv, modifier4, trackColor5, trackColor4, strokeCap3, 0.0f, null, $composer2, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty), 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            trackColor3 = trackColor4;
            strokeCap2 = strokeCap3;
            color3 = trackColor5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            color3 = color2;
            modifier3 = modifier2;
            trackColor3 = trackColor2;
            strokeCap2 = i2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator__5eSR_E$lambda$17(progress, modifier3, color3, trackColor3, strokeCap2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final float LinearProgressIndicator__5eSR_E$lambda$16$lambda$15(float $progress) {
        return $progress;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: LinearProgressIndicator-eaDK9VM, reason: not valid java name */
    public static final /* synthetic */ void m2739LinearProgressIndicatoreaDK9VM(final float progress, Modifier modifier, long color, long trackColor, Composer $composer, final int $changed, final int i) {
        float f;
        Modifier modifier2;
        long j;
        long j2;
        final Modifier modifier3;
        final long color2;
        final long trackColor2;
        long color3;
        long trackColor3;
        Modifier modifier4;
        long color4;
        Composer $composer2 = $composer.startRestartGroup(-372717133);
        ComposerKt.sourceInformation($composer2, "C(LinearProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,trackColor:c#ui.graphics.Color)376@15619L164:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            f = progress;
        } else if (($changed & 6) == 0) {
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
                j = color;
                int i3 = $composer2.changed(j) ? 256 : 128;
                $dirty |= i3;
            } else {
                j = color;
            }
            $dirty |= i3;
        } else {
            j = color;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j2 = trackColor;
                int i4 = $composer2.changed(j2) ? 2048 : 1024;
                $dirty |= i4;
            } else {
                j2 = trackColor;
            }
            $dirty |= i4;
        } else {
            j2 = trackColor;
        }
        if ($composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "373@15530L11,374@15593L16");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    color3 = ProgressIndicatorDefaults.INSTANCE.getLinearColor($composer2, 6);
                    $dirty &= -897;
                } else {
                    color3 = j;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    modifier4 = modifier5;
                    color4 = color3;
                    trackColor3 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor($composer2, 6);
                } else {
                    trackColor3 = j2;
                    modifier4 = modifier5;
                    color4 = color3;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                trackColor3 = j2;
                modifier4 = modifier2;
                color4 = j;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-372717133, $dirty, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:376)");
            }
            m2737LinearProgressIndicator_5eSRE(f, modifier4, color4, trackColor3, ProgressIndicatorDefaults.INSTANCE.m2725getLinearStrokeCapKaPHkGw(), $composer2, ($dirty & 14) | 24576 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            color2 = color4;
            trackColor2 = trackColor3;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color2 = j;
            trackColor2 = j2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator_eaDK9VM$lambda$18(progress, modifier3, color2, trackColor2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: LinearProgressIndicator-RIQooxk, reason: not valid java name */
    public static final /* synthetic */ void m2736LinearProgressIndicatorRIQooxk(Modifier modifier, long color, long trackColor, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        long trackColor2;
        final Modifier modifier3;
        final long color2;
        final long trackColor3;
        long color3;
        long trackColor4;
        Modifier modifier4;
        long color4;
        Composer $composer2 = $composer.startRestartGroup(585576195);
        ComposerKt.sourceInformation($composer2, "C(LinearProgressIndicator)N(modifier,color:c#ui.graphics.Color,trackColor:c#ui.graphics.Color)391@16079L146:ProgressIndicator.kt#uh7d8r");
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
                j = color;
                int i3 = $composer2.changed(j) ? 32 : 16;
                $dirty |= i3;
            } else {
                j = color;
            }
            $dirty |= i3;
        } else {
            j = color;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                trackColor2 = trackColor;
                int i4 = $composer2.changed(trackColor2) ? 256 : 128;
                $dirty |= i4;
            } else {
                trackColor2 = trackColor;
            }
            $dirty |= i4;
        } else {
            trackColor2 = trackColor;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "388@15990L11,389@16053L16");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    color3 = ProgressIndicatorDefaults.INSTANCE.getLinearColor($composer2, 6);
                    $dirty &= -113;
                } else {
                    color3 = j;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    trackColor4 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor($composer2, 6);
                    modifier4 = modifier5;
                    color4 = color3;
                } else {
                    trackColor4 = trackColor2;
                    modifier4 = modifier5;
                    color4 = color3;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                trackColor4 = trackColor2;
                modifier4 = modifier2;
                color4 = j;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(585576195, $dirty, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:391)");
            }
            m2740LinearProgressIndicatorrIrjwxo(modifier4, color4, trackColor4, ProgressIndicatorDefaults.INSTANCE.m2725getLinearStrokeCapKaPHkGw(), 0.0f, $composer2, ($dirty & 14) | 3072 | ($dirty & 112) | ($dirty & 896), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            color2 = color4;
            trackColor3 = trackColor4;
            modifier3 = modifier4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color2 = j;
            trackColor3 = trackColor2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator_RIQooxk$lambda$19(modifier3, color2, trackColor3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Failed to apply debug info
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v6 ??, new type: long
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
    /* JADX WARN: Failed to calculate best type for var: r11v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r11v2 ??, new type: long
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
    /* JADX WARN: Failed to calculate best type for var: r5v6 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v6 ??, new type: long
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:159)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:136)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:241)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
    Caused by: java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r5v6 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v6 ??, new type: long
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
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException
     */
    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v6 ??, new type: long
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:186)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:245)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
        Caused by: java.lang.NullPointerException
        */
    /* JADX INFO: renamed from: drawLinearIndicator-qYKTg0g, reason: not valid java name */
    private static final void m2745drawLinearIndicatorqYKTg0g(androidx.compose.ui.graphics.drawscope.DrawScope r44, float r45, float r46, long r47, float r49, int r50) {
        /*
            Method dump skipped, instruction units count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.m2745drawLinearIndicatorqYKTg0g(androidx.compose.ui.graphics.drawscope.DrawScope, float, float, long, float, int):void");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize`, see `LegacyCircularProgressIndicatorSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "CircularProgressIndicator(progress, modifier, color, strokeWidth, trackColor, strokeCap, gapSize)", imports = {}))
    /* JADX INFO: renamed from: CircularProgressIndicator-DUhRLBM, reason: not valid java name */
    public static final /* synthetic */ void m2729CircularProgressIndicatorDUhRLBM(final Function0 progress, Modifier modifier, long color, float strokeWidth, long trackColor, int strokeCap, Composer $composer, final int $changed, final int i) {
        Function0 function0;
        Modifier modifier2;
        long color2;
        float strokeWidth2;
        long trackColor2;
        int i2;
        Composer $composer2;
        final Modifier modifier3;
        final long color3;
        final long trackColor3;
        final float strokeWidth3;
        final int strokeCap2;
        Modifier.Companion modifier4;
        long trackColor4;
        int strokeCap3;
        Modifier modifier5;
        long color4;
        float strokeWidth4;
        int i3;
        Composer $composer3 = $composer.startRestartGroup(-761680467);
        ComposerKt.sourceInformation($composer3, "C(CircularProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,strokeWidth:c#ui.unit.Dp,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap)481@19831L218:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function0 = progress;
        } else if (($changed & 6) == 0) {
            function0 = progress;
            $dirty |= $composer3.changedInstance(function0) ? 4 : 2;
        } else {
            function0 = progress;
        }
        int i4 = i & 2;
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
            if ((i & 4) == 0) {
                color2 = color;
                int i5 = $composer3.changed(color2) ? 256 : 128;
                $dirty |= i5;
            } else {
                color2 = color;
            }
            $dirty |= i5;
        } else {
            color2 = color;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            strokeWidth2 = strokeWidth;
        } else if (($changed & 3072) == 0) {
            strokeWidth2 = strokeWidth;
            $dirty |= $composer3.changed(strokeWidth2) ? 2048 : 1024;
        } else {
            strokeWidth2 = strokeWidth;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                trackColor2 = trackColor;
                int i7 = $composer3.changed(trackColor2) ? 16384 : 8192;
                $dirty |= i7;
            } else {
                trackColor2 = trackColor;
            }
            $dirty |= i7;
        } else {
            trackColor2 = trackColor;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i2 = strokeCap;
        } else if ((196608 & $changed) == 0) {
            i2 = strokeCap;
            $dirty |= $composer3.changed(i2) ? 131072 : 65536;
        } else {
            i2 = strokeCap;
        }
        if (!$composer3.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color3 = color2;
            trackColor3 = trackColor2;
            strokeWidth3 = strokeWidth2;
            strokeCap2 = i2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "476@19575L13,478@19709L29");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                trackColor4 = trackColor2;
                strokeCap3 = i2;
                i3 = -761680467;
                modifier5 = modifier2;
                color4 = color2;
                strokeWidth4 = strokeWidth2;
            } else {
                if (i4 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 4) != 0) {
                    color2 = ProgressIndicatorDefaults.INSTANCE.getCircularColor($composer3, 6);
                    $dirty &= -897;
                }
                if (i6 != 0) {
                    strokeWidth2 = ProgressIndicatorDefaults.INSTANCE.m2723getCircularStrokeWidthD9Ej5fM();
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    trackColor2 = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor($composer3, 6);
                }
                if (i8 == 0) {
                    trackColor4 = trackColor2;
                    strokeCap3 = i2;
                    modifier5 = modifier4;
                    color4 = color2;
                    strokeWidth4 = strokeWidth2;
                    i3 = -761680467;
                } else {
                    strokeCap3 = ProgressIndicatorDefaults.INSTANCE.m2720getCircularDeterminateStrokeCapKaPHkGw();
                    strokeWidth4 = strokeWidth2;
                    trackColor4 = trackColor2;
                    modifier5 = modifier4;
                    color4 = color2;
                    i3 = -761680467;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:480)");
            }
            $composer2 = $composer3;
            m2730CircularProgressIndicatorIyT6zlY(function0, modifier5, color4, strokeWidth4, trackColor4, strokeCap3, ProgressIndicatorDefaults.INSTANCE.m2722getCircularIndicatorTrackGapSizeD9Ej5fM(), $composer2, ($dirty & 14) | 1572864 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            color3 = color4;
            strokeWidth3 = strokeWidth4;
            trackColor3 = trackColor4;
            strokeCap2 = strokeCap3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.CircularProgressIndicator_DUhRLBM$lambda$20(progress, modifier3, color3, strokeWidth3, trackColor3, strokeCap2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: CircularProgressIndicator-IyT6zlY, reason: not valid java name */
    public static final void m2730CircularProgressIndicatorIyT6zlY(final Function0<Float> function0, Modifier modifier, long color, float strokeWidth, long trackColor, int strokeCap, float gapSize, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float strokeWidth2;
        long trackColor2;
        int strokeCap2;
        float f;
        final Modifier modifier3;
        final float strokeWidth3;
        final long color2;
        final long trackColor3;
        final float gapSize2;
        final int strokeCap3;
        long color3;
        int $dirty;
        final float gapSize3;
        final int strokeCap4;
        long color4;
        final long color5;
        final long trackColor4;
        final float strokeWidth4;
        Composer $composer2 = $composer.startRestartGroup(-1798883595);
        ComposerKt.sourceInformation($composer2, "C(CircularProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,strokeWidth:c#ui.unit.Dp,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap,gapSize:c#ui.unit.Dp)527@22030L35,528@22101L7,531@22243L241,537@22536L693,529@22170L1059:ProgressIndicator.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(function0) ? 4 : 2;
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
            $dirty2 |= ((i & 4) == 0 && $composer2.changed(color)) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
            strokeWidth2 = strokeWidth;
        } else if (($changed & 3072) == 0) {
            strokeWidth2 = strokeWidth;
            $dirty2 |= $composer2.changed(strokeWidth2) ? 2048 : 1024;
        } else {
            strokeWidth2 = strokeWidth;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                trackColor2 = trackColor;
                int i4 = $composer2.changed(trackColor2) ? 16384 : 8192;
                $dirty2 |= i4;
            } else {
                trackColor2 = trackColor;
            }
            $dirty2 |= i4;
        } else {
            trackColor2 = trackColor;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            strokeCap2 = strokeCap;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            strokeCap2 = strokeCap;
            $dirty2 |= $composer2.changed(strokeCap2) ? 131072 : 65536;
        } else {
            strokeCap2 = strokeCap;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
            f = gapSize;
        } else if (($changed & 1572864) == 0) {
            f = gapSize;
            $dirty2 |= $composer2.changed(f) ? 1048576 : 524288;
        } else {
            f = gapSize;
        }
        if ($composer2.shouldExecute(($dirty2 & 599187) != 599186, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "521@21677L13,523@21811L29");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                $dirty = $dirty2;
                gapSize3 = f;
                strokeCap4 = strokeCap2;
                color4 = color;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) == 0) {
                    color3 = color;
                } else {
                    color3 = ProgressIndicatorDefaults.INSTANCE.getCircularColor($composer2, 6);
                    $dirty2 &= -897;
                }
                if (i3 != 0) {
                    strokeWidth2 = ProgressIndicatorDefaults.INSTANCE.m2723getCircularStrokeWidthD9Ej5fM();
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    trackColor2 = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor($composer2, 6);
                }
                if (i5 != 0) {
                    strokeCap2 = ProgressIndicatorDefaults.INSTANCE.m2720getCircularDeterminateStrokeCapKaPHkGw();
                }
                if (i6 == 0) {
                    $dirty = $dirty2;
                    gapSize3 = f;
                    strokeCap4 = strokeCap2;
                    color4 = color3;
                } else {
                    $dirty = $dirty2;
                    gapSize3 = ProgressIndicatorDefaults.INSTANCE.m2722getCircularIndicatorTrackGapSizeD9Ej5fM();
                    strokeCap4 = strokeCap2;
                    color4 = color3;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1798883595, $dirty, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:526)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 2144702904, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean invalid$iv = ($dirty & 14) == 4;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(ProgressIndicatorKt.CircularProgressIndicator_IyT6zlY$lambda$22$lambda$21(function0));
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            final Function0 coercedProgress = (Function0) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$CircularProgressIndicator_IyT6zlY_u24lambda_u2423 = (Density) objConsume;
            final Stroke stroke = new Stroke($this$CircularProgressIndicator_IyT6zlY_u24lambda_u2423.mo406toPx0680j_4(strokeWidth2), 0.0f, strokeCap4, 0, null, 26, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 2144709926, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changed(coercedProgress);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.CircularProgressIndicator_IyT6zlY$lambda$26$lambda$25(coercedProgress, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            boolean z = true;
            Modifier modifierM865size3ABfNKs = SizeKt.m865size3ABfNKs(SemanticsModifierKt.semantics(modifier2, true, (Function1) it$iv2), CircularIndicatorDiameter);
            ComposerKt.sourceInformationMarkerStart($composer2, 2144719754, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean zChanged = $composer2.changed(coercedProgress) | ((458752 & $dirty) == 131072) | ((3670016 & $dirty) == 1048576) | (($dirty & 7168) == 2048) | ((((57344 & $dirty) ^ 24576) > 16384 && $composer2.changed(trackColor2)) || ($dirty & 24576) == 16384) | $composer2.changedInstance(stroke);
            if (((($dirty & 896) ^ 384) <= 256 || !$composer2.changed(color4)) && ($dirty & 384) != 256) {
                z = false;
            }
            boolean invalid$iv3 = zChanged | z;
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                color5 = color4;
                trackColor4 = trackColor2;
                strokeWidth4 = strokeWidth2;
                Object value$iv3 = new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.CircularProgressIndicator_IyT6zlY$lambda$28$lambda$27(coercedProgress, strokeCap4, gapSize3, strokeWidth4, trackColor4, stroke, color5, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            } else {
                color5 = color4;
                trackColor4 = trackColor2;
                strokeWidth4 = strokeWidth2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierM865size3ABfNKs, (Function1) it$iv3, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            strokeCap3 = strokeCap4;
            gapSize2 = gapSize3;
            strokeWidth3 = strokeWidth4;
            trackColor3 = trackColor4;
            color2 = color5;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            strokeWidth3 = strokeWidth2;
            color2 = color;
            trackColor3 = trackColor2;
            gapSize2 = f;
            strokeCap3 = strokeCap2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.CircularProgressIndicator_IyT6zlY$lambda$29(function0, modifier3, color2, strokeWidth3, trackColor3, strokeCap3, gapSize2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final float CircularProgressIndicator_IyT6zlY$lambda$22$lambda$21(Function0 $progress) {
        float $this$fastCoerceIn$iv = ((Number) $progress.invoke()).floatValue();
        float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
            return 1.0f;
        }
        return $this$fastCoerceAtLeast$iv$iv;
    }

    static final Unit CircularProgressIndicator_IyT6zlY$lambda$26$lambda$25(Function0 $coercedProgress, SemanticsPropertyReceiver $this$semantics) {
        Object objInvoke = $coercedProgress.invoke();
        float it = ((Number) objInvoke).floatValue();
        if (Float.isNaN(it)) {
            objInvoke = null;
        }
        Float f = (Float) objInvoke;
        SemanticsPropertiesKt.setProgressBarRangeInfo($this$semantics, new ProgressBarRangeInfo(f != null ? f.floatValue() : 0.0f, RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0051  */
    static final Unit CircularProgressIndicator_IyT6zlY$lambda$28$lambda$27(Function0 $coercedProgress, int $strokeCap, float $gapSize, float $strokeWidth, long $trackColor, Stroke $stroke, long $color, DrawScope $this$Canvas) {
        float fM7905constructorimpl;
        float sweep = ((Number) $coercedProgress.invoke()).floatValue() * 360.0f;
        if (StrokeCap.m5467equalsimpl0($strokeCap, StrokeCap.INSTANCE.m5471getButtKaPHkGw())) {
            fM7905constructorimpl = $gapSize;
        } else {
            long arg0$iv = $this$Canvas.mo5671getSizeNHjbRc();
            int bits$iv$iv$iv = (int) (4294967295L & arg0$iv);
            float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv);
            long arg0$iv2 = $this$Canvas.mo5671getSizeNHjbRc();
            int bits$iv$iv$iv2 = (int) (arg0$iv2 >> 32);
            if (fIntBitsToFloat <= Float.intBitsToFloat(bits$iv$iv$iv2)) {
                fM7905constructorimpl = Dp.m7905constructorimpl($gapSize + $strokeWidth);
            } else {
                fM7905constructorimpl = $gapSize;
            }
        }
        float adjustedGapSize = fM7905constructorimpl;
        long arg0$iv3 = $this$Canvas.mo5671getSizeNHjbRc();
        int bits$iv$iv$iv3 = (int) (arg0$iv3 >> 32);
        float gapSizeSweep = (adjustedGapSize / ((float) (((double) $this$Canvas.mo402toDpu2uoSUM(Float.intBitsToFloat(bits$iv$iv$iv3))) * 3.141592653589793d))) * 360.0f;
        m2741drawCircularIndicator42QJj7c($this$Canvas, 270.0f + sweep + Math.min(sweep, gapSizeSweep), (360.0f - sweep) - (Math.min(sweep, gapSizeSweep) * 2.0f), $trackColor, $stroke);
        m2743drawDeterminateCircularIndicator42QJj7c($this$Canvas, 270.0f, sweep, $color, $stroke);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize`", replaceWith = @ReplaceWith(expression = "CircularProgressIndicator(modifier, color, strokeWidth, trackColor, strokeCap, gapSize)", imports = {}))
    /* JADX INFO: renamed from: CircularProgressIndicator-LxG7B9w, reason: not valid java name */
    public static final /* synthetic */ void m2731CircularProgressIndicatorLxG7B9w(Modifier modifier, long color, float strokeWidth, long trackColor, int strokeCap, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        float f;
        long j2;
        int i2;
        Composer $composer2;
        final long color2;
        final long trackColor2;
        final Modifier modifier3;
        final float strokeWidth2;
        final int strokeCap2;
        long color3;
        long trackColor3;
        float strokeWidth3;
        int strokeCap3;
        long trackColor4;
        Modifier modifier4;
        long color4;
        Composer $composer3 = $composer.startRestartGroup(-115871647);
        ComposerKt.sourceInformation($composer3, "C(CircularProgressIndicator)N(modifier,color:c#ui.graphics.Color,strokeWidth:c#ui.unit.Dp,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap)594@24879L258:ProgressIndicator.kt#uh7d8r");
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
                j = color;
                int i4 = $composer3.changed(j) ? 32 : 16;
                $dirty |= i4;
            } else {
                j = color;
            }
            $dirty |= i4;
        } else {
            j = color;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty |= 384;
            f = strokeWidth;
        } else if (($changed & 384) == 0) {
            f = strokeWidth;
            $dirty |= $composer3.changed(f) ? 256 : 128;
        } else {
            f = strokeWidth;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j2 = trackColor;
                int i6 = $composer3.changed(j2) ? 2048 : 1024;
                $dirty |= i6;
            } else {
                j2 = trackColor;
            }
            $dirty |= i6;
        } else {
            j2 = trackColor;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty |= 24576;
            i2 = strokeCap;
        } else if (($changed & 24576) == 0) {
            i2 = strokeCap;
            $dirty |= $composer3.changed(i2) ? 16384 : 8192;
        } else {
            i2 = strokeCap;
        }
        if ($composer3.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "589@24619L13,591@24753L31");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    color3 = ProgressIndicatorDefaults.INSTANCE.getCircularColor($composer3, 6);
                    $dirty &= -113;
                } else {
                    color3 = j;
                }
                float strokeWidth4 = i5 != 0 ? ProgressIndicatorDefaults.INSTANCE.m2723getCircularStrokeWidthD9Ej5fM() : f;
                if ((i & 8) != 0) {
                    trackColor3 = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor($composer3, 6);
                    $dirty &= -7169;
                } else {
                    trackColor3 = j2;
                }
                if (i7 != 0) {
                    strokeWidth3 = strokeWidth4;
                    strokeCap3 = ProgressIndicatorDefaults.INSTANCE.m2721getCircularIndeterminateStrokeCapKaPHkGw();
                    trackColor4 = trackColor3;
                    modifier4 = modifier5;
                    color4 = color3;
                } else {
                    strokeWidth3 = strokeWidth4;
                    strokeCap3 = i2;
                    trackColor4 = trackColor3;
                    modifier4 = modifier5;
                    color4 = color3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                strokeWidth3 = f;
                strokeCap3 = i2;
                modifier4 = modifier2;
                trackColor4 = j2;
                color4 = j;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-115871647, $dirty, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:594)");
            }
            $composer2 = $composer3;
            m2727CircularProgressIndicator4lLiAd8(modifier4, color4, strokeWidth3, trackColor4, strokeCap3, ProgressIndicatorDefaults.INSTANCE.m2722getCircularIndicatorTrackGapSizeD9Ej5fM(), $composer2, ($dirty & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            color2 = color4;
            modifier3 = modifier4;
            strokeWidth2 = strokeWidth3;
            trackColor2 = trackColor4;
            strokeCap2 = strokeCap3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            color2 = j;
            trackColor2 = j2;
            modifier3 = modifier2;
            strokeWidth2 = f;
            strokeCap2 = i2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.CircularProgressIndicator_LxG7B9w$lambda$30(modifier3, color2, strokeWidth2, trackColor2, strokeCap2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: CircularProgressIndicator-4lLiAd8, reason: not valid java name */
    public static final void m2727CircularProgressIndicator4lLiAd8(Modifier modifier, long color, float strokeWidth, long trackColor, int strokeCap, float gapSize, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        float f;
        long trackColor2;
        int strokeCap2;
        float f2;
        Composer $composer2;
        final Modifier modifier3;
        final long color2;
        final float gapSize2;
        final float strokeWidth2;
        final int strokeCap3;
        final long trackColor3;
        Modifier.Companion modifier4;
        long color3;
        int $dirty;
        float strokeWidth3;
        final float gapSize3;
        final int strokeCap4;
        final float strokeWidth4;
        final long color4;
        final long trackColor4;
        Composer $composer3 = $composer.startRestartGroup(333154241);
        ComposerKt.sourceInformation($composer3, "C(CircularProgressIndicator)N(modifier,color:c#ui.graphics.Color,strokeWidth:c#ui.unit.Dp,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap,gapSize:c#ui.unit.Dp)631@26690L7,633@26785L28,636@26938L195,644@27283L193,653@27649L216,659@27940L734,659@27871L803:ProgressIndicator.kt#uh7d8r");
        int $dirty2 = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty2 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                j = color;
                int i3 = $composer3.changed(j) ? 32 : 16;
                $dirty2 |= i3;
            } else {
                j = color;
            }
            $dirty2 |= i3;
        } else {
            j = color;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 384;
            f = strokeWidth;
        } else if (($changed & 384) == 0) {
            f = strokeWidth;
            $dirty2 |= $composer3.changed(f) ? 256 : 128;
        } else {
            f = strokeWidth;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                trackColor2 = trackColor;
                int i5 = $composer3.changed(trackColor2) ? 2048 : 1024;
                $dirty2 |= i5;
            } else {
                trackColor2 = trackColor;
            }
            $dirty2 |= i5;
        } else {
            trackColor2 = trackColor;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty2 |= 24576;
            strokeCap2 = strokeCap;
        } else if (($changed & 24576) == 0) {
            strokeCap2 = strokeCap;
            $dirty2 |= $composer3.changed(strokeCap2) ? 16384 : 8192;
        } else {
            strokeCap2 = strokeCap;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f2 = gapSize;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            f2 = gapSize;
            $dirty2 |= $composer3.changed(f2) ? 131072 : 65536;
        } else {
            f2 = gapSize;
        }
        int $dirty3 = $dirty2;
        if (!$composer3.shouldExecute(($dirty2 & 74899) != 74898, $dirty3 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color2 = j;
            gapSize2 = f2;
            strokeWidth2 = f;
            strokeCap3 = strokeCap2;
            trackColor3 = trackColor2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "625@26324L13,627@26458L31");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty4 = (i & 2) != 0 ? $dirty3 & (-113) : $dirty3;
                if ((i & 8) != 0) {
                    $dirty4 &= -7169;
                }
                $dirty = $dirty4;
                modifier4 = modifier2;
                color3 = j;
                gapSize3 = f2;
                strokeWidth3 = f;
                strokeCap4 = strokeCap2;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 2) == 0) {
                    color3 = j;
                    $dirty = $dirty3;
                } else {
                    color3 = ProgressIndicatorDefaults.INSTANCE.getCircularColor($composer3, 6);
                    $dirty = $dirty3 & (-113);
                }
                if (i4 == 0) {
                    strokeWidth3 = f;
                } else {
                    strokeWidth3 = ProgressIndicatorDefaults.INSTANCE.m2723getCircularStrokeWidthD9Ej5fM();
                }
                if ((i & 8) != 0) {
                    trackColor2 = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor($composer3, 6);
                    $dirty &= -7169;
                }
                if (i6 != 0) {
                    strokeCap2 = ProgressIndicatorDefaults.INSTANCE.m2721getCircularIndeterminateStrokeCapKaPHkGw();
                }
                if (i7 == 0) {
                    gapSize3 = f2;
                    strokeCap4 = strokeCap2;
                } else {
                    gapSize3 = ProgressIndicatorDefaults.INSTANCE.m2722getCircularIndicatorTrackGapSizeD9Ej5fM();
                    strokeCap4 = strokeCap2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(333154241, $dirty, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:630)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density $this$CircularProgressIndicator_4lLiAd8_u24lambda_u2431 = (Density) objConsume;
            final Stroke stroke = new Stroke($this$CircularProgressIndicator_4lLiAd8_u24lambda_u2431.mo406toPx0680j_4(strokeWidth3), 0.0f, strokeCap4, 0, null, 26, null);
            InfiniteTransition infiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition(null, $composer3, 0, 1);
            final State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.0f, 1080.0f, getCircularIndeterminateGlobalRotationAnimationSpec(), null, $composer3, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            final State<Float> stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.0f, 360.0f, getCircularIndeterminateRotationAnimationSpec(), null, $composer3, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            final State<Float> stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.1f, 0.87f, getCircularIndeterminateProgressAnimationSpec(), null, $composer3, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            $composer2 = $composer3;
            Modifier modifierM865size3ABfNKs = SizeKt.m865size3ABfNKs(ProgressSemanticsKt.progressSemantics(modifier4), CircularIndicatorDiameter);
            Modifier modifier5 = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer2, -1961263937, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(stateAnimateFloat3) | ((57344 & $dirty) == 16384) | ((458752 & $dirty) == 131072) | (($dirty & 896) == 256) | $composer2.changed(stateAnimateFloat) | $composer2.changed(stateAnimateFloat2) | (((($dirty & 7168) ^ 3072) > 2048 && $composer2.changed(trackColor2)) || ($dirty & 3072) == 2048) | $composer2.changedInstance(stroke) | (((($dirty & 112) ^ 48) > 32 && $composer2.changed(color3)) || ($dirty & 48) == 32);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                strokeWidth4 = strokeWidth3;
                color4 = color3;
                trackColor4 = trackColor2;
                Object value$iv = new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.CircularProgressIndicator_4lLiAd8$lambda$34$lambda$33(stateAnimateFloat3, strokeCap4, gapSize3, strokeWidth4, stateAnimateFloat, stateAnimateFloat2, trackColor4, stroke, color4, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            } else {
                strokeWidth4 = strokeWidth3;
                color4 = color3;
                trackColor4 = trackColor2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierM865size3ABfNKs, (Function1) it$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            strokeCap3 = strokeCap4;
            gapSize2 = gapSize3;
            strokeWidth2 = strokeWidth4;
            trackColor3 = trackColor4;
            color2 = color4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.CircularProgressIndicator_4lLiAd8$lambda$35(modifier3, color2, strokeWidth2, trackColor3, strokeCap3, gapSize2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0050  */
    static final Unit CircularProgressIndicator_4lLiAd8$lambda$34$lambda$33(State $progressAnimation, int $strokeCap, float $gapSize, float $strokeWidth, State $globalRotation, State $additionalRotation, long $trackColor, Stroke $stroke, long $color, DrawScope $this$Canvas) throws Throwable {
        float fM7905constructorimpl;
        DrawContext $this$withTransform_u24lambda_u246$iv$iv;
        long previousSize$iv$iv;
        float sweep = ((Number) $progressAnimation.getValue()).floatValue() * 360.0f;
        if (StrokeCap.m5467equalsimpl0($strokeCap, StrokeCap.INSTANCE.m5471getButtKaPHkGw())) {
            fM7905constructorimpl = $gapSize;
        } else {
            long arg0$iv = $this$Canvas.mo5671getSizeNHjbRc();
            int bits$iv$iv$iv = (int) (4294967295L & arg0$iv);
            float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv);
            long arg0$iv2 = $this$Canvas.mo5671getSizeNHjbRc();
            int bits$iv$iv$iv2 = (int) (arg0$iv2 >> 32);
            if (fIntBitsToFloat <= Float.intBitsToFloat(bits$iv$iv$iv2)) {
                fM7905constructorimpl = Dp.m7905constructorimpl($gapSize + $strokeWidth);
            } else {
                fM7905constructorimpl = $gapSize;
            }
        }
        float adjustedGapSize = fM7905constructorimpl;
        long arg0$iv3 = $this$Canvas.mo5671getSizeNHjbRc();
        int bits$iv$iv$iv3 = (int) (arg0$iv3 >> 32);
        float gapSizeSweep = (adjustedGapSize / ((float) (((double) $this$Canvas.mo402toDpu2uoSUM(Float.intBitsToFloat(bits$iv$iv$iv3))) * 3.141592653589793d))) * 360.0f;
        float degrees$iv = ((Number) $globalRotation.getValue()).floatValue() + ((Number) $additionalRotation.getValue()).floatValue();
        long pivot$iv = $this$Canvas.mo5670getCenterF1C5BW0();
        DrawContext $this$withTransform_u24lambda_u246$iv$iv2 = $this$Canvas.getDrawContext();
        long previousSize$iv$iv2 = $this$withTransform_u24lambda_u246$iv$iv2.mo5592getSizeNHjbRc();
        $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().save();
        try {
            DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv = $this$withTransform_u24lambda_u246$iv$iv2.getTransform();
            $this$rotate_Rg1IO4c_u24lambda_u240$iv.mo5598rotateUv8p0NA(degrees$iv, pivot$iv);
            m2741drawCircularIndicator42QJj7c($this$Canvas, sweep + Math.min(sweep, gapSizeSweep), (360.0f - sweep) - (Math.min(sweep, gapSizeSweep) * 2.0f), $trackColor, $stroke);
            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
            previousSize$iv$iv = previousSize$iv$iv2;
            try {
                m2743drawDeterminateCircularIndicator42QJj7c($this$Canvas, 0.0f, sweep, $color, $stroke);
                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u246$iv$iv.mo5593setSizeuvyYCjk(previousSize$iv$iv);
                return Unit.INSTANCE;
            } catch (Throwable th) {
                th = th;
                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u246$iv$iv.mo5593setSizeuvyYCjk(previousSize$iv$iv);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
            previousSize$iv$iv = previousSize$iv$iv2;
        }
    }

    @Deprecated(message = "Use the overload that takes `progress` as a lambda", replaceWith = @ReplaceWith(expression = "CircularProgressIndicator(\nprogress = { progress },\nmodifier = modifier,\ncolor = color,\nstrokeWidth = strokeWidth,\ntrackColor = trackColor,\nstrokeCap = strokeCap,\n)", imports = {}))
    /* JADX INFO: renamed from: CircularProgressIndicator-DUhRLBM, reason: not valid java name */
    public static final void m2728CircularProgressIndicatorDUhRLBM(final float progress, Modifier modifier, long color, float strokeWidth, long trackColor, int strokeCap, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long color2;
        float strokeWidth2;
        long trackColor2;
        int i2;
        Composer $composer2;
        final Modifier modifier3;
        final float strokeWidth3;
        final long color3;
        final long trackColor3;
        final int strokeCap2;
        float strokeWidth4;
        boolean invalid$iv;
        int i3;
        Modifier modifier4;
        int strokeCap3;
        long trackColor4;
        long trackColor5;
        Composer $composer3 = $composer.startRestartGroup(-1472321743);
        ComposerKt.sourceInformation($composer3, "C(CircularProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,strokeWidth:c#ui.unit.Dp,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap)706@29604L12,705@29558L216:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(progress) ? 4 : 2;
        }
        int i4 = i & 2;
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
            if ((i & 4) == 0) {
                color2 = color;
                int i5 = $composer3.changed(color2) ? 256 : 128;
                $dirty |= i5;
            } else {
                color2 = color;
            }
            $dirty |= i5;
        } else {
            color2 = color;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            strokeWidth2 = strokeWidth;
        } else if (($changed & 3072) == 0) {
            strokeWidth2 = strokeWidth;
            $dirty |= $composer3.changed(strokeWidth2) ? 2048 : 1024;
        } else {
            strokeWidth2 = strokeWidth;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                trackColor2 = trackColor;
                int i7 = $composer3.changed(trackColor2) ? 16384 : 8192;
                $dirty |= i7;
            } else {
                trackColor2 = trackColor;
            }
            $dirty |= i7;
        } else {
            trackColor2 = trackColor;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i2 = strokeCap;
        } else if ((196608 & $changed) == 0) {
            i2 = strokeCap;
            $dirty |= $composer3.changed(i2) ? 131072 : 65536;
        } else {
            i2 = strokeCap;
        }
        if ($composer3.shouldExecute(($dirty & 74899) != 74898, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "700@29313L13,702@29447L18");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                strokeWidth4 = strokeWidth2;
                invalid$iv = false;
                i3 = -1472321743;
                modifier4 = modifier2;
                strokeCap3 = i2;
                trackColor4 = trackColor2;
                trackColor5 = color2;
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    color2 = ProgressIndicatorDefaults.INSTANCE.getCircularColor($composer3, 6);
                    $dirty &= -897;
                }
                if (i6 != 0) {
                    strokeWidth2 = ProgressIndicatorDefaults.INSTANCE.m2723getCircularStrokeWidthD9Ej5fM();
                }
                if ((i & 16) != 0) {
                    trackColor2 = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor($composer3, 6);
                    $dirty &= -57345;
                }
                if (i8 == 0) {
                    strokeWidth4 = strokeWidth2;
                    invalid$iv = false;
                    i3 = -1472321743;
                    modifier4 = modifier2;
                    strokeCap3 = i2;
                    trackColor4 = trackColor2;
                    trackColor5 = color2;
                } else {
                    strokeCap3 = ProgressIndicatorDefaults.INSTANCE.m2720getCircularDeterminateStrokeCapKaPHkGw();
                    invalid$iv = false;
                    strokeWidth4 = strokeWidth2;
                    trackColor4 = trackColor2;
                    i3 = -1472321743;
                    modifier4 = modifier2;
                    trackColor5 = color2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:705)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1856924995, "CC(remember):ProgressIndicator.kt#9igjgp");
            if (($dirty & 14) == 4) {
                invalid$iv = true;
            }
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda29
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(ProgressIndicatorKt.CircularProgressIndicator_DUhRLBM$lambda$37$lambda$36(progress));
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            m2730CircularProgressIndicatorIyT6zlY((Function0) it$iv, modifier4, trackColor5, strokeWidth4, trackColor4, strokeCap3, 0.0f, $composer2, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            strokeWidth3 = strokeWidth4;
            trackColor3 = trackColor4;
            strokeCap2 = strokeCap3;
            color3 = trackColor5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            strokeWidth3 = strokeWidth2;
            color3 = color2;
            trackColor3 = trackColor2;
            strokeCap2 = i2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda30
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.CircularProgressIndicator_DUhRLBM$lambda$38(progress, modifier3, color3, strokeWidth3, trackColor3, strokeCap2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final float CircularProgressIndicator_DUhRLBM$lambda$37$lambda$36(float $progress) {
        return $progress;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: CircularProgressIndicator-MBs18nI, reason: not valid java name */
    public static final /* synthetic */ void m2732CircularProgressIndicatorMBs18nI(final float progress, Modifier modifier, long color, float strokeWidth, Composer $composer, final int $changed, final int i) {
        float f;
        Modifier modifier2;
        long j;
        float f2;
        final Modifier modifier3;
        final long color2;
        final float strokeWidth2;
        long color3;
        Modifier modifier4;
        float strokeWidth3;
        long color4;
        Composer $composer2 = $composer.startRestartGroup(402841196);
        ComposerKt.sourceInformation($composer2, "C(CircularProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,strokeWidth:c#ui.unit.Dp)728@30267L18,723@30121L247:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            f = progress;
        } else if (($changed & 6) == 0) {
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
                j = color;
                int i3 = $composer2.changed(j) ? 256 : 128;
                $dirty |= i3;
            } else {
                j = color;
            }
            $dirty |= i3;
        } else {
            j = color;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            f2 = strokeWidth;
        } else if (($changed & 3072) == 0) {
            f2 = strokeWidth;
            $dirty |= $composer2.changed(f2) ? 2048 : 1024;
        } else {
            f2 = strokeWidth;
        }
        if ($composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "720@30029L13");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    color3 = ProgressIndicatorDefaults.INSTANCE.getCircularColor($composer2, 6);
                    $dirty &= -897;
                } else {
                    color3 = j;
                }
                if (i4 != 0) {
                    strokeWidth3 = ProgressIndicatorDefaults.INSTANCE.m2723getCircularStrokeWidthD9Ej5fM();
                    modifier4 = modifier5;
                    color4 = color3;
                } else {
                    modifier4 = modifier5;
                    strokeWidth3 = f2;
                    color4 = color3;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                strokeWidth3 = f2;
                color4 = j;
                modifier4 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(402841196, $dirty, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:723)");
            }
            float strokeWidth4 = strokeWidth3;
            m2728CircularProgressIndicatorDUhRLBM(f, modifier4, color4, strokeWidth4, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor($composer2, 6), ProgressIndicatorDefaults.INSTANCE.m2720getCircularDeterminateStrokeCapKaPHkGw(), $composer2, ($dirty & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            color2 = color4;
            strokeWidth2 = strokeWidth4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color2 = j;
            strokeWidth2 = f2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.CircularProgressIndicator_MBs18nI$lambda$39(progress, modifier3, color2, strokeWidth2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: CircularProgressIndicator-aM-cp0Q, reason: not valid java name */
    public static final /* synthetic */ void m2733CircularProgressIndicatoraMcp0Q(Modifier modifier, long color, float strokeWidth, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long color2;
        float f;
        final Modifier modifier3;
        final long color3;
        final float strokeWidth2;
        float strokeWidth3;
        long color4;
        Modifier modifier4;
        Composer $composer2 = $composer.startRestartGroup(947193756);
        ComposerKt.sourceInformation($composer2, "C(CircularProgressIndicator)N(modifier,color:c#ui.graphics.Color,strokeWidth:c#ui.unit.Dp)744@30822L18,740@30694L231:ProgressIndicator.kt#uh7d8r");
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
                color2 = color;
                int i3 = $composer2.changed(color2) ? 32 : 16;
                $dirty |= i3;
            } else {
                color2 = color;
            }
            $dirty |= i3;
        } else {
            color2 = color;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            f = strokeWidth;
        } else if (($changed & 384) == 0) {
            f = strokeWidth;
            $dirty |= $composer2.changed(f) ? 256 : 128;
        } else {
            f = strokeWidth;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "737@30602L13");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    color2 = ProgressIndicatorDefaults.INSTANCE.getCircularColor($composer2, 6);
                    $dirty &= -113;
                }
                if (i4 != 0) {
                    strokeWidth3 = ProgressIndicatorDefaults.INSTANCE.m2723getCircularStrokeWidthD9Ej5fM();
                    color4 = color2;
                    modifier4 = modifier5;
                } else {
                    strokeWidth3 = f;
                    color4 = color2;
                    modifier4 = modifier5;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                strokeWidth3 = f;
                color4 = color2;
                modifier4 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(947193756, $dirty, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:740)");
            }
            m2727CircularProgressIndicator4lLiAd8(modifier4, color4, strokeWidth3, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor($composer2, 6), ProgressIndicatorDefaults.INSTANCE.m2721getCircularIndeterminateStrokeCapKaPHkGw(), 0.0f, $composer2, ($dirty & 14) | 24576 | ($dirty & 112) | ($dirty & 896), 32);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            color3 = color4;
            strokeWidth2 = strokeWidth3;
            modifier3 = modifier4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color3 = color2;
            strokeWidth2 = f;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.CircularProgressIndicator_aM_cp0Q$lambda$40(modifier3, color3, strokeWidth2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: drawCircularIndicator-42QJj7c, reason: not valid java name */
    private static final void m2741drawCircularIndicator42QJj7c(DrawScope $this$drawCircularIndicator_u2d42QJj7c, float startAngle, float sweep, long color, Stroke stroke) {
        float diameterOffset = stroke.getWidth() / 2.0f;
        long arg0$iv = $this$drawCircularIndicator_u2d42QJj7c.mo5671getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float arcDimen = Float.intBitsToFloat(bits$iv$iv$iv) - (2.0f * diameterOffset);
        long v1$iv$iv = Float.floatToRawIntBits(diameterOffset);
        long v2$iv$iv = Float.floatToRawIntBits(diameterOffset);
        long jM4844constructorimpl = Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        long v1$iv$iv2 = Float.floatToRawIntBits(arcDimen);
        long v2$iv$iv2 = Float.floatToRawIntBits(arcDimen);
        DrawScope.m5650drawArcyD3GUKo$default($this$drawCircularIndicator_u2d42QJj7c, color, startAngle, sweep, false, jM4844constructorimpl, Size.m4912constructorimpl((v1$iv$iv2 << 32) | (4294967295L & v2$iv$iv2)), 0.0f, stroke, null, 0, 832, null);
    }

    /* JADX INFO: renamed from: drawCircularIndicatorTrack-bw27NRU, reason: not valid java name */
    private static final void m2742drawCircularIndicatorTrackbw27NRU(DrawScope $this$drawCircularIndicatorTrack_u2dbw27NRU, long color, Stroke stroke) {
        m2741drawCircularIndicator42QJj7c($this$drawCircularIndicatorTrack_u2dbw27NRU, 0.0f, 360.0f, color, stroke);
    }

    /* JADX INFO: renamed from: drawDeterminateCircularIndicator-42QJj7c, reason: not valid java name */
    private static final void m2743drawDeterminateCircularIndicator42QJj7c(DrawScope $this$drawDeterminateCircularIndicator_u2d42QJj7c, float startAngle, float sweep, long color, Stroke stroke) {
        m2741drawCircularIndicator42QJj7c($this$drawDeterminateCircularIndicator_u2d42QJj7c, startAngle, sweep, color, stroke);
    }

    /* JADX INFO: renamed from: drawIndeterminateCircularIndicator-hrjfTZI, reason: not valid java name */
    private static final void m2744drawIndeterminateCircularIndicatorhrjfTZI(DrawScope $this$drawIndeterminateCircularIndicator_u2dhrjfTZI, float startAngle, float strokeWidth, float sweep, long color, Stroke stroke) {
        float strokeCapOffset;
        if (StrokeCap.m5467equalsimpl0(stroke.getCap(), StrokeCap.INSTANCE.m5471getButtKaPHkGw())) {
            strokeCapOffset = 0.0f;
        } else {
            float arg0$iv = CircularIndicatorDiameter;
            float other$iv = strokeWidth / Dp.m7905constructorimpl(arg0$iv / 2);
            strokeCapOffset = (other$iv * 57.29578f) / 2.0f;
        }
        float adjustedStartAngle = startAngle + strokeCapOffset;
        float adjustedSweep = Math.max(sweep, 0.1f);
        m2741drawCircularIndicator42QJj7c($this$drawIndeterminateCircularIndicator_u2dhrjfTZI, adjustedStartAngle, adjustedSweep, color, stroke);
    }

    public static final InfiniteRepeatableSpec<Float> getCircularIndeterminateGlobalRotationAnimationSpec() {
        return AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(6000, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null);
    }

    public static final InfiniteRepeatableSpec<Float> getCircularIndeterminateRotationAnimationSpec() {
        return AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt._get_circularIndeterminateRotationAnimationSpec_$lambda$41((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), null, 0L, 6, null);
    }

    static final Unit _get_circularIndeterminateRotationAnimationSpec_$lambda$41(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(6000);
        Float fValueOf = Float.valueOf(90.0f);
        $this$keyframes.using($this$keyframes.at(fValueOf, 300), MotionTokens.INSTANCE.getEasingEmphasizedDecelerateCubicBezier());
        $this$keyframes.at(fValueOf, 1500);
        Float fValueOf2 = Float.valueOf(180.0f);
        $this$keyframes.at(fValueOf2, 1800);
        $this$keyframes.at(fValueOf2, 3000);
        Float fValueOf3 = Float.valueOf(270.0f);
        $this$keyframes.at(fValueOf3, 3300);
        $this$keyframes.at(fValueOf3, 4500);
        Float fValueOf4 = Float.valueOf(360.0f);
        $this$keyframes.at(fValueOf4, 4800);
        $this$keyframes.at(fValueOf4, 6000);
        return Unit.INSTANCE;
    }

    public static final InfiniteRepeatableSpec<Float> getCircularIndeterminateProgressAnimationSpec() {
        return AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt._get_circularIndeterminateProgressAnimationSpec_$lambda$42((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), null, 0L, 6, null);
    }

    static final Unit _get_circularIndeterminateProgressAnimationSpec_$lambda$42(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(6000);
        $this$keyframes.using($this$keyframes.at(Float.valueOf(0.87f), 3000), CircularProgressEasing);
        $this$keyframes.at(Float.valueOf(0.1f), 6000);
        return Unit.INSTANCE;
    }

    public static final InfiniteRepeatableSpec<Float> getLinearIndeterminateFirstLineHeadAnimationSpec() {
        return AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt._get_linearIndeterminateFirstLineHeadAnimationSpec_$lambda$43((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), null, 0L, 6, null);
    }

    static final Unit _get_linearIndeterminateFirstLineHeadAnimationSpec_$lambda$43(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(LinearAnimationDuration);
        $this$keyframes.using($this$keyframes.at(Float.valueOf(0.0f), 0), LinearIndeterminateProgressEasing);
        $this$keyframes.at(Float.valueOf(1.0f), 1000);
        return Unit.INSTANCE;
    }

    public static final InfiniteRepeatableSpec<Float> getLinearIndeterminateFirstLineTailAnimationSpec() {
        return AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt._get_linearIndeterminateFirstLineTailAnimationSpec_$lambda$44((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), null, 0L, 6, null);
    }

    static final Unit _get_linearIndeterminateFirstLineTailAnimationSpec_$lambda$44(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(LinearAnimationDuration);
        $this$keyframes.using($this$keyframes.at(Float.valueOf(0.0f), 250), LinearIndeterminateProgressEasing);
        $this$keyframes.at(Float.valueOf(1.0f), 1250);
        return Unit.INSTANCE;
    }

    public static final InfiniteRepeatableSpec<Float> getLinearIndeterminateSecondLineHeadAnimationSpec() {
        return AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt._get_linearIndeterminateSecondLineHeadAnimationSpec_$lambda$45((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), null, 0L, 6, null);
    }

    static final Unit _get_linearIndeterminateSecondLineHeadAnimationSpec_$lambda$45(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(LinearAnimationDuration);
        $this$keyframes.using($this$keyframes.at(Float.valueOf(0.0f), SecondLineHeadDelay), LinearIndeterminateProgressEasing);
        $this$keyframes.at(Float.valueOf(1.0f), 1500);
        return Unit.INSTANCE;
    }

    public static final InfiniteRepeatableSpec<Float> getLinearIndeterminateSecondLineTailAnimationSpec() {
        return AnimationSpecKt.m173infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda27
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt._get_linearIndeterminateSecondLineTailAnimationSpec_$lambda$46((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), null, 0L, 6, null);
    }

    static final Unit _get_linearIndeterminateSecondLineTailAnimationSpec_$lambda$46(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(LinearAnimationDuration);
        $this$keyframes.using($this$keyframes.at(Float.valueOf(0.0f), 900), LinearIndeterminateProgressEasing);
        $this$keyframes.at(Float.valueOf(1.0f), LinearAnimationDuration);
        return Unit.INSTANCE;
    }

    public static final float getLinearIndicatorWidth() {
        return LinearIndicatorWidth;
    }

    public static final float getLinearIndicatorHeight() {
        return LinearIndicatorHeight;
    }

    public static final float getStopIndicatorTrailingSpace() {
        return StopIndicatorTrailingSpace;
    }

    public static final float getCircularIndicatorDiameter() {
        return CircularIndicatorDiameter;
    }

    public static final CubicBezierEasing getLinearIndeterminateProgressEasing() {
        return LinearIndeterminateProgressEasing;
    }

    public static final CubicBezierEasing getCircularProgressEasing() {
        return CircularProgressEasing;
    }
}
