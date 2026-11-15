package androidx.compose.material;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Divider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\u001a7\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\t\u0010\n\"\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Divider", "", "modifier", "Landroidx/compose/ui/Modifier;", "color", "Landroidx/compose/ui/graphics/Color;", "thickness", "Landroidx/compose/ui/unit/Dp;", "startIndent", "Divider-oMI9zvI", "(Landroidx/compose/ui/Modifier;JFFLandroidx/compose/runtime/Composer;II)V", "DividerAlpha", "", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DividerKt {
    private static final float DividerAlpha = 0.12f;

    static final Unit Divider_oMI9zvI$lambda$0(Modifier modifier, long j, float f, float f2, int i, int i2, Composer composer, int i3) {
        m1798DivideroMI9zvI(modifier, j, f, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: Divider-oMI9zvI, reason: not valid java name */
    public static final void m1798DivideroMI9zvI(Modifier modifier, long j, float f, float f2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j2;
        float fM7905constructorimpl;
        float f3;
        final Modifier modifier3;
        final long j3;
        final float f4;
        final float f5;
        Modifier.Companion modifier4;
        long jM5095copywmQWz5c;
        float fM7905constructorimpl2;
        Modifier.Companion indentMod;
        float targetThickness;
        Composer $composer2 = $composer.startRestartGroup(-1249392198);
        ComposerKt.sourceInformation($composer2, "C(Divider)N(modifier,color:c#ui.graphics.Color,thickness:c#ui.unit.Dp,startIndent:c#ui.unit.Dp)63@2271L94:Divider.kt#jmzs0o");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                j2 = j;
                int i3 = $composer2.changed(j2) ? 32 : 16;
                $dirty |= i3;
            } else {
                j2 = j;
            }
            $dirty |= i3;
        } else {
            j2 = j;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            fM7905constructorimpl = f;
        } else if (($changed & 384) == 0) {
            fM7905constructorimpl = f;
            $dirty |= $composer2.changed(fM7905constructorimpl) ? 256 : 128;
        } else {
            fM7905constructorimpl = f;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            f3 = f2;
        } else if (($changed & 3072) == 0) {
            f3 = f2;
            $dirty |= $composer2.changed(f3) ? 2048 : 1024;
        } else {
            f3 = f2;
        }
        if (!$composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = j2;
            f4 = fM7905constructorimpl;
            f5 = f3;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "47@1841L6");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                modifier4 = modifier2;
                jM5095copywmQWz5c = j2;
                fM7905constructorimpl2 = f3;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 2) == 0) {
                    jM5095copywmQWz5c = j2;
                } else {
                    long jM1764getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer2, 6).m1764getOnSurface0d7_KjU();
                    jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU) : 0.12f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU) : 0.0f);
                    $dirty &= -113;
                }
                if (i4 != 0) {
                    fM7905constructorimpl = Dp.m7905constructorimpl(1);
                }
                if (i5 == 0) {
                    fM7905constructorimpl2 = f3;
                } else {
                    fM7905constructorimpl2 = Dp.m7905constructorimpl(0);
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1249392198, $dirty, -1, "androidx.compose.material.Divider (Divider.kt:50)");
            }
            if (!(fM7905constructorimpl2 == 0.0f)) {
                indentMod = PaddingKt.m823paddingqDBjuR0$default(Modifier.INSTANCE, fM7905constructorimpl2, 0.0f, 0.0f, 0.0f, 14, null);
            } else {
                indentMod = Modifier.INSTANCE;
            }
            if (Dp.m7910equalsimpl0(fM7905constructorimpl, Dp.INSTANCE.m7923getHairlineD9Ej5fM())) {
                $composer2.startReplaceGroup(-455979798);
                ComposerKt.sourceInformation($composer2, "59@2198L7");
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                float $this$dp$iv = 1.0f / ((Density) objConsume).getDensity();
                targetThickness = Dp.m7905constructorimpl($this$dp$iv);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-455913241);
                $composer2.endReplaceGroup();
                targetThickness = fM7905constructorimpl;
            }
            BoxKt.Box(BackgroundKt.m265backgroundbw27NRU$default(SizeKt.m851height3ABfNKs(SizeKt.fillMaxWidth$default(modifier4.then(indentMod), 0.0f, 1, null), targetThickness), jM5095copywmQWz5c, null, 2, null), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            j3 = jM5095copywmQWz5c;
            f4 = fM7905constructorimpl;
            f5 = fM7905constructorimpl2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.DividerKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DividerKt.Divider_oMI9zvI$lambda$0(modifier3, j3, f4, f5, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
