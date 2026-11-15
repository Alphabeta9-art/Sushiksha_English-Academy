package androidx.compose.material;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
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
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Button.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\u001a\u008f\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u008f\u0001\u0010\u001a\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u008f\u0001\u0010\u001b\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019¨\u0006\u001c²\u0006\n\u0010\u001d\u001a\u00020\u001eX\u008a\u0084\u0002"}, d2 = {"Button", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "elevation", "Landroidx/compose/material/ButtonElevation;", "shape", "Landroidx/compose/ui/graphics/Shape;", OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "colors", "Landroidx/compose/material/ButtonColors;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/ButtonElevation;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/material/ButtonColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "OutlinedButton", "TextButton", "material", "contentColor", "Landroidx/compose/ui/graphics/Color;"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ButtonKt {
    static final Unit Button$lambda$4(Function0 function0, Modifier modifier, boolean z, MutableInteractionSource mutableInteractionSource, ButtonElevation buttonElevation, Shape shape, BorderStroke borderStroke, ButtonColors buttonColors, PaddingValues paddingValues, Function3 function3, int i, int i2, Composer composer, int i3) {
        Button(function0, modifier, z, mutableInteractionSource, buttonElevation, shape, borderStroke, buttonColors, paddingValues, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void Button(final Function0<Unit> function0, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, ButtonElevation elevation, Shape shape, BorderStroke border, ButtonColors colors, PaddingValues contentPadding, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        MutableInteractionSource interactionSource2;
        Shape shape2;
        BorderStroke borderStroke;
        Composer $composer2;
        final BorderStroke border2;
        final PaddingValues contentPadding2;
        final Modifier modifier3;
        final boolean enabled2;
        final MutableInteractionSource interactionSource3;
        final Shape shape3;
        final ButtonElevation elevation2;
        final ButtonColors colors2;
        Modifier.Companion modifier4;
        boolean enabled3;
        MutableInteractionSource interactionSource4;
        ButtonElevation elevation3;
        CornerBasedShape shape4;
        int $dirty;
        BorderStroke border3;
        Composer $composer3;
        ButtonColors colors3;
        final PaddingValues contentPadding3;
        Shape shape5;
        int $dirty2;
        BorderStroke border4;
        boolean enabled4;
        ButtonColors colors4;
        MutableInteractionSource interactionSource5;
        State<Dp> stateElevation;
        Composer $composer4 = $composer.startRestartGroup(-1084573925);
        ComposerKt.sourceInformation($composer4, "C(Button)N(onClick,modifier,enabled,interactionSource,elevation,shape,border,colors,contentPadding,content)108@5157L21,111@5257L22,114@5354L24,119@5603L646,109@5183L1066:Button.kt#jmzs0o");
        int $dirty3 = $changed;
        if (($changed & 6) == 0) {
            $dirty3 |= $composer4.changedInstance(function0) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer4.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty3 |= 384;
            z = enabled;
        } else if (($changed & 384) == 0) {
            z = enabled;
            $dirty3 |= $composer4.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty3 |= 3072;
            interactionSource2 = interactionSource;
        } else if (($changed & 3072) == 0) {
            interactionSource2 = interactionSource;
            $dirty3 |= $composer4.changed(interactionSource2) ? 2048 : 1024;
        } else {
            interactionSource2 = interactionSource;
        }
        if (($changed & 24576) == 0) {
            $dirty3 |= ((i & 16) == 0 && $composer4.changed(elevation)) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i5 = $composer4.changed(shape2) ? 131072 : 65536;
                $dirty3 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i5;
        } else {
            shape2 = shape;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty3 |= 1572864;
            borderStroke = border;
        } else if ((1572864 & $changed) == 0) {
            borderStroke = border;
            $dirty3 |= $composer4.changed(borderStroke) ? 1048576 : 524288;
        } else {
            borderStroke = border;
        }
        if (($changed & 12582912) == 0) {
            $dirty3 |= ((i & 128) == 0 && $composer4.changed(colors)) ? 8388608 : 4194304;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer4.changed(contentPadding) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty3 |= $composer4.changedInstance(function3) ? 536870912 : 268435456;
        }
        int $dirty4 = $dirty3;
        if ($composer4.shouldExecute(($dirty4 & 306783379) != 306783378, $dirty4 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "99@4736L11,100@4782L6,102@4872L14");
            if (($changed & 1) != 0 && !$composer4.getDefaultsInvalid()) {
                $composer4.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty4 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty4 &= -458753;
                }
                if ((i & 128) != 0) {
                    $dirty4 &= -29360129;
                }
                elevation3 = elevation;
                colors4 = colors;
                contentPadding3 = contentPadding;
                border4 = borderStroke;
                shape5 = shape2;
                $dirty2 = $dirty4;
                enabled4 = z;
                $composer3 = $composer4;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i3 == 0) {
                    enabled3 = z;
                } else {
                    enabled3 = true;
                }
                if (i4 == 0) {
                    interactionSource4 = interactionSource2;
                } else {
                    interactionSource4 = null;
                }
                if ((i & 16) == 0) {
                    elevation3 = elevation;
                } else {
                    elevation3 = ButtonDefaults.INSTANCE.m1728elevationR_JCAzs(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer4, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
                    $dirty4 &= -57345;
                }
                if ((i & 32) == 0) {
                    shape4 = shape2;
                    $dirty = $dirty4;
                } else {
                    shape4 = MaterialTheme.INSTANCE.getShapes($composer4, 6).getSmall();
                    $dirty = $dirty4 & (-458753);
                }
                if (i6 == 0) {
                    border3 = border;
                } else {
                    border3 = null;
                }
                if ((i & 128) == 0) {
                    $composer3 = $composer4;
                    colors3 = colors;
                } else {
                    colors3 = ButtonDefaults.INSTANCE.m1727buttonColorsro_MJ88(0L, 0L, 0L, 0L, $composer4, 24576, 15);
                    $composer3 = $composer4;
                    $dirty &= -29360129;
                }
                if (i7 == 0) {
                    contentPadding3 = contentPadding;
                    shape5 = shape4;
                    $dirty2 = $dirty;
                    border4 = border3;
                    enabled4 = enabled3;
                    modifier2 = modifier4;
                    colors4 = colors3;
                    interactionSource2 = interactionSource4;
                } else {
                    contentPadding3 = ButtonDefaults.INSTANCE.getContentPadding();
                    shape5 = shape4;
                    $dirty2 = $dirty;
                    border4 = border3;
                    enabled4 = enabled3;
                    modifier2 = modifier4;
                    colors4 = colors3;
                    interactionSource2 = interactionSource4;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1084573925, $dirty2, -1, "androidx.compose.material.Button (Button.kt:105)");
            }
            if (interactionSource2 == null) {
                $composer3.startReplaceGroup(497721888);
                ComposerKt.sourceInformation($composer3, "107@5086L39");
                ComposerKt.sourceInformationMarkerStart($composer3, 1401528866, "CC(remember):Button.kt#9igjgp");
                Composer $this$cache$iv = $composer3;
                Object it$iv = $this$cache$iv.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $this$cache$iv.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
                interactionSource5 = (MutableInteractionSource) it$iv;
            } else {
                $composer3.startReplaceGroup(1401528215);
                $composer3.endReplaceGroup();
                interactionSource5 = interactionSource2;
            }
            final State<Color> stateContentColor = colors4.contentColor(enabled4, $composer3, (($dirty2 >> 6) & 14) | (($dirty2 >> 18) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, 1401534321, "CC(remember):Button.kt#9igjgp");
            Composer $this$cache$iv2 = $composer3;
            Object it$iv2 = $this$cache$iv2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material.ButtonKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ButtonKt.Button$lambda$2$0((SemanticsPropertyReceiver) obj);
                    }
                };
                $this$cache$iv2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier2, false, (Function1) it$iv2, 1, null);
            long jM5107unboximpl = colors4.backgroundColor(enabled4, $composer3, (($dirty2 >> 6) & 14) | (($dirty2 >> 18) & 112)).getValue().m5107unboximpl();
            long jButton$lambda$1 = Button$lambda$1(stateContentColor);
            long jM5095copywmQWz5c = Color.m5095copywmQWz5c(jButton$lambda$1, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jButton$lambda$1) : 1.0f, (14 & 2) != 0 ? Color.m5103getRedimpl(jButton$lambda$1) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jButton$lambda$1) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jButton$lambda$1) : 0.0f);
            if (elevation3 == null) {
                $composer3.startReplaceGroup(498128545);
                $composer3.endReplaceGroup();
                stateElevation = null;
            } else {
                $composer3.startReplaceGroup(1401541984);
                ComposerKt.sourceInformation($composer3, "117@5496L37");
                stateElevation = elevation3.elevation(enabled4, interactionSource5, $composer3, (($dirty2 >> 6) & 14) | (($dirty2 >> 6) & 896));
                $composer3.endReplaceGroup();
            }
            ButtonColors colors5 = colors4;
            Composer $composer5 = $composer3;
            SurfaceKt.m1955SurfaceLPr_se0(function0, modifierSemantics$default, enabled4, shape5, jM5107unboximpl, jM5095copywmQWz5c, border4, stateElevation != null ? stateElevation.getValue().m7919unboximpl() : Dp.m7905constructorimpl(0), interactionSource5, ComposableLambdaKt.rememberComposableLambda(-20345758, true, new Function2() { // from class: androidx.compose.material.ButtonKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ButtonKt.Button$lambda$3(stateContentColor, contentPadding3, function3, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer5, ($dirty2 & 14) | C.ENCODING_PCM_32BIT | ($dirty2 & 896) | (($dirty2 >> 6) & 7168) | (3670016 & $dirty2), 0);
            $composer2 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            elevation2 = elevation3;
            modifier3 = modifier2;
            contentPadding2 = contentPadding3;
            interactionSource3 = interactionSource2;
            enabled2 = enabled4;
            shape3 = shape5;
            border2 = border4;
            colors2 = colors5;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            border2 = border;
            contentPadding2 = contentPadding;
            modifier3 = modifier2;
            enabled2 = z;
            interactionSource3 = interactionSource2;
            shape3 = shape2;
            elevation2 = elevation;
            colors2 = colors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ButtonKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ButtonKt.Button$lambda$4(function0, modifier3, enabled2, interactionSource3, elevation2, shape3, border2, colors2, contentPadding2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final long Button$lambda$1(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m5107unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Button$lambda$2$0(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.m7118setRolekuIjeqM($this$semantics, Role.INSTANCE.m7099getButtono7Vup1c());
        return Unit.INSTANCE;
    }

    static final Unit Button$lambda$3(State $contentColor$delegate, final PaddingValues $contentPadding, final Function3 $content, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C120@5685L558,120@5613L630:Button.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-20345758, $changed, -1, "androidx.compose.material.Button.<anonymous> (Button.kt:120)");
            }
            CompositionLocalKt.CompositionLocalProvider(ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m5099getAlphaimpl(Button$lambda$1($contentColor$delegate)))), ComposableLambdaKt.rememberComposableLambda(-869936862, true, new Function2() { // from class: androidx.compose.material.ButtonKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ButtonKt.Button$lambda$3$0($contentPadding, $content, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Button$lambda$3$0(final PaddingValues $contentPadding, final Function3 $content, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C121@5738L10,121@5757L476,121@5699L534:Button.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-869936862, $changed, -1, "androidx.compose.material.Button.<anonymous>.<anonymous> (Button.kt:121)");
            }
            TextKt.ProvideTextStyle(MaterialTheme.INSTANCE.getTypography($composer, 6).getButton(), ComposableLambdaKt.rememberComposableLambda(165539859, true, new Function2() { // from class: androidx.compose.material.ButtonKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ButtonKt.Button$lambda$3$0$0($contentPadding, $content, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Button$lambda$3$0$0(PaddingValues $contentPadding, Function3 $content, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C122@5775L444:Button.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(165539859, $changed, -1, "androidx.compose.material.Button.<anonymous>.<anonymous>.<anonymous> (Button.kt:122)");
            }
            Modifier modifier$iv = PaddingKt.padding(SizeKt.m849defaultMinSizeVpY3zN4(Modifier.INSTANCE, ButtonDefaults.INSTANCE.m1733getMinWidthD9Ej5fM(), ButtonDefaults.INSTANCE.m1732getMinHeightD9Ej5fM()), $contentPadding);
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getCenter();
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            int $changed$iv$iv = (432 << 3) & 112;
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
            $content.invoke(RowScopeInstance.INSTANCE, $composer, Integer.valueOf(((432 >> 6) & 112) | 6));
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

    public static final void OutlinedButton(Function0<Unit> function0, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, ButtonElevation elevation, Shape shape, BorderStroke border, ButtonColors colors, PaddingValues contentPadding, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, int $changed, int i) {
        Modifier modifier2;
        boolean enabled2;
        MutableInteractionSource interactionSource2;
        ButtonElevation elevation2;
        Shape shape2;
        BorderStroke border2;
        ButtonColors colors2;
        PaddingValues contentPadding2;
        ComposerKt.sourceInformationMarkerStart($composer, -1445054947, "C(OutlinedButton)N(onClick,modifier,enabled,interactionSource,elevation,shape,border,colors,contentPadding,content)175@8304L6,176@8361L14,177@8419L22,181@8564L315:Button.kt#jmzs0o");
        if ((i & 2) != 0) {
            modifier2 = Modifier.INSTANCE;
        } else {
            modifier2 = modifier;
        }
        if ((i & 4) == 0) {
            enabled2 = enabled;
        } else {
            enabled2 = true;
        }
        if ((i & 8) == 0) {
            interactionSource2 = interactionSource;
        } else {
            interactionSource2 = null;
        }
        if ((i & 16) == 0) {
            elevation2 = elevation;
        } else {
            elevation2 = null;
        }
        if ((i & 32) == 0) {
            shape2 = shape;
        } else {
            shape2 = MaterialTheme.INSTANCE.getShapes($composer, 6).getSmall();
        }
        if ((i & 64) == 0) {
            border2 = border;
        } else {
            border2 = ButtonDefaults.INSTANCE.getOutlinedBorder($composer, 6);
        }
        if ((i & 128) == 0) {
            colors2 = colors;
        } else {
            colors2 = ButtonDefaults.INSTANCE.m1735outlinedButtonColorsRGew2ao(0L, 0L, 0L, $composer, 3072, 7);
        }
        if ((i & 256) == 0) {
            contentPadding2 = contentPadding;
        } else {
            contentPadding2 = ButtonDefaults.INSTANCE.getContentPadding();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1445054947, $changed, -1, "androidx.compose.material.OutlinedButton (Button.kt:181)");
        }
        Button(function0, modifier2, enabled2, interactionSource2, elevation2, shape2, border2, colors2, contentPadding2, function3, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | ($changed & 7168) | (57344 & $changed) | (458752 & $changed) | (3670016 & $changed) | (29360128 & $changed) | (234881024 & $changed) | (1879048192 & $changed), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    public static final void TextButton(Function0<Unit> function0, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, ButtonElevation elevation, Shape shape, BorderStroke border, ButtonColors colors, PaddingValues contentPadding, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, int $changed, int i) {
        Modifier modifier2;
        boolean enabled2;
        MutableInteractionSource interactionSource2;
        ButtonElevation elevation2;
        Shape shape2;
        BorderStroke border2;
        ButtonColors colors2;
        PaddingValues contentPadding2;
        ComposerKt.sourceInformationMarkerStart($composer, 724562088, "C(TextButton)N(onClick,modifier,enabled,interactionSource,elevation,shape,border,colors,contentPadding,content)232@10929L6,234@11019L18,238@11170L315:Button.kt#jmzs0o");
        if ((i & 2) != 0) {
            modifier2 = Modifier.INSTANCE;
        } else {
            modifier2 = modifier;
        }
        if ((i & 4) == 0) {
            enabled2 = enabled;
        } else {
            enabled2 = true;
        }
        if ((i & 8) == 0) {
            interactionSource2 = interactionSource;
        } else {
            interactionSource2 = null;
        }
        if ((i & 16) == 0) {
            elevation2 = elevation;
        } else {
            elevation2 = null;
        }
        if ((i & 32) == 0) {
            shape2 = shape;
        } else {
            shape2 = MaterialTheme.INSTANCE.getShapes($composer, 6).getSmall();
        }
        if ((i & 64) == 0) {
            border2 = border;
        } else {
            border2 = null;
        }
        if ((i & 128) == 0) {
            colors2 = colors;
        } else {
            colors2 = ButtonDefaults.INSTANCE.m1736textButtonColorsRGew2ao(0L, 0L, 0L, $composer, 3072, 7);
        }
        if ((i & 256) == 0) {
            contentPadding2 = contentPadding;
        } else {
            contentPadding2 = ButtonDefaults.INSTANCE.getTextButtonContentPadding();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(724562088, $changed, -1, "androidx.compose.material.TextButton (Button.kt:238)");
        }
        Button(function0, modifier2, enabled2, interactionSource2, elevation2, shape2, border2, colors2, contentPadding2, function3, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | ($changed & 7168) | (57344 & $changed) | (458752 & $changed) | (3670016 & $changed) | (29360128 & $changed) | (234881024 & $changed) | (1879048192 & $changed), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
    }
}
