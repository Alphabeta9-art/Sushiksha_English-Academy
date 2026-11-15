package androidx.compose.material;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: compiled from: Chip.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JK\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0011\u001a\u00020\fH\u0007¢\u0006\u0004\b\u0012\u0010\u0013JK\u0010\u0014\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0011\u001a\u00020\fH\u0007¢\u0006\u0004\b\u0015\u0010\u0013Ji\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0019\u001a\u00020\f2\b\b\u0002\u0010\u001a\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\f2\b\b\u0002\u0010\u001c\u001a\u00020\fH\u0007¢\u0006\u0004\b\u001d\u0010\u001eJi\u0010\u001f\u001a\u00020\u00172\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0019\u001a\u00020\f2\b\b\u0002\u0010\u001a\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\f2\b\b\u0002\u0010\u001c\u001a\u00020\fH\u0007¢\u0006\u0004\b \u0010\u001eR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010!\u001a\u00020\"8G¢\u0006\u0006\u001a\u0004\b#\u0010$R\u000e\u0010%\u001a\u00020&X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020&X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020&X\u0086T¢\u0006\u0002\n\u0000R\u0013\u0010)\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b*\u0010\u0007R\u0013\u0010+\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b,\u0010\u0007R\u0013\u0010-\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b.\u0010\u0007¨\u0006/"}, d2 = {"Landroidx/compose/material/ChipDefaults;", "", "<init>", "()V", "MinHeight", "Landroidx/compose/ui/unit/Dp;", "getMinHeight-D9Ej5fM", "()F", "F", "chipColors", "Landroidx/compose/material/ChipColors;", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "contentColor", "leadingIconContentColor", "disabledBackgroundColor", "disabledContentColor", "disabledLeadingIconContentColor", "chipColors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ChipColors;", "outlinedChipColors", "outlinedChipColors-5tl4gsc", "filterChipColors", "Landroidx/compose/material/SelectableChipColors;", "leadingIconColor", "disabledLeadingIconColor", "selectedBackgroundColor", "selectedContentColor", "selectedLeadingIconColor", "filterChipColors-J08w3-E", "(JJJJJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/SelectableChipColors;", "outlinedFilterChipColors", "outlinedFilterChipColors-J08w3-E", "outlinedBorder", "Landroidx/compose/foundation/BorderStroke;", "getOutlinedBorder", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/BorderStroke;", "LeadingIconOpacity", "", "ContentOpacity", "OutlinedBorderOpacity", "OutlinedBorderSize", "getOutlinedBorderSize-D9Ej5fM", "LeadingIconSize", "getLeadingIconSize-D9Ej5fM", "SelectedIconSize", "getSelectedIconSize-D9Ej5fM", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ChipDefaults {
    public static final int $stable = 0;
    public static final float ContentOpacity = 0.87f;
    public static final float LeadingIconOpacity = 0.54f;
    public static final float OutlinedBorderOpacity = 0.12f;
    public static final ChipDefaults INSTANCE = new ChipDefaults();
    private static final float MinHeight = Dp.m7905constructorimpl(32);
    private static final float OutlinedBorderSize = Dp.m7905constructorimpl(1);
    private static final float LeadingIconSize = Dp.m7905constructorimpl(20);
    private static final float SelectedIconSize = Dp.m7905constructorimpl(18);

    private ChipDefaults() {
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m1747getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* JADX INFO: renamed from: chipColors-5tl4gsc, reason: not valid java name */
    public final ChipColors m1744chipColors5tl4gsc(long j, long j2, long j3, long j4, long j5, long j6, Composer $composer, int $changed, int i) {
        long jM5142compositeOverOWjLjI;
        long jM5095copywmQWz5c;
        long jM5142compositeOverOWjLjI2;
        long jM5095copywmQWz5c2;
        long jM5095copywmQWz5c3;
        ComposerKt.sourceInformationMarkerStart($composer, 1838505436, "C(chipColors)N(backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,leadingIconContentColor:c#ui.graphics.Color,disabledBackgroundColor:c#ui.graphics.Color,disabledContentColor:c#ui.graphics.Color,disabledLeadingIconContentColor:c#ui.graphics.Color)375@16985L6,377@17100L6,378@17161L6,381@17363L6,382@17423L8,383@17502L6,385@17608L8,387@17747L8:Chip.kt#jmzs0o");
        if ((i & 1) != 0) {
            long jM1764getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5142compositeOverOWjLjI = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU) : 0.12f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU) : 0.0f), MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU());
        } else {
            jM5142compositeOverOWjLjI = j;
        }
        if ((i & 2) != 0) {
            long jM1764getOnSurface0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU2) : 0.87f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU2) : 0.0f);
        } else {
            jM5095copywmQWz5c = j2;
        }
        long jM5095copywmQWz5c4 = (i & 4) != 0 ? Color.m5095copywmQWz5c(jM5095copywmQWz5c, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5095copywmQWz5c) : 0.54f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM5095copywmQWz5c) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5095copywmQWz5c) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5095copywmQWz5c) : 0.0f) : j3;
        if ((i & 8) != 0) {
            long jM1764getOnSurface0d7_KjU3 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5142compositeOverOWjLjI2 = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU3) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.12f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU3) : 0.0f), MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU());
        } else {
            jM5142compositeOverOWjLjI2 = j4;
        }
        if ((i & 16) != 0) {
            long j7 = jM5095copywmQWz5c;
            jM5095copywmQWz5c2 = Color.m5095copywmQWz5c(j7, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j7) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.87f, (14 & 2) != 0 ? Color.m5103getRedimpl(j7) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j7) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j7) : 0.0f);
        } else {
            jM5095copywmQWz5c2 = j5;
        }
        if ((i & 32) != 0) {
            long j8 = jM5095copywmQWz5c4;
            jM5095copywmQWz5c3 = Color.m5095copywmQWz5c(j8, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j8) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.54f, (14 & 2) != 0 ? Color.m5103getRedimpl(j8) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j8) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j8) : 0.0f);
        } else {
            jM5095copywmQWz5c3 = j6;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1838505436, $changed, -1, "androidx.compose.material.ChipDefaults.chipColors (Chip.kt:389)");
        }
        DefaultChipColors defaultChipColors = new DefaultChipColors(jM5142compositeOverOWjLjI, jM5095copywmQWz5c, jM5095copywmQWz5c4, jM5142compositeOverOWjLjI2, jM5095copywmQWz5c2, jM5095copywmQWz5c3, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultChipColors;
    }

    /* JADX INFO: renamed from: outlinedChipColors-5tl4gsc, reason: not valid java name */
    public final ChipColors m1750outlinedChipColors5tl4gsc(long j, long j2, long j3, long j4, long j5, long j6, Composer $composer, int $changed, int i) {
        long jM5095copywmQWz5c;
        long jM5095copywmQWz5c2;
        long jM5095copywmQWz5c3;
        ComposerKt.sourceInformationMarkerStart($composer, -1763922662, "C(outlinedChipColors)N(backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,leadingIconContentColor:c#ui.graphics.Color,disabledBackgroundColor:c#ui.graphics.Color,disabledContentColor:c#ui.graphics.Color,disabledLeadingIconContentColor:c#ui.graphics.Color)413@19076L6,414@19136L6,418@19418L8,420@19557L8,422@19617L371:Chip.kt#jmzs0o");
        long jM1769getSurface0d7_KjU = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU() : j;
        if ((i & 2) != 0) {
            long jM1764getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU) : 0.87f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU) : 0.0f);
        } else {
            jM5095copywmQWz5c = j2;
        }
        long jM5095copywmQWz5c4 = (i & 4) != 0 ? Color.m5095copywmQWz5c(jM5095copywmQWz5c, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5095copywmQWz5c) : 0.54f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM5095copywmQWz5c) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5095copywmQWz5c) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5095copywmQWz5c) : 0.0f) : j3;
        long j7 = (i & 8) != 0 ? jM1769getSurface0d7_KjU : j4;
        if ((i & 16) != 0) {
            long j8 = jM5095copywmQWz5c;
            jM5095copywmQWz5c2 = Color.m5095copywmQWz5c(j8, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j8) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.87f, (14 & 2) != 0 ? Color.m5103getRedimpl(j8) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j8) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j8) : 0.0f);
        } else {
            jM5095copywmQWz5c2 = j5;
        }
        if ((i & 32) != 0) {
            long j9 = jM5095copywmQWz5c4;
            jM5095copywmQWz5c3 = Color.m5095copywmQWz5c(j9, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j9) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.54f, (14 & 2) != 0 ? Color.m5103getRedimpl(j9) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j9) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j9) : 0.0f);
        } else {
            jM5095copywmQWz5c3 = j6;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1763922662, $changed, -1, "androidx.compose.material.ChipDefaults.outlinedChipColors (Chip.kt:422)");
        }
        ChipColors chipColorsM1744chipColors5tl4gsc = m1744chipColors5tl4gsc(jM1769getSurface0d7_KjU, jM5095copywmQWz5c, jM5095copywmQWz5c4, j7, jM5095copywmQWz5c2, jM5095copywmQWz5c3, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | ($changed & 7168) | (57344 & $changed) | (458752 & $changed) | (3670016 & $changed), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return chipColorsM1744chipColors5tl4gsc;
    }

    /* JADX INFO: renamed from: filterChipColors-J08w3-E, reason: not valid java name */
    public final SelectableChipColors m1745filterChipColorsJ08w3E(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, Composer $composer, int $changed, int i) {
        long jM5142compositeOverOWjLjI;
        long jM5095copywmQWz5c;
        long jM5142compositeOverOWjLjI2;
        long jM5095copywmQWz5c2;
        long jM5095copywmQWz5c3;
        long jM5142compositeOverOWjLjI3;
        long jM5142compositeOverOWjLjI4;
        long jM5142compositeOverOWjLjI5;
        ComposerKt.sourceInformationMarkerStart($composer, 830140629, "C(filterChipColors)N(backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,leadingIconColor:c#ui.graphics.Color,disabledBackgroundColor:c#ui.graphics.Color,disabledContentColor:c#ui.graphics.Color,disabledLeadingIconColor:c#ui.graphics.Color,selectedBackgroundColor:c#ui.graphics.Color,selectedContentColor:c#ui.graphics.Color,selectedLeadingIconColor:c#ui.graphics.Color)448@20997L6,450@21112L6,451@21173L6,454@21360L6,455@21420L8,456@21499L6,458@21605L8,460@21730L8,462@21829L6,466@22012L6,470@22197L6:Chip.kt#jmzs0o");
        if ((i & 1) != 0) {
            long jM1764getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5142compositeOverOWjLjI = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU) : 0.12f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU) : 0.0f), MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU());
        } else {
            jM5142compositeOverOWjLjI = j;
        }
        if ((i & 2) != 0) {
            long jM1764getOnSurface0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU2) : 0.87f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU2) : 0.0f);
        } else {
            jM5095copywmQWz5c = j2;
        }
        long jM5095copywmQWz5c4 = (i & 4) != 0 ? Color.m5095copywmQWz5c(jM5095copywmQWz5c, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5095copywmQWz5c) : 0.54f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM5095copywmQWz5c) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5095copywmQWz5c) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5095copywmQWz5c) : 0.0f) : j3;
        if ((i & 8) != 0) {
            long jM1764getOnSurface0d7_KjU3 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5142compositeOverOWjLjI2 = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU3) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.12f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU3) : 0.0f), MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU());
        } else {
            jM5142compositeOverOWjLjI2 = j4;
        }
        if ((i & 16) != 0) {
            long j10 = jM5095copywmQWz5c;
            jM5095copywmQWz5c2 = Color.m5095copywmQWz5c(j10, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j10) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.87f, (14 & 2) != 0 ? Color.m5103getRedimpl(j10) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j10) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j10) : 0.0f);
        } else {
            jM5095copywmQWz5c2 = j5;
        }
        if ((i & 32) != 0) {
            long j11 = jM5095copywmQWz5c4;
            jM5095copywmQWz5c3 = Color.m5095copywmQWz5c(j11, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j11) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.54f, (14 & 2) != 0 ? Color.m5103getRedimpl(j11) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j11) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j11) : 0.0f);
        } else {
            jM5095copywmQWz5c3 = j6;
        }
        if ((i & 64) != 0) {
            long jM1764getOnSurface0d7_KjU4 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5142compositeOverOWjLjI3 = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU4, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU4) : 0.12f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU4) : 0.0f), jM5142compositeOverOWjLjI);
        } else {
            jM5142compositeOverOWjLjI3 = j7;
        }
        if ((i & 128) != 0) {
            long jM1764getOnSurface0d7_KjU5 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5142compositeOverOWjLjI4 = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU5, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU5) : 0.16f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU5) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU5) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU5) : 0.0f), jM5095copywmQWz5c);
        } else {
            jM5142compositeOverOWjLjI4 = j8;
        }
        if ((i & 256) != 0) {
            long jM1764getOnSurface0d7_KjU6 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5142compositeOverOWjLjI5 = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU6, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU6) : 0.16f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU6) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU6) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU6) : 0.0f), jM5095copywmQWz5c4);
        } else {
            jM5142compositeOverOWjLjI5 = j9;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(830140629, $changed, -1, "androidx.compose.material.ChipDefaults.filterChipColors (Chip.kt:474)");
        }
        DefaultSelectableChipColors defaultSelectableChipColors = new DefaultSelectableChipColors(jM5142compositeOverOWjLjI, jM5095copywmQWz5c, jM5095copywmQWz5c4, jM5142compositeOverOWjLjI2, jM5095copywmQWz5c2, jM5095copywmQWz5c3, jM5142compositeOverOWjLjI3, jM5142compositeOverOWjLjI4, jM5142compositeOverOWjLjI5, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultSelectableChipColors;
    }

    /* JADX INFO: renamed from: outlinedFilterChipColors-J08w3-E, reason: not valid java name */
    public final SelectableChipColors m1751outlinedFilterChipColorsJ08w3E(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, Composer $composer, int $changed, int i) {
        long jM5095copywmQWz5c;
        long jM5095copywmQWz5c2;
        long jM5095copywmQWz5c3;
        long jM5142compositeOverOWjLjI;
        long jM5142compositeOverOWjLjI2;
        long jM5142compositeOverOWjLjI3;
        ComposerKt.sourceInformationMarkerStart($composer, 346878099, "C(outlinedFilterChipColors)N(backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,leadingIconColor:c#ui.graphics.Color,disabledBackgroundColor:c#ui.graphics.Color,disabledContentColor:c#ui.graphics.Color,disabledLeadingIconColor:c#ui.graphics.Color,selectedBackgroundColor:c#ui.graphics.Color,selectedContentColor:c#ui.graphics.Color,selectedLeadingIconColor:c#ui.graphics.Color)502@23909L6,503@23969L6,507@24228L8,509@24353L8,511@24452L6,515@24636L6,519@24821L6:Chip.kt#jmzs0o");
        long jM1769getSurface0d7_KjU = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU() : j;
        if ((i & 2) != 0) {
            long jM1764getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU) : 0.87f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU) : 0.0f);
        } else {
            jM5095copywmQWz5c = j2;
        }
        long jM5095copywmQWz5c4 = (i & 4) != 0 ? Color.m5095copywmQWz5c(jM5095copywmQWz5c, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5095copywmQWz5c) : 0.54f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM5095copywmQWz5c) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5095copywmQWz5c) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5095copywmQWz5c) : 0.0f) : j3;
        long j10 = (i & 8) != 0 ? jM1769getSurface0d7_KjU : j4;
        if ((i & 16) != 0) {
            long j11 = jM5095copywmQWz5c;
            jM5095copywmQWz5c2 = Color.m5095copywmQWz5c(j11, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j11) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.87f, (14 & 2) != 0 ? Color.m5103getRedimpl(j11) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j11) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j11) : 0.0f);
        } else {
            jM5095copywmQWz5c2 = j5;
        }
        if ((i & 32) != 0) {
            long j12 = jM5095copywmQWz5c4;
            jM5095copywmQWz5c3 = Color.m5095copywmQWz5c(j12, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j12) : ContentAlpha.INSTANCE.getDisabled($composer, 6) * 0.54f, (14 & 2) != 0 ? Color.m5103getRedimpl(j12) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j12) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j12) : 0.0f);
        } else {
            jM5095copywmQWz5c3 = j6;
        }
        if ((i & 64) != 0) {
            long jM1764getOnSurface0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5142compositeOverOWjLjI = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU2) : 0.16f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU2) : 0.0f), jM1769getSurface0d7_KjU);
        } else {
            jM5142compositeOverOWjLjI = j7;
        }
        if ((i & 128) != 0) {
            long jM1764getOnSurface0d7_KjU3 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5142compositeOverOWjLjI2 = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU3) : 0.16f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU3) : 0.0f), jM5095copywmQWz5c);
        } else {
            jM5142compositeOverOWjLjI2 = j8;
        }
        if ((i & 256) != 0) {
            long jM1764getOnSurface0d7_KjU4 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5142compositeOverOWjLjI3 = ColorKt.m5142compositeOverOWjLjI(Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU4, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU4) : 0.16f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU4) : 0.0f), jM5095copywmQWz5c4);
        } else {
            jM5142compositeOverOWjLjI3 = j9;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(346878099, $changed, -1, "androidx.compose.material.ChipDefaults.outlinedFilterChipColors (Chip.kt:523)");
        }
        DefaultSelectableChipColors defaultSelectableChipColors = new DefaultSelectableChipColors(jM1769getSurface0d7_KjU, jM5095copywmQWz5c, jM5095copywmQWz5c4, j10, jM5095copywmQWz5c2, jM5095copywmQWz5c3, jM5142compositeOverOWjLjI, jM5142compositeOverOWjLjI2, jM5142compositeOverOWjLjI3, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultSelectableChipColors;
    }

    public final BorderStroke getOutlinedBorder(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1650225597, "C(<get-outlinedBorder>)541@25750L6:Chip.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1650225597, $changed, -1, "androidx.compose.material.ChipDefaults.<get-outlinedBorder> (Chip.kt:539)");
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

    /* JADX INFO: renamed from: getOutlinedBorderSize-D9Ej5fM, reason: not valid java name */
    public final float m1748getOutlinedBorderSizeD9Ej5fM() {
        return OutlinedBorderSize;
    }

    /* JADX INFO: renamed from: getLeadingIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1746getLeadingIconSizeD9Ej5fM() {
        return LeadingIconSize;
    }

    /* JADX INFO: renamed from: getSelectedIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1749getSelectedIconSizeD9Ej5fM() {
        return SelectedIconSize;
    }
}
