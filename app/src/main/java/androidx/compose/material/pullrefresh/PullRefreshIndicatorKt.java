package androidx.compose.material.pullrefresh;

import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.ColorsKt;
import androidx.compose.material.ElevationOverlay;
import androidx.compose.material.ElevationOverlayKt;
import androidx.compose.material.MaterialTheme;
import androidx.compose.material.ProgressIndicatorKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PullRefreshIndicator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aG\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\f\u0010\r\u001a'\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u001a;\u0010\u0016\u001a\u00020\u0001*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u001e\u0010\u001f\"\u000e\u0010 \u001a\u00020!X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\"\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%\"\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010(\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%\"\u0010\u0010)\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%\"\u0010\u0010*\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%\"\u0010\u0010+\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%\"\u0010\u0010,\u001a\u00020$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%\"\u000e\u0010-\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010.\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001500X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061²\u0006\n\u00102\u001a\u00020\u0003X\u008a\u0084\u0002²\u0006\n\u00103\u001a\u00020\u0015X\u008a\u0084\u0002"}, d2 = {"PullRefreshIndicator", "", "refreshing", "", "state", "Landroidx/compose/material/pullrefresh/PullRefreshState;", "modifier", "Landroidx/compose/ui/Modifier;", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "contentColor", "scale", "PullRefreshIndicator-jB83MbM", "(ZLandroidx/compose/material/pullrefresh/PullRefreshState;Landroidx/compose/ui/Modifier;JJZLandroidx/compose/runtime/Composer;II)V", "CircularArrowIndicator", "color", "CircularArrowIndicator-iJQMabo", "(Landroidx/compose/material/pullrefresh/PullRefreshState;JLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "ArrowValues", "Landroidx/compose/material/pullrefresh/ArrowValues;", "progress", "", "drawArrow", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "arrow", "Landroidx/compose/ui/graphics/Path;", "bounds", "Landroidx/compose/ui/geometry/Rect;", "alpha", "values", "drawArrow-Bx497Mc", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/geometry/Rect;JFLandroidx/compose/material/pullrefresh/ArrowValues;)V", "CrossfadeDurationMs", "", "MaxProgressArc", "IndicatorSize", "Landroidx/compose/ui/unit/Dp;", "F", "SpinnerShape", "Landroidx/compose/foundation/shape/RoundedCornerShape;", "ArcRadius", "StrokeWidth", "ArrowWidth", "ArrowHeight", "Elevation", "MinAlpha", "MaxAlpha", "AlphaTween", "Landroidx/compose/animation/core/TweenSpec;", "material", "showElevation", "targetAlpha"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PullRefreshIndicatorKt {
    private static final int CrossfadeDurationMs = 100;
    private static final float MaxAlpha = 1.0f;
    private static final float MaxProgressArc = 0.8f;
    private static final float MinAlpha = 0.3f;
    private static final float IndicatorSize = Dp.m7905constructorimpl(40);
    private static final RoundedCornerShape SpinnerShape = RoundedCornerShapeKt.getCircleShape();
    private static final float ArcRadius = Dp.m7905constructorimpl((float) 7.5d);
    private static final float StrokeWidth = Dp.m7905constructorimpl((float) 2.5d);
    private static final float ArrowWidth = Dp.m7905constructorimpl(10);
    private static final float ArrowHeight = Dp.m7905constructorimpl(5);
    private static final float Elevation = Dp.m7905constructorimpl(6);
    private static final TweenSpec<Float> AlphaTween = AnimationSpecKt.tween$default(300, 0, EasingKt.getLinearEasing(), 2, null);

    static final Unit CircularArrowIndicator_iJQMabo$lambda$5(PullRefreshState pullRefreshState, long j, Modifier modifier, int i, Composer composer, int i2) {
        m2033CircularArrowIndicatoriJQMabo(pullRefreshState, j, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit PullRefreshIndicator_jB83MbM$lambda$3(boolean z, PullRefreshState pullRefreshState, Modifier modifier, long j, long j2, boolean z2, int i, int i2, Composer composer, int i3) {
        m2034PullRefreshIndicatorjB83MbM(z, pullRefreshState, modifier, j, j2, z2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:106:0x0189  */
    /* JADX WARN: Code duplicated, block: B:107:0x0196  */
    /* JADX WARN: Code duplicated, block: B:109:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:110:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:113:0x01da  */
    /* JADX WARN: Code duplicated, block: B:114:0x01df  */
    /* JADX WARN: Code duplicated, block: B:117:0x0271  */
    /* JADX WARN: Code duplicated, block: B:120:0x027d  */
    /* JADX WARN: Code duplicated, block: B:121:0x0283  */
    /* JADX WARN: Code duplicated, block: B:131:0x0370  */
    /* JADX INFO: renamed from: PullRefreshIndicator-jB83MbM, reason: not valid java name */
    public static final void m2034PullRefreshIndicatorjB83MbM(final boolean refreshing, final PullRefreshState state, Modifier modifier, long j, long j2, boolean scale, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j3;
        long j4;
        boolean scale2;
        Composer $composer2;
        final Modifier modifier3;
        final long j5;
        final long j6;
        final boolean scale3;
        Modifier.Companion modifier4;
        long jM1769getSurface0d7_KjU;
        final long jM1783contentColorForek8zF_U;
        int i2;
        State showElevation$delegate;
        int $dirty;
        ElevationOverlay elevationOverlay;
        Color colorM5087boximpl;
        long color;
        float fM7905constructorimpl;
        Composer $composer$iv;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        Composer $composer3 = $composer.startRestartGroup(308716636);
        ComposerKt.sourceInformation($composer3, "C(PullRefreshIndicator)N(refreshing,state,modifier,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,scale)83@3565L86,88@3936L7,92@4064L1050:PullRefreshIndicator.kt#t44y28");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(refreshing) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(state) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j3 = j;
                int i4 = $composer3.changed(j3) ? 2048 : 1024;
                $dirty2 |= i4;
            } else {
                j3 = j;
            }
            $dirty2 |= i4;
        } else {
            j3 = j;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                j4 = j2;
                int i5 = $composer3.changed(j4) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                j4 = j2;
            }
            $dirty2 |= i5;
        } else {
            j4 = j2;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            scale2 = scale;
        } else if ((196608 & $changed) == 0) {
            scale2 = scale;
            $dirty2 |= $composer3.changed(scale2) ? 131072 : 65536;
        } else {
            scale2 = scale;
        }
        if ($composer3.shouldExecute((74899 & $dirty2) != 74898, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "78@3424L6,79@3466L32");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                modifier4 = modifier2;
                jM1769getSurface0d7_KjU = j3;
                jM1783contentColorForek8zF_U = j4;
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 8) == 0) {
                    jM1769getSurface0d7_KjU = j3;
                } else {
                    jM1769getSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer3, 6).m1769getSurface0d7_KjU();
                    $dirty2 &= -7169;
                }
                if ((i & 16) == 0) {
                    jM1783contentColorForek8zF_U = j4;
                } else {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1769getSurface0d7_KjU, $composer3, ($dirty2 >> 9) & 14);
                    $dirty2 &= -57345;
                }
                if (i6 != 0) {
                    scale2 = false;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(308716636, $dirty2, -1, "androidx.compose.material.pullrefresh.PullRefreshIndicator (PullRefreshIndicator.kt:81)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 323953426, "CC(remember):PullRefreshIndicator.kt#9igjgp");
            boolean invalid$iv = (($dirty2 & 14) == 4) | $composer3.changed(state);
            Object it$iv = $composer3.rememberedValue();
            if (!invalid$iv) {
                i2 = 0;
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                }
                showElevation$delegate = (State) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
                $dirty = $dirty2;
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer3.consume(localElevationOverlay);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                elevationOverlay = (ElevationOverlay) objConsume;
                if (elevationOverlay == null) {
                    $composer3.startReplaceGroup(1453038224);
                    $composer3.endReplaceGroup();
                    $composer2 = $composer3;
                    colorM5087boximpl = null;
                } else {
                    $composer3.startReplaceGroup(323966865);
                    ComposerKt.sourceInformation($composer3, "90@3986L53");
                    long jMo1796apply7g2Lkgo = elevationOverlay.mo1796apply7g2Lkgo(jM1769getSurface0d7_KjU, Elevation, $composer3, (($dirty >> 9) & 14) | 48);
                    $composer2 = $composer3;
                    $composer2.endReplaceGroup();
                    colorM5087boximpl = Color.m5087boximpl(jMo1796apply7g2Lkgo);
                }
                if (colorM5087boximpl != null) {
                    color = colorM5087boximpl.m5107unboximpl();
                } else {
                    color = jM1769getSurface0d7_KjU;
                }
                Modifier modifierPullRefreshIndicatorTransform = PullRefreshIndicatorTransformKt.pullRefreshIndicatorTransform(SizeKt.m865size3ABfNKs(modifier4, IndicatorSize), state, scale2);
                if (PullRefreshIndicator_jB83MbM$lambda$1(showElevation$delegate)) {
                    fM7905constructorimpl = Elevation;
                } else {
                    fM7905constructorimpl = Dp.m7905constructorimpl(0);
                }
                Modifier modifier$iv = BackgroundKt.m264backgroundbw27NRU(ShadowKt.m4695shadows4CzXII$default(modifierPullRefreshIndicatorTransform, fM7905constructorimpl, SpinnerShape, true, 0L, 0L, 24, null), color, SpinnerShape);
                $composer$iv = $composer2;
                int $changed$iv = i2;
                ComposerKt.sourceInformationMarkerStart($composer$iv, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv = ($changed$iv << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, i2);
                CompositionLocalMap localMap$iv$iv = $composer$iv.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                Modifier modifier5 = modifier4;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer$iv.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer$iv.startReusableNode();
                if ($composer$iv.getInserting()) {
                    function0 = constructor;
                    $composer$iv.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer$iv.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer$iv);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i7 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i8 = (($changed$iv >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv, 1424656180, "C103@4511L597,100@4379L729:PullRefreshIndicator.kt#t44y28");
                CrossfadeKt.Crossfade(Boolean.valueOf(refreshing), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(1853731063, true, new Function3() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return PullRefreshIndicatorKt.PullRefreshIndicator_jB83MbM$lambda$2$0(jM1783contentColorForek8zF_U, state, ((Boolean) obj).booleanValue(), (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer$iv, 54), $composer$iv, ($dirty & 14) | 24960, 10);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j5 = jM1769getSurface0d7_KjU;
                j6 = jM1783contentColorForek8zF_U;
                modifier3 = modifier5;
                scale3 = scale2;
            } else {
                i2 = 0;
            }
            Object value$iv = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(PullRefreshIndicatorKt.PullRefreshIndicator_jB83MbM$lambda$0$0(refreshing, state));
                }
            });
            $composer3.updateRememberedValue(value$iv);
            it$iv = value$iv;
            showElevation$delegate = (State) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay2 = ElevationOverlayKt.getLocalElevationOverlay();
            $dirty = $dirty2;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localElevationOverlay2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            elevationOverlay = (ElevationOverlay) objConsume2;
            if (elevationOverlay == null) {
                $composer3.startReplaceGroup(1453038224);
                $composer3.endReplaceGroup();
                $composer2 = $composer3;
                colorM5087boximpl = null;
            } else {
                $composer3.startReplaceGroup(323966865);
                ComposerKt.sourceInformation($composer3, "90@3986L53");
                long jMo1796apply7g2Lkgo2 = elevationOverlay.mo1796apply7g2Lkgo(jM1769getSurface0d7_KjU, Elevation, $composer3, (($dirty >> 9) & 14) | 48);
                $composer2 = $composer3;
                $composer2.endReplaceGroup();
                colorM5087boximpl = Color.m5087boximpl(jMo1796apply7g2Lkgo2);
            }
            if (colorM5087boximpl != null) {
                color = colorM5087boximpl.m5107unboximpl();
            } else {
                color = jM1769getSurface0d7_KjU;
            }
            Modifier modifierPullRefreshIndicatorTransform2 = PullRefreshIndicatorTransformKt.pullRefreshIndicatorTransform(SizeKt.m865size3ABfNKs(modifier4, IndicatorSize), state, scale2);
            if (PullRefreshIndicator_jB83MbM$lambda$1(showElevation$delegate)) {
                fM7905constructorimpl = Elevation;
            } else {
                fM7905constructorimpl = Dp.m7905constructorimpl(0);
            }
            Modifier modifier$iv2 = BackgroundKt.m264backgroundbw27NRU(ShadowKt.m4695shadows4CzXII$default(modifierPullRefreshIndicatorTransform2, fM7905constructorimpl, SpinnerShape, true, 0L, 0L, 24, null), color, SpinnerShape);
            $composer$iv = $composer2;
            int $changed$iv2 = i2;
            ComposerKt.sourceInformationMarkerStart($composer$iv, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
            int $changed$iv$iv2 = ($changed$iv2 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer$iv, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer$iv, i2);
            CompositionLocalMap localMap$iv$iv2 = $composer$iv.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer$iv, modifier$iv2);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
            Modifier modifier6 = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer$iv, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer$iv.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer$iv.startReusableNode();
            if ($composer$iv.getInserting()) {
                function0 = constructor;
                $composer$iv.createNode(function0);
            } else {
                function0 = constructor;
                $composer$iv.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer$iv);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            int i9 = ($changed$iv$iv$iv2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer$iv, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i10 = (($changed$iv2 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer$iv, 1424656180, "C103@4511L597,100@4379L729:PullRefreshIndicator.kt#t44y28");
            CrossfadeKt.Crossfade(Boolean.valueOf(refreshing), (Modifier) null, AnimationSpecKt.tween$default(100, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(1853731063, true, new Function3() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return PullRefreshIndicatorKt.PullRefreshIndicator_jB83MbM$lambda$2$0(jM1783contentColorForek8zF_U, state, ((Boolean) obj).booleanValue(), (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer$iv, 54), $composer$iv, ($dirty & 14) | 24960, 10);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            $composer$iv.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j5 = jM1769getSurface0d7_KjU;
            j6 = jM1783contentColorForek8zF_U;
            modifier3 = modifier6;
            scale3 = scale2;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            j5 = j3;
            j6 = j4;
            scale3 = scale2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PullRefreshIndicatorKt.PullRefreshIndicator_jB83MbM$lambda$3(refreshing, state, modifier3, j5, j6, scale3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PullRefreshIndicator_jB83MbM$lambda$0$0(boolean $refreshing, PullRefreshState $state) {
        return $refreshing || $state.getPosition$material() > 0.5f;
    }

    private static final boolean PullRefreshIndicator_jB83MbM$lambda$1(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit PullRefreshIndicator_jB83MbM$lambda$2$0(long j, PullRefreshState $state, boolean refreshing, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "CN(refreshing)104@4539L559:PullRefreshIndicator.kt#t44y28");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(refreshing) ? 4 : 2;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1853731063, $dirty, -1, "androidx.compose.material.pullrefresh.PullRefreshIndicator.<anonymous>.<anonymous> (PullRefreshIndicator.kt:104)");
            }
            Modifier modifier$iv = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
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
            ComposerKt.sourceInformationMarkerStart($composer, -1916688881, "C:PullRefreshIndicator.kt#t44y28");
            float arg0$iv = ArcRadius;
            float other$iv = StrokeWidth;
            float arg0$iv2 = Dp.m7905constructorimpl(arg0$iv + other$iv);
            float arg0$iv3 = 2;
            float spinnerSize = Dp.m7905constructorimpl(arg0$iv3 * arg0$iv2);
            if (refreshing) {
                $composer.startReplaceGroup(-1916589279);
                ComposerKt.sourceInformation($composer, "108@4741L208");
                ProgressIndicatorKt.m1889CircularProgressIndicatorLxG7B9w(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, spinnerSize), j, StrokeWidth, 0L, 0, $composer, 390, 24);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1916362142);
                ComposerKt.sourceInformation($composer, "114@4995L71");
                m2033CircularArrowIndicatoriJQMabo($state, j, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, spinnerSize), $composer, 384);
                $composer.endReplaceGroup();
            }
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

    /* JADX INFO: renamed from: CircularArrowIndicator-iJQMabo, reason: not valid java name */
    private static final void m2033CircularArrowIndicatoriJQMabo(final PullRefreshState state, final long j, final Modifier modifier, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-486016981);
        ComposerKt.sourceInformation($composer2, "C(CircularArrowIndicator)N(state,color:c#ui.graphics.Color,modifier)125@5306L61,128@5400L87,130@5510L74,133@5649L2,133@5653L950,133@5623L980:PullRefreshIndicator.kt#t44y28");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(j) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(modifier) ? 256 : 128;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-486016981, $dirty, -1, "androidx.compose.material.pullrefresh.CircularArrowIndicator (PullRefreshIndicator.kt:124)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1089119880, "CC(remember):PullRefreshIndicator.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Path $this$CircularArrowIndicator_iJQMabo_u24lambda_u240_u240 = AndroidPath_androidKt.Path();
                $this$CircularArrowIndicator_iJQMabo_u24lambda_u240_u240.mo4987setFillTypeoQ8Xj4U(PathFillType.INSTANCE.m5393getEvenOddRgk1Os());
                $composer2.updateRememberedValue($this$CircularArrowIndicator_iJQMabo_u24lambda_u240_u240);
                it$iv = $this$CircularArrowIndicator_iJQMabo_u24lambda_u240_u240;
            }
            final Path path = (Path) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1089122914, "CC(remember):PullRefreshIndicator.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(state);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(PullRefreshIndicatorKt.CircularArrowIndicator_iJQMabo$lambda$1$0(state));
                    }
                });
                $composer2.updateRememberedValue(value$iv);
                it$iv2 = value$iv;
            }
            State targetAlpha$delegate = (State) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(CircularArrowIndicator_iJQMabo$lambda$2(targetAlpha$delegate), AlphaTween, 0.0f, null, null, $composer2, 48, 28);
            ComposerKt.sourceInformationMarkerStart($composer2, 1089130797, "CC(remember):PullRefreshIndicator.kt#9igjgp");
            Object it$iv3 = $composer2.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PullRefreshIndicatorKt.CircularArrowIndicator_iJQMabo$lambda$3$0((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                it$iv3 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier, false, (Function1) it$iv3, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1089131873, "CC(remember):PullRefreshIndicator.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changedInstance(state) | $composer2.changed(stateAnimateFloatAsState) | (($dirty & 112) == 32) | $composer2.changedInstance(path);
            Object it$iv4 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = new Function1() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PullRefreshIndicatorKt.CircularArrowIndicator_iJQMabo$lambda$4$0(state, stateAnimateFloatAsState, j, path, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
                it$iv4 = value$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierSemantics$default, (Function1) it$iv4, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.pullrefresh.PullRefreshIndicatorKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PullRefreshIndicatorKt.CircularArrowIndicator_iJQMabo$lambda$5(state, j, modifier, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float CircularArrowIndicator_iJQMabo$lambda$1$0(PullRefreshState $state) {
        if ($state.getProgress() < 1.0f) {
            return MinAlpha;
        }
        return 1.0f;
    }

    private static final float CircularArrowIndicator_iJQMabo$lambda$2(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CircularArrowIndicator_iJQMabo$lambda$3$0(SemanticsPropertyReceiver $this$semantics) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CircularArrowIndicator_iJQMabo$lambda$4$0(PullRefreshState $state, State $alphaState, long j, Path $path, DrawScope $this$Canvas) throws Throwable {
        DrawContext $this$withTransform_u24lambda_u246$iv$iv;
        long previousSize$iv$iv;
        ArrowValues values = ArrowValues($state.getProgress());
        float alpha = ((Number) $alphaState.getValue()).floatValue();
        float degrees$iv = values.getRotation();
        long pivot$iv = $this$Canvas.mo5670getCenterF1C5BW0();
        DrawContext $this$withTransform_u24lambda_u246$iv$iv2 = $this$Canvas.getDrawContext();
        long previousSize$iv$iv2 = $this$withTransform_u24lambda_u246$iv$iv2.mo5592getSizeNHjbRc();
        $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().save();
        try {
            DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv = $this$withTransform_u24lambda_u246$iv$iv2.getTransform();
            $this$rotate_Rg1IO4c_u24lambda_u240$iv.mo5598rotateUv8p0NA(degrees$iv, pivot$iv);
            float arcRadius = $this$Canvas.mo406toPx0680j_4(ArcRadius) + ($this$Canvas.mo406toPx0680j_4(StrokeWidth) / 2.0f);
            long arg0$iv = androidx.compose.ui.geometry.SizeKt.m4931getCenteruvyYCjk($this$Canvas.mo5671getSizeNHjbRc());
            int bits$iv$iv$iv = (int) (arg0$iv >> 32);
            try {
                float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv) - arcRadius;
                long arg0$iv2 = androidx.compose.ui.geometry.SizeKt.m4931getCenteruvyYCjk($this$Canvas.mo5671getSizeNHjbRc());
                int bits$iv$iv$iv2 = (int) (arg0$iv2 & 4294967295L);
                try {
                    float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv2) - arcRadius;
                    long arg0$iv3 = androidx.compose.ui.geometry.SizeKt.m4931getCenteruvyYCjk($this$Canvas.mo5671getSizeNHjbRc());
                    int bits$iv$iv$iv3 = (int) (arg0$iv3 >> 32);
                    float fIntBitsToFloat3 = Float.intBitsToFloat(bits$iv$iv$iv3) + arcRadius;
                    long arg0$iv4 = androidx.compose.ui.geometry.SizeKt.m4931getCenteruvyYCjk($this$Canvas.mo5671getSizeNHjbRc());
                    int bits$iv$iv$iv4 = (int) (arg0$iv4 & 4294967295L);
                    Rect arcBounds = new Rect(fIntBitsToFloat, fIntBitsToFloat2, fIntBitsToFloat3, Float.intBitsToFloat(bits$iv$iv$iv4) + arcRadius);
                    DrawScope.m5650drawArcyD3GUKo$default($this$Canvas, j, values.getStartAngle(), values.getEndAngle() - values.getStartAngle(), false, arcBounds.m4887getTopLeftF1C5BW0(), arcBounds.m4885getSizeNHjbRc(), alpha, new Stroke($this$Canvas.mo406toPx0680j_4(StrokeWidth), 0.0f, StrokeCap.INSTANCE.m5473getSquareKaPHkGw(), 0, null, 26, null), null, 0, 768, null);
                    $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                    previousSize$iv$iv = previousSize$iv$iv2;
                    try {
                        m2035drawArrowBx497Mc($this$Canvas, $path, arcBounds, j, alpha, values);
                        $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                        $this$withTransform_u24lambda_u246$iv$iv.mo5593setSizeuvyYCjk(previousSize$iv$iv);
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        th = th;
                        $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                        $this$withTransform_u24lambda_u246$iv$iv.mo5593setSizeuvyYCjk(previousSize$iv$iv);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                    previousSize$iv$iv = previousSize$iv$iv2;
                }
            } catch (Throwable th3) {
                th = th3;
                $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                previousSize$iv$iv = previousSize$iv$iv2;
            }
        } catch (Throwable th4) {
            th = th4;
            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
            previousSize$iv$iv = previousSize$iv$iv2;
        }
    }

    private static final ArrowValues ArrowValues(float progress) {
        float adjustedPercent = (Math.max(Math.min(1.0f, progress) - 0.4f, 0.0f) * 5.0f) / 3.0f;
        float overshootPercent = Math.abs(progress) - 1.0f;
        float $this$fastCoerceAtLeast$iv$iv = overshootPercent;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 2.0f) {
            $this$fastCoerceAtLeast$iv$iv = 2.0f;
        }
        float tensionPercent = $this$fastCoerceAtLeast$iv$iv - (((float) Math.pow($this$fastCoerceAtLeast$iv$iv, 2.0d)) / 4.0f);
        float endTrim = 0.8f * adjustedPercent;
        float rotation = (((0.4f * adjustedPercent) - 0.25f) + tensionPercent) * 0.5f;
        float startAngle = rotation * 360.0f;
        float endAngle = (rotation + endTrim) * 360.0f;
        float scale = Math.min(1.0f, adjustedPercent);
        return new ArrowValues(rotation, startAngle, endAngle, scale);
    }

    /* JADX INFO: renamed from: drawArrow-Bx497Mc, reason: not valid java name */
    private static final void m2035drawArrowBx497Mc(DrawScope $this$drawArrow_u2dBx497Mc, Path arrow, Rect bounds, long j, float alpha, ArrowValues values) throws Throwable {
        long previousSize$iv$iv;
        DrawContext $this$withTransform_u24lambda_u246$iv$iv;
        arrow.reset();
        arrow.moveTo(0.0f, 0.0f);
        arrow.lineTo($this$drawArrow_u2dBx497Mc.mo406toPx0680j_4(ArrowWidth) * values.getScale(), 0.0f);
        arrow.lineTo(($this$drawArrow_u2dBx497Mc.mo406toPx0680j_4(ArrowWidth) * values.getScale()) / 2.0f, $this$drawArrow_u2dBx497Mc.mo406toPx0680j_4(ArrowHeight) * values.getScale());
        float radius = Math.min(bounds.getRight() - bounds.getLeft(), bounds.getBottom() - bounds.getTop()) / 2.0f;
        float inset = ($this$drawArrow_u2dBx497Mc.mo406toPx0680j_4(ArrowWidth) * values.getScale()) / 2.0f;
        long arg0$iv = bounds.m4882getCenterF1C5BW0();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float x$iv = (Float.intBitsToFloat(bits$iv$iv$iv) + radius) - inset;
        long arg0$iv2 = bounds.m4882getCenterF1C5BW0();
        int bits$iv$iv$iv2 = (int) (arg0$iv2 & 4294967295L);
        float y$iv = Float.intBitsToFloat(bits$iv$iv$iv2) + ($this$drawArrow_u2dBx497Mc.mo406toPx0680j_4(StrokeWidth) / 2.0f);
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        arrow.mo4989translatek4lQ0M(Offset.m4844constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv)));
        arrow.close();
        float degrees$iv = values.getEndAngle();
        long pivot$iv = $this$drawArrow_u2dBx497Mc.mo5670getCenterF1C5BW0();
        DrawContext $this$withTransform_u24lambda_u246$iv$iv2 = $this$drawArrow_u2dBx497Mc.getDrawContext();
        long previousSize$iv$iv2 = $this$withTransform_u24lambda_u246$iv$iv2.mo5592getSizeNHjbRc();
        $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().save();
        try {
            DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv = $this$withTransform_u24lambda_u246$iv$iv2.getTransform();
            $this$rotate_Rg1IO4c_u24lambda_u240$iv.mo5598rotateUv8p0NA(degrees$iv, pivot$iv);
            previousSize$iv$iv = previousSize$iv$iv2;
            try {
                DrawScope.m5661drawPathLG529CI$default($this$drawArrow_u2dBx497Mc, arrow, j, alpha, null, null, 0, 56, null);
                $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().restore();
                $this$withTransform_u24lambda_u246$iv$iv2.mo5593setSizeuvyYCjk(previousSize$iv$iv);
            } catch (Throwable th) {
                th = th;
                $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u246$iv$iv.mo5593setSizeuvyYCjk(previousSize$iv$iv);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            previousSize$iv$iv = previousSize$iv$iv2;
            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
        }
    }
}
