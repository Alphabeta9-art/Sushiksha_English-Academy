package androidx.compose.material;

import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.media3.exoplayer.offline.DownloadService;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: compiled from: MaterialTextSelectionColors.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u000e\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¢\u0006\u0002\u0010\u0004\u001a'\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a'\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000e\u0010\u000f\u001a/\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001f\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u0016\u0010\u0017\"\u000e\u0010\u0018\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"rememberTextSelectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "colors", "Landroidx/compose/material/Colors;", "(Landroidx/compose/material/Colors;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/text/selection/TextSelectionColors;", "calculateSelectionBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "selectionColor", "textColor", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "calculateSelectionBackgroundColor-ysEtTa8", "(JJJ)J", "binarySearchForAccessibleSelectionColorAlpha", "", "binarySearchForAccessibleSelectionColorAlpha-ysEtTa8", "(JJJ)F", "calculateContrastRatio", "selectionColorAlpha", "calculateContrastRatio-nb2GgbA", "(JFJJ)F", DownloadService.KEY_FOREGROUND, "background", "calculateContrastRatio--OWjLjI", "(JJ)F", "DefaultSelectionBackgroundAlpha", "MinimumSelectionBackgroundAlpha", "DesiredContrastRatio", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class MaterialTextSelectionColorsKt {
    private static final float DefaultSelectionBackgroundAlpha = 0.4f;
    private static final float DesiredContrastRatio = 4.5f;
    private static final float MinimumSelectionBackgroundAlpha = 0.2f;

    public static final SelectionColors rememberTextSelectionColors(Colors colors, Composer $composer, int $changed) {
        long jM5107unboximpl;
        ComposerKt.sourceInformationMarkerStart($composer, -721696685, "C(rememberTextSelectionColors)N(colors)45@1907L6,46@1926L418:MaterialTextSelectionColors.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-721696685, $changed, -1, "androidx.compose.material.rememberTextSelectionColors (MaterialTextSelectionColors.kt:35)");
        }
        long primaryColor = colors.m1765getPrimary0d7_KjU();
        long backgroundColor = colors.m1758getBackground0d7_KjU();
        $composer.startReplaceGroup(-2060762245);
        ComposerKt.sourceInformation($composer, "*44@1858L7");
        long $this$takeOrElse_u2dDxMtmZc$iv = ColorsKt.m1782contentColorFor4WTKRHQ(colors, backgroundColor);
        if ($this$takeOrElse_u2dDxMtmZc$iv != 16) {
            jM5107unboximpl = $this$takeOrElse_u2dDxMtmZc$iv;
        } else {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            jM5107unboximpl = ((Color) objConsume).m5107unboximpl();
        }
        $composer.endReplaceGroup();
        long textColorWithLowestAlpha = Color.m5095copywmQWz5c(jM5107unboximpl, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5107unboximpl) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM5107unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5107unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5107unboximpl) : 0.0f);
        ComposerKt.sourceInformationMarkerStart($composer, -2060758699, "CC(remember):MaterialTextSelectionColors.kt#9igjgp");
        boolean invalid$iv = $composer.changed(primaryColor) | $composer.changed(backgroundColor) | $composer.changed(textColorWithLowestAlpha);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new SelectionColors(colors.m1765getPrimary0d7_KjU(), m1856calculateSelectionBackgroundColorysEtTa8(primaryColor, textColorWithLowestAlpha, backgroundColor), null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        SelectionColors selectionColors = (SelectionColors) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return selectionColors;
    }

    /* JADX INFO: renamed from: calculateSelectionBackgroundColor-ysEtTa8, reason: not valid java name */
    public static final long m1856calculateSelectionBackgroundColorysEtTa8(long j, long j2, long j3) {
        float alpha;
        float maximumContrastRatio = m1855calculateContrastRationb2GgbA(j, 0.4f, j2, j3);
        float minimumContrastRatio = m1855calculateContrastRationb2GgbA(j, 0.2f, j2, j3);
        if (maximumContrastRatio >= DesiredContrastRatio) {
            alpha = 0.4f;
        } else {
            alpha = minimumContrastRatio < DesiredContrastRatio ? 0.2f : m1853binarySearchForAccessibleSelectionColorAlphaysEtTa8(j, j2, j3);
        }
        return Color.m5095copywmQWz5c(j, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j) : alpha, (14 & 2) != 0 ? Color.m5103getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j) : 0.0f);
    }

    /* JADX INFO: renamed from: binarySearchForAccessibleSelectionColorAlpha-ysEtTa8, reason: not valid java name */
    private static final float m1853binarySearchForAccessibleSelectionColorAlphaysEtTa8(long j, long j2, long j3) {
        float lowAlpha = 0.2f;
        float alpha = 0.4f;
        float highAlpha = 0.4f;
        for (int attempts = 0; attempts < 7; attempts++) {
            float contrastRatio = m1855calculateContrastRationb2GgbA(j, alpha, j2, j3);
            float percentageError = (contrastRatio / DesiredContrastRatio) - 1.0f;
            boolean z = false;
            if (0.0f <= percentageError && percentageError <= 0.01f) {
                z = true;
            }
            if (z) {
                break;
            }
            if (percentageError < 0.0f) {
                highAlpha = alpha;
            } else {
                lowAlpha = alpha;
            }
            alpha = (highAlpha + lowAlpha) / 2.0f;
        }
        return alpha;
    }

    /* JADX INFO: renamed from: calculateContrastRatio-nb2GgbA, reason: not valid java name */
    private static final float m1855calculateContrastRationb2GgbA(long j, float selectionColorAlpha, long j2, long j3) {
        long compositeBackground = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(j, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j) : selectionColorAlpha, (14 & 2) != 0 ? Color.m5103getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j) : 0.0f), j3);
        long compositeTextColor = ColorKt.m5142compositeOverOWjLjI(j2, compositeBackground);
        return m1854calculateContrastRatioOWjLjI(compositeTextColor, compositeBackground);
    }

    /* JADX INFO: renamed from: calculateContrastRatio--OWjLjI, reason: not valid java name */
    public static final float m1854calculateContrastRatioOWjLjI(long j, long j2) {
        float foregroundLuminance = ColorKt.m5149luminance8_81llA(j) + 0.05f;
        float backgroundLuminance = ColorKt.m5149luminance8_81llA(j2) + 0.05f;
        return Math.max(foregroundLuminance, backgroundLuminance) / Math.min(foregroundLuminance, backgroundLuminance);
    }
}
