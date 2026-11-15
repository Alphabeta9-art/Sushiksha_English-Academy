package androidx.compose.material;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.IntrinsicKt;
import androidx.compose.foundation.layout.IntrinsicSize;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.CornerSizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Chip.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u0090\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00112\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0002\u0010\u0016\u001aÆ\u0001\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00192\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00112\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0002\u0010\u001c\"\u0010\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001f\"\u0010\u0010 \u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001f\"\u0010\u0010!\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001f\"\u0010\u0010\"\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001f\"\u000e\u0010#\u001a\u00020$X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010%\u001a\u00020$X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010&\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001f¨\u0006'²\u0006\n\u0010(\u001a\u00020)X\u008a\u0084\u0002²\u0006\n\u0010*\u001a\u00020)X\u008a\u0084\u0002"}, d2 = {"Chip", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "shape", "Landroidx/compose/ui/graphics/Shape;", OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "colors", "Landroidx/compose/material/ChipColors;", "leadingIcon", "Landroidx/compose/runtime/Composable;", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/material/ChipColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "FilterChip", "selected", "Landroidx/compose/material/SelectableChipColors;", "selectedIcon", "trailingIcon", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/material/SelectableChipColors;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "HorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "LeadingIconStartSpacing", "LeadingIconEndSpacing", "TrailingIconSpacing", "SurfaceOverlayOpacity", "", "SelectedOverlayOpacity", "SelectedIconContainerSize", "material", "contentColor", "Landroidx/compose/ui/graphics/Color;", "leadingIconContentColor"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ChipKt {
    private static final float SelectedOverlayOpacity = 0.16f;
    private static final float SurfaceOverlayOpacity = 0.12f;
    private static final float HorizontalPadding = Dp.m7905constructorimpl(12);
    private static final float LeadingIconStartSpacing = Dp.m7905constructorimpl(4);
    private static final float LeadingIconEndSpacing = Dp.m7905constructorimpl(8);
    private static final float TrailingIconSpacing = Dp.m7905constructorimpl(8);
    private static final float SelectedIconContainerSize = Dp.m7905constructorimpl(24);

    static final Unit Chip$lambda$3(Function0 function0, Modifier modifier, boolean z, MutableInteractionSource mutableInteractionSource, Shape shape, BorderStroke borderStroke, ChipColors chipColors, Function2 function2, Function3 function3, int i, int i2, Composer composer, int i3) {
        Chip(function0, modifier, z, mutableInteractionSource, shape, borderStroke, chipColors, function2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit FilterChip$lambda$2(boolean z, Function0 function0, Modifier modifier, boolean z2, MutableInteractionSource mutableInteractionSource, Shape shape, BorderStroke borderStroke, SelectableChipColors selectableChipColors, Function2 function2, Function2 function3, Function2 function4, Function3 function5, int i, int i2, int i3, Composer composer, int i4) {
        FilterChip(z, function0, modifier, z2, mutableInteractionSource, shape, borderStroke, selectableChipColors, function2, function3, function4, function5, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    public static final void Chip(final Function0<Unit> function0, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, Shape shape, BorderStroke border, ChipColors colors, Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function1;
        Modifier modifier2;
        boolean enabled2;
        MutableInteractionSource interactionSource2;
        Shape shape2;
        BorderStroke borderStroke;
        ChipColors chipColors;
        Composer $composer2;
        final Function2<? super Composer, ? super Integer, Unit> function4;
        final Modifier modifier3;
        final boolean enabled3;
        final MutableInteractionSource interactionSource3;
        final Shape shape3;
        final BorderStroke border2;
        final ChipColors colors2;
        CornerBasedShape shape4;
        int $dirty;
        BorderStroke border3;
        Composer $composer3;
        ChipColors colors3;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Shape shape5;
        BorderStroke border4;
        boolean enabled4;
        MutableInteractionSource interactionSource4;
        Composer $composer4 = $composer.startRestartGroup(-1232125330);
        ComposerKt.sourceInformation($composer4, "C(Chip)N(onClick,modifier,enabled,interactionSource,shape,border,colors,leadingIcon,content)100@4561L21,103@4661L22,106@4758L24,110@4916L1391,101@4587L1720:Chip.kt#jmzs0o");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            function1 = function0;
            $dirty2 |= $composer4.changedInstance(function1) ? 4 : 2;
        } else {
            function1 = function0;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer4.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer4.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            interactionSource2 = interactionSource;
        } else if (($changed & 3072) == 0) {
            interactionSource2 = interactionSource;
            $dirty2 |= $composer4.changed(interactionSource2) ? 2048 : 1024;
        } else {
            interactionSource2 = interactionSource;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i5 = $composer4.changed(shape2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i5;
        } else {
            shape2 = shape;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            borderStroke = border;
        } else if ((196608 & $changed) == 0) {
            borderStroke = border;
            $dirty2 |= $composer4.changed(borderStroke) ? 131072 : 65536;
        } else {
            borderStroke = border;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                chipColors = colors;
                int i7 = $composer4.changed(chipColors) ? 1048576 : 524288;
                $dirty2 |= i7;
            } else {
                chipColors = colors;
            }
            $dirty2 |= i7;
        } else {
            chipColors = colors;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer4.changedInstance(function2) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= $composer4.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty3 = $dirty2;
        if (!$composer4.shouldExecute(($dirty2 & 38347923) != 38347922, $dirty3 & 1)) {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            function4 = function2;
            modifier3 = modifier2;
            enabled3 = enabled2;
            interactionSource3 = interactionSource2;
            shape3 = shape2;
            border2 = borderStroke;
            colors2 = colors;
        } else {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "94@4298L6,96@4415L12");
            if (($changed & 1) != 0 && !$composer4.getDefaultsInvalid()) {
                $composer4.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 64) != 0) {
                    $dirty3 &= -3670017;
                }
                ChipColors chipColors2 = chipColors;
                shape5 = shape2;
                colors3 = chipColors2;
                function5 = function2;
                interactionSource4 = interactionSource2;
                border4 = borderStroke;
                $dirty = $dirty3;
                $composer3 = $composer4;
                enabled4 = enabled2;
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
                if ((i & 16) == 0) {
                    shape4 = shape2;
                    $dirty = $dirty3;
                } else {
                    shape4 = MaterialTheme.INSTANCE.getShapes($composer4, 6).getSmall().copy(CornerSizeKt.CornerSize(50));
                    $dirty = $dirty3 & (-57345);
                }
                if (i6 == 0) {
                    border3 = borderStroke;
                } else {
                    border3 = null;
                }
                if ((i & 64) != 0) {
                    colors3 = ChipDefaults.INSTANCE.m1744chipColors5tl4gsc(0L, 0L, 0L, 0L, 0L, 0L, $composer4, 1572864, 63);
                    $composer3 = $composer4;
                    $dirty &= -3670017;
                } else {
                    $composer3 = $composer4;
                    colors3 = colors;
                }
                if (i8 == 0) {
                    function5 = function2;
                    shape5 = shape4;
                    border4 = border3;
                    enabled4 = enabled2;
                    interactionSource4 = interactionSource2;
                } else {
                    function5 = null;
                    shape5 = shape4;
                    border4 = border3;
                    enabled4 = enabled2;
                    interactionSource4 = interactionSource2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1232125330, $dirty, -1, "androidx.compose.material.Chip (Chip.kt:99)");
            }
            final State<Color> stateContentColor = colors3.contentColor(enabled4, $composer3, (($dirty >> 6) & 14) | (($dirty >> 15) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, 1338549252, "CC(remember):Chip.kt#9igjgp");
            Composer $this$cache$iv = $composer3;
            Object it$iv = $this$cache$iv.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material.ChipKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ChipKt.Chip$lambda$1$0((SemanticsPropertyReceiver) obj);
                    }
                };
                $this$cache$iv.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier2, false, (Function1) it$iv, 1, null);
            long jM5107unboximpl = colors3.backgroundColor(enabled4, $composer3, (($dirty >> 6) & 14) | (($dirty >> 15) & 112)).getValue().m5107unboximpl();
            long jChip$lambda$0 = Chip$lambda$0(stateContentColor);
            long jM5095copywmQWz5c = Color.m5095copywmQWz5c(jChip$lambda$0, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jChip$lambda$0) : 1.0f, (14 & 2) != 0 ? Color.m5103getRedimpl(jChip$lambda$0) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jChip$lambda$0) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jChip$lambda$0) : 0.0f);
            final Function2<? super Composer, ? super Integer, Unit> function6 = function5;
            final ChipColors colors4 = colors3;
            final boolean enabled5 = enabled4;
            Function2 leadingIcon = new Function2() { // from class: androidx.compose.material.ChipKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.Chip$lambda$2(stateContentColor, function6, colors4, enabled5, function3, (Composer) obj, ((Integer) obj2).intValue());
                }
            };
            function4 = function6;
            $composer2 = $composer3;
            SurfaceKt.m1955SurfaceLPr_se0(function1, modifierSemantics$default, enabled4, shape5, jM5107unboximpl, jM5095copywmQWz5c, border4, 0.0f, interactionSource4, ComposableLambdaKt.rememberComposableLambda(-1849195083, true, leadingIcon, $composer3, 54), $composer2, ($dirty & 14) | C.ENCODING_PCM_32BIT | ($dirty & 896) | (($dirty >> 3) & 7168) | (($dirty << 3) & 3670016) | (($dirty << 15) & 234881024), 128);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors2 = colors4;
            modifier3 = modifier2;
            enabled3 = enabled4;
            shape3 = shape5;
            border2 = border4;
            interactionSource3 = interactionSource4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ChipKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.Chip$lambda$3(function0, modifier3, enabled3, interactionSource3, shape3, border2, colors2, function4, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final long Chip$lambda$0(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m5107unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Chip$lambda$1$0(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.m7118setRolekuIjeqM($this$semantics, Role.INSTANCE.m7099getButtono7Vup1c());
        return Unit.INSTANCE;
    }

    static final Unit Chip$lambda$2(State $contentColor$delegate, final Function2 $leadingIcon, final ChipColors $colors, final boolean $enabled, final Function3 $content, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C111@4998L1303,111@4926L1375:Chip.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1849195083, $changed, -1, "androidx.compose.material.Chip.<anonymous> (Chip.kt:111)");
            }
            CompositionLocalKt.CompositionLocalProvider(ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m5099getAlphaimpl(Chip$lambda$0($contentColor$delegate)))), ComposableLambdaKt.rememberComposableLambda(1808091765, true, new Function2() { // from class: androidx.compose.material.ChipKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.Chip$lambda$2$0($leadingIcon, $colors, $enabled, $content, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Chip$lambda$2$0(final Function2 $leadingIcon, final ChipColors $colors, final boolean $enabled, final Function3 $content, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C112@5051L10,112@5069L1222,112@5012L1279:Chip.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1808091765, $changed, -1, "androidx.compose.material.Chip.<anonymous>.<anonymous> (Chip.kt:112)");
            }
            TextKt.ProvideTextStyle(MaterialTheme.INSTANCE.getTypography($composer, 6).getBody2(), ComposableLambdaKt.rememberComposableLambda(1507027814, true, new Function2() { // from class: androidx.compose.material.ChipKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.Chip$lambda$2$0$0($leadingIcon, $colors, $enabled, $content, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:31:0x016b  */
    /* JADX WARN: Code duplicated, block: B:32:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:35:0x0205  */
    public static final Unit Chip$lambda$2$0$0(Function2 $leadingIcon, ChipColors $colors, boolean $enabled, Function3 $content, Composer $composer, int $changed) {
        float fM7905constructorimpl;
        Function0<ComposeUiNode> function0;
        int compositeKeyHash$iv$iv;
        ComposerKt.sourceInformation($composer, "C113@5087L1190:Chip.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1507027814, $changed, -1, "androidx.compose.material.Chip.<anonymous>.<anonymous>.<anonymous> (Chip.kt:113)");
            }
            Modifier modifierM850defaultMinSizeVpY3zN4$default = SizeKt.m850defaultMinSizeVpY3zN4$default(Modifier.INSTANCE, 0.0f, ChipDefaults.INSTANCE.m1747getMinHeightD9Ej5fM(), 1, null);
            if ($leadingIcon == null) {
                fM7905constructorimpl = HorizontalPadding;
            } else {
                fM7905constructorimpl = Dp.m7905constructorimpl(0);
            }
            Modifier modifier$iv = PaddingKt.m823paddingqDBjuR0$default(modifierM850defaultMinSizeVpY3zN4$default, fM7905constructorimpl, 0.0f, HorizontalPadding, 0.0f, 10, null);
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
                int $changed2 = ((432 >> 6) & 112) | 6;
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 1856957913, "C135@6250L9:Chip.kt#jmzs0o");
                if ($leadingIcon != null) {
                    $composer.startReplaceGroup(1856981007);
                    ComposerKt.sourceInformation($composer, "126@5702L47,127@5812L32,128@5869L268,133@6162L45");
                    SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, LeadingIconStartSpacing), $composer, 6);
                    State<Color> stateLeadingIconContentColor = $colors.leadingIconContentColor($enabled, $composer, 0);
                    CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(Chip$lambda$2$0$0$0$0(stateLeadingIconContentColor))), ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m5099getAlphaimpl(Chip$lambda$2$0$0$0$0(stateLeadingIconContentColor))))}, (Function2<? super Composer, ? super Integer, Unit>) $leadingIcon, $composer, ProvidedValue.$stable);
                    SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, LeadingIconEndSpacing), $composer, 6);
                } else {
                    $composer.startReplaceGroup(1851332280);
                }
                $composer.endReplaceGroup();
                $content.invoke(rowScopeInstance, $composer, Integer.valueOf($changed2 & 14));
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
            ComposerKt.sourceInformationMarkerStart($composer, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            int $changed3 = ((432 >> 6) & 112) | 6;
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1856957913, "C135@6250L9:Chip.kt#jmzs0o");
            if ($leadingIcon != null) {
                $composer.startReplaceGroup(1856981007);
                ComposerKt.sourceInformation($composer, "126@5702L47,127@5812L32,128@5869L268,133@6162L45");
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, LeadingIconStartSpacing), $composer, 6);
                State<Color> stateLeadingIconContentColor2 = $colors.leadingIconContentColor($enabled, $composer, 0);
                CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(Chip$lambda$2$0$0$0$0(stateLeadingIconContentColor2))), ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m5099getAlphaimpl(Chip$lambda$2$0$0$0$0(stateLeadingIconContentColor2))))}, (Function2<? super Composer, ? super Integer, Unit>) $leadingIcon, $composer, ProvidedValue.$stable);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, LeadingIconEndSpacing), $composer, 6);
            } else {
                $composer.startReplaceGroup(1851332280);
            }
            $composer.endReplaceGroup();
            $content.invoke(rowScopeInstance2, $composer, Integer.valueOf($changed3 & 14));
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

    private static final long Chip$lambda$2$0$0$0$0(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m5107unboximpl();
    }

    /* JADX WARN: Code duplicated, block: B:82:0x00f5  */
    /*  JADX ERROR: JadxRuntimeException in pass: PrepareForCodeGen
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r6v0 int
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.dex.instructions.args.RegisterArg.sameCodeVar(RegisterArg.java:193)
        	at jadx.core.dex.visitors.PrepareForCodeGen.modifyArith(PrepareForCodeGen.java:242)
        	at jadx.core.dex.visitors.PrepareForCodeGen.visit(PrepareForCodeGen.java:88)
        */
    public static final void FilterChip(final boolean r36, final kotlin.jvm.functions.Function0<kotlin.Unit> r37, androidx.compose.ui.Modifier r38, boolean r39, androidx.compose.foundation.interaction.MutableInteractionSource r40, androidx.compose.ui.graphics.Shape r41, androidx.compose.foundation.BorderStroke r42, androidx.compose.material.SelectableChipColors r43, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r44, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r45, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r46, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r47, androidx.compose.runtime.Composer r48, final int r49, final int r50, final int r51) {
        /*
            Method dump skipped, instruction units count: 1014
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ChipKt.FilterChip(boolean, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Shape, androidx.compose.foundation.BorderStroke, androidx.compose.material.SelectableChipColors, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FilterChip$lambda$0$0(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.m7118setRolekuIjeqM($this$semantics, Role.INSTANCE.m7101getCheckboxo7Vup1c());
        return Unit.INSTANCE;
    }

    static final Unit FilterChip$lambda$1(final State $contentColor, final Function2 $leadingIcon, final boolean $selected, final Function2 $selectedIcon, final Function2 $trailingIcon, final Function3 $content, final SelectableChipColors $colors, final boolean $enabled, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C208@9690L3763,208@9612L3841:Chip.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-60565717, $changed, -1, "androidx.compose.material.FilterChip.<anonymous> (Chip.kt:208)");
            }
            CompositionLocalKt.CompositionLocalProvider(ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m5099getAlphaimpl(((Color) $contentColor.getValue()).m5107unboximpl()))), ComposableLambdaKt.rememberComposableLambda(-773543317, true, new Function2() { // from class: androidx.compose.material.ChipKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.FilterChip$lambda$1$0($leadingIcon, $selected, $selectedIcon, $trailingIcon, $content, $colors, $enabled, $contentColor, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FilterChip$lambda$1$0(final Function2 $leadingIcon, final boolean $selected, final Function2 $selectedIcon, final Function2 $trailingIcon, final Function3 $content, final SelectableChipColors $colors, final boolean $enabled, final State $contentColor, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C209@9743L10,209@9761L3682,209@9704L3739:Chip.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-773543317, $changed, -1, "androidx.compose.material.FilterChip.<anonymous>.<anonymous> (Chip.kt:209)");
            }
            TextKt.ProvideTextStyle(MaterialTheme.INSTANCE.getTypography($composer, 6).getBody2(), ComposableLambdaKt.rememberComposableLambda(-44453990, true, new Function2() { // from class: androidx.compose.material.ChipKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ChipKt.FilterChip$lambda$1$0$0($leadingIcon, $selected, $selectedIcon, $trailingIcon, $content, $colors, $enabled, $contentColor, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FilterChip$lambda$1$0$0(Function2 $leadingIcon, boolean $selected, Function2 $selectedIcon, Function2 $trailingIcon, Function3 $content, SelectableChipColors $colors, boolean $enabled, State $contentColor, Composer $composer, int $changed) {
        float fM7905constructorimpl;
        float fM7905constructorimpl2;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        ComposerKt.sourceInformation($composer, "C210@9779L3650:Chip.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-44453990, $changed, -1, "androidx.compose.material.FilterChip.<anonymous>.<anonymous>.<anonymous> (Chip.kt:210)");
            }
            Modifier modifierM850defaultMinSizeVpY3zN4$default = SizeKt.m850defaultMinSizeVpY3zN4$default(IntrinsicKt.width(Modifier.INSTANCE, IntrinsicSize.Max), 0.0f, ChipDefaults.INSTANCE.m1747getMinHeightD9Ej5fM(), 1, null);
            if ($leadingIcon == null && (!$selected || $selectedIcon == null)) {
                fM7905constructorimpl = HorizontalPadding;
            } else {
                fM7905constructorimpl = Dp.m7905constructorimpl(0);
            }
            if ($trailingIcon == null) {
                fM7905constructorimpl2 = HorizontalPadding;
            } else {
                fM7905constructorimpl2 = Dp.m7905constructorimpl(0);
            }
            Modifier modifier$iv = PaddingKt.m823paddingqDBjuR0$default(modifierM850defaultMinSizeVpY3zN4$default, fM7905constructorimpl, 0.0f, fM7905constructorimpl2, 0.0f, 10, null);
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            String str = "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo";
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
            int i2 = ((432 >> 6) & 112) | 6;
            RowScope $this$FilterChip_u24lambda_u241_u240_u240_u240 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -1505315515, "C268@12902L264:Chip.kt#jmzs0o");
            if ($leadingIcon != null || ($selected && $selectedIcon != null)) {
                $composer.startReplaceGroup(-1505271403);
                ComposerKt.sourceInformation($composer, "231@10804L47,232@10876L1913,266@12814L45");
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, LeadingIconStartSpacing), $composer, 6);
                ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Modifier modifier$iv2 = Modifier.INSTANCE;
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
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
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -2077321583, "C:Chip.kt#jmzs0o");
                if ($leadingIcon != null) {
                    $composer.startReplaceGroup(-2077339967);
                    ComposerKt.sourceInformation($composer, "234@10999L35,235@11067L298");
                    State<Color> stateLeadingIconColor = $colors.leadingIconColor($enabled, $selected, $composer, 0);
                    CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(stateLeadingIconColor.getValue()), ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m5099getAlphaimpl(stateLeadingIconColor.getValue().m5107unboximpl())))}, (Function2<? super Composer, ? super Integer, Unit>) $leadingIcon, $composer, ProvidedValue.$stable);
                } else {
                    $composer.startReplaceGroup(-2088202739);
                }
                $composer.endReplaceGroup();
                if (!$selected || $selectedIcon == null) {
                    str = "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo";
                    $composer.startReplaceGroup(-2088202739);
                } else {
                    $composer.startReplaceGroup(-2076791112);
                    ComposerKt.sourceInformation($composer, "255@12280L453");
                    Modifier.Companion overlayModifier = Modifier.INSTANCE;
                    long iconColor = ((Color) $contentColor.getValue()).m5107unboximpl();
                    if ($leadingIcon != null) {
                        $composer.startReplaceGroup(-2076615869);
                        ComposerKt.sourceInformation($composer, "253@12173L34");
                        overlayModifier = ClipKt.clip(BackgroundKt.m264backgroundbw27NRU(SizeKt.m857requiredSize3ABfNKs(Modifier.INSTANCE, SelectedIconContainerSize), ((Color) $contentColor.getValue()).m5107unboximpl(), RoundedCornerShapeKt.getCircleShape()), RoundedCornerShapeKt.getCircleShape());
                        iconColor = $colors.backgroundColor($enabled, $selected, $composer, 0).getValue().m5107unboximpl();
                    } else {
                        $composer.startReplaceGroup(-2088202739);
                    }
                    $composer.endReplaceGroup();
                    Alignment contentAlignment$iv2 = Alignment.INSTANCE.getCenter();
                    Modifier modifier$iv3 = overlayModifier;
                    ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                    int $changed$iv$iv3 = (48 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                    CompositionLocalMap localMap$iv$iv3 = $composer.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer, modifier$iv3);
                    Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        function2 = constructor3;
                        $composer.createNode(function2);
                    } else {
                        function2 = constructor3;
                        $composer.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5 = ($changed$iv$iv$iv3 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i6 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer, 1681585881, "C259@12494L205:Chip.kt#jmzs0o");
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(iconColor)), (Function2<? super Composer, ? super Integer, Unit>) $selectedIcon, $composer, ProvidedValue.$stable);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                }
                $composer.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, LeadingIconEndSpacing), $composer, 6);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1516029364);
                $composer.endReplaceGroup();
                str = "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo";
            }
            Modifier modifier$iv4 = RowScope.weight$default($this$FilterChip_u24lambda_u241_u240_u240_u240, Modifier.INSTANCE, 1.0f, false, 2, null);
            Arrangement.Horizontal horizontalArrangement$iv2 = Arrangement.INSTANCE.getStart();
            Alignment.Vertical verticalAlignment$iv2 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, str);
            MeasurePolicy measurePolicy$iv4 = RowKt.rowMeasurePolicy(horizontalArrangement$iv2, verticalAlignment$iv2, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            int $changed$iv$iv4 = (432 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv4 = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer, modifier$iv4);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function3 = constructor4;
                $composer.createNode(function3);
            } else {
                function3 = constructor4;
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash4);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
            int i7 = ($changed$iv$iv$iv4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            $content.invoke(RowScopeInstance.INSTANCE, $composer, Integer.valueOf(((432 >> 6) & 112) | 6));
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if ($trailingIcon != null) {
                $composer.startReplaceGroup(-1502914938);
                ComposerKt.sourceInformation($composer, "275@13239L43,276@13307L14,277@13346L43");
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, TrailingIconSpacing), $composer, 6);
                $trailingIcon.invoke($composer, 0);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, TrailingIconSpacing), $composer, 6);
            } else {
                $composer.startReplaceGroup(-1516029364);
            }
            $composer.endReplaceGroup();
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
}
