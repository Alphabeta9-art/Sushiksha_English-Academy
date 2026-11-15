package androidx.compose.material3.pulltorefresh;

import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.MotionSchemeKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PullToRefresh.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Jo\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020\u00152\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010(\u001a\u00020\u00152\u001c\u0010)\u001a\u0018\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020 0*¢\u0006\u0002\b,¢\u0006\u0002\b-H\u0007¢\u0006\u0004\b.\u0010/JG\u00100\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u00101\u001a\u00020\f2\b\b\u0002\u0010'\u001a\u00020\u0015H\u0007¢\u0006\u0004\b2\u00103R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u001a\u0010\u000b\u001a\u00020\f8GX\u0087\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\f8G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0012\u001a\u00020\f8G¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u0013\u0010\u0014\u001a\u00020\u0015¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0019\u001a\u00020\u0015¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u001a\u0010\u0017R\u0013\u0010\u001b\u001a\u00020\u0015¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u001c\u0010\u0017R\u0013\u0010\u001d\u001a\u00020\u0015¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u001e\u0010\u0017¨\u00064"}, d2 = {"Landroidx/compose/material3/pulltorefresh/PullToRefreshDefaults;", "", "<init>", "()V", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape$annotations", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "indicatorShape", "getIndicatorShape", "containerColor", "Landroidx/compose/ui/graphics/Color;", "getContainerColor$annotations", "getContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "indicatorContainerColor", "getIndicatorContainerColor", "indicatorColor", "getIndicatorColor", "PositionalThreshold", "Landroidx/compose/ui/unit/Dp;", "getPositionalThreshold-D9Ej5fM", "()F", "F", "IndicatorMaxDistance", "getIndicatorMaxDistance-D9Ej5fM", "Elevation", "getElevation-D9Ej5fM", "LoadingIndicatorElevation", "getLoadingIndicatorElevation-D9Ej5fM", "IndicatorBox", "", "state", "Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "isRefreshing", "", "modifier", "Landroidx/compose/ui/Modifier;", "maxDistance", "elevation", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "IndicatorBox-1CPYgEU", "(Landroidx/compose/material3/pulltorefresh/PullToRefreshState;ZLandroidx/compose/ui/Modifier;FLandroidx/compose/ui/graphics/Shape;JFLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "Indicator", "color", "Indicator-2poqoh4", "(Landroidx/compose/material3/pulltorefresh/PullToRefreshState;ZLandroidx/compose/ui/Modifier;JJFLandroidx/compose/runtime/Composer;II)V", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PullToRefreshDefaults {
    public static final int $stable = 0;
    private static final float Elevation;
    private static final float IndicatorMaxDistance;
    private static final float LoadingIndicatorElevation;
    public static final PullToRefreshDefaults INSTANCE = new PullToRefreshDefaults();
    private static final Shape shape = RoundedCornerShapeKt.getCircleShape();
    private static final Shape indicatorShape = RoundedCornerShapeKt.getCircleShape();
    private static final float PositionalThreshold = Dp.m7905constructorimpl(80);

    static final Unit IndicatorBox_1CPYgEU$lambda$7(PullToRefreshDefaults pullToRefreshDefaults, PullToRefreshState pullToRefreshState, boolean z, Modifier modifier, float f, Shape shape2, long j, float f2, Function3 function3, int i, int i2, Composer composer, int i3) {
        pullToRefreshDefaults.m3457IndicatorBox1CPYgEU(pullToRefreshState, z, modifier, f, shape2, j, f2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Indicator_2poqoh4$lambda$8(PullToRefreshDefaults pullToRefreshDefaults, PullToRefreshState pullToRefreshState, boolean z, Modifier modifier, long j, long j2, float f, int i, int i2, Composer composer, int i3) {
        pullToRefreshDefaults.m3456Indicator2poqoh4(pullToRefreshState, z, modifier, j, j2, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    @Deprecated(message = "Use indicatorContainerColor instead", replaceWith = @ReplaceWith(expression = "indicatorContainerColor", imports = {}))
    public static /* synthetic */ void getContainerColor$annotations() {
    }

    @Deprecated(message = "Use indicatorShape instead", replaceWith = @ReplaceWith(expression = "indicatorShape", imports = {}))
    public static /* synthetic */ void getShape$annotations() {
    }

    private PullToRefreshDefaults() {
    }

    public final Shape getShape() {
        return shape;
    }

    static {
        PullToRefreshDefaults pullToRefreshDefaults = INSTANCE;
        IndicatorMaxDistance = PositionalThreshold;
        Elevation = ElevationTokens.INSTANCE.m3721getLevel2D9Ej5fM();
        LoadingIndicatorElevation = ElevationTokens.INSTANCE.m3719getLevel0D9Ej5fM();
    }

    public final Shape getIndicatorShape() {
        return indicatorShape;
    }

    public final long getContainerColor(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1066257972, "C(<get-containerColor>)405@15435L11:PullToRefresh.kt#djiw08");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1066257972, $changed, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.<get-containerColor> (PullToRefresh.kt:405)");
        }
        long surfaceContainerHigh = MaterialTheme.INSTANCE.getColorScheme($composer, 6).getSurfaceContainerHigh();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return surfaceContainerHigh;
    }

    public final long getIndicatorContainerColor(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -80510850, "C(<get-indicatorContainerColor>)409@15605L11:PullToRefresh.kt#djiw08");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-80510850, $changed, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.<get-indicatorContainerColor> (PullToRefresh.kt:409)");
        }
        long surfaceContainerHigh = MaterialTheme.INSTANCE.getColorScheme($composer, 6).getSurfaceContainerHigh();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return surfaceContainerHigh;
    }

    public final long getIndicatorColor(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1441334156, "C(<get-indicatorColor>)413@15766L11:PullToRefresh.kt#djiw08");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1441334156, $changed, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.<get-indicatorColor> (PullToRefresh.kt:413)");
        }
        long onSurfaceVariant = MaterialTheme.INSTANCE.getColorScheme($composer, 6).getOnSurfaceVariant();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return onSurfaceVariant;
    }

    /* JADX INFO: renamed from: getPositionalThreshold-D9Ej5fM, reason: not valid java name */
    public final float m3461getPositionalThresholdD9Ej5fM() {
        return PositionalThreshold;
    }

    /* JADX INFO: renamed from: getIndicatorMaxDistance-D9Ej5fM, reason: not valid java name */
    public final float m3459getIndicatorMaxDistanceD9Ej5fM() {
        return IndicatorMaxDistance;
    }

    /* JADX INFO: renamed from: getElevation-D9Ej5fM, reason: not valid java name */
    public final float m3458getElevationD9Ej5fM() {
        return Elevation;
    }

    /* JADX INFO: renamed from: getLoadingIndicatorElevation-D9Ej5fM, reason: not valid java name */
    public final float m3460getLoadingIndicatorElevationD9Ej5fM() {
        return LoadingIndicatorElevation;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0130  */
    /* JADX WARN: Code duplicated, block: B:103:0x0133  */
    /* JADX WARN: Code duplicated, block: B:106:0x013c  */
    /* JADX WARN: Code duplicated, block: B:119:0x0163 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:120:0x0165  */
    /* JADX WARN: Code duplicated, block: B:123:0x016e  */
    /* JADX WARN: Code duplicated, block: B:126:0x0177  */
    /* JADX WARN: Code duplicated, block: B:128:0x0180  */
    /* JADX WARN: Code duplicated, block: B:131:0x018a  */
    /* JADX WARN: Code duplicated, block: B:134:0x019a  */
    /* JADX WARN: Code duplicated, block: B:137:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:141:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:142:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:145:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:146:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:180:0x02f4  */
    /* JADX WARN: Code duplicated, block: B:183:0x0300  */
    /* JADX WARN: Code duplicated, block: B:184:0x0306  */
    /* JADX WARN: Code duplicated, block: B:194:0x03a6  */
    /* JADX WARN: Code duplicated, block: B:196:0x03b2  */
    /* JADX WARN: Code duplicated, block: B:199:0x03c5  */
    /* JADX WARN: Code duplicated, block: B:201:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x0107  */
    /* JADX WARN: Code duplicated, block: B:92:0x010d  */
    /* JADX WARN: Code duplicated, block: B:94:0x0112  */
    /* JADX WARN: Code duplicated, block: B:96:0x011a  */
    /* JADX WARN: Code duplicated, block: B:97:0x011d  */
    /* JADX WARN: Code duplicated, block: B:99:0x0122  */
    /* JADX INFO: renamed from: IndicatorBox-1CPYgEU, reason: not valid java name */
    public final void m3457IndicatorBox1CPYgEU(final PullToRefreshState state, final boolean isRefreshing, Modifier modifier, float maxDistance, Shape shape2, long containerColor, float elevation, final Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float maxDistance2;
        Shape shape3;
        long containerColor2;
        float elevation2;
        int i2;
        int i3;
        boolean z;
        boolean z2;
        final Modifier modifier3;
        final float elevation3;
        final float maxDistance3;
        final long containerColor3;
        final Shape shape4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Object it$iv;
        boolean z3;
        boolean z4;
        boolean invalid$iv;
        Object it$iv2;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        Composer $composer2 = $composer.startRestartGroup(-1341144489);
        ComposerKt.sourceInformation($composer2, "C(IndicatorBox)N(state,isRefreshing,modifier,maxDistance:c#ui.unit.Dp,shape,containerColor:c#ui.graphics.Color,elevation:c#ui.unit.Dp,content)461@17782L372,471@18183L951,457@17644L1652:PullToRefresh.kt#djiw08");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(isRefreshing) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                maxDistance2 = maxDistance;
                int i5 = $composer2.changed(maxDistance2) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                maxDistance2 = maxDistance;
            }
            $dirty |= i5;
        } else {
            maxDistance2 = maxDistance;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape3 = shape2;
                int i6 = $composer2.changed(shape3) ? 16384 : 8192;
                $dirty |= i6;
            } else {
                shape3 = shape2;
            }
            $dirty |= i6;
        } else {
            shape3 = shape2;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            containerColor2 = containerColor;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            containerColor2 = containerColor;
            $dirty |= $composer2.changed(containerColor2) ? 131072 : 65536;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                elevation2 = elevation;
                int i8 = $composer2.changed(elevation2) ? 1048576 : 524288;
                $dirty |= i8;
            } else {
                elevation2 = elevation;
            }
            $dirty |= i8;
        } else {
            elevation2 = elevation;
        }
        if ((i & 128) == 0) {
            if ((12582912 & $changed) == 0) {
                i2 = $composer2.changedInstance(function3) ? 8388608 : 4194304;
            }
            if ((i & 256) != 0) {
                $dirty |= 100663296;
            } else if ((100663296 & $changed) != 0) {
                if ($composer2.changed(this)) {
                    i3 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i3 = 33554432;
                }
                $dirty |= i3;
            }
            z = true;
            if (($dirty & 38347923) != 38347922) {
                z2 = true;
            } else {
                z2 = false;
            }
            if ($composer2.shouldExecute(z2, $dirty & 1)) {
                $composer2.startDefaults();
                if (($changed & 1) == 0 && !$composer2.getDefaultsInvalid()) {
                    $composer2.skipToGroupEnd();
                    if ((i & 8) != 0) {
                        $dirty &= -7169;
                    }
                    if ((i & 16) != 0) {
                        $dirty &= -57345;
                    }
                    if ((i & 64) != 0) {
                        $dirty &= -3670017;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i & 8) != 0) {
                        $dirty &= -7169;
                        maxDistance2 = IndicatorMaxDistance;
                    }
                    if ((i & 16) != 0) {
                        $dirty &= -57345;
                        shape3 = indicatorShape;
                    }
                    if (i7 != 0) {
                        containerColor2 = Color.INSTANCE.m5133getUnspecified0d7_KjU();
                    }
                    if ((i & 64) != 0) {
                        $dirty &= -3670017;
                        elevation2 = Elevation;
                    }
                }
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1341144489, $dirty, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.IndicatorBox (PullToRefresh.kt:456)");
                }
                Modifier modifierM865size3ABfNKs = SizeKt.m865size3ABfNKs(modifier2, PullToRefreshKt.getSpinnerContainerSize());
                ComposerKt.sourceInformationMarkerStart($composer2, -349648245, "CC(remember):PullToRefresh.kt#9igjgp");
                it$iv = $composer2.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = new Function1() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return PullToRefreshDefaults.IndicatorBox_1CPYgEU$lambda$2$lambda$1((ContentDrawScope) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierDrawWithContent = DrawModifierKt.drawWithContent(modifierM865size3ABfNKs, (Function1) it$iv);
                ComposerKt.sourceInformationMarkerStart($composer2, -349634834, "CC(remember):PullToRefresh.kt#9igjgp");
                if (($dirty & 14) == 4) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (($dirty & 112) == 32) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z5 = z3 | z4 | (((($dirty & 7168) ^ 3072) <= 2048 && $composer2.changed(maxDistance2)) || ($dirty & 3072) == 2048) | ((((3670016 & $dirty) ^ 1572864) <= 1048576 && $composer2.changed(elevation2)) || ($dirty & 1572864) == 1048576);
                if ((((57344 & $dirty) ^ 24576) > 16384 || !$composer2.changed(shape3)) && ($dirty & 24576) != 16384) {
                }
                invalid$iv = z5 | z;
                int $dirty2 = $dirty;
                it$iv2 = $composer2.rememberedValue();
                if (!invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    final float maxDistance4 = maxDistance2;
                    final Shape shape5 = shape3;
                    final float elevation4 = elevation2;
                    Object value$iv2 = new Function3() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return PullToRefreshDefaults.IndicatorBox_1CPYgEU$lambda$6$lambda$5(state, isRefreshing, maxDistance4, elevation4, shape5, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifier$iv = BackgroundKt.m264backgroundbw27NRU(LayoutModifierKt.layout(modifierDrawWithContent, (Function3) it$iv2), containerColor2, shape3);
                Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                int $changed$iv = (($dirty2 >> 12) & 7168) | 48;
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv = ($changed$iv << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function0 = constructor;
                    $composer2.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i9 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                function3.invoke(BoxScopeInstance.INSTANCE, $composer2, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier2;
                elevation3 = elevation2;
                long j = containerColor2;
                maxDistance3 = maxDistance2;
                containerColor3 = j;
                shape4 = shape3;
            } else {
                $composer2.skipToGroupEnd();
                modifier3 = modifier2;
                elevation3 = elevation2;
                long j2 = containerColor2;
                maxDistance3 = maxDistance2;
                containerColor3 = j2;
                shape4 = shape3;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return PullToRefreshDefaults.IndicatorBox_1CPYgEU$lambda$7(this.f$0, state, isRefreshing, modifier3, maxDistance3, shape4, containerColor3, elevation3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i2 = 12582912;
        $dirty |= i2;
        if ((i & 256) != 0) {
            $dirty |= 100663296;
        } else if ((100663296 & $changed) != 0) {
            if ($composer2.changed(this)) {
                i3 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            } else {
                i3 = 33554432;
            }
            $dirty |= i3;
        }
        z = true;
        if (($dirty & 38347923) != 38347922) {
            z2 = true;
        } else {
            z2 = false;
        }
        if ($composer2.shouldExecute(z2, $dirty & 1)) {
            $composer2.startDefaults();
            if (($changed & 1) == 0) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    maxDistance2 = IndicatorMaxDistance;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    shape3 = indicatorShape;
                }
                if (i7 != 0) {
                    containerColor2 = Color.INSTANCE.m5133getUnspecified0d7_KjU();
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    elevation2 = Elevation;
                }
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    maxDistance2 = IndicatorMaxDistance;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    shape3 = indicatorShape;
                }
                if (i7 != 0) {
                    containerColor2 = Color.INSTANCE.m5133getUnspecified0d7_KjU();
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    elevation2 = Elevation;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1341144489, $dirty, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.IndicatorBox (PullToRefresh.kt:456)");
            }
            Modifier modifierM865size3ABfNKs2 = SizeKt.m865size3ABfNKs(modifier2, PullToRefreshKt.getSpinnerContainerSize());
            ComposerKt.sourceInformationMarkerStart($composer2, -349648245, "CC(remember):PullToRefresh.kt#9igjgp");
            it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = new Function1() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PullToRefreshDefaults.IndicatorBox_1CPYgEU$lambda$2$lambda$1((ContentDrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
                it$iv = value$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierDrawWithContent2 = DrawModifierKt.drawWithContent(modifierM865size3ABfNKs2, (Function1) it$iv);
            ComposerKt.sourceInformationMarkerStart($composer2, -349634834, "CC(remember):PullToRefresh.kt#9igjgp");
            if (($dirty & 14) == 4) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (($dirty & 112) == 32) {
                z4 = true;
            } else {
                z4 = false;
            }
            boolean z6 = z3 | z4 | (((($dirty & 7168) ^ 3072) <= 2048 && $composer2.changed(maxDistance2)) || ($dirty & 3072) == 2048) | ((((3670016 & $dirty) ^ 1572864) <= 1048576 && $composer2.changed(elevation2)) || ($dirty & 1572864) == 1048576);
            z = ((57344 & $dirty) ^ 24576) > 16384 ? false : false;
            invalid$iv = z6 | z;
            int $dirty3 = $dirty;
            it$iv2 = $composer2.rememberedValue();
            if (!invalid$iv) {
            }
            final float maxDistance5 = maxDistance2;
            final Shape shape6 = shape3;
            final float elevation5 = elevation2;
            Object value$iv4 = new Function3() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return PullToRefreshDefaults.IndicatorBox_1CPYgEU$lambda$6$lambda$5(state, isRefreshing, maxDistance5, elevation5, shape6, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                }
            };
            $composer2.updateRememberedValue(value$iv4);
            it$iv2 = value$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv2 = BackgroundKt.m264backgroundbw27NRU(LayoutModifierKt.layout(modifierDrawWithContent2, (Function3) it$iv2), containerColor2, shape3);
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getCenter();
            int $changed$iv2 = (($dirty3 >> 12) & 7168) | 48;
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
            int $changed$iv$iv2 = ($changed$iv2 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            int i10 = ($changed$iv$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            function3.invoke(BoxScopeInstance.INSTANCE, $composer2, Integer.valueOf((($changed$iv2 >> 6) & 112) | 6));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            elevation3 = elevation2;
            long j3 = containerColor2;
            maxDistance3 = maxDistance2;
            containerColor3 = j3;
            shape4 = shape3;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            elevation3 = elevation2;
            long j4 = containerColor2;
            maxDistance3 = maxDistance2;
            containerColor3 = j4;
            shape4 = shape3;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PullToRefreshDefaults.IndicatorBox_1CPYgEU$lambda$7(this.f$0, state, isRefreshing, modifier3, maxDistance3, shape4, containerColor3, elevation3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit IndicatorBox_1CPYgEU$lambda$2$lambda$1(ContentDrawScope $this$drawWithContent) {
        ContentDrawScope $this$clipRect_u2drOu3jXo_u24default$iv = $this$drawWithContent;
        int clipOp$iv = ClipOp.INSTANCE.m5086getIntersectrtfAjoo();
        DrawContext $this$withTransform_u24lambda_u246$iv$iv = $this$clipRect_u2drOu3jXo_u24default$iv.getDrawContext();
        long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv.mo5592getSizeNHjbRc();
        $this$withTransform_u24lambda_u246$iv$iv.getCanvas().save();
        try {
            DrawTransform $this$clipRect_rOu3jXo_u24lambda_u244$iv = $this$withTransform_u24lambda_u246$iv$iv.getTransform();
            $this$clipRect_rOu3jXo_u24lambda_u244$iv.mo5595clipRectN_I0leg(-3.4028235E38f, 0.0f, Float.MAX_VALUE, Float.MAX_VALUE, clipOp$iv);
            $this$drawWithContent.drawContent();
            return Unit.INSTANCE;
        } finally {
            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
            $this$withTransform_u24lambda_u246$iv$iv.mo5593setSizeuvyYCjk(previousSize$iv$iv);
        }
    }

    static final MeasureResult IndicatorBox_1CPYgEU$lambda$6$lambda$5(final PullToRefreshState $state, final boolean $isRefreshing, final float $maxDistance, final float $elevation, final Shape $shape, MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        final Placeable placeable = measurable.mo6541measureBRTryo0(constraints.getValue());
        return MeasureScope.layout$default($this$layout, placeable.getWidth(), placeable.getHeight(), null, new Function1() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PullToRefreshDefaults.IndicatorBox_1CPYgEU$lambda$6$lambda$5$lambda$4(placeable, $state, $isRefreshing, $maxDistance, $elevation, $shape, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit IndicatorBox_1CPYgEU$lambda$6$lambda$5$lambda$4(Placeable $placeable, final PullToRefreshState $state, final boolean $isRefreshing, final float $maxDistance, final float $elevation, final Shape $shape, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeWithLayer$default($this$layout, $placeable, 0, 0, 0.0f, new Function1() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PullToRefreshDefaults.IndicatorBox_1CPYgEU$lambda$6$lambda$5$lambda$4$lambda$3($state, $isRefreshing, $maxDistance, $elevation, $shape, (GraphicsLayerScope) obj);
            }
        }, 4, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit IndicatorBox_1CPYgEU$lambda$6$lambda$5$lambda$4$lambda$3(PullToRefreshState $state, boolean $isRefreshing, float $maxDistance, float $elevation, Shape $shape, GraphicsLayerScope $this$placeWithLayer) {
        float f = 0.0f;
        boolean showElevation = $state.getDistanceFraction() > 0.0f || $isRefreshing;
        float distanceFraction = $state.getDistanceFraction() * $this$placeWithLayer.mo400roundToPx0680j_4($maxDistance);
        long arg0$iv = $this$placeWithLayer.getSize();
        int bits$iv$iv$iv = (int) (4294967295L & arg0$iv);
        $this$placeWithLayer.setTranslationY(distanceFraction - Float.intBitsToFloat(bits$iv$iv$iv));
        if (showElevation) {
            f = $this$placeWithLayer.mo406toPx0680j_4($elevation);
        }
        $this$placeWithLayer.setShadowElevation(f);
        $this$placeWithLayer.setShape($shape);
        $this$placeWithLayer.setClip(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: Indicator-2poqoh4, reason: not valid java name */
    public final void m3456Indicator2poqoh4(final PullToRefreshState state, final boolean isRefreshing, Modifier modifier, long containerColor, long color, float maxDistance, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        long color2;
        final float maxDistance2;
        Composer $composer2;
        final Modifier modifier3;
        final long containerColor2;
        final long color3;
        Modifier.Companion modifier4;
        long containerColor3;
        int $dirty;
        float maxDistance3;
        final long color4;
        Composer $composer3 = $composer.startRestartGroup(-1076870256);
        ComposerKt.sourceInformation($composer3, "C(Indicator)N(state,isRefreshing,modifier,containerColor:c#ui.graphics.Color,color:c#ui.graphics.Color,maxDistance:c#ui.unit.Dp)522@20382L755,516@20173L964:PullToRefresh.kt#djiw08");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(isRefreshing) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j = containerColor;
                int i3 = $composer3.changed(j) ? 2048 : 1024;
                $dirty2 |= i3;
            } else {
                j = containerColor;
            }
            $dirty2 |= i3;
        } else {
            j = containerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                color2 = color;
                int i4 = $composer3.changed(color2) ? 16384 : 8192;
                $dirty2 |= i4;
            } else {
                color2 = color;
            }
            $dirty2 |= i4;
        } else {
            color2 = color;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                maxDistance2 = maxDistance;
                int i5 = $composer3.changed(maxDistance2) ? 131072 : 65536;
                $dirty2 |= i5;
            } else {
                maxDistance2 = maxDistance;
            }
            $dirty2 |= i5;
        } else {
            maxDistance2 = maxDistance;
        }
        if ((i & 64) != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changed(this) ? 1048576 : 524288;
        }
        if ($composer3.shouldExecute((599187 & $dirty2) != 599186, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "512@20040L23,513@20093L14");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                modifier4 = modifier2;
                containerColor3 = j;
                color4 = color2;
                $dirty = $dirty2;
                maxDistance3 = maxDistance2;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 8) == 0) {
                    containerColor3 = j;
                } else {
                    containerColor3 = getIndicatorContainerColor($composer3, ($dirty2 >> 18) & 14);
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    color2 = getIndicatorColor($composer3, ($dirty2 >> 18) & 14);
                    $dirty2 &= -57345;
                }
                if ((i & 32) == 0) {
                    $dirty = $dirty2;
                    maxDistance3 = maxDistance2;
                    color4 = color2;
                } else {
                    $dirty = $dirty2 & (-458753);
                    maxDistance3 = IndicatorMaxDistance;
                    color4 = color2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1076870256, $dirty, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator (PullToRefresh.kt:515)");
            }
            Modifier modifier5 = modifier4;
            m3457IndicatorBox1CPYgEU(state, isRefreshing, modifier5, maxDistance3, null, containerColor3, 0.0f, ComposableLambdaKt.rememberComposableLambda(298232649, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer, Integer num) {
                    invoke(boxScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(BoxScope $this$IndicatorBox, Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C526@20600L7,527@20623L504,524@20476L651:PullToRefresh.kt#djiw08");
                    if (!$composer4.shouldExecute(($changed2 & 17) != 16, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(298232649, $changed2, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous> (PullToRefresh.kt:524)");
                    }
                    CrossfadeKt.Crossfade(Boolean.valueOf(isRefreshing), (Modifier) null, (FiniteAnimationSpec<Float>) MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer4, 6), (String) null, ComposableLambdaKt.rememberComposableLambda(-2064098104, true, new AnonymousClass1(color4, state), $composer4, 54), $composer4, 24576, 10);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                /* JADX INFO: renamed from: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1, reason: invalid class name */
                /* JADX INFO: compiled from: PullToRefresh.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                static final class AnonymousClass1 implements Function3<Boolean, Composer, Integer, Unit> {
                    final /* synthetic */ long $color;
                    final /* synthetic */ PullToRefreshState $state;

                    AnonymousClass1(long j, PullToRefreshState pullToRefreshState) {
                        this.$color = j;
                        this.$state = pullToRefreshState;
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Composer composer, Integer num) {
                        invoke(bool.booleanValue(), composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean refreshing, Composer $composer, int $changed) {
                        ComposerKt.sourceInformation($composer, "CN(refreshing):PullToRefresh.kt#djiw08");
                        int $dirty = $changed;
                        if (($changed & 6) == 0) {
                            $dirty |= $composer.changed(refreshing) ? 4 : 2;
                        }
                        int $dirty2 = $dirty;
                        if (!$composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
                            $composer.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2064098104, $dirty2, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:528)");
                        }
                        if (refreshing) {
                            $composer.startReplaceGroup(-499784343);
                            ComposerKt.sourceInformation($composer, "529@20693L201");
                            ProgressIndicatorKt.m2727CircularProgressIndicator4lLiAd8(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, PullToRefreshKt.getSpinnerSize()), this.$color, PullToRefreshKt.StrokeWidth, 0L, 0, 0.0f, $composer, 390, 56);
                            $composer.endReplaceGroup();
                        } else {
                            $composer.startReplaceGroup(-499540745);
                            ComposerKt.sourceInformation($composer, "536@21007L26,535@20940L155");
                            ComposerKt.sourceInformationMarkerStart($composer, 676625122, "CC(remember):PullToRefresh.kt#9igjgp");
                            boolean invalid$iv = $composer.changed(this.$state);
                            final PullToRefreshState pullToRefreshState = this.$state;
                            Object it$iv = $composer.rememberedValue();
                            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                                Object value$iv = 
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0096: CONSTRUCTOR (r9v0 'value$iv' java.lang.Object) = (r1v4 'pullToRefreshState' androidx.compose.material3.pulltorefresh.PullToRefreshState A[DONT_INLINE]) A[DECLARE_VAR, MD:(androidx.compose.material3.pulltorefresh.PullToRefreshState):void (m)] (LINE:537) call: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$$ExternalSyntheticLambda0.<init>(androidx.compose.material3.pulltorefresh.PullToRefreshState):void type: CONSTRUCTOR in method: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1.1.invoke(boolean, androidx.compose.runtime.Composer, int):void, file: classes10.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:140)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
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
                                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$$ExternalSyntheticLambda0, state: NOT_LOADED
                                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                    	... 31 more
                                    */
                                /*
                                    this = this;
                                    r8 = r14
                                    java.lang.String r0 = "CN(refreshing):PullToRefresh.kt#djiw08"
                                    androidx.compose.runtime.ComposerKt.sourceInformation(r14, r0)
                                    r0 = r15
                                    r1 = r15 & 6
                                    if (r1 != 0) goto L15
                                    boolean r1 = r14.changed(r13)
                                    if (r1 == 0) goto L13
                                    r1 = 4
                                    goto L14
                                L13:
                                    r1 = 2
                                L14:
                                    r0 = r0 | r1
                                L15:
                                    r11 = r0
                                    r0 = r11 & 19
                                    r1 = 18
                                    r2 = 0
                                    if (r0 == r1) goto L1f
                                    r0 = 1
                                    goto L20
                                L1f:
                                    r0 = r2
                                L20:
                                    r1 = r11 & 1
                                    boolean r0 = r14.shouldExecute(r0, r1)
                                    if (r0 == 0) goto Lb6
                                    boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                    if (r0 == 0) goto L37
                                    r0 = -1
                                    java.lang.String r1 = "androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator.<anonymous>.<anonymous> (PullToRefresh.kt:528)"
                                    r3 = -2064098104(0xffffffff84f85cc8, float:-5.8389726E-36)
                                    androidx.compose.runtime.ComposerKt.traceEventStart(r3, r11, r0, r1)
                                L37:
                                    if (r13 == 0) goto L66
                                    r0 = -499784343(0xffffffffe235e569, float:-8.3884786E20)
                                    r14.startReplaceGroup(r0)
                                    java.lang.String r0 = "529@20693L201"
                                    androidx.compose.runtime.ComposerKt.sourceInformation(r14, r0)
                                    float r3 = androidx.compose.material3.pulltorefresh.PullToRefreshKt.access$getStrokeWidth$p()
                                    androidx.compose.ui.Modifier$Companion r0 = androidx.compose.ui.Modifier.INSTANCE
                                    androidx.compose.ui.Modifier r0 = (androidx.compose.ui.Modifier) r0
                                    float r1 = androidx.compose.material3.pulltorefresh.PullToRefreshKt.getSpinnerSize()
                                    androidx.compose.ui.Modifier r0 = androidx.compose.foundation.layout.SizeKt.m865size3ABfNKs(r0, r1)
                                    long r1 = r12.$color
                                    r4 = 0
                                    r6 = 0
                                    r7 = 0
                                    r9 = 390(0x186, float:5.47E-43)
                                    r10 = 56
                                    androidx.compose.material3.ProgressIndicatorKt.m2727CircularProgressIndicator4lLiAd8(r0, r1, r3, r4, r6, r7, r8, r9, r10)
                                    r14.endReplaceGroup()
                                    goto Lac
                                L66:
                                    r0 = -499540745(0xffffffffe2399cf7, float:-8.5598954E20)
                                    r14.startReplaceGroup(r0)
                                    java.lang.String r0 = "536@21007L26,535@20940L155"
                                    androidx.compose.runtime.ComposerKt.sourceInformation(r14, r0)
                                    r0 = 676625122(0x28547ae2, float:1.179501E-14)
                                    java.lang.String r1 = "CC(remember):PullToRefresh.kt#9igjgp"
                                    androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r14, r0, r1)
                                    androidx.compose.material3.pulltorefresh.PullToRefreshState r0 = r12.$state
                                    boolean r0 = r14.changed(r0)
                                    androidx.compose.material3.pulltorefresh.PullToRefreshState r1 = r12.$state
                                    r3 = r14
                                    r4 = 0
                                    java.lang.Object r5 = r3.rememberedValue()
                                    r6 = 0
                                    if (r0 != 0) goto L93
                                    androidx.compose.runtime.Composer$Companion r7 = androidx.compose.runtime.Composer.INSTANCE
                                    java.lang.Object r7 = r7.getEmpty()
                                    if (r5 == r7) goto L93
                                    goto L9e
                                L93:
                                    r7 = 0
                                    androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$$ExternalSyntheticLambda0 r9 = new androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1$1$$ExternalSyntheticLambda0
                                    r9.<init>(r1)
                                    r3.updateRememberedValue(r9)
                                    r5 = r9
                                L9e:
                                    androidx.compose.material3.internal.FloatProducer r5 = (androidx.compose.material3.internal.FloatProducer) r5
                                    androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r14)
                                    long r0 = r12.$color
                                    androidx.compose.material3.pulltorefresh.PullToRefreshKt.m3464access$CircularArrowProgressIndicatorRPmYEkk(r5, r0, r14, r2)
                                    r14.endReplaceGroup()
                                Lac:
                                    boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
                                    if (r0 == 0) goto Lb9
                                    androidx.compose.runtime.ComposerKt.traceEventEnd()
                                    goto Lb9
                                Lb6:
                                    r14.skipToGroupEnd()
                                Lb9:
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$Indicator$1.AnonymousClass1.invoke(boolean, androidx.compose.runtime.Composer, int):void");
                            }
                        }
                    }, $composer3, 54), $composer3, ($dirty & 14) | 12582912 | ($dirty & 112) | ($dirty & 896) | (($dirty >> 6) & 7168) | (($dirty << 6) & 458752) | (($dirty << 6) & 234881024), 80);
                    $composer2 = $composer3;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    maxDistance2 = maxDistance3;
                    containerColor2 = containerColor3;
                    color3 = color4;
                    modifier3 = modifier5;
                } else {
                    $composer2 = $composer3;
                    $composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    containerColor2 = j;
                    color3 = color2;
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshDefaults$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return PullToRefreshDefaults.Indicator_2poqoh4$lambda$8(this.f$0, state, isRefreshing, modifier3, containerColor2, color3, maxDistance2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
        }
