package androidx.compose.material3;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.internal.AnchoredDraggableKt;
import androidx.compose.material3.internal.AnchoredDraggableState;
import androidx.compose.material3.internal.DraggableAnchors;
import androidx.compose.material3.internal.DraggableAnchorsConfig;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BottomSheetScaffold.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0007\n\u0002\b\u0005\u001a\u0087\u0002\u0010\u0000\u001a\u00020\u00012\u001c\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\f2\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u00052\u0019\b\u0002\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u001c\u001a\u00020\u00112\b\b\u0002\u0010\u001d\u001a\u00020\u00112\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b \u0010!\u001a!\u0010\"\u001a\u00020\n2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u001bH\u0007¢\u0006\u0002\u0010&\u001a7\u0010'\u001a\u00020$2\b\b\u0002\u0010(\u001a\u00020)2\u0014\b\u0002\u0010*\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00180\u00032\b\b\u0002\u0010+\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010,\u001a\u008a\u0001\u0010-\u001a\u00020\u00012\u0006\u0010.\u001a\u00020$2\u0006\u0010/\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00100\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u00101\u001a\u00020\f2\u0006\u00102\u001a\u00020\f2\u0013\u00103\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u00052\u001c\u0010\u001e\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u0006H\u0003¢\u0006\u0004\b4\u00105\u001aq\u00106\u001a\u00020\u00012\u0013\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u00052\u0011\u00107\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u00052\u0011\u00108\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u00052\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u00052\f\u00109\u001a\b\u0012\u0004\u0012\u00020:0\u00162\u0006\u0010;\u001a\u00020$H\u0003¢\u0006\u0002\u0010<\u001a\u0014\u0010=\u001a\u00020\b*\u00020\b2\u0006\u0010.\u001a\u00020$H\u0000\u001a\u0014\u0010>\u001a\u00020\b*\u00020\b2\u0006\u0010.\u001a\u00020$H\u0000¨\u0006?"}, d2 = {"BottomSheetScaffold", "", "sheetContent", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "scaffoldState", "Landroidx/compose/material3/BottomSheetScaffoldState;", "sheetPeekHeight", "Landroidx/compose/ui/unit/Dp;", "sheetMaxWidth", "sheetShape", "Landroidx/compose/ui/graphics/Shape;", "sheetContainerColor", "Landroidx/compose/ui/graphics/Color;", "sheetContentColor", "sheetTonalElevation", "sheetShadowElevation", "sheetDragHandle", "Lkotlin/Function0;", "sheetSwipeEnabled", "", "topBar", "snackbarHost", "Landroidx/compose/material3/SnackbarHostState;", "containerColor", "contentColor", UriUtil.LOCAL_CONTENT_SCHEME, "Landroidx/compose/foundation/layout/PaddingValues;", "BottomSheetScaffold-sdMYb0k", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/BottomSheetScaffoldState;FFLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;JJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "rememberBottomSheetScaffoldState", "bottomSheetState", "Landroidx/compose/material3/SheetState;", "snackbarHostState", "(Landroidx/compose/material3/SheetState;Landroidx/compose/material3/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/BottomSheetScaffoldState;", "rememberStandardBottomSheetState", "initialValue", "Landroidx/compose/material3/SheetValue;", "confirmValueChange", "skipHiddenState", "(Landroidx/compose/material3/SheetValue;Lkotlin/jvm/functions/Function1;ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SheetState;", "StandardBottomSheet", "state", "peekHeight", "shape", "tonalElevation", "shadowElevation", "dragHandle", "StandardBottomSheet-w7I5h1o", "(Landroidx/compose/material3/SheetState;FFZLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomSheetScaffoldLayout", TtmlNode.TAG_BODY, "bottomSheet", "sheetOffset", "", "sheetState", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/SheetState;Landroidx/compose/runtime/Composer;I)V", "verticalScaleUp", "verticalScaleDown", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BottomSheetScaffoldKt {

    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SheetValue.values().length];
            try {
                iArr[SheetValue.Hidden.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[SheetValue.Expanded.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit BottomSheetScaffoldLayout$lambda$16(Function2 function2, Function2 function3, Function2 function4, Function2 function5, Function0 function0, SheetState sheetState, int i, Composer composer, int i2) {
        BottomSheetScaffoldLayout(function2, function3, function4, function5, function0, sheetState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit BottomSheetScaffold_sdMYb0k$lambda$1(Function3 function3, Modifier modifier, BottomSheetScaffoldState bottomSheetScaffoldState, float f, float f2, Shape shape, long j, long j2, float f3, float f4, Function2 function2, boolean z, Function2 function4, Function3 function5, long j3, long j4, Function3 function6, int i, int i2, int i3, Composer composer, int i4) {
        m2113BottomSheetScaffoldsdMYb0k(function3, modifier, bottomSheetScaffoldState, f, f2, shape, j, j2, f3, f4, function2, z, function4, function5, j3, j4, function6, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit StandardBottomSheet_w7I5h1o$lambda$14(SheetState sheetState, float f, float f2, boolean z, Shape shape, long j, long j2, float f3, float f4, Function2 function2, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2114StandardBottomSheetw7I5h1o(sheetState, f, f2, z, shape, j, j2, f3, f4, function2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: BottomSheetScaffold-sdMYb0k, reason: not valid java name */
    public static final void m2113BottomSheetScaffoldsdMYb0k(final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, BottomSheetScaffoldState scaffoldState, float sheetPeekHeight, float sheetMaxWidth, Shape sheetShape, long sheetContainerColor, long sheetContentColor, float sheetTonalElevation, float sheetShadowElevation, Function2<? super Composer, ? super Integer, Unit> function2, boolean sheetSwipeEnabled, Function2<? super Composer, ? super Integer, Unit> function4, Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function5, long containerColor, long contentColor, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function6, Composer $composer, final int $changed, final int $changed1, final int i) {
        BottomSheetScaffoldState scaffoldState2;
        float sheetPeekHeight2;
        float sheetMaxWidth2;
        Shape sheetShape2;
        long sheetContainerColor2;
        int $dirty;
        int $dirty1;
        int i2;
        int i3;
        Composer $composer2;
        final Modifier modifier2;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function8;
        final long containerColor2;
        final long contentColor2;
        final float sheetPeekHeight3;
        final float sheetMaxWidth3;
        final Shape sheetShape3;
        final long sheetContainerColor3;
        final BottomSheetScaffoldState scaffoldState3;
        final long sheetContentColor2;
        final float sheetTonalElevation2;
        final float sheetShadowElevation2;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final boolean sheetSwipeEnabled2;
        Modifier.Companion modifier3;
        int i4;
        long sheetContentColor3;
        float sheetTonalElevation3;
        float sheetShadowElevation3;
        Function2<? super Composer, ? super Integer, Unit> lambda$1392012807$material3;
        boolean sheetSwipeEnabled3;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> lambda$1768941633$material3;
        long containerColor3;
        int $dirty2;
        long contentColor3;
        long containerColor4;
        Modifier modifier4;
        float sheetTonalElevation4;
        int $dirty3;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(920075480);
        ComposerKt.sourceInformation($composer3, "C(BottomSheetScaffold)N(sheetContent,modifier,scaffoldState,sheetPeekHeight:c#ui.unit.Dp,sheetMaxWidth:c#ui.unit.Dp,sheetShape,sheetContainerColor:c#ui.graphics.Color,sheetContentColor:c#ui.graphics.Color,sheetTonalElevation:c#ui.unit.Dp,sheetShadowElevation:c#ui.unit.Dp,sheetDragHandle,sheetSwipeEnabled,topBar,snackbarHost,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,content)136@7263L1424:BottomSheetScaffold.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer3.changedInstance(function3) ? 4 : 2;
        }
        int i6 = i & 2;
        if (i6 != 0) {
            $dirty4 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty4 |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                scaffoldState2 = scaffoldState;
                int i7 = $composer3.changed(scaffoldState2) ? 256 : 128;
                $dirty4 |= i7;
            } else {
                scaffoldState2 = scaffoldState;
            }
            $dirty4 |= i7;
        } else {
            scaffoldState2 = scaffoldState;
        }
        int i8 = i & 8;
        if (i8 != 0) {
            $dirty4 |= 3072;
            sheetPeekHeight2 = sheetPeekHeight;
        } else if (($changed & 3072) == 0) {
            sheetPeekHeight2 = sheetPeekHeight;
            $dirty4 |= $composer3.changed(sheetPeekHeight2) ? 2048 : 1024;
        } else {
            sheetPeekHeight2 = sheetPeekHeight;
        }
        int i9 = i & 16;
        int i10 = 8192;
        if (i9 != 0) {
            $dirty4 |= 24576;
            sheetMaxWidth2 = sheetMaxWidth;
        } else if (($changed & 24576) == 0) {
            sheetMaxWidth2 = sheetMaxWidth;
            $dirty4 |= $composer3.changed(sheetMaxWidth2) ? 16384 : 8192;
        } else {
            sheetMaxWidth2 = sheetMaxWidth;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                sheetShape2 = sheetShape;
                if ($composer3.changed(sheetShape2)) {
                    i5 = 131072;
                }
                $dirty4 |= i5;
            } else {
                sheetShape2 = sheetShape;
            }
            i5 = 65536;
            $dirty4 |= i5;
        } else {
            sheetShape2 = sheetShape;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                sheetContainerColor2 = sheetContainerColor;
                int i11 = $composer3.changed(sheetContainerColor2) ? 1048576 : 524288;
                $dirty4 |= i11;
            } else {
                sheetContainerColor2 = sheetContainerColor;
            }
            $dirty4 |= i11;
        } else {
            sheetContainerColor2 = sheetContainerColor;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
                int i12 = $composer3.changed(sheetContentColor) ? 8388608 : 4194304;
                $dirty = $dirty3 | i12;
            } else {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
            }
            $dirty = $dirty3 | i12;
        } else {
            $dirty = $dirty4;
            $dirty1 = $changed1;
        }
        int i13 = i & 256;
        if (i13 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(sheetTonalElevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty5 = i & 512;
        if ($dirty5 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changed(sheetShadowElevation) ? 536870912 : 268435456;
        }
        int $dirty6 = $dirty;
        int i14 = i & 1024;
        if (i14 != 0) {
            $dirty1 |= 6;
        } else {
            int $dirty7 = $changed1 & 6;
            if ($dirty7 == 0) {
                $dirty1 |= $composer3.changedInstance(function2) ? 4 : 2;
            }
        }
        int i15 = i & 2048;
        if (i15 != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changed(sheetSwipeEnabled) ? 32 : 16;
        }
        int i16 = i & 4096;
        if (i16 != 0) {
            $dirty1 |= 384;
            i2 = i16;
        } else {
            i2 = i16;
            if (($changed1 & 384) == 0) {
                $dirty1 |= $composer3.changedInstance(function4) ? 256 : 128;
            }
        }
        int i17 = i & 8192;
        if (i17 != 0) {
            $dirty1 |= 3072;
            i3 = i17;
        } else {
            i3 = i17;
            if (($changed1 & 3072) == 0) {
                $dirty1 |= $composer3.changedInstance(function5) ? 2048 : 1024;
            }
        }
        if (($changed1 & 24576) == 0) {
            if ((i & 16384) == 0 && $composer3.changed(containerColor)) {
                i10 = 16384;
            }
            $dirty1 |= i10;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= ((i & 32768) == 0 && $composer3.changed(contentColor)) ? 131072 : 65536;
        }
        if ((i & 65536) != 0) {
            $dirty1 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty1 |= $composer3.changedInstance(function6) ? 1048576 : 524288;
        }
        if ($composer3.shouldExecute((($dirty6 & 306783379) == 306783378 && (599187 & $dirty1) == 599186) ? false : true, $dirty6 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "120@6376L34,123@6578L13,124@6646L14,125@6693L36,132@7125L11,133@7172L31");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty6 &= -897;
                }
                if ((i & 32) != 0) {
                    $dirty6 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty6 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty6 &= -29360129;
                }
                if ((i & 16384) != 0) {
                    $dirty1 &= -57345;
                }
                if ((i & 32768) != 0) {
                    $dirty1 &= -458753;
                }
                sheetContentColor3 = sheetContentColor;
                sheetTonalElevation4 = sheetTonalElevation;
                sheetShadowElevation3 = sheetShadowElevation;
                lambda$1392012807$material3 = function2;
                sheetSwipeEnabled3 = sheetSwipeEnabled;
                function10 = function4;
                lambda$1768941633$material3 = function5;
                containerColor4 = containerColor;
                contentColor3 = contentColor;
                $dirty2 = $dirty6;
                modifier4 = modifier;
            } else {
                if (i6 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if ((i & 4) != 0) {
                    $dirty6 &= -897;
                    scaffoldState2 = rememberBottomSheetScaffoldState(null, null, $composer3, 0, 3);
                }
                if (i8 != 0) {
                    sheetPeekHeight2 = BottomSheetDefaults.INSTANCE.m2111getSheetPeekHeightD9Ej5fM();
                }
                if (i9 != 0) {
                    sheetMaxWidth2 = BottomSheetDefaults.INSTANCE.m2110getSheetMaxWidthD9Ej5fM();
                }
                if ((i & 32) == 0) {
                    i4 = 6;
                } else {
                    i4 = 6;
                    $dirty6 &= -458753;
                    sheetShape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer3, 6);
                }
                if ((i & 64) != 0) {
                    sheetContainerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer3, i4);
                    $dirty6 &= -3670017;
                }
                if ((i & 128) == 0) {
                    sheetContentColor3 = sheetContentColor;
                } else {
                    sheetContentColor3 = ColorSchemeKt.m2263contentColorForek8zF_U(sheetContainerColor2, $composer3, ($dirty6 >> 18) & 14);
                    $dirty6 &= -29360129;
                }
                if (i13 == 0) {
                    sheetTonalElevation3 = sheetTonalElevation;
                } else {
                    sheetTonalElevation3 = Dp.m7905constructorimpl(0);
                }
                if ($dirty5 != 0) {
                    sheetShadowElevation3 = BottomSheetDefaults.INSTANCE.m2108getElevationD9Ej5fM();
                } else {
                    sheetShadowElevation3 = sheetShadowElevation;
                }
                lambda$1392012807$material3 = i14 != 0 ? ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3() : function2;
                if (i15 == 0) {
                    sheetSwipeEnabled3 = sheetSwipeEnabled;
                } else {
                    sheetSwipeEnabled3 = true;
                }
                if (i2 == 0) {
                    function10 = function4;
                } else {
                    function10 = null;
                }
                lambda$1768941633$material3 = i3 != 0 ? ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3() : function5;
                int $dirty8 = $dirty6;
                if ((i & 16384) != 0) {
                    containerColor3 = MaterialTheme.INSTANCE.getColorScheme($composer3, 6).getSurface();
                    $dirty1 &= -57345;
                } else {
                    containerColor3 = containerColor;
                }
                if ((i & 32768) == 0) {
                    $dirty2 = $dirty8;
                    contentColor3 = contentColor;
                    containerColor4 = containerColor3;
                    modifier4 = modifier3;
                    sheetTonalElevation4 = sheetTonalElevation3;
                } else {
                    contentColor3 = ColorSchemeKt.m2263contentColorForek8zF_U(containerColor3, $composer3, ($dirty1 >> 12) & 14);
                    $dirty1 &= -458753;
                    $dirty2 = $dirty8;
                    containerColor4 = containerColor3;
                    modifier4 = modifier3;
                    sheetTonalElevation4 = sheetTonalElevation3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(920075480, $dirty2, $dirty1, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
            }
            long containerColor5 = containerColor4;
            Modifier modifier$iv = BackgroundKt.m265backgroundbw27NRU$default(SizeKt.fillMaxSize$default(modifier4, 0.0f, 1, null), containerColor5, null, 2, null);
            Modifier modifier5 = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
            $composer2 = $composer3;
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i18 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i19 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1080463218, "C139@7540L1141,139@7474L1207:BottomSheetScaffold.kt#uh7d8r");
            float sheetShadowElevation4 = sheetShadowElevation3;
            Function2<? super Composer, ? super Integer, Unit> function11 = lambda$1392012807$material3;
            boolean sheetSwipeEnabled4 = sheetSwipeEnabled3;
            Function2<? super Composer, ? super Integer, Unit> function12 = function10;
            Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function13 = lambda$1768941633$material3;
            long sheetContentColor4 = sheetContentColor3;
            float sheetTonalElevation5 = sheetTonalElevation4;
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(contentColor3)), ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(scaffoldState2, function12, function6, sheetPeekHeight2, sheetMaxWidth2, sheetSwipeEnabled4, sheetShape2, sheetContainerColor2, sheetContentColor3, sheetTonalElevation4, sheetShadowElevation4, function11, function3, function13), $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            sheetPeekHeight3 = sheetPeekHeight2;
            sheetMaxWidth3 = sheetMaxWidth2;
            sheetShape3 = sheetShape2;
            sheetContainerColor3 = sheetContainerColor2;
            scaffoldState3 = scaffoldState2;
            sheetShadowElevation2 = sheetShadowElevation4;
            function9 = function11;
            contentColor2 = contentColor3;
            modifier2 = modifier5;
            containerColor2 = containerColor5;
            sheetTonalElevation2 = sheetTonalElevation5;
            sheetContentColor2 = sheetContentColor4;
            sheetSwipeEnabled2 = sheetSwipeEnabled4;
            function7 = function12;
            function8 = function13;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            function7 = function4;
            function8 = function5;
            containerColor2 = containerColor;
            contentColor2 = contentColor;
            sheetPeekHeight3 = sheetPeekHeight2;
            sheetMaxWidth3 = sheetMaxWidth2;
            sheetShape3 = sheetShape2;
            sheetContainerColor3 = sheetContainerColor2;
            scaffoldState3 = scaffoldState2;
            sheetContentColor2 = sheetContentColor;
            sheetTonalElevation2 = sheetTonalElevation;
            sheetShadowElevation2 = sheetShadowElevation;
            function9 = function2;
            sheetSwipeEnabled2 = sheetSwipeEnabled;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomSheetScaffoldKt.BottomSheetScaffold_sdMYb0k$lambda$1(function3, modifier2, scaffoldState3, sheetPeekHeight3, sheetMaxWidth3, sheetShape3, sheetContainerColor3, sheetContentColor2, sheetTonalElevation2, sheetShadowElevation2, function9, sheetSwipeEnabled2, function7, function8, containerColor2, contentColor2, function6, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final BottomSheetScaffoldState rememberBottomSheetScaffoldState(SheetState bottomSheetState, SnackbarHostState snackbarHostState, Composer $composer, int $changed, int i) {
        Composer $composer2;
        ComposerKt.sourceInformationMarkerStart($composer, -1474606134, "C(rememberBottomSheetScaffoldState)N(bottomSheetState,snackbarHostState)189@9451L34,190@9530L32,192@9605L197:BottomSheetScaffold.kt#uh7d8r");
        if ((i & 1) != 0) {
            $composer2 = $composer;
            bottomSheetState = rememberStandardBottomSheetState(null, null, false, $composer2, 0, 7);
        } else {
            $composer2 = $composer;
        }
        if ((i & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer2, 242719018, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            Composer $this$cache$iv = $composer2;
            Object it$iv = $this$cache$iv.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new SnackbarHostState();
                $this$cache$iv.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            snackbarHostState = (SnackbarHostState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1474606134, $changed, -1, "androidx.compose.material3.rememberBottomSheetScaffoldState (BottomSheetScaffold.kt:191)");
        }
        ComposerKt.sourceInformationMarkerStart($composer2, 242721583, "CC(remember):BottomSheetScaffold.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer2.changed(bottomSheetState)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer2.changed(snackbarHostState)) || ($changed & 48) == 32);
        Composer $this$cache$iv2 = $composer2;
        Object it$iv2 = $this$cache$iv2.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new BottomSheetScaffoldState(bottomSheetState, snackbarHostState);
            $this$cache$iv2.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        BottomSheetScaffoldState bottomSheetScaffoldState = (BottomSheetScaffoldState) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer2);
        return bottomSheetScaffoldState;
    }

    static final boolean rememberStandardBottomSheetState$lambda$5$lambda$4(SheetValue it) {
        return true;
    }

    public static final SheetState rememberStandardBottomSheetState(SheetValue initialValue, Function1<? super SheetValue, Boolean> function1, boolean skipHiddenState, Composer $composer, int $changed, int i) {
        SheetValue initialValue2;
        Function1<? super SheetValue, Boolean> function2;
        ComposerKt.sourceInformationMarkerStart($composer, 678511581, "C(rememberStandardBottomSheetState)N(initialValue,confirmValueChange,skipHiddenState)212@10383L8,215@10438L154:BottomSheetScaffold.kt#uh7d8r");
        if ((i & 1) != 0) {
            SheetValue initialValue3 = SheetValue.PartiallyExpanded;
            initialValue2 = initialValue3;
        } else {
            initialValue2 = initialValue;
        }
        if ((i & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, -785394011, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(BottomSheetScaffoldKt.rememberStandardBottomSheetState$lambda$5$lambda$4((SheetValue) obj));
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            function2 = (Function1) it$iv;
        } else {
            function2 = function1;
        }
        boolean skipHiddenState2 = (i & 4) != 0 ? true : skipHiddenState;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(678511581, $changed, -1, "androidx.compose.material3.rememberStandardBottomSheetState (BottomSheetScaffold.kt:215)");
        }
        SheetState sheetStateM2838rememberSheetStateAGcomas = SheetDefaultsKt.m2838rememberSheetStateAGcomas(false, function2, initialValue2, skipHiddenState2, 0.0f, 0.0f, $composer, ($changed & 112) | (($changed << 6) & 896) | (($changed << 3) & 7168), 49);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return sheetStateM2838rememberSheetStateAGcomas;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:110:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:111:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:114:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:119:0x022e  */
    /* JADX WARN: Code duplicated, block: B:121:0x0252  */
    /* JADX WARN: Code duplicated, block: B:124:0x028a  */
    /* JADX WARN: Code duplicated, block: B:125:0x028c  */
    /* JADX WARN: Code duplicated, block: B:128:0x029e  */
    /* JADX WARN: Code duplicated, block: B:131:0x02a9  */
    /* JADX WARN: Code duplicated, block: B:135:0x033c  */
    /* JADX INFO: renamed from: StandardBottomSheet-w7I5h1o, reason: not valid java name */
    public static final void m2114StandardBottomSheetw7I5h1o(final SheetState state, final float peekHeight, final float sheetMaxWidth, final boolean sheetSwipeEnabled, final Shape shape, final long containerColor, final long contentColor, final float tonalElevation, final float shadowElevation, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1) {
        Composer $composer2;
        int $dirty;
        Object value$iv$iv;
        final CoroutineScope scope;
        Orientation orientation;
        Modifier.Companion nestedScroll;
        boolean z;
        boolean invalid$iv;
        Object value$iv;
        Modifier nestedScroll2;
        boolean invalid$iv2;
        Object value$iv2;
        Composer $composer3 = $composer.startRestartGroup(-2108849428);
        ComposerKt.sourceInformation($composer3, "C(StandardBottomSheet)N(state,peekHeight:c#ui.unit.Dp,sheetMaxWidth:c#ui.unit.Dp,sheetSwipeEnabled,shape,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,dragHandle,content)238@11171L7,239@11265L7,240@11356L7,242@11380L159,242@11369L170,248@11557L24,250@11666L7,271@12473L1938,322@15222L4372,265@12198L7396:BottomSheetScaffold.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(peekHeight) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changed(sheetMaxWidth) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changed(sheetSwipeEnabled) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changed(shape) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty2 |= $composer3.changed(containerColor) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty2 |= $composer3.changed(contentColor) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(tonalElevation) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty2 |= $composer3.changed(shadowElevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changedInstance(function3) ? 4 : 2;
        }
        if ($composer3.shouldExecute((($dirty2 & 306783379) == 306783378 && ($dirty1 & 3) == 2) ? false : true, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2108849428, $dirty2, $dirty1, "androidx.compose.material3.StandardBottomSheet (BottomSheetScaffold.kt:235)");
            }
            final FiniteAnimationSpec anchoredDraggableMotion = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer3, 6);
            final FiniteAnimationSpec showMotion = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer3, 6);
            final FiniteAnimationSpec hideMotion = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer3, 6);
            ComposerKt.sourceInformationMarkerStart($composer3, 620288715, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            boolean invalid$iv3 = (($dirty2 & 14) == 4) | $composer3.changedInstance(showMotion) | $composer3.changedInstance(hideMotion) | $composer3.changedInstance(anchoredDraggableMotion);
            Object value$iv3 = $composer3.rememberedValue();
            if (invalid$iv3) {
                $dirty = $dirty2;
            } else {
                $dirty = $dirty2;
                if (value$iv3 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.SideEffect((Function0) value$iv3, $composer3, 0);
                ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart($composer3, 683737348, "CC(remember):Effects.kt#9igjgp");
                value$iv$iv = $composer3.rememberedValue();
                if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3);
                    $composer3.updateRememberedValue(value$iv$iv);
                }
                scope = (CoroutineScope) value$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                orientation = Orientation.Vertical;
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer3.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density $this$StandardBottomSheet_w7I5h1o_u24lambda_u248 = (Density) objConsume;
                final float peekHeightPx = $this$StandardBottomSheet_w7I5h1o_u24lambda_u248.mo406toPx0680j_4(peekHeight);
                if (sheetSwipeEnabled) {
                    $composer3.startReplaceGroup(2049456610);
                    ComposerKt.sourceInformation($composer3, "254@11804L327");
                    Modifier.Companion companion = Modifier.INSTANCE;
                    AnchoredDraggableState<SheetValue> anchoredDraggableState$material3 = state.getAnchoredDraggableState$material3();
                    ComposerKt.sourceInformationMarkerStart($composer3, 620302451, "CC(remember):BottomSheetScaffold.kt#9igjgp");
                    invalid$iv2 = $composer3.changed(anchoredDraggableState$material3);
                    value$iv2 = $composer3.rememberedValue();
                    if (invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(state, orientation, new Function1() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return BottomSheetScaffoldKt.StandardBottomSheet_w7I5h1o$lambda$10$lambda$9(scope, state, ((Float) obj).floatValue());
                            }
                        });
                        $composer3.updateRememberedValue(value$iv2);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    nestedScroll = NestedScrollModifierKt.nestedScroll$default(companion, (NestedScrollConnection) value$iv2, null, 2, null);
                    $composer3.endReplaceGroup();
                } else {
                    $composer3.startReplaceGroup(2049851798);
                    $composer3.endReplaceGroup();
                    nestedScroll = Modifier.INSTANCE;
                }
                Modifier modifierThen = SizeKt.m856requiredHeightInVpY3zN4$default(SizeKt.fillMaxWidth$default(SizeKt.m872widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, sheetMaxWidth, 1, null), 0.0f, 1, null), peekHeight, 0.0f, 2, null).then(nestedScroll);
                AnchoredDraggableState<SheetValue> anchoredDraggableState$material4 = state.getAnchoredDraggableState$material3();
                ComposerKt.sourceInformationMarkerStart($composer3, 620325470, "CC(remember):BottomSheetScaffold.kt#9igjgp");
                if (($dirty & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                invalid$iv = z | $composer3.changed(peekHeightPx);
                $composer2 = $composer3;
                value$iv = $composer3.rememberedValue();
                if (invalid$iv) {
                    nestedScroll2 = nestedScroll;
                } else {
                    nestedScroll2 = nestedScroll;
                    if (value$iv == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SurfaceKt.m2929SurfaceT9BRK9s(verticalScaleUp(AnchoredDraggableKt.anchoredDraggable(AnchoredDraggableKt.draggableAnchors(modifierThen, anchoredDraggableState$material4, orientation, (Function2) value$iv), state.getAnchoredDraggableState$material3(), orientation, (24 & 4) != 0 ? true : sheetSwipeEnabled, (24 & 8) != 0 ? false : false, (24 & 16) != 0 ? null : null), state), shape, containerColor, contentColor, tonalElevation, shadowElevation, null, ComposableLambdaKt.rememberComposableLambda(1508311921, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed2) {
                            Function0<ComposeUiNode> function0;
                            ComposerKt.sourceInformation($composer4, "C323@15232L4356:BottomSheetScaffold.kt#uh7d8r");
                            if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1508311921, $changed2, -1, "androidx.compose.material3.StandardBottomSheet.<anonymous> (BottomSheetScaffold.kt:323)");
                            }
                            Modifier modifier$iv = BottomSheetScaffoldKt.verticalScaleDown(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), state);
                            Function2<Composer, Integer, Unit> function4 = function2;
                            Function3<ColumnScope, Composer, Integer, Unit> function5 = function3;
                            SheetState sheetState = state;
                            CoroutineScope coroutineScope = scope;
                            boolean z2 = sheetSwipeEnabled;
                            ComposerKt.sourceInformationMarkerStart($composer4, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv = (0 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                            CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if ($composer4.getInserting()) {
                                function0 = constructor;
                                $composer4.createNode(function0);
                            } else {
                                function0 = constructor;
                                $composer4.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer4);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                            }
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                            int i = ($changed$iv$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                            int $changed3 = ((0 >> 6) & 112) | 6;
                            ColumnScope $this$invoke_u24lambda_u240 = ColumnScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1044090540, "C399@19569L9:BottomSheetScaffold.kt#uh7d8r");
                            if (function4 != null) {
                                $composer4.startReplaceGroup(-1044068159);
                                ComposerKt.sourceInformation($composer4, "332@15663L54,333@15759L48,334@15848L47,335@15934L3608,335@15912L3630");
                                Strings.Companion companion2 = Strings.INSTANCE;
                                String partialExpandActionLabel = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_collapse_description), $composer4, 0);
                                Strings.Companion companion3 = Strings.INSTANCE;
                                String dismissActionLabel = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), $composer4, 0);
                                Strings.Companion companion4 = Strings.INSTANCE;
                                String expandActionLabel = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_expand_description), $composer4, 0);
                                SheetDefaultsKt.DragHandleWithTooltip($this$invoke_u24lambda_u240, ComposableLambdaKt.rememberComposableLambda(-511691176, true, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1(sheetState, coroutineScope, z2, expandActionLabel, partialExpandActionLabel, dismissActionLabel, function4), $composer4, 54), $composer4, ($changed3 & 14) | 48);
                                $composer4.endReplaceGroup();
                            } else {
                                $composer4.startReplaceGroup(-1040260677);
                                $composer4.endReplaceGroup();
                            }
                            function5.invoke($this$invoke_u24lambda_u240, $composer4, Integer.valueOf($changed3 & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer2, 54), $composer2, (($dirty >> 9) & 112) | 12582912 | (($dirty >> 9) & 896) | (($dirty >> 9) & 7168) | (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752), 64);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                value$iv = new Function2() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return BottomSheetScaffoldKt.StandardBottomSheet_w7I5h1o$lambda$13$lambda$12(state, peekHeightPx, (IntSize) obj, (Constraints) obj2);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SurfaceKt.m2929SurfaceT9BRK9s(verticalScaleUp(AnchoredDraggableKt.anchoredDraggable(AnchoredDraggableKt.draggableAnchors(modifierThen, anchoredDraggableState$material4, orientation, (Function2) value$iv), state.getAnchoredDraggableState$material3(), orientation, (24 & 4) != 0 ? true : sheetSwipeEnabled, (24 & 8) != 0 ? false : false, (24 & 16) != 0 ? null : null), state), shape, containerColor, contentColor, tonalElevation, shadowElevation, null, ComposableLambdaKt.rememberComposableLambda(1508311921, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer4, int $changed2) {
                        Function0<ComposeUiNode> function0;
                        ComposerKt.sourceInformation($composer4, "C323@15232L4356:BottomSheetScaffold.kt#uh7d8r");
                        if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            $composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1508311921, $changed2, -1, "androidx.compose.material3.StandardBottomSheet.<anonymous> (BottomSheetScaffold.kt:323)");
                        }
                        Modifier modifier$iv = BottomSheetScaffoldKt.verticalScaleDown(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), state);
                        Function2<Composer, Integer, Unit> function4 = function2;
                        Function3<ColumnScope, Composer, Integer, Unit> function5 = function3;
                        SheetState sheetState = state;
                        CoroutineScope coroutineScope = scope;
                        boolean z2 = sheetSwipeEnabled;
                        ComposerKt.sourceInformationMarkerStart($composer4, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            function0 = constructor;
                            $composer4.createNode(function0);
                        } else {
                            function0 = constructor;
                            $composer4.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer4);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                        int $changed3 = ((0 >> 6) & 112) | 6;
                        ColumnScope $this$invoke_u24lambda_u240 = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1044090540, "C399@19569L9:BottomSheetScaffold.kt#uh7d8r");
                        if (function4 != null) {
                            $composer4.startReplaceGroup(-1044068159);
                            ComposerKt.sourceInformation($composer4, "332@15663L54,333@15759L48,334@15848L47,335@15934L3608,335@15912L3630");
                            Strings.Companion companion2 = Strings.INSTANCE;
                            String partialExpandActionLabel = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_collapse_description), $composer4, 0);
                            Strings.Companion companion3 = Strings.INSTANCE;
                            String dismissActionLabel = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), $composer4, 0);
                            Strings.Companion companion4 = Strings.INSTANCE;
                            String expandActionLabel = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_expand_description), $composer4, 0);
                            SheetDefaultsKt.DragHandleWithTooltip($this$invoke_u24lambda_u240, ComposableLambdaKt.rememberComposableLambda(-511691176, true, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1(sheetState, coroutineScope, z2, expandActionLabel, partialExpandActionLabel, dismissActionLabel, function4), $composer4, 54), $composer4, ($changed3 & 14) | 48);
                            $composer4.endReplaceGroup();
                        } else {
                            $composer4.startReplaceGroup(-1040260677);
                            $composer4.endReplaceGroup();
                        }
                        function5.invoke($this$invoke_u24lambda_u240, $composer4, Integer.valueOf($changed3 & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer2, 54), $composer2, (($dirty >> 9) & 112) | 12582912 | (($dirty >> 9) & 896) | (($dirty >> 9) & 7168) | (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752), 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            value$iv3 = new Function0() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BottomSheetScaffoldKt.StandardBottomSheet_w7I5h1o$lambda$7$lambda$6(state, showMotion, hideMotion, anchoredDraggableMotion);
                }
            };
            $composer3.updateRememberedValue(value$iv3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.SideEffect((Function0) value$iv3, $composer3, 0);
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, 683737348, "CC(remember):Effects.kt#9igjgp");
            value$iv$iv = $composer3.rememberedValue();
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3);
                $composer3.updateRememberedValue(value$iv$iv);
            }
            scope = (CoroutineScope) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            orientation = Orientation.Vertical;
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Density $this$StandardBottomSheet_w7I5h1o_u24lambda_u249 = (Density) objConsume2;
            final float peekHeightPx2 = $this$StandardBottomSheet_w7I5h1o_u24lambda_u249.mo406toPx0680j_4(peekHeight);
            if (sheetSwipeEnabled) {
                $composer3.startReplaceGroup(2049456610);
                ComposerKt.sourceInformation($composer3, "254@11804L327");
                Modifier.Companion companion2 = Modifier.INSTANCE;
                AnchoredDraggableState<SheetValue> anchoredDraggableState$material5 = state.getAnchoredDraggableState$material3();
                ComposerKt.sourceInformationMarkerStart($composer3, 620302451, "CC(remember):BottomSheetScaffold.kt#9igjgp");
                invalid$iv2 = $composer3.changed(anchoredDraggableState$material5);
                value$iv2 = $composer3.rememberedValue();
                if (invalid$iv2) {
                    value$iv2 = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(state, orientation, new Function1() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return BottomSheetScaffoldKt.StandardBottomSheet_w7I5h1o$lambda$10$lambda$9(scope, state, ((Float) obj).floatValue());
                        }
                    });
                    $composer3.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(state, orientation, new Function1() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return BottomSheetScaffoldKt.StandardBottomSheet_w7I5h1o$lambda$10$lambda$9(scope, state, ((Float) obj).floatValue());
                        }
                    });
                    $composer3.updateRememberedValue(value$iv2);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                nestedScroll = NestedScrollModifierKt.nestedScroll$default(companion2, (NestedScrollConnection) value$iv2, null, 2, null);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(2049851798);
                $composer3.endReplaceGroup();
                nestedScroll = Modifier.INSTANCE;
            }
            Modifier modifierThen2 = SizeKt.m856requiredHeightInVpY3zN4$default(SizeKt.fillMaxWidth$default(SizeKt.m872widthInVpY3zN4$default(Modifier.INSTANCE, 0.0f, sheetMaxWidth, 1, null), 0.0f, 1, null), peekHeight, 0.0f, 2, null).then(nestedScroll);
            AnchoredDraggableState<SheetValue> anchoredDraggableState$material6 = state.getAnchoredDraggableState$material3();
            ComposerKt.sourceInformationMarkerStart($composer3, 620325470, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            if (($dirty & 14) == 4) {
                z = true;
            } else {
                z = false;
            }
            invalid$iv = z | $composer3.changed(peekHeightPx2);
            $composer2 = $composer3;
            value$iv = $composer3.rememberedValue();
            if (invalid$iv) {
                nestedScroll2 = nestedScroll;
                if (value$iv == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SurfaceKt.m2929SurfaceT9BRK9s(verticalScaleUp(AnchoredDraggableKt.anchoredDraggable(AnchoredDraggableKt.draggableAnchors(modifierThen2, anchoredDraggableState$material6, orientation, (Function2) value$iv), state.getAnchoredDraggableState$material3(), orientation, (24 & 4) != 0 ? true : sheetSwipeEnabled, (24 & 8) != 0 ? false : false, (24 & 16) != 0 ? null : null), state), shape, containerColor, contentColor, tonalElevation, shadowElevation, null, ComposableLambdaKt.rememberComposableLambda(1508311921, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer4, int $changed2) {
                        Function0<ComposeUiNode> function0;
                        ComposerKt.sourceInformation($composer4, "C323@15232L4356:BottomSheetScaffold.kt#uh7d8r");
                        if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            $composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1508311921, $changed2, -1, "androidx.compose.material3.StandardBottomSheet.<anonymous> (BottomSheetScaffold.kt:323)");
                        }
                        Modifier modifier$iv = BottomSheetScaffoldKt.verticalScaleDown(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), state);
                        Function2<Composer, Integer, Unit> function4 = function2;
                        Function3<ColumnScope, Composer, Integer, Unit> function5 = function3;
                        SheetState sheetState = state;
                        CoroutineScope coroutineScope = scope;
                        boolean z2 = sheetSwipeEnabled;
                        ComposerKt.sourceInformationMarkerStart($composer4, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            function0 = constructor;
                            $composer4.createNode(function0);
                        } else {
                            function0 = constructor;
                            $composer4.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer4);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                        int $changed3 = ((0 >> 6) & 112) | 6;
                        ColumnScope $this$invoke_u24lambda_u240 = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1044090540, "C399@19569L9:BottomSheetScaffold.kt#uh7d8r");
                        if (function4 != null) {
                            $composer4.startReplaceGroup(-1044068159);
                            ComposerKt.sourceInformation($composer4, "332@15663L54,333@15759L48,334@15848L47,335@15934L3608,335@15912L3630");
                            Strings.Companion companion3 = Strings.INSTANCE;
                            String partialExpandActionLabel = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_collapse_description), $composer4, 0);
                            Strings.Companion companion4 = Strings.INSTANCE;
                            String dismissActionLabel = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), $composer4, 0);
                            Strings.Companion companion5 = Strings.INSTANCE;
                            String expandActionLabel = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_expand_description), $composer4, 0);
                            SheetDefaultsKt.DragHandleWithTooltip($this$invoke_u24lambda_u240, ComposableLambdaKt.rememberComposableLambda(-511691176, true, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1(sheetState, coroutineScope, z2, expandActionLabel, partialExpandActionLabel, dismissActionLabel, function4), $composer4, 54), $composer4, ($changed3 & 14) | 48);
                            $composer4.endReplaceGroup();
                        } else {
                            $composer4.startReplaceGroup(-1040260677);
                            $composer4.endReplaceGroup();
                        }
                        function5.invoke($this$invoke_u24lambda_u240, $composer4, Integer.valueOf($changed3 & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer2, 54), $composer2, (($dirty >> 9) & 112) | 12582912 | (($dirty >> 9) & 896) | (($dirty >> 9) & 7168) | (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752), 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                nestedScroll2 = nestedScroll;
            }
            value$iv = new Function2() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomSheetScaffoldKt.StandardBottomSheet_w7I5h1o$lambda$13$lambda$12(state, peekHeightPx2, (IntSize) obj, (Constraints) obj2);
                }
            };
            $composer3.updateRememberedValue(value$iv);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SurfaceKt.m2929SurfaceT9BRK9s(verticalScaleUp(AnchoredDraggableKt.anchoredDraggable(AnchoredDraggableKt.draggableAnchors(modifierThen2, anchoredDraggableState$material6, orientation, (Function2) value$iv), state.getAnchoredDraggableState$material3(), orientation, (24 & 4) != 0 ? true : sheetSwipeEnabled, (24 & 8) != 0 ? false : false, (24 & 16) != 0 ? null : null), state), shape, containerColor, contentColor, tonalElevation, shadowElevation, null, ComposableLambdaKt.rememberComposableLambda(1508311921, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$StandardBottomSheet$3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    ComposerKt.sourceInformation($composer4, "C323@15232L4356:BottomSheetScaffold.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1508311921, $changed2, -1, "androidx.compose.material3.StandardBottomSheet.<anonymous> (BottomSheetScaffold.kt:323)");
                    }
                    Modifier modifier$iv = BottomSheetScaffoldKt.verticalScaleDown(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), state);
                    Function2<Composer, Integer, Unit> function4 = function2;
                    Function3<ColumnScope, Composer, Integer, Unit> function5 = function3;
                    SheetState sheetState = state;
                    CoroutineScope coroutineScope = scope;
                    boolean z2 = sheetSwipeEnabled;
                    ComposerKt.sourceInformationMarkerStart($composer4, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer4.startReusableNode();
                    if ($composer4.getInserting()) {
                        function0 = constructor;
                        $composer4.createNode(function0);
                    } else {
                        function0 = constructor;
                        $composer4.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer4);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                    int $changed3 = ((0 >> 6) & 112) | 6;
                    ColumnScope $this$invoke_u24lambda_u240 = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1044090540, "C399@19569L9:BottomSheetScaffold.kt#uh7d8r");
                    if (function4 != null) {
                        $composer4.startReplaceGroup(-1044068159);
                        ComposerKt.sourceInformation($composer4, "332@15663L54,333@15759L48,334@15848L47,335@15934L3608,335@15912L3630");
                        Strings.Companion companion3 = Strings.INSTANCE;
                        String partialExpandActionLabel = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_collapse_description), $composer4, 0);
                        Strings.Companion companion4 = Strings.INSTANCE;
                        String dismissActionLabel = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), $composer4, 0);
                        Strings.Companion companion5 = Strings.INSTANCE;
                        String expandActionLabel = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_expand_description), $composer4, 0);
                        SheetDefaultsKt.DragHandleWithTooltip($this$invoke_u24lambda_u240, ComposableLambdaKt.rememberComposableLambda(-511691176, true, new BottomSheetScaffoldKt$StandardBottomSheet$3$1$1(sheetState, coroutineScope, z2, expandActionLabel, partialExpandActionLabel, dismissActionLabel, function4), $composer4, 54), $composer4, ($changed3 & 14) | 48);
                        $composer4.endReplaceGroup();
                    } else {
                        $composer4.startReplaceGroup(-1040260677);
                        $composer4.endReplaceGroup();
                    }
                    function5.invoke($this$invoke_u24lambda_u240, $composer4, Integer.valueOf($changed3 & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, (($dirty >> 9) & 112) | 12582912 | (($dirty >> 9) & 896) | (($dirty >> 9) & 7168) | (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomSheetScaffoldKt.StandardBottomSheet_w7I5h1o$lambda$14(state, peekHeight, sheetMaxWidth, sheetSwipeEnabled, shape, containerColor, contentColor, tonalElevation, shadowElevation, function2, function3, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit StandardBottomSheet_w7I5h1o$lambda$7$lambda$6(SheetState $state, FiniteAnimationSpec $showMotion, FiniteAnimationSpec $hideMotion, FiniteAnimationSpec $anchoredDraggableMotion) {
        $state.setShowMotionSpec$material3($showMotion);
        $state.setHideMotionSpec$material3($hideMotion);
        $state.setAnchoredDraggableMotionSpec$material3($anchoredDraggableMotion);
        return Unit.INSTANCE;
    }

    static final Unit StandardBottomSheet_w7I5h1o$lambda$10$lambda$9(CoroutineScope $scope, SheetState $state, float it) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new BottomSheetScaffoldKt$StandardBottomSheet$nestedScroll$1$1$1($state, it, null), 3, null);
        return Unit.INSTANCE;
    }

    static final Pair StandardBottomSheet_w7I5h1o$lambda$13$lambda$12(final SheetState $state, final float $peekHeightPx, IntSize sheetSize, Constraints constraints) {
        final float layoutHeight = Constraints.m7857getMaxHeightimpl(constraints.getValue());
        long arg0$iv = sheetSize.m8080unboximpl();
        final float sheetHeight = (int) (4294967295L & arg0$iv);
        DraggableAnchors newAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BottomSheetScaffoldKt.StandardBottomSheet_w7I5h1o$lambda$13$lambda$12$lambda$11($state, layoutHeight, $peekHeightPx, sheetHeight, (DraggableAnchorsConfig) obj);
            }
        });
        SheetValue oldTarget = $state.getAnchoredDraggableState$material3().getTargetValue();
        switch (WhenMappings.$EnumSwitchMapping$0[oldTarget.ordinal()]) {
            case 1:
                if (newAnchors.hasAnchorFor(SheetValue.Hidden)) {
                    oldTarget = SheetValue.Hidden;
                }
                break;
            case 2:
                if (newAnchors.hasAnchorFor(SheetValue.PartiallyExpanded)) {
                    oldTarget = SheetValue.PartiallyExpanded;
                } else if (newAnchors.hasAnchorFor(SheetValue.Expanded)) {
                    oldTarget = SheetValue.Expanded;
                } else if (newAnchors.hasAnchorFor(SheetValue.Hidden)) {
                    oldTarget = SheetValue.Hidden;
                }
                break;
            case 3:
                if (newAnchors.hasAnchorFor(SheetValue.Expanded)) {
                    oldTarget = SheetValue.Expanded;
                } else if (newAnchors.hasAnchorFor(SheetValue.PartiallyExpanded)) {
                    oldTarget = SheetValue.PartiallyExpanded;
                } else if (newAnchors.hasAnchorFor(SheetValue.Hidden)) {
                    oldTarget = SheetValue.Hidden;
                }
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return TuplesKt.to(newAnchors, oldTarget);
    }

    static final Unit StandardBottomSheet_w7I5h1o$lambda$13$lambda$12$lambda$11(SheetState $state, float $layoutHeight, float $peekHeightPx, float $sheetHeight, DraggableAnchorsConfig $this$DraggableAnchors) {
        if (!$state.getSkipPartiallyExpanded()) {
            $this$DraggableAnchors.at(SheetValue.PartiallyExpanded, $layoutHeight - $peekHeightPx);
        }
        if (!($sheetHeight == $peekHeightPx)) {
            $this$DraggableAnchors.at(SheetValue.Expanded, Math.max($layoutHeight - $sheetHeight, 0.0f));
        }
        if (!$state.getSkipHiddenState()) {
            $this$DraggableAnchors.at(SheetValue.Hidden, $layoutHeight);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:102:0x023b  */
    /* JADX WARN: Code duplicated, block: B:85:0x0150  */
    /* JADX WARN: Code duplicated, block: B:88:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:91:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:92:0x01b3  */
    public static final void BottomSheetScaffoldLayout(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final Function0<Float> function0, final SheetState sheetState, Composer $composer, final int $changed) {
        int i;
        MultiContentMeasurePolicy measurePolicy$iv;
        int $changed$iv;
        Object it$iv$iv;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function1;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        Composer $composer2 = $composer.startRestartGroup(-1217723575);
        ComposerKt.sourceInformation($composer2, "C(BottomSheetScaffoldLayout)N(topBar,body,bottomSheet,snackbarHost,sheetOffset,sheetState)416@20022L1942,414@19911L2053:BottomSheetScaffold.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function5) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changed(sheetState) ? 131072 : 65536;
        }
        int i2 = 1;
        if (!$composer2.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1217723575, $dirty, -1, "androidx.compose.material3.BottomSheetScaffoldLayout (BottomSheetScaffold.kt:413)");
            }
            Function2[] function2Arr = new Function2[4];
            function2Arr[0] = function2 == null ? ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.m2297getLambda$788244078$material3() : function2;
            function2Arr[1] = function3;
            function2Arr[2] = function4;
            function2Arr[3] = function5;
            List listListOf = CollectionsKt.listOf((Object[]) function2Arr);
            ComposerKt.sourceInformationMarkerStart($composer2, -2001235553, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            boolean invalid$iv = ((458752 & $dirty) == 131072) | ((57344 & $dirty) == 16384);
            Object it$iv = $composer2.rememberedValue();
            if (!invalid$iv) {
                i = 0;
                if (it$iv != Composer.INSTANCE.getEmpty()) {
                }
                measurePolicy$iv = (MultiContentMeasurePolicy) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $changed$iv = i;
                ComposerKt.sourceInformationMarkerStart($composer2, 1399185516, "CC(Layout)P(!1,2)168@6883L62,165@6769L182:Layout.kt#80mrfh");
                Modifier modifier$iv = Modifier.INSTANCE;
                Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(listListOf);
                ComposerKt.sourceInformationMarkerStart($composer2, -290764973, "CC(remember):Layout.kt#9igjgp");
                if (((($changed$iv & 896) ^ 384) > 256 || !$composer2.changed(measurePolicy$iv)) && ($changed$iv & 384) != 256) {
                }
                it$iv$iv = $composer2.rememberedValue();
                if (i2 == 0 || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv$iv = MultiContentMeasurePolicyKt.createMeasurePolicy(measurePolicy$iv);
                    $composer2.updateRememberedValue(value$iv$iv);
                    it$iv$iv = value$iv$iv;
                }
                MeasurePolicy measurePolicy$iv$iv = (MeasurePolicy) it$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                int $changed$iv$iv = $changed$iv & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, i);
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
                    function1 = constructor;
                    $composer2.createNode(function1);
                } else {
                    function1 = constructor;
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts.invoke($composer2, Integer.valueOf(($changed$iv$iv$iv >> 6) & 14));
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                i = 0;
            }
            Object value$iv = (MultiContentMeasurePolicy) new BottomSheetScaffoldKt$BottomSheetScaffoldLayout$1$1(sheetState, function0);
            $composer2.updateRememberedValue(value$iv);
            it$iv = value$iv;
            measurePolicy$iv = (MultiContentMeasurePolicy) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $changed$iv = i;
            ComposerKt.sourceInformationMarkerStart($composer2, 1399185516, "CC(Layout)P(!1,2)168@6883L62,165@6769L182:Layout.kt#80mrfh");
            Modifier modifier$iv2 = Modifier.INSTANCE;
            Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts2 = LayoutKt.combineAsVirtualLayouts(listListOf);
            ComposerKt.sourceInformationMarkerStart($composer2, -290764973, "CC(remember):Layout.kt#9igjgp");
            i2 = (($changed$iv & 896) ^ 384) > 256 ? i : i;
            it$iv$iv = $composer2.rememberedValue();
            if (i2 == 0) {
                Object value$iv$iv2 = MultiContentMeasurePolicyKt.createMeasurePolicy(measurePolicy$iv);
                $composer2.updateRememberedValue(value$iv$iv2);
                it$iv$iv = value$iv$iv2;
            } else {
                Object value$iv$iv3 = MultiContentMeasurePolicyKt.createMeasurePolicy(measurePolicy$iv);
                $composer2.updateRememberedValue(value$iv$iv3);
                it$iv$iv = value$iv$iv3;
            }
            MeasurePolicy measurePolicy$iv$iv2 = (MeasurePolicy) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            int $changed$iv$iv2 = $changed$iv & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, i);
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
                function1 = constructor;
                $composer2.createNode(function1);
            } else {
                function1 = constructor;
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            function2CombineAsVirtualLayouts2.invoke($composer2, Integer.valueOf(($changed$iv$iv$iv2 >> 6) & 14));
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomSheetScaffoldKt.BottomSheetScaffoldLayout$lambda$16(function2, function3, function4, function5, function0, sheetState, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final Modifier verticalScaleUp(Modifier $this$verticalScaleUp, final SheetState state) {
        return GraphicsLayerModifierKt.graphicsLayer($this$verticalScaleUp, new Function1() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BottomSheetScaffoldKt.verticalScaleUp$lambda$17(state, (GraphicsLayerScope) obj);
            }
        });
    }

    static final Unit verticalScaleUp$lambda$17(SheetState $state, GraphicsLayerScope $this$graphicsLayer) {
        float fIntBitsToFloat;
        float offset = $state.getAnchoredDraggableState$material3().getOffset();
        float anchor = $state.getAnchoredDraggableState$material3().getAnchors().minAnchor();
        float overflow = offset < anchor ? anchor - offset : 0.0f;
        if (overflow > 0.0f) {
            long arg0$iv = $this$graphicsLayer.getSize();
            int bits$iv$iv$iv = (int) (arg0$iv & 4294967295L);
            float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv) + overflow;
            long arg0$iv2 = $this$graphicsLayer.getSize();
            int bits$iv$iv$iv2 = (int) (4294967295L & arg0$iv2);
            fIntBitsToFloat = fIntBitsToFloat2 / Float.intBitsToFloat(bits$iv$iv$iv2);
        } else {
            fIntBitsToFloat = 1.0f;
        }
        $this$graphicsLayer.setScaleY(fIntBitsToFloat);
        $this$graphicsLayer.mo5298setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, 0.0f));
        return Unit.INSTANCE;
    }

    public static final Modifier verticalScaleDown(Modifier $this$verticalScaleDown, final SheetState state) {
        return GraphicsLayerModifierKt.graphicsLayer($this$verticalScaleDown, new Function1() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BottomSheetScaffoldKt.verticalScaleDown$lambda$18(state, (GraphicsLayerScope) obj);
            }
        });
    }

    static final Unit verticalScaleDown$lambda$18(SheetState $state, GraphicsLayerScope $this$graphicsLayer) {
        float offset = $state.getAnchoredDraggableState$material3().getOffset();
        float anchor = $state.getAnchoredDraggableState$material3().getAnchors().minAnchor();
        float overflow = offset < anchor ? anchor - offset : 0.0f;
        float fIntBitsToFloat = 1.0f;
        if (overflow > 0.0f) {
            long arg0$iv = $this$graphicsLayer.getSize();
            int bits$iv$iv$iv = (int) (arg0$iv & 4294967295L);
            float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv) + overflow;
            long arg0$iv2 = $this$graphicsLayer.getSize();
            int bits$iv$iv$iv2 = (int) (4294967295L & arg0$iv2);
            fIntBitsToFloat = 1.0f / (fIntBitsToFloat2 / Float.intBitsToFloat(bits$iv$iv$iv2));
        }
        $this$graphicsLayer.setScaleY(fIntBitsToFloat);
        $this$graphicsLayer.mo5298setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, 0.0f));
        return Unit.INSTANCE;
    }
}
