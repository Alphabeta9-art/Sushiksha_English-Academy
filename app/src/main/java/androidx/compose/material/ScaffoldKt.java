package androidx.compose.material;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: Scaffold.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a!\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a¤\u0002\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00012\u0013\b\u0002\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u00102\u0013\b\u0002\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u00102\u0019\b\u0002\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0013¢\u0006\u0002\b\u00102\u0013\b\u0002\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182 \b\u0002\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\b\u0018\u00010\u0013¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00182\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"2\b\b\u0002\u0010&\u001a\u00020\"2\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\b0\u0013¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0004\b)\u0010*\u001a\u009c\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00012\u0013\b\u0002\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u00102\u0013\b\u0002\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u00102\u0019\b\u0002\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0013¢\u0006\u0002\b\u00102\u0013\b\u0002\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182 \b\u0002\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\b\u0018\u00010\u0013¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00182\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"2\b\b\u0002\u0010&\u001a\u00020\"2\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\b0\u0013¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0004\b+\u0010,\u001a¥\u0001\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020\u00182\u0006\u0010/\u001a\u00020\u00162\u0016\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u0010¢\u0006\u0002\b02\u001c\u0010'\u001a\u0018\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\b0\u0013¢\u0006\u0002\b\u0010¢\u0006\u0002\b02\u0016\u00101\u001a\u0012\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u0010¢\u0006\u0002\b02\u0016\u00102\u001a\u0012\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u0010¢\u0006\u0002\b02\u0006\u0010\t\u001a\u00020\n2\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\u0002\b\u0010¢\u0006\u0002\b0H\u0003¢\u0006\u0004\b3\u00104\"\u001c\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010706X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u00109\"\u0010\u0010:\u001a\u00020 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010;¨\u0006<"}, d2 = {"rememberScaffoldState", "Landroidx/compose/material/ScaffoldState;", "drawerState", "Landroidx/compose/material/DrawerState;", "snackbarHostState", "Landroidx/compose/material/SnackbarHostState;", "(Landroidx/compose/material/DrawerState;Landroidx/compose/material/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/ScaffoldState;", "Scaffold", "", "contentWindowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "modifier", "Landroidx/compose/ui/Modifier;", "scaffoldState", "topBar", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "bottomBar", "snackbarHost", "Lkotlin/Function1;", "floatingActionButton", "floatingActionButtonPosition", "Landroidx/compose/material/FabPosition;", "isFloatingActionButtonDocked", "", "drawerContent", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "drawerGesturesEnabled", "drawerShape", "Landroidx/compose/ui/graphics/Shape;", "drawerElevation", "Landroidx/compose/ui/unit/Dp;", "drawerBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "drawerContentColor", "drawerScrimColor", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "contentColor", UriUtil.LOCAL_CONTENT_SCHEME, "Landroidx/compose/foundation/layout/PaddingValues;", "Scaffold-u4IkXBM", "(Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/Modifier;Landroidx/compose/material/ScaffoldState;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;IZLkotlin/jvm/functions/Function3;ZLandroidx/compose/ui/graphics/Shape;FJJJJJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "Scaffold-27mzLpw", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material/ScaffoldState;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;IZLkotlin/jvm/functions/Function3;ZLandroidx/compose/ui/graphics/Shape;FJJJJJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "ScaffoldLayout", "isFabDocked", "fabPosition", "Landroidx/compose/ui/UiComposable;", "snackbar", "fab", "ScaffoldLayout-i1QSOvI", "(ZILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "LocalFabPlacement", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material/FabPlacement;", "getLocalFabPlacement", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "FabSpacing", "F", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ScaffoldKt {
    private static final ProvidableCompositionLocal<FabPlacement> LocalFabPlacement = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.material.ScaffoldKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return ScaffoldKt.LocalFabPlacement$lambda$0();
        }
    });
    private static final float FabSpacing = Dp.m7905constructorimpl(16);

    static final Unit ScaffoldLayout_i1QSOvI$lambda$2(boolean z, int i, Function2 function2, Function3 function3, Function2 function4, Function2 function5, WindowInsets windowInsets, Function2 function6, int i2, Composer composer, int i3) {
        m1915ScaffoldLayouti1QSOvI(z, i, function2, function3, function4, function5, windowInsets, function6, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    static final Unit Scaffold_27mzLpw$lambda$0(Modifier modifier, ScaffoldState scaffoldState, Function2 function2, Function2 function3, Function3 function4, Function2 function5, int i, boolean z, Function3 function6, boolean z2, Shape shape, float f, long j, long j2, long j3, long j4, long j5, Function3 function7, int i2, int i3, int i4, Composer composer, int i5) {
        m1913Scaffold27mzLpw(modifier, scaffoldState, function2, function3, function4, function5, i, z, function6, z2, shape, f, j, j2, j3, j4, j5, function7, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit Scaffold_u4IkXBM$lambda$3(WindowInsets windowInsets, Modifier modifier, ScaffoldState scaffoldState, Function2 function2, Function2 function3, Function3 function4, Function2 function5, int i, boolean z, Function3 function6, boolean z2, Shape shape, float f, long j, long j2, long j3, long j4, long j5, Function3 function7, int i2, int i3, int i4, Composer composer, int i5) {
        m1914Scaffoldu4IkXBM(windowInsets, modifier, scaffoldState, function2, function3, function4, function5, i, z, function6, z2, shape, f, j, j2, j3, j4, j5, function7, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    public static final ScaffoldState rememberScaffoldState(DrawerState drawerState, SnackbarHostState snackbarHostState, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1569641925, "C(rememberScaffoldState)N(drawerState,snackbarHostState)71@2876L39,72@2960L32,73@3013L58:Scaffold.kt#jmzs0o");
        if ((i & 1) != 0) {
            drawerState = DrawerKt.rememberDrawerState(DrawerValue.Closed, null, $composer, 6, 2);
        }
        if ((i & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, 892373605, "CC(remember):Scaffold.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new SnackbarHostState();
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            snackbarHostState = (SnackbarHostState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1569641925, $changed, -1, "androidx.compose.material.rememberScaffoldState (Scaffold.kt:73)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 892375327, "CC(remember):Scaffold.kt#9igjgp");
        Object it$iv2 = $composer.rememberedValue();
        if (it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new ScaffoldState(drawerState, snackbarHostState);
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ScaffoldState scaffoldState = (ScaffoldState) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return scaffoldState;
    }

    /* JADX INFO: renamed from: Scaffold-u4IkXBM, reason: not valid java name */
    public static final void m1914Scaffoldu4IkXBM(final WindowInsets contentWindowInsets, Modifier modifier, ScaffoldState scaffoldState, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, int i, final boolean isFloatingActionButtonDocked, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function6, boolean drawerGesturesEnabled, Shape drawerShape, float f, long j, long j2, long j3, long j4, long j5, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function7, Composer $composer, final int $changed, final int $changed1, final int i2) {
        Modifier modifier2;
        ScaffoldState scaffoldState2;
        Function2<? super Composer, ? super Integer, Unit> lambda$866784315$material;
        Function2<? super Composer, ? super Integer, Unit> lambda$1714259275$material;
        Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function3M1792getLambda$1836397928$material;
        Function2<? super Composer, ? super Integer, Unit> function2M1790getLambda$1406416085$material;
        int iM1834getEnd5ygKITE;
        int i3;
        int $dirty1;
        int i4;
        int $dirty2;
        int $dirty3;
        Composer $composer2;
        final long j6;
        final long j7;
        final long j8;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final int i5;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function10;
        final Modifier modifier3;
        final ScaffoldState scaffoldState3;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final boolean isFloatingActionButtonDocked2;
        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function12;
        final boolean drawerGesturesEnabled2;
        final Shape drawerShape2;
        final float f2;
        final long j9;
        final long j10;
        boolean isFloatingActionButtonDocked3;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function13;
        boolean drawerGesturesEnabled3;
        CornerBasedShape drawerShape3;
        int $dirty4;
        float fM1803getElevationD9Ej5fM;
        long jM1769getSurface0d7_KjU;
        long jM1783contentColorForek8zF_U;
        int i6;
        long scrimColor;
        long jM1758getBackground0d7_KjU;
        long jM1783contentColorForek8zF_U2;
        long j11;
        int $dirty5;
        boolean drawerGesturesEnabled4;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function14;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function15;
        boolean drawerGesturesEnabled5;
        int $dirty6;
        Composer $composer3 = $composer.startRestartGroup(50073903);
        ComposerKt.sourceInformation($composer3, "C(Scaffold)N(contentWindowInsets,modifier,scaffoldState,topBar,bottomBar,snackbarHost,floatingActionButton,floatingActionButtonPosition:c#material.FabPosition,isFloatingActionButtonDocked,drawerContent,drawerGesturesEnabled,drawerShape,drawerElevation:c#ui.unit.Dp,drawerBackgroundColor:c#ui.graphics.Color,drawerContentColor:c#ui.graphics.Color,drawerScrimColor:c#ui.graphics.Color,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,content)197@9490L74,199@9601L1024:Scaffold.kt#jmzs0o");
        int $dirty = $changed;
        int $dirty7 = $changed1;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(contentWindowInsets) ? 4 : 2;
        }
        int i7 = i2 & 2;
        if (i7 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i2 & 4) == 0) {
                scaffoldState2 = scaffoldState;
                int i8 = $composer3.changed(scaffoldState2) ? 256 : 128;
                $dirty |= i8;
            } else {
                scaffoldState2 = scaffoldState;
            }
            $dirty |= i8;
        } else {
            scaffoldState2 = scaffoldState;
        }
        int i9 = i2 & 8;
        int i10 = 1024;
        if (i9 != 0) {
            $dirty |= 3072;
            lambda$866784315$material = function2;
        } else if (($changed & 3072) == 0) {
            lambda$866784315$material = function2;
            $dirty |= $composer3.changedInstance(lambda$866784315$material) ? 2048 : 1024;
        } else {
            lambda$866784315$material = function2;
        }
        int i11 = i2 & 16;
        if (i11 != 0) {
            $dirty |= 24576;
            lambda$1714259275$material = function3;
        } else if (($changed & 24576) == 0) {
            lambda$1714259275$material = function3;
            $dirty |= $composer3.changedInstance(lambda$1714259275$material) ? 16384 : 8192;
        } else {
            lambda$1714259275$material = function3;
        }
        int i12 = i2 & 32;
        if (i12 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function3M1792getLambda$1836397928$material = function4;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function3M1792getLambda$1836397928$material = function4;
            $dirty |= $composer3.changedInstance(function3M1792getLambda$1836397928$material) ? 131072 : 65536;
        } else {
            function3M1792getLambda$1836397928$material = function4;
        }
        int i13 = i2 & 64;
        if (i13 != 0) {
            $dirty |= 1572864;
            function2M1790getLambda$1406416085$material = function5;
        } else if (($changed & 1572864) == 0) {
            function2M1790getLambda$1406416085$material = function5;
            $dirty |= $composer3.changedInstance(function2M1790getLambda$1406416085$material) ? 1048576 : 524288;
        } else {
            function2M1790getLambda$1406416085$material = function5;
        }
        int i14 = i2 & 128;
        if (i14 != 0) {
            $dirty |= 12582912;
            iM1834getEnd5ygKITE = i;
        } else if (($changed & 12582912) == 0) {
            iM1834getEnd5ygKITE = i;
            $dirty |= $composer3.changed(iM1834getEnd5ygKITE) ? 8388608 : 4194304;
        } else {
            iM1834getEnd5ygKITE = i;
        }
        int i15 = i2 & 256;
        if (i15 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(isFloatingActionButtonDocked) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i16 = i2 & 512;
        if (i16 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changedInstance(function6) ? 536870912 : 268435456;
        }
        int i17 = i2 & 1024;
        if (i17 != 0) {
            $dirty7 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty7 |= $composer3.changed(drawerGesturesEnabled) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty7 |= ((i2 & 2048) == 0 && $composer3.changed(drawerShape)) ? 32 : 16;
        }
        int i18 = i2 & 4096;
        if (i18 != 0) {
            $dirty7 |= 384;
            i3 = i18;
        } else {
            i3 = i18;
            if (($changed1 & 384) == 0) {
                $dirty7 |= $composer3.changed(f) ? 256 : 128;
            }
        }
        if (($changed1 & 3072) == 0) {
            if ((i2 & 8192) == 0) {
                $dirty6 = $dirty7;
                i4 = i7;
                if ($composer3.changed(j)) {
                    i10 = 2048;
                }
            } else {
                $dirty6 = $dirty7;
                i4 = i7;
            }
            $dirty1 = $dirty6 | i10;
        } else {
            $dirty1 = $dirty7;
            i4 = i7;
        }
        int $dirty8 = $dirty1;
        if (($changed1 & 24576) == 0) {
            $dirty2 = $dirty8 | (((i2 & 16384) == 0 && $composer3.changed(j2)) ? 16384 : 8192);
        } else {
            $dirty2 = $dirty8;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= ((i2 & 32768) == 0 && $composer3.changed(j3)) ? 131072 : 65536;
        }
        if (($changed1 & 1572864) == 0) {
            $dirty2 |= ((i2 & 65536) == 0 && $composer3.changed(j4)) ? 1048576 : 524288;
        }
        if (($changed1 & 12582912) == 0) {
            $dirty2 |= ((i2 & 131072) == 0 && $composer3.changed(j5)) ? 8388608 : 4194304;
        }
        if (($changed1 & 100663296) == 0) {
            $dirty3 = $dirty2 | ($composer3.changedInstance(function7) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432);
        } else {
            $dirty3 = $dirty2;
        }
        if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty3 & 38347923) == 38347922) ? false : true, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "179@8523L23,188@9033L6,190@9148L6,191@9196L38,192@9281L10,193@9336L6,194@9381L32");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i2 & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i2 & 2048) != 0) {
                    $dirty3 &= -113;
                }
                $dirty5 = $dirty3;
                if ((i2 & 8192) != 0) {
                    $dirty5 &= -7169;
                }
                if ((i2 & 16384) != 0) {
                    $dirty5 &= -57345;
                }
                if ((32768 & i2) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i2 & 65536) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i2 & 131072) != 0) {
                    $dirty5 &= -29360129;
                }
                function14 = function6;
                drawerGesturesEnabled4 = drawerGesturesEnabled;
                drawerShape3 = drawerShape;
                fM1803getElevationD9Ej5fM = f;
                jM1783contentColorForek8zF_U = j2;
                scrimColor = j3;
                j11 = j4;
                jM1783contentColorForek8zF_U2 = j5;
                $dirty = $dirty;
                jM1769getSurface0d7_KjU = j;
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 4) != 0) {
                    $dirty &= -897;
                    scaffoldState2 = rememberScaffoldState(null, null, $composer3, 0, 3);
                }
                if (i9 != 0) {
                    lambda$866784315$material = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$866784315$material();
                }
                if (i11 != 0) {
                    lambda$1714259275$material = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1714259275$material();
                }
                if (i12 != 0) {
                    function3M1792getLambda$1836397928$material = ComposableSingletons$ScaffoldKt.INSTANCE.m1792getLambda$1836397928$material();
                }
                if (i13 != 0) {
                    function2M1790getLambda$1406416085$material = ComposableSingletons$ScaffoldKt.INSTANCE.m1790getLambda$1406416085$material();
                }
                if (i14 != 0) {
                    iM1834getEnd5ygKITE = FabPosition.INSTANCE.m1834getEnd5ygKITE();
                }
                if (i15 == 0) {
                    isFloatingActionButtonDocked3 = isFloatingActionButtonDocked;
                } else {
                    isFloatingActionButtonDocked3 = false;
                }
                if (i16 == 0) {
                    function13 = function6;
                } else {
                    function13 = null;
                }
                if (i17 == 0) {
                    drawerGesturesEnabled3 = drawerGesturesEnabled;
                } else {
                    drawerGesturesEnabled3 = true;
                }
                if ((i2 & 2048) == 0) {
                    drawerShape3 = drawerShape;
                    $dirty4 = $dirty3;
                } else {
                    drawerShape3 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                    $dirty4 = $dirty3 & (-113);
                }
                if (i3 == 0) {
                    fM1803getElevationD9Ej5fM = f;
                } else {
                    fM1803getElevationD9Ej5fM = DrawerDefaults.INSTANCE.m1803getElevationD9Ej5fM();
                }
                if ((i2 & 8192) != 0) {
                    jM1769getSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer3, 6).m1769getSurface0d7_KjU();
                    $dirty4 &= -7169;
                } else {
                    jM1769getSurface0d7_KjU = j;
                }
                if ((i2 & 16384) == 0) {
                    jM1783contentColorForek8zF_U = j2;
                } else {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1769getSurface0d7_KjU, $composer3, ($dirty4 >> 9) & 14);
                    $dirty4 &= -57345;
                }
                if ((32768 & i2) != 0) {
                    i6 = 6;
                    scrimColor = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                    $dirty4 &= -458753;
                } else {
                    i6 = 6;
                    scrimColor = j3;
                }
                if ((i2 & 65536) == 0) {
                    jM1758getBackground0d7_KjU = j4;
                } else {
                    $dirty4 &= -3670017;
                    jM1758getBackground0d7_KjU = MaterialTheme.INSTANCE.getColors($composer3, i6).m1758getBackground0d7_KjU();
                }
                if ((i2 & 131072) == 0) {
                    isFloatingActionButtonDocked = isFloatingActionButtonDocked3;
                    jM1783contentColorForek8zF_U2 = j5;
                    j11 = jM1758getBackground0d7_KjU;
                    $dirty5 = $dirty4;
                    drawerGesturesEnabled4 = drawerGesturesEnabled3;
                    function14 = function13;
                } else {
                    isFloatingActionButtonDocked = isFloatingActionButtonDocked3;
                    jM1783contentColorForek8zF_U2 = ColorsKt.m1783contentColorForek8zF_U(jM1758getBackground0d7_KjU, $composer3, ($dirty4 >> 18) & 14);
                    $dirty5 = $dirty4 & (-29360129);
                    j11 = jM1758getBackground0d7_KjU;
                    drawerGesturesEnabled4 = drawerGesturesEnabled3;
                    function14 = function13;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(50073903, $dirty, $dirty5, "androidx.compose.material.Scaffold (Scaffold.kt:196)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 876415481, "CC(remember):Scaffold.kt#9igjgp");
            boolean invalid$iv = ($dirty & 14) == 4;
            int $dirty9 = $dirty5;
            Object value$iv = $composer3.rememberedValue();
            if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new MutableWindowInsets(contentWindowInsets);
                $composer3.updateRememberedValue(value$iv);
            }
            final MutableWindowInsets safeInsets = (MutableWindowInsets) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Function2<? super Composer, ? super Integer, Unit> function16 = lambda$866784315$material;
            final int i19 = iM1834getEnd5ygKITE;
            final Function2<? super Composer, ? super Integer, Unit> function17 = lambda$1714259275$material;
            final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function18 = function3M1792getLambda$1836397928$material;
            final ScaffoldState scaffoldState4 = scaffoldState2;
            final Function2<? super Composer, ? super Integer, Unit> function19 = function2M1790getLambda$1406416085$material;
            final long j12 = j11;
            final long j13 = jM1783contentColorForek8zF_U2;
            boolean isFloatingActionButtonDocked4 = isFloatingActionButtonDocked;
            final Function3 child = ComposableLambdaKt.rememberComposableLambda(-1236753028, true, new Function3() { // from class: androidx.compose.material.ScaffoldKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return ScaffoldKt.Scaffold_u4IkXBM$lambda$1(safeInsets, contentWindowInsets, j12, j13, isFloatingActionButtonDocked, i19, function16, function7, function19, function17, function18, scaffoldState4, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54);
            if (function14 != 0) {
                $composer3.startReplaceGroup(1400257547);
                ComposerKt.sourceInformation($composer3, "234@11126L19,224@10668L488");
                long j14 = jM1769getSurface0d7_KjU;
                Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function20 = function14;
                boolean drawerGesturesEnabled6 = drawerGesturesEnabled4;
                DrawerKt.m1810ModalDrawerGs3lGvM(function20, modifier2, scaffoldState2.getDrawerState(), drawerGesturesEnabled6, drawerShape3, fM1803getElevationD9Ej5fM, j14, jM1783contentColorForek8zF_U, scrimColor, ComposableLambdaKt.rememberComposableLambda(-1888468172, true, new Function2() { // from class: androidx.compose.material.ScaffoldKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ScaffoldKt.Scaffold_u4IkXBM$lambda$2(child, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer3, 54), $composer3, (($dirty >> 27) & 14) | C.ENCODING_PCM_32BIT | ($dirty & 112) | (($dirty9 << 9) & 7168) | (($dirty9 << 9) & 57344) | (($dirty9 << 9) & 458752) | (($dirty9 << 9) & 3670016) | (($dirty9 << 9) & 29360128) | (($dirty9 << 9) & 234881024), 0);
                function15 = function20;
                drawerGesturesEnabled5 = drawerGesturesEnabled6;
                jM1769getSurface0d7_KjU = j14;
                $composer2 = $composer3;
                $composer2.endReplaceGroup();
            } else {
                function15 = function14;
                drawerGesturesEnabled5 = drawerGesturesEnabled4;
                $composer2 = $composer3;
                $composer2.startReplaceGroup(1400739380);
                ComposerKt.sourceInformation($composer2, "237@11178L15");
                child.invoke(modifier2, $composer2, Integer.valueOf((($dirty >> 3) & 14) | 48));
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function8 = lambda$866784315$material;
            i5 = iM1834getEnd5ygKITE;
            isFloatingActionButtonDocked2 = isFloatingActionButtonDocked4;
            Function2<? super Composer, ? super Integer, Unit> function21 = lambda$1714259275$material;
            function12 = function15;
            modifier3 = modifier2;
            drawerShape2 = drawerShape3;
            function11 = function2M1790getLambda$1406416085$material;
            j9 = jM1769getSurface0d7_KjU;
            function9 = function21;
            function10 = function3M1792getLambda$1836397928$material;
            drawerGesturesEnabled2 = drawerGesturesEnabled5;
            scaffoldState3 = scaffoldState2;
            f2 = fM1803getElevationD9Ej5fM;
            j10 = jM1783contentColorForek8zF_U;
            j6 = scrimColor;
            j7 = j11;
            j8 = jM1783contentColorForek8zF_U2;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            j6 = j3;
            j7 = j4;
            j8 = j5;
            function8 = lambda$866784315$material;
            i5 = iM1834getEnd5ygKITE;
            function9 = lambda$1714259275$material;
            function10 = function3M1792getLambda$1836397928$material;
            modifier3 = modifier2;
            scaffoldState3 = scaffoldState2;
            function11 = function2M1790getLambda$1406416085$material;
            isFloatingActionButtonDocked2 = isFloatingActionButtonDocked;
            function12 = function6;
            drawerGesturesEnabled2 = drawerGesturesEnabled;
            drawerShape2 = drawerShape;
            f2 = f;
            j9 = j;
            j10 = j2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ScaffoldKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ScaffoldKt.Scaffold_u4IkXBM$lambda$3(contentWindowInsets, modifier3, scaffoldState3, function8, function9, function10, function11, i5, isFloatingActionButtonDocked2, function12, drawerGesturesEnabled2, drawerShape2, f2, j9, j10, j6, j7, j8, function7, $changed, $changed1, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Scaffold_u4IkXBM$lambda$1(final MutableWindowInsets $safeInsets, final WindowInsets $contentWindowInsets, long j, long j2, final boolean $isFloatingActionButtonDocked, final int i, final Function2 $topBar, final Function3 $content, final Function2 $floatingActionButton, final Function2 $bottomBar, final Function3 $snackbarHost, final ScaffoldState $scaffoldState, Modifier childModifier, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(childModifier)202@9742L275,209@10119L496,200@9642L973:Scaffold.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(childModifier) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if ($composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1236753028, $dirty2, -1, "androidx.compose.material.Scaffold.<anonymous> (Scaffold.kt:200)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1186285871, "CC(remember):Scaffold.kt#9igjgp");
            boolean invalid$iv = $composer.changed($safeInsets) | $composer.changed($contentWindowInsets);
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material.ScaffoldKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ScaffoldKt.Scaffold_u4IkXBM$lambda$1$0$0($safeInsets, $contentWindowInsets, (WindowInsets) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            SurfaceKt.m1954SurfaceFjzlyU(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(childModifier, (Function1) it$iv), null, j, j2, null, 0.0f, ComposableLambdaKt.rememberComposableLambda(-1761194824, true, new Function2() { // from class: androidx.compose.material.ScaffoldKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ScaffoldKt.Scaffold_u4IkXBM$lambda$1$1($isFloatingActionButtonDocked, i, $topBar, $content, $floatingActionButton, $safeInsets, $bottomBar, $snackbarHost, $scaffoldState, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 1572864, 50);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Scaffold_u4IkXBM$lambda$1$0$0(MutableWindowInsets $safeInsets, WindowInsets $contentWindowInsets, WindowInsets consumedWindowInsets) {
        $safeInsets.setInsets(WindowInsetsKt.exclude($contentWindowInsets, consumedWindowInsets));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Scaffold_u4IkXBM$lambda$1$1(boolean $isFloatingActionButtonDocked, int i, Function2 $topBar, Function3 $content, Function2 $floatingActionButton, MutableWindowInsets $safeInsets, Function2 $bottomBar, final Function3 $snackbarHost, final ScaffoldState $scaffoldState, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C216@10442L49,210@10137L464:Scaffold.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1761194824, $changed, -1, "androidx.compose.material.Scaffold.<anonymous>.<anonymous> (Scaffold.kt:210)");
            }
            m1915ScaffoldLayouti1QSOvI($isFloatingActionButtonDocked, i, $topBar, $content, ComposableLambdaKt.rememberComposableLambda(545329543, true, new Function2() { // from class: androidx.compose.material.ScaffoldKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ScaffoldKt.Scaffold_u4IkXBM$lambda$1$1$0($snackbarHost, $scaffoldState, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $floatingActionButton, $safeInsets, $bottomBar, $composer, 24576);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Scaffold_u4IkXBM$lambda$1$1$0(Function3 $snackbarHost, ScaffoldState $scaffoldState, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C216@10444L45:Scaffold.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(545329543, $changed, -1, "androidx.compose.material.Scaffold.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:216)");
            }
            $snackbarHost.invoke($scaffoldState.getSnackbarHostState(), $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit Scaffold_u4IkXBM$lambda$2(Function3 $child, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C234@11128L15:Scaffold.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1888468172, $changed, -1, "androidx.compose.material.Scaffold.<anonymous> (Scaffold.kt:234)");
            }
            $child.invoke(Modifier.INSTANCE, $composer, 54);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: Scaffold-27mzLpw, reason: not valid java name */
    public static final void m1913Scaffold27mzLpw(Modifier modifier, ScaffoldState scaffoldState, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, int i, boolean isFloatingActionButtonDocked, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function6, boolean drawerGesturesEnabled, Shape drawerShape, float f, long j, long j2, long j3, long j4, long j5, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function7, Composer $composer, final int $changed, final int $changed1, final int i2) {
        Modifier modifier2;
        ScaffoldState scaffoldState2;
        Function2<? super Composer, ? super Integer, Unit> lambda$566090785$material;
        Function2<? super Composer, ? super Integer, Unit> function2M1791getLambda$1624772335$material;
        Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> lambda$939725476$material;
        Function2<? super Composer, ? super Integer, Unit> function2M1789getLambda$1341284559$material;
        int iM1834getEnd5ygKITE;
        int $dirty;
        int $dirty1;
        long j6;
        int $dirty2;
        Composer $composer2;
        final long j7;
        final long j8;
        final long j9;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Modifier modifier3;
        final ScaffoldState scaffoldState3;
        final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function10;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final int i3;
        final boolean isFloatingActionButtonDocked2;
        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function12;
        final boolean drawerGesturesEnabled2;
        final Shape drawerShape2;
        final float f2;
        final long j10;
        final long j11;
        int $dirty3;
        boolean isFloatingActionButtonDocked3;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function13;
        boolean drawerGesturesEnabled3;
        CornerBasedShape drawerShape3;
        int $dirty4;
        float fM1803getElevationD9Ej5fM;
        long jM1769getSurface0d7_KjU;
        long jM1783contentColorForek8zF_U;
        long scrimColor;
        long jM1758getBackground0d7_KjU;
        boolean drawerGesturesEnabled4;
        Modifier modifier4;
        Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function14;
        int i4;
        long j12;
        long j13;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function15;
        long jM1783contentColorForek8zF_U2;
        long j14;
        int $dirty5;
        int $dirty6;
        Shape drawerShape4;
        ScaffoldState scaffoldState4;
        Function2<? super Composer, ? super Integer, Unit> function16;
        boolean isFloatingActionButtonDocked4;
        int $dirty7;
        Composer $composer3 = $composer.startRestartGroup(1135600301);
        ComposerKt.sourceInformation($composer3, "C(Scaffold)N(modifier,scaffoldState,topBar,bottomBar,snackbarHost,floatingActionButton,floatingActionButtonPosition:c#material.FabPosition,isFloatingActionButtonDocked,drawerContent,drawerGesturesEnabled,drawerShape,drawerElevation:c#ui.unit.Dp,drawerBackgroundColor:c#ui.graphics.Color,drawerContentColor:c#ui.graphics.Color,drawerScrimColor:c#ui.graphics.Color,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,content)322@16008L496:Scaffold.kt#jmzs0o");
        int $dirty8 = $changed;
        int $dirty9 = $changed1;
        int i5 = i2 & 1;
        if (i5 != 0) {
            $dirty8 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty8 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i2 & 2) == 0) {
                scaffoldState2 = scaffoldState;
                int i6 = $composer3.changed(scaffoldState2) ? 32 : 16;
                $dirty8 |= i6;
            } else {
                scaffoldState2 = scaffoldState;
            }
            $dirty8 |= i6;
        } else {
            scaffoldState2 = scaffoldState;
        }
        int i7 = i2 & 4;
        if (i7 != 0) {
            $dirty8 |= 384;
            lambda$566090785$material = function2;
        } else if (($changed & 384) == 0) {
            lambda$566090785$material = function2;
            $dirty8 |= $composer3.changedInstance(lambda$566090785$material) ? 256 : 128;
        } else {
            lambda$566090785$material = function2;
        }
        int i8 = i2 & 8;
        int i9 = 2048;
        if (i8 != 0) {
            $dirty8 |= 3072;
            function2M1791getLambda$1624772335$material = function3;
        } else if (($changed & 3072) == 0) {
            function2M1791getLambda$1624772335$material = function3;
            $dirty8 |= $composer3.changedInstance(function2M1791getLambda$1624772335$material) ? 2048 : 1024;
        } else {
            function2M1791getLambda$1624772335$material = function3;
        }
        int i10 = i2 & 16;
        if (i10 != 0) {
            $dirty8 |= 24576;
            lambda$939725476$material = function4;
        } else if (($changed & 24576) == 0) {
            lambda$939725476$material = function4;
            $dirty8 |= $composer3.changedInstance(lambda$939725476$material) ? 16384 : 8192;
        } else {
            lambda$939725476$material = function4;
        }
        int i11 = i2 & 32;
        if (i11 != 0) {
            $dirty8 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function2M1789getLambda$1341284559$material = function5;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function2M1789getLambda$1341284559$material = function5;
            $dirty8 |= $composer3.changedInstance(function2M1789getLambda$1341284559$material) ? 131072 : 65536;
        } else {
            function2M1789getLambda$1341284559$material = function5;
        }
        int i12 = i2 & 64;
        if (i12 != 0) {
            $dirty8 |= 1572864;
            iM1834getEnd5ygKITE = i;
        } else if (($changed & 1572864) == 0) {
            iM1834getEnd5ygKITE = i;
            $dirty8 |= $composer3.changed(iM1834getEnd5ygKITE) ? 1048576 : 524288;
        } else {
            iM1834getEnd5ygKITE = i;
        }
        int i13 = i2 & 128;
        if (i13 != 0) {
            $dirty8 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty8 |= $composer3.changed(isFloatingActionButtonDocked) ? 8388608 : 4194304;
        }
        int i14 = i2 & 256;
        if (i14 != 0) {
            $dirty8 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty8 |= $composer3.changedInstance(function6) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i15 = i2 & 512;
        if (i15 != 0) {
            $dirty8 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty8 |= $composer3.changed(drawerGesturesEnabled) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty9 |= ((i2 & 1024) == 0 && $composer3.changed(drawerShape)) ? 4 : 2;
        }
        int i16 = i2 & 2048;
        if (i16 != 0) {
            $dirty9 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty9 |= $composer3.changed(f) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            if ((i2 & 4096) == 0) {
                $dirty = $dirty8;
                $dirty7 = $dirty9;
                int i17 = $composer3.changed(j) ? 256 : 128;
                $dirty1 = $dirty7 | i17;
            } else {
                $dirty = $dirty8;
                $dirty7 = $dirty9;
            }
            $dirty1 = $dirty7 | i17;
        } else {
            $dirty = $dirty8;
            $dirty1 = $dirty9;
        }
        int $dirty10 = $dirty1;
        if (($changed1 & 3072) == 0) {
            if ((i2 & 8192) == 0) {
                j6 = j2;
                if (!$composer3.changed(j6)) {
                }
                $dirty2 = $dirty10 | i9;
            } else {
                j6 = j2;
            }
            i9 = 1024;
            $dirty2 = $dirty10 | i9;
        } else {
            j6 = j2;
            $dirty2 = $dirty10;
        }
        int $dirty11 = $dirty2;
        if (($changed1 & 24576) == 0) {
            $dirty11 |= ((i2 & 16384) == 0 && $composer3.changed(j3)) ? 16384 : 8192;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty11 |= ((i2 & 32768) == 0 && $composer3.changed(j4)) ? 131072 : 65536;
        }
        if (($changed1 & 1572864) == 0) {
            $dirty11 |= ((i2 & 65536) == 0 && $composer3.changed(j5)) ? 1048576 : 524288;
        }
        if (($changed1 & 12582912) == 0) {
            $dirty11 |= $composer3.changedInstance(function7) ? 8388608 : 4194304;
        }
        if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty11 & 4793491) == 4793490) ? false : true, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "304@15058L23,313@15568L6,315@15683L6,316@15731L38,317@15816L10,318@15871L6,319@15916L32");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                $dirty5 = (i2 & 2) != 0 ? $dirty & (-113) : $dirty;
                if ((i2 & 1024) != 0) {
                    $dirty11 &= -15;
                }
                $dirty6 = $dirty11;
                if ((i2 & 4096) != 0) {
                    $dirty6 &= -897;
                }
                if ((i2 & 8192) != 0) {
                    $dirty6 &= -7169;
                }
                if ((i2 & 16384) != 0) {
                    $dirty6 &= -57345;
                }
                if ((32768 & i2) != 0) {
                    $dirty6 &= -458753;
                }
                if ((i2 & 65536) != 0) {
                    $dirty6 &= -3670017;
                }
                function15 = function6;
                drawerShape4 = drawerShape;
                fM1803getElevationD9Ej5fM = f;
                j13 = j;
                scrimColor = j3;
                j14 = j4;
                jM1783contentColorForek8zF_U2 = j5;
                j12 = j6;
                modifier4 = modifier2;
                scaffoldState4 = scaffoldState2;
                function14 = lambda$939725476$material;
                function16 = function2M1789getLambda$1341284559$material;
                i4 = iM1834getEnd5ygKITE;
                isFloatingActionButtonDocked4 = isFloatingActionButtonDocked;
                drawerGesturesEnabled4 = drawerGesturesEnabled;
            } else {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 2) == 0) {
                    $dirty3 = $dirty;
                } else {
                    $dirty3 = $dirty & (-113);
                    scaffoldState2 = rememberScaffoldState(null, null, $composer3, 0, 3);
                }
                if (i7 != 0) {
                    lambda$566090785$material = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$566090785$material();
                }
                if (i8 != 0) {
                    function2M1791getLambda$1624772335$material = ComposableSingletons$ScaffoldKt.INSTANCE.m1791getLambda$1624772335$material();
                }
                if (i10 != 0) {
                    lambda$939725476$material = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$939725476$material();
                }
                if (i11 != 0) {
                    function2M1789getLambda$1341284559$material = ComposableSingletons$ScaffoldKt.INSTANCE.m1789getLambda$1341284559$material();
                }
                if (i12 != 0) {
                    iM1834getEnd5ygKITE = FabPosition.INSTANCE.m1834getEnd5ygKITE();
                }
                if (i13 == 0) {
                    isFloatingActionButtonDocked3 = isFloatingActionButtonDocked;
                } else {
                    isFloatingActionButtonDocked3 = false;
                }
                if (i14 == 0) {
                    function13 = function6;
                } else {
                    function13 = null;
                }
                if (i15 == 0) {
                    drawerGesturesEnabled3 = drawerGesturesEnabled;
                } else {
                    drawerGesturesEnabled3 = true;
                }
                if ((i2 & 1024) == 0) {
                    drawerShape3 = drawerShape;
                    $dirty4 = $dirty11;
                } else {
                    drawerShape3 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getLarge();
                    $dirty4 = $dirty11 & (-15);
                }
                if (i16 == 0) {
                    fM1803getElevationD9Ej5fM = f;
                } else {
                    fM1803getElevationD9Ej5fM = DrawerDefaults.INSTANCE.m1803getElevationD9Ej5fM();
                }
                if ((i2 & 4096) != 0) {
                    $dirty4 &= -897;
                    jM1769getSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer3, 6).m1769getSurface0d7_KjU();
                } else {
                    jM1769getSurface0d7_KjU = j;
                }
                if ((i2 & 8192) == 0) {
                    jM1783contentColorForek8zF_U = j2;
                } else {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1769getSurface0d7_KjU, $composer3, ($dirty4 >> 6) & 14);
                    $dirty4 &= -7169;
                }
                if ((i2 & 16384) != 0) {
                    scrimColor = DrawerDefaults.INSTANCE.getScrimColor($composer3, 6);
                    $dirty4 &= -57345;
                } else {
                    scrimColor = j3;
                }
                if ((32768 & i2) == 0) {
                    jM1758getBackground0d7_KjU = j4;
                } else {
                    jM1758getBackground0d7_KjU = MaterialTheme.INSTANCE.getColors($composer3, 6).m1758getBackground0d7_KjU();
                    $dirty4 &= -458753;
                }
                if ((i2 & 65536) == 0) {
                    int i18 = iM1834getEnd5ygKITE;
                    drawerGesturesEnabled4 = drawerGesturesEnabled3;
                    modifier4 = modifier2;
                    function14 = lambda$939725476$material;
                    i4 = i18;
                    j12 = jM1783contentColorForek8zF_U;
                    j13 = jM1769getSurface0d7_KjU;
                    function15 = function13;
                    jM1783contentColorForek8zF_U2 = j5;
                    j14 = jM1758getBackground0d7_KjU;
                    $dirty5 = $dirty3;
                    $dirty6 = $dirty4;
                    drawerShape4 = drawerShape3;
                    scaffoldState4 = scaffoldState2;
                    function16 = function2M1789getLambda$1341284559$material;
                    isFloatingActionButtonDocked4 = isFloatingActionButtonDocked3;
                } else {
                    int i19 = iM1834getEnd5ygKITE;
                    drawerGesturesEnabled4 = drawerGesturesEnabled3;
                    modifier4 = modifier2;
                    function14 = lambda$939725476$material;
                    i4 = i19;
                    j12 = jM1783contentColorForek8zF_U;
                    j13 = jM1769getSurface0d7_KjU;
                    function15 = function13;
                    jM1783contentColorForek8zF_U2 = ColorsKt.m1783contentColorForek8zF_U(jM1758getBackground0d7_KjU, $composer3, ($dirty4 >> 15) & 14);
                    j14 = jM1758getBackground0d7_KjU;
                    $dirty5 = $dirty3;
                    $dirty6 = $dirty4 & (-3670017);
                    drawerShape4 = drawerShape3;
                    scaffoldState4 = scaffoldState2;
                    function16 = function2M1789getLambda$1341284559$material;
                    isFloatingActionButtonDocked4 = isFloatingActionButtonDocked3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1135600301, $dirty5, $dirty6, "androidx.compose.material.Scaffold (Scaffold.kt:321)");
            }
            int $dirty12 = $dirty5;
            $composer2 = $composer3;
            m1914Scaffoldu4IkXBM(WindowInsetsKt.m891WindowInsetsa9UjIt4$default(Dp.m7905constructorimpl(0), 0.0f, 0.0f, 0.0f, 14, null), modifier4, scaffoldState4, lambda$566090785$material, function2M1791getLambda$1624772335$material, function14, function16, i4, isFloatingActionButtonDocked4, function15, drawerGesturesEnabled4, drawerShape4, fM1803getElevationD9Ej5fM, j13, j12, scrimColor, j14, jM1783contentColorForek8zF_U2, function7, $composer2, (($dirty12 << 3) & 112) | (($dirty12 << 3) & 896) | (($dirty12 << 3) & 7168) | (($dirty12 << 3) & 57344) | (($dirty12 << 3) & 458752) | (($dirty12 << 3) & 3670016) | (($dirty12 << 3) & 29360128) | (($dirty12 << 3) & 234881024) | (($dirty12 << 3) & 1879048192), (($dirty12 >> 27) & 14) | (($dirty6 << 3) & 112) | (($dirty6 << 3) & 896) | (($dirty6 << 3) & 7168) | (($dirty6 << 3) & 57344) | (($dirty6 << 3) & 458752) | (($dirty6 << 3) & 3670016) | (($dirty6 << 3) & 29360128) | (($dirty6 << 3) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            scaffoldState3 = scaffoldState4;
            function8 = lambda$566090785$material;
            function9 = function2M1791getLambda$1624772335$material;
            function10 = function14;
            function11 = function16;
            i3 = i4;
            isFloatingActionButtonDocked2 = isFloatingActionButtonDocked4;
            function12 = function15;
            drawerGesturesEnabled2 = drawerGesturesEnabled4;
            drawerShape2 = drawerShape4;
            f2 = fM1803getElevationD9Ej5fM;
            j10 = j13;
            j7 = j12;
            j11 = scrimColor;
            j8 = j14;
            j9 = jM1783contentColorForek8zF_U2;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            j7 = j2;
            j8 = j4;
            j9 = j5;
            function8 = lambda$566090785$material;
            function9 = function2M1791getLambda$1624772335$material;
            modifier3 = modifier2;
            scaffoldState3 = scaffoldState2;
            function10 = lambda$939725476$material;
            function11 = function2M1789getLambda$1341284559$material;
            i3 = iM1834getEnd5ygKITE;
            isFloatingActionButtonDocked2 = isFloatingActionButtonDocked;
            function12 = function6;
            drawerGesturesEnabled2 = drawerGesturesEnabled;
            drawerShape2 = drawerShape;
            f2 = f;
            j10 = j;
            j11 = j3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ScaffoldKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ScaffoldKt.Scaffold_27mzLpw$lambda$0(modifier3, scaffoldState3, function8, function9, function10, function11, i3, isFloatingActionButtonDocked2, function12, drawerGesturesEnabled2, drawerShape2, f2, j10, j7, j11, j8, j9, function7, $changed, $changed1, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: ScaffoldLayout-i1QSOvI, reason: not valid java name */
    private static final void m1915ScaffoldLayouti1QSOvI(final boolean isFabDocked, final int i, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final WindowInsets contentWindowInsets, final Function2<? super Composer, ? super Integer, Unit> function6, Composer $composer, final int $changed) {
        boolean z;
        final int i2;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function8;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        Function2<? super Composer, ? super Integer, Unit> function10;
        WindowInsets windowInsets;
        Function2<? super Composer, ? super Integer, Unit> function11;
        int i3;
        Composer $composer2 = $composer.startRestartGroup(675142332);
        ComposerKt.sourceInformation($composer2, "C(ScaffoldLayout)N(isFabDocked,fabPosition:c#material.FabPosition,topBar,content,snackbar,fab,contentWindowInsets,bottomBar)382@18230L626,398@18879L6945,398@18862L6962:Scaffold.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            z = isFabDocked;
            $dirty |= $composer2.changed(z) ? 4 : 2;
        } else {
            z = isFabDocked;
        }
        if (($changed & 48) == 0) {
            i2 = i;
            $dirty |= $composer2.changed(i2) ? 32 : 16;
        } else {
            i2 = i;
        }
        if (($changed & 384) == 0) {
            function7 = function2;
            $dirty |= $composer2.changedInstance(function7) ? 256 : 128;
        } else {
            function7 = function2;
        }
        if (($changed & 3072) == 0) {
            function8 = function3;
            $dirty |= $composer2.changedInstance(function8) ? 2048 : 1024;
        } else {
            function8 = function3;
        }
        if (($changed & 24576) == 0) {
            function9 = function4;
            $dirty |= $composer2.changedInstance(function9) ? 16384 : 8192;
        } else {
            function9 = function4;
        }
        if ((196608 & $changed) == 0) {
            function10 = function5;
            $dirty |= $composer2.changedInstance(function10) ? 131072 : 65536;
        } else {
            function10 = function5;
        }
        if (($changed & 1572864) == 0) {
            windowInsets = contentWindowInsets;
            $dirty |= $composer2.changed(windowInsets) ? 1048576 : 524288;
        } else {
            windowInsets = contentWindowInsets;
        }
        if (($changed & 12582912) == 0) {
            function11 = function6;
            $dirty |= $composer2.changedInstance(function11) ? 8388608 : 4194304;
        } else {
            function11 = function6;
        }
        if (!$composer2.shouldExecute(($dirty & 4793491) != 4793490, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(675142332, $dirty, -1, "androidx.compose.material.ScaffoldLayout (Scaffold.kt:377)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 271741614, "CC(remember):Scaffold.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new ScaffoldKt$ScaffoldLayout$contentPadding$1$1();
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            final ScaffoldKt$ScaffoldLayout$contentPadding$1$1 contentPadding = (ScaffoldKt$ScaffoldLayout$contentPadding$1$1) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 271768701, "CC(remember):Scaffold.kt#9igjgp");
            boolean invalid$iv = (($dirty & 896) == 256) | ((57344 & $dirty) == 16384) | ((3670016 & $dirty) == 1048576) | ((458752 & $dirty) == 131072) | (($dirty & 112) == 32) | (($dirty & 14) == 4) | ((29360128 & $dirty) == 8388608) | (($dirty & 7168) == 2048);
            Object value$iv2 = $composer2.rememberedValue();
            if (invalid$iv || value$iv2 == Composer.INSTANCE.getEmpty()) {
                final boolean z2 = z;
                final WindowInsets windowInsets2 = windowInsets;
                final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function12 = function8;
                final Function2<? super Composer, ? super Integer, Unit> function13 = function11;
                i3 = 0;
                final Function2<? super Composer, ? super Integer, Unit> function14 = function10;
                value$iv2 = new Function2() { // from class: androidx.compose.material.ScaffoldKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ScaffoldKt.ScaffoldLayout_i1QSOvI$lambda$1$0(function7, function9, function14, i2, z2, windowInsets2, contentPadding, function13, function12, (SubcomposeMeasureScope) obj, (Constraints) obj2);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
            } else {
                i3 = 0;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SubcomposeLayoutKt.SubcomposeLayout(null, (Function2) value$iv2, $composer2, i3, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ScaffoldKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ScaffoldKt.ScaffoldLayout_i1QSOvI$lambda$2(isFabDocked, i, function2, function3, function4, function5, contentWindowInsets, function6, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult ScaffoldLayout_i1QSOvI$lambda$1$0(Function2 $topBar, Function2 $snackbar, Function2 $fab, int i, boolean $isFabDocked, WindowInsets $contentWindowInsets, final ScaffoldKt$ScaffoldLayout$contentPadding$1$1 $contentPadding, final Function2 $bottomBar, final Function3 $content, SubcomposeMeasureScope $this$SubcomposeLayout, Constraints constraints) {
        Object maxElem$iv;
        Object maxElem$iv2;
        FabPlacement fabPlacement;
        Object maxElem$iv3;
        Integer numValueOf;
        final int snackbarOffsetFromBottom;
        Object maxElem$iv4;
        Object maxElem$iv5;
        int fabLeftOffset;
        int layoutWidth = Constraints.m7858getMaxWidthimpl(constraints.getValue());
        int layoutHeight = Constraints.m7857getMaxHeightimpl(constraints.getValue());
        long value = constraints.getValue();
        long looseConstraints = Constraints.m7847copyZbe2FdA(value, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(value) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(value) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(value) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(value) : 0);
        List<Measurable> listSubcompose = $this$SubcomposeLayout.subcompose(ScaffoldLayoutContent.TopBar, $topBar);
        List target$iv = new ArrayList(listSubcompose.size());
        int index$iv$iv = 0;
        int size = listSubcompose.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = listSubcompose.get(index$iv$iv);
            int layoutWidth2 = layoutWidth;
            Measurable it = (Measurable) item$iv$iv;
            target$iv.add(it.mo6541measureBRTryo0(looseConstraints));
            index$iv$iv++;
            size = size;
            layoutWidth = layoutWidth2;
        }
        int layoutWidth3 = layoutWidth;
        List topBarPlaceables = target$iv;
        List $this$fastMaxBy$iv = topBarPlaceables;
        if ($this$fastMaxBy$iv.isEmpty()) {
            maxElem$iv = null;
        } else {
            maxElem$iv = $this$fastMaxBy$iv.get(0);
            Placeable it2 = (Placeable) maxElem$iv;
            int maxValue$iv = it2.getHeight();
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex($this$fastMaxBy$iv);
            if (1 <= lastIndex) {
                while (true) {
                    Object e$iv = $this$fastMaxBy$iv.get(i$iv);
                    Placeable it3 = (Placeable) e$iv;
                    int height = it3.getHeight();
                    List $this$fastMaxBy$iv2 = $this$fastMaxBy$iv;
                    if (maxValue$iv < height) {
                        maxElem$iv = e$iv;
                        maxValue$iv = height;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                    $this$fastMaxBy$iv = $this$fastMaxBy$iv2;
                }
            }
        }
        Placeable placeable = (Placeable) maxElem$iv;
        final int topBarHeight = placeable != null ? placeable.getHeight() : 0;
        List<Measurable> listSubcompose2 = $this$SubcomposeLayout.subcompose(ScaffoldLayoutContent.Snackbar, $snackbar);
        List target$iv2 = new ArrayList(listSubcompose2.size());
        int size2 = listSubcompose2.size();
        int index$iv$iv2 = 0;
        while (index$iv$iv2 < size2) {
            Object item$iv$iv2 = listSubcompose2.get(index$iv$iv2);
            int index$iv$iv3 = index$iv$iv2;
            Measurable it4 = (Measurable) item$iv$iv2;
            int layoutHeight2 = layoutHeight;
            int leftInset = $contentWindowInsets.getLeft($this$SubcomposeLayout, $this$SubcomposeLayout.getLayoutDirection());
            List topBarPlaceables2 = topBarPlaceables;
            int rightInset = $contentWindowInsets.getRight($this$SubcomposeLayout, $this$SubcomposeLayout.getLayoutDirection());
            int bottomInset = $contentWindowInsets.getBottom($this$SubcomposeLayout);
            int rightInset2 = -leftInset;
            int leftInset2 = -bottomInset;
            target$iv2.add(it4.mo6541measureBRTryo0(ConstraintsKt.m7877offsetNN6EwU(looseConstraints, rightInset2 - rightInset, leftInset2)));
            index$iv$iv2 = index$iv$iv3 + 1;
            size2 = size2;
            layoutHeight = layoutHeight2;
            topBarPlaceables = topBarPlaceables2;
        }
        final int layoutHeight3 = layoutHeight;
        final List topBarPlaceables3 = topBarPlaceables;
        final List snackbarPlaceables = target$iv2;
        List $this$fastMaxBy$iv3 = snackbarPlaceables;
        if ($this$fastMaxBy$iv3.isEmpty()) {
            maxElem$iv2 = null;
        } else {
            maxElem$iv2 = $this$fastMaxBy$iv3.get(0);
            Placeable it5 = (Placeable) maxElem$iv2;
            int maxValue$iv2 = it5.getHeight();
            int i$iv2 = 1;
            int lastIndex2 = CollectionsKt.getLastIndex($this$fastMaxBy$iv3);
            if (1 <= lastIndex2) {
                while (true) {
                    Object e$iv2 = $this$fastMaxBy$iv3.get(i$iv2);
                    Placeable it6 = (Placeable) e$iv2;
                    int height2 = it6.getHeight();
                    List $this$fastMaxBy$iv4 = $this$fastMaxBy$iv3;
                    if (maxValue$iv2 < height2) {
                        maxElem$iv2 = e$iv2;
                        maxValue$iv2 = height2;
                    }
                    if (i$iv2 == lastIndex2) {
                        break;
                    }
                    i$iv2++;
                    $this$fastMaxBy$iv3 = $this$fastMaxBy$iv4;
                }
            }
        }
        Placeable placeable2 = (Placeable) maxElem$iv2;
        int snackbarHeight = placeable2 != null ? placeable2.getHeight() : 0;
        List<Measurable> listSubcompose3 = $this$SubcomposeLayout.subcompose(ScaffoldLayoutContent.Fab, $fab);
        int $i$f$fastMap = 0;
        ArrayList target$iv3 = new ArrayList(listSubcompose3.size());
        List<Measurable> list = listSubcompose3;
        int index$iv$iv4 = 0;
        int size3 = list.size();
        while (index$iv$iv4 < size3) {
            Object item$iv$iv3 = list.get(index$iv$iv4);
            int i2 = size3;
            ArrayList arrayList = target$iv3;
            Measurable measurable = (Measurable) item$iv$iv3;
            int $i$f$fastMap2 = $i$f$fastMap;
            ArrayList target$iv4 = target$iv3;
            int leftInset3 = $contentWindowInsets.getLeft($this$SubcomposeLayout, $this$SubcomposeLayout.getLayoutDirection());
            List<Measurable> list2 = list;
            int rightInset3 = $contentWindowInsets.getRight($this$SubcomposeLayout, $this$SubcomposeLayout.getLayoutDirection());
            int bottomInset2 = $contentWindowInsets.getBottom($this$SubcomposeLayout);
            int rightInset4 = -leftInset3;
            int leftInset4 = -bottomInset2;
            arrayList.add(measurable.mo6541measureBRTryo0(ConstraintsKt.m7877offsetNN6EwU(looseConstraints, rightInset4 - rightInset3, leftInset4)));
            index$iv$iv4++;
            size3 = i2;
            $i$f$fastMap = $i$f$fastMap2;
            target$iv3 = target$iv4;
            list = list2;
        }
        final ArrayList fabPlaceables = target$iv3;
        if (fabPlaceables.isEmpty()) {
            fabPlacement = null;
        } else {
            List $this$fastMaxBy$iv5 = fabPlaceables;
            if ($this$fastMaxBy$iv5.isEmpty()) {
                maxElem$iv4 = null;
            } else {
                maxElem$iv4 = $this$fastMaxBy$iv5.get(0);
                Placeable it7 = (Placeable) maxElem$iv4;
                int maxValue$iv3 = it7.getWidth();
                int i$iv3 = 1;
                int lastIndex3 = CollectionsKt.getLastIndex($this$fastMaxBy$iv5);
                if (1 <= lastIndex3) {
                    while (true) {
                        Object e$iv3 = $this$fastMaxBy$iv5.get(i$iv3);
                        Placeable it8 = (Placeable) e$iv3;
                        int width = it8.getWidth();
                        List $this$fastMaxBy$iv6 = $this$fastMaxBy$iv5;
                        if (maxValue$iv3 < width) {
                            maxElem$iv4 = e$iv3;
                            maxValue$iv3 = width;
                        }
                        if (i$iv3 == lastIndex3) {
                            break;
                        }
                        i$iv3++;
                        $this$fastMaxBy$iv5 = $this$fastMaxBy$iv6;
                    }
                }
            }
            Placeable placeable3 = (Placeable) maxElem$iv4;
            int fabWidth = placeable3 != null ? placeable3.getWidth() : 0;
            List $this$fastMaxBy$iv7 = fabPlaceables;
            if ($this$fastMaxBy$iv7.isEmpty()) {
                maxElem$iv5 = null;
            } else {
                maxElem$iv5 = $this$fastMaxBy$iv7.get(0);
                Placeable it9 = (Placeable) maxElem$iv5;
                int maxValue$iv4 = it9.getHeight();
                int i$iv4 = 1;
                int $i$f$fastMaxBy = CollectionsKt.getLastIndex($this$fastMaxBy$iv7);
                if (1 <= $i$f$fastMaxBy) {
                    while (true) {
                        Object e$iv4 = $this$fastMaxBy$iv7.get(i$iv4);
                        Placeable it10 = (Placeable) e$iv4;
                        int height3 = it10.getHeight();
                        List $this$fastMaxBy$iv8 = $this$fastMaxBy$iv7;
                        if (maxValue$iv4 < height3) {
                            maxElem$iv5 = e$iv4;
                            maxValue$iv4 = height3;
                        }
                        if (i$iv4 == $i$f$fastMaxBy) {
                            break;
                        }
                        i$iv4++;
                        $this$fastMaxBy$iv7 = $this$fastMaxBy$iv8;
                    }
                }
            }
            Placeable placeable4 = (Placeable) maxElem$iv5;
            int fabHeight = placeable4 != null ? placeable4.getHeight() : 0;
            if (fabWidth == 0 || fabHeight == 0) {
                fabPlacement = null;
            } else {
                if (FabPosition.m1829equalsimpl0(i, FabPosition.INSTANCE.m1835getStart5ygKITE())) {
                    fabLeftOffset = $this$SubcomposeLayout.getLayoutDirection() == LayoutDirection.Ltr ? $this$SubcomposeLayout.mo400roundToPx0680j_4(FabSpacing) : (layoutWidth3 - $this$SubcomposeLayout.mo400roundToPx0680j_4(FabSpacing)) - fabWidth;
                } else if (FabPosition.m1829equalsimpl0(i, FabPosition.INSTANCE.m1834getEnd5ygKITE())) {
                    fabLeftOffset = $this$SubcomposeLayout.getLayoutDirection() == LayoutDirection.Ltr ? (layoutWidth3 - $this$SubcomposeLayout.mo400roundToPx0680j_4(FabSpacing)) - fabWidth : $this$SubcomposeLayout.mo400roundToPx0680j_4(FabSpacing);
                } else {
                    fabLeftOffset = (layoutWidth3 - fabWidth) / 2;
                }
                fabPlacement = new FabPlacement($isFabDocked, fabLeftOffset, fabWidth, fabHeight);
            }
        }
        final FabPlacement fabPlacement2 = fabPlacement;
        List<Measurable> listSubcompose4 = $this$SubcomposeLayout.subcompose(ScaffoldLayoutContent.BottomBar, ComposableLambdaKt.composableLambdaInstance(-502652347, true, new Function2() { // from class: androidx.compose.material.ScaffoldKt$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ScaffoldKt.ScaffoldLayout_i1QSOvI$lambda$1$0$7(fabPlacement2, $bottomBar, (Composer) obj, ((Integer) obj2).intValue());
            }
        }));
        List target$iv5 = new ArrayList(listSubcompose4.size());
        int index$iv$iv5 = 0;
        for (int size4 = listSubcompose4.size(); index$iv$iv5 < size4; size4 = size4) {
            Object item$iv$iv4 = listSubcompose4.get(index$iv$iv5);
            int index$iv$iv6 = index$iv$iv5;
            Measurable it11 = (Measurable) item$iv$iv4;
            target$iv5.add(it11.mo6541measureBRTryo0(looseConstraints));
            index$iv$iv5 = index$iv$iv6 + 1;
        }
        final List bottomBarPlaceables = target$iv5;
        List $this$fastMaxBy$iv9 = bottomBarPlaceables;
        if ($this$fastMaxBy$iv9.isEmpty()) {
            maxElem$iv3 = null;
        } else {
            maxElem$iv3 = $this$fastMaxBy$iv9.get(0);
            Placeable it12 = (Placeable) maxElem$iv3;
            int maxValue$iv5 = it12.getHeight();
            int i$iv5 = 1;
            int $i$f$fastMaxBy2 = CollectionsKt.getLastIndex($this$fastMaxBy$iv9);
            if (1 <= $i$f$fastMaxBy2) {
                while (true) {
                    Object e$iv5 = $this$fastMaxBy$iv9.get(i$iv5);
                    Placeable it13 = (Placeable) e$iv5;
                    int height4 = it13.getHeight();
                    List $this$fastMaxBy$iv10 = $this$fastMaxBy$iv9;
                    if (maxValue$iv5 < height4) {
                        maxElem$iv3 = e$iv5;
                        maxValue$iv5 = height4;
                    }
                    if (i$iv5 == $i$f$fastMaxBy2) {
                        break;
                    }
                    i$iv5++;
                    $this$fastMaxBy$iv9 = $this$fastMaxBy$iv10;
                }
            }
        }
        Placeable placeable5 = (Placeable) maxElem$iv3;
        Integer bottomBarHeight = placeable5 != null ? Integer.valueOf(placeable5.getHeight()) : null;
        if (fabPlacement2 != null) {
            numValueOf = Integer.valueOf(bottomBarHeight == null ? fabPlacement2.getHeight() + $this$SubcomposeLayout.mo400roundToPx0680j_4(FabSpacing) + $contentWindowInsets.getBottom($this$SubcomposeLayout) : $isFabDocked ? bottomBarHeight.intValue() + (fabPlacement2.getHeight() / 2) : bottomBarHeight.intValue() + fabPlacement2.getHeight() + $this$SubcomposeLayout.mo400roundToPx0680j_4(FabSpacing));
        } else {
            numValueOf = null;
        }
        final Integer fabOffsetFromBottom = numValueOf;
        if (snackbarHeight != 0) {
            snackbarOffsetFromBottom = snackbarHeight + (fabOffsetFromBottom != null ? fabOffsetFromBottom.intValue() : bottomBarHeight != null ? bottomBarHeight.intValue() : $contentWindowInsets.getBottom($this$SubcomposeLayout));
        } else {
            snackbarOffsetFromBottom = 0;
        }
        PaddingValues insets = WindowInsetsKt.asPaddingValues($contentWindowInsets, $this$SubcomposeLayout);
        $contentPadding.setPaddingHolder(PaddingKt.m815PaddingValuesa9UjIt4(PaddingKt.calculateStartPadding(insets, $this$SubcomposeLayout.getLayoutDirection()), topBarPlaceables3.isEmpty() ? insets.getTop() : Dp.m7905constructorimpl(0), PaddingKt.calculateEndPadding(insets, $this$SubcomposeLayout.getLayoutDirection()), (bottomBarPlaceables.isEmpty() || bottomBarHeight == 0) ? insets.getBottom() : $this$SubcomposeLayout.mo403toDpu2uoSUM(bottomBarHeight.intValue())));
        int bodyContentHeight = layoutHeight3 - topBarHeight;
        List<Measurable> listSubcompose5 = $this$SubcomposeLayout.subcompose(ScaffoldLayoutContent.MainContent, ComposableLambdaKt.composableLambdaInstance(-574531306, true, new Function2() { // from class: androidx.compose.material.ScaffoldKt$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ScaffoldKt.ScaffoldLayout_i1QSOvI$lambda$1$0$11($content, $contentPadding, (Composer) obj, ((Integer) obj2).intValue());
            }
        }));
        List target$iv6 = new ArrayList(listSubcompose5.size());
        int $i$f$fastMap3 = 0;
        for (int size5 = listSubcompose5.size(); $i$f$fastMap3 < size5; size5 = size5) {
            Object item$iv$iv5 = listSubcompose5.get($i$f$fastMap3);
            int index$iv$iv7 = $i$f$fastMap3;
            Measurable it14 = (Measurable) item$iv$iv5;
            long looseConstraints2 = looseConstraints;
            target$iv6.add(it14.mo6541measureBRTryo0(Constraints.m7847copyZbe2FdA(looseConstraints2, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(looseConstraints2) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(looseConstraints2) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(looseConstraints2) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(looseConstraints2) : bodyContentHeight)));
            $i$f$fastMap3 = index$iv$iv7 + 1;
            looseConstraints = looseConstraints2;
        }
        final List bodyContentPlaceables = target$iv6;
        final Integer bottomBarHeight2 = bottomBarHeight;
        return MeasureScope.layout$default($this$SubcomposeLayout, layoutWidth3, layoutHeight3, null, new Function1() { // from class: androidx.compose.material.ScaffoldKt$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ScaffoldKt.ScaffoldLayout_i1QSOvI$lambda$1$0$13(bodyContentPlaceables, topBarPlaceables3, snackbarPlaceables, bottomBarPlaceables, fabPlaceables, topBarHeight, layoutHeight3, snackbarOffsetFromBottom, bottomBarHeight2, fabPlacement2, fabOffsetFromBottom, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScaffoldLayout_i1QSOvI$lambda$1$0$7(FabPlacement $fabPlacement, Function2 $bottomBar, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C474@22460L157:Scaffold.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-502652347, $changed, -1, "androidx.compose.material.ScaffoldLayout.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:474)");
            }
            CompositionLocalKt.CompositionLocalProvider(LocalFabPlacement.provides($fabPlacement), (Function2<? super Composer, ? super Integer, Unit>) $bottomBar, $composer, ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScaffoldLayout_i1QSOvI$lambda$1$0$11(Function3 $content, ScaffoldKt$ScaffoldLayout$contentPadding$1$1 $contentPadding, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C533@24904L23:Scaffold.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-574531306, $changed, -1, "androidx.compose.material.ScaffoldLayout.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:533)");
            }
            $content.invoke($contentPadding, $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScaffoldLayout_i1QSOvI$lambda$1$0$13(List $bodyContentPlaceables, List $topBarPlaceables, List $snackbarPlaceables, List $bottomBarPlaceables, List $fabPlaceables, int $topBarHeight, int $layoutHeight, int $snackbarOffsetFromBottom, Integer $bottomBarHeight, FabPlacement $fabPlacement, Integer $fabOffsetFromBottom, Placeable.PlacementScope $this$layout) {
        int size = $bodyContentPlaceables.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $bodyContentPlaceables.get(index$iv);
            Placeable it = (Placeable) item$iv;
            Placeable.PlacementScope.place$default($this$layout, it, 0, $topBarHeight, 0.0f, 4, null);
        }
        int size2 = $topBarPlaceables.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = $topBarPlaceables.get(index$iv2);
            Placeable it2 = (Placeable) item$iv2;
            Placeable.PlacementScope.place$default($this$layout, it2, 0, 0, 0.0f, 4, null);
        }
        int size3 = $snackbarPlaceables.size();
        for (int index$iv3 = 0; index$iv3 < size3; index$iv3++) {
            Object item$iv3 = $snackbarPlaceables.get(index$iv3);
            Placeable it3 = (Placeable) item$iv3;
            Placeable.PlacementScope.place$default($this$layout, it3, 0, $layoutHeight - $snackbarOffsetFromBottom, 0.0f, 4, null);
        }
        int index$iv4 = 0;
        int size4 = $bottomBarPlaceables.size();
        while (true) {
            int iIntValue = 0;
            if (index$iv4 >= size4) {
                break;
            }
            Object item$iv4 = $bottomBarPlaceables.get(index$iv4);
            Placeable it4 = (Placeable) item$iv4;
            if ($bottomBarHeight != null) {
                iIntValue = $bottomBarHeight.intValue();
            }
            Placeable.PlacementScope.place$default($this$layout, it4, 0, $layoutHeight - iIntValue, 0.0f, 4, null);
            index$iv4++;
        }
        int size5 = $fabPlaceables.size();
        for (int index$iv5 = 0; index$iv5 < size5; index$iv5++) {
            Object item$iv5 = $fabPlaceables.get(index$iv5);
            Placeable it5 = (Placeable) item$iv5;
            Placeable.PlacementScope.place$default($this$layout, it5, $fabPlacement != null ? $fabPlacement.getLeft() : 0, $layoutHeight - ($fabOffsetFromBottom != null ? $fabOffsetFromBottom.intValue() : 0), 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    static final FabPlacement LocalFabPlacement$lambda$0() {
        return null;
    }

    public static final ProvidableCompositionLocal<FabPlacement> getLocalFabPlacement() {
        return LocalFabPlacement;
    }
}
