package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Card.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a_\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001a\u0083\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u00132\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0018\u001aS\u0010\u0019\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u001a\u001aw\u0010\u0019\u001a\u00020\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u00132\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u001b\u001a]\u0010\u001c\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001a\u0081\u0001\u0010\u001c\u001a\u00020\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u00132\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0018¨\u0006\u001d"}, d2 = {"Card", "", "modifier", "Landroidx/compose/ui/Modifier;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/CardColors;", "elevation", "Landroidx/compose/material3/CardElevation;", androidx.compose.material.OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/CardColors;Landroidx/compose/material3/CardElevation;Landroidx/compose/foundation/BorderStroke;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "onClick", "Lkotlin/Function0;", "enabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/CardColors;Landroidx/compose/material3/CardElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ElevatedCard", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/CardColors;Landroidx/compose/material3/CardElevation;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/CardColors;Landroidx/compose/material3/CardElevation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "OutlinedCard", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class CardKt {
    static final Unit Card$lambda$0(Modifier modifier, Shape shape, CardColors cardColors, CardElevation cardElevation, BorderStroke borderStroke, Function3 function3, int i, int i2, Composer composer, int i3) {
        Card(modifier, shape, cardColors, cardElevation, borderStroke, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Card$lambda$2(Function0 function0, Modifier modifier, boolean z, Shape shape, CardColors cardColors, CardElevation cardElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, Function3 function3, int i, int i2, Composer composer, int i3) {
        Card(function0, modifier, z, shape, cardColors, cardElevation, borderStroke, mutableInteractionSource, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ElevatedCard$lambda$3(Modifier modifier, Shape shape, CardColors cardColors, CardElevation cardElevation, Function3 function3, int i, int i2, Composer composer, int i3) {
        ElevatedCard(modifier, shape, cardColors, cardElevation, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ElevatedCard$lambda$4(Function0 function0, Modifier modifier, boolean z, Shape shape, CardColors cardColors, CardElevation cardElevation, MutableInteractionSource mutableInteractionSource, Function3 function3, int i, int i2, Composer composer, int i3) {
        ElevatedCard(function0, modifier, z, shape, cardColors, cardElevation, mutableInteractionSource, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit OutlinedCard$lambda$5(Modifier modifier, Shape shape, CardColors cardColors, CardElevation cardElevation, BorderStroke borderStroke, Function3 function3, int i, int i2, Composer composer, int i3) {
        OutlinedCard(modifier, shape, cardColors, cardElevation, borderStroke, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit OutlinedCard$lambda$6(Function0 function0, Modifier modifier, boolean z, Shape shape, CardColors cardColors, CardElevation cardElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, Function3 function3, int i, int i2, Composer composer, int i3) {
        OutlinedCard(function0, modifier, z, shape, cardColors, cardElevation, borderStroke, mutableInteractionSource, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void Card(Modifier modifier, Shape shape, CardColors colors, CardElevation elevation, BorderStroke border, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        CardColors cardColors;
        CardElevation cardElevation;
        BorderStroke borderStroke;
        final Modifier modifier3;
        final Shape shape3;
        final CardColors colors2;
        final CardElevation elevation2;
        final BorderStroke border2;
        Modifier.Companion modifier4;
        Shape shape4;
        CardColors colors3;
        CardElevation elevation3;
        int $dirty;
        CardElevation elevation4;
        Modifier modifier5;
        BorderStroke border3;
        Shape shape5;
        Composer $composer2 = $composer.startRestartGroup(1359693790);
        ComposerKt.sourceInformation($composer2, "C(Card)N(modifier,shape,colors,elevation,border,content)92@4165L57,94@4261L41,87@3953L349:Card.kt#uh7d8r");
        int $dirty2 = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty2 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                shape2 = shape;
                int i3 = $composer2.changed(shape2) ? 32 : 16;
                $dirty2 |= i3;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i3;
        } else {
            shape2 = shape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                cardColors = colors;
                int i4 = $composer2.changed(cardColors) ? 256 : 128;
                $dirty2 |= i4;
            } else {
                cardColors = colors;
            }
            $dirty2 |= i4;
        } else {
            cardColors = colors;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                cardElevation = elevation;
                int i5 = $composer2.changed(cardElevation) ? 2048 : 1024;
                $dirty2 |= i5;
            } else {
                cardElevation = elevation;
            }
            $dirty2 |= i5;
        } else {
            cardElevation = elevation;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty2 |= 24576;
            borderStroke = border;
        } else if (($changed & 24576) == 0) {
            borderStroke = border;
            $dirty2 |= $composer2.changed(borderStroke) ? 16384 : 8192;
        } else {
            borderStroke = border;
        }
        if ((i & 32) != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer2.changedInstance(function3) ? 131072 : 65536;
        }
        boolean z = true;
        if ($composer2.shouldExecute((74899 & $dirty2) != 74898, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "81@3742L5,82@3787L12,83@3845L15");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty2 &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                $dirty = $dirty2;
                elevation4 = cardElevation;
                border3 = borderStroke;
                modifier5 = modifier2;
                shape5 = shape2;
                colors3 = cardColors;
                z = true;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 2) == 0) {
                    shape4 = shape2;
                } else {
                    shape4 = CardDefaults.INSTANCE.getShape($composer2, 6);
                    $dirty2 &= -113;
                }
                if ((i & 4) == 0) {
                    colors3 = cardColors;
                } else {
                    colors3 = CardDefaults.INSTANCE.cardColors($composer2, 6);
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    elevation3 = CardDefaults.INSTANCE.m2145cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer2, 1572864, 63);
                    $dirty2 &= -7169;
                } else {
                    elevation3 = elevation;
                }
                if (i6 == 0) {
                    Modifier modifier6 = modifier4;
                    $dirty = $dirty2;
                    elevation4 = elevation3;
                    modifier5 = modifier6;
                    border3 = border;
                    shape5 = shape4;
                } else {
                    Modifier modifier7 = modifier4;
                    $dirty = $dirty2;
                    elevation4 = elevation3;
                    modifier5 = modifier7;
                    border3 = null;
                    shape5 = shape4;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1359693790, $dirty, -1, "androidx.compose.material3.Card (Card.kt:86)");
            }
            SurfaceKt.m2929SurfaceT9BRK9s(modifier5, shape5, colors3.m2137containerColorvNxB06k$material3(z), colors3.m2138contentColorvNxB06k$material3(z), 0.0f, elevation4.shadowElevation$material3(z, null, $composer2, (($dirty >> 3) & 896) | 54).getValue().m7919unboximpl(), border3, ComposableLambdaKt.rememberComposableLambda(-97109725, z, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code duplicated, block: B:27:0x0142  */
                /* JADX WARN: Code duplicated, block: B:30:? A[RETURN, SYNTHETIC] */
                public final void invoke(Composer $composer3, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    int compositeKeyHash$iv$iv;
                    ComposerKt.sourceInformation($composer3, "C95@4271L25:Card.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-97109725, $changed2, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:95)");
                    }
                    Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                    ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                    Modifier modifier$iv = Modifier.INSTANCE;
                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function0 = constructor;
                        $composer3.createNode(function0);
                    } else {
                        function0 = constructor;
                        $composer3.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                        compositeKeyHash$iv$iv = compositeKeyHash$iv$iv2;
                        if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i7 = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                        function4.invoke(ColumnScopeInstance.INSTANCE, $composer3, Integer.valueOf(((0 >> 6) & 112) | 6));
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    compositeKeyHash$iv$iv = compositeKeyHash$iv$iv2;
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i8 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                    function4.invoke(ColumnScopeInstance.INSTANCE, $composer3, Integer.valueOf(((0 >> 6) & 112) | 6));
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, ($dirty & 14) | 12582912 | ($dirty & 112) | (($dirty << 6) & 3670016), 16);
            $composer2 = $composer2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape3 = shape5;
            colors2 = colors3;
            border2 = border3;
            elevation2 = elevation4;
            modifier3 = modifier5;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            shape3 = shape2;
            colors2 = cardColors;
            elevation2 = elevation;
            border2 = border;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.CardKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CardKt.Card$lambda$0(modifier3, shape3, colors2, elevation2, border2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void Card(final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, CardColors colors, CardElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        CardColors cardColors;
        BorderStroke borderStroke;
        Composer $composer2;
        final Modifier modifier3;
        final CardColors colors2;
        final CardElevation elevation2;
        final boolean enabled3;
        final Shape shape3;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        CardColors colors3;
        Composer $composer3;
        CardElevation elevation3;
        BorderStroke border3;
        MutableInteractionSource interactionSource3;
        BorderStroke border4;
        Modifier modifier4;
        boolean enabled4;
        Shape shape4;
        MutableInteractionSource interactionSource4;
        Composer $composer4 = $composer.startRestartGroup(2136075085);
        ComposerKt.sourceInformation($composer4, "C(Card)N(onClick,modifier,enabled,shape,colors,elevation,border,interactionSource,content)155@7038L43,158@7167L41,148@6786L422:Card.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer4.changedInstance(function0) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer4.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty |= $composer4.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i4 = $composer4.changed(shape2) ? 2048 : 1024;
                $dirty |= i4;
            } else {
                shape2 = shape;
            }
            $dirty |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                cardColors = colors;
                int i5 = $composer4.changed(cardColors) ? 16384 : 8192;
                $dirty |= i5;
            } else {
                cardColors = colors;
            }
            $dirty |= i5;
        } else {
            cardColors = colors;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= ((i & 32) == 0 && $composer4.changed(elevation)) ? 131072 : 65536;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty |= 1572864;
            borderStroke = border;
        } else if ((1572864 & $changed) == 0) {
            borderStroke = border;
            $dirty |= $composer4.changed(borderStroke) ? 1048576 : 524288;
        } else {
            borderStroke = border;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer4.changed(interactionSource) ? 8388608 : 4194304;
        }
        if ((i & 256) != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer4.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ($composer4.shouldExecute((38347923 & $dirty) != 38347922, $dirty & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "139@6397L5,140@6442L12,141@6500L15");
            if (($changed & 1) != 0 && !$composer4.getDefaultsInvalid()) {
                $composer4.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                interactionSource3 = interactionSource;
                shape4 = shape2;
                colors3 = cardColors;
                border4 = borderStroke;
                $composer3 = $composer4;
                modifier4 = modifier2;
                enabled4 = enabled2;
                elevation3 = elevation;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    shape2 = CardDefaults.INSTANCE.getShape($composer4, 6);
                }
                if ((i & 16) == 0) {
                    colors3 = cardColors;
                } else {
                    colors3 = CardDefaults.INSTANCE.cardColors($composer4, 6);
                    $dirty &= -57345;
                }
                if ((i & 32) == 0) {
                    $composer3 = $composer4;
                    elevation3 = elevation;
                } else {
                    elevation3 = CardDefaults.INSTANCE.m2145cardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer4, 1572864, 63);
                    $composer3 = $composer4;
                    $dirty &= -458753;
                }
                if (i6 == 0) {
                    border3 = border;
                } else {
                    border3 = null;
                }
                if (i7 == 0) {
                    interactionSource3 = interactionSource;
                    border4 = border3;
                    modifier4 = modifier2;
                    enabled4 = enabled2;
                    shape4 = shape2;
                } else {
                    interactionSource3 = null;
                    border4 = border3;
                    modifier4 = modifier2;
                    enabled4 = enabled2;
                    shape4 = shape2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2136075085, $dirty, -1, "androidx.compose.material3.Card (Card.kt:145)");
            }
            if (interactionSource3 == null) {
                $composer3.startReplaceGroup(1577885006);
                ComposerKt.sourceInformation($composer3, "147@6742L39");
                ComposerKt.sourceInformationMarkerStart($composer3, -226195148, "CC(remember):Card.kt#9igjgp");
                Composer $this$cache$iv = $composer3;
                Object it$iv = $this$cache$iv.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $this$cache$iv.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
                interactionSource4 = (MutableInteractionSource) it$iv;
            } else {
                $composer3.startReplaceGroup(-226195799);
                $composer3.endReplaceGroup();
                interactionSource4 = interactionSource3;
            }
            $composer2 = $composer3;
            SurfaceKt.m2932Surfaceo_FOJdg(function0, modifier4, enabled4, shape4, colors3.m2137containerColorvNxB06k$material3(enabled4), colors3.m2138contentColorvNxB06k$material3(enabled4), 0.0f, elevation3.shadowElevation$material3(enabled4, interactionSource4, $composer3, (($dirty >> 6) & 14) | (($dirty >> 9) & 896)).getValue().m7919unboximpl(), border4, interactionSource4, ComposableLambdaKt.rememberComposableLambda(-1347531112, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.CardKt.Card.3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code duplicated, block: B:27:0x0142  */
                /* JADX WARN: Code duplicated, block: B:30:? A[RETURN, SYNTHETIC] */
                public final void invoke(Composer $composer5, int $changed2) {
                    Function0<ComposeUiNode> function1;
                    int compositeKeyHash$iv$iv;
                    ComposerKt.sourceInformation($composer5, "C159@7177L25:Card.kt#uh7d8r");
                    if (!$composer5.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer5.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1347531112, $changed2, -1, "androidx.compose.material3.Card.<anonymous> (Card.kt:159)");
                    }
                    Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                    ComposerKt.sourceInformationMarkerStart($composer5, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                    Modifier modifier$iv = Modifier.INSTANCE;
                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                    CompositionLocalMap localMap$iv$iv = $composer5.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer5, modifier$iv);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer5.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer5.startReusableNode();
                    if ($composer5.getInserting()) {
                        function1 = constructor;
                        $composer5.createNode(function1);
                    } else {
                        function1 = constructor;
                        $composer5.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer5);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                        compositeKeyHash$iv$iv = compositeKeyHash$iv$iv2;
                        if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i8 = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer5, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                        function4.invoke(ColumnScopeInstance.INSTANCE, $composer5, Integer.valueOf(((0 >> 6) & 112) | 6));
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        $composer5.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    compositeKeyHash$iv$iv = compositeKeyHash$iv$iv2;
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i9 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer5, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                    function4.invoke(ColumnScopeInstance.INSTANCE, $composer5, Integer.valueOf(((0 >> 6) & 112) | 6));
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    $composer5.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 6) & 234881024), 6, 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors2 = colors3;
            interactionSource2 = interactionSource3;
            modifier3 = modifier4;
            enabled3 = enabled4;
            shape3 = shape4;
            border2 = border4;
            elevation2 = elevation3;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            colors2 = cardColors;
            elevation2 = elevation;
            enabled3 = enabled2;
            shape3 = shape2;
            border2 = border;
            interactionSource2 = interactionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.CardKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CardKt.Card$lambda$2(function0, modifier3, enabled3, shape3, colors2, elevation2, border2, interactionSource2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void ElevatedCard(Modifier modifier, Shape shape, CardColors colors, CardElevation elevation, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        CardColors cardColors;
        CardElevation cardElevation;
        Composer $composer2;
        final Modifier modifier3;
        final Shape shape3;
        final CardColors colors2;
        final CardElevation elevation2;
        Shape shape4;
        CardColors colors3;
        Composer $composer3;
        CardElevation elevation3;
        Modifier modifier4;
        Shape shape5;
        CardColors colors4;
        Composer $composer4 = $composer.startRestartGroup(-1464672362);
        ComposerKt.sourceInformation($composer4, "C(ElevatedCard)N(modifier,shape,colors,elevation,content)197@8874L169:Card.kt#uh7d8r");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer4.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                shape2 = shape;
                int i3 = $composer4.changed(shape2) ? 32 : 16;
                $dirty |= i3;
            } else {
                shape2 = shape;
            }
            $dirty |= i3;
        } else {
            shape2 = shape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                cardColors = colors;
                int i4 = $composer4.changed(cardColors) ? 256 : 128;
                $dirty |= i4;
            } else {
                cardColors = colors;
            }
            $dirty |= i4;
        } else {
            cardColors = colors;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                cardElevation = elevation;
                int i5 = $composer4.changed(cardElevation) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                cardElevation = elevation;
            }
            $dirty |= i5;
        } else {
            cardElevation = elevation;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer4.changedInstance(function3) ? 16384 : 8192;
        }
        if ($composer4.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "192@8673L13,193@8726L20,194@8792L23");
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    shape4 = CardDefaults.INSTANCE.getElevatedShape($composer4, 6);
                    $dirty &= -113;
                } else {
                    shape4 = shape2;
                }
                if ((i & 4) != 0) {
                    colors3 = CardDefaults.INSTANCE.elevatedCardColors($composer4, 6);
                    $dirty &= -897;
                } else {
                    colors3 = cardColors;
                }
                if ((i & 8) != 0) {
                    $composer3 = $composer4;
                    $dirty &= -7169;
                    modifier4 = modifier5;
                    shape5 = shape4;
                    colors4 = colors3;
                    elevation3 = CardDefaults.INSTANCE.m2147elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    $composer3 = $composer4;
                    elevation3 = elevation;
                    modifier4 = modifier5;
                    shape5 = shape4;
                    colors4 = colors3;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                shape5 = shape2;
                colors4 = cardColors;
                elevation3 = cardElevation;
                $composer3 = $composer4;
                modifier4 = modifier2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1464672362, $dirty, -1, "androidx.compose.material3.ElevatedCard (Card.kt:197)");
            }
            Composer $composer5 = $composer3;
            Card(modifier4, shape5, colors4, elevation3, null, function3, $composer5, ($dirty & 14) | 24576 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 458752), 0);
            $composer2 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape3 = shape5;
            colors2 = colors4;
            elevation2 = elevation3;
            modifier3 = modifier4;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            shape3 = shape2;
            colors2 = cardColors;
            elevation2 = elevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.CardKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CardKt.ElevatedCard$lambda$3(modifier3, shape3, colors2, elevation2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void ElevatedCard(final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, CardColors colors, CardElevation elevation, MutableInteractionSource interactionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function1;
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        CardColors cardColors;
        CardElevation cardElevation;
        MutableInteractionSource mutableInteractionSource;
        Composer $composer2;
        final Modifier modifier3;
        final CardColors colors2;
        final CardElevation elevation2;
        final boolean enabled3;
        final Shape shape3;
        final MutableInteractionSource interactionSource2;
        CardColors colors3;
        Composer $composer3;
        CardElevation elevation3;
        CardColors colors4;
        CardElevation elevation4;
        Modifier modifier4;
        boolean enabled4;
        Shape shape4;
        Composer $composer4;
        MutableInteractionSource interactionSource3;
        Composer $composer5 = $composer.startRestartGroup(-129138571);
        ComposerKt.sourceInformation($composer5, "C(ElevatedCard)N(onClick,modifier,enabled,shape,colors,elevation,interactionSource,content)250@11337L270:Card.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function1 = function0;
        } else if (($changed & 6) == 0) {
            function1 = function0;
            $dirty |= $composer5.changedInstance(function1) ? 4 : 2;
        } else {
            function1 = function0;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer5.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty |= $composer5.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i4 = $composer5.changed(shape2) ? 2048 : 1024;
                $dirty |= i4;
            } else {
                shape2 = shape;
            }
            $dirty |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                cardColors = colors;
                int i5 = $composer5.changed(cardColors) ? 16384 : 8192;
                $dirty |= i5;
            } else {
                cardColors = colors;
            }
            $dirty |= i5;
        } else {
            cardColors = colors;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                cardElevation = elevation;
                int i6 = $composer5.changed(cardElevation) ? 131072 : 65536;
                $dirty |= i6;
            } else {
                cardElevation = elevation;
            }
            $dirty |= i6;
        } else {
            cardElevation = elevation;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty |= 1572864;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 1572864) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty |= $composer5.changed(mutableInteractionSource) ? 1048576 : 524288;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer5.changedInstance(function3) ? 8388608 : 4194304;
        }
        if (!$composer5.shouldExecute((4793491 & $dirty) != 4793490, $dirty & 1)) {
            $composer2 = $composer5;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            colors2 = cardColors;
            elevation2 = elevation;
            enabled3 = enabled2;
            shape3 = shape2;
            interactionSource2 = interactionSource;
        } else {
            $composer5.startDefaults();
            ComposerKt.sourceInformation($composer5, "244@11079L13,245@11132L20,246@11198L23");
            if (($changed & 1) != 0 && !$composer5.getDefaultsInvalid()) {
                $composer5.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                $composer4 = $composer5;
                shape4 = shape2;
                colors4 = cardColors;
                elevation4 = cardElevation;
                interactionSource3 = mutableInteractionSource;
                modifier4 = modifier2;
                enabled4 = enabled2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    shape2 = CardDefaults.INSTANCE.getElevatedShape($composer5, 6);
                }
                if ((i & 16) == 0) {
                    colors3 = cardColors;
                } else {
                    colors3 = CardDefaults.INSTANCE.elevatedCardColors($composer5, 6);
                    $dirty &= -57345;
                }
                if ((i & 32) == 0) {
                    $composer3 = $composer5;
                    elevation3 = elevation;
                } else {
                    $composer3 = $composer5;
                    elevation3 = CardDefaults.INSTANCE.m2147elevatedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty &= -458753;
                }
                if (i7 == 0) {
                    colors4 = colors3;
                    elevation4 = elevation3;
                    modifier4 = modifier2;
                    enabled4 = enabled2;
                    shape4 = shape2;
                    $composer4 = $composer3;
                    interactionSource3 = interactionSource;
                } else {
                    colors4 = colors3;
                    elevation4 = elevation3;
                    modifier4 = modifier2;
                    enabled4 = enabled2;
                    shape4 = shape2;
                    $composer4 = $composer3;
                    interactionSource3 = null;
                }
            }
            $composer4.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-129138571, $dirty, -1, "androidx.compose.material3.ElevatedCard (Card.kt:250)");
            }
            Card(function1, modifier4, enabled4, shape4, colors4, elevation4, null, interactionSource3, function3, $composer4, ($dirty & 14) | 1572864 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (($dirty << 3) & 29360128) | (($dirty << 3) & 234881024), 0);
            MutableInteractionSource interactionSource4 = interactionSource3;
            $composer2 = $composer4;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            interactionSource2 = interactionSource4;
            modifier3 = modifier4;
            enabled3 = enabled4;
            shape3 = shape4;
            colors2 = colors4;
            elevation2 = elevation4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.CardKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CardKt.ElevatedCard$lambda$4(function0, modifier3, enabled3, shape3, colors2, elevation2, interactionSource2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void OutlinedCard(Modifier modifier, Shape shape, CardColors colors, CardElevation elevation, BorderStroke border, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        CardColors cardColors;
        CardElevation elevation2;
        BorderStroke borderStroke;
        Composer $composer2;
        final Modifier modifier3;
        final Shape shape3;
        final CardColors colors2;
        final CardElevation elevation3;
        final BorderStroke border2;
        Shape shape4;
        CardColors colors3;
        int i2;
        Composer $composer3;
        BorderStroke border3;
        Modifier modifier4;
        Shape shape5;
        CardColors colors4;
        CardElevation elevation4;
        Composer $composer4 = $composer.startRestartGroup(-1945643296);
        ComposerKt.sourceInformation($composer4, "C(OutlinedCard)N(modifier,shape,colors,elevation,border,content)298@13425L171:Card.kt#uh7d8r");
        int $dirty = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer4.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                shape2 = shape;
                int i4 = $composer4.changed(shape2) ? 32 : 16;
                $dirty |= i4;
            } else {
                shape2 = shape;
            }
            $dirty |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                cardColors = colors;
                int i5 = $composer4.changed(cardColors) ? 256 : 128;
                $dirty |= i5;
            } else {
                cardColors = colors;
            }
            $dirty |= i5;
        } else {
            cardColors = colors;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                elevation2 = elevation;
                int i6 = $composer4.changed(elevation2) ? 2048 : 1024;
                $dirty |= i6;
            } else {
                elevation2 = elevation;
            }
            $dirty |= i6;
        } else {
            elevation2 = elevation;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                borderStroke = border;
                int i7 = $composer4.changed(borderStroke) ? 16384 : 8192;
                $dirty |= i7;
            } else {
                borderStroke = border;
            }
            $dirty |= i7;
        } else {
            borderStroke = border;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer4.changedInstance(function3) ? 131072 : 65536;
        }
        if ($composer4.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "292@13162L13,293@13215L20,294@13281L23,295@13346L20");
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    shape4 = CardDefaults.INSTANCE.getOutlinedShape($composer4, 6);
                    $dirty &= -113;
                } else {
                    shape4 = shape2;
                }
                if ((i & 4) != 0) {
                    colors3 = CardDefaults.INSTANCE.outlinedCardColors($composer4, 6);
                    $dirty &= -897;
                } else {
                    colors3 = cardColors;
                }
                if ((i & 8) != 0) {
                    $composer3 = $composer4;
                    i2 = 1;
                    elevation2 = CardDefaults.INSTANCE.m2149outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty &= -7169;
                } else {
                    i2 = 1;
                    $composer3 = $composer4;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    border3 = CardDefaults.INSTANCE.outlinedCardBorder(false, $composer3, 48, i2);
                    modifier4 = modifier5;
                    shape5 = shape4;
                    colors4 = colors3;
                    elevation4 = elevation2;
                } else {
                    border3 = border;
                    modifier4 = modifier5;
                    shape5 = shape4;
                    colors4 = colors3;
                    elevation4 = elevation2;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                shape5 = shape2;
                colors4 = cardColors;
                elevation4 = elevation2;
                border3 = borderStroke;
                $composer3 = $composer4;
                modifier4 = modifier2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1945643296, $dirty, -1, "androidx.compose.material3.OutlinedCard (Card.kt:298)");
            }
            Composer $composer5 = $composer3;
            Card(modifier4, shape5, colors4, elevation4, border3, function3, $composer5, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty), 0);
            $composer2 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape3 = shape5;
            colors2 = colors4;
            elevation3 = elevation4;
            border2 = border3;
            modifier3 = modifier4;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            shape3 = shape2;
            colors2 = cardColors;
            elevation3 = elevation2;
            border2 = border;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.CardKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CardKt.OutlinedCard$lambda$5(modifier3, shape3, colors2, elevation3, border2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void OutlinedCard(final Function0<Unit> function0, Modifier modifier, boolean enabled, Shape shape, CardColors colors, CardElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean enabled2;
        Shape shape2;
        CardColors colors2;
        CardElevation cardElevation;
        MutableInteractionSource mutableInteractionSource;
        Composer $composer2;
        final CardElevation elevation2;
        final Modifier modifier3;
        final boolean enabled3;
        final Shape shape3;
        final CardColors colors3;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        Composer $composer3;
        CardElevation elevation3;
        BorderStroke border3;
        MutableInteractionSource interactionSource3;
        BorderStroke border4;
        CardElevation elevation4;
        Modifier modifier4;
        boolean enabled4;
        Shape shape4;
        CardColors colors4;
        Composer $composer4 = $composer.startRestartGroup(1401605899);
        ComposerKt.sourceInformation($composer4, "C(OutlinedCard)N(onClick,modifier,enabled,shape,colors,elevation,border,interactionSource,content)353@16051L272:Card.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer4.changedInstance(function0) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer4.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty |= $composer4.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i4 = $composer4.changed(shape2) ? 2048 : 1024;
                $dirty |= i4;
            } else {
                shape2 = shape;
            }
            $dirty |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                int i5 = $composer4.changed(colors2) ? 16384 : 8192;
                $dirty |= i5;
            } else {
                colors2 = colors;
            }
            $dirty |= i5;
        } else {
            colors2 = colors;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                cardElevation = elevation;
                int i6 = $composer4.changed(cardElevation) ? 131072 : 65536;
                $dirty |= i6;
            } else {
                cardElevation = elevation;
            }
            $dirty |= i6;
        } else {
            cardElevation = elevation;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= ((i & 64) == 0 && $composer4.changed(border)) ? 1048576 : 524288;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty |= 12582912;
            mutableInteractionSource = interactionSource;
        } else if ((12582912 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty |= $composer4.changed(mutableInteractionSource) ? 8388608 : 4194304;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 256) != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer4.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (!$composer4.shouldExecute(($dirty & 38347923) != 38347922, $dirty & 1)) {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            elevation2 = elevation;
            modifier3 = modifier2;
            enabled3 = enabled2;
            shape3 = shape2;
            colors3 = colors2;
            border2 = border;
            interactionSource2 = interactionSource;
        } else {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "346@15724L13,347@15777L20,348@15843L23,349@15908L27");
            if (($changed & 1) != 0 && !$composer4.getDefaultsInvalid()) {
                $composer4.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                }
                border4 = border;
                $composer3 = $composer4;
                modifier4 = modifier2;
                enabled4 = enabled2;
                colors4 = colors2;
                elevation4 = cardElevation;
                interactionSource3 = mutableInteractionSource;
                shape4 = shape2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    shape2 = CardDefaults.INSTANCE.getOutlinedShape($composer4, 6);
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    colors2 = CardDefaults.INSTANCE.outlinedCardColors($composer4, 6);
                }
                if ((i & 32) == 0) {
                    $composer3 = $composer4;
                    elevation3 = elevation;
                } else {
                    elevation3 = CardDefaults.INSTANCE.m2149outlinedCardElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer4, 1572864, 63);
                    $composer3 = $composer4;
                    $dirty &= -458753;
                }
                if ((i & 64) == 0) {
                    border3 = border;
                } else {
                    border3 = CardDefaults.INSTANCE.outlinedCardBorder(enabled2, $composer3, (($dirty >> 6) & 14) | 48, 0);
                    $dirty &= -3670017;
                }
                if (i7 == 0) {
                    interactionSource3 = interactionSource;
                    border4 = border3;
                    elevation4 = elevation3;
                    modifier4 = modifier2;
                    enabled4 = enabled2;
                    shape4 = shape2;
                    colors4 = colors2;
                } else {
                    border4 = border3;
                    elevation4 = elevation3;
                    interactionSource3 = null;
                    modifier4 = modifier2;
                    enabled4 = enabled2;
                    shape4 = shape2;
                    colors4 = colors2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1401605899, $dirty, -1, "androidx.compose.material3.OutlinedCard (Card.kt:353)");
            }
            $composer2 = $composer3;
            Card(function0, modifier4, enabled4, shape4, colors4, elevation4, border4, interactionSource3, function3, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enabled3 = enabled4;
            shape3 = shape4;
            colors3 = colors4;
            elevation2 = elevation4;
            border2 = border4;
            interactionSource2 = interactionSource3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.CardKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CardKt.OutlinedCard$lambda$6(function0, modifier3, enabled3, shape3, colors3, elevation2, border2, interactionSource2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
