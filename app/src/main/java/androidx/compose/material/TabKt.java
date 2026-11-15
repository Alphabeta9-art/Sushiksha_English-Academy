package androidx.compose.material;

import androidx.compose.animation.ColorVectorConverterKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.foundation.IndicationNodeFactory;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
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
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Tab.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000n\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0087\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\n2\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u007f\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0011\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\n2\u0011\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\n2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001aw\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00010\u0017¢\u0006\u0002\b\n¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a:\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\nH\u0003¢\u0006\u0004\b\u001f\u0010 \u001a7\u0010!\u001a\u00020\u00012\u0013\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\n2\u0013\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\nH\u0003¢\u0006\u0002\u0010\"\u001a\u001c\u0010#\u001a\u00020\u0001*\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0002\u001aD\u0010)\u001a\u00020\u0001*\u00020$2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020&2\u0006\u0010.\u001a\u00020(2\u0006\u0010'\u001a\u00020(2\u0006\u0010/\u001a\u00020(2\u0006\u00100\u001a\u00020(H\u0002\"\u0010\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0004\n\u0002\u00103\"\u0010\u00104\u001a\u000202X\u0082\u0004¢\u0006\u0004\n\u0002\u00103\"\u000e\u00105\u001a\u00020(X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u00106\u001a\u00020(X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u00107\u001a\u00020(X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u00108\u001a\u000202X\u0082\u0004¢\u0006\u0004\n\u0002\u00103\"\u0010\u00109\u001a\u000202X\u0082\u0004¢\u0006\u0004\n\u0002\u00103\"\u0010\u0010:\u001a\u000202X\u0082\u0004¢\u0006\u0004\n\u0002\u00103\"\u0010\u0010;\u001a\u00020<X\u0082\u0004¢\u0006\u0004\n\u0002\u0010=\"\u0010\u0010>\u001a\u000202X\u0082\u0004¢\u0006\u0004\n\u0002\u00103¨\u0006?²\u0006\n\u0010@\u001a\u00020\u000fX\u008a\u0084\u0002"}, d2 = {"Tab", "", "selected", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "text", "Landroidx/compose/runtime/Composable;", "icon", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "selectedContentColor", "Landroidx/compose/ui/graphics/Color;", "unselectedContentColor", "Tab-0nD-MI0", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;JJLandroidx/compose/runtime/Composer;II)V", "LeadingIconTab", "LeadingIconTab-0nD-MI0", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;JJLandroidx/compose/runtime/Composer;II)V", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "Tab-EVJuX4I", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;JJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "TabTransition", "activeColor", "inactiveColor", "TabTransition-Klgx-Pg", "(JJZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "TabBaselineLayout", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "placeTextOrIcon", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "textOrIconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "tabHeight", "", "placeTextAndIcon", "density", "Landroidx/compose/ui/unit/Density;", "textPlaceable", "iconPlaceable", "tabWidth", "firstBaseline", "lastBaseline", "SmallTabHeight", "Landroidx/compose/ui/unit/Dp;", "F", "LargeTabHeight", "TabFadeInAnimationDuration", "TabFadeInAnimationDelay", "TabFadeOutAnimationDuration", "HorizontalTextPadding", "SingleLineTextBaselineWithIcon", "DoubleLineTextBaselineWithIcon", "IconDistanceFromBaseline", "Landroidx/compose/ui/unit/TextUnit;", "J", "TextDistanceFromLeadingIcon", "material", "color"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TabKt {
    private static final int TabFadeInAnimationDelay = 100;
    private static final int TabFadeInAnimationDuration = 150;
    private static final int TabFadeOutAnimationDuration = 100;
    private static final float SmallTabHeight = Dp.m7905constructorimpl(48);
    private static final float LargeTabHeight = Dp.m7905constructorimpl(72);
    private static final float HorizontalTextPadding = Dp.m7905constructorimpl(16);
    private static final float SingleLineTextBaselineWithIcon = Dp.m7905constructorimpl(14);
    private static final float DoubleLineTextBaselineWithIcon = Dp.m7905constructorimpl(6);
    private static final long IconDistanceFromBaseline = TextUnitKt.getSp(20);
    private static final float TextDistanceFromLeadingIcon = Dp.m7905constructorimpl(8);

    static final Unit LeadingIconTab_0nD_MI0$lambda$1(boolean z, Function0 function0, Function2 function2, Function2 function3, Modifier modifier, boolean z2, MutableInteractionSource mutableInteractionSource, long j, long j2, int i, int i2, Composer composer, int i3) {
        m1969LeadingIconTab0nDMI0(z, function0, function2, function3, modifier, z2, mutableInteractionSource, j, j2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TabBaselineLayout$lambda$2(Function2 function2, Function2 function3, int i, Composer composer, int i2) {
        TabBaselineLayout(function2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TabTransition_Klgx_Pg$lambda$3(long j, long j2, boolean z, Function2 function2, int i, Composer composer, int i2) {
        m1972TabTransitionKlgxPg(j, j2, z, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Tab_0nD_MI0$lambda$2(boolean z, Function0 function0, Modifier modifier, boolean z2, Function2 function2, Function2 function3, MutableInteractionSource mutableInteractionSource, long j, long j2, int i, int i2, Composer composer, int i3) {
        m1970Tab0nDMI0(z, function0, modifier, z2, function2, function3, mutableInteractionSource, j, j2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Tab_EVJuX4I$lambda$1(boolean z, Function0 function0, Modifier modifier, boolean z2, MutableInteractionSource mutableInteractionSource, long j, long j2, Function3 function3, int i, int i2, Composer composer, int i3) {
        m1971TabEVJuX4I(z, function0, modifier, z2, mutableInteractionSource, j, j2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: Tab-0nD-MI0, reason: not valid java name */
    public static final void m1970Tab0nDMI0(final boolean selected, final Function0<Unit> function0, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, MutableInteractionSource interactionSource, long j, long j2, Composer $composer, final int $changed, final int i) {
        boolean z;
        Function0<Unit> function1;
        Modifier modifier2;
        boolean enabled2;
        final Function2<? super Composer, ? super Integer, Unit> function4;
        final Function2<? super Composer, ? super Integer, Unit> function5;
        int $dirty;
        int i2;
        Composer $composer2;
        final MutableInteractionSource interactionSource2;
        final Modifier modifier3;
        final boolean enabled3;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        final long j3;
        final long j4;
        MutableInteractionSource interactionSource3;
        long jM5107unboximpl;
        long jM5095copywmQWz5c;
        MutableInteractionSource interactionSource4;
        int $dirty2;
        boolean enabled4;
        final Function2 styledText;
        int $dirty3;
        Composer $composer3 = $composer.startRestartGroup(-610760526);
        ComposerKt.sourceInformation($composer3, "C(Tab)N(selected,onClick,modifier,enabled,text,icon,interactionSource,selectedContentColor:c#ui.graphics.Color,unselectedContentColor:c#ui.graphics.Color)113@4852L65,105@4682L235:Tab.kt#jmzs0o");
        int $dirty4 = $changed;
        if (($changed & 6) == 0) {
            z = selected;
            $dirty4 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = selected;
        }
        if (($changed & 48) == 0) {
            function1 = function0;
            $dirty4 |= $composer3.changedInstance(function1) ? 32 : 16;
        } else {
            function1 = function0;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty4 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty4 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty4 |= 24576;
            function4 = function2;
        } else if (($changed & 24576) == 0) {
            function4 = function2;
            $dirty4 |= $composer3.changedInstance(function4) ? 16384 : 8192;
        } else {
            function4 = function2;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function5 = function3;
        } else if ((196608 & $changed) == 0) {
            function5 = function3;
            $dirty4 |= $composer3.changedInstance(function5) ? 131072 : 65536;
        } else {
            function5 = function3;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty4 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty4 |= $composer3.changed(interactionSource) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                $dirty3 = $dirty4;
                i2 = i3;
                int i8 = $composer3.changed(j) ? 8388608 : 4194304;
                $dirty = $dirty3 | i8;
            } else {
                $dirty3 = $dirty4;
                i2 = i3;
            }
            $dirty = $dirty3 | i8;
        } else {
            $dirty = $dirty4;
            i2 = i3;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= ((i & 256) == 0 && $composer3.changed(j2)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty5 = $dirty;
        if (!$composer3.shouldExecute(($dirty5 & 38347923) != 38347922, $dirty5 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            interactionSource2 = interactionSource;
            modifier3 = modifier2;
            enabled3 = enabled2;
            function6 = function4;
            function7 = function5;
            j3 = j;
            j4 = j2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "95@4304L7,96@4396L6");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                $dirty2 = (i & 128) != 0 ? $dirty5 & (-29360129) : $dirty5;
                if ((i & 256) != 0) {
                    $dirty2 &= -234881025;
                }
                interactionSource4 = interactionSource;
                jM5107unboximpl = j;
                jM5095copywmQWz5c = j2;
                enabled4 = enabled2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if (i5 != 0) {
                    function4 = null;
                }
                if (i6 != 0) {
                    function5 = null;
                }
                if (i7 == 0) {
                    interactionSource3 = interactionSource;
                } else {
                    interactionSource3 = null;
                }
                if ((i & 128) != 0) {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    jM5107unboximpl = ((Color) objConsume).m5107unboximpl();
                    $dirty5 &= -29360129;
                } else {
                    jM5107unboximpl = j;
                }
                if ((i & 256) != 0) {
                    jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM5107unboximpl, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5107unboximpl) : ContentAlpha.INSTANCE.getMedium($composer3, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM5107unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5107unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5107unboximpl) : 0.0f);
                    interactionSource4 = interactionSource3;
                    $dirty2 = $dirty5 & (-234881025);
                    enabled4 = enabled2;
                } else {
                    jM5095copywmQWz5c = j2;
                    interactionSource4 = interactionSource3;
                    $dirty2 = $dirty5;
                    enabled4 = enabled2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-610760526, $dirty2, -1, "androidx.compose.material.Tab (Tab.kt:97)");
            }
            if (function4 == null) {
                $composer3.startReplaceGroup(-1684876703);
                $composer3.endReplaceGroup();
                styledText = null;
            } else {
                $composer3.startReplaceGroup(-1684876702);
                ComposerKt.sourceInformation($composer3, "*100@4501L166");
                Function2 function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1101313667, true, new Function2() { // from class: androidx.compose.material.TabKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return TabKt.Tab_0nD_MI0$lambda$0$0(function4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer3, 54);
                $composer3.endReplaceGroup();
                styledText = function2RememberComposableLambda;
            }
            $composer2 = $composer3;
            Modifier modifier4 = modifier2;
            long j5 = jM5107unboximpl;
            long j6 = jM5095copywmQWz5c;
            m1971TabEVJuX4I(z, function1, modifier4, enabled4, interactionSource4, j5, j6, ComposableLambdaKt.rememberComposableLambda(-1088373601, true, new Function3() { // from class: androidx.compose.material.TabKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TabKt.Tab_0nD_MI0$lambda$1(styledText, function5, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), $composer2, ($dirty2 & 14) | 12582912 | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (($dirty2 >> 6) & 57344) | (($dirty2 >> 6) & 458752) | (($dirty2 >> 6) & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function6 = function4;
            function7 = function5;
            modifier3 = modifier4;
            enabled3 = enabled4;
            interactionSource2 = interactionSource4;
            j3 = j5;
            j4 = j6;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TabKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabKt.Tab_0nD_MI0$lambda$2(selected, function0, modifier3, enabled3, function6, function7, interactionSource2, j3, j4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Tab_0nD_MI0$lambda$0$0(Function2 $text, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C101@4545L10,102@4614L39:Tab.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1101313667, $changed, -1, "androidx.compose.material.Tab.<anonymous>.<anonymous> (Tab.kt:101)");
            }
            TextStyle style = TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, 6).getButton(), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m7758getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null);
            TextKt.ProvideTextStyle(style, $text, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit Tab_0nD_MI0$lambda$1(Function2 $styledText, Function2 $icon, ColumnScope $this$Tab, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C114@4862L49:Tab.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1088373601, $changed, -1, "androidx.compose.material.Tab.<anonymous> (Tab.kt:114)");
            }
            TabBaselineLayout($styledText, $icon, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: LeadingIconTab-0nD-MI0, reason: not valid java name */
    public static final void m1969LeadingIconTab0nDMI0(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, long j, long j2, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function1;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Modifier modifier2;
        boolean enabled2;
        MutableInteractionSource interactionSource2;
        int $dirty;
        Composer $composer2;
        final Modifier modifier3;
        final boolean enabled3;
        final MutableInteractionSource interactionSource3;
        final long j3;
        final long j4;
        long jM5107unboximpl;
        long jM5095copywmQWz5c;
        final boolean enabled4;
        final MutableInteractionSource interactionSource4;
        int $dirty2;
        final Modifier modifier4;
        int $dirty3;
        Composer $composer3 = $composer.startRestartGroup(-524682663);
        ComposerKt.sourceInformation($composer3, "C(LeadingIconTab)N(selected,onClick,text,icon,modifier,enabled,interactionSource,selectedContentColor:c#ui.graphics.Color,unselectedContentColor:c#ui.graphics.Color)165@7275L921,165@7205L991:Tab.kt#jmzs0o");
        int $dirty4 = $changed;
        if (($changed & 6) == 0) {
            $dirty4 |= $composer3.changed(selected) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            function1 = function0;
            $dirty4 |= $composer3.changedInstance(function1) ? 32 : 16;
        } else {
            function1 = function0;
        }
        if (($changed & 384) == 0) {
            function4 = function2;
            $dirty4 |= $composer3.changedInstance(function4) ? 256 : 128;
        } else {
            function4 = function2;
        }
        if (($changed & 3072) == 0) {
            function5 = function3;
            $dirty4 |= $composer3.changedInstance(function5) ? 2048 : 1024;
        } else {
            function5 = function3;
        }
        int i2 = i & 16;
        if (i2 != 0) {
            $dirty4 |= 24576;
            modifier2 = modifier;
        } else if (($changed & 24576) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 32;
        if (i3 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            enabled2 = enabled;
        } else if ((196608 & $changed) == 0) {
            enabled2 = enabled;
            $dirty4 |= $composer3.changed(enabled2) ? 131072 : 65536;
        } else {
            enabled2 = enabled;
        }
        int i4 = i & 64;
        if (i4 != 0) {
            $dirty4 |= 1572864;
            interactionSource2 = interactionSource;
        } else if ((1572864 & $changed) == 0) {
            interactionSource2 = interactionSource;
            $dirty4 |= $composer3.changed(interactionSource2) ? 1048576 : 524288;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((12582912 & $changed) == 0) {
            if ((i & 128) == 0) {
                $dirty3 = $dirty4;
                int i5 = $composer3.changed(j) ? 8388608 : 4194304;
                $dirty = $dirty3 | i5;
            } else {
                $dirty3 = $dirty4;
            }
            $dirty = $dirty3 | i5;
        } else {
            $dirty = $dirty4;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= ((i & 256) == 0 && $composer3.changed(j2)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (!$composer3.shouldExecute((38347923 & $dirty) != 38347922, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enabled3 = enabled2;
            interactionSource3 = interactionSource2;
            j3 = j;
            j4 = j2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "157@6806L7,158@6898L6");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 128) != 0) {
                    $dirty &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty &= -234881025;
                }
                jM5107unboximpl = j;
                jM5095copywmQWz5c = j2;
                enabled4 = enabled2;
                interactionSource4 = interactionSource2;
                $dirty2 = $dirty;
                modifier4 = modifier2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    enabled2 = true;
                }
                if (i4 != 0) {
                    interactionSource2 = null;
                }
                if ((i & 128) != 0) {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty &= -29360129;
                    jM5107unboximpl = ((Color) objConsume).m5107unboximpl();
                } else {
                    jM5107unboximpl = j;
                }
                if ((i & 256) != 0) {
                    jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM5107unboximpl, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5107unboximpl) : ContentAlpha.INSTANCE.getMedium($composer3, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM5107unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5107unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5107unboximpl) : 0.0f);
                    enabled4 = enabled2;
                    interactionSource4 = interactionSource2;
                    $dirty2 = $dirty & (-234881025);
                    modifier4 = modifier2;
                } else {
                    jM5095copywmQWz5c = j2;
                    enabled4 = enabled2;
                    interactionSource4 = interactionSource2;
                    $dirty2 = $dirty;
                    modifier4 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-524682663, $dirty2, -1, "androidx.compose.material.LeadingIconTab (Tab.kt:159)");
            }
            final IndicationNodeFactory ripple = RippleKt.m1907rippleH2RKhps$default(true, 0.0f, jM5107unboximpl, 2, null);
            final Function0<Unit> function6 = function1;
            final Function2<? super Composer, ? super Integer, Unit> function7 = function4;
            final Function2<? super Composer, ? super Integer, Unit> function8 = function5;
            m1972TabTransitionKlgxPg(jM5107unboximpl, jM5095copywmQWz5c, selected, ComposableLambdaKt.rememberComposableLambda(-1415722219, true, new Function2() { // from class: androidx.compose.material.TabKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabKt.LeadingIconTab_0nD_MI0$lambda$0(modifier4, selected, interactionSource4, ripple, enabled4, function6, function8, function7, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer3, (($dirty2 >> 21) & 14) | 3072 | (($dirty2 >> 21) & 112) | (($dirty2 << 6) & 896));
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j4 = jM5095copywmQWz5c;
            modifier3 = modifier4;
            interactionSource3 = interactionSource4;
            enabled3 = enabled4;
            j3 = jM5107unboximpl;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TabKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabKt.LeadingIconTab_0nD_MI0$lambda$1(selected, function0, function2, function3, modifier3, enabled3, interactionSource3, j3, j4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit LeadingIconTab_0nD_MI0$lambda$0(Modifier $modifier, boolean $selected, MutableInteractionSource $interactionSource, IndicationNodeFactory $ripple, boolean $enabled, Function0 $onClick, Function2 $icon, Function2 $text, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C166@7285L905:Tab.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1415722219, $changed, -1, "androidx.compose.material.LeadingIconTab.<anonymous> (Tab.kt:166)");
            }
            Modifier modifier$iv = SizeKt.fillMaxWidth$default(PaddingKt.m821paddingVpY3zN4$default(SelectableKt.m1089selectableO2vRcR0(SizeKt.m851height3ABfNKs($modifier, SmallTabHeight), $selected, $interactionSource, $ripple, $enabled, Role.m7092boximpl(Role.INSTANCE.m7106getTabo7Vup1c()), $onClick), HorizontalTextPadding, 0.0f, 2, null), 0.0f, 1, null);
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getCenter();
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            int $changed$iv$iv = (432 << 3) & 112;
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
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i2 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 322948405, "C183@7959L6,184@7978L59,185@8076L10,186@8141L39:Tab.kt#jmzs0o");
            $icon.invoke($composer, 0);
            SpacerKt.Spacer(SizeKt.m862requiredWidth3ABfNKs(Modifier.INSTANCE, TextDistanceFromLeadingIcon), $composer, 6);
            TextStyle style = TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, 6).getButton(), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m7758getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null);
            TextKt.ProvideTextStyle(style, $text, $composer, 0);
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

    /* JADX INFO: renamed from: Tab-EVJuX4I, reason: not valid java name */
    public static final void m1971TabEVJuX4I(final boolean selected, final Function0<Unit> function0, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, long j, long j2, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        boolean z;
        Modifier modifier2;
        boolean enabled2;
        MutableInteractionSource interactionSource2;
        long j3;
        long j4;
        Composer $composer2;
        final Modifier modifier3;
        final boolean enabled3;
        final MutableInteractionSource interactionSource3;
        final long j5;
        final long j6;
        int $dirty;
        long jM5107unboximpl;
        int $dirty2;
        final Modifier modifier4;
        final boolean enabled4;
        long jM5095copywmQWz5c;
        long j7;
        final MutableInteractionSource interactionSource4;
        Composer $composer3 = $composer.startRestartGroup(-1847932236);
        ComposerKt.sourceInformation($composer3, "C(Tab)N(selected,onClick,modifier,enabled,interactionSource,selectedContentColor:c#ui.graphics.Color,unselectedContentColor:c#ui.graphics.Color,content)235@10460L602,235@10390L672:Tab.kt#jmzs0o");
        int $dirty3 = $changed;
        if (($changed & 6) == 0) {
            z = selected;
            $dirty3 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = selected;
        }
        if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(function0) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty3 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty3 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty3 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty3 |= 24576;
            interactionSource2 = interactionSource;
        } else if (($changed & 24576) == 0) {
            interactionSource2 = interactionSource;
            $dirty3 |= $composer3.changed(interactionSource2) ? 16384 : 8192;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                j3 = j;
                int i5 = $composer3.changed(j3) ? 131072 : 65536;
                $dirty3 |= i5;
            } else {
                j3 = j;
            }
            $dirty3 |= i5;
        } else {
            j3 = j;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                j4 = j2;
                int i6 = $composer3.changed(j4) ? 1048576 : 524288;
                $dirty3 |= i6;
            } else {
                j4 = j2;
            }
            $dirty3 |= i6;
        } else {
            j4 = j2;
        }
        if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changedInstance(function3) ? 8388608 : 4194304;
        }
        int $dirty4 = $dirty3;
        if (!$composer3.shouldExecute(($dirty3 & 4793491) != 4793490, $dirty4 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enabled3 = enabled2;
            interactionSource3 = interactionSource2;
            j5 = j3;
            j6 = j4;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "226@9945L7,227@10037L6");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                $dirty2 = (i & 32) != 0 ? $dirty4 & (-458753) : $dirty4;
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                enabled4 = enabled2;
                j7 = j3;
                modifier4 = modifier2;
                jM5095copywmQWz5c = j4;
                interactionSource4 = interactionSource2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    enabled2 = true;
                }
                if (i4 != 0) {
                    interactionSource2 = null;
                }
                if ((i & 32) != 0) {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    jM5107unboximpl = ((Color) objConsume).m5107unboximpl();
                    $dirty = $dirty4 & (-458753);
                } else {
                    $dirty = $dirty4;
                    jM5107unboximpl = j3;
                }
                if ((i & 64) != 0) {
                    $dirty2 = $dirty & (-3670017);
                    modifier4 = modifier2;
                    enabled4 = enabled2;
                    interactionSource4 = interactionSource2;
                    jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM5107unboximpl, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5107unboximpl) : ContentAlpha.INSTANCE.getMedium($composer3, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM5107unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5107unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5107unboximpl) : 0.0f);
                    j7 = jM5107unboximpl;
                } else {
                    $dirty2 = $dirty;
                    modifier4 = modifier2;
                    enabled4 = enabled2;
                    jM5095copywmQWz5c = j4;
                    j7 = jM5107unboximpl;
                    interactionSource4 = interactionSource2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1847932236, $dirty2, -1, "androidx.compose.material.Tab (Tab.kt:229)");
            }
            final IndicationNodeFactory ripple = RippleKt.m1907rippleH2RKhps$default(true, 0.0f, j7, 2, null);
            final boolean z2 = z;
            m1972TabTransitionKlgxPg(j7, jM5095copywmQWz5c, selected, ComposableLambdaKt.rememberComposableLambda(-652402312, true, new Function2() { // from class: androidx.compose.material.TabKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabKt.Tab_EVJuX4I$lambda$0(modifier4, z2, interactionSource4, ripple, enabled4, function0, function3, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer3, (($dirty2 << 6) & 896) | (($dirty2 >> 15) & 14) | 3072 | (($dirty2 >> 15) & 112));
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j5 = j7;
            j6 = jM5095copywmQWz5c;
            modifier3 = modifier4;
            interactionSource3 = interactionSource4;
            enabled3 = enabled4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TabKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabKt.Tab_EVJuX4I$lambda$1(selected, function0, modifier3, enabled3, interactionSource3, j5, j6, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Tab_EVJuX4I$lambda$0(Modifier $modifier, boolean $selected, MutableInteractionSource $interactionSource, IndicationNodeFactory $ripple, boolean $enabled, Function0 $onClick, Function3 $content, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C236@10470L586:Tab.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-652402312, $changed, -1, "androidx.compose.material.Tab.<anonymous> (Tab.kt:236)");
            }
            Modifier modifier$iv = SizeKt.fillMaxWidth$default(SelectableKt.m1089selectableO2vRcR0($modifier, $selected, $interactionSource, $ripple, $enabled, Role.m7092boximpl(Role.INSTANCE.m7106getTabo7Vup1c()), $onClick), 0.0f, 1, null);
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            int $changed$iv$iv = (432 << 3) & 112;
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
                $composer.createNode(constructor);
            } else {
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
            ComposerKt.sourceInformationMarkerStart($composer, -384672921, "C89@4556L9:Column.kt#2w3rfo");
            $content.invoke(ColumnScopeInstance.INSTANCE, $composer, Integer.valueOf(((432 >> 6) & 112) | 6));
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

    /* JADX INFO: renamed from: TabTransition-Klgx-Pg, reason: not valid java name */
    private static final void m1972TabTransitionKlgxPg(final long j, long j2, final boolean selected, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        long j3;
        boolean z;
        Composer $composer2 = $composer.startRestartGroup(-1841653376);
        ComposerKt.sourceInformation($composer2, "C(TabTransition)N(activeColor:c#ui.graphics.Color,inactiveColor:c#ui.graphics.Color,selected,content)267@11494L26,269@11557L549,284@12111L165:Tab.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(j) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            j3 = j2;
            $dirty |= $composer2.changed(j3) ? 32 : 16;
        } else {
            j3 = j2;
        }
        if (($changed & 384) == 0) {
            z = selected;
            $dirty |= $composer2.changed(z) ? 256 : 128;
        } else {
            z = selected;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1841653376, $dirty2, -1, "androidx.compose.material.TabTransition (Tab.kt:266)");
            }
            Transition transition = TransitionKt.updateTransition(Boolean.valueOf(z), (String) null, $composer2, ($dirty2 >> 6) & 14, 2);
            Function3 transitionSpec$iv = new Function3() { // from class: androidx.compose.material.TabKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TabKt.TabTransition_Klgx_Pg$lambda$0((Transition.Segment) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            };
            ComposerKt.sourceInformationMarkerStart($composer2, -1939694975, "CC(animateColor)P(2)67@3229L31,68@3296L58,70@3367L70:Transition.kt#xbi5r1");
            int $changed2 = (0 >> 6) & 112;
            boolean it = ((Boolean) transition.getTargetState()).booleanValue();
            $composer2.startReplaceGroup(90393475);
            ComposerKt.sourceInformation($composer2, "CN(it):Tab.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(90393475, $changed2, -1, "androidx.compose.material.TabTransition.<anonymous> (Tab.kt:282)");
            }
            long j4 = it ? j : j3;
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
            int $changed$iv$iv = (0 & 14) | ((0 << 3) & 896) | ((0 << 3) & 7168) | ((0 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer2, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
            int $changed3 = ($changed$iv$iv >> 9) & 112;
            boolean it2 = ((Boolean) transition.getCurrentState()).booleanValue();
            $composer2.startReplaceGroup(90393475);
            ComposerKt.sourceInformation($composer2, "CN(it):Tab.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(90393475, $changed3, -1, "androidx.compose.material.TabTransition.<anonymous> (Tab.kt:282)");
            }
            long j5 = it2 ? j : j3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer2.endReplaceGroup();
            Object initialValue$iv$iv = Color.m5087boximpl(j5);
            int $changed4 = ($changed$iv$iv >> 9) & 112;
            boolean it3 = ((Boolean) transition.getTargetState()).booleanValue();
            $composer2.startReplaceGroup(90393475);
            ComposerKt.sourceInformation($composer2, "CN(it):Tab.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(90393475, $changed4, -1, "androidx.compose.material.TabTransition.<anonymous> (Tab.kt:282)");
            }
            long j6 = it3 ? j : j3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer2.endReplaceGroup();
            Object targetValue$iv$iv = Color.m5087boximpl(j6);
            FiniteAnimationSpec animationSpec$iv$iv = (FiniteAnimationSpec) transitionSpec$iv.invoke(transition.getSegment(), $composer2, Integer.valueOf(($changed$iv$iv >> 3) & 112));
            State color$delegate = TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv, targetValue$iv$iv, animationSpec$iv$iv, typeConverter$iv, "ColorAnimation", $composer2, ($changed$iv$iv & 14) | (($changed$iv$iv << 9) & 57344) | (($changed$iv$iv << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            long jTabTransition_Klgx_Pg$lambda$2 = TabTransition_Klgx_Pg$lambda$2(color$delegate);
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{localContentColor.provides(Color.m5087boximpl(Color.m5095copywmQWz5c(jTabTransition_Klgx_Pg$lambda$2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jTabTransition_Klgx_Pg$lambda$2) : 1.0f, (14 & 2) != 0 ? Color.m5103getRedimpl(jTabTransition_Klgx_Pg$lambda$2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jTabTransition_Klgx_Pg$lambda$2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jTabTransition_Klgx_Pg$lambda$2) : 0.0f))), ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m5099getAlphaimpl(TabTransition_Klgx_Pg$lambda$2(color$delegate))))}, function2, $composer2, ProvidedValue.$stable | (($dirty2 >> 6) & 112));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final long j7 = j3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TabKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabKt.TabTransition_Klgx_Pg$lambda$3(j, j7, selected, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final long TabTransition_Klgx_Pg$lambda$2(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m5107unboximpl();
    }

    static final FiniteAnimationSpec TabTransition_Klgx_Pg$lambda$0(Transition.Segment $this$animateColor, Composer $composer, int $changed) {
        TweenSpec tweenSpecTween$default;
        $composer.startReplaceGroup(297582231);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(297582231, $changed, -1, "androidx.compose.material.TabTransition.<anonymous> (Tab.kt:271)");
        }
        if (!$this$animateColor.isTransitioningTo(false, true)) {
            tweenSpecTween$default = AnimationSpecKt.tween$default(100, 0, EasingKt.getLinearEasing(), 2, null);
        } else {
            tweenSpecTween$default = AnimationSpecKt.tween(150, 100, EasingKt.getLinearEasing());
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return tweenSpecTween$default;
    }

    /* JADX WARN: Code duplicated, block: B:54:0x0164  */
    /* JADX WARN: Code duplicated, block: B:56:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:59:0x01f6  */
    /* JADX WARN: Code duplicated, block: B:60:0x01fc  */
    /* JADX WARN: Code duplicated, block: B:69:0x02b1  */
    /* JADX WARN: Code duplicated, block: B:72:0x02c4  */
    /* JADX WARN: Code duplicated, block: B:74:0x0337  */
    /* JADX WARN: Code duplicated, block: B:77:0x0343  */
    /* JADX WARN: Code duplicated, block: B:78:0x0349  */
    /* JADX WARN: Code duplicated, block: B:81:0x037a  */
    /* JADX WARN: Code duplicated, block: B:83:0x038c  */
    /* JADX WARN: Code duplicated, block: B:84:0x038f  */
    /* JADX WARN: Code duplicated, block: B:87:0x03fe  */
    /* JADX WARN: Code duplicated, block: B:90:0x041d  */
    private static final void TabBaselineLayout(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Composer $composer$iv;
        Composer $composer3;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function1;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        Composer $composer$iv2;
        int compositeKeyHash$iv$iv2;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function4;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        Composer $composer4 = $composer.startRestartGroup(1466813041);
        ComposerKt.sourceInformation($composer4, "C(TabBaselineLayout)N(text,icon)305@12918L1803,298@12674L2047:Tab.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer4.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer4.changedInstance(function3) ? 32 : 16;
        }
        if ($composer4.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1466813041, $dirty, -1, "androidx.compose.material.TabBaselineLayout (Tab.kt:297)");
            }
            ComposerKt.sourceInformationMarkerStart($composer4, 858071932, "CC(remember):Tab.kt#9igjgp");
            boolean invalid$iv = (($dirty & 14) == 4) | (($dirty & 112) == 32);
            Object it$iv = $composer4.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (MeasurePolicy) new TabKt$TabBaselineLayout$2$1(function2, function3);
                $composer4.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            MeasurePolicy measurePolicy$iv = (MeasurePolicy) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            Modifier modifier$iv = Modifier.INSTANCE;
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
            CompositionLocalMap localMap$iv = $composer4.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = ((0 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer4.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer4.startReusableNode();
            if ($composer4.getInserting()) {
                function0 = constructor3;
                $composer4.createNode(function0);
            } else {
                function0 = constructor3;
                $composer4.useNode();
            }
            $composer2 = $composer4;
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer4);
            int $dirty2 = $dirty;
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                $composer$iv = $composer4;
            } else {
                $composer$iv = $composer4;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv >> 6) & 14;
                $composer3 = $composer$iv;
                ComposerKt.sourceInformationMarkerStart($composer3, -1044963090, "C:Tab.kt#jmzs0o");
                if (function2 != null) {
                    $composer3.startReplaceGroup(-1044948645);
                    ComposerKt.sourceInformation($composer3, "300@12723L85");
                    Modifier modifier$iv2 = PaddingKt.m821paddingVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, "text"), HorizontalTextPadding, 0.0f, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                    int $changed$iv$iv2 = (6 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                    constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function4 = constructor2;
                        $composer3.createNode(function4);
                    } else {
                        function4 = constructor2;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i2 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i3 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1022846780, "C300@12800L6:Tab.kt#jmzs0o");
                    function2.invoke($composer3, Integer.valueOf($dirty2 & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    $composer3.startReplaceGroup(-1057560344);
                }
                $composer3.endReplaceGroup();
                if (function3 != null) {
                    $composer3.startReplaceGroup(-1044815097);
                    ComposerKt.sourceInformation($composer3, "303@12859L41");
                    Modifier modifier$iv3 = LayoutIdKt.layoutId(Modifier.INSTANCE, "icon");
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                    int $changed$iv$iv3 = (6 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function1 = constructor;
                        $composer3.createNode(function1);
                    } else {
                        function1 = constructor;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                        $composer$iv2 = $composer3;
                    } else {
                        $composer$iv2 = $composer3;
                        if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                        int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
                        Composer $composer$iv3 = $composer$iv2;
                        ComposerKt.sourceInformationMarkerStart($composer$iv3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                        int i5 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer$iv3, 1180634875, "C303@12892L6:Tab.kt#jmzs0o");
                        function3.invoke($composer$iv3, Integer.valueOf(($dirty2 >> 3) & 14));
                        ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                        $composer$iv2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    }
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i6 = ($changed$iv$iv$iv2 >> 6) & 14;
                    Composer $composer$iv4 = $composer$iv2;
                    ComposerKt.sourceInformationMarkerStart($composer$iv4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    int i7 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv4, 1180634875, "C303@12892L6:Tab.kt#jmzs0o");
                    function3.invoke($composer$iv4, Integer.valueOf(($dirty2 >> 3) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer$iv4);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv4);
                    $composer$iv2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                } else {
                    $composer3.startReplaceGroup(-1057560344);
                }
                $composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
            $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = ($changed$iv$iv >> 6) & 14;
            $composer3 = $composer$iv;
            ComposerKt.sourceInformationMarkerStart($composer3, -1044963090, "C:Tab.kt#jmzs0o");
            if (function2 != null) {
                $composer3.startReplaceGroup(-1044948645);
                ComposerKt.sourceInformation($composer3, "300@12723L85");
                Modifier modifier$iv4 = PaddingKt.m821paddingVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, "text"), HorizontalTextPadding, 0.0f, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                int $changed$iv$iv4 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv4);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv3 = (($changed$iv$iv4 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function4 = constructor2;
                    $composer3.createNode(function4);
                } else {
                    function4 = constructor2;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash4);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                int i9 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                int i10 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1022846780, "C300@12800L6:Tab.kt#jmzs0o");
                function2.invoke($composer3, Integer.valueOf($dirty2 & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
            } else {
                $composer3.startReplaceGroup(-1057560344);
            }
            $composer3.endReplaceGroup();
            if (function3 != null) {
                $composer3.startReplaceGroup(-1044815097);
                ComposerKt.sourceInformation($composer3, "303@12859L41");
                Modifier modifier$iv5 = LayoutIdKt.layoutId(Modifier.INSTANCE, "icon");
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv5 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
                int $changed$iv$iv5 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer3, modifier$iv5);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv4 = (($changed$iv$iv5 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function1 = constructor;
                    $composer3.createNode(function1);
                } else {
                    function1 = constructor;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    $composer$iv2 = $composer3;
                    if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                    int i11 = ($changed$iv$iv$iv4 >> 6) & 14;
                    Composer $composer$iv5 = $composer$iv2;
                    ComposerKt.sourceInformationMarkerStart($composer$iv5, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                    int i12 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv5, 1180634875, "C303@12892L6:Tab.kt#jmzs0o");
                    function3.invoke($composer$iv5, Integer.valueOf(($dirty2 >> 3) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer$iv5);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv5);
                    $composer$iv2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                } else {
                    $composer$iv2 = $composer3;
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash5);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                int i13 = ($changed$iv$iv$iv4 >> 6) & 14;
                Composer $composer$iv6 = $composer$iv2;
                ComposerKt.sourceInformationMarkerStart($composer$iv6, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                int i14 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv6, 1180634875, "C303@12892L6:Tab.kt#jmzs0o");
                function3.invoke($composer$iv6, Integer.valueOf(($dirty2 >> 3) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer$iv6);
                ComposerKt.sourceInformationMarkerEnd($composer$iv6);
                $composer$iv2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
            } else {
                $composer3.startReplaceGroup(-1057560344);
            }
            $composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer$iv.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TabKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabKt.TabBaselineLayout$lambda$2(function2, function3, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeTextOrIcon(Placeable.PlacementScope $this$placeTextOrIcon, Placeable textOrIconPlaceable, int tabHeight) {
        int contentY = (tabHeight - textOrIconPlaceable.getHeight()) / 2;
        Placeable.PlacementScope.placeRelative$default($this$placeTextOrIcon, textOrIconPlaceable, 0, contentY, 0.0f, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeTextAndIcon(Placeable.PlacementScope $this$placeTextAndIcon, Density density, Placeable textPlaceable, Placeable iconPlaceable, int tabWidth, int tabHeight, int firstBaseline, int lastBaseline) {
        float baselineOffset;
        if (firstBaseline == lastBaseline) {
            baselineOffset = SingleLineTextBaselineWithIcon;
        } else {
            baselineOffset = DoubleLineTextBaselineWithIcon;
        }
        int textOffset = density.mo400roundToPx0680j_4(baselineOffset) + density.mo400roundToPx0680j_4(TabRowDefaults.INSTANCE.m1979getIndicatorHeightD9Ej5fM());
        int iconOffset = (iconPlaceable.getHeight() + density.mo399roundToPxR2X_6o(IconDistanceFromBaseline)) - firstBaseline;
        int textPlaceableX = (tabWidth - textPlaceable.getWidth()) / 2;
        int textPlaceableY = (tabHeight - lastBaseline) - textOffset;
        Placeable.PlacementScope.placeRelative$default($this$placeTextAndIcon, textPlaceable, textPlaceableX, textPlaceableY, 0.0f, 4, null);
        int iconPlaceableX = (tabWidth - iconPlaceable.getWidth()) / 2;
        int iconPlaceableY = textPlaceableY - iconOffset;
        Placeable.PlacementScope.placeRelative$default($this$placeTextAndIcon, iconPlaceable, iconPlaceableX, iconPlaceableY, 0.0f, 4, null);
    }
}
