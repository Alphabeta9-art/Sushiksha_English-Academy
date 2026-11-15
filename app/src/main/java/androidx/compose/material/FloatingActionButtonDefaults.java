package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;

/* JADX INFO: compiled from: FloatingActionButton.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\t\u0010\nJ7\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/compose/material/FloatingActionButtonDefaults;", "", "<init>", "()V", "elevation", "Landroidx/compose/material/FloatingActionButtonElevation;", "defaultElevation", "Landroidx/compose/ui/unit/Dp;", "pressedElevation", "elevation-ixp7dh8", "(FFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/FloatingActionButtonElevation;", "hoveredElevation", "focusedElevation", "elevation-xZ9-QkE", "(FFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/FloatingActionButtonElevation;", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class FloatingActionButtonDefaults {
    public static final int $stable = 0;
    public static final FloatingActionButtonDefaults INSTANCE = new FloatingActionButtonDefaults();

    private FloatingActionButtonDefaults() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use another overload of elevation")
    /* JADX INFO: renamed from: elevation-ixp7dh8, reason: not valid java name */
    public final /* synthetic */ FloatingActionButtonElevation m1839elevationixp7dh8(float f, float f2, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -654132828, "C(elevation)N(defaultElevation:c#ui.unit.Dp,pressedElevation:c#ui.unit.Dp)215@9540L154:FloatingActionButton.kt#jmzs0o");
        float fM7905constructorimpl = (i & 1) != 0 ? Dp.m7905constructorimpl(6) : f;
        float fM7905constructorimpl2 = (i & 2) != 0 ? Dp.m7905constructorimpl(12) : f2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-654132828, $changed, -1, "androidx.compose.material.FloatingActionButtonDefaults.elevation (FloatingActionButton.kt:215)");
        }
        int $this$dp$iv = $changed & 14;
        FloatingActionButtonElevation floatingActionButtonElevationM1840elevationxZ9QkE = m1840elevationxZ9QkE(fM7905constructorimpl, fM7905constructorimpl2, Dp.m7905constructorimpl(8), Dp.m7905constructorimpl(8), $composer, $this$dp$iv | 3456 | ($changed & 112) | (($changed << 6) & 57344), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return floatingActionButtonElevationM1840elevationxZ9QkE;
    }

    /* JADX INFO: renamed from: elevation-xZ9-QkE, reason: not valid java name */
    public final FloatingActionButtonElevation m1840elevationxZ9QkE(float f, float f2, float f3, float f4, Composer $composer, int $changed, int i) {
        float fM7905constructorimpl;
        float fM7905constructorimpl2;
        float fM7905constructorimpl3;
        float fM7905constructorimpl4;
        ComposerKt.sourceInformationMarkerStart($composer, 380403812, "C(elevation)N(defaultElevation:c#ui.unit.Dp,pressedElevation:c#ui.unit.Dp,hoveredElevation:c#ui.unit.Dp,focusedElevation:c#ui.unit.Dp)239@10503L368:FloatingActionButton.kt#jmzs0o");
        if ((i & 1) == 0) {
            fM7905constructorimpl = f;
        } else {
            fM7905constructorimpl = Dp.m7905constructorimpl(6);
        }
        if ((i & 2) == 0) {
            fM7905constructorimpl2 = f2;
        } else {
            fM7905constructorimpl2 = Dp.m7905constructorimpl(12);
        }
        if ((i & 4) == 0) {
            fM7905constructorimpl3 = f3;
        } else {
            fM7905constructorimpl3 = Dp.m7905constructorimpl(8);
        }
        if ((i & 8) == 0) {
            fM7905constructorimpl4 = f4;
        } else {
            fM7905constructorimpl4 = Dp.m7905constructorimpl(8);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(380403812, $changed, -1, "androidx.compose.material.FloatingActionButtonDefaults.elevation (FloatingActionButton.kt:238)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 495850516, "CC(remember):FloatingActionButton.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(fM7905constructorimpl)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(fM7905constructorimpl2)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(fM7905constructorimpl3)) || ($changed & 384) == 256) | (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(fM7905constructorimpl4)) || ($changed & 3072) == 2048);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new DefaultFloatingActionButtonElevation(fM7905constructorimpl, fM7905constructorimpl2, fM7905constructorimpl3, fM7905constructorimpl4, null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        DefaultFloatingActionButtonElevation defaultFloatingActionButtonElevation = (DefaultFloatingActionButtonElevation) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultFloatingActionButtonElevation;
    }
}
