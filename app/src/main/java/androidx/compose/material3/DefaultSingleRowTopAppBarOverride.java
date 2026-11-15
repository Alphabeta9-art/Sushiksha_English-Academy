package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.material3.internal.FloatProducer;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.media3.extractor.ts.TsExtractor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0017¢\u0006\u0002\u0010\u0007¨\u0006\b²\u0006\n\u0010\t\u001a\u00020\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material3/DefaultSingleRowTopAppBarOverride;", "Landroidx/compose/material3/SingleRowTopAppBarOverride;", "<init>", "()V", "SingleRowTopAppBar", "", "Landroidx/compose/material3/SingleRowTopAppBarOverrideScope;", "(Landroidx/compose/material3/SingleRowTopAppBarOverrideScope;Landroidx/compose/runtime/Composer;I)V", "material3", "targetColor", "Landroidx/compose/ui/graphics/Color;"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DefaultSingleRowTopAppBarOverride implements SingleRowTopAppBarOverride {
    public static final int $stable = 0;
    public static final DefaultSingleRowTopAppBarOverride INSTANCE = new DefaultSingleRowTopAppBarOverride();

    static final Unit SingleRowTopAppBar$lambda$16(DefaultSingleRowTopAppBarOverride defaultSingleRowTopAppBarOverride, SingleRowTopAppBarOverrideScope singleRowTopAppBarOverrideScope, int i, Composer composer, int i2) {
        defaultSingleRowTopAppBarOverride.SingleRowTopAppBar(singleRowTopAppBarOverrideScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private DefaultSingleRowTopAppBarOverride() {
    }

    /* JADX WARN: Code duplicated, block: B:102:0x03f0  */
    /* JADX WARN: Code duplicated, block: B:106:0x0441  */
    /* JADX WARN: Code duplicated, block: B:27:0x0068  */
    /* JADX WARN: Code duplicated, block: B:91:0x0357  */
    /* JADX WARN: Code duplicated, block: B:92:0x035a  */
    @Override // androidx.compose.material3.SingleRowTopAppBarOverride
    public void SingleRowTopAppBar(final SingleRowTopAppBarOverrideScope $this$SingleRowTopAppBar, Composer $composer, final int $changed) {
        boolean z;
        Modifier.Companion appBarDragModifier;
        Function0<ComposeUiNode> function0;
        int $changed$iv;
        boolean invalid$iv;
        Object value$iv;
        Object it$iv;
        Composer $composer2 = $composer.startRestartGroup(2137486921);
        ComposerKt.sourceInformation($composer2, "C(SingleRowTopAppBar)2520@118647L280,2531@119179L7,2528@118976L225,2536@119296L222,2574@120882L222,2580@121136L27,2581@121204L2,2570@120751L1724:AppBar.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed($this$SingleRowTopAppBar) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2137486921, $dirty2, -1, "androidx.compose.material3.DefaultSingleRowTopAppBarOverride.SingleRowTopAppBar (AppBar.kt:2510)");
            }
            float $this$isSpecified$iv = $this$SingleRowTopAppBar.getExpandedHeight();
            if (Float.isNaN($this$isSpecified$iv)) {
                z = false;
            } else {
                float $this$isFinite$iv = $this$SingleRowTopAppBar.getExpandedHeight();
                if ((Float.floatToRawIntBits($this$isFinite$iv) & Integer.MAX_VALUE) < 2139095040) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (!z) {
                throw new IllegalArgumentException("The expandedHeight is expected to be specified and finite".toString());
            }
            Object colors = $this$SingleRowTopAppBar.getColors();
            Object scrollBehavior = $this$SingleRowTopAppBar.getScrollBehavior();
            ComposerKt.sourceInformationMarkerStart($composer2, 1130584929, "CC(remember):AppBar.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changed(colors) | $composer2.changed(scrollBehavior);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = SnapshotStateKt.derivedStateOf(new Function0<Color>() { // from class: androidx.compose.material3.DefaultSingleRowTopAppBarOverride$SingleRowTopAppBar$targetColor$2$1
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Color invoke() {
                        return Color.m5087boximpl(m2385invoke0d7_KjU());
                    }

                    /* JADX INFO: renamed from: invoke-0d7_KjU, reason: not valid java name */
                    public final long m2385invoke0d7_KjU() {
                        TopAppBarState state;
                        TopAppBarScrollBehavior scrollBehavior2 = $this$SingleRowTopAppBar.getScrollBehavior();
                        float overlappingFraction = (scrollBehavior2 == null || (state = scrollBehavior2.getState()) == null) ? 0.0f : state.getOverlappedFraction();
                        return $this$SingleRowTopAppBar.getColors().m3267containerColorvNxB06k$material3(overlappingFraction > 0.01f ? 1.0f : 0.0f);
                    }
                });
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            State targetColor$delegate = (State) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final State<Color> stateM135animateColorAsStateeuL9pac = SingleValueAnimationKt.m135animateColorAsStateeuL9pac(SingleRowTopAppBar$lambda$2(targetColor$delegate), MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer2, 6), null, null, $composer2, 0, 12);
            Function2 actionsRow = ComposableLambdaKt.rememberComposableLambda(-1658896622, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DefaultSingleRowTopAppBarOverride$SingleRowTopAppBar$actionsRow$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    Function0<ComposeUiNode> function1;
                    ComposerKt.sourceInformation($composer3, "C2537@119314L190:AppBar.kt#uh7d8r");
                    if ($composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1658896622, $changed2, -1, "androidx.compose.material3.DefaultSingleRowTopAppBarOverride.SingleRowTopAppBar.<anonymous> (AppBar.kt:2537)");
                        }
                        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getEnd();
                        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                        Function3<RowScope, Composer, Integer, Unit> actions = $this$SingleRowTopAppBar.getActions();
                        ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
                        Modifier modifier$iv = Modifier.INSTANCE;
                        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                        int $changed$iv$iv = (432 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
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
                            function1 = constructor;
                            $composer3.createNode(function1);
                        } else {
                            function1 = constructor;
                            $composer3.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                        actions.invoke(RowScopeInstance.INSTANCE, $composer3, Integer.valueOf(((432 >> 6) & 112) | 6));
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54);
            if ($this$SingleRowTopAppBar.getScrollBehavior() == null || $this$SingleRowTopAppBar.getScrollBehavior().getIsPinned()) {
                $composer2.startReplaceGroup(690108113);
                $composer2.endReplaceGroup();
                appBarDragModifier = Modifier.INSTANCE;
            } else {
                $composer2.startReplaceGroup(689467622);
                ComposerKt.sourceInformation($composer2, "2550@119886L107,2550@119863L130,2553@120031L312");
                Modifier.Companion companion = Modifier.INSTANCE;
                Orientation orientation = Orientation.Vertical;
                ComposerKt.sourceInformationMarkerStart($composer2, 1130624404, "CC(remember):AppBar.kt#9igjgp");
                boolean invalid$iv3 = ($dirty2 & 14) == 4;
                Object it$iv3 = $composer2.rememberedValue();
                if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = new Function1() { // from class: androidx.compose.material3.DefaultSingleRowTopAppBarOverride$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return DefaultSingleRowTopAppBarOverride.SingleRowTopAppBar$lambda$4$lambda$3($this$SingleRowTopAppBar, ((Float) obj).floatValue());
                        }
                    };
                    $composer2.updateRememberedValue(value$iv3);
                    it$iv3 = value$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                DraggableState draggableStateRememberDraggableState = DraggableKt.rememberDraggableState((Function1) it$iv3, $composer2, 0);
                Modifier.Companion companion2 = companion;
                ComposerKt.sourceInformationMarkerStart($composer2, 1130629249, "CC(remember):AppBar.kt#9igjgp");
                boolean invalid$iv4 = ($dirty2 & 14) == 4;
                Object it$iv4 = $composer2.rememberedValue();
                if (invalid$iv4 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv4 = (Function3) new DefaultSingleRowTopAppBarOverride$SingleRowTopAppBar$appBarDragModifier$2$1($this$SingleRowTopAppBar, null);
                    $composer2.updateRememberedValue(value$iv4);
                    it$iv4 = value$iv4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                appBarDragModifier = DraggableKt.draggable(companion2, draggableStateRememberDraggableState, orientation, (TsExtractor.TS_PACKET_SIZE & 4) != 0, (TsExtractor.TS_PACKET_SIZE & 8) != 0 ? null : null, (TsExtractor.TS_PACKET_SIZE & 16) != 0 ? false : false, (TsExtractor.TS_PACKET_SIZE & 32) != 0 ? DraggableKt.NoOpOnDragStarted : null, (TsExtractor.TS_PACKET_SIZE & 64) != 0 ? DraggableKt.NoOpOnDragStopped : (Function3) it$iv4, (TsExtractor.TS_PACKET_SIZE & 128) != 0 ? false : false);
                $composer2.endReplaceGroup();
            }
            Modifier modifierThen = $this$SingleRowTopAppBar.getModifier().then(appBarDragModifier);
            ComposerKt.sourceInformationMarkerStart($composer2, 1130656391, "CC(remember):AppBar.kt#9igjgp");
            boolean invalid$iv5 = $composer2.changed(stateM135animateColorAsStateeuL9pac);
            Object it$iv5 = $composer2.rememberedValue();
            if (invalid$iv5 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                Object value$iv5 = new Function1() { // from class: androidx.compose.material3.DefaultSingleRowTopAppBarOverride$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DefaultSingleRowTopAppBarOverride.SingleRowTopAppBar$lambda$7$lambda$6(stateM135animateColorAsStateeuL9pac, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv5);
                it$iv5 = value$iv5;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierDrawBehind = DrawModifierKt.drawBehind(modifierThen, (Function1) it$iv5);
            ComposerKt.sourceInformationMarkerStart($composer2, 1130664324, "CC(remember):AppBar.kt#9igjgp");
            Object it$iv6 = $composer2.rememberedValue();
            if (it$iv6 == Composer.INSTANCE.getEmpty()) {
                Object value$iv6 = new Function1() { // from class: androidx.compose.material3.DefaultSingleRowTopAppBarOverride$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DefaultSingleRowTopAppBarOverride.SingleRowTopAppBar$lambda$9$lambda$8((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv6);
                it$iv6 = value$iv6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierDrawBehind, false, (Function1) it$iv6, 1, null);
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 1130666475, "CC(remember):AppBar.kt#9igjgp");
            Object it$iv7 = $composer2.rememberedValue();
            if (it$iv7 == Composer.INSTANCE.getEmpty()) {
                Object value$iv7 = (PointerInputEventHandler) new PointerInputEventHandler() { // from class: androidx.compose.material3.DefaultSingleRowTopAppBarOverride$SingleRowTopAppBar$4$1
                    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                    public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                        return Unit.INSTANCE;
                    }
                };
                $composer2.updateRememberedValue(value$iv7);
                it$iv7 = value$iv7;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv = SuspendingPointerInputFilterKt.pointerInput(modifierSemantics$default, unit, (PointerInputEventHandler) it$iv7);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                $changed$iv = 0;
            } else {
                $changed$iv = 0;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i2 = (($changed$iv >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1199733554, "C2589@121570L45,2598@122100L6,2583@121231L1234:AppBar.kt#uh7d8r");
                Modifier modifierAdjustHeightOffsetLimit = AppBarKt.adjustHeightOffsetLimit(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, $this$SingleRowTopAppBar.getWindowInsets())), $this$SingleRowTopAppBar.getScrollBehavior());
                ComposerKt.sourceInformationMarkerStart($composer2, -592880752, "CC(remember):AppBar.kt#9igjgp");
                if (($dirty2 & 14) == 4) {
                    invalid$iv = true;
                } else {
                    invalid$iv = false;
                }
                value$iv = $composer2.rememberedValue();
                if (!invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = new FloatProducer() { // from class: androidx.compose.material3.DefaultSingleRowTopAppBarOverride$$ExternalSyntheticLambda3
                        @Override // androidx.compose.material3.internal.FloatProducer
                        public final float invoke() {
                            return DefaultSingleRowTopAppBarOverride.SingleRowTopAppBar$lambda$15$lambda$12$lambda$11($this$SingleRowTopAppBar);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                }
                FloatProducer floatProducer = (FloatProducer) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                long navigationIconContentColor = $this$SingleRowTopAppBar.getColors().getNavigationIconContentColor();
                long titleContentColor = $this$SingleRowTopAppBar.getColors().getTitleContentColor();
                long actionIconContentColor = $this$SingleRowTopAppBar.getColors().getActionIconContentColor();
                long subtitleContentColor = $this$SingleRowTopAppBar.getColors().getSubtitleContentColor();
                Function2<Composer, Integer, Unit> title = $this$SingleRowTopAppBar.getTitle();
                TextStyle titleTextStyle = $this$SingleRowTopAppBar.getTitleTextStyle();
                Function2<Composer, Integer, Unit> subtitle = $this$SingleRowTopAppBar.getSubtitle();
                TextStyle subtitleTextStyle = $this$SingleRowTopAppBar.getSubtitleTextStyle();
                Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                Alignment.Horizontal titleHorizontalAlignment = $this$SingleRowTopAppBar.getTitleHorizontalAlignment();
                Function2<Composer, Integer, Unit> navigationIcon = $this$SingleRowTopAppBar.getNavigationIcon();
                float expandedHeight = $this$SingleRowTopAppBar.getExpandedHeight();
                ComposerKt.sourceInformationMarkerStart($composer2, -592863831, "CC(remember):AppBar.kt#9igjgp");
                it$iv = $composer2.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv8 = new Function0() { // from class: androidx.compose.material3.DefaultSingleRowTopAppBarOverride$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Float.valueOf(DefaultSingleRowTopAppBarOverride.SingleRowTopAppBar$lambda$15$lambda$14$lambda$13());
                        }
                    };
                    $composer2.updateRememberedValue(value$iv8);
                    it$iv = value$iv8;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                AppBarKt.m2092TopAppBarLayoutlyUyIHI(modifierAdjustHeightOffsetLimit, floatProducer, navigationIconContentColor, titleContentColor, subtitleContentColor, actionIconContentColor, title, titleTextStyle, subtitle, subtitleTextStyle, (Function0) it$iv, center, titleHorizontalAlignment, 0, false, navigationIcon, actionsRow, expandedHeight, $composer2, 0, 1600566);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i4 = (($changed$iv >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1199733554, "C2589@121570L45,2598@122100L6,2583@121231L1234:AppBar.kt#uh7d8r");
            Modifier modifierAdjustHeightOffsetLimit2 = AppBarKt.adjustHeightOffsetLimit(ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, $this$SingleRowTopAppBar.getWindowInsets())), $this$SingleRowTopAppBar.getScrollBehavior());
            ComposerKt.sourceInformationMarkerStart($composer2, -592880752, "CC(remember):AppBar.kt#9igjgp");
            if (($dirty2 & 14) == 4) {
                invalid$iv = true;
            } else {
                invalid$iv = false;
            }
            value$iv = $composer2.rememberedValue();
            if (!invalid$iv) {
            }
            value$iv = new FloatProducer() { // from class: androidx.compose.material3.DefaultSingleRowTopAppBarOverride$$ExternalSyntheticLambda3
                @Override // androidx.compose.material3.internal.FloatProducer
                public final float invoke() {
                    return DefaultSingleRowTopAppBarOverride.SingleRowTopAppBar$lambda$15$lambda$12$lambda$11($this$SingleRowTopAppBar);
                }
            };
            $composer2.updateRememberedValue(value$iv);
            FloatProducer floatProducer2 = (FloatProducer) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            long navigationIconContentColor2 = $this$SingleRowTopAppBar.getColors().getNavigationIconContentColor();
            long titleContentColor2 = $this$SingleRowTopAppBar.getColors().getTitleContentColor();
            long actionIconContentColor2 = $this$SingleRowTopAppBar.getColors().getActionIconContentColor();
            long subtitleContentColor2 = $this$SingleRowTopAppBar.getColors().getSubtitleContentColor();
            Function2<Composer, Integer, Unit> title2 = $this$SingleRowTopAppBar.getTitle();
            TextStyle titleTextStyle2 = $this$SingleRowTopAppBar.getTitleTextStyle();
            Function2<Composer, Integer, Unit> subtitle2 = $this$SingleRowTopAppBar.getSubtitle();
            TextStyle subtitleTextStyle2 = $this$SingleRowTopAppBar.getSubtitleTextStyle();
            Arrangement.HorizontalOrVertical center2 = Arrangement.INSTANCE.getCenter();
            Alignment.Horizontal titleHorizontalAlignment2 = $this$SingleRowTopAppBar.getTitleHorizontalAlignment();
            Function2<Composer, Integer, Unit> navigationIcon2 = $this$SingleRowTopAppBar.getNavigationIcon();
            float expandedHeight2 = $this$SingleRowTopAppBar.getExpandedHeight();
            ComposerKt.sourceInformationMarkerStart($composer2, -592863831, "CC(remember):AppBar.kt#9igjgp");
            it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv9 = new Function0() { // from class: androidx.compose.material3.DefaultSingleRowTopAppBarOverride$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(DefaultSingleRowTopAppBarOverride.SingleRowTopAppBar$lambda$15$lambda$14$lambda$13());
                    }
                };
                $composer2.updateRememberedValue(value$iv9);
                it$iv = value$iv9;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AppBarKt.m2092TopAppBarLayoutlyUyIHI(modifierAdjustHeightOffsetLimit2, floatProducer2, navigationIconContentColor2, titleContentColor2, subtitleContentColor2, actionIconContentColor2, title2, titleTextStyle2, subtitle2, subtitleTextStyle2, (Function0) it$iv, center2, titleHorizontalAlignment2, 0, false, navigationIcon2, actionsRow, expandedHeight2, $composer2, 0, 1600566);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DefaultSingleRowTopAppBarOverride$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DefaultSingleRowTopAppBarOverride.SingleRowTopAppBar$lambda$16(this.f$0, $this$SingleRowTopAppBar, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final long SingleRowTopAppBar$lambda$2(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m5107unboximpl();
    }

    static final Unit SingleRowTopAppBar$lambda$4$lambda$3(SingleRowTopAppBarOverrideScope $this_SingleRowTopAppBar, float delta) {
        TopAppBarState state = $this_SingleRowTopAppBar.getScrollBehavior().getState();
        state.setHeightOffset(state.getHeightOffset() + delta);
        return Unit.INSTANCE;
    }

    static final Unit SingleRowTopAppBar$lambda$7$lambda$6(State $appBarContainerColor, DrawScope $this$drawBehind) {
        long color = ((Color) $appBarContainerColor.getValue()).m5107unboximpl();
        if (!Color.m5098equalsimpl0(color, Color.INSTANCE.m5133getUnspecified0d7_KjU())) {
            DrawScope.m5665drawRectnJ9OG0$default($this$drawBehind, color, 0L, 0L, 0.0f, null, null, 0, WebSocketProtocol.PAYLOAD_SHORT, null);
        }
        return Unit.INSTANCE;
    }

    static final Unit SingleRowTopAppBar$lambda$9$lambda$8(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setTraversalGroup($this$semantics, true);
        return Unit.INSTANCE;
    }

    static final float SingleRowTopAppBar$lambda$15$lambda$12$lambda$11(SingleRowTopAppBarOverrideScope $this_SingleRowTopAppBar) {
        TopAppBarState state;
        TopAppBarScrollBehavior scrollBehavior = $this_SingleRowTopAppBar.getScrollBehavior();
        if (scrollBehavior == null || (state = scrollBehavior.getState()) == null) {
            return 0.0f;
        }
        return state.getHeightOffset();
    }

    static final float SingleRowTopAppBar$lambda$15$lambda$14$lambda$13() {
        return 1.0f;
    }
}
