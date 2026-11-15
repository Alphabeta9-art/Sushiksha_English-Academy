package androidx.compose.material;

import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.CornerSizeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FloatingActionButton.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001an\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0085\u0001\u0010\u0013\u001a\u00020\u00012\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00102\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00102\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0016\u0010\u0017\"\u0010\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001a\"\u0010\u0010\u001b\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001a\"\u0010\u0010\u001c\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001a\"\u0010\u0010\u001d\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001a¨\u0006\u001e"}, d2 = {"FloatingActionButton", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "shape", "Landroidx/compose/ui/graphics/Shape;", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "contentColor", "elevation", "Landroidx/compose/material/FloatingActionButtonElevation;", UriUtil.LOCAL_CONTENT_SCHEME, "Landroidx/compose/runtime/Composable;", "FloatingActionButton-bogVsAg", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/material/FloatingActionButtonElevation;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ExtendedFloatingActionButton", "text", "icon", "ExtendedFloatingActionButton-wqdebIU", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/material/FloatingActionButtonElevation;Landroidx/compose/runtime/Composer;II)V", "FabSize", "Landroidx/compose/ui/unit/Dp;", "F", "ExtendedFabSize", "ExtendedFabIconPadding", "ExtendedFabTextPadding", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class FloatingActionButtonKt {
    private static final float FabSize = Dp.m7905constructorimpl(56);
    private static final float ExtendedFabSize = Dp.m7905constructorimpl(48);
    private static final float ExtendedFabIconPadding = Dp.m7905constructorimpl(12);
    private static final float ExtendedFabTextPadding = Dp.m7905constructorimpl(20);

    static final Unit ExtendedFloatingActionButton_wqdebIU$lambda$1(Function2 function2, Function0 function0, Modifier modifier, Function2 function3, MutableInteractionSource mutableInteractionSource, Shape shape, long j, long j2, FloatingActionButtonElevation floatingActionButtonElevation, int i, int i2, Composer composer, int i3) {
        m1845ExtendedFloatingActionButtonwqdebIU(function2, function0, modifier, function3, mutableInteractionSource, shape, j, j2, floatingActionButtonElevation, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit FloatingActionButton_bogVsAg$lambda$3(Function0 function0, Modifier modifier, MutableInteractionSource mutableInteractionSource, Shape shape, long j, long j2, FloatingActionButtonElevation floatingActionButtonElevation, Function2 function2, int i, int i2, Composer composer, int i3) {
        m1846FloatingActionButtonbogVsAg(function0, modifier, mutableInteractionSource, shape, j, j2, floatingActionButtonElevation, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: FloatingActionButton-bogVsAg, reason: not valid java name */
    public static final void m1846FloatingActionButtonbogVsAg(final Function0<Unit> function0, Modifier modifier, MutableInteractionSource interactionSource, Shape shape, long j, long j2, FloatingActionButtonElevation elevation, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function1;
        MutableInteractionSource interactionSource2;
        Shape shape2;
        long j3;
        final long jM1783contentColorForek8zF_U;
        FloatingActionButtonElevation floatingActionButtonElevation;
        Composer $composer2;
        final Modifier modifier2;
        final MutableInteractionSource interactionSource3;
        final Shape shape3;
        final long j4;
        final long j5;
        final FloatingActionButtonElevation elevation2;
        Modifier.Companion modifier3;
        MutableInteractionSource interactionSource4;
        Shape shape4;
        int $dirty;
        long jM1767getSecondary0d7_KjU;
        int $dirty2;
        Modifier modifier4;
        int $dirty3;
        FloatingActionButtonElevation elevation3;
        Shape shape5;
        long j6;
        int i2;
        MutableInteractionSource interactionSource5;
        Composer $composer3 = $composer.startRestartGroup(-482679837);
        ComposerKt.sourceInformation($composer3, "C(FloatingActionButton)N(onClick,modifier,interactionSource,shape,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation,content)95@4354L22,99@4501L28,101@4590L420,93@4280L730:FloatingActionButton.kt#jmzs0o");
        int $dirty4 = $changed;
        if (($changed & 6) == 0) {
            function1 = function0;
            $dirty4 |= $composer3.changedInstance(function1) ? 4 : 2;
        } else {
            function1 = function0;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty4 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty4 |= $composer3.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty4 |= 384;
            interactionSource2 = interactionSource;
        } else if (($changed & 384) == 0) {
            interactionSource2 = interactionSource;
            $dirty4 |= $composer3.changed(interactionSource2) ? 256 : 128;
        } else {
            interactionSource2 = interactionSource;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i5 = $composer3.changed(shape2) ? 2048 : 1024;
                $dirty4 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty4 |= i5;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                j3 = j;
                int i6 = $composer3.changed(j3) ? 16384 : 8192;
                $dirty4 |= i6;
            } else {
                j3 = j;
            }
            $dirty4 |= i6;
        } else {
            j3 = j;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                jM1783contentColorForek8zF_U = j2;
                int i7 = $composer3.changed(jM1783contentColorForek8zF_U) ? 131072 : 65536;
                $dirty4 |= i7;
            } else {
                jM1783contentColorForek8zF_U = j2;
            }
            $dirty4 |= i7;
        } else {
            jM1783contentColorForek8zF_U = j2;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                floatingActionButtonElevation = elevation;
                int i8 = $composer3.changed(floatingActionButtonElevation) ? 1048576 : 524288;
                $dirty4 |= i8;
            } else {
                floatingActionButtonElevation = elevation;
            }
            $dirty4 |= i8;
        } else {
            floatingActionButtonElevation = elevation;
        }
        if (($changed & 12582912) == 0) {
            $dirty4 |= $composer3.changedInstance(function2) ? 8388608 : 4194304;
        }
        if ($composer3.shouldExecute(($dirty4 & 4793491) != 4793490, $dirty4 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "85@3859L6,86@3947L6,87@3991L32,88@4101L11");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty4 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty4 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty4 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty4 &= -3670017;
                }
                j6 = j3;
                elevation3 = floatingActionButtonElevation;
                i2 = -482679837;
                shape5 = shape2;
                $dirty3 = $dirty4;
                modifier4 = modifier;
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if (i4 == 0) {
                    interactionSource4 = interactionSource2;
                } else {
                    interactionSource4 = null;
                }
                if ((i & 8) != 0) {
                    $dirty4 &= -7169;
                    shape4 = MaterialTheme.INSTANCE.getShapes($composer3, 6).getSmall().copy(CornerSizeKt.CornerSize(50));
                } else {
                    shape4 = shape2;
                }
                if ((i & 16) == 0) {
                    $dirty = $dirty4;
                    jM1767getSecondary0d7_KjU = j3;
                } else {
                    $dirty = $dirty4 & (-57345);
                    jM1767getSecondary0d7_KjU = MaterialTheme.INSTANCE.getColors($composer3, 6).m1767getSecondary0d7_KjU();
                }
                if ((i & 32) == 0) {
                    $dirty2 = $dirty;
                } else {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1767getSecondary0d7_KjU, $composer3, ($dirty >> 12) & 14);
                    $dirty2 = $dirty & (-458753);
                }
                if ((i & 64) == 0) {
                    long j7 = jM1767getSecondary0d7_KjU;
                    modifier4 = modifier3;
                    $dirty3 = $dirty2;
                    elevation3 = floatingActionButtonElevation;
                    interactionSource2 = interactionSource4;
                    shape5 = shape4;
                    j6 = j7;
                    i2 = -482679837;
                } else {
                    $dirty3 = $dirty2 & (-3670017);
                    interactionSource2 = interactionSource4;
                    shape5 = shape4;
                    j6 = jM1767getSecondary0d7_KjU;
                    i2 = -482679837;
                    elevation3 = FloatingActionButtonDefaults.INSTANCE.m1840elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
                    modifier4 = modifier3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty3, -1, "androidx.compose.material.FloatingActionButton (FloatingActionButton.kt:90)");
            }
            if (interactionSource2 == null) {
                $composer3.startReplaceGroup(36032728);
                ComposerKt.sourceInformation($composer3, "92@4236L39");
                ComposerKt.sourceInformationMarkerStart($composer3, 1162346, "CC(remember):FloatingActionButton.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                interactionSource5 = (MutableInteractionSource) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(1161695);
                $composer3.endReplaceGroup();
                interactionSource5 = interactionSource2;
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1166105, "CC(remember):FloatingActionButton.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            MutableInteractionSource interactionSource6 = interactionSource5;
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material.FloatingActionButtonKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return FloatingActionButtonKt.FloatingActionButton_bogVsAg$lambda$1$0((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            long j8 = jM1783contentColorForek8zF_U;
            $composer2 = $composer3;
            SurfaceKt.m1955SurfaceLPr_se0(function1, SemanticsModifierKt.semantics$default(modifier4, false, (Function1) it$iv2, 1, null), false, shape5, j6, j8, null, elevation3.elevation(interactionSource6, $composer3, ($dirty3 >> 15) & 112).getValue().m7919unboximpl(), interactionSource6, ComposableLambdaKt.rememberComposableLambda(-1823447062, true, new Function2() { // from class: androidx.compose.material.FloatingActionButtonKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return FloatingActionButtonKt.FloatingActionButton_bogVsAg$lambda$2(jM1783contentColorForek8zF_U, function2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer2, ($dirty3 & 14) | C.ENCODING_PCM_32BIT | ($dirty3 & 7168) | (57344 & $dirty3) | (458752 & $dirty3), 68);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            elevation2 = elevation3;
            interactionSource3 = interactionSource2;
            shape3 = shape5;
            j4 = j6;
            j5 = j8;
            modifier2 = modifier4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            interactionSource3 = interactionSource2;
            shape3 = shape2;
            j4 = j3;
            j5 = jM1783contentColorForek8zF_U;
            elevation2 = floatingActionButtonElevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.FloatingActionButtonKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return FloatingActionButtonKt.FloatingActionButton_bogVsAg$lambda$3(function0, modifier2, interactionSource3, shape3, j4, j5, elevation2, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FloatingActionButton_bogVsAg$lambda$1$0(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.m7118setRolekuIjeqM($this$semantics, Role.INSTANCE.m7099getButtono7Vup1c());
        return Unit.INSTANCE;
    }

    static final Unit FloatingActionButton_bogVsAg$lambda$2(long j, final Function2 $content, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C102@4672L332,102@4600L404:FloatingActionButton.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1823447062, $changed, -1, "androidx.compose.material.FloatingActionButton.<anonymous> (FloatingActionButton.kt:102)");
            }
            CompositionLocalKt.CompositionLocalProvider(ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m5099getAlphaimpl(j))), ComposableLambdaKt.rememberComposableLambda(-1072292694, true, new Function2() { // from class: androidx.compose.material.FloatingActionButtonKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return FloatingActionButtonKt.FloatingActionButton_bogVsAg$lambda$2$0($content, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FloatingActionButton_bogVsAg$lambda$2$0(final Function2 $content, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C103@4717L10,103@4736L258,103@4686L308:FloatingActionButton.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1072292694, $changed, -1, "androidx.compose.material.FloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:103)");
            }
            TextKt.ProvideTextStyle(MaterialTheme.INSTANCE.getTypography($composer, 6).getButton(), ComposableLambdaKt.rememberComposableLambda(-1686273317, true, new Function2() { // from class: androidx.compose.material.FloatingActionButtonKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return FloatingActionButtonKt.FloatingActionButton_bogVsAg$lambda$2$0$0($content, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FloatingActionButton_bogVsAg$lambda$2$0$0(Function2 $content, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C104@4754L226:FloatingActionButton.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1686273317, $changed, -1, "androidx.compose.material.FloatingActionButton.<anonymous>.<anonymous>.<anonymous> (FloatingActionButton.kt:104)");
            }
            Modifier modifier$iv = SizeKt.m849defaultMinSizeVpY3zN4(Modifier.INSTANCE, FabSize, FabSize);
            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (54 << 3) & 112;
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
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -344600103, "C108@4953L9:FloatingActionButton.kt#jmzs0o");
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

    /* JADX INFO: renamed from: ExtendedFloatingActionButton-wqdebIU, reason: not valid java name */
    public static final void m1845ExtendedFloatingActionButtonwqdebIU(final Function2<? super Composer, ? super Integer, Unit> function2, final Function0<Unit> function0, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, MutableInteractionSource interactionSource, Shape shape, long j, long j2, FloatingActionButtonElevation elevation, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function1;
        Modifier modifier2;
        final Function2<? super Composer, ? super Integer, Unit> function4;
        MutableInteractionSource interactionSource2;
        Shape shape2;
        final FloatingActionButtonElevation elevation2;
        Composer $composer2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function5;
        final MutableInteractionSource interactionSource3;
        final Shape shape3;
        final long j3;
        final long j4;
        int $dirty;
        long jM1767getSecondary0d7_KjU;
        long jM1783contentColorForek8zF_U;
        Composer $composer3;
        MutableInteractionSource interactionSource4;
        FloatingActionButtonElevation elevation3;
        long j5;
        long j6;
        Shape shape4;
        Composer $composer4 = $composer.startRestartGroup(-1506973027);
        ComposerKt.sourceInformation($composer4, "C(ExtendedFloatingActionButton)N(text,onClick,modifier,icon,interactionSource,shape,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation)168@7792L442,160@7463L771:FloatingActionButton.kt#jmzs0o");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer4.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            function1 = function0;
            $dirty2 |= $composer4.changedInstance(function1) ? 32 : 16;
        } else {
            function1 = function0;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer4.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
            function4 = function3;
        } else if (($changed & 3072) == 0) {
            function4 = function3;
            $dirty2 |= $composer4.changedInstance(function4) ? 2048 : 1024;
        } else {
            function4 = function3;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty2 |= 24576;
            interactionSource2 = interactionSource;
        } else if (($changed & 24576) == 0) {
            interactionSource2 = interactionSource;
            $dirty2 |= $composer4.changed(interactionSource2) ? 16384 : 8192;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i5 = $composer4.changed(shape2) ? 131072 : 65536;
                $dirty2 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i5;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            $dirty2 |= ((i & 64) == 0 && $composer4.changed(j)) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= ((i & 128) == 0 && $composer4.changed(j2)) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            if ((i & 256) == 0) {
                elevation2 = elevation;
                int i6 = $composer4.changed(elevation2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                $dirty2 |= i6;
            } else {
                elevation2 = elevation;
            }
            $dirty2 |= i6;
        } else {
            elevation2 = elevation;
        }
        if ($composer4.shouldExecute(($dirty2 & 38347923) != 38347922, $dirty2 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "155@7200L6,156@7288L6,157@7332L32,158@7442L11");
            if (($changed & 1) != 0 && !$composer4.getDefaultsInvalid()) {
                $composer4.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty2 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty2 &= -234881025;
                }
                j6 = j;
                j5 = j2;
                $composer3 = $composer4;
                interactionSource4 = interactionSource2;
                elevation3 = elevation2;
                shape4 = shape2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    function4 = null;
                }
                if (i4 != 0) {
                    interactionSource2 = null;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    shape2 = MaterialTheme.INSTANCE.getShapes($composer4, 6).getSmall().copy(CornerSizeKt.CornerSize(50));
                }
                if ((i & 64) == 0) {
                    $dirty = $dirty2;
                    jM1767getSecondary0d7_KjU = j;
                } else {
                    $dirty = $dirty2 & (-3670017);
                    jM1767getSecondary0d7_KjU = MaterialTheme.INSTANCE.getColors($composer4, 6).m1767getSecondary0d7_KjU();
                }
                if ((i & 128) == 0) {
                    jM1783contentColorForek8zF_U = j2;
                } else {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1767getSecondary0d7_KjU, $composer4, ($dirty >> 18) & 14);
                    $dirty &= -29360129;
                }
                if ((i & 256) == 0) {
                    $composer3 = $composer4;
                    interactionSource4 = interactionSource2;
                    elevation3 = elevation2;
                    j5 = jM1783contentColorForek8zF_U;
                    j6 = jM1767getSecondary0d7_KjU;
                    $dirty2 = $dirty;
                    shape4 = shape2;
                } else {
                    $composer3 = $composer4;
                    elevation3 = FloatingActionButtonDefaults.INSTANCE.m1840elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer4, 24576, 15);
                    interactionSource4 = interactionSource2;
                    j5 = jM1783contentColorForek8zF_U;
                    j6 = jM1767getSecondary0d7_KjU;
                    $dirty2 = $dirty & (-234881025);
                    shape4 = shape2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1506973027, $dirty2, -1, "androidx.compose.material.ExtendedFloatingActionButton (FloatingActionButton.kt:159)");
            }
            $composer2 = $composer3;
            m1846FloatingActionButtonbogVsAg(function1, SizeKt.m869sizeInqDBjuR0$default(modifier2, ExtendedFabSize, ExtendedFabSize, 0.0f, 0.0f, 12, null), interactionSource4, shape4, j6, j5, elevation3, ComposableLambdaKt.rememberComposableLambda(-555697957, true, new Function2() { // from class: androidx.compose.material.FloatingActionButtonKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return FloatingActionButtonKt.ExtendedFloatingActionButton_wqdebIU$lambda$0(function4, function2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer2, (($dirty2 >> 3) & 14) | 12582912 | (($dirty2 >> 6) & 896) | (($dirty2 >> 6) & 7168) | (($dirty2 >> 6) & 57344) | (($dirty2 >> 6) & 458752) | (($dirty2 >> 6) & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            function5 = function4;
            interactionSource3 = interactionSource4;
            shape3 = shape4;
            j3 = j6;
            j4 = j5;
            elevation2 = elevation3;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            function5 = function4;
            interactionSource3 = interactionSource2;
            shape3 = shape2;
            j3 = j;
            j4 = j2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.FloatingActionButtonKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return FloatingActionButtonKt.ExtendedFloatingActionButton_wqdebIU$lambda$1(function2, function0, modifier3, function5, interactionSource3, shape3, j3, j4, elevation2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit ExtendedFloatingActionButton_wqdebIU$lambda$0(Function2 $icon, Function2 $text, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C170@7898L330:FloatingActionButton.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-555697957, $changed, -1, "androidx.compose.material.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:169)");
            }
            float startPadding = $icon == null ? ExtendedFabTextPadding : ExtendedFabIconPadding;
            Modifier modifier$iv = PaddingKt.m823paddingqDBjuR0$default(Modifier.INSTANCE, startPadding, 0.0f, ExtendedFabTextPadding, 0.0f, 10, null);
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
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
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i2 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 185594530, "C178@8212L6:FloatingActionButton.kt#jmzs0o");
            if ($icon != null) {
                $composer.startReplaceGroup(185611238);
                ComposerKt.sourceInformation($composer, "175@8116L6,176@8139L46");
                $icon.invoke($composer, 0);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, ExtendedFabIconPadding), $composer, 6);
            } else {
                $composer.startReplaceGroup(177573899);
            }
            $composer.endReplaceGroup();
            $text.invoke($composer, 0);
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
}
