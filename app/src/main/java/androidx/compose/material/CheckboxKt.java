package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.ToggleableKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.state.ToggleableStateKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;

/* JADX INFO: compiled from: Checkbox.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u001aU\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\r\u001aO\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00122\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\u0013\u001a-\u0010\u0014\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0003¢\u0006\u0002\u0010\u0016\u001a3\u0010\u0017\u001a\u00020\u0001*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002¢\u0006\u0004\b\u001f\u0010 \u001a;\u0010!\u001a\u00020\u0001*\u00020\u00182\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020'H\u0002¢\u0006\u0004\b(\u0010)\"\u000e\u0010*\u001a\u00020+X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010,\u001a\u00020+X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010-\u001a\u00020+X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u00101\u001a\u00020/X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u00102\u001a\u00020/X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u00103\u001a\u00020/X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u00104\u001a\u00020/X\u0082\u0004¢\u0006\u0004\n\u0002\u00100¨\u00065²\u0006\n\u00106\u001a\u00020\u001dX\u008a\u0084\u0002²\u0006\n\u00107\u001a\u00020\u001dX\u008a\u0084\u0002²\u0006\n\u0010\"\u001a\u00020\u001aX\u008a\u0084\u0002²\u0006\n\u0010\u0019\u001a\u00020\u001aX\u008a\u0084\u0002²\u0006\n\u0010\u001b\u001a\u00020\u001aX\u008a\u0084\u0002"}, d2 = {"Checkbox", "", "checked", "", "onCheckedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "colors", "Landroidx/compose/material/CheckboxColors;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/CheckboxColors;Landroidx/compose/runtime/Composer;II)V", "TriStateCheckbox", "state", "Landroidx/compose/ui/state/ToggleableState;", "onClick", "Lkotlin/Function0;", "(Landroidx/compose/ui/state/ToggleableState;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/CheckboxColors;Landroidx/compose/runtime/Composer;II)V", "CheckboxImpl", "value", "(ZLandroidx/compose/ui/state/ToggleableState;Landroidx/compose/ui/Modifier;Landroidx/compose/material/CheckboxColors;Landroidx/compose/runtime/Composer;I)V", "drawBox", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "boxColor", "Landroidx/compose/ui/graphics/Color;", "borderColor", "radius", "", "strokeWidth", "drawBox-1wkBAMs", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJFF)V", "drawCheck", "checkColor", "checkFraction", "crossCenterGravitation", "strokeWidthPx", "drawingCache", "Landroidx/compose/material/CheckDrawingCache;", "drawCheck-3IgeMak", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFFFLandroidx/compose/material/CheckDrawingCache;)V", "BoxInDuration", "", "BoxOutDuration", "CheckAnimationDuration", "CheckboxRippleRadius", "Landroidx/compose/ui/unit/Dp;", "F", "CheckboxDefaultPadding", "CheckboxSize", "StrokeWidth", "RadiusSize", "material", "checkDrawFraction", "checkCenterGravitationShiftFraction"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class CheckboxKt {
    private static final int BoxInDuration = 50;
    private static final int BoxOutDuration = 100;
    private static final int CheckAnimationDuration = 100;
    private static final float CheckboxRippleRadius = Dp.m7905constructorimpl(24);
    private static final float CheckboxDefaultPadding = Dp.m7905constructorimpl(2);
    private static final float CheckboxSize = Dp.m7905constructorimpl(20);
    private static final float StrokeWidth = Dp.m7905constructorimpl(2);
    private static final float RadiusSize = Dp.m7905constructorimpl(2);

    /* JADX INFO: compiled from: Checkbox.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToggleableState.values().length];
            try {
                iArr[ToggleableState.On.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ToggleableState.Off.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ToggleableState.Indeterminate.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit Checkbox$lambda$1(boolean z, Function1 function1, Modifier modifier, boolean z2, MutableInteractionSource mutableInteractionSource, CheckboxColors checkboxColors, int i, int i2, Composer composer, int i3) {
        Checkbox(z, function1, modifier, z2, mutableInteractionSource, checkboxColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit CheckboxImpl$lambda$11(boolean z, ToggleableState toggleableState, Modifier modifier, CheckboxColors checkboxColors, int i, Composer composer, int i2) {
        CheckboxImpl(z, toggleableState, modifier, checkboxColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TriStateCheckbox$lambda$0(ToggleableState toggleableState, Function0 function0, Modifier modifier, boolean z, MutableInteractionSource mutableInteractionSource, CheckboxColors checkboxColors, int i, int i2, Composer composer, int i3) {
        TriStateCheckbox(toggleableState, function0, modifier, z, mutableInteractionSource, checkboxColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void Checkbox(final boolean checked, final Function1<? super Boolean, Unit> function1, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, CheckboxColors colors, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        MutableInteractionSource interactionSource2;
        CheckboxColors colors2;
        final CheckboxColors colors3;
        final Modifier modifier3;
        final boolean enabled2;
        final MutableInteractionSource interactionSource3;
        Modifier modifier4;
        boolean enabled3;
        Function0 function0;
        Composer $composer2 = $composer.startRestartGroup(-2118660998);
        ComposerKt.sourceInformation($composer2, "C(Checkbox)N(checked,onCheckedChange,modifier,enabled,interactionSource,colors)91@4126L325:Checkbox.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(checked) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            interactionSource2 = interactionSource;
        } else if (($changed & 24576) == 0) {
            interactionSource2 = interactionSource;
            $dirty |= $composer2.changed(interactionSource2) ? 16384 : 8192;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                int i5 = $composer2.changed(colors2) ? 131072 : 65536;
                $dirty |= i5;
            } else {
                colors2 = colors;
            }
            $dirty |= i5;
        } else {
            colors2 = colors;
        }
        if ($composer2.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "89@4108L8");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i3 != 0 ? true : z;
                MutableInteractionSource interactionSource4 = i4 != 0 ? null : interactionSource2;
                if ((i & 32) != 0) {
                    CheckboxColors colors4 = CheckboxDefaults.INSTANCE.m1740colorszjMxDiM(0L, 0L, 0L, 0L, 0L, $composer2, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $composer2 = $composer2;
                    $dirty &= -458753;
                    modifier4 = modifier5;
                    enabled3 = enabled4;
                    colors2 = colors4;
                    interactionSource2 = interactionSource4;
                } else {
                    colors2 = colors;
                    modifier4 = modifier5;
                    enabled3 = enabled4;
                    interactionSource2 = interactionSource4;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                enabled3 = z;
                modifier4 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2118660998, $dirty, -1, "androidx.compose.material.Checkbox (Checkbox.kt:90)");
            }
            ToggleableState ToggleableState = ToggleableStateKt.ToggleableState(checked);
            if (function1 != null) {
                $composer2.startReplaceGroup(1809972427);
                ComposerKt.sourceInformation($composer2, "95@4263L29");
                ComposerKt.sourceInformationMarkerStart($composer2, -218707913, "CC(remember):Checkbox.kt#9igjgp");
                boolean invalid$iv = (($dirty & 112) == 32) | (($dirty & 14) == 4);
                Composer $this$cache$iv = $composer2;
                Object it$iv = $this$cache$iv.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = new Function0() { // from class: androidx.compose.material.CheckboxKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return CheckboxKt.Checkbox$lambda$0$0(function1, checked);
                        }
                    };
                    $this$cache$iv.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
                function0 = (Function0) it$iv;
            } else {
                $composer2.startReplaceGroup(1810037123);
                $composer2.endReplaceGroup();
                function0 = null;
            }
            TriStateCheckbox(ToggleableState, function0, modifier4, enabled3, interactionSource2, colors2, $composer2, ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enabled2 = enabled3;
            colors3 = colors2;
            interactionSource3 = interactionSource2;
        } else {
            $composer2.skipToGroupEnd();
            colors3 = colors;
            modifier3 = modifier2;
            enabled2 = z;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.CheckboxKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CheckboxKt.Checkbox$lambda$1(checked, function1, modifier3, enabled2, interactionSource3, colors3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Checkbox$lambda$0$0(Function1 $onCheckedChange, boolean $checked) {
        $onCheckedChange.invoke(Boolean.valueOf(!$checked));
        return Unit.INSTANCE;
    }

    public static final void TriStateCheckbox(final ToggleableState state, final Function0<Unit> function0, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, CheckboxColors colors, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        MutableInteractionSource mutableInteractionSource;
        CheckboxColors colors2;
        Composer $composer2;
        final CheckboxColors colors3;
        final Modifier modifier3;
        final boolean enabled2;
        final MutableInteractionSource interactionSource2;
        Modifier.Companion modifier4;
        boolean enabled3;
        MutableInteractionSource interactionSource3;
        int $dirty;
        Modifier modifier5;
        boolean enabled4;
        MutableInteractionSource interactionSource4;
        Modifier.Companion toggleableModifier;
        Modifier.Companion companionMinimumInteractiveComponentSize;
        Composer $composer3 = $composer.startRestartGroup(2031255194);
        ComposerKt.sourceInformation($composer3, "C(TriStateCheckbox)N(state,onClick,modifier,enabled,interactionSource,colors)152@6815L461:Checkbox.kt#jmzs0o");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(state.ordinal()) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(function0) ? 32 : 16;
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
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty2 |= 24576;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 24576) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty2 |= $composer3.changed(mutableInteractionSource) ? 16384 : 8192;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                int i5 = $composer3.changed(colors2) ? 131072 : 65536;
                $dirty2 |= i5;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i5;
        } else {
            colors2 = colors;
        }
        if ($composer3.shouldExecute((74899 & $dirty2) != 74898, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "137@6355L8");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                $dirty = $dirty2;
                $composer2 = $composer3;
                enabled4 = z;
                interactionSource4 = mutableInteractionSource;
                modifier5 = modifier2;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i3 == 0) {
                    enabled3 = z;
                } else {
                    enabled3 = true;
                }
                if (i4 == 0) {
                    interactionSource3 = mutableInteractionSource;
                } else {
                    interactionSource3 = null;
                }
                if ((i & 32) == 0) {
                    $composer2 = $composer3;
                    colors2 = colors;
                    $dirty = $dirty2;
                    modifier5 = modifier4;
                    enabled4 = enabled3;
                    interactionSource4 = interactionSource3;
                } else {
                    $composer2 = $composer3;
                    $dirty = $dirty2 & (-458753);
                    modifier5 = modifier4;
                    enabled4 = enabled3;
                    colors2 = CheckboxDefaults.INSTANCE.m1740colorszjMxDiM(0L, 0L, 0L, 0L, 0L, $composer2, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    interactionSource4 = interactionSource3;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2031255194, $dirty, -1, "androidx.compose.material.TriStateCheckbox (Checkbox.kt:138)");
            }
            if (function0 != null) {
                boolean enabled5 = enabled4;
                toggleableModifier = ToggleableKt.m1102triStateToggleableO2vRcR0(Modifier.INSTANCE, state, interactionSource4, RippleKt.m1907rippleH2RKhps$default(false, CheckboxRippleRadius, 0L, 4, null), enabled5, Role.m7092boximpl(Role.INSTANCE.m7101getCheckboxo7Vup1c()), function0);
                enabled4 = enabled5;
            } else {
                toggleableModifier = Modifier.INSTANCE;
            }
            if (function0 != null) {
                companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
            } else {
                companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
            }
            CheckboxColors colors4 = colors2;
            CheckboxImpl(enabled4, state, PaddingKt.m819padding3ABfNKs(modifier5.then(companionMinimumInteractiveComponentSize).then(toggleableModifier), CheckboxDefaultPadding), colors4, $composer2, (($dirty >> 9) & 14) | (($dirty << 3) & 112) | (($dirty >> 6) & 7168));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors3 = colors4;
            modifier3 = modifier5;
            enabled2 = enabled4;
            interactionSource2 = interactionSource4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            colors3 = colors;
            modifier3 = modifier2;
            enabled2 = z;
            interactionSource2 = mutableInteractionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.CheckboxKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CheckboxKt.TriStateCheckbox$lambda$0(state, function0, modifier3, enabled2, interactionSource2, colors3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void CheckboxImpl(final boolean enabled, final ToggleableState value, final Modifier modifier, final CheckboxColors colors, Composer $composer, final int $changed) {
        float f;
        float f2;
        float f3;
        float f4;
        Composer $composer2 = $composer.startRestartGroup(-2118895727);
        ComposerKt.sourceInformation($composer2, "C(CheckboxImpl)N(enabled,value,modifier,colors)257@10849L23,259@10921L499,276@11488L514,291@12024L32,292@12086L21,293@12135L24,294@12190L27,295@12300L510,295@12222L588:Checkbox.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(enabled) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(value.ordinal()) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(modifier) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(colors) ? 2048 : 1024;
        }
        if ($composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2118895727, $dirty, -1, "androidx.compose.material.CheckboxImpl (Checkbox.kt:256)");
            }
            Transition transition = TransitionKt.updateTransition(value, (String) null, $composer2, ($dirty >> 3) & 14, 2);
            Function3 transitionSpec$iv = new Function3() { // from class: androidx.compose.material.CheckboxKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return CheckboxKt.CheckboxImpl$lambda$0((Transition.Segment) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            };
            ComposerKt.sourceInformationMarkerStart($composer2, -1338768149, "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv = (0 & 14) | ((0 << 3) & 896) | ((0 << 3) & 7168) | ((0 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer2, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
            int $changed2 = ($changed$iv$iv >> 9) & 112;
            ToggleableState it = (ToggleableState) transition.getCurrentState();
            $composer2.startReplaceGroup(-1798345588);
            ComposerKt.sourceInformation($composer2, "CN(it):Checkbox.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1798345588, $changed2, -1, "androidx.compose.material.CheckboxImpl.<anonymous> (Checkbox.kt:268)");
            }
            switch (WhenMappings.$EnumSwitchMapping$0[it.ordinal()]) {
                case 1:
                    f = 1.0f;
                    break;
                case 2:
                    f = 0.0f;
                    break;
                case 3:
                    f = 1.0f;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer2.endReplaceGroup();
            Object initialValue$iv$iv = Float.valueOf(f);
            int $changed3 = ($changed$iv$iv >> 9) & 112;
            ToggleableState it2 = (ToggleableState) transition.getTargetState();
            $composer2.startReplaceGroup(-1798345588);
            ComposerKt.sourceInformation($composer2, "CN(it):Checkbox.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1798345588, $changed3, -1, "androidx.compose.material.CheckboxImpl.<anonymous> (Checkbox.kt:268)");
            }
            switch (WhenMappings.$EnumSwitchMapping$0[it2.ordinal()]) {
                case 1:
                    f2 = 1.0f;
                    break;
                case 2:
                    f2 = 0.0f;
                    break;
                case 3:
                    f2 = 1.0f;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer2.endReplaceGroup();
            Object targetValue$iv$iv = Float.valueOf(f2);
            FiniteAnimationSpec animationSpec$iv$iv = (FiniteAnimationSpec) transitionSpec$iv.invoke(transition.getSegment(), $composer2, Integer.valueOf(($changed$iv$iv >> 3) & 112));
            final State checkDrawFraction$delegate = TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv, targetValue$iv$iv, animationSpec$iv$iv, vectorConverter, "FloatAnimation", $composer2, ($changed$iv$iv & 14) | (($changed$iv$iv << 9) & 57344) | (($changed$iv$iv << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Function3 transitionSpec$iv2 = new Function3() { // from class: androidx.compose.material.CheckboxKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return CheckboxKt.CheckboxImpl$lambda$3((Transition.Segment) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            };
            ComposerKt.sourceInformationMarkerStart($composer2, -1338768149, "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv2 = (0 & 14) | ((0 << 3) & 896) | ((0 << 3) & 7168) | ((0 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer2, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
            int $changed4 = ($changed$iv$iv2 >> 9) & 112;
            ToggleableState it3 = (ToggleableState) transition.getCurrentState();
            $composer2.startReplaceGroup(-2098942571);
            ComposerKt.sourceInformation($composer2, "CN(it):Checkbox.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2098942571, $changed4, -1, "androidx.compose.material.CheckboxImpl.<anonymous> (Checkbox.kt:285)");
            }
            switch (WhenMappings.$EnumSwitchMapping$0[it3.ordinal()]) {
                case 1:
                    f3 = 0.0f;
                    break;
                case 2:
                    f3 = 0.0f;
                    break;
                case 3:
                    f3 = 1.0f;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer2.endReplaceGroup();
            Object initialValue$iv$iv2 = Float.valueOf(f3);
            int $changed5 = ($changed$iv$iv2 >> 9) & 112;
            ToggleableState it4 = (ToggleableState) transition.getTargetState();
            $composer2.startReplaceGroup(-2098942571);
            ComposerKt.sourceInformation($composer2, "CN(it):Checkbox.kt#jmzs0o");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2098942571, $changed5, -1, "androidx.compose.material.CheckboxImpl.<anonymous> (Checkbox.kt:285)");
            }
            switch (WhenMappings.$EnumSwitchMapping$0[it4.ordinal()]) {
                case 1:
                    f4 = 0.0f;
                    break;
                case 2:
                    f4 = 0.0f;
                    break;
                case 3:
                    f4 = 1.0f;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer2.endReplaceGroup();
            Object targetValue$iv$iv2 = Float.valueOf(f4);
            FiniteAnimationSpec animationSpec$iv$iv2 = (FiniteAnimationSpec) transitionSpec$iv2.invoke(transition.getSegment(), $composer2, Integer.valueOf(($changed$iv$iv2 >> 3) & 112));
            final State checkCenterGravitationShiftFraction$delegate = TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv2, targetValue$iv$iv2, animationSpec$iv$iv2, vectorConverter2, "FloatAnimation", $composer2, ($changed$iv$iv2 & 14) | (($changed$iv$iv2 << 9) & 57344) | (($changed$iv$iv2 << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -444253071, "CC(remember):Checkbox.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new CheckDrawingCache(null, null, null, 7, null);
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            final CheckDrawingCache checkCache = (CheckDrawingCache) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final State<Color> stateCheckmarkColor = colors.checkmarkColor(value, $composer2, (($dirty >> 3) & 14) | (($dirty >> 6) & 112));
            final State<Color> stateBoxColor = colors.boxColor(enabled, value, $composer2, ($dirty & 14) | ($dirty & 112) | (($dirty >> 3) & 896));
            final State<Color> stateBorderColor = colors.borderColor(enabled, value, $composer2, ($dirty & 14) | ($dirty & 112) | (($dirty >> 3) & 896));
            Modifier modifierM857requiredSize3ABfNKs = SizeKt.m857requiredSize3ABfNKs(SizeKt.wrapContentSize$default(modifier, Alignment.INSTANCE.getCenter(), false, 2, null), CheckboxSize);
            ComposerKt.sourceInformationMarkerStart($composer2, -444243761, "CC(remember):Checkbox.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(stateBoxColor) | $composer2.changed(stateBorderColor) | $composer2.changed(stateCheckmarkColor) | $composer2.changed(checkDrawFraction$delegate) | $composer2.changed(checkCenterGravitationShiftFraction$delegate);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material.CheckboxKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CheckboxKt.CheckboxImpl$lambda$10$0(checkCache, stateBoxColor, stateBorderColor, stateCheckmarkColor, checkDrawFraction$delegate, checkCenterGravitationShiftFraction$delegate, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierM857requiredSize3ABfNKs, (Function1) it$iv2, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.CheckboxKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CheckboxKt.CheckboxImpl$lambda$11(enabled, value, modifier, colors, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float CheckboxImpl$lambda$2(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    static final FiniteAnimationSpec CheckboxImpl$lambda$0(Transition.Segment $this$animateFloat, Composer $composer, int $changed) {
        SpringSpec springSpecSnap;
        $composer.startReplaceGroup(-1707702900);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1707702900, $changed, -1, "androidx.compose.material.CheckboxImpl.<anonymous> (Checkbox.kt:261)");
        }
        if ($this$animateFloat.getInitialState() == ToggleableState.Off) {
            springSpecSnap = AnimationSpecKt.tween$default(100, 0, null, 6, null);
        } else {
            springSpecSnap = $this$animateFloat.getTargetState() == ToggleableState.Off ? AnimationSpecKt.snap(100) : AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return springSpecSnap;
    }

    private static final float CheckboxImpl$lambda$5(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    static final FiniteAnimationSpec CheckboxImpl$lambda$3(Transition.Segment $this$animateFloat, Composer $composer, int $changed) {
        TweenSpec tweenSpecSnap;
        $composer.startReplaceGroup(1075283605);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1075283605, $changed, -1, "androidx.compose.material.CheckboxImpl.<anonymous> (Checkbox.kt:278)");
        }
        if ($this$animateFloat.getInitialState() == ToggleableState.Off) {
            tweenSpecSnap = AnimationSpecKt.snap$default(0, 1, null);
        } else {
            tweenSpecSnap = $this$animateFloat.getTargetState() == ToggleableState.Off ? AnimationSpecKt.snap(100) : AnimationSpecKt.tween$default(100, 0, null, 6, null);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return tweenSpecSnap;
    }

    private static final long CheckboxImpl$lambda$7(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m5107unboximpl();
    }

    private static final long CheckboxImpl$lambda$8(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m5107unboximpl();
    }

    private static final long CheckboxImpl$lambda$9(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m5107unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CheckboxImpl$lambda$10$0(CheckDrawingCache $checkCache, State $boxColor$delegate, State $borderColor$delegate, State $checkColor$delegate, State $checkDrawFraction$delegate, State $checkCenterGravitationShiftFraction$delegate, DrawScope $this$Canvas) {
        float strokeWidthPx = (float) Math.floor($this$Canvas.mo406toPx0680j_4(StrokeWidth));
        m1742drawBox1wkBAMs($this$Canvas, CheckboxImpl$lambda$8($boxColor$delegate), CheckboxImpl$lambda$9($borderColor$delegate), $this$Canvas.mo406toPx0680j_4(RadiusSize), strokeWidthPx);
        m1743drawCheck3IgeMak($this$Canvas, CheckboxImpl$lambda$7($checkColor$delegate), CheckboxImpl$lambda$2($checkDrawFraction$delegate), CheckboxImpl$lambda$5($checkCenterGravitationShiftFraction$delegate), strokeWidthPx, $checkCache);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: drawBox-1wkBAMs, reason: not valid java name */
    private static final void m1742drawBox1wkBAMs(DrawScope $this$drawBox_u2d1wkBAMs, long j, long j2, float radius, float strokeWidth) {
        float halfStrokeWidth = strokeWidth / 2.0f;
        Stroke stroke = new Stroke(strokeWidth, 0.0f, 0, 0, null, 30, null);
        long arg0$iv = $this$drawBox_u2d1wkBAMs.mo5671getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float checkboxSize = Float.intBitsToFloat(bits$iv$iv$iv);
        if (Color.m5098equalsimpl0(j, j2)) {
            long v1$iv$iv = Float.floatToRawIntBits(checkboxSize);
            long v2$iv$iv = Float.floatToRawIntBits(checkboxSize);
            long jM4912constructorimpl = Size.m4912constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
            long v1$iv$iv2 = Float.floatToRawIntBits(radius);
            long v2$iv$iv2 = Float.floatToRawIntBits(radius);
            DrawScope.m5667drawRoundRectuAw5IA$default($this$drawBox_u2d1wkBAMs, j, 0L, jM4912constructorimpl, CornerRadius.m4806constructorimpl((v1$iv$iv2 << 32) | (4294967295L & v2$iv$iv2)), Fill.INSTANCE, 0.0f, null, 0, 226, null);
            return;
        }
        long v1$iv$iv3 = Float.floatToRawIntBits(strokeWidth);
        long v2$iv$iv3 = Float.floatToRawIntBits(strokeWidth);
        long jM4844constructorimpl = Offset.m4844constructorimpl((v1$iv$iv3 << 32) | (v2$iv$iv3 & 4294967295L));
        float y$iv = strokeWidth * 2.0f;
        float height$iv = checkboxSize - (2.0f * strokeWidth);
        long v1$iv$iv4 = Float.floatToRawIntBits(checkboxSize - y$iv);
        long v2$iv$iv4 = Float.floatToRawIntBits(height$iv);
        long jM4912constructorimpl2 = Size.m4912constructorimpl((v1$iv$iv4 << 32) | (v2$iv$iv4 & 4294967295L));
        float x$iv = Math.max(0.0f, radius - strokeWidth);
        long v1$iv$iv5 = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv5 = Float.floatToRawIntBits(x$iv);
        DrawScope.m5667drawRoundRectuAw5IA$default($this$drawBox_u2d1wkBAMs, j, jM4844constructorimpl, jM4912constructorimpl2, CornerRadius.m4806constructorimpl((v1$iv$iv5 << 32) | (v2$iv$iv5 & 4294967295L)), Fill.INSTANCE, 0.0f, null, 0, 224, null);
        long v1$iv$iv6 = Float.floatToRawIntBits(halfStrokeWidth);
        long v2$iv$iv6 = Float.floatToRawIntBits(halfStrokeWidth);
        long jM4844constructorimpl2 = Offset.m4844constructorimpl((v1$iv$iv6 << 32) | (v2$iv$iv6 & 4294967295L));
        float y$iv2 = checkboxSize - strokeWidth;
        float height$iv2 = checkboxSize - strokeWidth;
        long v1$iv$iv7 = Float.floatToRawIntBits(y$iv2);
        long v2$iv$iv7 = Float.floatToRawIntBits(height$iv2);
        long jM4912constructorimpl3 = Size.m4912constructorimpl((v1$iv$iv7 << 32) | (v2$iv$iv7 & 4294967295L));
        float width$iv = radius - halfStrokeWidth;
        long v1$iv$iv8 = Float.floatToRawIntBits(width$iv);
        long v2$iv$iv8 = Float.floatToRawIntBits(width$iv);
        DrawScope.m5667drawRoundRectuAw5IA$default($this$drawBox_u2d1wkBAMs, j2, jM4844constructorimpl2, jM4912constructorimpl3, CornerRadius.m4806constructorimpl((v1$iv$iv8 << 32) | (4294967295L & v2$iv$iv8)), stroke, 0.0f, null, 0, 224, null);
    }

    /* JADX INFO: renamed from: drawCheck-3IgeMak, reason: not valid java name */
    private static final void m1743drawCheck3IgeMak(DrawScope $this$drawCheck_u2d3IgeMak, long j, float checkFraction, float crossCenterGravitation, float strokeWidthPx, CheckDrawingCache drawingCache) {
        Stroke stroke = new Stroke(strokeWidthPx, 0.0f, StrokeCap.INSTANCE.m5473getSquareKaPHkGw(), 0, null, 26, null);
        long arg0$iv = $this$drawCheck_u2d3IgeMak.mo5671getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float width = Float.intBitsToFloat(bits$iv$iv$iv);
        float gravitatedCrossX = MathHelpersKt.lerp(0.4f, 0.5f, crossCenterGravitation);
        float gravitatedCrossY = MathHelpersKt.lerp(0.7f, 0.5f, crossCenterGravitation);
        float gravitatedLeftY = MathHelpersKt.lerp(0.5f, 0.5f, crossCenterGravitation);
        float gravitatedRightY = MathHelpersKt.lerp(0.3f, 0.5f, crossCenterGravitation);
        drawingCache.getCheckPath().reset();
        drawingCache.getCheckPath().moveTo(width * 0.2f, width * gravitatedLeftY);
        drawingCache.getCheckPath().lineTo(width * gravitatedCrossX, width * gravitatedCrossY);
        drawingCache.getCheckPath().lineTo(width * 0.8f, width * gravitatedRightY);
        drawingCache.getPathMeasure().setPath(drawingCache.getCheckPath(), false);
        drawingCache.getPathToDraw().reset();
        drawingCache.getPathMeasure().getSegment(0.0f, drawingCache.getPathMeasure().getLength() * checkFraction, drawingCache.getPathToDraw(), true);
        DrawScope.m5661drawPathLG529CI$default($this$drawCheck_u2d3IgeMak, drawingCache.getPathToDraw(), j, 0.0f, stroke, null, 0, 52, null);
    }
}
