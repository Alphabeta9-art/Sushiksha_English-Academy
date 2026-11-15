package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: compiled from: RadioButton.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/material/RadioButtonDefaults;", "", "<init>", "()V", "colors", "Landroidx/compose/material/RadioButtonColors;", "selectedColor", "Landroidx/compose/ui/graphics/Color;", "unselectedColor", "disabledColor", "colors-RGew2ao", "(JJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/RadioButtonColors;", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RadioButtonDefaults {
    public static final int $stable = 0;
    public static final RadioButtonDefaults INSTANCE = new RadioButtonDefaults();

    private RadioButtonDefaults() {
    }

    /* JADX INFO: renamed from: colors-RGew2ao, reason: not valid java name */
    public final RadioButtonColors m1902colorsRGew2ao(long j, long j2, long j3, Composer $composer, int $changed, int i) {
        long jM5095copywmQWz5c;
        long jM5095copywmQWz5c2;
        ComposerKt.sourceInformationMarkerStart($composer, 1370708026, "C(colors)N(selectedColor:c#ui.graphics.Color,unselectedColor:c#ui.graphics.Color,disabledColor:c#ui.graphics.Color)158@6429L6,159@6494L6,160@6576L6,160@6619L8,162@6672L151:RadioButton.kt#jmzs0o");
        long jM1767getSecondary0d7_KjU = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1767getSecondary0d7_KjU() : j;
        if ((i & 2) == 0) {
            jM5095copywmQWz5c = j2;
        } else {
            long jM1764getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU) : 0.6f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU) : 0.0f);
        }
        if ((i & 4) == 0) {
            jM5095copywmQWz5c2 = j3;
        } else {
            long jM1764getOnSurface0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c2 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU2) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU2) : 0.0f);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1370708026, $changed, -1, "androidx.compose.material.RadioButtonDefaults.colors (RadioButton.kt:161)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -1304537135, "CC(remember):RadioButton.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(jM1767getSecondary0d7_KjU)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(jM5095copywmQWz5c)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(jM5095copywmQWz5c2)) || ($changed & 384) == 256);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new DefaultRadioButtonColors(jM1767getSecondary0d7_KjU, jM5095copywmQWz5c, jM5095copywmQWz5c2, null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        DefaultRadioButtonColors defaultRadioButtonColors = (DefaultRadioButtonColors) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultRadioButtonColors;
    }
}
