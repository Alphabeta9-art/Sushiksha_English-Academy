package androidx.compose.material;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: RadioButton.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aO\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\r\"\u000e\u0010\u000e\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012\"\u0010\u0010\u0013\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012\"\u0010\u0010\u0014\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012\"\u0010\u0010\u0015\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012\"\u0010\u0010\u0016\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012\"\u0010\u0010\u0017\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012¨\u0006\u0018"}, d2 = {"RadioButton", "", "selected", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "colors", "Landroidx/compose/material/RadioButtonColors;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/RadioButtonColors;Landroidx/compose/runtime/Composer;II)V", "RadioAnimationDuration", "", "RadioButtonRippleRadius", "Landroidx/compose/ui/unit/Dp;", "F", "RadioButtonPadding", "RadioButtonSize", "RadioRadius", "RadioButtonDotSize", "RadioStrokeWidth", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RadioButtonKt {
    private static final int RadioAnimationDuration = 100;
    private static final float RadioButtonDotSize;
    private static final float RadioRadius;
    private static final float RadioStrokeWidth;
    private static final float RadioButtonRippleRadius = Dp.m7905constructorimpl(24);
    private static final float RadioButtonPadding = Dp.m7905constructorimpl(2);
    private static final float RadioButtonSize = Dp.m7905constructorimpl(20);

    static final Unit RadioButton$lambda$1(boolean z, Function0 function0, Modifier modifier, boolean z2, MutableInteractionSource mutableInteractionSource, RadioButtonColors radioButtonColors, int i, int i2, Composer composer, int i3) {
        RadioButton(z, function0, modifier, z2, mutableInteractionSource, radioButtonColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void RadioButton(final boolean selected, final Function0<Unit> function0, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, RadioButtonColors colors, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        MutableInteractionSource mutableInteractionSource;
        RadioButtonColors radioButtonColors;
        final Modifier modifier3;
        final boolean enabled2;
        final MutableInteractionSource interactionSource2;
        final RadioButtonColors colors2;
        Modifier.Companion modifier4;
        boolean enabled3;
        MutableInteractionSource interactionSource3;
        Composer $composer2;
        int $dirty;
        MutableInteractionSource interactionSource4;
        RadioButtonColors colors3;
        float arg0$iv;
        Modifier.Companion selectableModifier;
        Modifier.Companion companionMinimumInteractiveComponentSize;
        Composer $composer3 = $composer.startRestartGroup(1314435585);
        ComposerKt.sourceInformation($composer3, "C(RadioButton)N(selected,onClick,modifier,enabled,interactionSource,colors)82@3703L177,86@3909L29,113@4796L386,100@4389L793:RadioButton.kt#jmzs0o");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(selected) ? 4 : 2;
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
                radioButtonColors = colors;
                int i5 = $composer3.changed(radioButtonColors) ? 131072 : 65536;
                $dirty2 |= i5;
            } else {
                radioButtonColors = colors;
            }
            $dirty2 |= i5;
        } else {
            radioButtonColors = colors;
        }
        if ($composer3.shouldExecute((74899 & $dirty2) != 74898, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "79@3661L8");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                $dirty = $dirty2;
                modifier4 = modifier2;
                enabled3 = z;
                interactionSource4 = mutableInteractionSource;
                $composer2 = $composer3;
                colors3 = radioButtonColors;
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
                    $dirty = $dirty2;
                    interactionSource4 = interactionSource3;
                    colors3 = colors;
                } else {
                    $composer2 = $composer3;
                    $dirty = $dirty2 & (-458753);
                    colors3 = RadioButtonDefaults.INSTANCE.m1902colorsRGew2ao(0L, 0L, 0L, $composer2, 3072, 7);
                    interactionSource4 = interactionSource3;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1314435585, $dirty, -1, "androidx.compose.material.RadioButton (RadioButton.kt:80)");
            }
            if (selected) {
                float arg0$iv2 = RadioButtonDotSize;
                arg0$iv = Dp.m7905constructorimpl(arg0$iv2 / 2);
            } else {
                arg0$iv = Dp.m7905constructorimpl(0);
            }
            int $dirty3 = $dirty;
            $composer3 = $composer2;
            final State<Dp> stateM162animateDpAsStateAjpBEmI = AnimateAsStateKt.m162animateDpAsStateAjpBEmI(arg0$iv, AnimationSpecKt.tween$default(100, 0, null, 6, null), null, null, $composer3, 48, 12);
            final State<Color> stateRadioColor = colors3.radioColor(enabled3, selected, $composer3, (($dirty3 >> 9) & 14) | (($dirty3 << 3) & 112) | (($dirty3 >> 9) & 896));
            if (function0 != null) {
                enabled2 = enabled3;
                selectableModifier = SelectableKt.m1089selectableO2vRcR0(Modifier.INSTANCE, selected, interactionSource4, RippleKt.m1907rippleH2RKhps$default(false, RadioButtonRippleRadius, 0L, 4, null), enabled2, Role.m7092boximpl(Role.INSTANCE.m7104getRadioButtono7Vup1c()), function0);
            } else {
                enabled2 = enabled3;
                selectableModifier = Modifier.INSTANCE;
            }
            if (function0 != null) {
                companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
            } else {
                companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
            }
            Modifier modifierM857requiredSize3ABfNKs = SizeKt.m857requiredSize3ABfNKs(PaddingKt.m819padding3ABfNKs(SizeKt.wrapContentSize$default(modifier4.then(companionMinimumInteractiveComponentSize).then(selectableModifier), Alignment.INSTANCE.getCenter(), false, 2, null), RadioButtonPadding), RadioButtonSize);
            ComposerKt.sourceInformationMarkerStart($composer3, 452256579, "CC(remember):RadioButton.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(stateRadioColor) | $composer3.changed(stateM162animateDpAsStateAjpBEmI);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material.RadioButtonKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RadioButtonKt.RadioButton$lambda$0$0(stateRadioColor, stateM162animateDpAsStateAjpBEmI, (DrawScope) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            CanvasKt.Canvas(modifierM857requiredSize3ABfNKs, (Function1) it$iv, $composer3, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource2 = interactionSource4;
            modifier3 = modifier4;
            colors2 = colors3;
        } else {
            $composer3.skipToGroupEnd();
            modifier3 = modifier2;
            enabled2 = z;
            interactionSource2 = mutableInteractionSource;
            colors2 = colors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.RadioButtonKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RadioButtonKt.RadioButton$lambda$1(selected, function0, modifier3, enabled2, interactionSource2, colors2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RadioButton$lambda$0$0(State $radioColor, State $dotRadius, DrawScope $this$Canvas) {
        float strokeWidth = $this$Canvas.mo406toPx0680j_4(RadioStrokeWidth);
        DrawScope.m5652drawCircleVaOC9Bg$default($this$Canvas, ((Color) $radioColor.getValue()).m5107unboximpl(), $this$Canvas.mo406toPx0680j_4(RadioRadius) - (strokeWidth / 2.0f), 0L, 0.0f, new Stroke(strokeWidth, 0.0f, 0, 0, null, 30, null), null, 0, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, null);
        if (Dp.m7904compareTo0680j_4(((Dp) $dotRadius.getValue()).m7919unboximpl(), Dp.m7905constructorimpl(0)) > 0) {
            DrawScope.m5652drawCircleVaOC9Bg$default($this$Canvas, ((Color) $radioColor.getValue()).m5107unboximpl(), $this$Canvas.mo406toPx0680j_4(((Dp) $dotRadius.getValue()).m7919unboximpl()) - (strokeWidth / 2.0f), 0L, 0.0f, Fill.INSTANCE, null, 0, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, null);
        }
        return Unit.INSTANCE;
    }

    static {
        float arg0$iv = RadioButtonSize;
        RadioRadius = Dp.m7905constructorimpl(arg0$iv / 2);
        RadioButtonDotSize = Dp.m7905constructorimpl(12);
        RadioStrokeWidth = Dp.m7905constructorimpl(2);
    }
}
