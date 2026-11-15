package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: Slider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Js\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0014X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0014X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/compose/material/SliderDefaults;", "", "<init>", "()V", "colors", "Landroidx/compose/material/SliderColors;", "thumbColor", "Landroidx/compose/ui/graphics/Color;", "disabledThumbColor", "activeTrackColor", "inactiveTrackColor", "disabledActiveTrackColor", "disabledInactiveTrackColor", "activeTickColor", "inactiveTickColor", "disabledActiveTickColor", "disabledInactiveTickColor", "colors-q0g_0yA", "(JJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material/SliderColors;", "InactiveTrackAlpha", "", "DisabledInactiveTrackAlpha", "DisabledActiveTrackAlpha", "TickAlpha", "DisabledTickAlpha", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SliderDefaults {
    public static final int $stable = 0;
    public static final float DisabledActiveTrackAlpha = 0.32f;
    public static final float DisabledInactiveTrackAlpha = 0.12f;
    public static final float DisabledTickAlpha = 0.12f;
    public static final SliderDefaults INSTANCE = new SliderDefaults();
    public static final float InactiveTrackAlpha = 0.24f;
    public static final float TickAlpha = 0.54f;

    private SliderDefaults() {
    }

    /* JADX INFO: renamed from: colors-q0g_0yA, reason: not valid java name */
    public final SliderColors m1918colorsq0g_0yA(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, Composer $composer, int $changed, int $changed1, int i) {
        long jM5142compositeOverOWjLjI;
        long jM5095copywmQWz5c;
        long jM5095copywmQWz5c2;
        long jM5095copywmQWz5c3;
        long jM5095copywmQWz5c4;
        long jM5095copywmQWz5c5;
        long jM5095copywmQWz5c6;
        ComposerKt.sourceInformationMarkerStart($composer, 436017687, "C(colors)N(thumbColor:c#ui.graphics.Color,disabledThumbColor:c#ui.graphics.Color,activeTrackColor:c#ui.graphics.Color,inactiveTrackColor:c#ui.graphics.Color,disabledActiveTrackColor:c#ui.graphics.Color,disabledInactiveTrackColor:c#ui.graphics.Color,activeTickColor:c#ui.graphics.Color,inactiveTickColor:c#ui.graphics.Color,disabledActiveTickColor:c#ui.graphics.Color,disabledInactiveTickColor:c#ui.graphics.Color)588@25750L6,590@25828L6,591@25888L8,592@25943L6,593@26008L6,596@26179L6,599@26392L33:Slider.kt#jmzs0o");
        long jM1765getPrimary0d7_KjU = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1765getPrimary0d7_KjU() : j;
        if ((i & 2) != 0) {
            long jM1764getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5142compositeOverOWjLjI = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU) : 0.0f), MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU());
        } else {
            jM5142compositeOverOWjLjI = j2;
        }
        long jM1765getPrimary0d7_KjU2 = (i & 4) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1765getPrimary0d7_KjU() : j3;
        long jM5095copywmQWz5c7 = (i & 8) != 0 ? Color.m5095copywmQWz5c(jM1765getPrimary0d7_KjU2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1765getPrimary0d7_KjU2) : 0.24f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1765getPrimary0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1765getPrimary0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1765getPrimary0d7_KjU2) : 0.0f) : j4;
        if ((i & 16) != 0) {
            long jM1764getOnSurface0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU2) : 0.32f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU2) : 0.0f);
        } else {
            jM5095copywmQWz5c = j5;
        }
        if ((i & 32) != 0) {
            long j11 = jM5095copywmQWz5c;
            jM5095copywmQWz5c2 = Color.m5095copywmQWz5c(j11, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j11) : 0.12f, (14 & 2) != 0 ? Color.m5103getRedimpl(j11) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j11) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j11) : 0.0f);
        } else {
            jM5095copywmQWz5c2 = j6;
        }
        if ((i & 64) != 0) {
            long jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1765getPrimary0d7_KjU2, $composer, ($changed >> 6) & 14);
            jM5095copywmQWz5c3 = Color.m5095copywmQWz5c(jM1783contentColorForek8zF_U, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1783contentColorForek8zF_U) : 0.54f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1783contentColorForek8zF_U) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1783contentColorForek8zF_U) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1783contentColorForek8zF_U) : 0.0f);
        } else {
            jM5095copywmQWz5c3 = j7;
        }
        if ((i & 128) != 0) {
            long j12 = jM1765getPrimary0d7_KjU2;
            jM5095copywmQWz5c4 = Color.m5095copywmQWz5c(j12, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j12) : 0.54f, (14 & 2) != 0 ? Color.m5103getRedimpl(j12) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j12) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j12) : 0.0f);
        } else {
            jM5095copywmQWz5c4 = j8;
        }
        if ((i & 256) != 0) {
            long j13 = jM5095copywmQWz5c3;
            jM5095copywmQWz5c5 = Color.m5095copywmQWz5c(j13, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j13) : 0.12f, (14 & 2) != 0 ? Color.m5103getRedimpl(j13) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j13) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j13) : 0.0f);
        } else {
            jM5095copywmQWz5c5 = j9;
        }
        if ((i & 512) != 0) {
            long j14 = jM5095copywmQWz5c2;
            jM5095copywmQWz5c6 = Color.m5095copywmQWz5c(j14, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j14) : 0.12f, (14 & 2) != 0 ? Color.m5103getRedimpl(j14) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j14) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j14) : 0.0f);
        } else {
            jM5095copywmQWz5c6 = j10;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(436017687, $changed, $changed1, "androidx.compose.material.SliderDefaults.colors (Slider.kt:605)");
        }
        DefaultSliderColors defaultSliderColors = new DefaultSliderColors(jM1765getPrimary0d7_KjU, jM5142compositeOverOWjLjI, jM1765getPrimary0d7_KjU2, jM5095copywmQWz5c7, jM5095copywmQWz5c, jM5095copywmQWz5c2, jM5095copywmQWz5c3, jM5095copywmQWz5c4, jM5095copywmQWz5c5, jM5095copywmQWz5c6, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultSliderColors;
    }
}
