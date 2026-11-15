package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: compiled from: Checkbox.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JA\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/material/CheckboxDefaults;", "", "<init>", "()V", "colors", "Landroidx/compose/material/CheckboxColors;", "checkedColor", "Landroidx/compose/ui/graphics/Color;", "uncheckedColor", "checkmarkColor", "disabledColor", "disabledIndeterminateColor", "colors-zjMxDiM", "(JJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/CheckboxColors;", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CheckboxDefaults {
    public static final int $stable = 0;
    public static final CheckboxDefaults INSTANCE = new CheckboxDefaults();

    private CheckboxDefaults() {
    }

    /* JADX INFO: renamed from: colors-zjMxDiM, reason: not valid java name */
    public final CheckboxColors m1740colorszjMxDiM(long j, long j2, long j3, long j4, long j5, Composer $composer, int $changed, int i) {
        long jM5095copywmQWz5c;
        long jM5095copywmQWz5c2;
        long j6;
        long jM5095copywmQWz5c3;
        ComposerKt.sourceInformationMarkerStart($composer, 469524104, "C(colors)N(checkedColor:c#ui.graphics.Color,uncheckedColor:c#ui.graphics.Color,checkmarkColor:c#ui.graphics.Color,disabledColor:c#ui.graphics.Color,disabledIndeterminateColor:c#ui.graphics.Color)220@9357L6,221@9421L6,222@9504L6,223@9565L6,223@9608L8,224@9702L8,226@9752L922:Checkbox.kt#jmzs0o");
        long jM1767getSecondary0d7_KjU = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1767getSecondary0d7_KjU() : j;
        if ((i & 2) != 0) {
            long jM1764getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU) : 0.6f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU) : 0.0f);
        } else {
            jM5095copywmQWz5c = j2;
        }
        long jM1769getSurface0d7_KjU = (i & 4) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU() : j3;
        if ((i & 8) != 0) {
            long jM1764getOnSurface0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c2 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU2) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU2) : 0.0f);
        } else {
            jM5095copywmQWz5c2 = j4;
        }
        if ((i & 16) != 0) {
            long j7 = jM1767getSecondary0d7_KjU;
            jM5095copywmQWz5c3 = Color.m5095copywmQWz5c(j7, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j7) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j7) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j7) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j7) : 0.0f);
            j6 = j7;
        } else {
            j6 = jM1767getSecondary0d7_KjU;
            jM5095copywmQWz5c3 = j5;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(469524104, $changed, -1, "androidx.compose.material.CheckboxDefaults.colors (Checkbox.kt:225)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 241113218, "CC(remember):Checkbox.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(j6)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(jM5095copywmQWz5c)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(jM1769getSurface0d7_KjU)) || ($changed & 384) == 256) | (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(jM5095copywmQWz5c2)) || ($changed & 3072) == 2048) | ((((57344 & $changed) ^ 24576) > 16384 && $composer.changed(jM5095copywmQWz5c3)) || ($changed & 24576) == 16384);
        Object value$iv = $composer.rememberedValue();
        if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
            long j8 = jM1769getSurface0d7_KjU;
            long jM5095copywmQWz5c4 = Color.m5095copywmQWz5c(j8, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j8) : 0.0f, (14 & 2) != 0 ? Color.m5103getRedimpl(j8) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j8) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j8) : 0.0f);
            long j9 = j6;
            long jM5095copywmQWz5c5 = Color.m5095copywmQWz5c(j9, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j9) : 0.0f, (14 & 2) != 0 ? Color.m5103getRedimpl(j9) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j9) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j9) : 0.0f);
            long j10 = jM5095copywmQWz5c2;
            value$iv = new DefaultCheckboxColors(j8, jM5095copywmQWz5c4, j9, jM5095copywmQWz5c5, j10, Color.m5095copywmQWz5c(j10, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j10) : 0.0f, (14 & 2) != 0 ? Color.m5103getRedimpl(j10) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j10) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j10) : 0.0f), jM5095copywmQWz5c3, j9, jM5095copywmQWz5c, j10, jM5095copywmQWz5c3, null);
            $composer.updateRememberedValue(value$iv);
        }
        DefaultCheckboxColors defaultCheckboxColors = (DefaultCheckboxColors) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultCheckboxColors;
    }
}
