package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: compiled from: Snackbar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078G¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u00078G¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Landroidx/compose/material/SnackbarDefaults;", "", "<init>", "()V", "SnackbarOverlayAlpha", "", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "getBackgroundColor", "(Landroidx/compose/runtime/Composer;I)J", "primaryActionColor", "getPrimaryActionColor", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SnackbarDefaults {
    public static final int $stable = 0;
    public static final SnackbarDefaults INSTANCE = new SnackbarDefaults();
    private static final float SnackbarOverlayAlpha = 0.8f;

    private SnackbarDefaults() {
    }

    public final long getBackgroundColor(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1630911716, "C(<get-backgroundColor>)201@8577L6,203@8691L6:Snackbar.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1630911716, $changed, -1, "androidx.compose.material.SnackbarDefaults.<get-backgroundColor> (Snackbar.kt:201)");
        }
        long jM1764getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
        long jM5142compositeOverOWjLjI = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU) : 0.8f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU) : 0.0f), MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU());
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return jM5142compositeOverOWjLjI;
    }

    public final long getPrimaryActionColor(Composer $composer, int $changed) {
        long primary;
        ComposerKt.sourceInformationMarkerStart($composer, -810329402, "C(<get-primaryActionColor>)222@9639L6:Snackbar.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-810329402, $changed, -1, "androidx.compose.material.SnackbarDefaults.<get-primaryActionColor> (Snackbar.kt:221)");
        }
        Colors colors = MaterialTheme.INSTANCE.getColors($composer, 6);
        if (colors.isLight()) {
            long primary2 = colors.m1765getPrimary0d7_KjU();
            long jM1769getSurface0d7_KjU = colors.m1769getSurface0d7_KjU();
            long overlayColor = Color.m5095copywmQWz5c(jM1769getSurface0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1769getSurface0d7_KjU) : 0.6f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1769getSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1769getSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1769getSurface0d7_KjU) : 0.0f);
            primary = ColorKt.m5142compositeOverOWjLjI(overlayColor, primary2);
        } else {
            primary = colors.m1766getPrimaryVariant0d7_KjU();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return primary;
    }
}
