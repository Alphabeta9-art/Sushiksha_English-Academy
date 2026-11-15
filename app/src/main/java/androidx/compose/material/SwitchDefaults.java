package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: Switch.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\n\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Js\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/compose/material/SwitchDefaults;", "", "<init>", "()V", "colors", "Landroidx/compose/material/SwitchColors;", "checkedThumbColor", "Landroidx/compose/ui/graphics/Color;", "checkedTrackColor", "checkedTrackAlpha", "", "uncheckedThumbColor", "uncheckedTrackColor", "uncheckedTrackAlpha", "disabledCheckedThumbColor", "disabledCheckedTrackColor", "disabledUncheckedThumbColor", "disabledUncheckedTrackColor", "colors-SQMK_m0", "(JJFJJFJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material/SwitchColors;", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SwitchDefaults {
    public static final int $stable = 0;
    public static final SwitchDefaults INSTANCE = new SwitchDefaults();

    private SwitchDefaults() {
    }

    /* JADX INFO: renamed from: colors-SQMK_m0, reason: not valid java name */
    public final SwitchColors m1966colorsSQMK_m0(long j, long j2, float checkedTrackAlpha, long j3, long j4, float uncheckedTrackAlpha, long j5, long j6, long j7, long j8, Composer $composer, int $changed, int $changed1, int i) {
        long j9;
        long jM5142compositeOverOWjLjI;
        long jM5142compositeOverOWjLjI2;
        long jM5142compositeOverOWjLjI3;
        long jM5142compositeOverOWjLjI4;
        ComposerKt.sourceInformationMarkerStart($composer, -1032127534, "C(colors)N(checkedThumbColor:c#ui.graphics.Color,checkedTrackColor:c#ui.graphics.Color,checkedTrackAlpha,uncheckedThumbColor:c#ui.graphics.Color,uncheckedTrackColor:c#ui.graphics.Color,uncheckedTrackAlpha,disabledCheckedThumbColor:c#ui.graphics.Color,disabledCheckedTrackColor:c#ui.graphics.Color,disabledUncheckedThumbColor:c#ui.graphics.Color,disabledUncheckedTrackColor:c#ui.graphics.Color)318@13166L6,321@13338L6,322@13405L6,326@13583L8,327@13638L6,330@13771L8,331@13826L6,334@13963L8,335@14018L6,338@14155L8,339@14210L6:Switch.kt#jmzs0o");
        long jM1768getSecondaryVariant0d7_KjU = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1768getSecondaryVariant0d7_KjU() : j;
        long j10 = (i & 2) != 0 ? jM1768getSecondaryVariant0d7_KjU : j2;
        float checkedTrackAlpha2 = (i & 4) != 0 ? 0.54f : checkedTrackAlpha;
        long jM1769getSurface0d7_KjU = (i & 8) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU() : j3;
        long jM1764getOnSurface0d7_KjU = (i & 16) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU() : j4;
        float uncheckedTrackAlpha2 = (i & 32) != 0 ? 0.38f : uncheckedTrackAlpha;
        if ((i & 64) != 0) {
            long j11 = jM1768getSecondaryVariant0d7_KjU;
            j9 = j11;
            jM5142compositeOverOWjLjI = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(j11, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j11) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j11) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j11) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j11) : 0.0f), MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU());
        } else {
            j9 = jM1768getSecondaryVariant0d7_KjU;
            jM5142compositeOverOWjLjI = j5;
        }
        if ((i & 128) != 0) {
            long j12 = j10;
            jM5142compositeOverOWjLjI2 = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(j12, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j12) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j12) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j12) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j12) : 0.0f), MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU());
        } else {
            jM5142compositeOverOWjLjI2 = j6;
        }
        if ((i & 256) != 0) {
            long j13 = jM1769getSurface0d7_KjU;
            jM5142compositeOverOWjLjI3 = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(j13, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j13) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j13) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j13) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j13) : 0.0f), MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU());
        } else {
            jM5142compositeOverOWjLjI3 = j7;
        }
        if ((i & 512) != 0) {
            long j14 = jM1764getOnSurface0d7_KjU;
            jM5142compositeOverOWjLjI4 = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(j14, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j14) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j14) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j14) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j14) : 0.0f), MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU());
        } else {
            jM5142compositeOverOWjLjI4 = j8;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1032127534, $changed, $changed1, "androidx.compose.material.SwitchDefaults.colors (Switch.kt:341)");
        }
        long j15 = j10;
        float checkedTrackAlpha3 = checkedTrackAlpha2;
        long jM5095copywmQWz5c = Color.m5095copywmQWz5c(j15, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j15) : checkedTrackAlpha3, (14 & 2) != 0 ? Color.m5103getRedimpl(j15) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j15) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j15) : 0.0f);
        long j16 = jM1764getOnSurface0d7_KjU;
        long jM5095copywmQWz5c2 = Color.m5095copywmQWz5c(j16, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j16) : uncheckedTrackAlpha2, (14 & 2) != 0 ? Color.m5103getRedimpl(j16) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j16) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j16) : 0.0f);
        long j17 = jM5142compositeOverOWjLjI2;
        long jM5095copywmQWz5c3 = Color.m5095copywmQWz5c(j17, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j17) : checkedTrackAlpha3, (14 & 2) != 0 ? Color.m5103getRedimpl(j17) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j17) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j17) : 0.0f);
        long j18 = jM5142compositeOverOWjLjI4;
        long j19 = j9;
        DefaultSwitchColors defaultSwitchColors = new DefaultSwitchColors(j19, jM5095copywmQWz5c, jM1769getSurface0d7_KjU, jM5095copywmQWz5c2, jM5142compositeOverOWjLjI, jM5095copywmQWz5c3, jM5142compositeOverOWjLjI3, Color.m5095copywmQWz5c(j18, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j18) : uncheckedTrackAlpha2, (14 & 2) != 0 ? Color.m5103getRedimpl(j18) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j18) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j18) : 0.0f), null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultSwitchColors;
    }
}
