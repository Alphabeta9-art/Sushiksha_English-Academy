package androidx.compose.material;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.IndicationNodeFactory;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.foundation.selection.SelectableKt;
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
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import com.cloudinary.metadata.MetadataField;
import com.facebook.common.util.UriUtil;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: NavigationRail.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000x\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u007f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2 \b\u0002\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000f2\u001c\u0010\u0010\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001aw\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2 \b\u0002\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000f2\u001c\u0010\u0010\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u008d\u0001\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00192\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\u00010\u0019¢\u0006\u0002\b\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u001b\u001a\u00020\u00172\u0015\b\u0002\u0010\u001c\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0019¢\u0006\u0002\b\u000e2\b\b\u0002\u0010\u001d\u001a\u00020\u00172\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020\u00072\b\b\u0002\u0010!\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\"\u0010#\u001aO\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00172&\u0010\u0010\u001a\"\u0012\u0013\u0012\u00110'¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\u000eH\u0003¢\u0006\u0004\b+\u0010,\u001a?\u0010-\u001a\u00020\u00012\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\u00010\u0019¢\u0006\u0002\b\u000e2\u0013\u0010\u001c\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0019¢\u0006\u0002\b\u000e2\b\b\u0001\u0010.\u001a\u00020'H\u0003¢\u0006\u0002\u0010/\u001a#\u00100\u001a\u000201*\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0002¢\u0006\u0004\b7\u00108\u001a5\u00109\u001a\u000201*\u0002022\u0006\u0010:\u001a\u0002042\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\b\b\u0001\u0010.\u001a\u00020'H\u0002¢\u0006\u0004\b;\u0010<\"\u0014\u0010=\u001a\b\u0012\u0004\u0012\u00020'0>X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010?\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010@\"\u0010\u0010A\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010@\"\u0010\u0010B\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010@\"\u0010\u0010C\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010@\"\u0010\u0010D\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010@\"\u0010\u0010E\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010@\"\u000e\u0010F\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006G²\u0006\n\u0010*\u001a\u00020'X\u008a\u0084\u0002"}, d2 = {"NavigationRail", "", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "modifier", "Landroidx/compose/ui/Modifier;", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "contentColor", "elevation", "Landroidx/compose/ui/unit/Dp;", "header", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", UriUtil.LOCAL_CONTENT_SCHEME, "NavigationRail-afqeVBk", "(Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/Modifier;JJFLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "NavigationRail-HsRjFd4", "(Landroidx/compose/ui/Modifier;JJFLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "NavigationRailItem", "selected", "", "onClick", "Lkotlin/Function0;", "icon", "enabled", MetadataField.LABEL, "alwaysShowLabel", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "selectedContentColor", "unselectedContentColor", "NavigationRailItem-0S3VyRs", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;JJLandroidx/compose/runtime/Composer;II)V", "NavigationRailTransition", "activeColor", "inactiveColor", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "animationProgress", "NavigationRailTransition-Klgx-Pg", "(JJZLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "NavigationRailItemBaselineLayout", "iconPositionAnimationProgress", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;FLandroidx/compose/runtime/Composer;I)V", "placeIcon", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "iconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "placeIcon-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;J)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndIcon", "labelPlaceable", "placeLabelAndIcon-DIyivk0", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JF)Landroidx/compose/ui/layout/MeasureResult;", "NavigationRailAnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "NavigationRailItemSize", "F", "NavigationRailItemCompactSize", "NavigationRailPadding", "HeaderPadding", "ItemLabelBaselineBottomOffset", "ItemIconTopOffset", "ZeroInsets", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class NavigationRailKt {
    private static final TweenSpec<Float> NavigationRailAnimationSpec = new TweenSpec<>(300, 0, EasingKt.getFastOutSlowInEasing(), 2, null);
    private static final float NavigationRailItemSize = Dp.m7905constructorimpl(72);
    private static final float NavigationRailItemCompactSize = Dp.m7905constructorimpl(56);
    private static final float NavigationRailPadding = Dp.m7905constructorimpl(8);
    private static final float HeaderPadding = Dp.m7905constructorimpl(8);
    private static final float ItemLabelBaselineBottomOffset = Dp.m7905constructorimpl(16);
    private static final float ItemIconTopOffset = Dp.m7905constructorimpl(14);
    private static final WindowInsets ZeroInsets = WindowInsetsKt.m891WindowInsetsa9UjIt4$default(Dp.m7905constructorimpl(0), 0.0f, 0.0f, 0.0f, 14, null);

    static final Unit NavigationRailItemBaselineLayout$lambda$2(Function2 function2, Function2 function3, float f, int i, Composer composer, int i2) {
        NavigationRailItemBaselineLayout(function2, function3, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit NavigationRailItem_0S3VyRs$lambda$2(boolean z, Function0 function0, Function2 function2, Modifier modifier, boolean z2, Function2 function3, boolean z3, MutableInteractionSource mutableInteractionSource, long j, long j2, int i, int i2, Composer composer, int i3) {
        m1870NavigationRailItem0S3VyRs(z, function0, function2, modifier, z2, function3, z3, mutableInteractionSource, j, j2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit NavigationRailTransition_Klgx_Pg$lambda$2(long j, long j2, boolean z, Function3 function3, int i, Composer composer, int i2) {
        m1871NavigationRailTransitionKlgxPg(j, j2, z, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit NavigationRail_HsRjFd4$lambda$0(Modifier modifier, long j, long j2, float f, Function3 function3, Function3 function4, int i, int i2, Composer composer, int i3) {
        m1868NavigationRailHsRjFd4(modifier, j, j2, f, function3, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit NavigationRail_afqeVBk$lambda$1(WindowInsets windowInsets, Modifier modifier, long j, long j2, float f, Function3 function3, Function3 function4, int i, int i2, Composer composer, int i3) {
        m1869NavigationRailafqeVBk(windowInsets, modifier, j, j2, f, function3, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: NavigationRail-afqeVBk, reason: not valid java name */
    public static final void m1869NavigationRailafqeVBk(final WindowInsets windowInsets, Modifier modifier, long j, long j2, float f, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        long j3;
        long j4;
        float f2;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function5;
        Composer $composer2;
        final long j5;
        final long j6;
        final Modifier modifier2;
        final float f3;
        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function6;
        Modifier.Companion modifier3;
        long jM1769getSurface0d7_KjU;
        long jM1783contentColorForek8zF_U;
        float fM1866getElevationD9Ej5fM;
        Modifier modifier4;
        long j7;
        float f4;
        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function7;
        int i2;
        long j8;
        Composer $composer3 = $composer.startRestartGroup(-171916405);
        ComposerKt.sourceInformation($composer3, "C(NavigationRail)N(windowInsets,modifier,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation:c#ui.unit.Dp,header,content)114@5148L442,109@5003L587:NavigationRail.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(windowInsets) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j3 = j;
                int i4 = $composer3.changed(j3) ? 256 : 128;
                $dirty |= i4;
            } else {
                j3 = j;
            }
            $dirty |= i4;
        } else {
            j3 = j;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j4 = j2;
                int i5 = $composer3.changed(j4) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                j4 = j2;
            }
            $dirty |= i5;
        } else {
            j4 = j2;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            f2 = f;
        } else if (($changed & 24576) == 0) {
            f2 = f;
            $dirty |= $composer3.changed(f2) ? 16384 : 8192;
        } else {
            f2 = f;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function5 = function3;
        } else if ((196608 & $changed) == 0) {
            function5 = function3;
            $dirty |= $composer3.changedInstance(function5) ? 131072 : 65536;
        } else {
            function5 = function3;
        }
        if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 1048576 : 524288;
        }
        if ($composer3.shouldExecute(($dirty & 599187) != 599186, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "103@4758L6,104@4800L32");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                f4 = f2;
                function7 = function5;
                i2 = -171916405;
                j7 = j3;
                j8 = j4;
                modifier4 = modifier;
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if ((i & 4) == 0) {
                    jM1769getSurface0d7_KjU = j3;
                } else {
                    jM1769getSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer3, 6).m1769getSurface0d7_KjU();
                    $dirty &= -897;
                }
                if ((i & 8) == 0) {
                    jM1783contentColorForek8zF_U = j4;
                } else {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1769getSurface0d7_KjU, $composer3, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                }
                if (i6 == 0) {
                    fM1866getElevationD9Ej5fM = f2;
                } else {
                    fM1866getElevationD9Ej5fM = NavigationRailDefaults.INSTANCE.m1866getElevationD9Ej5fM();
                }
                if (i7 == 0) {
                    modifier4 = modifier3;
                    j7 = jM1769getSurface0d7_KjU;
                    f4 = fM1866getElevationD9Ej5fM;
                    function7 = function5;
                    i2 = -171916405;
                    j8 = jM1783contentColorForek8zF_U;
                } else {
                    modifier4 = modifier3;
                    function7 = null;
                    j7 = jM1769getSurface0d7_KjU;
                    f4 = fM1866getElevationD9Ej5fM;
                    i2 = -171916405;
                    j8 = jM1783contentColorForek8zF_U;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material.NavigationRail (NavigationRail.kt:108)");
            }
            $composer2 = $composer3;
            SurfaceKt.m1954SurfaceFjzlyU(modifier4, null, j7, j8, null, f4, ComposableLambdaKt.rememberComposableLambda(366031815, true, new Function2() { // from class: androidx.compose.material.NavigationRailKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationRailKt.NavigationRail_afqeVBk$lambda$0(windowInsets, function7, function4, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer2, (($dirty >> 3) & 14) | 1572864 | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 458752), 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function6 = function7;
            modifier2 = modifier4;
            j5 = j7;
            j6 = j8;
            f3 = f4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            j5 = j3;
            j6 = j4;
            modifier2 = modifier;
            f3 = f2;
            function6 = function5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.NavigationRailKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationRailKt.NavigationRail_afqeVBk$lambda$1(windowInsets, modifier2, j5, j6, f3, function6, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0159  */
    /* JADX WARN: Code duplicated, block: B:28:0x017d  */
    /* JADX WARN: Code duplicated, block: B:31:0x01ae  */
    static final Unit NavigationRail_afqeVBk$lambda$0(WindowInsets $windowInsets, Function3 $header, Function3 $content, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        int compositeKeyHash$iv$iv;
        int $changed2;
        ColumnScopeInstance columnScopeInstance;
        ComposerKt.sourceInformation($composer, "C115@5158L426:NavigationRail.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(366031815, $changed, -1, "androidx.compose.material.NavigationRail.<anonymous> (NavigationRail.kt:115)");
            }
            Modifier modifier$iv = SelectableGroupKt.selectableGroup(PaddingKt.m821paddingVpY3zN4$default(WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null), $windowInsets), 0.0f, NavigationRailPadding, 1, null));
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
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
                ComposerKt.sourceInformationMarkerStart($composer, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                $changed2 = ((384 >> 6) & 112) | 6;
                columnScopeInstance = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, -1703433687, "C126@5565L9:NavigationRail.kt#jmzs0o");
                if ($header != null) {
                    $composer.startReplaceGroup(-1703415150);
                    ComposerKt.sourceInformation($composer, "123@5475L8,124@5500L38");
                    $header.invoke(columnScopeInstance, $composer, Integer.valueOf($changed2 & 14));
                    SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, HeaderPadding), $composer, 6);
                } else {
                    $composer.startReplaceGroup(-1708832431);
                }
                $composer.endReplaceGroup();
                $content.invoke(columnScopeInstance, $composer, Integer.valueOf($changed2 & 14));
                ComposerKt.sourceInformationMarkerEnd($composer);
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
            ComposerKt.sourceInformationMarkerStart($composer, -384672921, "C89@4556L9:Column.kt#2w3rfo");
            $changed2 = ((384 >> 6) & 112) | 6;
            columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -1703433687, "C126@5565L9:NavigationRail.kt#jmzs0o");
            if ($header != null) {
                $composer.startReplaceGroup(-1703415150);
                ComposerKt.sourceInformation($composer, "123@5475L8,124@5500L38");
                $header.invoke(columnScopeInstance, $composer, Integer.valueOf($changed2 & 14));
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, HeaderPadding), $composer, 6);
            } else {
                $composer.startReplaceGroup(-1708832431);
            }
            $composer.endReplaceGroup();
            $content.invoke(columnScopeInstance, $composer, Integer.valueOf($changed2 & 14));
            ComposerKt.sourceInformationMarkerEnd($composer);
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

    /* JADX INFO: renamed from: NavigationRail-HsRjFd4, reason: not valid java name */
    public static final void m1868NavigationRailHsRjFd4(Modifier modifier, long j, long j2, float f, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long jM1769getSurface0d7_KjU;
        long j3;
        float fM1866getElevationD9Ej5fM;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function5;
        Composer $composer2;
        final long j4;
        final float f2;
        final Modifier modifier3;
        final long j5;
        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function6;
        Modifier.Companion modifier4;
        float f3;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function7;
        int i2;
        Modifier modifier5;
        long j6;
        long j7;
        Composer $composer3 = $composer.startRestartGroup(-2116369751);
        ComposerKt.sourceInformation($composer3, "C(NavigationRail)N(modifier,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation:c#ui.unit.Dp,header,content)173@7644L95:NavigationRail.kt#jmzs0o");
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
                jM1769getSurface0d7_KjU = j;
                int i4 = $composer3.changed(jM1769getSurface0d7_KjU) ? 32 : 16;
                $dirty |= i4;
            } else {
                jM1769getSurface0d7_KjU = j;
            }
            $dirty |= i4;
        } else {
            jM1769getSurface0d7_KjU = j;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j3 = j2;
                int i5 = $composer3.changed(j3) ? 256 : 128;
                $dirty |= i5;
            } else {
                j3 = j2;
            }
            $dirty |= i5;
        } else {
            j3 = j2;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            fM1866getElevationD9Ej5fM = f;
        } else if (($changed & 3072) == 0) {
            fM1866getElevationD9Ej5fM = f;
            $dirty |= $composer3.changed(fM1866getElevationD9Ej5fM) ? 2048 : 1024;
        } else {
            fM1866getElevationD9Ej5fM = f;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty |= 24576;
            function5 = function3;
        } else if (($changed & 24576) == 0) {
            function5 = function3;
            $dirty |= $composer3.changedInstance(function5) ? 16384 : 8192;
        } else {
            function5 = function3;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 131072 : 65536;
        }
        if (!$composer3.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            j4 = jM1769getSurface0d7_KjU;
            f2 = fM1866getElevationD9Ej5fM;
            modifier3 = modifier2;
            j5 = j3;
            function6 = function5;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "167@7399L6,168@7441L32");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                f3 = fM1866getElevationD9Ej5fM;
                function7 = function5;
                i2 = -2116369751;
                modifier5 = modifier2;
                j6 = jM1769getSurface0d7_KjU;
                j7 = j3;
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 2) != 0) {
                    $dirty &= -113;
                    jM1769getSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer3, 6).m1769getSurface0d7_KjU();
                }
                if ((i & 4) != 0) {
                    long jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1769getSurface0d7_KjU, $composer3, ($dirty >> 3) & 14);
                    $dirty &= -897;
                    j3 = jM1783contentColorForek8zF_U;
                }
                if (i6 != 0) {
                    fM1866getElevationD9Ej5fM = NavigationRailDefaults.INSTANCE.m1866getElevationD9Ej5fM();
                }
                if (i7 == 0) {
                    f3 = fM1866getElevationD9Ej5fM;
                    function7 = function5;
                    i2 = -2116369751;
                    modifier5 = modifier4;
                    j6 = jM1769getSurface0d7_KjU;
                    j7 = j3;
                } else {
                    function7 = null;
                    j6 = jM1769getSurface0d7_KjU;
                    f3 = fM1866getElevationD9Ej5fM;
                    i2 = -2116369751;
                    modifier5 = modifier4;
                    j7 = j3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material.NavigationRail (NavigationRail.kt:172)");
            }
            $composer2 = $composer3;
            m1869NavigationRailafqeVBk(ZeroInsets, modifier5, j6, j7, f3, function7, function4, $composer2, (($dirty << 3) & 112) | 6 | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty << 3) & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            j5 = j7;
            f2 = f3;
            function6 = function7;
            j4 = j6;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.NavigationRailKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationRailKt.NavigationRail_HsRjFd4$lambda$0(modifier3, j4, j5, f2, function6, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:164:0x03dc  */
    /* JADX INFO: renamed from: NavigationRailItem-0S3VyRs, reason: not valid java name */
    public static final void m1870NavigationRailItem0S3VyRs(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function3, boolean alwaysShowLabel, MutableInteractionSource interactionSource, long j, long j2, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function1;
        Modifier modifier2;
        boolean enabled2;
        final Function2<? super Composer, ? super Integer, Unit> function4;
        final boolean alwaysShowLabel2;
        int $dirty;
        final Modifier modifier3;
        final boolean enabled3;
        final Function2<? super Composer, ? super Integer, Unit> function5;
        final boolean alwaysShowLabel3;
        final MutableInteractionSource interactionSource2;
        final long j3;
        final long j4;
        MutableInteractionSource interactionSource3;
        long jM1765getPrimary0d7_KjU;
        long jM5095copywmQWz5c;
        int $dirty2;
        final Function2 styledLabel;
        Function0<ComposeUiNode> function6;
        Composer $composer$iv;
        int $dirty3;
        Composer $composer2 = $composer.startRestartGroup(83562179);
        ComposerKt.sourceInformation($composer2, "C(NavigationRailItem)N(selected,onClick,icon,modifier,enabled,label,alwaysShowLabel,interactionSource,selectedContentColor:c#ui.graphics.Color,unselectedContentColor:c#ui.graphics.Color)227@10507L773:NavigationRail.kt#jmzs0o");
        int $dirty4 = $changed;
        if (($changed & 6) == 0) {
            $dirty4 |= $composer2.changed(selected) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            function1 = function0;
            $dirty4 |= $composer2.changedInstance(function1) ? 32 : 16;
        } else {
            function1 = function0;
        }
        if (($changed & 384) == 0) {
            $dirty4 |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty4 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer2.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty4 |= 24576;
            enabled2 = enabled;
        } else if (($changed & 24576) == 0) {
            enabled2 = enabled;
            $dirty4 |= $composer2.changed(enabled2) ? 16384 : 8192;
        } else {
            enabled2 = enabled;
        }
        int i4 = i & 32;
        if (i4 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function4 = function3;
        } else if ((196608 & $changed) == 0) {
            function4 = function3;
            $dirty4 |= $composer2.changedInstance(function4) ? 131072 : 65536;
        } else {
            function4 = function3;
        }
        int i5 = i & 64;
        if (i5 != 0) {
            $dirty4 |= 1572864;
            alwaysShowLabel2 = alwaysShowLabel;
        } else if ((1572864 & $changed) == 0) {
            alwaysShowLabel2 = alwaysShowLabel;
            $dirty4 |= $composer2.changed(alwaysShowLabel2) ? 1048576 : 524288;
        } else {
            alwaysShowLabel2 = alwaysShowLabel;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty4 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty4 |= $composer2.changed(interactionSource) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0) {
                $dirty3 = $dirty4;
                int i7 = $composer2.changed(j) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                $dirty = $dirty3 | i7;
            } else {
                $dirty3 = $dirty4;
            }
            $dirty = $dirty3 | i7;
        } else {
            $dirty = $dirty4;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= ((i & 512) == 0 && $composer2.changed(j2)) ? 536870912 : 268435456;
        }
        if ($composer2.shouldExecute(($dirty & 306783379) != 306783378, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "209@9518L6,210@9588L7,210@9622L6");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 256) != 0) {
                    $dirty &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty &= -1879048193;
                }
                interactionSource3 = interactionSource;
                jM1765getPrimary0d7_KjU = j;
                jM5095copywmQWz5c = j2;
                $dirty2 = $dirty;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    enabled2 = true;
                }
                if (i4 != 0) {
                    function4 = null;
                }
                if (i5 != 0) {
                    alwaysShowLabel2 = true;
                }
                if (i6 == 0) {
                    interactionSource3 = interactionSource;
                } else {
                    interactionSource3 = null;
                }
                if ((i & 256) == 0) {
                    jM1765getPrimary0d7_KjU = j;
                } else {
                    jM1765getPrimary0d7_KjU = MaterialTheme.INSTANCE.getColors($composer2, 6).m1765getPrimary0d7_KjU();
                    $dirty &= -234881025;
                }
                if ((i & 512) == 0) {
                    jM5095copywmQWz5c = j2;
                    $dirty2 = $dirty;
                } else {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer2.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    long jM5107unboximpl = ((Color) objConsume).m5107unboximpl();
                    jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM5107unboximpl, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5107unboximpl) : ContentAlpha.INSTANCE.getMedium($composer2, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM5107unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5107unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5107unboximpl) : 0.0f);
                    $dirty2 = $dirty & (-1879048193);
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(83562179, $dirty2, -1, "androidx.compose.material.NavigationRailItem (NavigationRail.kt:211)");
            }
            if (function4 == null) {
                $composer2.startReplaceGroup(-1679616946);
                $composer2.endReplaceGroup();
                styledLabel = null;
            } else {
                $composer2.startReplaceGroup(-1679616945);
                ComposerKt.sourceInformation($composer2, "*214@9729L168");
                Function2 function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-674640273, true, new Function2() { // from class: androidx.compose.material.NavigationRailKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationRailKt.NavigationRailItem_0S3VyRs$lambda$0$0(function4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer2, 54);
                $composer2.endReplaceGroup();
                styledLabel = function2RememberComposableLambda;
            }
            float itemSize = function4 == null ? NavigationRailItemCompactSize : NavigationRailItemSize;
            IndicationNodeFactory ripple = RippleKt.m1907rippleH2RKhps$default(false, 0.0f, jM1765getPrimary0d7_KjU, 2, null);
            MutableInteractionSource interactionSource4 = interactionSource3;
            Modifier modifier$iv = SizeKt.m865size3ABfNKs(SelectableKt.m1089selectableO2vRcR0(modifier2, selected, interactionSource4, ripple, enabled2, Role.m7092boximpl(Role.INSTANCE.m7106getTabo7Vup1c()), function1), itemSize);
            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            int $dirty5 = $dirty2;
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            Modifier modifier4 = modifier2;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function6 = constructor;
                $composer2.createNode(function6);
            } else {
                function6 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                $composer$iv = $composer2;
            } else {
                $composer$iv = $composer2;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = ($changed$iv$iv$iv >> 6) & 14;
                Composer $composer$iv2 = $composer$iv;
                ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i9 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv2, 415492902, "C240@10971L303,240@10890L384:NavigationRail.kt#jmzs0o");
                m1871NavigationRailTransitionKlgxPg(jM1765getPrimary0d7_KjU, jM5095copywmQWz5c, selected, ComposableLambdaKt.rememberComposableLambda(317431928, true, new Function3() { // from class: androidx.compose.material.NavigationRailKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return NavigationRailKt.NavigationRailItem_0S3VyRs$lambda$1$0(alwaysShowLabel2, function2, styledLabel, ((Float) obj).floatValue(), (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer$iv2, 54), $composer$iv2, (($dirty5 >> 24) & 14) | 3072 | (($dirty5 >> 24) & 112) | (($dirty5 << 6) & 896));
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                enabled3 = enabled2;
                function5 = function4;
                alwaysShowLabel3 = alwaysShowLabel2;
                j3 = jM1765getPrimary0d7_KjU;
                j4 = jM5095copywmQWz5c;
                interactionSource2 = interactionSource4;
                modifier3 = modifier4;
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i10 = ($changed$iv$iv$iv >> 6) & 14;
            Composer $composer$iv3 = $composer$iv;
            ComposerKt.sourceInformationMarkerStart($composer$iv3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i11 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer$iv3, 415492902, "C240@10971L303,240@10890L384:NavigationRail.kt#jmzs0o");
            m1871NavigationRailTransitionKlgxPg(jM1765getPrimary0d7_KjU, jM5095copywmQWz5c, selected, ComposableLambdaKt.rememberComposableLambda(317431928, true, new Function3() { // from class: androidx.compose.material.NavigationRailKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return NavigationRailKt.NavigationRailItem_0S3VyRs$lambda$1$0(alwaysShowLabel2, function2, styledLabel, ((Float) obj).floatValue(), (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer$iv3, 54), $composer$iv3, (($dirty5 >> 24) & 14) | 3072 | (($dirty5 >> 24) & 112) | (($dirty5 << 6) & 896));
            ComposerKt.sourceInformationMarkerEnd($composer$iv3);
            ComposerKt.sourceInformationMarkerEnd($composer$iv3);
            $composer$iv.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            enabled3 = enabled2;
            function5 = function4;
            alwaysShowLabel3 = alwaysShowLabel2;
            j3 = jM1765getPrimary0d7_KjU;
            j4 = jM5095copywmQWz5c;
            interactionSource2 = interactionSource4;
            modifier3 = modifier4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enabled3 = enabled2;
            function5 = function4;
            alwaysShowLabel3 = alwaysShowLabel2;
            interactionSource2 = interactionSource;
            j3 = j;
            j4 = j2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.NavigationRailKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationRailKt.NavigationRailItem_0S3VyRs$lambda$2(selected, function0, function2, modifier3, enabled3, function5, alwaysShowLabel3, interactionSource2, j3, j4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit NavigationRailItem_0S3VyRs$lambda$0$0(Function2 $label, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C215@9773L10,216@9843L40:NavigationRail.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-674640273, $changed, -1, "androidx.compose.material.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:215)");
            }
            TextStyle style = TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, 6).getCaption(), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m7758getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null);
            TextKt.ProvideTextStyle(style, $label, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit NavigationRailItem_0S3VyRs$lambda$1$0(boolean $alwaysShowLabel, Function2 $icon, Function2 $styledLabel, float progress, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(progress)244@11084L180:NavigationRail.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(progress) ? 4 : 2;
        }
        if (!$composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(317431928, $dirty, -1, "androidx.compose.material.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:242)");
            }
            float animationProgress = $alwaysShowLabel ? 1.0f : progress;
            NavigationRailItemBaselineLayout($icon, $styledLabel, animationProgress, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: NavigationRailTransition-Klgx-Pg, reason: not valid java name */
    private static final void m1871NavigationRailTransitionKlgxPg(final long j, final long j2, final boolean selected, final Function3<? super Float, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(140356545);
        ComposerKt.sourceInformation($composer2, "C(NavigationRailTransition)N(activeColor:c#ui.graphics.Color,inactiveColor:c#ui.graphics.Color,selected,content)287@12657L139,297@13010L42,294@12871L181:NavigationRail.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(j) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(j2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(selected) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 2048 : 1024;
        }
        if ($composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(140356545, $dirty, -1, "androidx.compose.material.NavigationRailTransition (NavigationRail.kt:285)");
            }
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(selected ? 1065353216 : 0, NavigationRailAnimationSpec, 0.0f, null, null, $composer2, 48, 28);
            long color = ColorKt.m5148lerpjxsXWHM(j2, j, NavigationRailTransition_Klgx_Pg$lambda$0(stateAnimateFloatAsState));
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(Color.m5095copywmQWz5c(color, (14 & 1) != 0 ? Color.m5099getAlphaimpl(color) : 1.0f, (14 & 2) != 0 ? Color.m5103getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(color) : 0.0f))), ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m5099getAlphaimpl(color)))}, ComposableLambdaKt.rememberComposableLambda(1330097921, true, new Function2() { // from class: androidx.compose.material.NavigationRailKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationRailKt.NavigationRailTransition_Klgx_Pg$lambda$1(function3, stateAnimateFloatAsState, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.NavigationRailKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationRailKt.NavigationRailTransition_Klgx_Pg$lambda$2(j, j2, selected, function3, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float NavigationRailTransition_Klgx_Pg$lambda$0(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    static final Unit NavigationRailTransition_Klgx_Pg$lambda$1(Function3 $content, State $animationProgress$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C298@13020L26:NavigationRail.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1330097921, $changed, -1, "androidx.compose.material.NavigationRailTransition.<anonymous> (NavigationRail.kt:298)");
            }
            $content.invoke(Float.valueOf(NavigationRailTransition_Klgx_Pg$lambda$0($animationProgress$delegate)), $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final void NavigationRailItemBaselineLayout(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final float iconPositionAnimationProgress, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function4;
        Composer $composer$iv;
        Composer $composer3 = $composer.startRestartGroup(-1276874318);
        ComposerKt.sourceInformation($composer3, "C(NavigationRailItemBaselineLayout)N(icon,label,iconPositionAnimationProgress)323@13918L928,318@13720L1126:NavigationRail.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(iconPositionAnimationProgress) ? 256 : 128;
        }
        if ($composer3.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1276874318, $dirty, -1, "androidx.compose.material.NavigationRailItemBaselineLayout (NavigationRail.kt:317)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1284440530, "CC(remember):NavigationRail.kt#9igjgp");
            boolean invalid$iv = (($dirty & 112) == 32) | (($dirty & 896) == 256);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (MeasurePolicy) new MeasurePolicy() { // from class: androidx.compose.material.NavigationRailKt$NavigationRailItemBaselineLayout$2$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo42measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, long j) {
                        Placeable labelPlaceable;
                        List<? extends Measurable> list2 = list;
                        int index$iv$iv = 0;
                        int size = list2.size();
                        while (index$iv$iv < size) {
                            Object item$iv$iv = list2.get(index$iv$iv);
                            Measurable it = (Measurable) item$iv$iv;
                            List<? extends Measurable> list3 = list2;
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "icon")) {
                                Placeable iconPlaceable = ((Measurable) item$iv$iv).mo6541measureBRTryo0(j);
                                if (function3 != null) {
                                    int index$iv$iv2 = 0;
                                    int size2 = list.size();
                                    while (true) {
                                        if (index$iv$iv2 >= size2) {
                                            ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                                            throw new KotlinNothingValueException();
                                        }
                                        Object item$iv$iv2 = list.get(index$iv$iv2);
                                        Measurable it2 = (Measurable) item$iv$iv2;
                                        if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), MetadataField.LABEL)) {
                                            j = j;
                                            labelPlaceable = ((Measurable) item$iv$iv2).mo6541measureBRTryo0(Constraints.m7847copyZbe2FdA(j, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(j) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(j) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(j) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(j) : 0));
                                            break;
                                        }
                                        index$iv$iv2++;
                                    }
                                } else {
                                    labelPlaceable = null;
                                }
                                if (function3 == null) {
                                    return NavigationRailKt.m1874placeIcon3p2s80s($this$Layout, iconPlaceable, j);
                                }
                                Intrinsics.checkNotNull(labelPlaceable);
                                return NavigationRailKt.m1875placeLabelAndIconDIyivk0($this$Layout, labelPlaceable, iconPlaceable, j, iconPositionAnimationProgress);
                            }
                            index$iv$iv++;
                            list2 = list3;
                        }
                        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                        throw new KotlinNothingValueException();
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            MeasurePolicy measurePolicy$iv = (MeasurePolicy) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            Modifier modifier$iv = Modifier.INSTANCE;
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = ((0 << 6) & 896) | 6;
            $composer2 = $composer3;
            int $dirty2 = $dirty;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1177287557, "C319@13737L41:NavigationRail.kt#jmzs0o");
            Modifier modifier$iv2 = LayoutIdKt.layoutId(Modifier.INSTANCE, "icon");
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv2 = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor2;
                $composer3.createNode(function1);
            } else {
                function1 = constructor2;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -491319352, "C319@13770L6:NavigationRail.kt#jmzs0o");
            function2.invoke($composer3, Integer.valueOf($dirty2 & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (function3 != null) {
                $composer3.startReplaceGroup(-1177221249);
                ComposerKt.sourceInformation($composer3, "321@13820L80");
                Modifier modifier$iv3 = AlphaKt.alpha(LayoutIdKt.layoutId(Modifier.INSTANCE, MetadataField.LABEL), iconPositionAnimationProgress);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                int $changed$iv$iv3 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function4 = constructor3;
                    $composer3.createNode(function4);
                } else {
                    function4 = constructor3;
                    $composer3.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                    $composer$iv = $composer3;
                } else {
                    $composer$iv = $composer3;
                    if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
                    Composer $composer$iv2 = $composer$iv;
                    ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i5 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv2, -1391280638, "C321@13891L7:NavigationRail.kt#jmzs0o");
                    function3.invoke($composer$iv2, Integer.valueOf(($dirty2 >> 3) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    $composer$iv.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i6 = ($changed$iv$iv$iv2 >> 6) & 14;
                Composer $composer$iv3 = $composer$iv;
                ComposerKt.sourceInformationMarkerStart($composer$iv3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                int i7 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv3, -1391280638, "C321@13891L7:NavigationRail.kt#jmzs0o");
                function3.invoke($composer$iv3, Integer.valueOf(($dirty2 >> 3) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
            } else {
                $composer3.startReplaceGroup(-1190921017);
            }
            $composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.NavigationRailKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationRailKt.NavigationRailItemBaselineLayout$lambda$2(function2, function3, iconPositionAnimationProgress, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeIcon-3p2s80s, reason: not valid java name */
    public static final MeasureResult m1874placeIcon3p2s80s(MeasureScope $this$placeIcon_u2d3p2s80s, final Placeable iconPlaceable, long j) {
        final int iconX = Math.max(0, (Constraints.m7858getMaxWidthimpl(j) - iconPlaceable.getWidth()) / 2);
        final int iconY = Math.max(0, (Constraints.m7857getMaxHeightimpl(j) - iconPlaceable.getHeight()) / 2);
        return MeasureScope.layout$default($this$placeIcon_u2d3p2s80s, Constraints.m7858getMaxWidthimpl(j), Constraints.m7857getMaxHeightimpl(j), null, new Function1() { // from class: androidx.compose.material.NavigationRailKt$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavigationRailKt.placeIcon_3p2s80s$lambda$0(iconPlaceable, iconX, iconY, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit placeIcon_3p2s80s$lambda$0(Placeable $iconPlaceable, int $iconX, int $iconY, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelative$default($this$layout, $iconPlaceable, $iconX, $iconY, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeLabelAndIcon-DIyivk0, reason: not valid java name */
    public static final MeasureResult m1875placeLabelAndIconDIyivk0(MeasureScope $this$placeLabelAndIcon_u2dDIyivk0, final Placeable labelPlaceable, final Placeable iconPlaceable, long j, final float iconPositionAnimationProgress) {
        int baseline = labelPlaceable.get(AlignmentLineKt.getLastBaseline());
        int labelBaselineOffset = $this$placeLabelAndIcon_u2dDIyivk0.mo400roundToPx0680j_4(ItemLabelBaselineBottomOffset);
        final int labelY = (Constraints.m7857getMaxHeightimpl(j) - baseline) - labelBaselineOffset;
        final int labelX = (Constraints.m7858getMaxWidthimpl(j) - labelPlaceable.getWidth()) / 2;
        final int selectedIconY = $this$placeLabelAndIcon_u2dDIyivk0.mo400roundToPx0680j_4(ItemIconTopOffset);
        int unselectedIconY = (Constraints.m7857getMaxHeightimpl(j) - iconPlaceable.getHeight()) / 2;
        final int iconX = (Constraints.m7858getMaxWidthimpl(j) - iconPlaceable.getWidth()) / 2;
        int iconDistance = unselectedIconY - selectedIconY;
        final int offset = MathKt.roundToInt(iconDistance * (1.0f - iconPositionAnimationProgress));
        return MeasureScope.layout$default($this$placeLabelAndIcon_u2dDIyivk0, Constraints.m7858getMaxWidthimpl(j), Constraints.m7857getMaxHeightimpl(j), null, new Function1() { // from class: androidx.compose.material.NavigationRailKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavigationRailKt.placeLabelAndIcon_DIyivk0$lambda$0(iconPositionAnimationProgress, labelPlaceable, labelX, labelY, offset, iconPlaceable, iconX, selectedIconY, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit placeLabelAndIcon_DIyivk0$lambda$0(float $iconPositionAnimationProgress, Placeable $labelPlaceable, int $labelX, int $labelY, int $offset, Placeable $iconPlaceable, int $iconX, int $selectedIconY, Placeable.PlacementScope $this$layout) {
        if (!($iconPositionAnimationProgress == 0.0f)) {
            Placeable.PlacementScope.placeRelative$default($this$layout, $labelPlaceable, $labelX, $labelY + $offset, 0.0f, 4, null);
        }
        Placeable.PlacementScope.placeRelative$default($this$layout, $iconPlaceable, $iconX, $selectedIconY + $offset, 0.0f, 4, null);
        return Unit.INSTANCE;
    }
}
