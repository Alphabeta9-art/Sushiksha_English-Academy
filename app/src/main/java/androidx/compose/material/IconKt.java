package androidx.compose.material;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.PainterModifierKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.painter.BitmapPainter;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.VectorPainter;
import androidx.compose.ui.graphics.vector.VectorPainterKt;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Icon.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a5\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a5\u0010\u0000\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0004\b\n\u0010\u000e\u001a5\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0004\b\n\u0010\u0011\u001a\u0014\u0010\u0012\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u001a\u0013\u0010\u0013\u001a\u00020\u0014*\u00020\u0015H\u0002¢\u0006\u0004\b\u0016\u0010\u0017\"\u000e\u0010\u0018\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Icon", "", "imageVector", "Landroidx/compose/ui/graphics/vector/ImageVector;", "contentDescription", "", "modifier", "Landroidx/compose/ui/Modifier;", "tint", "Landroidx/compose/ui/graphics/Color;", "Icon-ww6aTOc", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "bitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "(Landroidx/compose/ui/graphics/ImageBitmap;Ljava/lang/String;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "(Landroidx/compose/ui/graphics/painter/Painter;Ljava/lang/String;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "defaultSizeFor", "isInfinite", "", "Landroidx/compose/ui/geometry/Size;", "isInfinite-uvyYCjk", "(J)Z", "DefaultIconSizeModifier", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class IconKt {
    private static final Modifier DefaultIconSizeModifier = SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(24));

    static final Unit Icon_ww6aTOc$lambda$3(Painter painter, String str, Modifier modifier, long j, int i, int i2, Composer composer, int i3) {
        m1848Iconww6aTOc(painter, str, modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: Icon-ww6aTOc, reason: not valid java name */
    public static final void m1849Iconww6aTOc(ImageVector imageVector, String contentDescription, Modifier modifier, long j, Composer $composer, int $changed, int i) {
        long jM5095copywmQWz5c;
        ComposerKt.sourceInformationMarkerStart($composer, -800853103, "C(Icon)N(imageVector,contentDescription,modifier,tint:c#ui.graphics.Color)65@3157L7,65@3196L7,68@3238L34,67@3214L164:Icon.kt#jmzs0o");
        Modifier modifier2 = (i & 4) != 0 ? Modifier.INSTANCE : modifier;
        if ((i & 8) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            long jM5107unboximpl = ((Color) objConsume).m5107unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd($composer);
            jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM5107unboximpl, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5107unboximpl) : ((Number) objConsume2).floatValue(), (14 & 2) != 0 ? Color.m5103getRedimpl(jM5107unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5107unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5107unboximpl) : 0.0f);
        } else {
            jM5095copywmQWz5c = j;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-800853103, $changed, -1, "androidx.compose.material.Icon (Icon.kt:66)");
        }
        m1848Iconww6aTOc(VectorPainterKt.rememberVectorPainter(imageVector, $composer, $changed & 14), contentDescription, modifier2, jM5095copywmQWz5c, $composer, ($changed & 7168) | VectorPainter.$stable | ($changed & 112) | ($changed & 896), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    /* JADX INFO: renamed from: Icon-ww6aTOc, reason: not valid java name */
    public static final void m1847Iconww6aTOc(ImageBitmap bitmap, String contentDescription, Modifier modifier, long j, Composer $composer, int $changed, int i) {
        long jM5095copywmQWz5c;
        ComposerKt.sourceInformationMarkerStart($composer, -554892675, "C(Icon)N(bitmap,contentDescription,modifier,tint:c#ui.graphics.Color)99@4818L7,99@4857L7,101@4889L42,102@4936L137:Icon.kt#jmzs0o");
        Modifier modifier2 = (i & 4) != 0 ? Modifier.INSTANCE : modifier;
        if ((i & 8) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            long jM5107unboximpl = ((Color) objConsume).m5107unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd($composer);
            jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM5107unboximpl, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5107unboximpl) : ((Number) objConsume2).floatValue(), (14 & 2) != 0 ? Color.m5103getRedimpl(jM5107unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5107unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5107unboximpl) : 0.0f);
        } else {
            jM5095copywmQWz5c = j;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-554892675, $changed, -1, "androidx.compose.material.Icon (Icon.kt:100)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -675909209, "CC(remember):Icon.kt#9igjgp");
        boolean invalid$iv = $composer.changed(bitmap);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new BitmapPainter(bitmap, 0L, 0L, 6, null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        BitmapPainter painter = (BitmapPainter) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        m1848Iconww6aTOc(painter, contentDescription, modifier2, jM5095copywmQWz5c, $composer, ($changed & 7168) | ($changed & 112) | ($changed & 896), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    /* JADX INFO: renamed from: Icon-ww6aTOc, reason: not valid java name */
    public static final void m1848Iconww6aTOc(final Painter painter, final String contentDescription, Modifier modifier, long j, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j2;
        final Modifier modifier3;
        final long j3;
        Modifier.Companion modifier4;
        long jM5095copywmQWz5c;
        int $dirty;
        Modifier modifier5;
        long j4;
        Object value$iv;
        Modifier.Companion companionSemantics$default;
        Composer $composer2 = $composer.startRestartGroup(-1142959010);
        ComposerKt.sourceInformation($composer2, "C(Icon)N(painter,contentDescription,modifier,tint:c#ui.graphics.Color)136@6567L82,146@6911L217:Icon.kt#jmzs0o");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(painter) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changed(contentDescription) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j2 = j;
                int i3 = $composer2.changed(j2) ? 2048 : 1024;
                $dirty2 |= i3;
            } else {
                j2 = j;
            }
            $dirty2 |= i3;
        } else {
            j2 = j;
        }
        if ($composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "133@6484L7,133@6523L7");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                long j5 = j2;
                modifier5 = modifier2;
                jM5095copywmQWz5c = j5;
                $dirty = $dirty2;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 8) == 0) {
                    jM5095copywmQWz5c = j2;
                    $dirty = $dirty2;
                    modifier5 = modifier4;
                } else {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer2.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    long jM5107unboximpl = ((Color) objConsume).m5107unboximpl();
                    ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume2 = $composer2.consume(localContentAlpha);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM5107unboximpl, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5107unboximpl) : ((Number) objConsume2).floatValue(), (14 & 2) != 0 ? Color.m5103getRedimpl(jM5107unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5107unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5107unboximpl) : 0.0f);
                    $dirty = $dirty2 & (-7169);
                    modifier5 = modifier4;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1142959010, $dirty, -1, "androidx.compose.material.Icon (Icon.kt:134)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1128026256, "CC(remember):Icon.kt#9igjgp");
            boolean invalid$iv = ((($dirty & 7168) ^ 3072) > 2048 && $composer2.changed(jM5095copywmQWz5c)) || ($dirty & 3072) == 2048;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                if (Color.m5098equalsimpl0(jM5095copywmQWz5c, Color.INSTANCE.m5133getUnspecified0d7_KjU())) {
                    j4 = jM5095copywmQWz5c;
                    value$iv = null;
                } else {
                    j4 = jM5095copywmQWz5c;
                    value$iv = ColorFilter.Companion.m5138tintxETnrds$default(ColorFilter.INSTANCE, j4, 0, 2, null);
                }
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            } else {
                j4 = jM5095copywmQWz5c;
            }
            ColorFilter colorFilter = (ColorFilter) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (contentDescription != null) {
                $composer2.startReplaceGroup(609219782);
                ComposerKt.sourceInformation($composer2, "139@6743L115");
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, 1128031921, "CC(remember):Icon.kt#9igjgp");
                boolean invalid$iv2 = ($dirty & 112) == 32;
                Object it$iv2 = $composer2.rememberedValue();
                if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = new Function1() { // from class: androidx.compose.material.IconKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return IconKt.Icon_ww6aTOc$lambda$2$0(contentDescription, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                companionSemantics$default = SemanticsModifierKt.semantics$default(companion, false, (Function1) it$iv2, 1, null);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(609378564);
                $composer2.endReplaceGroup();
                companionSemantics$default = Modifier.INSTANCE;
            }
            Modifier semantics = companionSemantics$default;
            BoxKt.Box(PainterModifierKt.paint(defaultSizeFor(GraphicsLayerModifierKt.toolingGraphicsLayer(modifier5), painter), painter, (22 & 2) != 0, (22 & 4) != 0 ? Alignment.INSTANCE.getCenter() : null, (22 & 8) != 0 ? ContentScale.INSTANCE.getInside() : ContentScale.INSTANCE.getFit(), (22 & 16) != 0 ? 1.0f : 0.0f, (22 & 32) != 0 ? null : colorFilter).then(semantics), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            j3 = j4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = j2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.IconKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return IconKt.Icon_ww6aTOc$lambda$3(painter, contentDescription, modifier3, j3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Icon_ww6aTOc$lambda$2$0(String $contentDescription, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContentDescription($this$semantics, $contentDescription);
        SemanticsPropertiesKt.m7118setRolekuIjeqM($this$semantics, Role.INSTANCE.m7103getImageo7Vup1c());
        return Unit.INSTANCE;
    }

    private static final Modifier defaultSizeFor(Modifier $this$defaultSizeFor, Painter painter) {
        Modifier.Companion companion;
        if (Size.m4917equalsimpl0(painter.getIntrinsicSize(), Size.INSTANCE.m4929getUnspecifiedNHjbRc()) || m1850isInfiniteuvyYCjk(painter.getIntrinsicSize())) {
            companion = DefaultIconSizeModifier;
        } else {
            companion = Modifier.INSTANCE;
        }
        return $this$defaultSizeFor.then(companion);
    }

    /* JADX INFO: renamed from: isInfinite-uvyYCjk, reason: not valid java name */
    private static final boolean m1850isInfiniteuvyYCjk(long j) {
        int bits$iv$iv$iv = (int) (j >> 32);
        if (Float.isInfinite(Float.intBitsToFloat(bits$iv$iv$iv))) {
            int bits$iv$iv$iv2 = (int) (4294967295L & j);
            if (Float.isInfinite(Float.intBitsToFloat(bits$iv$iv$iv2))) {
                return true;
            }
        }
        return false;
    }
}
