package androidx.compose.material;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\u001a\u0089\u0001\u0010\u0000\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\u001e\b\u0002\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0004¢\u0006\u0002\b\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0081\u0001\u0010\u0000\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\u001e\b\u0002\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0004¢\u0006\u0002\b\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001ag\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u001c\u0010\u0019\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0004¢\u0006\u0002\b\rH\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a_\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u001c\u0010\u0019\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0004¢\u0006\u0002\b\rH\u0007¢\u0006\u0004\b\u001c\u0010\u001d\u001as\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u001c\u0010\u0019\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0004¢\u0006\u0002\b\rH\u0007¢\u0006\u0004\b!\u0010\"\u001ak\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u001c\u0010\u0019\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0004¢\u0006\u0002\b\rH\u0007¢\u0006\u0004\b#\u0010$\u001a\u0011\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&H\u0082\b\u001a\u0019\u0010(\u001a\u00020&2\u0006\u0010)\u001a\u00020&2\u0006\u0010*\u001a\u00020&H\u0080\b\u001a,\u0010+\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&0,2\u0006\u0010-\u001a\u00020&2\u0006\u0010*\u001a\u00020&2\u0006\u0010.\u001a\u00020&H\u0000\u001ag\u0010/\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00100\u001a\u00020 2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u001c\u0010\u0019\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0004¢\u0006\u0002\b\rH\u0003¢\u0006\u0004\b1\u00102\"\u0010\u00103\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u00104\"\u0010\u00105\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u00104\"\u000e\u00106\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u00107\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u00108\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u00104\"\u0010\u00109\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u00104\"\u000e\u0010:\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"TopAppBar", "", "title", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "modifier", "Landroidx/compose/ui/Modifier;", "navigationIcon", "actions", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Lkotlin/ExtensionFunctionType;", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "contentColor", "elevation", "Landroidx/compose/ui/unit/Dp;", "TopAppBar-Rx1qByU", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;JJFLandroidx/compose/runtime/Composer;II)V", "TopAppBar-xWeB9-s", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;JJFLandroidx/compose/runtime/Composer;II)V", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", UriUtil.LOCAL_CONTENT_SCHEME, "TopAppBar-afqeVBk", "(Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "TopAppBar-HsRjFd4", "(Landroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomAppBar", "cutoutShape", "Landroidx/compose/ui/graphics/Shape;", "BottomAppBar-DanWW-k", "(Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/graphics/Shape;FLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomAppBar-Y1yfwus", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/graphics/Shape;FLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "square", "", "x", "calculateCutoutCircleYIntercept", "cutoutRadius", "verticalOffset", "calculateRoundedEdgeIntercept", "Lkotlin/Pair;", "controlPointX", "radius", "AppBar", "shape", "AppBar-HkEspTQ", "(JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "AppBarHeight", "F", "AppBarHorizontalPadding", "TitleInsetWithoutIcon", "TitleIconModifier", "BottomAppBarCutoutOffset", "BottomAppBarRoundedEdgeRadius", "ZeroInsets", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AppBarKt {
    private static final float AppBarHeight = Dp.m7905constructorimpl(56);
    private static final float AppBarHorizontalPadding = Dp.m7905constructorimpl(4);
    private static final float BottomAppBarCutoutOffset;
    private static final float BottomAppBarRoundedEdgeRadius;
    private static final Modifier TitleIconModifier;
    private static final Modifier TitleInsetWithoutIcon;
    private static final WindowInsets ZeroInsets;

    static final Unit AppBar_HkEspTQ$lambda$1(long j, long j2, float f, PaddingValues paddingValues, Shape shape, WindowInsets windowInsets, Modifier modifier, Function3 function3, int i, int i2, Composer composer, int i3) {
        m1689AppBarHkEspTQ(j, j2, f, paddingValues, shape, windowInsets, modifier, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit BottomAppBar_DanWW_k$lambda$0(WindowInsets windowInsets, Modifier modifier, long j, long j2, Shape shape, float f, PaddingValues paddingValues, Function3 function3, int i, int i2, Composer composer, int i3) {
        m1690BottomAppBarDanWWk(windowInsets, modifier, j, j2, shape, f, paddingValues, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit BottomAppBar_Y1yfwus$lambda$0(Modifier modifier, long j, long j2, Shape shape, float f, PaddingValues paddingValues, Function3 function3, int i, int i2, Composer composer, int i3) {
        m1691BottomAppBarY1yfwus(modifier, j, j2, shape, f, paddingValues, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TopAppBar_HsRjFd4$lambda$0(Modifier modifier, long j, long j2, float f, PaddingValues paddingValues, Function3 function3, int i, int i2, Composer composer, int i3) {
        m1692TopAppBarHsRjFd4(modifier, j, j2, f, paddingValues, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TopAppBar_Rx1qByU$lambda$1(Function2 function2, WindowInsets windowInsets, Modifier modifier, Function2 function3, Function3 function4, long j, long j2, float f, int i, int i2, Composer composer, int i3) {
        m1693TopAppBarRx1qByU(function2, windowInsets, modifier, function3, function4, j, j2, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TopAppBar_afqeVBk$lambda$0(WindowInsets windowInsets, Modifier modifier, long j, long j2, float f, PaddingValues paddingValues, Function3 function3, int i, int i2, Composer composer, int i3) {
        m1694TopAppBarafqeVBk(windowInsets, modifier, j, j2, f, paddingValues, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TopAppBar_xWeB9_s$lambda$0(Function2 function2, Modifier modifier, Function2 function3, Function3 function4, long j, long j2, float f, int i, int i2, Composer composer, int i3) {
        m1695TopAppBarxWeB9s(function2, modifier, function3, function4, j, j2, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: TopAppBar-Rx1qByU, reason: not valid java name */
    public static final void m1693TopAppBarRx1qByU(final Function2<? super Composer, ? super Integer, Unit> function2, final WindowInsets windowInsets, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function4, long j, long j2, float f, Composer $composer, final int $changed, final int i) {
        WindowInsets windowInsets2;
        Modifier modifier2;
        final Function2<? super Composer, ? super Integer, Unit> function5;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> lambda$269254275$material;
        long primarySurface;
        Composer $composer2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function7;
        final long j3;
        final float f2;
        final long j4;
        long jM1783contentColorForek8zF_U;
        Modifier modifier4;
        int $dirty;
        long j5;
        float fM1687getTopAppBarElevationD9Ej5fM;
        Composer $composer3 = $composer.startRestartGroup(138090236);
        ComposerKt.sourceInformation($composer3, "C(TopAppBar)N(title,windowInsets,modifier,navigationIcon,actions,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation:c#ui.unit.Dp)101@4667L1063,93@4484L1246:AppBar.kt#jmzs0o");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            windowInsets2 = windowInsets;
            $dirty2 |= $composer3.changed(windowInsets2) ? 32 : 16;
        } else {
            windowInsets2 = windowInsets;
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
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
            function5 = function3;
        } else if (($changed & 3072) == 0) {
            function5 = function3;
            $dirty2 |= $composer3.changedInstance(function5) ? 2048 : 1024;
        } else {
            function5 = function3;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty2 |= 24576;
            lambda$269254275$material = function4;
        } else if (($changed & 24576) == 0) {
            lambda$269254275$material = function4;
            $dirty2 |= $composer3.changedInstance(lambda$269254275$material) ? 16384 : 8192;
        } else {
            lambda$269254275$material = function4;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                primarySurface = j;
                int i5 = $composer3.changed(primarySurface) ? 131072 : 65536;
                $dirty2 |= i5;
            } else {
                primarySurface = j;
            }
            $dirty2 |= i5;
        } else {
            primarySurface = j;
        }
        if ((1572864 & $changed) == 0) {
            $dirty2 |= ((i & 64) == 0 && $composer3.changed(j2)) ? 1048576 : 524288;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(f) ? 8388608 : 4194304;
        }
        int $dirty3 = $dirty2;
        if ($composer3.shouldExecute((4793491 & $dirty2) != 4793490, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "89@4338L6,90@4387L32");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty3 &= -3670017;
                }
                fM1687getTopAppBarElevationD9Ej5fM = f;
                modifier4 = modifier2;
                $dirty = $dirty3;
                j5 = j2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    function5 = null;
                }
                if (i4 != 0) {
                    lambda$269254275$material = ComposableSingletons$AppBarKt.INSTANCE.getLambda$269254275$material();
                }
                if ((i & 32) != 0) {
                    primarySurface = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer3, 6));
                    $dirty3 &= -458753;
                }
                if ((i & 64) == 0) {
                    jM1783contentColorForek8zF_U = j2;
                } else {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(primarySurface, $composer3, ($dirty3 >> 15) & 14);
                    $dirty3 &= -3670017;
                }
                if (i6 == 0) {
                    modifier4 = modifier2;
                    $dirty = $dirty3;
                    j5 = jM1783contentColorForek8zF_U;
                    fM1687getTopAppBarElevationD9Ej5fM = f;
                } else {
                    long j6 = jM1783contentColorForek8zF_U;
                    fM1687getTopAppBarElevationD9Ej5fM = AppBarDefaults.INSTANCE.m1687getTopAppBarElevationD9Ej5fM();
                    $dirty = $dirty3;
                    j5 = j6;
                    modifier4 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(138090236, $dirty, -1, "androidx.compose.material.TopAppBar (AppBar.kt:92)");
            }
            $composer2 = $composer3;
            m1689AppBarHkEspTQ(primarySurface, j5, fM1687getTopAppBarElevationD9Ej5fM, AppBarDefaults.INSTANCE.getContentPadding(), RectangleShapeKt.getRectangleShape(), windowInsets2, modifier4, ComposableLambdaKt.rememberComposableLambda(-2019867954, true, new Function3() { // from class: androidx.compose.material.AppBarKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return AppBarKt.TopAppBar_Rx1qByU$lambda$0(function5, function2, lambda$269254275$material, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), $composer2, (($dirty >> 15) & 14) | 12610560 | (($dirty >> 15) & 112) | (($dirty >> 15) & 896) | (($dirty << 12) & 458752) | (($dirty << 12) & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function6 = function5;
            function7 = lambda$269254275$material;
            j3 = j5;
            f2 = fM1687getTopAppBarElevationD9Ej5fM;
            modifier3 = modifier4;
            j4 = primarySurface;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            function6 = function5;
            function7 = lambda$269254275$material;
            j3 = j2;
            f2 = f;
            j4 = primarySurface;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.AppBarKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.TopAppBar_Rx1qByU$lambda$1(function2, windowInsets, modifier3, function6, function7, j4, j3, f2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:52:0x0343  */
    static final Unit TopAppBar_Rx1qByU$lambda$0(Function2 $navigationIcon, final Function2 $title, final Function3 $actions, RowScope $this$AppBar, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        String str;
        int i;
        Function0<ComposeUiNode> function1;
        int compositeKeyHash$iv$iv;
        ComposerKt.sourceInformation($composer, "C113@5056L345,122@5476L6,122@5484L240,122@5411L313:AppBar.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed($this$AppBar) ? 4 : 2;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2019867954, $dirty, -1, "androidx.compose.material.TopAppBar.<anonymous> (AppBar.kt:102)");
            }
            if ($navigationIcon == null) {
                $composer.startReplaceGroup(-1394361313);
                ComposerKt.sourceInformation($composer, "103@4719L29");
                SpacerKt.Spacer(TitleInsetWithoutIcon, $composer, 6);
                $composer.endReplaceGroup();
                str = "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo";
                i = 6;
            } else {
                $composer.startReplaceGroup(-1394295686);
                ComposerKt.sourceInformation($composer, "105@4778L258");
                Modifier modifier$iv = TitleIconModifier;
                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                int $changed$iv$iv = (390 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
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
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    str = "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo";
                } else {
                    str = "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo";
                    if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i2 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    int i3 = ((390 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 2031468274, "C107@4953L4,106@4867L155:AppBar.kt#jmzs0o");
                    i = 6;
                    CompositionLocalKt.CompositionLocalProvider(ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(ContentAlpha.INSTANCE.getHigh($composer, 6))), (Function2<? super Composer, ? super Integer, Unit>) $navigationIcon, $composer, ProvidedValue.$stable);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endReplaceGroup();
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i4 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                int i5 = ((390 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 2031468274, "C107@4953L4,106@4867L155:AppBar.kt#jmzs0o");
                i = 6;
                CompositionLocalKt.CompositionLocalProvider(ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(ContentAlpha.INSTANCE.getHigh($composer, 6))), (Function2<? super Composer, ? super Integer, Unit>) $navigationIcon, $composer, ProvidedValue.$stable);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
            }
            int i6 = i;
            Modifier modifier$iv2 = RowScope.weight$default($this$AppBar, SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null);
            Alignment.Vertical verticalAlignment$iv2 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, str);
            Arrangement.Horizontal horizontalArrangement$iv2 = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv2, verticalAlignment$iv2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv2 = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
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
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                compositeKeyHash$iv$iv = compositeKeyHash$iv$iv3;
            } else {
                compositeKeyHash$iv$iv = compositeKeyHash$iv$iv3;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i7 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                int i8 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -297206295, "C114@5198L10,114@5213L178,114@5159L232:AppBar.kt#jmzs0o");
                TextKt.ProvideTextStyle(MaterialTheme.INSTANCE.getTypography($composer, i6).getH6(), ComposableLambdaKt.rememberComposableLambda(1206983395, true, new Function2() { // from class: androidx.compose.material.AppBarKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return AppBarKt.TopAppBar_Rx1qByU$lambda$0$1$0($title, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer, 54), $composer, 48);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                CompositionLocalKt.CompositionLocalProvider(ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(ContentAlpha.INSTANCE.getMedium($composer, 6))), ComposableLambdaKt.rememberComposableLambda(-1033635954, true, new Function2() { // from class: androidx.compose.material.AppBarKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return AppBarKt.TopAppBar_Rx1qByU$lambda$0$2($actions, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer, 54), $composer, ProvidedValue.$stable | 48);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            int i9 = ($changed$iv$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
            int i10 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -297206295, "C114@5198L10,114@5213L178,114@5159L232:AppBar.kt#jmzs0o");
            TextKt.ProvideTextStyle(MaterialTheme.INSTANCE.getTypography($composer, i6).getH6(), ComposableLambdaKt.rememberComposableLambda(1206983395, true, new Function2() { // from class: androidx.compose.material.AppBarKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.TopAppBar_Rx1qByU$lambda$0$1$0($title, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 48);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            CompositionLocalKt.CompositionLocalProvider(ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(ContentAlpha.INSTANCE.getMedium($composer, 6))), ComposableLambdaKt.rememberComposableLambda(-1033635954, true, new Function2() { // from class: androidx.compose.material.AppBarKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.TopAppBar_Rx1qByU$lambda$0$2($actions, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TopAppBar_Rx1qByU$lambda$0$1$0(Function2 $title, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C116@5317L4,115@5231L146:AppBar.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1206983395, $changed, -1, "androidx.compose.material.TopAppBar.<anonymous>.<anonymous>.<anonymous> (AppBar.kt:115)");
            }
            CompositionLocalKt.CompositionLocalProvider(ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(ContentAlpha.INSTANCE.getHigh($composer, 6))), (Function2<? super Composer, ? super Integer, Unit>) $title, $composer, ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TopAppBar_Rx1qByU$lambda$0$2(Function3 $actions, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C123@5498L216:AppBar.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1033635954, $changed, -1, "androidx.compose.material.TopAppBar.<anonymous>.<anonymous> (AppBar.kt:123)");
            }
            Modifier modifier$iv = SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            int $changed$iv$iv = (438 << 3) & 112;
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
            ComposerKt.sourceInformationMarkerStart($composer, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            $actions.invoke(RowScopeInstance.INSTANCE, $composer, Integer.valueOf(((438 >> 6) & 112) | 6));
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: TopAppBar-xWeB9-s, reason: not valid java name */
    public static final void m1695TopAppBarxWeB9s(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function4, long j, long j2, float f, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function5;
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> lambda$1260131259$material;
        long primarySurface;
        long jM1783contentColorForek8zF_U;
        Composer $composer2;
        final float f2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function8;
        final long j3;
        final long j4;
        int $dirty;
        float fM1687getTopAppBarElevationD9Ej5fM;
        int $dirty2;
        long j5;
        long j6;
        int $dirty3;
        Modifier modifier4;
        Function2<? super Composer, ? super Integer, Unit> function9;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function10;
        Composer $composer3 = $composer.startRestartGroup(-350082398);
        ComposerKt.sourceInformation($composer3, "C(TopAppBar)N(title,modifier,navigationIcon,actions,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation:c#ui.unit.Dp)169@7728L176:AppBar.kt#jmzs0o");
        int $dirty4 = $changed;
        if (($changed & 6) == 0) {
            function5 = function2;
            $dirty4 |= $composer3.changedInstance(function5) ? 4 : 2;
        } else {
            function5 = function2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty4 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty4 |= 384;
            function6 = function3;
        } else if (($changed & 384) == 0) {
            function6 = function3;
            $dirty4 |= $composer3.changedInstance(function6) ? 256 : 128;
        } else {
            function6 = function3;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty4 |= 3072;
            lambda$1260131259$material = function4;
        } else if (($changed & 3072) == 0) {
            lambda$1260131259$material = function4;
            $dirty4 |= $composer3.changedInstance(lambda$1260131259$material) ? 2048 : 1024;
        } else {
            lambda$1260131259$material = function4;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                primarySurface = j;
                int i5 = $composer3.changed(primarySurface) ? 16384 : 8192;
                $dirty4 |= i5;
            } else {
                primarySurface = j;
            }
            $dirty4 |= i5;
        } else {
            primarySurface = j;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                jM1783contentColorForek8zF_U = j2;
                int i6 = $composer3.changed(jM1783contentColorForek8zF_U) ? 131072 : 65536;
                $dirty4 |= i6;
            } else {
                jM1783contentColorForek8zF_U = j2;
            }
            $dirty4 |= i6;
        } else {
            jM1783contentColorForek8zF_U = j2;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty4 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty4 |= $composer3.changed(f) ? 1048576 : 524288;
        }
        int $dirty5 = $dirty4;
        if (!$composer3.shouldExecute(($dirty4 & 599187) != 599186, $dirty5 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            f2 = f;
            modifier3 = modifier2;
            function7 = function6;
            function8 = lambda$1260131259$material;
            j3 = primarySurface;
            j4 = jM1783contentColorForek8zF_U;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "165@7582L6,166@7631L32");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty6 = (i & 16) != 0 ? $dirty5 & (-57345) : $dirty5;
                if ((i & 32) != 0) {
                    $dirty6 &= -458753;
                }
                fM1687getTopAppBarElevationD9Ej5fM = f;
                $dirty2 = $dirty6;
                j5 = primarySurface;
                j6 = jM1783contentColorForek8zF_U;
                $dirty3 = -350082398;
                modifier4 = modifier2;
                function9 = function6;
                function10 = lambda$1260131259$material;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    function6 = null;
                }
                if (i4 != 0) {
                    lambda$1260131259$material = ComposableSingletons$AppBarKt.INSTANCE.getLambda$1260131259$material();
                }
                if ((i & 16) == 0) {
                    $dirty = $dirty5;
                } else {
                    primarySurface = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer3, 6));
                    $dirty = $dirty5 & (-57345);
                }
                if ((i & 32) != 0) {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(primarySurface, $composer3, ($dirty >> 12) & 14);
                    $dirty &= -458753;
                }
                if (i7 == 0) {
                    fM1687getTopAppBarElevationD9Ej5fM = f;
                    $dirty2 = $dirty;
                    j5 = primarySurface;
                    j6 = jM1783contentColorForek8zF_U;
                    $dirty3 = -350082398;
                    modifier4 = modifier2;
                    function9 = function6;
                    function10 = lambda$1260131259$material;
                } else {
                    fM1687getTopAppBarElevationD9Ej5fM = AppBarDefaults.INSTANCE.m1687getTopAppBarElevationD9Ej5fM();
                    j5 = primarySurface;
                    j6 = jM1783contentColorForek8zF_U;
                    $dirty2 = $dirty;
                    modifier4 = modifier2;
                    function9 = function6;
                    function10 = lambda$1260131259$material;
                    $dirty3 = -350082398;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart($dirty3, $dirty2, -1, "androidx.compose.material.TopAppBar (AppBar.kt:168)");
            }
            $composer2 = $composer3;
            m1693TopAppBarRx1qByU(function5, ZeroInsets, modifier4, function9, function10, j5, j6, fM1687getTopAppBarElevationD9Ej5fM, $composer2, ($dirty2 & 14) | 48 | (($dirty2 << 3) & 896) | (($dirty2 << 3) & 7168) | (($dirty2 << 3) & 57344) | (($dirty2 << 3) & 458752) | (($dirty2 << 3) & 3670016) | (($dirty2 << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            function7 = function9;
            function8 = function10;
            j3 = j5;
            j4 = j6;
            f2 = fM1687getTopAppBarElevationD9Ej5fM;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.AppBarKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.TopAppBar_xWeB9_s$lambda$0(function2, modifier3, function7, function8, j3, j4, f2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: TopAppBar-afqeVBk, reason: not valid java name */
    public static final void m1694TopAppBarafqeVBk(final WindowInsets windowInsets, Modifier modifier, long j, long j2, float f, PaddingValues contentPadding, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long primarySurface;
        long j3;
        float fM1687getTopAppBarElevationD9Ej5fM;
        PaddingValues paddingValues;
        Composer $composer2;
        final long j4;
        final float f2;
        final Modifier modifier3;
        final long j5;
        final PaddingValues contentPadding2;
        Modifier modifier4;
        float f3;
        PaddingValues contentPadding3;
        long j6;
        long j7;
        Composer $composer3 = $composer.startRestartGroup(684777089);
        ComposerKt.sourceInformation($composer3, "C(TopAppBar)N(windowInsets,modifier,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation:c#ui.unit.Dp,contentPadding,content)222@10051L205:AppBar.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(windowInsets) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
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
                primarySurface = j;
                int i3 = $composer3.changed(primarySurface) ? 256 : 128;
                $dirty |= i3;
            } else {
                primarySurface = j;
            }
            $dirty |= i3;
        } else {
            primarySurface = j;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j3 = j2;
                int i4 = $composer3.changed(j3) ? 2048 : 1024;
                $dirty |= i4;
            } else {
                j3 = j2;
            }
            $dirty |= i4;
        } else {
            j3 = j2;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            fM1687getTopAppBarElevationD9Ej5fM = f;
        } else if (($changed & 24576) == 0) {
            fM1687getTopAppBarElevationD9Ej5fM = f;
            $dirty |= $composer3.changed(fM1687getTopAppBarElevationD9Ej5fM) ? 16384 : 8192;
        } else {
            fM1687getTopAppBarElevationD9Ej5fM = f;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            paddingValues = contentPadding;
        } else if ((196608 & $changed) == 0) {
            paddingValues = contentPadding;
            $dirty |= $composer3.changed(paddingValues) ? 131072 : 65536;
        } else {
            paddingValues = contentPadding;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        }
        if (!$composer3.shouldExecute(($dirty & 599187) != 599186, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            j4 = primarySurface;
            f2 = fM1687getTopAppBarElevationD9Ej5fM;
            modifier3 = modifier2;
            j5 = j3;
            contentPadding2 = paddingValues;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "216@9792L6,217@9841L32");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                modifier4 = modifier2;
                f3 = fM1687getTopAppBarElevationD9Ej5fM;
                contentPadding3 = paddingValues;
                j6 = primarySurface;
                j7 = j3;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    primarySurface = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer3, 6));
                }
                if ((i & 8) != 0) {
                    long jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(primarySurface, $composer3, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                    j3 = jM1783contentColorForek8zF_U;
                }
                if (i5 != 0) {
                    fM1687getTopAppBarElevationD9Ej5fM = AppBarDefaults.INSTANCE.m1687getTopAppBarElevationD9Ej5fM();
                }
                if (i6 == 0) {
                    modifier4 = modifier2;
                    f3 = fM1687getTopAppBarElevationD9Ej5fM;
                    contentPadding3 = paddingValues;
                    j6 = primarySurface;
                    j7 = j3;
                } else {
                    contentPadding3 = AppBarDefaults.INSTANCE.getContentPadding();
                    modifier4 = modifier2;
                    j7 = j3;
                    f3 = fM1687getTopAppBarElevationD9Ej5fM;
                    j6 = primarySurface;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(684777089, $dirty, -1, "androidx.compose.material.TopAppBar (AppBar.kt:221)");
            }
            $composer2 = $composer3;
            m1689AppBarHkEspTQ(j6, j7, f3, contentPadding3, RectangleShapeKt.getRectangleShape(), windowInsets, modifier4, function3, $composer2, (($dirty >> 6) & 14) | 24576 | (($dirty >> 6) & 112) | (($dirty >> 6) & 896) | (($dirty >> 6) & 7168) | (($dirty << 15) & 458752) | (($dirty << 15) & 3670016) | (($dirty << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j4 = j6;
            j5 = j7;
            f2 = f3;
            contentPadding2 = contentPadding3;
            modifier3 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.AppBarKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.TopAppBar_afqeVBk$lambda$0(windowInsets, modifier3, j4, j5, f2, contentPadding2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: TopAppBar-HsRjFd4, reason: not valid java name */
    public static final void m1692TopAppBarHsRjFd4(Modifier modifier, long j, long j2, float f, PaddingValues contentPadding, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long primarySurface;
        long j3;
        float fM1687getTopAppBarElevationD9Ej5fM;
        PaddingValues paddingValues;
        Composer $composer2;
        final long j4;
        final float f2;
        final Modifier modifier3;
        final long j5;
        final PaddingValues contentPadding2;
        Modifier.Companion modifier4;
        Modifier modifier5;
        float f3;
        PaddingValues contentPadding3;
        long j6;
        long j7;
        Composer $composer3 = $composer.startRestartGroup(-2030536439);
        ComposerKt.sourceInformation($composer3, "C(TopAppBar)N(modifier,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation:c#ui.unit.Dp,contentPadding,content)270@12153L203:AppBar.kt#jmzs0o");
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
                primarySurface = j;
                int i3 = $composer3.changed(primarySurface) ? 32 : 16;
                $dirty |= i3;
            } else {
                primarySurface = j;
            }
            $dirty |= i3;
        } else {
            primarySurface = j;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j3 = j2;
                int i4 = $composer3.changed(j3) ? 256 : 128;
                $dirty |= i4;
            } else {
                j3 = j2;
            }
            $dirty |= i4;
        } else {
            j3 = j2;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            fM1687getTopAppBarElevationD9Ej5fM = f;
        } else if (($changed & 3072) == 0) {
            fM1687getTopAppBarElevationD9Ej5fM = f;
            $dirty |= $composer3.changed(fM1687getTopAppBarElevationD9Ej5fM) ? 2048 : 1024;
        } else {
            fM1687getTopAppBarElevationD9Ej5fM = f;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            paddingValues = contentPadding;
        } else if (($changed & 24576) == 0) {
            paddingValues = contentPadding;
            $dirty |= $composer3.changed(paddingValues) ? 16384 : 8192;
        } else {
            paddingValues = contentPadding;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 131072 : 65536;
        }
        if (!$composer3.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            j4 = primarySurface;
            f2 = fM1687getTopAppBarElevationD9Ej5fM;
            modifier3 = modifier2;
            j5 = j3;
            contentPadding2 = paddingValues;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "264@11894L6,265@11943L32");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier5 = modifier2;
                f3 = fM1687getTopAppBarElevationD9Ej5fM;
                contentPadding3 = paddingValues;
                j6 = primarySurface;
                j7 = j3;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 2) != 0) {
                    $dirty &= -113;
                    primarySurface = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer3, 6));
                }
                if ((i & 4) != 0) {
                    long jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(primarySurface, $composer3, ($dirty >> 3) & 14);
                    $dirty &= -897;
                    j3 = jM1783contentColorForek8zF_U;
                }
                if (i5 != 0) {
                    fM1687getTopAppBarElevationD9Ej5fM = AppBarDefaults.INSTANCE.m1687getTopAppBarElevationD9Ej5fM();
                }
                if (i6 == 0) {
                    modifier5 = modifier4;
                    f3 = fM1687getTopAppBarElevationD9Ej5fM;
                    contentPadding3 = paddingValues;
                    j6 = primarySurface;
                    j7 = j3;
                } else {
                    modifier5 = modifier4;
                    contentPadding3 = AppBarDefaults.INSTANCE.getContentPadding();
                    j7 = j3;
                    f3 = fM1687getTopAppBarElevationD9Ej5fM;
                    j6 = primarySurface;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2030536439, $dirty, -1, "androidx.compose.material.TopAppBar (AppBar.kt:269)");
            }
            $composer2 = $composer3;
            m1689AppBarHkEspTQ(j6, j7, f3, contentPadding3, RectangleShapeKt.getRectangleShape(), ZeroInsets, modifier5, function3, $composer2, (($dirty >> 3) & 14) | 221184 | (($dirty >> 3) & 112) | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168) | (($dirty << 18) & 3670016) | (($dirty << 6) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j4 = j6;
            j5 = j7;
            f2 = f3;
            contentPadding2 = contentPadding3;
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.AppBarKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.TopAppBar_HsRjFd4$lambda$0(modifier3, j4, j5, f2, contentPadding2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:127:0x01d3  */
    /* JADX INFO: renamed from: BottomAppBar-DanWW-k, reason: not valid java name */
    public static final void m1690BottomAppBarDanWWk(final WindowInsets windowInsets, Modifier modifier, long j, long j2, Shape cutoutShape, float f, PaddingValues contentPadding, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long primarySurface;
        long jM1783contentColorForek8zF_U;
        Shape cutoutShape2;
        float fM1686getBottomAppBarElevationD9Ej5fM;
        Composer $composer2;
        final Modifier modifier3;
        final long j3;
        final long j4;
        final Shape cutoutShape3;
        final float f2;
        final PaddingValues contentPadding2;
        Modifier modifier4;
        Shape cutoutShape4;
        float f3;
        int i2;
        PaddingValues contentPadding3;
        long j5;
        long j6;
        Shape shape;
        Composer $composer3 = $composer.startRestartGroup(-1136595494);
        ComposerKt.sourceInformation($composer3, "C(BottomAppBar)N(windowInsets,modifier,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,cutoutShape,elevation:c#ui.unit.Dp,contentPadding,content)337@15398L7,344@15612L175:AppBar.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(windowInsets) ? 4 : 2;
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
                primarySurface = j;
                int i4 = $composer3.changed(primarySurface) ? 256 : 128;
                $dirty |= i4;
            } else {
                primarySurface = j;
            }
            $dirty |= i4;
        } else {
            primarySurface = j;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                jM1783contentColorForek8zF_U = j2;
                int i5 = $composer3.changed(jM1783contentColorForek8zF_U) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                jM1783contentColorForek8zF_U = j2;
            }
            $dirty |= i5;
        } else {
            jM1783contentColorForek8zF_U = j2;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            cutoutShape2 = cutoutShape;
        } else if (($changed & 24576) == 0) {
            cutoutShape2 = cutoutShape;
            $dirty |= $composer3.changed(cutoutShape2) ? 16384 : 8192;
        } else {
            cutoutShape2 = cutoutShape;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            fM1686getBottomAppBarElevationD9Ej5fM = f;
        } else if ((196608 & $changed) == 0) {
            fM1686getBottomAppBarElevationD9Ej5fM = f;
            $dirty |= $composer3.changed(fM1686getBottomAppBarElevationD9Ej5fM) ? 131072 : 65536;
        } else {
            fM1686getBottomAppBarElevationD9Ej5fM = f;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changed(contentPadding) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 8388608 : 4194304;
        }
        boolean z = false;
        if (!$composer3.shouldExecute(($dirty & 4793491) != 4793490, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = primarySurface;
            j4 = jM1783contentColorForek8zF_U;
            cutoutShape3 = cutoutShape2;
            f2 = fM1686getBottomAppBarElevationD9Ej5fM;
            contentPadding2 = contentPadding;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "330@15067L6,331@15116L32");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                modifier4 = modifier2;
                cutoutShape4 = cutoutShape2;
                f3 = fM1686getBottomAppBarElevationD9Ej5fM;
                i2 = -1136595494;
                contentPadding3 = contentPadding;
                j5 = primarySurface;
                j6 = jM1783contentColorForek8zF_U;
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    primarySurface = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer3, 6));
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(primarySurface, $composer3, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                }
                if (i6 != 0) {
                    cutoutShape2 = null;
                }
                if (i7 != 0) {
                    fM1686getBottomAppBarElevationD9Ej5fM = AppBarDefaults.INSTANCE.m1686getBottomAppBarElevationD9Ej5fM();
                }
                if (i8 == 0) {
                    modifier4 = modifier2;
                    cutoutShape4 = cutoutShape2;
                    f3 = fM1686getBottomAppBarElevationD9Ej5fM;
                    i2 = -1136595494;
                    contentPadding3 = contentPadding;
                    j5 = primarySurface;
                    j6 = jM1783contentColorForek8zF_U;
                } else {
                    modifier4 = modifier2;
                    f3 = fM1686getBottomAppBarElevationD9Ej5fM;
                    i2 = -1136595494;
                    contentPadding3 = AppBarDefaults.INSTANCE.getContentPadding();
                    j6 = jM1783contentColorForek8zF_U;
                    cutoutShape4 = cutoutShape2;
                    j5 = primarySurface;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material.BottomAppBar (AppBar.kt:336)");
            }
            ProvidableCompositionLocal<FabPlacement> localFabPlacement = ScaffoldKt.getLocalFabPlacement();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localFabPlacement);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            FabPlacement fabPlacement = (FabPlacement) objConsume;
            if (cutoutShape4 == null) {
                shape = RectangleShapeKt.getRectangleShape();
            } else {
                if (fabPlacement != null && fabPlacement.getIsDocked()) {
                    z = true;
                }
                if (z) {
                    shape = new BottomAppBarCutoutShape(cutoutShape4, fabPlacement);
                } else {
                    shape = RectangleShapeKt.getRectangleShape();
                }
            }
            $composer2 = $composer3;
            m1689AppBarHkEspTQ(j5, j6, f3, contentPadding3, shape, windowInsets, modifier4, function3, $composer2, (($dirty >> 6) & 14) | (($dirty >> 6) & 112) | (($dirty >> 9) & 896) | (($dirty >> 9) & 7168) | (($dirty << 15) & 458752) | (($dirty << 15) & 3670016) | (29360128 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            cutoutShape3 = cutoutShape4;
            j3 = j5;
            j4 = j6;
            f2 = f3;
            contentPadding2 = contentPadding3;
            modifier3 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.AppBarKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.BottomAppBar_DanWW_k$lambda$0(windowInsets, modifier3, j3, j4, cutoutShape3, f2, contentPadding2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:121:0x01c3  */
    /* JADX INFO: renamed from: BottomAppBar-Y1yfwus, reason: not valid java name */
    public static final void m1691BottomAppBarY1yfwus(Modifier modifier, long j, long j2, Shape cutoutShape, float f, PaddingValues contentPadding, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long primarySurface;
        long jM1783contentColorForek8zF_U;
        Shape cutoutShape2;
        float fM1686getBottomAppBarElevationD9Ej5fM;
        PaddingValues paddingValues;
        Composer $composer2;
        final Modifier modifier3;
        final long j3;
        final long j4;
        final Shape cutoutShape3;
        final float f2;
        final PaddingValues contentPadding2;
        int $dirty;
        Modifier modifier4;
        Shape cutoutShape4;
        PaddingValues contentPadding3;
        int i2;
        long j5;
        float f3;
        long j6;
        Shape shape;
        Composer $composer3 = $composer.startRestartGroup(-2058075642);
        ComposerKt.sourceInformation($composer3, "C(BottomAppBar)N(modifier,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,cutoutShape,elevation:c#ui.unit.Dp,contentPadding,content)406@18576L7,413@18790L173:AppBar.kt#jmzs0o");
        int $dirty2 = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
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
                primarySurface = j;
                int i4 = $composer3.changed(primarySurface) ? 32 : 16;
                $dirty2 |= i4;
            } else {
                primarySurface = j;
            }
            $dirty2 |= i4;
        } else {
            primarySurface = j;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                jM1783contentColorForek8zF_U = j2;
                int i5 = $composer3.changed(jM1783contentColorForek8zF_U) ? 256 : 128;
                $dirty2 |= i5;
            } else {
                jM1783contentColorForek8zF_U = j2;
            }
            $dirty2 |= i5;
        } else {
            jM1783contentColorForek8zF_U = j2;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty2 |= 3072;
            cutoutShape2 = cutoutShape;
        } else if (($changed & 3072) == 0) {
            cutoutShape2 = cutoutShape;
            $dirty2 |= $composer3.changed(cutoutShape2) ? 2048 : 1024;
        } else {
            cutoutShape2 = cutoutShape;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty2 |= 24576;
            fM1686getBottomAppBarElevationD9Ej5fM = f;
        } else if (($changed & 24576) == 0) {
            fM1686getBottomAppBarElevationD9Ej5fM = f;
            $dirty2 |= $composer3.changed(fM1686getBottomAppBarElevationD9Ej5fM) ? 16384 : 8192;
        } else {
            fM1686getBottomAppBarElevationD9Ej5fM = f;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            paddingValues = contentPadding;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            paddingValues = contentPadding;
            $dirty2 |= $composer3.changed(paddingValues) ? 131072 : 65536;
        } else {
            paddingValues = contentPadding;
        }
        if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        }
        int $dirty3 = $dirty2;
        boolean z = false;
        if (!$composer3.shouldExecute(($dirty2 & 599187) != 599186, $dirty3 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = primarySurface;
            j4 = jM1783contentColorForek8zF_U;
            cutoutShape3 = cutoutShape2;
            f2 = fM1686getBottomAppBarElevationD9Ej5fM;
            contentPadding2 = paddingValues;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "399@18245L6,400@18294L32");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                $dirty = (i & 2) != 0 ? $dirty3 & (-113) : $dirty3;
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier4 = modifier2;
                cutoutShape4 = cutoutShape2;
                contentPadding3 = paddingValues;
                i2 = -2058075642;
                j5 = primarySurface;
                f3 = fM1686getBottomAppBarElevationD9Ej5fM;
                j6 = jM1783contentColorForek8zF_U;
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 2) == 0) {
                    $dirty = $dirty3;
                } else {
                    primarySurface = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer3, 6));
                    $dirty = $dirty3 & (-113);
                }
                if ((i & 4) != 0) {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(primarySurface, $composer3, ($dirty >> 3) & 14);
                    $dirty &= -897;
                }
                if (i6 != 0) {
                    cutoutShape2 = null;
                }
                if (i7 != 0) {
                    fM1686getBottomAppBarElevationD9Ej5fM = AppBarDefaults.INSTANCE.m1686getBottomAppBarElevationD9Ej5fM();
                }
                if (i8 == 0) {
                    modifier4 = modifier2;
                    cutoutShape4 = cutoutShape2;
                    contentPadding3 = paddingValues;
                    i2 = -2058075642;
                    j5 = primarySurface;
                    f3 = fM1686getBottomAppBarElevationD9Ej5fM;
                    j6 = jM1783contentColorForek8zF_U;
                } else {
                    contentPadding3 = AppBarDefaults.INSTANCE.getContentPadding();
                    modifier4 = modifier2;
                    cutoutShape4 = cutoutShape2;
                    f3 = fM1686getBottomAppBarElevationD9Ej5fM;
                    i2 = -2058075642;
                    j5 = primarySurface;
                    j6 = jM1783contentColorForek8zF_U;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material.BottomAppBar (AppBar.kt:405)");
            }
            ProvidableCompositionLocal<FabPlacement> localFabPlacement = ScaffoldKt.getLocalFabPlacement();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localFabPlacement);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            FabPlacement fabPlacement = (FabPlacement) objConsume;
            if (cutoutShape4 == null) {
                shape = RectangleShapeKt.getRectangleShape();
            } else {
                if (fabPlacement != null && fabPlacement.getIsDocked()) {
                    z = true;
                }
                if (z) {
                    shape = new BottomAppBarCutoutShape(cutoutShape4, fabPlacement);
                } else {
                    shape = RectangleShapeKt.getRectangleShape();
                }
            }
            $composer2 = $composer3;
            m1689AppBarHkEspTQ(j5, j6, f3, contentPadding3, shape, ZeroInsets, modifier4, function3, $composer2, (($dirty >> 3) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (($dirty >> 3) & 112) | (($dirty >> 6) & 896) | (($dirty >> 6) & 7168) | (($dirty << 18) & 3670016) | (($dirty << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            cutoutShape3 = cutoutShape4;
            j3 = j5;
            j4 = j6;
            f2 = f3;
            contentPadding2 = contentPadding3;
            modifier3 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.AppBarKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.BottomAppBar_Y1yfwus$lambda$0(modifier3, j3, j4, cutoutShape3, f2, contentPadding2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float square(float x) {
        return x * x;
    }

    public static final float calculateCutoutCircleYIntercept(float cutoutRadius, float verticalOffset) {
        float x$iv = cutoutRadius * cutoutRadius;
        float x$iv2 = verticalOffset * verticalOffset;
        return -((float) Math.sqrt(x$iv - x$iv2));
    }

    public static final Pair<Float, Float> calculateRoundedEdgeIntercept(float controlPointX, float verticalOffset, float radius) {
        Float fValueOf;
        Float fValueOf2;
        Pair pair;
        Float fValueOf3;
        Float fValueOf4;
        float x$iv = verticalOffset * verticalOffset;
        float x$iv2 = radius * radius;
        float f = x$iv * x$iv2;
        float x$iv3 = controlPointX * controlPointX;
        float x$iv4 = verticalOffset * verticalOffset;
        float f2 = x$iv3 + x$iv4;
        float x$iv5 = radius * radius;
        float discriminant = f * (f2 - x$iv5);
        float x$iv6 = controlPointX * controlPointX;
        float x$iv7 = verticalOffset * verticalOffset;
        float divisor = x$iv6 + x$iv7;
        float x$iv8 = radius * radius;
        float bCoefficient = x$iv8 * controlPointX;
        float xSolutionA = (bCoefficient - ((float) Math.sqrt(discriminant))) / divisor;
        float xSolutionB = (((float) Math.sqrt(discriminant)) + bCoefficient) / divisor;
        float x$iv9 = radius * radius;
        float x$iv10 = xSolutionA * xSolutionA;
        float ySolutionA = (float) Math.sqrt(x$iv9 - x$iv10);
        float x$iv11 = radius * radius;
        float x$iv12 = xSolutionB * xSolutionB;
        float ySolutionB = (float) Math.sqrt(x$iv11 - x$iv12);
        if (verticalOffset > 0.0f) {
            if (ySolutionA > ySolutionB) {
                fValueOf3 = Float.valueOf(xSolutionA);
                fValueOf4 = Float.valueOf(ySolutionA);
            } else {
                fValueOf3 = Float.valueOf(xSolutionB);
                fValueOf4 = Float.valueOf(ySolutionB);
            }
            pair = TuplesKt.to(fValueOf3, fValueOf4);
        } else {
            if (ySolutionA < ySolutionB) {
                fValueOf = Float.valueOf(xSolutionA);
                fValueOf2 = Float.valueOf(ySolutionA);
            } else {
                fValueOf = Float.valueOf(xSolutionB);
                fValueOf2 = Float.valueOf(ySolutionB);
            }
            pair = TuplesKt.to(fValueOf, fValueOf2);
        }
        float xSolution = ((Number) pair.component1()).floatValue();
        float ySolution = ((Number) pair.component2()).floatValue();
        float adjustedYSolution = xSolution < controlPointX ? -ySolution : ySolution;
        return TuplesKt.to(Float.valueOf(xSolution), Float.valueOf(adjustedYSolution));
    }

    /* JADX INFO: renamed from: AppBar-HkEspTQ, reason: not valid java name */
    private static final void m1689AppBarHkEspTQ(final long j, final long j2, final float f, final PaddingValues contentPadding, final Shape shape, final WindowInsets windowInsets, Modifier modifier, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        long j3;
        float f2;
        Shape shape2;
        Modifier modifier2;
        Composer $composer2;
        final Modifier modifier3;
        Modifier modifier4;
        Composer $composer3 = $composer.startRestartGroup(1222317265);
        ComposerKt.sourceInformation($composer3, "C(AppBar)N(backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation:c#ui.unit.Dp,contentPadding,shape,windowInsets,modifier,content)706@31596L472,700@31428L640:AppBar.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            j3 = j;
            $dirty |= $composer3.changed(j3) ? 4 : 2;
        } else {
            j3 = j;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(j2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            f2 = f;
            $dirty |= $composer3.changed(f2) ? 256 : 128;
        } else {
            f2 = f;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(contentPadding) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            shape2 = shape;
            $dirty |= $composer3.changed(shape2) ? 16384 : 8192;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changed(windowInsets) ? 131072 : 65536;
        }
        int i2 = i & 64;
        if (i2 != 0) {
            $dirty |= 1572864;
            modifier2 = modifier;
        } else if (($changed & 1572864) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 1048576 : 524288;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 8388608 : 4194304;
        }
        if (!$composer3.shouldExecute(($dirty & 4793491) != 4793490, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1222317265, $dirty, -1, "androidx.compose.material.AppBar (AppBar.kt:699)");
            }
            $composer2 = $composer3;
            SurfaceKt.m1954SurfaceFjzlyU(modifier4, shape2, j3, j2, null, f2, ComposableLambdaKt.rememberComposableLambda(-1628734195, true, new Function2() { // from class: androidx.compose.material.AppBarKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.AppBar_HkEspTQ$lambda$0(windowInsets, contentPadding, function3, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer2, (($dirty >> 18) & 14) | 1572864 | (($dirty >> 9) & 112) | (($dirty << 6) & 896) | (($dirty << 6) & 7168) | (($dirty << 9) & 458752), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.AppBarKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.AppBar_HkEspTQ$lambda$1(j, j2, f, contentPadding, shape, windowInsets, modifier3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit AppBar_HkEspTQ$lambda$0(final WindowInsets $windowInsets, final PaddingValues $contentPadding, final Function3 $content, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C707@31671L6,707@31679L383,707@31606L456:AppBar.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1628734195, $changed, -1, "androidx.compose.material.AppBar.<anonymous> (AppBar.kt:707)");
            }
            CompositionLocalKt.CompositionLocalProvider(ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(ContentAlpha.INSTANCE.getMedium($composer, 6))), ComposableLambdaKt.rememberComposableLambda(597057613, true, new Function2() { // from class: androidx.compose.material.AppBarKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.AppBar_HkEspTQ$lambda$0$0($windowInsets, $contentPadding, $content, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:27:0x015f  */
    public static final Unit AppBar_HkEspTQ$lambda$0$0(WindowInsets $windowInsets, PaddingValues $contentPadding, Function3 $content, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        int compositeKeyHash$iv$iv;
        ComposerKt.sourceInformation($composer, "C708@31693L359:AppBar.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(597057613, $changed, -1, "androidx.compose.material.AppBar.<anonymous>.<anonymous> (AppBar.kt:708)");
            }
            Modifier modifier$iv = SizeKt.m851height3ABfNKs(PaddingKt.padding(WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), $windowInsets), $contentPadding), AppBarHeight);
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            int $changed$iv$iv = (432 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
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
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                compositeKeyHash$iv$iv = compositeKeyHash$iv$iv2;
            } else {
                compositeKeyHash$iv$iv = compositeKeyHash$iv$iv2;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                $content.invoke(RowScopeInstance.INSTANCE, $composer, Integer.valueOf(((432 >> 6) & 112) | 6));
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
            ComposerKt.sourceInformationMarkerStart($composer, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            $content.invoke(RowScopeInstance.INSTANCE, $composer, Integer.valueOf(((432 >> 6) & 112) | 6));
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

    static {
        Modifier.Companion companion = Modifier.INSTANCE;
        float arg0$iv = Dp.m7905constructorimpl(16);
        float other$iv = AppBarHorizontalPadding;
        TitleInsetWithoutIcon = SizeKt.m870width3ABfNKs(companion, Dp.m7905constructorimpl(arg0$iv - other$iv));
        Modifier modifierFillMaxHeight$default = SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null);
        float arg0$iv2 = Dp.m7905constructorimpl(72);
        float other$iv2 = AppBarHorizontalPadding;
        TitleIconModifier = SizeKt.m870width3ABfNKs(modifierFillMaxHeight$default, Dp.m7905constructorimpl(arg0$iv2 - other$iv2));
        BottomAppBarCutoutOffset = Dp.m7905constructorimpl(8);
        BottomAppBarRoundedEdgeRadius = Dp.m7905constructorimpl(4);
        ZeroInsets = WindowInsetsKt.m891WindowInsetsa9UjIt4$default(Dp.m7905constructorimpl(0), 0.0f, 0.0f, 0.0f, 14, null);
    }
}
