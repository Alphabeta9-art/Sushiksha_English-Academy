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
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
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
import androidx.compose.ui.unit.ConstraintsKt;
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
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: BottomNavigation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000x\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a]\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u001c\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001aU\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u001c\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0091\u0001\u0010\u0014\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u00182\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u00162\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0018¢\u0006\u0002\b\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u00162\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00072\b\b\u0002\u0010 \u001a\u00020\u0007H\u0007¢\u0006\u0004\b!\u0010\"\u001aO\u0010#\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00162&\u0010\u000b\u001a\"\u0012\u0013\u0012\u00110&¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\u000eH\u0003¢\u0006\u0004\b*\u0010+\u001a?\u0010,\u001a\u00020\u00012\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\u000e2\u0013\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0018¢\u0006\u0002\b\u000e2\b\b\u0001\u0010-\u001a\u00020&H\u0003¢\u0006\u0002\u0010.\u001a#\u0010/\u001a\u000200*\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0002¢\u0006\u0004\b6\u00107\u001a5\u00108\u001a\u000200*\u0002012\u0006\u00109\u001a\u0002032\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\b\b\u0001\u0010-\u001a\u00020&H\u0002¢\u0006\u0004\b:\u0010;\"\u0014\u0010<\u001a\b\u0012\u0004\u0012\u00020&0=X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010>\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010?\"\u0010\u0010@\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010?\"\u0010\u0010A\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010?\"\u000e\u0010B\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C²\u0006\n\u0010)\u001a\u00020&X\u008a\u0084\u0002"}, d2 = {"BottomNavigation", "", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "modifier", "Landroidx/compose/ui/Modifier;", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "contentColor", "elevation", "Landroidx/compose/ui/unit/Dp;", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "BottomNavigation-_UMDTes", "(Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/Modifier;JJFLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomNavigation-PEIptTM", "(Landroidx/compose/ui/Modifier;JJFLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomNavigationItem", "selected", "", "onClick", "Lkotlin/Function0;", "icon", "enabled", MetadataField.LABEL, "alwaysShowLabel", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "selectedContentColor", "unselectedContentColor", "BottomNavigationItem-jY6E1Zs", "(Landroidx/compose/foundation/layout/RowScope;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;JJLandroidx/compose/runtime/Composer;III)V", "BottomNavigationTransition", "activeColor", "inactiveColor", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "animationProgress", "BottomNavigationTransition-Klgx-Pg", "(JJZLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "BottomNavigationItemBaselineLayout", "iconPositionAnimationProgress", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;FLandroidx/compose/runtime/Composer;I)V", "placeIcon", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "iconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "placeIcon-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;J)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndIcon", "labelPlaceable", "placeLabelAndIcon-DIyivk0", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JF)Landroidx/compose/ui/layout/MeasureResult;", "BottomNavigationAnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "BottomNavigationHeight", "F", "BottomNavigationItemHorizontalPadding", "CombinedItemTextBaseline", "ZeroInsets", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BottomNavigationKt {
    private static final TweenSpec<Float> BottomNavigationAnimationSpec = new TweenSpec<>(300, 0, EasingKt.getFastOutSlowInEasing(), 2, null);
    private static final float BottomNavigationHeight = Dp.m7905constructorimpl(56);
    private static final float BottomNavigationItemHorizontalPadding = Dp.m7905constructorimpl(12);
    private static final float CombinedItemTextBaseline = Dp.m7905constructorimpl(12);
    private static final WindowInsets ZeroInsets = WindowInsetsKt.m890WindowInsetsa9UjIt4(Dp.m7905constructorimpl(0), Dp.m7905constructorimpl(0), Dp.m7905constructorimpl(0), Dp.m7905constructorimpl(0));

    static final Unit BottomNavigationItemBaselineLayout$lambda$2(Function2 function2, Function2 function3, float f, int i, Composer composer, int i2) {
        BottomNavigationItemBaselineLayout(function2, function3, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit BottomNavigationItem_jY6E1Zs$lambda$2(RowScope rowScope, boolean z, Function0 function0, Function2 function2, Modifier modifier, boolean z2, Function2 function3, boolean z3, MutableInteractionSource mutableInteractionSource, long j, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m1713BottomNavigationItemjY6E1Zs(rowScope, z, function0, function2, modifier, z2, function3, z3, mutableInteractionSource, j, j2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit BottomNavigationTransition_Klgx_Pg$lambda$2(long j, long j2, boolean z, Function3 function3, int i, Composer composer, int i2) {
        m1714BottomNavigationTransitionKlgxPg(j, j2, z, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit BottomNavigation_PEIptTM$lambda$0(Modifier modifier, long j, long j2, float f, Function3 function3, int i, int i2, Composer composer, int i3) {
        m1711BottomNavigationPEIptTM(modifier, j, j2, f, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit BottomNavigation__UMDTes$lambda$1(WindowInsets windowInsets, Modifier modifier, long j, long j2, float f, Function3 function3, int i, int i2, Composer composer, int i3) {
        m1712BottomNavigation_UMDTes(windowInsets, modifier, j, j2, f, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: BottomNavigation-_UMDTes, reason: not valid java name */
    public static final void m1712BottomNavigation_UMDTes(final WindowInsets windowInsets, Modifier modifier, long j, long j2, float f, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long primarySurface;
        long jM1783contentColorForek8zF_U;
        float f2;
        Composer $composer2;
        final Modifier modifier3;
        final long j3;
        final long j4;
        final float f3;
        long j5;
        float fM1709getElevationD9Ej5fM;
        Modifier modifier4;
        long j6;
        Composer $composer3 = $composer.startRestartGroup(-636542119);
        ComposerKt.sourceInformation($composer3, "C(BottomNavigation)N(windowInsets,modifier,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation:c#ui.unit.Dp,content)112@4927L314,107@4782L459:BottomNavigation.kt#jmzs0o");
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
                jM1783contentColorForek8zF_U = j2;
                int i4 = $composer3.changed(jM1783contentColorForek8zF_U) ? 2048 : 1024;
                $dirty |= i4;
            } else {
                jM1783contentColorForek8zF_U = j2;
            }
            $dirty |= i4;
        } else {
            jM1783contentColorForek8zF_U = j2;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            f2 = f;
        } else if (($changed & 24576) == 0) {
            f2 = f;
            $dirty |= $composer3.changed(f2) ? 16384 : 8192;
        } else {
            f2 = f;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 131072 : 65536;
        }
        if ($composer3.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "102@4589L6,103@4638L32");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                j5 = jM1783contentColorForek8zF_U;
                fM1709getElevationD9Ej5fM = f2;
                modifier4 = modifier2;
                j6 = primarySurface;
            } else {
                if (i2 != 0) {
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
                if (i5 == 0) {
                    j5 = jM1783contentColorForek8zF_U;
                    fM1709getElevationD9Ej5fM = f2;
                    modifier4 = modifier2;
                    j6 = primarySurface;
                } else {
                    fM1709getElevationD9Ej5fM = BottomNavigationDefaults.INSTANCE.m1709getElevationD9Ej5fM();
                    j6 = primarySurface;
                    j5 = jM1783contentColorForek8zF_U;
                    modifier4 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-636542119, $dirty, -1, "androidx.compose.material.BottomNavigation (BottomNavigation.kt:106)");
            }
            $composer2 = $composer3;
            SurfaceKt.m1954SurfaceFjzlyU(modifier4, null, j6, j5, null, fM1709getElevationD9Ej5fM, ComposableLambdaKt.rememberComposableLambda(807373717, true, new Function2() { // from class: androidx.compose.material.BottomNavigationKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomNavigationKt.BottomNavigation__UMDTes$lambda$0(windowInsets, function3, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer2, (($dirty >> 3) & 14) | 1572864 | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 458752), 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            j3 = j6;
            j4 = j5;
            f3 = fM1709getElevationD9Ej5fM;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = primarySurface;
            j4 = jM1783contentColorForek8zF_U;
            f3 = f2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.BottomNavigationKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomNavigationKt.BottomNavigation__UMDTes$lambda$1(windowInsets, modifier3, j3, j4, f3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit BottomNavigation__UMDTes$lambda$0(WindowInsets $windowInsets, Function3 $content, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C113@4937L298:BottomNavigation.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(807373717, $changed, -1, "androidx.compose.material.BottomNavigation.<anonymous> (BottomNavigation.kt:113)");
            }
            Modifier modifier$iv = SelectableGroupKt.selectableGroup(SizeKt.m850defaultMinSizeVpY3zN4$default(WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), $windowInsets), 0.0f, BottomNavigationHeight, 1, null));
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getSpaceBetween();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            int $changed$iv$iv = (48 << 3) & 112;
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
            $content.invoke(RowScopeInstance.INSTANCE, $composer, Integer.valueOf(((48 >> 6) & 112) | 6));
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

    /* JADX INFO: renamed from: BottomNavigation-PEIptTM, reason: not valid java name */
    public static final void m1711BottomNavigationPEIptTM(Modifier modifier, long j, long j2, float f, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j3;
        long jM1783contentColorForek8zF_U;
        float f2;
        Composer $composer2;
        final Modifier modifier3;
        final long j4;
        final long j5;
        final float f3;
        Modifier.Companion modifier4;
        long primarySurface;
        Modifier modifier5;
        long j6;
        float fM1709getElevationD9Ej5fM;
        long j7;
        Composer $composer3 = $composer.startRestartGroup(783675913);
        ComposerKt.sourceInformation($composer3, "C(BottomNavigation)N(modifier,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation:c#ui.unit.Dp,content)162@6934L89:BottomNavigation.kt#jmzs0o");
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
                j3 = j;
                int i3 = $composer3.changed(j3) ? 32 : 16;
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
                jM1783contentColorForek8zF_U = j2;
                int i4 = $composer3.changed(jM1783contentColorForek8zF_U) ? 256 : 128;
                $dirty |= i4;
            } else {
                jM1783contentColorForek8zF_U = j2;
            }
            $dirty |= i4;
        } else {
            jM1783contentColorForek8zF_U = j2;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            f2 = f;
        } else if (($changed & 3072) == 0) {
            f2 = f;
            $dirty |= $composer3.changed(f2) ? 2048 : 1024;
        } else {
            f2 = f;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 16384 : 8192;
        }
        if (!$composer3.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j4 = j3;
            j5 = jM1783contentColorForek8zF_U;
            f3 = f2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "157@6741L6,158@6790L32");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                fM1709getElevationD9Ej5fM = f2;
                j7 = j3;
                j6 = jM1783contentColorForek8zF_U;
                modifier5 = modifier2;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 2) == 0) {
                    primarySurface = j3;
                } else {
                    primarySurface = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer3, 6));
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(primarySurface, $composer3, ($dirty >> 3) & 14);
                    $dirty &= -897;
                }
                if (i5 == 0) {
                    modifier5 = modifier4;
                    j6 = jM1783contentColorForek8zF_U;
                    fM1709getElevationD9Ej5fM = f2;
                    j7 = primarySurface;
                } else {
                    modifier5 = modifier4;
                    j6 = jM1783contentColorForek8zF_U;
                    j7 = primarySurface;
                    fM1709getElevationD9Ej5fM = BottomNavigationDefaults.INSTANCE.m1709getElevationD9Ej5fM();
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(783675913, $dirty, -1, "androidx.compose.material.BottomNavigation (BottomNavigation.kt:161)");
            }
            $composer2 = $composer3;
            m1712BottomNavigation_UMDTes(ZeroInsets, modifier5, j7, j6, fM1709getElevationD9Ej5fM, function3, $composer2, (($dirty << 3) & 112) | 6 | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            j5 = j6;
            f3 = fM1709getElevationD9Ej5fM;
            j4 = j7;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.BottomNavigationKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomNavigationKt.BottomNavigation_PEIptTM$lambda$0(modifier3, j4, j5, f3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: BottomNavigationItem-jY6E1Zs, reason: not valid java name */
    public static final void m1713BottomNavigationItemjY6E1Zs(final RowScope $this$BottomNavigationItem_u2djY6E1Zs, final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function3, boolean alwaysShowLabel, MutableInteractionSource interactionSource, long j, long j2, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        Function0<Unit> function1;
        Modifier modifier2;
        boolean enabled2;
        final Function2<? super Composer, ? super Integer, Unit> function4;
        int $dirty1;
        Composer $composer2;
        final boolean alwaysShowLabel2;
        final MutableInteractionSource interactionSource2;
        final long j3;
        final Modifier modifier3;
        final long j4;
        final boolean enabled3;
        Modifier.Companion modifier4;
        final boolean alwaysShowLabel3;
        MutableInteractionSource interactionSource3;
        long jM5107unboximpl;
        int $dirty;
        long jM5095copywmQWz5c;
        int $dirty2;
        final Function2 styledLabel;
        Function0<ComposeUiNode> function5;
        Composer $composer3 = $composer.startRestartGroup(-1744464261);
        ComposerKt.sourceInformation($composer3, "C(BottomNavigationItem)N(selected,onClick,icon,modifier,enabled,label,alwaysShowLabel,interactionSource,selectedContentColor:c#ui.graphics.Color,unselectedContentColor:c#ui.graphics.Color)221@10008L773:BottomNavigation.kt#jmzs0o");
        int $dirty3 = $changed;
        if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changed($this$BottomNavigationItem_u2djY6E1Zs) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            z = selected;
            $dirty3 |= $composer3.changed(z) ? 32 : 16;
        } else {
            z = selected;
        }
        if (($changed & 384) == 0) {
            function1 = function0;
            $dirty3 |= $composer3.changedInstance(function1) ? 256 : 128;
        } else {
            function1 = function0;
        }
        if (($changed & 3072) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 2048 : 1024;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty3 |= 24576;
            modifier2 = modifier;
        } else if (($changed & 24576) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            enabled2 = enabled;
        } else if ((196608 & $changed) == 0) {
            enabled2 = enabled;
            $dirty3 |= $composer3.changed(enabled2) ? 131072 : 65536;
        } else {
            enabled2 = enabled;
        }
        int i4 = i & 32;
        if (i4 != 0) {
            $dirty3 |= 1572864;
            function4 = function3;
        } else if (($changed & 1572864) == 0) {
            function4 = function3;
            $dirty3 |= $composer3.changedInstance(function4) ? 1048576 : 524288;
        } else {
            function4 = function3;
        }
        int i5 = i & 64;
        if (i5 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changed(alwaysShowLabel) ? 8388608 : 4194304;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            if ((i & 256) == 0) {
                $dirty1 = $changed1;
                int i7 = $composer3.changed(j) ? 536870912 : 268435456;
                $dirty3 |= i7;
            } else {
                $dirty1 = $changed1;
            }
            $dirty3 |= i7;
        } else {
            $dirty1 = $changed1;
        }
        if (($changed1 & 6) == 0) {
            int $dirty4 = $dirty1;
            int $dirty5 = i & 512;
            $dirty1 = $dirty4 | (($dirty5 == 0 && $composer3.changed(j2)) ? 4 : 2);
        }
        int $dirty6 = $dirty1;
        int $dirty7 = $dirty3 & 306783379;
        int $dirty8 = $dirty3;
        if ($composer3.shouldExecute(($dirty7 == 306783378 && ($dirty6 & 3) == 2) ? false : true, $dirty8 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "206@9325L7,207@9417L6");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty9 = (i & 256) != 0 ? $dirty8 & (-1879048193) : $dirty8;
                if ((i & 512) != 0) {
                    alwaysShowLabel3 = alwaysShowLabel;
                    jM5107unboximpl = j;
                    jM5095copywmQWz5c = j2;
                    $dirty = $dirty9;
                    modifier4 = modifier2;
                    $dirty2 = $dirty6 & (-15);
                    interactionSource3 = interactionSource;
                } else {
                    alwaysShowLabel3 = alwaysShowLabel;
                    interactionSource3 = interactionSource;
                    jM5107unboximpl = j;
                    jM5095copywmQWz5c = j2;
                    $dirty = $dirty9;
                    modifier4 = modifier2;
                    $dirty2 = $dirty6;
                }
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i3 != 0) {
                    enabled2 = true;
                }
                if (i4 != 0) {
                    function4 = null;
                }
                if (i5 == 0) {
                    alwaysShowLabel3 = alwaysShowLabel;
                } else {
                    alwaysShowLabel3 = true;
                }
                if (i6 == 0) {
                    interactionSource3 = interactionSource;
                } else {
                    interactionSource3 = null;
                }
                if ((i & 256) == 0) {
                    jM5107unboximpl = j;
                    $dirty = $dirty8;
                } else {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty = $dirty8 & (-1879048193);
                    jM5107unboximpl = ((Color) objConsume).m5107unboximpl();
                }
                if ((i & 512) != 0) {
                    jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM5107unboximpl, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5107unboximpl) : ContentAlpha.INSTANCE.getMedium($composer3, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM5107unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5107unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5107unboximpl) : 0.0f);
                    $dirty2 = $dirty6 & (-15);
                } else {
                    jM5095copywmQWz5c = j2;
                    $dirty2 = $dirty6;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1744464261, $dirty, $dirty2, "androidx.compose.material.BottomNavigationItem (BottomNavigation.kt:208)");
            }
            if (function4 == null) {
                $composer3.startReplaceGroup(-229974730);
                $composer3.endReplaceGroup();
                styledLabel = null;
            } else {
                $composer3.startReplaceGroup(-229974729);
                ComposerKt.sourceInformation($composer3, "*211@9524L168");
                styledLabel = ComposableLambdaKt.rememberComposableLambda(2120789583, true, new Function2() { // from class: androidx.compose.material.BottomNavigationKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return BottomNavigationKt.BottomNavigationItem_jY6E1Zs$lambda$0$0(function4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer3, 54);
                $composer3.endReplaceGroup();
            }
            IndicationNodeFactory ripple = RippleKt.m1907rippleH2RKhps$default(false, 0.0f, jM5107unboximpl, 2, null);
            Modifier modifier5 = modifier4;
            Modifier modifier$iv = RowScope.weight$default($this$BottomNavigationItem_u2djY6E1Zs, SelectableKt.m1089selectableO2vRcR0(modifier5, z, interactionSource3, ripple, enabled2, Role.m7092boximpl(Role.INSTANCE.m7106getTabo7Vup1c()), function1), 1.0f, false, 2, null);
            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
            $composer2 = $composer3;
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (48 << 3) & 112;
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
                function5 = constructor;
                $composer3.createNode(function5);
            } else {
                function5 = constructor;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i9 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1642075038, "C234@10470L305,234@10387L388:BottomNavigation.kt#jmzs0o");
            long j5 = jM5095copywmQWz5c;
            m1714BottomNavigationTransitionKlgxPg(jM5107unboximpl, j5, selected, ComposableLambdaKt.rememberComposableLambda(2011119551, true, new Function3() { // from class: androidx.compose.material.BottomNavigationKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return BottomNavigationKt.BottomNavigationItem_jY6E1Zs$lambda$1$0(alwaysShowLabel3, function2, styledLabel, ((Float) obj).floatValue(), (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), $composer3, (($dirty >> 27) & 14) | 3072 | (($dirty2 << 3) & 112) | (($dirty << 3) & 896));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource2 = interactionSource3;
            alwaysShowLabel2 = alwaysShowLabel3;
            modifier3 = modifier5;
            j3 = jM5107unboximpl;
            j4 = j5;
            enabled3 = enabled2;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            alwaysShowLabel2 = alwaysShowLabel;
            interactionSource2 = interactionSource;
            j3 = j;
            modifier3 = modifier2;
            j4 = j2;
            enabled3 = enabled2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.BottomNavigationKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomNavigationKt.BottomNavigationItem_jY6E1Zs$lambda$2($this$BottomNavigationItem_u2djY6E1Zs, selected, function0, function2, modifier3, enabled3, function4, alwaysShowLabel2, interactionSource2, j3, j4, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomNavigationItem_jY6E1Zs$lambda$0$0(Function2 $label, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C212@9568L10,213@9638L40:BottomNavigation.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2120789583, $changed, -1, "androidx.compose.material.BottomNavigationItem.<anonymous>.<anonymous> (BottomNavigation.kt:212)");
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
    public static final Unit BottomNavigationItem_jY6E1Zs$lambda$1$0(boolean $alwaysShowLabel, Function2 $icon, Function2 $styledLabel, float progress, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(progress)238@10583L182:BottomNavigation.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(progress) ? 4 : 2;
        }
        if (!$composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2011119551, $dirty, -1, "androidx.compose.material.BottomNavigationItem.<anonymous>.<anonymous> (BottomNavigation.kt:236)");
            }
            float animationProgress = $alwaysShowLabel ? 1.0f : progress;
            BottomNavigationItemBaselineLayout($icon, $styledLabel, animationProgress, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: BottomNavigationTransition-Klgx-Pg, reason: not valid java name */
    private static final void m1714BottomNavigationTransitionKlgxPg(final long j, final long j2, final boolean selected, final Function3<? super Float, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(616920545);
        ComposerKt.sourceInformation($composer2, "C(BottomNavigationTransition)N(activeColor:c#ui.graphics.Color,inactiveColor:c#ui.graphics.Color,selected,content)281@12195L141,291@12550L42,288@12411L181:BottomNavigation.kt#jmzs0o");
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
                ComposerKt.traceEventStart(616920545, $dirty, -1, "androidx.compose.material.BottomNavigationTransition (BottomNavigation.kt:279)");
            }
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(selected ? 1065353216 : 0, BottomNavigationAnimationSpec, 0.0f, null, null, $composer2, 48, 28);
            long color = ColorKt.m5148lerpjxsXWHM(j2, j, BottomNavigationTransition_Klgx_Pg$lambda$0(stateAnimateFloatAsState));
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(Color.m5095copywmQWz5c(color, (14 & 1) != 0 ? Color.m5099getAlphaimpl(color) : 1.0f, (14 & 2) != 0 ? Color.m5103getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(color) : 0.0f))), ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m5099getAlphaimpl(color)))}, ComposableLambdaKt.rememberComposableLambda(343660833, true, new Function2() { // from class: androidx.compose.material.BottomNavigationKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomNavigationKt.BottomNavigationTransition_Klgx_Pg$lambda$1(function3, stateAnimateFloatAsState, (Composer) obj, ((Integer) obj2).intValue());
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.BottomNavigationKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomNavigationKt.BottomNavigationTransition_Klgx_Pg$lambda$2(j, j2, selected, function3, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float BottomNavigationTransition_Klgx_Pg$lambda$0(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    static final Unit BottomNavigationTransition_Klgx_Pg$lambda$1(Function3 $content, State $animationProgress$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C292@12560L26:BottomNavigation.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(343660833, $changed, -1, "androidx.compose.material.BottomNavigationTransition.<anonymous> (BottomNavigation.kt:292)");
            }
            $content.invoke(Float.valueOf(BottomNavigationTransition_Klgx_Pg$lambda$0($animationProgress$delegate)), $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final void BottomNavigationItemBaselineLayout(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final float iconPositionAnimationProgress, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function4;
        Composer $composer$iv;
        Composer $composer3 = $composer.startRestartGroup(-1481587758);
        ComposerKt.sourceInformation($composer3, "C(BottomNavigationItemBaselineLayout)N(icon,label,iconPositionAnimationProgress)323@13622L928,312@13264L1286:BottomNavigation.kt#jmzs0o");
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
                ComposerKt.traceEventStart(-1481587758, $dirty, -1, "androidx.compose.material.BottomNavigationItemBaselineLayout (BottomNavigation.kt:311)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 2123325490, "CC(remember):BottomNavigation.kt#9igjgp");
            boolean invalid$iv = (($dirty & 112) == 32) | (($dirty & 896) == 256);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (MeasurePolicy) new MeasurePolicy() { // from class: androidx.compose.material.BottomNavigationKt$BottomNavigationItemBaselineLayout$2$1
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
                                    return BottomNavigationKt.m1717placeIcon3p2s80s($this$Layout, iconPlaceable, j);
                                }
                                Intrinsics.checkNotNull(labelPlaceable);
                                return BottomNavigationKt.m1718placeLabelAndIconDIyivk0($this$Layout, labelPlaceable, iconPlaceable, j, iconPositionAnimationProgress);
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
            ComposerKt.sourceInformationMarkerStart($composer3, 1977638459, "C313@13281L41:BottomNavigation.kt#jmzs0o");
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
            ComposerKt.sourceInformationMarkerStart($composer3, -430660824, "C313@13314L6:BottomNavigation.kt#jmzs0o");
            function2.invoke($composer3, Integer.valueOf($dirty2 & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (function3 != null) {
                $composer3.startReplaceGroup(1977704767);
                ComposerKt.sourceInformation($composer3, "315@13364L240");
                Modifier modifier$iv3 = PaddingKt.m821paddingVpY3zN4$default(AlphaKt.alpha(LayoutIdKt.layoutId(Modifier.INSTANCE, MetadataField.LABEL), iconPositionAnimationProgress), BottomNavigationItemHorizontalPadding, 0.0f, 2, null);
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
                    ComposerKt.sourceInformationMarkerStart($composer$iv2, -1599731230, "C320@13583L7:BottomNavigation.kt#jmzs0o");
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
                ComposerKt.sourceInformationMarkerStart($composer$iv3, -1599731230, "C320@13583L7:BottomNavigation.kt#jmzs0o");
                function3.invoke($composer$iv3, Integer.valueOf(($dirty2 >> 3) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
            } else {
                $composer3.startReplaceGroup(1964452391);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.BottomNavigationKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomNavigationKt.BottomNavigationItemBaselineLayout$lambda$2(function2, function3, iconPositionAnimationProgress, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeIcon-3p2s80s, reason: not valid java name */
    public static final MeasureResult m1717placeIcon3p2s80s(MeasureScope $this$placeIcon_u2d3p2s80s, final Placeable iconPlaceable, long j) {
        int height = ConstraintsKt.m7874constrainHeightK40F9xA(j, $this$placeIcon_u2d3p2s80s.mo400roundToPx0680j_4(BottomNavigationHeight));
        final int iconY = (height - iconPlaceable.getHeight()) / 2;
        return MeasureScope.layout$default($this$placeIcon_u2d3p2s80s, iconPlaceable.getWidth(), height, null, new Function1() { // from class: androidx.compose.material.BottomNavigationKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BottomNavigationKt.placeIcon_3p2s80s$lambda$0(iconPlaceable, iconY, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit placeIcon_3p2s80s$lambda$0(Placeable $iconPlaceable, int $iconY, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelative$default($this$layout, $iconPlaceable, 0, $iconY, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeLabelAndIcon-DIyivk0, reason: not valid java name */
    public static final MeasureResult m1718placeLabelAndIconDIyivk0(MeasureScope $this$placeLabelAndIcon_u2dDIyivk0, final Placeable labelPlaceable, final Placeable iconPlaceable, long j, final float iconPositionAnimationProgress) {
        int firstBaseline = labelPlaceable.get(AlignmentLineKt.getFirstBaseline());
        int baselineOffset = $this$placeLabelAndIcon_u2dDIyivk0.mo400roundToPx0680j_4(CombinedItemTextBaseline);
        int netBaselineAdjustment = baselineOffset - firstBaseline;
        int contentHeight = iconPlaceable.getHeight() + labelPlaceable.getHeight() + netBaselineAdjustment;
        int height = ConstraintsKt.m7874constrainHeightK40F9xA(j, Math.max(contentHeight, $this$placeLabelAndIcon_u2dDIyivk0.mo400roundToPx0680j_4(BottomNavigationHeight)));
        final int contentVerticalPadding = RangesKt.coerceAtLeast((height - contentHeight) / 2, 0);
        int unselectedIconY = (height - iconPlaceable.getHeight()) / 2;
        final int labelY = iconPlaceable.getHeight() + contentVerticalPadding + netBaselineAdjustment;
        int containerWidth = Math.max(labelPlaceable.getWidth(), iconPlaceable.getWidth());
        final int labelX = (containerWidth - labelPlaceable.getWidth()) / 2;
        final int iconX = (containerWidth - iconPlaceable.getWidth()) / 2;
        int iconDistance = unselectedIconY - contentVerticalPadding;
        final int offset = MathKt.roundToInt(iconDistance * (1.0f - iconPositionAnimationProgress));
        return MeasureScope.layout$default($this$placeLabelAndIcon_u2dDIyivk0, containerWidth, height, null, new Function1() { // from class: androidx.compose.material.BottomNavigationKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BottomNavigationKt.placeLabelAndIcon_DIyivk0$lambda$0(iconPositionAnimationProgress, labelPlaceable, labelX, labelY, offset, iconPlaceable, iconX, contentVerticalPadding, (Placeable.PlacementScope) obj);
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
