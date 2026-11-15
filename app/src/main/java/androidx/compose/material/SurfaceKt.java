package androidx.compose.material;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.foundation.selection.ToggleableKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Surface.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a`\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0084\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u008c\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00142\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0092\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00142\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u001e2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b\u001a\u0010\u001f\u001a5\u0010 \u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0004\b\"\u0010#\u001a)\u0010$\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010'\u001a\u00020\fH\u0003¢\u0006\u0004\b(\u0010)¨\u0006*"}, d2 = {"Surface", "", "modifier", "Landroidx/compose/ui/Modifier;", "shape", "Landroidx/compose/ui/graphics/Shape;", "color", "Landroidx/compose/ui/graphics/Color;", "contentColor", OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "elevation", "Landroidx/compose/ui/unit/Dp;", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Surface-F-jzlyU", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "onClick", "enabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Surface-LPr_se0", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "selected", "Surface-Ny5ogXk", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "checked", "onCheckedChange", "Lkotlin/Function1;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "surface", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "surface-8ww4TTg", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JLandroidx/compose/foundation/BorderStroke;F)Landroidx/compose/ui/Modifier;", "surfaceColorAtElevation", "elevationOverlay", "Landroidx/compose/material/ElevationOverlay;", "absoluteElevation", "surfaceColorAtElevation-cq6XJ1M", "(JLandroidx/compose/material/ElevationOverlay;FLandroidx/compose/runtime/Composer;I)J", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SurfaceKt {
    static final Unit Surface_F_jzlyU$lambda$1(Modifier modifier, Shape shape, long j, long j2, BorderStroke borderStroke, float f, Function2 function2, int i, int i2, Composer composer, int i3) {
        m1954SurfaceFjzlyU(modifier, shape, j, j2, borderStroke, f, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Surface_LPr_se0$lambda$1(Function0 function0, Modifier modifier, boolean z, Shape shape, long j, long j2, BorderStroke borderStroke, float f, MutableInteractionSource mutableInteractionSource, Function2 function2, int i, int i2, Composer composer, int i3) {
        m1955SurfaceLPr_se0(function0, modifier, z, shape, j, j2, borderStroke, f, mutableInteractionSource, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Surface_Ny5ogXk$lambda$1(boolean z, Function0 function0, Modifier modifier, boolean z2, Shape shape, long j, long j2, BorderStroke borderStroke, float f, MutableInteractionSource mutableInteractionSource, Function2 function2, int i, int i2, int i3, Composer composer, int i4) {
        m1956SurfaceNy5ogXk(z, (Function0<Unit>) function0, modifier, z2, shape, j, j2, borderStroke, f, mutableInteractionSource, (Function2<? super Composer, ? super Integer, Unit>) function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit Surface_Ny5ogXk$lambda$3(boolean z, Function1 function1, Modifier modifier, boolean z2, Shape shape, long j, long j2, BorderStroke borderStroke, float f, MutableInteractionSource mutableInteractionSource, Function2 function2, int i, int i2, int i3, Composer composer, int i4) {
        m1957SurfaceNy5ogXk(z, (Function1<? super Boolean, Unit>) function1, modifier, z2, shape, j, j2, borderStroke, f, mutableInteractionSource, (Function2<? super Composer, ? super Integer, Unit>) function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: Surface-F-jzlyU, reason: not valid java name */
    public static final void m1954SurfaceFjzlyU(Modifier modifier, Shape shape, long j, long j2, BorderStroke border, float f, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        long jM1769getSurface0d7_KjU;
        long jM1783contentColorForek8zF_U;
        BorderStroke border2;
        final Shape shape3;
        final long j3;
        final Modifier modifier3;
        final long j4;
        final BorderStroke border3;
        final float f2;
        final float fM7905constructorimpl;
        final Shape shape4;
        final long j5;
        final BorderStroke border4;
        Composer $composer2 = $composer.startRestartGroup(174096871);
        ComposerKt.sourceInformation($composer2, "C(Surface)N(modifier,shape,color:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,border,elevation:c#ui.unit.Dp,content)102@5257L7,106@5421L878,103@5281L1018:Surface.kt#jmzs0o");
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
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            shape2 = shape;
        } else if (($changed & 48) == 0) {
            shape2 = shape;
            $dirty |= $composer2.changed(shape2) ? 32 : 16;
        } else {
            shape2 = shape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                jM1769getSurface0d7_KjU = j;
                int i4 = $composer2.changed(jM1769getSurface0d7_KjU) ? 256 : 128;
                $dirty |= i4;
            } else {
                jM1769getSurface0d7_KjU = j;
            }
            $dirty |= i4;
        } else {
            jM1769getSurface0d7_KjU = j;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                jM1783contentColorForek8zF_U = j2;
                int i5 = $composer2.changed(jM1783contentColorForek8zF_U) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                jM1783contentColorForek8zF_U = j2;
            }
            $dirty |= i5;
        } else {
            jM1783contentColorForek8zF_U = j2;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            border2 = border;
        } else if (($changed & 24576) == 0) {
            border2 = border;
            $dirty |= $composer2.changed(border2) ? 16384 : 8192;
        } else {
            border2 = border;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changed(f) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 1048576 : 524288;
        }
        if (!$composer2.shouldExecute(($dirty & 599187) != 599186, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            shape3 = shape2;
            j3 = jM1783contentColorForek8zF_U;
            modifier3 = modifier2;
            j4 = jM1769getSurface0d7_KjU;
            border3 = border2;
            f2 = f;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "96@5039L6,97@5081L22");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                fM7905constructorimpl = f;
                shape4 = shape2;
                j5 = jM1769getSurface0d7_KjU;
                border4 = border2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    shape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    jM1769getSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer2, 6).m1769getSurface0d7_KjU();
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1769getSurface0d7_KjU, $composer2, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                }
                if (i6 != 0) {
                    border2 = null;
                }
                if (i7 == 0) {
                    fM7905constructorimpl = f;
                    shape4 = shape2;
                    j5 = jM1769getSurface0d7_KjU;
                    border4 = border2;
                } else {
                    fM7905constructorimpl = Dp.m7905constructorimpl(0);
                    shape4 = shape2;
                    j5 = jM1769getSurface0d7_KjU;
                    border4 = border2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(174096871, $dirty, -1, "androidx.compose.material.Surface (Surface.kt:101)");
            }
            ProvidableCompositionLocal<Dp> localAbsoluteElevation = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localAbsoluteElevation);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            float arg0$iv = ((Dp) objConsume).m7919unboximpl();
            float other$iv = fM7905constructorimpl;
            final float absoluteElevation = Dp.m7905constructorimpl(arg0$iv + other$iv);
            final Modifier modifier4 = modifier2;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(jM1783contentColorForek8zF_U)), ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m7903boximpl(absoluteElevation))}, ComposableLambdaKt.rememberComposableLambda(-2004281689, true, new Function2() { // from class: androidx.compose.material.SurfaceKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SurfaceKt.Surface_F_jzlyU$lambda$0(modifier4, shape4, j5, absoluteElevation, border4, fM7905constructorimpl, function2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = jM1783contentColorForek8zF_U;
            shape3 = shape4;
            j4 = j5;
            border3 = border4;
            f2 = fM7905constructorimpl;
            modifier3 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SurfaceKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SurfaceKt.Surface_F_jzlyU$lambda$1(modifier3, shape3, j4, j3, border3, f2, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:35:0x01e8  */
    static final Unit Surface_F_jzlyU$lambda$0(Modifier $modifier, Shape $shape, long j, float $absoluteElevation, BorderStroke $border, float f, Function2 $content, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Composer $composer$iv;
        ComposerKt.sourceInformation($composer, "C115@5768L7,113@5623L254,121@6046L115,125@6202L2,107@5431L862:Surface.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2004281689, $changed, -1, "androidx.compose.material.Surface.<anonymous> (Surface.kt:107)");
            }
            ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localElevationOverlay);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierM1958surface8ww4TTg = m1958surface8ww4TTg($modifier, $shape, m1959surfaceColorAtElevationcq6XJ1M(j, (ElevationOverlay) objConsume, $absoluteElevation, $composer, 0), $border, f);
            ComposerKt.sourceInformationMarkerStart($composer, -1969334406, "CC(remember):Surface.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material.SurfaceKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SurfaceKt.Surface_F_jzlyU$lambda$0$0$0((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierSemantics = SemanticsModifierKt.semantics(modifierM1958surface8ww4TTg, false, (Function1) it$iv);
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -1969329527, "CC(remember):Surface.kt#9igjgp");
            Object it$iv2 = $composer.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = (PointerInputEventHandler) new PointerInputEventHandler() { // from class: androidx.compose.material.SurfaceKt$Surface$1$2$1
                    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                    public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                        return Unit.INSTANCE;
                    }
                };
                $composer.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifier$iv = SuspendingPointerInputFilterKt.pointerInput(modifierSemantics, unit, (PointerInputEventHandler) it$iv2);
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                $composer$iv = $composer;
            } else {
                $composer$iv = $composer;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                Composer $composer$iv2 = $composer$iv;
                ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i2 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv2, -305383667, "C128@6274L9:Surface.kt#jmzs0o");
                $content.invoke($composer$iv2, 0);
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            Composer $composer$iv3 = $composer$iv;
            ComposerKt.sourceInformationMarkerStart($composer$iv3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i4 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer$iv3, -305383667, "C128@6274L9:Surface.kt#jmzs0o");
            $content.invoke($composer$iv3, 0);
            ComposerKt.sourceInformationMarkerEnd($composer$iv3);
            ComposerKt.sourceInformationMarkerEnd($composer$iv3);
            $composer$iv.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Surface_F_jzlyU$lambda$0$0$0(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContainer($this$semantics, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: Surface-LPr_se0, reason: not valid java name */
    public static final void m1955SurfaceLPr_se0(final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, long j, long j2, BorderStroke border, float f, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        long jM1769getSurface0d7_KjU;
        int i2;
        long jM1783contentColorForek8zF_U;
        final boolean enabled3;
        final long j3;
        final Modifier modifier3;
        final Shape shape3;
        final long j4;
        final BorderStroke border2;
        final float f2;
        final MutableInteractionSource interactionSource2;
        BorderStroke border3;
        float fM7905constructorimpl;
        final MutableInteractionSource interactionSource3;
        final BorderStroke border4;
        final float f3;
        final boolean enabled4;
        final Shape shape4;
        int $dirty;
        final long j5;
        Composer $composer2 = $composer.startRestartGroup(2141308794);
        ComposerKt.sourceInformation($composer2, "C(Surface)N(onClick,modifier,enabled,shape,color:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,border,elevation:c#ui.unit.Dp,interactionSource,content)208@11026L7,212@11190L967,209@11050L1107:Surface.kt#jmzs0o");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(function0) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer2.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty2 |= 3072;
            shape2 = shape;
        } else if (($changed & 3072) == 0) {
            shape2 = shape;
            $dirty2 |= $composer2.changed(shape2) ? 2048 : 1024;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                jM1769getSurface0d7_KjU = j;
                int i6 = $composer2.changed(jM1769getSurface0d7_KjU) ? 16384 : 8192;
                $dirty2 |= i6;
            } else {
                jM1769getSurface0d7_KjU = j;
            }
            $dirty2 |= i6;
        } else {
            jM1769getSurface0d7_KjU = j;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                i2 = i3;
                jM1783contentColorForek8zF_U = j2;
                int i7 = $composer2.changed(jM1783contentColorForek8zF_U) ? 131072 : 65536;
                $dirty2 |= i7;
            } else {
                i2 = i3;
                jM1783contentColorForek8zF_U = j2;
            }
            $dirty2 |= i7;
        } else {
            i2 = i3;
            jM1783contentColorForek8zF_U = j2;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer2.changed(border) ? 1048576 : 524288;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer2.changed(f) ? 8388608 : 4194304;
        }
        int i10 = i & 256;
        if (i10 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer2.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? 536870912 : 268435456;
        }
        int $dirty3 = $dirty2;
        if (!$composer2.shouldExecute(($dirty2 & 306783379) != 306783378, $dirty3 & 1)) {
            $composer2.skipToGroupEnd();
            long j6 = jM1783contentColorForek8zF_U;
            enabled3 = enabled2;
            j3 = j6;
            modifier3 = modifier2;
            shape3 = shape2;
            j4 = jM1769getSurface0d7_KjU;
            border2 = border;
            f2 = f;
            interactionSource2 = interactionSource;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "201@10751L6,202@10793L22");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                }
                border4 = border;
                f3 = f;
                interactionSource3 = interactionSource;
                enabled4 = enabled2;
                shape4 = shape2;
                $dirty = $dirty3;
                j5 = jM1769getSurface0d7_KjU;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if (i5 != 0) {
                    shape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 16) != 0) {
                    jM1769getSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer2, 6).m1769getSurface0d7_KjU();
                    $dirty3 &= -57345;
                }
                if ((i & 32) != 0) {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1769getSurface0d7_KjU, $composer2, ($dirty3 >> 12) & 14);
                    $dirty3 &= -458753;
                }
                if (i8 == 0) {
                    border3 = border;
                } else {
                    border3 = null;
                }
                if (i9 == 0) {
                    fM7905constructorimpl = f;
                } else {
                    fM7905constructorimpl = Dp.m7905constructorimpl(0);
                }
                if (i10 != 0) {
                    border4 = border3;
                    f3 = fM7905constructorimpl;
                    interactionSource3 = null;
                    enabled4 = enabled2;
                    shape4 = shape2;
                    $dirty = $dirty3;
                    j5 = jM1769getSurface0d7_KjU;
                } else {
                    interactionSource3 = interactionSource;
                    border4 = border3;
                    f3 = fM7905constructorimpl;
                    enabled4 = enabled2;
                    shape4 = shape2;
                    $dirty = $dirty3;
                    j5 = jM1769getSurface0d7_KjU;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2141308794, $dirty, -1, "androidx.compose.material.Surface (Surface.kt:207)");
            }
            ProvidableCompositionLocal<Dp> localAbsoluteElevation = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localAbsoluteElevation);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            float other$iv = f3;
            final float arg0$iv = Dp.m7905constructorimpl(((Dp) objConsume).m7919unboximpl() + other$iv);
            final Modifier modifier4 = modifier2;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(jM1783contentColorForek8zF_U)), ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m7903boximpl(arg0$iv))}, ComposableLambdaKt.rememberComposableLambda(-1766606150, true, new Function2() { // from class: androidx.compose.material.SurfaceKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SurfaceKt.Surface_LPr_se0$lambda$0(modifier4, shape4, j5, arg0$iv, border4, f3, interactionSource3, enabled4, function0, function2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = jM1783contentColorForek8zF_U;
            modifier3 = modifier4;
            shape3 = shape4;
            j4 = j5;
            border2 = border4;
            f2 = f3;
            interactionSource2 = interactionSource3;
            enabled3 = enabled4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SurfaceKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SurfaceKt.Surface_LPr_se0$lambda$1(function0, modifier3, enabled3, shape3, j4, j3, border2, f2, interactionSource2, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Surface_LPr_se0$lambda$0(Modifier $modifier, Shape $shape, long j, float $absoluteElevation, BorderStroke $border, float f, MutableInteractionSource $interactionSource, boolean $enabled, Function0 $onClick, Function2 $content, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C222@11592L7,220@11447L254,213@11200L951:Surface.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1766606150, $changed, -1, "androidx.compose.material.Surface.<anonymous> (Surface.kt:213)");
            }
            Modifier modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize($modifier);
            ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localElevationOverlay);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifier$iv = ClickableKt.m295clickableO2vRcR0(m1958surface8ww4TTg(modifierMinimumInteractiveComponentSize, $shape, m1959surfaceColorAtElevationcq6XJ1M(j, (ElevationOverlay) objConsume, $absoluteElevation, $composer, 0), $border, f), $interactionSource, RippleKt.m1907rippleH2RKhps$default(false, 0.0f, 0L, 7, null), (28 & 4) != 0 ? true : $enabled, (28 & 8) != 0 ? null : null, (28 & 16) != 0 ? null : null, $onClick);
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -2077913498, "C236@12132L9:Surface.kt#jmzs0o");
            $content.invoke($composer, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: Surface-Ny5ogXk, reason: not valid java name */
    public static final void m1956SurfaceNy5ogXk(final boolean selected, final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, long j, long j2, BorderStroke border, float f, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        Function0<Unit> function1;
        Modifier modifier2;
        boolean z2;
        Shape shape2;
        final long jM1769getSurface0d7_KjU;
        int $dirty;
        int $dirty1;
        final long j3;
        final float f2;
        final Modifier modifier3;
        final boolean enabled2;
        final Shape shape3;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        Modifier.Companion modifier4;
        boolean enabled3;
        long jM1783contentColorForek8zF_U;
        BorderStroke border3;
        char c;
        float fM7905constructorimpl;
        final MutableInteractionSource interactionSource3;
        final BorderStroke border4;
        final float f3;
        final boolean enabled4;
        final Shape shape4;
        int $dirty2;
        int i2;
        final long j4;
        int $dirty3;
        Composer $composer2 = $composer.startRestartGroup(-258978402);
        ComposerKt.sourceInformation($composer2, "C(Surface)N(selected,onClick,modifier,enabled,shape,color:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,border,elevation:c#ui.unit.Dp,interactionSource,content)318@16972L7,322@17136L1013,319@16996L1153:Surface.kt#jmzs0o");
        int $dirty4 = $changed;
        if (($changed & 6) == 0) {
            z = selected;
            $dirty4 |= $composer2.changed(z) ? 4 : 2;
        } else {
            z = selected;
        }
        if (($changed & 48) == 0) {
            function1 = function0;
            $dirty4 |= $composer2.changedInstance(function1) ? 32 : 16;
        } else {
            function1 = function0;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty4 |= 3072;
            z2 = enabled;
        } else if (($changed & 3072) == 0) {
            z2 = enabled;
            $dirty4 |= $composer2.changed(z2) ? 2048 : 1024;
        } else {
            z2 = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty4 |= 24576;
            shape2 = shape;
        } else if (($changed & 24576) == 0) {
            shape2 = shape;
            $dirty4 |= $composer2.changed(shape2) ? 16384 : 8192;
        } else {
            shape2 = shape;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                jM1769getSurface0d7_KjU = j;
                int i6 = $composer2.changed(jM1769getSurface0d7_KjU) ? 131072 : 65536;
                $dirty4 |= i6;
            } else {
                jM1769getSurface0d7_KjU = j;
            }
            $dirty4 |= i6;
        } else {
            jM1769getSurface0d7_KjU = j;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
                int i7 = $composer2.changed(j2) ? 1048576 : 524288;
                $dirty = $dirty3 | i7;
            } else {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
            }
            $dirty = $dirty3 | i7;
        } else {
            $dirty = $dirty4;
            $dirty1 = $changed1;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changed(border) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changed(f) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i10 = i & 512;
        if (i10 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        int $dirty5 = $dirty1;
        if (!$composer2.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty5 & 3) == 2) ? false : true, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            j3 = j2;
            f2 = f;
            modifier3 = modifier2;
            enabled2 = z2;
            shape3 = shape2;
            border2 = border;
            interactionSource2 = interactionSource;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "311@16697L6,312@16739L22");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                }
                border4 = border;
                f3 = f;
                interactionSource3 = interactionSource;
                modifier4 = modifier2;
                enabled4 = z2;
                shape4 = shape2;
                $dirty2 = $dirty;
                i2 = -258978402;
                c = 1;
                jM1783contentColorForek8zF_U = j2;
                j4 = jM1769getSurface0d7_KjU;
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 == 0) {
                    enabled3 = z2;
                } else {
                    enabled3 = true;
                }
                if (i5 != 0) {
                    shape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 32) != 0) {
                    jM1769getSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer2, 6).m1769getSurface0d7_KjU();
                    $dirty &= -458753;
                }
                if ((i & 64) == 0) {
                    jM1783contentColorForek8zF_U = j2;
                } else {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1769getSurface0d7_KjU, $composer2, ($dirty >> 15) & 14);
                    $dirty &= -3670017;
                }
                if (i8 == 0) {
                    border3 = border;
                } else {
                    border3 = null;
                }
                if (i9 == 0) {
                    c = 1;
                    fM7905constructorimpl = f;
                } else {
                    c = 1;
                    fM7905constructorimpl = Dp.m7905constructorimpl(0);
                }
                if (i10 != 0) {
                    border4 = border3;
                    f3 = fM7905constructorimpl;
                    enabled4 = enabled3;
                    interactionSource3 = null;
                    shape4 = shape2;
                    $dirty2 = $dirty;
                    i2 = -258978402;
                    j4 = jM1769getSurface0d7_KjU;
                } else {
                    interactionSource3 = interactionSource;
                    border4 = border3;
                    f3 = fM7905constructorimpl;
                    enabled4 = enabled3;
                    shape4 = shape2;
                    $dirty2 = $dirty;
                    i2 = -258978402;
                    j4 = jM1769getSurface0d7_KjU;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty2, $dirty5, "androidx.compose.material.Surface (Surface.kt:317)");
            }
            ProvidableCompositionLocal<Dp> localAbsoluteElevation = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localAbsoluteElevation);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            float arg0$iv = ((Dp) objConsume).m7919unboximpl();
            float other$iv = f3;
            final float absoluteElevation = Dp.m7905constructorimpl(arg0$iv + other$iv);
            ProvidedValue[] providedValueArr = new ProvidedValue[2];
            providedValueArr[0] = ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(jM1783contentColorForek8zF_U));
            providedValueArr[c] = ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m7903boximpl(absoluteElevation));
            final Modifier modifier5 = modifier4;
            final boolean z3 = z;
            final Function0<Unit> function3 = function1;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.rememberComposableLambda(1830486238, c, new Function2() { // from class: androidx.compose.material.SurfaceKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SurfaceKt.Surface_Ny5ogXk$lambda$0(modifier5, shape4, j4, absoluteElevation, border4, f3, z3, interactionSource3, enabled4, function3, function2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = jM1783contentColorForek8zF_U;
            shape3 = shape4;
            jM1769getSurface0d7_KjU = j4;
            border2 = border4;
            f2 = f3;
            interactionSource2 = interactionSource3;
            enabled2 = enabled4;
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SurfaceKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SurfaceKt.Surface_Ny5ogXk$lambda$1(selected, function0, modifier3, enabled2, shape3, jM1769getSurface0d7_KjU, j3, border2, f2, interactionSource2, function2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Surface_Ny5ogXk$lambda$0(Modifier $modifier, Shape $shape, long j, float $absoluteElevation, BorderStroke $border, float f, boolean $selected, MutableInteractionSource $interactionSource, boolean $enabled, Function0 $onClick, Function2 $content, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C332@17538L7,330@17393L254,323@17146L997:Surface.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1830486238, $changed, -1, "androidx.compose.material.Surface.<anonymous> (Surface.kt:323)");
            }
            Modifier modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize($modifier);
            ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localElevationOverlay);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifier$iv = SelectableKt.m1090selectableO2vRcR0$default(m1958surface8ww4TTg(modifierMinimumInteractiveComponentSize, $shape, m1959surfaceColorAtElevationcq6XJ1M(j, (ElevationOverlay) objConsume, $absoluteElevation, $composer, 0), $border, f), $selected, $interactionSource, RippleKt.m1907rippleH2RKhps$default(false, 0.0f, 0L, 7, null), $enabled, null, $onClick, 16, null);
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1999142974, "C347@18124L9:Surface.kt#jmzs0o");
            $content.invoke($composer, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: Surface-Ny5ogXk, reason: not valid java name */
    public static final void m1957SurfaceNy5ogXk(final boolean checked, final Function1<? super Boolean, Unit> function1, Modifier modifier, boolean enabled, Shape shape, long j, long j2, BorderStroke border, float f, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        Function1<? super Boolean, Unit> function3;
        Modifier modifier2;
        boolean z2;
        Shape shape2;
        final long jM1769getSurface0d7_KjU;
        int $dirty;
        int $dirty1;
        final long j3;
        final float f2;
        final Modifier modifier3;
        final boolean enabled2;
        final Shape shape3;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        Modifier.Companion modifier4;
        boolean enabled3;
        long jM1783contentColorForek8zF_U;
        BorderStroke border3;
        char c;
        float fM7905constructorimpl;
        final MutableInteractionSource interactionSource3;
        final BorderStroke border4;
        final float f3;
        final boolean enabled4;
        final Shape shape4;
        int $dirty2;
        int i2;
        final long j4;
        int $dirty3;
        Composer $composer2 = $composer.startRestartGroup(-391302147);
        ComposerKt.sourceInformation($composer2, "C(Surface)N(checked,onCheckedChange,modifier,enabled,shape,color:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,border,elevation:c#ui.unit.Dp,interactionSource,content)429@23038L7,433@23202L1023,430@23062L1163:Surface.kt#jmzs0o");
        int $dirty4 = $changed;
        if (($changed & 6) == 0) {
            z = checked;
            $dirty4 |= $composer2.changed(z) ? 4 : 2;
        } else {
            z = checked;
        }
        if (($changed & 48) == 0) {
            function3 = function1;
            $dirty4 |= $composer2.changedInstance(function3) ? 32 : 16;
        } else {
            function3 = function1;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty4 |= 3072;
            z2 = enabled;
        } else if (($changed & 3072) == 0) {
            z2 = enabled;
            $dirty4 |= $composer2.changed(z2) ? 2048 : 1024;
        } else {
            z2 = enabled;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty4 |= 24576;
            shape2 = shape;
        } else if (($changed & 24576) == 0) {
            shape2 = shape;
            $dirty4 |= $composer2.changed(shape2) ? 16384 : 8192;
        } else {
            shape2 = shape;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                jM1769getSurface0d7_KjU = j;
                int i6 = $composer2.changed(jM1769getSurface0d7_KjU) ? 131072 : 65536;
                $dirty4 |= i6;
            } else {
                jM1769getSurface0d7_KjU = j;
            }
            $dirty4 |= i6;
        } else {
            jM1769getSurface0d7_KjU = j;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
                int i7 = $composer2.changed(j2) ? 1048576 : 524288;
                $dirty = $dirty3 | i7;
            } else {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
            }
            $dirty = $dirty3 | i7;
        } else {
            $dirty = $dirty4;
            $dirty1 = $changed1;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changed(border) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changed(f) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i10 = i & 512;
        if (i10 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        int $dirty5 = $dirty1;
        if (!$composer2.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty5 & 3) == 2) ? false : true, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            j3 = j2;
            f2 = f;
            modifier3 = modifier2;
            enabled2 = z2;
            shape3 = shape2;
            border2 = border;
            interactionSource2 = interactionSource;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "422@22763L6,423@22805L22");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                }
                border4 = border;
                f3 = f;
                interactionSource3 = interactionSource;
                modifier4 = modifier2;
                enabled4 = z2;
                shape4 = shape2;
                $dirty2 = $dirty;
                i2 = -391302147;
                c = 1;
                jM1783contentColorForek8zF_U = j2;
                j4 = jM1769getSurface0d7_KjU;
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 == 0) {
                    enabled3 = z2;
                } else {
                    enabled3 = true;
                }
                if (i5 != 0) {
                    shape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 32) != 0) {
                    jM1769getSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer2, 6).m1769getSurface0d7_KjU();
                    $dirty &= -458753;
                }
                if ((i & 64) == 0) {
                    jM1783contentColorForek8zF_U = j2;
                } else {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1769getSurface0d7_KjU, $composer2, ($dirty >> 15) & 14);
                    $dirty &= -3670017;
                }
                if (i8 == 0) {
                    border3 = border;
                } else {
                    border3 = null;
                }
                if (i9 == 0) {
                    c = 1;
                    fM7905constructorimpl = f;
                } else {
                    c = 1;
                    fM7905constructorimpl = Dp.m7905constructorimpl(0);
                }
                if (i10 != 0) {
                    border4 = border3;
                    f3 = fM7905constructorimpl;
                    enabled4 = enabled3;
                    interactionSource3 = null;
                    shape4 = shape2;
                    $dirty2 = $dirty;
                    i2 = -391302147;
                    j4 = jM1769getSurface0d7_KjU;
                } else {
                    interactionSource3 = interactionSource;
                    border4 = border3;
                    f3 = fM7905constructorimpl;
                    enabled4 = enabled3;
                    shape4 = shape2;
                    $dirty2 = $dirty;
                    i2 = -391302147;
                    j4 = jM1769getSurface0d7_KjU;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty2, $dirty5, "androidx.compose.material.Surface (Surface.kt:428)");
            }
            ProvidableCompositionLocal<Dp> localAbsoluteElevation = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localAbsoluteElevation);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            float arg0$iv = ((Dp) objConsume).m7919unboximpl();
            float other$iv = f3;
            final float absoluteElevation = Dp.m7905constructorimpl(arg0$iv + other$iv);
            ProvidedValue[] providedValueArr = new ProvidedValue[2];
            providedValueArr[0] = ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(jM1783contentColorForek8zF_U));
            providedValueArr[c] = ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m7903boximpl(absoluteElevation));
            final Modifier modifier5 = modifier4;
            final boolean z3 = z;
            final Function1<? super Boolean, Unit> function4 = function3;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.rememberComposableLambda(1698162493, c, new Function2() { // from class: androidx.compose.material.SurfaceKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SurfaceKt.Surface_Ny5ogXk$lambda$2(modifier5, shape4, j4, absoluteElevation, border4, f3, z3, interactionSource3, enabled4, function4, function2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = jM1783contentColorForek8zF_U;
            shape3 = shape4;
            jM1769getSurface0d7_KjU = j4;
            border2 = border4;
            f2 = f3;
            interactionSource2 = interactionSource3;
            enabled2 = enabled4;
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SurfaceKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SurfaceKt.Surface_Ny5ogXk$lambda$3(checked, function1, modifier3, enabled2, shape3, jM1769getSurface0d7_KjU, j3, border2, f2, interactionSource2, function2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Surface_Ny5ogXk$lambda$2(Modifier $modifier, Shape $shape, long j, float $absoluteElevation, BorderStroke $border, float f, boolean $checked, MutableInteractionSource $interactionSource, boolean $enabled, Function1 $onCheckedChange, Function2 $content, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C443@23604L7,441@23459L254,434@23212L1007:Surface.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1698162493, $changed, -1, "androidx.compose.material.Surface.<anonymous> (Surface.kt:434)");
            }
            Modifier modifierMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize($modifier);
            ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localElevationOverlay);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifier$iv = ToggleableKt.m1097toggleableO2vRcR0$default(m1958surface8ww4TTg(modifierMinimumInteractiveComponentSize, $shape, m1959surfaceColorAtElevationcq6XJ1M(j, (ElevationOverlay) objConsume, $absoluteElevation, $composer, 0), $border, f), $checked, $interactionSource, RippleKt.m1907rippleH2RKhps$default(false, 0.0f, 0L, 7, null), $enabled, null, $onCheckedChange, 16, null);
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1275852541, "C458@24200L9:Surface.kt#jmzs0o");
            $content.invoke($composer, 0);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: surface-8ww4TTg, reason: not valid java name */
    private static final Modifier m1958surface8ww4TTg(Modifier $this$surface_u2d8ww4TTg, Shape shape, long j, BorderStroke border, float f) {
        Modifier $this$surface_u2d8ww4TTg2 = ShadowKt.m4695shadows4CzXII$default($this$surface_u2d8ww4TTg, f, shape, false, 0L, 0L, 24, null);
        Modifier.Companion companionBorder = Modifier.INSTANCE;
        if (border != null) {
            companionBorder = BorderKt.border(companionBorder, border, shape);
        }
        return ClipKt.clip(BackgroundKt.m264backgroundbw27NRU($this$surface_u2d8ww4TTg2.then(companionBorder), j, shape), shape);
    }

    /* JADX INFO: renamed from: surfaceColorAtElevation-cq6XJ1M, reason: not valid java name */
    private static final long m1959surfaceColorAtElevationcq6XJ1M(long j, ElevationOverlay elevationOverlay, float f, Composer $composer, int $changed) {
        Composer $composer2;
        long jMo1796apply7g2Lkgo;
        ComposerKt.sourceInformationMarkerStart($composer, 1561611256, "C(surfaceColorAtElevation)N(color:c#ui.graphics.Color,elevationOverlay,absoluteElevation:c#ui.unit.Dp)480@24749L6:Surface.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1561611256, $changed, -1, "androidx.compose.material.surfaceColorAtElevation (Surface.kt:479)");
        }
        if (!Color.m5098equalsimpl0(j, MaterialTheme.INSTANCE.getColors($composer, 6).m1769getSurface0d7_KjU()) || elevationOverlay == null) {
            $composer2 = $composer;
            $composer2.startReplaceGroup(-1124546347);
            $composer2.endReplaceGroup();
            jMo1796apply7g2Lkgo = j;
        } else {
            $composer.startReplaceGroup(-1124614454);
            ComposerKt.sourceInformation($composer, "481@24820L31");
            $composer2 = $composer;
            jMo1796apply7g2Lkgo = elevationOverlay.mo1796apply7g2Lkgo(j, f, $composer2, ($changed & 14) | (($changed >> 3) & 112) | (($changed << 3) & 896));
            $composer2.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer2);
        return jMo1796apply7g2Lkgo;
    }
}
