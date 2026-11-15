package androidx.compose.material;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;

/* JADX INFO: compiled from: Button.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u001a\u0010\u001bJA\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u00052\b\b\u0002\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u001e\u0010\u001fJ7\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020#2\b\b\u0002\u0010%\u001a\u00020#2\b\b\u0002\u0010&\u001a\u00020#H\u0007¢\u0006\u0004\b'\u0010(J-\u0010)\u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020#2\b\b\u0002\u0010&\u001a\u00020#H\u0007¢\u0006\u0004\b*\u0010+J-\u0010,\u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020#2\b\b\u0002\u0010&\u001a\u00020#H\u0007¢\u0006\u0004\b-\u0010+R\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u000f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\u0011\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0012\u0010\u000eR\u0013\u0010\u0013\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0014\u0010\u000eR\u000e\u0010.\u001a\u00020/X\u0086T¢\u0006\u0002\n\u0000R\u0013\u00100\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b1\u0010\u000eR\u0011\u00102\u001a\u0002038G¢\u0006\u0006\u001a\u0004\b4\u00105R\u0010\u00106\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0011\u00107\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u000b¨\u00069"}, d2 = {"Landroidx/compose/material/ButtonDefaults;", "", "<init>", "()V", "ButtonHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "ButtonVerticalPadding", "ContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "MinWidth", "getMinWidth-D9Ej5fM", "()F", "MinHeight", "getMinHeight-D9Ej5fM", "IconSize", "getIconSize-D9Ej5fM", "IconSpacing", "getIconSpacing-D9Ej5fM", "elevation", "Landroidx/compose/material/ButtonElevation;", "defaultElevation", "pressedElevation", "disabledElevation", "elevation-yajeYGU", "(FFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ButtonElevation;", "hoveredElevation", "focusedElevation", "elevation-R_JCAzs", "(FFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ButtonElevation;", "buttonColors", "Landroidx/compose/material/ButtonColors;", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "contentColor", "disabledBackgroundColor", "disabledContentColor", "buttonColors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ButtonColors;", "outlinedButtonColors", "outlinedButtonColors-RGew2ao", "(JJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ButtonColors;", "textButtonColors", "textButtonColors-RGew2ao", "OutlinedBorderOpacity", "", "OutlinedBorderSize", "getOutlinedBorderSize-D9Ej5fM", "outlinedBorder", "Landroidx/compose/foundation/BorderStroke;", "getOutlinedBorder", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/BorderStroke;", "TextButtonHorizontalPadding", "TextButtonContentPadding", "getTextButtonContentPadding", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ButtonDefaults {
    public static final int $stable = 0;
    public static final float OutlinedBorderOpacity = 0.12f;
    private static final PaddingValues TextButtonContentPadding;
    public static final ButtonDefaults INSTANCE = new ButtonDefaults();
    private static final float ButtonHorizontalPadding = Dp.m7905constructorimpl(16);
    private static final float ButtonVerticalPadding = Dp.m7905constructorimpl(8);
    private static final PaddingValues ContentPadding = PaddingKt.m815PaddingValuesa9UjIt4(ButtonHorizontalPadding, ButtonVerticalPadding, ButtonHorizontalPadding, ButtonVerticalPadding);
    private static final float MinWidth = Dp.m7905constructorimpl(64);
    private static final float MinHeight = Dp.m7905constructorimpl(36);
    private static final float IconSize = Dp.m7905constructorimpl(18);
    private static final float IconSpacing = Dp.m7905constructorimpl(8);
    private static final float OutlinedBorderSize = Dp.m7905constructorimpl(1);
    private static final float TextButtonHorizontalPadding = Dp.m7905constructorimpl(8);

    private ButtonDefaults() {
    }

    static {
        float f = TextButtonHorizontalPadding;
        ButtonDefaults buttonDefaults = INSTANCE;
        float top = ContentPadding.getTop();
        float f2 = TextButtonHorizontalPadding;
        ButtonDefaults buttonDefaults2 = INSTANCE;
        TextButtonContentPadding = PaddingKt.m815PaddingValuesa9UjIt4(f, top, f2, ContentPadding.getBottom());
    }

    public final PaddingValues getContentPadding() {
        return ContentPadding;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m1733getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m1732getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1730getIconSizeD9Ej5fM() {
        return IconSize;
    }

    /* JADX INFO: renamed from: getIconSpacing-D9Ej5fM, reason: not valid java name */
    public final float m1731getIconSpacingD9Ej5fM() {
        return IconSpacing;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use another overload of elevation")
    /* JADX INFO: renamed from: elevation-yajeYGU, reason: not valid java name */
    public final /* synthetic */ ButtonElevation m1729elevationyajeYGU(float f, float f2, float f3, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1428576874, "C(elevation)N(defaultElevation:c#ui.unit.Dp,pressedElevation:c#ui.unit.Dp,disabledElevation:c#ui.unit.Dp)347@14836L185:Button.kt#jmzs0o");
        float fM7905constructorimpl = (i & 1) != 0 ? Dp.m7905constructorimpl(2) : f;
        float fM7905constructorimpl2 = (i & 2) != 0 ? Dp.m7905constructorimpl(8) : f2;
        float fM7905constructorimpl3 = (i & 4) != 0 ? Dp.m7905constructorimpl(0) : f3;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1428576874, $changed, -1, "androidx.compose.material.ButtonDefaults.elevation (Button.kt:347)");
        }
        int $this$dp$iv = $changed & 14;
        ButtonElevation buttonElevationM1728elevationR_JCAzs = m1728elevationR_JCAzs(fM7905constructorimpl, fM7905constructorimpl2, fM7905constructorimpl3, Dp.m7905constructorimpl(4), Dp.m7905constructorimpl(4), $composer, $this$dp$iv | 27648 | ($changed & 112) | ($changed & 896) | (($changed << 6) & 458752), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return buttonElevationM1728elevationR_JCAzs;
    }

    /* JADX INFO: renamed from: elevation-R_JCAzs, reason: not valid java name */
    public final ButtonElevation m1728elevationR_JCAzs(float f, float f2, float f3, float f4, float f5, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -737170518, "C(elevation)N(defaultElevation:c#ui.unit.Dp,pressedElevation:c#ui.unit.Dp,disabledElevation:c#ui.unit.Dp,hoveredElevation:c#ui.unit.Dp,focusedElevation:c#ui.unit.Dp)375@15987L499:Button.kt#jmzs0o");
        float fM7905constructorimpl = (i & 1) != 0 ? Dp.m7905constructorimpl(2) : f;
        float fM7905constructorimpl2 = (i & 2) != 0 ? Dp.m7905constructorimpl(8) : f2;
        float fM7905constructorimpl3 = (i & 4) != 0 ? Dp.m7905constructorimpl(0) : f3;
        float fM7905constructorimpl4 = (i & 8) != 0 ? Dp.m7905constructorimpl(4) : f4;
        float fM7905constructorimpl5 = (i & 16) != 0 ? Dp.m7905constructorimpl(4) : f5;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-737170518, $changed, -1, "androidx.compose.material.ButtonDefaults.elevation (Button.kt:374)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 248744253, "CC(remember):Button.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(fM7905constructorimpl)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(fM7905constructorimpl2)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(fM7905constructorimpl3)) || ($changed & 384) == 256) | (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(fM7905constructorimpl4)) || ($changed & 3072) == 2048) | ((((57344 & $changed) ^ 24576) > 16384 && $composer.changed(fM7905constructorimpl5)) || ($changed & 24576) == 16384);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new DefaultButtonElevation(fM7905constructorimpl, fM7905constructorimpl2, fM7905constructorimpl3, fM7905constructorimpl4, fM7905constructorimpl5, null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        DefaultButtonElevation defaultButtonElevation = (DefaultButtonElevation) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultButtonElevation;
    }

    /* JADX INFO: renamed from: buttonColors-ro_MJ88, reason: not valid java name */
    public final ButtonColors m1727buttonColorsro_MJ88(long j, long j2, long j3, long j4, Composer $composer, int $changed, int i) {
        long jM5142compositeOverOWjLjI;
        long jM5095copywmQWz5c;
        ComposerKt.sourceInformationMarkerStart($composer, 1870371134, "C(buttonColors)N(backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,disabledBackgroundColor:c#ui.graphics.Color,disabledContentColor:c#ui.graphics.Color)403@17055L6,404@17101L32,406@17202L6,408@17301L6,410@17382L6,410@17425L8:Button.kt#jmzs0o");
        long jM1765getPrimary0d7_KjU = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1765getPrimary0d7_KjU() : j;
        long jM1783contentColorForek8zF_U = (i & 2) != 0 ? ColorsKt.m1783contentColorForek8zF_U(jM1765getPrimary0d7_KjU, $composer, $changed & 14) : j2;
        if ((i & 4) != 0) {
            long jM1764getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5142compositeOverOWjLjI = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU) : 0.12f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU) : 0.0f), MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU());
        } else {
            jM5142compositeOverOWjLjI = j3;
        }
        if ((i & 8) != 0) {
            long jM1764getOnSurface0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU2) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU2) : 0.0f);
        } else {
            jM5095copywmQWz5c = j4;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1870371134, $changed, -1, "androidx.compose.material.ButtonDefaults.buttonColors (Button.kt:412)");
        }
        DefaultButtonColors defaultButtonColors = new DefaultButtonColors(jM1765getPrimary0d7_KjU, jM1783contentColorForek8zF_U, jM5142compositeOverOWjLjI, jM5095copywmQWz5c, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultButtonColors;
    }

    /* JADX INFO: renamed from: outlinedButtonColors-RGew2ao, reason: not valid java name */
    public final ButtonColors m1735outlinedButtonColorsRGew2ao(long j, long j2, long j3, Composer $composer, int $changed, int i) {
        long jM5095copywmQWz5c;
        ComposerKt.sourceInformationMarkerStart($composer, -2124406093, "C(outlinedButtonColors)N(backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,disabledContentColor:c#ui.graphics.Color)429@18202L6,430@18262L6,432@18342L6,432@18385L8:Button.kt#jmzs0o");
        long jM1769getSurface0d7_KjU = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU() : j;
        long jM1765getPrimary0d7_KjU = (i & 2) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1765getPrimary0d7_KjU() : j2;
        if ((i & 4) != 0) {
            long jM1764getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU) : 0.0f);
        } else {
            jM5095copywmQWz5c = j3;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2124406093, $changed, -1, "androidx.compose.material.ButtonDefaults.outlinedButtonColors (Button.kt:434)");
        }
        DefaultButtonColors defaultButtonColors = new DefaultButtonColors(jM1769getSurface0d7_KjU, jM1765getPrimary0d7_KjU, jM1769getSurface0d7_KjU, jM5095copywmQWz5c, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultButtonColors;
    }

    /* JADX INFO: renamed from: textButtonColors-RGew2ao, reason: not valid java name */
    public final ButtonColors m1736textButtonColorsRGew2ao(long j, long j2, long j3, Composer $composer, int $changed, int i) {
        long jM5095copywmQWz5c;
        ComposerKt.sourceInformationMarkerStart($composer, 182742216, "C(textButtonColors)N(backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,disabledContentColor:c#ui.graphics.Color)452@19182L6,454@19262L6,454@19305L8:Button.kt#jmzs0o");
        long jM5132getTransparent0d7_KjU = (i & 1) != 0 ? Color.INSTANCE.m5132getTransparent0d7_KjU() : j;
        long jM1765getPrimary0d7_KjU = (i & 2) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1765getPrimary0d7_KjU() : j2;
        if ((i & 4) != 0) {
            long jM1764getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU) : 0.0f);
        } else {
            jM5095copywmQWz5c = j3;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(182742216, $changed, -1, "androidx.compose.material.ButtonDefaults.textButtonColors (Button.kt:456)");
        }
        DefaultButtonColors defaultButtonColors = new DefaultButtonColors(jM5132getTransparent0d7_KjU, jM1765getPrimary0d7_KjU, jM5132getTransparent0d7_KjU, jM5095copywmQWz5c, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultButtonColors;
    }

    /* JADX INFO: renamed from: getOutlinedBorderSize-D9Ej5fM, reason: not valid java name */
    public final float m1734getOutlinedBorderSizeD9Ej5fM() {
        return OutlinedBorderSize;
    }

    public final BorderStroke getOutlinedBorder(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -2091313033, "C(<get-outlinedBorder>)475@20035L6:Button.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2091313033, $changed, -1, "androidx.compose.material.ButtonDefaults.<get-outlinedBorder> (Button.kt:473)");
        }
        float f = OutlinedBorderSize;
        long jM1764getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
        BorderStroke borderStrokeM291BorderStrokecXLIe8U = BorderStrokeKt.m291BorderStrokecXLIe8U(f, Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU) : 0.12f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU) : 0.0f));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return borderStrokeM291BorderStrokecXLIe8U;
    }

    public final PaddingValues getTextButtonContentPadding() {
        return TextButtonContentPadding;
    }
}
